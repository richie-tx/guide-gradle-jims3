<!DOCTYPE HTML>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/removeJuvCaleventQueryResult.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script language="javascript">

function confirmDelete(){
	if(confirm('You are about to remove Juvenile # ' + <bean:write name="ProdSupportForm" property="juvenileId" /> + ' from Service Event ID ' + <bean:write name="ProdSupportForm" property="serveventId" /> +  '. Click OK to continue.')) {
		spinner();
		return true;	
	} else {
		return false;
	}
}

</script>

</head>

<html:form action="/PerformRemoveJuvCalevent" onsubmit="return this;">

<input type="hidden" name="tableId" value="" />
	
	<h2 align="center">Remove Juvenile from Calendar Event Query Results</h2>
	<br>
	<h4 align="center"><i>Juvenile <bean:write name="ProdSupportForm" property="juvenileId" /> will be <font color="red">REMOVED</font> from Service Event 
	<bean:write name="ProdSupportForm" property="serveventId" />.</i></h4>

<hr>
<table border="0" width="700">			
	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<bean:write name="ProdSupportForm" property="msg" />
			</logic:notEqual> 
			</font>
			</td>
	</tr>
</table>
<logic:notEmpty	name="ProdSupportForm" property="servevents">
<p>&nbsp;</p>
<h3 align="left">Associated Service Events</h3>
<table class="resultTbl" border="1" width="700">	
 	<tr>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				SERVICEEVENT_ID
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				CALEVENT_ID
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				EVENTMAXIMUM
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				EVENTMINIMUM 
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				EVENTSTATUSCD
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				EVENTTYPECD 
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				JVSRVPRVPROF_ID
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				JUVLOCUNIT_ID
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				SERVICE_ID
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				EVENTCOMMENTS 
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				SCHOOLDERIVEDCD 
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				INTERVIEW_ID 
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				MEMADDRESS_ID 
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				CURRENTENROLL 
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				FACILITYCD 
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				MEMEMPLOY_ID 
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				CONTACTFIRSTNAME 
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				CONTACTLASTNAME 
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				SEXOFFENDER 
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				RESTRICTOTHER 
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				WEAPONSDESCS  
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				CREATEUSER  
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				CREATEDATE  
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				UPDATEUSER  
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				UPDATEDATE  
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				CREATEJIMS2USER  
  			</font>
  		</td>
  		<td bgcolor="gray">
  			<font color="white" face="bold" size="-1">
  				UPDATEJIMS2USER  
  			</font>
  		</td>	  			  			  			  			  		
	</tr>
	<logic:iterate id="servevents" name="ProdSupportForm" property="servevents">
	 	<tr>		
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="serviceEventId" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="calendarEventId" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="maxAttendance" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="minAttendance" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="eventStatusCode" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="eventTypeCode" />&nbsp;</font>
	 		</td>		
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="probationOfficerId" />&nbsp;</font>
	 		</td>		
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="juvUnitCd" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="serviceId" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="eventComments" />&nbsp;</font>
	 		</td>		
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="schoolCd" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="interviewId" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="memberAddressId" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="currentEnrollment" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="facilityCd" />&nbsp;</font>
	 		</td>		
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="memberEmploymentId" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="contactFirstName" />&nbsp;</font>
	 		</td>		
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="contactLastName" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="sexOffenderRegistrantStr" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="restrictionsOther" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="weaponDescs" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="createUser" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="createDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="updateUser" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="updateDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font>
	 		</td>		
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="createJims2User" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="updateJims2User" />&nbsp;</font>
	 		</td>
	 	</tr>		
 	</logic:iterate>
</table>
</logic:notEmpty>

<logic:empty name="ProdSupportForm" property="servevents">
<table border="0" width="700"> 
	 <tr>
	 	<td align="center">   
	 		<i>No Result(s) Returned</i>
		</td>
	</tr>
</table>
</logic:empty>

<logic:notEmpty	name="ProdSupportForm" property="calevents">
<p>&nbsp;</p>
<h3 align="left">Associated Calendar Events</h3>
<table class="resultTbl" border="1" width="700">	
 	<tr>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			CALEVENT_ID 
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			EVENTSERIES_ID 
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			CALEVENTTYPE 
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			STARTDATETIME 
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			ENDDATETIME 
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			SUBJECT 
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			BODYTEXT  
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			LOCATION 
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			STATUS  
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			CREATEDBY  
	  		</font>
	  	</td>
	  	<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			TIMEZONE  
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			CREATEUSER  
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			CREATEDATE  
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			UPDATEUSER  
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			UPDATEDATE  
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			CREATEJIMS2USER  
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			UPDATEJIMS2USER   
	  		</font>
	  	</td>
	</tr>
	<logic:iterate id="calevents" name="ProdSupportForm" property="calevents">
	 	<tr>		
	 		<td><font size="-1">
	 			<bean:write name="calevents" property="calendarEventId" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="calevents" property="calendarSeriesId" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="calevents" property="calendarEventType" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="calevents" property="startDatetime" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="calevents" property="endDatetime" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="calevents" property="subject" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="calevents" property="bodyText" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="calevents" property="location" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="calevents" property="status" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="calevents" property="createdBy" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="calevents" property="timeZone" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="calevents" property="createUser" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="calevents" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="calevents" property="updateUser" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="calevents" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font>
	 		</td>		
	 		<td><font size="-1">
	 			<bean:write name="calevents" property="createJims2User" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="calevents" property="updateJims2User" />&nbsp;</font>
	 		</td>
	 	</tr>		
 		</logic:iterate>
