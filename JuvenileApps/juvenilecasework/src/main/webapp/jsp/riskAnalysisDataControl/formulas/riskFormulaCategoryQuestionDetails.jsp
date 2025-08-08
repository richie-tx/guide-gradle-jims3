<!DOCTYPE HTML>

<%-- Used for creating Risk Formulas in MJCW --%>
<%--MODIFICATIONS --%>
<%-- CShimek	03/02/2012	Create JSP --%>

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
<title><bean:message key="title.heading" /> - riskFormulaCategoryQuestionDetails.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>

</head>
<body topmargin="0" leftmargin="0">
<html:form action="/displayRiskFormulaCategoryQuestionDetails" target="content">
<span align="center">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" >
				<bean:message key="title.riskFormulas"/> - <bean:write name="riskFormulaDetailsForm" property="question.questionName"/>&nbsp;<bean:message key="prompt.details"/>
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
					<li>Select Back button to return to Category Details.</li>
					<li>Select Cancel button to return to Formula Details.</li>
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
<%-- BEGIN CATEGORY BLOCK TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr class="detailHead">
			<td>
				<a href="javascript:showHideMulti('Category', 'cat', 1, '/<msp:webapp/>')">
				<img border='0' src="/<msp:webapp/>images/expand.gif" name="Category"/></a>
				<bean:message key="prompt.category" />
			</td>
		</tr>
		<tr id="cat0" class='hidden'>
			<td>
<%-- BEGIN CATEGORY DETAILS TABLE --%>	
				<tiles:insert page="../../riskAnalysisDataControl/category/riskCategoryInfoTile.jsp" flush="true">
					<tiles:put name="formName" type="String" value="riskFormulaDetailsForm" />
					<tiles:put name="categoryBoxTitle" type="String" value="Category Information" />
				</tiles:insert>		
<%-- END CATEGORY DETAILS TABLE --%>					
			</td>
		</tr>
	</table>
<%-- END CATEGORY BLOCK TABLE --%>	
	<div class="spacer4px"></div>
<%-- BEGIN QUESTION DETAIL BLOCK TABLE --%>	
	<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr class="detailHead">
			<td><bean:message key="prompt.question" /></td>
		</tr>
		<tr>
			<td>
<%-- BEGIN QUESTION DETAILS TABLE --%>			
				<tiles:insert page="../../riskAnalysisDataControl/category/riskCategoryQuestionInfoTile.jsp" flush="true">
					<tiles:put name="formName" type="String" value="riskFormulaDetailsForm" />
				</tiles:insert>
<%-- END QUESTION DETAILS TABLE --%>
			</td>
		</tr>
	</table>							
<%-- END QUESTION DETAIL BLOCK TABLE --%>	
	<div class="spacer4px"></div>	
<%-- BEGIN ANSWERS BLOCK TABLE --%>	
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
        	<td class="detailHead">
<%-- BEGIN ANSWER HEADING TABLE --%>        	
  				<table width="100%" cellpadding="2" cellspacing="0">
            		<tr>
						<td class="detailHead">
							<bean:message key="prompt.answer"/>s for <bean:write name="riskFormulaDetailsForm" property="question.questionName" />
						</td>
 					</tr>
				</table>
<%-- END ANSWER HEADING TABLE --%>   				
			</td>
		</tr>
		<tr>
			<td>	
<%-- BEGIN ANSWER LIST TABLE --%>
	<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
		<logic:empty name="riskFormulaDetailsForm" property="answerList">
			<tr>
				<td class="formDe" colspan="4">No Answers found for this Question.</td>
			</tr>
		</logic:empty>
		<logic:iterate id="aId" name="riskFormulaDetailsForm" property="answerList">
			<tr>
				<td class="formDeLabel"width="1%" nowrap="nowrap"><bean:message key="prompt.entryDateTime" /></td>
				<td class="formDe" colspan="3"><bean:write name="aId" property="answerEntryDate"/></td>
			</tr>
			<tr>
				<td class="formDeLabel"><bean:message key="prompt.answerText" /></td>
				<td class="formDe" colspan="3"><bean:write name="aId" property="response"/></td>
			</tr>
			<tr class="formDeLabel">
				<td class="formDeLabel"><bean:message key="prompt.weight" /></td>
				<td class="formDe" width="35%"><bean:write name="aId" property="weight"/></td>
				<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.subordinateQuestion"/></td>
				<td class="formDe" width="35%">
					<logic:notEqual name='aId' property='subordinateQuestionName' value="">
						<a href="/<msp:webapp/>displayRiskFormulaCategoryQuestionDetails.do?submitAction=View&questionId=<bean:write name='aId' property='subordinateQuestionId'/>&subordinateQuestion=Y" ><bean:write name="aId" property="subordinateQuestionName"/></a>
					</logic:notEqual>
				</td>
			</tr>	
			<tr class="formDeLabel">	
				<td class="formDeLabel"><bean:message key="prompt.sortOrder"/></td>
				<td class="formDe" width="35%"><bean:write name="aId" property="sortOrder"/></td>
				<td class="formDeLabel"><bean:message key="prompt.action"/></td>
				<td class="formDe" width="35%"><bean:write name="aId" property="action"/></td>
			</tr>
			<tr><td></td></tr>
		</logic:iterate>	
	</table>
<%-- END ANSWER LIST TABLE --%>
			</td>
		</tr>
	</table> 
<%-- END ANSWER BLOCK TABLE --%>
</span>
<div class="spacer4px"></div>
<%-- BEGIN BUTTON TABLE --%>	
<table border="0" width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.back"/></html:submit>
			<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.cancel"/></html:submit>
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>
<div class="spacer4px"></div>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>