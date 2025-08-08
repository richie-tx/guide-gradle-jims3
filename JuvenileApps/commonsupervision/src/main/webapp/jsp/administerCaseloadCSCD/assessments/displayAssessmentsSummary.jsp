<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 01/21/2009   CShimek    defect#56515 added onload to clear radio button selections -->
<!-- 02/05/2009   DWilliamson ER #56719 Changed label Initials to Initial Assessments -->
<!-- 01/25/2010   CShimek    defect#63653 remove logic tags around New SCS Inventory and New SCS Interview buttons so they are always accessable for user with feature -->
<!-- 02/10/2010   CShimek    defect#63925 added logic tag to hide New Supervision Plan button when no active supervision found. -->
<!-- 07/23/2010   CShimek    Activity #66506 revised button labels (Update to Correct Initial(assessment) or Correct(reassessment), Process to Re-Assess Wisconsin, etc.) and removed smart button logic. -->
<!-- 01/06/2012   RCapestani defect #72312 added logic to hide  New SCS Interview and New SCS Inventory buttons when SCS Interview or SCS Inventory exists -->
<!-- 04/04/2013   RYoung	 ER #75403 CSCD: Unable to create annual re-assessment *removed all pagenation-->
<!-- 10/11/2013   RCapestani defect #75929 display View Historical Assessment button -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@page import="naming.PDCodeTableConstants"%>
<%@page import="naming.CSCAssessmentConstants"%>
<%@ page import="naming.Features" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/assessments/displayAssessmentsSummary.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>common/sorttable.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>common/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript'>
function setInitialorReassess(isInitial)
{
	if(isInitial=="true")
	{
		document.forms[0].selectedInitialorReassess.value="INITIAL_ASSESSMENT";
	}
	else
	{
		document.forms[0].selectedInitialorReassess.value="REASSESSMENT";
	}
}

//changes the update button under the reassessments area from Update to Process
function checkButtonStatus(val)
{
	if(val=="Update")
	{
		document.forms[0].reasmtCorrect.disabled = false;
		document.forms[0].reasmtProcess.disabled = true;
	}
	else
	{
		document.forms[0].reasmtCorrect.disabled = true;
		document.forms[0].reasmtProcess.disabled = false;
	}
}


var initialAssessType = ""
var initialAssessId = ""

var reAssessType = ""
var reAssessId = ""

function setInitialAssess(assessType,assessId, isComplete)
{
	initialAssessType=assessType;
	initialAssessId=assessId;

//	alert(typeof(document.forms[0].asmtUpdateNotRestrected) + "  " + isComplete);
	if (isComplete) {
		document.forms[0].asmtCorrectInitial.disabled = true;
		if (typeof(document.forms[0].asmtUpdateNotRestrected) != "undefined") {
			document.forms[0].asmtCorrectInitial.disabled = false;
		}
	} else {
		document.forms[0].asmtCorrectInitial.disabled = false;
	}		
}


function setReAssess(assessType,assessId, isComplete)
{
	reAssessType=assessType;
	reAssessId=assessId;

	if (isComplete) {
		document.forms[0].reasmtCorrect.disabled = true;
		if (typeof(document.forms[0].reasmtUpdateNotRestrected) != "undefined") {
			document.forms[0].reasmtCorrect.disabled = false;
		}
	} else {
		document.forms[0].reasmtCorrect.disabled = false;
	}
}


//determines where the Update button under the reassessments area navigates to
function determineInitalAssessUpdate()
{
	var url = "";
	if (initialAssessType == "W")
	{
		url = "/<msp:webapp/>displayWisconsinCreateUpdate.do?submitAction=Update Link" + "&selectedAssessmentBeanId=" + initialAssessId + "&selectedAssessmentType=true" ;
		goNav(url);
	}
	else
	if (initialAssessType == "L")
	{
		url = "/<msp:webapp/>displayLSIRCreateUpdate.do?submitAction=Update Link" + "&selectedAssessmentBeanId=" + initialAssessId + "&selectedAssessmentType=true" ;
		goNav(url);
	}
	else
	if (initialAssessType == "S")
	{
		url = "/<msp:webapp/>displaySCSCreateUpdate.do?submitAction=Update Link" + "&selectedAssessmentBeanId=" + initialAssessId ;
		goNav(url);
	}
	else
	if (initialAssessType == "I")
	{
		url = "/<msp:webapp/>displaySCSInterviewCreateUpdate.do?submitAction=Update Link" + "&selectedAssessmentBeanId=" + initialAssessId ;
		goNav(url);
	}
	else
	if (initialAssessType == "F")
	{
		url = "/<msp:webapp/>displayForceFieldCreateUpdate.do?submitAction=Update Link" + "&selectedAssessmentBeanId=" + initialAssessId ;
		goNav(url);
	}
	if(url == "")
	{
		alert("Selection required for update.");
	}
}	


//determines where the Update/Process button under the reassessments area navigates to
function determineReassessUpdateOrProcess()
{
	var url = "";

	if (reAssessType == "W")
	{
		if(reAssessId == "Process")
		{
			url = "/<msp:webapp/>displayWisconsinCreateUpdate.do?submitAction=Process";
			goNav(url);
		}
		else
		{
			url = "/<msp:webapp/>displayWisconsinCreateUpdate.do?submitAction=Update Link" + "&selectedAssessmentBeanId=" + reAssessId + "&selectedAssessmentType=false" ;
			goNav(url);
		}
		
	} 
	else
	if (reAssessType == "L")
	{
		url = "/<msp:webapp/>displayLSIRCreateUpdate.do?submitAction=Update Link" + "&selectedAssessmentBeanId=" + reAssessId + "&selectedAssessmentType=false" ;
		goNav(url);
	}
	if (url == ""){
		alert("Selection required for update.");
	}
}

var selectedSupervisionPlanStatus = "";

function onSupervisionPlanSelect(selectedSupervisionPlanId, selectedSupPlanStatus)
{
	document.forms[0].selectedSupervisionPlanId.value = selectedSupervisionPlanId;
	selectedSupervisionPlanStatus = selectedSupPlanStatus;
}

function onUpdateSupervisionPlan()
{
	if((selectedSupervisionPlanStatus!=null) && (selectedSupervisionPlanStatus!=""))
	{
		if(selectedSupervisionPlanStatus == "D")
		{
			url = "/<msp:webapp/>handleSupervisionPlanDetails.do?submitAction=Update+Link&selectedSupervisionPlanId=" +  document.forms[0].selectedSupervisionPlanId.value;
			goNav(url);
		}
		else
		{
			alert("Active/Inactive Supervision Plan cannot be updated.");
		}
	}
	else 
	{
		alert("Selection required for update.");
	}	
}

