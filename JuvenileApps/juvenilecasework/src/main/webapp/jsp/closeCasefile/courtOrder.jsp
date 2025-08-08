<!DOCTYPE HTML>
<%-- Used in closing of a casefile --%>
<%--MODIFICATIONS --%>
<%-- 11/29/2005		Justin Jose	Created JSP --%>
<%-- 05/01/2012		C Shimek	#73346 Revise hardcoded TYC prompts to TJJD --%>
<%-- 07/12/2012 	C Shimek    #73565 added age > 20 check (juvUnder21) to not allow updating info --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.Features"%>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - courtOrder.jsp</title>

<!--JQUERY FRAMEWORK LOCAL REFERENCE-->
 <%@include file="../jQuery.fw"%> 

<%-- Javascript for emulated navigation --%>

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/commonAppCourtOrder.js"></script>

<html:javascript formName="commonAppCourtForm" />
<script type='text/javascript'>
/* function showJudgeName(theForm) // all replaced in jquery
{
	var forwardURL = "/<msp:webapp/>displayCommonAppCourtOrderUpdateSummary.do?submitAction=Link";

	theForm.action = forwardURL;
	theForm.submit();
} */

/* function populateCurrentOffense(theForm)
{
	var forwardURL = "/<msp:webapp/>displayCommonAppCourtOrderUpdateSummary.do?submitAction=Find";

	theForm.action = forwardURL;
	theForm.submit();
} */

/* function populateOffenseCode(theForm)
{
	var forwardURL = "/<msp:webapp/>displayCommonAppCourtOrderUpdateSummary.do?submitAction=Process";

	theForm.action = forwardURL;
	theForm.submit();
} */

/* function showOffenseLevel(theForm)
{
	var forwardURL = "/<msp:webapp/>displayCommonAppCourtOrderUpdateSummary.do?submitAction=Filter";

	theForm.action = forwardURL;
	theForm.submit();
} */
/*Java script functions moved to jquery */
</script>
</head>
<%--END HEAD TAG--%>

<body topmargin='0' leftmargin="0">
<html:form action="/displayCommonAppCourtOrderUpdateSummary" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|454">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework" />&nbsp;-&nbsp;Close Casefile&nbsp;-&nbsp;<bean:message key="title.commonApp" />&nbsp;-&nbsp;Court Order</td>
	</tr>
</table>
<!-- END HEADING TABLE -->
<div class='spacer'></div>
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%">
	<tr>
		<td>
			<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">		
				<ul>
					<li>Select Submit button to view the Summary screen.</li>
				</ul>
			</logic:equal>	
		</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN HEADER INFO TABLE -->
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader" />
</tiles:insert>
<!-- BEGIN DETAIL TABLE -->
<div class='spacer'></div>
<%-- BEGIN FULL PAGE ALIGNMENT TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign='top'>
<!-- Begin Casefile App Tabs -->		
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="commonApp" />
				<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
			</tiles:insert>
<!-- End Casefile App Tabs -->	
<!-- Begin blue border tabs table -->			
			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign='top' align='center'>
					<div class='spacer'></div>
<!-- Begin Juvenile Details Tabs Table -->
						<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td valign='top'>
								  <!--tabs start--> 
								  	<tiles:insert page="../caseworkCommon/commonAppFormTabs.jsp" flush="true">
										<tiles:put name="tabid" value="JuvenileDetails" />
										<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
									</tiles:insert> 
									<!--tabs end-->
								</td>
							</tr>
							<tr>
								<td bgcolor='#ff6666'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
							</tr>
						</table>
<!-- End Juvenile Details Tabs Table -->									
<!-- Begin Red Tabs Border Table -->					
						<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
							<tr>
								<td valign='top' align='center'>
									<div class='spacer'></div>
