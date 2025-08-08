<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%--11/05/2008	C Shimek   - created JSP from manageAssociate/assocateView.jsp  --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@page import="naming.UIConstants"%>

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

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - manageRecords/associateDetails.jsp</title>

<script language="JavaScript" src="/<msp:webapp/>js/case_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/app.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body>
<!-- BEGIN FORM -->
<html:form action="/submitAssociateSummary" target="content">
	<table width="98%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
			<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
		</tr>
		<tr>
			<td valign=top>
<!-- BEGIN HEADING TABLE -->
			<table width=100%>
				<tr>
					<td align="center" class="header">
						<bean:message key="prompt.associate" /> <bean:message key="prompt.details" />
					</td>		
				</tr>
			</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
			<table width=98% align="center">							
				<tr>
					<td align="center" class="errorAlert"><html:errors/></td>
				</tr>		
			</table>
<!-- END ERROR TABLE -->
<!-- BEGIN DETAILS TABLE -->
			<table width="98%" align="center" border="0" cellpadding="4" cellspacing="1">
<!-- BEGIN ASSOCIATE INFO SECTION -->
				<tr>
					<td class="detailHead" colspan="4">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr class="detailHead">
								<td class="detailHead">Associate Information</td>
								<td class="legendSmallText" align="right">Last Updated:&nbsp;
									<bean:write name="associateForm" property="updateDate" formatKey="date.format.mmddyyy" />&nbsp;
									<bean:write name="associateForm" property="updateJIMS2User" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap><bean:message key="prompt.name"/></td>
					<td class="formDe" colspan=3>
						<bean:write name="associateForm" property="associateName.formattedName" />
					</td>
				</tr>
				<tr id=viewUpdateRow>
					<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.relationship"/></td>
					<td class="formDe"><bean:write name="associateForm" property="relationship" /></td>
					<td class="formDeLabel" width=1%><bean:message key="prompt.status"/></td>
					<td class="formDe">
						<logic:equal name="associateForm" property="status" value="true">
							<img src="/<msp:webapp/>images/thumbs_up.gif" hspace=0 title="This Associate has a Good Status">
						</logic:equal>
						<logic:equal name="associateForm" property="status" value="false">
							<img src="/<msp:webapp/>images/thumbs_down.gif" hspace=0 title="This Associate has a Bad Status"/>
						</logic:equal>
					</td>
				</tr>
				<tr>
					<td class="formDeLabel" width=1% nowrap colspan="4"><bean:message key="prompt.comments" /></td>
				</tr>
				<tr>
					<td class="formDe" colspan="4">
						<bean:write name="associateForm" property="comments" />
					</td>
				</tr>
<!-- END ASSOCIATE INFO SECTION -->
				<tr>
					<td>&nbsp;</td>
				</tr>
<!-- BEGIN CONTACT SECTION -->
				<tr>
					<td class="detailHead" colspan="4"><bean:message key="prompt.contactInfo"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap><bean:message key="prompt.homePhone"/></td>
					<td class="formDe" colspan="3"><bean:write name="associateForm" property="homePhone.formattedPhoneNumber" /></td>
				</tr>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.workPhone"/></td>
					<td class="formDe" colspan="3"><bean:write name="associateForm" property="workPhone.formattedPhoneNumber" /></td>
				</tr>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.cellPhone"/></td>
					<td class="formDe" colspan="3"><bean:write name="associateForm" property="cellPhone.formattedPhoneNumber" /></td>
				</tr>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.pager"/></td>
					<td class="formDe" colspan="3"><bean:write name="associateForm" property="pager.formattedPhoneNumber" /></td>
				</tr>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.email"/></td>
					<td class="formDe" colspan="3"><bean:write name="associateForm" property="email" /></td>
				</tr>
<!-- END CONTACT SECTION -->
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
<!-- END ASSOCIATE INFO TABLE  -->			
<!-- BEGIN ADDRESS TABLE -->
			<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="detailHead" colspan="3">
						<table width="100%" border="0" cellpadding="4" cellspacing="1">
							<tr>
								<td class=detailHead nowrap><bean:message key="prompt.residenceAddress"/></td>
							</tr>
						</table>
					</td>
				</tr>
