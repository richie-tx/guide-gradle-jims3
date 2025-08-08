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
<title>Common Supervision - common/serviceProviderHeader.jsp</title>
</head>

<body>
	<!-- BEGIN  TABLE -->
<table cellpadding=1 cellspacing=0 border=0 width='98%'>
  <tr>
	<td bgcolor='#cccccc'>
		<table width='100%' border=0 cellpadding=2 cellspacing=1>
			<tr>
				<td class="headerLabel" width='1%' nowrap>Provider <bean:message key="prompt.name" /></td>
				<td colspan=3 class=headerData><bean:write name="cscServiceProviderForm" property="name"/></td>
			</tr>
			<tr>
				<td class="headerLabel"><bean:message key="prompt.start" /> <bean:message key="prompt.date" /></td>
				<td class="headerData"><bean:write name="cscServiceProviderForm" property="startDateAsStr" formatKey="date.format.mmddyyyy"/></td>
				<td class="headerLabel" width='1%' nowrap><bean:message key="prompt.inHouse" /></td>
				<td class="headerData"><bean:write name="cscServiceProviderForm" property="inHouseAsStr"/></td>
			</tr>
		</table>
	</td>
  </tr>
</table>			
</body>
</html:html>