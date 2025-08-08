<!DOCTYPE HTML>

<%-- Manages Tabs for Juvenile Casefiles Constellation stuff--%>
<%-- 05/20/2005		glyons	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title><bean:message key="title.heading"/>/commonAppJuvDetTabs.jsp</title>
</head>

<table border='0' cellpadding='0' cellspacing='0'>
  <tr>
  	<bean:define id="tab"><tiles:getAsString name="tabid" /></bean:define>
  
  	<logic:equal name="tab" value="CourtOrder">
      <td bgcolor='#008080' valign='top'><img src='/<msp:webapp/>images/left_teal_active_tab.png'></td>
      <td bgcolor='#008080' align='center' class='topNav'><a href="/<msp:webapp/>displayCommonAppCourtOrderUpdate.do?submitAction=Link" class='topNav'>Court Order</a></td>
      <td bgcolor='#008080' valign='top'><img src='/<msp:webapp/>images/right_teal_active_tab.png'></td>				
  	</logic:equal>
  	<logic:notEqual name="tab" value="CourtOrder">
  		<td bgcolor='#00FFFF' valign='top'><img src='/<msp:webapp/>images/left_teal_inactive_tab.png'></td>
  		<td bgcolor='#00FFFF' align='center' class='topNav'><a href="/<msp:webapp/>displayCommonAppCourtOrderUpdate.do?submitAction=Link" class='topNav'>Court Order</a></td>
  		<td bgcolor='#00FFFF' valign='top'><img src='/<msp:webapp/>images/right_teal_inactive_tab.png'></td>
  	</logic:notEqual>
  
  	<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width='2'></td>
  	
  	<logic:equal name="tab" value="Placement">
      <td bgcolor='#008080' valign='top'><img src='/<msp:webapp/>images/left_teal_active_tab.png'></td>
      <td bgcolor='#008080' align='center' class='topNav'><a href="/<msp:webapp/>displayCommonAppPlacementHistory.do?submitAction=Link" class='topNav'>Placement</a></td>
      <td bgcolor='#008080' valign='top'><img src='/<msp:webapp/>images/right_teal_active_tab.png'></td>				
  	</logic:equal>
  	<logic:notEqual name="tab" value="Placement">
  		<td bgcolor='#00FFFF' valign='top'><img src='/<msp:webapp/>images/left_teal_inactive_tab.png'></td>
  		<td bgcolor='#00FFFF' align='center' class='topNav'><a href="/<msp:webapp/>displayCommonAppPlacementHistory.do?submitAction=Link" class='topNav'>Placement</a></td>
  		<td bgcolor='#00FFFF' valign='top'><img src='/<msp:webapp/>images/right_teal_inactive_tab.png'></td>
  	</logic:notEqual>
  		
  	<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width='2'></td>	
  	
  	<logic:equal name="tab" value="Financial">
      <td bgcolor='#008080' valign='top'><img src='/<msp:webapp/>images/left_teal_active_tab.png'></td>
      <td bgcolor='#008080' align='center' class='topNav'><a href="/<msp:webapp/>displayCommonAppFinancialDetails.do?submitAction=Link" class='topNav'>Financial</a></td>
      <td bgcolor='#008080' valign='top'><img src='/<msp:webapp/>images/right_teal_active_tab.png'></td>				
  	</logic:equal>
  	<logic:notEqual name="tab" value="Financial">
  		<td bgcolor='#00FFFF' valign='top'><img src='/<msp:webapp/>images/left_teal_inactive_tab.png'></td>
  		<td bgcolor='#00FFFF' align='center' class='topNav'><a href="/<msp:webapp/>displayCommonAppFinancialDetails.do?submitAction=Link" class='topNav'>Financial</a></td>
  		<td bgcolor='#00FFFF' valign='top'><img src='/<msp:webapp/>images/right_teal_inactive_tab.png'></td>
  	</logic:notEqual>
  	
  	<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width='2'></td>
			
	</tr>
</table>
