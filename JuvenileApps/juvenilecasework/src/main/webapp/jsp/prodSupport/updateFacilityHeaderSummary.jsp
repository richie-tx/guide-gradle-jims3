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
<title>Juvenile Casework -/prodSupport/updateFacilityHeaderSummary.jsp</title>
</head>

<body>
	
<h2 align="center">Update Facility Header Summary</h2>
<hr>

<p align="center"><font color="green"><b>FACILITY header was successfully updated.</b></font></p>

<p align="center"><b>The following is a list of records affected by this change. This is for auditing purposes.<br> 
If this modification was made in error, please provide this list to the JIMS Data Team to restore the data.</b></p>
<hr>

<html:form action="/PerformUpdateFacilityHeader" onsubmit="return this;">
<br/>


<logic:notEmpty	name="ProdSupportForm" property="facilityHeaderList">
	<logic:iterate id="facilityHeaderList" name="ProdSupportForm" property="facilityHeaderList">
	
	<table border="1" width="900" align="center">
	
	<tr>
		<td bgcolor="gray" width="30%"><font color="white" face="bold" size="-1">HEADER ID</font></td>
		<td><font size="-1" width="37%"><bean:write  name="facilityHeaderList" property="headerId" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE ID</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="juvenileId" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">FULL NAME</font></td>
		<td><font size="-1">
			<bean:write name="facilityHeaderList" property="firstName" />&nbsp;
			<bean:write name="facilityHeaderList" property="middleName" />&nbsp;
			<bean:write name="facilityHeaderList" property="lastName" />&nbsp;
			<bean:write name="facilityHeaderList" property="suffixName" />
			</font>
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">BOOKING REFERRAL</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="bookingReferralNum"/>&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">BOOKING SUPERVISION NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="bookingSupervisionNum" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LAST SEQUENCE NUMBER</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="lastSequenceNum" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">HIGHEST SEQUENCE IN USE</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="highestSequenceNumInUse" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">HEADER FACILITY</font></td>
		<td>
			<font size="-1"><bean:write  name="facilityHeaderList" property="facilityCode" />&nbsp;-&nbsp;
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">FACILITY STATUS</font></td>
		<td>
			<font size="-1"><bean:write  name="facilityHeaderList" property="facilityStatusCode" />&nbsp;-&nbsp;
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">NEXT HEARING DATE</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="nextHearingDate"/>&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PC HEARING DATE</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="probableCauseHearingDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="createUserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="updateUser" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="createJIMS2UserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="facilityHeaderList" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
		
	</table>
	</logic:iterate>
	</logic:notEmpty>

</html:form>


<table align="center" border="0" width="500">
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