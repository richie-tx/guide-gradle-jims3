<!DOCTYPE HTML>

<%-- 07/17/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 09/10/2012	C Shimek   	#74186 Add Court Ordered Probation Start Date display field --%>
<%-- 11/05/2012	C Shimek	#74186 commented out changes made 9/10/12 per request of JP department --%>

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
<title><bean:message key="title.heading"/> - casefileEditSupervisionEndDateSummary.jsp</title>
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/timeout.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|50">

<html:form action="submitSupervisionEndDateUpdate.do" target="content">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header">
			<bean:message key="title.juvenileCasework"/> - <bean:message key="title.casefile"/> -
			<bean:message key="title.update"/> Expected End Date <bean:message key="title.summary"/>
		</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Verify that the information is correct, then select the Finish button to save the information.</li>
				<li>If any changes are required, select the Back button.</li>
			</ul>	
		</td>
</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<%--header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%--header end--%>
<div class='spacer'></div>
<%-- BEGIN CASEFILE TABS TABLE --%>
<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign='top'>
			<%--tabs start--%>
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="casefiledetailstab"/>
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>				
			<%--tabs end--%>
<%-- BEGIN CASEFILE TABS BORDER TABLE --%>
			<table width='100%'  border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign='top' align="center">
						<div class='spacer'></div>			  
<%-- BEGIN CASEFILE INFO TABS TABLE --%>
						<table width='98%' border="0" cellpadding="0" cellspacing="0" align="center">
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
								<td bgcolor='#33cc66'><img src='../../images/spacer.gif' width="5"></td>
							</tr>
						</table>
<%-- END CASEFILE INFO TABS TABLE --%>
<%-- BEGIN CASEFILE INFO TABS BORDER TABLE --%>
						<table width='98%' align="center" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td valign='top' align="center">
									<div class='spacer'></div>										
<%-- BEGIN CASEFILE TABLE --%>
									<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr>
											<td valign='top' class="detailHead"><bean:message key="prompt.casefile"/></td>
										</tr>
										<tr>
											<td valign='top' align="center">
