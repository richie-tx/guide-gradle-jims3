<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- Used for updating Juvenile Profile Master Details --%>
<%-- 06/16/2005	 LDeen  		Create JSP --%>
<%-- 06/22/2005	 LDeen  		Revised JSP change boolean dropdowns to checkboxes --%>
<%-- 02/17/2006	 LDeen  		Revised JSP for 800x600 & Defect #28520 --%>
<%-- 08/21/2006  HRodriguez     Add calendar icon & implement new UI Guidelines --%>
<%-- 01/09/2007  CShimek        #37160 Added trimAll() to reduce possible validation errors --%>
<%-- 03/04/2013  CShimek        #75072 Added Education Id field --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<!-- Changes for JIMS200077276 Starts -->
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<!-- Changes for JIMS200077276 ends -->
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.PDCodeTableConstants"%>
<!-- Changes for JIMS200077276 Starts -->
<%@ page import="naming.Features" %>
<!-- Changes for JIMS200077276 ends -->



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<html:base />

<title><bean:message key="title.heading" /> - juvenileProfileMasterUpdate.jsp</title>

<%--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS--%>
<html:javascript formName="juvenileProfileMainForm" />


<%-- Javascript for emulated navigation --%>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/juvenileProfileInfo.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/date.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/PopupWindow.js"></script>

<script  type='text/javascript'>

var harrisCountyDropDownValue = <%=PDCodeTableConstants.HARRIS_COUNTY%>;
$(document).ready(function(){
	var raceId = '<bean:write name="juvenileProfileMainForm" property="raceId"/>' ;
	var originalRaceId = '<bean:write name="juvenileProfileMainForm" property="originalRaceId"/>';
	$("#countryIssuanceDesc").val( $('#countryIssuance option:selected').text() );

	if ( originalRaceId   ==="" ) {
		if ("I" === raceId ){
			$("#race").val("A");
		} else if ("A" === raceId ) {
			$("#race").val("O");
		} else if ("P" === raceId ) {
			$("#race").val("O");
		} else if ("B" === raceId ) {
			$("#race").val("B");
		} else if ("W" === raceId ) {
			$("#race").val("W");
		} else if ("U" === raceId ) {
			$("#race").val("X");
		}
	}
	
	$("#countryIssuance").change(function(){
		$("#countryIssuanceDesc").val( $('#countryIssuance option:selected').text() );
	})
	
	$("#next").click(function(){
		var passportNumber = $("#passportNum").val();
		var countryIssuance = $("#countryIssuance").val();
		var expDate	= $("#expDate").val();
		var currentDate = new Date().setHours(0,0,0,0);
		
		if ( passportNumber != "" ){
			
			if ( countryIssuance == "" ||
					countryIssuance.length == 0 ) {
				alert("Country of Issuance is required");
				$("#countryIssuance").focus();
				return false;
			}
			
			if ( expDate == "" || expDate.length == 0 ) {
				alert("Expiration Date is required");
				$("#expDate").focus();
				return false;
			}
		}
		
		if ( new Date( expDate ).setHours(0,0,0,0) < currentDate ) {
			alert("Expiration Date entered is past date");
		}
		
		return true;
	})
	
	
	
})
</script>

</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">

<%--BEGIN FORM TAG--%>
<html:form action="/displayJuvenileProfileUpdateSummary" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|5">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="title.update" /></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Enter required fields then click Next.</li>
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required"><bean:message key="prompt.requiredFields" />&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction" /></td>
	</tr>
	<tr> 
		<td class="required">+ All Driver's License information is required if any portion of D.L. information is entered.</td> 
	</tr> 
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>

<%--BEGIN JUVENILE PROFILE HEADER--%>
<tiles:insert page="/jsp/caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader" />
</tiles:insert>
<%--END JUVENILE PROFILE HEADER--%>

<%-- BEGIN DETAIL TABLE --%>
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign='top'><%-- BEGIN JUVENILE PROFILE TABS TABLE --%>
			<table width='100%' border="0" cellpadding="0" cellspacing="0">
				<tr>
 					<td valign='top'>
						<tiles:insert page="/jsp/caseworkCommon/juvenileProfileTabs.jsp" flush="true">
    						<tiles:put name="tabid" value="maintab" />
    					</tiles:insert>
  					</td>
  				</tr>
	  			<tr>
	  				<td bgcolor='#33cc66'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
	  			</tr>
	  		</table>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign='top' align='center'>
						<div class='spacer'></div>
