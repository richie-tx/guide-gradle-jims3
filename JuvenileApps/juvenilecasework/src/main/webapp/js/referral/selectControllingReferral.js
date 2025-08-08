/**
 * Create Juvenile JQuery
 */
$(document).ready(function () {
	var myForm = document.forms[0];
	var action = $("#action").val(); 
	
	if(typeof $("#probationStartDate") != "undefined"){		
		datePickerSingle($("#probationStartDate"),"Probation Start Date",true);
	}
	
	if(typeof $("#probationEndDate") != "undefined"){		
		datePickerSingle($("#probationEndDate"),"Probation End Date",true);
	}
});