<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/caseModifyJpoQueryConfirm.jsp</title>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script language="javascript">

function confirmUpdate(){

	if(confirm('Are you sure you want to delete the selected records?')) {
		spinner();
		return true;	
	} else {
		return false;
	}
}

</script>

</head>

<body onLoad="resetDates()">

<html:form action="/PerformModifyJpo" onsubmit="return this;">

<div>
	
	<h2 align="center">Update Casefile Modify JPO Confirmation
	</h2>
	<hr>
	     
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

<p align="center"><font color="green">The JPO for casefile <b><bean:write name="ProdSupportForm" property="casefileId" /></b> has been changed from 
<b><bean:write name="ProdSupportForm" property="previousJpoId" /></b> (<bean:write name="ProdSupportForm" property="previousJpoCode" />) to <b><bean:write name="ProdSupportForm" property="newJpoId" /></b> 
(<bean:write name="ProdSupportForm" property="newJpoCode" />).
</font></p>

<logic:notEmpty	name="ProdSupportForm" property="assnmnthists">
	
<p align="center"><i>In addition, the following records were found in connection to the casefile.<br>
If any of these records are now erroneous, check the box next to the record(s) and they will be deleted.</i></p>	     
	     
	<h3 align="center">Associated JPOAssnmntHists</h3>
	
	<table border="1" width="700" align="center">
	
	<tr>
		<!-- <td>&nbsp;</td> -->
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">JPOASSNMNTHIST_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">JPOASSNMNTDT</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">OFFICER_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">JPOUSERID</font></td>
	</tr>
	
	<logic:iterate id="assnmnthists" name="ProdSupportForm" property="assnmnthists">
	<tr>
		<td>
			<html:multibox property="selectedHists">
				<bean:write  name="assnmnthists" property="jpoAssignmentHistoryId" />
			</html:multibox>
				<bean:write  name="assnmnthists" property="jpoAssignmentHistoryId" />
			</td>
		<td><font size="-1"><bean:write  name="assnmnthists" property="jpoAssignmentDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assnmnthists" property="casefileId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assnmnthists" property="officerProfileId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assnmnthists" property="jpoOfficerUserId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	
	<logic:empty	name="ProdSupportForm" property="assnmnthists">
	<br>
	<table align="center" border="1" width="700">
		<tr>
		<td>
	   <h3 align="center"><font color="green"><i>No assignment records returned for this casefile. Nothing more needs to be done.</i></font></h3>
	   </td>
	   </tr>
	   </table>
	</logic:empty>
	
	<BR>
	
	<table align="center"">
	
	<logic:notEmpty	name="ProdSupportForm" property="assnmnthists">
		<td>
			<html:reset value="Reset Form Values" />
		</td>
	
	<td>
	<p align="center">
	<html:submit value="Delete Records" onclick="return confirmUpdate();" />
	</p>
	</td>
	</logic:notEmpty>

	</table>
	
	<logic:empty name="ProdSupportForm" property="casefiles">
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

<html:form action="/ModifyJpoQuery?clr=Y" onsubmit="return this;">
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