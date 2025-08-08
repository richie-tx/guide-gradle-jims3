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
<title><bean:message key="title.heading"/>/commonAppFormTabs.jsp</title>
</head>

<table  border=0 cellpadding=0 cellspacing=0>
	<tr>
		<bean:define id="tab">
			<tiles:getAsString name="tabid" />
		</bean:define>
		
  		<logic:equal name="tab" value="JuvenileDetails">
      		<td bgcolor='#ff6666' valign=top><img src='/<msp:webapp/>images/left_red_active_tab.gif'></td>
		    <td bgcolor='#ff6666' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppCourtOrderUpdate.do?submitAction=Link" class=topNav>Juvenile Details</a></td>
		    <td bgcolor='#ff6666' valign=top><img src='/<msp:webapp/>images/right_red_active_tab.gif'></td>	
  		</logic:equal>

  		<logic:notEqual name="tab" value="JuvenileDetails">
  			<td bgcolor='#ffcccc' valign=top><img src='/<msp:webapp/>images/left_red_inactive_tab.gif'></td>
  			<td bgcolor='#ffcccc' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppCourtOrderUpdate.do?submitAction=Link" class=topNav>Juvenile Details</a></td>
  			<td bgcolor='#ffcccc' valign=top><img src='/<msp:webapp/>images/right_red_inactive_tab.gif'></td>
  		</logic:notEqual>	
  	
  		<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>	
        
  		<logic:equal name="tab" value="commonAppReport">
      		<td bgcolor='#ff6666' valign=top><img src='/<msp:webapp/>images/left_red_active_tab.gif'></td>
      		<td bgcolor='#ff6666' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppExitReportDetails.do?submitAction=Link" class=topNav>Common App Report</a></td>
      		<td bgcolor='#ff6666' valign=top><img src='/<msp:webapp/>images/right_red_active_tab.gif'></td>				
  		</logic:equal>

  		<logic:notEqual name="tab" value="commonAppReport">
  			<td bgcolor='#ffcccc' valign=top><img src='/<msp:webapp/>images/left_red_inactive_tab.gif'></td>
  			<td bgcolor='#ffcccc' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppExitReportDetails.do?submitAction=Link" class=topNav>Common App Report</a></td>
  			<td bgcolor='#ffcccc' valign=top><img src='/<msp:webapp/>images/right_red_inactive_tab.gif'></td>
  		</logic:notEqual>
  	
  		<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>	
        
  		<logic:equal name="tab" value="ViewHistoryReport">
      		<td bgcolor='#ff6666' valign=top><img src='/<msp:webapp/>images/left_red_active_tab.gif'></td>
      		<td bgcolor='#ff6666' align=center class=topNav><a href="/<msp:webapp/>displayExitReports.do?submitAction=Link&fromCommonApp=true" class=topNav>View History Report</a></td>
      		<td bgcolor='#ff6666' valign=top><img src='/<msp:webapp/>images/right_red_active_tab.gif'></td>				
  		</logic:equal>

  		<logic:notEqual name="tab" value="ViewHistoryReport">
  			<td bgcolor='#ffcccc' valign=top><img src='/<msp:webapp/>images/left_red_inactive_tab.gif'></td>
  			<td bgcolor='#ffcccc' align=center class=topNav><a href="/<msp:webapp/>displayExitReports.do?submitAction=Link&fromCommonApp=true" class=topNav>View History Report</a></td>
  			<td bgcolor='#ffcccc' valign=top><img src='/<msp:webapp/>images/right_red_inactive_tab.gif'></td>
  		</logic:notEqual>
  		
  		<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
			
	</tr>
</table>
