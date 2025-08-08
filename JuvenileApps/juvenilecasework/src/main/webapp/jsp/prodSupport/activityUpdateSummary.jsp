<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/activityUpdateSummary.jsp</title>
</head>

<body>
	
<html:form method="post" action="/ActivityDeleteQuery" onsubmit="return this;">

<h2 align="center">Update Activity Summary</h2>
<hr>

<p align="center"><font color="green"><b>Activity # 
<bean:write name="ProdSupportForm" property="activityId" /> was successfully Updated.</b></font></p>
<p align="center"><b>The following is a list of changes made by this action. This is for auditing purposes.
If these changes were made in error, please provide this list to the JIMS Data Team to restore the data.</b></p> 
<logic:notEqual name="ProdSupportForm" property="originalactivityResp.casefileId" value="">
	<bean:define id="oldcasefileId" name="ProdSupportForm" property="originalactivityResp.casefileId"/>
</logic:notEqual>
<logic:equal name="ProdSupportForm" property="originalactivityResp.casefileId" value="">
	<bean:define id="oldcasefileId" value=""/>
</logic:equal>
<logic:notEqual name="ProdSupportForm" property="originalactivityResp.codeId" value="">
	<bean:define id="oldcodeId" name="ProdSupportForm" property="originalactivityResp.codeId"/>
</logic:notEqual>
<logic:equal name="ProdSupportForm" property="originalactivityResp.codeId" value="">
	<bean:define id="oldcodeId" value=""/>
</logic:equal>
<logic:notEqual name="ProdSupportForm" property="originalactivityResp.comments" value="">
	<bean:define id="oldcomments" name="ProdSupportForm" property="originalactivityResp.comments"/>
</logic:notEqual>
<logic:equal name="ProdSupportForm" property="originalactivityResp.comments" value="">
	<bean:define id="oldcomments" value=""/>
</logic:equal>
<logic:notEqual name="ProdSupportForm" property="originalactivityResp.updateComments" value="">
	<bean:define id="oldupdateComments" name="ProdSupportForm" property="originalactivityResp.updateComments"/>
</logic:notEqual>
<logic:equal name="ProdSupportForm" property="originalactivityResp.updateComments" value="">
	<bean:define id="oldupdateComments" value=""/>
</logic:equal>
<logic:notEqual name="ProdSupportForm" property="originalactivityResp.activityDate" value="">
	<bean:define id="oldactivityDate" name="ProdSupportForm" property="originalactivityResp.activityDate"/>
</logic:notEqual>
<logic:equal name="ProdSupportForm" property="originalactivityResp.activityDate" value="">
	<bean:define id="oldactivityDate" value=""/>
</logic:equal>
<logic:notEqual name="ProdSupportForm" property="originalactivityResp.detentionId" value="">
	<bean:define id="olddetentionId" name="ProdSupportForm" property="originalactivityResp.detentionId"/>
</logic:notEqual>
<logic:equal name="ProdSupportForm" property="originalactivityResp.detentionId" value="">
	<bean:define id="olddetentionId" value=""/>
</logic:equal>
<logic:notEqual name="ProdSupportForm" property="originalactivityResp.detentionTime" value="">
	<bean:define id="olddetentionTime" name="ProdSupportForm" property="originalactivityResp.detentionTime"/>
</logic:notEqual>
<logic:equal name="ProdSupportForm" property="originalactivityResp.detentionTime" value="">
	<bean:define id="olddetentionTime" value=""/>
</logic:equal>
<logic:notEqual name="ProdSupportForm" property="originalactivityResp.activityTime" value="">
	<bean:define id="oldactivityTime" name="ProdSupportForm" property="originalactivityResp.activityTime"/>
</logic:notEqual>
<logic:equal name="ProdSupportForm" property="originalactivityResp.activityTime" value="">
	<bean:define id="oldactivityTime" value=""/>
</logic:equal>
<logic:notEqual name="ProdSupportForm" property="originalactivityResp.activityendTime" value="">
	<bean:define id="oldactivityendTime" name="ProdSupportForm" property="originalactivityResp.activityendTime"/>
</logic:notEqual>
<logic:equal name="ProdSupportForm" property="originalactivityResp.activityendTime" value="">
	<bean:define id="oldactivityendTime" value=""/>
