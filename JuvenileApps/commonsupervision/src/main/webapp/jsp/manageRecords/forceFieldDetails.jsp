<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 11/17/2008 C Shimek   Modified version of administerCaseloadCSCD/assessments/forceFieldDetails.jsp for SPn Splits -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!-- jims2 CUSTOM TAG lIBRARIES FOR QUESTION ANSWER DISPLAY -->
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@page import="naming.CSCAssessmentConstants"%>
<%@page import="naming.Features" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - manageRecords/forceFieldDetails.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displayForceFieldDetails" target="content">
<div align="center">
<!-- BEGIN MAIN TABLE -->	
	<table width="98%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
<!-- BEGIN HEADING TABLE -->
				<table width="100%">
					<tr>
						<td align="center" class="header">
							<bean:message key="title.CSCD"/>&nbsp;-
							<bean:message key="prompt.forceFieldAnalysis"/>&nbsp;<bean:message key="title.details"/>
						</td>		
					</tr>
				</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
				<table width="98%" align="center">							
					<tr>
						<td align="center" class="errorAlert"><html:errors/></td>
					</tr>		
				</table>
<!-- END ERROR TABLE -->
<!-- BEGIN INSTRUCTIONS TABLE -->
				<table width="98%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<ul>
	                            <li>Click a version number to view prior version details.</li>
	                        </ul>
						</td>
					</tr>
				</table>									
<!-- END INSTRUCTIONS TABLE -->
<!-- BEGIN VERSION TABLE -->
<%-- 				<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
					<tr class="detailHead">
						<td><bean:message key="prompt.view"/>&nbsp;<bean:message key="prompt.priorVersions"/>&nbsp;</td>
					</tr>
					<tr>
						<td>
							<table width="100%" cellpadding="4" cellspacing="1">
								<tr class="formDeLabel">
									<td nowrap width="5%"><bean:message key="prompt.version"/> #</td>
									<td><bean:message key="prompt.transaction"/>&nbsp;<bean:message key="prompt.date"/></td>
									<td><bean:message key="prompt.assessment"/>&nbsp;<bean:message key="prompt.date"/></td>
								</tr>
								<logic:iterate id="eachAssessmentVersion" name="forceFieldAssessmentForm" property="versionDetailsList" indexId="index">  
									<bean:define id="selectedVersion" type="java.lang.String" name="forceFieldAssessmentForm" property="versionId"></bean:define>
									<logic:notEqual name="eachAssessmentVersion" property="versionNumber" value="<%=selectedVersion%>">                                                                         
										<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
											<td align=center><a href='/<msp:webapp/>displayForceFieldDetails.do?submitAction=View+Version&selectedVersionNumber=<bean:write name="eachAssessmentVersion" property="versionNumber"/>'><bean:write name="eachAssessmentVersion" property="versionNumber"/></a></td>
											<td><bean:write name="eachAssessmentVersion" property="transactionDateStr"/></td>
											<td><bean:write name="eachAssessmentVersion" property="assessmentDateStr" /></td>
										</tr>
									</logic:notEqual>
								</logic:iterate>
							</table>
						</td>
					</tr>
				</table>  --%>
<!-- END VERSION TABLE  -->
				<div class=spacer4px></div>
<!-- BEGIN ASSESSMENT DATE TABLEt-->
				<table width="98%" cellpadding="4" cellspacing="1" class=borderTableBlue>
					<tr>
						<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.version" /> #</td>
						<td class="formDe" nowrap width=25%><bean:write name="forceFieldAssessmentForm" property="versionId"/></td>
					
						<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.assessmentDate" /></td>
						<td class="formDe">
							<bean:write name="forceFieldAssessmentForm" property="assessmentDateStr" />
						</td>
					</tr>
				</table>
<!-- END ASSESSMENT DATE TABLE -->
				<div class=spacer4px></div>
<!-- BEGIN FORCE FIELD ASSESSMENT TABLE -->	
				<table width="98%" border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
					<tr>
						<td>
							<table width='100%' cellpadding=2 cellspacing=1>
								<jims2:cscquestions name="forceFieldAssessmentForm" property="forceFieldQuestionsList" type="summary" columns="1"/>
							</table>
					 	 </td>
				 	 </tr>
			 	 </table>
 <!-- END FORCE FEIDL ASSESSEMENT TABLE -->	
				<div class=spacer4px></div>
<!-- BEGIN BUTTON TABLE -->
				<table width=98% border="0" cellpadding="2" cellspacing="1">
					<tr>
						<td align="center">
							<input type="button" name="close" value="Close Window" onclick="window.close()") >
						</td>   
					</tr>
				</table>
<!-- END BUTTON TABLE -->
			</td>
		</tr>
	</table>
<!-- END  MAIN TABLE -->
</div>
<br>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>