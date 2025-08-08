<!-- Javascript for emulated validation for Juvenile Search to View -->
function clearSearchInputs(){
	var iTexts = document.getElementsByTagName("input");
	var iSelects = document.getElementsByTagName("select");
	if (iTexts.length > 0)
	{
		for(t = 0; t < iTexts.length; t++){
			if(iTexts[t].type == "text")
			{
				iTexts[t].value = "";
			}	
		}
	}
	for (i= 0; i < iSelects.length; i++)
	{
		if (iSelects[i].name != "searchType")
		{
		iSelects[i].selectedIndex = 0;
		}
	}
}

function evalSearch(el){
    clearSearchInputs();

	if (el.searchType.options[el.searchType.selectedIndex].value == "warrantNumber"){
		showHide('warrantNumber', 1);
		showHide('juvName', 0);
        showHide('adultName', 0);
       	showHide('warrantOriginatorID', 0);
       	showHide('warrantTypeId', 0);
        showHide('officerId', 0);
		document.getElementsByName("warrantNum")[0].focus();

	}else if (el.searchType.options[el.searchType.selectedIndex].value == "juvenileName"){
		showHide('juvName', 1);
		showHide('warrantNumber', 0);
        showHide('adultName', 0); 
		showHide('warrantOriginatorID', 0);			
        showHide('warrantTypeId', 0);
        showHide('officerId', 0);
		document.getElementsByName("juvenileName.lastName")[0].focus();
	
	}else if (el.searchType.options[el.searchType.selectedIndex].value == "warrantTypeId"){
       	showHide('warrantTypeId', 1);
		showHide('warrantNumber', 0);
		showHide('juvName', 0);
        showHide('adultName', 0);
       	showHide('warrantOriginatorID', 0);
        showHide('officerId', 0); 
		document.getElementsByName("warrantTypeId")[0].focus();
			
	}else if (el.searchType.options[el.searchType.selectedIndex].value == "officerId"){
        showHide('officerId', 1);
		showHide('warrantNumber', 0);
		showHide('juvName', 0);
        showHide('adultName', 0);
       	showHide('warrantOriginatorID', 0);
       	showHide('warrantTypeId', 0); 
		document.getElementsByName("officerId")[0].focus();
			
	}else if (el.searchType.options[el.searchType.selectedIndex].value == "adultName"){
	    showHide('adultName', 1);
        showHide('warrantNumber', 0);
	    showHide('juvName', 0);
		showHide('warrantOriginatorID', 0);
        showHide('warrantTypeId', 0);
        showHide('officerId', 0);
	    document.getElementsByName("associateName.lastName")[0].focus();
		    
	}else if (el.searchType.options[el.searchType.selectedIndex].value == "warrantOriginatorId"){
		showHide('warrantOriginatorId', 1);
		showHide('warrantNumber', 0);
	    showHide('juvName', 0);
        showHide('adultName', 0);
        showHide('warrantTypeId', 0);
        showHide('officerId', 0); 
		document.getElementsByName("warrantOriginatorId")[0].focus();
			
	}
	
}

