$(document).ready(function jose() {
    $.ajax({
        url: "http://192.168.2.24:8080/BlackPennies/user/" + hash + "/details",
        type: "GET",

        async: true,
        contentType: "application/json; charset=utf-8",
        success: onNice,
        error: errorCallback
    });
});
function onNice(response) {
    let content = '<tr><td>'+ response.nickname + "</td><td>" + response.email + "</td><td>" + response.balance + "</td></tr>"
    $(content).appendTo("#table")

}
function onSuccess(response) {
    alert("Balanced added successfully");
    location.reload();

}

function errorCallback(request, status, xhr) {

}

function balance(){
    $.ajax({
        url: "http://192.168.2.24:8080/BlackPennies/user/" + hash + "/deposit",
        type: "PUT",
        data: JSON.stringify({
            balance: 5
        }),
        async: true,
        contentType: "application/json; charset=utf-8",
        success: onSuccess,
        error: errorCallback
    });
}

var hash = location.hash.substr(1);




