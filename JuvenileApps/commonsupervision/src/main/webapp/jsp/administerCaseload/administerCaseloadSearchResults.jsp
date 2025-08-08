<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%-- 02/28/2008	LDeen	 Activity #49819 integrate help    --%>
<%-- 03/11/2009  CShimek Added disableSubmit to buttons --%>
<%-- 06/18/2009  CShimek #59631 Add frompage attribute to supervisee href for page flow --%>
<%-- 09/20/2010  CShimek #67212 Add Current Caseload header --%>
<%-- 11/14/2011	TSvines	#71846	Added "function validateForOOCCase()" to match administerCaseloadSearch.jsp to facilitate OOC TransferOut Request  --%>
<%-- 11/14/2011	RCapestani	#72261 set logic tag to display transfer in and out buttons --%>
<%-- 12/07/2012  CShimek #74630 - corrected more than 90 days display on supervisee dispaly line --%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@page import="naming.Features"%>
<%@page import="naming.UIConstants"%>
<%@page import="naming.CSEventsReportConstants"%>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<head>
	<!-- This is the SPN Search for Office Mgr -->
	<msp:nocache />
	<%-- Checks to make sure if the user is logged in. --%>
	<%--msp:login / --%>
	<meta http-equiv="Content-Language" content="en-us">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META name="GENERATOR" content="IBM WebSphere Studio">
	<META http-equiv="Content-Style-Type" content="text/css">
	
	<!-- STYLE SHEET LINK -->
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
	<html:base />
	<title>CommonSupervision/administerCaseload/administerCaseloadSearchResults.jsp</title>
	<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
	<script type="text/javascript">
		function validateCaseSelection() {
			var selected = false;
			var elements = document.getElementsByName("casesSelectedForReassignment");
			if (elements.length > 0) {
				for (i = 0; i < elements.length; i++) {
					var element = document.getElementsByName("casesSelectedForReassignment")[i];
					if (element.checked) {
						selected = true;
						break;
					}
				}
			}
			if (!selected) {
				alert ("Please select a case.");
			}
			return selected;
		}
		
		function validateForOOCCase()
		{
			var elements = document.getElementsByName("casesSelectedForReassignment");
			for(i = 0; i < elements.length; i++)
			{
				if (elements[i].checked == true)
				{	
					var caseId = document.getElementById(elements[i].value).value;
					caseId = caseId.substring(0, 3);
					
					if(caseId == "010")
					{
						alert("Select Harris County case for transfer. Courtesy case transfer is not allowed.");
						return false;
					}
				}	
			}
			return true;
		}
		selectedDefendant = "";

		function selectSuperviseeCase(theSelectedCase, defendantId) {
			if (selectedDefendant == "" || defendantId == selectedDefendant) {
				selectedDefendant = defendantId;
				var casesSelected = document.getElementsByName("officerCaseload.selectedCasesForReassignment");
				var length = casesSelected.length;
				var noOfCasesSelected = 0;
				for (var i = 0; i < length; i++) {
					if (casesSelected[i].checked) {
						noOfCasesSelected++;
					}
				}
				var caseloadSubmitButton = document.getElementById("caseloadSubmitBtnId");
				
				if (noOfCasesSelected > 0) {
					caseloadSubmitButton.disabled = false;	
				} else {
					caseloadSubmitButton.disabled = true;	
				}
				
				var closeCaseSubmitButton = document.getElementById("closeCaseSubmitBtnId");
				if (closeCaseSubmitButton != null) {
					if (noOfCasesSelected > 0) {
						closeCaseSubmitButton.disabled = false;	 
					} else {
						closeCaseSubmitButton.disabled = true;	 	
					}
				}
				
			} else {
				selectedDefendant = defendantId;
				var casesSelected = document.getElementsByName("officerCaseload.selectedCasesForReassignment");
				var length = casesSelected.length;
				for (var i = 0; i < length; i++) {
					if (casesSelected[i].checked) {
						casesSelected[i].checked = false;
					}
				}
				theSelectedCase.checked = true;
			}
		}
		function caseloadSearchSubmit(theForm) {
			var defendant = theForm["defendantId"];
			defendant.value = selectedDefendant;
			return true;
		}
		function caseloadTransferSubmit(theForm) {
			if (typeof(theForm.defendantId) != "undefined"){
	// verify at least 1 case selected		
				var casesSelected = document.getElementsByName("casesSelectedForReassignment");
				var len = casesSelected.length;
				for (var i = 0; i < len; i++) {
					if (casesSelected[i].checked) {
						return true;
					}
				}
			}
			alert("No case selected to transfer.");
			return false;
		}	

				
		function showCalendarReport(theForm)
		{
			var selectedInd = theForm.selectedCalendarCategory.selectedIndex;
			var selCalendarCategory = theForm.selectedCalendarCategory.options[selectedInd].value;
			var startDate = theForm.calendarStartDateStr.value;
			var endDate = theForm.calendarEndDateStr.value;
			
			clearAllValArrays();
			customValRequired("selectedCalendarCategory","<bean:message key='errors.required' arg0='Calendar Category'/>","");
			customValRequired("calendarStartDateStr","<bean:message key='errors.required' arg0='Start Date'/>","");
			customValRequired("calendarEndDateStr","<bean:message key='errors.required' arg0='End Date'/>","");
			addMMDDYYYYDateValidation("calendarStartDateStr","<bean:message key='errors.date' arg0='Start Date'/>","");
			addMMDDYYYYDateValidation("calendarEndDateStr","<bean:message key='errors.date' arg0='End Date'/>","");
			
			if(validateCustomStrutsBasedJS(theForm))
			{
				var url = "/<msp:webapp/>handleCSEventsSearch.do?submitAction=Submit&calendarCategory=" + selCalendarCategory + "&startDateStr=" + startDate + "&endDateStr=" + endDate; 
				javascript:goNav(url);
			}
			else
			{
				return false;
			}
		}

		function showInOutReport(theForm)
		{
			var startDate = theForm.inOutStartDateStr.value;
			var endDate = theForm.inOutEndDateStr.value;
			
			//clearAllValArrays();
			customValRequired("inOutStartDateStr","<bean:message key='errors.required' arg0='Start Date'/>","");
			customValRequired("inOutEndDateStr","<bean:message key='errors.required' arg0='End Date'/>","");
			addMMDDYYYYDateValidation("inOutStartDateStr","<bean:message key='errors.date' arg0='Start Date'/>","");
			addMMDDYYYYDateValidation("inOutEndDateStr","<bean:message key='errors.date' arg0='End Date'/>","");
			
			if(validateCustomStrutsBasedJS(theForm))
			{
				var url = "/<msp:webapp/>handleCSEventsSearch.do?submitAction=View In %26 Out Activity&startDateStr=" + startDate + "&endDateStr=" + endDate; 
				javascript:goNav(url);
			}
			else
			{
				return false;
			}
		}
	</script>
	<SCRIPT LANGUAGE="JavaScript" ID="js1">
		var cal = new CalendarPopup();
		cal.showYearNavigation();
	</SCRIPT>
