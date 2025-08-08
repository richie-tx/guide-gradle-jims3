<!DOCTYPE HTML>
<%-- Used in closing of a casefile --%>
<%--MODIFICATIONS --%>
<%-- 11/29/2005		Justin Jose	Created JSP --%>
<%-- 05/13/2011     CShimek		#70290 add guardian exception code  --%>
<%-- 11/29/2011     CShimek		#71970 revised href action value from 'cancel' to 'close' to correct back button navigation flow issue for normal referral processing. --%>
<%-- 11/16/2012     DWilliamson #74127 Changed in home guardian error message for one or more guardians. --%>
<%-- 05/23/2013     CShimek		#75535 added non-compliance exception code and revised override logic to not be allowed for this exception  --%>
<%-- 08/02/2013     RYoung		#75898 MJCW: Verify TJJD Risk closed on casefile close  --%>
<%-- 08/26/2013     CShimek		#75983 revised override logic to not be allowed when guardian exception is present  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDJuvenileConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />
<meta charset="UTF-8" />

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
<title><bean:message key="title.heading"/> - exceptionsList.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script> 
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type="text/javascript">
$(document).ready(function(){
	$("#reasonToOverride").empty();
	$("#exceptionOverrideComments").val();
	$('input[type=radio][name=overrideSelection]').prop("checked", false);
	$('input[type=radio][name=overrideSelection]').change(function() {
		if ( "PACT" == this.value){
			$("#reasonToOverride").empty();
			$("#reasonToOverride").append($("<option></option>")
									.attr("value","")
									.text("Please Select"));
			<logic:iterate id="overrideOptionCode" name="casefileClosingForm" property="overrideOptionCodes">
				if ( 'PACT' == '<bean:write name="overrideOptionCode" property="overrideOption"/>' ){
					$('#reasonToOverride')
			         .append($("<option></option>")
			                    .attr("value", '<bean:write name="overrideOptionCode" property="exceptionReason"/>')
			                    .text('<bean:write name="overrideOptionCode" property="exceptionReason"/>')); 
				}
			</logic:iterate>
		}
		
		if ( "MAYSI" == this.value){
			$("#reasonToOverride").empty();
			$("#reasonToOverride").append($("<option></option>")
									.attr("value","")
									.text("Please Select"));
			<logic:iterate id="overrideOptionCode" name="casefileClosingForm" property="overrideOptionCodes">
				if ( 'MAYSI' == '<bean:write name="overrideOptionCode" property="overrideOption"/>' ){
					$('#reasonToOverride')
			         .append($("<option></option>")
			                    .attr("value", '<bean:write name="overrideOptionCode" property="exceptionReason"/>')
			                    .text('<bean:write name="overrideOptionCode" property="exceptionReason"/>')); 
				}
			</logic:iterate>
		}
	});
	
	$('#reasonToOverride').change(function(){
		$("#exceptionOverrideComments").val( $('#reasonToOverride').val() );
	});
})
function processOverrideButton()
{
	var msg = "";
	var exception = 0;
	<logic:iterate id="casefileException" name="casefileClosingForm" property="casefileExceptions" indexId="index">
		
		if ( exception <2 ) {
			if ( '<bean:write name= "casefileException" property="exceptionType"/>' == '<%=PDJuvenileCaseConstants.MAYSI_DATE_PRIOR_ACTIVATION_DATE%>' ){
				msg = "MAYSI Subsequent Needed cannot be overridden. User must complete the Subsequent entry.";
				alert(msg);
				exception++;
			}
			
			if ( '<bean:write name= "casefileException" property="exceptionType"/>' == '<%=PDJuvenileCaseConstants.PROG_REFERRALS_NOT_DONE%>' ){
				msg = "Override not allowed on Casefiles with Active Program Referrals.  Program Referral must be closed or transferred to an active casefile.\n";
				alert(msg);
				exception++;
			}
			
			if ( '<bean:write name= "casefileException" property="exceptionType"/>' == '<%=PDJuvenileCaseConstants.GOALS_NOT_DONE%>' ){
				msg = "Override not allowed on Casefiles with Goal not finalized.\n";
				alert(msg);
				exception++;
			}
			

			if ( '<bean:write name= "casefileException" property="exceptionType"/>' == '<%=PDJuvenileCaseConstants.RULES_NOT_DONE%>' ){
				msg = "Override not allowed on Casefiles with Rule not finalized.\n";
				alert(msg);
				exception++;
			}
			
			if ( '<bean:write name= "casefileException" property="exceptionType"/>' == '<%=PDJuvenileCaseConstants.GUARDIAN_IN_HOME_STATUS%>' ){
				msg = "Override not allowed on Casefiles with in-home Guardian residential address or assigned status issue.\n";
				alert(msg);
				exception++;
			}
			
			if ( '<bean:write name= "casefileException" property="exceptionType"/>' == '<%=PDJuvenileCaseConstants.NON_COMPLIANCE_COMPLETION_NO_STATUS_FOUND%>' ){
				msg = "Override not allowed on Casefiles with incomplete Non-Compliance letter status.\n";
				alert(msg);
				exception++;
			}
			
			if ( '<bean:write name= "casefileException" property="exceptionType"/>' == '<%=PDJuvenileCaseConstants.JUVENILE_IN_FACILITY%>' ){
				msg = "Override not allowed on casefiles when juvenile is in facility.\n";
				alert(msg);
				exception++;
			}
			if ( '<bean:write name= "casefileException" property="exceptionType"/>' == '<%=PDJuvenileCaseConstants.VOP_DETAILS_REQUIRED%>' ){
				msg = "Override not allowed on Casefiles requiring VOP Details on referrals. Contact Data Corrections for assistance, if needed.\n";
				alert(msg);
				exception++;
			}
			
			if ( '<bean:write name= "casefileException" property="exceptionType"/>' == '<%=PDJuvenileConstants.HISPANIC_ENTRY_REQUIRED%>' ){
				msg = "Override not allowed on Juvenile with Hispanic entry required.\n";
				alert(msg);
				exception++;
			}
			
			if ( '<bean:write name= "casefileException" property="exceptionType"/>' == '<%=PDJuvenileConstants.SUBSTANCE_ABUSE_TJJD_REQUIRED%>' ){
				msg = "Override not allowed on Juvenile with substance abuse TJJD required.\n";
				alert(msg);
				exception++;
			}
			
			if ( '<bean:write name= "casefileException" property="exceptionType"/>' == '<%=PDJuvenileConstants.SCHOOL_HISTORY_UPDATE_ONCE_YEAR_REQUIRED%>' ){
				msg = "Override not allowed on School history update required.\n";
				alert(msg);
				exception++;
			}
			
		}
	
	</logic:iterate>
 	
	/* if (document.forms[0].noOverrideTJJD.value == "true"){
	   msg = "Override not allowed on Casefiles with pending TJJD risk needing completion.\n";
	} removed for task #41376
	if (document.forms[0].noOverridePGMREF.value == "true"){
	   msg += "Override not allowed on Casefiles with Active Program Referrals.  Program Referral must be closed or transferred to an active casefile.\n";
	}
	if (document.forms[0].noOverrideGOALS.value == "true"){
	   msg += "Override not allowed on Casefiles with Goal not finalized.\n";
	}
	if (document.forms[0].noOverrideRULES.value == "true"){
	   msg += "Override not allowed on Casefiles with Rule not finalized.\n";
	}	
	if (document.forms[0].noOverrideGUARDIAN.value == "true"){
	   msg += "Override not allowed on Casefiles with in-home Guardian residential address or assigned status issue.\n";
	}
	if (document.forms[0].noOverrideNONCOMP.value == "true"){
	   msg += "Override not allowed on Casefiles with incomplete Non-Compliance letter status.\n";
	}
	if (document.forms[0].noOverrideFACILITY.value == "true"){
		   msg += "Override not allowed on casefiles when juvenile is in facility.\n";
		}*/
	if (msg == ""){		  	
		show( 'defaultInstructions', HIDE_ITEM, 'row' ) ;
		show( 'overrideInstructions', SHOW_ITEM, 'row' ) ;
		showHide( 'overrideTable', SHOW_ITEM ) ;
		showHide( 'defaultButtons', HIDE_ITEM );
		showHide( 'summaryButtons', SHOW_ITEM );
		
		$("#reasonToOverride").empty();
		$("#exceptionOverrideComments").val("");
		$('input[type=radio][name=overrideSelection]').prop("checked", false);
	} 
}

