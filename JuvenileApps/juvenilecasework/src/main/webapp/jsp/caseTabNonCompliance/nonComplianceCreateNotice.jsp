<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 07/21/2011     C Shimek	Create JSP Activity #70949--%>
<%-- 10/17/2011     C Shimek    removed Parent Informed select, now on Completion page --%>
<%-- 11/18/2011     C Shimek    #71697 revised checkbox id values and validation scripting to correct validation error that allowed display of summary page with missing requried data. --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%@ page import="naming.Features" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading"/> - nonComplianceCreateNotice.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type="text/javaScript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/nonComplianceCreateNotice.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/casework.js"></script>

<script type="text/javaScript">

function displayViolationInfo(el)
{
	var mList = new Array();
	if (el.value == "MINOR")
	{
		show("minorVOPs", 1);
		show("modsVOPs", 0);
	} else {
		show("minorVOPs", 0);
		show("modsVOPs", 1);
	}
	show("SanctionLevels", 1);
	
	var sels = document.getElementsByName("selectedMinorTechnicalIds");
	for (x=0; x<sels.length; x++)
	{
		sels[x].checked = false;
	}	
	var sels = document.getElementsByName("selectedModSevereTechnicalIds");
	for (x=0; x<sels.length; x++)
	{
		sels[x].checked = false;
	}	
	document.forms[0].sanctionLevelId.selectedIndex = 0;
//	dd[0].selectedIndex = 0;
	show("sanctionSelectHeader",0);	
	show("minorMinSanctions",0);
	show("minorMedSanctions",0);
	show("minorMaxSanctions",0);
	show("minorIntSanctions",0); 
	show("modMinSanctions",0);
	show("modMedSanctions",0);
	show("modsMaxSanctions",0);
	show("modsIntSanctions",0);	
}

function displaySanctions(el)
{
	var vpLevel = "MINOR";
	var volLevel = document.getElementsByName("violationLevelId");
	if (volLevel[1].checked == true)
	{
		vpLevel = "MODSEV";
	}		

	var cboxes = document.getElementsByName("selectedMinorSanctionIds");
	for (x=0; x<cboxes.length; x++)
	{
		cboxes[x].checked = false;
	}
	var cboxes = document.getElementsByName("selectedModSevereSanctionIds");
	for (x=0; x<cboxes.length; x++)
	{
		cboxes[x].checked = false;
	}
	var allTxts = document.getElementsByTagName('textarea');
	for (y=0; y<allTxts.length; y++)
	{
		allTxts[y].value = "";
	}	
	var allInputs = document.getElementsByTagName('input');
	for (z=0; z<allInputs.length; z++)
	{
		if (allInputs[z].type == "text" && allInputs[z].name.indexOf("Description") > 0) {
	
			allInputs[z].value = "";
		}
	}
	show("sanctionSelectHeader",0);		
	show("minorMinSanctions",0);
	show("minorMedSanctions",0);
	show("minorMaxSanctions",0);
	show("minorIntSanctions",0); 
	show("modMinSanctions",0);
	show("modMedSanctions",0);
	show("modsMaxSanctions",0);
	show("modsIntSanctions",0);
	
	if (el.selectedIndex > 0)
	{	
		
		show("sanctionSelectHeader",1);	
		var sancLevel = el.options[el.selectedIndex].value;
		if (vpLevel == "MINOR")
		{	
			switch (sancLevel)
			{
			case "MIN":
				show("minorMinSanctions",1);
				break;
			case "MED":
				show("minorMedSanctions",1);
				break;
			case "MAX":
				show("minorMaxSanctions",1);
				break;
			case "INT":
				show("minorIntSanctions",1);
				break;
			default:	
			}
		} else {				
			switch (sancLevel)
			{
			case "MIN":
				show("modMinSanctions",1);
				break;
			case "MED":
				show("modMedSanctions",1);
				break;
			case "MAX":
				show("modsMaxSanctions",1);
				break;
			case "INT":
				show("modsIntSanctions",1);
				break;
			default:	
			}			
		}			
	}	
}

