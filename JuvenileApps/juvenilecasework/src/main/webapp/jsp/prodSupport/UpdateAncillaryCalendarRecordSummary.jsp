<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>


<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title>Juvenile Casework
	-/prodSupport/UpdateAncillaryCalendarRecordSummary</title>


<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript'
	src="/<msp:webapp/>js/prodSupport/updateAncillaryCourtCalendar.js"></script>

</head>

<body>

	<html:form action="/PerformUpdateAncillaryCalendarRecord" onsubmit="return this;">
		<BR>		
		<div>

			<h2 align="center">
				Updated Petition Number  =
				<bean:write name="ProdSupportForm" property="petitionNum" />
			</h2>
			<BR>
			
			<!-- End Error Message Area -->

			
			
			
			<table class="resultTbl" border="1" width="750" align="center">

				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">Petition
							Number</font></td>
					<td><font size="-1"><bean:write name="ProdSupportForm"
								property="petitionNum" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">Respondent
							Name</font></td>
					<td><font size="-1"><bean:write name="ProdSupportForm"
								property="respondentName" />&nbsp;</font>
					</td>
				</tr>
			
			</table>
			<p align="center"><b><i><font style="font-weight: bold;"
							color="green" size="3" face="Arial">Record Successfully Updated</font></i></b></p>	
			<h3 align="center">
				<b>Ancillary Court Details </b>
			</h3>
			<table class="resultTbl" border="1" width="800" align="center">
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Chain Number</font>
					</td>
					<td  bgcolor="white">
						<font  size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.chainNumber"/></font>
					</td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Chain Sequence Number</font>
					</td>
					<td  bgcolor="white">
						<font size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.seqNum"/></font>
					</td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Assigned Court</font>
					</td>
					<td bgcolor="white">
						<font  size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.court"/></font>
					</td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Hearing Date</font>
					</td>
					<td  bgcolor="white">
						<font  size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.courtDate"/></font>
					</td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Hearing Time</font>
					</td>
					<td  bgcolor="white">
						<font  size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.formattedCourtTime"  /></font>
					</td>
				</tr>
				
				
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Hearing Decision(Result)</font>
					</td>
					<td bgcolor="white"><font size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.courtResult"/>&nbsp;</font></td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Hearing Disposition</font>
					</td>
					<td  bgcolor="white"><font size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.disposition"/>&nbsp;</font></td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Reset Hearing Type</font>
					</td>
					<td  bgcolor="white"><font size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.resetHearingType"/>&nbsp;</font></td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Petition Number</font>
					</td>
					<td  bgcolor="white">
						<font  size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.petitionNumber"/></font>
					</td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Setting Reason</font>																										
					</td>
					<td bgcolor="white"><font size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.settingReason"/></font></td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Type Case</font>
					</td>
					<td bgcolor="white"><font size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.typeCase"/></font></td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Filing Date</font>
					</td>
					<td bgcolor="white"><font size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.filingDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Attorney Bar Number</font>
					</td>
					<td bgcolor="white"><font size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.barNum"/>&nbsp;</font></td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Attorney Connection</font>
					</td>
					<td bgcolor="white"><font size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.attorneyConnection"/>&nbsp;</font></td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Attorney Name</font>
					</td>
					<td bgcolor="white"><font size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.attorneyName"/>&nbsp;</font></td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Respondant Name</font>
					</td>
					<td bgcolor="white"><font size="-1"><bean:write name="ProdSupportForm"
								property="ancillaryCalendarRecord.respondantName" />&nbsp;</font>
					</td>
				</tr>
			</table>

		<BR>

			
			
		</div>


	</html:form>

	<table align="center" > 
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




