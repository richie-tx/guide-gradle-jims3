<!DOCTYPE HTML>

<%-- Used for creating Risk Formulas in MJCW --%>
<%--MODIFICATIONS --%>
<%-- CShimek	02/24/2012	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
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

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - riskFormulaCreateSummary.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
</head>

<body topmargin="0" leftmargin="0">
<html:form action="/submitRiskFormulaCreate" target="content">

<span align="center">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" >
				<bean:message key="title.riskFormulas"/> - <bean:message key="prompt.create"/>&nbsp;<bean:message key="prompt.formula"/>
				<logic:equal name="riskFormulaCreateForm" property="pageType" value="summary">
					<bean:message key="prompt.summary" />
				</logic:equal>
				<logic:equal name="riskFormulaCreateForm" property="pageType" value="confirm">
					<bean:message key="prompt.confirmation" />
				</logic:equal>		
			</td>
		</tr>
	</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN MESSAGES TABLE --%>
	<table width="98%" border="0" align="center">
		<logic:equal name="riskFormulaCreateForm" property="pageType" value="confirm">
			<tr>
				<td align="center" class="confirm">Formula successfully created.</td>
			</tr>
		</logic:equal>
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
<%-- END MESSAGES TABLE --%>
	<div class="spacer4px"></div>	
<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="98%" border="0">
		<tr>
			<td>
				<logic:equal name="riskFormulaCreateForm" property="pageType" value="summary">
					<ul>
						<li>Select Finish button to create formula.</li>
						<li>Select Back button to return to create formula page.</li>       
					</ul>
				</logic:equal>
				<logic:equal name="riskFormulaCreateForm" property="pageType" value="confirm">
					<ul>
						<li>Select Formula Search button to return to search page.</li>
					</ul>
				</logic:equal>
			</td>
		</tr>
	</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN FORMULA INFO TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td class="detailHead">
				<table width="100%" cellpadding="2" cellspacing="0">
					<tr>
						<td class="detailHead"><bean:message key="prompt.formula"/></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>  	
			<td>
<%-- BEGIN FORMULA DETAILS TABLE --%>	
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
					<tr class="detailHead">
						<td colspan="4"><bean:message key="prompt.formula"/>&nbsp;<bean:message key="prompt.information"/></td>
					</tr>
					<logic:equal name="riskFormulaCreateForm" property="pageType" value="summary">
						<tr>
							<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.assessmentType"/></td>
							<td class="formDe"><bean:write name="riskFormulaCreateForm" property="assessmentTypeId" /></td>
						</tr>
					</logic:equal>	
					<logic:equal name="riskFormulaCreateForm" property="pageType" value="confirm">
						<tr>
							<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.assessmentType"/></td>
							<td class="formDe" colspan="3"><bean:write name="riskFormulaCreateForm" property="assessmentTypeId" /></td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.version"/></td>
							<td class="formDe" width="45%"><bean:write name="riskFormulaCreateForm" property="version" /></td>
							<td class="formDeLabel"><bean:message key="prompt.status"/></td>
							<td class="formDe"><bean:write name="riskFormulaCreateForm" property="statusDesc" /></td>
						</tr>
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.entryDate"/></td>
							<td class="formDe" ><bean:write name="riskFormulaCreateForm" property="entryDate" formatKey="datetime.format.mmddyyyy" /></td>
							<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.activationDate"/></td>
							<td class="formDe"><bean:write name="riskFormulaCreateForm" property="activationDate" formatKey="datetime.format.mmddyyyy"/></td>
						</tr>
					</logic:equal>	
				</table>
<%-- END FORMULA DETAILS TABLE --%>					
				<div class="spacer4px"></div>
<%-- BEGIN SELECTED CATEGORIES INFO TABLE --%>								
				<table width="100%" cellpadding="2" cellspacing="1" class="borderTableGrey">
					<tr class="detailHead">
						<td colspan="3"><bean:message key="prompt.formula"/> <bean:message key="prompt.categories"/> - <bean:message key="prompt.selectedList"/></td>
					</tr>
					<tr class="listHeader">
						<td class="formDeLabel" width="35%" ><bean:message key="prompt.categoryName"/></td>
						<td class="formDeLabel" width="50%" ><bean:message key="prompt.description"/></td>
						<td class="formDeLabel" width="15%" ><bean:message key="prompt.riskResultGroup"/></td>
					</tr>
					<logic:iterate id="catId" name="riskFormulaCreateForm" property="selectedCategoriesList" indexId="index1">
						<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
							<td><bean:write name="catId" property="categoryName"/></td>
							<td><bean:write name="catId" property="description"/></td>
							<td><bean:write name="catId" property="riskResultGroupDesc"/></td>
						</tr>
					</logic:iterate>
				</table>	
<%-- END SELECTED CATEGORIES INFO TABLE --%>	
			</td>
		</tr>      
	</table>
<%-- END FORMULA INFO TABLE --%>	
</span>
<div class="spacer4px"></div>
<%-- BEGIN BUTTON TABLE --%>
<table border="0" width="100%">
	<tr>
		<td align="center">
			<logic:equal name="riskFormulaCreateForm" property="pageType" value="summary">
				<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.back"/></html:submit>
				<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.finish" /></html:submit>
				<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.cancel"/></html:submit>
			</logic:equal>
			<logic:equal name="riskFormulaCreateForm" property="pageType" value="confirm">
				<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.formulaSearch"/></html:submit>
			</logic:equal>	
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>
<div class="spacer4px"></div>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>