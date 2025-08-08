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
<title>Common Supervision - common/adultSupervisionHeader.jsp</title>
</head>
<body>
	<table width="98%" border="0" cellpadding="1" cellspacing="0">
		<tr>
			<td class="headerLabel" width="1%">Name</td>
			<td class="headerData" colspan="5"><bean:write name="partyForm" property="partyName.formattedName" /></td>
        </tr> 
        <tr>
			<td class="headerLabel" width="1%">SPN</td>
			<td class="headerData"><bean:write name="partyForm" property="spn" /></td>
			<td class="headerLabel" width="1%">SSN</td>
			<td class="headerData"><bean:write name="partyForm" property="ssn" /></td>
			<td class="headerLabel" width="1%">DOB</td>
			<td class="headerData"><bean:write name="partyForm" property="dateOfBirth" formatKey="date.format.mmddyyyy" /></td>
		</tr>
		<tr>
			<td class="headerLabel" width="1%">Race</td>
			<td class="headerData"><bean:write name="partyForm" property="raceId" /></td>
			<td class="headerLabel" width="1%">Sex</td>
			<td class="headerData"><bean:write name="partyForm" property="sexId" /></td>
			<td class="headerLabel" width="1%">SID</td>
			<td class="headerData"><bean:write name="partyForm" property="sid" /></td>
		</tr>
	</table>			
</body>
</html:html>