<!DOCTYPE HTML>
<!-- Used to create new User Group - page 1 of 2 -->
<!--MODIFICATIONS -->
<!-- CShimek 06/07/2005	 Create JSP -->
<!-- CShimek 01/11/2007  #38306 add multiple submit functionality  -->
<!-- CShimek 02/06/2009  #56860 add Back to Top  -->
<!-- RYoung  10/20/2015  #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

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
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading"/> - userGroupCreate1.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/userGroups/userGroupCreate1.js"></script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
  <tr>
    <td align="center" class="header"><bean:message key="title.userGroupCreate"/></td>
  </tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<table align="center" width="98%" border="0">
   <tr>
     <td>
	  <ul>
        <li>Enter User Group Name and Description then select Next button. </li>
      </ul>
	</td>
  </tr>
  <tr>
     <td class="required"><bean:message key="prompt.requiredFields"/></td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<html:form action="/displayUserGroupCreateSummary" target="content" focus="userGroupName">	
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|69">		
	<tr>
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%" align="center">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="title.userGroupCreate"/></td>
					<td align="right"><img src=/<msp:webapp/>images/step_1.gif hspace=0 vspace=0></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table width="100%">
							<tr>
								<td nowrap class="formDeLabel"><bean:message key="prompt.diamond" /><bean:message key="prompt.userGroupName"/></td>
								<td class="formDe"><html:text property="userGroupName" size="25" maxlength="25"/></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.diamond" /><bean:message key="prompt.userGroupDescription"/></td>
								<td class="formDe"><html:text property="userGroupDescription" size="50" maxlength="50"/></td>
							</tr>
						</table>
					</td> 
				</tr>
			</table>				
		</td>			
	</tr>	
	<tr><td>&nbsp;</td></tr>
    <logic:notEqual name="userGroupForm" property="errorMessage" value="">
    <tr>
   	    <td align="center" class="errorAlert"><bean:write name="userGroupForm" property="errorMessage" /></td>
    </tr>	    
   	</logic:notEqual>  
<!-- BEGIN BUTTONS -->
	<tr> 
		<td align="center">
			<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				<bean:message key="button.back"></bean:message></html:button>&nbsp;		
			<html:submit onclick="return validateFields(this.form)" property="submitAction"><bean:message key="button.next"/></html:submit>
		</td> 
	</tr> 
<!--END BUTTONS -->
</html:form>	
</table>
<br>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>