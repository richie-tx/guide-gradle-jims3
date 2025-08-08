<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/servEventModifySummary.jsp</title>
</head>

<body>
	
<html:form method="post" action="/DeleteCasefileQuery" onsubmit="return this;">

<h2 align="center">Service Event Attendance Modification Summary</h2>
<hr>

<p align="center"><font color="green"><b>Service Event ID  
<bean:write name="ProdSupportForm" property="serveventId" /> was successfully updated.</b></font></p>


<p align="center"><b>The following is a list of records affected by this change. This is for auditing purposes.<br> 
If this modification was made in error, please provide this list to the JIMS Data Team to restore the data.</b></p>
<hr>
	<logic:notEmpty name="ProdSupportForm" property="servattends">

		<p>&nbsp;</p>


		<logic:iterate id="servattends" name="ProdSupportForm"
			property="servattends">
			<table border="1" width="750" align="center">

				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">SERVATTEND_ID</font></td>
					<td><font size="-1"><bean:write name="servattends"
						property="serviceEventAttendanceId" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">SERVEVENT_ID</font></td>
					<td><font size="-1"><bean:write name="servattends"
						property="serviceEventId" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
					<td><font size="-1"><bean:write name="servattends"
						property="juvenileId" />&nbsp;</font></td>
				</tr>
				
		<tr>
		<logic:notEmpty name="ProdSupportForm" property="attendstatusBox">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ATTENDSTATUSCD</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="attendstatusBox" />&nbsp;</font></td>
		<td>
		<font color="red"><i>Code updated, previous value: </i><bean:write  name="servattends" property="attendanceStatusCd" /></font>
		</td>
		</logic:notEmpty>
		<logic:empty name="ProdSupportForm" property="attendstatusBox">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ATTENDSTATUSCD</font></td>
		<td><font size="-1"><bean:write  name="servattends" property="attendanceStatusCd" />&nbsp;</font></td>
		</logic:empty>
		</tr>
		<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">PROGRESSNOTES</font></td>
					<td><font size="-1"><bean:write name="servattends"
						property="progressNotes" />&nbsp;</font></td>
				</tr>
		<tr>
		<logic:notEmpty name="ProdSupportForm" property="newAddlAttendees">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ADDLATTENDEES</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="newAddlAttendees" />&nbsp;</font></td>
		<td>
		<font color="red"><i>Updated, previous value: </i><bean:write  name="servattends" property="addlAttendees" /></font>
		</td>
		</logic:notEmpty>
		<logic:empty name="ProdSupportForm" property="newAddlAttendees">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ADDLATTENDEES</font></td>
		<td><font size="-1"><bean:write  name="servattends" property="addlAttendees" />&nbsp;</font></td>
		</logic:empty>
		</tr>		
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
					<td><font size="-1"><bean:write name="servattends"
						property="createUser" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
					<td><font size="-1"><bean:write name="servattends"
						property="createDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEUSER</font></td>
					<td><font size="-1"><bean:write name="servattends"
						property="updateUser" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEDATE</font></td>
					<td><font size="-1"><bean:write name="servattends"
						property="updateDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
					<td><font size="-1"><bean:write name="servattends"
						property="createJims2User" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
					<td><font size="-1"><bean:write name="servattends"
						property="updateJims2User" />&nbsp;</font></td>
				</tr>
			</table>
		</logic:iterate>
	</logic:notEmpty>

</html:form>

	<table align="center" border="0" width="500">
		<tr>
		<td>&nbsp;</td>
		</tr>
		<tr>
		<td colspan="2" align="center">
		<html:form method="post" action="/ModifyServEventQuery.do?clr=Y" onsubmit="return this;">
		<html:submit onclick="return this;" value="Update Another Event"/>
		</html:form>
		</td>
		</tr>
    </table> 

	<table align="center" border="0" width="500">
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