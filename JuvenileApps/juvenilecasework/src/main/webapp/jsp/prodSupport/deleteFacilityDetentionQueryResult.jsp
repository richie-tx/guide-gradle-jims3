<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="mojo.km.utilities.DateUtil"%>
<%@ page import="messaging.codetable.criminal.reply.JuvenileReleasedFromResponseEvent"%>
<%@ page import="java.util.*" %>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/deleteFacilityDetentionQueryResult.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<style>
	table tr td {
		text-align: left;
	}
</style>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script>
	$(document).ready(function(){
		
		var lastSeqNum = '<bean:write name="ProdSupportForm" property="lastSeqNum"/>';		
		var sequenceNum = '<bean:write name="ProdSupportForm" property="facilitySeqNum"/>';
		var detentionId = '<bean:write name="ProdSupportForm" property="detentionId"/>';
		
		var isLastSeqNum = checkLastSeqNumber(lastSeqNum, sequenceNum);		
		
		console.log('lastSeqNumber: ' + lastSeqNum);
		console.log('sequenceNumber: ' + sequenceNum);
		console.log('isLastSeqNumber: ' + isLastSeqNum);
		
		$("#deleteRec").click(function(){
			
			if(isLastSeqNum){
				
				if (confirm('Are you sure you want to delete this facility detention record(# ' + detentionId + ') ?')){				
					spinner();
					$("#deleteDetentionForm").submit();
				} else {
					return;
				}
				
			} else {				
				
				if (confirm('Are you sure you want to delete this facility detention record( # <bean:write name="ProdSupportForm" property="detentionId"/> ) ?')){				
						spinner();
						$("#deleteDetentionForm").submit();
				} else {
					return;
				}
				
			}
			
			
			
		})
		
		function checkLastSeqNumber(lastSeqNum, seqNum){
			result = false;
			
			if(lastSeqNum === seqNum){
				result = true;
			}
			
			return result;
		}
		
	})
</script> 
<bean:define id="releaseTemp" name="ProdSupportForm" property="releaseTo" type= "java.util.ArrayList"/>


</head>

<body>

<h2 align="center">Production Support - Facilities Detention Record -- Delete</h2>
<hr>

<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><font color="red"><html:errors></html:errors></font></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>
<h3 align="center">
	<font color="black" style="font-weight: bold"><i>Please delete the following Facility Detention information </i></font>
</h3>

<html:form styleId= "deleteDetentionForm" action="/PerformDeleteFacilityDetention" onsubmit="return this;">

<div>
	
	<h2 align="center">Facility Detention Information</h2>
	     
<!-- Error Message Area -->
    <logic:notEqual name="ProdSupportForm" property="msg" value="">
	<table border="0" width="900" align="center">

	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<bean:write name="ProdSupportForm" property="msg" />
	 		</font></td>
	</tr>
	</table>
	</logic:notEqual>
<!-- End Error Message Area -->	         
<logic:notEmpty	name="ProdSupportForm" property="facilityDetentionList">
	<logic:iterate id="facilityDetention" name="ProdSupportForm" property="facilityDetentionList">
	<p>&nbsp;</p>
	<table id="facilityDetention" border="1" width="900" align="center">
	
	<tr>
		<td bgcolor="gray" width="30%"> <font color="white" face="bold" size="-1">DETENTION ID</font></td>
		<td><font size="-1" width="40%"><bean:write  name="facilityDetention" property="detentionId" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">JUVENILE NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="juvenileId" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">JUVENILE NAME</font></td>
		<td><font size="-1">
			<bean:write name="facilityDetention" property="firstName" />&nbsp;
			<bean:write name="facilityDetention" property="middleName" />&nbsp;
			<bean:write name="facilityDetention" property="lastName" />&nbsp;
			<bean:write name="facilityDetention" property="suffixName" />
			</font>
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">BOOKING REFERRAL NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="bookingReferralNum" />&nbsp;</font></td>				
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CURRENT REFERRAL NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="currentReferralNum" />&nbsp;</font></td>	
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">SEQUENCE NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="sequenceNum" />&nbsp;</font></td>
	</tr>	
	
	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CURRENT OFFENSE</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="currentOffense" />&nbsp;</font></td>
	</tr>	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">FACILITY</font></td>
		<td>
			<font size="-1">
				<bean:write  name="facilityDetention" property="facilityCode" />&nbsp;-&nbsp;
				<bean:write  name="facilityDetention" property="facilityName" />
			</font>
		</td>
	</tr>	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">FACILITY STATUS</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="facilityStatusCode" /></font></td>	
		<%-- <td><i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="facilityStatus" size="5" maxlength="5" styleId="facilityStatus"/></td> --%>		
	</tr>		
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ADMITTED DATE</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="admittedDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ADMITTED TIME</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="admittedTime" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RELEASE DATE</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="releaseDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RELEASE TIME</font></td>
		<td><font size="-1"><bean:write  name="facilityDetention" property="releaseTime"/></font></td>		
	</tr>
	</table>
    <table width="900" align="center">
        <tr>
            <td colspan="2" align="center"><br/></font></td>
        </tr>
    </table>   
   
	<table align="center"">
	
	<logic:notEmpty	name="ProdSupportForm" property="facilityDetentionList">
	<td>
	<p align="center">
	<input id="deleteRec" type="button" value="Delete Record"/>
	</p>
	</td>
	</logic:notEmpty>
	</table>
	
	</logic:iterate>
	</logic:notEmpty>
	
	<logic:empty name="ProdSupportForm" property="facilityDetentionList">
	<br>
	<table align="center" border="1" width="700">
		<tr>
		<td>
	   <h3 align="center"><font color="green"><i>No Records Returned</i></font></h3>
	   </td>
	   </tr>
	</table>
	</logic:empty>



	</div>
	
</html:form>

<html:form action="/DeleteFacilityDetentionQuery?clr=Y" onsubmit="return this;">
<table align="center"">
	<tr>
		<td>
		<html:submit value="Back to Query"/>
		</td>
	</tr>
</table>
</html:form>


</body>
</html:html>