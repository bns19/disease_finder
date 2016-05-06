/**
 * Created by hjdupon on 26-4-16.
 */
function executeLoadHistory(){
    console.log("HOI")
    var controller = "/history";

    $.get(controller, null, function (history){
        console.log("print1")
        //console.log(history.searchHistoryRepos)
        console.log("print2")
    })




}

