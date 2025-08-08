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
<title>Juvenile Casework -/prodSupport/viewJuvenileMasterUpdateQuery.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript'>

function refreshPage()
{
	document.getElementById("juvenileNum").value = '';
	document.getElementById("alertMsg").value = '';
	
}

function validateSubmit()
{
	var juvId = document.getElementById("juvenileNum").value;
	if( juvId == '' ){
		alert("Juvenile number is Required!");
		return false;
	}else if( juvId.charAt(0) == 0 ){
		
		alert("Juvenile number is invalid. Please retry search.");
		return false;
	}else if ( juvId != ''){
		
		var reg = new RegExp('^\\d+$');
		if( reg.test( juvId.toString() ) == false){
			alert("Juvenile number is invalid. Please retry search.");
			return false;
		}

	}
	if(true)
	{
		spinner();
	}
}
$(document).ready(function(){	
	$("#closeIt").click(function(){
		spinner();
	})
})
</script>
</head>
<body>
<h2 align="center">Update Juvenile Master Record</h2>
<hr>
<p align="center">Please enter a Juvenile Number to see Juvenile Master data.</p>

	<div align="center">
	<html:form method="post" action="/UpdateJuvenileMasterQuery" onsubmit="return this;">
	<table border="0" width="700">
		<tr>
			<td align="right"><font color="#0000aa"><b>Juvenile Number:</b></font>
			</td>
			<td>
				<html:text property="fromJuvenileId" styleId="juvenileNum" size="8" maxlength="8" />
			</td>
		</tr>

		<tr>
		<td>&nbsp;&nbsp;</td>
		</br>
		<td>
			<html:submit value="Submit" onclick="return validateSubmit();"/>
		</td></tr>

		<tr>
		<td>&nbsp;</td>
		</tr>
	</table>
	</html:form>
	<table border="0" width="700">
		<tr align="center">
			<td>
				<input type="button" name="refreshIt" value="Refresh" onclick="refreshPage();"/>
				<input type="button" name="closeIt" value="Cancel" onclick="self.close()"/>
			</td>
		</tr>
		<tr align="center">
			<td>
				<html:form method="post" action="/MainMenu" onsubmit="return this;">
					<html:submit onclick="return this;" value="Back to Main Menu"/>
				</html:form>
			</td>
		</tr>
		
	</table>
	
	<html:form method="post" action="/ViewJuvenileSealQuery"  onsubmit="return this;">
	    
    <table border="0" width="700">
	
	<tr><td colspan="4">&nbsp;</td></tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	
	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<bean:write name="ProdSupportForm" property="msg"/>
			</logic:notEqual> 
			</font></td>
	</tr>
	</table>
	
	<html:hidden styleId="alertMsg" name="ProdSupportForm" property="msg"/>
    </html:form>
    
	</div>

</body>
	<br />
	
</html:html>