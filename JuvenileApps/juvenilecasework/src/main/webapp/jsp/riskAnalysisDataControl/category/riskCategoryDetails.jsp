<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/11/2012 CShimek  	Create JSP --%>
<%-- 05/17/2012	CShimek		Revised Sort Order heading in question listing to UI Display Order per email request. --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
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
<title><bean:message key="title.heading" /> - riskCategoryDetails.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javaScript" >
function validateUpdateSelect()
{
	var sels = document.getElementsByName("selectedValues");
	var selCount = 0;
	if (sels != null)
	{	
		for (x=0; x<sels.length; x++)
		{
			if (sels[x].checked == true)
			{
				selCount++;
			}	
		}
	}
	if (selCount == 0)
	{	
		alert("Question selection is required for update.")
		return false;
	}
	if (selCount > 1)
	{
		alert("Only 1 Question selection allowed for update.")
		return false;		
	}	
	document.getElementById("updateInd").value = "Q";
	return true;

}
function validateRemoveSelect()
{
	var sels = document.getElementsByName("selectedValues");
	var selCount = 0;
	if (sels != null)
	{	
		for (x=0; x<sels.length; x++)
		{
			if (sels[x].checked == true)
			{
				selCount++;
			}	
		}
	}
	if (selCount == 0)
	{	
		alert("Question selection is required for remove.")
		return false;
	}
	if (selCount == sels.length)
	{
		alert("Cannot remove all questions associated to Category.\n Add a new question(s), then remove unwanted questions.")
		return false;		
	}	
	return true;
}
function setUpdateType(el)
{
	document.getElementById("updateInd").value = el;
	return true;
}
</script>

</head>
<body topmargin="0" leftmargin="0">
<html:form action="/handleRiskCategoryDetails" target="content"> 
<input type="hidden" name="helpFile" value="filelocation|00">
<input type="hidden" name="updateType" value="" id="updateInd">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header" ><bean:message key="title.riskTotalCategories"/> - <bean:message key="prompt.category"/>&nbsp;<bean:message key="prompt.details"/></td>
	</tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN Error Message Table -->
<logic:messagesPresent> 
	<table width="100%">
		<tr>		  
			<td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"></html:errors></td>		  
		</tr>   	  
	</table>
  </logic:messagesPresent> 
<!-- END Error Message Table -->
<div class='spacer'></div>
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
        		<li>Click on hyperlink Question Name to view Question Details.</li>
			</ul>
		</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<%-- BEGIN CATEGORY BLOCK TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
        	<td class="detailHead">
  				<table width="100%" cellpadding="2" cellspacing="0">
            		<tr>
						<td class="detailHead"><bean:message key="prompt.category"/></td>
 					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>	
<%-- BEGIN CATEGORY DETAIL TABLE --%>
				<tiles:insert page="riskCategoryInfoTile.jsp" flush="true">
					<tiles:put name="formName" type="String" value="riskCategoryDetailsForm" />
					<tiles:put name="categoryBoxTitle" type="String" value="Category Information" />
				</tiles:insert>
<%-- END CATEGORY TABLE --%>
			</td>
		</tr>
		<tr>
			<td align="center">
				<logic:equal name="riskCategoryDetailsForm" property="category.updatable" value="true">
					<html:submit property="submitAction" onclick="setUpdateType('C') && disableSubmit(this, this.form)"><bean:message key="button.update" /></html:submit>
				</logic:equal>
				<logic:notEqual name="riskCategoryDetailsForm" property="category.formulaStatusCd" value="A">
					<logic:equal name="riskCategoryDetailsForm" property="category.totalCategoriesTiedToFormulaGreaterThan1" value="true">
						<html:submit property="submitAction" onclick="disableSubmit(this, this.form)"><bean:message key="button.delete" /></html:submit>
					</logic:equal>
				</logic:notEqual>
			</td>
		</tr>
	</table>
<%-- END CATEGORY BLOCK TABLE --%>
	<div class="spacer4px" ></div>
<%-- BEGIN QUESTION TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr class="detailHead">
  			<td><bean:message key="prompt.questions"/> for <bean:write name="riskCategoryDetailsForm" property="categoryName"/></td>
		</tr>
		<tr>
			<td>
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
					<logic:empty name="riskCategoryDetailsForm" property="questionsList">
						<tr class="formDe">
							<td>No Questions found for this category.</td>
						</tr>
					</logic:empty>
					<logic:notEmpty name="riskCategoryDetailsForm" property="questionsList">
						<tr class="formDeLabel">
							<td width="1%"></td>
							<td nowrap="nowrap"><bean:message key="prompt.questionName"/></td>
							<td><bean:message key="prompt.questionText"/></td>
							<td nowrap="nowrap"><bean:message key="prompt.UIDisplayOrder"/></td>
							<td nowrap="nowrap"><bean:message key="prompt.UIControlType"/></td>
							<td nowrap="nowrap"><bean:message key="prompt.initialAction"/></td>
							<td><bean:message key="prompt.entryDate"/></td>
						</tr>
						<logic:iterate id="qId" name="riskCategoryDetailsForm" property="questionsList" indexId="index">
							<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
								<td>
									<logic:equal name="riskCategoryDetailsForm" property="category.updatable" value="true">
										<input type="checkBox" name="selectedValues" value="<bean:write name="qId" property="questionId"/>" >
									</logic:equal>
								</td>	
								<td>
									<a href="/<msp:webapp/>handleRiskCategoryDetails.do?submitAction=View&questionId=<bean:write name="qId" property="questionId"/>"><bean:write name="qId" property="questionName"/></a> 
								</td>
								<td><bean:write name="qId" property="questionText"/></td>
								<td><bean:write name="qId" property="uiDisplayOrder"/></td>
								<td><bean:write name="qId" property="uiControlType"/></td>
								<td><bean:write name="qId" property="initialAction"/></td>
								<td><bean:write name="qId" property="questonEntryDate" formatKey="datetime.format.mmddyyyyHHmm"/></td>
							</tr>
						</logic:iterate>
					</logic:notEmpty>	
				</table>
			</td>
		</tr>
		<logic:notEmpty name="riskCategoryDetailsForm" property="questionsList">
			<logic:equal name="riskCategoryDetailsForm" property="category.updatable" value="true">
				<tr>
					<td align="center">
						<html:submit property="submitAction" onclick="return validateUpdateSelect() && disableSubmit(this, this.form)"><bean:message key="button.update" /></html:submit>
						<html:submit property="submitAction" onclick="return validateRemoveSelect() && disableSubmit(this, this.form)"><bean:message key="button.remove" /></html:submit>
					</td>
				</tr>
			</logic:equal>
		</logic:notEmpty>
	</table>				
<%-- END QUESTION TABLE --%>
<div class="spacer4PX"></div>  
<%-- BEGIN BUTTON TABLE --%>
<table border="0" width="100%">
  <tr>
    <td align="center"> 
        <html:submit property="submitAction" onclick="disableSubmit(this, this.form)"><bean:message key="button.back" /></html:submit>
        <html:submit property="submitAction" onclick="disableSubmit(this, this.form)"><bean:message key="button.cancel"/></html:submit>
    </td>
  </tr>
</table>
<%-- END BUTTON TABLE --%>
<div class=spacer></div>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>