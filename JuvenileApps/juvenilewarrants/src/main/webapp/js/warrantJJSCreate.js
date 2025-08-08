<!-- warrantJJSCreate.js -->
<script language=javascript>

// this function need because formIndex not supported in struts-html.tld
// first check for checkbox in charge information box to set focus
// if not found, set focus on first input box.
function setPageFocus(){
   for(i=0; i<document.forms[0].length; i++) {
	  if(document.forms[0].elements[i].type == "radio" ){
	     if (document.forms[0].elements[i].name == "chargeSeqNum"){ 
  	        document.forms[0].elements[i].focus();
	        break;
	      }  
	   }  
	   if(document.forms[0].elements[i].type == "text" ){
	     document.forms[0].elements[i].focus();
	     break;
	   }  
    }
	return;     
}

// Cshimek 04-19-2005 commented out code to check for radio button selecttion on charge as part 
//                    of changes for defect JIMS200020188.  this is now handled in app.js
// CShimek 08-25-2005 added warrantType to prevent js error for vop warrants 
function validateFields(theForm,warrantType) {
//   var chargeSelected = false;
   var otherCautionSelected = false;
   var focusSet = false;
   var msg = "";
   var cautionValue = "";
   for(i=0; i<theForm.length; i++) 
   {
	  if(theForm[i].type == "checkbox" )
	  {
         if(theForm[i].name == "selectedCautions")
         {          
            cautionValue = theForm[i].value.toUpperCase();
 	        if(cautionValue == "OT")
 	        { 
	    	   if(theForm[i].checked)
	    	   {
	    	      otherCautionSelected = true;
	    	   }
	        }
	     }   	     
	  }
	}

   if (otherCautionSelected)
   {
	  var fldValue = Trim(theForm.cautionComments.value);
      if (fldValue.length == 0) 
      {
	    msg += "Caution Comment required when OTHER caution is selected.\n";
	    if (!focusSet)
	    {
	      theForm.cautionComments.focus();
	      focusSet = true; 
        }
	  }
   } else {
	   theForm.cautionComments.value = "";
   }
   if (warrantType == "oic"){
	   if (theForm.courtId.selectedIndex == 0){
    	   msg += "Court is required.\n";
	       if (!focusSet){
		      theForm.courtId.focus();
	    	  focusSet = true; 
         	}
		}         	
    }	

    if (msg == ""){
       return true;
    }
    alert(msg);
    return false;
}

function validateJuvenileFields(thisForm)
{
	var msg = "";
	//var SSN1 = Trim(thisForm.SSN1.value);
	//var SSN2 = Trim(thisForm.SSN2.value);
	//var SSN3 = Trim(thisForm.SSN3.value);
	//var ssn1Regex = /^(?!000)([0-9]{3}$)/
	//var ssn2Regex = /^([0-9]{2}$)/
	//var ssn3Regex = /^([0-9]{4}$)/
//alert('SSN1(' + SSN1.length + ')\nSSN2(' + SSN2.length + ')\nSSN3(' + SSN3.length + ')');
	//if (SSN1.length > 0 || SSN1.length > 0 || SSN1.length > 0)
	//{
		//if(ssn1Regex.test(SSN1) == false)
		//{
		//	msg += "First 3 digits of SSN must be numeric and not equal 000.\n";
		//	thisForm.SSN1.focus();
		//}
		//if(ssn2Regex.test(SSN2) == false)
		//{
		//	if (msg == "")
		//	{
		//		thisForm.SSN2.focus();
		//	}
		//	msg += "Second 2 digits of SSN must be numeric.\n";
		//}
		//if(ssn3Regex.test(SSN3) == false)
		//{
		//	if (msg == "")
		//	{
		//		thisForm.SSN3.focus();
		//	}		
		//	msg += "Last 4 digits of SSN must be numeric.\n";
		//}				
	//}
	
	//var feetRegex =  /^\d{1}$/
	//var inchRegex =  /^\d{1,2}$/
		
	//var heightFeet = Trim(thisForm.heightFeet.value);
	//thisForm.heightFeet.value = heightFeet;
	
	//var heightInches = Trim(thisForm.heightInch.value);		
	//thisForm.heightInch.value = heightInches;
	
	//if(heightFeet == "" && heightInches != "")
	//{
	//	msg += "Height feet is required if inches are provided.";
	//	thisForm.heightFeet.focus();
	//}
	
	//if(heightInches == "" && heightFeet != "")
	//{
	//	if (msg == "")
	//	{
	//		thisForm.heightInch.focus();
	//	}	
	//	msg += "Height inches is required if feet are provided.";
	//}
	
	//if(heightFeet != "" && feetRegex.test(heightFeet) == false && msg == "")
	//{
	//	if (msg == "")
	//	{
	//		thisForm.heightFeet.focus();
	//	}	
	//	msg += "Height feet must be an integer.\n";
	//}
	//else if(heightInches != "" && inchRegex.test(heightInches) == false && msg == "")
	//{
	//	if (msg == "")
	//	{
	//		thisForm.heightInch.focus();
	//	}			
	//	msg += "Height inches must be an integer.\n";
	//}

	//var weight = Trim(thisForm.weight.value);
	//thisForm.weight.value = weight;
	
	//var weightRegex =  /^\d{1,3}$/
	
	//if(weight != "")
	//{
	//	if(weightRegex.test(weight) == false)
	//	{
	//		if (msg == "")
	//		{
	//			thisForm.weight.focus();
	//		}
	//		msg += "Weight must be an integer.\n";			
	//	}
	//	else
	//	{
	//		thisForm.weight.value = parseFloat(weight);
	//	}
	//}		
	var area = document.getElementsByName("phoneNum.areaCode")[0].value;	
	var prefix = document.getElementsByName("phoneNum.prefix")[0].value;
	var fourDigit = document.getElementsByName("phoneNum.4Digit")[0].value;
	
	if(area != "" || prefix != "" || fourDigit != "")
	{
		areaRegex = /^\d{3}$/
		prefixRegex = /^\d{3}$/
		fourDigitRegex = /^\d{4}$/
				
		if(areaRegex.test(area) == false)
		{
			msg += "Phone Number Area Code must be an integer of 3 digits.\n"
		}
		if(prefixRegex.test(prefix) == false)
		{
			msg += "Phone Number Prefix must be an integer of 3 digits.\n"
		}
		if(fourDigitRegex.test(fourDigit) == false)
		{
			msg += "Phone Number Suffix must be an integer of 4 digits.\n"
		}
	}
	 
	if(thisForm.schoolDistrictId.selectedIndex > 0)
	{
		if(thisForm.schoolCodeId.selectedIndex == 0)
		{
			msg += "School Name selection is required.\n"
			thisForm.schoolCodeId.focus();
		}
	}
	if(thisForm.schoolCodeId.selectedIndex > 0)
	{
		if(thisForm.schoolDistrictId.selectedIndex == 0)
		{
			msg += "School District selection is required.\n"
			thisForm.schoolDistrictId.focus();
		}
	}
	
	if (msg == "")
	{
		return true;
	}
	else
	{	
		alert(msg);
		return false;
	}
}

</script>
