<!DOCTYPE HTML>

<%-- Manages Tabs for Juvenile Casefiles Constellation stuff--%>
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
<%@include file="../jQuery.fw"%>
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
	<title><bean:message key="title.heading"/>/memberInfoTabs.jsp</title>
</head> 

<table border=0 cellpadding=0 cellspacing=0>
	<tr>
			<bean:define id="tab"><tiles:getAsString name="tabid" /></bean:define>
			
			<logic:equal name="juvenileFamilyForm" property="action" value="">	
				<jims2:if name="juvenileMemberForm" property="action" value="createMember" op="equal">
					<jims2:or name="juvenileMemberForm" property="action" value="createMemberSummary" op="equal"/>
						<jims2:then>
							<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
								<logic:equal name="tab" value="MemberInformation">
					        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
					        <td bgcolor='#6699FF' align=center class=topNav>Member Information</td>
					        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
								</logic:equal>
								<logic:notEqual name="tab" value="MemberInformation">
									<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
									<td bgcolor='#B3C9F5' align=center class=topNav>Member Information</td>
									<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
								</logic:notEqual>		
								<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
							</jims2:isAllowed>
							
							<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
								<logic:equal name="tab" value="AddressInfo">
					        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
					        <td bgcolor='#6699FF' align=center class=topNav>Address Info</td>
					        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
								</logic:equal>
								<logic:notEqual name="tab" value="AddressInfo">
									<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
									<td bgcolor='#B3C9F5' align=center class=topNav>Address Info</td>
									<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
								</logic:notEqual>	
								<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>	
							</jims2:isAllowed>
							
							<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
								<logic:equal name="tab" value="ContactInfo">
							        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
							        <td bgcolor='#6699FF' align=center class=topNav>Contact Info</td>
							        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
								</logic:equal>
								<logic:notEqual name="tab" value="ContactInfo">
									<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
									<td bgcolor='#B3C9F5' align=center class=topNav>Contact Info</td>
									<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
								</logic:notEqual>	
								<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
							</jims2:isAllowed>
							
							<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
								<logic:equal name="tab" value="Traits">
							        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
							        <td bgcolor='#6699FF' align=center class=topNav>Traits</td>
							        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
								</logic:equal>
								<logic:notEqual name="tab" value="Traits">
									<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
									<td bgcolor='#B3C9F5' align=center class=topNav>Traits</td>
									<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
								</logic:notEqual>
								<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
							</jims2:isAllowed>

					<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
						<jims2:isAllowed
							requiredFeatures='<%=Features.JCW_PRFV_MAS_BEN_INS_FAMFIN%>'>
							<logic:equal name="tab" value="Employment">
								<td bgcolor='#6699FF' valign=top><img
									src='/<msp:webapp/>images/left_active_tab.gif'>
								</td>
								<td bgcolor='#6699FF' align=center class=topNav>Employment</td>
								<td bgcolor='#6699FF' valign=top><img
									src='/<msp:webapp/>images/right_active_tab.gif'>
								</td>
							</logic:equal>
							<logic:notEqual name="tab" value="Employment">
								<td bgcolor='#B3C9F5' valign=top><img
									src='/<msp:webapp/>images/left_inactive_tab.gif'>
								</td>
								<td bgcolor='#B3C9F5' align=center class=topNav>Employment</td>
								<td bgcolor='#B3C9F5' valign=top><img
									src='/<msp:webapp/>images/right_inactive_tab.gif'>
								</td>
							</logic:notEqual>

							<logic:equal name="tab" value="Benefits">
								<td bgcolor='#6699FF' valign=top><img
									src='/<msp:webapp/>images/left_active_tab.gif'>
								</td>
								<td bgcolor='#6699FF' align=center class=topNav>Benefits</td>
								<td bgcolor='#6699FF' valign=top><img
									src='/<msp:webapp/>images/right_active_tab.gif'>
								</td>
							</logic:equal>
							<logic:notEqual name="tab" value="Benefits">
								<td bgcolor='#B3C9F5' valign=top><img
									src='/<msp:webapp/>images/left_inactive_tab.gif'>
								</td>
								<td bgcolor='#B3C9F5' align=center class=topNav>Benefits</td>
								<td bgcolor='#B3C9F5' valign=top><img
									src='/<msp:webapp/>images/right_inactive_tab.gif'>
								</td>
							</logic:notEqual>
							<td valign=top><img src='/<msp:webapp/>images/spacer.gif'
								width=2>
							</td>

							<logic:equal name="tab" value="Insurance">
								<td bgcolor='#6699FF' valign=top><img
									src='/<msp:webapp/>images/left_active_tab.gif'>
								</td>
								<td bgcolor='#6699FF' align=center class=topNav>Insurance</td>
								<td bgcolor='#6699FF' valign=top><img
									src='/<msp:webapp/>images/right_active_tab.gif'>
								</td>
							</logic:equal>
							<logic:notEqual name="tab" value="Insurance">
								<td bgcolor='#B3C9F5' valign=top><img
									src='/<msp:webapp/>images/left_inactive_tab.gif'>
								</td>
								<td bgcolor='#B3C9F5' align=center class=topNav>Insurance</td>
								<td bgcolor='#B3C9F5' valign=top><img
									src='/<msp:webapp/>images/right_inactive_tab.gif'>
								</td>
							</logic:notEqual>
						</jims2:isAllowed>
					</jims2:isAllowed>
				</jims2:then>
					
					
  					<jims2:elseif name="juvenileMemberForm" property="action" value="viewOnly" op="equal">
  						<jims2:then>

  					 		<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
    							<logic:equal name="tab" value="MemberInformation">
						        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
						        <td bgcolor='#6699FF' align=center class=topNav><a href="/<msp:webapp/>displayFamilyMemberDetails.do?submitAction=Details&selectedValue=<bean:write name='juvenileMemberForm' property='memberNumber'/>" class=topNav>Member Information</a></td>
						        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
    							</logic:equal>
    							<logic:notEqual name="tab" value="MemberInformation">
    								<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
    								<td bgcolor='#B3C9F5' align=center class=topNav><a href="/<msp:webapp/>displayFamilyMemberDetails.do?submitAction=Details&selectedValue=<bean:write name='juvenileMemberForm' property='memberNumber'/>" class=topNav>Member Information</a></td>
    								<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
    							</logic:notEqual>		
    							<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
   							</jims2:isAllowed>
 								
								<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
    							<logic:equal name="tab" value="AddressInfo">
						        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
						        <td bgcolor='#6699FF' align=center class=topNav><a id="addressanchor" href="/<msp:webapp/>displayManageFamilyMemberAddressAction.do?submitAction=Link" class=topNav>Address Info</a></td>
						        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
    							</logic:equal>
    							<logic:notEqual name="tab" value="AddressInfo">
    								<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
    								<td bgcolor='#B3C9F5' align=center class=topNav><a id="addressanchor" href="/<msp:webapp/>displayManageFamilyMemberAddressAction.do?submitAction=Link" class=topNav>Address Info</a></td>
    								<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
    							</logic:notEqual>	
    							<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>	
   							</jims2:isAllowed>

								<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
    							<logic:equal name="tab" value="ContactInfo">
						        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
						        <td bgcolor='#6699FF' align=center class=topNav><a href="/<msp:webapp/>displayManageFamilyMemberContactAction.do?submitAction=Link" class=topNav>Contact Info</a></td>
						        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
    							</logic:equal>
    							<logic:notEqual name="tab" value="ContactInfo">
    								<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
    								<td bgcolor='#B3C9F5' align=center class=topNav><a href="/<msp:webapp/>displayManageFamilyMemberContactAction.do?submitAction=Link" class=topNav>Contact Info</a></td>
    								<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
    							</logic:notEqual>	
    							<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
   							</jims2:isAllowed>

								<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
    							<logic:equal name="tab" value="Traits">
						        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
						        <td bgcolor='#6699FF' align=center class=topNav><a href="/<msp:webapp/>displayManageFamilyMemberTraitsAction.do?submitAction=Link" class=topNav>Traits</a></td>
						        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
    							</logic:equal>
    							<logic:notEqual name="tab" value="Traits">
    								<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
    								<td bgcolor='#B3C9F5' align=center class=topNav><a href="/<msp:webapp/>displayManageFamilyMemberTraitsAction.do?submitAction=Link" class=topNav>Traits</a></td>
    								<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
    							</logic:notEqual>
    							<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
   							</jims2:isAllowed>



						<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
							<jims2:isAllowed
								requiredFeatures='<%=Features.JCW_PRFV_MAS_BEN_INS_FAMFIN%>'>
								<logic:equal name="tab" value="Employment">
									<td bgcolor='#6699FF' valign=top><img
										src='/<msp:webapp/>images/left_active_tab.gif'>
									</td>
									<td bgcolor='#6699FF' align=center class=topNav><a
										href="/<msp:webapp/>displayManageFamilyMemberEmploymentAction.do?submitAction=Link"
										class=topNav>Employment</a>
									</td>
									<td bgcolor='#6699FF' valign=top><img
										src='/<msp:webapp/>images/right_active_tab.gif'>
									</td>
								</logic:equal>
								<logic:notEqual name="tab" value="Employment">
									<td bgcolor='#B3C9F5' valign=top><img
										src='/<msp:webapp/>images/left_inactive_tab.gif'>
									</td>
									<td bgcolor='#B3C9F5' align=center class=topNav><a
										href="/<msp:webapp/>displayManageFamilyMemberEmploymentAction.do?submitAction=Link"
										class=topNav>Employment</a>
									</td>
									<td bgcolor='#B3C9F5' valign=top><img
										src='/<msp:webapp/>images/right_inactive_tab.gif'>
									</td>
								</logic:notEqual>
								<td valign=top><img src='/<msp:webapp/>images/spacer.gif'
									width=2>
								</td>
								<logic:equal name="tab" value="Benefits">
									<td bgcolor='#6699FF' valign=top><img
										src='/<msp:webapp/>images/left_active_tab.gif'>
									</td>
									<td bgcolor='#6699FF' align=center class=topNav><a
										href="/<msp:webapp/>displayManageFamilyMemberBenefitAction.do?submitAction=Link"
										class=topNav>Benefits</a>
									</td>
									<td bgcolor='#6699FF' valign=top><img
										src='/<msp:webapp/>images/right_active_tab.gif'>
									</td>
								</logic:equal>
								<logic:notEqual name="tab" value="Benefits">
									<td bgcolor='#B3C9F5' valign=top><img
										src='/<msp:webapp/>images/left_inactive_tab.gif'>
									</td>
									<td bgcolor='#B3C9F5' align=center class=topNav><a
										href="/<msp:webapp/>displayManageFamilyMemberBenefitAction.do?submitAction=Link"
										class=topNav>Benefits</a>
									</td>
									<td bgcolor='#B3C9F5' valign=top><img
										src='/<msp:webapp/>images/right_inactive_tab.gif'>
									</td>
								</logic:notEqual>
								<td valign=top><img src='/<msp:webapp/>images/spacer.gif'
									width=2>
								</td>
								<logic:equal name="tab" value="Insurance">
									<td bgcolor='#6699FF' valign=top><img
										src='/<msp:webapp/>images/left_active_tab.gif'>
									</td>
									<td bgcolor='#6699FF' align=center class=topNav><a
										href="/<msp:webapp/>displayManageFamilyMemberInsuranceAction.do?submitAction=Link"
										class=topNav>Insurance</a>
									</td>
									<td bgcolor='#6699FF' valign=top><img
										src='/<msp:webapp/>images/right_active_tab.gif'>
									</td>
								</logic:equal>
								<logic:notEqual name="tab" value="Insurance">
									<td bgcolor='#B3C9F5' valign=top><img
										src='/<msp:webapp/>images/left_inactive_tab.gif'>
									</td>
									<td bgcolor='#B3C9F5' align=center class=topNav><a
										href="/<msp:webapp/>displayManageFamilyMemberInsuranceAction.do?submitAction=Link"
										class=topNav>Insurance</a>
									</td>
									<td bgcolor='#B3C9F5' valign=top><img
										src='/<msp:webapp/>images/right_inactive_tab.gif'>
									</td>
								</logic:notEqual>
								<td valign=top><img src='/<msp:webapp/>images/spacer.gif'
									width=2>
								</td>
								<logic:equal name="tab" value="familyFinancial">
									<td bgcolor='#6699FF' valign=top><img
										src='/<msp:webapp/>images/left_active_tab.gif'>
									</td>
									<td bgcolor='#6699FF' align=center class=topNav><a
										href="/<msp:webapp/>displayManageFamilyFinancial.do?submitAction=Link"
										class=topNav>Family Financial</a>
									</td>
									<td bgcolor='#6699FF' valign=top><img
										src='/<msp:webapp/>images/right_active_tab.gif'>
									</td>
								</logic:equal>
								<logic:notEqual name="tab" value="familyFinancial">
									<td bgcolor='#B3C9F5' valign=top><img
										src='/<msp:webapp/>images/left_inactive_tab.gif'>
									</td>
									<td bgcolor='#B3C9F5' align=center class=topNav><a
										href="/<msp:webapp/>displayManageFamilyFinancial.do?submitAction=Link"
										class=topNav>Family Financial</a>
									</td>
									<td bgcolor='#B3C9F5' valign=top><img
										src='/<msp:webapp/>images/right_inactive_tab.gif'>
									</td>
								</logic:notEqual>
							</jims2:isAllowed>
						</jims2:isAllowed>

					</jims2:then>
						
  						<jims2:else>
								<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
    							<logic:equal name="tab" value="MemberInformation">
						        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
						        <td bgcolor='#6699FF' align=center class=topNav><a href="/<msp:webapp/>displayCreateFamilyMember.do?submitAction=Update" class=topNav>Member Information</a></td>
						        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
    							</logic:equal>
    							<logic:notEqual name="tab" value="MemberInformation">
    								<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
    								<td bgcolor='#B3C9F5' align=center class=topNav><a href="/<msp:webapp/>displayCreateFamilyMember.do?submitAction=Update" class=topNav>Member Information</a></td>
    								<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
    							</logic:notEqual>		
    							<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
   							</jims2:isAllowed>

 								<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
    							<logic:equal name="tab" value="AddressInfo">
						        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
						        <td bgcolor='#6699FF' align=center class=topNav><a id="addressanchor" href="/<msp:webapp/>displayManageFamilyMemberAddressAction.do?submitAction=Link&action=%20" class=topNav>Address Info</a></td>
						        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
    							</logic:equal>
    							<logic:notEqual name="tab" value="AddressInfo">
    								<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
    								<td bgcolor='#B3C9F5' align=center class=topNav><a id="addressanchor" href="/<msp:webapp/>displayManageFamilyMemberAddressAction.do?submitAction=Link&action=%20" class=topNav>Address Info</a></td>
    								<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
    							</logic:notEqual>	
    							<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>	
   							</jims2:isAllowed>
 								<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
    							<logic:equal name="tab" value="ContactInfo">
						        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
						        <td bgcolor='#6699FF' align=center class=topNav><a href="/<msp:webapp/>displayManageFamilyMemberContactAction.do?submitAction=Link&action=%20" class=topNav>Contact Info</a></td>
						        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
    							</logic:equal>
    							<logic:notEqual name="tab" value="ContactInfo">
    								<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
    								<td bgcolor='#B3C9F5' align=center class=topNav><a href="/<msp:webapp/>displayManageFamilyMemberContactAction.do?submitAction=Link&action=%20" class=topNav>Contact Info</a></td>
    								<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
    							</logic:notEqual>	
    							<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
   							</jims2:isAllowed>

 								<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
    							<logic:equal name="tab" value="Traits">
						        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
						        <td bgcolor='#6699FF' align=center class=topNav><a href="/<msp:webapp/>displayManageFamilyMemberTraitsAction.do?submitAction=Link&action=%20" class=topNav>Traits</a></td>
						        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
    							</logic:equal>
    							<logic:notEqual name="tab" value="Traits">
    								<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
    								<td bgcolor='#B3C9F5' align=center class=topNav><a href="/<msp:webapp/>displayManageFamilyMemberTraitsAction.do?submitAction=Link&action=%20" class=topNav>Traits</a></td>
    								<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
    							</logic:notEqual>
    							<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
   							</jims2:isAllowed>
						<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
							<jims2:isAllowed
								requiredFeatures='<%=Features.JCW_PRFV_MAS_BEN_INS_FAMFIN%>'>
								<logic:equal name="tab" value="Employment">
									<td bgcolor='#6699FF' valign=top><img
										src='/<msp:webapp/>images/left_active_tab.gif'>
									</td>
									<td bgcolor='#6699FF' align=center class=topNav><a
										href="/<msp:webapp/>displayManageFamilyMemberEmploymentAction.do?submitAction=Link&action=%20"
										class=topNav>Employment</a>
									</td>
									<td bgcolor='#6699FF' valign=top><img
										src='/<msp:webapp/>images/right_active_tab.gif'>
									</td>
								</logic:equal>
								<logic:notEqual name="tab" value="Employment">
									<td bgcolor='#B3C9F5' valign=top><img
										src='/<msp:webapp/>images/left_inactive_tab.gif'>
									</td>
									<td bgcolor='#B3C9F5' align=center class=topNav><a
										href="/<msp:webapp/>displayManageFamilyMemberEmploymentAction.do?submitAction=Link&action=%20"
										class=topNav>Employment</a>
									</td>
									<td bgcolor='#B3C9F5' valign=top><img
										src='/<msp:webapp/>images/right_inactive_tab.gif'>
									</td>
								</logic:notEqual>
								<td valign=top><img src='/<msp:webapp/>images/spacer.gif'
									width=2>
								</td>

								<logic:equal name="tab" value="Benefits">
									<td bgcolor='#6699FF' valign=top><img
										src='/<msp:webapp/>images/left_active_tab.gif'>
									</td>
									<td bgcolor='#6699FF' align=center class=topNav><a
										href="/<msp:webapp/>displayManageFamilyMemberBenefitAction.do?submitAction=Link&action=%20"
										class=topNav>Benefits</a>
									</td>
									<td bgcolor='#6699FF' valign=top><img
										src='/<msp:webapp/>images/right_active_tab.gif'>
									</td>
								</logic:equal>
								<logic:notEqual name="tab" value="Benefits">
									<td bgcolor='#B3C9F5' valign=top><img
										src='/<msp:webapp/>images/left_inactive_tab.gif'>
									</td>
									<td bgcolor='#B3C9F5' align=center class=topNav><a
										href="/<msp:webapp/>displayManageFamilyMemberBenefitAction.do?submitAction=Link&action=%20"
										class=topNav>Benefits</a>
									</td>
									<td bgcolor='#B3C9F5' valign=top><img
										src='/<msp:webapp/>images/right_inactive_tab.gif'>
									</td>
								</logic:notEqual>
								<td valign=top><img src='/<msp:webapp/>images/spacer.gif'
									width=2>
								</td>

								<logic:equal name="tab" value="Insurance">
									<td bgcolor='#6699FF' valign=top><img
										src='/<msp:webapp/>images/left_active_tab.gif'>
									</td>
									<td bgcolor='#6699FF' align=center class=topNav><a
										href="/<msp:webapp/>displayManageFamilyMemberInsuranceAction.do?submitAction=Link&action=%20"
										class=topNav>Insurance</a>
									</td>
									<td bgcolor='#6699FF' valign=top><img
										src='/<msp:webapp/>images/right_active_tab.gif'>
									</td>
								</logic:equal>
								<logic:notEqual name="tab" value="Insurance">
									<td bgcolor='#B3C9F5' valign=top><img
										src='/<msp:webapp/>images/left_inactive_tab.gif'>
									</td>
									<td bgcolor='#B3C9F5' align=center class=topNav><a
										href="/<msp:webapp/>displayManageFamilyMemberInsuranceAction.do?submitAction=Link&action=%20"
										class=topNav>Insurance</a>
									</td>
									<td bgcolor='#B3C9F5' valign=top><img
										src='/<msp:webapp/>images/right_inactive_tab.gif'>
									</td>
								</logic:notEqual>
								<td valign=top><img src='/<msp:webapp/>images/spacer.gif'
									width=2>
								</td>

								<logic:equal name="tab" value="familyFinancial">
									<td bgcolor='#6699FF' valign=top><img
										src='/<msp:webapp/>images/left_active_tab.gif'>
									</td>
									<td bgcolor='#6699FF' align=center class=topNav><a
										href="/<msp:webapp/>displayManageFamilyFinancial.do?submitAction=Link"
										class=topNav>Family Financial</a>
									</td>
									<td bgcolor='#6699FF' valign=top><img
										src='/<msp:webapp/>images/right_active_tab.gif'>
									</td>
								</logic:equal>
								<logic:notEqual name="tab" value="familyFinancial">
									<td bgcolor='#B3C9F5' valign=top><img
										src='/<msp:webapp/>images/left_inactive_tab.gif'>
									</td>
									<td bgcolor='#B3C9F5' align=center class=topNav><a
										href="/<msp:webapp/>displayManageFamilyFinancial.do?submitAction=Link"
										class=topNav>Family Financial</a>
									</td>
									<td bgcolor='#B3C9F5' valign=top><img
										src='/<msp:webapp/>images/right_inactive_tab.gif'>
									</td>
								</logic:notEqual>
							</jims2:isAllowed>
						</jims2:isAllowed>
					</jims2:else>
					</jims2:elseif>
				</jims2:if>
		</logic:equal>

		<logic:notEqual name="juvenileFamilyForm" property="action" value="">	
  		<logic:notEqual name="juvenileMemberForm" property="action" value="viewOnly">
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
  				<logic:equal name="tab" value="MemberInformation">
  	        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
  	        <td bgcolor='#6699FF' align=center class=topNav>Member Information</td>
  	        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
  				</logic:equal>
  				<logic:notEqual name="tab" value="MemberInformation">
  					<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
  					<td bgcolor='#B3C9F5' align=center class=topNav>Member Information</td>
  					<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
  				</logic:notEqual>		
  				<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
				</jims2:isAllowed>
			</logic:notEqual>

			<logic:equal name="juvenileMemberForm" property="action" value="viewOnly">
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
  				<logic:equal name="tab" value="MemberInformation">
  	        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
  	        <td bgcolor='#6699FF' align=center class=topNav><a href="/<msp:webapp/>displayFamilyMemberDetails.do?submitAction=Details&selectedValue=<bean:write name='juvenileMemberForm' property='memberNumber'/>" class=topNav>Member Information</a></td>
  	        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
  				</logic:equal>
  				<logic:notEqual name="tab" value="MemberInformation">
  					<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
  					<td bgcolor='#B3C9F5' align=center class=topNav><a href="/<msp:webapp/>displayFamilyMemberDetails.do?submitAction=Details&selectedValue=<bean:write name='juvenileMemberForm' property='memberNumber'/>" class=topNav>Member Information</a></td>
  					<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
  				</logic:notEqual>		

					<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
				</jims2:isAllowed>
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
  				<logic:equal name="tab" value="AddressInfo">
  	        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
  	        <td bgcolor='#6699FF' align=center class=topNav><a id="addressanchor" href="/<msp:webapp/>displayManageFamilyMemberAddressAction.do?submitAction=Link&action=viewOnly" class=topNav>Address Info</a></td>
  	        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
  				</logic:equal>
  				<logic:notEqual name="tab" value="AddressInfo">
  					<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
  					<td bgcolor='#B3C9F5' align=center class=topNav><a id="addressanchor" href="/<msp:webapp/>displayManageFamilyMemberAddressAction.do?submitAction=Link&action=viewOnly" class=topNav>Address Info</a></td>
  					<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
  				</logic:notEqual>	

					<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>	
				</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
  				<logic:equal name="tab" value="ContactInfo">
  	        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
  	        <td bgcolor='#6699FF' align=center class=topNav><a href="/<msp:webapp/>displayManageFamilyMemberContactAction.do?submitAction=Link&action=viewOnly" class=topNav>Contact Info</a></td>
  	        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
  				</logic:equal>
  				<logic:notEqual name="tab" value="ContactInfo">
  					<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
  					<td bgcolor='#B3C9F5' align=center class=topNav><a href="/<msp:webapp/>displayManageFamilyMemberContactAction.do?submitAction=Link&action=viewOnly" class=topNav>Contact Info</a></td>
  					<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
  				</logic:notEqual>	
					<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
				</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
					<logic:equal name="tab" value="Traits">
		        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/left_active_tab.gif'></td>
		        <td bgcolor='#6699FF' align=center class=topNav><a href="/<msp:webapp/>displayManageFamilyMemberTraitsAction.do?submitAction=Link&action=viewOnly" class=topNav>Traits</a></td>
		        <td bgcolor='#6699FF' valign=top><img src='/<msp:webapp/>images/right_active_tab.gif'></td>				
  				</logic:equal>
  				<logic:notEqual name="tab" value="Traits">
  					<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/left_inactive_tab.gif'></td>
  					<td bgcolor='#B3C9F5' align=center class=topNav><a href="/<msp:webapp/>displayManageFamilyMemberTraitsAction.do?submitAction=Link&action=viewOnly" class=topNav>Traits</a></td>
  					<td bgcolor='#B3C9F5' valign=top><img src='/<msp:webapp/>images/right_inactive_tab.gif'></td>
  				</logic:notEqual>
  				<td valign=top><img src='/<msp:webapp/>images/spacer.gif' width=2></td>
 				</jims2:isAllowed>

				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_FAMILY%>'>
					<jims2:isAllowed
						requiredFeatures='<%=Features.JCW_PRFV_MAS_BEN_INS_FAMFIN%>'>
						<logic:equal name="tab" value="Employment">
							<td bgcolor='#6699FF' valign=top><img
								src='/<msp:webapp/>images/left_active_tab.gif'>
							</td>
							<td bgcolor='#6699FF' align=center class=topNav><a
								href="/<msp:webapp/>displayManageFamilyMemberEmploymentAction.do?submitAction=Link&action=viewOnly"
								class=topNav>Employment</a>
							</td>
							<td bgcolor='#6699FF' valign=top><img
								src='/<msp:webapp/>images/right_active_tab.gif'>
							</td>
						</logic:equal>
						<logic:notEqual name="tab" value="Employment">
							<td bgcolor='#B3C9F5' valign=top><img
								src='/<msp:webapp/>images/left_inactive_tab.gif'>
							</td>
							<td bgcolor='#B3C9F5' align=center class=topNav><a
								href="/<msp:webapp/>displayManageFamilyMemberEmploymentAction.do?submitAction=Link&action=viewOnly"
								class=topNav>Employment</a>
							</td>
							<td bgcolor='#B3C9F5' valign=top><img
								src='/<msp:webapp/>images/right_inactive_tab.gif'>
							</td>
						</logic:notEqual>
						<td valign=top><img src='/<msp:webapp/>images/spacer.gif'
							width=2>
						</td>

						<logic:equal name="tab" value="Benefits">
							<td bgcolor='#6699FF' valign=top><img
								src='/<msp:webapp/>images/left_active_tab.gif'>
							</td>
							<td bgcolor='#6699FF' align=center class=topNav><a
								href="/<msp:webapp/>displayManageFamilyMemberBenefitAction.do?submitAction=Link&action=viewOnly"
								class=topNav>Benefits</a>
							</td>
							<td bgcolor='#6699FF' valign=top><img
								src='/<msp:webapp/>images/right_active_tab.gif'>
							</td>
						</logic:equal>
						<logic:notEqual name="tab" value="Benefits">
							<td bgcolor='#B3C9F5' valign=top><img
								src='/<msp:webapp/>images/left_inactive_tab.gif'>
							</td>
							<td bgcolor='#B3C9F5' align=center class=topNav><a
								href="/<msp:webapp/>displayManageFamilyMemberBenefitAction.do?submitAction=Link&action=viewOnly"
								class=topNav>Benefits</a>
							</td>
							<td bgcolor='#B3C9F5' valign=top><img
								src='/<msp:webapp/>images/right_inactive_tab.gif'>
							</td>
						</logic:notEqual>
						<td valign=top><img src='/<msp:webapp/>images/spacer.gif'
							width=2>
						</td>

						<logic:equal name="tab" value="Insurance">
							<td bgcolor='#6699FF' valign=top><img
								src='/<msp:webapp/>images/left_active_tab.gif'>
							</td>
							<td bgcolor='#6699FF' align=center class=topNav><a
								href="/<msp:webapp/>displayManageFamilyMemberInsuranceAction.do?submitAction=Link"
								class=topNav>Insurance</a>
							</td>
							<td bgcolor='#6699FF' valign=top><img
								src='/<msp:webapp/>images/right_active_tab.gif'>
							</td>
						</logic:equal>
						<logic:notEqual name="tab" value="Insurance">
							<td bgcolor='#B3C9F5' valign=top><img
								src='/<msp:webapp/>images/left_inactive_tab.gif'>
							</td>
							<td bgcolor='#B3C9F5' align=center class=topNav><a
								href="/<msp:webapp/>displayManageFamilyMemberInsuranceAction.do?submitAction=Link&action=viewOnly"
								class=topNav>Insurance</a>
							</td>
							<td bgcolor='#B3C9F5' valign=top><img
								src='/<msp:webapp/>images/right_inactive_tab.gif'>
							</td>
						</logic:notEqual>
						<td valign=top><img src='/<msp:webapp/>images/spacer.gif'
							width=2>
						</td>

						<logic:equal name="tab" value="familyFinancial">
							<td bgcolor='#6699FF' valign=top><img
								src='/<msp:webapp/>images/left_active_tab.gif'>
							</td>
							<td bgcolor='#6699FF' align=center class=topNav><a
								href="/<msp:webapp/>displayManageFamilyFinancial.do?submitAction=Link"
								class=topNav>Family Financial</a>
							</td>
							<td bgcolor='#6699FF' valign=top><img
								src='/<msp:webapp/>images/right_active_tab.gif'>
							</td>
						</logic:equal>
						<logic:notEqual name="tab" value="familyFinancial">
							<td bgcolor='#B3C9F5' valign=top><img
								src='/<msp:webapp/>images/left_inactive_tab.gif'>
							</td>
							<td bgcolor='#B3C9F5' align=center class=topNav><a
								href="/<msp:webapp/>displayManageFamilyFinancial.do?submitAction=Link"
								class=topNav>Family Financial</a>
							</td>
							<td bgcolor='#B3C9F5' valign=top><img
								src='/<msp:webapp/>images/right_inactive_tab.gif'>
							</td>
						</logic:notEqual>
					</jims2:isAllowed>
				</jims2:isAllowed>
			</logic:equal>
		</logic:notEqual>
	</tr>
</table>
