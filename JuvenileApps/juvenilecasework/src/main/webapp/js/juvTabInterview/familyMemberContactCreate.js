//<!-- JavaScript - Contact Check -->
//modified to use JQuery

$(document).ready(function () {
	
	var finalRequiredMsg="";
	var finalNumericMsg="";
	var finalInRangeMsg="";
	
	//formerly function validateContactFields(theForm)
	$("#addContactValidate").on('click',function(){
		finalRequiredMsg="";
		finalNumericMsg="";
		finalInRangeMsg="";
		var theDropDownVal=$("#contactTypeMain").val();
		if(theDropDownVal=="EMAIL"){
			if ($("[name='currentContact.emailContactTypeId']").val() == "") {
			      alert("Email Type selection is required.");
			      $("[name='currentContact.emailContactTypeId']").focus();
			      return false;
			   }
			clearAllValArrays();
			customValRequired('currentContact.emailAddress','Email Address is Required',"");
			customValEmail('currentContact.emailAddress','Invalid e-mail address.',"");
			var theForm = document.juvenileMemberForm;
			return validateCustomStrutsBasedJS(theForm);
		} 
		else if(theDropDownVal=="PHONE"){
			if ($("[name='currentContact.contactTypeId']").val() == "") {
			      alert("Phone Type selection is required.");
			      $("[name='currentContact.contactTypeId']").focus();
			      return false;
			   }
		/*	var theForm = document.juvenileMemberForm;
			return validateJuvenileMemberFormContact(theForm);*/
			

		var areaCode=$("#areaCode").val();
		var prefix=$("#prefix").val();
		var last4Digit=$("#last4Digit").val();
		var ext=$("#ext").val();
		
		return isPhoneRequired(areaCode,prefix,last4Digit) && isPhoneNumeric(areaCode,prefix,last4Digit,ext) && isPhoneInRange(areaCode,prefix,last4Digit);
		
		}
		else{
			alert("The type of contact is required and must be selected");
			return false;
		}
		return true;
	});
			
			
			function isPhoneRequired(areaCode,prefix,last4Digit){		

				 isFieldNotEmpty(areaCode, "Phone Number Area Code is required.", "#areaCode"); 
				 isFieldNotEmpty(prefix, "Phone Number Prefix is required.", "#prefix");
				 isFieldNotEmpty(last4Digit, "Phone Number 4Digits is required.", "#last4Digit");	
				 
				 if(finalRequiredMsg == ""){
						return true;
					}else {
						alert(finalRequiredMsg);
						return false;
					}
				 
			}
			
			function isPhoneNumeric(areaCode,prefix,last4Digit,ext){

				 isFieldNumeric(areaCode, "Phone Number Area Code must be numeric.", "#areaCode"); 
				 isFieldNumeric(prefix, "Phone Number Prefix must be numeric.", "#prefix");
				 isFieldNumeric(last4Digit, "Phone Number 4Digits must be numeric.", "#last4Digit");
				 isFieldNumeric(ext, "Ext must be numeric.", "#ext");
				 
				 if(finalNumericMsg == ""){
						return true;
					}else {
						alert(finalNumericMsg);
						return false;
					}
				 
			}
			
			function isPhoneInRange(areaCode,prefix,last4Digit){
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
				regEx=/^[0-9]*$/;
				if(field != ""){
					 if(isNaN(field) || regEx.test(field) == false){
						 finalNumericMsg += fieldMsg + "\n";
					}
				}
				
			/*	return true;*/
				
			}
			
			function isFieldNotEmpty(field, fieldMsg, domElementId){
				if(field == "" || field === null || field === undefined){	
					finalRequiredMsg += fieldMsg + "\n";	
				}
				/*return true;*/
			}
			
			function isFieldInRange(field, fieldMsg, range, domElementId ) {
				if(field.length<range){
					finalInRangeMsg += fieldMsg + "\n";
				}
			/*	return true;*/
			}
			


	//formerly function showType(theDropDownVal){
	$("#contactTypeMain").on('change', function(){
		$("#phone1").hide();
		$("#phone2").hide();
		$("#email1").hide();
		$("#email2").hide();
		if($("#contactTypeMain").val()=="EMAIL"){
			resetContactValues(false);
			$("#email1").show();
			$("#email2").show();
		} 
		else if($("#contactTypeMain").val()=="PHONE"){
			resetContactValues(true);
			$("#phone1").show();
			$("#phone2").show();
		}
	});

	//formerly function populateUnknownPhone(el, checkboxName)
	$("[name='unknownPhone']").on('click', function(){
		if($(this).prop('checked') == true)
		{
			$("[name='currentContact.contactPhoneNumber.areaCode']").val("000");
			$("[name='currentContact.contactPhoneNumber.prefix']").val("000");
			$("[name='currentContact.contactPhoneNumber.last4Digit']").val("0000");
		}
		else{
			$("[name='currentContact.contactPhoneNumber.areaCode']").val("");
			$("[name='currentContact.contactPhoneNumber.prefix']").val("");
			$("[name='currentContact.contactPhoneNumber.last4Digit']").val("");
			$("[name='currentContact.contactPhoneNumber.ext']").val("");
		}
	});

	
	function resetContactValues(isPhone){
		if(isPhone){
			$("[name='currentContact.emailContactTypeId']").val("");
			$("[name='currentContact.emailAddress']").val("");
		}
		else{
			$("[name='currentContact.contactTypeId']").val("");
			$("[name='currentContact.contactPhoneNumber.areaCode']").val("");
			$("[name='currentContact.contactPhoneNumber.prefix']").val("");
			$("[name='currentContact.contactPhoneNumber.last4Digit']").val("");
			$("[name='currentContact.contactPhoneNumber.ext']").val("");
		}
	}
	
});