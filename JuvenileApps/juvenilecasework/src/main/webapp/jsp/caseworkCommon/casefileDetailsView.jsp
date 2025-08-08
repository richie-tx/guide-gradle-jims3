<!DOCTYPE HTML>

<%-- Used to display casefile traits details off Traits Tab --%>
<%--MODIFICATIONS --%>
<%-- 06/21/2005		DWilliamson	Create JSP --%>
<%-- 10/22/2015 Richard Capestani #30817 MJCW: PROD Juv Profile > Drugs Tab > Add Drug - 4 Tiny Squares Are Visible (IE11 conversion) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>



<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- casefileDetailsView.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">

<CENTER><font size="3"><b>Casefile Details</b></font></CENTER>
<table width="98%" border="0">
   <tr>
     <td>
	  <ul>
        <li>Click Close to close this window.</li>       
	  </ul>	</td>
  </tr>
</table>

<%-- BEGIN HEADING TABLE --%>

<%-- END HEADING TABLE --%>
<%--juv profile header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%--juv profile header end--%>


<%-- BEGIN CASEFILE TABLE --%>
<div class='spacer'></div>
<CENTER><tiles:insert page="../caseworkCommon/casefileDetailsTable.jsp" ></tiles:insert></CENTER> 
<%-- END CASEFILE TABLE --%>
<div class='spacer'></div>

<CENTER><input type=button value=Close onclick=window.close()></CENTER>

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

