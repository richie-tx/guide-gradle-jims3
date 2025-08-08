<!DOCTYPE HTML>

<%-- Used for Create Juvenile Referrals --%>
<%--MODIFICATIONS --%>
<%-- UGopinath 10/25/2018	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!-- Changes for JIMS200077276 Starts -->
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>

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
<html:base />
<title><bean:message key="title.heading" /> - createJuvenileReferral.jsp</title>


<%-- Javascript for emulated navigation --%>
<html:javascript formName="createReferralForm"/>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>


<script type='text/javascript' src="/<msp:webapp/>js/referral/referral.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	
	if ( '<bean:write name="juvenileReferralForm" property="refExcludedReporting" />' == '1' ) {
		$("#excludeReporting").prop("checked", true);
	} else {
		$("#excludeReporting").prop("checked", false);
	}
	
	$("#excludeReporting").change(function(){
		console.log("Check change");
		if (this.checked){
			$("#refExcludedReporting").val(1);
			console.log( $("#refExcludedReporting").val());x
		} 
		
		if (!this.checked){
			$("#refExcludedReporting").val(0);
			console.log( $("#refExcludedReporting").val());
		}
	})
	
	$("#btnCasefileActivate").click(function(){
		spinner();
	})
	$("#btnReturnReferralBriefing").click(function(){
		spinner();
	})
	$("#btnReturnFacilityBriefing").click(function(){
		spinner();
	})
	$("#createVopDetailsBtn").click(function(){
		spinner();
	})
	$("#generateReceipt").click(function(){
			spinner();
			$.ajax({
		        url: '/JuvenileCasework/submitCreateReferral.do?submitAction=Generate Referral Receipt',
		        type: 'post',
		        data: $('form#createReferralReport').serialize(),
		        success: function(data, textStatus , xhr) {
		         	if (200 == xhr.status){
		         		$(".overlay").remove();
		         	}
		        }
		    });
		})
})
</script>

</head>
<%-- ANY changes made to this page, please check if it is relevant to juvenileReferralBriefingDetailsPOPUP.jsp --%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<%--BEGIN FORM TAG--%>
<html:form styleId="createReferralReport" action="/submitCreateReferral" target="content" >
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|162">       

<%-- BEGIN HEADING TABLE --%>
</br>
<table width='100%'>
  <tr>
    <td align="center" class="header"> Process Referrals - Create Referral and Assignment Record</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<logic:messagesPresent message="true"> 
	<table width="100%">
		<tr>		  
			<td align="center" class="messageAlert"><html:messages id="message" message="true"><bean:write name="message"/></html:messages></td>		  
		</tr>   	  
	</table>
</logic:messagesPresent> 
</br>

<logic:equal name="juvenileReferralForm" property="action" value="confirmCreate">
	<logic:notEqual name="juvenileReferralForm" property="message" value="">
		<table width="100%">
			<tr>
				<td class="confirm"><bean:write name="juvenileReferralForm" property="message"/></td>
			</tr>
			
		</table>
	</logic:notEqual>	
	
	<logic:notEqual name="juvenileReferralForm" property="subsequentMessage" value="">
		<table width="100%">
			<tr>
				<td class="confirm"><bean:write name="juvenileReferralForm" property="subsequentMessage"/></td>
			</tr>
		</table>
	</logic:notEqual>
			
</logic:equal>
<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>
</br>

<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="98%" border="0">
		
		<tr>
			<td class="required"><bean:message key="prompt.requiredFields" /></td>
		</tr>
		<tr>
			<td>
				<ul>
				     <li>To add one or more offense(s), click the Add Offense button after entering offense details. </li>
				     <li>Enter required fields then click the Add Referral button.</li>
				     <li>To print the Referral Receipt, select the radio button associated to the referral record and click Generate Referral Receipt.</li>
				     <li>To create additional referral records, click Create Next Referral after completing the referral and assignment record.</li>
				     
				</ul>
			</td>
		</tr>
		
	</table>
	<%-- END INSTRUCTION TABLE --%>




<%-- BEGIN INQUIRY TABLE --%>

<%-- END INQUIRY TABLE --%>

