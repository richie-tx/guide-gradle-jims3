<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 02/22/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 02/10/2010 C. Shimek         - #63931 revised Add Treatment input line to match PT to omit left/right scrolling -->
<!-- 07/02/2010 C. Shimek         - #66278 revised comment max. check from onsubmit to active -->
<!-- 10/07/2010 C. Shimek 		  - #67758 revised comment max. from 500 to 3500 and added spell check -->
<!-- 06/10/2011 R Young   		  - #70346 Violation Report-copy Comments in each section(UI) -->
<!-- 11/29/2011 R Capestani       - #72051 CSCD:VR-Change Referral type to enumerated list(UI) -->
<!-- 03/04/2013 R Young           - #75259 Fixed Treatment issues by using the new codetablechildren code table -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDCodeTableConstants" %>
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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/caseHistory/violationReports/vrTreatmentIssues.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/caseHistory/caseHistory_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/caseHistory/violationReports/vrTreatmentIssues.js"></script>
<script>
//constructor
function subgroup(id, name)
{
	this.id = id;
	this.name = name;
	this.childEvents = new Array();
}
var pgmGroups = new Array();
var programTypes = new Array();

<logic:iterate indexId="groupIterIndex" id="groupIter" name="cscServiceProviderSearchForm" property="programHeirarchyList">
	pgmGroups[<bean:write name="groupIterIndex"/>] = new subgroup("<bean:write name="groupIter" property="parentCd" />", "<bean:write name="groupIter" property="parentDesc"/>");

	<logic:iterate indexId="groupIterIndex2" id="groupIter2" name="groupIter" property="childEvents">
		var innerGroup = new subgroup("<bean:write name="groupIter2" property="code" />", "<bean:write name="groupIter2" property="description"/>");
		pgmGroups[<bean:write name="groupIterIndex"/>].childEvents[<bean:write name="groupIterIndex2"/>] = innerGroup;
	</logic:iterate>
</logic:iterate >

function loadPgmTypes()
{
	el = document.getElementsByName("programGroupId")[0];
	var selId = el.options[el.selectedIndex].value;
	var pgms = document.getElementsByName("programTypeId");

	pgms[0].options.length = 0;
	pgms[0].options[0] = new Option( "Please Select", "", false, false );
	pgms[0].disabled = false;
	
	if(el.selectedIndex == 0)
	{
		pgms[0].selectedIndex = 0; //reset choice back to default
		pgms[0].value="";
		pgms[0].disabled = true; //disable group2 choice		
		return;
	}
 
	for(i in pgmGroups)
	{

	if(pgmGroups[i].id == selId)
		{
			for(j in pgmGroups[i].childEvents)
			{
				pgms[0].options[pgms[0].options.length] = new Option( pgmGroups[i].childEvents[j].name, pgmGroups[i].childEvents[j].id);
			}
			break;	
		}
	}
}
</script>
</head>
<body topmargin="0" leftmargin="0" onLoad="setAddCursorPos();" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displayVRTreatmentIssuesSummary" target="content">
<input type="hidden" name="cursorPos" value="<bean:write name='violationReportsForm' property='cursorPosition'/>" >
<div align="center">
<!-- BEGIN PAGE TABLE -->
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->		
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
						</tiles:insert>
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
<!-- ENE BLUE TABS TABLE -->	
<!-- BEGIN BLUE BORDER TABLE -->				
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif"  height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<!-- BEGIN SUPERVISEE HEADER TABLE -->
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td bgcolor="#cccccc" colspan="2">
									<tiles:insert page="../../../common/caseloadHeaderCase.jsp" flush="true">
									</tiles:insert> 
								</td>
							</tr>
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
<!-- END SUPERVISEE HEADER TABLE -->						
					</td>
				</tr>							
				<tr>
					<td valign="top" align="center">
