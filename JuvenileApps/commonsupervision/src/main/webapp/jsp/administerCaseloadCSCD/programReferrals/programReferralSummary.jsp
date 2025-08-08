<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/29/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 06/12/2009 C. Shimek         - #60188 add caution message table -->
<!-- 07/01/2011 R Young - #68612 Referral Date - make this an Entry/Update field-UI -->
<!-- 07/11/2012 R Capestani - #J73843  Print Fax Number on Program Referral -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<%@ page import="naming.CSAdministerProgramReferralsConstants"%>

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
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/programReferralSummary.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/submitProgRefInitiateUpdate" target="content">
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
								<td bgcolor="#cccccc" colspan="2">
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
												<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_UPDATE_INIT%>">
													<bean:message key="title.update"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|12">
												</logic:equal>
												<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_CREATE%>">
													<bean:message key="prompt.initiate"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|6">
												</logic:equal> 
												<bean:message key="prompt.programReferral"/>&nbsp;<bean:message key="prompt.summary"/></td>
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
									<!-- BEGIN CAUTION MESSAGE TABLE -->
									<logic:notEqual name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_UPDATE_INIT%>">
										<table width="98%" align="center">
											<tr>
												<td align="center">
													<logic:present name="cautionMsg2" >
														<div>
															<span class="cautionText" style='font-weight:bold; width:98%'><bean:write name="cautionMsg2" /></span>
														</div>
													</logic:present>
												</td>
											</tr>
										</table>	
									</logic:notEqual>							
									<!-- END CAUTION MESSAGE TABLE -->		
									<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center">
										<tr>
											<td>
												<ul>
													<li>Verify that information is correct and select Finish button. If any changes are needed, select Back button.</li>
											    </ul>
											</td>
										</tr>
										<tr>
											<td class="required"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
										</tr>
									</table>
									
									<!--Conditions List start-->
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td colspan="2">
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
									<table width="98%" cellpadding="0" cellspacing="0" border="0">
										<tr>
											<td width="100%" valign="top">
												<table width="100%" cellpadding="2" cellspacing="0" class="borderTableBlue">
													<tr class="detailHead">
														<td><bean:message key="prompt.programReferral"/>&nbsp;<bean:message key="prompt.info"/></td>
													</tr>
													<tr>
														<td>
															<table width="100%" cellpadding="4" cellspacing="1">
																<tr>
																	<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.referralTypes"/></td>
																	<td colspan="3" class="formDe">
																		<div>
                                                                           <logic:iterate id="referralsIndex" name="cscProgRefForm" property="selectedReferralTypesList">
                                                                           	  <bean:write name="referralsIndex" property="referralTypeNum" />.&nbsp;
																			  <bean:write name="referralsIndex" property="referralTypeDesc" />
																			  <logic:equal name="referralsIndex" property="notProgressedForPgmLoc" value="true">
																			  	<img src="/<msp:webapp/>images/circledXicon.png" title="Referral Type Not Progressed" border="0" hspace="4">
																			  </logic:equal>
																			  </br>			
                                                                           </logic:iterate>    
																		</div>
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.referralDate"/></td>
																	<td colspan="3" class="formDe">
																		<bean:write name="cscProgRefForm" property="referralDateAsStr" />
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<div class="spacer4px"></div>
									<table width="98%" cellpadding="0" cellspacing="0" border="0">
										<tr>
											<td width="100%" valign="top">
												<table width="100%" cellpadding="2" cellspacing="0" class="borderTableBlue">
													<tr class="detailHead">
														<td><bean:message key="prompt.selectedPrograms"/></td>
													</tr>
													<tr>
														<td>
							                        <logic:iterate id="spProgramLocIndex" name="cscProgRefForm" property="selectedServiceProviderProgLocList">
															<table width="100%" cellpadding="2" cellspacing="1">
																<tr class="formDeLabel">
																	<td><bean:message key="prompt.serviceProvider" /></td>
																	<td><bean:message key="prompt.referralType" /></td>
																	<td><bean:message key="prompt.identifier" /></td>
																	<td><bean:message key="prompt.name" /></td>
																	<td width="5%"><bean:message key="prompt.CSTSCode" /></td>
																	<td width="10%"><bean:message key="prompt.languagesOffered" /></td>
																	<td><bean:message key="prompt.sexSpecific" /></td>
																	<td><bean:message key="prompt.contractProgram" /></td>
																</tr>
																<tr class="programRow">
																	<td class="bottomBorder"><a href="javascript:openWindow('/<msp:webapp/>displayCSCServiceProviderUpdate.do?submitAction=View Details&selectedValue=<bean:write name="spProgramLocIndex" property="serviceProviderId" />')"><bean:write name="spProgramLocIndex" property="serviceProviderName" /></a></td>
																	<td class="bottomBorder"><bean:write name="spProgramLocIndex" property="referralTypeDesc" /></td>
																	<td class="bottomBorder"><a href="javascript: openWindow('/<msp:webapp/>displayCSCProgramUpdate.do?submitAction=View Details&selectedValue=<bean:write name="spProgramLocIndex" property="programId"/>')"><bean:write name="spProgramLocIndex" property="programIdentifier" /></a>&nbsp;</td>
																	<td class="bottomBorder"><bean:write name="spProgramLocIndex" property="programName" />&nbsp;</td>
																	<td class="bottomBorder"><bean:write name="spProgramLocIndex" property="cstsCode" />&nbsp;</td>
																	<td class="bottomBorder">
																		<logic:iterate id="eachLanguage" name="spProgramLocIndex" property="languagesOffered" >
																			<bean:write name="eachLanguage" />
																		</logic:iterate>
																		&nbsp;
																	</td>
																	<td class="bottomBorder"><bean:write name="spProgramLocIndex" property="sexSpecificDesc" />&nbsp;</td>
																	<td class="bottomBorder"><bean:write name="spProgramLocIndex" property="contractProgramDesc" />&nbsp;</td>
																</tr>
															 <logic:iterate id="locationIndex" name="spProgramLocIndex" property="programLocationsList">
																<tr>
																	<td colspan="2"><div><bean:write name="locationIndex" property="streetNumber" />
                                                                                       <bean:write name="locationIndex" property="streetName" />
                                                                                       <bean:write name="locationIndex" property="streetTypeCd" />
                                                                                       <logic:notEqual name="locationIndex" property="aptNum" value="">
                                                                                       	Apt/Suite <bean:write name="locationIndex" property="aptNum" />
                                                                                       </logic:notEqual>
                                                                                       </div>
                                                                                  <div><bean:write name="locationIndex" property="city" />
                                                                                       <bean:write name="locationIndex" property="state" />
                                                                                       <bean:write name="locationIndex" property="zipCode" /></div></td>
																	<td nowrap colspan="6">
																		<div>
																			<logic:notEqual name="locationIndex" property="locationPhone.formattedPhoneNumber" value="">
																				<bean:write name="locationIndex" property="locationPhone.formattedPhoneNumber" />&nbsp;Ph
																			</logic:notEqual>
																			<logic:notEqual name="locationIndex" property="locationFax.formattedPhoneNumber" value="">
																				<br><bean:write name="locationIndex" property="locationFax.formattedPhoneNumber" />&nbsp;f
																			</logic:notEqual>
																			&nbsp;
																		</div>
																	</td>
																</tr>
																</logic:iterate>
																<logic:equal name="cscProgRefForm" property="scheduleDateTimeSelected" value="true">
																	<tr>
																		<td colspan="8">
																			<table width="100%" cellpadding="1" cellspacing="1">
																				<tr>
																					<td class="formDeLabel" width="5%" nowrap><bean:message key="prompt.scheduledDate" /></td>
																					<td class="formDe" width="45%">
																						<bean:write name="spProgramLocIndex" property="scheduleDateAsStr" />
																					</td>
																					<td class="formDeLabel" width="5%" nowrap><bean:message key="prompt.time" /></td>
																					<td class="formDe" width="45%">
																						<bean:write name="spProgramLocIndex" property="scheduleTimeDesc"/>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																</logic:equal>
																<tr bgcolor="#999999">
																	<td colspan="8"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
																</tr>
															</table>
                                                        </logic:iterate>   
														</td>
													</tr>
													
													<logic:equal name="cscProgRefForm" property="userEnteredServiceProvider" value="true" >
														<tr>
															<td>
																<table width="100%" cellpadding="2" cellspacing="1">
																	<tr class="formDeLabel">
																		<td><bean:message key="prompt.serviceProvider" /></td>
																		<td><bean:message key="prompt.referralType" /></td>
																		<td><bean:message key="prompt.identifier" /></td>
																		<td><bean:message key="prompt.name" /></td>
																		<td><bean:message key="prompt.CSTSCode" /></td>
																		<td><bean:message key="prompt.languagesOffered" /></td>
																		<td><bean:message key="prompt.sexSpecific" /></td>
																		<td><bean:message key="prompt.contractProgram" /></td>
																	</tr>
																	<tr class="programRow">
																		<td class="bottomBorder"><bean:write name="cscProgRefForm" property="userEnteredServiceProviderName" /></td>
																		<td class="bottomBorder"><bean:write name="cscProgRefForm" property="userEnteredServiceProviderRefTypeDesc" /></td>
																		<td class="bottomBorder">&nbsp;</td>
																		<td class="bottomBorder">&nbsp;</td>
																		<td class="bottomBorder">&nbsp;</td>
																		<td class="bottomBorder">&nbsp;</td>
																		<td class="bottomBorder">&nbsp;</td>
																		<td class="bottomBorder">&nbsp;</td>
																	</tr>
																	<tr>
																		<td colspan="2">&nbsp;</td>
																		<td nowrap colspan="6">
																			<div>
																				<logic:notEqual name="cscProgRefForm" property="userEnteredSPPhone.formattedPhoneNumber" value="">
																					<bean:write name="cscProgRefForm" property="userEnteredSPPhone.formattedPhoneNumber" />&nbsp;ph
																				</logic:notEqual>
																			</div>
																			<div>
																				<logic:notEqual name="cscProgRefForm" property="userEnteredSPFax.formattedPhoneNumber" value="">
																					<bean:write name="cscProgRefForm" property="userEnteredSPFax.formattedPhoneNumber" />&nbsp;f
																				</logic:notEqual>
																			</div>
																		</td>
																	</tr>
																	<logic:equal name="cscProgRefForm" property="scheduleDateTimeSelected" value="true">
																		<tr>
																			<td colspan="8">
																				<table width="100%" cellpadding="1" cellspacing="1">
																					<tr>
																						<td class="formDeLabel" width="5%" nowrap><bean:message key="prompt.scheduledDate" /></td>
																						<td class="formDe" width="45%">
																							<bean:write name="cscProgRefForm" property="userEnteredScheduledDateAsStr" />
																						</td>
																						<td class="formDeLabel" width="5%" nowrap><bean:message key="prompt.time" /></td>
																						<td class="formDe" width="45%">
																							<bean:write name="cscProgRefForm" property="userEnteredScheduledTime"/>
																						</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																	</logic:equal>
																	<tr bgcolor="#999999">
																		<td colspan="8"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
																	</tr>
																</table>
															</td>
														</tr>
													</logic:equal>
													
												</table>
											</td>
										</tr>
									</table>
									<div id="legendArea"><img src="/<msp:webapp/>images/circledXicon.png" title="Referral Type Not Progressed" border="0" hspace="4"> referral type not progressed </div>
									<!-- BEGIN BUTTON TABLE -->
									<table border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td align="center">
												<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"> <bean:message key="button.finish" /></html:submit>
												<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
											</td>
										</tr>
									</table>
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
