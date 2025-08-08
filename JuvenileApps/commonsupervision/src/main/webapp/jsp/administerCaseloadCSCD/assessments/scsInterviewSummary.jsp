<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 06/25/2009 Bhavani Jangay - Created JSP -->
<!-- 08/06/2012 Richard Capestani hide duplicate Finish N Update Force Field Analysis Button -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<!--  jims2 CUSTOM TAG lIBRARIES FOR QUESTION ANSWER DISPLAY-->
<%@ taglib uri="../../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@page import="naming.CSCAssessmentConstants"%>
<%@page import="naming.PDCodeTableConstants"%>
<%@page import="naming.Features" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/assessments/scsInterviewSummary.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script>
function validateFields(theForm)
{
	return true;
}
</script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/submitSCSInterviewAssessment" target="content">
<div align="center">
<!-- BEGIN MAIN TABLE -->	
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
		<!-- BEGIN BLUE TABS TABLE -->	
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--tabs start-->
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
						</tiles:insert> 
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
			</table>
		<!-- END BLUE TABS TABLE -->  
		<!-- BEGIN BLUE BORDER TABLE -->		
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
		<!-- BEGIN SUPERVISEE INFORMATION TABLE  -->
						<tiles:insert page="../../common/superviseeHeader.jsp" flush="true"></tiles:insert>		
		<!-- END SUPERVISEE INFORMATION TABLE  -->	
                        <div class="spacer4px"></div> 
		 <!-- BEGIN GREEN TABS TABLE -->	
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<!--tabs start-->
									<tiles:insert page="../../common/caseloadCSCDSubTabs.jsp" flush="true">
										<tiles:put name="tab" value="AssessmentsTab" />
									</tiles:insert> 
									<!--tabs end-->
								</td>
							</tr>
							<tr>
								<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
		<!-- END GREEN TABS TABLE -->	
		<!-- BEGIN GREEN BORDER TABLE -->
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
								
									<!-- BEGIN HEADING -->
									<div class="header"><bean:message key="title.CSCD"/>&nbsp;-
									
										<logic:equal name="scsInterviewAssessmentForm" property="action" value="create">
											<bean:message key="prompt.new"/>
											<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|69">
										</logic:equal>
                                        <logic:equal name="scsInterviewAssessmentForm" property="action" value="update">
											<bean:message key="prompt.update"/>
											<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|70">
										</logic:equal>
                                        <logic:equal name="scsInterviewAssessmentForm" property="action" value="delete">
											<bean:message key="prompt.delete"/>
											<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|71">
										</logic:equal>
                                        <bean:message key="title.SCSInterview"/>&nbsp;<bean:message key="prompt.summary"/>
										
										</div>
										<!-- END HEADING -->
										
										<!-- BEGIN ERRORS TABLE -->
										<table width="98%" align="center">							
											<tr>
												<td align="center" class="errorAlert"><html:errors></html:errors></td>
											</tr>		
										</table>
										<!-- END ERRORS TABLE -->
										
										<!-- BEGIN instructions -->
										<table width="98%" border="0" cellpadding="0" cellspacing="0">
											
											<tr>
												<td>
													<ul>
						                            	<li>Verify that information is correct and select Finish button. If any changes are needed, select Back button.</li>
						                            	<li>To continue with Force Field Analysis click appropriate button.</li>
						                        	</ul>
												</td>
											</tr>
										</table>
										<!-- END instructions -->
										
										<!-- assessment date start -->
										<table width="98%" cellpadding="4" cellspacing="1" class="borderTableBlue">
											<tr>
												<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.assessmentDate"/></td>
												<td class="formDe">
													<bean:write name="scsInterviewAssessmentForm" property="assessmentDateStr" />
												</td>
											</tr>
										</table>
										<!-- assessment date end -->
										
										<div class="spacer4px"></div>
										
										<!-- scs Interview start -->
										<table width="98%" border="0" cellpadding="4" cellspacing="0" class="borderTableBlue">
											<tr class="detailHead">
												<td colspan="6"><bean:message key="prompt.scsInterviewQuestions"/></td>
											</tr>
											<tr>
												<td>
													<table width="100%" cellpadding="2" cellspacing="1">
														<jims2:cscquestions name="scsInterviewAssessmentForm" property="scsSummaryQuestionsList" type="summary" columns="1"/>
													</table>
												</td>
											</tr>
										</table>

										<div class="spacer4px"></div>
										<!-- scs Interview end -->

										<div class="spacer4px"></div>
										
										<!-- BEGIN BUTTON TABLE -->
										<table border="0" cellpadding=2 cellspacing=1>
											<tr>
												<td align="center">
													<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
													
													<logic:notEqual name="scsInterviewAssessmentForm" property="action" value="delete">
												 	 	 <logic:equal name="scsInterviewAssessmentForm" property="assessmentIncomplete" value="true">
												 	 	 	<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"> <bean:message key="button.saveAsDraft" /></html:submit>
												 	 	 </logic:equal>
												 	 </logic:notEqual>
													 	 
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"> <bean:message key="button.finish" /></html:submit>
													
													<logic:equal name="scsInterviewAssessmentForm" property="action" value="create">
														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_CREATE_INITIAL%>'>
															<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"> <bean:message key="button.finishNNewForceFieldAnalysis" /></html:submit>
														</jims2:isAllowed>	
													</logic:equal>
													
													<logic:equal name="scsInterviewAssessmentForm" property="action" value="update">
														<logic:equal name="scsInterviewAssessmentForm" property="forceFieldAssessmentStatus" value="<%=CSCAssessmentConstants.ASSESSMENT_NOT_EXIST%>">
															<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_CREATE_INITIAL%>'>
																<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"> <bean:message key="button.finishNNewForceFieldAnalysis" /></html:submit>
															</jims2:isAllowed>	
														</logic:equal>
														<logic:equal name="scsInterviewAssessmentForm" property="forceFieldAssessmentStatus" value="<%=CSCAssessmentConstants.ASSESSMENT_EXIST%>">
															<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_UPDATE_WITH_RESTRC%>'>
																<logic:equal name="scsInterviewAssessmentForm" property="forceFieldAssessmentIncomplete" value="true">
																	<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"> <bean:message key="button.finishNUpdateForceFieldAnalysis" /></html:submit>
																	<bean:define id="finishNUpdateForceFieldAnalysisButton"  value="x"></bean:define>
																</logic:equal>	
															</jims2:isAllowed>
															<logic:notPresent name="finishNUpdateForceFieldAnalysisButton">	
																<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_UPDATE_WITHOUT_RESTRC%>'>
																	<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"> <bean:message key="button.finishNUpdateForceFieldAnalysis" /></html:submit>
																</jims2:isAllowed>
															</logic:notPresent>
														</logic:equal>	
													</logic:equal>	
													
													<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
												</td>
											</tr>
										</table>
										<!-- END BUTTON TABLE -->
									</td>
								</tr>
							</table>
							<!-- END GREEN BORDER TABLE -->	
							<div class="spacer4px"></div>
						</td>
					</tr>
				</table>
				<!-- END BLUE BORDER TABLE -->
				<br>
			</td>
		</tr>
	</table>
	<br>
<!-- END  MAIN TABLE -->
</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
