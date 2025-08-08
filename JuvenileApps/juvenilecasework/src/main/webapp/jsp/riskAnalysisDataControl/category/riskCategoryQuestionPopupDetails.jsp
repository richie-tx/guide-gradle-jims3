<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- CShimek  03/06/2012	Create JSP --%>

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
<title><bean:message key="title.heading" /> - riskCategoryQuestionPopupDetails.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
</head>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">

<%--BEGIN FORM TAG--%>
<html:form action="/handleRiskCategoryQuestionDetails" target="content"> 
<input type="hidden" name="helpFile" value="">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" >
				<bean:message key="title.riskTotalCategories"/> - <bean:message key="prompt.question"/> <bean:message key="prompt.details"/>
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
		<tr>
			<td>
				<ul>
					<li>Select Close button to close this window.</li>
				</ul>
			</td>
		</tr>
	</table>
<%-- END INSTRUCTION TABLE --%>
<!-- Place formName in request for tiles to use -->	
	<bean:define id="cform" value="riskCategoryDetailsForm" toScope="page"/>
	<bean:define id="cqform" value="riskQuestionDetailsPopupForm" toScope="page"/>
<!--  category should not display when page accessed from create category flow -->
	<logic:equal name="riskQuestionDetailsPopupForm" property="showCategory" value="Y">
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
						<tiles:put name="formName" type="String" value="${cform}" />
						<tiles:put name="categoryBoxTitle" type="String" value="Category Information" />
					</tiles:insert>
<%-- END CATEGORY TABLE --%>
				</td>
			</tr>
		</table> 
<%-- END CATEGORY BLOCK TABLE --%>	
		<div class="spacer4px" ></div>
	</logic:equal>
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
				<tiles:insert page="riskCategoryQuestionInfoTile.jsp" flush="true">
					<tiles:put name="formName" type="String" value="${cqform}" />
				</tiles:insert>			
<%-- END QUESTION DETAILS TABLE --%>					
			</td>
		</tr>
	</table>
<%-- END QUESTION BLOCK TABLE --%>
    <div class="spacer4px" ></div>
<%-- BEGIN ANSWER BLOCK TABLE --%>
	<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
		<tr class="detailHead">
  			<td><bean:message key="prompt.answer"/>(s)</td>
		</tr>
		<tr>
			<td>
<%-- BEGIN ANSWER DETAILS TABLE --%>
				<logic:empty  name="riskQuestionDetailsPopupForm" property="answerList">
					<table width="100%" cellpadding="1" cellspacing="2" class="borderTableGrey">
						<tr class="formDe">
							<td>No Answers found for this Question.</td>
						</tr>
					</table>
				</logic:empty>			
				<logic:iterate id="aId" name="riskQuestionDetailsPopupForm" property="answerList" indexId="indexA">
					<table width="100%" cellpadding="1" cellspacing="1" class="borderTableGrey">
						<tr class="alternateRow">
							<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.entryDateTime" /></td>
							<td colspan="3"><bean:write name="aId" property="answerEntryDate" formatKey="datetime.format.mmddyyyyHHmm"/></td>
						</tr>
						<tr class="alternateRow">	
							<td class="formDeLabel"><bean:message key="prompt.answerText" /></td>
							<td colspan="3"><bean:write name="aId" property="response"/></td>
						</tr>
						<tr class="alternateRow">
							<td class="formDeLabel"><bean:message key="prompt.weight" /></td>
							<td width="35%"><bean:write name="aId" property="weight"/></td>
							<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.subordinateQuestion"/></td>
							<td width="35%"><bean:write name="aId" property="subordinateQuestionName"/></td>
						</tr>	
						<tr class="alternateRow">	
							<td class="formDeLabel"><bean:message key="prompt.sortOrder"/></td>
							<td width="35%"><bean:write name="aId" property="sortOrder"/></td>
							<td class="formDeLabel"><bean:message key="prompt.action"/></td>
							<td width="35%"><bean:write name="aId" property="action"/></td>
						</tr>
					</table>
					<div class="spacer"></div>
				</logic:iterate>	
<%-- END ANSWER DETAILS TABLE --%>					
			</td>
		</tr>
	</table>				
<%-- END ANSWER BLOCK TABLE --%>
	<div class="spacer4px" ></div>
<%-- BEGIN BUTTONS TABLE --%>	    
	<table width="100%">
		<tr>
			<td align="center">
				<input type="button" name="closeButton" value="Close" onclick="window.close()">
			</td>
		</tr>
	</table>
<%-- END BUTONS TABLE --%>	
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>