//Created by aroeters
$(document).ready(initialize);
function initialize() {
    var url = "treeBuilder";

    $('#ontology-tree').jstree({
        'core': {
            'data': {
                'url': url,
                'data': function(node) {
                    console.log(node.icon)
                    return {'id': node.id, 'icon':node.icon};
                }
            }
        },
        'plugins': ["checkbox", "search"]
    });

    var to = false;
    $('#search-symptom').keyup(function() {
        if (to) {
            clearTimeout(to);
        }
        to = setTimeout(function() {
            var v = $('#search-symptom').val();

            $('#ontology-tree').jstree(true).search(v);
        }, 250);
    });
    $('#search-symptom').focus(function() {
        $('#search-symptom').attr('value', '');
    });
}