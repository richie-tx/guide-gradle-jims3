<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 11/07/2006		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />
<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- educationalHistoryTile.jsp</title>
</head>
<bean:define id="juvenileSchoolDetails" name="juvenileSchoolHistoryForm" property="currentSchoolDetails"/>
<div class=spacer></div>
	<table class="borderTableBlue" border="0" cellpadding="2" cellspacing="0" width="98%">
		<tr>
			<td class="detailHead" valign="top">School Information</td>
		</tr>
		<tr>
	  		<td>
				<table border=0 cellpadding=2 cellspacing="1" width='100%' >
					<tr id='entryDateRow' class='hidden'>
						<td class=formDeLabel width="1%" nowrap>Entry Date</td>
						<td class=formDe colspan=3><bean:write name="juvenileSchoolDetails" property="createDate" formatKey="date.format.mmddyyyy"/></td>
					</tr>

					<tr>
						<td class=formDeLabel>School District</td>
						<td class=formDe><bean:write name="juvenileSchoolDetails" property="schoolDistrict"/></td>
					</tr>
					<tr>											
						<td class=formDeLabel>School Name</td>
						<td class=formDe><bean:write name="juvenileSchoolDetails" property="school"/></td>
					</tr>
					<tr> 
						<td class=formDeLabel width="1%" nowrap>Enrollment Date</td>
						<td class=formDe><bean:write name="juvenileSchoolDetails" property="lastAttendedDate" formatKey="date.format.mmddyyyy"/></td>
					 </tr>
					<tr> 
						<td class=formDeLabel width="1%" nowrap>Verified Date</td>
						<td class=formDe><bean:write name="juvenileSchoolDetails" property="verifiedDate" formatKey="date.format.mmddyyyy"/></td>
					 </tr>
					<tr>
						<td class=formDeLabel>Current Grade Level</td>
						<td class=formDe colspan="3"><bean:write name="juvenileSchoolDetails" property="gradeLevelDescription"/></td>
					</tr>
					<tr>
						<td class=formDeLabel width="1%" nowrap>Is the youth grade level appropriate based on his age?</td>
						<td class=formDe colspan="3"><bean:write name="juvenileSchoolDetails" property="appropriateLevelDescription"/></td>
					</tr>
					<tr>
						<td class=formDeLabel>Enrollment Status</td>
						<td class=formDe><bean:write name="juvenileSchoolDetails" property="exitTypeDescription"/></td>
					</tr>
					 
					<tr> 
						<td class=formDeLabel width="1%" nowrap>Grade Average</td>
						<td class=formDe><bean:write name="juvenileSchoolDetails" property="gradeAverage"/></td>
					</tr>
					<tr> 
						<td class=formDeLabel width="1%" nowrap>Grade Repeated Number</td>
						<td class=formDe><bean:write name="juvenileSchoolDetails" property="gradesRepeatNumber"/></td>
					</tr>
					<tr> 
						<td class=formDeLabel width="1%" nowrap>Grade Repeated</td>
						<td class=formDe><bean:write name="juvenileSchoolDetails" property="gradesRepeatedDescription"/></td>
					</tr>
					<tr> 
						<td class=formDeLabel width="1%" nowrap>Participation</td>
						<td class=formDe><bean:write name="juvenileSchoolDetails" property="participationDescription"/></td>
					</tr>
					<tr> 
						<td class=formDeLabel width="1%" nowrap>Program Attending</td>
						<td class=formDe><bean:write name="juvenileSchoolDetails" property="programAttendingDescription"/></td>
					</tr>
					<tr> 
						<td class=formDeLabel width="1%" nowrap>Rule Infraction</td>
								<td class=formDe><bean:write name="juvenileSchoolDetails" property="ruleInfractionDescription"/></td>
					 </tr>
					<tr>
						<td class=formDeLabel width="1%" nowrap>School Attendance Status</td>
						<td class=formDe><bean:write name="juvenileSchoolDetails" property="schoolAttendanceStatusDescription"/></td>
					</tr>
					<tr> 
						<td valign=top class=formDeLabel width="1%" nowrap>History of Truancy</td>
						<td class=formDe><bean:write name="juvenileSchoolDetails" property="truancyHistory"/></td>
					</tr>													 
				</table>  <%-- END SCHOOL INFO TABLE --%>
			</td>
		</tr><%-- end view only - summary/confirmation --%>
	</table>
	<div class='spacer'></div>
	<table border=0 width='98%'>
		<tr>
			<td align=center>
				<input type=button name=close value=Close onclick="javascript:window.close()">
			</td>
		</tr>	
	</table>

