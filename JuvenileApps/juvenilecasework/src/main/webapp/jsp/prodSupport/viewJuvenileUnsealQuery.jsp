<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/viewJuvenileUnsealQuery.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script type='text/javascript'>

$(document).ready(function(){
	$("#juvenileNum").val(""); 
	
	$("#refresh").click(function(){
		$("#juvenileNum").val("");
		$("#notice").html("");
	});
	
 	$("#search").click(function(){
		if ($("#juvenileNum").val().length > 0 ) {
			if ( $.isNumeric( $("#juvenileNum").val() ) && $("#juvenileNum").val().length < 9  ) {
				if($("#juvenileNum").val().substring(0,1)=='0'){
					$("#notice").html("Juvenile number is invalid. Please retry search.");
				}
				else {
					spinner();
					$("#viewJuvenilePurge").submit();
				}
			} else {
				$("#notice").html("Juvenile number is invalid. Please retry search.");
			}
		} else {
			$("#notice").html("Juvenile number is Required!");
		}		
	}) 
	
})


</script>
<style>
	#container{
		text-align: center;
	}
	
	label {
		color: #0000aa;
	}
	
	#command div {
		display: inline-block;
		margin : 30px 10px auto 10px;
	}
	
	#command input {
		width : 150px;
	}
	
	#notice{
		margin-top: 30px;
		color: #FF0000;
		font-weight: bold;
	}
	
</style>
</head>
<body>
	<h2 align="center">Un-Seal Juvenile Master and/or Juvenile Referral</h2>
	<hr>
	<div id="container">
		<p align="center">Please enter a Juvenile Number to find out what data exists.</p>
		<div id="inputFields">
			<html:form method="post" styleId="viewJuvenilePurge" action="/ViewJuvenileUnsealQuery" onsubmit="return this;">
				<label>Juvenile Number:</label>
				<html:text property="fromJuvenileId" styleId="juvenileNum" size="10" maxlength="8" />
			</html:form>
		</div>
		<div id="command">
			<div>
				 <input id="search" type="button" value="Submit" /> 
			</div>
			<div>
				<input id="refresh" type="button" name="refreshIt" value="Refresh" />
			</div>
			<div>
				<!-- <input id="cancel" type="button" name="closeIt" value="Cancel" onclick="self.close()"  /> -->
				<input type="submit" name="submitAction" value="<bean:message key='button.cancel'/>"
				onclick="changeFormActionURL('/<msp:webapp/>globalBack.do', true)">
			</div>
		</div>
		<div id="notice">
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
					<bean:write name="ProdSupportForm" property="msg"/>
			</logic:notEqual> 
		</div>
	</div>
	</body>
</html:html>