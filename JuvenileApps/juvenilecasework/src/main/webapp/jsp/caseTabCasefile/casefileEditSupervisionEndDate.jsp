<!DOCTYPE HTML>

<%-- 09/10/2012 	C Shimek     	#74186 Add Court Ordered Probation Start Date display field and revised validation js for this field --%>
<%-- 11/05/2012		C Shimek	  	#74186 commented out changes made 9/10/12 per request of JP department --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
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
<title><bean:message key="title.heading"/> - casefileEditSupervisionEndDate.jsp</title>


<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casefileEditSupervisionEndDate.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type='text/javascript'>
function validateEndDate(theForm)
{ 
  	var endDate = Trim( theForm.supervisionEndDateStr.value ) ;

  	var msg = "";
	var result = validateDate(endDate, "Expected End Date ");
// validate date to be valid date in mm/dd/yyyy format and not a past date	
	if (result != "") {
		msg = result;
		theForm.supervisionEndDateStr.focus();
	}
	if (result == ""){
// validate date to be greater than Activation date	
		var dt = endDate + " 00:00:00";
		var endDateTime = new Date(dt);
		var actDate = theForm.activationDateStr.value;
		var dt = actDate + " 00:00:00"; 
		var actDateTime = new Date(dt);
		if (endDateTime < actDateTime){
			theForm.supervisionEndDateStr.focus();
			msg = "Expected End Date must be greater than Activation Date.\n";
		}
// validate date to be greater than Court Ordered Probation State Date if present
//		var probStartDate = theForm.courtOrderedProbationStartDateStr.value;
//		if (probStartDate != ""){
//			var dt3 = probStartDate + " 00:00:00"; 
//			var probStartDateTime = new Date(dt3);
//			if (endDateTime < probStartDateTime){
//				theForm.supervisionEndDateStr.focus();
//				msg += "Expected End Date must be greater than Activation Date.\n";
//			}
//		}
	}
	if (msg == ""){
		return true;
	}
	alert(msg);
	return false;
}
function validateDate(fldValue, fldLit)
{
	var numericRegExp = /^[0-9]*$/;
	var msg = "";
	if (fldValue == "")
	{
		msg = fldLit + "is required.\n";
		return msg;
	}
	if (fldValue.length < 10 || fldValue.indexOf("/") < 2)
	{
		msg = fldLit + "must be in mm/dd/yyyy format.\n";
		return msg;
	}
	var dValues = fldValue.split('/');
	var month = dValues[0];
	var day = dValues[1];
	var year = dValues[2];

	if (numericRegExp.test(month,numericRegExp) == false || numericRegExp.test(day,numericRegExp) == false || numericRegExp.test(year,numericRegExp) == false ) { 
		msg = fldLit + "is not a valid date.\n";
		return msg; 
	} 

	if (month.length != 2 || day.length != 2 || year.length != 4) {
		msg = fldLit + "must be in mm/dd/yyyy format.\n";
		return msg;
	} 

    if (month < 1 || month > 12)
    {
		msg = fldLit + "is not valid.\n";
		return msg;
    }
    if (day < 1 || day > 31) {
		msg = fldLit + "is not valid.\n";
		return msg;
    }
    if ((month == 4 || month == 6 || month == 9 || month == 11) && (day == 31))
    {
		msg = fldLit + "is not valid.\n";
		return msg;
    }
    if (month == 2) {
        var leap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
        if (day > 29 || (day == 29 && !leap)) {
    		msg = fldLit + "is not valid.\n";
    		return msg;
        }
    }  
 	return "";
}
</script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin='0' leftmargin="0">
<html:form action="displaySupervisionEndDateUpdateSummary.do" target="content">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.casefile"/> - <bean:message key="title.update"/> Expected Supervision End Date</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Update the Expected End Date, then select Submit button to view Summary.</li>
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required"><bean:message key="prompt.requiredFields" />&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction" /></td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%--header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%--header end--%>

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|48">
<html:hidden property="supervisionTypeId" />
<html:hidden property="activationDateStr" /> 
<html:hidden property="courtOrderedProbationStartDateStr" /> 

<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign='top'>
  		<%--tabs start--%>
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="casefiledetailstab"/>
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>				
  		<%--tabs end--%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign='top' align='center'>
						<div class='spacer'></div>	
<%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign='top'>
												<%--tabs start--%>
												<tiles:insert page="../caseworkCommon/casefileInfoTabs.jsp" flush="true">
												<tiles:put name="tabid" value="casefiledetailstab"/>
												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
												</tiles:insert>	
												<%--tabs end--%>
											</td>
										</tr>
										<tr>
											<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
										</tr>
									</table>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
										<tr>
											<td valign='top' align='center'>
		  										<div class='spacer'></div>
