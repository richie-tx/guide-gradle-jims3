<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 02/04/2008 Debbie Williamson - Converted PT to JSP -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

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
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/prApptPopUp.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top" align="center">
					
						<!-- BEGIN HEADING TABLE -->
					       <table width="100%">
					        <tr>
					         <td align="center" class="header"><bean:message key="title.CSCD"/> - Appointment List</td>
					        </tr>
					       </table>
						<!-- END HEADING TABLE -->
					
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="innerBorderTableBlack">
							<tr>
								<td class="headerLabel" nowrap width="1%"><bean:message key="prompt.supervisee"/></td>
								<td class="headerData"><bean:write name="cscProgRefForm" property="appointmentInfoBean.superviseeName" /></td>
								<td class="headerLabel"><bean:message key="prompt.SPN"/></td>
								<td class="headerData"><bean:write name="cscProgRefForm" property="appointmentInfoBean.defendantId" /></td>
							</tr>
							<tr>
								<td class="headerLabel"><bean:message key="prompt.phoneNo"/></td>
								<td class="headerData"><bean:write name="cscProgRefForm" property="appointmentInfoBean.superviseePhone" /></td>
								<td class="headerLabel"><bean:message key="prompt.dob"/></td>
								<td class="headerData"><bean:write name="cscProgRefForm" property="appointmentInfoBean.dobAsStr" /></td>
							</tr>
							<tr>
								<td class="headerLabel"><bean:message key="prompt.offense"/></td>
								<td class="headerData"><bean:write name="cscProgRefForm" property="appointmentInfoBean.offenseDesc" /></td>
								<td class="headerLabel"><bean:message key="prompt.CRT"/></td>
								<td class="headerData"><bean:write name="cscProgRefForm" property="appointmentInfoBean.crt" /></td>
							</tr>
							<tr>
								<td colspan="4"></td>
							</tr>
							<tr>
								<td colspan="4"></td>
							</tr>
							<tr>
								<td colspan="4"></td>
							</tr>
							<tr>
								<td class="headerLabel"><bean:message key="prompt.officer"/></td>
								<td class="headerData"><bean:write name="cscProgRefForm" property="appointmentInfoBean.officerName" /></td>
								<td class="headerLabel" width="1%"><bean:message key="prompt.position"/></td>
								<td class="headerData"><bean:write name="cscProgRefForm" property="appointmentInfoBean.positionPOI" />, <bean:write name="cscProgRefForm" property="appointmentInfoBean.positionName" /></td>
							</tr>
							<tr>
								<td class="headerLabel" nowrap><bean:message key="prompt.programUnit"/></td>
								<td class="headerData"><bean:write name="cscProgRefForm" property="appointmentInfoBean.programUnitDesc" /></td>
								<td class="headerLabel"><bean:message key="prompt.phoneNo"/></td>
								<td class="headerData"><bean:write name="cscProgRefForm" property="appointmentInfoBean.officerPhone" /></td>
							</tr>
						</table>
						<logic:equal name="cscProgRefForm" property="appointmentInfoBean.programLocationExist" value="true">
							<table width="100%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr class="detailHead">
									<td width="50%"><bean:write name="cscProgRefForm" property="appointmentInfoBean.serviceProviderName" /></td>
									<td width="10%" nowrap><bean:write name="cscProgRefForm" property="appointmentInfoBean.serviceProviderPhone" /></td>
									<td width="40%" align="right"><bean:write name="cscProgRefForm" property="appointmentInfoBean.serviceProviderURL" /></td>
								</tr>
								<tr>
									<td colspan="3" align="center">
										<table width="98%" cellpadding="2" cellspacing="0" class="innerBorderTable">
											<tr class="formDe" style="font-style: italic;">
												<td><bean:message key="prompt.date"/></td>
												<td><bean:message key="prompt.day"/></td>
												<td><bean:message key="prompt.time"/></td>
											</tr>
											<tr class="boldText">
												<td><bean:write name="cscProgRefForm" property="appointmentInfoBean.scheduleDateStr" /></td>
												<td><bean:write name="cscProgRefForm" property="appointmentInfoBean.weekDay" /></td>
												<td><bean:write name="cscProgRefForm" property="appointmentInfoBean.scheduleTime" /></td>
											</tr>
											<tr class="formDe" style="font-style: italic;">
												<td width="70%"><bean:message key="prompt.address"/></td>
												<td width="15%"><bean:message key="prompt.phone"/></td>
												<td width="15%"><bean:message key="prompt.fax"/></td>
											</tr>
											<tr>
												<td><bean:write name="cscProgRefForm" property="appointmentInfoBean.programLocationBean.streetNumber" />
	                                                <bean:write name="cscProgRefForm" property="appointmentInfoBean.programLocationBean.streetName" />
	                                                <bean:write name="cscProgRefForm" property="appointmentInfoBean.programLocationBean.streetTypeCd" /><br>
	                                                <bean:write name="cscProgRefForm" property="appointmentInfoBean.programLocationBean.city" />,
	                                                <bean:write name="cscProgRefForm" property="appointmentInfoBean.programLocationBean.state" />
	                                                <bean:write name="cscProgRefForm" property="appointmentInfoBean.programLocationBean.zipCode" /></td>
												<td nowrap><bean:write name="cscProgRefForm" property="appointmentInfoBean.programLocationBean.locationPhone.formattedPhoneNumber" /></td>
												<td nowrap><bean:write name="cscProgRefForm" property="appointmentInfoBean.programLocationBean.locationFax.formattedPhoneNumber" /></td>
											</tr>
											<tr>
												<td colspan="3" class="boldText"><bean:write name="cscProgRefForm" property="appointmentInfoBean.programName" /></td>
											</tr>
											<tr>
												<td colspan="3" class="formDe" style="font-style: italic;"><bean:message key="prompt.contact"/></td>
											</tr>
											<tr>
											<td colspan="3">
											<logic:iterate id="contactName" name="cscProgRefForm" property="appointmentInfoBean.contactsList">
												<bean:write name="contactName"/></br>
											</logic:iterate>
											</td>
											</tr>
											<tr>
												<td colspan="3" class="formDe" style="font-style: italic;"><bean:message key="prompt.officeHours"/></td>
											</tr>
											<tr>
												<td colspan="3"><bean:write name="cscProgRefForm" property="appointmentInfoBean.officeHours" /></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</logic:equal>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<!--condition literals end-->

<!-- begin BUTTON TABLE -->
<table width="100%" cellpadding="2" cellspacing="1" border="0">
	<tr>
		<td align="center">
			<input type="button" value="Print" onclick="window.print()">
			<input type="button" value="Close" onclick="window.close()">
		</td>
	</tr>
</table>

</div>
<br>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
