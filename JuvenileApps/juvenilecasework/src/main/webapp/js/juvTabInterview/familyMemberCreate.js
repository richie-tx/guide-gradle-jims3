//<!-- JavaScript - Button Check -->
//Converted to us JQuery

$(document).ready(function(){
	
	if(typeof $("#dateOfBirth")!= "undefined"){
		datePickerSingle( $("#dateOfBirth"),"Date of Birth",true);
	}
	if(typeof $("#dlExperationDate")!= "undefined"){
		datePickerSingle( $("#dlExperationDate"),"Expiration Date",false);
	}
	if(typeof $("#passportExpirationDate")!= "undefined"){
		datePickerSingle( $("#passportExpirationDate"),"Expiration Date",false);
	}
	if(typeof $("#marriageDate")!= "undefined"){
		datePickerSingle( $("#marriageDate"),"Marriage Date",false);
	}
	if(typeof $("#divorceDate")!= "undefined"){
		datePickerSingle( $("#divorceDate"),"Divorce Date",false);
	}
	
	//calls casework.js's disableSubmitButtonCasework
	$("#createSummaryFinish,#updateSummaryFinish").click(function(){
		disableSubmitButtonCasework($(this));
		
	});
	
	//all calls for goNav on all family Tab pages
	$("#displayConList").on("click", function(){
		goNav($(this).data("href"));
	});
	
	$("[name='familyMemberComments']").on('keyup mouseout',function(){
		textLimit($(this),255);
		return false;
	});
	
	//onclick="return (validateJuvenileMemberForm(this.form) && checkBirthdate(this.form) && checkPassportExpirationDate(this.form) &&validateCreateFields(this.form) && checkMarital());"
	$("#famMembNextValidate").on("click",function(){		
		var thisForm = document.juvenileMemberForm;
		if( ($("#actionStat").val()) == "createMember")
		{
			//verifyIsAdult();
			return (validateJuvenileMemberForm(thisForm) && checkBirthdate(thisForm) && checkPassportExpirationDate(thisForm) && validateSSN() && validateCreateFields() && 
					checkMarital() && validateDriversLicense() && validateIsDeceasedAndGuardian());
		}
		else {
			//verifyIsAdult();
			return (validateJuvenileMemberForm(thisForm) && checkBirthdate(thisForm) && checkPassportExpirationDate(thisForm) && validateCreateFields() && 
					checkMarital() && validateDriversLicense() && validateIsDeceasedAndGuardian());
		}
			
	});
	
	//onclick="return validateJuvenileNumber()"
	$("#valJuvNumGo").on("click",function(){
		var numericRegExp = /^[0-9]*$/;
		var fld = $("[name='searchJuvenileNumber']");
	 	fld.val(Trim($(fld).val()));
	 	if($(fld).val() == "")
	 	{
	 		alert("Juvenile Number required on GO button.");
	 		fld.focus();
	 		return false;
	 	}
	 	if (numericRegExp.test($(fld).val(),numericRegExp) == false)
	 	{
	 		alert("Juvenile Number must be numeric.");
	 		fld.focus();
	 		return false;
	 	}	
		return true;
	});
	
	
	//formerly function toggleDeceased()
	$("[name='deceased']").on('click',function(){
		var deceased = $("[name='deceased']:checked").val();
		var incarcerated = $("[name='incarcerated']:checked").val();
		
		if(deceased == "true")
		{
			$("[name='juvenileAgeAtDeath']").prop('disabled',false);
			$("[name='causeofDeathId']").prop('disabled',false);		
			
			if(incarcerated == "true"){						
				$("[name='incarcerated'][value='false']").prop("checked", true);
				$("[name='incarcerated']:checked").val(false)
				
				console.log('incarcerated value - when deceased: ', $("[name='incarcerated']:checked").val());
			}
			
			//$("[name='deceased']:checked").val(true);
			$("[name='deceased'][value='true']").prop("checked", true);
			//$("[name='incarcerated']:checked").val(false);
			$("[name='incarcerated'][value='false']").prop("checked", true);
			$("[name='incarcerated']").prop('disabled',true);		
			
		}
		else
		{
			$("[name='juvenileAgeAtDeath']").val("");
			$("[name='causeofDeathId']").val("");
			$("[name='juvenileAgeAtDeath']").prop('disabled',true);
			$("[name='causeofDeathId']").prop('disabled',true);
			
			$("[name='incarcerated']").prop('disabled',false);	
			$("[name='deceased'][value='false']").prop("checked", true);
			
		}		
		
		$("#deceasedValue").val(deceased);
		
	});
	$("[name='deceased']:checked").trigger('click');
	
	//incarcerated
	$("[name='incarcerated']").on('click',function(){
		var incarcerated = $("[name='incarcerated']:checked").val();
		var deceased = $("[name='deceased']:checked").val();
		
		if(incarcerated == "true")
		{
			if(deceased == "true"){				
				
				$("[name='deceased']:checked").val(false)
				$("[name='deceased'][value='false']").prop("checked", true);
				
				console.log('deceased value - when incarcerated: ', $("[name='deceased']:checked").val());
			}
			
			//$("[name='incarcerated']:checked").val(true);
			$("[name='incarcerated'][value='true']").prop("checked", true);
			//$("[name='deceased']:checked").val(false);
			$("[name='deceased'][value='false']").prop("checked", true);
			$("[name='deceased']").prop('disabled',true);			

		} else {
			
			$("[name='deceased']").prop('disabled',false);
			//$("[name='incarcerated']:checked").val(false)
			$("[name='incarcerated'][value='false']").prop("checked", true);
		}		
		
		$("#incarceratedValue").val(incarcerated);
		
	});
	$("[name='incarcerated']:checked").trigger('click');	

	if(document.title == "JIMS2 - familyMemberCreate.jsp")
	{
		if($("#popUp").val() == "true")
		{
			var file = 'familyMemberSelectionList.jsp';
			var window = 'selectionMemberWindow';
			msgWindow = open(file,window,'resizable=yes, scrollbar=yes, width=800,height=600');
			if (msgWindow.opener == null) 
				msgWindow.opener = self;
		}
	}
	
	//88726
	
	if($("#actionStat").val() != "findJuvenileInfo")
	{
		function validateSSN(){
		
		//Begin Social Security Validation
		
		 var SSN1 = $.trim( $("#ssn1").val());
		 var SSN2 = $.trim($("#ssn2").val());
	     var SSN3 = $.trim($("#ssn3").val());
	     
	     var ssn1Regex = /^(?!000)([0-9]{3}$)/
		 var ssn2Regex = /^([0-9]{2}$)/
		 var ssn3Regex = /^([0-9]{4}$)/
			
	
		   if( SSN1 == "" && SSN2 == "" && SSN3 == "") {
			      alert("Social Security Number is required.Please enter a valid SSN.");
			      $("#ssn1").focus();
			      return false;
		   }	
			   
	 
	   if (SSN1.length > 0 || SSN2.length > 0 || SSN3.length > 0)
	   {
			if(ssn1Regex.test(SSN1) == false)
			{
				alert("First 3 digits of SSN must be numeric and not equal 000.\n");
				$("#ssn1").focus();
				return false;
			}
			if(ssn2Regex.test(SSN2) == false)
			{
				alert("Second 2 digits of SSN must be numeric.\n");
				$("#ssn2").focus();
				return false;
			}
			if(ssn3Regex.test(SSN3) == false)
			{
				alert("Last 4 digits of SSN must be numeric.\n");
				$("#ssn3").focus();
				return false;
			}	
		}
	   if ($("[name='ssn.SSN1']").val() > "" && $("[name='ssn.SSN2']").val() == "") {
	      alert("All of Social Security number must be entered if there is partial entry.");
	      $("[name='ssn.SSN2']").focus();
	      return false;
	   }
	   if ($("[name='ssn.SSN1']").val() > "" && $("[name='ssn.SSN3']").val() == "") {
	      alert("All of Social Security number must be entered if there is partial entry.");
	      $("[name='ssn.SSN3']").focus();
	      return false;
	   }
	   if ($("[name='ssn.SSN2']").val() > "" && $("[name='ssn.SSN1']").val() == "") {
	      alert("All of Social Security number must be entered if there is partial entry.");
	      $("[name='ssn.SSN1']").focus();
	      return false;
	   }
	   if ($("[name='ssn.SSN2']").val() > "" && $("[name='ssn.SSN3']").val() == "") {
	      alert("All of Social Security number must be entered if there is partial entry.");
	      $("[name='ssn.SSN3']").focus();
	      return false;
	   }
	   if ($("[name='ssn.SSN3']").val() > "" && $("[name='ssn.SSN1']").val() == "") {
	      alert("All of Social Security number must be entered if there is partial entry.");
	      $("[name='ssn.SSN1']").focus();
	      return false;
	   }
	   if ($("[name='ssn.SSN3']").val() > "" && $("[name='ssn.SSN2']").val() == "") {
	      alert("All of Social Security number must be entered if there is partial entry.");
	      $("[name='ssn.SSN2']").focus();
	      return false;
	   }   
		//End Social Security Validation
	 
		} //88726
	}
	function validateCreateFields(){
	//Begin Social Security Validation
		
	/*	 var SSN1 = $.trim( $("#ssn1").val());
		 var SSN2 = $.trim($("#ssn2").val());
	     var SSN3 = $.trim($("#ssn3").val());
	     
	     var ssn1Regex = /^(?!000)([0-9]{3}$)/
		 var ssn2Regex = /^([0-9]{2}$)/
		 var ssn3Regex = /^([0-9]{4}$)/
		 		
		 if(typeof SSN1!="undefined"){
			   if($("#SSN1").val() == "" && $("#SSN2").val() == "" && $("#SSN3").val() == "") {
				      alert("Social Security Number is required.Please enter a valid SSN.");
				      $("#SSN1").focus();
				      return false;
			   }
		}
			   
			   
	   if (SSN1.length > 0 || SSN2.length > 0 || SSN3.length > 0)
	   {
			if(ssn1Regex.test(SSN1) == false)
			{
				alert("First 3 digits of SSN must be numeric and not equal 000.\n");
				$("#SSN1").focus();
				return false;
			}
			if(ssn2Regex.test(SSN2) == false)
			{
				alert("Second 2 digits of SSN must be numeric.\n");
				$("#SSN2").focus();
				return false;
			}
			if(ssn3Regex.test(SSN3) == false)
			{
				alert("Last 4 digits of SSN must be numeric.\n");
				$("#SSN3").focus();
				return false;
			}	
		}
	   if ($("[name='ssn.SSN1']").val() > "" && $("[name='ssn.SSN2']").val() == "") {
	      alert("All of Social Security number must be entered if there is partial entry.");
	      $("[name='ssn.SSN2']").focus();
	      return false;
	   }
	   if ($("[name='ssn.SSN1']").val() > "" && $("[name='ssn.SSN3']").val() == "") {
	      alert("All of Social Security number must be entered if there is partial entry.");
	      $("[name='ssn.SSN3']").focus();
	      return false;
	   }
	   if ($("[name='ssn.SSN2']").val() > "" && $("[name='ssn.SSN1']").val() == "") {
	      alert("All of Social Security number must be entered if there is partial entry.");
	      $("[name='ssn.SSN1']").focus();
	      return false;
	   }
	   if ($("[name='ssn.SSN2']").val() > "" && $("[name='ssn.SSN3']").val() == "") {
	      alert("All of Social Security number must be entered if there is partial entry.");
	      $("[name='ssn.SSN3']").focus();
	      return false;
	   }
	   if ($("[name='ssn.SSN3']").val() > "" && $("[name='ssn.SSN1']").val() == "") {
	      alert("All of Social Security number must be entered if there is partial entry.");
	      $("[name='ssn.SSN1']").focus();
	      return false;
	   }
	   if ($("[name='ssn.SSN3']").val() > "" && $("[name='ssn.SSN2']").val() == "") {
	      alert("All of Social Security number must be entered if there is partial entry.");
	      $("[name='ssn.SSN2']").focus();
	      return false;
	   }  */ 
	//End Social Security Validation
	 
	//Begin Driver License Validation
	   if ($("[name='driverLicenseNum']").val() > "")
	   {
	       if ($("[name='driverLicenseStateId']").val() == "") 
		   {
	          alert("State selection is required when Driver License Number entered.");
	          $("[name='driverLicenseStateId']").focus();
	          return false;
	       }
	       if ($("[name='driverLicenseExpirationDate']").val() == "") 
		   {
	          alert("Expiration Date is required when Driver License Number entered.");
	          $("[name='driverLicenseExpirationDate']").focus();
	          return false;
	       }
	       if ($("[name='driverLicenseClassId']").val() == "") 
		   {
	          alert("Class selection is required when Driver License Number entered.");
	          $("[name='driverLicenseClassId']").focus();
	          return false;
	       }
	   }
	   if ($("[name='driverLicenseStateId']").val() > "")
	   {   
		   if ($("[name='driverLicenseNum']").val() == "") 
		   {
	          alert("Driver License number is required when State is selected.");
	          $("[name='driverLicenseNum']").focus();
	          return false;
	       }
		   if ($("[name='driverLicenseExpirationDate']").val() == "") 
		   {
	          alert("Expiration Date is required when State is selected.");
	          $("[name='driverLicenseExpirationDate']").focus();
	          return false;
	       }
	       if ($("[name='driverLicenseClassId']").val() == "") 
		   {
	          alert("Class selection is required when State is selected.");
	          $("[name='driverLicenseClassId']").focus();
	          return false;
	       }
	   }
	   if ($("[name='driverLicenseExpirationDate']").val() > "")
	   {
		   if ($("[name='driverLicenseNum']").val() == "") 
		   {
	          alert("Driver License number is required when Expiration Date is entered.");
	          $("[name='driverLicenseNum']").focus();
	          return false;
	       }
		   if ($("[name='driverLicenseStateId']").val() == "") 
		   {
	          alert("State selection is required when Expiration Date is entered.");
	          $("[name='driverLicenseStateId']").focus();
	          return false;
	       }
		   if ($("[name='driverLicenseClassId']").val() == "") 
		   {
	          alert("Class selection is required when Expiration Date is entered.");
	          $("[name='driverLicenseClassId']").focus();
	          return false;
	       }
	   }
	   if ($("[name='driverLicenseClassId']").val() > "")
	   {
		   if ($("[name='driverLicenseNum']").val() == "")
	       {
		      alert("Driver License number is required when Class is selected.");
		      $("[name='driverLicenseNum']").focus();
	          return false; 
	       }
	   	   if ($("[name='driverLicenseStateId").val() == "") 
	   	   {
	   		   alert("State selection is required when Class is selected.");
	   		   $("[name='driverLicenseStateId']").focus();
	   		   return false;
	   	   }
	       if ($("[name='driverLicenseExpirationDate']").val() == "") 
		   {
	           alert("Expiration Date is required when Class is selected.");
	           $("[name='driverLicenseExpirationDate']").focus();
	           return false;
	       }
	   }	   
		   
	//End Driver License Validation   
	 
	 //Begin Passport Detail Validation
	   if ( $("[name='passportNum']").val() > "")
	   {
	       if ($("[name='countryOfIssuanceId']").val() == "") 
		   {
	          alert("Country of Issuance selection is required when Passport Number entered.");
	          $("[name='countryOfIssuanceId']").focus();
	          return false;
	       }
	       if ($("[name='passportExpirationDate']").val() == "") 
		   {
	          alert(" Passport Expiration Date is required when Passport Number entered.");
	          $("[name='passportExpirationDate']").focus();
	          return false;
	       }
	   }
	   if ($("[name='countryOfIssuanceId']").val() > "")
	   {   
		   if ($("[name='passportNum']").val() == "") 
		   {
	          alert("Passport number is required when Country Of Issuance is selected.");
	          $("[name='passportNum']").focus();
	          return false;
	       }
		   if ($("[name='passportExpirationDate']").val() == "") 
		   {
	          alert(" Passport Expiration Date is required when Country Of Issuance is selected.");
	          $("[name='passportExpirationDate']").focus();
	          return false;
	       }
	   }
	   if ($("[name='passportExpirationDate']").val() > "")
	   {
		   if ($("[name='passportNum']").val() == "") 
		   {
	          alert("Passport number is required when  Passport Expiration Date is entered.");
	          d$("[name='passportNum']").focus();
	          return false;
	       }
		   if ($("[name='countryOfIssuanceId']").val() == "") 
		   {
	          alert("Country Of Issuance selection is required when  Passport Expiration Date is entered.");
	          $("[name='countryOfIssuanceId']").focus();
	          return false;
	       }
		  
	   }
	   	   
		  //end of Passport Detail validation
		return true;  
	}
	
	
	function checkBirthdate(theForm)
	{
	    var msg = "";
	 	var dateOfBirth = $("[name='dateOfBirth']");
	 	dateOfBirth.val(Trim($(dateOfBirth).val()));
	 	if($(dateOfBirth).val() != "")
	 	 	{
//	 		clearAllValArrays();		
//			addMMDDYYYYDateValidation("dateOfBirth", "Date of Birth must be in mm/dd/yyyy date format. ");
		    var noErrors = validateCustomStrutsBasedJS(theForm);
			if (noErrors == true)
		 	{
		    	var currDate=new Date();
				var curDate = new Date();
				var currYYYY = curDate.getFullYear().toString();
				var currMM = curDate.getMonth() + 1;
				var currDD = curDate.getDate();
				if (currMM < 10){
					currMM = "0" + currMM;
				}
				if (currDD < 10){
					currDD = "0" + currDD;
				}      		
			      		
				var inDate = $(dateOfBirth).val();
				var mm = inDate.substring(0,2);
				var dd = inDate.substring(3,5);
				var yyyy = inDate.substring(6,10);
				var century = inDate.substring(6,8);
				var inDateStr = inDate.substring(6,10) + inDate.substring(0,2) + inDate.substring(3,5); 
				var currDateStr = currYYYY + currMM.toString() + currDD.toString();
				if (inDateStr > currDateStr)
				{
	  	    		if (msg == ""){
	  	    			$("[name='dateOfBirth']").focus();
		    		}	
	   	    		msg += "Birth Date cannot be a future date.";
				} 
				if (century < 19 && inDateStr <= currDateStr) 
				{
	   	    		if (msg == ""){
	   	    			$("[name='dateOfBirth']").focus();
		    		}	
	   	    		msg += "Date of Birth year cannot start with a number lower than 19.";
				}
		    }
		}
		if (msg == ""){
			$("[name='dateOfBirth']").focus();
			return birthDateValidation(document.juvenileMemberForm.dateOfBirth.value,'Date of Birth',0, 105);
		}
		alert(msg);
		return false;		
	}
	
	function checkPassportExpirationDate(theForm)
	{
	    
	 	var passportExpirationDate = $("[name='passportExpirationDate']");
	 	passportExpirationDate.val(Trim($("[name='passportExpirationDate']").val()));
	 	if($(passportExpirationDate).val() != "")
	 	 	{
		    var noErrors = validateCustomStrutsBasedJS(theForm);
			if (noErrors == true)
		 	{
		    	var currDate=new Date();
				var curDate = new Date();
				var currYYYY = curDate.getFullYear().toString();
				var currMM = curDate.getMonth() + 1;
				var currDD = curDate.getDate();
				if (currMM < 10){
					currMM = "0" + currMM;
				}
				if (currDD < 10){
					currDD = "0" + currDD;
				}      		
			      		
				var inDate = passportExpirationDate.val();
				var mm = inDate.substring(0,2);
				var dd = inDate.substring(3,5);
				var yyyy = inDate.substring(6,10);
				var century = inDate.substring(6,8);
				var inDateStr = inDate.substring(6,10) + inDate.substring(0,2) + inDate.substring(3,5); 
				var currDateStr = currYYYY + currMM.toString() + currDD.toString();
				if (inDateStr < currDateStr)
				{
		    		alert("Passport expiration date is past date. Please confirm.");
		    		return;
				} 	
		    }
		}	
		
		return true;		
	}
	
	function checkMarital()
	{
	  var marStatId = $("[name='maritalStatusId']").val();
	  var marMessage = 'non selected marital status.';
		
	  if(marStatId == "SI" || marStatId == "")
		{
	  	if(marStatId != "")
			{
	  		marMessage = 'SINGLE marital status.';
	  	}
			
	  	if($("[name='relatedFamMemId']").val() != "")
			{
	  		alert("Relationship With cannot be selected for " + marMessage); 
	  		return false;
	  	}
	  	else if($("[name='marriageDate']").val() != "")
			{
	  		alert("Marriage Date cannot be entered for " + marMessage); 
	  		return false;
	  	}
	  	else if($("[name='divorceDate']").val() != "")
			{
	  		alert("Divorce Date cannot be entered for " + marMessage); 
	  		return false;
	  	}
	  }
	  return true;
	}
	
	function validateDriversLicense(){
		var result = true;
		var driverLicenseNum = $("#driverLicenseNum").val().trim();
		
		//regExp = /^[a-zA-Z0-9!@#$ +\-]*$/;
		regExp = /^[a-zA-Z0-9#,./' \-]*$/;
		
		if(!regExp.test(driverLicenseNum, regExp))
		{
			alert("only alphanumeric characters and the following special characters ('#', '/', '.', ',', '-') are allowed");
			$("#driverLicenseNum").focus();
	 		result = false;
		}
		
		return result;
	}
	
	function validateIsDeceasedAndGuardian()
	{		
		//console.log("deceased value: " + $("#deceasedValue").val());
		//console.log("deceased: " +  $("[name='deceased']:checked").val());
		
		var isDeceased = $("[name='deceased']:checked").val();
		var isGuardian = $("#isGuardian").val().trim();
		var juvenileNumber = $("#juvenileNumber").val().trim();
		
		if(isDeceased === "true" && isGuardian === "true"){
			
			if(confirm("Updating a family member as deceased requires removing him/her as a guardian. Proceed?")){
				spinner();
				var url = '/JuvenileCasework/displayFamilyConstellationDetailsCreate.do?submitAction=Update Guardians'; 
				location.href = url;	
				
				return false
			} else {
				
				$('#deceasedYes').focus();
				return false				
			}
		}
		
		return true;
	}
	
	//disable/enable over21 radio buttons
	disableRadioSelectionIfNullDob();
	
	$('#dateOfBirth').change(function(){
		
		verifyIsAdult();		

	})
	
	function verifyIsAdult(){
		var result = false;
		var dateOfBirth =  $('#dateOfBirth').val();
		var currentDate = getCurrentDate();
		
		var Dob = new Date(dateOfBirth);
		var cDate =  new Date(currentDate);		
		var age;
		
		if(Dob && cDate){
			
			age = cDate.getFullYear() - Dob.getFullYear();
			//console.log('age: ', age);			
		}		
		
		if(age && age >= 21)
		{
			result = true;
		} 
		
		//console.log('before', $('#over21').val())
		
		if(result)
		{		
			$('#over21Yes').prop('checked', true);
			$('#over21Yes').val(true);	
			$('#over21').val(true);	
		} 
		else
		{
			$('#over21No').prop('checked', true);
			$('#over21No').val(false);
			$('#over21').val(false);	
		}
		
		//console.log('after', $('#over21').val())
		
		//return result;
	}
	
	function disableRadioSelectionIfNullDob(){
		
		var dateOfBirth =  $('#dateOfBirth').val();
		
		if(!dateOfBirth){
			$('#over21Yes').prop('disabled', false);
			$('#over21No').prop('disabled', false);
		} else {
			$('#over21Yes').prop('disabled', true);
			$('#over21No').prop('disabled', true);
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

});
