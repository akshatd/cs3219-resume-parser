var url = "";
var paramID = location.search.split('fileNo=')[1].split('&')[0];
var paramResume = location.search.split('jobName=')[1];
paramResume = paramResume.replace(/%20/g, " ");
var newRow = jQuery('<tr><td>'+paramID+'</td><td>lala</td><td>'+paramResume+'</td><td>100%</td></tr>');
jQuery('tbody.list').append(newRow);
