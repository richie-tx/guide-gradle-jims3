<!DOCTYPE HTML>

<%-- Manages Tabs for Juvenile Casefiles --%>
<%-- 05/20/2005		glyons	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" /> 

	<msp:nocache />
	<meta http-equiv="Content-Language" content="en-us">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<meta name="GENERATOR" content="IBM WebSphere Studio">
	<meta http-equiv="Content-Style-Type" content="text/css">

	<%-- STYLE SHEET LINK --%>
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
	<html:base />
	<title><bean:message key="title.heading"/>/socialHistoryTabs.jsp</title>
	<!-- <script type="text/javascript">
	 function showAlert()
	{
		var reportFlag			   = sessionStorage.getItem("isReportGenerated");		
		if ( reportFlag != null && "true" == reportFlag ) 
		{		
			alert('Documents can be located in the Documents tab');
		}
	} 
	</script> -->
</head> 

<%-- START PROFILE TABS --%>
<table border=0 cellpadding=0 cellspacing=0>
  <tr>
  	<bean:define id="tab"><tiles:getAsString name="tabid" /></bean:define>
  	
  	<logic:equal name="tab" value="jpoData">
      <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
  		<td bgcolor='#ff6666' align=center><a href="/<msp:webapp/>handleSocialHistoryData.do?submitAction=Link&tabId=jpoData" class=topNav>JPO Data</a></td><!-- onclick="showAlert();" -->
      <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>				
  	</logic:equal>
  	<logic:notEqual name="tab" value="jpoData">
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>
  		<td bgcolor='#ffcccc' align=center><a href="/<msp:webapp/>handleSocialHistoryData.do?submitAction=Link&tabId=jpoData" class=topNav>JPO Data</a></td><!-- onclick="showAlert();"  -->
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>
  	</logic:notEqual>
  	
  	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>
  	<logic:equal name="tab" value="presentOffense">
  		<td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
  		<td bgcolor='#ff6666' align=center><a href="/<msp:webapp/>handleSocialHistoryData.do?submitAction=Link&tabId=presentOffense" class=topNav>Present Offense</a></td><!-- onclick="showAlert();" --> 
  		<td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>				
  	</logic:equal>
  	<logic:notEqual name="tab" value="presentOffense">
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>
  		<td bgcolor='#ffcccc' align=center><a onclick="showAlert();" href="/<msp:webapp/>handleSocialHistoryData.do?submitAction=Link&tabId=presentOffense" class=topNav>Present Offense</a></td>
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>
  	</logic:notEqual>
  
  	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>
  	<logic:equal name="tab" value="juvInfo">
      <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
  		<td bgcolor='#ff6666' align=center><a onclick="showAlert();" href="/<msp:webapp/>handleSocialHistoryData.do?submitAction=Link&tabId=juvInfo" class=topNav>Juvenile Info</a></td>
      <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>				
  	</logic:equal>
  	<logic:notEqual name="tab" value="juvInfo">
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>
  		<td bgcolor='#ffcccc' align=center><a onclick="showAlert();" href="/<msp:webapp/>handleSocialHistoryData.do?submitAction=Link&tabId=juvInfo" class=topNav>Juvenile Info</a></td>
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>
  	</logic:notEqual>	
  	
  	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>
  	<logic:equal name="tab" value="school">
      <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
  		<td bgcolor='#ff6666' align=center><a href="/<msp:webapp/>handleSocialHistoryData.do?submitAction=Link&tabId=school" class=topNav>School</a></td>
      <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>				
  	</logic:equal>
  	<logic:notEqual name="tab" value="school">
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>
  		<td bgcolor='#ffcccc' align=center><a href="/<msp:webapp/>handleSocialHistoryData.do?submitAction=Link&tabId=school" class=topNav>School</a></td>
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>
  	</logic:notEqual>	
  	
  	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>
  	<logic:equal name="tab" value="jobs">
      <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
  		<td bgcolor='#ff6666' align=center><a href="/<msp:webapp/>handleSocialHistoryData.do?submitAction=Link&tabId=jobs" class=topNav>Jobs</a></td>
      <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>				
  	</logic:equal>
  	<logic:notEqual name="tab" value="jobs">
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>
  		<td bgcolor='#ffcccc' align=center><a href="/<msp:webapp/>handleSocialHistoryData.do?submitAction=Link&tabId=jobs" class=topNav>Jobs</a></td>
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>
  	</logic:notEqual>	
  	
  	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>
  	<logic:equal name="tab" value="financialHistory">
      <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
  		<td bgcolor='#ff6666' align=center><a href="/<msp:webapp/>handleSocialHistoryData.do?submitAction=Link&tabId=financialHistory" class=topNav>Financial History</a></td>
      <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>				
  	</logic:equal>
  	<logic:notEqual name="tab" value="financialHistory">
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>
  		<td bgcolor='#ffcccc' align=center><a href="/<msp:webapp/>handleSocialHistoryData.do?submitAction=Link&tabId=financialHistory" class=topNav>Financial History</a></td>
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>
  	</logic:notEqual>	
  	
  	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>
  	<logic:equal name="tab" value="supervisionRules">
      <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
  		<td bgcolor='#ff6666' align=center><a href="/<msp:webapp/>handleSocialHistoryData.do?submitAction=Link&tabId=supervisionRules" class=topNav>Supervision Rules</a></td>
      <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>				
  	</logic:equal>
  	<logic:notEqual name="tab" value="supervisionRules">
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>
  		<td bgcolor='#ffcccc' align=center><a href="/<msp:webapp/>handleSocialHistoryData.do?submitAction=Link&tabId=supervisionRules" class=topNav>Supervision Rules</a></td>
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>
  	</logic:notEqual>
  	
  	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>
  	<logic:equal name="tab" value="warrantHistory">
      <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
  		<td bgcolor='#ff6666' align=center><a href="/<msp:webapp/>handleSocialHistoryData.do?submitAction=Link&tabId=warrantHistory" class=topNav>Warrant History</a></td>
      <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>				
  	</logic:equal>
  	<logic:notEqual name="tab" value="warrantHistory">
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>
  		<td bgcolor='#ffcccc' align=center><a href="/<msp:webapp/>handleSocialHistoryData.do?submitAction=Link&tabId=warrantHistory" class=topNav>Warrant History</a></td>
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>
  	</logic:notEqual>
  	
  	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>
  	<logic:equal name="tab" value="referralHistory">
      <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
  		<td bgcolor='#ff6666' align=center><a href="/<msp:webapp/>handleSocialHistoryData.do?submitAction=Link&tabId=referralHistory" class=topNav>Referral History</a></td>
      <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>				
  	</logic:equal>
  	<logic:notEqual name="tab" value="referralHistory">
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>
  		<td bgcolor='#ffcccc' align=center><a href="/<msp:webapp/>handleSocialHistoryData.do?submitAction=Link&tabId=referralHistory" class=topNav>Referral History</a></td>
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>
  	</logic:notEqual>
  		
  	
  	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>	
  </tr>
</table>
<%-- END PROFILE TABS --%>

