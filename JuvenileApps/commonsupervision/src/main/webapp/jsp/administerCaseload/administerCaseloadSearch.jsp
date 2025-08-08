<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%--10/18/2007	CShimek	defect#46036 added cursor setting and preliminary renderSearch script    --%>
<%--02/28/2008	LDeen	Defect #49819 integrate help    --%>
<%--09/11/2008	CShimek	Defect #54107 set cursor on SPN select and defect #54135 revise default sort field on search results  --%>
<%--10/06/2008	CShimek	Defect #54161 added script and onchange to supervisor select to clear officer and caseload info upon new supervisor select  --%>
<%--11/17/2008	CShimek	Defect #55233 replaced onchange in division drop down with button "Get Supervisors" to correct tabbing submit problem --%>
<%--11/20/2008	CShimek	Defect #55572 added validation to spn value on spn search --%>
<%--01/05/2009  CShimek #56234 added script to transfer buttons to check for case selection --%>
<%--02/12/2009  CShimek #57022 removed cancel button at bottom of page, not needed on page accessed directly from Navigation menu --%>
<%--03/11/2009  CShimek added disableSubmit to buttons --%>
<%--03/17/2009  CShimek revised logic check for transfer buttons, found problem while testing defect 57788 --%>
<%--08/05/2009  CShimek #58722 revised case number display to display only case number value, not CDI and Case combination value --%>
<%--09/24/2009  CShimek #62123 removed logic tag around Filter By Program Referral Provider href --%>
<%--03/02/2010  CShimek #64003 added frompage paramater to filter by href --%>
<%--06/08/2010  CShimek #65740 added onclick function to View Caseload button to reset pagination offset value --%>
<%--06/08/2010  RYoung  #67212 set the size of the spn search field to 8 --%>
<%--04/08/2011  RYoung  #69327 Changed the getSupervisors button to a html- submit  --%>
<%--11/14/2011	TSvines	#71846	Added "function validateForOOCCase()" to match administerCaseloadSearchResults.jsp to facilitate OOC TransferOut Request  --%>
<%--09/21/2012  CShimek #71900 replaced pagination with scrollable text box --%>
<%--10/24/2012  RCapestani #74264 Display Days Left in red when SED<= 90 days --%>
<%--02/08/2013  CShimek #74964 revised logic tag from lessThan to LessEqaul so SED = 90 will display --%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@page import="naming.UIConstants"%>
<%@page import="naming.CSEventsReportConstants"%>
<%@page import="naming.Features"%>

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
	<title>commonSupervision/administerCaseload/administerCaseloadSearch.jsp</title>
	
	<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
	<script type="text/javascript">
	var cal = new CalendarPopup();
	cal.showYearNavigation();
	
	function renderSearch() {
		var defendant = document.getElementById("selectedDefendantId");
		defendant.value = "";
		selectedDefendant = "";
		
		var theElementId = "searchCriteria";
		var theSelect = document.getElementById(theElementId);
		var theSelectValue = theSelect.options[theSelect.selectedIndex].value;	
		switch (theSelectValue) {
			case "SPN":
				show("spSearchSPN", 1);
				show("caseloadSpan", 0);
				document.forms[0].defendantId.focus();
				break;
			case "NAME":
				show("spSearchSPN", 0);
				show("caseloadSpan", 0);
				alert("Feature not available currently");
				break;
			case "CASELOAD":
				show("spSearchSPN", 0);
				show("caseloadSpan", 1);
				break;
		} 
	}
	
	function getSupervisorsForDivision(theForm) {
/*		theForm.action = theForm.action + "?submitAction=Get%2BSupervisors";*/
 		var dSelects = document.getElementsByName("officerCaseload.selectedDivisionId");
 		if (dSelects[0].options.value == ""){
 	 		alert("Division selection required.");
 	 		return false;
 		} 
	}
	
	selectedDefendant = "";

	function selectSuperviseeCase(theSelectedCase, defendantId) {
		var noOfCasesSelected = 0;
		if (selectedDefendant == "" || defendantId != selectedDefendant) {
			selectedDefendant = defendantId;
			var casesSelected = document.getElementsByName("officerCaseload.selectedCasesForReassignment");
			var length = casesSelected.length;			
			for (var i = 0; i < length; i++) {
				if (casesSelected[i].checked) {
					casesSelected[i].checked = false;
					noOfCasesSelected++;
				}
			}
			theSelectedCase.checked = true;
		} else if (defendantId == selectedDefendant) {
			selectedDefendant = defendantId;
			var casesSelected = document.getElementsByName("officerCaseload.selectedCasesForReassignment");
			var length = casesSelected.length;
			var noOfCasesSelected = 0;
			for (var i = 0; i < length; i++) {
				if (casesSelected[i].checked) {
					noOfCasesSelected++;
				}
			}
		}
		var caseloadSubmitButton = document.getElementById("caseloadSubmitBtnId");
		if (caseloadSubmitButton != null) {
			if (noOfCasesSelected > 0) {
				caseloadSubmitButton.disabled = false;	
			} else {
				caseloadSubmitButton.disabled = true;	
			}
		}
		var closeCaseSubmitButton = document.getElementById("closeCaseSubmitBtnId");
		if (closeCaseSubmitButton != null) {
			if (noOfCasesSelected > 0) {
				closeCaseSubmitButton.disabled = false;	 
			} else {
				closeCaseSubmitButton.disabled = true;	 	
			}
		}		
	}
	
	function caseloadSearchSubmit(theForm) {
		var defendant = theForm["defendantId"];
		defendant.value = selectedDefendant;
		return true;
	}

	function caseloadTransferSubmit(theForm) {
		var defendant = theForm["defendantId"];
		defendant.value = selectedDefendant;
// verify at least 1 case selected		
		var casesSelected = document.getElementsByName("officerCaseload.selectedCasesForReassignment");
		var length = casesSelected.length;
		var noOfCasesSelected = 0;
		for (var i = 0; i < length; i++) {
			if (casesSelected[i].checked) {
				if (defendant.value == ""){
					hidflds = document.getElementsByName(casesSelected[i].value);
					defendant.value = hidflds[0].value;
				}	
				return true;
			}
		}
		alert("No case selected to transfer.");
		return false;
	}
	
	function resetOfficers(theForm) {
		var opts = document.getElementsByName("officerCaseload.selectedOfficerId");
		var len = 0;
		if (opts.length > 0){
			theForm.action = theForm.action + "?submitAction=Clear";
			theForm.submit();		
		}	
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
		
	function validateSpn(){
		var defId = document.getElementById("defendantId");
		var spn = trimAll(defId.value);
		if (spn == ""){
			alert("SPN required to search");
			document.forms[0].defendantId.focus();
			return false;
		}
		var numRegExp = /^[0-9]*$/;
		if (numRegExp.test(spn,numRegExp) == false){
			alert("SPN must be numeric.");
			document.forms[0].defendantId.focus();
			return false;
		}
		document.forms[0].defendantId.value = spn;
		return true;
	}

	function validateZipCode() {
		var zipCodeElement = document.getElementById("zipCodeId");
		var zipCode = zipCodeElement.value;
		var numRegExp = /^[0-9]*$/;
		if (numRegExp.test(zipCode,numRegExp) == false){
			alert("Zip code  must be numeric.");
			zipCodeElement.focus();
			return false;
		}
		return true;
	}

	function resetPaging() {
		var ps = document.getElementsByName("pager.offset");
		if (ps.length > 0 || typeof( ps.value)!="undefined"){
			ps[0].value = 0;
		}	
		return true;

	}

	function showSupervisorWorkload() {
		var selectedSupervisorId = document.getElementById("selectedSupervisorId").value;
		var url = "/<msp:webapp/>supervisorWorkloadAction.do?submitAction=Setup&sId=" + selectedSupervisorId;
		openWindow(url);
	}

	function validateForOOCCase()
	{
		var elements = document.getElementsByName("officerCaseload.selectedCasesForReassignment");

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
	</script>
</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="renderSearch();">
	<html:form action="/superviseeSearchSetupAction" target="content" focus="defendantSearchCriteria">
		<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/CSCD_Caseload.htm#|2">
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
									<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
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
								<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header">
											<bean:message key="title.CSCD" />&nbsp;-&nbsp;<bean:message key="prompt.caseload" />&nbsp;<bean:message key="title.search" />
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
													<li>Select Search By to change the search type.</li>
													<li>Enter the required field(s) and click Submit to see results.</li>
												</ul>
											</td>
										</tr>
									</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN SEARCH TABLE -->
									<table width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<tr>
											<td class="formDeLabel" width="1%" nowrap="nowrap">Search By</td>
											<td class="formDe">
												<html:select name="caseAssignmentForm" property="defendantSearchCriteria" styleId="searchCriteria" onchange="renderSearch()">
													<html:option value="CASELOAD">CASELOAD</html:option>
													<html:option value="SPN">SPN</html:option>
												</html:select>
											</td>
										</tr>
									</table>
<!-- end search TABLE -->
									<div class="spacer4px"></div>
<!-- start search by spn -->
									<span class="hidden" id="spSearchSPN">
										<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
											<tr class="detailHead">
												<td>Search By SPN</td>
											</tr>
											<tr>
												<td>
													<table width="100%" cellpadding="2" cellspacing="1">
														<tr>
															<td class="formDeLabel" width="1%" nowrap="nowrap"><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border="0" width="10" height="9">SPN</td>
															<td class="formDe">
																<html:text name="caseAssignmentForm" property="defendantId" styleId="selectedDefendantId" maxlength="8" size="8"/>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
										<table border="0" width="100%">
											<tr>
												<td align="center">
													<html:submit property="submitAction" onclick="return validateSpn()">
														<bean:message key="button.submit" />
													</html:submit> 
													<html:submit property="submitAction">
														<bean:message key="button.refresh" />
													</html:submit>
												</td>
											</tr>
										</table>
									</span>
<!-- end search by spn -->

<!-- start search by officer -->
									<span class="visible" id="caseloadSpan">
									<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td>Search By Caseload</td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1">
													<logic:notEmpty name="caseAssignmentForm" property="officerCaseload.divisions">
														<tr id="divisionRow">
															<td class="formDeLabel" width="1%" nowrap="nowrap">Division</td>
															<td class="formDe" colspan="3">
																<html:select name="caseAssignmentForm" property="officerCaseload.selectedDivisionId"> 
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																	<html:optionsCollection name="caseAssignmentForm" property="officerCaseload.divisions" label="assignedNameQualifiedByPositionName" value="organizationId"/>														
																</html:select>
																<html:submit property="submitAction" onclick="return getSupervisorsForDivision(this.form) && disableSubmit(this, this.form);">
																		<bean:message key="button.getSupervisors" />
																</html:submit>
															</td>
														</tr>
													</logic:notEmpty>
													<logic:notEqual name="caseAssignmentForm" property="officerCaseload.selectedDivisionId" value="">
														<logic:notEmpty name="caseAssignmentForm" property="officerCaseload.supervisorsInDivision"> 
															<tr id="supervisorRow">
																<td class="formDeLabel" width="1%">Supervisor</td>
																<td class="formDe"  colspan="3">  
																	<html:select name="caseAssignmentForm" property="officerCaseload.selectedSupervisorId" onchange="resetOfficers(this.form)" styleId="selectedSupervisorId">
																		<html:option value=""><bean:message key="select.generic" /></html:option>
																		<html:optionsCollection name="caseAssignmentForm" property="officerCaseload.supervisorsInDivision" label="assignedNameQualifiedByPositionName" value="staffPositionId"/>														
																	</html:select>
																	<html:submit property="submitAction" onclick="disableSubmit(this, this.form);">
																		<bean:message key="button.getOfficers" />
																	</html:submit>
																	<input type="button" value="Workload" onclick="showSupervisorWorkload();" />																
																</td>
															</tr>
														</logic:notEmpty>
														<logic:notEmpty name="caseAssignmentForm" property="officerCaseload.selectedSupervisorId">
															<logic:notEmpty name="caseAssignmentForm" property="officerCaseload.officersUnderSupervisor">
																<tr id="officerRow">
																	<td class="formDeLabel" width="1%" nowrap="nowrap">Officer</td>
																	<td class="formDe" colspan="3">
																		<html:select name="caseAssignmentForm" property="officerCaseload.selectedOfficerId">
																			<html:option value=""><bean:message key="select.generic" /></html:option>
																			<html:optionsCollection name="caseAssignmentForm" property="officerCaseload.officersUnderSupervisor" label="assignedNameQualifiedByPositionName" value="staffPositionId"/>														
																		</html:select>
																	</td>
																</tr>
															</logic:notEmpty>
														</logic:notEmpty>
														<tr>
															<td class="formDeLabel" width="1%" nowrap="nowrap">Court</td>
															<td class="formDe" width="10%">
																<html:text name="caseAssignmentForm" maxlength="4" size="4" property="officerCaseload.courtIdFilter"></html:text>
															</td>
															<td class="formDeLabel" width="1%" nowrap="nowrap">Zip Code</td>
															<td class="formDe"> 
																<html:text name="caseAssignmentForm" maxlength="5" size="5" property="officerCaseload.zipCodeFilter" styleId="zipCodeId"></html:text>																
															</td>
														</tr>
														<tr>
															<td class="formDeLabel"></td>
															<td class="formDe" colspan="3">															
																<html:submit property="submitAction" onclick="return validateZipCode() && resetPaging() && disableSubmit(this, this.form)" >
																	<bean:message key="button.viewCaseload" />
																</html:submit>
																<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
																	<bean:message key="button.refresh" />
																</html:submit>
															</td>
														</tr>
													</logic:notEqual>
												</table>
											</td>
										</tr>																		
									</table>	
									<div class="spacer4px" /></div>
									<logic:notEmpty name="caseAssignmentForm" property="officerCaseload.defendantsSupervised">
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
																					<A HREF="#" onClick="cal.select(caseAssignmentForm.calendarStartDateStr,'anchor141','MM/dd/yyyy'); return false;" NAME="anchor141" ID="anchor141" border="0">
																					<bean:message key="prompt.2.calendar"/>
																					</A>
																				-
																				<html:text name="caseAssignmentForm" property="calendarEndDateStr" size="10" maxlength="10" title="mm/dd/yyyy" />
																					<A HREF="#" onClick="cal.select(caseAssignmentForm.calendarEndDateStr,'anchor151','MM/dd/yyyy'); return false;" NAME="anchor151" ID="anchor151" border="0">
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
												</td>
											</tr>
											<tr>
												<td bgcolor="#cccccc" colspan="2">
													<table width="100%" border="0" cellpadding="2" cellspacing="1">
														<tr>
															<td class="headerLabel">Supervisor</td>
															<td class="headerData"><bean:write name="caseAssignmentForm" property="officerCaseload.selectedSupervisorName"/></td>
															<td class="headerLabel">Officer</td>
															<td class="headerData"><bean:write name="caseAssignmentForm" property="officerCaseload.selectedOfficerName"/></td>
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
										<div class="spacer4px" /></div>
										<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
											<tr class="detailHead">
												<td>Caseload Supervisee(s)</td>
												<td align="right">
													<bean:message key="prompt.filterBy"/>&nbsp;<a href="/<msp:webapp/>handleProgramReferralByCaseload.do?submitAction=Link&fromPage=<%=UIConstants.FROM_CASELOAD %>" class="blackSubNav"><bean:message key="prompt.programReferralProvider"/></a>
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<div style="height:258px; overflow:auto; ">
													<table width="100%" border="0" cellpadding="2" cellspacing="1">
														<tr class="formDeLabel">
															<td width="1%"></td>															
															<td width="1%"></td>
															<td>Supervisee Name
																<jims2:sortResults beanName="caseAssignmentForm" results="officerCaseload.defendantsSupervised" primaryPropSort="defendantFullName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" />																
															</td>
															<td>SPN
																<jims2:sortResults beanName="caseAssignmentForm" results="officerCaseload.defendantsSupervised" primaryPropSort="defendantId" primarySortType="INTEGER" defaultSortOrder="ASC" sortId="2" />																
															</td>
															<td title="Level Of Supervision">LOS
																<jims2:sortResults beanName="caseAssignmentForm" results="officerCaseload.defendantsSupervised" primaryPropSort="levelOfSupervision" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" />																
															</td>
															<td title="In Jail">J
																<jims2:sortResults beanName="caseAssignmentForm" results="officerCaseload.defendantsSupervised" primaryPropSort="jailIndicator" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" />																
															</td>
															<td title="Open Warrant">W
																<jims2:sortResults beanName="caseAssignmentForm" results="officerCaseload.defendantsSupervised" primaryPropSort="warrantIndicator" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />																
															</td>
															<td title="Next Office Visit">Next OV
																<jims2:sortResults beanName="caseAssignmentForm" results="officerCaseload.defendantsSupervised" primaryPropSort="nextOfficeVisitDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="6" />																
															</td>
															<td title="Last Face to Face Visit">Last F2F
																<jims2:sortResults beanName="caseAssignmentForm" results="officerCaseload.defendantsSupervised" primaryPropSort="lastFaceToFaceDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="7" />																
															</td>
															<td title="Days Left" width="12%">Days Left
																<jims2:sortResults beanName="caseAssignmentForm" results="officerCaseload.defendantsSupervised" primaryPropSort="daysLeft" primarySortType="INTEGER" defaultSortOrder="ASC" sortId="8" />																
															</td>
														</tr>
														<logic:notEmpty name="caseAssignmentForm" property="officerCaseload.defendantsSupervised">
															<logic:iterate id="supervisee" name="caseAssignmentForm" property="officerCaseload.defendantsSupervised" indexId="index0">
																<tr class="<%out.print((index0.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																	<td width="1%">
																		<a href="/<msp:webapp/>caseAssignmentReportResultAction.do?submitAction=Link&superviseeReport.defendantId=<bean:write name="supervisee" property="defendantId"/>">
																			<img src="/<msp:webapp/>images/page_go.png" alt="View Casenotes" width="16" height="16" border="0" />
																		</a>																	
																	</td>
																	<td width="1%">
																		<a href="javascript:showHide('img<bean:write name="supervisee" property="defendantId" />', 
																									 '', '/<msp:webapp/>');" title="View Cases">
																			<img src="/<msp:webapp/>images/expand.gif" name="img<bean:write name="supervisee" property="defendantId" />" border="0" />
																		</a>
																	</td>
																	<td>
																		<a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&selectedValue=<bean:write name="supervisee" property="defendantId"/>&fromPage=<%=UIConstants.FROM_CASELOAD %>">
																			<bean:write name="supervisee" property="defendantFullName"/>
																		</a>
																	</td>
																	<td><bean:write name="supervisee" property="defendantId"/></td>
																	<td><bean:write name="supervisee" property="levelOfSupervision"/></td>
																	<td><bean:write name="supervisee" property="jailIndicator"/></td>
																	<td><bean:write name="supervisee" property="warrantIndicator"/></td>
																	<td><bean:write name="supervisee" property="formattedNextOfficeVisitDate"/></td>
																	<td><bean:write name="supervisee" property="formattedLastFaceToFaceDate"/></td>
																	<logic:lessEqual name="supervisee" property="daysLeft" value="90">
																		<td class="caseloadDaysLeft"><bean:write name="supervisee" property="daysLeft"/></td>
																	</logic:lessEqual>	
																	<logic:greaterThan name="supervisee" property="daysLeft" value="90">
																		<td><bean:write name="supervisee" property="daysLeft"/></td>
																	</logic:greaterThan>
																</tr> 
																<tr>
																	<td></td>
																	<td colspan="10">
																		<span id="img<bean:write name="supervisee" property="defendantId" />Span" class="hidden">
																			<table width="100%" cellpadding="2" cellspacing="1">
																				<tr class="formDeLabel">
																					<td width="1%"/>
																					<td width="5%" title="Degree of Offense">DEG</td>
																					<td width="35%" title="Case No">Case</td>
																					<td width="1%" title="Court">CRT</td>
																					<td width="15%" title="Start Date of Community Supervision">DOCS</td>
																					<td width="15%" title="End Date of Community Supervision">End Date</td>
																					<td width="15%">Days Left</td>
																					<td width="1%" title="Case Status">C</td>
																					<td width="1%" title="Defendant Status">D</td>
																				</tr>
																				<logic:iterate id="activeCase" name="supervisee" property="caseAssignments" indexId="index1">
																					<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																				        <td>
																						<logic:notEqual name="caseAssignmentForm" property="userSecurityFeature" value="<%=UIConstants.CS_VIEW_CASELOAD%>">
																				        	<input type="checkbox" name="officerCaseload.selectedCasesForReassignment" 
																				        			value="<bean:write name="activeCase" property="caseAssignmentId"/>"
																				        			onclick="selectSuperviseeCase(this, '<bean:write name="activeCase" property="defendantId"/>');"/>
																				        			<input type="hidden" name="activeCaseId" id="<bean:write name="activeCase" property="caseAssignmentId"/>" value="<bean:write name='activeCase' property='criminalCaseId' />" />
																			        	</logic:notEqual>
																				        </td>
																						<td><bean:write name="activeCase" property="degreeOfOffense"/></td>
																						<td>		
																							<a href="javascript:openWindow('/<msp:webapp/>displaySupervisionOrderDetails.do?submitAction=Link&selectedValue=<bean:write name="activeCase" property="supervisionOrderId"/>')">																																						
																								<bean:write name="activeCase" property="displayCaseNum"/>
																							</a>  <%-- <bean:write name="activeCase" property="criminalCaseId"/> --%>
																							<%-- if the case is OOC show ICAOS link --%>
																							<elogic:if name="activeCase" property="courtId" op="equal" value="INF">
																							<elogic:or name="activeCase" property="courtId" op="equal" value="INM" />
																							<elogic:or name="activeCase" property="courtId" op="equal" value="OTF" />
																							<elogic:or name="activeCase" property="courtId" op="equal" value="OTM" />																								
																							<elogic:then>
																							<span style="padding-left:7px"><a href="http://www.interstatecompact.org/" class="editLink" target="_new">ICAOS</a></span>
																							</elogic:then>
																							</elogic:if>
																						</td>																																																									
																						<td><bean:write name="activeCase" property="courtId"/></td>
																						<td><bean:write name="activeCase" property="probationStartDate"/></td>
																						<td><bean:write name="activeCase" property="probationEndDate"/></td>
																						<logic:lessEqual name="activeCase" property="daysLeft" value="90">
																							<td class="caseloadDaysLeft"><bean:write name="activeCase" property="daysLeft"/></td>
																						</logic:lessEqual>
																						<logic:greaterThan name="activeCase" property="daysLeft" value="90">
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
															</logic:iterate>
														</logic:notEmpty>
													</table>
												</div>
												</td>
											</tr>
										</table>	
									</logic:notEmpty>
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<logic:equal name="caseAssignmentForm" property="userSecurityFeature" value="<%=UIConstants.CS_REASSIGN_SUP%>">
													<html:submit property="submitAction" onclick="caseloadSearchSubmit(this.form) && disableSubmit(this, this.form);" disabled="true" styleId="caseloadSubmitBtnId">
														<bean:message key="button.reassign" />
													</html:submit> 
												</logic:equal>
												
												<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CLOSE_SUPERVISION%>'>  
													<html:submit property="submitAction" onclick="caseloadSearchSubmit(this.form) && disableSubmit(this, this.form);" disabled="true" styleId="closeCaseSubmitBtnId">
														<bean:message key="button.closeCase" />
													</html:submit>
												</jims2:isAllowed>	
												
												<logic:equal name="caseAssignmentForm" property="userSecurityFeature" value="<%=UIConstants.CS_REASSIGN_CASE_CLO%>">
													<html:submit property="submitAction" onclick="caseloadSearchSubmit(this.form) && disableSubmit(this, this.form);" disabled="true" styleId="caseloadSubmitBtnId">
														<bean:message key="button.reassign" />
													</html:submit> 
												</logic:equal>
	
												<logic:equal name="caseAssignmentForm" property="userSecurityFeature" value="<%=UIConstants.CS_REASSIGN_CASE_CSO%>">
													<html:submit property="submitAction" onclick="caseloadSearchSubmit(this.form) && disableSubmit(this, this.form);" disabled="true" styleId="caseloadSubmitBtnId">
														<bean:message key="button.requestReassignmentToClo" />
													</html:submit> 
												</logic:equal>
	
												<logic:equal name="caseAssignmentForm" property="userSecurityFeature" value="<%=UIConstants.CS_REASSIGN_ADMIN%>">
													<html:submit property="submitAction" onclick="caseloadSearchSubmit(this.form) && disableSubmit(this, this.form)" disabled="true" styleId="caseloadSubmitBtnId">
														<bean:message key="button.reassign" />
													</html:submit> 
													<input type="button" value="Reports" disabled="true"/>
												</logic:equal>
	
												<logic:equal name="caseAssignmentForm" property="allowTransfers" value="<%=UIConstants.YES%>">
													<html:submit property="submitAction"  onclick="return caseloadTransferSubmit(this.form) && validateForOOCCase(this) && disableSubmit(this, this.form);" styleId="caseloadSubmitBtnId">
														<bean:message key="button.transferIn"/>
													</html:submit>
													<html:submit property="submitAction" onclick="return caseloadTransferSubmit(this.form) && validateForOOCCase(this) && disableSubmit(this, this.form);" styleId="caseloadSubmitBtnId">
														<bean:message key="button.transferOut"/>
													</html:submit>	
												</logic:equal>
											</td>											
										</tr>
									</table>
									</span>
									<!-- end search by officer -->
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>