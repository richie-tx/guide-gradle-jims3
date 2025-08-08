<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>

<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Juvenile Casework -/prodSupport/deleteFamilyConstellationQueryResult</title>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />	
<style>
	#command div {
		margin-top: 20px;
		display: inline-block;
	}
</style>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script>
	$(document).ready(function(){
		
		$('input[type=radio][name=constellationId]').change(function() {
			
			//prevent deletion if only 1 constellation record exists
			if($('input[name=constellationId]').length === 1){
				alert("This is the only active family constellation for the juvenile, no deletion allowed.")
				$("#deleteBtn").prop("disabled", true);
				return false;
			}
			
			$("#deleteBtn").prop("disabled", false);
		});
		
		if ($('input[name=constellationId]:checked').length > 0) {
			$("#deleteBtn").prop("disabled", false);
		} else {
			$("#deleteBtn").prop("disabled", true);
		}
		
		
		$("#deleteBtn").click(function(){		

			if ( confirm("Are you sure you want to delete this family constellation?") ) {
				$("#deleteFamilyConstellationForm").submit();
			}
			
		})
		
		$("#cancelBtn").click(function(){
			  $('#deleteFamilyConstellationForm').attr('action', "/JuvenileCasework/deleteFamilyConstellationQuery.do?clr=Y").submit();
		})		
		
		console.log($('input[name=constellationId]').length );
		
	})
</script>


</head>
<body>
<html:form styleId="deleteFamilyConstellationForm" action="/performDeleteFamilyConstellation">
	<div align="center">
		<h2>Family Constellation List</h2>
		<hr>
		<p align="center"><b><i>Select the radio button next to the record <br>
			you want to delete and click the Submit button.</i></b></p>
		<br><br>
		<table width="100%">
			<tr>
				<td align="center" class="errorAlert"><html:errors></html:errors></td>
			</tr>
		</table>
		<table class="resultTbl" border="1" width="700" align="center">
			<tr>
				<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">Juvenile Number</font></th>
				<td><bean:write name="ProdSupportForm" property="juvenileId"/></td>
			</tr>
			<tr>
				<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">Juvenile Name</font></th>
				<td><bean:write name="ProdSupportForm" property="juvenileName"/></td>
			</tr>
		</table>
		<br><br><br>
		<table class="resultTbl" border="1" width="1000" align="center">
			<thead>
				<tr>
					<th></th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">Family #</font></th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">Entry Date</font></th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">Active</font></th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">Guardian Name(s)</font></th>
				</tr>
			</thead>
			<tbody>
				<logic:iterate id="familyConstellation" name="ProdSupportForm" property="familyConstellationResults">
					<tr>
						<td>
							<input type="radio" 
										name="constellationId" 
										value='<bean:write name="familyConstellation" property="familyNum"/>'
							/>
						</td>
						<td><bean:write name="familyConstellation" property="familyNum"/></td>
						<td><bean:write name="familyConstellation" property="entryDate" format="MM/dd/yyyy"/></td>
						<logic:equal name="familyConstellation" property="active" value="true">
							<td>Yes</td>
						</logic:equal>
						<logic:equal name="familyConstellation" property="active" value="false">
							<td>No</td>
						</logic:equal>
						<td><bean:write name="familyConstellation" property="guardiansNames"/></td>
					</tr>
				</logic:iterate>
			</tbody>
		</table>
	</div>
	
	<div id="command">
		<div>
			<input id="deleteBtn" type="button" value="Submit"/>
		</div>
		<div>
			<input id="cancelBtn" type="button" value="Cancel"/>
		</div>
		
	</div>
	
	<div style="margin-left: 20px">
	<br />
	<br />
		<table width="800px">
			<tr align="center">
				<td>
					<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
					<logic:notEqual name="ProdSupportForm" property="msg" value="">
						<bean:write name="ProdSupportForm" property="msg" />
					</logic:notEqual> 
					</font>
				</td>
			</tr>
		</table>
	</div>
</html:form>
</body>