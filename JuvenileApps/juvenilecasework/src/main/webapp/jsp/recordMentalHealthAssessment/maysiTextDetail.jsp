<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>


<%--BEGIN HEADER TAG--%>
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
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/mentalHealth.js"></script>

<html:base />
<title><bean:message key="title.heading"/> - maysiTextDetail.jsp</title>

<%--GENERAL UTILITY JAVASCRIPT FOR THIS PAGE --%> 
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" >
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|189">

<%-- BEGIN HEADING TABLE --%>
	<%-- Heading table --%>
	<table width="100%">
      <tr>
        <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Mental Health Assessment - MAYSI Text File</td>
      </tr>
  </table>
  
<%-- END HEADING TABLE --%>

<%-- BEGIN HEADING/REQUIRED INFORMATION TABLE --%> 
	 <table width="98%" border="0" align="center">
   <tr>
     <td align="left">
	  <ul>
        <li>Click Back button to return to Mental Health Assessments List.</li>
	  </ul>
     </td>
  </tr>
</table>
<%-- END HEADING/REQUIRED INFORMATION TABLE --%> 
<%-- BEGIN JUVENILE HEADER INCLUDE --%>
<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" >
<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>   
<%-- END JUVENILE HEADER INCLUDE  --%>
<br>
	
<%-- BEGIN MAYSI TEXT FILE INFORMATION TABLE --%>
    <table width="98%" align="center" border="0" cellpadding="2" cellspacing="0" style="borderTableBlue">
      <tr class="detailHead">
        <td class="subhead">MAYSI Text file</td>
      </tr>
      <tr bgcolor="#f0f0f0">
        <td>
          <table width="100%" align="center" cellspacing="0" bgcolor="#f0f0f0">
			 <tr><td><html:textarea name="mentalHealthForm" property="maysiTextFileContents" style="width:100%" rows="32" readonly="true"></html:textarea></td></tr>
        </table>
        </td>
      </tr>
    </table>
<%-- END MAYSI TEXT FILE INFORMATION TABLE --%>
<br>

<%-- BEGIN BUTTON TABLE --%>
  <table width="100%">
      <tr valign="top">
        <td align="center" width="55%">
          <html:button
				property="org.apache.struts.taglib.html.BUTTON"
				onclick="history.go(-1);">
				<bean:message key="button.back"></bean:message>
		  </html:button>&nbsp;
	    </td>
	   
      </tr>
  </table>
<%-- END BUTTON TABLE --%>

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>