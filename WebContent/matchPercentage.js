var url = "";
var counter = 0;
//var paramID = location.search.split('fileNo=')[1].split('&')[0];
var paramResume = location.search.split('jobId=')[1];
paramResume = paramResume.replace(/%20/g, " ");

var jobName = location.search.split('jobName=')[1];
jobName = jobName.replace(/%20/g, " ");

$(function(){
	
	$('#jobTitle').html(jobName);
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
          console.log("matchPercentage is = "+matchPercentage);
          if(parseFloat(value) > parseFloat(matchPercentage))
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

	$.ajax({
		url: 'rank',
		data: {
			jobId: parseInt(paramResume)
		},
		success: function(data) {
			for (var x = 0; x < data.length; x++) {
				counter++;
				var name = (data[x].cv.cvContentMap.fullname[0] &&
						data[x].cv.cvContentMap.fullname[1])?
								data[x].cv.cvContentMap.fullname[0].content + ' ' +
								data[x].cv.cvContentMap.fullname[1].content:
									data[x].cv.id;
				var newRow = jQuery('<tr id="paramResume'+(x + 1)+'"><td>'+
						data[x].cv.id+'</td><td>'+ name +'</td><td>'+data[x].jobId+
						'</td><td id="percentage' + (x + 1) + '">' + 
						data[x].matchPercentage*100 + '%</td></tr>');
				jQuery('tbody.list').append(newRow);
				
			}
		}	
	});

	// Trigger the event on load, so
	// the value field is populated:

	//$('#defaultSlider').change();

});
