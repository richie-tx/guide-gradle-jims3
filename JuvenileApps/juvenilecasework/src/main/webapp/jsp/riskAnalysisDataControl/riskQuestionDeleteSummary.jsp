<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- DWilliamson  01/12/2011	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>



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
<title><bean:message key="title.heading"/> - riskQuestionDeleteSummary.jsp</title>

<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>

</head>
<body topmargin="0" leftmargin="0">
<html:form action="/submitRiskQuestionDelete" target="content">
<input type="hidden" name="helpFile" value="">

<!-- BEGIN HEADING TABLE -->
<table width="100%">
  <tr>
    <td align="center" class="header" ><bean:message key="title.riskQuestions"/> - <bean:message key="prompt.delete"/>
                                       <bean:message key="prompt.question"/>&nbsp;<bean:message key="title.summary"/></td>
  </tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
  <table width=98% align=center>							
	<TBODY>
	  <tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	  </tr>		
	</TBODY>
  </table>
  <!-- END ERROR TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<br>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Select Finish button to delete question.</li>
      </ul>
    </td>
  </tr>
  <tr>
    <td align="center"></td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

  <br>

  <!-- Place formName in request for tiles to use -->	
  <bean:define id="form" value="riskQuestionDeleteForm" toScope="page"/>
  
  <!-- START Titles to be used for Question and Answer Boxes  -->
  <bean:define id="questionTitle" toScope="page">
  	<bean:message key="prompt.question"/>
  </bean:define>
  <!-- END Titles to be used for Question and Answer Boxes  -->

  <!-- BEGIN QUESTION TABLE -->
  <tiles:insert page="riskQuestionTile.jsp" flush="true">
  	<tiles:put name="formName" type="String" value="${form}" />
  	<tiles:put name="showQuestionButtons" type="String" value="false" />
  	<tiles:put name="questionBoxTitle" type="String" value="${questionTitle}" />
  </tiles:insert>
  <%-- END QUESTION TABLE --%>

  <div class="spacer"></div>
  <table border="0" width="100%">
    <tr>
      <td align="center">
      		<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
            <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>
            <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
	  </td>				
    </tr>
  </table>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop();</script></div>

</body>
</html:html>