function textCounter( field, maxlimit )
{
  if( field.value.length > maxlimit )
  {
    alert( 'Maximum text length of ' + maxlimit + ' reached for this field - input has been truncated.' );
    field.value = field.value.substring( 0, maxlimit -1 );
  } 
  else
  {
    maxlimit = maxlimit - field.value.length;
  }
}

function validateMandatoryFields()
{
	var msg = "";
	var ovrdcmts = document.forms[0].exceptionOverrideComments.value;
	
	if ( ! $("input[name='overrideSelection']:checked").length > 0 ){
	 	alert("Please select a radio button.\n");
	 	return false;
	}
	
	
	if(ovrdcmts== "")
	{
		msg = "Reason for overriding exceptions required.\n";
		alert(msg);
		return false;
	}
}
</script>

</head> 
<%--END HEAD TAG--%>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0" onload="return showOnLoad()">
<html:form action="processCasefileClosing.do" target="content" >

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|79">
<%-- Values for checking type of exception not allowing override  --%>
<!-- <input type="hidden" name="noOverrideTJJD" value=<bean:write name="casefileClosingForm" property="hasPendingTJJDException"/> > added for task 41376-->
<input type="hidden" name="noOverridePGMREF" value=<bean:write name="casefileClosingForm" property="hasActiveProgramReferral"/> >
<input type="hidden" name="noOverrideGUARDIAN" value=<bean:write name="casefileClosingForm" property="hasGuardianException"/> >
<input type="hidden" name="noOverrideGOALS" value=<bean:write name="casefileClosingForm" property="hasGoalsException"/> >
<input type="hidden" name="noOverrideRULES" value=<bean:write name="casefileClosingForm" property="hasRulesException"/> >
<input type="hidden" name="noOverrideNONCOMP" value=<bean:write name="casefileClosingForm" property="hasNonComplianceIncompleteStatus"/> >
<input type="hidden" name="noOverrideFACILITY" value=<bean:write name="casefileClosingForm" property="hasJuvenileInFacility"/> >
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.close"/> <bean:message key="title.casefile"/> 
		- <bean:message key="title.casefile"/> <bean:message key="title.closing"/> <bean:message key="title.exceptions"/>
		</td>
	</tr>
