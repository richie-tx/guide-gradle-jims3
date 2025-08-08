<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 04/21/2006	 Hien Rodriguez - Create JSP -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

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
<title><bean:message key="title.heading" /> -
outOfCountyCase/reactivate.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript"
	src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript"
	src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<!-- STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="outOfCountyCaseForm2" />

<script type="text/javascript">
	var theCurrentDispType = "";
	
	function setCurrentDispType(theSelect){
		theCurrentDispType = theSelect.options[theSelect.selectedIndex].value;
	}

	function checkDisp(theSelect){
		if(theSelect.options[theSelect.selectedIndex].value == "DADJ"){
			show("updateDispDate", 1, "row");
			show("displayDispDate", 0, "row");
			show("ptinDatesRow", 0, "row");
			show("datesRow", 1, "row");
			show("dateOfSentence", 1, "row");
		}else
		if(theSelect.options[theSelect.selectedIndex].value == "PROB"){
			show("updateDispDate", 1, "row");
			show("displayDispDate", 0, "row");
			show("ptinDatesRow", 0, "row");
			show("datesRow", 1, "row");
			show("dateOfSentence", 1, "row");
		}
		else
		if(theSelect.options[theSelect.selectedIndex].value == "PTIN"){
			show("updateDispDate", 1, "row");
			show("displayDispDate", 0, "row");
			show("ptinDatesRow", 1, "row");
			show("datesRow", 0, "row");
			show("dateOfSentence", 0, "row");
		}
		else
		{
			show("updateDispDate", 0, "row");
			show("displayDispDate", 1, "row");
			show("ptinDatesRow", 0, "row");
			show("datesRow", 0, "row");
			show("dateOfSentence", 1, "row");
		}
		
		theForm = theSelect.form;
	}

	function validateField(theForm){
		clearAllValArrays();
		var theSentenceDateStr='<bean:write name="outOfCountyCaseForm" property="sentenceDateAsString" />';
		var caseTypeId =document.getElementById("oocCaseTypeId").value;		
		
		if(caseTypeId != "CSR" && caseTypeId != "HCT"){
		customValRequired("transferInDateAsString", "Transfer In Date is required");
		}
		addMMDDYYYYDateValidation("transferInDateAsString", "Transfer In Date must be in the format MM/DD/YYYY");
		if(validateCustomStrutsBasedJS(theForm))
		{		
	     	if (theForm.transferInDateAsString.value != "" && theSentenceDateStr != "")
			{
				var thisTransferInDate = new Date(theForm.transferInDateAsString.value);
				var thisSentenceDate = new Date(theSentenceDateStr);
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
		   	
	   	}
	   	else{
	   	  return false;
	   	}
		if (theForm.dispositionTypeId.value == "DADJ" || 
	   		theForm.dispositionTypeId.value == "PROB")
		{
			   			   	   	
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

	   		var thisDispositionDate = new Date(theForm.dispositionDateAsString.value);
	   		var thisBeginDate = new Date(theForm.supervisionBeginDateAsString.value);
			var thisEndDate = new Date(theForm.supervisionEndDateAsString.value);
			var thisPrevSuperBeginDate = new Date(theForm.previousSupervisionBeginDate.value);
			var thisPrevPTBeginDate = new Date(theForm.previousPTInterventionBeginDate.value);
											
			if (thisEndDate < thisBeginDate)		
			    
			{		
		     	alert("Supervision End Date must be equal or greater than Supervision Begin Date.");
		     	theForm.supervisionEndDateAsString.focus();
		     	return false;
		   	}
			
		   	if (theSentenceDateStr != "")
				{		                
					if (thisSentenceDate > thisBeginDate)
					{		
				     	alert("Supervision Begin Date must be equal to or greater than Sentence Date.");
				     	theForm.supervisionBeginDateAsString.focus();
			    	 	return false;
			   		}
		   		}
	   		if (thisPrevPTBeginDate != "")
		   		{
		   		    if(thisDispositionDate < thisPrevPTBeginDate)
		   		    {
			   		    alert("Disposition Date must be equal to or greater than the previous Pretrial Intervention Begin Date");
			   		    theForm.dispositionDateAsString.focus();
			   		    return false;
		   		    }        
	                if(thisPrevPTBeginDate > thisBeginDate)
	                {
		                alert("Supervision Begin Date must be equal to or greater than the previous Pretrial Intervention Begin Date");
		                theForm.supervisionBeginDateAsString.focus();
		                return false;
	                }          
		   		}
	   		if (thisPrevSuperBeginDate != "")
	   		    {
		   			if(thisDispositionDate < thisPrevSuperBeginDate)
		   		    {
			   		    alert("Disposition Date must be equal to or greater than the previous Supervision Begin Date");
			   		    theForm.dispositionDateAsString.focus();
			   		    return false;
		   		    }
                    if(thisPrevSuperBeginDate > thisBeginDate)
                    {
	                    alert("Supervision Begin Date must be equal to or greater than the previous Supervision Begin Date");
	                    theForm.supervisionBeginDateAsString.focus();
	                    return false;
                    }          
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

	   		var thisDispositionDate = new Date(theForm.dispositionDateAsString.value);   	   	
			var thisPreInterBeginDate = new Date(theForm.pretrialInterventionBeginAsString.value);
			var thisPreInterEndDate = new Date(theForm.pretrialInterventionEndAsString.value);
			var thisPrevPTBeginDate = new Date(theForm.previousPTInterventionBeginDate.value);
			var thisPrevSuperBeginDate = new Date(theForm.previousSupervisionBeginDate.value);

			if (thisPreInterEndDate < thisPreInterBeginDate)
			{		
		     	alert("Pretrial Intervention End Date must be equal or greater than Pretrial Intervention Begin Date.");
		     	theForm.pretrialInterventionEndAsString.focus();
		     	return false;
		   	}
			if (thisPrevPTBeginDate != "")
	   		{
				if(thisDispositionDate < thisPrevPTBeginDate)
	   		    {
		   		    alert("Disposition Date must be equal to or greater than the previous Pretrial Intervention Begin Date");
		   		    theForm.dispositionDateAsString.focus();
		   		    return false;
	   		    }
		   		if(thisPrevPTBeginDate > thisPreInterBeginDate)
                {
	                alert("Pretrial Intervention Begin Date must be equal to or greater than the previous Pretrial Begin Date");
	                theForm.pretrialInterventionBeginAsString.focus();
	                return false;
                }          
	   		}
   		    if (thisPrevSuperBeginDate != "")
   		    {
   		    	if(thisDispositionDate < thisPrevSuperBeginDate)
	   		    {
		   		    alert("Disposition Date must be equal to or greater than the previous Supervision Begin Date");
		   		    theForm.dispositionDateAsString.focus();
		   		    return false;
	   		    }
   	   		    if(thisPrevSuperBeginDate > thisPreInterBeginDate)
                {
                    alert("Pretrial Intervention Begin Date must be equal to or greater than the previous Supervision Begin Date");
                    theForm.pretrialInterventionBeginAsString.focus();
                    return false;
                }          
   		    }
		
			   			   
	   	}
	   	
	   	return true;
	}
</script>
</head>

<body topmargin="0" leftmargin="0"
	onload="setCurrentDispType(document.forms[0].dispositionTypeId); checkDisp(document.forms[0].dispositionTypeId);"
	onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayOutOfCountyCaseReactivateSummary"
	target="content" focus="dispositionTypeId">
	<input type="hidden" name="helpFile"
		value="commonsupervision/ooc/out_of_county_case.htm#|12">
	<div align="center">
 <input type="hidden" name="previousSupervisionBeginDate" value="<bean:write name="outOfCountyCaseForm" property="supervisionBeginDate" />">
 <input type="hidden" name="previousPTInterventionBeginDate" value="<bean:write name="outOfCountyCaseForm" property="pretrialInterventionBegin" />">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif"
				height="5" alt=""></td>
		</tr>
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><!--tabs start--> <tiles:insert
						page="../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="setupTab" />
					</tiles:insert> <!--tabs end--></td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img
						src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"
						alt=""></td>
				</tr>
				<tr>
					<td valign="top" align="center">
					<table width="98%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top"><!--tabs start--> <tiles:insert
								page="../common/manageFeaturesTabs.jsp" flush="true">
								<tiles:put name="tabid" value="oocTab" />
							</tiles:insert> <!--tabs end--></td>
						</tr>

					</table>
					<table width="98%" border="0" cellpadding="0" cellspacing="0"
						class="borderTableGreen">
						<tr>
							<td><img src="/<msp:webapp/>images/spacer.gif" height="5"
								alt=""></td>
						</tr>
						<tr>
							<td valign="top" align="center"><!-- BEGIN HEADING TABLE -->
							<table width="98%">
								<tr>
									<td align="center" class="header"><bean:message
										key="prompt.reactivate" />&nbsp;<bean:message
										key="title.outOfCountyCase" /></td>
								</tr>
							</table>
							<!-- END HEADING TABLE --> <!-- BEGIN ERROR TABLE -->
							<table width="98%" align="center">
								<tr>
									<td align="center" class="errorAlert"><html:errors></html:errors></td>
								</tr>
							</table>
							<!-- END ERROR TABLE --> <!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td>
									<ul>
										<li>Enter in at least the required information to
										reactivate case and click Next button to view summary.</li>
										<li>Click Back button if you selected the wrong case.</li>
									</ul>
									</td>
								</tr>
								<tr>
									<td class="required"><bean:message
										key="prompt.requiredFields" />&nbsp;&nbsp;&nbsp;<bean:message
										key="prompt.dateFieldsInstruction" /></td>
								</tr>
							</table>
							<!-- END INSTRUCTION TABLE --> <!-- BEGIN DETAIL HEADER TABLE -->
							<tiles:insert page="partyInfoHeaderTile.jsp" flush="true">
								<tiles:put name="partyHeader" beanName="outOfCountyCaseForm" />
							</tiles:insert> <!-- END DETAIL HEADER TABLE --> <!-- BEGIN DETAIL TABLE -->
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td align="center">
									<table border="0" width="98%" cellspacing="1" cellpadding="2">
										<!-- BEGIN CASE IDENTIFICATIONS SECTION -->
										<tr>
											<td colspan="4" class="detailHead"><bean:message
												key="prompt.case" /><bean:message
												key="prompt.identifications" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.CDI" /></td>
											<td class="formDe" colspan="3"><bean:write
												name="outOfCountyCaseForm" property="cdi" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.case#" /></td>
											<td class="formDe" colspan="3"><bean:write
												name="outOfCountyCaseForm" property="caseNum" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.CJIS#" /></td>
											<td class="formDe" colspan="3"><bean:write
												name="outOfCountyCaseForm" property="cjisNum1" /> - <bean:write
												name="outOfCountyCaseForm" property="cjisNum2" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message
												key="prompt.offense" /></td>
											<td class="formDe" colspan="3"><bean:write
												name="outOfCountyCaseForm" property="offenseId" /> - <bean:write
												name="outOfCountyCaseForm" property="offense" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap width="1%"><bean:message
												key="prompt.outOfCountyCase" /> <bean:message
												key="prompt.type" /></td>
											<td class="formDe" colspan="3"><bean:write
												name="outOfCountyCaseForm" property="caseType" /></td>
										</tr>
										<!-- END CASE IDENTIFICATIONS SECTION -->
										<tr>
											<td><br>
											</td>
										</tr>
										<!-- BEGIN CASE INFORMATION SECTION -->
										<tr>
											<td class="detailHead" colspan="4"><bean:message
												key="prompt.case" /> <bean:message key="prompt.information" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap width="1%"><bean:message
												key="prompt.previous" /> <bean:message
												key="prompt.disposition" /> <bean:message key="prompt.type" /></td>
											<td class="formDe" colspan="3"><bean:write
												name="outOfCountyCaseForm"
												property="previousDispositionType" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message
												key="prompt.2.diamond" /><bean:message
												key="prompt.dispositionType" /></td>
											<td class="formDe" colspan="3"><html:select
												property="dispositionTypeId" onchange="checkDisp(this)">
												<html:option value="">
													<bean:message key="select.generic" />
												</html:option>
												<html:optionsCollection name="outOfCountyCaseForm"
													property="dispositionTypeList" value="code"
													label="description" />
											</html:select></td>
										</tr>
										<tr id="displayDispDate">
											<td class="formDeLabel"><bean:message
												key="prompt.dispositionDate" /></td>
											<td class="formDe" colspan="3"><bean:write
												name="outOfCountyCaseForm" property="dispositionDateAsString" /></td>
										</tr>
										<tr class="hidden" id="updateDispDate">
											<td class="formDeLabel"><bean:message
												key="prompt.2.diamond" /><bean:message
												key="prompt.dispositionDate" /></td>
											<td class="formDe" colspan="3"><SCRIPT
												LANGUAGE="JavaScript" ID="js1" type="text/javascript">
																var cal1 = new CalendarPopup();
																cal1.showYearNavigation();
															</SCRIPT> <html:text name="outOfCountyCaseForm"
												property="dispositionDateAsString" maxlength="10" size="10" />
											<A HREF="#"
												onClick="cal1.select(document.forms[0].dispositionDateAsString,'anchor1','MM/dd/yyyy'); return false;"
												NAME="anchor1" ID="anchor1" border="0"><bean:message
												key="prompt.2.calendar" /></A></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message
												key="prompt.confinement" /> <bean:message
												key="prompt.length" /></td>
											<td class="formDe" colspan="3"><bean:write
												name="outOfCountyCaseForm" property="confinementLengthYear" />
											<bean:message key="prompt.years" />&nbsp; <bean:write
												name="outOfCountyCaseForm" property="confinementLengthMonth" />
											<bean:message key="prompt.months" />&nbsp; <bean:write
												name="outOfCountyCaseForm" property="confinementLengthDay" />
											<bean:message key="prompt.days" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message
												key="prompt.supervision" /> <bean:message
												key="prompt.length" /></td>
											<td class="formDe" colspan="3"><bean:write
												name="outOfCountyCaseForm" property="supervisionLengthYear" />
											<bean:message key="prompt.years" />&nbsp; <bean:write
												name="outOfCountyCaseForm" property="supervisionLengthMonth" />
											<bean:message key="prompt.months" />&nbsp; <bean:write
												name="outOfCountyCaseForm" property="supervisionLengthDay" />
											<bean:message key="prompt.days" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message
												key="prompt.offenseDate" /></td>
											<td class="formDe"><bean:write
												name="outOfCountyCaseForm" property="offenseDateAsString" /></td>
											<td class="formDeLabel"><bean:message
												key="prompt.arrestDate" /></td>
											<td class="formDe"><bean:write
												name="outOfCountyCaseForm" property="arrestDateAsString" /></td>
										</tr>
										<!-- END CASE INFORMATION SECTION -->
										<tr>
											<td><br>
											</td>
										</tr>
										<!-- BEGIN SUPERVISION PARAMETERS SECTION -->
										<tr>
											<td class="detailHead" colspan="4"><bean:message
												key="prompt.supervisionParameters" /></td>
										</tr>
										<tr class="hidden" id="datesRow">
											<td class="formDeLabel"><bean:message
												key="prompt.plusSign" /><bean:message key="prompt.beginDate" /></td>
											<td class="formDe"><html:text name="outOfCountyCaseForm"
												property="supervisionBeginDateAsString" maxlength="10"
												size="10" /> <A HREF="#"
												onClick="cal1.select(document.forms[0].supervisionBeginDateAsString,'anchor4','MM/dd/yyyy'); return false;"
												NAME="anchor4" ID="anchor4" border="0"><bean:message
												key="prompt.2.calendar" /></A></td>

											<td class="formDeLabel"><bean:message
												key="prompt.plusSign" /><bean:message key="prompt.endDate" /></td>
											<td class="formDe"><html:text name="outOfCountyCaseForm"
												property="supervisionEndDateAsString" maxlength="10"
												size="10" /> <A HREF="#"
												onClick="cal1.select(document.forms[0].supervisionEndDateAsString,'anchor5','MM/dd/yyyy'); return false;"
												NAME="anchor5" ID="anchor5" border="0"><bean:message
												key="prompt.2.calendar" /></A></td>
										</tr>

										<tr class="hidden" id="ptinDatesRow">
											<td class="formDeLabel" nowrap width="1%"><bean:message
												key="prompt.plusSign" /><bean:message
												key="prompt.pretrialInterventionBegin" /></td>
											<td class="formDe"><html:text name="outOfCountyCaseForm"
												property="pretrialInterventionBeginAsString" maxlength="10"
												size="10" /> <A HREF="#"
												onClick="cal1.select(document.forms[0].pretrialInterventionBeginAsString,'anchor6','MM/dd/yyyy'); return false;"
												NAME="anchor6" ID="anchor6" border="0"><bean:message
												key="prompt.2.calendar" /></A></td>

											<td class="formDeLabel" nowrap width="1%"><bean:message
												key="prompt.plusSign" /><bean:message
												key="prompt.pretrialInterventionEnd" /></td>
											<td class="formDe"><html:text name="outOfCountyCaseForm"
												property="pretrialInterventionEndAsString" maxlength="10"
												size="10" /> <A HREF="#"
												onClick="cal1.select(document.forms[0].pretrialInterventionEndAsString,'anchor7','MM/dd/yyyy'); return false;"
												NAME="anchor7" ID="anchor7" border="0"><bean:message
												key="prompt.2.calendar" /></A></td>
										</tr>
										<tr id="dateOfSentence">
											<td class="formDeLabel" nowrap><bean:message
												key="prompt.dateOfSentence" /></td>
											<td class="formDe" colspan="3"><bean:write
												name="outOfCountyCaseForm" property="sentenceDateAsString" /></td>
										</tr>
										<!-- END SUPERVISION PARAMETERS SECTION -->
										<tr>
											<td><br>
											</td>
										</tr>
										<!-- BEGIN ORIGINAL JURISDICTION SECTION -->
										<tr>
											<td class="detailHead" colspan="4"><bean:message
												key="prompt.original" /> <bean:message
												key="prompt.jurisdiction" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.case#" /></td>
											<td class="formDe"><bean:write
												name="outOfCountyCaseForm" property="orgJurisCaseNum" /></td>
											<td class="formDeLabel"><bean:message key="prompt.court" /></td>
											<td class="formDe"><bean:write
												name="outOfCountyCaseForm" property="orgJurisCourt" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message
												key="prompt.personID" /></td>
											<td class="formDe"><bean:write
												name="outOfCountyCaseForm" property="orgJurisPID" /></td>
											<td class="formDeLabel" nowrap width="1%"><jims2:if
												name="outOfCountyCaseForm" property="caseTypeId" value="CSR"
												op="notEqual">
												<jims2:and name="outOfCountyCaseForm" property="caseTypeId"
													value="HCT" op="notEqual" />
												<jims2:then>
													<bean:message key="prompt.2.diamond" />
												</jims2:then>
											</jims2:if><bean:message key="prompt.transferInDate" /></td>
											<td class="formDe"><html:text name="outOfCountyCaseForm"
												property="transferInDateAsString" maxlength="10" size="10" />
											<A HREF="#"
												onClick="cal1.select(document.forms[0].transferInDateAsString,'anchor9','MM/dd/yyyy'); return false;"
												NAME="anchor9" ID="anchor9" border="0"><bean:message
												key="prompt.2.calendar" /></A></td>
											<input type="hidden" name="oocCaseTypeId" id="oocCaseTypeId"
												value="<bean:write name='outOfCountyCaseForm' property='caseTypeId' />">
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message
												key="prompt.texasCounty" /></td>
											<td class="formDe" colspan="3"><bean:write
												name="outOfCountyCaseForm" property="orgJurisCounty" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message
												key="prompt.outOfState" /></td>
											<td class="formDe" colspan="3"><bean:write
												name="outOfCountyCaseForm" property="state" /></td>
										</tr>
										<!-- END ORIGINAL JURISDICTION SECTION -->
										<tr>
											<td><br>
											</td>
										</tr>
										<!-- BEGIN CONTACT INFO FOR ORIGINATING JURISDICTION SECTION -->
										<tr>
											<td colspan="4" class="detailHead"><bean:message
												key="prompt.contactInformationForOriginatingJurisdiction" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" valign="top" nowrap><bean:message
												key="prompt.name" /></td>
											<td class="formDe" colspan="3">
											<table border="0" cellspacing="1">
												<tr>
													<td class="formDeLabel" colspan="2"><bean:message
														key="prompt.last" /></td>
												</tr>
												<tr>
													<td class="formDe" colspan="2"><html:text
														property="contactLastName" maxlength="75" size="30" /></td>
												</tr>
												<tr>
													<td class="formDeLabel"><bean:message
														key="prompt.first" /></td>
													<td class="formDeLabel"><bean:message
														key="prompt.middle" /></td>
												</tr>
												<tr>
													<td class="formDe"><html:text
														property="contactFirstName" maxlength="50" size="25" /></td>
													<td class="formDe"><html:text
														property="contactMiddleName" maxlength="50" size="25" /></td>
												</tr>
											</table>
											</td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message
												key="prompt.jobTitle" /></td>
											<td class="formDe" colspan="3"><html:text
												property="contactJobTitle" maxlength="25" size="25" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message
												key="prompt.phone" /></td>
											<td class="formDe" colspan="3"><html:text
												property="contactPhone1" maxlength="3" size="3" /> - <html:text
												property="contactPhone2" maxlength="3" size="3" /> - <html:text
												property="contactPhone3" maxlength="4" size="4" /> <b><bean:message
												key="prompt.ext" /></b> <html:text property="contactPhoneExt"
												maxlength="10" size="10" /></td>
										</tr>
										<!-- END CONTACT INFO FOR ORIGINATING JURISDICTION SECTION -->
									</table>
									</td>
								</tr>
							</table>
							<!-- END DETAIL TABLE --> <br>
							<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="98%">
								<tr>
									<td align="center"><html:submit property="submitAction"
										onclick="return disableSubmit(this, this.form);">
										<bean:message key="button.back" />
									</html:submit>&nbsp; <html:submit property="submitAction"
										onclick="return (validateOutOfCountyCaseForm2(this.form) &amp;&amp; validateField(this.form) &amp;&amp; disableSubmit(this, this.form));">
										<bean:message key="button.next"></bean:message>
									</html:submit>&nbsp; <html:submit property="submitAction"
										onclick="return disableSubmit(this, this.form);">
										<bean:message key="button.cancel"></bean:message>
									</html:submit></td>
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
