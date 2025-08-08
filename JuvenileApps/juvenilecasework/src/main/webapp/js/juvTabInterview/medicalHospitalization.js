//updated to use the JQuery library
$(document).ready(function(){
	
	var finalMsg;
	var finalNumericMsg;
	var finalInRangeMsg;
	/*if(typeof $("#admisDate")!= "undefined"){
		datePickerSingle( $("#admisDate"),"Admission Date",true);
	}
	if(typeof $("#relesDate")!= "undefined"){
		datePickerSingle( $("#relesDate"),"Release Date",true);
	}*/
	
	$("[name='hospRec.hospitalizationReason']").on('keyup mouseout',function(){
		textLimit($(this),250);
	});
	
	$("#validateCreateFeild").on('click',function(){
		finalMsg="";
		finalNumericMsg="";
		finalInRangeMsg="";
		
		//$("[name='hospRec.admissionDate']").val(Trim($("[name='hospRec.admissionDate']").val()));
		$("[name='hospRec.facilityName']").val(Trim($("[name='hospRec.facilityName']").val()));
		//$("[name='hospRec.releaseDate']").val(Trim($("[name='hospRec.releaseDate']").val()));
		$("[name='hospRec.admittingPhysicianName.lastName']").val(Trim($("[name='hospRec.admittingPhysicianName.lastName']").val()));
		$("[name='hospRec.admittingPhysicianName.firstName']").val(Trim($("[name='hospRec.admittingPhysicianName.firstName']").val()));
		$("[name='hospRec.admittingPhysicianName.middleName']").val(Trim($("[name='hospRec.admittingPhysicianName.middleName']").val()));
		$("[name='hospRec.physicianPhone.areaCode']").val(Trim($("[name='hospRec.physicianPhone.areaCode']").val()));
		$("[name='hospRec.physicianPhone.prefix']").val(Trim($("[name='hospRec.physicianPhone.prefix']").val()));
		$("[name='hospRec.physicianPhone.last4Digit']").val(Trim($("[name='hospRec.physicianPhone.last4Digit']").val()));
		var mydate = new Date();	
		var admissiondate= new Date($("[name='hospRec.admissionDate']").val());
		//var releasedate= new Date($("[name='hospRec.releaseDate']").val());
		var admissionTypeId= $("[name='hospRec.admissionTypeId']").val();
		var facilityName= $("[name='hospRec.facilityName']").val();
		var hospitalizationReason= $("[name='hospRec.hospitalizationReason']").val();
		//var admisDate= $("#admisDate").val();
		//var relesDate=$("#relesDate").val();
		var lastName= $("[name='hospRec.admittingPhysicianName.lastName']").val();
		var firstName= $("[name='hospRec.admittingPhysicianName.firstName']").val();
		var middleName= $("[name='hospRec.admittingPhysicianName.middleName']").val();
		var areaCode=$("[name='hospRec.physicianPhone.areaCode']").val();
		var prefix=$("[name='hospRec.physicianPhone.prefix']").val();
		var last4Digit=$("[name='hospRec.physicianPhone.last4Digit']").val();
		var admitYear = $("#admitYear").val();
		var lengthOfStay = $("#lengthOfStay").val();
		var currentYear = (new Date).getFullYear();
	   
	   //isFieldNotEmpty(admisDate, "Admission Date is required.", "#admisDate"); 
	   isFieldNotEmpty(admissionTypeId, "Admission Type is required.", "#admissionTypeId"); 
	   isFieldNotEmpty(facilityName, "Facility Name is required.", "#facilityName"); 
	   isFieldNotEmpty(hospitalizationReason, "Hospitalization Reason is required.", "#hospitalizationReason");
	   //isFieldNotEmpty(relesDate, "Release Date is required.", "#facilityName"); 
	
	  /* if (admisDate.trim() == ""
		   && admitYear.trim() == "" ){
		   alert("Admit Year is required.");
		   return false;
	   }
	   
	   if ( admisDate.trim() != ""
		   && relesDate.trim() == ""
			   && lengthOfStay.trim() == "" ){
		   alert("Length of Stay is required.");
		   return false;
	   }
	   
	   if ( admisDate.trim() == ""
	   		&&  admitYear.trim() != ""
	   			&& lengthOfStay.trim() == "" ) {
		   alert("Length of Stay is required.");
		   return false;
	   } 
		   
	   if(releasedate < admissiondate)
		{
		   alert("Release date cannot be before Admission date.");
		   $("[name='hospRec.releaseDate']").focus();
	       return false;
		} */
	   
	   if ( admitYear.trim() == "" ){
		   alert("Admit Year is required.");
		   $("#admitYear").focus();
		   return false;
	   }
	   
	   if ( admitYear > currentYear ) {
		   alert("The Admit Year cannot be a future year. Please enter a current or past year.");
		   $("#admitYear").focus();
		   return false;
	   }
	   
	   if ( lengthOfStay.trim() == "" ) {
		   alert("Length of Stay is required.");
		   $("#lengthOfStay").focus();
		   return false;
	   } 
	   
	   if(finalMsg != ""){
			alert(finalMsg);
			return false;
		} 

	 return isHospitalizationInfoAlphaNum(facilityName,hospitalizationReason,lastName,firstName,middleName) && isPhysicianPhoneDetailsValid(areaCode,prefix,last4Digit);
		
		  
	});
	
	function isFieldNotEmpty(field, fieldMsg, domElementId){
		if(field.trim() == ""){
			finalMsg += fieldMsg + "\n";	
		}
		
	}
	
function isHospitalizationInfoAlphaNum(facilityName,hospitalizationReason,lastName,firstName,middleName){
		
		finalMsg="";
		isFieldAlphaNumeric(facilityName,"Facility Name must be alphanumeric.","#facilityName");
		isFieldAlphaNumeric(hospitalizationReason,"Hospitalization Reason must be alphanumeric.","#hospitalizationReason");
		isFieldAlphaNumeric(lastName,"Last Name must be alphanumeric.","#lastName");
		isFieldAlphaNumeric(firstName,"First Name must be alphanumeric.","#firstName");
		isFieldAlphaNumeric(middleName,"Middle Name must be alphanumeric.","#middleName");
		
		if(finalMsg != ""){
			alert(finalMsg);
			return false;
		} 

		return true;
	}
	
function isFieldAlphaNumeric(field,fieldMsg, domElementId){
		
		var letters = /^^[\w\-\s-\.]+$/;
		if(letters.test(field) == false && field!=""){
			finalMsg += fieldMsg + "\n";
		}
	}

function isPhysicianPhoneDetailsValid(areaCode,prefix,last4Digit){
	
	 if ($("[name='hospRec.physicianPhone.areaCode']").val() != "" && $("[name='hospRec.physicianPhone.prefix']").val() == "") {
	      alert("All of Phone number must be entered if there is partial entry.");
	      $("[name='hospRec.physicianPhone.prefix']").focus();
	      return false;
	  	 }
		   if ($("[name='hospRec.physicianPhone.areaCode']").val() != "" && $("[name='hospRec.physicianPhone.last4Digit']").val() == "") {
		      alert("All of Phone number must be entered if there is partial entry.");
		      $("[name='hospRec.physicianPhone.last4Digit']").focus();
		      return false;
		   }
		   if ($("[name='hospRec.physicianPhone.prefix']").val() != "" && $("[name='hospRec.physicianPhone.areaCode']").val() == "") {
		      alert("All of Phone number must be entered if there is partial entry.");
		      $("[name='hospRec.physicianPhone.areaCode']").focus();
		      return false;
		   }
		   if ($("[name='hospRec.physicianPhone.prefix']").val() != "" && $("[name='hospRec.physicianPhone.last4Digit']").val() == "") {
		      alert("All of Phone number must be entered if there is partial entry.");
		      $("[name='hospRec.physicianPhone.last4Digit']").focus();
		      return false;
		   }
		   if ($("[name='hospRec.physicianPhone.last4Digit']").val() != "" && $("[name='hospRec.physicianPhone.areaCode']").val() == "") {
		      alert("All of Phone number must be entered if there is partial entry.");
		      $("[name='hospRec.physicianPhone.areaCode']").focus();
		      return false;
		   }
		   if ($("[name='hospRec.physicianPhone.last4Digit']").val() != "" && $("[name='hospRec.physicianPhone.prefix']").val() == "") {
		      alert("All of Phone number must be entered if there is partial entry.");
		      $("[name='hospRec.physicianPhone.prefix']").focus();
		      return false;
		   }   
		   
		   return isPhoneNumeric(areaCode,prefix,last4Digit) && isPhoneInRange(areaCode,prefix,last4Digit);
}

});



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


