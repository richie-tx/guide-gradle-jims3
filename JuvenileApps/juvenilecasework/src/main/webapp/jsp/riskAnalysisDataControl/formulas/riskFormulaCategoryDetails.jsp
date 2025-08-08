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
<title><bean:message key="title.heading" /> - riskFormulaCategoryDetails.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
</head>
<body topmargin="0" leftmargin="0">
<html:form action="/displayRiskFormulaCategoryDetails" target="content">
<span align="center">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" >
				<bean:message key="title.riskFormulas"/> - <bean:message key="prompt.category"/>&nbsp;<bean:message key="prompt.details"/>
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
					<li>Click on hyperlinked Question Name to view Question Details.</li>       
					<li>Select Back button to return to Formula Details.</li>
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
			<td><bean:message key="prompt.category" /></td>
		</tr>
		<tr>
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
<%-- BEGIN QUESTIONS BLOCK TABLE --%>	
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
        	<td class="detailHead">
<%-- BEGIN QUESTION COLAPSE TABLE --%>        	
  				<table width="100%" cellpadding="2" cellspacing="0">
            		<tr>
						<td class="detailHead">
							<a href="javascript:showHideMulti('Question', 'quest', 1, '/<msp:webapp/>')">
			   					<img border='0' src="/<msp:webapp/>images/expand.gif" name="Question"/></a>
							<bean:message key="prompt.questions"/> for <bean:write name="riskFormulaDetailsForm" property="category.categoryName" />
						</td>
 					</tr>
				</table>
<%-- END QUESTION COLAPSE TABLE --%>   				
			</td>
		</tr>
		<tr id="quest0" class='hidden'>
			<td>	
<%-- BEGIN QUESTIONS LIST TABLE --%>
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
					<tr class="formDeLabel">
						<td nowrap="nowrap"><bean:message key="prompt.questionName"/></td>
						<td><bean:message key="prompt.questionText"/></td>
						<td nowrap="nowrap"><bean:message key="prompt.sortOrder"/></td>
						<td nowrap="nowrap"><bean:message key="prompt.UIControlType"/></td>
						<td nowrap="nowrap"><bean:message key="prompt.initialAction"/></td>
						<td><bean:message key="prompt.entryDate"/></td>
					</tr>
			<%-- 		<tr>
						<td>
							<a href="/<msp:webapp/>displayRiskFormulaCategoryQuestionDetails.do?submitAction=View&questionId=88">Question 1</a>
						</td>
						<td>Question 1 Text here</td>
						<td>1</td>
						<td>CHECKBOX</td>
						<td>SHOW</td>
						<td>03/01/2012</td> 
					</tr>  --%>
					<logic:iterate id="qId" name="riskFormulaDetailsForm" property="questionsList" indexId="index1">
						<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
				 			<td>
								<a href="/<msp:webapp/>displayRiskFormulaCategoryQuestionDetails.do?submitAction=View&questionId=<bean:write name='qId' property='questionId'/>"><bean:write name="qId" property="questionName"/></a>
							</td>
							<td><bean:write name="qId" property="questionText"/></td>
							<td><bean:write name="qId" property="uiDisplayOrder"/></td>
							<td><bean:write name="qId" property="uiControlType"/></td>
							<td><bean:write name="qId" property="initialAction"/></td>
							<td><bean:write name="qId" property="questonEntryDate" formatKey="datetime.format.mmddyyyyHHmm"/></td>
						</tr>
					</logic:iterate>
				</table>
<%-- END QUESTIONS LIST TABLE --%>
			</td>
		</tr>
	</table> 
<%-- END QUESTION BLOCK TABLE --%>	
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