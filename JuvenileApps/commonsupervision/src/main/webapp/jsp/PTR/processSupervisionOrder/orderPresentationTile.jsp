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
<table border=0 align=center cellpadding=4 cellspacing=1 width=100%>											
											<tr>
												<td class=formDeLabel nowrap width="1%"><bean:message key="prompt.versionType" /></td>
												<td class=formDe><bean:write name="supervisionOrderForm" property="versionType" /></td>
											</tr>
											<tr>
												<td class=formDeLabel><bean:message key="prompt.orderTitle" /></td>
												<td class=formDe><bean:write name="supervisionOrderForm" property="orderTitle" /></td>		
											</tr>
											
											
											
										<logic:equal name="supervisionOrderForm" property="limitedSupervisonPeriod" value="true">
											<tr>				                          	
												<td class=formDeLabel nowrap width="1%"><bean:message key="prompt.supervisionBeginDate" /></td>
												<td class=formDe><bean:write name="supervisionOrderForm" property="supervisionBeginDateAsString" /></td>		
											</tr>
											<tr>				                          	
												<td class=formDeLabel><bean:message key="prompt.supervisionEndDate" /></td>
												<td class=formDe><bean:write name="supervisionOrderForm" property="supervisionEndDateAsString" /></td>		
											</tr>
										</logic:equal>
								
								
										
										<logic:notEmpty name="supervisionOrderForm" property="casenotes">
										<logic:notEqual name="supervisionOrderForm" property="casenotes" value="">
											<tr>				                          	
												<td class=formDeLabel colspan="2"><bean:message key="prompt.modificationReason" /></td>
											</tr>
											<tr>
												<td class=formDe colspan="2"><bean:write name="supervisionOrderForm" property="casenotes" /></td>		
											</tr>
										</logic:notEqual>
										</logic:notEmpty>
										
										</table>