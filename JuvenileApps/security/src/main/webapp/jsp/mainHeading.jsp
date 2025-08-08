<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<!--MODIFICATIONS -->
<!-- LDeen	03/29/04	Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<!--msp:login --/>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title>
<bean:message key="title.heading"/> - Heading</title>
<link href="../css/navmenu.css" rel="stylesheet" type="text/css">
</head>

<!--BEGIN BODY TAG-->
<body leftmargin="0" topmargin="0">
<form name="jims2heading">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" bgcolor="#000080" align="center" height="26">
       <font size=4 color="#d0d0d0" face=arial>Justice Information Management Systems</font>
    </td>
  </tr>
</table>
  
<table background="../images/BGHeaderShadow.gif" width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="1" nowrap><img src="../images/shim.gif" width="1" height="26"></td>
	<td width="5%" align="right" nowrap class="subhead">User ID:&nbsp;</td>
	<td width="10%" align="left" nowrap class="bodyText">UVJBU&nbsp; </td>
	<td width="5%" align="right" nowrap class="subhead">User Name:&nbsp; </td>
	<td width="20%" align="left" nowrap class="bodyText">J. B. User</td>
	<td width="40%" align="left" nowrap class="bodyText"><jims:headingDate /></td>
	   <!-- non-functional Logout and Help -->
   	<td width="10%" align="right" nowrap class="subhead">Logout&nbsp;</td>
	<td width="7%" align="right" nowrap class="subhead">Help&nbsp;</td>
  </tr>
</table>
</form>
</body>
</html>
