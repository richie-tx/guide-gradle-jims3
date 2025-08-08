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
<title>Juvenile Casework -/prodSupport/deleteTraitQuery2.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script language="javascript">

function fillId(value)
{
	document.forms[0].traitId.value = value;
	return true;
}
</script>

</head>

<body>

<h2 align="center">Production Support - Trait Query</h2>
<hr>

<p align="center">Select a Trait ID from the list and click SUBMIT to view current data.</p>

	<div align="center">
	<html:form method="post" action="/DeleteTraitQuery?delete=Y" onsubmit="return this;">
	
	<logic:notEmpty	name="ProdSupportForm" property="traits">
	
	<p>&nbsp;</p>
	<h3 align="center">Associated Traits</h3>
	<h4 align="center">JuvenileID: <bean:write  name="ProdSupportForm" property="juvenileId" /></h4>
	
	<table border="1" width="900" align="center">
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">TRAITS_ID</font></td>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">COMMENTS</font></td>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">DISPOSITIONNUM</font></td>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">SUPERVISIONNUM</font></td>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">TRAITTYPE_ID</font></td>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">TRAITTYPE_DESC</font></td>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">TRAITSTATUS</font></td>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">TRAITSTATUS_DESC</font></td>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
			<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
			<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
			<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
			<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		</tr>
		<logic:iterate id="traits" name="ProdSupportForm" property="traits">
			<tr>
				<td>
				 	<input type="radio" name="radioProp" value="<bean:write name="traits" property="juvenileTraitId"/>"
				 		onclick="fillId(<bean:write name="traits" property="juvenileTraitId"/>)">
				 		<font size="-1"><bean:write name="traits" property="juvenileTraitId"/>&nbsp;</font>
				</td>
				<td><font size="-1"><bean:write  name="traits" property="traitComments" />&nbsp;</font></td>
				<td><font size="-1"><bean:write  name="traits" property="dispositionNum" />&nbsp;</font></td>
				<td><font size="-1"><bean:write  name="traits" property="juvenileNum" />&nbsp;</font></td>
				<td><font size="-1"><bean:write  name="traits" property="supervisionNum" />&nbsp;</font></td>
				<td><font size="-1"><bean:write  name="traits" property="traitTypeId" />&nbsp;</font></td>
				<td><font size="-1"><bean:write  name="traits" property="traitTypeDescription" />&nbsp;</font></td>
				<td><font size="-1"><bean:write  name="traits" property="statusId" />&nbsp;</font></td>
				<td><font size="-1"><bean:write  name="traits" property="status" />&nbsp;</font></td>
				<td><font size="-1"><bean:write  name="traits" property="createUserID" />&nbsp;</font></td>
				<td><font size="-1"><bean:write  name="traits" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
				<td><font size="-1"><bean:write  name="traits" property="updateUser" />&nbsp;</font></td>
				<td><font size="-1"><bean:write  name="traits" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
				<td><font size="-1"><bean:write  name="traits" property="createJIMS2UserID" />&nbsp;</font></td>
				<td><font size="-1"><bean:write  name="traits" property="updateJIMS2UserID" />&nbsp;</font></td>
			</tr>
		</logic:iterate>	
	</table>
	<table border="0" width="900">
		<tr>
			<td align="right">
			<font face="" style="FONT-FAMILY: Arial" color="#0000aa"><strong>Delete Selected Trait:</strong></font>
			</td>
			<td><html:text property="traitId" size="10" maxlength="15" readonly="true"/>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><html:submit onclick="spinner();" value="Submit" /></td>
		</tr>
	
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
	</logic:notEmpty>

<logic:empty name="ProdSupportForm" property="traits">
	<table align="center" border="0" width="900">
		<tr>
			<td align="center"><h4><i>No Traits found.</i></h4></td>
		</tr>
	</table>
</logic:empty>	

	&nbsp;
</html:form>
	
	<table border="0">
		<tr>
		<td align="center">
		<html:form method="post" action="/DeleteTraitQuery?clr=Y" onsubmit="return this;">
		<html:submit onclick="return this;" value="Search a Different Record"/>
		</html:form>
		</td>
	
		<td>&nbsp;</td>
	
		<td align="center">
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