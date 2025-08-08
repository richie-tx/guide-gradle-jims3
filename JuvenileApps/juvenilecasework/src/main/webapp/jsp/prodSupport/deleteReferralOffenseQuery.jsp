<!DOCTYPE HTML>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/deleteReferralOffenseQuery</title>
<!--JQUERY FRAMEWORK-->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<script type='text/javascript'>

function closeWindow(el)
{
	window.close();
    return 0;
}
/* function Refresh() {
	$("#juvNum").val("");
	$("#refNum").val("");
	return true;
} */
function Validate() 
{
	var juvNum 	= $("#juvNum").val();
	var refNum 	= $("#refNum").val();
	
	if( juvNum == '' )
	{
		alert('Juvenile Number is Required.');
		return false;
	}
	else
	{
		if( $.isNumeric( juvNum ))
		{
			
		}
		else
		{	
			alert('Juvenile Number is invalid. Please retry search.');
			return false;
		}
	}
	
	if( refNum == '')
	{
		alert('Referral number is required.');
		return false;
	}
	else
	{
		
		if( $.isNumeric( refNum ))
		{
			
		}
		else
		{			
			alert('Referral Number is invalid. Please retry search.');
			return false;
		 }
	}
	if(true)
		spinner();
	return true;
}
</script>
</head>
<body>
<h2 align="center">Production Support - Search Referral Offense Record for Deletion</h2>
<hr>

<p align="center">Please enter a Juvenile ID and Referral Number to view current referral offense data.</p>

	<div align="center">
	<html:form method="post" action="/DeleteReferralOffenseQuery" onsubmit="return this;">
	<table border="0" width="700" align="center">
		<tr>
			<td align="right"><font face="" style="FONT-FAMILY: Arial"
				color="#0000aa"><strong> Juvenile Number:</strong></font></td>
			<td>
				<html:text property="juvenileId" styleId='juvNum' size="8" maxlength="8" />
			</td>
		</tr>
		<tr>
			<td align="right"><font face="" style="FONT-FAMILY: Arial"
				color="#0000aa"><strong> Referral Number:</strong></font></td>
			<td>
				<html:text property="referralId" styleId='refNum' size="8" maxlength="4" />
			</td>
		</tr>

		<tr>
		<td>&nbsp;</td>
		<td>
		
		<html:submit property="submitAction" onclick="return Validate()" styleId="submitButton">
					<bean:message key="button.submit" />
		</html:submit>
		&nbsp;
		<input type="button" onclick="return closeWindow(this.form)" id="BtnCancel" value="<bean:message key='button.cancel'/>"/>		
		&nbsp;
		<%-- <input type="button" id="BtnRefresh" value="<bean:message key='button.refresh'/>"/> --%>	<!-- onclick="return Refresh()"  -->
		</td></tr>

		<tr>
		<td>&nbsp;</td>
		</tr>
		</table>
	</html:form>
	<table align="center">
			<tr>

				<td>&nbsp;</td>

			</tr>

			
			<html:form action="/DeleteReferralOffenseQuery?clr=Y"
		onsubmit="return this;">
		<table align="center"">
			<tr>
				<td><html:submit onclick="spinner();" value="Refresh" />
				</td>
			</tr>
		</table>
	</html:form>
		</table>
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
	<html:hidden styleId="juvenileNum" name="ProdSupportForm" property="juvenileId"/>
	<html:hidden styleId="referralNum" name="ProdSupportForm" property="referralId"/>
</body>
</html:html>