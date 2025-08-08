<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<head>
	<msp:nocache />
	<meta http-equiv="Content-Language" content="en-us">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<meta name="GENERATOR" content="IBM WebSphere Studio">
	<meta http-equiv="Content-Style-Type" content="text/css">

	<!-- STYLE SHEET LINK -->
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
	<html:base />
	<title></title>
	<script language="JavaScript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	
</head> 

<tiles:importAttribute name="position"/>

			<table width="100%" border="0" cellpadding="2" cellspacing="1">
			<tr>
				<td class="headerLabel"><bean:message key="prompt.current"/> <bean:message key="prompt.user"/></td>
				<td colspan="5" align="left" bgcolor="#ffffff" class="headerData">
					<logic:equal name="position" property="vacant" value="false">
						<bean:write name="position" property="user.userName.formattedName"/>
					</logic:equal>
					<logic:equal name="position" property="vacant" value="true">
						<bean:message key="prompt.noOfficerAssigned"/>
					</logic:equal>
				</td>
			</tr>
			<tr>
				<td class="headerLabel" width="1%" nowrap><bean:message key="prompt.positionName"/></td>
				<td class="headerData" width="60%"><bean:write name="position" property="positionName"/></td>
				<td class="headerLabel"><bean:message key="prompt.poi"/></td>
				<td class="headerData"><bean:write name="position" property="probOfficerInd"/></td>
			</tr>
			<tr>
				<td class="headerLabel"><bean:message key="prompt.programUnit"/></td>
				<td class="headerData"><bean:write name="position" property="programUnitDesc"/></td>
				<td class="headerLabel" width="1%" nowrap><bean:message key="prompt.programSection"/></td>
				<td class="headerData"><bean:write name="position" property="programSectionDesc"/></td>
			</tr>
			</table>