function validateInput()
{
	var msg = "";
	var result = "";
	var theForm = document.forms[0];
	var dt = theForm.caseAssignmentDate.value + " 00:00";
	var caDateTime = new Date(dt);
// begin date validations	
	result = validateDate(theForm.nonComplianceDateStr.value, "Non-Compliance Date", true);
	if (result != "")
	{
		msg = result;
		theForm.nonComplianceDateStr.focus();
	} else {
		var dt = theForm.nonComplianceDateStr.value + " 00:00";
		var fldDateTime = new Date(dt);
		if (caDateTime > fldDateTime){
			msg = "Non-Compliance Date can not previous to Case Assignment Date " + theForm.caseAssignmentDate.value + ".\n";
			theForm.nonComplianceDateStr.focus();			
		}    	
	}
	result = validateDate(theForm.sanctionAssignedDateStr.value, "Sanction(s) Assigned Date", true);	
	if (result != "")
	{
		if (msg == ""){
			theForm.sanctionAssignedDateStr.focus();
		}	
		msg += result;
	}else {
		var dt = theForm.sanctionAssignedDateStr.value + " 00:00";
		var fldDateTime = new Date(dt);
		if (caDateTime > fldDateTime){
			if (msg == ""){
				theForm.sanctionAssignedDateStr.focus();
			}
			msg += "Sanction(s) Assigned Date can not previous to Case Assignment Date " + theForm.caseAssignmentDate.value + ".\n";
		}    	
	}
	result = validateDate(theForm.sanctionCompleteByDateStr.value, "Complete Sanction(s) By", false);		
	if (result != "")
	{
		if (msg == ""){
			theForm.sanctionCompleteByDateStr.focus();
		}	
		msg += result;
	}
	if (msg == "")
	{
		var nonCompDte = theForm.nonComplianceDateStr.value + " 23:59";
		var sanAssignDte = theForm.sanctionAssignedDateStr.value + " 23:59";
		var sanCompByDte = theForm.sanctionCompleteByDateStr.value + " 23:59";				
		var nonCompDateTime = new Date(nonCompDte);
		var sanAssignDateTime = new Date(sanAssignDte);
		var sanCompByDateTime = new Date(sanCompByDte);	
		var curDateTime = new Date();	
		if (nonCompDateTime > sanAssignDateTime) {
			msg = "Non-compliance Date must be same or previous to Sanctions Assigned Date.\n";
			theForm.nonComplianceDateStr.focus();				
		}
		if (sanAssignDateTime > sanCompByDateTime) {
			msg = "Sanctions Assigned Date must be same or previous to Complete Sanction By Date.\n";
			theForm.nonComplianceDateStr.focus();				
		}  
		if (curDateTime > sanCompByDateTime) {
			if (msg == "") 	{
				theForm.sanctionCompleteByDateStr.focus();	
			}		
			msg += "Complete Sanction By Date must be current or future date.\n";
		}    	
	}		
// end date validations	

// begin VOP validations 	
	var vopLevels = document.getElementsByName("violationLevelId");
	if (vopLevels[0].checked == false && vopLevels[1].checked == false)
	{
		if (msg == ""){
			vopLevels[0].focus();
		}	
		msg += "VOP selection is required.\n";
	}

	if (vopLevels[0].checked == true || vopLevels[1].checked == true)
	{
		var selTechs = document.getElementsByName("selectedMinorTechnicalIds");
		if ( vopLevels[1].checked == true)
		{
			var selTechs = document.getElementsByName("selectedModSevereTechnicalIds");
		}	
		var selMade = false;
		for (x=0; x< selTechs.length; x++)
		{
			if (selTechs[x].checked == true)
			{
				selMade = selTechs[x].checked;
				break;
			}						
		}
		if ( selMade == false)
		{
			if (msg == "") {
				selTechs[0].focus();
			}	
			msg += "Probation Violation selection is required.\n";
		}
// end VOP validations 		
// begin sanction validations 		
		if (theForm.sanctionLevelId.selectedIndex == 0)
		{
			if (msg == ""){
				selTechs[0].focus();
			}	
			msg += "Sanction Level selection is required.\n";
		} else {
			var selectedSanctionId = "selectedMinorSanctionIds";
			if (vopLevels[1].checked == true)  // Moderate To Severe
			{
				selectedSanctionId = "selectedModSevereSanctionIds";
			}
			var sancSel = document.getElementsByName(selectedSanctionId); 
			selMade = false;
			var fld1 = "";
			var fld2 = "";
			for (x=0; x<sancSel.length; x++)
			{
				if (sancSel[x].checked == true)
				{
					selMade = true;
					if ( sancSel[x].value.indexOf("OTH") > -1)
					{	
						var id2 = sancSel[x].value + "DESC";
						fld1 = document.getElementById(id2);
						fld2 = Trim(fld1.value);
						if (fld2 == "") {	
							if (msg == ""){
								fld1.focus();
							}
							msg += "Description required for 'Other Action' sanction.\n";
						}
						fld1.value = fld2;
						fld1 = document.getElementById(sancSel[x].value);
						fld2 = Trim(fld1.value);
						if (fld2 == "") {
							if (msg == ""){
								fld1.focus();
							}
							msg += "Commets required for 'Other Action' sanction.\n";
						}	
					}		
					fld1 = document.getElementById(sancSel[x].value);
					fld2 = Trim(fld1.value);
					result = validateComments(fld2);
					if (result != "") {
						if (msg == "") {
							fld1.focus();
						}
						msg += result;
					}	
				}			
			}
			if (selMade == false)
			{
				msg += "Sanction selection is required.\n";
			}		
		}			 
// end sanction validations 
	}
	if (msg == "")
	{
		return true;
	}
	alert(msg);
	return false;		
}

function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen + 1)
	{
  	alert("Your input has been truncated to "  +maxlen +" characters!");
	}

	if (field.value.length > maxlen)
	{
  	field.value = field.value.substring(0, maxlen);
	}
}

function Trim( s )
{
	// Remove leading spaces and carriage returns
	while( (s.substring( 0,1 ) == ' ') || (s.substring( 0,1 ) == '\n') || (s.substring( 0,1 ) == '\r') )
	{ 
		s = s.substring( 1, s.length ); 
	}

	// Remove trailing spaces and carriage returns
	while(( s.substring( s.length -1, s.length ) == ' ' ) || 
			(s.substring( s.length -1, s.length ) == '\n') ||
			(s.substring( s.length -1, s.length ) == '\r') )
	{ 
		s = s.substring( 0, s.length -1 ); 
	}

	return s;
}
function reloadPage()
{
	var vopLevels = document.getElementsByName("violationLevelId");
	if (vopLevels[0].checked == true || vopLevels[1].checked == true)
	{
		show("SanctionLevels", 1);
		var sancLevelId = document.getElementsByName("sanctionLevelId");
		sancLevel = sancLevelId[0].options[sancLevelId[0].selectedIndex].value;
		if (vopLevels[0].checked == true)
		{
			show("minorVOPs", 1);
			switch (sancLevel)
			{
			case "MIN":
				show("minorMinSanctions",1);
				break;
			case "MED":
				show("minorMedSanctions",1);
				break;
			case "MAX":
				show("minorMaxSanctions",1);
				break;
			case "INT":
				show("minorIntSanctions",1);
				break;
			default:
			}	
		} else {
			show("modsVOPs", 1);
			switch (sancLevel)
			{
			case "MIN":
				show("modMinSanctions",1);
				break;
			case "MED":
				show("modMedSanctions",1);
				break;
			case "MAX":
				show("modsMaxSanctions",1);
				break;
			case "INT":
				show("modsIntSanctions",1);
				break;
			default:	
			}	
		}			
	}			
}
function validateDate(fldValue, fldName, futureDateEdit)
{
	var msg = "";
	if (fldValue == "")
	{
		msg = fldName + " is required.\n";
		return msg;
	}
    if (futureDateEdit && msg == ""){
		var dt = fldValue + " 00:00";
		var fldDateTime = new Date(dt);
		var curDateTime = new Date();
		if (fldDateTime > curDateTime){
			msg = fldName + " can not be future date.\n";
			return msg;				
		}    	
    }
 	return msg;
}

