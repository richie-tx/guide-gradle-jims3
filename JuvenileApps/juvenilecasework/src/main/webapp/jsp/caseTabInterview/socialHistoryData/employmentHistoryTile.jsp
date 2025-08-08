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
<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- employmentHistoryTile.jsp</title>
</head>

<bean:define id="jobDetails" name="juvenileJobForm" property="currentJobDetails" />
<div class='spacer'></div>
	<table width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
		<tr>
			<td valign=top class=detailHead>Juvenile Employment Information</td>
		</tr>
		<tr>
			<td align=center>
				<table border=0 cellpadding=4 cellspacing=1 width='100%'>
					<tr id='entryDateRow' class='hidden'>
						<td class=formDeLabel width="1%" nowrap>Entry Date</td>
						<td class=formDe colspan=3><bean:write name="jobDetails" property="entryDate" formatKey="date.format.mmddyyyy" /></td>
					</tr>

					<tr>
						<td class=formDeLabel width="1%" nowrap>Employment Status</td>
						<td class=formDe colspan=3><bean:write name="jobDetails" property="employmentStatus" /></td>
					</tr>
					<tr>	
						<td class=formDeLabel>Place Employed</td>
						<td class=formDe colspan=3><bean:write name="jobDetails" property="employmentPlace" /></td>
					</tr>
					<tr>
						<td class=formDeLabel>Salary</td>
						<td class=formDe><bean:message key="currency.prefix"/><bean:write name="jobDetails" property="salary" formatKey="currency.format"/></td>
						<td class=formDeLabel nowrap>Salary Rate</td>
						<td class=formDe><bean:write name="jobDetails" property="salaryRate" /></td>
					</tr>
					<tr>
						<td class=formDeLabel>Supervisor Name</td>
						<td class=formDe colspan=3><bean:write name="jobDetails" property="supervisorLastName" />, <bean:write name="jobDetails" property="supervisorFirstName" /> <bean:write name="jobDetails" property="supervisorMiddleName" /></td>
					</tr>
					<tr>  
						<td class=formDeLabel width="1%" nowrap>Supervisor Family Member #</td>
						<td class=formDe colspan=3><bean:write name="jobDetails" property="supervisorFamilyNum" /></td>
					</tr>
					<tr>
						<td class=formDeLabel>Work Hours</td>
						<td class=formDe colspan=3><bean:write name="jobDetails" property="workHours" /></td>
					</tr>
					<tr>
						<td class=formDeLabel>Job Description</td>
						<td class=formDe colspan=3><bean:write name="jobDetails" property="jobDescription" /></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<div class=spacer></div>
	<table border=0 width='98%'>
		<tr>
			<td align=center>
				<input type=button name=close value=Close onclick="javascript:window.close()">
			</td>
		</tr>	
	</table>
