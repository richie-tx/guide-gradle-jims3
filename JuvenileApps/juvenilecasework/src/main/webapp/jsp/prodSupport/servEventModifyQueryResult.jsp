<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<!--5/7/2014    r carter  changed name as part of prod support update  -->
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/servEventModifyQueryResult.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script language="javascript">

$(document).ready(function(){
	$("#updateRecord").click(function(){
		if ( confirmUpdate() ) {
			spinner();
		} else {
			return false;
		}
	})
})

function confirmUpdate(){
	if(confirm('Are you sure you want to update the service event?'))
			return true;	
		else
			return false;
}

</script>

</head>

<!-- Error Message Area -->
<logic:notEqual name="ProdSupportForm" property="msg" value="">
	<table border="0" width="700" align="center">

		<tr align="center">
			<td colspan="4"><font style="font-weight: bold;" color="#FF0000"
				size="3" face="Arial"> <bean:write name="ProdSupportForm"
				property="msg" /> </font></td>
		</tr>
	</table>
</logic:notEqual>
<!-- End Error Message Area -->


<div>
<html:form action="/PerformModifyServEvent" onsubmit="return this;">

	<h2 align="center">Results for Service Event ID = <bean:write
		name="ProdSupportForm" property="serveventId" /></h2>
	<logic:notEmpty name="ProdSupportForm" property="servattends">

		<p>&nbsp;</p>


		<logic:iterate id="servattends" name="ProdSupportForm"
			property="servattends">
			<table class="resultTbl" border="1" width="750" align="center">

				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">SERVATTEND_ID</font></td>
					<td><font size="-1"><bean:write name="servattends"
						property="serviceEventAttendanceId" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">SERVEVENT_ID</font></td>
					<td><font size="-1"><bean:write name="servattends"
						property="serviceEventId" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
					<td><font size="-1"><bean:write name="servattends"
						property="juvenileId" />&nbsp;</font></td>
				</tr>
				
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ATTENDSTATUSCD</font></td>
		<td><font size="-1"><bean:write  name="servattends" property="attendanceStatusCd" />&nbsp;</font></td>
		<td>  
			<html:select name="ProdSupportForm" property="attendstatusBox" style="width:250px">
				<html:option value="">Select a New Value: </html:option>
				<html:optionsCollection name="ProdSupportForm" property="attendstatusCodes" label="description" value="code" />
			</html:select>
		</td>
		</tr>
		<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">PROGRESSNOTES</font></td>
					<td><font size="-1"><bean:write name="servattends"
						property="progressNotes" />&nbsp;</font></td>
				</tr>
		<tr>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">ADDLATTENDEES</font></td>
				<td><font size="-1"><bean:write name="servattends"
						property="addlAttendees" />&nbsp;</font></td>
				<TD>Enter a New Value: <html:text property="newAddlAttendees" size="10" maxlength="15" /></TD>
		</tr>		
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
					<td><font size="-1"><bean:write name="servattends"
						property="createUser" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
					<td><font size="-1"><bean:write name="servattends"
						property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEUSER</font></td>
					<td><font size="-1"><bean:write name="servattends"
						property="updateUser" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEDATE</font></td>
					<td><font size="-1"><bean:write name="servattends"
						property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
					<td><font size="-1"><bean:write name="servattends"
						property="createJims2User" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
					<td><font size="-1"><bean:write name="servattends"
						property="updateJims2User" />&nbsp;</font></td>
				</tr>
			</table>
		</logic:iterate>

	</logic:notEmpty> <logic:empty name="ProdSupportForm" property="servattends">
		<table border="1" width="700" align="center">
			<tr>
				<td>
				<h3 align="center"><i>No associated Service Events.
				Found</i></h3>
				</td>
			</tr>
		</table>
	</logic:empty>

	<table align="center"">
		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
		<tr>
			<logic:notEmpty name="ProdSupportForm" property="servattends">
					<td>
					<input type="submit" 
						id="updateRecord"
						value="Update Record" />
					</td>
			</logic:notEmpty>
		</tr>
		</table>
		</html:form>
		<table align="center">
		<tr>
		<td>&nbsp;</td>
		</tr>
		<tr>
		<td><p align="center"><html:form action="/ModifyServEventQuery?clr=Y"
				 onsubmit="return this;">
				<html:submit value="Back to Query" />
			</html:form></p></td>
		</tr>
	</table>
	</div>
</html:html>