<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- 10/18/2007	 CShimek		Defect#46036 added cursor setting and preliminary renderSearch script    --%>
<%-- 06/05/2008	 Leslie Deen	#52283 Add Help File --%>
<%-- 02/13/2009  CShimek        #57079 revise instructions as stated in defect --%>
<%-- 08/05/2009  CShimek        corrected js error found during defect 61248 testing --%>
<%-- 04/08/2010  RYoung         #64735 corrected CCO field visit problems --%>
<%-- 05/19/2010  RYoung         #65618 Put Clarence's pagination with scrolling DIV code back --%>

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
<%@page import="messaging.administercaseload.reply.CaseAssignmentResponseEvent"%>

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
	<title>Common Supervision - csCalendar/fieldVisit/caseloadSearch.jsp</title>

	<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
	<script language="javascript" type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
	
<html:javascript formName="caseloadSearch"/>
<script>

function validateForm(theForm)
{

	clearAllValArrays();
	var theSelectValue2 = theForm.defendantSearchCriteria.options[theForm.defendantSearchCriteria.selectedIndex].value;
	if (theSelectValue2 == 'SPN'){
		customValRequired ("superviseeId","SPN is required for search.","");
		customValMinLength("superviseeId","SPN must be at least 3 characters.","3");
		customValMaxLength("superviseeId","SPN cannot be more than 8 characters.","8");
		addNumericValidation("superviseeId","SPN must be numeric.");
	} else if (theSelectValue2 == 'SOZIP'){
		customValRequired ("soZipCode","Zip Code is required for search.","");
		customValMinLength("soZipCode","Zip Code must be at least 3 characters.","3");
		addNumericValidation("soZipCode","Zip Code must be numeric.");
	} else {
		return true;
	}
	return validateCustomStrutsBasedJS(theForm);	
}

function checkForm()
{
  
	
	var myOption = (-1);

	if( document.getElementsByName("caseSuperviseeId").length >= 1 )
	{
		for( i = 0; i < document.getElementsByName("caseSuperviseeId").length; i++ )
		{
			if( document.getElementsByName("caseSuperviseeId")[ i ].checked )
			{
				myOption = i;
			}
		}
	}
	if(myOption == (-1)){
		alert("Please Select a Supervisee");
		return false;
		}
	else
		return true;	
}

function checkGVForm()
{
 	
	var myOption = (-1);

	if( document.getElementsByName("caseSuperviseeIds").length >= 1 )
	{
		for( i = 0; i < document.getElementsByName("caseSuperviseeIds").length; i++ )
		{
			if( document.getElementsByName("caseSuperviseeIds")[ i ].checked )
			{
				myOption = i;
			}
		}
	}
	if(myOption == (-1)){
		alert("Please Select a Supervisee");
		return false;
		}
	else
		return true;	
}

function getSupervisorsForDivision(theForm) 
{		
 		var dSelects = document.getElementsByName("selectedDivisionId");

 		if (dSelects[0].options[dSelects[0].options.selectedIndex].value == ""){
 	 		alert("Division selection required.");
 		} else {	

			theForm.action = theForm.action + "?submitAction=Get Supervisors";
			theForm.submit();
 		}
}

function renderSearchOrig(theSelect)
{
	document.forms[0].superviseeId.value="";
	theSelectValue = theSelect.options[theSelect.selectedIndex].value;
	if (theSelectValue != 'SPN' && theSelectValue != 'CASELOAD' && theSelectValue != 'SOZIP' && theSelectValue != 'SOQUAD')
	{
		alert(theSelectValue + ' search is a future feature.')
	}
}

function resetOfficers(theForm) 
{
	var opts = document.getElementsByName("selectedOfficerId");
	var len = 0;
	if (opts.length > 0){
		theForm.action = theForm.action + "?submitAction=Clear";
		theForm.submit();		
	}	
}

function resetSupervisors(theForm) 
{
	
	var opts = document.getElementsByName("selectedSupervisorId");
	var len = 0;
	if (opts.length > 0){
		theForm.action = theForm.action + "?submitAction=Clear&divisionSelected=true";
		theForm.submit();		
	}	
}

