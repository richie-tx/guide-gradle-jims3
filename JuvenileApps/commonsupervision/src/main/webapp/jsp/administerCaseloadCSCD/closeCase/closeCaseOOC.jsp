<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 06/20/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 07/20/2010 Richard Capestani - #66292 OOC - Missing OPD on migrated cases (PD/UI) -->
<!-- 12/28/2010 Dawn Gibler - changed getter of updateReasonList  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest"%>

<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@ page import="naming.PDCodeTableConstants" %>
<%@ page import="naming.CloseCaseConstants" %>

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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/closeCase/closeCaseOOC.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script language="javascript" type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script>

function checkPersonID()
{
	if(document.forms[0].personId.value == "")
	{
		alert("Contact Information Services-Person Id missing.");
		return false;
	}	
}

function validateFields(theForm)
{
	clearAllValArrays();
	customValRequired("transferOutDateStr","<bean:message key='errors.required' arg0='Transfer Out Date'/>","");
	customValRequired("closureReasonId","<bean:message key='errors.required' arg0='Closure Reason'/>","");
	addMMDDYYYYDateValidation("transferOutDateStr","<bean:message key='errors.date' arg0='Transfer Out Date'/>","");
	if(theForm.action.value=="updateClosure")
	{
		 customValRequired("reasonForUpdateId","<bean:message key='errors.required' arg0='Reason For Update'/>","");
	}
	var result = validateCustomStrutsBasedJS(theForm);
	if (result){
		if (theForm.transferOutDateStr.value.length > 0) {
			var inDate = new Date(theForm.transferInDateX.value);
			var outDate = new Date(theForm.transferOutDateStr.value);

			if (outDate < inDate) {
				alert('Transfer Out Date must be greater than the Transfer In date of: ' + theForm.transferInDateAsStringX.value);
				theForm.transferOutDateStr.focus();
				return false;
			}
		}
	}
	return result;
}
</script>


