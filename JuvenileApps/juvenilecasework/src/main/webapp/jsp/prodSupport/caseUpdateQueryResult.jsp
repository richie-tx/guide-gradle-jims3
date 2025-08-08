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
<%@ page import="naming.Features" %>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/caseUpdateQueryResult</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/updateCasefileProdSupport.js"></script>
<script language="javascript">

$( document ).ready(function() {
    
    $("#updateBtn").click(function(){
    	if ( confirmUpdate() ){
    		spinner();
    		$("#updateRecordsForm").submit();
    	}
    	
    })
});

function actDateIsEmpty(){
	
	if (document.forms[0].actdate==null || document.forms[0].actdate.value=="")
		return true;
	else
		return false;
}

function createDateIsEmpty(){
	
	if (document.forms[0].createdate==null || document.forms[0].createdate.value=="")
		return true;
	else
		return false;
}

function endDateIsEmpty(){
	
	if (document.forms[0].enddate==null || document.forms[0].enddate.value=="")
		return true;
	else
		return false;
}

function supervisionCodeIsEmpty(){
	if (document.forms[0].supervisionBox.value=="")
		return true;
	else
		return false;
}

function controllingReferralIsEmpty(){
	if (document.forms[0].newControllingReferral==null||document.forms[0].newControllingReferral.value=="")
		return true;
		
	else
		return false;

}

function juvseqnumIsValid(){
	if (document.forms[0].newJuvseqnum==null||document.forms[0].newJuvseqnum.value=="")	
		return false;
	
	if (!isNumber(document.forms[0].newJuvseqnum.value))
		{
		alert('JUVSEQNUM must be numeric, and cannot be blank.');
		return false;
		}
	else 
		return true;
}

function isMaysiNeededEmpty(){
	if (document.forms[0].newMaysineeded.selectedIndex == 0)
		return true;
	else
		return false;
}

function isNumber(n) {
	  return !isNaN(parseFloat(n)) && isFinite(n);
	}

function verifyDates(){
	if (!endDateIsEmpty())
		return true;
	else if (!actDateIsEmpty())
		return true;
	else if (!supervisionCodeIsEmpty())
		return true;
	else if (!controllingReferralIsEmpty())
		return true;
	else if (juvseqnumIsValid())
		return true;
	else if (!isMaysiNeededEmpty())
		return true;
	else if (!createDateIsEmpty())
		return true;
	else
	{
	alert('You must select a new value for at least one field. To clear out text fields, use a single space.');
	return false;
	}
}

function confirmUpdate(){

	if ( verifyDates() )
	{
		if(confirm('Are you sure you want to update the casefile?')) {
			return true;	
		} else {}
			return false;
		}
	
	}


function setCurrentDate()
{
	var today = new Date();
	
	document.forms[0].actmonth.selectedIndex = today.getMonth() + 1;
	document.forms[0].actday.selectedIndex = today.getDate();
	
	for (var y=0; y<document.forms[0].actyear.options.length; y++)
	{
		if (document.forms[0].actyear.options[y].value == today.getFullYear()) {
			document.forms[0].actyear.selectedIndex = y;
			break;
		}
	}
}	

</script>

</head>

<body>

<html:form styleId="updateRecordsForm" action="/PerformUpdateCasefile" onsubmit="return this;">

<div>
	
	<h2 align="center">Results for Casefile ID = 
			<bean:write name="ProdSupportForm" property="casefileId" /></h2>
	     
<!-- Error Message Area -->
    <logic:notEqual name="ProdSupportForm" property="msg" value="">
	<table border="0" width="700" align="center">

	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<bean:write name="ProdSupportForm" property="msg" />
	 		</font></td>
	</tr>
	</table>
	</logic:notEqual>
<!-- End Error Message Area -->	     
	     
