<!-- JavaScript for departmentCreate.jsp -->

function getIndex(input) {
   var index = -1;
   var i = 0;
   var found = false;
   while (i < input.form.length && index == -1)
   if (input.form[i] == input)index = i;
     else i++;
   return index;
}
function validateDepartmentCreateFields(theForm){
	var deptName = Trim(theForm.departmentName.value);
	var deptCode = Trim(theForm.departmentId.value);
	var orgCode = Trim(theForm.orgCode.value);
	theForm.departmentName.value = deptName;
	theForm.departmentId.value = deptCode;
	theForm.orgCode.value = orgCode;
	if (deptName == ""){
		alert("Department Name is required.");
		theForm.departmentName.focus();
		return false;
	}
	if (deptName.length < 4){
		alert("Department Name must be at least 4 characters.");
		theForm.departmentName.focus();
		return false;	
	}
	var deptNameRegex = /^[a-zA-Z0-9/ \\.,'()\-\x26]*$/;
	if(deptNameRegex.test(deptName) == false){
		alert("Department Name contains invalid character value.");
		theForm.departmentName.focus();		
		return false;
	}
	if (deptCode == ""){
		alert("Department Code is required.");
		theForm.departmentId.focus();
		return false;
	}
	if (deptCode.length < 3){
		alert("Department Code must be 3 characters.");
		theForm.departmentId.focus();
		return false;	
	}
	var deptCodeRegex = /^[a-zA-Z0-9]*$/;	
	if(deptCodeRegex.test(deptCode) == false){
		alert("Department Code must be alphanumeric value.");
		theForm.departmentId.focus();		
		return false;
	}
	if (orgCode == ""){
		alert("Organization Code is required.");
		theForm.orgCode.focus();
		return false;
	}
	if (orgCode.length < 3){
		alert("Organization Code must be 3 characters.");
		theForm.orgCode.focus();
		return false;	
	}   	
	var orgCodeRegex = /^[a-zA-Z0-9]*$/;
	if(orgCodeRegex.test(orgCode) == false){
		alert("Organization Code must be alphanumeric value.");
		theForm.orgCode.focus();		
		return false;
	}
   if(!validateAddress("physicalAddress.streetNumber","physicalAddress.streetName","physicalAddress.city","physicalAddress.stateId","physicalAddress.zipCode")){
   		alert("Physical Address is incomplete and must have street number, street name, city, state, and zip at minimum.");
   		return false;
   }		
   if(!validateAddress("mailingAddress.streetNumber","mailingAddress.streetName","mailingAddress.city","mailingAddress.stateId","mailingAddress.zipCode")){
   		alert("Mailing Address is incomplete and must have street number, street name, city, state, and zip at minimum.");
   		return false;
   }
   if (theForm.agencyTypeId.value.toUpperCase() == "N")
   {
	   if(!validateAddress("setcicBillingAddress.streetNumber","setcicBillingAddress.streetName","setcicBillingAddress.city","setcicBillingAddress.stateId","setcicBillingAddress.zipCode")){
   			alert("Setcic Billing Address is incomplete and must have street number, street name, city, state, and zip at minimum.");
   			return false;
   		}	
   }
   if(!(document.getElementById("mailingAddress.city").value>"") && !(document.getElementById("physicalAddress.city").value>"")){
   		alert("At least one complete Physical or Mailing Address is required.");
   		return false;
   }
   if (document.getElementById("departmentFaxNumber.areaCode").value > "" && document.getElementById("departmentFaxNumber.prefix").value == "") {
      alert("Fax Prefix must be entered if Fax Area Code is entered.");
      document.getElementById("departmentFaxNumber.prefix").focus();
      return false;
   }
   if (document.getElementById("departmentFaxNumber.areaCode").value == "" && document.getElementById("departmentFaxNumber.prefix").value > "") {
      alert("Fax Area Code must be entered if Fax Prefix is entered.");
      document.getElementById("departmentFaxNumber.areaCode").focus();
      return false;
   }
   if (document.getElementById("departmentFaxNumber.prefix").value > "" && document.getElementById("departmentFaxNumber.last4Digit").value == "") {
      alert("Fax Main number must be entered if Fax Prefix is entered.");
      document.getElementById("departmentFaxNumber.last4Digit").focus();
      return false;
   }
   if (document.getElementById("departmentFaxNumber.last4Digit").value > "" && document.getElementById("departmentFaxNumber.prefix").value == "") {
      alert("Fax Prefix must be entered if Fax Main number is entered.");
      document.getElementById("departmentFaxNumber.prefix").focus();
      return false;
   }
   if (theForm.statusId.value == "I" && theForm.terminationDate.value == "") {
          alert("JIMS Inactive Date is required if Department Status is Inactive.");
          theForm.terminationDate.focus();
          return false;
   }
   if (theForm.statusId.value != "I" && theForm.terminationDate.value > "") {
      alert("If JIMS Inactive Date is entered then Department Status must be Inactive.");
      theForm.terminationDate.focus();
      return false;
   }
   var curDate = new Date();
   var today = Date.UTC(curDate.getFullYear(), curDate.getMonth()+1, curDate.getDate(),0,0,0);
   var tDate_Year = "";
   var tDate_Month = "";
   var tdate_Day = "";
   var inputDate = "";

   var checkDate = Trim(document.forms[0].activationDate.value);
   if (checkDate > ""){
		result = validateDate(checkDate);
		if (result == false){
			alert("JIMS Activation Date is invalid date or wrong date format.");
			theForm.activationDate.focus();
			return false;
		}  
   }

   checkDate = Trim(document.forms[0].terminationDate.value);
   if (checkDate > ""){
	    result = validateDate(checkDate);
		if (result == false){
			alert("JIMS Inactive Date is invalid date or wrong date format.");
			theForm.terminationDate.focus();
			return false;		
		 }
		 dateValues = checkDate.split('/');
		 tDate_Month = dateValues[0];
		 tDate_Day = dateValues[1];
		 tDate_Year = dateValues[2];
		 inputDate = Date.UTC( tDate_Year, tDate_Month , tDate_Day,0,0,0);
		 if ( inputDate > today ){
			  alert("JIMS Inactive Date cannot be a future date.");
			  theForm.terminationDate.focus();
			  return false;
   		 }
   	}	 
 
   if (theForm.accessTypeId.value == "P" && theForm.agencyTypeId.value.toUpperCase() == "S") {
      if (theForm.subscriberCivilTerminationDate.value > "" || theForm.subscriberCriminalTerminationDate.value > "") {
         var answer=confirm("If Civil Inactive Date or Criminal Inactive Date is entered, please check the Department Status.");
         theForm.subscriberCivilTerminationDate.focus();
         if (answer == true){ 
            return false;
         }
       }        
   }

// subscriber date edits only for subscribers      
   if (theForm.agencyTypeId.value.toUpperCase() == "S"){
	   checkDate = Trim(document.forms[0].subscriberCivilActivationDate.value);
	   if (checkDate > ""){
			result = validateDate(checkDate);
			if (result == false){
				alert("Subscriber Civil Activation Date is invalid date or wrong date format.");
				theForm.subscriberCivilActivationDate.focus();
			    return false;			
			}  
	   }
   
	   checkDate = Trim(document.forms[0].subscriberCriminalActivationDate.value);
   		if (checkDate > ""){
			result = validateDate(checkDate);
			if (result == false){
				alert("Subscriber Criminal Activation Date is invalid date or wrong date format.");
				theForm.subscriberCriminalActivationDate.focus();
		    	return false;			
			} 
   		}   

	   checkDate = Trim(document.forms[0].subscriberCivilTerminationDate.value);
	   if (checkDate > ""){
			result = validateDate(checkDate);
			if (result == false){
				alert("Civil Inactive Date is invalid date or wrong date format.");
				theForm.subscriberCivilTerminationDate.focus();
			    return false;			
			} 
		    dateValues = checkDate.split('/');
			tDate_Month = dateValues[0];
			tDate_Day = dateValues[1];
			tDate_Year = dateValues[2];
		    inputDate = Date.UTC( tDate_Year, tDate_Month , tDate_Day,0,0,0);
		    if ( inputDate > today ){
			   alert("Civil Inactive Date cannot be a future date.");
			   theForm.subscriberCivilTerminationDate.focus();
			   return false;
		    }
		}    
   
   		checkDate = Trim(document.forms[0].subscriberCriminalTerminationDate.value);
	    if (checkDate > ""){
			result = validateDate(checkDate);
			if (result == false){
				alert("Criminal Inactive Date is invalid date or wrong date format.");
				theForm.subscriberCriminalTerminationDate.focus();
			    return false;
			}   
		    dateValues = checkDate.split('/');
			tDate_Month = dateValues[0];
			tDate_Day = dateValues[1];
			tDate_Year = dateValues[2];
		    inputDate = Date.UTC( tDate_Year, tDate_Month , tDate_Day,0,0,0);
		    if ( inputDate > today ){
			   alert("Criminal Inactive Date cannot be a future date.");
			   theForm.subscriberCriminalTerminationDate.focus();
		   	   return false;
		    } 
    	} 
    } 
// SETCIC edits for non-subscribers only   
   if (theForm.agencyTypeId.value.toUpperCase() == "N") 
   {    
	   if (theForm.setcicAccessId.value == "I" || theForm.setcicAccessId.value == "F") {
    	   if (document.getElementById("setcicContactName.lastName").value == "") {
        	  alert("Last name is required if SETCIC Access is Inquiry or Full");
	          document.getElementById("setcicContactName.lastName").focus();
    	      return false;
	       }
    	   if (document.getElementById("setcicContactName.firstName").value == "") {
        	  alert("First name is required if SETCIC Access is Inquiry or Full");
	          document.getElementById("setcicContactName.firstName").focus();
    	      return false;
	       }
    	   if (document.getElementById("setcicPhoneNumber.areaCode").value == "" && document.getElementById("setcicPhoneNumber.prefix").value == "") {
        	  alert("Contact Phone is required if SETCIC Access is Inquiry or Full");
	          document.getElementById("setcicPhoneNumber.areaCode").focus();
    	      return false;
	       }
    	   if (document.getElementById("setcicPhoneNumber.areaCode").value > "" && document.getElementById("setcicPhoneNumber.prefix").value == "") {
        	  alert("Contact Phone Prefix must be entered if Contact Phone Area Code is entered.");
	          document.getElementById("setcicPhoneNumber.prefix").focus();
    	      return false;
	       }
	       if (document.getElementById("setcicPhoneNumber.areaCode").value == "" && document.getElementById("setcicPhoneNumber.prefix").value > "") {
    	      alert("Contact Area Code must be entered if Contact Prefix is entered.");
        	  document.getElementById("setcicPhoneNumber.areaCode").focus();
	          return false;
    	   }
	       if (document.getElementById("setcicPhoneNumber.prefix").value > "" && document.getElementById("setcicPhoneNumber.last4Digit").value == "") {
    	      alert("Contact Phone Main number must be entered if Contact Phone Prefix is entered.");
        	  document.getElementById("setcicPhoneNumber.last4Digit").focus();
	          return false;
    	   }
	       if (document.getElementById("setcicPhoneNumber.prefix").value == "" && document.getElementById("setcicPhoneNumber.last4Digit").value > "") {
    	      alert("Contact Phone Prefix must be entered if Contact Phone Main number is entered.");
        	  document.getElementById("setcicPhoneNumber.prefix").focus();
	          return false;
    	   }
	       if (document.getElementById("setcicPhoneNumber.ext").value > "" && document.getElementById("setcicPhoneNumber.last4Digit").value == "") {
		      alert("Contact Phone Number must be entered if Contact Phone Extension is entered.");
	    	  document.getElementById("setcicPhoneNumber.areaCode").focus();
		      return false;
		   }
  	   }

	   if (theForm.setcicAccessId.value == "F") {
    	   if (document.getElementById("warrantConfPhoneNumber.areaCode").value == "" && document.getElementById("warrantConfPhoneNumber.prefix").value == "") {
        	  alert("Warrant Contact Phone is required if SETCIC Access is Full");
	          document.getElementById("warrantConfPhoneNumber.areaCode").focus();
    	      return false;
	       }
		   if (document.getElementById("warrantConfPhoneNumber.areaCode").value > "" && document.getElementById("warrantConfPhoneNumber.prefix").value == "") {
	    	  alert("Warrant Contact Phone Prefix must be entered if Warrant Contact Phone Area Code is entered.");
		      document.getElementById("warrantConfPhoneNumber.prefix").focus();
		      return false;
		   }
		   if (document.getElementById("warrantConfPhoneNumber.areaCode").value == "" && document.getElementById("warrantConfPhoneNumber.prefix").value > "") {
        	  alert("Warrant Contact Area Code must be entered if Warrant Contact Prefix is entered.");
	          document.getElementById("warrantConfPhoneNumber.areaCode").focus();
    	      return false;
	       }
		   if (document.getElementById("warrantConfPhoneNumber.prefix").value > "" && document.getElementById("warrantConfPhoneNumber.last4Digit").value == "") {
	    	  alert("Warrant Contact Phone Main number must be entered if Warrant Contact Phone Prefix is entered.");
		      document.getElementById("warrantConfPhoneNumber.last4Digit").focus();
		      return false;
		   }
		   if (document.getElementById("warrantConfPhoneNumber.prefix").value == "" && document.getElementById("warrantConfPhoneNumber.last4Digit").value > "") {
	    	  alert("Warrant Contact must be entered if Warrant Contact Phone Main number is entered.");
		      document.getElementById("warrantConfPhoneNumber.prefix").focus();
		      return false;
		   }
		   if (document.getElementById("warrantConfPhoneNumber.ext").value > "" && document.getElementById("warrantConfPhoneNumber.last4Digit").value == "") {
	    	  alert("Warrant Contact Phone Number must be entered if Warrant Contact Phone Extension is entered.");
		      document.getElementById("warrantConfPhoneNumber.areaCode").focus();
		      return false;
		   }
	   }
   	   if (theForm.setcicAccessId.value == "I" || theForm.setcicAccessId.value == "F") {
       		if (theForm.setcicDate.value == "") {
          		alert("Active Date is required if SETCIC access is inquiry or full");
	            theForm.setcicDate.focus();
      		    return false;
	        }   
   		}
		if (theForm.statusId.value == "A" && theForm.setcicInactiveDate.value > "") {
		      alert("If SETCIC Inactive Date is entered then Department Status must be Inactive.");
		      theForm.setcicInactiveDate.focus();
		      return false;
		}

     	checkDate = Trim(document.forms[0].setcicDate.value);
     	if (checkDate > ""){
			result = validateDate(checkDate);
			if (result == false){
			    alert("SETCIC Active Date is invalid date or wrong date format.");
				theForm.setcicDate.focus();
				return false;
			}   
    	}
	    checkDate = Trim(document.forms[0].setcicInactiveDate.value);
    	if (checkDate > ""){
			result = validateDate(checkDate);
			if (result == false){
				theForm.setcicInactiveDate.focus();
				alert("SETCIC Inactive Date is invalid date or wrong date format.");
				theForm.setcicInactiveDate.focus();
				return false;				
		    }
		    dateValues = checkDate.split('/');
			tDate_Month = dateValues[0];
			tDate_Day = dateValues[1];
			tDate_Year = dateValues[2];
	        inputDate = Date.UTC( tDate_Year, tDate_Month , tDate_Day,0,0,0);
		    if ( inputDate > today ){
		 		 alert("SETCIC Inactive Date cannot be a future date.");
				 theForm.setcicInactiveDate.focus();
				 return false;
			}	
		}
     	checkDate = Trim(document.forms[0].setcicRenewDate.value);
     	if (checkDate > ""){
			result = validateDate(checkDate);
			if (result == false){
			    alert("SETCIC Renew Date is invalid date or wrong date format.");
				theForm.setcicRenewDate.focus();
				return false;
			}   
    	}		  
   } 	
   return true;  
}

function validateAddress(streetNumId,streetNameId,cityId,stateId,zipId){
	var streetNum=document.getElementById(streetNumId);
	var streetName=document.getElementById(streetNameId);
	var city=document.getElementById(cityId);
	var state=document.getElementById(stateId);
	var zip=document.getElementById(zipId);
	if(streetNum.value <= "" && streetName.value <= "" && city.value <= "" && state.value <= "" && zip.value <= "")
		{
			return true;
		}
	else{
		if(streetNum.value > "" && streetName.value > "" && city.value > "" && state.value > "" && zip.value > "")
		{
			return true;
		}
		else{
			return false;
		}
	}
	return true;
}
function validateDate(fldValue){
	var regDate = /^\d{1,2}\/\d{1,2}\/\d{4}$/;
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
}
