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
<!-- 07/28/2008	 Richard Young  - ER#53069 changed wording on Casenote prompt. Also logic tags around the next buttons-->
<!-- 09/08/2008	 Richard Young  - Defect#53776 changed length of Casenote field from 2500 to 500-->
<!-- 10/30/2008	 RYoung         - Defect#55115 Summary would not show when amending or modifying-->
<!-- 02/24/2009  C Shimek       - #57462 added Add/Remove Conditions button for update flow only -->
<!-- 06/05/2009  C Shimek       - #60104 added Calculate button and script -->
<!-- 06/23/2009  C Shimek       - #60524 added plea value validation to validateField() -->
<!-- 08/07/2009  C Shimek       - #61094 convert Plea from input text to drop down list --> 
<!-- 08/14/2009  C Shimek       - #61438 added Comments for Printed Order -->
<!-- 08/17/2009  C Shimek       - #61534 not reproducible but corrected scripting for Calculate button to handle new Order Title values that contain the value "Determinate Sentencing" -->
<!-- 08/24/2009  C Shimek       - #61664 added script and code to display juvenile court drop down for determinate sentencing order title -->
<!-- 08/27/2009  D Gibler	    - #61748 added special courts -->
<!-- 08/28/2009  L Deen		    - #61746 revised layout for added special courts -->
<!-- 09/17/2009  C Shimek       - #62008 added logic tags around order title for Draft updates and removed unnecessary checkForMod() script and call in onload -->
<!-- 09/18/2009  C Shimek       - #62062 removed logic tag around order title for versionTypeChangeAllowed to allow active original order to dispaly -->
<!-- 09/23/2009  C Shimek       - #62146 revised validatoin on offense desc to also allow <> and ? -->
<!-- 09/28/2009  C Shimek       - #62228 reivsed populateOderTitles() to default amended version type from blank to value on supervisionOrderFrom  -->
<!-- 10/08/2009  C Shimek       - #61837 revised printed name validation to not allow / or & -->
<!-- 10/19/2009  C Shimek       - #62093 add validation to comments for printed order to not allow backslash -->
<!-- 10/26/2009  C Shimek       - #60771 revised offense code to display only, removed Validate button and Find Offense Code href -->
<!-- 10/27/2009  C Shimek       - #62664 revised Calculate button to use local js instead on submitAction -->
<!-- 11/03/2009  C Shimek       - #62713 revised end date calculation script to match calc examples in original ER #60104 -->
<!-- 11/03/2009  C Shimek       - #62373 revised scripting for modified orders to not display and/or validate order title field -->
<!-- 11/09/2009  C Shimek       - #62754 revised scripting for update modified orders to display judges name input for felony cases -->
<!-- 11/12/2009  C Shimek       - #60771 complete remove offense code display in updte block, ER solution changed -->
<!-- 12/01/2009  C Shimek       - #62433 reopened - corrected juvenile supervision begin date validation to use correct field string value -->
<!-- 12/02/2009  C Shimek       - #62962 added = as valid printed offense description character -->
<!-- 12/29/2009  C Shimek       - #63272 remove logic restriction to allow Plea to be updateable for any flow -->
<!-- 03/04/2010  C Shimek       - #64228 revised supervision and confinement length validation based on order title selection of Determinate Sentencing -->
<!-- 03/05/2010  D Gibler       - Added check for orderTitleId of blank for amended to keep from blank space being selected above please select. -->
<!-- 03/10/2010  C Shimek       - #64351 added edit to Printed Office Description to require space after symbol < and a space before symbol > -->
<!-- 07/13/2010  C Shimek       - #64607 added edit hide Original Judge input fields for Active Amended orders -->
<!-- 07/29/2010  D Williamson	- ER#55920 Added Text Counter to Summary of Changes field.  -->
<!-- 10/19/2010  C Shimek       - #67921 removed "intervention" from pretrial begin and end date labels -->
<!-- 04/04/2011  L Deen		    - #67870 changed Printed Offense Description length from 50 to 75 to match maxlength & Reqs -->
<!-- 09/23/2011  D Williamson   - #70192 Removed change made 07/21/2009 for #62541 per requirements -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
 
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@page import="naming.UIConstants"%>
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
<title><bean:message key="title.heading" /> - processSupervisionOrder/orderPresentation.jsp</title>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="supervisionOrderForm" />

