<!DOCTYPE HTML>
<!-- Used for searching of Service Providers -->
<!--MODIFICATIONS -->
<!-- UGopinath	09/14/2006	Create JSP -->
<!-- Ldeen		08/13/2007	Defect #43451 -->
<!-- CShimek	10/19/2007  defect#46036 add cursor set -->
<!-- RCapestani 10/13/2015  Task #30717 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Service Provider-Juvenile link) -->
 
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<html:html>
<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- STRUTS VALIDATION --%>
<html:javascript formName="providerSearchForm"/>
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/commonsupervision.css" />
<html:base />

<title><bean:message key="title.heading"/> - searchSP.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/administerServiceProviderHCJPD/searchSP.js"></script>

<script type="text/javascript">

$(function() {
	
	if(typeof $("#frmEndDate")!= "undefined"){			
		datePickerSingle( $("#frmEndDate"),"From Date", false);		 
	}
	if(typeof $("#toEndDate")!= "undefined"){			
		datePickerSingle( $("#toEndDate"),"To Date", false);		 
	}

});

</script>

<script type="text/javascript">
function renderSearch(theSelect, resetFlag)
{
  if (resetFlag)
  {
  	show("spSearch", 1)
  	show("progSearch", 0)
  	show("servSearch", 0)
  	show("fsSearch", 1)
  	//show("pcSearch", 0)
  	document.getElementById("type").value= "SP";
  	document.forms[0]['providerName'].focus();
  }
  else
  {
  	theSelectValue = theSelect;
  	switch (theSelectValue)
  	{
  		case "SP":
  		show("spSearch", 1)
  		show("progSearch", 0)
  		show("servSearch", 0)
  		show("pcSearch", 0)
  		show("fsSearch", 0)
  		document.getElementById("type").value= "SP";					
  		document.forms[0]['providerName'].focus();					
  		break;
  
  		case "PR":
  		show("spSearch", 0)
  		show("progSearch", 1)
  		show("servSearch", 0)
  		show("pcSearch", 0)
  		show("fsSearch", 0)
  		document.getElementById("type").value= "PR";
  		document.forms[0]['currentProgram.programName'].focus();		
  		break;
  
  		case "SV":
  		show("spSearch", 0)
  		show("progSearch", 0)
  		show("servSearch", 1)
  		show("pcSearch", 0)
  		show("fsSearch", 0)
  		document.getElementById("type").value= "SV";
  		document.forms[0]['currentProgram.programService.serviceName'].focus();	
  		break;
  		
  		 case "PC":
  	  		show("spSearch", 0)
  	  		show("progSearch", 0)
  	  		show("servSearch", 0)
  	  		show("pcSearch", 1)
  	  		show("fsSearch", 0)
  	  		document.getElementById("type").value= "PC";
  	  		document.forms[0]['currentProgram.programCode'].focus();	
  	  		break; 
  	  	
  		case "FS":
  	  		show("spSearch", 0)
  	  		show("progSearch", 0)
  	  		show("servSearch", 0)
  	  		show("pcSearch", 0)
  	  		show("fsSearch", 1)
  	  		document.getElementById("type").value= "FS";
  	  		document.forms[0]['fundSource'].focus();		
  	  		break;
   	}
  }
}
</script>
</head>

<!--BEGIN BODY TAG-->
<body topmargin="0" leftmargin="0" onload="renderSearch(document.getElementById('type').value)">

<!--BEGIN FORM TAG-->
<html:form action="/displayJuvServiceProviderSearchResults" target="content" focus="currentProgram.programName">

<input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|360">
<html:hidden name="serviceProviderForm" property="searchTypeId" styleId="type"/>

