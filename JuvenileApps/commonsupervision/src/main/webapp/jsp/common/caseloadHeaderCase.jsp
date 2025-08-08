<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title>Common Supervision - common/caseloadHeaderCase.jsp</title>
</head>
	<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#cccccc">
		<tr>
			<td class="headerLabel"><bean:message key="prompt.name" /></td>
			<td class="headerData"><bean:write name="caseAssignmentForm" property="superviseeNameStr" /></td>
			<td class="headerLabel"><bean:message key="prompt.SPN" /></td>
			<td class="headerData" colspan="3"><bean:write name="caseAssignmentForm" property="defendantId" /></td>
		</tr>
		<tr>
			<td class="headerLabel"><bean:message key="prompt.officer" /></td>
			<td class="headerData"><bean:write name="caseAssignmentForm" property="officerNameStr" /></td>
			<td class="headerLabel"><bean:message key="prompt.LOS" /></td>
			<td class="headerData" colspan="3"><bean:write name="caseAssignmentForm" property="levelOfSupervision" /></td>
		</tr>
		<tr>
            <td class="headerLabel"><bean:message key="prompt.offense" /></td>
			<td class="headerData"><bean:write name="caseAssignmentForm" property="offenseDesc" /></td>
			<td class="headerLabel"><bean:message key="prompt.programUnit" /></td>
			<td class="headerData" colspan="3"><bean:write name="caseAssignmentForm" property="programUnitName" /></td>
		</tr>
		<tr>
            <td class="headerLabel"><bean:message key="prompt.CDI" /></td>
			<td class="headerData"><bean:write name="caseAssignmentForm" property="cdi" /></td>
			<td class="headerLabel"><bean:message key="prompt.case" /></td>
			<td class="headerData"><bean:write name="caseAssignmentForm" property="caseNum" /></td>
			<td class="headerLabel"><bean:message key="prompt.CRT" /></td>
			<td class="headerData"><bean:write name="caseAssignmentForm" property="courtNumber" /></td>
		</tr>
	</table>			
</html:html>