//Created the secondary tree after it a node is selected in get Symptoms
function createSecondaryTree(selected, parents) {

    // Clear the subtree that is currently shown on the html page
    $("#subtree").empty();

    // Get the information of all the parent nodes
    getNodeInformation(selected)

    // Create the tree structure in a flat dictionary
    function createTree(arr) {
        var tree = [],
            mappedArr = {},
            arrElem,
            mappedElem;

        // First map the nodes of the array to an object -> create a hash table.
        for (var i = 0, len = arr.length; i < len; i++) {
            arrElem = arr[i];
            mappedArr[arrElem.id] = arrElem;
            mappedArr[arrElem.id]['children'] = [];
        }

        for (var id in mappedArr) {
            if (mappedArr.hasOwnProperty(id)) {
                mappedElem = mappedArr[id];
                // If the element is not at the root level, add it to its parent array of children.
                if (mappedElem.parentid) {
                    mappedArr[mappedElem['parentid']]['children'].push(mappedElem);
                }
                // If the element is at the root level, add it to first level elements array.
                else {
                    tree.push(mappedElem);
                }
            }
        }

        // Execute the d3.js tree with the created Object of flat nodes.
        executeCreateTree(tree);
    }


    // Connect with the connector and get the HPO file information per parent (id and name)
    function getNodeInformation(parents) {
        var url = "/parentTreeBuilder";
        $.get(url, {"ids": parents.toString()}, function (outdata) {
            processInformation(parents, outdata)
        });
    }


    // Get the parent from every child node
    function processInformation(parents, outdata) {
        var listOfNodes = new Array();

        for (greatList in outdata) {
            var objectsInOutdata = outdata[greatList];

            for (parents in objectsInOutdata) {

                var newObject = new Object;

                // Set the parent to null when you got the root node
                if (objectsInOutdata[parents].name == "All") {
                    newObject.id = objectsInOutdata[parents].id;
                    newObject.name = objectsInOutdata[parents].name;
                    newObject.children = null;
                    newObject.parentid = null;

                    listOfNodes.push(newObject)
                }

                else {
                    if (objectsInOutdata[parents].parentid != "#") {
                        newObject.parentid = objectsInOutdata[parents].parentid;
                    }
                    else {
                        newObject.parentid = null;
                    }

                    newObject.id = objectsInOutdata[parents].id;
                    newObject.name = objectsInOutdata[parents].name;
                    newObject.children = null;

                    listOfNodes.push(newObject)
                }
            }
        }

        // Create the tree with the list of nodes
        createTree(listOfNodes)
    }
}


