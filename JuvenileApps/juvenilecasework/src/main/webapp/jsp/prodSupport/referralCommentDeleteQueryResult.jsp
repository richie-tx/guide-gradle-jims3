<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/referralCommentDeleteQueryResult.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>

<script language="javascript">
$("#document").ready( function() {	
		$("#deleteRecords").on(
			"click",
			function() {		
		
				var commentId = $(
						'input:radio[name=programReferralCommentId]:checked')
						.val();	
				if(	commentId == null ||commentId == "" )
				{
					alert("Please select a comment to delete!" );
					return false;	
				}		
				else if (commentId != "") {
					$("#tempNum").val(commentId); 					
				if (confirmDelete()) {
										spinner();
										$('form').attr('action','/JuvenileCasework/PerformReferralCommentDelete.do?&commentId='+ commentId);
										$('form').submit();
									}
					return true;
					}

					});
		})
	function setTableId(idName) {
		document.forms[0].tableId.value = idName;
	}

	function confirmDelete() {
		if (confirm('Are you sure you want to delete the referral comment record?')) {
			return true;
		} else {
			return false;
		}
	}
</script>

</head>

<html:form styleId="performReferralCommentDelete" action="/PerformReferralCommentDelete" onsubmit="return this;">

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


<div>
	
	<h2 align="center">Program Referral Comments for Referral ID = 
			<bean:write name="ProdSupportForm" property="juvprogrefId" /></h2>
	<br>
	<p align="center">
				<b><i>Select the radio button next to the record <br>
						you want to delete and click the Delete button.</i> </b>
			</p>
	<!-- <h4 align="center"><i>The following rows will be <font color="red">DELETED</font>.</i></h4> -->

    
	
	<!-- ProgRefComments Section -->
	
	<logic:notEmpty	name="ProdSupportForm" property="progrefcomments">
	
	<p>&nbsp;</p>	
	
	<table class="resultTbl" border="1" width="700" align="center">
	
	<tr>
		<td bgcolor="gray"></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PRGREFCOMMENTS_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">COMMENTS</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">USERNAME</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">COMMENTS DATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVPROGREF_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATE USER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREAE DATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATE USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATE DATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="progrefcomments" name="ProdSupportForm" property="progrefcomments">
	<tr>
	<td width="1%"><input type="radio" name="programReferralCommentId"
										value="<bean:write name='progrefcomments' property='programReferralCommentId'/>" />
									</td>
		<td><font size="-1"><bean:write  name="progrefcomments" property="programReferralCommentId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="progrefcomments" property="commentText" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="progrefcomments" property="userName" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="progrefcomments" property="commentsDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="progrefcomments" property="programReferralId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="progrefcomments" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="progrefcomments" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="progrefcomments" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="progrefcomments" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="progrefcomments" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="progrefcomments" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	<html:hidden styleId="tempNum" name="ProdSupportForm"
			property="commentId" />
	<logic:empty name="ProdSupportForm" property="progrefcomments">
	<br>
	<table align="center" border="1" width="700">
		<tr>
		<td>
	   <h3 align="center"><font color="green"><i>No Associated ProgRefComments</i></font></h3>
	   </td>
	   </tr>
	   </table>
	</logic:empty>	
	
</div>
	
<br/>

<table align="center"">
<tr>

<td>&nbsp;</td>
<logic:notEmpty	name="ProdSupportForm" property="progrefcomments">
<td>

<p align="center">
	<input type="button" id="deleteRecords"
						 name="Delete Records"
						  value="Delete Record" />
</p>

</td>
</logic:notEmpty>
</tr>
</table>
</html:form>


<html:form action="/DeleteReferralCommentQuery?clr=Y" onsubmit="return this;">
<table align="center"">
	<tr>
		<td>
		<html:submit value="Back to Query"/>
		</td>
	</tr>
</table>
</html:form>

</html:html>