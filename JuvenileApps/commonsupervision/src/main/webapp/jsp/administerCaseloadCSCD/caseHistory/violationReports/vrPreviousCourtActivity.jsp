<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 02/21/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 03/31/2010  C Shimek - #64667 revised PCA block for Type of Court Action comments and removed comments display  -->
<!-- 07/14/2010  C Shimek - #66380 revised Type Of Court Action and Summary Of Court Actions max. check from onsubmit to active -->
<!-- 09/24/2013  R Capestani - #76068 CSCD:  VR and CS Casenote needs correcting hold formatting from previous court actions -->
<!-- 10/08/2013  R Capestani  - #71520 Format case summary in others section -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ page import="naming.UIConstants" %>
<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
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
<title>Common Supervision - administerCaseloadCSCD/caseHistory/violationReports/vrPreviousCourtActivity.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/caseHistory/caseHistory_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/caseHistory/violationReports/vrPreviousCourtActivity.js"></script>
</head>
<body topmargin="0" leftmargin="0" onload="setAddCursorPos();" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displayVRPreviousCourtActivitySummary" target="content">
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
												<bean:message key="prompt.previous"/>&nbsp;<bean:message key="prompt.court"/>&nbsp;<bean:message key="prompt.activity"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Violation_Reports/CSCD_Violation_Reports.htm#|18">
											</td>
										</tr>
									</table>
<!-- END HEADING TABLE -->
<%-- BEGIN ERROR TABLE --%>
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
													<li>Click Remove Selected to remove selected court activities. Click Next when complete.</li>
												</ul>
											</td>
										</tr>
									</table>
<!-- END INSTRUCTION TABLE -->
									<br>
       								<SCRIPT LANGUAGE="JavaScript" ID="js1">
										var cal1 = new CalendarPopup();
										cal1.showYearNavigation();
									</SCRIPT>
									<% String displayClass=""; %>
									
<!-- BEGIN VIOLATION REPORT TABLE -->									
									<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
										<tr class="detailHead">
											<td><bean:message key="prompt.violationReports"/></td>
										</tr>
										<tr>
											<td>
<!-- BEGIN VIOLATION REPORT DETAILS TABLE -->											
												<table width="100%" cellpadding="2" cellspacing="1">
													<tr class="formDeLabel">
														<td width="1%"></td>
														<td><bean:message key="prompt.date"/></td>
														<td width="40%"><bean:message key="prompt.typeOfCourtAction"/> (<bean:message key="prompt.comments"/>)<div>(Max. characters allowed: 50)</div></td>
														<td width="40%"><bean:message key="prompt.summaryOfCourtActions"/><div>(Max. characters allowed: 250)</div></td>
													</tr>
													<logic:iterate id="vrIter" name="violationReportsForm" property="create1ElementsList" indexId="index">
													  	<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
															<td width="1%" valign="top">
																<input type="checkbox" name="selectedCourtActivityVRIds" value="<bean:write name='vrIter' property='previousCourtActivityId'/>" >																
															</td>
															<% displayClass = ""; %>
												 			<logic:equal name="vrIter" property="manualAdded" value="true">
																<% displayClass = "class='pendingSP'"; %>	
															</logic:equal> 
															<td <%= displayClass %> valign="top"><bean:write name="vrIter" property="occurenceDate" formatKey="date.format.mmddyyyy"/></td>
															<td <%= displayClass %> valign="top"><bean:write name="vrIter" property="typeOfCourtActionComment" /></td>
															<td <%= displayClass %> valign="top"><bean:write name="vrIter" property="summaryOfCourtAction" filter="false"/></td>
														</tr>
												   	</logic:iterate> 
													<logic:equal name="violationReportsForm" property="showVRAddFields" value="true"> 
														<tr>
												 			<td width="1%">&nbsp;</td>
															<td valign="top">
																<html:text name="violationReportsForm" size="10" maxlength="10" property="courtActivityVRDateStr" />
																	<a href="#" onClick="cal1.select(violationReportsForm.courtActivityVRDateStr,'anchor1','MM/dd/yyyy'); return false;"
																		NAME="anchor1" ID="anchor1"><bean:message key="prompt.4.calendar"/></a> 
															</td>
															<td valign="top">
																<html:textarea name="violationReportsForm" property="create1Comments" 
																	onmouseout="textLimit(this, 50);" 
       																onkeyup="textLimit(this, 50);" 
																	style="width:100%" rows="3">
																</html:textarea>
															</td>
															<td> 
																<html:textarea name="violationReportsForm" property="courtActivityVRSummaryOfCourtActions" 
																	onmouseout="textLimit(this, 250);" 
       																onkeyup="textLimit(this, 250);" 
																	style="width:100%" rows="5">
																</html:textarea>
															</td>
														</tr>	
													</logic:equal>	
												</table>
