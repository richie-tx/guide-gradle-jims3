<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>
<%-- 03/30/2016	RCarter	   New initial query jsp for facilities --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDCodeTableConstants"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@page import="mojo.km.utilities.DateUtil"%>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/updateJuvenileMasterSummary.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script> 


</head>

<body>

<h2 align="center">Production Support - Juvenile Master Update Summary</h2>
<hr>

<p align="center"><font color="green"><b>Juvenile Record 
<bean:write name="ProdSupportForm" property="juvenileId" /> was successfully updated.</b></font></p>


<p align="center"><b>The following is the record affected by this change. This is for auditing purposes.<br></p>
<hr>


<html:form action="/PerformUpdateJuvenileMaster" onsubmit="return this;">

<div>	
	     

<logic:notEmpty	name="ProdSupportForm" property="juveniles">
	<logic:iterate id="juveniles" name="ProdSupportForm" property="juveniles">
	<p>&nbsp;</p>
	<table border="1" width="900" align="center">
	
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE NUMBER</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="juvenileNum" />&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"> <font color="white" face="bold" size="-1">JUVENILE NAME</font></td>
			<td nowrap><font size="-1">
				<bean:write name="juveniles" property="firstName" />&nbsp;
				<bean:write name="juveniles" property="middleName" />&nbsp;
				<bean:write name="juveniles" property="lastName" />&nbsp;
				<bean:write name="juveniles" property="nameSuffix" />
				</font>
				
			</td>
			
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">RECORD TYPE</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="recType" />&nbsp;</font></td>		
			<%-- <td><font size="-1">
			<elogic:if name="juveniles" property="recType" op="equal" value="JUVENILE">
				<elogic:then>
					<font size="-1">ACTIVE</font>
				</elogic:then>	
			</elogic:if>		
				<elogic:if name="juveniles" property="recType" op="equal" value="I.JUVENILE">
						<elogic:then>
						<font size="-1">PURGED </font>
				</elogic:then>
			</elogic:if>
				<elogic:if name="juveniles" property="recType" op="equal" value="S.JUVENILE">
						<elogic:then>
						<font size="-1">SEALED </font>
				</elogic:then>
			</elogic:if>
			</font></td> --%>
		</tr>
		
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">DOB</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="dateOfBirth" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>			
		</tr>	
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Real DOB</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="realDOB" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>			
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">RACE</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="race" />&nbsp;</font></td>			
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">ORIGINAL RACE</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="originalRace" />&nbsp;</font></td>			
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">SEX</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="sex" />&nbsp;</font></td>
			
		</tr>
			<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">SSN</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="completeSSN" />&nbsp;</font></td>			
		</tr>
			<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">LAST REFERRAL</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="lastReferral" />&nbsp;</font></td>			
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">LEGACY MASTER STATUS</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="oldStatusId" />&nbsp;</font></td>			
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">MASTER STATUS</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="statusId" />&nbsp;</font></td>			
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Casenote 1</font></td>
			<td><font size="-1"><bean:write name="juveniles" property="caseNotePart1"/></font></td>	
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Check Out Date</font></td>
			<td><font size="-1"><bean:write name="juveniles" property="checkedOutDate" formatKey="date.format.mmddyyyy"/></font></td>		
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Check Out To</font></td>
			<td><font size="-1"><bean:write name="juveniles" property="checkedOutTo"/></font></td>	
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1"><bean:message key="prompt.detentionFacility"/></font></td>
			<td><font size="-1"><bean:write name="juveniles" property="detentionFacilityId"/></font></td>	
		</tr>
			<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1"><bean:message key="prompt.detentionStatus"/></font></td>
			<td><font size="-1"><bean:write name="juveniles" property="detentionStatusId"/></font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1"><bean:message key="prompt.purgeDate"/></font></td>
			<td><font size="-1"><bean:write name="juveniles" property="purgeDate" formatKey="date.format.mmddyyyy"/></font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1"><bean:message key="prompt.purgeFlag"/></font></td>
			<td><font size="-1"><bean:write name="juveniles" property="purgeFlag"/></font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1"><bean:message key="prompt.purgeBox"/></font></td>
			<td><font size="-1"><bean:write name="juveniles" property="purgeBoxNum" /></font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1"><bean:message key="prompt.purgeSerialNumber"/></font></td>
			<td><font size="-1"><bean:write name="juveniles" property="purgeSerNum"/></font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1"><bean:message key="prompt.sealComments"/></font></td>
			<td><font size="-1"><bean:write name="juveniles" property="sealComments"/></font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1"><bean:message key="prompt.sealedDate"/></font></td>
			<td><font size="-1"><bean:write name="juveniles" property="sealedDate" formatKey="date.format.mmddyyyy"/></font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1"><bean:message key="prompt.deathDate"/></font></td>
			<td><font size="-1"><bean:write name="juveniles" property="deathDate" formatKey="date.format.mmddyyyy"/></font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1"><bean:message key="prompt.causeOfDeath"/></font></td>
			<td><font size="-1"><bean:write name="juveniles" property="youthDeathReason"/></font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1"><bean:message key="prompt.deathVerifiedBy"/></font></td>
			<td><font size="-1"><bean:write name="juveniles" property="youthDeathVerification"/></font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1"><bean:message key="prompt.deathAge"/></font></td>
			<td><font size="-1"><bean:write name="juveniles" property="deathAge"/></font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1"><bean:message key="prompt.livewith"/></font></td>
			<td><font size="-1"><bean:write name="juveniles" property="liveWithTjjd"/></font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1"><bean:message key="prompt.juvenileExclReport"/></font></td>
			<td><font size="-1"><bean:write name="juveniles" property="juvExcluded"/></font></td>
		</tr>
		<tr>	
			<td bgcolor="gray"><font color="white" face="bold" size="-1"><bean:message key="prompt.lastChangeUser"/></font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="lcuser"/></font></td>
		</tr>
		
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1"><bean:message key="prompt.lastChangeDate"/>/<bean:message key="prompt.time"/></font></td>
			<td><font size="-1"><bean:write name="juveniles" property="lcDate" formatKey="date.format.mmddyyyy"/> <bean:write name="ProdSupportForm" property="lctime"/></font></td>
		</tr>

	</table>	    

	<BR>
	
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