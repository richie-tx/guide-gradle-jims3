<!DOCTYPE HTML>

<%-- Used for searching of juvenile JIMS reports in MJCW --%>
<%--MODIFICATIONS --%>
<%-- 10/25/2013	CShimek  	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - populationReportsSearch.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript">

$(function(){
	
	$("#searchById").on("change", function(){		
		$('#facility').hide();
		$('#secureFacility').hide();
		$('#admitId').hide();
		if ($(this).prop("selectedIndex") != 0) {			
			$('#facility').show();
			if ($(this).val() == "AR") {
				$('#secureFacility').show();
				$('#admitId').show();
			}
		}
	});
	
	$("#refresh").on("click", function(){
		$('#facility').hide();
		$('#secureFacility').hide();
		$('#admitId').hide();
		$("#searchById").prop("selectedIndex", 0);
		$("#facId").prop("selectedIndex", 0);
		$("#admitReasonId").prop("selectedIndex", 0);
		$("#securedFacility0").prop("checked", false);
		$("#securedFacility1").prop("checked", false);
	});
});
function onloadForm()
{
//	document.forms[0].searchType.selectedIndex = 2;
// 	evalSearch(document.forms[0]) ;
}



function validateInputs()
{
	fld = document.getElementById("searchById");
	if (fld.selectedIndex == 0)
	{
		alert("Search For selection required.");
		fld.focus();
		return false;
	}
	fld = document.getElementById("facId");
	if (fld.selectedIndex == 0)
	{
		alert("Facility selection required.\n");
		fld.focus();
		return false;
	}
	spinner();
	return true;
}

function resetPage()
{
	showHide('facility', 0);
	showHide('secureFacility', 0);
	showHide('admitId', 0);
	document.getElementById("searchById").selectedIndex = 0;
	document.getElementById("facId").selectedIndex = 0;
	document.getElementById("admitReasonId").selectedIndex = 0;
	var secure = document.getElementsByName('securedFacility');
	secure[0].checked=false;
	secure[1].checked=false;
}
</script>

</head>


<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  onload="onloadForm();">
<%--BEGIN FORM TAG--%>
<html:form action="/handlePopulationReportSearch" target="content" focus="searchTypeId" >
<input type="hidden" name="helpFile" value="">       

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="prompt.facility"/> Population Reports</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select the type of facility report and enter required fields.</li>
			</ul>	
		</td>
	</tr>
	<tr>
		<td class="required"><bean:message key="prompt.2.diamond"/>&nbsp;Required Fields</td>
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

<%-- BEGIN SEARCH FOR TABLE --%>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1"  class='borderTableBlue'>
	<tr>
		<td class="formDeLabel" width='1%' nowrap='nowrap'><bean:message key="prompt.searchFor"/></td>
		<td class="formDe">
			<html:select name="juvenilePopulationForm" property="searchTypeId" styleId="searchById" >
				<html:option key="select.generic" value=""/>
				<html:optionsCollection name="juvenilePopulationForm" property="searchTypes" value="code" label="description" /> 
			</html:select> 
		</td>
	</tr>
</table>
<%-- END SEARCH FOR TABLE  --%>
<br>
<span id="facility" class='hidden'>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
		<tr>
			<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/> <bean:message key="prompt.facility" /></td>
			<td class="formDe">
				<html:select name="juvenilePopulationForm" property="facilityId" styleId="facId">
					<html:option key="select.generic" value=""/>
					<html:optionsCollection name="juvenilePopulationForm" property="facilities" value="code" label="descriptionWithCode" />
				</html:select>
			</td>
		</tr>
		<tr id="secureFacility" class="hidden">
			<td class="formDeLabel" nowrap='nowrap'>+ <bean:message key="prompt.secure" /> <bean:message key="prompt.facility" />?</td>
			<td class="formDe">
				<bean:message key="prompt.yes" /><html:radio name="juvenilePopulationForm" property="securedFacility" value="S" styleId="securedFacility0"/>
				<bean:message key="prompt.no" /><html:radio name="juvenilePopulationForm" property="securedFacility" value="N" styleId="securedFacility1"/>
			</td>
		</tr>	
		<tr id="admitId" class="hidden">
			<td class="formDeLabel" nowrap='nowrap'>+ <bean:message key="prompt.admit" /> <bean:message key="prompt.reason" /></td>
			<td class="formDe">
				<html:select name="juvenilePopulationForm" property="admitReasonIds" size="12" styleId="admitReasonId" multiple = "true">
					<html:option key="select.generic" value=""/>
					<html:optionsCollection name="juvenilePopulationForm" property="admitReasons" value="code" label="description" />
				</html:select>
			</td>
		</tr>
	</table>
</span>

<div class="spacer4px" />
<%-- BEGIN BUTTON TABLE --%>
<table align="center" border="0" width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction"  onclick="return validateInputs(this.form)"><bean:message key="button.submit" /></html:submit>
			<input type="button" id="refresh" value="<bean:message key='button.refresh'/>"/>
			<html:button property="org.apache.struts.taglib.html.CANCEL" onclick="document.location.href='/security.war/jsp/mainMenu.jsp'">
				<bean:message key="button.cancel"></bean:message>
			</html:button>
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>
</html:form>
<br>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>