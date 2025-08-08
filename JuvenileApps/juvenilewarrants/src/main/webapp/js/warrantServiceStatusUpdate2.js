<!-- warrantServiceStatusUpdate2.js -->

function validateFormFields(theForm) 
{
	var msg = "";

	var badAmount = false;
	var badDate = false;
	var curHour = 0;
	var curMin = 0;
	var curTime = "";
	var serviceDateCurrentDate = false;
	var dateValue = "";
	var result = "";
   
	var hourRegex = /^\d{2}$/;
	var minuteRegex = /^\d{2}$/;
	var mileageRegex = /^[0-9]{1,4}$/;	
	var currencyRegex = /^([1-9]{1}([0-9]{0,3})(\.[0-9]{2})?|0(\.[0-9]{2})?)$/;
   
	theForm.selectedBadAddress.value = "";
	
	if (theForm.costMileage.value > "")
	{
		if (mileageRegex.test(theForm.costMileage.value) == false)
		{
			msg += "Mileage must be a number.\n";
			theForm.costMileage.focus();
		}    
	}

	if (theForm.costAirFare.value > "")
	{
		if (currencyRegex.test(theForm.costAirFare.value) == false)
		{   
			
			if (msg == "")
			{
				theForm.costAirFare.focus();
			}  
			msg += "Air Fare amount is invalid.\n";
		}	
		else if(theForm.costAirFare.value >= 10000)
		{
			if (msg == "")
			{
				theForm.costAirFare.focus();
			}
			msg += "Air Fare amount must be less than $10000.\n";
		}
	}

	if (theForm.costPerDiem.value > "")
	{
		if (currencyRegex.test(theForm.costPerDiem.value) == false)
		{
			if (msg == "")
			{           
				theForm.costPerDiem.focus();
			}
			msg += "Per Diem amount is invalid.\n";
		} 
		else if (theForm.costPerDiem.value >= 10000)
		{ 
			if (msg == "")
			{
				theForm.costPerDiem.focus();
			}
			msg += "Per Diem amount must be less than $10000.\n";		
		}	
	}

   if (theForm.currentWarrantServiceStatus.selectedIndex == 0)
   {
      if (msg == "")
      {
	      theForm.currentWarrantServiceStatus.focus();
	  }
	  msg += "Warrant Service Status is required.\n";	  
	}
	
	if (theForm.currentServiceDateString.value == "" || theForm.currentServiceDateString.value == null)
	{
		if (msg == "")
		{
			theForm.currentServiceDateString.focus();
		}       
		msg += "Service Date is required.\n";
	}
// tried using checkdate in app.js did not work
// tried RegExp but could not get it set up correctly in time
// this date format and validation function should be replaced - cws   
   if (theForm.currentServiceDateString.value > "")
   {
       dateValue = theForm.currentServiceDateString.value;
       //badDate = checkServiceDate(dateValue);
       var invalidDateFormat = checkDateFormat(dateValue);
       if(invalidDateFormat)
       {
           if (msg == "")
           {
       	       theForm.currentServiceDateString.focus();
           }    
       	   msg += "Service date is invalid.  The valid format is MM/DD/YYYY.\n";           
       }
       else {
	       result = checkValidDate(dateValue);
    	   if (result != "")
    	   {
	           if (msg == "")
	           {
        	       theForm.currentServiceDateString.focus();
	           }    
        	   msg += result;
        	   badDate = true;
     	   }
    	} 
    	
   	}    
   // set indicator if service date is current date for hour and minute edits
   if (!badDate)
   {
      var curDate = new Date();
      var curYYYY = curDate.getFullYear();
      var curMM = curDate.getMonth() + 1;
      var curDD = curDate.getDate();
      var mm = dateValue.substring(0,2);
      var dd = dateValue.substring(3,5);
      var yyyy = dateValue.substring(6,10);
      curHour = curDate.getHours().toString();
      curMin = curDate.getMinutes().toString();
      if (curHour < 10){	
      	curHour = "0" + curHour;  
      }
      if (curMin < 10){	
      	curMin = "0" + curMin;  
      }

      curTime = curHour + ":" + curMin;
      if (curYYYY == yyyy && curMM == mm && curDD == dd)
      {
         serviceDateCurrentDate = true; 
      } 
   }
 if (theForm.currentServiceTime.value == ""){
      if (msg == ""){
		     theForm.currentServiceTime.focus();
	   }
      msg += "Service Time is required.\n";
   } 

 if (theForm.currentServiceTime.value > ""){
       if(isValidTime(theForm.currentServiceTime.value) == false)
       {
          if (msg == ""){
		     theForm.currentServiceTime.focus();
	   	  }
      	  msg += "Service Time value is invalid or not in hh:mm format.\n";
       } else if (serviceDateCurrentDate && curTime < theForm.currentServiceTime.value)
       		{
   			if (msg == "")
            {
   		      theForm.currentServiceTime.focus();
	        }        
           	msg += "Service Time can not be future time.\n";
       }
   }
	
// service attempt comments required of status is unsuccessful	
    var currentWarrantServiceStatus = ""
	if (theForm.currentWarrantServiceStatus.selectedIndex > 0)
	{
	   for (var i = 0; i <theForm.currentWarrantServiceStatus.length; i++)
	   {
	       if (theForm.currentWarrantServiceStatus.options[i].selected == true)
	       {
	           currentWarrantServiceStatus = theForm.currentWarrantServiceStatus.options[i].value.toUpperCase();
	           if (currentWarrantServiceStatus == "U")
	           {
	               if (Trim(theForm.currentServiceAttemptComments.value) == "")
	               {
                       if (msg == "")
                       {
                           theForm.currentServiceAttemptComments.focus();
	                   }
	                   msg += "A Service Attempt Comment is required.\n";
	               } 
	           }
	       }   
	   } 
    }
    
// Either existing associate address or new addres must be selected (radio button)
// if addressId value equal newAddress, user selected button to enter new address 
    addressSelected = false;
    addressId = "";
    //selectedAssociateAddressId = "";
    firstRadioElement = 0;
    for (var r = 0; r <theForm.length; r++){
       if (theForm.elements[r].type == "radio" && theForm.elements[r].name == "selectedAssociateAddressId"){
          if (firstRadioElement == 0){
             firstRadioElement = r;
          } 
          if (theForm.elements[r].checked == true){
             addressSelected = true;
             addressId = theForm.elements[r].value;
          }  	
       } 
       
       if(theForm.elements[r].name === 'addressId' && theForm.addressId.checked && theForm.addressId.value === 'newAddress' && theForm.associateId.value === '1111111'){
			 
     	  	addressSelected = true;
     	  	addressId = theForm.addressId.value;
		}
       
    }
    if (!addressSelected  || addressId == "")
    {
       if (msg == ""){
	       theForm.elements[firstRadioElement].focus();
	   }
       msg += "Service Address Selection is required.\n";
    }
// if true, existing associate address selected        
    if (addressId != "newAddress" && addressId > ""){
       for (var r = 0; r <theForm.length; r++){
           if (theForm.elements[r].type == "checkbox" && theForm.elements[r].name == addressId){
               if (theForm.elements[r].checked == true){
                   theForm.selectedBadAddress.value = addressId;
                   break;
               } 
           } 
       }
// successful service attempt cannot be used when bad address selected       
       if (theForm.selectedBadAddress.value == addressId)
       {
           if (currentWarrantServiceStatus == "S")
           {
               if (msg == "")
               {
	               theForm.currentWarrantServiceStatus.focus();
	           }          
	           msg += "Warrant Service Status can not be SUCCESSFUL for bad address.\n";
           }
       }   
    }
// if true, new address values are required    
	if (addressId == "newAddress"){
		var strNumRegex = /^[A-Za-z0-9\/ ]*$/;
		var strNameRegex = /^[A-Za-z0-9\.\/\- ]*$/;
		var aptNumRegex = /^[A-Za-z0-9]*$/;
		var cityRegex = /^[A-Za-z\.\- ]*$/
		var zipCodeRegex = /^[0-9]{5}$/	
		var zipExtRegex = /^[0-9]{4}$/;
 		var streetNum = Trim(theForm.streetNum.value);
 		var streetName = Trim(theForm.streetName.value);
		var aptNum = Trim(theForm.apartmentNum.value);
		var city = Trim(theForm.city.value);
		var zipCode = Trim(theForm.zipCode.value);
		var zipCode2 = Trim(theForm.zipCode2.value);
		theForm.streetNum.value = streetNum;
		theForm.streetName.value = streetName;
		theForm.apartmentNum.value = aptNum;
		theForm.city.value = city;
		theForm.zipCode.value = zipCode;
		theForm.zipCode2.value = zipCode2;
							 
		if (theForm.streetNum.value == "" || theForm.streetNum.value == null )
		{
			if (msg == "")
			{
				theForm.streetNum.focus();
			}    
			msg += "Street Number is required.\n";
		}else {
			if(strNumRegex.test(theForm.streetNum.value) == false)
			{
				if (msg == "")
				{
					theForm.streetNum.focus()
				}
				msg += "Street Number must be alphanumeric value.\n";
			}
		}	
		if ( theForm.streetName.value == "" || theForm.streetName.value == null)
		{
			if (msg == "")
			{
				theForm.streetName.focus();
			}    
			msg += "Street Name is required.\n";
		}else if(strNameRegex.test(theForm.streetName.value) == false)
			{
				if (msg == ""){
					theForm.streetName.focus()
			}
			msg += "Street Name must be alphanumeric value.\n";
		}

		if (theForm.apartmentNum.value > "")
		{
			if(aptNumRegex.test(theForm.apartmentNum.value) == false)
			{
				if (msg == ""){
					theForm.apartmentNum.focus()
				}	
				msg += "Apartment Number must be alphanumeric value.\n";
			}
		}
		
		if (theForm.city.value == "" ||theForm.city.value == null)
		{
			if (msg == ""){
				theForm.city.focus();
			}    
			msg += "City is required.\n";
		} else if(cityRegex.test(theForm.city.value) == false)
			{
				if (msg == ""){
					theForm.city.focus()
				}	   
				msg += "City must be alphabetic value.\n";
			}
	   
		if (theForm.state.selectedIndex == 0)
		{
			if (msg == ""){
				theForm.state.focus();
			}
			msg += "State is required.\n";
		}
	   
	   
		if (zipCode == "" || zipCode == null)
		{
			if (msg == "")
			{
				theForm.zipCode.focus();
			}
			msg += "Zip Code is required.\n";
		}
		else if (zipCodeRegex.test(zipCode) == false)
			{
				if (msg == "")
				{
					theForm.zipCode.focus();
				}	       
				msg += "Zip Code must be 5 digit number.\n";
			}
		
		if (zipCode2 != "")
		{
			if (zipCode == "")
			{
				if (msg == "")
				{
					theForm.zipCode.focus();
				}	      
				msg += "Zip Code is required for extension entry.\n";
			}
			if (zipExtRegex.test(theForm.zipCode2.value) == false)
			{
				if (msg == "")
				{
					theForm.zipCode2.focus();
				}
				msg += "Zip Code extension must be 4 digit number.\n";
			}
		}   
    }   
    
// all edits complete, check if msg is still blank    
    if (msg == "")
    {
	    return true;
    }
	alert(msg);
	return false;
}

