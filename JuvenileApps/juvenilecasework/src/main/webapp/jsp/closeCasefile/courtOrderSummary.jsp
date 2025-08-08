<!DOCTYPE HTML>
<%-- Used in closing of a casefile --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2007		UGopinath	Created JSP --%>
<%-- 05/01/2012		C Shimek	#73346 Revise hardcoded TYC prompts to TJJD --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims"%>
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
<title><bean:message key="title.heading" /> - courtOrderSummary.jsp</title>
<%-- Javascript for emulated navigation --%>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/app.js"></script>
<script>
$(document).ready(function(){
	$("#finishBtn").click(function(){
		console.log("Click");
		sessionStorage.removeItem("determinateSentence");
	})
	
})
</script>

</head>

<body topmargin='0' leftmargin="0">
<html:form action="/submitCommonAppCourtOrder" target="content">
	<logic:equal name="commonAppForm" property="action" value="summary">
		<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|129">
	</logic:equal>
	<logic:equal name="commonAppForm" property="action" value="confirm">
		<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|131">
	</logic:equal>

	<!-- BEGIN HEADING TABLE -->
	<table width='100%'>
		<tr>
			<td align="center" class="header">Juvenile Casework - Close Casefile - Common App - Court Order 
				<logic:equal name="commonAppForm" property="action" value="summary">
	  				<bean:message key="prompt.summary"/>
	  			</logic:equal>
	  			<logic:equal name="commonAppForm" property="action" value="confirm">
	  				<bean:message key="prompt.confirmation"/>
	  			</logic:equal>
  			</td>
		</tr>

		<logic:equal name="commonAppForm" property="action" value="confirm">
			<tr>
				<td class='confirm'>Court Order information has been saved.</td>
			</tr>
		</logic:equal>
	</table>
	<!-- END HEADING TABLE -->

	<!-- BEGIN INSTRUCTION TABLE -->
	<table width="98%">
		<logic:equal name="commonAppForm" property="action" value="summary">
			<tr>
				<td>
				<ul>
					<li>Select Back button to make changes.</li>
					<li>Select Finish button to save information.</li>
				</ul>
				</td>
			</tr>
		</logic:equal>
		<logic:equal name="commonAppForm" property="action" value="confirm">
			<tr>
				<td>
				<ul>
					<li>Select Return to Court Order button to return to the Court Order page.</li>
				</ul>
				</td>
			</tr>
		</logic:equal>
	</table>
	<!-- END INSTRUCTION TABLE -->


	<!-- BEGIN HEADER INFO TABLE -->
	<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
		<tiles:put name="headerType" value="casefileheader" />
	</tiles:insert>
	<!-- BEGIN DETAIL TABLE -->

	<!-- Begin Casefile App Tabs -->
	<div class='spacer'></div>
	<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign='top'>
				<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
					<tiles:put name="tabid" value="closing" />
					<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
				</tiles:insert>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td valign='top' align='center'><!-- Begin Common App Tabs -->
					<div class='spacer'></div>
					<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign='top'>
							<table width='100%' border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td valign='top'><!--tabs start-->
										<tiles:insert page="../caseworkCommon/commonAppFormTabs.jsp" flush="true">
											<tiles:put name="tabid" value="JuvenileDetails" />
											<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
										</tiles:insert> <!--tabs end-->
									</td>
								</tr>
								<tr>
									<td bgcolor='#ff6666'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
								</tr>
							</table>

							<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
								<tr>
									<td valign='top' align='center'><!-- Begin Common App Detail Tabs -->
									<div class='spacer'></div>
									<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td valign='top'>
											<table width='100%' border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td valign='top'><!--tabs start-->
														<tiles:insert page="../caseworkCommon/commonAppJuvDetTabs.jsp" flush="true">
															<tiles:put name="tabid" value="CourtOrder" />
															<tiles:put name="casefileid" value='<%=request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM)%>' />
														</tiles:insert> <!--tabs end-->
													</td>
												</tr>
												<tr>
													<td bgcolor='#008080'><img src='/<msp:webapp/>images/spacer.gif' width='5'></td>
												</tr>
											</table>

											<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableTurquoise">
												<tr>
													<td valign='top' align='center'><!-- BEGIN Search Results Summary TABLE -->
													<div class='spacer'></div>
													<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
														<tr>
															<td valign='top' align='center'><!-- BEGIN Current Constellation TABLE -->
															<table width='100%' border="0" cellpadding="4" cellspacing="1">
																<tr class="detailHead">
																	<td colspan='2'>TJJD Commitment</td>
																</tr>
																<tr>
																	<td class='formDeLabel'>Commitment Date</td>
																	<td class='formDe'><bean:write name="commonAppForm" property="courtOrder.commitmentDate" formatKey="date.format.mmddyyyy" /></td>
																</tr>
																<tr>
																	<td class='formDeLabel'>Court Name</td>
																	<td class='formDe'><bean:write name="commonAppForm" property="courtOrder.courtName" /></td>
																</tr>
																<tr>
																	<td class='formDeLabel'>Judge's Name</td>
																	<td class='formDe'><bean:write name="commonAppForm" property="courtOrder.judgeName" /></td>
																</tr>
																<jims:switch name="commonAppForm" property="caseStatus">
																	<jims:case value="A" />
																	<jims:case value="P" />
																	<jims:case value="R" />
																	<tr>
																		<td class="formDeLabel"><bean:message key="prompt.controllingReferral" /></td>

																		<logic:empty name="commonAppForm" property="selectedControllingReferral">
																			<td class='formDe'>No Controlling Referral available.</td>
																		</logic:empty>

																		<logic:notEmpty name="commonAppForm" property="selectedControllingReferral">
																			<td class='formDe'><bean:write name="commonAppForm" property="selectedControllingReferral" /></td>
																		</logic:notEmpty>
																	</tr>
																</jims:switch>

																<tr>
																	<td class='formDeLabel' width='1%' nowrap='nowrap'>Description of Current Offense</td>
																	<td class='formDe'><bean:write name="commonAppForm" property="courtOrder.currentOffense" /></td>
																</tr>
																<tr>
																	<td class='formDeLabel'><bean:message key="prompt.offenseLevel" /></td>
																	<td class='formDe'><bean:write name="commonAppForm" property="courtOrder.offenseLevel" /></td>
																</tr>
																
																
																<tr>
																	<td class='formDeLabel'>Cause Number</td>
																	<td class='formDe'><bean:write name="commonAppForm" property="courtOrder.causeNumber" /></td>
																</tr>
																<tr>
																	<td class='formDeLabel'>Prosecuting Attorney's Name</td>
																	<td class='formDe'><bean:write name="commonAppForm" property="courtOrder.prosecutingAttorneyName" /></td>
																</tr>
																<!--END Current Constellation TABLE -->
																<tr>
																	<td class='formDeLabel'>Type of Commitment</td>
																	<td class='formDe'><bean:write name="commonAppForm" property="courtOrder.typeOfCommitment" /></td>
																</tr>
															
																<tr>
																	<td class='formDeLabel'>Probation Failure</td>
																	<td class='formDe'><jims2:if name="commonAppForm" property="courtOrder.probationFailure" value="true" op="equal">
																		<jims2:then>Yes</jims2:then>
																		<jims2:else>No</jims2:else>
																	</jims2:if></td>
																</tr>

																<logic:equal name="commonAppForm" property="courtOrder.probationFailure" value="true">
																	<tr>
																		<td colspan='2'>
																		<table width='100%' cellspacing='1' cellpadding='1' class='borderTableGrey'>
																			<tr>
																				<td class='formDeLabel' width='1%' nowrap='nowrap'>If yes, describe most serious offense</td>
																				<td class='formDe'><bean:write name="commonAppForm" property="courtOrder.mostSeriousOffense" /></td>
																			</tr>
																			<tr>
																				<td class='formDeLabel' colspan='2'>Reason for Failure</td>
																			</tr>
																			<tr>
																				<td class='formDe' colspan='2'><bean:write name="commonAppForm" property="courtOrder.failureReason" /></td>
																			</tr>
																			<tr>
																				<td class='formDeLabel'  width="10%" >Offense Code</td>
																				<td class='formDe'><bean:write name="commonAppForm" property="courtOrder.offenseCode" /></td>
																			</tr>
																		</table>
																		</td>
																	</tr>
																</logic:equal>

																<tr>
																	<td class='formDeLabel'>Weapon Used</td>
																	<td class='formDe'><bean:write name="commonAppForm" property="courtOrder.weaponType" /></td>
																</tr>
																<tr>
																	<td class='formDeLabel'>Gang Related</td>
																	<td class='formDe'><bean:write name="commonAppForm" property="courtOrder.gangRelated" /></td>
																</tr>

																<tr>
																	<td class='formDeLabel'>Determinate Sentence</td>
																	<td class='formDe'><jims2:if name="commonAppForm" property="courtOrder.determinateSentence" value="true" op="equal">
																		<jims2:then>Yes</jims2:then>
																		<jims2:else>No</jims2:else>
																	</jims2:if></td>
																</tr>
																<logic:equal name="commonAppForm" property="courtOrder.determinateSentence" value="true">
																	<tr>
																		<td class='formDeLabel'>Time</td>
																		<td class='formDe'><bean:write name="commonAppForm" property="courtOrder.timeStr" /></td>
																	</tr>
																</logic:equal>

																<tr>
																	<td class='formDeLabel' colspan="2">Locations for Time in Detention in Connection with this Offense</td>
																</tr>
																<tr valign='top'>
																	<td colspan='2'>
																	<table cellpadding='2' cellspacing='1' width='100%' class='borderTableGrey'>

																		<logic:empty name="commonAppForm" property="courtOrder.selectedFacilities">
																			<tr class='formDe'>
																				<td>No Locations included.</td>
																			</tr>
																		</logic:empty>

																		<logic:notEmpty name="commonAppForm" property="courtOrder.selectedFacilities">
																			<tr bgcolor='#cccccc' class='subHead'>
																				<td><bean:message key="prompt.referral" /></td>
																				<td>Facility Name</td>
																				<td>Admit Date</td>
																				<td>Admit Time</td>
																				<td>Release Date</td>
																				<td>Release Time</td>
																				<td>Total Time</td>
																			</tr>
																			<logic:iterate id="facilities" name="commonAppForm" property="courtOrder.selectedFacilities" indexId="index">
																				<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow": "normalRow");%>">
																					<td><bean:write name="facilities" property="referralNumber" /></td>
																					<td><bean:write name="facilities" property="facilityName" /></td>
																					<td><bean:write name="facilities" property="admitDate" /></td>
																					<td><bean:write name="facilities" property="admitTime" /></td>
																					<td><bean:write name="facilities" property="releaseDate" /></td>
																					<td><bean:write name="facilities" property="releaseTime" /></td>
																					<td><bean:write name="facilities" property="totalTime" /></td>
																				</tr>
																			</logic:iterate>
																		</logic:notEmpty>
																	</table>
																	</td>
																</tr>
																<tr>
																	<td class='formDeLabel'>Time in Detention in Connection with this Offense</td>
																	<td class='formDe'><bean:write name="commonAppForm" property="courtOrder.detentionTime" /></td>
																</tr>
																<tr>
																	<td class='formDeLabel' nowrap='nowrap'>Date of Prior TJJD Commitment</td>
																	<td class='formDe'><bean:write name="commonAppForm" property="courtOrder.priorTJJDCommitmentDate" formatKey="date.format.mmddyyyy" /></td>
																</tr>
															</table>
															</td>
														</tr>
													</table>
													<!-- BEGIN BUTTON TABLE -->
													<table width="100%">
														<logic:notEqual name="commonAppForm" property="action" value="confirm">
															<tr>
																<td align="center">
																	<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
																	<html:submit styleId="finishBtn" property="submitAction"><bean:message key="button.finish" /></html:submit>
																	<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
																</td>
															</tr>
														</logic:notEqual>
														<logic:equal name="commonAppForm" property="action" value="confirm">
															<tr>
																<td align="center">
																	<html:submit property="submitAction"><bean:message key="button.returnToCourtOrder"/></html:submit>
																</td>
															</tr>
														</logic:equal>
													</table>
													<div class='spacer'></div>
													<!-- END BUTTON TABLE --></td>
												</tr>
											</table>
											<div class='spacer'></div>
											<!-- End Common App Detail Tabs --></td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							<div class='spacer'></div>
							<!-- End Common App Tabs --></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<!-- End Casefile App Tabs -->
	<!-- END DETAIL TABLE -->
</html:form>
<div align='center'>
	<script type="text/javascript">renderBackToTop()</script>
</div>
</body>
</html:html>