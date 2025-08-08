<%-- 04/26/2011 Debbie Williamson	Create JSP--%>
<%-- 08/05/2011 C Shimek			Revise Custody to TDPRS to Custody to TDFPS per email request from R. Wilder --%>
<%-- 04/30/2012 C Shimek			#73346 Revise hardcoded TYC prompts to TJJD --%>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>


<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<head>
<title>manageJuvCaseWork/caseTabAssignedReferrals/legacyCourtOrderDetails.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
</head>

<body topmargin="0" leftmargin="0">
<!-- BEGIN HEADING TABLE -->
<table width='100%'>
	<tr>
		<td align="center" class="header">
			<bean:message key="title.juvenileCasework"/>&nbsp;-&nbsp;<bean:message key="prompt.court"/>
            <bean:message key="prompt.order"/>&nbsp;<bean:message key="title.details"/>
		</td>
	</tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<!-- END ERROR TABLE -->
<br>
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select Close Window button to close this window.</li>
			</ul>
		</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN DETAILS ALIGNMENT TABLE -->
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
<!-- BEGIN DISPOSITION INFO -->
  			<table align="center" width='100%' border="0" cellpadding="1" cellspacing="0" class="borderTableBlue">
  				<tr class='detailHead'>
					<td width='1%'>
						<a href="javascript:showHideMulti('dispositionInfo', 'dispositionInfoSpan', '2', '/<msp:webapp/>')" border="0"><img border="0" src="/<msp:webapp/>images/contract.gif" name="dispositionInfo"></a>&nbsp;
						<bean:message key="prompt.disposition"/>&nbsp;<bean:message key="prompt.information"/>
					</td>
				</tr>

				<tr id="dispositionInfoSpan0" class="visibleTR">
					<td colspan="2">
						<table width="100%" cellpadding="2" cellspacing="1" >
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.petitionNumber"/></td>
								<td valign="top" class="formDe"><bean:write name="legacyCourtOrdersForm" property="petitionNum" /></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.hearingType"/></td>
								<td valign="top" class="formDe"><bean:write name="legacyCourtOrdersForm" property="hearingTypeDescription" /></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.court"/></td>
								<td valign="top" class="formDe"><bean:write name="legacyCourtOrdersForm" property="juvenileCourt" /></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.court"/>&nbsp;<bean:message key="prompt.date"/></td>
								<td valign="top" class="formDe"><bean:write name="legacyCourtOrdersForm" property="courtDate" formatKey="date.format.mmddyyyy" /></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.state"/>&nbsp;Attorney</td>
								<td valign="top" class="formDe" width="1%" nowrap="nowrap"><bean:write name="legacyCourtOrdersForm" property="stateAttorneyName" /></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.respondentAttorney"/></td>
								<td valign="top" class="formDe"width="1%" nowrap="nowrap"><bean:write name="legacyCourtOrdersForm" property="respondentAttorneyName" /></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.dispositionDate"/></td>
								<td valign="top" class="formDe"><bean:write name="legacyCourtOrdersForm" property="dispositionDate" formatKey="date.format.mmddyyyy" /></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.judgement"/>&nbsp;<bean:message key="prompt.date"/></td>
								<td valign="top" class="formDe"><bean:write name="legacyCourtOrdersForm" property="judgementDate" formatKey="date.format.mmddyyyy" /></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" width="1%" nowrap="nowrap">Probation/Sentencing Begins</td>
								<td valign="top" class="formDe"><bean:write name="legacyCourtOrdersForm" property="probationBeginDate" formatKey="date.format.mmddyyyy" /></td>
								<td valign="top" class="formDeLabel" nowrap width="1%">Probation/Sentencing Ends</td>
								<td valign="top" class="formDe"><bean:write name="legacyCourtOrdersForm" property="probationEndDate" formatKey="date.format.mmddyyyy" /></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.judgement"/>&nbsp;<bean:message key="prompt.date"/>&nbsp;(TJJD)</td>
								<td valign="top" class="formDe"><bean:write name="legacyCourtOrdersForm" property="judgementTYCDate" formatKey="date.format.mmddyyyy" /></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.commitmentDate"/></td>
								<td valign="top" class="formDe"><bean:write name="legacyCourtOrdersForm" property="commitmentDate" formatKey="date.format.mmddyyyy" /></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">GJ&nbsp;<bean:message key="prompt.number"/></td>
								<td valign="top" class="formDe"><bean:write name="legacyCourtOrdersForm" property="gjNumber" /></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">GJ Approval&nbsp;<bean:message key="prompt.date"/></td>
								<td valign="top" class="formDe"><bean:write name="legacyCourtOrdersForm" property="gjApprovalDate" formatKey="date.format.mmddyyyy" /></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">GJ Waiver&nbsp;<bean:message key="prompt.date"/></td>
								<td valign="top" class="formDe"><bean:write name="legacyCourtOrdersForm" property="gjWaiverDate" formatKey="date.format.mmddyyyy" /></td>
								<td valign="top" class="formDe" colspan="2"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
