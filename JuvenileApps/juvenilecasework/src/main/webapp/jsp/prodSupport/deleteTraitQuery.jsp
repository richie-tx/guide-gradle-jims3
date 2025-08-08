<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/deleteTraitQuery.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script>
	$(document).ready(function(){
		$("#submitBtn").click(function(){
			if ( validateInput() ) {
				spinner();
				$("#deleteTraitForm").submit();
			}
		})
		
		function validateInput(){
			var juvenileId = $("#jId").val();
			var traitId = $("#tId").val();
			
			if ( juvenileId === "" ) {
				alert("Juvenile Id is required.");
				return false;
			}
			
			if ( isNaN( juvenileId  ) || juvenileId .length > 8 ) {
				alert("Juvenile Id is not valid. Please input a valid Juvenile Id");
				return false;
			}
			
			if ( traitId  != ""
				&& ( isNaN( traitId ) || traitId .length > 10)  ) {
				alert ("Trait Id is not valid. Please input a valid Trait Id");
				return false;
			}
			
			return true;
		}
	})
</script>
</head>

<body>

<h2 align="center">Production Support - Juvenile Trait Query</h2>
<hr>

<p align="center">Please enter a JUVENILE_ID find out what trait data exists.</p>

	<div align="center">
	<html:form styleId="deleteTraitForm" method="post" action="/DeleteTraitQuery" onsubmit="return this;">
	<table border="0" width="700">
		<tr>
			<td align="right"><font color="#0000aa"><b>Juvenile ID:</b></font>
			</td>
			<td>
				<html:text styleId="jId" property="juvenileId" size="10" maxlength="15" />
			</td>
		</tr>
		<tr>
			<td align="right"><font color="#0000aa"><b>Trait ID:</b></font>
			</td>
			<td>
				<html:text styleId="tId" property="traitId" size="10" maxlength="15" />
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
	<tr><td colspan="4">
			<p align="center">Enter a Juvenile Id to get all Traits for a Juvenile. Enter a specific Trait Id to get a unique trait. </br> If both are entered, a unique trait will be found.</p>
		</td>
	</tr>	
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