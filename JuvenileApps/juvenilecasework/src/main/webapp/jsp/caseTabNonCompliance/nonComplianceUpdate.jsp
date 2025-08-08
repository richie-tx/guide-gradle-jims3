<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 07/21/2011     C Shimek       	Create JSP --%>

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
<title><bean:message key="title.heading"/> - nonComplianceUpdate.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type="text/javaScript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/nonComplianceCreateNotice.js"></script>

<script type="text/javascript" >

$(function(){
	
	if (document.getElementsByName("pgAction")[0].value == "update")
	{	
		var pi = document.getElementsByName("pgInd")[0].value;
		if (pi == "Sign")
		{
			document.getElementsByName("signatureSignedDateStr")[0].focus();
		} else {

			document.getElementById("radioYes").focus();
			var comStat = document.getElementsByName("juvenileCompletedStatus");
			if (comStat[1].checked != comStat[2].checked) // if values are equal, yes is selected
			{
				$("#actionTaken").show();
				var actTaken = document.forms[0].actionTakenId.options[document.forms[0].actionTakenId.selectedIndex].value;
				if (actTaken == "OTH")
				{	
					$("#actionTakenCommenthdg").show();	
					$("#actionTakenCommentText").show();
				}
	
			}					
		}	
	
	}
	
	$("#radioYes").on("click", function(){
		
		$("#actionTakenCommenthdg").hide();	
		$("#actionTakenCommentText").hide();
		document.getElementById("atSelect").selectedIndex=0;
		$("#actionTaken").hide();
		$("#actionTakenCommentText").val("");
		$("#completionDate").show();		
	});
		
	$("#radioNo, #radioPar").on("click", function(){
		$("#completionDate").hide();
		$("#completionDateId").val("");
		var idx = document.getElementById("atSelect").selectedIndex;	
		if (idx > 0 )
		{
			var actTaken = $("#atSelect").val();		
			if (actTaken == "OTH")
			{			
				$("#actionTakenCommenthdg").show();	
				$("#actionTakenCommentText").show();
			}	
		}	
		$("#actionTaken").show();
	});
	
	$("#atSelect").on("change", function(){
		
		var actTaken = $("#atSelect").val();
		if (actTaken == "OTH")
		{
			$("#actionTakenCommenthdg").show();
			$("#actionTakenCommentText").show();
			$("#atComments").val("");
		}
		else
		{
			$("#actionTakenCommenthdg").hide();
			$("#actionTakenCommentText").hide();			
		}	
	});
});



function validateSignatureInput()
{
	msg = "";
	var aDate = document.getElementsByName("assignedDateStr");
	var sDate = document.getElementsByName("signatureSignedDateStr");
//	if (sDate[0].value > "")
//	{	
	var inDate = new Date(sDate[0].value);
	var compDate = new Date(aDate[0].value);
//		msg = validateDate(sDate[0].value, "Signed Date", true);
	var dt = sDate[0].value + " 00:00";
	var fldDateTime = new Date(dt);
	var curDateTime = new Date();
	if (fldDateTime > curDateTime)
	{
		msg = "Signed Date can not be future date.\n";
		sDate[0].focus();
	} else { 
		if (inDate < compDate == true)
		{
			msg = "Signed Date can not be previous to Sanction(s) Assigned Date " + aDate[0].value + ".";
			sDate[0].focus();
		}		
	}		
//	}	 
	var ss = document.getElementsByName("signatureStatus");
	if (ss[0].checked == ss[1].checked)
	{
		if (msg == "")
		{
			ss[0].focus();
		}		
		msg += "Signature seletion is required."
	}		
	if (msg == "") {
		return true;
	}
	alert(msg);
	return false;
}


