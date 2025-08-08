<!DOCTYPE HTML>

<%-- Manages Tabs for Juvenile Casefiles --%>
<%-- 05/20/2005		glyons	Create JSP --%>
<%-- 11/10/2010     cShimek #68006, revised Referrals tabs to use submitAction&parms to correct SubmitAction error in action --%>

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

<head>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title><bean:message key="title.heading"/>/juvenileProfileTabs.jsp</title>
</head>

<tiles:importAttribute name="juvenileProfileMainForm" ignore="true"/>

<%-- START PROFILE TABS --%>
<table border=0 cellpadding=0 cellspacing=0>
	<tr>
  	<bean:define id="tab"><tiles:getAsString name="tabid" /></bean:define>
  	<bean:define id="requestParam"><%=PDJuvenileCaseConstants.JUVENILENUM_PARAM%>=<bean:write name="juvenileProfileHeader" property="juvenileNum"/></bean:define>

    <elogic:if name="tab" op="equal" value="maintab">
      <elogic:or name="tab" op="equal" value="interviewinfotab" />
      <elogic:or name="tab" op="equal" value="main" />
    	<elogic:then>
        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
        <td bgcolor='#33cc66' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileMasterInformation.do?<bean:write name='requestParam' />&fromProfile=profileBriefingDetails" class=topNav>Master</a></td>
        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>				
    	</elogic:then>
			<elogic:else>
				<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
				<td bgcolor='#99ff99' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileMasterInformation.do?<bean:write name='requestParam' />&fromProfile=profileBriefingDetails" class=topNav>Master</a></td>
				<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
			</elogic:else>		
		</elogic:if>
		<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>
		
		<logic:present name="juvenileProfileMainForm">
			<logic:equal name="juvenileProfileMainForm" property="hasCasefile" value="true">	
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_CASEFILE%>'>	
					<logic:equal name="tab" value="casefilestab">
			            <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
			            <td bgcolor='#33cc66' align='center' class=topNavDisabled> 
							<logic:notEmpty name="juvenileProfileHeader" property="status">
	                			<a onclick="spinner()"href="/<msp:webapp/>displayJuvenileProfileCasefileList.do?<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.casefiles" /></a>
	              			</logic:notEmpty>
	              			<logic:empty name="juvenileProfileHeader" property="status"><bean:message key="prompt.casefiles" /></logic:empty>
  		      			</td>
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>				
					</logic:equal>

					<logic:notEqual name="tab" value="casefilestab">
  						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
  						<td bgcolor='#99ff99' align='center' class=topNavDisabled>
    						<logic:notEmpty name="juvenileProfileHeader" property="status">
      							<a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileCasefileList.do?<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.casefiles" /></a>
    						</logic:notEmpty>
    						<logic:empty name="juvenileProfileHeader" property="status"><bean:message key="prompt.casefiles" /></logic:empty>
  						</td>
  						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
  					</logic:notEqual>		
  					<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
				</jims2:isAllowed>
				
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_RULE%>'>	
					<logic:equal name="tab" value="rulestab">
			  	        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
			  	        <td bgcolor='#33cc66' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileRuleList.do?<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.rules" /></a></td>
			  	        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="rulestab">
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
						<td bgcolor='#99ff99' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileRuleList.do?<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.rules" /></a></td>
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
					</logic:notEqual>	
					<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
				</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_CASEPLAN%>'>						
					<logic:equal name="tab" value="goalstab">
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
				        <td bgcolor='#33cc66' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvProfileCaseplanList.do?<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.casePlan" /></a></td>
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="goalstab">
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
						<td bgcolor='#99ff99' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvProfileCaseplanList.do?<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.casePlan" /></a></td>
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
					</logic:notEqual>	
					<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
				</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_ASSREF%>'>	
					<logic:equal name="tab" value="referralstab">
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
				        <td bgcolor='#33cc66' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Referrals&<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.referrals" /></a></td>
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="referralstab">
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
						<td bgcolor='#99ff99' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Referrals&<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.referrals" /></a></td>
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
					</logic:notEqual>	
			
					<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
				</jims2:isAllowed>
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_ASSREF%>'>	
					<logic:equal name="tab" value="facilityHistorytab">
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
				        <td bgcolor='#33cc66' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Facility&<bean:write name="requestParam" />" class=topNav>Facility History</a></td>
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="facilityHistorytab">
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
						<td bgcolor='#99ff99' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Facility&<bean:write name="requestParam" />" class=topNav>Facility History</a></td>
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
					</logic:notEqual>	
			
					<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
				</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_PGMREF%>'>	
					<logic:equal name="tab" value="programreferraltab">
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
				        <td bgcolor='#33cc66' align='center' class=topNavDisabled><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileProgramReferralList.do?submitAction=Link" class=topNav><bean:message key="prompt.programReferral" /></a></td>
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="programreferraltab">
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
						<td bgcolor='#99ff99' align='center' class=topNavDisabled><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileProgramReferralList.do?submitAction=Link" class=topNav><bean:message key="prompt.programReferral" /></a></td>
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
					</logic:notEqual>
					<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
				</jims2:isAllowed>


				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_RULE%>'>	
						<%--logic:equal name="tab" value="feestab">
					        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
					        <td bgcolor='#33cc66' align='center' class=topNavDisabled><a href="/<msp:webapp/>displayJuvenileMasterInformation.do?<bean:write name="requestParam" />" class=topNav>Fees</a></td>
					        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
						</logic:equal>	
						<logic:notEqual name="tab" value="feestab">
							<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
							<td bgcolor='#99ff99' align='center' class=topNavDisabled><a href="/<msp:webapp/>displayJuvenileMasterInformation.do?<bean:write name="requestParam" />" class=topNav>Fees</a></td>
							<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
						</logic:notEqual>	
						<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width=2></td
						--%>	
				</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_TRAITS%>'>	
					<logic:equal name="tab" value="traitstab">
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
				        <td bgcolor='#33cc66' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileTraitsSearch.do?submitAction=Tab&action=%20" class=topNav><bean:message key="prompt.traits" /></a></td>
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="traitstab">
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
						<td bgcolor='#99ff99' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileTraitsSearch.do?submitAction=Tab&action=%20" class=topNav><bean:message key="prompt.traits" /></a></td>
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
					</logic:notEqual>		
					<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>
				</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_CONTACTS%>'>	
					<logic:equal name="tab" value="contactstab">
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
				        <td bgcolor='#33cc66' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileContactList.do?<bean:write name='requestParam' />" class=topNav><bean:message key="prompt.contacts" /></a></td>
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="contactstab">
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
						<td bgcolor='#99ff99' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileContactList.do?<bean:write name='requestParam' />" class=topNav><bean:message key="prompt.contacts" /></a></td>
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
					</logic:notEqual>		
					<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>
			  	</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>			
					<logic:equal name="tab" value="family">
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
				        <td bgcolor='#33cc66' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayFamilyConstellationList.do?<bean:write name='requestParam' />" class=topNav>Family</a></td>
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="family">
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
						<td bgcolor='#99ff99' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayFamilyConstellationList.do?<bean:write name='requestParam' />" class=topNav>Family</a></td>
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
					</logic:notEqual>		
			
					<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
  				</jims2:isAllowed>
			</logic:equal>
			
			<logic:notEqual name="juvenileProfileMainForm" property="hasCasefile" value="true">		
				<logic:equal name="juvenileProfileHeader" property="hasCasefile" value="true">	
					<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_CASEFILE%>'>	
						<logic:equal name="tab" value="casefilestab">
				            <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
				            <td bgcolor='#33cc66' align='center' class=topNavDisabled> 
    							<logic:notEmpty name="juvenileProfileHeader" property="status">
	                				<a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileCasefileList.do?<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.casefiles" /></a>
              					</logic:notEmpty>
              					<logic:empty name="juvenileProfileHeader" property="status"><bean:message key="prompt.casefiles" /></logic:empty>
  		      				</td>
		        			<td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>				
						</logic:equal>

						<logic:notEqual name="tab" value="casefilestab">
		  					<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
		  					<td bgcolor='#99ff99' align='center' class=topNavDisabled>
		    					<logic:notEmpty name="juvenileProfileHeader" property="status">
			      					<a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileCasefileList.do?<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.casefiles" /></a>
		    					</logic:notEmpty>
		    					<logic:empty name="juvenileProfileHeader" property="status"><bean:message key="prompt.casefiles" /></logic:empty>
	  						</td>
  							<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
  						</logic:notEqual>		
  						<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
					</jims2:isAllowed>
				
					<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_RULE%>'>	
						<logic:equal name="tab" value="rulestab">
				  	        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
				  	        <td bgcolor='#33cc66' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileRuleList.do?<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.rules" /></a></td>
				  	        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
						</logic:equal>	
						<logic:notEqual name="tab" value="rulestab">
							<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
							<td bgcolor='#99ff99' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileRuleList.do?<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.rules" /></a></td>
							<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
						</logic:notEqual>	
						<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
					</jims2:isAllowed>

					<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_CASEPLAN%>'>						
						<logic:equal name="tab" value="goalstab">
					        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
					        <td bgcolor='#33cc66' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvProfileCaseplanList.do?<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.casePlan" /></a></td>
					        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
						</logic:equal>	
						<logic:notEqual name="tab" value="goalstab">
							<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
							<td bgcolor='#99ff99' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvProfileCaseplanList.do?<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.casePlan" /></a></td>
							<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
						</logic:notEqual>	
						<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
					</jims2:isAllowed>

					<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_ASSREF%>'>	
						<logic:equal name="tab" value="referralstab">
					        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
					        <td bgcolor='#33cc66' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Referrals&<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.referrals" /></a></td>
					        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
						</logic:equal>	
						<logic:notEqual name="tab" value="referralstab">
							<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
							<td bgcolor='#99ff99' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Referrals&<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.referrals" /></a></td>
							<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
						</logic:notEqual>	
				
						<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
					</jims2:isAllowed>

					<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_PGMREF%>'>	
						<logic:equal name="tab" value="programreferraltab">
					        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
					        <td bgcolor='#33cc66' align='center' class=topNavDisabled><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileProgramReferralList.do?submitAction=Link" class=topNav><bean:message key="prompt.programReferral" /></a></td>
					        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
						</logic:equal>	
						<logic:notEqual name="tab" value="programreferraltab">
							<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
							<td bgcolor='#99ff99' align='center' class=topNavDisabled><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileProgramReferralList.do?submitAction=Link" class=topNav><bean:message key="prompt.programReferral" /></a></td>
							<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
						</logic:notEqual>
						<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
					</jims2:isAllowed>

					<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_RULE%>'>	
						<%--logic:equal name="tab" value="feestab">
					        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
					        <td bgcolor='#33cc66' align='center' class=topNavDisabled><a href="/<msp:webapp/>displayJuvenileMasterInformation.do?<bean:write name="requestParam" />" class=topNav>Fees</a></td>
					        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
						</logic:equal>	
						<logic:notEqual name="tab" value="feestab">
							<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
							<td bgcolor='#99ff99' align='center' class=topNavDisabled><a href="/<msp:webapp/>displayJuvenileMasterInformation.do?<bean:write name="requestParam" />" class=topNav>Fees</a></td>
							<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
						</logic:notEqual>	
						<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width=2></td
						--%>	
					</jims2:isAllowed>

					<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_TRAITS%>'>	
						<logic:equal name="tab" value="traitstab">
					        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
					        <td bgcolor='#33cc66' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileTraitsSearch.do?submitAction=Tab&action=%20" class=topNav><bean:message key="prompt.traits" /></a></td>
					        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
						</logic:equal>	
						<logic:notEqual name="tab" value="traitstab">
							<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
							<td bgcolor='#99ff99' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileTraitsSearch.do?submitAction=Tab&action=%20" class=topNav><bean:message key="prompt.traits" /></a></td>
							<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
						</logic:notEqual>		
						<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>
					</jims2:isAllowed>

					<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_CONTACTS%>'>	
						<logic:equal name="tab" value="contactstab">
					        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
					        <td bgcolor='#33cc66' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileContactList.do?<bean:write name='requestParam' />" class=topNav><bean:message key="prompt.contacts" /></a></td>
					        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
						</logic:equal>	
						<logic:notEqual name="tab" value="contactstab">
							<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
							<td bgcolor='#99ff99' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileContactList.do?<bean:write name='requestParam' />" class=topNav><bean:message key="prompt.contacts" /></a></td>
							<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
						</logic:notEqual>		
						<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>
			  		</jims2:isAllowed>

					<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>			
						<logic:equal name="tab" value="family">
					        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
					        <td bgcolor='#33cc66' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayFamilyConstellationList.do?<bean:write name='requestParam' />" class=topNav>Family</a></td>
					        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
						</logic:equal>	
						<logic:notEqual name="tab" value="family">
							<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
							<td bgcolor='#99ff99' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayFamilyConstellationList.do?<bean:write name='requestParam' />" class=topNav>Family</a></td>
							<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
						</logic:notEqual>		
				
						<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
		  			</jims2:isAllowed>
				</logic:equal>
		 	</logic:notEqual>	
			
		</logic:present>
		
		<logic:notPresent name="juvenileProfileMainForm">
			<logic:equal name="juvenileProfileHeader" property="hasCasefile" value="true">
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_CASEFILE%>'>	
					<logic:equal name="tab" value="casefilestab">
			            <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
			            <td bgcolor='#33cc66' align='center' class=topNavDisabled> 
    			        	<logic:notEmpty name="juvenileProfileHeader" property="status">
	            		    	<a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileCasefileList.do?<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.casefiles" /></a>
              				</logic:notEmpty>
              				<logic:empty name="juvenileProfileHeader" property="status"><bean:message key="prompt.casefiles" /></logic:empty>
  		      			</td>
		        		<td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>				
					</logic:equal>

					<logic:notEqual name="tab" value="casefilestab">
  						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
  						<td bgcolor='#99ff99' align='center' class=topNavDisabled>
    						<logic:notEmpty name="juvenileProfileHeader" property="status">
      							<a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileCasefileList.do?<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.casefiles" /></a>
    						</logic:notEmpty>
    						<logic:empty name="juvenileProfileHeader" property="status"><bean:message key="prompt.casefiles" /></logic:empty>
  						</td>
  						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
  					</logic:notEqual>		
  					<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
				</jims2:isAllowed>
				
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_RULE%>'>	
					<logic:equal name="tab" value="rulestab">
			  	        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
			  	        <td bgcolor='#33cc66' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileRuleList.do?<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.rules" /></a></td>
			  	        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="rulestab">
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
						<td bgcolor='#99ff99' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileRuleList.do?<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.rules" /></a></td>
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
					</logic:notEqual>	
					<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
				</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_CASEPLAN%>'>						
					<logic:equal name="tab" value="goalstab">
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
				        <td bgcolor='#33cc66' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvProfileCaseplanList.do?<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.casePlan" /></a></td>
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="goalstab">
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
						<td bgcolor='#99ff99' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvProfileCaseplanList.do?<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.casePlan" /></a></td>
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
					</logic:notEqual>	
					<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
				</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_ASSREF%>'>	
					<logic:equal name="tab" value="referralstab">
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
				        <td bgcolor='#33cc66' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Referrals&<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.referrals" /></a></td>
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="referralstab">
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
						<td bgcolor='#99ff99' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Referrals&<bean:write name="requestParam" />" class=topNav><bean:message key="prompt.referrals" /></a></td>
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
					</logic:notEqual>	
			
					<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
				</jims2:isAllowed>
				
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_ASSREF%>'>	
					<logic:equal name="tab" value="facilityHistorytab">
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
				        <td bgcolor='#33cc66' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Facility&<bean:write name="requestParam" />" class=topNav>Facility History</a></td>
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="facilityHistorytab">
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
						<td bgcolor='#99ff99' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Facility&<bean:write name="requestParam" />" class=topNav>Facility History</a></td>
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
					</logic:notEqual>	
					<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
				</jims2:isAllowed>
				
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_PGMREF%>'>	
					<logic:equal name="tab" value="programreferraltab">
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
				        <td bgcolor='#33cc66' align='center' class=topNavDisabled><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileProgramReferralList.do?submitAction=Link" class=topNav><bean:message key="prompt.programReferral" /></a></td>
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="programreferraltab">
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
						<td bgcolor='#99ff99' align='center' class=topNavDisabled><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileProgramReferralList.do?submitAction=Link" class=topNav><bean:message key="prompt.programReferral" /></a></td>
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
					</logic:notEqual>
					<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
				</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_RULE%>'>	
						<%--logic:equal name="tab" value="feestab">
					        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
					        <td bgcolor='#33cc66' align='center' class=topNavDisabled><a href="/<msp:webapp/>displayJuvenileMasterInformation.do?<bean:write name="requestParam" />" class=topNav>Fees</a></td>
					        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
						</logic:equal>	
						<logic:notEqual name="tab" value="feestab">
							<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
							<td bgcolor='#99ff99' align='center' class=topNavDisabled><a href="/<msp:webapp/>displayJuvenileMasterInformation.do?<bean:write name="requestParam" />" class=topNav>Fees</a></td>
							<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
						</logic:notEqual>	
						<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width=2></td
						--%>	
				</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_TRAITS%>'>	
					<logic:equal name="tab" value="traitstab">
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
				        <td bgcolor='#33cc66' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileTraitsSearch.do?submitAction=Tab&action=%20" class=topNav><bean:message key="prompt.traits" /></a></td>
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="traitstab">
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
						<td bgcolor='#99ff99' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileTraitsSearch.do?submitAction=Tab&action=%20" class=topNav><bean:message key="prompt.traits" /></a></td>
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
					</logic:notEqual>		
					<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>
				</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_CONTACTS%>'>	
					<logic:equal name="tab" value="contactstab">
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
				        <td bgcolor='#33cc66' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileContactList.do?<bean:write name='requestParam' />" class=topNav><bean:message key="prompt.contacts" /></a></td>
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="contactstab">
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
						<td bgcolor='#99ff99' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileContactList.do?<bean:write name='requestParam' />" class=topNav><bean:message key="prompt.contacts" /></a></td>
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
					</logic:notEqual>		
					<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>
			 	</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>			
					<logic:equal name="tab" value="family">
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/left_green_active_tab.gif"></td>
				        <td bgcolor='#33cc66' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayFamilyConstellationList.do?<bean:write name='requestParam' />" class=topNav>Family</a></td>
				        <td bgcolor='#33cc66' valign='top'><img src="/<msp:webapp/>images/right_green_active_tab.gif"></td>					
					</logic:equal>	
					<logic:notEqual name="tab" value="family">
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/left_green_inactive_tab.gif"></td>
						<td bgcolor='#99ff99' align='center'><a onclick="spinner()" href="/<msp:webapp/>displayFamilyConstellationList.do?<bean:write name='requestParam' />" class=topNav>Family</a></td>
						<td bgcolor='#99ff99' valign='top'><img src="/<msp:webapp/>images/right_green_inactive_tab.gif"></td>
					</logic:notEqual>		
			
					<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" width="2"></td>	
  				</jims2:isAllowed>
			</logic:equal>
		</logic:notPresent>
	</tr>
</table>
<%-- END PROFILE TABS --%>