</table>
</logic:notEmpty>

<logic:notEmpty	name="ProdSupportForm" property="caleventconts">
<p>&nbsp;</p>
<h3 align="left">Associated CalEventConts</h3>	
	<table class="resultTbl" border="1" width="700">
 	<tr> 		
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			CALEVENTCONT_ID
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			CALEVENT_ID
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			OFFICER_ID
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			CASEFILE_ID
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			JUVENILE_ID
	  		</font>
	  	</td>
	  	<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			PROGRAMREFERRALID
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			CREATEUSER  
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			CREATEDATE  
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			UPDATEUSER  
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			UPDATEDATE  
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			CREATEJIMS2USER  
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			UPDATEJIMS2USER   
	  		</font>
	  	</td>
	</tr>
	<logic:iterate id="caleventconts" name="ProdSupportForm" property="caleventconts">
		<logic:empty name="caleventconts" property="programReferralRespList">
			<tr>	
			 		<td><font size="-1">
			 			<bean:write name="caleventconts" property="calendarEventContextId" />&nbsp;</font>
			 		</td>
			 		 		<td><font size="-1">
			 			<bean:write name="caleventconts" property="calendarEventId" />&nbsp;</font>
			 		</td>
			 		<td><font size="-1">
			 			<bean:write name="caleventconts" property="probationOfficerId" />&nbsp;</font>
			 		</td>
			 		 		<td><font size="-1">
			 			<bean:write name="caleventconts" property="caseFileId" />&nbsp;</font>
			 		</td>
			 		<td><font size="-1">
			 			<bean:write name="caleventconts" property="juvenileId" />&nbsp;</font>
			 		</td>
			 		<td><font size="-1">
			 			
			 		</td>
			 		<td><font size="-1">
			 			<bean:write name="servevents" property="createUser" />&nbsp;</font>
			 		</td>	
			 		<td><font size="-1">
			 			<bean:write name="servevents" property="createDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font>
			 		</td>	
			 		<td><font size="-1">
			 			<bean:write name="servevents" property="updateUser" />&nbsp;</font>
			 		</td>	
			 		<td><font size="-1">
			 			<bean:write name="servevents" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font>
			 		</td>		
			 		<td><font size="-1">
			 			<bean:write name="servevents" property="createJims2User" />&nbsp;</font>
			 		</td>	
			 		<td><font size="-1">
			 			<bean:write name="servevents" property="updateJims2User" />&nbsp;</font>
			 		</td>
			 	</tr>
		</logic:empty>
		<logic:notEmpty name="caleventconts" property="programReferralRespList">
			<logic:iterate id="programReferral" name="caleventconts" property="programReferralRespList">
			 	<tr>	
			 		<td><font size="-1">
			 			<bean:write name="caleventconts" property="calendarEventContextId" />&nbsp;</font>
			 		</td>
			 		 		<td><font size="-1">
			 			<bean:write name="caleventconts" property="calendarEventId" />&nbsp;</font>
			 		</td>
			 		<td><font size="-1">
			 			<bean:write name="caleventconts" property="probationOfficerId" />&nbsp;</font>
			 		</td>
			 		 		<td><font size="-1">
			 			<bean:write name="caleventconts" property="caseFileId" />&nbsp;</font>
			 		</td>
			 		<td><font size="-1">
			 			<bean:write name="caleventconts" property="juvenileId" />&nbsp;</font>
			 		</td>
			 		<td><font size="-1">
			 			<bean:write name="programReferral" property="juvenileProgramReferralId" />&nbsp;</font>
			 		</td>
			 		<td><font size="-1">
			 			<bean:write name="servevents" property="createUser" />&nbsp;</font>
			 		</td>	
			 		<td><font size="-1">
			 			<bean:write name="servevents" property="createDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font>
			 		</td>	
			 		<td><font size="-1">
			 			<bean:write name="servevents" property="updateUser" />&nbsp;</font>
			 		</td>	
			 		<td><font size="-1">
			 			<bean:write name="servevents" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font>
			 		</td>		
			 		<td><font size="-1">
			 			<bean:write name="servevents" property="createJims2User" />&nbsp;</font>
			 		</td>	
			 		<td><font size="-1">
			 			<bean:write name="servevents" property="updateJims2User" />&nbsp;</font>
			 		</td>
			 	</tr>
		 	</logic:iterate>
	 	</logic:notEmpty>
  	</logic:iterate>
