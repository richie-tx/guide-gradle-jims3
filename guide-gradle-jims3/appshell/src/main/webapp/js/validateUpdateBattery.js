function validate() {
  var errorMsg = null;
  var errorField = null;
  var frm = document.forms[0];

  trim(frm.Code);
  trim(frm.LongName);
  trim(frm.ShortName);

  if (frm.Code.value == "") {
    errorField = frm.Code;
    errorMsg = "You must enter a battery code.";
  } else
    if (frm.Code.value.length > 9 ||frm.Code.value.length < 1) {
    errorField = frm.Code;
    errorMsg = "The procedure code must contain 1 to 9 characters.";
  } else 
  if (frm.Code.value.indexOf('"') > -1) {
    errorField = frm.Code;
    errorMsg = 'The battery code cannot contain a double quote character.';
  } else
  if (frm.LongName.value == "") {
    errorField = frm.LongName;
    errorMsg = "You must enter a battery long name.";
  } else
  if (frm.LongName.value.indexOf('"') > -1) {
    errorField = frm.LongName;
    errorMsg = 'The battery long name cannot contain a double quote character.';
  } else
  if (frm.ShortName.value == "") {
    errorField = frm.ShortName;
    errorMsg = "You must enter a battery short name.";
  } else 
  if (frm.ShortName.value.indexOf('"') > -1) {
    errorField = frm.ShortName;
    errorMsg = 'The battery short name cannot contain a double quote character.';
  } else
  if (frm.NumberOfLabels.value != "" && frm.NumberOfLabels.value.match(/\D/)) {
    errorField = frm.NumberOfLabels;
    errorMsg = "The number of labels field must be numeric.";
  } else
  if (frm.NumberOfLabels.value != "" && (frm.NumberOfLabels.value > 9 || frm.NumberOfLabels.value < 1)) {
    errorField = frm.NumberOfLabels;
    errorMsg = "The number of labels field must be between 1 and 9.";
  } else
  if (frm.Type == -1) {
    errorField = frm.Type;
    errorMsg = "You must select a battery use.";
  } else 
  if (frm.BusinessUnit == -1) {
    errorField = frm.BusinessUnit;
    errorMsg = "You must select a business unit.";
  } else 
  if (frm.ClinicalUnitPrice.value != "" && !frm.ClinicalUnitPrice.value.match(/^(\d{1,6}|\d{1,3},\d{3})\.\d{2}$/)) {
    errorField = frm.ClinicalUnitPrice;
    errorMsg = "The clinical list price must be in this format: ###,###.##";
  } else
  if (frm.ClinicalTrialsListCost.value != "" && !frm.ClinicalTrialsListCost.value.match(/^(\d{1,6}|\d{1,3},\d{3})\.\d{2}$/)) {
    errorField = frm.ClinicalTrialsListCost;
    errorMsg = "The clinical trials list price must be in this format: ###,###.##";
  } else
  if (frm.ClinicalUnitCost.value != "" && !frm.ClinicalUnitCost.value.match(/^(\d{1,6}|\d{1,3},\d{3})\.\d{2}$/)) {
    errorField = frm.ClinicalUnitCost;
    errorMsg = "The clinical unit cost must be in this format: ###,###.##";
  } else
  if (frm.ClinicalTrialsUnitCost.value != "" && !frm.ClinicalTrialsUnitCost.value.match(/^(\d{1,6}|\d{1,3},\d{3})\.\d{2}$/)) {
    errorField = frm.ClinicalTrialsUnitCost;
    errorMsg = "The clinical trials unit cost must be in this format: ###,###.##";
  } else
  if (frm.ClinicalTrialsBatchPrice.value != "" && !frm.ClinicalTrialsBatchPrice.value.match(/^(\d{1,6}|\d{1,3},\d{3})\.\d{2}$/)) {
    errorField = frm.ClinicalTrialsBatchPrice;
    errorMsg = "The clinical trials batch price must be in this format: ###,###.##";
  } else
  if (frm.ClinicalTrialsBatchCost.value != "" && !frm.ClinicalTrialsBatchCost.value.match(/^(\d{1,6}|\d{1,3},\d{3})\.\d{2}$/)) {
    errorField = frm.ClinicalTrialsBatchCost;
    errorMsg = "The clinical trials batch cost must be in this format: ###,###.##";
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
