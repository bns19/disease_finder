/**
 * Created by hjdupon on 15-3-16.
 */
console.log("test 1");

function Validate() {
    var password = document.getElementById("txtPassword").value;
    var confirmPassword = document.getElementById("txtConfirmPassword").value;
    if (password != confirmPassword) {
        alert("Passwords do not match.");

        return false;

    }
    var correctPassword = document.getElementById("txtPassword").value;
    return true;

}