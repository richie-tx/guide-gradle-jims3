function validate() {
  var frm = document.forms[0];
  var errorMsg = null;
  
  if (frm.Code.value == "" 
      && frm.LongName.value == "" 
      && frm.Category.selectedIndex == "0" 
      && frm.BusinessUnit.selectedIndex == "0" 
      && frm.Type.selectedIndex == "0"
      && frm.ClassName.selectedIndex == "0"){
	  errorMsg="Not setting any search criteria could result in exceptionally large search results.\n" 
			+ "Are you sure you want to continue?"
      return confirm(errorMsg);
  } else
      return true;
}