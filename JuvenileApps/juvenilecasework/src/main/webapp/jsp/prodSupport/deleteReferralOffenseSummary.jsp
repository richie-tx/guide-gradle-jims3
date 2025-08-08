<!DOCTYPE HTML>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/deleteReferralOffenseSummary</title>
<!--JQUERY FRAMEWORK-->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<style>
	
	
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
	 }
	 
	 table th,  td {
	 	border: 1px solid black;
	 	text-align: left;
	 	padding: 7px 7px 7px 7px;
	 }
	 
	 .command {
	 	margin-top: 15px;
	 }
	 
	 .command div {
	 	margin-top: 5px;
	 	margin-bottomn: 5px;
	 }
	 
</style>
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<script type='text/javascript'>
	$(document).ready(function(){
		$("#deleteBtn").click(function(){
			
			 var comments = $("#delComments").val();		
			 if (comments.trim() === '') {
		            alert("Please provide comments on why you're deleting this referral offense.");
		            return false;
		        }
			 else
				 {
				 var newField = $('<input>').attr({
				        type: 'hidden',
				        name: 'delComments', 
				        value: comments 
				    });

				 $('form').append(newField); 
				    
				 }
			
			if ( confirm( "Are you sure you want to delete the Referral Offense Record ? ") ) {
				spinner();
				return true;
			} else {
				$("form").attr("action", "/JuvenileCasework/DeleteReferralOffenseQuery.do?clr=Y");
				$("form").submit();
				return false;
			}
		})
		$("#backBtn").click(function(){
			$("form").attr("action", "/JuvenileCasework/DeleteReferralOffenseQuery.do?clr=Y");
			$("form").submit();
		})
	})

</script>
</head>
<body>
<div class="container">
	<h2 align="center">Results for Juvenile ID = <bean:write name="ProdSupportForm"  property="juvenileId"/> </h2>
	<div>
		<p align="center"><b><i>For Record Deletion click Delete Referral Offense</i></b></p>	
	</div>
	<div>
		<p align="center"><b><i>The following Referral Offense Record will be <span style="color:red;">DELETED</span></i></b>.</p>
	</div>
	<div>
		<table>
			<tr>
				<th>Juvenile Number</th>
				<td><bean:write name="ProdSupportForm"  property="juvenileId"/></td>
			</tr>
			<tr>
				<th>Juvenile Name</th>
				<td><bean:write name="ProdSupportForm" property= "juvenileName" /></td>
			</tr>
			<tr>
				<th>Record Type</th>
				<td><bean:write name="ProdSupportForm" property= "referralOffense.recType" /></td>
			</tr>
			<tr>
				<th>Master Status</th>
				<td><bean:write name="ProdSupportForm" property= "statusId" /></td>
			</tr>
			<tr>
				<th>Referral Number</th>
				<td><bean:write name="ProdSupportForm" property="referralId"/></td>
			</tr>
			<tr>
				<th>Offense Code</th>
				<td><bean:write name="ProdSupportForm" property= "referralOffense.offenseCode" /></td>
			</tr>
			<tr>
				<th>Offense Severity</th>
				<td><bean:write name="ProdSupportForm" property= "referralOffense.offenseSeverity" /></td>
			</tr>
			<tr>
				<th>Offense Date</th>
				<td><bean:write name="ProdSupportForm" property= "referralOffense.offDate" formatKey="date.format.mmddyyyy" /></td>
			</tr>
			<tr>
				<th>Offense Investigation</th>
				<td><bean:write name="ProdSupportForm" property= "referralOffense.investigationNum" /></td>
			</tr>
			<tr>
				<th>Offense Street Number</th>
				<td><bean:write name="ProdSupportForm" property= "referralOffense.offenseStreetNum" /></td>
			</tr>
			<tr>
				<th>Offense Street Name</th>
				<td><bean:write name="ProdSupportForm" property= "referralOffense.offenseStreetName" /></td>
			</tr>
			<tr>
				<th>Offense Apt Number</th>
				<td><bean:write name="ProdSupportForm" property= "referralOffense.offenseAptNum" /></td>
			</tr>
			<tr>
				<th>Offense City</th>
				<td><bean:write name="ProdSupportForm" property= "referralOffense.offenseCity" /></td>
			</tr>
			<tr>
				<th>Offense State</th>
				<td><bean:write name="ProdSupportForm" property= "referralOffense.offenseState" /></td>
			</tr>
			<tr>
				<th>Offense Zip</th>
				<td><bean:write name="ProdSupportForm" property= "referralOffense.offenseZip" /></td>
			</tr>
			<tr>
				<th>Weapon Type</th>
				<td><bean:write name="ProdSupportForm" property= "referralOffense.weaponType" /></td>
			</tr>
			<tr>
				<th>CJIS Number</th>
				<td><bean:write name="ProdSupportForm" property= "referralOffense.cjisNum" /></td>
			</tr>
			<tr>
				<th>Date of Arrest</th>
				<td><bean:write name="ProdSupportForm" property= "referralOffense.arrestDate" /></td>
			</tr>
			<tr>
				<th>Arrest Time</th>
				<td><bean:write name="ProdSupportForm" property= "referralOffense.arrestTime" /></td>
			</tr>
			<tr>
				<th>Sequence Number</th>
				<td><bean:write name="ProdSupportForm" property= "referralOffense.sequenceNum" /></td>
			</tr>
			<tr>
				<th>Charge Sequence Number</th>
				<td><bean:write name="ProdSupportForm" property= "referralOffense.chargeSequenceNum" /></td>
			</tr>
			<tr>
				<th>LCUSER</th>
				<td><bean:write name="ProdSupportForm" property= "referralOffense.lcUser" /></td>
			</tr>
			<tr>
				<th>LCDATE/LCTIME</th>
				<td><bean:write name="ProdSupportForm" property= "referralOffense.lcDate" formatKey="date.format.mmddyyyy" />  <bean:write name="ProdSupportForm" property= "referralOffense.lcTime" /></td>
			</tr>
			
		</table>
		<BR>
	<table align="center" style="border: none;">
	<tr>
	  <td style="border: none;">
	   <b>Comments</b>
	   </td>
	  <td style="border: none;">
	    <textarea id="delComments" name="delComments" maxlength="250" rows="6" cols="50" style="overflow: hidden;resize: none;"></textarea>
	  </td>
	</tr>
	</table>
	
	</div>
	<div class="command">
		<html:form method="post" styleId="performDeleteReferralOffense"
										action="/PeformDeleteReferralOffense">
			<div>
				<html:submit styleId="deleteBtn" property="submitAction"><bean:message key="button.deleteReferralOffense"></bean:message></html:submit>
			</div>
			<div>
				<input id="backBtn" type="button" value="Back to Query" />
			</div>
		</html:form>	
	</div>
</div>



</body>
</html:html>