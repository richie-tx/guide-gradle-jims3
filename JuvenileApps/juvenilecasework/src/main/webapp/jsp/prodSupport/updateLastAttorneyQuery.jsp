
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>

<html:html> 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/updateLastAttorneyQuery</title>\
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />	
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<script type='text/javascript'>

function closeWindow(el)
{
	window.close();
    return 0;
}

function Validate() 
{
	var juvNum 	= $("#juvNum").val();
	var chainNum = $("#chaiNum").val();
	console.log( chainNum );
	
	if( juvNum == '' )
	{
		alert('Juvenile Number is Required');
		return false;
	}
	else
	{
		if( !$.isNumeric( juvNum ))
		{
			alert('Juvenile Number is invalid. Please retry search.');
			return false;
		}
	}

	if ( !$.isNumeric( chainNum ) && chainNum != ""  ){
		alert('Chain Number is invalid. Please retry search.');
		return false;
	}
	
	spinner();
	
}
</script>
</head>
<body>
<h2 align="center">Production Support - Search Juvenile – Last Attorney Update</h2>
<hr>
<p align="center">Please enter a Juvenile ID to view Last Attorney details</p>

	<div align="center">
	<html:form method="post" action="/UpdateLastAttorneyQuery" onsubmit="return this;">
	<table style="padding-right: 100px;">
		<tr>
			<td align="right"><font face="" style="FONT-FAMILY: Arial"
				color="#0000aa"><strong> Juvenile Number:</strong></font></td>
			<td>
				<html:text property="juvenileId" styleId='juvNum' size="8" maxlength="7" />
			</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td align="right"><font face="" style="FONT-FAMILY: Arial"
				color="#0000aa"><strong> Chain Number:</strong></font></td>
			<td>
				<html:text property="chainNum" styleId='chaiNum' size="8" maxlength="7" />
			</td>
			<td>&nbsp;</td>
		</tr>

		<tr>
		<td>&nbsp;</td>
		</tr>
		</table>
		
		<table align="center">
		<td>
		
		<html:submit property="submitAction" onclick="return Validate()" styleId="submitButton">
					<bean:message key="button.submit" />
		</html:submit>
		</html:form>
		&nbsp;
		<input type="button" onclick="return closeWindow(this.form)" id="BtnCancel" value="<bean:message key='button.cancel'/>"/>		
		&nbsp;
		</td>
		<td>
			<html:form action="/UpdateLastAttorneyQuery?clr=Y"
				onsubmit="return this;">
		<html:submit value="Refresh" />
		</html:form>
		</td>
		</tr>
		<tr>
		<td>&nbsp;</td>
		</tr>
		</table>

	</html:form> 
		</table>
	<table align="center">
		<tr>
		<td>
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
	<html:hidden styleId="juvenileNum" name="ProdSupportForm" property="juvenileId"/>
</body>
</html:html>