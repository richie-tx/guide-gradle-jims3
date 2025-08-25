function validate() {

  
  var errorMsg = null;
  var errorField = null;
  var frm = document.forms[0];
  var intRegexp = new RegExp("^\\d*$");
  var floatRegexp = new RegExp("^-?\\d*\\.?\\d*$");

  trim(frm.Reason);

  if (frm.Origin.value == "") {
    errorField = frm.Origin;
    errorMsg = "You must enter an origin.";
  } else 
  if (frm.Origin.length > 50) {
    errorField = frm.Origin;
    errorMsg = "The origin cannot exceed 50 characters.";
  } else 
  if (frm.Sex.value == "") {
    errorField = frm.Sex;
    errorMsg = "You must enter a Gender.";
  } else 
  if (frm.ageLowYears.value != "" && !intRegexp.test(frm.ageLowYears.value)) {
    errorField = frm.ageLowYears;
    errorMsg = "The low age range years must be a positive integer value.";
  } else
  if (frm.ageLowMonths.value != "" && !intRegexp.test(frm.ageLowMonths.value)) {
    errorField = frm.ageLowMonths;
    errorMsg = "The low age range months must be a positive integer value.";
  } else
  if (frm.ageLowDays.value != "" && !intRegexp.test(frm.ageLowDays.value)) {
    errorField = frm.ageLowDays;
    errorMsg = "The low age range days must be a positive integer value.";
  } else
  if (frm.ageHighYears.value != "" && !intRegexp.test(frm.ageHighYears.value)) {
    errorField = frm.ageHighYears;
    errorMsg = "The high age range years must be a positive integer value.";
  } else
  if (frm.ageHighMonths.value != "" && !intRegexp.test(frm.ageHighMonths.value)) {
    errorField = frm.ageHighMonths;
    errorMsg = "The high age range months must be a positive integer value.";
  } else
  if (frm.ageHighDays.value != "" && !intRegexp.test(frm.ageHighDays.value)) {
    errorField = frm.ageHighDays;
    errorMsg = "The high age range days must be a positive integer value.";
  } else
  if (frm.ageLowYears.value != "" && frm.ageLowYears.value > 99999) {
    errorField = frm.ageLowYears;
    errorMsg = "Age low years must be less than 99999";
  } else
  if (frm.ageLowMonths.value != "" && frm.ageLowMonths.value > 99999) {
    errorField = frm.ageLowMonths;
    errorMsg = "Age low months must be less than 99999";
  } else
  if (frm.ageLowDays.value != "" && frm.ageLowDays.value > 99999) {
    errorField = frm.ageLowDays;
    errorMsg = "Age low days must be less than 99999";
  } else
  if (frm.ageHighYears.value != "" && frm.ageHighYears.value > 99999) {
    errorField = frm.ageHighYears;
    errorMsg = "Age high years must be less than 99999";
  } else
  if (frm.ageHighMonths.value != "" && frm.ageHighMonths.value > 99999) {
    errorField = frm.ageHighMonths;
    errorMsg = "Age high months must be less than 99999";
  } else
  if (frm.ageHighDays.value != "" && frm.ageHighDays.value > 99999) {
    errorField = frm.ageHighDays;
    errorMsg = "Age high days must be less than 99999";
  } else
  if (ageHighExists() && 
	(frm.ageLowYears.value == "" && frm.ageLowMonths.value == "" && frm.ageLowDays.value == "")) {
    errorField = frm.ageLowDays;
    errorMsg = "If a high age is entered, a low age must be entered as well.";
  } else
  if (ageLowExists() && 
	(frm.ageHighYears.value == "" && frm.ageHighMonths.value == "" && frm.ageHighDays.value == "")) {
    errorField = frm.ageHighDays;
    errorMsg = "If a low age is entered, a high age must be entered as well.";
  } else
  if (ageHighExists() && ageLowExists() && ageRangeInvalid()) {
    errorField = frm.ageLow;
    errorMsg = "The high age must be greater than the low age";
  } else 
  if (panicsExist() && !normalsExist()) {
    errorField = frm.ConvNormalsLow;
    errorMsg = "If Panics are entered, you must also enter values for Normals.";
  } else 
  if (panicsExist() && !pendingsExist()) {
    errorField = frm.ConvPendingValuesLow;
    errorMsg = "If Panics are entered, you must also enter values for Pending Values.";
  } else
  if (panicsExist() && !alertsExist()) {
    errorField = frm.ConvAlertsLow;
    errorMsg = "If Panics are entered, you must also enter values for Alerts.";
  } else
  if (alertsExist() && !normalsExist()) {
    errorField = frm.ConvNormalsLow;
    errorMsg = "If Alerts are entered, you must also enter values for Normals.";
  } else 
  if (alertsExist() && !pendingsExist()) {
    errorField = frm.ConvPendingValuesLow;
    errorMsg = "If Alerts are entered, you must also enter values for Pending Values.";
  } else 
  if (!normalsExist() && !validsExist() && !pendingsExist()) {
    errorField = frm.ConvNormalsLow;
    errorMsg = "You must enter values for either Normals, Valids or Pending Values.";
  } else
  if (fieldExists("NormalsLow") && !floatRegexp.test(frm.ConvNormalsLow.value)) {
    errorField = frm.ConvNormalsLow;
    errorMsg = "Low value must be numeric.";
  } else
  if (fieldExists("NormalsHigh") && !floatRegexp.test(frm.ConvNormalsHigh.value)) {
    errorField  = frm.ConvNormalsHigh;
    errorMsg = "High value must be numeric.";
  } else 
  if (fieldExists("ValidsLow") && !floatRegexp.test(frm.ConvValidsLow.value)) {
    errorField  = frm.ConvValidsLow;
    errorMsg = "Low value must be numeric.";
  } else 
  if (fieldExists("ValidsHigh") && !floatRegexp.test(frm.ConvValidsHigh.value)) {
    errorField  = frm.ConvValidsHigh;
    errorMsg = "High value must be numeric.";
  } else 
  if (fieldExists("AlertsLow") && !floatRegexp.test(frm.ConvAlertsLow.value)) {
    errorField  = frm.ConvAlertsLow;
    errorMsg = "Low value must be numeric.";
  } else 
  if (fieldExists("AlertsHigh") && !floatRegexp.test(frm.ConvAlertsHigh.value)) {
    errorField  = frm.ConvAlertsHigh;
    errorMsg = "High value must be numeric.";
  } else 
  if (fieldExists("PanicsLow") && !floatRegexp.test(frm.ConvPanicsLow.value)) {
    errorField  = frm.ConvPanicsLow;
    errorMsg = "Low value must be numeric.";
  } else 
  if (fieldExists("PanicsHigh") && !floatRegexp.test(frm.ConvPanicsHigh.value)) {
    errorField  = frm.ConvPanicsHigh;
    errorMsg = "High value must be numeric.";
  } else 
  if (fieldExists("PendingValuesLow") && !floatRegexp.test(frm.ConvPendingValuesLow.value)) {
    errorField  = frm.ConvPendingValuesLow;
    errorMsg = "Low value must be numeric.";
  } else 
  if (fieldExists("PendingValuesHigh") && !floatRegexp.test(frm.ConvPendingValuesHigh.value)) {
    errorField  = frm.ConvPendingValuesHigh;
    errorMsg = "High value must be numeric.";
  } else 
  if (fieldExists("NormalsHigh") && fieldExists("NormalsLow") 
      && parseFloat(frm.ConvNormalsLow.value) >= parseFloat(frm.ConvNormalsHigh.value)) {
    errorField = frm.ConvNormalsLow;
    errorMsg = "Low value must be below high value for normals";
  } else
  if (fieldExists("ValidsHigh") && fieldExists("ValidsLow") 
      && parseFloat(frm.ConvValidsLow.value) >= parseFloat(frm.ConvValidsHigh.value)) {
    errorField = frm.ConvValidsLow;
    errorMsg = "Low value must be below high value for valids";
  } else
  if (fieldExists("AlertsHigh") && fieldExists("AlertsLow") 
      && parseFloat(frm.ConvAlertsLow.value) >= parseFloat(frm.ConvAlertsHigh.value)) {
    errorField = frm.ConvAlertsLow;
    errorMsg = "Low value must be below high value for alerts";
  } else
  if (fieldExists("PanicsHigh") && fieldExists("PanicsLow") 
      && parseFloat(frm.ConvPanicsLow.value) >= parseFloat(frm.ConvPanicsHigh.value)) {
    errorField = frm.ConvPanicsLow;
    errorMsg = "Low value must be below high value for panics";
  } else
  if (fieldExists("PendingValuesHigh") && fieldExists("PendingValuesLow") 
      && parseFloat(frm.ConvPendingValuesLow.value) >= parseFloat(frm.ConvPendingValuesHigh.value)) {
    errorField = frm.ConvPendingValuesLow;
    errorMsg = "Low value must be below high value for pending values";
  } else
  if (fieldExists("AlertsHigh") && fieldExists("NormalsHigh")
      && parseFloat(frm.ConvNormalsHigh.value) >= parseFloat(frm.ConvAlertsHigh.value)) {
    errorField = frm.ConvNormalsHigh;
    errorMsg = "Normals high must be less than alerts high.";
  } else 
  if (fieldExists("AlertsLow") && fieldExists("NormalsLow")
      && parseFloat(frm.ConvNormalsLow.value) <= parseFloat(frm.ConvAlertsLow.value)) {
    errorField = frm.ConvNormalsLow;
    errorMsg = "Normals low must be greater than the alerts low.";
  } else 
  if (fieldExists("AlertsHigh") && fieldExists("PanicsHigh")
      && parseFloat(frm.ConvAlertsHigh.value) > parseFloat(frm.ConvPanicsHigh.value)) {
    errorField = frm.ConvAlertsHigh;
    errorMsg = "Alerts high must be less than or equal to panics high.";
  } else
  if (fieldExists("AlertsLow") && fieldExists("PanicsLow")
      && parseFloat(frm.ConvAlertsLow.value) < parseFloat(frm.ConvPanicsLow.value)) {
    errorField = frm.ConvAlertsLow;
    errorMsg = "Alerts low must be greater than or equal to panics low.";
  } else
  if (fieldExists("PanicsHigh") && fieldExists("ValidsHigh")
      && parseFloat(frm.ConvPanicsHigh.value) > parseFloat(frm.ConvValidsHigh.value)) {
    errorField = frm.ConvPanicsHigh;
    errorMsg = "Panics high must be less than or equal to valids high.";
  } else
  if (fieldExists("PanicsLow") && fieldExists("ValidsLow") 
      && parseFloat(frm.ConvPanicsLow.value) < parseFloat(frm.ConvValidsLow.value)) {
    errorField = frm.ConvPanicsLow;
    errorMsg = "Panics low must be greater than or equal to valids low.";
  } else
  if (frm.Reason && frm.Reason.value == "") {
    errorField = frm.Reason;
    errorMsg = "You must enter a reason when editing.";
  }else 
  if (frm.Reason && frm.Reason.value.length > 50) {
    errorField = frm.Reason;
    errorMsg = "The reason cannot exceed 50 characters.";
  }

  if (errorMsg != null) {
    alert(errorMsg);
    if (errorField != null)
      errorField.focus();
    return false;
  } else {
    disableSubmitButton();
    return true;
  }
}

