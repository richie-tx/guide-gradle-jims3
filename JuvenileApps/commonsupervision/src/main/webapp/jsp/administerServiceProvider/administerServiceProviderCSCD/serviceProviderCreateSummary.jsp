<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Used for Service Provider Creation Summary (CSCD)-->
<!--MODIFICATIONS -->
<!-- DWilliamson 11/20/2007	Create JSP -->
 
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDCodeTableConstants" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>
<html:html>

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

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading"/> - serviceProviderCreateSummary.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitCSCServiceProviderUpdate" target="content">
<logic:equal name="cscServiceProviderForm" property="action" value="create">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|3"> 
</logic:equal>
<logic:equal name="cscServiceProviderForm" property="action" value="update">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|20"> 
</logic:equal>
<logic:equal name="cscServiceProviderForm" property="action" value="inactivate">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|30"> 
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
								<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-
								<logic:equal name="cscServiceProviderForm" property="action" value="create">
									<bean:message key="prompt.create"/> 
								</logic:equal>
								<logic:equal name="cscServiceProviderForm" property="action" value="update">
									<bean:message key="prompt.update"/> 
								</logic:equal>
								<logic:equal name="cscServiceProviderForm" property="action" value="inactivate">
									<bean:message key="prompt.inactivate"/> 
								</logic:equal>
								<bean:message key="title.serviceProvider"/> <bean:message key="title.summary"/>
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
							<tr>
								<td>
									<ul>
										<li>Review and click Save and Continue button, or click Back button if changes are necessary.</li>
									</ul>
								</td>
							</tr>
						</table>
						<!-- BEGIN  TABLE -->
						<table width="98%" border="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class="detailHead">
									<table width="100%" cellpadding="2" cellspacing="0">
										<tr>
											<td class="detailHead"><bean:message key="title.serviceProvider" /> <bean:message key="prompt.info" /></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellpadding="4" cellspacing="1">
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.name" /></td>
											<td class="formDe" colspan="3"><bean:write name="cscServiceProviderForm" property="name"/></td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap><bean:message key="prompt.startDate" /></td>
											<td class="formDe"><bean:write name="cscServiceProviderForm" property="startDateAsStr" formatKey="date.format.mmddyyyy"/></td>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inHouse" /></td>
											<td class="formDe"><bean:write name="cscServiceProviderForm" property="inHouseAsStr"/></td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.IFAS" /></td>
											<td colspan="1" class="formDe"><bean:write name="cscServiceProviderForm" property="ifasNumber"/></td>
											<td class="formDeLabel" width="1%" nowrap>Faith Based</td>
											<td class="formDe">
											<logic:equal name="cscServiceProviderForm" property="isFaithBased" value="true">
												YES
											</logic:equal>
											<logic:equal name="cscServiceProviderForm" property="isFaithBased" value="false">
												NO
											</logic:equal>																							
												
											</td>											
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.phone" /></td>
											<td class="formDe" colspan="3"><msp:formatter name="cscServiceProviderForm" property="phoneNumber" format="A-P-F"/><logic:notEqual name="cscServiceProviderForm" property="phoneNumber.ext" value=""><bean:message key="prompt.ext"/> <msp:formatter name="cscServiceProviderForm" property="phoneNumber" format="X"/></logic:notEqual></td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.fax" /></td>
											<td class="formDe" colspan="3"><msp:formatter name="cscServiceProviderForm" property="faxNumber" format="A-P-F"/></td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.website" /></td>
											<td class="formDe" colspan="3"><logic:notEmpty name="cscServiceProviderForm" property="website"><bean:define id="web1" name="cscServiceProviderForm" property="website"/>
							<a href="//<%=web1%>" target="_new"><bean:write name="cscServiceProviderForm" property="website"/></a>
							</logic:notEmpty>
                             <logic:empty name="cscServiceProviderForm" property="website"><bean:write name="cscServiceProviderForm" property="website"/></logic:empty></td>
										</tr>
										<tr>
										  <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.email" /></td>
										  <td class="formDe" colspan="3"> <a href='mailto:<bean:write name="cscServiceProviderForm" property="email"/>'><bean:write name="cscServiceProviderForm" property="email"/> </a></td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.ftp" /></td>
											<td class="formDe" colspan="3"><bean:write name="cscServiceProviderForm" property="ftp"/></td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap colspan="4"><bean:message key="prompt.comments"/></td>
										</tr>
										<tr>
											<td class="formDe" colspan="4"><bean:write name="cscServiceProviderForm" property="comments"/></td>
										</tr>
									</table>
									<table width="100%" cellpadding="1" cellspacing="1">
										<!-- BEGIN ADDRESS INFORMATION TABLES -->
										<tr>
											<td class="detailHead">
												<table width="100%" cellpadding="2" cellspacing="0">
													<tr>
														<td width="1%"><a href="javascript:showHideMulti('mailing', 'ma', 1,'/<msp:webapp/>')"><img border=0 src="../../../images/contract.gif" name="mailing"></a></td>
														<td class="detailHead">
														<logic:equal name="cscServiceProviderForm" property="inHouse" value="true">
															<bean:write name="cscServiceProviderForm" property="name"/> <bean:message key="prompt.address"/>
														</logic:equal>
														<logic:notEqual name="cscServiceProviderForm" property="inHouse" value="true">
														<bean:message key="prompt.mailingAddress"/>
														</logic:notEqual>
														
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr class="visibleTR" id="ma0">
											<td>
												<table width="100%" border="0" cellpadding="2" cellspacing="1">
													<tr class="formDeLabel">
														<td width="20%"><bean:message key="prompt.addressType" /></td>
														<td width="70%"><bean:message key="prompt.address" /></td>
														<td width="10%"><bean:message key="prompt.county" /></td>
													</tr>
													<tr class="formDe">
														<td><bean:write name="cscServiceProviderForm" property="mailingAddress.addressType"/></td>
														<td>
														<div><bean:write name="cscServiceProviderForm" property="mailingAddress.streetAddress"/></div>
														<div><bean:write name="cscServiceProviderForm" property="mailingAddress.cityStateZip"/></div>
															
														</td>
														<td><bean:write name="cscServiceProviderForm" property="mailingAddress.county"/></td>
													</tr>
												</table>
											</td>
										</tr>
										<!-- END ADDRESS INFORMATION TABLE -->
									</table>
									<logic:equal name="cscServiceProviderForm" property="inHouse" value="false">
									<table width="100%" cellpadding="1" cellspacing="1">
										<!-- BEGIN ADDRESS INFORMATION TABLES -->
										<tr>
											<td class="detailHead">
												<table width="100%" cellpadding="2" cellspacing="0">
													<tr>
														<td width="1%"><a href="javascript:showHideMulti('billing', 'ba', 1,'/<msp:webapp/>')"><img border=0 src="../../../images/contract.gif" name="billing"></a></td>
														<td class="detailHead"><bean:message key="prompt.billingAddress" /></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr class="visibleTR" id="ba0">
											<td>
												<table width="100%" border="0" cellpadding="2" cellspacing="1">
													<tr class="formDeLabel">
														<td width="20%"><bean:message key="prompt.addressType" /></td>
														<td width="70%"><bean:message key="prompt.address" /></td>
														<td width="10%"><bean:message key="prompt.county" /></td>
													</tr>
													<tr class="formDe">
														<td><bean:write name="cscServiceProviderForm" property="billingAddress.addressType"/></td>
														<td>
																<div><bean:write name="cscServiceProviderForm" property="billingAddress.streetAddress"/></div>
														<div><bean:write name="cscServiceProviderForm" property="billingAddress.cityStateZip"/></div>
														</td>
														<td><bean:write name="cscServiceProviderForm" property="billingAddress.county"/></td>
													</tr>
												</table>
											</td>
											
										</tr>
										<!-- END ADDRESS INFORMATION TABLE -->
									</table>
									</logic:equal>
								</td>
							</tr>
						</table>
						<br>
						<!-- BEGIN BUTTON TABLE -->
						<table border="0">
							<tr>
								<td>
									<html:submit property="submitAction">
										<bean:message key="button.back"></bean:message>
									</html:submit>
								</td>
								<td>
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
										<bean:message key="button.saveAndContinue"></bean:message>
									</html:submit>
								</td>
								<td>
									<html:submit property="submitAction">
										<bean:message key="button.cancel"></bean:message>
									</html:submit>
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
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