<!-- BEGIN GREEN TABS TABLE -->							
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<tiles:insert page="../../../common/caseloadCSCDSubTabs.jsp" flush="true">
										<tiles:put name="tab" value="CasesTab" />
									</tiles:insert>
								</td>
							</tr>
							<tr>
								<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
<!-- END GREEN TABS TABLE -->
<!-- BEGIN BLUE BORDER TABLE -->								
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header">
												<bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="title.violationReport"/>&nbsp;-
												<bean:message key="prompt.treatmentIssues"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Violation_Reports/CSCD_Violation_Reports.htm#|20">

												
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
													<li>Click Remove Selected to remove selected treatment issues.  Click Next when complete.</li>
												</ul>
											</td>
										</tr>
									</table>
<!-- END INSTRUCTION TABLE -->
									<SCRIPT LANGUAGE="JavaScript" ID="js1">
										var cal1 = new CalendarPopup();
										cal1.showYearNavigation();
									</SCRIPT>
									<% String displayClass=""; %>	
<!-- BEGIN TREATMENT ISSUE TABLE -->									   								
									<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
										<tr class="detailHead">
											<td><bean:message key="prompt.treatmentIssues"/></td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1">
													<tr class="formDeLabel">
														<td width="1%"></td>
														<td><bean:message key="prompt.referralType"/>
															<jims2:sortResults beanName="violationReportsForm" results="create1ElementsList" primaryPropSort="referralTypeDesc" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" levelDeep="4" hideMe="true"/>  
														</td>
														<td><bean:message key="prompt.serviceProviderName"/></td>
														<td><bean:message key="prompt.beginDate"/></td>
														<td><bean:message key="prompt.exit"/>&nbsp;<bean:message key="prompt.date"/></td>														
														<td><bean:message key="prompt.dischargeReason"/></td>
													</tr>
													<logic:iterate id="vrIter" name="violationReportsForm" property="create1ElementsList" indexId="index">
													  	<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
															<td width="1%">
																<input type="checkbox" name="selectedTreatmentIssuesIds" value="<bean:write name='vrIter' property='programReferralId'/>" >
															</td>
															<% displayClass = ""; %>
												 			<logic:equal name="vrIter" property="manualAdded" value="true">
																<% displayClass = "class='pendingSP'"; %>	
															</logic:equal> 
															<td <%= displayClass %>><bean:write name="vrIter" property="referralTypeCode" /></td>														
															<td <%= displayClass %>><bean:write name="vrIter" property="newServiceProviderName" /></td>
															<td <%= displayClass %>><bean:write name="vrIter" property="programBeginDate" formatKey="date.format.mmddyyyy"/></td>
															<td <%= displayClass %>><bean:write name="vrIter" property="programEndDate" formatKey="date.format.mmddyyyy"/></td>																
															<td <%= displayClass %>><bean:write name="vrIter" property="dischargeReason"/></td>
														</tr>
													</logic:iterate>	
													<logic:equal name="violationReportsForm" property="showAddFields" value="true"> 
														<tr>
															<td bgcolor="#6699FF" colspan="6"></td>
														</tr>
														<tr>
															<td class="required"><bean:message key="prompt.4.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
														</tr>
														<tr>
															<td class="formDeLabel" nowrap><bean:message key="prompt.4.diamond"/><bean:message key="prompt.programGroup" /></td>
															<td <%= displayClass %>>
			                                                    <html:select styleId="group1" size="1" name="violationReportsForm" property="programGroupId" onchange="loadPgmTypes(this);">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																	<html:optionsCollection name="cscServiceProviderSearchForm" property="programHeirarchyList" value="parentCd" label="parentDesc" />
																</html:select>
															</td>
															<td>
																<html:text name="violationReportsForm" property="serviceProviderName" size="29" maxlength="100"/>
															</td>																
															<td nowrap>
																<html:text name="violationReportsForm" property="referralBeginDateStr" size="10" maxlength="10" />
																		<a href="#" onClick="cal1.select(violationReportsForm.referralBeginDateStr,'anchor1','MM/dd/yyyy'); return false;"
																			NAME="anchor1" ID="anchor1"><bean:message key="prompt.4.calendar"/></a> 
															</td>
															<td nowrap>
																<html:text name="violationReportsForm" property="referralExitDateStr" size="10" maxlength="10"/>
																		<a href="#" onClick="cal1.select(violationReportsForm.referralExitDateStr,'anchor2','MM/dd/yyyy'); return false;"
																			NAME="anchor2" ID="anchor2"><bean:message key="prompt.4.calendar"/></a> 
															</td>
															<td>
																<html:select name="violationReportsForm" property="selectedDischargeReasonId">
																	<html:option value=""><bean:message key="select.generic"/></html:option>
																	<html:optionsCollection name="violationReportsForm" property="dischargeReasons" value="code" label="description" /> 
																</html:select>
															</td>
														</tr>
														<tr>
															<td class="formDeLabel"><bean:message key="prompt.4.diamond"/><bean:message key="prompt.programType" /></td>
															<td <%= displayClass %> colspan="5" >
			                                                    <html:select styleId="group2" size="1" name="violationReportsForm" property="programTypeId" disabled="true">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																	<html:optionsCollection name="cscServiceProviderSearchForm" property="programTypes" value="code" label="description" />
																</html:select>
															</td>
														</tr>																	
													</logic:equal>	
												</table>
