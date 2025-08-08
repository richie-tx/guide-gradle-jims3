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
<title>Juvenile Casework -/prodSupport/caseModifyJpoQueryResult.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>

<script language="javascript">

$(document).ready(function(){
	$("#updateJPOBtn").click(function(){
		if ( confirmUpdate() ) {
			spinner();
			$("#modifyJpoRetrieveAssignments").submit();
		}
	})
})

function confirmUpdate(){
	if (document.forms[0].newJpoId.value != "")
	{

	if(confirm('Are you sure you want to update the JPO for this casefile?'))
			return true;
		else
			return false;
	}
	else
	{
	alert("You must enter a new value for the OfficerID column.");
	return false;
	}
}

function resetForm(){
	document.forms[0].newJpoId.value = "";
	document.forms[0].newJpoId.focus();
}

</script>

</head>

<body onLoad="resetDates()">

<html:form styleId ="modifyJpoRetrieveAssignments" action="/ModifyJpoRetrieveAssignments" onsubmit="return this;">

<div>
	
	<h2 align="center">Results for Casefile ID = 
			<bean:write name="ProdSupportForm" property="casefileId" /></h2>
	     
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
	     
<p align="center"><b><i>Enter a new Juvenile Probation Officer to update the record.</i></b></p>
	     
<logic:notEmpty	name="ProdSupportForm" property="casefiles">
	<logic:iterate id="casefiles" name="ProdSupportForm" property="casefiles">
	
	<table class="resultTbl" border="1" width="800" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="supervisionNum" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="juvenileNum" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASESTATUSCD</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="caseStatusId" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACTIVATIONDATE</font></td>
		<td><font size="-1"><bean:write name="casefiles" property="activationDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SUPRVSIONENDDATE</font></td>
		<td><font size="-1"><bean:write name="casefiles" property="supervisionEndDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SPRVSIONTYPECD</font></td>
		<td><font size="-1"><bean:write name="casefiles" property="supervisionTypeId" />&nbsp;</font></td>
	</tr>
	<tr>	
<!-- I'm using the JPO User ID here, not the actual OFFICER_ID -->
	<td bgcolor="gray"><font color="white" face="bold" size="-1">OFFICER ID</font></td>
		<td><font size="-1"><bean:write name="casefiles" property="probationOfficeId" />&nbsp;</font></td>
	</tr>
	<td bgcolor="gray"><font color="white" face="bold" size="-1">OFFICER UV CODE</font></td>
		<td><font size="-1"><bean:write name="casefiles" property="probationOfficerLogonId" />&nbsp;</font></td>
		<td><i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="newJpoId" size="10" maxlength="10" value = "" /></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVSEQNUM</font></td>
		<td><font size="-1"><bean:write name="casefiles" property="sequenceNum" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISMAYSINEEDED</font></td>
		<td>
		<logic:equal name="casefiles" property="isMAYSINeeded" value="true">
			<font size="-1">True</font>
		</logic:equal>
		<logic:notEqual name="casefiles" property="isMAYSINeeded" value="true">
			<font size="-1">False</font>
		</logic:notEqual>
		</td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISRISKREFRNEEDED</font></td>
		<td>
		<logic:equal name="casefiles" property="referralRiskNeeded" value="true">
			<font size="-1">True</font>
		</logic:equal>
		<logic:notEqual name="casefiles" property="referralRiskNeeded" value="true">
			<font size="-1">False</font>
		</logic:notEqual>
		</td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISRISKINTVNEEDED</font></td>
		<td>
		<logic:equal name="casefiles" property="interviewRiskNeeded" value="true">
			<font size="-1">True</font>
		</logic:equal>
		<logic:notEqual name="casefiles" property="interviewRiskNeeded" value="true">
			<font size="-1">False</font>
		</logic:notEqual>
		</td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISRISKTESTNEEDED</font></td>
		<td>
		<logic:equal name="casefiles" property="testingRiskNeeded" value="true">
			<font size="-1">True</font>
		</logic:equal>
		<logic:notEqual name="casefiles" property="testingRiskNeeded" value="true">
			<font size="-1">False</font>
		</logic:notEqual>
		</td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISRISKCOMMNEEDED</font></td>
		<td>
		<logic:equal name="casefiles" property="communityRiskNeeded" value="true">
			<font size="-1">True</font>
		</logic:equal>
		<logic:notEqual name="casefiles" property="communityRiskNeeded" value="true">
			<font size="-1">False</font>
		</logic:notEqual>
		</td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISRISKRESINEEDED</font></td>
		<td>
		<logic:equal name="casefiles" property="residentialRiskNeeded" value="true">
			<font size="-1">True</font>
		</logic:equal>
		<logic:notEqual name="casefiles" property="residentialRiskNeeded" value="true">
			<font size="-1">False</font>
		</logic:notEqual>
		</td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISRISKPROGNEEDED</font></td>
		<td>
		<logic:equal name="casefiles" property="progressRiskNeeded" value="true">
			<font size="-1">True</font>
		</logic:equal>
		<logic:notEqual name="casefiles" property="progressRiskNeeded" value="true">
			<font size="-1">False</font>
		</logic:notEqual>
		</td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISBENASMNTNEEDED</font></td>
		<td>
		<logic:equal name="casefiles" property="benefitsAssessmentNeeded" value="true">
			<font size="-1">True</font>
		</logic:equal>
		<logic:notEqual name="casefiles" property="benefitsAssessmentNeeded" value="true">
			<font size="-1">False</font>
		</logic:notEqual>
		</td>
	</tr>
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEPLAN_ID</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="supervisionNum" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CONTROLLING_REFERRAL_NUM</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="controllingReferralId" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="createUserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATDATE</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="updateUser" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="createJIMS2UserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
			
	</table>
	
	<BR>
	
	<table align="center"">
	
	<logic:notEmpty	name="ProdSupportForm" property="casefiles">
	<tr>
	<td>
	<html:reset value="Reset Form Values" onclick="resetForm();" />
	</td>
	<td align="center">
	<input style="width: 175px" type="button" id="updateJPOBtn" value="Update JPO"/>
	</td>
	</tr>
	</logic:notEmpty>

	</table>
	
	</logic:iterate>
	</logic:notEmpty>
	
	<logic:empty name="ProdSupportForm" property="casefiles">
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

</tr>
</table>
</html:form>

<html:form action="/ModifyJpoQuery?clr=Y" onsubmit="return this;">
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