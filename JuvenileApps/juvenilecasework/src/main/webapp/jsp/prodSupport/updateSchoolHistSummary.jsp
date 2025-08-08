<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/updateSchoolHistSummary.jsp</title>
</head>

<body class="ContentFrameInjection">
	
<html:form method="post" action="/DeleteCasefileQuery" onsubmit="return this;">

<h2 align="center">Update School History Summary</h2>
<hr>

<p align="center"><font color="green"><b>SCHOOLHIST_ID 
<bean:write name="ProdSupportForm" property="schoolhistId" /> was successfully updated.</b></font></p>


<p align="center"><b>The following is a list of items affected by this change. This is for auditing purposes.<br> 
If this delete was made in error, please provide this list to the JIMS Data Team to restore the data.</b></p>
<hr>

	<logic:notEmpty	name="ProdSupportForm" property="schoolhists">
	<logic:iterate id="schoolhists" name="ProdSupportForm" property="schoolhists">
	<h3 align="center">School History Details</h3>
	
	<table border="1" width="800" align="center">
		
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SCHOOLHIST_ID</font></td>
		<td><font size="-1"><bean:write  name="schoolhists" property="schoolHistoryId" />&nbsp;</font></td>
	</tr>
	<tr>	
		<logic:notEqual name="ProdSupportForm" property="newAttDate" value="">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LASTATTENDEDDATE</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="newAttDate" />&nbsp;</font></td>
		<TD>
		<font color="red"><i>Date changed, previous value: </i><bean:write  name="schoolhists" property="lastAttendedDateString" /></font>
		</td>
		</logic:notEqual>
		<logic:equal name="ProdSupportForm" property="newAttDate" value="">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LASTATTENDEDDATE</font></td>
		<td><font size="-1"><bean:write  name="schoolhists" property="lastAttendedDateString" />&nbsp;</font></td>
		</logic:equal>	
	</tr>
	<tr>
		<logic:notEmpty name="ProdSupportForm" property="currentGradeBox">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CURRENTGRADECD</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="currentGradeBox" />&nbsp;</font></td>
		<td>
		<font color="red"><i>Code updated, previous value: </i>
		<bean:write  name="schoolhists" property="gradeLevelCode" /></font>
		</td>
		</logic:notEmpty>
		<logic:empty name="ProdSupportForm" property="currentGradeBox">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CURRENTGRADECD</font></td>
		<td><font size="-1"><bean:write  name="schoolhists" property="gradeLevelCode" />&nbsp;</font></td>
		</logic:empty>
	</tr>

	<tr>
		<logic:notEmpty name="ProdSupportForm" property="exitTypeBox">
			<td bgcolor="gray"><font color="white" face="bold" size="-1">EXITTYPECD</font></td>
			<logic:equal name="ProdSupportForm" property="exitTypeBox" value="NV">
				<logic:notEqual name="schoolhists" property="exitTypeCode" value="">
					<td><font size="-1"></font></td>
					<td>
						<font color="red"><i>Code updated, previous value: </i>
						<bean:write  name="schoolhists" property="exitTypeCode" /></font>
					</td>
				</logic:notEqual>
				<logic:equal name="schoolhists" property="exitTypeCode" value="">
					<td><font size="-1"></font></td>
				</logic:equal> 
			</logic:equal>
			<logic:notEqual name="ProdSupportForm" property="exitTypeBox" value="NV">
				<td><font size="-1"><bean:write  name="ProdSupportForm" property="exitTypeBox" />&nbsp;</font></td>
				<td>
					<font color="red"><i>Code updated, previous value: </i>
					<bean:write  name="schoolhists" property="exitTypeCode" /></font>
				</td>
			</logic:notEqual>
		</logic:notEmpty>
		<logic:empty name="ProdSupportForm" property="exitTypeBox">
			<td bgcolor="gray"><font color="white" face="bold" size="-1">EXITTYPECD</font></td>
			<td><font size="-1"><bean:write  name="schoolhists" property="exitTypeCode" />&nbsp;</font></td>
		</logic:empty>
	</tr>
	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="juvenileNum" />&nbsp;</font></td>
	</tr>
	
	<tr>
		<logic:notEmpty name="ProdSupportForm" property="appropGradeBox">
			<td bgcolor="gray"><font color="white" face="bold" size="-1">APPROPGRADECD</font></td>
			<logic:equal name="ProdSupportForm" property="appropGradeBox" value="NV">
				<logic:notEqual name="schoolhists" property="appropriateLevelCode" value="">
					<td><font size="-1"></font></td>
					<td>
						<font color="red"><i>Code updated, previous value: </i>
						<bean:write  name="schoolhists" property="appropriateLevelCode" /></font>
					</td>
				</logic:notEqual>
				<logic:equal name="schoolhists" property="appropriateLevelCode" value="">
					<td><font size="-1"></font></td>
				</logic:equal>
				
			</logic:equal>
			<logic:notEqual name="ProdSupportForm" property="appropGradeBox" value="NV">
				<td><font size="-1"><bean:write  name="ProdSupportForm" property="appropGradeBox" />&nbsp;</font></td>
				<td>
					<font color="red"><i>Code updated, previous value: </i>
					<bean:write  name="schoolhists" property="appropriateLevelCode" /></font>
				</td>
			</logic:notEqual>
			
		
		</logic:notEmpty>
		<logic:empty name="ProdSupportForm" property="appropGradeBox">
			<td bgcolor="gray"><font color="white" face="bold" size="-1">APPROPGRADECD</font></td>
			<td><font size="-1"><bean:write  name="schoolhists" property="appropriateLevelCode" />&nbsp;</font></td>
		</logic:empty>
	</tr>

	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SCHOOLCD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="schoolId" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SCHOOLDISTCD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="schoolDistrictId" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TEACODE</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="TEASchoolNumber" />&nbsp;</font></td>
	</tr>	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">VERIFIEDDATE</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="verifiedDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">GRADEAVG</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="gradeAverage" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">GRADEREPEATNUM</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="gradesRepeatNumber" />&nbsp;</font></td>
	</tr>
   
   <tr>
		<logic:notEmpty name="ProdSupportForm" property="gradesRepeatCodeBox">
			<td bgcolor="gray"><font color="white" face="bold" size="-1">GRADESREPEATCD</font></td>
			<logic:equal name="ProdSupportForm" property="gradesRepeatCodeBox" value="NV">
				<logic:notEqual name="schoolhists" property="gradesRepeatedCode" value="">
					<td><font size="-1"></font></td>
					<td>
						<font color="red"><i>Code updated, previous value: </i>
						<bean:write  name="schoolhists" property="gradesRepeatedCode" /></font>
					</td>
				</logic:notEqual>
				<logic:equal name="schoolhists" property="gradesRepeatedCode" value="">
					<td><font size="-1"></font></td>
				</logic:equal>
			</logic:equal>
			<logic:notEqual name="ProdSupportForm" property="gradesRepeatCodeBox" value="NV">
				<td><font size="-1"><bean:write  name="ProdSupportForm" property="gradesRepeatCodeBox" />&nbsp;</font></td>
				<td>
					<font color="red"><i>Code updated, previous value: </i>
					<bean:write  name="schoolhists" property="gradesRepeatedCode" /></font>
				</td>
			</logic:notEqual>
			
		</logic:notEmpty>
		<logic:empty name="ProdSupportForm" property="gradesRepeatCodeBox">
			<td bgcolor="gray"><font color="white" face="bold" size="-1">GRADESREPEATCD</font></td>
			<td><font size="-1"><bean:write  name="schoolhists" property="gradesRepeatedCode" />&nbsp;</font></td>
		</logic:empty>
	</tr>
	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PARTICIPATIONCD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="participationCode" />&nbsp;</font></td>
	</tr>			
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PGMATTENDINGCD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="programAttendingCode" />&nbsp;</font></td>
	</tr>	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RULEINFRACTIONCD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="ruleInfractionCode" />&nbsp;</font></td>
	</tr>		
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ATTSTATUSCD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="schoolAttendanceStatusCode" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TRUANCYHISTORY</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="truancyHistory" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">HOMESCHOOLDISTCD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="homeSchoolDistrictId" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">HOMESCHOOLCD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="homeSchoolId" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td><font size="-1"><bean:write  name="schoolhists" property="createUser" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATDATE</font></td>
		<td><font size="-1"><bean:write  name="schoolhists" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td><font size="-1"><bean:write  name="schoolhists" property="updateUser" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td><font size="-1"><bean:write  name="schoolhists" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="schoolhists" property="createJims2User" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="schoolhists" property="updateJims2User" />&nbsp;</font></td>
	</tr>
			
	</table>
	
	</logic:iterate>
	</logic:notEmpty>	
</html:form>

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