function onCopySupervisionPlan()
{
	if((selectedSupervisionPlanStatus!=null) && (selectedSupervisionPlanStatus!=""))
	{
		if(selectedSupervisionPlanStatus == "A" || selectedSupervisionPlanStatus=="I")
		{
			url = "/<msp:webapp/>handleSupervisionPlanDetails.do?submitAction=Copy+Link&selectedSupervisionPlanId=" +  document.forms[0].selectedSupervisionPlanId.value;
			goNav(url);
		}
		else
		{
			alert("Draft Supervision Plan cannot be copied.");
		}
	} 
	else
	{
		alert("Selection required to copy.");
	}	
}

function validateDateField(theForm) {

	if (theForm.historicalBeginDateStr.value == "" && theForm.historicalEndDateStr.value == ""){
		alert("Assessment Date is required to Filter");
		theForm.historicalBeginDateStr.focus();
		return false;
	}	
    clearAllValArrays();
      		    
    addMMDDYYYYDateValidation("historicalBeginDateStr","Beginning Assessment Date must be in date format (mm/dd/yyyy). ");
    addMMDDYYYYDateValidation("historicalEndDateStr","Ending Assessment Date must be in date format (mm/dd/yyyy). ");
    return validateCustomStrutsBasedJS(theForm);
}


function clearSelects()
{
	for (x=0; x< document.forms[0].length; x++)
	{
		if (document.forms[0].elements[x].type == "radio")
		{
			document.forms[0].elements[x].checked = false;
		}	
	}		
}

</script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="checkEnterKeyAndSubmit(event,true)" onload="clearSelects()">
<html:form action="/displayAssessmentSummary" target="content">

<SCRIPT LANGUAGE="JavaScript" ID="js1">
	var cal1 = new CalendarPopup();
	cal1.showYearNavigation();
</SCRIPT>

<div align="center">
<!-- BEGIN MAIN TABLE -->	
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
							<!--tabs start-->
							<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tab" value="caseloadTab" />
							</tiles:insert> 
							<!--tabs end-->
						</td>
					</tr>
					<tr>
						<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
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
<!-- BEGIN SUPERVISEE INFORMATION TABLE  -->
							<tiles:insert page="../../common/superviseeHeader.jsp" flush="true"></tiles:insert>		
<!-- END SUPERVISEE INFORMATION TABLE  -->	
                        	<div class="spacer4px"></div> 
<!-- BEGIN GREEN TABS TABLE -->	
							<table width="98%" border="0" cellpadding="0" cellspacing="0" >
								<tr>
									<td valign="top">
										<!--tabs start-->
										<tiles:insert page="../../common/caseloadCSCDSubTabs.jsp" flush="true">
											<tiles:put name="tab" value="AssessmentsTab" />
										</tiles:insert>
									</td>
								</tr>
								<tr>
									<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
							</table>
<!-- END GREEN TABS TABLE -->	
<!-- BEGIN GREEN BORDER TABLE -->				
							<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
								<tr>
									<td valign="top" align="center">
<!-- BEGIN HEADING AREA -->
										<div class="header"><bean:message key="title.CSCD"/>&nbsp;-
                                    	   <logic:equal name="assessmentForm" property="supervisionPeriod" value="<%= CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_INACTV %>">    
												<bean:message key="prompt.historical"/>&nbsp;<bean:message key="prompt.assessments"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|2">
										   </logic:equal>
										   <logic:equal name="assessmentForm" property="supervisionPeriod" value="<%= CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV %>">    
											   	<bean:message key="prompt.assessments"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|1">
									   		</logic:equal>
									   	</div>
<%-- BEGIN ERROR TABLE --%>
										<table width="98%" align="center">
											<tr>
												<td align="center" class="errorAlert"><html:errors></html:errors></td>
											</tr>
										</table>								
<!-- END ERROR TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
										<table width="98%" border="0" cellpadding="0" cellspacing="0">
											<logic:present name="confirmMessage">
												<tr id="confirmations">
													<td class="confirm">
														<bean:write name="confirmMessage" />
													</td>
												</tr>
											</logic:present>
											<tr>
												<td>
													<ul>
														<li>Select a hyperlinked assessment to view details or select the assessment and click the appropriate button.</li>
													</ul>
												</td>
											</tr>
										</table>									
<!-- END INSTRUCTION TABLE -->
<!--BEGIN FILTER BY DATE RANGE -->
										<logic:equal name="assessmentForm" property="supervisionPeriod" value="<%= CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_INACTV %>">									
											<table width="98%" cellpadding="2" cellspacing="1" class=borderTableBlue>
												<tr>
													<td class="formDeLabel" width="1%" nowrap>Filter By Assessment Date Range</td>
													<td class="formDe">
														<table cellpadding="2" cellspacing="1">
															<tr>
																<td class="formDe">		
																	<html:text name="assessmentForm" property="historicalBeginDateStr" size="10" maxlength="10" title="(mm/dd/yyyy)" />
																	<a href="#" onClick="cal1.select(assessmentForm.historicalBeginDateStr,'anchor1','MM/dd/yyyy'); return false;" NAME="anchor1" ID="anchor1" border=0><bean:message key="prompt.3.calendar"/></a> </td>
																<td class="formDe">-</td>
																<td class="formDe">
																	<html:text name="assessmentForm" property="historicalEndDateStr" size="10" maxlength="10" title="(mm/dd/yyyy)" />
																	<a href="#" onClick="cal1.select(assessmentForm.historicalEndDateStr,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2" border="0"><bean:message key="prompt.3.calendar"/></a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
												<td class="formDeLabel" nowrap></td>
												<td class="formDe" colspan="3">
													<html:submit property="submitAction" onclick="return validateDateField(this.form)"><bean:message key="button.filter" /></html:submit>
													<html:submit property="submitAction" ><bean:message key="button.refresh"/></html:submit>
												</td>
											</tr>
										</table>
										<div class="spacer4px"></div>
									</logic:equal>						
<!--END FILTER BY DATE RANGE -->
									<input type="hidden" name="selectedInitialorReassess" value="" />	
									<input type="hidden" name="selectedSupervisionPlanId" value="" />
<!-- BEGIN INITIAL ASSESSMNETS BLOCK -->
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td><bean:message key="prompt.initialAssessments"/></td>
										</tr>
										<tr>
											<td>