function validateCompletionInput()
{
	msg = "";
	var fldValue = "";
	var commentRegExp = /^[a-zA-Z0-9][a-zA-Z0-9 \.\\(),\n\r\x27\x3A\x3B\x26\x2f\-]*$/;
	var jcs = document.getElementsByName("juvenileCompletedStatus");
	if (jcs[0].checked == jcs[1].checked && jcs[1].checked == jcs[2].checked )
	{
		msg = "Juvenile Completed selection is required.\n";
	}
	if (jcs[0].checked == true)
	{
		var cDate = document.getElementsByName("completionDateStr");
		var result = validateDate(cDate[0].value, "Date Completed", true);
		if (cDate[0].value == "")
		{
			if (msg == "")
			{
				cDate[0].focus();
			} 
			msg = "Date Completed is required.\n";
		} 
		if (cDate[0].value != "")
		{	
			var dt = cDate[0].value + " 00:00";
			var fldDateTime = new Date(dt);
			var curDateTime = new Date();
			if (fldDateTime > curDateTime)
			{
				if (msg == "")
				{
					cDate[0].focus();
				}				
				msg = "Date Completed can not be future date.\n";
			} else {    	
				var aDate = document.getElementsByName("assignedDateStr");
				var inDate = new Date(cDate[0].value);
				var compDate = new Date(aDate[0].value);
				var rx = inDate < compDate;
				if (inDate < compDate)
				{
					if (msg == "")
					{
						cDate[0].focus();
					}	
					msg += "Completion Date can not be previous to Sanction(s) Assigned Date " + aDate[0].value + ".";
				} 	
			}			 
		}
	}	
	if (jcs[1].checked == true ||  jcs[2].checked == true)
	{
		var ati = document.getElementsByName("actionTakenId");
		if (ati[0].selectedIndex == 0)
		{
			if (msg == ""){
				ati[0].focus();
			}
			msg += "Action Taken selection is required if Completed selection is ";
			if (jcs[1].checked == true)
			{
				msg += "No.\n";
			} else {
				msg += "Partial.\n";
			}
		} else {
			if (ati[0].options[ati[0].selectedIndex].value == "OTH")
			{
				var atcoms = document.getElementsByName("otherActionTakenComment");
				fldValue = Trim(atcoms[0].value);
				atcoms[0].value = fldValue;
				if (fldValue.length == 0) {
					if (msg == ""){
						atcoms[0].focus();
					}
					msg += "Action Taken Comments required if Action Taken selection is Other.\n";
				} else {
					if (commentRegExp.test(fldValue,commentRegExp) == false) {
						if (msg == ""){
							atcoms[0].focus();
						}
						msg += "Action Taken Comments contains invalid character(s).\n";
					}	
				}	
			}		
		}		
	}		
	var coms = document.getElementsByName("juvenileCompletedComments");
	fldValue = Trim(coms[0].value);
	coms[0].value = fldValue;
	if (jcs[1].checked == true || jcs[2].checked == true)  //No or Partial
	{
		if (fldValue.length == 0) {
			if (msg == ""){
				coms[0].focus();
			}
			msg += "Completion Comments required if Juvenile Completed selection is ";
			if (jcs[1].checked == true)
			{
				msg += "No.";
			} else {
				msg += "Partial.";
			}			
		}	 
	}				
	if (fldValue.length > 0)
		if (fldValue.length < 6){
			if (msg == ""){
				coms[0].focus();
			}	
			msg += "Completion Comments must be at least 6 characters.";
		} else {
			if (commentRegExp.test(fldValue,commentRegExp) == false) {
				if (msg == ""){
					coms[0].focus();
				}
				msg += "Completion Comments contains invalid character(s).\n";
		}
	}
	if (msg == "") {
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
function validateDate(fldValue, fldName, futureDateEdit)
{
	var msg = "";
	var numericRegExp = /^[0-9]*$/;
	if (fldValue == "")
	{
		msg = fldName + " is required.\n";
		return msg;
	}
	if (fldValue.length < 10 || fldValue.indexOf("/") < 2)
	{
		msg = fldName + " must be in the MM/DD/YYYY format.\n";
		return msg;
	}
	var dValues = fldValue.split('/');
	var month = dValues[0];
	var day = dValues[1];
	var year = dValues[2];

	if (numericRegExp.test(month,numericRegExp) == false || numericRegExp.test(day,numericRegExp) == false || numericRegExp.test(year,numericRegExp) == false ) { 
		msg = fldName + " is not a valid date.\n";
		return msg;	
	} 

	if (month.length != 2 || day.length != 2 || year.length != 4) {
		msg = fldName + " must be in the MM/DD/YYYY format.\n";
		return msg;	
	} 

    if (month < 1 || month > 12)
    {
		msg = fldName + " is not valid.\n";
		return msg;		
    }
    if (day < 1 || day > 31) {
		msg = fldName + " is not valid.\n";
		return msg;		
    }
    if ((month == 4 || month == 6 || month == 9 || month == 11) && (day == 31))
    {
		msg = fldName + " is not valid.\n";
		return msg;	
    }
    if (month == 2) {
        var leap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
        if (day > 29 || (day == 29 && !leap)) {
			msg = fldName + " is not valid.\n";
			return msg;	
        }
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



</script>

</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" >
<html:form action="submitJuvenileCasefileNonComplianceNoticeUpdate" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|243">
<input type="hidden" name="pgInd" value="<bean:write name="juvenileNonComplianceForm" property="updateFlow" />" >
<input type="hidden" name="pgAction" value="<bean:write name="juvenileNonComplianceForm" property="action" />" >
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Notice <bean:message key="prompt.details"/></td>
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
<%-- BEGIN CONFIRMATION TABLE --%>
<table width="98%" align="center">
	<tr>
		<td Class="confirm">
			<bean:write name="juvenileNonComplianceForm" property="confirmationMsg" />
		</td>
	</tr>
</table>
<%-- END CONFIRMATION TABLE --%> 
<%-- BEGIN INSTRUCTION/REQUIRED INFORMATION TABLE --%>
<table width="98%" align="center">
	<tr>
		<td>
			<logic:equal name="juvenileNonComplianceForm" property="action" value="confirm">
				<ul>
			<%-- 	<li>Click Print Notice button to print Non-Compliance Notice.</li>  --%>	
					<li>Click Notice List button to return to Non-Compliance list..</li>
				</ul>
			</logic:equal>	
		</td>		
	</tr>
	<logic:equal name="juvenileNonComplianceForm" property="action" value="update">
		<tr>
			 <td class="required"><bean:message key="prompt.2.diamond"/>&nbsp;Required Fields</td>
		</tr>
	</logic:equal>	
</table>
<%-- END INSTRUCTION/REQUIRED INFORMATION TABLE --%> 

<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>

<div class='spacer'></div>
<%-- BEGIN CASEFILE TABS TABLE --%>
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
<%-- BEGIN CASEFILE INFO TABS TABLE --%>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" >
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
<%-- BEGIN GREEN TABS BORDER TABLE --%>
									<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
										<tr>
											<td valign='top' align="center">
                                                <div class=spacer></div>
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
<%-- BEGIN RED TABS BORDER TABLE --%>													
												<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
													<tr>
														<td align="center">	
<%-- BEGIN SANCTIONS DETAILS TABLE --%>
 															<tiles:insert page="/jsp/caseTabNonCompliance/nonComplianceDetailsTile.jsp" flush="true">
															</tiles:insert>
<%-- END SANCTIONS DETAILS TABLE --%>
															<input type="hidden" name="assignedDateStr" value="<bean:write name='juvenileNonComplianceForm' property='sanctionAssignedDate' formatKey="date.format.mmddyyyy" />"  >
			                  								<div class='spacer'></div>
															<logic:equal name="juvenileNonComplianceForm" property="updateFlow" value="Sign">
																<logic:equal name="juvenileNonComplianceForm" property="action" value="update"> 
<%-- BEGIN SIGNATURE STATUS INPUT TABLE --%>															
																	<table width="99%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">	
																		<tr>
																			<td class="detailHead"><bean:message key="prompt.juvenile" /> <bean:message key="prompt.signature" /> <bean:message key="prompt.status" /></td>
																		</tr>	
																		<tr>
																			<td>
																				<table width="100%"  cellspacing="1" cellpadding="1" >
																					<tr>
																						<td class="formDeLabel"><bean:message key="prompt.signedDate" /></td>
																						<td class="formDe">
																							<html:text  property="signatureSignedDateStr" size="10" maxlength="10" readonly="true" styleId="signatureSignedDateStrId"/>
																							
																						</td>
																					</tr>
																					<tr>
																						<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.diamond" /><bean:message key="prompt.juvenile" />'s <bean:message key="prompt.signatureStatus" /></td>
																						<td class="formDe">
																							<input type="radio" name="signatureStatus" value="SIG" checked="checked"><bean:message key="prompt.signed"/>
																							<input type="radio" name="signatureStatus" value="REF"><bean:message key="prompt.refusedToSign"/>
																						</td>
																					</tr>
																					<tr> 
																						<td class="formDeLabel"><bean:message key="prompt.parentInformed" />?</td>
																						<td class="formDe">
																							<bean:message key="prompt.yes" /><html:radio name="juvenileNonComplianceForm" property="parentalNotified" value="true"/>
																							<bean:message key="prompt.no" /><html:radio name="juvenileNonComplianceForm" property="parentalNotified" value="false"/> 
																						</td>
																					</tr> 
																					<tr>
																						<td colspan="2" align="center">
																							<html:submit property="submitAction" onclick="return validateSignatureInput()"> <bean:message key="button.submitSignatureStatus" /></html:submit>
																						</td>
																					</tr>
																				</table>
																			</td>
																		</tr>																						 
																	</table>			
<%-- END SIGNATURE STATUS UPDATE TABLE --%>	
																</logic:equal>
																<logic:equal name="juvenileNonComplianceForm" property="action" value="confirm">
<%-- BEGIN SIGNATURE STATUS CONFIRM TABLE --%>	
																	<table border="0" width="100%" cellspacing="2" cellpadding="1">
																		<tr>
																			<td colspan="2" class="formDeLabel"><bean:message key="prompt.juvenile" /> <bean:message key="prompt.signature" /> <bean:message key="prompt.status" /></td>
																		</tr>	
																		<tr>
																			<td class="formDeLabel"><bean:message key="prompt.signedDate" /></td>
																			<td class="formDe"><bean:write name="juvenileNonComplianceForm" property="signatureSignedDate" formatKey="date.format.mmddyyyy"/></td>
																		</tr>
																		<tr>
																			<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.juvenile" />'s <bean:message key="prompt.signatureStatus" /></td>
																			<td class="formDe"><bean:write name="juvenileNonComplianceForm" property="signatureStatusLiteral" /></td> 
																		</tr>
																		<tr>
																			<td class="formDeLabel"><bean:message key="prompt.parentInformed" />?</td>
																			<td class="formDe">
																				<logic:equal name="juvenileNonComplianceForm" property="parentalNotified" value='true'>
																					<bean:message key="prompt.yes" />
																				</logic:equal>
																				<logic:equal name="juvenileNonComplianceForm" property="parentalNotified" value='false'>
																					<bean:message key="prompt.no" />
																				</logic:equal>
																			</td>
																		</tr>	
																	</table>
<%-- END SIGNATURE STATUS CONFIRM TABLE --%>																		
																</logic:equal> 
															</logic:equal>
																	
															<logic:equal name="juvenileNonComplianceForm" property="updateFlow" value="Comp">
<%-- BEGIN SIGNATURE STATUS DISPLAY TABLE for COMPLETION --%>
																<table border="0" width="100%" cellspacing="2" cellpadding="1">
																	<tr>
																		<td colspan="2" class="formDeLabel"><bean:message key="prompt.juvenile" /> <bean:message key="prompt.signature" /> <bean:message key="prompt.status" /></td>
																	</tr>	
																	<tr>
																		<td class="formDeLabel"><bean:message key="prompt.signedDate" /></td>
																		<td class="formDe"><bean:write name="juvenileNonComplianceForm" property="signatureSignedDate" formatKey="date.format.mmddyyyy"/></td>
																	</tr>
																	<tr>
																		<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.juvenile" />'s <bean:message key="prompt.signatureStatus" /></td>
																		<td class="formDe"><bean:write name="juvenileNonComplianceForm" property="signatureStatusLiteral" /></td> 
																	</tr>
																	<tr>
																		<td class="formDeLabel"><bean:message key="prompt.parentInformed" />?</td>
																		<td class="formDe">
																			<logic:equal name="juvenileNonComplianceForm" property="parentalNotified" value='true'>
																				<bean:message key="prompt.yes" />
																			</logic:equal>
																			<logic:equal name="juvenileNonComplianceForm" property="parentalNotified" value='false'>
																				<bean:message key="prompt.no" />
																			</logic:equal>
																		</td>
																	</tr>	
																</table>
<%-- END SIGNATURE STATUS DISPLAY TABLE for COMPLETION --%>																	
																<div class='spacer'></div>
																<logic:equal name="juvenileNonComplianceForm" property="action" value="update">
<%-- BEGIN COMPLETION UPDATE TABLE --%>																	
																	<table  width="99%" align="center" cellpadding="2" cellspacing="0" class="borderTableBlue" >
																		<tr>
																			<td class="detailHead"><bean:message key="prompt.juvenile" /> <bean:message key="prompt.completionStatus" /></td>
																		</tr>
																		<tr>
																			<td>
																				<table border="0" width="100%" cellspacing="1" cellpadding="2">
																					<tr>
						                  												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.diamond" /><bean:message key="prompt.juvenile" /> <bean:message key="prompt.completed" /></td>
						                  												<td class="formDe">
										 													<bean:message key="prompt.yes" /><html:radio name="juvenileNonComplianceForm" property="juvenileCompletedStatus" value="YES" styleId="radioYes" />
																							<bean:message key="prompt.no" /><html:radio name="juvenileNonComplianceForm" property="juvenileCompletedStatus" value="NOO" styleId="radioNo" /> 
																							<bean:message key="prompt.partial" /><html:radio name="juvenileNonComplianceForm" property="juvenileCompletedStatus" value="PAR" styleId="radioPar" /> 
																						</td>
																					</tr>
																					<tr id="completionDate" class="hidden">
																						<td class="formDeLabel"><bean:message key="prompt.diamond" /><bean:message key="prompt.date" /> <bean:message key="prompt.completed" /></td>
																						<td class="formDe">
																							<html:text property="completionDateStr" size="10" maxlength="10" styleId="completionDateId" readonly="true"/>
																							
																						</td>
																					</tr>
																					<tr id="actionTaken" class="hidden">
																						<td class="formDeLabel" align="right"><bean:message key="prompt.diamond" /><bean:message key="prompt.actionTaken" /></td>
																						<td class="formDe">
																							<html:select property="actionTakenId" styleId="atSelect">
																								<html:option value=""><bean:message key="select.generic" /></html:option>
																								<html:optionsCollection property="actionTakenCodes" value="code" label="description" />
																								<html:option value="OTH"><bean:message key="prompt.other" /></html:option>
																							</html:select>
																						</td>
																					</tr>
																					<tr id="actionTakenCommenthdg" class="hidden">
																						<td class="formDeLabel"></td>
																						<td class="formDeLabel">
																							<bean:message key="prompt.diamond" /><bean:message key="prompt.actionTaken" /> <bean:message key="prompt.comments" />
																							<tiles:insert page="/jsp/caseworkCommon/spellCheckTile.jsp" flush="false">
			                      					 	 										<tiles:put name="tTextField" value="otherActionTakenComment"/>
			                      																<tiles:put name="tSpellCount" value="spellBtn2" />    
			                    															</tiles:insert>
																							(Maximum Characters allowed: 1000)
																						</td>	
																					</tr>
																					<tr id="actionTakenCommentText" class="hidden">
																						<td class="formDeLabel"></td>
																						<td class="formDeLabel">
																							<html:textarea name="juvenileNonComplianceForm" property="otherActionTakenComment" styleId="atComments" style="width:100%" rows="5" onmouseout="textLimit(this, 1000);" onkeydown="textLimit(this, 1000)" />
																						</td>
																					</tr>
																					<tr>
																						<td class="formDeLabel" colspan="2" >
																							<bean:message key="prompt.completion" /> <bean:message key="prompt.comments" />
																							<tiles:insert page="/jsp/caseworkCommon/spellCheckTile.jsp" flush="false">
			                      					 	 										<tiles:put name="tTextField" value="juvenileCompletedComments"/>
			                      																<tiles:put name="tSpellCount" value="spellBtn1" />    
			                    															</tiles:insert>
																							(Maximum Characters allowed: 1000)
																						</td>
																					</tr>
																					<tr>
																						<td colspan="2" class="formDeLabel">
																							<html:textarea name="juvenileNonComplianceForm" property="juvenileCompletedComments" style="width:100%" rows="5" onmouseout="textLimit(this, 1000);" onkeydown="textLimit(this, 1000)" />
																						</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																		<tr>
																			<td align="center">
																				<html:submit property="submitAction" onclick="return validateCompletionInput()"> <bean:message key="button.submitCompletionStatus" /></html:submit>
																			</td>
																		</tr>
																	</table>			
<%-- END COMPLETION UPDATE TABLE --%>		
																</logic:equal>													
																<logic:equal name="juvenileNonComplianceForm" property="action" value="confirm">
<%-- BEGIN COMPLETION CONFIRM TABLE --%>
																	<table border="0" width="100%" cellspacing="1" cellpadding="2">
																		<tr>
																			<td colspan="2" class="formDeLabel"><bean:message key="prompt.juvenile" /> <bean:message key="prompt.completionStatus" /></td>
																		</tr>
																		<tr>
			                  												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.juvenile" /> <bean:message key="prompt.completed" /></td>
			                  												<td class="formDe"><bean:write name="juvenileNonComplianceForm" property="juvenileCompletedStatusLit" /></td>
																		</tr>
																		<logic:equal name="juvenileNonComplianceForm" property="juvenileCompletedStatus" value="YES">
																			<td class="formDeLabel"><bean:message key="prompt.completionDate" /></td>
																			<td class="formDe"><bean:write name="juvenileNonComplianceForm" property="completionDateStr" /></td>
																		</logic:equal>
																<%-- 		<logic:notEqual name="juvenileNonComplianceForm" property="juvenileCompletedStatus" value="YES">  --%>
																			<tr>
																				<td class="formDeLabel"><bean:message key="prompt.actionTaken" /></td>
																				<td class="formDe"><bean:write name="juvenileNonComplianceForm" property="actionTakenDesc" /></td>
																			</tr>	
																			<logic:equal name="juvenileNonComplianceForm" property="actionTakenId" value="OTH">
																				<tr>
																					<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.actionTaken" /> <bean:message key="prompt.comments" /></td>
																					<td class="formDe"><bean:write name="juvenileNonComplianceForm" property="otherActionTakenComment" /></td>
																				</tr>
																			</logic:equal>
																<%-- 		</logic:notEqual>  --%>
																		<tr>
																			<td class="formDeLabel" width="1%" nowrap="nowrap" valign="top">
																				<bean:message key="prompt.completion" /> <bean:message key="prompt.comments" />
																			</td>
																			<td class="formDe"><bean:write name="juvenileNonComplianceForm" property="juvenileCompletedComments" /></td>
																		</tr>
																	</table>
<%-- BEGIN COMPLETION CONFIRM TABLE --%>																			
																</logic:equal> 
															</logic:equal> 		
															<div class='spacer'></div>				                  								         
<%-- BEGIN BUTTON TABLE --%>
															<table border="0" width="100%">
																<tr>
																	<td align="center">
																		<logic:equal name="juvenileNonComplianceForm" property="action" value="update">
																			<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
																<%--			<html:submit property="submitAction"> <bean:message key="button.printNotice" /></html:submit> --%>
																			<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
																		</logic:equal>	
																		<logic:equal name="juvenileNonComplianceForm" property="action" value="confirm">
																<%--			<html:submit property="submitAction"> <bean:message key="button.printNotice" /></html:submit>  --%>
																			<html:submit property="submitAction"> <bean:message key="button.noticeList" /></html:submit>
																		</logic:equal>
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
<%-- END BLUE TABS BORDER TABLE --%>	
	    </td>
	</tr>
</table>
<%-- END CASEFILE TABS TABLE --%>
</html:form>
<%-- END FORM --%>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>