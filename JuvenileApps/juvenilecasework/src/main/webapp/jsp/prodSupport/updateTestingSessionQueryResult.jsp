<!DOCTYPE HTML>

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
<title>Juvenile Casework -/prodSupport/updateTestingSessionQueryResult.jsp"</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />	
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script>
	$(document).ready(function(){
		$("#nextBtn").click(function(){
			if ( !$('input[type=radio]:checked').size() > 0 ) {
				alert("Please select a record to update.");
				return false;
			} else {
				spinner();
				return true;
			}
		})
		
		$('input[type=radio][name=testingSessionId]').change(function() {
			$("#sessionId").val( $(this).val() ); 
		})
		
	})

</script>

</head>

<body>

<html:form action="/PerformUpdateTestingSession" onsubmit="return this;">

<div>
	
	<h2 align="center">Results for Juvenile ID = 
			<bean:write name="ProdSupportForm" property="juvenileId" />
			and Test Session ID <bean:write name="ProdSupportForm" property="testingSessionId" />
			</h2>
	     
<!-- Error Message Area -->
    <logic:notEqual name="ProdSupportForm" property="msg" value="">
	<table border="0" width="700" align="center">

	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<bean:write name="ProdSupportForm" property="msg" />
	 		</font></td>
	</tr>
	</table>
	</logic:notEqual>
<!-- End Error Message Area -->	     
<logic:notEmpty	name="ProdSupportForm" property="testingSessionInfos">
	<table border="1" width="750" align="center">
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Number</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="juvenileId"/>&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Name</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="juvenileName"/></font></td>
		</tr>
	</table>     
	<p align="center"><b><i>Select the radio button next to the record <br>
							you want to update and click the Next button.</i></b></p>	     
	<h3 align="center">Testing Session Details</h3>	
	<table border="1" width="800" align="center">
	
	<tr>
		<td bgcolor="gray"></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Session ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Session Date</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DSM Table</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">IQ Table</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Achievement Table</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Adaptive Function Table</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Adaptive Behavior Table</font></td>
	</tr>
	
	<logic:iterate id="testingSessionInfo" name="ProdSupportForm" property="testingSessionInfos">
		<tr>
			<td width="1%">
				<input type="radio" name="testingSessionId" value="<bean:write name='testingSessionInfo' property='testingSessionId'/>" />
				<html:hidden styleId="sessionId"  name="ProdSupportForm" property="testingSessionId"/>
			</td>
			<td><font size="-1"><bean:write  name="testingSessionInfo" property="testingSessionId" />&nbsp;</font></td>
			<td><font size="-1"><bean:write  name="testingSessionInfo" property="testSessionDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
			<td><font size="-1"><bean:write  name="testingSessionInfo" property="jCMHDSMLISTId" />&nbsp;</font></td>
			<td><font size="-1"><bean:write  name="testingSessionInfo" property="jCMHIQTESTLISTId" />&nbsp;</font></td>
			<td><font size="-1"><bean:write  name="testingSessionInfo" property="jCMHACHLISTId" />&nbsp;</font></td>
			<td><font size="-1"><bean:write  name="testingSessionInfo" property="jCMHADFNCLISTId" />&nbsp;</font></td>
			<td><font size="-1"><bean:write  name="testingSessionInfo" property="jCMHABSLISTId" />&nbsp;</font></td>
		</tr>
	</logic:iterate>
		
	</table>

	</logic:notEmpty>
	<BR>
	
	<table align="center"">
	
	<logic:notEmpty	name="ProdSupportForm" property="testingSessionInfos">
	<td>
	<p align="center">
	<html:submit property="submitAction" styleId="nextBtn"><bean:message key="button.next"/></html:submit>
	</p>
	</td>
	</logic:notEmpty>

	</table>
	
	
	<logic:empty name="ProdSupportForm" property="testingSessionInfos">
	<br>
	<table align="center" border="1" width="700">
		<tr>
		<td>
	   <h3 align="center"><font color="green"><i>No Records Returned</i></font></h3>
	   </td>
	   </tr>
	   </table>
	</logic:empty>

</div>
	

<table align="center"">
<tr>

<td>&nbsp;</td>

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