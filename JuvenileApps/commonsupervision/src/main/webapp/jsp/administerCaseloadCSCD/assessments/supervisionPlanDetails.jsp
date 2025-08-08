<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/11/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 11/07/2012 Richard Capestani - 74548 Move Delete Button -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<%@page import="naming.PDCodeTableConstants" %>
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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/assessments/supervisionPlanDetails.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/handleSupervisionPlanDetails" target="content">
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
									
										 <logic:equal name="supervisionPlanForm" property="secondaryAction" value="summary">
										 	 <logic:equal name="supervisionPlanForm" property="action" value="create">
										 	 	<bean:message key="prompt.new"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Supervision_Plan/CSCD_Caseload.htm#|7">
										 	 </logic:equal>
										 	 <logic:equal name="supervisionPlanForm" property="action" value="update">
										 	 	<bean:message key="title.update"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Supervision_Plan/CSCD_Caseload.htm#|11">
										 	 </logic:equal>
										 	 <logic:equal name="supervisionPlanForm" property="action" value="copy">
										 	 	<bean:message key="prompt.copy"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Supervision_Plan/CSCD_Caseload.htm#|3">
										 	 </logic:equal> 
										 	 <logic:equal name="supervisionPlanForm" property="action" value="delete">
												<bean:message key="prompt.delete"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Supervision_Plan/CSCD_Caseload.htm#|4">
											</logic:equal> 
										 	 <bean:message key="title.supervisionPlan"/>&nbsp;<bean:message key="title.summary" />
										 </logic:equal>
										 
										 <logic:equal name="supervisionPlanForm" property="secondaryAction" value="confirm">
										 	 <logic:equal name="supervisionPlanForm" property="action" value="create">
										 	 	<bean:message key="prompt.new"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Supervision_Plan/CSCD_Caseload.htm#|6">
										 	 </logic:equal>
										 	 <logic:equal name="supervisionPlanForm" property="action" value="update">
										 	 	<bean:message key="title.update"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Supervision_Plan/CSCD_Caseload.htm#|10">
										 	 </logic:equal>
										 	 <logic:equal name="supervisionPlanForm" property="action" value="copy">
										 	 	<bean:message key="prompt.copy"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Supervision_Plan/CSCD_Caseload.htm#|2">
										 	 </logic:equal> 
										 	 <bean:message key="title.supervisionPlan"/>&nbsp;<bean:message key="title.confirmation" />
										</logic:equal>
										 										
										<logic:equal name="supervisionPlanForm" property="action" value="view">
										    <bean:message key="title.supervisionPlan"/>
					                        <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Supervision_Plan/CSCD_Caseload.htm#|8">						  
										</logic:equal>
									</div>
									<!-- END HEADING -->
									
									<%-- BEGIN ERROR TABLE --%>
									<table width="98%" align="center">
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>
									</table>								
									<!-- END ERROR TABLE -->
									
									<!-- BEGIN instructions -->
									<table width="98%" border="0" cellpadding=0 cellspacing=0>
										<logic:present name="confirmMessage" >
											<tr id="confirmations">
												<td class="confirm"><bean:write name="confirmMessage"/></td>
											</tr>
										</logic:present>
									
										<logic:equal name="supervisionPlanForm" property="secondaryAction" value="summary">
											<tr>
												<td>
													<ul>
														<li>Verify that information is correct and select Finish button. If any changes are needed, select Back button.</li>
													</ul>
												</td>
											</tr>	
											
											<logic:notEqual name="supervisionPlanForm" property="action" value="delete">
												<tr>
													<td>	
														<ul>
															<li>Click Save as Draft to finish later.</li>
														</ul>	
													</td>
												</tr>		
											</logic:notEqual>
										</logic:equal>
											
										<logic:notEqual name="supervisionPlanForm" property="secondaryAction" value="summary">
											<tr>
												<td>
													<ul>
														<li>Click appropriate button below.</li>
													</ul>
												</td>
											</tr>		
										</logic:notEqual>
									</table>	
									<!-- END instructions -->
									
									<!-- START supervisionplan date -->
									<table width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<tr>
											<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.supervisionPlanDate"/></td>
											<td class="formDe"><bean:write name="supervisionPlanForm" property="supervisionPlanDateStr"/></td>
										</tr>
									</table>
									<div class="spacer4px"></div>
									<!-- END supervisionplan date-->
									
									<!--  START Supervisionplan -->
									<div></div>
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td width="25%">Supervision Plan</td>
											<td align="center" class="bodyText">
												<logic:equal name="supervisionPlanForm" property="action" value="view" >
													<span class="errorAlert">
														<bean:write name="supervisionPlanForm" property="statusDesc" /> 
													</span>
												</logic:equal>	
											</td>
											<td width="25%" align="right">																
												<div><a href="javascript:openWindow('/<msp:webapp/>displaySupervisionOrderConditions.do?submitAction=Link&defendantId=<bean:write name="supervisionPlanForm" property="defendantId"/>');" class=blackSubNav><bean:message key="prompt.viewConditions"/></a></div>
											</td>
										</tr>
										<tr>
											<td colspan="3">
												<div style="width:100%; text-align:center;">Last Change: <span class=boldText><bean:write name="supervisionPlanForm" property="lastChageDateStr"/> <bean:write name="supervisionPlanForm" property="lastChangeUserName"/></span></div>
												<table width="100%" cellpadding="2" cellspacing="1">
													<tr>
														<td>
															<div class="borderTable" style="padding:2px">
															<DIV class="formDeLabel spacer"><B><bean:message key="prompt.problem" /></B></DIV>
															<bean:write name="supervisionPlanForm" property="problem" filter="false"/></div>
															<div class="spacer4px"></div>
															<div class="borderTable" style="padding:2px">
															<DIV class="formDeLabel spacer"><B><bean:message key="prompt.behavioralObjective" /></B></DIV>
															<bean:write name="supervisionPlanForm" property="behaviorObjective" filter="false"/></div>
															<div class="spacer4px"></div>
															<div class="borderTable" style="padding:2px">
															<DIV class="formDeLabel spacer"><B><bean:message key="prompt.offenderActionPlan" /></B></DIV>
															<bean:write name="supervisionPlanForm" property="offenderActionPlan" filter="false"/></div>
															<div class="spacer4px"></div>
															<div class="borderTable" style="padding:2px">
															<DIV class="formDeLabel spacer"><B><bean:message key="prompt.csoActionPlan" /></B></DIV>
															<bean:write name="supervisionPlanForm" property="csoActionPlan" filter="false"/></div>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<!--  END Supervisionplan -->
									<div class="spacer4px"></div>
									
									<!-- BEGIN BUTTON TABLE -->
									<table border="0" cellpadding=2 cellspacing=1>
										<tr>
											<td align="center">
												<logic:equal name="supervisionPlanForm" property="secondaryAction" value="confirm">
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.print" /></html:submit>
													<input type="button" value="<bean:message key='button.assessmentsList'/>" onclick="javascript:goNav('/<msp:webapp/>displayAssessmentSummary.do?submitAction=Link') && disableSubmit(this, this.form)"></input>
												</logic:equal>
												<logic:notEqual name="supervisionPlanForm" property="secondaryAction" value="confirm">
														<logic:equal name="supervisionPlanForm" property="secondaryAction" value="summary">
															<html:hidden name="supervisionPlanForm" property="pageType" value="SUMMARY" />
															<logic:notEqual name="supervisionPlanForm" property="action" value="delete">
																<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"> <bean:message key="button.saveAsDraft" /></html:submit>
															</logic:notEqual>	
																<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"> <bean:message key="button.finish" /></html:submit>
														</logic:equal>
														<logic:equal name="supervisionPlanForm" property="action" value="view">
															<logic:equal name="supervisionPlanForm" property="activeSupervisionPeriod" value="true">
																<logic:equal name="supervisionPlanForm" property="statusCd" value="<%= PDCodeTableConstants.CSC_SUPERVISION_PLAN_STATUS_DRAFT %>">
																	<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPPLAN_UPDATE%>'>  	
																		<html:submit property="submitAction"> <bean:message key="button.update" /></html:submit>
																	</jims2:isAllowed>	
																</logic:equal>	
																<logic:notEqual name="supervisionPlanForm" property="statusCd" value="<%= PDCodeTableConstants.CSC_SUPERVISION_PLAN_STATUS_ACTIVE %>">
																	
																</logic:notEqual>
															</logic:equal>
															<logic:equal name="supervisionPlanForm" property="draftSupervisionPlanExists" value="false">
																<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPPLAN_COPY%>'>  	
																	<html:submit property="submitAction"> <bean:message key="button.copy" /></html:submit>
																</jims2:isAllowed>	
															</logic:equal>	
															<input type=button value="<bean:message key="button.print" />" onclick="openWindow('handleSupervisionPlanDetails.do?submitAction=Print&rrand=<%out.print((Math.random()*246));%>')">
														</logic:equal>
												</logic:notEqual>
                                            </td>   
										</tr>
										<tr>
											<td align="center">
												<logic:notEqual name="supervisionPlanForm" property="secondaryAction" value="confirm">
													<logic:equal name="supervisionPlanForm" property="action" value="view">
														<logic:equal name="supervisionPlanForm" property="activeSupervisionPeriod" value="true">
															<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"> <bean:message key="button.back" /></html:submit>
															<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
										          			<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPPLAN_DELETE_WITH_RESTRC%>'>
																<logic:equal name="supervisionPlanForm" property="statusCd" value="<%= PDCodeTableConstants.CSC_SUPERVISION_PLAN_STATUS_DRAFT %>">
																	<html:submit property="submitAction"> <bean:message key="button.delete" /></html:submit>
																</logic:equal>
															</jims2:isAllowed>
															<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPPLAN_DELETE_WTHOUT_RESTRC%>'>
																<logic:equal name="supervisionPlanForm" property="statusCd" value="<%= PDCodeTableConstants.CSC_SUPERVISION_PLAN_STATUS_ACTIVE %>">
																	<html:submit property="submitAction"> <bean:message key="button.delete" /></html:submit>
																</logic:equal>
										          			</jims2:isAllowed>
									          			</logic:equal>
								          			</logic:equal>
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
