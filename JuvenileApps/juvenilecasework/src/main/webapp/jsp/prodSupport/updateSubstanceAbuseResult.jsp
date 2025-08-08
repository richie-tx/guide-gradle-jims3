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
<title>Juvenile Casework -/prodSupport/updateSubstanceAbuseResult.jsp"</title>
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
		
		$('input[type=radio][name=substanceAbuseId]').change(function() {
			$("#subAbuseId").val( $(this).val() ); 
		})
		
	})

</script>

</head>

<body>

<html:form action="/PerformUpdateSubstanceAbuse" onsubmit="return this;">

<div>
	
	<h2 align="center">Results for Juvenile ID = 
			<bean:write name="ProdSupportForm" property="juvenileId" />
			and Substance Abuse ID <bean:write name="ProdSupportForm" property="substanceAbuseId" />
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
<logic:notEmpty	name="ProdSupportForm" property="substanceAbuseInfos">
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
	<h3 align="center">Substance Abuse Details</h3>	
	<table border="1" width="800" align="center">
	
	<tr>
		<td bgcolor="gray"></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Substance Abuse ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Casefile ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Number</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SAbuse</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Substance Type</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Treatment Location</font></td>
	</tr>
	
	<logic:iterate id="substanceAbuseInfo" name="ProdSupportForm" property="substanceAbuseInfos">
		<tr>
			<td width="1%">
				<input type="radio" name="substanceAbuseId" value="<bean:write name='substanceAbuseInfo' property='substanceAbuseId'/>" />
				<html:hidden styleId="subAbuseId"  name="ProdSupportForm" property="substanceAbuseId"/>
			</td>
			<td><font size="-1"><bean:write  name="substanceAbuseInfo" property="substanceAbuseId" />&nbsp;</font></td>
			<td><font size="-1"><bean:write  name="substanceAbuseInfo" property="juvenileId" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
			<td><font size="-1"><bean:write  name="substanceAbuseInfo" property="casefileId" />&nbsp;</font></td>
			<td><font size="-1"><bean:write  name="substanceAbuseInfo" property="referralNum" />&nbsp;</font></td>
			<td><font size="-1"><bean:write  name="substanceAbuseInfo" property="sAbuse" />&nbsp;</font></td>
			<td><font size="-1"><bean:write  name="substanceAbuseInfo" property="substanceType" />&nbsp;</font></td>
			<td><font size="-1"><bean:write  name="substanceAbuseInfo" property="treatmentLocation" />&nbsp;</font></td>
		</tr>
	</logic:iterate>
		
	</table>

	</logic:notEmpty>
	<BR>
	
	<table align="center"">
	
	<logic:notEmpty	name="ProdSupportForm" property="substanceAbuseInfos">
	<td>
	<p align="center">
	<html:submit property="submitAction" styleId="nextBtn"><bean:message key="button.next"/></html:submit>
	</p>
	</td>
	</logic:notEmpty>

	</table>
	
	
	<logic:empty name="ProdSupportForm" property="substanceAbuseInfos">
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

<html:form action="/UpdateSubstanceAbuseQuery?clr=Y" onsubmit="return this;">
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