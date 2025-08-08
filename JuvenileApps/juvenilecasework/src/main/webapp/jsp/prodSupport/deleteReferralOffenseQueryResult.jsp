<!DOCTYPE HTML>
<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@page import="java.util.*"%>


<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" /> 
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<html:base />
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
	 
	 .command div {
	 	margin-top: 5px;
	 	margin-bottom: 5px;
	 }
</style>

<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script>
	$(document).ready(function(){
		
		$('input[type=radio][name=offenseId]').change(function(){
			$("#offenseId").val(this.value);
			console.log( $("#offenseId").val() );
		});
		
		$("#submitBtn").click(function(){
			spinner();
			if ( !$('input[name=offenseId]:checked').length > 0 ) {
				alert("Please select a radio button.");
				$(".overlay").remove();
				return false;
			} 			
		})
		
		$("#backBtn").click(function(){
			$("form").attr("action", "/JuvenileCasework/DeleteReferralOffenseQuery.do?clr=Y");
			$("form").submit();
		})
		
	})
</script>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/deleteReferralOffenseQueryResult.jsp</title>
<body>
	<div class="container">
		<h2 align="center">Results for Juvenile ID = <bean:write name="ProdSupportForm"  property="juvenileId"/> and Referral Number = <bean:write name="ProdSupportForm" property="referralId"/></h2>
		<div>
			<table>
					<tr>
						<th>Juvenile Name</th>
						<td><bean:write name="ProdSupportForm" property= "juvenileName" /></td>
					</tr>
					<tr>
						<th>Juvenile Number</th>
						<td>
							<bean:write name="ProdSupportForm"  property="juvenileId"/>
						</td>
					</tr>
			</table>
		</div>
		<div>
			<p align="center"><b><i>Some referral may have multiple offenses. 
										Please select the radio button next to the offense
							</i></b>
			</p>
			<p align="center"><b><i>
									you want to delete and click the Submit button. 
							</i></b>
			</p>		
		</div>
		<div>
			<table>
					<tr>
						<th></th>
						<th>Offense_ID</th>
						<th>Juvenile Number</th>
						<th>Referral Number</th>
						<th>Offense Code</th>
						<th>Offense Date</th>
						<th>Offense Severity</th>
						<th>SeqNum</th>
					</tr>
					<logic:notEmpty name="ProdSupportForm" property="referralOffenses">
						<logic:iterate id="referralOffense" name="ProdSupportForm" property="referralOffenses">
							<tr>
								<td><input type="radio" name="offenseId" value="<bean:write name="referralOffense"  property="OID"/>"/></td>
								<td> <bean:write name="referralOffense"  property="OID"/> </td>
								<td> <bean:write name="referralOffense"  property="juvenileNum"/> </td>
								<td> <bean:write name="referralOffense"  property="referralNum"/> </td>
								<td> <bean:write name="referralOffense"  property="offenseCode"/> </td>
								<td> <bean:write name="referralOffense"  property="offDate"/> </td>
								<td> <bean:write name="referralOffense"  property="referralNum"/> </td>
								<td> <bean:write name="referralOffense"  property="sequenceNum"/> </td>
							</tr>
						</logic:iterate>
					
					</logic:notEmpty>
					
			</table>
		</div>
		<div class="command">
			<html:form method="post" styleId="performDeleteReferralOffense"
										action="/PeformDeleteReferralOffense">
				<html:hidden styleId="offenseId" name="ProdSupportForm" property="offenseId"/>
				<logic:notEmpty name="ProdSupportForm" property="referralOffenses">
				<div>
					<html:submit styleId="submitBtn" property="submitAction"><bean:message key="button.submit"></bean:message></html:submit>
				</div>
				</logic:notEmpty>
				<div>
					<input id="backBtn" type="button" value="Back to Query" />
				</div>
			</html:form>	
		</div>
		
	</div>

</body>