</table>
<table width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign='top' align='center' class='errorAlert'>The Closing requirements have not been met. The following need to be completed before the casefile can be closed.</td>
	</tr>
</table>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<%-- END HEADING TABLE --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%">
	<tr>
   		<td>
	   		<ul id='defaultInstructions'>
	    		<li>Select a "Fix" hyperlink associated to the exception to perform corrections.</li>
	    		<li>Select the Back button to return to the previous page.</li>
	    		<li>Select the Override Exceptions button to override all the exceptions listed.</li>
	  		</ul>
	    		
	  		<ul id='overrideInstructions' class='hidden'>
	    		<li>Enter the reason why the exceptions are being overridden.</li>
	    		<li>Select the Submit button to continue to the Summary page.</li>
	  		</ul>
	   	</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>
<div class='spacer'></div>
<%-- BEGIN EXCEPTIONS TABLE --%>
<table width='98%' cellpadding='2' cellspacing='0' class='borderTableBlue' align="center">			
	<tr>
		<td class='detailHead'><bean:message key="prompt.exceptions"/></td>
	</tr>
				
	<logic:empty name="casefileClosingForm" property="casefileExceptions">
		<tr class='normalRow'>
			<td>There are no Exceptions</td>
		</tr>
	</logic:empty>

	<logic:notEmpty name="casefileClosingForm" property="casefileExceptions">
		<tr>
			<td>
				<table width='100%' cellpadding=2 cellspacing=1>						
					<tr bgcolor='#cccccc'>
						<td class='subHead' width='1%'>&nbsp;</td>
						<td class='subHead' colspan='2'><bean:message key="prompt.message"/></td>
					</tr>

					<logic:iterate id="casefileException" name="casefileClosingForm" property="casefileExceptions" indexId="index">
						<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">

							<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.MAYSI_NOT_DONE%>" >
								<td><a href="/<msp:webapp/>displayMAYSIResults.do?juvenileNum=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>&casefileId=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>">Fix</a></td>
								<td><bean:message key="error.AssessmentNotDone" arg0="MAYSI"/></td>
							</logic:equal>	
							<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.MAYSI_DATE_PRIOR_ACTIVATION_DATE%>" >
								<td><a href="/<msp:webapp/>displayMAYSIResults.do?juvenileNum=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>&casefileId=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>">Fix</a></td>
								<td>MAYSI SUBSEQUENT data entry needs to be entered prior to casefile closure.</td>
							</logic:equal>						
							<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.CASEPLAN_STATUS_NOT_ACKNOWLEDGED%>" >
								<td><a href="/<msp:webapp/>displayCaseplanDetails.do?submitAction=<bean:message key='button.link'/>&casefileId=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>">Fix</a></td>
								<td><bean:message key="error.CaseplanAcknowledged"/></td>
							</logic:equal>
							<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.BENEFITS_NOT_DONE%>" >										 		
								<td><a href="/<msp:webapp/>displayBenefitsAssessmentCreate.do?submitAction=<bean:message key="title.benefitsAssessments"/>">Fix</a></td>
								<td><bean:message key="error.AssessmentNotDone" arg0="BENEFITS"/></td>
							</logic:equal>
							
							<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.RISK_NOT_DONE%>" >
								<td><a href="/<msp:webapp/>displayRiskResults.do?juvenileNum=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>&casefileID=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>">Fix</a></td>
								<td><bean:message key="error.AssessmentNotDone" arg0="RISK"/></td>
							</logic:equal>
								  
							<%-- <logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.TJJD_NOT_COMPLETED%>" >
								<td><a href="/<msp:webapp/>displayRiskResults.do?juvenileNum=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>&casefileID=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>">Fix</a></td>
								<td><bean:message key="error.tjjdNotCompleted"/></td>
							</logic:equal> removed for task 41376--%> 

							<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.PROG_REFERRALS_NOT_DONE%>" >									 		
								<td><a href="/<msp:webapp/>displayProgramReferralDetails.do?submitAction=<bean:message key='button.link'/>&referralId=<bean:write name="casefileException" property="exceptionId" />&action=close">Fix</a></td>
								<td><bean:message key="error.assessmentNeeded" arg0="PROGRAM REFERRAL"/>
									<bean:message key="prompt.programReferral"/>												
									<bean:write name="casefileException" property="exceptionId" /> 
									
									<logic:notEqual name="casefileException" property="exceptionMessage" value="">
										<bean:write name="casefileException" property="exceptionMessage" /> 
									</logic:notEqual>
									
									<logic:equal name="casefileException" property="exceptionMessage" value="">
										<bean:message key="error.notFinalized" />
									</logic:equal>
								</td>
							</logic:equal>	 
  
							<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.GOALS_NOT_DONE%>" >									 		
								<td><a href="/<msp:webapp/>displayGoalDetails.do?submitAction=Edit&selectedValue=<bean:write name="casefileException" property="exceptionId" />">Fix</a></td>
								<td><bean:message key="error.AssessmentNotDone" arg0="GOALS"/>
									<bean:message key="prompt.goal"/>												
									<bean:write name="casefileException" property="exceptionId" /> 
									<bean:message key="error.notFinalized" />
								</td>
							</logic:equal>	                                 
									
							<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.RULES_NOT_DONE%>" >									 		
								<td><a href="/<msp:webapp/>displayCasefileSupervisionRuleList.do?submitAction=Display Rule Details&selectedValue=<bean:write name="casefileException" property="exceptionId" />">Fix</a></td>
								<td><bean:message key="error.AssessmentNotDone" arg0="RULES"/>
									<bean:message key="prompt.rule"/>												
									<bean:write name="casefileException" property="exceptionId" /> 
									<bean:message key="error.notFinalized" />
								</td>
							</logic:equal>
							
						<%-- guardian has "in home" status exception --%>
							<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.GUARDIAN_IN_HOME_STATUS%>" >   
								<td><a href="/<msp:webapp/>displayFamilyConstellationList.do?submitAction=Details&juvNum=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>">Fix</a></td>
								<td><bean:message key="error.inHomeGuardianAddressNotFound"/></td>
							</logic:equal>
							
						<%-- Single guardian has address exception --%>
							<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.GUARDIAN_IN_HOME_ADDRESS_NOT_FOUND%>" >   
								<td><a href="/<msp:webapp/>displayManageFamilyMemberAddressAction.do?submitAction=Link&action=updateMember&juvenileNum=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>">Fix</a></td>
								<td><bean:message key="error.inHomeGuardianAddressMissing"/></td>
							</logic:equal>
							
						<%-- Both guardians have address exception --%>
							<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.GUARDIANS_IN_HOME_ADDRESS_NOT_FOUND%>" >   
								<td><a href="/<msp:webapp/>displayFamilyConstellationList.do?submitAction=Details&juvNum=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>">Fix</a></td>
								<td><bean:message key="error.inHomeGuardianAddressMissing"/></td>
							</logic:equal>

						<%-- Non Compliance record found with completion status not equal Yes --%>
							<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.NON_COMPLIANCE_COMPLETION_NO_STATUS_FOUND%>" >   
								<td><a href="/<msp:webapp/>displayJuvenileCasefileNonComplianceNoticeList.do?submitAction=Link">Fix</a></td>
								<td><bean:message key="error.nonComplianceCompletionNoStatusFound"/></td>
							</logic:equal>
							
						<%-- Pre-adjudication casefile PACT not done exception --%>
							<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.PACT_NOT_DONE%>" >   
								<td><a href="/<msp:webapp/>displayJuvenileCasefileAssignedReferralsList.do?submitAction=Referrals&casefileId=casefileId&supervisionNum=<bean:write name="juvenileCasefileForm" property="supervisionNum"/>">Fix</a></td>
								<td><bean:message key="error.pactNotDone"/></td>
							</logic:equal>
							
							<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.JUVENILE_IN_FACILITY%>" >   
								<td></td>
								<td><bean:message key="error.cannotCloseCasefile"/></td>
							</logic:equal>
							
							<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.JUVENILE_REFFERAL_NOT_ASSOCIATED%>" >   
								<td></td>
								<td><bean:message key="error.cannotCloseCasefileReferral"/></td>
							</logic:equal>
							
							<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.JUVENILE_REFFERAL_ASSOCIATED%>" >									 		
								<td></td>
								<td> 
									<logic:notEqual name="casefileException" property="exceptionMessage" value="">
										<bean:write name="casefileException" property="exceptionMessage" /> 
									</logic:notEqual>
								</td>
							</logic:equal>
							
						<%-- VOP details required US 161877 STARTS--%>
						 	<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.VOP_DETAILS_REQUIRED%>" >									 		
								<td><a href="/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Referrals&juvenileNum=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>">Fix</a></td>
						   <%-- <td><a href="/<msp:webapp/>handleJuvenileProfileVOPsAction.do?submitAction=addVOPFromCaseFileClosing&juvenileNum=<bean:write name="juvenileCasefileForm" property="juvenileNum"/>&referralNum=<bean:write name="casefileException" property="exceptionId" />">Fix</a></td> --%> 
								<td><bean:message key="error.VOPNotDone"/> </td>
							</logic:equal> 
						<%-- VOP details required US 161877 ENDS--%>
						
							<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.PROGRAM_REFERRAL_END_DATE_AFTER%>" >									 		
								<td><a href="/<msp:webapp/>handleProgramReferral.do?submitAction=Fix&selectedValue=<bean:write name="casefileException" property="exceptionId" />">Fix</a></td>
								<td> 
									<logic:notEqual name="casefileException" property="exceptionMessage" value="">
										<bean:write name="casefileException" property="exceptionMessage" /> 
									</logic:notEqual>
								</td>
							</logic:equal>
							
							<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileConstants.HISPANIC_ENTRY_REQUIRED%>" >									 		
								<td><a href="/<msp:webapp/>displayJuvenileProfileUpdate.do?juvenileNum=<bean:write name="casefileException" property="exceptionId" />">Fix</a></td>
								<td> 
									<logic:notEqual name="casefileException" property="exceptionMessage" value="">
										<bean:write name="casefileException" property="exceptionMessage" /> 
									</logic:notEqual>
								</td>
							</logic:equal>
							
							<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileConstants.SUBSTANCE_ABUSE_TJJD_REQUIRED%>" >									 		
								<td><a href="/<msp:webapp/>displaySubstanceAbuseCreate.do?juvenileNum=<bean:write name="casefileException" property="exceptionId" />">Fix</a></td>
								<td> 
									<logic:notEqual name="casefileException" property="exceptionMessage" value="">
										<bean:write name="casefileException" property="exceptionMessage" /> 
									</logic:notEqual>
								</td>
							</logic:equal>	
							
							
							<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileConstants.SCHOOL_HISTORY_UPDATE_ONCE_YEAR_REQUIRED%>" >									 		
								<td><a href="/<msp:webapp/>displayJuvenileSchool.do?submitAction=Tab&action=View">Fix</a></td>
								<td> 
									<logic:notEqual name="casefileException" property="exceptionMessage" value="">
										<bean:write name="casefileException" property="exceptionMessage" /> 
									</logic:notEqual>
								</td>
							</logic:equal>			
						</tr>
					</logic:iterate>	
				</table>
			</td>
		</tr>
	</logic:notEmpty>
