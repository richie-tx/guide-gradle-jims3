<!DOCTYPE HTML>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/UpdateFamilyConstellationQuery.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script>
	$(document).ready(function(){
		$("#juvenileId").val("");
		$("#familyMemberId").val("");
		
		var juvenileId = $("#juvenileId").val();
		var familyMemberId = $("#familyMemberId").val();
		
		$("#submitBtn").click(function(e){
			event.preventDefault();
			if ( validateInput() ) {
				spinner();
				$("#updateFamilyConstellationForm").submit();
			} 
		})
		
		$("#refreshBtn").click(function(){
			$("#juvenileId").val("");
			$("#familyMemberId").val("");
		})
		
		$("#cancelBtn").click(function(){
			window.location.href = '/JuvenileCasework/DisplayProductionSupportMainPopup.do';
		})
		
		//disbale familymemberId when juvId has a value and looses focus
		$("#juvenileId").focusout(function(){ //loose focus
			juvenileId = $("#juvenileId").val();
			
			if(juvenileId){	
				$("#familyMemberId").val("");
				$('#familyMemberId').prop('disabled', true);
			} else {
				$('#familyMemberId').prop('disabled', false);
			}	
		});
		
		//disbale familymemberId when juvId has a value and looses focus
		$("#familyMemberId").focusout(function(){ //loose focus
			familyMemberId = $("#familyMemberId").val();
			console.log("famId", familyMemberId);
			
			if(familyMemberId){
				$("#juvenileId").val("");
				$('#juvenileId').prop('disabled', true);
			} else {
				$('#juvenileId').prop('disabled', false);
			}			
			
			
		});
		 
		function validateInput(){			
			
			var juvenileId = $("#juvenileId").val();
			var familyMemberId = $("#familyMemberId").val();
			
			if (!juvenileId && !familyMemberId) {
				alert('juvenileId or familyMemberId is required');
				return false;
			}
			
			if (juvenileId && familyMemberId) {
				alert('you can only search by juvenileId or familyMemberId but not both');
				return false;
			}
			
			if(familyMemberId)
			{
				if(!isValidNumber(familyMemberId)){
					alert('familyMemberId must be a number');
					return false;
				}
				
				$("#constellationQueryType").val('familyMemberId');
			}
			
			if(juvenileId)
			{
				if(!isValidNumber(juvenileId)){
					alert('juvenileId must be a number');
					return false;
				}
				
				$("#constellationQueryType").val('juvenileId');
			}
			
			
			return true;
		}
		
		function isValidNumber(value) {
			const numberPattern = /^-?\d+(\.\d+)?$/;
		    return numberPattern.test(value);
		}
		
		
	})
	
</script>
</head>

<body>

<h2 align="center">Production Support - Update Family Constellation Query </h2>
<hr>
<table width="80%" align="center">
	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<bean:write name="ProdSupportForm" property="msg" />
			</logic:notEqual> 
			</font></td>
	</tr>
</table>
<p align="center">Please enter a Juvenile ID or Family Member ID to view Family Constellation</p>
	<div align="center">
	<html:form styleId="updateFamilyConstellationForm" method="post" action="/UpdateFamilyConstellationQuery?list=Y" onsubmit="return this;">
	<table border="0" width="700">
		
		<tr>
			<td align="center"><font face="" style="FONT-FAMILY: Arial"
				color="#0000aa"><strong>Family Member ID: </strong></font>
					&nbsp;&nbsp;&nbsp;&nbsp;
				<html:text styleId="familyMemberId" name="ProdSupportForm" property="familyMemberId" ></html:text>
			</td>										
				<td align="center" style="font-size:12px; font-weight: bold">
					&nbsp;&nbsp; OR &nbsp;&nbsp;
				</td>
				<td align="center"><font face="" style="FONT-FAMILY: Arial"	color="#0000aa">
					<strong>Juvenile ID: </strong></font>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<html:text styleId="juvenileId" name="ProdSupportForm" property="juvenileId"></html:text>
				</td>
				<html:hidden styleId="constellationQueryType" name="ProdSupportForm" property="constellationQueryType"></html:hidden>		
		</tr>
		<tr>
		<td colspan=3>&nbsp;</td>
		</tr>	 
		<tr>
			<td align="center" colspan=3>
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
	
	</table>
	
	</div>

</body>
</html:html>