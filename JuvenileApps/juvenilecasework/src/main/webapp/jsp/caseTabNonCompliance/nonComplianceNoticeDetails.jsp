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
<title><bean:message key="title.heading"/> - nonComplianceCreateNoticeDetails.jsp</title>

<script type="text/javaScript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework.js"></script>

</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="displayJuvenileCasefileNonComplianceNoticeDetails" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|243">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Non-Compliance Notice Details</td>
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
<%-- BEGIN CONFIRMATION TABLE --%>
<table width="98%" align="center">
	<tr>
		<td Class="confirm">
			<bean:write name="juvenileNonComplianceForm" property="confirmationMsg" />
		</td>
	</tr>
</table>
<%-- END CONFIRMATION TABLE --%> 
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION/REQUIRED INFORMATION TABLE --%>
<table width="98%" align="center">
	<tr>
		<td>
			<ul>
				<li>Click Print Notice button to print Non-Compliance Notice.</li>
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
<%-- BEGIN DETAILS TABLE --%>
 															<tiles:insert page="/jsp/caseTabNonCompliance/nonComplianceDetailsTile.jsp" flush="true">
															</tiles:insert>
<%-- END DETAILS TABLE --%>
			                  								<div class='spacer'></div>
<%-- BEGIN SIGNATURE STATUS INFO TABLE --%>	
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
															</table>
<%-- END SIGNATURE STATUS INFO TABLE --%>	
<%-- BEGIN COMPLETION INFO TABLE --%>			                  								
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
																	<td class="formDe"><bean:write name="juvenileNonComplianceForm" property="completionDate" formatKey="date.format.mmddyyyy"/></td>
																</logic:equal>
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
																<tr>
																	<td class="formDeLabel" width="1%" nowrap="nowrap" valign="top">
																		<bean:message key="prompt.completion" /> <bean:message key="prompt.comments" />
																	</td>
																	<td class="formDe"><bean:write name="juvenileNonComplianceForm" property="juvenileCompletedComments" /></td>
																</tr>
																<tr>
																	<td class="formDeLabel"><bean:message key="prompt.parentInformed" />?</td>
																	<td class="formDe">
																		<logic:equal name="juvenileNonComplianceForm" property="parentalNotified" value='true'>
																			YES
																		</logic:equal>
																		<logic:equal name="juvenileNonComplianceForm" property="parentalNotified" value='false'>
																			NO
																		</logic:equal>
																	</td>
																</tr>
															</table>
<%-- END COMPLETION INFO TABLE --%>																			
															<div class='spacer'></div>	
<%-- BEGIN BUTTON TABLE --%>
															<table border="0" width="100%">
																<tr>
																	<td align="center">
																		<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
																		<html:submit property="submitAction"> <bean:message key="button.printNotice" /></html:submit>
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