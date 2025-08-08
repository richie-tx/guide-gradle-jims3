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
<title>Juvenile Casework -/prodSupport/updateClosingQueryResult.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/updateCasefileProdSupport.js"></script>
<script language="javascript">

$(document).ready(function(){
	$("#updateBtn").click(function(e){
		if ( confirmUpdate()  ) {
			spinner();
		} else {
			e.preventDefault();
		}
	})
})
function endDateIsEmpty(){
	
	if (document.forms[0].closingenddate==null || document.forms[0].closingenddate.value=="")
		return true;
	else
		return false;
}

function Trim( s )
{
	// Remove leading spaces and carriage returns
	while( (s.substring( 0,1 ) == ' ') || (s.substring( 0,1 ) == '\n') || (s.substring( 0,1 ) == '\r') )
	{ 
		s = s.substring( 1, s.length ); 
	}
	
	// Remove trailing spaces and carriage returns
	while(( s.substring( s.length -1, s.length ) == ' ' ) || 
			(s.substring( s.length -1, s.length ) == '\n') ||
			(s.substring( s.length -1, s.length ) == '\r') )
	{ 
		s = s.substring( 0, s.length -1 ); 
	}
	
	return s;
}

function confirmUpdate(){

	document.forms[0].newControllingReferral.value = Trim(document.forms[0].newControllingReferral.value);
	
	if (!endDateIsEmpty())
	{
		if(confirm('Are you sure you want to update the record?'))
			return true;	
		else
			return false;
	}
	else if (endDateIsEmpty()&&document.forms[0].outcomeBox.selectedIndex>0)
	{
		if(confirm('Are you sure you want to update the record?'))
			return true;	
		else
			return false;
	}
	else if (endDateIsEmpty()&&document.forms[0].outcomeBox.selectedIndex==0
	&&document.forms[0].newControllingReferral.value!="")
	{
		if(confirm('Are you sure you want to update the record?'))
			return true;	
		else
			return false; 
	}
	else if (document.forms[0].outcomeDescBox.selectedIndex>0&&
			document.form[0].outcomeDescBox.selectedIndex.value!=999)
			{
				if(confirm('Are you sure you want to update the record?'))
					return true;	
				else
					return false; 
			}
	else	
	{
		alert('You must select a new value for at least one column.');
		return false;
	}
	
	
}

function resetMenus(){

	document.forms[0].outcomeBox.selectedIndex = 0;
	document.forms[0].outcomeDescBox.selectedIndex = 0;
	document.forms[0].newControllingReferral.value="";
	document.getElementById('newRecordCLM').value = "";
	document.getElementById('newJuvLocationUnitId').value = "";
}

</script>

</head>

<body onLoad="resetMenus()">

<html:form action="/PerformUpdateClosing" onsubmit="return this;">

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
	     

