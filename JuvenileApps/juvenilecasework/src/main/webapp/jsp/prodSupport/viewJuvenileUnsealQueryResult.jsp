<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%-- TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<title>Juvenile Casework-/prodSupport/viewJuvenileUnsealQueryResult.jsp</title>


<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>


<script type='text/javascript'>
	function textLimit(field, maxlen) {
		console.log(field.value.length);
		if (field.value.length > maxlen) {
			alert("Your comment has been truncated to " + maxlen
					+ " characters, the maximum allowed.");
			field.value = field.value.substring(0, maxlen);
		}

	}

	function closeWindow(el) {
		window.close();
		return 0;
	}
	function confirmUnseal(){
		if(confirm('Are you sure you want to Un-Seal the master record and all associated records.?')) { 
			spinner();
			$('form').attr('action',"/JuvenileCasework/PerformJuvenileReferralUnSeal.do?submitAction=UnSeal Juvenile");
			$('form').submit();
		} else {
			return false;
		}
	}
	

	function confirmUnsealReferral() {
		if ($('input:radio[name="referralNum"]:checked').length == 0) {
			alert('Please select a Referral Number to process the unsealing.');
			return false;
		}

		if (confirm('Are you sure you want to Un-Seal the referral record?')) {
			spinner();
			$('form').attr('action',"/JuvenileCasework/PerformJuvenileReferralUnSeal.do?submitAction=UnSeal Referral");
			$('form').submit();
		} else {
			return false;
		}
	}
</script>
<html:base />
<style>
.row-disabled {
	   background-color: #dfdfdf;
	   pointer-events: none;
	   width: 100%;
	}
</style>
</head>