</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="checkPersonID();">
<html:form action="/displayOutOfCountyCaseClose" target="content" focus="transferOutDateStr">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Close_Supervision/OOC_Case/Close_Supervision_OOC_Case.htm#|2">
<input type="hidden" name="transferInDateX" value=<bean:write name="outOfCountyCaseForm" property="transferInDate" formatKey="date.format.mmddyyyy"  /> />
<input type="hidden" name="transferInDateAsStringX" value=<bean:write name="outOfCountyCaseForm" property="transferInDateAsString"  /> />

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
							<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
							    <tiles:put name="tab" value="setupTab" />
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
							<table width="98%" border="0" cellpadding="0" cellspacing="0" >
								<tr>
									<td valign="top">
										<!--tabs start-->
										<tiles:insert page="../../common/manageFeaturesTabs.jsp" flush="true">
											<tiles:put name="tab" value="oocTab" />
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
										<!-- BEGIN HEADING INFO -->
										<table align="center">
											<tr>
												<td align="center" class="header">
													<bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.outOfCountyCase"/>&nbsp;-&nbsp;
												<logic:equal name="outOfCountyCaseForm" property="action" value="<%= CloseCaseConstants.OOC_CASE_UPDATE_CLOSURE %>">
													<bean:message key="prompt.update"/>&nbsp;<bean:message key="prompt.closure"/>
												</logic:equal> 
												<logic:equal name="outOfCountyCaseForm" property="action" value="<%= CloseCaseConstants.OOC_CASE_CLOSE %>">    
													<bean:message key="prompt.closeCase"/>
												</logic:equal>
												</td>
											</tr>
										</table>
										<!-- END HEADING INFO -->
										
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
														<li>Enter required fields</li>
													</ul>
												</td>
											</tr>
											<tr>
												<td class="required"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.requiredFieldInstruction"/>&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction"/></td>
											</tr>
										</table>
										<!-- END INSTRUCTION TABLE -->
										
										<!-- BEGIN HEADER TABLE -->
										<tiles:insert page="../../outOfCountyCase/partyInfoHeaderTile.jsp" flush="true">
											<tiles:put name="partyHeader" beanName="outOfCountyCaseForm"/>
										</tiles:insert>	
										<!-- END HEADER TABLE -->
										<br>
										<html:hidden name="outOfCountyCaseForm" property="action" />
										<!-- BEGIN  TABLE -->
										<table width="100%" border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td align="center">
													<table border="0" width="98%" cellspacing="1" cellpadding="2">
														<tr>
															<td colspan="4" class="detailHead">
																<table width="100%" cellpadding="2" cellspacing="0">
																	<tr>
																		<td width="1%"><a href="javascript:showHideMulti('caseID', 'caseIDRow', 5, '/<msp:webapp/>')"><img src="/<msp:webapp/>images/expand.gif" border="0" name="caseID"></a></td>
																		<td class="detailHead"><bean:message key="prompt.case"/>&nbsp;<bean:message key="prompt.identifications"/></td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr id="caseIDRow0" class="hidden">
															<td nowrap class="formDeLabel" width="1%"><bean:message key="prompt.CDI"/></td>
															<td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="courtDivision"/></td>
														</tr>
														<tr id="caseIDRow1" class="hidden">
															<td nowrap class="formDeLabel" width="1%"><bean:message key="prompt.case#"/></td>
															<td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="caseNum"/></td>
														</tr>
														<tr id="caseIDRow2" class="hidden">
															<td class="formDeLabel"><bean:message key="prompt.CJIS#"/></td>
															<td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="cjisNum1" />
					                                  		- <bean:write name="outOfCountyCaseForm" property="cjisNum2" /></td>
														</tr>
														<tr id="caseIDRow3" class="hidden">
															<td class="formDeLabel"><bean:message key="prompt.offense"/></td>
															<td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="offenseId"/> - <bean:write name="outOfCountyCaseForm" property="offense" /></td>
														</tr>
														<tr id="caseIDRow4" class="hidden">
															<td class="formDeLabel" nowrap><bean:message key="prompt.outOfCountyCaseType"/></td>
															<td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="caseType" /></td>
														</tr>
														<tr>
															<td><br>
															</td>
														</tr>
														<tr>
															<td class="detailHead" colspan="4">
																<table width="100%" cellpadding="2" cellspacing="0">
																	<tr>
																		<td width="1%"><a href="javascript:showHideMulti('caseInfo', 'caseInfoRow', 5,'/<msp:webapp/>')"><img src="/<msp:webapp/>images/expand.gif" border="0" name="caseInfo"></a></td>
																		<td class="detailHead"><bean:message key="prompt.caseInfo"/></td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr id="caseInfoRow0" class="hidden">
															<td class="formDeLabel" nowrap><bean:message key="prompt.dispositionType"/></td>
															<td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="dispositionType"/></td>
														</tr>
														<tr id="caseInfoRow1" class="hidden">
															<td class="formDeLabel" nowrap><bean:message key="prompt.dispositionDate"/></td>
															<td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="dispositionDate" formatKey="date.format.mmddyyyy"/></td>
														</tr>
														<tr id="caseInfoRow2" class="hidden">
															<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.confinementLength"/></td>
															<td class="formDe" colspan="3">
					                                  		<bean:write name="outOfCountyCaseForm" property="confinementLengthYear" /> <bean:message key="prompt.years" />&nbsp;
					                                    	<bean:write name="outOfCountyCaseForm" property="confinementLengthMonth" /> <bean:message key="prompt.months" />&nbsp;
					                                    	<bean:write name="outOfCountyCaseForm" property="confinementLengthDay" /> <bean:message key="prompt.days" /></td>
														</tr>
														<tr id="caseInfoRow3" class="hidden">
															<td class="formDeLabel" ><bean:message key="prompt.supervisionLength"/></td>
															<td class="formDe" colspan="3">
					                                  		<bean:write name="outOfCountyCaseForm" property="supervisionLengthYear" /> <bean:message key="prompt.years" />&nbsp;
					                                    	<bean:write name="outOfCountyCaseForm" property="supervisionLengthMonth" /> <bean:message key="prompt.months" />&nbsp;
					                                    	<bean:write name="outOfCountyCaseForm" property="supervisionLengthDay" /> <bean:message key="prompt.days" /></td>
														</tr>
														<tr id="caseInfoRow4" class="hidden">
															<td class="formDeLabel" nowrap><bean:message key="prompt.offenseDate"/></td>
															<td class="formDe"><bean:write name="outOfCountyCaseForm" property="offenseDateAsString" /></td>
															<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.arrestDate"/></td>
															<td class="formDe"><bean:write name="outOfCountyCaseForm" property="arrestDateAsString" /></td>
														</tr>
														<tr>
															<td><br>
															</td>
														</tr>
														<tr>
															<td class="detailHead" colspan="4">
																<table width="100%" cellpadding="2" cellspacing="0">
																	<tr>
																		<td width="1%"><a href="javascript:showHideMulti('supParam', 'supParamRow', 3,'/<msp:webapp/>')"><img src="/<msp:webapp/>images/expand.gif" border=0 name=supParam></a></td>
																		<td class="detailHead"><bean:message key="prompt.supervisionParameters"/></td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr id=supParamRow0 class=hidden>
															<td class="formDeLabel" nowrap><bean:message key="prompt.beginDate"/></td>
															<td class="formDe"><bean:write name="outOfCountyCaseForm" property="supervisionBeginDate" formatKey="date.format.mmddyyyy"/></td>
															<td class="formDeLabel" nowrap><bean:message key="prompt.endDate"/></td>
															<td class="formDe"><bean:write name="outOfCountyCaseForm" property="supervisionEndDate" formatKey="date.format.mmddyyyy"/></td>
														</tr>
														<tr id=supParamRow1 class=hidden>
															<td class="formDeLabel" nowrap><bean:message key="prompt.pretrialInterventionBegin"/></td>
															<td class="formDe"><bean:write name="outOfCountyCaseForm" property="pretrialInterventionBeginAsString" /></td>
															<td class="formDeLabel" nowrap><bean:message key="prompt.pretrialInterventionEnd"/></td>
															<td class="formDe"><bean:write name="outOfCountyCaseForm" property="pretrialInterventionBeginAsString" /></td>
														</tr>
														<tr id=supParamRow2 class=hidden>
															<td class="formDeLabel" width=1% nowrap ><bean:message key="prompt.dateOfSentence"/></td>
															<td class="formDe" colspan=3><bean:write name="outOfCountyCaseForm" property="sentenceDate" formatKey="date.format.mmddyyyy"/></td>
														</tr>
														<tr>
															<td><br>
															</td>
														</tr>
														<tr>
															<td class="detailHead" colspan="4">
																<table width="100%" cellpadding="2" cellspacing="0">
																	<tr>
																		<td class="detailHead"><bean:message key="prompt.originalJurisdiction"/></td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr id=origJurisRow0>
															<td class="formDeLabel"><bean:message key="prompt.case#"/></td>
															<td class="formDe"><bean:write name="outOfCountyCaseForm" property="orgJurisCaseNum"/></td>
															<td class="formDeLabel"><bean:message key="prompt.court"/></td>
															<td class="formDe"><bean:write name="outOfCountyCaseForm" property="orgJurisCourt"/></td>
														</tr>
														<tr id=origJurisRow1>
															<td class="formDeLabel" ><bean:message key="prompt.personID"/></td>
															<td class="formDe"><bean:write name="outOfCountyCaseForm" property="orgJurisPID"/><input type ="hidden" name = "personId" value = "<bean:write name="outOfCountyCaseForm" property="orgJurisPID"/>"/></td>
															<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.transferInDate"/></td>
															<td class="formDe"><bean:write name="outOfCountyCaseForm" property="transferInDate" formatKey="date.format.mmddyyyy"/></td>
														</tr>
														<tr id=origJurisRow2>
															<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.texasCounty"/></td>
															<td class="formDe"><bean:write name="outOfCountyCaseForm" property="orgJurisCounty"/></td>
															<td class="formDeLabel" ><bean:message key="prompt.outOfState"/></td>
															<td class="formDe" id="Out of State Code"><bean:write name="outOfCountyCaseForm" property="state"/></td>
														</tr>
														<tr>
															<td><br>
															</td>
														</tr>
														<tr>
															<td colspan="4" class="detailHead">
																<table width="100%" cellpadding="2" cellspacing="0">
																	<tr>
																		<td width="1%"><a href="javascript:showHideMulti('origJurisInfo', 'origJurisInfoRow', 3,'/<msp:webapp/>')"><img src="/<msp:webapp/>images/expand.gif" border=0 name=origJurisInfo></a></td>
																		<td class="detailHead"><bean:message key="prompt.contactInformationForOriginatingJurisdiction"/></td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr id="origJurisInfoRow0" class="hidden">
															<td class="formDeLabel"><bean:message key="prompt.name"/></td>
															<td colspan="3" class="formDe"><bean:write name="outOfCountyCaseForm" property="contactName"/></td>
														</tr>
														<tr id="origJurisInfoRow1" class="hidden">
															<td class="formDeLabel" nowrap><bean:message key="prompt.jobTitle"/></td>
															<td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="contactJobTitle"/></td>
														</tr>
														<tr id="origJurisInfoRow2" class="hidden">
															<td class="formDeLabel"  nowrap width="1%"><bean:message key="prompt.phone"/></td>
															<td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="contactPhone" formatKey="phone.format"/>
															<logic:notEqual name="outOfCountyCaseForm" property="contactPhone" value="">
																&nbsp;&nbsp;<b><bean:message key="prompt.ext"/></b>&nbsp;<bean:write name="outOfCountyCaseForm" property="contactPhoneExt"/>
															</logic:notEqual></td>
														</tr>
														<!-- Other pages -->
														<tr>
															<td><br>
															</td>
														</tr>
														<tr>
															<td colspan="4" class="detailHead"><bean:message key="prompt.closureInformation"/></td>
														</tr>
														<tr>
															<td class="formDeLabel" nowrap><bean:message key="prompt.3.diamond"/><bean:message key="prompt.transferOutDate"/></td>
															<td class="formDe" colspan="3">
																<SCRIPT LANGUAGE="JavaScript" ID="js1">
																	var cal1 = new CalendarPopup();
																	cal1.showYearNavigation();
																</SCRIPT>
																<html:text name="outOfCountyCaseForm" property="transferOutDateStr" size="10" maxlength="10" />
																<A HREF="#" onClick="cal1.select(transferOutDateStr,'anchor5','MM/dd/yyyy'); return false;" NAME="anchor5" ID="anchor5"><bean:message key="prompt.3.calendar"/></A>
															</td>
														</tr>
														<tr>
															<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.3.diamond"/><bean:message key="prompt.closureReason"/></td>
															<td class="formDe" colspan="3">
																<html:select size="1" name="outOfCountyCaseForm" property="closureReasonId" >
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																	<jims2:codetable codeTableName="<%=PDCodeTableConstants.OOC_DISPOSITION_TYPE%>" sort="true"></jims2:codetable>
																</html:select>
															</td>
														</tr>
														<logic:equal name="outOfCountyCaseForm" property="action" value="<%= CloseCaseConstants.OOC_CASE_UPDATE_CLOSURE %>">
															<tr><td><br></td></tr>
															<tr>
																<td colspan="4" class="detailHead"><bean:message key="prompt.reasonForUpdate"/></td>
															</tr>
															<tr>
																<td class="formDeLabel" ><bean:message key="prompt.3.diamond"/><bean:message key="prompt.reason"/></td>
																<td class="formDe" colspan="3">
																	<html:select size="1" name="outOfCountyCaseForm" property="reasonForUpdateId">
																		<html:option value=""><bean:message key="select.generic" /></html:option>
																		<html:optionsCollection name="outOfCountyCaseForm" property="activeReasonForUpdateList" value="code" label="description" />
																	</html:select>
																</td>
															</tr>
														</logic:equal>
													</table>
													<!-- END TABLE -->
													<br>
													<!-- BEGIN BUTTON TABLE -->
													<table width="98%" border="0">
														<tr>
															<td align="center">
																<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
																<logic:notEqual name="outOfCountyCaseForm" property="orgJurisPID" value="">
																	<html:submit property="submitAction" onclick="return validateFields(this.form);"> <bean:message key="button.next" /></html:submit>
																	<html:reset> <bean:message key="button.reset" /></html:reset>
																</logic:notEqual>
																<logic:equal name="outOfCountyCaseForm" property="orgJurisPID" value="">
																	<input type="button" name="next" value=<bean:message key="button.next" /> disabled="true" />
																	<input type="button" name="reset2" value="<bean:message key="button.reset" />" disabled="true" />
																</logic:equal>
																<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
															</td>
														</tr>
													</table>
													<!-- END BUTTON TABLE -->
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table><br>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>			
</div>
<br>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
