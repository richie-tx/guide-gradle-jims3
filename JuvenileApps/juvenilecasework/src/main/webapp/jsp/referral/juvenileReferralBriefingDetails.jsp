<!DOCTYPE HTML>

<%-- Used for juvenile profile in MJCW -Process Referrals --%>
<%--MODIFICATIONS --%>
<%-- NMathew  08/07/2018	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!-- Changes for JIMS200077276 Starts -->
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<!-- Changes for JIMS200077276 ends -->
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

<html:base />
<title><bean:message key="title.heading" /> - juvenileReferralBriefingDetails.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- STRUTS VALIDATIONS--%>
<html:javascript formName="juvenileProfileSearchForm" />

<%-- Javascript for emulated navigation --%>
<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/casefileSearch.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/referral/referral.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/referral/juvReferralBriefing.js"></script>

<script type="text/javascript"> 

function casefileCallback(tForm)
{
	<logic:notEqual name="juvenileBriefingDetailsForm" property="fromProfile" value="profileBriefingDetails">
		<logic:notEqual name="juvenileBriefingDetailsForm" property="currentCasefile.caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING%>">	 	
		  goNav("/<msp:webapp/>displayJuvenileCasefileDetails.do?supervisionNum=<bean:write name='juvenileBriefingDetailsForm' property='supervisionNum'/>");
	  </logic:notEqual>
	</logic:notEqual> 
}
$(function() { 
	$("#referralLink").click(function(){		
		 var webApp = "/<msp:webapp/>";
		 var juvNum = '<bean:write name="juvenileBriefingDetailsForm" property="juvenileNum"/>';
		 var url = webApp + "displayJuvenileProfileReferralList.do?submitAction=Link&juvnum=" + juvNum +"&testAction=fromProcessReferral";
		 window.myVariable=juvNum;
		 var referralWindow = window.open(url, "referralWindow", "height=500,width=800,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
		 referralWindow.focus();	
		localStorage.setItem("referralWin", "open");
		localStorage.setItem("juvnum", juvNum);
		return false;		
		});
 });
$(document).ready(function () {
	
	 var webApp = "/<msp:webapp/>";
	 var juvNum = '<bean:write name="juvenileBriefingDetailsForm" property="juvenileNum"/>';	
	 var url = webApp + "displayJuvenileProfileReferralList.do?submitAction=Link&juvnum=" +juvNum;	
	var referralWinStatus = localStorage.getItem("referralWin");	
	var juvInWindow = localStorage.getItem("juvnum");	
	if(referralWinStatus == "open")	{	
		
		if(juvInWindow!= null && juvInWindow == juvNum)
			return false;
		//window is open just refresh it
		 var referralWindow = window.open(url, "referralWindow", "height=500,width=800,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
		 localStorage.setItem("referralWin", "open");
		 referralWindow.focus();		
		//return false;
	}
	
	
});
$(document).ready(function(){	
	$("#btnUpdateReferral").click(function(){
		spinner();
	})
	
})
</script>
</head>
<%-- ANY changes made to this page, please check if it is relevant to juvenileReferralBriefingDetailsPOPUP.jsp --%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<%--BEGIN FORM TAG--%>
<html:form action="/processReferralBriefing.do" target="content" focus="juvenileNum">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|162">       

<%-- BEGIN HEADING TABLE --%>
</br>
<table width='100%'>
  <tr>
    <td align="center" class="header"> Referral Briefing Details</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>
</br>
<%-- BEGIN INSTRUCTION TABLE --%>

<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>


<%-- BEGIN INQUIRY TABLE --%>

<%-- END INQUIRY TABLE --%>

<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
		<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
			<tr>
				<td class="referralDetailHead" nowrap align = "left">&nbsp;Referral Briefing&nbsp;
					<a href="mailto:Data.corrections@hcjpd.hctx.net">Email Data Corrections</a>  &nbsp;
					<a href="#" id="referralLink">Referral History</a>
					<%-- <a href="javascript:newCustomWindow('/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Referrals&juvnum=
						     <bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>','viewDetails',1000,800)">  Referral History</a>--%>
					<jims2:isAllowed requiredFeatures='<%=Features.JCW_SEARCHFACPROF%>'>
						&nbsp;	<a onclick="spinner();" href="/<msp:webapp/>displayJuvenileProfileSearchResults.do?isFacility=true&juvenileNum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>&searchType=JNUM">Facility Briefing</a>
						&nbsp;&nbsp;
					</jims2:isAllowed>
					<jims2:isAllowed requiredFeatures='<%=Features.JCW_CASEWORKBRIEFINGDETAILSSCREEN%>'>
         			  	<a onclick="spinner()" href="/<msp:webapp/>displayJuvenileBriefingDetails.do?submitAction=Link&fromProfile=profileBriefingDetails&juvenileNum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>">Casework Briefing</a>&nbsp;&nbsp;
         			 </jims2:isAllowed>
					<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTACTVTYYOUTH%>'>	
						<a style="float:right;" onclick="spinner();" href="javascript:goNav('/<msp:webapp/>processJuvenileDistrictCourtHearings.do?submitAction=courtActivityByYouth&juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>');">Court Activity</a>
					</jims2:isAllowed>
				</td>			
			</tr>
		</table>
		
		<%-- BEGIN INFORMATION TABLE --%>
		<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
			<tr>
				<td width='50%' valign="top">
					<%--general info start --%>
						<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
							<tr class='referralDetailHead'>
								<td colspan= '1' class='paddedFourPix' align='left' nowrap='nowrap'>Juvenile Information</td>
								<td colspan= '1' align='left' nowrap='nowrap'>Master Status: 
									<span title="<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.masterStatus" />">
										<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.masterStatusId" />
									</span>
								</td>
							</tr>
							<tr>
								<td colspan="2">
 									<table width="100%" cellpadding="2" cellspacing="0" border='0'>
										<tr>
 											<td valign='top'>
												<table width="100%" cellpadding="2" cellspacing="1">
												<tr>
													<td class='formDeLabel'><bean:message key="prompt.name"/></td>
													<td class='formDe' colspan='3'>
														<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.formattedName"/>
														&nbsp;
							  							<logic:equal name="juvenileBriefingDetailsForm" property="dualStatus" value="FDS">
							  								<span style="color:purple; font-weight: bold;" title="Former Dual Status">(<bean:write name="juvenileBriefingDetailsForm" property="dualStatus"/>)</span>
							  							</logic:equal>
							  							<logic:equal name="juvenileBriefingDetailsForm" property="dualStatus" value="DS">
							  								<span style="color:purple; font-weight: bold;" title="Dual Status">(<bean:write name="juvenileBriefingDetailsForm" property="dualStatus"/>)</span>
							  							</logic:equal>
							  							&nbsp;
							  							<logic:equal name="juvenileBriefingDetailsForm" property="traitTypeId" value="MER">
							  								<span style="color:#8417A0; font-weight: bold;" title="<bean:write name="juvenileBriefingDetailsForm" property="traitTypeDescription"/>">(M)</span>
							  							</logic:equal>
														&nbsp;
														<logic:equal name="juvenileBriefingDetailsForm" property="profileDetail.recType" value="I.JUVENILE">
														&nbsp;&nbsp;&nbsp;&nbsp;<font color="Red" face="verdana"><b><span title='Purged'>P</span></b></font>
														</logic:equal>
														<logic:equal name="juvenileBriefingDetailsForm" property="profileDetail.recType" value="S.JUVENILE">
														&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Sealed'>S</span></b></font>
														</logic:equal>
														<logic:equal name="juvenileBriefingDetailsForm" property="restrictedAccessTrait" value="true"><span title='RESTRICTED ACCESS'> <font color="red"><b>(RESTRICTED)</b></font></span></logic:equal>
														<logic:equal name="juvenileBriefingDetailsForm" property="inSpecialtyCourt" value="true">
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: green; font-weight: bold; margin-left: 35px;" title="<bean:write name="juvenileBriefingDetailsForm" property="specialtyCourtDescription"/>">SC</span>
												</logic:equal>
													</td>
												</tr>
												<tr>
													<td class='formDeLabel' valign='top'><bean:message key="prompt.juvenile#"/></td>
													<td class='formDe'>
													<logic:equal name="juvenileBriefingDetailsForm" property="restrictedAccessTrait" value="true"><font color="red"><b><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.juvenileNum"/></b></font></logic:equal>															<logic:notEqual name="juvenileBriefingDetailsForm" property="restrictedAccessTrait" value="true"><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.juvenileNum"/></logic:notEqual>
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
													<td class='formDeLabel'><bean:message key="prompt.SSN"/></td>
													<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.formattedSSN" /></td>
													<td class='formDeLabel' valign='top'><bean:message key="prompt.hispanic"/></td>
													<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="hispanic"/></td>
												</tr>
												

						<%-- begin Schools section --%>
												<tr class='referralDetailHead'>
													<td colspan='4' align='center'><bean:message key="prompt.schoolInfo"/></td>
												</tr>
												<tr>
													<td class='formDeLabel'><bean:message key="prompt.school"/>&nbsp;<bean:message key="prompt.name"/></td>
													<logic:notEmpty name="juvenileBriefingDetailsForm" property="school.specificSchoolName">
													<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="school.specificSchoolName"/></td>
	  												</logic:notEmpty>
	  												<logic:empty name="juvenileBriefingDetailsForm" property="school.specificSchoolName">
	  												<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="school.school"/></td>
	  												</logic:empty>
												</tr>
	  											<tr>
	  												<td class='formDeLabel'><bean:message key="prompt.school"/>&nbsp;<bean:message key="prompt.district"/>&nbsp;<bean:message key="prompt.name"/></td>
	  												<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="school.schoolDistrict"/></td>
	  											</tr>
												<tr>
													<td class='formDeLabel'><bean:message key="prompt.gradeLevel"/></td>
													<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="school.gradeLevelDescription"/>&nbsp;
						<%-- based on the Juv's Grade Level Code, display a specific icon --%>
	  												<logic:notEmpty name="juvenileBriefingDetailsForm" property="school.appropriateLevelCode">
													<logic:equal name="juvenileBriefingDetailsForm" property="school.appropriateLevelCode" value="APP">
	  												<img src="/<msp:webapp/>images/green_check.gif" alt="Appropriate" />
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
	  											<%-- <tr> prompt.schoolAttendanceStatus
													<td class='formDeLabel'><bean:message key="prompt.enrollmentStatus"/></td>
	  												<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="school.exitTypeDescription"/></td>
	  											</tr> --%>
	  											<tr> 
													<td class='formDeLabel'><bean:message key="prompt.schoolAttendanceStatus"/></td>
	  												<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="school.schoolAttendanceStatusDescription"/></td>
	  											</tr>
												<tr>
	  												<td class='formDeLabel'><bean:message key="prompt.programAttending"/></td>
													<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="school.programAttendingDescription" /></td>
												</tr>
						<%-- end School section --%>																
						<%-- begin Residential section --%>	
										<logic:empty name="juvenileBriefingDetailsForm" property="memberAddress">
											<tr class='referralDetailHead'>
												<td class='paddedFourPix' colspan='4'>Residential Information
													<logic:empty name="juvenileBriefingDetailsForm" property="juvAddress">
													(No Residential Information)
													</logic:empty>
												</td>
											</tr>
										</logic:empty>
												<logic:notEmpty name="juvenileBriefingDetailsForm" property="memberAddress">														
	  											<tr class='referralDetailHead'>
													<td colspan='4' align='center'><bean:message key="prompt.residential"/> <bean:message key="prompt.info"/></td>
	  											</tr>
		  										<tr>
													<td class='formDeLabel'><bean:message key="prompt.address"/></td>
													<td class='formDe' colspan='3'>
		  											<div>
		  											<bean:write name="juvenileBriefingDetailsForm" property="memberAddress.streetAddress"/>
												<%-- based on the Address validation, display a specific icon --%>
			  										<logic:notEmpty name="juvenileBriefingDetailsForm" property="memberAddress.validated">
														<logic:equal name="juvenileBriefingDetailsForm" property="memberAddress.validated" value="Y">
			  												&nbsp;<img src="/<msp:webapp/>images/green_check.gif" alt="Appropriate" />
														</logic:equal>
														<logic:equal name="juvenileBriefingDetailsForm" property="memberAddress.validated" value="N">
		  													&nbsp;<img src="/<msp:webapp/>images/red_x.gif" alt="Unverified" />
														</logic:equal>
			 										</logic:notEmpty>		  																	
		  											</div>
		  											<logic:notEqual name="juvenileBriefingDetailsForm" property="memberAddress.cityStateZip" value="">
		  												<div>
		  												<bean:write name="juvenileBriefingDetailsForm" property="memberAddress.cityStateZip"/>
		  												</div>	
		  											</logic:notEqual>
		  											</td>
												</tr>
												<tr>
													<td class='formDeLabel'><bean:message key="prompt.county"/></td>
		  											<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="memberAddress.county"/></td>
		  										</tr>
		  										<tr>
													<td class='formDeLabel'>Phone</td>
		  											<td class='formDe'>
		  											<bean:write name="juvenileBriefingDetailsForm" property="memberContact.contactPhoneNumber.formattedPhoneNumber"/>
		  											<logic:notEmpty name="juvenileBriefingDetailsForm" property="memberContact.contactPhoneNumber.ext">
		  												Ext <bean:write name="juvenileBriefingDetailsForm" property="memberContact.contactPhoneNumber.ext"/>
		  											</logic:notEmpty>
		  											</td>
		  											<td class='formDeLabel' width='1%' nowrap='nowrap'>Priority</td>
		  											<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="memberContact.primaryInd"/></td>
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
													</logic:notEqual>
		  										</tr>
		  										</logic:notEmpty>
		  										<logic:empty name="juvenileBriefingDetailsForm" property="memberAddress">
		  											<logic:notEmpty name="juvenileBriefingDetailsForm" property="juvAddress">
		  											<tr>
		  												<td class='formDeLabel'><bean:message key="prompt.address"/></td>
														<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="juvAddress"/></td>	
														<tr>
															<td class='formDeLabel'><bean:message key="prompt.county"/></td>
				  											<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="juvCounty"/></td>
				  										</tr>														
		  											</tr>
		  											</logic:notEmpty>
		  										</logic:empty>
		  										<%-- END Residential section --%>
												<%-- COMMENTS section US 80124--%>	
		  										<logic:notEmpty name="juvenileBriefingDetailsForm" property="profileDetail.comments">	
													<tr class='referralDetailHead'>
														<td colspan='4' align='center'><bean:message key="prompt.comments"/></td>
													</tr>
													<tr class='formDe' colspan='3'>
						  								<td>
						  								<div><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.comments"/></div>
						  								</td>
						  							</tr>	
						  							</logic:notEmpty>
						  							<logic:empty name="juvenileBriefingDetailsForm" property="profileDetail.comments">
														<tr class='referralDetailHead'>
															<td class='paddedFourPix' colspan='4'>Comments (No Comments)</td>
														</tr>
												</logic:empty>
		  										<%-- COMMENTS section ENDS 80124--%>	
		  										
		  										<%-- ReadDOB section US 171564--%>	
		  										<logic:notEmpty name="juvenileBriefingDetailsForm" property="profileDetail.realDOB">	
													<tr class='referralDetailHead'>
														<td colspan='4' align='center'>Real DOB</td>
													</tr>
													<tr class='formDe'>
														<td class='formDeLabel'>
															<span>Real Date of Birth</span>
														</td>
						  								<td colspan='3'>
						  									<div>
						  										<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.realDOB" formatKey="date.format.mmddyyyy"/>
						  									</div>
						  								</td>
						  							</tr>	
						  							</logic:notEmpty>
						  							<logic:empty name="juvenileBriefingDetailsForm" property="profileDetail.realDOB">
														<tr class='referralDetailHead'>
															<td class='paddedFourPix' colspan='4'>Real DOB</td>
														</tr>
												</logic:empty>
		  										<%-- ReadDOB section Ends - US 171564--%>
		  										
		  										<%-- Cause of Death--%>
		  										<logic:notEmpty name="juvenileBriefingDetailsForm" property="deathDate" >
			  										<tr class='referralDetailHead'>
														<td colspan='4' align='left'>Cause of Death</td>
													</tr>
													<tr>
														<td class='formDeLabel'><bean:message key="prompt.causeOfDeath"/></td>
														<logic:notEmpty name="juvenileBriefingDetailsForm" property="youthDeathReasonDesc" >
															<td class='formDe' colspan='4'><span title='<bean:write name="juvenileBriefingDetailsForm" property="youthDeathReasonDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="youthDeathReason"/></span></td>
														</logic:notEmpty>
														<logic:empty name="juvenileBriefingDetailsForm" property="youthDeathReasonDesc" >
															<td class='formDe' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="youthDeathReason"/></td>
														</logic:empty>
													</tr>
													<tr>
														<td class='formDeLabel'><bean:message key="prompt.deathVerifiedBy"/></td>
														<logic:notEmpty name="juvenileBriefingDetailsForm" property="youthDeathVerificationDesc" >
															<td class='formDe' colspan='4'><span title='<bean:write name="juvenileBriefingDetailsForm" property="youthDeathVerificationDesc"/>'><bean:write name="juvenileBriefingDetailsForm" property="youthDeathVerification"/></span></td>
														</logic:notEmpty>
														<logic:empty name="juvenileBriefingDetailsForm" property="youthDeathVerificationDesc" >
															<td class='formDe' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="youthDeathVerification"/></td>
														</logic:empty>
													</tr>
																
				 									<tr>
														<td class='formDeLabel'><bean:message key="prompt.deathDate"/></td>
				 										<td class='formDe' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="deathDate" /></td>				 										
				 									</tr>
				 									<tr>
														<td class='formDeLabel'><bean:message key="prompt.deathAge"/></td>
				 										<td class='formDe' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="deathAge"/></td>
				 									</tr>  
														
												</logic:notEmpty>
		  										
		  										<%-- Purge Details --%>
		  										<logic:notEmpty name="juvenileBriefingDetailsForm" property="purgeDate" >
			  										<tr class='referralDetailHead'>
														<td colspan='4' align='left'>Purge Details</td>
													</tr>
													<tr>
														<td class='formDeLabel'><bean:message key="prompt.purgeDate"/></td>
														<td class='formDe' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="purgeDate"/></td>
													</tr>
													<tr>
														<td class='formDeLabel'><bean:message key="prompt.purgeSeries"/></td>
														<td class='formDe' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="purgeSerNum"/></td>
													</tr>
																
				 									<tr>
														<td class='formDeLabel'><bean:message key="prompt.purgeBox"/></td>
				 										<td class='formDe' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="purgeBoxNum"/></td>
				 									</tr>
			 									</logic:notEmpty>
			 									<tr>
													<td class='formDeLabel'><bean:message key="prompt.checkedOutTo"/></td>
			 										<td class='formDe' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="checkedOutTo"/></td>
			 									</tr>  
 												<tr class="normalRow">		  						
							  						<td class='formDeLabel' width='1%' nowrap='nowrap'>Update User</td>
							  						<td class='formDe'>
							  							<logic:notEmpty name="juvenileBriefingDetailsForm" property="profileDetail.updateUser">
							  								<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.updateUser" />
							  							</logic:notEmpty>		  							
							  						</td>
							  						<td class='formDeLabel'>Update Date</td>
							  						<td class='formDe'>
							  							<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.updateDate" formatKey="date.format.mmddyyyy"/>
							  						</td>
							  					</tr>
											</table></td>
									</tr>
																							
								</table>
								</td>
						</tr>
					</table>
					</td>
					
					<td width='50%' valign="top">
					<%-- Casefiles INFO start --%>
						<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
							<tr class='referralDetailHead'>
							<td class='paddedFourPix' onclick="casefileCallback(this.form);" align="left">
								<logic:notEmpty name="juvenileBriefingDetailsForm" property="casefiles">
									<a onclick="spinner();" href="/<msp:webapp/>displayJuvenileProfileCasefileList.do?juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>">Casefiles</a>
									</logic:notEmpty>
								<logic:empty name="juvenileBriefingDetailsForm" property="casefiles">
									Casefiles	(No Casefiles)
								</logic:empty>
							</td>
							<td>JPO of Record - <span title='<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.jpoOfRecord"/>'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.jpoOfRecID"/></span></td>
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
							<td  align="left" width='10%' nowrap="nowrap">Supervision#&nbsp;</td>
							<td align="left" width='10%'>Status</td>
							<td align="left" width='40%'>Supervision Type</td>
							<td align="left" width='20%'>Expected End Date</td>
							<td align="left" width='20%'>JPO</td>
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="casefiles" name="juvenileBriefingDetailsForm" property="casefiles" indexId="indexer"> 
							<tr class="<% out.print( (indexer.intValue() % 2 == 1) ? "normalRow" : "alternateRow" ); %>">
							<logic:equal name="casefiles" property="caseStatus" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING_DESCRIPTION%>">	 	
							<td align="left"><a onclick="spinner();" href="/<msp:webapp/>handleCasefileActivation.do?submitAction=Link&casefileID=<bean:write name='casefiles' property='supervisionNum'/>&action=default"><bean:write name="casefiles" property="supervisionNum"/></a></td>
							</logic:equal>
							<logic:notEqual name="casefiles" property="caseStatus" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING_DESCRIPTION%>">	 	
							<td align="left"><a onclick="spinner();" href="/<msp:webapp/>displayJuvenileCasefileDetails.do?supervisionNum=<bean:write name='casefiles' property='supervisionNum'/>"><bean:write name="casefiles" property="supervisionNum"/></a></td>
							</logic:notEqual>	
							<td align="left"><span title="<bean:write name="casefiles" property="closedDate" formatKey="date.format.mmddyyyy"/>"><bean:write name="casefiles" property="caseStatus"/></span></td>
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
						<%-- guardian info start --%>
						<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
						<tr class='referralDetailHead'>
						<td class='paddedFourPix'>Guardian Information
						<logic:empty name="juvenileBriefingDetailsForm" property="guardians">
						<logic:empty name="juvenileBriefingDetailsForm" property="mothersName">
						<logic:empty name="juvenileBriefingDetailsForm" property="fathersName">
						<logic:empty name="juvenileBriefingDetailsForm" property="otherRelName">
							(No Guardian Information)
						</logic:empty>
						</logic:empty>
						</logic:empty>
						</logic:empty>
						</td>
						<td>Youth lives with: 
						<span title="<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.liveWithTjjdDesc" />">
									<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.liveWithTjjd"/> 
						</span>
						</td>
						</tr>
						<logic:empty name="juvenileBriefingDetailsForm" property="mothersName">
						<logic:empty name="juvenileBriefingDetailsForm" property="fathersName">
						<logic:empty name="juvenileBriefingDetailsForm" property="otherRelName">
					   	<logic:notEmpty name="juvenileBriefingDetailsForm" property="guardians">
						<tr>
							<td colspan="2">
							<table width="100%" cellpadding="2" cellspacing="1">
							<tr class='formDeLabel'>
							<td>Relationship</td>
							<td>Name</td>
							<td colspan="2">SSN</td>
							</tr>
							<logic:iterate name="juvenileBriefingDetailsForm" property="guardians" id="juvGuardiansIter" indexId="indexer">      			
							<tr class="<% out.print( (indexer.intValue() % 2 == 1) ?  "normalRow" : "alternateRow" );%>">
							<td nowrap='nowrap'><bean:write name="juvGuardiansIter" property="relationshipType"/></td>
							<td>
								<bean:write name="juvGuardiansIter" property="nameOfPerson.formattedName"/>
								<%-- <logic:equal  name="juvGuardiansIter" property="inHomeStatus" value="true">
									<img title='home' src='/<msp:webapp/>images/home-s.jpg' />
								</logic:equal>
								<logic:equal  name="juvGuardiansIter" property="primaryContact" value="true">
									<img title='primary contact' src='/<msp:webapp/>images/starBlueIcon.gif' />
								</logic:equal> --%>
								<logic:equal  name="juvGuardiansIter" property="incarcerated" value="true">
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="Red" face="verdana"><b><span title='Incarcerated'>I</span></b></font>
								</logic:equal>
								<logic:equal  name="juvGuardiansIter" property="deceased" value="true">
								&nbsp;&nbsp;&nbsp;&nbsp;<font color = "Red"><span title='Deceased'>D</span></font>
								</logic:equal>
							</td>
							
							<td colspan='2' nowrap='nowrap'>
								<bean:write name="juvGuardiansIter" property="SSN"/>
							</td>
							</tr>
							<tr class="<% out.print( (indexer.intValue() % 2 == 1) ?  "normalRow" : "alternateRow" );%>">
		  						<td class='formDeLabel' width='20%'><bean:message key="prompt.address"/></td>
								<td class='formDe' colspan='3'>
		  						<div>
		  						<logic:notEmpty name="juvGuardiansIter" property="guardianAddress.streetAddress">
		  						<bean:write name="juvGuardiansIter" property="guardianAddress.streetAddress"/>
					<%-- based on the Address validation, display a specific icon --%>
			  					<logic:notEmpty name="juvGuardiansIter" property="guardianAddress.validated">
								<logic:equal name="juvGuardiansIter" property="guardianAddress.validated" value="Y">
			  						&nbsp;<img src="/<msp:webapp/>images/green_check.gif" alt="Appropriate" />
								</logic:equal>
								<logic:equal name="juvGuardiansIter" property="guardianAddress.validated" value="N">
		  							&nbsp;<img src="/<msp:webapp/>images/red_x.gif" alt="Unverified" />
								</logic:equal>
			 					</logic:notEmpty>	
			 					</logic:notEmpty>	  																	
		  						</div>
		  						<logic:notEqual name="juvGuardiansIter" property="guardianAddress.cityStateZip" value="">
		  						<div>
		  						<bean:write name="juvGuardiansIter" property="guardianAddress.cityStateZip"/>
		  						</div>	
		  						</logic:notEqual>
		  														
		  						</td>	
		  					</tr>	
		  					<tr class="<% out.print( (indexer.intValue() % 2 == 1) ?  "normalRow" : "alternateRow" );%>">
			  					<td class='formDeLabel'><bean:message key="prompt.county"/></td>
		  						<td class='formDe' colspan='3'><bean:write name="juvGuardiansIter" property="guardianAddress.county"/></td>
		  					</tr>
		  					<tr class="<% out.print( (indexer.intValue() % 2 == 1) ?  "normalRow" : "alternateRow" );%>">
		  						<td class='formDeLabel'>Phone</td>
		  						<td class='formDe'>
		  							<bean:write name="juvGuardiansIter" property="guardianPhone.contactPhoneNumber.formattedPhoneNumber"/>
		  								<logic:notEmpty name="juvGuardiansIter" property="guardianPhone.contactPhoneNumber.ext">
		  									Ext <bean:write name="juvGuardiansIter" property="guardianPhone.contactPhoneNumber.ext"/>
		  								</logic:notEmpty>
		  						</td>
		  						<td class='formDeLabel' width='1%' nowrap='nowrap'>Priority</td>
		  						<td class='formDe'><bean:write name="juvGuardiansIter" property="guardianPhone.primaryInd"/></td>
		  					</tr>
		  												
		  					
		  					<tr class="<% out.print( (indexer.intValue() % 2 == 1) ?  "normalRow" : "alternateRow" );%>">
		  						<td class='formDeLabel'><bean:message key="prompt.type"/></td>
		  						<td class='formDe'><bean:write name="juvGuardiansIter" property="guardianPhone.contactType" /></td>
		  						<td class='formDeLabel'><bean:message key="prompt.unknown"/></td>
		  						<logic:equal name="juvGuardiansIter" property="guardianPhone.contactPhoneNumber.formattedPhoneNumber" value="000-000-0000">
								<td class='formDe'>Yes</td>
								</logic:equal>
								<logic:notEqual name="juvGuardiansIter" property="guardianPhone.contactPhoneNumber.formattedPhoneNumber" value="000-000-0000">
								<td class='formDe'></td>
								</logic:notEqual>
		  					</tr>		  					
						</logic:iterate>
							
						</table>
						</td>
					</tr>
							
							
							</logic:notEmpty>
							</logic:empty>
							</logic:empty>
							</logic:empty>
							<logic:notEmpty name="juvenileBriefingDetailsForm" property="fathersName">
							<tr>
								<td>
									<table width="100%" cellpadding="2" cellspacing="1">
										<tr class='formDeLabel'>
											<td>Relationship</td>
											<td>Name</td>
											<td colspan="2">SSN</td>
										</tr>
										<tr class='formDe'>
											<td>FATHER</td>
											<td><bean:write name="juvenileBriefingDetailsForm" property="fathersName" /></td>
											<td><bean:write name="juvenileBriefingDetailsForm" property="fathersSSN" /></td>
										</tr>
										<tr>
											<td class='formDeLabel' width='20%'><bean:message key="prompt.address"/></td>
											<td class='formDe' colspan='2'><bean:write name="juvenileBriefingDetailsForm" property="fathersAddress"/></td>
										</tr>	
										<tr>
											<td class='formDeLabel' width='20%'><bean:message key="prompt.phoneNo"/></td>
											<td class='formDe' colspan='2'><bean:write name="juvenileBriefingDetailsForm" property="fathersPhone"/></td>
										</tr>	
									</table>
								</td>
							</tr>
							</logic:notEmpty>
							<logic:notEmpty name="juvenileBriefingDetailsForm" property="mothersName">
							<tr>
								<td>
									<table width="100%" cellpadding="2" cellspacing="1">
										<tr class='formDeLabel'>
											<td>Relationship</td>
											<td>Name</td>
											<td colspan="2">SSN</td>
										</tr>
										<tr class = 'formDe'>
											<td>MOTHER</td>
											<td><bean:write name="juvenileBriefingDetailsForm" property="mothersName"/></td>
											<td><bean:write name="juvenileBriefingDetailsForm" property="mothersSSN"/></td>
										</tr>
										<tr>
											<td class='formDeLabel' width='20%'><bean:message key="prompt.address"/></td>
											<td class='formDe' colspan='2'><bean:write name="juvenileBriefingDetailsForm" property="mothersAddress"/></td>
										</tr>
										<tr>
											<td class='formDeLabel' width='20%'><bean:message key="prompt.phoneNo"/></td>
											<td class='formDe' colspan='2'><bean:write name="juvenileBriefingDetailsForm" property="mothersPhone"/></td>
										</tr>		
									</table>
								</td>
							</tr>	
							</logic:notEmpty>
							<logic:notEmpty name="juvenileBriefingDetailsForm" property="otherRelName">
							<tr>
								<td>
									<table width="100%" cellpadding="2" cellspacing="1">
										<tr class='formDeLabel'>
											<td>Relationship</td>
											<td>Name</td>
											<td colspan="2">SSN</td>
										</tr>
										<tr class = 'formDe'>
											<td>OTHER</td>
											<td><bean:write name="juvenileBriefingDetailsForm" property="otherRelName"/></td>
											<td><bean:write name="juvenileBriefingDetailsForm" property="otherRelSSN"/></td>
										</tr>
										<tr>
											<td class='formDeLabel' width='20%'><bean:message key="prompt.address"/></td>
											<td class='formDe' colspan='2'><bean:write name="juvenileBriefingDetailsForm" property="otherRelAddress"/></td>
										</tr>
										<tr>
											<td class='formDeLabel' width='20%'><bean:message key="prompt.phoneNo"/></td>
											<td class='formDe' colspan='2'><bean:write name="juvenileBriefingDetailsForm" property="otherRelPhone"/></td>
										</tr>	
									</table>
								</td>
							</tr>	
							</logic:notEmpty>
							</table>
						<%-- guardian end --%>
						<div class='spacer'></div>
							</td>
							</tr>
							</table>
<html:hidden styleId="hascasefiles" name="juvenileBriefingDetailsForm" property="hasCasefiles"/>		
		<%-- END INFORMATION TABLE --%>
<%-- END DETAIL TABLE --%>
<%-- BEGIN BUTTON TABLE --%>
<%-- <logic:notEqual name="juvenileBriefingDetailsForm" property="profileDetail.recType" value="I.JUVENILE">
<logic:notEqual name="juvenileBriefingDetailsForm" property="profileDetail.recType" value="S.JUVENILE"> --%>
<table align="center" border="0" width="100%">
<tr><td></td></tr>
	<tr>
		<td align="center">
		  <logic:equal name="juvenileBriefingDetailsForm" property="hasActiveOrClosingPendingCasefiles" value="false">
		  <jims2:isAllowed requiredFeatures='<%=Features.JCW_UPDATEJUVRECORD%>'>
		  	<html:submit property="submitAction" onclick="spinner()" styleId="updateJuvBtn" > <bean:message key="button.updateJuvenileRecord" /></html:submit>&nbsp;
		  </jims2:isAllowed>
		  </logic:equal>
		   <logic:equal name="juvenileBriefingDetailsForm" property="hasActiveOrClosingPendingCasefiles" value="true">
		   	 <jims2:isAllowed requiredFeatures='<%=Features.JCW_UPDATEJUVRECWITHCASEFILES%>'>
		  			<html:submit property="submitAction" onclick="spinner()" styleId="updateJuvBtn" > <bean:message key="button.updateJuvenileRecord" /></html:submit>&nbsp;
		 	 </jims2:isAllowed>
		   </logic:equal>
		  <jims2:isAllowed requiredFeatures='<%=Features.JCW_CREATEREFERRAL%>'>
		  	<html:submit property="submitAction" onclick="spinner()" styleId="createRefBtn"> <bean:message key="button.createReferral" /></html:submit>&nbsp;
		  </jims2:isAllowed>
		  
		  
		  <logic:equal name="juvenileBriefingDetailsForm" property="hasAnyReferrals" value="true">
			  <logic:notEqual name="juvenileBriefingDetailsForm" property="profileDetail.masterStatusId" value="C">
				  <jims2:isAllowed requiredFeatures='<%=Features.JCW_UPDATEREFERRAL%>'>	
			  		  <input type="button" id="btnUpdateReferral"
			  		  name="submitAction" value="<bean:message key="button.updateReferral"/>" 
									onclick="goNav('/<msp:webapp/>processReferralBriefing.do?submitAction=Update Referral&updateAction=updateReferral')">
			  	  </jims2:isAllowed>
		  	  </logic:notEqual>
	  	  </logic:equal>
	  	  
	  	  
		  <logic:equal name="juvenileBriefingDetailsForm" property="hasActiveReferrals" value="true">
	  		   <jims2:isAllowed requiredFeatures='<%=Features.JCW_MANAGEASSIGNMENT%>'>
	 		 	 <html:submit property="submitAction" onclick="spinner()" styleId="manageAssignBtn"><bean:message key="button.manageAssignment"></bean:message></html:submit>
	  		  </jims2:isAllowed>
	  		  <jims2:isAllowed requiredFeatures='<%=Features.JCW_OVERRIDEASSIGNMENT%>'>
	  		 	 <html:submit property="submitAction" onclick="spinner()" styleId="overRideBtn"><bean:message key="button.overrideAssignment"></bean:message>
	  		 </html:submit>
	  		  </jims2:isAllowed>
	  	</logic:equal>
		</td>
	</tr>
</table>
<%-- </logic:notEqual>
</logic:notEqual> --%>
	<table align="center" border="0" width="100%">
	<tr><td></td></tr>
	<tr>
		<td>	 
			 <jims2:isAllowed requiredFeatures='<%=Features.JCW_CREATERESEAL%>'>
		  		<html:submit property="submitAction" onclick="spinner()"> <bean:message key="button.createReseal" /></html:submit>&nbsp;
		 	 </jims2:isAllowed>

		 	 <jims2:isAllowed requiredFeatures='<%=Features.JCW_PRINTMASTERDISPLAY%>'>
		  		<html:submit property="submitAction"> <bean:message key="button.printMasterDisplay" /></html:submit>&nbsp;
		 	 </jims2:isAllowed>
		 	
	 	 </td>
 	</tr>
	</table>  
<%-- END BUTTON TABLE --%>
<br>
</html:form>

<br>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
