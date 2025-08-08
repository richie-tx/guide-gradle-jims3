//used with pages belonging to the Medical tab under the Juvenile Master tab
$(document).ready(function(){
	
	var finalMsg;
	var finalNumericMsg;
	var finalInRangeMsg;
	$("[name='medicRec.medicationReason'],[name='hsRec.modificationReason'],[name='medicRec.modificationReason']").on('keyup mouseout',function(){
		textLimit($(this),250);
	});
	
	//interviewInfoMedical.jsp
	//-------------------------------------------
	if(document.title == "JIMS2 Juvenile Casework - interviewInfoMedical.jsp")
	{
		if( fromMedication == 'true')
	 	{
			showHideMulti('Medication', 'pMedication', 2, '/JuvenileCasework/');
		} 
	 	
	 	if( fromHealthIssue == 'true')
	 	{
			showHideMulti('Health', 'pHealth', 2, '/JuvenileCasework/');  
		} 
	 	
	 	if( fromHospital == 'true')
	 	{
	 		showHideMulti('Hospitalization', 'pHospitalization', 2, '/JuvenileCasework/')
		} 
	 	if ($("#expandTriatsId").val() != 0 || fromTraits=='true')
	 	{	
	 		showHideMulti('medical', 'pMedical', 4, '/JuvenileCasework/');
	 	} 
	 	/* remains expanded if the page loads from the pagination not from the action DEFECT JIMS200076236*/
	 	else if( fromAction != 'true' && fromMedication != 'true' && fromHealthIssue != 'true' && fromHospital != 'true')
	 	{
			showHideMulti('medical', 'pMedical', 4, '/JuvenileCasework/');
		} 
	 	$("input[name='selectedValue']:checked").prop('checked',false);
	}
	
	$("#uptmed,#upthlth").on('click',function(){
		return disableSubmitButtonCasework($(this));
	});
	
	$("[name='selectedValue']").on('click',function(){
		var sect = $(this).data('sect');
		if(sect == 'medical'){
			var btn = $("#uptmed");
		}
		else if(sect == 'health'){
			var btn = $("#upthlth");
		}
		else{
			var btn = null;
		}
		if (btn == null)
		{
			btn = $("#utsbtn2");
		}	
		$(btn).prop('disabled',false);
	});
	

	//interviewInfoMedicalHealthIssuesCreate.jsp
	//-------------------------------------------
	$("#healthIssuesNext").on('click',function(){
		var issueId = $("[name='hsRec.issueId']");
		if ($(issueId).val() == "") {
	      alert("Issue selection is required.");
	      $(issueId).focus();
	      return false;
		}
		var issueStatusId = $("[name='hsRec.issueStatusId']");
		if ($(issueStatusId).val() == "") {
	      alert("Issue status selection is required.");
	      $(issueStatusId).focus();
	      return false;
		}
		var healthStatusId = $("[name='hsRec.healthStatusId']");
		if ($(healthStatusId).val() == "") {
		      alert("Status selection is required.");
		      $(healthStatusId).focus();
		      return false;
		} 
	});
	
	$("#healthIssuesUpdate").on('click',function(){
		var modificationReason = $("[name='hsRec.modificationReason']");
		var modificationReasonVal = $(modificationReason).val();
		if(modificationReasonVal == "")
		{
			 alert("Modification Reason is required.");
			 $(modificationReason).focus();
			 return false;	  
		}
	});
	
	//interviewInfoMedicalMedicationCreate.jsp
	//-------------------------------------------------
	$("#medicalInfoValidate").on('click',function(){
	 
		finalMsg="";
		finalNumericMsg="";
		finalInRangeMsg="";
		var myForm = document.forms[0];
		var confirm = $("[name='medicRec.medication']").val()	
		if(confirm == "")
		{
			 alert("Medication is required.");
			 $("[name='medicRec.medCode']").focus();
			 return false;	  
		}
		
		/*var actionType = document.getElementById("medicRec.action");*/
		var actionType = $("[name='medicRec.action']").val();
		var lastName=$("[name='medicRec.physicianName.lastName']").val();
		var firstName=$("[name='medicRec.physicianName.firstName']").val();
		var middleName=$("[name='medicRec.physicianName.middleName']").val();
		var areaCode=$("[name='medicRec.physicianPhone.areaCode']").val().trim();
		var prefix=$("[name='medicRec.physicianPhone.prefix']").val().trim();
		var last4Digit=$("[name='medicRec.physicianPhone.last4Digit']").val().trim();
		
		
		//alert(actionType);
		var medicReason = $("[name='medicRec.medicationReason']").val();
		/*var confirmCurrentlyMediVal = confirmCurrentlyMedi.value;*/
		var isAlphaNum = true;
		if(medicReason != null && medicReason != ""){
			if(actionType != null && actionType != "" && actionType == "create"){
				isAlphaNum = isAlphaNumeric(medicReason, "create");
			}else if( actionType == "update"){
				isAlphaNum = isAlphaNumeric(medicReason,"update");
			}
		}
		if(isAlphaNum == false){
			alert("Medication reason must be alpha numeric");
			$("[name='medicRec.medicationReason']").focus();
			return false;
		}
		
		
		
	 	//alert("sruti testing");
	 	var confirmCurrentlyMedi = $("[name='medicRec.currentlyTakingMedId");
		var confirmCurrentlyMediVal = $(confirmCurrentlyMedi).val();
		//alert(confirmCurrentlyMediVal);
		if(confirmCurrentlyMediVal == "")
		{
			 alert("Currently taking Medication is required.");
			 $("[name='medicRec.currentlyTakingMedId']").focus();
	     return false;	  
		} 
		
		$("[name='medicRec.physicianName.lastName']").val(Trim($("[name='medicRec.physicianName.lastName']").val()));
		$("[name='medicRec.physicianName.firstName']").val(Trim($("[name='medicRec.physicianName.firstName']").val()));
		$("[name='medicRec.physicianName.middleName']").val(Trim($("[name='medicRec.physicianName.middleName']").val()));
		$("[name='medicRec.physicianPhone.areaCode']").val(Trim($("[name='medicRec.physicianPhone.areaCode']").val()));
		$("[name='medicRec.physicianPhone.prefix']").val(Trim($("[name='medicRec.physicianPhone.prefix']").val()));
		$("[name='medicRec.physicianPhone.last4Digit']").val(Trim($("[name='medicRec.physicianPhone.last4Digit']").val()));
		 
		
		/*return validateMedicationForm(myForm);*/  
		
		return isMedicalInfoAlphaNum(lastName,firstName,middleName) && isMedicalPhoneDetailsValid(areaCode,prefix,last4Digit);
	});
	
	function isMedicalInfoAlphaNum(lastName,firstName,middleName){
		finalMsg="";
		isFieldAlphaNum(lastName,"Last Name must be alphanumeric.", "#lastName");
		isFieldAlphaNum(firstName,"First Name must be alphanumeric.", "#firstName");	
		isFieldAlphaNum(middleName,"Middle Name must be alphanumeric.", "#middleName");	
		
		if(finalMsg != ""){
			alert(finalMsg);
			return false;
		} 

		return true;
	}
	
	
	function isMedicalPhoneDetailsValid(areaCode,prefix,last4Digit){
		
		if ($("[name='medicRec.physicianPhone.areaCode']").val() != "" && $("[name='medicRec.physicianPhone.prefix']").val() == "") {
		      alert("All of Phone number must be entered if there is partial entry.");
		      $("[name='medicRec.physicianPhone.prefix']").focus();
		      return false;
		   }
		   if ($("[name='medicRec.physicianPhone.areaCode']").val() != "" && $("[name='medicRec.physicianPhone.last4Digit']").val() == "") {
		      alert("All of Phone number must be entered if there is partial entry.");
		      $("[name='medicRec.physicianPhone.last4Digit']").focus();
		      return false;
		   }
		   if ($("[name='medicRec.physicianPhone.prefix']").val() != "" && $("[name='medicRec.physicianPhone.areaCode']").val() == "") {
		      alert("All of Phone number must be entered if there is partial entry.");
		      $("[name='medicRec.physicianPhone.areaCode']").focus();
		      return false;
		   }
		   if ($("[name='medicRec.physicianPhone.prefix']").val() != "" && $("[name='medicRec.physicianPhone.last4Digit']").val() == "") {
		      alert("All of Phone number must be entered if there is partial entry.");
		      $("[name='medicRec.physicianPhone.last4Digit']").focus();
		      return false;
		   }
		   if ($("[name='medicRec.physicianPhone.last4Digit']").val() != "" && $("[name='medicRec.physicianPhone.areaCode']").val() == "") {
		      alert("All of Phone number must be entered if there is partial entry.");
		      $("[name='medicRec.physicianPhone.areaCode']").focus();
		      return false;
		   }
		   if ($("[name='medicRec.physicianPhone.last4Digit']").val() != "" && $("[name='medicRec.physicianPhone.prefix']").val() == "") {
		      alert("All of Phone number must be entered if there is partial entry.");
		      $("[name='medicRec.physicianPhone.prefix']").focus();
		      return false;
		   }   
		   
		   return isPhoneNumeric(areaCode,prefix,last4Digit) && isPhoneInRange(areaCode,prefix,last4Digit);
		
	}
	
	function isAlphaNumeric(inputStr, actionType)
	{
		var textRegExp = /^[a-zA-Z0-9][a-zA-Z0-9 \.\\(),\n\r\x27\x3B\x26\x2f\-]*$/;
		if(actionType == "create"){
			/*^[a-zA-Z0-9][a-zA-Z0-9 \n\/\.\\';()\x26\-]*$*/	   
		    /*var format=inputStr.match(/^[a-zA-Z0-9!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]*$/g);*/
		    if (textRegExp.test(inputStr,textRegExp) == false) {
		    //if(format) {	    
		      	return false;
		    }else {	    	
		      	return true;
		    } 
		}else if(actionType == "update"){		
			var returnVal = true;
			if(inputStr.indexOf("]") != -1){			
				var inputStrLength = inputStr.length;
				//alert("inputStrLength :"+inputStrLength)
				var index = inputStr.indexOf("]");
				//alert("index :"+index);
				if(inputStrLength > index+1){
					//user has updated value
					var modifiedStr = inputStr.substring(index+1, inputStrLength);
					//alert("modified >----"+modifiedStr+"---");
					if (textRegExp.test(modifiedStr,textRegExp) == false) {
					    return false;
				    }				
				}
			}
			return returnVal;
		} 	
	 }
	
	function isFieldAlphaNum(field,fieldMsg, domElementId){
			
			var letters = /^[0-9a-zA-Z]+$/;
			if(letters.test(field) == false && field != ""){
				finalMsg += fieldMsg + "\n";
			}
		}
	
	function isPhoneNumeric(areaCode,prefix,last4Digit){
		finalNumericMsg="";
		 isFieldNumeric(areaCode, "Phone Number Area Code must be numeric.", "#areaCode"); 
		 isFieldNumeric(prefix, "Phone Number Prefix must be numeric.", "#prefix");
		 isFieldNumeric(last4Digit, "Phone Number 4Digits must be numeric.", "#last4Digit");
		 
		 if(finalNumericMsg == ""){
				return true;
			}else {
				alert(finalNumericMsg);
				return false;
			}
		 
	}

	function isPhoneInRange(areaCode,prefix,last4Digit){
		finalInRangeMsg="";
		 isFieldInRange(areaCode, "Phone Number Area Code cannot be less than 3 characters", 3 , "#areaCode");
		 isFieldInRange(prefix, "Phone Number Prefix cannot be less than 3 characters", 3 , "#prefix"); 
		 isFieldInRange(last4Digit, "Phone Number 4Digits cannot be less than 4 characters", 4 , "#last4Digit");
		 
		 if(finalInRangeMsg == ""){
				return true;
			}else {
				alert(finalInRangeMsg);
				return false;
			}
	}
	
	function isFieldNumeric(field, fieldMsg, domElementId){
		 if(isNaN(field) && field!=""){
			 finalNumericMsg += fieldMsg + "\n";
		}
	/*	return true;*/
		
	}


	function isFieldInRange(field, fieldMsg, range, domElementId ) {
		if(field.length<range && field!=""){
			finalInRangeMsg += fieldMsg + "\n";
		}
	/*	return true;*/
	}

	
	
	$("#validateMedCode").on('click',function(){
		$("[name='medicRec.medCode']").val(Trim($("[name='medicRec.medCode']").val()));
		if($("[name='medicRec.medCode']").val() == "")
		{
	  	alert("Medication code has to be provided for validation.");
	  	$("[name='medicRec.medCode']").focus();		    
	    return false;
		}
	 	return true;
	});
	
	$("#validateMedUpdate").on('click',function(){
		var modificationReason = $("[name='medicRec.modificationReason']");
		var modificationReasonVal = $(modificationReason).val();
		//alert(modificationReasonVal);
		if(modificationReasonVal == "")
		{
			 alert("Modification Reason is required.");
			 $(modificationReason).focus();
	     	return false;	  
		}
	});
	
	$("#searchMedication").on('click',function(){
		changeFormActionURL($(this).data("href") , true);
	});
	
	//medicationSearch.jsp
	//-------------------------------------------------
	if(document.title == "JIMS2 Juvenile Casework - medicationSearch.jsp")
	{
		var rbs = $("[name='selectedMed']");
		if (rbs.length == 1){
			$(rbs).prop('checked',true);
			$("#select").show();
			$("#noselect").hide();
		}
		$("input[name='selectedMed']:checked").prop('checked',false);
	}
	
	//function displaySelectButton()
	$("[name='selectedMed']").on('click',function(){
		//alert(document.getElementById("select").class);
		$("#select").show();
		$("#noselect").hide(); 		
	});
	
	$("#findMedication").on('click',function(){
		var medication = $("[name='searchMedication.medication']").val();
		var delivery = $("[name='searchMedication.delivery']").val();
		var strength = $("[name='searchMedication.strength']").val();
		var medicationCode = $("[name='searchMedication.medCode']").val();
		var usage = $("[name='searchMedication.usage']").val();
		if(medication=="" && delivery=="" && strength==""
		 && medicationCode=="" && usage=="")
		 {
		 	alert("At least one field is required for search");
		 	$("[name='searchMedication.medication']").focus;
		 	return false;
		}
		
		if(medication.indexOf("%") != -1){
			alert('Invalid Medication.Please use * for partial search');
			return false;
		}
		if(delivery.indexOf("%") != -1){
			alert('Invalid Delivery.Please use * for partial search');
			return false;
		}
		if(strength.indexOf("%") != -1){
			alert('Invalid Strength.Please use * for partial search');
			return false;
		}
		if(medicationCode.indexOf("%") != -1){
			alert('Invalid Medication Code.Please use * for partial search');
			return false;
		}
		if(usage.indexOf("%") != -1){
			alert('Invalid Usage.Please use * for partial search');
			return false;
		}
		return true;
	});
	
	
});