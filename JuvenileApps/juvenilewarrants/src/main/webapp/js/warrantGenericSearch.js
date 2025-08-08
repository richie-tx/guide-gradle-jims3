<!-- Javascript for emulated validation for Juvenile warrant DTA Search -->

// Check that a string contains only numbers
function clearJuvNameFields(el){
	el.lastName.value="";
	el.firstName.value="";
	}

function clearJuvWarrantFields(el){
	el.warrantNum.value="";
	}

function clearJuvNumFields(el){
	el.juvenileNum.value="";
	el.referralNum.value="";
	}
	
        
function evalUIType(theType){
	displayInstructions(theType.value);
	showHide('sep', 1);
	if (theType.value == "actARR" || theType.value == "actDTA" || theType.value == "updateOIC" || theType.value == "updateVOP")
	{
		showHide('sep', 0);
		showHide('searchByWarrantNum_JuvName', 0);		
		showHide('searchByWarrantNum_JuvNum', 0);	
		showHide('warrantNumOnly', 1);
		showHide('juvNameOnly', 0);					 	
		showHide('juvNum_RefNum', 0);
		document.getElementsByName("warrantNum")[0].focus();		
	} else 
		if (theType.value == "actVOP")
		{
			showHide('warrantNumOnly', 0);
			showHide('searchByWarrantNum_JuvName', 0);		
			showHide('searchByWarrantNum_JuvNum', 1);
			evalSearchNumbers(document.forms[0]);			

		} else 
			{
				showHide('warrantNumOnly', 0);
				showHide('searchByWarrantNum_JuvName', 1);		
				showHide('searchByWarrantNum_JuvNum', 0);
				evalSearch(document.forms[0]);			
			}
}   

function evalSearch(el){
	showHide('juvNum_RefNum', 0);
	if (el.searchType.options[el.searchType.selectedIndex].value == "warrantNumber"){
		showHide('warrantNumOnly', 1);
		showHide('juvNameOnly', 0);	
		clearJuvNameFields(el);			
 		document.getElementsByName("warrantNum")[0].focus();

		}else if (el.searchType.options[el.searchType.selectedIndex].value == "juvenileName"){
			showHide('warrantNumOnly', 0);
			showHide('juvNameOnly', 1);	
			clearJuvWarrantFields(el);				
   			document.getElementsByName("lastName")[0].focus();
			} 
    }   
function evalSearchNumbers(el){
	showHide('juvNameOnly', 0);	
	if (el.searchNumberType.options[el.searchNumberType.selectedIndex].value == "warrantNumber"){
			showHide('warrantNumOnly', 1);
    		showHide('juvNum_RefNum', 0);
			clearJuvNumFields(el);    		
			document.getElementsByName("warrantNum")[0].focus();

		}else if (el.searchNumberType.options[el.searchNumberType.selectedIndex].value == "juvNumber"){
				showHide('warrantNumOnly', 0);
				showHide('juvNum_RefNum', 1);
				clearJuvWarrantFields(el);
				document.getElementsByName("juvenileNum")[0].focus();
			}
        } 
function displayInstructions(el){
	showHide('instr_general', 0);	
	showHide('instr_actVOP', 0);	
	showHide('instr_warrantNumOnly', 0);	
	showHide('instr_releaseDecision', 0);	
	showHide('instr_releaseToJP', 0);						
	showHide('instr_releaseToPerson', 0);	
	showHide('instr_warrantService', 0);	
	showHide('instr_processReturn', 0);
	showHide('instr_retSigStatus', 0);	

	switch(el){
		case 'actVOP':
			showHide('instr_actVOP', 1);
			break;
		case 'releaseDecision':
			showHide('instr_releaseDecision', 1); 	
			break;
		case 'releaseToJP':
			showHide('instr_releaseToJP', 1);	
			break;
		case 'releaseToPerson':	
			showHide('instr_releaseToPerson', 1);
			break;
		case 'warrantService':		
			showHide('instr_warrantService', 1);	
			break;
		case 'processReturn':		
			showHide('instr_processReturn', 1);	
			break;			
		case 'retSigStatus':	
			showHide('instr_retSigStatus', 1);
			break;
		case 'actARR':
			showHide('instr_warrantNumOnly', 1);
			break;	
		case 'actDTA':
			showHide('instr_warrantNumOnly', 1);
			break;	
		case 'updateOIC':
			showHide('instr_warrantNumOnly', 1);
			break;		
		case 'updateVOP':
			showHide('instr_warrantNumOnly', 1);
			break;													
		default:
			showHide('instr_general', 1);
		}	
}   
// script from warrantGenericSearch.js
function isNumeric(string, ignoreWhiteSpace) 
{
   if (string.search) 
   {
      if ((ignoreWhiteSpace && string.search(/[^\d\s]/) != -1) 
      	 || (!ignoreWhiteSpace && string.search(/\D/) != -1)) 
      {
      	 return false;
      }
   }
   return true;
}

