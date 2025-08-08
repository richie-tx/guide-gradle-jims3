<!DOCTYPE HTML>
<%-- 12/22/2008  	C Shimek	defect#56140 revised warrant number href to include secondaryAction parameter --%>
<%-- 04/30/2012		C Shimek	#73346 Revise hardcoded TYC prompts to TJJD --%>
<%-- 10/24/2012 	DGibler		#73746 MJCW: Add Street Number suffix field --%>
<%-- 12/10/2012     C Shimek	#74731 Added Guardian Primary Contact Indicator (blue star) in Gurardian box --%>
<%-- 04/16/2013     C Shimek	Revised 'Address' display to cleaner look, added nowrap to Current Age and gave more room to Supervision Type, found while testing #74116 --%>
<%-- 07/24/2014     C Shimek	#75854 relocated Warrant block to top of right column display --%>
<%-- 07/24/2014     C Shimek	#75859 added Facility/DET to JUvenile Information block --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>




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

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<html:base />
<title><bean:message key="title.heading"/>/juvenileBriefingDetails.jsp</title>

<%--HELP JAVASCRIPT FILE --%> 
<%-- <SCRIPT SRC="../js/help.js" /> --%>
<script type="text/javascript"> 

$(function() { 
	$("#historyLink").click(function(){		
		 var webApp = "/<msp:webapp/>";
		 var juvNum = '<bean:write name="juvenileBriefingDetailsForm" property="juvenileNum"/>';
		 window.myVariable=juvNum;
		 var url = webApp + "displayJuvenileProfileReferralList.do?submitAction=Facility&actionType=popup&juvnum=" +juvNum;
		 var facilityWindow = window.open(url, "facilityHistWin", "height=500,width=800,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
		 facilityWindow.focus();	
		localStorage.setItem("facilityHistWin", "open");
		localStorage.setItem("juvnum", juvNum);
		return false;		
		});
 });

function casefileCallback(tForm)
{
	<logic:notEqual name="juvenileBriefingDetailsForm" property="fromProfile" value="profileBriefingDetails">
		<logic:notEqual name="juvenileBriefingDetailsForm" property="currentCasefile.caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING%>">	 	
		  goNav("/<msp:webapp/>displayJuvenileCasefileDetails.do?supervisionNum=<bean:write name='juvenileBriefingDetailsForm' property='supervisionNum'/>");
	  </logic:notEqual>
	</logic:notEqual> 
}

function checkRestrictedAccess()
{
	var restrictedAccess = '<bean:write name="juvenileBriefingDetailsForm" property="restrictedAccessTrait"/>';	
	if(restrictedAccess=="true")
		alert("Information about this youth is RESTRICTED and should not be shared outside of HCJPD personnel.  Contact Data Corrections for additional information.");
}
<%-- Pact Invocation  Task 41028   &&   //# task 44099 . U.S #41378--%>
$(document).ready(function () {
	$("#pactLink").click(function(){
		$("#pactForm").submit();
		});
	$("#bannedLink").click(function(){
		 var webApp = "/<msp:webapp/>";
		 var juvNum = '<bean:write name="juvenileBriefingDetailsForm" property="juvenileNum"/>';	
		 var url = webApp + "displayJuvenileProfileTraitsSearch.do?submitAction=Detention&juvenileNum=" +juvNum;
		 var newWin = window.open(url, "pictureWindow", "height=500,width=800,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
			newWin.focus();
		return false;
	});
	var target = $("#pactForm").attr('target');
	$("#pactForm").on('submit',function(){
		var  pactWindow= window.open('', target, 'width=1000,height=600,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no');
		pactWindow.focus();
	});
	
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
	
		url = webApp + "displayJuvenileProfileReferralList.do?submitAction=Facility&actionType=popup&juvnum=" +juvNum;	
		var facilityWinStatus = localStorage.getItem("facilityHistWin");		
		if(facilityWinStatus == "open")
		{	
			if(juvInWindow!= null && juvInWindow == juvNum)
				return false;
			//window is open just refresh it
			 var facilityWindow = window.open(url, "facilityHistWin", "height=500,width=800,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
			 localStorage.setItem("facilityHistWin", "open");				
			 facilityWindow.focus();		
			return false;
		}
	
	$("#viewCasefile").click(function(){
		spinner();
	})
	
	$("#viewProfile").click(function(){
		spinner();
	})
	
	$("#referralLink").click(function(){		
		var webApp = "/<msp:webapp/>";
		var juvNum = '<bean:write name="juvenileBriefingDetailsForm" property="juvenileNum"/>';
		var url = webApp + "displayJuvenileProfileReferralList.do?submitAction=Link&juvnum=" +juvNum;
		window.myVariable=juvNum;
		var referralWindow = window.open(url, "referralWindow", "height=500,width=800,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
		referralWindow.focus();	
		localStorage.setItem("referralWin", "open");
		localStorage.setItem("juvnum", juvNum);
		return false;		
	});
		
	//get attorney email
	getAttorneyEmail();
	
});

function getAttorneyEmail(){
	
	var barNumber = '<bean:write name="juvenileBriefingDetailsForm" property="attorneyBarNumber"/>';
	var attorneyEmail = '<bean:write name="juvenileBriefingDetailsForm" property="attorneyEmail"/>';
	console.log('attorneyEmail: ', attorneyEmail);
	console.log('barNumber: ', barNumber);
	
}
</script>

</head> 
<%--END HEAD TAG--%>
<body onload="checkRestrictedAccess()">
<html:form action="/displayJuvenileBriefingDetails" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header">Juvenile Briefing Details</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0" align="center">
	<tr>
		<td>
			<ul>
				<li>Select the View Profile button to navigate to Master Profile screen.</li>
				<li>Select the View Casefile button to navigate to the Casefile Info screen based on the selected Casefile from Casefile Search.</li>
			</ul>
		</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td valign='top'>
			<table width='100%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
				<tr>
					<td class='detailHead' nowrap='nowrap' align="left">&nbsp;Juvenile Briefing&nbsp;
					<a onclick="spinner()" href="javascript:goNav('/<msp:webapp/>displayJuvenileMasterInformation.do?juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>&fromProfile=profileBriefingDetails');" value="<bean:message key='button.viewProfile'/>">View Profile</a>
					      &nbsp;
					      <jims2:isAllowed requiredFeatures='<%=Features.JCW_PS_SCANNED_DOCUMENTS%>'>
						      <a href="javascript:openCustomRestrictiveWindow('http://svpjpdweb1:8086/Ecx.Web/en-US/do/root_HC_JuvenileDetail?appid=root_Juvenile_Documents&d=Juvenile_Documents_Production.tenant1&JuvenileNo=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>',800,900 );"/>Scanned Documents</a>
							  &nbsp;
						  </jims2:isAllowed>
						  <jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_PGMREF%>'>
						  <a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileProgramReferralList.do?submitAction=Link&juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>">Program Referrals</a> &nbsp; &nbsp;
						  </jims2:isAllowed>
						  <!-- Removed for HOT FIX Bug #41283 - will be reinstated once US #39891 is implemented -->
						 <!--  Added back - bug #48048 -->
						 <jims2:isAllowed requiredFeatures='<%=Features.JCW_SEARCHFACPROF%>'>
						 	 <a onclick="spinner()" href="/<msp:webapp/>displayJuvenileBriefingDetails.do?juvenileNum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>&fromProfile=profileBriefingDetails&submitAction=facilityLink">Facility Briefing</a>
						 	&nbsp;
						 </jims2:isAllowed>
						
						 <jims2:isAllowed requiredFeatures='<%=Features.JCW_SEARCHJUVREFPROF%>'>
							<a onclick="spinner()" href="/<msp:webapp/>displayJuvenileBriefingDetails.do?fromProfile=profileBriefingDetails&submitAction=referralLink&juvenileNum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>">
								Referral Briefing
							</a>
						</jims2:isAllowed>
					
							 	
						  <!-- Pact Invocation  Task 41028 #43956-->
						  <jims2:isAllowed requiredFeatures='<%=Features.JCW_BRF_PACT_GEN%>'>
						  	 <div align="right" style="display:inline;float:right"> 
						  		 <logic:notEmpty name="juvenileBriefingDetailsForm" property="lastPactDate">
									 <span title='Recent PACT Results'><bean:write name="juvenileBriefingDetailsForm" property="lastPactDate"/> <bean:write name="juvenileBriefingDetailsForm" property="riskLvl"/>/<bean:write name="juvenileBriefingDetailsForm" property="needsLvl"/></span>&nbsp;&nbsp;&nbsp;&nbsp;
								 </logic:notEmpty>
								 	<a href="#" id="pactLink">Noble/PACT</a>
						  	 </div>
						  </jims2:isAllowed>
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
										<tr class='detailHead'>
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
														<logic:notEqual name="juvenileBriefingDetailsForm" property="masterStatus" value="C">
															<a onclick="spinner()" href="/<msp:webapp/>displayJuvenileMasterInformation.do?fromDashboard=update&juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>">Update Master</a>&nbsp;
														</logic:notEqual>
														<logic:equal name="juvenileBriefingDetailsForm" property="masterStatus" value="C">
														<jims2:isAllowed requiredFeatures='<%=Features.JCW_JP_ENABLEMASTERTAB_CLOSEDJUV%>'>
															<a onclick="spinner()" href="/<msp:webapp/>displayJuvenileMasterInformation.do?fromDashboard=update&juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>">Update Master</a>&nbsp;
														</jims2:isAllowed>
														</logic:equal>														
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
																	<td class='formDeLabel'><bean:message key="prompt.name"/></td>
																		<td class='formDe' colspan='2'>
																		<logic:equal name="juvenileBriefingDetailsForm" property="medicalHold" value="MHO">
																			<span style="color: red;"><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.lastName"/><sup title="Medical Hold">M</sup></span><span>,</span>
																		</logic:equal>	
																		<logic:notEqual name="juvenileBriefingDetailsForm" property="medicalHold" value="MHO">
																			<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.lastName"/><span>,</span>
																		</logic:notEqual>
																			<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.firstName"/>
																			<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.middleName"/>
																				&nbsp;
																			<logic:notEqual name="juvenileBriefingDetailsForm" property="profileDetail.preferredFirstName" value="">
																				<span style="font-weight: bold;" title="Preferred First Name">*<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.preferredFirstName"/></span>
																				&nbsp;
																			</logic:notEqual>
																			<logic:equal name="juvenileBriefingDetailsForm" property="dualStatus" value="FDS">
																				<span style="color: purple; font-weight: bold;" title="Former Dual Status">(<bean:write name="juvenileBriefingDetailsForm" property="dualStatus"/>)</span>
																			</logic:equal>
																			<logic:equal name="juvenileBriefingDetailsForm" property="dualStatus" value="DS">
																				<span style="color: purple; font-weight: bold;" title="Dual Status">(<bean:write name="juvenileBriefingDetailsForm" property="dualStatus"/>)</span>
																			</logic:equal>
																			&nbsp;
												  							<logic:equal name="juvenileBriefingDetailsForm" property="traitTypeId" value="MER">
												  								<span style="color:#8417A0; font-weight: bold;" title="<bean:write name="juvenileBriefingDetailsForm" property="traitTypeDescription"/>">(M)</span>
												  							</logic:equal>
																			<logic:equal name="juvenileBriefingDetailsForm" property="holdTrait" value="HOLD">
																				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: orange; font-weight: bold; margin-left: 35px;" title="<bean:write name="juvenileBriefingDetailsForm" property="holdTraitDescription"/>"><bean:write name="juvenileBriefingDetailsForm" property="holdTrait"/></span>
																			</logic:equal>
																			<logic:equal name="juvenileBriefingDetailsForm" property="inSpecialtyCourt" value="true">
																				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: green; font-weight: bold; margin-left: 35px;" title="<bean:write name="juvenileBriefingDetailsForm" property="specialtyCourtDescription"/>">SC</span>
																			</logic:equal>
																			<logic:equal name="juvenileBriefingDetailsForm" property="profileDetail.recType" value="I.JUVENILE">
																				&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Purged'>P</span></b></font>
																			</logic:equal>
																			<logic:equal name="juvenileBriefingDetailsForm" property="profileDetail.recType" value="S.JUVENILE">
																				&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Sealed'>S</span></b></font>
																			</logic:equal>
																		</td>
																	<td class='formDe'>
																	<logic:equal name="juvenileBriefingDetailsForm" property="restrictedAccessTrait" value="true"><span title='RESTRICTED ACCESS'><font color="red"><b>(RESTRICTED)</b></font></span></logic:equal></td>
																	
																</tr>
																<tr>
																	<td class='formDeLabel' valign='top'><bean:message key="prompt.juvenile#"/></td>
																	<td class='formDe'><logic:equal name="juvenileBriefingDetailsForm" property="restrictedAccessTrait" value="true"><font color="red"><b><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.juvenileNum"/></b></font></logic:equal>
																	<logic:notEqual name="juvenileBriefingDetailsForm" property="restrictedAccessTrait" value="true"><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.juvenileNum"/></logic:notEqual></td>
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
																		<logic:notEqual name="juvenileBriefingDetailsForm" property="profileDetail.detentionFacilityId"  value="">
																			<strong><span title='<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.detentionFacility"/>'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.detentionFacilityId" />:</span></strong>
																				<span title='<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.detentionStatus"/>'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.detentionStatusId" /></span>&nbsp;&nbsp;
																				<logic:notEqual name="juvenileBriefingDetailsForm" property="profileDetail.floorNum"  value="">
																					<span title='FLOOR'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.floorNum" /></span>
																				</logic:notEqual>
																				<logic:notEqual name="juvenileBriefingDetailsForm" property="profileDetail.roomNum"  value="">
																					<elogic:if name="juvenileBriefingDetailsForm" property="profileDetail.floorNum" op="equal" value="">
																						<elogic:then>
																						<span title='ROOM'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.roomNum" /></span>	
																						</elogic:then>
																						<elogic:else>
																						:<span title='ROOM'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.roomNum" /></span>	
																						</elogic:else>
																					</elogic:if>																					
																				</logic:notEqual>
																				<logic:notEqual name="juvenileBriefingDetailsForm" property="profileDetail.unitNum"  value="">
																					<elogic:if name="juvenileBriefingDetailsForm" property="profileDetail.roomNum" op="equal" value="">
																						<elogic:then><!-- check for floor here -->
																							<elogic:if name="juvenileBriefingDetailsForm" property="profileDetail.floorNum" op="equal" value="">
																								<elogic:then>
																									<span title='UNIT'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.unitNum" /></span>
																								</elogic:then>
																								<elogic:else>
																									:<span title='UNIT'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.unitNum" /></span>
																								</elogic:else>
																							</elogic:if>
																								
																						</elogic:then>
																						<elogic:else>
																						:<span title='UNIT'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.unitNum" /></span>	
																						</elogic:else>
																					</elogic:if>																					
																				</logic:notEqual>
																		</logic:notEqual>
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
																<tr class='detailHead'>
																	<td colspan='4' align='center'>Physical Characteristics</td>
																</tr>

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
					  																	<img title="Mug Shot Not Available" title="Juvenile Photo" src="/<msp:webapp/>getJuvenilePhoto.do?submitAction=Most Recent Photo&juvenileNumber=<bean:write name="juvenileBriefingDetailsForm" property="juvenileNum"/>" width="128" border='1'></a>
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
				  																<%-- ER-11051 GANG TATTOO STARTS --%>
				  																<tr>
				  																	<td>										
				    																	<a href="javascript:goNav('/<msp:webapp/>displayJuvenileMasterInformation.do?juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>&fromProfile=fromProfile&action=viewTattoo');">View All Tattoos</a>
				  																	</td>
				  																</tr>
				  																<%-- ER-11051 GANG TATTOO ENDS --%>
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
																<tr class='detailHead'>
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
	  																				<img src="/<msp:webapp/>images/grade_level_appropriate.png" title="Appropriate" />
																				</logic:equal>
	
																				<logic:equal name="juvenileBriefingDetailsForm" property="school.appropriateLevelCode" value="BEH">
	  																				<img src="/<msp:webapp/>images/grade_level_behind.png" title="Behind" />
																				</logic:equal>
	
																				<logic:equal name="juvenileBriefingDetailsForm" property="school.appropriateLevelCode" value="ADV">
	  																				<img src="/<msp:webapp/>images/grade_level_advanced.gif" title="Advanced" />
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
	  																	<bean:write name="juvenileBriefingDetailsForm" 
	  																				property="school.schoolAttendanceStatusDescription" />
	  																	<span style="color:blue; font-weight: bold;  margin-left:30px;">
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
	  															<tr class='detailHead'>
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
		  																	<a onclick="spinner()" href="/<msp:webapp/>displayFamilyMemberDetails.do?fromDashboard=viewMemberDetails&submitAction=Details&selectedValue=<bean:write name='juvenileBriefingDetailsForm' property='memberNum'/>&clearFamAction=true&addrstab=true&juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>">
		  																		<bean:write name="juvenileBriefingDetailsForm" property="memberAddress.streetAddress"/>
		  																	</a>		  																		
			  																	<logic:notEmpty name="juvenileBriefingDetailsForm" property="memberAddress.validated">
																					<logic:equal name="juvenileBriefingDetailsForm" property="memberAddress.validated" value="Y">
			  																			&nbsp;<img src="/<msp:webapp/>images/grade_level_appropriate.png" title="Verified" />
																					</logic:equal>
		
																					<logic:equal name="juvenileBriefingDetailsForm" property="memberAddress.validated" value="N">
		  																				&nbsp;<img src="/<msp:webapp/>images/red_x.gif" title="Unverified" />
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
		  																	<td class='formDe' colspan='3'>
		  																		<logic:notEmpty name="juvenileBriefingDetailsForm" property="memberAddress"><bean:write name="juvenileBriefingDetailsForm" property="memberAddress.county"/>
		  																		</logic:notEmpty>
		  																	</td>
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
																		</logic:notEqual></td>
		  															</tr>
		  															<tr>
		  															   <td class='formDeLabel' width='1%' nowrap='nowrap'>Youth Phone</td>
		  															   <td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="juvPhoneNum"/></td>
		  															</tr>
		  															<tr>
		  															   <td class='formDeLabel' width='1%' nowrap='nowrap'>Parent Email</td>
		  															   <td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="parentEmail"/></td>
		  															   <td class='formDeLabel' width='1%' nowrap='nowrap'>Priority</td>
		  															   <td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="primaryIndEmail"/></td>
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
 									<div class='spacer'></div>
 									<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
<%--general info end --%>				<tr class='detailHead'>
											<td>Court Information		
											</td>
											<td>
											<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTACTVTYYOUTH%>'>
											<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->													
													<a onclick="spinner()" href="/<msp:webapp/>processJuvenileDistrictCourtHearings.do?submitAction=courtActivityByYouth&juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>">Court Activity</a> &nbsp;
												</jims2:isAllowed>											
											</td>															
										</tr>
										<tr class='visibleTR'>
												<td>
													<table width="100%" cellpadding="2" cellspacing="1">
														<tr>
															<td class='formDeLabel'>Court ID</td>
															<td class='formDe'>
																<bean:write name="juvenileBriefingDetailsForm" property="courtID"/>
															</td>
															<td class='formDeLabel'>Last Attorney</td>											
															<td class='formDe' >
																<logic:notEmpty name="juvenileBriefingDetailsForm" property="attorneyEmail">
																	<a href="mailto:<bean:write name="juvenileBriefingDetailsForm" property="attorneyEmail"/>">
																		<bean:write name="juvenileBriefingDetailsForm" property="lastAttorney"/>
																	</a>
																</logic:notEmpty>
																<logic:empty name="juvenileBriefingDetailsForm" property="attorneyEmail">
																	<bean:write name="juvenileBriefingDetailsForm" property="lastAttorney"/>
																</logic:empty>																
															</td>																													
														</tr>	
														<tr>
														<td class='formDeLabel'>GAL </td>
														<td class='formDe'>
														<logic:notEmpty name="juvenileBriefingDetailsForm" property="galEmail">
															<a href="mailto:<bean:write name="juvenileBriefingDetailsForm" property="galEmail"/>">
																		<bean:write name="juvenileBriefingDetailsForm" property="galName"/></a>																	
														</logic:notEmpty>
														<logic:empty name="juvenileBriefingDetailsForm" property="galEmail">
																<bean:write name="juvenileBriefingDetailsForm" property="galName"/>
														</logic:empty>
														</td>
														<td class='formDe'></td>
														<td class='formDe'></td>
														</tr>
														<tr>											
															<td class='formDeLabel'>Next Court Date</td>
															<td class='formDe'>
																<bean:write name="juvenileBriefingDetailsForm" property="nextCourtDate"/>
															</td>
															<%-- <logic:notEmpty name="juvenileBriefingDetailsForm" property="profileDetail.nexthearingDate"> --%>				   	 							
																	<td class='formDeLabel'>Next Detention Date </td>
																	<td class='formDe'>
																		<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.nexthearingDate" formatKey="date.format.mmddyyyy"/>
																	</td>											
															<%-- </logic:notEmpty> --%>
															
														</tr>
													</table>
												</td>
												<td class='formDe'></td>
										</tr>
									</table>
									<div class='spacer'></div>
<%-- behavioral start --%>
									<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>									
										<tr class='detailHead'>
											<td>Behavioral History
												<logic:empty name="juvenileBriefingDetailsForm" property="casefiles">
												  (No Behavioral History)
												</logic:empty>
											</td>
											<td>
											
											</td>
											<%-- <td >
												Next Court Date: <bean:write name="juvenileBriefingDetailsForm" property="nextCourtDate"/>
											</td>
											<td >
												Court ID: <bean:write name="juvenileBriefingDetailsForm" property="courtID"/>
											</td> --%>										
										</tr>										
										<logic:notEmpty name="juvenileBriefingDetailsForm" property="casefiles">
											<tr class='visibleTR'>
												<td>
													<table width="100%" cellpadding="2" cellspacing="1">
													<tr>
														<td class='formDeLabel'>Age first Referred</td>
														<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="behaviorHistory.ageFirstReferred"/></td>
														<td class='formDe' colspan=2></td>
													</tr>
													<tr>
														<td class='formDeLabel'>Diversion Events</td>
														<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="behaviorHistory.diversionEvents"/></td>
														<td class='formDeLabel' width='1%' nowrap='nowrap'>Deferred Adjudication Events</td>
														<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="behaviorHistory.deferredAdjudicationEvents"/></td>
													</tr>
													<tr>
														<td class='formDeLabel' width='1%' nowrap='nowrap'>Adjudication Events</td>
														<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="behaviorHistory.adjudicationEvents"/></td>
														<td class='formDeLabel'>TJJD Commitments</td>
														<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="behaviorHistory.TYCCommitments"/></td>
													</tr>
													<tr>
														<td class='formDeLabel'>Seriousness Index</td>
														<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="behaviorHistory.seriousnessIndex"/></td>
														<td class='formDeLabel'>Severity Index</td>
														<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="behaviorHistory.severityIndex"/></td>
													</tr>
													</table>
												</td>
											</tr>
										</logic:notEmpty>
										
									</table>
<%-- behavioral end --%>
								</td>
<%-- end of first major column --%>
<%-- begin second major column --%>
								<td width='44%' valign="top">
<%-- Warrant start --%>
									<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
										<tr class='detailHead'>
											<td class='paddedFourPix'>Warrant
												<logic:empty name="juvenileBriefingDetailsForm" property="warrant">
													(No Warrant Information)
												</logic:empty> 
											</td>
										</tr>

										<logic:notEmpty name="juvenileBriefingDetailsForm" property="warrant">
	    									<tr>
	    										<td>
	    											<table width="100%" cellpadding="2" cellspacing="1">
	    												<tr>
	    													<td class='formDeLabel' width='1%'>Warrant#</td>
	    													
	    													<td class='formDe'>
	    													   <jims2:isAllowed requiredFeatures='<%=Features.JW_VIEW%>'>
	    														<a href="javascript:openWindow('/JuvenileWarrants/displayWarrantViewSearchResults.do?submitAction=Submit&warrantNum=<bean:write name="juvenileBriefingDetailsForm" property="warrant.warrantNum"/>&secondaryAction=popup');">
	    															<bean:write name="juvenileBriefingDetailsForm" property="warrant.warrantNum"/></a>
	    													   </jims2:isAllowed>
	    													   <jims2:isAllowed requiredFeatures='<%=Features.JW_VIEW%>' value="false">
	    													        <bean:write name="juvenileBriefingDetailsForm" property="warrant.warrantNum"/>
	    													   </jims2:isAllowed>    																		
	    													</td>
	    												</tr>
	    												<tr>
	    													<td class='formDeLabel' valign='top'>Type</td>
	    													<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="warrant.warrantType"/></td>
	    												</tr>
	    												<tr>
	    													<td class='formDeLabel'>Date Issued</td>
	    													<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="warrant.dateOfIssue" formatKey="date.format.mmddyyyy"/></td>
	    												</tr>
	    												<tr>
	    													<td class='formDeLabel'>Originating Agency</td>
	    													<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="warrant.warrantOriginatorAgencyName"/></td>
	    												</tr>
	    											</table>
	    										</td>
	    									</tr>
										</logic:notEmpty>
									</table>
<%-- Warrant end --%>
	
									<div class='spacer'></div>
<%-- JIS Information section start --%>
									<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
										<tr class='detailHead'>
											<td class='paddedFourPix'>
												JIS - 												
													<jims2:isAllowed requiredFeatures='<%=Features.JCW_JUVENILEINFORMATIONSHARING_JIS%>'> 
														<a href="javascript:openInNewTab('https://apps.harriscountytx.gov/JIS/');"> 
															Juvenile Information Sharing 
														</a>
													</jims2:isAllowed> 
												Verification													
												
											</td>
										</tr>
										<logic:notEmpty name="juvenileBriefingDetailsForm" property="jisInfo">
											<tr>
												<td colspan='2'><b>Agencies with Confirmed record:</b> 
												<logic:notEmpty name="juvenileBriefingDetailsForm" property="jisInfo.agency">
													<bean:write name="juvenileBriefingDetailsForm" property="jisInfo.agency"/> 
													<logic:notEmpty name="juvenileBriefingDetailsForm" property="jisInfo.otherAgency">
													 :<bean:write name="juvenileBriefingDetailsForm" property="jisInfo.otherAgency"/> 
													</logic:notEmpty>
													&nbsp;<bean:write name="juvenileBriefingDetailsForm" property="jisInfo.comments"/> 
												</logic:notEmpty>
												<logic:empty name="juvenileBriefingDetailsForm" property="jisInfo.agency"> None</logic:empty>
												</td>
											</tr>
										</logic:notEmpty>
									</table>

									<div class='spacer'></div>
<%-- Traits section start --%>
									<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
										<tr class='detailHead'>
											<td class='paddedFourPix'>Traits
												<logic:empty name="juvenileBriefingDetailsForm" property="traits">
													(No Trait Information)
												</logic:empty>
											</td>
										</tr>

										<logic:notEmpty name="juvenileBriefingDetailsForm" property="traits">
											<tr>
												<td colspan='2'>
													<%-- this span simulates a scrolling table after 5 entries --%>
													<div class='scrollingDiv200'>
														<table width="100%" cellpadding="2" cellspacing="1" border='0'>
															<thead>
															<%-- static, column titles --%>
															<tr class='formDeLabel'>
																<td>Entry Date</td>
																<td>Trait Description</td>
																<td>Trait Status</td>
															</tr>
															</thead>
															<tbody>
															<logic:iterate id="traitsList" name="juvenileBriefingDetailsForm" property="traits" indexId="indexer"> 
																<tr class="<% out.print( (indexer.intValue() % 2 == 1) ? "normalRow" : "alternateRow" ); %>">
																	<td><a href="javascript:newCustomWindow('/<msp:webapp/>displayJuvenileBriefingDetails.do?submitAction=Details&traitID=<%=indexer.intValue()%>', 'winName',600,300);"><bean:write name='traitsList' property='entryDate' formatKey="date.format.mmddyyyy" /></a></td>
																	<td><bean:write name="traitsList" property="traitTypeDescription" /></td>
																	<td><bean:write name="traitsList" property="status" /></td>
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
										<tr class='detailHead'>
											<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_CASEFILE%>'>										
											<td class='paddedFourPix' onclick="casefileCallback(this.form);">
											
											<a onclick="spinner()" href="/<msp:webapp/>displayJuvenileProfileCasefileList.do?juvenileNum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>&action=casefilesLink">Casefiles</a>
												<logic:empty name="juvenileBriefingDetailsForm" property="casefiles">
													(No Casefiles)
												</logic:empty>
												
											</td>
											</jims2:isAllowed>
											<td>
												JPO of Record - <span title='<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.jpoOfRecord"/> - <bean:write name="juvenileBriefingDetailsForm" property="profileDetail.jpoPhoneNumber"/> - <bean:write name="juvenileBriefingDetailsForm" property="profileDetail.locUnitName"/>'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.jpoOfRecID"/></span>
												<logic:notEmpty name="juvenileBriefingDetailsForm" property="juvProgramCode">
													&nbsp;&nbsp;&nbsp;Res. Caseworker - <span title='<bean:write name="juvenileBriefingDetailsForm" property="juvProgramDescription"/>'> <bean:write name="juvenileBriefingDetailsForm" property="juvProgramCode"/> </span>
												</logic:notEmpty>
											</td>
												
											<logic:notEmpty name="juvenileBriefingDetailsForm" property="casefiles">
												<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_JOU%>'>
	  											<td align='right'><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileJournalList.do?juvenileNum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>&submitAction=<bean:message key='button.link'/>">Journals</a>&nbsp;</td>
												</jims2:isAllowed>
											</logic:notEmpty>
										</tr>
										
										<logic:notEmpty name="juvenileBriefingDetailsForm" property="casefiles">
											<tr>
												<td colspan='3'>
												<%-- this span simulates a scrolling table after 5 entries --%>
												<div class='scrollingDiv200'>
													<table width="100%" cellpadding="2" cellspacing="1" border='0'>
														<thead>
														<%-- static, column titles --%>
														<tr class='formDeLabel'>
															<td width='10%' nowrap="nowrap">Supervision#&nbsp;</td>
															<td width='10%'>Status</td>
															<td width='40%'>Supervision Type</td>
															<td width="20%">Expected End Date</td>
															<td width='20%'>JPO</td>
														</tr>
														</thead>
														<tbody>
															<logic:iterate id="casefiles" name="juvenileBriefingDetailsForm" property="casefiles" indexId="indexer"> 
																<tr class="<% out.print( (indexer.intValue() % 2 == 1) ? "normalRow" : "alternateRow" ); %>">
																	<logic:equal name="casefiles" property="caseStatus" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING_DESCRIPTION%>">	 	
																		<td><a onclick="spinner()" href="/<msp:webapp/>handleCasefileActivation.do?submitAction=Link&casefileID=<bean:write name='casefiles' property='supervisionNum'/>&action=default"><bean:write name="casefiles" property="supervisionNum"/></a></td>
																	</logic:equal>
																	<logic:notEqual name="casefiles" property="caseStatus" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING_DESCRIPTION%>">	 	
																		<td><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileCasefileDetails.do?supervisionNum=<bean:write name='casefiles' property='supervisionNum'/>"><bean:write name="casefiles" property="supervisionNum"/></a></td>
																	</logic:notEqual>
																	<td class="hidden">
																	<jims2:sortResults beanName="juvenileBriefingDetailsForm" results="casefiles" primaryPropSort="sequenceNum"  primarySortType="INTEGER"  defaultSortOrder="DESC" defaultSort="true" sortId="1" /></td>
																	<td><bean:write name="casefiles" property="caseStatus"/></td>
																	<td><bean:write name="casefiles" property="supervisionType"/></td>
																	<td><bean:write name="casefiles" property="supervisionEndDate" formatKey="date.format.mmddyyyy" /></td>
																	<td><bean:write name="casefiles" property="probationOfficerLastName" /></td>
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
										<tr class='detailHead'>
											<td class='paddedFourPix'>Guardian Information
						      					<logic:empty name="juvenileBriefingDetailsForm" property="guardians">
											 		(No Guardian Information)
						      					</logic:empty>
										 	</td>
										 	<td>Youth lives with: 
												<span title="<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.liveWithTjjdDesc" />">
													<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.liveWithTjjd"/> 
												</span>
											</td>
										</tr>

				      					<logic:notEmpty name="juvenileBriefingDetailsForm" property="guardians">
											<tr>
												<td colspan='2'>
													<table width="100%" cellpadding="2" cellspacing="1">
														<tr class='formDeLabel'>
															<td>Name</td>
															<td>Relationship</td>
															<td>Member#</td>
														</tr>
 
							      						<logic:iterate name="juvenileBriefingDetailsForm" property="guardians" id="juvGuardiansIter" indexId="indexer">      			
															<tr class="<% out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
																<td>
																	<bean:write name="juvGuardiansIter" property="nameOfPerson.formattedName"/>
																	<logic:equal  name="juvGuardiansIter" property="inHomeStatus" value="true">
																		<img title='home' src='/<msp:webapp/>images/home-s.jpg' />
																	</logic:equal>
																	<logic:equal  name="juvGuardiansIter" property="primaryContact" value="true">
																		<img title='primary contact' src='/<msp:webapp/>images/starBlueIcon.gif' />
																	</logic:equal>
																</td>
																<td><bean:write name="juvGuardiansIter" property="relationshipType"/></td>
																<td>
																	<a onclick="spinner()" href="/<msp:webapp/>displayFamilyMemberDetails.do?fromDashboard=viewMemberDetails&submitAction=Details&selectedValue=<bean:write name='juvGuardiansIter' property='memberNum'/>&clearFamAction=true&juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>">
																		<bean:write name="juvGuardiansIter" property="memberNum"/>
																	</a>																
																</td>
															</tr>
															<tr class="<% out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
																<td class='boldItalicText'>Language: <bean:write name="juvGuardiansIter" property="language"/></td>
  															<td class='boldItalicText' colspan='3'></td>
															</tr>
														</logic:iterate>
													</table>
												</td>
												
											</tr>
										</logic:notEmpty>
									</table>
<%-- guardian end --%>
						<div class='spacer'></div>
<%-- Detention Visitation info start --%>
									<jims2:isAllowed requiredFeatures='<%=Features.JCW_BRF_DET_VISITATION%>'>
										<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
											
											<tr class='detailHead'>											
												<logic:equal name="juvenileBriefingDetailsForm" property="detVisitBanned" value="true">
													<td nowrap align="left" bgcolor="red" class="jpoAlert"><b><bean:message key="prompt.detention"/> Visitation</b>																									
														<logic:empty name="juvenileBriefingDetailsForm" property="detVisits">
													 		(No Detention Visits)
								      					</logic:empty>
								      					<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_ASSREF%>'>
														&nbsp;&nbsp;&nbsp;<a href="#" id="historyLink"><bean:message key="prompt.facilityHistory" /></a>
					   	 							</jims2:isAllowed>						   	 							
													</td>
													<td nowrap align="right" bgcolor="red" class="jpoAlert"><b><a id="bannedLink" href="#">Banned</b></td>
												</logic:equal>
												<logic:notEqual name="juvenileBriefingDetailsForm" property="detVisitBanned" value="true">
													<td class='paddedFourPix'><bean:message key="prompt.detention"/> Visitation													
														<logic:empty name="juvenileBriefingDetailsForm" property="detVisits">
													 		(No Detention Visits)
								      					</logic:empty>
								      					<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_ASSREF%>'>
														&nbsp;&nbsp;&nbsp;<a href="#" id="historyLink"><bean:message key="prompt.facilityHistory" /></a>
					   	 							</jims2:isAllowed>					   	 							
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
<%-- referral start --%>
  						<table width="99%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
  							<tr class='detailHead'>
  								<td>Referral History
									<logic:empty name="juvenileBriefingDetailsForm" property="casefiles">
										(No Referral History)
									</logic:empty>
  								</td>
  								<td style="padding-left: 300px;">
  								<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRFV_ASSREF%>'>	
  								<span><a href="#" id="referralLink"><bean:message key="prompt.referrals" /></a></span>
  								</jims2:isAllowed>
  								</td>
  							</tr>
							<logic:notEmpty name="juvenileBriefingDetailsForm" property="casefiles">
		  						<tr>
		  							<td colspan='2'>
		  								<table width="100%" cellpadding="2" cellspacing="1">
		  									<tr>
		  										<td class='formDeLabel' width='15%'>Referrals Count - Non-Administrative</td> 
		  										<td class='formDe' colspan='5'><bean:write name="juvenileBriefingDetailsForm" property="behaviorHistory.totalReferralNonAdmin"/></td> 
		  									</tr>
		  									<tr> 
		  										<td class='formDeLabel'>Referral Events by Date</td> 
		  										<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="behaviorHistory.referralByDateNonAdminEvents"/></td> 
		  										<td class='formDeLabel'>Offenses</td> 
		  										<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="behaviorHistory.totalOffenses"/></td> 
		  									</tr> 
											<tr bgcolor="#aaaaaa" class='subhead'>
												<td colspan='6'>Felony Information</td>
											</tr>
		  									<tr> 
		  										<td class='formDeLabel'>Felony - 1</td> 
		  										<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="behaviorHistory.felony1"/></td> 
		  										<td class='formDeLabel'>Felony - 2</td> 
		  										<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="behaviorHistory.felony2"/></td> 
		  										<td class='formDeLabel' width='1%' nowrap='nowrap'>Felony - 3</td> 
		  										<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="behaviorHistory.felony3"/></td>
											</tr>
		  									<tr> 
		  										<td class='formDeLabel'>Felony - Capital</td> 
		  										<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="behaviorHistory.felonyCapital"/></td> 
		  										<td class='formDeLabel'>Felony - State Jail</td> 
		  										<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="behaviorHistory.felonyStateJail"/></td> 
		  									</tr> 
		  									<tr bgcolor="#aaaaaa" class='subhead'>
												<td colspan='6'>Misdemeanor Information</td>
											</tr>
		    								<tr> 
		    									<td class='formDeLabel' width='1%' nowrap='nowrap'>Misdemeanor - A</td> 
		    									<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="behaviorHistory.misdemeanorA"/></td> 
		    									<td class='formDeLabel'>Misdemeanor - B</td> 
		    									<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="behaviorHistory.misdemeanorB"/></td> 
											</tr>
		    								<%-- spacer --%>
		    								<tr bgcolor="#aaaaaa" class='subhead'>
												<td colspan='6'>Status Offense Information </td>
											</tr>
		    								<tr> 
		    									<td class='formDeLabel'>Runaways</td> 
		    									<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="behaviorHistory.runaways"/></td> 
		    									<td class='formDeLabel' width='1%' nowrap='nowrap'>Violations of Probation</td> 
		    									<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="behaviorHistory.violationsOfProbation"/></td> 
		    								</tr> 
		  								</table>
		  							</td>
		  						</tr>
		  					</logic:notEmpty>
  						</table>
  					<%-- referral end --%>
						<div class='spacer'></div>
					<%--button start --%>
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<input type="button" value="Back" name="return" onClick="goNav('back')">
									<input id="viewProfile" type="button" onclick="goNav('/<msp:webapp/>displayJuvenileMasterInformation.do?juvnum=<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>');" value="<bean:message key='button.viewProfile'/>"/>
									<input type="button" onclick="goNav('/<msp:webapp/>displayJuvenileBriefingDetails.do?submitAction=Print Demographics');" value="<bean:message key='button.printDemographics'/>"/>
									
									<logic:notEqual name="juvenileBriefingDetailsForm" property="fromProfile" value="profileBriefingDetails">
										<logic:notEqual name="juvenileBriefingDetailsForm" property="currentCasefile.caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING%>">	 	
											<input id="viewCasefile" type="button" onclick="goNav('/<msp:webapp/>displayJuvenileCasefileDetails.do?supervisionNum=<bean:write name='juvenileBriefingDetailsForm' property='supervisionNum'/>');" value="<bean:message key='button.viewCasefile'/>"/>
										</logic:notEqual>
	
										<logic:equal name="juvenileBriefingDetailsForm" property="currentCasefile.caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING%>">	 	
											<input id="viewCasefile" type="button" onclick="goNav('/<msp:webapp/>handleCasefileActivation.do?submitAction=Link&action=default&casefileID=<bean:write name='juvenileBriefingDetailsForm' property='supervisionNum'/>');" value="<bean:message key='button.viewCasefile'/>"/>
										</logic:equal>
										<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>									
									</logic:notEqual>
									<logic:equal name="juvenileBriefingDetailsForm" property="fromProfile" value="profileBriefingDetails">
									   <input type="button" onclick="goNav('/<msp:webapp/>displayJuvenileProfileSearch.do');" value="<bean:message key='button.cancel'/>"/>									    
									</logic:equal>
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

<%-- Pact Invocation task #41028  &&   //# task 44099 . U.S #41378--%>
<form id="pactForm" action="https://<bean:write name='juvenileBriefingDetailsForm' property='pactServerName'/>/<bean:write name='juvenileBriefingDetailsForm' property='pactApplicationName'/>/juvenile/Login/IntegrationLogon" method="POST"  target="newFormWindow">
	<input type="hidden" name="USER_EXTERNAL_ID" value="<bean:write name='juvenileBriefingDetailsForm' property='userId'/>">
	<input type="hidden" name="SUBJECT_EXTERNAL_ID" value="<bean:write name='juvenileBriefingDetailsForm' property='juvenileNum'/>">
	<input type="hidden" name="AUTH_TOKEN" value="<bean:write name='juvenileBriefingDetailsForm' property='pactAuthKey'/>">
</form>

<input type="hidden" id="attorneyEmail" name="attorneyEmail" />

<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>