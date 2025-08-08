<!DOCTYPE HTML>

<%-- Manages Tabs for Juvenile Casefiles --%>
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
	<title><bean:message key="title.heading"/>/mentalHealthTabs.jsp</title>
</head> 

<table border="0" cellpadding="0" cellspacing="0">
	<tr>
			<bean:define id="tab"><tiles:getAsString name="tabid" /></bean:define>
			<bean:define id="requestParam"><%=PDJuvenileCaseConstants.JUVENILENUM_PARAM%>=<bean:write name="juvenileProfileHeader" property="juvenileNum"/></bean:define>

	
			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MH_MAYSI%>'> 
  			<logic:equal name="tab" value="maysi">
          <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
          <td bgcolor='#ff6666' align=center><a href="/<msp:webapp/>displayMAYSIResults.do?<bean:write name="requestParam" />" class=topNav>MAYSI</a></td>
          <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>				
  			</logic:equal>
  			<logic:notEqual name="tab" value="maysi">
  				<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>
  				<td bgcolor='#ffcccc' align=center><a href="/<msp:webapp/>displayMAYSIResults.do?<bean:write name="requestParam" />" class=topNav>MAYSI</a></td>
  				<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>
  			</logic:notEqual>		
  			<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
 			</jims2:isAllowed>  

			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MH_HOSP%>'> 
  			<logic:equal name="tab" value="hospitalization">
          <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
          <td bgcolor='#ff6666' align=center><a href="/<msp:webapp/>displayMentalHealthHospitalizationList.do" class=topNav>Hospitalization</a></td>
          <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>				
  			</logic:equal>
  			<logic:notEqual name="tab" value="hospitalization">
  				<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>			
  				<td bgcolor='#ffcccc' align=center><a href="/<msp:webapp/>displayMentalHealthHospitalizationList.do?<bean:write name="requestParam" />" class=topNav>Hospitalization</a></td>
  				<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>
  			</logic:notEqual>		
  			<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
 			</jims2:isAllowed>

 			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MH_TEST%>'>
  			<logic:equal name="tab" value="testingsession">
          <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
          <td bgcolor='#ff6666' align=center><a href="/<msp:webapp/>displayMentalHealthTestingResults.do?<bean:write name="requestParam" />" class=topNav>Testing Session</a> </td>
          <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>				
  			</logic:equal>
  			<logic:notEqual name="tab" value="testingsession">
  				<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>
  				<td bgcolor='#ffcccc' align=center><a href="/<msp:webapp/>displayMentalHealthTestingResults.do?<bean:write name="requestParam" />" class=topNav>Testing Session</a></td>
  				<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>
  			</logic:notEqual>		
  			<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>	
			</jims2:isAllowed>

 			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MH_DSM%>'>
  			<logic:equal name="tab" value="dsm">
          <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
          <td bgcolor='#ff6666' align=center><a href="/<msp:webapp/>displayMentalHealthDSMIVResults.do?<bean:write name="requestParam" />" class=topNav>DSM V</a></td>
          <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>					
  			</logic:equal>	
  			<logic:notEqual name="tab" value="dsm">
  				<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>
  				<td bgcolor='#ffcccc' align=center><a href="/<msp:webapp/>displayMentalHealthDSMIVResults.do?<bean:write name="requestParam" />" class=topNav>DSM V</a></td>
  				<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>
  			</logic:notEqual>	
  			<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>	
 			</jims2:isAllowed>

 			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MH_IQ%>'>
  			<logic:equal name="tab" value="iq">
          <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
          <td bgcolor='#ff6666' align=center><a href="/<msp:webapp/>displayMentalHealthIQResults.do?<bean:write name="requestParam" />" class=topNav>IQ</a></td>
          <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>					
  			</logic:equal>	
  			<logic:notEqual name="tab" value="iq">
  				<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>
  				<td bgcolor='#ffcccc' align=center><a href="/<msp:webapp/>displayMentalHealthIQResults.do?<bean:write name="requestParam" />" class=topNav>IQ</a></td>
  				<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>
  			</logic:notEqual>	
  			<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>	
 			</jims2:isAllowed>

 			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MH_AT%>'>
  			<logic:equal name="tab" value="achievement">
          <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
          <td bgcolor='#ff6666' align=center><a href="/<msp:webapp/>displayMentalHealthAchievementResults.do?<bean:write name="requestParam" />" class=topNav>Achievement</a></td>
          <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>					
  			</logic:equal>	
  			<logic:notEqual name="tab" value="achievement">
  				<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>
  				<td bgcolor='#ffcccc' align=center><a href="/<msp:webapp/>displayMentalHealthAchievementResults.do?<bean:write name="requestParam" />" class=topNav>Achievement</a></td>
  				<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>
  			</logic:notEqual>
  			<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>	
 			</jims2:isAllowed>

 			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MH_AF%>'>
  			<logic:equal name="tab" value="adaptivefunctioning">
          <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
          <td bgcolor='#ff6666' align=center ><a href="/<msp:webapp/>displayMentalHealthAdaptiveFuncResults.do?<bean:write name="requestParam" />" class=topNav>Adaptive Functioning</a></td>
          <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>					
  			</logic:equal>	
  			<logic:notEqual name="tab" value="adaptivefunctioning">
  				<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>
  				<td bgcolor='#ffcccc' align=center ><a href="/<msp:webapp/>displayMentalHealthAdaptiveFuncResults.do?<bean:write name="requestParam" />" class=topNav>Adaptive Functioning</a></td>
  				<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>
  			</logic:notEqual>	
  			<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>	
			</jims2:isAllowed>
			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MH_AB%>'>
  			<logic:equal name="tab" value="adaptiveBehavior">
          <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
          <td bgcolor='#ff6666' align=center ><a href="/<msp:webapp/>displayMentalHealthABResults.do?<bean:write name="requestParam" />" class=topNav>Adaptive Behavior</a></td>
          <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>					
  			</logic:equal>	
  			<logic:notEqual name="tab" value="adaptiveBehavior">
  				<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>
  				<td bgcolor='#ffcccc' align=center ><a href="/<msp:webapp/>displayMentalHealthABResults.do?<bean:write name="requestParam" />" class=topNav>Adaptive Behavior</a></td>
  				<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>
  			</logic:notEqual>	
  			<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>	
			</jims2:isAllowed>
	</tr>
</table>
