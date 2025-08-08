<!DOCTYPE HTML>

<%-- Manages Tabs for Juvenile Casefiles --%>
<%-- 10/25/2006	uGopinath	Create JSP --%>
<%-- 01/17/2007 Hien Rodriguez  ER#37077 Add Tab security features --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
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
	<html:base />
	<title><bean:message key="title.heading"/>/abuseDualTabs.jsp</title>
</head> 

	<%-- START EDUCATION TABS --%>
	<table border=0 cellpadding=0 cellspacing=0>
		<tr>
			<bean:define id="tab"><tiles:getAsString name="tabid" /></bean:define>
			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MAS_ABUSE%>'>
				<logic:equal name="tab" value="ABUSE">
						<td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
				        <td bgcolor='#ff6666' align=center ><a href="/<msp:webapp/>displayJuvenileAbuse.do?submitAction=Tab&action=View" class=topNav>Abuse</a></td>
				        <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>					
				</logic:equal>	
				<logic:notEqual name="tab" value="ABUSE">
						<td bgcolor='#FFCCCC' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>
						<td bgcolor='#FFCCCC' align=center ><a href="/<msp:webapp/>displayJuvenileAbuse.do?submitAction=Tab&action=View" class=topNav>Abuse</a></td>
				        <td bgcolor='#FFCCCC' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>	
				</logic:notEqual>	
				<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
			</jims2:isAllowed>
			 <jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MAS_DUALSTATUS%>'>
				<logic:equal name="tab" value="DUALSTATUS">
			        	<td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
				        <td bgcolor='#ff6666' align=center ><a href="/<msp:webapp/>displayJuvenileAbuse.do?submitAction=Dual&action=View" class=topNav>Dual Status</a></td>
				        <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>				
				</logic:equal>
				<logic:notEqual name="tab" value="DUALSTATUS">
						<td bgcolor='#FFCCCC' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>
				        <td bgcolor='#FFCCCC' align=center ><a href="/<msp:webapp/>displayJuvenileAbuse.do?submitAction=Dual&action=View" class=topNav>Dual Status</a></td>
				        <td bgcolor='#FFCCCC' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>	
				</logic:notEqual>		
				<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
			</jims2:isAllowed> 			
		</tr>
	</table>
	<%-- END PROFILE TABS --%>

