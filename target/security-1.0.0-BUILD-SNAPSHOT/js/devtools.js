function tailLog(){
	
	$.ajax({
        url: 'http://10.223.61.90:8080/security/tailLog',
        success: function (result) {
            if (result.isOk == false) alert(result.message);
        },
        failure: function (){
        	
        }
    });

	setTimeout(readOutput(),5000);
}
function readOutput(){
	
	$.get('/path/to/file.txt',function(data) {
		   if (data) {
			   $('.logDisplay').append(data)
		   } else {

		   }
		});
}