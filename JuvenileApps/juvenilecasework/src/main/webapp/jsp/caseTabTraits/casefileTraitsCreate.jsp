<!DOCTYPE HTML>

<%-- Used to display casefile traits details off Traits Tab --%>
<%--MODIFICATIONS --%>
<%-- 06/09/2005		DWilliamson	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- casefileTraitsCreate.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">


<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<%-- APP JAVASCRIPT FILE --%>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

<html:javascript formName="juvenileTraitsForm" />
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin="0">
<html:form action="/submitJuvenileCasefileTraits"  target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|121">


<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" >&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- Casefile Create Traits</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="98%" border="0">
  <tr>
    <td>
	    <ul>
        <li>Select a Trait Type and Click View to see list of traits, enter Trait Comments, and click Add button to add new trait to list.</li>
        <li>To remove trait from list, check traits to remove then click Remove Selected button to remove trait from list.</li>
        <li>Click Finish button to create Trait(s). </li>
	    </ul>
	  </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN JUVENILE HEADER INCLUDE --%> 
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END JUVENILE HEADER INCLUDE  --%> 


<%-- BEGIN DETAIL TABLE --%>
<div class=spacer></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td valign=top>

      <%--tabs start--%> 
      <bean:define id="casefileId" name="juvenileTraitsForm" property="supervisionNum"/>
      <tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
        <tiles:put name="tabid" value="traitstab"/>
        <tiles:put name="casefileid" value='<%=casefileId%>' />
      </tiles:insert>				
      <%--tabs end--%> 

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
			  <tr>
					<td valign=top align=center>
			
      			<div class=spacer></div>
            <%-- BEGIN TRAITS TABLE --%>
            <tiles:insert page="../caseworkCommon/juvenileTraitsAdd.jsp" flush="true"/>
            <%-- END TRAITS TABLE --%>

            <div class=spacer></div>
		        <table border="0" cellpadding=1 cellspacing=1 align=center>
		          <tr>
		            <td align="center">
     							<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
     							<html:submit property="submitAction" onclick="spinner(); return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>
     							<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
      					</td>
		          </tr>
		        </table> 
						<div class=spacer></div>
					</td>
			  </tr>
			</table>
   	</td>
  </tr>
</table>
<%-- END DETAIL TABLE --%>

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