function normalsExist() {
  var frm = document.forms[0];
  if (fieldExists("NormalsHigh") || fieldExists("NormalsLow") || fieldExists("NormalsText1"))
    return true;
  return false;
}

function validsExist() {
  var frm = document.forms[0];
  if (fieldExists("ValidsHigh") || fieldExists("ValidsLow") || fieldExists("ValidsText1"))
    return true;
  return false;
}

function panicsExist() {
  var frm = document.forms[0];
  if (fieldExists("PanicsHigh") || fieldExists("PanicsLow") || fieldExists("PanicsText1"))
    return true;
  return false;
}

function alertsExist() {
  var frm = document.forms[0];
  if (fieldExists("AlertsHigh") || fieldExists("AlertsLow") || fieldExists("AlertsText1"))
    return true;
  return false;
}

function pendingsExist() {
  var frm = document.forms[0];
  if (fieldExists("PendingValuesHigh") || fieldExists("PendingValuesLow") || fieldExists("PendingValuesText1"))
    return true;
  return false;
}

function fieldExists(RRValue) {
  var fldName = 'document.forms[0].Conv' + RRValue;
  if (eval(fldName ) && eval(fldName + '.value != ""')) 
    return true;
  return false;
}

function ageLowExists() {
  frm = document.forms[0];
  if (frm.ageLowDays.value != "" || frm.ageLowMonths.value != "" || frm.ageLowYears.value != "")
    return true;
  return false;
}

function ageHighExists() {
  frm = document.forms[0];
  if (frm.ageHighDays.value != "" || frm.ageHighMonths.value != "" || frm.ageHighYears.value != "")
    return true;
  return false;
}

function ageRangeInvalid() {
  var frm = document.forms[0];
  var lowDays = 0;
  if (frm.ageLowDays.value != "") lowDays += parseInt(frm.ageLowDays.value);
  if (frm.ageLowMonths.value != "") lowDays += Math.round(parseInt(frm.ageLowMonths.value)*30.42);
  if (frm.ageLowYears.value != "") lowDays += parseInt(frm.ageLowYears.value)*365;
  var highDays = 0;
  if (frm.ageHighDays.value != "") highDays += parseInt(frm.ageHighDays.value);
  if (frm.ageHighMonths.value != "") highDays += Math.round(parseInt(frm.ageHighMonths.value)*30.42);
  if (frm.ageHighYears.value != "") highDays += parseInt(frm.ageHighYears.value)*365;

  return lowDays >= highDays;
}

function trim(textbox) {
  if (textbox) {
    while (textbox.value.substring(0,1) == " ") {
      textbox.value = textbox.value.substring(1);
    }
  }
}