<!-- BEGIN DISPLAY FOR WISCONSIN AND LSI-R DETAILS -->
												<table width="100%" cellpadding="2" cellspacing="1" id="initialAssess">
													<tr class="formDeLabel">
														<logic:equal name="assessmentForm" property="supervisionPeriod" value="<%=CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV%>">
															<td width="1%"></td>
														</logic:equal>												
														<td><bean:message key="prompt.assessment"/></td>
														<td nowrap width="20%"><bean:message key="prompt.status"/></td>
														<td nowrap width="20%"><bean:message key="prompt.assessment"/>&nbsp;<bean:message key="prompt.date"/></td>
														<td nowrap width="5%"><bean:message key="prompt.riskScore"/></td>
														<td nowrap width="5%"><bean:message key="prompt.needsScore"/></td>
													</tr>
													
													<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_VIEW%>'>	
													<!-- There might be more than 1 Initial Assessments shown in Inactive Supervion Period but there will be only 1 Initial Assessment in Active Supervision Period -->
														<logic:equal name="assessmentForm" property="initialAssessmentExist" value="<%=CSCAssessmentConstants.ASSESSMENT_EXIST%>">													
															<logic:iterate id="eachAssessmentIndex" name="assessmentForm" property="initialAssessmentsList" indexId="index11">
																<tr class="<%out.print((index11.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">														        
																	<logic:equal name="eachAssessmentIndex" property="assessmentTypeId" value="<%= PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_WISCONSIN %>">
																		<logic:equal name="assessmentForm" property="supervisionPeriod" value="<%= CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV %>">
																			<bean:define id="selectedId" type="java.lang.String" name="eachAssessmentIndex" property="assessmentBeanId" />
																			<bean:define id="initAssessStatusId" type="java.lang.Boolean" name="eachAssessmentIndex" property="statusComplete" />																
																			<td width="1%"><input type="radio" name="selectedInitialAssessmentBeanId" value="<%=selectedId%>" onclick="setInitialAssess('W',this.value,<%=initAssessStatusId %>);" /></td>																	
																		</logic:equal>
																		<td><a href='/<msp:webapp/>displayWisconsinDetails.do?submitAction=View+Details&selectedAssessmentBeanId=<bean:write name="eachAssessmentIndex" property="assessmentBeanId"/>&selectedAssessSupervisionPrd=<bean:write name="assessmentForm" property="supervisionPeriod"/>&selectedAssessmentType=<%=CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT%>'><bean:write name="eachAssessmentIndex" property="assessmentTypeName"/></a> </td>
																		<td><bean:write name="eachAssessmentIndex" property="statusDesc"/></td>
																		<td><bean:write name="eachAssessmentIndex" property="assessmentDate" formatKey="date.format.mmddyyyy"/><logic:equal name="eachAssessmentIndex" property="migrated" value="Y"><logic:notEmpty name="eachAssessmentIndex" property="actualAssessmentDate" ><a href="#" title="" class=tooltip230><img src="/<msp:webapp/>images/starGreenIcon.gif" border=0><span><div>Actual Assessment Date: <bean:write name="eachAssessmentIndex" property="actualAssessmentDate" formatKey="date.format.mmddyyyy"/></div></span></a></logic:notEmpty></logic:equal></td>
																		<td><bean:write name="eachAssessmentIndex" property="riskScore"/></td>
																		<td><bean:write name="eachAssessmentIndex" property="needsScore"/></td>															
																	</logic:equal>
																
																	<logic:equal name="eachAssessmentIndex" property="assessmentTypeId" value="<%= PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_LSIR %>">
																		<logic:equal name="assessmentForm" property="supervisionPeriod" value="<%= CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV %>">
																			<bean:define id="selectedId" type="java.lang.String" name="eachAssessmentIndex" property="assessmentBeanId" />
																			<bean:define id="initAssessStatusId" type="java.lang.Boolean" name="eachAssessmentIndex" property="statusComplete" />																		
																			<td width="1%"><input type="radio" name="selectedInitialAssessmentBeanId" value="<%=selectedId%>" onclick="setInitialAssess('L',this.value,<%=initAssessStatusId %>);" /></td>																	
																		</logic:equal>
																		<td> <a href='/<msp:webapp/>displayLSIRDetails.do?submitAction=View+Details&selectedAssessmentBeanId=<bean:write name="eachAssessmentIndex" property="assessmentBeanId"/>&selectedAssessSupervisionPrd=<bean:write name="assessmentForm" property="supervisionPeriod"/>&selectedAssessmentType=<%=CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT%>'><bean:write name="eachAssessmentIndex" property="assessmentTypeName"/></a> </td>
																		<td><bean:write name="eachAssessmentIndex" property="statusDesc"/></td>
																		<td><bean:write name="eachAssessmentIndex" property="assessmentDate" formatKey="date.format.mmddyyyy"/><logic:equal name="eachAssessmentIndex" property="migrated" value="Y"><logic:notEmpty name="eachAssessmentIndex" property="actualAssessmentDate" ><a href="#" title="" class=tooltip230><img src="/<msp:webapp/>images/starGreenIcon.gif" border=0><span><div>Actual Assessment Date: <bean:write name="eachAssessmentIndex" property="actualAssessmentDate" formatKey="date.format.mmddyyyy"/></div></span></a></logic:notEmpty></logic:equal></td>
																		<td><bean:write name="eachAssessmentIndex" property="riskScore"/></td>
																		<td><bean:write name="eachAssessmentIndex" property="needsScore"/></td>															
																	</logic:equal>
														  		</tr>
															</logic:iterate>
														</logic:equal>
														<logic:equal name="assessmentForm" property="initialAssessmentExist" value="<%=CSCAssessmentConstants.ASSESSMENT_NOT_EXIST%>">	
															<tr class="normalRow">
							  									<td colspan="5"></td>
						  									</tr>
														</logic:equal>						
