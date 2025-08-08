<!DOCTYPE HTML>
<!-- 08/22/2005	 Hien Rodriguez - Create JSP -->
<!-- 01/10/2007  C Shimek       - #38306 add multiple submit functionality  -->
<!-- 02/05/2009  C Shimek       - #56860 add Back to Top  -->

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
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading" /> - departmentSummary.jsp</title>
<!-- APP JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head>
<!--END HEADER TAG-->

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<!-- BEGIN HEADING TABLE -->
<table width="98%" align="center">
	<tr>
		<td align="center" class="header">
		<logic:equal name="departmentForm" property="action" value="deptView">
			<bean:message key="title.departmentDetails" />
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="deptCreate">
			<bean:message key="title.departmentCreate" />&nbsp;<bean:message key="prompt.summary" />		
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="deptUpdate">
			<bean:message key="title.departmentUpdate" />&nbsp;<bean:message key="prompt.summary" />	
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="deptDelete">
			<bean:message key="title.departmentDelete" />&nbsp;<bean:message key="prompt.summary" />	
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="deptCopy">
			<bean:message key="title.departmentCopy" />&nbsp;<bean:message key="prompt.summary" />	
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="confirmCreate">
			<bean:message key="title.departmentCreate" />&nbsp;<bean:message key="prompt.confirmation" />
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="confirmUpdate">
			<bean:message key="title.departmentUpdate" />&nbsp;<bean:message key="prompt.confirmation" />
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="confirmDelete">
			<bean:message key="title.departmentDelete" />&nbsp;<bean:message key="prompt.confirmation" />
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="confirmCopy">
			<bean:message key="title.departmentCopy" />&nbsp;<bean:message key="prompt.confirmation" />
		</logic:equal>
		</td>
	</tr>
</table>
<!-- END HEADING TABLE -->
<br>
<!-- BEGIN ERROR TABLE -->
	<table width="98%" align="center">
		<TBODY>
			<tr>
				<td align="center" class="errorAlert"><html:errors></html:errors></td>
			</tr>
		</TBODY>
	</table>
