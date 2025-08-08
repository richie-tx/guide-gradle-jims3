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

<tiles:importAttribute name="juvenileProfileMainForm" ignore="true"/>
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
	<title><bean:message key="title.heading"/>/interviewInfoTabs.jsp</title>
</head> 


<table border=0 cellpadding=0 cellspacing=0>
	<tr>
  	<bean:define id="tab"><tiles:getAsString name="tabid" /></bean:define>	
  	<bean:define id="requestParam"><%=PDJuvenileCaseConstants.JUVENILENUM_PARAM%>=<bean:write name="juvenileProfileHeader" property="juvenileNum"/></bean:define>

		<logic:equal name="tab" value="profileInfo">
      <td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
      <td bgcolor='#6699FF' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileMasterInformation.do?<bean:write name="requestParam" />" class=topNav>Profile Info</a></td>
      <td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/right_active_tab.gif"></td>				
		</logic:equal>

  	<logic:notEqual name="tab" value="profileInfo">
  		<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
  		<td bgcolor='#B3C9F5' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileMasterInformation.do?<bean:write name="requestParam" />" class=topNav>Profile Info</a></td>
  		<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
  	</logic:notEqual>		

		<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>

		<logic:present name="juvenileProfileMainForm">
			<logic:equal name="juvenileProfileMainForm" property="hasCasefile" value="true">
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MAS_MED%>'>
					<logic:equal name="tab" value="MEDICAL_ISSUES">
		        <td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
		        <td bgcolor='#6699FF' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileMedicalList.do" class=topNav>Medical</a></td>
		        <td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/right_active_tab.gif"></td>				
					</logic:equal>

					<logic:notEqual name="tab" value="MEDICAL_ISSUES">
						<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
						<td bgcolor='#B3C9F5' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileMedicalList.do" class=topNav>Medical</a></td>
						<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
					</logic:notEqual>		

					<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
				</jims2:isAllowed>
				
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MAS_GANG%>'>
					<logic:equal name="tab" value="GANGS">
				    <td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
				    <td bgcolor='#6699FF' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileGangs.do?submitAction=Tab&action=View" class=topNav>Gangs</a></td>
				    <td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/right_active_tab.gif"></td>				
					</logic:equal>
				
					<logic:notEqual name="tab" value="GANGS">
						<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
						<td bgcolor='#B3C9F5' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileGangs.do?submitAction=Tab&action=View" class=topNav>Gangs</a></td>
						<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
					</logic:notEqual>		
					<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>	
				</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MAS_DRUG%>'>
					<logic:equal name="tab" value="DRUGS">
		        <td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
		        <td bgcolor='#6699FF' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileDrugsList.do?submitAction=Tab&action=View" class=topNav>Drugs</a></td>
		        <td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/right_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="DRUGS">
						<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
						<td bgcolor='#B3C9F5' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileDrugsList.do?submitAction=Tab&action=View" class=topNav>Drugs</a></td>
						<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
					</logic:notEqual>	
					<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>	
				</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MAS_SCL%>'>
					<logic:equal name="tab" value="SCHOOL">
		        <td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
		        <td bgcolor='#6699FF' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileSchool.do?submitAction=Tab&action=View" class=topNav>Education</a></td>
		        <td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/right_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="SCHOOL">
						<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
						<td bgcolor='#B3C9F5' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileSchool.do?submitAction=Tab&action=View" class=topNav>Education</a></td>
						<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
					</logic:notEqual>	
					<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>	
				</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MAS_SI%>'>
					<logic:equal name="tab" value="SPECIAL_INTERESTS">
		        <td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
		        <td bgcolor='#6699FF' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileSpecialInterestList.do?submitAction=Tab&action=View" class=topNav>Special Interest</a></td>
		        <td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/right_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="SPECIAL_INTERESTS">
						<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
						<td bgcolor='#B3C9F5' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileSpecialInterestList.do?submitAction=Tab&action=View" class=topNav>Special Interest</a></td>
						<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
					</logic:notEqual>
					<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>	
				</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MAS_ABUSE%>'>
					<logic:equal name="tab" value="ABUSE">
				        <td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
				        <td bgcolor='#6699FF' align=center ><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileAbuse.do?submitAction=Tab&action=View" class=topNav>Abuse/Dual Status</a></td>
				        <td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/right_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="ABUSE">
						<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
						<td bgcolor='#B3C9F5' align=center ><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileAbuse.do?submitAction=Tab&action=View" class=topNav>Abuse/Dual Status</a></td>
						<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
					</logic:notEqual>					
					<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>	
				</jims2:isAllowed>				
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MAS_MH%>'>
					<logic:equal name="tab" value="mentalhealthtab">
		        <td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
		        <td bgcolor='#6699FF' align=center class=topNavDisabled>
		        <a onclick="spinner()" href="/<msp:webapp/>displayMAYSIResults.do?<bean:write name="requestParam" />" class=topNav>Mental Health</a></td>
		        <td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/right_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="mentalhealthtab">
						<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
						<td bgcolor='#B3C9F5' align=center class=topNavDisabled>
						<a onclick="spinner()" href="/<msp:webapp/>displayMAYSIResults.do?<bean:write name="requestParam" />" class=topNav>Mental Health</a></td>
						<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
					</logic:notEqual>		
					<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
				</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MAS_JOBS%>'>
					<logic:equal name="tab" value="job">
		        <td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
		        <td bgcolor='#6699FF' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileJobList.do?<bean:write name="requestParam" />" class=topNav>Jobs</a></td>
		        <td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/right_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="job">
						<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
						<td bgcolor='#B3C9F5' align=center><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileJobList.do?<bean:write name="requestParam" />" class=topNav>Jobs</a></td>
						<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
					</logic:notEqual>		
					<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
				</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_MAS_BEN%>'>
					<logic:equal name="tab" value="benefitstab">
		        <td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
		        <td bgcolor='#6699FF' align=center class=topNavDisabled><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileBenefitsInsuranceList.do?<bean:write name="requestParam" />" class=topNav>Benefits</a></td>
		        <td bgcolor='#6699FF' valign=top><img src="/<msp:webapp/>images/right_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="benefitstab">
						<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
						<td bgcolor='#B3C9F5' align=center class=topNavDisabled><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileBenefitsInsuranceList.do?<bean:write name="requestParam" />" class=topNav>Benefits</a></td>
						<td bgcolor='#B3C9F5' valign=top><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
					</logic:notEqual>		
					<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>	
				</jims2:isAllowed>
			</logic:equal>
		</logic:present>
	</tr>
</table>

