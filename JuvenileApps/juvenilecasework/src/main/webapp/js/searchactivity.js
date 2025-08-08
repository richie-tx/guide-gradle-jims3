$(document).ready(function () {
	//var currDate= new Date();	
	//alert('inside js')
	document.getElementById("selectedCategoryId").selectedIndex = 0;
	document.getElementById("locationId").selectedIndex = 0;
	$("#selectSystemActivitiesId").val("1");
	$("#selectclosedCasefilesId").val("1");
	/*if(typeof $("#activityPage") != "undefined" ) {
		//added for bug fix #39771
		$("#selectedCategoryId").val("selectAll");
		document.getElementById("selectedCategoryId").selectedIndex = 0;
	}*/
	//startDate DATE CALENDAR.
	if(typeof $("#startDateAsStr") != "undefined" && typeof $("#endDateAsStr") != "undefined"){
		datePickerRange($("#startDateAsStr"),$("#endDateAsStr"),"Start Date","End Date");
	}
	$("input[name^='selectSystemActivities']").on('change', function (e) {		
		if (this.checked)
		{			
			$("#selectSystemActivitiesId").val("1");		
		} 	
		if (!this.checked)
		{
			$("#selectSystemActivitiesId").val("0");		
		}
	});
	$("input[name^='selectclosedCasefiles']").on('change', function (e) {		
		if (this.checked)
		{			
			$("#selectclosedCasefilesId").val("1");		
		} 	
		if (!this.checked)
		{
			$("#selectclosedCasefilesId").val("0");		
		}
	});
	$("#viewActivity").on("click",function(){		
		var offlastName = $("#offlastName").val();
		var offfirstName = $("#offfirstName").val();
		var category = $("#selectedCategoryId").val();
		var type = $("#selectedTypeId").val();
		var startDt = $("#startDateAsStr").val();
		var endDt = $("#endDateAsStr").val();	
		var juvNum = $("#juvenileNum").val();
		
		var officerSearch = false;
		
		if(offlastName && !category && !type && !startDt){
			alert("search by officer requires a start date");
			$("#startDateAsStr").focus();
			return false;
		}
		
		if(offlastName && !category && !type && !endDt){
			alert("search by officer requires an end date");
			$("#endDateAsStr").focus();
			return false;
		}
		
		if(offlastName && offfirstName && startDt && endDt && !category && !type){
			officerSearch = true;
		}
		
		
		if(officerSearch) {
			//do not include closed case files - set checkbox to false
			$("#selectclosedCasefilesId").val("0");	
			
			category = "";
			type = "";
			
		} else {
			
			if(category ==""){
		    	alert("Please select an Activity Category.");
		    	$("#selectedCategoryId").focus();
		    	return false;
		    }
			
			if(type ==""){
		    	alert("Please select an Activity Type.");
		    	$("#selectedTypeId").focus();
		    	return false;
		    }
		}		
	    	    
	    if(startDt ==""){
	    	alert("Start Date is required.");
	    	$("#startDateAsStr").focus();
	    	return false;
	    }
	    if(endDt ==""){
	    	alert("End Date is required.");
	    	$("#endDateAsStr").focus();
	    	return false;
	    }
	    if(offlastName!=""){
		    if(offlastName.length<2){
		    	alert("Last Name must be at least 2 valid characters.");
		    	$("#offlastName").focus();
		    	return false;
		    }
	    }
	    if(offfirstName!=""&&offlastName=="") 
	    {
	    	alert("Last Name is required .");
	    	$("#offlastName").focus();
	    	return false;
	    }
	    if(offfirstName!=""){
		    if(offfirstName.length<2){
		    	alert("First Name must be at least 2 valid characters.");
		    	$("#offfirstName").focus();
		    	return false;
		    }
	    }	   					
		if (juvNum != "") 
		{
			if (juvNum.trim().length < 6) {
				alert("Invalid Juvenile Number.");
				$("#juvNum").focus();
				return false;
			}
			if (Math.floor(juvNum) != juvNum
					|| $.isNumeric(juvNum) == false) {
				alert("Invalid Juvenile Number.");
				$("#juvNum").focus();
				return false;
			}
		} 
		
	    if ( true ){
			spinner();
		}
	});
	
	//activityDate DATE CALENDAR.
	if(typeof  $("#activityDate") != "undefined"){
		datePickerSingle($("#activityDate"),"Activity Date",true);
	}
	
	

	// Limit text on keyup and mouseout 
	$("#comments").on('keyup ',function(){
		textLimit($(this),32000);
		$(this).css('height','auto'); //added for 35135
		$(this).height(this.scrollHeight); // hot fix : screen resolution issue
		$(this).focus();
		return false;
	});
	
});