<!DOCTYPE HTML>

<%-- Used for creating Risk Formulas in MJCW --%>
<%--MODIFICATIONS --%>
<%-- CShimek	02/23/2012	Create JSP --%>

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

<%-- STYLE SHEET LINK --%>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - riskFormulaCreate.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript">
function validateInput()
{
	var indx = document.getElementById("asmtTypeId").selectedIndex;
	if(indx == 0) {
		alert("Assessment Type selection is required.");
		return false;
	}
	return true;
}
function checkSelect()
{
	var cbs = document.getElementsByName("selectedValues");
	if ( cbs != null && cbs !="undefined"){
		alert("stuff:" + cbs);
		for (x=0; x<cbs.length; x++) {
			if (cbs(x).checked == true) {
				return true;
			}
		}
	}	
	alert("No Category selected to Add.");
	return false;
}

function checkElements(){
	var checked=false;
	var elements = document.getElementsByName("selectedValues");
	for(var i=0; i < elements.length; i++){
		if(elements[i].checked) {
			checked = true;
		}
	}
	if (!checked) {
		alert('No Category selected to Add.');
	}
	return checked;
}

function selectAllCategories(el)
{
	var cbs = document.getElementsByName("selectedValues");
	if (cbs != null){
		for (x=0; x<cbs.length; x++) {
			cbs(x).checked = el.checked;
		}
	}	
}
function resetMaster(el)
{
	if (el.checked == false)
	{
		document.getElementById("masterSelect").checked = false;
	}	
}
</script>
</head>

<body topmargin="0" leftmargin="0">
<html:form action="/displayRiskFormulaCreateSummary" target="content" focus="assessmentTypeId">

<span align="center">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" ><bean:message key="title.riskFormulas"/> - <bean:message key="prompt.create"/>&nbsp;<bean:message key="prompt.formula"/></td>
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
	<div class="spacer4px"></div>	
<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="98%" border="0">
		<tr>
			<td>
				<ul>
					<li>Select Back button to return to Search page.</li>
					<li>Select Next button to continue to Summary page.</li>       
				</ul>
			</td>
		</tr>
		<tr>
			<td class="required"><bean:message key="prompt.3.diamond" /> <bean:message key="prompt.requiredFieldsInstruction"/></td>
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
<%-- BEGIN FORMULA INPUT TABLE --%>			
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
					<tr class="detailHead">
						<td colspan="2"><bean:message key="prompt.formula"/>&nbsp;<bean:message key="prompt.information"/></td>
					</tr>
					<tr>
						<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond" />&nbsp;<bean:message key="prompt.assessmentType"/></td>
						<td class="formDe">
							<html:select name="riskFormulaCreateForm" property="assessmentTypeId" styleId="asmtTypeId">
								<html:option value=""><bean:message key="select.generic"/></html:option>
								<html:optionsCollection property="assessmentTypes" value="code" label="description" />
							</html:select>
						</td>
					</tr>
<%-- 					<tr>	
						<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.riskType"/></td>
						<td class="formDe" width="35%">
							<html:select name="riskFormulaCreateForm" property="riskTypeId" >
								<html:option value=""><bean:message key="select.generic"/></html:option>
								<html:optionsCollection property="riskResultGroups" value="code" label="description" />
							</html:select>
						</td>
					</tr>  --%>
				</table>
<%-- END FORMULA INPUT TABLE --%>					
				<div class="spacer4px"></div>
<%-- BEGIN SELECTABLE LIST TABLE --%>							
				<table width="100%" cellpadding="2" cellspacing="0" class="borderTableGrey">
					<tr class="detailHead">
						<td colspan="5"><bean:message key="prompt.formula"/> <bean:message key="prompt.categories"/> - <bean:message key="prompt.selectableList"/>  </td>
					</tr>
				</table>
				<bean:size id="sel1" name="riskFormulaCreateForm" property="selectableCategoriesList"/>
				<table width="100%" cellpadding="0" cellspacing="0" class="borderTableGrey">	
					<tr>
						<td>
							<table width="100%" cellpadding="2" cellspacing="1">
								<tr class="listHeader">
									<td class="formDeLabel" width="1%" >
										<input type="checkbox" name="selectAll" id="masterSelect" onclick="selectAllCategories(this)">
									</td>
									<td class="formDeLabel" width="30%" ><bean:message key="prompt.categoryName"/></td>
									<logic:greaterThan name="sel1" value="4">
										<td class="formDeLabel" width="49%" ><bean:message key="prompt.description"/></td>
										<td class="formDeLabel" width="18%" ><bean:message key="prompt.riskResultGroup"/></td>
										<td class="formDeLabel" width="1%">&nbsp;</td>
									</logic:greaterThan>
									<logic:lessThan name="sel1" value="5">
										<td class="formDeLabel" width="50%" ><bean:message key="prompt.description"/></td>
										<td class="formDeLabel" width="18%" ><bean:message key="prompt.riskResultGroup"/></td>
									</logic:lessThan>
								</tr>
							</table>
						</td>
					</tr>
				</table>			
				<div class="scrollingDiv200">
					<table width=100% cellpadding=2 cellspacing=1>
						<logic:iterate id="catId" name="riskFormulaCreateForm" property="selectableCategoriesList" indexId="index1">
							<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="10%">
								<td width="1%" valign="top">
									<input type="checkbox" name="selectedValues" value="<bean:write name="catId" property="categoryId"/>" onclick="resetMaster(this)">
								</td>
								<td width="30%" valign="top"><bean:write name="catId" property="categoryName"/>
								</td>
								<td width="50%"><bean:write name="catId" property="description"/></td>
								<td width="18%" valign="top"><bean:write name="catId" property="riskResultGroupDesc"/></td>
							</tr>
						</logic:iterate>
					</table>	
				</div>