<!-- Begin Court Order level Tabs -->												
									<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td valign='top'>
												<!--tabs start--> 
											  	<tiles:insert page="../caseworkCommon/commonAppJuvDetTabs.jsp"  flush="true">
													<tiles:put name="tabid" value="CourtOrder" />
													<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
												</tiles:insert> 
												<!--tabs end-->
											</td>
										</tr>
										<tr>
											<td bgcolor='#008080'><img src='/<msp:webapp/>images/spacer.gif' width='1'></td>
										</tr>
									</table>
<!-- End Court Order level Tabs -->												
<!-- Begin Turquoise Tabs border Table -->						
									<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableTurquoise">
										<tr>
											<td valign='top' align='center'>
												<div class='spacer'></div>
<!-- Begin Commitment input Table -->
												<table align="center" width="98%"' border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
													<tr class="detailHead">
														<td colspan='2'>TJJD Commitment</td>
													</tr>
													<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">  
														<tr>
															<td class='formDeLabel'><bean:message key="prompt.commitmentDate" /></td>
															<td class='formDe'>
																<!--<html:text name="commonAppForm" maxlength='10' size='10' property="courtOrder.commitmentDate" /> 
																	  <a href="#" onClick="cal1.select(document.forms[0]['courtOrder.commitmentDate'],'anchor1','MM/dd/yyyy'); return false;" name="anchor1" id="anchor1" border='0'>
																	<bean:message key="prompt.2.calendar" /></a>-->
																	<bean:write name="commonAppForm" property="courtOrder.commitmentDate" />
															</td>
														</tr>
														<tr>
															<td class='formDeLabel'>Court Name</td>
															<td class='formDe'>
															  <html:select name="commonAppForm" property="courtOrder.courtNameId" styleId="courtName">
																	<option value=""><bean:message key="select.generic" /></option>
																	<html:optionsCollection name="commonAppForm"  property="juvenileCourts" value="code" label="description" />
																</html:select>
																<html:hidden styleId="courtNameId" name="commonAppForm" property="courtOrder.courtNameId"/>
															</td>
														</tr>
														<tr>
															<td class='formDeLabel'>Judge's Name</td>
															<td class='formDe' id='judgeNameField'><span id="judgeName"><bean:write name="commonAppForm" property="courtOrder.judgeName"/></span></td>
														</tr>
																			<logic:notEmpty name="commonAppForm" property="controllingReferralFromClosing">
                 											<tr>
                       											<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.controllingReferral" /></td>
                       											<td class="formDe">
                       												<bean:write name="commonAppForm" property="controllingReferralFromClosing" />
                       												<html:hidden name="commonAppForm" property="selectedControllingReferral"/>
                       											</td>
                 											</tr>
														</logic:notEmpty>

														<logic:empty name="commonAppForm" property="controllingReferralFromClosing">
															<jims2:switch name="commonAppForm" property="caseStatus">
																<jims2:case value="A" />
															  	<jims2:case value="P" />
															  	<jims2:case value="R" />
         																
																<tr>
              														<td class="formDeLabel" nowrap='nowrap'>
              															<bean:message key="prompt.2.diamond" /><bean:message key="prompt.controllingReferral" />
              														</td>

																	<logic:empty name="commonAppForm" property="controllingReferrals">
																		<td class='formDe'>No Controlling Referral(s) available.
																		<%-- 23jul2009 - mjt - might be a useful message for the user. Commented out for now
																		<logic:notEqual name="commonAppForm" property="casefilePostAdjCommOrRes" value='true'>
																			(Check Supervision Category/Case Status).
																		</logic:notEqual>
 																		end commented out code --%>
																		</td>
																	</logic:empty>

																	<logic:notEmpty name="commonAppForm" property="controllingReferrals">
																		<td class='formDe'>
																		  	<html:select name="commonAppForm" property="selectedControllingReferral" styleId="selectedControllingReferral">
																				<option value=""><bean:message key="select.generic" /></option>
																				<html:optionsCollection name="commonAppForm" property="controllingReferrals" value="referralNumber" label="referralNumberWithSeverity" />
																			</html:select>
																		</td>
																	</logic:notEmpty>
						                    					</tr>
					                        				</jims2:switch>
														</logic:empty>
				                        										
												<!-- Changes for ER 11462 - Modified to offense code id and removed offense level id-->			
														<logic:notEmpty name="commonAppForm" property="currentOffenses">		
														<tr id="descCurrentOffenseRowId">
															<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.2.diamond" />Description of Current Offense</td>
															<td class='formDe'>
															    <html:select name="commonAppForm" property="courtOrder.currentOffenseId" styleId="currentOffenseId"> <!-- bug fix 26658 -->
																	<option value=""><bean:message key="select.generic" /></option>
																	<html:optionsCollection name="commonAppForm" property="currentOffenses" value="offenseCodeId" label="offenseDescription" />
																</html:select>
															</td>
														</tr>
														<tr id="offenseLevelRowId">
															<td class='formDeLabel'><bean:message key="prompt.offenseLevel" /></td>
															<td class='formDe'><bean:write name="commonAppForm" property="courtOrder.offenseLevel"/></td>
														</tr>
														</logic:notEmpty>
														<!-- selection of either radio button will show or hide the row that follows -->
														<tr>
															<html:hidden property="courtOrder.causeNumber" styleId="petitionNum"/>
															<td class='formDeLabel'>Cause Number</td>
															<td class='formDe'><html:text name="commonAppForm" maxlength='12' size='12' property="courtOrder.causeNumber" styleId="causeNum"/></td>
														</tr>
														<tr>
															<html:hidden property="courtOrder.prosecutingAttorneyName" styleId="prosecutingAttorneyNameHdn"/>
															<td class='formDeLabel' width='1%' nowrap='nowrap'>Prosecuting Attorney's Name</td>
															<td class='formDe'><html:text name="commonAppForm"	maxlength='20' size='20' property="courtOrder.prosecutingAttorneyName" styleId="prosecutingAttorneyName"/></td>
														</tr>
														<tr>
															<td class='formDeLabel'>Type of Commitment</td>
															<td class='formDe'>
															  <html:select property="courtOrder.typeOfCommitmentId" name="commonAppForm" styleId="typeOfCommitment">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																	<jims2:codetable codeTableName='TYPE_OF_COMMITMENT'></jims2:codetable>
																</html:select>
															</td>
														</tr>
									
														<tr>
															<td class='formDeLabel'>Probation Failure</td>
															<td class='formDe'>Yes <html:radio name="commonAppForm" title="cpf" property="courtOrder.probationFailure" styleId="probationFailureYes" value="true"/> No<html:radio name="commonAppForm" title="cpf" property="courtOrder.probationFailure" styleId="probationFailureNo" value="false"/></td>
														</tr>
														<tr class='hidden' id='failureReasonRow'>
															<td colspan='2'>
																<!-- Begin Failure details Table -->																		
																<table width='100%' cellspacing='1' cellpadding='2' class='borderTableGrey'>

																	<logic:empty name="commonAppForm" property="mostSeriousOffenses">
																		<tr>
																			<td class='formDeLabel' width='1%' nowrap='nowrap'>If yes, describe most serious offense</td>
																			<td class='formDe'>No Offenses available for this Juvenile.</td>
																		</tr>
																	</logic:empty>

																	<logic:notEmpty name="commonAppForm" property="mostSeriousOffenses">
																		<tr>
																			<td class='formDeLabel' colspan='2'><bean:message key="prompt.2.diamond" />If yes, describe most serious offense</td>
																		</tr>
																		<tr>
																			<td class='formDe' colspan='2'>
																			  	<html:select name="commonAppForm" property="courtOrder.mostSeriousOffenseId" styleId="mostSeriousOffenseId">
																					<option value=""><bean:message key="select.generic" /></option>
																					<html:optionsCollection name="commonAppForm" property="mostSeriousOffenses" value="offenseLevelId" label="offenseDescription" />
																					<%--  property="currentOffenses" value="investigationNum" label="offenseDescription" --%>
																					<%--  property="mostSeriousOffenses" value="referralNumber" label="referralNumberWithSeverity" --%>
																				</html:select>
																			</td>
																		</tr>
																	</logic:notEmpty>
	
																	<tr>
																		<td class='formDeLabel' width='1%' colspan='2' nowrap='nowrap'><bean:message key="prompt.2.diamond" />Reason for Failure &nbsp;
																			<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
																				<tiles:put name="tTextField" value="courtOrder.failureReason" />
																				<tiles:put name="tSpellCount" value="spellBtn1" />
																			</tiles:insert>
																			(Max. characters allowed: 255)
																		</td>
																	</tr>
																	<tr>
																		<td class='formDe' colspan='2'>
																			<html:textarea name="commonAppForm" property="courtOrder.failureReason" style="width:100%" rows="3" styleId="failureReason"/>
																		</td>
																	</tr>
										
																	<tr>
																		<td class='formDeLabel' width="50%"><bean:message key="prompt.offenseCode" /></td>
																		<td class='formDe'><bean:write name="commonAppForm" property="courtOrder.offenseCode" /></td>
																	</tr>
																</table>
																<!-- End Failure details Table -->																			
															</td>
														</tr>
											
														<tr>
															<td class='formDeLabel'><bean:message key="prompt.weaponUsed" /></td>
															<td class='formDe'>
																<html:select property="courtOrder.weaponTypeId" name="commonAppForm" styleId="weaponTypeId">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																	<jims2:codetable codeTableName='WEAPONS'></jims2:codetable>
																</html:select>
															</td>
														</tr>
														<tr>
															<td class='formDeLabel'><bean:message key="prompt.gangRelated" /></td>
															<td class='formDe'><span id="gangRelated"><bean:write name="commonAppForm" property="courtOrder.gangRelated"/></span></td>
														</tr>
			
													<!-- selection of either radio button will show or hide the row that follows -->
														<tr>
															<td class='formDeLabel'>
																<bean:message key="prompt.2.diamond" />Determinate Sentence</td>
															<td class='formDe'>Yes<html:radio name="commonAppForm" property="courtOrder.determinateSentence" styleId="determinateSentenceYes" value="true"/>No<html:radio name="commonAppForm" property="courtOrder.determinateSentence" styleId="determinateSentenceNo" value="false"/></td>
														</tr>
														<tr class='hidden' id='timeRow'>
															<td class='formDeLabel' nowrap><bean:message key="prompt.2.diamond" /><bean:message key="prompt.time" /></td>
															<td class='formDe'>
															  <html:select property="courtOrder.timeNumericId" name="commonAppForm" styleId="timeNumericId">
																	<option value=""><bean:message key="select.generic" /></option>
																	<html:optionsCollection name="commonAppForm" property="numericTime" value="code" label="description" />
																</html:select>

																<html:select property="courtOrder.detentionPeriodId" name="commonAppForm" styleId="detentionPeriodId">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																	<jims2:codetable codeTableName='DETENTION_PERIOD'></jims2:codetable>
																</html:select>
															</td>
														</tr>
														<tr valign='top'>
															<td colspan='2' class='formDeLabel'>Select one or more Locations for Time in Detention in Connection with this Offense</td>
														</tr>

														<logic:empty name="commonAppForm" property="detentionFacilities">
															<tr>
																<td colspan='4' class='formDe' align="left">No Detention Facilities available for this Juvenile.</td>
															</tr>
														</logic:empty>

														<logic:notEmpty name="commonAppForm" property="detentionFacilities">
															<tr valign='top'>
																<td colspan='2'>
																	<!-- Begin Detention Facility Table -->
																	<table cellpadding='2' cellspacing='1' width='100%' class='borderTableGrey'>
																		<tr bgcolor='#cccccc' class='subHead'>
																			<td>Include?</td>
																			<td>Referral</td>
																			<td><bean:message key="prompt.facilityName" /></td>
																			<td>Admit Date</td>
																			<td>Admit Time</td>
																			<td><bean:message key="prompt.releaseDate" /></td>
																			<td>Release Time</td>
																			<td>Total Time</td>
																		</tr>
	
																		<nested:iterate indexId="facIndexer" id="facilities" name="commonAppForm" property="detentionFacilities">
																			<tr class="<%out.print( (facIndexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
																				<bean:define id="answerValue" name="facilities" property="facilityName" />
																				<td><nested:multibox property="stayed" value="true" styleId="stayed" /></td>
																				<td><nested:write property="referralNumber" /></td>
																				<td><nested:write property="facilityName" /></td>
																				<td><nested:write property="admitDate" /></td>
																				<td><nested:write property="admitTime" /></td>
																				<td><nested:write property="releaseDate" /></td>
																				<td><nested:write property="releaseTime" /></td>
																				<td><nested:write property="totalTime" /></td>
																			</tr>
																		</nested:iterate>
																	</table>
																	<!-- End Detention Facility Table -->																				
																</td>
															</tr>
															<input type="hidden" name="clearDetentionFacilitiesCheckBoxes" value="" />
														</logic:notEmpty>
														<%-- ER CHANGES 13740 starts --%>
														<tr>
															<td class='formDeLabel' width='1%' nowrap='nowrap'><bean:message key="prompt.dateOfPriorTYCCommitment" /></td>
															<td class='formDe'>
															<logic:notEqual  name="commonAppForm" property="courtOrder.priorTJJDCommitmentDate" value="">
																<bean:write name="commonAppForm" property="courtOrder.priorTJJDCommitmentDate"/>
															</logic:notEqual>
															<logic:equal name="commonAppForm" property="courtOrder.priorTJJDCommitmentDate" value="">
        														<html:text name="commonAppForm" maxlength='10' size='10' property="courtOrder.priorTJJDCommitmentDate" styleId="priorTJJDCommitmentDate"/> 
             														<%--Removed as part of html 5 conversion.Using jquery calendar instead.
             														 <a href="#" onClick="cal1.select(document.forms[0]['courtOrder.priorTJJDCommitmentDate'],'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2" border='0'><bean:message key="prompt.2.calendar"/></a> --%>
             												</logic:equal>
             												</td>
														</tr>
														<%-- ER CHANGES 13740 ends --%>
														<input type="hidden" name="inputAllowed" value="" id="inputAllowedId" >
													</logic:equal>
													<logic:notEqual name="juvenileCasefileForm" property="juvUnder21" value="true">
														<tr>
															<td class="subhead"> Juvenile is 21 or more years old - no updating is allowed.</td>
														</tr>	
													</logic:notEqual>
												</table>
<!-- End Commitment input Table -->															
												<div class='spacer'></div>
											</td>
										</tr>
									</table>
<!-- End Turquoise Tabs border Table -->												
									<div class='spacer'></div>
<!-- BEGIN BUTTON TABLE -->
									<table width="100%">
										<tr>
											<td align="center">
												<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
											  		<html:submit property="submitAction" styleId="submitBtn"><bean:message key="button.submit" /></html:submit> 
													<input type='button' value='Reset' name='reset' id="reset">
												</logic:equal>	
												<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
											</td>
										</tr>
									</table>
									<div class='spacer'></div>
<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table>
<!-- End Red Tabs Border Table -->								
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
<!-- End blue border tabs table -->							
			<div class='spacer'></div>
		</td>
	</tr>
</table>
<!-- END DETAIL TABLE -->
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</html:form>
</body>
</html:html>