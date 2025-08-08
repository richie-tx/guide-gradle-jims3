<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- CShimek  01/18/2012	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.RiskAnalysisConstants"%>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

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
<title><bean:message key="title.heading" /> - riskCategoryQuestionRemoveSummary.jsp</title>

<%-- STRUTS VALIDATION --%>
<%--html:javascript formName="riskQuestionsSearchForm" /--%>
<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>

</head>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">

<%--BEGIN FORM TAG--%>
<html:form action="/submitRemoveCategoryQuestion" target="content"> 
<input type="hidden" name="helpFile" value="">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" >
				<bean:message key="title.riskTotalCategories"/> - <bean:message key="prompt.remove"/>&nbsp;<bean:message key="prompt.question"/>
				from <bean:message key="prompt.category"/>
				<logic:equal name="riskQuestionDeleteForm" property="pageType" value="summary">
					<bean:message key="prompt.summary"/>
				</logic:equal>
				<logic:equal name="riskQuestionDeleteForm" property="pageType" value="confirm">
					<bean:message key="prompt.confirmation"/>
				</logic:equal>
			 </td>
		</tr>
		<logic:equal name="riskQuestionDeleteForm" property="pageType" value="confirm">
			<tr>
		  		<td class=confirm>Question successfully removed from Category.</td>
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
	<br/>
<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="98%" border="0">
		<tr>
			<td>
				<logic:equal name="riskQuestionDeleteForm" property="pageType" value="summary">
					<ul>
						<li>Select Finish button to remove question from category.</li>
					</ul>
				</logic:equal>
				<logic:equal name="riskQuestionDeleteForm" property="pageType" value="confirm">
					<ul>
						<li>Select Category Details button to return to Category Details page.</li>
					</ul>
				</logic:equal>			
			</td>
		</tr>
	</table>
<%-- END INSTRUCTION TABLE --%>
<!-- Place formName in request for tiles to use -->	
	<bean:define id="form" value="riskCategoryDetailsForm" toScope="page"/>
<%-- BEGIN CATEGORY BLOCK TABLE --%>	
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
        	<td class="detailHead">
<%-- BEGIN CATEGORY BLOCK COLASPE TABLE --%>	        	
  				<table width="100%" cellpadding="2" cellspacing="0">
            		<tr>
						<td class="detailHead">
							<a href="javascript:showHideMulti('Category', 'cat', 1, '/<msp:webapp/>')">
			   					<img border='0' src="/<msp:webapp/>images/expand.gif" name="Category"/></a>
							<bean:message key="prompt.category"/>
						</td>
 					</tr>
				</table>
<%-- END CATEGORY BLOCK COLASPE TABLE --%>					
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
<%-- BEGIN CURRENT QUESTIONS TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td class="detailHead">
				<table width="100%" cellpadding="2" cellspacing="0">
		        	<tr>
		            	<td class="detailHead">
		            		<bean:message key="prompt.questions"/> currently attached to <bean:write name="riskCategoryDetailsForm" property="category.categoryName"/>
		            	</td>
		         	</tr>
		        </table>
	        </td>
	    </tr>
	    <tr>
			<td>
<%-- BEGIN CURRENT QUESTIONS DETAILS TABLE  --%>			
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
					<tr class="formDeLabel">
						<td nowrap="nowrap"><bean:message key="prompt.questionName"/></td>
						<td><bean:message key="prompt.questionText"/></td>
						<td nowrap="nowrap"><bean:message key="prompt.sortOrder"/></td>
						<td nowrap="nowrap"><bean:message key="prompt.UIControlType"/></td>
						<td nowrap="nowrap"><bean:message key="prompt.initialAction"/></td>
						<td><bean:message key="prompt.entryDate"/></td>
					</tr>
					<logic:iterate id="currentQ" name="riskQuestionDeleteForm" property="currentQuestions" indexId="index">
						<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
							<td><bean:write name="currentQ" property="questionName"/></td>
							<td><bean:write name="currentQ" property="questionText"/></td>
							<td><bean:write name="currentQ" property="uiDisplayOrder"/></td>
							<td><bean:write name="currentQ" property="uiControlType"/></td>
							<td><bean:write name="currentQ" property="initialAction"/></td>
							<td><bean:write name="currentQ" property="questonEntryDate" formatKey="datetime.format.mmddyyyyHHmm"/></td>
						</tr>
					</logic:iterate>
				</table>
<%-- END CURRENT QUESTIONS DETAILS TABLE  --%>				
			</td>
		</tr>
	</table>	
<%-- END CURRENT QUESTIONS TABLE --%>	
	<div class="spacer4px" ></div>
<%-- BEGIN REMOVE QUESTIONS TABLE --%>
	<table width="98%" align="center" ALIGNborder="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td class="detailHead">
				<table width="100%" cellpadding="2" cellspacing="0">
		        	<tr>
		            	<td class="detailHead">
			            	<logic:equal name="riskQuestionDeleteForm" property="pageType" value="summary">
			            		<bean:message key="prompt.questions"/> to be removed from <bean:write name="riskCategoryDetailsForm" property="category.categoryName"/>
			            	</logic:equal>	
			            	<logic:equal name="riskQuestionDeleteForm" property="pageType" value="confirm">
			            		<bean:message key="prompt.question"/> removed from <bean:write name="riskCategoryDetailsForm" property="category.categoryName"/>
			            	</logic:equal>	
		            	</td>
		         	</tr>
		        </table>
	        </td>
	    </tr>
	    <tr>
			<td>
<%-- BEGIN REMOVE QUESTIONS DETAILS TABLE  --%>				
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
					<tr class="formDeLabel">
						<td nowrap="nowrap"><bean:message key="prompt.questionName"/></td>
						<td><bean:message key="prompt.questionText"/></td>
						<td nowrap="nowrap"><bean:message key="prompt.sortOrder"/></td>
						<td nowrap="nowrap"><bean:message key="prompt.UIControlType"/></td>
						<td nowrap="nowrap"><bean:message key="prompt.initialAction"/></td>
						<td><bean:message key="prompt.entryDate"/></td>
					</tr>
					<logic:iterate id="removeQ" name="riskQuestionDeleteForm" property="removeQuestions" indexId="index2">
						<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
							<td><bean:write name="removeQ" property="questionName"/></td>
							<td><bean:write name="removeQ" property="questionText"/></td>
							<td><bean:write name="removeQ" property="uiDisplayOrder"/></td>
							<td><bean:write name="removeQ" property="uiControlType"/></td>
							<td><bean:write name="removeQ" property="initialAction"/></td>
							<td><bean:write name="removeQ" property="questonEntryDate" formatKey="datetime.format.mmddyyyyHHmm"/></td>
						</tr>
					</logic:iterate>
				</table>
<%-- END REMOVE QUESTIONS DETAILS TABLE  --%>					
			</td>
		</tr>
	</table>	
<%-- END REMOVE QUESTIONS TABLE --%>			
    <br>
<%-- BEGIN BUTTONS TABLE --%>    
	<table width="100%">
		<tr>
			<td align="center">
				<logic:equal name="riskQuestionDeleteForm" property="pageType" value="summary">
					<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
					<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.finish" /></html:submit>
					<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
				</logic:equal>
				<logic:equal name="riskQuestionDeleteForm" property="pageType" value="confirm">
					<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.categoryDetails" /></html:submit>
				</logic:equal>			
			</td>
		</tr>
	</table> 
<%-- END BUTTONS TABLE --%>  	
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>