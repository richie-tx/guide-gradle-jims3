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
<title>Juvenile Casework -/prodSupport/updateReferralDateQueryResult.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/updateReferralDate.js"></script>
<script language="javascript">
$(document).ready(function(){
	$("#updateRecords").click(function(){
		if ( confirmUpdate() ) {
			spinner();
			$("#performUpdateReferralDate").submit();
		}
	})
})

function endDateIsEmpty(){
	
	if (document.forms[0].refEnddate==null )
		return true;
	else
		return false;
}

function beginDateIsEmpty(){
	
	if (document.forms[0].refBegindate==null )
		return true;
	else
		return false;
}
function verifyDates(){

	if (!endDateIsEmpty())
	return true;
	
	else if (!beginDateIsEmpty())
	return true;

	else
		return false;
}

function isInt(value){
	
    return !isNaN(parseInt(value)) && (parseFloat(value) == parseInt(value)); 
}

function verifyCasefile(){

	if (document.forms[0].newcasefileId==null||document.forms[0].newcasefileId.value=='')	
		return false;
	else
		{
		if (isInt(document.forms[0].newcasefileId.value))
			return true;
		else{
			alert('New casefile must be an integer.');
			return false;
			}
		}
}

function confirmUpdate(){

	if (verifyDates())
	{
		if(confirm('Are you sure you want to update the referral?'))
			return true;	
		else
			return false;
	}
	else if (verifyCasefile())
	{
		if(confirm('Are you sure you want to update the referral?'))
			return true;	
		else
			return false;
	}
	else 
		{
		alert("You must enter or select a new value.");
		return false;
		}
}

function setCurrentDate()
{
	var today = new Date();
	
	document.forms[0].beginmonth.selectedIndex = today.getMonth() + 1;
	document.forms[0].beginday.selectedIndex = today.getDate();
	
	for (var y=0; y<document.forms[0].beginyear.options.length; y++)
	{
		if (document.forms[0].beginyear.options[y].value == today.getFullYear()) 
		{
			document.forms[0].beginyear.selectedIndex = y;
			break;
		}
	}
}	

</script>

</head>

<body>

<html:form styleId="performUpdateReferralDate" action="/PerformUpdateReferralDate" onsubmit="return this;">

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
	     
<p align="center"><b><i>Choose a new date for one or both date fields. <br>
To clear a date field, choose the BLANK option for all three drop-downs.</i></b></p>	     
	     
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
		<td>New Value:&nbsp;<html:text property="newcasefileId" size="10" maxlength="15" /></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PROGRAM REFERRAL ASSIGNMENT DATE</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="programReferralAssignmentDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td>
	 		<font color="red">New value:&nbsp;</font>
	   		<html:text name="ProdSupportForm" property="programReferralAssignmentDate" size="10" maxlength="10" styleId="programReferralAssignmentDate"/> 
    	</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CONTROLLING REFERRAL</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="controllingReferralNum" />&nbsp;</font></td>
		<td>
	 		<font color="red">New value:&nbsp;</font>
	   		<html:select name="ProdSupportForm" property="newControllingReferral" 
	   											styleId="newControllingReferral" 
	   											>
				<html:option key="select.generic" value="" />
				<html:optionsCollection name="ProdSupportForm" 
										property="controllingReferrals" 
										value="referralNum" 
										label="referralNum"/> 				
			</html:select> 
    	</td>
    		
	</tr>	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACKDATE</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="ackDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td>
	 		<font color="red">New value:&nbsp;</font>
	   		<html:text name="ProdSupportForm" property="refAckDate" size="10" maxlength="10" styleId="refAckdate"/> 
    	</td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">BEGINDATE</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="beginDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
	  	<td>
	   		<font color="red">New value:&nbsp;</font>
	   		<html:text name="ProdSupportForm" property="refBeginDate" size="10" maxlength="10" styleId="refBegindate"/> 
    	</td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ENDDATE</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="endDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td>
	 		<font color="red">New value:&nbsp;</font>
	   		<html:text name="ProdSupportForm" property="refEndDate" size="10" maxlength="10" styleId="refEnddate"/> 
    	</td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSIGNEDHOURS</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="assignedHours" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SENTDATE</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="sentDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td>
	 		<font color="red">New value:&nbsp;</font>
	   		<html:text name="ProdSupportForm" property="refSentDate" size="10" maxlength="10" styleId="refSentdate"/> 
    	</td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">FUND SOURCE</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="fundSource"  />&nbsp;</font></td>
    	<td>
	 		<font color="red">New value:&nbsp;</font>
	   		<html:select name="ProdSupportForm" property="newFundSource" styleId="newFundSource">
				<html:option key="select.generic" value="" />
				<html:optionsCollection name="ProdSupportForm" property="juvSourceFunds" value="code" label="code"/> 				
			</html:select> 
    	</td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">STATUSCD</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="statusCd" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SUBSTATUSCD</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="statusSubCd" />&nbsp;</font></td>
	</tr>
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">OUTCOMECD</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="outcomeCd" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PROVPROGRAM_ID</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="providerProgramId" />&nbsp;</font></td>
	</tr>
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
	
	<BR>
	
	<table align="center"">
	
	<logic:notEmpty	name="ProdSupportForm" property="juvprogrefs">
	<td>
	<p align="center">
	<input type="button" id="updateRecords" value="Update Records" />
	</p>
	</td>
	<td>
	<html:reset value="Reset Form Values" />
	</td>
	</logic:notEmpty>

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

</tr>
</table>
</html:form>

<html:form action="/UpdateReferralDateQuery?clr=Y" onsubmit="return this;">
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