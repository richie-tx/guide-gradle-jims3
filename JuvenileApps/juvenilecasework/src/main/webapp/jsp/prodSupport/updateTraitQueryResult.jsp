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
<%@ page import="naming.Features" %>


<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>

<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/UpdateTraitQueryResult.jsp</title>
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
		
		 $("#submitBtn").click(function(){
			 event.preventDefault();
			 
			 var juvNum = $("#toJuvenileId").val();
				if ($("#toJuvenileId").val() == "" || $("#toJuvenileId").val().trim().length < 6) {
						alert("Invalid Juvenile Number.");
						$("#toJuvenileId").focus();
						return false;
					}
					// juvenile Number
					if ($.isNumeric(juvNum) == false) {
						alert("Invalid Juvenile Number.");
						$("#toJuvenileId").focus();
						return false;
					}							
				
					 var traitType = $("#toTraitTypeId").val();
					 
					 if (traitType == "" || traitType == null)
					 {
						 alert("Invalid Trait Type Id.");
						 $("#toTraitTypeId").focus();
							return false;
					 }
					 
					 
						var originaljuvenileNum = '<bean:write name="ProdSupportForm" property="originalTrait.juvenileNum"/>';	
						var originalsupervisionNum = '<bean:write name="ProdSupportForm" property="originalTrait.supervisionNum"/>';						
						var originaltraitTypeId= '<bean:write name="ProdSupportForm" property="originalTrait.traitTypeId"/>';
						var originalstatusId = '<bean:write name="ProdSupportForm" property="originalTrait.statusId" />';
						
						
						if ( originaljuvenileNum === $("#toJuvenileId").val()
								&&  originalsupervisionNum === $("#toSupervisionNum").val()
								&& 	originaltraitTypeId === $("#toTraitTypeId").val()
								&&	originalstatusId === $("#toStatusId").val()								
								 ) {
								alert("At least one value must be modified to submit system changes.");
								return false;
							}
						
						
			 
			 if(confirm('Are you sure you want to update the traits record?')) 
				{ 		
					if(true)
					{
						spinner();
						 $('#PerformUpdateTrait').submit();  
					}	
				} 
		 });
	})
</script>

</head>

<body>

<h2 align="center">Results for Traits ID <bean:write name="ProdSupportForm" property="traitId" /></h2>
<!-- Error Message Area -->
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
	
    <logic:notEqual name="ProdSupportForm" property="msg" value="">
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert">
				<bean:write name="ProdSupportForm" property="msg" />
	 		</td>
	 	</tr>
	</table>
	</logic:notEqual>
<!-- End Error Message Area -->
<p align="center"><b><i>Please enter new values for the attributes you wish to update.<br></i></b></p>

<logic:notEmpty	name="ProdSupportForm" property="originalTrait">
<bean:define id="originalTrait" name="ProdSupportForm" property="originalTrait" type="messaging.juvenilecase.reply.JuvenileTraitResponseEvent"/>
<html:form method="post" styleId="PerformUpdateTrait" action="/PerformUpdateTrait">
										
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
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						JUVENILE_ID
					</th>
					<td>
						<bean:write name="originalTrait" property="juvenileNum" /> 
					</td>
					
					<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATEJUVENILENUM%>">
					    <td>
						<font color="red">New value:</font>
						<html:text styleId="toJuvenileId" name="ProdSupportForm" property="toJuvenileId" maxlength="8"></html:text>
						</td>
					</jims:isAllowed>
					
					<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATEJUVENILENUM%>" value='false'>
					    <td style="display:none;">
						<font color="red">New value:</font>
						<html:text styleId="toJuvenileId" name="ProdSupportForm" property="toJuvenileId" maxlength="8"></html:text>
						</td>
					</jims:isAllowed>
					
					
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						SUPERVISIONNUM
					</th>
					<td>
						<bean:write name="originalTrait" property="supervisionNum" /> 
					</td>
					<td>
						<font color="red">New value:</font>
						<html:text styleId="toSupervisionNum" name="ProdSupportForm" property="currentSupervisionNum" maxlength="8"></html:text>
					</td>
				</tr>
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						TRAITTYPE_ID
					</th>
					<td>
						<bean:write name="originalTrait" property="traitTypeId" /> 
					</td>
					<td>
						<font color="red">New value:</font>
						<html:text styleId="toTraitTypeId" name="ProdSupportForm" property="traitTypeId" maxlength="3" style="text-transform: uppercase;"></html:text>
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
				<tr>
					<th bgcolor="#cccccc"><font color="#000000" face="bold" size="-1">
						TRAITSTATUS
					</th>
					<td>
						<bean:write name="originalTrait" property="statusId" /> 
					</td>
					<td>
						<font color="red">New value:</font>
						<html:text styleId="toStatusId" name="ProdSupportForm" property="statusId" maxlength="2" style="text-transform: uppercase;"></html:text>
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
	<div id="command">
			
				<logic:notEmpty name="ProdSupportForm" property="originalTrait">
					<div>
						<input id="submitBtn" type="button" value="Update Record"/>
					</div>
				</logic:notEmpty>
	</div>
</html:form>
</logic:notEmpty>
<logic:empty name="ProdSupportForm" property="originalTrait">
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
					<html:form method="post" action="/UpdateTraitRecordsQuery?clr=Y">
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