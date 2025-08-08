<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/activityDeleteSummary.jsp</title>
</head>

<body>
	
<html:form method="post" action="/ActivityDeleteQuery" onsubmit="return this;">

<h2 align="center">Delete Activity Summary</h2>
<hr>

<p align="center"><font color="green"><b>Activity number 
<bean:write name="ProdSupportForm" property="activityId" /> was successfully deleted.</b></font></p>

<p align="center"><b>The following is a list of records affected by this change. This is for auditing purposes.<br> 
If this delete was made in error, please provide this list to the JIMS Data Team to restore the data.</b></p>
<hr>

	<logic:notEmpty	name="ProdSupportForm" property="activities">
	
	<table border="1" width="500" align="center">
	<tr>
        <td bgcolor="gray" align="center"> <font color="white" face="bold" size="-1">ACTIVITY_ID</font></td>
	</tr>
	
    <logic:iterate id="activities" name="ProdSupportForm" property="activities">
	<tr>
	    <td align="center"><font size="-1"><bean:write  name="activities" property="activityId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
	
	</table>
	</logic:notEmpty>
	
	</html:form>
	
	<table align="center" border="0" width="500">
		<tr>
		<td>&nbsp;</td>
		</tr>
		<tr>
		<td colspan="2" align="center">
		<html:form method="post" action="/ActivityDeleteQuery.do?clr=Y" onsubmit="return this;">
		<html:submit onclick="return this;" value="Delete Another Activity"/>
		</html:form>
		</td>
		</tr>

    </table>    
	
	<table align="center" border="0" width="500">
		<tr>
		<td>&nbsp;</td>
		</tr>
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