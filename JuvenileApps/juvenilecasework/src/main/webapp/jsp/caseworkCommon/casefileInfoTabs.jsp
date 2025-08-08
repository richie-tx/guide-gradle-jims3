<!DOCTYPE HTML>

<%-- Manages Tabs for Juvenile Casefiles --%>
<%-- 05/20/2005		glyons	Create JSP --%>
<%-- 08/31/2015	 	RCarter	   #29426 html 5 compliance effort and jquery 5 (when required) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %> 
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<head>
	<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
	<title><bean:message key="title.heading"/>/casefileInfoTabs.jsp</title>

</head>

<%--BEGIN BODY TAG--%>
<bean:define id="tab"><tiles:getAsString name="tabid" /></bean:define>
<tiles:importAttribute name="casefileid"/>
<logic:equal name="casefileid" value="">
	<bean:define id="requestParam"><%=PDJuvenileCaseConstants.CASEFILEID_PARAM%>=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>&supervisionNum=<bean:write name="juvenileCasefileForm" property="supervisionNum"/></bean:define>
</logic:equal>
<logic:notEqual name="casefileid" value="">
	<bean:define id="requestParam"><%=PDJuvenileCaseConstants.CASEFILEID_PARAM%>=<bean:write name="casefileid" />&supervisionNum=<bean:write name="juvenileCasefileForm" property="supervisionNum"/></bean:define>
</logic:notEqual>		

			

<%-- START PROFILE TABS --%>
<table border=0 cellpadding=0 cellspacing=0>
	<tr>
		
    <logic:equal name="tab" value="casefiledetailstab">
      <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
      <td bgcolor='#33cc66' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileCasefileDetails.do?<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.casefileInfo" /></a></td>
      <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
    </logic:equal>	

    <logic:notEqual name="tab" value="casefiledetailstab">
      <td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
      <td bgcolor='#99ff99' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileCasefileDetails.do?<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.casefileInfo" /></a></td>
      <td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
    </logic:notEqual>

		<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>	

  	<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_INTERVIEW%>'>
  		<logic:equal name="tab" value="interviewtab">
        <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
        <td bgcolor='#33cc66' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvInterviewList.do?submitAction=<bean:message key='button.link'/>" class=topNav><bean:message key="prompt.interview" /></a></td>
        <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
  		</logic:equal>	
  		<logic:notEqual name="tab" value="interviewtab">
  			<td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
  			<td bgcolor='#99ff99' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvInterviewList.do?submitAction=<bean:message key='button.link'/>" class=topNav><bean:message key="prompt.interview" /></a></td>
  			<td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
  		</logic:notEqual>
  		<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>	
  	</jims2:isAllowed>

  	<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_ACT%>'>
  		<logic:equal name="tab" value="activitiestab">
        <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
        <td bgcolor='#33cc66' align=center><a onclick="spinner()" href="/<msp:webapp/>displayActivities.do?submitAction=<bean:message key='button.link'/>" class=topNav><bean:message key="prompt.activities" /></a></td>
        <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
  		</logic:equal>	
  		<logic:notEqual name="tab" value="activitiestab">
  			<td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
  			<td bgcolor='#99ff99' align=center><a onclick="spinner()" href="/<msp:webapp/>displayActivities.do?submitAction=<bean:message key='button.link'/>" class=topNav><bean:message key="prompt.activities" /></a></td>
  			<td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
  		</logic:notEqual>
  		 <td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>	
  	</jims2:isAllowed>

    <jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_BENASSESS_V%>'>
      <logic:equal name="tab" value="benefitsAssess">
        <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
        <td bgcolor='#33cc66' align=center><a onclick="spinner()" href="/<msp:webapp/>displayBenefitsAssessmentCreate.do?submitAction=<bean:message key="title.benefitsAssessments"/>" class=topNav><bean:message key="prompt.benefitsAssessment" /></a></td>
        <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
      </logic:equal>	
      <logic:notEqual name="tab" value="benefitsAssess">
      	<td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
      	<td bgcolor='#99ff99' align=center><a onclick="spinner()" href="/<msp:webapp/>displayBenefitsAssessmentCreate.do?submitAction=<bean:message key="title.benefitsAssessments"/>" class=topNav><bean:message key="prompt.benefitsAssessment" /></a></td>
      	<td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
      </logic:notEqual>
      <td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>	
    </jims2:isAllowed>

    <jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_RISKANA_V%>'>
    <logic:equal name="tab" value="riskAnalysis">
           <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
           <td bgcolor='#33cc66' align=center><a onclick="spinner()" href="/<msp:webapp/>displayRiskResults.do?juvenileNum=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>&casefileID=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>&casefileId=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>" class=topNav><bean:message key="prompt.riskAnalysis" /></a></td>
           <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
    </logic:equal>	
      <logic:notEqual name="tab" value="riskAnalysis">
      	<td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
      	<td bgcolor='#99ff99' align=center><a onclick="spinner()" href="/<msp:webapp/>displayRiskResults.do?juvenileNum=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>&casefileID=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>&casefileId=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>" class=topNav><bean:message key="prompt.riskAnalysis" /></a></td>
      	<td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
      </logic:notEqual>
      <td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>	
    </jims2:isAllowed>
 
		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_JOU%>'>
      <logic:equal name="tab" value="journaltab">
        <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
        <td bgcolor='#33cc66' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileCasefileJournalSearch.do?submitAction=<bean:message key='button.link'/>" class=topNav> Journal </a></td>
        <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
      </logic:equal>	
      <logic:notEqual name="tab" value="journaltab">
        <td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
        <td bgcolor='#99ff99' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileCasefileJournalSearch.do?submitAction=<bean:message key='button.link'/>" class=topNav>Journal </a></td>
        <td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
      </logic:notEqual>
      <td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>	
		</jims2:isAllowed>
		
		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_DOCUMENT%>'>
      <%-- <KISHORE>JIMS200060663 : Document tab in Casefile view (UI) - KK --%>
      <logic:equal name="tab" value="documentstab">
        <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
        <td bgcolor='#33cc66' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileCasefileDocuments.do?submitAction=<bean:message key='button.link'/>" class=topNav> Documents </a></td>
        <td bgcolor='#33cc66' valign=top><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
      </logic:equal>	
      <logic:notEqual name="tab" value="documentstab">
        <td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
        <td bgcolor='#99ff99' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileCasefileDocuments.do?submitAction=<bean:message key='button.link'/>" class=topNav> Documents </a></td>
        <td bgcolor='#99ff99' valign=top><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
      </logic:notEqual>
      </jims2:isAllowed>
      <td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>	

	</tr>
</table>
<%-- END PROFILE TABS --%>

