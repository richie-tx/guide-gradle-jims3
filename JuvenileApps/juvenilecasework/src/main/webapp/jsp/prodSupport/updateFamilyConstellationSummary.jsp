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
<title>Juvenile Casework -/prodSupport/UpdateFamilyConstellationSummary.jsp</title>
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

<h2 align="center">Update Family Constellation  Summary</h2>
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
<p align="center" style="font-weight:bold"><font color="green" face="bold" size="-1px"><i>Family Constellation Record Updated Successfully </i></font></p>
<p align="center" style="font-weight:bold"><font  face="bold">The following is the record affected by this change. This is for auditing purposes.</font></p>	 

	<div align="center">
		<table class="resultTbl" border="1"  width="80%" align="center">
			<thead>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Family Constellation ID
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellation.familyConstellationId" />
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Juvenile ID
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellation.juvenileId" />
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold">
						Constellation Date
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellation.entryDate" format="MM/dd/yyyy" />
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						Status
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellation.active" />
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