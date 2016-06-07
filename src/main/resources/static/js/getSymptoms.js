/**
 * Created by mkslofstra and aroeters, addditions and adjustments by bnsikkema
 */


$(document).ready(initialize);
function initialize() {
    var symptoms;

    localStorage.setItem("ids", "");
    //by aroeters (lists made by mkslofstra)
    $("#ontology-tree").on('changed.jstree', function (e, data) {
        localStorage.setItem("shortSymptoms", "")
        var i, j, selectedNodes = [], selectedIds = [];
        var shortSymptomsList = []
        //run through all selected nodes
        for (i = 0, j = data.selected.length; i < j; i++) {
            //get the selected node

            var selected = data.instance.get_node(data.selected[i]);

            if ($.inArray(selected.id, selectedNodes) === -1 && selected.text !== "All") {
                shortSymptomsList.push(selected.text)
                selectedNodes.push(selected.text);
                selectedIds.push(selected.id);
            }
            //get all parents
            parents = selected.parents;
            //The if makes sure that the parents are more than one, so if it
            //is one word, it will not be divided in characters
            if (parents.length !== 1) {
                //check for each parent, if the parent is in the array of
                //selected nodes
                $.each(parents, function (index, value) {
                        if ($.inArray(value, selectedNodes) === -1 && value !== "#") {
                            var thisNode = $("#ontology-tree").jstree("get_node", value);
                            if ($.inArray(thisNode.text, selectedNodes) === -1 && thisNode.text !== "All") {
                                selectedNodes.push(thisNode.text);
                                selectedIds.push(value);
                            }
                        }
                    }
                );
            } else {
                //checks if the parent is #, this should not be added to the list.
                if (parents[0] !== "#") {
                    selectedNodes.push(parents[0]);
                }
                ;
            }
            localStorage.setItem("symptoms", selectedNodes);
            localStorage.setItem("selectedIds", selectedIds);
        }
        var shortSymptomString = shortSymptomsList.toString();
        localStorage.setItem("shortSymptoms", shortSymptomString)

        //Here will be the link between the old tree and the new tree.

        // by mkslofstra make buttons of the selected symptoms which on click deselect the symptoms
        $('#event_result').html('Selected symptoms:<br/>');

        var shortSymptomCounter = -1;
        for (i = 0; i < selectedIds.length; i++) {

            var this_node = $("#ontology-tree").jstree("get_node", selectedIds[i]);
            var icon = this_node.icon;
            if ($.inArray(selectedNodes[i],shortSymptomsList) >-1) {
                shortSymptomCounter++;
                var negaButtonValue = i;
                $("#event_result").append("<button class=\"btn btn-default dontClick\"data-close=\"" + selectedIds[i] + "\"> <img alt=\"" + icon + "\" src=\"" + icon + "\"> "
                    + selectedNodes[i] + " <span class=\"closeSymptom\"> X </span></button><button class='negateButton' id='negateButton' value="+shortSymptomCounter+" >negate</button>");

            }

            else{
                $("#event_result").append("<button class=\"btn btn-default dontClick\"data-close=\"" + selectedIds[i] + "\"> <img alt=\"" + icon + "\" src=\"" + icon + "\"> "
                    + selectedNodes[i] + " <span class=\"closeSymptom\"> X </span></button>");
            }
        }
        $(".dontClick").click(function () {
            $("#ontology-tree").jstree("deselect_node", $(this).data("close"));
        });

        $(".negateButton").click(function () {

            var index = $(this).attr("value");

            var shortSymptomList = localStorage.getItem("shortSymptoms").split(",");
            var longSymptomList = localStorage.getItem("symptoms").split(",");
            var symptomToNegate = shortSymptomList[index];

            var longIndex = longSymptomList.indexOf(symptomToNegate);

            if (symptomToNegate.substring(0,3) != "non") {
                var negatedSymptom = "non"+symptomToNegate;
                shortSymptomList[index]=negatedSymptom;
                localStorage.removeItem("shortSymptoms");
                localStorage.setItem("shortSymptoms", shortSymptomList.toString())


                //longSymptomList[longIndex]=negatedSymptom;
                //localStorage.removeItem("symptoms");
                //localStorage.setItem("symptoms", longSymptomList.toString())
            }

            if (symptomToNegate.substring(0,3) == "non" && symptomToNegate.substring(3,5) != "non") {
                var nonNegatedSymptom = symptomToNegate.substring(3,symptomToNegate.length);
                shortSymptomList[index]=nonNegatedSymptom;
                localStorage.removeItem("shortSymptoms");
                localStorage.setItem("shortSymptoms", shortSymptomList.toString())

                //longSymptomList[longIndex]=nonNegatedSymptom;
                //localStorage.removeItem("symptoms");
                //localStorage.setItem("symptoms", longSymptomList.toString())
            }
        });


        //make sure that when a node opens, it is not selected (otherwise the children will not be loaded in the search)
        $("#ontology-tree").on('open_node.jstree', function (e, data) {
            var node = data.node;
            if ($("#ontology-tree").jstree("is_checked", node)) {
                var children = node.children;
                $("#ontology-tree").jstree("check_node", node);
                $.each(children, function (index, child) {
                    $("#ontology-tree").jstree("deselect_node", child);
                });

            }
        });
    });

    $("#search-button").click(function () {
        sendSymptoms();
    });

}



