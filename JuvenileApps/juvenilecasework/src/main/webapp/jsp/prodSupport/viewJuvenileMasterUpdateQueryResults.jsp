<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDCodeTableConstants"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/viewJuvenileMasterUpdateQueryResult.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/updateMasterProdSupport.js"></script>

<script>


</script>

</head>

<html:form action="/PerformUpdateJuvenileMaster" onsubmit="return this;">

<h2 align="center">Juvenile Master Update Results</h2>
<br>
<hr>

<logic:notEmpty	name="ProdSupportForm" property="juveniles">
<p>&nbsp;</p>

	<logic:iterate id="juveniles" name="ProdSupportForm" property="juveniles">
 	<table class="resultTbl" border="1" width="900" align="center">
	
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
			<td class="">
				<table border='0' cellspacing='1'>
					<tr>
						<td class="" ><i>Enter a new value:</i></td>
					</tr>
					<tr>
						<td class='formDeLabel' colspan="2"><bean:message key="prompt.last" /></td>
					</tr>
					<tr>
						<td class='formDe' colspan="2"><html:text property="juvenileLName" size="30" maxlength="75" styleId="lastName"/></td>
					</tr>
					<tr>
						<td class='formDeLabel'><bean:message key="prompt.first" /></td>
						<td class='formDeLabel'><bean:message key="prompt.middle" /></td>
						<td class='formDeLabel' colspan='2'><bean:message key="prompt.suffix" /></td>
					</tr>
					<tr>
						<td class='formDe'><html:text property="juvenileFName" size="25" maxlength="50" styleId="firstName"/></td>
						<td class='formDe'><html:text property="juvenileMName" size="25" maxlength="50" /></td>
						<td class='formDe' colspan='3'><html:text property="juvenileNameSuffix" size="3" maxlength="3" /></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">RECORD TYPE</font></td>
			<td nowrap><font size="-1"><bean:write name="juveniles" property="recType" /></font></td>
			<td class="" >
				<i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="rectype" size="10" maxlength="10" styleId="recType"/></td>
		
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
			</font></td> --%> <!-- commented for US 189272 --> 
		</tr>
		
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">DOB</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="dateOfBirth" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
			<td class="" >
				<i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="masterDOB" size="10" maxlength="10" styleId="masterDOB"/></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Real DOB</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="realDOB" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
			<td class="" >
				<i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="realDOB" size="10" maxlength="10" styleId="realDateOfBirth"/>
			</td>
		</tr>	
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">RACE</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="race" />&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">ORIGINAL RACE</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="originalRace" />&nbsp;</font></td>
			<td class="" >
					<i>Select a new value:</i>&nbsp;
				<html:select name="ProdSupportForm" property="originalRaceId"  styleId="race">
           				<html:option key="select.generic" value="" />
					<html:optionsCollection property="originalRaceList" value="code" label="description" />
           			</html:select></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">SEX</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="sex" />&nbsp;</font></td>
			<td class="" >
				<i>Select a new value:</i>&nbsp;
				<html:select name="ProdSupportForm" property="sexId" styleId="sex">
           			<html:option value="">
						<bean:message key="select.generic" />
					</html:option>
					<html:optionsCollection name="ProdSupportForm" property="sexList" value="code" label="description" />
           			</html:select>
			</td>
		</tr>
			<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">SSN</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="completeSSN" />&nbsp;</font></td>
			<td class="" >
				<i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="newSSN.SSN1" styleId="SSN1" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/>- 
				<html:text name="ProdSupportForm" property="newSSN.SSN2" styleId="SSN2" size="2" maxlength="2" onkeyup="return autoTab(this, 2);"/>- 
				<html:text name="ProdSupportForm" property="newSSN.SSN3" styleId="SSN3" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/> &nbsp;</td>
		</tr>
			<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">LAST REFERRAL</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="lastReferral" />&nbsp;</font></td>
			<td class="" >
				<i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="lastReferral" size="4" maxlength="4" styleId="lastReferral"/></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">LEGACY MASTER STATUS</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="oldStatusId" />&nbsp;</font></td>
			
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">MASTER STATUS</font></td>
			<td><font size="-1"><bean:write  name="juveniles" property="statusId" />&nbsp;</font></td>
			<td class="" >
				<html:select name="ProdSupportForm" property="statusId" style="width:300px" styleId="statusId">
					<html:option value="">Select a New Value</html:option>
					<html:optionsCollection name="ProdSupportForm" property="masterStatusCodes" label="codeDesc" value="code" />
				</html:select>	
			</td>
		</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASENOTE 1</font></td>
		<td><font size="-1"><bean:write name="juveniles" property="caseNotePart1"/></font></td>
		<td class="" >
				<i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="caseNotePart1" size="30" maxlength="55" styleId="caseNotePart1"/></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CHECK OUT DATE</font></td>
		<td><font size="-1"><bean:write name="juveniles" property="checkedOutDate" formatKey="date.format.mmddyyyy"/></font></td>
		<td class="" >
				<i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="checkedOutDate" size="10" maxlength="10" styleId="checkedOutDate"/></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CHECK OUT TO</font></td>
		<td><font size="-1"><bean:write name="juveniles" property="checkedOutTo"/></font></td>
		<td class="" >
				<i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="checkedOutTo" size="5" maxlength="5" styleId="checkedOutTo"/></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1" style="text-transform:uppercase"><bean:message key="prompt.detentionFacility"/></font></td>
		<td><font size="-1"><bean:write name="juveniles" property="detentionFacilityId"/></font></td>
		<td class="" >
			<html:select name="ProdSupportForm" property="detentionFacilityId" style="width:400px" styleId="detentionFacilityId">
				<html:option value="">Select a New Value</html:option>
				<html:optionsCollection name="ProdSupportForm" property="activeFacilitiesList" label="descriptionWithCode" value="code" />
			</html:select>		
		</td>
	</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1" style="text-transform:uppercase"><bean:message key="prompt.detentionStatus"/></font></td>
		<td><font size="-1"><bean:write name="juveniles" property="detentionStatusId"/></font></td>
		<td class="" >
		<html:select name="ProdSupportForm" property="detentionStatusId" style="width:400px" styleId="detentionStatusId">
			<html:option value="">Select a New Value</html:option>
				<html:optionsCollection name="ProdSupportForm" property="facilityStatusList" label="description" value="code" />
			</html:select>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1" style="text-transform:uppercase"><bean:message key="prompt.purgeDate"/></font></td>
		<td><font size="-1"><bean:write name="juveniles" property="purgeDate" formatKey="date.format.mmddyyyy"/></font></td>
		<td class="" >
				<i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="purgeDate" size="10" maxlength="10" styleId="purgeDate"/></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1" style="text-transform:uppercase"><bean:message key="prompt.purgeFlag"/></font></td>
		<td><font size="-1"><bean:write name="juveniles" property="purgeFlag"/></font></td>
		<td class="" >
				<i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="purgeFlag" size="1" maxlength="1" styleId="purgeFlag"/></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1" style="text-transform:uppercase"><bean:message key="prompt.purgeBox"/></font></td>
		<td><font size="-1"><bean:write name="juveniles" property="purgeBoxNum" /></font></td>
		<td class="" >
				<i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="purgeBoxNum" size="3" maxlength="3" styleId="purgeBoxNum"/></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1" style="text-transform:uppercase"><bean:message key="prompt.purgeSerialNumber"/></font></td>
		<td><font size="-1"><bean:write name="juveniles" property="purgeSerNum"/></font></td>
		<td class="" >
				<i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="purgeSerNum" size="3" maxlength="4" styleId="purgeSerNum"/></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1" style="text-transform:uppercase"><bean:message key="prompt.sealComments"/></font></td>
		<td><font size="-1"><bean:write name="juveniles" property="sealComments"/></font></td>
		<td class="" >
				<i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="sealComments" size="50" maxlength="50" styleId="sealComments"/></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1" style="text-transform:uppercase"><bean:message key="prompt.sealedDate"/></font></td>
		<td><font size="-1"><bean:write name="juveniles" property="sealedDate" formatKey="date.format.mmddyyyy"/></font></td>
		<td class="" >
				<i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="sealedDate" size="10" maxlength="10" styleId="sealedDate"/></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1" style="text-transform:uppercase"><bean:message key="prompt.deathDate"/></font></td>
		<td><font size="-1"><bean:write name="juveniles" property="deathDate" formatKey="date.format.mmddyyyy"/></font></td>
		<td class="" >
				<i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="dateOfDeath" size="10" maxlength="10" styleId="dateOfDeath"/></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1" style="text-transform:uppercase"><bean:message key="prompt.causeOfDeath"/></font></td>
		<td><font size="-1"><bean:write name="juveniles" property="youthDeathReason"/></font></td>
		<td class="" >
				<html:select name="ProdSupportForm" property="causeOfDeath" style="width:400px" styleId="causeOfDeathId">
					<html:option value="">Please Select</html:option>
					<html:optionsCollection name="ProdSupportForm" property="causesOfDeath" value="code" label="description" />
				</html:select>
		</td>
	</tr>
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1" style="text-transform:uppercase"><bean:message key="prompt.deathVerifiedBy"/></font></td>
		<td><font size="-1"><bean:write name="juveniles" property="youthDeathVerification" /></font></td>
		<td class="" >
				<html:select name="ProdSupportForm" property="deathVerification" style="width:400px" styleId="deathVerificationId">
					<html:option value="">Please Select</html:option>
					<html:optionsCollection name="ProdSupportForm" property="deathVerficationCodes" value="code" label="description" />
				</html:select>
		</td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1" style="text-transform:uppercase"><bean:message key="prompt.deathAge"/></font></td>
		<td><font size="-1"><bean:write name="juveniles" property="deathAge" /></font></td>
		<td class="" >
				<i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="ageAtDeath" size="4" maxlength="2" styleId="ageAtDeath"/></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1" style="text-transform:uppercase"><bean:message key="prompt.livewith"/></font></td>
		<td><font size="-1"><bean:write name="juveniles" property="liveWithTjjd" /></font></td>
		<td class="" >
				<i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="livewith" size="10" maxlength="10" styleId="livewith"/></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1" style="text-transform:uppercase"> <bean:message key="prompt.juvenileExclReport"/></font></td>
		<td><font size="-1"><bean:write name="juveniles" property="juvExcluded" /></font></td>
		<td class="" >
				<i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="juvExcluded" size="1" maxlength="1" styleId="juvExcluded"/></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1" style="text-transform:uppercase"><bean:message key="prompt.lastChangeUser"/></font></td>
		<td><font size="-1"><bean:write  name="juveniles" property="lcuser"/></font></td>
	</tr>
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1" style="text-transform:uppercase"><bean:message key="prompt.lastChangeDate"/>/<bean:message key="prompt.time"/></font></td>
		<td><font size="-1"><bean:write name="juveniles" property="lcDate" formatKey="date.format.mmddyyyy"/> <logic:notEmpty name="ProdSupportForm" property="lctime"><bean:write name="ProdSupportForm" property="lctime"/></logic:notEmpty></font></td>
	</tr>

		<br /><br />
		
	</table>
	
  	</logic:iterate>
</logic:notEmpty>
<br />
	
<logic:notEmpty	name="ProdSupportForm" property="juveniles">
	<table align="center" border="0" width="700">
		<tr>
			<td align="center">
				<html:submit value="Update Record" styleId="updateRec"/>
			<html:reset value="Reset Form Values" onclick="resetDates();" />
			</td>
	</tr>
	</table>
</logic:notEmpty>
	     
<table border="0" width="700">			
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
</table>
</html:form>


<html:form action="/UpdateJuvenileMasterQuery?clr=Y" onsubmit="return this;">
<table align="center"">
	<tr>
		<td>
		<html:submit value="Back to Query" onclick="spinner();" />
		</td>
	</tr>
</table>
</html:form>

</html:html>