<!-- END VIOLATION REPORT DETAILS TABLE -->											
											</td>
										</tr>
									</table>
<!-- END VIOLATION REPORTS DETAIL TABLE -->	
<!-- BEGIN ADD VIOLATION REPORT BUTTON TABLE -->	
									<table width="98%">
										<tr>
											<td><bean:message key="prompt.rowsManuallyAdded" /></td>
										</tr>
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return validateInputs(this, this.form) && disableSubmit(this, this.form)"> <bean:message key="button.addViolationReport" /></html:submit>
											</td>
										</tr>
										<tr>
											<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
										</tr>
									</table>	
<!-- END ADD VIOLATION REPORT BUTTON TABLE -->										
<!-- BEGIN MOTIONS TABLE -->									
									<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
										<tr class="detailHead">
											<td><bean:message key="prompt.motions"/></td>
										</tr>
										<tr>
											<td>
<!-- BEGIN MOTIONS DETAILS TABLE -->											
												<table width="100%" cellpadding="2" cellspacing="1">
													<tr class="formDeLabel">
														<td width="1%"></td>
														<td><bean:message key="prompt.date"/>
															<jims2:sortResults beanName="violationReportsForm" results="create2ElementsList" primaryPropSort="occurenceDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="ASC" sortId="m1" levelDeep="4" hideMe="true" />
														</td>
														<td width="30%"><bean:message key="prompt.typeOfCourtAction"/> (<bean:message key="prompt.comments"/>)<div>(Max. characters allowed: 50)</div></td>
														<td width="30%"><bean:message key="prompt.summaryOfCourtActions"/><div>(Max. characters allowed: 250)</div></td>
														<td><bean:message key="prompt.disposition"/></td>
													</tr>
													<logic:iterate id="motionsIter" name="violationReportsForm" property="create2ElementsList" indexId="index">
														<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
															<td width="1%" valign="top">
																<input type="checkbox" name="selectedMotionsIds" value="<bean:write name='motionsIter' property='previousCourtActivityId'/>" >																
															</td>
															<% displayClass = ""; %>
															<logic:equal name="motionsIter" property="manualAdded" value="true">
																<% displayClass = "class='pendingSP'"; %>	
															</logic:equal> 
																<td <%= displayClass %> valign="top"><bean:write name="motionsIter" property="occurenceDate" formatKey="date.format.mmddyyyy"/></td>
																<td <%= displayClass %> valign="top"><bean:write name="motionsIter" property="typeOfCourtActionComment" /></td>
																<td <%= displayClass %> valign="top"><bean:write name="motionsIter" property="summaryOfCourtAction"  filter="false"/></td>
																<td <%= displayClass %> valign="top"><bean:write name="motionsIter" property="disposition"/></td>																
														</tr>
													</logic:iterate>             													
													<logic:equal name="violationReportsForm" property="showMotionAddFields" value="true"> 
														<tr>
															<td width="1%">&nbsp;</td>
															<td valign="top">   
																<html:text name="violationReportsForm" size="10" maxlength="10" property="motionDateStr" />
																<a href="#" onClick="cal1.select(violationReportsForm.motionDateStr,'anchorMO','MM/dd/yyyy'); return false;"
																	NAME="anchorMO" ID="anchorMO"><bean:message key="prompt.4.calendar"/></a> 
															</td>
															<td valign="top"> 	
																<html:textarea name="violationReportsForm" property="create2Comments" 
																	onmouseout="textLimit(this, 50);" 
       																onkeyup="textLimit(this, 50);" 
																	style="width:100%" rows="3">
																</html:textarea>
															</td>
															<td> 	
																<html:textarea name="violationReportsForm" property="motionSummaryOfCourtActions" 
																	onmouseout="textLimit(this, 250);" 
       																onkeyup="textLimit(this, 250);" 
																	style="width:100%" rows="5">
																</html:textarea>
															</td>
															<td valign="top">
																<html:select name="violationReportsForm" property="selectedMotionDispositionId">
																	<html:option value=""><bean:message key="select.generic"/></html:option>
																	<html:optionsCollection name="violationReportsForm" property="motionsDispositions" value="code" label="description" />  
																</html:select>
															</td>
														</tr>	
													</logic:equal>	
												</table>
<!-- END MOTIONS DETAILS TABLE -->													
											</td>
										</tr>
									</table>