function renderSearch1() {
	if(document.forms[0].quadSearch.value != ""){
		var theSelect = document.getElementById("defendantSearchCriteria");
		for (x = 0; x<theSelect.options.length; x++){
			if (theSelect.options[x].value == document.forms[0].quadSearch.value){
				theSelect.options.selectedIndex = x;
				break;
			}	
		}	
	}	
	renderSearch2();
}
function renderSearch2() {
	document.forms[0].superviseeId.value="";
	var theElementId = "searchCriteria";
	var theSelect = document.getElementById("defendantSearchCriteria");
	var theSelectValue = theSelect.options[theSelect.selectedIndex].value;	

	switch (theSelectValue) {
	case "SPN":
		show("spSearchSPN", 1);
		show("caseloadSpan", 0);
		show("spQuadrant", 0);
		show("spQuadrantDetails", 0);
		show("spCaseloadDetails", 0);
		show("spSearchZip", 0);
		if (typeof(document.forms[0].currentCaseload) != "undefined"){
			show("currentCaseload", 0);
		}
		document.forms[0].superviseeId.focus();
		break;
	case "CASELOAD":
		show("spSearchSPN", 0);	
		show("caseloadSpan", 1);
		show("spCaseloadDetails", 1);
		show("spQuadrant", 0);
		show("spQuadrantDetails", 0);
		show("spSearchZip", 0);
		if (typeof(document.forms[0].currentCaseload) != "undefined"){
			show("currentCaseload", 1);
		}
		break;
	case "SOZIP":
		show("spSearchSPN", 0);			
		show("caseloadSpan", 0);
		show("spQuadrant", 0);
		show("spQuadrantDetails", 0);
		show("spCaseloadDetails", 0);
		show("spSearchZip", 1);
		if (typeof(document.forms[0].currentCaseload) != "undefined"){
			show("currentCaseload", 0);
		}
		document.forms[0].soZipCode.focus();
		break;
	case "SOQUAD":
		show("spSearchSPN", 0);		
		show("caseloadSpan", 0);
		show("spQuadrant", 1);
		show("spQuadrantDetails", 1);
		show("spCaseloadDetails", 0);
		show("spSearchZip", 0);
		if (typeof(document.forms[0].currentCaseload) != "undefined"){
			show("currentCaseload", 0);
		}
		document.forms[0].selectedQuadrantId.focus();
		break;	
	} 
}

function checkForSingleResult() {
    var rbs = document.getElementsByName("caseSuperviseeId");
	if (rbs.length == 1){
		rbs[0].checked = true;
	}	
}

