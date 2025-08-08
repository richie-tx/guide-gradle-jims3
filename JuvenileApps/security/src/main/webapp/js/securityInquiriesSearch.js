<!-- securityInquiriesSearch.js -->
/* var wildCard = new RegExp("^[*]$", "i"); */

function getIndex(input) {
   var index = -1;
   var i = 0;
   var found = false;
   while (i < input.form.length && index == -1)
   if (input.form[i] == input)index = i;
     else i++;
   return index;
}

function evalSearch(el)	{  
	show("agency", 0);
//	show("employeeId", 0);	
	show("role", 0);
	show("subsystem", 0);
	show("userGroup", 0); 
	show("userID", 0);
	show("userProfile", 0);    
    
//	show("employeeIdINSTR", 0);	
	show("generalINSTR", 0);	
	show("userIDINSTR", 0);	
	show("userProfileINSTR", 0);	

	for (x = 0; x<document.forms[0].length; x++){
		if (document.forms[0].elements[x].type == "text"){
			document.forms[0].elements[x].value = "";
		}
	}
	var rpt = el.options[el.selectedIndex].value;
	switch (rpt){
		case "AG":
			show("agency", 1);
			show("generalINSTR", 1);	    
			document.forms[0].agencyName.focus();	
			break;
    
//		case "EM":
//			show("employeeId", 1);
//			show("employeeIdINSTR", 1);	        
//			document.forms[0].employeeId.focus();		  
//			break;

		case "RO":
			show("role", 1);
			show("generalINSTR", 1);	        
			document.forms[0].roleName.focus();		  
			break;
    
		case "SS":
			show("subsystem", 1);
			show("generalINSTR", 1);				
			document.forms[0].featureCategoryId.focus();	
/*			if (document.forms[0].featureCategoryId.selectedIndex == 0){
				document.forms[0].featureId.disabled = true;
		  	} 	*/	  
			break;
      
		case "UG":
			show("userGroup", 1);
			show("generalINSTR", 1);	        
			document.forms[0].userGroupName.focus();	  
			break;

		case "UI":
			show("userID", 1);
			show("userIDINSTR", 1);	        
			document.forms[0].searchJIMSLogonId.focus();		  
			break;
              
		case "UP":
			show("userProfile", 1);
			show("generalINSTR", 0);	
			show("userProfileINSTR", 1);
			document.forms[0].lastNamePrompt.focus();
			break;
    				
		default:
			break;    
	}
}

