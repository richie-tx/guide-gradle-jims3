<!DOCTYPE HTML>

<%-- Used for creating Risk Formulas in MJCW --%>
<%--MODIFICATIONS --%>
<%-- CShimek	02/29/2012	Create JSP --%>

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
<title><bean:message key="title.heading" /> - riskFormulaRecommendationRemoveSummary.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>

<body topmargin="0" leftmargin="0">
<html:form action="/submitRiskFormulaRecommendationRemove" target="content">
<span align="center">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" >
				<bean:message key="title.riskFormulas"/> - <bean:message key="prompt.remove"/>&nbsp;<bean:message key="prompt.recommendation"/>
				<logic:equal name="riskFormulaRecommendationForm" property="pageType" value="summary">
					<bean:message key="prompt.summary" />
				</logic:equal>
				<logic:equal name="riskFormulaRecommendationForm" property="pageType" value="confirm">
					<bean:message key="prompt.confirmation" />
				</logic:equal>		
			</td>
		</tr>
	</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN MESSAGES TABLE --%>
	<table width="98%" border="0" align="center">
		<logic:equal name="riskFormulaRecommendationForm" property="pageType" value="confirm">
			<tr>
				<td align="center" class="confirm"><bean:write name="riskFormulaRecommendationForm" property="confirmMessage" /></td>
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
				<logic:equal name="riskFormulaRecommendationForm" property="pageType" value="summary">
					<ul>
						<li>Select Finish button to remove recommendation.</li>
						<li>Select Back button to return to Formula Details page.</li>       
					</ul>
				</logic:equal>
				<logic:equal name="riskFormulaRecommendationForm" property="pageType" value="confirm">
					<ul>
						<li>Select Formula Details button to return to Formula Details.</li>
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
<%-- BEGIN FORMULA BLOCK HEADING TABLE --%>        	
  				<table width="100%" cellpadding="2" cellspacing="0">
            		<tr>
						<td class="detailHead"><bean:message key="prompt.formula"/></td>
 					</tr>
				</table>
<%-- END FORMULA BLOCK HEADING TABLE --%>   				
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
<%-- BEGIN RECOMMNEDATION ATTACHED BLOCK TABLE --%>	
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
        	<td class="detailHead">	
<%-- BEGIN RECOMMENDATION ATTACHED HEADING TABLE --%>	
				<table width="100%" cellpadding="2" cellspacing="1" border="0">
					<tr class="detailHead">
						<td><bean:message key="prompt.recommendation"/>s Currently Attached to Formula</td>
					</tr>
				</table>
<%-- END RECOMMENDATION ATTACHED HEADING TABLE --%>						
			</td>
		</tr>
		<tr>
			<td>	
<%-- BEGIN RECOMMENDATION ATTACHED DETAILS TABLE --%>	
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
					<tr class="formDeLabel">
						<td><bean:message key="prompt.recommendationName"/></td>
						<td><bean:message key="prompt.formulaName"/></td>
						<td><bean:message key="prompt.minScore"/></td>
						<td><bean:message key="prompt.maxScore"/></td>
						<td><bean:message key="prompt.recommendation"/></td>
						<td><bean:message key="prompt.custody"/></td>
						<td><bean:message key="prompt.riskResultGroup"/></td>
					</tr>
					<logic:iterate id="raId" name="riskFormulaRecommendationForm" property="currentList" indexId="index1">
						<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
				 			<td><bean:write name="raId" property="recommendationName"/></td>
							<td><bean:write name="raId" property="assessmentTypeName"/></td>
							<td><bean:write name="raId" property="minScore"/></td>
							<td><bean:write name="raId" property="maxScore"/></td>
							<td><bean:write name="raId" property="recommendationDesc"/></td>
							<td>
								<logic:equal name="raId" property="custody" value="true">
									<%=UIConstants.YES_FULL_TEXT%>
								</logic:equal>	
								<logic:equal name="raId" property="custody" value="false">
									<%=UIConstants.NO_FULL_TEXT%>
								</logic:equal>	
							</td>
							<td><bean:write name="raId" property="resultGroupDisplayDesc"/></td> 
						</tr>
					</logic:iterate>
				</table>
<%-- END RECOMMENDATION ATTACHED DETAILS TABLE --%>
			</td>
		</tr>
	</table>		
<%-- END RECOMMNEDATION ATTACHED BLOCK TABLE --%>	
	<div class="spacer4px"></div>
<%-- BEGIN RECOMMENDATION TO BE REMOVED BLOCK TABLE --%>	
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
        	<td class="detailHead">	
<%-- BEGIN RECOMMENDATION TO BE REMOVED HEADING TABLE --%>	
				<table width="100%" cellpadding="2" cellspacing="1" border="0">
					<tr class="detailHead">
						<td><bean:message key="prompt.recommendation"/> to be Removed from Formula</td>
					</tr>
				</table>
<%-- END RECOMMENDATION TO BE REMOVED HEADING TABLE --%>						
			</td>
		</tr>
		<tr>
			<td>	
<%-- BEGIN RECOMMENDATION TO BE REMOVED DETAILS TABLE --%>	
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
					<tr class="formDeLabel">
						<td><bean:message key="prompt.recommendationName"/></td>
						<td><bean:message key="prompt.formulaName"/></td>
						<td><bean:message key="prompt.minScore"/></td>
						<td><bean:message key="prompt.maxScore"/></td>
						<td><bean:message key="prompt.recommendation"/></td>
						<td><bean:message key="prompt.custody"/></td>
						<td><bean:message key="prompt.riskResultGroup"/></td>
					</tr>
					<logic:iterate id="raId" name="riskFormulaRecommendationForm" property="removeList" indexId="index1">
						<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
				 			<td><bean:write name="raId" property="recommendationName"/></td>
							<td><bean:write name="raId" property="assessmentTypeName"/></td>
							<td><bean:write name="raId" property="minScore"/></td>
							<td><bean:write name="raId" property="maxScore"/></td>
							<td><bean:write name="raId" property="recommendationDesc"/></td>
							<td>
								<logic:equal name="raId" property="custody" value="true">
									<%=UIConstants.YES_FULL_TEXT%>
								</logic:equal>	
								<logic:equal name="raId" property="custody" value="false">
									<%=UIConstants.NO_FULL_TEXT%>
								</logic:equal>	
							</td>
							<td><bean:write name="raId" property="resultGroupDisplayDesc"/></td> 
						</tr>
					</logic:iterate>
				</table>
<%-- END RECOMMENDATION TO BE REMOVED DETAILS TABLE --%>
			</td>
		</tr>
	</table>		
<%-- END RECOMMENDATION TO BE REMOVED BLOCK TABLE --%>						
</span>
<div class="spacer4px"></div>
<%-- BEGIN BUTTON TABLE --%>
<table border="0" width="100%">
	<tr>
		<td align="center">
			<logic:equal name="riskFormulaRecommendationForm" property="pageType" value="summary">
				<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.back"/></html:submit>
				<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.finish" /></html:submit>
				<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.cancel"/></html:submit>
			</logic:equal>
			<logic:equal name="riskFormulaRecommendationForm" property="pageType" value="confirm">
				<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.formulaDetails"/></html:submit>
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