<!-- JavaScript for departmentUpdate.jsp -->

function getIndex(input) {
   var index = -1;
   var i = 0;
   var found = false;
   while (i < input.form.length && index == -1)
   if (input.form[i] == input)index = i;
     else i++;
   return index;
}
function validateDepartmentUpdateFields(theForm){
   if (theForm.accessTypeId.value == "P") {
      if (theForm.subscriberCivilTerminationDate.value > "" || theForm.subscriberCriminalTerminationDate.value > "") {
         var answer=confirm("If Civil Inactive Date or Criminal Inactive Date is entered, please check the Department Status.\n");
         if (answer == true){ 
   	     	theForm.statusId.focus();
             return false;
         }
       }        
   }
   var msg = "";	
   var temp = Trim(theForm.departmentName.value);
   if (temp == ""){
	  if (msg == ""){
	      theForm.departmentName.focus();
	  }
      msg += "Department Name is required.\n";   
   }
   var temp = document.getElementById("departmentId");
   if (temp != null){
	   if (temp.value == ""){
		  if (msg == ""){
    		  theForm.departmentId.focus();
		  }
   		  msg += "Department Code is required.\n";   
   		}  
   }
   var temp = Trim(theForm.orgCode.value);
   if (temp == ""){
	  if (msg == ""){
	      theForm.orgCode.focus();
	  }
      msg += "Organization Code is required.\n";   
   }   
   if (document.getElementById("departmentFaxNumber.areaCode").value > "" && document.getElementById("departmentFaxNumber.prefix").value == "") {
      msg = "Fax Prefix must be entered if Fax Area Code is entered.\n";
      document.getElementById("departmentFaxNumber.prefix").focus();
   }
   if (document.getElementById("departmentFaxNumber.areaCode").value == "" && document.getElementById("departmentFaxNumber.prefix").value > "") {
		if (msg == ""){
		      document.getElementById("departmentFaxNumber.areaCode").focus();
		}
      msg += "Fax Area Code must be entered if Fax Prefix is entered.\n";
   }
   if (document.getElementById("departmentFaxNumber.prefix").value > "" && document.getElementById("departmentFaxNumber.last4Digit").value == "") {
		if (msg == ""){
	      document.getElementById("departmentFaxNumber.last4Digit").focus();
		}
      msg += "Fax Main number must be entered if Fax Prefix is entered.\n";
   }
   if (document.getElementById("departmentFaxNumber.last4Digit").value > "" && document.getElementById("departmentFaxNumber.prefix").value == "") {
		if (msg == ""){
	      document.getElementById("departmentFaxNumber.prefix").focus();
		}
      msg += "Fax Prefix must be entered if Fax Main number is entered.\n";
   }
/* Status and termination date edits only for non-subscriber department */
	var subscriberFlds = document.getElementsByName("subscriberCivilActivationDate");
	if (subscriberFlds[0].type == "hidden"){   
		if (theForm.statusId.value == "I" && theForm.terminationDate.value == "") {
			if (msg == ""){
	      		theForm.terminationDate.focus();
			}   
	      msg += "JIMS Inactive Date is required if Department Status is Inactive.\n";
   		}
		if (theForm.statusId.value != "I" && theForm.terminationDate.value > "") {
			if (msg == ""){
	      		theForm.terminationDate.focus();
			} 
    		msg += "If JIMS Inactive Date is entered then Department Status must be Inactive.\n";
    	}	
   }
   var curDate = new Date();
   var today = Date.UTC(curDate.getFullYear(), curDate.getMonth()+1, curDate.getDate(),0,0,0);
   var tDate_Year = "";
   var tDate_Month = "";
   var tdate_Day = "";
   var dateValues = "";
   var inputDate = "";

   var checkDate = Trim(document.forms[0].activationDate.value);
   if (checkDate > ""){
		result = validateDate(checkDate);
		if (result == false){
			if (msg == ""){
				theForm.activationDate.focus();
			}   
			msg += "JIMS Activation Date is invalid date or wrong date format.\n";
		}  
   }   

    checkDate = Trim(document.forms[0].terminationDate.value);
    if (checkDate > ""){
		result = validateDate(checkDate);
		if (result == false){
			if (msg == ""){
				theForm.terminationDate.focus();
			}   
			msg += "JIMS Inactive Date is invalid date or wrong date format.\n";
		} else {
		    dateValues = checkDate.split('/');
			tDate_Month = dateValues[0];
			tDate_Day = dateValues[1];
			tDate_Year = dateValues[2];
			inputDate = Date.UTC( tDate_Year, tDate_Month , tDate_Day,0,0,0);
			if ( inputDate > today ){
				if (msg == ""){
					theForm.terminationDate.focus();
				}   
				msg += "JIMS Inactive Date cannot be a future date.\n";
			}
		}		  
	}
   
   checkDate = Trim(document.forms[0].subscriberCivilActivationDate.value);
   if (checkDate > ""){
		result = validateDate(checkDate);
		if (result == false){
			if (msg == ""){
				theForm.subscriberCivilActivationDate.focus();
			}   
			msg += "Subscriber Civil Activation Date is invalid date or wrong date format.\n";
		}  
   }
   
   checkDate = Trim(document.forms[0].subscriberCriminalActivationDate.value);
   if (checkDate > ""){
		result = validateDate(checkDate);
		if (result == false){
			if (msg == ""){
				theForm.subscriberCriminalActivationDate.focus();
			}   
			msg += "Subscriber Criminal Activation Date is invalid date or wrong date format.\n";
		} 
   } 
     
   checkDate = Trim(document.forms[0].subscriberCivilTerminationDate.value);
   if (checkDate > ""){
		result = validateDate(checkDate);
		if (result == false){
			if (msg == ""){
				theForm.terminationDate.focus();
			}   
			msg += "Civil Inactive Date is invalid date or wrong date format.\n";
		} else {
		    dateValues = checkDate.split('/');
			tDate_Month = dateValues[0];
			tDate_Day = dateValues[1];
			tDate_Year = dateValues[2];
	   		inputDate = Date.UTC( tDate_Year, tDate_Month , tDate_Day,0,0,0);
		    if ( inputDate > today ){
				if (msg == ""){
				  theForm.subscriberCivilTerminationDate.focus();
				}   
			  msg += "Civil Inactive Date cannot be a future date.\n";
	   		}
	   	}	
   }

   checkDate = Trim(document.forms[0].subscriberCriminalTerminationDate.value);
   if (checkDate > ""){
		result = validateDate(checkDate);
		if (result == false){
			if (msg == ""){
				theForm.subscriberCriminalTerminationDate.focus();
			}   
			msg += "Criminal Inactive Date is invalid date or wrong date format.\n";
		} else {      
		    dateValues = checkDate.split('/');
		    tDate_Month = dateValues[0];
		    tDate_Day = dateValues[1];
		    tDate_Year = dateValues[2];
    	    inputDate = Date.UTC( tDate_Year, tDate_Month , tDate_Day,0,0,0);
	   		if ( inputDate > today ){
				if (msg == ""){
				  theForm.subscriberCriminalTerminationDate.focus();
				}    
			    msg += "Criminal Inactive Date cannot be a future date.\n";
	   		}
	   	}	 
	}   

   var mailingAddressComplete = validateAddress("mailingAddress");
   var physicalAddressComplete = validateAddress("physicalAddress");
   if(physicalAddressComplete > 0 && physicalAddressComplete < 5){
 		if (msg == ""){
		  document.getElementById("physicalAddress.StreetNumber").focus();
		} 
   		msg += "Physical Address is incomplete and must have street number, street name, city, state, and zip at minimum.\n";
   } 
   if(mailingAddressComplete > 0 && mailingAddressComplete < 5 ){     
 		if (msg == ""){
		  document.getElementById("mailingAddress.StreetNumber").focus();
		} 
    	msg += "Mailing Address is incomplete and must have street number, street name, city, state, and zip at minimum.\n";
   } 
   if(mailingAddressComplete == 0 && physicalAddressComplete == 0){
 		if (msg == ""){
		  document.getElementById("physicalAddress.StreetNumber").focus();
		}    
   		msg += "At least one complete Physical or Mailing Address is required.\n";
   } 

   if (document.getElementById("setcicEdit") != null){
     if (theForm.setcicAccessId.value == "I" || theForm.setcicAccessId.value == "F") {
       if (document.getElementById("setcicContactName.lastName").value == "") {
          if (msg == ""){
			  document.getElementById("setcicContactName.lastName").focus();
		  }  
          msg += "Last name is required if SETCIC Access is Inquiry or Full.\n";
       }
       if (document.getElementById("setcicContactName.firstName").value == "") {
          if (msg == ""){
			  document.getElementById("setcicContactName.firstName").focus();
		  }         
          msg += "First name is required if SETCIC Access is Inquiry or Full.\n";
       }
       if (document.getElementById("setcicPhoneNumber.areaCode").value == "" && document.getElementById("setcicPhoneNumber.prefix").value == "") {
          if (msg == ""){
			  document.getElementById("setcicPhoneNumber.areaCode").focus();
		  }
          msg += "Contact Phone is required if SETCIC Access is Inquiry or Full.\n";
       }
       if (document.getElementById("setcicPhoneNumber.areaCode").value > "" && document.getElementById("setcicPhoneNumber.prefix").value == "") {
           if (msg == ""){
			  document.getElementById("setcicPhoneNumber.prefix").focus();
		  }
          msg += "Contact Phone Prefix must be entered if Contact Phone Area Code is entered.\n";
       }
       if (document.getElementById("setcicPhoneNumber.areaCode").value == "" && document.getElementById("setcicPhoneNumber.prefix").value > "") {
           if (msg == ""){
			  document.getElementById("setcicPhoneNumber.areaCode").focus();
		  }
          msg += "Contact Area Code must be entered if Contact Prefix is entered.\n";
       }
       if (document.getElementById("setcicPhoneNumber.prefix").value > "" && document.getElementById("setcicPhoneNumber.last4Digit").value == "") {
           if (msg == ""){
			  document.getElementById("setcicPhoneNumber.last4Digit").focus();
		  }          
          msg += "Contact Phone Main number must be entered if Contact Phone Prefix is entered.\n";
       }
       if (document.getElementById("setcicPhoneNumber.prefix").value == "" && document.getElementById("setcicPhoneNumber.last4Digit").value > "") {
           if (msg == ""){
			  document.getElementById("setcicPhoneNumber.prefix").focus();
		  } 
          msg += "Contact Phone Prefix must be entered if Contact Phone Main number is entered.\n";
       }
       if (document.getElementById("setcicPhoneNumber.ext").value > "" && document.getElementById("setcicPhoneNumber.last4Digit").value == "") {
           if (msg == ""){
			  document.getElementById("setcicPhoneNumber.areaCode").focus();
		  } 
	      msg += "Contact Phone Number must be entered if Contact Phone Extension is entered.\n";
	   }
     }
     if (theForm.setcicAccessId.value == "F") {
       if (document.getElementById("warrantConfPhoneNumber.areaCode").value == "" && document.getElementById("warrantConfPhoneNumber.prefix").value == "") {
           if (msg == ""){
			  document.getElementById("warrantConfPhoneNumber.areaCode").focus();
		  } 
          msg += "Warrant Contact Phone is required if SETCIC Access is Full.\n";
       }
	   if (document.getElementById("warrantConfPhoneNumber.areaCode").value > "" && document.getElementById("warrantConfPhoneNumber.prefix").value == "") {
           if (msg == ""){
			  document.getElementById("warrantConfPhoneNumber.prefix").focus();
		  } 
	      msg += "Warrant Contact Phone Prefix must be entered if Warrant Contact Phone Area Code is entered.\n";
	   }
	   if (document.getElementById("warrantConfPhoneNumber.areaCode").value == "" && document.getElementById("warrantConfPhoneNumber.prefix").value > "") {
            if (msg == ""){
			  document.getElementById("warrantConfPhoneNumber.areaCode").focus();
		  } 
          msg += "Warrant Contact Area Code must be entered if Warrant Contact Prefix is entered.\n";
       }
	   if (document.getElementById("warrantConfPhoneNumber.prefix").value > "" && document.getElementById("warrantConfPhoneNumber.last4Digit").value == "") {
          if (msg == ""){
			  document.getElementById("warrantConfPhoneNumber.last4Digit").focus();
		  } 
	      msg += "Warrant Contact Phone Main number must be entered if Warrant Contact Phone Prefix is entered.\n";
	   }
	   if (document.getElementById("warrantConfPhoneNumber.prefix").value == "" && document.getElementById("warrantConfPhoneNumber.last4Digit").value > "") {
          if (msg == ""){
			  document.getElementById("warrantConfPhoneNumber.prefix").focus();
		  } 
	      msg += "Warrant Contact must be entered if Warrant Contact Phone Main number is entered.\n";
	   }
	   if (document.getElementById("warrantConfPhoneNumber.ext").value > "" && document.getElementById("warrantConfPhoneNumber.last4Digit").value == "") {
          if (msg == ""){
			  document.getElementById("warrantConfPhoneNumber.areaCode").focus();
		  } 
	      msg += "Warrant Contact Phone Number must be entered if Warrant Contact Phone Extension is entered.\n";
	   }
     }
     if (theForm.setcicAccessId.value == "I" || theForm.setcicAccessId.value == "F") {
       if (theForm.setcicDate.value == "") {
           if (msg == ""){
			  theForm.setcicDate.focus();
		  } 
          msg += "Active Date is required if SETCIC access is Inquiry or Full.\n";
       }   
     }
     if (theForm.statusId.value == "A" && theForm.setcicInactiveDate.value > "") {
          if (msg == ""){
			  theForm.setcicInactiveDate.focus();
		  } 
        msg += "If SETCIC Inactive Date is entered then Department Status must be Inactive.\n";
     }
     
     checkDate = Trim(document.forms[0].setcicDate.value);
     if (checkDate > ""){
		result = validateDate(checkDate);
		if (result == false){
			if (msg == ""){
				theForm.setcicDate.focus();
			}   
			msg += "SETCIC Active Date is invalid date or wrong date format.\n";
		}   
      }

     checkDate = Trim(document.forms[0].setcicInactiveDate.value);
     if (checkDate > ""){
		result = validateDate(checkDate);
		if (result == false){
			if (msg == ""){
				theForm.setcicInactiveDate.focus();
			}   
			msg += "SETCIC Inactive Date is invalid date or wrong date format.\n";
		} else {    
		    dateValues = checkDate.split('/');
			tDate_Month = dateValues[0];
			tDate_Day = dateValues[1];
			tDate_Year = dateValues[2];
	        inputDate = Date.UTC( tDate_Year, tDate_Month , tDate_Day,0,0,0);
		     if ( inputDate > today ){
    		      if (msg == ""){
					  theForm.setcicInactiveDate.focus();
				  }      
			    msg += "SETCIC Inactive Date cannot be a future date.\n";
	    	 }
	    }	  
	 }  
     checkDate = Trim(document.forms[0].setcicRenewDate.value);
     if (checkDate > ""){
		result = validateDate(checkDate);
		if (result == false){
			if (msg == ""){
				theForm.setcicRenewDate.focus();
			}   
			msg += "SETCIC Renew Date is invalid date or wrong date format.\n";
		 }   
      }    
	   
     if (theForm.setcicAccessId.value == "I" || theForm.setcicAccessId.value == "F") {	
	     if(validateAddress("setcicBillingAddress") < 5){
          	if (msg == ""){
			  document.getElementById("setcicBillingAddress.streetNumber").focus();
		  	}  	     
   		  	msg += "Setcic Billing Address is incomplete and must have street number, street name, city, state, and zip at minimum.\n";
   		  }
   	 }	  
   }
	if (msg == ""){
		return true;	
	}
	alert(msg);
	return false;   
// 	return validateDepartmentUpdateForm(theForm);  
}

