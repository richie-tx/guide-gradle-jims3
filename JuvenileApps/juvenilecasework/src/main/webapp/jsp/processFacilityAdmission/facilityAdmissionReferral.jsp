<!DOCTYPE HTML>
<!--  07/24/2014  Created jsp ugopinath -->


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
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">


<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />


<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>



<html:base />
<title><bean:message key="title.heading"/>/facilityAdmissionReferral.jsp</title>

<%--HELP JAVASCRIPT FILE --%> 
<%-- <SCRIPT SRC="../js/help.js" /> --%>
<script type="text/javascript"> 
function setBookingReferral(el)
{
	var stat="<bean:write name='juvenileBriefingDetailsForm' property='headerInfo.facilityStatus'/>";
	var currRef="<bean:write name='juvenileBriefingDetailsForm' property='currentReferral'/>";
	var ref = el.value.split('|');
	var val = document.getElementsByName('currentReferral');	
	//go through and make the current referral selected	
	//show curr referral only if booking ref is 'R'	or there are no casefiles associated
	for(i=0;i<val.length;i++)
	{
		
		var currRef = val[i].value.split('|');			
			//if there is no associated casefile, or is administrative it cannot be a current referral
			if(currRef[1]==" " || currRef[2]=="R")
			{
				val[i].disabled=true;
				val[i].checked=false;
				
			}
			else if(currRef[0]==ref[0] && currRef[1]==ref[1] && ref[2]!='R')
				val[i].checked=true;	
	}
	
	showHide("currentRef",1);	
}

function checkReferralSelection()
{
	var stat="<bean:write name='juvenileBriefingDetailsForm' property='headerInfo.facilityStatus'/>";
	var val1 = document.getElementsByName('bookingReferral');
	var val2 = document.getElementsByName('currentReferral');
	var foundBooking=0;
	var foundCurrent=0;
	var bookRefSeveritySubType="";
	var bookingCasefile="";
	for(i=0;i<val1.length;i++)
	{	
		if(val1[i].checked == true)
		{			
			var bookingRefElems = val1[i].value.split('|');
			bookRefSeveritySubType=bookingRefElems[2];			
			bookingCasefile=bookingRefElems[1];
			foundBooking=1;
		}
	}
	
	if(foundBooking==0)
	{
		if(stat!='N')
		{
			alert("Booking Referral selection is required.");
			return false;
		}
	}
	else//check if current referral is present
	{	
		if(bookRefSeveritySubType=="R" || bookingCasefile=="")
		{			
			for(i=0;i<val2.length;i++)
			{	
				if(val2[i].checked == true)
					foundCurrent=1;				
			}
			if(foundCurrent==0)
			{
				if(stat=='N')
					alert("Current Supervision selection is required.");
				else
					alert("Current Referral selection is required.");
				return false;
			}
		}
	}
	
	if ( true ) {
		spinner();
	}
	
	return true;
	
}
function setRefForSubseq()
{
	
	var bookingRef="<bean:write name='juvenileBriefingDetailsForm' property='bookingReferral'/>";
	var currRef="<bean:write name='juvenileBriefingDetailsForm' property='currentReferral'/>";
	var val1 = document.getElementsByName('bookingReferral');
	var val2 = document.getElementsByName('currentReferral');

	for(i=0;i<val1.length;i++)
	{	
		var bookingRefElems = val1[i].value.split('|');
		var bookingRefOnForm=bookingRef.split('|');
		if(bookingRefElems[0]!= bookingRefOnForm[0])
			val1[i].disabled=true;
	}
	for(i=0;i<val2.length;i++)
	{		
		var currentRefElems = val2[i].value.split('|');
		var currentRefOnForm=currRef.split('|');	
		if(currentRefElems[0]!= currentRefOnForm[0])
			val2[i].disabled=true;
	}
}