function submitPage(theForm){
	var inqBy = document.getElementsByName("searchTypeId");
	var selValue = inqBy[0].value.toString();
	var inputFound = 0;
	var	errMsg = "";
	var msg = "At least 1 search value required to search.";
	var temp = "";
	
	switch (selValue) {
	    case "AG":
			theForm.agencyName.value = Trim(theForm.agencyName.value);
			theForm.agencyIdPrompt.value = Trim(theForm.agencyIdPrompt.value);
			if (theForm.agencyName.value > "" || theForm.agencyIdPrompt.value> ""){
               	errMsg = validateWildCardSearchField("Agency Name", theForm.agencyName.value);
				if (errMsg != ""){
			       	alert(errMsg);	
			      	theForm.agencyName.focus();  
			       	return false;
    			} 
			    errMsg = validateWildCardSearchField("Agency Code", theForm.agencyIdPrompt.value);
				if (errMsg != ""){
			       	alert(errMsg);	
			      	theForm.agencyIdPrompt.focus();  
			       	return false;
			    } 			
				inputFound = 1;
			} else {
				theForm.agencyName.focus();
			}
			break;

//	    case "EM":
//			theForm.employeeId.value = Trim(theForm.employeeId.value);
//			var empIDFmtRegex = /^[a-zA-Z0-9]+$/;
//			if (theForm.employeeId.value > ""){
//				inputFound = 1;
//				if (empIDFmtRegex.test(theForm.employeeId.value) == false){
//					alert("Employee ID is invalid.");
//					theForm.employeeId.focus();
//					return false;
//				}
//			}  
//			if (theForm.employeeId.value == "") {
//				msg = "Employee ID is required to search.";
//				theForm.employeeId.focus();
//		}		
//		break;
		    
	    case "RO":
			theForm.roleName.value = Trim(theForm.roleName.value);
			theForm.roleDescription.value = Trim(theForm.roleDescription.value);	
			theForm.roleAgencyName.value = Trim(theForm.roleAgencyName.value);
			theForm.roleAgencyId.value = Trim(theForm.roleAgencyId.value);
			temp = theForm.roleName.value + theForm.roleDescription.value + theForm.roleAgencyName.value + theForm.roleAgencyId.value;		
			if (temp > ""){
               	errMsg = validateWildCardSearchField("Role Name", theForm.roleName.value);
				if (errMsg != ""){
			       	alert(errMsg);	
			      	theForm.roleName.focus();  
			       	return false;
    			} 
			    errMsg = validateWildCardSearchField("Role Description", theForm.roleDescription.value);
				if (errMsg != ""){
			       	alert(errMsg);	
			      	theForm.roleDescription.focus();  
			       	return false;
			    } 			
               	errMsg = validateWildCardSearchField("Agency Name", theForm.roleAgencyName.value);
				if (errMsg != ""){
			       	alert(errMsg);	
			      	theForm.roleAgencyName.focus();  
			       	return false;
    			} 
			    errMsg = validateWildCardSearchField("Agency Code", theForm.roleAgencyId.value);
				if (errMsg != ""){
			       	alert(errMsg);	
			      	theForm.roleAgencyId.focus();  
			       	return false;
			    } 			
				inputFound = 1;
			} else {
				theForm.roleName.focus();
			}		
			break;
    
	    case "SS":
			if (theForm.featureCategoryId.selectedIndex > 0){
				inputFound = 1;		
			} else {
				theForm.featureCategoryId.focus();
			}		
			break;

    	case "UG":
			theForm.userGroupName.value = Trim(theForm.userGroupName.value);
			theForm.userGroupDescription.value = Trim(theForm.userGroupDescription.value);
			theForm.userGroupAgencyName.value = Trim(theForm.userGroupAgencyName.value);
			theForm.userGroupAgencyId.value = Trim(theForm.userGroupAgencyId.value);
			
			temp = theForm.userGroupName.value + theForm.userGroupDescription.value + theForm.userGroupAgencyName.value + theForm.userGroupAgencyId.value;		
			if (temp > ""){
               	errMsg = validateWildCardSearchField("User Group Name", theForm.userGroupName.value);
				if (errMsg != ""){
			       	alert(errMsg);	
			      	theForm.userGroupName.focus();  
			       	return false;
    			} 
			    errMsg = validateWildCardSearchField("User Group Description", theForm.userGroupDescription.value);
				if (errMsg != ""){
			       	alert(errMsg);	
			      	theForm.userGroupDescription.focus();  
			       	return false;
			    } 			
               	errMsg = validateWildCardSearchField("Agency Name", theForm.userGroupAgencyName.value);
				if (errMsg != ""){
			       	alert(errMsg);	
			      	theForm.userGroupAgencyName.focus();  
			       	return false;
    			} 
			    errMsg = validateWildCardSearchField("Agency Code", theForm.userGroupAgencyId.value);
				if (errMsg != ""){
			       	alert(errMsg);	
			      	theForm.userGroupAgencyId.focus();  
			       	return false;
			    } 			
				inputFound = 1;
			} else {
				theForm.userGroupName.focus();
			}
			break;

	    case "UI":
			theForm.searchJIMSLogonId.value = Trim(theForm.searchJIMSLogonId.value);
			theForm.searchJIMS2LogonId.value = Trim(theForm.searchJIMS2LogonId.value);				
			if (theForm.searchJIMSLogonId.value > "" && theForm.searchJIMS2LogonId.value > ""){
				alert("Only 1 search field is allowed.");
				theForm.searchJIMSLogonId.focus();
				return false;
			}
			if (theForm.searchJIMSLogonId.value > "" || theForm.searchJIMS2LogonId.value > ""){
               	errMsg = validateWildCardSearchField("JIMS User ID", theForm.searchJIMSLogonId.value);
				if (errMsg != ""){
			       	alert(errMsg);	
			      	theForm.searchJIMSLogonId.focus();  
			       	return false;
    			} 
			    errMsg = validateWildCardSearchField("JIMS2 User ID", theForm.searchJIMS2LogonId.value);
				if (errMsg != ""){
			       	alert(errMsg);	
			      	theForm.searchJIMS2LogonId.focus();  
			       	return false;
			    }			
				inputFound = 1;
			} else {
				theForm.searchJIMSLogonId.focus();
			}
		
			break;	
		
    	case "UP":
			theForm.lastNamePrompt.value = Trim(theForm.lastNamePrompt.value);
			theForm.firstNamePrompt.value = Trim(theForm.firstNamePrompt.value);
			theForm.userAgencyNamePrompt.value = Trim(theForm.userAgencyNamePrompt.value);
			theForm.userAgencyIdPrompt.value = Trim(theForm.userAgencyIdPrompt.value);		
			theForm.userDeptNamePrompt.value = Trim(theForm.userDeptNamePrompt.value);
			theForm.userDeptIdPrompt.value = Trim(theForm.userDeptIdPrompt.value);
			if (theForm.lastNamePrompt.value == ""){
				alert('Last Name is required.');
				theForm.lastNamePrompt.focus();
				return false;	
				}
			if (theForm.lastNamePrompt.value > ""){
				inputFound = 1;
			}	
          	errMsg = validateWildCardSearchField("Last Name", theForm.lastNamePrompt.value);
			if (errMsg != ""){
		       	alert(errMsg);	
		      	theForm.lastNamePrompt.focus();  
		       	return false;
   			} 
		    errMsg = validateWildCardSearchField("First Name", theForm.firstNamePrompt.value);
			if (errMsg != ""){
		       	alert(errMsg);	
		      	theForm.firstNamePrompt.focus();  
		       	return false;
		    }		
          	errMsg = validateWildCardSearchField("Agency Name", theForm.userAgencyNamePrompt.value);
			if (errMsg != ""){
		       	alert(errMsg);	
		      	theForm.userAgencyNamePrompt.focus();  
		       	return false;
   			} 
		    errMsg = validateWildCardSearchField("Agency Code", theForm.userAgencyIdPrompt.value);
			if (errMsg != ""){
		       	alert(errMsg);	
		      	theForm.userAgencyIdPrompt.focus();  
		       	return false;
		    }		
          	errMsg = validateWildCardSearchField("Department Name", theForm.userDeptNamePrompt.value);
			if (errMsg != ""){
		       	alert(errMsg);	
		      	theForm.userDeptNamePrompt.focus();  
		       	return false;
   			} 
		    errMsg = validateWildCardSearchField("Department Code", theForm.userDeptIdPrompt.value);
			if (errMsg != ""){
		       	alert(errMsg);	
		      	theForm.userDeptIdPrompt.focus();  
		       	return false;
		    }		
	 
			if (inputFound == 0){
				theForm.lastNamePrompt.focus();
			}
			break;
    
		default:
			break;    
    }
	if (!inputFound){
		alert(msg);
		return false;
	}  	
	return true;
 }
 
function checkSubsystemSelect(theForm){
	if (theForm.featureCategoryId.selectedIndex == 0){
		alert("Subsystem selection required.");
		return false;
	}
	return true;
}

function show(what, hide, format){
	if (hide == 0)
		{
			document.getElementById(what).className='hidden';
		}else if(format=="row"){				
				document.getElementById(what).className='visibleTR';
			}else if(format=="table"){
					document.getElementById(what).className='visibleTable';
				}else {			
						document.getElementById(what).className='visible';
					}
	}

