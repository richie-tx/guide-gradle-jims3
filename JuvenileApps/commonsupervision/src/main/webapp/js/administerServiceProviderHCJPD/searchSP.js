
function validateFields()
{
	var thisForm = document.forms[0];	
	trim(thisForm["currentProgram.programName"]);
	trim(thisForm["frmEndDate"]);
	trim(thisForm["toEndDate"]);
	trim(thisForm["providerName"]);
	trim(thisForm["currentProgram.programService.serviceName"]);	

	if(document.getElementById("type").value=="PR")
	{
		thisForm["providerName"].value="";
		thisForm["statusId"].selectedIndex = 0;
		thisForm["isInHouse"].selectedIndex = 0;
		thisForm["currentProgram.programService.serviceName"].value = "";
		thisForm["currentProgram.programService.typeId"].selectedIndex = 0;
		thisForm["currentProgram.programService.statusId"].selectedIndex = 0;	
		//if(!validateProviderSearchForm(thisForm))commented out as struts validations not working and rewriting those 
		if(!validateStrutsPRFields(thisForm))	
		   return false;
		if(	thisForm["frmEndDate"].value != "" )
		{
			result = validateDate(thisForm["frmEndDate"].value);					
			if (result == false){
				alert("End Date From is invalid date or wrong date format.");
				thisForm["frmEndDate"].focus();
				return false;
			}
		}
		if(	thisForm["toEndDate"].value != "" )
		{
			result = validateDate(thisForm["toEndDate"].value);					
			if (result == false){
				alert("End Date To is invalid date or wrong date format.");
				thisForm["toEndDate"].focus();
				return false;
			}
		}
	}
	
	if(document.getElementById("type").value=="SP")
	{	
		thisForm["currentProgram.programName"].value="";
		thisForm["frmEndDate"].value ="";
		thisForm["toEndDate"].value = "";
		thisForm["currentProgram.stateProgramCodeId"].selectedIndex = 0;
		thisForm["currentProgram.targetInterventionId"].selectedIndex = 0;
		thisForm["currentProgram.statusId"].selectedIndex = 0;	
		thisForm["currentProgram.programService.serviceName"].value = "";		
		thisForm["currentProgram.programService.typeId"].selectedIndex = 0;
		thisForm["currentProgram.programService.statusId"].selectedIndex = 0;
		if(thisForm["providerName"].value == "")
		{
			alert("Service Provider Name is required for search by Service Provider");
			thisForm["providerName"].focus();
			return false;			
		}
		
		//if(!validateProviderSearchForm(thisForm))commented out as struts validations not working and rewriting those 
		if(!validateStrutsSPFields(thisForm))
				return false;
	}
	// ER JIMS200075756 program code as required field --- Start
	if(document.getElementById("type").value=="PC")
	{	
		thisForm["providerName"].value="";
		thisForm["statusId"].selectedIndex = 0;
		thisForm["isInHouse"].selectedIndex = 0;
		thisForm["currentProgram.programService.serviceName"].value = "";
		thisForm["currentProgram.programService.typeId"].selectedIndex = 0;
		thisForm["currentProgram.programService.statusId"].selectedIndex = 0;
				
		if(thisForm["currentProgram.programCode"].value == "")
		{
			alert("Program Code is required for search by Program Code");
			thisForm["currentProgram.programCode"].focus();
			return false;			
		}
		
		var programCode = thisForm["currentProgram.programCode"].value;
		if(programCode && programCode.length > 10){
			alert("Program Code length must be 10 characters or less");
			thisForm["currentProgram.programCode"].focus();
			return false;
		}
		
		var pattern = /^[a-z0-9]+$/i;
		var isValidPattern = pattern.test(programCode);
		if(!isValidPattern){
			alert("Program Code contains invalid character(s). Only alphanumeric characters allowed.");
			thisForm["currentProgram.programCode"].focus();
			return false;
		}
		
	}
	// ER JIMS200075756 program code as required field --- End
	if(document.getElementById("type").value=="SV")
	{
		thisForm["currentProgram.programName"].value="";
		thisForm["frmEndDate"].value ="";
		thisForm["toEndDate"].value = "";		
		thisForm["currentProgram.stateProgramCodeId"].selectedIndex = 0;
		thisForm["currentProgram.targetInterventionId"].selectedIndex = 0;
		thisForm["currentProgram.statusId"].selectedIndex = 0;	
		thisForm["providerName"].value="";
		thisForm["statusId"].selectedIndex = 0;
		thisForm["isInHouse"].selectedIndex = 0;	
		//if(!validateProviderSearchForm(thisForm))commented out as struts validations not working and rewriting those 
		if(!validateStrutsSVFields(thisForm))
			return false;	
	}
	var mychoice=0;
	for(var i=0; i<thisForm.length;i++)
    {
    	if(thisForm.elements[i].type == "text")
    	{
    		  if(thisForm.elements[i].value !="")
    		    mychoice=1;
    	}
    	else if(thisForm.elements[i].selectedIndex>0)
    	{    		
    		if(document.getElementById("type").value=="PR")
			{
				if(thisForm.elements[i].name == "currentProgram.statusId")
				{
					if( mychoice == 0 )
					{
						alert("At least one other field is required for search by status.");    	
						return false;
					}
				}
			}
			if(document.getElementById("type").value=="SP")
			{
				if(thisForm.elements[i].name == "statusId")
				{
					if( mychoice == 0 )
					{
						alert("At least one other field is required for search by status.");    	
						return false;
					}
				}	
			}
			if(document.getElementById("type").value=="SV")
			{
				if(thisForm.elements[i].name == "currentProgram.programService.statusId")
				{
					if( mychoice == 0 )
					{
						alert("At least one other field is required for search by status.");    	
						return false;
					}
				}
			}
			
			
			if(thisForm.elements[i].name != "searchTypeId")
    			mychoice=1;
    	}
    }
     if(mychoice==0)
    {
    	alert("At least one field has to be selected or entered for search");    	
    	return false;
    }
     
     if ( true ) {
    	 spinner();
     }
	return true;	
}
function trim(textbox) {
  if (textbox) {
    while (textbox.value.substring(0,1) == " ") {
      textbox.value = textbox.value.substring(1);
    }
  }
}