function validateComments(fldValue)
{
	var msg = "";
// alphanumeric with specail characters only	
	var commentRegExp = /^[a-zA-Z0-9][a-zA-Z0-9 \.\\(),\n\r\x27\x3A\x3B\x26\x2f\-]*$/;

	if (fldValue.length > 0)
		if (fldValue.length < 6){
			msg = "Comments must be at least 6 characters.\n";
		} else {
			if (commentRegExp.test(fldValue,commentRegExp) == false) {
				msg = "Comments contains invalid character or begins with space.\n";
		}
	}
	return msg;
}	
</script>
</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body  topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="reloadPage();">
<html:form action="displayJuvenileCasefileNonComplianceNoticeSummary" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|242">
<input type="hidden" name="minorTechs" value="<bean:write name='juvenileNonComplianceForm' property='minorProbationViolationList' />" >
<input type="hidden" name="modSevereTechs" value="<bean:write name='juvenileNonComplianceForm' property='modSevereProbationViolationList' />" >
<input type="hidden" name="caseAssignmentDate" value="<bean:write name='juvenileNonComplianceForm' property='caseAssignmentDateStr' />" >
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Create Non-Compliance Notice</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN ERROR TABLE --%>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<%-- END ERROR TABLE --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION/REQUIRED INFORMATION TABLE --%>
<table width="98%" align="center">
	<tr>
		<td>
			<ul>
				<li>Enter data and click Next.</li>
			</ul>
		</td>
	</tr>
	<tr>
		 <td class="required"><bean:message key="prompt.2.diamond"/>&nbsp;Required Fields</td>
	</tr>
</table>
<%-- END INSTRUCTION/REQUIRED INFORMATION TABLE --%> 

<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>

<div class='spacer'></div>
<%-- BEGIN CASEFILE (BLUE) TABS TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign='top'>
			<tiles:insert page="/jsp/caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="casefiledetailstab"/>
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>				
<%-- BEGIN BLUE TABS BORDER TABLE --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign='top' align="center">
						<div class='spacer'></div>
<%-- BEGIN CASEFILE INFO (GREEN) TABS TABLE --%> 								
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign='top'>
									<tiles:insert page="/jsp/caseworkCommon/casefileInfoTabs.jsp" flush="true">
										<tiles:put name="tabid" value="casefiledetailstab"/>
										<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
									</tiles:insert>	
								</td>
							</tr>
							<tr>
								<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
							 </tr>
						</table>
<%-- END CASEFILE INFO TABS TABLE --%> 										
<%-- BEGIN GREEN TABS BORDER TABLE --%>
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td valign='top' align="center">
									<div class='spacer'></div>
<%-- BEGIN CASEFILE AUDIT (RED) TABS TABLE --%>                                                
									<table width='98%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign='top'>
												<tiles:insert page="/jsp/caseworkCommon/casefileAuditTabs.jsp" flush="true">
													<tiles:put name="tabid" value="NonComplianceNoticeDetails"/>
													<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
												</tiles:insert>
											</td>
										</tr>
										<tr>
											<td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
										</tr>
									</table>
<%-- END CASEFILE AUDIT TABS TABLE --%>  												
<%-- BEGIN RED TABS BORDER TABLE --%>													
									<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
										<tr>
											<td align="center">	
												<div class='spacer'></div>
<%-- BEGIN DATE AND VOP LEVEL INPUT TABLE --%>  												
												<table width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
													<tr> 
														<td class="formDeLabel"><bean:message key="prompt.diamond" /><bean:message key="prompt.nonComplianceDate" /></td>
														<td valign='top' class="formDe">
															<html:text  property="nonComplianceDateStr" size="10" maxlength="10" readonly="true" styleId="nonComplianceDateStrId"/>
															
														</td> 
													</tr>
													<tr> 
														<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.diamond" /><bean:message key="prompt.sanctionsAssignedDate" /></td>
														<td valign='top' class="formDe">
															<html:text  property="sanctionAssignedDateStr" size="10" maxlength="10" readonly="true" styleId="sanctionAssignedDateStrId"/>															
														</td> 
													</tr>
													<tr> 
														<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.diamond" /><bean:message key="prompt.completeSanctionsBy" /></td>
														<td valign='top' class="formDe">
															<html:text  property="sanctionCompleteByDateStr" size="10" maxlength="10" readonly="true" styleId="sanctionCompleteByDateStrId"/>															
														</td> 
													</tr>
			                  				<%-- 		<tr>   // moved to Completion page per request of Carla G. 10/14/2011
			                  							<td class="formDeLabel"><bean:message key="prompt.parentInformed" />?</td>
			                  							<td class="formDe">
							 								<bean:message key="prompt.yes" /><html:radio name="juvenileNonComplianceForm" property="parentalNotified" value="true"/>
															<bean:message key="prompt.no" /><html:radio name="juvenileNonComplianceForm" property="parentalNotified" value="false"/> 
														</td>
													</tr>   --%>
													<tr>
														<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.diamond" /><bean:message key="prompt.vopLevel" /></td>
														<td class='formDe'>
															<bean:message key="prompt.minor" /><input type="radio" name='violationLevelId' value="MINOR" onclick="displayViolationInfo(this)">
															<bean:message key="prompt.moderateToSevere" /><input type="radio" name='violationLevelId' value="MODSEV" onclick="displayViolationInfo(this)">
														</td>
													</tr>
												</table>
<%-- END DATE AND VOP LEVEL INPUT TABLE --%>  											
<%-- VARIABLES FOR DISPLAYING PROBATION VIOLATIONS --%>
		                  						<% int RecordCtr=0;
												   int bgCtr=0; 
												   int maxSize = 0;
												   String bgcolor=""; %>
												<span id="minorVOPs" class="hidden"> 
													<bean:size id="minorVOPsSize" name="juvenileNonComplianceForm" property="minorProbationViolationList" />	
													<% maxSize = minorVOPsSize.intValue(); %>
													<div class='spacer'></div>  
<%-- BEGIN BLUE BORDER FOR MINOR VIOLATION SELECTION TABLE --%>												   
													<table width="98%" cellpadding="0" cellspacing="0" class="borderTableBlue">
			                  							<tr>
			                  								<td align="center" colspan="2">		
