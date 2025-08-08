function checkAdvancedSearchCriteria(theForm){
	if (document.getElementById("searchBeginDateAsString").value==""  &&
		document.getElementById("searchEndDateAsString").value=="" &&
		document.getElementById("searchCourt").value=="" &&
		document.getElementById("searchCollateralId").value == "" &&
		document.getElementById("searchGeneratedById").value == "" &&		
		document.getElementById("searchCasenoteTypeId").value == "" &&
		document.getElementById("searchCaseNumberIds").value == "" &&
		document.getElementById("searchSubjectIds").value == "" ) {
			alert("At least one search criteria is required");
			return false;
	}
	clearAllValArrays();
	if(document.getElementById("searchEndDateAsString").value!="" || document.getElementById("searchBeginDateAsString").value!=""){
		customValRequired("searchBeginDateAsString", "Begin Date is required to complete Date Range.");
		addMMDDYYYYDateValidation("searchBeginDateAsString", "Begin Date must be in the format MM/DD/YYYY.");
		customValRequired("searchEndDateAsString", "End Date is required to complete Date Range.");
		addMMDDYYYYDateValidation("searchEndDateAsString", "End Date must be in the format MM/DD/YYYY.");
		if(validateCustomStrutsBasedJS(theForm)){
			if(compareDate1GreaterEqualDate2(document.getElementById("searchEndDateAsString").value,document.getElementById("searchBeginDateAsString").value)){
				
			}
			else{
				alert("Begin Date must be less than or equal to end date.");
				document.getElementById("searchBeginDateAsString").focus();
				return false;
			}
		}
		else{
			return false;
		}
		
	}

	clearAllValArrays();	
	if(document.getElementById("searchCourt").value!=""){
		addAlphaNumericnSpaceValidation("searchCourt", "Court must be alphanumeric.");
		if(!validateCustomStrutsBasedJS(theForm)){
			return false;			
		}
	}

	if(document.getElementById("searchCollateralId").value!="" && document.getElementById("searchCollateralId").value=="AS"){
		var theAssocElem=theForm["searchAssociateIds"];
		if (theAssocElem.length > 1){
			var theAssocVal=theAssocElem.options[theAssocElem.selectedIndex].value
			if(theAssocVal==""){
				alert("An Associate must be selected.");
				theAssocElem.focus();
				return false;
			} 	
		}else {
			alert("An Associate must be selected.");
			theAssocElem.focus();
			return false;
		}
	}

	clearAllValArrays();
	if(document.getElementById("searchGeneratedById").value!=""){
		var theGenerElem=theForm["searchGeneratedById"];
		var theGenerVal=theGenerElem.options[theGenerElem.selectedIndex].value
		if(theGenerVal=="CB"){
			customValRequired("createdByName.lastName", "Last Name is required.");
			addSearchFieldValidation("createdByName.lastName", "Last Name has invalid symbols.");
			addSearchFieldValidation("createdByName.firstName", "First Name has invalid symbols.");
			customValMinLength("createdByName.lastName", "Last Name must be at least 3 characters.","3");
			customValMinLength("createdByName.firstName", "First Name must be at least 3 characters.","3");
			if(!validateCustomStrutsBasedJS(theForm)){
				return false;	
			}
		}
	}
	return true;
}

/*function renderAdvancedFields(){
	var endDateStrElem=document.getElementById("searchCasenote.casenoteEndDateAsStr");
	var howGenSelect=document.getElementById("searchCasenote.generatedById");
	changeDropDownSysGen(howGenSelect.options[howGenSelect.selectedIndex].value, 'createdBy', 'row');
	var collateralSelect=document.getElementById("searchCasenote.collateralId");
	renderAssociatesSelect(collateralSelect);
} */

function checkBasicSearchCriteria(theForm){
	clearAllValArrays();
	var theSelectedField = theForm["searchById"];
	theSelectedValue = theSelectedField.options[theSelectedField.selectedIndex].value;
	if(theSelectedValue==""){
		alert("A Search By criteria must be selected.");
		theSelectedField.focus();
		return false;
	}
	switch (theSelectedValue)
	{
		case "D": 
			customValRequired("searchBeginDateAsString", "Begin Date is required.");
			addMMDDYYYYDateValidation("searchBeginDateAsString", "Begin Date must be in the format MM/DD/YYYY.");
			customValRequired("searchEndDateAsString", "End Date is required.");
			addMMDDYYYYDateValidation("searchEndDateAsString", "End Date must be in the format MM/DD/YYYY.");
			if(validateCustomStrutsBasedJS(theForm)){
				if(compareDate1GreaterEqualDate2(document.getElementById("searchEndDateAsString").value,document.getElementById("searchBeginDateAsString").value)){
					return true;
				}
				else{
					alert("Begin Date must be less than or equal to end date");
					document.getElementById("searchBeginDateAsString").focus();
					return false;
				}
			}
			else{
				return false;
			}
		break

		case "R": 
			customValRequired("searchCourt", "Court is required");
			addAlphaNumericnSpaceValidation("searchCourt", "Court must be alphanumeric.");
			return validateCustomStrutsBasedJS(theForm);
		break

		case "T": 
			customValRequired("searchCasenoteTypeId", "Type selection is required.");
			return validateCustomStrutsBasedJS(theForm);
		break

		case "C":  
			customValRequired("searchCaseNumberIds", "Case selection is required.");
			return validateCustomStrutsBasedJS(theForm);
		break

		case "S": 
			customValRequired("searchSubjectIds", "Subjects selection is required.");
			return validateCustomStrutsBasedJS(theForm);
		break

		case "L": 
			customValRequired("searchCollateralId", "Collateral selection is required.");
			if(validateCustomStrutsBasedJS(theForm)){
				if(document.getElementById("searchCollateralId").value!="" && document.getElementById("searchCollateralId").value=="AS"){
					var theAssocElem=theForm["searchAssociateIds"];
					var theAssocVal=theAssocElem.options[theAssocElem.selectedIndex].value
					if(theAssocVal==""){
						alert("An Associate must be selected.");
						theAssocElem.focus();
						return false;
					}
				}
			}
			else{
				return false;
			}
			
		break  
     
		case "H":  
			customValRequired("searchGeneratedById", "How Generated selection is required.");
			var theGenerElem=theForm["searchGeneratedById"];
			var theGenerVal=theGenerElem.options[theGenerElem.selectedIndex].value
			if(theGenerVal==""){
				alert("How generated is required.");
				theGenerElem.focus();
				return false;
			}  
			if(theGenerVal=="CB"){
				customValRequired("createdByName.lastName", "Last Name is required.");
				addSearchFieldValidation("createdByName.lastName", "Last Name has invalid symbols.");
				addSearchFieldValidation("createdByName.firstName", "First Name has invalid symbols.");
				customValMinLength("createdByName.lastName", "Last Name must be at least 3 characters.","3");
				customValMinLength("createdByName.firstName", "First Name must be at least 3 characters.","3");
				return validateCustomStrutsBasedJS(theForm);
			}
			else{
				return true;
			}
		break;
	}
return true;
}

