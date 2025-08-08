<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 02/19/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 04/15/2009 C. Shimek         - #58735 revised default sort start date, descending -->
<!-- 06/01/2009 C. Shimek         - #59904 remove start date  -->
<!-- 07/02/2010 C. Shimek         - #66278 revised comment max. check from onsubmit to active -->
<!-- 10/07/2010 C Shimek          - #67758 revised comment max. from 500 to 3500 and added spell check -->
<!-- 05/13/2011 R Young   		  - #70295 Sort employment by lowest cls -->
<!-- 06/10/2011 R Young   		  - #70346 Violation Report-copy Comments in each section(UI) -->
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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/caseHistory/violationReports/vrEmploymentHistory.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/caseHistory/caseHistory_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/caseHistory/violationReports/vrEmploymentHistory.js"></script>
</head>
<body topmargin="0" leftmargin="0" onLoad="setAddCursorPos();" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displayVREmploymentHistorySummary" target="content">
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
<!-- BEGIN SUPERVISEE INFO TABLE -->
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
<!-- END SUPERVISEE INFO TABLE -->	
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
												 <bean:message key="prompt.employmentHistory"/>
												 <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Violation_Reports/CSCD_Violation_Reports.htm#|16">
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
													<li>Click Remove Selected to remove selected employment records. Click next when complete.</li>
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
<!-- BEGIN EMPLOYMENT HISTORY TABLE -->									
									<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
										<tr class="detailHead">
											<td><bean:message key="prompt.employmentHistory"/></td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1">
												<% int idxCtr=0; 	
												   String displayClass; %>
													<tr class="formDeLabel">
														<td width="1%"></td>
														<td><bean:message key="prompt.employerName"/>
															<jims2:sortResults beanName="violationReportsForm" results="create1ElementsList" primaryPropSort="seqNum" primarySortType="STRING" defaultSortOrder="ASC" defaultSort="true" sortId="3" levelDeep="4" hideMe="true"/>
														</td>
														<td><bean:message key="prompt.jobTitle"/></td>
														<td><bean:message key="prompt.status"/></td>
													</tr>
													<logic:iterate id="create1ElementsList" name="violationReportsForm" property="create1ElementsList" indexId="index">
														<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
															<td width="1%">
																<input type="checkbox" name="selectedEmploymentHistoryIds" value="<bean:write name='create1ElementsList' property='employmentId'/>" >																
															</td>
															<% displayClass = ""; %>
												 			<logic:equal name="create1ElementsList" property="manualAdded" value="true">
																<% displayClass = "class='pendingSP'"; %>	
															</logic:equal>  
															<td <%= displayClass %>><bean:write name="create1ElementsList" property="employerName"/></td>
															<td <%= displayClass %>><bean:write name="create1ElementsList" property="jobTitle"/></td>
															<td <%= displayClass %>><bean:write name="create1ElementsList" property="statusDesc"/></td>
														</tr> 
														<% idxCtr++; %>
												   	</logic:iterate>  
													<logic:equal name="violationReportsForm" property="showAddFields" value="true">
														<tr class="normalRow" >
															<td width="1%">&nbsp;</td>
															<td><html:text name="violationReportsForm" property="employerName" size="30" maxlength="100" /></td>
															<td><html:text name="violationReportsForm" property="jobTitle"size="30" maxlength="50" /></td>
															<td>
																<html:select name="violationReportsForm" property="jobStatusId">
																	<html:option value=""><bean:message key="select.generic"/></html:option>
																	<html:optionsCollection name="violationReportsForm" property="employmentStatusList" value="codeId" label="description" /> 
																</html:select>
															</td>	
														</tr>	
													</logic:equal>
												</table>
											</td>
										</tr>
									</table>
<!-- END EMPLOYMENT HISTORY TABLE -->	
<!-- BEGIN ADD EMPLOYMENT BUTTON TABLE -->								
									<table width="98%">
										<tr>
											<td><bean:message key="prompt.rowsManuallyAdded" /></td>
										</tr>
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return validateInputs(this, this.form) && disableSubmit(this, this.form)"><bean:message key="button.addEmployment" /></html:submit>
											</td>
										</tr>
										<tr>
											<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
										</tr>
									</table>	
<!-- END ADD EMPLOYMENT BUTTON TABLE -->
<!-- BEGIN EMPLOYMENT HISTORY COMMENT TABLE -->										
									<table width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.comments"/>
											<tiles:insert page="/jsp/common/spellCheckCaseHistTile.jsp" flush="false">
                          						<tiles:put name="tTextField" value="create1Comments"/>
                          						<tiles:put name="tSpellCount" value="spellBtn1" />
                        					</tiles:insert>
											 (Max. characters allowed: 3500)</td>
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
										<input type="hidden" name="prevComments" value="<bean:write name='violationReportsForm' property='previousEmploymentHistoryComments' />" >
									</table>
<!-- END EMPLOYMENT HISTORY COMMENT TABLE -->										
									<br>
<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return validateInputs(this, this.form) && disableSubmit(this, this.form)"> <bean:message key="button.next" /></html:submit>
												<html:submit property="submitAction" onclick="return validateCheckBoxSelect(this.form) && disableSubmit(this, this.form)"> <bean:message key="button.removeSelected" /></html:submit>
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.refreshEmployment" /></html:submit>
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
<!-- END BLUE BORDERE TABLE -->				
		</td>
	</tr>
</table>
<!-- END PAGE TABLE -->	
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