function checkForSingleGVResult() {
    var rbs = document.getElementsByName("caseSuperviseeIds");
	if (rbs.length == 1){
		rbs[0].checked = true;
	}	
}
</script>
</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="checkForSingleResult(); checkForSingleGVResult(); renderSearch1()">
	<html:form action="/displayCaseloadSearchResults" target="content" method="post">
	<input type="hidden" name="quadSearch"  value=<bean:write name="csCalendarDisplayForm" property="quadrantSearch" /> >
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
											<bean:message key="title.CSCD" />&nbsp;-&nbsp;<bean:message key="prompt.caseload" />&nbsp;<bean:message key="title.search" /> -
												<logic:equal name="csCalendarDisplayForm" property="activityFlow" value="addAttendees">
													Add Attendees
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|44">
												</logic:equal>
												<logic:equal name="csCalendarDisplayForm" property="activityFlow" value="create">												
													<logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="OV">
														Office Visit
													<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|19">
													</logic:equal>
													<logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="FV">
														Field Visit
													<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|29">
													</logic:equal>
													<logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="GV">
														Group Office Visit
													<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Calendar/CSCD_Calendar.htm#|47">
													</logic:equal>
												</logic:equal>	
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
													<logic:equal name="csCalendarDisplayForm" property="activityFlow" value="create">												
														<logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="OV">
															<li>Select a Supervisee and click Create Office Visit.</li>														
														</logic:equal>
														<logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="FV">
															<li>Select a Supervisee and click Create Field Visit.</li>														
														</logic:equal>
														<logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="GV">
															<li>Select a Supervisee and click Create Group Office Visit.</li>														
														</logic:equal>
													</logic:equal>	
													<logic:notEqual name="csCalendarDisplayForm" property="activityFlow" value="create">
														<li>Enter the required field(s) and click Submit to see results.</li>
													</logic:notEqual>
												</ul>
											</td>
										</tr>
									</table>
									
									<logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="FV">
										<tiles:insert page="../../csCalendar/fieldVisit/fieldVisitItineraryTile.jsp" flush="true">
											<tiles:put name="itinerary" beanName="csCalendarFVForm" beanProperty="currentItinerary" />
											<tiles:put name="displayEvents" value="true"/>
										</tiles:insert>
										<div class="spacer4px"></div>
									</logic:equal>
									
									
									<!-- END INSTRUCTION TABLE -->
									<span id="searchChoice">
										<!-- begin search TABLE -->
										<table width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
											<tr>
												<td class="formDeLabel" width="1%" nowrap="nowrap">
													Search By
												</td>
												<td class="formDe">
													<select id="defendantSearchCriteria" name="defendantSearchCriteria" onchange="renderSearch2()">
														<option value="CASELOAD">CASELOAD</option>
														<!-- <option value="NAME" >NAME</option>-->
														<option value="SPN">SPN</option>
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALFV_ZIP_SEARCH%>'>
															<option value="SOZIP">SEX OFFENDER UNIT ZIP CODE</option>
														</jims2:isAllowed>	 
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALFV_QUAD_SEARCH%>'>
															<option value="SOQUAD">SEX OFFENDER QUADRANTS</option>
														</jims2:isAllowed>
													</select>
												</td>
											</tr>
										</table>
										<!-- end search TABLE -->
										<div class="spacer4px"></div>
									</span>
									
									<!-- start search by spn -->
									<span class="visible" id="spSearchSPN">
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
																<input type="text" name="superviseeId" size="10" maxlength="8"/>
															</td>
														</tr>
														<tr>
															<td class="formDeLabel"></td>
															<td class="formDe" colspan="3" >		
																<html:submit property="submitAction" onclick="return validateForm(this.form);">
																	<bean:message key="button.submit" />											
																</html:submit>
																<html:submit property="submitAction">
																	<bean:message key="button.refresh" />
																</html:submit>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											</table>
										<div class="spacer4px"></div>
									</span>
									<!-- end search by spn -->
									
									<!-- start search by officer -->
									<span class="hidden" id="caseloadSpan">
									<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td>Search By Caseload</td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1">
												<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CASELOAD_ADMIN%>'>
													<logic:notEmpty name="csCalendarDisplayForm" property="divisionList">
														<tr id="divisionRow">
															<td class="formDeLabel" width="1%" nowrap="nowrap">Division</td>
															<td class="formDe" colspan="3">
																<html:select name="csCalendarDisplayForm" property="selectedDivisionId" onchange="resetSupervisors(this.form)"> 
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																	<html:optionsCollection name="csCalendarDisplayForm" property="divisionList" label="divisionName" value="organizationId" />														
																</html:select>

																<html:submit property="submitAction">
																	<bean:message key="button.getSupervisors" />
																</html:submit>																
															</td>
														</tr>
													</logic:notEmpty>
												</jims2:isAllowed>
												
													<logic:notEmpty name="csCalendarDisplayForm" property="selectedDivisionId">
														<tr id="supervisorRow">
															<td class="formDeLabel" width="1%">Supervisor</td>
															<td class="formDe" colspan="3">
																<html:select name="csCalendarDisplayForm" property="selectedSupervisorId" onchange="resetOfficers(this.form)">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																	<html:optionsCollection name="csCalendarDisplayForm" property="supervisorList" label="assignedNameQualifiedByPositionName" value="staffPositionId"/>														
																</html:select>
																<html:submit property="submitAction">
																	<bean:message key="button.getOfficers" />
																</html:submit>
															</td>
														</tr>
														<logic:notEmpty name="csCalendarDisplayForm" property="selectedSupervisorId">
															<logic:notEmpty name="csCalendarDisplayForm" property="officerList">
																<tr id="officerRow">
																	<td class="formDeLabel" width="1%" nowrap="nowrap">Officer</td>
																	<td class="formDe" colspan="3">
																		<html:select name="csCalendarDisplayForm" property="selectedOfficerId">
																			<html:option value=""><bean:message key="select.generic" /></html:option>
																			<html:optionsCollection name="csCalendarDisplayForm" property="officerList" label="assignedNameQualifiedByPositionName" value="staffPositionId"/>														
																		</html:select>
																	</td>
																</tr>
															</logic:notEmpty>
														</logic:notEmpty>
														<tr>
															<td class="formDeLabel">Court</td>
															<td class="formDe" width="1%" nowrap="nowrap">
																<html:text name="csCalendarDisplayForm" property="courtIdFilter" size="8" maxlength="6"></html:text>
															</td>
															<td class="formDeLabel" width="1%" nowrap="nowrap">Zip Code</td>
															<td class="formDe">
																<html:text name="csCalendarDisplayForm" property="zipCode" size="8" maxlength="5"></html:text>
															</td>
														</tr>	
														<tr>
															<td class="formDeLabel"></td>
															<td class="formDe" colspan="3" >		
																<html:submit property="submitAction">
																	<bean:message key="button.viewCaseload" />
																</html:submit>
																<html:submit property="submitAction">
																	<bean:message key="button.refresh" />
																</html:submit>
															</td>
														</tr>	
													</logic:notEmpty>
												</table>
											</td>
										</tr>																		
									</table>
									</span>
									
									<!-- start CASELOAD DETAILS -->
									<span class="hidden" id="spCaseloadDetails">
										<div class="spacer4px" ></div>
										<table width="98%" border="0" cellpadding="0" cellspacing="0">
											<tr class="paddedFourPix">
												<td class="formDeLabel">Current Caseload</td>
											</tr>
											<tr>
												<td bgcolor="#cccccc" colspan="2">
													<table width="100%" border="0" cellpadding="2" cellspacing="1">
														<tr class="paddedFourPix">
															<td class="headerLabel" width="2%" >Supervisor</td>
															<td class="headerData" width="5%"><bean:write name="csCalendarDisplayForm" property="selectedSupervisorName" /></td>
															
															<td class="headerLabel" width="2%">Officer </td>
															<td class="headerData" width="5%"><bean:write name="csCalendarDisplayForm" property="selectedOfficerName" /></td>
														</tr>
								
														<tr class="paddedFourPix">
															<td class="headerLabel" width="2%" nowrap="nowrap"># of Supervisees</td>
															<td class="headerData"><bean:write name="csCalendarDisplayForm" property="totalSuperviseesInCaseload" /></td>
															
															<td class="headerLabel" width="2%" nowrap="nowrap"># of Cases</td>
															<td class="headerData"><bean:write name="csCalendarDisplayForm" property="totalCasesInCaseload" /></td>
														</tr>
													</table>
												</td>
											</tr>					
										</table>
									</span>
									<!-- end CASELOAD DETAILS -->	
									
									<!-- end search by officer -->
									<!-- start search by SEX OFFENDER QUADRANT -->
									<span class="hidden" id="spQuadrant">
										<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
											<tr class="detailHead">
												<td><bean:message key="prompt.searchBy" />&nbsp;<bean:message key="prompt.sexOffender" />&nbsp;<bean:message key="prompt.quadrant" /></td>
											</tr>
											<tr>
												<td>
													<table width="100%" cellpadding="2" cellspacing="1">
														<tr>
															<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.quadrant" /></td>
															<td class="formDe">
																<html:select name="csCalendarDisplayForm" property="selectedQuadrantId">
																	<html:optionsCollection name="csCalendarFVForm" property="quadrantList" value="supervisionCode" label="description"/>
																</html:select>
															</td>
														</tr>
														<tr>
															<td class="formDeLabel" width="1%" nowrap="nowrap"></td>
															<td class="formDe">
																<html:submit property="submitAction"><bean:message key="button.viewSupervisees" /></html:submit>
															</td>
														</tr>		
													</table>
												</td>
											</tr>
										</table>
									</span>
									<!-- end search by SEX OFFENDER QUADRANT -->
									<!-- start QUADRANT DETAILS -->
									<span class="hidden" id="spQuadrantDetails">
										<div class="spacer4px" ></div>
										<table width="98%" border="0" cellpadding="0" cellspacing="0">
											<tr class="paddedFourPix">
												<td class="formDeLabel"><bean:message key="prompt.sexOffender" />&nbsp;<bean:message key="prompt.quadrant" />s</td>
											</tr>
											<tr>
												<td bgcolor="#cccccc" colspan="2">
													<table width="100%" border="0" cellpadding="2" cellspacing="1">
														<tr>
															<td class="headerLabel" ><bean:message key="prompt.quadrant" /></td>
															<td class="headerData"><bean:write name="csCalendarDisplayForm" property="quadrantDescription" /></td>
														</tr>
														<tr>
															<td class="headerLabel" width="1%" nowrap="nowrap"># of <bean:message key="prompt.supervisee" />s</td>
															<td class="headerData"><bean:write name="csCalendarDisplayForm" property="totalSuperviseesInCaseload" /></td>
														</tr>
													</table>
												</td>
											</tr>					
										</table>
									</span>
									<!-- end QUADRANT DETAILS -->								

									<!-- start search by SEXOFFENDER ZIP -->
									<span class="visible" id="spSearchZip">
										<table align="center" width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
											<tr class="detailHead">
												<td><bean:message key="prompt.searchBy" />&nbsp;<bean:message key="prompt.sexOffender" />&nbsp;<bean:message key="prompt.unit" />&nbsp;<bean:message key="prompt.zipCode" /></td>
											</tr>
											<tr>
												<td>
													<table width="100%" cellpadding="2" cellspacing="1">
														<tr>
															<td class="formDeLabel" width="1%" nowrap="nowrap"><img src="/<msp:webapp/>images/required.gif" title="required" alt="required image" border="0" width="10" height="9">Zip Code</td>
															<td class="formDe"><input type="text" name="soZipCode" size="6" maxlength="5"/></td>
														</tr>
														
														<tr>
															<td class="formDeLabel"></td>
															<td class="formDe" colspan="3" >		
																<html:submit property="submitAction" onclick="return validateForm(this.form);">
																	<bean:message key="button.submit" />											
																</html:submit>
																<html:submit property="submitAction">
																	<bean:message key="button.refresh" />
																</html:submit>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</span>
									<!-- end search by SEXOFFENDER ZIP -->
									<div class="spacer4px" /></div>
									<logic:notEmpty name="csCalendarDisplayForm" property="defendantsSupervised">
									
										<!-- Begin Pagination Header Tag-->
										<bean:define id="paginationResultsPerPage" type="java.lang.String">
											<bean:message key="pagination.recordsPerPage"></bean:message>
										</bean:define> 
										<span class="hidden" id="currentCaseload" > 
											<table width="98%" border="0" cellpadding="0" cellspacing="0">
												<tr class="paddedFourPix">
													<td class="formDeLabel">Current Caseload</td>
												</tr>
												<tr>
													<td bgcolor="#cccccc" colspan="2">
														<table width="100%" border="0" cellpadding="2" cellspacing="1">
															<tr>
																<td class="headerLabel">Supervisor</td>
																<td class="headerData"><bean:write name="csCalendarDisplayForm" property="selectedSupervisorName"/></td>
																<td class="headerLabel">Officer</td>
																<td class="headerData"><bean:write name="csCalendarDisplayForm" property="selectedOfficerName"/></td>
															</tr>
															<tr>
																<td class="headerLabel" width="1%" nowrap="nowrap"># of Supervisees</td>
																<td class="headerData"><bean:write name="csCalendarDisplayForm" property="totalSuperviseesInCaseload"/></td>
																<td class="headerLabel" width="1%" nowrap="nowrap"># of Cases</td>
																<td class="headerData"><bean:write name="csCalendarDisplayForm" property="totalCasesInCaseload"/></td>
															</tr>											
														</table>
													</td>
												</tr>
											</table>
											<div class="spacer4px" /></div>
											</span>
											<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
												<tr class="detailHead">
													<td>Caseload Supervisee(s)</td>
												</tr>
											</table>	
											<table width="98%" border="0" cellpadding="0" cellspacing="0" >	
												<tr>
													<td>
														<div class="scrollingDiv300">
														<table width="100%" border="0" cellpadding="2" cellspacing="1"> 
															<tr class="formDeLabel">
																<td width="1%"></td>															
																<td width="1%"></td>
																<td width="1%"></td>
																<td>Supervisee Name
																	<jims2:sortResults beanName="csCalendarDisplayForm" results="defendantsSupervised" primaryPropSort="defendantFullName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" levelDeep="3"/>																
																</td>
																<td>SPN
																	<jims2:sortResults beanName="csCalendarDisplayForm" results="defendantsSupervised" primaryPropSort="defendantId" primarySortType="INTEGER" defaultSortOrder="ASC" sortId="2" levelDeep="3"/>																
																</td>
																<td title="Level Of Supervision">LOS
																	<jims2:sortResults beanName="csCalendarDisplayForm" results="defendantsSupervised" primaryPropSort="levelOfSupervision" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" levelDeep="3"/>																
																</td>
																<td title="In Jail">J
																	<jims2:sortResults beanName="csCalendarDisplayForm" results="defendantsSupervised" primaryPropSort="jailIndicator" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" levelDeep="3"/>																
																</td>
																<td title="Open Warrant">W
																	<jims2:sortResults beanName="csCalendarDisplayForm" results="defendantsSupervised" primaryPropSort="warrantIndicator" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" levelDeep="3" />																
																</td>
																<td title="Next Office Visit">Next OV
																	<jims2:sortResults beanName="csCalendarDisplayForm" results="defendantsSupervised" primaryPropSort="nextOfficeVisitDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="6" levelDeep="3" />																
																</td>
																<td title="Last Face to Face Visit">Last F2F
																	<jims2:sortResults beanName="csCalendarDisplayForm" results="defendantsSupervised" primaryPropSort="lastFaceToFaceDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="7" levelDeep="3" />																
																</td>
																<td title="Days Left">Days Left
																	<jims2:sortResults beanName="csCalendarDisplayForm" results="defendantsSupervised" primaryPropSort="daysLeft" primarySortType="INTEGER" defaultSortOrder="ASC" sortId="8" levelDeep="3" />																
																</td>
															</tr>
															<logic:notEmpty name="csCalendarDisplayForm" property="defendantsSupervised">
																<logic:iterate id="supervisee" name="csCalendarDisplayForm" property="defendantsSupervised" indexId="indexSPN">
																	<pg:item>
																	<tr class="<%out.print((indexSPN.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																	<td>
																     <logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="GV">																	
																			<html:checkbox styleId="caseSuperviseeIds" name="csCalendarDisplayForm" property="caseSuperviseeIds" value="<%=((CaseAssignmentResponseEvent)supervisee).getDefendantId()%>" />
																	 </logic:equal>
																	 <logic:notEqual name="csCalendarDisplayForm" property="selectedEventTypeCd" value="GV">
																		<html:radio styleId="caseSuperviseeId" name="csCalendarDisplayForm" property="caseSuperviseeId" value="<%=((CaseAssignmentResponseEvent)supervisee).getDefendantId()%>" />
																	 </logic:notEqual>
																	</td>
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
																			<a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&selectedValue=<bean:write name="supervisee" property="defendantId"/>">
																				<bean:write name="supervisee" property="defendantFullName"/>
																			</a>
																		</td>
																		<td><bean:write name="supervisee" property="defendantId"/></td>
																		<td><bean:write name="supervisee" property="levelOfSupervision"/></td>
																		<td><bean:write name="supervisee" property="jailIndicator"/></td>
																		<td><bean:write name="supervisee" property="warrantIndicator"/></td>
																		<td><bean:write name="supervisee" property="formattedNextOfficeVisitDate"/></td>
																		<td><bean:write name="supervisee" property="formattedLastFaceToFaceDate"/></td>
																		<logic:greaterEqual name="supervisee" property="daysLeft" value="0">
																			<td><bean:write name="supervisee" property="daysLeft"/></td>
																		</logic:greaterEqual>
																		<logic:lessThan name="supervisee" property="daysLeft" value="30">
																			<td class="caseloadDaysLeft"><bean:write name="supervisee" property="daysLeft"/></td>
																		</logic:lessThan>											
																		<logic:greaterThan name="supervisee" property="daysLeft" value="90">
																			<td></td>
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
																					<logic:iterate id="activeCase" name="supervisee" property="caseAssignments" indexId="index">
																						<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																					        <td>
																					        <%-- 
																					        	<html:multibox property="casesSelectedForReassignment">
																					        		<bean:write name="activeCase" property="caseAssignmentId"/>
																					        	</html:multibox>
																					         --%>	
