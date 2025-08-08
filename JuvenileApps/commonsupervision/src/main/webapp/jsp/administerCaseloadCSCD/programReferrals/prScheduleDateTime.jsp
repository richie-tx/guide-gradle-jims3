<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/28/2008 Debbie Williamson - Converted PT to JSP -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<%@ page import="naming.PDCodeTableConstants" %>
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
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/prScheduleDateTime.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<script>
	function validateFields(theForm)
	{
		clearAllValArrays();

		var selPrgmsSize = (document.getElementsByName('selProgramsSize'))[0].value;
		for(var index=0; index < selPrgmsSize; index++)
		{
			var elementId = "scheduleDateStr" + index;
			addMMDDYYYYDateValidation(elementId,"<bean:message key='errors.date' arg0='Scheduled Date'/>","");
		}

		if(document.getElementById('userEnteredScheduledDateAsStrId') != null)
		{
			addMMDDYYYYDateValidation("userEnteredScheduledDateAsStr","<bean:message key='errors.date' arg0='Scheduled Date'/>","");
		}
	
		var result = validateCustomStrutsBasedJS(theForm);
		if (result == true){
	 
			var refdt = theForm.refDateStr.value + " " + "00:00";
			var refDateTime = new Date(refdt);
			for(var x=0; x < selPrgmsSize; x++)
			{
				var elementId = "scheduleDateStr" + x;			
				var schdt = document.getElementById(elementId).value + " " + "00:00";
				var schDateTime = new Date(schdt);
		    	if ( schDateTime < refDateTime ){
			    	alert("Schedule Date can not be before the Referral Date.");
			    	document.getElementById(elementId).focus();
			    	result = false;
		    	}
			}
		}
		 return result; 
	}