function setRefForBack()
{	
	var val1 = document.getElementsByName('bookingReferral');	
	var found=0;
	var ref2="<bean:write name='juvenileBriefingDetailsForm' property='currentReferral'/>";		
	for(i=0;i<val1.length;i++)
	{	
		if(val1[i].checked == true)
		{
			found=1;
			break;
		}
	}	
	if(found==1)
	{		
		var val2 = document.getElementsByName('currentReferral');	
		for(i=0;i<val2.length;i++)
		{	//on hitting back button current referral selection is not retaining so force it to
			var currRef = val2[i].value.split('|');
			if(currRef[1]==" " || currRef[2]=="R")
			{
				val2[i].disabled=true;
				
			}
			if(ref2==val2[i].value)
				val2[i].checked=true;
	
		}
		showHide("currentRef",1);		
	}
	else
	{		
		showHide("currentRef",0);
	}
}
function checkFacStat()
{
	var stat="<bean:write name='juvenileBriefingDetailsForm' property='headerInfo.facilityStatus'/>";		
	var ref2="<bean:write name='juvenileBriefingDetailsForm' property='currentReferral'/>";	
	
	if(stat!='N')
		setRefForBack();
	if(stat=='N')
	{	
		document.getElementById("bookRefDivId").disabled=true;
		document.getElementById("currentRef").disabled=true;		
		showHide("currentRef",1);	
	}
}
</script>

</head> 
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body onLoad="checkFacStat();">

	
<html:form action="/displayJuvenileFacilityAdmit" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header">Process Juvenile Facility Admission - Facility Admission Referral</td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN MESSAGE TABLE --%>

<logic:notEmpty name="juvenileBriefingDetailsForm" property="casefiles">
	<logic:equal name="juvenileBriefingDetailsForm" property="hasOpenReferrals" value="false">
		<div class='spacer'></div>
		<table width='100%'>
			<tr>
				<td align="center" class="errorAlert">All referrals are closed. Juvenile cannot be admitted.</td>
			</tr>
		</table>
	</logic:equal>
	<logic:equal name="juvenileBriefingDetailsForm" property="hasOpenReferrals" value="true">
		<logic:equal name="juvenileBriefingDetailsForm" property="hasActiveCasefiles" value="false">
			<div class='spacer'></div>
			<table width='100%'>
				<tr>
					<td align="center" class="errorAlert">Juvenile cannot be admitted; casefile needs to be activated.</td>
				</tr>
			</table>
		</logic:equal>
	</logic:equal>
</logic:notEmpty>
<logic:empty name="juvenileBriefingDetailsForm" property="casefiles">
	<div class='spacer'></div>
	<table width='100%'>
		<tr>
			<td align="center" class="errorAlert">Juvenile cannot be admitted; casefile is needed for Admit.</td>
		</tr>
	</table>
</logic:empty>
<%-- END HEADING TABLE --%>
<!-- BEGIN ERROR TABLE -->
<table width="100%">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
</table>
<!-- END ERROR TABLE -->

<%-- BEGIN INSTRUCTION TABLE --%>
<logic:equal name="juvenileBriefingDetailsForm" property="hasActiveCasefiles" value="true">
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select the Booking Referral and Current Referral (if Booking Referral is Administrative) and select the Next button.</li>
			
			</ul>
		</td>
	</tr>
</table>
</logic:equal>

