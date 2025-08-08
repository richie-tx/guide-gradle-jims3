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
<title>Juvenile Casework -/prodSupport/updateMsReferralSummary</title>
<style>
	 .message {
	 	text-align: center;
	 	color: green;
	 }
	 
</style>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script>
	$(document).ready(function(){
		
		displayMessage("juvenileNum", '<bean:write name="ProdSupportForm" property="originalJuvprogref.juvenileNum"/>', '<bean:write name="ProdSupportForm" property="juvprogref.juvenileNum" />' );
		displayMessage("referralNum", '<bean:write name="ProdSupportForm" property="originalJuvprogref.referralNum"/>', '<bean:write name="ProdSupportForm" property="juvprogref.referralNum" />' );
		displayMessage("referralDate", '<bean:write name="ProdSupportForm" property="originalJuvprogref.referralDate"/>', '<bean:write name="ProdSupportForm" property="juvprogref.referralDate" />' );
		displayMessage("referralSource", '<bean:write name="ProdSupportForm" property="originalJuvprogref.referralSource"/>', '<bean:write name="ProdSupportForm" property="juvprogref.referralSource" />' );
		displayMessage("referralOfficer", '<bean:write name="ProdSupportForm" property="originalJuvprogref.referralOfficer"/>', '<bean:write name="ProdSupportForm" property="juvprogref.referralOfficer" />' );
		displayMessage("intakeDecision", '<bean:write name="ProdSupportForm" property="originalJuvprogref.intakeDecision"/>', '<bean:write name="ProdSupportForm" property="juvprogref.intakeDecision" />' );
		displayMessage("closeDate", '<bean:write name="ProdSupportForm" property="originalJuvprogref.closeDate"/>', '<bean:write name="ProdSupportForm" property="juvprogref.closeDate" />' );
		displayMessage("intakeDate", '<bean:write name="ProdSupportForm" property="originalJuvprogref.intakeDate"/>', '<bean:write name="ProdSupportForm" property="juvprogref.intakeDate" />' );
		displayMessage("referralTypeInd", '<bean:write name="ProdSupportForm" property="originalJuvprogref.referralTypeInd"/>', '<bean:write name="ProdSupportForm" property="juvprogref.referralTypeInd" />' );
		displayMessage("ctAssignJPOId", '<bean:write name="ProdSupportForm" property="originalJuvprogref.ctAssignJPOId"/>', '<bean:write name="ProdSupportForm" property="juvprogref.ctAssignJPOId" />' );
		displayMessage("probationStartDate", '<bean:write name="ProdSupportForm" property="originalJuvprogref.probationStartDate"/>', '<bean:write name="ProdSupportForm" property="juvprogref.probationStartDate" />' );
		displayMessage("probationEndDate", '<bean:write name="ProdSupportForm" property="originalJuvprogref.probationEndDate"/>', '<bean:write name="ProdSupportForm" property="juvprogref.probationEndDate" />' );
		displayMessage("courtResult", '<bean:write name="ProdSupportForm" property="originalJuvprogref.courtResult"/>', '<bean:write name="ProdSupportForm" property="juvprogref.courtResult" />' );
		displayMessage("courtDisposition", '<bean:write name="ProdSupportForm" property="originalJuvprogref.courtDisposition"/>', '<bean:write name="ProdSupportForm" property="juvprogref.courtDisposition" />' );
		displayMessage("dispositionDate", '<bean:write name="ProdSupportForm" property="originalJuvprogref.dispositionDate"/>', '<bean:write name="ProdSupportForm" property="juvprogref.dispositionDate" />' );
		displayMessage("pdaDate", '<bean:write name="ProdSupportForm" property="originalJuvprogref.pdaDate"/>', '<bean:write name="ProdSupportForm" property="juvprogref.pdaDate" />' );
		displayMessage("courtId", '<bean:write name="ProdSupportForm" property="originalJuvprogref.courtId"/>', '<bean:write name="ProdSupportForm" property="juvprogref.courtId" />' );
		displayMessage("piaStatus", '<bean:write name="ProdSupportForm" property="originalJuvprogref.piaStatus"/>', '<bean:write name="ProdSupportForm" property="juvprogref.piaStatus" />' );
		displayMessage("violationProbation", '<bean:write name="ProdSupportForm" property="originalJuvprogref.violationProbation"/>', '<bean:write name="ProdSupportForm" property="juvprogref.violationProbation" />' );
		displayMessage("daLogNum", '<bean:write name="ProdSupportForm" property="originalJuvprogref.daLogNum"/>', '<bean:write name="ProdSupportForm" property="juvprogref.daLogNum" />' );
		displayMessage("transNum", '<bean:write name="ProdSupportForm" property="originalJuvprogref.transNum"/>', '<bean:write name="ProdSupportForm" property="juvprogref.transNum" />' );
		displayMessage("tjjdDate", '<bean:write name="ProdSupportForm" property="originalJuvprogref.TJJDreferralDate"/>', '<bean:write name="ProdSupportForm" property="juvprogref.TJJDreferralDate" />' );
		displayMessage("countyrefd", '<bean:write name="ProdSupportForm" property="originalJuvprogref.countyREFD"/>', '<bean:write name="ProdSupportForm" property="juvprogref.countyREFD" />' );
		displayMessage("jpoId", '<bean:write name="ProdSupportForm" property="originalJuvprogref.jpoId"/>', '<bean:write name="ProdSupportForm" property="juvprogref.jpoId" />' );
		displayMessage("offenseTotal", '<bean:write name="ProdSupportForm" property="originalJuvprogref.offenseTotal"/>', '<bean:write name="ProdSupportForm" property="juvprogref.offenseTotal" />' );
		displayMessage("probJpoId", '<bean:write name="ProdSupportForm" property="originalJuvprogref.probationJpoId"/>', '<bean:write name="ProdSupportForm" property="juvprogref.probationJpoId" />' );
		displayMessage("decisionType", '<bean:write name="ProdSupportForm" property="originalJuvprogref.decisionType"/>', '<bean:write name="ProdSupportForm" property="juvprogref.decisionType" />' );
	})
	
	
	function displayMessage(id, originalValue, updatedValue){
		if (originalValue != updatedValue){
			$("#"+id).append("<td class='message'>Updated from previous value, " + originalValue + "</td>");
		}
	}