</head>

<body topmargin="0" leftmargin="0">
	<html:form action="/handleSuperviseeSearchAction" target="content">
		<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assign_Supervisee/Assign_Supervisee.htm#|2">
		<div align="center">
<!-- BEGIN FULL PAGE TABLE -->		
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
									<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
										<tiles:put name="tab" value="caseloadTab"/>
									</tiles:insert>	
								</td>
							</tr>
							<tr>
								<td bgcolor="#6699FF" height="5px"></td> 
							</tr>  
						</table>
<!-- END BLUE TABS TABLE -->
<!-- BEGIN BLUE BORDER TABLE -->						
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header"><bean:message key="title.CSCD" />&nbsp;-&nbsp;<bean:message key="prompt.caseload" />&nbsp;<bean:message key="prompt.searchResults" /></td>
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
									<logic:notEmpty name="caseAssignmentForm" property="activeCases">									
<!-- BEGIN CURRENT CASELOAD TABLE -->
										<table width="98%" border="0" cellpadding="0" cellspacing="0">
											<tr class="paddedFourPix">
												<td class="formDeLabel">Current Caseload</td>
												<td class="formDeLabel" align="right">
													<span id="reportLinks">
														<logic:equal name="caseAssignmentForm" property="viewCalReportFeatureAssigned" value="true">
															<a href="javascript:show('ovfvoth',1)" class="blackSubNav" title="OV / FV / Other Events">OV/FV/OTH</a>
															&nbsp;|&nbsp;
															<a href="javascript:show('dateRangeInOut',1)" class="blackSubNav" title="In & Out Activity">In & Out Activity</a><br>																
														</logic:equal>	
													</span>