</table>
</logic:notEmpty>

<logic:notEmpty	name="ProdSupportForm" property="interviews">
<p>&nbsp;</p>
<h3 align="left">Associated Interviews</h3>	
<table class="resultTbl" border="1" width="700">	
 	<tr>
   		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			INTERVIEW_ID
	  		</font>
	  	</td>
   		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			CASEFILE_ID
	  		</font>
	  	</td>
   		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			CALEVENT_ID
	  		</font>
	  	</td>
   		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			IVIEWDATE
	  		</font>
	  	</td>
   		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			IVIEWTYPECD
	  		</font>
	  	</td>
   		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			LOCATION_ID
	  		</font>
	  	</td>
   		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			ADDRESS_ID
	  		</font>
	  	</td>
   		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			JUVLOCUNIT_ID
	  		</font>
	  	</td>
   		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			STATUSCD
	  		</font>
	  	</td>
   		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			ISCSTMADDRSVALID
	  		</font>
	  	</td>
   		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			OTHERRECSINTV
	  		</font>
	  	</td>
   		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			SUMMARYNOTES
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			CREATEUSER
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			CREATEDATE
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			UPDATEUSER
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			UPDATEDATE
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			CREATEJIMS2USER
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			UPDATEJIMS2USER
	  		</font>
	  	</td>
	</tr>
	<logic:iterate id="interviews" name="ProdSupportForm" property="interviews">
	 	<tr>	
	 		<td><font size="-1">
	 			<bean:write name="interviews" property="interviewId" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="interviews" property="casefileId" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="interviews" property="calEventId" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="interviews" property="interviewDate" formatKey="date.format.mmddyyyy" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="interviews" property="interviewTypeId" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="interviews" property="locationDescription" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="interviews" property="addressId" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="interviews" property="juvLocationUnitId" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="interviews" property="interviewStatusCd" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="interviews" property="otherInventoryRecords" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="interviews" property="customAddressValid" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="interviews" property="summaryNotes" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="createUser" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="updateUser" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font>
	 		</td>		
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="createJims2User" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="updateJims2User" />&nbsp;</font>
	 		</td>
	 	</tr>		
  	</logic:iterate>
</table>
</logic:notEmpty>
 <logic:notEmpty	name="ProdSupportForm" property="servattends">
<p>&nbsp;</p>
<h3 align="left">Associated ServAttends</h3>	
<table class="resultTbl" border="1" width="700">
 	<tr>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			SERVATTEND_ID
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			SERVEVENT_ID
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			JUVENILE_ID
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			ATTENDSTATUSCD
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			PROGRESSNOTES
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			ADDLATTENDEES
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			CREATEUSER
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			CREATEDATE
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			UPDATEUSER
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			UPDATEDATE
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			CREATEJIMS2USER
	  		</font>
	  	</td>
		<td bgcolor="gray">
	  		<font color="white" face="bold" size="-1">
	  			UPDATEJIMS2USER
	  		</font>
	  	</td>
	</tr>
	<logic:iterate id="servattends" name="ProdSupportForm" property="servattends">
	 	<tr>		
	 		<td><font size="-1">
	 			<bean:write name="servattends" property="serviceEventAttendanceId" />&nbsp;</font>
	 		</td>
	 		 <td><font size="-1">
	 			<bean:write name="servattends" property="serviceEventId" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="servattends" property="juvenileId" />&nbsp;</font>
	 		</td>
	 		 <td><font size="-1">
	 			<bean:write name="servattends" property="attendanceStatusCd" />&nbsp;</font>
	 		</td>
	  		<td><font size="-1">
	 			<bean:write name="servattends" property="progressNotes" />&nbsp;</font>
	 		</td>
	 		 <td><font size="-1">
	 			<bean:write name="servattends" property="addlAttendees" />&nbsp;</font>
	 		</td>
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="createUser" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="createDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="updateUser" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font>
	 		</td>		
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="createJims2User" />&nbsp;</font>
	 		</td>	
	 		<td><font size="-1">
	 			<bean:write name="servevents" property="updateJims2User" />&nbsp;</font>
	 		</td>
	 	</tr>		
  	</logic:iterate>
</table>
</logic:notEmpty> 
 

 
<table border="0" width="700">			
	<tr><td colspan="4">&nbsp;</td></tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	
	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<bean:write name="ProdSupportForm" property="msg" />
			</logic:notEqual> 
			</font>
			</td>
	</tr>

<logic:notEmpty	name="ProdSupportForm" property="servevents">
	<tr>
		<td>
		<p align="center">
			<input type="submit" value="Remove Youth from Event" onClick="return confirmDelete();">
		</p>
		</td>
	</tr>
</logic:notEmpty>
</table>
</html:form>
<table border="0" width="700">
	<tr>
	<td>
	<html:form action="/RemoveJuvCaleventQuery?clr=Y">
		<p align="center">
			<input type="submit" name="details" value="Back to Query"/>
		</p>
	</html:form>	
	</td>
	</tr>
</table>


</html:html>