function resendQuery(longQuery) {
    localStorage.setItem("symptoms", longQuery)
    sendSymptoms()
}

//by mkslofstra and bnsikkema: this function will send data to the servlet and get diseases back
function sendSymptoms(symptoms) {
    //localStorage.setItem("symptoms", symptoms);
    var symptomSet = symptoms;

    var algorithm = document.getElementById('algorithmType').value;
    var runtime = document.getElementById('runtime').value;

    $('.nav-tabs a[href="#resultTab"]').tab('show');

    //HTMLpage that the spring controller has mapped.
    var controller = "/sendSymptoms";
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    //use the mapped controller
    $.post(controller, {"symptoms": localStorage.getItem("symptoms"), "shortSymptoms": localStorage.getItem("shortSymptoms"), "algorithm": algorithm,"runtime": runtime, _csrf: token}, function (diseases) {
        var diseaseSummary = "";

        $("#resultTab").text("");
        $("#resultTab").append("<br/><br/><ul>");
        // $("#resultTab").append(diseases);

        for (var disease in diseases) {
            var values = diseases[disease];
            // var matches = getMatches(values[2]);
            diseaseSummary = "<li class =\"disease\">"
                + "<table>"
                + "<tr class=\"diseaseTitle\">"
                + "<td class=\"title\" colspan=\"3\">"
                + "<a class = \"clickTitle\" id=\""
                + values[1]
                + "\"><b>"
                + values[0]
                + "</a></td></tr>"
                + "<tr>\n"
                + "<td class=\"label\">Omimnumber: </td><td class=\"value\">"
                + values[1]
                + "</td></tr>"
                + "<tr><td class=\"label\"><a data"
                + "-toggle=\"tooltip\" title=\"The score is calculated through:"
                + " The sum of 1 / occurence of each match through the "
                + "search.\" id=\"score\"data-placement=\"right\">"
                + "Score: </a></td><td class=\"value\">"
                + "score"
                + "<tr><td class=\"label\"><a data"
                + "-toggle=\"tooltip\" title=\"The number of matched "
                + "symptoms.\"data-placement=\"right\">Hits: "
                + "</a></td><td class=\"value\">"
                + "hits"
                + "</td></tr>"
                + "<tr><td class=\"label\">Matches: "
                + "</td><td class=\"value\">"
                + values[2]
                + "</td></tr>"
                + "</table>"
                + "</li><br/>"

            $("#resultTab").append(diseaseSummary);
        }

        $("#resultTab").append("</ul>");
        $("#resultTab").append("<button id = \"save\" class=\"btn btn-default\">Save this result as .txt</button>");
        $("#resultTab").append("<button id = \"savePdf\" class=\"btn btn-default\">Save this result as .pdf</button>");
        $("body").tooltip({selector: '[data-toggle=tooltip]'});
        $(".clickTitle").click(function () {
            localStorage.setItem("omimNumber", $(this).attr("id"));
            loadDisease();
        });
        $("#save").click(function () {
            saveResults();
        });
        $("#savePdf").click(function () {
            saveResultsAsPdf();
        });
    });
}



