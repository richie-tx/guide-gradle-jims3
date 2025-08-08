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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/updatePetitionDetailsConfirm.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />	
<style>
	 h2 { text-align: center;}
	 .message {
	 	text-align: center;
	 	color: green;
	 }
	 
	 #container{
	 	margin-top: 50px;
	 
	 }
	 
	.updatedPetitionDetail{
	 	width: 500px;
	 	margin-top: 15px;
	 	margin-bottom: 15px;
	 	margin-left: auto;
	 	margin-right: auto;
	 }
	 .updatedPetitionDetail label{
	 	display: inline-block;
	 	width: 200px; text-align: right;
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
		padding-right:5px;
		text-align:left;
	 }
	 
	 .updatedPetitionDetail td {
	 	border: 1px solid black;
	 	text-align: center;
	 }
</style>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/updatePetitionDetails.js"></script>
<script>

	$("document").ready(function(){
		<logic:iterate id="petitionDetail" name="ProdSupportForm" property="petitionDetails" indexId="index">
			if ('<bean:write name="petitionDetail" property="juvenileNum"/>' != '<bean:write name="ProdSupportForm" property="juvenileId" />'){
				$("#juvenileNumber").append("<td class='message'>Updated from previous value, " + '<bean:write name="petitionDetail" property="juvenileNum"/>' + "</td>" );
			}
			if ('<bean:write name="petitionDetail" property="petitionAllegation"/>' != '<bean:write name="ProdSupportForm" property="petitionAllegation" />'){
				$("#petitionAllegation").append("<td class='message'>Updated from previous value, " + '<bean:write name="petitionDetail" property="petitionAllegation"/>' + "</td>" );
			}
			
			if ('<bean:write name="petitionDetail" property="amend"/>' != '<bean:write name="ProdSupportForm" property="petitionAmended" />'){
				$("#petitionAmendment").append("<td class='message'>Updated from previous value, " + '<bean:write name="petitionDetail" property="amend"/>' + "</td>" );
			}
			
			if ('<bean:write name="petitionDetail" property="petitionDate" formatKey="date.format.mmddyyyy"/>' != '<bean:write name="ProdSupportForm" property="newPetitionDate" formatKey="date.format.mmddyyyy" />'){
				$("#petitionDate").append("<td class='message'>Updated from previous value, " + '<bean:write name="petitionDetail" property="petitionDate" formatKey="date.format.mmddyyyy"/>' + "</td>" );
			}
			
			if ('<bean:write name="petitionDetail" property="petitionNum"/>' != '<bean:write name="ProdSupportForm" property="petitionNum" />'){
				$("#petitionNumber").append("<td class='message'>Updated from previous value, " + '<bean:write name="petitionDetail" property="petitionNum"/>' + "</td>" );
			}
			
			if ('<bean:write name="petitionDetail" property="petitionType"/>' != '<bean:write name="ProdSupportForm" property="petitionType" />'){
				$("#petitionType").append("<td class='message'>Updated from previous value, " + '<bean:write name="petitionDetail" property="petitionType"/>' + "</td>" );
			}
			
			if ('<bean:write name="petitionDetail" property="referralNum"/>' != '<bean:write name="ProdSupportForm" property="newReferralNum" />'){
				$("#referralNumber").append("<td class='message'>Updated from previous value, " + '<bean:write name="petitionDetail" property="referralNum"/>' + "</td>" );
			}
			
			if ('<bean:write name="petitionDetail" property="petitionStatus"/>' != '<bean:write name="ProdSupportForm" property="petitionStatus" />'){
				$("#petitionStatus").append("<td class='message'>Updated from previous value, " + '<bean:write name="petitionDetail" property="petitionStatus"/>' + "</td>" );
			}
			
			if ('<bean:write name="petitionDetail" property="sequenceNum"/>' != '<bean:write name="ProdSupportForm" property="sequenceNumber" />'){
				$("#sequenceNumber").append("<td class='message'>Updated from previous value, " + '<bean:write name="petitionDetail" property="sequenceNum"/>' + "</td>" );
			}
			
			if ('<bean:write name="petitionDetail" property="terminationDate" formatKey="date.format.mmddyyyy"/>' != '<bean:write name="ProdSupportForm" property="newTerminationDate" formatKey="date.format.mmddyyyy" />'){
				$("#terminationDate").append("<td class='message'>Updated from previous value, " + '<bean:write name="petitionDetail" property="terminationDate" formatKey="date.format.mmddyyyy"/>' + "</td>" );
			}
			
			if ('<bean:write name="petitionDetail" property="terminationCreateDate" formatKey="date.format.mmddyyyy"/>' != '<bean:write name="ProdSupportForm" property="newTerminationCreateDate" formatKey="date.format.mmddyyyy" />'){
				$("#terminationCreateDate").append("<td class='message'>Updated from previous value, " + '<bean:write name="petitionDetail" property="terminationCreateDate" formatKey="date.format.mmddyyyy"/>' + "</td>" );
			}
			if ('<bean:write name="petitionDetail" property="DPSCode"/>' != '<bean:write name="ProdSupportForm" property="DPSCode" />'){
				$("#dpsCode").append("<td class='message'>Updated from previous value, " + '<bean:write name="petitionDetail" property="DPSCode"/>' + "</td>" );
			}
			if ('<bean:write name="petitionDetail" property="petCJISNum"/>' != '<bean:write name="ProdSupportForm" property="CJISNumber" />'){
				$("#cjisNumber").append("<td class='message'>Updated from previous value, " + '<bean:write name="petitionDetail" property="petCJISNum"/>' + "</td>" );
			}
			
			
		</logic:iterate>
	})
	
	
	function backToQuery(){
		$("#goBackToQuery").submit();
	}
	
	function updateAgain(){
		$("#viewDetailsForm").submit();
	}
