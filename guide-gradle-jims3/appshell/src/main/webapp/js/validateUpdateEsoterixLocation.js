function validate() {
  var errorMsg = null;
  var errorField = null;
  var frm = document.forms[0];

  trim(frm.PrimaryName);
  trim(frm.Reason);

  if (frm.PrimaryName.value == "") {
    errorField = frm.PrimaryName;
    errorMsg = "You must enter a location name.";
  } else
  if (frm.PrimaryName.value.indexOf('"') > -1) {
    errorField = frm.PrimaryName;
    errorMsg = 'A location name cannot contain a double quote character.';
  } else
  if ((frm.MailingCountry.selectedIndex == -1)) {
    errorField = frm.MailingCountry;
    errorMsg = "You must select a mailing address country.";
  } else
  if (frm.MailingCountry.options[frm.MailingCountry.selectedIndex].text == "None") {
    errorField = frm.MailingCountry;
    errorMsg = "You must select a Country for Mailing Address";
  } else
  if ((errorMsg = validateZipCode(frm.MailingCountry, frm.MailingZipCode)) != null) {
    errorField = frm.MailingZipCode;
    errorMsg = "The mailing address zip code is in an invalid format.\nPlease enter the number in this format: "+errorMsg;
  } else
  if (frm.ShippingCountry.options[frm.ShippingCountry.selectedIndex].text != "None" && (errorMsg = validateZipCode(frm.ShippingCountry, frm.ShippingZipCode)) != null) {
      errorField = frm.ShippingZipCode;
      errorMsg = "The shipping address zip code is in an invalid format.\nPlease enter the number in this format: "+errorMsg;
  } else
  if ((errorMsg = validatePhoneNumber(frm.PrimaryCountryCode, frm.PrimaryNumber)) != null) {
    errorField = frm.PrimaryNumber;
    if (!errorMsg == "Must enter a country code")
      errorMsg = "The primary phone number is in an invalid format.\nPlease enter the number in this format: "+errorMsg;
  } else
  if ((errorMsg = validatePhoneNumber(frm.SecondaryCountryCode, frm.SecondaryNumber)) != null) {
    errorField = frm.SecondaryNumber;
    if (!errorMsg == "Must enter a country code")
      errorMsg = "The secondary phone number is in an invalid format.\nPlease enter the number in this format: "+errorMsg;
  } else
  if ((errorMsg = validatePhoneNumber(frm.PrimaryFaxCountryCode, frm.PrimaryFaxNumber)) != null) {
    errorField = frm.PrimaryFaxNumber;
    if (!errorMsg == "Must enter a country code")
      errorMsg = "The primary fax number is in an invalid format.\nPlease enter the number in this format: "+errorMsg;
  } else
  if ((errorMsg = validatePhoneNumber(frm.SecondaryFaxCountryCode, frm.SecondaryFaxNumber)) != null) {
    errorField = frm.SecondaryFaxNumber;
    if (!errorMsg == "Must enter a country code")
      errorMsg = "The secondary fax number is in an invalid format.\nPlease enter the number in this format: "+errorMsg;
  } else
  if (frm.Status.selectedIndex == -1) {
    errorField = frm.Status;
    errorMsg = "You must a mailing address country.";
  } else
	if (frm.MailingStreet.value.length > 100) {
    errorField = frm.MailingStreet;
    errorMsg = "The mailing street cannot be more than 100 characters.";
  } else
	if (frm.ShippingStreet.value.length > 100) {
    errorField = frm.ShippingStreet;
    errorMsg = "The shipping street cannot be more than 100 characters.";
  } else
   if (!checkPunctuation(frm.KpPrintQueue.value)) {
   	errorField = frm.KpPrintQueue;
    errorMsg = 'No punctuation allowed in the kit production print queue except the underscore.';
  } else
  if (!checkPunctuation(frm.OePrintQueue.value)) {
   	errorField = frm.OePrintQueue;
    errorMsg = 'No punctuation allowed in the order entry print queue except the underscore.';
  } else
  if (!checkPunctuation(frm.GenericPrintQueue.value)) {
   	errorField = frm.GenericPrintQueue;
    errorMsg = 'No punctuation allowed in the generic print queue except the underscore.';
  } else
  if (frm.Comments.value.length > 250) {
    errorField = frm.Comments;
    errorMsg = "The comment cannot be more than 250 characters.";
  } else
  if (frm.Reason && frm.Reason.value == "") {
    errorField = frm.Reason;
    errorMsg = "You must enter a reason for editing this record before saving.";
  } else {
    var businessUnitSelected = false;
    if (frm.BusinessUnits.value) {
      if (frm.BusinessUnits.checked) businessUnitSelected = true;
    } else {
      for (var i=0; i < frm.BusinessUnits.length && !businessUnitSelected; i++) {
        if (frm.BusinessUnits[i].checked) businessUnitSelected = true;
      }
    }
    if (!businessUnitSelected) {
      if (frm.BusinessUnits.value)
        errorField = frm.BusinessUnits;
      else
        errorField = frm.BusinessUnits[0];
      errorMsg = "You must select at least one business unit.";
    }
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

function checkPunctuation(testValue){
var validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_"
var theValue= new String(testValue)
//alert('hi')
  var i
    for (i=0; i<theValue.length; i++){
      if (validChars.indexOf(theValue.charAt(i)) == -1) {
	  return false}
	  }
return true
}

function trim(textbox) {
  if (textbox) {
    while (textbox.value.substring(0,1) == " ") {
      textbox.value = textbox.value.substring(1);
    }
  }
}
