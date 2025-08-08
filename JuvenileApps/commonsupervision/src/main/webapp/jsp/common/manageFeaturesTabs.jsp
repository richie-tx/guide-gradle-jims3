<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Manages Tabs for Manage Features-->
<!-- 07/29/2005		awidjaja	Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.Features" %> 

<!--BEGIN HEADER TAG-->
<head>
	<msp:nocache />
	<meta http-equiv="Content-Language" content="en-us">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<meta name="GENERATOR" content="IBM WebSphere Studio">
	<meta http-equiv="Content-Style-Type" content="text/css">

	<!-- STYLE SHEET LINK -->
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
	<html:base />
	<title>Common Supervision - common/manageFeaturesTabs.jsp</title>
</head> 

<tiles:useAttribute id="tab" name="tabid" classname="java.lang.String" ignore="true"/> <!-- this attribute is optional-->

<!--BEGIN BODY TAG-->
<body topmargin=0 leftmargin="0">
<table width=100% border=0 cellpadding=0 cellspacing=0>	
	<tr>
		<td>
			<table border=0 cellpadding=0 cellspacing=0>
				<tr>
					<jims2:isAllowed requiredFeatures="CSCD-RPT-ASSIGNMENTS">
					<%-- 
					slin: currently the only report is the view assignments report, so we, by default, set the link from the Reports tab to the view assignments reports... 
					ultimately we might have multiple reports so we would sub nav them (like prototype) or create a general reports page that lists all available reports
					--%>
					<logic:equal name="tab" value="reportsTab">
						<td bgcolor=#33cc66 valign=top><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>						
						<td bgcolor=#33cc66 align=center><a href="/<msp:webapp/>caseAssignmentReportSearchAction.do?submitAction=Setup" class=topNav><bean:message key="prompt.reports" /></a></td>
						<td bgcolor=#33cc66 valign=top><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>				
					</logic:equal>
					<logic:notEqual name="tab" value="reportsTab">
						<td bgcolor=#99ff99 valign=top><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
						<td bgcolor=#99ff99 align=center><a href="/<msp:webapp/>caseAssignmentReportSearchAction.do?submitAction=Setup" class=topNav><bean:message key="prompt.reports" /></a></td>
						<td bgcolor=#99ff99 valign=top><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
					</logic:notEqual>		
					<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>
					</jims2:isAllowed>
						
 					<jims2:isAllowed requiredFeatures="CS-OOC-SEARCH">
					<logic:equal name="tab" value="oocTab">
						<td bgcolor=#33cc66 valign=top><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
						<td bgcolor=#33cc66 align=center><a href="/<msp:webapp/>displayOutOfCountyCaseSearch.do" class=topNav><bean:message key="prompt.ooc" /></a></td>
						<td bgcolor=#33cc66 valign=top><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>				
					</logic:equal>
					<logic:notEqual name="tab" value="oocTab">
						<td bgcolor=#99ff99 valign=top><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
						<td bgcolor=#99ff99 align=center><a href="/<msp:webapp/>displayOutOfCountyCaseSearch.do" class=topNav><bean:message key="prompt.ooc" /></a></td>
						<td bgcolor=#99ff99 valign=top><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
					</logic:notEqual>		
					<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>	
					</jims2:isAllowed>									
					<jims2:isAllowed requiredFeatures="<%=Features.CS_POSV_POSITIONS%>">
					<logic:equal name="tab" value="positionsTab">
						<td bgcolor=#33cc66 valign=top><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
						<td bgcolor=#33cc66 align=center><a href="/<msp:webapp/>displayPositionSearch.do?submitAction=Link" class=topNav><bean:message key="prompt.positions" /></a></td>
						<td bgcolor=#33cc66 valign=top><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>				
					</logic:equal>
					<logic:notEqual name="tab" value="positionsTab">
						<td bgcolor=#99ff99 valign=top><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
						<td bgcolor=#99ff99 align=center><a href="/<msp:webapp/>displayPositionSearch.do?submitAction=Link" class=topNav><bean:message key="prompt.positions" /></a></td>
						<td bgcolor=#99ff99 valign=top><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
					</logic:notEqual>		
					<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>	
					</jims2:isAllowed>				
					<jims2:isAllowed requiredFeatures="CS-WORKGRP-VIEW">
					<logic:equal name="tab" value="workgroupsTab">
						<td bgcolor=#33cc66 valign=top><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
						<td bgcolor=#33cc66 align=center><a href="/<msp:webapp/>displayWorkgroupSearch.do?submitAction=Link" class=topNav><bean:message key="prompt.workgroups" /></a></td>
						<td bgcolor=#33cc66 valign=top><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>				
					</logic:equal>
					<logic:notEqual name="tab" value="workgroupsTab">
						<td bgcolor=#99ff99 valign=top><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
						<td bgcolor=#99ff99 align=center><a href="/<msp:webapp/>displayWorkgroupSearch.do?submitAction=Link" class=topNav><bean:message key="prompt.workgroups" /></a></td>
						<td bgcolor=#99ff99 valign=top><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
					</logic:notEqual>		
					<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>	
					</jims2:isAllowed>					
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td bgcolor=#33cc66><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
	</tr>
</table>
	<!-- END FORM -->
</body>
</html:html>
