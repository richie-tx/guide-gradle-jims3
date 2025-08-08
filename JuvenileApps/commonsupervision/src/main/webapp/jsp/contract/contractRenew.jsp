<!DOCTYPE HTML>
<!-- 10/06/2006	 Clarence Shimek    Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ include file="../jQuery.fw" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/csbase.css" />
<html:base />
<title><bean:message key="title.heading" /> - contract/contractRenew.jsp</title>

<!-- JAVASCRIPT FILES -->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/condition_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/contracts/contractRenew.js"></script>

</head>

<body topmargin="0" leftmargin="0">
<html:form action="/displayContractSummary" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/Contract/Administer_Contract.htm#|424">
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
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tabid" value=""/>
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
			<!-- BEGIN HEADING TABLE -->
							<table width="100%">
								<tr>
									<td align="center" class="header">
										<bean:message key="prompt.renew" />&nbsp;<bean:message key="prompt.contract" />
									</td>
								</tr>
							</table>
			<!-- END HEADING TABLE -->
			<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td>
										<ul>
											<li>Enter new end date and click next.</li>
										</ul>
									</td>
								</tr>
							</table>
			<!-- END INSTRUCTION TABLE -->							
            <!-- BEGIN SERVICE PROVIDER TABLE -->
							<logic:equal name="contractForm" property="showServiceProviderInfo" value="Y">
								<tiles:insert page="../common/contractServiceProviderInfo.jsp" flush="true"></tiles:insert>							
							</logic:equal>					
			<!-- END SERVICE PROVIDER TABLE -->	         
							<div class="spacer"></div>
							<table width="98%" cellpadding="1" cellspacing="1">
								<tr>
									<td class="required"><bean:message key="prompt.2.diamond"/> <bean:message key="prompt.requiredFieldsInstruction"/> *All dates fields must be in the format of mm/dd/yyyy</td>
								</tr>
							</table>
							<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr>
									<td class="detailHead"><bean:message key="prompt.contract"/>&nbsp;<bean:message key="prompt.info"/></td>
								</tr>
								<tr>
									<td>
										<table width="100%" cellpadding="4" cellspacing="1">
											<tr>
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.number"/></td>
												<td class="formDe"><bean:write name="contractForm" property="contractNum" /></td>
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.renewal#"/></td>
												<td class="formDe"><bean:write name="contractForm" property="renewalNum" /></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.name"/></td>
												<td class="formDe" colspan="3"><bean:write name="contractForm" property="contractName" /></td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap><bean:message key="prompt.startDate"/></td>
												<td class="formDe">
													<bean:write name="contractForm" property="startDate" formatKey="date.format.mmddyyyy"/>
													<input type="hidden" name="startDate" value=<bean:write name="contractForm" property="startDate" formatKey="date.format.mmddyyyy"/> >
												</td>
												<td class="formDeLabel" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.endDate"/></td>
												<td class="formDe">
													<html:text name="contractForm" property="endDate" size="10" maxlength="10" styleId="endDate"/>
													<input type="hidden" name="originalEndDate" value=<bean:write name="contractForm" property="endDate" formatKey="date.format.mmddyyyy"/> >	
												</td>
											</tr>
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.fundingType" /></td>
												<td class="formDe" colspan="3"><bean:write name="contractForm" property="contractType" /></td>
											</tr>
											<tr>	
												<td class="formDeLabel" nowrap><bean:message key="prompt.gLAccountKey" /></td>
												<td class="formDe" colspan="3"><bean:write name="contractForm" property="glAccountKey" /></td>
											</tr>
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.programFunding" />&nbsp;<bean:message key="prompt.description" /></td>
												<td class="formDe" colspan="3"><bean:write name="contractForm" property="programFundingDesc" /></td>
											</tr>
											<logic:equal name="contractForm" property="departmentId" value="CSC">
												<tr>
													<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.tracerNumber" />&nbsp;<bean:message key="prompt.range" /></td>
													<td class="formDe" colspan="3">
														<bean:write name="contractForm" property="tracerNumberRangeFrom" />&nbsp;-&nbsp;
														<bean:write name="contractForm" property="tracerNumberRangeTo"/>
													</td>
												</tr>
											</logic:equal>
											<logic:equal name="contractForm" property="showServiceProviderInfo" value="Y">
											<tr>
												<td class="formDeLabel" nowrap><bean:message key="prompt.total" />&nbsp;<bean:message key="prompt.value" /></td>
												<td class="formDe"><bean:write name="contractForm" property="totalValue" /></td>
												<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.service" />&nbsp;<bean:message key="prompt.provider" />&nbsp;<bean:message key="prompt.value" /></td>
												<td class="formDe"><bean:write name="contractForm" property="serviceProviderValue" /></td>
											</tr>
											</logic:equal>	
											<logic:notEqual name="contractForm" property="showServiceProviderInfo" value="Y">
											<tr>
												<td class="formDeLabel" nowrap><bean:message key="prompt.total" />&nbsp;<bean:message key="prompt.value" /></td>
												<td class="formDe" colspan="3"><bean:write name="contractForm" property="totalValue" /></td>
											</tr>
											</logic:notEqual>	
										</table>
									</td>
								</tr>
							</table>
							<br>
				<!-- BEGIN BUTTON TABLE -->
							<table border="0">
								<tr>
									<td align="center">
										<input type="button" value="Back" onclick="history.go(-1)">	&nbsp;								
										<html:submit property="submitAction" onclick="return checkForInput(this.form) && disableSubmit(this, this.form);"><bean:message key="button.next"/></html:submit>&nbsp;										
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>																					
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
<!-- END  TABLE -->
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
