<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 04/18/2006	 Hien Rodriguez - Create JSP -->
<!-- 09/08/2006  Hien Rodriguez - Defect#35020 Change wording if instructions -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 04/27/2007  C Shimek		- #41559 correct typo in alert msg in function checkcasetype -->
<!-- 05/16/2007  C Shimek		- #41583 added edit for Supervision begin date to not be less than senternce date -->
<!-- 08/08/2007	 C Shimek       - #44186 Added Offense Code edit to validateField() to pad with leading zeroes to min length of 6. -->
<!-- 01/30/2008	 R.Young        - #47943 Changed personId to required. -->
<!-- 03/10/2009  D Williamson   - #57743 Changed personId to not required -->
<!-- 07/20/2010 Richard Capestani - #66292 OOC - Changed personId to required. Missing OPD on migrated cases (PD/UI) -->
<!-- 08/12/2010  D Williamson   - #66911 Added additional validation for Confinement and Supervision Length.  -->
<!-- 09/23/2010  CShimek        - #67526 added SNU input field -->
<!-- 10/19/2010  CShimek        - removed Name Pointer from validation.xml and added to local function validateField(theForm); could not resolve regExp to resolve values 001 thru 999 while allowing zero as last digit -->
<!-- 11/15/2010  DGibler        - changed Name Pointer to Name Seq num  -->
<!-- 12/29/2010  Dawn Gibler    - changed getter of updateReasonList  -->
<!-- 01/13/2011  C Shimek       - #67526 revised Name Seq Number to Sequence Number and made field required per reopening of ER 66012 request -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - outOfCountyCase/createUpdate.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<!-- STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="outOfCountyCaseForm" />