<%-- BEGIN MINOR VIOLATION SELECTION TABLE --%>
						                  						<table width="100%" cellpadding="2" cellspacing="0" >
						                  							<tr>
						                  								<td colspan="6" class="detailHead"><bean:message key="prompt.diamond" /><bean:message key="prompt.probationViolation" /> (one or more selection is required)</td>
						                  							</tr>
																	<logic:iterate id="minorIter" name="juvenileNonComplianceForm" property="minorProbationViolationList" >
																		<% RecordCtr++; 
																		   if (RecordCtr % 3 == 1 ) { 
																	  	 		bgCtr++;
																		   		bgcolor = "alternateRow";                      
																		   		if (bgCtr % 2 == 1){
																				   bgcolor = "normalRow";
																				}  %>	  
																				<tr class=<%= bgcolor %> >
																					<td valign="top" width="1%">
																						<input type="checkbox" name="selectedMinorTechnicalIds" value="<bean:write name="minorIter" property="code" />" >
																					</td>
																					<td valign="top" width="32%">	
																						<bean:write name="minorIter" property="description" />
																					</td>
																		<% } 	
																			if(RecordCtr % 3 == 2 ) { %>
																				<td valign="top" width="1%">
																					<input type="checkbox" name="selectedMinorTechnicalIds" value="<bean:write name="minorIter" property="code" />" >
																				</td>
																				<td valign="top" width="32%">	
																					<bean:write name="minorIter" property="description" />
																				</td>
																		<% }	
																			if(RecordCtr % 3 == 0 ) { %>
																				<td valign="top" width="1%">
																					<input type="checkbox" name="selectedMinorTechnicalIds" value="<bean:write name="minorIter" property="code" />" >
																				</td>
																				<td valign="top" width="33%">	
																					<bean:write name="minorIter" property="description" />
																				</td>
																			</tr>
																		<% }	
																			if (RecordCtr == maxSize) {
																 				if(RecordCtr % 3 == 1 ) { %>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																			<%  }	
																				if(RecordCtr % 3 == 2 ) { %>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																			<% }
																				 if (RecordCtr % 3 != 0) { %>	
																				</tr>
																			<% 	 }
																			 } %>	 
																	</logic:iterate>
						                  						</table>
<%-- END MINOR VIOLATION SELECTION TABLE --%>         
															</td>
														</tr>
													</table>
<%-- END BLUE BORDER FOR MINOR VIOLATION SELECTION TABLE --%>																	         									
												</span>
												<% RecordCtr=0;
												   bgCtr=0; 
												   bgcolor=""; %>
			                  					<span id="modsVOPs" class="hidden">
			                  						<bean:size id="modsVOPsSize" name="juvenileNonComplianceForm" property="modSevereProbationViolationList" />	
													<% maxSize = modsVOPsSize.intValue(); %>
			                  						<div class='spacer'></div>
<%-- BEGIN BLUE BORDER FOR MODERATE/SEVERE VIOLATION SELECTION TABLE --%>												   
													<table width="98%" cellpadding="0" cellspacing="0" class="borderTableBlue">
			                  							<tr>
			                  								<td align="center" colspan="2">		
<%-- BEGIN MODERATE/SEVERE VIOLATION SELECTION TABLE --%>
						                  						<table width="100%" cellpadding="2" cellspacing="0">
						                  							<tr>
						                  								<td colspan="6" class="detailHead"><bean:message key="prompt.diamond" /><bean:message key="prompt.probationViolation" /> (one or more selection is required)</td>
						                  							</tr>
																	<logic:iterate id="modsIter" name="juvenileNonComplianceForm" property="modSevereProbationViolationList">
																		<%  RecordCtr++; 
																	  	  	if(RecordCtr % 3 == 1 ) { 
																	  	  		bgCtr++;
																		   		bgcolor = "alternateRow";                      
																		  		if (bgCtr % 2 == 1){
																				   bgcolor = "normalRow";
																				}  %>	  
																			<tr class=<%= bgcolor %> >
																				<td valign="top" width="1%">
																					<input type="checkbox" name="selectedModSevereTechnicalIds" value="<bean:write name="modsIter" property="code" />" >
																				</td>
																				<td valign="top" width="32%">	
																					<bean:write name="modsIter" property="description" />
																				</td>
																		<%  } 
																			if(RecordCtr % 3 == 2 ) { %>
																				<td valign="top" width="1%">
																					<input type="checkbox" name="selectedModSevereTechnicalIds" value="<bean:write name="modsIter" property="code" />" >
																				</td>
																				<td valign="top" width="32%">	
																					<bean:write name="modsIter" property="description" />
																				</td>
																		<%  } 
																		    if(RecordCtr % 3 == 0 ) { %>
																				<td valign="top" width="1%">
																					<input type="checkbox" name="selectedModSevereTechnicalIds" value="<bean:write name="modsIter" property="code" />" >
																				</td>
																				<td valign="top" width="33%">	
																					<bean:write name="modsIter" property="description" />
																				</td>
																			</tr>
																		<%  } 
																		    if (RecordCtr == maxSize) {
																 				if(RecordCtr % 3 == 1 ) { %>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																			<%  }	
																				if(RecordCtr % 3 == 2 ) { %>
																					<td>&nbsp;</td>
																					<td>&nbsp;</td>
																			<% }
																				 if (RecordCtr % 3 != 0) { %>	
																				</tr>
																			<% 	 }
																			 } %>	  
																	</logic:iterate>
																</table>
<%-- BEGIN MODERATE/SEVERE VIOLATION SELECTION TABLE --%>  
															</td>
														</tr>
													</table>		                												
<%-- END BLUE BORDER TABLE FOR VOP SELECT --%>
			                  					</span>
       											<span id="SanctionLevels" class="hidden" >
       												<div class='spacer'></div>
<%-- BEGIN BLUE BORDER TABLE FOR SCANTION DATA ENTRY --%>         												
	       											<table width="98%" cellpadding="0" cellspacing="0" class="borderTableBlue">
			                  							<tr>
			                  								<td align="center" colspan="2">	
<%-- BEGIN SCANTION LEVEL SELECT TABLE --%>         											
																<table width="100%">
																	<tr>
																		<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.diamond" /><bean:message key="prompt.sanction" /> <bean:message key="prompt.level" /></td>
																		<td class="formDe">
																			<html:select property="sanctionLevelId" onchange="displaySanctions(this)">
																				<html:option value=""><bean:message key="select.generic" /></html:option>
																				<html:optionsCollection property="sanctionLevelTypes" value="code" label="description" />
																			</html:select>  
																		</td>
																	</tr>
																</table>	
<%-- END SCANTION LEVEL SELECT TABLE --%>  	
															</td>
														</tr>
														<tr>
															<td align="center" colspan="2">
