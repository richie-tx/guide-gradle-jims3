<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 02/25/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 04/14/2010 C Shimek          - #64845 revise block title and layout -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/caseHistory/violationReports/vrRecommendationsSummary.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/submitVRRecommendationsCreateUpdate" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Violation_Reports/CSCD_Violation_Reports.htm#|27">
<div align="center">
<!-- BEGIN PAGE TABLE -->
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->		
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
						</tiles:insert>
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
<!-- END BLUE TABS TABLE -->				
<!-- BEGIN BLUE BORDER TABLE -->	
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<!-- BEGIN SUPERVISEE DETAILS TABLE -->
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td bgcolor="#cccccc" colspan="2">
									<tiles:insert page="../../../common/caseloadHeaderCase.jsp" flush="true">
									</tiles:insert> 
								</td>
							</tr>
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
<!-- END SUPERVISEE DETAILS TABLE -->						
<!-- BEGIN GREEN TABS TABLE -->
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<tiles:insert page="../../../common/caseloadCSCDSubTabs.jsp" flush="true">
										<tiles:put name="tab" value="CasesTab" />
									</tiles:insert>
								</td>
							</tr>
							<tr>
								<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
<!-- END GREEN TABS TABLE -->	
<!-- BEGIN GREEN BORDER TABLE -->						
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;- <bean:message key="title.violationReport"/>&nbsp;-
												<bean:message key="prompt.recommendations"/>&nbsp;<bean:message key="title.summary"/>
											</td>
										</tr>
									</table>
<!-- END HEADING TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
													<li>Review entry. Click Finish.</li>
												</ul>
											</td>
										</tr>
									</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN DETAILS TABLE -->
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td><bean:message key="prompt.suggestedCourtActions"/></td>
										</tr>
										<tr>
											<td>
												<logic:iterate id="vrIter" name="violationReportsForm" property="create2ElementsList" indexId="index">
   											    	<table width="100%" border="0" cellpadding="2" cellspacing="1">
													  	<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
															<td><bean:write name="vrIter" property="description" /></td>
														</tr>
													</table>
                                              	</logic:iterate>  
											</td>
										</tr>
										<tr class="formDeLabel">
											<td><bean:message key="prompt.statusCommentsandRecommendations"/></td>
										</tr>
										<tr>
											<td class="formDe">
												<bean:write name="violationReportsForm" property="create1Comments"/>
											</td>
										</tr>
										
									</table> 
<!-- END DETAILS TABLE -->
								
<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
										</tr>
										<tr>
											<td align="center">
												<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.finish" /></html:submit>
												<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
											</td>
										</tr>
									</table>
<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table>
<!-- END GREEN BORDER TABLE -->
						<br>
					</td>
				</tr>
			</table>
<!-- END BLUE BORDER TABLE -->
		</td>
	</tr>
</table>
<!-- END PAGE TABLE -->
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>