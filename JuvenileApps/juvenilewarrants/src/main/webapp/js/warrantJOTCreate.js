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

function clearDeptName()
{
	document.getElementById("deptNameTd").innerHTML="";
}

/* this function is called from the warrant create page - warrantJOTCreate.jsp */
function validateLawEnforcementInfo(theForm)
{
   var msg = "";
   var focusSet = false;
   
   var userId = theForm.elements["userId"].value;
   var officerId = theForm.elements["officerId"].value;
 
	if(document.getElementById('byOfficerId').className == 'hidden' && document.getElementById('byUserId').className == 'hidden')
	{
		
		alert("A Search By option needs to be selected.\n"); 
	    theForm.search.focus();		
		return false;
	}

	if(document.getElementById('byUserId').className != 'hidden')
	{
		if(userId == "")
		{
			if (focusSet == false){
		      focusSet = true;
		     theForm.elements["userId"].focus();
		    }
		   msg += "User ID is required.\n";
		}  
	    if(userId != "")
	    {
	   
	   	   if (userId.length < 5)
	   	   {
	       		msg += "User ID must be 5 alphanumeric characters long.\n";
	       		theForm.elements["userId"].focus();
	       		focusSet = true;
	       } else if(validateAlphanumericField(userId) == false)
	       {
	       		msg += "User ID must be 5  alphanumeric characters.\n";
	       		theForm.elements["userId"].focus();
	       		focusSet = true;
	       } 
		}
	}      
	if(document.getElementById('byOfficerId').className!= 'hidden')
	{ 
		if(officerId == "")
		{
			if (focusSet == false){
		      	focusSet = true;
		     	theForm.officerId.focus();
		    }
		   msg += "Officer ID is required.\n";
		}  
	   	if(officerId != "")
	  	{
	  		if (theForm.officerIdTypeId.selectedIndex == 0)
	  		{
	      		if (focusSet == false){
		      		focusSet = true;
		     		theForm.officerIdTypeId.focus();
		  		}
		  		msg += "Officer ID Type is required.\n";
			}
			if (theForm.officerIdTypeId.value == "B")
			{
				if (validateAlphanumericField(officerId) == false)
				{
	      			if (focusSet == false){
		      			focusSet = true;
		     			theForm.officerId.focus();
		  			}				
					msg += "Officer ID Number must be alphanumeric.\n";
				}			
			}
		}
	}
	
	if(document.getElementById("deptNameTd").innerHTML=="")
	{
		msg += "Officer Department Name is required.\n";
	}
    theForm.affidavitStatement.value = Trim(theForm.affidavitStatement.value);
		
   	if (theForm.affidavitStatement.value == ""){
      	if (focusSet == false){
		     focusSet = true;
		     theForm.affidavitStatement.focus();
	   	}
      msg += "Affidavit Statement is required.\n";
   	}
   	
    if (msg != "")
    {
		alert(msg);
	 	return false;
	 } 
	 return true;
}

function validateAlphanumericField(checkStr)
{  
	var regex = /^[a-zA-Z0-9]+$/;
	
	return regex.test(checkStr);
}

