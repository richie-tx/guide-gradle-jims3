<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/10/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 02/10/2009 Debbie Williamson - Standardized field displays - ER #56726  -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ taglib uri="../../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@page import="naming.CSCAssessmentConstants"%>

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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/assessments/wisconsinSummary.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/submitWisconsinAssessment" target="content">
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
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
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
		<!-- BEGIN SUPERVISEE INFORMATION -->
						<tiles:insert page="../../common/superviseeHeader.jsp" flush="true"></tiles:insert>		
		<!-- END SUPERVISEE INFORMATION -->	
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
										<logic:equal name="wisconsinAssessmentForm" property="action" value="create">
											<logic:equal name="wisconsinAssessmentForm" property="wisconsinAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												<bean:message key="prompt.new"/>&nbsp;<bean:message key="prompt.wisconsin"/>&nbsp;<bean:message key="prompt.assessment"/>
											</logic:equal>
											<logic:notEqual name="wisconsinAssessmentForm" property="wisconsinAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												<bean:message key="prompt.new"/>&nbsp;<bean:message key="prompt.wisconsin"/>&nbsp;<bean:message key="prompt.reassessment"/>
											</logic:notEqual>
										</logic:equal>
										
										<logic:equal name="wisconsinAssessmentForm" property="action" value="update">
											<logic:equal name="wisconsinAssessmentForm" property="wisconsinAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												<bean:message key="title.update"/>&nbsp;<bean:message key="prompt.wisconsin"/>&nbsp;<bean:message key="prompt.assessment"/>
											</logic:equal>
											<logic:notEqual name="wisconsinAssessmentForm" property="wisconsinAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												<bean:message key="title.update"/>&nbsp;<bean:message key="prompt.wisconsin"/>&nbsp;<bean:message key="prompt.reassessment"/>												
											</logic:notEqual>
										</logic:equal>
										
										<logic:equal name="wisconsinAssessmentForm" property="action" value="delete">
											<logic:equal name="wisconsinAssessmentForm" property="wisconsinAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												<bean:message key="prompt.delete"/>&nbsp;<bean:message key="prompt.wisconsin"/>&nbsp;<bean:message key="prompt.assessment"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|15">
											</logic:equal>
											<logic:notEqual name="wisconsinAssessmentForm" property="wisconsinAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												<bean:message key="prompt.delete"/>&nbsp;<bean:message key="prompt.wisconsin"/>&nbsp;<bean:message key="prompt.reassessment"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|20">
											</logic:notEqual>
										</logic:equal>
										
										<logic:equal name="wisconsinAssessmentForm" property="secondaryAction" value="confirm">
											<bean:message key="title.confirmation"/>  
											<logic:equal name="wisconsinAssessmentForm" property="action" value="create">
											    <logic:equal name="wisconsinAssessmentForm" property="wisconsinAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">											    
												    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|10">
												</logic:equal>
												<logic:notEqual name="wisconsinAssessmentForm" property="wisconsinAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">												    
												    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|53">												        
												</logic:notEqual>    																								    
											</logic:equal>
											<logic:equal name="wisconsinAssessmentForm" property="action" value="update">
											    <logic:equal name="wisconsinAssessmentForm" property="wisconsinAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|14">
												</logic:equal>
												<logic:notEqual name="wisconsinAssessmentForm" property="wisconsinAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">												    
												    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|19">												        
												</logic:notEqual>    
											</logic:equal>
										</logic:equal>
										
										<logic:notEqual name="wisconsinAssessmentForm" property="secondaryAction" value="confirm">
											<bean:message key="title.summary"/>
											<logic:equal name="wisconsinAssessmentForm" property="action" value="create">
											    <logic:equal name="wisconsinAssessmentForm" property="wisconsinAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|9">
												</logic:equal>
												<logic:notEqual name="wisconsinAssessmentForm" property="wisconsinAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">												    
												    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|54">												        
												</logic:notEqual>
											</logic:equal> 
											<logic:equal name="wisconsinAssessmentForm" property="action" value="update">
											    <logic:equal name="wisconsinAssessmentForm" property="wisconsinAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|13">
												</logic:equal>
												<logic:notEqual name="wisconsinAssessmentForm" property="wisconsinAssessmentType" value="<%= CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT %>">
												    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|18">
												</logic:notEqual>    
											</logic:equal> 
										</logic:notEqual>                                                 
                                    </div>
									<!-- END HEADING -->
									
									<!-- BEGIN ERROR TABLE -->
									<table width="98%" align="center">							
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>		
									</table>
									<!-- END ERROR TABLE -->
									
									<!-- BEGIN instructions -->
									<table width="98%" border="0" cellpadding="0" cellspacing="0">
										<logic:present name="confirmMessage">
											<tr id="confirmations">
												<td class="confirm">
													<bean:write name="confirmMessage" />
												</td>
											</tr>
										</logic:present>
										
										<logic:equal name="wisconsinAssessmentForm" property="secondaryAction" value="confirm">
											<tr>
												<td>
													<ul>
														<li>Click appropriate button below.</li>
													</ul>
												</td>
											</tr>
										</logic:equal>
										
										<logic:notEqual name="wisconsinAssessmentForm" property="secondaryAction" value="confirm">
											<tr>
												<td>
													<ul>
														<li>Verify that information is correct and select Finish button. If any changes are needed, select Back button. </li>
													</ul>
												</td>
											</tr>
										</logic:notEqual>
										
									</table>									
									<!-- END instructions -->
									
									<!-- BEGIN version TABLE -->
									
									<!-- end version TABLE  -->
									
										<!--assessment date start-->
												<table width="98%" cellpadding="4" cellspacing="1" class="borderTableBlue">
													<tr>
														<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.assessmentDate" /></td>
														<td class="formDe">
															<bean:write name="wisconsinAssessmentForm" property="assessmentDateStr" />
														</td>
													</tr>
												</table>
										<!--assessment date end-->
																						
											<!--risk assessment start-->
											<div class="spacer4px"></div>
											<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
												<tr class="detailHead">
													<td><bean:message key="prompt.riskAssessment"/></td>
												</tr>
																					
												<tr>
													<td>
														<table width="100%" cellpadding="2" cellspacing="1">
														
															<jims2:cscquestions name="wisconsinAssessmentForm" property="riskAssessmentQuestionsList" type="summary" columns="1"/>			
																											
															<tr bgcolor="black">
																<td colspan="2"><div class="spacer"></div></td>
															</tr>
															<tr>
																<td class="formDeLabel" align="right">Total Risk Score</td>
																<td class="formDe" nowrap width="15%"><bean:write name="wisconsinAssessmentForm" property="totalRiskScore" /></td>
															</tr>
															<tr>
																<td class="formDeLabel" align="right">Risk Level</td>
																<td class="formDe" nowrap><bean:write name="wisconsinAssessmentForm" property="riskLevel" /></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
											<!--risk assessment end-->
											
											<div class="spacer4px"></div>
											
											<!--needs assessment start-->
											<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
												<tr class="detailHead">
													<td><bean:message key="prompt.needs"/>&nbsp;<bean:message key="prompt.assessment"/></td>
												</tr>
												<tr>
													<td>
														<table width="100%" cellpadding="4" cellspacing="1">
															<jims2:cscquestions name="wisconsinAssessmentForm" property="needsAssessmentQuestionsList" type="summary" columns="1"/>
															<tr bgcolor="black">
																<td colspan="2"><div class="spacer"></div></td>
															</tr>
															<tr>
																<td class="formDeLabel" align="right">Total Needs Score</td>
																<td class="formDe" nowrap width="15%"><bean:write name="wisconsinAssessmentForm" property="totalNeedsScore" /></td>
															</tr>
															<tr>
																<td class="formDeLabel" align="right">Needs Level</td>
																<td class="formDe" nowrap><bean:write name="wisconsinAssessmentForm" property="needsLevel" /></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>	
											<!--needs assessment end-->
											
											<!--suggested LOS start-->
											<div class="spacer4px"></div>
											<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
												<tr class="detailHead">
													<td>Suggested Level of Supervision</td>
												</tr>
												<tr>
													<td>
														<table width="100%" cellpadding="4" cellspacing="1">
															<tr class="formDe">
																<td colspan="2"><bean:write name="wisconsinAssessmentForm" property="levelOfSupervision" /></td>
															</tr>															
														</table>
													</td>
												</tr>
											</table>
											<div class="spacer4px"></div>
											<!--suggested LOS end-->
											
											<!-- BEGIN BUTTON TABLE -->
											<table border="0" cellpadding="2" cellspacing="1">
												<tr>
													<td align="center">
														<logic:equal name="wisconsinAssessmentForm" property="secondaryAction" value="confirm">
															<html:button property="submitAction" onclick="openWindow('submitWisconsinAssessmentWebFocusPrintRequest.do?submitAction=Print')"><bean:message key="button.print"/></html:button>
															<input type="button" value="<bean:message key='button.assessmentsList' />"  onclick="goNav('/<msp:webapp/>displayAssessmentSummary.do?submitAction=Link')"></input>
														</logic:equal>
													
														 <!--  Summary Page -->
														 <logic:notEqual name="wisconsinAssessmentForm" property="secondaryAction" value="confirm">
														 	 <html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>	
														 	 
														 	 <logic:notEqual name="wisconsinAssessmentForm" property="action" value="delete">
														 	 	 <logic:equal name="wisconsinAssessmentForm" property="assessmentIncomplete" value="true">
														 	 	 	<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"> <bean:message key="button.saveAsDraft" /></html:submit>
														 	 	 </logic:equal>
														 	 </logic:notEqual>
														 	 
														 	 <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"> <bean:message key="button.finish" /></html:submit>       
														 	 <html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>                                                      
														 </logic:notEqual>
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
