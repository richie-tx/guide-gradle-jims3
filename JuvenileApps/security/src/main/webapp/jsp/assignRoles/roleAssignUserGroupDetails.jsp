<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Used for activate, inactivate, create, and update summary pages and view details. -->
<!--MODIFICATIONS -->
<!-- mchowdhury 10/12/2005	 Create JSP -->
<!-- CShimek    02/04/2009   #56860 add Back to Top  -->
<!-- CShimek    07/17/2009   #61004 added timeout.js  -->

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
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading"/> - roleAssignUserGroupDetails.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
</head>
<body>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
     <tr>
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.userGroupInfo" /></td>
					<td align="right"><img src="/<msp:webapp/>images/step_1.gif hspace='0' vspace='0' border='0'"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table border="0" cellspacing="1" cellpadding="4" width="100%">
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userGroupName" /></td>
								<td class="formDe">
								     <bean:write name="assignRolesForm" property="userGroupName"/>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap><bean:message key="prompt.userGroupDescription" /></td>
								<td class="formDe">
								     <bean:write name="assignRolesForm" property="userGroupDescription"/>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.status" /></td>
								<td class="formDe">
								     <bean:write name="assignRolesForm" property="userGroupStatus"/>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.agencyName" /></td>
								<td class="formDe">
								     <bean:write name="assignRolesForm" property="userGroupAgencyName"/>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.createdBy" /></td>
								<td class="formDe">
								     <bean:write name="assignRolesForm" property="userGroupCreatorName"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<br> 
			<!--user members begin-->
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.userGroupMembers" /></td>
					<td align="right">
						<img src="/<msp:webapp/>images/step_2.gif hspace=0 vspace=0 border=0">

					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<div style="height:80px; overflow:auto; ">
						<table width="100%" border="0" cellpadding="2">
							<tr>
								<td class="formDeLabel" class="detailHead"><bean:message key="prompt.memberName"/></td>
								<td class="formDeLabel" class="detailHead"><bean:message key="prompt.agency"/></td>								
							</tr>
						    <logic:iterate id="memberNameIndex" name="assignRolesForm" property="userGroupUsers" indexId="index">
						    <tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
         					    <td><bean:write name="memberNameIndex" property="lastName"/>, <bean:write name="memberNameIndex" property="firstName"/> <bean:write name="memberNameIndex" property="middleName"/></td>
         					    <td><bean:write name="memberNameIndex" property="agencyName"/></td>
                            </tr>      	
                            </logic:iterate> 
 						</table>
 						</div>
					</td>
				</tr>
			</table>
			<br>
		    <tr>
		       <td align="center">
		           <html:form action="/submitUserGroupUpdate" target="content"> 
			           <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				          <bean:message key="button.back"></bean:message>
				       </html:button>
						<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|##">	
			       </html:form>    
		   	   </td>
	       </tr>
</table>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>