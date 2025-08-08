<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/22/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 11/19/2009 C Shimek          - #62124 revised Refresh button to call script -->
<!-- RYoung    01/30/2012 Defect#74824 CSCD: Add Filter to exclude inactive code -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@ page import="naming.Features" %>
<%@ page import="naming.PDCodeTableConstants" %>
<%@ page import="naming.CSAdministerProgramReferralsConstants" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/programReferralCaseload/serviceProviderSearch.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<script>

	function renderSearch(theSelectElement,theForm)
	{
		var theSelectValue = theSelectElement.options[theSelectElement.selectedIndex].value;
		switch(theSelectValue)
		{
			case "<%=CSAdministerProgramReferralsConstants.SERVICE_PROVIDER_SEARCH%>":
				document.getElementsByName('programName')[0].value="";
				document.getElementsByName('cstsCode')[0].selectedIndex="";
				document.getElementsByName('programGroupId')[0].selectedIndex="";
				document.getElementsByName('programTypeId')[0].selectedIndex="";
				//document.getElementsByName('programSubTypeId')[0].selectedIndex="";
				
				show('spRequiredFieldHeader','1');
				show('spDetailsHeader','1');
				show('spDetails','1');
				show('pgmRequiredFieldHeader','0');
				show('pgmDetailsHeader','0');
				show('pgmDetails','0');
				
				document.getElementsByName('serviceProviderName')[0].focus();
			break;
				
			case "<%=CSAdministerProgramReferralsConstants.PROGRAM_SEARCH%>":
				document.getElementsByName('serviceProviderName')[0].value="";
				document.getElementsByName('inHouse')[0].selectedIndex="";
				document.getElementsByName('regionCd')[0].selectedIndex="";
				
				show('pgmRequiredFieldHeader','1');
				show('pgmDetailsHeader','1');
				show('pgmDetails','1');
				show('spRequiredFieldHeader','0');
				show('spDetailsHeader','0');
				show('spDetails','0');

				document.getElementsByName('programName')[0].focus();
			break;	
		}
		el = document.getElementsByName("programGroupId")[0];
		loadPgmTypes(el);
	}


	function validate(theForm)
	{
		var customSearchMask="/^([a-zA-Z0-9]{1})([a-zA-Z0-9 \.\\\\';()\x2c\x26\x2f\-]*)([*]?)$/";
		
		var searchByElem = document.getElementsByName('searchBy')[0];
		var searchByValue = searchByElem.options[searchByElem.selectedIndex].value;

		switch (searchByValue)
		{
			case "<%=CSAdministerProgramReferralsConstants.SERVICE_PROVIDER_SEARCH%>":
				clearAllValArrays();
				customValRequired("serviceProviderName","<bean:message key='errors.required' arg0='Service Provider Name'/>","");
				customValMinLength("serviceProviderName","<bean:message key='errors.minlength' arg0='Service Provider Name' arg1='1'/>","1");
				customValMaxLength("serviceProviderName","<bean:message key='errors.maxlength' arg0='Service Provider Name' arg1='100'/>","100"); 
				customValMask("serviceProviderName","<bean:message key='errors.comments' arg0='Service Provider Name'/>",customSearchMask);
				return validateCustomStrutsBasedJS(theForm);
			break;

			case "<%=CSAdministerProgramReferralsConstants.PROGRAM_SEARCH%>":
				var programNameElem=document.getElementsByName('programName')[0];
				var cstsCodeElem=document.getElementsByName('cstsCode')[0];
				var programGroupElem=document.getElementsByName('programGroupId')[0];
				var programTypeElem=document.getElementsByName('programTypeId')[0];
				var programSubTypeElem=document.getElementsByName('programSubTypeId')[0];
				
				if(programNameElem.value!="" || cstsCodeElem.value!="" || programGroupElem.value!="" || programTypeElem.value!="" || programSubTypeElem.value!=""){
					clearAllValArrays();
					customValMinLength("programName","<bean:message key='errors.minlength' arg0='Program Name' arg1='1'/>","1");
					customValMaxLength("programName","<bean:message key='errors.maxlength' arg0='Program Name' arg1='100'/>","100"); 
					customValMask("programName","<bean:message key='errors.comments' arg0='Program Name'/>",customSearchMask);
					return validateCustomStrutsBasedJS(theForm);
				}
				else
				{
					alert("At least one field must be used to search by.");
					return false;
				}
			break;
		}
	}
	
	//constructor
function subgroup(id, name)
{
	this.id = id;
	this.name = name;
	this.childEvents = new Array();
}
var pgmGroups = new Array();
var programTypes = new Array();