<script type="text/javascript">
	var theCurrentDispType = "";
	var myAction = "<bean:write name="outOfCountyCaseForm" property="action" />"; 
	function setCurrentDispType(theSelect){
		theCurrentDispType = theSelect.options[theSelect.selectedIndex].value;
	}

	function checkDisp(theSelect){
		var myForm=document.forms[0];
		if(theSelect.options[theSelect.selectedIndex].value == "DADJ"){
			show("confinementLengthRow", 0);
			show("supervisionLengthRow", 1, "row");
			show("ptinDatesRow", 0);
			show("datesRow", 1, "row");
			show("dispDate", 1, "row");
			show("sentenceDate", 1, "row");
			if(myForm.caseTypeId.value != "HCT"){
			show("sentenceDateReqImg", 1, "inline")
			}
			else{
				show("sentenceDateReqImg", 0)
			}
		}else
		if(theSelect.options[theSelect.selectedIndex].value == "PROB"){
			show("confinementLengthRow", 1, "row");
			show("supervisionLengthRow", 1, "row");
			show("ptinDatesRow", 0);
			show("datesRow", 1, "row");
			show("dispDate", 1, "row");
			show("sentenceDate", 1, "row");			
			if(myForm.caseTypeId.value != "HCT"){
				show("sentenceDateReqImg", 1, "inline")
			}
			else{
				show("sentenceDateReqImg", 0)
			}
		}
		else
		if(theSelect.options[theSelect.selectedIndex].value == "PTIN"){
			show("confinementLengthRow", 0);
			show("supervisionLengthRow", 0);
			show("ptinDatesRow", 1, "row");
			show("datesRow", 0);
			show("dispDate", 0);
			show("sentenceDate", 0, "row");
			show("sentenceDateReqImg", 0) 
		}
		else
		{
			show("confinementLengthRow", 0);
			show("supervisionLengthRow", 0);
			show("ptinDatesRow", 0);
			show("datesRow", 0);
			show("dispDate", 0);
			show("sentenceDateReqImg", 1, "inline")
		}
		
		theForm = theSelect.form;
	
	}

	function checkOOCCaseType(theSelect){
		var theSelectVal = theSelect.options[theSelect.selectedIndex].value;		
		if(theSelectVal == "CSR" || theSelectVal == "HCT"){
			show("caseNumReqImg", 0)
			show("personIDReqImg", 0)
			show("transferDateReqImg", 0)
			if(theSelectVal == "HCT"){
			show("sentenceDateReqImg", 0)  
			}
			else{
		    show("sentenceDateReqImg", 1, "inline")
			}
		}
		else{
			show("caseNumReqImg", 1, "inline")
			show("personIDReqImg", 1, "inline")
			show("transferDateReqImg", 1, "inline")
			show("sentenceDateReqImg", 1, "inline")
		}    
	}
	
	function checkCaseType(){
		var myForm=document.forms[0];
		if(myForm.caseTypeId.value==""){
			alert("Out of County Case Type must be selected first");
			myForm.caseTypeId.focus();
			return false;
		}
		return true;
	}

	function validateField(theForm){
//		if (theForm.nameSeqNum.value > ""){
			if (theForm.nameSeqNum.value.length < 3){
				alert("Sequence Number can not be less than 3 characters.");
				theForm.nameSeqNum.focus();
				return false;	
			}
			var numericRegExp = /^[0-9]*$/;
			if (theForm.nameSeqNum.value == "000" || numericRegExp.test(theForm.nameSeqNum.value,numericRegExp) == false ){
				alert("Sequence Nunber must be a number from 001 thru 999.");
				theForm.nameSeqNum.focus();
				return false;	
			}		
//		}	
// pad offenseId with zeroes to min. length of 6 per defect#44186			
		if (theForm.offenseId.value > "")
		{
			while (theForm.offenseId.value.length < 6)
			{
				theForm.offenseId.value = "0" + theForm.offenseId.value;
			}
		}	

		if (theForm.caseTypeId.value != "CSR" && theForm.caseTypeId.value != "HCT" )
	   	{

			if (theForm.orgJurisCaseNum.value == "")
		   	{		
		     	alert("Case # is required.");
		     	theForm.orgJurisCaseNum.focus();
		     	return false;
		   	}
		   	
			if (theForm.orgJurisPID.value == "")
		   	{		
		     	alert("Contact Information Services-Person Id is required.");
		     	theForm.orgJurisPID.focus();
		     	return false;
		   	}

			if (theForm.transferInDateAsString.value == "")
		   	{		
		     	alert("Transfer In Date is required.");
		     	theForm.transferInDateAsString.focus();
		     	return false;
		   	}
		   		   	
	   		if (!isDate(theForm.transferInDateAsString.value))
			{
				alert("Transfer In Date is invalid. Valid format is mm/dd/yyyy.");
				theForm.transferInDateAsString.focus();
				return false;
			} 
				   
	   	}  	

		if (theForm.caseTypeId.value != "HCT" && theForm.dispositionTypeId.value != "PTIN" )
	   	{	

			if (theForm.sentenceDateAsString.value == "")
		   	{		
		     	alert("Sentence Date is required.");
		     	theForm.sentenceDateAsString.focus();
		     	return false;
		   	}	   
	   	}  	
	   	

		if (theForm.dispositionTypeId.value == "PROB")
	   	{
	   	    
	   		if ((theForm.confinementLengthYear.value == "") || theForm.confinementLengthYear.value.length < 2) {
	   			alert("Confinement year(s) must be entered and must be 2 digits.");
	   			theForm.confinementLengthYear.focus();
	   			return false;
	   		} else {
	   			var confinementLengthYearRegex = /^([0-9][0-9])$/;
		   	    if(!confinementLengthYearRegex.test(theForm.confinementLengthYear.value)) {
		   	     	alert("Confinement year(s) must be digits only.");
	   				theForm.confinementLengthYear.focus();
	   				return false;
		   	    } else if (theForm.confinementLengthYear.value > 10) {
		   	    	alert("Confinement year(s) must be less than or equal to 10 years.");
		     		theForm.confinementLengthYear.focus();
		     		return false;
		   	    }
	   		}
	   		
	   		if ((theForm.confinementLengthMonth.value == "") || theForm.confinementLengthMonth.value.length < 2) {
	   			alert("Confinement month(s) must be entered and must be 2 digits.");
	   			theForm.confinementLengthMonth.focus();
	   			return false;
	   		} else {
	   			var confinementLengthMonthRegex = /^([0-9][0-9])$/;
		   	    if(!confinementLengthMonthRegex.test(theForm.confinementLengthMonth.value)) {
		   	     	alert("Confinement month(s) must be digits only.");
	   				theForm.confinementLengthMonth.focus();
	   				return false;
		   	    } else if (theForm.confinementLengthMonth.value > 12) {
		   	    	alert("Confinement month(s) must be less than or equal to 12 months.");
		     		theForm.confinementLengthMonth.focus();
		     		return false;
		   	    }
	   		}
	   		
	   		if ((theForm.confinementLengthDay.value == "") || theForm.confinementLengthDay.value.length < 2) {
	   			alert("Confinement day(s) must be entered and must be 2 digits.");
	   			theForm.confinementLengthDay.focus();
	   			return false;
	   		} else {
	   			var confinementLengthDayRegex = /^([0-9][0-9])$/;
		   	    if(!confinementLengthDayRegex.test(theForm.confinementLengthDay.value)) {
		   	     	alert("Confinement day(s) must be digits only.");
	   				theForm.confinementLengthDay.focus();
	   				return false;
		   	    }
		   	    if (theForm.confinementLengthDay.value > 31){
		   		   alert("Confinement days cannot be greater than 31.");
   				   theForm.confinementLengthDay.focus();
   				   return false;
				} 
	   		}
	   		if ((theForm.confinementLengthYear.value == "00") && (theForm.confinementLengthMonth.value == "00") && theForm.confinementLengthDay.value == "00") {
		   		alert("Either Confinement Year, Confinement Month, or Confinement Day must be greater than 0");
		   		theForm.confinementLengthYear.focus();
		   		return false;
	   		}	 
	   	
	   	}
	   	
	   	if (theForm.dispositionTypeId.value == "DADJ" || 
	   		theForm.dispositionTypeId.value == "PROB")
	   	{

	   		if (theForm.dispositionDateAsString.value == "")
		   	{		
		     	alert("Disposition Date is required for this Disposition Type.");
		     	theForm.dispositionDateAsString.focus();
		     	return false;
		   	}
		   		   	
	   		if (!isDate(theForm.dispositionDateAsString.value))
			{
				alert("Disposition Date is invalid. Valid format is mm/dd/yyyy.");
				theForm.dispositionDateAsString.focus();
				return false;
			} 
			
			if ((theForm.supervisionLengthYear.value == "") || theForm.supervisionLengthYear.value.length < 2) {
	   			alert("Supervision year(s) must be entered and must be 2 digits.");
	   			theForm.supervisionLengthYear.focus();
	   			return false;
	   		} else {
	   			var supervisionLengthYearRegex = /^([0-9][0-9])$/;
		   	    if(!supervisionLengthYearRegex.test(theForm.supervisionLengthYear.value)) {
		   	     	alert("Supervision year(s) must be digits only.");
	   				theForm.supervisionLengthYear.focus();
	   				return false;
		   	    } else if (theForm.supervisionLengthYear.value > 10) {
		   	    	alert("Supervision year(s) must be less than or equal to 10 years.");
		     		theForm.supervisionLengthYear.focus();
		     		return false;
		   	    }
	   		}
	   		
	   		if ((theForm.supervisionLengthMonth.value == "") || theForm.supervisionLengthMonth.value.length < 2) {
	   			alert("Supervision month(s) must be entered and must be 2 digits.");
	   			theForm.supervisionLengthMonth.focus();
	   			return false;
	   		} else {
	   			var supervisionLengthMonthRegex = /^([0-9][0-9])$/;
		   	    if(!supervisionLengthMonthRegex.test(theForm.supervisionLengthMonth.value)) {
		   	     	alert("Supervision month(s) must be digits only.");
	   				theForm.supervisionLengthMonth.focus();
	   				return false;
		   	    } else if (theForm.supervisionLengthMonth.value > 12) {
		   	    	alert("Supervision month(s) must be less than or equal to 12 months.");
		     		theForm.supervisionLengthMonth.focus();
		     		return false;
		   	    }
	   		}
	   		
	   		if ((theForm.supervisionLengthDay.value == "") || theForm.supervisionLengthDay.value.length < 2) {
	   			alert("Supervision day(s) must be entered and must be 2 digits.");
	   			theForm.supervisionLengthDay.focus();
	   			return false;
	   		} else {
	   			var supervisionLengthDayRegex = /^([0-9][0-9])$/;
		   	    if(!supervisionLengthDayRegex.test(theForm.supervisionLengthDay.value)) {
		   	     	alert("Supervision day(s) must be digits only.");
	   				theForm.supervisionLengthDay.focus();
	   				return false;
		   	    }
		   	    if (theForm.supervisionLengthDay.value > 31){
			   		alert("Supervision days cannot be greater than 31.");
	   				theForm.supervisionLengthDay.focus();
	   				return false;
				} 
	   		}  
	   		
		   	if (theForm.supervisionLengthYear.value == "" ||
		   		theForm.supervisionLengthMonth.value == "" ||		
			    theForm.supervisionLengthDay.value == "")
			{		
		     	alert("Supervision Length is required for this Disposition Type.");
		     	if (theForm.supervisionLengthYear.value == "") {
		     		theForm.supervisionLengthYear.focus();
		     	} else if (theForm.supervisionLengthMonth.value == "") {
		     		theForm.supervisionLengthMonth.focus();
		     	} else {
		     		theForm.supervisionLengthDay.focus();
		     	}
		     	theForm.supervisionLengthYear.focus();
		     	return false;
		   	}
		   	if (theForm.supervisionLengthYear.value != "")
		   	{		
				var supervisionLengthYearRegex = /^([0-9][0-9])$/;
		   	    if(!supervisionLengthYearRegex.test(theForm.supervisionLengthYear.value)
		   	    	|| theForm.supervisionLengthYear.value > 10) {
		   	    	alert("Supervision Length Year is invalid.");
		     		theForm.supervisionLengthYear.focus();
		     		return false;
		   	    }
		   	}
		   	if (theForm.supervisionLengthMonth.value != "")
		   	{		
				var supervisionLengthMonthRegex = /^([0-9][0-9])$/;
		   	    if(!supervisionLengthMonthRegex.test(theForm.supervisionLengthMonth.value)
					|| theForm.supervisionLengthMonth.value > 12) {
		   	    	alert("Supervision Length Month is invalid.");
		     		theForm.supervisionLengthMonth.focus();
		     		return false;
		   	    }
		   	}
		   	if (theForm.supervisionLengthDay.value != "")
		   	{		
				var supervisionLengthDayRegex = /^([0-9][0-9])$/;
		   	    if(!supervisionLengthDayRegex.test(theForm.supervisionLengthDay.value)) {
		   	    	alert("Supervision Length Day is invalid.");
		     		theForm.supervisionLengthDay.focus();
		     		return false;
		   	    }
		   	    if (theForm.supervisionLengthDay.value > 31){
			   		alert("Supervision days cannot be greater than 31.");
	   				theForm.supervisionLengthDay.focus();
	   				return false;
				}
		   	}
		   	if ((theForm.supervisionLengthYear.value == "00") && (theForm.supervisionLengthMonth.value == "00") && theForm.supervisionLengthDay.value == "00") {
		   		alert("Either Supervision Year, Supervision Month, or Supervision Day must be greater than 0");
		   		theForm.supervisionLengthYear.focus();
		   		return false;
	   		}
		   	
		   	if (theForm.supervisionBeginDateAsString.value == "")
		   	{		
		     	alert("Supervision Begin Date is required for this Disposition Type.");
		     	theForm.supervisionBeginDateAsString.focus();
		     	return false;
		   	}
		   	
		   	// Use javascript Date validation because this is a hidden field
	   		if (!isDate(theForm.supervisionBeginDateAsString.value))
			{
				alert("Supervision Begin Date is invalid. Valid format is mm/dd/yyyy.");
				theForm.supervisionBeginDateAsString.focus();
				return false;
			}
		   	
		   	if (theForm.supervisionEndDateAsString.value == "")
		   	{		
		     	alert("Supervision End Date is required for this Disposition Type.");
		     	theForm.supervisionEndDateAsString.focus();
		     	return false;
		   	}
		   		   	
	   		if (!isDate(theForm.supervisionEndDateAsString.value))
			{
				alert("Supervision End Date is invalid. Valid format is mm/dd/yyyy.");
				theForm.supervisionEndDateAsString.focus();
				return false;
			}   	   		
	   		
	   		var thisBeginDate = new Date(theForm.supervisionBeginDateAsString.value);
			var thisEndDate = new Date(theForm.supervisionEndDateAsString.value);
			if (thisEndDate < thisBeginDate)
			{		
		     	alert("Supervision End Date cannot be less than Supervision Begin Date.");
		     	theForm.supervisionEndDateAsString.focus();
		     	return false;
		   	}
			
			if (theForm.offenseDateAsString.value != "" &&		
				theForm.supervisionBeginDateAsString.value != "")
			{
				var thisOffenseDate = new Date(theForm.offenseDateAsString.value);
				var thisBeginDate = new Date(theForm.supervisionBeginDateAsString.value);
				if (thisOffenseDate > thisBeginDate)
				{		
			     	alert("Offense Date must be less or equal to Supervision Begin Date.");
			     	theForm.offenseDateAsString.focus();
			     	return false;
			   	}
		   	}
	   	}
	   	
	   	if (theForm.offenseDateAsString.value != "" &&		
			theForm.arrestDateAsString.value != "")
		{
			var thisOffenseDate = new Date(theForm.offenseDateAsString.value);
			var thisArrestDate = new Date(theForm.arrestDateAsString.value);			

			
			
			if ( compareDate1GreaterDate2(thisOffenseDate,getCurrentDate()) ) {
				alert("Offense Date cannot be a Future Date.");
		     	theForm.offenseDateAsString.focus();
		     	return false;
			}
			
			if ( compareDate1GreaterDate2(thisArrestDate,getCurrentDate()) ) {
				alert("Arrest Date cannot be a Future Date.");
		     	theForm.arrestDateAsString.focus();
		     	return false;
			}
			
			if (thisOffenseDate > thisArrestDate)
			{		
		     	alert("Offense Date must be equal or less than Arrest Date.");
		     	theForm.offenseDateAsString.focus();
		     	return false;
		   	}
	   	}	   		   	
	   	
	   	if (theForm.dispositionTypeId.value == "PTIN")
	   	{		
		    if (theForm.pretrialInterventionBeginAsString.value == "")
			{		
		     	alert("Pretrial Intervention Begin Date is required for this Disposition Type.");
		     	theForm.pretrialInterventionBeginAsString.focus();
		     	return false;
	   		}
	   		
	   		if (!isDate(theForm.pretrialInterventionBeginAsString.value))
			{
				alert("Pretrial Intervention Begin Date is invalid. Valid format is mm/dd/yyyy.");
				theForm.pretrialInterventionBeginAsString.focus();
				return false;
			}
	   		
	   		if (theForm.pretrialInterventionEndAsString.value == "")
			{		
		     	alert("Pretrial Intervention End Date is required for this Disposition Type.");
		     	theForm.pretrialInterventionEndAsString.focus();
		     	return false;
	   		}
	   		
	   		if (!isDate(theForm.pretrialInterventionEndAsString.value))
			{
				alert("Pretrial Intervention End Date is invalid. Valid format is mm/dd/yyyy.");
				theForm.pretrialInterventionEndAsString.focus();
				return false;
			}
	   		   	   	
			var thisPreInterBeginDate = new Date(theForm.pretrialInterventionBeginAsString.value);
			var thisPreInterEndDate = new Date(theForm.pretrialInterventionEndAsString.value);
			if (thisPreInterEndDate < thisPreInterBeginDate)
			{		
		     	alert("Pretrial Intervention End Date must be equal or greater than Pretrial Intervention Begin Date.");
		     	theForm.pretrialInterventionEndAsString.focus();
		     	return false;
		   	}
	   	}
	   	
	   	if (theForm.sentenceDateAsString.value != "" &&
	   		theForm.arrestDateAsString.value != "" &&			
			theForm.offenseDateAsString.value != "")
		{
			var thisSentenceDate = new Date(theForm.sentenceDateAsString.value);
			var thisArrestDate = new Date(theForm.arrestDateAsString.value);
			var thisOffenseDate = new Date(theForm.offenseDateAsString.value);
			if (thisSentenceDate < thisArrestDate ||
				thisSentenceDate < thisOffenseDate)
			{		
		     	alert("Date of Sentence must be equal or greater than Arrest Date and Offense Date.");
		     	theForm.sentenceDateAsString.focus();
		     	return false;
		   	}
	   	}
	   	if (theForm.dispositionTypeId.value == "DADJ" || 
		   		theForm.dispositionTypeId.value == "PROB")
		   	{
		   	if (theForm.sentenceDateAsString.value != "" &&
			   	theForm.supervisionBeginDateAsString.value != "" )
				{
					var thisSentenceDate = new Date(theForm.sentenceDateAsString.value);
					var thisSupervisionBeginDate = new Date(theForm.supervisionBeginDateAsString.value);
					if (thisSentenceDate > thisSupervisionBeginDate)
					{		
				     	alert("Supervision Begin Date must be equal to or greater than Sentence Date.");
				     	theForm.supervisionBeginDateAsString.focus();
			    	 	return false;
			   		}
		   		}
		   	}
	   	
	   	if (theForm.transferInDateAsString.value != "" &&		
			theForm.sentenceDateAsString.value != "")
		{
			var thisTransferInDate = new Date(theForm.transferInDateAsString.value);
			var thisSentenceDate = new Date(theForm.sentenceDateAsString.value);
			if (thisTransferInDate < thisSentenceDate)
			{		
		     	alert("Transfer In Date must be equal or greater than Date of Sentence.");
		     	theForm.transferInDateAsString.focus();
		     	return false;
		   	}
	   	}
     	var prevTransferOutDate='<bean:write name="outOfCountyCaseForm" property="transferOutDateStr" formatKey="date.format.mmddyyyy" />';
     	
     	if (theForm.transferInDateAsString.value != "" && prevTransferOutDate != "")
		{
			var thisTransferInDate = new Date(theForm.transferInDateAsString.value);
			var thisTransferOutDate = new Date(prevTransferOutDate);
			if (thisTransferInDate < thisTransferOutDate)
			{		
		     	alert("Transfer In Date must be equal or greater than previous Transfer Out Date of " + prevTransferOutDate);
		     	theForm.transferInDateAsString.focus();
		     	return false;
		   	}
	   	}
     	var dispDatex='<bean:write name="outOfCountyCaseForm" property="dispositionDate" formatKey="date.format.mmddyyyy" />';
     	
     	if (theForm.transferInDateAsString.value != "" && dispDatex != "")
		{
			var thisTransferInDate = new Date(theForm.transferInDateAsString.value);
			var thisDispDate = new Date(dispDatex);
			if (thisTransferInDate < thisDispDate)
			{		
		     	alert("Transfer In Date must be equal or greater than Disposition Date.");
		     	theForm.transferInDateAsString.focus();
		     	return false;
		   	}
	   	}
   	

	   	if (theForm.caseTypeId.value != "CSR" && theForm.caseTypeId.value != "HCT" )
	   	{
	   	    if (theForm.orgJurisCountyId.value == "" &&		
			    theForm.stateId.value == "")
		    {
			    alert("Please select either Texas County Code or Out of State Code.");
			    theForm.orgJurisCountyId.focus();
			    return false;
		    }
	   	}    
	   	if (theForm.orgJurisCountyId.value != "" &&		
			theForm.stateId.value != "")
		{
			alert("Please select either Texas County Code or Out of State Code, can not be both.");
			theForm.orgJurisCountyId.focus();
			return false;
		}
	   	
	   	if (myAction == "update" && theForm.reasonForUpdateId.value == "")
		{
   			alert("Reason for Update is required.");
			theForm.reasonForUpdateId.focus();
			return false;
	   	}
	   	
	   	return true;
	}
