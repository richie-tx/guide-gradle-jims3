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
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Juvenile Casework -/prodSupport/referralUpdateQueryResult</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
	
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/updateReferral.js"></script>

</head>

<body>

<html:form action="/PerformUpdateMsReferral" onsubmit="return this;">

<div>
	
	<h2 align="center">Results for Juvenile ID = 
			<bean:write name="ProdSupportForm" property="juvenileId" /></h2>
	     		<p align="center"><b><i>
	     		<elogic:if name="ProdSupportForm" property="rectype" op="equal" value="S.JUVENILE">
					<elogic:then>
						<font size="-1"> Juvenile record is classified as Sealed. </font>
					</elogic:then>	
				</elogic:if>		
				<elogic:if name="ProdSupportForm" property="rectype" op="equal" value="I.JUVENILE">
						<elogic:then>
						<font size="-1"> Juvenile record is classified as Purged. </font>
					</elogic:then>
				</elogic:if>
				</i></b></p>
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

	<table class="resultTbl" border="1" width="750" align="center">
	
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Name</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="juvenileName" />&nbsp;</font>
				<elogic:if name="ProdSupportForm" property="rectype" op="equal" value="S.JUVENILE">
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
			<td><font size="-1"><bean:write name="ProdSupportForm" property="juvenileId" />&nbsp;</font></td>
		</tr>
		<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Master Status</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="statusId" />&nbsp;</font></td>
		</tr>
	</table>     