<!-- END DISPLAY FOR WISCONSIN AND LSI-R DETAILS -->
<!-- BEGIN DISPLAY FOR ACTIVE SCS ASSESSMENTS DETAILS (only 1 (SCS Inventory and/or SCS Interview) for ACTIVE supervision period)  -->
														<logic:equal name="assessmentForm" property="supervisionPeriod" value="<%= CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV %>">
															<logic:equal name="assessmentForm" property="scsAssessmentExist" value="<%=CSCAssessmentConstants.ASSESSMENT_EXIST%>">
																<tr class="formDeLabel">
																	<logic:equal name="assessmentForm" property="supervisionPeriod" value="ACTIVE">
																		<td width="1%"></td>	
																	</logic:equal>
																	<td></td>
																	<td></td>
																	<td></td>
																	<td nowrap width="5%"><bean:message key="prompt.primary"/></td>
																	<td nowrap width="5%"><bean:message key="prompt.secondary"/></td>
																</tr>
																
																<logic:iterate id="eachSCSAsssessmentIndex" name="assessmentForm" property="scsAssessmentsList" indexId="index">
																	<logic:equal name="assessmentForm" property="scsAssessmentExist" value="<%=CSCAssessmentConstants.ASSESSMENT_EXIST %>">			
																		<logic:notEqual name="eachSCSAsssessmentIndex" property="assessmentTypeId" value="<%=CSCAssessmentConstants.ASSESSMENTTYPE_FORCEFIELD %>">												
																			<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																				<bean:define id="selectedId" type="java.lang.String" name="eachSCSAsssessmentIndex" property="assessmentBeanId" />
																				<bean:define id="initAssessStatusId" type="java.lang.Boolean" name="eachSCSAsssessmentIndex" property="statusComplete" />																	
																				<td width="1%"><input type="radio" name="selectedInitialAssessmentBeanId" value="<%=selectedId%>" onclick="setInitialAssess('<bean:write name="eachSCSAsssessmentIndex" property="assessmentTypeId"/>',this.value,<%=initAssessStatusId %>);"/></td>
																				<logic:equal name="eachSCSAsssessmentIndex" property="assessmentTypeId" value="<%=CSCAssessmentConstants.ASSESSMENTTYPE_SCS%>">
																					<td> <a href='/<msp:webapp/>displaySCSDetails.do?submitAction=View+Details&selectedAssessmentBeanId=<bean:write name="eachSCSAsssessmentIndex" property="assessmentBeanId"/>&selectedAssessSupervisionPrd=<bean:write name="assessmentForm" property="supervisionPeriod"/>'><bean:write name="eachSCSAsssessmentIndex" property="assessmentTypeName"/></a> </td>
																				</logic:equal>
																				<logic:equal name="eachSCSAsssessmentIndex" property="assessmentTypeId" value="<%=CSCAssessmentConstants.ASSESSMENTTYPE_SCS_INTERVIEW%>">
																					<td> <a href='/<msp:webapp/>displaySCSInterviewDetails.do?submitAction=View+Details&selectedAssessmentBeanId=<bean:write name="eachSCSAsssessmentIndex" property="assessmentBeanId"/>&selectedAssessSupervisionPrd=<bean:write name="assessmentForm" property="supervisionPeriod"/>'><bean:write name="eachSCSAsssessmentIndex" property="assessmentTypeName"/></a> </td>
																				</logic:equal>
																				<td><bean:write name="eachSCSAsssessmentIndex" property="statusDesc"/></td>
																				<td><bean:write name="eachSCSAsssessmentIndex" property="assessmentDate" formatKey="date.format.mmddyyyy"/><logic:equal name="eachSCSAsssessmentIndex" property="migrated" value="Y"><logic:notEmpty name="eachSCSAsssessmentIndex" property="actualAssessmentDate" ><a href="#" title="" class=tooltip230><img src="/<msp:webapp/>images/starGreenIcon.gif" border=0><span><div>Actual Assessment Date: <bean:write name="eachSCSAsssessmentIndex" property="actualAssessmentDate" formatKey="date.format.mmddyyyy"/></div></span></a></logic:notEmpty></logic:equal></td>
																				<td><bean:write name="eachSCSAsssessmentIndex" property="primaryClassificationStr"/></td>
																				<td><bean:write name="eachSCSAsssessmentIndex" property="secondaryClassificationStr"/></td>
																			</tr>
																		</logic:notEqual>
																		<logic:equal name="eachSCSAsssessmentIndex" property="assessmentTypeId" value="<%=CSCAssessmentConstants.ASSESSMENTTYPE_FORCEFIELD %>">
																			<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																				<logic:equal name="eachSCSAsssessmentIndex" property="forceFieldAssessmentStatus" value="<%= CSCAssessmentConstants.ASSESSMENT_EXIST %>">
																					<bean:define id="selectedId" type="java.lang.String" name="eachSCSAsssessmentIndex" property="assessmentBeanId" />
																					<bean:define id="initFAssessStatusId" type="java.lang.Boolean" name="eachSCSAsssessmentIndex" property="forceFieldStatusComplete" />
																					<td width="1%"><input type="radio" name="selectedInitialAssessmentBeanId" value="<%=selectedId%>" onclick="setInitialAssess('F',this.value,<%=initFAssessStatusId %>);"/></td>															
																					<td><a href='/<msp:webapp/>displayForceFieldDetails.do?submitAction=View+Details&selectedAssessmentBeanId=<bean:write name="eachSCSAsssessmentIndex" property="assessmentBeanId"/>&selectedAssessSupervisionPrd=<bean:write name="assessmentForm" property="supervisionPeriod"/>'><bean:write name="eachSCSAsssessmentIndex" property="forceFieldAssessmentTypeName"/></a> </td>
																				</logic:equal>
																				<logic:equal name="eachSCSAsssessmentIndex" property="forceFieldAssessmentStatus" value="<%= CSCAssessmentConstants.ASSESSMENT_NOT_EXIST %>">
																					<td width="1%">&nbsp;</td>		
																					<td><a class="editLink" href='/<msp:webapp/>displayForceFieldCreateUpdate.do?submitAction=Create&assessmentDateStr=<bean:write name="eachSCSAsssessmentIndex" property="assessmentDateStr"/>'><bean:write name="eachSCSAsssessmentIndex" property="forceFieldAssessmentTypeName"/></a></td>
																				</logic:equal>
																				<td><bean:write name="eachSCSAsssessmentIndex" property="forceFieldStatusDesc"/></td>
																				<td><bean:write name="eachSCSAsssessmentIndex" property="assessmentDate" formatKey="date.format.mmddyyyy"/><logic:equal name="eachSCSAsssessmentIndex" property="migrated" value="Y"><logic:notEmpty name="eachSCSAsssessmentIndex" property="actualAssessmentDate" ><a href="#" title="" class=tooltip230><img src="/<msp:webapp/>images/starGreenIcon.gif" border=0><span><div>Actual Assessment Date: <bean:write name="eachSCSAsssessmentIndex" property="actualAssessmentDate" formatKey="date.format.mmddyyyy"/></div></span></a></logic:notEmpty></logic:equal></td>
																				<td>&nbsp;</td>
																				<td>&nbsp;</td>
																			</tr>
																		</logic:equal>
																	</logic:equal>
																</logic:iterate>
															</logic:equal>
														</logic:equal>		
