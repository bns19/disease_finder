function createTree(selectedname) {

    var seclastitem = selectedname[selectedname.length-2]

    var rightpartoftree = selectedname[0];

    console.log(rightpartoftree)

    var parentdata = new Array();
    var mlist = new Array();

    console.log(selectedname)

    //getParentData(selectedname);

    //Connect to the connector
    function getParentData(seclastitem){

        var url = "parentTreeBuilder";
        var parentslist = new Array();
        var selected = selected.toString();

        // Call the connector and ask for the nodes information (id, parent, children, name)
        $.get(url, {id : selected}, function (json) {
            console.log("jsonout: " + json)
            parentdata.push(json)
        });
    }


    createSecondTree(rightpartoftree)

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


        //for (x in json) {
        //    var loop = json[x];
        //    console.log("loop: " + loop)
        //
        //    //Update the mlist with all the child node objects
        //    for (firstsearch in loop) {
        //        parentdata.push(loop[firstsearch]);
        //
        //        console.log("loop[firstsearch]: " + loop[firstsearch].id)
        //    }
        //
        //}

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


/**
 * Created by hjdupon on 1-6-16.
 */

//************** Generate the tree diagram	 *****************
function executeCreateTree(treeData) {

    createTree(treeData)
    collapsefirstchildren(treeData);


    function createTree(treeData) {

        var margin = {top: 20, right: 120, bottom: 20, left: 200},
            width = 1000 - margin.right - margin.left,
            height = 1000 - margin.top - margin.bottom;

        var i = 0,
            duration = 750,
            root;

        var tree = d3.layout.tree()
            .size([height, width]);

        var diagonal = d3.svg.diagonal()
            .projection(function (d) {
                return [d.y, d.x];
            });

        var svg = d3.select("#subtree").append("svg")
            .attr("width", width + margin.right + margin.left)
            .attr("height", height + margin.top + margin.bottom)
            .append("g")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

        root = treeData[0];
        root.x0 = height / 2;
        root.y0 = 0;

        update(root);

        d3.select(self.frameElement).style("height", "500px");

        function update(source) {

            // show selected element
            document.getElementById("selectedTreeNode").innerHTML = source.name;

            // Compute the new tree layout.
            var nodes = tree.nodes(root).reverse(),
                links = tree.links(nodes);

            // Normalize for fixed-depth.
            nodes.forEach(function (d) {
                d.y = d.depth * 180;
            });

            // Update the nodes…
            var node = svg.selectAll("g.node")
                .data(nodes, function (d) {
                    return d.id || (d.id = ++i);
                });

            // Enter any new nodes at the parent's previous position.
            var nodeEnter = node.enter().append("g")
                .attr("class", "node")
                .attr("transform", function (d) {
                    return "translate(" + source.y0 + "," + source.x0 + ")";
                })
                .on("click", click);

            nodeEnter.append("circle")
                .attr("r", 1e-6)
                .style("fill", function (d) {
                    return d._children ? "lightsteelblue" : "#fff";
                });

            nodeEnter.append("text")
                .attr("x", function (d) {
                    return d.children || d._children ? -13 : 13;
                })
                .attr("dy", ".35em")
                .attr("text-anchor", function (d) {
                    return d.children || d._children ? "end" : "start";
                })
                .text(function (d) {
                    return d.name;
                })
                .style("fill-opacity", 1e-6);

            // Transition nodes to their new position.
            var nodeUpdate = node.transition()
                .duration(duration)
                .attr("transform", function (d) {
                    return "translate(" + d.y + "," + d.x + ")";
                });

            nodeUpdate.select("circle")
                .attr("r", 10)
                .style("fill", function (d) {
                    return d._children ? "lightsteelblue" : "#fff";
                });

            nodeUpdate.select("text")
                .style("fill-opacity", 1);

            // Transition exiting nodes to the parent's new position.
            var nodeExit = node.exit().transition()
                .duration(duration)
                .attr("transform", function (d) {
                    return "translate(" + source.y + "," + source.x + ")";
                })
                .remove();

            nodeExit.select("circle")
                .attr("r", 1e-6);

            nodeExit.select("text")
                .style("fill-opacity", 1e-6);

            // Update the links…
            var link = svg.selectAll("path.link")
                .data(links, function (d) {
                    return d.target.id;
                });

            // Enter any new links at the parent's previous position.
            link.enter().insert("path", "g")
                .attr("class", "link")
                .attr("d", function (d) {
                    var o = {x: source.x0, y: source.y0};
                    return diagonal({source: o, target: o});
                });

            // Transition links to their new position.
            link.transition()
                .duration(duration)
                .attr("d", diagonal);

            // Transition exiting nodes to the parent's new position.
            link.exit().transition()
                .duration(duration)
                .attr("d", function (d) {
                    var o = {x: source.x, y: source.y};
                    return diagonal({source: o, target: o});
                })
                .remove();

            // Stash the old positions for transition.
            nodes.forEach(function (d) {
                d.x0 = d.x;
                d.y0 = d.y;
            });
        }


        function click(d) {

            if (d.children) {
                d._children = d.children;
                d.children = null;
            } else {
                d.children = d._children;
                d._children = null;
            }
            update(d);
        }

    }
}

function update(source) {

    // show selected element
    document.getElementById("selectedTreeNode").innerHTML = source.name;

    // Compute the new tree layout.
    var nodes = tree.nodes(root).reverse(),
        links = tree.links(nodes);

    // Normalize for fixed-depth.
    nodes.forEach(function (d) {
        d.y = d.depth * 180;
    });

    // Update the nodes…
    var node = svg.selectAll("g.node")
        .data(nodes, function (d) {
            return d.id || (d.id = ++i);
        });

    // Enter any new nodes at the parent's previous position.
    var nodeEnter = node.enter().append("g")
        .attr("class", "node")
        .attr("transform", function (d) {
            return "translate(" + source.y0 + "," + source.x0 + ")";
        })
        .on("click", click);

    nodeEnter.append("circle")
        .attr("r", 1e-6)
        .style("fill", function (d) {
            return d._children ? "lightsteelblue" : "#fff";
        });

    nodeEnter.append("text")
        .attr("x", function (d) {
            return d.children || d._children ? -13 : 13;
        })
        .attr("dy", ".35em")
        .attr("text-anchor", function (d) {
            return d.children || d._children ? "end" : "start";
        })
        .text(function (d) {
            return d.name;
        })
        .style("fill-opacity", 1e-6);

    // Transition nodes to their new position.
    var nodeUpdate = node.transition()
        .duration(duration)
        .attr("transform", function (d) {
            return "translate(" + d.y + "," + d.x + ")";
        });

    nodeUpdate.select("circle")
        .attr("r", 10)
        .style("fill", function (d) {
            return d._children ? "lightsteelblue" : "#fff";
        });

    nodeUpdate.select("text")
        .style("fill-opacity", 1);

    // Transition exiting nodes to the parent's new position.
    var nodeExit = node.exit().transition()
        .duration(duration)
        .attr("transform", function (d) {
            return "translate(" + source.y + "," + source.x + ")";
        })
        .remove();

    nodeExit.select("circle")
        .attr("r", 1e-6);

    nodeExit.select("text")
        .style("fill-opacity", 1e-6);

    // Update the links…
    var link = svg.selectAll("path.link")
        .data(links, function (d) {
            return d.target.id;
        });

    // Enter any new links at the parent's previous position.
    link.enter().insert("path", "g")
        .attr("class", "link")
        .attr("d", function (d) {
            var o = {x: source.x0, y: source.y0};
            return diagonal({source: o, target: o});
        });

    // Transition links to their new position.
    link.transition()
        .duration(duration)
        .attr("d", diagonal);

    // Transition exiting nodes to the parent's new position.
    link.exit().transition()
        .duration(duration)
        .attr("d", function (d) {
            var o = {x: source.x, y: source.y};
            return diagonal({source: o, target: o});
        })
        .remove();

    // Stash the old positions for transition.
    nodes.forEach(function (d) {
        d.x0 = d.x;
        d.y0 = d.y;
    });
}

function click(d) {

    if (d.children) {
        d._children = d.children;
        d.children = null;
    } else {
        d.children = d._children;
        d._children = null;
    }
    update(d);
}

function collapsefirstchildren(root) {
    var rootchildren = root.children;

    for (items in rootchildren) {

        console.log(rootchildren[items].id)
        var d = rootchildren[items].id;

        click(d)
    }
}




