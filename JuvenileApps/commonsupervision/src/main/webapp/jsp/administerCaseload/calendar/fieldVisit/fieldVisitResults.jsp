<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 01/30/2008	 Aswin Widjaja - Create JSP -->
<!-- 03/18/2009  CShimek       - #57838 corrected associate display when page accessed via back button.  Also fixed problem of Back and Cancel buttons validating input(this was not part of defect) -->
<!-- 02/22/2010  CShimek       - #64108 corrected originatedPage value to this page -->
<!-- 06/25/2010  L Deen        - Activity #66169 change to tinyMCECustomInitCasenote.js-->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
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
<title><bean:message key="title.heading" /> - csCalendar - fieldVisitResults.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script language="javascript" type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script language="javascript" type="text/javascript" src="/<msp:webapp/>js/tinyMCECustomInitCasenote.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
		
<script>
function renderAssociatesSelect(el){
	if (el.options[el.selectedIndex].value=="AS"){
		show("associatesIdsSpan", 1)
		document.getElementById("currentFieldVisit.associateIds").selectedIndex=0;
	}
	else {
			show("associatesIdsSpan", 0)
			document.getElementById("currentFieldVisit.associateIds").selectedIndex=0;
	}
}

function validateMethodOfContact(theForm) {

	if (theForm.buttonSelected.value.toUpperCase() == "BACK" || theForm.buttonSelected.value.toUpperCase() == "CANCEL"){
		return true;
	}	
	if(theForm["currentFieldVisit.methodOfContactCd"].value == "AS" && theForm["currentFieldVisit.associateIds"].selectedIndex < 1) {
		alert("Please select one or more associate(s).");
		theForm["currentFieldVisit.associateIds"].focus();
		return false;
	}
}


function checkMethodOfContact(){
	var mc = document.getElementsByName("currentFieldVisit.methodOfContactCd");
	if (mc.length > 0){
		if (mc[0].options[mc[0].options.selectedIndex].value == "AS"){
			show("associatesIdsSpan", 1)
			document.getElementById("currentFieldVisit.associateIds").selectedIndex=0;
		}	
	}	
}

