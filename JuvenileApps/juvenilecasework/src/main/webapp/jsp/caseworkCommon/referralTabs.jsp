<!DOCTYPE HTML>

<%-- Manages Tabs for Juvenile Casefiles --%>
<%-- 10/25/2006	uGopinath	Create JSP --%>
<%-- 01/17/2007 Hien Rodriguez  ER#37077 Add Tab security features --%>
<%-- 06/04/2013 CShimek		#ER75613 Add Transferred Offenses tab --%>
<%-- 10/16/2013 CShimek		#76247 Added feature edit to Transferred Offenses tab --%>

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
<title><bean:message key="title.heading"/>/referralTabs.jsp</title>
</head>

<bean:define id="jsp"><%= session.getAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY_PAGE) %></bean:define>
<bean:define id="tab"><%= session.getAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY) %></bean:define>

<logic:equal name="jsp" value="CASEFILE">
	<logic:equal name="casefileid" value="">
		<bean:define id="requestParam"><%=PDJuvenileCaseConstants.CASEFILEID_PARAM%>=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>&supervisionNum=<bean:write name="juvenileCasefileForm" property="supervisionNum"/></bean:define>
	</logic:equal>
	<logic:notEqual name="casefileid" value="">
		<bean:define id="requestParam"><%=PDJuvenileCaseConstants.CASEFILEID_PARAM%>=<bean:write name="casefileid" />&supervisionNum=<bean:write name="juvenileCasefileForm" property="supervisionNum"/></bean:define>
	</logic:notEqual>

<%-- START CASEFILE TABS --%>
	<table border='0' cellpadding='0' cellspacing='0'>
		<tr>
			<%-- the JOT tab should display all by itself  --%>			
			<logic:equal name="tab" value="JOT">
		   	    <td bgcolor='#33cc66' valign='top'><img src='/<msp:webapp/>images/left_green_active_tab.gif'></td>
		        <td bgcolor='#33cc66' align='center'><a href="/<msp:webapp/>displayJuvenileCasefileAssignedReferralsList.do?submitAction=Referrals&<bean:write name="requestParam" />" class='topNav'>JOT Details</a></td>
		        <td bgcolor='#33cc66' valign='top'><img src='/<msp:webapp/>images/right_green_active_tab.gif'></td>				
			</logic:equal>
		
			<logic:notEqual name="tab" value="JOT">
				<logic:equal name="tab" value="REFERRAL">
			   	    <td bgcolor='#33cc66' valign='top'><img src='/<msp:webapp/>images/left_green_active_tab.gif'></td>
			        <td bgcolor='#33cc66' align='center'><a href="/<msp:webapp/>displayJuvenileCasefileAssignedReferralsList.do?submitAction=Referrals&<bean:write name="requestParam" />" class='topNav'>Referral List</a></td>
			        <td bgcolor='#33cc66' valign='top'><img src='/<msp:webapp/>images/right_green_active_tab.gif'></td>				
				</logic:equal>
				<logic:notEqual name="tab" value="REFERRAL">
					<td bgcolor='#99ff99' valign='top'><img src='/<msp:webapp/>images/left_green_inactive_tab.gif'></td>
					<td bgcolor='#99ff99' align='center'><a href="/<msp:webapp/>displayJuvenileCasefileAssignedReferralsList.do?submitAction=Referrals&<bean:write name="requestParam" />" class='topNav'>Referral List</a></td>
					<td bgcolor='#99ff99' valign='top'><img src='/<msp:webapp/>images/right_green_inactive_tab.gif'></td>
				</logic:notEqual>		
	
				<td valign='top'><img src='/<msp:webapp/>images/spacer.gif' width='2'></td>
	
				<logic:equal name="tab" value="FACILITY">
		        	<td bgcolor='#33cc66' valign='top'><img src='/<msp:webapp/>images/left_green_active_tab.gif'></td>
		        	<td bgcolor='#33cc66' align='center'><a href="/<msp:webapp/>displayJuvenileCasefileAssignedReferralsList.do?submitAction=Facility&<bean:write name="requestParam" />" class='topNav'>Facility History</a></td>
		        	<td bgcolor='#33cc66' valign='top'><img src='/<msp:webapp/>images/right_green_active_tab.gif'></td>				
				</logic:equal>
				<logic:notEqual name="tab" value="FACILITY">
					<td bgcolor='#99ff99' valign='top'><img src='/<msp:webapp/>images/left_green_inactive_tab.gif'></td>
					<td bgcolor='#99ff99' align='center'><a href="/<msp:webapp/>displayJuvenileCasefileAssignedReferralsList.do?submitAction=Facility&<bean:write name="requestParam" />" class='topNav'>Facility History</a></td>
					<td bgcolor='#99ff99' valign='top'><img src='/<msp:webapp/>images/right_green_inactive_tab.gif'></td>
				</logic:notEqual>
				
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_TRANSOFF_VIEW%>'>		
					<td valign='top'><img src='/<msp:webapp/>images/spacer.gif' width='2'></td>
		
					<logic:equal name="tab" value="TRANSFERRED">
			        	<td bgcolor='#33cc66' valign='top'><img src='/<msp:webapp/>images/left_green_active_tab.gif'></td>
			        	<td bgcolor='#33cc66' align='center'><a href="/<msp:webapp/>displayJuvenileCasefileAssignedReferralsList.do?submitAction=Transfer&<bean:write name="requestParam" />" class='topNav'>Transferred Offenses</a></td>
			        	<td bgcolor='#33cc66' valign='top'><img src='/<msp:webapp/>images/right_green_active_tab.gif'></td>				
					</logic:equal>
					<logic:notEqual name="tab" value="TRANSFERRED">
						<td bgcolor='#99ff99' valign='top'><img src='/<msp:webapp/>images/left_green_inactive_tab.gif'></td>
						<td bgcolor='#99ff99' align='center'><a href="/<msp:webapp/>displayJuvenileCasefileAssignedReferralsList.do?submitAction=Transfer&<bean:write name="requestParam" />" class='topNav'>Transferred Offenses</a></td>
						<td bgcolor='#99ff99' valign='top'><img src='/<msp:webapp/>images/right_green_inactive_tab.gif'></td>
					</logic:notEqual>
				</jims2:isAllowed>		
			</logic:notEqual>

			<td valign='top'><img src='/<msp:webapp/>images/spacer.gif' width='2'></td>
		</tr>
	</table>
