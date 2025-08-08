<!DOCTYPE HTML>

<%-- Manages Petition Tabs for Juvenile Profiles --%>
<%-- 08/08/2007		ugopinath	Create JSP --%>

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
	<title><bean:message key="title.heading"/>/juvenileProfilePetitionTabs.jsp</title>
</head> 

<%-- start table --%>

<table border=0 cellpadding=0 cellspacing=0>
	<tr>
		<bean:define id="tab"><tiles:getAsString name="tabid" /></bean:define>
			
  	<logic:equal name="tab" value="petition">
      <td bgcolor='#6699FF' align=center><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
      <td bgcolor='#6699FF' align=center><a href="/<msp:webapp/>displayJuvenileProfilePetitionDetails.do?submitAction=View&notDetailed=false" class=topNav>Petition Details</a></td>
      <td bgcolor='#6699FF' align=center><img src="/<msp:webapp/>images/right_active_tab.gif"></td>				
  	</logic:equal>
  	<logic:notEqual name="tab" value="petition">
  		<td bgcolor='#B3C9F5' align=center><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>			
  		<td bgcolor='#B3C9F5' align=center><a href="/<msp:webapp/>displayJuvenileProfilePetitionDetails.do?submitAction=View&notDetailed=false" class=topNav>Petition Details</a></td>
  		<td bgcolor='#B3C9F5' align=center><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
  	</logic:notEqual>		
  
  	<td valign=top><img src'=/<msp:webapp/>images/spacer.gif' width=2></td>
  	<jims2:isAllowed requiredFeatures='<%=Features.JCW_VIEW_COURTORDER%>'>
	  	<logic:equal name="tab" value="courtOrder">
	      <td bgcolor='#6699FF' align=center><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
	      <td bgcolor='#6699FF' align=center><a href="/<msp:webapp/>displayJuvenileProfileCourtOrderList.do?submitAction=Link&juvenileNum=<bean:write name='petitionDetailsForm' property='juvenileNum'/>&petitionNum=<bean:write name='petitionDetailsForm' property='petitionNum'/>" class=topNav>Court Order</a> </td>
	      <td bgcolor='#6699FF' align=center><img src="/<msp:webapp/>images/right_active_tab.gif"></td>				
	  	</logic:equal>
	  	<logic:notEqual name="tab" value="courtOrder">
	  		<td bgcolor='#B3C9F5' align=center><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
	  		<td bgcolor='#B3C9F5' align=center><a href="/<msp:webapp/>displayJuvenileProfileCourtOrderList.do?submitAction=Link&juvenileNum=<bean:write name='petitionDetailsForm' property='juvenileNum'/>&petitionNum=<bean:write name='petitionDetailsForm' property='petitionNum'/>" class=topNav>Court Order</a></td>
	  		<td bgcolor='#B3C9F5' align=center><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
	  	</logic:notEqual>		
  </jims2:isAllowed>
  
  	<td valign=top><img src'=/<msp:webapp/>images/spacer.gif' width=2></td>	
  	<jims2:isAllowed requiredFeatures='<%=Features.JCW_VIEW_COURTORDER%>'>
	  	<logic:equal name="tab" value="dispositions">
	      <td bgcolor='#6699FF' align=center><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
	      <td bgcolor='#6699FF' align=center><a href="/<msp:webapp/>displayJuvenileProfileDispositionList.do" class=topNav>Dispositions</a></td>
	      <td bgcolor='#6699FF' align=center><img src="/<msp:webapp/>images/right_active_tab.gif"></td>					
	  	</logic:equal>	
	  	<logic:notEqual name="tab" value="dispositions">
	  		<td bgcolor='#B3C9F5' align=center><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
	  		<td bgcolor='#B3C9F5' align=center><a href="/<msp:webapp/>displayJuvenileProfileDispositionList.do" class=topNav>Dispositions</a></td>
	  		<td bgcolor='#B3C9F5' align=center><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
	  	</logic:notEqual>	
  	</jims2:isAllowed>
  	<td valign=top><img src'=/<msp:webapp/>images/spacer.gif' width=2></td>	
  
  	<jims2:isAllowed requiredFeatures='<%=Features.JCW_VIEW_COURTORDER%>'>
	  	<logic:equal name="tab" value="fees">
	      <td bgcolor='#6699FF' valign=center><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
	      <td bgcolor='#6699FF' align=center><a href="/<msp:webapp/>displayJuvenileProfileFeeList.do" class=topNav>Fees</a></td>
	      <td bgcolor='#6699FF' align=center><img src="/<msp:webapp/>images/right_active_tab.gif"></td>					
	  	</logic:equal>	
	  	<logic:notEqual name="tab" value="fees">
	  		<td bgcolor='#B3C9F5' align=center><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
	  		<td bgcolor='#B3C9F5' align=center><a href="/<msp:webapp/>displayJuvenileProfileFeeList.do" class=topNav>Fees</a></td>
	  		<td bgcolor='#B3C9F5' align=center><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
	  	</logic:notEqual>	
  	</jims2:isAllowed>
  	
  	<td valign=top><img src'=/<msp:webapp/>images/spacer.gif' width=2></td>	
  </tr>
</table>
<%-- end table --%>

