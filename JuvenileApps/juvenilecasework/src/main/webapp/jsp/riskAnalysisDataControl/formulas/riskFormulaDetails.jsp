<!DOCTYPE HTML>

<%-- Used for creating Risk Formulas in MJCW --%>
<%--MODIFICATIONS --%>
<%-- CShimek	02/27/2012	Create JSP --%>
<%-- CShimek	12/14/2012  #74807 checkAttachedCategorySelect() to check for 1 category --%>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.UIConstants" %>


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
<title><bean:message key="title.heading" /> - riskFormulaDetails.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript'>
function checkRecommendationSelect(ind)
{
	var sels = document.getElementsByName("selectedRecommendationId");
	var btn = "Update";
	for (x=0; x<sels.length; x++) {
		if (sels[x].checked == true)
		{
			if (ind == 'R') {	
				setRemoveType(ind)
			}	
			return true;
		}	
	}	
	if (ind == 'R') {
		btn = "Remove";
	}	
	alert("Recommendation selection required for " + btn + ".");
	return false;
	
}
function checkAttachedCategorySelect()
{
	var flds = document.getElementsByName("selectedCategoryId");
	if (flds.length == 1 && flds[0].checked == true){
		alert("At least 1 Category must be attached to Formula. Please Add at least 1 other Category then try Remove again.");
		return false;
	}
	for (x=0; x<flds.length; x++) {
		if (flds[x].checked == true) {
			setRemoveType("C");
			return true;
		}
	}	
	alert("Category Currently Attached to Formula selection is required for Remove.");
	return false;
}

function selectAllCategories(el)
{
	var cbs = document.getElementsByName("selectedValues");
	if (cbs != null){
		for (x=0; x<cbs.length; x++) {
			cbs[x].checked = el.checked;
		}
	}		
}

function setRemoveType(btnType)
{
	document.getElementById("removeTypeId").value = btnType;
}

