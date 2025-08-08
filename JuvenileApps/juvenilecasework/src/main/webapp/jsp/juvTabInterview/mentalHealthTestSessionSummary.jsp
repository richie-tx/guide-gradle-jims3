<!DOCTYPE HTML>
<%-- User selects the "Mental Health" tab in the "Interview Info" tab  on Juvenile Profile Detail page then selects the "Hospitalization" tab on MAYSI page --%>
<%--MODIFICATIONS --%>
<%-- 01/18/2007	Debbie Williamson	Create JSP --%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>
<%-- 07/10/2012 C Shimek    #73565 added age > 20 check (juvUnder21) to Add Testing Session button --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<%--CUSTOM LIBRARIES NEEDED FOR PAGE --%>
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
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

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - mentalHealthTestSessionSummary.jsp</title>
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/mentalHealth.js"></script>

</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/handleMentalHealthTestSession" target="content">

<logic:equal name="testingSessionForm" property="actionType" value="summary">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|318">
</logic:equal>
<logic:equal name="testingSessionForm" property="actionType" value="confirm">
    <logic:notEqual name="testingSessionForm" property="secondaryAction" value="update">
        <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|319">
    </logic:notEqual>
    <logic:equal name="testingSessionForm" property="secondaryAction" value="update">    
        <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|6">
    </logic:equal>
</logic:equal>
<logic:equal name="testingSessionForm" property="actionType" value="update">    
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|6">
</logic:equal>
<logic:equal name="testingSessionForm" property="actionType" value="view">    
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|458">
</logic:equal>
    
<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.mentalHealth"/>  
			<logic:equal name="testingSessionForm" property="actionType" value="summary">
					</td>	
				</tr>
				<tr>		
					<td align="center" class="header"> Create Testing Session Summary </td>
				</tr>	
			</logic:equal>
			<logic:equal name="testingSessionForm" property="actionType" value="confirm">
					 </td>
				</tr>			
				<logic:notEqual name="testingSessionForm" property="secondaryAction" value="update">
					<tr>
						<td align="center" class="header">Create Testing Session Confirmation</td>
					</tr>
				</logic:notEqual>	   
				<logic:equal name="testingSessionForm" property="secondaryAction" value="update">
					<tr>
						<td align="center" class="header">Update Testing Session</td>
					</tr>
				</logic:equal>
				<tr>
					<td class="confirm"><bean:write name="testingSessionForm" property="confirmMessage"/></td>
				</tr>
			</logic:equal>
  
  			<logic:equal name="testingSessionForm" property="actionType" value="update">
	      		<tr>
	      			<td align="center" class="header">Update Testing Session</td>
	      		</tr>
  			</logic:equal>
  
  			<logic:equal name="testingSessionForm" property="actionType" value="view">
     			- Testing Session Details</td>
     			</tr>
  			</logic:equal>                              
</table>    	
<%-- END HEADING TABLE --%>
<div class="spacer"></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<logic:equal name="testingSessionForm" property="actionType" value="summary">
		<tr>
			<td>
				<ul>
					<li>Review information then select <b>Finish</b> button to save Session Results.</li>
					<li>Select <b>Back</b> button to return to previous page.</li>
				</ul>
			</td>
		</tr>
	</logic:equal>
	<logic:equal name="testingSessionForm" property="actionType" value="view">
		<tr>
			<td align="left">
				<ul>
					<li>Select <b>Back</b> button to return to previous page.</li>
				</ul>
			</td>
		</tr>
	</logic:equal>
</table>
<%-- END INSTRUCTION TABLE --%>

<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>

<div class="spacer"></div>
<%--BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
<%-- BEGIN PROFILE TABS TABLE --%>		
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
<%--tabs start--%>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="interviewinfotab"/>
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>		
						<%--script type='text/javascript'>renderTabs("Interview Info")</script--%>
<%--tabs end--%>
					</td>
				</tr>
				<tr>
					<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
			</table>
<%-- END PROFILE TABS TABLE --%>			
<%-- BEGIN GREEN BORDER TABLE --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
						<div class="spacer"></div>
<%-- BEGIN INNER TABLE --%>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
<%-- BEGIN INTERVIEW TABS TABLE --%>								
									<table width='100%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign="top">
												<%--tabs start--%>
												<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
													<tiles:put name="tabid" value="mentalhealthtab"/>
													<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
												</tiles:insert>	
												<%--script type='text/javascript'>renderInterviewInfo("Mental Health")</script--%>
												<%--tabs end--%>
											</td>
										</tr>
										<tr>
									  		<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
										</tr>
									</table>
<%-- END INTERVIEW TABS TABLE --%>									
<%-- BEGIN OUTER BLUE BORDER TABLE --%>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
										<tr>
											<td valign="top" align="center">
												<div class="spacer"></div>
