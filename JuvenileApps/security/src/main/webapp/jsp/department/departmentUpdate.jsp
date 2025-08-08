<!DOCTYPE HTML>
<!-- 08/29/2005	 Hien Rodriguez - Create JSP -->
<!-- 08/02/2006  CShimek	- defect#33828 add Allow Officier Profile Creation field (lost during merge with Maint MSA)-->
<!-- 01/10/2007  CShimek    - #38306 add multiple submit functionality  -->
<!-- 03/30/2007  CShimek	- #40508 added call for checksubscriberInactiveDates on next button -->
<!-- 07/06/2007  CShimek    - #43456 replaced casework_uitl.js as all needed functions are in app.js  -->
<!-- 02/05/2009  CShimek    - #56860 add Back to Top  -->

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
<title><bean:message key="title.heading" /> - departmentUpdate.jsp</title>
<!-- JAVASCRIPT FILES -->
<html:javascript formName="departmentUpdateForm"/>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/department/departmentUpdate.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script language="javascript">
function showMailing(el){
	if (el.checked){
		document.forms[0]["mailingAddress.streetNumber"].value = document.forms[0]["physicalAddress.streetNumber"].value;
		document.forms[0]["mailingAddress.streetName"].value = document.forms[0]["physicalAddress.streetName"].value;
		document.forms[0]["mailingAddress.streetTypeId"].value = document.forms[0]["physicalAddress.streetTypeId"].value;
		document.forms[0]["mailingAddress.aptNumber"].value = document.forms[0]["physicalAddress.aptNumber"].value;
		document.forms[0]["mailingAddress.city"].value = document.forms[0]["physicalAddress.city"].value;
		document.forms[0]["mailingAddress.stateId"].value = document.forms[0]["physicalAddress.stateId"].value;
		document.forms[0]["mailingAddress.zipCode"].value = document.forms[0]["physicalAddress.zipCode"].value;
		document.forms[0]["mailingAddress.additionalZipCode"].value = document.forms[0]["physicalAddress.additionalZipCode"].value;		
	    show('mailing1', 0,"row" );	
	}
	else{
		document.forms[0]["mailingAddress.streetNumber"].value = "";
		document.forms[0]["mailingAddress.streetName"].value = "";
		document.forms[0].elements["mailingAddress.streetTypeId"].selectedIndex = -1;
		document.forms[0]["mailingAddress.streetTypeId"].value = "";
		document.forms[0]["mailingAddress.aptNumber"].value = "";
		document.forms[0]["mailingAddress.city"].value = "";
		document.forms[0].elements["mailingAddress.stateId"].selectedIndex = -1;
		document.forms[0]["mailingAddress.stateId"].value = "";
		document.forms[0]["mailingAddress.zipCode"].value = "";
		document.forms[0]["mailingAddress.additionalZipCode"].value = "";
		show('mailing1', 1,"row");
		}
}

function showBilling(el){
	if (el.checked){
		document.forms[0]["setcicBillingAddress.streetNumber"].value = document.forms[0]["physicalAddress.streetNumber"].value;
		document.forms[0]["setcicBillingAddress.streetName"].value = document.forms[0]["physicalAddress.streetName"].value;
		document.forms[0]["setcicBillingAddress.streetTypeId"].value = document.forms[0]["physicalAddress.streetTypeId"].value;
		document.forms[0]["setcicBillingAddress.aptNumber"].value = document.forms[0]["physicalAddress.aptNumber"].value;
		document.forms[0]["setcicBillingAddress.city"].value = document.forms[0]["physicalAddress.city"].value;
		document.forms[0]["setcicBillingAddress.stateId"].value = document.forms[0]["physicalAddress.stateId"].value;
		document.forms[0]["setcicBillingAddress.zipCode"].value = document.forms[0]["physicalAddress.zipCode"].value;
		document.forms[0]["setcicBillingAddress.additionalZipCode"].value = document.forms[0]["physicalAddress.additionalZipCode"].value;		
	    show('billing1', 0,"row" );	
	}
	else{
		  document.forms[0]["setcicBillingAddress.streetNumber"].value = "";
		document.forms[0]["setcicBillingAddress.streetName"].value = "";
		document.forms[0]["setcicBillingAddress.streetTypeId"].selectedIndex = -1;
		document.forms[0]["setcicBillingAddress.streetTypeId"].value = "";
		document.forms[0]["setcicBillingAddress.aptNumber"].value = "";
		document.forms[0]["setcicBillingAddress.city"].value = "";
		document.forms[0]["setcicBillingAddress.stateId"].selectedIndex = -1;
		document.forms[0]["setcicBillingAddress.stateId"].value = "";
		document.forms[0]["setcicBillingAddress.zipCode"].value = "";
		document.forms[0]["setcicBillingAddress.additionalZipCode"].value = "";	
		 show('billing1', 1,"row");
	    }
}