<!-- END DISPLAY FOR ACTIVE SCS ASSESSMENTS DETAILS -->
<!-- BEGIN DISPLAY FOR INACTIVE SCS ASSESSMENTS DETAILS (more than 1 for INACTIVE supervision period)  -->
														<logic:equal name="assessmentForm" property="supervisionPeriod" value="<%= CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_INACTV %>">
															<logic:equal name="assessmentForm" property="scsAssessmentExist" value="<%=CSCAssessmentConstants.ASSESSMENT_EXIST%>">
																<tr class="formDeLabel">
																	<logic:equal name="assessmentForm" property="supervisionPeriod" value="ACTIVE">
																		<td width="1%"></td>	
																	</logic:equal>
																	<td></td>
																	<td></td>
																	<td nowrap width="5%"><bean:message key="prompt.primary"/></td>
																	<td nowrap width="5%"><bean:message key="prompt.secondary"/></td>
																</tr>
																<logic:iterate id="eachSCSAsssessmentIndex" name="assessmentForm" property="scsAssessmentsList" indexId="index">															
																	<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																		<logic:equal name="assessmentForm" property="supervisionPeriod" value="<%= CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV %>">
																			<bean:define id="selectedId" type="java.lang.String" name="eachSCSAsssessmentIndex" property="assessmentBeanId" />
																			<td width="1%"><input type="radio" name="selectedInitialAssessmentBeanId" value="<%=selectedId%>" onclick="setInitialAssess('S',this.value);"/></td>																	
																		</logic:equal>	
																		<logic:equal name="eachSCSAsssessmentIndex" property="forceFieldAssessmentStatus" value="<%= CSCAssessmentConstants.ASSESSMENT_EXIST %>">															
																			<td> <a href='/<msp:webapp/>displaySCSDetails.do?submitAction=View+Details&selectedAssessmentBeanId=<bean:write name="eachSCSAsssessmentIndex" property="assessmentBeanId"/>&selectedAssessSupervisionPrd=<bean:write name="assessmentForm" property="supervisionPeriod"/>'><bean:write name="eachSCSAsssessmentIndex" property="assessmentTypeName"/></a> | <a href='/<msp:webapp/>displayForceFieldDetails.do?submitAction=View+Details&selectedAssessmentBeanId=<bean:write name="eachSCSAsssessmentIndex" property="assessmentBeanId"/>&selectedAssessSupervisionPrd=<bean:write name="assessmentForm" property="supervisionPeriod"/>'><bean:write name="eachSCSAsssessmentIndex" property="forceFieldAssessmentTypeName"/></a> </td>
																		</logic:equal>
																		<logic:equal name="eachSCSAsssessmentIndex" property="forceFieldAssessmentStatus" value="<%= CSCAssessmentConstants.ASSESSMENT_NOT_EXIST %>">															
																		    <logic:notEqual name="assessmentForm" property="supervisionPeriod" value="INACTIVE">
																			<td> <a href='/<msp:webapp/>displaySCSDetails.do?submitAction=View+Details&selectedAssessmentBeanId=<bean:write name="eachSCSAsssessmentIndex" property="assessmentBeanId"/>&selectedAssessSupervisionPrd=<bean:write name="assessmentForm" property="supervisionPeriod"/>'><bean:write name="eachSCSAsssessmentIndex" property="assessmentTypeName"/></a> | <a class="editLink" href='/<msp:webapp/>displayForceFieldCreateUpdate.do?submitAction=Create&assessmentDateStr=<bean:write name="eachSCSAsssessmentIndex" property="assessmentDateStr"/>'><bean:write name="eachSCSAsssessmentIndex" property="forceFieldAssessmentTypeName"/></a></td>
																		    </logic:notEqual>
																		    <logic:equal name="assessmentForm" property="supervisionPeriod" value="INACTIVE">
																		    <td> <a href='/<msp:webapp/>displaySCSDetails.do?submitAction=View+Details&selectedAssessmentBeanId=<bean:write name="eachSCSAsssessmentIndex" property="assessmentBeanId"/>&selectedAssessSupervisionPrd=<bean:write name="assessmentForm" property="supervisionPeriod"/>'><bean:write name="eachSCSAsssessmentIndex" property="assessmentTypeName"/></a></td>
																		     </logic:equal>
																		</logic:equal>
																		<td><bean:write name="eachSCSAsssessmentIndex" property="statusDesc"/></td>
																		<td><bean:write name="eachSCSAsssessmentIndex" property="assessmentDate" formatKey="date.format.mmddyyyy"/><logic:equal name="eachSCSAsssessmentIndex" property="migrated" value="Y"><logic:notEmpty name="eachSCSAsssessmentIndex" property="actualAssessmentDate" ><a href="#" title="" class=tooltip230><img src="/<msp:webapp/>images/starGreenIcon.gif" border=0><span><div>Actual Assessment Date: <bean:write name="eachSCSAsssessmentIndex" property="actualAssessmentDate" formatKey="date.format.mmddyyyy"/></div></span></a></logic:notEmpty></logic:equal></td>
																		<td><bean:write name="eachSCSAsssessmentIndex" property="primaryClassificationStr"/></td>
																		<td><bean:write name="eachSCSAsssessmentIndex" property="secondaryClassificationStr"/></td>
																	</tr>
																</logic:iterate>
															</logic:equal>		
														</logic:equal>
<!-- END DISPLAY FOR INACTIVE SCS ASSESSMENTS DETAILS -->	
													</jims2:isAllowed>
												</table>												
											</td>
										</tr>
									</table>
<!-- BEGIN INITIAL ASSESSMNETS BLOCK -->									
									<div class="spacer"></div>
