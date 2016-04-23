//by mkslofstra
$(document).ready(initialize);
function initialize() {
    $(".examplePic").click(function() {
        //toggle the class so the css will make the pic bigger
        $(this).toggleClass("bigger");
    });
    $('[data-toggle="tooltip"]').tooltip();
}