<%-- END SELECTABLE LIST TABLE --%>	
<%-- BEGIN SELECTABLE LIST BUTTON TABLE --%>							
				<table border="0" width="100%">
					<tr>
						<td align="center">
							<html:submit property="submitAction" onclick="return validateInput() && checkElements() && disableSubmit(this, this.form)"><bean:message key="button.addSelected"/></html:submit>
						</td>
					</tr>
				</table>
<%-- END SELECTABLE LIST BUTTON TABLE --%>	
				<logic:notEmpty name="riskFormulaCreateForm" property="selectedCategoriesList" >							
<%-- BEGIN SELECTED LIST TABLE --%>						
					<table width="100%" cellpadding="2" cellspacing="1" border="0">
						<tr>
							<td>
								<table width="100%" cellpadding="2" cellspacing="0" class="borderTableGrey">
									<tr class="detailHead">
										<td colspan="5"><bean:message key="prompt.formula"/>&nbsp;<bean:message key="prompt.categories"/> - <bean:message key="prompt.selectedList"/></td>
									</tr>
								</table>
								<bean:size id="sel2" name="riskFormulaCreateForm" property="selectedCategoriesList"/>
								<table width="100%" cellpadding="0" cellspacing="0" class="borderTableGrey">	
									<tr>
										<td>
											<table width="100%" cellpadding="2" cellspacing="1">
												<tr class="listHeader">
													<td class="formDeLabel" width="7%" >&nbsp;</td>
													<td class="formDeLabel" width="30%" ><bean:message key="prompt.categoryName"/></td>
													<logic:greaterThan name="sel2" value="4">
														<td class="formDeLabel" width="42%" ><bean:message key="prompt.description"/></td>
														<td class="formDeLabel" width="18%" ><bean:message key="prompt.riskResultGroup"/></td>
														<td class="formDeLabel" width="1%">&nbsp;</td>
													</logic:greaterThan>
													<logic:lessThan name="sel2" value="5">
														<td class="formDeLabel" width="43%" ><bean:message key="prompt.description"/></td>
														<td class="formDeLabel" width="18%" ><bean:message key="prompt.riskResultGroup"/></td>
													</logic:lessThan>
												</tr>
											</table>
										</td>
									</tr>
								</table>			
								<div class="scrollingDiv200">
									<table width="100%" cellpadding="2" cellspacing="1">
										<logic:iterate id="catId2" name="riskFormulaCreateForm" property="selectedCategoriesList" indexId="index2">
											<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="10%">
										 		<td width="7%" valign="top">
													<a href="/<msp:webapp/>displayRiskFormulaCreateSummary.do?submitAction=Remove&categoryID=<bean:write name='catId2' property='categoryId'/>"><bean:message key="prompt.remove" /></a>
												</td>  
												<td width="30%" valign="top"><bean:write name="catId2" property="categoryName"/></td>
												<td width="43%" valign="top"><bean:write name="catId2" property="description"/></td>
												<td width="18%" valign="top"><bean:write name="catId2" property="riskResultGroupDesc"/></td>
											</tr>
										</logic:iterate>
									</table>	
								</div>
							</td>
						</tr>		
					</table>
				</logic:notEmpty>	
<%-- END SELECTED LIST TABLE --%> 							
			</td>
		</tr>      
	</table>
</span>
<div class="spacer4px"></div>
<table border="0" width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.back"/></html:submit>
			<html:submit property="submitAction" onclick="return validateInput() && disableSubmit(this,this.form)"><bean:message key="button.next" /></html:submit>
			<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.cancel"/></html:submit>
		</td>
	</tr>
</table>
<div class="spacer4px"></div>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>