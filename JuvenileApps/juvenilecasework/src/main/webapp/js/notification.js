$(document).ready(function () {
	
	//startDate DATE CALENDAR.
	if(typeof $("#beginDateRange") != "undefined" && typeof $("#endDateRange") != "undefined"){
		datePickerRange($("#beginDateRange"),$("#endDateRange"),"From Date","To Date");
	}
	
	
	$("#notificationTypeId").on("load change",function(){
		$("#notificationStatusIdRow").hide(); 
		$("#taskStatusIdRow").hide(); 
		if($("#notificationTypeId").val() == "N"){
			$("#notificationStatusIdRow").show(); 
		}	
		else if($("#notificationTypeId").val() == "T"){
			$("#taskStatusIdRow").show(); 
		}
	});
});