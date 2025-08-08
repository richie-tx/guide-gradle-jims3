<!DOCTYPE HTML>

<%-- Manages Tabs for Juvenile Casefiles Constellation stuff--%>
<%-- 05/20/2005		glyons	Create JSP --%>

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
	<title><bean:message key="title.heading"/>/constellationInfoTabs.jsp</title>
</head> 

	<%-- START FORM --%>
	<%--html:form action="/displayJuvenilexxxxxxxxxxxx.do" target="content" --%>
	<table border=0 cellpadding=0 cellspacing=0>
		<tr>
			<bean:define id="tab"><tiles:getAsString name="tabid" /></bean:define>

			<logic:equal name="juvenileFamilyForm" property="action" value="create">

  			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
    			<logic:equal name="tab" value="ConstellationMembers">
  		        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
  		        <td bgcolor='#6699FF' align=center class=topNav>Constellation Members</td>
  		        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
    			</logic:equal>
    			<logic:notEqual name="tab" value="ConstellationMembers">
    				<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
    				<td bgcolor='#B3C9F5' align=center class=topNav>Constellation Members</td>
    				<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
    			</logic:notEqual>	
    			<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
  			</jims2:isAllowed>
  			
  			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
    			<logic:equal name="tab" value="GuardianInfo">
  		        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
  		        <td bgcolor='#6699FF' align=center class=topNav>Guardian Info</td>
  		        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
    			</logic:equal>
    			<logic:notEqual name="tab" value="GuardianInfo">
    				<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
    				<td bgcolor='#B3C9F5' align=center class=topNav>Guardian Info</td>
    				<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
    			</logic:notEqual>	
    			<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>	
  			</jims2:isAllowed>
  
  			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
    			<logic:equal name="tab" value="ConstellationInfo">
  		        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
  		        <td bgcolor='#6699FF' align=center class=topNav>Summary</td>
  		        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
    			</logic:equal>
    			<logic:notEqual name="tab" value="ConstellationInfo">
    				<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
    				<td bgcolor='#B3C9F5' align=center class=topNav>Summary</td>
    				<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
    			</logic:notEqual>	
   			</jims2:isAllowed>
  		</logic:equal>
			
			<logic:equal name="juvenileFamilyForm" property="action" value="update">
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
    			<logic:equal name="tab" value="ConstellationMembers">
		        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
		        <td bgcolor='#6699FF' align=center><a href="/<msp:webapp/>displayFamilyConstellationDetailsUpdate.do?submitAction=<bean:message key="button.updateActiveConstellation"/>&action=update" class=topNav>Constellation Members</a></td>
		        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
    			</logic:equal>
    			<logic:notEqual name="tab" value="ConstellationMembers">
    				<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
    				<td bgcolor='#B3C9F5' align=center><a href="/<msp:webapp/>displayFamilyConstellationDetailsUpdate.do?submitAction=<bean:message key="button.updateActiveConstellation"/>&action=update" class=topNav>Constellation Members</a></td>
    				<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
    			</logic:notEqual>	
    			<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
   			</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
    			<logic:equal name="tab" value="FamilyFinancial">
		        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
		        <td bgcolor='#6699FF' align=center><a href="/<msp:webapp/>displayFamilyConstellationGuardianUpdateAction.do?submitAction=<bean:message key="button.link"/>" class=topNav>Family Financial</a></td>
		        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
    			</logic:equal>
    			<logic:notEqual name="tab" value="FamilyFinancial">
    				<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
    				<td bgcolor='#B3C9F5' align=center><a href="/<msp:webapp/>displayFamilyConstellationGuardianUpdateAction.do?submitAction=<bean:message key="button.link"/>" class=topNav>Family Financial</a></td>
    				<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
    			</logic:notEqual>		
    			<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
   			</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
    			<logic:equal name="tab" value="Dynamics">
		        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
		        <td bgcolor='#6699FF' align=center><a href="/<msp:webapp/>displayManageFamilyConstellationDynamics.do?submitAction=GO" class=topNav>Dynamics</a></td>
		        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
    			</logic:equal>
    			<logic:notEqual name="tab" value="Dynamics">
    				<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
    				<td bgcolor='#B3C9F5' align=center><a href="/<msp:webapp/>displayManageFamilyConstellationDynamics.do?submitAction=GO" class=topNav>Dynamics</a></td>
    				<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
    			</logic:notEqual>	
  			</jims2:isAllowed>
			</logic:equal>
			
			<logic:equal name="juvenileFamilyForm" property="action" value="view">
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
    			<logic:equal name="tab" value="ConstellationMembers">
		        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
		        <td bgcolor='#6699FF' align=center><a href="/<msp:webapp/>displayFamilyConstellationDetailsUpdate.do?submitAction=<bean:message key="button.updateActiveConstellation"/>&action=update" class=topNav>Constellation Members</a></td>
		        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
    			</logic:equal>
    			<logic:notEqual name="tab" value="ConstellationMembers">
    				<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
    				<td bgcolor='#B3C9F5' align=center><a href="/<msp:webapp/>displayFamilyConstellationDetailsUpdate.do?submitAction=<bean:message key="button.updateActiveConstellation"/>&action=update" class=topNav>Constellation Members</a></td>
    				<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
    			</logic:notEqual>	
    			<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
  			</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
    			<logic:equal name="tab" value="FamilyFinancial">
		        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
		        <td bgcolor='#6699FF' align=center><a href="/<msp:webapp/>displayFamilyConstellationGuardianUpdateAction.do?submitAction=<bean:message key="button.link"/>" class=topNav>Family Financial</a></td>
		        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
    			</logic:equal>
    			<logic:notEqual name="tab" value="FamilyFinancial">
    				<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
    				<td bgcolor='#B3C9F5' align=center><a href="/<msp:webapp/>displayFamilyConstellationGuardianUpdateAction.do?submitAction=<bean:message key="button.link"/>" class=topNav>Family Financial</a></td>
    				<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
    			</logic:notEqual>		
    			<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
   			</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
    			<logic:equal name="tab" value="Dynamics">
		        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
		        <td bgcolor='#6699FF' align=center><a href="/<msp:webapp/>displayManageFamilyConstellationDynamics.do?submitAction=GO" class=topNav>Dynamics</a></td>
		        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
    			</logic:equal>
    			<logic:notEqual name="tab" value="Dynamics">
    				<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
    				<td bgcolor='#B3C9F5' align=center><a href="/<msp:webapp/>displayManageFamilyConstellationDynamics.do?submitAction=GO" class=topNav>Dynamics</a></td>
    				<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
    			</logic:notEqual>	
   			</jims2:isAllowed>
 			</logic:equal>
			
		<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
	</tr>
</table>
