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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1" />
<title>Juvenile Casework
	-/prodSupport/deletePetitionRecordSummary.jsp</title>
<link rel="stylesheet" type="text/css"
	href="/<msp:webapp/>css/casework.css" />
<style>
h2 {
	text-align: center;
}

#message {
	text-align: center;
	color: red;
}

#container {
	margin-top: 50px;
}

.updatedPetitionDetail {
	width: 500px;
	margin-top: 15px;
	margin-bottom: 15px;
	margin-left: auto;
	margin-right: auto;
}

.updatedPetitionDetail label {
	display: inline-block;
	width: 200px;
	text-align: right;
	font-weight: bold;
}

.updatedPetitionDetail div {
	margin-left: 25px;
	display: inline-block;
}

#command {
	text-align: center;
}

#command div {
	display: inline-block;
}

#command input {
	margin-top: 45px;
	width: 150px;
}

table {
	border: 1px solid black;
	width: 500px;
	margin-left: auto;
	margin-right: auto;
}

.updatedPetitionDetail th {
	font-family: Geneva, Arial, Helvetica, sans-serif;
	font-size: small;
	font-weight: bold;
	color: #000000;
	background-color: #cccccc;
	padding-right: 5px;
	text-align: left;
}

.updatedPetitionDetail td {
	border: 1px solid black;
	text-align: center;
}
</style>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript'
	src="/<msp:webapp/>js/prodSupport/deletePetitionDetails.js"></script>
<script>
	function backToQuery() {
		$("#goBackToQuery").submit();
	}
	
</script>
</head>
<body>
	<br></br>
	<h2 align="center">
		Delete Results for Petition ID =
		<bean:write name="ProdSupportForm" property="referralOID" />
		for the selected Juvenile,
		<bean:write name="ProdSupportForm" property="juvenileId" />
	</h2>	
	<div id="message">
		<logic:notEmpty name="ProdSupportForm" property="msg">
			<bean:write name="ProdSupportForm" property="msg" />
		</logic:notEmpty>
	</div>
	<table class="resultTbl" border="1" width="750" align="center">

		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Name</font></td>
			<td><bean:write name="ProdSupportForm" property="juvenileDetail.lastName" />,
					<bean:write name="ProdSupportForm" property="juvenileDetail.firstName" />&nbsp;<bean:write name="ProdSupportForm" property="juvenileDetail.middleName" />
				</td>
		</tr>		
	</table>  
	<p align="center"><b><i><span style="color: green; font-weight: bold;">Petition Record Successfully Deleted</span></i></b></p>
	<%-- <logic:notEmpty	name="ProdSupportForm" property="petitionDetails"> --%>
	<!-- <br></br> -->
	<h3 align="center">Petition Details</h3>
	<div id="container">
		<%-- <logic:iterate id="petitionDetail" name="ProdSupportForm"
			property="petitionDetail" indexId="index"> --%>
			<table class="resultTbl" align="center" border="1" width="650">
				<tr class="updatedPetitionDetail">
					<th>Petition ID Number</th>
					<td><bean:write name="ProdSupportForm"
							property="petitionDetail.OID" />
					</td>					
					<bean:define id="petitionId" name='ProdSupportForm' property='petitionDetail.OID'
						type="java.lang.String"></bean:define>
					<html:hidden styleId='petOID' name='ProdSupportForm' property='OID'
						value='<%=petitionId%>' />
				</tr>
				 <tr class="updatedPetitionDetail">
					<th nowrap="nowrap">Juvenile Number</th>
					<td><bean:write name="ProdSupportForm" property="petitionDetail.juvenileNum" />
					</td>					
				</tr>
				<tr class="updatedPetitionDetail">
					<th>Referral Number</th>
					<td><bean:write name="ProdSupportForm" property="petitionDetail.referralNum" />
					</td>					
				</tr>
				<tr class="updatedPetitionDetail">
					<th>Petition Status</th>
					<td><font size="-1"><bean:write name="ProdSupportForm"
								property="petitionDetail.petitionStatus" />&nbsp;</font>
					</td>
				</tr>
				<tr class="updatedPetitionDetail">
					<th>Petition Type</th>
					<td><bean:write name="ProdSupportForm" property="petitionDetail.petitionType" />
					</td>					
				</tr>
				<tr class="updatedPetitionDetail">
					<th>Petition Date</th>
					<td><bean:write name="ProdSupportForm" property="petitionDetail.petition_Date" formatKey="date.format.mmddyyyy"/>
					</td>					
				</tr>
				<tr class="updatedPetitionDetail">
					<th>Petition Number</th>
					<td><font size="-1"><bean:write name="ProdSupportForm"
								property="petitionDetail.petitionNum" />&nbsp;</font>
					</td>					
				</tr>
				<tr class="updatedPetitionDetail">
					<th>Petition Amendment</th>
					<td><bean:write name="ProdSupportForm"
							property="petitionDetail.petitionAmendment" />
					</td>					
				</tr>

				<tr class="updatedPetitionDetail">
					<th>Petition Allegation</th>
					<td><bean:write name="ProdSupportForm" property="petitionDetail.petitionAllegation" />
					</td>					
				</tr>
				<tr class="updatedPetitionDetail">
					<th>Petition Allegation Severity</th>
					<td><bean:write name="ProdSupportForm" property="petitionDetail.severity" />
					</td>					
				</tr>
				<tr class="updatedPetitionDetail">
					<th>SeqNum (Petition)</th>
					<td><bean:write name="ProdSupportForm" property="petitionDetail.sequenceNum" />
					</td>					
				</tr>
				<tr class="updatedPetitionDetail">
					<th>Sequence Number (Petition)</th>
					<td><bean:write name="ProdSupportForm" property="petitionDetail.sequenceNumber" />
					</td>					
				</tr>
				<tr class="updatedPetitionDetail">
					<th>Last Change Date</th>
					<td><bean:write name="ProdSupportForm"
							property="petitionDetail.last_Change_Date" formatKey="date.format.mmddyyyy" />
					</td>
				</tr>
				<tr class="updatedPetitionDetail">
					<th>Last Change Time</th>
					<td><bean:write name="ProdSupportForm"
							property="petitionDetail.last_Change_Time" />
					</td>
				</tr>
				<tr class="updatedPetitionDetail">
					<th>Last Change User</th>
					<td><bean:write name="ProdSupportForm"
							property="petitionDetail.lastChangeUser" />
					</td>
				</tr>
			</table>
		<%-- </logic:iterate> --%>
	</div>
	<div id="command">
		
		<div>
			<html:form styleId="goBackToQuery" method="post" action="/MainMenu"
				onsubmit="return this;">
				<input type="button" value="Back to Main Menu"
					onclick="backToQuery()" />
			</html:form>
		</div>
	</div>
</body>

</html:html>