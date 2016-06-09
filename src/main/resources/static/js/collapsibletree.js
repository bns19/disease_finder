function createTree(nodeName){
    //clear the subtree that is currently shown on the html page
    //$("#subtree").empty();

    //Global variables
    var mlist = new Array();
    var listofallids = new Array();

    getListOfParents(nodeName)

    console.log(listofallids)

    //ctree(nodeName)

    //Create the tree structure
    function ctree(input) {
        addObjects();

        //Create the root object
        function addObjects() {

            for (selectedObjects in nodeName) {
                var object = nodeName[selectedObjects]

                var rootobject = new Object();
                rootobject['name'] = object.text;
                rootobject['id'] = object.id;
                rootobject['parent'] = object.parent;

                //Push root object to the input list
                mlist.push(rootobject)
            }
        }

        var arr = mlist;
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
                if (mappedElem.parent) {
                    mappedArr[mappedElem['parent']]['children'].push(mappedElem);
                }

                // If the element is at the root level, add it to first level elements array.
                else {
                    tree.push(mappedElem);
                }
            }
        }
        executeCreateTree(tree);
    }

    function getListOfParents(nodeName){
        for (selectedObjects in nodeName) {
            var object = nodeName[selectedObjects]
            listofallids.push(object.id)

            for (parentobjects in object.parents){
                listofallids.push(object.parents[parentobjects])
            }
        }
    }
}