<!-- BEGIN IN & OUT ACTIVITY DIV -->													 
													<div id="dateRangeInOut" class="hidden">
														<table cellspacing="0" cellpadding="4" width="100%">
															<tr class="formDeLabel">
																<td class="formDeLabel">In & Out Activity Date Range</td>
																<td align="right"><a href="javascript:show('dateRangeInOut', 0)"><img src="../../images/close.gif" border="0"></a></td>
															</tr>
															<tr class="formDe">
																<td colspan="2">
																	<html:text name="caseAssignmentForm" property="inOutStartDateStr" size="10" maxlength="10" title="mm/dd/yyyy" />
																	<A HREF="#" onClick="cal.select(caseAssignmentForm.inOutStartDateStr,'anchorIOStart','MM/dd/yyyy'); return false;" NAME="anchorIOStart" ID="anchorIOStart" border="0">
																	<bean:message key="prompt.2.calendar"/>
																	</A>
																	-
																	<html:text name="caseAssignmentForm" property="inOutEndDateStr" size="10" maxlength="10" title="mm/dd/yyyy" />
																	<A HREF="#" onClick="cal.select(caseAssignmentForm.inOutEndDateStr,'anchorIOEnd','MM/dd/yyyy'); return false;" NAME="anchorIOEnd" ID="anchorIOEnd" border="0">
																	<bean:message key="prompt.2.calendar"/>
																	</A>
																</td>
															</tr> 
															<tr>
																<td colspan="2" align="center">
																<input type="button" value="View In & Out Activity" class="button" onclick="javascript:showInOutReport(document.forms[0]);"> 
																<input type="button" value="Cancel" onclick="show('dateRangeInOut', 0)" class="button">
																</td>

															</tr>
														</table>
													</div>	
