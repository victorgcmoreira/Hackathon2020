/*function load(){// perform an ajax http post request
$.ajax({
    url: 'http://localhost:8080/javabank5/api/customer',
    type: 'POST',
    data: JSON.stringify({
        firstName: 'ruizinho',
        lastName: 'ferraozinho',
        email: 'ruizinho.ferraozinho@academiadecodigo.org',
        phone: '912345678'
    }),
    async: true,
    contentType: 'application/json',
    success: postData,
    error: errorCallback
});

}

load(); 
*/
function onSucess(){
    
}

function errorCallback(request, status, error) {
    console.log(error);
}


$(document).ready(function() {
    start();
});


function successCallback(response) {

    console.log(response    );
    var customerTable = $('#customers-table');

    response.forEach(function(elem){
        var rowValue = "<tr><td>" + elem.nickname + "</td>" + 
        "<td>" + elem.password + "</td>" + 
        "<td>" + elem.email + "</td>" + 
        "<td>" + elem.balance + "</td></tr>"

        $(rowValue).appendTo(customerTable);
    })
}




function start(){

    var uName = "ruizinho";
    var passwrd = "ferraozinho"

$.ajax({
    url: 'http://192.168.2.24:8080/BlackPennies/user/authenticate',
    type: 'POST',
    data: JSON.stringify({
        nickname: 'ruizinho',
        password: btoa('ferraozinho'),
    }),
    async: true,
    contentType: 'application/json',
    success: successCallback,
    error: errorCallback
});




}