<%-- BEGIN SCANTION SELECT HEADER TABLE --%> 																									
																	<table width="100%" cellpadding="2" cellspacing="2" id="sanctionSelectHeader" class="hidden"> 
																		<tr class="detailHead">
		            	      												<td colspan="2"><bean:message key="prompt.diamond" /><bean:message key="prompt.sanction" /> (one or more selection is required)</td>
		                	  											</tr>
		                  											</table>
		                  										
<%-- END SCANTION SELECT HEADER TABLE --%> 												 
																<% int commentIndex = 0; 
																   String item = "";
																   String spellbtn = ""; %>
<%-- BEGIN MINOR-MINIMUM SCANTION SELECT TABLE --%>  
																<div>
			                  									<table width="98%" cellpadding="2" cellspacing="0" id="minorMinSanctions" class="hidden"> 			
			                  										<logic:iterate id="minorMin" name="juvenileNonComplianceForm" property="minorSanctionLevelMinList" indexId="index1">
				                  										<% item = "minorMinComments[" + index1.intValue() + "]"; 
				                  										   commentIndex++;
				                  										   spellbtn = "spellBtn" + commentIndex;	
				                  										%>
				                  										<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
								                  							<td width="1%" valign="top"><input type="checkbox" name="selectedMinorSanctionIds" value="<bean:write name="minorMin" property="sanctionId" />"></td>
				                  											<logic:notEqual name="minorMin" property="sanctionId" value="OTH1">
								                  								<td valign="top"><ul><li><bean:write name="minorMin" property="sanctionDesc" /></li></ul></td>
								                  							</logic:notEqual>
				                  											<logic:equal name="minorMin" property="sanctionId" value="OTH1">
																				<td><bean:message key="prompt.other" /> <bean:message key="prompt.action" />  (Max. characters allowed: 125)</td>
																			</logic:equal>
																		</tr>
																		<logic:equal name="minorMin" property="sanctionId" value="OTH1">
																			<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
																				<td width="1%"></td>
																				<td valign="top">	
																					<html:text name="juvenileNonComplianceForm" property="minorMinOtherDescription" styleId="OTH1DESC" size="120" maxlength="120" />
																				</td>
																			</tr>
							                  							</logic:equal>
							                  							<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">	
																			<td>&nbsp;</td>	
																			<td class='FormDeLabel'>
																				<logic:equal name="minorMin" property="sanctionId" value="OTH1">
																					<bean:message key="prompt.diamond" />
																				</logic:equal>
																				<bean:message key="prompt.comments"/>
																				<tiles:insert page="/jsp/caseworkCommon/spellCheckTile.jsp" flush="false">
								            	          					 	 	<tiles:put name="tTextField" value="<%=item%>"/>
								                      								<tiles:put name="tSpellCount" value="<%=spellbtn%>" />    
								                    							</tiles:insert>
																				(Max. characters allowed: 1000)
																			</td>
																		</tr>
																		<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
							                  								<td>&nbsp;</td>	
							                  								<td class='FormDeLabel'>
							                  									<textarea name="<%=item%>" style="width:100%" rows="5" id="<bean:write name="minorMin" property="sanctionId" />" onmouseout="textLimit(this, 1000);" onkeydown="textLimit(this, 1000)"></textarea>
							                  								</td>
							                  							</tr> 
								                  					</logic:iterate>
								                  				</table>
								                  				
<%-- END MINOR-MINIMUM SCANTION SELECT TABLE --%>                    										
<%-- BEGIN MINOR-MEDIUM SCANTION SELECT TABLE --%>                   								
					                  							<table width="98%" cellpadding="2" cellspacing="0" id="minorMedSanctions" class="hidden">
						                 							<logic:iterate id="minorMed" name="juvenileNonComplianceForm" property="minorSanctionLevelMedList" indexId="index2">
				                  										<% item = "minorMedComments[" + index2.intValue() + "]"; 
				                  										   commentIndex++;
				                  										   spellbtn = "spellBtn" + commentIndex;	
			                  											%>
				                  										<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
								                  							<td width="1%" valign="top"><input type="checkbox" name="selectedMinorSanctionIds" value="<bean:write name="minorMed" property="sanctionId" />"></td>
				                  											<logic:notEqual name="minorMed" property="sanctionId" value="OTH2">
								                  								<td valign="top"><ul><li><bean:write name="minorMed" property="sanctionDesc" /></li></ul></td>
								                  							</logic:notEqual>
				                  											<logic:equal name="minorMed" property="sanctionId" value="OTH2">
																				<td><bean:message key="prompt.other" /> <bean:message key="prompt.action" />  (Max. characters allowed: 125)</td>
																			</logic:equal>
																		</tr>
																		<logic:equal name="minorMed" property="sanctionId" value="OTH2">
																			<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
																				<td width="1%"></td>
																				<td valign="top">	
																					<html:text name="juvenileNonComplianceForm" property="minorMedOtherDescription" styleId="OTH2DESC" size="120" maxlength="120" />
																				</td>
																			</tr>
							                  							</logic:equal>
							                  							<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">	
																			<td>&nbsp;</td>	
																			<td class='FormDeLabel'>
																				<logic:equal name="minorMed" property="sanctionId" value="OTH2">
																					<bean:message key="prompt.diamond" />
																				</logic:equal>
																				<bean:message key="prompt.comments"/>
																				<tiles:insert page="/jsp/caseworkCommon/spellCheckTile.jsp" flush="false">
								            	          					 	 	<tiles:put name="tTextField" value="<%=item%>"/>
								                      								<tiles:put name="tSpellCount" value="<%=spellbtn%>" />    
								                    							</tiles:insert>
																				(Max. characters allowed: 1000)
																			</td>
																		</tr>
																		<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
							                  								<td>&nbsp;</td>	
							                  								<td class='FormDeLabel'>
							                  									<textarea name="<%=item%>" style="width:100%" rows="5" id="<bean:write name="minorMed" property="sanctionId" />" onmouseout="textLimit(this, 1000);" onkeydown="textLimit(this, 1000)"></textarea>
							                  								</td>
							                  							</tr> 
						                  							</logic:iterate>
							                  					</table>
