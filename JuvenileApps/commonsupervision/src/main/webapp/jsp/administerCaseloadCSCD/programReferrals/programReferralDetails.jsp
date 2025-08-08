<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/29/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 06/12/2009 C Shimek          - #60190 removed instructions when page used as View -->
<!-- 08/11/2010 C Shimek          - #66250 added feature check to Casenote Create button -->
<!-- 07/12/2011 R Young      - #68612 added code needed to make referral date and entry field -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.CSAdministerProgramReferralsConstants" %>

<%@ page import="naming.Features" %>

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
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/programReferralDetails.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>

<script>

	function showViewInitateRefButtons()
	{
		var serviceProviderId = document.getElementsByName("serviceProviderId")[0].value;
		
		show('buttonsRow0', 1, 'inline');
		show('buttonsRow1', 1, 'inline');
		show('buttonsRow2', 1, 'inline');

		if(document.getElementById('deleteButton') != null)
		{
			show('deleteButton', 1, 'row');
		}
		if(document.getElementById('submitButton') != null)
		{
			show('submitButton', 0, 'row');	
		}
		if(serviceProviderId!=null && (serviceProviderId !=""))
		{
			if(document.getElementById('submitButton') != null)
			{
				show('submitButton', 1, 'row');	
			}
		}
		if(document.getElementById('rereferButton') != null)
		{
			show('rereferButton', 0, 'row');
		}
		if(document.getElementById('removeEntryButton') != null)
		{
			show('removeEntryButton', 0, 'row');
		}
		if(document.getElementById('removeExitButton') != null)
		{
			show('removeExitButton', 0, 'row');
		}
		if(document.getElementById('exitButton') != null)
		{
			show('exitButton', 1, 'row');
		}
		show('formButton', 1, 'row');
		show('printApptButton', 1, 'row');
		show('finishButton', 0, 'row');
	}

	function showViewOpenRefButtons()
	{
		show('buttonsRow0', 1, 'inline');
		show('buttonsRow1', 1, 'inline');
		show('buttonsRow2', 1, 'inline');

		if(document.getElementById('deleteButton') != null)
		{
			show('deleteButton', 0, 'row');
		}
		if(document.getElementById('submitButton') != null)
		{
			show('submitButton', 0, 'row');	
		}
		if(document.getElementById('rereferButton') != null)
		{
			show('rereferButton', 1, 'row');
		}
		if(document.getElementById('removeEntryButton') != null)
		{
			show('removeEntryButton', 1, 'row');
		}
		if(document.getElementById('removeExitButton') != null)
		{
			show('removeExitButton', 0, 'row');
		}
		if(document.getElementById('exitButton') != null)
		{
			show('exitButton', 1, 'row');
		}
		show('formButton', 1, 'row');
		show('printApptButton', 1, 'row');
		show('finishButton', 0, 'row');
	}

	
	function showViewExitRefButtons()
	{
		show('buttonsRow0', 1, 'inline');
		show('buttonsRow1', 1, 'inline');
		show('buttonsRow2', 1, 'inline');

		if(document.getElementById('deleteButton') != null)
		{
			show('deleteButton', 0, 'row');
		}
		if(document.getElementById('submitButton') != null)
		{
			show('submitButton', 0, 'row');	
		}
		if(document.getElementById('rereferButton') != null)
		{
			show('rereferButton', 0, 'row');
		}
		if(document.getElementById('removeEntryButton') != null)
		{
			show('removeEntryButton', 0, 'row');
		}
		if(document.getElementById('removeExitButton') != null)
		{
			show('removeExitButton', 1, 'row');
		}
		if(document.getElementById('exitButton') != null)
		{
			show('exitButton', 0, 'row');
		}
		show('formButton', 1, 'row');
		show('printApptButton', 1, 'row');
		show('finishButton', 0, 'row');
	}

	function showFinishButton()
	{
		show("buttonsRow0", 0);
		show("buttonsRow1", 0);
		show("buttonsRow2", 1);

		show('finishButton', 1, 'row');
	}

	function printAppointment()
	{
		var selectedProgRefId = document.getElementsByName('progRefId')[0].value;
		var url = "displayProgRefAppointment.do?submitAction=Link&selectedValue=" + selectedProgRefId;
		openWindow(url);
	}

	function viewCasenotes()
	{
		var selectedProgRefId = document.getElementsByName('progRefId')[0].value;
		var url = "displayProgRefCasenote.do?submitAction=View&selectedValue=" + selectedProgRefId;
		openWindow(url);
	}
	
