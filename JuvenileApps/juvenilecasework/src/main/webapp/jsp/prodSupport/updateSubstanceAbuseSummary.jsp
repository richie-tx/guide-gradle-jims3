<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>

<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Juvenile Casework -/prodSupport/updateSubstanceAbuseSummary.jsp"</title>
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
	$(document).ready(function () {
		displayMessage("juvenileId",'<bean:write name="ProdSupportForm" property="originalSubstanceAbuseInfo.juvenileId"/>',
									'<bean:write name="ProdSupportForm" property="substanceAbuseInfo.juvenileId"/>');
		
		displayMessage("casefileId",'<bean:write name="ProdSupportForm" property="originalSubstanceAbuseInfo.casefileId"/>',
									'<bean:write name="ProdSupportForm" property="substanceAbuseInfo.casefileId"/>');
		
		displayMessage("referralNumber",'<bean:write name="ProdSupportForm" property="originalSubstanceAbuseInfo.referralNum"/>',
									'<bean:write name="ProdSupportForm" property="substanceAbuseInfo.referralNum"/>');
		
		displayMessage("sAbuse",'<bean:write name="ProdSupportForm" property="originalSubstanceAbuseInfo.sAbuse"/>',
									'<bean:write name="ProdSupportForm" property="substanceAbuseInfo.sAbuse"/>');
		
		displayMessage("substanceType",'<bean:write name="ProdSupportForm" property="originalSubstanceAbuseInfo.substanceType"/>',
									'<bean:write name="ProdSupportForm" property="substanceAbuseInfo.substanceType"/>');
		displayMessage("treatmentLocation",'<bean:write name="ProdSupportForm" property="originalSubstanceAbuseInfo.treatmentLocation"/>',
		'<bean:write name="ProdSupportForm" property="substanceAbuseInfo.treatmentLocation"/>');
	})
	
	function displayMessage(id, originalValue, updatedValue){
		console.log("originalValue" + originalValue);
		console.log("updatedValue" + updatedValue);
		if (originalValue != updatedValue){
			$("#"+id).append("<td class='message'>Updated from previous value, " + originalValue + "</td>");
		}
	}
	
</script>

</head>
<body>
<html:form action="/PerformUpdateTestingSession" onsubmit="return this;">
<h2 align="center">Update Substance Abuse Summary</h2>

<p align="center"><font color="green" face="bold"><i>Record Successfully Updated</i></font></p>	 

<table  class="resultTbl" border="1" width="1000" align="center">
	<tr id="substanceAbuseId">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Substance Abuse ID</font></td>
		<td><bean:write name="ProdSupportForm" property="substanceAbuseInfo.substanceAbuseId"/></td>
	</tr>
	<tr id="juvenileId">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile ID</font></td>
		<td><bean:write name="ProdSupportForm" property="substanceAbuseInfo.juvenileId"/></td>
	</tr>
	<tr id="casefileId">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Casefile ID</font></td>
		<td><bean:write name="ProdSupportForm" property="substanceAbuseInfo.casefileId"/></td>
	</tr>
	<tr id="referralNumber">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Number</font></td>
		<td><bean:write name="ProdSupportForm" property="substanceAbuseInfo.referralNum"/></td>
	</tr>
	<tr id="sAbuse">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SAbuse</font></td>
		<td><bean:write name="ProdSupportForm" property="substanceAbuseInfo.sAbuse"/></td>
	</tr>
	<tr id="substanceType">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Substance Type</font></td>
		<td><bean:write name="ProdSupportForm" property="substanceAbuseInfo.substanceType"/></td>
	</tr>
	<tr id="treatmentLocation">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Treatment Location</font></td>
		<td><bean:write name="ProdSupportForm" property="substanceAbuseInfo.treatmentLocation"/></td>
	</tr>
</table>
</html:form>


<table align="center"">
	<tr>
		<td>
			<html:form action="/UpdateSubstanceAbuseQuery?clr=Y">
				<html:submit value="Back to Query"/>
			</html:form>
		</td>
		<td>
			<html:form method="post" action="/MainMenu">
						<input id="backBtn" type="submit" value="Back to Main Menu" />
			</html:form>
		</td>
	</tr>
</table>

</body>
</html:html>