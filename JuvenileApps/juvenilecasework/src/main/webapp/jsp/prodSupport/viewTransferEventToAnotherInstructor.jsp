<!DOCTYPE HTML>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/viewTransferEventToAnotherInstructor.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<style>
	 .transferTbl {
	 	border: 1px solid black;
	 	width: 500px;
		margin-left: auto;
		margin-right: auto;
		margin-top : 10px;
		margin-bottom: 10px;
	 }
	 
	.transferTbl  th {
	 	font-family: Geneva, Arial, Helvetica, sans-serif;
		font-size: small;
		font-weight: bold;
		color: #000000;
		background-color: #cccccc;
	 }
	 
	 .transferTbl th,  td {
	 	border: 1px solid black;
	 }
	 
	 .command{
	 	margin-top: 10px;
	 }
	 
</style>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script>
	$(document).ready(function(){
		$("#transferBtn").click(function(){
			$('form')
			.attr(
					'action',
					"/JuvenileCasework/PerformTransferEventToAnotherInstructor.do?submitAction=Display All");
			spinner();
			$('form').submit();
		})
			
		$("#cancelBtn").click(function(){
			 window.history.back();
		})
		
		$("#backBtn").click(function(){
			$('form').attr("action","/JuvenileCasework/MainMenu.do?selectedMenuItem=eventTransferToAnotherInstructor");
			$('form').submit();
		})
		
	})
</script>
</head>

<body>

<h2 align="center">Production Support - Transfer to Another Instructor</h2>
<hr>
<div>
	<div align="center">
		<table class="transferTbl">
			<tr>
				<th>Date </th>
				<th>Event ID </th>
				<th>Event Name </th>
			</tr>
			<bean:define id="serviceEventId">
				<bean:write  name="ProdSupportForm" property="serviceEventId" />
			</bean:define>
			<logic:iterate id="service" name="ProdSupportForm" property="services">
				<logic:equal name="service" property="serviceEventId" value="<%= serviceEventId  %>">
					<tr>
						<td><bean:write name="service" property="startDatetime" format="MM/dd/yyyy"/></td>
						<td><bean:write name="service" property="serviceEventId"/></td>
						<td><bean:write name="service" property="serviceName"/></td>
					</tr>
				</logic:equal>
			</logic:iterate>
		</table>
	</div>
	<div class="command">
		<div>
			<html:form method="post" styleId="/PerformTransferEventToAnotherInstructor"
										action="/PerformTransferEventToAnotherInstructor">
				<input id="cancelBtn" type="button" value="Cancel" />
				<input id="transferBtn" type="button" value="Transfer to Another Instructor"/>				
			</html:form>
		</div>
		<div style="margin-top:5px;">
			<input id="backBtn" type="button" value="Back To Query" />
			
		</div>
	</div>
</div>
</body>
</html:html>