<logic:notEmpty	name="ProdSupportForm" property="casefileClosing">
	
	<h3 align="center">Closing Details</h3>
	
	<table class="resultTbl" border="1" width="800" align="center">
	
			<tr>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">CASFILECLOSNG_ID</font></td>
				<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileClosing.casefileClosingInfoId" />&nbsp;</font></td>
			</tr>
			<tr>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
				<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileId" />&nbsp;</font></td>
			</tr>
				<tr>	
				<td bgcolor="gray"><font color="white" face="bold" size="-1">SUPERVISIONOUTCOME</font></td>
				<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileClosing.supervisionOutcomeId" />&nbsp;</font></td>
				<td>  
					<html:select name="ProdSupportForm" property="outcomeBox" style="width:400px">
						<html:option value="">Select a New Value</html:option>
						<html:optionsCollection name="ProdSupportForm" property="outcomeCodes" label="description" value="code" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">CNTROLLINGREFERRAL</font></td>
				<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileClosing.controllingReferralId" />&nbsp;</font></td>
			</tr>
			<tr>	
				<td bgcolor="gray"><font color="white" face="bold" size="-1">CASFILECLOSNGSTATS</font></td>
				<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileClosing.casefileClosingStatus" />&nbsp;</font></td>
			</tr>
			<tr>	
				<td bgcolor="gray"><font color="white" face="bold" size="-1">FACLTYRLESEREASON</font></td>
				<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileClosing.facilityReleaseReasonId" />&nbsp;</font></td>
			</tr>
			<tr>	
				<td bgcolor="gray"><font color="white" face="bold" size="-1">PERMANENCYPLAN</font></td>
				<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileClosing.permanencyPlanId" />&nbsp;</font></td>
			</tr>
			<tr>	
				<td bgcolor="gray"><font color="white" face="bold" size="-1">FACILITY</font></td>
				<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileClosing.facilityId" />&nbsp;</font></td>
			</tr>
				<tr>	
				<td bgcolor="gray"><font color="white" face="bold" size="-1">LEVELOFCARE</font></td>
				<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileClosing.levelOfCareId" />&nbsp;</font></td>
			</tr>
				<tr>	
				<td bgcolor="gray"><font color="white" face="bold" size="-1">PETITIONNUMBER</font></td>
				<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileClosing.petitionNumber" />&nbsp;</font></td>
			</tr>
				<tr>	
				<td bgcolor="gray"><font color="white" face="bold" size="-1">EXITPLANTEMPLTLCTN</font></td>
				<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileClosing.exitPlanTemplateLocation" />&nbsp;</font></td>
			</tr>
			<tr>	
				<td bgcolor="gray"><font color="white" face="bold" size="-1">SUPRVSIONENDDATE</font></td>
				<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileClosing.supervisionEndDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
				<td><i>Select a new value:</i>&nbsp;
			  	<html:text name="ProdSupportForm" property="closingEndDate" size="10" maxlength="10" styleId="closingenddate"/>		  
		    </td>
			</tr>
			<tr>	
				<td bgcolor="gray"><font color="white" face="bold" size="-1">EXPECTEDRELESEDATE</font></td>
				<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileClosing.expectedReleaseDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
			</tr>
			<tr>	
				<td bgcolor="gray"><font color="white" face="bold" size="-1">ISCLOSNGPKTGEN</font></td>
				<td>
				<logic:equal name="ProdSupportForm" property="casefileClosing.closingPktGenerated" value="1">
					<font size="-1">True</font>
				</logic:equal>
				<logic:notEqual name="ProdSupportForm" property="casefileClosing.closingPktGenerated" value="1">
					<font size="-1">False</font>
				</logic:notEqual>
				</td>
			</tr>
			<tr>	
				<td bgcolor="gray"><font color="white" face="bold" size="-1">ISCLOSNGLTRGEN</font></td>
				<td>
				<logic:equal name="ProdSupportForm" property="casefileClosing.closingLetterGenerated" value="1">
					<font size="-1">True</font>
				</logic:equal>
				<logic:notEqual name="ProdSupportForm" property="casefileClosing.closingLetterGenerated" value="1">
					<font size="-1">False</font>
				</logic:notEqual>
				</td>
			</tr>
			<tr>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">SPRVNOUTCOMEDESCCD</font></td>
				<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileClosing.supervisionOutcomeDescriptionId" />&nbsp;</font></td>
				<td>  
					<html:select name="ProdSupportForm" property="outcomeDescBox" style="width:250px">
						<html:option value="">Select a New Value</html:option>
						<html:option value="999">NONE (NO VALUE)</html:option>
						<html:optionsCollection name="ProdSupportForm" property="outcomeDescCodes" label="description" value="code" />
					</html:select>
				</td>
			</tr>
			</tr>
				<tr>	
				<td bgcolor="gray"><font color="white" face="bold" size="-1">RECORDCLM</font></td>
				<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileClosing.recordCLM" />&nbsp;</font></td>
				<td><i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="newRecordCLM" styleId="newRecordCLM" size="10" maxlength="10" /></td>
			</tr>
			</tr>
				<tr>	
				<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVLOCUNIT_ID</font></td>
				<td><font size="-1"><bean:write name="ProdSupportForm" property="casefileClosing.juvLocUnitId" />&nbsp;</font></td>
				<td><i>Enter a new value:</i>&nbsp;<html:text name="ProdSupportForm" property="newJuvLocationUnitId" styleId="newJuvLocationUnitId" size="10" maxlength="10" /></td>				
			</tr>
			<tr>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
				<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileClosing.createdBy" />&nbsp;</font></td>
			</tr>
			<tr>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATDATE</font></td>
				<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileClosing.createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
			</tr>
			<tr>
				<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
				<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileClosing.updateUser" />&nbsp;</font></td>
			</tr>
			<tr>
				<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
				<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileClosing.updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
			</tr>
			<tr>
				<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
				<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileClosing.createJIMS2UserID" />&nbsp;</font></td>
			</tr>
			<tr>
				<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
				<td><font size="-1"><bean:write  name="ProdSupportForm" property="casefileClosing.updateJIMS2UserID" />&nbsp;</font></td>
			</tr>
	</table>

</logic:notEmpty>	

<html:hidden styleId="E1" name="ProdSupportForm" property="newRecordCLM"/>
<html:hidden styleId="E2" name="ProdSupportForm" property="newJuvLocationUnitId"/>
<logic:empty name="ProdSupportForm" property="casefileClosing">
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

<logic:notEmpty	name="ProdSupportForm" property="casefileClosing">
	<td>
	<p align="center">
	<html:submit styleId="updateBtn" value="Update Records"/>
	</p>
	</td>
	<td>
	<html:reset value="Reset Form Values" onclick="resetMenus();" />
	</td>
</logic:notEmpty>
</tr>
</table>
</html:form>

<html:form action="/UpdateClosingQuery?clr=Y" onsubmit="return this;">
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