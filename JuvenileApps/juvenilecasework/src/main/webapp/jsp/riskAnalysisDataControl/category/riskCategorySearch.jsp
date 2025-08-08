<!DOCTYPE HTML>

<%-- Used for searching of Risk Categories in MJCW --%>
<%--MODIFICATIONS --%>
<%-- Dwilliamson  11/30/2011	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>  
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../../WEB-INF/msp.tld" prefix="msp"%>
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
<title><bean:message key="title.heading" /> - riskCategorySearch.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript">
function validateSearchFields()
{
	var indx = document.getElementById("searchById").selectedIndex;
	var selId = "";
	if (indx == 0 )  //Category only search
	{	
		if (document.getElementById("categoriesId").selectedIndex == 0 )
		{
			alert("Category Name selection is required.");
			document.getElementById("categoriesId")[0].focus();
			return false;
		}
		selId = document.getElementById("categoriesId").options[document.getElementById("categoriesId").selectedIndex].value; 
	}
	if (indx == 1 )  //formula/Category search
	{
		if (document.getElementById("formulasId").selectedIndex == 0 )
		{
			alert("Formula Name selection is required.");
			document.getElementById("formulasId").focus();
			return false;
		} 
		if (document.getElementById("formulaCategoryId").value == "")
		{
			alert("Category Name selection is required.");
			document.getElementById("formulaCategoryId").focus();
			return false;
		}
		selId = document.getElementById("formulaCategoryId").value
	}	
	document.getElementById("selectedCatId").value = selId;
	return true;
}

function showSearchFields(el)
{
	var fld = "";
	if (el.selectedIndex == 0)
	{
		show("categories", 1);
		show("formulas", 0);
		fld = document.getElementById("categoriesId");
		fld.selectedIndex = 0;
		fld.focus();
	} else {
		show("categories", 0);
		show("formulas", 1);
		fld = document.getElementById("formulasId");
		fld.selectedIndex = 0;
		fld.focus();
		fld = document.getElementById("formulaCategoryId");
		fld.selectedIndex = 0;
		fld.disabled = true;
	}
}
function refreshPage(theForm)
{
	document.getElementById("searchById").selectedIndex=0;
	document.getElementById("selectedCatId").value = "";
}

<!-- FUNCTIONS FOR FILTER CONDITIONS GROUPS  -->
function filterConditonSet()
{
	var group1Id = document.forms[0].formulaId.options[ document.forms[0].formulaId.selectedIndex].value;
	var group2IdSelect = "";
	if (group1Id > "")
	{
		updateGroup2(document.forms[0]);
		if (document.forms[0].group2Id.options.length > 1)
		{
			group2IdSelect = document.forms[0].group2IdSelect.value;
			for (x = 0; x < document.forms[0].group2Id.options.length; x++)
			{
				if (document.forms[0].group2Id.options[x].value == group2IdSelect)
				{
					document.forms[0].group2Id.selectedIndex = x;
					break;
				}
			}
		}
	}
	for (y =0; y < document.forms[0].showFilter.length; y++)
	{
		if (document.forms[0].showFilter[y].value == document.forms[0].showFilterSelected.value)
		{
			document.forms[0].showFilter[y].checked = true;
		}
	}	
}
function subgroup(id, name)
{
	this.id = id;
	this.name = name;
	this.subgroups = new Array();
}

var groups = new Array(); //the array of group1
function updateGroup2(theForm)
{
	var group1Id = theForm.formulaId.options[theForm.formulaId.selectedIndex].value;
	theForm.group2Id.options.length = 0;
	theForm.group2Id.options[0] = new Option( "Please Select", "", false, false );
	if(theForm.formulaId.selectedIndex == 0)
	{
		theForm.group2Id.selectedIndex = 0; //reset choice back to default
		theForm.group2Id.value="";
		theForm.group2Id.disabled = true; //disable group2 choice
		
		return;
	}
	else
	{
		for(i in groups)
		{
			if(groups[i].id == group1Id)
			{
				for(j in groups[i].subgroups)
				{
					//alert(groups[i].subgroups[j].id+":"+groups[i].subgroups[j].name);
					theForm.group2Id.options[theForm.group2Id.options.length] = new Option( groups[i].subgroups[j].name, groups[i].subgroups[j].id);
				}
			}
		}
		if(theForm.group2Id.options.length>1){
			theForm.group2Id.disabled = false;
			theForm.group2Id.value="";
		}
		else{
			theForm.group2Id.selectedIndex = 0; //reset choice back to default
			theForm.group2Id.value="";
			theForm.group2Id.disabled = true;
		}
	}	
}