function checkJOTRequired(theForm)
{
	var msg = "";
	var warrantType = theForm.warrantType.value;
	var warrantNum = "";

	var warrantRegex = /^\d+$/;
	var juvenileRegex = /^\d+$/;
	var referralRegex = /^\d{4}$/;
	var searchBy = "";
	var search1 = document.getElementById("searchByWarrantNum_JuvName");
	var search2 = document.getElementById("searchByWarrantNum_JuvNum");
	clearAllValArrays();

	if (search1.className == 'visible')
	{
		searchBy = theForm.searchType.options[theForm.searchType.selectedIndex].value;
	} 
	if (search2.className == 'visible')
	{
		searchBy = theForm.searchNumberType.options[theForm.searchNumberType.selectedIndex].value;
	}	
   	if (searchBy == "warrantNumber")
   	{
		warrantNum = Trim(theForm.warrantNum.value);
		theForm.warrantNum.value = warrantNum;
		if(warrantNum == null || warrantNum == "")
		{
			msg = "Warrant Number is required for search.";
			theForm.warrantNum.focus();
		}
		if(warrantNum != "" && warrantRegex.test(warrantNum) == false)
		{
			msg = "Warrant Number should be an integer.";
			theForm.warrantNum.focus();
		}
	}
	
	if(warrantType == "actVOP" && msg == "")
	{
		if (warrantNum == "" && searchBy == "warrantNumber")
		{
			theForm.juvenileNum.value = "";
			theForm.referralNum.value = "";			
			msg = "Warrant Number is required for search.";
			theForm.warrantNum.focus();
		}	
		if (searchBy == "juvNumber"){	
			theForm.warrantNum.value = "";		
			var juvNum = Trim(theForm.juvenileNum.value);
			var refNum = Trim(theForm.referralNum.value);
			theForm.juvenileNum.value = juvNum;
			theForm.referralNum.value = refNum;
			if (theForm.juvenileNum.value == "" && theForm.referralNum.value == "" ) 
			{
				msg = "Juvenile Number and Referral Number are required for search.";
				theForm.juvenileNum.focus();
			}
		    if ((theForm.juvenileNum.value != "") && (theForm.referralNum.value == ""))
		    {
			    msg = "Referral Number is required for search.";
			    theForm.referralNum.focus();
		    } 
		    if ((theForm.juvenileNum.value == "") && (theForm.referralNum.value != ""))
		    {
			    msg = "Juvenile Number is required for search.";
			    theForm.juvenileNum.focus();
		    }
		    if (msg == "")
		    {
				var juvenileNum = Trim(theForm.juvenileNum.value);
				theForm.juvenileNum.value = juvenileNum;				
				if(juvenileRegex.test(juvenileNum) == false)
				{
					msg = "Juvenile Number should be an integer.\n";
					theForm.juvenileNum.focus();			
				}
				var referralNum = Trim(theForm.referralNum.value);
				theForm.referralNum.value = referralNum;
				if(referralRegex.test(referralNum) == false)
				{
					if (msg == "")
					{	
						theForm.referralNum.focus();
					}	
					msg += "Referral Number should be a four digit integer.";
				}			
			}
		}
     }
   
   if ((msg == "") && 
   	   (warrantType == "reqAckDTA" ||
   	   warrantType == "reqSigOIC" ||
   	   warrantType == "releaseDecision" ||
   	   warrantType == "releaseToJP" ||
   	   warrantType == "releaseToPerson" ||
   	   warrantType == "warrantService" ||
   	   warrantType == "processReturn" ||
   	   warrantType == "retSigStatus" ||
   	   warrantType == "recall" ||
   	   warrantType == "inactivate"))
   {
   		var lastName = Trim(theForm.lastName.value);
   		var firstName = Trim(theForm.firstName.value);
   		theForm.lastName.value = lastName;
   		theForm.firstName.value = firstName;
   		if (warrantNum == "" && searchBy == "warrantNumber")
		{		
	   		theForm.lastName.value = "";
   			theForm.firstName.value = "";			
			msg = "Warrant Number is required for search.";
			theForm.warrantNum.focus();
			return false;
		}
		if (searchBy == "juvenileName")
		{
		    	customValRequired("lastName", "Last Name is required for search.");
				customValMask("lastName", "Juvenile Last Name must be at least 2 valid characters or 2 valid characters before an *.","/^([a-zA-Z']{2})([a-zA-Z '.-]*)([*]?)([a-zA-Z '.-]*)$/");
				return validateCustomStrutsBasedJS(theForm);
    	}					
	}

/*  These search pages only have Warrant Number search */	
	if ((msg == "") &&
	    (warrantType == "actARR" ||
		warrantType == "actDTA" ||
		warrantType == "updateVOP" ||
		warrantType == "updateOIC"))
	{
		warrantNum = Trim(theForm.warrantNum.value);
		theForm.warrantNum.value = warrantNum;
		if (warrantNum == "")
		{
			msg = "Warrant Number is required for search.";
			theForm.warrantNum.focus();
		}
		if(warrantNum != "")
		{
			if(warrantRegex.test(warrantNum) == false)
			{
				msg = "Warrant number should be an integer value.";
			}
		}		
	}
    if (msg == "")
    {
		return true;
    }
    alert(msg);
    return false;
}

//imitates the reset button by clearing the text fields only
//param: theForm - the form object 
 function resetClear(theForm){
  var focusSet = false; 
  for(var i=0; i<theForm.length; i++)
  {
     if(theForm.elements[i].type == 'text')
     {
         theForm.elements[i].value = "";
         if (!focusSet){
            theForm.elements[i].focus();
            focusSet = true;
         }
     }
   }
   return true;
 }
 
function validateJuvenileFields(theForm)
{	
	if(theForm.warrantNum != null)
	{
		var warrantNum = Trim(theForm.warrantNum.value);
		
		var re = /^\d+$/;
		if(warrantNum != "")
		{
			if(re.test(warrantNum) == false)
			{
				alert("Warrant number should be an integer value.");
				return false;
			}
		}
	}
	
return true;
}