<%-- END MINOR-MEDIUM SCANTION SELECT TABLE --%> 			                  							
<%-- BEGIN MINOR-MAXIMUM SCANTION SELECT TABLE --%> 		
																<table width="98%" cellpadding="2" cellspacing="0" id="minorMaxSanctions" class="hidden">
			                  					 					<logic:iterate id="minorMax" name="juvenileNonComplianceForm" property="minorSanctionLevelMaxList" indexId="index3">
				                  										<% item = "minorMaxComments[" + index3.intValue() + "]"; 
					                  									   commentIndex++;
					                  									   spellbtn = "spellBtn" + commentIndex;	
					                  									%>
					                  									<tr class="<%out.print((index3.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
								                  							<td width="1%" valign="top"><input type="checkbox" name="selectedMinorSanctionIds" value="<bean:write name="minorMax" property="sanctionId" />"></td>
				                  											<logic:notEqual name="minorMax" property="sanctionId" value="OTH3">
								                  								<td valign="top"><ul><li><bean:write name="minorMax" property="sanctionDesc" /></li></ul></td>
								                  							</logic:notEqual>
				                  											<logic:equal name="minorMax" property="sanctionId" value="OTH3">
																				<td><bean:message key="prompt.other" /> <bean:message key="prompt.action" />  (Max. characters allowed: 125)</td>
																			</logic:equal>
																		</tr>
																		<logic:equal name="minorMax" property="sanctionId" value="OTH3">
																			<tr class="<%out.print((index3.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
																				<td width="1%"></td>
																				<td valign="top">	
																					<html:text name="juvenileNonComplianceForm" property="minorMaxOtherDescription" styleId="OTH3DESC" size="120" maxlength="120" />
																				</td>
																			</tr>
							                  							</logic:equal>
							                  							<tr class="<%out.print((index3.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">	
																			<td>&nbsp;</td>	
																			<td class='FormDeLabel'>
																				<logic:equal name="minorMax" property="sanctionId" value="OTH3">
																					<bean:message key="prompt.diamond" />
																				</logic:equal>
																				<bean:message key="prompt.comments"/>
																				<tiles:insert page="/jsp/caseworkCommon/spellCheckTile.jsp" flush="false">
								            	          					 	 	<tiles:put name="tTextField" value="<%=item%>"/>
								                      								<tiles:put name="tSpellCount" value="<%=spellbtn%>" />    
								                    							</tiles:insert>
																				(Max. characters allowed: 1000)
																			</td>
																		</tr>
																		<tr class="<%out.print((index3.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
							                  								<td>&nbsp;</td>	
							                  								<td class='FormDeLabel'>
							                  									<textarea name="<%=item%>" style="width:100%" rows="5" id="<bean:write name="minorMax" property="sanctionId" />" onmouseout="textLimit(this, 1000);" onkeydown="textLimit(this, 1000)"></textarea>
							                  								</td>
							                  							</tr> 
			                 										</logic:iterate>
			                  									</table>	
<%-- END MINOR-MAXIMUM SCANTION SELECT TABLE --%> 	                  									
<%-- BEGIN MINOR-INTENSE SCANTION SELECT TABLE  --%> 												
																<table width="98%" cellpadding="2" cellspacing="0" id="minorIntSanctions" class="hidden">
																	<logic:iterate id="minorInt" name="juvenileNonComplianceForm" property="minorSanctionLevelIntesiveList" indexId="index4">
													             		<% item = "minorIntComments[" + index4.intValue() + "]"; 
					                  									   commentIndex++;
					                  									   spellbtn = "spellBtn" + commentIndex;	
					                  									%>
					                  									<tr class="<%out.print((index4.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
								                  							<td width="1%" valign="top"><input type="checkbox" name="selectedMinorSanctionIds" value="<bean:write name="minorInt" property="sanctionId" />"></td>
				                  											<logic:notEqual name="minorInt" property="sanctionId" value="OTH4">
								                  								<td valign="top"><ul><li><bean:write name="minorInt" property="sanctionDesc" /></li></ul></td>
								                  							</logic:notEqual>
				                  											<logic:equal name="minorInt" property="sanctionId" value="OTH4">
																				<td><bean:message key="prompt.other" /> <bean:message key="prompt.action" />  (Max. characters allowed: 125)</td>
																			</logic:equal>
																		</tr>
																		<logic:equal name="minorInt" property="sanctionId" value="OTH4">
																			<tr class="<%out.print((index4.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
																				<td width="1%"></td>
																				<td valign="top">	
																					<html:text name="juvenileNonComplianceForm" property="minorIntOtherDescription" styleId="OTH4DESC" size="120" maxlength="120" />
																				</td>
																			</tr>
							                  							</logic:equal>
							                  							<tr class="<%out.print((index4.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">	
																			<td>&nbsp;</td>	
																			<td class='FormDeLabel'>
																				<logic:equal name="minorInt" property="sanctionId" value="OTH4">
																					<bean:message key="prompt.diamond" />
																				</logic:equal>
																				<bean:message key="prompt.comments"/>
																				<tiles:insert page="/jsp/caseworkCommon/spellCheckTile.jsp" flush="false">
								            	          					 	 	<tiles:put name="tTextField" value="<%=item%>"/>
								                      								<tiles:put name="tSpellCount" value="<%=spellbtn%>" />    
								                    							</tiles:insert>
																				(Max. characters allowed: 1000) 
																			</td>
																		</tr>
																		<tr class="<%out.print((index4.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
							                  								<td>&nbsp;</td>	
							                  								<td class='FormDeLabel'>
							                  									<textarea name="<%=item%>" style="width:100%" rows="5" id="<bean:write name="minorInt" property="sanctionId" />" onmouseout="textLimit(this, 1000);" onkeydown="textLimit(this, 1000)"></textarea>
							                  								</td>
							                  							</tr> 
																	</logic:iterate>
			                  									</table>
