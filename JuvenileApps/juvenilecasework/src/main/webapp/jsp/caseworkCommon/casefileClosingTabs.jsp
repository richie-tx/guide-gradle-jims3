<!DOCTYPE HTML>

<%-- Manages Tabs for Juvenile Casefiles --%>
<%-- 05/20/2005		glyons	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<%--BEGIN HEADER TAG--%>
<head>
	<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
	<msp:nocache />
	<meta http-equiv="Content-Language" content="en-us">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<meta name="GENERATOR" content="IBM WebSphere Studio">
	<meta http-equiv="Content-Style-Type" content="text/css">

	<%-- STYLE SHEET LINK --%>
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
	<title><bean:message key="title.heading"/>/casefileClosingTabs.jsp</title>
	<html:base />
	
</head> 


	<%-- START PROFILE TABS --%>
	<table border=0 cellpadding=0 cellspacing=0>
		<tr>
			<bean:define id="tab"><tiles:getAsString name="tabid" /></bean:define>
			<logic:equal name="tab" value="CommonApp">
		        <td bgcolor='#33cc66' valign=top><img src='/<msp:webapp/>images/left_green_active_tab.gif'></td>
		        <td bgcolor='#33cc66' align=center><a href="/<msp:webapp/>displayCommonAppCourtDispositions.do?submitAction=Link" class=topNav>Common App</a></td>
		        <td bgcolor='#33cc66' valign=top><img src='/<msp:webapp/>images/right_green_active_tab.gif'></td>				
			</logic:equal>
			<logic:notEqual name="tab" value="CommonApp">
				<td bgcolor='#99ff99' valign=top><img src='/<msp:webapp/>images/left_green_inactive_tab.gif'></td>
				<td bgcolor='#99ff99' align=center><a href="/<msp:webapp/>displayCommonAppCourtDispositions.do?submitAction=Link" class=topNav>Common App</a></td>
				<td bgcolor='#99ff99' valign=top><img src='/<msp:webapp/>images/right_green_inactive_tab.gif'></td>
			</logic:notEqual>		
			<td valign=top><img src=/<msp:webapp/>images/spacer.gif width=2></td>
			
		</tr>
	</table>
	<%-- END PROFILE TABS --%>
