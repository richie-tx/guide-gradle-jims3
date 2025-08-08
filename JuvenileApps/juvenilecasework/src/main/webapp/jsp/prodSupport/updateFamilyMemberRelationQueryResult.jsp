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
<title>Juvenile Casework -/prodSupport/UpdateFamilyMemberRelationQueryResult.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<style>
	#nav, #command{
		margin-top: 10px;
	}
	#command div{
		margin-bottom: 5px;
	}
	
	th {
		background-color: #cccccc;
		font-size: 12px;
		font-weight: bold;
		text-transform: uppercase
	}
</style> 
	
<script>
	$(document).ready(function(){		
		
		 $("#submitBtn").click(function(){
			 event.preventDefault();
			 
			 var response = confirm('Are you sure you want to update the family member relation record?')
			 if(response) 
				{ 							
					spinner();
					$('#PerformUpdateFamilyMemberRelation').submit();  
				} else {
					return false;
				}
		 });
		 		 
		 $("#btnBackToQuery").click(function(){
				window.location.href = '/JuvenileCasework/UpdateFamilyMemberRelationQuery.do?clr=Y';
		 })
		 
		 var inHomeSelect = $('#inHomeStatusYesNoSelect').val();
		 var inHomeChanged = false;
		 $('#inHomeStatusYesNoSelect').change(function(item){
			 
			 //console.log(item.target.value);
			 
			 inHomeSelect = $('#inHomeStatusYesNoSelect').val();
			 inHomeChanged = true;
			 //var inHomeStatus = $('#inHomeStatusYesNoSelect').val();
			 //$('#inHomeStatusYesNoTxt').val(inHomeStatus);
			 
			 console.log('inHomeStatus: ',  inHomeSelect);
		 });
		 
		 var isParentalRightsSelect = $('#isParentalRightsTerminatedSelect').val();
		 var parentsRightsChanged = false;
		$('#isParentalRightsTerminatedSelect').change(function(item){
			 
			 //console.log(item.target.value);
			 
			 isParentalRightsSelect = $('#isParentalRightsTerminatedSelect').val();
			 parentsRightsChanged = true;
			 //var isParentalRightsTerminated = $('#isParentalRightsTerminatedSelect').val();
			 //$('#isParentalRightsTerminatedTxt').val(isParentalRightsTerminated);
			 
			 console.log('isParentalRightsTerminated: ',  isParentalRightsSelect);
		 });
		
		var isPrimaryContactSelect = $('#isPrimaryContactSelect').val();
		var primaryContactChanged = false;
		$("#guardianYesNoTxt").css('visibility','hidden');
		$('#isPrimaryContactSelect').change(function(item){
			 
			 //console.log(item.target.value);
			 
			 isPrimaryContactSelect = $('#isPrimaryContactSelect').val();
			 primaryContactChanged = true;
			 
			 if(isPrimaryContactSelect === 'true')
			 {
				 $("#guardianYesNoTxt").val('YES');
				 $("#guardianYesNoTxt").css('visibility','visible');
				 $("#guardianYesNoTxt").css('color','red');
			 }
			 
			 if(isPrimaryContactSelect === 'false')
			 {
				 $("#guardianYesNoTxt").val('NO');
				 $("#guardianYesNoTxt").css('visibility','hidden');
			 }
			 
			 console.log('isPrimaryContact: ',  isPrimaryContactSelect);
		 });
		
		
		$('#btnReset').click(function(){					
			
			if(inHomeChanged){
				if(inHomeSelect === 'true'){
					$('#inHomeStatusYesNoSelect').val('false');
				} else {
					$('#inHomeStatusYesNoSelect').val('true');
				}
				
				inHomeChanged = false;
			}
			
			
			if(parentsRightsChanged){
				if(isParentalRightsSelect === 'true'){
					$('#isParentalRightsTerminatedSelect').val('false');
				} else {
					$('#isParentalRightsTerminatedSelect').val('true');
				}
				
				parentsRightsChanged = false;
			}
			
			
			if(primaryContactChanged){
				if(isPrimaryContactSelect === 'true'){
					$('#isPrimaryContactSelect').val('false');
				} else {
					$('#isPrimaryContactSelect').val('true');
				}
				
				primaryContactChanged = false;
			}
			
				
		});

	})
	
</script>

</head>

<body>

<h2 align="center">Result for Constellation ID:&nbsp;<bean:write name="ProdSupportForm" property="familyConstellationId" />&nbsp;</h2>
<!-- Error Message Area -->
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
	
    <logic:notEqual name="ProdSupportForm" property="msg" value="">
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" style="color:red">
				<bean:write name="ProdSupportForm" property="msg" />
	 		</td>
	 	</tr>
	</table>
	</logic:notEqual>
<!-- End Error Message Area -->
<p align="center"><b><i>Please enter new values for the attributes you wish to update.<br></i></b></p>

<logic:notEmpty	name="ProdSupportForm" property="familyConstellationMember">

