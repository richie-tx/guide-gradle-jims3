<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- CShimek  	01/11/2012	Create JSP --%>

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
<title><bean:message key="title.heading" /> - riskCategoryDeleteSummary.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>   
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>

</head>
<body topmargin="0" leftmargin="0">
<html:form action="/submitRiskCategoryDelete" target="content"> 
<input type="hidden" name="helpFile" value="filelocation|00">
<input type="hidden" name="updateType" value="" id="updateInd">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header" >
			<bean:message key="title.riskTotalCategories"/> - <bean:message key="prompt.delete"/>&nbsp;<bean:message key="prompt.category"/>
			<logic:equal name="riskCategoryDeleteForm" property="pageType" value="summary">
				<bean:message key="prompt.summary"/>
			</logic:equal>
			<logic:equal name="riskCategoryDeleteForm" property="pageType" value="confirm">
				<bean:message key="prompt.confirmation"/>
			</logic:equal>
		</td>
	</tr>
	<logic:equal name="riskCategoryDeleteForm" property="pageType" value="confirm">
		<tr>
	  		<td class=confirm>Category deleted and link to Questions has been removed.</td>
  		</tr>
 	</logic:equal>
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
			<logic:equal name="riskCategoryDeleteForm" property="pageType" value="summary">
				<ul>
					<li>Select Finish button to delete category.</li>
				</ul>
			</logic:equal>
			<logic:equal name="riskCategoryDeleteForm" property="pageType" value="confirm">
				<ul>
					<li>Select Category Search button to continue.</li>
				</ul>
			</logic:equal>	
		</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<!-- Place formName in request for tiles to use -->	
<bean:define id="form" value="riskCategoryDetailsForm" toScope="page"/>
  
<%-- START Titles  --%>
  <bean:define id="categoryTitle" toScope="page">
  	<bean:message key="prompt.category"/>
  </bean:define>
<%-- END Titles --%>
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
					<tiles:put name="formName" type="String" value="${form}" />
					<tiles:put name="categoryBoxTitle" type="String" value="Category to be Deleted" />
				</tiles:insert>
<%-- END CATEGORY DETAIL TABLE --%>
			</td>
		</tr>
	</table>
<%-- END CATEGORY BLOCK TABLE --%>	
	<div class="spacer4px" ></div>
<%-- BEGIN QUESTION BLOCK TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr class="detailHead">
  			<td>Associated <bean:message key="prompt.questions"/></td>
		</tr>
		<tr>
			<td>
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
					<logic:empty name="riskCategoryDeleteForm" property="questionsList">
						<tr class="formDe">
							<td>No Questions found for this category.</td>
						</tr>
					</logic:empty>
					<logic:notEmpty name="riskCategoryDeleteForm" property="questionsList">				
						<tr class="formDeLabel">
							<td class="formDeLabel" width="28%"><bean:message key="prompt.questionName"/></td>
							<td class="formDeLabel" width="35%"><bean:message key="prompt.questionText"/></td>
							<td class="formDeLabel" width="10%"><bean:message key="prompt.sortOrder"/></td>
							<td class="formDeLabel" width="15%"><bean:message key="prompt.UIControlType"/></td>
							<td class="formDeLabel" width="12%"><bean:message key="prompt.initialAction"/></td>
						</tr>
						<logic:iterate id="qid" name="riskCategoryDeleteForm" property="questionsList" indexId="index">
							<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
								<td><bean:write name="qid" property="questionName"/></td>
								<td><bean:write name="qid" property="questionText"/></td>
								<td><bean:write name="qid" property="uiDisplayOrder" /></td>
								<td><bean:write name="qid" property="uiControlType" /></td>
								<td><bean:write name="qid" property="initialAction" /></td>
							</tr>
						</logic:iterate>
					</logic:notEmpty>	
				</table>
			</td>
		</tr>
	</table>				
<%-- END QUESTION BLOCK TABLE --%>
<div class="spacer4px"></div>  
<%-- BEGIN BUTTON TABLE --%>
<table border="0" width="100%">
  <tr>
    <td align="center"> 
		<logic:equal name="riskCategoryDeleteForm" property="pageType" value="summary">
			<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.back" /></html:submit>
			<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.finish" /></html:submit>
			<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.cancel"/></html:submit>
		</logic:equal>
		<logic:equal name="riskCategoryDeleteForm" property="pageType" value="confirm">
			<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.categorySearch" /></html:submit>
		</logic:equal>	
    </td>
  </tr>
</table>
<%-- END BUTTON TABLE --%>
<div class=spacer></div>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>