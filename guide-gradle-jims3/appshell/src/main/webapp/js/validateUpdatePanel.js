function validate() {
  var errorMsg = null;
  var errorField = null;
  var frm = document.forms[0];

  trim(frm.Code);
  trim(frm.LongName);
  trim(frm.ShortName);

	

  if (frm.Code.value == "") {
    errorField = frm.Code;
    errorMsg = "You must enter a panel code.";
  } else
  if (frm.Code.value.length > 9 ||frm.Code.value.length < 1) {
    errorField = frm.Code;
    errorMsg = "The procedure code must contain 1 to 9 characters.";
  } else 
  if (frm.Code.value.indexOf('"') > -1) {
    errorField = frm.Code;
    errorMsg = 'The panel code cannot contain a double quote character.';
  } else
  if (frm.LongName.value == "") {
    errorField = frm.LongName;
    errorMsg = "You must enter a panel long name.";
  } else
  if (frm.LongName.value.indexOf('"') > -1) {
    errorField = frm.LongName;
    errorMsg = 'The panel long name cannot contain a double quote character.';
  } else
  if (frm.ShortName.value == "") {
    errorField = frm.ShortName;
    errorMsg = "You must enter a panel short name.";
  } else
  if (frm.ShortName.value.indexOf('"') > -1) {
    errorField = frm.ShortName;
    errorMsg = 'The panel short name cannot contain a double quote character.';
  } else
  if (frm.NumberOfLabels.value != "" && frm.NumberOfLabels.value.match(/\D/)) {
    errorField = frm.NumberOfLabels;
    errorMsg = "The number of labels field must be numeric.";
  } else
  if (frm.NumberOfLabels.value != "" && (frm.NumberOfLabels.value > 9 || frm.NumberOfLabels.value < 1)) {
    errorField = frm.NumberOfLabels;
    errorMsg = "The number of labels field must be between 1 and 9.";
  } else
  if (frm.Type.selectedIndex == -1) {
    errorField = frm.Type;
    errorMsg = "You must select a use.";
  } else 
  if (frm.BusinessUnit.selectedIndex == -1) {
    errorField = frm.BusinessUnit;
    errorMsg = "You must select a business unit.";
  } else
  if (frm.ClinicalUtilityInterpretation.value.length > 256) {
    errorField = frm.ClinicalUtilityInterpretation;
    errorMsg = "The clinical interpretation cannot exceed 256 characters.";
  } else
  if (frm.TechnicalReferences.value.length > 256) {
    errorField = frm.TechnicalReferences;
    errorMsg = "The technical references cannot exceed 256 characters.";
  } else 
  if (frm.Comments.value.length > 100) {
    errorField = frm.Comments;
    errorMsg = "The comments cannot exceed 100 characters.";
  } else
  if (frm.Reason && frm.Reason.value == "") {
    errorField = frm.Reason;
    errorMsg = "You must enter a reason for editing this record.";
  } else
  if (frm.Reason && frm.Reason.value.length > 50) {
    errorField = frm.Reason;
    errorMsg = "The reason cannot exceed 50 characters.";
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
