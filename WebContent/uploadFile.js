var myfile="";
var counter = 0;
var resumeArray = ["hi"];
$('#resume_link1').click(function( e ) {
    e.preventDefault();
    $('#resume_applicant').trigger('click');

});
$('#resume_link2').click(function( e ) {
    e.preventDefault();
    $('#resume_recruiter').trigger('click');

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
            resumeArray.push(fileName);
            console.log(resumeArray);
            jQuery('tbody.list').append(newRow);

        }

   }
);

$('#resume_recruiter').on( 'change', function() {
   myfile= $( this ).val();
   console.log("myfile"+myfile);
       //upload it
       var x = document.getElementById("resume_recruiter");
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
            var newRow = jQuery('<tr><td>'+fileNo+'</td><td><a href="match_percentage.html?fileNo='+fileNo+'&jobName='+fileName+'&cvName='+resumeArray[0]+'" target="_blank">'+fileName+'</a></td></tr>');
            jQuery('tbody.list').append(newRow);

        }

   }
);

$('#resume_applicant').fileupload({	 
    dataType: 'json',
    done: function(e, data) {
    	alert(JSON.stringify(data));
    }
});


// $('#resume_link').click(function( e ) {
//     e.preventDefault();
//     $('#resume').trigger('click');
//
// });

// $('#resume').on( 'change', function() {
//    myfile= $( this ).val();
//    console.log("myfile"+myfile);
//        //upload it
//        var x = document.getElementById("resume");
//        var fileNo = "";
//        var fileName = "";
//        var fileSize = "";
//        if ('files' in x)
//        {
//
//             for (var i = 0; i < x.files.length; i++)
//             {
//                 fileNo += "<strong>" + (i+1) + "</strong>";
//                 var file = x.files[i];
//                 if ('name' in file)
//                 {
//                     //fileName = "<a href='"+file.name+"'>"+file.name+"</a>" ;
//                     fileName = file.name;
//                 }
//                 if ('size' in file)
//                 {
//                     fileSize = file.size;
//                 }
//             }
//         }
//         document.getElementById("num").innerHTML = fileNo;
//         document.getElementById("name").innerHTML = fileName;
//         document.getElementById("size").innerHTML = fileSize;
//
//    }
// );