<logic:iterate indexId="groupIterIndex" id="groupIter" name="cscProgRefCaseloadForm" property="programHeirarchyList">
	pgmGroups[<bean:write name="groupIterIndex"/>] = new subgroup("<bean:write name="groupIter" property="parentCd" />", "<bean:write name="groupIter" property="parentDesc"/>");

	<logic:iterate indexId="groupIterIndex2" id="groupIter2" name="groupIter" property="childEvents">
		var innerGroup = new subgroup("<bean:write name="groupIter2" property="code" />", "<bean:write name="groupIter2" property="description"/>");
		pgmGroups[<bean:write name="groupIterIndex"/>].childEvents[<bean:write name="groupIterIndex2"/>] = innerGroup;
	</logic:iterate>
</logic:iterate >

function loadPgmTypes(el)
{
	var selId = el.options[el.selectedIndex].value;
	var pgms = document.getElementsByName("programTypeId");

	pgms[0].options.length = 0;
	pgms[0].options[0] = new Option( "Please Select", "", false, false );
	pgms[0].disabled = false;
	
	if(el.selectedIndex == 0)
	{
		pgms[0].selectedIndex = 0; //reset choice back to default
		pgms[0].value="";
		pgms[0].disabled = true; //disable group2 choice		
		return;
	}
 
	for(i in pgmGroups)
	{

	if(pgmGroups[i].id == selId)
		{
			for(j in pgmGroups[i].childEvents)
			{
				pgms[0].options[pgms[0].options.length] = new Option( pgmGroups[i].childEvents[j].name, pgmGroups[i].childEvents[j].id);
			}
			break;	
		}
	}
}
	
function refreshPage(theForm){
	document.getElementsByName('serviceProviderName')[0].value = "";
	document.getElementsByName('inHouse')[0].selectedIndex="";
	document.getElementsByName('regionCd')[0].selectedIndex="";	
	document.getElementsByName('programName')[0].value="";
	document.getElementsByName('cstsCode')[0].selectedIndex="";
	document.getElementsByName('programGroupId')[0].selectedIndex="";
	document.getElementsByName('programTypeId')[0].selectedIndex="";
	document.getElementsByName('programSubTypeId')[0].selectedIndex="";
	if (theForm.searchBy.selectedIndex == 0){
		show('spRequiredFieldHeader','1');
		show('spDetailsHeader','1');
		show('spDetails','1');
		show('pgmRequiredFieldHeader','0');
		show('pgmDetailsHeader','0');
		show('pgmDetails','0');
		document.getElementsByName('serviceProviderName')[0].focus();
	} else {
		show('pgmRequiredFieldHeader','1');
		show('pgmDetailsHeader','1');
		show('pgmDetails','1');
		show('spRequiredFieldHeader','0');
		show('spDetailsHeader','0');
		show('spDetails','0');
		document.getElementsByName('programName')[0].focus();
	}
	enableDropDownsOnPage(theForm);

}	 

