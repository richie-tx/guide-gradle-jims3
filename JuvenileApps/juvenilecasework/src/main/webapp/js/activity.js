$(document).ready(function () {
	var currDate= new Date();
	$("#activityTime").val("");
	if(typeof $("#activityPage") != "undefined" && $("#activityPage").val()!="" && typeof $("#vendor") != "undefined" &&  $("#vendor").val()== "" ) {
		//added for bug fix #39771
		$("#selectedCategoryId").val("selectAll");
	}
	//startDate DATE CALENDAR.
	if(typeof $("#startDateAsStr") != "undefined" && typeof $("#endDateAsStr") != "undefined"){
		datePickerRange($("#startDateAsStr"),$("#endDateAsStr"),"Start Date","End Date");
	}
	
	$("#viewActivity").on("click",function(){
		var category = $("#selectedCategoryId").val();
	    if(category ==""){
	    	alert("Please select an Activity Category.");
	    	$("#selectedCategoryId").focus();
	    	return false;
	    }
	});
	
	//activityDate DATE CALENDAR.
	if(typeof  $("#activityDate") != "undefined"){
		datePickerSingle($("#activityDate"),"Activity Date",true);
	}
	
	
	$("#addActivity").on("click",function(){
		//escape from is mandatory on escape flow.  
    	var activityDate = $("#activityDate").val();
    	
    	if(activityDate ==""){
    		alert("Activity Date is required.");
    		$("#activityDate").focus();
    		return false;
    	}
    	
		var category = $("#selectedCategoryId").val();
	    if(category ==""){
	    	alert("Activity Category is required.");
	    	$("#selectedCategoryId").focus();
	    	return false;
	    }
	    	
	   
	   	var activityType =  $("#selectedTypeId").val();  
		if(activityType ==""){
	    	alert("Activity Type is required.");
	    	$("#selectedTypeId").focus();
	    	return false;
	    }
	   	
	 	var activityDesc =  $("#selectedDescriptionId").val();
		if(activityDesc ==""){
	    	alert("Activity Description is required.");
	    	$("#selectedDescriptionId").focus();
	    	return false;
	    }
	});
	
	// Limit text on keyup and mouseout 
	$("#comments").on('keyup ',function(){
		textLimit($(this),32000);
		$(this).css('height','auto'); //added for 35135
		$(this).height(this.scrollHeight); // hot fix : screen resolution issue
		$(this).focus();
		return false;
	});
	
});