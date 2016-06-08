/**
 * Created by hjdupon on 6-6-16.
 */

function clearlocalstorage(){
    console.log("local storage has been cleared")
    localStorage.clear();
}

//function addsymptomsfromsecondtree() {
//    var objectcount = oldsymptomItems.length;
//    var shortSymptomCounter = objectcount + 1;
//    var selectedsymptom = document.getElementById("selectedTreeNode").innerHTML;
//    var symptom = {};
//
//    symptom[selectedsymptom] = selectedsymptom;
//    oldsymptomItems.push(symptom);
//
//    console.log(objectcount)
//    console.log(shortSymptomCounter)
//
//    $("#event_result").append("<button class=\"btn btn-default dontClick\"data-close=\"" + selectedsymptom + "\"> "
//        + selectedsymptom + " <span class=\"closeSymptom\"> X </span></button><button class='negateButton' id='negateButton' value="+shortSymptomCounter+" >negate</button>");
//
//
//    $(".negateButton").click(function () {
//
//        var index = $(this).attr("value");
//
//        var symptomToNegate = oldsymptomItems[index];
//
//        var longIndex = oldsymptomItems.indexOf(symptomToNegate);
//
//        if (symptomToNegate.substring(0,3) != "non") {
//            var negatedSymptom = "non"+symptomToNegate;
//            oldsymptomItems[index]=negatedSymptom;
//            localStorage.removeItem("symptomsArray");
//            localStorage.setItem("symptomsArray", oldsymptomItems.toString())
//
//        }
//
//        if (symptomToNegate.substring(0,3) == "non" && symptomToNegate.substring(3,5) != "non") {
//            var nonNegatedSymptom = symptomToNegate.substring(3,symptomToNegate.length);
//            oldsymptomItems[index]=nonNegatedSymptom;
//            localStorage.removeItem("symptomsArray");
//            localStorage.setItem("symptomsArray", oldsymptomItems.toString())
//
//        }
//    });
//
//    localStorage.setItem('symptomsArray', JSON.stringify(oldsymptomItems));
//    console.log(localStorage.getItem('symptomsArray'))
//
//
//}