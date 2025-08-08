<!DOCTYPE HTML>

<%-- Used for searching of juvenile casefiles in MJCW --%>
<%--MODIFICATIONS --%>
<%-- Dwilliamson  10/26/2010	Create JSP --%>
<%-- 04/24/2012		CShimek		#73272 added onload scripting for Control Type --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>



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
<title><bean:message key="title.heading" /> - riskQuestionCreate.jsp</title>

<%-- STRUTS VALIDATION --%>
<%--html:javascript formName="riskQuestionsSearchForm" /--%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/riskAnalysis.js"></script>
<!--  validation Javascript is found in riskAnalysis.js -->
<script type='text/javascript'>
function checkUIControlType()
{
	var qs = document.getElementsByName("question.hardcoded");
	for (x=0; x<qs.length; x++)
	{	 
		if (qs[x].type == "checkbox" && qs[x].checked == true)
		{
			var qtxt = document.getElementById("questionText").readOnly = true;
			break;
		} 	
	}
	var ct = document.getElementById("uiControlType");
	if (ct.value == "DATE"){
		document.getElementById("defaultSysDate").disabled = false;
		document.getElementById("allowFutDate").disabled = false;
	}
}
</script>
</head>
<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="checkUIControlType()">

<%--BEGIN FORM TAG--%>
<!-- NOT NEEDED - redirect to summary in displayRiskQuestionCreate action
	html:form action="/displayRiskCreateQuestionSummary" target="content" focus="questionName"  --> 
<html:form action="/displayRiskQuestionCreate" target="content" focus="question.questionName"> 

<input type="hidden" name="helpFile" value="">
  <!-- BEGIN HEADING TABLE -->
  <table width="100%">
    <tr>
      <td align="center" class="header" ><bean:message key="title.riskQuestions"/> - <bean:message key="title.create"/>&nbsp;<bean:message key="prompt.question"/> </td>
    </tr>
  </table>
  <!-- END HEADING TABLE -->
  
  <%-- BEGIN ERROR TABLE --%>
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
	<%-- END ERROR TABLE --%>
  
  <br/>
  
  <!-- BEGIN INSTRUCTION TABLE -->
  <table width="98%" border="0">
    <tr>
      <td>
        <ul>
          <li>Select Back button to return to previous page.</li>
          <li>Select Next button to continue to Summary page.</li>
        </ul></td>
    </tr>
    <tr>
      <td class="required"><bean:message key="prompt.2.diamond" /> <bean:message key="prompt.requiredFieldsInstruction"/></td>
    </tr>
  </table>
  <!-- END INSTRUCTION TABLE -->
 
    <!-- Place formName in page for tiles to use -->	
	<bean:define id="form" value="riskQuestionCreateForm" toScope="page"/>
	
	<!-- START Titles to be used for Question and Answer Boxes  -->
	<bean:define id="questionTitle" toScope="page">
		<bean:message key="prompt.question"/>
	</bean:define>
	<!-- END Titles to be used for Question and Answer Boxes  -->
	
	<!-- BEGIN QUESTION TABLE -->
	<tiles:insert page="riskQuestionEditTile.jsp" flush="true">
  		<tiles:put name="formName" type="String" value="${form}" />
  		<tiles:put name="questionBoxTitle" type="String" value="${questionTitle}" />
    </tiles:insert>
	<%-- END QUESTION TABLE --%>
  
    <br>
	
	<!-- BEGIN ANSWERS TABLE -->
    <tiles:insert page="riskAnswersEditTile.jsp" flush="true">
    	<tiles:put name="formName" type="String" value="${form}" />
  		<tiles:put name="selfDirectLink" type="String" value="displayRiskQuestionCreate" />
  	</tiles:insert>
    <%-- END ANSWERS TABLE --%>
			
    <br>
   
  <table width="100%">
	<tr id="submitButtons">
      <td align="center">
        <html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
        <html:submit property="submitAction" onclick="return validateQuestionFields(this.form) && validateAnswerToBeAdded() "><bean:message key="button.next" /></html:submit>
        <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
      </td>
    </tr>
 </table> 


</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
