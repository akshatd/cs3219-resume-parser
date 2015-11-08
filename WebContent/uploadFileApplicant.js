var myfile="";
var counter = 0;

$('#resume_link1').click(function( e ) {
    e.preventDefault();
    $('#resume_applicant').trigger('click');

});
$('#resume_applicant').on( 'change', function() {
   myfile= $( this ).val();
   console.log("myfile"+myfile);
       //upload it
       var x = document.getElementById("resume_applicant");
       var fileNo = "";
       var fileName = "";
       var fileSize = "";
       counter++;
       if ('files' in x)
       {

            for (var i = 0; i < x.files.length; i++)
            {
                fileNo += counter;
                var file = x.files[i];
                if ('name' in file)
                {
                    fileName = file.name;
                }
            }
            var newRow = jQuery('<tr><td>'+fileNo+'</td><td>'+fileName+'</td></tr>');
            jQuery('tbody.list').append(newRow);

        }

   }
);

$('#resume_applicant').fileupload({	 
    dataType: 'json',
    formData: {uploader: 'applicant'},
    done: function(e, data) {
    	alert(JSON.stringify(data));
    }
});
