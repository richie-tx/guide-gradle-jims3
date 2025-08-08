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
<title>Juvenile Casework -/prodSupport/deleteMsReferralQuery</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/deleteReferral.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
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
<script type='text/javascript'>
	$(document).ready(function(){
			$("#deleteMsReferral").click(function(){
				if ( isValid() ){
					spinner();
					$("#deleteMsReferralForm").submit();
				} 
			});
			
			$("#cancel").click(function(){
				closeWindow();
			})
			
			
			$("#refresh").click(function(){
				$("#juvenileId").val("");
				$("#referralId").val("");
				$("#notice").html("");
			});
			
			$("#mainMenu").click(function(){
				$('form').attr("action","DisplayProductionSupportMainPopup.do");
				$('form').submit();
			})
			
			
	});
	
	function isValid(){
		var juvenileId = $("#juvenileId").val();
		var referralId = $("#referralId").val();
		
		if ( juvenileId === ""){
			$("#notice").html( "Juvenile number is required." );
 			return false;	
		}
		
 		if( !(  $.isNumeric(juvenileId)
 			&& juvenileId.length == 6 ) ) {
 			$("#notice").html( "Juvenile number is invalid. Please retry search." );
 			return false;	
 		}
 		
 		if (! $.isNumeric(referralId) && referralId != ""  ){
 			$("#notice").html( "Referral number is invalid. Please retry search." );
 			return false
 		}
 		
 		return true;
 	}
 	
	function closeWindow(el)
	{
		window.close();
	    return 0;
	}
</script>
</head>
<body>
<h2 align="center">Production Support - Search Juvenile Referral Record - Delete </h2>
<hr>

<p align="center">Please enter a Juvenile ID and Referral Number to view current referral data.</p>
<div id="container">
	<div id="inputFields">
			<html:form method="post" styleId="deleteMsReferralForm"
										action="/DeleteMsReferralQuery">
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
				<input id="deleteMsReferral"  type="button" value="Submit"/>
			</div>
			<div>
				<input id="cancel" type="submit" value="Cancel" />
			</div>
			<div>
				<input id="refresh" type="submit" value="Refresh" />
			</div>
	</div>
	<div>
			<div>
				<input id="mainMenu" type="submit" value="Back to Main Menu" />
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