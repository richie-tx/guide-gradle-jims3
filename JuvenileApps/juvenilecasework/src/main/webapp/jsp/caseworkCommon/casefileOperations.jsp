<!DOCTYPE HTML>
<%-- Used to display casefile details off Casefile Tab --%>
<%--MODIFICATIONS --%>
<%-- 05/09/2005		LDeen	Create JSP --%>
<%-- 01/09/2007 Hien Rodriguez  ER#37077 Add Tab & Buttons security features --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>





<%--BEGIN HEADER TAG--%>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<html:base />
<title><bean:message key="title.heading"/> - casefileOperations.jsp</title>
</head> 

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin='0' leftmargin="0">
<html:form action="/displayJuvenileCasefileDetails"  target="content" >

<%-- BEGIN CASEFILE OPERATIONS TABLE --%>
<logic:equal name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_ACTIVE%>">
<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_CLOSING_U%>'>	
	<bean:define id="showTable" value="true"/>
</jims2:isAllowed>
</logic:equal>
<logic:notPresent name="showTable">
	<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_CORR_U%>'>	
		<bean:define id="showTable" value="true"/>
	</jims2:isAllowed>
</logic:notPresent>

<logic:present name="showTable">
<table align='center' cellpadding='6' class='borderTableGrey'>
	<logic:equal name="juvenileCasefileForm" property="caseStatusId" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_ACTIVE%>">
		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_CLOSING_U%>'>	

			<logic:equal name="juvenileCasefileForm" property="hasReferrals" value="true">
				<tr>
					<td><img src='../../images/blue_arrow.gif' hspace='4' align='middle'><a onclick="spinner()" href="/<msp:webapp/>processCasefileClosing.do?submitAction=<bean:message key='button.link'/> ">Process Casefile Closing</a></td>
				</tr>
			</logic:equal> 
							
		</jims2:isAllowed>
	</logic:equal>
	
		<jims2:isAllowed requiredFeatures='<%=Features.JCW_CF_CORR_U%>'>	
	  	<bean:define id="casefileCorrShown" value="true"/>
		 		<tr>
		  			<td><img src='../../images/blue_arrow.gif' hspace='4' align='middle'><a href="/<msp:webapp/>handleCasefileCorrection.do?submitAction=<bean:message key='button.link'/>">Change Casefile Status and Supervision Type</a></td>
		  		</tr>
		</jims2:isAllowed>
</table>
</logic:present>
</html:form>	
<%-- END FORM --%>

</body>
</html:html>
