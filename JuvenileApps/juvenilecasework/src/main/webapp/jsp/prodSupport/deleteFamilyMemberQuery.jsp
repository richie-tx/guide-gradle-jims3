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
<title>Juvenile Casework -/prodSupport/deleteFamilyMemberQuery.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<style>
	#container{
		text-align: center;
		width: 700px;
		height: 600px;
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
		$("#viewFamilyMember").click(function(){
			if ( isValid() ) {
				spinner();
				$("#viewFamilyMemberForm").submit();
			} else {
				$("#notice").html( "Please input a valid family member number." );
			}
		
		});
		
		$("#mainMenu").click(function(){
			$("#mainMenuForm").submit();
		});
		
		$("#refresh").click(function(){
			$("#familyMemberId").val("");
			$("#notice").html("");
		});
		
		//clear the family member Id input on initial load
		$('#familyMemberId').val('');
  });
  
 	function isValid(){
 		if( $.isNumeric($("#familyMemberId").val() )
 			 ){
 			return true;	
 		} else {
 			return false;
 		}
 	}

</script>
</head>
<body>
	<div id="container" style="margin-top: 50px; width: 95%; align: center">
		<h2>Production Support - Delete Family Member</h2>
		<hr>
		<p align="center">Please enter a Family Member ID to view Family Member Details</p>
		<div id="inputFields">
			<html:form method="post" styleId="viewFamilyMemberForm"
									  action="/DeleteFamilyMemberQuery?list=Y" onsubmit="return this;">
				<div>
					<label><strong>Family Member ID: </strong></label>
					<html:text styleId = "familyMemberId" name="ProdSupportForm"
									   property="familyMemberId"
									   size="10" maxlength="15" />				
				</div>
			</html:form>
			<html:form method="post" styleId="mainMenuForm"
									 action="/MainMenu" onsubmit="return this;">
			</html:form>
		</div>
		<div id="command">
			<div>
				<input id="viewFamilyMember" type="button" value="Submit"/>
			</div>
			<div>
				<input id="mainMenu" type="submit" value="Cancel" />
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