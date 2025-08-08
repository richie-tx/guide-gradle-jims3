<!DOCTYPE HTML>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/updatePactReferralQuerySummary</title>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/updatePactReferralQuery.js"></script>
</head>

<body>

	<html:form action="/PerformUpdatePactReferral" onsubmit="return this;">
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

			<tr>
				<td align="center" colspan="4" class="errorAlert"><font style="font-weight: bold;" color="red" size="3" face="Arial"><html:errors></html:errors></font></td>
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
						<td colspan="4"><font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> <bean:write name="ProdSupportForm" property="msg" /> </font>
						</td>
					</tr>
				</table>
			</logic:notEqual>
			
			<p align="center"><b><i><font style="font-weight: bold;"
							color="green" size="3" face="Arial">Record Successfully Updated</font></i></b></p>
			
		<table border="1" width="800" align="center">
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Name</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="juvenileName" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Juvenile Number</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="juvenileId" />&nbsp;</font></td>
	</tr>
	<tr>
		<logic:notEqual name="ProdSupportForm" property="newReferralID" value="">	
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral No.</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="newReferralID" />&nbsp;</font></td>
			<td>
				<font color="red"><i>Updated from previous value, </i><bean:write  name="ProdSupportForm" property="pactRecord.referralNumber" /></font>
			</td>
		</logic:notEqual>
		<logic:equal name="ProdSupportForm" property="newReferralID" value="">
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Referral No.</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactRecord.referralNumber" />&nbsp;</font></td>
		</logic:equal>
	</tr>
	<tr>
		<logic:notEqual name="ProdSupportForm" property="newRiskNeedLvlId" value="">	
			<td bgcolor="gray"><font color="white" face="bold" size="-1">RISKNDSLVLID</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="newRiskNeedLvlId" />&nbsp;</font></td>
			<td>
				<font color="red"><i>Updated from previous value, </i><bean:write  name="ProdSupportForm" property="pactRecord.riskNeedLvlId" /></font>
			</td>
		</logic:notEqual>
		<logic:equal name="ProdSupportForm" property="newRiskNeedLvlId" value="">
			<td bgcolor="gray"><font color="white" face="bold" size="-1">RISKNDSLVLID</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactRecord.riskNeedLvlId" />&nbsp;</font></td>
		</logic:equal>
	</tr>
	<tr>
		<logic:notEqual name="ProdSupportForm" property="newCaseID" value="">	
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Casefile ID</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="newCaseID" />&nbsp;</font></td>
			<td>
				<font color="red"><i>Updated from previous value, </i><bean:write  name="ProdSupportForm" property="pactRecord.caseFileId" /></font>
			</td>
		</logic:notEqual>
		<logic:equal name="ProdSupportForm" property="newCaseID" value="">
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Casefile ID</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactRecord.caseFileId" />&nbsp;</font></td>
		</logic:equal>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Offense Code</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="offCode" />&nbsp;</font></td>
	</tr>
	<tr>
		<logic:notEqual name="ProdSupportForm" property="newPactDate" value="">	
			<td bgcolor="gray"><font color="white" face="bold" size="-1">PACT Date</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="newPactDate" />&nbsp;</font></td>
			<td>
				<font color="red"><i>Updated from previous value, </i><bean:write  name="ProdSupportForm" property="pactDate" /></font>
			</td>
		</logic:notEqual>
		<logic:equal name="ProdSupportForm" property="newPactDate" value="">
			<td bgcolor="gray"><font color="white" face="bold" size="-1">PACT Date</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactDate" />&nbsp;</font></td>
		</logic:equal>
	</tr>
	<tr>
		<logic:notEqual name="ProdSupportForm" property="riskLevel" value="">	
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Risk Level</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="riskLevel" />&nbsp;</font></td>	
			<td>
				<font color="red"><i>Updated from previous value, </i><bean:write  name="ProdSupportForm" property="riskValue" /></font>
			</td>
		</logic:notEqual>
		<logic:equal name="ProdSupportForm" property="riskLevel" value="">
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Risk Level</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="riskValue" />&nbsp;</font></td>	
		</logic:equal>
	</tr>
	
	<tr>
		<logic:notEqual name="ProdSupportForm" property="needLevel" value="">	
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Need Level</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="needLevel" />&nbsp;</font></td>
			<td>
				<font color="red"><i>Updated from previous value, </i><bean:write  name="ProdSupportForm" property="needValue" /></font>
			</td>
		</logic:notEqual>
		<logic:equal name="ProdSupportForm" property="needLevel" value="">
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Need Level</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="needValue" />&nbsp;</font></td>	
		</logic:equal>
	</tr>
	<tr>
		<logic:notEqual name="ProdSupportForm" property="newPactStatus" value="">	
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Status</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="newPactStatus" />&nbsp;</font></td>
			<td>
				<font color="red"><i>Updated from previous value, </i><bean:write  name="ProdSupportForm" property="pactStatus" /></font>
			</td>
		</logic:notEqual>
		<logic:equal name="ProdSupportForm" property="newPactStatus" value="">
			<td bgcolor="gray"><font color="white" face="bold" size="-1">Status</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactStatus" />&nbsp;</font></td>	
		</logic:equal>
	</tr>
	<tr>
		<logic:equal name="ProdSupportForm" property="pactOIDChanged" value="true">	
			<td bgcolor="gray"><font color="white" face="bold" size="-1">PACT ID</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="newPactId" />&nbsp;</font></td>	
			<td>
				<font color="red"><i>Updated from previous value, </i><bean:write  name="ProdSupportForm" property="pactId" /></font>
			</td>
		</logic:equal>
		<logic:equal name="ProdSupportForm" property="pactOIDChanged" value="false">
			<td bgcolor="gray"><font color="white" face="bold" size="-1">PACT ID</font></td>
			<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactId" />&nbsp;</font></td>	
		</logic:equal>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Create User</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactRecord.createUserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Create Date</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactRecord.createTimestamp" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Update User</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactRecord.updateUserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Last Date/Time</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactRecord.updateTimestamp" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Create JIMS2User</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactRecord.createJIMS2UserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">Update JIMS2User</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="pactRecord.updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</table>		

		</div>

</html:form>
		<table align="center"">
			<tr>

				<td>&nbsp;</td>

			</tr>
	</table>
	<table align="center">
		<tr>
		<td>
		<html:form method="post" action="/UpdatePactReferralQuery?clr=Y" onsubmit="return this;">
		<html:submit onclick="return this;" value="Update PACT Referral Details" />
		</html:form>
		</td>
		<td>
		<html:form method="post" action="/MainMenu" onsubmit="return this;">
		<html:submit onclick="return this;" value="Back to Main Menu"/>
		</html:form>
		</td>
		</tr>
    </table> 
		

</body>
</html:html>