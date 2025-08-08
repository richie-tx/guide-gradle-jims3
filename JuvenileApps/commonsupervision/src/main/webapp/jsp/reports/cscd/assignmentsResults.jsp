<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/17/2007	 Debbie Williamson - Create JSP -->
<%-- 03/04/2008	LDeen	Defect #49819 integrate help    --%>
<%-- 04/24/2009	CShimek Defect #58511 revised courtId to displayCourtId to display formatted courtId  --%>
<%-- 09/22/2009	CShimek Defect #61738 revised Case(s) to use displayCaseNum value  --%>
<%-- 02/16/2010	RYoung  Defect #63964 Sorted results by Program Unit Assign Date Desc  --%>
<!-- 11/08/2010 TSVines Defect #71044 Changes inline CSS for the header and changed "handlePositionSelection.do?submitAction=Position History" remove Position Name history popup -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - reports/assignmentsResults.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javaScript" src="../case_util.js"></script>
<style>

    #bottomInformationContainer{
               width:98%;
                padding: 2px;
    }
    #reportPrintedContainer{
                float:left;
                width:50%;
                text-align:left;
    }
    #generatedByContainer{
                float:right; 
                width:50%; 
                text-align:right;
    }

</style>

</head>
<body topmargin="0" leftmargin="0">
	<html:form action="/caseAssignmentReportResultAction" target="content">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Reports\View_Assignments.htm#|2">
	<div align="center">
		<table width="98%" border="0" cellpadding="0" cellspacing="0" id="mainTable">
			<tr>
				<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
			</tr>
			<tr>
				<td valign="top">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" id="commonSupervisionTabsTable">
						<tr>
							<td valign="top">
								<!--tabs start-->
								<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
									<tiles:put name="tabid" value="setupTab"/>
								</tiles:insert>
								<!--tabs end-->
							</td>
						</tr>
						<tr>
							<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
						</tr>
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue" id="blueBorderTable">
						<tr>
							<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
						</tr>
						<tr>
							<td valign="top" align="center">
								<table width="98%" border="0" cellpadding="0" cellspacing="0" id="managerFeaturesTabTable">
									<tr>
										<td valign="top">
											<!--tabs start-->
                                            <tiles:insert page="../../common/manageFeaturesTabs.jsp" flush="true">
												<tiles:put name="tabid" value="reportsTab"/>
											</tiles:insert>						
											<!--tabs end-->
										</td>
									</tr>
                                    <!-- Add these links later (DAW) -->   
									<%--tr>
										<td bgcolor=#33cc66><script language="JavaScript">manageFeaturesSubNav("Reports")</script></td>
									</tr--%>
								</table>
								<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen" id="greenBorderTable">
									<tr>
										<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
									</tr>
									<tr>
										<td valign="top" align="center">
											<!-- BEGIN HEADING TABLE -->
											<table width="100%" id="headingTable">
												<tr>
													<td align="center" class="header">
														<bean:message key="title.CSCD" /> - <bean:message key="title.assignment" />&nbsp;
														<bean:message key="prompt.searchResults" />
													</td>
												</tr>
											</table>
											<!-- END HEADING TABLE -->
										    <!-- BEGIN ERROR TABLE -->
											<table width="98%" align="center" id="errorTable"> 							
												<tr>
													<td align="center" class="errorAlert"><html:errors></html:errors></td>
												</tr>		
											</table>
										    <!-- END ERROR TABLE -->

											<!-- BEGIN INSTRUCTION TABLE -->
											<!-- this is the search criteria header - it depends upon which type of search was run -->
											<table width="98%" border="0" cellpadding="0" cellspacing="0" id="searchHeaderTable">
												<tr>
													<td bgcolor="#cccccc" colspan="2">
														<!--pu header start-->
														<logic:equal name="caseAssignmentReportForm" property="searchBy" value="programUnit"> 																												
															<table width="100%" border="0" cellpadding="2" cellspacing="1" id="programUnitSearchHeaderTable">
																<tr>
																	<td class="headerLabel"><bean:message key="prompt.cases" /></td>
																	<td class="headerData"><bean:write name="caseAssignmentReportForm" property="programUnitReport.noOfCases"/></td>
																	<td class="headerLabel" nowrap="nowrap"><bean:message key="prompt.assignmentDate" />&nbsp;<bean:message key="prompt.range" /></td>
																	<td class="headerData"><bean:write name="caseAssignmentReportForm" property="programUnitReport.assignmentStartDate" formatKey="date.format.mmddyyyy" />&nbsp;-
	                                                                                     <bean:write name="caseAssignmentReportForm" property="programUnitReport.assignmentEndDate" formatKey="date.format.mmddyyyy" /></td>
																</tr>
																<tr>
																	<td class="headerLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.programUnit" /></td>
																	<td class="headerData" colspan="3">
																		<logic:iterate id="programUnitIdx" name="caseAssignmentReportForm" property="programUnitReport.selectedProgramUnitsNames">
																			<div><bean:write name="programUnitIdx"/></div>
																		</logic:iterate>
																	</td>
																</tr>
															</table>
														</logic:equal>
														<!--pu header end-->
														<!--supervisee header start-->
														<logic:equal name="caseAssignmentReportForm" property="searchBy" value="supervisee"> 																												
															<table width="100%" border="0" cellpadding="2" cellspacing="1" id="superviseeSearchHeaderTable">
																<tr>
																	<td class="headerLabel"><bean:message key="prompt.cases" /></td>
																	<td class="headerData"><bean:write name="caseAssignmentReportForm" property="superviseeReport.noOfCases"/></td>
																	<td class="headerLabel" width="1%" nowrap="nowrap"="nowrap="nowrap""><bean:message key="prompt.assignmentDate" />&nbsp;<bean:message key="prompt.range" /></td>
																	<td class="headerData"><bean:write name="caseAssignmentReportForm" property="superviseeReport.assignmentStartDate" formatKey="date.format.mmddyyyy" />&nbsp;-
	                                                                                     <bean:write name="caseAssignmentReportForm" property="superviseeReport.assignmentEndDate" formatKey="date.format.mmddyyyy" /></td>
																</tr>															
																<tr>
																	<td class="headerLabel"><bean:message key="prompt.supervisee" /></td>
																	<td class="headerData"><bean:write name="caseAssignmentReportForm" property="superviseeReport.defendantName"/></td>
																	<td class="headerLabel" width="1%"><bean:message key="prompt.SPN" /></td>
																	<td class="headerData"><bean:write name="caseAssignmentReportForm" property="superviseeReport.defendantId"/></td>
																</tr>
															</table>
														</logic:equal>
														<!--supervisee header end-->
														
														<!--case header start-->
														<logic:equal name="caseAssignmentReportForm" property="searchBy" value="individualCase"> 																												
															<table width="100%" border="0" cellpadding="2" cellspacing="1" id="caseSearchHeaderTable">
																<tr>
																	<td class="headerLabel"><bean:message key="prompt.cases" /></td>
																	<td class="headerData"><bean:write name="caseAssignmentReportForm" property="singleCaseReport.noOfCases"/></td>
																	<td class="headerLabel"><bean:message key="prompt.case" /></td>
																	<td class="headerData"><bean:write name="caseAssignmentReportForm" property="singleCaseReport.criminalCaseId"/></td>
																	<td class="headerLabel"><bean:message key="prompt.CDI" /></td>
																	<td class="headerData"><bean:write name="caseAssignmentReportForm" property="singleCaseReport.courtDivisionIndicator"/></td>
																</tr>
															</table>
														</logic:equal>
														<!--case header end-->
														<!--user header start-->
														<logic:equal name="caseAssignmentReportForm" property="searchBy" value="user"> 																												
															<table width="100%" border="0" cellpadding="2" cellspacing="1" id="userSearchHeaderTable">
																<tr>
																	<td class="headerLabel"><bean:message key="prompt.cases" /></td>
																	<td class="headerData"><bean:write name="caseAssignmentReportForm" property="userReport.noOfCases"/></td>
																	<td class="headerLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.assignmentDate" />&nbsp;<bean:message key="prompt.range" /></td>
																	<td class="headerData"><bean:write name="caseAssignmentReportForm" property="userReport.assignmentStartDate" formatKey="date.format.mmddyyyy" />&nbsp;-
	                                                                                     <bean:write name="caseAssignmentReportForm" property="userReport.assignmentEndDate" formatKey="date.format.mmddyyyy" /></td>
																</tr>
																<tr>
																	<td class="headerLabel"><bean:message key="prompt.user" /></td>
																	<td class="headerData"><bean:write name="caseAssignmentReportForm" property="userReport.userName"/></td>
																	<td class="headerLabel" width="1%"><bean:message key="prompt.userId" /></td>
																	<td class="headerData"><bean:write name="caseAssignmentReportForm" property="userReport.userId"/></td>
																</tr>
															</table>
														</logic:equal>
														<!--user header end-->
														<!--workgroup header start-->
														<logic:equal name="caseAssignmentReportForm" property="searchBy" value="workGroup"> 																												
															<table width="100%" border="0" cellpadding="2" cellspacing="1" id="workgroupSearchHeaderTable">
																<tr>
																	<td class="headerLabel"><bean:message key="prompt.cases" /></td>
																	<td class="headerData"><bean:write name="caseAssignmentReportForm" property="workgroupReport.noOfCases"/></td>
																	<td class="headerLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.assignmentDate" />&nbsp;<bean:message key="prompt.range" /></td>
																	<td class="headerData"><bean:write name="caseAssignmentReportForm" property="workgroupReport.assignmentStartDate" formatKey="date.format.mmddyyyy" />&nbsp;-
	                                                                                     <bean:write name="caseAssignmentReportForm" property="workgroupReport.assignmentEndDate" formatKey="date.format.mmddyyyy" /></td>
																</tr>
																<tr>
																	<td class="headerLabel"><bean:message key="prompt.workgroup" /></td>
																	<td class="headerData"><bean:write name="caseAssignmentReportForm" property="workgroupReport.selectedWorkGroupName"/></td>
																	<td class="headerLabel"><bean:message key="prompt.programUnit" /></td>
																	<td class="headerData">
																		<logic:iterate id="programUnitIdx" name="caseAssignmentReportForm" property="workgroupReport.selectedProgramUnitsNames">
																			<div><bean:write name="programUnitIdx" /></div>
																		</logic:iterate>
																	</td>
																</tr>
															</table>
														</logic:equal>
														<!--workgroup header end-->
													</td>
												</tr>
												<tr>
													<td><img src="/<msp:webapp/>images/spacer.gif" height="10"></td>
												</tr>
											</table>

											<table width="98%" cellpadding="2" cellspacing="1" border="0" class="borderTableBlue" id="searchResultsTable">
                                              <logic:notEmpty name="caseAssignmentReportForm" property="searchResults">	
                                              	 <logic:iterate id="SPNIndex" name="caseAssignmentReportForm" property="searchResults" indexId="index">
													<tr class="formDe">
														<td colspan="3">
															<table width="100%" cellpadding="0" cellspacing="0">
																<tr>
																	<td>
																		<span class="formDeLabel"><bean:message key="prompt.supervisee" />:</span>
																		<logic:notEmpty name="SPNIndex" property="defendantName"> 
																			<a href="/<msp:webapp/>displaySuperviseeInfo.do?submitAction=Link&superviseeId=<bean:write name="SPNIndex" property="defendantId"/>">
																				<bean:write name="SPNIndex" property="defendantName.formattedName"/>
																			</a>																			
																		</logic:notEmpty>
																	</td>
																</tr>
															</table>
														</td>
														<td colspan="3">
															<table width="100%" cellpadding="0" cellspacing="0">
																<tr>
																	<td>
																		<span class="formDeLabel"><bean:message key="prompt.SPN" />:</span>
																		<bean:write name="SPNIndex" property="defendantId"/>
																	</td>
																	<td align="right">
																		<a href="/<msp:webapp/>caseAssignmentReportResultAction.do?submitAction=Link&superviseeReport.defendantId=<bean:write name="SPNIndex" property="defendantId"/>">
																			<bean:message key="prompt.viewCasenotes"/>
																		</a>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr class="detailHead">
														<td width="30%"><bean:message key="prompt.cases" /></td>
														<td width="10%"><bean:message key="prompt.court" />(s)</td>															
														<td><bean:message key="prompt.programUnit" /></td>
														<td><bean:message key="prompt.puAssignDate" /> / System Date</td>
														<td><bean:message key="prompt.positionName" /></td>
														<td><bean:message key="prompt.soAssignDate" /> / System Date</td>
													</tr>
													<jims:sortResults beanName="SPNIndex" results="activeCases" 
                    					                        primaryPropSort="programUnitAssignDate" primarySortType="DATE" defaultSort="true" 
                                        					    defaultSortOrder="DESC" sortId="<%out.print(index.intValue()); %>" hideMe="true" />
													
									               <logic:iterate id="caseIndex" name="SPNIndex" property="activeCases">
													<tr>
														<td><bean:write name="caseIndex" property="displayCaseNum"/></td>
														<td><bean:write name="caseIndex" property="displayCourtId"/></td>
														<td><bean:write name="caseIndex" property="programUnitName"/></td>
													<td>
															<bean:write name="caseIndex" property="programUnitAssignDate" formatKey="date.format.mmddyyyy"/>&nbsp;/&nbsp;
															<bean:write name="caseIndex" property="programUnitAssignmentTranactionDate" formatKey="date.format.mmddyyyy"/>
														</td>
														<td>
															<logic:notEmpty name="caseIndex" property="assignedStaffPositionName">
																<a href="/<msp:webapp/>handlePositionSelection.do?submitAction=Position History&selectedValue=<bean:write name="caseIndex" property="assignedStaffPositionId"/>">
																	<bean:write name="caseIndex" property="assignedStaffPositionName"/>
																</a>
															</logic:notEmpty>
														</td>
														<td>
															<logic:notEmpty name="caseIndex" property="officerAssignDate">
																<bean:write name="caseIndex" property="officerAssignDate" formatKey="date.format.mmddyyyy"/>&nbsp;/&nbsp;
																<bean:write name="caseIndex" property="officerAssignmentTransactionDate" formatKey="date.format.mmddyyyy"/>
															</logic:notEmpty>
														</td>
													</tr>
	                                                </logic:iterate>
	                                              </logic:iterate>
                                             </logic:notEmpty> 
											</table>
											<div id="bottomInformationContainer" class="smallBoldText">
                                        <div id="reportPrintedContainer">Report Printed: <script>document.write(getCurrentDate() + " " + getCurrentTime(true));</script></div>
                                        <div id="generatedByContainer">Generated By:  <bean:write name="caseAssignmentReportForm" property="logonUserName" /></div>
                                        </div>

											
											<br>
											<!-- BEGIN BUTTON TABLE -->
											<table border="0" width="100%" id="buttonsTable">
												<tr>
													<td align="center">
														<html:submit property="submitAction" styleId="submitButton">
															<bean:message key="button.back"></bean:message>
														</html:submit>&nbsp;
														<input type="button" value="Print" onClick="window.print()">&nbsp;														
														<html:submit property="submitAction" styleId="cancelButton">
															<bean:message key="button.cancel"></bean:message>
														</html:submit>  																																		 																	 														   
													</td>
												</tr>
											</table>
											<!-- END BUTTON TABLE -->
										</td>
									</tr>
								</table><br>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

		<!-- END  TABLE -->
	</div>
	<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
