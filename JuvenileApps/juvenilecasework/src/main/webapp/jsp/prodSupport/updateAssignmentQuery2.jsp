<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<title>Juvenile Casework -/prodSupport/updateAssignmentQuery2.jsp</title>
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script language="javascript">
$( document ).ready(function() {
	$("#asgmtId").val('');
	
	$("#updateAsgmtBtn").click(function(){
		var assignmentId = $("#asgmtId").val();
		if ( assignmentId != ""
				&& assignmentId.length > 0 ){
			if ( isNaN( assignmentId ) ) {
				alert("Assignment Id is not valid. Please input a valid Assignment Id.")
			} else {
				spinner();
				$("#updateAsgmtForm").submit();
			}
		} else {
			alert("Assignment Id is required.");
		}
	})
});

function fillId(value)
{
	document.forms[0].assignmentId.value = value;
	return true;
}
</script>

</head>

<body>

<h2 align="center">Production Support - Assignment Query</h2>
<hr>

<p align="center">Select an assignment ID from the list and click SUBMIT to view current data.</p>

	<div align="center">
	<html:form styleId="updateAsgmtForm" method="post" action="/UpdateAssignmentQuery?edit=Y" onsubmit="return this;">
	
	<logic:notEmpty	name="ProdSupportForm" property="assignments">
	
	<p>&nbsp;</p>
	<h3 align="center">Associated Assignments</h3>
	<h4 align="center">CasefileID: <bean:write  name="ProdSupportForm" property="casefileId" /></h4>
	
	<table border="1" width="700" align="center">
	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ASSIGNMENT_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">JPOUSERID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ASSMNTADDDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ASSMNTTYPE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">REFERRALNUMBER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">REFSEQNUM</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CASEFILEID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">SERVICEUNITCD</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ASSMNTLEVELCD</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	<logic:iterate id="assignments" name="ProdSupportForm" property="assignments">
	<tr>
		<td>
		 	<input type="radio" name="radioProp" value="<bean:write name="assignments" property="assignmentId"/>"
		 		onclick="fillId(<bean:write name="assignments" property="assignmentId"/>)">
		 		<font size="-1"><bean:write name="assignments" property="assignmentId"/>&nbsp;</font>
		</td>
		<td>
	 		<font size="-1"><bean:write name="assignments" property="jpoUserId"/>&nbsp;</font>
		</td>
		<td>
			<font size="-1"><bean:write name="assignments" property="assignmentDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font>
		</td>
		<td>
			<font size="-1"><bean:write name="assignments" property="assignmentType"/>&nbsp;</font>
		</td>
		<td>
			<font size="-1"><bean:write name="assignments" property="referralNum"/>&nbsp;</font>
		</td>
		<td>
			<font size="-1"><bean:write name="assignments" property="refSeqNum"/>&nbsp;</font>
		</td>
		<td>
			<font size="-1"><bean:write name="assignments" property="caseFileId"/>&nbsp;</font>
		</td>
		<td>
			<font size="-1"><bean:write name="assignments" property="serviceUnitId"/>&nbsp;</font>
		</td>
		<td>
			<font size="-1"><bean:write name="assignments" property="assessmentLevelId"/>&nbsp;</font>
		</td>
		<td>
			<font size="-1"><bean:write name="assignments" property="createUserID"/>&nbsp;</font>
		</td>
		<td>
			<font size="-1"><bean:write name="assignments" property="createDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font>
		</td>
		<td>
			<font size="-1"><bean:write name="assignments" property="updateUser"/>&nbsp;</font>
		</td>
		<td>
			<font size="-1"><bean:write name="assignments" property="updateDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font>
		</td>
		<td>
			<font size="-1"><bean:write name="assignments" property="createJIMS2UserID"/>&nbsp;</font>
		</td>
		<td>
			<font size="-1"><bean:write name="assignments" property="updateJIMS2UserID"/>&nbsp;</font>
		</td>		
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	&nbsp;
	<table border="0" width="700">
		<tr>
			<td align="right"><font face="" style="FONT-FAMILY: Arial"
				color="#0000aa"><strong> Assignment ID:</strong></font></td>
			<td>
				<html:text styleId="asgmtId" property="assignmentId" size="10" maxlength="15" />
			</td>
		</tr>

		<tr>
		<td>&nbsp;</td>
		<td>
		
		<input id="updateAsgmtBtn" type="button"  value="Submit"/>
		
		</td></tr>

		<tr>
		<td>&nbsp;</td>
		</tr>
		</table>
	</html:form>
	
	<table border="0">
		<tr>
		<td align="center">
		<html:form method="post" action="/UpdateAssignmentQuery?clr=Y" onsubmit="return this;">
		<html:submit onclick="return this;" value="Search a Different Casefile"/>
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