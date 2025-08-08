<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%--05/29/2025 NMathew --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/moveEventToNewProgramSummary.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
</head>

<body>

<h2 align="center">Production Support - Move Event to New Program</h2>
<hr>
<logic:notEqual name="ProdSupportForm" property="action" value="updateSuccess">
<p align="center">Enter the new JUVPROGREF_ID to move the existing data.</p>

	<div align="center">
		<html:form method="post" action="/performMoveEventToNewProgram" onsubmit="return this;">
			<table border="0" width="700">
				<tr>
					<td align="right"><font face="" style="FONT-FAMILY: Arial" color="#0000aa"><strong>New JUVPROGREF_ID:</strong> </font></td>
					<td><html:text property="newProgramRefId" size="10" maxlength="8" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><html:submit onclick="spinner()" value="Move Events" disabled="false" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
		</html:form>

		<html:form method="post" action="/MainMenu" onsubmit="return this;">
	
	<table border="0" width="700">
		<tr>
		<td colspan="2" align="center">
		<%-- <html:submit onclick="return this;" value="Back to Query Results"/> --%>
		<html:submit onclick="return this;" value="Cancel"/>
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
</logic:notEqual>
<logic:equal name="ProdSupportForm" property="action" value="updateSuccess">
<h3><font color=green>The selected events for the JUVPROGREF_ID = <bean:write name="ProdSupportForm" property="juvprogrefId" /> 
is now moved to JUVPROGREF_ID = <bean:write name="ProdSupportForm" property="newProgramRefId" /></font></h3>

	<html:form action="/moveEventToNewProgramAction?clr=Y" onsubmit="return this;">
	<table align="center">
		<tr>
			<td><html:submit value="Back to Query" /></td>
		</tr>
	</table>
</html:form>
<html:form method="post" action="/MainMenu" onsubmit="return this;">
	
	<table align="center">
		<tr>
		<td>
		<html:submit onclick="return this;" value="Back to Main Menu"/>
		</td>
		</tr>
    </table>
</html:form>
</logic:equal>
</body>
</html:html>