function selectAll() {
  doAll(true);
}

function clearAll() {
  doAll(false);
}

function doAll(check) {
  var frm = document.forms[0];
  for (var i=0; i<frm.elements.length; i++) {
    var element = frm.elements[i];
    if (element.type == "checkbox") {
      element.checked = check;
    }
  }
}

function validate(){
	disableSubmitButton();
	return true;
}