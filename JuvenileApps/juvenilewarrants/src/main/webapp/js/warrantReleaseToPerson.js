<!-- warrantReleaseToPerson.js -->
<script language=javascript>

var isNewAssociate = true;

function radioIsNewAssociate(el){

	if (el.value != "")
    {
		show('newAssociate', 0);
		isNewAssociate = false;
	}
	else 
	{
		show('newAssociate', 1);
		isNewAssociate = true;
	}
}

function maintainAssociateRadio(theRadioButton){

if (theRadioButton != null){
for (i=0; i<theRadioButton.length; i++)
  {         
   if (theRadioButton[i].checked)
   {
    radioIsNewAssociate(theRadioButton[i]);
   }
  }
 }
}

function showNewAssociate(){

	
		show('newAssociate', 1);
		isNewAssociate = true;
	
}


function validatePhone(areaCode, prefix, fourDigit)
{
	var areaRegex = /^\d{3}$/
	var prefixRegex = /^\d{3}$/
	var fourDigitRegex = /^\d{4}$/
	
	if(areaRegex.test(areaCode) == false 
	   || prefixRegex.test(prefix) == false 
	   || fourDigitRegex.test(fourDigit) == false)
	{
		return false;
	}
	else
	{
		return true;
	}
}

function validateFields(theForm) 
{
	var msg = "";
	
	var zipre = /^[0-9]{5}$/;	
 	var addzipre = /^[0-9]{4}$/;
 	var dateRegex = /^\d{2}\/\d{2}\/\d{4}$/;
 	
 	var ssn1Regex = /^\d{3}$/;
 	var ssn2Regex = /^\d{2}$/;
 	var ssn3Regex = /^\d{4}$/;
 	
 	var nameRegex = /^[a-zA-Z- .()]+$/;
 		
	if(isNewAssociate == true) 
	{
		var lastName = document.getElementsByName("newReleaseToAssociate.associateName.lastName")[0];
		var firstName = document.getElementsByName("newReleaseToAssociate.associateName.firstName")[0];
		var middleName = document.getElementsByName("newReleaseToAssociate.associateName.middleName")[0];
	
		if (lastName.value == "")
		{
			msg +="New Person Released to Last Name is required.\n";
			lastName.focus();
		}
		else
	 	{
	 		if(nameRegex.test(Trim(lastName.value)) == false)
	 		{
	 			msg += "New Person Released to Last Name is invalid.\n";
	 			lastName.focus();
	 		}
	 	}
		
		if (firstName.value == "")
		{
			if (msg == "")
			{
		  		firstName.focus();
	  		}
			msg +="New Person Released to First Name is required.\n";
		}
		else
	 	{
	 		if(nameRegex.test(Trim(firstName.value)) == false)
	 		{
	 			msg += "New Person Released to First Name is invalid.\n";
	 			firstName.focus();
	 		}
	 	}
	 	
	 	if(Trim(middleName.value) != "")
	 	{
	 		if(nameRegex.test(Trim(middleName.value)) == false)
	 		{
	 			msg += "New Person Released to Middle Name is invalid.\n";
	 			middleName.focus();
	 		}
	 	}
		
		var dateOfBirth = document.getElementsByName("newReleaseToAssociate.dateOfBirthString")[0];
 		dateOfBirth.value = Trim(dateOfBirth.value);	 			
		
		if (dateOfBirth.value == "")
		{
	    	if (msg == "")
	    	{
				dateOfBirth.focus();
			}
			msg += "New Person Date of Birth is required.\n";
		}
		else
		{			
			var invalidDOB = checkDateFormat(dateOfBirth.value);
	 		if(invalidDOB == true)
 			{
 	    		if (msg == "")
	    		{
					dateOfBirth.focus();
				}
	        	msg += "New Person Date of Birth is invalid.  The valid format is mm/dd/yyyy.\n";
	    	}
	    	if(invalidDOB == false)
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
 	    			if (msg == "")
	    			{
						dateOfBirth.focus();
					}				
   	    			msg += "New Person Birth Date cannot be a future date.\n";
				} 
				if (century < 19 && inDateStr <= currDateStr) 
				{
 	    			if (msg == "")
	    			{
						dateOfBirth.focus();
					}					
	   	    		msg += "New Person Date of Birth year cannot start with a number lower than 19.\n";
				}
	    	}
	    }
		
		
		if (document.getElementsByName("newReleaseToAssociate.raceId")[0].selectedIndex == 0)
		{
	    	if (msg == "")
	    	{
			  document.getElementsByName("newReleaseToAssociate.raceId")[0].focus();
	    	}
			msg +="Race is required.\n";
		}
		
		if (document.getElementsByName("newReleaseToAssociate.sexId")[0].selectedIndex == 0)
		{
			if (msg == "")
			{
				document.getElementsByName("newReleaseToAssociate.sexId")[0].focus();
			}
			msg +="Sex is required.\n";
		}
		
		if (document.getElementsByName("newReleaseToAssociate.relationshipToJuvenileId")[0].selectedIndex == 0)
		{
			if (msg == "")
			{
				document.getElementsByName("newReleaseToAssociate.relationshipToJuvenileId")[0].focus();
	    	}
			msg +="Relationship to Juvenile is required.\n";
		}
		
		// BEGIN SSN
		
		var ssn1 = Trim(document.getElementsByName("newReleaseToAssociate.ssn.SSN1")[0].value);
	 	var ssn2 = Trim(document.getElementsByName("newReleaseToAssociate.ssn.SSN2")[0].value);
	 	var ssn3 = Trim(document.getElementsByName("newReleaseToAssociate.ssn.SSN3")[0].value);
	 	
	 	if(ssn1 != "" || ssn2 != "" || ssn3 != "")
	 	{
	 		if(ssn1Regex.test(ssn1) == false
	 		   || ssn2Regex.test(ssn2) == false
	 		   || ssn3Regex.test(ssn3) == false)
	 		{
	 			msg += "Social Security Number is invalid or incomplete.\n";
	 			if (msg == "")
		   		{
		   			document.getElementsByName("newReleaseToAssociate.ssn.SSN1")[0].focus();
			    }
	 		}
	 	}
		
		// END SSN
		
		// BEGIN Phone number validation
		
		areaCode = theForm.elements["newReleaseToAssociate.homePhoneNum.areaCode"].value;
		prefix = theForm.elements["newReleaseToAssociate.homePhoneNum.prefix"].value;
		fourDigit = theForm.elements["newReleaseToAssociate.homePhoneNum.4Digit"].value;		
		if(areaCode != "" || prefix != "" || fourDigit != "")
		{
			if(validatePhone(areaCode, prefix, fourDigit) == false)
			{
				msg += "Home Phone is invalid or not complete.\n";
			}
		}
	
		areaCode = theForm.elements["newReleaseToAssociate.workPhoneNum.areaCode"].value;
		prefix = theForm.elements["newReleaseToAssociate.workPhoneNum.prefix"].value;
		fourDigit = theForm.elements["newReleaseToAssociate.workPhoneNum.4Digit"].value;		
		if(areaCode != "" || prefix != "" || fourDigit != "")
		{
		    if(validatePhone(areaCode, prefix, fourDigit) == false)
			{
				msg += "Work Phone is invalid or not complete.\n";
			}
		}
	
		areaCode = theForm.elements["newReleaseToAssociate.cellPhoneNum.areaCode"].value;
		prefix = theForm.elements["newReleaseToAssociate.cellPhoneNum.prefix"].value;
		fourDigit = theForm.elements["newReleaseToAssociate.cellPhoneNum.4Digit"].value;		
		if(areaCode != "" || prefix != "" || fourDigit != "")
		{
		    if(validatePhone(areaCode, prefix, fourDigit) == false)
			{
				msg += "Cell Phone is invalid or not complete.\n";
			}
		}
		
		areaCode = theForm.elements["newReleaseToAssociate.pager.areaCode"].value;
		prefix = theForm.elements["newReleaseToAssociate.pager.prefix"].value;
		fourDigit = theForm.elements["newReleaseToAssociate.pager.4Digit"].value;		
		if(areaCode != "" || prefix != "" || fourDigit != "")
		{
		    if(validatePhone(areaCode, prefix, fourDigit) == false)
			{
				msg += "Pager is invalid or not complete.\n";
			}
		}
		
		areaCode = theForm.elements["newReleaseToAssociate.faxNum.areaCode"].value;
		prefix = theForm.elements["newReleaseToAssociate.faxNum.prefix"].value;
		fourDigit = theForm.elements["newReleaseToAssociate.faxNum.4Digit"].value;		
		if(areaCode != "" || prefix != "" || fourDigit != "")
		{
		    if(validatePhone(areaCode, prefix, fourDigit) == false)
			{
				msg += "Fax Number is invalid or not complete.\n";
			}
		}
		
		// END Phone number validation
		
		
		if (document.getElementsByName("streetNum")[0].value == "")
		{
			if (msg == "")
			{
				document.getElementsByName("streetNum")[0].focus();
		    }
			msg +="Street Num is required.\n";
		}
		
		if (document.getElementsByName("streetName")[0].value == "")
		{
			if (msg == "")
			{
				document.getElementsByName("streetName")[0].focus();
		    } 
			msg +="Street Name is required.\n";
		}
		
		if (document.getElementsByName("city")[0].value == "")
		{
			if (msg == "")
		    {
			  document.getElementsByName("city")[0].focus();
		    } 
		    msg +="City is required.\n";
		}
		
		if (document.getElementsByName("address[0].stateId")[0].value == "")		
		{
			if (msg == "")
			{
				document.getElementsByName("address[0].stateId")[0].focus();
		    } 
			msg += "State is required.\n";
		}
		
		var zipCode = document.getElementsByName("zipCode")[0].value;
		var zipCode2 = document.getElementsByName("additionalZipCode")[0].value;
		zipCode = Trim(zipCode);
		zipCode2 = Trim(zipCode2);
		
		if (zipCode != "")
	    {
			if(!zipre.test(zipCode)){
	    		if (msg == "")
	    		{
		   			document.getElementsByName(zipCode)[0].focus();		    		
			    }
			    msg += "Zip Code must be a 5 digit number.\n";	    
			}
			
		    if (zipCode2 != "")
		    {
				if(!addzipre.test(zipCode2)){
	    			if (msg == "")
	    			{
		   				document.getElementsByName("additionalZipCode")[0].focus();		    			
			    	}
				    msg += "Zip Code extension must be a 4 digit number.\n";	    
				}	
	    	}	
	    }
	    
	    if (zipCode2 != "" && zipCode == "")
	    {
			if (msg == "")
			{
	   			document.getElementsByName(zipCode)[0].focus();	    			
	    	}
		    msg += "Zip Code required when zip code extension is entered.\n";
	    }
	}
 
	if (msg == "")
	{
		return true;
	}
	
	alert (msg);
    return false;
}

function checkDateFormat(dateField)
{
   if (dateField.length != 10)
   {
      return true;
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
      return true;
   }
   
   if (sl1 != "/" || sl2 != "/")
   {
      return true;
   }
   
   if (mm == "00" || mm > 12 || dd == "00" || yyyy == "0000" )
   {
      return true;
   }
   
   if (mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12){
      if (dd > 31){
         return true;
      }
   }
   if (mm == 4 || mm == 6 || mm == 9 || mm == 11){
      if (dd > 30){
         return true;
      }
   }
   var leapYr = yyyy % 4;
   if (mm == 2){
       if ((leapYr == 0 && dd > 29) || leapYr != 0 && dd > 28){
           return true;
       }
   }
   return false;
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

function setStateCounty(){
// set Update Addressess county based on State selected value	
	var countyName = "";
	var fldName = "";
	var selectedState = "";
	var selectedCounty = "";
	var stSelect = "";		
	for (y=0; y<document.forms[0].length; y++){
		fldName = "address[" + y + "].stateId";
	    stSelect = document.getElementsByName(fldName);	
		if (stSelect.length == 0){
			break;
		}

 		countyName="address[" + y + "].countyId";
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

</script>