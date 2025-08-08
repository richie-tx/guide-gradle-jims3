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
<title>Juvenile Casework -/prodSupport/UpdateFamilyConstellationQueryResult.jsp</title>
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
	
		if (typeof $("#entryDate") != "undefined") 
		{
			datePickerSingle($("#entryDate"), "Date", false);
		}
		
		$("#entryDate").change(function(item, index){
			console.log(item.target.value);
			$("#entryDateHidden").val(item.target.value);
			
			console.log($("#entryDateHidden").val());
		});
		
		 $("#submitBtn").click(function(){
			 event.preventDefault();
			 
			 if(confirm('Are you sure you want to update the family constellation record?')) 
				{ 		
					if(true)
					{						
						spinner();
						 $('#PerformUpdateFamilyConstellation').submit();  
					}
				} 
		 });
		 
		 $("#btnBackToQuery").click(function(){
				window.location.href = '/JuvenileCasework/UpdateFamilyConstellationQuery.do?clr=Y';
		 })
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

<h2 align="center">Results for Family Constellation ID <bean:write name="ProdSupportForm" property="familyConstellation.familyConstellationId" /></h2>
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

<logic:notEmpty	name="ProdSupportForm" property="familyConstellation">

<html:form method="post" styleId="PerformUpdateFamilyConstellation"
										action="/PerformUpdateFamilyConstellation">
	<div align="center">
		<table class="resultTbl" border="1" width="1000" align="center">
			<thead>
				<tr>
					<th>
						Family Constellation ID
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellation.familyConstellationId" />
					</td>
				</tr>
				<tr>
					<th>
						Juvenile ID
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellation.juvenileId" />
					</td>
					<td>
						<jims:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATEJUVENILENUM%>">
							<html:text name="ProdSupportForm" property="familyConstellation.juvenileId"></html:text>
						</jims:isAllowed>
					</td>
				</tr>
				<tr>
					<th>
						Constellation Date
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellation.entryDate" format="MM/dd/yyyy" />
						<input id="dob" type="hidden" value="<bean:write name="ProdSupportForm" property="familyConstellation.entryDate" format="MM/dd/yyyy" />" /> 
					</td>
					<td>
						<html:text styleId="entryDate" name="ProdSupportForm" property="familyConstellation.entryDateString"></html:text>
						<html:hidden styleId="entryDateHidden" name="ProdSupportForm" property="familyConstellation.entryDateString"></html:hidden>
					</td>
				</tr>
				<tr>
					<th>
						Status
					</th>
					<td>
						<bean:write name="ProdSupportForm" property="familyConstellation.active" />
					</td>
					<td>
						<html:select name="ProdSupportForm" property="familyConstellation.active" styleId="status">
		           			<html:option value="">
								<bean:message key="select.generic" />
							</html:option>
							<html:optionsCollection name="ProdSupportForm" property="statusList" value="code" label="description" />
           				</html:select>
					</td>
				</tr>
				<!-- 
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATE USER</font></td>
					<td><font size="-1"><bean:write  name="ProdSupportForm" property="familyConstellation.createUserID" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATE DATE</font></td>
					<td><font size="-1"><bean:write  name="ProdSupportForm" property="familyConstellation.createTimestamp" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATE USER</font></td>
					<td><font size="-1"><bean:write  name="ProdSupportForm" property="familyConstellation.updateUserID" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATE DATE</font></td>
					<td><font size="-1"><bean:write  name="ProdSupportForm" property="familyConstellation.updateTimestamp" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATE JIMS2USER</font></td>
					<td><font size="-1"><bean:write  name="ProdSupportForm" property="familyConstellation.createJIMS2UserID" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATE JIMS2USER</font></td>
					<td><font size="-1"><bean:write  name="ProdSupportForm" property="familyConstellation.updateJIMS2UserID" />&nbsp;</font></td>
				</tr>
				 -->
			</thead>
		</table>
	</div>
	
	<div id="command">			
	<logic:notEmpty name="ProdSupportForm" property="familyConstellation">
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
	
<logic:empty name="ProdSupportForm" property="familyConstellation">
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