<!-- END IN & OUT ACTIVITY DIV -->
<!-- BEGIN OV/FV/OTH DIV -->													
													<div id="ovfvoth" class="hidden">
														<table cellspacing="0" cellpadding="4" width="100%">
															<tr class="formDeLabel">
																<td class="formDeLabel">OV / FV / Other Events</td>
																<td align="right"><A href="javascript:show('ovfvoth',0)"><img src="../../images/close.gif" border="0"></a></td>
															</tr>
															<tr>
																<td colspan="2">
																	<table width="100%" border="0" cellspacing="1">
																		<tr>
																			<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.category"/></td>
																			<td class="formDe">
																				<html:select property="selectedCalendarCategory">
																					<html:option value=""><bean:message key="select.generic"/></html:option>
																					<option value="<%= CSEventsReportConstants.CS_CALENDAR_CATEGORY_FIELD_VISITS %>"><%= CSEventsReportConstants.CS_CALENDAR_FIELD_VISITS %></option>
																					<option value="<%= CSEventsReportConstants.CS_CALENDAR_CATEGORY_OFFICE_VISITS %>"><%= CSEventsReportConstants.CS_CALENDAR_OFFICE_VISITS %></option>
																					<option value="<%= CSEventsReportConstants.CS_CALENDAR_CATEGORY_OTHER_EVTS %>"><%= CSEventsReportConstants.CS_CALENDAR_OTHER_EVTS %></option>
																				</html:select>
																			</td>
																		</tr>
																		<tr class="formDe">
																			<td colspan="2">
																				<html:text name="caseAssignmentForm" property="calendarStartDateStr" size="10" maxlength="10" title="mm/dd/yyyy" />
																					<A HREF="#" onClick="cal1.select(caseAssignmentForm.calendarStartDateStr,'anchor141','MM/dd/yyyy'); return false;" NAME="anchor141" ID="anchor141" border="0">
																					<bean:message key="prompt.2.calendar"/>
																					</A>
																				-
																				<html:text name="caseAssignmentForm" property="calendarEndDateStr" size="10" maxlength="10" title="mm/dd/yyyy" />
																					<A HREF="#" onClick="cal1.select(caseAssignmentForm.calendarEndDateStr,'anchor151','MM/dd/yyyy'); return false;" NAME="anchor151" ID="anchor151" border="0">
																					<bean:message key="prompt.2.calendar"/>
																					</A>
																			</td>
																		</tr>
																</table>
															</td>
															</tr>	
															<tr>
																<td colspan="2" align="center">
																	<input type="button" value="Submit" class="button" onclick="javascript:showCalendarReport(document.forms[0]);">
																	<input type="button" value="Cancel" onclick="javascript:show('ovfvoth', 0)" class="button">
																</td>
															</tr>
														</table>
													</div>
<!-- END OV/FV/OTH DIV -->														
												</td>
											</tr>
											<tr>
												<td bgcolor="#cccccc" colspan="2">
													<table width="100%" border="0" cellpadding="2" cellspacing="1">
														<tr>
															<td class="headerLabel">Supervisor</td>
															<td class="headerData"><bean:write name="caseAssignmentForm" property="officerCaseload.selectedSupervisorName"/></td>
															<td class="headerLabel">Officer</td>
															<td class="headerData"><bean:write name="caseAssignmentForm" property="officerNameStr"/></td>
														</tr>
														<tr>
															<td class="headerLabel" width="1%" nowrap="nowrap"># of Supervisees</td>
															<td class="headerData"><bean:write name="caseAssignmentForm" property="officerCaseload.totalSuperviseesInCaseload"/></td>
															<td class="headerLabel" width="1%" nowrap="nowrap"># of Cases</td>
															<td class="headerData"><bean:write name="caseAssignmentForm" property="officerCaseload.toalCasesInCaseload"/></td>
														</tr>											
													</table>
												</td>
											</tr>
										</table>
<!-- BEGIN CURRENT CASELOAD TABLE -->										
										<div class="spacer4px" /></div>
