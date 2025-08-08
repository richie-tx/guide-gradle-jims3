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
<title>Juvenile Casework -/prodSupport/testingSessionDetail.jsp"</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />	
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script>
	$(document).ready(function () {

		$("#updateRecordBtn").click(function(){
			spinner();
		})
		if(typeof  $("#testDate") != "undefined"){
			datePickerSingle($("#testDate"),"Test  Date",true);
		}
	})
	
</script>

</head>
<body>
<html:form action="/PerformUpdateTestingSession" onsubmit="return this;">
<h2 align="center">Testing Session Detail for Session ID <bean:write name="ProdSupportForm" property="testingSessionId"/> </h2>
<!-- Error Message Area -->
	<table width="98%" border="0" align="center">
		<tr>
			<td align="center" class="errorAlert"><html:errors></html:errors></td>
		</tr>
	</table>
	
<table  class="resultTbl" border="1" width="1000" align="center">
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE NUMBER</font></td>
		<td><bean:write name="ProdSupportForm" property="juvenileId"/></td>
		<td></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE NAME</font></td>
		<td><bean:write name="ProdSupportForm" property="juvenileName"/></td>
		<td></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SESSION ID</font></td>
		<td><bean:write name="ProdSupportForm" property="testingSessionId"/></td>
		<td></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SESSION DATE</font></td>
		<td>
			<bean:define id="testingSessionId" name="ProdSupportForm" property="testingSessionId" type="java.lang.String"/>
			<logic:iterate id="testingSessionInfo" name="ProdSupportForm" property="testingSessionInfos">
				<logic:equal name="testingSessionInfo" property="testingSessionId" 
							value='<%= testingSessionId %>'>
					<bean:write  name="testingSessionInfo" property="testSessionDate" formatKey="date.format.mmddyyyy" />
				</logic:equal>
			</logic:iterate>
		</td>
		<td><html:text styleId="testDate" name="ProdSupportForm" property="testDate"/></td>
	</tr>

</table>

<table align="center"">
	<tr>
	<td>
		<p align="center">
		<html:submit property="submitAction" styleId="updateRecordBtn"><bean:message key="button.updateRecord"/></html:submit>
		</p>
	</td>
	<td>
		<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
	</td>
	</tr>

</table>
</html:form>

<html:form action="/UpdateTestingSessionQuery?clr=Y" onsubmit="return this;">
<table align="center"">
	<tr>
		<td>
		<html:submit value="Back to Query"/>
		</td>
	</tr>
</table>
</html:form>
</body>
</html:html>