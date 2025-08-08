<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 06/20/2008 Debbie Williamson - Converted PT to JSP -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDCodeTableConstants" %>
<%@ page import="naming.UIConstants" %>
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
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_court_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/transferCaseSummaryAction" target="content">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>

		<td valign="top">

			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--blue tabs start-->
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
							    <tiles:put name="tab" value="caseloadTab" />
						    </tiles:insert>
						<!--blue tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<!-- BEGIN HEADING TABLE -->
						<table width="100%">
							<tr>
								<td align="center" class="header">
									<bean:message key="title.CSCD"/>&nbsp;-&nbsp;
									<bean:message key="prompt.transfer"/>
									<logic:equal name="caseAssignmentForm" property="superviseeTransferCases.transferType" value="<%=UIConstants.CS_TRANSFER_IN_CASES%>">
										<bean:message key="prompt.in"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Transfers/CSCD_Transfers.htm#|9">
									</logic:equal>
									<logic:equal name="caseAssignmentForm" property="superviseeTransferCases.transferType" value="<%=UIConstants.CS_TRANSFER_OUT_CASES%>">
										<bean:message key="prompt.out"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Transfers/CSCD_Transfers.htm#|5">
									</logic:equal>		
									<bean:message key="title.summary"/>
								</td>
							</tr>
						</table>
						<!-- END HEADING TABLE -->
						<!-- BEGIN ERROR TABLE -->
						<table width="98%" align="center">							
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>		
						</table>
						<!-- END ERROR TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
							<tr>
								<td>
									<ul>
										<li>Verify that information is correct and select Finish button. If any changes are needed, select Back button. </li>
									</ul>
								</td>
							</tr>
						</table>
						<!-- END INSTRUCTION TABLE -->
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td bgcolor="#cccccc">
									<!--header start-->
										<tiles:insert page="../common/assignmentHeader.jsp" flush="true">
									    </tiles:insert>
									<!--header end-->
								</td>
							</tr>
						</table>
						<br>
						<!-- BEGIN DETAIL TABLE -->
						<span id="caselist">
						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr class="detailHead">
								<td>
									<bean:message key="prompt.casesToBeTransferred"/>&nbsp;
									<logic:equal name="caseAssignmentForm" property="superviseeTransferCases.transferType" value="<%=UIConstants.CS_TRANSFER_IN_CASES%>">
										<bean:message key="prompt.in"/>
									</logic:equal>
									<logic:equal name="caseAssignmentForm" property="superviseeTransferCases.transferType" value="<%=UIConstants.CS_TRANSFER_OUT_CASES%>">
										<bean:message key="prompt.out"/>
									</logic:equal>									
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellpadding="2" cellspacing="1">
										<tr class="formDeLabel">
											<td title="Degree of Offense"><bean:message key="prompt.DEG"/></td>
											<td><bean:message key="prompt.case"/></td>
											<td><bean:message key="prompt.CRT"/></td>
											<td title="Date of Community Supervision"><bean:message key="prompt.DOCS"/></td>
											<td title="Community Supervision End Date"><bean:message key="prompt.endDate"/></td>
											<td><bean:message key="prompt.daysLeft"/></td>
											<td title="Case Status"><bean:message key="prompt.caseStatus"/></td>
											<td title="Defendant Status"><bean:message key="prompt.defendantStatus"/></td>
										</tr>
										<logic:notEmpty name="caseAssignmentForm" property="superviseeTransferCases.casesToTransfer">
                                        <%int RecordCounter = 0;%>
											<logic:iterate id="transferCase" name="caseAssignmentForm" property="superviseeTransferCases.casesToTransfer">
	                                            <tr class="<% out.print( ((++RecordCounter) % 2 == 1) ? "normalRow" : "alternateRow" );%>">
													<td><bean:write name="transferCase" property="degreeOfOffense"/></td>
													<td>		
														<a href="javascript:openWindow('/<msp:webapp/>displaySupervisionOrderDetails.do?submitAction=Link&selectedValue=<bean:write name="transferCase" property="supervisionOrderId"/>')">																																						
															<bean:write name="transferCase" property="criminalCaseId"/>
														</a>
													</td>																																																									
													<td><bean:write name="transferCase" property="courtId"/></td>
													<td><bean:write name="transferCase" property="probationStartDate"/></td>
													<td><bean:write name="transferCase" property="probationEndDate"/></td>
													<logic:equal name="transferCase" property="daysLeft" value="<%=String.valueOf(Long.MAX_VALUE)%>">
														<td/>
													</logic:equal>
													<logic:notEqual name="transferCase" property="daysLeft" value="<%=String.valueOf(Long.MAX_VALUE)%>">
														<td><bean:write name="transferCase" property="daysLeft"/></td>
													</logic:notEqual>
													<td><bean:write name="transferCase" property="caseStatus"/></td>
													<td><bean:write name="transferCase" property="defendantStatus"/></td>																			
												</tr>
											</logic:iterate>
                                       </logic:notEmpty>
									</table>
								</td>
							</tr>
						</table>
						<div class="spacer4px"></div>
						</span>
						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<logic:equal name="caseAssignmentForm" property="superviseeTransferCases.transferType" value="<%=UIConstants.CS_TRANSFER_IN_CASES%>">
								<tr class="detailHead">
									<td>
										<bean:message key="prompt.transferInInformation"/>
									</td>
								</tr>
								<tr>
									<td>
										<table width="100%" border="0" cellpadding="4" cellspacing="1">
												<tr>
													<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.rejected"/></td>
													<td class="formDe" colspan="3">
														<logic:equal name="caseAssignmentForm" property="superviseeTransferCases.rejected" value="true">
															<bean:message key="prompt.yes"/>
														</logic:equal>	
														<logic:equal name="caseAssignmentForm" property="superviseeTransferCases.rejected" value="false">
															<bean:message key="prompt.no"/>
														</logic:equal>	
													</td>
												</tr>
												<tr>															
													<td class="formDeLabel" nowrap><bean:message key="prompt.transferInDate"/></td>
													<td class="formDe">
														<bean:write name="caseAssignmentForm" property="superviseeTransferCases.transferInDate" formatKey="date.format.mmddyyyy"/>
													</td>
												</tr>
												<tr>
													<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.texasCounty"/></td>
													<td class="formDe" colspan="3">
														<bean:write name="caseAssignmentForm" property="superviseeTransferCases.transferTxCountyName"/>
													</td>
												</tr>
												<tr>
													<td class="formDeLabel" nowrap><bean:message key="prompt.outOfState"/></td>
													<td class="formDe" colspan="3">
														<bean:write name="caseAssignmentForm" property="superviseeTransferCases.transferStateName"/>
													</td>
												</tr>
												<tr>
													<td class="formDeLabel" nowrap><span  id="requiredPID"><span class="diamond"></span></span><bean:message key="prompt.personID"/></td>
													<td class="formDe" colspan="3">
														<bean:write name="caseAssignmentForm" property="superviseeTransferCases.personId" />
													</td>
												</tr>
											</table>
										</td>
									</tr>
							</logic:equal>
							<logic:equal name="caseAssignmentForm" property="superviseeTransferCases.transferType" value="<%=UIConstants.CS_TRANSFER_OUT_CASES%>">							
								<tr class="detailHead">
									<td>
										<bean:message key="prompt.transferOutInformation"/>
									</td>
								</tr>
								<tr>
									<td>
										<table width="100%" border="0" cellpadding="2" cellspacing="1">
											<tr>
												<td class="formDeLabel" nowrap width="1%">
													<bean:message key="prompt.transferOutDate"/>
												</td>
												<td class="formDe">
													<bean:write name="caseAssignmentForm" property="superviseeTransferCases.transferOutDate" formatKey="date.format.mmddyyyy"/>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap><bean:message key="prompt.texasCounty"/></td>
												<td class="formDe">
													<bean:write name="caseAssignmentForm" property="superviseeTransferCases.transferTxCountyName" />
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap><bean:message key="prompt.outOfState"/></td>
												<td class="formDe">
													<bean:write name="caseAssignmentForm" property="superviseeTransferCases.transferStateName" />
												</td>
											</tr>
										</table>									
									</td>
								</tr>
							</logic:equal>																		
						</table>
						<!-- END DETAIL TABLE -->
						<br>
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"> 
										<bean:message key="button.finish" />
									</html:submit>
									<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
								</td>
							</tr>
						</table>
						<!-- END BUTTON TABLE -->
						</td>
					</tr>
				</table>
				<br>
			</td>
		</tr>
	</table>
</div>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
