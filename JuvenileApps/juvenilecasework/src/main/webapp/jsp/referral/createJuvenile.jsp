<!DOCTYPE HTML>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.PDCodeTableConstants"%>
<%@ page import="ui.common.UIUtil" %>
<!-- Changes for JIMS200077276 Starts -->
<%@ page import="naming.Features"%>
<!-- Changes for JIMS200077276 ends -->
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type="text/javascript">

$(document).ready(function(){
	
	
	if ( '<bean:write name="juvenileReferralForm" property="juvExcludedReporting" />' == '1' ) {
		$("#excludeReporting").prop("checked", true);
	} else {
		$("#excludeReporting").prop("checked", false);
	}
	$("#excludeReporting").change(function(){
		console.log("Check change");
		if (this.checked){
			$("#juvExcludedReporting").val(1);
			console.log( $("#juvExcludedReporting").val());
		} 
		
		if (!this.checked){
			$("#juvExcludedReporting").val(0);
			console.log( $("#juvExcludedReporting").val());
		}
	})
	
	$("#backBtn").click(function(){
		spinner();
	})
	$("#refreshBtn").click(function(){
		spinner();
	})
	$("#cancelBtn").click(function(){
		spinner();
	})
	
})
</script>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>

<%--msp:login / --%>

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading" />-createJuvenile.jsp</title>

<html:javascript formName="juvenileReferralForm"/>

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/address.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<%-- <script type="text/javascript" src="/<msp:webapp/>js/casework_edu.js"></script> --%>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>



<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/referral/createJuvenile.js"></script>

<script type='text/javascript'>
var harrisCountyDropDownValue = <%=PDCodeTableConstants.HARRIS_COUNTY%>;

</script>
</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin='0' leftmargin="0">
<%--BEGIN FORM TAG--%>
<html:form action="/displayCreateJuvenile" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Create_Juvenile.htm#|5">
	<br/>
<%-- BEGIN HEADING TABLE --%>
	<table width='100%'>
		<tr>
			<td align="center" class="header">Process Referrals - Create Juvenile</td>
		</tr>
	</table>
<%-- END HEADING TABLE --%>
<br \>
	<%-- BEGIN ERROR TABLE --%>
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
	<%-- END ERROR TABLE --%>
