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
<title>Juvenile Casework -/prodSupport/viewIntakeHistoryRecordsQuery.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<style>
	#container{
		text-align: center;
	}
	
	#container div {
			margin-top: 15px;
	}
	
	#inputFields div {
		margin-top: 10px;
	}
	
	#command div {
		display: inline-block;
		margin-left: 10px;
		margin-right: 10px;
	}
	
	label {
		color: #0000aa;
	}
	
	#command input {
		width: 75px;
	}
	
	#notice {
		margin-top: 10px;
		color: red;
	}
</style>
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script>
	$(document).ready(function(){
		$("#viewIntakeHistoryRecords").click(function(){
			if ( isValid() ){				
				$("#viewIntakeHistoryForm").submit();				
			} else {
								
			}
		});
		
		$("#cancel").click(function(){
			$('form').attr("action","DisplayProductionSupportMainPopup.do");
			$('form').submit();
		})
		
		$("#refresh").click(function(){
			$("#juvenileId").val("");
			$("#referralId").val("");
			$("#notice").html("");
		});
		
		
	});
	
	function isValid(){
		var juvenileId = $("#juvenileId").val();
		var referralId = $("#referralId").val();
		
 		if( !(  $.isNumeric(juvenileId)
 			&& juvenileId.length == 6 ) ) {
 			$("#notice").html( "Juvenile number is invalid. Please retry search." );
 			return false;	
 		}
 		
 		if (! $.isNumeric(referralId) ){
 			$("#notice").html( "Referral number is invalid. Please retry search." );
 			return false;
 		}
 		if(true)
 			spinner();
 		return true;
 	}
</script>
</head>
<body>
	<div id="container">
		<h2>Production Support - Search Juvenile - Intake History Table</h2>
		<hr>
		<p>Please enter a Juvenile Number and Referral Number to view the intake history records
		<div id="inputFields">							
				<html:form method="post" styleId="viewIntakeHistoryForm"
          action="/ViewIntakeHistoryRecordsQuery${ProdSupportForm.isIntakeHistoryDelete ? '?deleteIntakHistory=true' : ''}">								
												
				<div>
					<label>Juvenile Number: </label>
					<html:text styleId = "juvenileId" 
									   property="juvenileId"
									   size="10" maxlength="8" />
				</div>
				<div>
					<label>Referral Number: </label>
					<html:text styleId="referralId"
									   property="referralId"
									   size="10" maxlength="4" />
				</div>
			</html:form>
		</div>
		<div id="command">
			<div>
				<input id="viewIntakeHistoryRecords" type="button" value="Submit"/>
			</div>
			<div>
				<input id="cancel" type="submit" value="Cancel" />
			</div>
			<div>
				<input id="refresh" type="submit" value="Refresh" />
			</div>
		</div>
		<div id="notice">
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<bean:write name="ProdSupportForm" property="msg" />
			</logic:notEqual> 
		</div>
	</div>
</body>
</html:html>