function validateFields(theForm)
{    
    clearAllValArrays();
    trimCasenote("currentFieldVisit.narrative");
    customValRequired("currentFieldVisit.outcomeCd", "<bean:message key='errors.required' arg0='Outcome'/>","");
    customValRequired("currentFieldVisit.narrative", "<bean:message key='errors.required' arg0='Narrative'/>","");
    customValRequired("currentFieldVisit.comments", "<bean:message key='errors.required' arg0='Comments'/>","");
    customValRequired("currentFieldVisit.methodOfContactCd", "<bean:message key='errors.required' arg0='Method of Contact'/>","");
    <logic:equal name="csCalendarFVForm" property="currentFieldVisit.fieldVisitTypeCd" value="SO">	
		<jims:if name="csCalendarFVForm" property="currentFieldVisit.sexOffenderCategoryCd" value="CZR" op="equal">
			<jims:or name="csCalendarFVForm" property="currentFieldVisit.sexOffenderCategoryCd" value="CZE" op="equal"/>
				<jims:then>
				customValRequired("currentFieldVisit.measurementResultCd", "<bean:message key='errors.required' arg0='Measurement Result'/>","");
				</jims:then>
		</jims:if>	
	</logic:equal>    
    customValMinLength("currentFieldVisit.alternatePhone.areaCode","<bean:message key='errors.minlength' arg0='Alternate Phone Area Code' arg1='3'/>","3");
    addNumericValidation("currentFieldVisit.alternatePhone.areaCode", "<bean:message key='errors.numeric' arg0='Alternate Phone Area Code'/>","");
    customValMinLength("currentFieldVisit.alternatePhone.prefix","<bean:message key='errors.minlength' arg0='Alternate Phone Prefix' arg1='3'/>","3");
    addNumericValidation("currentFieldVisit.alternatePhone.prefix", "<bean:message key='errors.numeric' arg0='Alternate Phone Prefix'/>","");
    customValMinLength("currentFieldVisit.alternatePhone.fourDigit","<bean:message key='errors.minlength' arg0='Alternate Phone Four Digit' arg1='4'/>","4");
    addNumericValidation("currentFieldVisit.alternatePhone.fourDigit", "<bean:message key='errors.numeric' arg0='Alternate Phone Four Digit'/>","");   
	customValMaxLength("currentFieldVisit.addressDescription", "Address Description cannot be more than 255 characters","255");
	addAlphaNumericNoFirstSpacewSymbolsValidation("currentFieldVisit.addressDescription", "<bean:message key='errors.alphanumericWSymbols2' arg0='Address Description'/>",""); 
    customValMaxLength("currentFieldVisit.caution", "Caution cannot be more than 255 characters","255");
	addDefinedDB2Mask("currentFieldVisit.caution", "<bean:message key='errors.alphanumericWSymbols2' arg0='Caution'/>","");
	
	customValMaxLength("currentFieldVisit.comments", "Comments cannot be more than 255 characters","255");	
	customValMaxLength("currentFieldVisit.narrative","<bean:message key='errors.maxlength' arg0='Narrative' arg1='3500'/>","7000");
	addDefinedTinyMCEFieldMask("currentFieldVisit.narrative","Narrative Text cannot have % or _ entries","");
	add12HrTimeValidation("currentFieldVisit.startTime","Start Time is not in proper 12 hour format, ie 03:15","");
    add12HrTimeValidation("currentFieldVisit.endTime","End Time is not in proper 12 hour format, ie 03:15","");
    
    if (validateCustomStrutsBasedJS(theForm) && validateForBROnly(document.getElementsByName('currentFieldVisit.narrative')[0].value, 'Narrative is required')){
		return true;
	}else {
		return false;
	}	
}

                        
</script>	
</head>
<body topmargin="0" leftmargin="0" onKeyDown="checkEnterKeyAndSubmit(event, true)" onload="checkMethodOfContact()">

	<bean:define id="userAgency" name="csCalendarFVForm" property="agencyId"/>
	<tiles:insert page="../../../common/spellCheckButtonTile.jsp" flush="false">
 	<tiles:put name="agencyCode"><%=userAgency%></tiles:put>
 	</tiles:insert>	
	<html:form action="/displayCSFVEventSummary" target="content">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|55">		
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
								<td align="center">
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
														<td align="center" class="header">CSCD - Field Visit Results
														</td>
													</tr>
												</table>
												<!-- END HEADING TABLE -->
												<!-- BEGIN INSTRUCTION TABLE -->
												<table width="98%" border="0">
												<logic:present name="confirmMsg">
													<tr id="confirmations">
														<td class="confirm">
															<bean:write name="confirmMsg"/>
														</td>
													</tr>
												</logic:present>
													<tr>
														<td>
															<ul>
																<li>Enter required fields. Click Next.</li>
															</ul>
														</td>
													</tr>
													<tr>
														<td class="required"> <bean:message key="prompt.4.diamond"/> Required Field &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															<bean:message key="prompt.4.diamond"/><bean:message key="prompt.4.diamond"/> Required for Validate Address
														</td>
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
														<td class="detailHead"><bean:message key="prompt.fieldVisit"/> <bean:message key="prompt.information"/></td>
													</tr>
													<tr>
														<td>
															<table width='100%' cellpadding="2" cellspacing="1" border="0">
																<tr>
																	<td class="formDeLabel" nowrap><bean:message key="prompt.4.diamond"/> <bean:message key="prompt.outcome"/></td>
																	<td class="formDe" colspan="3">
																		<html:select property="currentFieldVisit.outcomeCd">
																			<html:option value=""><bean:message key="select.generic"/></html:option>
																			<html:optionsCollection name="csCalendarFVForm" property="currentFieldVisit.filteredFVOutcomeList" value="supervisionCode" label="description"/>
																		</html:select>
																	</td>
																</tr>																
																<tr>
																	<td class="formDeLabel" colspan="4" ><bean:message key="prompt.4.diamond"/><bean:message key="prompt.narrative"/></td>
																</tr>
																<style>
																	.mceEditor{
																	width: "100%";
																	height: "150"
																	}
																</style>
																<tr class="formDe">
																	<td colspan="4">
																		<html:textarea styleClass="mceEditor" style="width:100%" rows="12" property="currentFieldVisit.narrative"/>																	    
																	</td>
																</tr>
																
																<tr>
																	<td class="formDeLabel"><bean:message key="prompt.purpose"/></td>
																	<td class="formDe" colspan="3"><bean:write name="csCalendarFVForm" property="currentFieldVisit.purposeDesc"/></td>
																</tr>
																<tr>
																	<td class="formDeLabel" width=25%><bean:message key="prompt.fieldVisitType"/></td>
																	<td class="formDe"><bean:write name="csCalendarFVForm" property="currentFieldVisit.fieldVisitTypeDesc"/></td>
																	<td class="formDeLabel" nowrap width=25%><bean:message key="prompt.fieldVisit"/> <bean:message key="prompt.date"/></td>
																	<td class="formDe">
																		<bean:write name="csCalendarFVForm" property="currentFieldVisit.fieldVisitDate" formatKey="date.format.mmddyyyy" />
																	</td>
																</tr>
																<logic:equal name="csCalendarFVForm" property="currentFieldVisit.fieldVisitTypeCd" value="SO">
																	<tr>
																		<td class="formDeLabel" nowrap><bean:message key="prompt.4.diamond"/>Sex Offender Category</td>
																		<td class="formDe" colspan="3">
																			<bean:write name="csCalendarFVForm" property="currentFieldVisit.sexOffenderCategoryDesc"/>
																		</td>
																	</tr>
																	<jims:if name="csCalendarFVForm" property="currentFieldVisit.sexOffenderCategoryCd" value="CZR" op="equal">
																		<jims:or name="csCalendarFVForm" property="currentFieldVisit.sexOffenderCategoryCd" value="CZE" op="equal"/>
																			<jims:then>
																				<tr>
																					<td class="formDeLabel"><bean:message key="prompt.4.diamond"/><bean:message key="prompt.measurementResult"/></td>
																					<td class="formDe" colspan="3">
																						<html:select property="currentFieldVisit.measurementResultCd">
																							<html:option value=""><bean:message key="select.generic"/></html:option>
																							<html:optionsCollection name="csCalendarFVForm" property="measurementTypeList" value="supervisionCode" label="description"/>
																						</html:select>
																					</td>
																				</tr>
																			</jims:then>
																	</jims:if>	
																</logic:equal>
																<tr>
																	<td class="formDeLabel" colspan="4" >
																		<bean:message key="prompt.4.diamond"/> <bean:message key="prompt.comments"/>
																	</td>
																</tr>
																<tr>
																	<td class="formDe" colspan="4" >
																		<html:textarea property="currentFieldVisit.comments" style="width:100%" rows="3" />
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel" colspan="4" >Noteworthy Conditions</td>
																</tr>
																<tr>
																	<td class="formDe" colspan="4">
																		<bean:write name="csCalendarFVForm" property="currentFieldVisit.noteworthyConditions" />&nbsp;
																	</td>
																</tr>								
												
																<tr>
																	<td class="formDeLabel" nowrap><bean:message key="prompt.4.diamond"/> <bean:message key="prompt.methodOfContact"/></td>
																	<td class="formDe" valign="top" colspan="3"> 
																		<html:select property="currentFieldVisit.methodOfContactCd" onchange="renderAssociatesSelect(this)">
																			<html:option value=""><bean:message key="select.generic"/></html:option>
																			<html:optionsCollection name="csCalendarFVForm" property="currentFieldVisit.methodOfContactList" value="supervisionCode" label="description"/>																		
																		</html:select>	
																		
																		<span id="associatesIdsSpan" class="hidden">
																		<div class=spacer></div>
																			<html:select property="currentFieldVisit.associateIds" size="3" multiple="true">
																				<html:option value=""><bean:message key="select.generic" /></html:option>
																				<html:optionsCollection  name="csCalendarFVForm" property="currentFieldVisit.superviseeAssociates" value="associateId" label="displayLabel" />
																			</html:select>
																			<div><a href="javascript:changeFormActionURL(document.forms[0].name,'/<msp:webapp/>handleFVAssociateDisplayOptions.do?submitAction=<bean:message key="button.link"/>&selectedValue=<bean:write name="csCalendarFVForm" property="currentFieldVisit.superviseeId" />&fromPath=<%=UIConstants.FROM_FIELD_RESULTS%>',true)">
																				<bean:message key="button.addAssociate"/>
																			</a></div>
																		</span>															
																	</td>																	
																</tr>
																<tr>
																	<td class="formDeLabel"><bean:message key="prompt.startTime"/></td>
																	<td class="formDe"> 
																		<html:text name="csCalendarFVForm" size="6" maxlength="5" property="currentFieldVisit.startTime"/>
																             <html:select name="csCalendarFVForm" property="currentFieldVisit.AMPMId1">
																	           <html:option value="AM">AM</html:option>
																	           <html:option value="PM">PM</html:option>
																             </html:select>
																	</td>
																	<td class="formDeLabel" nowrap><bean:message key="prompt.endTime"/></td>
																	<td class="formDe">
																		<html:text name="csCalendarFVForm" size="6" maxlength="5" property="currentFieldVisit.endTime"/>
																             <html:select name="csCalendarFVForm" property="currentFieldVisit.AMPMId2">
																	           <html:option value="AM">AM</html:option>
																	           <html:option value="PM">PM</html:option>
																        </html:select>
																	</td>
																</tr>

																<tr>
																	<td class="formDeLabel"><bean:message key="prompt.supervisee"/> <bean:message key="prompt.address"/></td>
																	<td class="formDe">
																		<div>
																			<bean:write name="csCalendarFVForm" property="currentFieldVisit.superviseeAddress.streetNum"/>
																			<bean:write name="csCalendarFVForm" property="currentFieldVisit.superviseeAddress.streetName"/>
																			<bean:write name="csCalendarFVForm" property="currentFieldVisit.superviseeAddress.streetType"/>
																			<logic:notEmpty  name="csCalendarFVForm" property="currentFieldVisit.superviseeAddress.aptNum">
																				<bean:message key="prompt.aptSuite"/> 
																				<bean:write name="csCalendarFVForm" property="currentFieldVisit.superviseeAddress.aptNum"/>
																			</logic:notEmpty>
																		</div>
																		<div>
																			<bean:write name="csCalendarFVForm" property="currentFieldVisit.superviseeAddress.cityStateZip"/>
																		</div>
																	</td>
																	<td class="formDeLabel"><bean:message key="prompt.keymap"/></td>
																	<td class="formDe"><bean:write name="csCalendarFVForm" property="currentFieldVisit.keyMap" /></td>
																</tr>
																<tr>
																	<td class="formDeLabel"><bean:message key="prompt.phone"/></td>
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
																			<tiles:put name="originatePage" value="/jsp/administerCaseload/calendar/fieldVisit/fieldVisitResults.jsp"/>
																			<tiles:put name="numberOfDiamonds" value="2"/>
																		</tiles:insert>
																		<!--tabs end-->
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel">Alternate Phone</td>
																	<td colspan="3" class="formDe">
																		<html:text property="currentFieldVisit.alternatePhone.areaCode" size="3" onkeyup="return autoTab(this, 3);"/>
																		-
																		<html:text property="currentFieldVisit.alternatePhone.prefix" size="3" onkeyup="return autoTab(this, 3);"/>
																		-
																		<html:text property="currentFieldVisit.alternatePhone.fourDigit" size="4" onkeyup="return autoTab(this, 4);"/>
																	</td>	
																</tr>
																<tr>
																	<td colspan="4" class="formDeLabel">
																		<bean:message key="prompt.address"/> <bean:message key="prompt.description"/>
																	</td>
																</tr>
																<tr>
																	<td colspan="4" class="formDe">
																		<html:textarea property="currentFieldVisit.addressDescription" style="width:100%" rows="2" />
																	</td>
																</tr>
																<tr>
																	<td colspan="4" class="formDeLabel">Caution</td>
																</tr>
																<tr>
																	<td colspan="4" class="formDe">
																		<html:textarea property="currentFieldVisit.caution" style="width:100%" rows="2" />
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
															<html:submit property="submitAction" onclick="return (myTinyMCEFix() && validateFields(this.form) && validateMethodOfContact(this.form))"><bean:message key="button.next" /></html:submit>
															<input type="reset" value="Reset">
															<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
															<input type="hidden" name="buttonSelected" value="" >
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
	<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>