function checkCataegroySelect()
{
	var cbs = document.getElementsByName("selectedValues");
	if (cbs != null){
		for (x=0; x<cbs.length; x++) {
			if (cbs[x].checked == true) {
				return true;
			}	
		}
	}
	alert("Categories - Selectable List selection is required for Add Selected.");
	return false;
}
function validate1Category()
{
	var flds = document.getElementsByName("selectableCategoriesList");
	if (flds != null){
		for (x=0; x<flds; x++) {
			if (flds[x].checked == true) {
				return true;
			}else{
				alert("At least one category must be attached to Formula.");
				return false;	
			}	
			}
		}
}
function checkAvailable()
{
	var sels = document.getElementsByName("selectedRecommendationId");
	if (sels == null || sels.length == 0) {
		if (document.getElementById("RecUpdateBtn") != null) {
			document.getElementById("RecUpdateBtn").disabled = true;
			document.getElementById("RecRemoveBtn").disabled = true;
		}
	}
	sels = document.getElementsByName("selectedCategoryId");
	if (sels == null || sels.length == 0) {
		if (document.getElementById("CatRemoveBtn") != null) {
			document.getElementById("CatRemoveBtn").disabled = true;
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
<body topmargin="0" leftmargin="0" onload="checkAvailable()">
<html:form action="/handleRiskFormulaDetailsSelection" target="content">
<html:hidden name="riskFormulaDetailsForm" property="removeType" styleId="removeTypeId"/>
<span align="center">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" >
				<bean:message key="title.riskFormulas"/> - <bean:message key="prompt.formula"/>&nbsp;<bean:message key="prompt.details"/>
			</td>
		</tr>
	</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN MESSAGES TABLE --%>
	<table width="98%" border="0" align="center">
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
				<ul>
					<li>Click on hyperlinked Category Name to view Category Details.</li>       
					<li>Select Cancel button to return to formula search page.</li>
				</ul>
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
						<td class="detailHead">
							<a href="javascript:showHideMulti('Formula', 'fmla', 1, '/<msp:webapp/>')">
			   					<img border='0' src="/<msp:webapp/>images/expand.gif" name="Formula"/></a>
							<bean:message key="prompt.formula"/>
						</td>
 					</tr>
				</table>
<%-- END FORMULA COLAPSE TABLE --%>   				
			</td>
		</tr>
		<tr id="fmla0" class='hidden'>
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
<%-- BEGIN RECOMMENDATIONS TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr class="detailHead">
			<td>Recommendations Currently Attached to Formula</td>
		</tr>
		<tr>
			<td>
<%-- BEGIN RECOMMENDATIONS LIST TABLE --%>			
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
					<logic:empty name="riskFormulaDetailsForm" property="recommendationsList">
						<tr>
							<td class="formDe" width="100%">No Recommendations currently attached to this formula.</td>
						</tr>
					</logic:empty>
					<logic:notEmpty name="riskFormulaDetailsForm" property="recommendationsList">
						<tr class="formDeLabel">
							<logic:equal name="riskFormulaDetailsForm" property="formulaUpdatable" value="true">
								<td width="1%"></td>
							</logic:equal>
							<td><bean:message key="prompt.recommendationName"/></td>
							<td nowrap="nowrap"><bean:message key="prompt.formulaName"/></td>
							<td><bean:message key="prompt.minScore"/></td>
							<td><bean:message key="prompt.maxScore"/></td>
							<td><bean:message key="prompt.recommendation"/></td>
							<td><bean:message key="prompt.custody"/></td>
							<td><bean:message key="prompt.riskResultGroup"/></td>
						</tr>
						<logic:iterate id="rId" name="riskFormulaDetailsForm" property="recommendationsList" indexId="index1">
							<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
								<logic:equal name="riskFormulaDetailsForm" property="formulaUpdatable" value="true">
									<td width="1%"><input type="radio" name="selectedRecommendationId" value="<bean:write name='rId' property='recommendationId'/>" ></td>
								</logic:equal>	
					 			<td>
									<a href="/<msp:webapp/>handleRiskFormulaDetailsSelection.do?submitAction=Link&recommendationId=<bean:write name='rId' property='recommendationId'/>"><bean:write name="rId" property="recommendationName"/></a>
								</td>
								<td><bean:write name="rId" property="assessmentTypeName"/></td>
								<td><bean:write name="rId" property="minScore"/></td>
								<td><bean:write name="rId" property="maxScore"/></td>
								<td><bean:write name="rId" property="recommendationDesc"/></td>
								<td>
									<logic:equal name="rId" property="custody" value="true">
										<%=UIConstants.YES_FULL_TEXT%>
									</logic:equal>	
									<logic:equal name="rId" property="custody" value="false">
										<%=UIConstants.NO_FULL_TEXT%>
									</logic:equal>	
								</td>
								<td><bean:write name="rId" property="resultGroupDisplayDesc"/></td> 
							</tr>
						</logic:iterate>
					</logic:notEmpty>
				</table>
<%-- END RECOMMENDATIONS LIST TABLE --%>					
			</td>
		</tr>
		<logic:equal name="riskFormulaDetailsForm" property="formulaUpdatable" value="true">
			<tr>
				<td align="center">
					<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.add"/></html:submit>
					<html:submit property="submitAction" onclick="return checkRecommendationSelect('U') && disableSubmit(this,this.form)" styleId="recUpdateBtn"><bean:message key="button.update" /></html:submit>
					<html:submit property="submitAction" onclick="return checkRecommendationSelect('R') && disableSubmit(this,this.form)" styleId="recRemoveBtn"><bean:message key="button.remove"/></html:submit>
				</td>
			</tr>
		</logic:equal>
	</table>
<%-- END RECOMMENDATIONS TABLE --%>
	<div class="spacer4px"></div>
<%-- BEGIN ATTACHED CATEGORIES TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr class="detailHead">
			<td>Categories Currently Attached to Formula</td>
		</tr>
		<tr>
			<td>
<%-- BEGIN ATTACHED CATEGORIES LIST TABLE --%>			
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
					<logic:empty name="riskFormulaDetailsForm" property="currentCategoriesList">
						<tr>
							<td class="formDe" width="100%">No Categories currently attached to this formula.</td>
						</tr>
					</logic:empty>
					<logic:notEmpty name="riskFormulaDetailsForm" property="currentCategoriesList">
						<tr class="formDeLabel">
							<logic:equal name="riskFormulaDetailsForm" property="formulaUpdatable" value="true">
								<td width="1%"></td>
							</logic:equal>	
							<td width="32%"><bean:message key="prompt.categoryName"/></td>
							<td width="40%"><bean:message key="prompt.description"/></td>
							<td width="15%"><bean:message key="prompt.riskResultGroup"/></td>
							<td width="12%"><bean:message key="prompt.entryDate"/></td>
						</tr>
						<logic:iterate id="curCatId" name="riskFormulaDetailsForm" property="currentCategoriesList" indexId="index1">
							<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
								<logic:equal name="riskFormulaDetailsForm" property="formulaUpdatable" value="true">
									<td width="1%"><input type="radio" name="selectedCategoryId" value="<bean:write name='curCatId' property='categoryId'/>" ></td>
								</logic:equal>
					 			<td>
									<a href="/<msp:webapp/>displayRiskFormulaCategoryDetails.do?submitAction=View&categoryId=<bean:write name='curCatId' property='categoryId'/>"><bean:write name="curCatId" property="categoryName"/></a>
								</td>
								<td><bean:write name="curCatId" property="description"/></td>
								<td><bean:write name="curCatId" property="riskResultGroupDesc"/></td> 
								<td><bean:write name="curCatId" property="entryDate" formatKey="date.format.mmddyyyy"/></td>
							</tr>
						</logic:iterate>
					</logic:notEmpty>
				</table>
<%-- END ATTACHED CATEGORIES LIST TABLE --%>					
			</td>
		</tr>
		<logic:equal name="riskFormulaDetailsForm" property="formulaUpdatable" value="true">
			<tr>
				<td align="center">
					<html:submit property="submitAction" onclick="return checkAttachedCategorySelect() && disableSubmit(this,this.form)" styleId="CatRemoveBtn"><bean:message key="button.remove" /></html:submit>
				</td>
			</tr>
		</logic:equal>
	</table>
<%-- END ATTACHED CATEGORIES TABLE --%>
	<div class="spacer4px"></div>
	<logic:equal name="riskFormulaDetailsForm" property="formulaUpdatable" value="true">
<%-- BEGIN SELECTABLE CATEGORY TABLE --%>
		<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
			<tr class="detailHead">
				<td colspan="5"><bean:message key="prompt.categories"/> Available to be Selected</td>
			</tr>
			<tr>
				<td colspan="5">
<%-- BEGIN SELECTABLE LIST TABLE --%>							
					<table width="100%" cellpadding="2" cellspacing="1" border="0">
						<tr>
							<td>
								<table width="100%" cellpadding="2" cellspacing="0" class="borderTableBlue">
									<tr class="detailHead">
										<td colspan="5"><bean:message key="prompt.categories"/> - <bean:message key="prompt.selectableList"/></td>
									</tr>
								</table>
								<logic:empty name="riskFormulaDetailsForm" property="selectableCategoriesList">
									<tr>
										<td class="formDe" width="100%">No Categories available for selection.</td>
									</tr>
								</logic:empty>
								<logic:notEmpty name="riskFormulaDetailsForm" property="selectableCategoriesList">
									<bean:size id="sel1" name="riskFormulaDetailsForm" property="selectableCategoriesList"/>
									<table width="100%" cellpadding="0" cellspacing="0" class="borderTable">	
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1">
													<tr class="listHeader">
														<td class="formDeLabel" width="1%" >
															<input type="checkbox" name="selectAll" id="masterSelect" onclick="selectAllCategories(this)">
														</td>
														<td class="formDeLabel" width="30%" ><bean:message key="prompt.categoryName"/></td>
														<logic:greaterThan name="sel1" value="3">
															<td class="formDeLabel" width="49%" ><bean:message key="prompt.description"/></td>
															<td class="formDeLabel" width="18%" ><bean:message key="prompt.riskResultGroup"/></td>
															<td class="formDeLabel" width="1%">&nbsp;</td>
														</logic:greaterThan>
														<logic:lessThan name="sel1" value="4">
															<td class="formDeLabel" width="50%" ><bean:message key="prompt.description"/></td>
															<td class="formDeLabel" width="18%" ><bean:message key="prompt.riskResultGroup"/></td>
														</logic:lessThan>
													</tr>
												</table>
											</td>
										</tr>
									</table>			
									<div class="scrollingDiv100">
										<table width="100%" cellpadding="2" cellspacing="1">
											<logic:iterate id="cat1Id" name="riskFormulaDetailsForm" property="selectableCategoriesList" indexId="index2">
												<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
													<td width="1%" valign="top">
														<input type="checkbox" name="selectedValues" value="<bean:write name="cat1Id" property="categoryId"/>" onclick="resetMaster(this)">
													</td>
													<td width="30%" valign="top">
														<a href="/<msp:webapp/>displayRiskFormulaCategoryDetails.do?submitAction=View&categoryId=<bean:write name='cat1Id' property='categoryId'/>"><bean:write name="cat1Id" property="categoryName"/></a>
													</td>
													<td width="50%" valign="top"><bean:write name="cat1Id" property="description"/></td>
													<td width="18%" valign="top"><bean:write name="cat1Id" property="riskResultGroupDesc"/></td>
												</tr>
											</logic:iterate>
										</table>	
									</div>
								</logic:notEmpty>
							</td>
						</tr>		
					</table>
<%-- END SELECTABLE LIST TABLE --%>	
<%-- BEGIN SELECTABLE LIST BUTTON TABLE --%>							
					<table border="0" width="100%">
						<tr>
							<td align="center">
								<html:submit property="submitAction" onclick="return checkCataegroySelect() && disableSubmit(this, this.form)"><bean:message key="button.addSelected"/></html:submit>
							</td>
						</tr>
					</table>
<%-- END SELECTABLE LIST BUTTON TABLE --%>								
				</td>	
			</tr>
			<logic:notEmpty name="riskFormulaDetailsForm" property="selectedCategoriesList" > 					
				<tr>
					<td colspan="5">
<%-- BEGIN SELECTED LIST TABLE --%>	
						<table width="100%" cellpadding="2" cellspacing="1" border="0">
							<tr>
								<td>
									<table width="100%" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td colspan="5"><bean:message key="prompt.categories"/> - <bean:message key="prompt.selectedList"/></td>
										</tr>
									</table>
									<table width="100%" cellpadding="0" cellspacing="0" class="borderTable">	
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1">
													<tr class="listHeader">
														<td class="formDeLabel" width="7%" >&nbsp;</td>
														<td class="formDeLabel" width="40%" ><bean:message key="prompt.categoryName"/></td>
														<td class="formDeLabel" width="53%" ><bean:message key="prompt.description"/></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>			
									<div class="scrollingDiv100">
										<table width="100%" cellpadding="2" cellspacing="1">
											<logic:iterate id="catId2" name="riskFormulaDetailsForm" property="selectedCategoriesList" indexId="index3">
												<tr class="<%out.print((index3.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
											 		<td width="7%" valign="top">
														<a href="/<msp:webapp/>handleRiskFormulaDetailsSelection.do?submitAction=Remove Selected&categoryId=<bean:write name='catId2' property='categoryId'/>"><bean:message key="prompt.remove" /></a>
													</td>  
													<td width="40%" valign="top">
														<a href="/<msp:webapp/>displayRiskFormulaCategoryDetails.do?submitAction=View&categoryId=<bean:write name="catId2" property="categoryId"/>"><bean:write name="catId2" property="categoryName"/></a>
													</td>
													<td width="53%" valign="top"><bean:write name="catId2" property="description"/></td>
												</tr>
											</logic:iterate>
										</table>	
									</div>
								</td>
							</tr>		
						</table>
<%-- END SELECTED LIST TABLE --%>								
					</td>	
				</tr>
				<tr>
					<td align="center">
						<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.cancelSelection"/></html:submit>
					</td>
				</tr>
			</logic:notEmpty>	
		</table>	
<%-- END SELECTABLE TABLE --%>
	</logic:equal>
</span>
<div class="spacer4px"></div>
<table border="0" width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.back"/></html:submit>
			<logic:equal name="riskFormulaDetailsForm" property="formulaUpdatable" value="true">
				<html:submit property="submitAction" onclick="return validate1Category() && disableSubmit(this,this.form)"><bean:message key="button.next"/></html:submit>
			</logic:equal>	
			<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.cancel"/></html:submit>
		</td>
	</tr>
</table>
<div class="spacer4px"></div>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>