<!DOCTYPE HTML>
<!-- 08/29/2005	 Hien Rodriguez - Create JSP -->
<!-- 04/06/2005  C Shimek       - Defect#26710 added logic tag to not display subscriber block if agenct type is non-subscriber
								  Also added logic tag around required fields indicators so they match page displays -->
<!-- 08/02/2006  C Shimek       - Defect#33828 add Allow Officier Profile Creation field (lost during merge with Maint MSA)-->
<!-- 01/10/2007  C Shimek       - #38306 add multiple submit functionality  -->
<!-- 07/06/2007  C Shimek    	- #43456 replaced casework_uitl.js as all needed functions are in app.js  -->
<!-- 08/21/2007  C Shimek    	- #44591 added autoTab to Fax number  -->
<!-- 02/05/2009  C Shimek       - #56860 add Back to Top  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ include file="../../jQuery.fw" %>

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
<title><bean:message key="title.heading" /> - departmentCreate.jsp</title>

<!--JAVASCRIPT FILE FOR THIS PAGE -->
<html:javascript formName="departmentCreateForm"/>

<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/department/departmentCreate.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script language="javascript">

$(function(){
	datePickerSingle($("#activationDate"),"JIMS Activation Date",false);
	datePickerSingle($("#terminationDate"),"JIMS Inactive Date",false);
	datePickerSingle($("#setcicDate"),"SETCIC Active Date",false);
	datePickerSingle($("#setcicInactiveDate"),"SETCIC Inactive Date",false);
	datePickerSingle($("#setcicRenewDate"),"SETCIC Renew Date",false);
});

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
		  show('billing1', 1,"row");
	    }
}
</script>


