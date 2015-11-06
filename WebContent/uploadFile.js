var myfile="";
var resume = angular.module("resume", []);
resume.controller("control", function($scope)
{
$scope.records = [{ 'num':'1','name':'Rupali.pdf'},];

$scope.addRow = function()
{
	$scope.records.push({ 'num':$scope.num,'name':$scope.name });
	//$scope.name='';
	//$scope.num = 0;
};

$('#resume_link').click(function( e ) {
    e.preventDefault();
    $('#resume').trigger('click');

});
$('#resume').on( 'change', function() {
   myfile= $( this ).val();
   console.log("myfile"+myfile);
       //upload it
       var x = document.getElementById("resume");
       var fileNo = "";
       var fileName = "";
       var fileSize = "";
       if ('files' in x)
       {

            for (var i = 0; i < x.files.length; i++)
            {
                fileNo += "<strong>" + (i+1) + "</strong>";
                var file = x.files[i];
                if ('name' in file)
                {
                    //fileName = "<a href='"+file.name+"'>"+file.name+"</a>" ;
                    fileName = file.name;
                }
            }
        }
        
        $scope.records.push({ 'num':fileNo,'name':fileName });

   }
);

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
