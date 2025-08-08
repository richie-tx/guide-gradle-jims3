<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 06/21/2005		DWilliamson	Create JSP --%>
<%-- 07/20/2009     C Shimek    #61004 added timeout.js  --%>
<%-- 07/20/2013     C Shimek    #61004 started changes but put on hold --%>
<%-- 09/12/2013     C Shimek    #76047 made changes for Trait Status Update  --%>
<%-- 10/25/2013     C Shimek    #76302 commented out logic tag for gangsForm confirmation message --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.Features" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
	<msp:nocache />
	<%-- Checks to make sure if the user is logged in. --%>
	<%--msp:login / --%>
	<meta http-equiv="Content-Language" content="en-us">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<META name="GENERATOR" content="IBM WebSphere Studio">
	<META http-equiv="Content-Style-Type" content="text/css">
	<%-- STYLE SHEET LINK --%>
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
	<html:base />
	<%-- Javascript for emulated navigation --%>
	<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- gangAssessmentReferralListSummary.jsp</title>
	
	<!--JQUERY FRAMEWORK-->
	<%@include file="../jQuery.fw"%>
	<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
	<script type='text/javascript' src="/<msp:webapp/>js/gangReferrals.js"></script>
	<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
	<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
	<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script> 
	<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script> 
	
</head>
<%--END HEADER TAG--%>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/handleGangAssessmentReferralCreate" target="content"> 
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Assessment Referral List</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<div class="spacer4px" />
<%-- BEGIN INSTRUCTION/CONFIRMATION TABLE --%>
<table width="98%" border="0">
	<logic:equal name="assessmentReferralForm" property="secondaryAction" value="details">	
	<tr>
		<td>
			<ul>
				<li>Verify all entries are correct then click finish to save assessment referral.</li>
				<li>Click back to manage changes.</li>
			</ul>
		</td>
	</tr>
	</logic:equal>	
	<logic:equal name="assessmentReferralForm" property="secondaryAction" value="confirm">			
		<tr>
			<td class="confirm">Assessment Referral successfully saved and notification sent.</td>
		</tr>   				
	</logic:equal> 
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN JUVENILE HEADER INCLUDE --%> 
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER INCLUDE  --%> 

<br>
<%-- BEGIN DETAILS TABLE --%>
<div class="spacer"></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
<%-- BEGIN PROFILE/GREEN TABS TABLE --%>		
			<table width='100%' border="0" cellpadding="0" cellspacing="0" align="center">
				<tr>
					<td valign="top">
						<%--tabs start--%>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="interviewinfotab"/>
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>	
						<%--tabs end--%>
					</td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
				</tr>
			</table>
<%-- END PROFILE/GREEN TABS TABLE --%>

<%-- BEGIN GREEN TABS BORDER TABLE --%>			
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen" align="center">
				<tr>
					<td valign="top" align="center">
						<div class="spacer"></div>	
<%-- BEGIN BLUE TABS TABLE --%>	
						<table width='98%' border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign='top'>
									<tiles:insert page="/jsp/caseworkCommon/juvenileProfileCasefileTabs.jsp" flush="true">
    									<tiles:put name="tabid" value="assessmentReferral"/>
    									<tiles:put name="juvnum" value='<%=request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>'/>
    								</tiles:insert>	
								</td>
							</tr>
							<tr>
								<td bgcolor='#6699ff'><img src="/<msp:webapp/>images/spacer.gif" width="5"></td>
					  	   </tr>
            			</table>