</script>

</head>
<!--END HEADER TAG-->

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/departmentDisplaySummary" target="content" focus="departmentName">
<logic:equal name="departmentForm" property="action" value="deptUpdate">
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|218">	
</logic:equal>	
<logic:equal name="departmentForm" property="action" value="deptCopy">
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|35">	
</logic:equal>	
	<!-- BEGIN HEADING TABLE -->
	<table width="98%">
		<TBODY>
			<tr>
				<td align="center" class="header"><logic:equal name="departmentForm" property="action" value="deptUpdate">
					<bean:message key="title.departmentUpdate" />
				</logic:equal> <logic:equal name="departmentForm" property="action" value="deptCopy">
					<bean:message key="title.departmentCopy" />
				</logic:equal></td>
			</tr>
		</TBODY>
	</table>
	<!-- END HEADING TABLE -->
	<!-- BEGIN ERROR TABLE -->
	<table width="98%" align="center">
		<TBODY>
			<tr>
				<td align="center" class="errorAlert"><html:errors></html:errors></td>
			</tr>
		</TBODY>
	</table>
	<!-- END ERROR TABLE -->
	<!-- INSTRUCTION TABLE -->
	<table width="98%">
		<TBODY>
			<tr>
				<logic:equal name="departmentForm" property="action" value="deptUpdate">
					<td>
					<ul>
						<li>Enter any required changes, then select Next button to view summary.</li>
          				<li>Either a <b>Physical</b> or <b>Mailing</b> address is required.</li>
					</ul>
					</td>
				</logic:equal>
				<logic:equal name="departmentForm" property="action" value="deptCopy">
					<td>
					<ul>
						<li>Enter information and select Next button to create New department.</li>
					</ul>
					</td>
				</logic:equal>
			</tr>
			<tr>
				<td class="required"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.requiredFieldsInstruction" /></td>
			</tr>
			<tr>
				<td class="required"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" />Required if SETCIC Access is Inquiry or Full.</td>
			</tr>
			<tr>
				<td class="required"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" />Required if SETCIC Access is Full.</td>
			</tr>
			<tr>
			    <td class="required"><bean:message key="prompt.dateFieldsInstruction" /></td>
		    </tr>
		</TBODY>
	</table>
	<!-- BEGIN DETAIL TABLE -->
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<TBODY>
			<tr>
				<td>
				<SCRIPT LANGUAGE="JavaScript" ID="js1">
					var cal1 = new CalendarPopup();
					cal1.showYearNavigation();
				</SCRIPT>
				<table width="100%" border="0" cellspacing="1" cellpadding="2" class="borderTableBlue">
					<!-- BEGIN GENERAL INFORMATION SECTION -->
					<TBODY>
						<tr>
							<td colspan="4" class="detailHead">
							<table width="100%" cellpadding="0" cellspacing="0">
								<TBODY>
									<tr>
										<td class="detailHead"><bean:message key="prompt.generalInfo" /></td>
										<td align="right"><img src="../../images/step_1.gif"></td>
									</tr>
								</TBODY>
							</table>
							</td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.agencyName" /></td>
							<td class="formDe" colspan="3"><bean:write name="departmentForm" property="agencyName" /></td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.departmentName" /></td>
							<td class="formDe" colspan="3"><html:text property="departmentName" size="60" maxlength="60" /></td>
						</tr>
						<tr>
							<td class="formDeLabel" nowrap><bean:message key="prompt.2.diamond" /><bean:message key="prompt.departmentCode" /></td>
							<td class="formDe">
								<logic:equal name="departmentForm" property="action" value="deptUpdate">
									<bean:write name="departmentForm"	property="departmentId" />&nbsp;
								</logic:equal>
								<logic:notEqual name="departmentForm" property="action" value="deptUpdate">
									<html:text name="departmentForm" property="departmentId" size="5" maxlength="3"/>&nbsp;
								</logic:notEqual>
 						</td>
							<td class="formDeLabel" width="1%" nowrap><bean:message
								key="prompt.2.diamond" /><bean:message key="prompt.organizationCode" />
							</td>
							<td class="formDe"><html:text property="orgCode" size="3"maxlength="3" /></td>
						</tr>
						<tr>
							<td class="formDeLabel" nowrap><bean:message key="prompt.originatingAgencyId" /></td>
							<td class="formDe" colspan="3"><html:text property="originatingAgencyId" size="9" maxlength="9" /></td>
						</tr>
						<tr>
							<td class="formDeLabel" nowrap><bean:message key="prompt.allowOfficerProfileCreation" /></td>
							<td class="formDe" colspan="3">
								<html:radio name="departmentForm" property="createOfficerProfileInd" value="Y" /><bean:message key="prompt.yes" /> 
								<html:radio name="departmentForm" property="createOfficerProfileInd" value="N" /><bean:message key="prompt.no" /> 								
							</td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.accessType" /></td>
							<td class="formDe" colspan="3"><html:select property="accessTypeId">
								<html:option key="select.generic" value="" />
								<html:optionsCollection property="accessTypes" value="code" label="description" /></html:select>
							</td>
						</tr>
						
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.departmentStatus" /></td>
							<td class="formDe" colspan="3"><html:select property="statusId">
								<html:option key="select.generic" value="" />
								<html:optionsCollection property="statusTypes" value="code" label="description" /></html:select>
							</td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.faxNumber" /></td>
							<td class="formDe" colspan="3"><html:text property="departmentFaxNumber.areaCode"
								size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> - <html:text property="departmentFaxNumber.prefix"
								size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> - <html:text property="departmentFaxNumber.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/>
							</td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.JIMSActivationDate" /></td>
							<td class="formDe" colspan="3"><html:text	property="activationDate" size="10" maxlength="10" />
							    <A HREF="#" onClick="cal1.select(document.departmentForm.activationDate,'anchor1','MM/dd/yyyy'); return false;"
								   NAME="anchor1" ID="anchor1"><bean:message key="prompt.2.calendar" /></A></td>
						</tr>
						<tr>
							<td class="formDeLabel" nowrap><bean:message key="prompt.JIMSInactiveDate" /></td>
							<td class="formDe" colspan="3"><html:text	property="terminationDate" size="10" maxlength="10" /> 
							    <A HREF="#" onClick="cal1.select(document.departmentForm.terminationDate,'anchor2','MM/dd/yyyy'); return false;"
								   NAME="anchor2" ID="anchor2"><bean:message key="prompt.2.calendar" /></A></td>
						</tr>
						<!-- END GENERAL INFORMATION SECTION -->
						<tr>
							<td colspan="4"><br></td>
						</tr>
						<!-- BEGIN SUBSCRIBER ACCESS INFORMATION SECTION -->
