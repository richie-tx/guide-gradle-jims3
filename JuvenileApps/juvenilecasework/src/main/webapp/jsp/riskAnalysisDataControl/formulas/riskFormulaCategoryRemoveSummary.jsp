<!DOCTYPE HTML>
<%-- Used for creating Risk Formulas in MJCW --%>
<%--MODIFICATIONS --%>
<%-- CShimek	03/01/2012	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
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
<title><bean:message key="title.heading" /> - riskFormulaCategoryRemoveSummary.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
</head>

<body topmargin="0" leftmargin="0">
<html:form action="/submitRiskFormulaCategoryRemove" target="content">

<span align="center">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" >
				<bean:message key="title.riskFormulas"/> - <bean:message key="prompt.category"/>&nbsp;<bean:message key="prompt.remove"/>
				<logic:equal name="riskFormulaDetailsForm" property="pageType" value="summary">
					<bean:message key="prompt.summary" />
				</logic:equal>
				<logic:equal name="riskFormulaDetailsForm" property="pageType" value="confirm">
					<bean:message key="prompt.confirmation" />
				</logic:equal>		
			</td>
		</tr>
	</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN MESSAGES TABLE --%>
	<table width="98%" border="0" align="center">
		<logic:equal name="riskFormulaDetailsForm" property="pageType" value="confirm">
			<tr>
				<td align="center" class="confirm"><bean:write name="riskFormulaDetailsForm" property="confirmMessage"/></td>
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
				<logic:equal name="riskFormulaDetailsForm" property="pageType" value="summary">
					<ul>
						<li>Select Back button to return to Formula Details page.</li> 
						<li>Select Finish button to remove category from Formula.</li>       
					</ul>
				</logic:equal>
				<logic:equal name="riskFormulaDetailsForm" property="pageType" value="confirm">
					<ul>
						<li>Select Formula Search button to return to search page.</li>
					</ul>
				</logic:equal>
			</td>
		</tr>
	</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN FORMULA BLOCK TABLE --%>	
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
        	<td class="detailHead">
<%-- BEGIN FORMULA COLAPSE TABLE --%>        	
  				<table width="100%" cellpadding="2" cellspacing="0">
            		<tr>
						<td class="detailHead"><bean:message key="prompt.formula"/></td>
 					</tr>
				</table>
<%-- END FORMULA COLAPSE TABLE --%>   				
			</td>
		</tr>
		<tr>
			<td>	
<%-- BEGIN FORMULA DETAIL TABLE --%>
				<tiles:insert page="riskFormulaInfoTile.jsp" flush="true">
					<tiles:put name="formName" type="String" value="riskFormulaDetailsForm" />
					<tiles:put name="boxTitle" type="String" value="Formula Information" />
					<tiles:put name="borderClass" type="String" value="borderTableGrey" />
				</tiles:insert>
<%-- END FORMULA DETAIL TABLE --%>
			</td>
		</tr>
	</table> 
<%-- END FORMULA BLOCK TABLE --%>	
	<div class="spacer4px"></div>
<%-- BEGIN ATTACHED CATEGORIES TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr class="detailHead">
			<td><bean:message key="prompt.categoriesCurrentlyAttachedToFormula"/></td>
		</tr>
		<tr>
			<td>
<%-- BEGIN ATTACHED CATEGORIES LIST TABLE --%>			
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
					<tr class="formDeLabel">
						<td width="33%"><bean:message key="prompt.categoryName"/></td>
						<td width="40%"><bean:message key="prompt.description"/></td>
						<td width="15%"><bean:message key="prompt.riskResultGroup"/></td>
						<td width="12%"><bean:message key="prompt.entryDate"/></td>
					</tr>
					<logic:iterate id="athdId" name="riskFormulaDetailsForm" property="currentCategoriesList" indexId="index1">
						<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
				 			<td><bean:write name="athdId" property="categoryName"/></td>
							<td><bean:write name="athdId" property="description"/></td>
							<td><bean:write name="athdId" property="riskResultGroupDesc"/></td> 
							<td><bean:write name="athdId" property="entryDate" formatKey="date.format.mmddyyyy"/></td>
						</tr>
					</logic:iterate>
				</table>
<%-- END ATTACHED CATEGORIES LIST TABLE --%>					
			</td>
		</tr>
	</table>
<%-- END ATTACHED CATEGORIES TABLE --%>
<div class="spacer4px"></div>
<%-- BEGIN CATEGORY REMOVE TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr class="detailHead">
			<td><bean:message key="prompt.categories"/>
				<logic:equal name="riskFormulaDetailsForm" property="pageType" value="summary">
					to be
				</logic:equal>
				Removed from Formula
			</td>
		</tr>
		<tr>
			<td>
<%-- BEGIN CATEGORY REMOVE LIST TABLE --%>			
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
					<tr class="formDeLabel">
						<td width="33%"><bean:message key="prompt.categoryName"/></td>
						<td width="40%"><bean:message key="prompt.description"/></td>
						<td width="15%"><bean:message key="prompt.riskResultGroup"/></td>
						<td width="12%"><bean:message key="prompt.entryDate"/></td>
					</tr>
					<logic:iterate id="remdId" name="riskFormulaDetailsForm" property="removedCategoriesList" indexId="index2">
						<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
				 			<td><bean:write name="remdId" property="categoryName"/></td>
							<td><bean:write name="remdId" property="description"/></td>
							<td><bean:write name="remdId" property="riskResultGroupDesc"/></td> 
							<td><bean:write name="remdId" property="entryDate" formatKey="date.format.mmddyyyy"/></td>
						</tr>
					</logic:iterate>
				</table>
<%-- END CATEGORY REMOVE LIST TABLE --%>					
			</td>
		</tr>
	</table>
<%-- END CATEGORY REMOVE TABLE --%>
</span>
<div class="spacer4px"></div>
<table border="0" width="100%">
	<tr>
		<td align="center">
			<logic:equal name="riskFormulaDetailsForm" property="pageType" value="summary">
				<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.back"/></html:submit>
				<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.finish" /></html:submit>
				<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.cancel"/></html:submit>
			</logic:equal>
			<logic:equal name="riskFormulaDetailsForm" property="pageType" value="confirm">
				<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.formulaDetails"/></html:submit>
			</logic:equal>	
		</td>
	</tr>
</table>
<div class="spacer4px"></div>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>