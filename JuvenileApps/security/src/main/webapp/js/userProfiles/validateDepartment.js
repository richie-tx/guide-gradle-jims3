function validateDepartment() {
		
	var thisForm = document.forms[0];
	trim(thisForm.departmentId);
	if(thisForm.departmentId.value=="")
	{
	alert("Department code has to be provided for Validation.");
			thisForm.departmentId.focus();		    
		    return false;
	}
 	return true;
}

function validateNewDepartment() {
		
	var thisForm = document.forms[0];
	trim(thisForm.newDepartmentId);
	if(thisForm.newDepartmentId.value=="")
	{
		alert("Department code has to be provided for Validation.");
		thisForm.newDepartmentId.focus();		    
	    return false;
	}
 	return true;
}

function validateDeptTransferFields() {
		
	var thisForm = document.forms[0];
	trim(thisForm.newDepartmentId);
	trim(thisForm.transferDate);
	if(thisForm.newDepartmentId.value=="")
	{
		alert("Department code has to be provided for Transfer.");
		thisForm.newDepartmentId.focus();		    
	    return false;
	}
	if(thisForm.transferDate.value=="")
	{
		alert("Department Transfer Date has to be provided for Transfer.");
		thisForm.transferDate.focus();		    
	    return false;
	}
	if(thisForm["transferTime"].selectedIndex < 1)
	{
		alert("Department Transfer Time has to be selected for Transfer.");
		thisForm.transferTime.focus();		    
	    return false;
	}
	
		  	var currDate=new Date();
	   	 	var myDate=new Date(thisForm.transferDate.value);
	   	 	var inactiveTime="";
	   	 	for(var i=0; i<thisForm["transferTime"].length;i++)
    		{
    			if(thisForm["transferTime"][i].selected)
    			inactiveTime=thisForm["transferTime"][i].innerText;
    		}
	   	 	var myTime=new String(inactiveTime);
	   	 	var char_five = myTime.charAt(6);
  			var char_six = myTime.charAt(7);
  			var timeOfDay = char_five + char_six;
  			var hour= myTime.charAt(0) + myTime.charAt(1);
  			var min=myTime.charAt(3)+myTime.charAt(4);
  			var myInt;
  			if (timeOfDay == 'AM')
  			{
  				myInt=hour;
  				
  				if(hour==12)
  				{  
  				myInt=parseInt(hour); 									
  					myInt=0;
  				}
  			}
  			else
  			{
  				myInt=hour;
  				if(hour!=12)
  				{
  					myInt=parseInt(hour);
  					myInt+=12;
  				}
  
  			}
	   	 	myDate.setHours(myInt);
	 		myDate.setMinutes(min);
	 		myDate.setSeconds(0);	 	
	   	 	if(myDate < currDate)
	   	 	{
	   	 		alert("Transfer Date/Time cannot be in the past");
	   	 		thisForm.transferDate.focus();
	   	 		return false
	   	 	}
 	return validateTransferDateForm(thisForm);
}

function trim(textbox) {
  if (textbox) {
    while (textbox.value.substring(0,1) == " ") {
      textbox.value = textbox.value.substring(1);
    }
  }
}