</logic:equal>
<!-- show the table -->
<table class="resultTbl" border="1" width="1000" align="center">
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACTIVITY_ID</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="activityResp.activityId" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="activityResp.casefileId" />&nbsp;</font></td>
		<%-- <logic:notEqual name="ProdSupportForm" property="activityResp.casefileId" value='<bean:write name="ProdSupportForm" property="originalactivityResp.casefileId"/>'> --%>
		<logic:notEqual name="ProdSupportForm" property="activityResp.casefileId" value='${oldcasefileId}'><%-- value="<%=(String)oldcasefileId%>"  --%>
			<td><font color="red"><i>CasefileId changed, previous value: </i><bean:write name="ProdSupportForm" property="originalactivityResp.casefileId"/>&nbsp;</font></td>
		</logic:notEqual>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACTIVITYCD</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="activityResp.codeId" />&nbsp;</font></td>
		<logic:notEqual name="ProdSupportForm" property="activityResp.codeId" value='${oldcodeId}'><%-- value="<%=(String)oldcodeId%>" --%>
			<td><font color="red"><i>ActivityCD changed, previous value: </i><bean:write name="ProdSupportForm" property="originalactivityResp.codeId"/>&nbsp;</font></td>
		</logic:notEqual>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">COMMENTS</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="activityResp.comments" />&nbsp;</font></td>
		<logic:notEqual name="ProdSupportForm" property="activityResp.comments" value='${oldcomments}' > <%-- value="<%=(String)oldcomments%>" --%>
			<td><font color="red"><i>Comments changed, previous value: </i><bean:write name="ProdSupportForm" property="originalactivityResp.comments"/>&nbsp;</font></td>
		</logic:notEqual>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATE COMMENTS</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="activityResp.updateComments" />&nbsp;</font></td>
		<logic:notEqual name="ProdSupportForm" property="activityResp.updateComments" value='${oldupdateComments}'> <%-- value="<%=(String)oldupdateComments%>" --%>
			<td><font color="red"><i>UpdateComments changed, previous value: </i><bean:write name="ProdSupportForm" property="originalactivityResp.updateComments"/>&nbsp;</font></td>
		</logic:notEqual>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">INACTIVEDATE</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="activityResp.inactiveDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TITLE</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="activityResp.title" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACTIVITYDATE</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="activityResp.activityDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<logic:notEqual name="ProdSupportForm" property="activityResp.activityDate" value='${oldactivityDate}'>
			<td><font color="red"><i>Date changed, previous value: </i><bean:write name="ProdSupportForm" property="originalactivityResp.activityDate"  formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
		</logic:notEqual>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DETENTION_ID</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="activityResp.detentionId" />&nbsp;</font></td>
		<logic:notEqual name="ProdSupportForm" property="activityResp.detentionId" value='${olddetentionId}' > <%-- value="<%=(String)olddetentionId%>" --%>
			<td><font color="red"><i>JJS_DETENTION_ID changed, previous value: </i><bean:write name="ProdSupportForm" property="originalactivityResp.detentionId"/>&nbsp;</font></td>
		</logic:notEqual>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DETENTIONTIME</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="activityResp.detentionTime" />&nbsp;</font></td>
		<logic:notEqual name="ProdSupportForm" property="activityResp.detentionTime" value='${olddetentionTime}' ><%-- value='${olddetentionTime}' --%>
			<td><font color="red"><i>DETENTIONTIME changed, previous value: </i><bean:write name="ProdSupportForm" property="originalactivityResp.detentionTime"/>&nbsp;</font></td>
		</logic:notEqual>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACTIVITYTIME</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="activityResp.activityTime" />&nbsp;</font></td>
		<logic:notEqual name="ProdSupportForm" property="activityResp.activityTime" value='${oldactivityTime}' ><%-- value="<%=(String)oldactivityTime%>" --%>
			<td><font color="red"><i>ACTIVITYTIME changed, previous value: </i><bean:write name="ProdSupportForm" property="originalactivityResp.activityTime"/>&nbsp;</font></td>
		</logic:notEqual>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACTIVITYENDTIME</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="activityResp.activityendTime" />&nbsp;</font></td>
		<logic:notEqual name="ProdSupportForm" property="activityResp.activityendTime" value='${oldactivityendTime}' ><%-- value="<%=(String)oldactivityendTime%>" --%>
			<td><font color="red"><i>ActivityEndTime changed, previous value: </i><bean:write name="ProdSupportForm" property="originalactivityResp.activityendTime"/>&nbsp;</font></td>
		</logic:notEqual>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LATITUDE</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="activityResp.latitude" />&nbsp;</font></td>
		</tr>
		<tr>		
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LONGITUDE</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="activityResp.longitude" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="activityResp.createUserID" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="activityResp.createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="activityResp.updateUser" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="activityResp.updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="activityResp.createJIMS2UserID" />&nbsp;</font></td>
		</tr>
		<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="ProdSupportForm" property="activityResp.updateJIMS2UserID" />&nbsp;</font></td>
		</tr>
		</table>
</html:form>
	<table align="center" border="0" width="500">
		<tr>
		<td>&nbsp;</td>
		</tr>
		<tr>
		<td colspan="2" align="center">
		<html:form method="post" action="/ActivityUpdateQuery.do?clr=Y" onsubmit="return this;">
		<html:submit onclick="return this;" value="Update Another Activity"/>
		</html:form>
		</td>
		</tr>

    </table>    
	
	<table align="center" border="0" width="500">
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