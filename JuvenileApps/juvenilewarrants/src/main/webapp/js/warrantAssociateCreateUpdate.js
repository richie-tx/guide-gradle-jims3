function setStateCounty(){
// set Update Addressess county based on State selected value	
	var countyName = "";
	var fldName = "";
	var selectedState = "";
	var selectedCounty = "";
	var stSelect = "";		
	for (y=0; y<document.forms[0].length; y++){
		fldName = "associateAddresses[" + y + "].stateId";
	    stSelect = document.getElementsByName(fldName);		
		if (stSelect.length == 0){
			break;
		}
 		countyName="associateAddresses[" + y + "].countyId";
		theCountySelect = document.getElementsByName(countyName);  
 		
	    selectedState = stSelect[0].value;
	    selectedCounty = theCountySelect[0].value;
	    if (selectedState == ""){
	    	stSelect[0].value = "TX";
	    	selectedState = "TX";
	    }
		if (selectedState == "TX"){  	
		  	theCountySelect[0].disabled = false;
		  	if (selectedCounty == ""){
				for (x=0; x<theCountySelect[0].length; x++){
					if (theCountySelect[0].options[x].value == "101"){
						theCountySelect[0].options[x].selected = true;
						break;
					}
				}	
			}  	
		}else {
  			theCountySelect[0].selectedIndex=0;
		  	theCountySelect[0].disabled = true; 	  	
		}	    
	}	
}

function enableCounty(theSelect, countyId)
{
  var countyName=countyId.replace(".stateId",".countyId");
  
  var selectedState = theSelect.options[theSelect.selectedIndex].value;
  var theForm = theSelect.form;
  var theCountySelect = document.getElementsByName(countyName);  
 
  if (selectedState == "TX"){  	
  	theCountySelect[0].disabled = false;
	for (x=0; x<theCountySelect[0].length; x++){
		if (theCountySelect[0].options[x].value == "101"){
			theCountySelect[0].options[x].selected = true;
			break;
		}
	}  	
  }else {
  	theCountySelect[0].selectedIndex=0;
  	theCountySelect[0].disabled = true; 	  	
  }
}

function checkForm(theForm)
{
    var msg = "";
 	if (theForm.relationshipToJuvenileId.selectedIndex == 0){
 		msg = "Relationship to Juvenile selection is required.\n";
 		theForm.relationshipToJuvenileId.focus();
 	} 
 	var dateOfBirth = document.getElementsByName("dateOfBirthString")[0];
 	dateOfBirth.value = Trim(dateOfBirth.value);
 	if(dateOfBirth.value != "")
 	{
 		var validDOB = checkDateFormat(dateOfBirth.value);
 		if (!validDOB){
    		if (msg == ""){
    			theForm.dateOfBirthString.focus();
    		}	
    		msg += "Date of Birth is invalid.  The valid format is MM/DD/YYYY.\n";
 		}
	 	if(validDOB)
	 	{
	    	var currDate=new Date();
			var curDate = new Date();
			var currYYYY = curDate.getFullYear().toString();
			var currMM = curDate.getMonth() + 1;
			var currDD = curDate.getDate();
			if (currMM < 10){
				currMM = "0" + currMM;
			}
			if (currDD < 10){
				currDD = "0" + currDD;
			}      		
		      		
			var inDate = dateOfBirth.value;
			var mm = inDate.substring(0,2);
			var dd = inDate.substring(3,5);
			var yyyy = inDate.substring(6,10);
			var century = inDate.substring(6,8);
			var inDateStr = inDate.substring(6,10) + inDate.substring(0,2) + inDate.substring(3,5); 
			var currDateStr = currYYYY + currMM.toString() + currDD.toString();
			if (inDateStr > currDateStr)
			{
   	    		if (msg == ""){
	    			theForm.dateOfBirthString.focus();
	    		}	
   	    		msg += "Birth Date cannot be a future date.";
			} 
			if (century < 19 && inDateStr <= currDateStr) 
			{
   	    		if (msg == ""){
	    			theForm.dateOfBirthString.focus();
	    		}	
   	    		msg += "Date of Birth year cannot start with a number lower than 19.";
			}
	    }
	}
	if (msg == ""){
		return true;
	}
	alert(msg);
	return false;		
}

function checkDateFormat(dateField)
{
   if (dateField.length != 10)
   {
      return false;
   } 
   var mm = dateField.substring(0,2);
   var dd = dateField.substring(3,5);
   var yyyy = dateField.substring(6,10);
   var sl1 = dateField.substring(2,3);
   var sl2 = dateField.substring(5,6);
   
   var dateRegex =  /^\d{2}$/
   var yearRegex = /^\d{4}$/

   if (dateRegex.test(mm) == false || dateRegex.test(dd) == false || yearRegex.test(yyyy) == false)
   {
      return false;
   }
   
   if (sl1 != "/" || sl2 != "/")
   {
      return false;
   }

   if (mm == "00" || mm > 12 || dd == "00" || yyyy == "0000" )
   {
      return false;
   }

   if (mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12){
      if (dd > 31){
         return false;
      }
   }

   if (mm == 4 || mm == 6 || mm == 9 || mm == 11){
      if (dd > 30){
         return false;
      }
   }

   var leapYr = yyyy % 4;
   if (mm == 2){
       if ((leapYr == 0 && dd > 29) || leapYr != 0 && dd > 28){
           return false;
       }
   }
return true;
}

function Trim(s)
{
// Remove leading spaces and carriage returns
while ((s.substring(0,1) == ' ') || (s.substring(0,1) == '\n') || (s.substring(0,1) == '\r'))
{ s = s.substring(1,s.length); }

// Remove trailing spaces and carriage returns
while ((s.substring(s.length-1,s.length) == ' ') || (s.substring(s.length-1,s.length) == '\n') || (s.substring(s.length-1,s.length) == '\r'))
{ s = s.substring(0,s.length-1); }

return s;
}