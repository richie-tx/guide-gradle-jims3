// only used for search
$(document).ready(function () {
	//Court Date
	
	var cursorPosition = $("#cursorPosition").val();
	var currDate= new Date(); //current Date
	
	if(typeof $("#date") != "undefined"){
		datePickerSingle($("#date"),"Date",false);
	}
	
	//required fields validation
	$("#btnCrtAction,#btnCrtDocket").on("click",function(){
		//Can be a hearing Date\court date
		if(typeof $("#date") != "undefined"){
			if($("#date").val()==""){
				alert("Date is required. Please select a date.");
				$("#date").focus();
				return false;
			}
		}
		
		if ( true ) {
			spinner();
		}
	});
	
});