function validateDate(fldValue){
	
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
}
function validateStrutsSPFields(theForm) {
	var alphaNumWithSymbolsRegExp = /^[a-zA-Z0-9]([a-zA-Z0-9 \/_\.\\'\-#,&]+)([*]?)$/;//'^[a-zA-Z0-9 '.\-]*$';		
	if (theForm.providerName.value != null && theForm.providerName.value != "")	{

		if (theForm.providerName.value.length < 2) {
			alert("Service Provider Name can not be less than 2 characters");
			return false;
		}
		if (alphaNumWithSymbolsRegExp.test(theForm.providerName.value
				.trim(), alphaNumWithSymbolsRegExp) == false) {
			alert("Service Provider Name must be at least 2 valid characters or 2 valid characters before the *.");
			theForm.providerName.focus();
			return false;
		}

	}
	return true;
}
function validateStrutsPRFields(theForm) 
{
	var alphaNumWithSymbolsRegExp = /^[a-zA-Z0-9]([a-zA-Z0-9 \/_\.\\'\-#,&]+)([*]?)$/;//'^[a-zA-Z0-9 '.\-]*$';		
	if (theForm.prgmName.value != null && theForm.prgmName.value != "") 
	{

		if (theForm.prgmName.value.length < 2) {
			alert("Program Name can not be less than 2 characters");
			return false;
		}
		if (alphaNumWithSymbolsRegExp.test(theForm.prgmName.value
				.trim(), alphaNumWithSymbolsRegExp) == false) {
			alert("Program Name must be at least 2 valid characters or 2 valid characters before the *.");
			theForm.prgmName.focus();
			return false;
		}
	}
	return true;
}
function validateStrutsSVFields(theForm) 
{
	var alphaNumWithSymbolsRegExp = /^[a-zA-Z0-9]([a-zA-Z0-9 \/_\.\\'\-#,&]+)([*]?)$/;//'^[a-zA-Z0-9 '.\-]*$';		
	if (theForm.srvcName.value != null && theForm.srvcName.value != "") 
	{

		if (theForm.srvcName.value.length < 2) {
			alert("Service Name can not be less than 2 characters");
			return false;
		}
		if (alphaNumWithSymbolsRegExp.test(theForm.srvcName.value
				.trim(), alphaNumWithSymbolsRegExp) == false) {
			alert("Service Name must be at least 2 valid characters or 2 valid characters before the *.");
			theForm.srvcName.focus();
			return false;
		}

	}
	return true;	

}

