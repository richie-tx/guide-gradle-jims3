<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>





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

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/facility.js"></script>

<title><bean:message key="title.heading"/>/facilityCurrentReferralTransferRecord.jsp</title>

</head> 
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
<html:form action="/displayJuvenileFacilityReleaseSummary" target="content">

<!-- HELP FILE -->
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header">Process Juvenile Facility Admission - Booking Transfer - Facility Admission Referral</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN INSTRUCTION TABLE --%>
<logic:equal name="juvenileBriefingDetailsForm" property="hasActiveCasefiles" value="true">
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
		<td align="left">
			<ul>
				<li>Select the CurrentReferral Referral and Click the Next button.</li>
			</ul>
		</td>
	</tr>
</table>
</logic:equal>

<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<table width="99%" border="0" cellpadding="0" cellspacing="0" align="center">
	  <tr>
		<td valign="top" colspan="4">
           <tiles:insert page="../caseworkCommon/juvenileFacilityDetailsTile.jsp" flush="false">
	        	 <tiles:put name="detailsForm" beanName="juvenileBriefingDetailsForm" />	
	        </tiles:insert>
          </td>
       </tr> 
</table>

<div class='spacer'></div>
<div class='spacer'></div>

<%-- END INSTRUCTION TABLE --%>
<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
<logic:notEmpty name="juvenileBriefingDetailsForm" property="casefiles">
<logic:equal name="juvenileBriefingDetailsForm" property="hasPendingCasefiles" value="true">	
	<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue'  align="center">
		<%-- Casefiles start --%>
		<logic:notEmpty name="juvenileBriefingDetailsForm" property="casefiles">
		<logic:equal name="juvenileBriefingDetailsForm" property="hasPendingCasefiles" value="true">							
		<tr>
			<td class='paddedFourPix' align="left">
				<font color="white"><b>Juvenile has Pending Casefile(s)</b></font>
			</td>
		</tr>
		</logic:equal>															
		</logic:notEmpty>
		
		<tr>
			<td colspan='2'>
				<%-- this span simulates a scrolling table after 5 entries --%>
				<table width="100%" cellpadding="2" cellspacing="1" border='0'>
					<thead>
					<%-- static, column titles --%>
						<tr class='formDeLabel'>
							<td  align="left" width='5%' nowrap="nowrap">Supervision#&nbsp;</td>
							<td align="left" width='5%' nowrap="nowrap">Seq.No.</td>
							<td align="left" width="50%" nowrap="nowrap">Supervision Type</td>
							<td align="left" width="20%" nowrap="nowrap">Case Status</td>
							<td align="left" width="20%"nowrap="nowrap">JPO</td>
						</tr>
					</thead>
					<tbody>
						<logic:iterate id="casefiles" name="juvenileBriefingDetailsForm" property="casefiles" indexId="indexer"> 
							<logic:equal name="casefiles" property="caseStatus" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING_DESCRIPTION%>">	
								<tr class="<% out.print( (indexer.intValue() % 2 == 1) ? "normalRow" : "alternateRow" ); %>" height="100%">
									<td align="left"><a href="/<msp:webapp/>handleCasefileActivation.do?submitAction=Link&casefileID=<bean:write name='casefiles' property='supervisionNum'/>&action=default"><bean:write name="casefiles" property="supervisionNum"/></a></td>
									<td align="left"><bean:write name="casefiles" property="sequenceNum"/></td>
									<td align="left"><bean:write name="casefiles" property="supervisionType"/></td>
									<td align="left" nowrap><bean:write name="casefiles" property="caseStatus"/></td>
									<td align="left"><bean:write name="casefiles" property="probationOfficerLastName" /></td>
								</tr>
							</logic:equal>
							<%-- <logic:equal name="indexer" value="1">
								<logic:notEqual name="casefiles" property="caseStatus" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING_DESCRIPTION%>">	
									<tr>
										<td  nowrap>(No Pending Casefiles Found)</td>
									</tr>	
								</logic:notEqual> 
							</logic:equal> --%>
						</logic:iterate> 
					</tbody>
				</table>
			</td>
		</tr>
	</table>
</logic:equal>
</logic:notEmpty>

<div class='spacer'></div>
<div class='spacer'></div>