<%-- BEGIN MENTAL HEALTH TABS TABLE --%>												
												<table width='98%' border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td valign="top">
															<%--tabs start--%>
															<tiles:insert page="../caseworkCommon/mentalHealthTabs.jsp" flush="true">
																<tiles:put name="tabid" value="testingsession"/>
																<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
															</tiles:insert>	
															<%--script type='text/javascript'>renderTestResultsTabs("Hosp")</script--%>
															<%--tabs end--%>
														</td>
													</tr>
													<tr>
												  		<td bgcolor="#ff6666"><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
												    </tr>
												</table>
<%-- END MENTAL HEALTH TABS TABLE --%>	
<%-- BEGIN RED BORDER TABLE --%>	
												<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
													<tr>
														<td align="center">
															<div class="spacer"></div>
<%-- BEGIN SESSION RESULT BORDER TABLE --%>															
															<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
																<tr>
																	<td class="detailHead"><bean:message key="prompt.session"/> <bean:message key="prompt.results"/> </td>
																</tr>		
																<tr>
																	<td align="center">
																		<table border="0" cellpadding=4 cellspacing=1 width='100%'>
																			<tr>
																				<td class="formDeLabel"><bean:message key="prompt.serviceProvider"/></td>
																				<td class="formDe" colspan=3><bean:write name="testingSessionForm" property="serviceProviderName"/></td>
																			</tr>
																			<tr>
																				<td class="formDeLabel"><bean:message key="prompt.instructorName"/></td>
																				<td class="formDe" colspan="3"><bean:write name="testingSessionForm" property="instructorName"/></td>
																			</tr>
																			<tr>
																				<td class="formDeLabel"><bean:message key="prompt.referralSentDate"/></td>
																				<td class="formDe"><bean:write name="testingSessionForm" property="referralDate" formatKey="date.format.mmddyyyy"/></td>
																				<td class="formDeLabel"><bean:message key="prompt.session"/> <bean:message key="prompt.date"/></td>
																				<td class="formDe"><bean:write name="testingSessionForm" property="sessionDate"/></td>
																			</tr>
																			<tr>
																				<td class="formDeLabel"><bean:message key="prompt.programReferral#"/></td>
																				<td class="formDe"><bean:write name="testingSessionForm" property="programReferralNum"/></td>
																				<td class="formDeLabel"><bean:message key="prompt.program"/> <bean:message key="prompt.status"/></td>
																				<td class="formDe"><bean:write name="testingSessionForm" property="programStatus"/></td>
																			</tr>	
																			<tr>
																				<td class="formDeLabel"><bean:message key="prompt.eventSessionLength"/></td>
																				<td class="formDe"><bean:write name="testingSessionForm" property="evtSessionLength"/></td>
																				<td class="formDeLabel"><bean:message key="prompt.eventStatus"/></td>
																				<td class="formDe"><bean:write name="testingSessionForm" property="eventStatus"/></td>
																			</tr>	
																			<tr>
																				<td class="formDeLabel"><bean:message key="prompt.eventTime"/></td>
																				<td class="formDe"><bean:write name="testingSessionForm" property="eventTime"/></td>
																				<td class="formDeLabel"><bean:message key="prompt.eventType"/></td>
																				<td class="formDe"><bean:write name="testingSessionForm" property="eventType"/></td>
																			</tr>	
																			<tr>
																				<td class="formDeLabel"><bean:message key="prompt.location"/> <bean:message key="prompt.details"/></td>
																				<td class="formDe" colspan="3"><bean:write name="testingSessionForm" property="locationDetails"/></td>
																			</tr>	
																			<tr>
																				<td class="formDeLabel"><bean:message key="prompt.serviceLocation"/> <bean:message key="prompt.unit"/></td>
																				<td class="formDe" colspan="3"><bean:write name="testingSessionForm" property="serviceLocationUnit"/></td>
																			</tr>	
																			<tr>
																				<td class="formDeLabel"><bean:message key="prompt.test"/> <bean:message key="prompt.type"/></td>
																				<td class="formDe"><bean:write name="testingSessionForm" property="testType"/></td>
																				<td class="formDeLabel"><bean:message key="prompt.actual"/> <bean:message key="prompt.sessionLength"/></td>
																				<td class="formDe"><bean:write name="testingSessionForm" property="actualSessionLengthDesc"/></td>
																			</tr>	
																			<tr>
																				<td><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
																			</tr>																			
																			<tr>
																				<td class="detailHead" colspan="4"><bean:message key="prompt.mentalHealth"/> <bean:message key="prompt.testing"/> <bean:message key="prompt.history"/></td>
																			</tr>		
																			<tr>
																				<td class="formDeLabel" width='1%' nowrap="nowrap"><bean:message key="prompt.psychological"/> <bean:message key="prompt.assessment"/></td>
																				<td class="formDe" nowrap="nowrap"><bean:write name="testingSessionForm" property="psychoAssessment"/></td>
																				<td class="formDeLabel" width='1%' nowrap="nowrap"><bean:message key="prompt.psychiatric"/> <bean:message key="prompt.assessment"/></td>
																				<td class="formDe" nowrap="nowrap"><bean:write name="testingSessionForm" property="psychiatricAssessment"/></td>
																			</tr>
																			<tr>
																				<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.mentalRetardation"/> <bean:message key="prompt.diagnosis"/></td>
																				<td class="formDe" nowrap="nowrap"><bean:write name="testingSessionForm" property="mentalRetardationDiagnosis"/></td>
																				<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.mentalIllness"/> <bean:message key="prompt.diagnosis"/></td>
																				<td class="formDe" nowrap="nowrap"><bean:write name="testingSessionForm" property="mentalIllnessDiagnosis"/></td>
																			</tr>
																			<tr>
																				<td><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
																			</tr>
																			<tr>
																				<td class="formDeLabel" colspan="4"><bean:message key="prompt.recommendations"/></td>														
																			</tr>
																			<tr>
																				<td class="formDe" colspan="4"><bean:write name="testingSessionForm" property="recommendations"/></td>														
																			</tr>
																		</table>
																	</td>
																</tr>
															</table>