<logic:iterate indexId="groupIterIndex" id="groupIter" name="riskCategorySearchForm" property="groups">
	groups[<bean:write name="groupIterIndex"/>] = new subgroup("<bean:write name="groupIter" property="groupId" />", "<bean:write name="groupIter" property="name" filter="false" />");
	<logic:iterate indexId="groupIterIndex2" id="groupIter2" name="groupIter" property="subgroups">
		var innerGroup = new subgroup("<bean:write name="groupIter2" property="groupId" />", "<bean:write name="groupIter2" property="name" filter="false" />");
		groups[<bean:write name="groupIterIndex"/>].subgroups[<bean:write name="groupIterIndex2"/>] = innerGroup;
	</logic:iterate>
</logic:iterate>
</script> 

</head>
<body topmargin="0" leftmargin="0" onLoad="refreshPage()" >
<html:form action="/handleRiskCategorySearch" target="content" focus="searchBy">
<input type="hidden" name="selectedValue" value="" id="selectedCatId">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header" ><bean:message key="title.riskTotalCategories"/> - <bean:message key="title.search"/></td>
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
				<li>Select Search By, then select Category Name or Formula Name and Category Name then click submit to view details.</li>
				<li>Click Create Category button to create new category.</li>
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required"><bean:message key="prompt.3.diamond" /> <bean:message key="prompt.requiredFieldsInstruction"/></td>
	</tr>    
</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN SEARCH BY TABLE -->
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr>
			<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond" />&nbsp;<bean:message key="prompt.searchBy"/></td>
			<td class="formDe">
				<select name="searchBy" id="searchById" onchange="showSearchFields(this)" >
					<option value="C">Category Name not Associated to Formula</option>
					<option value="F">Category Name Associated to Formula</option>
				</select>
			</td>
		</tr>
	</table> 
<!-- END SEARCH BY TABLE-->
	<div class="spacer4PX">
<span id="categories" class="visible">
<!-- BEGIN CATEGORY SELECT TABLE -->
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr>
			<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond" />&nbsp;<bean:message key="prompt.categoryName"/></td>
			<td class="formDe">
				<html:select name="riskCategorySearchForm" property="riskCategoryId" styleId="categoriesId">
					<html:option value=""><bean:message key="select.generic" /></html:option>
					<html:optionsCollection property="riskCategories" value="categoryId" label="categoryName" />
				</html:select>
			</td>
		</tr>
	</table> 
<!-- END CATEGORY SELECT TABLE-->	
</span>
<span id="formulas" class="hidden">
<!-- BEGIN FORMULA AND CATEGORY SELECT TABLE -->
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr>
			<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond" />&nbsp;<bean:message key="prompt.formulaName"/></td>
			<td class="formDe">
				<html:select name="riskCategorySearchForm" property="formulaId" styleId="formulasId" onchange="updateGroup2(this.form);">
					<html:option value=""><bean:message key="select.generic" /></html:option>
		 			<html:optionsCollection property="groups" value="groupId" label="name" />
		 		</html:select>
			</td>
		</tr>
		<tr>
			<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond" />&nbsp;<bean:message key="prompt.categoryName"/></td>
			<td class="formDe">
				<html:select property="group2Id" size="1" styleId="formulaCategoryId" disabled="disabled">
					<html:option value=""><bean:message key="select.generic" /></html:option>
					<html:optionsCollection property="group2" value="groupId" label="name" /> 
				</html:select>
			</td>
		</tr>
	</table> 
<!-- END FORMULA AND CATEGORY SELECT TABLE-->	
</span>
<div class="spacer4PX">
  <!-- BEGIN BUTTON TABLE -->
<table border="0" width="100%">
	<tr>
		<td align="center"> 
			<html:submit property="submitAction" onclick="return validateSearchFields() && disableSubmit(this, this.form)"><bean:message key="button.submit" /></html:submit>
			<html:submit property="submitAction" onclick="refreshPage(this.form) && disableSubmit(this,this.form)"><bean:message key="button.refresh"/></html:submit>	
			<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
		</td>
	</tr>
	<tr>
		<td align="center"> 
			<html:submit property="submitAction" onclick="disableSubmit(this, this.form)"><bean:message key="button.createCategory"/></html:submit>
		</td>
	</tr>
</table>
<!-- END BUTTON TABLE -->
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>