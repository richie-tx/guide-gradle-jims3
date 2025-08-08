<!DOCTYPE HTML>
<!-- 12/14/2006	 C Shimek   - Create JSP -->
<!-- 04/17/2007  C Shimek   - 41218 added missing mapId number -->
<!-- 02/05/2009  C Shimek   - #56860 add Back to Top  -->

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
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->  
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading" /> - JIMS2AccountCreateSearch.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/jims2Account/jims2AccountCreateSearch.js" ></script> 

</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayJIMS2AccountCreateSearch" target="content" focus="searchLogonId">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|114">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
  <tr>
    <td align="center" class="header">
    	<bean:message key="prompt.create"/>&nbsp;<bean:message key="prompt.JIMS2"/>&nbsp;<bean:message key="prompt.account"/>&nbsp;<bean:message key="prompt.search"/>
    </td>
  </tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<table align="center" width="98%" border="0" align="center">
   <tr>
     <td>
	  <ul> 
        <li>Enter full User ID then select Next button to search.</li>
      </ul>
	</td>
  </tr>
</table>
<table width="98%" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td class="required"><bean:message key="prompt.requiredFields"/></td>
	<tr>	
</table>
<!-- END INSTRUCTION TABLE -->
<table width="98%" cellpadding="4" cellspacing="0" class="borderTableBlue" align="center">
	<tr class="detailHead">
		<td class="detailHead">Search for Users</td>
	</tr>
	<tr>
		<td align="center">
			<!--search table start-->
			<table border="0" cellspacing="1" cellpadding="2" width="100%">
				<tr>
					<td class="formDeLabel"width="1%" nowrap><bean:message key="prompt.2.diamond" /><bean:message key="prompt.JIMS"/> <bean:message key="prompt.userId"/></td>
					<td class="formDe"><html:text property="searchLogonId" size="8" maxlength="8" /></td>
				</tr>
			</table>	
		</td>
	</tr> 
</table>
<br>
<table align="center">
    <tr>
        <td align="center">
			<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				<bean:message key="button.back"></bean:message>
			</html:button>
			<html:submit property="submitAction" onclick="return checkInput(this.form) && disableSubmit(this, this.form)"><bean:message key="button.next"/></html:submit>								
			<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.reset"/></html:submit> 
			<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
		</td>
    </tr>
</table>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>