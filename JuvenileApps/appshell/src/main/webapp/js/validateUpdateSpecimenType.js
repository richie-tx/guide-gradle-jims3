function validate() {
  var errorMsg = null;
  var errorField = null;
  var frm = document.forms[0];

  trim(frm.type);
  trim(frm.shippingInstructions);
  trim(frm.collectionInstructions);

  if (frm.type.value == "") {
    errorField = frm.type;
    errorMsg = "You must enter a specimen type.";
  } else
  if (frm.type.value.indexOf('"') > -1) {
    errorField = frm.type;
    errorMsg = 'The specimen type cannot contain a double quote character.';
  } else
  if (frm.collectionInstructions.value.length > 4000) {
	errorField = frm.collectionInstructions;
    errorMsg = "The collection instructions cannot be more than 4000 characters.";
  } else
  if (frm.shippingInstructions.value.length > 4000) {
	errorField = frm.shippingInstructions;
	errorMsg = "The shipping instructions cannot be more than 4000 characters.";
  } else
  if (frm.Reason && frm.Reason.value == "") {
    errorField = frm.Reason;
    errorMsg = "You must enter a reason for editing this record.";
  } else
  if (frm.Reason && frm.Reason.value.length > 50) {
    errorField = frm.Reason;
    errorMsg = "The reason cannot exceed 50 characters.";
  } 
  
  if (errorMsg != null && errorField != null) {
    alert(errorMsg);
    errorField.focus();
    return false;
  } else {
  disableSubmitButton()
    return true;
  }
}



function trim(textbox) {
  if (textbox) {
    while (textbox.value.substring(0,1) == " ") {
      textbox.value = textbox.value.substring(1);
    }
  }
}