</logic:equal>
<%-- END CASEFILE TABS --%>
<%-- START PROFILE TABS --%>
<logic:equal name="jsp" value="PROFILE">
	
	<bean:define id="requestParam"><%=PDJuvenileCaseConstants.JUVENILENUM_PARAM%>=<bean:write name="juvenileProfileHeader" property="juvenileNum"/></bean:define>

	<table border='0' cellpadding='0' cellspacing='0'>
		<tr>
			<%-- the JOT tab should display all by itself  --%>			
			<logic:equal name="tab" value="JOT">
	   			<td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
	   			<td bgcolor='#6699FF' align='center'><a href="/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Referrals&<bean:write name="requestParam" />" class='topNav'>JOT Details</a></td>
	   			<td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/right_active_tab.gif"></td>				
			</logic:equal>

			<logic:notEqual name="tab" value="JOT">
				<logic:equal name="tab" value="REFERRAL">
		   			<td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
		   			<td bgcolor='#6699FF' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Referrals&<bean:write name="requestParam" />" class='topNav'>Referral List</a></td>
		   			<td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/right_active_tab.gif"></td>				
				</logic:equal>
	 			<logic:notEqual name="tab" value="REFERRAL">
	 				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
	 				<td bgcolor='#B3C9F5' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Referrals&<bean:write name="requestParam" />" class='topNav'>Referral List</a></td>
	 				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
	 			</logic:notEqual>
	
	 	<%--	<td valign='top'><img src='/<msp:webapp/>images/spacer.gif' width='2'></td>	
	
				<logic:equal name="tab" value="FACILITY">
	     			<td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
	     			<td bgcolor='#6699FF' align='center'><a href="/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Facility&<bean:write name="requestParam" />" class='topNav'>Facility History</a></td>
	     			<td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/right_active_tab.gif"></td>				
				</logic:equal>
	 			<logic:notEqual name="tab" value="FACILITY">
	 				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
	 				<td bgcolor='#B3C9F5' align='center'><a href="/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Facility&<bean:write name="requestParam" />" class='topNav'>Facility History</a></td>
	 				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
	 			</logic:notEqual>  		--%>
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_TRANSOFF_VIEW%>'>	
					<td valign='top'><img src='/<msp:webapp/>images/spacer.gif' width='2'></td>  
		
					<logic:equal name="tab" value="TRANSFERRED">
			        	<td bgcolor='#6699FF' valign='top'><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
			        	<td bgcolor='#6699FF' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Transfer&<bean:write name="requestParam" />" class='topNav'>Transferred Offenses</a></td>
			        	<td bgcolor='#6699FF' valign='top'><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
					</logic:equal>
					<logic:notEqual name="tab" value="TRANSFERRED">
						<td bgcolor='#B3C9F5' valign='top'><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
						<td bgcolor='#B3C9F5' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Transfer&<bean:write name="requestParam" />" class='topNav'>Transferred Offenses</a></td>
						<td bgcolor='#B3C9F5' valign='top'><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
					</logic:notEqual>
					</jims2:isAllowed>
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_VOP_DETAILS%>'>
				<td valign='top'><img src='/<msp:webapp/>images/spacer.gif' width='2'></td>	
					<logic:equal name="tab" value="VOP">
			        	<td bgcolor='#6699FF' valign='top'><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
			        	<td bgcolor='#6699FF' align='center'><a onclick="spinner()" href="/<msp:webapp/>handleJuvenileProfileVOPsAction.do?submitAction=VOP&<bean:write name="requestParam" />" class='topNav'>Violation of Probation</a></td>
			        	<td bgcolor='#6699FF' valign='top'><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
					</logic:equal>
					<logic:notEqual name="tab" value="VOP">
						<td bgcolor='#B3C9F5' valign='top'><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
						<td bgcolor='#B3C9F5' align='center'><a onclick="spinner()" href="/<msp:webapp/>handleJuvenileProfileVOPsAction.do?submitAction=VOP&<bean:write name="requestParam" />" class='topNav'>Violation of Probation</a></td>
						<td bgcolor='#B3C9F5' valign='top'><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
					</logic:notEqual>
				</jims2:isAllowed>		
			</logic:notEqual>			
		</tr>
	</table>
<%-- END PROFILE TABS --%>
</logic:equal>	