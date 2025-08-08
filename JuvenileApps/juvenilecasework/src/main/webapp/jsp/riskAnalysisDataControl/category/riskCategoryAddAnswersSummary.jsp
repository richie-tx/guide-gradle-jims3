<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- CShimek  01/20/2012	Create JSP --%>

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
<title><bean:message key="title.heading" /> - riskCategoryAddAnswersSummary.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
</head>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">

<%--BEGIN FORM TAG--%>
<html:form action="/submitRiskCategoryAddAnswers" target="content"> 
<input type="hidden" name="helpFile" value="">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" >
				<bean:message key="title.riskTotalCategories"/> - <bean:message key="prompt.add"/> <bean:message key="prompt.answer"/>s
				<logic:equal name="riskAnswerCreateForm" property="pageType" value="summary">
					<bean:message key="prompt.summary"/>
				</logic:equal>
				<logic:equal name="riskAnswerCreateForm" property="pageType" value="confirm">
					<bean:message key="prompt.confirmation"/>
				</logic:equal>
			</td>
		</tr>
		<logic:equal name="riskAnswerCreateForm" property="pageType" value="confirm">
			<tr>
		  		<td class=confirm>Answer(s) successfully added to Category.</td>
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
				<logic:equal name="riskAnswerCreateForm" property="pageType" value="summary">
					<ul>
						<li>Select Finish button to save Answer.</li>
						<li>Select Cancel button to return to Category Details page.</li>
					</ul>
				</logic:equal>
				<logic:equal name="riskAnswerCreateForm" property="pageType" value="confirm">
					<ul>
						<li>Select Question Details button to return to Question Details page.</li>
					</ul>
				</logic:equal>	
			</td>
		</tr>
	</table>
<%-- END INSTRUCTION TABLE --%>
<!-- Place formName in request for tiles to use -->	
	<bean:define id="form" value="riskCategoryDetailsForm" toScope="page"/>
	<bean:define id="cqform" value="handleRiskQuestionDetailsSelectionForm" toScope="page"/>	
	<bean:define id="qaform" value="handleRiskQuestionDetailsSelectionForm" toScope="page"/>
<%-- BEGIN CATEGORY BLOCK TABLE --%>	
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
        	<td class="detailHead">
<%-- BEGIN CATEGORY COLAPSE TABLE --%>        	
  				<table width="100%" cellpadding="2" cellspacing="0">
            		<tr>
						<td class="detailHead">
							<a href="javascript:showHideMulti('Category', 'cat', 1, '/<msp:webapp/>')">
			   					<img border='0' src="/<msp:webapp/>images/expand.gif" name="Category"/></a>
							<bean:message key="prompt.category"/>
						</td>
 					</tr>
				</table>
<%-- END CATEGORY COLAPSE TABLE --%>   				
			</td>
		</tr>
		<tr id="cat0" class='hidden'>
			<td>	
<%-- BEGIN CATEGORY DETAIL TABLE --%>
				<tiles:insert page="riskCategoryInfoTile.jsp" flush="true">
					<tiles:put name="formName" type="String" value="${form}" />
					<tiles:put name="categoryBoxTitle" type="String" value="Category Information" />
				</tiles:insert>
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
						<td class="detailHead">
							<a href="javascript:showHideMulti('Question', 'quest', 1, '/<msp:webapp/>')">
			   					<img border='0' src="/<msp:webapp/>images/expand.gif" name="Question"/></a>
							<bean:write name="handleRiskQuestionDetailsSelectionForm" property="question.questionName" /> 
						</td>
 					</tr>
				</table>
<%-- END QUESTION COLAPSE TABLE --%> 			
			</td>
		</tr>
	    <tr id="quest0" class='hidden'>
			<td>
<%-- BEGIN QUESTION DETAILS TABLE --%>			
				<tiles:insert page="riskCategoryQuestionInfoTile.jsp" flush="true">
					<tiles:put name="formName" type="String" value="${cqform}" />
				</tiles:insert>	
<%-- END QUESTION DETAILS TABLE --%>					
			</td>
		</tr>
	</table>
<%-- END QUESTION BLOCK TABLE --%>
    <div class="spacer4px" ></div>
