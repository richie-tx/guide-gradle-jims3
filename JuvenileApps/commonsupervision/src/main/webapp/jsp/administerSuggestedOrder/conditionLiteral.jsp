<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/26/2005	 Hien Rodriguez - Create JSP -->

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

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - administerSuggestedOrder/conditionLiteral.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<div align="center">
	<br><br>
<table width="98%" border="0" cellpadding="2" cellspacing="0" >
	<tr>
		<td class="formDeLabel" align="center"><bean:write name="suggestedOrderForm" property="conditionName" /></td>
	</tr>	
	<tr>		
		<td class="formDe"><bean:write name="suggestedOrderForm" property="conditionLiteralPreview" filter="false"/></td>		
	</tr>
</table>
<br><br>
</div>														
<!-- BEGIN BUTTON TABLE -->
<table border="0" width="100%">
  	<tr>
    	<td align="center">
    		<input type="button" value="<bean:message key='button.close'/>" name="close" onClick="javascript:window.close()">      	
    	</td>
  	</tr>
</table>
<!-- END BUTTON TABLE -->
					

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>