<!-- END MOTIONS TABLE -->	
<!-- BEGIN ADD MOTION BUTTON TABLE -->	
									<table width="98%" align="center">
										<tr>
											<td><bean:message key="prompt.rowsManuallyAdded" /></td>
										</tr>	
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return validateInputs(this, this.form) && disableSubmit(this, this.form)"> <bean:message key="button.addMotion" /></html:submit>
											</td>
										</tr>	
										<tr>
											<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
										</tr>
									</table>
<!-- END ADD MOTION BUTTON TABLE -->									
<!-- BEGIN OTHERS TABLE -->
									<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
										<tr class="detailHead">
											<td><bean:message key="prompt.others"/></td>
										</tr>
										<tr>
											<td>
<!-- BEGIN OTHERS DETAILS TABLE -->											
												<table width="100%" cellpadding="2" cellspacing="1">
													<tr class="formDeLabel">
														<td width="1%"></td>
														<td><bean:message key="prompt.date"/>
															<jims2:sortResults beanName="violationReportsForm" results="create3ElementsList" primaryPropSort="occurenceDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="ASC" sortId="o1" levelDeep="4" hideMe="true"/>														
														</td>
														<td width="40%"><bean:message key="prompt.typeOfCourtAction"/> (<bean:message key="prompt.comments"/>)<div>(Max. characters allowed: 50)</div></td>
														<td width="40%"><bean:message key="prompt.summaryOfCourtActions"/><div>(Max. characters allowed: 250)</div></td>
													</tr>
													<logic:iterate id="otherIter" name="violationReportsForm" property="create3ElementsList" indexId="index">
														<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
															<td width="1%" valign="top">
																<input type="checkbox" name="selectedOthersIds" value="<bean:write name='otherIter' property='previousCourtActivityId'/>" >																
															</td>
															<% displayClass = ""; %>
															<logic:equal name="otherIter" property="manualAdded" value="true">
																<% displayClass = "class='pendingSP'"; %>	
															</logic:equal> 
															<td <%= displayClass %> valign="top"><bean:write name="otherIter" property="occurenceDate" formatKey="date.format.mmddyyyy"/></td>
															<td <%= displayClass %> valign="top"><bean:write name="otherIter" property="typeOfCourtActionComment" /></td>
															<td <%= displayClass %> valign="top"><bean:write name="otherIter" property="summaryOfCourtAction" filter="false"/></td>
														</tr>
												    </logic:iterate>   
													<logic:equal name="violationReportsForm" property="showAddFields" value="true"> 
														<tr>
															<td width="1%">&nbsp;</td>
															<td valign="top">  
																<html:text name="violationReportsForm" size="10" maxlength="10" property="otherDateStr" />
																	<a href="#" onClick="cal1.select(violationReportsForm.otherDateStr,'anchorOT','MM/dd/yyyy'); return false;"
																		NAME="anchorOT" ID="anchorOT"><bean:message key="prompt.4.calendar"/></a> 
															</td>
															<td valign="top"> 
																<html:textarea name="violationReportsForm" property="create3Comments" 
																	onmouseout="textLimit(this, 50);" 
       																onkeyup="textLimit(this, 50);" 
																	style="width:100%" rows="3">
																</html:textarea>
															</td>
															<td> 
															<html:textarea name="violationReportsForm" property="otherSummaryOfCourtActions" 
																	onmouseout="textLimit(this, 250);" 
       																onkeyup="textLimit(this, 250);" 
																	style="width:100%" rows="5">
																</html:textarea>
															</td>
														</tr>	
													</logic:equal>	
												</table>
<!-- END OTHERS DETAILS TABLE -->													
											</td>
										</tr>
									</table>
<!-- END OTHERS TABLE -->		
<!-- BEGIN ADD OTHER BUTTON TABLE -->	
									<table width="98%" align="center">
										<tr>
											<td><bean:message key="prompt.rowsManuallyAdded" /></td>
										</tr>		
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return validateInputs(this, this.form) && disableSubmit(this, this.form)"> <bean:message key="button.addOther" /></html:submit>
											</td>
										</tr>		
									</table>
<!-- END ADD OTHER BUTTON TABLE -->									
									<br>
<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return validateInputs(this, this.form) && disableSubmit(this, this.form)"> <bean:message key="button.next" /></html:submit>
												<html:submit property="submitAction" onclick="return validateCheckBoxSelect(this.form) && disableSubmit(this, this.form)"> <bean:message key="button.removeSelected" /></html:submit>
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.refreshCourtActivity" /></html:submit>
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