<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic"%>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.Features" %>

<head>

<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Juvenile Casework
	-/prodSupport/ReferralOffenseUpdateQueryResult</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/UpdateReferralOffense.js"></script>
<script type="text/javascript">
function Set()
{
	localStorage.setItem("RecordWin", "open");
	return true;
}
</script>

</head>

<body>

	<html:form action="/PerformUpdateReferralOffenseRecord"
		onsubmit="return this;">
	<!-- BEGIN Error Message Table -->
		 <logic:messagesPresent message="true"> 
			<table width="100%">
				<tr>		  
					<td align="center" class="messageAlert"><html:messages id="message" message="true"><font style="font-weight: bold;"
							color="green" size="3" face="Arial"><bean:write name="message"/></html:messages></font></td>		  
				</tr>   	  
			</table>
		</logic:messagesPresent> 
		
		<!-- BEGIN ERROR TABLE -->
		<table width="100%">
			<%-- <tr>
				<td align="center" class="errorAlert"><html:errors></html:errors></td>
			</tr> --%>
			<tr>
						<td align="center" colspan="4" class="errorAlert"><font style="font-weight: bold;"
							color="red" size="3" face="Arial"><html:errors></html:errors></font></td>
			</tr>
		</table>
	<!-- END ERROR TABLE -->
		<div>

			<h2 align="center">Results for Juvenile ID = 
			<bean:write name="ProdSupportForm" property="juvenileId" /></h2>

			<!-- Error Message Area -->
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<table border="0" width="700" align="center">

					<tr align="center">
						<td colspan="4"><font style="font-weight: bold;"
							color="#FF0000" size="3" face="Arial"> <bean:write
									name="ProdSupportForm" property="msg" /> </font>
						</td>
					</tr>
				</table>
			</logic:notEqual>
			
			<!-- End Error Message Area -->
			<p align="center">
				<b><i>Change the values of the field(s). <br> and Click
						Update Referral.</i>
				</b>
			</p>
			
	<logic:notEmpty	name="ProdSupportForm" property="originalReferralOffense"> 
	<bean:define id="originalReferralOffense" name="ProdSupportForm" property="originalReferralOffense" type="messaging.juvenile.reply.JJSOffenseResponseEvent"/>
		
	<table class="resultTbl" border="1" width="800" align="center">
	<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile
							Number</font>
					</td>
					<%-- <td><font size="-1"><bean:write name="ProdSupportForm"
								property="juvenileId" />&nbsp;</font>
					</td> --%>
					<td><font size="-1"><bean:write name="originalReferralOffense" property="juvenileNum"/>&nbsp;</font></td>
					<td><jims2:isAllowed requiredFeatures='<%=Features.JCW_PS_REFERRALOFFENSEPROCESSING_MASTER%>'><font size="-1"><html:text name="ProdSupportForm" property="referralOffense.juvenileNum" size="8" styleId='juvNum' maxlength="8"/></font></jims2:isAllowed></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile
							Name</font>
					</td>
					<td><font size="-1"><bean:write name="ProdSupportForm"
								property="juvenileName" />&nbsp;</font>
					</td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">Record Type</font>
					</td>
					<td>
					

					<elogic:if name="ProdSupportForm" property="rectype" op="equal"
						value="S.JUVENILE">
						<elogic:then>
							<font size="-1"> SEALED </font>
						</elogic:then>
					</elogic:if>
					<elogic:if name="ProdSupportForm" property="rectype" op="equal"
						value="I.JUVENILE">
						<elogic:then>
							<font size="-1"> PURGED </font>
						</elogic:then>
					</elogic:if>
					<%-- <elogic:if name="ProdSupportForm" property="rectype" op="equal"
						value="JUVENILE">
						<elogic:then>
							<font size="-1"> ACTIVE </font>
						</elogic:then>
					</elogic:if> --%>
					</td>
					
				</tr>
				<tr>
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Master Status</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm"
				property="statusId" />&nbsp;</font></td>
		</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral No.</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="referralNum"/>&nbsp;</font></td>
		<td><jims2:isAllowed requiredFeatures='<%=Features.JCW_PS_REFERRALOFFENSEPROCESSING_MASTER%>'><font size="-1"><html:text name="ProdSupportForm" property="referralOffense.referralNum" size="4" styleId='refNum' maxlength="4" /></font></jims2:isAllowed></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Code</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="oldoffenseCode"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="referralOffense.offenseCode" styleId='offCode' size="6" /></font>
		<html:submit property="submitAction" styleId="validateCode"><bean:message key="button.validateOffenseCode" /></html:submit>&nbsp;Or&nbsp;
		<html:submit property="submitAction" onclick="return Set();"><bean:message key="button.searchForOffenseCode" /></html:submit>
		<input type="hidden" name="validateMsg" value="<bean:write name="ProdSupportForm" property="msg" />"  id="valOffMsgId" /></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Severity</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="offenseSeverity"/>&nbsp;</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="referralOffense.offenseSeverity"/>&nbsp;</font></td>
		<%-- <td><font size="-1"><html:text name="referralOffenses" property="offenseCode" styleId='offSeverity' size="3" indexed="true"/></font></td> --%>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Date</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="offDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="referralOffense.offDate" styleId='offDate' size="10" maxlength="10" /></font></td>
	</tr>	
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Keymap</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="keyMapLocation"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="referralOffense.keyMapLocation" size="6" styleId='offKeyMap' /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Investigation</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="investigationNum"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="referralOffense.investigationNum" styleId='offInvestNumber' size="20" maxlength="20" /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Street Number</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="offenseStreetNum"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="referralOffense.offenseStreetNum" styleId='offStreetNumber' size="6" maxlength="6" /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Street Name</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="offenseStreetName"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="referralOffense.offenseStreetName" styleId='offStreetName' size="20" maxlength="20" /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Apt. Number</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="offenseAptNum"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="referralOffense.offenseAptNum" styleId='offAptNumber' size="8" maxlength="8" /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense City</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="offenseCity"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="referralOffense.offenseCity" styleId='offCity' size="15" maxlength="15" /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense State</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="offenseState"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="referralOffense.offenseState" styleId='offState' size="2" maxlength="2" /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Zip</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="offenseZip"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="referralOffense.offenseZip" styleId='offZip' size="9" maxlength="9" /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Weapon Type</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="weaponType"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="referralOffense.weaponType" styleId='offWeaponType' size="55" maxlength="55" /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CJIS Number</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="cjisNum"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="referralOffense.cjisNum" styleId='offCJISNum' size="15" maxlength="15" /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Date of Arrest</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="arrestDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="referralOffense.arrestDate" styleId='arrestDate' size="10" maxlength="10" /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Arrest Time</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="arrestTime"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="referralOffense.arrestTime" styleId='arrestTime' /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">On Campus Offense</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="onCampOffense"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="referralOffense.onCampOffense" styleId='onCampOffense' size="15" maxlength="15" /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">On Campus District</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="onCampDistrict"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="referralOffense.onCampDistrict" styleId='onCampDistrict' size="15" maxlength="15" /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">On Campus School</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="onCampSchool"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="referralOffense.onCampSchool" styleId='onCampSchool' size="15" maxlength="15" /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Sequence Number</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="sequenceNum"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="referralOffense.sequenceNum" styleId='seqNum' size="2" maxlength="2" /></font></td>
	</tr>
		
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Charge Sequence Number</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="chargeSequenceNum"/>&nbsp;</font></td>
		<td><font size="-1"><html:text name="ProdSupportForm" property="referralOffense.chargeSequenceNum" styleId='chargeSeqNum' size="2" maxlength="2" /></font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Last Changed User</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="lcUser"/>&nbsp;</font></td>		
	</tr> 
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Last Changed Date/Time</font></td>
		<td><font size="-1"><bean:write name="originalReferralOffense" property="lcDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font>	
		<font size="-1"><bean:write name="originalReferralOffense" property="lcTime" />&nbsp;</font></td>	
	</tr>
	</table>
	</logic:notEmpty>
			<BR>

			<table align="center"">

				<logic:notEmpty name="ProdSupportForm" property="originalReferralOffense">
					<td>
						<p align="center">
							<html:submit property="submitAction" styleId="submitBtn">
								<bean:message key="button.updateOffense" />
							</html:submit>
						</p></td>
				</logic:notEmpty>

			</table>


			

		</div>

</html:form>
		<table align="center"">
			<tr>

				<td>&nbsp;</td>

			</tr>

			<html:form action="/UpdateReferralOffenseRecordQuery?clr=Y"
		onsubmit="return this;">
		<table align="center"">
			<tr>
				<td><html:submit value="Back to Query" />
				</td>
			</tr>
		</table>
	</html:form>
		</table>
		
		<html:hidden styleId="hdnrefDate" name="ProdSupportForm"
			property="referralDate" />
	<%-- </html:form> --%>

</body>
</html:html>