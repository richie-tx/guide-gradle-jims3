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
		
		var originalOffenseDate = '<bean:write name="ProdSupportForm" property="originalOffenseDate" formatKey="date.format.mmddyyyy"/>';	
		var originalAdjudicationDate = '<bean:write name="ProdSupportForm" property="originalAdjudicationDate" formatKey="date.format.mmddyyyy"/>';
		var originalCountyId= '<bean:write name="ProdSupportForm" property="originalCountyId"/>';
		var originalOffenseCode = '<bean:write name="ProdSupportForm" property="originalOffenseCode" />';
		var originalCategory = '<bean:write name="ProdSupportForm" property="originalCategory" />';
		var originalDpsCode = '<bean:write name="ProdSupportForm" property="originalDpsCode" />';
		var originalPersonId = '<bean:write name="ProdSupportForm" property="originalPersonId" />';
		var originalJuvenileNumber = '<bean:write name="ProdSupportForm" property="originalJuvenileNumber" />';
		var originalReferralNumber = '<bean:write name="ProdSupportForm" property="originalReferralNumber" />';
		
		
		var updatedOffenseDate = '<bean:write name="ProdSupportForm" property="transferredOffenseRecord.offenseDate" formatKey="date.format.mmddyyyy"/>';	
		var updatedAdjudicationDate = '<bean:write name="ProdSupportForm" property="transferredOffenseRecord.adjudicationDate" formatKey="date.format.mmddyyyy"/>';
		var updatedCountyId= '<bean:write name="ProdSupportForm" property="transferredOffenseRecord.countyId"/>';
		var updatedOffenseCode = '<bean:write name="ProdSupportForm" property="transferredOffenseRecord.offenseCode" />';
		var updatedCategory = '<bean:write name="ProdSupportForm" property="transferredOffenseRecord.category" />';
		var updatedDpsCode = '<bean:write name="ProdSupportForm" property="transferredOffenseRecord.dpsCode" />';
		var updatedPersonId = '<bean:write name="ProdSupportForm" property="transferredOffenseRecord.personId" />';
		var updatedJuvenileNumber = '<bean:write name="ProdSupportForm" property="transferredOffenseRecord.juvenileNum" />';
		var updatedReferralNumber = '<bean:write name="ProdSupportForm" property="transferredOffenseRecord.referralNum" />';
		
		
		$("#updateTransBtn").click(function(){
			console.log("new update");
			spinner();
			$('form').submit();
		})
		
		$("#mainMenuBtn").click(function(){
			console.log("Main menu");
			$('form').attr('action',"DisplayProductionSupportMainPopup.do");
			$("form").submit();
		})
		
		
		if ( originalCountyId != updatedCountyId ) {
			$("#fromcountyid").append("<td style='color:green;'>Updated from previous value, "+ originalCountyId + "</td");
		}
		
		if ( originalOffenseCode != updatedOffenseCode  ) {
			$("#offensecd").append("<td style='color:green;'>Updated from previous value, "+ originalOffenseCode + "</td");
		}
		
		if ( originalCategory != updatedCategory ){
			$("#categorycd").append("<td style='color:green;'>Updated from previous value, "+ originalCategory + "</td");
		}
		
		if ( originalDpsCode != updatedDpsCode  ) {
			$("#dpscd").append("<td style='color:green;'>Updated from previous value, "+ originalDpsCode + "</td");
		}
		
		if ( originalPersonId != updatedPersonId  ){
			$("#personid").append("<td style='color:green;'>Updated from previous value, "+ originalPersonId + "</td");
		}
		
		if (originalOffenseDate != updatedOffenseDate ) {
			$("#offensedate").append("<td style='color:green;'>Updated from previous value, "+ originalOffenseDate + "</td");
		}
		
		if ( originalAdjudicationDate != updatedAdjudicationDate  ){
			$("#adjudicationdate").append("<td style='color:green;'>Updated from previous value, "+ originalAdjudicationDate + "</td");
		}
		
		
		if ( originalJuvenileNumber != updatedJuvenileNumber  ) {
			$("#juvenileNum").append("<td style='color:green;'>Updated from previous value, "+ originalJuvenileNumber  + "</td");
		}
		
		if ( originalReferralNumber != updatedReferralNumber ) {
			$("#referralNum").append("<td style='color:green;'>Updated from previous value, "+ originalReferralNumber   + "</td");
		}
	})
</script>
</head>
<body>
<div id="container">
	<div>
		<h2>Update Transferred Offense Referral Summary</h2>
	</div>
	<hr>
	<div id="notice">
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<bean:write name="ProdSupportForm" property="msg" />
			</logic:notEqual> 
	</div>
	<div><table class="resultTbl">
				<tr>
					<th>TRANSOFFNSREF_ID</th>
					<td><bean:write name="ProdSupportForm"
							property="transferredOffenseRecord.transOffenseReferralId" />
					</td>
				</tr>
				<tr id="juvenileNum">					
					<th>JUVENILENUMBER</th>
					<td><bean:write name="ProdSupportForm"
							property="transferredOffenseRecord.juvenileNum" />
					</td>
				</tr>
				<tr id="referralNum">
					<th>REFERRALNUMBER</th>
					<td><bean:write name="ProdSupportForm"
							property="transferredOffenseRecord.referralNum" />
					</td>
				</tr>
				<tr id="fromcountyid">
					<th>FROMCOUNTYCD</th>
					<td><bean:write name="ProdSupportForm"
							property="transferredOffenseRecord.countyId" />
					</td>
				</tr>
				<tr id="offensecd">
					<th>OFFENSECD</th>
					<td><bean:write name="ProdSupportForm"
							property="transferredOffenseRecord.offenseCode" />
					</td>
				</tr>
				<tr id="categorycd">
					<th>CATEGORYCD</th>
					<td><bean:write name="ProdSupportForm"
							property="transferredOffenseRecord.category" />
					</td>
				</tr>
				<tr id="dpscd">
					<th>DPSCD</th>
					<td><bean:write name="ProdSupportForm"
							property="transferredOffenseRecord.dpsCode" />
					</td>
				</tr>
				<tr id="offensedate">
					<th>OFFENSEDATE</th>
					<td><bean:write name="ProdSupportForm"
							property="transferredOffenseRecord.offenseDate"
							formatKey="date.format.mmddyyyy" />
					</td>
				</tr>
				<tr id="adjudicationdate">
					<th>ADJUDICATIONDATE</th>
					<td><bean:write name="ProdSupportForm"
							property="transferredOffenseRecord.adjudicationDate"
							formatKey="date.format.mmddyyyy" />
					</td>

				</tr>
				<tr id="personid">
					<th>PERSONID</th>
					<td><bean:write name="ProdSupportForm"
							property="transferredOffenseRecord.personId" />
					</td>
				</tr>
			</table>
		</div>
	<div id="command">
		<html:form action="ViewTransferredOffenseRecordsQuery?clr=R">
			<div>
				<input id="updateTransBtn" type="button" value="Update Transferred Offense Query"/>
			</div>
			<div>
				<input id="mainMenuBtn" type="button" value="Return to Main Menu" />
			</div>
		</html:form>
	</div>
</div>
</body>
</html:html>