<!DOCTYPE HTML>

<%-- Currently used to display juvenile guardian header info on Process Benefits Assessment--%>
<%--MODIFICATIONS --%>
<%-- 11/21/2005		Awidjaja	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<tiles:importAttribute name="juvenileInfo"/>

	<%--BEGIN HEADER TAG--%>
	<head>
		<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
		<msp:nocache />
		<%-- Checks to make sure if the user is logged in. --%>
		<%--msp:login / --%>
		<meta http-equiv="Content-Language" content="en-us">
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
		<meta name="GENERATOR" content="IBM WebSphere Studio">
		<meta http-equiv="Content-Style-Type" content="text/css">
		<%-- STYLE SHEET LINK --%>
		<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
		<html:base />
		<title><bean:message key="title.heading"/> - juvenileHeaderGuardianInfo.jsp</title>
	</head> 
	
	
		<logic:notEmpty name="juvenileInfo">
			<table align="center" cellpadding="1" cellspacing="0" border="0" width="98%">
			  <tr>
				 <td bgcolor="#CCCCCC">
					<%--tabs start--%>
					<table width='100%' border=0 cellpadding=2 cellspacing=1>
						<tr class=bodyText>
							<td class=headerLabel>Juvenile #:</td>
							<td class=headerData><bean:write name="juvenileInfo" property="juvNumber"/></td>
							<td class=headerLabel>Juvenile Name:</td>
							<td colspan=3 class=headerData><bean:write name="juvenileInfo" property="juvName.formattedName"/></td>
						</tr>
						
						<logic:notEmpty name="juvenileInfo" property="listOfGuardians">
						<logic:iterate name="juvenileInfo" property="listOfGuardians" id="juvGuardiansIter">
							<tr class=bodyText>
								<td class=headerLabel>Guardian:</td>
								<td class=headerData><bean:write name="juvGuardiansIter" property="name.formattedName"/></td>
								<td class=headerLabel>Phone:</td>
								<td class=headerData><bean:write name="juvGuardiansIter" property="phoneNumber"/></td>
								<td class=headerLabel>Phone Type:</td>
								<td class=headerData><bean:write name="juvGuardiansIter" property="phoneType"/></td> 
							</tr>
						</logic:iterate>
						</logic:notEmpty>
					</table>
				 </td>
			  </tr>
			</table>
		</logic:notEmpty>

