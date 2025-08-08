<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/08/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 07/23/2010 CShimek           - #66506 revised Update button to CorrectInitial -->
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
<%@ page import="naming.Features" %>

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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/assessments/scsDetails.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displaySCSDetails" target="content">
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
	                                        <bean:message key="title.SCSInventory"/>&nbsp;<bean:message key="title.details"/>
	                                        <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Assessments/CSCD_Assessments.htm#|5"> 
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
							                            <li>Click appropriate button below. Click a version number to view prior version details.</li>
							                            <logic:equal name="scsAssessmentForm" property="supervisionPeriod" value="<%= CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV %>"> 
						                            		<li>Delete button will delete all the versions of an Assessment.</li>
						                            	</logic:equal>
							                        </ul>
												</td>
											</tr>
										</table>									
										<!-- END instructions -->
										
										
										<!-- BEGIN version TABLE -->
										<logic:equal name="scsAssessmentForm" property="assessmentIncomplete" value="false" >
											<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_VIEW_HISTORY%>'>
												<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
													<tr class="detailHead">
														<td><bean:message key="prompt.view"/>&nbsp;<bean:message key="prompt.priorVersions"/>&nbsp;</td>
													</tr>
													<tr>
														<td>
															<table width="100%" cellpadding="4" cellspacing="1">
																<tr class="formDeLabel">
																	<td nowrap width="5%"><bean:message key="prompt.version"/> #</td>
																	<td><bean:message key="prompt.transaction"/>&nbsp;<bean:message key="prompt.date"/></td>
																	<td><bean:message key="prompt.assessment"/>&nbsp;<bean:message key="prompt.date"/></td>
																</tr>
																
																<% int RecordCounter= -1;%>	
																 
															  	<logic:iterate id="eachAssessmentVersion" name="scsAssessmentForm" property="versionDetailsList">  
															    	<bean:define id="selectedVersion" type="java.lang.String" name="scsAssessmentForm" property="versionId"></bean:define>
															  		<logic:notEqual name="eachAssessmentVersion" property="versionNumber" value="<%=selectedVersion%>">                                                                         
																		<%RecordCounter++; %>
																		<tr class="<%out.print((RecordCounter % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																			<td align="center"><a href='/<msp:webapp/>displaySCSDetails.do?submitAction=View+Version&selectedVersionNumber=<bean:write name="eachAssessmentVersion" property="versionNumber"/>'><bean:write name="eachAssessmentVersion" property="versionNumber"/></a></td>
																			<td><bean:write name="eachAssessmentVersion" property="transactionDateStr"/></td>
																			<td><bean:write name="eachAssessmentVersion" property="assessmentDateStr" /></td>
																		</tr>
																	</logic:notEqual>
															  	</logic:iterate>
															</table>
														</td>
													</tr>
												</table>
												<div class="spacer4px"></div>
											</jims2:isAllowed>
										<!-- end version TABLE  -->
										
										<!--assessment date start-->
											<table width="98%" cellpadding="4" cellspacing="1" class="borderTableBlue">
												<tr>
													<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.version" /> #</td>
													<td class="formDe" nowrap width="25%"><bean:write name="scsAssessmentForm" property="versionId"/></td>
												
													<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.assessmentDate" /></td>
													<td class="formDe">
														<bean:write name="scsAssessmentForm" property="assessmentDateStr" /><logic:equal name="scsAssessmentForm" property="migrated" value="Y"><logic:notEmpty name="scsAssessmentForm" property="actualAssessmentDate" ><a href="#" title="" class=tooltip230><img src="/<msp:webapp/>images/starGreenIcon.gif" border=0><span><div>Actual Assessment Date: <bean:write name="scsAssessmentForm" property="actualAssessmentDate" formatKey="date.format.mmddyyyy"/></div></span></a></logic:notEmpty></logic:equal>
													</td>
												</tr>
											</table>
										<!--assessment date end-->
										</logic:equal>	
										
										<logic:equal name="scsAssessmentForm" property="assessmentIncomplete" value="true" >
										<!--assessment date start-->
											<table width="98%" cellpadding="4" cellspacing="1" class="borderTableBlue">
												<tr>
													<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.assessmentDate" /></td>
													<td class="formDe"><bean:write name="scsAssessmentForm" property="assessmentDateStr" /><logic:equal name="scsAssessmentForm" property="migrated" value="Y"><logic:notEmpty name="scsAssessmentForm" property="actualAssessmentDate" ><a href="#" title="" class=tooltip230><img src="/<msp:webapp/>images/starGreenIcon.gif" border=0><span><div>Actual Assessment Date: <bean:write name="scsAssessmentForm" property="actualAssessmentDate" formatKey="date.format.mmddyyyy"/></div></span></a></logic:notEmpty></logic:equal></td>
												</tr>
											</table>
										<!--assessment date end-->	
										</logic:equal>
										
										<div class="spacer4px"></div>
										
										<!-- scs start -->
										<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
											<tr class="detailHead">
												<td colspan="6"><bean:message key="prompt.SCS"/>&nbsp;<bean:message key="prompt.assessment"/></td>
											</tr>
											<tr>
												<td>
													<table width="100%" cellpadding="2" cellspacing="1">
														<jims2:cscquestions name="scsAssessmentForm" property="scsSummaryQuestionsList" type="summary" columns="4"/>
													</table>
													<div class="spacer" style="background-color:black"></div>
													<table width="100%" cellpadding="2" cellspacing="1">
														<tr>
															<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.total"/>&nbsp;SI</td>
															<td class="formDe"><bean:write name="scsAssessmentForm" property="totalSI" /></td>
															<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.total"/>&nbsp;CC</td>
															<td class="formDe"><bean:write name="scsAssessmentForm" property="totalCC" /></td>
															<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.total"/>&nbsp;ES</td>
															<td class="formDe"><bean:write name="scsAssessmentForm" property="totalES" /></td>
															<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.total"/>&nbsp;LS</td>
															<td class="formDe"><bean:write name="scsAssessmentForm" property="totalLS" /></td>
														</tr>
													</table>
													<div class="spacer" style="background-color:black"></div>
													<table width="100%" cellpadding="2" cellspacing="1">
														<tr>
															<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.primaryClassification"/></td>
															<td class="formDe" width="24%"><bean:write name="scsAssessmentForm" property="primaryClassificationTypeDesc" /></td>
															<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.secondaryClassification"/></td>
															<td class="formDe" width="24%"><bean:write name="scsAssessmentForm" property="secondaryClassificationTypeDesc" /></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>

										<div class="spacer4px"></div>
										<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
											<tr class="formDeLabel">
												<td><bean:message key="prompt.comments"/></td>
											</tr>
											<tr class="formDe">
												<td><bean:write name="scsAssessmentForm" property="comments" /></td>
											</tr>
										</table>
										<!-- scs end -->

										<div class="spacer4px"></div>
										
										<!-- BEGIN BUTTON TABLE -->
											<table border="0" cellpadding="2" cellspacing="1">
												<tr>
													<td align="center">
														<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>	
														
													 	<logic:equal name="scsAssessmentForm" property="supervisionPeriod" value="<%= CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV %>">   
													 		<logic:equal name="scsAssessmentForm" property="islatestVersionShown" value="true">  
													 			<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_UPDATE_WITH_RESTRC%>'>  
													 				<logic:equal name="scsAssessmentForm" property="assessmentIncomplete" value="true" >
												       					<html:submit property="submitAction"> <bean:message key="button.correctInitial" /></html:submit>
												       				</logic:equal>	
												       			</jims2:isAllowed>	
												       			<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_UPDATE_WITHOUT_RESTRC%>'>  
												       				<html:submit property="submitAction"> <bean:message key="button.correctInitial" /></html:submit>
												       			</jims2:isAllowed>	
												       			<logic:equal name="scsAssessmentForm" property="forceFieldAssessmentStatus" value="<%=CSCAssessmentConstants.ASSESSMENT_NOT_EXIST%>">
												       				<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_DELETE_WITHOUT_RESTRC%>'>
												          				<html:submit property="submitAction"> <bean:message key="button.delete" /></html:submit>
												          			</jims2:isAllowed>	 
												          			<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_DELETE_WITH_RESTRC%>'>
												          				<logic:equal name="scsAssessmentForm" property="sentToState" value="false">
																			<html:submit property="submitAction"> <bean:message key="button.delete" /></html:submit>
																		</logic:equal>	
												          			</jims2:isAllowed>	
											          			</logic:equal>
											          		</logic:equal> 
	                                               		</logic:equal>
	                                               		
	                                               		<logic:equal name="scsAssessmentForm" property="forceFieldAssessmentStatus" value="<%=CSCAssessmentConstants.ASSESSMENT_EXIST%>">
	                                               			<jims2:isAllowed requiredFeatures='<%=Features.CSCD_ASSESS_VIEW%>'>  
									          					<html:submit property="submitAction"> <bean:message key="button.viewForceFieldAnalysis" /></html:submit> 
									          				</jims2:isAllowed>	
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
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>