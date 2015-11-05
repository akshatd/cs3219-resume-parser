var myfile="";

$('#resume_link').click(function( e ) {
    e.preventDefault();
    $('#resume').trigger('click');
});

$('#resume').on( 'change', function() {
   myfile= $( this ).val();
   console.log("myfile"+myfile);
       //upload it
       var x = document.getElementById("resume");
       //var fileNo = "";
       var fileName = "";
       var fileSize = "";
       if ('files' in x)
       {

            for (var i = 0; i < x.files.length; i++)
            {
                //fileNo += "<br><strong>" + (i+1) + "</strong><br>";
                var file = x.files[i];
                if ('name' in file)
                {
                    fileName = "<a download='"+file.name+"'>"+file.name+"</a>" ;
                }
                if ('size' in file)
                {
                    fileSize = file.size;
                }
            }
        }
        //document.getElementById("num").innerHTML = fileNo;
        document.getElementById("name").innerHTML = fileName;
        document.getElementById("size").innerHTML = fileSize;

   }
);
