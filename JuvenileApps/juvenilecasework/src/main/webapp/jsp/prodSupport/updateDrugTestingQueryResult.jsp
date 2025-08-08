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
<title>Juvenile Casework -/prodSupport/UpdateDrugTestingQueryResult.jsp</title>
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
</style> 
	
<script>
	$(document).ready(function(){
	
		if (typeof $("#testDate") != "undefined") 
		{
			datePickerSingle($("#testDate"), "Date", false);
		}
		
		$("#testTime").attr("type", "time");
		
		 $("#submitBtn").click(function(){
			 event.preventDefault();
			 if(confirm('Are you sure you want to update the drug testing record?')) 
				{ 		
					if(true)
					{
						spinner();
						 $('#PerformUpdateDrugTesting').submit();  
					}	
				} 
		 });
	})
</script>

</head>

<body>

<h2 align="center">Results for Drug Testing ID <bean:write name="ProdSupportForm" property="drugTestingId" /></h2>
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
<p align="center"><b><i>Please enter new values for the attributes you wish to update.<br></i></b></p>

<logic:notEmpty	name="ProdSupportForm" property="originalDrugTestingInfo">
<bean:define id="originalDrugTestingInfo" name="ProdSupportForm" property="originalDrugTestingInfo" type="messaging.juvenile.reply.DrugTestingResponseEvent"/>
<html:form method="post" styleId="PerformUpdateDrugTesting"
										action="/PerformUpdateDrugTesting">
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
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Associated Casefile
					</th>
					<td>
						<bean:write name="originalDrugTestingInfo" property="associateCasefile" /> 
					</td>
					<td>
						<font color="red">New value:</font>
						<html:text styleId="newcasefileId" name="ProdSupportForm" property="newcasefileId"></html:text>
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Test Date
					</th>
					<td>
						<bean:write name="originalDrugTestingInfo" property="testDate" format="MM/dd/yyyy" /> 
					</td>
					<td>
						<font color="red">New value:</font>
						<html:text styleId="testDate" name="ProdSupportForm" property="testDate"></html:text>
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Test Time
					</th>
					<td>
						<bean:write name="originalDrugTestingInfo" property="testTime" format="HH:mm" /> 
					</td>
					<td>
						<font color="red">New value:</font>
						<html:text styleId="testTime" name="ProdSupportForm" property="testTime"></html:text>
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
	<div id="command">
			
				<logic:notEmpty name="ProdSupportForm" property="originalDrugTestingInfo">
					<div>
						<input id="submitBtn" type="button" value="Update Record"/>
					</div>
				</logic:notEmpty>
	</div>
</html:form>
</logic:notEmpty>
<logic:empty name="ProdSupportForm" property="originalDrugTestingInfo">
	<br>
	<table align="center" border="1" width="700">
		<tr>
			<td>
		   		<h3 align="center"><font color="green"><i>No Records Returned</i></font></h3>
		   </td>
	   </tr>
	</table>
</logic:empty>
<div id="nav">
				<div style="display:inline-block;">
					<html:form method="post" action="/UpdateDrugTestingQuery?clr=Y">
						<input id="researchBtn" type="submit" value="Search a Different Record" />
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