<p align="center"><b><i>Change the values of the field(s). <br>
and Click Update Referral.</i></b></p>		     
	<logic:notEmpty	name="ProdSupportForm" property="originalJuvprogref">
	<h3 align="center">Referral Details</h3>
	
	<table class="resultTbl" border="1" width="800" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Number</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.juvenileNum"/>&nbsp;</font></td>
		<html:hidden styleId='juvNum'  name='ProdSupportForm' property='originalJuvprogref.juvenileNum'/>
		<td><jims2:isAllowed requiredFeatures='<%=Features.JCW_PS_REFERRALUPDATE_MASTER%>'><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.juvenileNum" size="6" styleId='juvNum' maxlength="8" /></font></jims2:isAllowed></td>
		<html:hidden styleId='oldJuvenileNum'  name='ProdSupportForm' property='originalJuvprogref.juvenileNum'/>
		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral No.</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.referralNum"/>&nbsp;</font></td>
		<html:hidden styleId='refNum'  name='ProdSupportForm' property='originalJuvprogref.referralNum'/>
		<td><jims2:isAllowed requiredFeatures='<%=Features.JCW_PS_REFERRALUPDATE_MASTER%>'><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.referralNum" size="4" styleId='refNum' maxlength="4" /></font></jims2:isAllowed></td>
		<html:hidden styleId='oldReferralNum'  name='ProdSupportForm' property='originalJuvprogref.referralNum'/>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Date</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.referralDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.referralDate" styleId='refDate' size="10" /></font></td>
		<html:hidden styleId='oldReferralDate'  name='ProdSupportForm' property='originalJuvprogref.referralDate'/>
	</tr>
	</tr>

	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Source</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.referralSource"/>&nbsp;</font></td>
		<td> 		
		<html:select name="ProdSupportForm" property="juvprogref.referralSource" styleId='referralSource' >
			<html:option key="select.generic" value="" />
			<html:optionsCollection name="ProdSupportForm" property="referralSrcCodes" value="code" label="codeWithDescription"/> 				
		</html:select>
		<html:hidden styleId='oldReferralSource'  name='ProdSupportForm' property='originalJuvprogref.referralSource' />
	   </td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Officer</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.referralOfficer"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.referralOfficer" size="25" styleId='jpo' /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Decision</font></td>	
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.intakeDecision"/>&nbsp;</font></td>	
		<td> 
			<html:select name="ProdSupportForm" property="juvprogref.intakeDecision" styleId='intakeDecision'>
			<html:option key="select.generic" value="" />
			<html:optionsCollection name="ProdSupportForm" property="outcomeCodes" value="code" label="codeWithDescription"/> 				
			</html:select>
		</td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Close Date</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.closeDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.closeDate" styleId='clDate' size="10" /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Intake Date</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.intakeDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.intakeDate" styleId='intakeDate' size="10" /></font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Type Indicator</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.referralTypeInd"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.referralTypeInd" styleId='refTypeInd' size="2" maxlength="2" /></font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court Assigned JPO</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.ctAssignJPOId"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.ctAssignJPOId" styleId='cAssignJPO' size="5" maxlength="5" /></font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Probation Start Date</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.probationStartDate"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.probationStartDate" styleId='pStartDate' size="10" maxlength="10" /></font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Probation End Date</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.probationEndDate"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.probationEndDate" styleId='pEndDate' size="10" maxlength="10" /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court Decision/Result</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.courtResult"/>&nbsp;</font></td>
		<td> 
			<html:select name="ProdSupportForm" property="juvprogref.courtResult" styleId='crtResult' >
			<html:option key="select.generic" value="" />
			<html:optionsCollection name="ProdSupportForm" property="statusCodes" value="codeAlpha" label="codeAlpha"/> 				
			</html:select>
		</td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court Disposition Code</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.courtDisposition"/>&nbsp;</font></td>
		<td> 
			<html:select name="ProdSupportForm" property="juvprogref.courtDisposition" styleId='crtDisposition' >
			<html:option key="select.generic" value="" />
			<html:optionsCollection name="ProdSupportForm" property="statusCodes" value="codeAlpha" label="codeAlpha"/> 				
			</html:select>
		</td>
	</tr>
	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court Disposition Date</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.dispositionDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.dispositionDate" styleId='dispDate' size="10" maxlength="10" /></font></td>
	</tr>
	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PDA Date</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.pdaDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.pdaDate" styleId='pdaDate' size="10" maxlength="10" /></font></td>
	</tr>
	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court No.</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.courtId"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.courtId" styleId='courtId' size="3" maxlength="3" /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PIA Status</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.piaStatus"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.piaStatus" styleId='piaStatus' size="1" maxlength="1" /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Probation Violation</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.violationProbation"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.violationProbation" styleId='violationProbation' size="1" maxlength="1" /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DA Log Number</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.daLogNum"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.daLogNum" styleId='daLogNum' size="6" maxlength="6" /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JOT Transaction Number</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.transNum"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.transNum" styleId='transNum' size="6" maxlength="6" /></font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TJJD Referral Date</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.TJJDreferralDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.TJJDreferralDate" styleId='TJJDrefDate' size="10" /></font></td>
		<html:hidden styleId='oldTJJDReferralDate'  name='ProdSupportForm' property='originalJuvprogref.TJJDreferralDate'/>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Countyrefd</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.countyREFD"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.countyREFD" styleId='countyREFD' size="3" maxlength="3" /></font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Inassign JPO Id</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.jpoId"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.jpoId" size="5" maxlength="5" /></font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Total</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.offenseTotal"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.offenseTotal" size="3" maxlength="3" /></font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Probation JPO Id</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.probationJpoId"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.probationJpoId" size="5" maxlength="5" /></font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Decision Type</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.decisionType"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="juvprogref.decisionType" size="1" maxlength="1" /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Last Changed Date</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.lcdate" formatKey="date.format.mmddyyyy"/><bean:write name="ProdSupportForm"  property="originalJuvprogref.lcTime"/></font></td>		
	</tr> 
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Last Changed User</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="originalJuvprogref.lcuser"/>&nbsp;</font></td>		
	</tr> 
		
	</table>
	</logic:notEmpty>
	<BR>
	
	<table align="center"">
	
	<logic:notEmpty	name="ProdSupportForm" property="originalJuvprogref">
	<td>
	<p align="center">
		<input id="submitBtn" type="button" value="Update Referral" />
	</p>
	</td>
	</logic:notEmpty>

	</table>
	
	
	<logic:empty name="ProdSupportForm" property="originalJuvprogref">
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
		<td>		
			<html:submit property="submitAction" 
						onclick="spinner();" 
						value="Back to Query"
						styleId="backToQryBtn"></html:submit>
		</td>
	</tr>
</table>
<html:hidden styleId="prevJuvNum" name="ProdSupportForm" property="fromJuvenileId"/>
<html:hidden styleId="juvenileNum" name="ProdSupportForm" property="juvenileId"/>
<html:hidden styleId="referralNum" name="ProdSupportForm" property="referralId"/>
</html:form>

</body>
</html:html>