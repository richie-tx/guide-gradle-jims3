<!-- warrantJOTCreate.js -->
<!-- DGibler 08/23/05 - Added validateAlphanumericField() and edits of officerId, badgeNum and prompt.payrollNumber for alphanumeric contents -->
<!-- UGopinath 08/17/06 - Changed payrollNumber to otherIdNumber -->
<script type="text/javascript">
// need check for button selected, if not "next" return true immediately.

// this function need because formIndex not supported in struts-html.tld
// first check for checkbox in charge information box to set focus
// if not found, set focus on first input box.
function setPageFocus(){
    var selCharges = document.getElementsByName("selectedCharges");
	if (selCharges.length > 0){
		selCharges[0].focus();	
	    if (selCharges.length == 1){
   			selCharges[0].checked = true;
    	}
    } else {	
	    for(i=0; i<document.forms[0].length; i++) {
	 	   if(document.forms[0].elements[i].type == "text" ){
	    	 document.forms[0].elements[i].focus();
		     break;
		   }  
		}
	}		   
	return;     
}

function checkCautionOther(theForm)
{
  var select = theForm.selectedCautions;
  var r = new Array();
  if(select.options != null)
  {
	  for (var i = 0; i < select.options.length; i++)
	  { 
	    if (select.options[i].selected)
	    {
	      r[r.length] = select.options[i].value;
	    }
	  }
	
	  for (var i = 0; i < r.length; i++)
	  {
	   if (r[i] == "OT")
	   {
	    var result = checkTextArea(theForm);
	    if (result == false)
	    	{
	    		theForm.cautionComments.focus();
	    		return false;
	    	}
	   }
	  }
  }
  return true;
}
 
function checkTextArea(theForm)
{
  var comments = Trim(theForm.cautionComments.value);
  if(comments == "")
  {
    alert('Caution Comment required when OTHER caution is selected.');
   	return false;
  } 
  return true;
}

function validateJuvenileFields(thisForm)
{	
	var msg = "";
    var SSN1 = Trim(thisForm.SSN1.value);
	var SSN2 = Trim(thisForm.SSN2.value);
	var SSN3 = Trim(thisForm.SSN3.value);
	var ssn1Regex = /^(?!000)([0-9]{3}$)/
	var ssn2Regex = /^([0-9]{2}$)/
	var ssn3Regex = /^([0-9]{4}$)/
//alert('SSN1(' + SSN1.length + ')\nSSN2(' + SSN2.length + ')\nSSN3(' + SSN3.length + ')');
	if (SSN1.length > 0 || SSN2.length > 0 || SSN3.length > 0)
	{
		if(ssn1Regex.test(SSN1) == false)
		{
			msg += "First 3 digits of SSN must be numeric and not equal 000.\n";
			thisForm.SSN1.focus();
		}
		if(ssn2Regex.test(SSN2) == false)
		{
			if (msg == "")
			{
				thisForm.SSN2.focus();
			}
			msg += "Second 2 digits of SSN must be numeric.\n";
		}
		if(ssn3Regex.test(SSN3) == false)
		{
			if (msg == "")
			{
				thisForm.SSN3.focus();
			}		
			msg += "Last 4 digits of SSN must be numeric.\n";
		}				
	}

	var feetRegex =  /^\d{1}$/
	var inchRegex =  /^\d{1,2}$/
		
	var heightFeet = Trim(thisForm.heightFeet.value);
	var heightInches = Trim(thisForm.heightInch.value);
	
	if(heightFeet != ""){
		if(feetRegex.test(heightFeet) == false)
		{
			if (msg == "")
			{
				thisForm.heightFeet.focus();
			}	
			msg += "Height feet must be an integer.";
		} else if (heightFeet < 3 || heightFeet > 8)
			{
				if (msg == "")
				{
					thisForm.heightFeet.focus();
				}	
				msg += "Height in feet is not in the range 3 through 8.\n";
			}	
	}	
	if(heightInches != ""){
		if(inchRegex.test(heightInches) == false){
			if (msg == "")
			{
				thisForm.heightInch.focus();
			}	
			msg += "Height inches must be an integer.";
		} else if (heightInches > 11){
			if (msg == "")
				{
					thisForm.heightInch.focus();
				}	
				msg += "Height in inches is not in the range 0 through 11.\n";		
		} else if (heightFeet == ""){
			if (msg == "")
				{
					thisForm.heightFeet.focus();
				}	
				msg += "Height feet is required when inches valued is entered.\n";		
		}
	}
	


	var weight = Trim(thisForm.weight.value);
	thisForm.weight.value = weight;
	
	var weightRegex =  /^\d{1,3}$/
	
	if(weight != "")
	{
		if(weightRegex.test(weight) == false)
		{
			if (msg == "")
			{
				thisForm.weight.focus();
			}	
			msg += "Weight must be an integer.\n";
		}
		else if (weight < 50 || weight > 499){
			if (msg == "")
			{
				thisForm.weight.focus();
			}	
			msg += "Weight is not in the range 50 through 499.\n";
		} else {
			thisForm.weight.value = parseFloat(weight);
		}
	}
	
	if (msg == "")
	{
		return true;
	}
	alert(msg);
	return false;
}

function checkBoxEdits(theForm) 
{
   var otherCautionSelected = false;
   var focusSet = false;
   var msg = "";
   var cautionValue = "";
   for(i=0; i<theForm.length; i++) {
	  if(theForm[i].type == "checkbox" ){
// if cautions checkbox for "OTHER" selected, comments required	
         if(theForm[i].name == "selectedCautions")
         { 
            cautionValue = theForm[i].value.toUpperCase();
	        if(cautionValue == "OT"){ 
	    	   if(theForm[i].checked){
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
	    if (!focusSet){
	      theForm.cautionComments.focus();
	      focusSet = true; 
         }
	  }
   } 
   if (msg != ""){
	 	alert(msg);
	   	return false;
	}
   
   theForm.cautionComments.value = Trim(theForm.cautionComments.value);
   
   return true;
}
function checkHeightWeightInputs(){
	var feet = document.getElementsByName("heightFeet");
	var inches = document.getElementsByName("heightInch");	
	var wt = document.getElementsByName("weight");	
	if (feet[0].value == 0 && inches[0].value == 0){
		feet[0].value = "";
		inches[0].value = "";
	}  
	if (wt[0].value == 0){
		wt[0].value = "";
	} 
	return;
}
</script>
