	
function validateFields()
{
	var thisForm = document.forms[0];
	trim(thisForm["userName.lastName"]);
	trim(thisForm["userName.firstName"]);
	trim(thisForm["ssn.SSN1"]);
	trim(thisForm["ssn.SSN2"]);
	trim(thisForm["ssn.SSN3"]);
	trim(thisForm.departmentId);
	trim( thisForm["dateOfBirth"]);
	trim(thisForm["inactivationDate"]);
	if(validateUserProfileCreateUpdateForm(thisForm))
	{
		if(thisForm.actionType.value == "create")
		{
//			if (thisForm["genericUserTypeId"].selectedIndex < 1 || thisForm["genericUserTypeId"].value == "N")
            if (thisForm.genericUserTypeId.value == "N")
			{
			  if(thisForm.dateOfBirth.value == "")
				   {
				      alert("Date of birth is required for Non-generic users.");
				      thisForm["dateOfBirth"].focus();
				      return false;
				   }
		      	}
		      	if(thisForm.departmentId.value=="")
		      	{
		      		alert("Department Code is required.");
			     	thisForm.departmentId.focus();
			      	return false;
		      	}
		      	if(thisForm.departmentId.value >"")
		      	{
		      	  	if (thisForm.departmentId.value.length < 3){
		      			alert("Department Code can not be less than 3 characters.");
				     	thisForm.departmentId.focus();
				      	return false;		      			
		      	 	 }
		    	  	 var regexp = /^[a-zA-Z0-9]*$/;
		      		 var result = regexp.test(thisForm.departmentId.value,regexp);
				  	 if (result == false){
		      			alert("Department Code must be alphanumeric value.");
				     	thisForm.departmentId.focus();
				      	return false;		      			
		      	  	 }
				}			  	
		   }
		   if(thisForm.actionType.value == "update")
		   {
		   		if (thisForm.genericUserTypeId.value == "N" || thisForm.genericUserTypeId.value.toUpperCase() == "NON-GENERIC")
		   		{
					  if(thisForm.dateOfBirth.value == "") {
				      alert("Date of birth is required for Non-generic users.");
				      thisForm["dateOfBirth"].focus();
				      return false;
				     }		     
				}
				if(thisForm.userStatusId.value =="I")
			   	{
				   	 if(thisForm.inactivationDate.value== "")
				   	 {
				   	 	alert("Inactivation Date must be entered for inactivating the User profile");
				   	 	thisForm.userStatusId.focus();
				   	 	return false;
				   	 }
			   	}
			   	if(thisForm.inactivationDate.value != "")
			   	{
				   	 	if(thisForm["inactivationTimeId"].selectedIndex <= 0)
					   	 {
					   	 	alert("Inactivation Time must be selected for inactivating the User profile");
					   	 	thisForm["inactivationTimeId"].focus();
					   	 	return false;
					   	 }
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
			      			var myDate=new Date(thisForm.inactivationDate.value);
				   	 	var inDate = thisForm.inactivationDate.value;
			     		var mm = inDate.substring(0,2);
			      		var dd = inDate.substring(3,5);
			      		var yyyy = inDate.substring(6,10);
			      		var inDateStr = inDate.substring(6,10) + inDate.substring(0,2) + inDate.substring(3,5); 
			      		var currDateStr = currYYYY + currMM.toString() + currDD.toString();
			      		
			      		if (inDateStr < currDateStr)
				   	 	{
				   	 		alert("Inactivation Date cannot be in the past");
				   	 		thisForm.inactivationDate.focus();
				   	 		return false
				   	 	} 
				   	 
				   	 	if (inDateStr == currDateStr) 
			      		{   		
					   	 	var inactiveTime= "";
			    	  		var curHour = curDate.getHours();
			      			var curMin = curDate.getMinutes();
			      			if (curHour < 10){
			      				curHour = "0" + curHour;
				      		} 
			      			if (curMin < 10){
			      				curMin = "0" + curMin;
				      		} 
				      	
				   	 	 	for(var i=0; i<thisForm["inactivationTimeId"].length;i++)
			    			{
			    				if(thisForm["inactivationTimeId"][i].selected){
				    				inactiveTime=thisForm["inactivationTimeId"][i].innerText;				    				
				    				break;
					    		}	
			    			}
			    			
			    			var curTime = curHour.toString() + curMin.toString();
				   	 		var myTime=new String(inactiveTime);
				   	 		//	alert("checking time : " +myTime.charAt(4));
				   	 		//		alert("checking time : " +myTime.charAt(5));
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
							var inTime = myInt.toString() + min.toString();	
					   	 	if(inTime < curTime)
					   	 	{
				   		 		alert("Inactivation Date cannot be in the past");
				   	 			thisForm.inactivationTimeId.focus();
				   	 			return false
				   	 		}
				   	 	 
				   	 	}
				 }
			}
		   if(thisForm.dateOfBirth.value != "")
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
		      		
			   	 	var inDate = thisForm.dateOfBirth.value;
		     		var mm = inDate.substring(0,2);
		      		var dd = inDate.substring(3,5);
		      		var yyyy = inDate.substring(6,10);
		      		var century = inDate.substring(6,8);
		      		var inDateStr = inDate.substring(6,10) + inDate.substring(0,2) + inDate.substring(3,5); 
		      		var currDateStr = currYYYY + currMM.toString() + currDD.toString();
		      		
		      		if (inDateStr > currDateStr)
			   	 	{
			   	 		alert("Birth Date cannot be a future date");
			   	 		thisForm.dateOfBirth.focus();
			   	 		return false
			   	 	}
			   	 	if (century < 19) 
			   	 	{
			   	 	    alert("Date of Birth year cannot start with a number lower than 19.");
			   	 	    thisForm.dateOfBirth.focus();
			   	 	    return false
			   	 	}    
			   	 	   
		   } 	 
		  if (thisForm["ssn.SSN1"].value > "" && thisForm["ssn.SSN2"].value == "") {
		      alert("All of Social Security number must be entered if there is partial entry.");
		      thisForm["ssn.SSN2"].focus();
		      return false;
		   }
		   if (thisForm["ssn.SSN1"].value > "" && thisForm["ssn.SSN3"].value == "") {
		      alert("All of Social Security number must be entered if there is partial entry.");
		      thisForm["ssn.SSN3"].focus();
		      return false;
		   }
		   if (thisForm["ssn.SSN2"].value > "" && thisForm["ssn.SSN1"].value == "") {
		      alert("All of Social Security number must be entered if there is partial entry.");
		      thisForm["ssn.SSN1"].focus();
		      return false;
		   }
		   if (thisForm["ssn.SSN2"].value > "" && thisForm["ssn.SSN3"].value == "") {
		      alert("All of Social Security number must be entered if there is partial entry.");
		      thisForm["ssn.SSN3"].focus();
		      return false;
		   }
		   if (thisForm["ssn.SSN3"].value > "" && thisForm["ssn.SSN1"].value == "") {
		      alert("All of Social Security number must be entered if there is partial entry.");
		      thisForm["ssn.SSN1"].focus();
		      return false;
		   }
		   if (thisForm["ssn.SSN3"].value > "" && thisForm["ssn.SSN2"].value == "") {
		      alert("All of Social Security number must be entered if there is partial entry.");
		      thisForm["ssn.SSN2"].focus();
		      return false;
		   }     
		  
	}
	else
	{
		return false;
	}
	return true;	
}

function trim(textbox) {
  if (textbox) {
    while (textbox.value.substring(0,1) == " ") {
      textbox.value = textbox.value.substring(1);
    }
  }
}

