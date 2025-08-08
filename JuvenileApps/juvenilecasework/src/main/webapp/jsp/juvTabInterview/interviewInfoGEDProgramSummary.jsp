<!DOCTYPE HTML>
<%-- User clicks the "Add School History" button from previous page --%>
<%--MODIFICATIONS --%>
<%-- 10/10/2012	C Shimek		Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@page import="naming.UIConstants"%>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<html:javascript formName="juvenileSchoolHistoryForm" />
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
<title><bean:message key="title.heading"/> - interviewInfoSchoolEducationalHistoryCreate.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
</head> 
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0">
<html:form action="/submitGEDProgramCreate" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|">

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Create School History Information 
		<logic:notEqual  name="juvenileGEDProgramForm" property="action" value="<%=UIConstants.CONFIRM%>">Summary</logic:notEqual>
		<logic:equal  name="juvenileGEDProgramForm" property="action" value="<%=UIConstants.CONFIRM%>">Confirmation</logic:equal></td>	  	    	 
	</tr>  	
</table>
<%-- END HEADING TABLE --%>
<div class="spacer"></div>
<logic:equal  name="juvenileGEDProgramForm" property="action" value="<%=UIConstants.SUMMARY%>">
<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="98%" border="0">
		<tr>
			<td>
				<ul>
					<li>Review information and select Finish to add GED Program information.</li>	  		
					<li>Select Back button to return to Create GED Program page.</li>
				</ul>
			</td>
		</tr>
	</table>
<%-- END INSTRUCTION TABLE --%>
</logic:equal>
<logic:equal  name="juvenileGEDProgramForm" property="action" value="<%=UIConstants.CONFIRM%>">
<%-- BEGIN CONFIRMATION MESSAGE TABLE --%>
	<table width="98%" border="0">
		<tr>
			<td class="confirm">GED Program successfully added.</td>
		</tr>
	</table>
<%-- END CONFIRMATION MESSAGE TABLE --%>
</logic:equal>
<%-- BEGIN DISPLAY PROFILE HEADER --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%-- END DISPLAY PROFILE HEADER --%>
<div class="spacer"></div>
<%-- BEGIN DETAIL TABLE --%>  
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
<%-- BEGIN GREEN TABS TABLE --%>			
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="interviewinfotab"/>
							<tiles:put name="juvenileNum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />							
						</tiles:insert>	
					</td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
			</table>
<%-- END GREEN TABS BORDER TABLE --%>				
<%-- BEGIN GREEN TABS BORDER TABLE --%>			
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">
						<div class="spacer"></div>
<%-- BEGIN BLUE TABS TABLE --%>							
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
										<tiles:put name="tabid" beanName="juvenileTraitsForm" beanProperty="categoryName" />
										<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
									</tiles:insert>	
								</td>
							</tr>
							<tr>
								<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
							</tr>
						</table>
<%-- END BLUE TABS TABLE --%>	
<%-- BEGIN BLUE TABS BORDER TABLE --%>							
						<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td valign="top" align="center">
									<div class="spacer"></div>
									<table width='98%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign="top">
												<tiles:insert page="../caseworkCommon/educationTabs.jsp" flush="true">
													<tiles:put name="tabid" value="school"/>
													<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
												</tiles:insert>	
											</td>
										</tr>
										<tr>
											<td bgcolor='#ff6666'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
										</tr>
									</table>
<%-- BEGIN RED TABS BORDER TABLE --%>									
									<table width='98%' align="center" cellpadding="0" cellspacing="0" class="borderTableRed"> 
										<tr>
											<td valign="top" align="center">
												<div class="spacer"></div>
