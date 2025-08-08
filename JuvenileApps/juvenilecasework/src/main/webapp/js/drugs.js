//used with the drugs tab under JuvenileProfile

$(document).ready(function(){
	
	$("#submitDrug").on('click',function(){
		return disableSubmitButtonCasework($(this));
	});
	
	$("#addDrugTestingInformation").click(function(){
		removeData();
		spinner();
	})
	
	$("#searchBtn").click(function(){
		saveData();
		$('form').attr("action", "/JuvenileCasework/searchOfficerProfile.do?clr=Y&requestOrigin=DT");
		$('form').submit();
	})
	
	$("#finishBtn").click(function(){
		removeData();
	})
	
	//formerly function validateFields(theForm)
	$("#addDrugValidate").on('click',function(){
		if($("[name='onsetAge']").val()!="")
		{
			if($("[name='frequencyId']").val()=="")
			{
				alert("Frequency is required if Onset Age is entered.");
				$("[name='frequencyId']").focus();
				return false;
			}
			if($("[name='degreeId']").val()=="")
			{
				alert("Degree is required if Onset Age is entered.");
				$("[name='degreeId']").focus();
				return false;
			}
		}
		var theForm = document.juvenileDrugForm
		return validateJuvenileDrugForm(theForm);
	});
	
	if(typeof $("#drugTestDate") != "undefined"){
		datePickerSingle($("#drugTestDate"),"Date",false);
	}
	
	$("#drugTestDate").change(function(){
		validateDrugTestDate();		
	})
	
});


function saveData(){
	localStorage.setItem("associatedCasefile", $("#associatedCasefile").val() );
	localStorage.setItem("testAdministered", $("#testAdministered").val() );
	localStorage.setItem("drugTestDate", $("#drugTestDate").val() );
	localStorage.setItem("drugTestTime", $("#drugTestTime").val() );
	localStorage.setItem("testLocation", $("#testLocation").val() );
	localStorage.setItem("substancesTested", $("#substancesTested").val() );
	localStorage.setItem("drugTestResult", $("#drugTestResult").val() );
	localStorage.setItem("comments", $("#comments").val() );
	spinner();
}

function loadData(){
	if ( localStorage.getItem("associatedCasefile") != "" ) {
		$("#associatedCasefile").val( localStorage.getItem("associatedCasefile") );
	}
	
	if ( localStorage.getItem("testAdministered") != "" ) {
		$("#testAdministered").val( localStorage.getItem("testAdministered") );
	}
	
	if ( localStorage.getItem("drugTestDate") != "" ) {
		$("#drugTestDate").val( localStorage.getItem("drugTestDate") );
	}
	
	if ( localStorage.getItem("drugTestTime") != "" ) {
		$("#drugTestTime").val( localStorage.getItem("drugTestTime") );
	}
	
	if ( localStorage.getItem("testLocation") != "" ) {
		$("#testLocation").val( localStorage.getItem("testLocation") );
	}
	
	if ( localStorage.getItem("substancesTested") != "" ) {
		$("#substancesTested").val( localStorage.getItem("substancesTested") );
	}
	
	if ( localStorage.getItem("drugTestResult") != "" ) {
		$("#drugTestResult").val( localStorage.getItem("drugTestResult") );
	}
	
	if ( localStorage.getItem("comments") != "" ) {
		$("#comments").val( localStorage.getItem("comments") );
	}
}

function removeData(){
	localStorage.removeItem("associatedCasefile");
	localStorage.removeItem("testAdministered");
	localStorage.removeItem("drugTestDate");
	localStorage.removeItem("drugTestTime");
	localStorage.removeItem("testLocation");
	localStorage.removeItem("substancesTested");
	localStorage.removeItem("drugTestResult");
	localStorage.removeItem("comments");
}


function validateDrugTestDate()
{
	var drugTestDate = $("#drugTestDate").val();
	var currentDate = getCurrentDate();
	console.log("drug test date: ", drugTestDate); 
	console.log("current date: ", currentDate); 
	
	var numDrugTestDate = Date.parse(drugTestDate);
	var numCurrentDate =  Date.parse(currentDate);
	
	if(numDrugTestDate > numCurrentDate){
		alert("drug test date cannot be in the future");
		$("#drugTestDate").val("");
		$("#drugTestDate").focus();		
		return false;
	} 
	
}

function getCurrentDate(){
	
	// Create a new Date object representing today's date
    today = new Date();
    // Get the day of the month
    var dd = today.getDate();
    // Get the month, adding 1 because JavaScript months are zero-based (January is 0)
    var mm = today.getMonth()+1; // As January is 0.
    // Get the full year
    var yyyy = today.getFullYear();

    // If the day of the month is less than 10, prepend '0' to it to ensure two digits
    if(dd<10) dd='0'+dd;
    // If the month is less than 10, prepend '0' to it to ensure two digits
    if(mm<10) mm='0'+mm;

	var currentDate = mm + "/" + dd + "/" + yyyy;
	
	return currentDate;
}