</script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displayProgRefScheduleDateTime"  target="content">
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
													<bean:message key="title.update"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|21">
												</logic:equal>
												<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_CREATE%>">
													<bean:message key="prompt.initiate"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|7">
												</logic:equal> 
												<bean:message key="title.programReferral"/>&nbsp;-&nbsp;<bean:message key="prompt.schedule"/>&nbsp;<bean:message key="prompt.dateTime"/>&nbsp;</td>
									    </tr>
								    </table>
								    <!-- END HEADING TABLE -->
									<%-- BEGIN ERROR TABLE --%>
									<table width="98%" align="center">
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>
									</table>	
									<logic:notEqual name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_UPDATE_INIT%>">
										<table width="98%" align="center">
											<tr>
												<td align="center">
													<logic:present name="cautionMsg" >
														<div>
															<span class="cautionText" style='font-weight:bold;'><bean:write name="cautionMsg" /></span>
														</div>
													</logic:present>
												</td>
											</tr>
										</table>	
									</logic:notEqual>								
									<!-- END ERROR TABLE -->
									<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center">
										<tr>
											<td><ul>
												<li>Enter the required information, and select Next.</li>
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
									<table width="98%" cellpadding="0" cellspacing="0" border="0">
										<tr>
											<td width="100%" valign="top">
												<table width="100%" cellpadding="2" cellspacing="0" class="borderTableBlue">
													<tr class="detailHead">
														<td><bean:message key="prompt.programReferral"/>&nbsp;<bean:message key="prompt.info"/></td>
													</tr>
													<tr>
														<td>
															<% int ReferralTypeCounter=0; %>
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
																		<input type="hidden" name="refDateStr" value=<bean:write name="cscProgRefForm" property="referralDateAsStr" /> >
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
															<table width="100%" cellpadding="2" cellspacing="1">
															<% int RecordCounter2=0;
															   String bgcolor2=""; %>
															 <nested:iterate id="spProgramLocIndex" name="cscProgRefForm" property="selectedServiceProviderProgLocList" indexId="prgLocIndexId">
															 	<% RecordCounter2++; %>
															 	<tr class="formDeLabel">
																	<td><bean:message key="prompt.serviceProvider"/></td>
																	<td><bean:message key="prompt.referralType"/></td>
																	<td><bean:message key="prompt.identifier"/></td>
																	<td><bean:message key="prompt.name"/></td>
																	<td width="5%"><bean:message key="prompt.CSTSCode" /></td>
																	<td width="5%"><bean:message key="prompt.languagesOffered"/></td>
																	<td width="5%"><bean:message key="prompt.sexSpecific"/></td>
																	<td width="5%"><bean:message key="prompt.contractProgram"/></td>
																</tr>
																<tr class="programRow">
																	<td class="bottomBorder"><a href="javascript:openWindow('/<msp:webapp/>displayCSCServiceProviderUpdate.do?submitAction=View Details&selectedValue=<bean:write name="spProgramLocIndex" property="serviceProviderId" />')"><bean:write name="spProgramLocIndex" property="serviceProviderName" /></a></td>
																	<td class="bottomBorder"><bean:write name="spProgramLocIndex" property="referralTypeDesc" /></td>
                                                                    <td class="bottomBorder"><a href="javascript: openWindow('/<msp:webapp/>displayCSCProgramUpdate.do?submitAction=View Details&selectedValue=<bean:write name="spProgramLocIndex" property="programId"/>')"><bean:write name="spProgramLocIndex" property="programIdentifier" /></a></td>
																	<td class="bottomBorder"><bean:write name="spProgramLocIndex" property="programName" />&nbsp;</td>
																	<td class="bottomBorder"><bean:write name="spProgramLocIndex" property="cstsCode" />&nbsp;</td>
																	<td class="bottomBorder">
																	<logic:iterate id="eachLanguage" name="spProgramLocIndex" property="languagesOffered" >
																		<bean:write name="eachLanguage" />
																	</logic:iterate>
																	&nbsp;</td>
																	<td class="bottomBorder"><bean:write name="spProgramLocIndex" property="sexSpecificDesc" />&nbsp;</td>
																	<td class="bottomBorder"><bean:write name="spProgramLocIndex" property="contractProgramDesc" />&nbsp;</td>
																</tr>
																<!-- There will be only one location in programLocationsList per SP, during scheduling date/time -->
																 <logic:iterate id="locationIndex" name="spProgramLocIndex" property="programLocationsList">
																	<tr>
																		<td colspan="2"><div><bean:write name="locationIndex" property="streetNumber" />
	                                                                                       <bean:write name="locationIndex" property="streetName" />
	                                                                                       <bean:write name="locationIndex" property="streetTypeCd" />
	                                                                                       <bean:write name="locationIndex" property="aptNum" /></div>
	                                                                                  <div><bean:write name="locationIndex" property="city" />
	                                                                                       <bean:write name="locationIndex" property="state" />
	                                                                                       <bean:write name="locationIndex" property="zipCode" /></div>
	                                                                    </td>
																		<td nowrap colspan="6">
																			<div>
																				<logic:notEqual name="locationIndex" property="locationPhone.formattedPhoneNumber" value="">
																					<bean:write name="locationIndex" property="locationPhone.formattedPhoneNumber" />&nbsp;Ph
																				</logic:notEqual>
																				&nbsp;
																			</div>
																		</td>
																	</tr>
																</logic:iterate>
																<tr>
																	<td colspan="8">
																		<table width="100%" cellpadding="1" cellspacing="1">
																			<tr>
																				<td class="formDeLabel" width="5%" nowrap><bean:message key="prompt.scheduledDate"/></td>
																				<td class="formDe" width="45%">
																					<SCRIPT LANGUAGE="JavaScript" ID="js1">
																						var cal1 = new CalendarPopup();
																						cal1.showYearNavigation();
																					</SCRIPT>
																					<logic:equal name="spProgramLocIndex" property="scheduleDateAsStr" value="">
																						<input type="text" name="selectedServiceProviderProgLocList[<%out.print(prgLocIndexId);%>].scheduleDateAsStr" size="10" maxlength="10"  title="(mm/dd/yyyy)" id="scheduleDateStr<%out.print(prgLocIndexId);%>">
																					</logic:equal>
																					<logic:notEqual name="spProgramLocIndex" property="scheduleDateAsStr" value="">
																						<input type="text" name="selectedServiceProviderProgLocList[<%out.print(prgLocIndexId);%>].scheduleDateAsStr" size="10" maxlength="10"  title="(mm/dd/yyyy)" id="scheduleDateStr<%out.print(prgLocIndexId);%>" value="<bean:write name="spProgramLocIndex" property="scheduleDateAsStr" />">
																					</logic:notEqual>
																					<A HREF="#" onClick="cal1.select(document.getElementById('scheduleDateStr<%out.print(prgLocIndexId);%>'),'anchor<%out.print(prgLocIndexId);%>','MM/dd/yyyy'); return false;" NAME="anchor<%out.print(prgLocIndexId);%>" ID="anchor<%out.print(prgLocIndexId);%>"><bean:message key="prompt.3.calendar"/></A> 
																				</td>
																				<td class="formDeLabel" width="5%" nowrap><bean:message key="prompt.time"/></td>
																				<td class="formDe" width="45%">
																					<nested:select property="scheduleTimeDesc">
																						<option value="">Please Select</option>
																						<jims2:codetable codeTableName="<%=PDCodeTableConstants.WORKDAY%>" sort="true" sortCode="true" value="description"/>
																					</nested:select>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr bgcolor="#999999">
																	<td colspan="8"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
																</tr>
                                                              </nested:iterate>   
                                                              
                                                              <input type="hidden" name="selProgramsSize" value="<%=RecordCounter2 %>" >
                                                              
                                                             <logic:equal name="cscProgRefForm" property="userEnteredServiceProvider" value="true" >
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
																	<td colspan="2"></td>
																	<td nowrap colspan="6">
																		<div>
																			<logic:notEqual name="cscProgRefForm" property="userEnteredSPPhone.formattedPhoneNumber" value="">
																				<bean:write name="cscProgRefForm" property="userEnteredSPPhone.formattedPhoneNumber" />&nbsp;ph
																			</logic:notEqual>
																			&nbsp;
																		</div>
																		<div>
																			<logic:notEqual name="cscProgRefForm" property="userEnteredSPFax.formattedPhoneNumber" value="">
																				<bean:write name="cscProgRefForm" property="userEnteredSPFax.formattedPhoneNumber" />&nbsp;f
																			</logic:notEqual>
																			&nbsp;
																		</div>
																	</td>
																</tr>
																<tr>
																	<td colspan=8>
																		<table width="100%" cellpadding="1" cellspacing="1">
																			<tr>
																				<td class="formDeLabel" width="5%" nowrap><bean:message key="prompt.scheduledDate"/>xx</td>
																				<td class="formDe" width="45%">
																					<SCRIPT LANGUAGE="JavaScript" ID="js1">
																						var cal1 = new CalendarPopup();
																						cal1.showYearNavigation();
																					</SCRIPT>
																					<html:text name="cscProgRefForm" property="userEnteredScheduledDateAsStr" size="10" maxlength="10"  title="(mm/dd/yyyy)" styleId="userEnteredScheduledDateAsStrId"></html:text>
																					<A HREF="#" onClick="cal1.select(cscProgRefForm.userEnteredScheduledDateAsStr,'anchor100','MM/dd/yyyy'); return false;" NAME="anchor100" ID="anchor100"><bean:message key="prompt.3.calendar"/></A> 
																				</td>
																				<td class="formDeLabel" width="5%" nowrap><bean:message key="prompt.time"/></td>
																				<td class="formDe" width="45%">
																					<html:select name="cscProgRefForm" property="userEnteredScheduledTime">
																						<option value="">Please Select</option>
																						<jims2:codetable codeTableName="<%=PDCodeTableConstants.WORKDAY%>" sort="true" sortCode="true" value="description"/>
																					</html:select>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr bgcolor="#999999">
																	<td colspan="8"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
																</tr>
                                                             </logic:equal> 
                                                             
                                                             
                                                             
															</table>
														</td>
													</tr>
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
												<html:submit property="submitAction" onclick="return validateFields(this.form);" > <bean:message key="button.next" /></html:submit>
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
