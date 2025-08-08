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
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<title>Juvenile Casework -/prodSupport/updateAssignmentQuery.jsp</title>
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script language="javascript">
	$(document).ready(function(){
		$("#id").val("");
		$("#searchAsmgtBtn").click(function(){
			var casefileId = $("#id").val();
			if (isCasefileValid(casefileId)){
				spinner();
				$("#searchAsgmtForm").submit();
			}
		});
		
		
		function isCasefileValid(id){
			if ( id === "" ) {
				alert("Casefile Id is required.");
				return false;
			} else if ( isNaN(id) || id.length > 9 ) {
				alert("Casefile Id is not valid. Please input a valid Casefile Id.");
				return false;
			}
			
			return true;
		}
	})

</script>
</head>

<body>

<h2 align="center">Production Support - Assignment Query</h2>
<hr>

<p align="center">Please enter a casefile ID to view a list of associated assignments.</p>

	<div align="center">
	<html:form styleId="searchAsgmtForm" method="post" action="/UpdateAssignmentQuery">
	<table border="0" width="700">
		<tr>
			<td align="right"><font face="" style="FONT-FAMILY: Arial"
				color="#0000aa"><strong> Casefile ID:</strong></font></td>
			<td>
				<html:text styleId="id" property="casefileId" size="10" maxlength="15" />
			</td>
		</tr>

		<tr>
		<td>&nbsp;</td>
		<td>
			<input id="searchAsmgtBtn" type="button" value="submit"/>
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