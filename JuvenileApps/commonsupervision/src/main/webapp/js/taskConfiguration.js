var taskRecipFieldsList=new Array();
	var taskTypeFieldList=new Array();
	var emailToFieldList=new Array();
	var emailOtherField=null;
	
	function evaluateOtherEmailField(checkBoxFieldName,addrFieldName){
		var theCheckBox=document.getElementById(checkBoxFieldName);
		var theAddrBox=document.getElementById(addrFieldName);
		if(theCheckBox!=null && theCheckBox.checked==true){
			emailOtherField=addrFieldName;
			if(theAddrBox!=null){
				show(addrFieldName,1,"0");
			}
		}
		else{	
		    emailOtherField=null;
			if(theAddrBox!=null){
				theAddrBox.value="";
				show(addrFieldName,0,"0");
			}
		}
	}
	
	function addCustomEmailFields(emailToFieldName){
		emailToFieldList.push(emailToFieldName);
	}
	
	function addCustomValidationFields(taskRecipFieldName,taskTypeFieldName){
		taskRecipFieldsList.push(taskRecipFieldName);
		taskTypeFieldList.push(taskTypeFieldName);
	}
	
	
	function customValidateTask(){
		clearAllValArrays();
		var myRecipFields=taskRecipFieldsList;
		var myTypeFields=taskTypeFieldList;
		var myEmailFields=emailToFieldList;
		var subjectElem=document.getElementById("tasks.subject2");
		var taskDueBy=document.getElementById("tasks.dueBy");
		addNumericValidation("tasks.dueBy","Task Due By must be numeric");
		
		var noFields=true;
		if(myRecipFields!=null){
			var recipName=null;
			var typeName=null;
			var recipElem=null;
			var typeElem=null;
			for (var i = 0; i < myRecipFields.length; i++) {
				recipName=myRecipFields[i];
				typeName=myTypeFields[i];
				recipElem=document.getElementById(recipName);
				typeElem=document.getElementById(typeName);
				if(recipElem!=null && typeElem!=null){
					if(recipElem.value!="" || typeElem.value!=""){
						noFields=false;
					}
					if(recipElem.value!="" && typeElem.value==""){
						alert("Task List Type is required if a Receipient is selected.");
						return false;
					}
					else if(recipElem.value=="" && typeElem.value!=""){
						alert("Receipient is required if Task List Type is selected.");
						return false;
					}
				}
			}
		}
		if(myEmailFields!=null){
			var emailFieldName=null;
			var emailElem=null;
			for (var k = 0; k < myEmailFields.length; k++) {
				emailFieldName=myEmailFields[k];
				emailElem=document.getElementById(emailFieldName);
				if(emailElem!=null && emailElem.checked==true){
					
					noFields=false;
				}
			}
		}
		if(emailOtherField!=null){
			var theEmailOtherFieldElem=document.getElementById(emailOtherField);
			if(theEmailOtherFieldElem.value==null || theEmailOtherFieldElem.value==""){
				alert("An email address is required when 'Other' is selected.");
				return false;
			}
			else if(theEmailOtherFieldElem.value.search(/^[a-zA-Z0-9_._-]+@[a-zA-Z0-9_._-]+\.[a-zA-Z0-9_]+$/) == -1) {
				alert("The 'Other' email address is in an invalid format");
				return false;
			}
		}
		if(noFields){
			if(subjectElem!=null && subjectElem.value!=''){
				alert("There are no receipents for the given task subject set, please set receipients.");
				return false;
			}
			if(taskDueBy!=null && taskDueBy.value!=''){
				alert("There are no receipents for the given task due by, please set receipients.");
				return false;
			}
		}
		else{
			
			if(subjectElem==null || subjectElem.value==''){
				alert("The Subject cannot be blank if there are people to notify");
				return false;
			}
			if(myRecipFields!=null && (taskDueBy==null || taskDueBy.value=='')){
				alert("The Task Due By cannot be blank if there are task recipients.");
				return false;
			
			}
		}
		if(subjectElem!=null || subjectElem.value!=''){
			if(subjectElem.value.length>50){
				alert("The Subject cannot be greater than 50 characters");
				return false;
			}
			
		}
		return validateCustomStrutsBasedJS(document.forms[0]);
	}