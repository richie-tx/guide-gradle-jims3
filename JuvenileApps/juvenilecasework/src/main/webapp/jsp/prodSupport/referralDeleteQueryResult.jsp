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
<title>Juvenile Casework -/prodSupport/referralDeleteQueryResult.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>

<script language="javascript">
$("#document").ready( function() {
	$("#deleteRecords").click(function(){
		if ( confirmDelete () ) {
			spinner();
			$("#performReferralDelete").submit();
		}
	})
})
function setTableId(idName){
     document.forms[0].tableId.value = idName;
}

function confirmDelete(){
	if( confirm('Are you sure you want to delete the referral and all its records?') ) {
		return true;	
	} else {
		return false;
	}
}

</script>

</head>

<html:form styleId="performReferralDelete" action="/PerformReferralDelete" onsubmit="return this;">

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
	
	<h2 align="center">Results for Referral ID = 
			<bean:write name="ProdSupportForm" property="juvprogrefId" /></h2>
	<br>
	<h4 align="center"><i>The following rows will be <font color="red">DELETED</font>.</i></h4>

	<logic:notEmpty	name="ProdSupportForm" property="juvprogrefs">
	
	<p>&nbsp;</p>
	<h3 align="left">Associated JuvProgRefs</h3>
	
	<table class="resultTbl" border="1" width="700">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVPROGREF_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACKDATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">BEGINDATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ENDDATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSIGNEDHOURS</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SENTDATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">STATUSCD</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SUBSTATUSCD</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">OUTCOMECD</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PROVPROGRAM_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">LASTACTIONDATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="juvprogrefs" name="ProdSupportForm" property="juvprogrefs">
	<tr>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="juvenileProgramReferralId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="caseFileId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="ackDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="beginDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="endDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="assignedHours" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="sentDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="statusCd" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="statusSubCd" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="outcomeCd" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="providerProgramId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="lastActionDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	
	<logic:empty name="ProdSupportForm" property="juvprogrefs">
	<br>
	<table  align="center" border="1" width="700">
		<tr>
		<td>
	   <h3 align="center"><font color="green"><i>No Associated JuvProgRefs.</i></font></h3>
	   </td>
	   </tr>
	   </table>
	</logic:empty>
	     
	
	<!-- ProgRefComments Section -->
	
	<logic:notEmpty	name="ProdSupportForm" property="progrefcomments">
	
	<p>&nbsp;</p>
	<h3 align="left">Associated ProgRefComments</h3>
	
	<table class="resultTbl" border="1" width="700">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PRGREFCOMMENTS_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">COMMENTSTXT</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">USERNAME</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">COMMENTSDATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVPROGREF_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="progrefcomments" name="ProdSupportForm" property="progrefcomments">
	<tr>
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

	<!-- EventReferrals Section -->
	
	<logic:notEmpty	name="ProdSupportForm" property="eventreferrals">
	
	<p>&nbsp;</p>
	<h3 align="left">Associated Event Referrals</h3>
	
	<table class="resultTbl" border="1" width="700">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">EVENTREFERRAL_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVPROGREF_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SERVEVENT_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="eventreferrals" name="ProdSupportForm" property="eventreferrals">
	<tr>
		<td><font size="-1"><bean:write  name="eventreferrals" property="eventReferralId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="eventreferrals" property="programReferralId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="eventreferrals" property="serviceEventId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="eventreferrals" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="eventreferrals" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="eventreferrals" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="eventreferrals" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="eventreferrals" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="eventreferrals" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	
	<logic:empty name="ProdSupportForm" property="eventreferrals">
	<br>
	<table align="center" border="1" width="700">
		<tr>
		<td>
	   <h3 align="center"><font color="green"><i>No Associated Event Referrals</i></font></h3>
	   </td>
	   </tr>
	   </table>
	</logic:empty>
	
	<!-- Progrfasgnhists Section -->
	
	<logic:notEmpty	name="ProdSupportForm" property="progrfasgnhists">
	
	<p>&nbsp;</p>
	<h3 align="left">Associated ProgRefAsgnHists</h3>
	
	<table class="resultTbl" border="1" width="700">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PROGRFASGNHIST_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVPROGREF_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PRGREFASGNDATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="progrfasgnhists" name="ProdSupportForm" property="progrfasgnhists">
	<tr>
		<td><font size="-1"><bean:write  name="progrfasgnhists" property="programReferralAssignmentHistoryId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="progrfasgnhists" property="programReferralId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="progrfasgnhists" property="casefileId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="progrfasgnhists" property="programReferralAssignmentDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="progrfasgnhists" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="progrfasgnhists" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="progrfasgnhists" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="progrfasgnhists" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="progrfasgnhists" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="progrfasgnhists" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	
	<logic:empty name="ProdSupportForm" property="progrfasgnhists">
	<br>
	<table align="center" border="1" width="700">
		<tr>
		<td>
	   <h3 align="center"><font color="green"><i>No Associated ProgRefAsgnHists</i></font></h3>
	   </td>
	   </tr>
	   </table>
	</logic:empty>
	
	<!-- Service Attendance Events Section -->
	
	<logic:notEmpty	name="ProdSupportForm" property="servattends">
	
	<p>&nbsp;</p>
	<h3 align="left">Associated Service Attendance Events</h3>
	
	<table class="resultTbl" border="1" width="700">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SERVICEEVENTATTENDANCE_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SERVICEEVENT_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ATTENDANCESTATUS_CD</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="servattends" name="ProdSupportForm" property="servattends">
	<tr>
		<td><font size="-1"><bean:write  name="servattends" property="serviceEventAttendanceId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="servattends" property="serviceEventId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="servattends" property="juvenileId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="servattends" property="attendanceStatusCd" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="servattends" property="createUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="servattends" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="servattends" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="servattends" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="servattends" property="createJims2User" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="servattends" property="updateJims2User" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	
	<logic:empty name="ProdSupportForm" property="servattends">
	<br>
	<table align="center" border="1" width="700">
		<tr>
		<td>
	   <h3 align="center"><font color="green"><i>No Associated servattends</i></font></h3>
	   </td>
	   </tr>
	   </table>
	</logic:empty>
	
		<!-- Calendar Event Contexts Section -->
	
	<logic:notEmpty	name="ProdSupportForm" property="caleventconts">
	
	<p>&nbsp;</p>
	<h3 align="left">Calendar Event Contexts</h3>
	
	<table class="resultTbl" border="1" width="700">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SERVICEEVENTCONTEXT_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PROBATIONOFFICER_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CALENDAREVENT_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="caleventconts" name="ProdSupportForm" property="caleventconts">
	<tr>
		<td><font size="-1"><bean:write  name="caleventconts" property="calendarEventContextId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caleventconts" property="probationOfficerId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caleventconts" property="caseFileId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caleventconts" property="juvenileId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caleventconts" property="calendarEventId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caleventconts" property="createUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caleventconts" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caleventconts" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caleventconts" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caleventconts" property="createJims2User" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caleventconts" property="updateJims2User" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	
	<logic:empty name="ProdSupportForm" property="caleventconts">
	<br>
	<table align="center" border="1" width="700">
		<tr>
		<td>
	   <h3 align="center"><font color="green"><i>No Associated Calendar Event Contexts</i></font></h3>
	   </td>
	   </tr>
	   </table>
	</logic:empty>	
	
</div>
	
<br/>

<table align="center"">
<tr>

<td>&nbsp;</td>

<td>

<p align="center">
	<input type="button" id="deleteRecords"
						 name="Delete Records"
						  value="Delete Records" />
</p>

</td>

</tr>
</table>
</html:form>


<html:form action="/DeleteReferralQuery?clr=Y" onsubmit="return this;">
<table align="center"">
	<tr>
		<td>
		<html:submit value="Back to Query"/>
		</td>
	</tr>
</table>
</html:form>

</html:html>