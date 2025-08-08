function checkForOther(el, fldName){
	show(fldName, 0);
	if (el.options[el.selectedIndex].value == "OTH"){
		show(fldName, 1)
		if (fldName.indexOf("presented") > -1) {
			document.forms[0].presentedByFirstName.focus();
		}else {
			document.forms[0].whoSignedFirstName.focus();
		}	
	}
}
function validateNextInput(theForm){
	var msg = "";
	var result = "";
	var alphaNumSymbolsRegExp = /^[a-zA-Z0-9][a-zA-Z0-9 \.\\(),\x27\x3B\x26\x2f\x7C\-]*$/;
	var actionSelected = false;
	var firstCBElement = -1;

	if (theForm.courtActionfiledDate.value == ""){
		msg += "Present Date is required.\n";
		theForm.courtActionfiledDate.focus();
	} else {
		result = validateDate(theForm.courtActionfiledDate.value, "Filed Date", true)
		if (result != "") {
			theForm.courtActionfiledDate.focus();
			msg += result;
		} 
	}
	if (theForm.presentedById.selectedIndex == 0){
		if (msg == ""){
			theForm.presentedById.focus();
		}
		msg += "Presented By is required.\n";
	} 

	if (theForm.presentedById.selectedIndex > 0 && theForm.presentedById.options[theForm.presentedById.selectedIndex].value != "OTH"){
		theForm.presentedByName.value = theForm.presentedById.options[theForm.presentedById.selectedIndex].text;
	}
	
	if (theForm.presentedById.options[theForm.presentedById.selectedIndex].value == "OTH"){
		theForm.presentedByFirstName.value = trimAll(theForm.presentedByFirstName.value);
		theForm.presentedByLastName.value = trimAll(theForm.presentedByLastName.value);
		if (theForm.presentedByFirstName.value == ""){
			if (msg == ""){
				theForm.presentedByFirstName.focus();
			}
			msg += "Presented By First Name is required.\n";
		} else if (alphaNumSymbolsRegExp.test(theForm.presentedByFirstName.value,alphaNumSymbolsRegExp) == false){
			if (msg == ""){
				theForm.presentedByFirstName.focus();
			}
			msg += "Presented By First Name contains an invalid character.\n";
		}
			
		if (theForm.presentedByLastName.value == ""){
			if (msg == ""){
				theForm.presentedByLastName.focus();
			}
			msg += "Presented By Last Name is required.\n";
		}else if (alphaNumSymbolsRegExp.test(theForm.presentedByLastName.value,alphaNumSymbolsRegExp) == false){
			if (msg == ""){
				theForm.presentedByLastName.focus();
			}
			msg += "Presented By Last Name contains an invalid character.\n";
		}
		theForm.presentedByName.value = theForm.presentedByLastName.value + ", " + theForm.presentedByFirstName.value; 		
	}
	
	if (theForm.whoSignedId.selectedIndex > 0 && theForm.whoSignedId.options[theForm.whoSignedId.selectedIndex].value != "OTH"){
		theForm.whoSignedName.value = theForm.whoSignedId.options[theForm.whoSignedId.selectedIndex].text;
	}
	
	if (theForm.whoSignedId.options[theForm.whoSignedId.selectedIndex].value == "OTH"){
		theForm.whoSignedFirstName.value = trimAll(theForm.whoSignedFirstName.value);
		theForm.whoSignedLastName.value = trimAll(theForm.whoSignedLastName.value);
		if (theForm.whoSignedFirstName.value == ""){
			if (msg == ""){
				theForm.whoSignedFirstName.focus();
			}
			msg += "Who Signed First Name is required.\n";
		} else if (alphaNumSymbolsRegExp.test(theForm.whoSignedFirstName.value,alphaNumSymbolsRegExp) == false){
			if (msg == ""){
				theForm.whoSignedFirstName.focus();
			}
			msg += "Who Signed First Name contains an invalid character.\n";
		}
		if (theForm.whoSignedLastName.value == ""){
			if (msg == ""){
				theForm.whoSignedLastName.focus();
			}
			msg += "Who Signed Last Name is required.\n";
		} else if (alphaNumSymbolsRegExp.test(theForm.whoSignedLastName.value,alphaNumSymbolsRegExp) == false){
			if (msg == ""){
				theForm.whoSignedLastName.focus();
			}
			msg += "Who Signed Last Name contains an invalid character.\n";
		}
		theForm.whoSignedName.value = theForm.whoSignedLastName.value + ", " + theForm.whoSignedFirstName.value; 		
	}

	for (x =0; x<theForm.length; x++){
		if (theForm.elements[x].type == "checkbox"){
			if (firstCBElement == -1){
				firstCBElement = x;
			}	
			if (theForm.elements[x].checked == true) {
				actionSelected = true;
				break;
			}
		}	
	}
	if (!actionSelected){
		if (msg == "" && firstCBElement > -1){	
			theForm.elements[firstCBElement].focus();
		}
		msg += "Court Action selection required.\n";
	}	
	
	if (msg == ""){	
		return true;
	}
	alert(msg);
	return false;	
}
function presetSelectNames() {
	var logonId = document.forms[0].presentedbyLogonId.value.toUpperCase();
	var presentedBys = document.getElementsByName("presentedById");
	if (logonId > "" && presentedBys.length > 0){
		for (x =0; x<presentedBys[0].options.length; x++){
			if (presentedBys[0].options[x].value.toUpperCase() == logonId){
				presentedBys[0].options[x].selected = true;
				break;
			}	
		}
	}
	var courtNum = document.forms[0].whoSignedCourtNum.value.toUpperCase();
	var whoSigneds = document.getElementsByName("whoSignedId");
	if (courtNum > "" && whoSigneds.length > 0){
		for (y =0; y<whoSigneds[0].options.length; y++){
			if (whoSigneds[0].options[y].value.toUpperCase() == courtNum){
				whoSigneds[0].options[y].selected = true;
				break;
			}	
		}
	}
}