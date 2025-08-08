//common functionality across casework

$(document).ready(function () {
	var originalGradeLevel = localStorage.getItem("originalGradeLevel");
	var currentGradeLevel = localStorage.getItem("originalSelectedGradeLevel");
	console.log(originalGradeLevel);
	console.log(currentGradeLevel);
	
	if ( originalGradeLevel == null){ 
		originalGradeLevel = parseInt( $("#gradeLevelId").val() ) 
		$("#gradeChangeReason").val("");
	}; 
	
	if ( currentGradeLevel == null ){
		currentGradeLevel = parseInt( $("#gradeLevelId").val() );
	}
	
	
	if ( currentGradeLevel < originalGradeLevel ) {
		$(".changeReason").addClass("lowerGrade");
	} else {
		$(".changeReason").removeClass("lowerGrade");
	}
	
	// Preload show hide
	var appropiate = $("#appropriateGradeLevelId").val();
	if( typeof $("#appropriateGradeLevelId") != "undefined" && appropiate == 'BEH'){
		
		$("#behindSelected1").show();
		$("#behindSelected2").show();
		$("#behindSelected3").show();
	}
	
	$("#gradeLevelId").on('change', function(){
		currentGradeLevel =  parseInt( $("#gradeLevelId").val() );
		$("#gradeChangeReason").val("");
		
		if ( currentGradeLevel < originalGradeLevel ) {
			$(".changeReason").addClass("lowerGrade");
		} else {
			$(".changeReason").removeClass("lowerGrade");
		}
		
		
	});
	
	$('#paId').on('change', function(){		
		
		showPAId();
	});
	
	$('#specificNamefld').hide();
	$("#schoolId").on('change',function(){		
		var schoolName = $("#schoolId").val();		
		if(typeof schoolName!="undefined"){
			showHomeSchoolInfo(schoolName);
		}
	});
	
	
	if(typeof $("#paId") != "undefined"){
		showPAId();
	}
	
	$("#nextButton").on("click", function(){
		console.log("next button");
		localStorage.setItem("originalGradeLevel", originalGradeLevel );
		localStorage.setItem("originalSelectedGradeLevel", currentGradeLevel );
		
		var errorMessage = "";
		var selectedVerification = $("#verificationCodeId").val();
		var otherVerify = $("#verfifyOther").val();
		var appropiate = $("#appropriateGradeLevelId").val();
		var repeatTotal = $("#gradeRepeatTotal").val();
		var gradesRepeated = $("#gradesRepeatedId").val();
		var gradesRepeatNum = $("#gradesRepeatNumber").val();
		var verifyDate = $("#verifiedDate").val();
		var completedDate = $("#completionDate").val();
		var awardedDate = $("#awardDateStrId").val();
		var schoolDist  = $("#schDist").val();
		var specificName = $("#specificSchName").val();	
		var today = formatDate(new Date());
		var numbers = /^[ 1-5 ]*$/;
		
		if(schoolDist == "141" && specificName == ""){			
			
			$("#specificSchName").focus();
			errorMessage +="Specific school name is required\n";
		}
		
		if( appropiate == 'BEH' ){
			
			if (gradesRepeatNum == "")
			{
				$("#gradesRepeatNumber").focus();
				errorMessage +="How many times cannot be blank \n";
			}else if (! $.isNumeric(gradesRepeatNum))
		 	{
				$("#gradesRepeatNumber").focus();
				errorMessage +="How many times must be Numeric \n";
		 	}else if( gradesRepeatNum.trim() < 1 )
		 	{
		 		$("#gradesRepeatNumber").focus();							
				errorMessage +="How many times must be greater than 0\n";
				
			} else if( gradesRepeatNum.trim() > 5 )
			{
				$("#gradesRepeatNumber").focus();
				errorMessage +="How many times cannot be greater than 5 \n";
			}
			
			
			if (repeatTotal == ""){
				$("#gradeRepeatTotal").focus();
				errorMessage +="Number of times youth failed or repeated a grade cannot be blank \n";
			}else if (! $.isNumeric(repeatTotal))
		 	{
				$("#gradeRepeatTotal").focus();
				errorMessage +="Number of times youth failed or repeated a grade must be Numeric \n";
				
		 	}else  if( repeatTotal.trim() < 1 )
		 	{
		 		$("#gradeRepeatTotal").focus();
				errorMessage +="Number of times youth failed or repeated a grade must be greater than 0\n";

			}else if( repeatTotal.trim() > 5 )
			{
				$("#gradeRepeatTotal").focus();			
				errorMessage += "Number of times youth failed or repeated a grade cannot be greater than 5\n";
			}
			
			if( gradesRepeated == ''){
							
				errorMessage +="Grade repeated cannot be blank\n";

			}
								
		}
		
		if( selectedVerification == "O" && otherVerify == ''){
			
			errorMessage += "If other selected, then you must specify\n";

		}		
		
		if(formatDate(verifyDate) > today){
			
			$("#verifiedDate").focus();
			errorMessage += "Verified Date cannot be a future date.\n";
		}
		
		if( awardedDate != '' && formatDate(awardedDate) > today){
			
			$("#awardDateStrId").focus();
		 	errorMessage += "Awarded Date cannot be a future date.\n";
		}
		
		if( completedDate != '' && formatDate(completedDate) > today){
					
			$("#completionDate").focus();
		 	errorMessage += "Completed Date cannot be a future date.\n";
		}
		
		if(errorMessage != ''){
			
			alert( errorMessage );
			return false;
		}
		
		return validateHomeSchoolandDates(document.forms[0]) && validateGradeChangeLevel() ;
		
	});
	
	$("#appropriateGradeLevelId").on("change",function(){
			
			var appropiate = $("#appropriateGradeLevelId").val();
			if( appropiate == 'BEH'){
				
				$("#behindSelected1").show();
				$("#behindSelected2").show();
				$("#behindSelected3").show();
			}else{
				$("#behindSelected1").hide();
				$("#behindSelected2").hide();
				$("#behindSelected3").hide();
				
				$("#gradeRepeatTotal").val("");
				$("#gradesRepeatNumber").val("");
				$("#gradesRepeatedId").val("");				
			}	

		});
	
	$("#verificationCodeId").on("change",function(){
		
		var selectedVerification = $("#verificationCodeId").val();
		if( selectedVerification == 'O'){
			
			$("#otherVerificationSelected").show();
		}else{
			$("#otherVerificationSelected").hide();
			$("#verfifyOther").val("");
		}	

	});
	
	function showPAId()
	{
		if ($('#paId').val() == "HS"){
			$("#sasId").val($('#paId').val()).attr("selected","selected");			
		}
		
		else if($('#paId').val() == "SE"){
		    $("#splEduCategoryId").val($("splEduCategoryId option:first").val());
		    $('#splEdu').show();
			
		}
		else{
			 $("#splEduCategoryId").val($("splEduCategoryId option:first").val());
			$('#splEdu').hide();
		}		
	}
	function clearSpecificNameFields()
	{		
		document.getElementsByName("specificSchoolName")[0].value = "";
		document.getElementsByName("unknownNameInd")[0].checked = false;
		document.getElementsByName("streetNum")[0].value = "";
		document.getElementsByName("streetName")[0].value = "";
		document.getElementsByName("city")[0].value = "";
		document.getElementsByName("stateId")[0].selectedIndex="0";
		document.getElementsByName("zipCode")[0].value = "";
		document.getElementsByName("zipCodeExt")[0].value = "";
	}
		


		var selVal1 = $("#schDist").val();
		if (selVal1 == "050" || selVal1 == "051" || selVal1 == "141")
		{			
			var selVal2 = $("#schoolId").val();
			if (selVal1 == "050")
			{
				// show field for Private Elementry, Private Jr.High or Private High only			
				if (selVal2 == "001" || selVal2 == "300" || selVal2 == "500")
				{
					$('#specificNamefld').show();
				}	
			}
			if (selVal1 == "051")
			{
				// show field for Elementry, Middle, Jr.High or High School only			
				if (selVal2 == "001" || selVal2 == "300" || selVal2 == "301" || selVal2 == "500")
				{
					$('#specificNamefld').show();
				}		
			}
			if (selVal1 == "141") // show field for any school 
			{
				$('#specificNamefld').show();
			} 
		}
		
		
		var selVal3 = $('#paId').val();
		
		if (selVal3 == "SE")
		{
			$("#splEdu").show();		
		}
	
	function showHomeSchoolInfo(theVal)
	{
		var fld1 = $("#schDist"); //document.getElementById("schDist");
		var selVal1 = $("#schDist").val();		
		//show("specificNamefld",0);
		// check for Private/Parochial(050), OCC(051) or GED(141)districts
		if (selVal1 == "050" || selVal1 == "051" || selVal1 == "141")
		{
			var selVal2 = $("#schoolId").val();
			if (selVal1 == "050")
			{
				// show field for Private Elementry, Private Jr.High or Private High only			
				if (selVal2 == "001" || selVal2 == "300" || selVal2 == "500")
				{
					//show("specificNamefld",1);
					$('#specificNamefld').show();
					clearSpecificNameFields();
				} else {
					clearSpecificNameFields();
				}	
			}
			if (selVal1 == "051")
			{
				// show field for Elementry, Middle, Jr.High or High School only			
				if (selVal2 == "001" || selVal2 == "300" || selVal2 == "301" || selVal2 == "500")
				{
					//show("specificNamefld",1);
					$('#specificNamefld').show();
					clearSpecificNameFields();
				} else {
					$('#specificNamefld').hide();
					clearSpecificNameFields();
				}		
			}
			if (selVal1 == "141") // show field for any school 
			{
				//show("specificNamefld",1);
				$('#specificNamefld').show();
				clearSpecificNameFields();
			}
		} 
		else 
		{				
			var forwardURL="/JuvenileCasework/processJuvenileSchool.do?submitAction=Submit";	
			$('form:first').attr('action',forwardURL);
			$('form:first').submit();
			 return false;
		}
	}
	
	
	
	
/*	//onchange of last attended Date
	$(document).on('click mouseover', '#lAttendedDate',  function(){
		var obj = $('#lAttendedDate');
		datePickerSingle( obj );
	});*/
	//onchange of last attended Date
	if(typeof  $("#enrollmentDateStr") != "undefined"){
			datePickerSingle($("#enrollmentDateStr"),"Enrollment Date",true);
	}
	if(typeof  $("#completionDate") != "undefined"){
				datePickerSingle($("#completionDate"),"Completion Date",true);
	}
	//onchange of last attended Date
	if(typeof  $("#lAttendedDate") != "undefined"){
			datePickerSingle($("#lAttendedDate"),"Enrollment Date",true);
	}
	
	//onchange of eligibility enrollment Date	
	if(typeof  $("#eligibilityEnrollmentDate") != "undefined"){
			datePickerSingle($("#eligibilityEnrollmentDate"),"Eligibility Enrollment Date",false);
	}
	//onchange of GED Awraded Date	
		if(typeof  $("#awardDateStrId") != "undefined"){
				datePickerSingle($("#awardDateStrId"),"GED Awardedt Date",true);
		}
	
	//onchange of verified Date
	if(typeof  $("#verifiedDate") != "undefined"){
		datePickerSingle($("#verifiedDate"),"Verified Date",false);
	}
	
	//onchange of completion Date
	$(document).on('click mouseover', '#completionDate',  function(){
		var obj = $('#completionDate');
		datePickerSingle( obj );
	});
	
	//onchange of award Date
	$(document).on('click mouseover', '#awardDateStrId',  function(){
		var obj = $('#awardDateStrId');
		datePickerSingle( obj );
	});
	
	//onchange of rlaTestDate Date
	$(document).on('click mouseover', '#rlaTestDate',  function(){
		var obj = $('#rlaTestDate');
		datePickerSingle( obj );
	});
	
	//onchange of verified Date
	$(document).on('click mouseover', '#mathTestDate',  function(){
		var obj = $('#mathTestDate');
		datePickerSingle( obj );
	});
	
	//onchange of verified Date
	$(document).on('click mouseover', '#scienceTestDate',  function(){
		var obj = $('#scienceTestDate');
		datePickerSingle( obj );
	});
	
	//onchange of verified Date
	$(document).on('click mouseover', '#socialStudiesTestDate',  function(){
		var obj = $('#socialStudiesTestDate');
		datePickerSingle( obj );
	});
	
	//onchange of verified Date
	$(document).on('click mouseover', '#programCompletionDate',  function(){
		var obj = $('#programCompletionDate');
		datePickerSingle( obj );
	});
	

	//From and To should be JQuery object.
	function datePickerRange( from,to ) 
	{
	      from.datepicker({
	      changeMonth: true,
	      changeYear: true,
	      onClose: function( selectedDate ) {
	        to.datepicker( "option", "minDate", selectedDate );
	      }
	    });

	    to.datepicker({
	      defaultDate: "+1w",
	      changeMonth: true,
	      changeYear: true,
	      onClose: function( selectedDate ) {
	        from.datepicker( "option", "maxDate", selectedDate );
	      }
	    });
	}
	
	//dateObj should be JQuery object.
	function datePickerSingle( dateObj ) 
	{
		  dateObj.datepicker({
	      changeMonth: true,
	      changeYear: true,
	      onClose: function( selectedDate ) {
	      dateObj.datepicker( "option", "mixDate", selectedDate );
		    }
	    });

	}
	
	$("#finish").click(function(){
		disableSubmitButton($(this));
	});
	
	$("#nextVEP").click(function(){

		var retVal = true;
		var msg = "";
		if ($("select#vepProgramCode").get(0).selectedIndex == 0){
			msg = "Program selection is required.\n";
			$('#vepProgramCode').focus(); //Focus on field
			retVal = false;
		}
		var dateStr = Trim(document.forms[0].programStartDateStr.value);
		document.forms[0].programStartDateStr.value = dateStr;
		var result = validateDate(dateStr)
		if (result > ""){
			if (msg == ""){
				$('#vepProgramCode').focus(); //Focus on field

			}	
			msg += result;
		}	
		if (msg > ""){
			alert(msg);
			//$("input#verifiedDate").focus();
			retVal = false;
		}
		return retVal;
		
	});
	
	function validateDate(fldValue)
	{
		var msg = "";
		if (fldValue == "")
		{
			msg = "Start Date is required.\n";
			return msg;
		}
	    if (msg == "")
	    {
			var dt = fldValue + " 00:00";
			var fldDateTime = new Date(dt);
			var curDateTime = new Date();
			curDateTime.setDate(curDateTime.getDate()+7);
			if (fldDateTime > curDateTime)
			{
				msg = "Start Date can not be more than 7 days forward of current date.\n";
				return msg;				
			}    	
	    }
	 	return msg;
	}
	
	function validateHomeSchoolandDates(theForm)
	{
		msg = "";
	
		//home school district and school name
		var obj  = document.getElementsByName("homeSchoolId")[0];
		var verHomeSchoolId = "";
		var verHomeSchoolDistrictId = "";
		
		if (obj != null){
			verHomeSchoolId = document.getElementsByName("homeSchoolId")[0].value;
		}
		obj = document.getElementsByName("homeSchoolDistrictId")[0];
		if (obj != null){
			verHomeSchoolDistrictId = document.getElementsByName("homeSchoolDistrictId")[0].value;
		}
		var verHomeSchoolRequired = theForm["homeSchoolRequired"].value;
		
		if(verHomeSchoolRequired != null && verHomeSchoolRequired == 'Y')
		{
			if (verHomeSchoolDistrictId == null || verHomeSchoolDistrictId==''){
				theForm["homeSchoolDistrictId"].focus();
				msg = "Home School District selection is required.\n";
			}
			if (verHomeSchoolId == null || verHomeSchoolId==''){
				if (msg == "") {
					theForm["homeSchoolId"].focus();
				}
				msg += "Home School Name selection is required.\n";
			}
		}
		//specify Name and address
		//if(typeof $("#specificSchName").val() != "undefined")
		
		if ($("#specificNamefld").is(":visible")){
			var fld1 = document.getElementsByName("specificSchoolName")[0];
			var fld2 = document.getElementsByName("unknownNameInd")[0];
			if (fld1.value == "" && fld2.checked == false){
				if (msg == ""){
					fld1.focus();
				}
				msg += "Specific Name entry or Unknown selection is required.\n";
			}
			var zipCode5 = /^[ 0-9 ]{5}$/;
			var zipCode4 = /^[ 0-9 ]{4}$/;
			fld1 = document.getElementsByName("zipCode")[0];
			if (fld1.value > ""){
				if( !zipCode5.exec( fld1.value ) || fld1.value <= 0 ){
					if (msg == ""){
						fld1.focus();
					}
					msg += "Zip Code must 5 digit number.\n";
				}
			}
			fld2 = document.getElementsByName("zipCodeExt")[0];	
			if (fld2.value > ""){
				if (fld1.value == ""){
					if (msg == ""){
						fld1.focus();
					}
					msg += "Zip Code required for zip code extension value.\n";
				}
				if( !zipCode4.exec( fld2.value ) || fld2.value <= 0 ){
					if (msg == ""){
						fld2.focus();
					}
					msg += "Zip Code extension must 4 digit number.\n";
				}
			}
		}	
		//enrollment date
		var attDate = theForm["lastAttendedDateString"];
		var inputDate = new Date(attDate.value);
		var today = new Date();
			
		if(inputDate > today){
			if (msg == "") {
				attDate.focus()
			}	
			msg += "Enrollment Date cannot be a future date.\n";
		}
		//verified date	
		var verDate = theForm["verifiedDateString"];
		if(verDate.value > "") {
			inputDate = new Date(verDate.value);
			if(inputDate > today)
			{
				if (msg == "") {
					verDate.focus()
				}
				msg += "Verified Date cannot be a future date.\n";
			}
		}
		
		
		if (msg == "") {
			return true;	
		}	
		alert(msg);
		return false;
	}

	function validateGradeChangeLevel() {
		var gradeChangeLevel = parseInt ( $("#gradeLevelId").val() );
		var gradeChangeReason = $("#gradeChangeReason").val();
		var pattern = /^([a-zA-Z0-9!@#$%^&*()\s]+)$/;
		console.log( pattern.test( gradeChangeReason ) );
		if ( gradeChangeLevel < originalGradeLevel ){
			if ( gradeChangeReason.length < 5 ){
				alert('Reason for Lower Grade Level Change is required. Minimum is 5 characters.');
				return false;
			} else if ( !pattern.test( gradeChangeReason ) ){
				alert("Reason for Lower Grade Level Change is not valid. Only alphanumeric with symbols !@#$%^&*() are allowed. ");
				return false;
			} else {
				return true;
			}
		}
		
		return true;
	}
	
	function formatDate(date) {
		 	
		var newStr = '';
		if( date > ''){
			
			var tempDate = new Date(date).toISOString().substr(0, 10);
 		newStr = tempDate.replace(/-/g, "");
		}	 		
 	return newStr;
	}

});