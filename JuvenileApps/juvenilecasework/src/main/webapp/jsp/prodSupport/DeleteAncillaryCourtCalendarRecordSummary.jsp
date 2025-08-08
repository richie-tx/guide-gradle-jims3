<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic"%>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>


<head>

<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Juvenile Casework
	-/prodSupport/DeleteAncillaryCourtCalendarRecordSummary</title>


<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript'
	src="/<msp:webapp/>js/prodSupport/DeleteAncillaryCourtCalendarRecordQuery.js"></script>

</head>

<body>

	<html:form action="/PerformUpdateDetentionCourtRecord"
		onsubmit="return this;">
		<!-- BEGIN Error Message Table -->
		<logic:messagesPresent message="true">
			<table width="100%">
				<tr>
					<td align="center" class="messageAlert"><html:messages
							id="message" message="true">
							<font style="font-weight: bold;" color="green" size="3"
								face="Arial"><bean:write name="message" />
						</html:messages></font>
					</td>
				</tr>
			</table>
		</logic:messagesPresent>

		<!-- BEGIN ERROR TABLE -->
		<table width="100%">
			<%-- <tr>
				<td align="center" class="errorAlert"><html:errors></html:errors></td>
			</tr> --%>
			<tr>
				<td align="center" colspan="4" class="errorAlert"><font
					style="font-weight: bold;" color="red" size="3" face="Arial"><html:errors></html:errors>
				</font>
				</td>
			</tr>
		</table>
		<!-- END ERROR TABLE -->
		<div>

			<h2 align="center">
				Delete Petition Number :
				<bean:write name="ProdSupportForm" property="petitionNum" />
			</h2>

			<logic:notEmpty name="ProdSupportForm" property="ancillaryCalendarRecords">
				<table class="resultTbl" border="1" width="750" align="center">
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">Petition Number</font></td>
					<td><font size="-1"><bean:write name="ProdSupportForm" property="petitionNum" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">Respondent Name</font></td>
					<td><font size="-1"><bean:write name="ProdSupportForm" property="respondentName" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">Ancillary Record ID</font></td>
					<td><font size="-1"><bean:write name="ProdSupportForm" property="docketEventId" />&nbsp;</font></td>
				</tr>

			</table>
			
			</logic:notEmpty>
			
			
			<p align="center"><b><i><font style="font-weight: bold;"
							color="green" size="3" face="Arial">Record Successfully Deleted</font></i></b></p>
							
							
			<logic:notEmpty	name="ProdSupportForm" property="ancillaryCalendarRecords">
	<logic:iterate id="ancillaryCalendarRecords" name="ProdSupportForm" property="ancillaryCalendarRecords">
	<h3 align="center">Ancillary Court Details</h3>
	
	<table class="resultTbl" border="1" width="800" align="center">
	
	 <tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Chain Number</font></td>
		<td><font size="-1"><bean:write name="ancillaryCalendarRecords" property="chainNumber"/>&nbsp;</font></td>
		
	</tr>	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Sequence Number</font></td>
		<td><font size="-1"><bean:write name="ancillaryCalendarRecords" property="seqNum"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Assigned Court</font></td>
		<td><font size="-1"><bean:write name="ancillaryCalendarRecords" property="court"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Hearing Date</font></td>
		<td><font size="-1"><bean:write name="ancillaryCalendarRecords" property="courtDate"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Hearing Time</font></td>
		<td><font size="-1"><bean:write name="ancillaryCalendarRecords" property="courtTime"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Hearing Decision</font></td>
		<td><font size="-1"><bean:write name="ancillaryCalendarRecords" property="hearingResult"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Hearing Disposition</font></td>
		<td><font size="-1"><bean:write name="ancillaryCalendarRecords" property="hearingDisposition"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Reset Hearing Type</font></td>
		<td><font size="-1"><bean:write name="ancillaryCalendarRecords" property="resetHearingType"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Petition Number</font></td>
		<td><font size="-1"><bean:write name="ancillaryCalendarRecords" property="petitionNumber"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Setting Reason</font></td>
		<td><font size="-1"><bean:write name="ancillaryCalendarRecords" property="settingReason"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Type Case</font></td>
		<td><font size="-1"><bean:write name="ancillaryCalendarRecords" property="typeCase"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Filing Date</font></td>
		<td><font size="-1"><bean:write name="ancillaryCalendarRecords" property="filingDate"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Attorney Bar Number</font></td>
		<td><font size="-1"><bean:write name="ancillaryCalendarRecords" property="barNum"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Attorney Connection</font></td>
		<td><font size="-1"><bean:write name="ancillaryCalendarRecords" property="attorneyConnection"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Attorney Name</font></td>
		<td><font size="-1"><bean:write name="ancillaryCalendarRecords" property="attorneyName"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Respondent Name</font></td>
		<td><font size="-1"><bean:write name="ancillaryCalendarRecords" property="respondantName"/>&nbsp;</font></td>
	</tr>
	</table>
	</logic:iterate>
	</logic:notEmpty>
			<BR>

			<logic:empty name="ProdSupportForm" property="ancillaryCalendarRecords">
				<br>
				<table align="center" border="1" width="700">
					<tr>
						<td>
							<h3 align="center">
								<font color="green"><i>No Records Returned</i>
								</font>
							</h3></td>
					</tr>
				</table>
			</logic:empty>

		</div>


		<table align="center"">
			<tr>

				<td>&nbsp;</td>

			</tr>

			<tr>
				<td><html:button property="submitAction" styleId="backToQryBtn">
						<bean:message key="button.backToSearch"></bean:message>
					</html:button></td>
			</tr>
		</table>
		


	</html:form>
<table align="center" border="0" width="500">
		<tr>
		<td colspan="2" align="center">
		<html:form method="post" action="/MainMenu" onsubmit="return this;">
		<html:submit onclick="return this;" value="Back to Main Menu"/>
		</html:form>
		</td>
		</tr>
    </table> 
</body>
</html:html>