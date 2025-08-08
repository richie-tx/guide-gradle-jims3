<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Manages Tabs for CommonSupervision  -->
<!-- 07/29/2005		awidjaja	Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<head>
<title>Common Supervision - common/commonSupervisionTabs.jsp</title>
</head>

<!-- START PROFILE TABS -->
<table width="100%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<tiles:importAttribute name="tab" ignore="true" />
					<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CAL_VIEW%>'>	
						<logic:equal name="tab" value="calendarTab">
					        <td bgcolor="#6699FF" valign="top"><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
					        <td bgcolor="#6699FF" align="center"><a href="/<msp:webapp/>displayCSCalendar.do?submitAction=Link" class="topNav"><bean:message key="prompt.calendar"/></a></td>
					        <td bgcolor="#6699FF" valign="top"><img src="/<msp:webapp/>images/right_active_tab.gif"></td>				
						</logic:equal>
						<logic:notEqual name="tab" value="calendarTab">
							<td bgcolor="#B3C9F5" valign="top"><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
							<td bgcolor="#B3C9F5" align="center"><a href="/<msp:webapp/>displayCSCalendar.do?submitAction=Link" class="topNav"><bean:message key="prompt.calendar"/></a></td>
							<td bgcolor="#B3C9F5" valign="top"><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
						</logic:notEqual>
						<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
					</jims2:isAllowed> 				
					<jims2:isAllowed requiredFeatures="CS-TASKS-VIEW">	
						<logic:equal name="tab" value="tasksTab">
					        <td bgcolor="#6699FF" valign="top"><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
					        <td bgcolor="#6699FF" align="center"><a href="/<msp:webapp/>handleTaskSearch.do?submitAction=Link" class="topNav"><bean:message key="prompt.tasks"/></a></td>
					        <td bgcolor="#6699FF" valign="top"><img src="/<msp:webapp/>images/right_active_tab.gif"></td>				
						</logic:equal>
						<logic:notEqual name="tab" value="tasksTab">
							<td bgcolor="#B3C9F5" valign="top"><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
							<td bgcolor="#B3C9F5" align="center"><a href="/<msp:webapp/>handleTaskSearch.do?submitAction=Link" class="topNav"><bean:message key="prompt.tasks"/></a></td>
							<td bgcolor="#B3C9F5" valign="top"><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
						</logic:notEqual>
						<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>
					</jims2:isAllowed>
					<jims2:isAllowed requiredFeatures="CS-PASO-SEARCH">	
						<logic:equal name="tab" value="processOrderTab">
					        <td bgcolor="#6699FF" valign="top"><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
					        <td bgcolor="#6699FF" align="center"><a href="/<msp:webapp/>displaySupervisionOrderSearch.do" class="topNav"><bean:message key="prompt.processOrder"/></a></td>
					        <td bgcolor="#6699FF" valign="top"><img src="/<msp:webapp/>images/right_active_tab.gif"></td>				
						</logic:equal>
						<logic:notEqual name="tab" value="processOrderTab">
							<td bgcolor="#B3C9F5" valign="top"><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
							<td bgcolor="#B3C9F5" align="center"><a href="/<msp:webapp/>displaySupervisionOrderSearch.do" class="topNav"><bean:message key="prompt.processOrder"/></a></td>
							<td bgcolor="#B3C9F5" valign="top"><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
						</logic:notEqual>
						<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
					</jims2:isAllowed>
					<jims2:isAllowed  requiredFeatures='<%=Features.CSCD_CASELOAD_SEARCH %>'> 
						<logic:equal name="tab" value="caseloadTab">
					        <td bgcolor="#6699FF" valign="top"><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
					        <td bgcolor="#6699FF" align="center"><a href="/<msp:webapp/>adminCaseloadAction.do?submitAction=Setup" class="topNav"><bean:message key="prompt.caseload"/></a></td>
					        <td bgcolor="#6699FF" valign="top"><img src="/<msp:webapp/>images/right_active_tab.gif"></td>				
						</logic:equal>
						<logic:notEqual name="tab" value="caseloadTab">
							<td bgcolor="#B3C9F5" valign="top"><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
							<td bgcolor="#B3C9F5" align="center"><a href="/<msp:webapp/>adminCaseloadAction.do?submitAction=Setup" class="topNav"><bean:message key="prompt.caseload"/></a></td>
							<td bgcolor="#B3C9F5" valign="top"><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
						</logic:notEqual>
						<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>
					</jims2:isAllowed>   	
					<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SETUP_SEARCH %>'>	 	
						<logic:equal name="tab" value="setupTab">
					        <td bgcolor="#6699FF" valign="top"><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
					        <td bgcolor="#6699FF" align="center"><a href="/<msp:webapp/>jsp/common/manageFeaturesIndex.jsp" class="topNav"><bean:message key="prompt.setup" /></a></td>
					        <td bgcolor="#6699FF" valign="top"><img src="/<msp:webapp/>images/right_active_tab.gif"></td>					
						</logic:equal>	
						<logic:notEqual name="tab" value="setupTab">
							<td bgcolor="#B3C9F5" valign="top"><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
							<td bgcolor="#B3C9F5" align="center"><a href="/<msp:webapp/>jsp/common/manageFeaturesIndex.jsp" class="topNav"><bean:message key="prompt.setup" /></a></td>
							<td bgcolor="#B3C9F5" valign="top"><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
						</logic:notEqual>
						<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
				</jims2:isAllowed>
				<jims2:isAllowed requiredFeatures="<%=Features.CSCD_CASENOTES_SEARCH%>">			
						<logic:equal name="tab" value="casenotesTab">	
					        <td bgcolor="#6699FF" valign="top"><img src="/<msp:webapp/>images/left_active_tab.gif"></td>
					        <td bgcolor="#6699FF" align="center"><a href="/<msp:webapp/>displayAdministerCasenotesSearch.do?submitAction=Link" class="topNav"><bean:message key="prompt.casenotes" /></a></td>
					        <td bgcolor="#6699FF" valign="top"><img src="/<msp:webapp/>images/right_active_tab.gif"></td>					
						</logic:equal>
						<logic:notEqual name="tab" value="casenotesTab">
							<td bgcolor="#B3C9F5" valign="top"><img src="/<msp:webapp/>images/left_inactive_tab.gif"></td>
							<td bgcolor="#B3C9F5" align="center"><a href="/<msp:webapp/>displayAdministerCasenotesSearch.do?submitAction=Link" class="topNav"><bean:message key="prompt.casenotes" /></a></td>
							<td bgcolor="#B3C9F5" valign="top"><img src="/<msp:webapp/>images/right_inactive_tab.gif"></td>
						</logic:notEqual>
				</jims2:isAllowed>						
				</tr>
			</table>
		</td>
	</tr>		
</table>
<!-- END PROFILE TABS -->

