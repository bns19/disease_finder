/**
 * Created by hjdupon on 15-3-16.
 */

function Validate() {
    clearErrors();
    var password = document.getElementById("passwordError").value;
    var confirmPassword = document.getElementById("txtConfirmPassword").value;
    if (password != confirmPassword) {
        //alert("Passwords do not match.");
        document.getElementById("passwordError").innerHTML = "Passwords do not match";
        document.getElementById("passwordError").style.color = "red";

        return false;
    }
    return true;
}

function Handleinput() {
    clearErrors();
    var inputemail = document.getElementById("emailError").value;
    var inputUser = document.getElementById("usernameError").value;
    var inputpw = document.getElementById("passwordError").value;

    if (inputemail == null || inputemail == "") {
        console.log(inputemail);
        document.getElementById("emailError").innerHTML = "please fill in your email";
        document.getElementById("emailError").style.color = "red";

    }

    if (inputUser == null || inputUser == "") {
        console.log(inputUsername);
        document.getElementById("usernameError").innerHTML = "please fill in your username";
        document.getElementById("usernameError").style.color = "red";

    }

    if (inputpw == null || inputpw == "") {
        //console.log(inputpw);
        document.getElementById("passwordError").innerHTML = "please fill in your password";
        document.getElementById("passwordError").style.color = "red";

    }


}

function clearErrors() {
    Array.prototype.forEach.call(
        document.getElementsByClassName("errmsg"),
        function (el) {
            el.style.display = "none";
        }
    );
}