<%-- BEGIN CASEFILE DETAILS TABLE --%>								
												<table width="100%" cellpadding="4" cellspacing="1">
													<tr>
														<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.activationDate"/>/<bean:message key="prompt.time"/></td>
														<td class='formDe' colspan="3"><bean:write name="juvenileCasefileForm" formatKey="datetime.format.mmddyyyyHHmmss" property="activationDate"/></td>
											<%-- 			<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.courtOrdered"/> <bean:message key="prompt.probationStartDate"/></td>
														<td class='formDe'><bean:write name="juvenileCasefileForm" formatKey="datetime.format.mmddyyyy" property="courtOrderedProbationStartDateStr"/></td> --%>
													</tr>
													<tr>
														<td valign='top' class='formDeLabel' nowrap="nowrap" width="1%"><bean:message key="prompt.expectedSupervisionEndDate"/></td>
														<td valign='top' class="formDe" colspan='3'><bean:write name="juvenileCasefileForm" property="supervisionEndDateStr"/></td>
													</tr>  
													<!-- taken out for US 14459 
													<tr>
														<td class='formDeLabel' valign='top' nowrap='nowrap'><bean:message key="prompt.titleIVE"/>
															<bean:message key="prompt.assessment"/> <bean:message key="prompt.Needed"/>
														</td>
														<td class='formDe' colspan='3'><jims2:displayBoolean name="juvenileCasefileForm"property="isBenefitsAssessmentNeeded" trueValue="Yes" falseValue="No"/></td>
													</tr>
													-->
													<tr>
														<td class='formDeLabel' valign='top' nowrap='nowrap'>
															<bean:message key="prompt.maysi"/> <bean:message key="prompt.Needed"/>
														</td>
														<td class='formDe'  width='25%'><jims2:displayBoolean name="juvenileCasefileForm" property="isNewMAYSINeeded" trueValue="Yes" falseValue="No"/></td>
														<td class='formDeLabel' valign='top' nowrap='nowrap'>
															<bean:message key="prompt.risk"/> <bean:message key="prompt.analysis"/> <bean:message key="prompt.Needed"/>
														</td>
														<td class='formDe'><jims2:displayBoolean name="juvenileCasefileForm"property="isRiskAssessmentNeeded" trueValue="Yes" falseValue="No"/></td>
													</tr>
													<tr>
														<td class='formDeLabel' valign='top' nowrap='nowrap'>
															<bean:message key="prompt.referral"/> <bean:message key="prompt.assessment"/> <bean:message key="prompt.Needed"/>
														</td>
														<td class='formDe'><jims2:displayBoolean name="juvenileCasefileForm"property="isReferralAssessmentNeeded" trueValue="Yes" falseValue="No"/></td>
														<td class='formDeLabel'>
															<bean:message key="prompt.communitySupervision"/> <bean:message key="prompt.assessment"/> <bean:message key="prompt.Needed"/>
														</td>
														<td class='formDe'><jims2:displayBoolean name="juvenileCasefileForm"property="isCommunityAssessmentNeeded" trueValue="Yes" falseValue="No"/></td>
														<!-- taken out for US 14459 
														<td class='formDeLabel' valign='top' nowrap='nowrap' width="1%">
															<bean:message key="prompt.interview"/> <bean:message key="prompt.assessment"/> <bean:message key="prompt.Needed"/>
														</td>
														<td class='formDe'><jims2:displayBoolean name="juvenileCasefileForm" property="isInterviewAssessmentNeeded" trueValue="Yes" falseValue="No"/></td>
														-->
													</tr>
													<!-- taken out for US 14459 
													<tr>
														<td class='formDeLabel'>
															<bean:message key="prompt.communitySupervision"/> <bean:message key="prompt.assessment"/> <bean:message key="prompt.Needed"/>
														</td>
														<td class='formDe'><jims2:displayBoolean name="juvenileCasefileForm"property="isCommunityAssessmentNeeded" trueValue="Yes" falseValue="No"/></td>
														<td class='formDeLabel' nowrap='nowrap'>
															<bean:message key="prompt.testing"/> <bean:message key="prompt.assessment"/> <bean:message key="prompt.Needed"/>
														</td>
														<td class='formDe'><jims2:displayBoolean name="juvenileCasefileForm"property="isTestingAssessmentNeeded" trueValue="Yes" falseValue="No"/></td>
													</tr>
													-->
													<tr>
														<td class='formDeLabel' valign='top' nowrap='nowrap'>
															<bean:message key="prompt.residential"/> <bean:message key="prompt.assessment"/> <bean:message key="prompt.Needed"/>
														</td>
														<td class='formDe'><jims2:displayBoolean name="juvenileCasefileForm"property="isResidentialAssessmentNeeded" trueValue="Yes" falseValue="No"/></td>
														<td class='formDeLabel' valign='top' nowrap='nowrap'>
															<bean:message key="prompt.progress"/> <bean:message key="prompt.assessment"/> <bean:message key="prompt.Needed"/>
														</td>
														<td class='formDe'><jims2:displayBoolean name="juvenileCasefileForm"property="isProgressAssessmentNeeded" trueValue="Yes" falseValue="No"/></td>
													</tr>
													<tr>																	
														<td class='formDeLabel' nowrap='nowrap'>
															<bean:message key="prompt.residential"/> <bean:message key="prompt.progress"/> <bean:message key="prompt.assessment"/> <bean:message key="prompt.Needed"/>
														</td>
														<td class='formDe' colspan='3'><jims2:displayBoolean name="juvenileCasefileForm" property="isResidentialProgressAssessNeeded" trueValue="Yes" falseValue="No"/></td>
													</tr>
												</table>
<%-- END CASEFILE DETAILS TABLE --%>													
											</td>
										</tr>
									</table>
<%-- END CASEFILE TABLE --%>						
									<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 
												<html:submit property="submitAction"><bean:message key="button.finish"/></html:submit> 
												<input type="button" value="Cancel" onClick="goNav('/<msp:webapp/>displayJuvenileCasefileDetails.do?casefileId=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>')">
											</td>
										</tr>
									</table>
<%-- END BUTTON TABLE --%>
								</td>
							</tr>
						</table>
<%-- END CASEFILE INFO TABS BORDER TABLE --%>	
						<div class='spacer'></div>       
					</td>
				</tr>
			</table>
<%-- END CASEFILE TABS BORDER TABLE --%>			
			<div class='spacer'></div>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>