<!-- BEGIN INITIAL ASSESSMNETS BUTTONS -->									
									<logic:equal name="assessmentForm" property="supervisionPeriod" value="<%= CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV %>">	
										<logic:equal name="assessmentForm" property="initialAssessmentExist" value="<%=CSCAssessmentConstants.ASSESSMENT_NOT_EXIST%>">	
											<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_CREATE_INITIAL%>'>							
												<input type="button" value="<bean:message key="button.newWisconsin" />"  onclick="goNav('/<msp:webapp/>displayWisconsinCreateUpdate.do?submitAction=New+Wisconsin');"></input>
											</jims2:isAllowed>
										</logic:equal>	
										
										<logic:equal name="assessmentForm" property="scsInventoryExist" value="<%=CSCAssessmentConstants.ASSESSMENT_NOT_EXIST%>">							
											<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_CREATE_INITIAL%>'>	
												<input type="button" value="<bean:message key="button.newSCS" />"  onclick="goNav('/<msp:webapp/>displaySCSCreateUpdate.do?submitAction=New SCS Inventory')"></input>
											</jims2:isAllowed>
										</logic:equal>
										
										<logic:equal name="assessmentForm" property="scsInterviewExist" value="<%=CSCAssessmentConstants.ASSESSMENT_NOT_EXIST%>">							
											<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_CREATE_INITIAL%>'>	
												<input type="button" value="<bean:message key="button.newSCSInterview" />"  onclick="goNav('/<msp:webapp/>displaySCSInterviewCreateUpdate.do?submitAction=New SCS Interview')"></input>
											</jims2:isAllowed>	
										</logic:equal>
										
										<logic:equal name="assessmentForm" property="initialAssessmentExist" value="<%=CSCAssessmentConstants.ASSESSMENT_NOT_EXIST%>">								
											<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_CREATE_INITIAL%>'>	
												<input type="button" value="<bean:message key="button.enterLSIRScore" />"  onclick="javascript:goNav('/<msp:webapp/>displayLSIRCreateUpdate.do?submitAction=Enter+LSI-R+Score&lsirAssessmentType=INITIAL_ASSESSMENT')"></input>
											</jims2:isAllowed>	
										</logic:equal>
										
									<%-- 	<span id="initUpdateWithRestrcButton" class="hidden">	
											<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_UPDATE_WITH_RESTRC%>'>
												<input type="button" value="<bean:message key="button.correctInitial"/>" onclick="determineInitalAssessUpdate()"/> 
											</jims2:isAllowed>	
									 		</span> 
										
									 	<span id="initUpdateWithoutRestrcButton" class="hidden"> 
											<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_UPDATE_WITHOUT_RESTRC%>'>
												<input type="button" value="<bean:message key="button.correctInitial"/>" onclick="determineInitalAssessUpdate()"/> 
											</jims2:isAllowed>
									 	</span>  --%>
									 	<span id="correctInitialButton" class="hidden"> 
											<input type="button" name="asmtCorrectInitial" value="<bean:message key="button.correctInitial"/>" onclick="determineInitalAssessUpdate()" disabled="true" />
										</span>
									 	<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_UPDATE_WITH_RESTRC%>'>
									 		<script type="text/javascript">
									 			show("correctInitialButton",1,"inline");
									 		</script>	
										</jims2:isAllowed>
										<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_UPDATE_WITHOUT_RESTRC%>'>
									 		<script type="text/javascript">
									 			show("correctInitialButton",1,"inline");
									 		</script>	
									 		<input type="hidden" name="asmtUpdateNotRestrected" value="" />
										</jims2:isAllowed>
											
									</logic:equal>
<!-- END INITIAL ASSESSMNETS BUTTONS -->	
									<div class="spacer4px"></div>
<!-- BEGIN REASSESSMENT DISPLAY BLOCK -->
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td colspan="6"><bean:message key="prompt.reassessments"/></td>
										</tr>
										<tr>
											<td>
												<div style="height:170px; overflow:auto; ">
													<table width="100%" cellpadding="2" cellspacing="1" class="notFirstColumn" id="reassess">
														<tr class="formDeLabel">
															<logic:equal name="assessmentForm" property="supervisionPeriod" value="ACTIVE">
																<td width='1%'></td>
															</logic:equal>	
															<td><bean:message key="prompt.assessment"/>
																<jims2:sortResults beanName="assessmentForm" results="reassessmentsList" primaryPropSort="assessmentTypeName" primarySortType="STRING"  defaultSortOrder="ASC" sortId="2" levelDeep="3"/>
															</td>
															<td><bean:message key="prompt.status"/>
																<jims2:sortResults beanName="assessmentForm" results="reassessmentsList" primaryPropSort="statusDesc" primarySortType="STRING"  defaultSortOrder="ASC" sortId="3" levelDeep="3"/>
															</td>
															<td><bean:message key="prompt.dueDate"/>
																<jims2:sortResults beanName="assessmentForm" results="reassessmentsList" primaryPropSort="assessmentDueDate" primarySortType="DATE"  defaultSortOrder="DESC" sortId="4" levelDeep="3"/>
															</td>
															<td><bean:message key="prompt.assessment"/>&nbsp;<bean:message key="prompt.date"/>
		                                                       	<jims2:sortResults beanName="assessmentForm" results="reassessmentsList" primaryPropSort="assessmentDate" primarySortType="DATE"  defaultSortOrder="DESC" sortId="1" levelDeep="3"/>
		                                                       </td>
															<td><bean:message key="prompt.riskScore"/>
										                     	<jims2:sortResults beanName="assessmentForm" results="reassessmentsList" primaryPropSort="riskScore" primarySortType="DOUBLE"  defaultSortOrder="ASC" sortId="5" levelDeep="3"/>
		                                                    </td>
															<td><bean:message key="prompt.needsScore"/>
		                                	                   	<jims2:sortResults beanName="assessmentForm" results="reassessmentsList" primaryPropSort="needsScore" primarySortType="DOUBLE"  defaultSortOrder="ASC" sortId="6" levelDeep="3"/>
		                                					</td> 
														</tr>
