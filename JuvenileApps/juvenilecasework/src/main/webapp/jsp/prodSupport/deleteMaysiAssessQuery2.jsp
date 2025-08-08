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
<title>Juvenile Casework -/prodSupport/deleteMaysiAssessQuery2.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>

<script language="javascript">

function fillId(value)
{
	document.forms[0].maysiassessmntId.value = value;
	return true;
}
</script>

</head>

<body>

<h2 align="center">Production Support - Assessment Query</h2>
<hr>

<p align="center">Select an assignment from the list and click SUBMIT to view current data.<br/> 
	No records will be deleted until confirmed on the next page.</p>

	<div align="center">
	<html:form method="post" action="/DeleteMaysiAssessQuery?edit=Y" onsubmit="return this;">
	
<logic:notEmpty name="ProdSupportForm" property="maysis">

	<p>&nbsp;</p>

    <table border="1" width="750" align="center">

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">MAYSIASSESSMNT_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSESSOPTION</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSESSOFFICER_ID</font></td>				
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSESSDATE</font></td>				
		<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUMBER</font></td>						
		<td bgcolor="gray"><font color="white" face="bold" size="-1">HASPREVIOUSMAYSI</font></td>				
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISADMINISTERED</font></td>		
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVLOCUNIT_ID</font></td>				
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LENGTHOFSTAY</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">FACILITYTYPECD</font></td>		
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">REASONNOTDONE</font></td>				
		<td bgcolor="gray"><font color="white" face="bold" size="-1">GENDERCD</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RACECD</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TESTAGE</font></td>
	</tr>				

	<logic:iterate id="maysis" name="ProdSupportForm" property="maysis">
						
	<tr>
		<td>
	 		<input type="radio" name="radioProp" value="<bean:write name="maysis" property="assessmentId"/>"
	 		onclick="fillId(<bean:write name="maysis" property="assessmentId"/>)">
	 		<bean:write name="maysis" property="assessmentId"/>
		</td>	
		
		<td><font size="-1"><bean:write name="maysis" property="assessmentOption" />&nbsp;</font></td>
		<td><font size="-1"><bean:write name="maysis" property="assessOfficerId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write name="maysis" property="assessmentDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write name="maysis" property="referralNumber" />&nbsp;</font></td>	
		<td><font size="-1"><bean:write name="maysis" property="hasPreviousMAYSI" />&nbsp;</font></td>	
		<td><font size="-1"><bean:write name="maysis" property="administered" />&nbsp;</font></td>			
		<td><font size="-1"><bean:write name="maysis" property="locationUnitId" />&nbsp;</font></td>		
		<td><font size="-1"><bean:write name="maysis" property="lengthOfStayId" />&nbsp;</font></td>		
		<td><font size="-1"><bean:write name="maysis" property="facilityTypeId" />&nbsp;</font></td>		
		<td><font size="-1"><bean:write name="maysis" property="juvenileNum" />&nbsp;</font></td>
		<td><font size="-1"><bean:write name="maysis" property="reasonNotDoneId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write name="maysis" property="sexId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write name="maysis" property="raceId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write name="maysis" property="testAge" />&nbsp;</font></td>

	</tr>
	
	</logic:iterate>
  </table>
</logic:notEmpty>

<logic:empty name="ProdSupportForm" property="maysis">
	<table border="1" width="700" align="center">
		<tr>
			<td><h3 align="center"><i>No assessments found.</i></h3></td>
		</tr>
	</table>
</logic:empty>

	&nbsp;
	
<table border="0" width="700">
	<tr>
		<td align="right">
		<font face="" style="FONT-FAMILY: Arial" color="#0000aa"><strong> Selected Assessment:</strong></font>
		</td>
		<td><html:text property="maysiassessmntId" size="10" maxlength="15" readonly="true"/>
	</tr>

	<tr>
		<td>&nbsp;</td>
		<td><html:submit onclick="spinner();" value="Submit" /></td>
	</tr>

	<tr>
		<td>&nbsp;</td>
	</tr>
</table>
</html:form>
	
	<table border="0">
		<tr>
		<td align="center">
		<html:form method="post" action="/DeleteMaysiAssessQuery?clr=Y" onsubmit="return this;">
		<html:submit onclick="return this;" value="Search a Different Record"/>
		</html:form>
		</td>
	
		<td>&nbsp;</td>
	
		<td align="center">
		<html:form method="post" action="/MainMenu" onsubmit="return this;">
		<html:submit onclick="return this;" value="Back to Main Menu"/>
		</html:form>
		</td>
		</tr>

    </table>
    
    <table border="0" width="700">
	
	<tr><td colspan="4">&nbsp;</td></tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	
	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<bean:write name="ProdSupportForm" property="msg" />
			</logic:notEqual> 
			</font></td>
	</tr>
	</table>
    
	</div>

</body>
</html:html>