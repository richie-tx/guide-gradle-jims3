function validateInputs(theButton, theForm)
{
	var msg = "";
	var result = "";
	var result2 = "";
	if (typeof(theForm.payTypeId) == "undefined"){
		if (theButton.value != "Next"){
			return true;
		}
		result2 = validateComments(theForm.create1Comments.value, "Comments");
		if (result2 != ""){
			theForm.create1Comments.focus();
			alert(result2);
			return false;
		}
		return true;
	} 
	
	theForm.amountOrdered.value = trimAll(theForm.amountOrdered.value);
	theForm.paidToDate.value = trimAll(theForm.paidToDate.value); 
	theForm.deliquentAmount.value = trimAll(theForm.deliquentAmount.value);

	if (theForm.payTypeId.selectedIndex == 0 &&
	    theForm.amountOrdered.value == "" && 
	    theForm.paidToDate.value == "" && 
	    theForm.deliquentAmount.value == "" && 
	    theButton.value != "Next"){
	   		alert("No data entered to create new Fee.");
			theForm.payTypeId.focus();
			return false;
	}

	if (theForm.amountOrdered.value > ""){
		result = validateCurrencyAmt("Amount Ordered", theForm.amountOrdered.value, false);
		if (isNaN(result)){
			if (msg == ""){
				theForm.amountOrdered.focus();
			}
			msg += result;
		} else {
			theForm.amountOrdered.value = result;
		}
	}
	
	if (theForm.paidToDate.value > ""){	
		result = validateCurrencyAmt("Paid to Date", theForm.paidToDate.value, true);
		if (isNaN(result)){
			if (msg == ""){
				theForm.paidToDate.focus();
			}
			msg += result;
		} else {
			theForm.paidToDate.value = result;
		}
	}

	if (theForm.deliquentAmount.value > ""){		
		result = validateCurrencyAmt("Delinquent Amount", theForm.deliquentAmount.value, true);
		if (isNaN(result)){
			if (msg == ""){
				theForm.deliquentAmount.focus();
			}
			msg += result;
		} else {
			theForm.deliquentAmount.value = result;
		}
	}

	if (msg != ""){
		alert(msg);
		return false;
	}
// validate currency amounts to arithmatically and logically correct	
	if (Number(theForm.paidToDate.value) > Number(theForm.amountOrdered.value)) {
		msg = "Paid to Date can not be more than Amount Ordered.\n";
		theForm.paidToDate.focus();
	}
	if (Number(theForm.deliquentAmount.value) > Number(theForm.amountOrdered.value)) {
		if (msg == "") {
			theForm.deliquentAmount.focus();
		}
		msg += "Delinquent Amount can not be more than Amount Ordered.\n";
	}	
	var maxDeqAmt = Number(theForm.amountOrdered.value) - Number(theForm.paidToDate.value);
	if (Number(theForm.deliquentAmount.value) > maxDeqAmt) {
		if (msg == "") {
			theForm.deliquentAmount.focus();
		}
		msg += "Delinquent Amount exceeds difference of Amount Ordered and Paid to Date.\n";
	}	
	if (theButton.value  == "Next"){
		var result2 = validateComments(theForm.create1Comments.value, "Comments");
		if (result2 != ""){
			if (msg == ""){
				theForm.create1Comments.focus();
			}
		   msg += result2;
		}   
	 }
	 if (msg != ""){
		alert(msg);
		return false;
	}
	return true;
}

function  validateCurrencyAmt(fldName, fldValue, zeroAllowed){
	var x = "";
	var currencyRegExp = /^([1-9]{1}([0-9]{0,15})(.[0-9]{2})|0(.[0-9]{2}))$/;
	if (fldValue == ""){
		if (zeroAllowed){ 
			return "0.00";
		} else {
			x = fldName + " is required.\n";
			return x
		}  
	}
	if (fldValue.indexOf(".") == -1){
		fldValue = fldValue + ".00";
	}
	if (currencyRegExp.test(fldValue, currencyRegExp) == false) {
		x = fldName + " contains invalid currency format.  Example: enter 1000 for $1000.\n";
	} else {
		x = fldValue
	}
	
	return x;
}