</table>
<%-- END EXCEPTIONS TABLE --%>		

<!-- override exceptions table/comments section -->
<span class='hidden' id='overrideTable'>
	<div class='spacer'></div>
	<table align='center' width='98%' cellpadding='2' cellspacing='0' class='borderTableBlue'>
		<tr>
			<td class=detailHead>Override Exceptions (Max. characters allowed: 255)</td>
		</tr>
		<tr>
			<td>
				<table  width='100%' cellpadding='2' cellspacing='1'>
					<logic:iterate id="casefileException" name="casefileClosingForm" property="casefileExceptions" indexId="index">
						<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.MAYSI_NOT_DONE%>" >
							<tr>
								<td class='formDe'>
									<html:radio name="casefileClosingForm" property="overrideSelection" value="MAYSI" styleId="maysiSelection"/><b>MAYSI</b></td>
								</td>
								
							</tr>
						</logic:equal>
						<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.PACT_NOT_DONE%>" >   
							<tr>
								<td  class='formDe'>
									<html:radio name="casefileClosingForm" property="overrideSelection" value="PACT" styleId="pactSelection"/><b>PACT</b></td>
								</td>
								
							</tr>
						</logic:equal>
					</logic:iterate>
				</table>
				Reason to Override
				 <html:select styleId="reasonToOverride" name="casefileClosingForm" property="overrideSelection">
		 			<html:option value="">Please Select</html:option>
	 			</html:select>
			</td>
			
		</tr>
		
		<tr>
			<td>
				<table width='100%' cellpadding='2' cellspacing='1'>
					<tr >
						<td class='formDeLabel' width='1%'><bean:message key="prompt.diamond"/>Reason for overriding exceptions</td>
					</tr>
					<tr>
						<td class='formDe'>
							<html:textarea styleId="exceptionOverrideComments" name="casefileClosingForm" property="exceptionOverrideComments" style="width:100%" rows="3" onmouseout="textCounter(this,255)" onkeydown="textCounter(this,255)"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</span>
<!-- end override exceptions table/comments section -->
<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
<table width="100%">
	<tr>
		<td align="center">
			<span id='defaultButtons'>
				<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
					<logic:equal name="casefileClosingForm" property="caseLoadManager" value="true">
					<input type="button" value="Override Exceptions"   name="overrideButton" id='overrideButton' onclick="processOverrideButton();">
					</logic:equal>
				
				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
			</span>
						
			<span class='hidden' id='summaryButtons'>
				<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
				<html:submit property="submitAction" onclick="return validateMandatoryFields();"><bean:message key="button.submit"/></html:submit>
				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
			</span>
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>
<div class='spacer'></div>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>