<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
	  <tr>
			<td valign="top" colspan="4">
              <tiles:insert page="../caseworkCommon/juvenileFacilityDetailsTile.jsp" flush="false">
	          <tiles:put name="detailsForm" beanName="juvenileBriefingDetailsForm" />	
	          </tiles:insert>
          </td>
       </tr> 

		<tr>
			<td>			<div class='spacer'></div>		
					<%-- Casefiles start --%>
					<table width="100%" cellpadding="2" cellspacing="1" class='borderTableBlue' align="center">
						<tr class='facDetailHead'>
							<td class='paddedFourPix'>Facility Admission Referral
								<logic:empty name="juvenileBriefingDetailsForm" property="casefiles">
									(No Casefiles)
								</logic:empty>
							</td>										
						</tr>
						<logic:notEmpty name="juvenileBriefingDetailsForm" property="casefiles">
							<logic:equal name="juvenileBriefingDetailsForm" property="hasPendingCasefiles" value="true">							
								<tr>
									<td class='paddedFourPix' bgcolor="green"><font color="white"><b>Juvenile has Pending Casefile(s)</b></font></td>
								</tr>
							
						
								<tr>
									<td colspan='2'>
									<%-- this span simulates a scrolling table after 5 entries --%>
									
										<table width="100%" cellpadding="2" cellspacing="1" border='0'>
											<thead>
											<%-- static, column titles --%>
											<tr bgcolor="#DFF0D8">
												<td  width='1%' nowrap="nowrap" align="left"><b>Supervision#&nbsp;</b></td>
												<td align="left"><b>Seq. No.</b></td>
												<td width="20%" nowrap align="left"><b>Probation Officer</b></td>
												<td nowrap align="left"><b>Case Status</b></td>
												<td nowrap align="left"><b>Supervision Type</b></td>
											</tr>
											</thead>
											<tbody>
												
												<logic:iterate id="casefiles" name="juvenileBriefingDetailsForm" property="casefiles" indexId="indexer"> 
													<logic:equal name="casefiles" property="caseStatus" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING_DESCRIPTION%>">	
													<tr class="<% out.print( (indexer.intValue() % 2 == 1) ? "normalRow" : "alternateRow" ); %>">														
														<td align="left"><a href="/<msp:webapp/>handleCasefileActivation.do?submitAction=Link&casefileID=<bean:write name='casefiles' property='supervisionNum'/>&action=default"><bean:write name="casefiles" property="supervisionNum"/></a></td>												
														<td align="left"><bean:write name="casefiles" property="sequenceNum"/></td>
														<td align="left"><bean:write name="casefiles" property="probationOfficerLastName" />, <bean:write name="casefiles" property="probationOfficerMiddleName" /> <bean:write name="casefiles" property="probationOfficerFirstName" /></td>
														<td align="left"><bean:write name="casefiles" property="caseStatus"/></td>
														<td align="left"><bean:write name="casefiles" property="supervisionType"/></td>						
													</tr>
												</logic:equal>
												</logic:iterate> 
											</tbody>
										</table>
									
									</td>
								</tr>
							</logic:equal>
						</logic:notEmpty>
					</table>
				<div class='spacer'></div>
				<%-- casefiles end --%>
				<logic:notEmpty name="juvenileBriefingDetailsForm" property="casefiles">
					<logic:equal name="juvenileBriefingDetailsForm" property="hasActiveCasefiles" value="true">			
					<%-- referral start --%>
					<table width="100%" cellpadding="0" cellspacing="0" >
						<tr id="refId">
							<td width='44%' valign="top">
								<%--general info start --%> 
								<table width="100%" class='borderTableBlue' cellpadding="0" cellspacing="0" id="bookRefDivId">
									<tr class='facDetailHead'>
										<td  class='paddedFourPix' align='left' nowrap='nowrap'>Booking Referral</td>									
									</tr>
									<logic:empty name="juvenileBriefingDetailsForm" property="referrals">
									<tr><td>(No Referrals)</td></tr>
									</logic:empty>
									<logic:notEmpty name="juvenileBriefingDetailsForm" property="referrals">											
										<tr>									
											<td>
												<table width="100%" cellpadding="2" cellspacing="1" border='0'>
													<tr style="background-color:#D9E4FA">
														<td></td>
														<td align="left">Referral</td>
														<td align="left">Offense/Allegation</td>
														<td nowrap align="left">Petition Number</td>
														<td align="left">Level</td>												
													</tr>
														
						      						<logic:iterate name="juvenileBriefingDetailsForm" property="referrals" id="juvRefsIter" indexId="refIndexer">  
						      							<bean:define id="bookingRefId" name="juvRefsIter" property="referralNumber" type="java.lang.String"/>
														<bean:define id="severitySubtype" name="juvRefsIter" property="mostSevereOffense.severitySubtype" type="java.lang.String"/>																																						
														<logic:equal  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="N">														
																<bean:define id="bookingSupId" name="juvenileBriefingDetailsForm" property="bookingSupervisionNum" type="java.lang.String"/>
																<jims2:if name="juvenileBriefingDetailsForm" property="bookingReferral" value='<%=bookingRefId + "|"+bookingSupId+ "|" + severitySubtype%>' op="equal">
																	<jims2:then><tr style="background-color:#F5F6F8"></jims2:then>
  																				<jims2:else><tr style="background-color:#F5F6F8" class='hidden'></jims2:else></jims2:if>																	
															
														</logic:equal>
														<logic:notEqual  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="N">
															<tr style="background-color:#F5F6F8">
														</logic:notEqual>		
														
															<logic:equal name="juvRefsIter" property="hasCasefiles" value="false">
																<td width="1%" align="left">			
																        <logic:equal name="juvRefsIter" property="isEligableForBooking" value="true">													
																		<html:radio name="juvenileBriefingDetailsForm" property="bookingReferral" styleId="bookingReferral" value='<%=bookingRefId + "||" + severitySubtype%>' onclick="setBookingReferral(this);"/>	
																		</logic:equal>																
																</td>
															</logic:equal>
															<logic:equal name="juvRefsIter" property="hasCasefiles" value="true">
																<td></td>
															</logic:equal>
															<td width="10%" align="left"><b><bean:write name="juvRefsIter" property="referralNumber"/></b></td>
															<td width="25%" nowrap align="left">
																	<logic:notEmpty name="juvRefsIter" property="petitions">
																		<b><bean:write name="juvRefsIter" property="petitionAllegation"/></b>	
																	</logic:notEmpty>
																<logic:empty name="juvRefsIter" property="petitions">
																	<b><bean:write name="juvRefsIter" property="mostSevereOffense.offenseDescription"/> </b>
																</logic:empty>
															</td>
															<td width="20%" align="left">
																<logic:notEmpty name="juvRefsIter" property="petitions">																    
																		<b><bean:write name="juvRefsIter" property="petitionNumber"/></b>
																	
																</logic:notEmpty>
															</td>
															<td align="left">
																<logic:notEmpty name="juvRefsIter" property="offenses">
																
																	<b><bean:write name="juvRefsIter" property="mostSevereOffense.offenseLevelId"/> </b>														
																</logic:notEmpty>
															</td>
														</tr>
														
																	<logic:equal name="juvRefsIter" property="hasCasefiles" value="true">
																		<logic:iterate name="juvRefsIter" property="casefileReferrals" id="casefilesIter" indexId="casefilesIndexer"> 	
																		<logic:equal name="casefilesIter" property="caseStatusCd" value="ACTIVE">															
																		<bean:define id="bookingRefCaseId" name="casefilesIter" property="caseFileId" type="java.lang.String"/>	
																			<logic:equal  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="N">
																				
	  																				<bean:define id="bookingSupId" name="juvenileBriefingDetailsForm" property="bookingSupervisionNum" type="java.lang.String"/>
																					<jims2:if name="juvenileBriefingDetailsForm" property="bookingReferral" value='<%=bookingRefId + "|"+bookingSupId+ "|" + severitySubtype%>' op="equal">
																						<jims2:then><tr style="<% out.print( (casefilesIndexer.intValue() % 2 == 1) ? "background-color: #ffffff" : "background-color:#F5F6F8" );%>"></jims2:then>
					  																				<jims2:else><tr style="<% out.print( (casefilesIndexer.intValue() % 2 == 1) ? "background-color: #ffffff" : "background-color:#F5F6F8" );%>" class='hidden'></jims2:else></jims2:if>																	
																															
																			</logic:equal>
																			<logic:notEqual  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="N">
																				<tr style="<% out.print( (casefilesIndexer.intValue() % 2 == 1) ? "background-color: #ffffff" : "background-color:#F5F6F8" );%>">
																			</logic:notEqual>																		
																		
																				<td></td>																						
																				<td align="right">
																					 <logic:equal name="juvRefsIter" property="isEligableForBooking" value="true">		
																					<html:radio name="juvenileBriefingDetailsForm" property="bookingReferral" styleId="bookingReferral" value='<%=bookingRefId + "|" + bookingRefCaseId + "|" + severitySubtype%>' onclick="setBookingReferral(this);" />
																					</logic:equal>
																				</td>
																				<td align="left"><bean:write name="casefilesIter" property="caseFileId"/></td>
																				<td nowrap align="left"><bean:write name="casefilesIter" property="supervisionType"/></td>
																				<td align="left"><bean:write name="casefilesIter" property="caseStatusCd"/></td>																					
																			</tr>
																			</logic:equal>
																		</logic:iterate>  
																	</logic:equal>
														
													</logic:iterate>
													
												</table>
											</td>
										</tr>
									</logic:notEmpty>
								</table>
							</td>
						
						<td width='44%' valign="top"">
						<%-- Current Referral start --%>
							<div id="currentRef">
							<table width="100%" class='borderTableBlue' cellpadding="0" cellspacing="0">
								<tr class='facDetailHead' >
									<td class='paddedFourPix' align='left' nowrap='nowrap'>Current Referral										
									</td>
								</tr>
									<logic:notEmpty name="juvenileBriefingDetailsForm" property="referrals">											
										<tr>									
											<td>
												<table width="100%" cellpadding="2" cellspacing="1" border='0'>
													<tr style="background-color:#D9E4FA">
														<td></td>
														<td align="left">Referral</td>
														<td align="left">Offense/Allegation</td>
														<td nowrap align="left">Petition Number</td>
														<td align="left">Level</td>												
													</tr>
														
						      						<logic:iterate name="juvenileBriefingDetailsForm" property="referrals" id="juvRefsIter" indexId="refIndexer">
						      								<logic:equal name="juvRefsIter" property="hasCasefiles" value="true">  
						      							<bean:define id="currentRefId" name="juvRefsIter" property="referralNumber" type="java.lang.String"/>
														<bean:define id="severitySubtype" name="juvRefsIter" property="mostSevereOffense.severitySubtype" type="java.lang.String"/>																																						
														<logic:equal  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="N">														
																<bean:define id="currentSupId" name="juvenileBriefingDetailsForm" property="currentSupervisionNum" type="java.lang.String"/>
																<jims2:if name="juvenileBriefingDetailsForm" property="currentReferral" value='<%=currentRefId + "|"+currentSupId+ "|" + severitySubtype%>' op="equal">
																	<jims2:then><tr style="background-color:#F5F6F8"></jims2:then>
  																				<jims2:else><tr style="background-color:#F5F6F8" class='hidden'></jims2:else></jims2:if>																	
															
														</logic:equal>
														<logic:notEqual  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="N">
															<tr style="background-color:#F5F6F8">
														</logic:notEqual>		
														
															<logic:equal name="juvRefsIter" property="hasCasefiles" value="false">
																<td width="1%" align="left">	
																<logic:equal name="juvRefsIter" property="isEligableForBooking" value="true">																
																		<html:radio name="juvenileBriefingDetailsForm" property="currentReferral" styleId="currentReferral" value='<%=currentRefId + "||" + severitySubtype%>'/>	
																</logic:equal>																	
																</td>
															</logic:equal>
															<logic:equal name="juvRefsIter" property="hasCasefiles" value="true">
																<td></td>
															</logic:equal>
															<td width="10%" align="left"><b><bean:write name="juvRefsIter" property="referralNumber"/></b></td>
															<td width="25%" nowrap align="left">
																<logic:notEmpty name="juvRefsIter" property="offenses">
																	<b><bean:write name="juvRefsIter" property="mostSevereOffense.offenseDescription"/> </b>
																</logic:notEmpty>
															</td>
															<td width="20%" align="left">
																<logic:notEmpty name="juvRefsIter" property="petitions">																    
																		<b><bean:write name="juvRefsIter" property="petitionNumber"/></b>
																	
																</logic:notEmpty>
															</td>
															<td align="left">
																<logic:notEmpty name="juvRefsIter" property="offenses">
																
																	<b><bean:write name="juvRefsIter" property="mostSevereOffense.offenseLevelId"/> </b>														
																</logic:notEmpty>
															</td>
														</tr>
														
																	<logic:equal name="juvRefsIter" property="hasCasefiles" value="true">
																		<logic:iterate name="juvRefsIter" property="casefileReferrals" id="casefilesIter" indexId="casefilesIndexer"> 																	
																		<bean:define id="currentRefCaseId" name="casefilesIter" property="caseFileId" type="java.lang.String"/>	
																			<logic:equal  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="N">																				
	  																				<bean:define id="currentSupId" name="juvenileBriefingDetailsForm" property="currentSupervisionNum" type="java.lang.String"/>
																					<jims2:if name="juvenileBriefingDetailsForm" property="currentReferral" value='<%=currentRefId + "|"+currentSupId+ "|" + severitySubtype%>' op="equal">
																						<jims2:then><tr style="<% out.print( (casefilesIndexer.intValue() % 2 == 1) ? "background-color: #ffffff" : "background-color:#F5F6F8" );%>"></jims2:then>
					  																				<jims2:else><tr style="<% out.print( (casefilesIndexer.intValue() % 2 == 1) ? "background-color: #ffffff" : "background-color:#F5F6F8" );%>" class='hidden'></jims2:else></jims2:if>																	
																															
																			</logic:equal>
																			<logic:notEqual  name="juvenileBriefingDetailsForm" property="headerInfo.facilityStatus" value="N">
																				<tr style="<% out.print( (casefilesIndexer.intValue() % 2 == 1) ? "background-color: #ffffff" : "background-color:#F5F6F8" );%>">
																			</logic:notEqual>																		
																		
																				<td></td>																						
																				<td align="right">
																					<logic:equal name="juvRefsIter" property="isEligableForBooking" value="true">	
																					<html:radio name="juvenileBriefingDetailsForm" property="currentReferral" styleId="currentReferral" value='<%=currentRefId + "|" + currentRefCaseId + "|" + severitySubtype%>'  />
																					</logic:equal>	
																				</td>
																				<td align="left"><bean:write name="casefilesIter" property="caseFileId"/></td>
																				<td nowrap align="left"><bean:write name="casefilesIter" property="supervisionType"/></td>
																				<td align="left"><bean:write name="casefilesIter" property="caseStatusCd"/></td>																					
																			</tr>
																		</logic:iterate>  
																	</logic:equal>
														</logic:equal>
													</logic:iterate>
													
												</table>
											</td>
										</tr>
									</logic:notEmpty>
								
							</table>
							</div>
						</td>
					</tr>
				</table>			
				
  					<%-- referral end --%>
  				</logic:equal>
  			</logic:notEmpty>
			</td>
		</tr>

</table>
<%-- END  TABLE --%>

<br>
<logic:notEmpty name="juvenileBriefingDetailsForm" property="casefiles">
	<logic:equal name="juvenileBriefingDetailsForm" property="hasActiveCasefiles" value="true">	
		 <table border="0" width="100%">
			<TBODY>
				<tr>
					<td align="center">
				
					<html:submit property="submitAction">
						<bean:message key="button.back"></bean:message>			
					</html:submit> 		
					<html:submit property="submitAction" onclick="return checkReferralSelection();">
						<bean:message key="button.next"></bean:message>
					</html:submit>			
					<html:submit property="submitAction">
						<bean:message key="button.cancel"></bean:message>
					</html:submit>
					</td>
				</tr>
			</TBODY>
		</table>
	</logic:equal>
</logic:notEmpty>
</html:form>

</body>
</html:html>