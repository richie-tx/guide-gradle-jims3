<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/deleteMaysiAssessSummary.jsp</title>
</head>

<body>
	
<h2 align="center">Delete MAYSI Assessment Summary</h2>
<hr>

<p align="center"><font color="green"><b>MAYSI Assessment was successfully deleted.</b></font></p>

<p align="center"><b>The following is a list of records affected by this change. This is for auditing purposes.<br> 
If this modification was made in error, please provide this list to the JIMS Data Team to restore the data.</b></p>
<hr>

<html:form action="/PerformUpdateMaysiAssess" onsubmit="return this;">
<br/>

<logic:notEmpty name="ProdSupportForm" property="maysis">
<logic:iterate id="maysis" name="ProdSupportForm" property="maysis">
	
	<table border="1" width="800" align="center">

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">MAYSIASSESSMNT_ID</font></td>
		<td><font size="-1"><bean:write name="maysis" property="assessmentId" />&nbsp;</font></td>
	</tr>

	<tr>
	  <logic:notEmpty name="ProdSupportForm" property="assessoptionBox">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSESSOPTION</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="assessoptionBox" />&nbsp;</font></td>
		<td><font color="red"><i>Updated, previous value: </i><bean:write  name="maysis" property="assessmentOption" /></font></td>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="assessoptionBox">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSESSOPTION</font></td>
		<td><font size="-1"><bean:write  name="maysis" property="assessmentOption" />&nbsp;</font></td>
	  </logic:empty>
	</tr>		
					
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSESSOFFICER_ID</font></td>
		<td><font size="-1"><bean:write name="maysis" property="assessOfficerId" />&nbsp;</font></td>
	</tr>
			
	<tr>
	 <logic:notEmpty name="ProdSupportForm" property="newBeginDate">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSESSDATE</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="newBeginDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font color="red"><i>Updated, previous value: </i><bean:write  name="maysis" property="assessmentDate" formatKey="date.format.mmddyyyy" /></font></td>
	 </logic:notEmpty>
	 
	 <logic:empty name="ProdSupportForm" property="newBeginDate">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSESSDATE</font></td>
		<td><font size="-1"><bean:write  name="maysis" property="assessmentDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	 </logic:empty>
	</tr>		
					
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUMBER</font></td>
		<td><font size="-1"><bean:write name="maysis" property="referralNumber" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">HASPREVIOUSMAYSI</font></td>
		<td><font size="-1"><bean:write name="maysis" property="hasPreviousMAYSI" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISADMINISTERED</font></td>
		<td><font size="-1"><bean:write name="maysis" property="administered" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVLOCUNIT_ID</font></td>
		<td><font size="-1"><bean:write name="maysis" property="locationUnitId" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LENGTHOFSTAY</font></td>
		<td><font size="-1"><bean:write name="maysis" property="lengthOfStayId" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">FACILITYTYPECD</font></td>
		<td><font size="-1"><bean:write name="maysis" property="facilityTypeId" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
		<td><font size="-1"><bean:write name="maysis" property="juvenileNum" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">REASONNOTDONE</font></td>
		<td><font size="-1"><bean:write name="maysis" property="reasonNotDoneId" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">GENDERCD</font></td>
		<td><font size="-1"><bean:write name="maysis" property="sexId" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RACECD</font></td>
		<td><font size="-1"><bean:write name="maysis" property="raceId" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TESTAGE</font></td>
		<td><font size="-1"><bean:write name="maysis" property="testAge" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td><font size="-1"><bean:write name="maysis" property="createUser" />&nbsp;</font></td>
	</tr>
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td><font size="-1"><bean:write name="maysis" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td><font size="-1"><bean:write name="maysis" property="updateUser" />&nbsp;</font></td>
	</tr>
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td><font size="-1"><bean:write name="maysis" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write name="maysis" property="createJims2User" />&nbsp;</font></td>
	</tr>
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write name="maysis" property="updateJims2User" />&nbsp;</font></td>
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