</script>

</head>

<body>

<html:form action="/PerformUpdateMsReferral" onsubmit="return this;">

<div>
	
	<h2 align="center">Results for Juvenile ID = 
			<bean:write name="ProdSupportForm" property="juvenileId" /></h2>
	     
<!-- Error Message Area -->
    <logic:notEqual name="ProdSupportForm" property="msg" value="">
	<table border="0" width="700" align="center">

	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#008000" size="3" face="Arial"> 
			<bean:write name="ProdSupportForm" property="msg" />
	 		</font></td>
	</tr>
	</table>
	</logic:notEqual>
<!-- End Error Message Area -->	     

	<table border="1" width="750" align="center">

		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Name</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="juvenileName" />&nbsp; <elogic:if name="ProdSupportForm" property="rectype" op="equal" value="S.JUVENILE">
					<elogic:then>
						<font size="-1"> S </font>
					</elogic:then>	
				</elogic:if>		
				<elogic:if name="ProdSupportForm" property="rectype" op="equal" value="I.JUVENILE">
						<elogic:then>
						<font size="-1"> P </font>
					</elogic:then>
				</elogic:if>
		   </td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile DOB</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="birthDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">SSN</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="juvenileSsn" />&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Number</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="juvenileId" />&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Master Status</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="statusId" />&nbsp;</font></td>
		</tr>
	</table> 
	</br >   
		<p align="center"><font color="green" face="bold"><i>Record Successfully Updated</i></font></p>	 
		   
	<logic:notEmpty	name="ProdSupportForm" property="juvprogref">	
	<h3 align="center">Referral Details</h3>
	
	<table border="1" width="800" align="center">
	
	<tr id="juvenileNum">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Number</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="juvprogref.juvenileNum"/></font></td>
	</tr>
	<tr id="referralNum">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral No.</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.referralNum"/></font></td>
	</tr>
	<tr id="referralDate">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Date</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.referralDate"/></font></td>
	</tr>
	<tr id="referralSource">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Source</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.referralSource"/></font></td>
	</tr>
	<tr id="referralOfficer">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Officer</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.referralOfficer"/></font></td>
	</tr>
	<tr id="intakeDecision">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Decision</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.intakeDecision"/></font></td>		
	</tr>
	<tr id="closeDate">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Close Date</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.closeDate"/></font></td>
	</tr>
	<tr id="intakeDate">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Intake Date</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.intakeDate"/></font></td>
	</tr>
	<tr id="referralTypeInd">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Type Indicator</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.referralTypeInd"/></font></td>
	</tr>
	<tr id="ctAssignJPOId">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court Assigned JPO</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.ctAssignJPOId"/></font></td>
	</tr>
	<tr id="probationStartDate">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Probation Start Date</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.probationStartDate"/></font></td>
	</tr>
	<tr id="probationEndDate">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Probation End Date</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.probationEndDate"/></font></td>
	</tr>
	<tr id="courtResult">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court Decision/Result</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.courtResult"/></font></td>
	</tr>
	<tr id="courtDisposition">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court Disposition Code</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.courtDisposition"/></font></td>
	</tr>
	<tr id="dispositionDate">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court Disposition Date</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.dispositionDate"/></font></td>
	</tr>
	<tr id="pdaDate">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PDA Date</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.pdaDate"/></font></td>
	</tr>
	<tr id="courtId">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court No.</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.courtId"/></font></td>
	</tr>
	<tr id="piaStatus">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PIA Status</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.piaStatus"/></font></td>
	</tr>
	<tr id="violationProbation">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Probation Violation</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.violationProbation"/></font></td>
	</tr>
	<tr id="daLogNum"">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DA Log Number</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.daLogNum"/></font></td>
	</tr>
	<tr id="transNum">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JOT Transaction Number</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.transNum"/></font></td>
	</tr>
	<tr id="tjjdDate">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TJJD Referral Date</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.TJJDreferralDate"/></font></td>
	</tr>
	<tr id="countyrefd">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Countyrefd</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.countyREFD"/></font></td>
	</tr>
	<tr id="jpoId">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Inassign JPO Id</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.jpoId"/>&nbsp;</font></td>
	</tr>
	<tr id="offenseTotal">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Total</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.offenseTotal"/>&nbsp;</font></td>
	</tr>
	<tr id="probJpoId">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Probation JPO Id</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.probationJpoId"/>&nbsp;</font></td>
	</tr>
	<tr id="decisionType">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Decision Type</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.decisionType"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Last Change User</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.lcuser"/></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Last Change Date/Time</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="juvprogref.lcdate"/> <bean:write name="ProdSupportForm"  property="juvprogref.lcTime"/></font></td>
	</tr>
		
	</table>
	</logic:notEmpty>
	<BR>

	</div>
</html:form>
	<table align="center" border="0" width="500">
		<tr>
			<td colspan="2" align="center">
				<html:form method="post" action="/UpdateMsReferralQuery?clr=Y" onsubmit="return this;">
					<html:submit onclick="return this;" value="Back to Query"/>
				</html:form>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
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