<%-- END MINOR-INTENSE SCANTION SELECT TABLE --%> 	                  									
<%-- BEGIN MODERATE/SEVERE-MINIMUM SANCTION SELECT TABLE   --%> 													
																<table width="98%" cellpadding="2" cellspacing="0" id="modMinSanctions" class="hidden">	
																	<logic:iterate id="modSevereMin" name="juvenileNonComplianceForm" property="modSevereSanctionLevelMinList" indexId="index5">
													             		<% item = "modSevereMinComments[" + index5.intValue() + "]"; 
					                  									   commentIndex++;
					                  									   spellbtn = "spellBtn" + commentIndex;	
					                  									%>
					                  									<tr class="<%out.print((index5.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
								                  							<td width="1%" valign="top"><input type="checkbox" name="selectedModSevereSanctionIds" value="<bean:write name="modSevereMin" property="sanctionId" />"></td>
				                  											<logic:notEqual name="modSevereMin" property="sanctionId" value="OTH5">
								                  								<td valign="top"><ul><li><bean:write name="modSevereMin" property="sanctionDesc" /></li></ul></td>
								                  							</logic:notEqual>
				                  											<logic:equal name="modSevereMin" property="sanctionId" value="OTH5">
																				<td><bean:message key="prompt.other" /> <bean:message key="prompt.action" />  (Max. characters allowed: 125)</td>
																			</logic:equal>
																		</tr>
																		<logic:equal name="modSevereMin" property="sanctionId" value="OTH5">
																			<tr class="<%out.print((index5.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
																				<td width="1%"></td>
																				<td valign="top">	
																					<html:text name="juvenileNonComplianceForm" property="modSevereMinOtherDescription" styleId="OTH5DESC" size="120" maxlength="120" />
																				</td>
																			</tr>
							                  							</logic:equal>
							                  							<tr class="<%out.print((index5.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">	
																			<td>&nbsp;</td>	
																			<td class='FormDeLabel'>
																				<logic:equal name="modSevereMin" property="sanctionId" value="OTH5">
																					<bean:message key="prompt.diamond" />
																				</logic:equal>
																				<bean:message key="prompt.comments"/>
																				<tiles:insert page="/jsp/caseworkCommon/spellCheckTile.jsp" flush="false">
								            	          					 	 	<tiles:put name="tTextField" value="<%=item%>"/>
								                      								<tiles:put name="tSpellCount" value="<%=spellbtn%>" />    
								                    							</tiles:insert>
																				(Max. characters allowed: 1000)
																			</td>
																		</tr>
																		<tr class="<%out.print((index5.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
							                  								<td>&nbsp;</td>	
							                  								<td class='FormDeLabel'>
							                  									<textarea name="<%=item%>" style="width:100%" rows="5" id="<bean:write name="modSevereMin" property="sanctionId" />" onmouseout="textLimit(this, 1000);" onkeydown="textLimit(this, 1000)"></textarea>
							                  								</td>
							                  							</tr>
																	</logic:iterate>
																</table>
<%-- END MODERATE/SEVERE-MINIMUM SANCTION SELECT TABLE --%> 														
<%-- BEGIN MODERATE/SEVERE-MEDIUM SANCTION SELECT TABLE  --%>                											
				   												<table width="98%" cellpadding="2" cellspacing="0" id="modMedSanctions" class="hidden">	
               				  										<logic:iterate id="modSevereMed" name="juvenileNonComplianceForm" property="modSevereSanctionLevelMedList" indexId="index6">
													             		<% item = "modSevereMedComments[" + index6.intValue() + "]"; 
					                  									   commentIndex++;
					                  									   spellbtn = "spellBtn" + commentIndex;	
					                  									%>
				                  										<tr class="<%out.print((index6.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
								                  							<td width="1%" valign="top"><input type="checkbox" name="selectedModSevereSanctionIds" value="<bean:write name="modSevereMed" property="sanctionId" />"></td>
				                  											<logic:notEqual name="modSevereMed" property="sanctionId" value="OTH6">
								                  								<td valign="top"><ul><li><bean:write name="modSevereMed" property="sanctionDesc" /></li></ul></td>
								                  							</logic:notEqual>
				                  											<logic:equal name="modSevereMed" property="sanctionId" value="OTH6">
																				<td><bean:message key="prompt.other" /> <bean:message key="prompt.action" />  (Max. characters allowed: 125)</td>
																			</logic:equal>
																		</tr>
																		<logic:equal name="modSevereMed" property="sanctionId" value="OTH6">
																			<tr class="<%out.print((index6.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
																				<td width="1%"></td>
																				<td valign="top">	
																					<html:text name="juvenileNonComplianceForm" property="modSevereMedOtherDescription" styleId="OTH6DESC" size="120" maxlength="120" />
																				</td>
																			</tr>
							                  							</logic:equal>
							                  							<tr class="<%out.print((index6.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">	
																			<td>&nbsp;</td>	
																			<td class='FormDeLabel'>
																				<logic:equal name="modSevereMed" property="sanctionId" value="OTH6">
																					<bean:message key="prompt.diamond" />
																				</logic:equal>
																				<bean:message key="prompt.comments"/>
																				<tiles:insert page="/jsp/caseworkCommon/spellCheckTile.jsp" flush="false">
								            	          					 	 	<tiles:put name="tTextField" value="<%=item%>"/>
								                      								<tiles:put name="tSpellCount" value="<%=spellbtn%>" />    
								                    							</tiles:insert>
																				(Max. characters allowed: 1000)
																			</td>
																		</tr>
																		<tr class="<%out.print((index6.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
							                  								<td>&nbsp;</td>	
							                  								<td class='FormDeLabel'>
							                  									<textarea name="<%=item%>" style="width:100%" rows="5" id="<bean:write name="modSevereMed" property="sanctionId" />" onmouseout="textLimit(this, 1000);" onkeydown="textLimit(this, 1000)"></textarea>
							                  								</td>
							                  							</tr> 
			                  										</logic:iterate>
			                 									</table>
