<!DOCTYPE HTML>
<%-- Used in closing of a casefile --%>
<%--MODIFICATIONS --%>
<%-- 11/29/2005		Justin Jose	Created JSP --%>
<%-- 06/21/2012		C. Shimek	73735 add outcome description --%>
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
<title><bean:message key="title.heading"/> - closingUpdateSummary.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<%--HELP JAVASCRIPT FILE --%> 
<%--<SCRIPT SRC="../js/help.js" /> --%>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script>

$(document).ready(function(){
	$("#saveAndContinue").click(function(){
		spinner();
	})
})
	
</script>
</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="submitCasefileClosingData.do" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|54">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header">
			<bean:message key="title.juvenileCasework"/> - <bean:message key="title.close"/> <bean:message key="title.casefile"/> - <bean:message key="title.casefile"/> <bean:message key="title.closing"/>
			<bean:message key="title.summary"/>
		</td>
	</tr>
</table>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<%-- END HEADING TABLE --%>
<div class=spacer></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%">
 	<tr>
	   	<td>
			<ul>
       			<li>Verify information, then click "Save and Continue" button to save.</li>
       			<li>If changes are needed, click Back button.</li>
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
<div class=spacer></div>
<%-- BEGIN MAIN BODY TABLE --%>
<table align="center" width="98%" cellpadding="0" cellspacing="0" >
 	<tr>
	   	<td valign=top>
			<logic:equal name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_ACTIVE%>">
					<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
						<tiles:put name="tabid" value="casefiledetailstab"/>
						<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
					</tiles:insert>		
				</logic:equal>	

				<logic:notEqual name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_ACTIVE%>">
					<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
						<tiles:put name="tabid" value="closing"/>
						<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
					</tiles:insert>		
				</logic:notEqual>				

						
      <%-- BEGIN CASEFILE TABLE --%>
			<table align="center" width='100%' cellpadding="2" cellspacing="0" class="borderTableBlue">
		    <tr>
					<td valign=top align=center>
           				<div class=spacer></div>
						<table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class=detailHead><bean:message key="prompt.casefile"/> <bean:message key="prompt.closing"/></td>
							</tr>
							<tr>
								<td valign=top align=center>
									<table width='100%' cellpadding=4 cellspacing="1">
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.casefileEndDate"/></td>
											<td class="formDe" colspan=3><bean:write name="casefileClosingForm" property="supervisionEndDate"/></td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.supervisionOutcome"/></td>
											<td class="formDe" colspan=3><bean:write name="casefileClosingForm" property="supervisionOutcome"/></td>
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
										
										
										<logic:notEqual name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM%>">                                        
											<logic:notEqual name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES%>">
												<logic:notEqual name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ%>">
													<logic:notEqual name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_INDIRECT%>">
													<tr>
														<td class="formDeLabel" nowrap><bean:message key="prompt.controlling"/> <bean:message key="prompt.referralNumber"/>
														<td class="formDe" colspan=3><bean:write name="casefileClosingForm" property="controllingReferral"/></td>
													</tr>
													</logic:notEqual>
												</logic:notEqual>
											</logic:notEqual>
										</logic:notEqual>

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

												<jims:if name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM%>" op="equal">
                                        	<jims:or name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ%>" op="equal" />
                                          		<jims:or name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES%>" op="equal" />
	                                          		<jims:or name="juvenileCasefileForm" property="supervisionCategoryId" value="<%=naming.UIConstants.CASEFILE_SUPERVISION_CAT_INDIRECT%>" op="equal" />	                                          	
	                                           		<jims:then>
														<tr>
															<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.supervisionOutcome"/>  <bean:message key="prompt.description"/></td>
															<td class="formDe" colspan=3><bean:write name="casefileClosingForm" property="supervsionOutcomeDescription"/></td>
														</tr>
														<tr>
															<td class="formDeLabel" nowrap><bean:message key="prompt.controlling"/> <bean:message key="prompt.referralNumber"/> </td>
															<td class="formDe" colspan=3><bean:write name="juvenileCasefileForm" property="controllingReferralNumber"/></td>
														</tr>
											   </jims:then>
										</jims:if>  
										
										<tr>
											<td class="formDeLabel" colspan="4"><bean:message key="prompt.closingEvaluation"/></td>
										</tr>
										<tr>
											<td class="formDe" colspan="4"><bean:write name="casefileClosingForm" property="closingEvalution"/>&nbsp;</td>
										</tr>
										<%-- <tr>
											<td class="formDeLabel" colspan="4"><bean:message key="prompt.closing"/> <bean:message key="prompt.comments"/></td>
										</tr>
										<tr>
											<td class="formDe" colspan="4"><bean:write name="casefileClosingForm" property="closingComments"/>&nbsp;</td>
										</tr> --%>
									</table>
								</td>
							</tr>
						</table>

						<%-- BEGIN BUTTON TABLE --%>
						<div class=spacer></div>
						<table width="100%">
						  	<tr>
						    	<td align="center">
									<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
					    			<html:submit styleId="saveAndContinue" property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.saveAndContinue"></bean:message></html:submit>
					    			<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
						    	</td>
						  	</tr>
						</table>
						<%-- END BUTTON TABLE --%>
						<div class=spacer></div>
					</td>
				</tr>
			</table>
      <%-- END CASEFILE TABLE --%>
		</td>
	</tr>
</table>
<%-- END MAIN BODY TABLE --%>

</html:form>

<div class=spacer></div>


<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

