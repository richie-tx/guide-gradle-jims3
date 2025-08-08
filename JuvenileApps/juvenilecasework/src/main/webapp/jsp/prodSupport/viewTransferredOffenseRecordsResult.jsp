<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic"%>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1" />
<msp:nocache />
<!DOCTYPE HTML>

<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1" />
<title>Juvenile Casework
	-/prodSupport/viewTransferredOffenseRecordsResult.jsp</title>
<link rel="stylesheet" type="text/css"
	href="/<msp:webapp/>css/casework.css" />
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
	padding-right: 5px;
	text-align: left;
}

table th,td {
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
	$(document)
			.ready(
					function() {
						
						
						$("#transOffenseId").val("");
						$('input[type=radio][name=transOffenseReferralId]').change(function(){
							console.log(this.value);
							$("#transOffenseId").val(this.value);
							console.log( $("#transOffenseId").val() );
						});
						
						
						$("#updateBtn").click(function(){
							if ($("#transOffenseId").val() != ""){
								spinner();
								$('form').submit();
							} else {
								alert("Please select an intake history record to update.");
							}
						
							
						})
						

						$("#backBtn")
								.click(
										function() {
											$('form')
													.attr("action",
															"/JuvenileCasework/ViewTransferredOffenseRecordsQuery.do?clr=R");
											$('form').submit();
										})
					})
</script>
</head>
<body>
	<div id="container">
		<h2>
			Results for Juvenile Number =
			<bean:write name="ProdSupportForm" property="juvenileId" />
		</h2>
		<hr>
		<div>
			<h3>Juvenile Details</h3>
		</div>
		<div>
			<table class="resultTbl">
				<tr>
					<th>Juvenile Number</th>
					<td><bean:write name="ProdSupportForm"
							property="juvenileDetail.juvenileNum" />
					</td>
				</tr>
				<tr>
					<th>Juvenile Name</th>
					<td><logic:notEmpty name="ProdSupportForm"
							property="juvenileDetail.lastName">
							<bean:write name="ProdSupportForm"
								property="juvenileDetail.lastName" />
							<logic:notEmpty name="ProdSupportForm"
								property="juvenileDetail.firstName">,
							</logic:notEmpty>
						</logic:notEmpty> <bean:write name="ProdSupportForm"
							property="juvenileDetail.firstName" />
						<logic:notEmpty name="ProdSupportForm"
							property="juvenileDetail.middleName">,
						</logic:notEmpty> <bean:write name="ProdSupportForm"
							property="juvenileDetail.middleName" /> &nbsp;&nbsp; <logic:equal
							name="ProdSupportForm" property="juvenileDetail.rectype"
							value="S.JUVENILE">S</logic:equal> <logic:equal
							name="ProdSupportForm" property="juvenileDetail.rectype"
							value="I.JUVENILE">P</logic:equal></td>
				</tr>
				<tr>
					<th>Record Type</th>
					<td><bean:write name="ProdSupportForm"
							property="juvenileDetail.rectype" />
					</td>
				</tr>
				<tr>
					<th>DOB</th>
					<td><bean:write name="ProdSupportForm"
							property="juvenileDetail.dob" />
					</td>
				</tr>
				<tr>
					<th>SSN</th>
					<td><bean:write name="ProdSupportForm"
							property="juvenileDetail.juvenileSsn" />
					</td>
				</tr>
				<tr>
					<th>Master Status</th>
					<td><bean:write name="ProdSupportForm"
							property="juvenileDetail.masterStatus" />
					</td>
				</tr>
			</table>
		</div>
		<div>
			<p align="center">
				<b><i>Select the radio button next to the record <br>
						you want to update and click the Update Transferred Offense
						button.</i>
				</b>
			</p>
			<h3>Transferred Offenses Details</h3>
		</div>
		<div>
			<table class="resultTbl" border="1" width="850" align="center">
				<thead>
					<tr>
						<th></th>
						<th>TRANSOFFNSREF_ID</th>
						<th>JUVENILENUM</th>
						<th>REFERRALNUM</th>
						<th>FROMCOUNTYCD</th>
						<th>OFFENSECD</th>
						<th>CATEGORYCD</th>
						<th>DPSCD</th>
						<th>OFFENSEDATE</th>
						<th>ADJUDICATIONDATE</th>
						<th>PERSONID</th>
					</tr>
					<tbody>
					<logic:iterate id="transOffenseReferral" name="ProdSupportForm" property="transOffenseReferrals">
						<tr>
						    <td><input name="transOffenseReferralId" type="radio" value="<bean:write name="transOffenseReferral" property="transOffenseReferralId"/>"/></td>
							<td><bean:write name="transOffenseReferral"
									property="transOffenseReferralId" />
							</td>
							<td><bean:write name="transOffenseReferral"
									property="juvenileNum" />
							</td>
							<td><bean:write name="transOffenseReferral"
									property="referralNum" />
							</td>
							<td><bean:write name="transOffenseReferral"
									property="countyId" />
							<td><bean:write name="transOffenseReferral"
									property="offenseCode" />
							</td><td><bean:write name="transOffenseReferral"
									property="category" />
							</td>
							<td><bean:write name="transOffenseReferral"
									property="dpsCode" />
							</td>
							<td><bean:write name="transOffenseReferral"
									property="offenseDate" />
							</td>
							<td><bean:write name="transOffenseReferral"
									property="adjudicationDate" />
							</td>
							<td><bean:write name="transOffenseReferral"
									property="personId" />
							</td>
							
						</tr>
					</logic:iterate>
				</tbody>
				</table>			
		</div>
		</form>
		<div class="command">
		<html:form method="post" styleId="intakeHistoryForm"
									action="/UpdateTransferredOffenseRecord?clr=Y">
				<html:hidden styleId="transOffenseId" name="ProdSupportForm" property="transOffenseId"/> 
				<input id="updateBtn" type="button"
					value="Update Transferred Offense" />
				<input id="backBtn" type="button" value="Back To Query" />
		</html:form>
				
			
			
		</div>
	</div>
</body>
</html:html>