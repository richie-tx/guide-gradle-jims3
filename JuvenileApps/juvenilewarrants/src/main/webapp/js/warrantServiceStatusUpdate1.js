<!-- warrantServiceStatusUpdate1.js -->
<script language=javascript>

function clearDeptName()
{
	document.getElementById("deptNameTd").innerHTML="";
}

function validateAlphanumericField(checkStr)
{  
	var regex = /^[0-9A-Za-z]+$/;
	
	return regex.test(checkStr);
}

function validateFields(theForm) 
{
	var msg = "";
	var userId = Trim(theForm.elements["userId"].value);
	var officerId = Trim(theForm.elements["officerId"].value);
	theForm.elements["userId"].value = userId;
	theForm.elements["officerId"].value = officerId;
    
	if(document.getElementById('byOfficerId').className == 'hidden' && document.getElementById('byUserId').className == 'hidden')
	{
		alert("A Search By option needs to be selected.\n"); 
		return false;
	}

	if(document.getElementById('byUserId').className != 'hidden')
	{
		if(userId == "")
		{
	      theForm.elements["userId"].focus();
	   	  msg = "User ID is required.\n";
		} else if (userId.length < 5)
	   	   {
	       		msg = "User ID must be at least 5 alphanumeric characters long.\n";
	       		theForm.elements["userId"].focus();
	       } else if(validateAlphanumericField(userId) == false)
	       {
	       		msg = "User ID must be alphanumeric.\n";
	       		theForm.elements["userId"].focus();
	       } 
	}  

	if(document.getElementById('byOfficerId').className!= 'hidden')
	{ 
		if(officerId == "")
		{
		    theForm.officerId.focus();
			msg = "Officer ID is required.\n";
		} 
		if(officerId != "")
	  	{
	  	    if(validateAlphanumericField(officerId) == false)
		    {
			    msg = "Officer ID must be alphanumeric.\n";
			    theForm.officerId.focus();
		    }
	  	    if (theForm.officerIdTypeId.selectedIndex == 0)
	  		{
	      		if (msg == "")
	      		{
		     		theForm.officerIdTypeId.focus();
		  		}
		  		msg += "Officer ID Type is required.\n";
			}
		}
		
		if( theForm.officerAgencyId.value=="")
		{
			msg += "Department code has to be provided to continue.\n";
			theForm.officerAgencyId.focus();		    

		}
	}

	
	if(document.getElementById("deptNameTd").innerHTML=="")
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

function evalSearch(el,clearDeptnameTd){
	if (el.search.options[ el.search.selectedIndex ].value == 'userSearch' ){
		show('byUserId', 1,"row");
		show('byOfficerId', 0,"row");
		show('officerDept',0,"row");
		el.officerId.value="";
		el.officerIdTypeId.value="";
		el.officerAgencyId.value="";
		el.userId.focus();
		if(clearDeptnameTd){
			document.getElementsByName("deptNameTd")[0].innerText="";
		}
	}
	else if (el.search.options[ el.search.selectedIndex ].value == 'officerSearch'){
		show('byOfficerId', 1,"row");	
		show('byUserId', 0,"row");
		show('officerDept',1,"row");
		el.userId.value="";
		el.officerId.focus();
		if(clearDeptnameTd){
			document.getElementsByName("deptNameTd")[0].innerText="";
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
		}
	}
}

function loadDepartment(file, actionVal){
	
	var theForm = document.forms[0];
	
	if(theForm.search.options[ document.forms[0].search.selectedIndex ].value == 'userSearch' || document.getElementById('byUserId').className == 'visibleTR')
	{
		Trim(theForm.userId.value);
		if(theForm.userId.value =="")
		{
			alert("User ID must be entered to find department.");
	        theForm.userId.focus();
	        return false;
		}
		if(theForm.userId.value.length < 5)
		{
			alert("User ID must be at least 5 alphanumeric characters long.");
	        theForm.userId.focus();
	        return false;
		}
	}
	else if(theForm.search.options[ document.forms[0].search.selectedIndex ].value == 'officerSearch' || document.getElementById('byOfficerId').className == 'visibleTR')
	{
		Trim(theForm.officerId.value);
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
	var thisForm = document.forms[0];
	var offAgencyId = Trim(thisForm.officerAgencyId.value);
	thisForm.officerAgencyId.value = offAgencyId;
	if(thisForm.officerAgencyId.value=="")
	{
		alert("Department code has to be provided for Validation.");
		thisForm.officerAgencyId.focus();		    
	    return false;
	}
	return true;
}

</script>
