//detention Hearings.js - Detention court conversion.
$(document).ready(function () {	
	
	var cursorPosition = $("#cursorPosition").val();

	if(typeof $("#date") != "undefined"){
		datePickerSingle($("#date"),"Date",false);
	}
	
   //required fields validation
	$("#btnCrtDocket").on("click",function(){
		//Can be a hearing Date\court date
		if(typeof $("#date") != "undefined"){
			if($("#date").val()==""){
				alert("Date is required. Please select a date.");
				$("#date").focus();
				return false;
			}
		}
		
		if ( true ){
			spinner();
		}
	});
	
	$("#printDocketBtn").on("click",function(){
		//docketTime
		if(typeof $("#docketTime") != "undefined"){
			if($("#docketTime").val()=="")
			{
				alert("Docket Time is required.Please enter a valid Time.");
				$("#docketTime").focus();
				return false;
			}
			if($("#docketTime").val()=="0000")
			{
				alert("INVALID TIME");
				$("#docketTime").focus();
				return false;
			}
			else{
				var timeFormatRegex = /^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$/;
				if(timeFormatRegex.test($("#docketTime").val())== false){
	 			  alert("Please enter a valid Docket Time in HHMM format.")
	 			  $("#docketTime").focus();
	 			  return false;
				}
			}
		}
		
		if ( true ) {
			spinner();
			$.ajax({
    	        url: "/JuvenileCasework/displayJuvenileSearchDetentionHearings.do?submitAction=Print",
    	        type: 'post',
    	        success: function(data, textStatus , xhr) {
    	         	if (200 == xhr.status){
    	         		$(".overlay").remove();
    	         	}
    	        }
    	    });
		}
		});	
	
});