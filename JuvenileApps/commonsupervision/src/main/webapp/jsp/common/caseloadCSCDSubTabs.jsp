<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/29/2005		cc_rsojitrawala		Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.Features" %>
<head>
<title>Common Supervision - common/caseloadCSCDSubTabs.jsp</title>
</head>

<!-- START CASELOAD TABS -->
<table width="100%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
			<table border="0" cellpadding="0" cellspacing="0">
			   <tr>
			   		<tiles:importAttribute name="tab" ignore="true" />
					<logic:equal name="tab" value="SuperviseeTab">
						<td bgcolor="#33cc66" valign="top"><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
						<td bgcolor="#33cc66" align="center"><a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&superviseeId=<bean:write name='superviseeHeaderForm' property='superviseeId'/>&tabLink=Y" class="topNav">Supervisee</a></td>
		                <td bgcolor="#33cc66" valign="top"><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>
	              	</logic:equal>
					<logic:notEqual name="tab" value="SuperviseeTab">
	                   <td bgcolor="#99ff99" valign="top"><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
	                   <td bgcolor="#99ff99" align="center"><a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&superviseeId=<bean:write name='superviseeHeaderForm' property='superviseeId'/>&tabLink=Y" class="topNav">Supervisee</a></td>
	                   <td bgcolor="#99ff99" valign="top"><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
					</logic:notEqual>
					<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
					<logic:equal name="tab" value="AssociatesTab">
						<td bgcolor="#33cc66" valign="top"><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
	    	            <td bgcolor="#33cc66" align="center"><a href="/<msp:webapp/>displayAssociatesList.do?submitAction=Associates List&superviseeId=<bean:write name='superviseeHeaderForm' property='superviseeId'/>" class="topNav">Associates</a></td>
	        	        <td bgcolor="#33cc66" valign="top"><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>
	                </logic:equal>
	               	<logic:notEqual name="tab" value="AssociatesTab">
	                   <td bgcolor="#99ff99" valign="top"><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
	                   <td bgcolor="#99ff99" align="center"><a href="/<msp:webapp/>displayAssociatesList.do?submitAction=Associates List&superviseeId=<bean:write name='superviseeHeaderForm' property='superviseeId'/>" class="topNav">Associates</a></td>
	                   <td bgcolor="#99ff99" valign="top"><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
	               	</logic:notEqual>
					<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>
					<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CAL_VIEW%>'>	
					<logic:equal name="tab" value="CalendarTab">
		               <td bgcolor="#33cc66" valign="top"><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
	    	           <td bgcolor="#33cc66" align="center"><a href="/<msp:webapp/>displayCSSuperviseeCalendar.do?submitAction=Link&superviseeId=<bean:write name='superviseeHeaderForm' property='superviseeId'/>" class="topNav">Calendar</a></td>
	        	       <td bgcolor="#33cc66" valign="top"><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>
	        	    </logic:equal>
	               	<logic:notEqual name="tab" value="CalendarTab">
	                       <td bgcolor="#99ff99" valign="top"><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
	                       <td bgcolor="#99ff99" align="center"><a href="/<msp:webapp/>displayCSSuperviseeCalendar.do?submitAction=Link&superviseeId=<bean:write name='superviseeHeaderForm' property='superviseeId'/>" class="topNav">Calendar</a></td>
	                       <td bgcolor="#99ff99" valign="top"><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
	               	</logic:notEqual>
	               	</jims2:isAllowed>
					<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>
                    <logic:equal name="tab" value="CasesTab">
		               <td bgcolor="#33cc66" valign="top"><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
	    	           <td bgcolor="#33cc66" align="center"><a href="/<msp:webapp/>displayCaseHistoryList.do?submitAction=Link&superviseeId=<bean:write name='superviseeHeaderForm' property='superviseeId'/>" class="topNav">Cases</a></td>
	        	       <td bgcolor="#33cc66" valign="top"><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>
	        	    </logic:equal>
	               	<logic:notEqual name="tab" value="CasesTab">
	                       <td bgcolor="#99ff99" valign="top"><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
	                       <td bgcolor="#99ff99" align="center"><a href="/<msp:webapp/>displayCaseHistoryList.do?submitAction=Link&superviseeId=<bean:write name='superviseeHeaderForm' property='superviseeId'/>" class="topNav">Cases</a></td>
	                       <td bgcolor="#99ff99" valign="top"><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
	               	</logic:notEqual>
					<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>
					<jims2:isAllowed requiredFeatures='<%=Features.CSCD_PRG_REF_VIEW%>'>
                     <logic:equal name="tab" value="ProgramReferralsTab">
		               <td bgcolor="#33cc66" valign="top"><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
	    	           <td bgcolor="#33cc66" align="center"><a href="/<msp:webapp/>displayProgRefList.do?submitAction=Link&superviseeId=<bean:write name='superviseeHeaderForm' property='superviseeId'/>" class="topNav">Program Referrals</a></td>
	        	       <td bgcolor="#33cc66" valign="top"><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>
	        	    </logic:equal>
	               	<logic:notEqual name="tab" value="ProgramReferralsTab">
	                       <td bgcolor="#99ff99" valign="top"><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
	                       <td bgcolor="#99ff99" align="center"><a href="/<msp:webapp/>displayProgRefList.do?submitAction=Link&superviseeId=<bean:write name='superviseeHeaderForm' property='superviseeId'/>" class="topNav">Program Referrals</a></td>
	                       <td bgcolor="#99ff99" valign="top"><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
	               	</logic:notEqual>
					<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>
					</jims2:isAllowed> 
					<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_ACCESS%>'>	
						<logic:equal name="tab" value="AssessmentsTab">
			               <td bgcolor="#33cc66" valign="top"><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
			               <td bgcolor="#33cc66" align="center"><a href="/<msp:webapp/>displayAssessmentSummary.do?submitAction=Link&superviseeId=<bean:write name='superviseeHeaderForm' property='superviseeId'/>" class="topNav"><bean:message key="prompt.assessments"/></a></td>
		        	       <td bgcolor="#33cc66" valign="top"><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>
		        	    </logic:equal>
		               	<logic:notEqual name="tab" value="AssessmentsTab">
	                       <td bgcolor="#99ff99" valign="top"><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
	                       <td bgcolor="#99ff99" align="center"><a href="/<msp:webapp/>displayAssessmentSummary.do?submitAction=Link&superviseeId=<bean:write name='superviseeHeaderForm' property='superviseeId'/>" class="topNav"><bean:message key="prompt.assessments"/></a></td>
	                       <td bgcolor="#99ff99" valign="top"><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
		               	</logic:notEqual>
						<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>		    
					</jims2:isAllowed> 
					<jims2:isAllowed requiredFeatures='<%=Features.CS_COMPLIANCE_ACCESS%>'>   	    
						<logic:equal name="tab" value="ComplianceTab">
			               <td bgcolor="#33cc66" valign="top"><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
		    	           <td bgcolor="#33cc66" align="center"><a href="/<msp:webapp/>displayComplianceConditionSelect.do?submitAction=Link&superviseeId=<bean:write name='superviseeHeaderForm' property='superviseeId'/>" class="topNav">Compliance</a></td>
		        	       <td bgcolor="#33cc66" valign="top"><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>
		        	    </logic:equal>
		               	<logic:notEqual name="tab" value="ComplianceTab">
		                    <td bgcolor="#99ff99" valign="top"><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
		                   	<td bgcolor="#99ff99" align="center"><a href="/<msp:webapp/>displayComplianceConditionSelect.do?submitAction=Link&superviseeId=<bean:write name='superviseeHeaderForm' property='superviseeId'/>" class="topNav">Compliance</a></td>
		                    <td bgcolor="#99ff99" valign="top"><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
		               	</logic:notEqual>
	               	</jims2:isAllowed>
	          	</tr>
	     	</table>
	     </td>
	</tr>
</table>
<!-- END CASELOAD TABS -->


