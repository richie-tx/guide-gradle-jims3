<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 03/30/2004	 Hien Rodriguez - Create JSP -->
<!-- 08/18/2004	 Hien Rodriguez - Refactor Action & JSP with new PD Event/Command -->
<!-- 07/17/2009  C Shimek       - #61004 added timeout.js  -->

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
<%-- <msp:login /> --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - roleAssociatedUserList.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
</head>
<!--END HEADER TAG-->

<bean:define id="role" name="roleEvent" scope="request" />

<!--BEGIN BODY TAG-->
<body>

<!-- BEGIN CONTENT TABLES -->
<table align="center">
  <tr>
	<td class="header"><bean:message key="title.roleAssociatedUserList" /></td>
  </tr>
</table>
<br>

<!-- DISPLAY ROLE INFO -->	
<table align="center">	    	  
	<tr>         
		<td align="right" class="subhead"><bean:message key="prompt.roleName"/>:</td> 
		<td><bean:write name="role" property="roleName"/></td>
		<td>&nbsp;&nbsp;&nbsp;</td>           
        <td align="right" class="subhead"><bean:message key="prompt.roleDescription"/>:</td> 
        <td><bean:write name="role" property="roleDescription"/></td>
	</tr>     
</table>
<br>      
<!-- DISPLAY ASSOCIATED USERS INFO -->	
<%int RecordCounter = 0;%>
<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center" class="borderTableBlue">	
	<tr class="detailHead">
		<td><bean:message key="prompt.logonId" /></td>
		<td><bean:message key="prompt.firstName" /></td>
		<td><bean:message key="prompt.lastName" /></td>
  	</tr>  
  	<logic:iterate id="user" name="role" property="users">
  		<tr class= 
  			<% RecordCounter++;
				if (RecordCounter % 2 == 1)
					out.print("normalRow");
				else
					out.print("alternateRow");	
  			%>>
			<td><bean:write name="user" property="logonId" /></td>
			<td><bean:write name="user" property="firstName" /></td>
			<td><bean:write name="user" property="lastName" /></td>
  		</tr>
  	</logic:iterate>  
</table>
<br>
<!-- BEGIN BUTTONS TABLES --> 	
<table align="center">
	<tr>
        <td align="center" valign="top">		
	  		<html:button property="button.returnToRolesList" onclick="history.go(-2);">
				<bean:message key="button.returnToRolesList"></bean:message>
			</html:button>
	  	</td>	
	  	
		<td valign="top">			      
		  	<html:form action="/displayRoleSearch?action=view">
				<html:submit property="submitAction">
					<bean:message key="button.cancel"></bean:message>
				</html:submit>
			</html:form>
		</td>	    		
  	</tr>		
</table><p>&nbsp;</p>
<!-- END CONTENT TABLES -->   

<html:errors></html:errors>
</body>
<!--END BODY TAG-->
</html:html>  