// all this logic could possibly be replaced by a RegExp
function checkDollarAmount(amount)
{
   var decimalIndex = amount.indexOf(".");
   if (decimalIndex < 0)
   {
      if (isNaN(amount) == true){
         return true;
      }else{
         return false;
      }
   }
   if (decimalIndex == 0){
      return true;
   }
   var dollar = amount.substring(0,decimalIndex);
   var cents = amount.substring(decimalIndex + 1, amount.length);
   if (isNaN(dollar) == true || isNaN(cents) == true || cents.length != 2){
      return true;
   }   
   return false; 
}

function checkDateFormat(dateField){
   if (dateField.length != 10){
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

}

function checkValidDate(dateField){
   if (dateField.length != 10){
      return "Invalid Service Date.\n";
   }        	   
   var mm = dateField.substring(0,2);
   var dd = dateField.substring(3,5);
   var yyyy = dateField.substring(6,10);

// check date to be current or previous date, cannot be future date
   var curDate = new Date();
  
   var curYear = curDate.getFullYear();
   var curMonth = curDate.getMonth() + 1;
   var curDay = curDate.getDate();
   var dateMsg = "Service Date cannot be a future date.\n";
   if (curYear < yyyy){
      return dateMsg;
   }
   if (curYear == yyyy){
      if (curMonth < mm ){
         return dateMsg;
      } 
      if (curMonth == mm){
          if (curDay < dd){
             return dateMsg;
          } 
      } 
   }
   if (yyyy < 2005){
   	dateMsg = "Service date cannot be prior to 01/01/2005. \n"; 
   	return dateMsg;	
   }
   dateMsg = "";
   return dateMsg;             
}

function checkDates(theForm)
{
	var dateRegex = /^\d{1,2}\/\d{1,2}\/\d{4}$/;
	var activationDate = theForm.activationDate.value;
	var issueDate = theForm.issueDate.value;	
	var serviceDate = theForm.currentServiceDateString.value;
	if (activationDate == null || activationDate == ""){
   		alert("Warrant Activation Date value is invalid or missing.");
  	  	return false;	
	}
	if (issueDate == null || issueDate == ""){
   		alert("Warrant Issue Date value is invalid or missing.");
  	  	return false;	
	}	

	if(dateRegex.test(activationDate) == false)
    {
   		alert("Warrant Activation Date value is invalid.");
  	  	return false;
   	}
	var activationMonth = activationDate.substring(0,2);
	var activationDateOfMonth = activationDate.substring(3,5);
	var activationYear = activationDate.substring(6,10);
	activationDate = activationYear + activationMonth + activationDateOfMonth ;    	
      
	if(dateRegex.test(issueDate) == false)
   	{
   		alert("Warrant Issue Date value is invalid.");
  	  	return false;
   	}
	var issueMonth = issueDate.substring(0,2);
	var issueDateOfMonth = issueDate.substring(3,5);
	var issueYear = issueDate.substring(6,10);
	issueDate = issueYear + issueMonth + issueDateOfMonth ;     	

    if(dateRegex.test(serviceDate) == false)
    {
      	alert("Service Date value is invalid.");
  	  	return false;
    }
	var flds = serviceDate.split("/");
	var serviceMonth = flds[0];
	var serviceDateOfMonth = flds[1];
	var serviceYear = flds[2];
	if (serviceMonth.length == 1){
		serviceMonth = '0' + serviceMonth;
	}
	if (serviceDateOfMonth.length == 1){
		serviceDateOfMonth = '0' + serviceDateOfMonth;
	}
	serviceDate = serviceYear + serviceMonth + serviceDateOfMonth ;
 	if (serviceDate < activationDate) 
	{
	    alert("Service Date can not be prior to Activation Date " + theForm.activationDate.value + ".");
	    theForm.currentServiceDateString.focus();
	    return false;
  	}
 	if (serviceDate < issueDate) 
	{
	    alert("Service Date can not be prior to Issue Date " + theForm.issueDate.value + ".");
	    theForm.currentServiceDateString.focus();
	    return false;
  	}
  	return true;
}

function compareActivationServiceDates(theForm){
    var date1=parseDate('<bean:write name="juvenileWarrantForm" property="currentServiceDateString"/>');
	var temp=theForm.currentServiceDateString.value;
	var date1='<bean:write name="juvenileWarrantForm" property="warrantActivationDateString"/>';
	var date2=temp;
	var chk=compareDates(date1,'<bean:message key="date.format.mmddyyyy"/>',date2,'<bean:message key="date.format.mmddyyyy"/>');
    if( chk==1){

	    alert("Service Date can not be prior to Activation Date " + theForm.warrantActivationDate.value + ".");
	    return false;

	}
	else{
		return true;

	}

}
function enableCounty(theSelect, countyId)
{
  var selectedState = theSelect.options[theSelect.selectedIndex].value;
  var theCountySelect = document.getElementsByName("county");  
 
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
function setStateCounty(){
// preset Create Address block state and county to Texas and Harris by default
	var stSelect = document.getElementsByName("state");
	var ctySelect = document.getElementsByName("county");	
	for (x=0; x<stSelect[0].length; x++){
		if (stSelect[0].options[x].value.toUpperCase() == "TX"){
			stSelect[0].options[x].selected = true;
			break;
		}
	}	
	for (x=0; x<ctySelect[0].length; x++){
		if (ctySelect[0].options[x].value == "101"){
			ctySelect[0].options[x].selected = true;
			break;
		}
	}
}	
function isTodaysDate(dateField){
   if (dateField.length != 10){
      return "Invalid Service Date.\n";
   }        	   
   var mm = dateField.substring(0,2);
   var dd = dateField.substring(3,5);
   var yyyy = dateField.substring(6,10);

// check date to be current or previous date, cannot be future date
   var curDate = new Date();
  
   var curYear = curDate.getFullYear();
   var curMonth = curDate.getMonth() + 1;
   var curDay = curDate.getDate();
   var dateMsg = "Date is not todays date.\n";
  
   if (curYear == yyyy){
      if (curMonth == mm){
          if (curDay == dd){
             return "";
          } 
      } 
   }
   
   return dateMsg;             
}
function isValidTime(value) 
{
	var re = /^\d{2}[:]\d{2}([:]\d{2})?$/;
	
	if (!re.test(value)) 
	{
		return false; 
	}

	var values = value.split(":");
	
	if ( (parseFloat(values[0]) == 24) && (parseFloat(values[0]) > 0) )
	{
		return false;
	} 
	else if ( (parseFloat(values[0]) < 0) || (parseFloat(values[0]) > 23) ) 
	{ 
		return false;
	}
 	else if ( (parseFloat(values[1]) < 0) || (parseFloat(values[1]) > 59) ) 
	{ 
		return false;
	}
	else if (values.length > 2)
	{
		if ( (parseFloat(values[2]) < 0) || (parseFloat(values[2]) > 59) ) 
		{
			return false;
		}
	}
	
	return true;
}

function clearExecutorFields(form)
{
/**	if(form.userId.value != "")
	{
		form.agencyId.value = "";
		form.officerId.value = "";
		form.officerIdTypeId.value = "";					
	} */
}
function textCount(field, fldName, maxlimit) 
{  
	if (field.value.length >= maxlimit) 
	{		 
	    alert(fldName +" has exceeded the max limit of " + maxlimit + " characters.");
	    field.value = field.value.substring(0,maxlimit);
	    return false;
  	} 
	return true;
}


