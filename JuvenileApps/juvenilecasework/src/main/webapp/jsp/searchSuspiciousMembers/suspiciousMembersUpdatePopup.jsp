<!DOCTYPE HTML>

<%-- Used for searching of juvenile casefiles in MJCW --%>
<%--MODIFICATIONS --%>
<%-- CShimek		12/06/2011	Create JSP --%>
<%-- RCapestani 10/15/2015  Task #30777 MJCW: IE11 conversion of "Data Admin-HCJPD"  link on UILeftNav (UI-Suspicious Members) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>



<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - suspiciousMembersUpdatePopup.jsp</title>

<%-- STRUTS VALIDATION --%>
<html:javascript formName="casefileSearchForm" />
<%-- JAVASCRIPT FILES FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displaySuspiciousMembersUpdateSummary" target="content">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td class="header" align="center">
				<bean:message key="prompt.suspiciousMembers" /> -  Matching <bean:message key="prompt.familyMember" /> List
			</td>
		</tr>
	</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="98%" border="0">
		<tr>
			<td>
				<ul>
					<li>Click 'Close Window' button to continue.</li>					
				</ul>
			</td>
		</tr>
	</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN MATCHING MEMBER LIST TABLE --%>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr>
			<td class="detailHead"><bean:message key="prompt.member#" /></td>
			<td class="detailHead"><bean:message key="prompt.name" /></td>
			<td class="detailHead"><bean:message key="prompt.sex" /></td>
			<td class="detailHead"><bean:message key="prompt.dateOfBirth" /></td>
			<td class="detailHead"><bean:message key="prompt.ssn" /></td>
		</tr>
		<logic:iterate id="memList" name="suspiciousMemberForm" property="similarMembers" indexId="index1">
			<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
				<td><bean:write name="memList" property="memberNum"/></td>
				<td><bean:write name="memList" property="fullNamelfm"/></td>
				<td><bean:write name="memList" property="sex"/></td>
				<td><bean:write name="memList" property="dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
				<td><bean:write name="memList" property="formattedSSN"/></td>
			</tr>
		</logic:iterate>
	</table>
<%-- END FAMILY MEMBER SELECT TABLE --%> 
	<div class="spacer4px"></div>	
<%-- BEGIN BUTTON TABLE --%>		
	<table align="center" border="0" width="100%">
		<tr>
			<td align="center">
				<input type="button" name="closeMe" value="Close Window" onClick="window.close();">	
			</td>
		</tr>
	</table>
<%-- END BUTTON TABLE --%> 
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>