<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
	</tr>
	<tr>
		<td valign="top">
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--tabs start-->
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tabid" value="suggestedOrderTab"/>
						</tiles:insert>
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
				</tr>
			</table>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><bean:message key="prompt.3.spacer"/></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<!-- BEGIN HEADING TABLE -->
						<table align="center" >
							<tr>
								 <td class="header" align="center"><bean:message key="title.serviceProvider"/> - <bean:message key="prompt.search"/></td>
							</tr>
						</table>
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
								<td class="formDeLabel" nowrap='nowrap' width="1%"><bean:message key="prompt.searchBy" /></td>
								<td class="formDe">
									<html:select property="searchTypeId" onchange="renderSearch(this.value);">
										<html:optionsCollection property="spSearchTypes" value="code" label="description" /> 
									</html:select> 			
								</td>		
							</tr>
						</table>
						
						<!-- SEARCH BY sp -->
						<span id="spSearch" class="hidden">
							<table width="98%" cellpadding="0" cellspacing="0">
								<tr>
									<td class="required" colspan="2"> <bean:message key="prompt.3.diamond"/> <bean:message key="prompt.atLeastOneRequiredForThisSection"/> <bean:message key="prompt.dateFieldsInstruction"/></td>
								</tr>
							</table>

							<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr class="detailHead">
									<td><bean:message key="prompt.searchBy"/> <bean:message key="title.serviceProvider" /></td>
								</tr>
								<tr>
									<td>
										<table align="center" width='100%' border="0" cellpadding="2" cellspacing="1">
											<tr>
												<td class="formDeLabel" width='1%' nowrap='nowrap'><bean:message key="prompt.3.diamond"/><bean:message key="title.serviceProvider" /> <bean:message key="prompt.name" /></td>
												<td class="formDe" colspan="2">
													<html:text name="serviceProviderForm" property="providerName" size="60" maxlength="100"/>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap='nowrap' width='1%'><bean:message key="prompt.status"/></td>
												<td class="formDe" colspan="2">														
													<html:select property="statusId">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:optionsCollection property="statusTypes" value="code" label="description" /> 
													</html:select> 	
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap='nowrap' width='1%'><bean:message key="prompt.inHouse"/></td>
												<td class="formDe">
													<html:select property="isInHouse">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:optionsCollection property="spTypes" value="code" label="description" /> 
													</html:select> 	
												</td>
											</tr>											
										</table>
									</td>
								</tr>
								
								<!-- SEARCH BY sp end-->
							</table>
						</span>
						
						<!-- SEARCH BY prog begin-->
						<span id="progSearch">
							<table width="98%" cellpadding="0" cellspacing="0">
								<tr>
									<td class="required" colspan="2"> <bean:message key="prompt.3.diamond"/> <bean:message key="prompt.atLeastOneRequiredForThisSection"/> <bean:message key="prompt.dateFieldsInstruction"/></td>
								</tr>
								<tr>
									<td class="required">+ indicates at least one other field is required for this search.</td>
								</tr>
							</table>

							<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr class="detailHead">
									<td colspan="2"><bean:message key="prompt.searchBy"/> <bean:message key="prompt.program"/> </td>
								</tr>
								<tr>
									<td>
										<table align="center" width='100%' border="0" cellpadding="2" cellspacing="1">
											<tr>
												<td class="formDeLabel" nowrap='nowrap' width='1%'><bean:message key="prompt.program"/> <bean:message key="prompt.name"/></td>
												<td class="formDe">
													<html:text name="serviceProviderForm" property="currentProgram.programName" styleId="prgmName" size="60" maxlength="100"/>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" width='1%' nowrap='nowrap'><bean:message key="prompt.end"/> <bean:message key="prompt.dateRange"/></td>
												<td class="formDe" colspan="3">												  
													<html:text name="serviceProviderForm" property="frmEndDate" styleId="frmEndDate" size="10" maxlength="10"/>															
													 -
													<html:text name="serviceProviderForm" property="toEndDate" styleId="toEndDate" size="10" maxlength="10"/>	
													
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap='nowrap' width='1%'><bean:message key="prompt.state"/> <bean:message key="prompt.program"/> <bean:message key="prompt.code"/></td>
												<td class="formDe" colspan="3" >
                           <html:select property="currentProgram.stateProgramCodeId" name="serviceProviderForm">
													   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
													   <html:optionsCollection property="stateProgramCodeList" value="code" label="description"  name="serviceProviderForm"/>
													 </html:select>
                        </td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.targetIntervention"/></td>
												<td colspan="3" class="formDe">
                          <html:select property="currentProgram.targetInterventionId" name="serviceProviderForm">
													   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
													   <html:optionsCollection property="targetInterventionList" value="code" label="description"  name="serviceProviderForm"/>
													</html:select>  
                        </td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap='nowrap' width='1%'>+<bean:message key="prompt.status"/></td>
												<td class="formDe">
													<html:select property="currentProgram.statusId">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:optionsCollection property="statusTypes" value="code" label="description" /> 
													</html:select> 	
												</td>
											</tr>
											<!-- SEARCH BY prog end-->
										</table>
									</td>
								</tr>
							</table>
						</span>
						
						
							
						<%-- Add program code as search criteria ER JIMS200075756 --- Start --%>					
						<span id="pcSearch">
							<table width="98%" cellpadding="0" cellspacing="0">
								<tr>
									<td class="required" colspan="2"> <bean:message key="prompt.3.diamond"/> <bean:message key="prompt.atLeastOneRequiredForThisSection"/> </td>
								</tr>
								
							</table>

							<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr class="detailHead">
									<td colspan="2"><bean:message key="prompt.searchBy"/> <bean:message key="prompt.program"/> <bean:message key="prompt.code"/> </td>
								</tr>
								<tr>
									<td>
										<table align="center" width='100%' border="0" cellpadding="2" cellspacing="1">
											<tr>
												<td class="formDeLabel" nowrap='nowrap' width='1%'><bean:message key="prompt.3.diamond"/><bean:message key="prompt.program"/> <bean:message key="prompt.code"/></td>
												<td class="formDe">
													<html:text name="serviceProviderForm" property="currentProgram.programCode" size="10" maxlength="10"/>
												</td>
											</tr>
											
											<!-- SEARCH BY prog code end-->
										</table>
									</td>
								</tr>
							</table>
						</span>
						<%-- Add program code as search criteria ER JIMS200075756 --- End --%>
						

						<!-- SEARCH BY service begin-->
					 <span id="servSearch" class="hidden">
					   <table width="98%" cellpadding="0" cellspacing="0">
								<tr>
									<td class="required" colspan="2"> <bean:message key="prompt.3.diamond"/> <bean:message key="prompt.atLeastOneRequiredForThisSection"/> <bean:message key="prompt.dateFieldsInstruction"/></td>
								</tr>
								<tr>
									<td class="required">+ indicates at least one other field is required for this search.</td>
								</tr>
							</table>

							<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr class="detailHead">
									<td colspan="2"><bean:message key="prompt.searchBy"/> <bean:message key="prompt.service" /></td>
								</tr>
								<tr>
									<td>
										<table align="center" width='100%' border="0" cellpadding="2" cellspacing="1">
											<tr>
												<td class="formDeLabel" nowrap='nowrap' width='1%'><bean:message key="prompt.service" /> <bean:message key="prompt.name"/></td>
												<td class="formDe">
													<html:text name="serviceProviderForm" property="currentProgram.programService.serviceName" styleId="srvcName" size="30" maxlength="50"/>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap='nowrap' width='1%'><bean:message key="prompt.service" /> <bean:message key="prompt.type"/></td>
												<td class="formDe" colspan="3">
								          <html:select property="currentProgram.programService.typeId" name="serviceProviderForm">
													   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
													   <html:optionsCollection property="serviceTypeList" value="serviceTypeCode" label="description"  name="serviceProviderForm"/>
													</html:select>						
								        </td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap='nowrap' width='1%'>+<bean:message key="prompt.status"/></td>
												<td class="formDe">
													<html:select property="currentProgram.programService.statusId">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:optionsCollection property="statusTypes" value="code" label="description" /> 
													</html:select> 	
												</td>
											</tr>
											<!-- SEARCH BY service end-->
										</table>
									</td>
								</tr>
							</table>
						</span>
						
						<!-- SEARCH BY Fund Source -->
						<span id="fsSearch" class="hidden">
							<table width="98%" cellpadding="0" cellspacing="0">
								<tr>
									<td class="required" colspan="2"> <bean:message key="prompt.3.diamond"/> <bean:message key="prompt.atLeastOneRequiredForThisSection"/></td>
								</tr>
							</table>

							<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr class="detailHead">
									<td><bean:message key="prompt.searchBy"/> Fund Source</td>
								</tr>
								<tr>
									<td>
										<table align="center" width='100%' border="0" cellpadding="2" cellspacing="1">											
											<tr>
												<td class="formDeLabel" nowrap='nowrap' width='1%'><bean:message key="prompt.3.diamond"/>Fund Source Name</td>
												<td class="formDe">
													<html:select property="fundSource">
														<html:option value=""><bean:message key="select.generic" /></html:option>
															<jims2:isAllowed requiredFeatures='<%=Features.JCW_SP_SEARCH_FUNDSOURCE%>'>
																<html:optionsCollection property="fundSourceList" value="code" label="description" /> 
															</jims2:isAllowed>
													</html:select> 	
												</td>
											</tr>
										</table>
									</td>
								</tr>
								
								<!-- SEARCH BY sp end-->
							</table>
						</span>
						
						
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<tr>
								<td align="center">
  								<logic:notEqual name="error" value="true">
  									<html:submit property="submitAction" onclick="return validateFields();">
  										<bean:message key="button.submit"></bean:message>
  									</html:submit>
  								</logic:notEqual>
  								<logic:equal name="error" value="true">
  									<html:submit property="submitAction" disabled="true" onclick="return validateFields();">
  										<bean:message key="button.submit"></bean:message>
  									</html:submit>
  								</logic:equal>
  
  							  <html:submit property="submitAction"><bean:message key="button.refresh"></bean:message></html:submit>	
  								<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
								</td>
							</tr>
						</table>
						<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>

			<!-- BEGIN BUTTON TABLE -->
			<br>
			<table border="0" width="100%">
				<tr>
					<td align="center">
  					<jims2:isAllowed requiredFeatures="CS-ASP-CREATEJUV">
  					 	<html:submit property="submitAction"><bean:message key="button.createHCJPD"></bean:message></html:submit>	
  					</jims2:isAllowed>										
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<!-- END  TABLE -->			
</div>

</html:form>

<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>

</html:html>

