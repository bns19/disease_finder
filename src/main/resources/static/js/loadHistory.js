/**
 * Created by hjdupon on 26-4-16.
 */
function executeLoadHistory(){
    console.log("HOI")
    var controller = "/history";

    $.get(controller, function (history){
        console.log(history.searchedsymptoms)
        console.log("print")
    })




}