<!DOCTYPE HTML>
<!-- Used to search for warrants to create an associate for. -->
<!--MODIFICATIONS -->
<!-- LDeen		06/24/2004	Create JSP -->
<%-- CShimek    04/12/2006  Updated helpfile reference, no mapId available at this time --%>
<%-- CShimek	12/21/2006  revised helpfile reference value--%>
<%-- CShimek 	03/01/2007	#39097 added multiple submit button logic --%>
<%-- LDeen		06/04/2007  #42564 changed path to app.js --%>
<%-- RYoung     08/6/2015   #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%> 

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<html:base />
<title><bean:message key="title.heading"/> - warrantAssociateSearch.jsp</title>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="juvenileWarrantForm" /> 

<!--JAVASCRIPT FILES --> 
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>

</head>
<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitSearchToCreateAssociateForm" target="content" focus="warrantNumber" onsubmit="return checkRequired();">
	<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|##">

<!-- BEGIN HEADING TABLE -->
 <table align="center">
   <tr>
     <td class="header">Search Warrants To Create Associate</td>
   </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0" align="center">
  <tr> 
    <td>
      <ul>
        <li>Enter the warrant number or Juvenile name information for associate.</li>
	    <li>Select submit to access Create Associate page.</li>
      </ul>
    </td>
  </tr>
  <tr> 
    <td height="20"><i>&nbsp;* Required Fields</i>
  </td>
 </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN CONTENT TABLE -->	
<table border="0" width="98%" align="center">
  <tr>
    <td width="50%" align="right" class="subhead"><bean:message key="prompt.warrantNumber"/></td>
    <td width="50%">
        <html:text property="warrantNum" size="10" maxlength="10" tabindex="1" /></td>
  </tr>	  
  <tr>
    <td width="50%" align="right" class="subhead">Juvenile <bean:message key="prompt.lastName"/></td>
    <td width="50%">
        <html:text property="lastName" size="20" maxlength="75" tabindex="2" /></td>
  </tr>
  <tr>
    <td width="50%" align="right" class="subhead">Juvenile <bean:message key="prompt.firstName"/></td>
    <td width="50%">
        <html:text property="firstName" size="20" maxlength="50" tabindex="3" /></td>
  </tr>	  	  
</table>
<!-- END CONTENT TABLE -->	
  <br>
<!-- BEGIN BUTTON TABLE -->
<table align="center" border="0" width="100%">
  <tr>
   <td align="center">
      <html:submit><bean:message key="button.submit"></bean:message></html:submit>&nbsp;
      <html:reset><bean:message key="button.reset"></bean:message></html:reset>&nbsp;
      <html:submit onclick="gotoURL('/<msp:webapp/>main.jsp')"><bean:message key="button.cancel"></bean:message></html:submit>
    </td>
  </tr>
</table>
<!-- END BUTTON TABLE --> 

</html:form>
<!-- END FORM -->
<html:errors></html:errors>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>