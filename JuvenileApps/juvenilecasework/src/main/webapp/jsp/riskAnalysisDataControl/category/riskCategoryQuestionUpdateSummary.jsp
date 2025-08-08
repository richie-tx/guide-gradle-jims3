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
<title><bean:message key="title.heading" /> - riskCategoryQuestionUpdateSummary.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
</head>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">

<%--BEGIN FORM TAG--%>
<html:form action="/submitUpdateCategoryQuestion" target="content"> 
<input type="hidden" name="helpFile" value="">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" >
				<bean:message key="title.riskTotalCategories"/> - <bean:message key="title.update"/>&nbsp;<bean:message key="prompt.question"/>
				<logic:equal name="riskQuestionUpdateForm" property="pageType" value="summary">
					<bean:message key="prompt.summary"/>
				</logic:equal>
				<logic:equal name="riskQuestionUpdateForm" property="pageType" value="confirm">
					<bean:message key="prompt.confirmation"/>
				</logic:equal>
			</td>
		</tr>
		<logic:equal name="riskQuestionUpdateForm" property="pageType" value="confirm">
			<tr>
		  		<td class=confirm>Category Question successfully updated.</td>
	  		</tr>
  		</logic:equal>
	</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN CONFIRMATION MESSAGE TABLE --%>
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="confirm"></td>
		</tr>
	</table>
<%-- BEGIN CONFIRMATION MESSAGE TABLE --%>
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
				<logic:equal name="riskQuestionUpdateForm" property="pageType" value="summary">
					<ul>
						<li>Select Cancel button to return to Category Details page.</li>
					</ul>
				</logic:equal>
				<logic:equal name="riskQuestionUpdateForm" property="pageType" value="confirm">
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
				<table width="100%" cellpadding="2" cellspacing="0">
		        	<tr>
		            	<td class="detailHead"><bean:message key="prompt.question"/></td>
		         	</tr>
		        </table>
	        </td>
	    </tr>
	    <tr>
			<td>
