<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/referralDeleteSummary.jsp</title>
</head>

<body>

	
<html:form method="post" action="/MainMenu" onsubmit="return this;">

<h2 align="center">Delete Program Referral Summary</h2>
<hr>

<p align="center"><font color="green"><b>Program Referral Comment was successfully deleted.</b></font></p>
	
 	<logic:notEmpty	name="ProdSupportForm" property="referralComments">
	
	
	<table border="1" width="700" align="center">	
	<logic:iterate id="progrefcomment" name="ProdSupportForm" property="referralComments">
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PRGREFCOMMENTSID</font></td>
		<td><font size="-1"><bean:write  name="progrefcomment" property="programReferralCommentId" />&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">COMMENTS</font></td>
		<td><font size="-1"><bean:write  name="progrefcomment" property="commentText" />&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">USER NAME</font></td>
		<td><font size="-1"><bean:write  name="progrefcomment" property="userName" />&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">COMMENTS DATE</font></td>
		<td><font size="-1"><bean:write  name="progrefcomment" property="commentsDate" />&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVPROGREF ID</font></td>
		<td><font size="-1"><bean:write  name="progrefcomment" property="programReferralId" />&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATE USER</font></td>
		<td><font size="-1"><bean:write  name="progrefcomment" property="createUserID" />&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATE DATE</font></td>
		<td><font size="-1"><bean:write  name="progrefcomment" property="createDate" />&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATE USER</font></td>
		<td><font size="-1"><bean:write  name="progrefcomment" property="updateUser" />&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATE DATE</font></td>
		<td><font size="-1"><bean:write  name="progrefcomment" property="updateDate" />&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATE JIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="progrefcomment" property="createJIMS2UserID" />&nbsp;</font></td>		
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATE JIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="progrefcomment" property="updateJIMS2UserID" />&nbsp;</font></td>		
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty> 

	
		
	<table align="center" border="0" width="500">
		<tr>
		<td colspan="2" align="center">

		<html:submit onclick="return this;" value="Back to Main Menu"/>
	
		</td>
		</tr>

    </table>    
    </html:form>
</body>
</html:html>