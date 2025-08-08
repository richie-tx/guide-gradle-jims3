<!DOCTYPE HTML>

<%-- Used to display casefile details off Casefile Tab --%>
<%--MODIFICATIONS --%>
<%-- 09/27/2011     C Shimek       	Create JSP Activity 70949--%>
<%-- 10/12/2011     C Shimek       	Add PARTIAL check under allowUpdates present --%>
<%-- 07/11/2012 	C Shimek     	#73565 added age > 20 check (juvUnder21) to only allow viewing --%>
<%-- 02/07/2013 	C Shimek     	#74937 removed active case status check on notice left, left edit on create button --%>
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
<title><bean:message key="title.heading"/> - nonComplianceNoticeList.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/casework.js"></script>

</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="handleJuvenileCasefileNonComplianceSelection" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|245">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="prompt.nonCompliance"/> Notice List</td>
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
<table width="100%">
	<tr>		  
		<td align="center" class="confirm"><bean:write name="juvenileNonComplianceForm" property="confirmationMsg"/></td>		  
	</tr>   	  
</table>
<%-- END CONFIRMATION TABLE --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION/REQUIRED INFORMATION TABLE --%>
<table width="98%" align="center">
	<tr>
		<td>
			<ul>
				<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
					<li>Select Generate Date hyperlink to update Signature Status/Completed information.</li>
					<li>Select a 'Create' button to create needed information.</li>
				</logic:equal>
				<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="false">
					<li>Select Generate Date hyperlink to view detail information.</li>
				</logic:equal>
			</ul>			
		</td>
	</tr>
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
											<%--tabs start--%>
												<tiles:insert page="/jsp/caseworkCommon/casefileInfoTabs.jsp" flush="true">
													<tiles:put name="tabid" value="casefiledetailstab"/>
													<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
												</tiles:insert>	
											<%--tabs end--%>
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
								  					<tr  bgcolor='#ff6666'>
            											<td align="left">
            												&nbsp;<a href='/<msp:webapp/>displayJuvenileCasefileNonComplianceNoticeList.do?submitAction=Link'>Notice List</a> <b>|</b>
            												&nbsp;<a href='/<msp:webapp/>displayJuvenileCasefileNonComplianceNoticeHistory.do?submitAction=Link'><bean:message key="prompt.view"/> Notice History</a> 
            											 </td>
            										</tr>
												</table>
<%-- BEGIN RED TABS BORDER TABLE --%>													
												<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
													<tr>
														<td valign='top' align="center">			
                                                 			<div class=spacer></div>
                                                 			<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
	                                                 			<jims2:isAllowed  requiredFeatures='<%=Features.JCW_CF_NONCOMPLIANCE_U%>'>
																	<bean:define id="allowUpdates" value="Yes" />
																</jims2:isAllowed>
															</logic:equal>  
