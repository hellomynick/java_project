$(document).ready(function () {
    $.ajax({
        url: "/accounts/page",
        data: {pageNumber: 0, pageSize: 5},
        success: function (response) {
            renderTable(response)
        }
    })
})

function renderTable(accountPage) {
    let accounts = accountPage.content;

    renderPagination(accountPage.first, accountPage.last, accountPage.pageable.pageNumber, accountPage.totalPages);

    let tbody = $('#account-table tbody');
    tbody.empty();
    accounts.forEach(function (item, index) {
        let styleRow = index % 2 === 0 ? "background-color:gainsboro" : "background-color:ghostwhite";

        let row = '<tr id="account-table-tr" style="' + styleRow + '">' +
            '<td class="td-row">' +
            '<img alt="logo" style="width: 30px;height: 30px" src="/images/edit-pencil.svg">' +
            '</td>' +
            '<td class="td-row">' + item.key + '</td>' +
            '<td class="td-row">' + item.userName + '</td>' +
            '<td class="td-row">' + item.active + '</td>' +
            '<td class="td-row">' + item.email + '</td>' +
            '<td class="td-row">' + item.phoneNumber + '</td>' +
            '<td class="td-row">' + item.dateCreate + '</td>' +
            '<td class="td-row">' + item.createBy + '</td>' +
            '<td class="td-row">' + item.dateUpdate + '</td>' +
            '<td class="td-row">' + item.updateBy + '</td>' +
            '</tr>';

        tbody.append(row);
    })
}

function renderPagination(first, last, pageNumber, pageTotal) {
    first ? document.getElementById("previous").style.display = 'none' : document.getElementById("previous").style.display = 'block';

    last ? document.getElementById("next").style.display = 'none' : document.getElementById("next").style.display = 'block';

    document.getElementById("pageIndex").textContent = pageNumber;
    document.getElementById("pageTotal").textContent = pageTotal;
}

function pagingAction(number) {
    let pageNumber = document.getElementById("pageIndex").textContent;

    $.ajax({
        url: "/accounts/page",
        data: {pageNumber: parseInt(pageNumber, 10) + number, pageSize: 5},
        success: function (response) {
            renderTable(response);
        }
    })
}

function searchAccount() {
    let userName = document.getElementById("accountSearch").value;
    let url = userName ? `/accounts/page/${encodeURIComponent(userName)}` : "/accounts/page";
    $.ajax({
        url: url,
        data: {pageNumber: 0, pageSize: 5},
        success: function (response) {
            renderTable(response);
        }
    })
}