function validateSearchCriteria(theForm){
	var searchChoice = document.getElementsByName("searchType");
	var searchBy = theForm.searchType.options[theForm.searchType.selectedIndex].value;
	var returnString = true;
	clearAllValArrays();
	if (searchBy == "warrantNumber")
	{
		var warrantNum = $("input#warrantNum").val();
		   if ( warrantNum !== "" && !$.isNumeric( warrantNum )) {
		   //Check if phone is numeric
		   $("input#warrantNum").focus(); //Focus on field
		   return false;
		}
	}
	
	/* begin name searches edits */
	if (searchBy == "juvenileName")
	{
		var lastName = document.getElementsByName("juvenileName.lastName")[0];
		var firstName = document.getElementsByName("juvenileName.firstName")[0];

		if (firstName.value == "" &&
	   	    lastName.value == "" &&
    	    theForm.warrantTypeId.selectedIndex == 0)
    	{
        	alert("At least 1 search field is is required for search.");
         	returnString = false;    	      	   
    	}   
		if (firstName.value != "" && lastName.value == "")
		{
			alert("Juvenile Last Name is required for search when First Name is entered.");
	    	lastName.focus();
    		returnString = false;
	    }
	    if(lastName.value != "")
	    {
	    	customValRequired("juvenileName.lastName", "Last Name is required for search.");
			customValMask("juvenileName.lastName", "Juvenile Last Name must be at least 2 valid characters or 2 valid characters before an *.","/^([a-zA-Z']{2})([a-zA-Z '.-]*)([*]?)([a-zA-Z '.-]*)$/");
			returnString = validateCustomStrutsBasedJS(theForm);
	  	}
	}	
	
	if (searchBy == "warrantTypeId")
	{
		var iSelects = document.getElementsByName("warrantTypeId");
		var iWarrantStatus = document.getElementsByName("warrantStatusId");
		if (theForm.warrantTypeId.selectedIndex == 0)
		{
			customValRequired("warrantTypeId", "A Warrant Type option needs to be selected.");
			returnString = validateCustomStrutsBasedJS(theForm);
		}
		if (theForm.warrantTypeId.value == "OIC") 
		{
			if (theForm.warrantStatusId.selectedIndex == 0) {
				customValRequired("warrantStatusId", "A Warrant Status option needs to be selected for Order of Immediate Custody warrants.");
			}
		}
		addMMDDYYYYDateValidation("searchDate1", "Begin Date must be in the format MM/DD/YYYY");
		addMMDDYYYYDateValidation("searchDate2", "End Date must be in the format MM/DD/YYYY");
		returnString = validateCustomStrutsBasedJS(theForm);
	}
	
	if (searchBy == "officerId")
	{
		var iSelects = document.getElementsByName("officerIdTypeId");
		customValRequired("officerId", "Officer ID Number is required for search.");
		customValRequired("officerAgencyId", "Department Code  is required for search.");
		if (iSelects.value != "")
		{
			customValRequired("officerIdTypeId", "An Officer Id Type option needs to be selected.");
			returnString = validateCustomStrutsBasedJS(theForm);
		}
	}
	
	if (searchBy == "adultName")
	{
		var associateLastName = document.getElementsByName("associateName.lastName")[0];
	    	customValRequired("associateName.lastName", "At least 2 valid characters of the Last Name are required for search.");
			customValMask("associateName.lastName", "Responsible Adult Last Name must be at least 2 valid characters or 2 valid characters before an *.","/^([a-zA-Z']{2})([a-zA-Z '.-]*)([*]?)([a-zA-Z '.-]*)$/");
			returnString = validateCustomStrutsBasedJS(theForm);
	}
	
	if (searchBy == "warrantOriginatorID")
	{
		var warrantOriginatorId = document.getElementsByName("warrantOriginatorID");
		if(warrantOriginatorId.length <5)
		{
			alert(warrantOriginatorId.length);
			alert("Warrant Originator ID must be at least 5 characters to find warrants.");
		}
		else if(warrantOriginatorId.value == "" || warrantOriginatorId.value == null)
		{
			customValRequired("warrantOriginatorId", "Warrant Originator ID is required for search.");
		}
		returnString = validateCustomStrutsBasedJS(theForm);
	}
	
   return returnString;
}

function evalWarrentTypeSelect(el )
{
	if (el.selectedIndex > 0){
		show("warrantstatus", 1);
		show("warrantStatusNotReqd", 1);
		show("warrantStatusReqd", 0);
		if (el.options[el.selectedIndex].value == "OIC") {
			show("warrantStatusReqd", 1);
			show("warrantStatusNotReqd", 0);
		}
	}
	document.forms[0].warrantStatusId.selectedIndex = 0;
	document.forms[0].searchDate1.value = "";
	document.forms[0].searchDate2.value = "";
	document.forms[0].warrantStatusId.focus();
}