<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
	
		
		<%-- BEGIN INFORMATION TABLE --%>
		<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
			<tr>
				<td width='50%' valign="top">
					<%--general info start --%>
						<table width="100%" cellpadding="0" cellspacing="0" class='borderTableBlue'>
							<tr class='referralDetailHead'>
								<td colspan= '2' class='paddedFourPix' align='left' nowrap='nowrap'>Profile Information</td>
							</tr>
							<tr>
								<td colspan="2">
 									<table width="100%" cellpadding="2" cellspacing="0" border='0'>
										<tr>
 											<td valign='top'>
												<table width="100%" cellpadding="2" cellspacing="1">
												<tr>
													<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.juvenile#"/></td>
													<td class='formDe'>
														<logic:equal name="juvenileBriefingDetailsForm" property="restrictedAccessTrait" value="true"><font color="red"><b><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.juvenileNum"/></b></font></logic:equal>	
														<logic:notEqual name="juvenileBriefingDetailsForm" property="restrictedAccessTrait" value="true"><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.juvenileNum"/></logic:notEqual>
														<logic:equal name="juvenileBriefingDetailsForm" property="profileDetail.recType" value="I.JUVENILE">
														&nbsp;&nbsp;&nbsp;&nbsp;<font color="Red" face="verdana"><b><span title='Purged'>P</span></b></font>
														</logic:equal>
														<logic:equal name="juvenileBriefingDetailsForm" property="profileDetail.recType" value="S.JUVENILE">
														&nbsp;&nbsp;&nbsp;&nbsp;<font color="Red" face="verdana"><b><span title='Sealed'>S</span></b></font>
														</logic:equal>
													</td>
													<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.juvenileName"/></td>
													<td class='formDe' colspan='3'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.formattedName"/> 
														<logic:equal name="juvenileBriefingDetailsForm" property="restrictedAccessTrait" value="true"><span title='RESTRICTED ACCESS'> <font color="red"><b>(RESTRICTED)</b></font></span></logic:equal>
															&nbsp;
												  			<logic:equal name="juvenileBriefingDetailsForm" property="traitTypeId" value="MER">
												  				<span style="color:#8417A0; font-weight: bold;" title="<bean:write name="juvenileBriefingDetailsForm" property="traitTypeDescription"/>">(M)</span>
												  			</logic:equal>
													</td>
													<td class='formDeLabel' nowrap='nowrap' width="1%"><bean:message key="prompt.hispanic" />?</td>
													<td class='formDe' colspan='4'><bean:write name="juvenileBriefingDetailsForm" property="hispanic"/></td>
												</tr>
											
												<tr>
													<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.currentAge"/></td>
													<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="age"/>
													<td class='formDeLabel' width="1%"><bean:message key="prompt.race" /></td>
													<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.race"/></td>
													<td class='formDeLabel' width="1%" ><bean:message key="prompt.sex" /></td>
													<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.sex"/></td>
													<td class='formDeLabel'> <bean:message key="prompt.dob"/></td>
													<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.dateOfBirth" formatKey="date.format.mmddyyyy"/></td>														
													<td class='formDeLabel' width="1%" ><bean:message key="prompt.operator"/></td>
													<td class='formDe'><span title="<bean:write name="juvenileReferralForm" property="operatorDesc" />"><bean:write name="juvenileReferralForm"property="operator"/></span></td>
												</tr>											

									</table>				
							<div class='spacer'></div>						
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<div class='spacer'></div>
			<tr>
				<td colspan="2">
					<table width="100%" cellpadding="2" cellspacing="0" border='0'>
						<tr class='referralDetailHead'>
							<td  class='paddedFourPix' align='left' nowrap='nowrap'>Referral Details</td>
							
						</tr>
						<tr>
							<td colspan="2">
								<table width="100%" cellpadding="2" cellspacing="1" border='0'>
								<tr>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.referral"/></td>
									<td class='formDe'>
										<html:text name="juvenileReferralForm" property="newRefNum" styleId='newRefNum' disabled="true" maxlength="4" size="4"/>
									</td>
									<td class='formDeLabel' nowrap='nowrap' width="5%"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.referralDate"/></td>
									<td class='formDe'><html:text styleId="newRefDate" property="newRefDate" size="10" maxlength="10"/></td>
									<td class='formDeLabel' nowrap='nowrap' width="5%"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.referralSource"/></td>									
									<td class='formDe'>
										<html:select property="newRefSource" styleId="newRefSource">
									   	 	<html:option value="">
												<bean:message key="select.generic" />
											</html:option>
											<html:optionsCollection property="refSources" value="code" label="codeWithDescription" />
										</html:select></td>
									<td class='formDeLabel' nowrap='nowrap' width="5%"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.intakeDecision"/></td>
									<td class='formDe'>
										<html:select property="newRefIntakeDecision" styleId="newRefIntakeDecision">
									   	 	<html:option value="">
												<bean:message key="select.generic" />
											</html:option>
											<html:optionsCollection property="intakeDecisions" value="code" label="codeWithDescription" />
										</html:select></td>
									<td class='formDeLabel' nowrap='nowrap' width="5%"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.intakeDate"/></td>
									<td class='formDe'><html:text styleId="newRefIntakeDate" property="newRefIntakeDate" size="10" maxlength="10"/></td>
								</tr>
								<tr>
									<jims:isAllowed requiredFeatures="<%=Features.JRP_REFASSIGNMENT_EXCREFRPT_CHK%>">
										<td class='formDe' colspan="10">
											<logic:equal name="juvenileReferralForm" property="refExcludedReporting" value="0">
												<input type="checkbox" id="excludeReporting" name="excludeReporting" value="false"/>
											</logic:equal>
											<logic:equal name="juvenileReferralForm" property="refExcludedReporting" value="1">
												<input type="checkbox" id="excludeReporting" name="excludeReporting" value="true" checked/>
											</logic:equal>
											<html:hidden styleId="refExcludedReporting" property="refExcludedReporting"/>
											Exclude Referral from Reporting
										</td>
									</jims:isAllowed>
								</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<div class='spacer'></div>
			<tr>
				<td colspan="2">
					<table width="100%" cellpadding="2" cellspacing="0" border='0'>
						<tr class='referralDetailHead'>
							<td colspan= '2' class='paddedFourPix' align='left' nowrap='nowrap'>Offense Details</td>
						</tr>
						<tr>
							<td colspan="2">
								<table width="100%" cellpadding="2" cellspacing="1" border='0'>
								<tr>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.offenseCode"/></td>
									<td class='formDe' width="50%"><html:text styleId="offenseCode" property="offenseCode" size="8" maxlength="8"/>
									<html:submit property="submitAction" styleId="validateCode"><bean:message key="button.validateOffenseCode" /></html:submit>&nbsp;Or&nbsp;
									<html:submit property="submitAction" onclick="spinner();" ><bean:message key="button.searchForOffenseCode" /></html:submit>
									</td>
									<td class='formDe' valign='top'></td><td class='formDe' valign='top'></td>
									
								</tr>
								<tr>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.offenseDate"/></td>
									<td class='formDe'><html:text styleId="offenseDate" property="offenseDate" size="8" maxlength="10"/></td>
									<td class='formDe' valign='top'></td><td class='formDe' valign='top'></td>
								</tr>
								<tr>
								
										<td class='formDeLabel' valign='top'  nowrap="nowrap" ><bean:message key="prompt.keymap"/> </td>
										<td class='formDe'><html:text styleId="keyMapLocation" property="referralOffenseBean.keyMapLocation" size="6" maxlength="6"/></td>
									
										<td class='formDeLabel' width="5%" ><bean:message key="prompt.investigation"/># </td> <td class='formDe'><html:text styleId="investigationNum" property="referralOffenseBean.investigationNum" size="10" maxlength="10"/></td>
											
									
								</tr>
								</table>
							</td>
						</tr>
						
							<tr>
								<td colspan="6" class="formDe">
									<div class="paddedFourPix" align="center" >
									
									<html:submit property="submitAction"  styleId="addToListBtn"> <bean:message key="button.addOffense" /></html:submit>&nbsp;  
										<html:submit property="submitAction"  styleId="refresh"> <bean:message key="button.refresh" /></html:submit> 								  
									
						   			
						  		</div>
								</td>
							</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td colspan="6">
		  			<logic:notEmpty name="juvenileReferralForm" property="offenseList">	              
					 <table width="100%" bgcolor="#cccccc" cellspacing="1">
							<tr bgcolor="#f0f0f0">
								<td class="subhead" valign="top" ><bean:message key="prompt.remove"/></td>
								<td class="subhead" valign="top"  width="5%" nowrap="nowrap"><bean:message key="prompt.offenseCode"/></td>
								<td class="subhead" valign="top" width="10%"><bean:message key="prompt.offense"/> <bean:message key="prompt.description"/></td>
								<td class="subhead" valign="top" width="10%" nowrap="nowrap"><bean:message key="prompt.offenseDate"/></td>
								
							</tr>
							<logic:iterate id="offenseBeans" name="juvenileReferralForm" property="offenseList" indexId="index">
								<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
									<td valign="top" align="center" width="1%">                               
										<a class="rm-link" onclick="spinner();" href="/<msp:webapp/>submitCreateReferral.do?submitAction=Remove&selectedValue=<%= index.intValue()%>"><bean:message key="prompt.remove"/></a>
									</td>
									<td valign="top"><bean:write name="offenseBeans" property="offenseCode"/></td>
									<td valign="top"><bean:write name="offenseBeans" property="offenseDescription"/></td>
									<td valign="top"><bean:write name="offenseBeans" property="offenseDate" formatKey="date.format.mmddyyyy"/></td>
								</tr>
							</logic:iterate>
							
						</table>
					</logic:notEmpty>
				</td>
			</tr>

			<div class='spacer'></div>
			<%--Referral list start --%>
			<logic:notEmpty name="juvenileReferralForm" property="originalChargeReferrals">
				<tr class='referralDetailHead'>
					<td colspan= '2' class='paddedFourPix' align='left' nowrap='nowrap'>Original Charge Referrals</td>
				</tr>
			</logic:notEmpty>
			<tr>
				<td colspan="2">
					<logic:notEmpty name="juvenileReferralForm" property="originalChargeReferrals">		              
					 <table width="100%" bgcolor="#cccccc" cellspacing="1">
							<tr bgcolor="#f0f0f0" colspan=6>
							<td></td>
							<td class="subhead" valign="top"><bean:message key="prompt.refNo"/>							
							</td>
							<td class="subhead" valign="top"><bean:message key="prompt.referralDate"/>							
							</td>
							<td class="subhead" valign="top"><bean:message key="prompt.offense"/>						
							</td>
							<td class="subhead" valign="top"><bean:message key="prompt.offenseCategory"/>							
							</td>
							<td class="subhead" valign="top"><bean:message key="prompt.intakeDecision"/>							
							</td>
							<td class="subhead" valign="top"><bean:message key="prompt.intakeDate"/>						
							</td>
							<td class="subhead" valign="top"><bean:message key="prompt.petitionNumber"/>						
							</td>
							<td class="subhead" valign="top"><bean:message key="prompt.disposition"/>						
							</td>
							<td class="subhead" valign="top"><bean:message key="prompt.decisionDate"/>				
							</td>
							<td class="subhead" valign="top"><bean:message key="prompt.currentSuprvision"/>						
							</td>
							<td class="subhead" valign="top"><bean:message key="prompt.JPO"/>						
							</td>
							<td  class="subhead" valign="top"><bean:message key="prompt.referralStatus"/></td>
							<td class="subhead" valign="top"><bean:message key="prompt.closedDate"/></td>
								
							</tr>
							<logic:iterate id="referralList" name="juvenileReferralForm" property="originalChargeReferrals" indexId="index">
							
							<logic:notEqual name="referralList" property="recType" value="REFERRAL">						
								<tr class ="row-disabled" class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">							
							</logic:notEqual>						
							<logic:equal name="referralList" property="recType" value="REFERRAL">
							<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
							</logic:equal>
								<td align="center" ><input type="radio" name="selectedValue" value="<bean:write name="referralList" property="referralNumber"/>" id="chargeReferralRadio"></td>
								<td class='formDe' ><bean:write name="referralList" property="referralNumber"/></td>
								<td class='formDe' ><bean:write name="referralList" property="referralDate" formatKey="date.format.mmddyyyy"/></td>
								<td class='formDe' ><span title="<bean:write name="referralList" property="offenseDesc"/>"><bean:write name="referralList" property="offense"/></span></td>
								<td class='formDe' ><bean:write name="referralList" property="offenseCategory"/></td>
								<td class='formDe' ><span title="<bean:write name="referralList" property="intakeDecision"/>"><bean:write name="referralList" property="intakeDecisionId"/></span></td>
								<td class='formDe' ><bean:write name="referralList" property="intakeDecDate" formatKey="date.format.mmddyyyy"/></td>
								<td class='formDe' ><bean:write name="referralList" property="petitionNumber"/></td>
								<%-- <td class='formDe' ><bean:write name="referralList" property="courtDate" formatKey="date.format.mmddyyyy"/></td>
								<td class='formDe' ><span title="<bean:write name="referralList" property="courtResultDesc"/>"><bean:write name="referralList" property="courtResult"/></span></td> --%>
								<td class='formDe' ><span title="<bean:write name="referralList" property="finalDispositionDescription"/>"><bean:write name="referralList" property="finalDisposition"/></span></td>
								<td class='formDe' ><bean:write name="referralList" property="courtDate" formatKey="date.format.mmddyyyy"/></td>
								<td class='formDe' >
								<logic:notEmpty  name="referralList" property="supervisionCategory">
									<span title="<bean:write name="referralList" property="supervisionCategory"/>"><bean:write name="referralList" property="supervisionCategoryId"/></span>/<span title="<bean:write name="referralList" property="supervisionType"/>"><bean:write name="referralList" property="supervisionTypeId"/></span>
								</logic:notEmpty>
								</td>
								<td class='formDe' ><span title="<bean:write name="referralList" property="jpo"/>"><bean:write name="referralList" property="jpoId"/></span></td>					
								<td class='formDe' ><bean:write name="referralList" property="referralStatus"/></td>
								<td class='formDe' ><bean:write name="referralList" property="closeDate" formatKey="date.format.mmddyyyy"/></td>
							</tr>
							</logic:iterate>
							
						</table>
					</logic:notEmpty>
				</td>
			</tr>
			<%--Referral list end --%>
			<div class='spacer'></div>
			<tr>
				<td colspan="2">
					<table width="100%" cellpadding="2" cellspacing="0" border='0'>
						<tr class='referralDetailHead'>
							<td colspan= '2' class='paddedFourPix' align='left' nowrap='nowrap'>Assignment Details</td>
						</tr>
						
						<tr>
							<td colspan="2">
								<table width="100%" cellpadding="2" cellspacing="1" border='0'>
								<tr>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.assignmentType"/></td>
									<td class='formDe' colspan= '4'>
									<jims2:if name="juvenileReferralForm" property="disbleAssignment" op="equal" value="Y">
									<jims2:or name="juvenileReferralForm" property="disableForTRN" value="Y" op="equal" />
										<jims2:then>	
											<html:select property="assignmentType" styleId="assignmentType" disabled="true">
										   	 	<html:option value="">
													<bean:message key="select.generic" />
												</html:option>
												<html:optionsCollection property="assignmentTypes" value="code" label="description" />
											</html:select>
										</jims2:then>
										<jims2:else>
											<html:select property="assignmentType" styleId="assignmentType">
										   	 	<html:option value="">
													<bean:message key="select.generic" />
												</html:option>
												<html:optionsCollection property="assignmentTypes" value="code" label="description" />
											</html:select>
										</jims2:else>
									</jims2:if>
									</td>
								</tr>
								
								<tr>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.supervisionCategory"/></td>									
									<td class='formDe' colspan= '4'>
											
									<jims2:if name="juvenileReferralForm" property="assignmentType" op="equal" value="REC">	
									<jims2:or name="juvenileReferralForm" property="assignmentType" value="INT" op="equal" />
									<jims2:or name="juvenileReferralForm" property="assignmentType" value="SBQ" op="equal" />
									<jims2:or name="juvenileReferralForm" property="disbleAssignment" value="Y" op="equal" />										
											<jims2:then>
											  <html:select property="supervisionCat" styleId="supervisionCat" disabled="true">	
												<html:option value="">
												<bean:message key="select.generic" />
												</html:option>
												<html:optionsCollection property="supervisionCategories" value="code" label="description" />
										      </html:select>								   	 	
									   	 </jims2:then>
										<jims2:else>
											<html:select property="supervisionCat" styleId="supervisionCat">
												<html:option value="">
													<bean:message key="select.generic" />
												</html:option>
												<html:optionsCollection property="supervisionCategories" value="code" label="description" />
										    </html:select>
										</jims2:else>
									</jims2:if>
											
									</td>
								</tr>
								
								<tr>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.supervisionType"/></td>
									<td class='formDe' colspan= '4'>
									
										<jims2:if name="juvenileReferralForm" property="supervisionCat" op="equal" value="RC">	
										<jims2:or name="juvenileReferralForm" property="disbleAssignment" value="Y" op="equal" />		
										<jims2:or name="juvenileReferralForm" property="assignmentType" value="SBQ" op="equal" />										
											<jims2:then>
												<html:select property="supervisionType" styleId="supervisionType" disabled="true">
											   	 	<html:option value="">
														<bean:message key="select.generic" />
													</html:option>
													<html:optionsCollection property="supervisionTypes" value="code" label="description" />
												</html:select>
											</jims2:then>
										<jims2:else>
											<html:select property="supervisionType" styleId="supervisionType">
										   	 	<html:option value="">
													<bean:message key="select.generic" />
												</html:option>
												<html:optionsCollection property="supervisionTypes" value="code" label="description" />
											</html:select>
										</jims2:else>
									</jims2:if>
									</td>
								</tr>
								
								<tr>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.JPO"/></td>
									<td class='formDe'>
										<jims2:if name="juvenileReferralForm" property="assignmentType" op="equal" value="SBQ">	
										<jims2:or name="juvenileReferralForm" property="assignmentType" value="REC" op="equal" />	
										<jims2:or name="juvenileReferralForm" property="disbleAssignment" value="Y" op="equal" />										
											<jims2:then>
												<html:text styleId="jpo" property="jpo" size="5" maxlength="5" disabled="true"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
												<html:submit property="submitAction"  styleId="validateBtn2" disabled="true"> <bean:message key="button.validate" /></html:submit>&nbsp; 
											</jims2:then>
											<jims2:else>
												<html:text styleId="jpo" property="jpo" size="5" maxlength="5"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
												<html:submit property="submitAction"  styleId="validateBtn1"> <bean:message key="button.validate" /></html:submit>&nbsp;
												<input id="searchBtn" type="button" onclick="spinner();"  value="Search" /> 
											</jims2:else>
										</jims2:if>
									</td>
								
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.assignmentDate"/></td>
									<td class='formDe'><html:text styleId="assignmentDateStr" property="assignmentDateStr" size="8" maxlength="10"/></td>
								</tr>
							
								</table>
							</td>
						</tr>
						
						<tr>
								<td colspan="6" class="formDe">
									<div class="paddedFourPix" align="center" >									
									<html:submit property="submitAction" styleId="addRefBtn"> <bean:message key="button.addReferral" /></html:submit>&nbsp;  				   			
						  		</div>
								</td>
							</tr>
							
					</table>
				</td>
			</tr>
			
			<tr>
				<td colspan="6">
		  			<logic:notEmpty name="juvenileReferralForm" property="referralList">	              
					 <table width="100%" bgcolor="#cccccc" cellspacing="1">
							<tr bgcolor="#f0f0f0">
								<td width="1%"></td>
								<td class="subhead" valign="top" width="5%" ><bean:message key="prompt.referral"/></td>
								<td class="subhead" valign="top"  width="5%" nowrap="nowrap"><bean:message key="prompt.referralDate"/></td>
								<td class="subhead" valign="top" width="20%"><bean:message key="prompt.offense"/> </td>
								<td class="subhead" valign="top" width="5%"><bean:message key="prompt.intakeDecision"/></td>
								<td class="subhead" valign="top" width="5%" nowrap="nowrap"><bean:message key="prompt.intakeDate"/></td>
								<td class="subhead" valign="top" width="5%" nowrap="nowrap"><bean:message key="prompt.supervisionType"/></td>
								<td class="subhead" valign="top" width="10%" nowrap="nowrap"><bean:message key="prompt.JPO"/></td>
							</tr>
							<logic:iterate id="refBeans" name="juvenileReferralForm" property="referralList" indexId="index">
							<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
								<td valign="top"><input type="radio" name="selectedValue" value="<bean:write name="refBeans" property="referralNum"/>" styleId = "referralNumRadio" id="referralNumIdRadio"></td>
								<td valign="top"><bean:write name="refBeans" property="referralNum"/></td>								
								<td valign="top"><bean:write name="refBeans" property="referralDate" formatKey="date.format.mmddyyyy"/></td>
								<td valign="top"><bean:write name="refBeans" property="offenseList"/></td>
								<td valign="top"><span title='<bean:write name="refBeans" property="intakeDecisionDescr"/>'><bean:write name="refBeans" property="intakeDecision"/></span></td>
								<td valign="top"><bean:write name="refBeans" property="intakeDate" formatKey="date.format.mmddyyyy"/></span></td>
								<td valign="top"><span title='<bean:write name="refBeans" property="supervisionTypeDescr"/>'><bean:write name="refBeans" property="supervisionType"/></td>
								<td valign="top"><span title='<bean:write name="refBeans" property="jpoDescr"/>'><bean:write name="refBeans" property="jpo"/></span></td>
							</tr>
							</logic:iterate>
						</table>
					</logic:notEmpty>
				</td>
			</tr>
	</table>
	<html:hidden styleId="action" name="juvenileReferralForm" property="action"/>
	<html:hidden styleId="hascasefiles" name="juvenileBriefingDetailsForm" property="hasCasefiles"/>
	<html:hidden styleId="refNumber" name="juvenileReferralForm" property="selectedValue"/>	
	<html:hidden styleId="adminSelection" name="juvenileReferralForm" property="adminReferralFlag"/>
	<html:hidden styleId="addReferralFlag" name="juvenileReferralForm" property="disableAddRefBtn"/>
		<%-- END INFORMATION TABLE --%>
