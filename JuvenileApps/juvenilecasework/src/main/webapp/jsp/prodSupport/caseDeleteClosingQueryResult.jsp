<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>

 <link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>

<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/caseDeleteClosingQueryResult.jsp</title>

<script language="javascript">
$(document).ready(function(){
	$("#deleteRecords").click(function(e){
		if ( confirmDelete() ) {
			spinner();
		} else {
			e.preventDefault();
		}
		
	})
})

function setTableId(idName){
     document.forms[0].tableId.value = idName;
}

function confirmDelete(){
	if(confirm('Are you sure you want to delete all the closing records for this casefile?'))
		return true;	
	else
		return false;
}

</script>

</head>

<html:form action="/CaseDeleteClosingQuery" onsubmit="return this;">

<input type="hidden" name="tableId" value="" />

	<div>
	
	<h2 align="center">Results for CASEFILE_ID = 
			<bean:write name="ProdSupportForm" property="casefileId" /></h2>
	<br>
	<h4 align="center"><i>The following rows will be <font color="red">DELETED</font>.</i></h4>

<hr>
<logic:notEmpty	name="ProdSupportForm" property="casefileclosings">
	
	<p>&nbsp;</p>
	<h3 align="center">Associated Casefile Closings</h3>
	
	<table class="resultTbl" align="center" border="1" width="700" id="closings">
	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CASFILECLOSNG_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">SUPERVISIONOUTCOME</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CNTROLLINGREFERRAL</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CASFILECLOSNGSTATS</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">SUPERVISIONENDDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">EXPECTEDRELESEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="casefileclosings" name="ProdSupportForm" property="casefileclosings">
	<tr>
		<td><font size="-1"><bean:write  name="casefileclosings" property="casefileClosingInfoId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="supervisionOutcomeId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="controllingReferralId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="casefileClosingStatus" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="supervisionEndDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="expectedReleaseDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
</logic:notEmpty>
	  
<logic:empty name="ProdSupportForm" property="casefileClosingCount">
	    <i>No Result(s) Returned</i>
</logic:empty>

	<table border="0" width="700" align="center">
	
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
	
</html:form>

<table align="center"">
<tr>
<td>
<html:form action="/CaseDeleteClosingQuery?clr=Y">
<p align="center">
	<input type="submit" name="details" value="Back to Query"/>
</p>
</html:form>	
</td>
<td>&nbsp;</td>
<td>

<html:form action="/PerformCaseDeleteClosing">
<p align="center">
	<input type="submit" id="deleteRecords"
							name="Delete Records"
						    value="Delete Records" />
</p>
</html:form>

</td>
</tr>
</table>

</html:html>