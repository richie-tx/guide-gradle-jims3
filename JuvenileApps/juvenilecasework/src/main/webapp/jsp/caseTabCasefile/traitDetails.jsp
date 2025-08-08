<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<%-- this is for the scrolling Casefiles table --%>

<html:base />
<title><bean:message key="title.heading"/>/traitDetails.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>

<%--HELP JAVASCRIPT FILE --%> 
<%-- <SCRIPT SRC="../js/help.js" /> --%>
</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body>
<html:form action="/displayJuvenileBriefingDetails" target="content">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header">Juvenile Briefing  - Traits Details</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select the <b>Close Window</b> button to close this window.</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign='top'>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
			  <tr>
			    <td>
      			<table width='100%' border="0" cellpadding="2" cellspacing="1">
							<tr>
								<td class='detailHead' nowrap='nowrap' colspan='2'>Trait Details</td>
							</tr>
							<tr>
								<td class='formDeLabel'>Trait Type</td>
								<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="currentTrait.traitTypeName"/></td>
							</tr>
							<tr>
								<td class='formDeLabel' width='1%' nowrap="nowrap">Trait Type Description</td>
								<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="currentTrait.traitTypeDescription" /></td>
							</tr>
							<tr>
								<td class='formDeLabel'>Trait Status</td>
								<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="currentTrait.status"/></td>
							</tr>
							<tr>
								<td class='formDeLabel' colspan='2'>Comments</td>
							</tr>
							<tr>
								<td class='formDe' colspan='2'><bean:write name="juvenileBriefingDetailsForm" property="currentTrait.traitComments"/></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<div class='spacer'></div>
			<table width='100%' border="0" cellpadding="2" cellspacing="0">
				<tr>
					<td align="center" colspan='2'>
						<input type="button" value="Close Window" onClick="window.close();">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%-- END  TABLE --%>

</html:form>

<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
