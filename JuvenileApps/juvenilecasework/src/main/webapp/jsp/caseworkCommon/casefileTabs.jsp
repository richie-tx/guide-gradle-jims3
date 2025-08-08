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

<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<title><bean:message key="title.heading"/>/casefileTabs.jsp</title>
</head>

<table width='100%' border='0' cellpadding='0' cellspacing='0'>
 <tr>
   <td>

	<table border='0' cellpadding='0' cellspacing='0'>
		<tr>
			<bean:define id="tab"><tiles:getAsString name="tabid" /></bean:define>
			
			<tiles:importAttribute name="casefileid"/>
			
			<logic:equal name="casefileid" value="">
				<bean:define id="requestParam"><%=PDJuvenileCaseConstants.CASEFILEID_PARAM%>=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>&supervisionNum=<bean:write name="juvenileCasefileForm" property="supervisionNum"/></bean:define>
			</logic:equal>
			<logic:notEqual name="casefileid" value="">
				<bean:define id="requestParam"><%=PDJuvenileCaseConstants.CASEFILEID_PARAM%>=<bean:write name="casefileid" />&supervisionNum=<bean:write name="juvenileCasefileForm" property="supervisionNum"/></bean:define>
			</logic:notEqual>
			
			<bean:parameter id="lockALLTabs" name="lockAll" value="false"></bean:parameter>

			<%-- <jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_CASEFILE%>'> --%>

			<logic:equal name="tab" value="casefiledetailstab">
		      <td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
		      <td bgcolor='#6699FF' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileCasefileDetails.do?<bean:write name="requestParam" />" class='topNav'><bean:message key="prompt.casefile" /></a></td>
       		  <td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/right_active_tab.gif"></td>				
			</logic:equal>

			<logic:notEqual name="tab" value="casefiledetailstab">
				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
				<td bgcolor='#B3C9F5' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileCasefileDetails.do?<bean:write name="requestParam" />" class='topNav'><bean:message key="prompt.casefile" /></a></td>
				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
			</logic:notEqual>		
			<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width='2'></td>	

			<%-- </jims2:isAllowed> --%>
			
			<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_RULES%>'>
			<logic:equal name="tab" value="rulestab">
		        <td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
		        <td bgcolor='#6699FF' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayCasefileSupervisionRuleList.do?submitAction=Display All&<bean:write name="requestParam" />" class='topNav'><bean:message key="prompt.rules" /></a></td>
		        <td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/right_active_tab.gif"></td>					
			</logic:equal>	

			<logic:notEqual name="tab" value="rulestab">
				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
				<td bgcolor='#B3C9F5' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayCasefileSupervisionRuleList.do?submitAction=Display All&<bean:write name="requestParam" />" class='topNav'><bean:message key="prompt.rules" /></a></td>
				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
			</logic:notEqual>
			<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width='2'></td>	
			</jims2:isAllowed>

			<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_GOALS%>'>
			<logic:equal name="tab" value="goalstab">
				<td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
				<td bgcolor='#6699FF' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayCaseplanDetails.do?<bean:write name="requestParam" />&submitAction=Link&action=d" class='topNav'><bean:message key="prompt.casePlan" /></a></td>
				<td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/right_active_tab.gif"></td>
			</logic:equal>

			<logic:notEqual name="tab" value="goalstab">
				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
				<td bgcolor='#B3C9F5' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayCaseplanDetails.do?<bean:write name="requestParam" />&submitAction=Link&action=d" class='topNav'><bean:message key="prompt.casePlan" /></a></td>
				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
			</logic:notEqual>	
			<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width='2'></td>	
			</jims2:isAllowed>

			<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_ASSREF%>'>
			<logic:equal name="tab" value="assignedreferralstab">
        <td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
        <td bgcolor='#6699FF' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileCasefileAssignedReferralsList.do?submitAction=Referrals&<bean:write name="requestParam" />" class='topNav'><bean:message key="prompt.referrals" /></a></td>
        <td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/right_active_tab.gif"></td>					
			</logic:equal>	

			<logic:notEqual name="tab" value="assignedreferralstab">
				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
				<td bgcolor='#B3C9F5' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileCasefileAssignedReferralsList.do?submitAction=Referrals&<bean:write name="requestParam" />" class='topNav'><bean:message key="prompt.referrals" /></a></td>
				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
			</logic:notEqual>	
			<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width='2'></td>	
			</jims2:isAllowed>

			<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_PGMREF%>'>
			<logic:equal name="tab" value="programreferraltab">
        <td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
        <td bgcolor='#6699FF' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayProgramReferralList.do?submitAction=Link" class='topNav'><bean:message key="prompt.programReferral" /></a></td>
        <td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/right_active_tab.gif"></td>					
			</logic:equal>	

			<logic:notEqual name="tab" value="programreferraltab">
				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
				<td bgcolor='#B3C9F5' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayProgramReferralList.do?submitAction=Link" class='topNav'><bean:message key="prompt.programReferral" /></a></td>
				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
			</logic:notEqual>	
			<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width='2'></td>	
			</jims2:isAllowed>

			<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_TRAITS%>'>
			<logic:equal name="tab" value="traitstab">
		        <td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
		        <td bgcolor='#6699FF' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileCasefileTraitsSearch.do?<bean:write name="requestParam" />&submitAction=Tab" class='topNav'><bean:message key="prompt.traits" /></a></td>
		        <td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/right_active_tab.gif"></td>					
			</logic:equal>	

			<logic:notEqual name="tab" value="traitstab">
				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
				<td bgcolor='#B3C9F5' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileCasefileTraitsSearch.do?<bean:write name="requestParam" />&submitAction=Tab" class='topNav'><bean:message key="prompt.traits" /></a></td>
				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
			</logic:notEqual>	
			<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width='2'></td>	
			</jims2:isAllowed>
			
