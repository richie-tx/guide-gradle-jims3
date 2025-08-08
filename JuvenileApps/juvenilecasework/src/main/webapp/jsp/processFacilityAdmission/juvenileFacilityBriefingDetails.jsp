<!DOCTYPE HTML>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.Features" %>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionMessages" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1"/>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">


<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/facility.js"></script> 

<html:base />
<title>juvenileFacilityBriefingDetails.jsp</title>

<%--HELP JAVASCRIPT FILE --%> 
<%-- <SCRIPT SRC="../js/help.js" /> --%>
<script type="text/javascript"> 
function casefileCallback(tForm)
{
	<logic:notEqual name="juvenileBriefingDetailsForm" property="fromProfile" value="profileBriefingDetails">
		<logic:notEqual name="juvenileBriefingDetailsForm" property="currentCasefile.caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING%>">
		  spinner();
		  goNav("/<msp:webapp/>displayJuvenileCasefileDetails.do?supervisionNum=<bean:write name='juvenileBriefingDetailsForm' property='supervisionNum'/>");
	  </logic:notEqual>
	</logic:notEqual> 
}
$(document).ready(function(){	
	$("#btnAdmit").click(function(){
		spinner();
	})
	
	$("#btnModify").click(function(){
		spinner();
	})
	
	$("#btnEscape").click(function(){
		spinner();
	})
	
	$("#btnRelease").click(function(){
		spinner();
	})
	
	$("#btnTransfer").click(function(){
		spinner();
	})
	
	$("#btnTempRelease").click(function(){
		spinner();
	})
	
	$("#btnTempReleaseDecision").click(function(){
		spinner();
	})
	
	$("#btnRetEscape").click(function(){
		spinner();
	})
	
	
	
	$("#generateFinalBtn").click(function(){
		spinner();
		$.ajax({
	        url: '/JuvenileCasework/submitJuvenileFacilityAdmit.do?submitAction=Generate Final&view=briefingView&nocache='+(new Date()).getTime(),
	        type: 'post',
	        success: function(data, textStatus , xhr) {
	         	if (200 == xhr.status){
	         		$(".overlay").remove();
	         	}
	        }
	    });
	})
	
	var webApp = "/<msp:webapp/>";
	var juvNum = '<bean:write name="juvenileBriefingDetailsForm" property="juvenileNum"/>';	
	var url = webApp + "displayJuvenileProfileReferralList.do?submitAction=Link&juvnum=" +juvNum +"&testAction=fromProcessReferral";	
	var referralWinStatus = localStorage.getItem("referralWin");	
	var juvInWindow = localStorage.getItem("juvnum");
	if(referralWinStatus == "open")	{	
		
		if(juvInWindow!= null && juvInWindow == juvNum){
			return false;
		}
		//window is open just refresh it
		 var referralWindow = window.open(url, "referralWindow", "height=500,width=800,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
		 localStorage.setItem("referralWin", "open");
		 localStorage.setItem("juvnum", juvNum);
		 referralWindow.focus();		
		//return false;
	}
	
})
</script>

</head> 
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
<html:form  styleId ="juvBriefingDetailsDisplay" action="/displayJuvenileBriefingDetails" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header">Process Juvenile Facility Admission - Juvenile Briefing Details</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<jims2:if name="juvenileBriefingDetailsForm" property="tempReleaseRequestDecision" value="N" op="equal">
<jims2:and name="juvenileBriefingDetailsForm" property="tempDistReleaseRequestDecision" value="C" op="equal"/>
<jims2:then>
	<div class='spacer'></div>
		<table width='100%'>
			<tr>
				<td align="center" class="errorAlert">Youth has district and detention hearing set for today.  Contact
				<a id="evCourtAdminCheck" href="javascript:goNav('/<msp:webapp/>displayJuvenileFacilityRelease.do?submitAction=courtAdmin&juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>');">Court Administration</a> prior to temporary release.</td>
			</tr>
		</table>
</jims2:then>
<jims2:else>
	<jims2:if name="juvenileBriefingDetailsForm" property="tempReleaseRequestDecision" value="N" op="equal">
		<jims2:then>
			<div class='spacer'></div>
			<table width='100%'>
				<tr>
					<td align="center" class="errorAlert">Youth has detention hearing set for today.  Contact
					<a id="evCourtAdminCheck" href="javascript:goNav('/<msp:webapp/>displayJuvenileFacilityRelease.do?submitAction=courtAdmin&juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>');">Court Administration</a> prior to temporary release.</td>
				</tr>
			</table>
		</jims2:then>
	</jims2:if>
	<jims2:if name="juvenileBriefingDetailsForm" property="tempDistReleaseRequestDecision" value="C" op="equal">
		<jims2:then>
			<div class='spacer'></div>
			<table width='100%'>
				<tr>
					<td align="center" class="errorAlert">Youth has district hearing set for today.  Contact
					<a id="evCourtAdminCheck" href="javascript:goNav('/<msp:webapp/>displayJuvenileFacilityRelease.do?submitAction=courtAdmin&juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>');">Court Administration</a> prior to temporary release.</td>
				</tr>
			</table>
		</jims2:then>
	</jims2:if>
</jims2:else>
</jims2:if>
<table width='100%'>
<jims2:if name="juvenileBriefingDetailsForm" property="tempReleaseRequestDecision" value="R" op="equal">
<jims2:and name="juvenileBriefingDetailsForm" property="tempDistReleaseRequestDecision" value="R" op="equal"/>
<jims2:then>
	<div class='spacer'></div>
		<table width='100%'>
			<tr>
				<td align="center" class="errorAlert">Court Administration has determined temporary release is not permitted for the current juvenile record. Please contact Court Administration for further assistance.
			</td>
			</tr>
		</table>
</jims2:then>
<jims2:else>
	<jims2:if name="juvenileBriefingDetailsForm" property="tempReleaseRequestDecision" value="R" op="equal">
		<jims2:then>
			<div class='spacer'></div>
			<table width='100%'>
			<tr>
				<td align="center" class="errorAlert">Court Administration has determined temporary release is not permitted for the current juvenile record. Please contact Court Administration for further assistance.
			</td>
			</tr>
			</table>
		</jims2:then>
	</jims2:if>
	<jims2:if name="juvenileBriefingDetailsForm" property="tempDistReleaseRequestDecision" value="R" op="equal">
		<jims2:then>
			<div class='spacer'></div>
			<table width='100%'>
			<tr>
				<td align="center" class="errorAlert">Court Administration has determined temporary release is not permitted for the current juvenile record. Please contact Court Administration for further assistance.
			</td>
			</tr>
			</table>
		</jims2:then>
	</jims2:if>
</jims2:else>
</jims2:if>
<jims2:if name="juvenileBriefingDetailsForm" property="tempReleaseRequestDecision" value="P" op="equal">
<jims2:and name="juvenileBriefingDetailsForm" property="tempDistReleaseRequestDecision" value="P" op="equal"/>
<jims2:then>
	<div class='spacer'></div>
		<table width='100%'>
			<tr>
				<td align="center" class="errorAlert">Court Administration request has been previously submitted and response is pending.</td>
			</tr>
		</table>
</jims2:then>
<jims2:else>
	<jims2:if name="juvenileBriefingDetailsForm" property="tempReleaseRequestDecision" value="P" op="equal">
		<jims2:then>
			<div class='spacer'></div>
			<table width='100%'>
			<tr>
				<td align="center" class="errorAlert">Court Administration request has been previously submitted and response is pending.</td>
			</tr>
			</table>
		</jims2:then>
	</jims2:if>
	<jims2:if name="juvenileBriefingDetailsForm" property="tempDistReleaseRequestDecision" value="P" op="equal">
		<jims2:then>
			<div class='spacer'></div>
			<table width='100%'>
			<tr>
				<td align="center" class="errorAlert">Court Administration request has been previously submitted and response is pending.</td>
			</tr>
			</table>
		</jims2:then>
	</jims2:if>
</jims2:else>
</jims2:if>
<jims2:if name="juvenileBriefingDetailsForm" property="tempReleaseRequestDecision" value="S" op="equal">
<jims2:and name="juvenileBriefingDetailsForm" property="tempDistReleaseRequestDecision" value="S" op="equal"/>
<jims2:then>
	<div class='spacer'></div>
		<table width='100%'>
			<tr>
				<td class="confirm">Temporary Release request is sent to Court Administration.</td>
			</tr>
		</table>
</jims2:then>
<jims2:else>
	<jims2:if name="juvenileBriefingDetailsForm" property="tempReleaseRequestDecision" value="S" op="equal">
		<jims2:then>
			<div class='spacer'></div>
			<table width='100%'>
			<tr>
				<td class="confirm">Temporary Release request is sent to Court Administration.</td>
			</tr>
			</table>
		</jims2:then>
	</jims2:if>
	<jims2:if name="juvenileBriefingDetailsForm" property="tempDistReleaseRequestDecision" value="S" op="equal">
		<jims2:then>
			<div class='spacer'></div>
			<table width='100%'>
			<tr>
				<td class="confirm">Temporary Release request is sent to Court Administration.</td>
			</tr>
			</table>
		</jims2:then>
	</jims2:if>
</jims2:else>
</jims2:if>
</table>
 
<table width='100%'>
<tr>
	
	<!--  when no casefiles -->
	<logic:equal name="juvenileBriefingDetailsForm" property="hasCasefiles" value="false">
		<td align="center"><b>Referral needs to be assigned to generate a JIMS2 casefile. </b></td>
	</logic:equal>
	
	<logic:equal name="juvenileBriefingDetailsForm" property="hasCasefiles" value="true">
		<!--"Referral needs to be assigned to generate a JIMS2 supervision.When all casefiles are closed. -->
		<logic:notEqual name="juvenileBriefingDetailsForm" property="hasPendingCasefiles" value="true">
			<logic:notEqual name="juvenileBriefingDetailsForm" property="hasActiveCasefiles" value="true">	
				<%--Purge status --%>
				<logic:notEmpty name="juvenileBriefingDetailsForm" property="profileDetail.purgeFlag">
					<jims2:if name="juvenileBriefingDetailsForm" property="profileDetail.purgeFlag" value="=" op="equal">
						<jims2:or name="juvenileBriefingDetailsForm" property="profileDetail.purgeFlag" value="F" op="equal"/>
						<jims2:then>
							<td align="center"><b>Juvenile is marked for Purge, please contact TSD for assistance.</b></td>
						</jims2:then>
					</jims2:if>
				</logic:notEmpty>
				<logic:empty name="juvenileBriefingDetailsForm" property="profileDetail.purgeFlag">
					<td align="center"><b>Referral needs to be assigned to generate a JIMS2 casefile.</b></td>
				</logic:empty>
			</logic:notEqual>
			<%--Purge status --%>
		</logic:notEqual>
		<%--Facility status:when pending casefile--%>
		<logic:equal name="juvenileBriefingDetailsForm" property="hasActiveCasefiles" value="false">
		<logic:equal name="juvenileBriefingDetailsForm" property="hasPendingCasefiles" value="true">	
			<td align="center"><b>Juvenile cannot be admitted. Casefile needs to be activated.</b></td>
		</logic:equal>
		</logic:equal>
	</logic:equal>
	
	<logic:equal name="juvenileBriefingDetailsForm" property="hasOpenReferrals" value="false">
		<div class='spacer'></div>
		<table width='100%'>
			<tr>
				<td align="center" class="errorAlert">All referrals are closed. Juvenile cannot be admitted.</td>
			</tr>
		</table>
	</logic:equal> 
	
	
	<%--Facility status:when header info null --%>
	<logic:equal name="juvenileBriefingDetailsForm" property="hasActiveCasefiles" value="true">	
		<logic:equal name="juvenileBriefingDetailsForm" property="headerInfo.juvenileNumber" value="">	
			<logic:equal name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="">	
				<td align="center"><b>Facility Status: Juvenile is NOT in facility. No release or admission data is available.</b></td>
			</logic:equal>
		</logic:equal>
		<%--Release Details Facility status --%>
		<logic:notEmpty name="juvenileBriefingDetailsForm" property="headerInfo.juvenileNumber">
			<logic:equal name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="">	
				<logic:equal name="juvenileBriefingDetailsForm" property="admissionInfo.releaseTo" value="TRN">
					<td align="center"><b>Facility Status: Juvenile is NOT in facility. Juvenile is being transferred to new facility.</b></td>
				</logic:equal>
				
				<logic:notEqual name="juvenileBriefingDetailsForm" property="admissionInfo.releaseTo" value="TRN">
					<td align="center"><b>Facility Status: Juvenile was previously released from <bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.detainedFacilityDesc"/>.</b></td>
				</logic:notEqual>
			</logic:equal>
		</logic:notEmpty>

		<logic:notEqual name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="">	
			<%--Admission Details Facility status --%>
			<jims2:if name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="P" op="equal">
			<jims2:or name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="D" op="equal"/>
			<jims2:or name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="V" op="equal"/>
			<jims2:or name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="R" op="equal"/>
			<jims2:or name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="Q" op="equal"/>
			<jims2:then>
				<td align="center"><b>Facility Status: Juvenile currently in <bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.detainedFacilityDesc"/></b></td>
			</jims2:then>
			</jims2:if>
			
			<%--Escape Details Facility status --%>
			<logic:equal  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="E">	
					<td align="center"><b>Facility Status: Juvenile is listed as Escaped from {<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.escapeCodeDesc"/>} as of {<bean:write name="juvenileBriefingDetailsForm" property="headerInfo.relocationDate" formatKey="date.format.mmddyyyy"/>}</b></td>
			</logic:equal>
			
			<%--Transfer Details Facility status --%>
			<logic:equal  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="N">	
					<td align="center"><b>Facility Status: Juvenile currently in transfer from <bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.detainedFacilityDesc"/> to <bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.transferToFacilityDesc"/>.</b></td>
			</logic:equal>
			<%--Temporary release Details Facility status --%> 
			<logic:equal  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="T">	
					<td align="center"><b>Facility Status: Juvenile has been temporarily released from <bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.detainedFacilityDesc"/> on <bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseDate" formatKey="date.format.mmddyyyy"/></b></td>
			</logic:equal>
		
		</logic:notEqual>
	
		<%--escape Risk status --%>
			 <logic:equal  name="juvenileBriefingDetailsForm" property="escapeRisk" value="true">	
			 	<tr align="center">
					<td align="center"><b><font color="Red" size="3">ESCAPE RISK</font></b></td>
				</tr>
			</logic:equal>
	</logic:equal>
</tr>
</table>
<html:hidden property="headerInfo.facilityStatus" styleId="facilityStatus"/>
<html:hidden name ="juvenileBriefingDetailsForm" property="restrictedAccessTrait" styleId="restrictedAccessTrait"/>
<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td valign='top'>
			<table width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
				<tr class='facDetailHead'>
					<td class='facDetailHead' width='1%' nowrap='nowrap' align="left">&nbsp;Juvenile Profile Information&nbsp;							
						<a  href="mailto:Data.corrections@hcjpd.hctx.net">Email Data Corrections</a>
					      &nbsp;
					      <jims2:isAllowed requiredFeatures='<%=Features.JCW_CASEWORKBRIEFINGDETAILSSCREEN%>'>
         			  	  <a onclick="spinner()" href="/<msp:webapp/>displayJuvenileBriefingDetails.do?submitAction=Link&fromProfile=profileBriefingDetails&juvenileNum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>">Casework Briefing</a>&nbsp;&nbsp;         			  	  
         			      </jims2:isAllowed>         			      
					     <a onclick="spinner()" href="javascript:goNav('/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Facility&juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>');">Facility History</a>
					   	 &nbsp;
					   	<jims2:isAllowed requiredFeatures='<%=Features.JCW_SEARCHJUVREFPROF%>'>
							<a onclick="spinner()" href="/<msp:webapp/>displayJuvenileBriefingDetails.do?fromFacility=true&submitAction=referralLink&juvenileNum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>">
						Referral Briefing
						</a>
					</jims2:isAllowed> 
					</td>
					<td  align='right'><a onclick="spinner()" href="/<msp:webapp/>displayProfileActivities.do?fromFacility=true&submitAction=Link&juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>">View Activities</a>
					</td>
				</tr>
				<tr>
					<td valign='top' align='center' colspan='2'>
						<%--Alert for Officer --%>
						
						<table width="100%" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<logic:equal name="juvenileBriefingDetailsForm" property="officerAlert" value="true">
									<td colspan="2" align="center" bgcolor="red" class="jpoAlert"><b><font color="white" size="3">JUVENILE OFFICER SAFETY/SECURITY ALERT IS CURRENTLY ACTIVE</font></b></td>
								</logic:equal>
							</tr>
						</table>
						
						<%--Alert Ended --%>

<%-- BEGIN DETAIL TABLE --%>
						<table width="100%" cellpadding="2" cellspacing="2" border='0'>
							<tr>
								<td width='50%' valign="top">
									<%--general info start --%>
									<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
										<tr class='facDetailHead'>
											<td class='paddedFourPix' align='left' nowrap='nowrap'>Juvenile Information</td>
											<td align='left' nowrap='nowrap'>Master Status: 
												<span title="<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.masterStatus" />">
													<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.masterStatusId" />
												</span>
											</td>
											<td align='right'>
											<logic:notEmpty name="juvenileBriefingDetailsForm" property="casefiles">
												<logic:equal name="juvenileBriefingDetailsForm" property="profileDetail.updatable" value="true">
													<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_PI_U%>'>														
															<a onclick="spinner()" href="/<msp:webapp/>displayJuvenileMasterInformation.do?fromDashboard=update&juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>">Update Master</a>&nbsp;
													</jims2:isAllowed>	
												</logic:equal>
											</logic:notEmpty>
											</td>
	 									</tr>
										<tr>
											<td colspan="3">
 												<table width="100%" cellpadding="2" cellspacing="0" border='0'>
													<tr>
 														<td valign='top'>
															<table width="100%" cellpadding="2" cellspacing="1">
																<tr>
																	<logic:empty name="juvenileBriefingDetailsForm" property="profileDetail.preferredFirstName">
																	<td class='formDeLabel'>
																		<bean:message key="prompt.name"/>
																	</td>
																	<td class='formDe' colspan='3'>
																		<logic:equal name="juvenileBriefingDetailsForm" property="medicalHold" value="MHO">
																		<span style="color: red;"><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.lastName"/><sup title="Medical Hold">M</sup></span><span>,</span>
																	</logic:equal>	
																	<logic:notEqual name="juvenileBriefingDetailsForm" property="medicalHold" value="MHO">
																		<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.lastName"/><span>,</span>
																	</logic:notEqual>
																		<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.firstName"/>
																		<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.middleName"/>																	
																		&nbsp;
																		<logic:equal name="juvenileBriefingDetailsForm" property="dualStatus" value="FDS">
																			<span style="color: purple; font-weight: bold;" title="Former Dual Status">(<bean:write name="juvenileBriefingDetailsForm" property="dualStatus"/>)</span>
																		</logic:equal
																		<logic:equal name="juvenileBriefingDetailsForm" property="dualStatus" value="DS">
																			<span style="color: purple; font-weight: bold;" title="Dual Status">(<bean:write name="juvenileBriefingDetailsForm" property="dualStatus"/>)</span>
																		</logic:equal>
																		</logic:equal>																		
																		&nbsp;
												  							<logic:equal name="juvenileBriefingDetailsForm" property="traitTypeId" value="MER">
												  								<span style="color:#8417A0; font-weight: bold;" title="<bean:write name="juvenileBriefingDetailsForm" property="traitTypeDescription"/>">(M)</span>
												  							</logic:equal>
																		<logic:equal name="juvenileBriefingDetailsForm" property="holdTrait" value="HOLD">
																			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: orange; font-weight: bold;" title="<bean:write name="juvenileBriefingDetailsForm" property="holdTraitDescription"/>"><bean:write name="juvenileBriefingDetailsForm" property="holdTrait"/></span>
																		</logic:equal>
																		<logic:equal name="juvenileBriefingDetailsForm" property="inSpecialtyCourt" value="true">
																			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: green; font-weight: bold; margin-left: 35px;" title="<bean:write name="juvenileBriefingDetailsForm" property="specialtyCourtDescription"/>">SC</span>
																		</logic:equal>
																		<logic:equal name="juvenileBriefingDetailsForm" property="profileDetail.recType" value="I.JUVENILE">
																			&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Purged'>P</span></b></font>
																		</logic:equal>
																		<logic:equal name="juvenileBriefingDetailsForm" property="profileDetail.recType" value="S.JUVENILE">
																			&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Sealed'>S</span></b></font>
																		</logic:equal></td>																		
																		<logic:equal name="juvenileBriefingDetailsForm" property="restrictedAccessTrait" value="true"><span title='RESTRICTED ACCESS'> <font color="red"><b>(RESTRICTED)</b></font></span></logic:equal></td>
																	</td>																		
																	</logic:empty>
																	<logic:notEmpty name="juvenileBriefingDetailsForm" property="profileDetail.preferredFirstName">
																	<td class='formDeLabel'>
																		<bean:message key="prompt.name"/>
																	</td>
																	<td class='formDe' colspan='4'>
																		<logic:equal name="juvenileBriefingDetailsForm" property="medicalHold" value="MHO">
																		<span style="color: red;"><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.lastName"/><sup title="Medical Hold">M</sup></span><span>,</span>
																	</logic:equal>	
																	<logic:notEqual name="juvenileBriefingDetailsForm" property="medicalHold" value="MHO">
																		<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.lastName"/><span>,</span>
																	</logic:notEqual>
																		<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.firstName"/>
																		<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.middleName"/>																	
																		&nbsp;&nbsp;&nbsp;
																			<span style="font-weight: bold;" title="Preferred First Name">*<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.preferredFirstName"/></span>
		  																	&nbsp;
																		
																		<logic:equal name="juvenileBriefingDetailsForm" property="dualStatus" value="FDS">
																			<span style="color: purple; font-weight: bold;" title="Former Dual Status">(<bean:write name="juvenileBriefingDetailsForm" property="dualStatus"/>)</span>
																		</logic:equal
																		<logic:equal name="juvenileBriefingDetailsForm" property="dualStatus" value="DS">
																			<span style="color: purple; font-weight: bold;" title="Dual Status">(<bean:write name="juvenileBriefingDetailsForm" property="dualStatus"/>)</span>
																		</logic:equal
																		</logic:equal
																		<logic:equal name="juvenileBriefingDetailsForm" property="holdTrait" value="HOLD">
																			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: orange; font-weight: bold;" title="<bean:write name="juvenileBriefingDetailsForm" property="holdTraitDescription"/>"><bean:write name="juvenileBriefingDetailsForm" property="holdTrait"/></span>
																		</logic:equal>
																		<logic:equal name="juvenileBriefingDetailsForm" property="inSpecialtyCourt" value="true">
																			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: green; font-weight: bold; margin-left: 35px;" title="<bean:write name="juvenileBriefingDetailsForm" property="specialtyCourtDescription"/>">SC</span>
																		</logic:equal>
																		<logic:equal name="juvenileBriefingDetailsForm" property="profileDetail.recType" value="I.JUVENILE">
																			&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Purged'>P</span></b></font>
																		</logic:equal>
																		<logic:equal name="juvenileBriefingDetailsForm" property="profileDetail.recType" value="S.JUVENILE">
																			&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Sealed'>S</span></b></font>
																		</logic:equal></td>																		
																		<logic:equal name="juvenileBriefingDetailsForm" property="restrictedAccessTrait" value="true"><span title='RESTRICTED ACCESS'> <font color="red"><b>(RESTRICTED)</b></font></span></logic:equal></td>

																	</logic:notEmpty>	
																</tr>
																<tr>
																	<td class='formDeLabel' valign='top'><bean:message key="prompt.juvenile#"/></td>
																	<td class='formDe'>
																	<logic:equal name="juvenileBriefingDetailsForm" property="restrictedAccessTrait" value="true"><font color="red"><b><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.juvenileNum"/></b></font></logic:equal>
																	<logic:notEqual name="juvenileBriefingDetailsForm" property="restrictedAccessTrait" value="true"><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.juvenileNum"/></logic:notEqual>
																	</td>
																	<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.currentAge"/></td>
																	<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="age"/></td>
																</tr>
																<tr>
																	<td class='formDeLabel'><bean:message key="prompt.dob"/></td>
																	<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
																	<td class='formDeLabel'><bean:message key="prompt.sex"/></td>
																	<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.sex"/></td>
																</tr>
																<tr>
																	<td class='formDeLabel'><bean:message key="prompt.race"/></td>
																	<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.race"/></td>
																	<td class='formDeLabel'><bean:message key="prompt.multiracial"/></td>
																	<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="multiracial"/></td>
																</tr>
																<tr>
																	<td class='formDeLabel' valign='top'><bean:message key="prompt.ethnicity"/></td>
																	<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="ethnicity"/></td>
																	<td class='formDeLabel' valign='top'><bean:message key="prompt.hispanic"/></td>
																	<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="hispanic"/></td>
																</tr>
																<tr>
																	<td class='formDeLabel'><bean:message key="prompt.SSN"/></td>
																	<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.formattedSSN" /></td>
																	<td class='formDe' colspan="2">
																		
																			<strong><span title='<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.detentionFacility"/>'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.detentionFacilityId"/></span><logic:notEqual name="juvenileBriefingDetailsForm" property="profileDetail.detentionStatusId" value="">: </logic:notEqual></strong>
																				<span title='<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.detentionStatus"/>'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.detentionStatusId"/></span>
																	
																	</td>	
																</tr>
																<tr>
																	<td class='formDeLabel'><bean:message key="prompt.natural"/> <bean:message key="prompt.eyeColor"/></td>
																	<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="naturalEyeColor"/></td>
																	<td colspan='2' class='formDe'>
																		<logic:equal name="juvenileBriefingDetailsForm" property="inTrackerProgram" value="true">
																			<strong>TRACKER PROGRAM</strong>
																		</logic:equal>
																	</td>	
																</tr>
																<tr>
																	<td class='formDeLabel'><bean:message key="prompt.natural"/> <bean:message key="prompt.hairColor"/></td>
																	<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="naturalHairColor"/></td>
																	<td colspan='2' class='formDe'>
																		<logic:equal name="juvenileBriefingDetailsForm" property="inMentalHealthServices" value="true">
																			<strong>Mental Health services are needed.</strong></td>
																		</logic:equal>
																	</td>
																</tr>

<%-- begin Physical Characteristics section --%>																
																<tr class='facDetailHead'>
																	<td colspan='3' align='center'>Physical Characteristics </td>
																	<td align = "right"><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileMasterInformation.do?updatePhysical=true&juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>">Update Physical Characteristics</a></td>
																</tr>
													</table>
													<table width="100%" cellpadding="2" cellspacing="1">
																
  																<tr>
	  																<td class='formDeLabel'><bean:message key="prompt.build"/></td>
	  																<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="physicalAttribute.build"/></td>
<%-- special photo section --%>
																	<td class='formDe' rowspan='7' colspan='2'>
																		<table>
				  															<logic:empty name='juvenilePhotoForm' property='mostRecentPhoto'>
				  																<tr>
				  																	<td><b>Juvenile has no photos</b></td>
				  																</tr>
				  															</logic:empty>
				  															<logic:notEmpty name='juvenilePhotoForm' property='mostRecentPhoto'>
				  																<tr>
					  																<td>
					  																	<a href="javascript:newCustomWindowForPhoto('/<msp:webapp/>getJuvenilePhoto.do?submitAction=Link&juvenileNumber=<bean:write name="juvenileBriefingDetailsForm" property="juvenileNum"/>&selectedValue=<bean:write name="juvenilePhotoForm" property="mostRecentPhoto.photoName"/>&source=photo&rrand=<%out.print((Math.random()*246));%>','juvPhoto',400,400);"  > 																
					  																	<img alt="Mug Shot Not Available" title="Juvenile Photo" src="/<msp:webapp/>getJuvenilePhoto.do?submitAction=Most Recent Photo&juvenileNumber=<bean:write name="juvenileBriefingDetailsForm" property="juvenileNum"/>" width="128" border='1'></a>
					  																</td>
				  																</tr>
				  																<tr>
				  																	<td align="left"><bean:write name='juvenilePhotoForm' property='mostRecentPhoto.entryDate' formatKey="date.format.mmddyyyy"/></td>
				  																</tr>								
				  																<tr>
				  																	<td>										
				    																	<logic:greaterThan name='juvenilePhotoForm' property='totalPhotosAvailable' value='1'>
				    																		<input type='button' value="View All Photos" onclick="javascript:newCustomWindowForPhoto('/<msp:webapp/>getJuvenilePhoto.do?submitAction=Link&selectedValue=&source=main&rrand=<%out.print((Math.random()*246));%>','juvPhoto',450,450);"/>
				    																	</logic:greaterThan>		
				  																	</td>
				  																</tr>
				  															</logic:notEmpty>
																		</table>
																	</td>
<%-- special photo section --%>
																</tr>
  																<tr>
  																	<td class='formDeLabel'><bean:message key="prompt.height"/></td>
  																	<td class='formDe'>
	  																	<logic:notEmpty name="juvenileBriefingDetailsForm" property="physicalAttribute.heightFeet">
		  																	<bean:write name="juvenileBriefingDetailsForm" property="physicalAttribute.heightFeet"/>ft
		  																	<bean:write name="juvenileBriefingDetailsForm" property="physicalAttribute.heightInch"/>in
		  																</logic:notEmpty>
	  																</td>
	  															</tr>
	  															<tr>
	  																<td class='formDeLabel'><bean:message key="prompt.weight"/></td>
	  																<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="physicalAttribute.weight"/></td>
	  															</tr>
	  															<tr>
	  																<td class='formDeLabel'><bean:message key="prompt.hairColor"/></td>
	  																<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="physicalAttribute.hairColor"/></td>
	  															</tr>
	  
																<tr>
																	<td class='formDeLabel'><bean:message key="prompt.eyeColorContacts"/></td>
																	<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="physicalAttribute.eyeColor"/></td>
																</tr>
	  															<tr>
	  																<td class='formDeLabel'><bean:message key="prompt.tattoos"/></td>
	  																<td class='formDe'>
	  																	<bean:write name="juvenileBriefingDetailsForm" property="physicalAttribute.tattoosDescription"/>
	  																</td>
	  															</tr>
	  															<tr>
	  																<td class='formDeLabel'>Other Tattoo Comments</td>
	  																<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="physicalAttribute.otherTattooCommentsAsString"/></td>
	  															</tr>
<%-- end Physical Characteristics section --%>																
<%-- begin School section --%>																
																<tr class='facDetailHead'>
																	<td colspan='4' align='center'><bean:message key="prompt.schoolInfo"/></td>
																</tr>
	  															<tr>
	  																<td class='formDeLabel'><bean:message key="prompt.school"/></td>
	  																<logic:notEmpty name="juvenileBriefingDetailsForm" property="school.specificSchoolName">
	  																	<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="school.specificSchoolName"/></td>
	  																</logic:notEmpty>
	  																<logic:empty name="juvenileBriefingDetailsForm" property="school.specificSchoolName">
	  																	<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="school.school"/></td>
	  																</logic:empty>
	  															</tr>
	  															<tr>
	  																<td class='formDeLabel'><bean:message key="prompt.district"/></td>
	  																<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="school.schoolDistrict"/></td>
	  															</tr>
	  
	  															<tr>
	  																<td class='formDeLabel'><bean:message key="prompt.gradeLevel"/></td>
	  																<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="school.gradeLevelDescription"/>&nbsp;
<%-- based on the Juv's Grade Level Code, display a specific icon --%>
	  																	<logic:notEmpty name="juvenileBriefingDetailsForm" property="school.appropriateLevelCode">
																				<logic:equal name="juvenileBriefingDetailsForm" property="school.appropriateLevelCode" value="APP">
	  																				<img src="/<msp:webapp/>images/grade_level_appropriate.png" alt="Appropriate" />
																				</logic:equal>
	
																				<logic:equal name="juvenileBriefingDetailsForm" property="school.appropriateLevelCode" value="BEH">
	  																				<img src="/<msp:webapp/>images/grade_level_behind.png" alt="Behind" />
																				</logic:equal>
	
																				<logic:equal name="juvenileBriefingDetailsForm" property="school.appropriateLevelCode" value="ADV">
	  																				<img src="/<msp:webapp/>images/grade_level_advanced.gif" alt="Advanced" />
																				</logic:equal>
	  																	</logic:notEmpty>
	  																</td>
	  															</tr>
	  															<tr>
	  																<td class='formDeLabel'><bean:message key="prompt.enrollmentDate"/></td>
	  																<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="school.lastAttendedDateString"/></td>
	  															</tr>
	  															<tr>
	  																<td class='formDeLabel'><bean:message key="prompt.attendance"/> <bean:message key="prompt.status"/></td>
	  																<td class='formDe' colspan='3'>
	  																	<bean:write name="juvenileBriefingDetailsForm" property="school.schoolAttendanceStatusDescription" />
	  																	<span style="color:blue; font-weight: bold; margin-left:30px;">
	  																		<span title="Special Education"><logic:equal name="juvenileBriefingDetailsForm" 
	  																						property="school.educationService"
	  																						value="SPECIAL EDUCATION">
	  																			SE
	  																		</logic:equal></span>
	  																		<span title="504 Services"><logic:equal name="juvenileBriefingDetailsForm" 
	  																						property="school.educationService" 
	  																						value="504 SERVICES">
	  																			504
	  																		</logic:equal></span>
	  																	</span>
	  																</td>
	  															</tr>
<%-- end School section --%>																
<%-- begin Residential section --%>																
	  															<tr class='facDetailHead'>
	  																<td colspan='4' align='center'><bean:message key="prompt.residential"/> <bean:message key="prompt.info"/>
																			<logic:empty name="juvenileBriefingDetailsForm" property="casefiles">
																				(No Residential Information)
																			</logic:empty>
	  																</td>
	  															</tr>
																<logic:notEmpty name="juvenileBriefingDetailsForm" property="casefiles">
		  															<tr>
		  																<td class='formDeLabel'><bean:message key="prompt.address"/></td>
																		<td class='formDe' colspan='3'>
																		<logic:notEmpty name="juvenileBriefingDetailsForm" property="memberAddress">
		  																	<div>
		  																		<bean:write name="juvenileBriefingDetailsForm" property="memberAddress.streetAddress"/>
<%-- based on the Address validation, display a specific icon --%>
			  																	<logic:notEmpty name="juvenileBriefingDetailsForm" property="memberAddress.validated">
																					<logic:equal name="juvenileBriefingDetailsForm" property="memberAddress.validated" value="Y">
			  																			&nbsp;<img src="/<msp:webapp/>images/grade_level_appropriate.png" alt="Appropriate" />
																					</logic:equal>
		
																					<logic:equal name="juvenileBriefingDetailsForm" property="memberAddress.validated" value="N">
		  																				&nbsp;<img src="/<msp:webapp/>images/red_x.gif" alt="redx" />
																					</logic:equal>
			 																	</logic:notEmpty>		  																	
		  																	</div>
		  																	<logic:notEqual name="juvenileBriefingDetailsForm" property="memberAddress.cityStateZip" value="">
		  																		<div>
		  																			<bean:write name="juvenileBriefingDetailsForm" property="memberAddress.cityStateZip"/>
		  																		</div>	
		  																	</logic:notEqual>
		  																</logic:notEmpty>
		  																</td>	
		  															</tr>
		  															<tr>
		  																<td class='formDeLabel'><bean:message key="prompt.county"/></td>
		  																<logic:notEmpty name="juvenileBriefingDetailsForm" property="memberAddress">
		  																	<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="memberAddress.county"/></td>
		  																</logic:notEmpty>
		  															</tr>
		  															<tr>
		  																<td class='formDeLabel'>Phone</td>
		  																<logic:notEmpty name="juvenileBriefingDetailsForm" property="memberContact">
			  																<td class='formDe'>
			  																	<bean:write name="juvenileBriefingDetailsForm" property="memberContact.contactPhoneNumber.formattedPhoneNumber"/>
			  																	<logic:notEmpty name="juvenileBriefingDetailsForm" property="memberContact.contactPhoneNumber.ext">
			  																		Ext <bean:write name="juvenileBriefingDetailsForm" property="memberContact.contactPhoneNumber.ext"/>
			  																	</logic:notEmpty>
			  																</td>
			  																<td class='formDeLabel' width='1%' nowrap='nowrap'>Priority</td>
			  																<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="memberContact.primaryInd"/></td>
			  															</logic:notEmpty>
		  															</tr>
		  															<tr>
		  																<td class='formDeLabel'><bean:message key="prompt.type"/></td>
		  																<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="memberContact.contactType" /></td>
		  																<td class='formDeLabel'><bean:message key="prompt.unknown"/></td>
		  																<logic:equal name="juvenileBriefingDetailsForm" property="memberContact.contactPhoneNumber.formattedPhoneNumber" value="000-000-0000">
																			<td class='formDe'>Yes</td>
																		</logic:equal>
																		<logic:notEqual name="juvenileBriefingDetailsForm" property="memberContact.contactPhoneNumber.formattedPhoneNumber" value="000-000-0000">
																			<td class='formDe'></td>
																		</logic:notEqual></td>
		  															</tr>
	  															</logic:notEmpty>
<%-- end Residential section --%>																
															</table>
 														</td>
 													</tr>
 												</table>
 											</td>
 										</tr>
 									</table>
<%--general info end --%>
									<div class='spacer'></div>
								</td>
<%-- end of first major column --%>
<%-- begin second major column --%>
								<td width='44%' valign="top">
<%-- Warrant start --%>
									<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
										<tr class='facDetailHead'>
											<td class='paddedFourPix'>Warrant
												<logic:empty name="juvenileBriefingDetailsForm" property="warrant">
													(No Warrant Information)
												</logic:empty> 
											</td>
										</tr>

										<logic:notEmpty name="juvenileBriefingDetailsForm" property="warrant">
	    									<tr>
	    										<td colspan='4'>
	    											<table width="100%" cellpadding="2" cellspacing="1">
	    												<tr>
	    													<td class='formDeLabel' width="1%" align="left">Warrant#</td>
	    													
	    													<td class='formDe' width="49%" valign='top'>
	    													   <jims2:isAllowed requiredFeatures='<%=Features.JW_VIEW%>'>
	    														<a href="javascript:openWindow('/JuvenileWarrants/displayWarrantViewSearchResults.do?submitAction=Submit&warrantNum=<bean:write name="juvenileBriefingDetailsForm" property="warrant.warrantNum"/>&secondaryAction=popup');">
	    															<bean:write name="juvenileBriefingDetailsForm" property="warrant.warrantNum"/></a>
	    													   </jims2:isAllowed>
	    													   <jims2:isAllowed requiredFeatures='<%=Features.JW_VIEW%>' value="false">
	    													        <bean:write name="juvenileBriefingDetailsForm" property="warrant.warrantNum"/>
	    													   </jims2:isAllowed>    																		
	    													</td>
	    													<td class='formDeLabel' width="1%" nowrap="nowrap">Date Issued</td>
	    													<td class='formDe' width="49%" valign='top' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="warrant.dateOfIssue" formatKey="date.format.mmddyyyy"/></td>
	    												</tr>
	    												<tr>
	    													<td class='formDeLabel' width="1%" valign='top'>Type</td>
	    													<td class='formDe' width="99%" valign='top' colspan='6'><bean:write name="juvenileBriefingDetailsForm" property="warrant.warrantType"/></td>
	    												</tr>
	    												<tr>
	    													<td class='formDeLabel' width="1%" nowrap="nowrap">Originating Agency</td>
	    													<td class='formDe' width="99%" valign='top' colspan='6'><bean:write name="juvenileBriefingDetailsForm" property="warrant.warrantOriginatorAgencyName"/></td>
	    												</tr>
	    											</table>
	    										</td>
	    									</tr>
										</logic:notEmpty>
									</table>
<%-- Warrant end --%>

<%--Escape Details --%>		
										<logic:equal name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="E">
											<div class='spacer'></div>
											<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
											<tr class='facDetailHead'>
												<td class='paddedFourPix' align="left">Escape Details
													<logic:empty name="juvenileBriefingDetailsForm" property="admissionInfo">
														(Escape Details)
													</logic:empty> 
												</td>
											</tr>
											<tr>
												<td colspan='4'>
				    								<table width="100%" cellpadding="2" cellspacing="1">
						    							<tr>
					    									<td class='formDeLabel' width="1%" valign='top'><bean:message key="prompt.escapeDate"/></td>
							    							<td class='formDe' width="49%" nowrap="nowrap"><bean:write name="juvenileBriefingDetailsForm" property="headerInfo.relocationDate" formatKey="date.format.mmddyyyy"/></td>
							    							<td class='formDeLabel' width="1%" valign='top'><bean:message key="prompt.escapeTime"/></td>
							    							<td class='formDe' width="49%" nowrap="nowrap"><bean:write name="juvenileBriefingDetailsForm" property="headerInfo.relocationTime"/></td>
														</tr>
														<tr>
															<td class='formDeLabel' width="1%" valign='top'><bean:message key="prompt.escapeFrom"/></td>
															<td class='formDe' width="49%" valign='top'><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.escapeCodeDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.escapeCode"/></span></td>
															<td class='formDeLabel' valign='top' nowrap="nowrap"><bean:message key="prompt.escapeStatus"/></td>
							    							<td class='formDe' width="49%">
													   			<logic:equal  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="E">	
													   			ESCAPED
													    		</logic:equal>
													    		<logic:equal  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="R">	
													   			RETURNED
													    		</logic:equal>
												    		</td>
												    	</tr>
														<tr>
				    										<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.bookingReferral"/></td>
			   												<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.referralNumber"/></td>
				    										<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.bookingSupervision"/></td>
				    										<td class='formDe' width="49%" valign='top' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.bookingSupervisionNum"/></td>
				    									</tr>
				    									<tr>
				    										<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.bookingOffense"/></td>
				    										<td class='formDe' width="49%" valign='top' colspan='1'><span title='<bean:write name="juvenileBriefingDetailsForm" property="bookingOffenseCodeDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="bookingOffenseCode"/></span></td>
				    										<logic:notEqual name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="T">
	    														<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.outcome"/></td>
	    														<td class='formDe' width="49%" valign='top'><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.outcomeDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.outcome"/></span></td>
	    													</logic:notEqual>
				    									</tr>
				    								</table>
				    							</td>
											</tr>
										</table>
									</logic:equal>	
<%--Escape Details --%>
<%--Temporary Release Details --%>
									<logic:notEmpty name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus">
									<logic:notEmpty name="juvenileBriefingDetailsForm" property="admissionInfo.releaseTo">
									<jims2:if name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" op="equal" value="N">
									<jims2:or name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="T" op="equal"/>
									<jims2:then>
										<div class='spacer'></div>
										<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
										<logic:equal name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="T">
											<tr class='facDetailHead'>
												<td class='paddedFourPix' align="left">Release Details
													<logic:empty name="juvenileBriefingDetailsForm" property="admissionInfo">
														(No Release Details)
													</logic:empty> 
												</td>
											</tr>
										</logic:equal>
										<logic:equal name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="N">
											<tr class='facDetailHead'>
											<td class='paddedFourPix' align="left">In Transfer Details
												<logic:empty name="juvenileBriefingDetailsForm" property="admissionInfo">
													(No In Transfer Details)
												</logic:empty> 
											</td>
										</tr>
										</logic:equal>
											<tr>
	    										<td colspan='4'>
	    											<table width="100%" cellpadding="2" cellspacing="1">
	    												<tr>
	    													<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.releaseDate"/></td>
			    											<td class='formDe' width="49%" valign='top'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseDate" formatKey="date.format.mmddyyyy"/></td>
			    											<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.releaseTime"/></td>
			    											<td class='formDe' width="49%" valign='top'  colspan='4' ><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseTime" formatKey="time.format.HHmm"/></td>
			    										</tr>
	    												<tr>
	    													<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.releasedTo"/></td>
			    											<td class='formDe' width="49%" valign='top' ><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.relToDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseTo"/></span></td>	
			    											<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.releasedBy"/></td>
			    											<td class='formDe' width="49%" valign='top' colspan='4' width=""><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.relByDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseBy"/></span></td>
														</tr>
														<tr>
	    													<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.releaseReason"/></td>
											    			<td class='formDe' width="49%" valign='top'><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseReasonDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseReason"/></td>
											    			<td class='formDeLabel' width="1%"  nowrap="nowrap"><bean:message key="prompt.releaseAuthority"/></td>
			    											<td class='formDe' width="49%" valign='top' colspan='4' width=""><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.relAuthByDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseAuthorizedBy"/></span></td>	
			    										</tr>
														<logic:equal name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="N">
														<tr>
												    		<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.detainedFacility"/></td>
		    												<td class='formDe'width="49%" valign='top'><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.detainedFacilityDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.detainedFacility"/></span></td>
		    												<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.transferToFacility"/></td>
		    												<td class='formDe' width="49%" valign='top' colspan='4'><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.transferToFacilityDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.transferToFacility"/></span></td>
												    	</tr>
												    	</logic:equal>
												    	<tr>
	    													<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.bookingOffense"/></td>
	    													<td class='formDe' width="49%" valign='top' ><span title='<bean:write name="juvenileBriefingDetailsForm" property="bookingOffenseCodeDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="bookingOffenseCode"/></span></td>
	    													<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.bookingSupervision"/></td>
	    													<td class='formDe' width="49%" valign='top' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.bookingSupervisionNum"/></td>
	    												</tr>
	    												<tr>
	    													<td class='formDeLabel'width="1%"  nowrap="nowrap"><bean:message key="prompt.referralSource"/></td>
	    													<td class='formDe' width="49%" valign='top'><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.referralSourceDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.referralSource"/></span></td>
	    													<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.referralOfficer"/></td>
	    													<td class='formDe' width="49%" valign='top' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.referralOfficer"/></td>
	    												</tr>
	    												<tr>
	    													<logic:notEqual name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="T">
	    														<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.outcome"/></td>
	    														<td class='formDe' width="49%" valign='top'><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.outcomeDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.outcome"/></span></td>
	    													</logic:notEqual>
															<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.bookingReferral"/></td>
	    													<td class='formDe' width="49%" valign='top'  colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.referralNumber"/></td>
			    										</tr>
	    												<logic:equal name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="T">
		    												<tr>
		    													<td class='formDeLabel' width="1%" nowrap><bean:message key="prompt.temporaryReleaseReason"/></td>
				    											<td class='formDe' width="99%" colspan='6' nowrap="nowrap"><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.tempReleaseReasonCdDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.tempReleaseReasonCd"/></span></td>
													    	</tr>
													    	<logic:notEmpty name="juvenileBriefingDetailsForm" property="admissionInfo.tempReleaseOtherComments">
														    	<tr>
														    		<td class='formDeLabel' width="1%" colspan='8'><bean:message key="prompt.temporaryReleaseReasonOtherComments"/></td>
														    	</tr>
														    	<tr>
														    		<td class='formDe' width="99%" valign='top' colspan='8' wrap><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.tempReleaseOtherComments"/></td>
														    	</tr>
													    	</logic:notEmpty>
												  	  </logic:equal>
	    											</table>
	    										</td>
	    									</tr>
										</table>
									</jims2:then>
									</jims2:if>
									</logic:notEmpty>
									</logic:notEmpty>
									
									<logic:empty name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus">
									<logic:notEmpty name="juvenileBriefingDetailsForm" property="admissionInfo.releaseTo">		
									<div class='spacer'></div>
										<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
											<tr class='facDetailHead'>
												<td class='paddedFourPix' align="left">Release Details
													<logic:empty name="juvenileBriefingDetailsForm" property="admissionInfo">
														(No Release Details)
													</logic:empty> 
												</td>
											</tr>
											<tr>
	    										<td colspan='4'>
	    											<table width="100%" cellpadding="2" cellspacing="1">
	    												<tr>
	    													<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.releaseDate"/></td>
			    											<td class='formDe' width="49%" valign='top'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseDate" formatKey="date.format.mmddyyyy"/></td>
			    											<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.releaseTime"/></td>
			    											<td class='formDe' width="49%" valign='top' colspan='4' width=""><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseTime" formatKey="time.format.HHmm"/></td>
			    										</tr>
	    												<tr>
	    													<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.releasedTo"/></td>
			    											<td class='formDe' width="49%" valign='top'><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.relToDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseTo"/></span></td>	
			    											<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.releasedBy"/></td>
			    											<td class='formDe' width="49%" valign='top' colspan='4' width=""><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.relByDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseBy"/></span></td>
														</tr>
														<tr>
	    													<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.releaseReason"/></td>
											    			<td class='formDe' width="49%" valign='top'><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseReasonDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseReason"/></td>	
											    			<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.releaseAuthority"/></td> 
			    											<td class='formDe' width="49%" valign='top' colspan='4' width=""><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.relAuthByDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.releaseAuthorizedBy"/></span></td>	
			    										</tr>
														<tr>
															<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.outcome"/></td>
	    													<td class='formDe' width="49%" valign='top'><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.outcomeDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.outcome"/></span></td>
															<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.bookingReferral"/></td>
	    													<td class='formDe' width="49%" valign='top' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.referralNumber"/></td>
			    										</tr>
												    	
												    	<tr>
	    													<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.bookingOffense"/></td>
	    													<td class='formDe' width="49%" valign='top'><span title='<bean:write name="juvenileBriefingDetailsForm" property="bookingOffenseCodeDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="bookingOffenseCode"/></span></td>
	    													<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.bookingSupervision"/></td>
	    													<td class='formDe' width="49%" valign='top' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.bookingSupervisionNum"/></td>
	    												</tr>
	    												<tr>
		    												<logic:notEmpty name="juvenileBriefingDetailsForm" property="admissionInfo.custodylastName"> 
		    													<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.custodyassumedBy"/></td>
		    													<td class='formDe' width="49%" valign='top' colspan='6'>
		    														<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.custodylastName"/>
		    															<logic:notEmpty name="juvenileBriefingDetailsForm" property="admissionInfo.custodyfirstName">
		    																, <bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.custodyfirstName"/>
		    															</logic:notEmpty>
		    													</td>
		    												</logic:notEmpty>
	    												</tr>
	    												<%-- <tr> //#32864
	    													<td class='formDeLabel'width="1%"  nowrap="nowrap"><bean:message key="prompt.referralSource"/></td>
	    													<td class='formDe' width="49%" valign='top'><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.referralSourceDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.referralSource"/></span></td>
	    													<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.referralOfficer"/></td>
	    													<td class='formDe' width="49%" valign='top' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.referralOfficer"/></td>
	    												</tr> --%>
	    											</table>
	    										</td>
	    									</tr>
										</table>
									</logic:notEmpty>
									</logic:empty>
								
						
									<%--Admission Information start --%>
									<logic:notEqual name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="">	
									<div class='spacer'></div>
										<html:hidden property="refTransferMesg" styleId="refTransferMesg"/>
										<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
											<tr class='facDetailHead'>
												<td class='paddedFourPix' align="left">Admission Information
													<logic:empty name="juvenileBriefingDetailsForm" property="admissionInfo">
														(No Admission Information)
													</logic:empty> 
												</td>
												<logic:notEmpty name="juvenileBriefingDetailsForm" property="admissionInfo">
													<td align='right'>
							    					    &nbsp;
							     						<a href="javascript:newCustomWindow('/<msp:webapp/>displayJuvenileBriefingDetails.do?submitAction=View&juvnum=<bean:write name="juvenileBriefingDetailsForm" property="juvenileNum"/>','viewDetails',1000,800)"><bean:message key="button.viewDetails"/></a>
							     						&nbsp;
													</td>
												</logic:notEmpty>
											</tr>
											<logic:notEmpty name="juvenileBriefingDetailsForm" property="admissionInfo">
		    									<tr>
		    										<td colspan='4'>
		    											<table width="100%" cellpadding="2" cellspacing="1">
		    												<tr>
		    													<td class='formDeLabel' width="1%" valign='top' nowrap="nowrap"><bean:message key="prompt.facility"/></td>
		    													<td class='formDe' width="99%" valign='top' colspan='6'><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.detainedFacilityDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.detainedFacility"/></span></td>
		    												</tr>
		    												<tr>
		    													<td class='formDeLabel' nowrap="nowrap" width="1%"><bean:message key="prompt.admitDate"/></td>
		    													<td class='formDe' width="49%" valign='top'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.admitDate" formatKey="date.format.mmddyyyy"/></td>
		    													<td class='formDeLabel' width='1%' nowrap="nowrap"><bean:message key="prompt.admitTime"/></td>
		    													<td class='formDe' width="49%" valign='top' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.admitTime" formatKey="time.format.HHmm"/></td>
		    												</tr>
		    												<tr>
		    													<td class='formDeLabel' width="1%"  nowrap="nowrap"><bean:message key="prompt.admitAuthorizedBy"/></td> 
		    													<td class='formDe' width="49%" valign='top'><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.admitAuthorityDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.admitAuthority"/></span></td>
		    													<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.admittedBy"/></td>
		    													<td class='formDe' width="49%" valign='top' colspan='4'><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.admittedByJPODesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.admittedByJPO"/></span></td>
		    												</tr>
		    												<tr>
		    													<td class='formDeLabel' width="1%"  nowrap="nowrap"><bean:message key="prompt.admitReason"/></td>
		    													<td class='formDe' width="49%" valign='top'><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.admitReasonDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.admitReason"/></span></td>
		    													
		    													<%-- <td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.secureIndicator"/></td>
		    													<jims2:if name="juvenileBriefingDetailsForm" property="admissionInfo.secureStatus" value="S" op="equal">
																<jims2:or name="juvenileBriefingDetailsForm" property="admissionInfo.secureStatus" value="B" op="equal"/>
																<jims2:then>
		    														<td class='formDe' width="49%" valign='top'><span title='SECURE'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.secureStatus"/></span></td>
		    													</jims2:then>
		    													<jims2:else>
		    														<td class='formDe' width="49%" valign='top'><span title='NON-SECURE'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.secureStatus"/></span></td>
		    													</jims2:else>
		    													</jims2:if> --%> <%--commented for US#38986, TASK# 42493, added the admitReason column above --%> 
		    													<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.bookingReferral"/></td>
		    													<td class='formDe' width="49%" valign='top' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.referralNumber"/></td>
		    												</tr>
		    												<tr>
		    													<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.bookingOffense"/></td>
		    													<td class='formDe' width="49%" valign='top'><span title='<bean:write name="juvenileBriefingDetailsForm" property="bookingOffenseCodeDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="bookingOffenseCode"/></span></td>
		    													<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.bookingSupervision"/></td>
		    													<td class='formDe' width="49%" valign='top' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.bookingSupervisionNum"/></td>
		    												</tr>
		    												<tr>
		    													<td class='formDeLabel'width="1%"  nowrap="nowrap"><bean:message key="prompt.referralSource"/></td>
		    													<td class='formDe' width="49%" valign='top'><span title='<bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.referralSourceDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.referralSource"/></span></td>
		    													<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.referralOfficer"/></td>
		    													<td class='formDe' width="49%" valign='top' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.referralOfficer"/></td>
		    												</tr>
		    												<tr>
		    													<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.valuableReceipt"/></td>
		    													<td class='formDe' width="49%" valign='top'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.receiptNumber"/></td>
		    													<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.locker"/></td>
		    													<td class='formDe' width="49%" valign='top' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="admissionInfo.lockerNumber"/></td>
		    												</tr>
		    											</table>
		    										</td>
		    									</tr>
											</logic:notEmpty>
										</table>
									</logic:notEqual>
<%--Admission Information end --%>
									<div class='spacer'></div>
<%-- Traits section start --%>
									 
									<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
										<tr class='facDetailHead'>
											<td class='paddedFourPix'>
											  <jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTACTVTYYOUTH%>'>
											  	<div style="text-align:left;float:left;"> <a onclick="spinner()" href="javascript:goNav('/<msp:webapp/>processJuvenileDistrictCourtHearings.do?submitAction=courtActivityByYouth&juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>');">Court Activity</a></div>
											  </jims2:isAllowed>		
											 
											 <logic:notEqual  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="">	
												<jims2:if name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="D" op="equal">
												<jims2:or name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="P" op="equal"/>
												<jims2:or name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="V" op="equal"/>
												<jims2:or name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="R" op="equal"/>
												<jims2:or name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="Q" op="equal"/>
												<jims2:then>  
												<logic:equal name="juvenileBriefingDetailsForm" property="hasActiveCasefiles" value="true">											
													<div style="text-align:right;float:right;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:newCustomWindow('/<msp:webapp/>displayJuvenileFacilityTraitsSearch.do?submitAction=populateTraits&admitView=true&juvNum=<bean:write name="juvenileBriefingDetailsForm" property="juvenileNum"/>','traits',1000,800)">View Facility Traits</a></div>
												</logic:equal>
												</jims2:then>
												<jims2:else>
													<div style="text-align:right;float:right;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:newCustomWindow('/<msp:webapp/>displayJuvenileFacilityTraitsSearch.do?submitAction=populateTraits&modifyAdmit=true&juvNum=<bean:write name="juvenileBriefingDetailsForm" property="juvenileNum"/>','traits',1000,800)">View Facility Traits</a></div>
												</jims2:else>
												</jims2:if>
											</logic:notEqual>		
											
											<logic:equal  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="">	
												<div style="text-align:right;float:right;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:newCustomWindow('/<msp:webapp/>displayJuvenileFacilityTraitsSearch.do?submitAction=populateTraits&modifyAdmit=true&juvNum=<bean:write name="juvenileBriefingDetailsForm" property="juvenileNum"/>','traits',1000,800)">View Facility Traits</a></div>
											</logic:equal>
															
											</td>											
										</tr>
										<tr class='facDetailHead'>
											<td class='paddedFourPix'><div style="text-align:left;float:left;">Detention/Facility Traits
												<logic:empty name="juvenileBriefingDetailsForm" property="traits">
													(No Trait Information)
												</logic:empty></div>
																					
											<logic:notEmpty name="juvenileBriefingDetailsForm" property="traits">
											
					    					  	<div style="text-align:right;float:right;"><a onclick="spinner" href="/<msp:webapp/>displayJuvenileProfileTraitsSearch.do?submitAction=Tab&action=%20">View Traits</a></div>
					     						&nbsp;
											
											</logic:notEmpty>
											</td>
										</tr>

										<logic:notEmpty name="juvenileBriefingDetailsForm" property="traits">
											<tr>
												<td colspan='2'>
													<%-- this span simulates a scrolling table after 5 entries --%>
													<div class='scrollingDiv100'>
														<table width="100%" cellpadding="2" cellspacing="1" border='0'>
															<thead>
																<%-- static, column titles --%>
																<tr class='formDeLabel'>
																	<td align="left">Entry Date</td>
																	<td align="left">Trait Description</td>
																	<td align="left">Trait Status</td>
																</tr>
															</thead>
															<tbody>
																<logic:iterate id="traitsList" name="juvenileBriefingDetailsForm" property="traits" indexId="indexer"> 
																	<tr class="<% out.print( (indexer.intValue() % 2 == 1) ? "normalRow" : "alternateRow" ); %>">
																		<td align="left"><a href="javascript:newCustomWindow('/<msp:webapp/>displayJuvenileBriefingDetails.do?submitAction=Details&traitID=<%=indexer.intValue()%>','traits',600,300);"><bean:write name='traitsList' property='entryDate' formatKey="date.format.mmddyyyy" /></a></td>
																		<td align="left"><bean:write name="traitsList" property="traitTypeDescription" /></td>
																		<td align="left"><bean:write name="traitsList" property="status" /></td>
																	</tr>
																</logic:iterate>
															</tbody>
														</table>
													</div>
												</td>
											</tr>
										</logic:notEmpty> 
									</table>
<%-- Traits section end --%>
									<div class='spacer'></div>
<%-- Casefiles start --%>
									<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
										<tr class='facDetailHead'>
											<td class='paddedFourPix' onclick="casefileCallback(this.form);" align="left"><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileCasefileList.do?juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>">Casefiles</a>
												<logic:empty name="juvenileBriefingDetailsForm" property="casefiles">
													(No Casefiles)
												</logic:empty>
											</td>
											<td>
												JPO of Record - <span title='<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.jpoOfRecord"/> - <bean:write name="juvenileBriefingDetailsForm" property="profileDetail.jpoPhoneNumber"/> - <bean:write name="juvenileBriefingDetailsForm" property="profileDetail.locUnitName"/>'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.jpoOfRecID"/></span>
											</td>
											<logic:notEmpty name="juvenileBriefingDetailsForm" property="casefiles">
												<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_JOU%>'>
	  												<td align='right'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileJournalList.do?&juvenileNum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>&submitAction=<bean:message key='button.link'/>">Journals</a>&nbsp;</td>
												</jims2:isAllowed>
											</logic:notEmpty>
										</tr>
										
										<logic:notEmpty name="juvenileBriefingDetailsForm" property="casefiles">
											<tr>
												<td colspan='2'>
												<%-- this span simulates a scrolling table after 5 entries --%>
												<div class='scrollingDiv200'>
													<table width="100%" cellpadding="2" cellspacing="1" border='0'>
														<thead>
														<%-- static, column titles --%>
														<tr class='formDeLabel'>
															<td width='10%' align="left" width='1%' nowrap="nowrap">Supervision#&nbsp;</td>
															<td width='10%' align="left" >Status</td>
															<td width='40%' align="left">Supervision Type</td>
															<td width='20%' align="left" width="1%">Expected End Date</td>
															<td width='20%' align="left" >JPO</td>
														</tr>
														</thead>
														<tbody>
															<logic:iterate id="casefiles" name="juvenileBriefingDetailsForm" property="casefiles" indexId="indexer"> 
																<tr class="<% out.print( (indexer.intValue() % 2 == 1) ? "normalRow" : "alternateRow" ); %>">
																	<logic:equal name="casefiles" property="caseStatus" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING_DESCRIPTION%>">	 	
																		<td align="left"><a href="/<msp:webapp/>handleCasefileActivation.do?submitAction=Link&casefileID=<bean:write name='casefiles' property='supervisionNum'/>&action=default"><bean:write name="casefiles" property="supervisionNum"/></a></td>
																	</logic:equal>
																	<logic:notEqual name="casefiles" property="caseStatus" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING_DESCRIPTION%>">	 	
																		<td align="left"><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileCasefileDetails.do?supervisionNum=<bean:write name='casefiles' property='supervisionNum'/>"><bean:write name="casefiles" property="supervisionNum"/></a></td>
																	</logic:notEqual>	
																	<td align="left"><bean:write name="casefiles" property="caseStatus"/></td>
																	<td align="left"><bean:write name="casefiles" property="supervisionType"/></td>
																	<td align="left" ><bean:write name="casefiles" property="supervisionEndDate" formatKey="date.format.mmddyyyy" /></td>
																	<td align="left"><bean:write name="casefiles" property="probationOfficerLastName" /></td>
																</tr>
															</logic:iterate> 
														</tbody>
													</table>
												</div>
												</td>
											</tr>
										</logic:notEmpty>
									</table>
<%-- casefiles end --%>

								<div class='spacer'></div>
<%-- Detention Visitation info start --%>
								<jims2:isAllowed requiredFeatures="<%=Features.JCW_BRF_DET_VISITATION%>">
										<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
											<tr class='facDetailHead'>
												<logic:equal name="juvenileBriefingDetailsForm" property="detVisitBanned" value="true">
													<td nowrap align="left" bgcolor="red" class="jpoAlert"><b><bean:message key="prompt.detention"/> Visitation</b>
														<logic:empty name="juvenileBriefingDetailsForm" property="detVisits">
													 		(No Detention Visits)
								      					</logic:empty>
													</td>
													<td nowrap align="right" bgcolor="red" class="jpoAlert"><b><a id="bannedLink" href="#" data-href='<bean:write name="juvenileBriefingDetailsForm" property="juvenileNum"/>'>Banned</b></td>
												</logic:equal>
												<logic:notEqual name="juvenileBriefingDetailsForm" property="detVisitBanned" value="true">
													<td class='paddedFourPix'><bean:message key="prompt.detention"/> Visitation
														<logic:empty name="juvenileBriefingDetailsForm" property="detVisits">
													 		(No Detention Visits)
								      					</logic:empty>
													</td>
												</logic:notEqual>
							      			</tr>	
							      					<logic:notEmpty name="juvenileBriefingDetailsForm" property="detVisits">
							      						<tr>
								      						<td colspan='6'>
																<table width="100%" cellpadding="2" cellspacing="1">
																	<tr class='formDeLabel'>
																		<td>Name</td>
																		<td>Relationship</td>
																		<td>ID/Type</td>
																	</tr>
											      					<logic:iterate name="juvenileBriefingDetailsForm" property="detVisits" id="detVisitsIter" indexId="indexer">  
											      						<tr class="<% out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
																			<td><bean:write name="detVisitsIter" property="memOrContactName"/>
																			</td>
																			<td><bean:write name="detVisitsIter" property="relationship"/>
																			</td>
																			<td><bean:write name="detVisitsIter" property="idType"/>
																			</td>
																		</tr>
											      					</logic:iterate>
										      					</table>
															</td>
														</tr>
								      				</logic:notEmpty>
											 
											</table>
									</jims2:isAllowed>
<%-- Detention Visitation end --%>
								</td>
<%-- end second major column --%>
							</tr>
						</table>
<%-- guardian start --%>
  						<table width="99%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
  							<tr class='facDetailHead'>
  							
								<td align='left'>Guardian Information
									<logic:empty name="juvenileBriefingDetailsForm" property="guardians">
								 		(No Guardian Information)
									</logic:empty>
								</td>
								<td align="right">
  							 		<a onclick="spinner()" href="javascript:goNav('/<msp:webapp/>displayFamilyConstellationList.do?juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>');">Family Constellation</a>
  								</td>
							</tr>
								
							<logic:notEmpty name="juvenileBriefingDetailsForm" property="guardians">
								<tr>
									<td colspan="6">
										<table width="100%" cellpadding="2" cellspacing="1" class='borderTableBlue'>
											<tr class='formDeLabel'>
												<td></td>
												<td></td>
												<td><bean:message key="prompt.name"/></td>
												<td><bean:message key="prompt.relationship"/></td>
												<td><bean:message key="prompt.member"/>#</td>
												<td nowrap="nowrap"><bean:message key="prompt.oln"/></td>
												<td><bean:message key="prompt.dob"/></td>
												<td><bean:message key="prompt.dh"/></td>
												<td><bean:message key="prompt.visit"/></td>
												<td><bean:message key="prompt.language"/></td>
												</tr>
								 
			      						<logic:iterate name="juvenileBriefingDetailsForm" property="guardians" id="juvGuardiansIter" indexId="indexer">      			
											<tr class="<% out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
											   <td>
													<logic:equal  name="juvGuardiansIter" property="inHomeStatus" value="true">
														<img title='home' src='/<msp:webapp/>images/home-s.jpg' />
													</logic:equal>
												</td>
												<td>
													<logic:equal  name="juvGuardiansIter" property="primaryContact" value="true">
														<img title='primary contact' src='/<msp:webapp/>images/starBlueIcon.gif' />
													</logic:equal>
												</td>
												<td>
													<bean:write name="juvGuardiansIter" property="nameOfPerson.formattedName"/>
												</td>
												<td><bean:write name="juvGuardiansIter" property="relationshipType"/></td>
												<td>
													<a onclick="spinner()" href="/<msp:webapp/>displayFamilyMemberDetails.do?fromDashboard=viewMemberDetails&submitAction=Details&selectedValue=<bean:write name='juvGuardiansIter' property='memberNum'/>&clearFamAction=true&juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>">
														<bean:write name="juvGuardiansIter" property="memberNum"/>
													</a>														
												</td>
												
												<td>
												 <logic:notEqual name="juvGuardiansIter" property="driverLicenceNumber" value="">																		 																			  
													<bean:write name="juvGuardiansIter" property="driverLicenceNumber"/><bean:write name="juvGuardiansIter" property="driverLicenseState"/>	 
												 </logic:notEqual>
												 <logic:equal name="juvGuardiansIter" property="driverLicenceNumber" value="">
												 	<bean:write name="juvGuardiansIter" property="stateIssuedIdNum"/><bean:write name="juvGuardiansIter" property="stateIssuedIdState"/>
												 </logic:equal>
												</td>
												<td><bean:write name="juvGuardiansIter" property="dob"/></td>
												<td>
													<logic:equal name="juvGuardiansIter" property="detentionHearing" value="true"> YES</logic:equal>
													<logic:notEqual name="juvGuardiansIter" property="detentionHearing" value="true"> NO</logic:notEqual>
												</td>
												<td>
													<logic:equal name="juvGuardiansIter" property="visit" value="true"> YES</logic:equal>
													<logic:notEqual name="juvGuardiansIter" property="visit" value="true"> NO</logic:notEqual>
												</td>	
												<td><bean:write name="juvGuardiansIter" property="language"/></td>
											</tr>
										</logic:iterate>
									</table>
								</td>
							</tr>
						</logic:notEmpty>
  					</table>
  					<%-- guardian end --%>
					<div class='spacer'/>
					<%--button start --%>
						<table border="0" width="100%">
							<tr>
								<td align="center">
								<jims2:isAllowed requiredFeatures="<%=Features.JFA_DETAILS_UPDATE%>">  
									<%--If there is no JUVENILE_FACIILTY_ADMISSION_HEADER record associated to the juvenile # and at least one casefile is active, display option to Admit. --%>
									<logic:equal name="juvenileBriefingDetailsForm" property="headerInfo.juvenileNumber" value="">
										<logic:equal name="juvenileBriefingDetailsForm" property="hasActiveCasefiles" value="true">	
											<input id="btnAdmit" type="button" onclick="goNav('/<msp:webapp/>displayJuvenileFacilityAdmit.do?submitAction=Admit');" value="<bean:message key='button.admit'/>"/>
										</logic:equal>
									</logic:equal>
									<%--If the JUVENILE_FACIILTY_ADMISSION_HEADER.FacilityStatus is not populated and JUVENILE_FACIILTY_ADMISSION_RELEASE. ReleaseTo = €˜TRN™, display option to Admit.--%>
									<%--If the JUVENILE_FACIILTY_ADMISSION_HEADER.FacilityStatus is not populated and JUVENILE_FACIILTY_ADMISSION_RELEASE. ReleaseTo is not €˜TRN€™, display options to Admit and Modify. --%>
									<logic:notEqual name="juvenileBriefingDetailsForm" property="headerInfo.juvenileNumber" value="">
									<logic:equal name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="">	
									<logic:equal name="juvenileBriefingDetailsForm" property="hasActiveCasefiles" value="true">	
										<input id="btnAdmit" type="button" onclick="goNav('/<msp:webapp/>displayJuvenileFacilityAdmit.do?submitAction=Admit');" value="<bean:message key='button.admit'/>"/>
										<%-- user-story 22926 fix
										<logic:notEqual name="juvenileBriefingDetailsForm" property="admissionInfo.releaseTo" value="TRN">
											<input id="btnModify" type="button" name="modifyAdmit" onclick="goNav('/<msp:webapp/>displayJuvenileFacilityModifyAdmit.do?submitAction=Modify Admit Record');" value="<bean:message key='button.modifyAdmit'/>" />	
										</logic:notEqual> --%>
									</logic:equal>
									</logic:equal>
									</logic:notEqual>
									<%--If the JUVENILE_FACIILTY_ADMISSION_HEADER.FacilityStatus = N (In Transfer), display option to Admit.  Display on-screen notice:  Juvenile currently In Transfer from {JUVENILE_FACILITY_ADMISSION_RELEASE. DetainedFacility} --%>
									<logic:equal  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="N">	
										<input id="btnAdmit" type="button" onclick="goNav('/<msp:webapp/>displayJuvenileFacilityAdmit.do?submitAction=Admit');" value="<bean:message key='button.admit'/>"/>									
									</logic:equal>
									<%--If the JUVENILE_FACIILTY_ADMISSION_HEADER.FacilityStatus = T€™ (temporary release), display option to Modify or Return Temp Release.   Display on-screen notice: Juvenile has been Temporarily Released from {JUVENILE_FACILITY_ADMISSION_RELEASE.DetainedFacility}. --%>
									<logic:equal  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="T">
									<logic:equal name="juvenileBriefingDetailsForm" property="hasActiveCasefiles" value="true">	
										<input id="btnModify" type="button" name="modifyAdmit" onclick="goNav('/<msp:webapp/>displayJuvenileFacilityModifyAdmit.do?submitAction=<bean:message key='button.modifyAdmit'/>');" value="<bean:message key='button.modifyAdmit'/>" />
										<input id="btnTempRelease" type="button" value="<bean:message key='button.returnTempRelease'/>" name="retTempRelease" onclick="goNav('/<msp:webapp/>displayJuvenileFacilityRelease.do?submitAction=<bean:message key='button.returnTempRelease'/>');"/>
									</logic:equal>
									</logic:equal>
									
									<%--If the JUVENILE_FACIILTY_ADMISSION_HEADER.FacilityStatus = €˜D (Detained in Detention facility), ˜P€™ (Placement) or €˜V (Diversion program) or R (Returned to facility after Escape), display on-screen notice: Juvenile currently in {JUVENILE_FACILITY_ADMISSION_HEADER.FacilityCode}.  
										Display the options to Transfer a.k.a. €œIn Transfer,€ Relocation, Modify, Temporarily Release, Escape, or Release. In the legacy application, a user can access the Admit option even if juvenile is currently in a facility; however, one of several notices display on the admission screen informing the user: a) Admission is not possible until juvenile is released from current facility or b) juvenile needs to be admitted into a different facility].--%>
									<logic:notEqual  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="">	
									<jims2:if name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="D" op="equal">
									<jims2:or name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="P" op="equal"/>
									<jims2:or name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="V" op="equal"/>
									<jims2:or name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="R" op="equal"/>
									<jims2:or name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="Q" op="equal"/>
									<jims2:then>  
									<logic:equal name="juvenileBriefingDetailsForm" property="hasActiveCasefiles" value="true">	
										<input id="btnModify" type="button" name="modifyAdmit" onclick="goNav('/<msp:webapp/>displayJuvenileFacilityModifyAdmit.do?submitAction=Modify Admit Record');" value="<bean:message key='button.modifyAdmit'/>" />
										<input id="btnEscape" type="button" name="escape" onclick="goNav('/<msp:webapp/>displayJuvenileFacilityRelease.do?submitAction=<bean:message key='button.escape'/>');" value="<bean:message key='button.escape'/>" />
										<input id="btnRelease" type="button" name="release" onclick="goNav('/<msp:webapp/>displayJuvenileFacilityRelease.do?submitAction=<bean:message key='button.release'/>');" value="<bean:message key='button.release'/>"/>         
										<logic:equal name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="D">	
										<input id="btnTransfer" type="button" name="transfer"  onclick="goNav('/<msp:webapp/>displayJuvenileFacilityRelease.do?submitAction=<bean:message key='button.facilityTransfer'/>');" value="<bean:message key='button.preTransferDetained'/>" />
										</logic:equal>	
										<%-- TEMP RELEASE Precondition:   The JUVENILE_FACIILTY_ADMISSION_HEADER.FacilityStatus = D (detention), P (placement), or V (diversion).   --%>
										<logic:notEqual  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="R">	
											<logic:equal name="juvenileBriefingDetailsForm" property="hasActiveCasefiles" value="true">
												<input id="btnTempRelease" type="button" name="tempRelease" onclick="goNav('/<msp:webapp/>displayJuvenileFacilityRelease.do?submitAction=<bean:message key='button.tempRelease'/>');" value="<bean:message key='button.tempRelease'/>" />
											</logic:equal>
										</logic:notEqual>
										<jims2:isAllowed requiredFeatures="<%=Features.JFA_TEMPORARY_RELEASE_DECISION%>"> 
											<jims2:if name="juvenileBriefingDetailsForm" property="isCourtSettingToday" value="yes" op="equal">
											<jims2:or name="juvenileBriefingDetailsForm" property="isDistCourtSettingToday" value="yes" op="equal"/>
												<jims2:then>
													<jims2:if name="juvenileBriefingDetailsForm" property="tempRelDecisionEnabled" value="true" op="equal">
													<jims2:or name="juvenileBriefingDetailsForm" property="tempRelDecisionDistEnabled" value="true" op="equal"/>
														<jims2:then>
																<input id="btnTempReleaseDecision" type="button" name="tempReleaseDecision" onclick="goNav('/<msp:webapp/>displayJuvenileFacilityRelease.do?submitAction=<bean:message key='button.tempReleaseDecision'/>');" value="<bean:message key='button.tempReleaseDecision'/>"/>
														</jims2:then>
													</jims2:if>
												</jims2:then>
											</jims2:if>		
										</jims2:isAllowed>
											<!-- add the conditions here to show only one button if both present -->
											<%-- <jims2:isAllowed requiredFeatures="<%=Features.JFA_TEMPORARY_RELEASE_DECISION%>"> 
												<logic:equal name="juvenileBriefingDetailsForm" property="isCourtSettingToday" value="yes">
													<logic:equal name="juvenileBriefingDetailsForm" property="tempRelDecisionEnabled" value="true">
														<input id="btnTempReleaseDecision" type="button" name="tempReleaseDecision" onclick="goNav('/<msp:webapp/>displayJuvenileFacilityRelease.do?submitAction=<bean:message key='button.tempReleaseDecision'/>');" value="<bean:message key='button.tempReleaseDecision'/>"/>
													</logic:equal>													
												</logic:equal>
												<logic:equal name="juvenileBriefingDetailsForm" property="isDistCourtSettingToday" value="yes">
												<logic:equal name="juvenileBriefingDetailsForm" property="tempRelDecisionDistEnabled" value="true">
													<input id="btnTempReleaseDecision" type="button" name="tempReleaseDecision" onclick="goNav('/<msp:webapp/>displayJuvenileFacilityRelease.do?submitAction=<bean:message key='button.tempReleaseDecision'/>');" value="<bean:message key='button.tempReleaseDecision'/>"/>
												</logic:equal>
												</logic:equal>
											</jims2:isAllowed> --%>
										
										
										<jims2:isAllowed requiredFeatures="<%=Features.JFA_BOOKING_TRANSFER%>">  
											<input id="btnRefTransfer" type="button" name="refTransfer" value="<bean:message key='button.refTransfer'/>" />
										</jims2:isAllowed>
									</logic:equal>
									</jims2:then>
									</jims2:if>
									</logic:notEqual>
									<%--If the JUVENILE_FACIILTY_ADMISSION_HEADER.FacilityStatus = E (escape), display option to Return Escapee or Modify.  display on-screen notice:  Juvenile is listed as { JUVENILE_FACIILTY_ADMISSION_HEADER.EscapeFrom} --%>
									<logic:equal  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="E">	
										<logic:equal name="juvenileBriefingDetailsForm" property="hasActiveCasefiles" value="true">	
											<input id="btnModify" type="button" name="modifyAdmit" onclick="goNav('/<msp:webapp/>displayJuvenileFacilityModifyAdmit.do?submitAction=<bean:message key='button.modifyAdmit'/>');" value="<bean:message key='button.modifyAdmit'/>" />
											<input id="btnRetEscape" type="button" name="returnEscape" onclick="goNav('/<msp:webapp/>displayJuvenileFacilityRelease.do?submitAction=<bean:message key='button.returnEscapee'/>');" value="<bean:message key='button.returnEscapee'/>"/>
											<jims2:isAllowed requiredFeatures="<%=Features.JFA_BOOKING_TRANSFER%>">  
												<input id="btnRefTransfer" type="button" name="refTransfer" value="<bean:message key='button.refTransfer'/>" />
											</jims2:isAllowed>
										</logic:equal>
									</logic:equal>
									<%--Added for U.S #51449 --%>
									<br/><br/>
									<logic:notEqual  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="">	  
										<input id="generateFinalBtn" type="button" name="generateFinal"  value="<bean:message key='button.generateFinal'/>"/>
									</logic:notEqual>
									<%--Added for U.S #51449 --%>
									</jims2:isAllowed>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		 <div class='spacer'></div>
		</td>
	</tr>
</table>
<%-- END  TABLE --%>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>