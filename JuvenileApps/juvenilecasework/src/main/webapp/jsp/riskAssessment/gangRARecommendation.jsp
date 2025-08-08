<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 05/14/2013		CShimek		Create jsp --%>
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

<title><bean:message key="title.heading" />&nbsp;<bean:message key="title.juvenileCasework" />&nbsp;-gangRARecommendation.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
</head>

<body  topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" >
<html:form action="/handleReturn" target="content">
    <logic:notEqual name="riskAnalysisForm" property="mode" value="update"> 
	   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|209">
    </logic:notEqual>
    <logic:equal name="riskAnalysisForm" property="mode" value="update"> 
	   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|220">
    </logic:equal>
<%-- BEGIN HEADING TABLE --%>
	<table width='100%'>
 <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> -
    <logic:notEqual name="riskAnalysisForm" property="riskAssessmentType" value="MH SCREEN">
    		 Risk Assessment Gang Information
    </logic:notEqual>
    <logic:equal name="riskAnalysisForm" property="riskAssessmentType"  value="MH SCREEN"> 
    Risk Assessment MH Screen Information
    </logic:equal>
	&nbsp;Recommendation</td>
      <logic:equal name="riskAnalysisForm" property="mode" value="update">
	   Update 
	</logic:equal>
    </tr>
  <logic:notEmpty name="riskAnalysisForm" property="assessmentId" >
	  <tr>
	    <td align="center" class="confirm" >
	    <logic:notEqual name="riskAnalysisForm" property="riskAssessmentType"  value="MH SCREEN">Gang</logic:notEqual>
	    <logic:equal name="riskAnalysisForm" property="riskAssessmentType"  value="MH SCREEN">MH Screen</logic:equal> Risk Assessment saved.</td>
	  </tr>
	  	  <logic:empty name="riskAnalysisForm" property="mode">
	  	<tr>
	    	<td align="center" class="" >Risk ID <bean:write name="riskAnalysisForm" property="assessmentId" /> has been generated.</td>
	  	</tr>
	  </logic:empty>
	</logic:notEmpty> 
	</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN HEADER INFO TABLE --%>
	<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
		<tiles:put name="headerType" value="casefileheader" />
	</tiles:insert>
<%-- END HEADER INFO TABLE --%>
	<div class='spacer'></div>
<%-- BEGIN GANG RISK RECOMMENDATION TABLE --%>
	<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top">
				<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
					<tiles:put name="tabid" value="casefiledetailstab" />
					<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
				</tiles:insert>
<%-- BEGIN BLUE BORDER TABS TABLE --%>
		 		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					<tr>
						<td valign="top" align="center">
							<div class='spacer'></div>  
<%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
							<table width='98%' align="center" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td valign="top"> 
										<tiles:insert page="../caseworkCommon/casefileInfoTabs.jsp" flush="true">
											<tiles:put name="tabid" value="riskAnalysis" />
											<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
										</tiles:insert>
									</td>
								</tr>
								<tr>
									<td bgcolor='#33cc66' height="5"></td>
								</tr>
							</table>
	
							<table width='98%' align="center" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
								<tr>
									<td valign="top" align="center">
										<div class='spacer'></div>
<%-- END Program Referral History TABLE --%>
 <%-- BEGIN Interview Information Recommendation TABLE --%>
 										<%-- User Story 28932 changed Gang Copy to MH Screen--%> 
										<%-- add logic tag to seperate the gang copy and gang offender --%>
										 
										<table align="center" width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
											<tr>
												<td class="detailHead" colspan="2">
												<logic:notEqual name="riskAnalysisForm" property="riskAssessmentType"  value="MH SCREEN">Gang Risk&nbsp;</logic:notEqual>
												<logic:equal name="riskAnalysisForm" property="riskAssessmentType"  value="MH SCREEN">MH Screen Risk &nbsp;</logic:equal>
												<bean:message key="prompt.recommendation" /></td>
											</tr>
											 <logic:iterate id="recommendationsIndex" name="riskAnalysisForm" property="recommendations" indexId="index">
												<tr bgcolor='#f0f0f0'>
														<td align="center">
															<table width='100%' cellpadding="4" cellspacing="0">
																		<tr>
																			<td class="formDeLabel" width='30%'><bean:message key="prompt.totalScore" /></td>
																			<td class="formDe" ><bean:write name="recommendationsIndex" property="riskAnalysisScore"/></td>
																		</tr>
																<tr>
																	<td class="formDeLabel" width='50%'><bean:write name="recommendationsIndex" property="resultGroupDisplayDesc"/> <bean:message key="prompt.recommendation" /></td>
																	<td class="formDe" width='50%'><bean:write name="recommendationsIndex" property="riskAnalysisRecommendation"/></td>
																</tr>
															</table>
														</td>
													</tr>
    										</logic:iterate>
											<logic:equal name="riskAnalysisForm" property="mode" value="update">
								          		<tr id='modificationReasonRow' class=''>
		     										<td colspan='2'>
									          			<table align="center" width='100%' border="0" cellpadding="4" cellspacing="1" class="borderTableGrey">
													 		<tr>
		                											<td class='formDeLabel' colspan='2'>Reason for modification&nbsp;
		               												(Max. characters allowed: 550)
		              												</td>
		                										</tr>
		                 									<tr>
		                 										<td class='formDe' colspan='2'><bean:write name="riskAnalysisForm" property="modReason" /></td>
		  	              									</tr>
		    	          										</table>
		        	      									</td>
		     											</tr>
											</logic:equal>
										</table>
										
<%-- END TJJD Risk Recommendation TABLE --%> 
										<input type="hidden" name="casefileID" value='<bean:write name="juvenileCasefileForm" property="supervisionNum"/>' />
										<input type="hidden" name="juvenileNum" value='<bean:write name="juvenileCasefileForm" property="juvenileNum"/>' />
										<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
										<table width='98%' align="center"border="0">
											<tr>
												<td align="center">
													<html:submit property="submitAction"><bean:message key="button.returnToCasefile" /></html:submit>&nbsp;
										 			<html:submit property="submitAction"><bean:message key="button.returnToRiskAnalysis" /></html:submit>
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
<%-- END BLUE BORDER TABS TABLE --%>				
				<div class='spacer'></div>
			</td>
		</tr>
	</table>
<%-- END GANG RISK RECOMMENDATION TABLE --%>		
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>