<%-- 		
			<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_INTERVIEW%>'>
			<logic:equal name="tab" value="interviewtab">
		        <td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
		        <td bgcolor='#6699FF' align='center'><a href="/<msp:webapp/>displayJuvInterviewList.do?submitAction=<bean:message key='button.link'/>" class='topNav'><bean:message key="prompt.interview" /></a></td>
		        <td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/right_active_tab.gif"></td>					
			</logic:equal>	
			<logic:notEqual name="tab" value="interviewtab">
				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
				<td bgcolor='#B3C9F5' align='center'><a href="/<msp:webapp/>displayJuvInterviewList.do?submitAction=<bean:message key='button.link'/>" class='topNav'><bean:message key="prompt.interview" /></a></td>
				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
			</logic:notEqual>
			<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width='2'></td>	
			</jims2:isAllowed>

			<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_ACT%>'>
			<logic:equal name="tab" value="activitiestab">
		        <td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
		        <td bgcolor='#6699FF' align='center'><a href="/<msp:webapp/>displayActivities.do?submitAction=<bean:message key='button.link'/>" class='topNav'><bean:message key="prompt.activities" /></a></td>
		        <td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/right_active_tab.gif"></td>					
			</logic:equal>	
			<logic:notEqual name="tab" value="activitiestab">
				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
				<td bgcolor='#B3C9F5' align='center'><a href="/<msp:webapp/>displayActivities.do?submitAction=<bean:message key='button.link'/>" class='topNav'><bean:message key="prompt.activities" /></a></td>
				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
			</logic:notEqual>
			 <td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width='2'></td>	
			</jims2:isAllowed>