<!-- END DISPOSITION INFO -->
			<div class="spacer4px"></div>
<!-- BEGIN ADJUDICATION INFO -->
			<table align="center" width="100%" border="0" cellpadding="1" cellspacing="0" class="borderTableBlue">
	  			<tr class="detailHead">
					<td width="1%"><a href="javascript:showHideMulti('adjudicatedOffenseInfo', 'adjudicatedOffenseInfoSpan', '2', '/<msp:webapp/>')" border="0"><img border="0" src="/<msp:webapp/>images/expand.gif" name="adjudicatedOffenseInfo"></a>&nbsp;
					Adjudicated&nbsp;<bean:message key="prompt.offense"/></td>
				</tr>
				<tr id="adjudicatedOffenseInfoSpan0" class="hidden">
					<td>
						<table width="100%" cellpadding="2" cellspacing="1" >
							<tr>
								<td valign="top" class="formDeLabel"><bean:message key="prompt.offense"/></td>
								<td valign="top" class="formDe" ><bean:write name="legacyCourtOrdersForm" property="juvenileOffenseCodeDescription"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" width="25%" nowrap="nowrap"><bean:message key="prompt.offenseDate"/></td>
								<td valign="top" class="formDe" width="75%"><bean:write name="legacyCourtOrdersForm" property="offenseDate" formatKey="date.format.mmddyyyy" /></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" ><bean:message key="prompt.degree"/></td>
								<td valign="top" class="formDe"><bean:write name="legacyCourtOrdersForm" property="degree"/></td>
							</tr>
						</table>
					</td>
				</tr>
			</table> 
<!-- END ADJUDICATION INFO -->
			<div class="spacer4px"></div>
<!-- BEGIN AFFIRMATIVE FINDINGS -->
			<table align="center" width="100%" border="0" cellpadding="1" cellspacing="0" class="borderTableBlue">
				<tr class="detailHead">
				    <td width="1%"><a href="javascript:showHideMulti('judgementInfo', 'judgementInfoSpan', '2', '/<msp:webapp/>')" border="0"><img border="0" src="/<msp:webapp/>images/expand.gif" name="judgementInfo"></a>&nbsp;
	  				Judgement</td>
	  			</tr>
				<tr id="judgementInfoSpan0" class="hidden">
					<td colspan="2">
						<table width="100%" cellpadding="2" cellspacing="1">
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Beyond Reasonable Doubt </td>
								<td valign="top" class="formDe" ><jims:displayBoolean name="legacyCourtOrdersForm" property="gjBeyondReasonableDoubt" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap"></td>
								<td valign="top" class="formDe"></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Child's Age at Hearing</td>
								<td valign="top" class="formDe" ><bean:write name="legacyCourtOrdersForm" property="gjChildsAge"/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Date of Birth</td>
								<td valign="top" class="formDe"><bean:write name="legacyCourtOrdersForm" property="birthDate" formatKey="date.format.mmddyyyy" /></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Deadly Weapon</td>
								<td valign="top" class="formDe" ><bean:write name="legacyCourtOrdersForm" property="deadlyWeaponDesc"/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Delinquent Conduct</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="delinquentConduct" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" width="27%"nowrap="nowrap">GJ Approved/Juvenile Waived</td>
								<td valign="top" class="formDe" width="23%"><jims:displayBoolean name="legacyCourtOrdersForm" property="gjApproved" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" width="25%" nowrap="nowrap">GJ Delinquent Conduct</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="gjDelinquentConduct" trueValue="Yes" falseValue=""/></td>							
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">In Need of Rehabilitation</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="inNeedOfRehab" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">In Need of Supervision</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="inNeedOfSupervision" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">No Delinquent Conduct</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="noDelinquentConduct" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">No Disposition Necessary</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="noDispositionNecessary" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Placement Outside Home</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="placementOutsideHome" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Probation Extended</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="probationExtended" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Probation Revoked</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="probationRevoked" trueValue="Yes" falseValue=""/></td>						
								<td valign="top" class="formDeLabel" nowrap="nowrap">Reasonable Efforts Made</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="reasonableEffortsMade" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">State Grand Jury Referral</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="gjStateReferral" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Title III Child</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="titleIIIChild" trueValue="Yes" falseValue=""/></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