<html:form method="post" styleId="PerformUpdateFamilyMemberRelation" action="/PerformUpdateFamilyMemberRelation">

	<div align="center">
		<table class="resultTbl" border="1" width="80%" align="center">
			<thead>
				<tr>
					<th>
						CONSRELATION_ID
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.OID" />
					</td>
				</tr>
				<tr>
					<th>
						FAMMEMBER_ID
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.theFamilyMemberId" />
					</td>
				</tr>
				<tr>
					<th>
						CONSTELLTION_ID
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.familyConstellationId" />
					</td>
				</tr>
				<tr>
					<th>
						RELTYPE
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.relationshipToJuvenileId" />
					</td>
				</tr>
				<tr>
					<th>
						GUARDIAN
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.guardianYesNo" />
					</td>
					<td>
						<html:text styleId="guardianYesNoTxt" name="ProdSupportForm" property="familyConstellationMember.guardianYesNo"></html:text>
					</td>
				</tr>	
				<tr>
					<th>
						INHOUSESTAT
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.inHomeStatusYesNo" />
						<html:hidden styleId="inHomeStatusYesNoTxt" name="ProdSupportForm" property="familyConstellationMember.inHomeStatusYesNo"></html:hidden>
					</td>
					<td>
						<html:select name="ProdSupportForm" property="familyConstellationMember.inHomeStatus" styleId="inHomeStatusYesNoSelect">
		           			<html:option value="">
								<bean:message key="select.generic" />
							</html:option>
							<html:optionsCollection name="ProdSupportForm" property="booleanList" value="code" label="description" />
           				</html:select>						
					</td>
				</tr>	
				<tr>
					<th>
						PARENTRIGHTSTERM
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.isParentalRightsTerminatedYesNo" />
						<html:hidden styleId="isParentalRightsTerminatedTxt" name="ProdSupportForm" property="familyConstellationMember.isParentalRightsTerminatedYesNo"></html:hidden>
					</td>
					<td>
						<html:select name="ProdSupportForm" property="familyConstellationMember.parentalRightsTerminated" styleId="isParentalRightsTerminatedSelect">
		           			<html:option value="">
								<bean:message key="select.generic" />
							</html:option>
							<html:optionsCollection name="ProdSupportForm" property="booleanList" value="code" label="description" />
           				</html:select>						
					</td>
				</tr>	
				<tr>
					<th>
						PRIMARYCONTACT/PRIMARY GUARDIAN
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.isPrimaryContactYesNo" />
						<html:hidden styleId="isPrimaryContactTxt" name="ProdSupportForm" property="familyConstellationMember.isPrimaryContactYesNo"></html:hidden>
					</td>
					<td>
						<html:select name="ProdSupportForm" property="familyConstellationMember.primaryContact" styleId="isPrimaryContactSelect">
		           			<html:option value="">
								<bean:message key="select.generic" />
							</html:option>
							<html:optionsCollection name="ProdSupportForm" property="booleanList" value="code" label="description" />
           				</html:select>						
					</td>
				</tr>					
				<tr>
					<th>
						DETENTIONHEARING
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.isDetentionHearingYesNo" />						
					</td>
				</tr>
				<tr>
					<th>
						VISIT
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.isDetentionVisitationYesNo" />
					</td>
				</tr>
				<tr>
					<th>
						PRIMARYCAREGIVER
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.isPrimaryCareGiverYesNo" />
					</td>
				</tr>
				<tr>
					<th>
						CREATEUSER
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.createUserID" />
					</td>
				</tr>
				<tr>
					<th>
						CREATEDATE
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.createTimestamp" formatKey="date.format.mmddyyyy" />
					</td>
				</tr>
				
				<tr>
					<th>
						UPDATEUSER
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.updateUser" />
					</td>
				</tr>
				<tr>
					<th>
						UPDATEDATE
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellationMember.updateDate" formatKey="date.format.mmddyyyy" />
					</td>
				</tr>
		
			</thead>
		</table>
	</div>
	
	<div id="command">			
	<logic:notEmpty name="ProdSupportForm" property="familyConstellationMember">
		<div style="display:inline-block;">
			<input id="submitBtn" type="button" value="Update Record"/>
		</div>
		<div style="display:inline-block;">
			<input id="btnReset" type="button" value="Reset Form Values" />
		</div>
	</logic:notEmpty>
	<logic:notEmpty name="ProdSupportForm" property="familyConstellationMember">
		<div>
			<br />
			<input id="btnBackToQuery" type="button" value="Back to Query" />
		</div>
	</logic:notEmpty>
</div>
	
</html:form>
</logic:notEmpty>
	
<logic:empty name="ProdSupportForm" property="familyConstellationMember">
	<br>
	<table align="center" border="1" width="700">
		<tr>
			<td>
		   		<h3 align="center"><font color="green"><i>Record Not Found</i></font></h3>
		   </td>
	   </tr>
	</table>
</logic:empty>
	     	       
</body>
</html:html>