--%>	


	<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_CALENDAR%>'>
			<logic:equal name="tab" value="calendartab">
        <td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
        <td bgcolor='#6699FF' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayCalendar.do?submitAction=<bean:message key='button.link'/>" class='topNav'><bean:message key="prompt.calendar" /></a></td>
        <td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/right_active_tab.gif"></td>					
			</logic:equal>	
			<logic:notEqual name="tab" value="calendartab">
				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
				<td bgcolor='#B3C9F5' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayCalendar.do?submitAction=<bean:message key='button.link'/>" class='topNav'><bean:message key="prompt.calendar" /></a></td>
				<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
			</logic:notEqual>
			 <td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width='2'></td>	
	</jims2:isAllowed>

		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_COMMONAPP%>'> <%-- added for hot-fix 35678 --%>
			<jims2:if name="juvenileCasefileForm" property="caseStatusId" op="equal" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_ACTIVE%>">
			  <jims2:or name="juvenileCasefileForm" property="caseStatusId" op="equal"  value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING%>" />
			  <jims2:then>
					<logic:equal name="tab" value="commonApp">
		        <td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
		        <td bgcolor='#6699FF' align='center'><a onclick="spinner()" href="/<msp:webapp/>handleCasefileClosingActivities.do?submitAction=<bean:message key='button.commonApp'/>" class='topNav'>Common App</a></td>
		        <td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/right_active_tab.gif"></td>
					</logic:equal>	
					<logic:notEqual name="tab" value="commonApp">
						<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
						<td bgcolor='#B3C9F5' align='center'><a onclick="spinner()" href="/<msp:webapp/>handleCasefileClosingActivities.do?submitAction=<bean:message key='button.commonApp'/>" class='topNav'>Common App</a></td>
						<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
					</logic:notEqual>
				 <td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width='2'></td>
				 </jims2:then>	
			</jims2:if>
		</jims2:isAllowed> 
			
			
			<logic:notEqual name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_ACTIVE%>">
			<logic:notEqual name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING%>">	
		 	  <jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_CLOSING%>'> 
					<logic:equal name="tab" value="closing">
		        <td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
		        <td bgcolor='#6699FF' align='center'><a onclick="spinner()" href="/<msp:webapp/>processCasefileClosing.do?<bean:write name="requestParam" />&submitAction=<bean:message key='button.link'/>" class='topNav'>Closing</a></td>
		        <td bgcolor='#6699FF' valign='top'><img src="/<msp:webapp/>images/right_active_tab.gif"></td>
					</logic:equal>	
					<logic:notEqual name="tab" value="closing">
						<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
						<td bgcolor='#B3C9F5' align='center'><a onclick="spinner()" href="/<msp:webapp/>processCasefileClosing.do?<bean:write name="requestParam" />&submitAction=<bean:message key='button.link'/>" class='topNav'>Closing</a></td>
						<td bgcolor='#B3C9F5' valign='top'><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
					</logic:notEqual>	
				</jims2:isAllowed> 
			</logic:notEqual>	
			</logic:notEqual>
		</tr>
	</table>
	
    <logic:notEqual name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_ACTIVE%>">
    	<logic:notEqual name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING%>">	
        	<logic:equal name="tab" value="closing">	
        		<table width="100%" border='0' cellpadding='0' cellspacing='0'>
      				<tr bgcolor='#6699FF'>
      					<td width='1%' align="left">&nbsp; <jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_CLOSING%>'> <a onclick="spinner()" href='/JuvenileCasework/processCasefileClosing.do?submitAction=Link'>View Casefile Closing</a></jims2:isAllowed>&nbsp;<b>|</b>
      					 <jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_COMMONAPP%>'>
      					  <a onclick="spinner()" href='/JuvenileCasework/displayExitReports.do?submitAction=Link'>View Common App Report</a>&nbsp;
      					  </jims2:isAllowed>
    					</td>
      				</tr>
        		</table>
        	</logic:equal>
      	</jims2:isAllowed>
    	</logic:notEqual>
    </logic:notEqual>

    <td>
  </tr>  
  <tr>
    <td bgcolor="#6699ff"><img src="/<msp:webapp/>images/spacer.gif" width=5></td>
  </tr>
</table>
	