<%-- END SESSION RESULT BORDER TABLE --%>																
<%-- BEGIN BUTTON TABLE --%>
															<table border="0" width="100%">                              
																<logic:equal name="testingSessionForm" property="actionType" value="summary">
																	<tr>	
																		<td align="center">
																			<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
																			<html:submit property="submitAction" ><bean:message key="button.finish" /></html:submit>		
																			<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
																		</td>	
																	</tr>
																</logic:equal>
								
															 	<logic:equal name="testingSessionForm" property="actionType" value="confirm">							     
																	<tr>
																 		<td align="center">
																	 		<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MH_DSM_U%>'>	
																				
																					<html:submit property="submitAction"><bean:message key="button.addDSMV" /></html:submit>
																				
																			</jims2:isAllowed>
																			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MH_IQ_U%>'>		
																				<html:submit property="submitAction" ><bean:message key="button.addIQTest" /></html:submit>
																			</jims2:isAllowed>

																   			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MH_AT_U%>'>		
																		 		<html:submit property="submitAction"><bean:message key="button.addAchievement" /></html:submit>
																			</jims2:isAllowed>
																			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MH_AF_U%>'>
																				<html:submit property="submitAction"><bean:message key="button.addAdaptiveFunctioning" /></html:submit>
																			</jims2:isAllowed>
																			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MH_AB_U%>'>
																				<html:submit property="submitAction"><bean:message key="button.addAdaptiveBehavior" /></html:submit>
																			</jims2:isAllowed>
																		</td>
														   			</tr>
																	<tr>
																		<td align="center">
																			<html:submit property="submitAction"><bean:message key="button.previousResults" /></html:submit>
																		</td>
																	</tr>
																</logic:equal>
																			
																<logic:equal name="testingSessionForm" property="actionType" value="update">							     
																	<tr>
																		<td align="center">
																		 	<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MH_DSM_U%>'>	
																			
																					<html:submit property="submitAction"><bean:message key="button.addDSMV" /></html:submit>
																				
																			</jims2:isAllowed>
																			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MH_IQ_U%>'>		
																				<html:submit property="submitAction" ><bean:message key="button.addIQTest" /></html:submit>
																			</jims2:isAllowed>
																			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MH_AT_U%>'>		
																				<html:submit property="submitAction"><bean:message key="button.addAchievement" /></html:submit>
																			</jims2:isAllowed>
																			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MH_AF_U%>'>
																				<html:submit property="submitAction"><bean:message key="button.addAdaptiveFunctioning" /></html:submit>
																			</jims2:isAllowed>
																			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MH_AB_U%>'>
																				<html:submit property="submitAction"><bean:message key="button.addAdaptiveBehavior" /></html:submit>
																			</jims2:isAllowed>
																		</td>
																	</tr>
																	<tr>
																		<td align="center">
																			<html:submit property="submitAction"><bean:message key="button.previousResults" /></html:submit>
																		</td>
																	</tr>
																</logic:equal>
																<logic:equal name="testingSessionForm" property="actionType" value="view">
																	<tr>	
																		<td align="center">
																			<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
																			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MH_TEST_U%>'>
																				<logic:equal name="juvenileProfileHeader" property="juvUnder21" value="true">
																					<html:submit property="submitAction"><bean:message key="button.addTestingSessionResults" /></html:submit>
																				</logic:equal>	
																			</jims2:isAllowed>		
																			<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
																		</td>	
																	</tr>
																</logic:equal>	                       	
															</table>
<%-- END BUTTON TABLE --%>
														</td>
													</tr>
												</table>
<%-- END RED BORDER TABLE --%>												
												<div class="spacer"></div>
											</td>
										</tr>
									</table>
<%--END OUTER BLUE BORDER TABLE --%>								
									<div class="spacer"></div>
								</td>
							</tr>
						</table>
<%-- END INNER TABLE --%>						
					</td>
				</tr>
			</table>
<%--END GREEN BORDER TABLE --%>			
		</td>
	</tr>
</table>
<%--END DETAIL TABLE --%>
</html:form>
<%-- END FORM --%>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>