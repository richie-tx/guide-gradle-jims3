<!-- JavaScript - Button Check -->
<script language=javascript>

var regDefaultMask = new RegExp("^[a-zA-Z0-9]*$", "i");

function getIndex(input) {
   var index = -1;
   var i = 0;
   var found = false;
   while (i < input.form.length && index == -1)
   if (input.form[i] == input)index = i;
     else i++;
   return index;
}
function validateManagerId(form)
{
	
	form.managerId.focus();
	if((form.managerId.value==null || form.managerId.value=="")){
		alert("Manager User ID must not be blank in order to validate");
		return false;
	}
	if(!regDefaultMask.exec(form.managerId.value)){
	alert("Manager User ID contains invalid characters");
		return false;
	}
	if(form.managerId.value.length <5){
		alert("Manager User ID must be 5 characters");
		
		return false;
	}
	
	
	if(validateDepartmentCode(form))
	{
		form.managerId.focus();
		return true;
	}
	else{
		return false;
	}
}

function validateDepartmentCode(form)
{
	if((form.departmentId.value==null || form.departmentId.value=="")){
		alert("Department Code must not be blank in order to validate");
		return false;
		form.departmentId.focus();
	}
	if(!regDefaultMask.exec(form.departmentId.value)){
	alert("Department Code contains invalid characters");
		return false;
	}
	if(form.departmentId.value.length <3){
		alert("Department Code must be 3 characters");
		return false;
	}
	return true;
}

function toggle(chkbox, group)
{
	var visSetting = (chkbox.checked) ? "hidden" : "visible" ;
	document.getElementById(group).style.visibility = visSetting;
	return true;
}

function toggleAll()
{
	toggle(document.getElementById("dayOff0"),'sundayStart');
	toggle(document.getElementById("dayOff0"),'sundayEnd');
	toggle(document.getElementById("dayOff1"),'mondayStart');
	toggle(document.getElementById("dayOff1"),'mondayEnd');
	toggle(document.getElementById("dayOff2"),'tuesdayStart');
	toggle(document.getElementById("dayOff2"),'tuesdayEnd');
	toggle(document.getElementById("dayOff3"),'wednesdayStart');
	toggle(document.getElementById("dayOff3"),'wednesdayEnd');
	toggle(document.getElementById("dayOff4"),'thursdayStart');
	toggle(document.getElementById("dayOff4"),'thursdayEnd');
	toggle(document.getElementById("dayOff5"),'fridayStart');
	toggle(document.getElementById("dayOff5"),'fridayEnd');
	toggle(document.getElementById("dayOff6"),'saturdayStart');
	toggle(document.getElementById("dayOff6"),'saturdayEnd');
	return true;
}

	//show - basic show/hide functionality
 /*params:
 what - id of what to show or hide
 hide - 0=hide 1=show
 format - table/row - no entry standard Block
 */

	function show(what, hide, format){
	if (hide == 0)
		{
			document.getElementById(what).className='hidden';
		}else if(format=="row"){
				document.getElementById(what).className='visibleTR';
			}else if(format=="table"){
					document.getElementById(what).className='visibleTable';
				}else if(format=="inline"){
					document.getElementById(what).className='visibleInline';
					}else {
						document.getElementById(what).className='visible';
					}
	}
	
	function disableDepartmentLinks(theForm){
	if (theForm==null){
		theForm=document.forms[0];
		}
		if (theForm.userId.value!=""){
		show('userSpan',0);
		} else {
		show('userSpan',1,'inline');
		}
	}


