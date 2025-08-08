<!DOCTYPE HTML>

<%-- Used for searching of Risk Analysis Formulas in MJCW --%>
<%--MODIFICATIONS --%>
<%-- CShimek 02/23/2012	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %> 
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../../WEB-INF/msp.tld" prefix="msp"%>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - riskFormulaSearch.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript">
function refreshPage(theForm)
{
	document.getElementsByName("asmntId")[0].selectedIndex="";
}
function validateSelect()
{
	if (document.getElementById("asmntId").selectedIndex == 0 )
	{
		alert("Assessment Type selection is required.");
		document.getElementById("asmntId").focus();
		return false;
	} 
	return true;
}
function checkAvailableAssessments()
{
	var fld = document.getElementById("availAsmntTypesId");
	if (fld != null) {
		return true;
	}
	alert("There is no Assessment Type available to create formula.");
	return false
}
</script>

</head>
<body topmargin="0" leftmargin="0">
<html:form action="/handleRiskFormulaSearch" target="content" focus="assessmentId">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" >Risk Formulas - <bean:message key="title.search"/></td>
		</tr>
	</table>
<%-- END HEADING TABLE --%>
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
			<td>
				<ul>
					<li>Select an Assessment Type and then click Submit to view listing.</li>
					<li>Click Create Formula to begin create process.</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td class="required"><bean:message key="prompt.3.diamond" /> <bean:message key="prompt.requiredFieldsInstruction"/></td>
		</tr>    
	</table>
<%-- END INSTRUCTION TABLE --%>
	<div class="spacer4px">
<%-- BEGIN FORMULA AND CATEGORY SELECT TABLE --%>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr>
			<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond" />&nbsp;<bean:message key="prompt.assessmentType"/></td>
			<td class="formDe">
				<html:select name="riskFormulaSearchForm" property="assessmentId" styleId="asmntId">
					<html:option value=""><bean:message key="select.generic" /></html:option>
		 			<html:optionsCollection property="assessmentTypesList" value="code" label="description" />
		 		</html:select>
			</td>
		</tr>
	</table> 
<%-- END FORMULA AND CATEGORY SELECT TABLE --%>	
	<div class="spacer4PX">
<%-- BEGIN BUTTON TABLE --%>
	<table border="0" width="100%">
		<tr>
			<td align="center"> 
				<html:submit property="submitAction" onclick="return validateSelect(this.form) && disableSubmit(this,this.form)"><bean:message key="button.submit" /></html:submit>
				<html:submit property="submitAction" onclick="refreshPage(this.form)"><bean:message key="button.refresh"/></html:submit>	
				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
			</td>
		</tr>
		<tr>
			<td align="center"> 
				<html:submit property="submitAction" onclick="return checkAvailableAssessments() && disableSubmit(this,this.form)"><bean:message key="button.createFormula"/></html:submit>
			</td>
		 </tr>
	</table>
	<logic:notEmpty name="riskFormulaSearchForm" property="availableAssessmentTypesList" >
		<input type="hidden" name="availAsmntTypes" value="" id="availAsmntTypesId">
	</logic:notEmpty>
<%-- END BUTTON TABLE --%>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>