</script>
</head>
<body>
	<h2 align="center">Petition Detail Record for 
			<bean:write name="ProdSupportForm" property="juvenileId" />
			and Referral <bean:write name="ProdSupportForm" property="referralId" /></h2>
	<hr>
	<div class="message">
		<logic:notEmpty	name="ProdSupportForm" property="msg">
			<bean:write name="ProdSupportForm" property="msg" />
		</logic:notEmpty>
	</div>
	<div id="container">
		<logic:iterate id="petitionDetail" name="ProdSupportForm" property="petitionDetails" indexId="index">
		<table class="resultTbl" align="center" border="1" width="850">
			<tr id="juvenileNumber" class="updatedPetitionDetail">
				<th>Juvenile Number:</th>
				<td><bean:write name="ProdSupportForm" property="juvenileId" /></td>
			</tr>
			<tr id="petitionAllegation" class="updatedPetitionDetail">
				<th>Petition Allegation:</th>
				<td><bean:write name="ProdSupportForm" property="petitionAllegation" /></td>
			</tr>
			<tr class="updatedPetitionDetail">
				<th nowrap="nowrap">Petition Allegation Severity:</th>
				<td><bean:write name="petitionDetail" property="severity" /></td>
			</tr>
			<tr id="petitionAmendment" class="updatedPetitionDetail">
				<th>Petition Amendment:</th>
				<td><bean:write name="ProdSupportForm" property="petitionAmended" /></td>
				
			</tr>
			<tr id="petitionDate" class="updatedPetitionDetail">
				<th>Petition Date:</th>
					<td><font size="-1"><bean:write name="ProdSupportForm" property="newPetitionDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
 			</tr>
			<tr id="petitionNumber" class="updatedPetitionDetail">
				<th>Petition Number:</th>
				<td><bean:write name="ProdSupportForm" property="petitionNum" /></td>
			</tr>
			<tr id="petitionType" class="updatedPetitionDetail">
				<th>Petition Type:</th>
				<td><bean:write name="ProdSupportForm" property="petitionType" /></td>
			</tr>
			
			<tr id="referralNumber" class="updatedPetitionDetail">
				<th>Referral Number:</th>
				<td><font size="-1"><bean:write name="ProdSupportForm" property="newReferralNum"/>&nbsp;</font></td>
			</tr>
			<tr id="petitionStatus" class="updatedPetitionDetail">
				<th>Petition Status:</th>
				<td><bean:write name="ProdSupportForm" property="petitionStatus" /></td>
			</tr>

			<tr id="sequenceNumber" class="updatedPetitionDetail">
				<th>Sequence Number:</th>
				<td><bean:write name="ProdSupportForm" property="sequenceNumber" /></td>
			</tr>			
			<tr id="terminationDate" class="updatedPetitionDetail">
				<th>Termination Date:</th>
				<td><bean:write name="ProdSupportForm" property="newTerminationDate"  formatKey="date.format.mmddyyyy"/></td>
			</tr>
			<tr id="terminationCreateDate" class="updatedPetitionDetail">
				<th>Termination Create Date:</th>
				<td><bean:write name="ProdSupportForm" property="newTerminationCreateDate"  formatKey="date.format.mmddyyyy"/></td>
			</tr>
			<tr id="dpsCode" class="updatedPetitionDetail">
				<th>DPScode:</th>
				<td><bean:write name="ProdSupportForm" property="DPSCode" /></td>
			</tr>
			<tr id="cjisNumber" class="updatedPetitionDetail">
				<th>CJIS Number:</th>
				<td><bean:write name="ProdSupportForm" property="CJISNumber" /></td>
			</tr>
			<tr class="updatedPetitionDetail">
				<th>Last Change Date/Time:</th>
				<td><bean:write name="petitionDetail" property="lastChangeDate" /></td>
			</tr>
			<tr class="updatedPetitionDetail">
				<th>LastChange User:</th>
				<td><bean:write name="petitionDetail" property="lastChangeUser" /></td>
			</tr>
			
		</table>
		</logic:iterate>
	</div>
	<div id="command">
		<div>
			<html:form  method="post" action="/viewPetitionDetailsQuery?clr=Y" onsubmit="return this;">
				<input type="submit" value="Back to Query" "/>
			</html:form>
		</div>
		<div>
			<html:form styleId="goBackToQuery" method="post" action="/MainMenu" onsubmit="return this;">
				<input type="button" value="Back to Main Menu" onclick="backToQuery()"/>
			</html:form>
		</div>
		
		
	</div>				
</body>

</html:html>