<!-- BEGIN CASELOAD SUPERVISEE TABLE -->
										<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
											<tr class="detailHead">
												<td>Caseload Supervisee(s)</td>
											</tr>
											<tr>
												<td>
													<table width="100%" border="0" cellpadding="2" cellspacing="1">
														<tr class="formDeLabel">
															<td width="1%"></td>
															<td width="1%"></td>
															<td><bean:message key="prompt.superviseeName" /></td>
															<td><bean:message key="prompt.SPN" /></td>
															<td title="Level Of Supervision"><bean:message key="prompt.LOS" /></td>
															<td title="In Jail">J</td>
															<td title="Open Warrant">W</td>
															<td title="Next Office Visit">Next OV</td>
															<td title="Last Face to Face Visit">Last F2F</td>
															<td><bean:message key="prompt.daysLeft" /></td>
														</tr>
														<tr>
															<td width="1%">
																<a href="/<msp:webapp/>caseAssignmentReportResultAction.do?submitAction=Link&superviseeReport.defendantId=<bean:write name="caseAssignmentForm" property="defendantId"/>">
																	<img src="/<msp:webapp/>images/page_go.png" alt="View Casenotes" width="16" height="16" border="0" />
																</a>																	
															</td>
															<td width="1%">
																<a href="javascript:showHide('img<bean:write name="caseAssignmentForm" property="defendantId" />', 
																							 '', '/<msp:webapp/>');" title="View Cases">
																	<img src="/<msp:webapp/>images/expand.gif" name="img<bean:write name="caseAssignmentForm" property="defendantId" />" border="0" />
																</a>
															</td>
															<td>
																<a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&selectedValue=<bean:write name="caseAssignmentForm" property="defendantId"/>&fromPage=<%=UIConstants.FROM_CASELOAD %>">
																	<bean:write name='caseAssignmentForm' property='superviseeNameStr' />
																</a>
															</td>
															<td>
																<bean:write name="caseAssignmentForm" property="defendantId"/>
																<input type="hidden" name="defendantId" value="<bean:write name='caseAssignmentForm' property='defendantId' />" size="5" />
															</td>
															<td><bean:write name="caseAssignmentForm" property="levelOfSupervision"/></td>
															<td><bean:write name="caseAssignmentForm" property="jailIndicator"/></td>
															<td><bean:write name="caseAssignmentForm" property="warrantIndicator"/></td>
															<td><bean:write name="caseAssignmentForm" property="nextOfficeVisitDate"/></td>
															<td><bean:write name="caseAssignmentForm" property="lastFaceToFaceDate"/></td>
															<logic:lessEqual name="caseAssignmentForm" property="daysLeft" value="90">
																<logic:greaterEqual name="caseAssignmentForm" property="daysLeft" value="0">
																	<td><bean:write name="caseAssignmentForm" property="daysLeft"/></td>
																</logic:greaterEqual>
																<logic:lessThan name="caseAssignmentForm" property="daysLeft" value="0">
																	<td class="changedRed"><bean:write name="caseAssignmentForm" property="daysLeft"/></td>
																</logic:lessThan>
															</logic:lessEqual>														
															<logic:greaterThan name="caseAssignmentForm" property="daysLeft" value="90">
																<td><bean:write name="caseAssignmentForm" property="daysLeft"/></td>
															</logic:greaterThan>
														</tr>
														<tr>
															<td></td>
															<td colspan="9">
																<span id="img<bean:write name="caseAssignmentForm" property="defendantId" />Span" class="hidden">
																	<table width="100%" cellpadding="2" cellspacing="1">
																		<tr class="formDeLabel">
																			<td width="1%"></td>
																			<td width="5%" title="Degree of Offense">DEG</td>
																			<td width="50%" title="Case No">Case</td>
																			<td width="1%" title="Court">CRT</td>
																			<td width="15%" title="Start Date of Community Supervision">DOCS</td>
																			<td width="15%" title="End Date of Community Supervision">End Date</td>
																			<td width="15%"><bean:message key="prompt.daysLeft" /></td>
																			<td width="1%" title="Case Status">C</td>
																			<td width="1%" title="Defendant Status">D</td>
																		</tr>
																		<logic:iterate id="activeCase" name="caseAssignmentForm" property="activeCases" indexId="index">
																			<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																		        <td>
																		        	<html:multibox property="casesSelectedForReassignment">
																		        		<bean:write name="activeCase" property="caseAssignmentId"/>
																		        	</html:multibox>
																		        	<input type="hidden" name="activeCaseId" id="<bean:write name="activeCase" property="caseAssignmentId"/>" value="<bean:write name='activeCase' property='criminalCaseId' />" />
																		        </td>
																				<td><bean:write name="activeCase" property="degreeOfOffense"/></td>
																				<td>		
																					<a href="javascript:openWindow('/<msp:webapp/>displaySupervisionOrderDetails.do?submitAction=Link&selectedValue=<bean:write name="activeCase" property="supervisionOrderId"/>')">																																						
																						<bean:write name="activeCase" property="displayCaseNum"/>
																					</a>
																				</td>																																																									
																				<td><bean:write name="activeCase" property="courtId"/></td>
																				<td><bean:write name="activeCase" property="probationStartDate"/></td>
																				<td><bean:write name="activeCase" property="probationEndDate"/></td>
																				<logic:lessEqual name="activeCase" property="daysLeft" value="30">
																					<td class="changedRed"><bean:write name="activeCase" property="daysLeft"/></td>
																				</logic:lessEqual>														
																				<logic:greaterThan name="activeCase" property="daysLeft" value="30">
																					<td><bean:write name="activeCase" property="daysLeft"/></td>
																				</logic:greaterThan>
																				<td><bean:write name="activeCase" property="caseStatus"/></td>
																				<td><bean:write name="activeCase" property="defendantStatus"/></td>																			
																			</tr>
																		</logic:iterate>
																	</table>
																</span>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
