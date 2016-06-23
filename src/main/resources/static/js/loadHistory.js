/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by hjdupon & bnsikkema
 */

// Loads the history of the user by getting it from the controller
function executeLoadHistory(){
    var controller = "/history";

    $.get(controller, null, function (history){
        returnHistory(history);
    });

    // Return the history
    function returnHistory(history) {
        if (history.length < 1) {
            //return "No former searches";
            $("#historyContent").html("No former searches");
        }
        else {
            var processedHistory = processHistory(history);
            $("#historyContent").html(processedHistory);
        }
    }


    // Present the history back to the user from a Json array.
    function processHistory(userSearchHistory) {
        var processedHistory = [];
        var dates = [];
        var queries = [];
        var terms = []
        var historyString = ""

        for (var i = 0; userSearchHistory.length > i; i++) {
            var historyObj = userSearchHistory[i];
            historyString+="search terms:  "
            historyString+=historyObj['query'];
            historyString+="\n";
            historyString+="searchdate:  "
            historyString+=historyObj['createdAt'];
            historyString+='\n';
            historyString+='\n';
        }

        var historyStringProcessed =  historyString.replace(',','\n');

        processedHistory.push(queries);
        processedHistory.push(dates);

        // return processedHistory;
        return historyStringProcessed;
    }
}

