function validate() {
  var frm = document.forms[0];
  if (frm.UserID.value == "" && 
      frm.PrimaryName.value == "" && 
      frm.jims2LocationName.options[frm.jims2LocationName.selectedIndex].value == "" && 
      (frm.Status.value == "Active" || (frm.Status.options && frm.Status.options[frm.Status.selectedIndex].value == ""))) {
    return confirm("Not setting any search criteria could result in exceptionally large search results.\nAre you sure you want to continue?");
  } else{
    disableSubmitButton();
    return true;}
}
