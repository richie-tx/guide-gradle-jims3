<!-- warrantReleaseToJP.js -->
<script language=javascript>

function clearDeptName()
{
	document.getElementById("deptName").innerHTML="";
}

function validateAlphanumericField(checkStr)
{  
	var regex = /^[0-9A-Za-z]+$/;
	
	return regex.test(checkStr);
}
function validateFindDept(theForm) 
{
	trim(theForm.transferOfficerLogonId);
	if(theForm.transferOfficerLogonId.value =="")
	{
		alert("User ID must be entered to find department.");
       theForm.transferOfficerLogonId.focus();
       return false;
	}
}
function trim(textbox) {
  if (textbox) {
    while (textbox.value.substring(0,1) == " ") {
      textbox.value = textbox.value.substring(1);
    }
  }
}
function validateFields(theForm) 
{
   var msg = "";
   var focusSet = false;
   
   var transferOfficerUserId = theForm.elements["transferOfficerLogonId"].value;
   var transferOfficerId = theForm.transferOfficerId.value;
 
	if(document.getElementById('byOfficerId').className == 'hidden' && document.getElementById('byUserId').className == 'hidden')
	{
		
		alert("A Search By option needs to be selected.\n"); 
		return false;
	}

	if(document.getElementById('byUserId').className != 'hidden')
	{
		if(transferOfficerUserId == "")
		{
			if (focusSet == false){
		      focusSet = true;
		     theForm.elements["transferOfficerLogonId"].focus();
		    }
		   msg += "User ID is required.\n";
		}  
	    if(transferOfficerUserId != "")
	    {
	   
	   	   if (transferOfficerUserId.length < 5)
	   	   {
	       		msg += "User ID must be 5 alphacharacters long.\n";
	       		theForm.elements["transferOfficerLogonId"].focus();
	       		focusSet = true;
	       } else if(validateAlphanumericField(transferOfficerUserId) == false)
	       {
	       		msg += "User ID must be 5 alphacharacters.\n";
	       		theForm.elements["transferOfficerLogonId"].focus();
	       		focusSet = true;
	       } 
		}
	}      
	if(document.getElementById('byOfficerId').className!= 'hidden')
	{ 
		if(transferOfficerId == "")
		{
			if (focusSet == false){
		      focusSet = true;
		     theForm.transferOfficerId.focus();
		    }
		   msg += "Officer ID is required.\n";
		}  
	   	if(transferOfficerId != "")
	  	{
	  	
	  		if (theForm.transferOfficerIdType.selectedIndex == 0)
	  		{
	      		if (focusSet == false){
		      		focusSet = true;
		     		theForm.transferOfficerIdType.focus();
		  		}
		  		msg += "Officer ID Type is required.\n";
			}
		}
	}   	

	
  
   if (theForm.transferCustodyDateString.value == ""){
      if (focusSet == false){
	      	focusSet = true;
    	  	theForm.transferCustodyDateString.focus();
    	  }
      msg += "Transfer Date is required.\n";
   } else
   {
	  var dateRegex = /^\d{2}\/\d{2}\/\d{4}$/;
      if(dateRegex.test(theForm.transferCustodyDateString.value) == false)
      {
      	msg += "Transfer Date value is invalid.\n";
  	  	theForm.transferCustodyDateString.focus();
      }
      
   }
   
   if (theForm.transferCustodyTimeString.value == ""){
      if (focusSet == false){
		     focusSet = true;
		     theForm.transferCustodyTimeString.focus();
	   }
      msg += "Transfer Time is required.\n";
   } else 
   {
      //var timeRegex = /^([0-1][0-9]|[2][0-3])(:[0-5][0-9])?$/
      //if(timeRegex.test(theForm.transferCustodyTimeString.value) == false)
      if(isValidTime(theForm.transferCustodyTimeString.value) == false)
      {
      	msg += "Transfer Time value is invalid.\n";
      	theForm.transferCustodyTimeString.focus();
      }
   }
   
   if (theForm.transferLocationId.selectedIndex == 0){
        if (focusSet == false){
	      focusSet = true;
	      theForm.transferLocationId.focus();
	    }
	   msg += "Transfer Location is required.\n";
	}
	
	if(document.getElementById("deptName").innerHTML=="")
	{
		msg += "Officer Department Name is required.\n";
	}
	
	
	
    if (msg != "")
    {
		alert(msg);
	 	return false;
	 } 
	 return true;
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

function checkReleaseDate(thisForm)
{
	var dateRegex = /^\d{1,2}\/\d{1,2}\/\d{4}$/;
	
	var releaseDecisionDate = thisForm.releaseDate.value;
	var transferDate = thisForm.transferCustodyDateString.value;
	
	if (dateRegex.test(transferDate ) == true)
	{ 
	 	var transMM = transferDate.substring(0,2);
	   	var transDD = transferDate.substring(3,5);
  		var transYYYY = transferDate.substring(6,10);
	 	var relMM = releaseDecisionDate.substring(0,2);
	   	var relDD = releaseDecisionDate.substring(3,5);
  		var relYYYY = releaseDecisionDate.substring(6,10);
  		var transDateYYYYMMDD = transYYYY + transMM + transDD;
  		var relDateYYYYMMDD = relYYYY + relMM + relDD;

	 	if (transDateYYYYMMDD < relDateYYYYMMDD) 
 		{
	      alert("Transfer Date should not be prior to Release Date.");
    	  thisForm.transferCustodyDateString.focus();
	      return false;
  		}

	  	var validTime = new String(thisForm.transferCustodyTimeString.value);
  		var the_char=validTime.charAt(2);
	  	if(the_char	!= ":" ) {
  			alert(" Must separate time with a Colon." + "\n" + " Ex: hh:mm");
  			thisForm.transferCustodyTimeString.focus();
	  		return false;
  		}
	  	var char_one = validTime.charAt(0);
  		var char_two = validTime.charAt(1);
	  	var hour = char_one + char_two;
  		if(hour > 23)
  		{
  			alert("Transfer Hour must be a number from 0 to 23.\n");
  			thisForm.transferCustodyTimeString.focus();
	  		return false;
  		}
  	
	  	var char_three = validTime.charAt(3);
  		var char_four = validTime.charAt(4);
	  	var minutes = char_three + char_four;
  		if(minutes > 59)
	  	{
  			alert("Transfer minutes must be a number from 0 to 59.\n");
  			thisForm.transferTime.focus();
	  		return false;
  		}
  	
	  	if(hour == 0)
  		{
  			if(minutes <1 )
	  		{
		  		alert( "You must enter a time greater than zero.");
	  			thisForm.transferCustodyTimeString.focus();
	  			return false;  		
	  		}
  		}
  		
		var transferMonth = transferDate.substring(0,2);
		transferMonth = transferMonth - 1;
		var transferDateOfMonth = transferDate.substring(3,5);
		var transferYear = transferDate.substring(6,10);
		
		transferDateParsed = new Date(transferYear, transferMonth, transferDateOfMonth, hour, minutes, 0, 0);
		
		currentDate = new Date();			
		
		if(transferDateParsed.valueOf() > currentDate.valueOf())
		{
			alert("Transfer Date/Time should not be in the future.");
			return false;
		}
  		if (transferYear < '2005'){
			alert("Transfer Date can not be prior to 01/01/2005.");
			return false;  		
  		}
	  	if (transferDate == releaseDecisionDate)
  		{
  			var releaseTime = new String(thisForm.releaseTime.value);
	  		var transferTime = new String(thisForm.transferCustodyTimeString.value);
  		  		
  			var newReleaseTime = releaseTime.replace(":", "");
  			var newTransferTime = transferTime.replace(":", "");
 	 		if(newTransferTime < newReleaseTime)
  			{
  				alert("Transfer Time should not be prior to Release Time.");
  				thisForm.transferCustodyTimeString.focus();
	  			return false;
  			}
   			return true;
	  	}
  
  	}
  	else 
  	{
  	return false;
  }
  
}

// Declaring valid date character
var dtCh= "/";

function isInteger(s)
{
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag)
{
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++)
    {   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year)
{
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}

function DaysArray(n) 
{
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

function isDate(dtStr)
{
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strMonth=dtStr.substring(0,pos1)
	var strDay=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1)
	{	
		return false
	}
	if (strMonth.length != 2 || month<1 || month>12)
	{	
		return false
	}
	if (strDay.length != 2 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month])
	{
		return false
	}
	if (strYear.length != 4 || year==0)
	{
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false)
	{
		return false
	}
	return true
}


</script>