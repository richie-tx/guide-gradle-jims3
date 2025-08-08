<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<!--5/14/2014    r carter  changed name as part of prod support update  -->
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/updateCaleventQueryResult.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<style>
	
	#calendarEvent tr td {
		text-align: left;
	}
</style>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script language="javascript">
$(document).ready(function(){
	if(typeof  $("#newStartCalDate") != "undefined"){	
		datePickerSingle($("#newStartCalDate"),"Date entered ",false);
	}
	
	if(typeof  $("#newEndCalDate") != "undefined"){	
		datePickerSingle($("#newEndCalDate"),"Date entered ",false);
	}
}) 

function confirmUpdate(){
	
	var isTrue = confirm('Are you sure you want to update the event?');
	if(isTrue == true){
		
		return true;
	}else{
		
		return false;
	}
							
}

</script>

</head>

<html:form action="/PerformUpdateCalevent" onsubmit="return this;">

<input type="hidden" name="tableId" value="" />
	
	<h2 align="center">Update Calendar Event Query Results</h2>
	<br>

<hr>
<table width="700" align="center">
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
	<h3 align="center">Service Event <bean:write  name="ProdSupportForm" property="serveventId" /></h3>
	
	<logic:iterate id="servevents" name="ProdSupportForm" property="servevents">	
	<table id="calendarEvent" border="1" width="700" align="center">
	<tr >
	    	  
        <td align="left" bgcolor="gray"> <font color="white" face="bold" size="-1">SERVEVENT_ID</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="serviceEventId" />&nbsp;</font></td>        
    </tr>
    <tr>    
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CALEVENT_ID</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="calendarEventId" />&nbsp;</font></td>		
	</tr>
	 <tr>    
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CAL_STARTDATE</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="startDatetime" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
	    <td><i>New value:</i>&nbsp;<html:text name="ProdSupportForm" 
									property="newStartCalDate" 
									size="10" 
									maxlength="10" 
									styleId="newStartCalDate"/>
		</td>
	</tr>
	<tr>    
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CAL_ENDDATE</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="endDatetime" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>		
		<td><i>New value:</i>&nbsp;<html:text name="ProdSupportForm" 
									property="newEndCalDate" 
									size="10" 
									maxlength="10" 
									styleId="newEndCalDate"/></td>
	</tr>
	<tr>    
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CAL_STARTTIME</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="startDatetime" formatKey="time.format.HHmm"/>&nbsp;</font></td>		
		<td>
			  <i>New value:</i>&nbsp;
			  <select name="startCalTimeHours" size="1">
		      <option value="">Hours</option>
		      <option value="01">01</option>
		      <option value="02">02</option>
		      <option value="03">03</option>
		      <option value="04">04</option>
		      <option value="05">05</option>
		      <option value="06">06</option>
		      <option value="07">07</option>
		      <option value="08">08</option>
		      <option value="09">09</option>
		      <option value="10">10</option>
		      <option value="11">11</option>
		      <option value="12">12</option>
		      <option value="13">13</option>
		      <option value="14">14</option>
		      <option value="15">15</option>
		      <option value="16">16</option>
		      <option value="17">17</option>
		      <option value="18">18</option>
		      <option value="19">19</option>
		      <option value="20">20</option>
		      <option value="21">21</option>
		      <option value="22">22</option>
		      <option value="23">23</option>
		      <option value="24">24</option>
		    </select>
		   :
			<select name="startCalTimeMinutes" size="1">
		      <option value="">Minutes</option>
		      <option value="00">00</option>
		      <option value="15">15</option>
		      <option value="30">30</option>
		      <option value="45">45</option>
		    </select>
		 </td>
	</tr>
	<tr>    
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CAL_ENDTIME</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="endDatetime" formatKey="time.format.HHmm"/>&nbsp;</font></td>		
				<td>
			  <i>New value:</i>&nbsp;
			  <select name="endCalTimeHours" size="1">
		      <option value="">Hours</option>
		      <option value="01">01</option>
		      <option value="02">02</option>
		      <option value="03">03</option>
		      <option value="04">04</option>
		      <option value="05">05</option>
		      <option value="06">06</option>
		      <option value="07">07</option>
		      <option value="08">08</option>
		      <option value="09">09</option>
		      <option value="10">10</option>
		      <option value="11">11</option>
		      <option value="12">12</option>
		      <option value="13">13</option>
		      <option value="14">14</option>
		      <option value="15">15</option>
		      <option value="16">16</option>
		      <option value="17">17</option>
		      <option value="18">18</option>
		      <option value="19">19</option>
		      <option value="20">20</option>
		      <option value="21">21</option>
		      <option value="22">22</option>
		      <option value="23">23</option>
		      <option value="24">24</option>
		    </select>
		  	:
			<select name="endCalTimeMinutes" size="1">
		      <option value="">Minutes</option>
		      <option value="00">00</option>
		      <option value="15">15</option>
		      <option value="30">30</option>
		      <option value="45">45</option>
		    </select>
		 </td>
	</tr>
	<tr>    
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CAL_SESSIONLENGTH</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="eventSessionLength" />&nbsp;</font></td>		
	</tr>
    <tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">EVENTMAXIMUM</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="maxAttendance" />&nbsp;</font></td>	
		<td><i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="newEventMaximum" size="10" maxlength="10" /></td>	    	
	</tr>
    <tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">EVENTMINIMUM</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="minAttendance" />&nbsp;</font></td>		
	</tr>
    <tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">EVENTSTATUSCD</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="eventStatusCode" />&nbsp;</font></td>
	    <td>  
			<html:select name="ProdSupportForm" property="newEventStatusCd" style="width:400px">
				<html:option value="">Select a New Value</html:option>
				<html:optionsCollection name="ProdSupportForm" property="eventStatusCodes" label="description" value="code" />
			</html:select>
		</td>		
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
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">JUVLOCUNIT_ID</font></td>
	    <td><font size="-1"><bean:write  name="servevents" property="juvUnitCd" />&nbsp;</font></td>
	    <td>  
			<html:select name="ProdSupportForm" property="newJuvLocationUnitId" style="width:400px">
				<html:option value="">Select a New Value</html:option>
				<html:optionsCollection name="ProdSupportForm" property="juvLocationUnitCodes" label="locationUnitName" value="juvLocationUnitId" />
			</html:select>
		</td>		
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
		<td><font size="-1"><bean:write  name="servevents" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>		
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
	
	</table>
	</logic:iterate>	
	</logic:notEmpty> 
 
 
<table border="0" width="700" align="center">			
	<tr><td colspan="4">&nbsp;</td></tr>
	<tr><td colspan="4">&nbsp;</td></tr>	
	<logic:notEmpty	name="ProdSupportForm" property="servevents">
		<tr>
			<td>
			<p align="center">
				<input type="submit" value="Update Record" onClick="return confirmUpdate();">
			</p>
			</td>
		</tr>
	</logic:notEmpty>
</table>
</html:form>
<table border="0" width="700" align="center">
	<tr>
	<td>
	<html:form action="/UpdateCaleventQuery?clr=Y">
		<p align="center">
			<input type="submit" name="details" value="Back to Query"/>
		</p>
	</html:form>	
	</td>
	</tr>
</table>


</html:html>