function validatePhoneRequired(areaCode, prefix, fourDigit)
{
	var result = true;
	if(areaCode == "" && prefix == "" && fourDigit == "") 
	{
		result = false;
	}
	return result;
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

function validateEmail(email)
{
	var result = true;
	if(email != null && email != "")
	{
		if(email.length < 3)
		{
			result = false;
		} else 
		{
			var atIndex = email.indexOf('@');
			if(atIndex == -1)
			{
				result = false;
			} else if(email.length == atIndex+1)
			{
				result = false;
			} else if(email.indexOf('@',atIndex+1) != -1)
			{
				result = false;
			} 
		}
	}
	return result;
}

function validateOfficerName( officername )
{ 
	var regex = /^[a-zA-Z0-9 '-.]*$/;
	
	return regex.test( officername );
}

/* this function called from Create Officer page - warrantLawEnforcementInfo.jsp */
function validateOfficerInformation(theForm) 
{
    var msg = "";
	var offBadgeNum = document.getElementsByName("officerBadgeNumber");
	var offOtherIdNum = document.getElementsByName("officerOtherIdNumber");
	var lastName = document.getElementsByName("officerName.lastName");
	var firstName = document.getElementsByName("officerName.firstName");	
	var officerBadge = "";
	var officerId = "";
	var temp = "";
// input Badge Number and Other Id Number fields can be either text or hidden field 
    if(offOtherIdNum.length > 0)
    {
    	if (offOtherIdNum[0].type == 'text') 
    	{    
    		officerId = Trim(offOtherIdNum[0].value);
    		offOtherIdNum[0].value = officerId;
    	} else {
   	   		officerId = offOtherIdNum[0].value;
    	}	
    }
    
    if(offBadgeNum.length > 0)
    {
    	if (offBadgeNum[0].type == 'text') 
    	{
	    	officerBadge = Trim(offBadgeNum[0].value);
	    	offBadgeNum[0].value = officerBadge;
	    } else {
	    	officerBadge = offBadgeNum[0].value;	    
	    }	
    }
//alert(officerId + "\n" + officerBadge);
  	if((officerBadge == null || officerBadge == "") && (officerId == null || officerId == ""))
  	{
  		msg += "Either Officer Badge Number or Officer Other ID Number must be entered.\n";
  		if (offOtherIdNum[0].type == 'text') 
  		{
			offOtherIdNum[0].focus();
  		} else {
	  		offBadgeNum[0].focus();
  		}
  	}

	if (officerId > "")
	{
		if (offOtherIdNum[0].type == 'text')  
		{
//alert("officerId...." + validateAlphanumericField(officerId));			
			if (validateAlphanumericField(officerId) == false)
			{
				msg += "Other ID Number must be alphanumeric.\n";
				offOtherIdNum[0].focus();
			}
		}
	}	
	if (officerBadge > "")
	{
		if (offBadgeNum[0].type == 'text') 
		{
//alert("officerBadge..." + validateAlphanumericField(officerBadge));		
			if (validateAlphanumericField(officerBadge) == false)
			{
				if (msg == "")
				{
					offBadgeNum[0].focus();
				}
				msg += "Badge Number must be alphanumeric.\n";
			}
		}
	}
    if (lastName.length > 0)
    {	
    	temp = Trim(lastName[0].value);
    	lastName[0].value = temp;
		if (lastName[0].value == "")
		{
			if (msg == "")
			{
				lastName[0].focus();
			}
			msg += "Last Name is required.\n";		
		}   	
    }
    if (firstName.length > 0)
    {	
    	temp = Trim(firstName[0].value);
    	firstName[0].value = temp;
		if (firstName[0].value == "")
		{
			if (msg == "")
			{
				firstName[0].focus();
			}
			msg += "First Name is required.\n";		
		}   	
    }

	var area = Trim(document.getElementsByName("workPhone.areaCode")[0].value);	
	var prefix = Trim(document.getElementsByName("workPhone.prefix")[0].value);
	var fourDigit = Trim(document.getElementsByName("workPhone.4Digit")[0].value);
	
	if (area == "" && prefix == "" && fourDigit == "")
	{
		if (msg == "")
		{
			theForm.elements["workPhone.areaCode"].focus();	
		}
		msg += "Work Phone is required.\n";		
	}	
	if(area != "" || prefix != "" || fourDigit != "")
	{
		areaRegex = /^(?!000)\d{3}$/
		prefixRegex = /^\d{3}$/
		fourDigitRegex = /^\d{4}$/
				
		if(areaRegex.test(area) == false)
		{
			if (msg == "")
			{
				theForm.elements["workPhone.areaCode"].focus();	
			}		
			msg += "Phone Number Area Code must be a non-zero integer of 3 digits.\n"
		}
		if(prefixRegex.test(prefix) == false)
		{
			if (msg == "")
			{
				theForm.elements["workPhone.prefix"].focus();				
			}		
			msg += "Phone Number Prefix must be an integer of 3 digits.\n"
		}
		if(fourDigitRegex.test(fourDigit) == false)
		{
			if (msg == "")
			{
				theForm.elements["workPhone.4Digit"].focus();				
			}	
			msg += "Phone Number Suffix must be an integer of 4 digits.\n"
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
   var chargeSelected = false;
   var otherCautionSelected = false;
   var focusSet = false;
   var msg = "";
   var cautionValue = "";
   for(i=0; i<theForm.length; i++) {
	  if(theForm[i].type == "checkbox" ){
// check for charge selection 	  
	     if(theForm[i].name == "selectedCharges"){ 
 	    	if(theForm[i].checked){
	    	   chargeSelected = true;
	    	}
	     }
	     
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

   if (!chargeSelected)
   {	
      msg += "At least one charge must be selected.\n";
      document.getElementsByName("selectedCharges")[0].focus();
      focusSet = true;
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

function validateDOBSource(warrantType,thisForm){
	
	if (warrantType != "dta") {
		return true;
	}
			
	if (thisForm.dateOfBirthSource.value =='')
	{
		alert("Please enter Age verified by");
		thisForm.dateOfBirthSource.focus();
		return false;
	}		
	return true;
}

function validateJuvenileFields(thisForm)
{

	var msg = "";
	var dobSource = document.getElementsByName("dateOfBirthSource");
	if (dobSource.length > 0){
		dobSource[0].value = Trim(dobSource[0].value);
		if (dobSource[0].value == "")
		{
			msg += "Age Verified By is required.\n";
			thisForm.dateOfBirthSource.focus();
		}
	}

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
			if (msg == "")
			{
				thisForm.SSN1.focus();
			}
			msg += "First 3 digits of SSN must be numeric and not equal 000.\n";
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
/* #43487 comment Juvenile Phone number out so it is not captured in the initiate warrants
	var area = Trim(document.getElementsByName("phoneNum.areaCode")[0].value);	
	var prefix = Trim(document.getElementsByName("phoneNum.prefix")[0].value);
	var fourDigit = Trim(document.getElementsByName("phoneNum.4Digit")[0].value);
	
	if(area != "" || prefix != "" || fourDigit != "")
	{
		areaRegex = /^(?!000)\d{3}$/
		prefixRegex = /^\d{3}$/
		fourDigitRegex = /^\d{4}$/
				
		if(areaRegex.test(area) == false)
		{
			if (msg == "")
			{
				setFieldFocus("phoneNum.areaCode");
			}		
			msg += "Phone Number Area Code must be a non-zero integer of 3 digits.\n"
		}
		if(prefixRegex.test(prefix) == false)
		{
			if (msg == "")
			{
				setFieldFocus("phoneNum.prefix");
			}		
			msg += "Phone Number Prefix must be an integer of 3 digits.\n"
		}
		if(fourDigitRegex.test(fourDigit) == false)
		{
			if (msg == "")
			{
				setFieldFocus("phoneNum.4Digit");
			}	
			msg += "Phone Number Suffix must be an integer of 4 digits.\n"
		}
	}
*/
	var feetRegex =  /^\d{1}$/
	var inchRegex =  /^\d{1,2}$/
	var heightFeet = Trim(thisForm.heightFeet.value);
	thisForm.heightFeet.value = heightFeet;

	var heightInches = Trim(thisForm.heightInch.value);		
	thisForm.heightInch.value = heightInches;
	
	if(heightFeet == "" && heightInches != "")
	{
		msg += "Height feet is required if inches are provided.";
		thisForm.heightFeet.focus();
	}
	
	if(heightInches == "" && heightFeet != "")
	{
		if (msg == "")
		{
			thisForm.heightInch.focus();
		}	
		msg += "Height inches is required if feet are provided.";
	}
	
	if(heightFeet != "" && feetRegex.test(heightFeet) == false && msg == "")
	{
		if (msg == "")
		{
			thisForm.heightFeet.focus();
		}	
		msg += "Height feet must be an integer.\n";
	}
	else if(heightInches != "" && inchRegex.test(heightInches) == false && msg == "")
	{
		if (msg == "")
		{
			thisForm.heightInch.focus();
		}			
		msg += "Height inches must be an integer.\n";
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
		else
		{
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

function evalSearch(el,clearDeptnameTd){
	if (el.search.options[ el.search.selectedIndex ].value == 'userSearch' ){
		show('byUserId', 1,"row");
		show('byOfficerId', 0,"row");
		show('officerDept',0,"row");
		if (el.officerAgencyId[0].value > ""){
			el.userId.focus();		
		}
		el.officerId.value="";
		el.officerIdTypeId.value="";
		el.officerAgencyId.value="";
		if(clearDeptnameTd){
			document.getElementsByName("deptNameTd")[0].innerText="";
			document.getElementsByName("officerAgencyId")[0].innerText="";
			document.getElementsByName("officerAgencyId")[1].innerText="";			
//			document.getElementsByName("affidavitStatement")[0].innerText="";
		}
	}
	else if (el.search.options[ el.search.selectedIndex ].value == 'officerSearch'){
		show('byOfficerId', 1,"row");	
		show('byUserId', 0,"row");
		show('officerDept',1,"row");	
		el.userId.value="";
		if (el.officerAgencyName.value > ""){
			el.officerId.focus();
		}		
		if(clearDeptnameTd){
			document.getElementsByName("deptNameTd")[0].innerText="";
			document.getElementsByName("officerAgencyId")[0].innerText="";
			document.getElementsByName("officerAgencyId")[1].innerText="";			
//			document.getElementsByName("affidavitStatement")[0].innerText="";
		}
	}
	else{
		show('byOfficerId', 0,"row");	
		show('byUserId', 0,"row");	
		show('officerDept',0,"row");
		el.officerId.value="";
		el.officerIdTypeId.value="";
		el.officerAgencyId.value="";
		el.userId.value="";
		if(clearDeptnameTd){
			document.getElementsByName("deptNameTd")[0].innerText="";
			document.getElementsByName("officerAgencyId")[0].innerText="";
			document.getElementsByName("officerAgencyId")[0].innerText="";				
//			document.getElementsByName("affidavitStatement")[0].innerText="";
		}
	}

// display caution comments if "OTHER" is selected	
	var cautions = document.getElementsByName("selectedCautions");
	for (x=0; x<cautions.length; x++){
		if (cautions[x].value == "OT" &&  cautions[x].checked == true){
		    show('otherCaution', 1,"row");
	    	show('otherCaution2', 1,"row");
		}
	}
}

function trim(textbox) {
  if (textbox) {
    while (textbox.value.substring(0,1) == " ") {
      textbox.value = textbox.value.substring(1);
    }
  }
}
function loadDepartment(file, actionVal){
	
	var theForm = document.forms[0];
	
	if(theForm.search.options[ document.forms[0].search.selectedIndex ].value == 'userSearch' || document.getElementById('byUserId').className == 'visibleTR')
	{
		var userId = Trim(theForm.userId.value);
		if(userId =="")
		{
			alert("User ID must be entered to find department.");
			theForm.userId.focus();
			return false;
		}
		if(userId.length < 5)
		{
			alert("User ID must be at least 5 characters to find department.");
	    	theForm.userId.focus();
			return false;
		}		
	}
	else if(theForm.search.options[ document.forms[0].search.selectedIndex ].value == 'officerSearch' || document.getElementById('byOfficerId').className == 'visibleTR')
	{
		trim(theForm.officerId);
		if(theForm.officerId.value =="")
		{
			alert("Officer ID must be entered to find department.");
			theForm.officerId.focus();
			return false;
		}
		if(theForm.officerIdType.value =="")
		{
			alert("Officer ID Type must be selected to find department.");
	       theForm.officerIdType.focus();
	       return false;
		}
	}	
	var myURL=file;
	//alert(myURL);
	load(myURL,top.opener);
	window.close();
}
function load(file,target) {
	window.location.href = file;
}
function validateDepartment() {
	var offAgencyIds = document.getElementsByName("officerAgencyId");
	var offAgencyId = Trim(offAgencyIds[1].value);
// set [0] value which is used to do actual department search	
	offAgencyIds[0].value = offAgencyId;
	if(offAgencyId=="")
	{
		alert("Department code has to be provided for Validation.");
		offAgencyIds[1].focus();		    
	    return false;
	}
	if(offAgencyId.length < 3)
	{
		alert("Department code must be 3 characters for Validation.");
		offAgencyIds[1].focus();		    
	    return false;
	}	
	return true;
}
function showOtherCaution(val)
{
 if(val){
    show('otherCaution', 1,"row");
    show('otherCaution2', 1,"row");
 }else{
 	show('otherCaution', 0,"row");
 	show('otherCaution2', 0,"row");
 }
}

</script>