<!DOCTYPE HTML>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/UpdateSubstanceAbuseQuery.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script>
	$(document).ready(function(){
		$("#submitBtn").click(function(e){
			event.preventDefault();
			if ( validateInput() ) {
				spinner();
				$("#updateSubstanceAbuseForm").submit();
			} else {
				alert("Juvenile Id or Substance Abuse ID is required.");
			}
		})
		
		$("#refreshBtn").click(function(){
			$("#juvenileId").val("");
			$("#substanceAbuseId").val("");
		})
		
		function validateInput(){
			var juvenileId = $("#juvenileId").val()
			var substanceAbuseId = $("#substanceAbuseId").val();
			
			if ( ( juvenileId === ""
							|| juvenileId.length == 0 )
							&& ( substanceAbuseId === ""
									|| substanceAbuseId.length == 0 ) ) {
				return false;
			}
			
			return true;
		}
	})
</script>
</head>

<body>

<h2 align="center">Production Support - Substance Abuse Query</h2>
<hr>

<p align="center">Please enter a Juvenile ID or Substance Abuse ID to view substance abuse Data.</p>
	<div align="center">
	<html:form styleId="updateSubstanceAbuseForm" method="post" action="/UpdateSubstanceAbuseQuery" onsubmit="return this;">
	<table border="0" width="700">
		
		<tr>
			<td align="right"><font face="" style="FONT-FAMILY: Arial"
				color="#0000aa"><strong>Juvenile ID </strong></font></td>
			<td>
				<input id="juvenileId" name="juvenileId" type="number" size="8" maxlength="8" />
			</td>
		</tr>
		
		<tr>
			<td align="right"><font face="" style="FONT-FAMILY: Arial"
				color="#0000aa"><strong>Substance Abuse ID </strong></font></td>
			<td>
				<input id="substanceAbuseId" name="substanceAbuseId" type="number" size="8" maxlength="8" />
			</td>
		</tr>

		<tr>
		<td>&nbsp;</td>
		<td>
			<input id="submitBtn" type="button" value="Submit" />
			<input id="refreshBtn" type="button" value="Refresh" />
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