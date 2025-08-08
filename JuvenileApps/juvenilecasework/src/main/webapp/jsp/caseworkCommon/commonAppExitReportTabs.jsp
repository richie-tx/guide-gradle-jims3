<!DOCTYPE HTML>

<%-- Manages Tabs for Common App Exit Report stuff--%>
<%-- 10/09/2007		ugopinath	Create JSP --%>

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
<title><bean:message key="title.heading"/>/commonAppExitReportsTabs.jsp</title>
</head>

<table border=0 cellpadding=0 cellspacing=0>
	<tr>
		<bean:define id="tab">
			<tiles:getAsString name="tabid" />
		</bean:define>
		<%--Common App ER 11046 changes starts --%>
  		<logic:equal name="tab" value="Questions">
      		<td bgcolor='#008080' valign=top><img src='/<msp:webapp/>images/left_teal_active_tab.png'></td>
      		<td bgcolor='#008080' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppExitReportDetails.do?submitAction=Link&reportType=CAER" class=topNav>Questions</a></td> <%--Common App ER 11046 changes --%>
      		<td bgcolor='#008080' valign=top><img src='/<msp:webapp/>images/right_teal_active_tab.png'></td>				
  		</logic:equal>
		
  		<logic:notEqual name="tab" value="Questions">
  			<td bgcolor='#00FFFF' valign=top><img src='/<msp:webapp/>images/left_teal_inactive_tab.png'></td>
	  		<td bgcolor='#00FFFF' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppExitReportDetails.do?submitAction=Link&reportType=CAER" class=topNav>Questions</a></td>
  			<td bgcolor='#00FFFF' valign=top><img src='/<msp:webapp/>images/right_teal_inactive_tab.png'></td>
	  	</logic:notEqual>	
	  	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>
		
  		<logic:equal name="tab" value="screeningProfile">
      		<td bgcolor='#008080' valign=top><img src='/<msp:webapp/>images/left_teal_active_tab.png'></td>
      		<td bgcolor='#008080' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppExitReportDetails.do?submitAction=Link&reportType=CASP" class=topNav>Screening Profile</a></td>
      		<td bgcolor='#008080' valign=top><img src='/<msp:webapp/>images/right_teal_active_tab.png'></td>				
  		</logic:equal>

  		<logic:notEqual name="tab" value="screeningProfile">
  			<td bgcolor='#00FFFF' valign=top><img src='/<msp:webapp/>images/left_teal_inactive_tab.png'></td>
	  		<td bgcolor='#00FFFF' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppExitReportDetails.do?submitAction=Link&reportType=CASP" class=topNav>Screening Profile</a></td>
  			<td bgcolor='#00FFFF' valign=top><img src='/<msp:webapp/>images/right_teal_inactive_tab.png'></td>
	  	</logic:notEqual>	
	  	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>
	  	
	  	<logic:equal name="tab" value="facilityDetails">
      		<td bgcolor='#008080' valign=top><img src='/<msp:webapp/>images/left_teal_active_tab.png'></td>
      		<td bgcolor='#008080' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppExitReportDetails.do?submitAction=Link" class=topNav>Facility Details</a></td>
      		<td bgcolor='#008080' valign=top><img src='/<msp:webapp/>images/right_teal_active_tab.png'></td>				
  		</logic:equal>

  		<logic:notEqual name="tab" value="facilityDetails">
  			<td bgcolor='#00FFFF' valign=top><img src='/<msp:webapp/>images/left_teal_inactive_tab.png'></td>
	  		<td bgcolor='#00FFFF' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppExitReportDetails.do?submitAction=Link" class=topNav>Facility Details</a></td>
  			<td bgcolor='#00FFFF' valign=top><img src='/<msp:webapp/>images/right_teal_inactive_tab.png'></td>
	  	</logic:notEqual>	
  		<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>	
  		
  		<logic:equal name="tab" value="socialAssessment">
      		<td bgcolor='#008080' valign=top><img src='/<msp:webapp/>images/left_teal_active_tab.png'></td>
      		<td bgcolor='#008080' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppExitReportDetails.do?submitAction=Link" class=topNav>Social/Development Assessment</a></td>
      		<td bgcolor='#008080' valign=top><img src='/<msp:webapp/>images/right_teal_active_tab.png'></td>				
  		</logic:equal>

  		<logic:notEqual name="tab" value="socialAssessment">
  			<td bgcolor='#00FFFF' valign=top><img src='/<msp:webapp/>images/left_teal_inactive_tab.png'></td>
	  		<td bgcolor='#00FFFF' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppExitReportDetails.do?submitAction=Link" class=topNav>Social/Development Assessment</a></td>
  			<td bgcolor='#00FFFF' valign=top><img src='/<msp:webapp/>images/right_teal_inactive_tab.png'></td>
	  	</logic:notEqual>	
  		<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>
  		
  		<logic:equal name="tab" value="delinquencyHistory">
      		<td bgcolor='#008080' valign=top><img src='/<msp:webapp/>images/left_teal_active_tab.png'></td>
      		<td bgcolor='#008080' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppExitReportDetails.do?submitAction=Link" class=topNav>Delinquency History</a></td>
      		<td bgcolor='#008080' valign=top><img src='/<msp:webapp/>images/right_teal_active_tab.png'></td>				
  		</logic:equal>

  		<logic:notEqual name="tab" value="delinquencyHistory">
  			<td bgcolor='#00FFFF' valign=top><img src='/<msp:webapp/>images/left_teal_inactive_tab.png'></td>
	  		<td bgcolor='#00FFFF' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppExitReportDetails.do?submitAction=Link" class=topNav>Delinquency History</a></td>
  			<td bgcolor='#00FFFF' valign=top><img src='/<msp:webapp/>images/right_teal_inactive_tab.png'></td>
	  	</logic:notEqual>	
  		<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>
  		
  		<logic:equal name="tab" value="specialNeeds">
      		<td bgcolor='#008080' valign=top><img src='/<msp:webapp/>images/left_teal_active_tab.png'></td>
      		<td bgcolor='#008080' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppExitReportDetails.do?submitAction=Link" class=topNav>Special Needs</a></td>
      		<td bgcolor='#008080' valign=top><img src='/<msp:webapp/>images/right_teal_active_tab.png'></td>				
  		</logic:equal>

  		<logic:notEqual name="tab" value="specialNeeds">
  			<td bgcolor='#00FFFF' valign=top><img src='/<msp:webapp/>images/left_teal_inactive_tab.png'></td>
	  		<td bgcolor='#00FFFF' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppExitReportDetails.do?submitAction=Link" class=topNav>Special Needs</a></td>
  			<td bgcolor='#00FFFF' valign=top><img src='/<msp:webapp/>images/right_teal_inactive_tab.png'></td>
	  	</logic:notEqual>	
  		<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>
  		
  		<logic:equal name="tab" value="substanceAbuseHistory">
      		<td bgcolor='#008080' valign=top><img src='/<msp:webapp/>images/left_teal_active_tab.png'></td>
      		<td bgcolor='#008080' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppExitReportDetails.do?submitAction=Link" class=topNav>Substance Abuse History</a></td>
      		<td bgcolor='#008080' valign=top><img src='/<msp:webapp/>images/right_teal_active_tab.png'></td>				
  		</logic:equal>

  		<logic:notEqual name="tab" value="substanceAbuseHistory">
  			<td bgcolor='#00FFFF' valign=top><img src='/<msp:webapp/>images/left_teal_inactive_tab.png'></td>
	  		<td bgcolor='#00FFFF' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppExitReportDetails.do?submitAction=Link" class=topNav>Substance Abuse History</a></td>
  			<td bgcolor='#00FFFF' valign=top><img src='/<msp:webapp/>images/right_teal_inactive_tab.png'></td>
	  	</logic:notEqual>	
  		<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>
  		
  		<logic:equal name="tab" value="familyHistory">
      		<td bgcolor='#008080' valign=top><img src='/<msp:webapp/>images/left_teal_active_tab.png'></td>
      		<td bgcolor='#008080' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppExitReportDetails.do?submitAction=Link" class=topNav>Family History</a></td>
      		<td bgcolor='#008080' valign=top><img src='/<msp:webapp/>images/right_teal_active_tab.png'></td>				
  		</logic:equal>

  		<logic:notEqual name="tab" value="familyHistory">
  			<td bgcolor='#00FFFF' valign=top><img src='/<msp:webapp/>images/left_teal_inactive_tab.png'></td>
	  		<td bgcolor='#00FFFF' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppExitReportDetails.do?submitAction=Link" class=topNav>Family History</a></td>
  			<td bgcolor='#00FFFF' valign=top><img src='/<msp:webapp/>images/right_teal_inactive_tab.png'></td>
	  	</logic:notEqual>	
  		<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>
  		<%--Common App ER 11046 changes ends --%>

  		<logic:equal name="tab" value="DSMDiagnosis">
      		<td bgcolor='#008080' valign=top><img src='/<msp:webapp/>images/left_teal_active_tab.png'></td>
      		<td bgcolor='#008080' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppDSMDiagnosis.do?submitAction=Link" class=topNav>DSM</a></td>
      		<td bgcolor='#008080' valign=top><img src='/<msp:webapp/>images/right_teal_active_tab.png'></td>				
  		</logic:equal>

  		<logic:notEqual name="tab" value="DSMDiagnosis">
  			<td bgcolor='#00FFFF' valign=top><img src='/<msp:webapp/>images/left_teal_inactive_tab.png'></td>
  			<td bgcolor='#00FFFF' align=center class=topNav><a href="/<msp:webapp/>displayCommonAppDSMDiagnosis.do?submitAction=Link" class=topNav>DSM</a></td>
  			<td bgcolor='#00FFFF' valign=top><img src='/<msp:webapp/>images/right_teal_inactive_tab.png'></td>
  		</logic:notEqual>
  	
  		<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>
			
	</tr>
</table>

