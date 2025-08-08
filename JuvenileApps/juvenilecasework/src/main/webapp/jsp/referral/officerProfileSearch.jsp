<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>

<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />


<head>
<META http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<%@include file="../jQuery.fw"%>
<style>
	table { margin-top: 2px; marign-bottom: 2px;}
	#searchOptions { margin-top: 10px;}
	#inputFields { margin-top: 20px; margin-bottom: 20px;}
	#fName, #lName, #mName { width: 300px;}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		if ( "DT" == '<bean:write name="officerForm" property="requestOrigin" />' ) {
			$("td").removeClass("referralDetailHead");
			$("td").addClass("detailHead");
		}
		
		
		$("#refreshBtn").click(function(){
			$("#fName").val("");
			$("#mName").val("");
			$("#lName").val("");
		});
		
		$("#searchBtn").click(function(){
			if ( validateInputs() ) {
				$("form").submit();
			}
		});
		
		$("#cancelBtn").click(function(){
			$("form").attr("action",'/JuvenileCasework/searchOfficerProfile.do?clr=<bean:write name="officerForm" property="requestOrigin"/>');
			$("form").submit();
		});
		
		function validateInputs(){
			var regExp = /^([A-Za-z0-9!@#$%^&*()]+)$/;
		 	
		 	if ( regExp.test( $("#lName").val()  ) ) {
		 		return true;
		 	} else {
		 		alert("Officer last name field is required. The special characters !@#$%^&*() are allowed. ");
		 		return false;
		 	}
		
		 	
		 }
	});
</script>
</head>
<body>
<h2 style="text-align: center;"><logic:notEqual name="officerForm" property="requestOrigin" value="DT">Process Referral</logic:notEqual><logic:equal name="officerForm" property="requestOrigin" value="DT">Drug/Substance Abuse Testing</logic:equal> - Search Officer Profiles</h2>
<div id="instruction">
	<div class="required">
		<span style='font-size:12px;'>&#9670;</span>&nbsp;Required Fields
	</div>
	<div>
		<ul>
			<li>Enter/Select required fields then click <strong>Submit</strong> button to search</li>
		</ul>
	</div>
</div>
<html:form action="/searchOfficerProfile">
<div id="inputFields">
	<div id="lastName">
		<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1"  class='borderTableBlue'>
		   <tr>
		      <td  width='9%' id="searchLabel" class='referralDetailHead' width='4%' nowrap='nowrap' ><span style='font-size:12px;'>&#9670;</span>&nbsp;Probation Officer Last Name: </td>
			  <td  width='89%' class="referralDetailHead">
			  	<html:text styleId="lName" property="lastName"></html:text>
			  </td>
			</tr>
		</table>
	</div>
	<div id="firstName">
		<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1"  class='borderTableBlue'>
		   <tr>
		      <td  width='9%' id="searchLabel" class='referralDetailHead' nowrap='nowrap' >Probation Officer First Name: </td>
			  <td width='89%' class="referralDetailHead">
			  	<html:text styleId="fName" property="firstName"></html:text>
			  </td>
			</tr>
		</table>
	</div>
	<div id="middleName">
		<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1"  class='borderTableBlue'>
		   <tr>
		      <td  width='9%' id="searchLabel" class='referralDetailHead' width='4%' nowrap='nowrap' >Probation Officer Middle Name: </td>
			  <td  width='89%' class="referralDetailHead">
			  	<html:text styleId="mName" property="middleName"></html:text>
			  </td>
			</tr>
		</table>
	</div>
</div>
</html:form>
<div id="command">
	<input id="searchBtn" onclick="spinner();" type="button" value="Submit"/>
	<input id="refreshBtn" type="button" value="Refresh"/>
	<input id="cancelBtn"  type="button" value="Cancel"/>
</div>
</body>
</html:html>