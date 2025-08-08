<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<!DOCTYPE HTML>

<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/viewIntakeHistoryRecordsResult.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<style>
	#container {
		text-align: center;
	}
	
	 table {
	 	border: 1px solid black;
	 	width: 500px;
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
	 
	 table th,  td {
	 	border: 1px solid black;
	 	text-align: center;
	 	padding: 7px 7px 7px 7px;
	 }
	 
	 .command {
	 	margin-top: 15px;
	 }
</style>
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script>
	$(document).ready(function(){
		$("#intakeHistoryId").val("");
		$('input[type=radio][name=intakeId]').change(function(){
			console.log(this.value);
			$("#intakeHistoryId").val(this.value);
			console.log( $("#intakeHistoryId").val() );
		});
		
		
		$("#updateBtn").click(function(){
			if ($("#intakeHistoryId").val() != ""){
				spinner();
				$('form').submit();
			} else {
				alert("Please select an intake history record to update.");
			}
		
			
		})
		
		$("#deleteBtn").click(function(){
			if ($("#intakeHistoryId").val() != ""){
				if(confirm('Are you sure you want to delete the record?'))
				{
					spinner();
					$('form').submit();
				}
				else
					return false;	
				} else {
				alert("Please select an intake history record to delete.");
			}
		
			
		})
		
		$("#backBtn").click(function(){
			
			if($("#isDeleteHistoryId").val() == "true")
			{
				$('form').attr("action","/JuvenileCasework/ViewIntakeHistoryRecordsQuery.do?clr=R&deleteIntakHistory=true");
				$('form').submit();
			}
			else
			{
				$('form').attr("action","/JuvenileCasework/ViewIntakeHistoryRecordsQuery.do?clr=R");
				$('form').submit();
			}
		})
	})
</script>
</head>
<body>
	<div id="container">
		<h2>Results for Juvenile Number = <bean:write name="ProdSupportForm" property="juvenileId" />
			and Referral Number = <bean:write name="ProdSupportForm" property="referralId" /></h2>
			<hr>
		<div>
			<h3>Juvenile Details</h3>
		</div>
		<div>
			<table class="resultTbl">
				<tr>
					<th>Juvenile Number</th>
					<td><bean:write name="ProdSupportForm" property= "juvenileDetail.juvenileNum" /></td>
				</tr>
				<tr>
					<th>Juvenile Name</th>
					<td>
						<logic:notEmpty name="ProdSupportForm" property= "juvenileDetail.lastName">
							<bean:write name="ProdSupportForm" property= "juvenileDetail.lastName" /><logic:notEmpty name="ProdSupportForm" property= "juvenileDetail.firstName">,
							</logic:notEmpty>
						</logic:notEmpty>
						<bean:write name="ProdSupportForm" property= "juvenileDetail.firstName" /><logic:notEmpty name="ProdSupportForm" property= "juvenileDetail.middleName">,
						</logic:notEmpty>
						<bean:write name="ProdSupportForm" property= "juvenileDetail.middleName" />
						&nbsp;&nbsp;
						<logic:equal name="ProdSupportForm" property="juvenileDetail.rectype" value="S.JUVENILE">S</logic:equal>
						<logic:equal name="ProdSupportForm" property="juvenileDetail.rectype" value="I.JUVENILE">P</logic:equal>
					</td>
				</tr>
				<tr>
					<th>Record Type</th>
					<td><bean:write name="ProdSupportForm" property= "juvenileDetail.rectype" /></td>
				</tr>
				<tr>
					<th>DOB</th>
					<td><bean:write name="ProdSupportForm" property= "juvenileDetail.dob" /></td>
				</tr>
				<tr>
					<th>SSN</th>
					<td><bean:write name="ProdSupportForm" property= "juvenileDetail.juvenileSsn" /></td>
				</tr>
				<tr>
					<th>Master Status</th>
					<td><bean:write name="ProdSupportForm" property= "juvenileDetail.masterStatus" /></td>
				</tr>
			</table>
		</div>
		<div>
			<h3>Referral Details</h3>
		</div>
		<div>
			<table class="resultTbl">
				<thead>
					<tr>
						<th>Referral Number</th>
						<th>Referral Date</th>
						<th>Referral Offense</th>
						<th>Referral Status</th>
					</tr>
				</thead>
				<tbody>
					<logic:iterate id="referralDetail" name="ProdSupportForm" property="referralDetails">
						<td><bean:write name="referralDetail" property="referralNum"/></td>
						<td><bean:write name="referralDetail" property="referralDate"/></td>
						<td><bean:write name="ProdSupportForm" property="currentOffense"/></td>
						<td><bean:write name="referralDetail" property="closeDate"/></td>
					</logic:iterate>
				</tbody>
			</table>
		</div>
		<div>
			<p align="center"><b><i>Select the radio button next to the record <br>
				you want to ${ProdSupportForm.isIntakeHistoryDelete ? 'Delete' : 'Update'} and click the ${ProdSupportForm.isIntakeHistoryDelete ? 'Submit' : 'Update Intake History'} button.</i></b>
				</p>	
			<h3>Intake History Details</h3>
		</div>
		<div>
			<table class="resultTbl" align="center" border="1" width="850">
				<thead>
					<tr>
						<th></th>
						<th>Intake History Id</th>
						<th>Assmnt Type</th>
						<th>Supervision Type Code</th>
						<th>Assignment Date</th>
						<th>Intake Date</th>
						<th>Intake Decision</th>
						<th>Intake JPO</th>
						<th>Juvenile Number</th>
						<th>Referral Number</th>
						<th>Create User</th>
						<th>Create Date</th>
						<th>Update User</th>
						<th>Update Date</th>
						<th>Create JIMS2 User</th>
						<th>Update JIMS2 User</th>
					</tr>
				</thead>
				<tbody>
					<logic:iterate id="intakeHistoryRecord" name="ProdSupportForm" property="intakeHistoryRecords">
						<tr>
							<td><input name="intakeId" type="radio" value="<bean:write name="intakeHistoryRecord" property="OID"/>"/></td>
							<td><bean:write name="intakeHistoryRecord" property="OID"/></td>
							<td><bean:write name="intakeHistoryRecord" property="assignmentType"/></td>
							<td><bean:write name="intakeHistoryRecord" property="supervisionTypeId"/></td>
							<td><bean:write name="intakeHistoryRecord" property="assignmentDate" formatKey="date.format.mmddyyyy"/></td>
							<td><bean:write name="intakeHistoryRecord" property="intakeDate" formatKey="date.format.mmddyyyy"/></td>
							<td><bean:write name="intakeHistoryRecord" property="intakeDecisionId"/></td>
							<td><bean:write name="intakeHistoryRecord" property="jpoId"/></td>
							<td><bean:write name="intakeHistoryRecord" property="juvenileNum"/></td>
							<td><bean:write name="intakeHistoryRecord" property="referralNumber"/></td>
							<td><bean:write name="intakeHistoryRecord" property="createUserID"/></td>
							<td><bean:write name="intakeHistoryRecord" property="createTimestamp" formatKey="date.format.mmddyyyy"/></td>
							<td><bean:write name="intakeHistoryRecord" property="updateUserID"/></td>
							<td><bean:write name="intakeHistoryRecord" property="updateTimestamp" formatKey="date.format.mmddyyyy"/></td>
							<td><bean:write name="intakeHistoryRecord" property="createJIMS2UserID"/></td>
							<td><bean:write name="intakeHistoryRecord" property="updateJIMS2UserID"/></td>
						</tr>
					</logic:iterate>
				</tbody>
			</table>
		</div>
		</form>
		<div class="command">

			<html:form method="post" styleId="intakeHistoryForm"
				action="${ProdSupportForm.isIntakeHistoryDelete ? '/DeleteIntakeHistoryRecord' : '/UpdateIntakeHistoryRecord?clr=Y'}">
				<html:hidden styleId="intakeHistoryId" name="ProdSupportForm"
					property="intakeHistoryId" />
				<html:hidden styleId="isDeleteHistoryId" name="ProdSupportForm"
					property="isIntakeHistoryDelete" />
				<input
					id="${ProdSupportForm.isIntakeHistoryDelete ? 'deleteBtn' : 'updateBtn'}"
					type="button"
					value="${ProdSupportForm.isIntakeHistoryDelete ? 'Delete' : 'Update Intake History'}" />
				<input id="backBtn" type="button" value="Back To Query" />
			</html:form>
		</div>
	</div>
</body>
</html:html>