</script>
</head>

<body topmargin="0" leftmargin="0"
	onload="setCurrentDispType(document.forms[0].dispositionTypeId); checkDisp(document.forms[0].dispositionTypeId); checkOOCCaseType(document.forms[0].caseTypeId);"
	onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayOutOfCountyCaseSummary" target="content" focus="cjisNum1">
	<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif"	height="5" alt=""></td>
		</tr>
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top">
						<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="setupTab" />
						</tiles:insert> 
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
				</tr>
				<tr>
					<td valign="top" align="center">
					<table width="98%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top">
								<tiles:insert page="../common/manageFeaturesTabs.jsp" flush="true">
									<tiles:put name="tabid" value="oocTab" />
								</tiles:insert> 
							</td>
						</tr>
					</table>
					<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
						<tr>
							<td><img src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
						</tr>
						<tr>
							<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
							<table width="100%">
								<tr>
									<td align="center" class="header">
										<logic:equal name="outOfCountyCaseForm" property="action" value="create">
											<input type="hidden" name="helpFile" value="commonsupervision/ooc/out_of_county_case.htm#|6">
											<bean:message key="prompt.create" />
										</logic:equal>
										<logic:equal name="outOfCountyCaseForm" property="action" value="update">
											<input type="hidden" name="helpFile" value="commonsupervision/ooc/out_of_county_case.htm#|9">
											<bean:message key="prompt.update" />
										</logic:equal>
										 <bean:message key="title.outOfCountyCase" />
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
									<td>
										<ul>
											<li>Enter required fields.</li>
											<li>Select Back button if you selected the wrong case.</li>
										</ul>
									</td>
								</tr>
								<tr>
									<td class="required">
										<bean:message key="prompt.requiredFields" />&nbsp;&nbsp;&nbsp;
										<bean:message key="prompt.conditionallyRequiredFields" />&nbsp;&nbsp;&nbsp;
										<bean:message key="prompt.dateFieldsInstruction" />
									</td>
								</tr>
							</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN DETAIL HEADER TABLE -->
							<tiles:insert page="partyInfoHeaderTile.jsp" flush="true">
								<tiles:put name="partyHeader" beanName="outOfCountyCaseForm" />
							</tiles:insert>
 <!-- END DETAIL HEADER TABLE -->
 <!-- BEGIN DETAIL TABLE -->
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td align="center">
<!-- BEGIN CASE IDENTIFICATIONS TABLE -->									
									<table border="0" width="98%" cellspacing="1" cellpadding="2">
										
										<tr>
											<td colspan="4" class="detailHead">
												<bean:message key="prompt.case" />&nbsp;<bean:message key="prompt.identifications" />
											</td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.CDI" /></td>
											<td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="courtDivision" /></td>
										</tr>
										<logic:equal name="outOfCountyCaseForm" property="action" value="update">
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.case#" /></td>
												<td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="caseNum" /></td>
											</tr>
										</logic:equal>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.CJIS#" /></td>
											<td class="formDe" >
												<html:text property="cjisNum1" maxlength="10" size="10" />&nbsp;- 
												<html:text property="cjisNum2" maxlength="4" size="4" />
											</td>
											<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.sequenceNumber" /></td>
											<td class="formDe" >
												<html:text property="nameSeqNum" maxlength="3" size="4" />
											</td>	
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.offenseCode" /></td>
											<td class="formDe" colspan="2">
												<html:text property="offenseId" maxlength="8" size="8" />&nbsp;
												<logic:notEmpty name="outOfCountyCaseForm" property="offense">
													<bean:write name="outOfCountyCaseForm" 	property="offenseDesc" />
												</logic:notEmpty>&nbsp;&nbsp;
												<html:submit property="submitAction" onclick="return (checkCaseType()&amp;&amp; disableSubmit(this, this.form));">
													<bean:message key="button.validate"></bean:message>
												</html:submit>
											</td>
											<td class="formDe">
											   <a href="javascript:changeFormActionURL(document.forms[0],'/<msp:webapp/>displayOutOfCountyCaseSummary.do?submitAction=<bean:message key="prompt.findOffenseCode"/>',true)">
											   	<bean:message key="prompt.findOffenseCode" /></a>
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap width="1%">
												<bean:message key="prompt.2.diamond" /><bean:message key="prompt.outOfCountyCaseType" /></td>
											<td class="formDe" colspan="3">
												<html:select property="caseTypeId" size="1" onchange="checkOOCCaseType(this)">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection name="outOfCountyCaseForm" property="caseTypeList" value="courtNumber" label="description" />
												</html:select>
											</td>
										</tr>
