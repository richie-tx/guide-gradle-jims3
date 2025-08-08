<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/assessUpdateSummary.jsp</title>
</head>

<body>

<html:form method="post" action="/DeleteCasefileQuery" onsubmit="return this;">

<h2 align="center">Update Assessment Summary</h2>
<hr>

<p align="center"><font color="green"><b>Assessment number 
<bean:write name="ProdSupportForm" property="riskanalysisId" /> was successfully updated.</b></font></p>
<p align="center"><b>The following is a list of records affected by this change. This is for auditing purposes.<br> 
If this delete was made in error, please provide this list to the JIMS Data Team to restore the data.</b></p>
</html:form>


	<logic:notEmpty name="ProdSupportForm" property="riskanalyses">

		<p>&nbsp;</p>


		<logic:iterate id="riskanalyses" name="ProdSupportForm"
			property="riskanalyses">
			<table border="1" width="750" align="center">

				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">RISKANALYSIS_ID</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="riskAnalysisId" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">FINALSCORE</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="finalScore" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSESSMENTTYPE</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="assessmentType" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
					<td><font size="-1"><bean:write name="ProdSupportForm"
						property="newcasefileId" />&nbsp;</font></td>
						
					<td><font color="red"><i>CasefileId changed, previous value: </i><bean:write name="riskanalyses" property="caseFileId"/>&nbsp;</font></td>
						
				</td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="juvenileId" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">JPOUSERID</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="jpoUserId" />&nbsp;</font></td>
				</tr>
				<tr>	
		<logic:notEqual name="ProdSupportForm" property="newActDate" value="">	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DATEENTERED</font></td>
		<td><font size="-1"><bean:write name="ProdSupportForm" property="newActDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<TD>
		<font color="red"><i>Date changed, previous value: </i><bean:write  name="riskanalyses" property="dateEntered" formatKey="date.format.mmddyyyy" /></font>
		</td>
		</logic:notEqual>
		<logic:equal name="ProdSupportForm" property="newActDate" value="">
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DATEENTERED</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="dateEntered" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		</logic:equal>	
	</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="createUserID" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEUSER</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="updateUser" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEDATE</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="createJIMS2UserID" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
					<td><font size="-1"><bean:write name="riskanalyses"
						property="updateJIMS2UserID" />&nbsp;</font></td>
				</tr>
			</table>
		</logic:iterate>

	</logic:notEmpty>
	
	<table align="center" border="0" width="500">
		<tr>
		<td>&nbsp;</td>
		</tr>
		<tr>
		<td colspan="2" align="center">
		<html:form action="/AssessUpdateQuery?clr=Y"
				onsubmit="return this;">
				<html:submit value="Update Another Assessment" />
		</html:form>
		</td>
		</tr>
    </table> 
	
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