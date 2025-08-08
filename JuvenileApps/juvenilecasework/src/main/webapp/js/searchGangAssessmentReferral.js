$(document).ready(function () {
	
	
	document.getElementById("startDateAsStr").value = "";
	document.getElementById("endDateAsStr").value = "";
	
	if(typeof $("#startDateAsStr").val() != "undefined"){
		  datePickerSingle($("#startDateAsStr"),"From",false);
	}
	if(typeof $("#endDateAsStr").val() != "undefined"){
		  datePickerSingle($("#endDateAsStr"),"From",false);
	}
	
	$("#submitBtn").on("click",function(){	
		
		var status = $("#status").val();
		var fromDate = $("#startDateAsStr").val();
		var toDate = $("#endDateAsStr").val();
		
		if( status == '' || status == null ){			
			alert("Gang Assessment Referrals is required to serach.");
	    	$("#status").focus();
	    	return false;
		}
	    if( status == "Completed" ){
		    if(fromDate == '' || fromDate == null || toDate == '' || toDate == null   ){
		    	alert("Date Range is required for Completed Referral Status search");
		    	$("#fromDate").focus();
		    	return false;
		    }
	    }
	    
	    if ( true ) {
			spinner();
			$('form').submit();
		}
	});
	
});