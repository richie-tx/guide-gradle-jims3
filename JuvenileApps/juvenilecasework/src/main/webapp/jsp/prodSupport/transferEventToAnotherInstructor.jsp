<!DOCTYPE HTML>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/transferEventToAnotherInstructor.jsp</title>
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
		
		$('input[type=radio][name=selectedTransferInstructorId]').change(function(){
			$("#selectedTransferInstructorId").val(this.value);
			console.log( $("#selectedTransferInstructorId").val() );
		});
		
		
		$("#transferBtn").click(function(){
			spinner();
			if ($("#selectedTransferInstructorId").val() == "" ){
				alert("Please select a radio button.");
				$(".overlay").remove();
				return false;
			}
			if (true){
				$("#PerformTransferEventToAnotherInstructor").submit();
			}
		})
		
	})
</script>
</head>

<body>

<h2 align="center">Production Support - Transfer Event by Instructor</h2>
<hr>
<div>
	<h3>List of Instructor</h3>
</div>
<div>
	<div align="center">
		<table class="transferTbl">
			<logic:iterate id="instructor" name="ProdSupportForm" property="instructors">
					<tr>
						<td><input type="radio" name="selectedTransferInstructorId" value="<bean:write name="instructor" property="OID"/>"/></td>
						<td><bean:write name="instructor" property="lastName"/>, <bean:write name="instructor" property="firstName"/> </td>
					</tr>
				</logic:equal>
			</logic:iterate>
		</table>
		</html:form>
	</div>
	<div class="command">
		<div style="display:inline-block;">
			<html:form method="post" styleId="PerformTransferEventToAnotherInstructor"
										action="/PerformTransferEventToAnotherInstructor">
				<html:hidden styleId="selectedTransferInstructorId" name="ProdSupportForm" property="selectedTransferInstructorId"/>
				<html:submit styleId="transferBtn" property="submitAction"><bean:message key="button.transfer"></bean:message></html:submit>				
			</html:form>
		</div>
		<div style="display:inline-block;">
			<html:form method="post" action="/MainMenu" onsubmit="return this;">
				<html:submit onclick="return this;" value="Back to Main Menu"/>
			</html:form>
		</div>
	</div>
</div>
</body>
</html:html>