<!DOCTYPE HTML>
<!-- Used to display associated SA with this role and user names if any before deleting -->
<!--MODIFICATIONS -->
<!-- CShimek 04/01/2005	 Create JSP -->
<!-- CShimek 01/12/2007  #38306 add multiple submit functionality  -->
<!-- CShimek 02/05/2009  #56860 add Back to Top  -->

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
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />

<title><bean:message key="title.heading"/> - roleSADeleteConfirm.jsp</title>

<!-- APP JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
  <tr>
    <td align="center" class="header"><bean:message key="title.saRoleDelete"/>&nbsp;<bean:message key="title.summary"/></td>
  </tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<table align="center" width="98%" border="0">
   <tr>
     <td>
	  <ul>
        <li>Select Finish to delete this role.</li>
      </ul>
	</td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<html:form action="/submitSARole" target="content">	
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|96">
	<tr>
		<td width="98%" align="center"valign="top">
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.delete" /> <bean:write name="roleSAForm" property="roleName"/></td>
					<td align="right"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">						
						<table width="100%" cellpadding="4">
							<tr>
								<td colspan="2" class="formDeLabel"><bean:message key="prompt.followingSAHaveRole"/></td>								
							</tr>
							<tr>
								<td class="formDeLabel">SA <bean:message key="prompt.userName"/>s</td>								
							</tr>
							<logic:iterate id="userNameIndex" name="roleSAForm" property="users" indexId="index">
							<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
								<td class="boldText" ><bean:write name="userNameIndex" property="lastName"/>,&nbsp;<bean:write name="userNameIndex" property="firstName"/></td>								
							</tr>
							</logic:iterate>
						</table>
					</td>
				</tr>
			</table>
		<br>
	        <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
		          <bean:message key="button.back"></bean:message></html:button>&nbsp;		
   		    <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>
   		    <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>  
		</td>
	</tr>	
</html:form>		
</table>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>