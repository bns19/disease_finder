/**
 * Created by bas on 21-5-16.
 */


$(document).ready(function(){
    $("#tablist").click(function(){
        $("#statisticsTab").load(location.href+" #statisticsTab>*","");
        $("#historytab").load(location.href+" #historytab>*","");
    });
});