<%-- BEGIN INTERVIEW INFO TABS OUTER TABLE --%>						
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td><%-- BEGIN INTERVIEW INFO TABS INNER TABLE --%>
									<table width='100%' border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td valign='top'><%--tabs start--%> 
											  <tiles:insert page="/jsp/caseworkCommon/interviewInfoTabs.jsp" flush="true">
  												<tiles:put name="tabid" value="profileInfo" />
  												<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
  											</tiles:insert> <%--tabs end--%>
											</td>
										</tr>
										<tr>
											<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
										</tr>
									</table>
								</td>
							</tr>

							<tr>
								<td>
<%-- BEGIN TAB BLUE BORDER TABLE --%>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
										<tr>
											<td valign='top' align='center'>
<%-- BEGIN MASTER INFO TABLE --%>
												<div class='spacer'></div>
	  											<table cellpadding='2' cellspacing='1' border='0' width="98%" class="borderTableBlue">
													<tr>
														<td valign='top' class='detailHead' colspan="4"><bean:message key="prompt.juvenileMasterInfo" /></td>
													</tr>
													<tr>
														<td class="formDeLabel" valign='top' nowrap='nowrap'><bean:message key="prompt.name" /></td>
														<td valign='top' class='formDe' colspan="3">
	  														<table border='0' cellspacing='1'>
	  															<tr>
	  																<td class='formDeLabel' colspan="3"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.last" /></td>
	  															</tr>
	  															<tr>
	  																<td class='formDe' colspan="3"><html:text property="lastName" size="50" maxlength="30" /></td>
	  															</tr>
	  															<tr>
	  																<td class='formDeLabel'><bean:message key="prompt.2.diamond" /><bean:message key="prompt.first" /></td>
	  																<td class='formDeLabel'><bean:message key="prompt.middle" /></td>
	  																<td class='formDeLabel'><bean:message key="prompt.suffix" /></td>
	  															</tr>
	  															<tr>
	  																<td class='formDe'><html:text property="firstName" size="50" maxlength="30" /></td>
	  																<td class='formDe'><html:text property="middleName" size="25" maxlength="15" /></td>
	  																<td class='formDe'><html:text property="nameSuffix" size="3" maxlength="3" /></td>
	  															</tr>
	  														</table>
														</td>
													</tr>
														<tr>
															<td class='formDeLabel' valign="top" nowrap='nowrap'> <bean:message key="prompt.preferredFirstName" /></td>
															<td class='formDe'><html:text property="preferredFirstName" size="50" maxlength="40" /></td>
														</tr>
														<tr>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.2.diamond" /><bean:message key="prompt.hispanic" /></td>
														<td class='formDe'>
															<html:select property="hispanic" styleId="hispanic">
  																<html:option value=""><bean:message key="select.generic" /></html:option>
  																<html:option value="Y">YES</html:option>
  																<html:option value="N">NO</html:option>
  															</html:select>
														</td>
														<td class='formDeLabel'><bean:message key="prompt.multiracial" /></td>
														<td class='formDe'><html:checkbox property="multiracial" value="Y" /></td>
													</tr>
													<tr>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.2.diamond" /><bean:message key="prompt.race" /></td>
														<td class='formDe'>
	  														<html:select property="originalRaceId" styleId="race">
	  															<html:option value=""><bean:message key="select.generic" /></html:option>
	  															<html:optionsCollection property="races" value="code" label="description" />
	  														</html:select>
														</td>

														<td class='formDeLabel'><bean:message key="prompt.sex" /></td>
														<td class='formDe'>
														  <html:select property="sexId">
  															<html:option value=""><bean:message key="select.generic" /></html:option>
  															<html:optionsCollection property="sexes" value="code" label="description" />
  														</html:select>
														</td>
													</tr>
												
													<tr>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.dateOfBirth" /></td>
														<td class='formDe'>
															<html:text styleId="profileInfoDateId" property="dateOfBirth" maxlength="10" size="10" />
														</td>
														
														    <td class='formDeLabel'><bean:message key="prompt.verifiedDOB" /></td>
														<logic:equal name="juvenileProfileMainForm" property="verifiedDOB" value="Y">    
														    <td class='formDe'>
															    <bean:message key="prompt.yes" /><html:radio property="verifiedDOB" value="Y" /> 
															    <bean:message key="prompt.no" /><html:radio property="verifiedDOB" value="N" disabled="true" />
														    </td>
														</logic:equal>
														<logic:notEqual name="juvenileProfileMainForm" property="verifiedDOB" value="Y">
														    <td class='formDe'>
															    <bean:message key="prompt.yes" /><html:radio property="verifiedDOB" value="Y" /> 
															    <bean:message key="prompt.no" /><html:radio property="verifiedDOB" value="N" />
														    </td>
														</logic:notEqual>
													</tr>
													<tr>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.birthCountry" /></td>
														<td class='formDe' colspan="3">
														  <html:select property="countryId">
  															<html:option value=""><bean:message key="select.generic" /></html:option>
  															<html:optionsCollection property="countries" value="code" label="description" />
  														</html:select>
														</td>
													</tr>
													<tr>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.birthState" /></td>
														<td class='formDe'>
														  <html:select name="juvenileProfileMainForm" property="stateId">
																<html:option value=""><bean:message key="select.generic" /></html:option>
																<html:optionsCollection property="states" value="code" label="description" />
															</html:select>
														</td>
														<td class='formDeLabel'><bean:message key="prompt.birthCounty" /></td>
														<td class='formDe'>
														  <html:select name="juvenileProfileMainForm" property="birthCountyId">
																<html:option value=""><bean:message key="select.generic" /></html:option>
																<html:optionsCollection property="counties" value="code" label="description" />
															</html:select>
														</td>
													</tr>
													<tr>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.birthCity" /></td>
														<td class='formDe'><html:text property="cityId" size="20" maxlength="30" /></td>
														<td class='formDeLabel'><bean:message key="prompt.2.diamond" /><bean:message key="prompt.usCitizen" /></td>
														<td class='formDe'>
															<html:select size="1" name="juvenileProfileMainForm" property="isUSCitizenId">
																<html:option value=""><bean:message key="select.generic" /></html:option>
																<html:optionsCollection property="isUSCitizenList" value="code" label="description" />
															</html:select>
														</td>
													</tr>
													<tr>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.nationality" /></td>
														<td class='formDe' colspan="3">
														  	<html:select property="nationalityId">
  																<html:option value=""><bean:message key="select.generic" /></html:option>
  																<html:optionsCollection property="countries" value="code" label="description" />
  															</html:select>
														</td>
													</tr>
													<tr>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.ethnicity" /></td>
														<td class='formDe' colspan="3">
														  	<html:select property="ethnicityId">
  																<html:option value=""><bean:message key="select.generic" /></html:option>
  																<html:optionsCollection property="ethnicities" value="code" label="description" />
  															</html:select>
														</td>
													</tr>
													<tr>
														<td class='formDeLabel'><bean:message key="prompt.primaryLanguage" /></td>
														<td class='formDe' colspan="3">
															<html:select property="primaryLanguageId">
  																<html:option value=""><bean:message key="select.generic" /></html:option>
  																<html:optionsCollection property="languages" value="code" label="description" />
  															</html:select>
														</td>
													</tr>
													<tr>
														<td class='formDeLabel'><bean:message key="prompt.secondaryLanguage" /></td>
														<td class='formDe' colspan="3">
															<html:select property="secondaryLanguageId">
  																<html:option value=""><bean:message key="select.generic" /></html:option>
  																<html:optionsCollection property="languages"  value="code" label="description" />
	  														</html:select>
														</td>
													</tr>
													<tr class='detailHead'>
														<td colspan="4"><bean:message key="prompt.adoptionInformation"/></td>
													</tr>
					                    			<tr>
					                    				<td class='formDeLabel' valign="top" nowrap='nowrap'><bean:message key="prompt.isAdopted" /></td>
					                    				<td class='formDe' colspan='3'>
															<bean:message key="prompt.yes" /><html:radio styleId='adopted' property="adopted" value="true"/> 
															<bean:message key="prompt.no"  /><html:radio  styleId='adopted' property="adopted" value="false"/>
														</td>
													</tr>
													<tr id='adoptFailedRow' class='hidden'>
					                    				<td class='formDeLabel' valign="top" nowrap='nowrap'><bean:message key="prompt.failedPlacements" /></td>
					                    				<td class='formDe' colspan='3'><html:text property="failedPlacements" size="2" maxlength="2"/></td>
					                    			</tr>
					                    			<tr id='adoptCommentsRow' class='hidden' valign='top'>
					                    				<td class='formDeLabel' valign="top"><bean:message key="prompt.adoptionComments" /></td>
					                    				<td class='formDe' colspan="3"><html:text property="adoptionComments" size="55" maxlength="40" /></td>
					                    			</tr>
					                    			<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_PI_ID_V%>'>
													<tr class='detailHead'>
														<td colspan="4"><bean:message key="prompt.idInfo" /></td>
													</tr>
													<tr>
														<td class='formDeLabel' valign="top" nowrap='nowrap'> + <bean:message key="prompt.dlNumber" /></td>
														<td class='formDe'><html:text styleId="driverLicenseNum" property="driverLicenseNum" size="25" maxlength="25" /></td>
														<td class='formDeLabel' valign="top" nowrap='nowrap'> + <bean:message key="prompt.dlState" /></td>
														<td class='formDe'>
															<html:select name="juvenileProfileMainForm" property="driverLicenseStateId">
  																<html:option value=""><bean:message key="select.generic" /></html:option>
  																<html:optionsCollection property="states" value="code" label="description" />
  															</html:select>
														</td>
													</tr>
													<tr>
														<td class='formDeLabel' valign="top" nowrap='nowrap'> + <bean:message key="prompt.dlClass" /></td>
														<td class='formDe'>
															<html:select name="juvenileProfileMainForm" property="driverLicenseClassId">
  																<html:option value=""><bean:message key="select.generic" /></html:option>
  																<html:optionsCollection property="driverLicenseClasses" value="code" label="description" />
	  														</html:select>
														</td>
														<td class='formDeLabel' valign="top"> + <bean:message key="prompt.dlExpDate" /></td>
														<td class='formDe'><html:text name="juvenileProfileMainForm" styleId="dlExpDateId" property="driverLicenseExpireDate" size="10" maxlength="10" /> 
														</td>
													</tr>
													<tr>
														<td class='formDeLabel' valign="top" nowrap='nowrap'><bean:message key="prompt.2.diamond" /><bean:message key="prompt.SSN" /></td>
														<td class='formDe' valign="top">
															<html:text property="SSN.SSN1" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" disabled="true"/>- 
															<html:text property="SSN.SSN2" size="2" maxlength="2" onkeyup="return autoTab(this, 2);" disabled="true"/>- 
															<html:text property="SSN.SSN3" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" disabled="true"/>
															
															&nbsp;<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_SSN_U%>'><html:submit property="submitAction" styleId="ssnUpdateSubmit"><bean:message key="button.update" /></html:submit></jims2:isAllowed></td>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.stateidSID" /></td>
														<td class='formDe'><html:text property="SID" size="14" maxlength="20" /></td>
													</tr>
													<tr>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.sheriffOffice#" /></td>
														<td class='formDe'><html:text property="SONum" size="10" maxlength="10" /></td>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.educationID" /></td>
														<td class='formDe'><html:text property="educationId" size="15" maxlength="15" /></td>	
													</tr>
													<tr>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.dhsNumber" /></td>
														<td class='formDe'><html:text property="DHSNum" size="20" maxlength="20" /></td>
														<!-- Changes for JIMS200077276 Starts -->
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.studentID" /></td>
														<td class='formDe'>
															<logic:equal name="juvenileProfileMainForm" property="hasFeature" value="true">
																<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_SCL_STU_U%>'>
																	<html:text  property="studentId" size="8" maxlength="8" />
																</jims2:isAllowed>	
															</logic:equal>
															<logic:notEqual name="juvenileProfileMainForm" property="hasFeature" value="true">
																<html:text  property="studentId" size="8" maxlength="8" disabled="true"/>
															</logic:notEqual>
														</td>
														<!-- Changes for JIMS200077276 Ends -->
													</tr>
													<tr>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.tsdsID" /></td>
														<td class='formDe'><html:text property="TSDSId" size="12" maxlength="12" /></td>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.alienReg#" /></td>
														<td class='formDe'><html:text property="alienNum" size="20" maxlength="20" /></td>
													</tr>
													<tr>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.fbiNumber" /></td>
														<td class='formDe' colspan="3"><html:text property="FBINum" size="15" maxlength="20" /></td>
													</tr>
													<tr>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.passportNumber"/></td>
														<td class='formDe'><html:text styleId="passportNum" name="juvenileProfileMainForm" property="passportNumber" size="15" maxlength="20" /></td>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.countryOfIssuance"/></td>
														<td>
															<html:select styleId="countryIssuance" size="1" name="juvenileProfileMainForm" property="countryOfIssuance">
																<option value="">Please Select</option>
																<html:optionsCollection property="nationalityList" value="code" label="description" />
															</html:select>
															<html:hidden styleId="countryIssuanceDesc" name="juvenileProfileMainForm" property="countryOfIssuanceDesc"/>
														</td>
													</tr>
													<tr>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.expirationDate"/></td>
														<td class='formDe' colspan="3"><html:text name="juvenileProfileMainForm" styleId="expDate" property="passportExpirationDate"/></td>
													</tr>
													</jims2:isAllowed>
													<tr class='detailHead'>
														<td colspan="4"><bean:message key="prompt.attributes" /></td>
													</tr>
													<tr>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.natural" /><br>
														<bean:message key="prompt.eyeColor" /></td>
														<td class='formDe'>
															<html:select  property="naturalEyeColorId">
  																<html:option value=""><bean:message key="select.generic" /></html:option>
  																<html:optionsCollection property="eyeColors" value="code" label="description" />
  															</html:select>
														</td>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.natural" /><br>
														<bean:message key="prompt.hairColor" /></td>
														<td class='formDe'>
														 	<html:select property="naturalHairColorId">
  																<html:option value=""><bean:message key="select.generic" /></html:option>
  																<html:optionsCollection property="hairColors"value="code" label="description" />
  															</html:select>
														</td>
													</tr>
													<tr>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.complexion" /></td>
														<td class='formDe'>
															<html:select property="complexionId">
  																<html:option value=""><bean:message key="select.generic" /></html:option>
  																<html:optionsCollection property="complexions" value="code" label="description" />
  															</html:select>
														</td>

														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.religion" /></td>
														<td class='formDe'>
															<html:select property="religionId">
  																<html:option value=""><bean:message key="select.generic" /></html:option>
  																<html:optionsCollection property="religions" value="code" label="description" />
  															</html:select>
														</td>
													</tr>
													<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_MAS_PI_ID_V%>'>
													<tr class='detailHead'>
														<td colspan="4"><bean:message key="prompt.dnaInfo" /></td>
													</tr>
													<tr>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.dnaSample#" /></td>
														<td class='formDe'><html:text property="DNASampleNum" size="20" maxlength="20" /></td>
														<td class='formDeLabel' nowrap='nowrap'><bean:message key="prompt.dateSent" /><br>to DPS</td>
														<td class='formDe'>
														  <html:text styleId="sentToDPSDateId" property="dateSenttoDPS" size="10" maxlength="10" />
														</td>
													</tr>
													</jims2:isAllowed>
  													<input type="hidden" name="clearProfileUpdateCheckBoxes" value="true" />
  												</table>
