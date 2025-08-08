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
<title>Juvenile Casework -/prodSupport/caseAddReferralQueryResult.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script language="javascript">

function confirmAdd(){
	
	var referralNum = document.getElementById('refNum').value;
	 if (referralNum.length == 0){
	 	 alert("Referral Number is required");
		return false;
	 }
	 if (referralNum.length != 4) {
		 alert("Referral Number is  not valid");
	   return false; // keep form from submitting
	 }
	 if(referralNum > 3000){
		 
		 alert("Referral Number outside of allowed range.");
		 return false; // keep form from submitting
		 
	 }
	if(confirm('Are you sure you want to add a referral to this casefile?'))
	{
		spinner();
		return true;
	}
	else
		return false;
}
</script>

</head>

<html:form action="/PerformCaseAddReferral" onsubmit="return this;">

<input type="hidden" name="tableId" value="" />
	
	<h2 align="center">Add Referral Query Results</h2>
	<br>
	<h4 align="center"><i>The following rows exist for casefile 
	<bean:write name="ProdSupportForm" property="casefileId" />.<br/>New referrals will be created using the same Add Date, Service Unit Code, and Assignment Level as the oldest 
	referral.</i></h4>

<hr>

<logic:notEmpty	name="ProdSupportForm" property="assignments">	
	<p>&nbsp;</p>
	<h3 align="center">Existing Referral Assignments</h3>
		<table  class="resultTbl" border="1" width="700" align="center">
			<tr>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSIGNMENT_ID</font></td>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSMNTADDDATE</font></td>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSMNTTYPE</font></td>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUMBER</font></td>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">REFSEQNUM</font></td>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">SERVICEUNITCD</font></td>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSMNTLEVELCD</font></td>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSIGNBYUSERID</font></td>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEUSER</font></td>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEDATE</font></td>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
			</tr>
			<logic:iterate id="assignments" name="ProdSupportForm"
			property="assignments">
				<tr>	
					<td><font size="-1"><bean:write name="assignments" property="assignmentId" />&nbsp;</font></td>
					<td><font size="-1"><bean:write name="assignments" property="assignmentDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
					<td><font size="-1"><bean:write name="assignments" property="assignmentType" />&nbsp;</font></td>
					<td><font size="-1"><bean:write name="assignments" property="referralNum" />&nbsp;</font></td>
					<td><font size="-1"><bean:write name="assignments" property="refSeqNum" />&nbsp;</font></td>
					<td><font size="-1"><bean:write name="assignments" property="serviceUnitId" />&nbsp;</font></td>
					<td><font size="-1"><bean:write name="assignments" property="caseFileId" />&nbsp;</font></td>
					<td><font size="-1"><bean:write name="assignments" property="assessmentLevelId" />&nbsp;</font></td>
					<td><font size="-1"><bean:write name="assignments" property="jpoUserId" />&nbsp;</font></td>
					<td><font size="-1"><bean:write name="assignments" property="createUserID" />&nbsp;</font></td>
					<td><font size="-1"><bean:write name="assignments" property="createDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
					<td><font size="-1"><bean:write name="assignments" property="updateUser" />&nbsp;</font></td>
					<td><font size="-1"><bean:write name="assignments" property="updateDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
					<td><font size="-1"><bean:write name="assignments" property="createJIMS2UserID" />&nbsp;</font></td>
					<td><font size="-1"><bean:write name="assignments" property="updateJIMS2UserID" />&nbsp;</font></td>
				</tr>
			</logic:iterate>
		</table>
	<!-- DATA ENTRY AREA -->
	<table border="0" width="700" align="center">
		<tr>
		<td>&nbsp;</td>
		</tr>
		<tr>
		<td align="center" colspan="2">
			<b>New Referral Number:</b>&nbsp;<html:text property="newReferralNum" styleId="refNum" size="10" maxlength="15" />
		</td>
		</tr>
	</table>
	<!-- END DATA ENTRY AREA -->	
</logic:notEmpty>
 
<logic:empty name="ProdSupportForm" property="assignments">
	<table border="0" width="700" align="center"> 
		 <tr>
		 	<td align="center">   
		 		<i>No Result(s) Returned</i>
			</td>
		</tr>
	</table>
</logic:empty>
 
<table border="0" width="700" align="center">			
	<tr><td colspan="4">&nbsp;</td></tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	
	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<bean:write name="ProdSupportForm" property="msg" />
			</logic:notEqual> 
			</font>
			</td>
	</tr>

	<tr>
		<td>
		<p align="center">
			<input type="submit" name="Add Record" value="Add Record" onClick="return confirmAdd();">
		</p>
		</td>
	</tr>
</table>
</html:form>
<table border="0" width="250" align="center">
	<tr> 
	<td>
		<html:form action="/CaseAddReferralQuery?clr=Y">
			<input type="submit" name="details" value="Back to Query"/>
		</html:form>
	</td>
	<td>
		<html:form action="/MainMenu">
			<input type="submit"  value="Return to the Main Menu"/>
		</html:form>
	</td>
	</tr>
</table>


</html:html>