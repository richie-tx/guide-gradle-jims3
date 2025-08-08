function validateFields() {
	var thisForm = document.forms[0];
	trim(thisForm["currentProgram.programService.updatedName"]);
	trim(thisForm["currentProgram.programService.code"]);
	trim(thisForm["currentProgram.programService.maxEnrollment"]);
	trim(thisForm["currentProgram.programService.cost"]);

	// if(validateServiceForm(thisForm))commented as struts validation not
	// working and wrote it in page level
	if (validateServiceFormStructs(thisForm)) {
		/* statusId = P for pending */
		if (thisForm["actionType"].value == "createService"
				|| (thisForm["actionType"].value == "updateService" && thisForm["statusId"].value == "P")) {
			if (thisForm["currentProgram.programService.code"].value == "") {
				alert("Code is required.");
				thisForm["currentProgram.programService.code"].focus();
				return false;
			}
			var codeRegex = /^[a-zA-Z0-9]*$/;
			if (codeRegex
					.test(thisForm["currentProgram.programService.code"].value) == false) {
				alert("Code must be alphanumeric value.");
				thisForm["currentProgram.programService.code"].focus();
				return false;
			}

		}
		if (thisForm["currentProgram.programService.cost"].value != ""
				&& thisForm["currentProgram.programService.costBasisId"].selectedIndex <= 0) {
			alert("Invalid cost : Cost basis must be provided.");
			thisForm["currentProgram.programService.costBasisId"].focus();
			return false;
		}
		if (thisForm["currentProgram.programService.cost"].value == ""
				&& thisForm["currentProgram.programService.costBasisId"].selectedIndex > 0) {
			alert("Cost must be provided to select cost basis.");
			thisForm["currentProgram.programService.cost"].focus();
			return false;
		}
		if (thisForm["currentProgram.programService.description"].value == "") {
			alert("Description must be provided to create Service.");
			thisForm["currentProgram.programService.description"].focus();
			return false;
		}
		return true;
	} else
		return false;

}
function validateServiceFormStructs(theForm) {
	var msg = "";
	var alphaNumWithSymbolsRegExp = /^[a-zA-Z0-9][a-zA-Z0-9 \/_\.\\'\-]*$/;
	var numberRegExp=/^[0-9]*$/;		
	if (theForm.serviceName.value == null || theForm.serviceName.value == "") {
		msg = "Name is required.\n";
		theForm.serviceName.focus();
	} 
	else {
		
		if (alphaNumWithSymbolsRegExp.test(theForm.serviceName.value,
				alphaNumWithSymbolsRegExp) == false) {
			//alert(theForm.serviceName.value);
			msg +="Name must be alphanumeric.\n";
			theForm.serviceName.focus();			
		}
	}

	if (theForm.serviceType.value == null || theForm.serviceType.value == "") {

		msg += "Type is required.\n";
		theForm.serviceType.focus();
	}
	if (theForm.txtMaxenrollment.value == null
			|| theForm.txtMaxenrollment.value == "") {

		msg += "Max Enrollment is required.\n";
		theForm.txtMaxenrollment.focus();
	} 
	else 
	{
		if (numberRegExp.test(theForm.txtMaxenrollment.value.trim(),
				numberRegExp) == false) {
			msg +="Max Enrollment must be numeric.\n";
			theForm.txtMaxenrollment.focus();			
		}
		if(theForm.txtMaxenrollment.value==0)
			{
				msg +="Max Enrollment is not in the range 1 through 9999.\n";
				theForm.txtMaxenrollment.focus();				
			}
	}

	if (theForm.txtCost.value != null && theForm.txtCost.value != "") {
		var costRegExp=/^([1-9]{1}([0-9]{0,5})(\.[0-9]{0,2})?|0(\.[0-9]{0,2})?)$/;
		if (costRegExp.test(theForm.txtCost.value.trim(),
				costRegExp) == false) {
			msg +="Cost is not a valid currency. Please note no commas or dollar signs are allowed. Example: for $1,000 enter 1000.\n";
			theForm.txtCost.focus();
		}
	}

	if (msg != "") {
		alert(msg);
		return false;
	}
	
	return true;
}

function trim(textbox) {
	if (textbox) {
		while (textbox.value.substring(0, 1) == " ") {
			textbox.value = textbox.value.substring(1);
		}
	}
}