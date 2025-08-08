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
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ page import="naming.PDJuvenileCaseConstants" %>




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
<html:base />
<title><bean:message key="title.heading" /> - juvenileReferralBriefingDetailsPOPUP.jsp</title>

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
<script type="text/javascript" src="/<msp:webapp/>js/referral/juvReferralProfileSearch.js"></script>

<script type="text/javascript"> 

function casefileCallback(tForm)
{
	<logic:notEqual name="juvenileBriefingDetailsForm" property="fromProfile" value="profileBriefingDetails">
		<logic:notEqual name="juvenileBriefingDetailsForm" property="currentCasefile.caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING%>">	 	
		  goNav("/<msp:webapp/>displayJuvenileCasefileDetails.do?supervisionNum=<bean:write name='juvenileBriefingDetailsForm' property='supervisionNum'/>");
	  </logic:notEqual>
	</logic:notEqual> 
}

</script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<%--BEGIN FORM TAG--%>
<html:form action="/displayJuvenileProfileSearchResults" target="content" focus="juvenileNum">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|162">       

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"> Referral Briefing Details</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>
</br></br>
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
				<td class="referralDetailHead" nowrap aligh = "left">&nbsp;Referral Briefing&nbsp;</td>
			</tr>
		</table>
		
		<%-- BEGIN INFORMATION TABLE --%>
		<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
			<tr>
				<td width='50%' valign="top">
					<%--general info start --%>
						<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
							<tr class='referralDetailHead'>
								<td colspan= '2' class='paddedFourPix' align='left' nowrap='nowrap'>Juvenile Information</td>
							</tr>
							<tr>
								<td colspan="2">
 									<table width="100%" cellpadding="2" cellspacing="0" border='0'>
										<tr>
 											<td valign='top'>
												<table width="100%" cellpadding="2" cellspacing="1">
												<tr>
													<td class='formDeLabel'><bean:message key="prompt.name"/></td>
													<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.formattedName"/> <logic:equal name="juvenileBriefingDetailsForm" property="restrictedAccessTrait" value="true"><span title='RESTRICTED ACCESS'> <font color="red"><b>(RESTRICTED)</b></font></span></logic:equal></td>
												</tr>
												<tr>
													<td class='formDeLabel' valign='top'><bean:message key="prompt.juvenile#"/></td>
													<td class='formDe'>
													<logic:equal name="juvenileBriefingDetailsForm" property="restrictedAccessTrait" value="true"><font color="red"><b><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.juvenileNum"/></b></font></logic:equal>															<logic:notEqual name="juvenileBriefingDetailsForm" property="restrictedAccessTrait" value="true"><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.juvenileNum"/></logic:notEqual>
													<logic:equal name="juvenileBriefingDetailsForm" property="profileDetail.recType" value="I.JUVENILE">
													&nbsp;&nbsp;&nbsp;&nbsp;<font color="Red" face="verdana"><b><span title='Purged'>P</span></b></font>
													</logic:equal>
													<logic:equal name="juvenileBriefingDetailsForm" property="profileDetail.recType" value="S.JUVENILE">
													&nbsp;&nbsp;&nbsp;&nbsp;<font color="Red" face="verdana"><b><span title='Sealed'>S</span></b></font>
													</logic:equal>
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
	  												<td class='formDeLabel'><bean:message key="prompt.school"/>&nbsp;<bean:message key="prompt.district" />&nbsp;<bean:message key="prompt.name"/></td>
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
	  											<%-- <tr>
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
										<logic:empty name="juvenileBriefingDetailsForm" property="juvAddress">
											<tr class='referralDetailHead'>
												<td class='paddedFourPix' colspan='4'>Residential Information
													(No Residential Information)
												</td>
											</tr>
										</logic:empty>
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
			  												&nbsp;<img src="/<msp:webapp/>images/grade_level_appropriate.png" alt="Appropriate" />
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
		  										<logic:notEmpty name="juvenileBriefingDetailsForm" property="juvAddress">	
	  											<tr class='referralDetailHead'>
	  												<td colspan='4' align='center'><bean:message key="prompt.residential"/> <bean:message key="prompt.info"/></td>
	  											</tr>
		  										<tr>
		  											<td class='formDeLabel'><bean:message key="prompt.address"/></td>
													<td class='formDe' colspan='3'>
		  												<div>
		  													<bean:write name="juvenileBriefingDetailsForm" property="juvAddress"/>
														</div>
		  											</td>	
		  										</tr>
		  										<tr>
		  											<td class='formDeLabel'><bean:message key="prompt.county"/></td>
		  											<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="juvCounty"/></td>
		  										</tr>
		  										<tr>
		  											<td class='formDeLabel'>Phone</td>
		  											<td class='formDe'></td>
		  											<td class='formDeLabel' width='1%' nowrap='nowrap'>Priority</td>
		  											<td class='formDe'></td>
		  										</tr>
		  										<tr>
		  											<td class='formDeLabel'><bean:message key="prompt.type"/></td>
		  											<td class='formDe'></td>
		  											<td class='formDeLabel'><bean:message key="prompt.unknown"/></td>
		  											<td class='formDe'></td>
												</tr>
												</logic:notEmpty>
											</table></td>
									</tr>
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
								<logic:notEmpty name="juvenileBriefingDetailsForm" property="casefiles">Casefiles
								</logic:notEmpty>
								<logic:empty name="juvenileBriefingDetailsForm" property="casefiles">
									Casefiles	(No Casefiles)
								</logic:empty>
							</td>
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
							<td  align="left" width='1%' nowrap="nowrap">Supervision#&nbsp;</td>
							<td align="left" >Status</td>
							<td align="left">Supervision Type</td>
							<td align="left" width="1%">Expected End Date</td>
							<td align="left" >JPO</td>
							</tr>
							</thead>
							<tbody>
							<logic:iterate id="casefiles" name="juvenileBriefingDetailsForm" property="casefiles" indexId="indexer"> 
							<tr class="<% out.print( (indexer.intValue() % 2 == 1) ? "normalRow" : "alternateRow" ); %>" height="100%">
							<logic:equal name="casefiles" property="caseStatus" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING_DESCRIPTION%>">	 	
							<td align="left"><bean:write name="casefiles" property="supervisionNum"/></td>
							</logic:equal>
							<logic:notEqual name="casefiles" property="caseStatus" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING_DESCRIPTION%>">	 	
							<td align="left"><bean:write name="casefiles" property="supervisionNum"/></td>
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
						</tr>
						<logic:empty name="juvenileBriefingDetailsForm" property="mothersName">
						<logic:empty name="juvenileBriefingDetailsForm" property="fathersName">
						<logic:empty name="juvenileBriefingDetailsForm" property="otherRelName">
					   	<logic:notEmpty name="juvenileBriefingDetailsForm" property="guardians">
						<tr>
							<td>
							<table width="100%" cellpadding="2" cellspacing="1">
							<tr class='formDeLabel'>
							<td>Relationship</td>
							<td>Name</td>
							<td colspan="2">SSN</td>
							</tr>
							<logic:iterate name="juvenileBriefingDetailsForm" property="guardians" id="juvGuardiansIter" indexId="indexer">      			
							<tr class="<% out.print( (indexer.intValue() % 2 == 1) ?  "normalRow" : "alternateRow" );%>">
							<td><bean:write name="juvGuardiansIter" property="relationshipType"/></td>
							<td>
								<bean:write name="juvGuardiansIter" property="nameOfPerson.formattedName"/>
								<%-- <logic:equal  name="juvGuardiansIter" property="inHomeStatus" value="true">
									<img title='home' src='/<msp:webapp/>images/home-s.jpg' />
								</logic:equal>
								<logic:equal  name="juvGuardiansIter" property="primaryContact" value="true">
									<img title='primary contact' src='/<msp:webapp/>images/starBlueIcon.gif' />
								</logic:equal> --%>
								<logic:equal  name="juvGuardiansIter" property="incarcerated" value="true">
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="Red" face="verdana"><span title='Incarcerated'>I</span></font>
								</logic:equal>
								<logic:equal  name="juvGuardiansIter" property="deceased" value="true">
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="Red" face="verdana"><span title='Deceased'>D</span></font>
								</logic:equal>
							</td>
							<td colspan='2'>
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
												
													
		
		<%-- END INFORMATION TABLE --%>
<%-- END DETAIL TABLE --%>
<%-- BEGIN BUTTON TABLE --%>

<table align="center" border="0" width="100%">
<tr><td></td></tr>
	<tr>
		<td align="center">
		 <html:button property="submitAction" onclick="window.close();"><bean:message key="button.close"/></html:button>
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>

</html:form>

<br>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
