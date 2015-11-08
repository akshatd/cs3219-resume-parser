var url = "";
var counter = 1;
var paramID = location.search.split('fileNo=')[1].split('&')[0];
var paramResume = location.search.split('jobName=')[1];
paramResume = paramResume.replace(/%20/g, " ");
var newRow = jQuery('<tr id="paramResume'+counter+'"><td>'+paramID+'</td><td>lala</td><td>'+paramResume+'</td><td id="percentage'+counter+'">100%</td></tr>');
jQuery('tbody.list').append(newRow);
counter++;
//--------for testing to see if rows hide/show properly, can delete this later---------------------------
var newRow = jQuery('<tr id="paramResume'+counter+'"><td>'+paramID+'</td><td>lala</td><td>'+paramResume+'</td><td id="percentage'+counter+'">88%</td></tr>');
jQuery('tbody.list').append(newRow);
counter++;
var newRow = jQuery('<tr id="paramResume'+counter+'"><td>'+paramID+'</td><td>lala</td><td>'+paramResume+'</td><td id="percentage'+counter+'">56%</td></tr>');
jQuery('tbody.list').append(newRow);
counter++;
var newRow = jQuery('<tr id="paramResume'+counter+'"><td>'+paramID+'</td><td>lala</td><td>'+paramResume+'</td><td id="percentage'+counter+'">23%</td></tr>');
jQuery('tbody.list').append(newRow);
counter++;
//-------------------------------------------------------------------------------------------------------
$(function(){

	var currentValue = $('#currentValue');

	$('#defaultSlider').change(function(){
	    currentValue.html(this.value);
      var value = this.value;
        console.log("value is = "+this.value);
        //show or hide the row based on the control slider value
        for(var i = 1; i <= counter; i++)
        {
          var matchPercentage = $("td#percentage"+i).html();
          matchPercentage = matchPercentage.replace(/%/g,"");
          if(parseInt(value) > parseInt(matchPercentage))
          {
            //hide
            $("tr#paramResume"+i).hide();
          }
          else
          {
            //show
            $("tr#paramResume"+i).show();
          }
        }
	});


	// Trigger the event on load, so
	// the value field is populated:

	//$('#defaultSlider').change();

});