<!-- END AFFIRMATIVE FINDINGS  -->
			<div class="spacer4px"></div>
<!-- BEGIN DISPOSITION AND PLACEMENT -->
			<table align="center" width="100%" border="0" cellpadding="1" cellspacing="0" class="borderTableBlue">
				<tr class="detailHead">
				    <td width="1%"><a href="javascript:showHideMulti('placementInfo', 'placementInfoSpan', '2', '/<msp:webapp/>')" border="0"><img border="0" src="/<msp:webapp/>images/expand.gif" name="placementInfo"></a>&nbsp;
	  				Disposition and Placement</td>
	  			</tr>
				<tr id="placementInfoSpan0" class="hidden">
					<td colspan="2">
						<table width="100%" cellpadding="2" cellspacing="1" >
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Committed to TJJD</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="committedToTYC" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Custody to Father</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="custodyToFather" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Custody to Guardian</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="custodyToGuardian" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel"nowrap="nowrap">Custody to Mother</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="custodyToMother" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Custody to MHMRA</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="custodyToMHMRA" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Custody to Other</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="custodyToOther" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Custody to Parents</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="custodyToParents" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Custody to Relative</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="custodyToRelative" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Custody to TDFPS</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="custodyToTDPRS" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Joint Custody</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="jointCustody" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">ISP</td>
								<td valign="top" class="formDe" colspan="3"><jims:displayBoolean name="legacyCourtOrdersForm" property="intensiveSupervisionProgram" trueValue="Yes" falseValue=""/></td>
							</tr>	
							<tr>	
								<td valign="top" class="formDeLabel" nowrap="nowrap">ISP Time Frame</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="intensiveSupervisionProgramTimeFrame"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" width="24%" nowrap="nowrap">Previous Rules In Effect</td>
								<td valign="top" class="formDe" width="26.5%"><jims:displayBoolean name="legacyCourtOrdersForm" property="previousRulesInEffect" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" width="26%" nowrap="nowrap">Prior C.S./Restitution Ended</td>
								<td valign="top" class="formDe" width="23.5%"><jims:displayBoolean name="legacyCourtOrdersForm" property="priorRestitutionEnded" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Violated Court Orders</td>
								<td valign="top" class="formDe" colspan="3"><jims:displayBoolean name="legacyCourtOrdersForm" property="violatedCourtOrders" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Custody to CJPO</td>
								<td valign="top" class="formDe" colspan="3"><jims:displayBoolean name="legacyCourtOrdersForm" property="custodyToCJPO" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">CJPO Referral</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="cjpoInstructions"/></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
<!-- END DISPOSITION AND PLACEMENT  -->		
			<div class="spacer4px"></div>
<!-- BEGIN GUARDIANS INFORMATION -->
			<table align="center" width="100%" border="0" cellpadding="1" cellspacing="0" class="borderTableBlue">
	  			<tr class="detailHead">
				    <td width="1%"><a href="javascript:showHideMulti('guardianInfo', 'guardianInfoSpan', '2', '/<msp:webapp/>')" border="0"><img border="0" src="/<msp:webapp/>images/expand.gif" name="guardianInfo"></a>&nbsp;
	  				Court Ordered Parents/Guardians/Custodians</td>
	  			</tr>
				<tr id="guardianInfoSpan0" class="hidden">
					<td colspan="2">
						<table width="100%" cellpadding="2" cellspacing="1" >
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Child Removed From</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="childRemovedFrom"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" width="25%" nowrap="nowrap">Parent/Guardian/Custodian</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="parentGuardianCustodian"/></td>
							</tr>
						</table>
					</td>
				</tr>
	  		</table>
