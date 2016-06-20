function validateLoginForm() {
    var username = document.forms["loginform"]["username"].value;
    var password = document.forms["loginform"]["password"].value;

    if (username == null || username == "") {
        document.getElementById('errors').innerHTML = "*Please enter a username*";
        return false;
    }
    if (password == null || password == "") {
        alert("password must be filled out");
        return false;
    }
}

function validateRegisterForm() {
    var username = document.forms["registerform"]["username"].value;
    var password = document.forms["registerform"]["password"].value;
    var confirmPassword = document.forms["registerform"]["confirmPassword"].value;
    var email = document.forms["registerform"]["email"].value;

    var booleanCheckPasswords = checkPasswords(confirmPassword, password)
    var booleanCheckifNull = checkifNull(confirmPassword, password)

    if (booleanCheckPasswords == false) {
        return false;
    }
    if (booleanCheckifNull == false) {
        return false;
    }
    else {
        return true;
    }

}


function checkifNull() {

    if (username == null || username == "") {
        alert("username must be filled out");
        return false;
    }
    if (password == null || password == "") {
        alert("password must be filled out");
        return false;
    }
    if (confirmPassword == null || confirmPassword == "") {
        alert("confirm password must be filled out");
        return false;
    }
    if (email == null || email == "") {
        alert("email must be filled out");
        return false;
    }
    else {
        return true;
    }

}


function checkPasswords(confirmPassword, password) {
    if (password != confirmPassword) {
        alert("passwords do not match");
        $('form[name=registerform]').submit(function (event) {
            event.preventDefault();
            //add stuff here
        });
        return false;
    }
    if (password == confirmPassword) {

        return true;
    }
}


