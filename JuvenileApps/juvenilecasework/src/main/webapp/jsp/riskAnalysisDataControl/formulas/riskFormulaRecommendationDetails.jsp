<!DOCTYPE HTML>

<%-- Used for creating Risk Formulas in MJCW --%>
<%--MODIFICATIONS --%>
<%-- CShimek	02/28/2012	Create JSP --%>

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
<title><bean:message key="title.heading" /> - riskFormulaRecommendationDetails.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
</head>

<body topmargin="0" leftmargin="0">
<html:form action="/handleRiskFormulaRecommendationDetailsSelection" target="content">
<html:hidden name="riskFormulaRecommendationForm" property="fromPage" value="RecommendationDetails" />
<span align="center">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" >
				<bean:message key="title.riskFormulas"/> - <bean:message key="prompt.recommendation"/>&nbsp;<bean:message key="prompt.details"/>
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
					<li>Select Back button to return to Formula Details.</li>       
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
<%-- BEGIN RECOMMENDATION TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr class="detailHead">
			<td><bean:message key="prompt.recommendation"/></td>
		</tr>
		<tr>
			<td>
<%-- BEGIN RECOMMENDATION DETAIL TABLE --%>	
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
					<tr class="detailHead">
						<td colspan="4"><bean:message key="prompt.recommendation"/> <bean:message key="prompt.info"/></td>
					</tr>
					<tr>
						<td class="formDeLabel" width="15%" ><bean:message key="prompt.formulaName"/></td>
						<td class="formDe" width="30%">
							<bean:write name="riskFormulaDetailsForm" property="formulaName" />
							<input type="hidden" name="formulaName" value="<bean:write name='riskFormulaRecommendationForm' property='formulaName'/>" > 
						</td>
						<td class="formDeLabel" width="18%"><bean:message key="prompt.entryDate"/></td>
						<td class="formDe" width="37%"><bean:write name="riskFormulaRecommendationForm" property="entryDate" formatKey="datetime.format.mmddyyyy" /></td>
					</tr>
					<tr>
						<td class="formDeLabel" colspan="4"><bean:message key="prompt.recommendationName"/></td>
					</tr>
			 		<tr>
						<td class="formDe" colspan="4"><bean:write name="riskFormulaRecommendationForm" property="recommendationName" /></td>
					</tr>
					<tr>
						<td class="formDeLabel" colspan="4"><bean:message key="prompt.recommendationText"/></td>
					</tr>
					<tr>
						<td class="formDe" colspan="4"><bean:write name="riskFormulaRecommendationForm" property="recommendationText" /></td>
					</tr>
			 		<tr>
						<td class="formDeLabel"><bean:message key="prompt.minScore"/></td>
						<td class="formDe" ><bean:write name="riskFormulaRecommendationForm" property="minScore"/></td>
						<td class="formDeLabel"><bean:message key="prompt.isInCustody"/></td>
						<td class="formDe">
							<logic:equal name="riskFormulaRecommendationForm" property="inCustodyId" value="true">
								<%=UIConstants.YES_FULL_TEXT%>
							</logic:equal>
							<logic:equal name="riskFormulaRecommendationForm" property="inCustodyId" value="false">
								<%=UIConstants.NO_FULL_TEXT%>
							</logic:equal> 	
						</td> 	
					</tr>   
					<tr>
						<td class="formDeLabel"><bean:message key="prompt.maxScore"/></td>
						<td class="formDe" ><bean:write name="riskFormulaRecommendationForm" property="maxScore" /></td>
						<td class="formDeLabel"><bean:message key="prompt.riskResultGroup"/></td>
						<td class="formDe"><bean:write name="riskFormulaRecommendationForm" property="riskResultGroupDesc" /></td>		
					</tr> 
				</table>
<%-- END RECOMMENDATION DETAIL TABLE --%>					
			</td>
		</tr>
		<logic:equal name="riskFormulaRecommendationForm" property="recommendationUpdatable" value="true">
			<tr>
				<td align="center">
					<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.update" /></html:submit>
					<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.remove"/></html:submit>
				</td>
			</tr>
		</logic:equal>
	</table>
<%-- END RECOMMENDATIONS TABLE --%>
</span>
<div class="spacer4px"></div>
<table border="0" width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.back"/></html:submit>
			<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.cancel"/></html:submit>
		</td>
	</tr>
</table>
<div class="spacer4px"></div>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>