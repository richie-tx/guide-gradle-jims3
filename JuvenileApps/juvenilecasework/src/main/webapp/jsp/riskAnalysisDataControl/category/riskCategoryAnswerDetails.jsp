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
<title><bean:message key="title.heading" /> - riskCategoryAnswerDetails.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javaScript">
function exitPopup()
{
	var pt = document.getElementById("popUpInd");
	if (pt != null)
	{
		pt.value == "";
	}	
	window.close();
}
</script>
</head>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">

<%--BEGIN FORM TAG--%>
<html:form action="/handleRiskAnswerDetailsSelection" target="content"> 
<input type="hidden" name="helpFile" value="">
<input type="hidden" name="popUpFld" value="<bean:write name="handleRiskQuestionDetailsSelectionForm" property="pageAsPopUp" />" id="popUpInd" >
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" >
				<bean:message key="title.riskTotalCategories"/> - <bean:write name="handleRiskQuestionDetailsSelectionForm" property="question.questionName" /> <bean:message key="prompt.details"/>
			</td>
		</tr>
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
		<tr id="instr1" class="visible">
			<td>
				<ul>
					<li>Select Cancel button to return to Question Details page.</li>
				</ul>
			</td>
		</tr>
		<tr id="instr2" class="hidden">
			<td>
				<ul>
					<li>Select Back button to return to Question Details page.</li>
					<li>Select Close button to return to Update Category page.</li>
				</ul>
			</td>
		</tr>		
	</table>
<%-- END INSTRUCTION TABLE --%>
<span id="catTable" class="visible">
<!-- Place formName in request for tiles to use -->	
	<bean:define id="form" value="riskCategoryDetailsForm" toScope="page"/>
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
</span>	
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
								<bean:message key="prompt.question"/>							
						</td>
 					</tr>
				</table>
<%-- END QUESTION COLAPSE TABLE --%> 			
			</td>
		</tr>
	    <tr id="quest0" class='hidden'>
			<td>
<%-- BEGIN QUESTION DETAILS TABLE --%>			
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
		        	<tr class="detailHead">
		            	<td colspan="4"><bean:message key="prompt.question"/>&nbsp;<bean:message key="prompt.information"/></td>
		          	</tr>
		          	<tr>
						<td width="15%" class="formDeLabel"><bean:message key="prompt.questionName"/></td>
						<td width="35%" class="formDe"><bean:write name="handleRiskQuestionDetailsSelectionForm" property="question.questionName" /></td>
						<td width="15%" class="formDeLabel"><bean:message key="prompt.entryDate"/></td>
						<td width="35%" class="formDe">
							<bean:write name="handleRiskQuestionDetailsSelectionForm" property="question.questonEntryDate" formatKey="datetime.format.mmddyyyyHHmm" />
							<html:hidden name="handleRiskQuestionDetailsSelectionForm" property="question.questonEntryDate"/>
						 </td>
					</tr>					
					<tr>
						<td class="formDeLabel"><bean:message key="prompt.questionText"/></td>
						<td class="formDe" colspan="3"><bean:write name="handleRiskQuestionDetailsSelectionForm" property="question.questionText" /></td>
					</tr>
			 		<tr>					
						<td class="formDeLabel"><bean:message key="prompt.question"/> <bean:message key="prompt.number" /></td>
						<td class="formDe"><bean:write name="handleRiskQuestionDetailsSelectionForm" property="question.questionNumber" /></td>
						<td class="formDeLabel"><bean:message key="prompt.UIControlType" /></td>
						<td class="formDe"><bean:write name="handleRiskQuestionDetailsSelectionForm" property="question.uiControlType" /></td>

					</tr>	
					<tr id='collapseHeader' class='hidden' >
						<td class="formDeLabel"><bean:message key="prompt.collapsibleHeader" /></td>
						<td class="formDe" colspan="3">
							<logic:equal name="handleRiskQuestionDetailsSelectionForm" property="question.collapsibleHeader" value="false">
								<%=UIConstants.NO_FULL_TEXT%>
							</logic:equal>
							<logic:equal name="handleRiskQuestionDetailsSelectionForm" property="question.collapsibleHeader" value="true">
								<%=UIConstants.YES_FULL_TEXT%>
							</logic:equal>
						</td>
					</tr>
		         	<logic:equal name="handleRiskQuestionDetailsSelectionForm" property="question.uiControlType" value="QUESTIONHEADER">
						<script type="text/javascript">
							document.getElementById("collapseHeader").className = "visible";
						</script>
					</logic:equal>
					<tr>
						<td class="formDeLabel"><bean:message key="prompt.UIDisplayOrder"/></td>
						<td class="formDe"><bean:write name="handleRiskQuestionDetailsSelectionForm" property="question.uiDisplayOrder" /></td>             
						<td class="formDeLabel"><bean:message key="prompt.allowsFutureDates"/></td>
						<td class="formDe">
							<logic:equal name="handleRiskQuestionDetailsSelectionForm" property="question.allowFutureDates" value="false">
								<%=UIConstants.NO_FULL_TEXT%>
							</logic:equal>
							<logic:equal name="handleRiskQuestionDetailsSelectionForm" property="question.allowFutureDates" value="true">
								<%=UIConstants.YES_FULL_TEXT%>
							</logic:equal>
						</td>	
					</tr>
					<tr>
						<td class="formDeLabel"><bean:message key="prompt.numericOnly"/></td>
						<td class="formDe">
							<logic:equal name="handleRiskQuestionDetailsSelectionForm" property="question.numericOnly" value="false">
								<%=UIConstants.NO_FULL_TEXT%>
							</logic:equal>
							<logic:equal name="handleRiskQuestionDetailsSelectionForm" property="question.numericOnly" value="true">
								<%=UIConstants.YES_FULL_TEXT%>
							</logic:equal>
						</td>
						<td class="formDeLabel"><bean:message key="prompt.questionTextNotModifiable"/></td>
						<td class="formDe">
							<logic:equal name="handleRiskQuestionDetailsSelectionForm" property="question.hardcoded" value="false">
								<%=UIConstants.NO_FULL_TEXT%>
							</logic:equal>
							<logic:equal name="handleRiskQuestionDetailsSelectionForm" property="question.hardcoded" value="true">
								<%=UIConstants.YES_FULL_TEXT%>
							</logic:equal>
						</td>
					</tr>
					<tr>
						<td class="formDeLabel"><bean:message key="prompt.initialAction"/></td>
						<td class="formDe"><bean:write name="handleRiskQuestionDetailsSelectionForm" property="question.questionInitialAction" /></td>   
						<td class="formDeLabel"><bean:message key="prompt.required?"/></td>
						<td class="formDe">
							<logic:equal name="handleRiskQuestionDetailsSelectionForm" property="question.required" value="false">
								<%=UIConstants.NO_FULL_TEXT%>
							</logic:equal>
							<logic:equal name="handleRiskQuestionDetailsSelectionForm" property="question.required" value="true">
								<%=UIConstants.YES_FULL_TEXT%>
							</logic:equal>
						</td>
					</tr>
					<tr>
						<td class="formDeLabel"><bean:message key="prompt.controlCode" /></td>
						<td class="formDe" colspan="3"><bean:write name="handleRiskQuestionDetailsSelectionForm" property="question.controlCode" /></td>
					</tr> 
				</table>
