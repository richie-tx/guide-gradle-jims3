<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/31/2008 Debbie Williamson - Created Tile -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<%@page import="naming.CSAdministerProgramReferralsConstants"%>

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
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/programReferralInformationTile.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body>
<div align="center">
<table width="100%" cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr class="detailHead">
	<%--	<td width=1%><a href="javascript:showHide('pr', 'row', '/<msp:webapp/>')"><img border="0" src="/<msp:webapp/>images/contract.gif" name="pr"></a></td> --%>
		<td><bean:message key="prompt.programReferral"/>&nbsp;<bean:message key="prompt.info"/></td>
	</tr>
	<tr id="prSpan">
		<td colspan="2">
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
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.programBeginDate"/></td>
								<td class="formDe" colspan="3">
									<bean:write name="cscProgRefForm" property="programBeginDateAsStr"/>
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
									<bean:write name="cscProgRefForm" property="tracerNum"/>
								</td>
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
					<td nowrap width="5%"><bean:message key="prompt.CSTSCode"/></td>
					<td nowrap><bean:message key="prompt.languagesOffered"/></td>
					<td><bean:message key="prompt.sexSpecific"/></td>
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
					<td class="bottomBorder"><bean:write name="cscProgRefForm" property="referralProgramLocBean.referralTypeDesc"/></td>
					<td class="bottomBorder"><a href="javascript: openWindow('/<msp:webapp/>displayCSCProgramUpdate.do?submitAction=View Details&selectedValue=<bean:write name="cscProgRefForm" property="referralProgramLocBean.programId"/>')"><bean:write name="cscProgRefForm" property="referralProgramLocBean.programIdentifier"/></a>&nbsp;</td>
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
					<td colspan="2">
						<div><bean:write name="cscProgRefForm" property="referralProgramLocBean.programLocationBean.streetNumber" />
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
<!-- END  TABLE -->
</div>
</body>
</html:html>
