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
<title>Juvenile Casework -/prodSupport/UpdateDrugTestingSummary.jsp</title>
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
		
		displayMessage("casefileId", '<bean:write name="ProdSupportForm" property="originalDrugTestingInfo.associateCasefile"/>', '<bean:write name="ProdSupportForm" property="newcasefileId" />' );
		displayMessage("testDate", '<bean:write name="ProdSupportForm" property="originalDrugTestingInfo.formarttedTestDate"/>', '<bean:write name="ProdSupportForm" property="testDate" />' );
		displayMessage("testTime", '<bean:write name="ProdSupportForm" property="originalDrugTestingInfo.formattedTestTime"/>', '<bean:write name="ProdSupportForm" property="testTime" />' );
		
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

<h2 align="center">Update Drug Testing  Summary</h2>
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
<p align="center"><font color="green" face="bold"><i>Record Successfully Updated</i></font></p>	 
<bean:define id="originalDrugTestingInfo" name="ProdSupportForm" property="originalDrugTestingInfo" type="messaging.juvenile.reply.DrugTestingResponseEvent"/>
	<div align="center">
		<table class="resultTbl" border="1" width="1000" align="center">
			<thead>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Drug Testing ID
					</th>
					<td>
						<bean:write name="originalDrugTestingInfo" property="drugTestingId" />
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Juvenile ID
					</th>
					<td>
						<bean:write name="originalDrugTestingInfo" property="juvenileId" />
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Activity ID
					</th>
					<td>
						<bean:write name="originalDrugTestingInfo" property="activityId" /> 
					</td>
				</tr>
				<tr id="casefileId">
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Associated Casefile
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="newcasefileId" /> 
					</td>
				</tr>
				<tr id="testDate">
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Test Date
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="testDate" /> 
					</td>
				</tr>
				<tr id="testTime">
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Test Time
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="testTime" /> 
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Test Administered
					</th>
					<td>
						<bean:write name="originalDrugTestingInfo" property="testAdministered" /> 
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Test Location
					</th>
					<td>
						<bean:write name="originalDrugTestingInfo" property="testLocation" /> 
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Administered By
					</th>
					<td>
						<bean:write name="originalDrugTestingInfo" property="administeredBy" /> 
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Substance Tested					
					</th>
					<td>
						<bean:write name="originalDrugTestingInfo" property="substanceTested" /> 
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Test Results					
					</th>
					<td>
						<bean:write name="originalDrugTestingInfo" property="drugTestResults" /> 
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Comments			
					</th>
					<td>
						<bean:write name="originalDrugTestingInfo" property="comments" /> 
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Create User			
					</th>
					<td>
						<bean:write name="originalDrugTestingInfo" property="createUser" /> 
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Create Date			
					</th>
					<td>
						<bean:write name="originalDrugTestingInfo" property="createDate" format="MM/dd/yyyy" /> 
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Update User			
					</th>
					<td>
						<bean:write name="originalDrugTestingInfo" property="updateUser" /> 
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Update Date			
					</th>
					<td>
						<bean:write name="originalDrugTestingInfo" property="updateDate" format="MM/dd/yyyy" /> 
					</td>
				</tr>
			</thead>
		</table>
	</div>
	


	<div id="nav">
		<div style="display:inline-block;">
			<html:form method="post" action="/UpdateDrugTestingQuery?clr=Y">
				<input id="researchBtn" type="submit" value="Back to Query" />
			</html:form>
		</div>
		<div style="display:inline-block;">
			<html:form method="post" action="/MainMenu">
						<input id="backBtn" type="submit" value="Back to Main Menu" />
			</html:form>
		</div>
	</div>		     	       
</body>
</html:html>