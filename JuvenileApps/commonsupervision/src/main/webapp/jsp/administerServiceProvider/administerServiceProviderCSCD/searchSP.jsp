<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Used for searching of Service Providers (CSCD)-->
<!--MODIFICATIONS -->
<!-- DWilliamson 11/16/2007	Create JSP -->
<!-- CShimek     12/05/2008 Defect#55792 removed "Please Select" from Search By options  -->
<!-- CShimek     12/30/2008 Defect#56217 removed Contract Program from Service provider search --> 
<!-- LDeen	 08/04/2009 Fixed instruction for program search --> 
<!-- RYoung  01/29/2012 Defect#74823 CSCD: Add Filter to exclude inactive code on create program --> 

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDCodeTableConstants" %>
<%@ page import="naming.Features" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>

<msp:nocache />
<html:html>
<!--BEGIN HEADER TAG-->
<head>

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
<title><bean:message key="title.heading"/> - searchSP.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javaScript' src="/<msp:webapp/>js/AnchorPosition.js"></script>
		<!-- begin Auto Tabbing script -->
<script type='text/javaScript'>

function subgroup(id, name)
{
	this.id = id;
	this.name = name;
	this.childEvents = new Array();
}
var pgmGroups = new Array();
var programTypes = new Array();

<logic:iterate indexId="groupIterIndex" id="groupIter" name="cscServiceProviderSearchForm" property="programHeirarchyList">
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

function renderSearch(theSelect, resetFlag){
	if (resetFlag){
		show("spSearch", 0)
		show("progSearch", 0)
		show("servSearch", 0)
		show("btnRow", 0)
	}else{
		theSelectValue = theSelect.options[theSelect.selectedIndex].value;
		
		switch (theSelectValue)
		{
			case "<%=PDCodeTableConstants.ASP_CS_SERVPROV_SEARCHBY_SP%>":
			show("spSearch", 1);
			show("progSearch", 0);
			show("consSearch", 0);
			show("btnRow", 1);			
			document.getElementById("serviceProviderName").focus();
			
			break

			case "<%=PDCodeTableConstants.ASP_CS_SERVPROV_SEARCHBY_PROGRAM%>":
			show("spSearch", 0)
			show("progSearch", 1)
			show("consSearch", 0)
			show("btnRow", 1)
			document.getElementById("programName").focus();
			el = document.getElementsByName("programGroupId")[0];
			loadPgmTypes(el);
			break

			case "<%=PDCodeTableConstants.ASP_CS_SERVPROV_SEARCHBY_CONSOLIDATED%>":
			show("spSearch", 0)
			show("progSearch", 0)
			show("consSearch", 1)
			show("btnRow", 0)
			break

		}
	}
}

