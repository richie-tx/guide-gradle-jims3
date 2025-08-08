<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>
<%-- 02/12/2016	RCarter	   New initial query jsp for facilities --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/deleteFacilityHeaderQuery.jsp</title>
</head>

<body>

<h2 align="center">Production Support Facility Header Delete Query</h2>
<hr>

<p align="center">Please enter a Juvenile Id to find and delete a header record.</p>

	<div align="center">
	<html:form method="post" action="/DeleteFacilityHeaderQuery" onsubmit="return this;">
	<table border="0" width="700">
		<tr>
			<td align="right"><font color="#0000aa"><b>Juvenile ID:</b></font>
			</td>
			<td>
				<html:text property="juvenileId" size="10" maxlength="15" />
			</td>
		</tr>
		<tr>
		<td>&nbsp;</td>
		<td>
			<html:submit value="Submit" />
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