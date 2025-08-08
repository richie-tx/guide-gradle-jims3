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
	<title><bean:message key="title.heading"/>/juvenileCasePlanTabs.jsp</title>
</head> 

<%-- START PROFILE TABS --%>
<table border=0 cellpadding=0 cellspacing=0>
  <tr>
			<bean:define id="tab"><tiles:getAsString name="tabid" /></bean:define>

			<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_GOALS%>'>
				<logic:equal name="tab" value="Caseplan">
	        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
	        <td bgcolor='#6699FF' align=center><a href="/<msp:webapp/>displayCaseplanDetails.do?action=JUVPROFILEVIEW&submitAction=Link" class=topNav>Caseplan Details</a></td>
	        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
				</logic:equal>
				<logic:notEqual name="tab" value="Caseplan">
					<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
					<td bgcolor='#B3C9F5' align=center><a href="/<msp:webapp/>displayCaseplanDetails.do?action=JUVPROFILEVIEW&submitAction=Link" class=topNav>Caseplan Details</a></td>
					<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
				</logic:notEqual>		
				<td valign=top><img src='/<msp:webapp/>images/spacer.gif'</td>
			</jims2:isAllowed>

			<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_GOALS%>'>
				<logic:equal name="tab" value="PreviousVersions">
	        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
	        <td bgcolor='#6699FF' align=center><a href="/<msp:webapp/>displayPreviousCaseplanVersions.do?submitAction=Link&action=juvprofileview" class=topNav>Previous Caseplan Versions</a></td>
	        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
				</logic:equal>
				<logic:notEqual name="tab" value="PreviousVersions">
					<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
					<td bgcolor='#B3C9F5' align=center><a href="/<msp:webapp/>displayPreviousCaseplanVersions.do?submitAction=Link&action=juvprofileview" class=topNav>Previous Caseplan Versions</a></td>
					<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
				</logic:notEqual>		
				<td valign=top><img src='/<msp:webapp/>images/spacer.gif'</td>
			</jims2:isAllowed>

			<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_GOALS%>'>
				<logic:equal name="tab" value="JPOReviews">
	        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
	        <td bgcolor='#6699FF' align=center><a href="/<msp:webapp/>displayJPOReviews.do?submitAction=Link" class=topNav>JPO Reviews</a></td>
	        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
				</logic:equal>
				<logic:notEqual name="tab" value="JPOReviews">
					<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
					<td bgcolor='#B3C9F5' align=center><a href="/<msp:webapp/>displayJPOReviews.do?submitAction=Link" class=topNav>JPO Reviews</a></td>
					<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
				</logic:notEqual>
				<td valign=top><img src='/<msp:webapp/>images/spacer.gif'</td>		
			</jims2:isAllowed>
			<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_GOALS%>'>
				<logic:equal name="tab" value="GenerateCaseplanDetails">
			        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
			        <td bgcolor='#6699FF' align=center><a href="/<msp:webapp/>displayGenerateCaseplanDetails.do?submitAction=Link" class=topNav>Generate Caseplan</a></td>
			        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
				</logic:equal>
				<logic:notEqual name="tab" value="GenerateCaseplanDetails">
					<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
					<td bgcolor='#B3C9F5' align=center><a href="/<msp:webapp/>displayGenerateCaseplanDetails.do?submitAction=Link" class=topNav>Generate Caseplan</a></td>
					<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
				</logic:notEqual>		
			</jims2:isAllowed>
	</tr>
</table>
<%-- END PROFILE TABS --%>

