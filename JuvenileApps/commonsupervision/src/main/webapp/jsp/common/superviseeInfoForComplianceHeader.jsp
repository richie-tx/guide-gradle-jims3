<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 11/02/2007	 C Shimek - Create JSP  -->
<!-- 07/07/2008  C Shimek - defect#51135 revised prompt form PU to Program Unit -- PT value changed about 4 months after this jsp was created -->

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
<title>Common Supervision - common/superviseeInfoForComplianceHeader.jsp</title>
</head>

	<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor=#cccccc>
		<tr>
			<td class="headerLabel"><bean:message key="prompt.name" /></td>
			<td class="headerData">
				<bean:write name="complianceSuperviseeInfoForm" property="superviseeName" />&nbsp;
				<logic:equal name="complianceSuperviseeInfoForm" property="superviseeCompliant" value="false">
					<img src="/<msp:webapp/>images/redLight.gif" title="Out of Compliance" border="0">
				</logic:equal>																			
				<logic:equal name="complianceSuperviseeInfoForm" property="superviseeCompliant" value="true">
					<img src="/<msp:webapp/>images/greenLight.gif" title="In Compliance" border="0">
				</logic:equal>
			</td> 
			<td class="headerLabel"><bean:message key="prompt.SPN" /></td>
			<td class="headerData"><bean:write name="complianceSuperviseeInfoForm" property="superviseeSPN"/></td>
		</tr> 
		<tr>
			<td class="headerLabel"><bean:message key="prompt.officer" /></td>
			<td class="headerData"><bean:write name="complianceSuperviseeInfoForm" property="officerName"/></td>
			<td class="headerLabel"><bean:message key="prompt.LOS" /></td>
			<td class="headerData"><bean:write name="complianceSuperviseeInfoForm" property="levelOfSupervision" /> </td>
		</tr>
		<tr>
			<td class="headerLabel"><bean:message key="prompt.programUnit" /></td>
			<td colspan="3" class="headerData"><bean:write name="complianceSuperviseeInfoForm" property="programUnit"/></td>
		</tr>
	</table>	