</script>
</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="renderSearch(document.cscProgRefCaseloadForm.searchBy,document.forms[0])">
<html:form action="/handleProgramReferralByCaseload" target="content">
		<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/CSCD_Caseload.htm#|6">
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
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
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
								<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="title.filterCaseload"/>-&nbsp;
																  <bean:message key="title.serviceProvider"/>&nbsp;<bean:message key="title.search"/>&nbsp;</td>
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
										<li>Select a search type; enter required fields. Click Submit.</li>
									</ul>
								</td>
							</tr>
						</table>
						<!-- END INSTRUCTION TABLE -->
						
						<!--BEGIN CURRENT CASELOAD TABLE -->
							<table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr class="paddedFourPix">
									<td class="formDeLabel"><bean:message key="prompt.currentCaseload" /></td>
								</tr>
								<tr>
									<td bgcolor="#cccccc" colspan="2">
										<!--header start-->
										<table width="100%" border="0" cellpadding="2" cellspacing="1">
											<tr>
												<td class="headerLabel"><bean:message key="prompt.officer"/></td>
												<td class="headerData"><bean:write name="cscProgRefCaseloadForm" property="officerNamePosition"/></td>
											</tr>
										</table>
										<!--header end-->
									</td>
								</tr>
							</table>
						<!-- END CURRENT CASELOAD TABLE -->	
						
						<div class="spacer4px"></div>
						
						<!-- BEGIN SEARCH BY DROP DOWN TABLE -->
						<table width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.searchBy" /></td>
								<td class="formDe">
									<html:select name="cscProgRefCaseloadForm" property="searchBy" onchange="renderSearch(this, this.form)">
										<html:option value="<%=CSAdministerProgramReferralsConstants.SERVICE_PROVIDER_SEARCH%>"><bean:message key="prompt.serviceProvider"/></html:option>
										<html:option value="<%=CSAdministerProgramReferralsConstants.PROGRAM_SEARCH%>"><bean:message key="prompt.program"/></html:option>
									</html:select>
								</td>
							</tr>
						</table>
						<!--  END SEARCH BY DROP DOWNTABLE -->
						<div class="spacer4px"></div>
						
						<!-- BEGIN SEARCH BY TABLE -->
						<table width="98%" cellpadding="0" cellspacing="0">
							<tr id="spRequiredFieldHeader">
								<td class="required" colspan="2"><img src="../../../../images/required.gif" title="required" alt="required image" border="0" width="10" height="9"><bean:message key="prompt.requiredFieldInstruction" /></td>
							</tr>
							<tr id="pgmRequiredFieldHeader">
								<td class="required" colspan="2"><img src="../../../../images/required.gif" title="required" alt="required image" border="0" width="10" height="9">At least one search criteria required.</td>
							</tr>
						</table>
						
						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr id="spDetailsHeader">
								<td class="detailHead"><bean:message key="prompt.searchBy" />&nbsp;<bean:message key="prompt.serviceProvider"/></td>
							</tr>
							<tr id="spDetails">
								<td>
									<table align="center" width="100%" border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td class="formDeLabel" width="1%" nowrap><img src="../../../../images/required.gif" title="required" alt="required image" border="0" width="10" height="9"><bean:message key="prompt.serviceProviderName" /></td>
											<td class="formDe" colspan="3">
												<html:text name="cscProgRefCaseloadForm" property="serviceProviderName" size="50" maxlength="100"></html:text>
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.inHouse"/></td>
											<td class="formDe">
												<html:select name="cscProgRefCaseloadForm" property="inHouse">
													<html:option value=""><bean:message key="select.generic" /></html:option> 
													<html:option value="N">No</html:option>
													<html:option value="Y">Yes</html:option>
												</html:select>
											</td>
											<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.region"/></td>
											<td class="formDe">
												<html:select name="cscProgRefCaseloadForm" property="regionCd">
													<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  							                	
				                					<html:optionsCollection name="cscProgRefCaseloadForm" property="regionsList"  value="code" label="description" />
												</html:select>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="pgmDetailsHeader">
								<td class="detailHead" ><bean:message key="prompt.searchBy" />&nbsp;<bean:message key="prompt.program"/></td>
							</tr>
							<tr id="pgmDetails">
								<td>
									<table align="center" width="100%" border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.programName"/></td>
											<td class="formDe">
												<html:text name="cscProgRefCaseloadForm" property="programName" size="50"  maxlength="100"></html:text>
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap width="1%" title="State Program Code"><bean:message key="prompt.CSTSCode"/></td>
											<td class="formDe">
												<html:select name="cscProgRefCaseloadForm" property="cstsCode">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection name="cscProgRefCaseloadForm" property="cstsCodesList"  value="code" label="description" />
												</html:select>																	
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" colspan="2"><bean:message key="prompt.referralType"/></td>
										</tr>
				                        <tr>
				                          <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.programGroup"/></td>
				                          <td class="formDe">
				                            <html:select name="cscProgRefCaseloadForm" property="programGroupId" onchange="loadPgmTypes(this);"> 
		          								<html:option value=""><bean:message key="select.generic" /></html:option>
		          								<html:optionsCollection name="cscProgRefCaseloadForm" property="programHeirarchyList" value="parentCd" label="parentDesc" />					
		          							</html:select>
				                          </td>
				                        </tr>
				                        <tr>
				                          <td class="formDeLabel"><bean:message key="prompt.type"/></td>
				                          <td class="formDe">
				                            <html:select name="cscProgRefCaseloadForm" property="programTypeId"  disabled="false">
		          								<html:option value=""><bean:message key="select.generic" /></html:option>
		          								<html:optionsCollection name="cscProgRefCaseloadForm" property="programTypes" value="code" label="description" />					
		          							</html:select>
				                          </td>
				                        </tr>
									</table>
								</td>
							</tr>
						</table>
						<!-- END SEARCH BY TABLE -->
						
						<br>
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
									<html:submit property="submitAction" onclick="return validate(this.form) && disableSubmit(this, this.form);"><bean:message key="button.submit" /></html:submit>
									<input type="button" name="reset" value="<bean:message key='button.refresh'/>" onclick="refreshPage(this.form)" />
									<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
								</td>
							</tr>
						</table>
						<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>
		</td>
	</tr>			
</table>	
</div>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</html:form>
</body>
</html:html>