<!DOCTYPE HTML>

<%-- Used to display casefile traits details off Traits Tab --%>
<%--MODIFICATIONS --%>
<%-- 06/21/2005		DWilliamson	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>



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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- casefileDetailsTable.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<%--html:form action="/displayJuvenileCasefileDetails.do"  target="content"--%>

<%-- BEGIN CASEFILE TABLE --%>
<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td valign=top class=detailHead>Casefile</td>
	</tr>
	<tr>
		<td>
			<table width='100%' cellpadding=4 cellspacing=1>
				<tr>
					<td valign=top class=formDeLabel width='1%' nowrap><bean:message key="prompt.activationDate" />/<bean:message key="prompt.time" /></td>
					<td valign=top class=formDe nowrap><bean:write name="juvenileCasefileForm" property="activationDate" formatKey="datetime.format.mmddyyyyHHmmAMPM"/></td>
					<td valign=top class=formDeLabel width="1%" nowrap><bean:message key="prompt.expectedSupervisionEndDate" /></td>
					<td valign=top class="formDe" nowrap><bean:write name="juvenileCasefileForm" property="expectedSupervisionEndDate" formatKey="date.format.mmddyyyy"/></td>
				</tr>
				<tr>
					<td valign=top class=formDeLabel nowrap><bean:message key="prompt.supervisionOutcome" /></td>
					<td valign=top class=formDe colspan="3" nowrap><bean:write name="juvenileCasefileForm" property="supervisionOutcomeDesc"/></td>
				</tr>										
				<tr>
					<td class=formDeLabel valign=top nowrap><bean:message key="prompt.closingEvaluation" /></td>
					<td class=formDe colspan="3" nowrap><bean:write name="juvenileCasefileForm" property="closingEvaluation"/></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%-- END CASEFILE TABLE --%>				
<%--/html:form--%>
</html:html>
