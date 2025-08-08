<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/deleteMaysiAssessQuery.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
</head>

<body>

<h2 align="center">Production Support - MAYSI Assessment Query</h2>
<hr>

<p align="center">Please enter a JUVENILE_ID and (optionally) REFERRALNUM to find out what data exists.</p>

	<div align="center">
	<html:form method="post" action="/DeleteMaysiAssessQuery" onsubmit="return this;">
	<table border="0" width="700">
		<tr>
			<td align="right"><font color="#0000aa"><b>Juvenile ID:</b></font>
			</td>
			<td>
				<html:text property="fromJuvenileId" size="10" maxlength="15" />
			</td>
		</tr>
		<tr>
			<td align="right"><font color="#0000aa"><b>Referral Number:</b></font>
			</td>
			<td>
				<html:text property="referralNum" size="10" maxlength="15" />
			</td>
		</tr>

		<tr>
		<td>&nbsp;</td>
		<td>
			<html:submit onclick="spinner();" value="Submit" />
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