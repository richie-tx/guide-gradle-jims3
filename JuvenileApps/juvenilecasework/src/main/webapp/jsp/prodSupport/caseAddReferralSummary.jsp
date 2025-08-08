<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/caseAddReferralSummary.jsp</title>
</head>

<body>

<html:form method="post" action="/CaseAddReferralQuery" onsubmit="return this;">

<h2 align="center">Add Referral Summary</h2>
<hr>

<p align="center"><font color="green"><b>A 
<bean:write name="ProdSupportForm" property="newReferralNum" /> referral was successfully added.</b></font></p>
</html:form>


<p align="center"><b>The following is a list of the current referrals for casefile 
<bean:write name="ProdSupportForm" property="casefileId" />. This is for auditing purposes.<br> 
If this addition was made in error, please provide this list to the JIMS Data Team to remove it.</b></p>
<hr>

<logic:notEmpty	name="ProdSupportForm" property="assignments">	
<p>&nbsp;</p>
<h3 align="center">Referral Assignments</h3>
<table border="1" width="700" align="center">
	<p>&nbsp;</p>
	<h3 align="center">Existing Referral Assignments</h3>
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
	<tr>
		<td colspan="2" align="center">
		<html:form method="post" action="/MainMenu" onsubmit="return this;">
		<html:submit onclick="return this;" value="Back to Main Menu"/>
		</html:form>
		</td>
	</tr>
</table>    
    

</body>
</html:html>