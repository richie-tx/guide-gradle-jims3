function validate() {
  var errorMsg = null;
  var errorField = null;
  var frm = document.forms[0];

  trim(frm.Code);
  trim(frm.LongName);
  trim(frm.ShortName);

  for (var i=0; i<frm.Methodology.options.length; i++) {
    frm.Methodology.options[i].selected = true;
  }

  if (frm.Code.value == "") {
    errorField = frm.Code;
    errorMsg = "You must enter a test code.";
  } else
  if (frm.Code.value.length > 9 ||frm.Code.value.length < 1) {
    errorField = frm.Code;
    errorMsg = "The procedure code must contain 1 to 9 characters.";
  } else 
  if (frm.Code.value.indexOf('"') > -1) {
    errorField = frm.Code;
    errorMsg = 'The test code cannot contain a double quote character.';
  } else
  if (frm.LongName.value == "") {
    errorField = frm.LongName;
    errorMsg = "You must enter a test long name.";
  } else
  if (frm.LongName.value.indexOf('"') > -1) {
    errorField = frm.LongName;
    errorMsg = 'The test long name cannot contain a double quote character.';
  } else
  if (frm.ShortName.value == "") {
    errorField = frm.ShortName;
    errorMsg = "You must enter a test short name.";
  } else
  if (frm.ShortName.value.indexOf('"') > -1) {
    errorField = frm.ShortName;
    errorMsg = 'The test short name cannot contain a double quote character.';
  } else
  if (frm.CptCode.value.length > 250) {
    errorField = frm.CptCode;
    errorMsg = "The CPT Codes cannot exceed 250 characters.";
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
    errorMsg = "You must select a test use.";
  } else 
  if (frm.BusinessUnit == -1) {
    errorField = frm.BusinessUnit;
    errorMsg = "You must select a business unit.";
  } else 
  if (frm.Frozen120StabilityUnits.value == "") {
    errorField = frm.Frozen120StabilityUnits;
    errorMsg = "You must enter the stability units for frozen-120.";
  } else 
  if (!(/^\d{1,3}$/).test(frm.Frozen120StabilityUnits.value)) {
    errorField = frm.Frozen120StabilityUnits;
    errorMsg = "The frozen-120 stability units must be between 1 and 3 digits.";
  } else 
  if (frm.Frozen70StabilityUnits.value == "") {
    errorField = frm.Frozen70StabilityUnits;
    errorMsg = "You must enter the stability units for frozen-70.";
  } else 
  if (!(/^\d{1,3}$/).test(frm.Frozen70StabilityUnits.value)) {
    errorField = frm.Frozen70StabilityUnits;
    errorMsg = "The frozen-70 stability units must be between 1 and 3 digits.";
  } else 
  if (frm.Frozen20StabilityUnits.value == "") {
    errorField = frm.Frozen20StabilityUnits;
    errorMsg = "You must enter the stability units for frozen-20.";
  } else 
  if (!(/^\d{1,3}$/).test(frm.Frozen20StabilityUnits.value)) {
    errorField = frm.Frozen20StabilityUnits;
    errorMsg = "The frozen-20 stability units must be between 1 and 3 digits.";
  } else 
  if (frm.RefrigeratedStabilityUnits.value == "") {
    errorField = frm.RefrigeratedStabilityUnits;
    errorMsg = "You must enter the refrigerated stability units.";
  } else 
  if (!(/^\d{1,3}$/).test(frm.RefrigeratedStabilityUnits.value)) {
    errorField = frm.RefrigeratedStabilityUnits;
    errorMsg = "The refrigerated stability units must be between 1 and 3 digits.";
  } else 
  if (frm.AmbientStabilityUnits.value == "") {
    errorField = frm.AmbientStabilityUnits;
    errorMsg = "You must enter the ambient stability units.";
  } else 
  if (!(/^\d{1,3}$/).test(frm.AmbientStabilityUnits.value)) {
    errorField = frm.AmbientStabilityUnits;
    errorMsg = "The ambient stability units must be between 1 and 3 digits.";
  } else 
  if (frm.ResultType == -1) {
    errorField = frm.ResultType;
    errorMsg = "You must select a standard result type.";
  } else
  if (frm.ConvDecimalPlaces.value == "" && frm.ConvUnitOfMeasure.value != "") {
    errorField = frm.ConvDecimalPlaces;
    errorMsg = "The conventional decimal places can only be null if no units of measure are given.";
  } else
  if (frm.ConvDecimalPlaces.value != "" && frm.ConvUnitOfMeasure.value == "") {
    errorField = frm.ConvDecimalPlaces;
    errorMsg = "The conventional units of measure can only be none if no decimal places are given.";
  } else
  if (frm.ConvDecimalPlaces.value != "" && frm.ConvDecimalPlaces.value.match(/\D/)) {
    errorField = frm.ConvDecimalPlaces;
    errorMsg = "The conventional decimal places field must be numeric.";
  } else
  if (frm.ConvDecimalPlaces.value != "" && (frm.ConvDecimalPlaces.value > 4 || frm.ConvDecimalPlaces.value < 0)) {
    errorField = frm.ConvDecimalPlaces;
    errorMsg = "The conventional decimal places field must be between 0 and 4.";
  } else
  if (frm.SiDecimalPlaces.value == "" && frm.SiUnitOfMeasure.value != "") {
    errorField = frm.SiDecimalPlaces;
    errorMsg = "The SI decimal places can only be null if no units of measure are given.";
  } else
  if (frm.SiDecimalPlaces.value != "" && frm.SiUnitOfMeasure.value == "") {
    errorField = frm.SiDecimalPlaces;
    errorMsg = "The SI units of measure can only be none if no decimal places are given.";
  } else
  if (frm.SiDecimalPlaces.value != "" && frm.SiDecimalPlaces.value.match(/\D/)) {
    errorField = frm.SiDecimalPlaces;
    errorMsg = "The SI decimal places field must be numeric.";
  } else
  if (frm.SiDecimalPlaces.value != "" && (frm.SiDecimalPlaces.value > 4 || frm.SiDecimalPlaces.value < 0)) {
    errorField = frm.SiDecimalPlaces;
    errorMsg = "The SI decimal places field must be between 0 and 4.";
  } else
  if (frm.OtherDecimalPlaces.value == "" && frm.OtherUnitOfMeasure.value != "") {
    errorField = frm.OtherDecimalPlaces;
    errorMsg = "The other decimal places can only be null if no units of measure are given.";
  } else
  if (frm.OtherDecimalPlaces.value != "" && frm.OtherUnitOfMeasure.value == "") {
    errorField = frm.OtherDecimalPlaces;
    errorMsg = "The other units of measure can only be none if no decimal places are given.";
  } else
  if (frm.OtherDecimalPlaces.value != "" && frm.OtherDecimalPlaces.value.match(/\D/)) {
    errorField = frm.OtherDecimalPlaces;
    errorMsg = "The other decimal places field must be numeric.";
  } else
  if (frm.OtherDecimalPlaces.value != "" && (frm.OtherDecimalPlaces.value > 4 || frm.OtherDecimalPlaces.value < 0)) {
    errorField = frm.OtherDecimalPlaces;
    errorMsg = "The other decimal places field must be between 0 and 4.";
  } else
  if (frm.SiUnitOfMeasure.value != "" && frm.SiConversionMultiplier.value == "0") {
	errorField = frm.SiConversionMultiplier;
    errorMsg = "The SI multiplier may not be zero if the SI unit of measure is not set to none.";
  } else
  if (frm.SiConversionMultiplier.value != "" && !frm.SiConversionMultiplier.value.match(/^(\d{0,3})\.\d{1,3}$/)) {
    errorField = frm.SiConversionMultiplier;
    errorMsg = "The SI Conversion Multiplier must be in this format: ###.###";
  } else
  if (frm.OtherUnitOfMeasure.value != "" && frm.OtherConversionMultiplier.value == "0") {
	errorField = frm.OtherConversionMultiplier;
    errorMsg = "The other multiplier may not be zero if the other unit of measure is not set to none.";
  } else
  if (frm.OtherConversionMultiplier.value != "" && !frm.OtherConversionMultiplier.value.match(/^(\d{0,3})\.\d{1,3}$/)) {
    errorField = frm.OtherConversionMultiplier;
    errorMsg = "The Other Conversion Multiplier must be in this format: ###.###";
  } else
  if (numSelectedMethodologies() > 4) {
    errorField = frm.Methodology;
    errorMsg = "You can only associate up to 4 methodologies with a test.";
  } else
  if (frm.ClinicalUtilityInterpretation.value.length > 256) {
    errorField = frm.ClinicalUtilityInterpretation;
    errorMsg = "The clinical interpretation cannot exceed 256 characters.";
  } else
  if (frm.TechnicalReferences.value.length > 256) {
    errorField = frm.TechnicalReferences;
    errorMsg = "The technical references cannot exceed 256 characters.";
  } else
  if (frm.MinimumVolume.value != "" && !frm.MinimumVolume.value.match(/^\d{1,4}\.\d{1,2}$/)) {
    errorField = frm.MinimumVolume;
    errorMsg = "The minimum volume field must be in this format: ####.##";
  } else
  if (frm.MinimumVolume.value != "" && frm.MinimumVolume.value <= 0) {
    errorField = frm.MinimumVolume;
    errorMsg = "The minimum volume field must be greater than 0.";
  } else
  if (frm.PreferredVolume.value == "" && frm.MinimumVolume.value != "") {
    errorField = frm.PreferredVolume;
    errorMsg = "If you enter a minimum volume, you must provide a preferred volume.";
  } else
  if (frm.PreferredVolume.value != "" && !frm.PreferredVolume.value.match(/^\d{1,4}\.\d{1,2}$/)) {
    errorField = frm.PreferredVolume;
    errorMsg = "The preferred volume field must be in this format: ####.##";
  } else
  if (frm.PreferredVolume.value != "" &&  frm.MinimumVolume.value != "" && parseInt(frm.MinimumVolume.value) > parseInt(frm.PreferredVolume.value)) {
    errorField = frm.PreferredVolume;
    errorMsg = "The preferred volume field must be greater than or equal to the minimum volume field.";
  } else
  if ((frm.PreferredVolume.value != "" || frm.MinimumVolume.value != "") && frm.VolumeUnits.value == "") {
    errorField = frm.VolumeUnits;
    errorMsg = "If you enter a minimum or preferred volume, you must provide a volume unit.";
  } else
  if (frm.PreferredVolume.value == "" && frm.MinimumVolume.value == "" && frm.VolumeUnits.value != "") {
    errorField = frm.VolumeUnits;
    errorMsg = "If you enter a volume unit, you must provide a minimum or preferred volume.";
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


function numSelectedMethodologies() {
  var select = document.forms[0].Methodology;
  var numSelected = 0;
  for (var i=0; i < select.options.length; i++) {
    if (select.options[i].selected)
      numSelected++;
  }
  return numSelected;
}

function trim(textbox) {
  if (textbox) {
    while (textbox.value.substring(0,1) == " ") {
      textbox.value = textbox.value.substring(1);
    }
  }
}


function openMethodologyWindow() {
  var selections = showModalDialog("/jims2/control/?Server=jims2&Topic=GetKey&keyNames=Methodology&ReplyTopic=DisplaySelectMethodologies", document.forms[0].Methodology.options);
  if (selections) {
    var list = document.forms[0].Methodology;
    list.options.length = 0;
    for (var i=0; i < selections.length; i++) {
      var selection = selections[i];
      var oid = selection[0];
      var name = selection[1];
      list.options[i] = new Option(name, oid);
    }
  }
}