<!-- BEGIN CASELOAD SUPERVISEE TABLE -->
										<div class="spacer4px" /></div>
<!-- BEGIN BUTTON TABLE -->
										<table border="0" width="100%">
											<tr>
												<td align="center">

													<logic:equal name="caseAssignmentForm" property="userSecurityFeature" value="<%=UIConstants.CS_REASSIGN_CASE_CSO%>">
														<html:submit property="submitAction" onclick="return validateCaseSelection() && disableSubmit(this, this.form);">
															<bean:message key="button.requestReassignment" />
														</html:submit> 
													</logic:equal>
													<logic:notEqual name="caseAssignmentForm" property="userSecurityFeature" value="<%=UIConstants.CS_REASSIGN_CASE_CSO%>">
														<logic:notEqual name="caseAssignmentForm" property="userSecurityFeature" value="">
															<html:submit property="submitAction" onclick="return validateCaseSelection() && disableSubmit(this, this.form);">
																<bean:message key="button.reassign" />
															</html:submit> 
														</logic:notEqual>
													</logic:notEqual>

													<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CLOSE_SUPERVISION%>'>  
														<html:submit property="submitAction" onclick="return validateCaseSelection() && disableSubmit(this, this.form);">
															<bean:message key="button.closeCase" />
														</html:submit>
													</jims2:isAllowed>		
													
													<html:submit property="submitAction" disabled="true">
														<bean:message key="button.reports" />
													</html:submit>
													<logic:equal name="caseAssignmentForm" property="allowTransfers" value="<%=UIConstants.YES%>">
														<html:submit property="submitAction"  onclick="return caseloadTransferSubmit(this.form) && validateForOOCCase(this) && disableSubmit(this, this.form)" styleId="caseloadSubmitBtnId">
															<bean:message key="button.transferIn"/>
														</html:submit>
														<html:submit property="submitAction"  onclick="return caseloadTransferSubmit(this.form) && validateForOOCCase(this) && disableSubmit(this, this.form)" styleId="caseloadSubmitBtnId">
															<bean:message key="button.transferOut"/>
														</html:submit>	
													</logic:equal>
													
												</td>
											</tr>
										</table>
<!-- END BUTTON TABLE -->
									</logic:notEmpty>
<!-- BEGIN DEFAULT BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
													<bean:message key="button.back" />
												</html:submit> 
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
													<bean:message key="button.cancel" />
												</html:submit> 
											</td>
										</tr>
									</table>
<!-- END DEFAULT BUTTON TABLE -->
								</td>
							</tr>
						</table>
<!-- END BLUE BORDER TABLE -->
					</td>
				</tr>
			</table>
<!-- END FULL PAGE TABLE -->			
		</div>
		<br>			
	</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>