<%-- BEGIN NONCOMPLIANCE NOTICES TABLE --%>
															<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
																<tr>
																	<td valign='top' class="detailHead" colspan="2"><bean:message key="prompt.previous" /> <bean:message key="prompt.nonComplianceNotice" />(s)
																		<logic:empty name="juvenileNonComplianceForm" property="existingNoticesList" >
																			(No existing notice found)
																		</logic:empty>
																	</td>
																</tr>
																<logic:notEmpty name="juvenileNonComplianceForm" property="existingNoticesList">
																	<tr>
																		<td>
																			<table width='100%' cellpadding="4" cellspacing="1">
																				<tr>
																					<td class='formDeLabel' width='1%' nowrap='nowrap'>
																						<bean:message key="prompt.generate" /> <bean:message key="prompt.date" /> 
																						<jims2:sortResults beanName="juvenileNonComplianceForm" results="existingNoticesList" primaryPropSort="generatedDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC" sortId="1" hideMe="true"/>
																					</td>
																					<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.juvenile" />'s <bean:message key="prompt.signatureStatus" /></td>
																					<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.juvenile" /> <bean:message key="prompt.completed" /></td>
													                                <td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.vopLevel" /></td>
																				</tr>
																				<logic:iterate id="notIter" name="juvenileNonComplianceForm" property="existingNoticesList" indexId="index">
																					<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
																						<logic:present name="allowUpdates">
																							<logic:equal name="notIter" property="juvenileCompletedLit" value="NO">
																								<td><a href="/<msp:webapp/>handleJuvenileCasefileNonComplianceSelection.do?submitAction=Link&noticeID=<bean:write name='notIter' property='casefileNonComplianceNoticeId' />"> <bean:write name="notIter" property="generatedDate" formatKey="date.format.mmddyyyy" /></a>&nbsp;&nbsp;<bean:write name="notIter" property="generatedDate" formatKey="time.format.hhmma" /></td>
																							</logic:equal>
																							<logic:equal name="notIter" property="juvenileCompletedLit" value="YES">
																								<td><a href="/<msp:webapp/>displayJuvenileCasefileNonComplianceNoticeDetails.do?submitAction=View&noticeID=<bean:write name='notIter' property='casefileNonComplianceNoticeId' />"> <bean:write name="notIter" property="generatedDate" formatKey="date.format.mmddyyyy" /></a>&nbsp;&nbsp;<bean:write name="notIter" property="generatedDate" formatKey="time.format.hhmma" /></td>
																							</logic:equal>
																							<logic:equal name="notIter" property="juvenileCompletedLit" value="PARTIAL">
																								<td><a href="/<msp:webapp/>handleJuvenileCasefileNonComplianceSelection.do?submitAction=Link&noticeID=<bean:write name='notIter' property='casefileNonComplianceNoticeId' />"> <bean:write name="notIter" property="generatedDate" formatKey="date.format.mmddyyyy" /></a>&nbsp;&nbsp;<bean:write name="notIter" property="generatedDate" formatKey="time.format.hhmma" /></td>
																							</logic:equal>
																							<logic:equal name="notIter" property="juvenileCompletedLit" value="PENDING">
																								<td><a href="/<msp:webapp/>handleJuvenileCasefileNonComplianceSelection.do?submitAction=Link&noticeID=<bean:write name='notIter' property='casefileNonComplianceNoticeId' />"> <bean:write name="notIter" property="generatedDate" formatKey="date.format.mmddyyyy" /></a>&nbsp;&nbsp;<bean:write name="notIter" property="generatedDate" formatKey="time.format.hhmma" /></td>
																							</logic:equal>
																							
																						</logic:present>
																						<logic:notPresent name="allowUpdates">
																							<td><a href="/<msp:webapp/>displayJuvenileCasefileNonComplianceNoticeDetails.do?submitAction=View&noticeID=<bean:write name='notIter' property='casefileNonComplianceNoticeId' />"> <bean:write name="notIter" property="generatedDate" formatKey="date.format.mmddyyyy" /></a>&nbsp;&nbsp;<bean:write name="notIter" property="generatedDate" formatKey="time.format.hhmma" /></td>
																						</logic:notPresent>
																						<td><bean:write name="notIter" property="noticeSignatureStatusLit" /></td>
																						<td><bean:write name="notIter" property="juvenileCompletedLit" /></td>
																						<td><bean:write name="notIter" property="violationLevelLit" /></td>
																					</tr>	
																				</logic:iterate>
										                       				</table>
				                      									</td>
				                    								</tr>
			                    								</logic:notEmpty>
			                  								</table>
<%-- END NONCOMPLIANCE NOTICES TABLE --%>
															
		                  									<div class='spacer'></div>    
			                  								<logic:present name="allowUpdates">
			                  									<logic:equal name="juvenileNonComplianceForm" property="caseStatusId" value="A">
			                  										<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">	      
<%-- BEGIN BUTTON TABLE --%>
																		<table border='0' width='100%'>
																			<tr>
																				<td align="center">
																					<html:submit property="submitAction"> <bean:message key="button.createNotice" /></html:submit>
																				</td>
																			</tr>
																		</table>
<%-- END BUTTON TABLE --%>
																	</logic:equal>
																</logic:equal>
															</logic:present>
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