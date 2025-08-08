function evalSearch(el)	{ 
    show("JIMSUserIdSearch", 1);
    show("JIMS2UserIdSearch", 0);    
    show("userNameSearch", 0);
    show("generalInstr1", 0);    	
    show("generalInstr2", 0);    	    
    show("userIdReqd", 0);
    show("userNameReqd", 0);	

    var rpt = el.options[el.selectedIndex].value;

    switch (rpt)
    {
    case "JIMSUserId":
	    show("JIMSUserIdSearch", 1);
 	  	show("generalInstr2", 1);	    
 	  	show("userIdReqd", 1);
		document.forms[0].searchJIMSLogonId.focus();
		break;
    
    case "JIMS2UserId":
	    show("JIMSUserIdSearch", 0);
	    show("JIMS2UserIdSearch", 1);
 	  	show("generalInstr1", 1);	    	    
	   	show("userIdReqd", 1);
		document.forms[0].searchJIMS2LogonId.focus();
		break;
    
    case "UserName":
	    show("JIMSUserIdSearch", 0);
		show("userNameSearch", 1);
	  	show("generalInstr1", 1);		
		show("userNameReqd", 1);			
		document.forms[0].searchLastName.focus();
		break;
				
	default:
		break;    
    } 
}

function validateSearchFields(theForm)
{
	var inqBy = document.getElementsByName("searchTypeId");
	var selValue = inqBy[0].value.toString();
	var userId = "";
	if (selValue == 'JIMSUserId'){
		theForm.searchJIMS2LogonId.value = "";
		theForm.searchLastName.value = "";
		theForm.searchFirstName.value = "";	
		userId = Trim(theForm.searchJIMSLogonId.value);
		theForm.searchJIMSLogonId.value = userId;
		if (userId == "")
		{
			alert("User ID required for search.");
			theForm.searchJIMSLogonId.focus();
			return false;
		} 
		if (userId.length < 5)
		{
			alert("Full User ID required for search.");
			theForm.searchJIMSLogonId.focus();
			return false;
		} 
      	var logonIdRegExp = /^[a-zA-Z0-9]+$/;	
		if (logonIdRegExp.test(userId) == false)
		{
			alert("User ID must be alphanumeric value.");
			theForm.searchJIMSLogonId.focus();
			return false;
		} 
	}	

	if (selValue == 'JIMS2UserId'){
		theForm.searchJIMSLogonId.value = "";	
		theForm.searchLastName.value = "";
		theForm.searchFirstName.value = "";	
		userId = Trim(theForm.searchJIMS2LogonId.value);
		theForm.searchJIMS2LogonId.value = userId
		if (userId == "")
		{
			alert("Full or partial User ID required for search.");
			theForm.searchJIMS2LogonId.focus();
			return false;
		} 
	}	
	if (selValue == 'UserName'){	
		theForm.searchJIMSLogonId.value = "";	
		theForm.searchJIMS2LogonId.value = "";		
		var lName = Trim(theForm.searchLastName.value);
		var fName = Trim(theForm.searchFirstName.value);
		theForm.searchLastName.value = lName;
		theForm.searchFirstName.value = fName;
		if (lName == "" && fName == ""){
			alert("At least 1 search is required to search.");
			theForm.searchLastName.focus();
			return false;
		}
		if (lName == "" && fName > ""){
			theForm.searchLastName.focus();
			alert("Last name required to use first name.");
			theForm.searchLastName.focus();
			return false;
		}
	    var msg = validateWildCardSearchField("Last Name", lName);
		if (msg != ""){
       		alert(msg);	
      		theForm.searchLastName.focus();  
       		return false;
    	}  		
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