<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

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
			if(theForm.versionTypeId.selectedIndex == "")
			{		
	     		alert("Please select Version Type.");
	    		theForm.versionTypeId.focus();
	    		return false;
	   		}
	   	}

		if (typeof(theForm.orderTitleId) != "undefined"){
			if (theForm.versionTypeId.value != "M"){
				if(theForm.orderTitleId.selectedIndex == 0)
				{		
		    	 	alert("Please select Order Title.");
		    		theForm.orderTitleId.focus();
		     		return false;
			   	}
			   	if (theForm.orderTitleId.options[theForm.orderTitleId.selectedIndex].text.indexOf("DETERMINATE SENTENCING") >= 0){
					editJuvFields = true;
				   	if (theForm.juvCourtId.selectedIndex == 0){
				     	alert("Please select Juvenile Court.");
			    		theForm.juvCourtId.focus();
			    		return false;
			   		}   	     	
		   		}
			}	
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
	   			addNumericValidation("juvSupervisionLengthYears", "Juvenile Supervision Length in Years must be a number of zero or more.");
			}
			if (theForm.supervisionLengthMonths.value > ""){
	   			addNumericValidation("juvSupervisionLengthMonths", "Juvenile Supervision Length in Months must be a number of zero or more.");
			}	
			if (theForm.supervisionLengthDays.value > ""){
	   			addNumericValidation("juvSupervisionLengthDays", "Juvenile Supervision Length in Days must be a number of zero or more.");
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
            
	   	if(theForm.versionTypeId.value == 'A' || theForm.versionTypeId.value == 'M')
		{
	   	 	addDB2FreeTextValidation('summaryOfChanges','Summary of Changes contains invalid symbols. Invalid symbols are: % (percent) and _ (underscore).',null);
	   	 	customValMaxLength ('summaryOfChanges','Summary of Changes cannot be more than 500 characters.',500);
		}

		customValRequired("printedOffenseDesc", "Printed Offense Description is required.","");
		var prtOffRegExp = /^[a-zA-Z0-9\.\\\\';,()\x26\x2f\x3c\x3e\x24\x3d\-][a-zA-Z0-9 \.\\\\';,()\x26\x2f\x3c\x3e\x24\x3d\-]*$/;
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

	   	var theSelect=theForm.dispositionTypeId;
	   	
	   	customValRequired("dispositionTypeId", "Disposition Type is required.","");
	   	
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
	   		
//	   		customValRequired("supervisionLengthYears", "Supervision Length in Years is required.","");
//	   		customValRequired("supervisionLengthMonths", "Supervision Length in Months is required.","");
//	   		customValRequired("supervisionLengthDays", "Supervision Length in Days is required.","");
//			if (theForm.supervisionLengthYears.value > ""){
//	   			addNumericValidation("supervisionLengthYears", "Supervision Length in Years must be an integer and nonnegative.");
//			}
//			if (theForm.supervisionLengthMonths.value > ""){
//	   			addNumericValidation("supervisionLengthMonths", "Supervision Length in Months must be an integer and nonnegative.");
//			}	
//			if (theForm.supervisionLengthDays.value > ""){
//	   			addNumericValidation("supervisionLengthDays", "Supervision Length in Days must be an integer and nonnegative.");
//			}
	   		customValRequired("caseSupervisionBeginDateAsString", "Supervision Begin Date is required.","");
	   		customValRequired("caseSupervisionEndDateAsString", "Supervision End Date is required.","");
	   		addMMDDYYYYDateValidation("caseSupervisionBeginDateAsString","Supervision Begin Date must be in date format ");
	   		addMMDDYYYYDateValidation("caseSupervisionEndDateAsString","Supervision End Date must be in date format ");

	   	}

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
	   		
	   		customValRequired("caseSupervisionBeginDateAsString", "Supervision Begin Date is required.","");
	   		customValRequired("caseSupervisionEndDateAsString", "Supervision End Date is required.","");
	   		addMMDDYYYYDateValidation("caseSupervisionBeginDateAsString","Supervision Begin Date must be in date format ");
	   		addMMDDYYYYDateValidation("caseSupervisionEndDateAsString","Supervision End Date must be in date format ");
	   	}

	   	if(theSelect.options[theSelect.selectedIndex].value == "<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>"){
	   		customValRequired("caseSupervisionBeginDateAsString", "Pretrial Begin Date is required.","");
	   		customValRequired("caseSupervisionEndDateAsString", "Pretrial End Date is required.","");
	   		addMMDDYYYYDateValidation("caseSupervisionBeginDateAsString","Pretrial Begin Date must be in date format ");
	   		addMMDDYYYYDateValidation("caseSupervisionEndDateAsString","Pretrial End Date must be in date format ");
	   	}
			
		<%--JIMS200059358 validation for original judge only needed when original judge name does not already exist (migrated orders)--%>
		if (document.getElementById("origJudgeName").className == "visible"){
			<logic:equal name="supervisionOrderForm" property="agencyId" value="<%=UIConstants.CSC%>">
				<logic:equal name="supervisionOrderForm" property="isMigratedOrder" value="true">
					<logic:equal name="supervisionOrderForm" property="cdi" value="003">
						<logic:equal name="supervisionOrderForm" property="originalJudgeLastName" value="">			
							customValRequired("originalJudgeFirstName", "Original Judge First Name is required.","");	
							customValRequired("originalJudgeLastName", "Original Judge Last Name is required.","");						
						</logic:equal>
					</logic:equal>		
				</logic:equal>		
			</logic:equal>
		}										

	   	// notes field is required for update option only and not the Original version 
	   	if ( typeof (theForm.versionTypeId) != "undefined"){
			if (theForm.myAction.value == "update" && theForm.versionTypeId.value != "O")
		   	{		
			  // var casenotesRegex = /^[a-zA-Z0-9_ ,.'-]*$/; 
			  	customValRequired("summaryOfChanges", "Summary Of Changes for Printed Order is required.","");
		   	}
	   	}
