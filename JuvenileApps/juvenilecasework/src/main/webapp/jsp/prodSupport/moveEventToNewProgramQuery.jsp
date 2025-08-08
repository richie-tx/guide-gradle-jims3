<!DOCTYPE HTML>
<%-- --%>
<%--NMathew May 2025 - US# 188944 --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/moveEventToNewProgramQuery.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
</head>

<body>

<h2 align="center">Production Support - Move Event to New Program</h2>
<hr>

<p align="center">Please enter a JUVPROGREF_ID to find out what dependent data exists.</p>

	<div align="center">
	<html:form method="post" action="/moveEventToNewProgramAction" onsubmit="return this;">
	<table border="0" width="700">
		<tr>
			<td align="right"><font face="" style="FONT-FAMILY: Arial"
				color="#0000aa"><strong> JUVPROGREF_ID:</strong></font></td>
			<td>
				<html:text property="juvprogrefId" size="10" maxlength="15" />
			</td>
		</tr>

		<tr>
		<td>&nbsp;</td>
		<td>
			<html:submit onclick="spinner()" value="Submit" disabled="false"/>
		</td></tr>

		<tr>
		<td>&nbsp;</td>
		</tr>
		</table>
	</html:form>
	
	<html:form method="post" action="/MainMenu" onsubmit="return this;">
	
	<table border="0" width="700">
		<tr>
		<td colspan="2" align="center">
		
		<html:submit onclick="return this;" value="Back to Main Menu"/>
		</td>
		</tr>

    </table>
    
    <table border="0" width="700">
	
	<tr><td colspan="4">&nbsp;</td></tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	
	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<bean:write name="ProdSupportForm" property="msg" />
			</logic:notEqual> 
			</font></td>
	</tr>
	</table>
    </html:form>
    
	</div>

</body>
</html:html>