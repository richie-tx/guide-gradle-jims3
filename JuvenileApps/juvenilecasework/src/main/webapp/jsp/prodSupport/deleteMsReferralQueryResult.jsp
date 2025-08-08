<!DOCTYPE HTML>


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
<title>Juvenile Casework -/prodSupport/deleteMsReferralQueryResult</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
	
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/deleteReferral.js"></script>

</head>

<body>

<html:form action="/DeleteAssociatedMsReferralQuery" onsubmit="return this;">

<div>
	
	<h2 align="center">Referral Details for Referral ID = 
			<bean:write name="ProdSupportForm" property="referralOID" /></h2>
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
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Number</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm" property="juvenileId" />&nbsp;</font></td>
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
		
	</table>     
<p align="center"><b><i>Review the values of the field(s). <br>
and Click Next to review the associated records.</i></b></p>		     
	<logic:notEmpty	name="ProdSupportForm" property="juvprogrefs">
	<logic:iterate id="juvprogrefs" name="ProdSupportForm" property="juvprogrefs">
	<h3 align="center">Referral Details</h3>
	
	<table class="resultTbl" border="1" width="800" align="center">
	
	<tr>		<bean:define id="oid" name='juvprogrefs' property='referralOID' type="java.lang.String"></bean:define>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Number</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="juvenileNum"/>&nbsp;</font></td>
		<html:hidden styleId='oldJuvenileNum'  name='juvprogrefs' property='juvenileNum' indexed="true"/>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral No.</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="referralNum"/>&nbsp;</font></td>
		<html:hidden styleId='oldReferralNum'  name='juvprogrefs' property='referralNum' indexed="true"/>
		<html:hidden styleId='<%="referralId-"+oid%>'  name='juvprogrefs' property='referralOID' indexed="true"/>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Date</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="referralDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Offense Code</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="offenseCode" />&nbsp;</font></td>
	</tr>
	</tr>

	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Source</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="referralSource"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Officer</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="referralOfficer"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Decision</font></td>	
		<td><font size="-1"><bean:write name="juvprogrefs" property="intakeDecision"/>&nbsp;</font></td>	
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Close Date</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="closeDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Intake Date</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="intakeDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Type Indicator</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="referralTypeInd"/>&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court Assigned JPO</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="ctAssignJPOId"/>&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Probation Start Date</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="probationStartDate"/>&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Probation End Date</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="probationEndDate"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court Decision/Result</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="courtResult"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court Disposition Code</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="courtDisposition"/>&nbsp;</font></td>
	</tr>
	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court Disposition Date</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="dispositionDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
	</tr>
	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PDA Date</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="pdaDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
	</tr>
	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Court No.</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="courtId"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PIA Status</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="piaStatus"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Probation Violation</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="violationProbation"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DA Log Number</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="daLogNum"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JOT Transaction Number</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="transNum"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Last Changed Date</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="lcdate" formatKey="date.format.mmddyyyy"/><bean:write name="juvprogrefs"  property="lcTime"/></font></td>		
	</tr> 
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Last Changed User</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="lcuser"/>&nbsp;</font></td>		
	</tr> 
		
	</table>
	</logic:iterate>
	</logic:notEmpty>
	<BR>
	
	<table align="center"">
	
	<logic:notEmpty	name="ProdSupportForm" property="juvprogrefs">
	<td>
	<p align="center">
		<input onclick="spinner();" id="submitBtn" type="submit" value="Next" />
	</p>
	</td>
	</logic:notEmpty>

	</table>
	
	
	<logic:empty name="ProdSupportForm" property="juvprogrefs">
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

	<tr>
		<td>		
		<html:button property="submitAction" onclick="spinner();" styleId="backToQryBtn">
  				<bean:message key="button.backToSearch"></bean:message>
  			</html:button>
		</td>
	</tr>
</table>
<html:hidden styleId="prevJuvNum" name="ProdSupportForm" property="fromJuvenileId"/>
<html:hidden styleId="juvenileNum" name="ProdSupportForm" property="juvenileId"/>
<html:hidden styleId="referralNum" name="ProdSupportForm" property="referralId"/>
</html:form>

</body>
</html:html>