// can not get regExp to check all except backslash to return false
//		var commentsRegExp = /[\x5c]*$/;
//        customValMask('comments','Comments for Printed Order contains invalid symbols.',commentsRegExp);
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
	function resetSupDates(theForm) {
	   	if ( typeof (theForm.versionTypeId) != "undefined"){
			if (theForm.myAction.value == "create" && theForm.versionTypeId.value == "O")
		   	{		
				theForm.caseSupervisionBeginDateAsString.value = getCurrentDate();
				theForm.caseSupervisionEndDateAsString.value = "";
		   	}
	   	}
	}
	function createModReason(theForm) {
		if ( typeof (theForm.versionTypeId) != "undefined")
		{
			if(theForm.versionTypeId.value == 'A' || theForm.versionTypeId.value == 'M')
			{
				//clearAllValArrays();
				show('modtextarea',1,'row');
				show('modprompt',1,'row');	
			}
		}
	}

	//this function renders certain fields based on what the disposition type is
	function checkDisp(theSelect){
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
		}

	// actual calculation made in action
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
		return false;
	}
	
	function checkOT(theForm){
		if (typeof(theForm.orderTitleId) != "undefined"){
			if (theForm.orderTitleId.type == "select-one"){
				if (theForm.orderTitleId.selectedIndex > 0 && theForm.orderTitleId.options[theForm.orderTitleId.selectedIndex].text.indexOf("DETERMINATE SENTENCING") >= 0){
					show ("allowCalc", 0);
					show ("juvCourts", 1);
					show ("juvSupervisionLengthRow", 1);
					show ("juvSupervisionBeginDate", 1);
				} else {
					show ("allowCalc", 1);
					if (theForm.dispositionTypeId.options[theForm.dispositionTypeId.selectedIndex].value == "<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>"){
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
		}		
	}

	function showOrderTypes(theForm){
		if (theForm.hidVersionType.value == "O" || theForm.hidOrderStatus.value == "DRAFT"){
			show("orderTypes", 1);	
			if (theForm.hidVersionType.value == "M" ){
				show("orderTypes", 0);
			}	
		}
		// added 10/08/09 issue found by Dawn
		if (theForm.hidVersionType.value == "A" && theForm.hidOrderStatus.value == "INCOMPLETE"){
			show("orderTypes", 1);	
		}	
		// added 10/21/09 defect 62541
//		if (theForm.hidVersionType.value == "A" || theForm.hidVersionType.value == "M"){
//			if (theForm.hidOrderStatus.value == "PENDING"){
//				show("orderTypes", 1);
//			}		
//		}
		// added 09/23/11 defect 70192 to correct change made 10/21/09
		if (theForm.hidVersionType.value == "A" && theForm.hidOrderStatus.value == "PENDING"){
			show("orderTypes", 1);	
			if (theForm.hidVersionType.value == "M" ){
				show("orderTypes", 0);
			}
		}		
		 
	}	
</script>
<SCRIPT LANGUAGE="JavaScript" ID="js1">
	var cal1 = new CalendarPopup();
	cal1.showYearNavigation();
</SCRIPT>
 
</head>

<bean:define id="limitedSupervisonPeriodCaption" name="supervisionOrderForm" property="limitedSupervisonPeriodCaption" type="java.lang.String"/>

<body topmargin="0" leftmargin="0" onload="showOrderTypes(document.forms[0]);showDate(document.forms[0].limitedSupervisonPeriod,document.forms[0]);createModReason(document.forms[0]);checkDisp(document.forms[0].dispositionTypeId);checkOT(document.forms[0])"  onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySupervisionOrderSelectSugOrder" target="content">
<div align="center">
<input type="hidden" name="myAction" value="<bean:write name="supervisionOrderForm" property="action" />">
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
										<bean:message key="title.supervisionOrder" />&nbsp;-&nbsp;<bean:message key="title.prepareOrderPresentation" />
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
											<logic:equal name="supervisionOrderForm" property="versionTypeChangeAllowed" value="false"> 				                        		
												<tr>				                          	
													<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.versionType" /></td>
													<td class="formDe"><bean:write name="supervisionOrderForm" property="versionType" /></td>		
												</tr>
												<html:hidden property="versionTypeId"/>
											</logic:equal>
											<logic:equal name="supervisionOrderForm" property="versionTypeChangeAllowed" value="true">
												<tr>
													<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.versionType" /></td>
													<td class="formDe">
														<html:select property="versionTypeId" size="1" onchange="populateOrdertitles(this.form);createModReason(this.form);">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:optionsCollection name="supervisionOrderForm" property="versionTypeDropDownList" value="code" label="description" />
														</html:select>
													</td>
												</tr>
											</logic:equal>
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.specialCourt" /></td>
												<td class="formDe">
													<html:select property="specialCourtCd" size="1">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection name="supervisionOrderForm" property="specialCourtCds" value="code" label="description" />  
													</html:select>
												</td>
											</tr>
									    <input type="hidden" name="hidVersionType" value="<bean:write name="supervisionOrderForm" property="versionTypeId" />" >
										<input type="hidden" name="hidOrderStatus" value="<bean:write name="supervisionOrderForm" property="orderStatus" />" >
											<tr id="orderTypes" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.orderTitle" /></td>
												<td class="formDe" colspan="3">
													<html:select property="orderTitleId" size="1" onchange="checkOT(this.form)">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection name="supervisionOrderForm" property="orderTitleList" value="printTemplateId" label="orderTitle" />
													</html:select> 
												</td>
											</tr>
 											<tr id="juvCourts" class="hidden">
												<td class="formDeLabel" colspan="1"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.juvenile" /> <bean:message key="prompt.court" /></td>
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
								<%-- 		<logic:equal name="supervisionOrderForm" property="versionTypeChangeAllowed" value="false">
											<logic:notEqual name="supervisionOrderForm" property="versionTypeId" value="O">
												<html:hidden property="orderTitleId"/>
											</logic:notEqual>
										</logic:equal>
										<logic:equal name="supervisionOrderForm" property="versionTypeChangeAllowed" value="true">
											<html:hidden property="orderTitleId"/>
										</logic:equal>  --%>
								<tr>
						        	<td class="formDeLabel" nowrap="nowrap" colspan="1" width="1%"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.printedOffenseDescription" /></td>
            						<td class="formDe" colspan="3"><html:text property="printedOffenseDesc" maxlength="75" size="75" /></td>
								</tr>
								<tr>
						        	<td class="formDeLabel" nowrap="nowrap" colspan="1"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.printedName" /></td>
            						<td class="formDe" colspan="3"><html:text property="printedName" maxlength="75" size="75" /></td>
								</tr>
								<tr>
									<td class="formDeLabel" nowrap="nowrap" colspan="1"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.dispositionType"/></td>
									<td class="formDe" colspan="3">
										<html:select property="dispositionTypeId" onchange="checkDisp(this);resetSupDates(this.form);">
											<html:option value=""><bean:message key="select.generic" /></html:option>
											<html:optionsCollection name="supervisionOrderForm" property="dispositionTypes" value="code" label="description" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td class="formDeLabel" nowrap="nowrap" colspan="1"><bean:message key="prompt.fineAmount"/></td>
									<td class="formDe" colspan="3"><html:text name="supervisionOrderForm" property="fineAmountTotal" maxlength="9" size="9" /></td>
								</tr>

								<tr class="hidden" id="confinementLengthRow">
									<td class="formDeLabel" colspan="1"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.confinementLength"/> </td>
									<td class="formDe" colspan="3">
										<html:text property="confinementLengthYears" size="2" maxlength="2"/> Years
										<html:text property="confinementLengthMonths" size="2" maxlength="2"/> Months
										<html:text property="confinementLengthDays" size="3" maxlength="3"/> Days
									</td>
								</tr>
								<tr class="hidden" id="supervisionLengthRow">
									<td class="formDeLabel" colspan="1"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.supervisionLength"/></td>
									<td class="formDe" colspan="3">
										<html:text property="supervisionLengthYears" size="2" maxlength="2"/> Years
										<html:text property="supervisionLengthMonths" size="2" maxlength="2"/> Months
										<html:text property="supervisionLengthDays" size="3" maxlength="3"/> Days
									</td>
								</tr>
								<tr class="hidden" id="supervisionBeginDate">
									<td class="formDeLabel" nowrap="nowrap" colspan="1">
										<bean:message key="prompt.3.diamond"/><span id="beginDateHeaderSupervision" class="hidden"><bean:message key="prompt.supervisionBeginDate" />
										</span>
										<span id="beginDateHeaderPretrial" class="hidden"><bean:message key="prompt.pretrial"/>&nbsp;<bean:message key="prompt.beginDate"/>
										</span>
									</td>
									<td class="formDe" colspan="3">
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
									<td class="formDeLabel" nowrap="nowrap" colspan="1">
										<bean:message key="prompt.3.diamond"/><span id="endDateHeaderSupervision" class="hidden"><bean:message key="prompt.supervisionEndDate" /> <div>(If extended, enter new expiration date)</div>
											</span>
										<span id="endDateHeaderPretrial" class="hidden">
											<bean:message key="prompt.pretrial"/>&nbsp;<bean:message key="prompt.endDate"/>
										</span>
									</td>
									<td class="formDe" colspan="3">
										<table cellpadding="0">
											<tr>
												<td>
													<html:text name="supervisionOrderForm" property="caseSupervisionEndDateAsString" maxlength="10" size="10" />
													<A HREF="#" onClick="cal1.select(document.forms[0].caseSupervisionEndDateAsString,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2" border="0"><bean:message key="prompt.3.calendar"/></A>
												</td>
												<td id="allowCalc" class="hidden" >
													<logic:equal name="supervisionOrderForm" property="versionTypeId" value="O">
													<%--	<html:submit property="submitAction" onclick="return calculateEndDate(this.form) && disableSubmit(this, this.form)"><bean:message key="button.calculate" /></html:submit> --%>
														<input type="button" name="calc" value=<bean:message key="button.calculate" /> onclick="return calculateEndDate(this.form)" />
													</logic:equal>
												</td>
											</tr>		
										</table>	
									</td>
								</tr>								
							<!-- PLEA field and Original Judge field for CSCD agency only -->
								<logic:equal name="supervisionOrderForm" property="agencyId" value="<%=UIConstants.CSC%>">
									<tr>				                          	
										<td class="formDeLabel" colspan="1"><bean:message key="prompt.plea" /></td>
										<!-- Allow to enter PLEA field for Create Original order only-->
										<!-- Allow to enter PLEA for any flow defect #63272 -->
								<%-- 		<logic:equal name="supervisionOrderForm" property="action" value="create"> --%>
											<td class="formDe" colspan="3">
												<html:select property="pleaId" size="1">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection name="supervisionOrderForm" property="pleas" value="code" label="description" /> 
												</html:select>
											</td>		
								<%-- 		</logic:equal>
																
										<logic:equal name="supervisionOrderForm" property="action" value="update">
											<logic:equal name="supervisionOrderForm" property="versionTypeId" value="O">
												<!-- Display PLEA field only for Original order with Active status -->
												<logic:equal name="supervisionOrderForm" property="orderStatusId" value="A">
													<td class="formDe" colspan="3"><bean:write name="supervisionOrderForm" property="plea" /></td>
												</logic:equal>
												<!-- Allow to modify PLEA field for Original order with other status -->
												<logic:notEqual name="supervisionOrderForm" property="orderStatusId" value="A">
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
										</logic:equal>  --%>
									</tr>	
									<tr id="origJudgeName" class="hidden">				                          	
										<td class="formDeLabel" colspan="1"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.originalJudge" /></td>
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
						<!-- begin felony case check -->		
									<logic:equal name="supervisionOrderForm" property="cdi" value="003">														
										<logic:equal name="supervisionOrderForm" property="isMigratedOrder" value="true">
											<logic:equal name="supervisionOrderForm" property="originalJudgeLastName" value="">	
												<script type="text/javascript">
													show("origJudgeName", 1);
												</script>	
											</logic:equal>
										</logic:equal>
										<logic:equal name="supervisionOrderForm" property="isMigratedOrder" value="false">
											<logic:notEqual name="supervisionOrderForm" property="originalJudgeLastName" value="">									
												<script type="text/javascript">
													show("origJudgeName", 1);
												</script>	
											</logic:notEqual>
										</logic:equal>
										<logic:equal name="supervisionOrderForm" property="versionTypeId" value="M">
											<logic:equal name="supervisionOrderForm" property="orderStatus" value="ACTIVE">									
												<script type="text/javascript">
													show("origJudgeName", 0);
												</script>	
											</logic:equal>
										</logic:equal>
										<logic:equal name="supervisionOrderForm" property="versionTypeId" value="A">
											<logic:equal name="supervisionOrderForm" property="orderStatus" value="ACTIVE">									
												<script type="text/javascript">
													show("origJudgeName", 0);
												</script>	
											</logic:equal>
										</logic:equal>
									</logic:equal>
								<!-- End felony case check -->
								</logic:equal>
							<!-- End PLEA and Original Judge for CSCD agency only -->				
								<tr id="modprompt" class="hidden">
									<td class="formDeLabel" colspan="4"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.summaryOfchanges"/> for Printed Order (Max Characters Allowed: 500)</td>
								</tr>
								<tr id="modtextarea" class="hidden">
									<td class="formDe" colspan="4">
										<textarea rows="4" style="width:100%" name="summaryOfChanges" onmouseout="textLimit( this, 500 )" onkeyup="textLimit( this, 500 )"/><bean:write name="supervisionOrderForm" property="summaryOfChanges" /></textarea>
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
								<logic:equal name="supervisionOrderForm" property="action" value="create">
									<html:submit property="submitAction" onclick="return (validateField(this.form) && checkSupervisionDates(this.form) && disableSubmit(this, this.form));"><bean:message key="button.next"></bean:message></html:submit>&nbsp; 	
								</logic:equal>
								<logic:equal name="supervisionOrderForm" property="action" value="update">  									
  								<logic:notEmpty name="supervisionOrderForm" property="suggestedOrderId">
  									<html:submit property="submitAction" onclick="return (validateField(this.form) && checkSupervisionDates(this.form) && disableSubmit(this, this.form));"><bean:message key="button.saveContinue"></bean:message></html:submit>&nbsp;
  									<html:submit property="submitAction" onclick="return (validateField(this.form) && checkSupervisionDates(this.form) && disableSubmit(this, this.form));"><bean:message key="button.addRemoveConditions"></bean:message></html:submit>&nbsp;
  								</logic:notEmpty>
  								<logic:empty name="supervisionOrderForm" property="suggestedOrderId">
  									<html:submit property="submitAction" onclick="return (validateField(this.form) && checkSupervisionDates(this.form) && disableSubmit(this, this.form));"><bean:message key="button.next"></bean:message></html:submit>  									
  								</logic:empty> 	
								</logic:equal>
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
<script>populateOrdertitles(document.forms[0]);	
  
	function populateOrdertitles(theForm)
	{
		if ( typeof (theForm.versionTypeId) != "undefined"){
			<logic:equal name="supervisionOrderForm" property="versionTypeChangeAllowed" value="true">
				show("orderTypes", 1);
				show ("allowCalc", 1);
				if(theForm.versionTypeId.value == 'M')
				{
					show("orderTypes", 0);
					show ("allowCalc", 0);
				}
				else if(theForm.versionTypeId.value == 'A')
				{	
					if (theForm.getOrderTitleId != null && !theForm.getOrderTitleId.equals("")){
						var selectedOrderTitleId="<bean:write name='supervisionOrderForm' property='orderTitleId'/>";
						counter=0;
						theForm.orderTitleId.value="<bean:write name='supervisionOrderForm' property='orderTitleId'/>";
						show ("allowCalc", 0);
					}
					if (theForm.dispositionTypeId.options[theForm.dispositionTypeId.selectedIndex].value == "<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>"){
						show ("allowCalc", 0);
					}
				} else if (theForm.versionTypeId.value == 'O' || theForm.versionTypeId.value == ""){
					if (theForm.orderTitleId.options[theForm.orderTitleId.selectedIndex].text.indexOf("DETERMINATE SENTENCING") >= 0) {
						show ("allowCalc", 0);
					}	
					if (theForm.dispositionTypeId.options[theForm.dispositionTypeId.selectedIndex].value == "<%=UIConstants.DISPOSITION_TYPE_CODE_PRETRIALINTERVENTION%>"){
						show ("allowCalc", 0);
					}
				}
			</logic:equal>

			<logic:equal name="supervisionOrderForm" property="versionTypeChangeAllowed" value="false">
				if ( typeof (theForm.versionTypeId) != "undefined"){
					if(theForm.versionTypeId.value == 'M')
					{
						show("orderTypes", 0);
					}	
				}
				<logic:equal name="supervisionOrderForm" property="versionTypeId" value="O">
					<logic:notEqual name="supervisionOrderForm" property="orderStatusId" value="A">
						if(theForm.versionTypeId.value == 'O')
						{
							theForm.orderTitleId.options.length = 0;
							var selectedOrderTitleId="<bean:write name='supervisionOrderForm' property='orderTitleId'/>";
				
							theForm.orderTitleId.options[0] = new Option( "Please Select", "");
							theForm.orderTitleId.disabled=false;
							<logic:iterate id="iter" name="supervisionOrderForm" property="orderTitleList">
								var tempOption=new Option("<bean:write name="iter" property="orderTitle"/>", "<bean:write name="iter" property="printTemplateId"/>");
						
								theForm.orderTitleId.options[theForm.orderTitleId.options.length] = tempOption;
								if(selectedOrderTitleId=='<bean:write name="iter" property="printTemplateId"/>'){
									tempOption.selected=true;
								}
							</logic:iterate>
							if(selectedOrderTitleId==''){
								theForm.orderTitleId.options[0].selected=true;
							}
						}
						else   
						{
							theForm.orderTitleId.options.length = 0;
							var selectedOrderTitleId="<bean:write name='supervisionOrderForm' property='orderTitleId'/>";
				
							theForm.orderTitleId.options[0] = new Option( "Please Select", "");
							theForm.orderTitleId.disabled=true;
							<logic:iterate id="iter" name="supervisionOrderForm" property="orderTitleList">
								var tempOption=new Option("<bean:write name="iter" property="orderTitle"/>", "<bean:write name="iter" property="printTemplateId"/>");
						
								theForm.orderTitleId.options[theForm.orderTitleId.options.length] = tempOption;
								if(selectedOrderTitleId=='<bean:write name="iter" property="printTemplateId"/>'){
									tempOption.selected=true;
								}
							</logic:iterate>
							if(selectedOrderTitleId==''){
								theForm.orderTitleId.options[0].selected=true;
							}
						}
					</logic:notEqual>
				</logic:equal>
			</logic:equal>
		}
	}
</script>
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>