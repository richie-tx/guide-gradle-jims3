<!DOCTYPE HTML>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@page import="mojo.km.utilities.DateUtil"%>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/deleteIntakeHistQueryResultSummary.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/facilityProdSupport.js"></script>

</head>

<body>

<h2 align="center">Production Support - Delete Intake History Record Summary</h2>
<hr>

<p align="center"><font color="green"><b>Intake History Record 
<bean:write name="ProdSupportForm" property="detentionId" /> was successfully deleted.</b></font></p>
<p align="center">The following is the record affected by this change. This is for auditing purposes</p>


<hr>


<html:form action="/DeleteIntakeHistoryRecord" onsubmit="return this;">
<body class="ContentFrameInjection">
<div>
	     
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
	
	

	</div>
	

<table align="center"">
<tr>

<td>&nbsp;</td>

</tr>
</table>
</html:form>

<table align="center" border="0" width="500">
    <tr>
        <td colspan="2" align="center">
            <html:form method="post" action="/ViewIntakeHistoryRecordsQuery.do?deleteIntakHistory=true&clr=Y" onsubmit="return this;">
                <html:submit onclick="return this;" value="Back to Query/Search"/>
            </html:form>
        </td>
    </tr>    
    <tr><td colspan="2" align="center"></td></tr>
    <tr>
        <td colspan="2" align="center">
            <html:form method="post" action="/ViewIntakeHistoryRecordsQuery.do?deleteIntakHistory=true" onsubmit="return this;">
              <input type="hidden" name="juvenileId" value='<bean:write name="ProdSupportForm" property="intakeHistoryRecord.juvenileNum" />'/>
              <input type="hidden" name="referralId" value='<bean:write name="ProdSupportForm" property="intakeHistoryRecord.referralNumber" />'/>
		      <html:submit onclick="return this;" value="Back to Query Results"/>
		    </html:form>
        </td>
    </tr>
</table>



</body>
</html:html>