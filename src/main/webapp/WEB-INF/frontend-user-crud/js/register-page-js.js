
function onSuccess(response) {
    window.location.href = "home.html"
}

function errorCallback(request, status, error) {
    alert("Please create a valid user!");
    $("#nickname").val("");
    $("#password").val("");
    $("#email").val("");
}

$(function () {
    $("#button1").click(function(event) {
        event.preventDefault();
        $.ajax({
            url: "http://192.168.2.24:8080/BlackPennies/user/add",
            type: "POST",
            data: JSON.stringify({
                nickname: $("#nickname").val(),
                password: $("#password").val(),
                email: $("#email").val()
            }),
            async: true,
            contentType: "application/json; charset=utf-8",
            success: onSuccess,
            error: errorCallback
        });
    })
});