function validateAddress(addressType){
	var streetNumId = addressType + '.streetNumber';
	var streetNameId = addressType + '.streetName';
	var cityId = addressType + '.city';
	var stateId = addressType + '.stateId';	
	var zipId = addressType + '.zipCode';		
	var streetNum = document.getElementById(streetNumId);
	var streetName = document.getElementById(streetNameId);
	var city = document.getElementById(cityId);
	var state =document.getElementById(stateId);
	var zipCode = document.getElementById(zipId);
	var count = 0;
	if (streetNum.value > ""){
		count++;
	}	 
	if (streetName.value > ""){
		count++;
	}	
	if (city.value > ""){
		count++;
	}	
	if (state.value > ""){
		count++;
	}	
	if (zipCode.value > ""){
		count++;
	}	
	return count;
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
function checkRadio(theForm){
	var yesTot = 0;
	var rb = "";
	var rbSelected = -1;

	for (n = 0; n < theForm.length; n++){
		fld = "contactList[" + n + "].primaryContact";
		rb = document.getElementsByName(fld);
		rbSelected = -1;
		if (rb.length == 0){
			break;
		}
		for (i=0; i < rb.length; i++)
		{
			rValue = rb[i].value.toUpperCase();
			if (rb[i].checked)
			{ 
				if (rValue == "Y")
					yesTot++ 
			} 
		}
	}	
	if (yesTot == 0){
		alert('One Primary Contact must be Selected.');
			var fldName = "contactList[" + 0 + "].lastName";
			setFieldFocus(fldName);
		return false;
		}
	if (yesTot > 1){
		alert('Only 1 contact can be selected as primary.');
			var fldName = "contactList[" + 0 + "].lastName";
			setFieldFocus(fldName);
		return false;
		}	
	return true;
	}
	
function setFieldFocus(fldName)
{
	for(i=0; i<document.forms[0].length; i++) 
	{
		if (document.forms[0].elements[i].name == fldName)
		{ 
			document.forms[0].elements[i].focus();
	       	break; 
	  	}
	}  
	return;   	
}

function checkSubscriberInactiveDates()
{
	var civilDate = document.getElementsByName("subscriberCivilTerminationDate");
	var crimDate = document.getElementsByName("subscriberCriminalTerminationDate");	
    var sel = document.getElementsByName("statusId")[0];
    var opt =sel[sel.options.selectedIndex].value;
	if (civilDate.length == 0 || crimDate.length == 0){
		return true;
	}
	if (civilDate[0].value > "" || crimDate[0].value > ""){
		if (opt.toUpperCase() == "A"){
			var ans = confirm("An inactive Subscriber date has been entered. \nSelect OK to update the Department Status field if needed or click Cancel to continue without changing Department Status."); 
			if (ans == true){
				setFieldFocus("statusId");			
				return false;
			}	
		}	
	}
	return true;
}
