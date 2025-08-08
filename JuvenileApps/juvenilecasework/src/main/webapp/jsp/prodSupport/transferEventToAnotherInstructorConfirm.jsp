<!DOCTYPE HTML>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/transferEventToAnotherInstructorConfirm.jsp</title>
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
</head>

<body>

<h2 align="center">Production Support - Transfer Event by Instructor</h2>
<hr>
<div>
		<font style="font-weight: bold;" color="#008000" size="3" face="Arial"> 
				<logic:notEqual name="ProdSupportForm" property="msg" value="">
					<bean:write name="ProdSupportForm" property="msg" />
				</logic:notEqual> 
		</font>
	</div>
<div>
	<div align="center">
		<table class="transferTbl">
			<tr>
				<th>Event ID</th>
				<th>Event Name</th>
			</tr>
			<bean:define id="serviceEventId">
				<bean:write  name="ProdSupportForm" property="serviceEventId" />
			</bean:define>
			<logic:iterate id="service" name="ProdSupportForm" property="services">
				<logic:equal name="service" property="serviceEventId" value="<%= serviceEventId  %>">
					<tr>
						<td><bean:write name="service" property="serviceEventId"/></td>
						<td><bean:write name="service" property="serviceName"/></td>
					</tr>
				</logic:equal>
			</logic:iterate>
		</table>
		</html:form>
	</div>
	<div class="command">
			<html:form method="post" action="/MainMenu" onsubmit="return this;">
				<html:submit onclick="return this;" value="Back to Main Menu"/>
			</html:form>	
		</div>
</div>
</body>
</html:html>