<%-- END DETAIL TABLE --%>
<%-- BEGIN BUTTON TABLE --%>
<table align="center" border="0" width="100%">
<tr><td></td></tr>
	<tr>
		<td align="center">	
			<logic:equal name="juvenileReferralForm" property="activateSupervision" value="Y">
				<input type="button" id="btnCasefileActivate" name="submitAction" value="<bean:message key='button.activateIntakeSup'/>" 
												onclick="changeFormActionURL(this.form.name, 
												'/<msp:webapp/>handleCasefileActivation.do?submitAction=Link&casefileID=<bean:write name='juvenileReferralForm' property='currentCasefileId'/>&action=default', true);">	 
			</logic:equal>
		  	
		  	<logic:equal name="juvenileReferralForm" property="action" value="confirmCreate">
		  	<%-- <html:submit property="submitAction" onclick="spinner()" styleId="createVopDetailsBtn"> <bean:message key="button.vopDetailsAdd" /></html:submit>&nbsp; --%>
		  	<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_VOP_DETAILS%>'>
		  	<input type="button" id="createVopDetailsBtn" name="submitAction" value="<bean:message key="button.vopDetailsCreate"/>" 
												onclick="changeFormActionURL(this.form.name, 
												'/<msp:webapp/>displayJuvenileProfileReferralList.do?submitAction=Referrals&juvenileNum=<bean:write name='juvenileBriefingDetailsForm' property='profileDetail.juvenileNum'/>', true);">
			</jims2:isAllowed>
		  	</logic:equal>
		  	
		  	<html:submit property="submitAction" styleId="generateReceipt"> <bean:message key="button.generateReferralReceipt" /></html:submit>&nbsp;  	
			<html:submit property="submitAction" onclick="spinner()" styleId="createNextRefBtn"> <bean:message key="button.createNextReferral" /></html:submit>&nbsp;
		</td>
	</tr>
	<tr>
		<td align="center">	
				<html:submit property="submitAction"  styleId="returnToSearchJuv" onclick="spinner();"> <bean:message key="button.returnToSearchJuv" /></html:submit>&nbsp; 			
				<input type="button" id="btnReturnReferralBriefing" name="submitAction" value="<bean:message key="button.returnToReferralBriefing"/>" 
												onclick="changeFormActionURL(this.form.name, 
												'/<msp:webapp/>displayJuvenileBriefingDetails.do?submitAction=referralLink&juvenileNum=<bean:write name='juvenileBriefingDetailsForm' property='profileDetail.juvenileNum'/>', true);">
				<input type="button" id="btnReturnFacilityBriefing" name="submitAction" value="<bean:message key='button.returnToFacilityBriefing'/>" 
												onclick="changeFormActionURL(this.form.name, 
												'/<msp:webapp/>displayJuvenileProfileSearchResults.do?isFacility=true&searchType=JNUM&juvenileNum=<bean:write name='juvenileBriefingDetailsForm' property='profileDetail.juvenileNum'/>', true);">
				
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
