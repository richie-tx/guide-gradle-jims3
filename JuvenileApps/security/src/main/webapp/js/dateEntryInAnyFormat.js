// Javascripts - Date Entry In Any Format
<!-- ENTER DATE ANY FORMAT JAVASCRIPT -->

<!-- <script Language="JavaScript"> -->

function checkdate(objName) {
var datefield = objName;

if (chkdate(objName) == false) {
datefield.select();
alert("That date is invalid.  Please enter a valid date in any format.");
datefield.focus();
return false;
}
}

function chkdate(objName) {
var strDatestyle = "US"; //United States date style
var strDate;
var strDateArray;
var strDay;
var strMonth;
var strYear;
var intday;
var intMonth;
var intYear;
var booFound = false;
var datefield = objName;
var strSeparatorArray = new Array("-"," ","/",".");
var intElementNr;
var err = 0;
var strMonthArray = new Array(12);
strMonthArray[0] = "Jan";
strMonthArray[1] = "Feb";
strMonthArray[2] = "Mar";
strMonthArray[3] = "Apr";
strMonthArray[4] = "May";
strMonthArray[5] = "Jun";
strMonthArray[6] = "Jul";
strMonthArray[7] = "Aug";
strMonthArray[8] = "Sep";
strMonthArray[9] = "Oct";
strMonthArray[10] = "Nov";
strMonthArray[11] = "Dec";
strDate = datefield.value;
if (strDate.length < 1) {
return true;
}
for (intElementNr = 0; intElementNr < strSeparatorArray.length; intElementNr++) {
if (strDate.indexOf(strSeparatorArray[intElementNr]) != -1) {
strDateArray = strDate.split(strSeparatorArray[intElementNr]);
if (strDateArray.length != 3) {
err = 1;
return false;
}
else {
strDay = strDateArray[0];
strMonth = strDateArray[1];
strYear = strDateArray[2];
}
booFound = true;
   }
}
if (booFound == false) {
if (strDate.length>5) {
strDay = strDate.substr(0, 2);
strMonth = strDate.substr(2, 2);
strYear = strDate.substr(4);
}
else {
	strDay = ""	
	strMonth = ""
	strYear = ""
}   
}
if (strYear.length < 2) {
err = 2;
return false;
}
if ((strYear.length != 2) && (strYear.length !=4)) {
err = 2;
return false;
}
if (strYear.length == 2) {
	if (strYear < 11) {
		strYear = '20' + strYear;
	}
	else {
		strYear = '19' + strYear;
	}
}
if (strDatestyle == "US") {
strTemp = strDay;
strDay = strMonth;
strMonth = strTemp;
}
intday = parseInt(strDay, 10);
if (isNaN(intday)) {
err = 2;
return false;
}
intMonth = parseInt(strMonth, 10);
if (isNaN(intMonth)) {
for (i = 0;i<12;i++) {
if (strMonth.toUpperCase() == strMonthArray[i].toUpperCase()) {
intMonth = i+1;
strMonth = strMonthArray[i];
i = 12;
   }
}
if (isNaN(intMonth)) {
err = 3;
return false;
   }
}
intYear = parseInt(strYear, 10);
if (isNaN(intYear)) {
err = 4;
return false;
}
if (intMonth>12 || intMonth<1) {
err = 5;
return false;
}
if ((intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12) && (intday > 31 || intday < 1)) {
err = 6;
return false;
}
if ((intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) && (intday > 30 || intday < 1)) {
err = 7;
return false;
}
if (intMonth == 2) {
if (intday < 1) {
err = 8;
return false;
}
if (LeapYear(intYear) == true) {
if (intday > 29) {
err = 9;
return false;
}
}
else {
if (intday > 28) {
err = 10;
return false;
}
}
}
strMonth = intMonth.toString();
if (strMonth.length < 2){
	strMonth = "0" + strMonth;
}	
strDay = intday.toString();
if (strDay.length < 2){
	strDay = "0" + strDay;
}
datefield.value = strMonth + "/" + strDay +"/" + strYear;
return true;
}

function LeapYear(intYear) {
if (intYear % 100 == 0) {
if (intYear % 400 == 0) { return true; }
}
else {
if ((intYear % 4) == 0) { return true; }
}
return false;
}
<!-- FORM FIELD VALIDATION JAVASCRIPT -->
function formCheck(theForm) 
{
  if (theForm.seqnum.value == "")
  {
    alert("Please enter a value for the Sequence Number.");
    theForm.seqnum.focus();
    return false;
  }
  if (theForm.seqnum.value.length < 8)
  {
    alert("Please enter at least 8 characters in the Sequence Number.");
    theForm.seqnum.focus();
    return (false);
  }
  
  if (theForm.seqnum.value.length > 8)
  {
    alert("Please enter at most 8 characters in the Sequence Number.");
    theForm.seqnum.focus();
    return (false);
  }
  var checkOK = "0123456789-";
  var checkStr = theForm.seqnum.value;
  var allValid = true;
  var decPoints = 0;
  var allNum = "";
  for (i = 0;  i < checkStr.length;  i++)
  {
    ch = checkStr.charAt(i);
    for (j = 0;  j < checkOK.length;  j++)
      if (ch == checkOK.charAt(j))
        break;
    if (j == checkOK.length)
    {
      allValid = false;
      break;
    }
    allNum += ch;
  }
  if (!allValid)
  {
    alert("Please enter only digit characters in the Sequence Number.");
    theForm.seqnum.focus();
    return (false);
  }
  if (theForm.jurornum.value == "") 
        {
           alert("Please enter a value for the Juror Number.");
           theForm.jurornum.focus();
		   return false;
        }
  
   if (theForm.jurornum.value.length < 4)
  {
    alert("Please enter the 4 digits before hyphen in the Juror Number.");
    theForm.jurornum.focus();
    return (false);
  }

  if (theForm.jurornum.value.length > 4)
  {
    alert("Please enter only the 4 digits before hyphen in the Juror Number.");
    theForm.jurornum.focus();
    return (false);
  }

  var checkOK = "0123456789-.";
  var checkStr = theForm.jurornum.value;
  var allValid = true;
  var decPoints = 0;
  var allNum = "";
  for (i = 0;  i < checkStr.length;  i++)
  {
    ch = checkStr.charAt(i);
    for (j = 0;  j < checkOK.length;  j++)
      if (ch == checkOK.charAt(j))
        break;
    if (j == checkOK.length)
    {
      allValid = false;
      break;
    }
    if (ch == ".")
    {
      allNum += ".";
      decPoints++;
    }
    else
      allNum += ch;
  }
  if (!allValid)
  {
    alert("Please enter only digit characters in the Juror Number.");
    theForm.jurornum.focus();
    return (false);
  }

  if (decPoints > 1)
  {
    alert("Please enter a valid number in Juror Number.");
    theForm.jurornum.focus();
    return (false);
  }

   if (theForm.lastname.value == "")
  {
    alert("Please enter your last name.");
    theForm.lastname.focus();
    return (false);
  }
  
   if (theForm.servdate.value == "")
  {
    alert("Please enter your Original Service Date.");
    theForm.servdate.focus();
    return (false);
  }
  var dateck = true;
  dateck = checkdate(theForm.servdate);
  return (dateck);
}

<!--< /script> -->

