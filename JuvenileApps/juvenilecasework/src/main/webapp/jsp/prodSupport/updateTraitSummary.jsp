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
<title>Juvenile Casework -/prodSupport/UpdateTraitSummary.jsp</title>
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
		
		displayMessage("juvenileId", '<bean:write name="ProdSupportForm" property="originalTrait.juvenileNum"/>', '<bean:write name="ProdSupportForm" property="toJuvenileId" />' );
		displayMessage("supervisionNum", '<bean:write name="ProdSupportForm" property="originalTrait.supervisionNum"/>', '<bean:write name="ProdSupportForm" property="currentSupervisionNum" />' );
		displayMessage("traitTypeId", '<bean:write name="ProdSupportForm" property="originalTrait.traitTypeId"/>', '<bean:write name="ProdSupportForm" property="traitTypeId" />' );
		displayMessage("statusId", '<bean:write name="ProdSupportForm" property="originalTrait.statusId"/>', '<bean:write name="ProdSupportForm" property="statusId" />' );
		
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

<h2 align="center">Update Traits  Summary</h2>
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
<bean:define id="originalTrait" name="ProdSupportForm" property="originalTrait" type="messaging.juvenilecase.reply.JuvenileTraitResponseEvent"/>
	<div align="center">
		<table class="resultTbl" border="1" width="1000" align="center">
			<thead>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						TRAITS_ID
					</th>
					<td>
						<bean:write name="originalTrait" property="juvenileTraitId" />
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						COMMENTS
					</th>
					<td>
						<bean:write name="originalTrait" property="traitComments" />
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						DISPOSITIONNUM
					</th>
					<td>
						<bean:write name="originalTrait" property="dispositionNum" /> 
					</td>
				</tr>
				<tr id="juvenileId">
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						JUVENILE_ID
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="toJuvenileId" /> 
					</td>					
				</tr>
				<tr id="supervisionNum">
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						SUPERVISIONNUM
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="currentSupervisionNum" /> 
					</td>				
				</tr>
				<tr id="traitTypeId">
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						TRAITTYPE_ID
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="traitTypeId" /> 
					</td>					
				</tr>				
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						TRAITTYPE_DESC
					</th>
					<td>
						<bean:write name="originalTrait" property="traitTypeDescription" /> 
					</td>
				</tr>
				<tr id="statusId">
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						TRAITSTATUS
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="statusId" /> 
					</td>					
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						TRAITSTATUS_DESC
					</th>
					<td>
						<bean:write name="originalTrait" property="status" /> 
					</td>
				</tr>				
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						CREATE USER			
					</th>
					<td>
						<bean:write name="originalTrait" property="createUserID" /> 
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						CREATE DATE			
					</th>
					<td>
						<bean:write name="originalTrait" property="createDate" format="MM/dd/yyyy" /> 
					</td>
				</tr>
			</thead>
		</table>
	</div>
	


	<div id="nav">
		<div style="display:inline-block;">
			<html:form method="post" action="/UpdateTraitRecordsQuery?clr=Y">
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