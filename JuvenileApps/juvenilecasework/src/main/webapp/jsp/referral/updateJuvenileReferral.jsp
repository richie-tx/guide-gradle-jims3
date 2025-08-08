<!DOCTYPE HTML>

<%-- Used for Updaye Juvenile Referrals --%>
<%--MODIFICATIONS --%>
<%-- UGopinath 12/28/2018	Create JSP --%>

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
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>


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
<title><bean:message key="title.heading" /> - updateJuvenileReferral.jsp</title>


<%-- Javascript for emulated navigation --%>
<html:javascript formName="createReferralForm"/>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

<script type='text/javascript' src="/<msp:webapp/>js/referral/referral.js"></script>

<script type="text/javascript">

$(function() {
	$("#newRefIntakeDecision").on("change",function(){
		$("#newRefIntakeDate").datepicker('setDate', null);
		alert("Intake Decision has been modified. Please update Intake Decision Date.");
		$("#newRefIntakeDate").focus();
	});
});
$(document).ready(function(){	
	$("#btnReturntobriefing").click(function(){
		spinner();
	})
	$("#btnManageassignment").click(function(){
		spinner();
	})
	$("#btnOverrideassignment").click(function(){
		spinner();
	})
	$("#btnReturntofacilitybriefing").click(function(){
		spinner();
	})	
	$("#generateReceipt").click(function(){
			spinner();
			$.ajax({
		        url: '/JuvenileCasework/submitCreateReferral.do?submitAction=Generate Referral Receipt',
		        type: 'post',
		        data: $('form#updateJuvenileReferral').serialize(),
		        success: function(data, textStatus , xhr) {
		         	if (200 == xhr.status){
		         		$(".overlay").remove();
		         	}
		        }
		    });
		})
})

</script>
<style>
.row-disabled {
	   background-color: dfdfdf;
	   pointer-events: none;
	   width: 100%;
	}
</style>
</head>
<%-- ANY changes made to this page, please check if it is relevant to juvenileReferralBriefingDetailsPOPUP.jsp --%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<%--BEGIN FORM TAG--%>
<html:form styleId="updateJuvenileReferral"  action="/submitCreateReferral" target="content" >
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|162">       

<%-- BEGIN HEADING TABLE --%>
</br>
<table width='100%'>
  <tr>
    <td align="center" class="header"> Process Referrals - Update Referral Record</td>
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


	<logic:notEqual name="juvenileReferralForm" property="updateMessage" value="">
		<table width="100%">
			<tr>
				<td class="confirm"><bean:write name="juvenileReferralForm" property="updateMessage"/></td>
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
				     <li>To update a referral with one associated offense code, select the referral number and modify the referral and/or offense details. </li>
				     <li>To update a referral with multiple offenses, select the referral number and the offense code which requires update.  Modify the offense code and/or associated details.</li>
				     <li>To add additional offense codes to a referral number, select the referral number and add offense details.</li>
				     <li>To print the Referral Receipt, select the radio button associated to the referral record and click Generate Referral Receipt.</li>
				     <li>To update additional referral records, select the next referral number after completing a referral record update.</li>
				     
				</ul>
			</td>
		</tr>
		
	</table>
	<%-- END INSTRUCTION TABLE --%>


