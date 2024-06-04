package com.example.java_project.Controller;

import com.example.java_project.Entities.Account;
import com.example.java_project.Models.AccountRequest;
import com.example.java_project.Models.LoginRequest;
import com.example.java_project.Models.RegisterRequest;
import com.example.java_project.Services.IAccountService;
import com.example.java_project.extensions.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class HomeController {
    @Autowired
    private IAccountService accountService;

    @GetMapping("/")
    public String homepage(@ModelAttribute("loginRequest") LoginRequest loginRequest) {
        return "index";
    }

    @PostMapping("/login")
    public String login(LoginRequest loginRequest, RedirectAttributes redirectAttributes) {
        boolean isValid = accountService.validateAccount(loginRequest.getUserName(), loginRequest.getPassword());
        if (!isValid) {
            redirectAttributes.addFlashAttribute("message", "Invalid username or password");
            return "redirect:/";
        }

        return "main/main";
    }

    @GetMapping("/accounts")
    public String getAccounts() {
        return "accounts/account";
    }

    @GetMapping("/redirectToRegister")
    public String redirectToRegister(@ModelAttribute("registerRequest") RegisterRequest registerRequest) {
        return "accounts/register";
    }

    @PostMapping("/register")
    public String register(RegisterRequest createAccountRequest, RedirectAttributes redirectAttributes) {
        var isSame = checkSamePassword(createAccountRequest.getPassword(), createAccountRequest.getSamePassword());
        if (!isSame) {
            redirectAttributes.addFlashAttribute("message", "Password not same");
            return "redirect:/register";
        }

        var isRegistered = accountService.registerAccount(createAccountRequest);
        if (!isRegistered) {
            redirectAttributes.addFlashAttribute("message", "User already exists");
            return "redirect:/register";
        }

        return "index";
    }

    private boolean checkSamePassword(String password, String samePassword) {
        return password.equals(samePassword);
    }

    @GetMapping("/redirectAccountCreate")
    public String redirectAccountCreate(@ModelAttribute("createAccountRequest") RegisterRequest createAccountRequest) {
        return "accounts/create";
    }

    @PostMapping("/createAccount")
    public String createAccount(@ModelAttribute("createAccountRequest") RegisterRequest createAccountRequest, RedirectAttributes redirectAttributes, Model model) {
        var isSame = checkSamePassword(createAccountRequest.getPassword(), createAccountRequest.getSamePassword());
        if (!isSame) {
            redirectAttributes.addFlashAttribute("password", "Password not same");
            return "redirect:/accounts/create";
        }

        var isCreated = accountService.registerAccount(createAccountRequest);
        if (!isCreated) {
            redirectAttributes.addFlashAttribute("message", "Account already exists");
            return "redirect:/accounts/create";
        }

        var accounts = accountService.getAll();
        model.addAttribute("accounts", accounts);
        return "/accounts/account";
    }

    @GetMapping("/accounts/page")
    @ResponseBody
    public Page<Account> accountList(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                     @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        return accountService.getAllPagination(pageNumber, pageSize);
    }

    @GetMapping("/accounts/page/{userName}")
    @ResponseBody
    public Page<Account> getByUserName(
            @PathVariable(name = "userName") String userName,
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        var accountPage = accountService.searchAccountByName(userName, pageNumber, pageSize);
        AccountRequest accountRequest = new AccountRequest();
        accountPage.map(account -> {
            accountRequest.setUserName(account.getUserName());
            accountRequest.setPassword(account.getPassword());
            accountRequest.setEmail(account.getEmail());
            accountRequest.setActive(account.isActive());
            accountRequest.setKey(account.getKey());
            accountRequest.setAvatarName(account.getAvatarName());
            accountRequest.setPhoneNumber(account.getPhoneNumber());

            return accountRequest;
        });

        return accountPage;
    }

    @PostMapping("/accounts/import")
    @ResponseBody
    public Response<AccountRequest[]> importAccount(@RequestBody AccountRequest[] accountRequests) {
        return accountService.importListAccount(accountRequests);
    }
}