function performValidation(theForm){
	var customSearchMask="/^([a-zA-Z0-9]{1})([a-zA-Z0-9 \.\\\\';()\x2c\x26\x2f\-]*)([*]?)$/";
	
	var theSelect=document.getElementsByName('searchById')[0];
	var theSelectValue = theSelect.options[theSelect.selectedIndex].value;
		switch (theSelectValue)
		{
			case "<%=PDCodeTableConstants.ASP_CS_SERVPROV_SEARCHBY_SP%>":					
				var serviceProviderNameElem=document.getElementsByName('serviceProviderName')[0];
				var serviceProviderStatusElem=document.getElementsByName('serviceProviderStatusId')[0];
				var serviceProviderInHouseElem=document.getElementsByName('serviceProviderInHouseId')[0];
				if (serviceProviderNameElem.value !="" || serviceProviderStatusElem.value !="" || serviceProviderInHouseElem.value !="") { 
					clearAllValArrays();												
					customValMinLength("serviceProviderName","<bean:message key='errors.minlength' arg0='Service Provider Name' arg1='1'/>","1");
					customValMaxLength("serviceProviderName","<bean:message key='errors.maxlength' arg0='Service Provider Name' arg1='100'/>","100"); 
					customValMask("serviceProviderName","<bean:message key='errors.comments' arg0='Service Provider Name'/>",customSearchMask);
					return validateCustomStrutsBasedJS(theForm);
				}
				else{
					alert("At least one field must be used to search by.");
					return false;
				}
			break

			case "<%=PDCodeTableConstants.ASP_CS_SERVPROV_SEARCHBY_PROGRAM%>":
				var programNameElem=document.getElementsByName('programName')[0];
				var programGroupElem=document.getElementsByName('programGroupId')[0];
				var programTypeElem=document.getElementsByName('programTypeId')[0];
				var contractProgramElem=document.getElementsByName('contractProgramId')[0];
				var statusElem=document.getElementsByName('programStatusId')[0];
				if(programNameElem.value!="" || programGroupElem.value!="" || programTypeElem.value!="" || contractProgramElem.value!="" || statusElem.value!=""){
					clearAllValArrays();
					customValMinLength("programName","<bean:message key='errors.minlength' arg0='Program Name' arg1='1'/>","1");
					customValMaxLength("programName","<bean:message key='errors.maxlength' arg0='Program Name' arg1='100'/>","100"); 
					customValMask("programName","<bean:message key='errors.comments' arg0='Program Name'/>",customSearchMask);
					return validateCustomStrutsBasedJS(theForm);
				}
				else{
					alert("At least one field must be used to search by.");
					return false;
				}
				
			break

			case "<%=PDCodeTableConstants.ASP_CS_SERVPROV_SEARCHBY_CONSOLIDATED%>":
				
			break

		}
}
	
	
</script>
</head>     <%--  enableDropDownsOnPage(document.forms[0] --%>
<body topmargin="0" leftmargin="0"  onKeyDown="checkEnterKeyAndSubmit(event,true)" onLoad="renderSearch(document.forms[0].searchById,false);">
<html:form action="/displayCSCServiceProviderSearch" target="content" focus="searchById">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|17">
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
								<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="title.serviceProvider"/>&nbsp;<bean:message key="prompt.search"/></td>
							</tr>
						</table>
						<!-- END HEADING TABLE -->
						<%-- BEGIN ERROR TABLE --%>
						<table width="98%" align="center">
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>
						</table>								
						<!-- END HEADING TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
							<tr>
								<td>
									<ul>
										<li>Select Search By to change the search type.</li>
										<li>Enter the required field(s) and click Submit to see results.</li>
									</ul>
								</td>
							</tr>
						</table>
						<!-- BEGIN  TABLE -->
						<table width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
							<tr>
								<td class="formDeLabel" width="1%" nowrap>
									<bean:message key="prompt.searchBy" />
								</td>
								<td class="formDe">
                                   
									<html:select property="searchById" onchange="renderSearch(this,false)">
										<html:option value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_SEARCHBY_PROGRAM%>">Program</html:option>
										<html:option value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_SEARCHBY_SP%>">Service Provider</html:option>
										<%-- <html:option value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_SEARCHBY_CONSOLIDATED%>" >Consolidated View</html:option> --%>
									</html:select>
								</td>
							</tr>
						</table>
						<br>
						<!-- SEARCH BY sp -->
						<span id="spSearch" class="hidden">
							<table width="98%" cellpadding="0" cellspacing="0">
								<tr>
									<td class="required" colspan="2"> <bean:message key="prompt.3.diamond"/> <bean:message key="prompt.atLeastOneRequiredForThisSection"/></td>
								</tr>								
							</table>
							<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr class="detailHead">
									<td><bean:message key="prompt.searchBy" />&nbsp;<bean:message key="title.serviceProvider" /></td>
								</tr>
								<tr>
									<td>
										<table align="center" width="100%" border="0" cellpadding="2" cellspacing="1">
											<tr>
												<td class="formDeLabel" width="1%" nowrap>Service Provider Name</td>
												<td class="formDe" colspan="2">
													<html:text name="cscServiceProviderSearchForm" styleId="serviceProviderName" property="serviceProviderName" size="60" maxlength="100"/>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.status" /></td>
												<td class="formDe" colspan="2">
													<html:select property="serviceProviderStatusId">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<jims2:codetable codeTableName="<%=PDCodeTableConstants.ASP_CS_SERVICE_PROVIDER_STATUS%>" sort="true"></jims2:codetable>
													
													</html:select>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.inHouse"/></td>
												<td class="formDe">
													<html:select name="cscServiceProviderSearchForm" property="serviceProviderInHouseId">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:option value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_INHOUSE_NO%>">NO</html:option>
														<html:option value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_INHOUSE_YES%>">YES</html:option>
													</html:select>
												</td>
											</tr>
								<%-- 			<tr>
												<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.contract"/>&nbsp;<bean:message key="prompt.program"/></td>
												<td class="formDe">
													<html:select name="cscServiceProviderSearchForm" property="serviceProviderContractProgId">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:option value="<%=PDCodeTableConstants.ASP_CS_CONTRACTPROGRAM_NO%>">NO</html:option>
														<html:option value="<%=PDCodeTableConstants.ASP_CS_CONTRACTPROGRAM_YES%>">YES</html:option>
													</html:select>
												</td>
											</tr> --%>
										</table>
									</td>
								</tr>

								<!-- SEARCH BY sp end-->
							</table>
						</span>

						<span id="progSearch" class="hidden">
							<!-- SEARCH BY prog begin-->
							<table width="98%" cellpadding="0" cellspacing="0">
								<tr>
									<td class="required" colspan="2"> <bean:message key="prompt.3.diamond"/> <bean:message key="prompt.atLeastOneRequiredForThisSection"/></td>
								</tr>
								
							</table>
							<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr class="detailHead">
									<td colspan="2"><bean:message key="prompt.searchBy" />&nbsp;<bean:message key="prompt.program" />&nbsp;</td>
								</tr>
								<tr>
									<td>
										<table align="center" width="100%" border="0" cellpadding="2" cellspacing="1">
											<tr>
												<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.programName" /></td>
												<td class="formDe">
													<html:text name="cscServiceProviderSearchForm" styleId="programName" property="programName" size="50" maxlength="50"/>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap><bean:message key="prompt.programGroup" /></td>
												<td colspan="3" class="formDe">
                                            		<html:select name="cscServiceProviderSearchForm" property="programGroupId" onchange="loadPgmTypes(this);"> 
		          										<html:option value=""><bean:message key="select.generic" /></html:option>
		          										<html:optionsCollection name="cscServiceProviderSearchForm" property="programHeirarchyList" value="parentCd" label="parentDesc" />					
		          									</html:select>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.programType" /></td>
												<td colspan="3" class="formDe">
                                                	<html:select name="cscServiceProviderSearchForm" property="programTypeId"  disabled="false">
		          										<html:option value=""><bean:message key="select.generic" /></html:option>
		          										<html:optionsCollection name="cscServiceProviderSearchForm" property="programTypes" value="code" label="description" />					
		          									</html:select>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.contractProgram" /></td>
												<td class="formDe">
                                                    <html:select name="cscServiceProviderSearchForm" property="contractProgramId">
														<html:option value=""><bean:message key="select.generic" /></html:option>
													    <html:option value="<%=PDCodeTableConstants.ASP_CS_CONTRACTPROGRAM_NO%>">NO</html:option>
														<html:option value="<%=PDCodeTableConstants.ASP_CS_CONTRACTPROGRAM_YES%>">YES</html:option>
													</html:select></td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.status" /></td>
												<td class="formDe">
                                                    <html:select name="cscServiceProviderSearchForm" property="programStatusId">
														<html:option value=""><bean:message key="select.generic" /></html:option>
													<jims2:codetable codeTableName="<%=PDCodeTableConstants.ASP_CS_PROGRAM_STATUS%>" sort="true"></jims2:codetable>
													</html:select>
												
												</td>
											</tr>
											<!-- SEARCH BY prog end-->
										</table>
									</td>
								</tr>
							</table>

						</span>
				<!-- *** Consolidated View search to be baselined at later date. *** daw -->		
						<span id="consSearch"  class="hidden">
							<table width="98%" cellpadding="0" cellspacing="0">
								<tr>
								    <td><b>Consolidated Search to be implemented at a later date.</b></td>
									<%--td class=required colspan=2><bean:message key="prompt.requiredFields" />&nbsp;<bean:message key="prompt.dateFieldsInstruction" /><br>+Program Type is required if searching by Program Group</td--%>
								</tr>

							</table>
							<%--table width="98%" cellpadding="2" cellspacing="0" class=borderTableBlue>
								<tr class="detailHead">
									<td>Consolidated View Parameters</td>
								</tr>
								<tr>
									<td>
										<table align="center" width=100% border="0" cellpadding="2" cellspacing="1">
											<tr>
												<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.serviceProviderName" /></td>
												<td class="formDe">
													<html:text name="cscServiceProviderSearchForm" property="serviceProviderName" size="60" maxlength="100"/>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.programName" /></td>
												<td class="formDe">
													<html:text name="cscServiceProviderSearchForm" property="programName" size="50" maxlength="100"/>
												</td>
											</tr>
											<tr>
												<td class=formDeLabel nowrap><bean:message key="prompt.requiredFieldsImage" /><bean:message key="prompt.dateRange" /></td>
												<td class=formDe>
													<html:text name="cscServiceProviderSearchForm" property="programStartDate" size="10" maxlength="10"/>
													   <A HREF="#" onClick="cal1.select(document.forms[0].stDate,'anchor21','MM/dd/yyyy'); return false;" NAME="anchor21" ID="anchor21" border=0><img border=0 src="../../common/images/calendar2.gif" title="(mm/dd/yyyy)"/></A> - 
                                                    <html:text name="cscServiceProviderSearchForm" property="programEndDate" size="10" maxlength="10"/>
												       <A HREF="#" onClick="cal1.select(document.forms[0].enDate,'anchor22','MM/dd/yyyy'); return false;" NAME="anchor22" ID="anchor22" border=0><img border=0 src="../../common/images/calendar2.gif" title="(mm/dd/yyyy)"/></A> </td>
											</tr>
											<tr>
												<td class=formDeLabel>+<bean:message key="prompt.programGroup" /></td>
												<td class=formDe>
													<!--i use enableSelectByID b.c the same search fields are for search by program -->
                                                    <html:select size="1" property="programGroupId" onchange="enableSelectById('typeForConsolidatedSearch', this)">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:optionsCollection property="programGroups" value="code" label="description" /> 
													</html:select>
													<!--select size="1" name="group11" onchange="enableSelectByID('typeForConsolidatedSearch', this)">
														<option value="">Please Select</option>
														<option value="c">Counseling</option>
														<option value="a">Assessment</option>
														<option value="e">Education Program</option>
														<option value="em">Electronic Monitoring</option>
														<option value="r">Residential Program</option>
													</select-->
												</td>
											</tr>
											<tr>
												<td class=formDeLabel><bean:message key="prompt.programType" /></td>
												<td class=formDe>
                                                    <html:select size="1" property="programTypeId" disabled id=="typeForConsolidatedSearch">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:optionsCollection property="programTypes" value="code" label="description" /> 
													</html:select>
													<!--select size="1" name="group22" disabled id="typeForConsolidatedSearch">
														<option>Please Select</option>
														<option>Administrative License Revocation</option>
														<option>Adult Basic Education (ABE)</option>
														<option>Defensive Driving Class </option>
														<option>Drug Dealers Education</option>
														<option>Drug Offender Education </option>
														<option>DWI Education</option>
														<option>DWI Repeat Offender</option>
													</select-->
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<!-- SEARCH BY consolidated view end-->
							</table--%>
						</span>
						<div class="spacer"></div>
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" cellpadding="2" cellspacing="1" width="100%">
							<tr id="btnRow" class="hidden">
								<td align="center">
									
										<html:submit property="submitAction" onclick="return performValidation(this.form)">
											<bean:message key="button.submit"></bean:message>
										</html:submit>
  							        <html:submit property="submitAction"><bean:message key="button.refresh"></bean:message></html:submit>	
  								    <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
								</td>
							</tr>
							<jims2:isAllowed requiredFeatures="<%=Features.CS_ASP_CREATE_CSC%>">
							<tr>
								<td align="center">
									
                                    <html:submit property="submitAction"><bean:message key="button.createServiceProvider"></bean:message></html:submit>
									
								</td>
							</tr>
							</jims2:isAllowed>
						</table>
						<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>
			<!-- BEGIN BUTTON TABLE -->
		</td>
	</tr>
</table>
<!-- END  TABLE -->

</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>