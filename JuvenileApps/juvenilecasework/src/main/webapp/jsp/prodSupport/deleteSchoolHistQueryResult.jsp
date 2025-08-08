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
<title>Juvenile Casework -/prodSupport/deleteSchoolHistQueryResult</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script language="javascript">

	function confirmDelete(){
		
			if(confirm('Are you sure you want to delete the record?'))
			{
				spinner();
				return true;
			}
			else
				return false;	
	}	
	
	</script>

</head>

<html:form action="/PerformDeleteSchoolHist" onsubmit="return this;">
<body class="ContentFrameInjection">
<div>
	
	<h2 align="center">Results for SCHOOLHIST_ID = 
			<bean:write name="ProdSupportForm" property="schoolhistId" /></h2>
	     
<!-- Error Message Area -->
    <logic:notEqual name="ProdSupportForm" property="msg" value="">
	<table  border="0" width="700" align="center">

	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<bean:write name="ProdSupportForm" property="msg" />
	 		</font></td>
	</tr>
	</table>
	</logic:notEqual>
<!-- End Error Message Area -->	     
	     
	<h4 align="center"><i>The following record will be <font color="red">DELETED</font>.</i></h4>	     
	     
<logic:notEmpty	name="ProdSupportForm" property="schoolhists">
	<logic:iterate id="schoolhists" name="ProdSupportForm" property="schoolhists">
	
	<table class="resultTbl" border="1" width="800" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SCHOOLHIST_ID</font></td>
		<td><font size="-1"><bean:write  name="schoolhists" property="schoolHistoryId" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LASTATTENDEDDATE</font></td>
		<td><font size="-1"><bean:write  name="schoolhists" property="lastAttendedDateString" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CURRENTGRADECD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="gradeLevelCode" />&nbsp;</font></td>
	</tr>
	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">EXITTYPECD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="exitTypeCode" />&nbsp;</font></td>
	</tr>

	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="juvenileNum" />&nbsp;</font></td>
	</tr>
	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">APPROPGRADECD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="appropriateLevelCode" />&nbsp;</font></td>
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
		<td bgcolor="gray"><font color="white" face="bold" size="-1">GRADESREPEATCD</font></td>
		<td><font size="-1"><bean:write name="schoolhists" property="gradesRepeatedCode" />&nbsp;</font></td>
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
	
	<BR>
	
	<table align="center"">
	
	<logic:notEmpty	name="ProdSupportForm" property="schoolhists">
	<td>
	<p align="center">
	<html:submit value="Delete Record" onclick="return confirmDelete();" />
	</p>
	</td>
	</logic:notEmpty>

	</table>
	
	</logic:iterate>
	</logic:notEmpty>
	
	<logic:empty name="ProdSupportForm" property="schoolhists">
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

<html:form action="/DeleteSchoolHistQuery?clr=Y" onsubmit="return this;">
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