<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/assessDeleteSummary.jsp</title>
</head>

<body>
	
<html:form method="post" action="/DeleteCasefileQuery" onsubmit="return this;">

<h2 align="center">Delete Assessment Summary</h2>
<hr>

<p align="center"><font color="green"><b>Assessment number 
<bean:write name="ProdSupportForm" property="riskanalysisId" /> was successfully deleted.</b></font></p>
</html:form>


<p align="center"><b>The following is a list of records affected by this change. This is for auditing purposes.<br> 
If this delete was made in error, please provide this list to the JIMS Data Team to restore the data.</b></p>
<hr>

	<logic:notEmpty	name="ProdSupportForm" property="riskresponses">
	<h3 align="center">Risk Responses</h3>
	
	<table border="1" width="500" align="center">
	<tr>
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">JCRISKRESPONSE_ID</font></td>
	</tr>
	
    <logic:iterate id="riskresponses" name="ProdSupportForm" property="riskresponses">
	<tr>
	    <td><font size="-1"><bean:write  name="riskresponses" property="responseID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
	
	</table>
	</logic:notEmpty>
	
	<h3 align="center">Risk Analyses</h3>
	
	<table border="1" width="500" align="center">
	<tr>    	  
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">JCRISKANALYSIS_ID</font></td>
	</tr>
	
	<tr>
	    <td><font size="-1"><bean:write  name="ProdSupportForm" property="riskanalysisId" />&nbsp;</font></td>
	</tr>
	
	
	</table>
	
	<table align="center" border="0" width="500">
		<tr>
		<td colspan="2" align="center">
		<html:form method="post" action="/MainMenu" onsubmit="return this;">
		<html:submit onclick="return this;" value="Back to Main Menu"/>
		</html:form>
		</td>
		</tr>

    </table>    
    

</body>
</html:html>