<!DOCTYPE HTML>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/deleteDrugTestingSummary.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<style>
	#command{
		margin-top: 10px;
	}
	
	#command div{
		margin-top: 5px;
	}
</style>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script>
	$(document).ready(function(){
		 $("#deleteBtn").click(function(){
			 if( confirm('Are you sure you want to delete the drug testing record?') ) { 
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

	<h2 align="center">Production Support - Delete Family Member  Summary</h2>
<!-- Error Message Area -->
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
	
    <logic:notEqual name="ProdSupportForm" property="msg" value="">
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center">
				<bean:write name="ProdSupportForm" property="msg" />
	 		</td>
	 	</tr>
	</table>
	</logic:notEqual>
<!-- End Error Message Area -->
<p align="center" style="font-weight:bold; font-family: Arial; font-size: 14px; color: green"><i>The selected family member was successfully deleted</i></p>
<p align="center" style="font-weight:bold; font-size: 14px">
	<font  face="bold">
		<logic:notEmpty name="ProdSupportForm" property="familyMemberId">
			Family Member ID: &nbsp;<bean:write name="ProdSupportForm" property="familyMemberId" />
		</logic:notEmpty>
	</font>
</p>	
<bean:define id="familyConstellationMember" name="ProdSupportForm" property="familyConstellationMember" type="pd.juvenilecase.family.FamilyConstellationMember"/>
	
		<div align="center">
		<table class="resultTbl" border="1" width="60%" align="center">
			<thead>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						CONSRELATION ID
					</th>
					<td>
						<bean:write name="familyConstellationMember" property="OID" />
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						CONSTELLATION ID
					</th>
					<td>
						<bean:write name="familyConstellationMember" property="familyConstellationId" />
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						RELTYPE
					</th>
					<td>
						<bean:write name="familyConstellationMember" property="relationshipToJuvenileId" />
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						GUARDIAN
					</th>
					<td>
						<bean:write name="familyConstellationMember" property="guardianYesNo" />
					</td>
				</tr>	
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						INHOUSESTAT
					</th>
					<td>
						<bean:write name="familyConstellationMember" property="inHomeStatusYesNo" />
					</td>
				</tr>	
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						INVOLVEMENT LEVEL
					</th>
					<td>
						<bean:write name="familyConstellationMember" property="involvementLevelId" />
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						PARENTS RIGHTS TERMIMATED
					</th>
					<td>
						<bean:write name="familyConstellationMember" property="isParentalRightsTerminatedYesNo" />
					</td>
				</tr>	
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						PRIMARY CONTACT
					</th>
					<td>
						<bean:write name="familyConstellationMember" property="isPrimaryContactYesNo" />
					</td>
				</tr>					
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						DETENTION HEARING
					</th>
					<td>
						<bean:write name="familyConstellationMember" property="isDetentionHearingYesNo" />
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						VISIT
					</th>
					<td>
						<bean:write name="familyConstellationMember" property="isDetentionVisitationYesNo" />						
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						PRIMARY CAREGIVER
					</th>
					<td>
						<bean:write name="familyConstellationMember" property="isPrimaryCareGiverYesNo" />
					</td>
				</tr>				
				
			</thead>
		</table>
	</div>

	<div id="nav">
		<div style="margin-top: 15px" align="center">
			<table>
				<tr>
				<td align="center">
					<html:form method="post" action="/DeleteFamilyMemberQuery?clr=Y" onsubmit="return this;">
						<html:submit onclick="return this;" value="Delete Another Family Member"/>
					</html:form>
				</td>
				<td>
					<html:form method="post" action="/MainMenu">
						<input id="backBtn" type="submit" value="Back to Main Menu" />
					</html:form>
				</td>
				</tr>
			</table>
		</div>
	</div>		     	  
	       
</body>
</html:html>