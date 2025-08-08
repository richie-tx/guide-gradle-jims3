<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- CShimek  01/23/2012	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
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
<title><bean:message key="title.heading" /> - riskCategoryUpdateSummary.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
</head>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">

<%--BEGIN FORM TAG--%>
<html:form action="/submitRiskCategoryUpdate" target="content"> 
<input type="hidden" name="helpFile" value="">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" >
				<bean:message key="title.riskTotalCategories"/> - <bean:message key="prompt.update"/> <bean:message key="prompt.category"/>
				<logic:equal name="riskCategoryUpdateForm" property="pageType" value="summary">
					<bean:message key="prompt.summary"/>
				</logic:equal>
				<logic:equal name="riskCategoryUpdateForm" property="pageType" value="confirm">
					<bean:message key="prompt.confirmation"/>
				</logic:equal>
			</td>
		</tr>
		<logic:equal name="riskCategoryUpdateForm" property="pageType" value="confirm">
			<tr>
		  		<td class=confirm>Category successfully updated.</td>
	  		</tr>
	 	</logic:equal>
	</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN ERROR TABLE --%>
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
<%-- END ERROR TABLE --%>
	<div class="spacer4px" ></div>
<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="98%" border="0">
		<tr>
			<td>
				<logic:equal name="riskCategoryUpdateForm" property="pageType" value="summary">
					<ul>
						<li>Select Finish button to update Category and Question.</li>
					</ul>
				</logic:equal>
				<logic:equal name="riskCategoryUpdateForm" property="pageType" value="confirm">
					<ul>
						<li>Select Category Details button to return to Category Details.</li>
					</ul>
				</logic:equal>	
			</td>
		</tr>
	</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN CATEGORY BLOCK TABLE --%>	
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
        	<td class="detailHead">
<%-- BEGIN CATEGORY COLAPSE TABLE --%>        	
  				<table width="100%" cellpadding="2" cellspacing="0">
            		<tr>
						<td class="detailHead"><bean:message key="prompt.category"/></td>
 					</tr>
				</table>
<%-- END CATEGORY COLAPSE TABLE --%>   				
			</td>
		</tr>
		<tr>
			<td>	
<%-- BEGIN CATEGORY DETAIL TABLE --%>
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
					<tr class="detailHead">
						<td  colspan="4"><bean:message key="prompt.category" /> <bean:message key="prompt.info" />
					</tr>
					<tr>
						<td width="15%" class="formDeLabel"><bean:message key="prompt.categoryName"/></td>
						<td width="35%" class="formDe">
							<bean:write name="riskCategoryUpdateForm" property="category.categoryName"/>
						</td>
						<td width="15%" class="formDeLabel"><bean:message key="prompt.entryDate"/></td>
						<td width="35%" class="formDe">
							<bean:write name="riskCategoryUpdateForm" property="category.entryDate" formatKey="datetime.format.mmddyyyy" />
						</td>
					</tr>					
					<tr>
						<td class="formDeLabel"><bean:message key="prompt.riskResultGroup"/></td>
						<td class="formDe">
							<bean:write name="riskCategoryUpdateForm" property="category.riskResultGroup"/>
						</td>
						<td class="formDeLabel"><bean:message key="prompt.version"/></td>
						<td class="formDe">
							<bean:write name="riskCategoryUpdateForm" property="category.version" />
						</td>
					</tr>					
					<tr>
						<td class="formDeLabel"><bean:message key="prompt.description"/></td>
						<td class="formDe" colspan="3"><bean:write name="riskCategoryUpdateForm" property="category.categoryDescription" /></td>
					</tr>
				</table>	
<%-- END CATEGORY TABLE --%>
			</td>
		</tr>
	</table> 
<%-- END CATEGORY BLOCK TABLE --%>	
	<div class="spacer4px" ></div>
<%-- BEGIN QUESTION BLOCK TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td class="detailHead">
<%-- BEGIN QUESTION COLAPSE TABLE --%>        	
  				<table width="100%" cellpadding="2" cellspacing="0">
            		<tr>
						<td class="detailHead"><bean:message key="prompt.questions"/></td>
 					</tr>
				</table>
<%-- END QUESTION COLAPSE TABLE --%> 			
			</td>
		</tr>
	    <tr>
			<td>
<%-- BEGIN QUESTIONS LIST TABLE --%>			
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
		        	<tr class="detailHead">
		            	<td colspan="5"><bean:message key="prompt.selectedList"/></td>
		          	</tr>
		          	<tr>
						<td class="formDeLabel" width="28%"><bean:message key="prompt.questionName"/></td>
						<td class="formDeLabel" width="35%"><bean:message key="prompt.questionText"/></td>
						<td class="formDeLabel" width="10%"><bean:message key="prompt.sortOrder"/></td>
						<td class="formDeLabel" width="15%"><bean:message key="prompt.UIControlType"/></td>
						<td class="formDeLabel" width="12%"><bean:message key="prompt.initialAction"/></td>
					</tr>
					<logic:iterate id="qid" name="riskCategoryUpdateForm" property="questionList" indexId="index">					
						<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
							<td><bean:write name="qid" property="questionName" /></td>
							<td><bean:write name="qid" property="questionText" /></td>
							<td><bean:write name="qid" property="uiDisplayOrder" /></td>
							<td><bean:write name="qid" property="uiControlType" /></td>
							<td><bean:write name="qid" property="initialAction" /></td>
						</tr>
					</logic:iterate>	
				</table>
<%-- END QUESTIONS LIST TABLE --%>					
			</td>
		</tr>
	</table>
<%-- END QUESTION BLOCK TABLE --%>
    <div class="spacer4px" ></div>
<%-- BEGIN BUTTONS TABLE --%>	    
	<table width="100%">
		<tr>
			<td align="center">
				<logic:equal name="riskCategoryUpdateForm" property="pageType" value="summary">
					<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.back" /></html:submit>
					<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.finish" /></html:submit>
					<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.cancel"/></html:submit>
				</logic:equal>
				<logic:equal name="riskCategoryUpdateForm" property="pageType" value="confirm">
					<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.categoryDetails" /></html:submit>
				</logic:equal>	
			</td>
		</tr>
	</table> 
<%-- END BUTONS TABLE --%>	
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>