<%-- END BLUE TABS TABLE --%>	
<%-- BEGIN BLUE TABS BORDER TABLE --%>									
						<table width="98%" align="center" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td valign="top" align="center">
									<div class="spacer"></div>
									<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr> 		<!-- table title bar -->
					       				  <td valign='top' colspan='4' class='detailHead'>Gang Assessment Referral</td>
					       				</tr>
										<tr>
											<td>
												<table cellpadding="4" border="0" cellspacing="1" width='100%'>
												<tr>
													<td class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.referralDate"/></td>
													<td class="formDe" colspan='4'>
														<bean:write name="assessmentReferralForm" property="referralDate"/>
													</td>
												</tr>
												<tr>
													<td class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.personMakingReferral"/></td>
													<td class="formDe" width="15%"><bean:write name="assessmentReferralForm" property="personMakingRef"/></td>
													<td class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.placementFacility"/></td>
													<td class="formDe">
														<bean:write name="assessmentReferralForm" property="placementFacility"/>
													</td>
												</tr>
												<tr>
													<td class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.juvenileName"/></td>
													<td class="formDe" width="15%"><bean:write name="assessmentReferralForm" property="juvenileName"/></td>
													<td class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.juvenileNumber"/></td>
													<td class="formDe" width="15%"><bean:write name="assessmentReferralForm" property="juvenileNum"/></td>
												</tr>
												<tr>
													<td class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.dateOfBirth"/></td>
													<td class="formDe" width="15%"><bean:write name="assessmentReferralForm" property="dateOfBirth"/></td>
													<td class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.gender"/></td>
													<td class="formDe" width="15%"><bean:write name="assessmentReferralForm" property="gender"/></td>
												</tr>
												<tr>
													<td class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.language"/></td>
													<td class="formDe" width="15%"><bean:write name="assessmentReferralForm" property="language"/></td>
													<td class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.race"/>/<bean:message key="prompt.ethnicity"/></td>
													<td class="formDe" width="15%"><bean:write name="assessmentReferralForm" property="raceOrEthinicity"/></td>
												</tr>
												<tr>
													<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%">Parent(s) was notified that a gang assessment was requested for the youth?</td>																
													<td class='formDe' colspan='4' nowrap>
									  					<bean:write name="assessmentReferralForm" property="parentNotifiedGangAssReq"/> 	
		  							 				</td>
												</tr>											
												<tr>
													<td valign='top'  class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.nameOfGangCliqueSuspected"/></td>
													<td  valign='top'  class="formDe" width="15%" colspan="4">
														<b><bean:message key="prompt.gangName"/></b> 
														<br>
														<bean:write name="assessmentReferralForm" property="gangName"/>
														<br>
														<logic:notEmpty name="assessmentReferralForm" property="descHybrid">
															<b><bean:message key="prompt.descHybrid"/></b><br>
															<bean:write name="assessmentReferralForm" property="descHybrid"/>
														</logic:notEmpty>
													</td>
													<td  valign='top' class="formDe hidden" width="15%" colspan="2">
														<b><bean:message key="prompt.clique"/></b>
														<br>
														<bean:write name="assessmentReferralForm" property="cliqueSet"/>
													</td>
												</tr>
												<tr>
													<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.reasonForReferral"/></td>
													<td class="formDe" width="15%" colspan='4' valign='top'>
													    <logic:notEmpty name="assessmentReferralForm"  property="selectedReasonForReferralsList">
															<logic:iterate indexId="index" id="reasonForReferralId" name="assessmentReferralForm" property="selectedReasonForReferralsList">
																<bean:write name="reasonForReferralId"/><br/>
															</logic:iterate> 
														</logic:notEmpty>
														<logic:notEmpty name="assessmentReferralForm"  property="otherReasonForReferral">
														  	<bean:write name="assessmentReferralForm" property="otherReasonForReferral"/> - 
														  	<bean:write name="assessmentReferralForm" property="otherReasonForReferralTxt"/>
														</logic:notEmpty>
													</td>
												</tr>
												<tr>
													<td class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.levelofGangInvolvement"/></td>
													<td class="formDe" width="15%"  colspan='4'>
												   	 <logic:notEmpty name="assessmentReferralForm" property="lvlOfGangInvolvement">
														<bean:write name="assessmentReferralForm" property="lvlOfGangInvolvement"/>
													 </logic:notEmpty>
													</td>
												</tr>
												<logic:equal property="action" value="update" name="assessmentReferralForm">
													<tr>
														<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%">Parent(s) was notified that a gang assessment will be completed with the youth? </td>																
														<td class='formDe' colspan='4' nowrap>
										  					<bean:write name="assessmentReferralForm" property="parentNotified"/> 	
			  							 				</td>
													</tr>
												</logic:equal>
												<tr>
													<td class="formDeLabel" nowrap="nowrap" width="15%" colspan='4'><bean:message key="prompt.comments"/>/<bean:message key="prompt.notes"/></td>
												</tr>
												<tr>
													<td class="formDe" width="15%" colspan='4'>
														<bean:write name="assessmentReferralForm" property="comments"/>
													</td>
												</tr>
												<!-- Update assessment fields starts -->
												<logic:notEmpty name="assessmentReferralForm" property="acceptedStatus">
												<tr>
													<td class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.status"/></td>
													<!-- add if -->
													<jims2:if name="assessmentReferralForm" property="acceptedStatus" value="Unable" op="equal">
														<jims2:then>
														<td class="formDe" width="15%">Unable to Assess Youth</td>
														</jims2:then>
														<jims2:else>
														<td class="formDe" width="15%"><bean:write name="assessmentReferralForm" property="acceptedStatus"/></td>
														</jims2:else>
													</jims2:if>													
													<td class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.recommendations"/></td>
													<td class="formDe" width="15%"><bean:write name="assessmentReferralForm" property="recommendations"/></td>
												</tr>
												<logic:notEmpty name="assessmentReferralForm"  property="rejectionReason">
												<tr>
													<td class="formDeLabel" nowrap="nowrap" width="15%" colspan='4'>Please Explain (Max Characters Allowed: 255)</td>
												</tr>
												<tr>
													<td class="formDe" width="15%" colspan='4'>
														<bean:write name="assessmentReferralForm" property="rejectionReason"/>
													</td>
												</tr>
												</logic:notEmpty>
												<tr>
													<td class="formDeLabel" nowrap="nowrap" width="15%" colspan='4'><bean:message key="prompt.conclusion"/>/<bean:message key="prompt.notes"/> (Max Characters Allowed: 3500)</td>
												</tr>
												<tr>
													<td class="formDe" width="15%" colspan='4'>
														<bean:write name="assessmentReferralForm" property="conclusion"/>
													</td>
												</tr>
												</logic:notEmpty>
											</table>
											 <div class="spacer"></div>
										</td>
									</tr>
								 </table> 
								 
								<table align="center">
									<tr>
										<td>
				  							<logic:equal name="assessmentReferralForm" property="secondaryAction" value="details">  	
												<td align="center">
													<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>		  					
													<html:submit property="submitAction" styleId="gangFinish" >				
														<bean:message key="button.finish"></bean:message>
													</html:submit>	
													<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
												</td>
											</logic:equal>
											<logic:equal name="assessmentReferralForm" property="secondaryAction" value="confirm">  
												<td align="center">
													<html:submit property="submitAction"><bean:message key="button.returnToAssessmentRequestList"></bean:message></html:submit>
												</td>		
											</logic:equal>
											<logic:equal name="assessmentReferralForm" property="secondaryAction" value="viewDetail">  
													<td align="center">		
														<html:submit property="submitAction"><bean:message key="button.returnToAssessmentRequestList"></bean:message></html:submit>
													</td>		
										    </logic:equal>
									  </td>
						    		</tr>
								</table>
							 	 <div class="spacer"></div>
								 </td>
								</tr>
							</table>
								<div class="spacer"></div>	
				   			<div class="spacer"></div>
<%-- END GREEN TABS BORDER TABLE --%>						
						</td>
					</tr>
			</table>
		</td>
	</tr>
</table>
<%-- END DETAIL TABLE --%>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>