<!DOCTYPE HTML>
<%-- Manages Tabs for Juvenile Casefiles --%>
<%-- 07/09/2007		ugopinath	Create JSP --%>

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
	<title><bean:message key="title.heading"/>/casefilePetitionTabs.jsp</title>
</head> 

	<%-- START FORM --%>
	
	<table border=0 cellpadding=0 cellspacing=0>
		<tr>
			<bean:define id="tab"><tiles:getAsString name="tabid" /></bean:define>

			<logic:equal name="tab" value="petition">
		        <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
		        <td bgcolor='#33cc66' align=center><a href="/<msp:webapp/>displayJuvenileCasefilePetitionDetails.do?submitAction=View&notDetailed=false" class=topNav>Petition Details</a></td>
		        <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>				
			</logic:equal>
			<logic:notEqual name="tab" value="petition">
				  <td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>			
					<td bgcolor='#99ff99' align=center><a href="/<msp:webapp/>displayJuvenileCasefilePetitionDetails.do?submitAction=View&notDetailed=false" class=topNav>Petition Details</a></td>
				  <td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
			</logic:notEqual>		
			<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
	
			
			<jims2:isAllowed requiredFeatures='<%=Features.JCW_VIEW_COURTORDER%>'>
				<logic:equal name="tab" value="courtOrder">
			       <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
			       <td bgcolor='#33cc66' align=center><a href="/<msp:webapp/>displayLegacyCourtOrder.do?submitAction=Link&juvenileNum=<bean:write name='petitionDetailsForm' property='juvenileNum'/>&petitionNum=<bean:write name='petitionDetailsForm' property='petitionNum'/>" class=topNav>Court Order</a></td>
			      <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>				
				</logic:equal>
				<logic:notEqual name="tab" value="courtOrder">
					<td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
					<td bgcolor='#99ff99' align=center><a href="/<msp:webapp/>displayLegacyCourtOrder.do?submitAction=Link&juvenileNum=<bean:write name='petitionDetailsForm' property='juvenileNum'/>&petitionNum=<bean:write name='petitionDetailsForm' property='petitionNum'/>" class=topNav>Court Order</a></td>
					<td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
				</logic:notEqual>		
				<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
			</jims2:isAllowed>	
			
			<logic:equal name="tab" value="dispositions">
		       <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
		      <td bgcolor='#33cc66' align=center><a href="/<msp:webapp/>displayJuvenileCasefileDispositionList.do" class=topNav>Dispositions</a></td>
		       <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
			</logic:equal>	
			<logic:notEqual name="tab" value="dispositions">
				<td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
				<td bgcolor='#99ff99' align=center><a href="/<msp:webapp/>displayJuvenileCasefileDispositionList.do" class=topNav>Dispositions</a></td>
				<td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
			</logic:notEqual>	
			<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>	
		
			<logic:equal name="tab" value="fees">
		      <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
		        <td bgcolor='#33cc66' align=center><a href="/<msp:webapp/>displayJuvenileCasefileFeeList.do" class=topNav>Fees</a></td>
		        <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
			</logic:equal>	
			<logic:notEqual name="tab" value="fees">
				<td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
				<td bgcolor='#99ff99' align=center><a href="/<msp:webapp/>displayJuvenileCasefileFeeList.do" class=topNav>Fees</a></td>
				<td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
			</logic:notEqual>	
			<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>	
		</tr>
	</table>
	<%--/html:form--%>	
	<%-- END FORM --%>