</head>
<!--END HEADER TAG--> 

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/departmentDisplayContactUserSearch" target="content" focus="departmentName">
	<input type="hidden" name="agencyTypeId" value=<bean:write name="departmentForm" property="agencyTypeId" />>
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|46">	
	<!-- BEGIN HEADING TABLE -->
	<table width="98%">
		<TBODY>
			<tr>
				<td align="center" class="header">
					<bean:message key="title.departmentCreate" />
				</td>
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
	<table width="98%" align="center">
		<TBODY>
    <tr>
      <td>
        <ul>
          <li>Enter information, then select the Next button to view summary.</li>
          <li>Either <b>Physical</b> or <b>Mailing</b> address is required.</li>
        </ul>
      </td>
    </tr>
			<tr>
				<td class="required"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.requiredFieldsInstruction" /></td>
			</tr>
			<logic:equal name="departmentForm" property="agencyTypeId" value="N">
			<tr>
				<td class="required"><bean:message key="prompt.2.diamond" /><bean:message	key="prompt.2.diamond" />Required if SETCIC Access is Inquiry or Full.</td>
			</tr>
			<tr>
				<td class="required"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" />Required if SETCIC Access is Full.</td>
			</tr>
			<tr>
			    <td class="required"><bean:message key="prompt.dateFieldsInstruction" /></td>
		    </tr>
			</logic:equal>
		</TBODY>
	</table>
	
	<!-- BEGIN DETAIL TABLE -->
	<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue">
		<TBODY>
			<tr>
				<td>
				<SCRIPT LANGUAGE="JavaScript" ID="js1">
					var cal1 = new CalendarPopup();
					cal1.showYearNavigation();
				</SCRIPT>
				<table width="100%" border="0" cellspacing="1" cellpadding="2">
					<!-- BEGIN GENERAL INFORMATION SECTION -->
					<TBODY>
						<tr>
							<td colspan="4" class="detailHead">
							<table width="100%" cellpadding="0" cellspacing="0">
								<TBODY>
									<tr>
										<td class="detailHead"><bean:message key="prompt.generalInfo" /></td>
										<td align="right" width="1%"><img src="../../images/step_1.gif"></td>
									</tr>
								</TBODY>
							</table>
							</td>
						</tr>
					
		 				<tr>
							<td class="formDeLabel" width="0.5%"><bean:message key="prompt.agencyName" /></td>
							<td class="formDe" colspan="6"><bean:write name="departmentForm" property="agencyName" /></td>
						</tr>
						<tr>
							<td class="formDeLabel" width="0.5%"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.departmentName" /></td>
							<td class="formDe" colspan="6"><html:text property="departmentName" size="60" maxlength="60" /></td>
						</tr>
						<tr>
							<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.2.diamond" /><bean:message	key="prompt.departmentCode" /></td>
							<td class="formDe"><html:text name="departmentForm" property="departmentId" size="5" maxlength="3"/>&nbsp;</td>
							<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond" /> <bean:message key="prompt.organizationCode" /></td>
							<td class="formDe"><html:text property="orgCode" size="3" maxlength="3" /></td> 
						</tr> 
						<tr>
							<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.originatingAgencyId" /></td>
							<td class="formDe" colspan="4"><html:text property="originatingAgencyId" size="9" maxlength="9" /></td>
						</tr>
						<tr>
							<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.allowOfficerProfileCreation" /></td>
							<td class="formDe" colspan="4">
								<html:radio name="departmentForm" property="createOfficerProfileInd" value="Y" /><bean:message key="prompt.yes" /> 
								<html:radio name="departmentForm" property="createOfficerProfileInd" value="N" /><bean:message key="prompt.no" /> 								
							</td>
						</tr>
						<tr>
							<td class="formDeLabel" width="1%"><bean:message key="prompt.accessType" /></td>
							<td class="formDe" colspan="4"><html:select property="accessTypeId">
								<html:option key="select.generic" value="" />
								<html:optionsCollection property="accessTypes" value="code" label="description" /></html:select></td>
						</tr>
						<tr>
							<td class="formDeLabel" width="1%"><bean:message key="prompt.departmentStatus" /></td>
							<td class="formDe" colspan="4"><html:select property="statusId">
								<html:option key="select.generic" value="" />
								<html:optionsCollection property="statusTypes" value="code" label="description" /> </html:select></td>
						</tr>
						<tr>
							<td class="formDeLabel" width="1%"><bean:message key="prompt.faxNumber" /></td>
							<td class="formDe" colspan="4">
								<html:text property="departmentFaxNumber.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" /> - 
								<html:text property="departmentFaxNumber.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" /> - 
								<html:text property="departmentFaxNumber.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" /></td>
						</tr>
						<tr>
							<td class="formDeLabel" width="1%"><bean:message key="prompt.JIMSActivationDate" /></td>
							<td class="formDe" colspan="4"><html:text property="activationDate" size="10" maxlength="10" styleId="activationDate"/>
							   </td>
						</tr>
						<tr>
							<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.JIMSInactiveDate" /></td>
							<td class="formDe" colspan="4"><html:text property="terminationDate" size="10" maxlength="10" styleId="terminationDate"/>
							  </td>
						</tr> 
				</TBODY>
		</table>
		
			<!-- END GENERAL INFORMATION SECTION -->
			
			
		<table width="100%" border="0" cellspacing="1" cellpadding="2">
					
					<TBODY>
					
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
							<td colspan="4"><br></td>
						</tr>
						<!-- BEGIN SUBSCRIBER ACCESS INFORMATION SECTION -->
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
										<td class="formDe"><html:text property="subscriberCivilActivationDate" size="10" maxlength="10" />
										     <A HREF="#" onClick="cal1.select(document.departmentForm.subscriberCivilActivationDate,'anchor3','MM/dd/yyyy'); return false;"
												NAME="anchor3" ID="anchor3"><bean:message key="prompt.2.calendar" /></A></td>
										<td class="formDeLabel"><bean:message key="prompt.activeDate" /></td>
										<td class="formDe"><html:text property="subscriberCriminalActivationDate" size="10"	maxlength="10" />
										     <A HREF="#" onClick="cal1.select(document.departmentForm.subscriberCriminalActivationDate,'anchor4','MM/dd/yyyy'); return false;"
												NAME="anchor4" ID="anchor4"><bean:message key="prompt.2.calendar" /></A></td>
									</tr>
									<tr>
										<td class="formDeLabel" nowrap><bean:message key="prompt.inactiveDate" /></td>
										<td class="formDe"><html:text property="subscriberCivilTerminationDate" size="10"	maxlength="10" />
										     <A HREF="#" onClick="cal1.select(document.departmentForm.subscriberCivilTerminationDate,'anchor5','MM/dd/yyyy'); return false;"
												NAME="anchor5" ID="anchor5"><bean:message key="prompt.2.calendar" /></A></td>
										<td class="formDeLabel" nowrap><bean:message key="prompt.inactiveDate" /></td>
										<td class="formDe"><html:text property="subscriberCriminalTerminationDate" size="10" maxlength="10" />
										     <A HREF="#" onClick="cal1.select(document.departmentForm.subscriberCriminalTerminationDate,'anchor6','MM/dd/yyyy'); return false;"
												NAME="anchor6" ID="anchor6"><bean:message key="prompt.2.calendar" /></A></td>
									</tr>
								</TBODY>
							</table>
							</td>
						</tr> 
						<!-- END SUBSCRIBER ACCESS INFORMATION SECTION -->
				</logic:equal>
			</TBODY>
	</table>
	<table width="100%" border="0" cellspacing="1" cellpadding="2">
					
			<TBODY>
						<tr>
							<td colspan="4"><br></td>
						</tr>
						<!-- BEGIN PHYSICAL ADDRESS SECTION -->
						<tr>
							<td colspan="4" class="detailHead">
							<table width="100%" cellpadding="0" cellspacing="0" >
								<TBODY>
									<tr>
										<td class="detailHead"><bean:message key="prompt.physicalAddress" /></td>
										<td align="right">
											<logic:notEqual name="departmentForm" property="agencyTypeId" value="N">
												<img src="../../images/step_3.gif">
											</logic:notEqual>
											<logic:equal name="departmentForm" property="agencyTypeId" value="N">
												<img src="../../images/step_2.gif">
											</logic:equal>													
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
										<td><bean:message key="prompt.2.diamond" /><bean:message key="prompt.streetNum" /></td>
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
										<td><html:text property="physicalAddress.zipCode" size="5" maxlength="5" />	- <html:text property="physicalAddress.additionalZipCode" size="4" maxlength="4" /></td>
									</tr>
								</TBODY>
							</table>
							</td>
						</tr>
						<!-- END PHYSICAL ADDRESS SECTION -->
						<tr> 
							<td colspan="4"><br></td>
						</tr>
			</TBODY>
		</table>
		<table width="100%" border="0" cellspacing="1" cellpadding="2">
					
					<TBODY>
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
											<logic:notEqual name="departmentForm" property="agencyTypeId" value="N">
												<img src="../../images/step_4.gif">
											</logic:notEqual>
											<logic:equal name="departmentForm" property="agencyTypeId" value="N">
												<img src="../../images/step_3.gif">
											</logic:equal>													
										</td>
									</tr>
								</TBODY>

							</table>
							</td>
						</tr>
						<tr id='mailing1'>
							<td colspan="4">
							<table border="0" cellspacing="1" width="100%">
								<TBODY>
									<tr class="formDeLabel">
										<td><bean:message key="prompt.2.diamond" /><bean:message key="prompt.streetNum" /></td>
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
										<td><html:text property="mailingAddress.zipCode" size="5" maxlength="5" /> - <html:text	property="mailingAddress.additionalZipCode" size="4" maxlength="4" /></td>
									</tr>
								</TBODY>

							</table>
							</td>
						</tr> 
						<!-- END MAILING ADDRESS SECTION -->
						<tr>
							<td colspan="4"><br></td>
						</tr>
				</TBODY>
		</table>
		<table width="100%" border="0" cellspacing="1" cellpadding="2">
					
					<TBODY>
						<!-- hidden SETCIC INFORMATION fields for validation.xml -- could not get div tag to work with logic tags -->						
						<logic:equal name="departmentForm" property="agencyTypeId" value="S">
								<tr>
									<td>
										<html:hidden property="setcicContactName.lastName" value=""/>
										<html:hidden property="setcicContactName.firstName" value="" />
										<html:hidden property="setcicContactName.middleName" value="" />
									</td>
									<td>
										<html:hidden property="setcicPhoneNumber.areaCode" value="" />
										<html:hidden property="setcicPhoneNumber.prefix" value="" />
										<html:hidden property="setcicPhoneNumber.last4Digit" value="" />
              							<html:hidden property="setcicPhoneNumber.ext" value="" />
									</td>
									<td>
										<html:hidden property="warrantConfPhoneNumber.areaCode" value="" />
										<html:hidden property="warrantConfPhoneNumber.prefix" value="" />
										<html:hidden property="warrantConfPhoneNumber.last4Digit" value="" />
              							<html:hidden property="warrantConfPhoneNumber.ext" value="" />
									</td>
									<td>
										<html:hidden property="setcicDate" value="" />
										<html:hidden property="setcicInactiveDate" value="" />
										<html:hidden property="setcicRenewDate" value="" />
									</td>
									<td>
										<html:hidden property="setcicBillingAddress.streetNumber" value="" />									
										<html:hidden property="setcicBillingAddress.streetName" value="" />
										<html:hidden property="setcicBillingAddress.aptNumber" value="" />
										<html:hidden property="setcicBillingAddress.city" value="" />
										<html:hidden property="setcicBillingAddress.zipCode" value="" />
										<html:hidden property="setcicBillingAddress.additionalZipCode" value="" />
									</td>
								</tr>
						</logic:equal>
						<logic:equal name="departmentForm" property="agencyTypeId" value="N">
						<!-- SETCIC INFORMATION SECTION -->
 						<tr>
							<td colspan="4" class="detailHead">
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td class="detailHead"><bean:message key="prompt.setcicInfo" /></td>
										<td align="right"><img src="../../images/step_4.gif"></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.access" /></td>
							<td class="formDe" colspan="3"><html:select property="setcicAccessId">
								<html:option key="select.generic" value="" />
								<html:optionsCollection property="setcicAccessTypes" value="code" label="description" /></html:select></td>
						</tr>
						<tr>
							<td class="formDeLabel" valign="top" nowrap><bean:message key="prompt.name" /></td>
							<td colspan="3" class="formDe" >
							<table width="100%" border="0" cellspacing="1">
								<tr>
									<td class="formDeLabel" colspan="2"><bean:message	key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message key="prompt.last" /></td>
								</tr>
								<tr>
									<td class="formDe"  colspan="2"><html:text property="setcicContactName.lastName" size="30" maxlength="75" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message key="prompt.first" /></td>
									<td class="formDeLabel"><bean:message key="prompt.middle" /></td>
								</tr>
								<tr>
									<td class="formDe" ><html:text property="setcicContactName.firstName" size="25" maxlength="50"/></td>
									<td class="formDe" ><html:text property="setcicContactName.middleName" size="25"  maxlength="50"/></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message key="prompt.contactPhone" /></td>
							<td class="formDe"  colspan="3">
								<html:text property="setcicPhoneNumber.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> - 
								<html:text property="setcicPhoneNumber.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> -
								<html:text property="setcicPhoneNumber.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/><b>&nbsp;
              					<bean:message key="prompt.ext" />&nbsp; </b>
              					<html:text	property="setcicPhoneNumber.ext" size="5" maxlength="5" onkeyup="return autoTab(this, 5);"/>
							</td>
						</tr>
						<tr>
							<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message
								key="prompt.2.diamond" /><bean:message key="prompt.warrantContactPhone" />
							</td>
							<td class="formDe"  colspan="3">
								<html:text property="warrantConfPhoneNumber.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> -
 								<html:text property="warrantConfPhoneNumber.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> -
 								<html:text property="warrantConfPhoneNumber.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/><b>&nbsp;
 								<bean:message key="prompt.ext" />&nbsp; </b>
 								<html:text property="warrantConfPhoneNumber.ext" size="5" maxlength="5" onkeyup="return autoTab(this, 5);"/>
							</td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.2.diamond" /><bean:message key="prompt.activeDate" /></td>
							<td class="formDe"  colspan="3"><html:text property="setcicDate" size="10" maxlength="10" styleId="setcicDate"/>
							  </td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.inactiveDate" /></td>
							<td class="formDe"  colspan="3"><html:text property="setcicInactiveDate" size="10" maxlength="10" styleId="setcicInactiveDate"/>
							    </td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.renewDate" /></td>
							<td class="formDe"  colspan="3"><html:text property="setcicRenewDate" size="10" maxlength="10" styleId="setcicRenewDate"/>
							  </td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.county" /></td>
							<td class="formDe"  colspan="3"><html:select property="countyId" size="1">
								<html:option key="select.generic" value="" />
								<html:optionsCollection property="countyList" value="code" label="description" /></html:select>
							</td>
						</tr>
						<tr>
							<td class="formDeLabel" nowrap><bean:message key="prompt.gritsAccess" /></td>
							<td class="formDe"  colspan="3"><html:radio property="gritsInd" value="Y" /><bean:message key="prompt.yes" /><html:radio property="gritsInd" value="N" /><bean:message key="prompt.no" /></td>
						</tr>
						
				</TBODY>
		</table>
	<table width="100%" border="0" cellspacing="1" cellpadding="2">
					
					<TBODY>
						<tr>
							<td class="subhead" colspan="4" bgcolor="#999999" align="left"><bean:message key="prompt.billingAddress" />
							&nbsp;&nbsp;&nbsp;Same as Physical Address
                                  <input type="checkbox" name="check" onclick="showBilling(this)">
							</td>
						</tr>
						<tr id='billing1' >
							<td colspan="4">
							<table border="0" cellspacing="1" width="100%">
								<tr class="formDeLabel">
									<td><bean:message key="prompt.streetNum" /></td>
									<td colspan="4"><bean:message key="prompt.streetName" /></td>
								</tr>
								<tr class="formDe" >
									<td><html:text property="setcicBillingAddress.streetNumber" size="10" maxlength="10"/></td>
									<td colspan="2"><html:text property="setcicBillingAddress.streetName" size="50" maxlength="50" /></td>
								</tr>
								<tr class="formDeLabel">
									<td><bean:message key="prompt.streetType" /></td>
									<td colspan="2"><bean:message key="prompt.aptSuite" /></td>
								</tr>
								<tr class="formDe" >
									<td><html:select property="setcicBillingAddress.streetTypeId">
										<html:option key="select.generic" value="" />
										<html:optionsCollection property="streetTypes" value="code" label="description" /></html:select>
									</td>
									<td colspan="2"><html:text property="setcicBillingAddress.aptNumber" size="20" maxlength="20" /></td>
								</tr>
								<tr class="formDeLabel">
									<td><bean:message key="prompt.city" /></td>
									<td><bean:message key="prompt.state" /></td>
									<td><bean:message key="prompt.zipCode" /></td>
								</tr>
								<tr class="formDe" >
									<td><html:text property="setcicBillingAddress.city" size="15" maxlength="15" /></td>
									<td class="formDe" ><html:select property="setcicBillingAddress.stateId">
										<html:option key="select.generic" value="" />
										<html:optionsCollection property="stateList" value="code" label="description" /></html:select>
									</td>
									<td><html:text property="setcicBillingAddress.zipCode" size="5"	maxlength="5" /> - <html:text property="setcicBillingAddress.additionalZipCode"	size="4" maxlength="4" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.label" /></td>
									<td class="formDe"  colspan="2"><html:radio property="setcicBillingLabelInd" value="Y"/>
										<bean:message key="prompt.yes" /> <html:radio property="setcicBillingLabelInd" value="N"/><bean:message key="prompt.no" />
									</td>
								</tr>
							</table>
							</td>
						</tr> 
						<!-- END SETCIC INFORMATION SECTION -->
						<tr>
							<td colspan="4"><br></td>
						</tr>
						</logic:equal>
				</TBODY>
		</table>
		<table width="100%" border="0" cellspacing="1" cellpadding="2">
					
					<TBODY>
						<!-- BEGIN COMMENT SECTION -->
						<tr>
							<td colspan="4" class="detailHead">
							<table width="100%" cellpadding="0" cellspacing="0">
								<TBODY>
									<tr>
										<td class="detailHead"><bean:message key="prompt.comments" /></td>
										<td align="right"><img src="../../images/step_5.gif"></td>
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
				<td align="center">
				  	<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
  						<bean:message key="button.back"></bean:message>
  					</html:button>&nbsp; 
					<html:submit property="submitAction" onclick="return validateDepartmentCreateFields(this.form) && disableSubmit(this, this.form);">
  						<bean:message key="button.next"></bean:message>
  					</html:submit>&nbsp;
					<html:reset><bean:message key="button.reset"></bean:message></html:reset>&nbsp;
					<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
						<bean:message key="button.cancel"></bean:message>
					</html:submit>
			  </td>
			</tr>
		</TBODY>
	</table>
	<!--END BUTTON TABLE-->

</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>

