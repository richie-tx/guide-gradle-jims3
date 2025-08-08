<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>


<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/updateMaysiAssessQueryResult.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/updateMaysiAssessment.js"></script>
<script type='text/javascript'>
$(document).ready(function() {
    $("#newhasPreviousMaySI").change(function() {
    	if (this.checked) {
            $(this).val("true").attr("checked", "checked");
            $("#newhasPreviousMaySIhidden").val("true");
        } else {
            $(this).val("false").removeAttr("checked");
            $("#newhasPreviousMaySIhidden").val("false");
        }
    });
    $("#newisAdministered").change(function() {
    	if (this.checked) {
            $(this).val("true").attr("checked", "checked");
            $("#newisAdministeredhidden").val("true");
        } else {
            $(this).val("false").removeAttr("checked");
            $("#newisAdministeredhidden").val("false");
        }
    });
});
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
	<html:form styleId="updateMaysiAssessForm" action="/PerformUpdateMaysiAssess" onsubmit="return this;">

		<h3 align="center">
			MAYSI Assessment Results
		</h3>


<logic:notEmpty name="ProdSupportForm" property="maysis">
<logic:iterate id="maysis" name="ProdSupportForm" property="maysis">
	
	<table  class="resultTbl" border="1" width="800" align="center">

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">MAYSIASSESSMNT_ID</font></td>
		<td><font size="-1"><bean:write name="maysis" property="assessmentId" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSESSOPTION</font></td>
		<td><font size="-1"><bean:write name="maysis" property="assessmentOption" />&nbsp;</font></td>
		
		<td><html:select name="ProdSupportForm"	property="assessoptionBox" style="width:250px">
				<html:option value="">Select a New Value: </html:option>
				<html:optionsCollection name="ProdSupportForm" property="assessoptionCodes" label="description" value="code" />
			</html:select>				
		</td>
		
	</tr>
					
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSESSOFFICER_ID</font></td>
		<td><font size="-1"><bean:write name="maysis" property="assessOfficerId" />&nbsp;</font></td>
		<td>New Value:&nbsp;
			<input id="newassessOfficerId" name="assessOfficerId" value='<bean:write name="ProdSupportForm" property="assessOfficerId"  />' />
		</td>
		
	</tr>
			
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSESSDATE</font></td>
		<td><font size="-1"><bean:write name="maysis" property="assessmentDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>

		<td>New Value:&nbsp;
			<input id="newDate" value='<bean:write name="maysis" property="assessmentDate" formatKey="date.format.mmddyyyy" />' />
		</td>

	</tr>
					
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUMBER</font></td>
		<td><font size="-1"><bean:write name="maysis" property="referralNumber" />&nbsp;</font></td>
		<td>New Value:&nbsp;
		<input id="newReferralNum" name="newReferralNum" value='<bean:write name="ProdSupportForm" property="newReferralNum"  />' /></td>
	
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">HASPREVIOUSMAYSI</font></td>
		<td><font size="-1"><bean:write name="maysis" property="hasPreviousMAYSI" />&nbsp;</font></td>
		<td>New Value:&nbsp; <input id="newhasPreviousMaySI" name="hasPreviousMaysi" value='<bean:write name="ProdSupportForm" property="hasPreviousMaysi" />' 
    type="checkbox" ${ProdSupportForm.hasPreviousMaysi ? "checked" : ""} />
    <input type="hidden" id="newhasPreviousMaySIhidden" name="hasPreviousMaysi" value="<bean:write name="ProdSupportForm" property="hasPreviousMaysi" />">
		</td>
		
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISADMINISTERED</font></td>
		<td><font size="-1"><bean:write name="maysis" property="administered" />&nbsp;</font></td>
		<td>New Value:&nbsp;
			<input id="newisAdministered" name="isAdministered" value='<bean:write name="ProdSupportForm" property="isAdministered"  />' type="checkbox" ${ProdSupportForm.isAdministered ? "checked" : ""} />
			<input type="hidden" id="newisAdministeredhidden" name="isAdministered" value="<bean:write name="ProdSupportForm" property="isAdministered" />">
		</td>
		
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVLOCUNIT_ID</font></td>
		<td><font size="-1"><bean:write name="maysis" property="locationUnitId" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LENGTHOFSTAY</font></td>
		<td><font size="-1"><bean:write name="maysis" property="lengthOfStayId" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">FACILITYTYPECD</font></td>
		<td><font size="-1"><bean:write name="maysis" property="facilityTypeId" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
		<td><font size="-1"><bean:write name="maysis" property="juvenileNum" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">REASONNOTDONE</font></td>
		<td><font size="-1"><bean:write name="maysis" property="reasonNotDoneId" />&nbsp;</font></td>
		<td>New Value:&nbsp;
			<input id="newreasonNotDone" name="reasonNotDone" value='<bean:write name="ProdSupportForm" property="reasonNotDone"  />' />
		</td>
		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">OTHERREASONNOTDONE</font></td>
		<td><font size="-1"><bean:write name="maysis" property="otherReasonNotDone" />&nbsp;</font></td>
		<td>New Value:&nbsp;
			<input id="newotherReasonNotDone" name="otherReasonNotDone" value='<bean:write name="ProdSupportForm" property="otherReasonNotDone"  />' />
		</td>
		
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">GENDERCD</font></td>
		<td><font size="-1"><bean:write name="maysis" property="sexId" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RACECD</font></td>
		<td><font size="-1"><bean:write name="maysis" property="raceId" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TESTAGE</font></td>
		<td><font size="-1"><bean:write name="maysis" property="testAge" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td><font size="-1"><bean:write name="maysis" property="createUser" />&nbsp;</font></td>
	</tr>
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td><font size="-1"><bean:write name="maysis" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td><font size="-1"><bean:write name="maysis" property="updateUser" />&nbsp;</font></td>
	</tr>
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td><font size="-1"><bean:write name="maysis" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write name="maysis" property="createJims2User" />&nbsp;</font></td>
	</tr>
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write name="maysis" property="updateJims2User" />&nbsp;</font></td>
	</tr>
  </table>
</logic:iterate>
</logic:notEmpty>


<logic:empty name="ProdSupportForm" property="maysis">
			<table border="1" width="700" align="center">
				<tr>
					<td><h3 align="center"><i>No assessments found.</i></h3></td>
				</tr>
			</table>
</logic:empty>

<table align="center"">
	<tr>
		<td colspan="3">&nbsp;</td>
	</tr>
	<tr>
		<logic:notEmpty name="ProdSupportForm" property="maysis">
		<td>
			<input id="updateRecord" type="button" name="Update Record" value="Update Record"/>
		</td>
		</logic:notEmpty>
	</tr>
</table>
<html:hidden styleId="newAssignmentDate" name="ProdSupportForm" property="newBeginDate" />
</html:form>

	<table align="center">
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
			<p align="center">
			<html:form action="/UpdateMaysiAssessQuery?clr=Y" onsubmit="return this;">
				<html:submit value="Back to Query" />
			</html:form>
			</p>
			</td>
		</tr>
	</table>
</div>
</html:html>