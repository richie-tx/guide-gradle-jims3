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
<title>Juvenile Casework -/prodSupport/UpdateFamilyMemberQueryResult.jsp</title>
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
	
		if (typeof $("#dateOfBirth") != "undefined") 
		{
			datePickerSingle($("#dateOfBirth"), "Date", false);
		}
		
		//var dbo = $("#dob").val();		
		//if(dob){
		//	$("#dateOfBirth").val(dob.value);
		//}
		
		$("#dateOfBirth").change(function(item, index){
			console.log(item.target.value);
			$("#dateOfBirthHidden").val(item.target.value);
			
			console.log($("#dateOfBirthHidden").val());
		});
		
		 $("#submitBtn").click(function(){
			 event.preventDefault();
			 
			 var ssn = $("#ssn").val();
			 
			 if(ssn && ssn.length !== 9)
			 {
				 alert('social security number must have a length of 9 digits');
				 return false;
			 }
			 
			 if(confirm('Are you sure you want to update the family member record?')) 
				{ 		
					if(true)
					{						
						spinner();
						 $('#PerformUpdateFamilyMember').submit();  
					}	
				} 
		 });
		 
		 $("#btnBackToQuery").click(function(){
				window.location.href = '/JuvenileCasework/UpdateFamilyMemberQuery.do?clr=Y';
		 })
		 
		 $('#comments').width(300);
		 
		 $("#ssn").change(function(item, index){
				console.log(item.target.value);
				
				//console.log($("#dateOfBirthHidden").val());
			});
	})
	
	function formatDate(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();
	
	    if (month.length < 2) 
	        month = '0' + month;
	    if (day.length < 2) 
	        day = '0' + day;
	
	    return [year, month, day].join('-');
	}
	
</script>

</head>

<body>

<h2 align="center">Results for Family Member ID <bean:write name="ProdSupportForm" property="familyMemberId" /></h2>
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

<logic:notEmpty	name="ProdSupportForm" property="familyMember">

<html:form method="post" styleId="PerformUpdateFamilyMember"
										action="/PerformUpdateFamilyMember">
	<div align="center">
		<table class="resultTbl" border="1" width="80%" align="center">
			<thead>
				<tr>
					<th>
						Family Member ID
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyMemberUpdate.familyMemberId" />
					</td>
				</tr>
				<tr>
					<th>
						First Name
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyMember.firstName" />
					</td>
					<td>
						<html:text name="ProdSupportForm" property="familyMemberUpdate.firstName"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						Last Name
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyMember.lastName" />
					</td>
					<td>
						<html:text name="ProdSupportForm" property="familyMemberUpdate.lastName"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						Date Of Birth
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyMember.dateOfBirth" format="MM/dd/yyyy" />
						<input id="dob" type="hidden" value="<bean:write name="ProdSupportForm" property="familyMember.dateOfBirth" format="MM/dd/yyyy" />" /> 
					</td>
					<td>
						<html:text styleId="dateOfBirth" name="ProdSupportForm" property="familyMemberUpdate.dateOfBirthString"></html:text>
						<html:hidden styleId="dateOfBirthHidden" name="ProdSupportForm" property="familyMemberUpdate.dateOfBirthString"></html:hidden>
					</td>
				</tr>	
				<tr>
					<th>
						SSN
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyMember.SSN" />
					</td>
					<td>
						<html:text styleId="ssn" name="ProdSupportForm" property="familyMemberUpdate.SSN"></html:text>
					</td>
				</tr>	
				<tr>
					<th>
						Comments
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyMember.comments" />
					</td>
					<td>
						<html:text styleId="comments" name="ProdSupportForm" property="familyMemberUpdate.comments"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						Sex
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyMember.sexId" />
					</td>
					<td>
						<html:select name="ProdSupportForm" property="familyMemberUpdate.sexId" styleId="sex">
		           			<html:option value="">
								<bean:message key="select.generic" />
							</html:option>
							<html:optionsCollection name="ProdSupportForm" property="sexList" value="code" label="description" />
           				</html:select>						
					</td>
				</tr>	
				<tr>
					<th>
						SID Number
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyMember.sidNum" />
					</td>
					<td>
						<html:text name="ProdSupportForm" property="familyMemberUpdate.sidNum"></html:text>
					</td>
				</tr>					
				<tr>
					<th>
						ID Number
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyMember.driverLicenseNum" />						
					</td>
					<td>
						<html:text name="ProdSupportForm" property="familyMemberUpdate.driverLicenseNum"></html:text>						
					</td>
				</tr>
				<!-- 
				<tr>
					<th>
						Juv Relation
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyMember.juvRelation" />
					</td>
					<td>
						<html:select name="ProdSupportForm" property="familyMemberUpdate.juvRelation" styleId="juvRelation">
		           			<html:option value="">
								<bean:message key="select.generic" />
							</html:option>
							<html:optionsCollection name="ProdSupportForm" property="relationshipToJuvenileList" value="code" label="description" />
           				</html:select>
					</td>
				</tr>
				 -->
				<tr>
					<th>
						ID State
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyMember.idCardStateId" />
					</td>
					<td>
						<html:select name="ProdSupportForm" property="familyMemberUpdate.idCardStateId" styleId="idCardStateId">
		           			<html:option value="">
								<bean:message key="select.generic" />
							</html:option>
							<html:optionsCollection name="ProdSupportForm" property="stateList" value="code" label="description" />
           				</html:select>
					</td>
				</tr>
				<tr>
					<th>
						STISSIDNUM
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyMember.idCardNum" />
					</td>
					<td>
						<html:text name="ProdSupportForm" property="familyMemberUpdate.idCardNum"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						ISSIDSTATE
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyMember.driverLicenseStateId" />
					</td>
					<td>
						<html:select name="ProdSupportForm" property="familyMemberUpdate.driverLicenseStateId" styleId="driverLicenseStateId">
		           			<html:option value="">
								<bean:message key="select.generic" />
							</html:option>
							<html:optionsCollection name="ProdSupportForm" property="stateList" value="code" label="description" />
           				</html:select>
					</td>
				</tr>
				<tr>
					<th>
						INCARCERATED?
					</th>
					<td>					
						<bean:write name="ProdSupportForm" property="familyMember.incarcerated" />
					</td>
					<td>
						
						<html:select name="ProdSupportForm" property="familyMemberUpdate.incarcerated" styleId="incarcerated">
		           			<html:option value="">
								<bean:message key="select.generic" />
							</html:option>
							<html:optionsCollection name="ProdSupportForm" property="isIncarceratedList" value="code" label="description" />
           				</html:select>
           				
					</td>
				</tr>				
			</thead>
		</table>
	</div>
	
	<div id="command">			
	<logic:notEmpty name="ProdSupportForm" property="familyMember">
		<div style="display:inline-block;">
			<input id="submitBtn" type="button" value="Update Record"/>
		</div>
		<div style="display:inline-block;">
			<input id="btnBackToQuery" type="button" value="Back to Query" />
		</div>
	</logic:notEmpty>
</div>
	
</html:form>
</logic:notEmpty>
	
<logic:empty name="ProdSupportForm" property="familyMember">
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