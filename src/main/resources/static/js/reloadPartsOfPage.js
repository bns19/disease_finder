/**
 * Project: Disease Finder
 * Theme 11/12
 * Created by bnsikkema
 */
 //reloads the history and statistics tab when the navbar is clicked



$(document).ready(function () {
    $("#tablist").click(function () {
        $("#statisticsTab").load(location.href + " #statisticsTab>*", "");
        $("#historytab").load(location.href + " #historytab>*", "");
    });
});
