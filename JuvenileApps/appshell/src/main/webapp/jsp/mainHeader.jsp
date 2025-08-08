<!DOCTYPE HTML>
<html>
<!--MODIFICATIONS -->
<!-- LDeen	03/29/04	Create JSP -->

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

<!-- STYLE SHEET LINK -->

<html:base />
<title><bean:message key="title.heading"/>-Header</title>
<link href="../css/appshell.css" rel="stylesheet" type="text/css">
</head>

<body topmargin="0" leftmargin="0" rightmargin="0" bgcolor="#f0f0f0">
<form name="navigation">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" bgcolor="#000080" align="center" height="26">
       <font size=4 color="#d0d0d0" face=arial>Main Menu</font>
    </td>
  </tr>
</table>
    </form>
  </body>
</html>