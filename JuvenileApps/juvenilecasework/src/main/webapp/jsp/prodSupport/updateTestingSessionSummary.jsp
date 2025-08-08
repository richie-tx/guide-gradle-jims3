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
<title>Juvenile Casework -/prodSupport/updateTestingSessionSummary.jsp"</title>
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

		if(typeof  $("#testDate") != "undefined"){
			datePickerSingle($("#testDate"),"Test  Date",true);
		}
		
		displayMessage("testDate",'<bean:write name="ProdSupportForm" property="originalTestDate"/>', '<bean:write name="ProdSupportForm" property="testDate"/>'  );
	})
	
	function displayMessage(id, originalValue, updatedValue){
		if (originalValue != updatedValue){
			$("#"+id).append("<td class='message'>Updated from previous value, " + originalValue + "</td>");
		}
	}
	
</script>

</head>
<body>
<html:form action="/PerformUpdateTestingSession" onsubmit="return this;">
<h2 align="center">Update Testing Session Summary</h2>

<p align="center"><font color="green" face="bold"><i>Record Successfully Updated</i></font></p>	 

<table  class="resultTbl" border="1" width="1000" align="center">
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE NUMBER</font></td>
		<td><bean:write name="ProdSupportForm" property="juvenileId"/></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE NAME</font></td>
		<td><bean:write name="ProdSupportForm" property="juvenileName"/></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SESSION ID</font></td>
		<td><bean:write name="ProdSupportForm" property="testingSessionId"/></td>
	</tr>
	<tr id="testDate">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SESSION DATE</font></td>
		<td>
			<bean:write name="ProdSupportForm" property="testDate"/>
		</td>
	</tr>

</table>
</html:form>


<table align="center"">
	<tr>
		<td>
			<html:form action="/UpdateTestingSessionQuery?clr=Y">
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