//by mkslofstra and bnsikkema
function loadDisease() {
    var diseaseServlet = "/diseaseInformation";
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.post(diseaseServlet, {
        "omimNumber": localStorage.getItem("omimNumber"),
        "symptoms": localStorage.getItem("symptoms"),
        _csrf: token
    }, function (disease) {
        console.log(disease["title"] + "de titel")
        var title = disease["title"];

        //var pattern = /<h2>([\w 1234567890,;.-]+)<\/h2>/;
        //var pattern = /<h2>([\w 1234567890,;.-]+)<\/h2>/;
        //var title = disease.match(pattern)[1];
        ////sommige ziekten worden niet gematched
        var id = title.replace(/[ ,;.-]* /g, "");
        var idPat = new RegExp(id);

        /////////////////////////////////////////////////////////////
        var matchId = localStorage.getItem("ids").match(idPat);
        console.log(matchId)
        //make sure, the tab is only created one time
        if (matchId === null) {
            localStorage.setItem("ids", localStorage.getItem("ids") + id);
            title = title.charAt(0) + title.substring(1, title.length).toLowerCase();
            if (title.length > 15) {
                title = title.substring(0, 12) + "...";
            }

            $("#tablist").append("<li role=\"presentation\"><a href=\"#" + id + "\" aria-controls=\"" + id
                + "\" role=\"tab\" data-toggle=\"tab\" id=\"" + id + "Tab\" class=\"tab\">" + title + " <button class=\"closeDiseaseTab\" data-close=\"" + id + "\">X</button></a></li>");
            $("#tabcontent").append("<div role=\"tabpanel\" class=\"tab-pane\" id=\"" + id + "\"></div>");
            $("#" + id).append("<br/><br/>");
            //$("#" + id).append(disease);

            $("#" + id).append("<h2>" + disease["title"] + "</h2><div id =\"disease\">"
                + "<p class=\"back2results\"><span class = \"glyphicon"
                + " glyphicon-arrow-left\" aria-hidden=\"true\">"
                + "  Back to results</p></span><br/>"
                + "<b>Omim number : </b>"
                + "<a href=\"http://omim.org/entry/" +
                disease["omimNumber"] +
                + "\"target=\"blank\"data-toggle=\"tooltip\""
                + " title=\"Click here to open the disease on the omim website\"id=\"omimSiteLink\">" +
                disease["omimNumber"] +
                "</a>"
                + "<br/><b>Matches: </b>" +
                disease["matches"] + "<br/>"
                + "<b><a data"
                + "-toggle=\"tooltip\" title=\"The number of matched "
                + "symptoms.\"data-placement=\"right\">Hits :</a></b> "
                + "hits" + "<br/><b><a data"
                + "-toggle=\"tooltip\" title=\"The score is calculated through:"
                + " The sum of 1 / occurence of each match through the search"
                + ".\"data-placement=\"right\">Score: </a></b>"
                + score + "<br/><br/><button id=\"highlightButton\""
                + " class=\"button btn btn-info\">"
                + "Highlight matches</button>" +
                disease["information"] +
                "</div>");


            $("#" + id).append("<br/><button class = \"saveDisease btn btn-default\" data-disease_id = \"" + id + "\">Save this disease as .txt</button>");
        }






        $(".closeDiseaseTab").click(function () {
            // matchId = localStorage.getItem("ids").match(idPat);
            var close_id = $(this).data("close");
            var elem = document.getElementById(close_id);
            if (elem !== null) {
                elem.parentNode.removeChild(elem);
                var tab = document.getElementById(close_id + "Tab");
                tab.parentNode.removeChild(tab);
                $('.nav-tabs a[href="#resultTab"]').tab('show');

                //////////////////////////////////////////////////////////
                // remove string from id list, so that it can be opened again
                var idString = localStorage.getItem("ids");
                var firstPart = idString.substring(0, matchId.index);
                var lastPart = idString.substring(matchId.index + id.length, idString.length);
                localStorage.setItem("ids", firstPart + lastPart);
                //////////////////////////////////////////////////////////////

            }
            // }
        });


        $(".saveDisease").click(function () {
            localStorage.setItem("disease2save", $(this).data("disease_id"));
            saveDisease();
        });
        //go to the disease tab
        $('.nav-tabs a[href="#' + id + '"]').tab('show');
        //set the bootstrap styling on the tooltip
        $("body").tooltip({selector: '[data-toggle=tooltip]'});
        $("#highlightButton").click(function () {
            $(".highlight").toggleClass("highlighted");
        });
        //load results again
        $(".back2results").click(function () {
            $('.nav-tabs a[href="#resultTab"]').tab('show');
        });

    });
}
;
//by mkslofstra
function saveResults() {
    var results = $("#resultTab").text();
    //for nice formating of the results
    var pattern = /\d[H|M|S]/g;
    var last = 0;
    var myArray;
    var print = "";
    while ((myArray = pattern.exec(results)) !== null) {
        print += results.substring(last, pattern.lastIndex - 1);
        print += ";";
        var last = pattern.lastIndex - 1;

    }
    //add last matches, without the save button
    print += results.substring(last, results.length - 16);
    //remove the semicolons in the title, this can cause problems in the csv format
    var result = print.replace(/;/g, "");
    //make a csv format table (labels are not needed)
    result = result.replace(/\nOmimnumber: /g, ";");
    result = result.replace(/Score: /g, ";");
    result = result.replace(/Hits: /g, ";");
    result = result.replace(/Matches: /g, ";");
    //make the blob with the content of the csv file
    var resultFile = new Blob(["Title;OmimNumber;Score;Hits;Matches\n" + result], {type: "text/plain;charset=utf-8"});
    //save the file
    saveAs(resultFile, "results.csv");
}
;


