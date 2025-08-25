function validate() {
  var errorMsg = null;
  var errorField = null;
  var frm = document.forms[0];

  trim(frm.RoleName);
  trim(frm.Description);
  trim(frm.Reason);

  if (frm.RoleName.value == "") {
    errorField = frm.RoleName;
    errorMsg = "You must enter a role name.";
  } else 
  if (frm.RoleName.value.indexOf('"') > -1) {
    errorField = frm.RoleName;
    errorMsg = 'The role name cannot contain a double quote character.';
  } else
  if (frm.Description.value == "") {
    errorField = frm.Description;
    errorMsg = "You must enter a description.";
  } else
  if (frm.Description.value.length > 100) {
    errorField = frm.Description;
    errorMsg = "The description cannot be more than 100 characters.";
  } else 
  if (frm.Reason && frm.Reason.value == "") {
    errorField = frm.Reason;
    errorMsg = "You must enter a reason for editing this record before saving.";
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
