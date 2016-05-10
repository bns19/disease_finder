/**
 * Created by hjdupon on 26-4-16.
 */
function executeLoadHistory(){
    console.log("HOI")
    var controller = "/history";
    var userHistory;
    $.get(controller, null, function (history){
        console.log("print1")
        console.log(history)


    })

    //if (history.length != 0) {
    //    for (searchHistory in history) {
    //        console.log(searchHistory)
    //    }
    //}




}