<!-- BEGIN RESIDENCE SECTION -->				
				<tr>
					<td colspan="3">
						<table border="0" cellpadding="4" cellspacing=1 width="100%">
							<tr>
								<td class="formDeLabel" width="45%"><bean:message key="prompt.streetNum"/></td>
								<td class="formDeLabel" colspan="2"><bean:message key="prompt.streetName"/></td>
							</tr>
							<tr>
								<td class="formDe"><bean:write name="associateForm" property="primaryResidenceAddress.streetNumber" /></td>
								<td class="formDe" colspan="2"><bean:write name="associateForm" property="primaryResidenceAddress.streetName" /></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.streetType"/></td>
								<td nowrap class="formDeLabel" colspan="2" ><bean:message key="prompt.aptSuite"/></td>
							</tr>
							<tr>
								<td class="formDe"><bean:write name="associateForm" property="primaryResidenceAddress.streetType" /></td>
								<td class="formDe" colspan="2"><bean:write name="associateForm" property="primaryResidenceAddress.aptNumber" /></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.city"/></td>
								<td class="formDeLabel"><bean:message key="prompt.state"/></td>
								<td class="formDeLabel"><bean:message key="prompt.zipCode"/></td>
							</tr>
							<tr>
								<td class="formDe"><bean:write name="associateForm" property="primaryResidenceAddress.city" /></td>
								<td class="formDe"><bean:write name="associateForm" property="primaryResidenceAddress.state" /></td>
							 	<td class="formDe">
							 		<bean:write name="associateForm" property="primaryResidenceAddress.zipCode" />
							 		<logic:notEqual name="associateForm" property="primaryResidenceAddress.additionalZipCode" value="">
							 			-&nbsp;<bean:write name="associateForm" property="primaryResidenceAddress.additionalZipCode" />
							 		</logic:notEqual>
							 	</td>
							</tr>
							<tr>
								<td class="formDeLabel" colspan="3"><bean:message key="prompt.addressComplexName"/></td>
							</tr>
							<tr>
								<td class="formDe" colspan="3"><bean:write name="associateForm" property="primaryResidenceAddress.addressComplexName" /></td>
							</tr>
							<tr>
								<td class="formDeLabel" colspan="3"><bean:message key="prompt.county"/></td>
							</tr>
							<tr>
								<td class="formDe" colspan="3"><bean:write name="associateForm" property="primaryResidenceAddress.county" /></td>
							</tr>
						</table>
					</td>
				</tr>
<!-- END RESIDENCE SECTION -->
				<tr>
					<td>&nbsp;</td>
				</tr>
<!-- BEGIN OTHER SECTION -->
				<tr>
					<td class="detailHead" colspan="3" >
						<table width="100%" border="0" cellpadding="2" cellspacing="1">
							<tr>
								<td class="detailHead" nowrap><bean:message key="prompt.otherAddress"/></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<table border="0" cellpadding=4 cellspacing=1 width=100%>
							<tr>
								<td class="formDeLabel" width="45%"><bean:message key="prompt.streetNum"/></td>
								<td class="formDeLabel" colspan="2"><bean:message key="prompt.streetName"/></td>
							</tr>
							<tr>
								<td class="formDe"><bean:write name="associateForm" property="otherAddress.streetNumber" /></td>
								<td class="formDe" colspan="2"><bean:write name="associateForm" property="otherAddress.streetName" /></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.streetType"/></td>
								<td nowrap class="formDeLabel" colspan="2" ><bean:message key="prompt.aptSuite"/></td>
							</tr>
							<tr>
								<td class="formDe"><bean:write name="associateForm" property="otherAddress.streetType" /></td>
								<td class="formDe" colspan="2"><bean:write name="associateForm" property="otherAddress.aptNumber" /></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.city"/></td>
								<td class="formDeLabel"><bean:message key="prompt.state"/></td>
								<td class="formDeLabel"><bean:message key="prompt.zipCode"/></td>
							</tr>
							<tr>
								<td class="formDe"><bean:write name="associateForm" property="otherAddress.city" /></td>
								<td class="formDe"><bean:write name="associateForm" property="otherAddress.state" /></td>
								<td class="formDe">
									<bean:write name="associateForm" property="otherAddress.zipCode" />
									<logic:notEqual name="associateForm" property="otherAddress.additionalZipCode" value="">
										-&nbsp;<bean:write name="associateForm" property="otherAddress.additionalZipCode" />
									</logic:notEqual>	
								</td>
							</tr>
							<tr>
								<td class="formDeLabel" colspan="3"><bean:message key="prompt.addressComplexName"/></td>
							</tr>
							<tr>
								<td class="formDe" colspan="3"><bean:write name="associateForm" property="otherAddress.addressComplexName" /></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.addressType"/></td>
								<td class="formDeLabel" colspan="2"><bean:message key="prompt.county"/></td>
							</tr>
							<tr>
								<td class="formDe"><bean:write name="associateForm" property="otherAddress.addressType" /></td>
								<td class="formDe" colspan="2"><bean:write name="associateForm" property="otherAddress.county" /></td>
							</tr>
						</table>
					</td>
				</tr>
<!-- END OTHER SECTION -->				
			</table>
<!-- END ADDRESS TABLE -->	
<!-- END BUTTON TABLE -->	
			<table width="100%" border="0" cellpadding="2" cellspacing="1">
				<tr>
					<td align="center">
						<input type="button" name="close" value="Close Window" onclick="window.close()") >
					</td>   
				</tr>
			</table>	
<!-- END BUTTON TABLE -->
		</td>
	</tr>
</table>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>