<%-- if you delete these hidden subscriber date fields, please change the edit in validateDepartmentUpdateFields() in departmentUpdate.js. 
	 They are also used to determine if dept is subscriber and edit accordingly --%>						
						<logic:equal name="departmentForm" property="agencyTypeId" value="N">
							<tr>
								<td><html:hidden property="subscriberCivilActivationDate" value="" /></td>
								<td><html:hidden property="subscriberCriminalActivationDate" value="" /></td>
								<td><html:hidden property="subscriberCivilTerminationDate" value="" /></td>
								<td><html:hidden property="subscriberCriminalTerminationDate" value="" /></td>
							</tr>
						</logic:equal>
						<logic:equal name="departmentForm" property="agencyTypeId" value="S">
						<tr>
							<td colspan="4" class="detailHead">
							<table width="100%" cellpadding="0" cellspacing="0">
								<TBODY>
									<tr>
										<td class="detailHead"><bean:message key="prompt.subscriberAccessInfo" /></td>
										<td align="right"><img src="../../images/step_2.gif"></td>
									</tr>
								</TBODY>
							</table>
							</td>
						</tr>
						<tr>
							<td colspan="4">
							<table border="0" cellspacing="1" width="100%">
								<TBODY>
									<tr class="subhead" bgcolor="#999999" align="center">
										<td colspan="2"><bean:message key="prompt.civil" /></td>
										<td colspan="2"><bean:message key="prompt.criminal" /></td>
									</tr>
									<tr>
										<td class="formDeLabel"><bean:message key="prompt.activeDate" /></td>
										<td class="formDe"><html:text
											property="subscriberCivilActivationDate" size="10" maxlength="10" />
											<A HREF="#" onClick="cal1.select(document.departmentForm.subscriberCivilActivationDate,'anchor3','MM/dd/yyyy'); return false;"
											   NAME="anchor3" ID="anchor3"><bean:message key="prompt.2.calendar" /></A></td>
										<td class="formDeLabel"><bean:message key="prompt.activeDate" /></td>
										<td class="formDe"><html:text
											property="subscriberCriminalActivationDate" size="10"	maxlength="10" />
											<A HREF="#" onClick="cal1.select(document.departmentForm.subscriberCriminalActivationDate,'anchor4','MM/dd/yyyy'); return false;"
											   NAME="anchor4" ID="anchor4"><bean:message key="prompt.2.calendar" /></A></td>
									</tr>
									<tr>
										<td class="formDeLabel" nowrap><bean:message key="prompt.inactiveDate" /></td>
										<td class="formDe"><html:text
											property="subscriberCivilTerminationDate" size="10" maxlength="10" />
											<A HREF="#" onClick="cal1.select(document.departmentForm.subscriberCivilTerminationDate,'anchor5','MM/dd/yyyy'); return false;"
											   NAME="anchor5" ID="anchor5"><bean:message key="prompt.2.calendar" /></A></td>
										<td class="formDeLabel" nowrap><bean:message key="prompt.inactiveDate" /></td>
										<td class="formDe"><html:text
											property="subscriberCriminalTerminationDate" size="10" maxlength="10" />
											<A HREF="#" onClick="cal1.select(document.departmentForm.subscriberCriminalTerminationDate,'anchor6','MM/dd/yyyy'); return false;"
											   NAME="anchor6" ID="anchor6"><bean:message key="prompt.2.calendar" /></A></td>
									</tr>
								</TBODY>
							</table>
							</td>
						</tr>
						<tr>
							<td colspan="4"><br></td>
						</tr>						
						</logic:equal>
						<!-- END SUBSCRIBER ACCESS INFORMATION SECTION -->

						<!-- BEGIN PHYSICAL ADDRESS SECTION -->
						<tr>
							<td colspan="4" class="detailHead">
							<table width="100%" cellpadding="0" cellspacing="0">
								<TBODY>
									<tr>
										<td class="detailHead"><bean:message key="prompt.physicalAddress" /></td>
										<td align="right">
											<logic:equal name="departmentForm" property="agencyTypeId" value="N">
												<img src="../../images/step_2.gif">
											</logic:equal>	
											<logic:notEqual name="departmentForm" property="agencyTypeId" value="N">
												<img src="../../images/step_3.gif">
											</logic:notEqual>	
										</td>
									</tr>
								</TBODY>
							</table>
							</td>
						</tr>
						<tr>
							<td colspan="4">
							<table border="0" cellspacing="1" width="100%">
								<TBODY>
									<tr class="formDeLabel">
										<td><bean:message key="prompt.2.diamond" /><bean:message key="prompt.streetNumber" /></td>
										<td colspan="2"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.streetName" /></td>
									</tr>
									<tr class="formDe">
										<td><html:text property="physicalAddress.streetNumber" size="10" maxlength="10" /></td>
										<td colspan="2"><html:text property="physicalAddress.streetName" size="50" maxlength="50" /></td>
									</tr>
									<tr class="formDeLabel">
										<td><bean:message key="prompt.streetType" /></td>
										<td colspan="2"><bean:message key="prompt.aptSuite" /></td>
									</tr>
									<tr class="formDe">
										<td><html:select property="physicalAddress.streetTypeId">
											<html:option key="select.generic" value="" />
											<html:optionsCollection property="streetTypes" value="code" label="description" />
										</html:select></td>
										<td colspan="2"><html:text property="physicalAddress.aptNumber" size="20" maxlength="20" /></td>
									</tr>
									<tr class="formDeLabel">
										<td><bean:message key="prompt.2.diamond" /><bean:message key="prompt.city" /></td>
										<td><bean:message key="prompt.2.diamond" /><bean:message key="prompt.state" /></td>
										<td><bean:message key="prompt.2.diamond" /><bean:message key="prompt.zipCode" /></td>
									</tr>
									<tr class="formDe">
										<td><html:text property="physicalAddress.city" size="15" maxlength="15" /></td>
										<td><html:select property="physicalAddress.stateId">
											<html:option key="select.generic" value="" />
											<html:optionsCollection property="stateList" value="code" label="description" />
										</html:select></td>
										<td><html:text property="physicalAddress.zipCode" size="5" maxlength="5" />
										- <html:text property="physicalAddress.additionalZipCode" size="4"
											maxlength="4" /></td>
									</tr>
								</TBODY>
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
								<TBODY>
									<tr>
										<td class="detailHead"><bean:message key="prompt.mailingAddress" />
										&nbsp;&nbsp;&nbsp;Same as Physical Address
		                                  <input type="checkbox" name="check" onclick="showMailing(this)">
										</td>
										<td align="right">
											<logic:equal name="departmentForm" property="agencyTypeId" value="N">
												<img src="../../images/step_3.gif">
											</logic:equal>	
											<logic:notEqual name="departmentForm" property="agencyTypeId" value="N">
												<img src="../../images/step_4.gif">
											</logic:notEqual>	
										</td>
									</tr>
								</TBODY>

							</table>
							</td>
						</tr>
						<tr  id='mailing1' class='visible'>
							<td colspan="4">
							<table border="0" cellspacing="1" width="100%">
								<TBODY>
									<tr class="formDeLabel">
										<td><bean:message key="prompt.2.diamond" /><bean:message key="prompt.streetNumber" /></td>
										<td colspan="2"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.streetName" /></td>
									</tr>
									<tr class="formDe">
										<td><html:text property="mailingAddress.streetNumber" size="10" maxlength="10" /></td>
										<td colspan="2"><html:text property="mailingAddress.streetName" size="50" maxlength="50" /></td>
									</tr>
									<tr class="formDeLabel">
										<td><bean:message key="prompt.streetType" /></td>
										<td colspan="2"><bean:message key="prompt.aptSuite" /></td>
									</tr>
									<tr class="formDe">
										<td><html:select property="mailingAddress.streetTypeId">
											<html:option key="select.generic" value="" />
											<html:optionsCollection property="streetTypes" value="code" label="description" />
										</html:select></td>
										<td colspan="2"><html:text property="mailingAddress.aptNumber" size="20" maxlength="20" /></td>
									</tr>
									<tr class="formDeLabel">
										<td><bean:message key="prompt.2.diamond" /><bean:message key="prompt.city" /></td>
										<td><bean:message key="prompt.2.diamond" /><bean:message key="prompt.state" /></td>
										<td><bean:message key="prompt.2.diamond" /><bean:message key="prompt.zipCode" /></td>
									</tr>
									<tr class="formDe">
										<td><html:text property="mailingAddress.city" size="15" maxlength="15" /></td>
										<td class="formDe"><html:select property="mailingAddress.stateId">
											<html:option key="select.generic" value="" />
											<html:optionsCollection property="stateList" value="code" label="description" />
										</html:select></td>
										<td><html:text property="mailingAddress.zipCode" size="5"
											maxlength="5" /> - <html:text property="mailingAddress.additionalZipCode" size="4" maxlength="4" /></td>
									</tr>
									
								</TBODY>

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
								<TBODY>
									<tr>
										<td class="detailHead"><bean:message key="prompt.contacts" /></td>
										<td align="right">
											<logic:equal name="departmentForm" property="agencyTypeId" value="N">
												<img src="../../images/step_4.gif">
											</logic:equal>	
											<logic:notEqual name="departmentForm" property="agencyTypeId" value="N">
												<img src="../../images/step_5.gif">
											</logic:notEqual>	
										</td>
									</tr>
								</TBODY>

							</table>
							</td>
						</tr>
						<logic:empty name="departmentForm" property="contactList">
						<tr>
							<td colspan="4">No Contacts Listed<br></td>
						</tr>
						<tr>
							<td colspan="4"><br></td>
						</tr>
						</logic:empty>
						<logic:notEmpty name="departmentForm" property="contactList">
						<tr>
							<td colspan="4">
							<table width="100%" cellpadding="4" cellspacing="1" border="0">
								<TBODY>
									<tr bgcolor="#999999">
										<logic:equal name="departmentForm" property="action" value="deptUpdate">
										<td class="subhead" align="center"><bean:message key="prompt.delete" /></td>
										</logic:equal>
										<logic:equal name="departmentForm" property="action" value="deptCopy">
											<td class="subhead" align="center"><bean:message key="prompt.remove" /></td>
										</logic:equal>
										<td class="subhead" colspan="4"><bean:message key="prompt.contactInfo" /></td>
									</tr>
									<!-- begin display contact info -->
									<logic:iterate id="contactList" name="departmentForm" property="contactList">
										<tr>
											<td class="formDeLabel" rowspan="7" align="center" valign="top" width="1%">
												<html:checkbox indexed="true" name="contactList" property="deletable"/>
												<html:hidden property="clearCheckBoxes" value="true"/>
											</td>
											<td class="formDeLabel" valign="top" width="1%"><bean:message key="prompt.name" /></td>
											<td colspan="3" class="formDe">
											<table width="100%" border="0" cellspacing="1">
												<TBODY>
													<tr>
														<td class="formDeLabel" colspan="2">
															<bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message key="prompt.last" />
														</td>
													</tr>
													<tr>
														<td class="formDe" colspan="2">
															<html:text indexed="true" name="contactList" property="lastName" size="30" maxlength="75" />
														</td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message key="prompt.first" /></td>
														<td class="formDeLabel"><bean:message key="prompt.middle" /></td>
													</tr>
													<tr>
														<td class="formDe"><html:text indexed="true" name="contactList" property="firstName" size="25" maxlength="50" /></td>
														<td class="formDe"><html:text indexed="true" name="contactList" property="middleName" size="20" maxlength="50" /></td>
													</tr>
												</TBODY>
											</table>
											</td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.userId" /></td>
											<td colspan="3" class="formDe"><html:text indexed="true" name="contactList" property="logonId" maxlength="5" size="5" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.jobTitle" /></td>
											<td colspan="3" class="formDe"><html:text	indexed="true" name="contactList" property="title" maxlength="50" size="50" /></td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.phone" /></td>
											<td class="formDe" colspan="3"><html:text indexed="true" name="contactList" 
												property="areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> - <html:text indexed="true" name="contactList" 
												property="prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> - <html:text indexed="true" name="contactList" 
												property="last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/> <b><bean:message
												key="prompt.ext" /></b> <html:text indexed="true" name="contactList" 
												property="phoneExt" size="5" maxlength="5" onkeyup="return autoTab(this, 5);"/></td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap><bean:message key="prompt.2.diamond" /><bean:message key="prompt.primaryContact" /></td>
											<td class="formDe"> <bean:message key="prompt.yes" />
												   <html:radio name="contactList" property="primaryContact" value="Y" indexed="true"/>
							                    	&nbsp;&nbsp; <bean:message key="prompt.no" />
												   <html:radio name="contactList" property="primaryContact" value="N" indexed="true"/>&nbsp;&nbsp; 
							                </td>
							            <!-- /tr>
							            <tr-->     
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.liaisonTraining" /></td>
											<td class="formDe"> <bean:message key="prompt.yes" />
												   <html:radio name="contactList" property="liaisonTrainingInd" value="Y" indexed="true"/>
							                    	&nbsp;&nbsp; <bean:message key="prompt.no" />
												   <html:radio name="contactList" property="liaisonTrainingInd" value="N" indexed="true"/> 
							                </td> 
										</tr> 
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.email" /></td>
											<td class="formDe" colspan="3"><html:text indexed="true" name="contactList"  property="email" maxlength="100" size="50" /></td>
										</tr>
										<!-- end display first contact info -->
										<tr>
											<td colspan="5">&nbsp;</td>
										</tr>
									</logic:iterate>
								</TBODY>

							</table>
							</td>
						</tr>
						</logic:notEmpty>

						<!-- END CONTACT INFORMATION SECTION -->

						<!-- BEGIN SETCIC INFORMATION SECTION -->
						<logic:equal name="departmentForm" property="agencyTypeId" value="S">
