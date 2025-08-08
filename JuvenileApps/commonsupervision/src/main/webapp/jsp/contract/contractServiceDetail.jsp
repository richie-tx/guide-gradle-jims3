<!DOCTYPE HTML>
<!-- 10/06/2006	 Clarence Shimek    Create JSP -->
<!-- 12/21/2015	 Ricahrd Capestani    30728 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Search Contract) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

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
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/commonsupervision.css" />
<html:base />
<title><bean:message key="title.heading" /> - contract/contractServiceDetail.jsp</title>

<!-- JAVASCRIPT FILES -->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0">
<html:form action="/handleContractDetailsSelection" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/Contract/Administer_Contract.htm#|417">
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
										<bean:message key="prompt.service" />&nbsp;Details
									</td>
								</tr>
							</table>
			<!-- END HEADING TABLE -->
            				<div class="spacer"></div>
          				<logic:iterate id="servIter" name="contractForm" property="currentSPServices">
							<table cellpadding="1" cellspacing="0" border="0" width="98%">
								<tr>
									<td bgcolor="#cccccc">
										<table width="100%" border="0" cellpadding="2" cellspacing="1">
											<tr>
												<td class="headerLabel" width="8%" nowrap><bean:message key="prompt.provider" />&nbsp;<bean:message key="prompt.name" /></td>
												<td colspan="3" class="headerData"><bean:write name="servIter" property="juvServProviderName" /></td>
											</tr>
											<tr>
												<td class="headerLabel"><bean:message key="prompt.startDate" /></td>
												<td class="headerData"><bean:write name="servIter" property="startDate" /></td>
												<td class="headerLabel" width="5%"><bean:message key="prompt.inHouse" /></td>
												<td class="headerData">
													<logic:equal name="servIter" property="inHouse" value="true">
														YES
													</logic:equal>	
													<logic:equal name="servIter" property="inHouse" value="false">
														NO
													</logic:equal>	
												</td>
											</tr>
											<tr>
												<td class="headerLabel"><bean:message key="prompt.program" />&nbsp;<bean:message key="prompt.name" /></td>
												<td class="headerData" colspan="3"><bean:write name="servIter" property="programName" /></td>
											</tr>
											<tr>
												<td class="headerLabel"><bean:message key="prompt.targetIntervention" /></td>
												<td class="headerData" colspan="3"><bean:write name="servIter" property="targetIntervention" /></td>
											</tr>
										</table>
									</td>
								</tr>
							</table> 
			<!-- END PROVIDER INFORMATION TABLE -->
							<br>
			<!-- BEGIN SERVICE INFORMATION TABLE -->
							<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr>
									<td class="detailHead">
										<table width="100%" cellpadding="2" cellspacing="0">
											<tr>
												<td class="detailHead"><bean:message key="prompt.serviceInfo" /></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
									<table width="100%" cellpadding="4" cellspacing="1">
										<tr>
											<td class="formDeLabel" nowrap><bean:message key="prompt.name" /></td>
											<td class="formDe"><bean:write name="servIter" property="serviceName" /></td>
											<td class="formDeLabel" nowrap><bean:message key="prompt.code" /></td>
											<td class="formDe"><bean:write name="servIter" property="serviceCode" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.type" /></td>
											<td class="formDe" colspan="3"><bean:write name="servIter" property="serviceType" /> </td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.maxEnrollment" /></td>
											<td class="formDe"><bean:write name="servIter" property="maxEnrollment" /></td>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.cost" /></td>
											<td class="formDe"><bean:write name="servIter" property="serviceCost" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.service"/> <bean:message key="prompt.location"/> Unit(s)</td>
											<td class="formDe" colspan="3"><bean:write name="servIter" property="locationName" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" colspan="4"><bean:message key="prompt.description" /></td>
										</tr>
										<tr>	
											<td class="formDe" colspan="4"><bean:write name="servIter" property="serviceDescription" /></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
<!-- END SERVICE INFORMATION TABLE -->
						<br>
				<!-- BEGIN BUTTON TABLE -->
						<table border="0">
							<tr>
								<td align="center">
									<input type="button" value="Back" onclick="history.go(-1)">	&nbsp;								
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>																					
								</td>
							</tr>
						</table>
				<!-- END BUTTON TABLE -->
						</td>
					</tr>
				</table>
				</logic:iterate> 
				<br>
			</td>
		</tr>
	</table>
<!-- END  TABLE -->
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
