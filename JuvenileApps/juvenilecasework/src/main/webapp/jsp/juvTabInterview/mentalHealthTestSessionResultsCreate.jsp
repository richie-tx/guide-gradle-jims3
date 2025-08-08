<!DOCTYPE HTML>

<%-- User selects to Add Testing Session Results--%>
<%--MODIFICATIONS --%>
<%-- 02/12/2007	 Uma Gopinath	Create JSP --%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>

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
<%@ page import="naming.PDCodeTableConstants" %>

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
<title><bean:message key="title.heading"/> - mentalHealthTestSessionResultsCreate.jsp</title>

<html:javascript formName="testingSessionForm"/>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>/js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>/js/AnchorPosition.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/mentalHealth.js"></script>

</head> 

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0" >
<html:form action="/displayMentalHealthTestSessionSummary" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|317">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.mentalHealth"/></td>
  </tr>
  <tr>
   	<td align="center" class="header">  	
 		 <bean:message key="prompt.create"/>   	
  	 <bean:message key="prompt.testing"/> 
     <bean:message key="prompt.session"/> 
   </td>
  </tr>
</table>
<%-- END HEADING TABLE --%>


<%-- BEGIN INSTRUCTION TABLE --%>
<div class="spacer"></div>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Enter information then select <b>Next</b> button to continue.</li>
				<li>At least one of the Yes/No fields must be answered.</li>
				<li>Select <b>Back</b> button to return to previous page.</li>
			</ul>
		</td>
	</tr>	
	<tr>
     <td class="required"><bean:message key="prompt.2.diamond"/> <bean:message key="prompt.requiredFieldsInstruction" />&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction"/></td>
   </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN ERROR TABLE --%>
<table width="100%">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
</table>
<%-- END ERROR TABLE --%> 

<%--juv profile header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%--juv profile header end--%>

<%--BEGIN DETAIL TABLE --%>
<div class="spacer"></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
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
				  	<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				  </tr>
			</table>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
            <%-- BEGIN TABLE --%>
    				<div class="spacer"></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
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
									  		<td bgcolor='#6699FF'><img src='../../common/images/spacer.gif' width="5"></td>
									  </tr>
									</table>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
										<tr>
											<td valign="top" align="center">
											<div class="spacer"></div>
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
												  	  <td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
												  </tr>
												</table>

												<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
													<tr>
														<td align="center">
																<div class="spacer"></div>
															<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
																	<tr>
																		<td class="detailHead"><bean:message key="prompt.session"/> <bean:message key="prompt.results"/> </td>
																	</tr>		
																<tr>
																	<td align="center">
																		<table border="0" cellpadding="4" cellspacing="1" width='100%'>
																			<tr>
																				<td class="formDeLabel"><bean:message key="prompt.serviceProvider"/></td>
																				<td class="formDe" colspan="3"><bean:write name="testingSessionForm" property="serviceProviderName"/>
																			</td>
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
																				<td class="formDeLabel"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.test"/> <bean:message key="prompt.type"/></td>
																				<td class="formDe">
																					<html:select
																						name="testingSessionForm" property="testTypeId" size="1">
																						<html:option value="">Please Select</html:option>
																						<jims2:codetable codeTableName='TEST_TYPE' sortOrder="ASC"></jims2:codetable>
																					</html:select>
																				</td>
																				<td class="formDeLabel"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.actual"/> <bean:message key="prompt.sessionLength"/></td>
																				<td class="formDe">
																					 
																					<html:select name="testingSessionForm" property="actualSessionLength"  size="1" >
																					<html:option value="">Please Select</html:option>
																						<jims2:codetable codeTableName="<%=PDCodeTableConstants.SESSION_LENGTH_INTERVAL%>" />
																					</html:select>
																				</td>
																			</tr>
																			<tr><td><img src="/<msp:webapp/>images/spacer.gif" width="5"></td></tr>	
																			
																			<tr>
																				<td colspan="6" class="detailHead"> <bean:message key="prompt.mentalHealth"/> <bean:message key="prompt.testing"/> <bean:message key="prompt.history"/></td>
																			<tr>
																				<td class="formDeLabel" width='1%' nowrap="nowrap"><bean:message key="prompt.psychological"/> <bean:message key="prompt.assessment"/></td>
																				<td class="formDe" nowrap="nowrap">Yes <html:radio name="testingSessionForm" property="psychoAssessment" value="Yes"/>No <html:radio name="testingSessionForm" property="psychoAssessment" value="No"/></td>
																				<td class="formDeLabel" width='1%' nowrap="nowrap"><bean:message key="prompt.psychiatric"/> <bean:message key="prompt.assessment"/></td>
																				<td class="formDe" nowrap="nowrap">Yes <html:radio name="testingSessionForm" property="psychiatricAssessment" value="Yes"/>No <html:radio name="testingSessionForm" property="psychiatricAssessment" value="No"/></td>
																			</tr>
																			<tr>
																				<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.mentalRetardation"/> <bean:message key="prompt.diagnosis"/></td>
																				<td class="formDe" nowrap="nowrap">Yes <html:radio name="testingSessionForm" property="mentalRetardationDiagnosis" value="Yes"/>No <html:radio name="testingSessionForm" property="mentalRetardationDiagnosis" value="No"/></td>
																				<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.mentalIllness"/> <bean:message key="prompt.diagnosis"/></td>
																				<td class="formDe" nowrap="nowrap">Yes <html:radio name="testingSessionForm" property="mentalIllnessDiagnosis" value="Yes"/>No <html:radio name="testingSessionForm" property="mentalIllnessDiagnosis" value="No"/></td>
																			</tr>
																					   		  

																			<tr><td><div class="spacer"></div></td></tr>
																			<tr>
																				<td class="formDeLabel" colspan="4"><bean:message key="prompt.recommendations"/>
                												  &nbsp;
                                					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
                                						<tiles:put name="tTextField" value="recommendations"/>
                                 						<tiles:put name="tSpellCount" value="spellBtn1" />
                              						</tiles:insert>  
																				</td>														
																			</tr>
																			<tr>
																				 <td colspan="4" class="formDe"><html:textarea rows="4" cols="40" style="width:100%" name="testingSessionForm" property="recommendations"></html:textarea></td>														
																			</tr>
																		</table>
																	</td>
																</tr>
															</table>
															
                            <%-- BEGIN BUTTON TABLE --%>
                          
                            <table border="0" width="100%">
                              <tr>
                                <td align="center">
                                  	<html:submit property="submitAction">
                    					<bean:message key="button.back"></bean:message>
                    				</html:submit>
                    				<html:submit property="submitAction" onclick="return validateTestingSessionForm(this.form);" >
                    					<bean:message key="button.next"></bean:message>
                    				</html:submit>		
                    				 <html:submit property="submitAction">
                    					<bean:message key="button.cancel"></bean:message>
                    				</html:submit>
                    			</td>			
                              </tr>
                            </table><%-- END BUTTON TABLE --%>

														</td>
													</tr>
												</table>
												<div class="spacer"></div>
											</td>
										</tr>
									</table>
									<div class="spacer"></div>
									<br><%-- END TABLE --%>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%--END DETAIL TABLE --%>

</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
