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
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<title>Juvenile Casework -/prodSupport/assignDeleteQueryResult.jsp</title>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>

<script language="javascript">
function confirmDelete(){
	if(confirm('Are you sure you want to delete the assignments chosen?'))
		{
			spinner();
			return true;	
		}
	else
		return false;
}
</script>

</head>

<body>

<h2 align="center">Production Support - Delete Assignments</h2>
<hr>

<p align="center">Select the assignment IDs from the list that you wish to delete.</p>

	<div align="center">
	<html:form method="post" action="/PerformAssignDelete" onsubmit="return this;">
	
	    <table border="0" width="700">
	
	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<bean:write name="ProdSupportForm" property="msg" />
			</logic:notEqual> 
			</font></td>
	</tr>
	</table>
	
	<logic:notEmpty	name="ProdSupportForm" property="assignments">

	<h3 align="center">Associated Assignments</h3>
	<h4 align="center">CasefileID: <bean:write  name="ProdSupportForm" property="casefileId" /></h4>
	
	<table border="1" width="700" align="center">
	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ASSIGNMENT_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">JPOUSERID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ASSMNTADDDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">REFERRALNUMBER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CASEFILEID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">SERVICEUNITCD</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ASSMNTLEVELCD</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="assignments" name="ProdSupportForm" property="assignments">
	<tr>
		
		<td>
		<html:multibox property="selectedAssignments">
				<bean:write  name="assignments" property="assignmentId" />
		</html:multibox>
				<bean:write  name="assignments" property="assignmentId" />
		</td>
		
		<td><font size="-1"><bean:write  name="assignments" property="jpoUserId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="assignmentDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="referralNum" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="caseFileId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="serviceUnitId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="assessmentLevelId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="createDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
		
	<table border="0" width="700">
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td align="center">
				<p align="center">
					<input type="submit" name="Delete Records" value="Delete Records" onClick="return confirmDelete();">
				</p>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
</html:form>
	<table border="0">
		<tr>
		<td>&nbsp;</td>
	
		<td align="center">
		<html:form method="post" action="/MainMenu" onsubmit="return this;">
			<html:submit onclick="return this;" value="Back to Main Menu"/>
		</html:form>
		</td>
		</tr>

    </table>
    
	</div>

</body>
</html:html>