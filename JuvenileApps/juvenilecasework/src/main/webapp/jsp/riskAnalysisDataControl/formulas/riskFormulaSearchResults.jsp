<!DOCTYPE HTML>

<%-- Used for searching of Risk Analysis Formulas in MJCW --%>
<%--MODIFICATIONS --%>
<%-- CShimek 02/23/2012	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %> 
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../../WEB-INF/msp.tld" prefix="msp"%>
<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>



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
<title><bean:message key="title.heading" /> - riskFormulaSearchResults.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript">
function validateSelect()
{
	var sels = document.getElementsByName("selectedValue");
	for (x=0; x<sels.length; x++) 	{
		if (sels[x].checked == true) {	
			return true;
		}	
	}
	alert("Assessment Type selection is required for Preview.");
	return false;
}
function enableButtons(el, status)
{
	var aBtn = document.getElementById("activateBtn");
	var dBtn = document.getElementById("deleteBtn");
	aBtn.disabled = true;
	dBtn.disabled = true;
	if (status == "PENDING") {
		aBtn.disabled = false;
		dBtn.disabled = false;
	}
	if (el != null) {
		document.getElementById("valueSelId").value = el.value;
	}	
}
function checkPreSelect()
{
	var val = document.getElementById("valueSelId").value;
	var sels = document.getElementsByName("selectedValue");
	if (val != null && val != "") 	{
		for (x=0; x<sels.length; x++)
		{
			if (sels[x].value == val) {	
				sels[x].checked = true;
				enableButtons(null,document.getElementById(val).value);
			}	
		}
	}
	// if only 1 search result, preSelect and set button according to status
	if (val == "" && sels.length == 1){
		sels[0].checked = true;
		fld = document.getElementById(sels[0].value);
		document.getElementById("valueSelId").value = fld.value;
		enableButtons(null,fld.value);
	}
}
</script>

</head>
<body topmargin="0" leftmargin="0" onload="checkPreSelect()">
<html:form action="/handleRiskFormulaSelection" target="content">
<input type="hidden" name="valueSelected" id="valueSelId" value="<bean:write name='riskFormulaSearchForm' property='selectedValue' />" >
<!-- Begin Pagination Header Tag -->
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
	<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    	maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
  	<input type="hidden" name="pager.offset" value="<%= offset %>">
<!-- End Pagination header  -->
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" >Risk Formulas - <bean:message key="prompt.searchResults"/></td>
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
					<li>Select Assessment Type then click appropriate button to process.</li>
					<li>Click on hyperlinked Assessment Type to view Details and make allowed Updates.</li>
					<li>Click Back button to return to Search page.</li>
				</ul>
			</td>
		</tr>
	</table>
<%-- END INSTRUCTION TABLE --%>
	<div class="spacer4px">
<%-- BEGIN DISPLAY RESULTS MESSAGE TABLE --%>
	<table width="100%">
		<tr>
			<td align="center"><bean:write name="riskFormulaSearchForm" property="resultsMessage"/></td>
		</tr>
	</table>
<%-- END DISPLAY RESULTS MESSAGE TABLE --%>		
<%-- BEGIN DISPLAY RESULTS TABLE --%>
    <table width="98%" cellpadding="0" cellspacing="0" class="borderTableBlue">
		<tr>
			<td>
				<table width="100%" align="center" border="0" cellpadding="2" cellspacing="1" class="notFirstColumn">
					<tr class="detailHead">
						<td></td>
						<td nowrap="nowrap"><bean:message key="prompt.assessmentType"/>	</td>
						<td width="1%" nowrap="nowrap">
							<bean:message key="prompt.version"/>
							<jims2:sortResults beanName="riskFormulaSearchForm" results="searchResultsList" primaryPropSort="version" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="ASC" sortId="2" levelDeep="3"/>
						</td>
						<td width="1%"nowrap="nowrap">
							<bean:message key="prompt.status"/>
							<jims2:sortResults beanName="riskFormulaSearchForm" results="searchResultsList" primaryPropSort="statusDesc" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="3" levelDeep="3"/>
						</td>
						<td nowrap="nowrap"><bean:message key="prompt.category"/> ID</td>
						<td nowrap="nowrap"><bean:message key="prompt.categoryName"/></td>
					</tr>
					<logic:iterate id="asmntId" name="riskFormulaSearchForm" property="searchResultsList" indexId="index">
						<pg:item>
							<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
								<td width="1%" valign="top">
									<input type="radio" name="selectedValue" value="<bean:write name="asmntId" property="formulaId"/>" onclick="enableButtons(this,'<bean:write name="asmntId" property="statusDesc" />')">
									<input type="hidden" name="formulaStatus" id="<bean:write name="asmntId" property="formulaId"/>" value="<bean:write name="asmntId" property="statusDesc" />" >
								</td>
								<td valign="top">
									<a href="/<msp:webapp/>handleRiskFormulaSelection.do?submitAction=View&formulaId=<bean:write name='asmntId' property='formulaId'/>"><bean:write name="asmntId" property="assessmentTypeCd"/></a>
								</td>
								<td valign="top"><bean:write name="asmntId" property="version"/></td>
								<td valign="top"><bean:write name="asmntId" property="statusDesc"/></td>
								<td>
									<logic:iterate id="cat1Id" name="asmntId" property="categories" >
										<div><bean:write name="cat1Id" property="categoryId"/></div>
									</logic:iterate>
								</td>
								<td>
									<logic:iterate id="cat2Id" name="asmntId" property="categories">
										<div><bean:write name="cat2Id" property="categoryName"/></div>
									</logic:iterate>
								</td>
							</tr>
						</pg:item> 
					</logic:iterate>	
				</table>
<%-- END DISPLAY RESULTS TABLE --%>	
			</td>
		</tr>
	</table>
<%-- BEGIN PAGINATION NAVIGATOIN ROW --%>
	<table align=center>
		<tr>
			<td>											
				<pg:index>
					<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
						<tiles:put name="pagerUniqueName" value="pagerSearch"/>
						<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
					</tiles:insert>
				</pg:index>	
			</td>
		</tr>
	</table>
<%-- END PAGINATION NAVIGATOIN ROW --%>
	<div class="spacer4PX">
<%-- BEGIN BUTTON TABLE --%>
	<table border="0" width="100%">
		<tr>
			<td align="center"> 
				<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.back"/></html:submit>
				<html:submit property="submitAction" styleId="previewBtn" onclick="return validateSelect() && disableSubmit(this,this.form)"><bean:message key="button.preview" /></html:submit>
				<html:submit property="submitAction" styleId="activateBtn" onclick="disableSubmit(this,this.form)" disabled="true"><bean:message key="button.activate" /></html:submit>
				<html:submit property="submitAction" styleId="deleteBtn" onclick="disableSubmit(this,this.form)" disabled="true"><bean:message key="button.delete" /></html:submit>	
				<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.cancel"/></html:submit>
			</td>
		</tr>
	</table>
<%-- END BUTTON TABLE --%>
</pg:pager>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>