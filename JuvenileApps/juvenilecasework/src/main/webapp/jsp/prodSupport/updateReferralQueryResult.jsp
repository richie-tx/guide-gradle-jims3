<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Juvenile Casework -/prodSupport/updateReferralQueryResult.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script language="javascript">

$(document).ready(function(){
	$("#updateRecords").click(function(){
		if ( confirmUpdate() ){
			spinner();
			$("#performUpdateReferral").submit();
		}
		
	})
})
function confirmUpdate(){
	
if ( $("#statusBox").val() == ""
		&& "AC" != $("#statusCd").val()
		&& ( ( $("#statusSubCd").val() == ""
				&&  $("#substatusBox").val() == "" )
			|| $("#substatusBox").val() == "999" )  ) {
	alert("SUBSTATUSCD is required.");
	return false;
}

if ( $("#statusBox").val() != "" )
{
	if ( $("#statusBox").val() != "AC"
			&& ( ( $("#statusSubCd").val() == ""
						&&  $("#substatusBox").val() == "" )
					|| $("#substatusBox").val() == "999" )  ) {
		alert("SUBSTATUSCD is required.");
		return false;
	}
		
	if(confirm('Are you sure you want to update the record?'))
		return true;	
	else
		return false; 
} else if ( $("#outcomeDescBox").val() != "" )
{
	if(confirm('Are you sure you want to update the record?'))
		return true;	
	else
		return false; 
} else if ( $("#outcomeBox").val() != "" )
{
	if(confirm('Are you sure you want to update the record?'))
		return true;	
	else
		return false; 
}	 else if ( $("#substatusBox").val() != "" )
{
	if(confirm('Are you sure you want to update the record?'))
		return true;	
	else
		return false; 
} else if ( $("#providerProgramBox").val() != "" )
{
	if(confirm('Are you sure you want to update the record?'))
		return true;	
	else
		return false; 
} else{	
	alert('You must select a new value from one of the menus.');
	return false;
	}
}

function clearCheckBoxes(){
	document.forms[0].outcomeBox.selectedIndex = 0;
	document.forms[0].statusBox.selectedIndex = 0;
	document.forms[0].substatusBox.selectedIndex = 0;
	document.forms[0].outcomeDescBox.selectedIndex = 0;
	document.forms[0].providerProgramBox.selectedIndex = 0;
}

</script>

</head>

<body onLoad="clearCheckBoxes()">


<html:form styleId="performUpdateReferral" action="/PerformUpdateReferral" onsubmit="return this;">

<div>
	
	<h2 align="center">Results for Referral Number = 
			<bean:write name="ProdSupportForm" property="referralNum" /></h2>
	     
<!-- Error Message Area -->
    <logic:notEqual name="ProdSupportForm" property="msg" value="">
	<table border="0" width="700" align="center">

	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<bean:write name="ProdSupportForm" property="msg" />
	 		</font></td>
	</tr>
	</table>
	</logic:notEqual>
<!-- End Error Message Area -->	     
	     
<logic:notEmpty	name="ProdSupportForm" property="juvprogrefs">
	<logic:iterate id="juvprogrefs" name="ProdSupportForm" property="juvprogrefs">
	<h3 align="center">Referral Details</h3>
	
	<table class="resultTbl" border="1" width="800" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVPROGREF_ID</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="juvenileProgramReferralId" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="caseFileId" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACKDATE</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="ackDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">BEGINDATE</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="beginDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ENDDATE</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="endDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSIGNEDHOURS</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="assignedHours" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SENTDATE</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="sentDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">STATUSCD</font></td>
		<td><font size="-1">
			<bean:write name="juvprogrefs" property="statusCd" />
			<input id="statusCd" type="hidden" value='<bean:write name="juvprogrefs" property="statusCd" />'/>
			&nbsp;</font></td>
		<td>  
			<html:select styleId="statusBox" name="ProdSupportForm" property="statusBox" style="width:250px">
				<html:option value="">Select a New Value</html:option>
				<html:optionsCollection name="ProdSupportForm" property="statusCodes" label="description" value="code" />
			</html:select>
		</td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SUBSTATUSCD</font></td>
		<td><font size="-1">
			<bean:write  name="juvprogrefs" property="statusSubCd" />
			<input id="statusSubCd" type="hidden" value='<bean:write  name="juvprogrefs" property="statusSubCd" />' />
			&nbsp;</font></td>
		<td>
			<html:select styleId="substatusBox" name="ProdSupportForm" property="substatusBox" style="width:250px">
				<html:option value="">Select a New Value</html:option>
				<html:option value="999">NONE (NO VALUE)</html:option>
				<html:optionsCollection name="ProdSupportForm" property="substatusCodes" label="description" value="code" />
			</html:select>
		</td>
	</tr>
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">OUTCOMECD</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="outcomeCd" />&nbsp;</font></td>
		<td>  
			<html:select styleId="outcomeBox" name="ProdSupportForm" property="outcomeBox" style="width:250px">
				<html:option value="">Select a New Value</html:option>
				<html:option value="999">NONE (NO VALUE)</html:option>
				<html:optionsCollection name="ProdSupportForm" property="outcomeCodes" label="description" value="code" />
			</html:select>
		</td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SPRVNOUTCOMEDESCCD</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="outcomeDescCd" />&nbsp;</font></td>
		<td>  
			<html:select styleId="outcomeDescBox" name="ProdSupportForm" property="outcomeDescBox" style="width:250px">
				<html:option value="">Select a New Value</html:option>
				<html:option value="999">NONE (NO VALUE)</html:option>
				<html:optionsCollection name="ProdSupportForm" property="outcomeDescCodes" label="description" value="code" />
			</html:select>
		</td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PROVPROGRAM_ID</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="providerProgramId" />&nbsp;</font></td>
		<td>  
			<html:select styleId="providerProgramBox" name="ProdSupportForm" property="providerPgmDescBox" style="width:250px">
				<html:option value="">Select a New Value</html:option>
				<html:optionsCollection name="ProdSupportForm" property="provProgramsList" label="programName" value="OID" />
			</html:select>
		</td>
	</tr>
	<%-- <tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PROVPROGRAM_ID</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="providerProgramId" />&nbsp;</font></td>
	</tr> --%>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LASTACTIONDATE</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="lastActionDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="createUserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATDATE</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="updateUser" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="createJIMS2UserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
		
	</table>
	</logic:iterate>
	</logic:notEmpty>
	
	<logic:empty name="ProdSupportForm" property="juvprogrefs">
	<br>
	<table align="center" border="1" width="700">
		<tr>
		<td>
	   <h3 align="center"><font color="green"><i>No Records Returned</i></font></h3>
	   </td>
	   </tr>
	   </table>
	</logic:empty>
		
	</div>
	

<table align="center"">
<tr>

<td>&nbsp;</td>

<logic:notEmpty	name="ProdSupportForm" property="juvprogrefs">
	<td>
	<p align="center">
	<input type="button" id="updateRecords" 
							value="Update Records"/>
	</p>
	</td>
</logic:notEmpty>
</tr>
</table>
</html:form>

<html:form action="/UpdateReferralQuery?clr=Y" onsubmit="return this;">
<table align="center"">
	<tr>
		<td>
		<html:submit value="Back to Query"/>
		</td>
	</tr>
</table>
</html:form>


</body>
</html:html>