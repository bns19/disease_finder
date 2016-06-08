// made by aroeters
$(document).ready(initialize);

function initialize() {

    $("#symptoms").on("click", function() {
        $(this).val("");
    });


    $("#symptoms").autocomplete({

    width: 150,
        max: 10,
        minLength: 1,
        autoFocus: true,
        cacheLength: 1,
        scroll: true,
        highlight: true,


        //Hier word de data uit de hpoprocessor opgehaald
        source: function(request, response) {

            $.ajax({
                url: "/autocompleteSymptoms",
                type: "GET",
                dataType: "json",
                data: request,
                success: function(data) {

                    var items = data;
                    response(items);
                }
            });
        },

        // ui = object being searched
        select: function(e, ui) {

            // Selected item value = ui.item.value
            $.ajax({
                url: "termsToTree",
                dataType: "text",
                data: {"autoCompleteResult": ui.item.value},
                success: function(data) {
                    var selectedname = ui.item.value;

                    data = data.replace(/\[|\]/g, "");

                    var newData = data.replace(/\"|\n/g, "").split(",").reverse();
                    var count = newData.length;

                    window.setInterval(function() {
                        $("#ontology-tree").jstree("open_node", newData[0]);

                        if (count === 1) {

                            //Create the secondary tree in the right field
                            //createTree(newData[0], selectedname);
                            //console.log(newData[0])

                            // to highligt the symptom that is searched for
                            $("#ontology-tree").jstree(true).get_node(newData[0]).li_attr.class = "jstree-search"


                            window.clearInterval();
                        }
                        newData.splice(0, 1);
                        count--;
                    }, 500);
                    $("#search-symptom").on("click", function() {
                        $("*").removeClass("jstree-search");
                    });

                }
            });
        }
    });
}