<!-- END ERROR TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0" align="center">
	<tr>
		<logic:equal name="departmentForm" property="action" value="deptView">
			<td>
			<ul>
				<li>Select the Update button to update this department/contact(s).</li>
				<li>Select the Delete button to delete this department/contact(s).</li>
				<li>Select the Copy button to copy this department/contact(s).</li>
				<li>Select the Create Contact button to create additional contact for this department.</li>
				<li>Select the Modify Contact button to update/delete contact for this department.</li>
			</ul>
			</td>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="deptCreate">
			<td>
			<ul>
				<li>The following department will be created when you select the Finish button.</li>
			</ul>
			</td>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="deptUpdate">
			<td>
			<ul>
				<li>The following department will be updated when you select the Finish button.</li>
			</ul>
			</td>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="deptDelete">
			<td>
			<ul>
				<li>The following department will be deleted when you select the Finish button.</li>
			</ul>
			</td>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="deptCopy">
			<td>
			<ul>
				<li>The following department will be copied when you select the Finish button.</li>
			</ul>
			</td>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="confirmCreate">
			<td align="center" class="confirm">This department has been successfully created.</td>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="confirmUpdate">
			<td align="center" class="confirm">This department has been successfully updated.</td>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="confirmDelete">
			<td align="center" class="confirm">This department has been successfully deleted.</td>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="confirmCopy">
			<td align="center" class="confirm">This department has been successfully copied.</td>
		</logic:equal>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN DETAIL TABLE -->
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td>
		<table width="100%" border="0" cellspacing="1" cellpadding="4" class="borderTableBlue">
			<!-- BEGIN GENERAL INFORMATION SECTION -->
			<tr>
				<td colspan="4" class="detailHead">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td class="detailHead"><bean:message key="prompt.generalInfo" /></td>
						<td align="right"><img src="../../images/step_1.gif"></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.agencyName" /></td>
				<td class="formDe" colspan="3"><bean:write name="departmentForm" property="agencyName" /></td>
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.departmentName" /></td>
				<td class="formDe" colspan="3"><bean:write name="departmentForm" property="departmentName" /></td>
			</tr>
			<tr>
				<td class="formDeLabel" nowrap><bean:message key="prompt.departmentCode" /></td>
				<td class="formDe"><bean:write name="departmentForm" property="departmentId" /></td>
				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.organizationCode" /></td>
				<td class="formDe"><bean:write name="departmentForm" property="orgCode" /></td>
			</tr>
			<tr>
				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.originatingAgencyId" /></td>
				<td class="formDe" colspan="3"><bean:write name="departmentForm" property="originatingAgencyId" /></td>
			</tr>
			<tr>
				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.allowOfficerProfileCreation" /></td>
				<td class="formDe" colspan="3">
					<logic:equal name="departmentForm" property="createOfficerProfileInd" value="Y" >
						YES
					</logic:equal>
					<logic:equal name="departmentForm" property="createOfficerProfileInd" value="N" >
						NO
					</logic:equal>
				</td>
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.departmentStatus" /></td>
				<td class="formDe"  colspan="3"><bean:write name="departmentForm" property="status" /></td>
			</tr>
			<tr>	
				<td class="formDeLabel"><bean:message key="prompt.accessType" /></td>
				<td class="formDe" colspan="3"><bean:write name="departmentForm" property="accessType" /></td>			
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.faxNumber" /></td>
				<td class="formDe" colspan="3"><bean:write name="departmentForm" property="departmentFaxNumber.formattedPhoneNumber" /></td>
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.JIMSActivationDate" /></td>
				<td class="formDe" colspan="3"><bean:write name="departmentForm" property="activationDate" /></td>
			</tr>
			<tr>	
				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.JIMSInactiveDate" /></td>
				<td class="formDe" colspan="3"><bean:write name="departmentForm" property="terminationDate" /></td>
			</tr>
			<!-- END GENERAL INFORMATION SECTION -->
			<tr>
				<td colspan="4"><br>
				</td>
			</tr>
			<!-- this block should only display if agency type = subscriber -->
			<!-- BEGIN SUBSCRIBER ACCESS INFORMATION SECTION -->
			<logic:equal name="departmentForm" property="agencyTypeId" value="S">
			<tr>
				<td colspan="4" class="detailHead">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td class="detailHead"><bean:message key="prompt.subscriberAccessInfo" /></td>
						<td align="right"><img src="../../images/step_2.gif"></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td class="subhead" bgcolor="#999999" colspan="2" align="center">
					<bean:message key="prompt.civil" />
				</td>
				<td class="subhead" bgcolor="#999999" colspan="2" align="center">
					<bean:message key="prompt.criminal" />
				</td>
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.activeDate" /></td>
				<td class="formDe"><bean:write name="departmentForm" property="subscriberCivilActivationDate" /></td>
				<td class="formDeLabel"><bean:message key="prompt.activeDate" /></td>
				<td class="formDe"><bean:write name="departmentForm" property="subscriberCriminalActivationDate" /></td>
			</tr>
			<tr>
				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inactiveDate" /></td>
				<td class="formDe"><bean:write name="departmentForm" property="subscriberCivilTerminationDate" /></td>
				<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inactiveDate" /></td>
				<td class="formDe"><bean:write name="departmentForm" property="subscriberCriminalTerminationDate" /></td>
			</tr>
			<!-- END SUBSCRIBER ACCESS INFORMATION SECTION -->
			<tr>
				<td colspan="4"><br>
				</td>
			</tr>
			</logic:equal>
			<!-- BEGIN PHYSICAL ADDRESS SECTION -->
			<tr>
				<td colspan="4" class="detailHead">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td class="detailHead"><bean:message key="prompt.physicalAddress" /></td>
						<td align="right">
							<logic:equal name="departmentForm" property="agencyTypeId" value="N">
								<img src="../../images/step_2.gif">
							</logic:equal>	
							<logic:equal name="departmentForm" property="agencyTypeId" value="S">						
								<img src="../../images/step_3.gif">
							</logic:equal>	
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
				<table border="0" cellspacing="1" cellpadding="4" width="100%">
					<tr class="formDeLabel">
						<td width="25%"><bean:message key="prompt.streetNumber" /></td>
						<td width="35%"><bean:message key="prompt.streetName" /></td>
						<td width="25%"><bean:message key="prompt.streetType" /></td>
						<td width="15%"><bean:message key="prompt.aptSuite" /></td>
					</tr>
					<tr class="formDe">
						<td><bean:write name="departmentForm" property="physicalAddress.streetNumber" /></td>
						<td><bean:write name="departmentForm" property="physicalAddress.streetName" /></td>
						<td><bean:write name="departmentForm" property="physicalAddress.streetType" /></td>
						<td><bean:write name="departmentForm" property="physicalAddress.aptNumber" />&nbsp;</td>
					</tr>
					<tr class="formDeLabel">
						<td><bean:message key="prompt.city" /></td>
						<td><bean:message key="prompt.state" /></td>
						<td colspan="3"><bean:message key="prompt.zipCode" /></td>
					</tr>
					<tr class="formDe">
						<td><bean:write name="departmentForm" property="physicalAddress.city" /></td>
						<td><bean:write name="departmentForm" property="physicalAddress.state" /></td>
						<td colspan="3"><bean:write name="departmentForm"
							property="physicalAddress.zipCode" /><logic:notEmpty name="departmentForm"
							property="physicalAddress.additionalZipCode"> -<bean:write name="departmentForm"
							property="physicalAddress.additionalZipCode" /></logic:notEmpty>&nbsp;</td>
					</tr>
				</table>
				</td> 
			</tr>
			<!-- END PHYSICAL ADDRESS SECTION -->
			<tr>
				<td colspan="4"><br>
				</td>
			</tr>
			<!-- BEGIN MAILING ADDRESS SECTION -->
			<tr>
				<td colspan="4" class="detailHead">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td class="detailHead"><bean:message key="prompt.mailingAddress" /></td>
						<td align="right">
							<logic:equal name="departmentForm" property="agencyTypeId" value="N">
								<img src="../../images/step_3.gif">
							</logic:equal>	
							<logic:equal name="departmentForm" property="agencyTypeId" value="S">						
								<img src="../../images/step_4.gif">
							</logic:equal>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
				<table border="0" cellspacing="1" cellpadding="4" width="100%">
					<tr class="formDeLabel">
						<td width="25%"><bean:message key="prompt.streetNumber" /></td>
						<td width="35%"><bean:message key="prompt.streetName" /></td>
						<td width="25%"><bean:message key="prompt.streetType" /></td>
						<td width="15%"><bean:message key="prompt.aptSuite" /></td>
					</tr>
					<tr class="formDe">
						<td class="formDe"><bean:write name="departmentForm" property="mailingAddress.streetNumber" /></td>
						<td class="formDe"><bean:write name="departmentForm" property="mailingAddress.streetName" /></td>
						<td class="formDe"><bean:write name="departmentForm" property="mailingAddress.streetType" /></td>
						<td class="formDe"><bean:write name="departmentForm" property="mailingAddress.aptNumber" />&nbsp;</td>
					</tr>
					<tr class="formDeLabel">
						<td><bean:message key="prompt.city" /></td>
						<td><bean:message key="prompt.state" /></td>
						<td colspan="2"><bean:message key="prompt.zipCode" /></td>
					</tr>
					<tr class="formDe">
						<td class="formDe"><bean:write name="departmentForm" property="mailingAddress.city" /></td>
						<td class="formDe"><bean:write name="departmentForm" property="mailingAddress.state" /></td>
						<td class="formDe" colspan="2">
							<bean:write name="departmentForm"property="mailingAddress.zipCode" />
							<logic:notEmpty name="departmentForm" property="mailingAddress.additionalZipCode">
								-<bean:write name="departmentForm" 	property="mailingAddress.additionalZipCode" />
							</logic:notEmpty>&nbsp;
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<!-- END MAILING ADDRESS SECTION -->
			<tr>
				<td colspan="4"><br>
				</td>
			</tr>
			<!-- BEGIN CONTACT INFORMATION SECTION -->
			<tr>
				<td colspan="4" class="detailHead">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td class="detailHead"><bean:message key="prompt.contactInfo" /></td>
						<td align="right">
							<logic:equal name="departmentForm" property="agencyTypeId" value="N">
								<img src="../../images/step_4.gif">
							</logic:equal>	
							<logic:equal name="departmentForm" property="agencyTypeId" value="S">						
								<img src="../../images/step_5.gif">
							</logic:equal>
						</td>						
					</tr>
				</table>
				</td>
			</tr>
			<logic:empty name="departmentForm" property="contactList">
				<tr><td class="formDe" colspan="4"><br></td></tr>
				<tr><td colspan="4"><br></td></tr>
			</logic:empty>
			<logic:iterate id="contactIndex" name="departmentForm" property="contactList">
				<logic:notEqual name="contactIndex" property="deletable" value="true">
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.name" /></td>
					<td class="formDe" colspan="3"><bean:write name="contactIndex"
						property="lastName" />, <bean:write name="contactIndex"
						property="firstName" />&nbsp;<bean:write name="contactIndex"
						property="middleName" /></td>
				</tr>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.userId" /></td>
					<td class="formDe" colspan="3"><bean:write name="contactIndex"
						property="logonId" /></td>
				</tr>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.jobTitle" /></td>
					<td class="formDe" colspan="3"><bean:write name="contactIndex" property="title" /></td>
				</tr>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.phone" /></td>
					<td class="formDe" colspan="3"><bean:write name="contactIndex" property="formattedPhone" />&nbsp;&nbsp;
						<logic:notEmpty name="contactIndex" property="phoneExt">
							<b><bean:message key="prompt.ext" /></b>
								<bean:write name="contactIndex" property="phoneExt" />
						</logic:notEmpty>
					</td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap><bean:message key="prompt.primaryContact"/></td>
					<td class="formDe"><bean:write name="contactIndex" property="primaryContactAsYesNo" /></td>
					<td class="formDeLabel"><bean:message key="prompt.liaisonTraining" /></td>
					<td class="formDe"><bean:write name="contactIndex" property="liaisonTraining" /></td>
				</tr>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.email" /></td>
					<td class="formDe" colspan="3"><bean:write name="contactIndex" property="email" /></td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				</logic:notEqual>
			</logic:iterate>
			<!-- END CONTACT INFORMATION SECTION -->

			<!-- BEGIN SETCIC INFORMATION SECTION -->
			<logic:equal name="departmentForm" property="agencyTypeId" value="N">
			<tr>
				<td colspan="4" class="detailHead">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td class="detailHead"><bean:message key="prompt.setcicInfo" /></td>
						<td align="right"><img src="../../images/step_5.gif"></td>						
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.access" /></td>
				<td class="formDe" colspan="3"><bean:write name="departmentForm"
					property="setcicAccess" /></td>
			</tr>
			<tr>
				<td class="formDeLabel" valign="top" nowrap><bean:message key="prompt.name" /></td>
				<td class="formDe" colspan="3"><bean:write name="departmentForm" property="setcicContactName.formattedName" /></td>
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.contactPhone" /></td>
				<td class="formDe" colspan="3"><bean:write name="departmentForm"
					property="setcicPhoneNumber.formattedPhoneNumber" />&nbsp;&nbsp;
					<logic:notEmpty name="departmentForm" property="setcicPhoneNumber.ext">
					<b><bean:message key="prompt.ext" /></b>&nbsp;<bean:write
					name="departmentForm" property="setcicPhoneNumber.ext" /></logic:notEmpty></td>
			</tr>
			<tr>
				<td class="formDeLabel" width="1%" nowrap><bean:message
					key="prompt.warrantContactPhone" /></td>
				<td class="formDe" colspan="3"><bean:write name="departmentForm"
					property="warrantConfPhoneNumber.formattedPhoneNumber" />&nbsp;&nbsp;
					<logic:notEmpty name="departmentForm" property="warrantConfPhoneNumber.ext">
					<b><bean:message
					key="prompt.ext" /></b>&nbsp;<bean:write name="departmentForm"
					property="warrantConfPhoneNumber.ext" /></logic:notEmpty></td>
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.activeDate" /></td>
				<td class="formDe"><bean:write name="departmentForm" property="setcicDate" /></td>
				<td class="formDeLabel"><bean:message key="prompt.inactiveDate" /></td>
				<td class="formDe"><bean:write name="departmentForm" property="setcicInactiveDate" /></td>
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.renewDate" /></td>
				<td class="formDe"><bean:write name="departmentForm" property="setcicRenewDate" /></td>
				<td class="formDeLabel"><bean:message key="prompt.county" /></td>
				<td class="formDe"><bean:write name="departmentForm" property="county" /></td>
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.gritsAccess" /></td>
				<td class="formDe" colspan="3"><bean:write name="departmentForm" property="grits" /></td>
			</tr>

			<tr>
				<td class="subhead" bgcolor="#999999" colspan="4"><bean:message key="prompt.billingAddress" /></td>
			</tr>
			<tr>
				<td colspan="4">
				<table border="0" cellspacing="1" cellpadding="4" width="100%">
					<tr class="formDeLabel">
						<td width="25%"><bean:message key="prompt.streetNumber" /></td>
						<td width="35%"><bean:message key="prompt.streetName" /></td>
						<td width="25%"><bean:message key="prompt.streetType" /></td>
						<td width="15%"><bean:message key="prompt.aptSuite" /></td>
					</tr>
					<tr class="formDe">
						<td class="formDe"><bean:write name="departmentForm" property="setcicBillingAddress.streetNumber" /></td>
						<td class="formDe"><bean:write name="departmentForm" property="setcicBillingAddress.streetName" /></td>
						<td class="formDe"><bean:write name="departmentForm" property="setcicBillingAddress.streetType" /></td>
						<td class="formDe"><bean:write name="departmentForm" property="setcicBillingAddress.aptNumber" />&nbsp;</td>
					</tr>
					<tr class="formDeLabel">
						<td><bean:message key="prompt.city" /></td>
						<td><bean:message key="prompt.state" /></td>
						<td><bean:message key="prompt.zipCode" /></td>
						<td><bean:message key="prompt.label" /></td>
					</tr>
					<tr class="formDe">
						<td class="formDe"><bean:write name="departmentForm" property="setcicBillingAddress.city" /></td>
						<td class="formDe"><bean:write name="departmentForm" property="setcicBillingAddress.state" /></td>
						<td class="formDe"><bean:write name="departmentForm" property="setcicBillingAddress.zipCode" /><logic:notEmpty name="departmentForm"
							property="setcicBillingAddress.additionalZipCode">-<bean:write name="departmentForm"
							property="setcicBillingAddress.additionalZipCode" /></logic:notEmpty>&nbsp;</td>
						<td class="formDe"><bean:write name="departmentForm" property="setcicBillingLabel" /></td>
					</tr>
				</table>
				</td>
			</tr>
			</logic:equal>
			<!-- END SETCIC INFORMATION SECTION -->
			<tr>
				<td colspan="4"></td>
			</tr>
			<!-- BEGIN COMMENTS SECTION -->
			<tr>
				<td colspan="4" class="detailHead">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td class="detailHead"><bean:message key="prompt.comments" /></td>
						<logic:notEqual name="departmentForm" property="agencyTypeId" value="N">										
							<td align="right"><img src="../../images/step_6.gif"></td>
						</logic:notEqual>				
						<logic:equal name="departmentForm" property="agencyTypeId" value="N">										
							<td align="right"><img src="../../images/step_6.gif"></td>
						</logic:equal>	
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td class="formDe" colspan="4">
					<logic:equal name="departmentForm" property="comments" value="">
						&nbsp;
					</logic:equal>
					<bean:write name="departmentForm" property="comments" />
				</td>
			</tr>
			<!-- END COMMENTS SECTION -->
		</table>
		</td>
	</tr>
