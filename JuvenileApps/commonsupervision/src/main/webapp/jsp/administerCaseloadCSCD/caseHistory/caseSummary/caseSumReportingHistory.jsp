<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 02/25/2008 C. Shimek - Converted to JSP -->
<!-- 04/15/2009 C. Shimek - #58735 revised defautl sort to date/time -->
<!-- 06/01/2009 C. Shimek - #59896 remove occurence time  -->
<!-- 07/02/2010 C. Shimek - #66278 revised comment max. check from onsubmit to active -->
<!-- 10/07/2010 C Shimek  - #67758 revised comment max. from 500 to 3500 and added spell check -->
<!-- 03/10/2011 R Young  - #69284 VR-Add 'Failure to Report' to Event Type field in Reporting History section -->
<!-- 06/16/2011 R Young   - #70346 Violation Report-copy Comments in each section(UI) -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ page import="naming.UIConstants"%>
<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
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
	administerCaseloadCSCD/caseHistory/caseSummary/caseSumReportingHistory.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript"
	src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript"
	src="/<msp:webapp/>js/caseHistory/caseHistory_util.js"></script>
<script type="text/javascript"
	src="/<msp:webapp/>js/caseHistory/caseSummary/caseSumReportingHistory.js"></script>

</head>
<body topmargin="0" leftmargin="0" onLoad="setAddCursorPos();"
	onKeyDown="return checkEnterKeyAndSubmit(event,true);">
	<html:form action="/displayReportingHistorySummary" target="content">
		<input type="hidden" name="cursorPos"
			value="<bean:write name='caseSummaryForm' property='cursorPosition'/>">
		<input type="hidden" name="helpFile"
			value="commonsupervision/CSCD_Caseload/Case_Summary/CSCD_Case_Summary.htm#|15">
		<div align="center">
			<!-- BEGIN PAGE TABLE -->
			<table width="98%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><img src="/<msp:webapp/>images/spacer.gif"
						height="5">
					</td>
				</tr>
				<tr>
					<td valign="top">
						<!-- BEGIN BLUE TABS TABLE -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td valign="top"><tiles:insert
										page="../../../common/commonSupervisionTabs.jsp" flush="true">
										<tiles:put name="tab" value="caseloadTab" />
									</tiles:insert></td>
							</tr>
							<tr>
								<td bgcolor="#6699FF"><img
									src="/<msp:webapp/>images/spacer.gif" height="5">
								</td>
							</tr>
						</table> <!-- ENE BLUE TABS TABLE --> <!-- BEGIN BLUE BORDER TABLE -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="borderTableBlue">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5">
								</td>
							</tr>
							<tr>
								<td valign="top" align="center">
									<table width="98%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td bgcolor="#cccccc" colspan="2"><tiles:insert
													page="../../../common/caseloadHeaderCase.jsp" flush="true">
												</tiles:insert></td>
										</tr>
										<tr>
											<td><img src="/<msp:webapp/>images/spacer.gif"
												height="5">
											</td>
										</tr>
									</table></td>
							</tr>
							<tr>
								<td valign="top" align="center">
									<!-- BEGIN GREEN TABS TABLE -->
									<table width="98%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="top"><tiles:insert
													page="../../../common/caseloadCSCDSubTabs.jsp" flush="true">
													<tiles:put name="tab" value="CasesTab" />
												</tiles:insert></td>
										</tr>
										<tr>
											<td bgcolor="#33cc66"><img
												src="/<msp:webapp/>images/spacer.gif" height="5">
											</td>
										</tr>
									</table> <!-- END GREEN TABS TABLE --> <!-- BEGIN BLUE BORDER TABLE -->
									<table width="98%" border="0" cellpadding="0" cellspacing="0"
										class="borderTableGreen">
										<tr>
											<td><img src="/<msp:webapp/>images/spacer.gif"
												height="5">
											</td>
										</tr>
										<tr>
											<td valign="top" align="center">
												<!-- BEGIN HEADING TABLE -->
												<table width="100%">
													<tr>
														<td align="center" class="header"><bean:message
																key="title.CSCD" />&nbsp;-&nbsp;<bean:message
																key="prompt.caseSummary" />&nbsp;- <bean:message
																key="prompt.reportingHistory" /></td>
													</tr>
												</table> <!-- END HEADING TABLE --> <%-- BEGIN ERROR TABLE --%>
												<table width="98%" align="center">
													<tr>
														<td align="center" class="errorAlert"><html:errors></html:errors>
														</td>
													</tr>
												</table> <!-- END ERROR TABLE --> <!-- BEGIN INSTRUCTION TABLE -->
												<table width="98%" border="0">
													<tr>
														<td>
															<ul>
																<li>Click Remove Selected to remove selected
																	Reporting records. Click next when complete.</li>
															</ul></td>
													</tr>
												</table> <!-- END INSTRUCTION TABLE --> <br> <SCRIPT
													LANGUAGE="JavaScript" ID="js1">
													var cal1 = new CalendarPopup();
													cal1.showYearNavigation();
												</SCRIPT> <!-- BEGIN REPORTING HISTORY TABLE -->
												<table width="98%" border="0" cellspacing="0"
													cellpadding="2" class="borderTableBlue">
													<tr class="detailHead">
														<td><bean:message key="prompt.reportingHistory" />
														</td>
													</tr>
													<tr>
														<td>
															<table width="100%" cellpadding="2" cellspacing="1">
																<%
																	String displayClass;
																%>
																<tr class="formDeLabel">
																	<td width="1%"></td>
																	<td><bean:message key="prompt.occurrenceDate" /> <jims2:sortResults
																			beanName="caseSummaryForm"
																			results="create1ElementsList"
																			primaryPropSort="dateTime" primarySortType="DATE"
																			defaultSort="true" defaultSortOrder="DESC" sortId="1"
																			levelDeep="4" hideMe="true" /></td>
																	<td><bean:message key="prompt.eventType" />(s)</td>
																	<td><bean:message key="prompt.details" /> (Max.
																		characters allowed: 50)</td>
																</tr>
																<logic:iterate id="vrIter" name="caseSummaryForm"
																	property="create1ElementsList" indexId="index">
																	<%
																		displayClass = "";
																	%>
																	<logic:equal name="vrIter" property="manualAdded"
																		value="true">
																		<%
																			displayClass = "class='pendingSP'";
																		%>
																	</logic:equal>
																	<tr
																		class="<%out.print((index.intValue() % 2 == 1)
								? "alternateRow"
								: "normalRow");%>">
																		<td width="1%"><input type="checkbox"
																			name="selectedReportingHistoryIds"
																			value="<bean:write name='vrIter' property='nonComplianceEventId'/>">
																		</td>
																		<td <%=displayClass%>><logic:present
																				name="vrIter" property="dateTime">
																				<bean:write name="vrIter" property="dateTime"
																					formatKey="date.format.mmddyyyy" />
																			</logic:present></td>
																		<td <%=displayClass%>><bean:write name="vrIter"
																				property="eventTypes" />
																		</td>
																		<td <%=displayClass%>><bean:write name="vrIter"
																				property="details" />
																		</td>
																	</tr>
																</logic:iterate>
																<logic:equal name="caseSummaryForm"
																	property="showAddFields" value="true">
																	<tr>
																		<td width="1%">&nbsp;</td>
																		<td valign="top"><html:text
																				name="caseSummaryForm" property="occurrenceDateStr"
																				size="10" /> <a href="#"
																			onClick="cal1.select(caseSummaryForm.occurrenceDateStr,'anchor1','MM/dd/yyyy'); return false;"
																			NAME="anchor1" ID="anchor1"><bean:message
																					key="prompt.4.calendar" />
																		</a></td>
																		<td><html:select name="caseSummaryForm"
																				property="selectedEventTypeIds" multiple="true">
																				<html:optionsCollection name="caseSummaryForm"
																					property="eventTypes" value="supervisionCode"
																					label="description" />
																			</html:select></td>
																		<td valign="top">
																			<%-- 																<html:text name="caseSummaryForm" property="reportingHistoryDetails" size="50" maxlength="50" /> --%>
																			<html:textarea name="caseSummaryForm"
																				property="reportingHistoryDetails"
																				onmouseout="textLimit(this, 50);"
																				onkeyup="textLimit(this, 50);" style="width:100%"
																				rows="2">
																			</html:textarea></td>
																	</tr>
																</logic:equal>
															</table></td>
													</tr>
												</table> <!-- END REPORTING HISTORY TABLE -->
												<table width="98%">
													<tr>
														<td><bean:message key="prompt.rowsManuallyAdded" />
														</td>
													</tr>
													<tr>
														<td align="center"><html:submit
																property="submitAction"
																onclick="return validateInputs(this, this.form) && disableSubmit(this, this.form)">
																<bean:message key="button.addReportingHistory" />
															</html:submit></td>
													</tr>
												</table> <!-- BEGIN REPORTING HISTORY COMMENTS TABLE -->
												<table width="98%" cellpadding="2" cellspacing="1"
													class="borderTableBlue">
													<tr>
														<td class="formDeLabel"><bean:message
																key="prompt.comments" /> <tiles:insert
																page="/jsp/common/spellCheckCaseHistTile.jsp"
																flush="false">
																<tiles:put name="tTextField" value="create1Comments" />
																<tiles:put name="tSpellCount" value="spellBtn1" />
															</tiles:insert> (Max. characters allowed: 3500)</td>
													</tr>
													<tr>
														<td class="formDe"><html:textarea
																name="caseSummaryForm" property="create1Comments"
																onmouseout="textLimit(this, 3500);"
																onkeyup="textLimit(this, 3500);" style="width:100%"
																rows="30">
															</html:textarea></td>
													</tr>
													<input type="hidden" name="prevComments"
														value="<bean:write name='caseSummaryForm' property='previousReportingHistoryComments'/>">
												</table> <!-- END REPORTING HISTORY COMMENTS TABLE --> <br> <!-- BEGIN ADDRESS TABLE -->
												<!-- true when last face to face contanct greater than 90 days -->
												<logic:equal name="caseSummaryForm" property="showAddress"
													value="true">
													<table width="98%" border="0" cellspacing="0"
														cellpadding="2" class="borderTableBlue">
														<tr class="detailHead">
															<td><bean:message key="prompt.lastKnownAddress" />
															</td>
														</tr>
														<tr>
															<td>
																<table width="100%" cellpadding="4" cellspacing="1">
																	<tr>
																		<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message
																				key="prompt.lastContactDate" />
																		</td>
																		<td class="formDe"><bean:write
																				name="caseSummaryForm" property="lastContactDate"
																				formatKey="date.format.mmddyyyy" />
																		</td>
																	</tr>
																	<tr>
																		<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message
																				key="prompt.addressType" />
																		</td>
																		<td class="formDe"><bean:write
																				name="caseSummaryForm" property="addressType" />
																		</td>
																	</tr>
																	<tr>
																		<td class="formDeLabel"><bean:message
																				key="prompt.address" />
																		</td>
																		<td class="formDe"><bean:write
																				name="caseSummaryForm" property="addressNumber" />&nbsp;<bean:write
																				name="caseSummaryForm" property="addressName" /> <bean:write
																				name="caseSummaryForm" property="addressCity" />,&nbsp;<bean:write
																				name="caseSummaryForm" property="addressState" />&nbsp;&nbsp;<bean:write
																				name="caseSummaryForm" property="addressZipCode" />
																		</td>
																	</tr>
																</table></td>
														</tr>
													</table>
												</logic:equal> <!-- END ADDRESS TABLE --> <br> <!-- BEGIN BUTTON TABLE -->
												<table border="0" width="100%">
													<tr>
														<td align="center"><html:submit
																property="submitAction"
																onclick="return validateInputs(this, this.form) && disableSubmit(this, this.form)">
																<bean:message key="button.next" />
															</html:submit> <html:submit property="submitAction"
																onclick="return validateCheckBoxSelect(this.form) && disableSubmit(this, this.form)">
																<bean:message key="button.removeSelected" />
															</html:submit> <html:submit property="submitAction"
																onclick="return disableSubmit(this, this.form)">
																<bean:message key="button.refreshReporting" />
															</html:submit> <input type="button" name="copyComments"
															value="<bean:message key="button.copyComments" />"
															onclick="loadPrevComments()" /></td>
													</tr>
													<tr>
														<td align="center"><html:submit
																property="submitAction"
																onclick="return disableSubmit(this, this.form)">
																<bean:message key="button.back" />
															</html:submit> <html:submit property="submitAction"
																onclick="return disableSubmit(this, this.form)">
																<bean:message key="button.cancel" />
															</html:submit></td>
													</tr>
												</table> <!-- END BUTTON TABLE --></td>
										</tr>
									</table> <!-- END GREEN BORDER TABLE --> <br></td>
							</tr>
						</table> <!-- END BLUE BORDER TABLE --></td>
				</tr>
			</table>
			<!-- END PAGE BORDER TABLE -->
		</div>
	</html:form>
	<div align="center">
		<script type="text/javascript">
			renderBackToTop()
		</script>
	</div>
</body>
</html:html>