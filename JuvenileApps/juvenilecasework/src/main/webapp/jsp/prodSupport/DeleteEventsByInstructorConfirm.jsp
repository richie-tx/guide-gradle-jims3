<!DOCTYPE HTML>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/DeleteEventsByInstructor.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<style>
	 .deleteTbl {
	 	border: 1px solid black;
	 	width: 500px;
		margin-left: auto;
		margin-right: auto;
		margin-top : 10px;
		margin-bottom: 10px;
	 }
	 
	.deleteTbl  th {
	 	font-family: Geneva, Arial, Helvetica, sans-serif;
		font-size: small;
		font-weight: bold;
		color: #000000;
		background-color: #cccccc;
	 }
	 
	 .deleteTbl th,  td {
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
	
</script>
</head>

<body>

<h2 align="center">Production Support - Event by Instructor Deleted</h2>
<hr>
<div class="container">
	<div>
		<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
				<logic:notEqual name="ProdSupportForm" property="msg" value="">
					<bean:write name="ProdSupportForm" property="msg" />
				</logic:notEqual> 
		</font>
	</div>
	<div>
		<table class="deleteTbl" >
			<logic:notEmpty name ="ProdSupportForm" property="services">
				<bean:define id="serviceEventId">
		   				 <bean:write  name="ProdSupportForm" property="serviceEventId" />
				</bean:define>
				<tr>
					<th>Juvenile Number</th>
					<th>Instructor Number</th>
					<th>Instructor Name</th>
					<th>Service Provider ID</th>
				</tr>
				<logic:iterate id="service" name ="ProdSupportForm" property="services" >
					<logic:iterate id="serviceContext" name ="service" property="serviceContexts" >
						<logic:equal name="serviceContext" property="serviceEventId" value="<%= serviceEventId  %>">
							<tr>
								<td><bean:write name="serviceContext" property="juvenileId"/></td>
								<td><bean:write name="ProdSupportForm" property="instructorId"/></td>
								<td>
									<logic:notEmpty name="ProdSupportForm" property="instructor">
										<bean:write name="ProdSupportForm" property= "instructor.lastName" />, <bean:write name="ProdSupportForm" property= "instructor.firstName" /> 
									</logic:notEmpty>
								</td>
								<td>
									<logic:notEmpty name="ProdSupportForm" property="instructor">
										<bean:write name="ProdSupportForm" property= "instructor.serviceProviderId" /> 
									</logic:notEmpty>
								</td>
							</tr>
						</logic:equal>
					</logic:iterate>					
				</logic:iterate>
			</logic:notEmpty>
		</table>
	</div>
	<div>
		<table class="deleteTbl">
			<logic:notEmpty name="ProdSupportForm" property="programs">
				<tr>
					<th>Service Provider ID</th>
					<th>Program ID</th>
					<th>Program Name</th>
				</tr>
				<logic:iterate id="program" name="ProdSupportForm" property="programs">
					<tr>
						<td><bean:write name="program" property="serviceProviderId"/></td>
						<td><bean:write name="program" property="providerProgramId"/></td>
						<td><bean:write name="program" property="programName"/></td>
					</tr>
				</logic:iterate>
			</logic:notEmpty>
		</table>
	</div>
	<div class="command">
		<html:form method="post" action="/MainMenu" onsubmit="return this;">
			<html:submit onclick="return this;" value="Back to Main Menu"/>
		</html:form>
	</div>
	
</div>
	


</body>
</html:html>