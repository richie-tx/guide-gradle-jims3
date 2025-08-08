<!DOCTYPE HTML>
<%-- Used in closing of a casefile --%>
<%--MODIFICATIONS --%>
<%-- 11/29/2005		Justin Jose	Created JSP --%>
<%-- 05/02/2012		CShimek		#73374 Add code to disable "Closing Packet - Spanish" button until new version for TJJD is available. --%>
<%-- 06/05/2012		DWilliamson #73622 Change name of screen containing document buttons --%>
<%-- 07/13/2012 	CShimek     #73565 added age > 20 check (juvUnder21) to hide Update type buttons --%>
<%-- 02/07/2013     CShimek		#74972 revised single closing letter to English and Spanish versions --%>
<%-- 09/01/2015     RCapestani	#29685 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Casefile Closing tab UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading"/> - closingHome.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script> 
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script>
	$(document).ready(function(){
		$("#generateExitPlan").click(function(e){
			e.preventDefault();
			spinner();
			$.ajax({
		        url: '/JuvenileCasework/handleCasefileClosingActivities.do?submitAction=Generate Exit Plan',
		        type: 'post',
		        data: $('form#casefileClosingActivities').serialize(),
		        success: function(data, textStatus , xhr) {
		         	if (200 == xhr.status){
		         		$(".overlay").remove();
		         		setTimeout(function(){alert("Documents can be located in the Documents tab")}, 1000);
		         	}
		        }
		    });
			
		})
		
		$("#createUpdateExitPlan").click(function(e){
			e.preventDefault();
			spinner();
			$.ajax({
		        url: '/JuvenileCasework/handleCasefileClosingActivities.do?submitAction=Create/Update Exit Plan',
		        type: 'post',
		        data: $('form#casefileClosingActivities').serialize(),
		        success: function(data, textStatus , xhr) {
		         	if (200 == xhr.status){
		         		$(".overlay").remove();
		         		setTimeout(function(){alert("Documents can be located in the Documents tab")}, 1000);
		         	}
		        }
		    });
			
		})
		
		$("#closingPacketEnglish").click(function(){
			spinner();
			$.ajax({
		        url: '/JuvenileCasework/handleCasefileClosingActivities.do?submitAction=Closing Packet - English&submitType=ajax',
		        type: 'post',
		        data: $('form#casefileClosingActivities').serialize(),
		        success: function(data, textStatus , xhr) {
		         	if (200 == xhr.status){
		         		$(".overlay").remove();
		         	}
		        }
		    });
			
		})
		
		$("#closingPacketSpanish").click(function(){
			spinner();
			$.ajax({
		        url: '/JuvenileCasework/handleCasefileClosingActivities.do?submitAction=Closing Packet - Spanish&submitType=ajax',
		        type: 'post',
		        data: $('form#casefileClosingActivities').serialize(),
		        success: function(data, textStatus , xhr) {
		         	if (200 == xhr.status){
		         		$(".overlay").remove();
		         	}
		        }
		    });
			
		})
		
		$("#closingLetterEnglish").click(function(){
			spinner();
			$.ajax({
		        url: '/JuvenileCasework/handleCasefileClosingActivities.do?submitAction=Closing Letter - English&submitType=ajax',
		        type: 'post',
		        data: $('form#casefileClosingActivities').serialize(),
		        success: function(data, textStatus , xhr) {
		         	if (200 == xhr.status){
		         		$(".overlay").remove();
		         	}
		        }
		    });
			
		})
		
		$("#closingLetterSpanish").click(function(){
			spinner();
			$.ajax({
		        url: '/JuvenileCasework/handleCasefileClosingActivities.do?submitAction=Closing Letter - Spanish&submitType=ajax',
		        type: 'post',
		        data: $('form#casefileClosingActivities').serialize(),
		        success: function(data, textStatus , xhr) {
		         	if (200 == xhr.status){
		         		$(".overlay").remove();
		         	}
		        }
		    });
			
		})
		
		$("#clientSatisfactionSurvey").click(function(){
			spinner();
			$.ajax({
		        url: '/JuvenileCasework/handleCasefileClosingActivities.do?submitAction=Client Satisfaction Survey&submitType=ajax',
		        type: 'post',
		        data: $('form#casefileClosingActivities').serialize(),
		        success: function(data, textStatus , xhr) {
		         	if (200 == xhr.status){
		         		$(".overlay").remove();
		         	}
		        }
		    });
			
		})
		
		$("#serviceProviderSatisfactionSurvey").click(function(){
			spinner();
			$.ajax({
		        url: '/JuvenileCasework/handleCasefileClosingActivities.do?submitAction=Service Provider Satisfaction Survey&submitType=ajax',
		        type: 'post',
		        data: $('form#casefileClosingActivities').serialize(),
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
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form styleId="casefileClosingActivities" action="handleCasefileClosingActivities.do" target="content">

<logic:notEqual name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
	<logic:notEqual name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_SUBMITTED%>">
		<logic:notEqual name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED%>">
			<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|67">
		</logic:notEqual> 
	</logic:notEqual>   
</logic:notEqual>   
<%--logic:notEqual name="casefileClosingForm" property="secondaryAction" value="<%=naming.UIConstants.APPROVE%>">
    <jims:if name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED%>" op="equal">
		<jims:or name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSED%>" op="equal"/>
				<jims:then>
				    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|"> 
				</jims:then>
	</jims:if>
</logic:notEqual--%>
<logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
	<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|162">
</logic:equal>
<%--logic:equal name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_SUBMITTED%>">
	<logic:notEqual name="casefileClosingForm" property="caseLoadManager" value="true">
       <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|163">
	</logic:notEqual>			
</logic:equal--%>
<jims:if name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED%>" op="equal">
<jims:or name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_SUBMITTED%>" op="equal"/>
	<jims:then>
		<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|62">		
	</jims:then>
</jims:if>
<logic:notEqual name="casefileClosingForm" property="secondaryAction" value="<%=naming.UIConstants.APPROVE%>"><jims:if name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED%>" op="equal">
	<jims:or name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSED%>" op="equal"/>
		<jims:then>
			<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|164">
		</jims:then>
	</jims:if>
</logic:notEqual>

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.close"/> <bean:message key="title.casefile"/> -  
			<logic:notEqual name="casefileClosingForm" property="secondaryAction" value="<%=naming.UIConstants.APPROVE%>">
				<jims:if name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED%>" op="equal">
				<jims:or name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSED%>" op="equal"/>
				<jims:then>Generate</jims:then>
				</jims:if>
			</logic:notEqual>
				
			<bean:message key="title.casefile"/> <bean:message key="title.closing"/>
			
			<logic:notEqual name="casefileClosingForm" property="secondaryAction" value="<%=naming.UIConstants.APPROVE%>">
				<jims:if name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED%>" op="equal">
				<jims:or name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSED%>" op="equal"/>
				<jims:then>Documents</jims:then>
				</jims:if>
			</logic:notEqual>
			<logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
				Confirmation
			</logic:equal>
			<logic:equal name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_SUBMITTED%>">
				<logic:notEqual name="casefileClosingForm" property="caseLoadManager" value="true"> Edit
				</logic:notEqual>			
			</logic:equal>
	<!-- DAW ER #73622 -->			
		<%--	<jims:if name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED%>" op="equal">
			<jims:or name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_SUBMITTED%>" op="equal"/>
				<jims:then>	Details</jims:then>
			</jims:if> --%>
		<logic:equal name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_SUBMITTED%>">
			Details
		</logic:equal>	
		</td>	     
	</tr>
</table>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN MESSAGE TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="confirm">
			<logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM_UPDATE%>">
				Supervision Closing Information has been saved.
			</logic:equal>
			<logic:equal name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_SUBMITTED%>">
				<logic:notEqual name="casefileClosingForm" property="caseLoadManager" value="true">
					Casefile has been submitted for approval and cannot be modified.
				</logic:notEqual>
			</logic:equal>
			<logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
				<logic:equal name="casefileClosingForm" property="secondaryAction" value="<%=naming.UIConstants.APPROVE%>">
					Casefile closing has been successfully approved and notification has been sent to the JPO.
				</logic:equal>
			</logic:equal>
			<logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
				<logic:equal name="casefileClosingForm" property="secondaryAction" value="<%=naming.UIConstants.CLOSE%>">
					Casefile has been successfully closed and notification has been sent to the JPO.
				</logic:equal>
			</logic:equal>
			<logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.APPROVE%>">
				<logic:equal name="casefileClosingForm" property="secondaryAction" value="<%=naming.UIConstants.CONFIRM%>">
					<logic:equal name="casefileClosingForm" property="caseLoadManager" value="true">
						Casefile has been submitted for approval.
					</logic:equal>
				</logic:equal>
			</logic:equal>
		</td>
	</tr>
</table>
<%-- END MESSAGE TABLE --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Click tabs to navigate.</li>							
				<logic:equal name="casefileClosingForm" property="caseLoadManager" value="true">
					<logic:equal name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_SUBMITTED%>">
						<li>Click Reject button to reject casefile closing.</li>
						<li>Click Approve button to approve casefile closing.</li>		
					</logic:equal>
				</logic:equal>					
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

<%-- BEGIN MAIN BODY TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" cellpadding="0" cellspacing="0">
 	<tr>
   	<td valign="top">
      <%-- BEGIN CASEFILE TABS TABLE --%>
			<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
				<tiles:put name="tabid" value="closing"/>
				<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
			</tiles:insert>				
      <%-- END CASEFILE TABS TABLE --%>	

      <%-- BEGIN CASEFILE TABLE --%>
			<table align="center" width='100%' cellpadding="2" cellspacing="0" class='borderTableBlue'>
		    	<tr>
					<td valign="top" align="center">
						<logic:notEmpty name="casefileClosingForm" property="rejectReason">
	  						<table align='center' width='98%' cellpadding='2' cellspacing='0' class='borderTableBlue'>
	  							<tr>
	  								<td valign="top" class=detailHead><bean:message key="prompt.reject"/> - <bean:message key="prompt.casefile"/> <bean:message key="prompt.closing"/></td>
	  							</tr>
	  							<tr>
	  								<td>
	  									<table width='100%' cellpadding=2 cellspacing=1>
	  										<tr>
	  											<td class='formDeLabel' nowrap><bean:message key="prompt.rejection"/> <bean:message key="prompt.reason"/></td>
	  										</tr>
	  										<tr>
	  											<td class='formDe'><bean:write name="casefileClosingForm" property="rejectReason"/> </td>
	  										</tr>
	  									</table>
	  								</td>
	  							</tr>
	  						</table>
	  						<br>
						</logic:notEmpty>

						<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class=detailHead><bean:message key="prompt.casefile"/> <bean:message key="prompt.closing"/></td>
							</tr>
							<tr>
								<td valign="top" align="center">
									<table width='100%' cellpadding=4 cellspacing="1">
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.casefile"/> <bean:message key="prompt.end"/> <bean:message key="prompt.date"/></td>
											<td class="formDe" colspan="3"><bean:write name="casefileClosingForm" property="supervisionEndDate"/></td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.supervisionOutcome"/></td>
											<td class="formDe" colspan="3"><bean:write name="casefileClosingForm" property="supervisionOutcome"/></td>
										</tr>
										<logic:equal name="casefileClosingForm" property="supervisionOutcomeId" value="D">
											<tr>
												<td class="formDeLabel" width="1%" nowrap>How did the youth die?</td>
												<td class="formDe" colspan=3><bean:write name="casefileClosingForm" property="youthDeathReasonDesc"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap>How was death verified?</td>
												<td class="formDe" colspan=3><bean:write name="casefileClosingForm" property="youthDeathVerificationDesc"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap>Date of Death?</td>
												<td class="formDe" colspan=3><bean:write name="casefileClosingForm" property="deathDate"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap>Age at Death?</td>
												<td class="formDe" colspan=3><bean:write name="casefileClosingForm" property="deathAge"/></td>
											</tr>
										</logic:equal>
										<jims:if name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM%>" op="equal">
                                        	<jims:or name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ%>" op="equal" />
                                          		<jims:or name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES%>" op="equal" />
                                           		<jims:then>									
													<tr>
														<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.supervisionOutcome"/> <bean:message key="prompt.description"/></td>
														<td class="formDe" colspan="3"><bean:write name="casefileClosingForm" property="supervsionOutcomeDescription"/></td>
													</tr>
												</jims:then>
										</jims:if>	
										
										<jims:if name="juvenileCasefileForm"
													property="supervisionCategoryId"
													value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_PRE_ADJ%>"
													op="equal">
													<jims:or name="juvenileCasefileForm"
														property="supervisionCategoryId"
														value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_PRE_PETITION%>"
														op="equal" />
													<jims:then>
														<logic:notEqual name="casefileClosingForm"
															property="supervsionOutcomeDescription" value="">
															<tr>
																<td class="formDeLabel" width="1%" nowrap><bean:message
																		key="prompt.supervisionOutcome" /> <bean:message
																		key="prompt.description" />
																</td>
																<td class="formDe" colspan=3><bean:write
																		name="casefileClosingForm"
																		property="supervsionOutcomeDescription" />
																</td>
															</tr>
														</logic:notEqual>
													</jims:then>
												</jims:if>
										
											
										<tr>
											<td class="formDeLabel" nowrap><bean:message key="prompt.controlling"/> <bean:message key="prompt.referralNumber"/>
											<td class="formDe">
												<bean:write name="juvenileCasefileForm" property="controllingReferralNumber"/> 
											</td>
										</tr>										
										<tr>
											<td class="formDeLabel" colspan="4"><bean:message key="prompt.closing"/> <bean:message key="prompt.evaluation"/></td>
										</tr>
										<tr>
											<td class="formDe" colspan="4"><bean:write name="casefileClosingForm" property="closingEvalution"/>&nbsp;</td>
										</tr>
										<logic:notEmpty name="casefileClosingForm" property="closingComments">
											<tr>
												<td class="formDeLabel" colspan="4"><bean:message key="prompt.closing"/> <bean:message key="prompt.comments"/></td>
											</tr>
											<tr>
												<logic:notEmpty name="casefileClosingForm" property="closingComments">
													<td class="formDe" colspan="4"><bean:write name="casefileClosingForm" property="closingComments"/>&nbsp;</td>
												</logic:notEmpty>												
											</tr> <!-- US 175534  -->    <%-- previous US - Commented for US 173290 --%>
										</logic:notEmpty>
									</table>

									<logic:equal name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_PENDING%>">
  										<span align='center'>
	  										<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_CLOSING_U%>'>
	  											<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
  													<html:submit onclick="spinner()" property="submitAction"><bean:message key="button.updateClosing" /></html:submit>
  												</logic:equal>	 
  											</jims2:isAllowed>
 										</span>
									</logic:equal>
									
									<logic:equal name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_SUBMITTED%>">
  										<span align='center'>
	  										<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_CLOSINGSUB_U%>'>
	  											<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
  													<html:submit onclick="spinner()" property="submitAction"><bean:message key="button.updateClosing" /></html:submit>
  												</logic:equal>	 
  											</jims2:isAllowed>
 										</span>
									</logic:equal>
								</td>
							</tr>
						</table>
						
			<%-- 			<logic:equal name="casefileClosingForm" property="caseLoadManager" value="true">
							<jims:if name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED%>" op="equal">
							<jims:or name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_SUBMITTED%>" op="equal"/>
								<jims:then>	
	        						<br>
    		    						<table align="center" width='98%' cellpadding="1" cellspacing="1" class="borderTableBlue">
        									<tr>
        										<td class='detailHead' colspan='5'>Referral Under Supervision</td>
        									</tr>

											<logic:empty name="casefileClosingForm" property="referrals">
												<td> No referrals available for this casefile.</td>
											</logic:empty>
												
											<logic:notEmpty name="casefileClosingForm" property="referrals">
												<tr bgcolor='#cccccc'> 
													<td class="subHead" align="left"><bean:message key="prompt.referral" /> #</td> 
													<td class="subHead" align="left"><bean:message key="prompt.court" /> <bean:message key="prompt.date" /></td> 
													<td class="subHead" align="left"><bean:message key="prompt.intake"/> <bean:message key="prompt.decision"/></td>		             
													<td class="subHead" align="left"><bean:message key="prompt.court"/> <bean:message key="prompt.id"/></td> 
													<td class="subHead" align="left"><bean:message key="prompt.referral" /> <bean:message key="prompt.date" /></td> 
												</tr> 

    					            			<% String casefileUrl = naming.PDJuvenileCaseConstants.CASEFILEID_PARAM + "=" + request.getParameter(naming.PDJuvenileCaseConstants.CASEFILEID_PARAM); %> 
        										<logic:iterate id="refs" name="casefileClosingForm" property="referralList" indexId="index"> 		 
        											<bean:define id="juvenileNum" name="juvenileCasefileForm" property="juvenileNum" type="java.lang.String"/>
        											<bean:define id="referralNum" name="refs" property="referralNumber" type="java.lang.String"/>
      										  		<%  StringBuffer url = new StringBuffer();
        												url.append(naming.PDJuvenileCaseConstants.CASEFILEID_PARAM + "=");
        												url.append(request.getParameter(naming.PDJuvenileCaseConstants.CASEFILEID_PARAM));
        												url.append("&" + naming.PDJuvenileCaseConstants.JUVENILENUM_PARAM + "=" + juvenileNum);
        												url.append("&" + naming.PDJuvenileCaseConstants.REFERRALNUM_PARAM + "=" + referralNum);
        												String queryUrl = url.toString();							
        											%>
        											<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
        				    							<td align="left"><a href="javascript:newCustomWindow('/<msp:webapp/>displayJuvenileCasefileReferralDetails.do?<%out.print(queryUrl);%>&actionType=caseloadMgrView', 'propertyLoss',450,450)">
        					    							<bean:write name="refs" property="referralNumber"/></a>
        												</td>
	        											<td align="left"><bean:write name="refs" property="courtDate" format="MM/dd/yyyy"/></td>
	        											<td align="left"><bean:write name="refs" property="intakeDecision"/></td>
	        											<td align="left"><bean:write name="refs" property="courtId"/></td>
	        											<td align="left"><bean:write name="refs" property="referralDate" format="MM/dd/yyyy"/></td>														
	        										</tr>
	        									</logic:iterate>
    	    							</logic:notEmpty>
        							</table>

        							<br>
        							<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
        								<tr>
        									<td class=detailHead colspan=5>Interview Notes</td>
        								</tr>
        								<logic:empty name="casefileClosingForm" property="interviewList">
        							  		<td> No interviews available for this casefile.</td>
        								</logic:empty>
        								<logic:notEmpty name="casefileClosingForm" property="interviewList">
        							   		<tr bgcolor='#cccccc'> 
    					              			<td class="subHead" align="left"><bean:message key="prompt.interview" /> <bean:message key="prompt.dateTime" /></td> 
    					              			<td class="subHead" align="left"><bean:message key="prompt.type"/></td>		             					             
    					               			<td class="subHead" align="left"><bean:message key="prompt.locationUnit" /></td> 
    					               			<td class="subHead" align="left"><bean:message key="prompt.status" /></td>					              
    					            		</tr> 
    					            		<% String casefileUrl = naming.PDJuvenileCaseConstants.CASEFILEID_PARAM + "=" + request.getParameter(naming.PDJuvenileCaseConstants.CASEFILEID_PARAM); %> 
        									<logic:iterate id="interview" name="casefileClosingForm" property="interviewList" indexId="index"> 										
        										<tr class="<% out.print( (index.intValue()  % 2 == 1) ? "normalRow" : "alternateRow"); %>" height="100%">
        											<td align="left"><a href="javascript:newCustomWindow('/<msp:webapp/>handleCasefileClosingActivities.do?submitAction=Interview&selectedValue=<bean:write name="interview" property="interviewId"/>', 'propertyLoss',450,450)"><bean:write name="interview" property="interviewDate" formatKey="date.format.mmddyyyy"/></a>
        												<bean:write name="interview" property="interviewDate" formatKey="time.format.hhmma"/></td>
        											<td align="left"><bean:write name="interview" property="interviewType"/></td>																								
        											<td align="left"><bean:write name="interview" property="juvLocUnitDescription"/></td>											
        											<td align="left"><bean:write name="interview" property="interviewStatusDescription"/></td>											
        										</tr>
        									</logic:iterate>
        								</logic:notEmpty>
        							</table>
    							</jims:then>
    						</jims:if>
    					</logic:equal> --%>
    						
					<%-- BEGIN BUTTON TABLE --%>
   						<div class='spacer'></div>
   						<table width="100%">
							<logic:equal name="casefileClosingForm" property="closingInfoFound" value="true">	
								<logic:notEqual name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED%>">
									<logic:notEqual name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSED%>">							
										<tr>
											<td align="center">    								
												<logic:equal name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_PENDING%>">																	
													<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
														<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_CLOSING%>'> 
															<logic:equal name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM%>">										 											
																<html:submit styleId="generateExitPlan" onclick="spinner()" property="submitAction"><bean:message key="button.generateExitPlan" /></html:submit>																					
															</logic:equal>
															<logic:equal name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ%>">										 											
																<html:submit styleId="generateExitPlan" onclick="spinner()" property="submitAction"><bean:message key="button.generateExitPlan" /></html:submit>																					
															</logic:equal>
															<logic:equal name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES%>">																					
																<html:submit styleId="createUpdateExitPlan" onclick="spinner()" property="submitAction"><bean:message key="button.createUpdateExitPlan" /></html:submit>																							
															</logic:equal>
														</jims2:isAllowed>
													</logic:equal>													
													<logic:equal name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_PRE_ADJ%>" >
														<jims2:isAllowed requiredFeatures='<%=Features.JCW_CFV_CLOSING%>'>									 	
															<html:submit onclick="spinner()" property="submitAction"><bean:message key="button.commonApp" /></html:submit>										
														</jims2:isAllowed>
													</logic:equal>
													<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
														<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_CLOSING_REQRV%>'>										 
															<html:submit styleId="sendApprovalRequest" onclick="spinner()" property="submitAction"><bean:message key="button.sendApprovalRequest" /></html:submit>											
														</jims2:isAllowed>
													</logic:equal>
												</logic:equal>    							
												<logic:equal name="casefileClosingForm" property="caseLoadManager" value="true">
													<logic:equal name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_SUBMITTED%>">
														<logic:notEqual name="casefileClosingForm" property="action" value="confirm">
															<logic:equal name="juvenileCasefileForm" property="juvUnder21" value="true">
																<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_CLOSING_REV%>'> 
																	<html:submit onclick="spinner()" property="submitAction"><bean:message key="button.reject" /></html:submit>
																	<html:submit property="submitAction" onclick="return confirm('You are about to approve this casefile. This action cannot be undone. Do you wish to continue')"><bean:message key="button.approve" /></html:submit>
																</jims2:isAllowed>
															</logic:equal>	
															<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
														</logic:notEqual>
													</logic:equal>
												</logic:equal>
												<logic:equal name="casefileClosingForm" property="caseLoadManager" value="false">
													<logic:equal name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_SUBMITTED%>">
														<logic:notEqual name="casefileClosingForm" property="action" value="confirm">
															<html:submit onclick="spinner()" property="submitAction"><bean:message key="button.returnToCasefile" /></html:submit>
														</logic:notEqual>
													</logic:equal>
												</logic:equal>
		    								</td>
    									</tr>
   									</logic:notEqual>
   								</logic:notEqual>
    							<logic:notEqual name="casefileClosingForm" property="secondaryAction" value="<%=naming.UIConstants.APPROVE%>">
    								<jims:if name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSING_APPROVED%>" op="equal">
    								<jims:or name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_CLOSED%>" op="equal"/>
    									<jims:then>								
    										<tr>
    											<td align="center">
    										  		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_CLOSING_GENRP%>'>
    													<html:submit styleId="closingPacketEnglish" onclick="spinner()" property="submitAction"><bean:message key="button.closingPacketEnglish"/></html:submit>
    													<html:submit styleId="closingPacketSpanish" onclick="spinner()" property="submitAction"><bean:message key="button.closingPacketSpanish"/></html:submit>
    													<logic:equal name="casefileClosingForm" property="caseLoadManager" value="false">
    														<html:submit styleId="clientSatisfactionSurvey" onclick="spinner()" property="submitAction"><bean:message key="button.clientSatisfactionSurvey"/></html:submit>
    													</logic:equal>												
    												</jims2:isAllowed>
								                </td>
										    </tr>
										    <jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_CLOSING_GENRP%>'>
											  	<tr>
													<td align="center">
														<html:submit styleId="closingLetterEnglish" onclick="spinner()" property="submitAction"><bean:message key="button.closingLetterEnglish" /></html:submit>
														<html:submit styleId="closingLetterSpanish" onclick="spinner()" property="submitAction"><bean:message key="button.closingLetterSpanish" /></html:submit>
													</td>
												</tr>	
											</jims2:isAllowed>	
											<tr>
												<td align="center">	
													<logic:notEmpty name="casefileClosingForm" property="programReferrals">
														<html:submit styleId="serviceProviderSatisfactionSurvey" onclick="spinner()" property="submitAction"><bean:message key="button.serviceProviderSatisfactionSurvey" /></html:submit>
													</logic:notEmpty>
													<logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
															<html:submit onclick="spinner()" property="submitAction"><bean:message key="button.returnToNotifications"></bean:message></html:submit>							
														</logic:equal>
													
													<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
												</td>
											</tr>
										</jims:then>
					    			</jims:if>
					    		</logic:notEqual>
						    	<logic:equal name="casefileClosingForm" property="caseLoadManager" value="true">
							    	<logic:equal name="casefileClosingForm" property="action" value="<%=naming.UIConstants.CONFIRM%>">
										<logic:equal name="casefileClosingForm" property="secondaryAction" value="<%=naming.UIConstants.APPROVE%>">
											<tr>
												<td align="center">
							      					<html:submit onclick="spinner()" property="submitAction"><bean:message key="button.returnToNotifications" /></html:submit>
							    				</td>
							  				</tr>    
										</logic:equal>
									</logic:equal>
								</logic:equal>	
				    		</logic:equal>
						</table>
				<%-- END BUTTON TABLE --%>
						<div class='spacer'></div>
					</td>
				</tr>
			</table>
      <%-- END CASEFILE TABLE --%>
		</td>
	</tr>
</table>
<%-- END MAIN BODY  TABLE --%>
</html:form>
<div class='spacer'></div>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>