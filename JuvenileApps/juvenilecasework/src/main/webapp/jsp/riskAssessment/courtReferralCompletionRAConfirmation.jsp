<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 09/20/2005		DWilliamson	Create Interview RA Summary jsp--%>
<%-- 07/20/2009     CShimek     #61004 added timeout.js  --%>
<%-- 05/01/2012		CShimek		#73346 Revise hardcoded TJPC prompts to TJJD --%>
<%-- 08/31/2015	 	RCarter	   #29426 html 5 compliance effort and jquery 5 (when required) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>



<head>

<msp:nocache />
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading" />&nbsp;<bean:message
	key="title.juvenileCasework" />&nbsp;-courtReferralCompletionRAConfirmation.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0">
<html:form action="/handleReturn" target="content">

	<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|204">
	<%-- BEGIN HEADING TABLE --%>
	<table width='100%'>
		<tr>
			<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Risk Assessment TJJD Risk Completion Confirmation</td>
		</tr>

		<logic:notEmpty name="riskAnalysisForm" property="assessmentId">
			<tr>
				<td align="center" class="confirm">TJJD Risk Assessment completed.</td>
			</tr>
			<tr>
				<td align="center" class="">Risk ID <bean:write name="riskAnalysisForm" property="assessmentId" /> has been completed.</td>
			</tr>
		</logic:notEmpty>

	</table>
	<%-- END HEADING TABLE --%>

	<%-- BEGIN HEADER INFO TABLE --%>
	<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader" />
	</tiles:insert>
	<%-- END HEADER INFO TABLE --%>

	<%-- BEGIN TJJD Risk Recommendation TABLE --%>
	<div class='spacer'></div>
	<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top">
				<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
					<tiles:put name="tabid" value="casefiledetailstab" />
					<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
				</tiles:insert> <%-- BEGIN DETAIL TABLE --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign="top" align="center">
					<%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
					<div class='spacer'></div>
					<table width='98%' border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<table width='100%' border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td valign="top"><%--tabs start--%> <tiles:insert
											page="../caseworkCommon/casefileInfoTabs.jsp" flush="true">
											<tiles:put name="tabid" value="riskAnalysis" />
											<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
										</tiles:insert> <%--tabs end--%></td>
									</tr>
									<tr>
										<td bgcolor='#33cc66'><img src='../../images/spacer.gif'width="5"></td>
									</tr>
								</table>
	
								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
									<tr>
										<td valign="top" align="center">
										
										
										<div class='spacer'></div>
										<table align="center" width='98%' border="0" cellpadding="1" cellspacing="1" class="borderTableBlue">
	                       							<tr>
	                   									<td class="detailHead">
	                   										TJJD Risk Completion
	                   									</td>
	                     							</tr>
	                     							<tr>
								                    	<td align='center'>
								                    		<table width='100%' cellpadding='4' cellspacing='1'>
																	<tr>
					            									<td valign='top' class='formDeLabel' width='1%' nowrap='nowrap'>
					            										JJS Court Decision  
					            									</td>
					            									<td valign='top' class='formDe' colspan="3">
					            										<bean:write name="riskCourtReferralForm" property="jjsCourtDecision"/>
					            									</td>
					            								</tr>
	                       									                                
	                     										<tr>
					            									<td valign='top' class='formDeLabel' width='1%' nowrap='nowrap'>
					            										Collateral Visits 
					            									</td>
					            									<td valign='top' class='formDe' colspan="3">
					            										<bean:write name="riskCourtReferralForm" property="collateralVisits"/>
					            									</td>
					            								</tr>
					            								
					            								<tr>
					            									<td valign='top' class='formDeLabel' width='1%' nowrap='nowrap'>
					            										Face To Face Visits 
					            									</td>
					            									<td valign='top' class='formDe' colspan="3">
					            										<bean:write name="riskCourtReferralForm" property="face2FaceVisits"/>
					            									</td>
					            								</tr>
					            								
					            								<tr>
					            									<td valign='top' class='formDeLabel' width='1%' nowrap='nowrap'>
					            										Court Disposition TJJD 
					            									</td>
					            									<td valign='top' class='formDe' colspan="3">
					            										<!-- <bean:write name="riskCourtReferralForm" property="courtDispositionTJPC"/> -->
					            										<bean:write name="riskCourtReferralForm" property="courtDispositionTJPCDesc"/>
					            									</td>
					            								</tr>
		                                					</table>
	                   									</td>
	                 								</tr>
	                       						</table>									
										
										<%-- END TJJD Risk Recommendation TABLE --%> 
										<input type="hidden" name="casefileID" value='<bean:write name="juvenileCasefileForm" property="supervisionNum"/>' />
										<input type="hidden" name="juvenileNum" value='<bean:write name="juvenileCasefileForm" property="juvenileNum"/>' />
	
										<%-- BEGIN BUTTON TABLE --%>
										<div class='spacer'></div>
										<table border="0" width="100%">
											<tr>
												<td align="center"><html:submit property="submitAction">
													<bean:message key="button.returnToCasefile" />
												</html:submit>&nbsp; <html:submit property="submitAction">
													<bean:message key="button.returnToRiskAnalysis" />
												</html:submit></td>
											</tr>
										</table>
										<%-- END BUTTON TABLE --%></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<div class='spacer'></div>
					</td>
				</tr>
			</table>
			<div class='spacer'></div>
			</td>
		</tr>
	</table>

</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
