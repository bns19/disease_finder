//by mkslofstra
//by resizing of the page, this script could cause errors, but that does not affect the application
//a refresh will remove the error
$(document).ready(initialize);

function initialize() {
    function autoResizeDiv(search)
    {
        document.getElementById(search).style.height = window.innerHeight + 'px';
    }
    window.onresize = autoResizeDiv;
    autoResizeDiv('search');
    autoResizeDiv('result');
}
