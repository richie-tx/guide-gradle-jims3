
function validateFields()
{
	
	var thisForm = document.forms[0];

	trim(thisForm["currentProgram.programName"]);
	trim(thisForm["currentProgram.programCode"]);
	trim(thisForm["currentProgram.startDate"]);
	trim(thisForm["currentProgram.endDate"]);
	trim(thisForm["currentProgram.description"]);
	trim(thisForm["currentProgram.tjjdEdiCode"]);
	if(thisForm["currentProgram.startDate"] != null)
	{
		if(thisForm["currentProgram.startDate"].value != "")
		{
			var currDate = new Date();
			var year = currDate.getYear();

			if(year < 1000)
				year += 1900;		  

			var today = new Date(year, currDate.getMonth(),currDate.getDate());
			var myStartDate = new Date(thisForm["currentProgram.startDate"].value);
			var myEndDate = new Date(thisForm["currentProgram.endDate"].value);
			var fundStartDate= new Date(thisForm["currentProgram.fundStartDate"].value);
			//var fundEndDate = new Date(thisForm["currentProgram.fundEndDate"].value);
			//bug fix for 121552 was myStartDate < today before
			if(myStartDate > today)
			{
			  alert("Start date cannot be a past date.");
			  thisForm["currentProgram.startDate"].focus();

		    return false;
			}	

			if(thisForm["currentProgram.endDate"].value != "")
			{
				if(myEndDate < myStartDate)
				{
				  alert("End date cannot be before Start date.");
			    thisForm["currentProgram.endDate"].focus();
			    
					return false;
				}
			}
			var alphaNumericRegExp = /^[a-zA-Z0-9 ]*$/;
			var fld = thisForm["currentProgram.programCode"].value;
			if(fld=="")
			{
				alert("Program Code is required.");
				thisForm["currentProgram.programCode"].focus();
				return false;
			}
			
			if(fld && fld.length > 10){
				alert("Program Code length must be 10 characters or less");
				thisForm["currentProgram.programCode"].focus();
				return false;
			}
			
			var pattern = /^[a-z0-9]+$/i;
			var isValidPattern = pattern.test(fld);
			if(!isValidPattern){
				alert("Program Code contains invalid character(s). Only alphanumeric characters allowed.");
				thisForm["currentProgram.programCode"].focus();
				return false;
			}
			
			if(thisForm["currentProgram.tjjdEdiCode"].value != ""){
				if ( !pattern.test( thisForm["currentProgram.tjjdEdiCode"].value ) ){
					alert("Tjjd Edi Code contains invalid character(s). Only alphanumeric characters allowed.");
					thisForm["currentProgram.tjjdEdiCode"].focus();
					return false;
				}
			}
			/*if(thisForm["currentProgram.fundStartDate"].value != "")
			{
				
				if(myStartDate > fundStartDate)
				{					
				  alert("Fund Start Date cannot be before Program Start date.");
				  thisForm["currentProgram.fundStartDate"].focus();
				  return false;
				}
			}
			if(thisForm["currentProgram.fundEndDate"].value != "")
			{
				if(fundEndDate < fundStartDate)
				{
					  alert("Fund End date cannot be before Fund Start date.");
					  thisForm["currentProgram.fundEndDate"].focus();
					  return false;
				}
			}*/
		}
		else
		{
			alert("Program Start date is required.");
			thisForm["currentProgram.startDate"].focus();
			return false;
		}
		
		//return validateProgramForm(thisForm);
	}
	
	var transferredProgRef = thisForm["currentProgram.transferredProgRef"]; 
	var transferredProgRefYes = thisForm["transferredProgRefYes"]; 
	var transferredProgRefNo = thisForm["transferredProgRefNo"]; 
	
	if(transferredProgRefYes.checked === true){
		transferredProgRef.value = "1";
	}			
	
	if(transferredProgRefNo.checked === true){
		transferredProgRef.value = "0";
	}
	
	var selectedSupervisionCategories = document.getElementById("selectedSupervisionCategories"); //thisForm["selectedSupervisionCategories"];
	if(transferredProgRef.value === "1" && selectedSupervisionCategories.selectedOptions.length === 0){
		
		alert("At least one supervion category must be selected if \"Program Referral Transferable\" is set to Yes");
		selectedSupervisionCategories.focus();
		return false;
	}
	
	/*else
	{		
		
		
		if(thisForm["currentProgram.fundEndDate"].value != "")
		{
			var fundStartDate= new Date(thisForm["currentProgram.fundStartDate"].value);
			var fundEndDate = new Date(thisForm["currentProgram.fundEndDate"].value);		
			if(fundEndDate < fundStartDate)
			{
			  alert("Fund End date cannot be before Fund Start date.");
			  thisForm["currentProgram.fundEndDate"].focus();		    
				return false;
			}
		}
	}*/
	//return validateProgramForm(thisForm); rewriting struts validations as its not working
	return validateStruts(thisForm);
	
}
function validateStruts(theForm) {
	var msg = "";
	var alphaNumWithSymbolsRegExp = /^[a-zA-Z0-9 -.]*$/;//'^[a-zA-Z0-9 '.\-]*$';		
	if (theForm.txtPgmname.value == null || theForm.txtPgmname.value == ""){
		msg="Name is required.\n";
		theForm.txtPgmname.focus();
	}	
	
	if (theForm.tarIntid.value == null || theForm.tarIntid.value == "") {

		msg +="Target Intervention is required.\n";
		theForm.tarIntid.focus();
	}
	if (theForm.statePgmcode.value == null || theForm.statePgmcode.value == "") {

		msg +="State Program Code is required.\n";
		theForm.statePgmcode.focus();
	}
	if (theForm.typePgmcode.value == null || theForm.typePgmcode.value == "") {

		msg +="Program Type Code is required.\n";
		theForm.typePgmcode.focus();
	}
	if (theForm.createPgm.value == null || theForm.createPgm.value == "") {

		msg +="Create Program is required.\n";
		theForm.createPgm.focus();
	}
	if (theForm.pgmLocation.value == null || theForm.pgmLocation.value == "") {

		msg +="Program Location is required.\n";
		theForm.pgmLocation.focus();
	}
	if (theForm.pgmCategory.value == null || theForm.pgmCategory.value == "") {

		msg +="Program Category is required.\n";
		theForm.pgmCategory.focus();
	}
	if (theForm.programFundSourceCd.value == null || theForm.programFundSourceCd.value == ""){
		msg +="Source Fund is required.\n";
		theForm.programFundSourceCd.focus();
	}
	if (theForm["currentProgram.description"].value == null || theForm["currentProgram.description"].value == ""){
		msg +="Description is required.\n";		
	}
	/*if (theForm["currentProgram.maxYouth"].value == null || theForm["currentProgram.maxYouth"].value == ""){
		msg +="Maximum Youth in Program is required.";		
	}*/
	
	if (msg != "") {
		alert(msg);
		return false;
	}
	if (theForm.txtPgmname.value != null && theForm.txtPgmname.value != "")
	{
		
		if (alphaNumWithSymbolsRegExp.test(theForm.txtPgmname.value
				.trim(), alphaNumWithSymbolsRegExp) == false) {
			alert("Name must be alphanumeric.");
			theForm.txtPgmname.focus();
			return false;
		}
		
	}
	
	if(theForm.programCode)
	{		
		if (theForm.programCode.value != null && theForm.programCode.value != "")
		{
			
			if (alphaNumWithSymbolsRegExp.test(theForm.programCode.value
					.trim(), alphaNumWithSymbolsRegExp) == false) {
				alert("Program Code must be alphanumeric.");
				theForm.programCode.focus();
				return false;
			}
			
		}
	}
	
	return true;
}

function trim(textbox) 
{
  if (textbox) 
	{
    while (textbox.value.substring(0,1) == " ") 
		{
      textbox.value = textbox.value.substring(1);
    }
  }
}

//Discontinue Date
if(typeof $("#discontinueDate") != "undefined"){
	datePickerSingle($("#discontinueDate"),"Discontinue Date",true);
}