<p align="center"><b><i>Choose a new date for one or both date fields. <br>
To clear a date field, choose the BLANK option for all three drop-downs.</i></b></p>	     
	<logic:notEmpty	name="ProdSupportForm" property="casefileDet">    
	<table class="resultTbl" border="1" width="800" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileDet.supervisionNum" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileDet.juvenileNum" />&nbsp;</font></td>
		
		<td>
		<jims2:isAllowed requiredFeatures="<%=Features.JCW_PS_UPDATEJUVENILENUM%>">
		<i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="toJuvenileId" size="7" maxlength="7" styleId="juvenileNumber" />
		</jims2:isAllowed>
		</td>
		
		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASESTATUSCD</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileDet.caseStatusId" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACTIVATIONDATE</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileDet.activationDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
	  <td>
	  	<font color="red">New value:&nbsp;</font>
	  	<html:text name="ProdSupportForm" property="actdate" size="10" maxlength="10" styleId="actdate"/>
    </td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SUPRVSIONENDDATE</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileDet.supervisionEndDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	<td>
	  <font color="red">New value:&nbsp;</font>
	  <html:text name="ProdSupportForm" property="enddate" size="10" maxlength="10" styleId="enddate"/> 
    </td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SPRVSIONTYPECD</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileDet.supervisionTypeId" />&nbsp;</font></td>
		<td>  
			<html:select name="ProdSupportForm" property="supervisionBox" style="width:400px">
				<html:option value=""><bean:message key="select.generic"/> </html:option>
				<html:optionsCollection name="ProdSupportForm" property="supervisionCodes" label="description" value="code" />
			</html:select>
		</td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">OFFICER_ID</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileDet.probationOfficeId" />&nbsp;</font></td>
	</tr>

	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVSEQNUM</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileDet.sequenceNum" />&nbsp;</font></td>
		<td><i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="newJuvseqnum" size="10" maxlength="10" /></td>
	</tr>
	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISMAYSINEEDED</font></td>
			<td class='formDe'><jims2:if name="ProdSupportForm" property="casefileDet.isMAYSINeeded" value="true" op="equal">
				<jims2:then>Yes</jims2:then>
				<jims2:else>No</jims2:else>
			</jims2:if></td>		
		<td><i>Select New Value:</i>&nbsp;
		<select name="newMaysineeded" size="1">
			<option value=""></option>
            <option value="1">Yes</option>
      		<option value="0">No</option>
    	</select></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISPACTRISKNEEDED</font></td>
		<td class='formDe'>
			<jims2:if name="ProdSupportForm" property="casefileDet.riskNeed" value="true" op="equal">
				<jims2:then>Yes</jims2:then>
				<jims2:else>No</jims2:else>
			</jims2:if>
		</td>
		<td><i>Select New Value:</i>&nbsp;
			<select name="newPactRiskNeeded" size="1">
				<option value=""></option>
	            <option value="1">Yes</option>
	      		<option value="0">No</option>
	    	</select>
    	</td>	
	</tr>
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISHISPANICINDICATORNEEDED</font></td>
		<td class='formDe'>
			<jims2:if name="ProdSupportForm" property="casefileDet.hispanic" value="true" op="equal">
				<jims2:then>Yes</jims2:then>
				<jims2:else>No</jims2:else>
			</jims2:if>
		</td>
		<td><i>Select New Value:</i>&nbsp;
			<select name="newHispanicIndicatorNeeded" size="1">
				<option value=""></option>
	            <option value="1">Yes</option>
	      		<option value="0">No</option>
	    	</select>
    	</td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISSCHOOLHISTORYNEEDED</font></td>
		<td class='formDe'>
			<jims2:if name="ProdSupportForm" property="casefileDet.school" value="true" op="equal">
				<jims2:then>Yes</jims2:then>
				<jims2:else>No</jims2:else>
			</jims2:if>
		</td>
		<td><i>Select New Value:</i>&nbsp;
			<select name="newSchoolHistoryNeeded" size="1">
				<option value=""></option>
	            <option value="1">Yes</option>
	      		<option value="0">No</option>
	    	</select>
    	</td>			
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISVOPENTRYNEEDED</font></td>
		<td class='formDe'>
			<jims2:if name="ProdSupportForm" property="casefileDet.vop" value="true" op="equal">
				<jims2:then>Yes</jims2:then>
				<jims2:else>No</jims2:else>
			</jims2:if>
		</td>
		<td><i>Select New Value:</i>&nbsp;
			<select name="newVopEntryNeeded" size="1">
				<option value=""></option>
	            <option value="1">Yes</option>
	      		<option value="0">No</option>
	    	</select>
    	</td>				
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISSUBSTANCEABUSENEEDED</font></td>
		<td class='formDe'>
			<jims2:if name="ProdSupportForm" property="casefileDet.subabuse" value="true" op="equal">
				<jims2:then>Yes</jims2:then>
				<jims2:else>No</jims2:else>
			</jims2:if>
		</td>
		<td><i>Select New Value:</i>&nbsp;
			<select name="newSubstanceAbuseNeeded" size="1">
				<option value=""></option>
	            <option value="1">Yes</option>
	      		<option value="0">No</option>
	    	</select>
    	</td>					
	</tr>
	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISRISKREFRNEEDED</font></td>
		<td class='formDe'><jims2:if name="ProdSupportForm" property="casefileDet.referralRiskNeeded" value="true" op="equal">
				<jims2:then>Yes</jims2:then>
				<jims2:else>No</jims2:else>
			</jims2:if></td>
		<td>
		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISRISKINTVNEEDED</font></td>
			<td class='formDe'><jims2:if name="ProdSupportForm" property="casefileDet.interviewRiskNeeded" value="true" op="equal">
				<jims2:then>Yes</jims2:then>
				<jims2:else>No</jims2:else>
			</jims2:if></td>
		<td>
		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISRISKTESTNEEDED</font></td>
		<td class='formDe'><jims2:if name="ProdSupportForm" property="casefileDet.testingRiskNeeded" value="true" op="equal">
				<jims2:then>Yes</jims2:then>
				<jims2:else>No</jims2:else>
			</jims2:if></td>
	
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISRISKCOMMNEEDED</font></td>
			<td class='formDe'><jims2:if name="ProdSupportForm" property="casefileDet.communityRiskNeeded" value="true" op="equal">
				<jims2:then>Yes</jims2:then>
				<jims2:else>No</jims2:else>
			</jims2:if></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISRISKRESINEEDED</font></td>
		<td class='formDe'><jims2:if name="ProdSupportForm" property="casefileDet.residentialRiskNeeded" value="true" op="equal">
				<jims2:then>Yes</jims2:then>
				<jims2:else>No</jims2:else>
			</jims2:if></td>	
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISRISKPROGNEEDED</font></td>
		<td class='formDe'><jims2:if name="ProdSupportForm" property="casefileDet.progressRiskNeeded" value="true" op="equal">
				<jims2:then>Yes</jims2:then>
				<jims2:else>No</jims2:else>
			</jims2:if></td>			
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISBENASMNTNEEDED</font></td>
		<td class='formDe'><jims2:if name="ProdSupportForm" property="casefileDet.benefitsAssessmentNeeded" value="true" op="equal">
				<jims2:then>Yes</jims2:then>
				<jims2:else>No</jims2:else>
			</jims2:if></td>	
	</tr>
	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CNTROLLINGREFERRAL</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileDet.controllingReferralId" />&nbsp;</font></td>
		<td><i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="newControllingReferral" size="10" maxlength="10" /></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEPLAN_ID</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileDet.caseplanId" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PROVPROGRAM_ID</font></td>
		<td></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileDet.createUserID" />&nbsp;</font></td>
	</tr>
	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileDet.createDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>    
		<td>
			<font color="red">New value:&nbsp;</font>
			<html:text name="ProdSupportForm" property="createdate" size="10" maxlength="10" styleId="createdate"/>
    	</td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileDet.updateUser" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileDet.updateDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileDet.createJIMS2UserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileDet.updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
					
	</table>
	</logic:notEmpty>
	<BR>
	
	<table align="center"">
	
	<logic:notEmpty	name="ProdSupportForm" property="casefileDet">
	<td>
	<p align="center">
	
	<input type="button" id="updateBtn" value="Update Records"/>
	</p>
	</td>
	<td>
	<html:reset value="Reset Form Values" />
	</td>
	</logic:notEmpty>

	</table>
	
	
	<logic:empty name="ProdSupportForm" property="casefileDet">
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
	

<table align="center"">
<tr>

<td>&nbsp;</td>

</tr>
</table>
</html:form>

<html:form action="/UpdateCasefileQuery?clr=Y" onsubmit="return this;">
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