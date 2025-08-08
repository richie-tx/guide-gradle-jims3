<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/updateReferralDateSummary.jsp</title>
</head>

<body>
	
<html:form method="post" action="/DeleteCasefileQuery" onsubmit="return this;">

<h2 align="center">Update Referral Date Summary</h2>
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
	<logic:notEqual name="ProdSupportForm" property="isCaseFileIdChanged" value="false">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="newcasefileId" />&nbsp;</font></td>
		<TD>
		<font color="red"><i>Changed, previous value: </i><bean:write  name="juvprogrefs" property="caseFileId" /></font>
		</td>
	</logic:notEqual>
	<logic:equal name="ProdSupportForm" property="isCaseFileIdChanged" value="false">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="caseFileId" />&nbsp;</font></td>
	</logic:equal>
	</tr>
	<tr>
		<logic:equal name="ProdSupportForm" property="isProgramReferralAssignmentDateChanged" value="true">	
			<td bgcolor="gray"><font color="white" face="bold" size="-1">PROGRAM REFERRAL ASSIGNMENT DATE</font></td>
			<td><font size="-1"><bean:write name="ProdSupportForm" property="programReferralAssignmentDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
			<td>
				<font color="red"><i>Date changed, previous value: </i><bean:write  name="juvprogrefs" property="programReferralAssignmentDate" formatKey="date.format.mmddyyyy" /></font>
			</td>
		</logic:equal>
		<logic:equal name="ProdSupportForm" property="isProgramReferralAssignmentDateChanged" value="false">
			<td bgcolor="gray"><font color="white" face="bold" size="-1">PROGRAM REFERRAL ASSIGNMENT DATE</font></td>
			<td><font size="-1"><bean:write  name="juvprogrefs" property="programReferralAssignmentDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		</logic:equal>
	</tr>
	<tr>
		<logic:equal name="ProdSupportForm" property="isControllingReferralChanged" value="true">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CONTROLLING REFERRAL</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="newControllingReferral"/>&nbsp;</font></td>
		<td>
		<font color="red"><i>Value changed, previous value: </i><bean:write  name="juvprogrefs" property="controllingReferralNum"/></font>
		</td>
		</logic:equal>
		<logic:equal name="ProdSupportForm" property="isControllingReferralChanged" value="false">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CONTROLLING REFERRAL</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="controllingReferralNum"/>&nbsp;</font></td>
		</logic:equal>
	</tr>	
	<tr>
		<logic:equal name="ProdSupportForm" property="isRefAckDateChanged" value="true">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACKDATE</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="refAckDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<TD>
		<font color="red"><i>Date changed, previous value: </i><bean:write  name="juvprogrefs" property="ackDate" formatKey="date.format.mmddyyyy" /></font>
		</td>
		</logic:equal>
		<logic:equal name="ProdSupportForm" property="isRefAckDateChanged" value="false">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACKDATE</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="ackDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		</logic:equal>
	</tr>
	<tr>
		<logic:equal name="ProdSupportForm" property="isRefBeginDateChanged" value="true">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">BEGINDATE</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="refBeginDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<TD>
		<font color="red"><i>Date changed, previous value: </i><bean:write  name="juvprogrefs" property="beginDate" formatKey="date.format.mmddyyyy" /></font>
		</td>
		</logic:equal>
		<logic:equal name="ProdSupportForm" property="isRefBeginDateChanged" value="false">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">BEGINDATE</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="beginDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		</logic:equal>
	</tr>
	<tr>
		<logic:equal name="ProdSupportForm" property="isRefEndDateChanged" value="true">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ENDDATE</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="refEndDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td>
		<font color="red"><i>Date changed, previous value: </i><bean:write  name="juvprogrefs" property="endDate" formatKey="date.format.mmddyyyy" /></font>
		</td>
		</logic:equal>
		<logic:equal name="ProdSupportForm" property="isRefEndDateChanged" value="false">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ENDDATE</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="endDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		</logic:equal>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSIGNEDHOURS</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="assignedHours" />&nbsp;</font></td>
	</tr>
	<tr>
		<logic:equal name="ProdSupportForm" property="isRefSentDateChanged" value="true">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SENTDATE</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="refSentDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<TD>
		<font color="red"><i>Date changed, previous value: </i><bean:write  name="juvprogrefs" property="sentDate" formatKey="date.format.mmddyyyy" /></font>
		</td>
		</logic:equal>
		<logic:equal name="ProdSupportForm" property="isRefSentDateChanged" value="false">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SENTDATE</font></td>
		<td><font size="-1"><bean:write name="juvprogrefs" property="sentDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		</logic:equal>
	</tr>
	<tr>
		<logic:equal name="ProdSupportForm" property="isfundSourceChanged" value="true">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">FUND SOURCE</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="newFundSource" />&nbsp;</font></td>
		<TD>
		<font color="red"><i>Value changed, previous value: </i><bean:write  name="juvprogrefs" property="fundSource"  /></font>
		</td>
		</logic:equal>
		<logic:equal name="ProdSupportForm" property="isfundSourceChanged" value="false">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">FUND SOURCE</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="fundSource" />&nbsp;</font></td>
		</logic:equal>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">STATUSCD</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="statusCd" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SUBSTATUSCD</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="statusSubCd" />&nbsp;</font></td>
	</tr>
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">OUTCOMECD</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="outcomeCd" />&nbsp;</font></td>
	</tr>

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PROVPROGRAM_ID</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="providerProgramId" />&nbsp;</font></td>
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
			<td colspan="1" align="right"><html:form method="post" action="/MainMenu" onsubmit="return this;">
		<html:submit onclick="return this;" value="Back to Main Menu"/>
		</html:form>
			</td>
			<td colspan="1" align="left"><html:form
					action="/UpdateReferralDateQuery?clr=Y" onsubmit="return this;">
					<html:submit value="Back to Search" />
				</html:form>
			</td>
		</tr>
	</table>
    
</body>
</html:html>