<%-- END MASTER INFO TABLE --%>
  												<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
	  											<table width="98%" border="0" align="center">
													<tr>
														<td align="center">
															<html:button property="org.apache.struts.taglib.html.BUTTON" styleId="masterUpdateBack"><bean:message key="button.back" /></html:button>&nbsp;
															<html:submit property="submitAction" styleId="next"><bean:message key="button.next" /></html:submit>&nbsp;
															<input type="button" id="masterUpdateCancel" value="<bean:message key='button.cancel' />" />
														</td>
													</tr>
	        									</table>
<%-- END BUTTON TABLE --%>        									
	        									<div class='spacer'></div>
		        							</td>
    		    						</tr>
        		  					</table>
	  							</td>
  							</tr>
    					</table>
<%-- END TAB BLUE BORDER TABLE --%>    				
						<div class='spacer'></div>
					</td>
  				</tr>
    		</table>
<%-- END INTERVIEW INFO TABS OUTER TABLE --%>
			<div class='spacer'></div>
    	</td>
	</tr>
</table>

<logic:equal name="juvenileProfileMainForm" property="matchDetectedSSN" value="true">
  <html:hidden name="juvenileMemberSearchForm" property="matchDetectedSSN" styleId="matchDetectedSSN" value="true"/>
</logic:equal>


<%-- END DETAIL TABLE --%>

</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>

<html:errors></html:errors>

</html:html>
