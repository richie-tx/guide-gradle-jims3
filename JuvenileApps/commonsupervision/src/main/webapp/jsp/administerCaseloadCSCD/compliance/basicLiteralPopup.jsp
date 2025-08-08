<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/02/2005	 Aswin Widjaja - Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

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

<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/compliance/basicLiteralPopup.jsp</title>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<form name="displayPopupLiteralInfo">
<div align="center">
<!-- BEGIN DISPLAY TABLE -->
<table width="98%" border="0" cellpadding="4" cellspacing="1">
	<tr>
		<td class="formDeLabel" align="center">
			<bean:write name="complianceForm" property="popupLiteralName" filter="false"/>
		</td>
	</tr>
	<tr>  
		<td>
			<bean:write name="complianceForm" property="popupLiteral" filter="false"/>
		</td>
	</tr>
</table>
<!-- END DISPLAY TABLE -->
<br>
<!-- BEGIN BUTTON TABLE -->
<table border="0" width="100%">
  <tr>
    <td align="center">
		<input type="button" value="Print" onclick="window.print()">    
    	<input type="button" value="Close Window" name="close" onClick="window.close()">
    </td>
  </tr> 
</table>
<!-- END BUTTON TABLE -->
</div>
<br>
</form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>