function validateAdd() {
  if (document.AddForm.jims2LocationOID.selectedIndex == -1) {
    alert('You must select a location before adding it.');
    return false;
  }
}

function validateEdit() {
  if (document.EditForm.OID.selectedIndex == -1) {
    alert('You must select a location before editing it.');
    return false;
  }
}

function validateDelete() {
  if (document.EditForm.OID.selectedIndex == -1) {
    alert('You must select a location before deleting it.');
    return false;
  }

  document.EditForm.Topic.value='DeleteProcedureLocation'; 
  document.EditForm.ReplyTopic.value='DisplayProcedureLocationList';
}

function openView() {
	if (document.EditForm.OID.selectedIndex == -1) {
		alert('You must select a location before viewing it.');
		return false;
	}
	var oid = document.EditForm.OID.options[document.EditForm.OID.selectedIndex].value;
	showModalDialog("/jims2/control?Server=jims2&Topic=GetProcedureLocation&ReplyTopic=DisplayViewProcedureLocation&OID="+oid,"","dialogHeight: 500px; dialogWidth: 800px; dialogTop: 150px; dialogLeft: 150px; edge: Raised; center: no; help: No; resizable: Yes; status: No;");
}