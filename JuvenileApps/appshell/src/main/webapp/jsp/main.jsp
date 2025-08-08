<!DOCTYPE HTML>


<html>
<!--MODIFICATIONS -->
<!-- LDeen	04/13/04	Create JSP -->
<!-- UGopinath 02/22/04	Modified to remove frames on page reload-->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<!--msp:login /-->
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<script language="JavaScript"><!--
if (parent != self)
    top.location.href = location.href;
//--></script>



<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/appshell.css" />
<html:base />


<title><bean:message key="title.heading"/> - Main Frameset</title>
</head>
<frameset rows="52,*" frameborder="yes" border="0" framespacing="0" >
   <html:frame frameName="heading" action="/displayHeader.do" scrolling="no"/>
   <frameset cols="160,*" frameborder="yes" border="2" bordercolor="#0000ff" framespacing="0" style="cursor: col-resize">
       <frameset rows="25,*" frameborder="no" border="1" style="border: 1px solid">
          <html:frame frameName="navheader" action="/displayNavigationHeader.do" scrolling="no"/>
          <frameset cols="160,*" frameborder="yes" border="0" framespacing="0">
       <!--frameset rows="50%,50%" frameborder="no" border="1" style="border: 1px solid #f0f0f0" onmouseover="this.style.cursor= 'e-resize'"-->
         <html:frame frameName="navmenu" action="/displayFeatures.do" scrolling="auto" />
         <!--html:frame frameName="shortcutmenu" forward="mainShortcutMenu.jsp" scrolling="auto" /-->
         <!--/frameset-->
         </frameset>

       </frameset>
       <!--frame name="mainFrame" src="mainMenu.jsp" scrolling="auto"/-->
       <!--frame name="content" src="mainMenu.jsp"/-->
        <html:frame frameName="content"  action="/displayMainContent"/>
   </frameset>
   <noframes>
     Sorry, this page requires a frame-capable browser
   </noframes>
</frameset>





</html>