<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>

 
<head>

<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Juvenile Casework -/prodSupport/DeleteDistrictCourtCalendarRecordSummary</title>

	
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/DeleteDistrictCourtCalendar.js"></script>

</head>

<body>

<html:form action="/PerformDeleteDistrictCourtCalendarRecord" onsubmit="return this;">

<div>
	
	<h2 align="center">Delete Results for Juvenile ID = 
			<bean:write name="ProdSupportForm" property="juvenileId" /></h2>
	     
<!-- Error Message Area -->
    
<!-- End Error Message Area -->	     

	<table border="1" width="750" align="center">
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Number</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="juvenileId" />&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Name</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="juvenileName" />&nbsp;</font>
				<elogic:if name="ProdSupportForm" property="rectype" op="equal" value="S.JUVENILE">
					<elogic:then>
						<font size="-1"> S </font>
					</elogic:then>	
				</elogic:if>		
				<elogic:if name="ProdSupportForm" property="rectype" op="equal" value="I.JUVENILE">
						<elogic:then>
						<font size="-1"> P </font>
					</elogic:then>
				</elogic:if>
				</td>
		</tr>		
		
	</table>     
<p align="center"><b><i><font style="font-weight: bold;"
							color="green" size="3" face="Arial">Record Successfully Deleted</font></i></b></p>		     
	<logic:notEmpty	name="ProdSupportForm" property="juvDistCourtRecords">
	<logic:iterate id="juvDistCourtRecords" name="ProdSupportForm" property="juvDistCourtRecords">
	<h3 align="center">District Court Details</h3>
	
	<table border="1" width="800" align="center">
	
	<tr>		
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Number</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="juvenileNumber"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court </font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="court"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court Date</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="courtDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Time</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="formattedCourtTime" />&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Number</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="referralNum"/>&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Chain Number</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="chainNumber"/>&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Sequence Number</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="seqNum"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court Result</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="courtResult"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court Disposition</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="disposition"/>&nbsp;</font></td>		
	</tr>	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Hearing Type</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="hearingType"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Reset Hearing Type</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="resetHearingType"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Petition Number</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="petitionNumber"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Petition Status</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="petitionStatus"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Allegation</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="allegation"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Filing Date</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="filingDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Amendment</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="petitionAmendment"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Amendment Date</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="petitionAmendmentDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>		
	</tr>
	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Bar Number</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="barNum"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Attorney Connection</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="attorneyConnection"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Attorney Name</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="attorneyName"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Previous Notes</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="prevNotes"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Last Changed Date</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="lcDt" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Last Changed User</font></td>
		<td><font size="-1"><bean:write name="juvDistCourtRecords" property="lcUser"/>&nbsp;</font></td>		
	</tr>
		
	</table>
	</logic:iterate>
	</logic:notEmpty> 
	<BR>

	</div>

</html:form>
<table align="center" border="0" width="500">
		
		<tr>
			<td colspan="2" align="center">
				<html:form method="post" action="/DeleteDistrictCourtCalendarRecordQuery?clr=Y" >
					<html:submit  value="Back to Query"/>
				</html:form>
			</td>
		</tr>
		<logic:greaterThan name="ProdSupportForm" property="juvcourtDocketsCount" value="1">
		<tr>
			<td colspan="2" align="center">
				<html:form method="post" action="/DeleteDistrictCourtCalendarRecordQuery" >
					<html:submit  value="Back to Query Results"/>
				</html:form>
			</td>
		</tr>
		</logic:greaterThan>
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