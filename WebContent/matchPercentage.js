var url = "";
var paramID = location.search.split('fileNo=')[1].split('&')[0];
var paramResume = location.search.split('jobName=')[1];
// var id = paramID.replace("\"", "");
// console.log("paramID = "+paramID);
// console.log("paramResume = "+paramResume);
var newRow = jQuery('<tr><td>'+paramID+'</td><td>lala</td><td>'+paramResume+'</td><td>100%</td></tr>');
jQuery('tbody.list').append(newRow);