function validateOfficerType(theForm){
    if (theForm.officerTypeId.value != "J") {
        if(theForm.departmentId.value.toUpperCase() == "JUV"){
            alert("Officer type must be JUVENILE PROBATION if department code is JUV.");
            theForm.departmentId.focus();
            return false;
        }
    }          
	if (theForm.officerTypeId.value=="J"){
		if(theForm.departmentId.value.toUpperCase() != "JUV"){
			alert("For officer type JUVENILE PROBATION, only valid department code is JUV.");
			theForm.departmentId.focus();
			return false;
		}
		if(((theForm.managerId.value==null) && (theForm.managerLastName.value==null)) || ((theForm.managerId.value=="")&& (theForm.managerLastName.value==""))){
			alert("Manager User ID must be entered for officer type JUVENILE PROBATION");
			theForm.managerId.focus();
			return false;
		}
		if(theForm.userId!=null){
			if((theForm.userId.value==null) || (theForm.userId.value=="")){
				alert("User ID must be entered for officer type JUVENILE PROBATION");
				theForm.userId.focus();
				return false;
			}
		}
		
/*		if(!(theForm.dayOff0.checked)){
//			if((theForm.startTimeId0.value==null) || (theForm.startTimeId0.value=="")){
//				alert("Either Start time and End Time must be entered or Day Off must be selected for Sunday");
				theForm.startTimeId0.focus();
				return false;
			}
			if((theForm.endTimeId0.value==null) || (theForm.endTimeId0.value=="")){
				alert("Either Start time and End Time must be entered or Day Off must be selected for Sunday");
				theForm.endTimeId0.focus();
				return false;
			}
		}
		if(!(theForm.dayOff1.checked)){
			if((theForm.startTimeId1.value==null) || (theForm.startTimeId1.value=="")){
				alert("Either Start time and End Time must be entered or Day Off must be selected for Monday");
				theForm.startTimeId1.focus();
				return false;
			}
			if((theForm.endTimeId1.value==null) || (theForm.endTimeId1.value=="")){
				alert("Either Start time and End Time must be entered or Day Off must be selected for Monday");
				theForm.endTimeId1.focus();
				return false;
			}
		}
		if(!(theForm.dayOff2.checked)){
			if((theForm.startTimeId2.value==null) || (theForm.startTimeId2.value=="")){
				alert("Either Start time and End Time must be entered or Day Off must be selected for Tuesday");
				theForm.startTimeId2.focus();
				return false;
			}
			if((theForm.endTimeId2.value==null) || (theForm.endTimeId2.value=="")){
				alert("Either Start time and End Time must be entered or Day Off must be selected for Tuesday");
				theForm.endTimeId2.focus();
				return false;
			}
		}
		if(!(theForm.dayOff3.checked)){
			if((theForm.startTimeId3.value==null) || (theForm.startTimeId3.value=="")){
				alert("Either Start time and End Time must be entered or Day Off must be selected for Wednesday");
				theForm.startTimeId3.focus();
				return false;
			}
			if((theForm.endTimeId3.value==null) || (theForm.endTimeId3.value=="")){
				alert("Either Start time and End Time must be entered or Day Off must be selected for Wednesday");
				theForm.endTimeId3.focus();
				return false;
			}
		}
		if(!(theForm.dayOff4.checked)){
			if((theForm.startTimeId4.value==null) || (theForm.startTimeId4.value=="")){
				alert("Either Start time and End Time must be entered or Day Off must be selected for Thursday");
				theForm.startTimeId4.focus();
				return false;
			}
			if((theForm.endTimeId4.value==null) || (theForm.endTimeId4.value=="")){
				alert("Either Start time and End Time must be entered or Day Off must be selected for Thursday");
				theForm.endTimeId4.focus();
				return false;
			}
		}
		if(!(theForm.dayOff5.checked)){
			if((theForm.startTimeId5.value==null) || (theForm.startTimeId5.value=="")){
				alert("Either Start time and End Time must be entered or Day Off must be selected for Friday");
				theForm.startTimeId5.focus();
				return false;
			}
			if((theForm.endTimeId5.value==null) || (theForm.endTimeId5.value=="")){
				alert("Either Start time and End Time must be entered or Day Off must be selected for Friday");
				theForm.endTimeId5.focus();
				return false;
			}
		}
		if(!(theForm.dayOff6.checked)){
			if((theForm.startTimeId6.value==null) || (theForm.startTimeId6.value=="")){
				alert("Either Start time and End Time must be entered or Day Off must be selected for Saturday");
				theForm.startTimeId6.focus();
				return false;
			}
			if((theForm.endTimeId6.value==null) || (theForm.endTimeId6.value=="")){
				alert("Either Start time and End Time must be entered or Day Off must be selected for Saturday");
				theForm.endTimeId6.focus();
				return false;
			}
		}
*/		
	}
	return validateOfficerForm(theForm);
}

