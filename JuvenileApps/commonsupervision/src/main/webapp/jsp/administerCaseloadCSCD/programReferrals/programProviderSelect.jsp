<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/25/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 04/13/2010 RYoung - #64883 Print Packet no longer displaying SP and Program Infor -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@ page import="naming.PDCodeTableConstants"%>
<%@ page import="naming.CSAdministerProgramReferralsConstants"%>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/programProviderSelect.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/administerProgramReferrals/csProgramReferrals.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/case_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/sorttable.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script>

var selectedIdArr = [];

function validate(theForm)
{
	var oneSPSelected = false;
	var action = document.getElementsByName("action")[0].value;

	if(action=="create")
	{
		var userEnteredSP = document.getElementsByName("userEnteredServiceProvider")[0];
		
		if(userEnteredSP.checked)
		{
			oneSPSelected= true;
	
			if(!(validatePhone("userEnteredSPPhone.areaCode","userEnteredSPPhone.prefix","userEnteredSPPhone.fourDigit","Service Provider Phone Number",theForm))){
				return false;
			}
			if(!(validatePhone("userEnteredSPFax.areaCode","userEnteredSPFax.prefix","userEnteredSPFax.fourDigit","Fax Number",theForm))){
				return false;
			}
			
			clearAllValArrays();
	
			customValRequired("userEnteredServiceProviderName","<bean:message key='errors.required' arg0='Service Provider Name'/>","");
			customValMinLength("userEnteredServiceProviderName","<bean:message key='errors.minlength' arg0='Service Provider Name' arg1='1'/>","1");
			customValMaxLength("userEnteredServiceProviderName","<bean:message key='errors.maxlength' arg0='Service Provider Name' arg1='100'/>","100");
			addAlphaNumericNoFirstSpacewSymbolsValidation("userEnteredServiceProviderName","<bean:message key='errors.comments' arg0='Service Provider Name'/>");
			
			customValRequired("userEnteredSPPhone.areaCode","<bean:message key='errors.required' arg0='Service Provider Phone Number Area Code'/>","");
			addNumericValidation("userEnteredSPPhone.areaCode","<bean:message key='errors.integer' arg0='Service Provider Phone Number Area Code'/>","");
			customValMinLength("userEnteredSPPhone.areaCode","<bean:message key='errors.minlength' arg0='Service Provider Phone Number Area Code' arg1='3'/>","3");
			addNumericValidation("userEnteredSPPhone.prefix","<bean:message key='errors.integer' arg0='Service Provider Phone Number Prefix'/>","");
			customValMinLength("userEnteredSPPhone.prefix","<bean:message key='errors.minlength' arg0='Service Provider Phone Number Prefix' arg1='3'/>","3");
			addNumericValidation("userEnteredSPPhone.fourDigit","<bean:message key='errors.integer' arg0='Service Provider Phone Number Last 4 Digits'/>","");
			customValMinLength("userEnteredSPPhone.fourDigit","<bean:message key='errors.minlength' arg0='Service Provider Phone Number Last 4 Digits' arg1='4'/>","4");
			
			addNumericValidation("userEnteredSPFax.areaCode","<bean:message key='errors.integer' arg0='Service Provider Fax Number Area Code'/>","");
			customValMinLength("userEnteredSPFax.areaCode","<bean:message key='errors.minlength' arg0='Service Provider Fax Number Area Code' arg1='3'/>","3");
			addNumericValidation("userEnteredSPFax.prefix","<bean:message key='errors.integer' arg0='Service Provider Fax Number Prefix'/>","");
			customValMinLength("userEnteredSPFax.prefix","<bean:message key='errors.minlength' arg0='Service Provider Fax Number Prefix' arg1='3'/>","3");
			addNumericValidation("userEnteredSPFax.fourDigit","<bean:message key='errors.integer' arg0='Service Provider Fax Number Last 4 Digits'/>","");
			customValMinLength("userEnteredSPFax.fourDigit","<bean:message key='errors.minlength' arg0='Service Provider Fax Number Last 4 Digits' arg1='4'/>","4");
	
			return validateCustomStrutsBasedJS(theForm);
		}
		else
		{
			var elements = document.getElementsByName("selectedSPIds");
			var spLen = elements.length;
			
			for(index=0; index < spLen; index++)
			{
				var filteredSP = elements[index];
	
				if(filteredSP.checked)
				{
					oneSPSelected = true;
					break;
				}
			}
		}
	}
	else if(action=="updateInit")
	{
		var serviceProviderObjList = document.getElementsByName("serviceProviderId");
		var len = serviceProviderObjList.length;
		var selSPObj = "";
		
		for(var i=0; i < len; i++)
		{
			if(serviceProviderObjList[i].checked)
			{
				oneSPSelected = true;
				selSPObj = serviceProviderObjList[i]; 
				break;
			}
		}

		if(oneSPSelected && (selSPObj.value=="USER_ENTERED"))
		{
			document.getElementsByName("userEnteredServiceProvider")[0].value = "true";
			
			if(!(validatePhone("userEnteredSPPhone.areaCode","userEnteredSPPhone.prefix","userEnteredSPPhone.fourDigit","Service Provider Phone Number",theForm))){
				return false;
			}
			if(!(validatePhone("userEnteredSPFax.areaCode","userEnteredSPFax.prefix","userEnteredSPFax.fourDigit","Fax Number",theForm))){
				return false;
			}
			
			clearAllValArrays();
	
			customValRequired("userEnteredServiceProviderName","<bean:message key='errors.required' arg0='Service Provider Name'/>","");
			customValMinLength("userEnteredServiceProviderName","<bean:message key='errors.minlength' arg0='Service Provider Name' arg1='1'/>","1");
			customValMaxLength("userEnteredServiceProviderName","<bean:message key='errors.maxlength' arg0='Service Provider Name' arg1='100'/>","100");
			addAlphaNumericNoFirstSpacewSymbolsValidation("userEnteredServiceProviderName","<bean:message key='errors.comments' arg0='Service Provider Name'/>");
			
			customValRequired("userEnteredSPPhone.areaCode","<bean:message key='errors.required' arg0='Service Provider Phone Number Area Code'/>","");
			addNumericValidation("userEnteredSPPhone.areaCode","<bean:message key='errors.integer' arg0='Service Provider Phone Number Area Code'/>","");
			customValMinLength("userEnteredSPPhone.areaCode","<bean:message key='errors.minlength' arg0='Service Provider Phone Number Area Code' arg1='3'/>","3");
			addNumericValidation("userEnteredSPPhone.prefix","<bean:message key='errors.integer' arg0='Service Provider Phone Number Prefix'/>","");
			customValMinLength("userEnteredSPPhone.prefix","<bean:message key='errors.minlength' arg0='Service Provider Phone Number Prefix' arg1='3'/>","3");
			addNumericValidation("userEnteredSPPhone.fourDigit","<bean:message key='errors.integer' arg0='Service Provider Phone Number Last 4 Digits'/>","");
			customValMinLength("userEnteredSPPhone.fourDigit","<bean:message key='errors.minlength' arg0='Service Provider Phone Number Last 4 Digits' arg1='4'/>","4");
			
			addNumericValidation("userEnteredSPFax.areaCode","<bean:message key='errors.integer' arg0='Service Provider Fax Number Area Code'/>","");
			customValMinLength("userEnteredSPFax.areaCode","<bean:message key='errors.minlength' arg0='Service Provider Fax Number Area Code' arg1='3'/>","3");
			addNumericValidation("userEnteredSPFax.prefix","<bean:message key='errors.integer' arg0='Service Provider Fax Number Prefix'/>","");
			customValMinLength("userEnteredSPFax.prefix","<bean:message key='errors.minlength' arg0='Service Provider Fax Number Prefix' arg1='3'/>","3");
			addNumericValidation("userEnteredSPFax.fourDigit","<bean:message key='errors.integer' arg0='Service Provider Fax Number Last 4 Digits'/>","");
			customValMinLength("userEnteredSPFax.fourDigit","<bean:message key='errors.minlength' arg0='Service Provider Fax Number Last 4 Digits' arg1='4'/>","4");
	
			return validateCustomStrutsBasedJS(theForm);
		}
	}
	
	if(!oneSPSelected)
	{
		alert("Please select at least 1 Service Provider.");
		return false;
	}

	if(action=="create")
	{
		setSelectedSPString();
	}
	return true;	
}



