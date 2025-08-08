<!DOCTYPE HTML>
<!-- 10/06/2006	 Clarence Shimek    Create JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>/css/commonsupervision.css" />

<html:base />
<title><bean:message key="title.heading" /> - contract/contractEnterSPValueSummary.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body topmargin="0" leftmargin="0">
<html:form action="/submitServiceProviderContractUpdate" target="content">
<logic:equal name="contractForm" property="action" value="summary">
	<input type="hidden" name="helpFile" value="commonsupervision/Contract/Administer_Contract.htm#|428">
</logic:equal> 
<logic:equal name="contractForm" property="action" value="confirm">
	<input type="hidden" name="helpFile" value="commonsupervision/Contract/Administer_Contract.htm#|429">
</logic:equal>
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
										<bean:message key="prompt.serviceProviderContractValue" />
									<logic:equal name="contractForm" property="action" value="summary">
											<bean:message key="prompt.summary" />
										</logic:equal> 
										<logic:equal name="contractForm" property="action" value="confirm">
											<bean:message key="prompt.confirmation" />
										</logic:equal> 
									</td>
								</tr>
							</table> 
			<!-- END HEADING TABLE -->
			<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
								<tr>
								<logic:equal name="contractForm" property="action" value="summary">
									<td>
										<ul>
											<li>Review information and select Finish button to update Service & Contract(s).</li>
										</ul>
									</td>
								</logic:equal>	
								<logic:equal name="contractForm" property="action" value="confirm">
									<logic:equal name="contractForm" property="showDropAssignmentMessage" value="Y">
									   <td class="confirm"><bean:message key="prompt.contract" />&nbsp;<bean:message key="prompt.successfully" />&nbsp;removed</td>
									</logic:equal>	
									<logic:equal name="contractForm" property="showNoMessage" value="N">
										<td class="confirm"><bean:message key="prompt.contract" />&nbsp;<bean:message key="prompt.successfully" />&nbsp;<bean:message key="prompt.updated" /></td>
									</logic:equal>	
								</logic:equal>	
								</tr>
							</table>  
			<!-- END INSTRUCTION TABLE -->	
			<!-- BEGIN  SERVICE PROVIDER TABLE -->
							<tiles:insert page="../common/contractServiceProviderInfo.jsp" flush="true"></tiles:insert> 
			<!-- END SERVICE PROVIDER TABLE -->			
						<div class="spacer"></div>
							<table width="98%" cellpadding="1" cellspacing="1">
								<tr>
									<td class="required"><bean:message key="prompt.2.diamond"/> <bean:message key="prompt.requiredFieldsInstruction"/></td>
								</tr>
							</table>  
							<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr>
									<td class="detailHead"><bean:message key="prompt.contract"/>&nbsp;<bean:message key="prompt.info"/></td>
								</tr>
								<tr>
									<td>
										<table width="100%" cellpadding="2" cellspacing="1">
											<tr class="formDeLabel">
												<td><bean:message key="prompt.name"/></td>
												<td><bean:message key="prompt.number"/></td>
												<td><bean:message key="prompt.type"/></td>
												<td><bean:message key="prompt.startDate" /></td>
												<td><bean:message key="prompt.endDate" /></td>
											</tr>
											<logic:iterate id="currentContracts" name="contractForm" property="currentContracts">
											<tr>										
												<td><bean:write name="currentContracts" property="contractName"/></td> 											
												<td><bean:write name="currentContracts" property="number"/></td> 
												<td><bean:write name="currentContracts" property="contractType"/></td> 
												<td><bean:write name="currentContracts" property="startDate" formatKey="date.format.mmddyyyy"/></td> 
												<td><bean:write name="currentContracts" property="endDate" formatKey="date.format.mmddyyyy"/></td> 
											</tr>
											<tr>
												<td colspan="5">
													<table width="100%" cellpadding="2" cellspacing="1">
														<tr>
															<td class="formDeLabel" width="20%" nowrap><bean:message key="prompt.available"/>&nbsp;<bean:message key="prompt.value"/></td> 
															<td class="formDe" width="30%"><bean:write name="currentContracts" property="availableContractValue" formatKey="currency.format" /></td> 
															<td class="formDeLabel" width="20%" nowrap><bean:message key="prompt.serviceProvider"/>&nbsp;<bean:message key="prompt.value"/></td>
															<td class="formDe" width="30%"><bean:write name="currentContracts" property="serviceProviderValue" formatKey="currency.format" /></td> 
														</tr> 
													</table> 
												</td>
											</tr>
											</logic:iterate> 
										</table> 
									</td>
								</tr>
							</table>	
							<br>  
			<!-- BEGIN BUTTON TABLE -->
			 			<table border="0">
								<tr>
									<td align="center">
									<logic:equal name="contractForm" property="action" value="summary">
										<input type="button" value="Back" onclick="history.go(-1)">&nbsp;
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>&nbsp;										
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>																					
									</logic:equal>	
									<logic:equal name="contractForm" property="action" value="confirm">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.programDashboard"/></html:submit>
									</logic:equal>										
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
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
