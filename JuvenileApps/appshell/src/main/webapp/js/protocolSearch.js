function validate() {
  var frm = document.forms[0];
  var errorMsg = null;

  if (frm.jims2StudyNumber.value == ""
      && frm.SponsorName.value == "" 
	  && frm.ProtocolID.value == "" 
      && frm.Status.selectedIndex == "0") {
	  errorMsg="Not setting any search criteria could result in exceptionally large search results.\n" 
			+ "Are you sure you want to continue?"
      return confirm(errorMsg);
  } else
      return true;
}