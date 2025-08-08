<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/updateAssignmentSummary.jsp</title>
</head>

<body>
	
<html:form method="post" action="/UpdateAssignmentQuery" onsubmit="return this;">
<h2 align="center">Update Assignment Date Summary</h2>
<hr>
<p align="center"><font color="green"><b>Assignment ID 
<bean:write name="ProdSupportForm" property="assignmentId" /> was successfully updated.</b></font></p>

<p align="center"><b>The following is a list of records affected by this change. This is for auditing purposes.<br> 
If this delete was made in error, please provide this list to the JIMS Data Team to restore the data.</b></p>
<hr>

<logic:notEmpty	name="ProdSupportForm" property="assignments">
 <logic:iterate id="assignments" name="ProdSupportForm" property="assignments">
  <table border="1" width="700" align="center">	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ASSIGNMENT_ID</font></td>
		<td><font size="-1"><bean:write name="assignments" property="assignmentId"/>&nbsp;</font></td>
	</tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">JPOUSERID</font></td>
		<td><font size="-1"><bean:write name="assignments" property="jpoUserId"/>&nbsp;</font></td>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ASSMNTADDDATE</font></td>
		<td><font size="-1"><bean:write name="assignments" property="assignmentDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	<logic:notEqual name="ProdSupportForm" property="newActDate" value="">	
		<td><font color="red"><i>Updated, previous value: </i><bean:write name="ProdSupportForm" property="oldAssignmentDate" /></font></td>	
	</logic:notEqual>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ASSMNTTYPE</font></td>
		<td><font size="-1"><bean:write name="assignments" property="assignmentType"/>&nbsp;</font></td>
	<logic:notEqual name="ProdSupportForm" property="referralAssmntType" value="">
		<td><font color="red"><i>Updated, previous value: </i><bean:write name="ProdSupportForm" property="oldRefAssmntType" /></font></td>	
	</logic:notEqual>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">REFERRALNUMBER</font></td>
		<td><font size="-1"><bean:write name="assignments" property="referralNum"/>&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">REFSEQNUM</font></td>
		<td><font size="-1"><bean:write name="assignments" property="refSeqNum"/>&nbsp;</font></td>
	<logic:notEqual name="ProdSupportForm" property="refSeqNum" value="">
		<td><font color="red"><i>Updated, previous value: </i><bean:write name="ProdSupportForm" property="oldRefSeqNum" /></font></td>	
	</logic:notEqual>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CASEFILEID</font></td>
		<td><font size="-1"><bean:write name="assignments" property="caseFileId"/>&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">SERVICEUNITCD</font></td>
		<td><font size="-1"><bean:write name="assignments" property="serviceUnitId"/>&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ASSMNTLEVELCD</font></td>
		<td><font size="-1"><bean:write name="assignments" property="assessmentLevelId"/>&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td><font size="-1"><bean:write name="assignments" property="createUserID"/>&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td><font size="-1"><bean:write name="assignments" property="createDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td><font size="-1"><bean:write name="assignments" property="updateUser"/>&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td><font size="-1"><bean:write name="assignments" property="updateDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write name="assignments" property="createJIMS2UserID"/>&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write name="assignments" property="updateJIMS2UserID"/>&nbsp;</font></td>
	</tr>
  </table>
 </logic:iterate>		
</logic:notEmpty>
</html:form>

<table align="center" border="0" width="500">
	<tr>
	<td align="right">
		<html:form action="/MainMenu.do?selectedMenuItem=assignupdate">
			<input type="submit" name="details" value="Back to Query"/>
		</html:form>
	</td>
	<td colspan="2" align="left">
	<html:form method="post" action="/MainMenu" onsubmit="return this;">
	<html:submit onclick="return this;" value="Back to Main Menu"/>
	</html:form>
	</td>
	</tr>
   </table> 
    
</body>
</html:html>