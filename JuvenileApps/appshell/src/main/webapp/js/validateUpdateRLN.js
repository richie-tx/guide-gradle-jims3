function validate() {
  var errorMsg = null;
  var errorField = null;
  var frm = document.forms[0];
  
  if (frm.RLNNum.value == "") {
    errorField = frm.RLNNum;
    errorMsg = "You must have an RLN Number.";
  }else 
  if (frm.RLNNum.value<100000){
    errorField = frm.RLNNum;
	errorMsg = "RLN Numbers must be greater than 100,000";
  }else
  if (frm.RLNNum.value != "" && frm.RLNNum.value.match(/\D/)){
    errorField = frm.RLNNum;
	errorMsg = "RLN Numbers must be numeric";  
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
