<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/referralDeleteSummary.jsp</title>
</head>

<body>

	
<html:form method="post" action="/MainMenu" onsubmit="return this;">

<h2 align="center">Delete Program Referral Summary</h2>
<hr>

<p align="center"><font color="green"><b>Program Referral for Program Referral ID 
<bean:write name="ProdSupportForm" property="juvprogrefId" /> was successfully deleted.</b></font></p>


<p align="center"><b>The following is a list of records affected by this change. This is for auditing purposes.<br> 
If this delete was made in error, please provide this list to the JIMS Data Team to restore the data.</b></p>
<hr>

<logic:notEmpty	name="ProdSupportForm" property="juvprogrefs">
	
	<p>&nbsp;</p>
	<h3 align="center">Juvenile Program Referrals</h3>
	
	<table border="1" width="700" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVPROGREF_ID</font></td>

	</tr>
	
	<logic:iterate id="juvprogrefs" name="ProdSupportForm" property="juvprogrefs">
	<tr>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="juvenileProgramReferralId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>

	<!-- ProgRefComments Section -->
	
	<logic:notEmpty	name="ProdSupportForm" property="progrefcomments">
	
	<p>&nbsp;</p>
	<h3 align="center">Program Referral Comments</h3>
	
	<table border="1" width="700" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PRGREFCOMMENTS_ID</font></td>
	</tr>
	
	<logic:iterate id="progrefcomments" name="ProdSupportForm" property="progrefcomments">
	<tr>
		<td><font size="-1"><bean:write  name="progrefcomments" property="programReferralCommentId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>

	<!-- EventReferrals Section -->
	
	<logic:notEmpty	name="ProdSupportForm" property="eventreferrals">
	
	<p>&nbsp;</p>
	<h3 align="center">Event Referrals</h3>
	
	<table border="1" width="700" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">EVENTREFERRAL_ID</font></td>
	</tr>
	
	<logic:iterate id="eventreferrals" name="ProdSupportForm" property="eventreferrals">
	<tr>
		<td><font size="-1"><bean:write  name="eventreferrals" property="eventReferralId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	
	<!-- CSPROGRFASGNHIST Section -->
	
	<logic:notEmpty	name="ProdSupportForm" property="progrfasgnhists">
	
	<p>&nbsp;</p>
	<h3 align="center">Program Referral Assignment Hists</h3>
	
	<table border="1" width="700" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PROGRFASGNHIST_ID</font></td>
	</tr>
	
	<logic:iterate id="progrfasgnhists" name="ProdSupportForm" property="progrfasgnhists">
	<tr>
		<td><font size="-1"><bean:write  name="progrfasgnhists" property="programReferralAssignmentHistoryId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	
	<!-- CSSERVATTEND Section -->
	
	<logic:notEmpty	name="ProdSupportForm" property="servattends">
	
	<p>&nbsp;</p>
	<h3 align="center">Service Event Attendance</h3>
	
	<table border="1" width="700" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SERVICEEVENTATTENDANCE_ID</font></td>
	</tr>
	
	<logic:iterate id="servattends" name="ProdSupportForm" property="servattends">
	<tr>
		<td><font size="-1"><bean:write  name="servattends" property="serviceEventAttendanceId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	
	<!-- JCCALEVENTCONT Section -->
	
	<logic:notEmpty	name="ProdSupportForm" property="caleventconts">
	
	<p>&nbsp;</p>
	<h3 align="center">Calendar Event Context</h3>
	
	<table border="1" width="700" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CALENDAREVENTCONTEXT_ID</font></td>
	</tr>
	
	<logic:iterate id="caleventconts" name="ProdSupportForm" property="caleventconts">
	<tr>
		<td><font size="-1"><bean:write  name="caleventconts" property="calendarEventContextId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
		
	<table align="center" border="0" width="500">
		<tr>
		<td colspan="2" align="center">

		<html:submit onclick="return this;" value="Back to Main Menu"/>
	
		</td>
		</tr>

    </table>    
    </html:form>
</body>
</html:html>