<!-- END GUARDIANS INFORMATION -->
			<div class="spacer4px"></div>
<!-- BEGIN SPECIAL RULES -->
			<table align="center" width="100%" border="0" cellpadding="1" cellspacing="0" class="borderTableBlue">
				<tr class="detailHead">
				    <td width="1%"><a href="javascript:showHideMulti('specialRulesInfo', 'specialRulesInfoSpan', '2', '/<msp:webapp/>')" border="0"><img border="0" src="/<msp:webapp/>images/expand.gif" name="specialRulesInfo"></a>&nbsp;
	  				Respondent ordered to participate in the following</td>
	  			</tr>
				<tr id="specialRulesInfoSpan0" class="hidden">
					<td colspan="2">
						<table width="100%" cellpadding="2" cellspacing="1" >
							<tr>
								<td valign="top" class="formDeLabel" width="25%" nowrap="nowrap">Anger Mgmt Counseling</td>
								<td valign="top" class="formDe" width="25%" ><jims:displayBoolean name="legacyCourtOrdersForm" property="angerManagementCounseling" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" width="27%" nowrap="nowrap">Any other rules set by HCJPD</td>
								<td valign="top" class="formDe" width="23%"><jims:displayBoolean name="legacyCourtOrdersForm" property="anyOtherRulesSetByHCJPD" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Drug Free Youth</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="drugFreeYouth" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Family Mgmt Counseling</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="familyManagementCounseling" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">FireSetters</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="fireSetters" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Gang Workshop</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="gangWorkshop" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">GED Program</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="gedProgram" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Individual Mgmt Counseling</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="individualManagementCounseling" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">MHMRA Assessment</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="mhmraAssessment" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Peer Pressure</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="peerPressure" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Sex Offender Counseling</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="sexOffenderCounseling" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">TDC Outreach</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="tdcOutreach" trueValue="Yes" falseValue=""/></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
<!-- END SPECIAL RULES  -->
			<div class="spacer4px"></div>
<!-- BEGIN SPECIAL INSTRUCTIONS  -->
			<table align="center" width="100%" border="0" cellpadding="1" cellspacing="0" class="borderTableBlue">
				<tr class="detailHead">
				    <td width="1%"><a href="javascript:showHideMulti('specialInstructionsInfo', 'specialInstructionsInfoSpan', '2', '/<msp:webapp/>')" border="0"><img border="0" src="/<msp:webapp/>images/expand.gif" name="specialInstructionsInfo"></a>&nbsp;
	  				Special Instructions</td>
	  			</tr>
				<tr id="specialInstructionsInfoSpan0" class="hidden">
					<td colspan="2">
						<table width="100%" cellpadding="2" cellspacing="1" >
							<tr>
								<td valign="top" class="formDeLabel" width="25%" nowrap="nowrap">C Avg S Conduct</td>
								<td valign="top" class="formDe" colspan="3"><jims:displayBoolean name="legacyCourtOrdersForm" property="cavgsConduct" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" width="25%" nowrap="nowrap">Community Service</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="communityService"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" width="25%" nowrap="nowrap">Deferred S.O. Registration</td>
								<td valign="top" class="formDe" width="25%"><jims:displayBoolean name="legacyCourtOrdersForm" property="deferredSORegistration" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" width="25%" nowrap="nowrap">Educational Specialist</td>
								<td valign="top" class="formDe" width="25%"><jims:displayBoolean name="legacyCourtOrdersForm" property="educationalSpecialist" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Gang Caseload</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="gangCaseload" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Juvenile Probation Placement</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="juvenileProbationPlacement" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Letter of Apology</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="letterOfApology" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Mentor</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="mentor" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">No Contact - CoActors</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="noContactCoActors" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">No Contact - Complainant</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="noContactComplainant" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">No Contact - Gang Members</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="noContactGangMembers" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Random Drug Screens</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="randomDrugScreens" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Register as Sex Offender (Non-Public)</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="sexOffenderRegistrationNonPublic" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Sex Offender Blood Sample</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="sexOffenderBloodSample" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Register as Sex Offender (Public)</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="sexOffenderRegistration" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Sex Offender Polygraph</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="sexOffenderPolygraph" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">TX DL Restrictions</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="txDLRestrictions"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Deny TDL</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="denyTDL" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Thumbprint Ordered</td>
								<td valign="top" class="formDe"><jims:displayBoolean name="legacyCourtOrdersForm" property="thumbprintOrdered" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Weekday curfew times</td>
								<td valign="top" class="formDe"><bean:write name="legacyCourtOrdersForm" property="weekdayCurfewTimes"/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Weekend curfew hours</td>
								<td valign="top" class="formDe"><bean:write name="legacyCourtOrdersForm" property="weekendCurfewHours"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" width="25%" nowrap="nowrap">Other Special Rules - Ordered</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="otherRulesSetByHCJPD"/></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