<%-- BEGIN QUESTION DETAILS TABLE --%>			
				<table width="100%" cellpadding="2" cellspacing="1" border="0" class="borderTableGrey">
		        	<tr class="detailHead">
		            	<td colspan="4"><bean:message key="prompt.question"/>&nbsp;<bean:message key="prompt.information"/></td>
		          	</tr>
		          	<tr>
						<td width="15%" class="formDeLabel"><bean:message key="prompt.questionName"/></td>
						<td width="35%" class="formDe"><bean:write name="riskQuestionUpdateForm" property="question.questionName" /></td>
						<td width="15%" class="formDeLabel"><bean:message key="prompt.entryDate"/></td>
						<td width="35%" class="formDe">
							<bean:write name="riskQuestionUpdateForm" property="question.questonEntryDate" formatKey="datetime.format.mmddyyyyHHmm" />
							<html:hidden name="riskQuestionUpdateForm" property="question.questonEntryDate"/>
						 </td>
					</tr>					
					<tr>
						<td class="formDeLabel"><bean:message key="prompt.questionText"/></td>
						<td class="formDe" colspan="3"><bean:write name="riskQuestionUpdateForm" property="question.questionText" /></td>
					</tr>
			 		<tr>					
						<td class="formDeLabel"><bean:message key="prompt.UIControlType" /></td>
						<td class="formDe"><bean:write name="riskQuestionUpdateForm" property="question.uiControlType" /></td>
						<td class="formDeLabel">Default to System Date</td>
						<td class="formDe">
							<logic:equal name="riskQuestionUpdateForm" property="question.defaultToSystemDate" value="false">
								<%=UIConstants.NO_FULL_TEXT%>
							</logic:equal>
							<logic:equal name="riskQuestionUpdateForm" property="question.defaultToSystemDate" value="true">
								<%=UIConstants.YES_FULL_TEXT%>
							</logic:equal>
						</td>	 
						
					</tr>	
					<tr id='collapseHeader' class='hidden' >
						<td class="formDeLabel"><bean:message key="prompt.collapsibleHeader" /></td>
						<td class="formDe" colspan="3">
							<logic:equal name="riskQuestionUpdateForm" property="question.collapsibleHeader" value="false">
								<%=UIConstants.NO_FULL_TEXT%>
							</logic:equal>
							<logic:equal name="riskQuestionUpdateForm" property="question.collapsibleHeader" value="true">
								<%=UIConstants.YES_FULL_TEXT%>
							</logic:equal>
						</td>
					</tr>
		         	<logic:equal name="riskQuestionUpdateForm" property="question.uiControlType" value="QUESTIONHEADER">
						<script type="text/javascript">
							document.getElementById("collapseHeader").className = "visible";
						</script>
					</logic:equal>
					<tr>
						<td class="formDeLabel"><bean:message key="prompt.UIDisplayOrder"/></td>
						<td class="formDe"><bean:write name="riskQuestionUpdateForm" property="question.uiDisplayOrder" /></td>             
						<td class="formDeLabel"><bean:message key="prompt.allowsFutureDates"/></td>
						<td class="formDe">
							<logic:equal name="riskQuestionUpdateForm" property="question.allowFutureDates" value="false">
								<%=UIConstants.NO_FULL_TEXT%>
							</logic:equal>
							<logic:equal name="riskQuestionUpdateForm" property="question.allowFutureDates" value="true">
								<%=UIConstants.YES_FULL_TEXT%>
							</logic:equal>
						</td>	 
					</tr>
					<tr>
						<td class="formDeLabel"><bean:message key="prompt.numericOnly"/></td>
						<td class="formDe">
							<logic:equal name="riskQuestionUpdateForm" property="question.numericOnly" value="false">
								<%=UIConstants.NO_FULL_TEXT%>
							</logic:equal>
							<logic:equal name="riskQuestionUpdateForm" property="question.numericOnly" value="true">
								<%=UIConstants.YES_FULL_TEXT%>
							</logic:equal>
						</td>
						<td class="formDeLabel"><bean:message key="prompt.questionTextNotModifiable"/></td>
						<td class="formDe">
						<logic:equal name="riskQuestionUpdateForm" property="question.hardcoded" value="true">
								<%=UIConstants.YES_FULL_TEXT%>
						</logic:equal>
						</td>
					</tr>
					<tr>
						<td class="formDeLabel"><bean:message key="prompt.initialAction"/></td>
						<td class="formDe"><bean:write name="riskQuestionUpdateForm" property="question.questionInitialAction" /></td>   
						<td class="formDeLabel"><bean:message key="prompt.required?"/> </td>
						<td class="formDe">
							<logic:equal name="riskQuestionUpdateForm" property="question.required" value="false">
								<%=UIConstants.NO_FULL_TEXT%>
							</logic:equal>
							<logic:equal name="riskQuestionUpdateForm" property="question.required" value="true">
								<%=UIConstants.YES_FULL_TEXT%>
							</logic:equal>
						</td>
					</tr>
					<tr>
						<td class="formDeLabel"><bean:message key="prompt.controlCode" /></td>
						<td class="formDe"><bean:write name="riskQuestionUpdateForm" property="question.controlCodeName" /></td>
				<%-- 		<td class="formDeLabel"><bean:message key="prompt.answerSource" /></td>
						<td class="formDe" ><bean:write name="riskQuestionUpdateForm" property="question.answerSource" /></td>  --%>
						
						<td class="formDeLabel">Allow Print</td>
						<td class="formDe">
							<logic:equal name="riskQuestionUpdateForm" property="question.allowPrint" value="false">
								<%=UIConstants.NO_FULL_TEXT%>
							</logic:equal>
							<logic:equal name="riskQuestionUpdateForm" property="question.allowPrint" value="true">
								<%=UIConstants.YES_FULL_TEXT%>
							</logic:equal>
						</td>
					</tr> 
				</table>
<%-- END QUESTION DETAILS TABLE --%>					
			</td>
		</tr>
	</table>
<%-- END QUESTION BLOCK TABLE --%>
    <div class="spacer4px" ></div>
<%-- BEGIN BUTONS TABLE --%>	    
	<table width="100%">
		<tr id="submitButtons">
			<td align="center">
				<logic:equal name="riskQuestionUpdateForm" property="pageType" value="summary">
					<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
					<html:submit property="submitAction"><bean:message key="button.finish" /></html:submit>
					<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
				</logic:equal>
				<logic:equal name="riskQuestionUpdateForm" property="pageType" value="confirm">
					<html:submit property="submitAction"><bean:message key="button.categoryDetails" /></html:submit>
				</logic:equal>			
			</td>
		</tr>
	</table> 
<%-- END BUTONS TABLE --%>	
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>