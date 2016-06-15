function createTree(nodeId, parents, lengthparents) {
    //clear the subtree that is currently shown on the html page
    $("#subtree").empty();

    //Global variables
    var mlist = new Array();
    var parentIds = parents;


    getNodeInformation(parents)


    //Create the tree structure
    function createTree(arr) {
        var tree = [],
            mappedArr = {},
            arrElem,
            mappedElem;

        // First map the nodes of the array to an object -> create a hash table.
        for(var i = 0, len = arr.length; i < len; i++) {
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

        console.log(tree)

        executeCreateTree(tree);
    }


    function getNodeInformation(parents) {
        var url = "/parentTreeBuilder";
        $.get(url, {"ids": parents.toString()}, function(outdata) {
            processInformation(parents, outdata)
        });
    }


    function processInformation(parents, outdata){
        var count = 0;
        var listOfNodes = new Array();

        for( greatList in outdata){
            var objectsInOutdata = outdata[greatList];

            for (parents in objectsInOutdata) {

                var newObject = new Object;

                if (count != lengthparents) {

                    if (parentIds[count + 1] != "#") {
                        newObject.parentid = parentIds[count + 1];

                    }
                    else{
                        newObject.parentid = null;
                    }
                }
                newObject.id = objectsInOutdata[parents].id;
                newObject.name = objectsInOutdata[parents].name;
                newObject.children = null;
                console.log(newObject)

                listOfNodes.push(newObject)


                count += 1;
            }
        }

        createTree(listOfNodes)
    }
}