//under construction
function saveResultsAsPdf() {
    console.log("hoipdf")
    //var results = $("#resultTab").text();
    //var doc = new jsPDF();
    //var specialElementHandlers = {
    //    '#editor': function (element, renderer) {
    //        return true;
    //    }}
    //doc.fromHTML($('#resultTab').html(), 15, 15, {
    //    'width': 170,
    //    'elementHandlers': specialElementHandlers
    //});
};

//by mkslofstra
function saveDisease() {
    var disease = $("#" + localStorage.getItem("disease2save"));
    var disease_info = disease.html();
    //delete all the html code (html instead of text is needed to make \n where needed)
    disease_info = disease_info.replace(/<div id="disease"><p class="back2results"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true">  Back to results/, "");
    disease_info = disease_info.replace(/<button id="highlightButton" class="button btn btn-info">Highlight matches/, "");
    disease_info = disease_info.replace(/\<br\>/g, "\n");
    disease_info = disease_info.replace(/\<[\w0-9]+\>/g, "");
    disease_info = disease_info.replace(/<a data-toggle="tooltip" title="The number of matched symptoms." data-placement="right">/g, "");
    disease_info = disease_info.replace(/<a data-toggle="tooltip" title="The score is calculated through: The sum of 1 \/ occurence of each match through the search." data-placement="right">/g, "");
    disease_info = disease_info.replace(/\<[\/\w0-9]+\>/g, "");
    disease_info = disease_info.replace(/<a href="http:\/\/omim.org\/entry\/\w{6}" target="blank" data-toggle="tooltip" title="Click here to open the disease on the omim website" id="omimSiteLink">/g, "");
    //for nice formating of the score etc.
    var pattern = /(\d[H|M|S])|]H/g;
    var last = 2;
    var myArray;
    var print = "";
    while ((myArray = pattern.exec(disease_info)) !== null) {
        print += disease_info.substring(last, pattern.lastIndex - 1);
        print += "\n";
        var last = pattern.lastIndex - 1;
    }
    //ignore the last button
    print += disease_info.substring(last, disease_info.length - 80 - localStorage.getItem("disease2save").length);
    //the file saved in a blob object
    var diseaseFile = new Blob([print], {type: "text/plain;charset=utf-8"});
    //save the file with its id as name in a txt file
    saveAs(diseaseFile, localStorage.getItem("disease2save") + ".txt");

}
