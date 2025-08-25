function initializePage(countryName) {
  var frm = document.forms[0];
  writeCountries(frm.MailingCountry, countryName);
  frm.MailingCountry.options[0] = new Option("Any","");
  frm.MailingCountry.options[0].selected = true;
  writeTerritories(frm.MailingTerritory, frm.MailingCountry.options[0].text);
}


function validate() {
  var frm = document.forms[0];
  var errorMsg = null;
 var whichVal=checkWhichSearch();
  if(whichVal){
  if (frm.FirstName.value == "" &&
      frm.PrimaryName.value == "" &&
      frm.RLNNumber.value == "" &&
      frm.MailingStreet.value == "" &&
      frm.MailingCity.value == "" &&
      frm.MailingTerritory.value == "" &&
      frm.MailingZipCode.value == "" &&
      frm.MailingCountry.selectedIndex == 0 &&
      (frm.Status.options && frm.Status.options[frm.Status.selectedIndex].value == "")) {
    return confirm("Not setting any search criteria could result in exceptionally large search results.\nAre you sure you want to continue?");
  } else{
    disableSubmitButton();
    return true; }
	
	}
	
	 else if(!whichVal){
  if (frm.FirstName.value == "" &&
      frm.PrimaryName.value == "" &&
      frm.RLNNumber.value == "" &&
      frm.MailingStreet.value == "" &&
      frm.MailingCity.value == "" &&
      frm.MailingTerritory.value == "" &&
      frm.MailingZipCode.value == "" &&
      frm.MailingCountry.selectedIndex == 0) {
    return confirm("Not setting any search criteria could result in exceptionally large search results.\nAre you sure you want to continue?");
  } else{
    disableSubmitButton();
    return true;}
}
	
	
}

function checkWhichSearch(){
 var frm = document.forms[0];
 if(frm.Status.type=="hidden")
 {
   return false;
  } else
    return true;
}