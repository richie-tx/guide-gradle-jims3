<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/updateReferralSummary.jsp</title>
</head>

<body>

<html:form method="post" action="/DeleteCasefileQuery" onsubmit="return this;">

<h2 align="center">Update Referral Summary</h2>
<hr>

<p align="center"><font color="green"><b>Referral number 
<bean:write name="ProdSupportForm" property="referralNum" /> was successfully updated.</b></font></p>


<p align="center"><b>The following is a list of records affected by this change. This is for auditing purposes.<br> 
If this delete was made in error, please provide this list to the JIMS Data Team to restore the data.</b></p>
<hr>

	<logic:notEmpty	name="ProdSupportForm" property="juvprogrefs">
	<logic:iterate id="juvprogrefs" name="ProdSupportForm" property="juvprogrefs">
	<h3 align="center">Referral Details</h3>
	
	<table border="1" width="800" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVPROGREF_ID</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="juvenileProgramReferralId" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="caseFileId" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACKDATE</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="ackDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">BEGINDATE</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="beginDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ENDDATE</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="endDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSIGNEDHOURS</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="assignedHours" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SENTDATE</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="sentDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>	
		<logic:notEmpty name="ProdSupportForm" property="statusBox">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">STATUSCD</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="statusBox" />&nbsp;</font></td>
		<td>
		<font color="red"><i>Code updated, previous value: </i><bean:write  name="juvprogrefs" property="statusCd" /></font>
		</td>
		</logic:notEmpty>
		<logic:empty name="ProdSupportForm" property="statusBox">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">STATUSCD</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="statusCd" />&nbsp;</font></td>
		</logic:empty>
	</tr>
	<tr>	
		<logic:notEmpty name="ProdSupportForm" property="substatusBox">
		<logic:equal name="ProdSupportForm" property="substatusBox" value="999">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SUBSTATUSCD</font></td>
		<td><font size="-1">&nbsp;</font></td>
		</logic:equal>
		<logic:notEqual name="ProdSupportForm" property="substatusBox" value="999">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SUBSTATUSCD</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="substatusBox" />&nbsp;</font></td>
		</logic:notEqual>
		<td>
		<font color="red"><i>Code updated, previous value: </i><bean:write  name="juvprogrefs" property="statusSubCd" /></font>
		</td>
		</logic:notEmpty>
		<logic:empty name="ProdSupportForm" property="substatusBox">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SUBSTATUSCD</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="statusSubCd" />&nbsp;</font></td>
		</logic:empty>
	</tr>
	
	<tr>
		<logic:notEmpty name="ProdSupportForm" property="outcomeBox">
		<logic:equal name="ProdSupportForm" property="outcomeBox" value="999">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">OUTCOMECD</font></td>
		<td><font size="-1">&nbsp;</font></td>
		</logic:equal>
		<logic:notEqual name="ProdSupportForm" property="outcomeBox" value="999">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">OUTCOMECD</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="outcomeBox" />&nbsp;</font></td>
		</logic:notEqual>
		<td>
		<font color="red"><i>Code updated, previous value: </i><bean:write  name="juvprogrefs" property="outcomeCd" /></font>
		</td>
		</logic:notEmpty>
		<logic:empty name="ProdSupportForm" property="outcomeBox">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">OUTCOMECD</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="outcomeCd" />&nbsp;</font></td>
		</logic:empty>
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
		<font color="red"><i>Code updated, previous value: </i><bean:write  name="juvprogrefs" property="outcomeDescCd" /></font>
		</td>
		</logic:notEmpty>
		<logic:empty name="ProdSupportForm" property="outcomeDescBox">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SPRVNOUTCOMEDESCCD</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="outcomeDescCd" />&nbsp;</font></td>
		</logic:empty>
	</tr>
	
	<tr>	
		<logic:notEmpty name="ProdSupportForm" property="providerPgmDescBox">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PROVPROGRAM_ID</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="providerPgmDescBox" />&nbsp;</font></td>
		<td>
		<font color="red"><i>Code updated, previous value: </i><bean:write  name="juvprogrefs" property="providerProgramId" /></font>
		</td>
		</logic:notEmpty>
		<logic:empty name="ProdSupportForm" property="providerPgmDescBox">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PROVPROGRAM_ID</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="providerProgramId" />&nbsp;</font></td>
		</logic:empty>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LASTACTIONDATE</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="lastActionDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="createUserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATDATE</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="updateUser" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="createJIMS2UserID" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
		
	</table>
	</logic:iterate>
	</logic:notEmpty>	
</html:form>


	<table align="center" border="0" width="500">
		<tr>
			<td colspan="1" align="right"><html:form method="post"
					action="/MainMenu" onsubmit="return this;">
					<html:submit onclick="return this;" value="Back to Main Menu" />
				</html:form>
			</td>
			<td colspan="1" align="left"><html:form
					action="/UpdateReferralQuery?clr=Y" onsubmit="return this;">
					<html:submit value="Back to Search" />
				</html:form>
			</td>
		</tr>
	</table>
</body>
</html:html>