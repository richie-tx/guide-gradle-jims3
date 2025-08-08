<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!--MODIFICATIONS -->
<!-- LDeen	04/13/04	Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<html>
<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<!--msp:login /-->
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="../css/base.css" />
<html:base />
<title><bean:message key="title.heading"/> - Main Frameset</title>
</head>

<frameset rows="52,*" framespacing="0" frameborder="no" border="0">
   <frame src="mainHeading.jsp" name="heading" noresize scrolling="no">
   <frameset cols="160,*" framespacing="0" frameborder="no" border="0">
       <frameset rows="28,60%,28,*">
         <frame src="mainNavigationHeader.jsp" name="navheader" scrolling="no" noresize>
         <frame src="mainNavigationMenu.jsp" name="navmenu" scrolling="yes" >
         <frame src="mainShortcutHeader.jsp" name="shortheader" scrolling="no" noresize>
         <frame src="mainShortcutMenu.jsp" name="shortcutmenu" scrolling="yes">
       </frameset>
       <frame src="mainMenu.jsp" name="content">
   </frameset>

<%-- Modified Jim Fisher 4-15-2004 Moved the 'noframes' tag from outside the frameset --%>
	<noframes>Sorry, this page requires a frame-capable browser.</noframes>

</frameset>

</html>