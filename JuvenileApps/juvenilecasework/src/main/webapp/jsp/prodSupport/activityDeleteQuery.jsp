<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%-- 01/04/2016	RCarter	   #29426 html 5 compliance effort and jquery 5 (when required) --%>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/activityDeleteQuery.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script>
	$(document).ready(function(){
		$("#submitBtn").click(function(){
			if ( validateInput() ) {
				spinner();
				$("#activityDeleteForm").submit();
			}
		})
		
		
		function validateInput(){
			var activityId = $("#actId").val();
			
			if ( activityId === "" ){
				alert("Activity Id is required.");
				return false;
			}
			
			if ( isNaN( activityId ) || activityId.length > 10  ){
				alert("Activity Id is not valid. Please input a valid Activity Id.");
				return false;
			}
			
			return true;
		}
	})
</script>
</head>
<body>

<h2 align="center">Production Support - Activity Query</h2>
<hr>

<p align="center">Please enter an ActivityID to display its information.</p>

	<div align="center">
	<html:form styleId="activityDeleteForm" method="post" action="/ActivityDeleteQuery" onsubmit="return this;">
	<table border="0" width="700">
		<tr>
			<td align="right"><font face="" style="FONT-FAMILY: Arial"
				color="#0000aa"><strong> Activity ID:</strong></font></td>
			<td>
				<html:text styleId="actId" property="activityId" size="10" maxlength="15" />
			</td>
		</tr>

		<tr>
		<td>&nbsp;</td>
		<td>
			<input id="submitBtn" type="button" value="Submit" />
		</td></tr>

		<tr>
		<td>&nbsp;</td>
		</tr>
		</table>
	</html:form>
	
	<table border="0" width="700">
		<tr>
		<td colspan="2" align="center">
		<html:form method="post" action="/MainMenu" onsubmit="return this;">
		<html:submit onclick="return this;" value="Back to Main Menu"/>
		</html:form>
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
    
	</div>

</body>
</html:html>