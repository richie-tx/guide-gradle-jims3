<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/updateCaleventSummary.jsp</title>
</head>


<body>
	
<html:form method="post" action="/CaseAddReferralQuery" onsubmit="return this;">

<h2 align="center">Update Calendar Event Summary</h2>
<hr>

<p align="center"><font color="green"><b>ServeventID  
<bean:write name="ProdSupportForm" property="serveventId" /> was successfully updated.</b></font></p>
</html:form>

<p align="center"><b>The following is a list of records affected by this change. This is for auditing purposes.<br></b></p>
<hr>

<logic:notEmpty	name="ProdSupportForm" property="servevents">

	<logic:iterate id="servevents" name="ProdSupportForm" property="servevents">	
	<table border="1" width="800" align="center">
	<tr>
	    	  
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">SERVEVENT_ID</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="serviceEventId" />&nbsp;</font></td>        
    </tr>
    <tr>    
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CALEVENT_ID</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="calendarEventId" />&nbsp;</font></td>		
	</tr>
		<tr>
	 <logic:notEmpty name="ProdSupportForm" property="newCalEventStartDate">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CAL_STARTDATE</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="newCalEventStartDate" formatKey="datetime.format.mmddyyyyHHmmAMPM" />&nbsp;</font></td>
		<td><font color="red"><i>Updated, previous value: </i><bean:write  name="servevents" property="startDatetime" formatKey="datetime.format.mmddyyyyHHmmAMPM" /></font></td>
	 </logic:notEmpty>	 
	 <logic:empty name="ProdSupportForm" property="newCalEventStartDate">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CAL_STARTDATE</font></td>
		<td><font size="-1"><bean:write  name="servevents" property="startDatetime" formatKey="datetime.format.mmddyyyyHHmmAMPM" />&nbsp;</font></td>
	 </logic:empty>
	</tr>
		<tr>
	 <logic:notEmpty name="ProdSupportForm" property="newCalEventEndDate">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CAL_ENDDATE</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="newCalEventEndDate" formatKey="datetime.format.mmddyyyyHHmmAMPM" />&nbsp;</font></td>
		<td><font color="red"><i>Updated, previous value: </i><bean:write  name="servevents" property="endDatetime" formatKey="datetime.format.mmddyyyyHHmmAMPM" /></font></td>
	 </logic:notEmpty>	 
	 <logic:empty name="ProdSupportForm" property="newCalEventEndDate">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CAL_ENDDATE</font></td>
		<td><font size="-1"><bean:write  name="servevents" property="endDatetime" formatKey="datetime.format.mmddyyyyHHmmAMPM" />&nbsp;</font></td>
	 </logic:empty>
	</tr>
	<tr>    
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CAL_SESSIONLENGTH</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="eventSessionLength" />&nbsp;</font></td>		
	</tr>
	<tr>
	 <logic:notEmpty name="ProdSupportForm" property="newEventMaximum">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">EVENTMAXIMUM</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="newEventMaximum" />&nbsp;</font></td>
		<td><font color="red"><i>Updated, previous value: </i><bean:write  name="servevents" property="maxAttendance" /></font></td>
	 </logic:notEmpty>	 
	 <logic:empty name="ProdSupportForm" property="newEventMaximum">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">EVENTMAXIMUM</font></td>
		<td><font size="-1"><bean:write  name="servevents" property="maxAttendance" />&nbsp;</font></td>
	 </logic:empty>
	</tr>		
    <tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">EVENTMINIMUM</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="minAttendance" />&nbsp;</font></td>		
	</tr>	
	<tr>
	 <logic:notEmpty name="ProdSupportForm" property="newEventStatusCd">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">EVENTSTATUSCD</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="newEventStatusCd" />&nbsp;</font></td>
		<td><font color="red"><i>Updated, previous value: </i><bean:write  name="servevents" property="eventStatusCode" /></font></td>
	 </logic:notEmpty>	 
	 <logic:empty name="ProdSupportForm" property="newEventStatusCd">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">EVENTSTATUSCD</font></td>
		<td><font size="-1"><bean:write  name="servevents" property="eventStatusCode" />&nbsp;</font></td>
	 </logic:empty>
	</tr>
    <tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">EVENTTYPECD</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="eventTypeCode" />&nbsp;</font></td>		
	</tr>
    <tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">JVSRVPRVPROF_ID</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="instructorId" />&nbsp;</font></td>		
	</tr>
	<tr>
	 <logic:notEmpty name="ProdSupportForm" property="newJuvLocationUnitId">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVLOCUNIT_ID</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="newJuvLocationUnitId" />&nbsp;</font></td>
		<td><font color="red"><i>Updated, previous value: </i><bean:write  name="servevents" property="juvUnitCd" /></font></td>
	 </logic:notEmpty>	 
	 <logic:empty name="ProdSupportForm" property="newJuvLocationUnitId">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVLOCUNIT_ID</font></td>
		<td><font size="-1"><bean:write  name="servevents" property="juvUnitCd" />&nbsp;</font></td>
	 </logic:empty>
	</tr>
	<tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">JUVLOCUNIT_NAME</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="juvUnitName" />&nbsp;</font></td>
	</tr>
    <tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">SERVICE_ID</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="serviceId" />&nbsp;</font></td>		
	</tr>
    <tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">EVENTCOMMENTS</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="eventComments" />&nbsp;</font></td>			
	</tr>
    <tr>						
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">SCHOOLDERIVEDCD</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="schoolCd" />&nbsp;</font></td>		
	</tr>
    <tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">INTERVIEW_ID</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="interviewId" />&nbsp;</font></td>		
	</tr>
    <tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">MEMADDRESS_ID</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="memberAddressId" />&nbsp;</font></td>		
	</tr>
    <tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CURRENTENROLL</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="currentEnrollment" />&nbsp;</font></td>		
	</tr>
    <tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">FACILITYCD</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="facilityCd" />&nbsp;</font></td>		
	</tr>
    <tr>								
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">MEMEMPLOY_ID</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="memberEmploymentId" />&nbsp;</font></td>			
	</tr>
    <tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CONTACTFIRSTNAME</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="contactFirstName" />&nbsp;</font></td>		
	</tr>
    <tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CONTACTLASTNAME</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="contactLastName" />&nbsp;</font></td>		
	</tr>
    <tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">SEXOFFENDER</font></td>
	    <td>
		<logic:equal name="servevents" property="sexOffenderRegistrantStr" value="1">
			<font size="-1">True</font>
		</logic:equal>
		<logic:notEqual name="servevents" property="sexOffenderRegistrantStr" value="1">
			<font size="-1">False</font>
		</logic:notEqual>
		</td>
	</tr>
    <tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">RESTRICTOTHER</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="restrictionsOther" />&nbsp;</font></td>			
	</tr>
    <tr>											
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td><font size="-1"><bean:write  name="servevents" property="createUser" />&nbsp;</font></td>		
	</tr>
    <tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATDATE</font></td>
		<td><font size="-1"><bean:write  name="servevents" property="createDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>		
	</tr>
    <tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td><font size="-1"><bean:write  name="servevents" property="updateUser" />&nbsp;</font></td>		
	</tr>
    <tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td><font size="-1"><bean:write  name="servevents" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>		
	</tr>
    <tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="servevents" property="createJims2User" />&nbsp;</font></td>		
	</tr>
    <tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="servevents" property="updateJims2User" />&nbsp;</font></td>	
	</tr>
	
	</logic:iterate>
	
	</table>
	</logic:notEmpty> 
 

<table align="center" border="0" width="500">
	<tr>
		<td align="right">
			<html:form action="/UpdateCaleventQuery?clr=Y">
				<input type="submit" name="details" value="Back to Query"/>
			</html:form>
		</td>
		<td align="left">
		<html:form method="post" action="/MainMenu" onsubmit="return this;">
		<html:submit onclick="return this;" value="Back to Main Menu"/>
		</html:form>
		</td>
	</tr>
</table>    
    

</body>
</html:html>