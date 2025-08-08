$(document).ready(function (){
	$("#btnNext").click(function()
	{
	var thisForm = document.forms[0];
	trim(thisForm["providerName"]);
	trim(thisForm["startDate"]);
	trim(thisForm["mailingAddress.streetNumber"]);
	trim(thisForm["mailingAddress.streetName"]);
	trim(thisForm["mailingAddress.city"]);
	trim(thisForm["mailingAddress.zipCode"]);
	trim(thisForm["billingAddress.streetNumber"]);
	trim(thisForm["billingAddress.streetName"]);
	trim(thisForm["billingAddress.city"]);
	trim(thisForm["billingAddress.zipCode"]);
	trim(thisForm["ftp"]);
	if(thisForm["providerName"].value == "")
	{
		 alert("Name is required to create Service Provider.");
	      thisForm["providerName"].focus();
	      return false;
	}
	
	if(thisForm["ftp"].value.length > 1000){
		alert("Notes field must be 1,000 characters or less");
	      thisForm["ftp"].focus();
	      return false;
	}
	
	if(thisForm["startDate"] != null)
	{
		if(thisForm["startDate"].value == "")
		{
			 alert("Start date is required to create Service Provider.");
		      thisForm["startDate"].focus();
		      return false;
		}
		if(thisForm["startDate"].value != "")
		{
			
			var currDate=new Date();
			var year=currDate.getYear();
			if(year < 1000)
				year += 1900;		  
			var today=new Date(year, currDate.getMonth(),currDate.getDate());
			var myDate=new Date(thisForm.startDate.value);
		
			if( myDate < today)
			{
			   alert("Start Date cannot be in the past.");
		       thisForm.startDate.focus();
		       return false;
			}
			
			   
					result = validateDate(thisForm["startDate"].value);
					
					if (result == false){
						alert("Start Date is invalid date or wrong date format.");
						thisForm["startDate"].focus();
						return false;
				
			   }	
		}
	}
	var inHouse=0;
	
	if(thisForm.isInHouse != null)
	{
		for(var i=0; i<thisForm.isInHouse.length;i++)
	    {  
	    	if( thisForm.isInHouse[i].checked)
	    		inHouse=1;
	    }
	    if(inHouse == 0)
	    {
	    	alert("InHouse indicator required to create Service Provider.");
	       thisForm.isInHouse[0].focus();
	       return false;
	    }
	}
	var numberRegExp=/^[0-9]*$/;	
	/*if(thisForm["maxYouthId"].value=="")
  	 {
  	 	alert("Maximum Youth value must be provided to create/update Service Provider.");
  	 	thisForm["maxYouthId"].focus();
  	 	return false;
  	 }else 
 	{
 		if (numberRegExp.test(thisForm.maxYouthId.value.trim(),numberRegExp) == false) {
 			alert("Maximum Youth must be numeric.\n");
 			thisForm.maxYouthId.focus();	
 			return false;
 		}
 		
 	}*/ //commented for BUG 179328 and added the code below
	if(! thisForm["maxYouthId"].value=="")
		{
		if (numberRegExp.test(thisForm.maxYouthId.value.trim(),numberRegExp) == false) 
			{
			alert("Maximum Youth must be numeric.\n");
			thisForm.maxYouthId.focus();	
			return false;
			}
		} //BUG 179328 ENDS
	if(typeof thisForm["adminContactId"]!="undefined" && thisForm["adminContactId"].value != null)
	{
	
	    if(thisForm["adminContactId"].value=="")
	   	 {
	   	 	alert("Admin User ID must be selected to create Service Provider.");
	   	 	thisForm["adminContactId"].focus();
	   	 	return false;
	   	 }
	}
	
	//validate email
	var spEmail = thisForm["email"].value;
	if(spEmail){
		
		var result = validateSPEmail(spEmail);
		if(result){
			return true;
		} else {
			alert("Please enter a valid email");
			thisForm["email"].focus();
			return false;
		}
	}
	
	/*
	 * if(thisForm["contactUserId"] != null) {
	 * if(thisForm["contactUserId"].selectedIndex <= 0) { alert("Contact User ID
	 * must be selected to create Service Provider.");
	 * thisForm["contactUserId"].focus(); return false; } }
	 */
	
	if(validateServiceProviderForm(thisForm))
	{		
		if(thisForm["phoneNum.areaCode"].value =="" && thisForm["phoneNum.prefix"].value=="" && thisForm["phoneNum.last4Digit"].value=="" && thisForm["phoneNum.ext"].value=="")
		{
		 	alert("All of Phone number must be entered if there is partial entry.");
	      	thisForm["phoneNum.areaCode"].focus();
	     	return false;
		}
		 if (thisForm["fax.areaCode"].value != "" && thisForm["fax.prefix"].value == "") {
	      alert("All of Fax number must be entered if there is partial entry.");
	      thisForm["fax.areaCode"].focus();
	      return false;
	   }
	   if (thisForm["fax.areaCode"].value != "" && thisForm["fax.last4Digit"].value == "") {
	      alert("All of Fax number must be entered if there is partial entry.");
	      thisForm["fax.last4Digit"].focus();
	      return false;
	   }
	   if (thisForm["fax.prefix"].value != "" && thisForm["fax.areaCode"].value == "") {
	      alert("All of Fax number must be entered if there is partial entry.");
	      thisForm["fax.areaCode"].focus();
	      return false;
	   }
	   if (thisForm["fax.prefix"].value != "" && thisForm["fax.last4Digit"].value == "") {
	      alert("All of Fax number must be entered if there is partial entry.");
	      thisForm["fax.last4Digit"].focus();
	      return false;
	   }
	   if (thisForm["fax.last4Digit"].value != "" && thisForm["fax.areaCode"].value == "") {
	      alert("All of Fax number must be entered if there is partial entry.");
	      thisForm["fax.areaCode"].focus();
	      return false;
	   }
	   if (thisForm["fax.last4Digit"].value != "" && thisForm["fax.prefix"].value == "") {
	      alert("All of Fax number must be entered if there is partial entry.");
	      thisForm["fax.prefix"].focus();
	      return false;
	   }   
		if(thisForm["mailingAddress.additionalZipCode"].value != "" && thisForm["mailingAddress.zipCode"].value == "")
	   	 {
	   	 	alert("Invalid zip code : Partial entry of zip code.");
	   	 	thisForm["mailingAddress.zipCode"].focus();
	   	 	return false;
	   	 }
	   	 if(thisForm["billingAddress.additionalZipCode"].value != "" && thisForm["billingAddress.zipCode"].value == "")
	   	 {
	   	 	alert("Invalid zip code : Additional zip code allowed only if zip code exists.");
	   	 	thisForm["billingAddress.zipCode"].focus();
	   	 	return false;
	   	 }		
	   
		
	}
	else
		return false;
	return true;
	
	});
	function trim(textbox) {
		  if (textbox) {
		    while (textbox.value.substring(0,1) == " ") {
		      textbox.value = textbox.value.substring(1);
		    }
		  }
		}
		function validateDate(fldValue){
			
			var regDate = /^\d{1,2}\/\d{1,2}\/\d{4}$/;;
			var result = regDate.test(fldValue,regDate);	
			if (result == false){
				return false;
			}  
			
			var dValues = fldValue.split('/');
			var mon = dValues[0];
			var day = dValues[1];
			var leapYear = 0;
			if (mon > 12 || mon == 0){
				return false;
			}
			
			if (day > 31 || day == 0){
				return false;
			}	
			if (mon == 4 || mon == 6 || mon == 9 || mon == 11){
				if (day > 30){
					return false;
				}	
			}
			if (mon == 2){
				leapYear = dValues[2] %4;
				if (leapYear == 0 && day > 29){
					return false;
				}
				if (leapYear > 0  && day > 28){
					return false;
				}	
			}
			return true;
		}
		
		function validateSPEmail(email){
			var regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
			
			if(regex.test(email)){
				return true;
			} else {
				return false;
			}			
		}

});


