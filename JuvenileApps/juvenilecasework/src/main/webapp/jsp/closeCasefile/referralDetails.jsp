<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 10/16/2007		UGopinath Create JSP--%>
<%-- 09/01/2015     RCapestani	#29685 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Casefile Closing tab UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- interviewDetails.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>

<body>

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
  <tr>
    <td align="center" class="header">Juvenile Casework - Casefile Referral Details</td>
  </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<div class=spacer></div>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
       <li>Select Close Window button to close this window.</li>
      </ul>
	  </td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->


<%String mode = "CLM";%>
<tiles:insert page="../caseworkCommon/referralDetailsTile.jsp" flush="true">
  <tiles:put name="assignedReferralsForm" beanName="assignedReferralsForm"/>	
  <tiles:put name="mode" value="<%=mode%>"/>	
</tiles:insert>

<%-- begin button table --%>
<div class=spacer></div>
<table border="0" cellpadding=1 cellspacing=1 align=center>
  <tr>
    <td align="center"><input type=button value='Close Window' onclick="window.close();"></td>
  </tr>
</table>
<%-- end button table --%>
	
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>