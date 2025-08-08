function validate() {
  var errorMsg = null;
  var errorField = null;
  var frm = document.forms[0];

  trim(frm.FirstName);
  trim(frm.PrimaryName);
  trim(frm.ClientNumber);
  trim(frm.Reason);
  trim(frm.EmailAddress);

  if (frm.FirstName.value == "") {
    errorField = frm.FirstName;
    errorMsg = "You must enter a first name.";
  } else
  if (frm.FirstName.value.indexOf('"') > -1) {
    errorField = frm.FirstName;
    errorMsg = "A contact's first name cannot contain a double quote character.";
  } else
  if (frm.PrimaryName.value == "") {
    errorField = frm.PrimaryName;
    errorMsg = "You must enter a last name.";
  } else
  if (frm.ClientNumber.value.substring(0,1) == "0") {
    errorField = frm.ClientNumber;
    errorMsg = "Leading zeros are not permitted in the core billing number.";
  } else 
  if (frm.ClientNumber.value != "" && frm.ClientNumber.value.match(/\D/)) {
    errorField = frm.ClientNumber;
    errorMsg = "The core billing number must be numeric.";  
  } else
  if ((frm.MailingCountry.selectedIndex == -1)) {
    errorField = frm.MailingCountry;
    errorMsg = "You must select a mailing address country.";
  } else
  if (frm.MailingCountry.options[frm.MailingCountry.selectedIndex].text == "None") {
    errorField = frm.MailingCountry;
    errorMsg = "You must select a Country for Mailing Address";
  } else
  if (frm.MailingCity.value == "") {
    errorField = frm.MailingCity;
    errorMsg = "You must enter a mailing address city.";
  } else    
  /* if ((frm.PrimaryCountryCode.selectedIndex == -1)) {
    errorField = frm.PrimaryCountryCode;
    errorMsg = "You must select a country code for primary phone.";
  } else
  if (frm.PrimaryCountryCode.options[frm.PrimaryCountryCode.selectedIndex].text == "None") {
    errorField = frm.PrimaryCountryCode;
    errorMsg = "You must select a country code for primary phone.";
  } else
  if (frm.PrimaryCountryCode.value == "") {
    errorField = frm.PrimaryCountryCode;
    errorMsg = "You must select a country code for primary phone.";
  } else  
  if (frm.PrimaryNumber.value == "") {
    errorField = frm.PrimaryNumber;
    errorMsg = "You must enter a primary phone number.";
  } else */
  if (frm.PrimaryName.value.indexOf('"') > -1) {
    errorField = frm.PrimaryName;
    errorMsg = "A contact's last name cannot contain a double quote character.";
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
	if (errorMsg != "Must enter a country code")
      errorMsg = "The primary phone number is in an invalid format.\nPlease enter the number in this format: "+errorMsg;
  } else
  if ((errorMsg = validatePhoneNumber(frm.SecondaryCountryCode, frm.SecondaryNumber)) != null) {
    errorField = frm.SecondaryNumber;
    if (errorMsg != "Must enter a country code")
      errorMsg = "The secondary phone number is in an invalid format.\nPlease enter the number in this format: "+errorMsg;
  } else
  if ((errorMsg = validatePhoneNumber(frm.PrimaryFaxCountryCode, frm.PrimaryFaxNumber)) != null) {
    errorField = frm.PrimaryFaxNumber;
    if (errorMsg != "Must enter a country code")
      errorMsg = "The primary fax number is in an invalid format.\nPlease enter the number in this format: "+errorMsg;
  } else
  if ((errorMsg = validatePhoneNumber(frm.SecondaryFaxCountryCode, frm.SecondaryFaxNumber)) != null) {
    errorField = frm.SecondaryFaxNumber;
    if (errorMsg != "Must enter a country code")
      errorMsg = "The secondary fax number is in an invalid format.\nPlease enter the number in this format: "+errorMsg;
  } else
  if ((errorMsg = validatePhoneNumber(frm.MobileCountryCode, frm.MobileNumber)) != null) {
    errorField = frm.MobileNumber;
    if (errorMsg != "Must enter a country code")
      errorMsg = "The mobile phone number is in an invalid format.\nPlease enter the number in this format: "+errorMsg;
  } else
  if ((errorMsg = validatePhoneNumber(frm.SaturdayCountryCode, frm.SaturdayNumber)) != null) {
    errorField = frm.SaturdayNumber;
    if (errorMsg != "Must enter a country code")
      errorMsg = "The Saturday phone number is in an invalid format.\nPlease enter the number in this format: "+errorMsg;
  } else
  if ((errorMsg = validatePhoneNumber(frm.PagerCountryCode, frm.PagerNumber)) != null) {
    errorField = frm.PagerNumber;
    if (errorMsg != "Must enter a country code")
      errorMsg = "The pager number is in an invalid format.\nPlease enter the number in this format: "+errorMsg;
  } else
  if (frm.EmailAddress.value != "" && frm.EmailAddress.value.search(/^[a-zA-Z0-9_._-]+@[a-zA-Z0-9_._-]+\.[a-zA-Z0-9_.]+$/) == -1) {
    errorField = frm.EmailAddress;
    errorMsg = "The primary email address is not in a valid format.";
  } else
  if (frm.EmailAddress2.value != "" && frm.EmailAddress2.value.search(/^[a-zA-Z0-9_._-]+@[a-zA-Z0-9_._-]+\.[a-zA-Z0-9_.]+$/) == -1) {
    errorField = frm.EmailAddress2;
    errorMsg = "The secondary email address is not in a valid format.";
  } else
  if (frm.Status.selectedIndex == -1) {
    errorField = frm.Status;
    errorMsg = "You must enter a mailing address country.";
  } else
  if (frm.MailingStreet.value.length > 100) {
    errorField = frm.MailingStreet;
    errorMsg = "The mailing street cannot be more than 100 characters.";
  } else
  if ((errorMsg = validateMolisAddress(frm.MailingStreet)) != null ) {
    errorField = frm.MailingStreet;
  } else
  if (frm.ShippingStreet.value.length > 100) {
    errorField = frm.ShippingStreet;
    errorMsg = "The shipping street cannot be more than 100 characters.";
  } else
  if (frm.Comments.value.length > 250) {
    errorField = frm.Comments;
    errorMsg = "The comment cannot be more than 250 characters.";
  } else
  if (frm.Reason && frm.Reason.value == "") {
    errorField = frm.Reason;
    errorMsg = "You must enter a reason for editing this record before saving.";
  } else 
  if (frm.Reason && frm.Reason.value.length > 50) {
     errorField = frm.Reason;
     errorMsg = "Your edit reason must not exceed 50 characters.";
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

function validateMolisAddress(address) {
  var aLine = "";
  var remainder = "";
  var newlineIdx;
  
  if (address.value == "") return null;
  
  remainder = address.value;
  
  newlineIdx = remainder.indexOf("\r\n");
  if (newlineIdx < 0) {
    if (remainder.length > 35) return "The first line of the mailing street cannot be more than 35 characters.";
  } else {
    aLine = remainder.substring(0, newlineIdx);
    if (aLine.length > 35) return "The first line of the mailing street cannot be more than 35 characters.";
    remainder = remainder.substring(newlineIdx+2, address.value.length);
    newlineIdx = remainder.indexOf("\r\n");
    if (newlineIdx < 0) {
      if (remainder.length > 35) return "The second line of the mailing street cannot be more than 35 characters.";
      return null;
    }
    aLine = remainder.substring(0, newlineIdx);
    if (aLine.length > 35) return "The second line of the mailing street cannot be more than 35 characters.";
    remainder = remainder.substring(newlineIdx+2, remainder.length);
    if (remainder.length > 35) return "The text after the second line of the mailing street cannot be more than 35 characters.";
  }
}
