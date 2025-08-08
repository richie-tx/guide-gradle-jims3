<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 01/30/2008	 Aswin Widjaja - Create JSP -->
<!-- 08/05/2009  C Shimek      - #61248 revised validateSexOffender to only validate measurement result during update (based on logic in function checkSOCat() which hides the field unless update) -->
<!-- 08/18/2009  C Shimek      - #61248 (re-opened) commented out scripting to display and validate measurement for update state (undo of defect#57174) -->
<!-- 03/05/2010  C Shimek      - #64311 added js to preselect State=Texas and County=Harris to onload-->
<!-- 06/14/2010  C Shimek      - #65901 added validation to zip code in Alternate Address on Next button onclick -->
<!-- 06/18/2010  C Shimek      - Activity #66045, made Field Visit Date required Input for create activityFlow -->
<!-- 06/25/2010  L Deen        - Activity #66169 change to tinyMCECustomInitCasenote.js-->
<!-- 12/21/2010  L Deen        - Activity #68540 -->
<!-- 02/28/2012  R Capestani   - Activity #71957 add validation to Alternate Phone number -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@page import="naming.UIConstants"%>
<%@page import="naming.PDCodeTableConstants"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
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
<title><bean:message key="title.heading" /> - csCalendar - fieldVisitCreateUpdate.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->

<html:javascript formName="fieldVisitCreateUpdate"/>

<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script language="javascript" type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script language="javascript" type="text/javascript" src="/<msp:webapp/>js/tinyMCECustomInitCasenote.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>	
<script>

function checkSOType(theSelect){
	var theSelectVal = theSelect.options[theSelect.selectedIndex].value;
	var theForm = theSelect.form
	if(theSelectVal == "SO"){
		show("sexOffender1", 1, "row")
		show("commentsReqImg", 1, "inline")
	}
}
function checkFVType(theSelect){
	var theSelectVal = theSelect.options[theSelect.selectedIndex].value;
	var theForm = theSelect.form
	if(theSelectVal == "SO"){
		show("sexOffender1", 1, "row")
		show("commentsReqImg", 1, "inline")
		theForm["currentFieldVisit.comments"].value = "";
	} else if(theSelectVal == "EL"){
		theForm["currentFieldVisit.sexOffenderCategoryCd"].selectedIndex = "0";
		show("sexOffender1", 0)
		show("commentsReqImg", 1, "inline")
		theForm["currentFieldVisit.comments"].value = "Electronic Monitoring:";
	}else {
		theForm["currentFieldVisit.sexOffenderCategoryCd"].selectedIndex = "0";
		show("sexOffender1", 0)
		show("commentsReqImg", 0)
		theForm["currentFieldVisit.comments"].value = "";
	}
}

function checkSOCat(theSelect){
	
	<logic:equal name="csCalendarFVForm" property="activityFlow" value="update">	
// commented out as part of changes for reopen of defect #61248	(see notes in CC)
//	if(theSelect.value =="CZE" || theSelect.value =="CZR" ){
//		show("sexOffender2", 1, "row")
//	}
//	else{
		show("sexOffender2", 0)
//	}
	</logic:equal>
	
	var theSelectVal = theSelect.options[theSelect.selectedIndex].innerHTML;
	var theForm = theSelect.form;
	if(theSelect.options[theSelect.selectedIndex].value !=""){
	theForm["currentFieldVisit.comments"].value = theSelectVal + ":";
	}
	var methodOfContact = theForm["currentFieldVisit.methodOfContactCd"];
}