<%-- <bean:write name="activeCase" property="criminalCaseId"/> --%>
																					        </td>
																							<td><bean:write name="activeCase" property="degreeOfOffense"/></td>
																							<td>		
																								<a href="javascript:openWindow('/<msp:webapp/>displaySupervisionOrderDetails.do?submitAction=Link&selectedValue=<bean:write name="activeCase" property="supervisionOrderId"/>')">																																						
																									
																									<bean:write name="activeCase" property="displayCaseNum"/>
																								</a>
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
																							<logic:equal name="activeCase" property="daysLeft" value="<%=String.valueOf(Long.MAX_VALUE)%>">
																								<td></td>
																							</logic:equal>
																							<logic:notEqual name="activeCase" property="daysLeft" value="<%=String.valueOf(Long.MAX_VALUE)%>">
																							<logic:greaterEqual name="activeCase" property="daysLeft" value="31">
																								<td><bean:write name="activeCase" property="daysLeft"/></td>
																							</logic:greaterEqual>
																							<logic:lessEqual name="activeCase" property="daysLeft" value="30">
																								<td class="caseloadDaysLeft"><bean:write name="activeCase" property="daysLeft"/></td>
																							</logic:lessEqual>
																							</logic:notEqual>
																							<td><bean:write name="activeCase" property="caseStatus"/></td>
																							<td><bean:write name="activeCase" property="defendantStatus"/></td>																			
																						</tr>
																					</logic:iterate>
																				</table>
																			</span>
																		</td>
																	</tr>			
																	</pg:item>													
																</logic:iterate>
															</logic:notEmpty>
															
														</table> 
														</div>
													</td>
												</tr>

											</table>	
										<%-- 	</pg:pager>   --%>
										<!-- BEGIN BUTTON TABLE -->
										<table border="0" width="100%">
											<tr>
												<td align="center">
												
												<logic:equal name="csCalendarDisplayForm" property="activityFlow" value="addAttendees">
													<input type="submit" value="<bean:message key='button.addSelected' />" name="submitAction" 
														onclick=" return checkGVForm() && changeFormActionURL(this.form, '/<msp:webapp/>displayCSGVSummary.do?submitAction=Next',false);">
												</logic:equal>
												<logic:equal name="csCalendarDisplayForm" property="activityFlow" value="create">
													<logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="FV">
													<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALFV_CREATE%>'>
														<html:submit property="submitAction" onclick="return checkForm();">
															<bean:message key="button.createFieldVisit" />
														</html:submit> 
													</jims2:isAllowed>	
													</logic:equal>
													<logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="OV">
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALOV_CREATE%>'>
														<input type="submit" value="<bean:message key='button.createOfficeVisit' />" name="submitAction" onclick="return checkForm() && changeFormActionURL(this.form, '/<msp:webapp/>displayCSOVCreateUpdate.do',false);">
														</jims2:isAllowed>	
													</logic:equal>
													<logic:equal name="csCalendarDisplayForm" property="selectedEventTypeCd" value="GV">
														<logic:notEqual name="csCalendarOVForm" property="activityFlow" value="addAttendees">
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CALGOV_CREATE%>'>
															<input type="submit" value="<bean:message key='button.createGroupOfficeVisit' />" name="submitAction" onclick="return checkGVForm() && changeFormActionURL(this.form, '/<msp:webapp/>displayCSGVCreateUpdate.do',false);">
														</jims2:isAllowed>
														</logic:notEqual>
														
													</logic:equal>
												</logic:equal>	
												</td>
											</tr>
										</table>
										<!-- END BUTTON TABLE -->
									</logic:notEmpty>
									
									<div align="center">												
											<div class="hidden" id="spnSpan">											
											<html:submit property="submitAction" onclick="return validateForm(this.form);">
											<bean:message key="button.submit" />											
										</html:submit>
										<html:submit property="submitAction">
											<bean:message key="button.refresh" />
										</html:submit>
								        </div>
										<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
										<html:submit property="submitAction">
											<bean:message key="button.cancel" />
										</html:submit>
									</div>								
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