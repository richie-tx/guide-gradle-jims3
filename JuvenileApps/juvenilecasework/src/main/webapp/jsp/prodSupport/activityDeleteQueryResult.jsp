<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/activityDeleteQueryResult.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script language="javascript">
function setTableId(idName){
     document.forms[0].tableId.value = idName;
}

function confirmDelete(){
	if(confirm('Are you sure you want to delete the activity and all its records?')) { 
		spinner();
		return true;	
	} else {
		return false;
	}
}
</script>
</head>


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

<html:form action="/PerformActivityDelete" onsubmit="return this;">

<div>
	<br>
	<h4 align="center"><i>The following activity will be <font color="red">DELETED</font>.</i></h4>	

	<logic:notEmpty	name="ProdSupportForm" property="activities">
	
	<logic:notEqual name="ProdSupportForm" property="activityCount" value="0">	 
	<p align="center">
	<font size="+1"><b>Activity Information for <bean:write name="ProdSupportForm" property="activityId" /></b></font>
	</p>
	
	<table class="resultTbl" border="1" width="700" align="center">

	<logic:iterate id="activities" name="ProdSupportForm" property="activities">
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACTIVITY_ID</font></td>
		<td><font size="-1"><bean:write  name="activities" property="activityId" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
		<td><font size="-1"><bean:write  name="activities" property="casefileId" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACTIVITYCD</font></td>
		<td><font size="-1"><bean:write  name="activities" property="codeId" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">COMMENTS</font></td>
		<td><font size="-1"><bean:write  name="activities" property="comments" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">INACTIVEDATE</font></td>
		<td><font size="-1"><bean:write  name="activities" property="inactiveDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TITLE</font></td>
		<td><font size="-1"><bean:write  name="activities" property="title" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACTIVITYDATE</font></td>
		<td><font size="-1"><bean:write  name="activities" property="activityDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td><font size="-1"><bean:write  name="activities" property="createUserID" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td><font size="-1"><bean:write  name="activities" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td><font size="-1"><bean:write  name="activities" property="updateUser" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td><font size="-1"><bean:write  name="activities" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="activities" property="createJIMS2UserID" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="activities" property="updateJIMS2UserID" />&nbsp;</font></td>
		</tr>
	</logic:iterate>
		
	</table>
	</logic:notEqual>	
	
	</logic:notEmpty>	
	
	<logic:empty name="ProdSupportForm" property="activities">
		<table border="1" width="700" align="center">
		<tr>
		<td>
	   <h3 align="center"><i>No activities found.</i></h3>
	   </td>
	   </tr>
	   </table>
	</logic:empty>
	
	</div>
	
</html:form>

<table align="center"">
<tr>

<td>&nbsp;</td>

<logic:notEmpty name="ProdSupportForm" property="activities">
<td>
<html:form action="/PerformActivityDelete">
<p align="center">
	<input type="submit" name="Delete Activity" value="Delete Activity" onClick="return confirmDelete();" />
</p>

</html:form>
</td>
</logic:notEmpty>

</tr>
</table>

<html:form action="/ActivityDeleteQuery?clr=Y" onsubmit="return this;">
<table align="center"">
	<tr>
		<td>
		<html:submit value="Back to Query"/>
		</td>
	</tr>
</table>
</html:form>

</html:html>