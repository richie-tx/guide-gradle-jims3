function validate() {
  var errorMsg = null;
  var errorField = null;
  var frm = document.forms[0];

  trim(frm.Quantity);
  trim(frm.ShippingInstructions);
  trim(frm.CollectionInstructions);

  if (frm.CollectionInstructions.value.length > 4000) {
    errorField = frm.CollectionInstructions;
    errorMsg = "Collection Instructions should not be more than 4000 characters.";
  } else
  if (frm.ShippingInstructions.value.length > 4000) {
    errorField = frm.ShippingInstructions;
    errorMsg = "Shipping Instructions should not be more than 4000 characters.";
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
    frm.Submit.disabled = true;
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
