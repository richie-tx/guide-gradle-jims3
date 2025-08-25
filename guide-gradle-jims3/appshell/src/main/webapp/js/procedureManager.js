function selectProcedure() {
  var procedureData = showModalDialog("/jims2/control/?Server=jims2&Topic=DisplayProcedureSearchPopUp");
  if (procedureData) {
    var index = procedureData.indexOf("|");
    var OID = procedureData.substring(0,index);
    var name = procedureData.substring(index+1);
    document.forms[0].SubProcedureOID.value = OID;
    document.forms[0].SubProcedureName.value = name;
    document.forms[0].addButton.focus();
  }
}


function validateAdd() {
  if (document.AddForm.SubProcedureOID.value == "") {
    alert('You must select a procedure before adding it.');
    return false;
  }
}

function validateEdit() {
  if (document.EditForm.SubProcedureOID.selectedIndex == -1) {
    alert('You must select a procedure before editing it.');
    return false;
  }
}

function validateDelete() {
  if (document.EditForm.SubProcedureOID.selectedIndex == -1) {
    alert('You must select a procedure before deleting it.');
    return false;
  }
}