<html:form action="/PerformJuvenileReferralUnSeal" onsubmit="return this;">


	<h2 align="center">Juvenile Master/Referral Seal Indicator Removal</h2>
	<table width="100%">
		<tr>
			<td align="center" class="errorAlert"><font color="red"><html:errors></html:errors>
			</font></td>
		</tr>
	</table>
	<logic:notEmpty name="ProdSupportForm" property="juveniles">

		<logic:iterate id="juveniles" name="ProdSupportForm" property="juveniles">
		<table width="900" align="center">
						<tr>
						<td align="center"><b>JUVENILE DETAILS</b></td>
						</tr>
						</table>
			<table border="1" width="900" align="center">
			
				<elogic:if name="juveniles" property="recType" op="equal" value="JUVENILE">
					<elogic:then>
						<tr >
							<td align="center" colspan = "2"><h4>
									<i>Juvenile Master Record is not sealed and does not qualify for Un-Seal processing.</i>
								</h4></td>
						</tr>
					</elogic:then>
				</elogic:if>
				
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE NUMBER</font></td>
						<td><font size="-1"><bean:write name="juveniles" property="juvenileNum" />&nbsp;</font></td>
								</tr>
								<tr>
									<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE NAME</font></td>
									<td><font size="-1"> <bean:write name="juveniles" property="firstName" />&nbsp; 
									<bean:write name="juveniles" property="middleName" />&nbsp; 
									<bean:write name="juveniles" property="lastName" />&nbsp; 
									<bean:write name="juveniles" property="nameSuffix" /> </font>
									</td>
								</tr>
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">RECORD TYPE</font></td>
				<td><font size="-1"> <elogic:if name="juveniles" property="recType" op="equal" value="JUVENILE">
				<elogic:then><font size="-1"> ACTIVE</font></elogic:then></elogic:if> 
				<elogic:if name="juveniles" property="recType" op="equal" value="I.JUVENILE">
					<elogic:then>
						<font size="-1"> PURGED </font>
					</elogic:then>
				</elogic:if> 
				<elogic:if name="juveniles" property="recType" op="equal" value="S.JUVENILE">
					<elogic:then>
						<font size="-1"> SEALED </font>
					</elogic:then>
				</elogic:if> 
				<input type="hidden" id="recordTye" value='<bean:write  name="juveniles" property="recType" />' />
				</font></td>
			</tr>

			<tr>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">DOB</font></td>
					<td><font size="-1"><bean:write name="juveniles" property="dateOfBirth" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
			</tr>

			<tr>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">SEX</font></td>
				<td><font size="-1"><bean:write name="juveniles" property="sex" />&nbsp;</font></td>
			</tr>

			<tr>
				<td bgcolor="gray"><font color="white" face="bold" size="-1">RACE</font></td>
				<td><font size="-1"><bean:write name="juveniles" property="race" />&nbsp;</font></td>
			</tr>

				<tr>
					<td bgcolor="gray"><font color="white" face="bold"
						size="-1">HISPANIC</font></td>
					<td><font size="-1"><bean:write
								name="juveniles" property="hispanic" />&nbsp;</font></td>
				</tr>

				<tr>
					<td bgcolor="gray"><font color="white" face="bold"
						size="-1">SSN</font></td>
					<td><font size="-1"><bean:write
								name="juveniles" property="completeSSN" />&nbsp;</font></td>
				</tr>

				<tr>
					<td bgcolor="gray"><font color="white" face="bold"
						size="-1">MASTER STATUS</font></td>
					<td><font size="-1"><bean:write
								name="juveniles" property="statusId" />&nbsp;</font></td>
				</tr>
				<br />
				<br />											
			
			</table>
		</logic:iterate>
	</logic:notEmpty>
	<br />

	<logic:empty name="ProdSupportForm" property="juveniles">
		<table align="center" border="0" width="700">
			<tr>
				<td align="center"><h4>
						<i>No Juvenile Details found.</i>
					</h4></td>
			</tr>
		</table>
	</logic:empty>
	
			<logic:notEmpty name="ProdSupportForm" property="juvprogrefs">
			<table width="900" align="center">
				<tr>
				<td align="center"><b>REFERRAL DETAILS</b></td></tr>
				<tr>	<td align="center"><h4>
									<i>Select the radio button next to the referral you want to un-seal and click Submit.</i>
								</h4></td>
				</tr>
			</table>
					<table border="1" width="980" align="center">
						
						<tr>
							<td bgcolor="gray"></td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Number</font>
							</td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral Date</font>
							</td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Code</font>
							</td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Intake Decision</font>
							</td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Intake Date</font>
							</td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Court Date</font>
							</td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Court Decision</font>
							</td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Closed Date</font>
							</td>
							<td bgcolor="gray"><font color="white" face="bold" size="-1">Record Type</font>
							</td>
						</tr>

						<logic:iterate id="juvprogrefs" name="ProdSupportForm" property="juvprogrefs" indexId="index">
							
							<logic:notEqual name="juvprogrefs" property="rectype" value="REFERRAL">						
								<tr class ="row-disabled" disabled="true">							
							</logic:notEqual>	
							
							<logic:equal name="juvprogrefs" property="rectype" value="S.REFERRAL">	
							<tr>
								<bean:define id="referralNum" name='juvprogrefs' property='referralNum' type="java.lang.String"></bean:define>
								<td><input type="radio" onclick="enableSelectButton();" id="updateRefNumRadio" name="referralNum" value="<bean:write name='juvprogrefs' property='referralNum'/>" />
								</td>
								<td width="10%"><html:text name="juvprogrefs" property="referralNum" styleId='<%="refNum-" + referralNum%>'
										size="4" style="border: 0;background-color: white" indexed="true" disabled="true" />
								</td>
								<td><font size="-1"><bean:write name="juvprogrefs" property="referralDate" formatKey="date.format.mmddyyyy" />&nbsp;</font>
								</td>
								<td><font size="-1"><bean:write name="juvprogrefs" property="offenseCode" />&nbsp;</font>
								</td>
								<td><font size="-1"><bean:write name="juvprogrefs" property="intakeDecision" />&nbsp;</font>
								</td>
								<td><font size="-1"><bean:write name="juvprogrefs" property="intakeDate" formatKey="date.format.mmddyyyy" />&nbsp;</font>
								</td>
								<td><font size="-1"><bean:write name="juvprogrefs" property="dispositionDate" formatKey="date.format.mmddyyyy" />&nbsp;</font>
								</td>
								<td><font size="-1"><bean:write name="juvprogrefs" property="courtResult" />&nbsp;</font>
								</td>
								<td><font size="-1"><bean:write name="juvprogrefs" property="closeDate" formatKey="date.format.mmddyyyy" />&nbsp;</font>
								</td>
								<td width="10%"><font size="-1"><bean:write name="juvprogrefs" property="rectype" />&nbsp;</font>
								</td>

							</tr>
							</logic:equal>
						</logic:iterate>

					</table>

				</logic:notEmpty>

	<table border="0" width="700">
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr align="center">
			<td colspan="4"><font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
						<bean:write name="ProdSupportForm" property="msg" />
			</logic:notEqual> </font>
			</td>
		</tr>
	</table>

	<table align="center" border="0" width="10%">

		<tr>
			<logic:equal name="ProdSupportForm" property="rectype" value="S.JUVENILE">
				<td align="left"><input type="submit" name="Un-Seal Juvenile" value="<bean:message key="button.UnSealJuvenile"/>" onClick="return confirmUnseal();" /> <!-- <input type="submit" name="Un-Seal Juvenile" value="Un-Seal Juvenile" onClick="return confirmUnseal();"/> -->
				</td>
			</logic:equal>
			<logic:notEqual name="ProdSupportForm" property="juvprogrefCount" value='0'>
				<td align="right"><input type="submit" name="Un-Seal Referral" value="<bean:message key="button.UnSealReferral"/>" onClick="return confirmUnsealReferral();" /></td>
			</logic:notEqual>
		</tr>
		</html:form>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td><html:button property="org.apache.struts.taglib.html.BUTTON" onclick="goNav('back')"> <bean:message key="button.back"></bean:message>
				</html:button>
			</td>
			<td>
				<html:form method="post" action="/displayProductionSupport.do" onsubmit="return this;">
					<html:submit onclick="return this;" value="Back to Main Menu"/>
				</html:form>
			</td>
		</tr>
	</table>
	<br />

	


</html:html>