<!-- END SPECIAL INSTRUCTIONS -->
			<div class="spacer4px"></div>
<!-- BEGIN FEE INFORMATION -->
			<table align="center" width="100%" border="0" cellpadding="1" cellspacing="0" class="borderTableBlue">
	  			<tr class="detailHead">
				    <td width="1%"><a href="javascript:showHideMulti('feeInfo', 'feeInfoSpan', '2', '/<msp:webapp/>')" border="0"><img border="0" src="/<msp:webapp/>images/expand.gif" name="feeInfo"></a>&nbsp;
	  				Fees</td>
	  			</tr>
				<tr id="feeInfoSpan0" class="hidden">
					<td colspan="2">
						<table width="100%" cellpadding="2" cellspacing="1" >
							<tr>
								<td valign="top" class="formDeLabel" width="25%" nowrap="nowrap">Attorney Fee</td>
								<td valign="top" class="formDe"><bean:write name="legacyCourtOrdersForm" property="attorneyFee"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" width="25%" nowrap="nowrap">Responsible Party</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="responsiblePartyAttorneyFee"/></td>
							</tr>
							<tr><td class="spacer"></td></tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Child Support</td>
								<td valign="top" class="formDe"><bean:write name="legacyCourtOrdersForm" property="childSupport"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Responsible Party</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="responsiblePartyChildSupport"/></td>
							</tr>
							<tr><td class="spacer"></td></tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Court Costs</td>
								<td valign="top" class="formDe"><bean:write name="legacyCourtOrdersForm" property="courtCost"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Responsible Party</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="responsiblePartyCourtCost"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Court Costs Waived</td>
								<td valign="top" class="formDe" colspan="3"><jims:displayBoolean name="legacyCourtOrdersForm" property="courtCostWaived" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr><td class="spacer4px"></td></tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Supervisory Fee</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="supervisoryFee"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Responsible Party</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="responsiblePartySupervisoryFee"/></td>
							</tr>
						</table>
					</td>
				</tr>
	  		</table>
<!-- END FEE INFORMATION  -->
			<div class="spacer4px"></div>
<!-- BEGIN RESTITUTION  -->
			<table align="center" width="100%" border="0" cellpadding="1" cellspacing="0" class="borderTableBlue">
				<tr class="detailHead">
				    <td width="1%"><a href="javascript:showHideMulti('restitutionInfo', 'restitutionInfoSpan', '2', '/<msp:webapp/>')" border="0"><img border="0" src="/<msp:webapp/>images/expand.gif" name="restitutionInfo"></a>&nbsp;
	  				Restitution</td>
	  			</tr>
				<tr id="restitutionInfoSpan0" class="hidden">
					<td colspan="2">
						<table width="100%" cellpadding="2" cellspacing="1" >
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Restitution Total</td>
								<td valign="top" class="formDe" colspan="3"><bean:message key="currency.prefix"/><bean:write name="legacyCourtOrdersForm" property="restitutionTotal"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" width='25%' nowrap="nowrap">Responsible Party</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="responsiblePartyRestitution"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Start Date</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="restitutionStartDate" formatKey="date.format.mmddyyyy"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Amount Ordered</td>
								<td valign="top" class="formDe" colspan="3"><bean:message key="currency.prefix"/><bean:write name="legacyCourtOrdersForm" property="restitutionAmountOrdered"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Payment Time Frame</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="restitutionPaymentTimeFrame"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Payee</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="restitutionPayeeFirstName"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Payee Last Name</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="restitutionPayeeLastName"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Street Address</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="restitutionPayeeStreetAddress"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">City</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="restitutionPayeeCity"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">State</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="restitutionPayeeState"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">ZIP Code</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="restitutionPayeeZipCode"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Phone</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="restitutionPayeePhoneNum"/></td>
							</tr>
							<tr><td class="spacer4px"></td></tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Restitution Total(2)</td>
								<td valign="top" class="formDe" colspan="3"><bean:message key="currency.prefix"/><bean:write name="legacyCourtOrdersForm" property="restitutionTotal2"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Responsible Party(2)</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="responsiblePartyRestitution2"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Start Date(2)</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="restitutionStartDate2" formatKey="date.format.mmddyyyy"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Amount Ordered(2)</td>
								<td valign="top" class="formDe" colspan="3"><bean:message key="currency.prefix"/><bean:write name="legacyCourtOrdersForm" property="restitutionAmountOrdered2"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Payment Time Frame(2)</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="restitutionPaymentTimeFrame2"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Payee(2)</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="restitutionPayeeFirstName2"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Payee Last Name(2)</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="restitutionPayeeLastName2"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Street Address(2)</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="restitutionPayeeStreetAddress2"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">City(2)</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="restitutionPayeeCity2"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">State(2)</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="restitutionPayeeState2"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">ZIP Code(2)</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="restitutionPayeeZipCode2"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Phone(2)</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="restitutionPayeePhoneNum2"/></td>
							</tr>
	
						</table>
					</td>
				</tr>
			</table>
