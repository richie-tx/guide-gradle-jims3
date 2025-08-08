<!DOCTYPE HTML>


<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />
<meta charset="UTF-8" />

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
<title><bean:message key="title.heading"/> - exceptionsListApprove.jsp</title>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script>
	$(document).ready(function(){
		var casefileClosingEndDate = '<bean:write name="casefileClosingForm" property="supervisionEndDate"/>';
		sessionStorage.removeItem("casefileClosingEndDate");
		sessionStorage.setItem("casefileClosingEndDate", casefileClosingEndDate);
	})
</script>


</head> 
<%--END HEAD TAG--%>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin="0" leftmargin="0" onload="return showOnLoad()">
<html:form action="processCasefileClosing.do" target="content" >


<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.close"/> <bean:message key="title.casefile"/> 
		- <bean:message key="title.casefile"/> <bean:message key="title.closing"/> <bean:message key="title.exceptions"/>
		</td>
	</tr>
</table>
<table width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign='top' align='center' class='errorAlert'>The Closing requirements have not been met. The following need to be completed before the casefile can be closed.</td>
	</tr>
</table>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<%-- END HEADING TABLE --%>
<div class='spacer'></div>
<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%">
	<tr>
   		<td>
	   		<ul id='defaultInstructions'>
	    		<li>Select a "Fix" hyperlink associated to the exception to perform corrections.</li>
	    		<li>Select the Back button to return to the previous page.</li>
	  		</ul>
	   	</td>
	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>
<div class='spacer'></div>
<%-- BEGIN EXCEPTIONS TABLE --%>
<table width='98%' cellpadding='2' cellspacing='0' class='borderTableBlue' align="center">			
	<tr>
		<td class='detailHead'><bean:message key="prompt.exceptions"/></td>
	</tr>
				
	<logic:empty name="casefileClosingForm" property="casefileExceptions">
		<tr class='normalRow'>
			<td>There are no Exceptions</td>
		</tr>
	</logic:empty>

	<logic:notEmpty name="casefileClosingForm" property="casefileExceptions">
		<tr>
			<td>
				<table width='100%' cellpadding=2 cellspacing=1>						
					<tr bgcolor='#cccccc'>
						<td class='subHead' width='1%'>&nbsp;</td>
						<td class='subHead' colspan='2'><bean:message key="prompt.message"/></td>
					</tr>

					<logic:iterate id="casefileException" name="casefileClosingForm" property="casefileExceptions" indexId="index">
						<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">						
							<logic:equal name="casefileException" property="exceptionType" value="<%=PDJuvenileCaseConstants.PROGRAM_REFERRAL_END_DATE_AFTER%>" >									 		
								<td><a href="/<msp:webapp/>handleProgramReferral.do?submitAction=Fix
																						&selectedValue=<bean:write name="casefileException" property="exceptionId" />">
									Fix</a>
								</td>
								<td> 
									<logic:notEqual name="casefileException" property="exceptionMessage" value="">
										<bean:write name="casefileException" property="exceptionMessage" /> 
									</logic:notEqual>
								</td>
							</logic:equal>	
						</tr>
					</logic:iterate>	
				</table>
			</td>
		</tr>
	</logic:notEmpty>
</table>
<%-- END EXCEPTIONS TABLE --%>		


<!-- end override exceptions table/comments section -->
<div class='spacer'></div>
<%-- BEGIN BUTTON TABLE --%>
<table width="100%">
	<tr>
		<td align="center">
			<span id='defaultButtons'>
				<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
			</span>
						
			
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>
<div class='spacer'></div>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>