function validateSexOffender(theForm) {
	
    var sexOffender = theForm["currentFieldVisit.sexOffenderCategoryCd"];
    var measurementResult = theForm["currentFieldVisit.measurementResultCd"];
    var comments = theForm["currentFieldVisit.comments"];
    if(theForm["currentFieldVisit.fieldVisitTypeCd"].value == "SO") {	
		if(sexOffender != null && sexOffender.value == "")  {
			alert("Please select a Sex Offender Category.");
			theForm["currentFieldVisit.sexOffenderCategoryCd"].focus();
			return false;
		}
// commented out as part of changes for reopen of defect #61248	(see notes in CC)	
//		if (sexOffender.value == "CZE" || sexOffender.value == "CZR") {
//			<logic:equal name="csCalendarFVForm" property="activityFlow" value="update">
//				if(measurementResult != null && measurementResult.value == "") {
//					alert("Please select a measurement result.");
//					theForm["currentFieldVisit.measurementResultCd"].focus();			
//					return false;
//				}
//			</logic:equal>
//		}
		if(comments != null && comments.value == "")  {
			alert("Comments are required when Field Visit Type is Sex Offender.");
			theForm["currentFieldVisit.comments"].focus();
			return false;
		}		
	}
	return true;	
}

function validateELM(theForm) {

	var comments = theForm["currentFieldVisit.comments"];
	if(theForm["currentFieldVisit.fieldVisitTypeCd"].value == "EL") {
		if(comments != null && comments.value == "")  {
			alert("Comments are required when Field Visit Type is ELM.");
			theForm["currentFieldVisit.comments"].focus();
			return false;
		}
	}
	return true;	
}

function checkForOther(theSelect){
	var theSelectVal = theSelect.options[theSelect.selectedIndex].value;

	if (theSelectVal == "OT"){
		show("otherTextField", 1, "inline")
	}else 
		show("otherTextField", 0)
}

function validateOtherPurpose(theForm) {
	//Added field vailidation under "function validateFields(theForm)" below
    var otherPurpose = theForm["currentFieldVisit.otherPurpose"];
    if(theForm["currentFieldVisit.purposeCd"].value == "OT") {	
		if(otherPurpose != null && otherPurpose.value == "")  {
			alert("Other Purpose must be entered when Purpose is Other.");
			theForm["currentFieldVisit.otherPurpose"].focus();
			return false;
		}		
	}
	return true;	
}

function renderAssociatesSelect(el){
	if (el.options[el.selectedIndex].value=="AS"){
		show("associatesIdsSpan", 1, "inline")
	}
	else {
			show("associatesIdsSpan", 0)
	}
}


function validateMethodOfContact(theForm) {
	var methodOfContact = theForm["currentFieldVisit.methodOfContactCd"];
	if(methodOfContact != null && methodOfContact.value == "AS" && theForm["currentFieldVisit.associateIds"].selectedIndex < 1) {
		alert("Please select one or more associate(s).");
		theForm["currentFieldVisit.associateIds"].focus();
		return false;
	}
}

function validateFieldVisitResults(theForm) {

	var outcome = theForm["currentFieldVisit.outcomeCd"];
	if(outcome != null && outcome.value == "") {
		alert("Please select an outcome.");
		outcome.focus();
		return false;
	}
	

	
	
	var methodOfContact = theForm["currentFieldVisit.methodOfContactCd"];
	if(methodOfContact != null && methodOfContact.value == "") {
		alert("Please select method of contact.");
		methodOfContact.focus();
		return false;
	
	}
	
	var narrative = theForm["currentFieldVisit.narrative"];
	if(narrative != null) {
	   clearAllValArrays(); 
	   trimCasenote('currentFieldVisit.narrative');
	   customValRequired("currentFieldVisit.narrative", "<bean:message key='errors.required' arg0='Narrative'/>","");	   
	   customValMaxLength("currentFieldVisit.narrative","<bean:message key='errors.maxlength' arg0='Narrative' arg1='3500'/>","7000");
	   addDefinedTinyMCEFieldMask("currentFieldVisit.narrative","Narrative Text cannot have % or _ entries","");
	   if (validateCustomStrutsBasedJS(theForm) && validateForBROnly(document.getElementsByName('currentFieldVisit.narrative')[0].value, 'Narrative is required')){
			return true;
		}else {
			return false;
		}
	}
	
}

