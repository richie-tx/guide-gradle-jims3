function validateFields() {
  var errorMsg = null;
  var errorField = null;
  var frm = document.forms[0];

  trim(frm.employeeId);
   
  if (frm.employeeId.value == "" ) {
      alert("Employee ID must be entered for validation.");
     frm.employeeId.focus();
      return false;
  }
}

function trim(textbox) {
  if (textbox) {
    while (textbox.value.substring(0,1) == " ") {
      textbox.value = textbox.value.substring(1);
    }
  }
}
