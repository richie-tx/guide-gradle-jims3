function validateInput(theForm){
      var msg = "";
      var PIDRegExp = /^[a-zA-Z0-9 .()\x26\x2f\x5c\x3b,-]*$/;  //a-zA-Z0-9 '-./\();&,  
      var outDate = document.getElementsByName("transferCasesInfo.transferOutDate");
      var inDate = document.getElementsByName("transferCasesInfo.transferInDate");
      var rejects = document.getElementsByName("transferCasesInfo.rejectedAsStr");
      var txCounty = document.getElementsByName("transferCasesInfo.transferTxCountyId");
      var outState = document.getElementsByName("transferCasesInfo.transferStateId");
      var personId = document.getElementsByName("transferCasesInfo.personId");
      var outDateStr = outDate[0].value;;
      var inDateStr = "";
      
      msg = validateDate(outDateStr, "Transfer Out Date", true );
      if (msg != ""){
            outDate[0].focus();
      }
      
// if transferIn date present, then transferIn date and Person Id values must be validated
      if (inDate.length > 0){
            inDateStr = inDate[0].value;
            if (msg == ""){
                  inDate[0].focus();
                  msg += validateDate(inDateStr, "Transfer In Date", true );
            }
            if (msg == ""){
                  var oDate = outDateStr.split("/");
                  var iDate = inDateStr.split("/");
                  outDateStr = oDate[2] + oDate[0] + oDate[1]; 
                  inDateStr = iDate[2] + iDate[0] + iDate[1];
                  if (outDateStr > inDateStr){
                        inDate[0].focus();
                        msg += "Transfer In Date cannot be previous to Transfer Out Date.\n";
                  }     
            }     
      }
      if (txCounty[0].selectedIndex == 0 && outState[0].selectedIndex == 0){
            if (msg == ""){
                  txCounty[0].focus();
            }     
            msg += "Texas County or Out of State selection required.";                    
      }     
      if (inDate.length > 0){
            if (rejects[1].checked == true){   // No selected
                  var personIdStr = trimAll(personId[0].value);
                  if (personIdStr == ""){
                        if (msg == ""){
                              personId[0].focus();
                        }     
                        msg += "Person ID required when Rejected is No.";
                  } else {
                        if (PIDRegExp.test(personIdStr,PIDRegExp) == false){
                              if (msg == ""){
                                    personId[0].focus();
                              }     
                              msg += "Person ID contains invalid character.";
                        }
                  }          
            }
      }
      if (msg == ""){
            return true;
      }
      alert(msg); 
      return false;     
}

function validateDate(fldValue, fldName, futureDateEdit){
	var msg = "";
	var numericRegExp = /^[0-9]*$/;
	if (fldValue == "")
	{
		msg = fldName + " is required.\n";
		return msg;
	}
	if (fldValue.length < 10 || fldValue.indexOf("/") < 2)
	{
		msg = fldName + " must be in the MM/DD/YYYY format.\n";
		return msg;
	}
	var dValues = fldValue.split('/');
	var month = dValues[0];
	var day = dValues[1];
	var year = dValues[2];

	if (numericRegExp.test(month,numericRegExp) == false || numericRegExp.test(day,numericRegExp) == false || numericRegExp.test(year,numericRegExp) == false ) { 
		msg = fldName + " is not a valid date.\n";
		return msg;	
	} 

	if (month.length != 2 || day.length != 2 || year.length != 4) {
		msg = fldName + " must be in the MM/DD/YYYY format.\n";
		return msg;	
	} 

    if (month < 1 || month > 12)
    {
		msg = fldName + " is not valid.\n";
		return msg;		
    }
    if (day < 1 || day > 31) {
		msg = fldName + " is not valid.\n";
		return msg;		
    }
    if ((month == 4 || month == 6 || month == 9 || month == 11) && (day == 31))
    {
		msg = fldName + " is not valid.\n";
		return msg;	
    }
    if (month == 2) {
        var leap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
        if (day > 29 || (day == 29 && !leap)) {
			msg = fldName + " is not valid.\n";
			return msg;	
        }
    }    
    if (futureDateEdit && msg == ""){
		var dt = fldValue + " 00:00";
		var fldDateTime = new Date(dt);
		var curDateTime = new Date();
		if (fldDateTime > curDateTime){
			msg = fldName + " can not be future value.\n";
			return msg;				
		}    	
    }
 	return msg;
}	

function checkSelect(el){
	var resetName = "transferCasesInfo.transferTxCountyId";
	if (el.selectedIndex > 0){
		if (el.name == resetName){
			resetName = "transferCasesInfo.transferStateId";
		} 
		flds = document.getElementsByName(resetName);
		flds[0].selectedIndex = 0;
	}	
}