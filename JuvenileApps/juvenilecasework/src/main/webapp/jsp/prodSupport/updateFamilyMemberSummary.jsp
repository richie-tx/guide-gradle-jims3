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
<title>Juvenile Casework -/prodSupport/UpdateFamilyMemberSummary.jsp</title>
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
		

	})

	function displayMessage(id, originalValue, updatedValue){
		if (originalValue != updatedValue){
			$("#"+id).append("<td class='message'>Updated from previous value, " + originalValue + "</td>");
		}
	}
</script>
<style>
	#nav, #command{
		margin-top: 10px;
	}
	#command div{
		margin-bottom: 5px;
	}
</style> 

</head>

<body>

<h2 align="center">Update Family Member  Summary</h2>
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
<p align="center" style="font-weight:bold"><font color="green" face="bold" size="-1px"><i>Family Member Record Successfully Updated</i></font></p>
<p align="center" style="font-weight:bold"><font  face="bold">The following is the record affected by this change. This is for auditing purposes.</font></p>	 
<bean:define id="familyMember" name="ProdSupportForm" property="familyMemberUpdate" type="pd.juvenilecase.family.FamilyMember"/>

	<div align="center">
		<table class="resultTbl" border="1" width="1000" align="center">
			<thead>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Family Member ID
					</th>
					<td>
						<bean:write name="familyMember" property="familyMemberId" />
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						First Name
					</th>
					<td>
						<bean:write name="familyMember" property="firstName" />
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Last Name
					</th>
					<td>
						<bean:write name="familyMember" property="lastName" />
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Date Of Birth
					</th>
					<td>
						<bean:write name="familyMember" property="dateOfBirth" format="MM/dd/yyyy" />
					</td>
				</tr>	
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						SSN
					</th>
					<td>
						<bean:write name="familyMember" property="SSN" />
					</td>
				</tr>	
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Comments
					</th>
					<td>
						<bean:write name="familyMember" property="comments" />
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Sex
					</th>
					<td>
						<bean:write name="familyMember" property="sexId" />
					</td>
				</tr>	
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						SID Number
					</th>
					<td>
						<bean:write name="familyMember" property="sidNum" />
					</td>
				</tr>					
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						ID Number
					</th>
					<td>
						<bean:write name="familyMember" property="driverLicenseNum" />
					</td>
				</tr>
				<!--  
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Juv Relation
					</th>
					<td>
						<bean:write name="familyMember" property="juvRelation" />
					</td>
				</tr>
				-->
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						ID State
					</th>
					<td>
						<bean:write name="familyMember" property="idCardStateId" />
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						STISSIDNUM
					</th>
					<td>
						<bean:write name="familyMember" property="idCardNum" />						
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						ISSIDSTATE
					</th>
					<td>
						<bean:write name="familyMember" property="driverLicenseStateId" />
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						ISINCARCERATED
					</th>
					<td>					
						<bean:write name="familyMember" property="incarcerated" />
					</td>
				</tr>
				
			</thead>
		</table>
	</div>

	<div id="nav">
		<div>
			<html:form method="post" action="/MainMenu">
						<input id="backBtn" type="submit" value="Back to Main Menu" />
			</html:form>
		</div>
	</div>		     	       
</body>
</html:html>