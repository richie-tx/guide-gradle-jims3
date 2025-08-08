<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>
<%-- 03/23/2016	RCarter	   New initial query jsp for facilities --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
 
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/updateFamilyMemberRelationQueryResultList.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script language="javascript">
$(document).ready(function(){
	
	//clear the input boxes
	$('#familyConstellationId').val('');
	$('#familyMemberId').val('');
	
	$('input[type=radio][name=familyConstellationId]').change(function() {
		$("#updateBtn").prop("disabled", false);
	});
	
	$("#updateBtn").click(function(){
		
		if(isValid()){
			//var response = confirm("Are you sure you want to proceed?")
			//if (response ) {
				spinner();
				$("#updateFamilyMemberRelationForm").submit();
			//}
		} else {
			return false;
		}
	})
	
	$("#cancelBtn").click(function(){
		  $('#updateFamilyMemberForm').attr('action', "/JuvenileCasework/UpdateFamilyMemberRelationQuery?list=Y").submit();
	})
	
})

	function isValid(){
		
		var familyConstellationId = $("#familyConstellationId").val();
		var familyMemberId = $("#familyMemberId").val();
		
		if (!familyConstellationId) {
			alert('Constellation ID is required');
			return false;
		}
		
		if(familyMemberId)
		{
			if(!isValidNumber(familyMemberId)){
				alert('Family Member ID must be a number');
				return false;
			}
			
			$("#constellationQueryType").val('familyMemberId');
		}
		
		if(familyConstellationId)
		{
			if(!isValidNumber(familyConstellationId)){
				alert('Constellation ID must be a number');
				return false;
			}
			
			$("#constellationQueryType").val('familyConstellationId');
		}
		
		$('input[type=radio]:checked').each(function(){
			var result = false;
			
			if(this.checked === true){
				return true;
			} else {
				alert('You must select a record. Please click the radio button to select a record.');
				return false;
			}
		});
		
		
		return true;		
 	}
 	
 	function isValidNumber(value) {
		const numberPattern = /^-?\d+(\.\d+)?$/;
	    return numberPattern.test(value);
	}
	
	function fillId(constellationId, familyMemberId)
	{
		console.log(constellationId, familyMemberId);
		$('#familyMemberId').val(familyMemberId);
		$('#familyConstellationId').val(constellationId);
		
		$('#fmId').val(familyMemberId)
		
		console.log($('#fmId').val());
		document.forms[0].familyMemberId.value = familyMemberId;
		document.forms[0].familyConstellationId.value = constellationId;		
		
		return true;
	}
</script>

</head>

<body>

<h2 align="center">Production Support - Family Member Relation Update Results</h2>

<logic:notEmpty name="ProdSupportForm" property="familyMemberId">
	<h2>
		Family Member ID:&nbsp;<bean:write name="ProdSupportForm" property="familyMemberId" />
	</h2>
</logic:notEmpty>
<logic:notEmpty name="ProdSupportForm" property="familyConstellationId">
	<h2>
		Constellation ID:&nbsp;<bean:write name="ProdSupportForm" property="familyConstellationId" />
	</h2>
</logic:notEmpty>
<hr>

<logic:notEmpty name="ProdSupportForm" property="familyConstellationMemberList">
	<p align="center" style="font-size: 14px">Select an item from the list by clicking a radio button and then click Next.</p>
</logic:notEmpty>

<div align="center">
<html:form styleId="updateFamilyMemberRelationForm" method="post" action="/UpdateFamilyMemberRelationQuery?list=N" onsubmit="return this;">

<br />

<input id="consMemberList" type="hidden" value="<bean:write name="ProdSupportForm" property="familyConstellationMemberList"/>" />

<logic:notEmpty name="ProdSupportForm" property="familyConstellationMemberList">

    <table border="1" width="98%" align="center">

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CONSTELLATION ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">FAMILY MEMBER ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CONSRELATION ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RELTYPE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">GUARDIAN</font></td>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">INHOUSESTAT</font></td>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PRIMARY CONTACT</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PRIMARY CAREGIVER</font></td>			
	</tr>
	
	<logic:iterate id="familyConstellationMember" name="ProdSupportForm" property="familyConstellationMemberList">

	<tr>
		<td>
	 		<input type="radio" name="radioProp" value="<bean:write name="familyConstellationMember" property="familyConstellationId"/>"
	 		onclick="fillId(<bean:write name="familyConstellationMember" property="familyConstellationId"/>, <bean:write name="familyConstellationMember" property="theFamilyMemberId"/>)">
	 		<bean:write name="familyConstellationMember" property="familyConstellationId"/>
	 		<html:hidden styleId="fmId" name="ProdSupportForm" property="familyMemberId"></html:hidden>
		</td>
		<td><font size="-1"><bean:write name="familyConstellationMember" property="theFamilyMemberId" />&nbsp;</font></td>	
		<td><font size="-1"><bean:write name="familyConstellationMember" property="OID" />&nbsp;</font></td>					
		<td><font size="-1"><bean:write name="familyConstellationMember" property="relationshipToJuvenileId" />&nbsp;</font></td>	
		<td><font size="-1"><bean:write name="familyConstellationMember" property="guardianYesNo"/>&nbsp;</font></td>			
		<td><font size="-1"><bean:write name="familyConstellationMember" property="inHomeStatusYesNo" />&nbsp;</font></td>			
		<td><font size="-1"><bean:write name="familyConstellationMember" property="isPrimaryContactYesNo" />&nbsp;</font></td>
		<td><font size="-1"><bean:write name="familyConstellationMember" property="isPrimaryCareGiverYesNo" />&nbsp;</font></td>				
	</tr>				

	</logic:iterate>
  </table>
  <table border="0" width="700">
  	<tr>
		<td colspan="4"><br /></td>
	</tr>
	<tr>
		<td align="right">
		<font face="" style="FONT-FAMILY: Arial" color="#0000aa"><strong> Selected Constellation ID:</strong></font>
		</td>
		<td><html:text styleId="familyConstellationId" name="familyConstellationMember" property="familyConstellationId" size="10" maxlength="15" readonly="true"/>
		<td align="right">
		<font face="" style="FONT-FAMILY: Arial" color="#0000aa"><strong> Selected Family Member ID:</strong></font>
		</td>
		<td><html:text styleId="familyMemberId" name="familyConstellationMember" property="theFamilyMemberId" size="10" maxlength="15" readonly="true"/>
	</tr>
	<tr>
		<td colspan="4"><br /></td>
	</tr>
	<tr>
		<td colspan="4" align="center"><html:submit styleId="updateBtn"  value="Next" /></td>
	</tr>

	<tr>
		<td>&nbsp;</td>
	</tr>
</table>
</logic:notEmpty>

	
<logic:empty name="ProdSupportForm" property="familyConstellationMemberList">
	<table align="center" border="0" width="700">
		<tr>
			<td align="center"><h4 style="color: red"><i>No Family Relation record(s) found.</i></h4></td>
		</tr>
	</table>
</logic:empty>	

	&nbsp;
</html:form>
	
	<table border="0">
		<tr>
		<td align="center">
		<html:form method="post" action="/UpdateFamilyMemberRelationQuery?clr=Y" onsubmit="return this;">
		<html:submit onclick="return this;" value="Search a Different Record"/>
		</html:form>
		</td>
	
		<td>&nbsp;</td>
	
		<td align="center">
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