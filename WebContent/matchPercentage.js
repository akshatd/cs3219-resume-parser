var url = "";
var counter = 1;
var paramID = location.search.split('fileNo=')[1].split('&')[0];
var paramResume = location.search.split('jobName=')[1];
paramResume = paramResume.replace(/%20/g, " ");
var newRow = jQuery('<tr id="paramResume'+counter+'"><td>'+paramID+'</td><td>lala</td><td>'+paramResume+'</td><td>100%</td></tr>');
jQuery('tbody.list').append(newRow);
counter++;
//--------for testing-------
var newRow = jQuery('<tr id="paramResume'+counter+'"><td>'+paramID+'</td><td>lala</td><td>'+paramResume+'</td><td>88%</td></tr>');
jQuery('tbody.list').append(newRow);
counter++;
var newRow = jQuery('<tr id="paramResume'+counter+'"><td>'+paramID+'</td><td>lala</td><td>'+paramResume+'</td><td>56%</td></tr>');
jQuery('tbody.list').append(newRow);
counter++;
var newRow = jQuery('<tr id="paramResume'+counter+'"><td>'+paramID+'</td><td>lala</td><td>'+paramResume+'</td><td>23%</td></tr>');
jQuery('tbody.list').append(newRow);
counter++;
//--------------------------
// $("#val").on('change',function(){
//   console.log("vghvm");
//   var x = document.getElementById("#val").val;
//   console.log("vghvmwdew");
//   document.getElementById("#controlValue").innerHTML = x;
//   console.log(x);
// });
$(function(){

	var currentValue = $('#currentValue');

	$('#defaultSlider').change(function(){
	    currentValue.html(this.value);
        console.log("value is = "+this.value);
        //show or hide the row based on the control slider value
	});


	// Trigger the event on load, so
	// the value field is populated:

	//$('#defaultSlider').change();

});
