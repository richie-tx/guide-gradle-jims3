<!DOCTYPE HTML>

<%-- Used for searching of Risk Questions in MJCW --%>
<%--MODIFICATIONS --%>
<%-- Dwilliamson  10/26/2010	Create JSP --%>
<%-- CShimek      03/15/2011	Corrected setFocus error(found while testing defect 69638) and revised instructions to be more informative ( --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>



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
<title><bean:message key="title.heading" /> - riskQuestionSearch.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/riskAnalysis.js"></script>
</head>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">

<%--BEGIN FORM TAG--%>
<html:form action="/handleRiskQuestionSearch" target="content" focus="questionId">

	<input type="hidden" name="helpFile" value="">
<!-- BEGIN HEADING TABLE -->
	<table width="100%">
		<tr>
			<td align="center" class="header" ><bean:message key="title.riskQuestions"/> - <bean:message key="title.search"/> </td>
		</tr>
	</table>
<!-- END HEADING TABLE -->
<%-- BEGIN ERROR TABLE --%>
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
<%-- END ERROR TABLE --%>
<!-- BEGIN INSTRUCTION TABLE -->
	<table width="98%" border="0">
    	<tr>
			<td>
				<ul>
					<li>Select a question name from list then click submit to view details.</li>
					<li>Click Create Question button to create new question.</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td class="required"><bean:message key="prompt.2.diamond" /> <bean:message key="prompt.requiredFieldsInstruction"/></td>
		</tr>
	</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN SEARCH BY QUESTION TABLE -->
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr>
			<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" />&nbsp;<bean:message key="prompt.questionName"/></td>
			<td class="formDe">
				<html:select styleId="questionId" name="riskQuestionSearchForm" property="questionId">
					<html:option value=""><bean:message key="select.generic" /></html:option>
					<html:optionsCollection property="questions" value="questionId" label="questionName" />
				</html:select> 
			</td>
		</tr>
	</table>
<!-- END SEARCH BY QUESTION TABLE -->
	<br>
  <!-- BEGIN BUTTON TABLE -->
	<table border="0" width="100%">
		<tr>
			<td align="center"> 
			 <!-- html:submit property="submitAction"><bean:message key="button.submit" /></html:submit-->
				<html:submit property="submitAction" onclick="return validateSearchFields(this.form) && disableSubmit(this, this.form)"><bean:message key="button.submit" /></html:submit>
				<html:submit property="submitAction" onclick="disableSubmit(this, this.form);"><bean:message key="button.refresh"/></html:submit>	
				<html:submit property="submitAction" onclick="disableSubmit(this, this.form);"><bean:message key="button.cancel"/></html:submit>
			</td>
		</tr>
		<tr>
			<td align="center"> 
				<html:submit property="submitAction" onclick="disableSubmit(this, this.form);"><bean:message key="button.createQuestion"/></html:submit>
			</td>
		</tr>
	</table>
<!-- END BUTTON TABLE -->

</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>