var counter = 0;
$(function(){

	$.ajax({
		url: 'jobs',
		success: function(data) {
			alert(JSON.stringify(data));
			for (var x = 0; x < data.length; x++) {
				counter++;
				var name = (data[x].jobContentMap.profile[0] &&
						data[x].jobContentMap.profile[1])?
								data[x].jobContentMap.profile[0].content + ' ' +
								data[x].jobContentMap.profile[1].content:
									data[x].id;
				var newRow = jQuery('<tr id="job'+(x + 1)+'"><td>'+
						data[x].id+'</td><td><a href="match_percentage.html?jobId=' + data[x].id + 
						'&jobName=' + name + '" target="_blank">'+ 
						name +'</a></td><td>'+ data[x].id + '</td></tr>');
				jQuery('tbody.list').append(newRow);
				
			}
		}	
	});
});