<!-- BEGIN REASSESSMENT DETAIL DISPLAY -->					
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_VIEW%>'>
															<logic:equal name="assessmentForm" property="incompleteWisconsinInitialExist" value="false">
						                                	    <logic:iterate id="eachReassessmentIndex" name="assessmentForm" property="reassessmentsList" indexId="index2">
					      											<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																		<logic:equal name="eachReassessmentIndex" property="assessmentStatus" value="<%=CSCAssessmentConstants.ASSESSMENT_EXIST%>">					   
																		 	<!-- For Wisconsin Reassessment -->
																			<logic:equal name="eachReassessmentIndex" property="assessmentTypeId" value="<%= PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_WISCONSIN %>">
																				<logic:equal name="assessmentForm" property="supervisionPeriod" value="<%= CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV %>">
																					<bean:define id="selectedId" type="java.lang.String" name="eachReassessmentIndex" property="assessmentBeanId" />
																					<bean:define id="reAssessStatusId" type="java.lang.Boolean" name="eachReassessmentIndex" property="statusComplete" />
																					<td width="1%"><input type="radio" name="selectedRessessmentBeanId" value="<%=selectedId%>" onclick="setReAssess('W',this.value,<%=reAssessStatusId %>);checkButtonStatus('Update');" /></td>																	
																				</logic:equal>
																				<td><a href='/<msp:webapp/>displayWisconsinDetails.do?submitAction=View+Details&selectedAssessmentBeanId=<bean:write name="eachReassessmentIndex" property="assessmentBeanId"/>&selectedAssessSupervisionPrd=<bean:write name="assessmentForm" property="supervisionPeriod"/>&selectedAssessmentType=<%=CSCAssessmentConstants.ASSESSMENT_REASSESSMENT%>'><bean:write name="eachReassessmentIndex" property="assessmentTypeName"/></a> </td>
																				<td><bean:write name="eachReassessmentIndex" property="statusDesc"/></td>
																				<td><bean:write name="eachReassessmentIndex" property="assessmentDueDate" formatKey="date.format.mmddyyyy"/></td>
																				<td><bean:write name="eachReassessmentIndex" property="assessmentDate" formatKey="date.format.mmddyyyy"/></td>
																				<td><bean:write name="eachReassessmentIndex" property="riskScore"/></td>
																				<td><bean:write name="eachReassessmentIndex" property="needsScore"/></td>															
																			</logic:equal>
																			<!-- For LSI-R Reassessment -->
																			<logic:equal name="eachReassessmentIndex" property="assessmentTypeId" value="<%= PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_LSIR %>">
																				<logic:equal name="assessmentForm" property="supervisionPeriod" value="<%= CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV %>">
																					<bean:define id="selectedId" type="java.lang.String" name="eachReassessmentIndex" property="assessmentBeanId" />
																					<bean:define id="reAssessStatusId" type="java.lang.Boolean" name="eachReassessmentIndex" property="statusComplete" />
																					<td width="1%"><input type="radio" name="selectedRessessmentBeanId" value="<%=selectedId%>" onclick="setReAssess('L',this.value,<%=reAssessStatusId %>); checkButtonStatus('Update');" /></td>																	
																				</logic:equal>
																				<td><a href='/<msp:webapp/>displayLSIRDetails.do?submitAction=View+Details&selectedAssessmentBeanId=<bean:write name="eachReassessmentIndex" property="assessmentBeanId"/>&selectedAssessSupervisionPrd=<bean:write name="assessmentForm" property="supervisionPeriod"/>&selectedAssessmentType=<%=CSCAssessmentConstants.ASSESSMENT_REASSESSMENT%>'><bean:write name="eachReassessmentIndex" property="assessmentTypeName"/></a> </td>
																				<td><bean:write name="eachReassessmentIndex" property="statusDesc"/></td>
																				<td><bean:write name="eachReassessmentIndex" property="assessmentDueDate" formatKey="date.format.mmddyyyy"/></td>
																				<td><bean:write name="eachReassessmentIndex" property="assessmentDate" formatKey="date.format.mmddyyyy"/></td>
																				<td><bean:write name="eachReassessmentIndex" property="riskScore"/></td>
																				<td><bean:write name="eachReassessmentIndex" property="needsScore"/></td>															
																			</logic:equal>
																		</logic:equal>
																		<!-- For Wisconsin Rescheduled Assessment -->												    
																		<logic:equal name="eachReassessmentIndex" property="assessmentStatus" value="<%=CSCAssessmentConstants.ASSESSMENT_NOT_EXIST%>">
																			<logic:equal name="eachReassessmentIndex" property="assessmentTypeId" value="<%= PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_WISCONSIN %>">
																				<logic:equal name="assessmentForm" property="incompleteWisconsinReassessExist" value="false">																
																				 	<td width="1%" align="center"><input type="radio" name="selectedRessessmentBeanId" value="Process" onclick="setReAssess('W',this.value,false); checkButtonStatus('Process');" ></td>
																					<td><bean:write name="eachReassessmentIndex" property="assessmentTypeName"/></td>
																					<td>&nbsp;</td>
																					<td><bean:write name="eachReassessmentIndex" property="assessmentDueDate" formatKey="date.format.mmddyyyy"/></td>
																					<td></td>
																					<td></td>
																					<td></td>
																				</logic:equal>
																			</logic:equal>
																		</logic:equal>
																	</tr>
															     </logic:iterate>
														     </logic:equal>
														</jims2:isAllowed>
<!-- END REASSESSMENT DETAIL DISPLAY -->												     	
		 											</table>	
	 											</div>										
											</td>
										</tr>
									</table>
<!-- END REASSESSMENT DISPLAY BLOCK -->			
<!-- BEGIN REASSESSMENT BUTTON DISPLAY -->			
									<logic:equal name="assessmentForm" property="initialAssessmentExist" value="<%=CSCAssessmentConstants.ASSESSMENT_EXIST%>">
										<logic:equal name="assessmentForm" property="incompleteWisconsinInitialExist" value="false">	
											<logic:equal name="assessmentForm" property="supervisionPeriod" value="<%= CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV %>">									
												<div class="spacer">
													<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_CREATE_REASSESS%>'>
														<input type="button" value="<bean:message key="button.reassessLSIR" />"  onclick="javascript:goNav('/<msp:webapp/>displayLSIRCreateUpdate.do?submitAction=Enter+LSI-R+Score&lsirAssessmentType=REASSESSMENT')"></input>
													</jims2:isAllowed>	
											<%--		<span id="correctButton" class="hidden">
														<span id="reassessUpdateWithRestrcButton" class="hidden">	
															<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_UPDATE_WITH_RESTRC%>'>
																<input type="button" value="<bean:message key="button.correct"/>" onclick="determineReassessUpdateOrProcess()" />
															</jims2:isAllowed>	
														</span>
														
														<span id="reassessUpdateWithoutRestrcButton" class="hidden">	
															<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_UPDATE_WITHOUT_RESTRC%>'>
																<input type="button" value="<bean:message key="button.correct"/>" onclick="determineReassessUpdateOrProcess()" />
															</jims2:isAllowed>
														</span>
													</span>   --%>
													<span id="correctButton" class="hidden">
														<input type="button" name="reasmtCorrect" value="<bean:message key="button.correct"/>" onclick="determineReassessUpdateOrProcess()" disabled="true" />
													</span>
													<span id="processButton" class="hidden">
														<input type="button" name="reasmtProcess" value="<bean:message key="button.reassessWisconsin"/>" onclick="determineReassessUpdateOrProcess()"  disabled="true"/>
													</span>
													<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_UPDATE_WITH_RESTRC%>'>
														<script type="text/javascript">
															show("correctButton",1,"inline");
														</script>
													</jims2:isAllowed>
													<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_UPDATE_WITHOUT_RESTRC%>'>
														<script type="text/javascript">
															show("correctButton",1,"inline");
														</script>
														<input type="hidden" name="reasmtUpdateNotRestrected" value="" /> 
													</jims2:isAllowed>
												<%-- 	<span id="processButton" class="hidden">  --%>
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_CREATE_REASSESS%>'>
															<logic:equal name="assessmentForm" property="incompleteWisconsinReassessExist" value="false">	
																<script type="text/javascript">
																	show("processButton",1,"inline");
																</script>
															</logic:equal>	
														</jims2:isAllowed>	
												<%--	</span>  --%>
												</div>
											</logic:equal>
										</logic:equal>
									</logic:equal>