<%-- BEGIN CURRENT ANSWERS BLOCK TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td class="detailHead">
<%-- BEGIN CURRENT ANSWERS COLAPSE TABLE --%>        	
  				<table width="100%" cellpadding="2" cellspacing="0">
            		<tr>
						<td class="detailHead">
							<a href="javascript:showHideMulti('currentQuestions', 'curQuest', 1, '/<msp:webapp/>')">
			   					<img border='0' src="/<msp:webapp/>images/expand.gif" name="currentQuestions"/></a>
							<bean:message key="prompt.answer"/>s for <bean:write name="handleRiskQuestionDetailsSelectionForm" property="question.questionName" /> 
						</td>
 					</tr>
				</table>
<%-- END CURRENT ANSWERS COLAPSE TABLE --%> 			
			</td>
		</tr>
	    <tr id="curQuest0" class='hidden'>
			<td>
<%-- BEGIN CURRENT ANSWERS LIST TABLE --%>			
				<tiles:insert page="riskCategoryQuestionAnswersTile.jsp" flush="true">
					<tiles:put name="formName" type="String" value="${qaform}" />
				</tiles:insert>
<%-- END CURRENT ANSWERS LIST TABLE --%>					
			</td>
		</tr>
	</table>				
<%-- END CURRENT ANSWERS BLOCK TABLE --%>
	<div class="spacer4px" ></div>
<%-- BEGIN ADD ANSWERS BLOCK TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td class="detailHead">
				<logic:equal name="riskAnswerCreateForm" property="pageType" value="summary">
					<bean:message key="prompt.answer"/>s to be added to <bean:write name="handleRiskQuestionDetailsSelectionForm" property="question.questionName"/>
           		</logic:equal>
				<logic:equal name="riskAnswerCreateForm" property="pageType" value="confirm">
					<bean:message key="prompt.answer"/>s added to <bean:write name="handleRiskQuestionDetailsSelectionForm" property="question.questionName"/>
           		</logic:equal>
			</td>
		</tr>
		<tr>
			<td>	
<%-- BEGIN ADD ANSWERS INPUT TABLE --%>
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
				    <logic:iterate id="naid" name="riskAnswerCreateForm" property="newAnswerList">
			         	<tr>
							<td width="15%" class="formDeLabel"><bean:message key="prompt.entryDate"/></td>
							<td colspan="3" class="formDe">
								<bean:write name="naid" property="answerEntryDate" formatKey="datetime.format.mmddyyyyHHmm" />
								<html:hidden name="naid" property="answerEntryDate"/>
							 </td>
						</tr>					
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.answerText"/></td>
							<td class="formDe" colspan="3"><bean:write name="naid" property="answerText" /></td>
						</tr>
			 	 		<tr>					
							<td width="15%" class="formDeLabel"><bean:message key="prompt.weight"/></td>
							<td width="25%" class="formDe"><bean:write name="naid" property="weight" /></td>
							<td width="20%" class="formDeLabel"><bean:message key="prompt.subordinateQuestion" />?</td>
							<td width="40%" class="formDe"><bean:write name="naid" property="subordinateQuestionId" /></td>   
						</tr>	 
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.sortOrder"/></td>
							<td class="formDe"><bean:write name="naid" property="sortOrder" /></td>
							<td class="formDeLabel"><bean:message key="prompt.action"/></td>
							<td class="formDe"><bean:write name="naid" property="action" /></td>
						</tr> 
					</logic:iterate>
				</table>		
<%-- END ADD ANSWERS INPUT TABLE --%>
			</td>
		</tr>
	</table>	
<%-- END ADD ANSWERS BLOCK TABLE --%>	
	<div class="spacer4px" ></div>
<%-- BEGIN BUTTONS TABLE --%>	    
	<table width="100%">
		<tr>
			<td align="center">
				<logic:equal name="riskAnswerCreateForm" property="pageType" value="summary">
					<html:submit property="submitAction" onclick="disableSubmit(this, this.form)"><bean:message key="button.back" /></html:submit>
					<html:submit property="submitAction" onclick="disableSubmit(this, this.form)"><bean:message key="button.finish" /></html:submit>
					<html:submit property="submitAction" onclick="disableSubmit(this, this.form)"><bean:message key="button.cancel"/></html:submit>
				</logic:equal>
				<logic:equal name="riskAnswerCreateForm" property="pageType" value="confirm">
					<html:submit property="submitAction" onclick="disableSubmit(this, this.form)"><bean:message key="button.questionDetails" /></html:submit>
				</logic:equal>	
			</td>
		</tr>
	</table> 
<%-- END BUTONS TABLE --%>	
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>