</script>	

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/handleProgRefDetails" target="content">
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
									<div class="header"><bean:message key="title.CSCD"/>&nbsp;-
										<logic:equal name="cscProgRefForm" property="action" value="<%=UIConstants.DELETE%>">
											<bean:message key="prompt.delete"/>
                                            <bean:message key="title.programReferral"/>
                                            <bean:message key="title.summary"/>
                                            <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|14">
										</logic:equal>
										<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_REMOVEENTRY%>">
											<bean:message key="title.programReferral"/>&nbsp;-
                                            <bean:message key="prompt.removeEntry"/>
                                            <bean:message key="title.summary"/>
                                            <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|28">
										</logic:equal>
										<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_REMOVEEXIT%>">
											<bean:message key="title.programReferral"/>&nbsp;-
                                            <bean:message key="prompt.removeExit"/>
                                            <bean:message key="title.summary"/>
                                            <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|29">
										</logic:equal>
										<logic:equal name="cscProgRefForm" property="action" value="<%=UIConstants.VIEW%>">
											<bean:message key="title.programReferral"/>
                                            <bean:message key="title.details"/>
                                            <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|13">
										</logic:equal>
									</div>
									<%-- BEGIN ERROR TABLE --%>
									<table width="98%" align="center">
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>
									</table>								
									<!-- END ERROR TABLE -->
									<div class="spacer"></div>
									<logic:equal name="cscProgRefForm" property="action" value="<%=UIConstants.DELETE%>">
										<div><span class="cautionText" style='padding:2px; font-weight:bold;'>Casenotes may exist associated to this program referral that need to be deleted.</span></div>
									</logic:equal>    
									<div class="instructions">
										<ul>
											<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_DELETE%>">
												<li>Click Finish to delete Program Referral.</li>
											</logic:equal> 
											<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_REMOVEENTRY%>">
												<li>Click Finish to remove entry on this Program Referral.</li>
											</logic:equal>
											<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_REMOVEEXIT%>">
												<li>Click Finish to remove exit on this Program Referral.</li>
											</logic:equal>
										</ul>
									</div>
									<!-- END HEADING TABLE -->
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
									<html:hidden name="cscProgRefForm" property="serviceProviderId" />
									<html:hidden name="cscProgRefForm" property="progRefId" />
									<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
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
																			<bean:write name="cscProgRefForm" property="programEndDateAsStr" />
																		</td>
																	</tr>
																	<tr>
																		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.reasonForDischarge"/></td>
																		<td class="formDe" colspan="3">
																			<bean:write name="cscProgRefForm" property="reasonForDischargeDesc" />
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
																<tr>
																	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.contractProgram"/></td>
																	<td class="formDe" width="50%">
																		<logic:notEqual name="cscProgRefForm" property="programBeginDateAsStr" value="" >
																			<logic:equal name="cscProgRefForm" property="contractProgram" value="true" >
																				Yes
																			</logic:equal>
																			<logic:equal name="cscProgRefForm" property="contractProgram" value="false" >
																				No
																			</logic:equal>
																		</logic:notEqual>
																	</td>
																	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.tracer#"/></td>
																	<td class="formDe">
																		<bean:write name="cscProgRefForm" property="tracerNum" />
																	</td>
																</tr>
																<tr>
																	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.reasonForPlacement"/></td>
																	<td class="formDe" colspan="3">
																		<bean:write name="cscProgRefForm" property="reasonForPlacementDesc" />
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
														<td width="10%"><bean:message key="prompt.languagesOffered"/></td>
														<td ><bean:message key="prompt.sexSpecific"/></td>
														<td ><bean:message key="prompt.contractProgram"/></td>
													</tr>
													<tr class="programRow">
														<td class="bottomBorder">
															<logic:equal name="cscProgRefForm" property="userEnteredServiceProvider" value="true">
																<bean:write name="cscProgRefForm" property="referralProgramLocBean.serviceProviderName" />&nbsp;
															</logic:equal>
															<logic:notEqual name="cscProgRefForm" property="userEnteredServiceProvider" value="true">
																<a href="javascript:openWindow('/<msp:webapp/>displayCSCServiceProviderUpdate.do?submitAction=View Details&selectedValue=<bean:write name="cscProgRefForm" property="referralProgramLocBean.serviceProviderId" />')"><bean:write name="cscProgRefForm" property="referralProgramLocBean.serviceProviderName" /></a>&nbsp;
															</logic:notEqual>
														</td>
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
														</tr>
															<tr>
																<td colspan="2"><div>
																	<bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.streetNumber" />
                                                                    <bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.streetName" />
                                                                    <bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.streetTypeCd" />
                                                                    <bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.aptNum" /></div>
                                                               <div><bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.city" />
                                                                    <bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.state" />
                                                                    <bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.zipCode" /></div>
                                                                   </td>
																<td nowrap colspan="6">
																	<div>
																		<logic:notEqual name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.locationPhone.formattedPhoneNumber" value="">
																			<bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.locationPhone.formattedPhoneNumber" />&nbsp;Ph<br>
																		</logic:notEqual>
																		<logic:notEqual name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.locationFax.formattedPhoneNumber" value="">
																			<bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.locationFax.formattedPhoneNumber" />&nbsp;F
																		</logic:notEqual>
																		&nbsp;
																	</div>
																</td>
															</tr>
															<tr>
																<td colspan="8">
																	<table width="100%" cellpadding="1" cellspacing="1">
																		<tr>
																			<td class="formDeLabel" width="5%" nowrap><bean:message key="prompt.scheduledDate" /></td>
																			<td class="formDe" width="45%">
																				<bean:write name="cscProgRefForm" property="referralProgramLocBean.scheduleDateAsStr" />
																			</td>
																			<td class="formDeLabel" width="5%" nowrap><bean:message key="prompt.time" /></td>
																			<td class="formDe" width="45%">
																				<bean:write name="cscProgRefForm" property="referralProgramLocBean.scheduleTimeDesc"/>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
												<!-- BEGIN BUTTON TABLE -->
												<table border="0" cellpadding="2" cellspacing="1">
													<tr id="buttonsRow0" class="hidden">
														<td align="center">
															<jims2:isAllowed requiredFeatures='<%=Features.CSCD_PRG_REF_UPDATE%>'>	
																<html:submit property="submitAction" > <bean:message key="button.update" /></html:submit>
															</jims2:isAllowed>	
															<jims2:isAllowed requiredFeatures='<%=Features.CSCD_PRG_REF_DELETE%>'>
																<html:submit property="submitAction" styleId="deleteButton" styleClass="hidden"> <bean:message key="button.delete" /></html:submit>
															</jims2:isAllowed>	
															<jims2:isAllowed requiredFeatures='<%=Features.CSCD_PRG_REF_SUBMIT%>'>
																<html:submit property="submitAction" styleId="submitButton" styleClass="hidden"> <bean:message key="button.submitReferral" /></html:submit>
															</jims2:isAllowed>	
															<jims2:isAllowed requiredFeatures='<%=Features.CSCD_PRG_REF_REREFER%>'>
																<html:submit property="submitAction" styleId="rereferButton" styleClass="hidden"> <bean:message key="button.reReferral" /></html:submit>
															</jims2:isAllowed>	
															<jims2:isAllowed requiredFeatures='<%=Features.CSCD_PRG_REF_REM_EXT_ENT_WITH_RESTRC%>'>
																<logic:equal name="cscProgRefForm" property="sentToState" value="false" >
																	<html:submit property="submitAction" styleId="removeEntryButton" styleClass="hidden"> <bean:message key="button.removeEntry" /></html:submit>
																	<html:submit property="submitAction" styleId="removeExitButton" styleClass="hidden"> <bean:message key="button.removeExit" /></html:submit>
																</logic:equal>
															</jims2:isAllowed>	
															<jims2:isAllowed requiredFeatures='<%=Features.CSCD_PRG_REF_REM_EXT_ENT_WITHOUT_RESTRC%>'>
																<html:submit property="submitAction" styleId="removeEntryButton" styleClass="hidden"> <bean:message key="button.removeEntry" /></html:submit>
																<html:submit property="submitAction" styleId="removeExitButton" styleClass="hidden"> <bean:message key="button.removeExit" /></html:submit>
															</jims2:isAllowed>
															<jims2:isAllowed requiredFeatures='<%=Features.CSCD_PRG_REF_EXIT%>'>
																<html:submit property="submitAction" styleId="exitButton" styleClass="hidden"> <bean:message key="button.exitReferral" /></html:submit>
															</jims2:isAllowed>	
															<html:submit property="submitAction" styleId="formButton" styleClass="hidden"> <bean:message key="button.generateForm" /></html:submit>
														</td>
													</tr>
													<tr id="buttonsRow1" class="hidden">
														<td align="center">
															<jims2:isAllowed requiredFeatures='<%=Features.CS_CASENOTE_CREATE%>'>
																<html:submit property="submitAction"> <bean:message key="button.createCasenote" /></html:submit>
															</jims2:isAllowed>	
															<html:button property="submitAction" onclick="viewCasenotes();" > <bean:message key="button.viewCasenotes" /></html:button> 
															<html:button property="submitAction" styleId="printApptButton" styleClass="hidden" onclick="printAppointment();" > <bean:message key="button.printAppt" /></html:button>
														</td>
													</tr>
													<tr id="buttonsRow2" class="hidden">
														<td align="center">
															<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
															<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);" styleClass="hidden" styleId="finishButton"> <bean:message key="button.finish" /></html:submit>
															<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
														</td>
													</tr>
												</table>
												<!-- END BUTTON TABLE -->
												
												<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_DELETE%>">
													<script> showFinishButton(); </script>
												</logic:equal>
                                                <logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_REMOVEENTRY%>">
                                               		<script> showFinishButton(); </script>
                                                </logic:equal>
                                                <logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_REMOVEEXIT%>">
                                                	<script> showFinishButton(); </script>
                                                </logic:equal>
                                                <logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_VIEW%>">
	                                                <logic:equal name="cscProgRefForm" property="referralStatusCd" value="<%=CSAdministerProgramReferralsConstants.INITIATED_REFERRAL_STATUS%>">
	                                                	<script>showViewInitateRefButtons();</script>
													</logic:equal>
	                                                <logic:equal name="cscProgRefForm" property="referralStatusCd" value="<%=CSAdministerProgramReferralsConstants.OPEN_REFERRAL_STATUS%>">
	                                                		<script>showViewOpenRefButtons();</script>
													</logic:equal>
	                                                <logic:equal name="cscProgRefForm" property="referralStatusCd" value="<%=CSAdministerProgramReferralsConstants.EXITED_REFERRAL_STATUS%>">
	                                                		<script> showViewExitRefButtons();  </script>	
													</logic:equal>
												</logic:equal>
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
	</div>
	<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>