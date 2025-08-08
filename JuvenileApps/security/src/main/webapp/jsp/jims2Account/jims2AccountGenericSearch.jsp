<!DOCTYPE HTML>
<!-- 12/11/2006	 C Shimek   - Create JSP -->
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
<title><bean:message key="title.heading" /> - jims2AccountGenericSearch.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/jims2Account/jims2AccountGenericSearch.js" ></script> 

</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="setCursor()">
<html:form action="/displayJIMS2AccountCreateSearchById" target="content">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|##">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header">
			<bean:message key="prompt.create"/>&nbsp;<bean:message key="prompt.JIMS2"/>&nbsp;<bean:message key="prompt.account"/>&nbsp;<bean:message key="prompt.search"/> -
			<logic:equal name="jims2AccountForm" property="userType" value="L">
				<bean:message key="title.officerProfile"/>
			</logic:equal>
			<logic:equal name="jims2AccountForm" property="userType" value="S">
				<bean:message key="prompt.serviceProvider"/>			
			</logic:equal>

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
<table align="center" width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Enter search criteria and then click Next button.</li>
				<logic:equal name="jims2AccountForm" property="userType" value="L">
					<li>Only 1 search field is required to search.</li>		
				</logic:equal>
			</ul>
		</td>
	</tr>
</table>  
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN PAGE ALIGNMENT TABLE -->
<input type="hidden" name="searchType" value="">
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td align="center" valign="top">
<!-- BEGIN SERVICE PROVIDER  SEARCH TABLE -->				
			<table border="0" cellspacing="1" cellpadding=2 width='100%' class="borderTableBlue" align="center">
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.JIMS"/> <bean:message key="prompt.userId"/></td>
					<td class="formDe"><bean:write name="jims2AccountForm" property="jimsLogonId"/></td>
				</tr>				
				<logic:equal name="jims2AccountForm" property="userType" value="S">										
					<tr>
						<td class="formDeLabel" nowrap width="1%">
							<bean:message key="prompt.2.diamond" />
							<bean:message key="prompt.employeeId"/></td>
						<td class="formDe"><html:text property="searchEmployeeId" size="10" maxlength="10" /></td>
					</tr>
				</logic:equal>
				<logic:equal name="jims2AccountForm" property="userType" value="L">					
					<tr>
						<td class="formDeLabel" ><bean:message key="prompt.badgeNumber"/></td>
						<td class="formDe"><html:text property="searchBadgeNumber" size="10" maxlength="10" /></td>
					</tr>
					<tr>
						<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.otherIdNumber"/></td>
						<td class="formDe"><html:text property="searchOtherIdNumber" size="10" maxlength="10" /></td>
					</tr>
				</logic:equal>
			</table>
		</td>
	</tr>
</table>
<!-- END PAGE ALIGNMENT TABLE -->
    <br>
<!--BEGIN BUTTON TABLE--> 
    <table align="center"> 
		<tr> 
			<td>
				<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
					<bean:message key="button.back"/>
				</html:button>
				<html:submit property="submitAction" onclick="return checkInput(this.form) && disableSubmit(this, this.form)"><bean:message key="button.next"/></html:submit>								
				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.reset"/></html:submit> 
				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.cancel"/></html:submit>
			</td> 
		</tr> 
    </table>
<!--END BUTTON TABLE-->
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>