<!DOCTYPE HTML>

<%-- Tabs for accessing casefiles/interview/activities from profile --%>
<%-- 12/11/2006	awidjaja	Create JSP --%>
<%-- 02/12/2007 Hien Rodriguez  ER#37077 Add Tabs security features --%>
<%-- 03/20/2013 C Shimek        #75311 add Documents tab --%>

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
	<!--JQUERY FRAMEWORK-->
	<%@include file="../jQuery.fw"%>
	<html:base />
	<title><bean:message key="title.heading"/>/juvenileProfileCasefileTabs.jsp</title>
</head> 


<bean:define id="tab"><tiles:getAsString name="tabid" /></bean:define>
<bean:define id="requestParam"><%=PDJuvenileCaseConstants.JUVENILENUM_PARAM%>=<bean:write name="juvenileProfileHeader" property="juvenileNum"/></bean:define>

<table border="0" cellpadding="0" cellspacing="0">
	<tr>
	<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_CASEFILE%>'>
		<logic:equal name="tab" value="casefileInfo">
			<td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
			<td bgcolor='#6699FF' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileCasefileList.do?<%=requestParam%>" class=topNav>Casefile Info</a></td>
			<td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/right_active_tab.gif"></td>
		</logic:equal>
		<logic:notEqual name="tab" value="casefileInfo">
			<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
			<td bgcolor='#B3C9F5' align=center class=topNav><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileCasefileList.do?<%=requestParam%>" class=topNav>Casefile Info</a></td>
			<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
		</logic:notEqual>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>
	</jims2:isAllowed>
	
	<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_INTERVIEW%>'>
		<logic:equal name="tab" value="interview">
			<td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
			<td bgcolor='#6699FF' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileInterviewList.do?submitAction=Link&<%=requestParam%>" class=topNav>Interview</a></td>
			<td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/right_active_tab.gif"></td>
		</logic:equal>
		<logic:notEqual name="tab" value="interview">
			<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
			<td bgcolor='#B3C9F5' align=center class=topNav><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileInterviewList.do?submitAction=Link&<%=requestParam%>" class=topNav>Interview</a></td>
			<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
		</logic:notEqual>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>
	</jims2:isAllowed>
	
	<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_ACT%>'>
		<logic:equal name="tab" value="activities">
			<td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
			<td bgcolor='#6699FF' align=center><a onclick="spinner()" href="/<msp:webapp/>displayProfileActivities.do?submitAction=<bean:message key='button.link'/>" class=topNav><bean:message key="prompt.activities" /></a></td>
			<td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/right_active_tab.gif"></td>
		</logic:equal>
		<logic:notEqual name="tab" value="activities">
			<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
			<td bgcolor='#B3C9F5' align=center><a onclick="spinner()" href="/<msp:webapp/>displayProfileActivities.do?submitAction=<bean:message key='button.link'/>" class=topNav><bean:message key="prompt.activities" /></a></td>
			<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
		</logic:notEqual>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>
	</jims2:isAllowed>
	<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_JOU%>'>
		<logic:equal name="tab" value="journal">
			<td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
			<td bgcolor='#6699FF' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileJournalList.do?submitAction=<bean:message key='button.tab'/>" class=topNav>Journal</a></td>
			<td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/right_active_tab.gif"></td>
		</logic:equal>
		<logic:notEqual name="tab" value="journal">
			<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
			<td bgcolor='#B3C9F5' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileJournalList.do?submitAction=<bean:message key='button.tab'/>" class=topNav>Journal</a></td>
			<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
		</logic:notEqual>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>
	</jims2:isAllowed>
	<%--ER GANG ASSESSMENT REFERRAL CHANGES STARTS--%>
	 <jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_ARL%>'> 
		<logic:equal name="tab" value="assessmentReferral">
			<td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
			<td bgcolor='#6699FF' align=center><a onclick="spinner()" href="/<msp:webapp/>displayGangAssessmentReferralList.do?submitAction=<bean:message key='button.link'/>" class=topNav>Assessment Referral</a></td>
			<td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/right_active_tab.gif"></td>
		</logic:equal>
		<logic:notEqual name="tab" value="assessmentReferral">
			<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
			<td bgcolor='#B3C9F5' align=center><a onclick="spinner()" href="/<msp:webapp/>displayGangAssessmentReferralList.do?submitAction=<bean:message key='button.link'/>" class=topNav>Assessment Referral</a></td>
			<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
		</logic:notEqual>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>
	</jims2:isAllowed>
	<%--ER GANG ASSESSMENT REFERRAL CHANGES ENDS--%>
	<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_DOC%>'>
		<logic:equal name="tab" value="document">
			<td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
			<td bgcolor='#6699FF' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileDocuments.do?submitAction=<bean:message key='button.link'/>" class=topNav>Documents</a></td>
			<td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/right_active_tab.gif"></td>
		</logic:equal>
		<logic:notEqual name="tab" value="document">
			<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
			<td bgcolor='#B3C9F5' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileDocuments.do?submitAction=<bean:message key='button.link'/>" class=topNav>Documents</a></td>
			<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
		</logic:notEqual>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>
	</jims2:isAllowed>
	</tr>
</table>
	