<!-- END TREATMENT ISSUES DETAILS TABLE -->													
											</td>
										</tr>
									</table>
<!-- END TREATMENT ISSUES TABLE -->									
									<table width="98%">
										<tr>
											<td><bean:message key="prompt.rowsManuallyAdded" /></td>
										</tr>
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return validateInputs(this, this.form) && disableSubmit(this, this.form)"><bean:message key="button.addTreatmentIssue" /></html:submit>
											</td>
										</tr>
										<tr>
											<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
										</tr>
									</table>
<!-- BEGIN TREATMENT ISSUES COMMENTS TABLE -->										
									<table width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.comments"/>
												<tiles:insert page="/jsp/common/spellCheckCaseHistTile.jsp" flush="false">
	                          						<tiles:put name="tTextField" value="create1Comments"/>
	                          						<tiles:put name="tSpellCount" value="spellBtn1" />
	                        					</tiles:insert>
												 (Max. characters allowed: 3500)
											</td>
										</tr>
										<tr>
											<td class="formDe">
												<html:textarea name="violationReportsForm" property="create1Comments" 
													onmouseout="textLimit(this, 3500);" 
       												onkeyup="textLimit(this, 3500);" 
													style="width:100%" rows="30">
												</html:textarea>
											</td>
										</tr>
										<input type="hidden" name="prevComments" value="<bean:write name='violationReportsForm' property='previousTreatmentIssuesComments' />" >
									</table>
<!-- END TREATMENT ISSUES COMMENTS TABLE -->	
									<br>
<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return validateInputs(this, this.form) && disableSubmit(this, this.form)"> <bean:message key="button.next" /></html:submit>
										        <html:submit property="submitAction" onclick="return validateCheckBoxSelect(this.form) && disableSubmit(this, this.form)"> <bean:message key="button.removeSelected" /></html:submit>
										        <html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.refreshTreatmentIssues" /></html:submit>
										        <input type="button" name="copyComments" value="<bean:message key="button.copyComments" />" onclick="loadPrevComments()" />
											</td>
										</tr>
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.back" /></html:submit>
										        <html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.cancel" /></html:submit>
											</td>
										</tr>
									</table>
<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table>
<!-- END GREEN BORDER TABLE -->									
						<br>
					</td>
				</tr>
			</table>
<!-- END BLUE BORDER TABLE -->			
		</td>
	</tr>
</table>
<!-- END PAGE TABLE -->	
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>