function validateCreateFields(theForm){
//Begin Badge and ID Number Validation
   if (theForm.badgeNum.value == "" && theForm.otherIdNum.value == ""){
	   alert("Either Badge Number or other ID Number is required.");
	   theForm.badgeNum.focus();
       return false; 
   }
//End Badge and ID Number Validation
//Begin Officer Type Validation - required selection 

   if (theForm.officerTypeId.selectedIndex < 1){
      alert("Officer Type selection is required.");
      theForm.officerTypeId.focus();
      return false;
   }
//End Officer Type Validation
//Begin Status Validation
      if (theForm.statusId.value == "I") {
         alert("Officer Status must be active to create or update Officer Profile.")
         theForm.statusId.focus();
         return false;
      }
//End Status Validation
//Begin Social Security Validation
   if (theForm.ssn1.value > "" && theForm.ssn2.value == "") {
      alert("All of Social Security number must be entered if there is partial entry.");
      theForm.ssn2.focus();
      return false;
   }
   if (theForm.ssn1.value > "" && theForm.ssn3.value == "") {
      alert("All of Social Security number must be entered if there is partial entry.");
      theForm.ssn3.focus();
      return false;
   }
   if (theForm.ssn2.value > "" && theForm.ssn1.value == "") {
      alert("All of Social Security number must be entered if there is partial entry.");
      theForm.ssn1.focus();
      return false;
   }
   if (theForm.ssn2.value > "" && theForm.ssn3.value == "") {
      alert("All of Social Security number must be entered if there is partial entry.");
      theForm.ssn3.focus();
      return false;
   }
   if (theForm.ssn3.value > "" && theForm.ssn1.value == "") {
      alert("All of Social Security number must be entered if there is partial entry.");
      theForm.ssn1.focus();
      return false;
   }
   if (theForm.ssn3.value > "" && theForm.ssn2.value == "") {
      alert("All of Social Security number must be entered if there is partial entry.");
      theForm.ssn2.focus();
      return false;
   }   
//End Social Security Validation
//Begin Phone Validation
   if (theForm.workPhoneAreaCode.value > "" && theForm.workPhonePrefix.value == "") {
      alert("Work Phone Prefix must be entered if Work Phone Area Code is entered.");
      theForm.workPhonePrefix.focus();
      return false;
   }
   if (theForm.workPhonePrefix.value > "" && theForm.workPhoneMain.value == "") {
      alert("Work Phone Main number must be entered if Work Phone Prefix is entered.");
      theForm.workPhoneMain.focus();
      return false;
   }
   if (theForm.workPhonePrefix.value == "" && theForm.workPhoneMain.value > "") {
      alert("Work Phone Prefix must be entered if Work Phone Main number is entered.");
      theForm.workPhonePrefix.focus();
      return false;
     }
    if (theForm.workPhoneAreaCode.value == "" && theForm.workPhoneMain.value > "") {
      alert("Work Phone Area Code must be entered if Work Phone Main number is entered.");
      theForm.workPhoneAreaCode.focus();
      return false;
     } 
   if (theForm.extn.value > "" && theForm.workPhoneMain.value == "") {
      alert("Work Phone Number must be entered if Work Phone Extension is entered.");
      theForm.workPhoneAreaCode.focus();
      return false;
     }
   if (theForm.homePhoneAreaCode.value > "" && theForm.homePhonePrefix.value == "") {
      alert("Home Phone Prefix must be entered if Home Phone Area Code is entered.");
      theForm.homePhonePrefix.focus();
      return false;
   }
   if (theForm.homePhonePrefix.value > "" && theForm.homePhoneMain.value == "") {
      alert("Home Phone Main number must be entered if Home Phone Prefix is entered.");
      theForm.homePhoneMain.focus();
      return false;
   }
   if (theForm.homePhoneMain.value > "" && theForm.homePhonePrefix.value == "") {
      alert("Home Phone Prefix must be entered if Home Phone Main number is entered.");
      theForm.homePhonePrefix.focus();
      return false;
      }
     if (theForm.homePhoneMain.value > "" && theForm.homePhoneAreaCode.value == "") {
      alert("Home Phone Area Code must be entered if Home Phone Main number is entered.");
      theForm.homePhoneAreaCode.focus();
      return false;
      }
   if (theForm.cellPhoneAreaCode.value > "" && theForm.cellPhonePrefix.value == "") {
      alert("Cell Phone Prefix must be entered if Cell Phone Area Code is entered.");
      theForm.cellPhonePrefix.focus();
      return false;
   }
   if (theForm.cellPhonePrefix.value > "" && theForm.cellPhoneMain.value == "") {
      alert("Cell Phone Main number must be entered if Cell Phone Prefix is entered.");
      theForm.cellPhoneMain.focus();
      return false;
   }
   if (theForm.cellPhoneMain.value > "" && theForm.cellPhonePrefix.value == "") {
      alert("Cell Phone Prefix must be entered if Cell Phone Main number is entered.");
      theForm.cellPhonePrefix.focus();
      return false;
      }
    if (theForm.cellPhoneMain.value > "" && theForm.cellPhoneAreaCode.value == "") {
      alert("Cell Phone Area Code must be entered if Cell Phone Main number is entered.");
      theForm.cellPhoneAreaCode.focus();
      return false;
      }
   if (theForm.pagerAreaCode.value > "" && theForm.pagerPrefix.value == "") {
      alert("Pager Prefix must be entered if Pager Area Code is entered.");
      theForm.pagerPrefix.focus();
      return false;
   }
   if (theForm.pagerPrefix.value > "" && theForm.pagerMain.value == "") {
      alert("Pager Main number must be entered if Pager Prefix is entered.");
      theForm.pagerMain.focus();
      return false;
   }
   if (theForm.pagerMain.value > "" && theForm.pagerPrefix.value == "") {
      alert("Pager Prefix must be entered if Pager Main number is entered.");
      theForm.pagerPrefix.focus();
      return false;
      }
      if (theForm.pagerMain.value > "" && theForm.pagerAreaCode.value == "") {
      alert("Pager Area Code must be entered if Pager Main number is entered.");
      theForm.pagerAreaCode.focus();
      return false;
      }
   if (theForm.faxAreaCode.value > "" && theForm.faxPrefix.value == "") {
      alert("Fax Prefix must be entered if Fax Area Code is entered.");
      theForm.faxPrefix.focus();
      return false;
   }
   if (theForm.faxPrefix.value > "" && theForm.faxMain.value == "") {
      alert("Fax Main number must be entered if Fax Prefix is entered.");
      theForm.faxMain.focus();
      return false;
   }
   if (theForm.faxMain.value > "" && theForm.faxPrefix.value == "") {
      alert("Fax Prefix must be entered if Fax Main number is entered.");
      theForm.faxPrefix.focus();
      return false;
   }
   if (theForm.faxMain.value > "" && theForm.faxAreaCode.value == "") {
      alert("Fax Area Code must be entered if Fax Main number is entered.");
      theForm.faxAreaCode.focus();
      return false;
   }
   

//End Phone Validation
//Begin Address Validation
   if (theForm.streetNumber.value > "" || theForm.streetName.value > "" || theForm.streetTypeId.selectedIndex > 0 ||
       theForm.apartmentNumber.value > "" || theForm.city.value > "" ||
       theForm.stateId.selectedIndex > 0 || theForm.zipCode.value > "") {
       if (theForm.streetNumber.value == "") {
          alert("Street Number is required if one or more other address fields have entries.");
          theForm.streetNumber.focus();
          return false;
       }
       if (theForm.streetName.value == "") {
          alert("Street Name is required if one or more other address fields have entries.");
          theForm.streetName.focus();
          return false;
       }
       if (theForm.city.value == "") {
          alert("City is required if one or more other address fields have entries.");
          theForm.city.focus();
          return false;
       }
       if (theForm.stateId.selectedIndex < 1){
          alert("State is required if one or more other address fields have entries.");
          theForm.stateId.focus();
          return false;
       }
       if (theForm.zipCode.value == "") {
          alert("Zip Code is required if one or more other address fields have entries.");
          theForm.zipCode.focus();
          return false;
       }    
   }    
       
 // End Address Validation
 
 // Start Manager Validation
   if(theForm.officerTypeId.value=="J") {
       if(theForm.managerId.value == "" || theForm.managerId.value == " ") 
        {
		  alert("Manager User ID is required for officer type Juvenile Probation.");
		  theForm.managerId.focus();
		  return false;
		}
	}	
   
   if(theForm.lastName.value == null || theForm.lastName.value == ""){
	   alert("Last Name is required");
	   theForm.lastName.focus();
	   return false;
   }
   
   if(theForm.firstName.value == null || theForm.firstName.value == ""){
	   alert("First Name is required");
	   theForm.firstName.focus();
	   return false;
   }
   
	   
	return validateOfficerType(theForm);  
}


</script>