<div class='spacer'></div>
<%-- BEGIN DETAIL TABLE --%>
	
		
		<%-- BEGIN INFORMATION TABLE --%>
		<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
			<tr>
				<td width='50%' valign="top">
					<%--general info start --%>
					<table width="100%" cellpadding="0" cellspacing="0" >
							 <tr height='30px' class='referralDetailHead'>
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
													<td class='formDe'><bean:write name="juvenileBriefingDetailsForm" property="hispanic"/></td>
													<td class='formDeLabel' width="1%" nowrap="nowrap"><bean:message key="prompt.masterStatus"/></td>
													<td class='formDe' colspan='3'><span title='<bean:write name="juvenileBriefingDetailsForm" property="profileDetail.masterStatus"/>'><bean:write name="juvenileBriefingDetailsForm" property="profileDetail.masterStatusId"/></span></td>
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
			</table>
			</td>
			</tr>
			<div class='spacer'></div>
			<%--Referral list start --%>
			
			<tr>
				<td colspan="2">
					<logic:notEmpty name="juvenileReferralForm" property="referralList">	              
					 <table width="100%" bgcolor="#cccccc" cellspacing="1">
							<tr class="referralDetailHead" colspan=6>
							<td></td>
							<td><bean:message key="prompt.refNo"/>
							<jims2:sortResults sortId="1" beanName="juvenileReferralForm" results="referralList" primaryPropSort="referralNumber" primarySortType="String" defaultSort="true" defaultSortOrder="DESC"/>							
							</td>
							<td valign='top'><bean:message key="prompt.referralDate"/>
							<jims2:sortResults sortId="2" beanName="juvenileReferralForm" results="referralList" primaryPropSort="referralDate" primarySortType="Date"/>							
							</td>
							<td valign ='top'><bean:message key="prompt.offense"/>	
							<jims2:sortResults sortId="3" beanName="juvenileReferralForm" results="referralList" primaryPropSort="offense" primarySortType="String"/>					
							</td>
							<td valign='top'><bean:message key="prompt.offenseCategory"/>	
							<jims2:sortResults sortId="4" beanName="juvenileReferralForm" results="referralList" primaryPropSort="offenseCategory" primarySortType="String"/>						
							</td>
							<td valign = 'top'><bean:message key="prompt.intakeDecision"/>
							<jims2:sortResults sortId="5" beanName="juvenileReferralForm" results="referralList" primaryPropSort="intakeDecisionId" primarySortType="String"/>							
							</td>
							<td valign = 'top'><bean:message key="prompt.intakeDate"/>	
							<jims2:sortResults sortId="6" beanName="juvenileReferralForm" results="referralList" primaryPropSort="intakeDecDate" primarySortType="Date"/>					
							</td>
							<td valign = 'top'><bean:message key="prompt.petitionNumber"/>
							<jims2:sortResults sortId="7" beanName="juvenileReferralForm" results="referralList" primaryPropSort="petitionNumber" primarySortType="String"/>						
							</td>
							<td><bean:message key="prompt.disposition"/>
							<jims2:sortResults sortId="8" beanName="juvenileReferralForm" results="referralList" primaryPropSort="finalDisposition" primarySortType="String"/>						
							</td>
							<td valign = 'top'><bean:message key="prompt.decisionDate"/>
							<jims2:sortResults sortId="9" beanName="juvenileReferralForm" results="referralList" primaryPropSort="courtDate" primarySortType="Date"/>				
							</td>
							<td><bean:message key="prompt.currentSuprvision"/>	
							<jims2:sortResults sortId="10" beanName="juvenileReferralForm" results="referralList" primaryPropSort="finalDisposition" primarySortType="String"/>						
							</td>
							<td valign = 'top'><bean:message key="prompt.JPO"/>
							<jims2:sortResults sortId="11" beanName="juvenileReferralForm" results="referralList" primaryPropSort="jpo" primarySortType="String"/>							
							</td>
							<td valign="top"  ><bean:message key="prompt.referralStatus"/>
							<jims2:sortResults sortId="12" beanName="juvenileReferralForm" results="referralList" primaryPropSort="referralStatus" primarySortType="String"/>
							</td>
							<td  valign="top"  ><bean:message key="prompt.closedDate"/>
							<jims2:sortResults sortId="13" beanName="juvenileReferralForm" results="referralList" primaryPropSort="closeDate" primarySortType="Date"/>
							</td>
								
							</tr>
							<logic:iterate id="referralList" name="juvenileReferralForm" property="referralList" indexId="index">
							
							<logic:notEqual name="referralList" property="recType" value="REFERRAL">
								<jims2:isAllowed requiredFeatures='<%=Features.JCW_SEALPURGE_VIEW%>'>						
									<tr  class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
									<td><input type="radio" name="selectedValue" value="<bean:write name="referralList" property="referralNumber"/>" id="updateRefNumRadio" disabled/></td>
									<td><bean:write name="referralList" property="referralNumber"/></td>
									<td><bean:write name="referralList" property="referralDate" formatKey="date.format.mmddyyyy"/></td>
									<td><span title="<bean:write name="referralList" property="offenseDesc"/>, <bean:write name="referralList" property="offenseCategory"/>"><bean:write name="referralList" property="offense"/></span></td>
									<td><bean:write name="referralList" property="offenseCategory"/></td>
									<td><span title="<bean:write name="referralList" property="intakeDecision"/>"><bean:write name="referralList" property="intakeDecisionId"/></span></td>
									<td><bean:write name="referralList" property="intakeDecDate" formatKey="date.format.mmddyyyy"/></td>
									<td><bean:write name="referralList" property="petitionNumber"/></td>
									<td><span title="<bean:write name="referralList" property="finalDispositionDescription"/>"><bean:write name="referralList" property="finalDisposition"/></span></td>
									<td><bean:write name="referralList" property="courtDate" formatKey="date.format.mmddyyyy"/></td>
									<td>
									<logic:notEmpty  name="referralList" property="supervisionCategory">
										<span title="<bean:write name="referralList" property="supervisionCategory"/>"><bean:write name="referralList" property="supervisionCategoryId"/></span>/<span title="<bean:write name="referralList" property="supervisionType"/>"><bean:write name="referralList" property="supervisionTypeId"/></span>
									</logic:notEmpty>
									</td>
									<td><span title="<bean:write name="referralList" property="jpo"/>"><bean:write name="referralList" property="jpoId"/></span></td>					
									<td><bean:write name="referralList" property="referralStatus"/></td>
									<td><bean:write name="referralList" property="closeDate" formatKey="date.format.mmddyyyy"/></td>
									</tr>
								</jims2:isAllowed>							
							</logic:notEqual>						
							<logic:equal name="referralList" property="recType" value="REFERRAL">
							<html:hidden styleId="offenseCategory" name="referralList" property="offenseCategory"/>
							<html:hidden styleId="severitySubtype" name="referralList" property="severitySubtype"/>
							<bean:define id="cat" name="referralList" property="offenseCategory" type="java.lang.String" />
							<bean:define id="sev" name="referralList" property="severitySubtype" type="java.lang.String" />
							
							<%int admin = 0;
							if( "AC".equals(cat) && !"T".equals(sev) && !"P".equals(sev) ){
								admin =1;
							}
							%>
							<tr class="<%out.print(( admin == 0 ) ? "crimReferralRow" : "normalRow"); %>">
								<td><input type="radio" name="selectedValue" value="<bean:write name="referralList" property="referralNumber"/>" id="updateRefNumRadio"/></td>
								<td><bean:write name="referralList" property="referralNumber"/></td>
								<td><bean:write name="referralList" property="referralDate" formatKey="date.format.mmddyyyy"/></td>
								<td><span title="<bean:write name="referralList" property="offenseDesc"/>, <bean:write name="referralList" property="offenseCategory"/>"><bean:write name="referralList" property="offense"/></span></td>
								<td><bean:write name="referralList" property="offenseCategory"/></td>
								<td><span title="<bean:write name="referralList" property="intakeDecision"/>"><bean:write name="referralList" property="intakeDecisionId"/></span></td>
								<td><bean:write name="referralList" property="intakeDecDate" formatKey="date.format.mmddyyyy"/></td>
								<td><bean:write name="referralList" property="petitionNumber"/></td>
								<td><span title="<bean:write name="referralList" property="finalDispositionDescription"/>"><bean:write name="referralList" property="finalDisposition"/></span></td>
								<td><bean:write name="referralList" property="courtDate" formatKey="date.format.mmddyyyy"/></td>
								<td>
								<logic:notEmpty  name="referralList" property="supervisionCategory">
									<span title="<bean:write name="referralList" property="supervisionCategory"/>"><bean:write name="referralList" property="supervisionCategoryId"/></span>/<span title="<bean:write name="referralList" property="supervisionType"/>"><bean:write name="referralList" property="supervisionTypeId"/></span>
								</logic:notEmpty>
								</td>
								<td><span title="<bean:write name="referralList" property="jpo"/>"><bean:write name="referralList" property="jpoId"/></span></td>					
								<td><bean:write name="referralList" property="referralStatus"/></td>
								<td><bean:write name="referralList" property="closeDate" formatKey="date.format.mmddyyyy"/></td>
							</tr>
							</logic:equal>
							</logic:iterate>
							
						</table>
					</logic:notEmpty>
				</td>
			</tr>
			<%--Referral list end --%>
			
			<div class='spacer'></div>
			<tr>
				<td colspan="2">
					<table width="100%" cellpadding="2" cellspacing="0" border='0' id='rd'>
						 <tr height='30px' class='referralDetailHead'>
							<td colspan= '2' class='paddedFourPix' align='left' nowrap='nowrap'>Referral Details</td>
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
									<td class='formDeLabel' nowrap='nowrap' width="1%"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.referralSource"/></td>									
									<td class='formDe'>
										<html:select property="newRefSource" styleId="newRefSource">
									   	 	<html:option value="">
												<bean:message key="select.generic" />
											</html:option>
											<html:optionsCollection property="refSources" value="code" label="codeWithDescription" />
										</html:select></td>
									
								</tr>
								 <tr>
									<td class='formDeLabel' nowrap='nowrap' width="5%"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.intakeDecision"/></td>
									<td class='formDe'>
										<html:select property="newRefIntakeDecision" styleId="newRefIntakeDecision" >
									   	 	<html:option value="">
												<bean:message key="select.generic" />
											</html:option>
											<html:optionsCollection property="intakeDecisions" value="code" label="codeWithDescription" />
										</html:select></td>
									<td class='formDeLabel' nowrap='nowrap' width="5%"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.intakeDecisionDate"/></td>
									<td class='formDe'><html:text styleId="newRefIntakeDate" property="newRefIntakeDate" size="10" maxlength="10"/></td>
									<td class='formDeLabel' nowrap='nowrap' width="1%"><bean:message key="prompt.tjjdReferralDate"/></td>
									<td class='formDe'><html:text styleId="TJJDReferralDate" property="tjjdDateStr" size="10" maxlength="10" disabled="true"/> 
									<html:hidden styleId="courtdecisionDate" name="juvenileReferralForm" property="courtdecisionDate"/>
								</tr> 
								<tr>
									<td class='formDeLabel' nowrap="nowrap" colspan="1" width="10%">DA Log#</td>
									<td class='formDe'  nowrap="nowrap" colspan="1" width="10%"><bean:write name="juvenileReferralForm" property="daLogNum"/></td>
									<td class='formDeLabel' nowrap="nowrap" colspan="1" width="10%">DA Status</td>
									<td class='formDe'  nowrap="nowrap" colspan="1" width="10%"><bean:write name="juvenileReferralForm" property="logStatus"/></td>
									<td class='formDeLabel' nowrap="nowrap" colspan="1" width="10%">DA Date Out</td>
									<td class='formDe'  nowrap="nowrap" colspan="1" width="10%"><bean:write name="juvenileReferralForm" property="daDateOut" formatKey="date.format.mmddyyyy"/></td>
									<td class='formDeLabel' nowrap="nowrap" colspan="1" width="10%">CJIS</td>
									<td class='formDe'  nowrap="nowrap"colspan="1"  width="10%"><bean:write name="juvenileReferralForm" property="CJIS"/></td>						
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
					<table width="100%" cellpadding="2" cellspacing="0" border='0' id='od'>
						 <tr height='30px' class='referralDetailHead'>
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
										<td class='formDe'><html:text styleId="keyMapLocation" property="keyMapLocation" size="6" maxlength="6"/></td>
									
										<td class='formDeLabel' width="1%" ><bean:message key="prompt.investigation"/># </td> <td class='formDe'><html:text styleId="investigationNum" property="investigationNum" size="6" maxlength="10"/></td>
											
									
								</tr>
								</table>
							</td>
						</tr>
						
							<tr>
								<td colspan="6" class="formDe">
									<div class="paddedFourPix" align="center" >
									
									<html:submit property="submitAction"  styleId="addToListBtn"> <bean:message key="button.addOffense" /></html:submit>&nbsp;  
									<html:submit property="submitAction" styleId="updateOffense" disabled="true">
										<bean:message key="button.updateOffense" />
									</html:submit>&nbsp;					
									<html:submit property="submitAction"  styleId="refresh"> <bean:message key="button.refresh" /></html:submit> 	
									
						   			</div>
								</td>
							</tr>
					</table>
				</td>
			</tr>
			
				<div class='spacer'></div>
			<logic:notEmpty name="juvenileReferralForm" property="offenseList">
			<tr>
				<td colspan="6">
		  				              
					 <table width="100%" bgcolor="#cccccc" cellspacing="1" id='ol'>
							 <tr height='30px' class='referralDetailHead'>
								<td class="subhead" valign="top" width="1%" ></td>
								
								<td class="subhead" valign="top"  width="5%" nowrap="nowrap"><bean:message key="prompt.offenseCode"/></td>
								<td class="subhead" valign="top" width="10%"><bean:message key="prompt.offense"/> <bean:message key="prompt.description"/></td>
								<td class="subhead" valign="top" width="10%" nowrap="nowrap"><bean:message key="prompt.offenseDate"/></td>
								<td class="subhead" valign="top" width="10%" nowrap="nowrap"><bean:message key="prompt.keymap"/></td>
								<td class="subhead" valign="top" width="10%" nowrap="nowrap"><bean:message key="prompt.investigation"/>#</td>
								
							</tr>
							<logic:iterate id="offenseBeans" name="juvenileReferralForm" property="offenseList" indexId="index">
								<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">									
									<td valign="top"><input type="radio" name="selectedOffense" value="<%= index.intValue()%>" styleId = "offenseNumRadio" id="offenseNumIdRadio"></td>
									<td valign="top"><bean:write name="offenseBeans" property="offenseCode"/></td>
									<td valign="top"><bean:write name="offenseBeans" property="offenseDescription"/></td>
									<td valign="top"><bean:write name="offenseBeans" property="offenseDate" formatKey="date.format.mmddyyyy"/></td>
									<td valign="top"><bean:write name="offenseBeans" property="keyMapLocation"/></td>
									<td valign="top"><bean:write name="offenseBeans" property="investigationNum"/></td>
								</tr>
							</logic:iterate>
							
						</table>
				
				</td>
			</tr>	
			</logic:notEmpty>
			
			<div class='spacer'></div>
			<logic:equal value="N" name="juvenileReferralForm" property="updateAsmntTypeFlag">
			<tr disabled="true" id="assignmentDetails">
			</logic:equal>
			<logic:equal value="Y" name="juvenileReferralForm" property="updateAsmntTypeFlag">
			<tr id="assignmentDetails">
			</logic:equal>
				<td colspan="2">
					<table width="100%" cellpadding="2" cellspacing="0" border='0' id='ad'>
						 <tr height='30px' class='referralDetailHead'>
							<td colspan= '2' class='paddedFourPix' align='left' nowrap='nowrap'>Assignment Details</td>
						</tr>
						
						<tr>
							<td colspan="2">
								<table width="100%" cellpadding="2" cellspacing="1" border='0'>
								<tr>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.assignmentType"/></td>
									<td class='formDe' colspan= '4'>
											<html:select property="assignmentType" styleId="assignmentType" disabled="true">
										   	 	<html:option value="">
													<bean:message key="select.generic" />
												</html:option>
												<html:optionsCollection property="assignmentTypes" value="code" label="description" />
											</html:select>
									</td>
								</tr>
								<tr>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.supervisionCategory"/></td>									
									<td class='formDe' colspan= '4'>
										<html:select property="supervisionCat" styleId="supervisionCat" disabled="true">
											<html:option value="">
												<bean:message key="select.generic" />
											</html:option>
											<html:optionsCollection property="supervisionCategories" value="code" label="description" />
									    </html:select>	
									  </td>
								</tr>
								<tr>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.supervisionType"/></td>
									<td class='formDe' colspan= '4'>
										<html:select property="supervisionType" styleId="supervisionTypeU" disabled="true">
										   	 	<html:option value="">
													<bean:message key="select.generic" />
												</html:option>
												<html:optionsCollection property="supervisionTypes" value="code" label="description" />
											</html:select>
									</td>
								</tr>
								<tr>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.JPO"/></td>
									<td class='formDe'>
										<html:text styleId="jpo" property="jpo" size="5" maxlength="5" disabled="true" />&nbsp;&nbsp;&nbsp;&nbsp;																		
												<html:submit property="submitAction"  styleId="validateBtn1"> <bean:message key="button.validate" /></html:submit>&nbsp;
									</td>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.assignmentDate"/></td>
									<td class='formDe'><html:text styleId="assignmentDateStr" property="assignmentDateStr" size="8" maxlength="10"/></td>
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
						 <tr height='30px' class='referralDetailHead'>
							<td colspan= '4' class='paddedFourPix' align='left' nowrap='nowrap'>Referral Status</td>
						</tr>
						<logic:equal name="juvenileReferralForm" property="updateRefStatFeature" value="Y">
						<tr>
						</logic:equal>
						<logic:equal name="juvenileReferralForm" property="updateRefStatFeature" value="N">
						<tr disabled="true">
						</logic:equal>
							<td colspan="2">
								<table width="100%" bgcolor="#cccccc" cellspacing="0">
								<tr>							
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.status"/></td>
									<td class='formDe' width="10%">Active<html:radio name="juvenileReferralForm" property="updateRefStat" value="ACTIVE" styleId="updateRefStat1"/>
										Closed<html:radio name="juvenileReferralForm" property="updateRefStat" value="CLOSED" styleId="updateRefStat2"/></td>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.closeDate"/></td>
									<td class='formDe' width="10%"><html:text styleId="updtateRefCloseDt" property="updateRefCloseDt" size="8" maxlength="10"/></td>
									
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
					<table width="100%" cellpadding="2" cellspacing="0" border='0' id='pd'>
						 <tr height='30px' class='referralDetailHead'>
							<td colspan= '2' class='paddedFourPix' align='left' nowrap='nowrap'>Probation Details</td>
						</tr>
							<tr>
							<td colspan="2">
								<table width="100%" cellpadding="2" cellspacing="1" border='0'>
								<tr>							
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.probationStartDate"/></td>
									<td class='formDe'><html:text styleId="probationStartDate" property="probationStartDate" size="8" maxlength="10"/></td>
										<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap"><bean:message key="prompt.probationEndDate"/></td>
									<td class='formDe'><html:text styleId="probationEndDate" property="probationEndDate" size="8" maxlength="10" /></td>
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
					<table width="100%" bgcolor="#cccccc" cellspacing="1">
						 <tr height='30px' class='referralDetailHead'>
							<td colspan= '4' class='paddedFourPix' align='left' nowrap='nowrap'>Corespondent Details</td>
						</tr>
						<logic:notEmpty name="juvenileReferralForm" property="jotInfo">
						<tr>
							<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap">Name</td>
								<td class='formDe' valign="top" width="20%"><bean:write name="juvenileReferralForm" property="jotInfo.juvenileCoActors"/></td>
						</tr>
						
						<logic:notEmpty name="juvenileReferralForm" property="jotInfo.adultCoActors">
							<logic:iterate id="coActorsList" name="juvenileReferralForm" property="jotInfo.adultCoActors" indexId="index">
								<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap">Name</td>
									<td class='formDe' valign="top" width="20%"><bean:write name="coActorsList" property="name"/></td>
									<td class='formDeLabel' valign='top' width="1%" nowrap="nowrap">Age</td>
									<td class='formDe' valign="top"><bean:write name="coActorsList" property="age"/></td>	
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						</logic:notEmpty>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<table width="100%" cellpadding = "0" cellspacing="0">
						<tr height='30px' class='referralDetailHead'>
							<td colspan= '4' class='paddedFourPix' align='left' nowrap='nowrap'><a href="javascript:showHideMulti('intakeHistory', 'intakeDecisionHistory', 1, '/<msp:webapp/>');" border=0><img border="0" src="/<msp:webapp/>images/expand.gif" name="intakeHistory"></a>&nbsp;&nbsp; Intake Decision History</td>
						</tr>
						<tr id="intakeDecisionHistory0" class='hidden'>
							<td valign="top" align="center" colspan="4">
								<table cellpadding="4" cellspacing="1" width="100%" border='0'> 
									<tr> 
										<td valign='middle' class="formDeLabel">
											Intake History Id
											<jims2:sortResults sortId="14" beanName="juvenileReferralForm" results="intakeHistoryList" primaryPropSort="OID" primarySortType="String" defaultSort="true" defaultSortOrder="DESC"/>											
										</td>
										<td valign='middle' class="formDeLabel">
											Assignment Date
											<jims2:sortResults sortId="15" beanName="juvenileReferralForm" results="intakeHistoryList" primaryPropSort="assignmentDate" primarySortType="Date"/>									
										</td>
										<td valign='middle' class="formDeLabel">
											Assessment Type
											<jims2:sortResults sortId="16" beanName="juvenileReferralForm" results="intakeHistoryList" primaryPropSort="assignmentType" primarySortType="String"/>								
										</td> 
										<td valign='middle' class="formDeLabel">
											Intake Date
											<jims2:sortResults sortId="17" beanName="juvenileReferralForm" results="intakeHistoryList" primaryPropSort="intakeDate" primarySortType="Date"/>									
										</td> 
										<td valign='middle' class="formDeLabel">
											Intake Decision
											<jims2:sortResults sortId="18" beanName="juvenileReferralForm" results="intakeHistoryList" primaryPropSort="intakeDecisionId" primarySortType="String"/>								
										</td> 
										<td valign='middle' class="formDeLabel">
											Intake JPO
											<jims2:sortResults sortId="19" beanName="juvenileReferralForm" results="intakeHistoryList" primaryPropSort="jpoId" primarySortType="String"/>									
										</td> 
										<td valign='middle' class="formDeLabel">
											Supervision Type Code
											<jims2:sortResults sortId="20" beanName="juvenileReferralForm" results="intakeHistoryList" primaryPropSort="supervisionTypeId" primarySortType="String"/>								
										</td> 
										<td valign='middle' class="formDeLabel">
											Create User
											<jims2:sortResults sortId="21" beanName="juvenileReferralForm" results="intakeHistoryList" primaryPropSort="createUserID" primarySortType="String"/>									
										</td> 
										<td valign='middle' class="formDeLabel">
											Create Date
											<jims2:sortResults sortId="22" beanName="juvenileReferralForm" results="intakeHistoryList" primaryPropSort="createTimestamp" primarySortType="Date"/>									
										</td> 
									</tr>
									<logic:iterate id="intakeHistory" name="juvenileReferralForm" property="intakeHistoryList" indexId="index">
										<tr>
											<td valign='middle' class='formDe' valign="top" ><bean:write name="intakeHistory" property="OID"/></td>
										
											<td valign='middle' class='formDe' valign="top" ><bean:write name="intakeHistory" property="assignmentDate" formatKey="date.format.mmddyyyy"/></td>
										
											<td valign='middle' class='formDe' valign="top" ><span title="<bean:write name="intakeHistory" property="assignmentTypeDescription"/>"><bean:write name="intakeHistory" property="assignmentType"/></span></td>
										
											<td valign='middle' class='formDe' valign="top" ><bean:write name="intakeHistory" property="intakeDate" formatKey="date.format.mmddyyyy"/></td>
										
											<td valign='middle' class='formDe' valign="top" ><span title="<bean:write name="intakeHistory" property="intakeDecisionDescription"/>"><bean:write name="intakeHistory" property="intakeDecisionId"/></span></td>
										
											<td valign='middle' class='formDe' valign="top" ><span title="<bean:write name="intakeHistory" property="jpoName"/>"><bean:write name="intakeHistory" property="jpoId"/></span></td>
										
											<td valign='middle' class='formDe' valign="top" ><span title="<bean:write name="intakeHistory" property="supervisionTypeDescription"/>"><bean:write name="intakeHistory" property="supervisionTypeId"/></span></td>
											
											<td valign='middle' class='formDe' valign="top" ><span title="<bean:write name="intakeHistory" property="createUserName"/>"><bean:write name="intakeHistory" property="createUserID"/></span></td>
											
											<td valign='middle' class='formDe' valign="top" ><bean:write name="intakeHistory" property="createTimestamp" formatKey="date.format.mmddyyyy"/></td>
										</tr>
									</logic:iterate>												
								</table>
							</td>
							
						</tr>
					</table>
				</td>
			</tr>
	<div class='spacer'></div>
 	 <tr align="center">
		<td colspan="7" align="center">
			<table align="center" border="0" width="100%">
				<tr>
					<td align="center">
						<logic:notEmpty name="juvenileReferralForm" property="referralList">						
									<logic:notEqual name="juvenileReferralForm"
										property="newRefRecType" value="REFERRAL">
										<jims2:isAllowed
											requiredFeatures='<%=Features.JCW_SEALPURGE_VIEW%>'>
										</jims2:isAllowed>
										<jims2:isAllowed
											requiredFeatures='<%=Features.JCW_SEALPURGE_VIEW%>' value='false'>
											<html:submit property="submitAction" styleId="updateReferral"
											disabled="true">
											<bean:message key="button.updateReferral" />
										</html:submit>
										</jims2:isAllowed>
									</logic:notEqual>
									<logic:equal name="juvenileReferralForm"
										property="newRefRecType" value="REFERRAL">
										<html:submit property="submitAction" styleId="updateReferral"
											disabled="true">
											<bean:message key="button.updateReferral" />
										</html:submit>
									</logic:equal>
								</logic:notEmpty>						
					</td>
				</tr>
			</table></td>
	</tr>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    </table>
	<html:hidden styleId="action" name="juvenileReferralForm" property="action"/>
	<html:hidden styleId="updateOffenseFlag" name="juvenileReferralForm" property="updateOffenseFlag"/>
	<html:hidden styleId="updateAsmntTypeFlag" name="juvenileReferralForm" property="updateAsmntTypeFlag"/>
	<html:hidden styleId="earliestOffenseDate" name="juvenileReferralForm" property="earliestOffenseDate"/>
	<html:hidden styleId="loadAssmntFlag" name="juvenileReferralForm" property="loadAssmntFlag"/>
	<html:hidden styleId="updateRefStat" name="juvenileReferralForm" property="updateRefStat"/>
	<html:hidden styleId="updateRefStatFlag" name="juvenileReferralForm" property="updateRefStatFlag"/>
	<html:hidden styleId="updNewRefSource" name="juvenileReferralForm" property="updNewRefSource"/>
	<html:hidden styleId="updNewRefIntakeDecision" name="juvenileReferralForm" property="newRefIntakeDecision"/>
	<html:hidden styleId="updateOffenseMsg" name="juvenileReferralForm" property="updateOffenseMsg"/>

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
			<logic:notEmpty name="juvenileReferralForm" property="newRefNum">	 
		  		<html:submit property="submitAction"  styleId="generateReceipt" disabled="false"> <bean:message key="button.generateReferralReceipt" /></html:submit>&nbsp;
		  	</logic:notEmpty>		  
		</td>
	</tr>
	<tr>
		<td align="center">	
				<html:submit property="submitAction" onclick="spinner()" styleId="returnToSearchJuv"> <bean:message key="button.returnToSearchJuv" /></html:submit>&nbsp; 		
					
				<input type="button" id="btnReturntobriefing" name="submitAction" value="<bean:message key="button.returnToReferralBriefing"/>" 
												onclick="changeFormActionURL(this.form.name, 
												'/<msp:webapp/>displayJuvenileBriefingDetails.do?submitAction=referralLink&juvenileNum=<bean:write name='juvenileBriefingDetailsForm' property='profileDetail.juvenileNum'/>', true);">
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_MANAGEASSIGNMENT%>'>
					<input type="button" id="btnManageassignment"  name="submitAction" value="<bean:message key="button.manageAssignment"/>" 
												onclick="goNav('/<msp:webapp/>processReferralBriefing.do?submitAction=Manage Assignment')">
  		  		</jims2:isAllowed>
  		  		<jims2:isAllowed requiredFeatures='<%=Features.JCW_OVERRIDEASSIGNMENT%>'>
  		  			<input type="button" id="btnOverrideassignment" name="submitAction" value="<bean:message key="button.overrideAssignment"/>" 
												onclick="goNav('/<msp:webapp/>processReferralBriefing.do?submitAction=Override Assignment')">  
				</jims2:isAllowed>		 	 		
  				</html:submit>
				<input type="button" id="btnReturntofacilitybriefing"  name="submitAction" value="<bean:message key='button.returnToFacilityBriefing'/>" 
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
