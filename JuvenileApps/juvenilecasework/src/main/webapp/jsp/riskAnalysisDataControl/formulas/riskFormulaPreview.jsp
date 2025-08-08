<!DOCTYPE HTML>
<%-- Used for creating Risk Formulas in MJCW --%>
<%--MODIFICATIONS --%>
<%-- CShimek	02/24/2012	Create JSP --%>
<%-- 05/01/2012		C Shimek	#73346 Revise hardcoded TYC prompts to TJJD --%>
<%-- 08/31/2015	 	RCarter	   #29426 html 5 compliance effort and jquery 5 (when required) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.RiskAnalysisConstants" %>


<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<!--JQUERY FRAMEWORK-->
<%@include file="../../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/facility.js"></script>

<html:base />
<title><bean:message key="title.heading" /> - riskFormulaPreview.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type='text/javascript' src="/JuvenileCasework/js/riskAnalysis.js"></script> 
<script type="text/javascript">
$(function() { 
	$("input[id*='formElementDate']").each(function(){	 
		  datePickerSingle($(this),'Entered Date',false);	      
	});
 });

cal1 = new CalendarPopup();
cal1.showYearNavigation();
function showAlert()
{
	alert("Sorry, this link not active on this page.");
}
</script>
</head>

<body topmargin="0" leftmargin="0">
<html:form action="/displayRiskFormulaPreview" target="content">

<span align="center">
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header" ><bean:message key="title.riskFormulas"/> - <bean:message key="prompt.formula"/> Preview</td>
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
	<div class="spacer4px"></div>	
<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="98%" border="0">
		<tr>
			<td>
				<ul>
					<li>Select Back button to return to Risk Formulas - Search Results page.</li>
				</ul>
			</td>
		</tr>
	</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN DUMMY CASEFILE INFO TABLE --%>
	<table width='98%' cellpadding='1' cellspacing='0' border='0' >
		<tr>
			<td bgcolor='#cccccc'>
				<table width='100%' border='0' cellpadding='2' cellspacing='1'>
		            <tr class='bodyText'>
						<td class='formDeLabel' colspan='2' nowrap><bean:message key="prompt.casefile"/> <bean:message key="prompt.info"/>&nbsp;&nbsp;<a href="#" onclick="showAlert()">View Briefing</a></td></td>
	              		<td class='formDeLabel' colspan='4' nowrap align="right">
							<a href="#" onclick="showAlert()">Juvenile Profile</a>&nbsp;&nbsp;
							<a href='#' onclick="showAlert()">View Case Info</a>&nbsp;&nbsp;
							<a href="#" onclick="showAlert()">View Guardian Info</a> 
	            		</td>
					</tr>
					<tr>
						<td class='headerLabel'><bean:message key="prompt.juvenile" />&nbsp;#</td>
						<td class='headerData'>123456</td>
						<td class='headerLabel' nowrap><bean:message key="prompt.juvenileName" /></td>
						<td class='headerData' colspan='3'>SMITH, JOHN L.</td>
					</tr>		
					<tr>
						<td class='headerLabel'><bean:message key="prompt.age" /></td>
						<td class='headerData'>16</td>
						<td class='headerLabel'><bean:message key="prompt.race" /></td>
						<td class='headerData'>WHITE</td>
						<td class='headerLabel' width='1%' nowrap align=left><bean:message key="prompt.sex" /></td>
						<td class='headerData'>MALE</td>
					</tr>							
	  			</table>	
			</td>
		</tr>
	</table>
<%-- END DUMMY CASEFILE INFO TABLE --%>
	<div class="spacer4px"></div>
<%-- BEGIN FORMULA/ASSESSMENT TILE DETAILS --%>	
	<table width="98%" cellpadding="2" cellspacing="0" border="0" class="borderTableBlue">
		<logic:equal name="riskAnalysisForm" property="riskAssessmentType" value="<%=RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE%>" >
			<tr class="detailHead">
				<td colspan="2">TJJD Risk</td>
			</tr>
			<tr>
				<td>	
					<table width="100%" cellpadding="2" cellspacing="1" border="0">
						<logic:empty name="riskAnalysisForm" property="questionAnswers">
							<tr>
								<td class="formDe">No Questions/Answers found to display</td>
							</tr>
						</logic:empty>
						<logic:notEmpty name="riskAnalysisForm" property="questionAnswers">
							<tiles:insert page="../../caseworkCommon/riskAnalysisQuestionAnswers.jsp" flush="false">
								<tiles:put name="tilesAFormName" value="riskAnalysisForm"/>
								<tiles:put name="tilesImageLevel" value="3"/>
							</tiles:insert>
						</logic:notEmpty>
					</table>
				</td>
			</tr>					
		</logic:equal>
		<logic:equal name="riskAnalysisForm" property="riskAssessmentType" value="<%=RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE%>" >
			<tr class="detailHead">
				<td>TJJD Risk</td>
			</tr>			
			<tr>
				<td>	
					<table width="100%" cellpadding="2" cellspacing="1" border="0">
						<logic:empty name="riskAnalysisForm" property="questionAnswers">
							<tr>
								<td class="formDe">No Questions/Answers found to display</td>
							</tr>
						</logic:empty>
						<logic:notEmpty name="riskAnalysisForm" property="questionAnswers">
							<tiles:insert page="../../caseworkCommon/riskAnalysisQuestionAnswers.jsp" flush="false">
								<tiles:put name="tilesAFormName" value="riskAnalysisForm"/>
								<tiles:put name="tilesImageLevel" value="3"/>
							</tiles:insert>
						</logic:notEmpty>
					</table>
				</td>
			</tr>					
		</logic:equal>
		<logic:notEqual name="riskAnalysisForm" property="riskAssessmentType" value="<%=RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE%>" >
			<logic:notEqual name="riskAnalysisForm" property="riskAssessmentType" value="<%=RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE%>" >
				<tr class="detailHead">
					<td colspan="2"><bean:write name="riskAnalysisForm" property="riskAssessmentType"/> <bean:message key="prompt.info"/></td>
				</tr>
				<tr>
					<td>
						<table width="100%" cellpadding="2" cellspacing="1" border="0">
							<logic:empty name="riskAnalysisForm" property="questionAnswers">
								<tr>
									<td class="formDe">No Questions/Answers found to display</td>
								</tr>
							</logic:empty>
							<logic:notEmpty name="riskAnalysisForm" property="questionAnswers">
								<tr>
									<td class='formDeLabel' nowrap='nowrap'>
										<bean:message key="prompt.dateTaken"/>
									</td>  
									<td class='formDe'>
										<bean:write name="riskAnalysisForm" property="riskAssessmentDate" formatKey="date.format.mmddyyyy"/>
									</td>
								</tr>
								<tiles:insert page="../../caseworkCommon/riskAnalysisQuestionAnswers.jsp" flush="false">
									<tiles:put name="tilesAFormName" value="riskAnalysisForm"/>
									<tiles:put name="tilesImageLevel" value="3"/>
								</tiles:insert>
							</logic:notEmpty>
						</table>
					</td>
				</tr>								
			</logic:notEqual>	
		</logic:notEqual> 	
	</table>	
	<div class="spacer4px"></div>
</span>
<div class="spacer4px"></div>
<table border="0" width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction" onclick="disableSubmit(this,this.form)"><bean:message key="button.back"/></html:submit>
		</td>
	</tr>
</table>
<div class="spacer4px"></div>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>