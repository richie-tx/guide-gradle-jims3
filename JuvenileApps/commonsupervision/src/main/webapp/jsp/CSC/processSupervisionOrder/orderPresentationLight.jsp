<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 12/07/2005	 Hien Rodriguez - Create JSP -->
<!-- 08/25/2006	 Hien Rodriguez - ER#34507 Implement new UI Guidelines for date field -->
<!-- 10/02/2006  Hien Rodriguez - ER#35457 Add new field PLEA -->
<!-- 11/15/2006  Hien Rodriguez - Defect#37052 Allow to change PLEA field of Original order with order status not active  -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 07/23/2007	 C Shimek       - #43668 Add Fine Amount input field and edit. -->
<!-- 08/07/2007	 C Shimek       - #44250 Revised Add Fine Amount to allow 0.00 as valid amount. -->
<!-- 08/08/2007	 C Shimek       - #44186 Added Offense Code edits to validateField(). -->
<!-- 06/10/2008  D Williamson   - ER#51556 Added requirement for 2 digits for supervision & confinement year, month, and day fields. -->
<!-- 07/08/2008  R Bajeev		- ER#51556 Revised due to problem w/DAW's code -->
<!-- 07/09/2008  L Deen			- ER#51556 Added maxlength=2 to confinement fields -->
<!-- 08/08/2008  D Gibler		- ER#52277 Added logic tags to display title for migrated order differently -->
<!-- 10/30/2008  R Young		- Defect#55115 Fixed summary of changes for Historical A or M orders -->
<!-- 06/23/2009  C Shimek       - #60524 added plea value validation to validateField() -->
<!-- 08/07/2009  C Shimek       - #61094 convert Plea from input text to drop down list -->
<!-- 08/17/2009  C Shimek       - #61534 not reproducible but corrected scripting for Calculate button to handle new Order Title values that contain the value "Determinate Sentencing" --> 
<!-- 08/24/2009  C Shimek       - #61664 added script and code to display juvenile court drop down for determinate sentencing order title -->
<!-- 09/10/2009  R Young		- Defect#61990 Removed the original option from the drop down list -->
<!-- 09/16/2009  C Shimek       - #61973 added Comments for Printed Order input field (missed during Activity #61438 changes) -->
<!-- 09/17/2009  C Shimek       - found and removed unnecessary checkForMod() script while testing defect#62008 -->
<!-- 09/18/2009  C Shimek       - #62060 removed isMigrated=true and cdi=003 logic tags to display judge name inputs and added alphanumeric with symbols validation to same fields -->
<!-- 09/24/2009  C Shimek       - #62151 added missing specail courts drop down list -->
<!-- 10/08/2009  C Shimek       - #61837 revised printed name validation to not allow / or & -->
<!-- 10/19/2009  C Shimek       - #62093 add validation to comments for printed order to not allow backslash -->
<!-- 10/20/2009  C Shimek       - #62485 revised page to handle access from back button on Select Sug Order jsp for migrated order -->
<!-- 10/26/2009  C Shimek       - #60771 revised offense code to display only, removed Validate button and Find Offense Code href -->
<!-- 10/28/2009  C Shimek       - #62664 revised Calculate button to use local js instead on submitAction -->
<!-- 11/03/2009  C Shimek       - #62713 revised end date calculation script to match calc examples in original ER #60104 -->
<!-- 11/09/2009  C Shimek       - #62754 revised scripting for update migrated modified orders to display judges name input for felony cases -->
<!-- 11/11/2009  C Shimek       - #62440 Revised Historical to Pretrial Intervention and made dispostion type display only for P.I. -->
<!-- 11/12/2009  C Shimek       - #60771 complete remove offense code display in update block, ER solution changed -->
<!-- 11/12/2009  C Shimek       - #62440 (note from Mary)followup with removal of version number and change to plea display to display only for amended/modified version type -->
<!-- 12/01/2009  C Shimek       - #62433 reopened - corrected juvenile supervision begin date validation to use correct field string value -->
<!-- 12/07/2009  C Shimek       - Per email from Mary, re-added Version Num for migrated, incuding validation -->
<!-- 12/11/2009  C Shimek       - #63106 make plea updateable for migrated orders -->
<!-- 12/29/2009  C Shimek       - #63272 remove logic restriction to allow Plea to be updateable for any flow -->
<!-- 01/14/2010  C Shimek       - added = symbol as valid symbol to printed offense description RegExp -->
<!-- 02/26/2010  C Shimek       - #64195 revised to only require Original Judge name for felony case (cdi=003) -->
<!-- 03/04/2010  C Shimek       - #64228 revised supervision and confinement length validation based on order title selection of Determinate Sentencing -->
<!-- 03/10/2010  C Shimek       - #64351 added edit to Printed Office Description to require space after symbol < and a space before symbol > -->
<!-- 07/29/2010  D Williamson	- ER#55920 Added Text Counter for Summary of Changes field -->
<!-- 10/19/2010  C Shimek       - #67921 removed "intervention" from pretrial begin and end date labels -->
<!-- 04/04/2011  L Deen		    - #67870 changed Printed Offense Description length from 50 to 75 to match maxlength & Reqs -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
 
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@page import="naming.UIConstants"%>
<%@page import="naming.PDCodeTableConstants"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - processSupervisionOrder/orderPresentationLight.jsp</title>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="supervisionOrderForm" />

