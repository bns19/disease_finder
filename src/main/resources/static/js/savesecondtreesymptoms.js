/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by Henri du Pon
 */
function savesecondtreesymptoms() {
    var selectedTreeNode = document.getElementById("selectedTreeNode").innerHTML;
            $("#event_result").append("<button class=\"btn btn-default dontClick\"data-close=\"" + selectedTreeNode + "\"> "
                + selectedTreeNode + " <span class=\"closeSymptom\"> x </span></button><button class='negateButton' id='negateButton' value=" + selectedTreeNode + " >negate</button>");

            $(".dontClick").click(function () {
                $("#ontology-tree").jstree("deselect_node", $(this).data("close"));
            });

            $(".negateButton").click(function () {
                //
                var index = $(this).attr("value");
                var shortSymptomList = localStorage.getItem("shortSymptoms").split(",");
                var symptomToNegate = shortSymptomList[index];
                if (symptomToNegate.substring(0, 3) != "non") {
                    var negatedSymptom = "non" + symptomToNegate;
                    shortSymptomList[index] = negatedSymptom;
                    localStorage.removeItem("shortSymptoms");
                    localStorage.setItem("shortSymptoms", shortSymptomList.toString())
                }
                if (symptomToNegate.substring(0, 3) == "non" && symptomToNegate.substring(3, 5) != "non") {
                    var nonNegatedSymptom = symptomToNegate.substring(3, symptomToNegate.length);
                    shortSymptomList[index] = nonNegatedSymptom;
                    localStorage.removeItem("shortSymptoms");
                    localStorage.setItem("shortSymptoms", shortSymptomList.toString())
                }
            });
}


