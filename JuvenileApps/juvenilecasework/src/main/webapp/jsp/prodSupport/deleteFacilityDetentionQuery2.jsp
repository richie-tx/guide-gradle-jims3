<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
 
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/deleteFacilityDetentionQuery2.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script language="javascript">

function fillId(value)
{
	document.forms[0].detentionId.value = value;
	return true;
}
</script>

</head>

<body>

<h2 align="center">Production Support - Facilities Detention Details Query -- Delete</h2>
<hr>

<p align="center">Select a detail from the list and click DELETE to view current data</p>

<div align="center">
<html:form method="post" action="/DeleteFacilityDetentionQuery?edit=Y" onsubmit="return this;">

<br />

<logic:notEmpty name="ProdSupportForm" property="facilityDetentionList">

	<p>&nbsp;</p>

    <table border="1" width="750" align="center">

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DETENTION ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE NAME</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE NUMBER</font></td>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DETAINED FACILITY</font></td>	
			<td bgcolor="gray"><font color="white" face="bold" size="-1">SEQUENCE NUMBER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CURRENT REFERRAL NUMBER</font></td>		
		<td bgcolor="gray"><font color="white" face="bold" size="-1">FACILITY ADMIT DATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RELEASE DATE</font></td>			
	</tr>
	
	<logic:iterate id="facilityDetention" name="ProdSupportForm" property="facilityDetentionList">

	<tr>
		<td>
	 		<input type="radio" name="radioProp" value="<bean:write name="facilityDetention" property="detentionId"/>"
	 		onclick="fillId(<bean:write name="facilityDetention" property="detentionId"/>)">
	 		<bean:write name="facilityDetention" property="detentionId"/>
		</td>			
		<td><font size="-1">
			<bean:write name="facilityDetention" property="firstName" />&nbsp;
			<bean:write name="facilityDetention" property="middleName" />&nbsp;
			<bean:write name="facilityDetention" property="lastName" />&nbsp;
			<bean:write name="facilityDetention" property="suffixName" />
			</font>
		</td>
		<td><font size="-1"><bean:write name="facilityDetention" property="juvenileId" />&nbsp;</font></td>	
			<td><font size="-1"><bean:write name="facilityDetention" property="facilityCode" />&nbsp;</font></td>			
		<td><font size="-1"><bean:write name="facilityDetention" property="sequenceNum" />&nbsp;</font></td>
		<td><font size="-1"><bean:write name="facilityDetention" property="currentReferralNum" />&nbsp;</font></td>
		<td><font size="-1"><bean:write name="facilityDetention" property="admittedDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
		<td><font size="-1"><bean:write name="facilityDetention" property="releaseDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>				
				
	</tr>				

	</logic:iterate>
  </table>
  <table border="0" width="700">
	<tr>
		<td align="right">
		<font face="" style="FONT-FAMILY: Arial" color="#0000aa"><strong> Selected Detail:</strong></font>
		</td>
		<td><html:text property="detentionId" size="10" maxlength="15" readonly="true"/>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td><html:submit onclick="spinner()" value="Delete" /></td>
	</tr>

	<tr>
		<td>&nbsp;</td>
	</tr>
</table>
</logic:notEmpty>

	
<logic:empty name="ProdSupportForm" property="facilityDetentionList">
	<table align="center" border="0" width="700">
		<tr>
			<td align="center"><h4><i>No Facility Detention records found.</i></h4></td>
		</tr>
	</table>
</logic:empty>	

	&nbsp;
</html:form>
	
	<table border="0">
		<tr>
		<td align="center">
		<html:form method="post" action="/DeleteFacilityDetentionQuery?clr=Y" onsubmit="return this;">
		<html:submit onclick="return this;" value="Search a Different Record"/>
		</html:form>
		</td>
	
		<td>&nbsp;</td>
	
		<td align="center">
		<html:form method="post" action="/MainMenu" onsubmit="return this;">
		<html:submit onclick="return this;" value="Back to Main Menu"/>
		</html:form>
		</td>
		</tr>

    </table>
    
    <table border="0" width="700">
	
	<tr><td colspan="4">&nbsp;</td></tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	
	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<bean:write name="ProdSupportForm" property="msg" />
			</logic:notEqual> 
			</font></td>
	</tr>
	</table>
    
	</div>

</body>
</html:html>