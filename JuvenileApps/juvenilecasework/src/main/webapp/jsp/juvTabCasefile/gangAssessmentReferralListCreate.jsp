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
	<title><bean:message key="title.heading"/> <bean:message key="title.juvenileCasework"/> - gangAssessmentReferralListCreate.jsp</title>
	
	<!--JQUERY FRAMEWORK-->
	<%@include file="../jQuery.fw"%>
	<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
	<script type='text/javascript' src="/<msp:webapp/>js/gangReferrals.js?b=12"></script>
	<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
	<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
	<script type="text/javaScript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
	<script type="text/javaScript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
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
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Enter values then click next to continue.</li>
			</ul>
		</td>
	</tr>
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
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
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
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
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
					       					<td valign='top' colspan='8' class='detailHead'>Gang Assessment Referral</td>
					       					<html:hidden styleId="gangAssessmentAction" name="assessmentReferralForm" property="action"/>	
					       				</tr>
										<tr>
											<td>
											   	<table cellpadding="4" cellspacing="1" width='100%'>
											   			<logic:equal property="action" value="create" name="assessmentReferralForm">
													   		<tr>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.diamond"/> <bean:message key="prompt.referralDate"/></td>
																<td class="formDe" colspan='8'>
																	<html:text name="assessmentReferralForm" property="referralDate" styleId="referralDate" size="10" maxlength="10" size="10" readonly="true"/>
																</td>
															</tr>
															<tr>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.personMakingReferral"/></td>
																<td class="formDe" width="15%" colspan='2'><bean:write name="assessmentReferralForm" property="personMakingRef"/></td>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.placementFacility"/></td>
																<td class="formDe" colspan='4' nowrap="nowrap">
																	<html:select property="placementFacilityId" styleId="placementFacilityId">
																		<html:option key="select.generic" value="" />
																		<html:optionsCollection property="placementFacilities" value="code" label="description"/>				
																	</html:select>
																</td>
															</tr>
														</logic:equal>
														<logic:equal property="action" value="update" name="assessmentReferralForm">
															<tr>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.referralDate"/></td>
																<td class="formDe" colspan='8'><bean:write name="assessmentReferralForm" property="referralDate"/></td>
															</tr>
															<tr>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.personMakingReferral"/></td>
																<td class="formDe" width="15%" nowrap="nowrap" colspan='2'><bean:write name="assessmentReferralForm" property="personMakingRef"/></td>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.placementFacility"/></td>
																<td class="formDe" colspan='4' nowrap="nowrap"><bean:write name="assessmentReferralForm" property="placementFacility"/></td>
															</tr>
														</logic:equal>
															<tr>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.juvenileName"/></td>
																<td class="formDe" width="15%" nowrap="nowrap" colspan='2'><bean:write name="assessmentReferralForm" property="juvenileName"/></td>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.juvenileNumber"/></td>
																<td class="formDe" width="15%" colspan='4'><bean:write name="assessmentReferralForm" property="juvenileNum"/></td>
															</tr>
															<tr>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.dateOfBirth"/></td>
																<td class="formDe" width="15%" colspan='2'><bean:write name="assessmentReferralForm" property="dateOfBirth"/></td>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.gender"/></td>
																<td class="formDe" width="15%" colspan='4'><bean:write name="assessmentReferralForm" property="gender"/></td>
															</tr>
															<tr>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.language"/></td>
																<td class="formDe" width="15%" colspan='2'><bean:write name="assessmentReferralForm" property="language"/></td>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.race"/>/<bean:message key="prompt.ethnicity"/></td>
																<td class="formDe" width="15%" colspan='4'><bean:write name="assessmentReferralForm" property="raceOrEthinicity"/></td>
															</tr>															
														<logic:equal property="action" value="create" name="assessmentReferralForm">
															<tr>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%">Parent(s) was notified that a gang assessment was requested for the youth? </td>																
																<td class='formDe' colspan='8' nowrap>
									  							 	<html:radio styleId="parentNotifiedGangAssReqYes" name="assessmentReferralForm" property="parentNotifiedGangAssReq" value="Yes"/><bean:message key="prompt.yes" />
									  							    <html:radio styleId="parentNotifiedGangAssReqNo" name="assessmentReferralForm" property="parentNotifiedGangAssReq" value="No" checked=true /><bean:message key="prompt.no" /> 
									  							    <html:hidden styleId="parentNotifiedGangAssReqId" name="assessmentReferralForm" property="parentNotifiedGangAssReq"/>	
		  							 							</td>
															</tr>
															<tr>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%">+ <bean:message key="prompt.nameOfGangCliqueSuspected"/></td>
																<td  valign='top' class="formDe" width="15%" colspan='8'>
																	<b><bean:message key="prompt.gangName"/></b> 
																	<br>
																	<html:select property="gangNameId" styleId="gangSelId" >
																		<html:option key="select.generic" value="" />
																		<html:optionsCollection property="gangNames" value="code" label="description"/>				
																	</html:select>
																	<br>
																	<div id="otherGangTxt" class="hidden">
																		<bean:message key="prompt.2.diamond"/><bean:message key="prompt.other" /> <bean:message key="prompt.gang" /> <bean:message key="prompt.name"/>
																		<html:text name="assessmentReferralForm" property="otherGangName" styleId="othrGangName" size="50" maxlength="30" />
																	</div>
																	<br>
																	<div id="descHybrid" class="hidden" nowrap="nowrap">
																		<bean:message key="prompt.2.diamond"/><bean:message key="prompt.descHybrid"/><b> (Max. characters allowed: 255) </b> <br/>
																		<html:textarea name="assessmentReferralForm" property="descHybrid" styleId="descHybridId"  rows="3" style="width:100%" />
																	</div>
																</td>
																<td  valign='top' class="formDe hidden" width="15%" colspan="6">
																	<b><bean:message key="prompt.clique"/></b>
																	<br>
																	<html:select property="cliqueSetId" styleId="cliqueSelId">
																		<html:option key="select.generic" value=""/>
																		<html:optionsCollection property="cliqueSets" value="code" label="description"/>				
																	</html:select>
																	<br>
																	<div id="otherCliqueTxt" class="hidden">
																		<bean:message key="prompt.2.diamond"/><bean:message key="prompt.other"/><bean:message key="prompt.cliqueSet"/>
																		<br>
																		<html:text name="assessmentReferralForm" property="otherCliqueSet" styleId="othrCliqueName" size="50" maxlength="30" />
																	</div>
																</td>
															</tr>
															<tr>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.diamond"/> <bean:message key="prompt.reasonForReferral"/><br>(check all that apply)</td>
																<td class="formDe" width="15%" colspan='8'>
																		<logic:iterate indexId="idx" id="reasonForReferralIdx" name="assessmentReferralForm" property="reasonForReferralList">
																			<input type=checkbox name="selectedReasonForReferrals" value="<bean:write name="reasonForReferralIdx" property="code"/>"><bean:write name="reasonForReferralIdx" property="description"/></input><br>
																		</logic:iterate> 
																		<input type="checkbox" name="otherReasonForReferral" id="othrReasForRefId" value="<bean:message key="prompt.other"/>"/><bean:message key="prompt.other"/> 
																		<br>
																		<div id="otherReasonForReferralId" class="hidden">
																			<b><bean:message key="prompt.otherReasonForRefferalTxt"/> 
																			<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
			                													<tiles:put name="tTextField" value="otherComments"/>
			                 														<tiles:put name="tSpellCount" value="spellBtn1" />
			              														</tiles:insert> (Max. Characters Allowed:255)</b>
																			<br>
																			<html:textarea name="assessmentReferralForm" property="otherReasonForReferralTxt" styleId="otherReasonForReferralTxt"  rows="3" style="width:100%" />
																		</div>
																</td>
															</tr>
															<tr>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.diamond"/> <bean:message key="prompt.levelofGangInvolvement"/></td>
																<td class="formDe" width="15%"  colspan='8'>
																	<logic:iterate indexId="index" id="lvlOfGangInvolvementIdx" name="assessmentReferralForm" property="levelOfGangInvolvementList">
																		<input type="radio" name="lvlOfGangInvolvementId" id="lvlOfGangInvolvement" value="<bean:write name="lvlOfGangInvolvementIdx" property="code"/>"><bean:write name="lvlOfGangInvolvementIdx" property="description"/></input><br>
																	</logic:iterate> 
																</td>
															</tr>						
															<tr>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%" colspan='8'><bean:message key="prompt.comments"/>/<bean:message key="prompt.notes"/> 
																	<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
			                										<tiles:put name="tTextField" value="comments"/>
			                 											<tiles:put name="tSpellCount" value="spellBtn1" />
			              											</tiles:insert> (Max Characters Allowed: 3500)
																</td>
															</tr>
															<tr>
																<td class="formDe" width="15%" colspan='8'>
																	<html:textarea name="assessmentReferralForm" property="comments" styleId="comments"  rows="8" style="width:100%" />
																</td>
															</tr>
														</logic:equal>
														<logic:equal property="action" value="update" name="assessmentReferralForm">
															<tr>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%">Parent(s) was notified that a gang assessment was requested for the youth?</td>																
																<td class='formDe' colspan='4' nowrap>
												  					<bean:write name="assessmentReferralForm" property="parentNotifiedGangAssReq"/> 	
					  							 				</td>
															</tr>															
															<tr>
																<td valign='top'  class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.nameOfGangCliqueSuspected"/></td>
																<td valign='top'  class="formDe" width="15%" colspan='8'>
																	<b><bean:message key="prompt.gangName"/></b> 
																	<br>
																	<bean:write name="assessmentReferralForm" property="gangName"/>
																	<logic:equal  name="assessmentReferralForm" property="gangName" value="OTHER">
																	<logic:notEmpty name="assessmentReferralForm"  property="otherGangName">
																	- <bean:write name="assessmentReferralForm" property="otherGangName"/>
																	</logic:notEmpty>
																	 </logic:equal>
																	<br>
																	<logic:notEmpty name="assessmentReferralForm" property="descHybrid">
																		<b><bean:message key="prompt.descHybrid"/></b>
																		<br>
																		<bean:write name="assessmentReferralForm" property="descHybrid"/>
																	</logic:notEmpty>
																</td>
																<td  valign='top' class="formDe hidden" width="15%" colspan="6">
																	<b><bean:message key="prompt.clique"/></b>
																	<br>
																	<bean:write name="assessmentReferralForm" property="cliqueSet"/>
																	<logic:equal  name="assessmentReferralForm" property="cliqueSet" value="OTHER">
																		 <logic:notEmpty name="assessmentReferralForm"  property="otherCliqueSet">
																		  - <bean:write name="assessmentReferralForm" property="otherCliqueSet"/>
																		 </logic:notEmpty>
																	 </logic:equal>
																</td>
															</tr>
															<tr>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.reasonForReferral"/></td>
																<td class="formDe" width="15%" colspan='8'>
																   <logic:notEmpty name="assessmentReferralForm"  property="selectedReasonForReferralsList">
																	 <logic:iterate indexId="index" id="reasonForReferralId" name="assessmentReferralForm" property="selectedReasonForReferralsList">
																		<bean:write name="reasonForReferralId"/><br>
																	 </logic:iterate> 
																  </logic:notEmpty>
																  <logic:notEmpty name="assessmentReferralForm"  property="otherReasonForReferral">
																     <bean:write name="assessmentReferralForm" property="otherReasonForReferral"/> - 
																  	 <bean:write name="assessmentReferralForm" property="otherReasonForReferralTxt"/>
																  </logic:notEmpty>
																</td>
															</tr>
															<tr>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.levelofGangInvolvement"/></td>
																<td class="formDe" width="15%"  colspan='8'>
																	 <logic:notEmpty name="assessmentReferralForm" property="lvlOfGangInvolvement">
																		<bean:write name="assessmentReferralForm" property="lvlOfGangInvolvement"/>
																	</logic:notEmpty>
																</td>
															</tr>
															<tr>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%">Parent(s) was notified that a gang assessment will be completed with the youth? </td>																
																<td class='formDe' colspan='8' nowrap>
									  							 	<html:radio styleId="parentNotifiedYes" name="assessmentReferralForm" property="parentNotified" value="Yes" checked=true/><bean:message key="prompt.yes" />
									  							    <html:radio styleId="parentNotifiedNo" name="assessmentReferralForm" property="parentNotified" value="No"/><bean:message key="prompt.no" /> 
									  							    <html:hidden styleId="parentNotifiedId" name="assessmentReferralForm" property="parentNotified"/>	
		  							 							</td>
															</tr>
															<!-- 
															<tr>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.gangparentNotification"/></td>																
																<td class='formDe' colspan='8' nowrap>
									  							 	<html:radio styleId="parentNotifiedYes" name="assessmentReferralForm" property="parentNotified" value="Yes"/><bean:message key="prompt.yes" />
									  							    <html:radio styleId="parentNotifiedNo" name="assessmentReferralForm" property="parentNotified" value="No"/><bean:message key="prompt.no" /> 
									  							    <html:hidden styleId="parentNotifiedId" name="assessmentReferralForm" property="parentNotified"/>	
		  							 							</td>
															</tr>
															 -->
															<tr>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.comments"/>/<bean:message key="prompt.notes"/></td>
																<td class="formDe" width="15%" colspan='8'>
																	<bean:write name="assessmentReferralForm" property="comments"/>
																</td>
															</tr>
															<tr>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.diamond"/><bean:message key="prompt.status"/></td>
																<td class="formDe">
																		<input type="radio" name="acceptedStatus" id="acceptedStatusId" value="<bean:message key="prompt.accepted"/>"/>
																		<bean:message key="prompt.accepted"/>
																</td>
																<td class="formDe">
																		<input type="radio" name="acceptedStatus" id="rejectedStatusId" value="<bean:message key="prompt.rejected"/>"/>
																		<bean:message key="prompt.rejected"/>
																</td>
																<td class="formDe"  colspan='2'>
																		<input type="radio" name="acceptedStatus" id="unableStatusId" value="<bean:message key="prompt.unable"/>"/>
																		<bean:message key="prompt.unabletoaccessYouth"/>
																</td>
															</tr>
															<tr>	
																<td class="formDeLabel" nowrap="nowrap" width="15%"><bean:message key="prompt.diamond"/><bean:message key="prompt.recommendations"/></td>	
																<td class="formDe" colspan='6'>
																	<html:select property="recommendationsId" styleId="recommendationId">
																				<html:option key="select.generic" value="" />
																				<html:optionsCollection property="recommendationsList" value="code" label="description"/>				
																	</html:select> 
																</td>
															</tr>
															<tr id="rejectionReasonId" class="hidden">
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%" colspan='8'>
																	<bean:message key="prompt.diamond"/>Please Explain  
																		<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
			                												<tiles:put name="tTextField" value="rejectionReason"/>
			                 												<tiles:put name="tSpellCount" value="spellBtn1" />
			              												</tiles:insert> (Max. Characters Allowed:255)
																</td>
															</tr>		
															<tr id="rejectionReasonIdTxt" class="hidden">		
																<td class="formDe" width="15%" colspan='8'>
																	<html:textarea name="assessmentReferralForm" property="rejectionReason" styleId="rejecReasonId" rows="3" style="width:100%" />
																</td>
															</tr>
															<tr>
																<td valign='top' class="formDeLabel" nowrap="nowrap" width="15%" colspan='8'><bean:message key="prompt.diamond"/><bean:message key="prompt.conclusion"/>
																	<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
			                												<tiles:put name="tTextField" value="conclusion"/>
			                 												<tiles:put name="tSpellCount" value="spellBtn1" />
			              												</tiles:insert> (Max Characters Allowed: 3500)</td>
															</tr>
															<tr>
																<td class="formDe" width="15%" colspan='8'>
																	<html:textarea name="assessmentReferralForm" property="conclusion" styleId="conclusionId"  rows="8" style="width:100%" />
																</td>
															</tr>
														</logic:equal>
													</table>
											</td>
										</tr>
									<div class="spacer"></div>
								 </table> 
								 
									<table align="center">
										<tr>
											<td>
					  								<html:button property="button.back" styleId="gangCreateBack"><bean:message key="button.back" /></html:button> 
													<html:submit property="submitAction" styleId="gangListCreateNext">				
															<bean:message key="button.next" />
								  					</html:submit>	
					  								<input type="button" name="reset" value="<bean:message key='button.refresh'/>" />
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