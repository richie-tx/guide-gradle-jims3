function validateHospitalization(thisForm) 
{	
	var msg = "";
	
	if(validateNewHospForm(thisForm))
	{
		
	}
	else{
	 return false;
	 }
	//trim(thisForm['hospRec.facilityName']);
	 while (thisForm['hospRec.facilityName'].value.substring(0,1) == " ") {
      thisForm['hospRec.facilityName'].value = thisForm['hospRec.facilityName'].value.substring(1);
    }
	 while (thisForm['hospRec.admissionDate'].value.substring(0,1) == " ") {
      thisForm['hospRec.admissionDate'].value = thisForm['hospRec.admissionDate'].value.substring(1);
    }
     while (thisForm['hospRec.releaseDate'].value.substring(0,1) == " ") {
      thisForm['hospRec.releaseDate'].value = thisForm['hospRec.releaseDate'].value.substring(1);
    }
     while (thisForm['hospRec.hospitalizationReason'].value.substring(0,1) == " ") {
      thisForm['hospRec.hospitalizationReason'].value = thisForm['hospRec.hospitalizationReason'].value.substring(1);
    }
   
	
	
	// 28 nov 2006 - mjt - following validation added
	
	if( thisForm['hospRec.facilityName'].value == "" )
	{
		alert("Facility Name is required.\n");
		thisForm['hospRec.facilityName'].focus();
		return false;
	}
	
/*
	if(thisForm['hospRec.admissionDate'].value == "")
	{
		alert("Admission Date is required.\n");
		thisForm['hospRec.admissionDate'].focus();
		return false;
	}
	*/
/*	else
	{
		 var dValues = thisForm['hospRec.admissionDate'].value.split('/');	
		var myDate= dValues[0]+ '/' +1 + '/' + dValues[1];
		result = validateDate(myDate);					
			if (result == false){
				alert("Admission Date is invalid date or wrong date format.");
				thisForm['hospRec.admissionDate'].focus();
				return false;
		
	   }	
	}*/
/*	
	if(thisForm['hospRec.releaseDate'].value == "")
	{
		alert("Release Date is required.\n");
		thisForm['hospRec.releaseDate'].focus();
		return false;
	}
	*/
/*	else
	{
		 var dValues = thisForm['hospRec.releaseDate'].value.split('/');	
		var myDate= dValues[0]+ '/' +1 + '/' + dValues[1];
		result = validateDate(myDate);					
			if (result == false){
				alert("Release Date is invalid date or wrong date format.");
				thisForm['hospRec.releaseDate'].focus();
				return false;
		
	   }	
	}*/

	if(thisForm['hospRec.hospitalizationReason'].value == "")
	{
		alert("Hospitalization reason is required.\n");
		thisForm['hospRec.hospitalizationReason'].focus();
		return false;
	}
	if(thisForm['hospRec.admissionTypeCd'].selectedIndex <= 0)
	{
		alert("Admission Type is required.\n");
		thisForm['hospRec.admissionTypeCd'].focus();
		return false;
	}

	 if (thisForm["hospRec.physicianPhone.areaCode"].value != "" && thisForm["hospRec.physicianPhone.prefix"].value == "") {
      alert("All of Phone number must be entered if there is partial entry.");
      thisForm["hospRec.physicianPhone.prefix"].focus();
      return false;
  	 }
	   if (thisForm["hospRec.physicianPhone.areaCode"].value != "" && thisForm["hospRec.physicianPhone.last4Digit"].value == "") {
	      alert("All of Phone number must be entered if there is partial entry.");
	      thisForm["hospRec.physicianPhone.last4Digit"].focus();
	      return false;
	   }
	   if (thisForm["hospRec.physicianPhone.prefix"].value != "" && thisForm["hospRec.physicianPhone.areaCode"].value == "") {
	      alert("All of Phone number must be entered if there is partial entry.");
	      thisForm["hospRec.physicianPhone.areaCode"].focus();
	      return false;
	   }
	   if (thisForm["hospRec.physicianPhone.prefix"].value != "" && thisForm["hospRec.physicianPhone.last4Digit"].value == "") {
	      alert("All of Phone number must be entered if there is partial entry.");
	      thisForm["hospRec.physicianPhone.last4Digit"].focus();
	      return false;
	   }
	   if (thisForm["hospRec.physicianPhone.last4Digit"].value != "" && thisForm["hospRec.physicianPhone.areaCode"].value == "") {
	      alert("All of Phone number must be entered if there is partial entry.");
	      thisForm["hospRec.physicianPhone.areaCode"].focus();
	      return false;
	   }
	   if (thisForm["hospRec.physicianPhone.last4Digit"].value != "" && thisForm["hospRec.physicianPhone.prefix"].value == "") {
	      alert("All of Phone number must be entered if there is partial entry.");
	      thisForm["hospRec.physicianPhone.prefix"].focus();
	      return false;
	   }
	   
    
}	



	/*
	if(compDate1GreaterEqualDate2(thisForm['hospRec.admissionDate'].value,thisForm['hospRec.releaseDate'].value))
		{
		   alert("Release date cannot be before Admission date.");
	     thisForm['hospRec.releaseDate'].focus();
	       return false;
		}
	

/*function validateDate(fldValue){
	
	var regDate = /^\d{1,2}\/\d{1,2}\/\d{4}$/;;
	var result = regDate.test(fldValue,regDate);	
	if (result == false){
		return false;
	}  
	
	var dValues = fldValue.split('/');
	var mon = dValues[0];
	var day = dValues[1];
	var leapYear = 0;
	if (mon > 12 || mon == 0){
		return false;
	}
	
	if (day > 31 || day == 0){
		return false;
	}	
	if (mon == 4 || mon == 6 || mon == 9 || mon == 11){
		if (day > 30){
			return false;
		}	
	}
	if (mon == 2){
		leapYear = dValues[2] %4;
		if (leapYear == 0 && day > 29){
			return false;
		}
		if (leapYear > 0  && day > 28){
			return false;
		}	
	}
	return true;
}*/

	