<%-- BEGIN GED PROGRAM DETAILS TABLE --%>
												<table width='98%' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
													<tr>
														<td colspan="2" class="detailHead"><bean:message key="prompt.GEDProgram" /></td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.GEDProgram" /></td>
														<td class="formDe"><bean:write name="juvenileGEDProgramForm" property="programDesc" /></td>
													</tr>
													<logic:notEqual name="juvenileGEDProgramForm" property="otherProgramDesc" value="">
						    							<tr>	
						    								<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.other" /> <bean:message key="prompt.GEDProgram" /></td>
						    								<td class="formDe"><bean:write name="juvenileGEDProgramForm" property="otherProgramDesc" /></td>
						    							</tr>
					    							</logic:notEqual>
													<tr>								
														<td class="formDeLabel"><bean:message key="prompt.enrollmentDate" /></td>
														<td class="formDe"><bean:write name="juvenileGEDProgramForm" property="enrollmentDateStr" /></td>												
													</tr>	
													<tr>	
														<td class="formDeLabel"><bean:message key="prompt.verifiedDate" /></td>
														<td class="formDe"><bean:write name="juvenileGEDProgramForm" property="verificationDateStr" /></td>
													</tr>																								
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.enrollmentStatus" /></td>	
														<td class="formDe"><bean:write name="juvenileGEDProgramForm" property="enrollmentStatusDesc" /></td>
													</tr>							
													<tr>
														<td class="formDeLabel" width="1%" nowrap="nowrap" ><bean:message key="prompt.participation" /> <bean:message key="prompt.level" />&nbsp;&nbsp;&nbsp;</td>								
														<td class="formDe"><bean:write name="juvenileGEDProgramForm" property="participationLevelDesc" /></td>
													</tr>	
													<tr>
														<td class="formDeLabel">Has the youth completed their <bean:message key="prompt.GED" />?</td>								
														<td class="formDe">
															<logic:equal name="juvenileGEDProgramForm" property="gedCompleted" value="true">
																<bean:message key="prompt.yes" />
															</logic:equal>
															<logic:equal name="juvenileGEDProgramForm" property="gedCompleted" value="false">
																<bean:message key="prompt.no" />
															</logic:equal>
														</td>				
													</tr>						
					    							<tr>								
														<td class="formDeLabel"><bean:message key="prompt.completionDate" /></td>
														<td class="formDe"><bean:write name="juvenileGEDProgramForm" property="completionDateStr" /></td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.GEDAwarded" /></td>
														<td class="formDe">
															<logic:equal name="juvenileGEDProgramForm" property="awarded" value="true">
																<bean:message key="prompt.yes" />
															</logic:equal>
															<logic:equal name="juvenileGEDProgramForm" property="awarded" value="false">
																<bean:message key="prompt.no" />
															</logic:equal>
														</td>												
													</tr>
													<logic:equal name="juvenileGEDProgramForm" property="awarded" value="true">
						    							<tr>									
															<td class="formDeLabel"><bean:message key="prompt.GEDAwarded" /> <bean:message key="prompt.date" /></td>
															<td class="formDe"><bean:write name="juvenileGEDProgramForm" property="awardedDateStr" /></td>
														</tr>																															
													</logic:equal>																																														
								                </table>
<%-- END GED PROGRAM DETAILS TABLE --%>		
												<div class="spacer"></div>			
<%-- BEGIN BUTTON TABLE --%>
												<table width="98%">
													<tr>
														<td align="center">
															<logic:equal  name="juvenileGEDProgramForm" property="action" value="<%=UIConstants.SUMMARY%>">
																<html:submit property="submitAction" onclick="disableSubmit(this, this.form)">
																	<bean:message key="button.back" />
																</html:submit> 
																<html:submit property="submitAction" onclick="disableSubmit(this, this.form)">
																	<bean:message key="button.finish"/>
																</html:submit> 
																<html:submit property="submitAction" onclick="disableSubmit(this, this.form)">
																	<bean:message key="button.cancel"/>
																</html:submit>
															</logic:equal>
															<logic:equal  name="juvenileGEDProgramForm" property="action" value="<%=UIConstants.CONFIRM%>">
																<html:submit property="submitAction" onclick="disableSubmit(this, this.form)">
																	<bean:message key="button.returnToSchoolDetails" />
																</html:submit> 
															</logic:equal>	
														</td>
													</tr>
												</table>
<%-- END BUTTON TABLE --%>												
												<div class="spacer"></div>
											</td>
										</tr>
									</table>	
<%-- END RED TABS BORDER TABLE --%>									
									<div class="spacer"></div>
								</td>
							</tr>
						</table>
<%-- BEGIN BLUE TABS BORDER TABLE --%>								
						<div class="spacer"></div>
					</td>
				</tr>
			</table>
<%-- END GREEN TABS BORDER TABLE --%>				
			<div class="spacer"></div>	
		 </td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>