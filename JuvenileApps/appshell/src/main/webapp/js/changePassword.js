function validate() {
  var frm = document.forms[0];
  var error = false;
  if (frm.Password.value == "" || frm.Password.value == null) {
    alert("You must enter a new password.");
    error = true;
  } else
  if (frm.Password.value.length < 6 || frm.Password.value.length > 20) {
    alert("The new password must be between 6 and 20 characters.");
    error = true;
  } else
  if (frm.Password.value != frm.ConfirmPassword.value) {
    alert("The passwords that you entered did not match.  Please try again.");
    error = true;
  }

  if (error) {
    frm.Password.value = "";
    frm.ConfirmPassword.value = "";
    frm.OldPassword.value = "";
    return false;
  } else{
    disableSubmitButton();
  return true;}
}

