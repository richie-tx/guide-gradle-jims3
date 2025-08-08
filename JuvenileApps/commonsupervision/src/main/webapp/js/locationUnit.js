//Validate both Location unit name and code along with phone number

function validateLocationUnit() 
{	
	var thisForm = document.forms[0];
	var msg = "";
	var locUnitName = "";
	var locUnitCd = "";
	var alphanumericName = /^[a-zA-Z0-9-\/ ]+$/;
	var alphanumericSymbolName = /^[a-zA-Z0-9/ \\.,()\-\x3B\x26\x27]*$/;
	locUnitCd = Trim(thisForm['locUnit.juvUnitCd'].value);
	locUnitName = Trim(thisForm['locUnit.locationUnitName'].value);
	thisForm['locUnit.juvUnitCd'].value = locUnitCd;
	thisForm['locUnit.locationUnitName'].value = locUnitName;
	
		if (locUnitCd == "" || locUnitCd == null)
		{
			thisForm['locUnit.juvUnitCd'].focus();
			msg += "Location Unit Code is required.\n";			
		}
		else if((alphanumericName.test(locUnitCd) == false))
		{
			thisForm['locUnit.juvUnitCd'].focus();
			msg += "Location Unit Code must be alphanumeric.\n";
		}
		else if (locUnitName == "" || locUnitName == null)
		{
			thisForm['locUnit.locationUnitName'].focus();
			msg += "Location Unit Name is required.\n";
		}
		else if((alphanumericSymbolName.test(locUnitName) == false))
		{
			thisForm['locUnit.locationUnitName'].focus();
			msg += "Location Unit Name must be alphanumeric.\n";			
		}	
	
	//Validate phone
   else if (thisForm["locUnit.phoneNumber.areaCode"].value > "" && thisForm["locUnit.phoneNumber.prefix"].value == "") {
      msg += "Phone Prefix must be entered if Phone Area Code is entered.\n";
      thisForm["locUnit.phoneNumber.prefix"].focus();
   }
   else if (thisForm["locUnit.phoneNumber.prefix"].value > "" && thisForm["locUnit.phoneNumber.last4Digit"].value == "") {
	   msg += "Phone Last4Digit number must be entered if Phone Prefix is entered.\n";
       thisForm["locUnit.phoneNumber.last4Digit"].focus();
   }
	else if (thisForm["locUnit.phoneNumber.prefix"].value == "" && thisForm["locUnit.phoneNumber.last4Digit"].value > "") {
      msg += "Phone Prefix must be entered if Phone Last4Digit number is entered.\n";
      thisForm["locUnit.phoneNumber.prefix"].focus();
     }
    else if (thisForm["locUnit.phoneNumber.areaCode"].value == "" && thisForm["locUnit.phoneNumber.last4Digit"].value > "") {
	   	msg+= "Phone Area Code must be entered if Phone Last4Digit number is entered.\n";
        thisForm["locUnit.phoneNumber.areaCode"].focus();
     }
     if(msg.length > 0){
     	alert(msg);
     	return false;
     	}
}

//Validate Location unit name along with phone number
function validateEditLocationUnit() 
{	
	var thisForm = document.forms[0];
	var msg = "";
	var locUnitName = "";
	var locUnitCd = "";
//	var alphanumericName = /^[a-zA-Z0-9-\/ ]+$/;
	var alphanumericSymbolName = /^[a-zA-Z0-9/ \\.,()\-\x3B\x26\x27]*$/;	
	locUnitName = Trim(thisForm['locUnit.locationUnitName'].value);
	thisForm['locUnit.locationUnitName'].value = locUnitName;	
		
		if (locUnitName == "" || locUnitName == null)
		{
			thisForm['locUnit.locationUnitName'].focus();
			msg += "Location Unit Name is required.\n";
		}
		else if((alphanumericSymbolName.test(locUnitName) == false))
		{
			thisForm['locUnit.locationUnitName'].focus();
			msg += "Location Unit Name must be alphanumeric.\n";			
		}	
	
	//Validate phone
   else if (thisForm["locUnit.phoneNumber.areaCode"].value > "" && thisForm["locUnit.phoneNumber.prefix"].value == "") {
      msg += "Phone Prefix must be entered if Phone Area Code is entered.\n";
      thisForm["locUnit.phoneNumber.prefix"].focus();
   }
   else if (thisForm["locUnit.phoneNumber.prefix"].value > "" && thisForm["locUnit.phoneNumber.last4Digit"].value == "") {
	   msg += "Phone Last4Digit number must be entered if Phone Prefix is entered.\n";
       thisForm["locUnit.phoneNumber.last4Digit"].focus();
   }
	else if (thisForm["locUnit.phoneNumber.prefix"].value == "" && thisForm["locUnit.phoneNumber.last4Digit"].value > "") {
      msg += "Phone Prefix must be entered if Phone Last4Digit number is entered.\n";
      thisForm["locUnit.phoneNumber.prefix"].focus();
     }
    else if (thisForm["locUnit.phoneNumber.areaCode"].value == "" && thisForm["locUnit.phoneNumber.last4Digit"].value > "") {
	   	msg+= "Phone Area Code must be entered if Phone Last4Digit number is entered.\n";
        thisForm["locUnit.phoneNumber.areaCode"].focus();
     }
     if(msg.length > 0){
     	alert(msg);
     	return false;
     	}
}

function Trim(s)
{

	// Remove leading spaces and carriage returns
	while ((s.substring(0,1) == ' ') || (s.substring(0,1) == '\n') || (s.substring(0,1) == '\r'))
	{
		s = s.substring(1,s.length);
	}

	// Remove trailing spaces and carriage returns
	while ((s.substring(s.length-1,s.length) == ' ') || (s.substring(s.length-1,s.length) == '\n') || (s.substring(s.length-1,s.length) == '\r'))
	{
		s = s.substring(0,s.length-1);
	}

	return s;
}