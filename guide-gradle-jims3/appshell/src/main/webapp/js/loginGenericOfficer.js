function validateFields() {
  var errorMsg = null;
  var errorField = null;
  var frm = document.forms[0];

  trim(frm.badgeNumber);
  trim(frm.otherIdNumber);
 

  
  if (frm.badgeNumber.value == "" && frm.otherIdNumber.value == "") {
      alert("Badge number or other ID number must be entered for Login.");
     frm.badgeNumber.focus();
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