<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="98%" border="0">
		<tr>
			<td align="left">
				&nbsp;&nbsp;&nbsp;Enter the juvenile details and select the Next button.
				
			</td>
		</tr>
		<tr>
			<td class="required"><bean:message key="prompt.requiredFields" /></td>
		</tr>
		<tr>
			<td>
				<ul>
					<li>If a legal parent/guardian name is entered, their relationship, address (street number, street name, city, state and zip code), phone type and phone number is required.</li>
					<li>Select the 'Add to List' button to include a family member entry in the Create Juvenile record.</li>
					<li>Family member entries cannot exceed three family members.</li>
				</ul>
				
			</td>
		</tr>
	
	</table>
	<%-- END INSTRUCTION TABLE --%>
	<%-- BEGIN TAB BLUE BORDER TABLE --%>
	<table cellpadding='1' cellspacing='1' border='0' width="99%" class="borderTableBlue" align="center">
		<tr height='30px' class='referralDetailHead'>
			<td  colspan="6"><bean:message key="prompt.juvenileDemographics" /></td>
		</tr>
		<tr>
			<td class="formDeLabel" valign='top' nowrap='nowrap'><bean:message key="prompt.name" /></td>
			<td valign='top' class='formDe' colspan="6">
				<table border='0' cellspacing='1'>
					<tr>
						<td class='formDeLabel' colspan="2"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.last" /></td>
						<jims:isAllowed requiredFeatures="<%=Features.JRP_CREATEJUV_EXCJUVRPT_CHK%>">
						<td class='formDeLabel' nowrap='nowrap' width="5%">
							<logic:equal name="juvenileReferralForm" property="juvExcludedReporting" value="0">
								<input type="checkbox" id="excludeReporting" name="excludeReporting" value="false"/>
							</logic:equal>
							<logic:equal name="juvenileReferralForm" property="juvExcludedReporting" value="1">
								<input type="checkbox" id="excludeReporting" name="excludeReporting" value="true" checked/>
							</logic:equal>
							<html:hidden styleId="juvExcludedReporting" property="juvExcludedReporting"/>
						Exclude Juvenile from Reporting</td>
						</jims:isAllowed>
					</tr>
					<tr>
						<td class='formDe' colspan="2"><html:text property="lastName" size="30" maxlength="30" styleId="lastName"/></td>
					</tr>
					<tr>
						<td class='formDeLabel'><bean:message key="prompt.2.diamond" /><bean:message key="prompt.first" /></td>
						<td class='formDeLabel'><bean:message key="prompt.middle" /></td>
						<td class='formDeLabel' colspan='2'><bean:message key="prompt.suffix" /></td>
					</tr>
					<tr>
						<td class='formDe'><html:text property="firstName" size="25" maxlength="30" styleId="firstName"/></td>
						<td class='formDe'><html:text property="middleName" size="15" maxlength="15" /></td>
						<td class='formDe' colspan='3'><html:text property="nameSuffix" size="3" maxlength="3" /></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.2.diamond" /><bean:message key="prompt.race" /></td>
			<td class='formDe' colspan="">
				<html:select property="originalRaceId" styleId="race">
			   	 	<html:option value="">
						<bean:message key="select.generic" />
					</html:option>
					<html:optionsCollection property="races" value="code" label="description" />
				</html:select>
			</td>
			<td class='formDeLabel'><bean:message key="prompt.2.diamond" /><bean:message key="prompt.sex" /></td>
			<td class='formDe' colspan='4'>
				<html:select property="sexId" styleId="sex">
					<html:option value="">
						<bean:message key="select.generic" />
					</html:option>
					<html:optionsCollection property="sexes" value="code" label="description" />
				</html:select>
			</td>
		</tr>
	
		<tr>
			<td class='formDeLabel' nowrap='nowrap' valign="top"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.dateOfBirth"/></td>
			<td class='formDe' valign="top" colspan="1" width="10%"><html:text styleId="dateOfBirth" property="dateOfBirth" maxlength="10" size="10" /></td>
			<td class='formDeLabel' nowrap='nowrap' width="1%" valign="top" width="40%" ><bean:message key="prompt.ssn" /></td>
			<td class='formDe' valign="top" colspan='4'>
				<html:text property="SSN.SSN1" styleId="SSN1" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/>- 
				<html:text property="SSN.SSN2" styleId="SSN2" size="2" maxlength="2" onkeyup="return autoTab(this, 2);"/>- 
				<html:text property="SSN.SSN3" styleId="SSN3" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/> &nbsp;
			</td>
		</tr>
		<tr>
			<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.comments" /></td>
			<td class='formDe' colspan='6'><html:text property="comments" size="55" maxlength="55" /></td>
		</tr> 
	<!-- Juvenile Demographics Section Completed -->

	<!-- Juvenile Address Information Section starts -->
		<tr height='30px' class='referralDetailHead'>
			<td colspan="6"><bean:message key="prompt.juvenileAddressInfo" /></td>
		</tr>
		<tr>
			<td class="formDeLabel" nowrap> <bean:message key="prompt.streetNumber" /></td>
			<td class="formDeLabel" nowrap><bean:message key="prompt.streetNumber" />&nbsp;<bean:message key="prompt.suffix" /></td>
			<td class="formDeLabel" nowrap> <bean:message key="prompt.streetName" /></td>
			<td class="formDeLabel" nowrap><input type="checkbox" name="unknownAddress" id="juvUnknownAddress"/>&nbsp;Address Unknown (Residence)</td>
			<td class="formDeLabel" nowrap colspan="2"><input type="checkbox" name="outOfCountry" id="juvOutOfCountryId"/>&nbsp;Out of Country (Residence)</td>
		</tr>
		<tr>
			<td class="formDe">
				 <html:text name="juvenileReferralForm" property="juvAddress.streetNumber" size="10" maxlength="9" styleId="juvStreetNumberId"/>
			</td>
			<td class="formDe">
				<html:select name="juvenileReferralForm" property="juvAddress.streetNumSuffixId" size="1">
					<option value="">Please Select</option>
					<html:optionsCollection name="juvenileReferralForm" property="streetNumSuffixList" value="code" label="description" />
				</html:select>
			</td>
			<td class="formDe" colspan="6">
				<html:text name="juvenileReferralForm" property="juvAddress.streetName" size="30" maxlength="50" styleId="juvStreetName"/>
			</td>
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.streetType" /></td>
			<td class="formDeLabel" colspan="5" nowrap='nowrap'><bean:message key="prompt.aptSuite" /></td>
		</tr>
		<tr>
			<td class="formDe">
				<html:select name="juvenileReferralForm" property="juvAddress.streetTypeId" size="1">
					<option value="">Select One</option>
					<html:optionsCollection name="juvenileReferralForm" property="streetTypeList" value="code" label="description" />
				</html:select>
			</td>
			<td class="formDe" colspan="6">
				<html:text name="juvenileReferralForm" property="juvAddress.aptNumber" size="10" maxlength="10" />
			</td>
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.city" /></td>
			<td class="formDeLabel"><bean:message key="prompt.state" /></td>
			<td class="formDeLabel" colspan="4"><bean:message key="prompt.zipCode" /></td>
		</tr>
		<tr>
			<td class="formDe">
				<html:text name="juvenileReferralForm" property="juvAddress.city" size="15" maxlength="15" styleId="juvCity"></html:text>
			</td>
			<td class="formDe">
				<html:select name="juvenileReferralForm" property="juvAddress.stateId" size="1" styleId="juvStateId" onchange="javascript:enableCounty(this, this.name)">
					<option value="">Please Select</option>
					<html:optionsCollection name="juvenileReferralForm" property="stateList" value="code" label="description" />
				</html:select>
			</td>
			<td class="formDe" colspan="4">
				<html:text name="juvenileReferralForm" property="juvAddress.zipCode" size="5" maxlength="5" onkeyup="return autoTab(this, 5);" styleId="juvZipCode"/> -
				<html:text name="juvenileReferralForm" property="juvAddress.additionalZipCode" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" styleId="juvAddtionalZipCode"/>
			</td>
		</tr>
		<tr>
			<td class="formDeLabel"> <bean:message key="prompt.addressType" /></td>
			<td class="formDeLabel" colspan="6"><bean:message key="prompt.county" /></td>
		</tr>
		<tr>
			<td class="formDe">
				<html:select name="juvenileReferralForm" property="juvAddress.addressTypeId" styleId="juvAddressTypeId">
					<option value="">Please Select</option>
					<html:optionsCollection name="juvenileReferralForm" property="addressTypeList" value="code" label="description" />
				</html:select>
			</td>
			<td class="formDe" colspan="6">
				<span class="hidden" id="emptySelect1">
					<select disabled='disabled'>
						<option>Please Select</option>
					</select> 
				</span> 
				<span class="visible" id="county1Span">
					<html:select name="juvenileReferralForm" property="juvAddress.countyId" styleId="county1">
						<option value="">Please Select</option>
						<html:optionsCollection name="juvenileReferralForm" property="countyList" value="code" label="description" />
					</html:select> 
				</span>
			</td>
		</tr>
	<!-- Juvenile Address Information Section completed -->

	<!-- Juvenile Education Section Starts -->
		<tr height='30px' class='referralDetailHead'><td colspan="6"><bean:message key="prompt.schoolInfo" /></td></tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.school" />&nbsp;<bean:message key="prompt.district" /></td>
			<td class="formDe" colspan="6">
				<html:select name="juvenileReferralForm" property="schoolDistrictId" styleId="schDistId">
					<html:option key="select.generic" value="" />
					<html:optionsCollection property="schoolDistricts" value="code" label="description" />
				</html:select> 
				<html:submit property="submitAction" onclick="spinner();"><bean:message key="button.go" /></html:submit> &nbsp;
				<a onclick="spinner();" href="javascript:changeFormActionURL('/<msp:webapp/>displayCreateJuvenile.do?submitAction=<bean:message key="button.search"/>', true);">Search for school</a>
			</td>
		</tr>
		<tr>
		<html:hidden name="juvenileReferralForm" property="instructionType" styleId="instructionType"/><%-- todo Delete this line if not needed --%>
			<td class="formDeLabel"><bean:message key="prompt.school" />&nbsp;<bean:message key="prompt.name" /></td>
			<td class="formDe" colspan="6">
				<html:select name="juvenileReferralForm" property="schoolId" styleId="schoolId">
					<html:option key="select.generic" value="" />
					<html:optionsCollection property="schools" value="schoolCode" label="schoolDisplayLiteral" />
				</html:select>
			</td>
		</tr>