</table>
<!-- END DETAIL TABLE -->
<br>
<!-- BEGIN BUTTON TABLE -->
<table width="98%" border="0">
	<tr>
		<td align="center"> <logic:equal
			name="departmentForm" property="action" value="deptView">
			<html:form action="/departmentDisplayDetails">
			<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|104">	
				<html:button property="org.apache.struts.taglib.html.BUTTON"
					onclick="history.go(-1);">
					<bean:message key="button.back"></bean:message>
				</html:button>
				<html:submit property="submitAction">
					<bean:message key="button.update" />
				</html:submit>
				<logic:equal name="departmentForm" property="inUse" value="false">
				<html:submit property="submitAction">
					<bean:message key="button.delete" />
				</html:submit>
				</logic:equal>
				<html:submit property="submitAction">
					<bean:message key="button.copy" />
				</html:submit>
				<logic:equal name="departmentForm" property="statusId" value="A">
				<html:submit property="submitAction">
					<bean:message key="button.createContact" />
				</html:submit>
				<html:submit property="submitAction">
					<bean:message key="button.modifyContact" />
				</html:submit>
				</logic:equal>
				<html:submit property="submitAction">
					<bean:message key="button.cancel" />
				</html:submit>
			</html:form>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="deptCreate">
			<html:form action="/departmentSubmitSummary">
			<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|49">	
				<html:button property="org.apache.struts.taglib.html.BUTTON"
					onclick="history.go(-1);">
					<bean:message key="button.back"></bean:message>
				</html:button>
				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
					<bean:message key="button.finish" />
				</html:submit>
				<html:submit property="submitAction">
					<bean:message key="button.cancel" />
				</html:submit>
			</html:form>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="deptCopy">
			<html:form action="/departmentSubmitSummary">
			<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|37">	
				<html:button property="org.apache.struts.taglib.html.BUTTON"
					onclick="history.go(-1);">
					<bean:message key="button.back"></bean:message>
				</html:button>
				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
					<bean:message key="button.finish" />
				</html:submit>
				<html:submit property="submitAction">
					<bean:message key="button.cancel" />
				</html:submit>
			</html:form>
		</logic:equal>
	    <logic:equal name="departmentForm" property="action" value="deptUpdate">
			<html:form action="/departmentSubmitSummary">
			<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|267">	
				<html:button property="org.apache.struts.taglib.html.BUTTON"
					onclick="history.go(-1);">
					<bean:message key="button.back"></bean:message>
				</html:button>
				
				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
					<bean:message key="button.finish" />
				</html:submit>
				<html:submit property="submitAction">
					<bean:message key="button.cancel" />
				</html:submit>
			</html:form>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="deptDelete">
			<html:form action="/departmentSubmitSummary">
				<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|86">	
				<html:button property="org.apache.struts.taglib.html.BUTTON"
					onclick="history.go(-1);">
					<bean:message key="button.back"></bean:message>
				</html:button>
				<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
					<bean:message key="button.finish" />
				</html:submit>
				<html:submit property="submitAction">
					<bean:message key="button.cancel" />
				</html:submit>
			</html:form>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="confirmCreate">
			<html:form action="/departmentSubmitSummary">
			<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|47">	
				<html:submit property="submitAction">
					<bean:message key="button.mainPage" />
				</html:submit>
			</html:form>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="confirmUpdate">
			<form id="uniqueCon" name="departmentForm" action="/<msp:webapp/>departmentSubmitSummary.do" method="post">
			<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|268">		
				<html:submit property="submitAction">
					<bean:message key="button.mainPage" />
				</html:submit>
				<input type="submit" value="<bean:message key='button.createContact'/>"
					name="submitAction"
					onclick="javascript:changeFormActionURL('departmentForm', '/<msp:webapp/>departmentDisplayDetails.do?action=contactCreate', false)"/>
			</form>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="confirmCopy">
			<html:form action="departmentSubmitSummary" >
			<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|36">	
				<html:submit property="submitAction">
					<bean:message key="button.mainPage" />
				</html:submit>
				<input type="submit"
						value="<bean:message key='button.createContact'/>"
						name="submitAction"
						onclick="javascript:changeFormActionURL('departmentForm', '/<msp:webapp/>departmentDisplayDetails.do?action=contactCreate', false)"/>
				
			</html:form>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="confirmDelete">
			<html:form action="/departmentSubmitSummary">
			<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|266">	
				<html:submit property="submitAction">
					<bean:message key="button.mainPage" />
				</html:submit>
			</html:form>
		</logic:equal></td>
	</tr>
</table>
<!--END BUTTON TABLE-->

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
