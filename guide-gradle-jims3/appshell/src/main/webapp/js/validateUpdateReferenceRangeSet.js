function validate() {
  var errorMsg = null;
  var errorField = null;
  var frm = document.forms[0];

  trim(frm.SetName);
  trim(frm.Description);
  trim(frm.Text);

  if (frm.SetName.value == "") {
    errorField = frm.SetName;
    errorMsg = "You must enter a name.";
  } else
  if (frm.SetName.value.length > 50) {
    errorField = frm.SetName;
    errorMsg = "The set name cannot exceed 50 characters.";
  } else
  if (frm.SetName.value.indexOf('"') > -1) {
    errorField = frm.SetName;
    errorMsg = 'The set name cannot contain a double quote character.';
  } else
  if (frm.Description.value == "") {
    errorField = frm.Description;
    errorMsg = "You must enter a description";
  } else
  if (frm.Description.value.length > 50) {
    errorField = frm.Description;
    errorMsg = "The description cannot exceed 50 characters.";
  } else
  if (frm.Reason && frm.Reason.value == "") {
    errorField = frm.Reason;
    errorMsg = "You must enter a reason when editing.";
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
  disableSubmitButton();
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
