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
<title>Juvenile Casework -/prodSupport/deleteIntakeHistQueryResult.jsp</title>
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

<h2 align="center">Delete Intake History Summary</h2>
<hr>

<html:form action="/DeleteIntakeHistoryRecord" onsubmit="return this;">
<body class="ContentFrameInjection">
<div>
	
	<h2 align="center">Results for Juvenile Id = 
			<bean:write name="ProdSupportForm" property="intakeHistoryRecord.juvenileNum" /></h2>
			
	<h3>For Record Deletion Click Delete Intake Histroy</h3>
	

	<h4 align="center"><i>The following Intake History record will be <font color="red">DELETED</font>.</i></h4>	     
	     
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
	
	<table class="resultTbl" align="center" border="1" width="850">
			<tr>
				<th>Intake History Id</th>
				<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.OID" /></td>
			</tr>
			<tr id="assmntType">
				<th>Assmnt Type</th>
				<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.assignmentType" /></td>
			</tr>
			<tr id="supervisionType">
				<th>Supervision Type Code</th>
				<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.supervisionTypeId" /></td>
			</tr>
			<tr id="assignmentDate">
				<th>Assignment Date</th>
				<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.assignmentDate" formatKey="date.format.mmddyyyy" /></td>
			</tr>
			<tr id="intakeDate">
				<th>Intake Date</th>
				<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.intakeDate" formatKey="date.format.mmddyyyy" /></td>
			</tr>
			<tr id="intakeDecision">
				<th>Intake Decision</th>
				<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.intakeDecisionId" /></td>
			</tr>
			<tr id="intakeJPO">
				<th>Intake JPO</th>
				<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.jpoId" /></td>
			</tr>
			<tr id="juvenileNum">
				<th>Juvenile Number</th>
				<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.juvenileNum" /></td>
			</tr>
			<tr id="referralNum">
				<th>Referral Number</th>
				<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.referralNumber" /></td>
			</tr>
			<tr>
				<th>Create User</th>
				<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.createUserID" /></td>
			</tr>
			<tr>
				<th>Create Date</th>
				<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.createTimestamp" /></td>
			</tr>
			<tr>
				<th>Update User</th>
				<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.updateUserID" /></td>
			</tr>
			<tr>
				<th>Update Date</th>
				<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.updateTimestamp" /></td>
			</tr>
			<tr>
				<th>Create Jims2 User</th>
				<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.createJIMS2UserID" /></td>
			</tr>
			<tr>
				<th>Update Jims2 User</th>
				<td><bean:write name="ProdSupportForm" property="intakeHistoryRecord.updateJIMS2UserID" /></td>
			</tr>
		</table>
	
	<BR>
	
	<table align="center"">
	
	<logic:notEmpty	name="ProdSupportForm" property="intakeHistoryRecord">
	<td>
	<p align="center">
	<html:submit value="Delete Intake History Record" onclick="return confirmDelete();" />
	</p>
	</td>
	</logic:notEmpty>

	</table>
	
	</logic:iterate>
	</logic:notEmpty>
	
	<logic:empty name="ProdSupportForm" property="intakeHistoryRecord">
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

<html:form action="/ViewIntakeHistoryRecordsQuery.do?deleteIntakHistory=true&clr=Y" onsubmit="return this;">
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