<!-- END RESTITUTION -->
			<div class="spacer4px"></div>
<!-- BEGIN DECLARATIONS -->
			<table align="center" width="100%" border="0" cellpadding="1" cellspacing="0" class="borderTableBlue">
	  			<tr class="detailHead">
				    <td width="1%"><a href="javascript:showHideMulti('declarationInfo', 'declarationInfoSpan', '2', '/<msp:webapp/>')" border="0"><img border="0" src="/<msp:webapp/>images/expand.gif" name="declarationInfo"></a>&nbsp;
	  				Declarations</td>
	  			</tr>
				<tr id="declarationInfoSpan0" class="hidden">
					<td colspan="2">
						<table width="100%" cellpadding="2" cellspacing="1" >
							<tr>
								<td valign="top" class="formDeLabel" width="30%" nowrap="nowrap">Continuously detained since</td>
								<td valign="top" class="formDe" width="18%" ><bean:write name="legacyCourtOrdersForm" property="continuouslyDetainedDate" formatKey="date.format.mmddyyyy"/></td>
								<td valign="top" class="formDeLabel" width="27%" nowrap="nowrap">Days in Detention</td>
								<td valign="top" class="formDe"><bean:write name="legacyCourtOrdersForm" property="daysInDetention"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap>Placement Outside Home (Determinate)</td>
								<td valign="top" class="formDe"><jims:displayBoolean  name="legacyCourtOrdersForm" property="determinatePlacement" trueValue="Yes" falseValue=""/></td>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Probation Granted (Determinate)</td>
								<td valign="top" class="formDe"><jims:displayBoolean  name="legacyCourtOrdersForm" property="determinateProbation" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">TJJD Detention</td>
								<td valign="top" class="formDe" colspan="3"><jims:displayBoolean  name="legacyCourtOrdersForm" property="tycDetention" trueValue="Yes" falseValue=""/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">TJJD Sentence Length</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="tycSentenceLength"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Reason TJJD commitment (1)</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="determinateReason1"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Reason TJJD commitment (2)</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="determinateReason2"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Reason TJJD commitment (3)</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="determinateReason3"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Reason TJJD commitment (4)</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="determinateReason4"/></td>
							</tr>
							<tr>
								<td valign="top" class="formDeLabel" nowrap="nowrap">Reason TJJD commitment (5)</td>
								<td valign="top" class="formDe" colspan="3"><bean:write name="legacyCourtOrdersForm" property="determinateReason5"/></td>
							</tr>
						</table>
					</td>
				</tr>
	  		</table>
<!-- END DECLARATIONS -->  		
	  		<div class="spacer"></div>
<!-- BEGIN BUTTON TABLE -->
	     	<table border="0" cellpadding="1" cellspacing="1" align="center">
	        	<tr>
	        		<td align="center"><input type=button value="Close Window" onClick="window.close();"></td>
	        	</tr>
	      	</table>
<!-- END BUTTON TABLE -->      	
			<div class="spacer"></div>
		</td>
	</tr>
</table>
<!-- END DETAIL ALIGNMENT TABLE -->
</body>
</html:html>