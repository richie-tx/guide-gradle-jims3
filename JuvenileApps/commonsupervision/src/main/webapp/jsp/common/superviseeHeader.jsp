<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 11/02/2007	 C Shimek - Create JSP  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title>Common Supervision - common/superviseeHeader.jsp</title>

</head>
<%-- <body> --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor=#cccccc>
		<tr>
			<td class="headerLabel"><bean:message key="prompt.name" /></td>
			<td class="headerData">
				<bean:write name="superviseeHeaderForm" property="superviseeNameDesc"/>&nbsp;
				<logic:equal name="superviseeHeaderForm" property="compliant" value="false">
					<img src="/<msp:webapp/>images/redLight.gif" title="Out of Compliance" border="0">
				</logic:equal>																			
				<logic:equal name="superviseeHeaderForm" property="compliant" value="true">
					<img src="/<msp:webapp/>images/greenLight.gif" title="In Compliance" border="0">
				</logic:equal>&nbsp;&nbsp;&nbsp;																			
				
				<logic:equal name="superviseeHeaderForm" property="disposition" value="PROB">
				    <logic:equal name="superviseeHeaderForm" property="dnaFlagInd" value="false">
				       <logic:equal name="superviseeHeaderForm" property="overNinetyDays" value="true">
					      <img src="/<msp:webapp/>images/circledXicon.png" title="DNA Test Needed over 90 days overdue" border="0">
					   </logic:equal> 
					   <logic:equal name="superviseeHeaderForm" property="overNinetyDays" value="false">
					      <img src="/<msp:webapp/>images/circledRedIcon.png" title="DNA Test Needed" border="0">
					   </logic:equal> 
					</logic:equal>  
					<logic:equal name="superviseeHeaderForm" property="dnaFlagInd" value="true">
						<logic:equal name="superviseeHeaderForm" property="overNinetyDays" value="true">
					      <img src="/<msp:webapp/>images/circledXicon.png" title="DNA Test Needed over 90 days overdue" border="0">
					   </logic:equal> 
				 	</logic:equal>
				</logic:equal>
			</td> 
			<td class="headerLabel"><bean:message key="prompt.SPN" /></td>
			<td class="headerData"><bean:write name="superviseeHeaderForm" property="superviseeSpn"/></td>
		</tr> 
		<tr>
			<td class="headerLabel"><bean:message key="prompt.officer" /></td>
			<td class="headerData"><bean:write name="superviseeHeaderForm" property="officerNameDesc"/></td>
			<td class="headerLabel"><bean:message key="prompt.LOS" /></td>
			<td class="headerData"><bean:write name="superviseeHeaderForm" property="LOSDesc" /> </td>
		</tr>
		<tr>
			<td class="headerLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.programUnit" /></td>
			<td colspan="3" class="headerData"><bean:write name="superviseeHeaderForm" property="programUnitDesc"/></td>
		</tr>
	</table>	

<%-- </body> --%>
</html:html>