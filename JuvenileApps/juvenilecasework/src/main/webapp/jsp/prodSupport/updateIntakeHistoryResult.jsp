<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.Features" %>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<!DOCTYPE HTML>

<head>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<style>
	#container{
		text-align: center;
	}
	
	#container div {
			margin-top: 15px;
	}
	
	
	#notice {
		color: green;
	}
	
	table {
	 	border: 1px solid black;
	 	width: 500px;
	 	margin-top: 20px;
		margin-left: auto;
		margin-right: auto;
	 }
	 
	 table  th {
	 	font-family: Geneva, Arial, Helvetica, sans-serif;
		font-size: small;
		font-weight: bold;
		color: #000000;
		background-color: #cccccc;
		padding-right:5px;
		text-align:left;
	 }
	 
	 table th, td {
	 	border: 1px solid black;
	 	text-align: center;
	 }
	 
	 #command div {
		display: inline-block;
		margin-left: 10px;
		margin-right: 10px;
	}
	 
	 
</style>
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script>
	$(document).ready(function(){
		var originAssignmentType= '<bean:write name="ProdSupportForm" property="originalAssmntType"/>';
		var originSupervisionTypeCode = '<bean:write name="ProdSupportForm" property="originalSupervisionType" />';
		var originAssignmentDate = '<bean:write name="ProdSupportForm" property="originalAssignmenDate" formatKey="date.format.mmddyyyy" />';
		var originIntakeDate = '<bean:write name="ProdSupportForm" property="originalIntakeDate" formatKey="date.format.mmddyyyy" />';
		var originIntakeDecision = '<bean:write name="ProdSupportForm" property="originalIntakeDecision" />';
		var originIntakeJPO = '<bean:write name="ProdSupportForm" property="originalIntakeJPO" />';
		var originJuvenileNumber = '<bean:write name="ProdSupportForm" property="originalJuvenileNumber" />';
		var originReferralNumber = '<bean:write name="ProdSupportForm" property="originalReferralNumber" />';
		
		var updatedAssignmentType= '<bean:write name="ProdSupportForm" property="intakeHistoryRecord.assignmentType"/>';
		var updatedSupervisionTypeCode = '<bean:write name="ProdSupportForm" property="intakeHistoryRecord.supervisionTypeId" />';
		var updatedAssignmentDate = '<bean:write name="ProdSupportForm" property="intakeHistoryRecord.assignmentDate" formatKey="date.format.mmddyyyy" />';
		var updatedIntakeDate = '<bean:write name="ProdSupportForm" property="intakeHistoryRecord.intakeDate" formatKey="date.format.mmddyyyy" />';
		var updatedIntakeDecision = '<bean:write name="ProdSupportForm" property="intakeHistoryRecord.intakeDecisionId" />';
		var updatedIntakeJPO = '<bean:write name="ProdSupportForm" property="intakeHistoryRecord.jpoId" />';
		var updatedJuvenileNumber = '<bean:write name="ProdSupportForm" property="intakeHistoryRecord.juvenileNum" />';
		var updatedReferralNumber = '<bean:write name="ProdSupportForm" property="intakeHistoryRecord.referralNumber" />';
		console.log("Original" + originAssignmentType );
		console.log("Updated" + updatedAssignmentType );
		
		$("#updateIntakeBtn").click(function(){
			console.log("new update");
			spinner();
			$('form').submit();
		})
		
		$("#mainMenuBtn").click(function(){
			console.log("Main menu");
			$('form').attr('action',"DisplayProductionSupportMainPopup.do");
			$("form").submit();
		})
		
		
		if ( originAssignmentType != updatedAssignmentType ) {
			$("#assmntType").append("<td style='color:green;'>Updated from previous value, "+ originAssignmentType + "</td");
		}
		
		if ( originSupervisionTypeCode != updatedSupervisionTypeCode  ) {
			$("#supervisionType").append("<td style='color:green;'>Updated from previous value, "+ originSupervisionTypeCode + "</td");
		}
		
		if ( originAssignmentDate != updatedAssignmentDate ){
			$("#assignmentDate").append("<td style='color:green;'>Updated from previous value, "+ originAssignmentDate + "</td");
		}
		
		if ( originIntakeDate != updatedIntakeDate  ) {
			$("#intakeDate").append("<td style='color:green;'>Updated from previous value, "+ originIntakeDate + "</td");
		}
		
		if ( originIntakeDecision != updatedIntakeDecision  ){
			$("#intakeDecision").append("<td style='color:green;'>Updated from previous value, "+ originIntakeDecision + "</td");
		}
		
		if ( originIntakeJPO != updatedIntakeJPO  ){
			$("#intakeJPO").append("<td style='color:green;'>Updated from previous value, "+ originIntakeJPO  + "</td");
		}
		
		if ( originJuvenileNumber != updatedJuvenileNumber  ) {
			$("#juvenileNum").append("<td style='color:green;'>Updated from previous value, "+ originJuvenileNumber  + "</td");
		}
		
		if ( originReferralNumber != updatedReferralNumber ) {
			$("#referralNum").append("<td style='color:green;'>Updated from previous value, "+ originReferralNumber   + "</td");
		}
	})
</script>
</head>
<body>
<div id="container">
	<div>
		<h2>Update Intake History Summary</h2>
	</div>
	<hr>
	<div id="notice">
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<bean:write name="ProdSupportForm" property="msg" />
			</logic:notEqual> 
	</div>
	<div>
		<table class="resultTbl">
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
	</div>
	<div id="command">
		<html:form action="ViewIntakeHistoryRecordsQuery?clr=R">
			<div>
				<input id="updateIntakeBtn" type="button" value="Update Intake History Table"/>
			</div>
			<div>
				<input id="mainMenuBtn" type="button" value="Return to Main Menu" />
			</div>
		</html:form>
	</div>
</div>
</body>
</html:html>