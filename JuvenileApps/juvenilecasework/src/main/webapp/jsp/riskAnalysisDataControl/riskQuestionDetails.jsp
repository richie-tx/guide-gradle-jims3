<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- DWilliamson  12/10/2010	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>
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
<title><bean:message key="title.heading" /> - riskQuestionDetails.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/riskAnalysis.js"></script>
</head>
<body topmargin="0" leftmargin="0">
<html:form action="/handleRiskQuestionDetailsSelection" target="content"> 
<input type="hidden" name="helpFile" value="">
  <!-- BEGIN HEADING TABLE -->
  <table width="100%">
    <tr>
      <td align="center" class="header" ><bean:message key="title.riskQuestions"/> - <bean:message key="prompt.question"/>&nbsp;<bean:message key="prompt.details"/></td>
    </tr>
  </table>
  <!-- END HEADING TABLE -->
  
  <!-- BEGIN Error Message Table -->
  <logic:messagesPresent> <br />
	<table width="100%">
		<tr>		  
			<td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"></html:errors></td>		  
		</tr>   	  
	</table>
  </logic:messagesPresent> 
  <!-- END Error Message Table -->
  
  <br>
  <!-- BEGIN INSTRUCTION TABLE -->
  <table width="98%" border="0">
    <tr>
      <td><ul>
          <li>Click on hyperlinked Answer Name to view Answer Details.</li>
        </ul></td>
    </tr>
  </table>
  <!-- END INSTRUCTION TABLE -->
  
  <!-- Place formName in request for tiles to use -->	
  <bean:define id="form" value="handleRiskQuestionDetailsSelectionForm" toScope="page"/>
  
  <!-- START Titles to be used for Question and Answer Boxes  -->
  <bean:define id="questionTitle" toScope="page">
  	<bean:message key="prompt.question"/>
  </bean:define>
  <bean:define id="answerTitle" toScope="page">
  	<bean:message key="prompt.answersForQuestion"/>
  </bean:define>
  <!-- END Titles to be used for Question and Answer Boxes  -->

  <!-- BEGIN QUESTION TABLE -->
  <tiles:insert page="riskQuestionTile.jsp" flush="true">
  	<tiles:put name="formName" type="String" value="${form}" />
  	<tiles:put name="showQuestionButtons" type="String" value="true" />
  	<tiles:put name="questionBoxTitle" type="String" value="${questionTitle}" />
  </tiles:insert>
  <%-- END QUESTION TABLE --%>
  
  <br>
  
  <!-- BEGIN ANSWERS TABLE -->
  <tiles:insert page="riskAnswersTile.jsp" flush="true">
    <tiles:put name="formName" type="String" value="${form}" />
  	<tiles:put name="answerListName" type="String" value="answerList" />
  	<tiles:put name="showAnswerButtons" type="String" value="true" />
  	<tiles:put name="showAnswerRadioButtons" type="String" value="true" />
  	<tiles:put name="showAnswerNameAsReferenceLinks" type="String" value="true" />
  	<tiles:put name="answerBoxTitle" type="String" value="${answerTitle}" />
  </tiles:insert>
  <%-- END ANSWERS TABLE --%>
  
  <!-- BEGIN BUTTON TABLE -->
<div class="spacer"></div>
<table border="0" width="100%">
  <tr>
    <td align="center"> 
        <html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
        <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
    </td>
  </tr>
</table>
<div class="spacer"></div>
<!-- END BUTTON TABLE -->
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
