<!DOCTYPE HTML>
<html>
<!--Main Navigation Menu for JIMS2 -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<!--msp:login --/>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
</head>
<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="../css/navmenu.css" />
<html:base />
<title>
<bean:message key="title.heading"/> - Navigation Menu</title>

<!--BEGIN BODY TAG-->
<body topmargin="0" leftmargin="0" rightmargin="0">
<!--form name="navigation"-->
<!-- USE CASE TABLE - MANAGE JUVENILE WARRANTS -->
<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  
   <tr>
     <td width="3%">&nbsp;</td>
     <td width="97%">
       <a href="/<msp:webapp/>displayUpdatePasswordAction.do" target="content" class="hyperlink">
		&nbsp;&nbsp;&nbsp;-- Update Password</a>
     </td>    
  
         
   </tr>
</table>



<!--/form-->
<!-- END FORM  -->
<html:errors></html:errors>

</body>
</html>