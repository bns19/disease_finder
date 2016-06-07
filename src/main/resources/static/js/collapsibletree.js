function createTree(selectedname, selected) {

    getParentData(selected);

    //console.log("SELECTED: " + selected)
    for (item in selected){
        console.log(selected[item])
    }

        //Global variables
    var oldsymptomItems = JSON.parse(localStorage.getItem('symptomsArray')) || [];

    var mlist = new Array();
    var parentlist = new Array();

    createSecondTree(selectedname)

    //Data has the id's that are searched for from symptoms
    function createSecondTree(data) {

        var url = "secondTreeBuilder";

        // Call the connector and ask for the nodes information (id, parent, children, name)
        $.get(url, {id: data}, function (jsonout) {
            executeTree(jsonout);
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

    //Get all the children of the input node
    function executeTree(jsonout) {
        for (x in jsonout) {
            var loop = jsonout[x];

            //Update the mlist with all the child node objects
            for (firstsearch in loop) {
                mlist.push(loop[firstsearch]);
            }
        }
        ctree(mlist);
    }

    //Create the tree structure
    function ctree(input) {
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

        function addRootObject() {
            //Create the root object
            var rootobject = new Object();
            rootobject['name'] = selectedname;
            rootobject['id'] = "000001";
            rootobject['parent'] = null;

            //Push root object to the input list
            input.push(rootobject)
        }

        //function addRootObject() {
        //    //Create the root object
        //    var rootobject = new Object();
        //
        //    for (symptomobject in oldsymptomItems){
        //        var selectedids = oldsymptomItems[symptomobject]
        //
        //
        //        for (objects in selectedids){
        //
        //            rootobject['name'] = selectedids[objects].name;
        //            rootobject['id'] = selectedids[objects].id;
        //            if (selectedids[objects].parent )
        //                rootobject['parent'] = selectedids[objects].parent;
        //            else{
        //                rootobject['parent'] = null;
        //
        //            }
        //
        //            //Push root object to the input list
        //            input.push(rootobject)
        //        }
        //
        //
        //    }
        //}

        console.log("input: " + input)
        for (inp in input){
            console.log(input[inp])
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

    //Connect to the connector
    function getParentData(selected){

        var url = "parentTreeBuilder";

        var selected = selected.toString();

        console.log("SELECTED: " + selected)

        // Call the connector and ask for the nodes information (id, parent, children, name)
        $.get(url, data: selected, function (jsonout) {
            executegetparentdata(jsonout);
        });

    }
    //Get all the parent information
    function executegetparentdata(jsonout) {
        for (x in jsonout) {
            var loop = jsonout[x];

            //Update the mlist with all the child node objects
            for (firstsearch in loop) {
                parentlist.push(loop[firstsearch]);
            }
        }
        ctree(mlist);
    }
}




