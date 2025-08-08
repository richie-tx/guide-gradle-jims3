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

<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/UpdateFamilyMemberRelationSummary.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<style>
	 .message {
	 	text-align: center;
	 	color: green;
	 }
	 
</style>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script>
	$(document).ready(function(){
		
		 $("#btnBackToQuery").click(function(){
				window.location.href = '/JuvenileCasework/UpdateFamilyMemberRelationQuery.do?clr=Y';
		 })
	})

</script>
<style>
	#nav, #command{
		margin-top: 10px;
	}
	#command div{
		margin-bottom: 5px;
	}
	.label {
		background-color: #cccccc;
		font-size: 12px;
		font-weight: bold;
		text-transform: uppercase
	}
</style> 

</head>

<body>

<h2 align="center">Production Support - Family Member Relation Summary</h2>
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
<p align="center" style="font-weight:bold"><font color="green" face="bold"><i>Family Member Relation Record Successfully Updated</i></font></p>
<p align="center" style="font-weight:bold"><font  face="bold">The following is the record affected by this change. This is for auditing purposes.</font></p>	 
<bean:define id="familyConstellationMember" name="ProdSupportForm" property="familyConstellationMember" type="pd.juvenilecase.family.FamilyConstellationMember"/>

	<div align="center">
		<table class="resultTbl" border="1" width="60%" align="center">
				<tr>
					<td class="label">
						CONSRELATION_ID
					</td>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.OID" />
					</td>
				</tr>
				<tr>
					<td class="label">
						FAMMEMBER_ID
					</td>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.theFamilyMemberId" />
					</td>
				</tr>
				<tr>
					<td class="label">
						CONSTELLTION_ID
					</td>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.familyConstellationId" />
					</td>
				</tr>
				<tr>
					<td class="label">
						RELTYPE
					</td>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.relationshipToJuvenileId" />
					</td>
				</tr>
				<tr>
					<td class="label">
						GUARDIAN
					</td>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.guardianYesNo" />
					</td>
				</tr>	
				<tr>
					<td class="label">
						INHOUSESTAT
					</td>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.inHomeStatusYesNo" />
					</td>
				</tr>	
				<tr>
					<td class="label">
						PARENTRIGHTSTERM
					</td>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.isParentalRightsTerminatedYesNo" />
					</td>
				</tr>	
				<tr>
					<td class="label">
						PRIMARYCONTACT/PRIMARY GUARDIAN
					</td>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.isPrimaryContactYesNo" />
					</td>
				</tr>					
				<tr>
					<td class="label">
						DETENTIONHEARING
					</td>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.isDetentionHearingYesNo" />						
					</td>
				</tr>
				<tr>
					<td class="label">
						VISIT
					</td>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.isDetentionVisitationYesNo" />
					</td>
				</tr>
				<tr>
					<td class="label">
						PRIMARYCAREGIVER
					</td>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.isPrimaryCareGiverYesNo" />
					</td>
				</tr>
				<tr>
					<td class="label">
						CREATEUSER
					</td>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.createUserID" />
					</td>
				</tr>
				<tr>
					<td class="label">
						CREATEDATE
					</td>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.createTimestamp"  formatKey="date.format.mmddyyyy" />
					</td>
				</tr>
				<tr>
					<td class="label">
						UPDATEUSER
					</td>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.updateUser" />
					</td>
				</tr>
				<tr>
					<td class="label">
						UPDATEDATE
					</td>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.updateDate" formatKey="date.format.mmddyyyy" />
					</td>
				</tr>
		</table>
		
		<div id="nav" style="align: center">
			<table>
				<tr>
					<td>
						<div style="display:inline-block;">
							<input id="btnBackToQuery" type="button" value="Back to Query" />
						</div>
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