function displayPacket()
{
	var webApp = "/<msp:webapp/>";
	var pageState = document.getElementsByName("currentPageState");
	var res = "selectedSPIds";
	if ( pageState[0].value == "updateInit") {
		res = "serviceProviderId";
	}
	var elements = document.getElementsByName(res);
	var spLen = elements.length;
	var separator = ",";
	var spIdsString = "";
	
	for( var index=0; index < spLen; index++ )
	{
		if( elements[index].checked )
		{
			var temp = elements[index].value;
			spIdsString += temp + separator ;
		}
	}
	
	if( spIdsString == "" )
	{
		alert("Please select at least 1 Service Provider.");
		return false;
	}

    openWindow(webApp + "displayServiceProviderPacket.do?submitAction=Link&selectedSPIdsString=" + spIdsString);
}

function setSelectedSPString()
{
	var elements = document.getElementsByName("selectedSPIds");
	var spLen = elements.length;
	var separator = "/";
	var spIdsString = "";
	
	for(var index=0; index < spLen; index++)
	{
		if(elements[index].checked)
		{
			var temp = elements[index].value;
			spIdsString = spIdsString + separator + temp;
		}
	}

	document.getElementsByName("selectedSPIdsString")[0].value = spIdsString;
}

</script>
</head>

<body topmargin="0" leftmargin="0"
	onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displayProgRefSPSelect" target="content">
	<div align="center">
	<html:hidden name="cscProgRefForm" property="action" />
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif"
				height="5"></td>
		</tr>
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><!--tabs start--> <tiles:insert
						page="../../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="caseloadTab" />
					</tiles:insert> <!--tabs end--></td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif"
						height="5"></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center"><!--header area start-->
					<table width="98%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td bgcolor="#cccccc" colspan="2">
								<!--header start-->
								<tiles:insert page="../../common/caseloadHeaderCase.jsp" flush="true">
								</tiles:insert> 
								<!--header end-->
							</td>
						</tr>
						<tr>
							<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
						</tr>
					</table>
					<!--header area end--> <!--casefile tabs start-->
					<table width="98%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top"><!--tabs start--> <tiles:insert
								page="../../common/caseloadCSCDSubTabs.jsp" flush="true">
								<tiles:put name="tab" value="ProgramReferralsTab" />
							</tiles:insert> <!--tabs end--></td>
						</tr>
						<tr>
							<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif"
								height="5"></td>
						</tr>
					</table>
					<table width="98%" border="0" cellpadding="0" cellspacing="0"
						class="borderTableGreen">
						<tr>
							<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
						</tr>
						<tr>
							<td valign="top" align="center">
							<!-- BEGIN HEADING TABLE -->
							<table width="100%">
								<tr>
									<td align="center" class="header"><bean:message
										key="title.CSCD" />&nbsp;- 
										<logic:equal name="cscProgRefForm" property="action" value="<%= CSAdministerProgramReferralsConstants.ACTION_UPDATE_INIT %>">
											<bean:message key="title.update" /><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|10">
										</logic:equal> 
										<logic:equal name="cscProgRefForm" property="action" value="<%= CSAdministerProgramReferralsConstants.ACTION_CREATE %>">
											<bean:message key="prompt.initiate" /><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|4">
										</logic:equal>
										<bean:message key="prompt.programReferral" />&nbsp;-&nbsp;<bean:message	key="prompt.select" />&nbsp;<bean:message key="prompt.serviceProvider" /></td>
										<input type="hidden" name="currentPageState" value=<bean:write name="cscProgRefForm" property="action" /> >
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
							<table width="98%" border="0" cellpadding="2" cellspacing="1"
								align="center">
								<tr>
									<td>
									<ul>
										<li>Select Service Providers. Click Next.</li>
										<li>To print forms based on referral types selected click
										links below Referral Types.</li>
										<li>Click Print Packet to print information for selected
										service providers.</li>
									</ul>
									</td>
								</tr>
								<tr>
									<td class="required"><bean:message key="prompt.3.diamond" /><bean:message
										key="prompt.requiredFieldsInstruction" /></td>
								</tr>
							</table>
							 
							
							<!-- reset Service Provider checkboxes -->
							<input type="hidden" name="clearSPCheckBoxes" value="true" />
							<input type="hidden" name="selectedSPIdsString" value="1" />
							
							<!--Conditions List start-->
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td  colspan="2"><!--conditions list tile start-->
									<tiles:insert page="prConditionsListTile.jsp" flush="true">
										<tiles:put name="condList" beanName="cscProgRefForm"
											beanProperty="conditionsList"></tiles:put>
									</tiles:insert> <!--conditions list tile end--></td>
								</tr>
							</table>
							<!--Conditions List end-->
							<div class="spacer4px"></div>
							<table width="98%" cellpadding="0" cellspacing="0" border="0">
								<tr>
									<td width="100%" valign="top">
									<table width="100%" cellpadding="2" cellspacing="0"
										class="borderTableBlue">
										<tr class="detailHead">
											<td><bean:message key="prompt.programReferral" />&nbsp;<bean:message
												key="prompt.info" /></td>
										</tr>
										<tr>
											<td><% int ReferralTypeCounter=0; %>
											<table width="100%" cellpadding="4" cellspacing="1">
												<tr>
													<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.referralTypes" /></td>
													<td colspan="3" class="formDe">
														<% int myCounterVal=0; %>
														<div>
															<logic:iterate id="referralsIndex" name="cscProgRefForm" property="selectedReferralTypesList">
																<% if (myCounterVal > 0)
																	{
																		myCounterVal=0;
																	}
																%>																	
																<bean:write name="referralsIndex" property="referralTypeNum" />.&nbsp;<bean:write name="referralsIndex" property="referralTypeDesc" />
																<div style="padding-left:15px">
																	<logic:iterate id="referralFormBean" name="referralsIndex" property="referralFormsList">
																		<% if (myCounterVal > 0 ){ %>
																	
																		   |
																		<% }
																		else
																		{
																			myCounterVal++;
																		}
																		%>
																		<a href="/<msp:webapp/>handleReferralForm.do?submitAction=View&referralFormId=<bean:write name='referralFormBean' property='referralFormId'/>&referralFormName=<bean:write name='referralFormBean' property='referralFormName'/>&referralTypeCode=<bean:write name='referralsIndex' property='referralTypeCode'/>" class="blackSubNav"><bean:write name="referralFormBean" property="referralFormName" /></a>
																	</logic:iterate>
																</div>
															</logic:iterate>
														</div>
													</td>
												</tr>
											</table>
											</td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							<div class="spacer4px"></div>
							<table width="98%" cellpadding="0" cellspacing="0" border="0">
								<tr>
									<td width="100%" valign="top">
									<table width="100%" cellpadding="2" cellspacing="0"
										class="borderTableBlue">
										<tr class="detailHead">
											<td><bean:message key="prompt.filterServiceProviders" /></td>
										</tr>
										<tr>
											<td>
											<table width="100%" cellpadding="4" cellspacing="1">
												<tr>
													<td class="formDeLabel" nowrap width="1%"><bean:message
														key="prompt.region" /></td>
													<td class="formDe">
														<html:select name="cscProgRefForm"
															property="selectedRegionIds" multiple="true" size="3"  onchange="multiSelectFix(this)">>
															<html:option value="">Please Select one or more</html:option>
															<jims2:codetable codeTableName="<%=PDCodeTableConstants.LOCATION_REGION%>" sort="true"></jims2:codetable>
														</html:select>
													</td>
													<td class="formDeLabel" nowrap width="1%"><bean:message
														key="prompt.sexSpecific" /></td>
													<td class="formDe">
														<html:select name="cscProgRefForm" property="selectedSexSpecificId">
															<html:option value=""><bean:message key="select.generic"/></html:option>
															<jims2:codetable codeTableName="<%=PDCodeTableConstants.ASP_CS_SEX_SPECIFIC%>" sort="true"></jims2:codetable>
														</html:select>
													</td>
												</tr>
												<tr>
													<td class="formDeLabel" nowrap width="1%"><bean:message
														key="prompt.languageOffered" /></td>
													<td class="formDe">
														<html:select name="cscProgRefForm" property="selectedLanguagesIds" multiple="true" size="3" onchange="multiSelectFix(this)">
															<html:option value="">Please Select one or more</html:option>
															<jims2:codetable codeTableName="<%=PDCodeTableConstants.ASP_PROGRAM_LANGUAGE%>" sort="true"></jims2:codetable>
														</html:select>
													</td>
													<td class="formDeLabel" nowrap width="1%"><bean:message
														key="prompt.contractProgram" /></td>
													<td class="formDe">
													<html:select name="cscProgRefForm" property="selectedContractProgram">
														<html:option value=""><bean:message key="select.generic"/></html:option>
														<html:option value="true">Yes</html:option>
														<html:option value="false">No</html:option>
													</html:select></td>
												</tr>
												<tr>
													<td class="formDeLabel" nowrap></td>
													<td class="formDe" colspan="3">
														<html:submit property="submitAction"><bean:message key="button.filter"/></html:submit>
													 	<html:submit property="submitAction"><bean:message key="button.refresh"/></html:submit>
												 	</td>
												</tr>
											</table>
											</td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							<div class="spacer4px"></div>

							<div class="borderTableBlue" style="width:98%">
							<table width="100%" border="0" cellpadding="2" cellspacing="0">
								<tr class="detailHead">
									<td><bean:message key="prompt.3.diamond" /><bean:message key="prompt.serviceProviders" /></td>
								</tr>
								<tr>
									<td><% int RecordCounter2=0;
											   String bgcolor2=""; %>
									<table width="100%" cellpadding="2" cellspacing="1"
										id="uniqueID">
										<tr class="formDeLabel">
											<td width="1%">
												<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_CREATE%>">
													<input type="checkbox" name="checkAll" value="" onclick="spCheckAll(this);" >
												</logic:equal>	
											</td>
											<td title="Service Provider Name" nowrap><bean:message key="prompt.serviceProviderName" />
												<jims2:sortResults beanName="cscProgRefForm" results="filteredSPList" primaryPropSort="serviceProviderName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" levelDeep="3" />
											</td>
											<td width="1%" title="Corresponding Referral Type"><bean:message key="prompt.refType" />
												<jims2:sortResults beanName="cscProgRefForm" results="filteredSPList" primaryPropSort="serviceProviderRefTypes" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" levelDeep="3" />
											</td>
											<td><bean:message key="prompt.region" /> 
												<jims2:sortResults beanName="cscProgRefForm" results="filteredSPList" primaryPropSort="serviceProviderRegions" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" levelDeep="3" />
											</td>
											<td><bean:message key="prompt.phoneNo" /> 
												<jims2:sortResults beanName="cscProgRefForm" results="filteredSPList" primaryPropSort="serviceProviderPhoneNumber.formattedPhoneNumber" primarySortType="String" defaultSortOrder="ASC" sortId="4" levelDeep="3" />
											</td>
											<td><bean:message key="prompt.fax" />
												<jims2:sortResults beanName="cscProgRefForm" results="filteredSPList" primaryPropSort="serviceProviderFaxNumber.formattedPhoneNumber" primarySortType="String" defaultSortOrder="ASC" sortId="5" levelDeep="3" />
											</td>
											<td nowrap width="5%"><bean:message key="prompt.inHouse" />
												<jims2:sortResults beanName="cscProgRefForm" results="filteredSPList" primaryPropSort="serviceProviderInHouseAsStr" primarySortType="STRING" defaultSortOrder="ASC" sortId="6" levelDeep="3" />
											</td>
										</tr>
										<logic:iterate id="spIndex" name="cscProgRefForm" property="filteredSPList">
											<tr class='<% RecordCounter2++;
															  bgcolor2 = "alternateRow";                      
															  if (RecordCounter2 % 2 == 1)
																  bgcolor2 = "normalRow";
															   out.print(bgcolor2); %>'>
													<td width="1%">
														<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_UPDATE_INIT%>">
															<html:radio idName="spIndex" property="serviceProviderId" value="serviceProviderId" />
														</logic:equal>
														<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_CREATE%>">  
														   <input type="checkbox" name="selectedSPIds" value="<bean:write name='spIndex' property='serviceProviderId'/>" onclick="uncheckCheckAll(this, 'checkAll')" >
														</logic:equal> 
													</td>
													<td>
														<a href="javascript:openWindow('/<msp:webapp/>displayCSCServiceProviderUpdate.do?submitAction=View Details&selectedValue=<bean:write name="spIndex" property="serviceProviderId" />')"><bean:write name="spIndex" property="serviceProviderName" /></a>
													</td>
													<td class="referralTypeNumber"><bean:write name="spIndex" property="serviceProviderRefTypes" /></span></td>
													<td><bean:write name="spIndex" property="serviceProviderRegions" /></td>
													<td><bean:write name="spIndex" property="serviceProviderPhoneNumber.formattedPhoneNumber" /></td>
													<td><bean:write name="spIndex" property="serviceProviderFaxNumber.formattedPhoneNumber" /></td>
													<td align="center"><bean:write name="spIndex" property="serviceProviderInHouseAsStr" /></td>
											</tr>
										</logic:iterate>
										<input type="hidden" name="spSize" value="<%= RecordCounter2 %>" />
										<tr	class='<%= (++RecordCounter2 % 2 == 1)?"normalRow":"alternateRow" %>'>
											<td width="1%">
												<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_UPDATE_INIT%>">
													<html:radio name="cscProgRefForm" property="serviceProviderId" value="<%= CSAdministerProgramReferralsConstants.USER_ENTERED_SP %>"></html:radio>
													<html:hidden name="cscProgRefForm" property="userEnteredServiceProvider" />
												</logic:equal>
												<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_CREATE%>">  
												  <html:checkbox name="cscProgRefForm" property="userEnteredServiceProvider" value="true" onclick="uncheckCheckAll(this, 'checkAll')"/>
												</logic:equal> 
											</td>
											<td><html:text name="cscProgRefForm" property="userEnteredServiceProviderName" size="50" maxlength="100" /></td>
											<td class="referralTypeNumber">
												<html:select name="cscProgRefForm" property="userEnteredServiceProviderRefTypeCd" >
													<html:optionsCollection name="cscProgRefForm" property="referralTypesList" label="refNum" value="refTypeCd"/>
												</html:select></td>
											<td>&nbsp;</td>
											<td>
											 	<html:text name="cscProgRefForm" property="userEnteredSPPhone.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
	  										   	<html:text name="cscProgRefForm" property="userEnteredSPPhone.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
	  										  	<html:text name="cscProgRefForm" property="userEnteredSPPhone.fourDigit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
											</td>
											<td>
											 	<html:text name="cscProgRefForm" property="userEnteredSPFax.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
	  										   	<html:text name="cscProgRefForm" property="userEnteredSPFax.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />-
	  										  	<html:text name="cscProgRefForm" property="userEnteredSPFax.fourDigit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
											</td>
											<td align="center">&nbsp;</td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</div>
							<!-- BEGIN BUTTON TABLE -->
							<table border="0" cellpadding="2" cellspacing="1">
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="return validate(this.form) && setSelectedSPString();"><bean:message key="button.next" /></html:submit>
										<logic:notEqual name="cscProgRefForm" property="action" value="<%= CSAdministerProgramReferralsConstants.ACTION_UPDATE_INIT %>">
										    <html:button property="submitAction" onclick="displayPacket();"><bean:message key="button.printPacket"/></html:button>
										</logic:notEqual>    
										<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
										<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit></td>
																</tr>

							</table>
							<!-- END BUTTON TABLE --></td>
						</tr>
					</table>
					<div class="spacer4px"></div>
					</td>
				</tr>
			</table>
			<br>
			</td>
		</tr>
	</table>
	<br>
	</div>
	<br>
</html:form>
<div align="center">[<a href="#top">Back to Top</a>]</div>
</body>
</html:html>
