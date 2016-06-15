function createTree(nodeId, parents, lengthparents) {
    //clear the subtree that is currently shown on the html page
    //$("#subtree").empty();

    //Global variables
    var mlist = new Array();
    var parentIds = parents;


    getNodeInformation(parents)


    //Create the tree structure
    function ctree(nodes) {

        var map = {}, node, roots = [];
        for (var i = 0; i < nodes.length; i += 1) {
            node = nodes[i];
            node.children = [];
            map[node.id] = i; // use map to look-up the parents
            if (node.parent !== "HP:0000001") {

                console.log(nodes[map[node.parent]].children)

                nodes[map[node.parent]].children.push(node);
            } else {
                roots.push(node);
            }
        }
        console.log(roots);
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

                    newObject.parent = parentIds[count + 1];
                    newObject.id = objectsInOutdata[parents].id;
                    newObject.name = objectsInOutdata[parents].name;
                    newObject.children = null;

                    console.log(newObject)
                }

                listOfNodes.push(newObject)
                count += 1;
            }
        }

        ctree(listOfNodes)
    }
}