<%-- END MODERATE/SEVERE-MEDIUM SANCTION SELECT TABLE --%>  			                  									
<%-- BEGIN MODERATE/SEVERE-MAXIMUM SANCTION SELECT TABLE  --%>                 									
																<table width="98%" cellpadding="2" cellspacing="0" id="modsMaxSanctions" class="hidden">	
			                 					 					<logic:iterate id="modSevereMax" name="juvenileNonComplianceForm" property="modSevereSanctionLevelMaxList" indexId="index7">
													             		<% item = "modSevereMaxComments[" + index7.intValue() + "]"; 
					                  									   commentIndex++;
					                  									   spellbtn = "spellBtn" + commentIndex;	
					                  									%>
				                  										<tr class="<%out.print((index7.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
								                  							<td width="1%" valign="top"><input type="checkbox" name="selectedModSevereSanctionIds" value="<bean:write name="modSevereMax" property="sanctionId" />"></td>
				                  											<logic:notEqual name="modSevereMax" property="sanctionId" value="OTH7">
								                  								<td valign="top"><ul><li><bean:write name="modSevereMax" property="sanctionDesc" /></li></ul></td>
								                  							</logic:notEqual>
				                  											<logic:equal name="modSevereMax" property="sanctionId" value="OTH7">
																				<td><bean:message key="prompt.other" /> <bean:message key="prompt.action" />  (Max. characters allowed: 125)</td>
																			</logic:equal>
																		</tr>
																		<logic:equal name="modSevereMax" property="sanctionId" value="OTH7">
																			<tr class="<%out.print((index7.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
																				<td width="1%"></td>
																				<td valign="top">	
																					<html:text name="juvenileNonComplianceForm" property="modSevereMaxOtherDescription" styleId="OTH7DESC" size="120" maxlength="120" />
																				</td>
																			</tr>
							                  							</logic:equal>
							                  							<tr class="<%out.print((index7.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">	
																			<td>&nbsp;</td>	
																			<td class='FormDeLabel'>
																				<logic:equal name="modSevereMax" property="sanctionId" value="OTH7">
																					<bean:message key="prompt.diamond" />
																				</logic:equal>
																				<bean:message key="prompt.comments"/>
																				<tiles:insert page="/jsp/caseworkCommon/spellCheckTile.jsp" flush="false">
								            	          					 	 	<tiles:put name="tTextField" value="<%=item%>"/>
								                      								<tiles:put name="tSpellCount" value="<%=spellbtn%>" />    
								                    							</tiles:insert>
																				(Max. characters allowed: 1000)
																			</td>
																		</tr>
																		<tr class="<%out.print((index7.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
							                  								<td>&nbsp;</td>	
							                  								<td class='FormDeLabel'>
							                  									<textarea name="<%=item%>" style="width:100%" rows="5" id="<bean:write name="modSevereMax" property="sanctionId" />" onmouseout="textLimit(this, 1000);" onkeydown="textLimit(this, 1000)"></textarea>
							                  								</td>
							                  							</tr> 
			                 										</logic:iterate>
			                									</table>
<%-- END MODERATE/SEVERE-MAXIMUM SANCTION SELECT TABLE --%>                  									
<%-- END MODERATE/SEVERE-INTENSE SANCTION SELECT TABLE  --%> 												
																<table width="98%" cellpadding="2" cellspacing="0" id="modsIntSanctions" class="hidden">	
																	<logic:iterate id="modSevereInt" name="juvenileNonComplianceForm" property="modSevereSanctionLevelIntesiveList" indexId="index8">
													             		<% item = "modSevereIntComments[" + index8.intValue() + "]"; 
					                  									   commentIndex++;
					                  									   spellbtn = "spellBtn" + commentIndex;	
					                  									%>
				                  										<tr class="<%out.print((index8.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
								                  							<td width="1%" valign="top"><input type="checkbox" name="selectedModSevereSanctionIds" value="<bean:write name="modSevereInt" property="sanctionId" />"></td>
				                  											<logic:notEqual name="modSevereInt" property="sanctionId" value="OTH8">
								                  								<td valign="top"><ul><li><bean:write name="modSevereInt" property="sanctionDesc" /></li></ul></td>
								                  							</logic:notEqual>
				                  											<logic:equal name="modSevereInt" property="sanctionId" value="OTH8">
																				<td><bean:message key="prompt.other" /> <bean:message key="prompt.action" />  (Max. characters allowed: 125)</td>
																			</logic:equal>
																		</tr>
																		<logic:equal name="modSevereInt" property="sanctionId" value="OTH8">
																			<tr class="<%out.print((index8.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
																				<td width="1%"></td>
																				<td valign="top">	
																					<html:text name="juvenileNonComplianceForm" property="modSevereIntOtherDescription" styleId="OTH8DESC" size="120" maxlength="120" />
																				</td>
																			</tr>
							                  							</logic:equal>
							                  							<tr class="<%out.print((index8.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">	
																			<td>&nbsp;</td>	
																			<td class='FormDeLabel'>
																				<logic:equal name="modSevereInt" property="sanctionId" value="OTH8">
																					<bean:message key="prompt.diamond" />
																				</logic:equal>
																				<bean:message key="prompt.comments"/>
																				<tiles:insert page="/jsp/caseworkCommon/spellCheckTile.jsp" flush="false">
								            	          					 	 	<tiles:put name="tTextField" value="<%=item%>"/>
								                      								<tiles:put name="tSpellCount" value="<%=spellbtn%>" />    
								                    							</tiles:insert>
																				(Max. characters allowed: 1000)
																			</td>
																		</tr>
																		<tr class="<%out.print((index8.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
							                  								<td>&nbsp;</td>	
							                  								<td class='FormDeLabel'>
																				<textarea name="<%=item%>" style="width:100%" rows="5" id="<bean:write name="modSevereInt" property="sanctionId" />" onmouseout="textLimit(this, 1000);" onkeydown="textLimit(this, 1000)"></textarea>
							                  								</td>
							                  							</tr> 
																	</logic:iterate>
																</table>
<%-- END MODERATE/SEVERE-INTENSE SANCTION SELECT TABLE --%>  													
															</td>
														</tr>
													</table>
<%-- END BLUE BORDER TABLE FOR SCANTION DATA ENTRY --%> 											
												</span>
												<div class='spacer'></div>
											</td>
										</tr>
<%-- END RED BORDER TABLE --%> 										
										<tr>
 											<td>
			                  					<div class='spacer'></div>          
<%-- BEGIN BUTTON TABLE --%>
												<table border="0" width="100%">
													<tr>
														<td align="center">
															<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
															<html:submit property="submitAction" onclick="return validateInput()"> <bean:message key="button.next" /></html:submit>
															<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
														</td>
													</tr>
												</table>
<%-- END BUTTON TABLE --%>
											</td>
										</tr>
									</table>
<%-- END RED TABS BORDER TABLE --%>												
									<div class='spacer'></div>
								</td>
							</tr>
						</table>
<%-- END GREEN TABS BORDER TABLE --%>								
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
<%-- END CASEFILE INFO TABS TABLE --%>						
	    </td>
	</tr>
</table>
<%-- BEGIN CASEFILE TABS TABLE --%>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>