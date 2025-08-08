<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%--MODIFICATIONS --%>
<%-- 12/06/2007 Debbie Williamson Create Contact Summary JSP --%>
<%-- 10/02/2013 Richard Capesatani 75969 hide update button for inactive service providers--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDCodeTableConstants" %>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<%--CUSTOM LIBRARIES NEEDED FOR PAGE --%>
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>
<html:html>
<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>/css/base.css" />
<html:base />

<title><bean:message key="title.heading"/> - contactSummary.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>

<body topmargin="0" leftmargin="0" onKeyDown="checkEnterKeyAndSubmit(event,true)">

<html:form action="/displayCSCContactUpdate" target="content" >
<logic:equal name="cscServiceProviderContactForm" property="action" value="create">
    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|6">
</logic:equal>
<logic:equal name="cscServiceProviderContactForm" property="action" value="update">
    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|12">
</logic:equal>
<logic:equal name="cscServiceProviderContactForm" property="action" value="inactivate">
    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|9">
</logic:equal>
<logic:equal name="cscServiceProviderContactForm" property="action" value="view">
    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|4">
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
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true"/>
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
								<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-<bean:message key="title.serviceProvider"/>&nbsp;–
                                    <logic:equal name="cscServiceProviderContactForm" property="action" value="create"><bean:message key="prompt.create"/></logic:equal>
								    <logic:equal name="cscServiceProviderContactForm" property="action" value="update"><bean:message key="prompt.update"/></logic:equal>
								    <logic:equal name="cscServiceProviderContactForm" property="action" value="inactivate"><bean:message key="prompt.inactivate"/></logic:equal>
								    <bean:message key="prompt.contact"/> 
                                    <logic:notEqual name="cscServiceProviderContactForm" property="action" value="view"> <bean:message key="prompt.summary"/> </logic:notEqual>
								    <logic:equal name="cscServiceProviderContactForm" property="action" value="view"><bean:message key="prompt.details"/></logic:equal> 
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
						<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
							<tr class="hidden" id="instructions">
								<td>
									<ul>
										<li><logic:equal name="cscServiceProviderContactForm" property="action" value="view">
												Click appropriate button below.
                                            </logic:equal>
                                            <logic:notEqual name="cscServiceProviderContactForm" property="action" value="view"> 
											    Review entries, click Save & Continue.
										    </logic:notEqual>
                                        </li>
									</ul>
								</td>
							</tr>
							
						</table>
						<!-- BEGIN  TABLE -->						
						<tiles:insert page="../../common/serviceProviderHeader.jsp" flush="true" />										 			                          
						<div class="spacer4px"></div>
						<table width="98%" border="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class="detailHead">
									<table width="100%" cellpadding="2" cellspacing="0">
										<tr>
											<td class="detailHead"><bean:message key="prompt.contactInfo"/></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellpadding="4" cellspacing="1">
										<tr class="formDe">
											<td class="formDeLabel" nowrap><bean:message key="prompt.name"/></td>
											<td colspan="3" class="formDe"><bean:write name="cscServiceProviderContactForm" property="contactName"/></td>
										</tr>
										<logic:equal name="cscServiceProviderForm" property="inHouseAsStr" value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_INHOUSE_NO%>">
										<tr class="formDe">
											<td class="formDeLabel" nowrap><bean:message key="prompt.jobTitle"/></td>
											<td colspan="3" class="formDe"><bean:write name="cscServiceProviderContactForm" property="jobTitle"/></td>
										</tr>
										</logic:equal>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.administrativeContact"/></td>
											<td class="formDe" colspan="3"><bean:write name="cscServiceProviderContactForm" property="adminContactAsStr"/></td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap><bean:message key="prompt.officePhone"/></td>
											<td class="formDe" colspan="3"><msp:formatter name="cscServiceProviderContactForm" property="officePhone" format="A-P-F"/>  <logic:notEqual name="cscServiceProviderContactForm" property="officePhone.ext" value=""><bean:message key="prompt.ext"/> <msp:formatter name="cscServiceProviderContactForm" property="officePhone" format="X"/></logic:notEqual></td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap><bean:message key="prompt.fax"/></td>
											<td class="formDe" colspan="3"><msp:formatter name="cscServiceProviderContactForm" property="fax" format="A-P-F"/></td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap><bean:message key="prompt.cellPhone"/></td>
											<td class="formDe"><msp:formatter name="cscServiceProviderContactForm" property="cellPhone" format="A-P-F"/></td>
											<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.pager"/></td>
											<td class="formDe"><msp:formatter name="cscServiceProviderContactForm" property="pager" format="A-P-F"/></td>
										</tr>
										<tr>

											<td class="formDeLabel" nowrap><bean:message key="prompt.email"/></td>
											<td class="formDe" colspan="3"><bean:write name="cscServiceProviderContactForm" property="email"/></td>
										</tr>
										<tr>
											<td class="formDeLabel" colspan="4"><bean:message key="prompt.notes"/></td>
										</tr>
										<tr>
											<td class="formDe" colspan="4"><bean:write name="cscServiceProviderContactForm" property="notes"/></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<br>
						<!-- BEGIN BUTTON TABLE -->
						<logic:notEqual name="cscServiceProviderContactForm" property="action" value="view">
						<table border="0"id="summaryTable">
							<tr>
								<td align="center">
									<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.saveAndContinue"/></html:submit>
									<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
								</td>
							</tr>
						</table>
						</logic:notEqual>
						<logic:equal name="cscServiceProviderContactForm" property="action" value="view">
						<table border="0" id="viewTable">
							<tr>
								<td align="center">
									<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
									<logic:notEqual name="cscServiceProviderForm" property="statusId" value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_INACTIVE%>">
										<logic:notEqual name="cscServiceProviderContactForm" property="contactStatusId" value="<%=PDCodeTableConstants.ASP_CS_CONTACT_INACTIVE%>">
											<html:submit property="submitAction"><bean:message key="button.update"/></html:submit>
											<logic:notEqual name="cscServiceProviderContactForm" property="lastAdminContact" value="true">
												<html:submit property="submitAction"><bean:message key="button.inactivate"/></html:submit>
											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
									<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
								</td>
							</tr>
						</table>
						</logic:equal>
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
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
