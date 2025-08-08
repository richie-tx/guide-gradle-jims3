<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<!--5/7/2014    r carter  changed name as part of prod support update  -->
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/servEventModifyQuery.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script>
	$(document).ready(function(){
		$("#submitBtn").click(function(){
			if ( validateInputs() ) {
				spinner();
				$("#modifyServEventForm").submit();
			}
		
		})
		function validateInputs(){
			var juvenileId = $("#jId").val();
			var serviceEventId =  $("#sId").val();
			
			if ( juvenileId  === "" ){
				alert("Juvenile Id is required");
				return false;
			}
			
			if ( isNaN(juvenileId) ||  juvenileId.length > 8 ){
				alert("Juvenile Id is not valid. Please input a valid Juvenile Id.");
				return false;
			}
			
			if ( serviceEventId === "" ){
				alert("Service Event Id is required");
				return false;
			}
			
			if ( isNaN(serviceEventId ) || serviceEventId.length > 10  ){
				alert("Service Event Id is not valid. Please input a valid Service Event Id.");
				return false;
			}
			
			return true;
		}
	})
</script>
</head>

<body>

<h2 align="center">Production Support - Calendar Event Query</h2>
<hr>

<p align="center">Please enter a SERVEVENT_ID and JUVENILE_ID to find out what data exists.</p>

	<div align="center">
	<html:form styleId="modifyServEventForm" method="post" action="/ModifyServEventQuery" onsubmit="return this;">
	<table border="0" width="700">
		<tr>
			<td align="right"><font color="#0000aa"><b>Juvenile ID:</b></font>
			</td>
			<td>
				<html:text styleId="jId" property="fromJuvenileId" size="10" maxlength="15" />
			</td>
		</tr>
		<tr>
			<td align="right"><font color="#0000aa"><b>Service Event ID:</b></font>
			</td>
			<td>
				<html:text styleId="sId" property="serveventId" size="10" maxlength="15" />
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