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
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/transferEventToAnotherInstructorQueryResult.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<style>
	#container {
		text-align: center;
	}
	
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
</style>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script>
	$(document).ready(function(){
		$('input[type=radio][name=serviceEventId]').change(function(){
			$("#serviceEventId").val(this.value);
			console.log( $("#serviceEventId").val() );
		});
		
		$("#nextBtn").click(function(){
			spinner();
			if ($("#serviceEventId").val() == "" ){
				alert("Please select a radio button.");
				$(".overlay").remove();
				return false;
			}
			
		})
		
		$("#cancelBtn").click(function(){
			 window.history.back();
		})
	})
</script>
</head>

<body>
	<div class="container">
		<h2 align="center">Result for Instructor ID = <bean:write name="ProdSupportForm" property="instructorId"/> </h2>
		<div>
			<table class="resultTbl">
					<tr>
						<th>Instructor ID</th>
						<td><bean:write name="ProdSupportForm" property= "instructorId" /></td>
					</tr>
					<tr>
						<th>Instructor Name</th>
						<td>
							<logic:notEmpty name="ProdSupportForm" property="instructor">
								<bean:write name="ProdSupportForm" property= "instructor.lastName" />, <bean:write name="ProdSupportForm" property= "instructor.firstName" /> 
							</logic:notEmpty>
						</td>
					</tr>
			</table>
		</div>
		<div>
			<p align="center"><b><i>Select the radio button next to the record <br>
				you want to transfer and click Next button.</i></b></p>	
			<h3>Instructor Event Details</h3>
		</div>
		<div>
			<table class="resultTbl">
				<tr>
					<th></th>
					<th>Date &nbsp;
								<jims:sortResults beanName="ProdSupportForm" results="services" 
                            	primaryPropSort="startDatetime" primarySortType="DATE" defaultSort="true" 
                               	defaultSortOrder="ASC" sortId="1" /></th>
					<th>Event ID &nbsp;
								<jims:sortResults beanName="ProdSupportForm" results="services" 
                            	primaryPropSort="serviceEventId" primarySortType="STRING" defaultSort="true" 
                               	defaultSortOrder="ASC" sortId="2" /></th>
					<th>Event Name &nbsp;
								<jims:sortResults beanName="ProdSupportForm" results="services" 
                            	primaryPropSort="serviceName" primarySortType="STRING" defaultSort="true" 
                               	defaultSortOrder="ASC" sortId="3" /></th>
				</tr>
				<logic:iterate id="service" name="ProdSupportForm" property="services">
				<tr>
					<td><input type="radio" name="serviceEventId" value="<bean:write name="service" property="serviceEventId"/>"/></td>
					<td><bean:write name="service" property="startDatetime" format="MM/dd/yyyy"/></td>
					<td><bean:write name="service" property="serviceEventId"/></td>
					<td><bean:write name="service" property="serviceName"/></td>
				</tr>
				</logic:iterate>
			</table>
		</div>	
		<div class="command">
			<html:form method="post" styleId="PerformTransferEventToAnotherInstructor"
										action="/PerformTransferEventToAnotherInstructor">
				<html:hidden styleId="serviceEventId" name="ProdSupportForm" property="serviceEventId"/>
				<logic:notEmpty name="ProdSupportForm" property="services">
					<html:submit styleId="nextBtn" property="submitAction"><bean:message key="button.next"></bean:message></html:submit>
				</logic:notEmpty>
				<input id="cancelBtn" type="button" value="Cancel" />
			</html:form>	
		</div>
	</div>

</body>
</html:html>