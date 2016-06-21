
function checkPasswords() {
    var password = document.forms["registerform"]["password"].value;
    var confirmPassword = document.forms["registerform"]["confirmPassword"].value;

    document.getElementById("registerform").disabled = true;

        if (password != confirmPassword) {
            document.getElementById('password').style.borderColor = "red";
            document.getElementById('confirmPassword').style.borderColor = "red";
            document.getElementById("registerform").disabled = true;

        }
        if (password == confirmPassword) {
            document.getElementById('password').style.borderColor = "green";
            document.getElementById('confirmPassword').style.borderColor = "green";
            document.getElementById("registerform").disabled = false;
        }
}


function process(userdata) {
    //console.log(userdata)
    //if (userdata == "False") {
    //    booleanRegistered = false;
    //}
    //else {
    //    alert("Username already in use");
    //    booleanRegistered = true;
    //}
}


function getRegistered() {

    var username = document.forms["registerform"]["username"].value;
    console.log(username)
    var url = "/getRegisteredUsers";
    $.get(url, {"username": username}, function (userdata) {
        console.log(userdata)
        process(userdata);
    });
}