<!-- END REASSESSMENT BUTTON DISPLAY -->			
									<div class="spacer4px"></div>
<!-- BEGIN SUPERVIONS PLAN DETAIL TABLE -->
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td colspan="4"><bean:message key="prompt.supervision"/>&nbsp;<bean:message key="prompt.plans"/></td>
										</tr>
										<tr>
											<td>
												<div style="height:170px; overflow:auto; ">
													<table width="100%" cellpadding="2" cellspacing="1" class="notFirstColumn">
														<tr class="formDeLabel">
															<logic:equal name="assessmentForm" property="supervisionPeriod" value="<%= CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV %>">		
																<td width=""2%"></td>
															</logic:equal>
															<td><bean:message key="prompt.last"/> <bean:message key="prompt.changeDate"/>
																<jims2:sortResults beanName="assessmentForm" results="supervisionPlansList" primaryPropSort="lastChangeDate" primarySortType="DATE"  defaultSortOrder="ASC" sortId="1" levelDeep="3"/>
															</td>
															<td><bean:message key="prompt.last"/> <bean:message key="prompt.change"/> <bean:message key="prompt.user"/>
																<jims2:sortResults beanName="assessmentForm" results="supervisionPlansList" primaryPropSort="lastChangeUserName" primarySortType="STRING"  defaultSortOrder="ASC" sortId="2" levelDeep="3"/>
															</td>
															<td><bean:message key="prompt.status"/>
																<jims2:sortResults beanName="assessmentForm" results="supervisionPlansList" primaryPropSort="statusDesc" primarySortType="STRING"  defaultSortOrder="ASC" sortId="3" levelDeep="3"/>
															</td>														
														</tr>
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPPLAN_VIEW%>'>  
															<logic:iterate id="eachSupervisionPlanBeanIndex" name="assessmentForm" property="supervisionPlansList" indexId="index3">   
																	<tr class="<%out.print((index3.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																		<logic:equal name="assessmentForm" property="supervisionPeriod" value="<%= CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV %>">
																			<bean:define id="selectedId" type="java.lang.String" name="eachSupervisionPlanBeanIndex" property="supervisionPlanId" />
																			<td width="1%"><input type="radio" name="selectedSupervisionPlanName" value="<%=selectedId%>" onclick="onSupervisionPlanSelect(this.value,'<bean:write name="eachSupervisionPlanBeanIndex" property="statusCd" />')" /></td>																	
																		</logic:equal> 
																		<td><A href="/<msp:webapp/>handleSupervisionPlanDetails.do?submitAction=View&selectedSupervisionPlanId=<bean:write name='eachSupervisionPlanBeanIndex' property='supervisionPlanId' />"><bean:write name="eachSupervisionPlanBeanIndex" property="lastChangeDateStr"/></A></td>
																		<td><bean:write name="eachSupervisionPlanBeanIndex" property="lastChangeUserName" /></td>
																		<td><bean:write name="eachSupervisionPlanBeanIndex" property="statusDesc" /></td>
																	</tr>
															</logic:iterate>       
														</jims2:isAllowed>	                                     
													</table>
												</div>
											</td>
										</tr>
									</table>
<!-- END SUPERVISION PLAN DETAILS TABLE -->									
									<div class="spacer"></div>	
<!-- BEGIN SUPERVISION PLAN BUTTONS -->									
									<logic:equal name="assessmentForm" property="supervisionPeriod" value="<%= CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV %>" >								
										<logic:equal name="assessmentForm" property="draftSupPlanExists" value="false" >
											<logic:equal name="assessmentForm" property="activeSupPlanFound" value="true" >								
												<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPPLAN_CREATE%>'>  
													<input type="button"  value="<bean:message key="button.newSupervisionPlan"/>" onclick="javascript:goNav('/<msp:webapp/>displaySupervisionPlanUpdate.do?submitAction=New+Supervision+Plan')" />
												</jims2:isAllowed>
											</logic:equal>		
										</logic:equal>
										<logic:equal name="assessmentForm" property="draftSupPlanExists" value="true" >
											<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPPLAN_UPDATE%>'> 
												<input type="button" value="<bean:message key="button.update"/>" onclick="onUpdateSupervisionPlan();" />
											</jims2:isAllowed>	
										</logic:equal>	
										<logic:equal name="assessmentForm" property="draftSupPlanExists" value="false" >								
											<logic:equal name="assessmentForm" property="actvInactvSupPlanExists" value="true" >	
												<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPPLAN_COPY%>'>  
													<input type="button" value="<bean:message key="button.copy"/>" onclick="onCopySupervisionPlan();" />
												</jims2:isAllowed>	
											</logic:equal>	
										</logic:equal>	
									</logic:equal>	
<!-- END SUPERVISION PLAN BUTTONS -->		
									<div class="spacer4px"></div>
<!-- BEGIN FULL PAGE BUTTON TABLE -->
									<table border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td align="center">
												<logic:notPresent name="confirmMessage">
													<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
												</logic:notPresent>
												<logic:equal name="assessmentForm" property="hasHistoricalAssessments" value="true">
													<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_VIEW_PRV_SUP_PRD%>'>	
														<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"> <bean:message key="button.viewHistoricalAssessments" /></html:submit>																																																				
													</jims2:isAllowed>	
												</logic:equal>	
												<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
											</td>
										</tr>
									</table>
<!-- END FULL PAGE BUTTON TABLE -->									
									</td>
								</tr>
							</table>
<!-- END GREEN BORDER TABLE -->		
							<div class="spacer4px"></div>
						</td>
					</tr>
				</table>
<!-- END BLUE BORDER TABLE -->
				<br>
			</td>
		</tr>
	</table>
<!-- END  MAIN TABLE -->
<br>
</div>
<br>

</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>