<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
	<tr class='facDetailHead'>
		<td align='left' colspan='6' class='paddedFourPix'>Referral Transfer Information</td>
	</tr>
	<tr>
		<td class='formDeLabel' valign='top' width='1%' nowrap><bean:message key="prompt.transferFrom"/></td>
		<td class='formDe' width="5%" colspan="1" nowrap><bean:write name="admitReleaseForm" property="transferFromReferral"/></td>
		
		<td class='formDeLabel' valign='top' width='1%' nowrap><bean:message key="prompt.transferTo"/></td>
		<td class='formDe' width="5%" colspan="1" nowrap><bean:write name="admitReleaseForm" property="bookingReferral"/></td>
		
		<td class='formDeLabel' valign='top' width='1%' nowrap><bean:message key="prompt.petition"/>#</td>
		<td class='formDe' width="5%" colspan="1" nowrap><bean:write name="admitReleaseForm" property="bookingPetitionNum"/></td>
	</tr>
	<tr>
		<td class='formDeLabel'  valign='top' width='1%' nowrap><bean:message key="prompt.bookingOffense"/></td>
		<td class='formDe' width="1%" colspan="1" nowrap><span title='<bean:write name="admitReleaseForm" property="bookingOffense"/>'><bean:write name="admitReleaseForm" property="bookingOffenseCd"/></span></td>
		
		<td class='formDeLabel'  valign='top' width='1%' nowrap><bean:message key="prompt.offenseLevel"/></td>
		<td class='formDe' width="1%" colspan="1" nowrap><bean:write name="admitReleaseForm" property="bookingOffenseLevel"/></td>
		
		<td class='formDeLabel'  valign='top' width='1%' nowrap><bean:message key="prompt.bookingSupervision"/></td>
		<td class='formDe' width="1%" colspan="1" nowrap><bean:write name="admitReleaseForm" property="bookingSupervisionNum"/></td>
	</tr>
</table>

<div class='spacer'></div>
<div class='spacer'></div>