<!-- hidden fields required so struts-validation will no abend -->
						<tr>
							<td>
								<html:hidden property="setcicContactName.lastName" />
							    <html:hidden property="setcicContactName.firstName" />
							    <html:hidden property="setcicContactName.middleName" />
								<html:hidden property="setcicPhoneNumber.areaCode" />
							    <html:hidden property="setcicPhoneNumber.prefix" />
							    <html:hidden property="setcicPhoneNumber.last4Digit"/>
								<html:hidden property="setcicPhoneNumber.ext" />
								
								<html:hidden property="warrantConfPhoneNumber.areaCode" />
							    <html:hidden property="warrantConfPhoneNumber.prefix" />
							    <html:hidden property="warrantConfPhoneNumber.last4Digit"/>
							    <html:hidden property="warrantConfPhoneNumber.ext" />

							    <html:hidden property="setcicDate" />
							    <html:hidden property="setcicInactiveDate" />
							    <html:hidden property="setcicRenewDate" />							
							    
							    <html:hidden property="countyId" />
							    <html:hidden property="gritsInd" />

							    <html:hidden property="setcicBillingAddress.streetNumber" />
							    <html:hidden property="setcicBillingAddress.streetName" />
							    <html:hidden property="setcicBillingAddress.streetTypeId" />
							    <html:hidden property="setcicBillingAddress.aptNumber" />
							    <html:hidden property="setcicBillingAddress.city" />
							    <html:hidden property="setcicBillingAddress.stateId" />
							    <html:hidden property="setcicBillingAddress.zipCode" />
							    <html:hidden property="setcicBillingAddress.additionalZipCode" />
							    <html:hidden property="setcicBillingLabelInd" />
						</td>
						</tr>
						</logic:equal>						
						<logic:equal name="departmentForm" property="agencyTypeId" value="N">
						<tr>
							<td colspan="4" class="detailHead">
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td class="detailHead">
										<bean:message key="prompt.setcicInfo" />
								    	<html:hidden property="setcicEdit" value="" />
								    </td>								
									<td align="right"><img src="../../images/step_5.gif"></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.access" /></td>
							<td class="formDe" colspan="3"><html:select property="setcicAccessId">
								<html:option key="select.generic" value="" />
								<html:optionsCollection property="setcicAccessTypes" value="code" label="description" />
							</html:select>
							
							</td>
						</tr>
						<tr>
							<td class="formDeLabel" valign="top" nowrap><bean:message key="prompt.name" /></td>
							<td colspan="3" class="formDe">
							<table width="100%" border="0" cellspacing="1">
								<tr>
									<td class="formDeLabel" colspan="2"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message key="prompt.last" /></td>
								</tr>
								<tr>
									<td class="formDe" colspan="2"><html:text property="setcicContactName.lastName" size="30" maxlength="30" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message key="prompt.first" /></td>
									<td class="formDeLabel"><bean:message key="prompt.middle" /></td>
								</tr>
								<tr>
									<td class="formDe"><html:text property="setcicContactName.firstName" size="25" /></td>
									<td class="formDe"><html:text property="setcicContactName.middleName" size="25" /></td>
								</tr>
								
							</table>
							</td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message
								key="prompt.2.diamond" /><bean:message key="prompt.contactPhone" /></td>
							<td class="formDe" colspan="3"><html:text
								property="setcicPhoneNumber.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> - <html:text
								property="setcicPhoneNumber.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> - <html:text
								property="setcicPhoneNumber.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/><b>&nbsp;
							<bean:message key="prompt.ext" />&nbsp; </b> <html:text property="setcicPhoneNumber.ext" size="5" maxlength="5" onkeyup="return autoTab(this, 5);"/></td>
						</tr>
						<tr>
							<td class="formDeLabel" width="1%" nowrap><bean:message
								key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message
								key="prompt.2.diamond" /><bean:message
								key="prompt.warrantContactPhone" /></td>
							<td class="formDe" colspan="3"><html:text	property="warrantConfPhoneNumber.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> -
            					<html:text property="warrantConfPhoneNumber.prefix" size="3"
								maxlength="3" onkeyup="return autoTab(this, 3);"/> - <html:text
								property="warrantConfPhoneNumber.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/><b>&nbsp;<bean:message
								key="prompt.ext" />&nbsp; </b> <html:text
								property="warrantConfPhoneNumber.ext" size="5" maxlength="5" onkeyup="return autoTab(this, 5);"/></td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message key="prompt.activeDate" /></td>
							<td class="formDe" colspan="3"><html:text property="setcicDate" size="10" maxlength="10"/>
							    <A HREF="#" onClick="cal1.select(document.departmentForm.setcicDate,'anchor7','MM/dd/yyyy'); return false;"
								   NAME="anchor7" ID="anchor7"><bean:message key="prompt.2.calendar" /></A></td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.inactiveDate" /></td>
							<td class="formDe" colspan="3"><html:text property="setcicInactiveDate" size="10" maxlength="10" />
							    <A HREF="#" onClick="cal1.select(document.departmentForm.setcicInactiveDate,'anchor8','MM/dd/yyyy'); return false;"
								   NAME="anchor8" ID="anchor8"><bean:message key="prompt.2.calendar" /></A></td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.renewDate" /></td>
							<td class="formDe" colspan="3"><html:text property="setcicRenewDate" size="10" maxlength="10" />
							    <A HREF="#" onClick="cal1.select(document.departmentForm.setcicRenewDate,'anchor9','MM/dd/yyyy'); return false;"
								   NAME="anchor9" ID="anchor9"><bean:message key="prompt.2.calendar" /></A></td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.county" /></td>
							<td class="formDe" colspan="3"><html:select property="countyId" size="1">
								<html:option key="select.generic" value="" />
								<html:optionsCollection property="countyList" value="code" label="description" />
							</html:select></td>
						</tr>
						<tr>
							<td class="formDeLabel" nowrap><bean:message key="prompt.gritsAccess" /></td>
							<td class="formDe" colspan="3"><html:radio property="gritsInd"
								value="Y" /><bean:message key="prompt.yes" /><html:radio
								property="gritsInd" value="N" /><bean:message key="prompt.no" /></td>
						</tr>
						<tr>
							<td class="subhead" colspan="4" bgcolor="#999999"><bean:message key="prompt.billingAddress" />
							&nbsp;&nbsp;&nbsp;Same as Physical Address
                                  <input type="checkbox" name="check" onclick="showBilling(this)">
							</td>
						</tr>
						<tr id="billing1" class="visible">
							<td colspan="4">
							<table border="0" cellspacing="1" width="100%">
								<tr class="formDeLabel">
									<td><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message key="prompt.streetNumber" /></td>
									<td colspan="2"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message key="prompt.streetName" /></td>
								</tr>
								<tr class="formDe">
									<td><html:text property="setcicBillingAddress.streetNumber" size="10" maxlength="10"/></td>
									<td colspan="2"><html:text property="setcicBillingAddress.streetName" size="50" maxlength="50" /></td>
								</tr>
								<tr class="formDeLabel">
									<td><bean:message key="prompt.streetType" /></td>
									<td colspan="2"><bean:message key="prompt.aptSuite" /></td>
								</tr>
								<tr class="formDe">
									<td><html:select property="setcicBillingAddress.streetTypeId">
										<html:option key="select.generic" value="" />
										<html:optionsCollection property="streetTypes" value="code" label="description" />
									</html:select></td>
									<td colspan="2"><html:text property="setcicBillingAddress.aptNumber" size="20" maxlength="20" /></td>
								</tr>
								<tr class="formDeLabel">
									<td><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message key="prompt.city" /></td>
									<td><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message key="prompt.state" /></td>
									<td><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message key="prompt.zipCode" /></td>
								</tr>
								<tr class="formDe">
									<td><html:text property="setcicBillingAddress.city" size="15" maxlength="15" /></td>
									<td class="formDe"><html:select property="setcicBillingAddress.stateId">
										<html:option key="select.generic" value="" />
										<html:optionsCollection property="stateList" value="code" label="description" />
									</html:select></td>
									<td><html:text property="setcicBillingAddress.zipCode" size="5"
										maxlength="5" /> - <html:text property="setcicBillingAddress.additionalZipCode" size="4" maxlength="4" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.label" /></td>
									<td class="formDe" colspan="2">
										<html:radio property="setcicBillingLabelInd" value="Y"/> <bean:message key="prompt.yes" />
										<html:radio property="setcicBillingLabelInd" value="N"/> <bean:message key="prompt.no" /></td>
								</tr>
							</table>
							</td>
						</tr>
						<!-- END SETCIC INFORMATION SECTION -->
						<tr><td colspan="4"><br></td></tr>
						</logic:equal>

						<!-- BEGIN COMMENT SECTION -->
						<tr>
							<td colspan="4" class="detailHead">
							<table width="100%" cellpadding="0" cellspacing="0">
								<TBODY>
									<tr>
										<td class="detailHead"><bean:message key="prompt.comments" /></td>
										<logic:notEqual name="departmentForm" property="agencyTypeId" value="N">										
											<td align="right"><img src="../../images/step_6.gif"></td>
										</logic:notEqual>				
										<logic:equal name="departmentForm" property="agencyTypeId" value="N">										
											<td align="right"><img src="../../images/step_6.gif"></td>
										</logic:equal>				

									</tr>
								</TBODY>
							</table>
							</td>
						</tr>
						<tr>
							<td class="formDe" colspan="4"><html:textarea property="comments" rows="3" style="width:100%" /></td>
						</tr> 
						<!-- END COMMENT SECTION -->
					</TBODY>
				</table>
				</td>
			</tr>
		</TBODY>
	</table>
	<!-- END DETAIL TABLE -->

	<!--BEGIN BUTTON TABLE-->
	<br>
	<table align="center" width="98%">
		<TBODY>
			<tr>
				<td align="center"><html:button
					property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
					<bean:message key="button.back"></bean:message>
				</html:button>&nbsp; <html:submit property="submitAction" onclick="return validateDepartmentUpdateFields(this.form) && checkRadio(this.form) && checkSubscriberInactiveDates();">
					<bean:message key="button.next"></bean:message>
				</html:submit>&nbsp; <html:reset>
					<bean:message key="button.reset"></bean:message>
				</html:reset>&nbsp; <html:submit property="submitAction">
					<bean:message key="button.cancel"></bean:message>
				</html:submit></td>
			</tr>
		</TBODY>
	</table>
	<!--END BUTTON TABLE-->

</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
