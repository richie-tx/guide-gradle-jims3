<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/updateClosingSummary.jsp</title>
</head>

<body>
	
<html:form method="post" action="/UpdateClosingQuery" onsubmit="return this;">

<h2 align="center">Update Casefile Closing Summary</h2>
<hr>

<p align="center"><font color="green"><b>Casefile ID 
<bean:write name="ProdSupportForm" property="casefileId" /> was successfully updated.</b></font></p>


<p align="center"><b>The following is a list of records affected by this change. This is for auditing purposes.<br> 
If this delete was made in error, please provide this list to the JIMS Data Team to restore the data.</b></p>
<hr>

	<logic:notEmpty	name="ProdSupportForm" property="casefileclosings">
	<logic:iterate id="casefileclosings" name="ProdSupportForm" property="casefileclosings">
	<h3 align="center">Casefile Details</h3>
	
	<table border="1" width="800" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASFILECLOSNG_ID</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="casefileClosingInfoId" />&nbsp;</font></td>
	</tr>
	<logic:notEmpty name="ProdSupportForm" property="outcomeBox">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SUPERVISIONOUTCOME</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="outcomeBox" />&nbsp;</font></td>
		<td>
		<font color="red"><i>Updated, previous value: </i>
		<bean:write name="ProdSupportForm" property="oldSupervisionOutcome" /></font>
		</td>
	</logic:notEmpty>
	<logic:empty name="ProdSupportForm" property="outcomeBox">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SUPERVISIONOUTCOME</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="supervisionOutcomeId" />&nbsp;</font></td>
	</logic:empty>
	<tr>
	<logic:notEqual name="ProdSupportForm" property="newControllingReferral" value="">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CNTROLLINGREFERRAL</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="newControllingReferral" />&nbsp;</font></td>
		<td>
		<font color="red"><i>Updated, previous value: </i>
			<bean:write name="ProdSupportForm" property="oldControllingReferral" /></font>
		</td>
		</logic:notEqual>
		
		<logic:equal name="ProdSupportForm" property="newControllingReferral" value="">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CNTROLLINGREFERRAL</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="controllingReferralId" />&nbsp;</font></td>
		</logic:equal>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASFILECLOSNGSTATS</font></td>
		<td><font size="-1"><bean:write name="casefileclosings" property="casefileClosingStatus" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">FACLTYRLESEREASON</font></td>
		<td><font size="-1"><bean:write name="casefileclosings" property="facilityReleaseReasonId" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PERMANENCYPLAN</font></td>
		<td><font size="-1"><bean:write name="casefileclosings" property="permanencyPlanId" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">FACILITY</font></td>
		<td><font size="-1"><bean:write name="casefileclosings" property="facilityId" />&nbsp;</font></td>
	</tr>
		<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LEVELOFCARE</font></td>
		<td><font size="-1"><bean:write name="casefileclosings" property="levelOfCareId" />&nbsp;</font></td>
	</tr>
		<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PETITIONNUMBER</font></td>
		<td><font size="-1"><bean:write name="casefileclosings" property="petitionNumber" />&nbsp;</font></td>
	</tr>
		<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">EXITPLANTEMPLTLCTN</font></td>
		<td><font size="-1"><bean:write name="casefileclosings" property="exitPlanTemplateLocation" />&nbsp;</font></td>
	</tr>
	<tr>
		<logic:notEqual name="ProdSupportForm" property="newEndDate" value="">	
			<td bgcolor="gray"><font color="white" face="bold" size="-1">SUPRVSIONENDDATE</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm" property="newEndDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
			<td>
			<font color="red"><i>Updated, previous value: </i>
				<bean:write  name="ProdSupportForm" property="oldSupervisionEndDate" formatKey="date.format.mmddyyyy" /></font>
			</td>
			</logic:notEqual>
			
			<logic:equal name="ProdSupportForm" property="newEndDate" value="">	
			<td bgcolor="gray"><font color="white" face="bold" size="-1">SUPRVSIONENDDATE</font></td>
			<td><font size="-1"><bean:write  name="casefileclosings" property="supervisionEndDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
			</logic:equal>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">EXPECTEDRELESEDATE</font></td>
		<td><font size="-1"><bean:write name="casefileclosings" property="expectedReleaseDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISCLOSNGPKTGEN</font></td>
		<td>
		<logic:equal name="casefileclosings" property="closingPktGenerated" value="1">
			<font size="-1">True</font>
		</logic:equal>
		<logic:notEqual name="casefileclosings" property="closingPktGenerated" value="1">
			<font size="-1">False</font>
		</logic:notEqual>
		</td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ISCLOSNGLTRGEN</font></td>
		<td>
		<logic:equal name="casefileclosings" property="closingLetterGenerated" value="1">
			<font size="-1">True</font>
		</logic:equal>
		<logic:notEqual name="casefileclosings" property="closingLetterGenerated" value="1">
			<font size="-1">False</font>
		</logic:notEqual>
		</td>
	</tr>
	
		<tr>
		<logic:notEmpty name="ProdSupportForm" property="outcomeDescBox">
		<logic:equal name="ProdSupportForm" property="outcomeDescBox" value="999">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SPRVNOUTCOMEDESCCD</font></td>
		<td><font size="-1">&nbsp;</font></td>
		</logic:equal>
		<logic:notEqual name="ProdSupportForm" property="outcomeDescBox" value="999">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SPRVNOUTCOMEDESCCD</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="outcomeDescBox" />&nbsp;</font></td>
		</logic:notEqual>
		<td>
		<font color="red"><i>Updated, previous value: </i><bean:write name="ProdSupportForm" property="oldSupervisionOutcomeDescCd" /></font>
		</td>
		</logic:notEmpty>
		<logic:empty name="ProdSupportForm" property="outcomeDescBox">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SPRVNOUTCOMEDESCCD</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="supervisionOutcomeDescriptionId" />&nbsp;</font></td>
		</logic:empty>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RECORDCLM</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="recordCLM" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVLOCUNIT_ID</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="juvLocUnitId" />&nbsp;</font></td>
	</tr>
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="createdBy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATDATE</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="updateUser" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="createJIMS2UserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
		
	</table>
	
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