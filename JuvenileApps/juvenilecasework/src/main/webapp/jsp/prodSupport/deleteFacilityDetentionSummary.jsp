<!DOCTYPE HTML>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@page import="mojo.km.utilities.DateUtil"%>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/deleteFacilityDetentionSummary.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/facilityProdSupport.js"></script>
<script>
	$(document).ready(function(){
		
		var lastSeqNum = '<bean:write name="ProdSupportForm" property="isLastSeqNum"/>';
		var sequenceNum = '<bean:write name="ProdSupportForm" property="lastSeqNum"/>';
		
		if(lastSeqNum == 'true' || sequenceNum == '')
	    {
		  $("#btnBackToResults").hide();	
		}
		
	})
</script> 


</head>

<body>

<h2 align="center">Production Support - Delete Facilities Detention Record Summary</h2>
<hr>

<p align="center"><font color="green"><b>Detention Record 
<bean:write name="ProdSupportForm" property="detentionId" /> was successfully deleted.</b></font></p>
<p align="center">The following is the record affected by this change. This is for auditing purposes</p>


<hr>


<html:form action="/PerformDeleteFacilityDetention" onsubmit="return this;">

<div>	
	     

<logic:notEmpty	name="ProdSupportForm" property="facilityDetentionList">
	<logic:iterate id="facilityDetention" name="ProdSupportForm" property="facilityDetentionList">
	<p>&nbsp;</p>
	<table border="1" width="900" align="center">
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
    

	<BR>
	
</logic:iterate>
</logic:notEmpty>
	
</html:form>

<table align="center" border="0" width="500">
    <tr>
        <td colspan="2" align="center">
            <html:form method="post" action="/DeleteFacilityDetentionQuery?clr=Y" onsubmit="return this;">
                <html:submit onclick="return this;" value="Back to Query/Search"/>
            </html:form>
        </td>
    </tr>
    <tr><td colspan="2" align="center"></td></tr>
    <tr>
        <td colspan="2" align="center">
            <html:form method="post" action="/DeleteFacilityDetentionQuery?list=Y" onsubmit="return this;" >
                <html:hidden styleId='juvenileId'  name='ProdSupportForm' property='juvenileId'/>
                <html:submit onclick="return this;" value="Back to Query Results" styleId="btnBackToResults" onclick="spinner()"/>		      
            </html:form>           
        </td>
    </tr>
    <tr><td colspan="2" align="center"></td></tr>
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