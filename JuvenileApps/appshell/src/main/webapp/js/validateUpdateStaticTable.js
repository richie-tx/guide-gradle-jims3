function validate() {
  var errorMsg = null;
  var errorField = null;
  var frm = document.forms[0];

  trim(frm.KeyName);
  trim(frm.Description);
  trim(frm.Reason);

  if (frm.KeyName && frm.KeyName.value == "") {
    errorField = frm.KeyName;
    errorMsg = "You must enter a static table name.";
  } else
  if (frm.KeyName && frm.KeyName.value.match(/[@#%\?\&\+']+/)) {
    errorField = frm.KeyName;
    errorMsg = "You cannot use @, #, %, ?, &, ', or + in a static table name.";
  } else
  if (frm.Description.value == "") {
    errorField = frm.Description;
    errorMsg = "You must enter a static table description.";
  } else 
  if (frm.Description.value.length > 100) {
    errorField = frm.Description;
    errorMsg = "The description cannot be more than 100 characters.";
  } else 
  if (frm.Reason && frm.Reason.value == "") {
    errorField = frm.Reason;
    errorMsg = "You must enter a reason for editing this record before saving.";
  } else
  if (duplicateValues(frm)) {
    errorField = frm.Values[0];
    errorMsg = "Two or more of the values are identical.  You cannot have identical values.";
  } else {
    for (var i=0; i < frm.Values.length && errorMsg == null; i++) {
      trim(frm.Values[i]);
      if (frm.Values[i].value == "") {
        trim(frm.Values[i+1]);
        trim(frm.Descriptions[i]);
        trim(frm.Attribute1s[i]);
        trim(frm.Attribute2s[i]);
        trim(frm.Attribute3s[i]);
        trim(frm.Attribute4s[i]);
        trim(frm.Attribute5s[i]);
        trim(frm.Attribute6s[i]);
        trim(frm.Attribute7s[i]);
        trim(frm.Attribute8s[i]);
        if (frm.OIDs[i].value != "") {
          errorField = frm.Values[i];
          errorMsg = "You cannot change an existing value to empty.";
        }
        if (i+1 < frm.Values.length && frm.Values[i+1].value != "") {
          errorField = frm.Values[i];
          errorMsg = "Please do not skip a row in the table.";
        }
        if (frm.Descriptions[i].value != "" || frm.Attribute1s[i].value != "" ||
	    frm.Attribute2s[i].value != "" || frm.Attribute3s[i].value != "" ||
	    frm.Attribute4s[i].value != "" || frm.Attribute5s[i].value != "" ||
	    frm.Attribute6s[i].value != "" || frm.Attribute7s[i].value != "" ||
	    frm.Attribute8s[i].value != "") {
	  errorField = frm.Values[i];
	  errorMsg = "You cannot create a row in this table without specifying a value.";
	}
      }
    }
  }

  if (errorMsg != null && errorField != null) {
    alert(errorMsg);
    errorField.focus();
    return false;
  } else {
    if (tooManyValues(frm)) {
      return confirm("This static table has 200 or more records.  You should not have this many values for a single table.  Please confirm that the data you entered is correct.");
    } else{
	disableSubmitButton();
      return true;}
  }
}

function tooManyValues(frm) {
  var numTotalVals = frm.Values.length;
  var numVals = numTotalVals;
  for (var i=0; i < numTotalVals; i++) {
    if ((frm.Inactives && frm.Inactives[i] && frm.Inactives[i].checked) || frm.Values[i].value == "")
      numVals--;
  }
  return (numVals >= 200);
}

function duplicateValues(frm) {
  var duplicate = -1;
  for (var i=0; i < frm.Values.length && duplicate == -1; i++) {
    var valueToSearchFor = frm.Values[i].value.toUpperCase();
    for (var j=i+1; j < frm.Values.length && duplicate == -1 && valueToSearchFor != ""; j++) {
      if (valueToSearchFor == frm.Values[j].value.toUpperCase())
        duplicate = i;
    }
  }
  return duplicate != -1;
}

function trim(textbox) {
  if (textbox) {
    while (textbox.value.substring(0,1) == " ") {
      textbox.value = textbox.value.substring(1);
    }
  }
}