<!-- END CASE IDENTIFICATIONS SECTION -->
										<tr>
											<td><br></td>
										</tr>
<!-- BEGIN CASE INFORMATION SECTION -->
										<tr>
											<td class="detailHead" colspan="4">
												<bean:message key="prompt.case" />&nbsp;<bean:message key="prompt.information" />
											</td>
										</tr>
										<tr>
											<td class="formDeLabel">
												<bean:message key="prompt.2.diamond" /><bean:message key="prompt.dispositionType" />
											</td>
											<td class="formDe" colspan="3">
												<html:select property="dispositionTypeId" onchange="checkDisp(this)">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection name="outOfCountyCaseForm" property="dispositionTypeList" value="code" label="description" />
												</html:select>
											</td>
										</tr>
										<tr id="dispDate">
											<td class="formDeLabel">
												<bean:message key="prompt.2.diamond" /><bean:message key="prompt.dispositionDate" />
											</td>
											<td class="formDe" colspan="3">
												<SCRIPT type="text/javascript" ID="js1" >
													var cal1 = new CalendarPopup();
													cal1.showYearNavigation();
												</SCRIPT>
												<html:text name="outOfCountyCaseForm" property="dispositionDateAsString" maxlength="10" size="10" />
												<A HREF="#" onClick="cal1.select(document.forms[0].dispositionDateAsString,'anchor1','MM/dd/yyyy'); return false;"
													NAME="anchor1" ID="anchor1" border="0"><bean:message key="prompt.2.calendar" /></A>
											</td>
										</tr>
										<tr class="hidden" id="confinementLengthRow">
											<td class="formDeLabel">
												<bean:message key="prompt.2.diamond" /><bean:message key="prompt.confinementLength" />
											</td>
											<td class="formDe" colspan="3">
												<html:text property="confinementLengthYear" maxlength="2" size="2" /><bean:message key="prompt.years" />
												<html:text property="confinementLengthMonth" maxlength="2" size="2" /><bean:message key="prompt.months" />
												<html:text property="confinementLengthDay" maxlength="2" size="2" /><bean:message key="prompt.days" />
											</td>
										</tr>
										<tr class="hidden" id="supervisionLengthRow">
											<td class="formDeLabel">
												<bean:message key="prompt.2.diamond" /><bean:message key="prompt.supervisionLength" />
											</td>
											<td class="formDe" colspan="3">
												<html:text property="supervisionLengthYear" maxlength="2" size="2" /><bean:message key="prompt.years" />
												<html:text property="supervisionLengthMonth" maxlength="2" size="2" /><bean:message key="prompt.months" />
												<html:text property="supervisionLengthDay" maxlength="2" size="2" /><bean:message key="prompt.days" />
											</td>
										</tr>
										<tr>
											<td class="formDeLabel">
												<bean:message key="prompt.2.diamond" /><bean:message key="prompt.offenseDate" />
											</td>
											<td class="formDe">
												<html:text name="outOfCountyCaseForm" property="offenseDateAsString" maxlength="10" size="10" />
												<A HREF="#" onClick="cal1.select(document.forms[0].offenseDateAsString,'anchor2','MM/dd/yyyy'); return false;"
												NAME="anchor2" ID="anchor2" border="0"><bean:message key="prompt.2.calendar" /></A>
											</td>
											<td class="formDeLabel">
												<bean:message key="prompt.2.diamond" /><bean:message key="prompt.arrestDate" /></td>
											<td class="formDe">
												<html:text name="outOfCountyCaseForm" property="arrestDateAsString" maxlength="10" size="10" />
													<A HREF="#" onClick="cal1.select(document.forms[0].arrestDateAsString,'anchor3','MM/dd/yyyy'); return false;"
														NAME="anchor3" ID="anchor3" border="0"><bean:message key="prompt.2.calendar" /></A>
											</td>
										</tr>
										<!-- END CASE INFORMATION SECTION -->
										<tr>
											<td><br></td>
										</tr>
										<!-- BEGIN SUPERVISION PARAMETERS SECTION -->
										<tr>
											<td class="detailHead" colspan="4"><bean:message key="prompt.supervisionParameters" /></td>
										</tr>
										<tr class="hidden" id="datesRow">
											<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.beginDate" /></td>
											<td class="formDe">
												<html:text name="outOfCountyCaseForm" property="supervisionBeginDateAsString" maxlength="10" size="10" />
													<A HREF="#" onClick="cal1.select(document.forms[0].supervisionBeginDateAsString,'anchor4','MM/dd/yyyy'); return false;"
													NAME="anchor4" ID="anchor4" border="0"><bean:message key="prompt.2.calendar" /></A>
											</td>
											<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.endDate" /></td>
											<td class="formDe">
												<html:text name="outOfCountyCaseForm" property="supervisionEndDateAsString" maxlength="10" size="10" />
													<A HREF="#" onClick="cal1.select(document.forms[0].supervisionEndDateAsString,'anchor5','MM/dd/yyyy'); return false;"
													NAME="anchor5" ID="anchor5" border="0"><bean:message key="prompt.2.calendar" /></A>
											</td>
										</tr>
										<tr class="hidden" id="ptinDatesRow">
											<td class="formDeLabel" nowrap width="1%">
												<bean:message key="prompt.2.diamond" /><bean:message key="prompt.pretrialInterventionBegin" />
											</td>
											<td class="formDe">
												<html:text name="outOfCountyCaseForm" property="pretrialInterventionBeginAsString" maxlength="10" size="10" />
												 	<A HREF="#" onClick="cal1.select(document.forms[0].pretrialInterventionBeginAsString,'anchor6','MM/dd/yyyy'); return false;"
													NAME="anchor6" ID="anchor6" border="0"><bean:message key="prompt.2.calendar" /></A>
											</td>
											<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.pretrialInterventionEnd" /></td>
											<td class="formDe">
												<html:text name="outOfCountyCaseForm" property="pretrialInterventionEndAsString" maxlength="10" size="10" />
													<A HREF="#" onClick="cal1.select(document.forms[0].pretrialInterventionEndAsString,'anchor7','MM/dd/yyyy'); return false;"
														NAME="anchor7" ID="anchor7" border="0"><bean:message key="prompt.2.calendar" /></A>
											</td>
										</tr>
										<tr id="sentenceDate">
											<td class="formDeLabel" nowrap>
												<span class="hidden" id="sentenceDateReqImg">
													<bean:message key="prompt.2.diamond" />
												</span>
												<bean:message key="prompt.dateOfSentence" /></td>
											<td class="formDe" colspan="3">
												<html:text name="outOfCountyCaseForm" property="sentenceDateAsString" maxlength="10" size="10" />
													<A HREF="#" onClick="cal1.select(document.forms[0].sentenceDateAsString,'anchor8','MM/dd/yyyy'); return false;"
														NAME="anchor8" ID="anchor8" border="0"><bean:message key="prompt.2.calendar" /></A>
											</td>
										</tr>
										<!-- END SUPERVISION PARAMETERS SECTION -->
										<tr>
											<td><br></td>
										</tr>
										<!-- BEGIN ORIGINAL JURISDICTION SECTION -->
										<tr>
											<td class="detailHead" colspan="4"><bean:message key="prompt.original" />&nbsp;<bean:message key="prompt.jurisdiction" /></td>
										</tr>
										<tr>
											<td class="formDeLabel">
												<span class="hidden" id="caseNumReqImg">
													<bean:message key="prompt.2.diamond" />
												</span>
												<bean:message key="prompt.case#" />
											</td>
											<td class="formDe"><html:text property="orgJurisCaseNum" maxlength="20" size="20" /></td>
											<td class="formDeLabel"><bean:message key="prompt.court" /></td>
											<td class="formDe"><html:text property="orgJurisCourt" maxlength="4" size="4" /></td>
										</tr>
										<tr>
											<td class="formDeLabel">
												<span class="hidden" id="personIDReqImg">
													<bean:message key="prompt.2.diamond" />
												</span>
												<bean:message key="prompt.personID" />
											</td>
											<td class="formDe"><html:text property="orgJurisPID" maxlength="20" size="20" /></td>
											<td class="formDeLabel" nowrap width="1%">
												<span class="hidden" id="transferDateReqImg">
													<bean:message key="prompt.2.diamond" />
												</span>
												<bean:message key="prompt.transferInDate" />
											</td>
											<td class="formDe"><html:text name="outOfCountyCaseForm"
												property="transferInDateAsString" maxlength="10" size="10" />
											<A HREF="#"
												onClick="cal1.select(document.forms[0].transferInDateAsString,'anchor9','MM/dd/yyyy'); return false;"
												NAME="anchor9" ID="anchor9" border="0"><bean:message
												key="prompt.2.calendar" /></A></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.plusSign" /><bean:message key="prompt.texasCounty" />&nbsp;<bean:message key="prompt.code" /></td>
											<td class="formDe" colspan="3">
												<html:select property="orgJurisCountyId" size="1">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection name="outOfCountyCaseForm" property="countyList" value="code" label="description" />
												</html:select>
											</td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.plusSign" /><bean:message key="prompt.outOfState" />&nbsp;<bean:message key="prompt.code" /></td>
											<td class="formDe" colspan="3">
												<html:select property="stateId" size="1">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection name="outOfCountyCaseForm" property="stateList" value="code" label="description" />
												</html:select>
											</td>
										</tr>
										<!-- END ORIGINAL JURISDICTION SECTION -->
										<tr>
											<td><br></td>
										</tr>
										<!-- BEGIN CONTACT INFO FOR ORIGINATING JURISDICTION SECTION -->
										<tr>
											<td colspan="4" class="detailHead"><bean:message key="prompt.contactInformationForOriginatingJurisdiction" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" valign="top" nowrap><bean:message key="prompt.name" /></td>
											<td class="formDe" colspan="3">
												<table border="0" cellspacing="1">
													<tr>
														<td class="formDeLabel" colspan="2"><bean:message key="prompt.last" /></td>
													</tr>
													<tr>
														<td class="formDe" colspan="2"><html:text property="contactLastName" maxlength="75" size="30" /></td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.first" /></td>
														<td class="formDeLabel"><bean:message key="prompt.middle" /></td>
													</tr>
													<tr>
														<td class="formDe"><html:text property="contactFirstName" maxlength="50" size="25" /></td>
														<td class="formDe"><html:text property="contactMiddleName" maxlength="50" size="25" /></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap><bean:message
												key="prompt.jobTitle" /></td>
											<td class="formDe" colspan="3"><html:text
												property="contactJobTitle" maxlength="25" size="25" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap><bean:message key="prompt.phone" /></td>
											<td class="formDe" colspan="3">
												<html:text property="contactPhone1" maxlength="3" size="3" /> - 
												<html:text property="contactPhone2" maxlength="3" size="3" /> -
												<html:text property="contactPhone3" maxlength="4" size="4" />
												 <b><bean:message key="prompt.ext" /></b>
												<html:text property="contactPhoneExt" maxlength="10" size="10" />
											</td>
										</tr>
										<!-- END CONTACT INFO FOR ORIGINATING JURISDICTION SECTION -->
										<logic:equal name="outOfCountyCaseForm" property="action" value="update">
											<tr>
												<td><br></td>
											</tr>
											<!-- BEGIN REASON FOR UPDATE SECTION -->
											<tr>
												<td colspan="4" class="detailHead"><bean:message key="prompt.reasonForUpdate" /></td>
											</tr>
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.reason" /></td>
												<td class="formDe" colspan="3">
													<html:select property="reasonForUpdateId" size="1">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:optionsCollection name="outOfCountyCaseForm" property="activeReasonForUpdateList" value="code" label="description" />
													</html:select>
												</td>
											</tr>
											<!-- END REASON FOR UPDATE SECTION -->
										</logic:equal>
									</table>
									</td>
								</tr>
							</table>
<!-- END DETAIL TABLE --> <br>
<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="98%">
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back" /></html:submit>&nbsp;
										<html:submit property="submitAction" onclick="return (validateOutOfCountyCaseForm(this.form) &amp;&amp; validateField(this.form) &amp;&amp; disableSubmit(this, this.form));">
											<bean:message key="button.next"></bean:message>
										</html:submit>&nbsp;
										<html:reset><bean:message key="button.reset"></bean:message></html:reset>&nbsp;
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
									</td>
								</tr>
							</table>
<!-- END BUTTON TABLE --></td>
						</tr>
					</table>
					<br>
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