<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<script type="text/javascript">
	function showDate(el,theForm){
		if (el!=null && el.checked){
			show("supervisionBeginDate", 1, "row");
			show("supervisionEndDate", 1, "row");
		}else {
				
				show("supervisionBeginDate", 0);
				show("supervisionEndDate", 0);
			}
		if (location.search=="?modOOC"){
			show("hardshipRow", 0);
		}
	}
	
	// If limitedSupervisionPeriod is unchecked, blank both dates out
	function checkSupervisionDates(theForm){
		if (theForm.limitedSupervisonPeriod !=null && !(theForm.limitedSupervisonPeriod.checked)){
	 		theForm.supervisionBeginDateAsString.value = "";
			theForm.supervisionEndDateAsString.value = "";
	 	}
	 	return true;
	}
	
	function validateField(theForm){
		clearAllValArrays();
		var editJuvFields = false;
		if(theForm.versionTypeId != null)
		{
			customValRequired("versionTypeId", "Version Type is required.","");
	   	}
		var reDisable=false;
		if(theForm.orderTitleId.disabled){
			reDisable=true;
		}
		theForm.orderTitleId.disabled=false;
		
		if(theForm.versionTypeId.value != 'M'){ //orderTitle not necessary when version type is modified
			if(theForm.orderTitleId.selectedIndex == 0)
			{				
		     	alert("Please select Order Title.");
		    	theForm.orderTitleId.focus();
		    	if(reDisable){
		    		theForm.orderTitleId.disabled=true;
		    	}
		     	return false;
		   	} else if (theForm.orderTitleId.options[theForm.orderTitleId.selectedIndex].text.indexOf("DETERMINATE SENTENCING") > -1){
				editJuvFields = true;
			   	if (theForm.juvCourtId.selectedIndex == 0){
			     	alert("Please select Juvenile Court.");
			    	theForm.juvCourtId.focus();
			    	return false;
			   	} 		     	
		   	}
	   	}
		if(reDisable){
    		theForm.orderTitleId.disabled=true;
    	}
		if (editJuvFields == true){
	   		if(theForm.juvSupervisionLengthYears.value <= 0 && theForm.juvSupervisionLengthMonths.value <= 0 && theForm.juvSupervisionLengthDays.value <= 0){
	   			alert("Juvenile Supervision Length is required and must be greater than zero.");
	   			theForm.juvSupervisionLengthYears.focus();
	   			return false;
	   		}
		   	customValRequired("juvCourtId", "Juvenile Court is required.","");
	   		customValRequired("juvSupervisionLengthYears", "Juvenile Supervision Length in Years is required.","");
	   		customValRequired("juvSupervisionLengthMonths", "Juvenile Supervision Length in Months is required.","");
	   		customValRequired("juvSupervisionLengthDays", "Juvenile Supervision Length in Days is required.","");
			if (theForm.supervisionLengthYears.value > ""){
	   			addNumericValidation("juvSupervisionLengthYears", "Juvenile Supervision Length in Years must be an integer and nonnegative.");
			}
			if (theForm.supervisionLengthMonths.value > ""){
	   			addNumericValidation("juvSupervisionLengthMonths", "Juvenile Supervision Length in Months must be an integer and nonnegative.");
			}	
			if (theForm.supervisionLengthDays.value > ""){
	   			addNumericValidation("juvSupervisionLengthDays", "Juvenile Supervision Length in Days must be an integer and nonnegative.");
			}
	   		customValRequired("juvSupervisionBeginDateAsString", "Juvenile Supervision Begin Date is required.","");
	   		addMMDDYYYYDateValidation("juvSupervisionBeginDateAsString","Juvenile Supervision Begin Date must be in date format ");
		}	
	
	   	if (theForm.caseSupervisionBeginDateAsString.value != "" && theForm.caseSupervisionEndDateAsString.value != "")
		{
			var thisBeginDate = new Date(theForm.caseSupervisionBeginDateAsString.value);
			var thisEndDate = new Date(theForm.caseSupervisionEndDateAsString.value);
			if (thisEndDate < thisBeginDate)
			{		
		     	alert("Supervision End Date must be equal or greater than Supervision Begin Date.");
		     	theForm.caseSupervisionEndDateAsString.focus();
		     	return false;
		   	}
	   	}
	   	if (theForm.orderIsMigrated.value == "true"){
		   	if(theForm.versionTypeId.value == 'A' || theForm.versionTypeId.value == 'M')
			{
				if(theForm.versionNum.value=='0'){
					theForm.versionNum.value="";
				}
		   	 	customValInteger("versionNum", "Version number must be an integer.","");
		   	 	customValMinLength("versionNum", "Version number must be greater than 1.","1");
	//	   	 	customValMaxLength("versionNum", "Version number must be less than 999","3");
		   	 	addNumericValidation("versionNum", "Version number must be positive numeric digits","3");
		   		customValRequired("versionNum", "Version number is required.","");
			}
	   	}  
	    if(theForm.versionTypeId.value == 'A' || theForm.versionTypeId.value == 'M')
	    {
		   	customValRequired("summaryOfChanges", "Summary Of Changes for Printed Order is required.","");
	   	 	addDB2FreeTextValidation('summaryOfChanges','Summary of Changes contains invalid symbols. Invalid symbols are: % (percent) and _ (underscore).',null);
	   	 	customValMaxLength ('summaryOfChanges','Summary of Changes cannot be more than 500 characters.',500);
		}

		customValRequired("printedOffenseDesc", "Printed Offense Description is required.","");
		var prtOffRegExp = /^[a-zA-Z0-9\.\\\\';,()\x26\x2f\x3c\x3d\x3e\x24\-][a-zA-Z0-9 \.\\\\';,()\x26\x2f\x3c\x3d\x3e\x24\-]*$/;
		customValMask('printedOffenseDesc','Printed Offense Description contains invalid symbols.',prtOffRegExp);
		var pod = trimAll(theForm.printedOffenseDesc.value);
		if (pod.indexOf(">") > -1 && pod.indexOf("<") > -1){
			var x = -1;
			var podMsg = "";
			while( x < pod.length){
				x++;
				if (pod.charAt(x) == "<" && x + 1 < pod.length && pod.charAt(x + 1) != " "){
					podMsg = "Printed Offense Description missing space after < symbol.";
					x = pod.length;
				}	
				if (pod.charAt(x) == ">" && pod.charAt(x -1) != " " && x > 0){
					podMsg = "Printed Offense Description missing space before > symbol.";
					x = pod.length;
				}	
			}	
			if (podMsg != ""){
				alert(podMsg);
				theForm.printedOffenseDesc.focus();
				return false;
			}	
		}
		customValRequired("printedName", "Printed Name is required.","");	
		var prtNameRegExp = /^[a-zA-Z0-9\.\';,()\-][a-zA-Z0-9 \.\';,()\-]*$/;
		customValMask('printedName','Printed Name contains invalid symbols.',prtNameRegExp);	
//		alert("VALIDATE..." + theForm.dispositionTypeId.type);	
	   	if (theForm.dispositionTypeId.type == 'select-one'){ 
	   		var theSelect=theForm.dispositionTypeId;
	   		customValRequired("dispositionTypeId", "Disposition Type is required.","");
	   	}
    	var fineAmtValue = trimAll(theForm.fineAmountTotal.value);
   		theForm.fineAmountTotal.value = fineAmtValue;
		if(fineAmtValue != "" && fineAmtValue != "0.00")
		{
			addCurrencyValidation("fineAmountTotal", "Fine Amount must be in valid currency format (ex: 4500.00).","");		
		}	
		if (fineAmtValue == "" || fineAmtValue == null)
		{
			theForm.fineAmountTotal.value = "0.00";
		}

		var numericMask=/^[0-9]*$/;	
		var entryCtr = 0;
	   	if (theForm.dispositionTypeId.type == 'select-one'){ 		   	
		   	if(theSelect.options[theSelect.selectedIndex].value == "<%=UIConstants.DISPOSITION_TYPE_CODE_DEFERRED%>"){
		   	// fill fields with zeroes in case user blanks out field due to only 1 value allowed unless Order Title is determinate sentencing.	  		   		
				if (theForm.supervisionLengthYears.value == ""){
					theForm.supervisionLengthYears.value = "00";
				}
				if (theForm.supervisionLengthMonths.value == ""){
					theForm.supervisionLengthMonths.value = "00"
				}	
				if (theForm.supervisionLengthDays.value == ""){
					theForm.supervisionLengthDays.value = "000"
				}	

				if (numericMask.test(theForm.supervisionLengthYears.value,numericMask) == false){
					alert("Supervision Length in Years must be a number of zero or more.");
					theForm.supervisionLengthYears.focus();
		   			return false;
				}	
				if (numericMask.test(theForm.supervisionLengthMonths.value,numericMask) == false){
					alert("Supervision Length in Months must be a number of zero or more.");
					theForm.supervisionLengthMonths.focus();
		   			return false;				
				}
				if (numericMask.test(theForm.supervisionLengthDays.value,numericMask) == false){
					alert("Supervision Length in Days must be a number of zero or more.");
					theForm.supervisionLengthDays.focus();
		   			return false;				
				}
		   		if(theForm.supervisionLengthYears.value <= 0 && theForm.supervisionLengthMonths.value <= 0 && theForm.supervisionLengthDays.value <= 0){
		   			alert("Supervision Length is required and must be greater than zero.");
		   			theForm.supervisionLengthYears.focus();
		   			return false;
		   		}
// only 1 length value allowed unless order title is determinate sentencing	   		
		   		if (editJuvFields == false){
		   			if (parseInt(theForm.supervisionLengthYears.value) != 0){
		   				entryCtr++;
		   			}	
		   			if (parseInt(theForm.supervisionLengthMonths.value) != 0){
		   				entryCtr++;
		   			}	
		   			if (parseInt(theForm.supervisionLengthDays.value) != 0){
		   				entryCtr++;
		   			}
		   			if (entryCtr > 1){
		   				alert("Supervision Length combination entry of Years, Months and Days only allowed for Determinate Sentencing order.");
		   				theForm.supervisionLengthYears.focus();
		   				return false;
		   			}	
		   		}	
		   		
//		   		if(theForm.supervisionLengthYears.value <= 0 && theForm.supervisionLengthMonths.value <= 0 && theForm.supervisionLengthDays.value <= 0){
//	   				alert("Supervision Length is required and must be greater than zero.");
//	   				theForm.supervisionLengthYears.focus();
//	   				return false;
//	   		}
//		   		addNumericValidation("supervisionLengthYears", "Supervision Length in Years must be an integer and nonnegative.");
//		   		addNumericValidation("supervisionLengthMonths", "Supervision Length in Months must be an integer and nonnegative.");
//	   			addNumericValidation("supervisionLengthDays", "Supervision Length in Days must be an integer and nonnegative.");
	   			customValRequired("caseSupervisionBeginDateAsString", "Supervision Begin Date is required.","");
	   			customValRequired("caseSupervisionEndDateAsString", "Supervision End Date is required.","");
	   			addMMDDYYYYDateValidation("caseSupervisionBeginDateAsString","Supervision Begin Date must be in date format ");
	   			addMMDDYYYYDateValidation("caseSupervisionEndDateAsString","Supervision End Date must be in date format ");
		   	}
	   	}
	   	<%--JIMS200059358 validation for original judge only needed when original judge name does not already exist (migrated orders)--%>
			if (typeof(theForm.originalJudgeFirstName) != "undefined"){
				if (document.getElementById("origJudgeName").className == "visible"){
					if (theForm.cdiValue.value == "003"){
						customValRequired("originalJudgeFirstName", "Original Judge First Name is required.","");	
						customValRequired("originalJudgeLastName", "Original Judge Last Name is required.","");
						addAlphaNumericNoFirstSpacewSymbolsValidation("originalJudgeFirstName", "Original Judge First Name must be alphanumeric.",null)
						addAlphaNumericNoFirstSpacewSymbolsValidation("originalJudgeLastName", "Original Judge Last Name must be alphanumeric.",null)
					}
				}	
			}	
	   	if (theForm.dispositionTypeId.type == 'select-one'){ 								
		   	if(theSelect.options[theSelect.selectedIndex].value == "<%=UIConstants.DISPOSITION_TYPE_CODE_PROBATION%>"){
		   	// fill fields with zeroes in case user blanks out field due to only 1 value allowed unless Order Title is determinate sentencing.	  		   		
				if (theForm.confinementLengthYears.value == ""){
					theForm.confinementLengthYears.value = "00";
				}
				if (theForm.confinementLengthMonths.value == ""){
					theForm.confinementLengthMonths.value = "00"
				}	
				if (theForm.confinementLengthDays.value == ""){
					theForm.confinementLengthDays.value = "000"
				}

				if (numericMask.test(theForm.confinementLengthYears.value,numericMask) == false){
					alert("Confinement Length in Years must be a number of zero or more.");
					theForm.confinementLengthYears.focus();
		   			return false;
				}	
				if (numericMask.test(theForm.confinementLengthMonths.value,numericMask) == false){
					alert("Confinement Length in Months must be a number of zero or more.");
					theForm.confinementLengthMonths.focus();
		   			return false;				
				}
				if (numericMask.test(theForm.supervisionLengthDays.value,numericMask) == false){
					alert("confinement Length in Days must be a number of zero or more.");
					theForm.confinementLengthDays.focus();
		   			return false;				
				}
		   		if(theForm.confinementLengthYears.value <= 0 && theForm.confinementLengthMonths.value <= 0 && theForm.confinementLengthDays.value <= 0){
		   			alert("Confinement Length is required and must be greater than zero.");
		   			theForm.confinementLengthYears.focus();
		   			return false;
		   		}
	// only 1 length value allowed unless order title is determinate sentencing	   		
		   		if (editJuvFields == false){
		   			entryCtr = 0;
		   			if (parseInt(theForm.confinementLengthYears.value) != 0){
		   				entryCtr++;
		   			}	
		   			if (parseInt(theForm.confinementLengthMonths.value) != 0){
		   				entryCtr++;
		   			}	
		   			if (parseInt(theForm.confinementLengthDays.value) != 0){
		   				entryCtr++;
		   			}
		   			if (entryCtr > 1){
		   				alert("Confinement Length combination entry of Years, Months and Days only allowed for Determinate Sentencing order.");
		   				theForm.confinementLengthYears.focus();
		   				return false;
		   			}	
		   		}

//		   		if(theForm.confinementLengthYears.value<= 0 && theForm.confinementLengthMonths.value <= 0 && theForm.confinementLengthDays.value<= 0){
//		   			alert("Confinement Length is required and must be greater than zero.");
//	   				theForm.confinementLengthYears.focus();
//	   				return false;
//	   			}

//		   		if(theForm.supervisionLengthYears.value <= 0 && theForm.supervisionLengthMonths.value <= 0 && theForm.supervisionLengthDays.value <= 0){
//	   				alert("Supervision Length is required and must be greater than zero.");
//	   				theForm.supervisionLengthYears.focus();
//	   				return false;
//	   			}
		   	// fill fields with zeroes in case user blanks out field due to only 1 value allowed unless Order Title is determinate sentencing.	  		   		
				if (theForm.supervisionLengthYears.value == ""){
					theForm.supervisionLengthYears.value = "00";
				}
				if (theForm.supervisionLengthMonths.value == ""){
					theForm.supervisionLengthMonths.value = "00"
				}	
				if (theForm.supervisionLengthDays.value == ""){
					theForm.supervisionLengthDays.value = "000"
				}
				if (numericMask.test(theForm.supervisionLengthYears.value,numericMask) == false){
					alert("Supervision Length in Years must be a number of zero or more.");
					theForm.supervisionLengthYears.focus();
		   			return false;
				}	
				if (numericMask.test(theForm.supervisionLengthMonths.value,numericMask) == false){
					alert("Supervision Length in Months must be a number of zero or more.");
					theForm.supervisionLengthMonths.focus();
		   			return false;				
				}
				if (numericMask.test(theForm.supervisionLengthDays.value,numericMask) == false){
					alert("Supervision Length in Days must be a number of zero or more.");
					theForm.supervisionLengthDays.focus();
		   			return false;				
				}
		   		if(theForm.supervisionLengthYears.value <= 0 && theForm.supervisionLengthMonths.value <= 0 && theForm.supervisionLengthDays.value <= 0){
		   			alert("Supervision Length is required and must be greater than zero.");
		   			theForm.supervisionLengthYears.focus();
		   			return false;
		   		}
// only 1 length value allowed unless order title is determinate sentencing	   		
		   		if (editJuvFields == false){
		   			entryCtr = 0;
		   			if (parseInt(theForm.supervisionLengthYears.value) != 0){
		   				entryCtr++;
		   			}	
		   			if (parseInt(theForm.supervisionLengthMonths.value) != 0){
		   				entryCtr++;
		   			}	
		   			if (parseInt(theForm.supervisionLengthDays.value) != 0){
		   				entryCtr++;
		   			}
		   			if (entryCtr > 1){
		   				alert("Supervision Length combination entry of Years, Months and Days only allowed for Determinate Sentencing order.");
		   				theForm.supervisionLengthYears.focus();
		   				return false;
		   			}	
		   		}			   		
//		   		addNumericValidation("confinementLengthYears", "Confinement Length in Years must be an integer and nonnegative.");
//		   		addNumericValidation("confinementLengthMonths", "Confinement Length in Months must be an integer and nonnegative.");
//		   		addNumericValidation("confinementLengthDays", "Confinement Length in Days must be an integer and nonnegative.");		   		
//		   		addNumericValidation("supervisionLengthYears", "Supervision Length in Years must be an integer and nonnegative.");
//		   		addNumericValidation("supervisionLengthMonths", "Supervision Length in Months must be an integer and nonnegative.");
//		   		addNumericValidation("supervisionLengthDays", "Supervision Length in Days must be an integer and nonnegative.");
		   		customValRequired("caseSupervisionBeginDateAsString", "Supervision Begin Date is required.","");
		   		customValRequired("caseSupervisionEndDateAsString", "Supervision End Date is required.","");
		   		addMMDDYYYYDateValidation("caseSupervisionBeginDateAsString","Supervision Begin Date must be in date format ");
		   		addMMDDYYYYDateValidation("caseSupervisionEndDateAsString","Supervision End Date must be in date format ");
		   	}
	   	} 
	   	if (theForm.dispositionTypeId.type == 'select-one'){  	
		   	if(theSelect.options[theSelect.selectedIndex].value == "<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>"){
			   	customValRequired("caseSupervisionBeginDateAsString", "Pretrial Begin Date is required.","");
		   		customValRequired("caseSupervisionEndDateAsString", "Pretrial End Date is required.","");
			   	addMMDDYYYYDateValidation("caseSupervisionBeginDateAsString","Pretrial Begin Date must be in date format ");
		   		addMMDDYYYYDateValidation("caseSupervisionEndDateAsString","Pretrial End Date must be in date format ");
		   	}
	   	} else {
		   	customValRequired("caseSupervisionBeginDateAsString", "Pretrial Begin Date is required.","");
	   		customValRequired("caseSupervisionEndDateAsString", "Pretrial End Date is required.","");
		   	addMMDDYYYYDateValidation("caseSupervisionBeginDateAsString","Pretrial Begin Date must be in date format ");
	   		addMMDDYYYYDateValidation("caseSupervisionEndDateAsString","PretrialEnd Date must be in date format ");
	   	}   	  	
	   	
		var vcResult = validateCustomStrutsBasedJS(theForm);
		if (vcResult == true){
			if (theForm.comments.value.indexOf('\\') >= 0){
				alert('Comments for Printed Order contains invalid symbols.');
				theForm.comments.focus();
				vcResult = false;
			}
		}
	
		return vcResult; 
	}
	
	function enableOrderTitles(theForm){
		theForm.orderTitleId.disabled=false;
		return true;
	}
	
	function showOrderTitle(theForm){
		if(theForm.versionTypeId.value == 'M'){
			show("orderTitleRow", 0);
		}else {
			show("orderTitleRow", 1, "row");
		}
	}
	
	function createVersionReason(theForm)
	{
		if (theForm.orderIsMigrated.value == "true") {
			if(theForm.versionTypeId.value == 'A' || theForm.versionTypeId.value == 'M')
			{
				document.getElementById('versionTypeId').colSpan=1;
				document.getElementById('versionLbl').className="formDeLabel";
				document.getElementById('versionBox').className="formDe";
				if(theForm.versionNum.value=='0'){
					theForm.versionNum.value="";
				}
			}
			else{
				theForm.versionNum.value="";
				show('versionLbl',0);
				show('versionBox',0);
				document.getElementById('versionTypeId').colSpan=3;
			}
		}
	}

	function createModReason(theForm)
	{

		if(theForm.versionTypeId.value == 'A' || theForm.versionTypeId.value == 'M')
		{
			//clearAllValArrays();
			show('modtextarea',1,'row');
			show('modprompt',1,'row');	
			//  addDB2FreeTextValidation('casenotes','Modification Reason  contains invalid symbols. Invalid symbols are: % (percent) and _ (underscore).',null);
		} else {
			show('modtextarea',0,'row');
			show('modprompt',0,'row');	
		}			
	}

	//this function renders certain fields based on what the disposition type is
	function checkDisp(theSelect){
		var dispTypeId = document.getElementsByName("dispositionTypeId");
		if (dispTypeId[0].type == 'select-one'){
			if(theSelect.options[theSelect.selectedIndex].value == "<%=UIConstants.DISPOSITION_TYPE_CODE_DEFERRED%>"){
				show("confinementLengthRow", 0);
				show("supervisionLengthRow", 1, "row");
				show("supervisionBeginDate", 1, "row");
				show("supervisionEndDate", 1, "row");
				show("beginDateHeaderSupervision", 1, "row");
				show("endDateHeaderSupervision", 1, "row");
				show("beginDateHeaderPretrial", 0, "row");
				show("endDateHeaderPretrial", 0, "row");
				}else	if(theSelect.options[theSelect.selectedIndex].value == "<%=UIConstants.DISPOSITION_TYPE_CODE_PROBATION%>"){
					show("confinementLengthRow", 1, "row");
					show("supervisionLengthRow", 1, "row");
					show("supervisionBeginDate", 1, "row");
					show("supervisionEndDate", 1, "row");
					show("beginDateHeaderSupervision", 1, "row");
					show("endDateHeaderSupervision", 1, "row");
					show("beginDateHeaderPretrial", 0, "row");
					show("endDateHeaderPretrial", 0, "row");
				}else	if(theSelect.options[theSelect.selectedIndex].value == "<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>"){
					show("supervisionBeginDate", 1);
					show("confinementLengthRow", 0);
					show("supervisionLengthRow", 0);
					show("supervisionEndDate", 1);
					show("beginDateHeaderSupervision", 0, "row");
					show("endDateHeaderSupervision", 0, "row");
					show("beginDateHeaderPretrial", 1, "row");
					show("endDateHeaderPretrial", 1, "row");
				}else{
					show("confinementLengthRow", 0);
					show("supervisionLengthRow", 0);
					show("beginDateHeaderSupervision", 0, "row");
					show("endDateHeaderSupervision", 0, "row");
					show("beginDateHeaderPretrial", 0, "row");
					show("endDateHeaderPretrial", 0, "row");
					show("supervisionBeginDate", 0, "row");
					show("supervisionEndDate", 0, "row");
				}
			checkOT(document.forms[0]);
		} else {
// dispositionTypeid.type = "hidden" and is pretrial intervention  			
			show("supervisionBeginDate", 1);
			show("confinementLengthRow", 0);
			show("supervisionLengthRow", 0);
			show("supervisionEndDate", 1);
			show("beginDateHeaderSupervision", 0, "row");
			show("endDateHeaderSupervision", 0, "row");
			show("beginDateHeaderPretrial", 1, "row");
			show("endDateHeaderPretrial", 1, "row");	
			checkOT(document.forms[0]);		
		}	
	}

	function calculateEndDate(theForm){
		theForm.supervisionLengthYears.value = trimAll(theForm.supervisionLengthYears.value);
		theForm.supervisionLengthMonths.value = trimAll(theForm.supervisionLengthMonths.value);
		theForm.supervisionLengthDays.value = trimAll(theForm.supervisionLengthDays.value);
		if(theForm.supervisionLengthYears.value=="" && theForm.supervisionLengthMonths.value == "" && theForm.supervisionLengthDays.value==""){
			alert("Supervision Length must have a value for calculation.");
	  		theForm.supervisionLengthYears.focus();
	   		return false;
	   	}
	   	var supLenYears = trimAll( theForm.supervisionLengthYears.value.replace(/0/g,"") );
	   	var supLenMonths = trimAll( theForm.supervisionLengthMonths.value.replace(/0/g,"") );
	   	var supLenDays = trimAll( theForm.supervisionLengthDays.value.replace(/0/g,"") );
		if (supLenYears == "" && supLenMonths == "" && supLenDays == ""){
	   		alert("Supervision Length must be greater than zero for calculation.");
	  		theForm.supervisionLengthYears.focus();
	  		return false;
	   	}
		clearAllValArrays();
		addNumericValidation("supervisionLengthYears", "Supervision Length in Years must be an integer and nonnegative for calculation.");
		addNumericValidation("supervisionLengthMonths", "Supervision Length in Months must be an integer and nonnegative for calculation.");
		addNumericValidation("supervisionLengthDays", "Supervision Length in Days must be an integer and nonnegative for calculation.");
		customValRequired("caseSupervisionBeginDateAsString", "Supervision Begin Date is required for calculation.","");
		addMMDDYYYYDateValidation("caseSupervisionBeginDateAsString","Supervision Begin Date must be in mm/dd/yyyy date format ");
		var noErrors = validateCustomStrutsBasedJS(theForm);
		if (noErrors == true){
			begDate = new Date(theForm.caseSupervisionBeginDateAsString.value);
			if (supLenYears != ""){
				begDate.setFullYear(begDate.getFullYear() + Number(theForm.supervisionLengthYears.value));
				begDate.setDate(begDate.getDate() - 1); 
			}
			if (supLenMonths != ""){
				yrs = Number(theForm.supervisionLengthMonths.value) / 12;
				mons = Number(theForm.supervisionLengthMonths.value) % 12;
				begDate.setFullYear(begDate.getFullYear() + yrs);
				begDate.setMonth(begDate.getMonth() + mons);
				begDate.setDate(begDate.getDate() - 1);
			}
			if (supLenDays != ""){
				days = Number(theForm.supervisionLengthDays.value);
				begDate.setDate(begDate.getDate() + days);
			}
			var mm = parseInt(begDate.getMonth() + 1).toString();
			var dd = begDate.getDate().toString();
			if (mm.length == 1){
				mm = "0" + mm;
			}
			if (dd.length == 1){
				dd = "0" + dd;
			}
			theForm.caseSupervisionEndDateAsString.value = mm + "/" + dd + "/" + begDate.getFullYear();
		}	
	}

	function checkOT(theForm){ 
		if (theForm.versionTypeId.options[theForm.versionTypeId.selectedIndex].value == "<%=PDCodeTableConstants.VERSION_TYPE_ID_ORIGINAL%>"){
			if (theForm.orderTitleId.options[theForm.orderTitleId.selectedIndex].text.indexOf("DETERMINATE SENTENCING") > -1){
				show ("allowCalc", 0);
				show ("juvCourts", 1);
				show ("juvSupervisionLengthRow", 1);
				show ("juvSupervisionBeginDate", 1);
			} else {
				show ("allowCalc", 1);
				if (theForm.dispositionTypeId.type == 'select-one'){
					if (theForm.dispositionTypeId.options[theForm.dispositionTypeId.selectedIndex].value == "<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>"){
						show ("allowCalc", 0);
					}	
				}
				if (theForm.dispositionTypeId.type == 'hidden'){
					show ("allowCalc", 0);
				}	
				theForm.juvCourtId.selectedIndex = 0;
				theForm.juvSupervisionLengthYears.value = "00"; 
				theForm.juvSupervisionLengthMonths.value = "00";
				theForm.juvSupervisionLengthDays.value = "000";
				theForm.juvSupervisionBeginDateAsString.value = "";
				show ("juvCourts", 0);	
				show ("juvSupervisionLengthRow", 0);
				show ("juvSupervisionBeginDate", 0);
			}
			
		} else {
			show ("allowCalc", 0);
		}
		if (theForm.orderTitleId.options[theForm.orderTitleId.selectedIndex].text.indexOf("DETERMINATE SENTENCING") > -1){
			show ("juvCourts", 1);
			show ("juvSupervisionLengthRow", 1);
			show ("juvSupervisionBeginDate", 1);
		} else {
			theForm.juvCourtId.selectedIndex = 0;
			theForm.juvSupervisionLengthYears.value = "00"; 
			theForm.juvSupervisionLengthMonths.value = "00";
			theForm.juvSupervisionLengthDays.value = "000";
			theForm.juvSupervisionBeginDateAsString.value = "";
			show ("juvCourts", 0);	
			show ("juvSupervisionLengthRow", 0);
			show ("juvSupervisionBeginDate", 0);
		}
		if (theForm.myAction.value == 'update'){
			if (theForm.orderIsMigrated.value == "true" && theForm.cdiValue.value == "003"){
				if (theForm.versionTypeId.options[theForm.versionTypeId.selectedIndex].value == "<%=PDCodeTableConstants.VERSION_TYPE_ID_AMMENDED%>" ||
					theForm.versionTypeId.options[theForm.versionTypeId.selectedIndex].value == "<%=PDCodeTableConstants.VERSION_TYPE_ID_MODIFIED%>"){
						show ("origJudgeName", 1);
				} else {
					show ("origJudgeName", 0);
				}
			}				
		}	
	}
</script>
 
</head>

<bean:define id="limitedSupervisonPeriodCaption" name="supervisionOrderForm" property="limitedSupervisonPeriodCaption" type="java.lang.String"/>

<body topmargin="0" leftmargin="0" onload="showOrderTitle(document.forms[0]);createModReason(document.forms[0]);createVersionReason(document.forms[0]);showDate(document.forms[0].limitedSupervisonPeriod,document.forms[0]);checkDisp(document.forms[0].dispositionTypeId);checkOT(document.forms[0])" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySupervisionOrderSelectSugOrder" target="content">
<div align="center">
<input type="hidden" name="myAction" value="<bean:write name="supervisionOrderForm" property="action" />">
<input type="hidden" name="orderIsMigrated" value="<bean:write name="supervisionOrderForm" property="isMigratedOrder" />">
<input type="hidden" name="cdiValue" value="<bean:write name="supervisionOrderForm" property="cdi" />">  
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
  	</tr>
  	<tr>
    	<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
					<!--tabs start-->
					<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="processOrderTab"/>
					</tiles:insert>		
					<!--tabs end--> 
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						
						<!-- BEGIN HEADING TABLE -->
							<table width="98%">
								<tr>
									<td align="center" class="header">
										<logic:equal name="supervisionOrderForm" property="action" value="create">
											<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|7">
											<bean:message key="prompt.create" />
										</logic:equal>									
										<logic:equal name="supervisionOrderForm" property="action" value="update">
										    <logic:notEqual name="supervisionOrderForm" property="orderStatus" value="INCOMPLETE">
											  <input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|16">
											  <bean:message key="prompt.update" />
										   </logic:notEqual>
										   <logic:equal name="supervisionOrderForm" property="orderStatus" value="INCOMPLETE">
											  <input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|7">
											  <bean:message key="prompt.create" />
										   </logic:equal>											
										</logic:equal>
										<logic:equal name="supervisionOrderForm" property="isMigratedOrder" value="true">	
											<bean:message key="title.migrated" /> <bean:message key="title.supervisionOrder" />&nbsp;-&nbsp;
										</logic:equal>	
										<logic:equal name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">	
											<bean:message key="prompt.pretrialInterventionOrder" />&nbsp;-&nbsp;
										</logic:equal>											
										<bean:message key="title.prepareOrderPresentation" />
									</td>
								 </tr>						 						  
							</table>									
						<!-- END HEADING TABLE -->
						<!-- BEGIN ERROR TABLE -->
							<table width="98%" align="center">															
								<tr>
									<td align="center" class="errorAlert"><html:errors></html:errors></td>
								</tr>		
							</table>
						<!-- END ERROR TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td><ul>
										<li>Enter Required Fields and Click Next.</li>
									</ul></td>
								</tr>
								<tr>
									<td class="required"><bean:message key="prompt.3.diamond"/>&nbsp;<bean:message key="prompt.requiredFieldInstruction"/>&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction"/></td>												
								</tr>
							</table>
						<!-- END INSTRUCTION TABLE -->                      
						<!-- BEGIN DETAIL HEADER TABLE -->
																									
							<tiles:insert page="caseOrderHeaderTile.jsp" flush="true"></tiles:insert>
						
						<!-- END DETAIL HEADER TABLE -->
							<br>
						<!-- BEGIN ORDER PRESENTATION TABLE -->                      
							<table width="98%" border="0" cellspacing="0" class="borderTableBlue">
								<tr class="detailHead">
									<td class="detailHead"><bean:message key="prompt.orderPresentation" /></td>				                        	
									<td align="right"><img src="/<msp:webapp/>images/step_1.gif"></td>				                          
								</tr>
								<tr>
									<td colspan="2">
										<table width="100%" cellpadding="2" cellspacing="1" border="0">
											<tr>
												<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.versionType" /></td>
												<td class="formDe">
												
												<html:select property="versionTypeId" size="1" onchange="createVersionReason(this.form);createModReason(this.form);showOrderTitle(this.form);checkOT(this.form)">
													<html:option value=""><bean:message key="select.generic" /></html:option>
										 	<%--  		<logic:equal name="supervisionOrderForm" property="isMigratedOrder" value="false"> --%>
										 			<logic:equal name="supervisionOrderForm" property="action" value="create">
														<html:option value="<%=PDCodeTableConstants.VERSION_TYPE_ID_ORIGINAL%>">ORIGINAL</html:option>
												 	</logic:equal> 
													<html:optionsCollection name="supervisionOrderForm" property="versionTypeDropDownList" value="code" label="description" />
												</html:select>
												
												</td>
												<td  nowrap width="10%" align="right"  class="hidden" id="versionLbl">
													<bean:message key="prompt.3.diamond"/><bean:message key="title.version#" />
												</td>
												<td  width="10%" class="hidden" id="versionBox">
													<html:text property="versionNum" size="3" maxlength="3"/>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.specialCourt" /></td>
												<td class="formDe" colspan="3">
													<html:select property="specialCourtCd" size="1">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection name="supervisionOrderForm" property="specialCourtCds" value="code" label="description" />  
													</html:select>
												</td>
											</tr>
											<tr id="orderTitleRow">
												<td class="formDeLabel"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.orderTitle" /></td>
												<td class="formDe" colspan="3">
													<html:select property="orderTitleId" size="1" onchange="checkOT(this.form)">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:optionsCollection name="supervisionOrderForm" property="orderTitleList" value="printTemplateId" label="orderTitle" />
													</html:select>
												</td>
											</tr>
 											<tr id="juvCourts" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.juvenile" /> <bean:message key="prompt.court" /></td>
												<td class="formDe" colspan="3">
													<html:select property="juvCourtId" size="1">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:optionsCollection name="supervisionOrderForm" property="juvCourts" value="codeId" label="description" />
													</html:select>
												</td>
											</tr> 
							 				<tr class="hidden" id="juvSupervisionLengthRow">
												<td class="formDeLabel" colspan="1"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.juvenile" /> <bean:message key="prompt.supervisionLength"/></td>
												<td class="formDe" colspan="3">
													<html:text property="juvSupervisionLengthYears" size="2" maxlength="2"/> Years
													<html:text property="juvSupervisionLengthMonths" size="2" maxlength="2"/> Months
													<html:text property="juvSupervisionLengthDays" size="3" maxlength="3"/> Days
												</td>
											</tr>
											<tr class="hidden" id="juvSupervisionBeginDate">
												<td class="formDeLabel" nowrap="nowrap" colspan="1">
													<bean:message key="prompt.3.diamond"/><bean:message key="prompt.juvenile" /> <bean:message key="prompt.supervisionBeginDate" />
												</td>
												<td class="formDe" colspan="3">
													<html:text name="supervisionOrderForm" property="juvSupervisionBeginDateAsString" maxlength="10" size="10" />
														<A HREF="#" onClick="cal1.select(document.forms[0].juvSupervisionBeginDateAsString,'anchor0','MM/dd/yyyy'); return false;" NAME="anchor0" ID="anchor0" border="0"><bean:message key="prompt.3.calendar"/></A>
												</td>                                			
											</tr>   							
											<tr>
									        	<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.printedOffenseDescription" /></td>
			            						<td class="formDe" colspan="3"><html:text property="printedOffenseDesc" maxlength="75" size="75"/>
			            						</td>
											</tr>								
											<tr>
									        	<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.3.diamond"/>Printed Name<%--<bean:message key="prompt.printedName" />--%></td>
			            						<td class="formDe" colspan="3"><html:text property="printedName" maxlength="75" size="75" /></td>
											</tr>
											<logic:equal name="supervisionOrderForm" property="isPretrialInterventionOrder" value="false">
												<tr>
													<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.dispositionType"/></td>
													<td class="formDe" colspan="3">
														<html:select property="dispositionTypeId" onchange="checkDisp(this)">
															<html:option value=""><bean:message key="select.generic" /></html:option>
															<html:optionsCollection name="supervisionOrderForm" property="dispositionTypes" value="code" label="description" />
														</html:select>
													</td>
												</tr>
											</logic:equal>
											<logic:equal name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">
												<tr>
													<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.dispositionType"/></td>
													<td class="formDe" colspan="3">
														<bean:write name="supervisionOrderForm" property="dispositionType" />
														<input type="hidden" name="dispositionTypeId" value="<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>">
													</td>
												</tr>
											</logic:equal>
											
											<tr>
												<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.fineAmount"/></td>
												<td class="formDe" colspan="3"><html:text name="supervisionOrderForm" property="fineAmountTotal" maxlength="9" size="9" /></td>
											</tr>

											<tr class="hidden" id="confinementLengthRow">
												<td class="formDeLabel" ><bean:message key="prompt.3.diamond"/><bean:message key="prompt.confinementLength"/> </td>
												<td class="formDe" colspan="3">
													<html:text property="confinementLengthYears" size="2" maxlength="2"/> Years
													<html:text property="confinementLengthMonths" size="2" maxlength="2"/> Months
													<html:text property="confinementLengthDays" size="3" maxlength="3"/> Days
												</td>
											</tr>
											<tr class="hidden" id="supervisionLengthRow">
												<td class="formDeLabel" ><bean:message key="prompt.3.diamond"/><bean:message key="prompt.supervisionLength"/></td>
												<td class="formDe" colspan="3">
													<html:text property="supervisionLengthYears" size="2" maxlength="2"/> Years
													<html:text property="supervisionLengthMonths" size="2" maxlength="2"/> Months
													<html:text property="supervisionLengthDays" size="3" maxlength="3"/> Days
												</td>
											</tr>
											<tr class="hidden" id="supervisionBeginDate">
												<td class="formDeLabel" nowrap="nowrap">
													<bean:message key="prompt.3.diamond"/><span id="beginDateHeaderSupervision" class="hidden"><bean:message key="prompt.supervisionBeginDate" />
													</span>
													<span id="beginDateHeaderPretrial" class="hidden"><bean:message key="prompt.pretrial"/>&nbsp;<bean:message key="prompt.beginDate"/>
													</span>
												</td>
												<td class="formDe" colspan="3">
													<SCRIPT LANGUAGE="JavaScript" ID="js1">
														var cal1 = new CalendarPopup();
														cal1.showYearNavigation();
													</SCRIPT>
													<html:text name="supervisionOrderForm" property="caseSupervisionBeginDateAsString" maxlength="10" size="10" />
														<A HREF="#" onClick="cal1.select(document.forms[0].caseSupervisionBeginDateAsString,'anchor1','MM/dd/yyyy'); return false;" NAME="anchor1" ID="anchor1" border="0"><bean:message key="prompt.3.calendar"/></A>
													<script>
														if (document.forms[0].caseSupervisionBeginDateAsString.value == "" )
														{
															document.forms[0].caseSupervisionBeginDateAsString.value = getCurrentDate();
														}
													</script>
												</td>                                			
											</tr>
											<tr class="hidden" id="supervisionEndDate">
												<td class="formDeLabel" nowrap="nowrap">
													<bean:message key="prompt.3.diamond"/><span id="endDateHeaderSupervision" class="hidden"><bean:message key="prompt.supervisionEndDate" /> <div>(If extended, enter new expiration date)</div>
														</span>
													<span id="endDateHeaderPretrial" class="hidden">
														<bean:message key="prompt.pretrial"/>&nbsp;<bean:message key="prompt.endDate"/>
													</span>
												</td>
												<td class="formDe" colspan="3" >
													<table cellpadding="0">
														<tr>
															<td>
															<html:text name="supervisionOrderForm" property="caseSupervisionEndDateAsString" maxlength="10" size="10" />
																<A HREF="#" onClick="cal1.select(document.forms[0].caseSupervisionEndDateAsString,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2" border="0"><bean:message key="prompt.3.calendar"/></A>
															</td>
															<td id="allowCalc" class="hidden">
																<logic:equal name="supervisionOrderForm" property="isMigratedOrder" value="false">
														<%--		<html:submit property="submitAction" onclick="return calculateEndDate(this.form) && disableSubmit(this, this.form)"><bean:message key="button.calculate" /></html:submit> --%>
																	<input type="button" name="calc" value=<bean:message key="button.calculate" /> onclick="return calculateEndDate(this.form)" />
																</logic:equal>	
															</td>
														</tr>
													</table>		
												</td>
											</tr>

							<!-- PLEA field and Original Judge Name for CSCD agency only -->
											<logic:equal name="supervisionOrderForm" property="agencyId" value="<%=UIConstants.CSC%>">
												<tr>				                          	
													<td class="formDeLabel"><bean:message key="prompt.plea" /></td>
													<!-- Allow to enter PLEA field for Create Original order only-->
											<%-- 		<logic:notEqual name="supervisionOrderForm" property="action" value="update"> --%>
														<td class="formDe" colspan="3">
															<html:select property="pleaId" size="1">
																<html:option value=""><bean:message key="select.generic" /></html:option>
																<html:optionsCollection name="supervisionOrderForm" property="pleas" value="code" label="description" /> 
															</html:select>
														</td>
											<%-- 		</logic:notEqual>			
													<logic:equal name="supervisionOrderForm" property="action" value="update">
														<logic:equal name="supervisionOrderForm" property="isMigratedOrder" value="false">
															<logic:equal name="supervisionOrderForm" property="versionTypeId" value="O">
														<!-- Display PLEA field only for Original order with Active status -->
																<logic:equal name="supervisionOrderForm" property="orderStatusId" value="A">
																	<td class="formDe" colspan="3"><bean:write name="supervisionOrderForm" property="plea" /></td>
																</logic:equal>
													<!-- Allow to modify PLEA field for Original order with other status -->
																<logic:notEqual name="supervisionOrderForm" property="orderStatusId" value="A" >
																	<td class="formDe" colspan="3">
																		<html:select property="pleaId" size="1">
																			<html:option value=""><bean:message key="select.generic" /></html:option>
																			<html:optionsCollection name="supervisionOrderForm" property="pleas" value="code" label="description" /> 
																		</html:select>
																	</td>		
																</logic:notEqual>
															</logic:equal>
												<!-- Display PLEA field only for other order -->
															<logic:notEqual name="supervisionOrderForm" property="versionTypeId" value="O">
																<td class="formDe" colspan="3"><bean:write name="supervisionOrderForm" property="plea" /></td>		
															</logic:notEqual>
														</logic:equal>	
														<logic:equal name="supervisionOrderForm" property="isMigratedOrder" value="true">
															<td class="formDe" colspan="3">
																<html:select property="pleaId" size="1">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																	<html:optionsCollection name="supervisionOrderForm" property="pleas" value="code" label="description" /> 
																</html:select>
															</td>		
														</logic:equal>
													</logic:equal>  --%>	<!-- end migrated order = false -->
												</tr>
											</logic:equal>
												
											<tr id="origJudgeName" class="hidden">				                          	
												<td class="formDeLabel">
													<logic:equal name="supervisionOrderForm" property="cdi" value="003">
														<bean:message key="prompt.3.diamond"/><bean:message key="prompt.originalJudge" />
													</logic:equal>	
														<logic:notEqual name="supervisionOrderForm" property="cdi" value="003">
													<bean:message key="prompt.originalJudge" />
													</logic:notEqual>
												</td>
												<td class="formDe" colspan="3">
												<table>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.firstName" /></td>
														<td class="formDeLabel"><bean:message key="prompt.lastName" /></td>
													</tr>
													<tr>
														<td>
															<html:text name="supervisionOrderForm" property="originalJudgeFirstName" maxlength="25" size="25" />
														</td>
														<td>
															<html:text name="supervisionOrderForm" property="originalJudgeLastName" maxlength="30" size="30" />
														</td>
													</tr>
												</table>
												</td>
											</tr>
											<logic:equal name="supervisionOrderForm" property="showOrigJudgeInput" value="true">
												<script type="text/javascript">
													show("origJudgeName", 1);
												</script>	
											</logic:equal>								
										</logic:equal>				
										
										<tr id="modprompt" class="hidden">
											<td class="formDeLabel" colspan="4"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.summaryOfchanges"/> for Printed Order (Max Characters Allowed: 500)</td>
										</tr>
										<tr id="modtextarea" class="hidden">
											<td class="formDe" colspan="4">
												<textarea rows="4" style="width:100%" name="summaryOfChanges" onmouseout="textLimit( this, 500 )" onkeyup="textLimit( this, 500 )"><bean:write name="supervisionOrderForm" property="summaryOfChanges" /></textarea>
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" colspan="4"><bean:message key="prompt.comments"/> for Printed Order</td>
										</tr>
										<tr>
											<td class="formDe" colspan="4">
												<html:text name="supervisionOrderForm" property="comments" maxlength="80" size="80" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<!-- END ORDER PRESENTATION TABLE -->
					<br>                     
						<!-- BEGIN BUTTON TABLE -->
				<table border="0" width="100%">											
					<tr>
						<td align="center">
							<html:submit property="submitAction" onclick="disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp; 
							<html:submit property="submitAction" onclick="return (validateField(this.form) && checkSupervisionDates(this.form) && enableOrderTitles(this.form) && disableSubmit(this, this.form));"><bean:message key="button.next"></bean:message></html:submit>&nbsp; 
							<html:reset><bean:message key="button.reset"></bean:message></html:reset>&nbsp; 
							<html:submit property="submitAction" onclick="disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
						</td>
					</tr>											
				</table>
						<!-- END BUTTON TABLE -->
               		</td>
	            </tr>
	        </table>
		</td>
    </tr>
</table>
</div>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>