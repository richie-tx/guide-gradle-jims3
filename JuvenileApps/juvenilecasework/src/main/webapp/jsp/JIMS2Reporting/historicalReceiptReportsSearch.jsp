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
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />


<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - historicalReceiptReportsSearch.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript">

function onloadForm()
{

}

function validateInputs()
{
	var errorMessage = "";
	var isSuccessful = true;
	fld = document.getElementById("facId");
	if (fld.selectedIndex == 0)
	{
		errorMessage = "Facility selection is required.\n";
		fld.focus();
		isSuccessful = false;
	}	
	fld = document.getElementById("juvenileNumber");
	if (fld.value == "")
	{
		errorMessage += "Juvenile Number is required.\n";
		fld.focus();
		isSuccessful = false;
	}
	if (!isNumeric(fld.value,false))
	{
		errorMessage += "Juvenile Number must be numeric.\n";
		fld.focus();
		isSuccessful = false;
	}
	var firstCharacter = fld.value.substring(0,1);
	if (firstCharacter.indexOf("0")!= -1)
	{
		errorMessage += "Juvenile Number cannot start with zero.\n";
		fld.focus();
		isSuccessful = false;
	}
	if(!isSuccessful){
		alert(errorMessage); 
	}	
	return isSuccessful;
}

function resetPage()
{
	document.getElementById("facId").selectedIndex = 0;
	document.getElementById("juvenileNumber").value = "";
}
</script>

</head>


<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  onload="onloadForm();">
<%--BEGIN FORM TAG--%>
<html:form action="/displayHistoricalReceiptReport" target="content" focus="searchTypeId" >
<input type="hidden" name="helpFile" value="">       

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Historical Receipt List Report</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select the type of facility and enter Juvenile Number.</li>
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
<%-- BEGIN FACILITY DROP DOWN AND JUVENILE NUMBER TABLE --%>
<br>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class='borderTableBlue'>
	<tr id = "facilitiesRow">
		<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/> <bean:message key="prompt.facility" /></td>
		<td class="formDe">
		<html:select name="juvenileFacilityReceiptForm" property="facilityId" styleId="facId">
				<html:option key="select.generic" value=""/>
				<html:optionsCollection name="juvenileFacilityReceiptForm" property="facilities" value="code" label="descriptionWithCode" />
			</html:select>
		</td>
	</tr>
	<tr id="juvenileRow">
		<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.2.diamond"/> <bean:message key="prompt.juvenileNumber" /></td>
		<td class="formDe">
			<html:text property="juvenileNumber" size="8" maxlength="8" styleId="juvenileNumber"/>
		</td>
	</tr>
</table>
<div class="spacer4px" />
<%-- END FACILITY DROP DOWN AND JUVENILE NUMBER TABLE --%>
<%-- BEGIN BUTTON TABLE --%>
<table align="center" border="0" width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction"  onclick="return validateInputs(this.form)"><bean:message key="button.submit" /></html:submit>
			<input type="button" onclick="return resetPage()" value="<bean:message key='button.refresh'/>"/>
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