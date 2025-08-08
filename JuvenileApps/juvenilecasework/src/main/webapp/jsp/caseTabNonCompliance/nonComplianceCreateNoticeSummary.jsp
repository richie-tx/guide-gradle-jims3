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
<title><bean:message key="title.heading"/> - nonComplianceCreateNoticeSummary.jsp</title>

<script type="text/javaScript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework.js"></script>
</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="submitJuvenileCasefileNonComplianceNoticeCreate" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|141">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Create Non-Compliance Notice
			<logic:equal name="juvenileNonComplianceForm" property="action" value="summary">
				<bean:message key="prompt.summary"/>
			</logic:equal>
		 	<logic:equal name="juvenileNonComplianceForm" property="action" value="confirm">
				<bean:message key="prompt.confirmation"/>
			</logic:equal>	
		</td>
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
			<logic:equal name="juvenileNonComplianceForm" property="action" value="summary">
				<ul>
					<li>Verify all information is correct then click Generate Notice button to create Non-Compliance Notice.</li>
					<li>To make corrections, click Back button.</li>
				</ul>
			</logic:equal>
			<logic:equal name="juvenileNonComplianceForm" property="action" value="confirm">
				<ul>
		<%-- 			<li>Click Print Notice button to print Non-Compliance Notice.</li>   --%>
					<li>Click Notice List button to return to Non-Compliance Notice list.</li>
				</ul>
			</logic:equal>		
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
<%-- BEGIN BUTTON TABLE --%>
															<table border="0" width="100%">
																<tr>
																	<td align="center">
																<%--		<logic:equal name="juvenileNonComplianceForm" property="action" value="summary">  --%>
																			<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
																			<html:submit property="submitAction"> <bean:message key="button.generateNotice" /></html:submit>
																			<html:submit property="submitAction"> <bean:message key="button.noticeList" /></html:submit>
																<%--		<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
																		</logic:equal>  --%>
																<%--	<logic:equal name="juvenileNonComplianceForm" property="action" value="confirm">
																		 	<html:submit property="submitAction"> <bean:message key="button.printNotice" /></html:submit>  
																			<html:submit property="submitAction"> <bean:message key="button.noticeList" /></html:submit>
																		</logic:equal>   --%>
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