<%-- 		<tr>
			<td class="formDeLabel"><bean:message key="prompt.enrollmentStatus" /></td>
			<td class="formDe" colspan="6">
				<html:select name="juvenileReferralForm" property="exitTypeId" styleId="exitTypeId" onchange="checkSelectES(this)">
					<html:option key="select.generic" value="" />
					<html:optionsCollection property="exitTypes" value="code" label="description" />
				</html:select>
			</td>
		</tr> --%>
		<%--code above commented as part of Bug  76348--%>
		<tr>
			<td class="formDeLabel" width='1%' nowrap="nowrap"><bean:message key="prompt.schoolAttendanceStatus" /></td>
			<td class="formDe"  colspan="6">
			<html:select name="juvenileReferralForm" property="attendanceStatusId" styleId="sasId">
			<html:option key="select.generic" value="" />
			<html:optionsCollection property="attendanceStatus" value="code" label="description"/>				
			</html:select>
			</td>
		</tr>
		<tr>
			<td class="formDeLabel" nowrap><bean:message key="prompt.programAttending" /></td>
			<td class="formDe" colspan="6">
				<html:select name="juvenileReferralForm" property="programAttendingId" styleId="prgmAttendingId">
					<html:option key="select.generic" value="" />
					<html:optionsCollection property="programAttending" value="code" label="description" />
				</html:select>
			</td>
		</tr> 
		<tr>
			<td class="formDeLabel" nowrap><bean:message key="prompt.currentGradeLevel" /></td>
			<td class="formDe" colspan="6">
				<html:select name="juvenileReferralForm" property="gradeLevelId" styleId="gradeLevelId">
					<html:option key="select.generic" value="" />
					<html:optionsCollection property="gradeLevels" value="code" label="description" />
				</html:select>
			</td>
		</tr>
					
