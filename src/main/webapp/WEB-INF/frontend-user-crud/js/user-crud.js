$(document).ready(function() {

    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');        
    });
  });


function onSuccess(response) {

    var id = response.id;
    window.location.href = "home..html#" + id;

}


function errorCallback(request, status, error) {

    alert("FUCK OFF AND MAKE AN ACCOUNT")

}

$(function () {
    $("#login-button").click(function(event) {
        event.preventDefault();
        $.ajax({
            url: "http://192.168.2.24:8080/BlackPennies/user/authenticate",
            type: "POST",
            data: JSON.stringify({
                nickname: $("#defaultForm-nickname").val(),
                password: $("#defaultForm-pass").val(),
            }),
            async: true,
            contentType: "application/json; charset=utf-8",
            success: onSuccess,
            error: errorCallback

        });
    })
});