<%-- END QUESTION DETAILS TABLE --%>					
			</td>
		</tr>
	</table>
<%-- END QUESTION BLOCK TABLE --%>
    <div class="spacer4px" ></div>
<%-- BEGIN ANSWER DETAILS BLOCK TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr>
			<td class="detailHead"><bean:message key="prompt.answer"/></td>
		</tr>
		<tr>
			<td>	
<%-- BEGIN ANSWERS DETAIL TABLE --%>
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
		        	<tr class="detailHead">
		            	<td colspan="4"><bean:message key="prompt.answer"/>&nbsp;<bean:message key="prompt.information"/></td>
		          	</tr>
		       		<tr>
						<td width="15%" class="formDeLabel"><bean:message key="prompt.entryDate"/></td>
						<td colspan="3" class="formDe">
							<bean:write name="handleRiskAnswerDetailsSelectionForm" property="question.questonEntryDate" formatKey="datetime.format.mmddyyyyHHmm" />
							<html:hidden name="handleRiskAnswerDetailsSelectionForm" property="question.questonEntryDate"/>
						 </td>
					</tr>					
			 		<tr>
						<td class="formDeLabel"><bean:message key="prompt.answerText"/></td>
						<td class="formDe" colspan="3"><bean:write name="handleRiskAnswerDetailsSelectionForm" property="question.questionText" /></td>
					</tr>
		 	  		<tr>					
						<td width="15%" class="formDeLabel"><bean:message key="prompt.weight"/></td>
						<td width="35%" class="formDe"><bean:write name="handleRiskAnswerDetailsSelectionForm" property="currentAnswer.weight" /></td>
						<td width="20%" class="formDeLabel"><bean:message key="prompt.subordinateQuestion" />?</td>
						<td width="30%" class="formDe"><bean:write name="handleRiskAnswerDetailsSelectionForm" property="currentAnswer.subordinateQuestionId" /></td>     
					</tr>	
			 		<tr>
						<td class="formDeLabel"><bean:message key="prompt.sortOrder"/></td>
						<td class="formDe"><bean:write name="handleRiskAnswerDetailsSelectionForm" property="currentAnswer.sortOrder" /></td>
						<td class="formDeLabel"><bean:message key="prompt.action"/></td>
						<td class="formDe"><bean:write name="handleRiskAnswerDetailsSelectionForm" property="currentAnswer.action" /></td> 
					</tr> 
				</table>
<%-- END UPDATE ANSWER TABLE --%>  
			</td>
		</tr>
		<tr id="processButtons" class="visible">
			<td colspan="4" align="center">
				<html:submit property="submitAction"><bean:message key="button.update" /></html:submit>
				<html:submit property="submitAction"><bean:message key="button.remove"/></html:submit>
			</td>
		</tr>
	</table>	
<%-- END UPDATE ANSWERS BLOCK TABLE --%>
	<div class="spacer4px" ></div>	
<%-- BEGIN BUTTONS TABLE --%>	    
	<table width="100%">
		<tr id="flowButtons" class="visible">
			<td align="center">
				<html:submit property="submitAction" onclick="disableSubmit(this, this.form)"><bean:message key="button.back" /></html:submit>
				<html:submit property="submitAction" onclick="disableSubmit(this, this.form)"><bean:message key="button.cancel"/></html:submit>
			</td>
		</tr>
		<tr id="popUpButton" class="hidden">
			<td align="center">
				<input type="button" name="backButton" value="Back" onclick="history.go(-1)">			
				<input type="button" name="closeButton" value="Close" onclick="exitPopup()">
			</td>
		</tr>
	</table> 
<%-- END BUTONS TABLE --%>
<script type="text/javaScript">
	var pt = document.getElementById("popUpInd");
	if (pt != null && pt.value == "Y")
	{
		showHide("instr1",0);
		showHide("catTable",0);
		showHide("flowButtons", 0);
		showHide("processButtons", 0);
		showHide("instr2",1);
		showHide("popUpButton", 1);
	}
</script>	
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>