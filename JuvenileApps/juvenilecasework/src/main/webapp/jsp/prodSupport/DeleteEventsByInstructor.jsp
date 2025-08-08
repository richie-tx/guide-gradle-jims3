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
	 	margin-top: 5px;
	 }
	 
</style>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script>
	$(document).ready(function(){
		$("#deleteBtn").click(function(){
			if(confirm('Are you sure you want to delete juveniles from the event?')) {
				spinner();
				return true;	
			} else {
				return false;
			}
		})
	})
</script>
</head>

<body>

<h2 align="center">Delete - Event by Instructor Query</h2>
<hr>
<div class="container">
	<html:form styleId="PerformDeleteEventsByInstructor" action="/PerformDeleteEventsByInstructor" onsubmit="return this;">
	<div>
		<table class="deleteTbl">
			<logic:notEmpty name ="ProdSupportForm" property="services">
				<bean:define id="serviceEventId">
	   				 <bean:write  name="ProdSupportForm" property="serviceEventId" />
				</bean:define>
						<tr>
							<th>Juvenile Number</th>
							<th>Event ID</th>
						</tr>
						<tr>
							<td style="border: 0px solid black; padding-left: 0px; padding-right: 0px;">
								<table style="width: 436px; margin-right: 0px; border: 0px solid black;"  >
									<logic:iterate id="service" name ="ProdSupportForm" property="services" >
											<logic:iterate id="serviceContext" name ="service" property="serviceContexts" >
												<logic:equal name="serviceContext" property="serviceEventId" value="<%= serviceEventId  %>">
													<tr>
														<td><bean:write name="serviceContext" property="juvenileId"/></td>
													</tr>
												</logic:equal>
											</logic:iterate>
										
									</logic:iterate>
								</table>
							</td>
							<td>
								<bean:write name="ProdSupportForm" property="serviceEventId"/>
							</td>
						</tr>
			</logic:notEmpty>
		</table>
	</div>
	<div class="command">
		<html:hidden styleId="serviceEventId" name="ProdSupportForm" property="serviceEventId"/>
		<html:submit styleId="deleteBtn" property="submitAction"><bean:message key="button.delete"></bean:message></html:submit>
	</div>
	</html:form>
	<div>
		<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
				<logic:notEqual name="ProdSupportForm" property="msg" value="">
					<bean:write name="ProdSupportForm" property="msg" />
				</logic:notEqual> 
		</font>
	</div>
</div>
	


</body>
</html:html>