<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue'  align="center">
	<tr class='facDetailHead'>
		<td  class='paddedFourPix' align='left' nowrap='nowrap' colspan="5">Facility Admission Referral</td>									
	</tr>		
	
	<logic:notEmpty name="juvenileBriefingDetailsForm" property="casefiles">
		<logic:equal name="juvenileBriefingDetailsForm" property="hasActiveCasefiles" value="true">			
			<tr>
				<td valign="top">
					<div class='spacer'></div>
					<div class='spacer'></div>
					<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue'  align="center" valign="top">	
						<tr class='facDetailHead'>
							<td  class='paddedFourPix' align='left' nowrap='nowrap'>Booking Referral</td>									
						</tr>
						<tr>									
							<td align="left">
								<table width="100%" cellpadding="2" cellspacing="1" border='0'>
									<tr class='formDeLabel'>
										<td  align="left" nowrap>Referral #</td>
										<td align="left" nowrap>Petition #</td>
										<td align="left" nowrap>Offense/Allegation</td>
										<td a align="left" nowrap>Level</td>		
									</tr>
										 
									<tr>
										<td width="15%" nowrap>
											<%-- <input type="radio" name="bookingReferral" value='<bean:write name="admitReleaseForm" property="bookingReferral"/>' disabled="true" checked="true" id='bookingReferralId-<bean:write name="admitReleaseForm" property="severitySubType"/>'/><bean:write  name="admitReleaseForm" property="bookingReferral"/> --%>
											 <input type="radio" name="bookingReferral" value='<bean:write name="admitReleaseForm" property="bookingReferral"/>' disabled="true" checked="true" id='bookingRefId' /><bean:write  name="admitReleaseForm" property="bookingReferral"/>
										</td>
										<td width="20%" nowrap>
											<b><bean:write name="admitReleaseForm" property="bookingPetitionNum"/></b>
										</td>
										<td width="50%" nowrap>
											<b><bean:write name="admitReleaseForm" property="bookingOffense"/></b>
										</td>
										<td width="15%" nowrap>
											<b><bean:write name="admitReleaseForm" property="bookingOffenseLevel"/></b>
										</td>
									</tr>
								</table>
							</td>
					 </tr>
					</table>
				</td>
				
				<td>
					<div class='spacer'></div>
					<div class='spacer'></div>
					<table width="99%" cellpadding="0" cellspacing="1" class='borderTableBlue'  align="top">	
						<tr class='facDetailHead'>
							<td  class='paddedFourPix' align='left' nowrap='nowrap'>Current Referral</td>									
						</tr>
						<logic:empty name="admitReleaseForm" property="referrals">
						<tr>
							<td>(No Referrals)</td>
						</tr>
						</logic:empty>
						<logic:notEmpty name="admitReleaseForm" property="referrals">
							<tr>									
								<td align="left">
									<table width="100%" cellpadding="2" cellspacing="1" border='0'>
										<tr class='formDeLabel'>
											<td align="left" nowrap>Referral #</td>
											<td align="left" nowrap>Petition #</td>
											<td align="left" nowrap>Supervision Type</td>	
											<td align="left" nowrap>Offense/Allegation</td>
											<td align="left" nowrap>Case Status</td>
											<td align="left" nowrap>Level</td>	
										</tr>
										
										<logic:iterate name="admitReleaseForm" property="referrals" id="juvRefsIter" indexId="refIndexer">   
											<html:hidden  name="juvRefsIter" property="mostSevereOffense.severitySubtype" styleId="severitySubtypeId"/>
											<html:hidden name="juvRefsIter" property="referralNumber" styleId='referralId'/>
											
											<tr id='<bean:write name="juvRefsIter" property="referralNumber"/>-<bean:write name="juvRefsIter" property="hasCasefiles"/>-<bean:write name="juvRefsIter" property="mostSevereOffense.severitySubtype"/>'>
												<logic:equal name="juvRefsIter" property="hasCasefiles" value="true">
													<td width="10%" align="left"  nowrap><b><bean:write name="juvRefsIter" property="referralNumber"/></b></td>
												</logic:equal>
												
												<logic:equal name="juvRefsIter" property="hasCasefiles" value="false">
													<td> 
														<bean:write name="juvRefsIter" property="referralNumber"/>
													</td>
												</logic:equal>
										
												<td width="10%" align="left"  nowrap>
													<bean:write name="juvRefsIter" property="petitionNumber"/>
												</td>
												<td></td>
												<td width="30%" align="left"  nowrap>
													<b><label><bean:write name="juvRefsIter" property="mostSevereOffense.offenseDescription"/></label></b>
												</td>
												<td></td>
												<td width="25%" align="left"  nowrap>
													<b><bean:write name="juvRefsIter" property="mostSevereOffense.offenseLevelId"/> </b>														
												</td>
											</tr>
											<logic:notEmpty  name="juvRefsIter" property="casefiles">
												<logic:iterate name="juvRefsIter" property="casefiles" id="casefilesIter" indexId="casefilesIndexer">
													<tr style="<% out.print( (casefilesIndexer.intValue() % 2 == 1) ? "background-color: #ffffff" : "background-color:#F5F6F8" );%>" id='<bean:write name="juvRefsIter" property="mostSevereOffense.severitySubtype"/>'>
														<td></td>
														<logic:equal name="juvRefsIter" property="hasCasefiles" value="true">
														 <logic:equal name="juvenileBriefingDetailsForm" property="hasActiveCasefiles" value="true">
														 	<logic:equal name="casefilesIter" property="caseStatus" value="ACTIVE">
																<td width="5%" align="left" nowrap>
																	<input type="radio" name="currentReferral"  value='<bean:write name="juvRefsIter" property="referralNumber"/>-<bean:write name="casefilesIter" property="supervisionNum"/>-<bean:write name="juvRefsIter" property="mostSevereOffense.severitySubtype"/>' id='<bean:write name="casefilesIter" property="supervisionNum"/>-<bean:write name="juvRefsIter" property="mostSevereOffense.severitySubtype"/>'/><b><bean:write name="casefilesIter" property="supervisionNum"/></b>
																</td>
															</logic:equal>
														 </logic:equal>
														</logic:equal>
														<td width="15%" align="left" nowrap><bean:write name="casefilesIter" property="supervisionType"/></td>
														<td></td>
														<td width="7%" align="left" nowrap><bean:write name="casefilesIter" property="caseStatus"/></td>
														<td></td>
													</tr>
												</logic:iterate>	
											</logic:notEmpty>
										</logic:iterate>
									</table>
								</td>
							</tr>
						</logic:notEmpty>
					</table>
				</td>
			</tr>
		</logic:equal>
	</logic:notEmpty>
</table>

<br>
 <table border="0" width="100%">
		<tr>
			<td align="center">
				<html:submit property="submitAction"><bean:message key="button.back"/></html:submit> 		
				<html:submit property="submitAction" styleId="next"><bean:message key="button.next"/></html:submit>			
				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
			</td>
		</tr>
</table>
</html:form>

</body>
</html:html>