<!-- Juvenile Education Section Ends --> 

<!-- Legal Parents or Guardian Info starts-->
	  <tr height='30px' class='referralDetailHead'>
			<td colspan="6"><bean:message key="prompt.legalParentsOrGuardianInfo" /></td>
	  </tr>
	  <tr>
		<td class="formDeLabel" valign='top' nowrap='nowrap'><bean:message key="prompt.name" /></td>
		<td valign='top' class='formDe' colspan="6">
			<table border='0' cellspacing='1'>
				<tr>
					<td class='formDeLabel' colspan="3"><bean:message key="prompt.last" /></td>
				</tr>
				<tr>
					<td class='formDe' colspan="4"><html:text name="juvenileReferralForm" property="memberBean.lastName" size="30" maxlength="75" styleId="memLastName"/></td>
				</tr>
				<tr>
					<td class='formDeLabel'><bean:message key="prompt.first"/></td>
					<td class='formDeLabel'><bean:message key="prompt.middle"/></td>
					
				</tr>
				<tr>
					<td class='formDe'><html:text name="juvenileReferralForm" property="memberBean.firstName" size="25" maxlength="50" styleId="memFirstName"/></td>
					<td class='formDe'><html:text name="juvenileReferralForm" property="memberBean.middleName" size="25" maxlength="50" /></td>
					
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.relationship" /></td>
		<td class="formDe">
			<html:select name="juvenileReferralForm" property="memberBean.relationshipId" size="1" styleId="relationship">
				<option value="">Please Select</option>
				<html:optionsCollection name="juvenileReferralForm" property="memberBean.relationships" value="code" label="description" />
			</html:select>
		</td>
		<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.ssn" /></td>
	
		<td class='formDe' valign="top" colspan="4">
			<html:text name="juvenileReferralForm"  property="memberBean.SSN.SSN1" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/>- 
			<html:text name="juvenileReferralForm"  property="memberBean.SSN.SSN2" size="2" maxlength="2" onkeyup="return autoTab(this, 2);"/>- 
			<html:text name="juvenileReferralForm"  property="memberBean.SSN.SSN3" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/> &nbsp;
		</td>
	</tr>
	 <tr>
		 <td class='formDeLabel' valign="top"><bean:message key="prompt.incarcerated"/>?</td>
	  	 <td class="formDe"><input type="checkbox" name="memberBean.incarceratedOrDeceased" value="I" id="incarcerated"/></td>
	  	 <td class='formDeLabel' valign="top"><bean:message key="prompt.deceased"/>?</td>
		 <td class="formDe"colspan="6"><input type="checkbox" name="memberBean.incarceratedOrDeceased" value="D" id="deceased"/></td>
	 </tr>
	<span id="guardianAdressInfo">
		<tr>
			<td class="formDeLabel" nowrap><bean:message key="prompt.streetNumber" /></td>
			<td class="formDeLabel" nowrap><bean:message key="prompt.streetNumber" />&nbsp;<bean:message key="prompt.suffix" /></td>
			<td class="formDeLabel" nowrap> <bean:message key="prompt.streetName" /></td>
			<td class="formDeLabel" nowrap><input type="checkbox" name="unknownAddress" id="memAddUnknownAddress"/>&nbsp;Address Unknown (Residence)</td>
			<td class="formDeLabel" nowrap colspan="2"><input type="checkbox" name="outOfCountry" id="memAddOutOfCountry"/>&nbsp;Out of Country (Residence)</td>
		</tr>
		<br><br>
		<tr>
			<td class="formDe">
				 <html:text name="juvenileReferralForm" property="memberBean.memberAddress.streetNumber" size="10" maxlength="9" styleId="memAddStreetNumber"/>
			</td>
			<td class="formDe">
				<html:select name="juvenileReferralForm" property="memberBean.memberAddress.streetNumSuffixId" size="1" styleId="memAddStreetNumSuffixId">
					<option value="">Please Select</option>
					<html:optionsCollection name="juvenileReferralForm" property="streetNumSuffixList" value="code" label="description" />
				</html:select>
			</td>
			<td class="formDe" colspan="6">
				<html:text name="juvenileReferralForm" property="memberBean.memberAddress.streetName" size="30" maxlength="50" styleId="memAddStreetName"/>
			</td>
		</tr>
		 <tr>
			<td class="formDeLabel"><bean:message key="prompt.streetType" /></td>
			<td class="formDeLabel" colspan="6" nowrap='nowrap'><bean:message key="prompt.aptSuite" /></td>
		</tr>
		<tr>
			<td class="formDe">
				<html:select name="juvenileReferralForm" property="memberBean.memberAddress.streetTypeId" size="1" styleId="memAddStreetTypeId">
					<option value="">Select One</option>
					<html:optionsCollection name="juvenileReferralForm" property="streetTypeList" value="code" label="description" />
				</html:select>
			</td>
			<td class="formDe" colspan="6">
				<html:text name="juvenileReferralForm" property="memberBean.memberAddress.aptNumber" size="10" maxlength="10" styleId="memAddAptNumber"/>
			</td>
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.city" /></td>
			<td class="formDeLabel"><bean:message key="prompt.state" /></td>
			<td class="formDeLabel" colspan="6"> <bean:message key="prompt.zipCode" /></td>
		</tr>
		<tr>
			<td class="formDe">
				<html:text name="juvenileReferralForm" property="memberBean.memberAddress.city" size="15" maxlength="15" styleId="memAddCity"></html:text>
			</td>
			<td class="formDe">
				<html:select name="juvenileReferralForm" property="memberBean.memberAddress.stateId" size="1" styleId="memAddStateId" onchange="javascript:enableCounty(this, this.name)">
					<option value="">Please Select</option>
					<html:optionsCollection name="juvenileReferralForm" property="stateList" value="code" label="description" />
				</html:select>
			</td>
			<td class="formDe" colspan="6">
				<html:text name="juvenileReferralForm" property="memberBean.memberAddress.zipCode" size="5" maxlength="5" onkeyup="return autoTab(this, 5);" styleId="memAddZipCode"/> -
				<html:text name="juvenileReferralForm" property="memberBean.memberAddress.additionalZipCode" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" styleId="memAddAdditionalZipCode"/>
			</td>
		</tr>
		<tr>
			<td class="formDeLabel"> <bean:message key="prompt.addressType" /></td>
			<td class="formDeLabel" colspan="6"><bean:message key="prompt.county" /></td>
		</tr>
		<tr>
			<td class="formDe">
				<html:select name="juvenileReferralForm" property="memberBean.memberAddress.addressTypeId" styleId="memAddAddressTypeId">
					<option value="">Please Select</option>
					<html:optionsCollection name="juvenileReferralForm" property="addressTypeList" value="code" label="description" />
				</html:select>
			</td>
			<td class="formDe" colspan="6">
				<span class="hidden" id="emptySelect1">
					<select disabled='disabled'>
						<option>Please Select</option>
					</select> 
				</span> 
				<span class="visible" id="county1Span">
					<html:select name="juvenileReferralForm" property="memberBean.memberAddress.countyId" styleId="memAddCountyId">
						<option value="">Please Select</option>
						<html:optionsCollection name="juvenileReferralForm" property="countyList" value="code" label="description" />
					</html:select> 
				</span>
			</td>
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.phoneType"/></td>
			<td colspan="6" class="formDe">
				<html:radio name="juvenileReferralForm" property="memberBean.phoneType" value="HM" styleId="memAddHome"/><bean:message key="prompt.home"/>
				<html:radio name="juvenileReferralForm" property="memberBean.phoneType" value="MO" styleId="memAddMobile"/><bean:message key="prompt.mobile"/>
			</td>
		</tr>
		<tr>
		<td class="formDeLabel"><bean:message key="prompt.phone"/></td>
		<td class="formDe" colspan="6">
			<html:text name="juvenileReferralForm" property="memberBean.contactPhoneNumber.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" styleId="memAddAreaCode"/> - 
			<html:text name="juvenileReferralForm" property="memberBean.contactPhoneNumber.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" styleId="memAddPrefix"/> - 
			<html:text name="juvenileReferralForm" property="memberBean.contactPhoneNumber.last4Digit" size="4"  maxlength="4" onkeyup="return autoTab(this, 4);" styleId="memAddLast4Digit"/> Ext.
			<html:text name="juvenileReferralForm" property="memberBean.contactPhoneNumber.ext" size="6" maxlength="6" onkeyup="return autoTab(this, 6);" styleId="memAddExt"></html:text>
			<input type="checkbox" name="memberBean.phoneInd" value="P" id="memAddPrimaryIndId"/>Primary&nbsp;&nbsp;&nbsp;
			<input type="checkbox" name="memberBean.phoneInd" value="U" id="memAddUnknownPhoneId"/>Unknown
		</tr>
		<tr>
			<td colspan="6" class="formDe">
				<div class="paddedFourPix" align="center" >
					<html:button property="submitAction" styleId="addToListBtn"> <bean:message key="button.addToList" /></html:button>&nbsp;
	   			<html:submit property="submitAction"  styleId="refresh"> <bean:message key="button.refresh" /></html:submit> 
	  		</div>
			</td>
		</tr>
		<tr>
			<td colspan="6">
	  			<logic:notEmpty name="juvenileReferralForm" property="memberDetailsBeanList">
               	 <logic:notPresent name="NewRecords"><bean:define id="NewRecords" value="1" type="java.lang.String"/></logic:notPresent>
				 <table width="100%" bgcolor="#cccccc" cellspacing="1">
						<tr bgcolor="#f0f0f0">
							<td class="subhead" valign="top" width="5%"><bean:message key="prompt.select"/>&nbsp;</td>
							<td class="subhead" valign="top" width="25%">&nbsp;<bean:message key="prompt.name"/></td>
							<td class="subhead" valign="top" width="10%"><bean:message key="prompt.incarcerated"/><br/>Or<br/><bean:message key="prompt.deceased"/></td>
							<td class="subhead" valign="top" width="10%">&nbsp;<bean:message key="prompt.relationship"/></td>
							<td class="subhead" valign="top" width="25%">&nbsp;<bean:message key="prompt.address"/></td>
							<td class="subhead" valign="top" width="10%" nowrap='nowrap'>&nbsp;Phone Type</td>
							<td class="subhead" valign="top" width="20%">&nbsp;Phone Number</td>
							<td class="subhead" valign="top" width="25%">&nbsp;SSN</td>
						</tr>
						<logic:iterate id="memBeans" name="juvenileReferralForm" property="memberDetailsBeanList" indexId="index">
							<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
								<td valign="top" align="left" width="1%">                               
									<a href="/<msp:webapp/>displayCreateJuvenile.do?submitAction=Remove&selectedValue=<%= index.intValue()%>"><bean:message key="prompt.remove"/></a>
								</td>
								<td valign="top"><bean:write name="memBeans" property="formattedName"/></td>
								<td valign="top"><span title="<bean:write name="memBeans" property="incarceratedOrDeceasedDesc"/>" ><bean:write name="memBeans" property="incarceratedOrDeceased"/></span></td>
								<td valign="top"><span title="<bean:write name="memBeans" property="relationshipDesc"/>" ><bean:write name="memBeans" property="relationshipId"/></span></td>
								<td wrap>
									<bean:write name="memBeans" property="formattedAddress"/>&nbsp;
									<logic:notEmpty name="memBeans" property="memberAddress.validated">
										<logic:equal name="memBeans" property="memberAddress.validated" value="Y">
											<img src="/<msp:webapp/>images/green_check.gif" alt="greenCheck" />
										</logic:equal>
										<logic:equal name="memBeans" property="memberAddress.validated" value="N">
											<img src="/<msp:webapp/>images/red_x.gif" alt="redx" />
										</logic:equal>
									</logic:notEmpty>
								</td>
								<td>
									<span title="<bean:write name="memBeans" property="phoneTypeDesc"/>" ><bean:write name="memBeans" property="phoneType"/></span>
								</td>
								<td valign="top">
									<logic:notEmpty name="memBeans" property="contactPhoneNumber.areaCode">
										<bean:write name="memBeans" property="contactPhoneNumber.areaCode"/>-
										<bean:write name="memBeans" property="contactPhoneNumber.prefix"/>-
										<bean:write name="memBeans" property="contactPhoneNumber.last4Digit"/>
										EXT
										<bean:write name="memBeans" property="contactPhoneNumber.ext"/>
										(<bean:write name="memBeans" property="phoneIndDesc"/>)
									</logic:notEmpty>
								</td>
								<td valign="top" nowrap='nowrap'><bean:write name="memBeans" property="formattedSSN"/></td>
							</tr>
						</logic:iterate>
					</table>
				</logic:notEmpty> 
	  		</td>
		</tr> 
	</span>
	<!-- Legal Parents or Guardian Info ends-->
	<tr height='30px' class='referralDetailHead'><td colspan="6"><bean:message key="prompt.fileCheckout" /></td></tr>
	<tr>
		<td class='formDeLabel' nowrap='nowrap' width="5%"><bean:message key="prompt.checkedOutTo"/></td>
		<td class='formDe'><html:text property="checkedOutTo" size="10" maxlength="5" styleId="checkedOutTo"/></td>
		<td class='formDeLabel' nowrap='nowrap' width="5%"><bean:message key="prompt.dateOut"/></td>
		<td class='formDe'><html:text styleId="dateOutId" property="dateOut" size="10" maxlength="10" styleId="dateOut" /></td>
		<td class='formDeLabel' width="5%" nowrap='nowrap'><bean:message key="prompt.lastActionBy"/></td>
		<td class='formDe' width="50%"><html:text property="lastActionBy" size="5" maxlength="5" styleId="lastActionBy" disabled="true"/></td>
	</tr>
	<tr>
		<td class='formDeLabel' nowrap='nowrap' width="5%"><bean:message key="prompt.lastUpdate"/></td>
		<td class='formDe'><html:text property="lastUpdate" size="8" maxlength="8" styleId="lastUpdate" disabled="true"/></td>
		<td class='formDeLabel' nowrap='nowrap' width="5%"><bean:message key="prompt.operator"/></td>
		<td class='formDe' colspan="4"><html:text styleId="operatorId" property="operator" size="5" maxlength="5" disabled="true"/></td>
	</tr>
	<!-- END TAB BLUE BORDER TABLE -->
 	</table>
	<html:hidden styleId="action" name="juvenileReferralForm" property="action"/>
	<!-- BEGIN BUTTON TABLE -->
	<div class='spacer'></div> 
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center">
				<html:submit property="submitAction" styleId="nextBtn"><bean:message key="button.next"></bean:message></html:submit>&nbsp;
				<html:button property="submitAction" styleId="backBtn"><bean:message key="button.back"></bean:message></html:button>
				<input type="button" id="refreshBtn" onclick="goNav('/<msp:webapp/>displayCreateJuvenile.do?submitAction=Create Juvenile')" value="<bean:message key='button.refresh'/>"/>&nbsp;
				<html:button property="org.apache.struts.taglib.html.CANCEL" styleId="cancelBtn" onclick="goNav('/appshell/displayHome.do')">
  					<bean:message key="button.cancel"></bean:message>
  		 	 	</html:button>
			</td>
		</tr>
	</table>
</html:form>
<br>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
