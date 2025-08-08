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
<title>Juvenile Casework -/prodSupport/DeleteDrugTestingQuery.jsp</title>
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
		 $("input[type='radio']").change(function() {
			var selectedValue = $(this).val();
			$("#selectedValue").val( selectedValue );
			console.log("Selected value:", selectedValue);
		});
		 
		 $("#submitBtn").click(function(){
			 if (!$("input[name='id']").is(':checked')) {
				   alert('Please select a drug testing record you want to delete.');
				   return false;
				}
				else {
				  return true;
				}
		 });
	})
</script>
</head>

<body>

<h2 align="center">Results for Juvenile ID = 
			<bean:write name="ProdSupportForm" property="juvenileId" />
			and Drug Testing ID <bean:write name="ProdSupportForm" property="drugTestingId" /></h2>
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
<p align="center"><b><i>Select Drug Testing Record from the list and click Submit to view current data.<br></i></b></p>
<p align="center"><b><i>No Records will be deleted until confirmed on the next page.<br></i></b></p>
<logic:notEmpty	name="ProdSupportForm" property="drugTestingInfos">
	<h3 align="center">Drug Testing Records</h3>
	<div align="center">
		<table class="resultTbl" border="1" width="850" align="center">
			<thead>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1"></th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Drug Testing ID
					</th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Juvenile ID
					</th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Activity ID
					</th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Associated Casefile
					</th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Test Date
					</th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Test Time
					</th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Test Administered
					</th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Test Location
					</th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Administered By
					</th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Substance Tested					
					</th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Test Results					
					</th>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Comments			
					</th>
				</tr>
			</thead>
			<tbody>
				<logic:iterate id="drugTestingInfo" name="ProdSupportForm" property="drugTestingInfos" indexId="index">
					<tr>
						<td><input type="radio" name="id"
									value="<bean:write name="drugTestingInfo" property="drugTestingId" />"/></td>
						<td>
							<bean:write name="drugTestingInfo" property="drugTestingId" />
						</td>
						<td>
							<bean:write name="drugTestingInfo" property="juvenileId" />	 
						</td>
						<td>
							<bean:write name="drugTestingInfo" property="activityId" /> 
						</td>
						<td>
							<bean:write name="drugTestingInfo" property="associateCasefile" /> 
						</td>
						<td>
							<bean:write name="drugTestingInfo" property="testDate" format="MM/dd/yyyy" /> 
						</td>
						<td>
							<bean:write name="drugTestingInfo" property="testTime" format="HH:mm:ss" /> 
						</td>
						<td>
							<bean:write name="drugTestingInfo" property="testAdministered" /> 
						</td>
						<td>
							<bean:write name="drugTestingInfo" property="testLocation" /> 
						</td>
						<td>
							<bean:write name="drugTestingInfo" property="administeredBy" /> 
						</td>
						<td>
							<bean:write name="drugTestingInfo" property="substanceTested" /> 
						</td>
						<td>
							<bean:write name="drugTestingInfo" property="drugTestResults" /> 
						</td>
						<td>
							<bean:write name="drugTestingInfo" property="comments" /> 
						</td>
					</tr>
				</logic:iterate>
			</tbody>
		</table>
	</div>
	<div id="command">
			<html:form method="post" styleId="PerformDeleteDrugTesting"
										action="/PerformDeleteDrugTesting">
				<logic:notEmpty name="ProdSupportForm" property="drugTestingInfos">
					<div align="center">
						<font style="FONT-FAMILY: Arial" color="#0000aa"><strong> Selected Drug Testing Record: </strong></font>
						<html:text styleId="selectedValue" name="ProdSupportForm" property="selectedValue" readonly="true"/>
					</div>
					<div>
						<html:submit styleId="submitBtn" property="submitAction"><bean:message key="button.submit"></bean:message></html:submit>
					</div>
				</logic:notEmpty>
			</html:form>
			
	</div>
</logic:notEmpty>
<logic:empty name="ProdSupportForm" property="drugTestingInfos">
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
					<html:form method="post" action="/DeleteDrugTestingQuery?clr=Y">
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