<!DOCTYPE HTML>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/UpdateFamilyMemberQuery.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script>
	$(document).ready(function(){
		
		$("#submitBtn").click(function(e){
			event.preventDefault();
			if ( validateInput() ) {
				spinner();
				$("#updateFamilyMemberForm").submit();
			} else {
				alert("Family Member Id is required.");
			}
		})
		
		$("#refreshBtn").click(function(){
			$("#familyMemberId").val("");
		})
		
		$("#cancelBtn").click(function(){
			window.location.href = '/JuvenileCasework/DisplayProductionSupportMainPopup.do';
		})
		
		function validateInput(){
			var familyMemberId = $("#familyMemberId").val();
			
			if ( familyMemberId === ""
							|| familyMemberId.length == 0   ) {
				return false;
			}
			
			if(isNaN(parseInt(familyMemberId))){
				alert("family Member Id must be a number");
				return false;
			}
			
			return true;
		}
	})
</script>
</head>

<body>

<h2 align="center">Production Support - Update Family Member Query </h2>
<hr>

<p align="center">Please enter a Family Member ID to view Family Member</p>
	<div align="center">
	<html:form styleId="updateFamilyMemberForm" method="post" action="/UpdateFamilyMemberQuery" onsubmit="return this;">
	<table border="0" width="700">
		
		<tr>
			<td align="center"><font face="" style="FONT-FAMILY: Arial"
				color="#0000aa"><strong>Family Member ID </strong></font>
			&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="familyMemberId" name="familyMemberId" type="number" />
			</td>
		</tr>	
		<tr>
		<td>&nbsp;</td>
		</tr>	 
		<tr>
			<td align="center">
				<input id="submitBtn" type="button" value="Submit" />&nbsp;&nbsp;
				<input id="cancelBtn" type="button" value="Cancel" />&nbsp;&nbsp;
				<input id="refreshBtn" type="button" value="Refresh" />
			</td>
		</tr>		

		<tr>
		<td>&nbsp;</td>
		</tr>
		</table>
	</html:form>
	
	<table border="0" width="700">
		<tr>
		<td colspan="2" align="center">
		<html:form method="post" action="/MainMenu" onsubmit="return this;">
		<html:submit onclick="return this;" value="Back to Main Menu"/>
		</html:form>
		</td>
		</tr>

	</table>
	
	<table border="0" width="700">
	
	<tr><td colspan="4">&nbsp;</td></tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	
	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<bean:write name="ProdSupportForm" property="msg" />
			</logic:notEqual> 
			</font></td>
	</tr>
	</table>
	
	</div>

</body>
</html:html>