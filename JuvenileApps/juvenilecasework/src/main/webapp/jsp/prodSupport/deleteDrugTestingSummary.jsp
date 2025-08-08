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

	<h4 align="center"><i>The following record will be <font color="red">DELETED</font>.</i></h4>
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
				<font color="green">
					<bean:write name="ProdSupportForm" property="msg" />
				</font>
	 		</td>
	 	</tr>
	</table>
	</logic:notEqual>
<!-- End Error Message Area -->
	<div id="command">
		<html:form method="post" action="/PerformDeleteDrugTesting">
			<div align="center">
				<table>
					<table class="resultTbl" border="1" width="850" align="center">
					<tr>	
						<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
							Drug Testing ID
						</th>
						<td>
							<bean:write name="ProdSupportForm" property="drugTestingInfo.drugTestingId"/>
						</td>
					</tr>
					<tr>
						<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
							Juvenile ID
						</th>
						<td>
							<bean:write name="ProdSupportForm" property="drugTestingInfo.juvenileId"/>
						</td>
					</tr>
					<tr>
						<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
							Activity ID
						</th>
						<td>
							<bean:write name="ProdSupportForm" property="drugTestingInfo.activityId"/>
						</td>
					</tr>
					<tr>
						<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
							Associated Casefile
						</th>
						<td>
							<bean:write name="ProdSupportForm" property="drugTestingInfo.associateCasefile"/>
						</td>
					</tr>
					<tr>
						<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
							Test Date
						</th>
						<td>
							<bean:write name="ProdSupportForm" property="drugTestingInfo.testDate" format="MM/dd/yyyy"/>
						</td>
					</tr>
					<tr>
						<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
							Test Time
						</th>
						<td>
							<bean:write name="ProdSupportForm" property="drugTestingInfo.testTime" format="HH:mm:ss"/>
						</td>
					</tr>
					<tr>
						<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
							Test Administered
						</th>
						<td>
							<bean:write name="ProdSupportForm" property="drugTestingInfo.testAdministered"/>
						</td>
					</tr>
					<tr>
						<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
							Test Location
						</th>
						<td>
							<bean:write name="ProdSupportForm" property="drugTestingInfo.testLocation"/>
						</td>
					</tr>
					<tr>
						<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
							Administered By
						</th>
						<td>
							<bean:write name="ProdSupportForm" property="drugTestingInfo.administeredBy"/>
						</td>
					</tr>
					<tr>
						<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
							Substance Tested					
						</th>
						<td>
							<bean:write name="ProdSupportForm" property="drugTestingInfo.substanceTested"/>
						</td>
					</tr>
					<tr>
						<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
							Test Results					
						</th>
						<td>
							<bean:write name="ProdSupportForm" property="drugTestingInfo.drugTestResults"/>
						</td>
					</tr>
					<tr>
						<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
							Comments			
						</th>
						<td>
							<bean:write name="ProdSupportForm" property="drugTestingInfo.comments"/>
						</td>
					</tr>
					<tr>
						<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
							Create Date			
						</th>
						<td>
							<bean:write name="ProdSupportForm" property="drugTestingInfo.createDate" format="MM/dd/yyyy"/>
						</td>
					</tr>
					<tr>
						<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
							Create User			
						</th>
						<td>
							<bean:write name="ProdSupportForm" property="drugTestingInfo.createUser"/>
						</td>
					</tr>
					
				</table>
			</div>
			<div id="command">
				<logic:equal name="ProdSupportForm" property="msg" value="">
					<div align="center">
						<html:submit property="submitAction" styleId="deleteBtn">
							<bean:message key="button.deleteRecord"></bean:message>
						</html:submit>
					</div>
				</logic:equal>
		</html:form>
				<div align="center">
					<html:form method="post" action="/DeleteDrugTestingQuery?clr=Y">
						<input id="backBtn" type="submit" value="Back to Query" />
					</html:form>
				</div>
		</div>
	</div>
</logic:notEmpty>
	       
</body>
</html:html>