function validateFields(theForm)
{    
    clearAllValArrays();
    
  	//TSV 01/04/2012 JIMS200072042 - Changed field vailidation 
    addDefinedDB2Mask("currentFieldVisit.otherPurpose", "<bean:message key='errors.alphanumericWSymbols2' arg0='Comments'/>","");
    customValMaxLength("currentFieldVisit.comments", "Comments cannot be more than 255 characters",255);
	addDefinedDB2Mask("currentFieldVisit.comments", "<bean:message key='errors.alphanumericWSymbols2' arg0='Comments'/>","");
	customValMinLength("currentFieldVisit.alternateAddress.zipCode","<bean:message key='errors.minlength' arg0='Zip Code' arg1='5'/>","5");
    addNumericValidation("currentFieldVisit.alternateAddress.zipCode", "<bean:message key='errors.numeric' arg0='Zip Code'/>","");
	customValMinLength("currentFieldVisit.alternateAddress.additionalZipCode","<bean:message key='errors.minlength' arg0='Zip Code extension' arg1='4'/>","4");
    addNumericValidation("currentFieldVisit.alternateAddress.additionalZipCode", "<bean:message key='errors.numeric' arg0='Zip Code extension'/>","");
    customValMinLength("currentFieldVisit.alternatePhone.areaCode","<bean:message key='errors.minlength' arg0='Alternate Phone Area Code' arg1='3'/>","3");
    addNumericValidation("currentFieldVisit.alternatePhone.areaCode", "<bean:message key='errors.numeric' arg0='Alternate Phone Area Code'/>","");
    customValMinLength("currentFieldVisit.alternatePhone.prefix","<bean:message key='errors.minlength' arg0='Alternate Phone Prefix' arg1='3'/>","3");
    addNumericValidation("currentFieldVisit.alternatePhone.prefix", "<bean:message key='errors.numeric' arg0='Alternate Phone Prefix'/>","");
    customValMinLength("currentFieldVisit.alternatePhone.fourDigit","<bean:message key='errors.minlength' arg0='Alternate Phone Four Digit' arg1='4'/>","4");
    addNumericValidation("currentFieldVisit.alternatePhone.fourDigit", "<bean:message key='errors.numeric' arg0='Alternate Phone Four Digit'/>","");   
	customValMaxLength("currentFieldVisit.noteworthyConditions", "Noteworthy Conditions cannot be more than 1000 characters",1000);
	addDefinedDB2Mask("currentFieldVisit.noteworthyConditions", "<bean:message key='errors.alphanumericWSymbols2' arg0='Comments'/>","");
	customValMaxLength("currentFieldVisit.addressDescription", "Address Description cannot be more than 255 characters",255);
	addDefinedDB2Mask("currentFieldVisit.comments", "<bean:message key='errors.alphanumericWSymbols2' arg0='Comments'/>","");
	addDefinedDB2Mask("currentFieldVisit.addressDescription", "<bean:message key='errors.alphanumericWSymbols2' arg0='Comments'/>","");
	customValMaxLength("currentFieldVisit.caution", "Caution cannot be more than 255 characters",255);
	addDefinedDB2Mask("currentFieldVisit.caution", "<bean:message key='errors.alphanumericWSymbols2' arg0='Comments'/>","");
	add12HrTimeValidation("currentFieldVisit.startTime","Start Time is not in proper 12 hour format, ie 03:15","");
    add12HrTimeValidation("currentFieldVisit.endTime","End Time is not in proper 12 hour format, ie 03:15","");		       
	result = validateCustomStrutsBasedJS(theForm);
	if (result == true){
		z2 = document.getElementsByName("currentFieldVisit.alternateAddress.additionalZipCode");
		if (z2[0].value > ""){
			z1 = document.getElementsByName("currentFieldVisit.alternateAddress.zipCode");
			if (z1[0].value == ""){
				alert("Zip Code required when zip code extension is present.");
				z1[0].focus();
				return false;
			}
		}					
	}
	
	if (theForm["currentFieldVisit.alternatePhone.areaCode"].value > "" && theForm["currentFieldVisit.alternatePhone.prefix"].value == "") {
	    alert("Alternate Phone Number Prefix must be entered if Alternate Phone Number Area Code is entered.");
	    theForm["currentFieldVisit.alternatePhone.prefix"].focus();
	    return false;
	}

	if (theForm["currentFieldVisit.alternatePhone.prefix"].value > "" && theForm["currentFieldVisit.alternatePhone.areaCode"].value == "") {
	    alert("Alternate Phone Number Area Code must be entered if Alternate Phone Number Prefix is entered.");
	    theForm["currentFieldVisit.alternatePhone.fourDigit"].focus();
	    return false;
  	}

	if (theForm["currentFieldVisit.alternatePhone.areaCode"].value == "" && theForm["currentFieldVisit.alternatePhone.fourDigit"].value > "") {
	    alert("Alternate Phone Number Area Code must be entered if Alternate Phone Number Last4Digit number is entered.");
	    theForm["currentFieldVisit.alternatePhone.areaCode"].focus();
	}
	  
	if (theForm["currentFieldVisit.alternatePhone.prefix"].value > "" && theForm["currentFieldVisit.alternatePhone.fourDigit"].value == "") {
	    alert("Alternate Phone Number Last4Digit number must be entered if Alternate Phone Number Prefix is entered.");
	    theForm["currentFieldVisit.alternatePhone.fourDigit"].focus();
	    return false;
	}

	if (theForm["currentFieldVisit.alternatePhone.prefix"].value == "" && theForm["currentFieldVisit.alternatePhone.fourDigit"].value > "") {
	    alert("Alternate Phone Number Prefix must be entered if Alternate Phone Number Last4Digit number is entered.");
	    theForm["currentFieldVisit.alternatePhone.prefix"].focus();
	    return false;
	}
	return result;
}
function preSelectStateCounty(){
	var st = document.getElementsByName("stateId");
	var cnty = document.getElementsByName("countyId");
	if (st[0].selectedIndex == 0){
		for (y=0; y < st[0].options.length; y++){
			if (st[0].options[y].value == "TX"){
				st[0].selectedIndex = y;
				for (z=0; z < cnty[0].options.length; z++){
					if (cnty[0].options[z].value == "101"){
						cnty[0].selectedIndex = z;
						cnty[0].disabled = false;
					    break
					}    
				}	
				break;
			}
		}		
	}	
}
</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="checkEnterKeyAndSubmit(event, true)" onload="checkSOType(document.forms[0]['currentFieldVisit.fieldVisitTypeCd']);">
		<%--  onload="checkForOther(document.forms[0]['currentFieldVisit.purposeCd']); 
		        checkFVType(document.forms[0]['currentFieldVisit.fieldVisitTypeCd']);			
			    checkSOCat(document.forms[0]['currentFieldVisit.sexOffenderCategoryCd']);
			    preSelectStateCounty()" --%>
	<html:form action="/displayCSFVEventSummary" target="content" onsubmit="return validateMethodOfContact(this)">						
		<div align="center">
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
									<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true">
										<tiles:put name="tab" value="caseloadTab"/>
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
								<td align=center>
									<!--header start-->
									<tiles:insert page="../../../common/superviseeHeader.jsp" flush="true">
									</tiles:insert>	
									<!--header end-->
								</td>
							</tr>
							<tr>
								<td valign="top" align="center">										
									<!--casefile tabs start-->
									<table width="98%" border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
										</tr>
										<tr>
											<td valign="top">
												<!--tabs start-->
												<tiles:insert page="../../../common/caseloadCSCDSubTabs.jsp" flush="true">
													<tiles:put name="tab" value="CalendarTab"/> 
												</tiles:insert>	
												<!--tabs end-->
											</td>
										</tr>
										<tr>
											<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
										</tr>
									</table>
									<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
										<tr>
											<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
										</tr>
										<tr>
											<td valign="top" align="center">
												<!-- BEGIN HEADING TABLE -->
												<table width="100%">
													<tr>
														<td align="center" class="header">CSCD - 
															<logic:equal name="csCalendarFVForm" property="activityFlow" value="create">
																<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|30">
																<bean:message key="prompt.create"/>
															</logic:equal>
															<logic:equal name="csCalendarFVForm" property="activityFlow" value="update">
																<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|52">
																<bean:message key="prompt.update"/>
															</logic:equal>
															<logic:equal name="csCalendarFVForm" property="activityFlow" value="reschedule">
																<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|52">
																<bean:message key="prompt.update"/>
															</logic:equal>
															<bean:message key="prompt.fieldVisit"/>
															<logic:equal name="csCalendarFVForm" property="activityFlow" value="enterResults">
																<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|55">
																<bean:message key="prompt.results"/>
															</logic:equal>
														</td>
													</tr>
												</table>
												<!-- END HEADING TABLE -->
												<!-- BEGIN INSTRUCTION TABLE -->
												<table width="98%" border="0">
													<tr>
														<td>
															<ul>
																<li>Enter required fields. Click Next.</li>
															</ul>
														</td>
													</tr>
													<tr>
														<td class=required> <bean:message key="prompt.4.diamond"/> Required Field &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<bean:message key="prompt.4.diamond"/><bean:message key="prompt.4.diamond"/> Required for Validate Address</td>
													</tr>
												</table>
												<!-- END INSTRUCTION TABLE -->
												<!-- BEGIN DETAIL TABLE -->
													
												<tiles:insert page="../../../csCalendar/fieldVisit/fieldVisitItineraryTile.jsp" flush="true">
													<tiles:put name="itinerary" beanName="csCalendarFVForm" beanProperty="currentItinerary" />
													<tiles:put name="eventsList" beanName="csCalendarFVForm" beanProperty="eventsList" />
													<tiles:put name="displayEvents" value="true"/> 
												</tiles:insert>
												
												<div class="spacer4px"></div>
												<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
													<tr>
														<td class="detailHead">Field Visit Information</td>
													</tr>
													<tr>
														<td>
															<table width='100%' cellpadding="2" cellspacing="1" border=0>
																<logic:equal name="csCalendarFVForm" property="currentFieldVisit.statusCd" value="C">
																	<tr>
																		<td class="formDeLabel" nowrap><bean:message key="prompt.4.diamond"/><bean:message key="prompt.outcome"/></td>
																		<td class="formDe" colspan=3>
																			<html:select property="currentFieldVisit.outcomeCd">
																				<html:option value=""><bean:message key="select.generic"/></html:option>
																				<html:optionsCollection name="csCalendarFVForm" property="currentFieldVisit.filteredFVOutcomeList" value="supervisionCode" label="description"/>
																			</html:select>
																		</td>
																	</tr>																
																	<tr>
																		<td class="formDeLabel" colspan="4"><bean:message key="prompt.4.diamond"/><bean:message key="prompt.narrative"/></td>
																	</tr>
																	<style>
																		.mceEditor{
																		width: "100%";
																		height: "150"
																		}
																	</style>
																	<tr class="formDe">
																		<td colspan="4" align="center">
																		<nest:define id="agencyId" name="csCalendarFVForm" property="agencyId"/>
																		<html:textarea styleClass="mceEditor" style="width:100%" rows="12" property="currentFieldVisit.narrative" ondblclick="myReverseTinyMCEFix(this)"/>
																		<tiles:insert page="../../../common/spellCheckButtonTile.jsp" flush="false">
																	         <tiles:put name="agencyCode"><%=agencyId%></tiles:put>
																        </tiles:insert>	
																		
																		</td>
																	</tr>
																</logic:equal>	
																
																<tr>
																	<td class="formDeLabel" nowrap>
																		<bean:message key="prompt.4.diamond"/><bean:message key="prompt.purpose"/>
																	</td>
																	<td class="formDe" colspan="3">
																		<html:select property="currentFieldVisit.purposeCd" onchange="checkForOther(this)">
																			<option value=""><bean:message key="select.generic"/></option>
																			<html:optionsCollection name="csCalendarFVForm" property="purposeList" value="supervisionCode" label="description"/>
																		</html:select> <span id=otherTextField class="hidden"><html:text property="currentFieldVisit.otherPurpose" /></span>
																	
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel" nowrap><bean:message key="prompt.4.diamond"/><bean:message key="prompt.fieldVisitType"/></td>
																	<td class="formDe">
																		<html:select property="currentFieldVisit.fieldVisitTypeCd" onchange="checkFVType(this)">
																			<option value=""><bean:message key="select.generic"/></option>
																			<html:optionsCollection name="csCalendarFVForm" property="fvTypeList" value="supervisionCode" label="description"/>
																		</html:select>
																	</td>
																	<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.fieldVisit"/> Date</td>
																	<td class="formDe">
																		<bean:write name="csCalendarFVForm" property="currentFieldVisit.fieldVisitDate" formatKey="date.format.mmddyyyy" />
																	</td>
																</tr>
																<tr id=sexOffender1 class="hidden">
																	<td class="formDeLabel" nowrap><bean:message key="prompt.4.diamond"/><bean:message key="prompt.sexOffender"/> Category</td>
																	<td class="formDe" colspan=3>
																		<html:select property="currentFieldVisit.sexOffenderCategoryCd" onchange="checkSOCat(this)">
																			<option value=""><bean:message key="select.generic"/></option>
																			<html:optionsCollection name="csCalendarFVForm" property="sexOffenderCategoryList" value="supervisionCode" label="description"/>
																		</html:select>
																	</td>
																</tr>											
																<tr id=sexOffender2 class="hidden">
																	<td class="formDeLabel" nowrap><bean:message key="prompt.4.diamond"/><bean:message key="prompt.measurementResult"/></td>
																	<td class="formDe" colspan="3">
																		<html:select property="currentFieldVisit.measurementResultCd">
																			<option value=""><bean:message key="select.generic"/></option>
																			<html:optionsCollection name="csCalendarFVForm" property="measurementTypeList" value="supervisionCode" label="description"/>
																		</html:select>
																	</td>
																</tr>																
																<tr>
																	<td class="formDeLabel" nowrap colspan="4">
																		<span class="hidden" id=commentsReqImg><bean:message key="prompt.4.diamond"/></span>Comments (Max Characters Allowed: 255)
																	</td>
																</tr>
																<tr>
																	<td class="formDe" colspan="4">
																		<html:textarea property="currentFieldVisit.comments" style="width:100%" rows="3" onmouseout="textLimit( this, 255 )" onkeyup="textLimit( this, 255 )"/>
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel" nowrap colspan="4">
																		<table width="100%" cellpadding="0" cellspacing="0">
																			<tr>
																				<td class="formDeLabel">Noteworthy Conditions (Max Characters Allowed: 1000</td>
																				<td align=right><a href="javascript:openWindow('/<msp:webapp/>displayComplianceConditions.do?submitAction=Link&superviseeId=<bean:write name='csCalendarFVForm' property='currentFieldVisit.superviseeId'/>')" class=blackSubNav>View Conditions</a></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td class="formDe" colspan="4">
																		<html:textarea property="currentFieldVisit.noteworthyConditions" style="width:100%" rows="3" onmouseout="textLimit( this, 1000 )" onkeyup="textLimit( this, 1000 )"/>
																	</td>
																</tr>
																
																<logic:equal name="csCalendarFVForm" property="currentFieldVisit.statusCd" value="C">
																	<tr>
																		<td class="formDeLabel" nowrap><bean:message key="prompt.4.diamond"/><bean:message key="prompt.methodOfContact"/></td>
																		<td class="formDe" valign="top" colspan="3"> 
																			<html:select property="currentFieldVisit.methodOfContactCd" onchange="renderAssociatesSelect(this)">
																				<option value=""><bean:message key="select.generic"/></option>
																				<html:optionsCollection name="csCalendarFVForm" property="currentFieldVisit.methodOfContactList" value="supervisionCode" label="description"/>																		
																			</html:select>	
																			
																			<span id="associatesIdsSpan" class="hidden">
																				<html:select property="currentFieldVisit.associateIds" size="3" multiple="true">
																					<html:option value=""><bean:message key="select.generic" /></html:option>
																					<html:optionsCollection  name="csCalendarFVForm" property="currentFieldVisit.superviseeAssociates" value="associateId" label="displayLabel" />
																				</html:select>
																				<a href="javascript:changeFormActionURL(document.forms[0].name,'/<msp:webapp/>handleAssociateCasenoteDisplayOptions.do?submitAction=<bean:message key="button.link"/>&selectedValue=<bean:write name="csCalendarFVForm" property="currentFieldVisit.superviseeId" />&fromPath=<%=UIConstants.FROM_CASELOAD%>',true)">
																					<bean:message key="button.addAssociate"/>
																				</a>
																			</span>
																			
																			<script>renderAssociatesSelect(document.forms[0]["currentFieldVisit.methodOfContactCd"])</script>
																		</td>
																	</tr>
																</logic:equal>	
																
																<tr>
																	<td class="formDeLabel" nowrap>Start Time</td>
																	<td class="formDe"> 
																		<html:text name="csCalendarFVForm" size="6" maxlength="5" property="currentFieldVisit.startTime"/>
																             <html:select name="csCalendarFVForm" property="currentFieldVisit.AMPMId1">
																	           <html:option value="AM">AM</html:option>
																	           <html:option value="PM">PM</html:option>
																             </html:select> 	
																	</td>
																	<td class="formDeLabel" nowrap>End Time</td>
																	<td class="formDe">
																		<html:text name="csCalendarFVForm" size="6" maxlength="5" property="currentFieldVisit.endTime"/>
																        <html:select name="csCalendarFVForm" property="currentFieldVisit.AMPMId2">
																	    	<html:option value="AM">AM</html:option>
																	        <html:option value="PM">PM</html:option>
																        </html:select> 
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel" width="1%" nowrap>Supervisee Address</td>
																	<td class="formDe">
																		<div>
																			<bean:write name="csCalendarFVForm" property="currentFieldVisit.superviseeAddress.streetNum"/>
																			<bean:write name="csCalendarFVForm" property="currentFieldVisit.superviseeAddress.streetName"/>
																			<bean:write name="csCalendarFVForm" property="currentFieldVisit.superviseeAddress.streetType"/>
																			<logic:notEqual name="csCalendarFVForm" property="currentFieldVisit.superviseeAddress.aptNum" value="">
																			<bean:message key="prompt.aptSuite"/>
																			<bean:write name="csCalendarFVForm" property="currentFieldVisit.superviseeAddress.aptNum"/>
																			</logic:notEqual>
																		</div>
																		<div>
																			<bean:write name="csCalendarFVForm" property="currentFieldVisit.superviseeAddress.cityStateZip"/>
																		</div>
																	</td>
																	<td class="formDeLabel" nowrap><bean:message key="prompt.keymap"/></td>
																	<td class="formDe"><html:text property="currentFieldVisit.keyMap" size="4" maxlength="4" /></td>
																</tr>
																<tr>
																	<td class="formDeLabel" nowrap><bean:message key="prompt.phoneNo"/></td>
																	<td class="formDe" colspan="3"><bean:write name="csCalendarFVForm" property="currentFieldVisit.superviseePhone.formattedPhoneNumber" /></td>
																</tr>
																
																<tr>
																	<td colspan="4">
																		<!--tabs start-->
																		<tiles:insert page="../../../common/enterAddressTile.jsp" flush="true">
																			<tiles:put name="address" beanName="csCalendarFVForm" beanProperty="currentFieldVisit.alternateAddress" />
																			<tiles:put name="attrPrefix" value="currentFieldVisit.alternateAddress."/>
																			<tiles:put name="title" value="Alternate Address"/>
																			<tiles:put name="headerColor" value="gray"/>
																			<tiles:put name="originatePage" value="/jsp/administerCaseload/calendar/fieldVisit/fieldVisitCreateUpdate.jsp"/>
																			<tiles:put name="numberOfDiamonds" value="2"/>
																		</tiles:insert>
																		<!--tabs end-->
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel" width=1% nowrap>Alternate Phone</td>
																	<td colspan=3 class="formDe">
																		<html:text property="currentFieldVisit.alternatePhone.areaCode" size="3" onkeyup="return autoTab(this, 3);"/>
																		-
																		<html:text property="currentFieldVisit.alternatePhone.prefix" size="3" onkeyup="return autoTab(this, 3);"/>
																		-
																		<html:text property="currentFieldVisit.alternatePhone.fourDigit" size="4" onkeyup="return autoTab(this, 4);"/>
																	</td>	
																</tr>
																<tr>
																	<td colspan="4" class="formDeLabel">Address Description (Max Characters Allowed: 255)</td>
																</tr>
																<tr>
																	<td colspan="4" class="formDe">
																		<html:textarea property="currentFieldVisit.addressDescription" style="width:100%" rows="2" onmouseout="textLimit( this, 255 )" onkeyup="textLimit( this, 255 )" />
																	</td>
																</tr>
																<tr>
																	<td colspan="4" class="formDeLabel">Caution (Max Characters Allowed: 255)</td>
																</tr>
																<tr>
																	<td colspan="4" class="formDe">
																		<html:textarea property="currentFieldVisit.caution" style="width:100%" rows="2" onmouseout="textLimit( this, 255 )" onkeyup="textLimit( this, 255 )"/>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
												<!-- BEGIN BUTTON TABLE -->
												<table border="0" width="100%">
													<tr>
														<td align="center">
															<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
															<logic:equal name="csCalendarFVForm" property="activityFlow" value="reschedule">
															    <html:submit property="submitAction" onclick="return (myTinyMCEFix() && validateFields(this.form) && validateFieldVisitCreateUpdate(this.form))"><bean:message key="button.next" /></html:submit>
															</logic:equal>
															<logic:notEqual name="csCalendarFVForm" property="currentFieldVisit.statusCd" value="C">
															    <logic:notEqual name="csCalendarFVForm" property="activityFlow" value="reschedule">
															        <html:submit property="submitAction" onclick="return (myTinyMCEFix() && validateFields(this.form) && validateFieldVisitCreateUpdate(this.form) && validateSexOffender(this.form) && validateELM(this.form) && validateOtherPurpose(this.form))"><bean:message key="button.next" /></html:submit>
															    </logic:notEqual>
															</logic:notEqual>
															<logic:equal name="csCalendarFVForm" property="currentFieldVisit.statusCd" value="C">
															    <html:submit property="submitAction" onclick="return (myTinyMCEFix() && validateSexOffender(this.form) && validateELM(this.form) && validateFieldVisitCreateUpdate(this.form) && validateOtherPurpose(this.form) && validateFields(this.form) && validateFieldVisitResults(this.form))"><bean:message key="button.next" /></html:submit>
															</logic:equal>
															<input type="reset" value="Reset">
															<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>																								
														</td>
													</tr>
												</table>
												<!-- END BUTTON TABLE -->
											</td>
										</tr>
									</table><br>
								</td>
							</tr>
						</table><br>
					</td>
				</tr>
			</table>
		</div>
	</html:form>
	<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>