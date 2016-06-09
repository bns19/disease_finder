function createTree(nodeId, nodeName){

    //Global variables
    var mlist = new Array();
    var url = "secondTreeBuilder";

    //clear the subtree that is currently shown on the html page
    $("#subtree").empty();

    //createSecondTree(nodeId)

    console.log("nodeId: " + nodeId)
    console.log("nodeName: " + nodeName)

    //Data has the id's that are searched for from symptoms
    function createSecondTree(data) {

        // Call the connector and ask for the nodes information (id, parent, children, name)
        $.get(url, {id: data}, function (jsonout) {
            for (x in jsonout) {
                var loop = jsonout[x];

                //Update the mlist with all the child node objects
                for (firstsearch in loop) {
                    if ($.inArray(loop[firstsearch], mlist) == -1)
                        mlist.push(loop[firstsearch]);
                }
            }
            ctree(mlist);
        });
    }

    //Unique array maker (used to get the root(s))
    function unique(arr) {
        var u = {}, a = [];
        for (var i = 0, l = arr.length; i < l; ++i) {
            if (!u.hasOwnProperty(arr[i])) {
                a.push(arr[i]);
                u[arr[i]] = 1;
            }
        }
        return a;
    }

    //Create the tree structure
    function ctree(input) {

        console.log(input)

        var uniqueidlist = new Array();
        for (item in input) {
            uniqueidlist.push(input[item]['id'])
        }
        var uniqueid = unique(uniqueidlist);
        for (item in input) {

            if (uniqueid.indexOf(input[item]['parent']) >= 0) {
            }
            else {
                input[item]['parent'] = "000001"; //sets the parentid's to 000001 of the nodes that have no parent
            }
        }

        addRootObject();

        //Create the root object
        function addRootObject() {
            var rootobject = new Object();
            rootobject['name'] = nodeName;
            rootobject['id'] = "000001";
            rootobject['parent'] = null;

            //Push root object to the input list
            input.push(rootobject)
        }

        var arr = input;
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
}




