<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/30/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 08/03/2009 C Shimek          - #61326 Added Program End Date and Reason for Discharge display fields for Exit Program action state -->
<!-- 08/03/2009 C Shimek          - #61048 revised to not display Contract Pgm and Tracer Num when service provider Contract Pgm is No on Submit Referral -->
<!-- 07/12/2011 R Young      - #68612 added code needed to make referral date and entry field -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<%@ page import="naming.CSAdministerProgramReferralsConstants" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/referralFormSummary.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>

<script>

	function printAppointment()
	{
		var selectedProgRefId = document.getElementsByName('progRefId')[0].value;
		var url = "displayProgRefAppointment.do?submitAction=Link&selectedValue=" + selectedProgRefId;
		openWindow(url);
	}
	
</script>
</head>
<!--END HEADER TAG-->
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/submitProgReferral" target="content">

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
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					<tr>
						<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
					<tr>
						<td valign="top" align="center">
							<!--header area start-->
							<table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td bgcolor="#CCCCCC" colspan="2">
										<!--header start-->
                                            <tiles:insert page="../../common/caseloadHeaderCase.jsp" flush="true">
									        </tiles:insert>	
      							        <!--header end-->
									</td>
								</tr>
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
							</table>
							<!--header area end-->
							<!--casefile tabs start-->
							<table width="98%" border="0" cellpadding="0" cellspacing="0" >
								<tr>
									<td valign="top">
										<!--tabs start-->
											<tiles:insert page="../../common/caseloadCSCDSubTabs.jsp" flush="true">
												<tiles:put name="tab" value="ProgramReferralsTab" />
											</tiles:insert>
										<!--tabs end-->
									</td>
								</tr>
								<tr>
									<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
							</table>
							<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>
								<tr>
									<td valign="top" align="center">
										<!-- BEGIN HEADING TABLE -->
										<table width="100%">
											<tr>
												<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-
													<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_SUBMITREFERRAL%>">
													   <bean:message key="title.submit"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|23">
													</logic:equal>
													<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_EXITREFERRAL%>">
													   <bean:message key="prompt.exit"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|16">
													</logic:equal>
													<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_UPDATE_SUBMIT%>">
														<bean:message key="title.update"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|12">
													</logic:equal>
													<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_UPDATE_EXIT%>">
														<bean:message key="title.update"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|#">
													</logic:equal>
													<bean:message key="prompt.program"/>
													<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_REREFERRAL%>">
													   <bean:message key="prompt.rereferral"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|27">
													</logic:equal>													
													<logic:notEqual name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_REREFERRAL%>">
													   <bean:message key="prompt.referral"/>
													</logic:notEqual>
													<bean:message key="title.summary"/>
                                                </td>    													
											</tr>
										</table>
										<!-- END HEADING TABLE -->
										<%-- BEGIN ERROR TABLE --%>
										<table width="98%" align="center">
											<tr>
												<td align="center" class="errorAlert"><html:errors></html:errors></td>
											</tr>
										</table>								
										<!-- END ERROR TABLE -->
										<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center">
											<tr>
												<td>
                                              <logic:notEqual name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_REREFERRAL%>">
                                                 <ul>
													<li>
														Review entries and click Finish.
													</li>
												</ul>
                                              </logic:notEqual>       
											</td>
											</tr>
										</table>
										
										<!--Conditions List start-->
										<table width="100%" border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<!--conditions list tile start-->
													<tiles:insert page="prConditionsListTile.jsp" flush="true">
														<tiles:put name="condList" beanName="cscProgRefForm" beanProperty="conditionsList"></tiles:put>
													</tiles:insert> 
													<!--conditions list tile end-->
												</td>
											</tr>
										</table>
										<!--Conditions List end-->
										<div class="spacer4px"></div>
										
										<html:hidden name="cscProgRefForm" property="progRefId" />
										
										<table width="98%" cellpadding="0" cellspacing="0" border="0">
											<tr>
												<td width="100%" valign="top">
													<table width="100%" cellpadding="2" cellspacing="0" class="borderTableBlue">
														<tr class="detailHead">
															<td><bean:message key="prompt.programReferral"/>&nbsp;<bean:message key="prompt.info"/></td>
														</tr>
														<tr>
															<td>
																<table width="100%" cellpadding="2" cellspacing="1">
																	<tr>
																		<td colspan="8">
																			<table width="100%" cellpadding="4" cellspacing="1">
																			  <logic:equal name="cscProgRefForm" property="referralStatusCd" value="<%=CSAdministerProgramReferralsConstants.EXITED_REFERRAL_STATUS %>">	
																				<tr>
																					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.programEndDate"/></td>
																					<td class="formDe" colspan="3">
																					   <bean:write name="cscProgRefForm" property="programEndDateAsStr" formatKey="date.format.mmddyyyy"/>
																					</td>
																				</tr>
																				<tr>
																					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.reasonForDischarge"/></td>
																					<td class="formDe" colspan="3">
																					    <bean:write name="cscProgRefForm" property="reasonForDischargeDesc"/>
																					</td>
																				</tr>
																			  </logic:equal>
																			  <logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_EXITREFERRAL %>">	
																				<tr>
																					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.programEndDate"/></td>
																					<td class="formDe" colspan="3">
																					   <bean:write name="cscProgRefForm" property="programEndDateAsStr" formatKey="date.format.mmddyyyy"/>
																					</td>
																				</tr>
																				<tr>
																					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.reasonForDischarge"/></td>
																					<td class="formDe" colspan="3">
																					    <bean:write name="cscProgRefForm" property="reasonForDischargeDesc"/>
																					</td>
																				</tr>
																			  </logic:equal>
																			  <tr>
																					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.referralDate"/></td>
																					<td class="formDe" colspan="3">
																						<bean:write name="cscProgRefForm" property="referralDateAsStr" />
																					</td>
																				</tr>
																				<tr>
																					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.programBeginDate"/></td>
																					<td class="formDe" colspan="3">
																						<bean:write name="cscProgRefForm" property="programBeginDateAsStr" />
																					</td>
																				</tr>
																				<tr id="contratPgmTracerNum" class="visible">
																					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.contractProgram"/></td>
																					<td class="formDe">
																						<logic:equal name="cscProgRefForm" property="contractProgram" value="true" >
																							Yes
																						</logic:equal>
																						<logic:equal name="cscProgRefForm" property="contractProgram" value="false" >
																							No
																						</logic:equal>
																					</td>
																					<logic:equal name="cscProgRefForm" property="contractProgram" value="true" >
																						<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.tracerNumber"/></td>
																						<td class="formDe">
																							<bean:write name="cscProgRefForm" property="tracerNum"/>
																						</td>
																					</logic:equal>
																				</tr>
																				<tr>
																					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.reasonForPlacement"/></td>
																					<td class="formDe" colspan="3">
																						<bean:write name="cscProgRefForm" property="reasonForPlacementDesc"/>
																					</td>
																				</tr>
																				<logic:equal name="cscProgRefForm" property="incarcerationReferral" value="true">
																					<tr>
																						<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.confinementLength"/></td>
																						<td class="formDe" colspan="3">
																							<bean:write name="cscProgRefForm" property="confinementLengthYears" />&nbsp;Years&nbsp;
																							<bean:write name="cscProgRefForm" property="confinementLengthMonths" />&nbsp;Months&nbsp;
																							<bean:write name="cscProgRefForm" property="confinementLengthDays" />&nbsp;Days&nbsp;
																						</td>
																					</tr>
																				</logic:equal>
																			</table>
																		</td>
																	</tr>
																	<tr class="formDeLabel">
																	    <td><bean:message key="prompt.serviceProvider"/></td>
																		<td><bean:message key="prompt.referralType"/></td>
																		<td><bean:message key="prompt.identifier"/></td>
																		<td><bean:message key="prompt.name"/></td>
																		<td width="5%"><bean:message key="prompt.CSTSCode"/></td>
																		<td><bean:message key="prompt.languagesOffered"/></td>
																		<td><bean:message key="prompt.sexSpecific"/></td>
																		<td><bean:message key="prompt.contractProgram"/></td>
																	</tr>
																	<tr class="programRow">
																		<td class="bottomBorder"><a href="javascript:openWindow('/<msp:webapp/>displayCSCServiceProviderUpdate.do?submitAction=View Details&selectedValue=<bean:write name="cscProgRefForm" property="referralProgramLocBean.serviceProviderId" />')"><bean:write name="cscProgRefForm" property="referralProgramLocBean.serviceProviderName" /></a>&nbsp;</td>
																		<td class="bottomBorder"><bean:write name="cscProgRefForm" property="referralProgramLocBean.referralTypeDesc" /></td>
																		<td class="bottomBorder"><a href="javascript: openWindow('/<msp:webapp/>displayCSCProgramUpdate.do?submitAction=View Details&selectedValue=<bean:write name="cscProgRefForm" property="referralProgramLocBean.programId"/>')"><bean:write name="cscProgRefForm" property="referralProgramLocBean.programIdentifier" /></a>&nbsp;</td>
																		<td class="bottomBorder"><bean:write name="cscProgRefForm" property="referralProgramLocBean.programName" />&nbsp;</td>
																		<td class="bottomBorder"><bean:write name="cscProgRefForm" property="referralProgramLocBean.cstsCode" />&nbsp;</td>
																		<td class="bottomBorder">
																			<logic:iterate id="eachLanguage" name="cscProgRefForm" property="referralProgramLocBean.languagesOffered" >
																				<bean:write name="eachLanguage" />,
																			</logic:iterate>
																			&nbsp;
																		</td>
																		<td class="bottomBorder"><bean:write name="cscProgRefForm" property="referralProgramLocBean.sexSpecificDesc" />&nbsp;</td>
																		<td class="bottomBorder"><bean:write name="cscProgRefForm" property="referralProgramLocBean.contractProgramDesc" />&nbsp;</td>
																			<logic:equal name="cscProgRefForm" property="referralProgramLocBean.contractProgramDesc"  value="No">
																				<script> show("contratPgmTracerNum", 0) </script>
																			</logic:equal>
																	</tr>
																	<tr>
																		<td colspan="2"><div><bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.streetNumber" />
		                                                                                      <bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.streetName" />
		                                                                                      <bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.streetTypeCd" />
		                                                                                      <bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.aptNum" /></div>
		                                                                                 <div><bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.city" />,
		                                                                                      <bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.state" />
		                                                                                      <bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.zipCode" /></div>
		                                                                   </td>
																		<td nowrap colspan="6">
																			<div>
																				<logic:notEqual name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.locationPhone.formattedPhoneNumber" value="">
																					<bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.locationPhone.formattedPhoneNumber" />&nbsp;ph<br>
																				</logic:notEqual>
																				<logic:notEqual name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.locationFax.formattedPhoneNumber" value="">
																					<bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.locationFax.formattedPhoneNumber" />&nbsp;f
																				</logic:notEqual>
																				&nbsp;
																			</div>
																		</td>
																	</tr>
																	<tr>
																		<td colspan="8">
																			<table width="100%" cellpadding="2" cellspacing="1">
																				<tr>
																					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.scheduledDate"/></td>
																					<td class="formDe">
																						<bean:write name="cscProgRefForm" property="scheduledDateAsStr" formatKey="date.format.mmddyyyy"/>
																					</td>
																					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.time"/></td>
																					<td class="formDe">
																						<bean:write name="cscProgRefForm" property="scheduledTime" formatKey="time.format.hhmma"/>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																	<tr id="commentsLabelRow">
																		<td colspan="8" class="formDeLabel">
																			<bean:message key="prompt.comments"/>
																		</td>
																	</tr>
																	<tr id="submitCommentsRow">
																		<td colspan="8" class="borderTableGrey">
																			<bean:write name="cscProgRefForm" property="submitComments" filter="false" />
																		</td>
																	</tr>
																	<tr id="exitCommentsRow">
																		<td colspan="8" class="borderTableGrey">
																			<bean:write name="cscProgRefForm" property="exitComments" filter="false" />
																		</td>
																	</tr>
																</table>
																<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_SUBMITREFERRAL%>">
																	<script> show("exitCommentsRow",0) </script>  
																</logic:equal>
																<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_EXITREFERRAL%>">
																		<script> show("submitCommentsRow",0) </script>  
																</logic:equal>
																<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_UPDATE_SUBMIT%>">
																		<script> show("commentsLabelRow",0) </script> 
																		<script> show("submitCommentsRow",0) </script>
																		<script> show("exitCommentsRow",0) </script>  
																</logic:equal>
																<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_UPDATE_EXIT%>">
																		<script> show("commentsLabelRow",0) </script> 
																		<script> show("submitCommentsRow",0) </script> 
																		<script> show("exitCommentsRow",0) </script> 
																</logic:equal>
																<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_REREFERRAL%>">
																		<script> show("exitCommentsRow",0) </script>  
																</logic:equal>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
										<!-- BEGIN BUTTON TABLE -->
										<table border="0" cellpadding="2" cellspacing="1">
											<tr>
												<td align="center">
													<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
												    <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish" /></html:submit>
												    <html:button property="submitAction" styleId="printApptButton" styleClass="hidden" onclick="printAppointment();" > <bean:message key="button.printAppt" /></html:button>
													<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
												</td>
											</tr>
										</table>
										<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_REREFERRAL%>">
												<script> show("printApptButton", 1, "inline") </script>
										</logic:equal>
										<!-- END BUTTON TABLE -->
									</td>
								</tr>
							</table>
							<div class="spacer4px"></div>
						</td>
					</tr>
				</table>
				<br>
			</td>
		</tr>
	</table>
	<br>
	<!--casefile tabs end-->
<!-- END  TABLE -->
</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
