<!-- scripts for departmentSearch.jsp -->

function validateDepartmentSearchFields(theForm){
    var agencyName = Trim(theForm.agencyName.value);    
	var deptName = Trim(theForm.departmentName.value);    
	var deptCode = Trim(theForm.departmentId.value); 
	var OrgAgencyNum = Trim(theForm.originatingAgencyId.value);   	

	theForm.agencyName.value = agencyName;
	theForm.departmentName.value = deptName;
	theForm.departmentId.value = deptCode;
	theForm.originatingAgencyId.value = OrgAgencyNum;

	var textInput = agencyName + deptName + deptCode + OrgAgencyNum;
	var selectInput = theForm.statusId.selectedIndex + theForm.setcicAccessId.selectedIndex;

	if (textInput == "" && selectInput == 0){	
	    alert("At least 1 input value required for Department search.");
	    theForm.agencyName.focus();
	    return false;
    }

    var msg = validateWildCardSearchField("Agency Name", agencyName);
	if (msg != ""){
       	alert(msg);	
      	theForm.agencyName.focus();  
       	return false;
    } 
    msg = validateWildCardSearchField("Department Name", deptName);
	if (msg != ""){
       	alert(msg);	
      	theForm.departmentName.focus();  
       	return false;
    }     
    msg = validateWildCardSearchField("Department Code", deptCode);
	if (msg != ""){
       	alert(msg);	
      	theForm.departmentId.focus(); 
       	return false;
    }
    msg = validateWildCardSearchField("Orginating Agency Number", OrgAgencyNum);
	if (msg != ""){
       	alert(msg);	
      	theForm.originatingAgencyId.focus(); 
       	return false;
    }    
   return true;
}
function displaySearchButtonList(inUse, inActive){
	var elementBtn;
	showAllButtons();
	if(inUse){
		elementBtn=document.getElementById("btnDelete");
		elementBtn.className='hidden';
	}
	if(inActive){
		elementBtn=document.getElementById("btnCreateContact");
		elementBtn.className='hidden';
		elementBtn=document.getElementById("btnModifyContact");
		elementBtn.className='hidden';
	}
}

function showAllButtons(){
	elementBtn=document.getElementById("btnUpdate");
	elementBtn.className='visible';
	elementBtn=document.getElementById("btnDelete");
	elementBtn.className='visible';
	elementBtn=document.getElementById("btnCopy");
	elementBtn.className='visible';
	elementBtn=document.getElementById("btnCreateContact");
	elementBtn.className='visible';
	elementBtn=document.getElementById("btnModifyContact");
	elementBtn.className='visible';
}
function changeDeptId(deptIdValue){
	var element=document.getElementById("deptId");
	element.value=deptIdValue;
}