<%-- BEGIN CASEFILE TABLE --%>
					              				<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
            										<tr>
            											<td valign='top' class='detailHead'><bean:message key="prompt.casefile"/></td>
            					  					</tr>
            					  					<tr>
            					  						<td valign='top' align='center'>
            					  							<table width='100%' cellpadding='4' cellspacing='1'>
              													<tr>
					              									<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.activationDate"/>/<bean:message key="prompt.time"/></td>
					              									<td class='formDe' colspan="3" ><bean:write name="juvenileCasefileForm" formatKey="datetime.format.mmddyyyyHHmmss" property="activationDate"/></td>
					              							<%-- 		<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.courtOrdered"/> <bean:message key="prompt.probationStartDate"/></td>
					              									<td class='formDe'><bean:write name="juvenileCasefileForm" formatKey="datetime.format.mmddyyyy" property="courtOrderedProbationStartDateStr"/></td> --%>
					              								</tr>
					              								<tr>
					              									<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.2.diamond" /> <bean:message key="prompt.expectedSupervisionEndDate" /></td>
																	<td valign='top' class="formDe" colspan='3'>
																		
																		<html:text name="juvenileCasefileForm" property="supervisionEndDateStr" size="10" maxlength="10" styleId="expectedSupervisionEndDateId"/>
																		
																	</td>
																</tr>
																<!-- taken out for US 14459 
																<tr>
																	<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.titleIVE"/>
																		<bean:message key="prompt.assessment"/> <bean:message key="prompt.Needed"/>
																	</td>
																	<td class='formDe' colspan='3'><jims2:displayBoolean name="juvenileCasefileForm" property="isBenefitsAssessmentNeeded" trueValue="YES" falseValue="NO"/></td>
																</tr>
																-->
																<tr>
																	<td class='formDeLabel' nowrap='nowrap'>
																		<bean:message key="prompt.maysi"/> <bean:message key="prompt.Needed"/>
																	</td>
																	<td class='formDe' width='25%'><jims2:displayBoolean name="juvenileCasefileForm" property="isNewMAYSINeeded" trueValue="YES" falseValue="NO"/></td>
																	<td class='formDeLabel' nowrap='nowrap'>
																		<bean:message key="prompt.risk"/> <bean:message key="prompt.analysis"/> <bean:message key="prompt.Needed"/>
																	</td>
																	<td class='formDe'><jims2:displayBoolean name="juvenileCasefileForm" property="isRiskAssessmentNeeded" trueValue="YES" falseValue="NO"/></td>
																</tr>
																<tr>
																	<td class='formDeLabel' nowrap='nowrap'>
																		<bean:message key="prompt.referral"/> <bean:message key="prompt.assessment"/> <bean:message key="prompt.Needed"/>
																	</td>
																	<td class='formDe'><jims2:displayBoolean name="juvenileCasefileForm" property="isReferralAssessmentNeeded" trueValue="YES" falseValue="NO"/></td>
																	<!-- taken out for US 14459 
																	<td class='formDeLabel' nowrap='nowrap' width="1%">
																		<bean:message key="prompt.interview"/> <bean:message key="prompt.assessment"/> <bean:message key="prompt.Needed"/>
																	</td>
																	<td class='formDe'><jims2:displayBoolean name="juvenileCasefileForm" property="isInterviewAssessmentNeeded" trueValue="YES" falseValue="NO"/></td>
																	-->
																	<td class='formDeLabel'>
																		<bean:message key="prompt.communitySupervision"/> <bean:message key="prompt.assessment"/> <bean:message key="prompt.Needed"/>
																	</td>
																	<td class='formDe'><jims2:displayBoolean name="juvenileCasefileForm" property="isCommunityAssessmentNeeded" trueValue="YES" falseValue="NO"/></td>
																</tr>
																	<!-- taken out for US 14459 
																<tr>
																	<td class='formDeLabel'>
																		<bean:message key="prompt.communitySupervision"/> <bean:message key="prompt.assessment"/> <bean:message key="prompt.Needed"/>
																	</td>
																	<td class='formDe'><jims2:displayBoolean name="juvenileCasefileForm" property="isCommunityAssessmentNeeded" trueValue="YES" falseValue="NO"/></td>
																	<td class='formDeLabel' nowrap='nowrap'>
																		<bean:message key="prompt.testing"/> <bean:message key="prompt.assessment"/> <bean:message key="prompt.Needed"/></td>
																	<td class='formDe'><jims2:displayBoolean name="juvenileCasefileForm" property="isTestingAssessmentNeeded" trueValue="YES" falseValue="NO"/></td>
																</tr>
																-->
																<tr>
																	<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.residential"/>
																		<bean:message key="prompt.assessment"/> <bean:message key="prompt.Needed"/>
																	</td>
																	<td class='formDe'><jims2:displayBoolean name="juvenileCasefileForm" property="isResidentialAssessmentNeeded" trueValue="YES" falseValue="NO"/></td>
																	<td class='formDeLabel' nowrap='nowrap'>
																		<bean:message key="prompt.progress"/> <bean:message key="prompt.assessment"/> <bean:message key="prompt.Needed"/>
																	</td>
																	<td class='formDe'><jims2:displayBoolean name="juvenileCasefileForm" property="isProgressAssessmentNeeded" trueValue="YES" falseValue="NO"/></td>
																</tr>
																<tr>																	
																	<td class='formDeLabel' nowrap='nowrap'>
																		<bean:message key="prompt.residential"/> <bean:message key="prompt.progress"/> <bean:message key="prompt.assessment"/> <bean:message key="prompt.Needed"/>
																	</td>
																	<td class='formDe' colspan='3'><jims2:displayBoolean name="juvenileCasefileForm" property="isResidentialProgressAssessNeeded" trueValue="YES" falseValue="NO"/></td>
																</tr>
															</table>
				            							</td>
				            						</tr>
				            					</table>
<%-- END CASEFILE TABLE --%>
<%-- BEGIN BUTTON TABLE --%>
												<table border="0" width="100%">
													<tr>
														<td align="center">
												<%-- commented out back button 09/10/2012, not on PT and no method in action  --%> 
													<%--	<input type="button"  name="submitAction" value="Back">  --%> 
															<html:submit  property="submitAction" onclick="return validateEndDate(this.form)"><bean:message key="button.submit" /></html:submit>
															<input type="button" value="Cancel" onClick="goNav('/<msp:webapp/>displayJuvenileCasefileDetails.do?casefileId=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>')"> 
														</td>
													</tr>
												</table>
<%-- END BUTTON TABLE --%>
											</td>
										</tr>
									</table>
									<div class='spacer'></div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>