function renderSelect(el){
	if (el.options[el.selectedIndex].value=="associate"){
		show("associateSelect", 1, "inline")
		show("addLink", 1, "inline")
		}else if (el.options[el.selectedIndex].value==""){
			show("associateSelect", 0)
			show("addLink", 0)
			}else {
				show("associateSelect", 0)
				show("addLink", 0)
			}
}

function renderSelect2(el){
	if (el.options[el.selectedIndex].value=="associate"){
		show("associateSelect2", 1, "inline")
		}else	if (el.options[el.selectedIndex].value==""){
			show("associateSelect2", 0)
			}else {
				show("associateSelect2", 0)
			}
		}

function renderSelect3(el){
	if (el.options[el.selectedIndex].value=="associate"){
		show("associateSelect3", 1, "inline")
		}else	if (el.options[el.selectedIndex].value==""){
			show("associateSelect3", 0)
			}else {
				show("associateSelect3", 0)
			}
		}

function displayBasicSearchFields(el,noClear){
	var theSelectedValue = el.options[el.selectedIndex].value
//alert("Selected Value..." + theSelectedValue + "\nnoClear..." + noClear );	
	if(noClear!="true"){
		clearAllFieldsInBasic();
	}
	show("dateRange", 0)
	show("courtField", 0)
	show("typeField", 0)
	show("casesField", 0)
	show("subjectField", 0)
	show("contactField", 0)
	show("howGeneratedField", 0)	
/**
	C = cases
	D = date range
	H = how generated
	L = collateral
	R = court
	S = casenote subjects
	T = casenote type
*/		
	switch (theSelectedValue)
	{
		case "D":
			show("dateRange", 1);
			document.getElementById("searchEndDateAsString").value=getCurrentDate();
			break;

		case "R":
			show("courtField", 1);
			break;

		case "T":
			show("typeField", 1);
			break;

		case "C":
			show("casesField", 1);
			break;

		case "S":
			show("subjectField", 1);
			break;

		case "L":
			show("contactField", 1);
			document.getElementById("searchCollateralId").selectedIndex = 0;
			document.getElementById("searchAssociateIds").selectedIndex = 0;
			show('SearchCollateralAssociates',0);
			break; 

		case "H":
			show("howGeneratedField", 1);
			document.getElementById("searchGeneratedById").selectedIndex = 0;
			document.getElementById("createdByName.lastName").value = "";
			document.getElementById("createdByName.firstName").value = "";			
			break;

		default:
			show("dateRange", 0);
			show("courtField", 0);
			show("typeField", 0);
			show("casesField", 0);
			show("subjectField", 0);
			show("contactField", 0);
			show("howGeneratedField", 0);
	}
}

function clearAllFieldsInBasic(){
	document.getElementById("searchGeneratedById").selectedIndex=0;
	document.getElementById("createdByName.lastName").value="";
	document.getElementById("createdByName.firstName").value="";
	document.getElementById("searchBeginDateAsString").value="";
	document.getElementById("searchEndDateAsString").value="";
	document.getElementById("searchCasenoteTypeId").selectedIndex=0;
	document.getElementById("searchCaseNumberIds").selectedIndex=0;
	document.getElementById("searchSubjectIds").selectedIndex=0;
	document.getElementById("searchCollateralId").selectedIndex=0;
	document.getElementById("searchAssociateIds").selectedIndex=0;
	document.getElementById("searchCourt").value="";
}
	
function showHideCreatedBy(createdVal, idToShow, format){
	if(createdVal=="CB"){
		show(idToShow,1, format);
	}else{
		show(idToShow,0);
	}
	document.getElementById("createdByName.lastName").value = "";
	document.getElementById("createdByName.firstName").value = "";			
}
	
function changeDropDownSysGen(sysGenVal, idToShow, format){
	if(sysGenVal=="CB"){
		show(idToShow,1, format);
	}else{
		show(idToShow,0);
	}
}
