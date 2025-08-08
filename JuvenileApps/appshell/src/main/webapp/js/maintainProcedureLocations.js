function selectSendOutOrg() {
  var sendOutData = showModalDialog("/jims2/control/?Server=jims2&Topic=DisplaySendOutSearchPopUp","","dialogHeight: 500px; dialogWidth: 800px; dialogTop: 150px; dialogLeft: 150px; edge: Raised; center: no; help: No; resizable: Yes; status: No;");
  if (sendOutData) {
    var index = sendOutData.indexOf("|");
    var OID = sendOutData.substring(0,index);
    var name = sendOutData.substring(index+1);
    document.forms[0].SendOutEntityOID.value = OID;
    document.forms[0].SendOutEntityName.value = name;
  }
}

function createSendOutOrg() {
  var sendOutData = showModalDialog("/jims2/control/?Server=jims2&Topic=DisplaySendOutCreatePopUp","","dialogHeight: 500px; dialogWidth: 800px; dialogTop: 150px; dialogLeft: 150px; edge: Raised; center: no; help: No; resizable: Yes; status: No;");
  if (sendOutData) {
    var index = sendOutData.indexOf("|");
    var OID = sendOutData.substring(0,index);
    var name = sendOutData.substring(index+1);
    document.forms[0].SendOutEntityOID.value = OID;
    document.forms[0].SendOutEntityName.value = name;
  }
}

function deleteSendOutOrg() {
  document.forms[0].SendOutEntityOID.value = "";
  document.forms[0].SendOutEntityName.value = "";
}

function openInstrumentWindow() {
  var selections = showModalDialog("/jims2/control/?Server=jims2&Topic=GetKey&keyNames=Instrument&ReplyTopic=DisplayInstrumentSearch", document.forms[0].Instruments.options);
  if (selections) {
    var list = document.forms[0].Instruments;
    list.options.length = 0;
    for (var i=0; i < selections.length; i++) {
      var selection = selections[i];
      list.options[i] = new Option(selection, selection);
    }
  }
}



function openCalendar() {
  var selectedDate = showModalDialog("/jims2/html/calendarPopUp.html","","dialogHeight: 320px; dialogWidth: 225px; resizable: No; status: No;");
  if (selectedDate != null && selectedDate != "") {
    document.forms[0].ReactivationDate.value = selectedDate;
  }
}

function validate() {
  var errorMsg = null;
  var errorField = null;
  var frm = document.forms[0];
 
  // select all the instruments so that the form values get sent
  var instruments = frm.Instruments;
  for (var i=0; i < instruments.options.length; i++) {
    instruments.options[i].selected = true;
  }

if( frm.ProcedureClassName.value == "Test" 
    || frm.ProcedureClassName.value == "Battery" 
    || frm.ProcedureClassName.value == "CalculatedTest" 
    || frm.ProcedureClassName.value == "Panel") {
  if (frm.ClinicalTrialsMinBatchSize.value.match(/\D/)) {
    errorField = frm.ClinicalTrialsMinBatchSize;
    errorMsg = "The clinical trials minimum batch size must be a number.";
  } else
  if (frm.LisCode.value == "" && frm.ProcedureClassName.value == "Test") {
    errorField = frm.LisCode;
    errorMsg = "You must enter an LIS code.";
  } else
  if (frm.Comments.value.length > 100) {
    errorField = frm.Comments;
    errorMsg = "The comments can not be more than 100 characters.";
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
  if (frm.Comments.value.length > 250) {
    errorField = frm.Comments;
    errorMsg = "The comment cannot be more than 250 characters.";
  } else
  if (frm.Reason && frm.Reason.value.length > 50) {
    errorField = frm.Reason;
    errorMsg = "The reason cannot be more than 50 characters.";
  } else
  if (frm.Reason && frm.Reason.value == "") {
    errorField = frm.Reason;
    errorMsg = "You must enter a reason for editing this record before saving.";
  }

} else
if(frm.ProcedureClassName.value == "Profile" ){
  if (frm.LisCode.value == "" && frm.ProcedureClassName.value == "Test") {
    errorField = frm.LisCode;
    errorMsg = "You must enter an LIS code.";
  } else
  if (frm.Comments.value.length > 100) {
    errorField = frm.Comments;
    errorMsg = "The comments can not be more than 100 characters.";
  } else
  if (frm.Reason && frm.Reason.value == "") {
    errorField = frm.Reason;
    errorMsg = "You must enter a reason for editing this record before saving.";
  }
}//end if

if (errorMsg != null && errorField != null) {
    alert(errorMsg);
    errorField.focus();
    return false;
  } else {
    disableSubmitButton();
    return true;
}

}


