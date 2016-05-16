/**
 * Created by hjdupon on 26-4-16.
 */
function executeLoadHistory(){
    var controller = "/history";

    $.get(controller, null, function (history){
        returnHistory(history);
    });

    function returnHistory(history) {
        if (history.length < 1) {
            //return "No former searches";
            $("#historyContent").html("No former searches");
        }
        else {
            var processedHistory = procesHistory(history);
            //console.log(processedHistory)

            $("#historyContent").html(processedHistory);

        }
    }
    //weh have a json array
    function procesHistory(userSearchHistory) {
       // console.log(userSearchHistory)
        var processedHistory = [];
        var dates = [];
        var queries = [];
        var terms = []
        var historyString = ""

        for (var i = 0; userSearchHistory.length > i; i++) {
            var historyObj = userSearchHistory[i];
            //queries.push(historyObj['query']);
            //dates.push(historyObj['createdAt'])
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

