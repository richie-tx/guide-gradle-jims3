<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<style type="text/css">
.hidden{
            display:none;
}
.visible{
            display:block;
}
</style>

<meta name="ROBOTS" content="NOINDEX">
<title>Juvenile Casework -/prodSupport/caseDetailQueryResults.jsp</title>

<script language="javascript">
function showTable(){
      var what = document.getElementsByName("tableId");
      document.getElementById(what[0].value).className='visible';
}

</script>

</head>

<!-- Tables remain hidden unless their show attribute is ticked by the previous page. -->

<body onLoad="showTable()">

<html:form action="/DeleteCasefileQuery" onsubmit="return this;">

<input type="hidden" name="tableId" id="tableId" value="<bean:write name="ProdSupportForm" property="tableId" />">

<H1 align="center">Table Detail</H1>
<hr>

	<span id="act" class="hidden">
	<logic:notEmpty	name="ProdSupportForm" property="activities">
	<h3>Activities</h3>
	
<table border="1" width="700">
	<tr>
	    	  
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">ACTIVITY_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ACTIVITYCD</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">INACTIVEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">TITLE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ACTIVITYDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	
	</tr>
	
    <logic:iterate id="activities" name="ProdSupportForm" property="activities">
	<tr>
	    <td><font size="-1"><bean:write  name="activities" property="activityId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="activities" property="codeId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="activities" property="inactiveDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="activities" property="title" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="activities" property="activityDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="activities" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="activities" property="createDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="activities" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="activities" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="activities" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="activities" property="updateJIMS2UserID" />&nbsp;</font></td>
	
	</tr>
	</logic:iterate>	
</table>
	</logic:notEmpty>
	</span>
	
	<span id="case" class="hidden">
	<logic:notEmpty	name="ProdSupportForm" property="casefiles">
	<h3 align="left">Associated Casefiles</h3>
	
	<table border="1" width="700">
	
	<tr>
	    <td bgcolor="gray"> <font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
	    <td bgcolor="gray"> <font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ACTIVATIONDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CASESTATUSCD</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">SPRVSIONTYPECD</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">OFFICER_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">SUPRVSIONENDDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
    <logic:iterate id="casefiles" name="ProdSupportForm" property="casefiles">
	<tr>
        <td><font size="-1"><bean:write  name="casefiles" property="supervisionNum" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="juvenileNum" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="activationDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="caseStatusId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="supervisionTypeId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="probationOfficeId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="supervisionEndDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
	
	</table>
	</logic:notEmpty>
	</span>
	
	<span id="assign" class="hidden">
	<logic:notEmpty	name="ProdSupportForm" property="assignments">
	
	<p>&nbsp;</p>
	<h3 align="left">Associated Assignments</h3>
	
	<table border="1" width="700">
	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ASSIGNMENT_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">JPOUSERID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ASSMNTADDDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">REFERRALNUMBER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CASELOADMGRCD</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">SERVICEUNITCD</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ASSMNTLEVELCD</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="assignments" name="ProdSupportForm" property="assignments">
	<tr>
		<td><font size="-1"><bean:write  name="assignments" property="assignmentId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="jpoUserId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="assignmentDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="referralNum" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="caseloadManagerId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="serviceUnitId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="assessmentLevelId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	</span>

<span id="assignhist" class="hidden">
	<logic:notEmpty	name="ProdSupportForm" property="assnmnthists">
	
	<p>&nbsp;</p>
	<h3 align="left">Associated JPOAssnmntHists</h3>
	
	<table border="1" width="700">
	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">JPOASSNMNTHIST_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">JPOASSNMNTDT</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">OFFICER_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="assnmnthists" name="ProdSupportForm" property="assnmnthists">
	<tr>
		<td><font size="-1"><bean:write  name="assnmnthists" property="jpoAssignmentHistoryId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assnmnthists" property="jpoAssignmentDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assnmnthists" property="casefileId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assnmnthists" property="officerProfileId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assnmnthists" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assnmnthists" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assnmnthists" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assnmnthists" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assnmnthists" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="assnmnthists" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	</span>


	<span id="plan" class="hidden">
	<logic:notEmpty	name="ProdSupportForm" property="caseplans">
	
	<p>&nbsp;</p>
	<h3 align="left">Associated Caseplans</h3>
	
	<table border="1" width="700">
	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CASEPLAN_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">REVIEWDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">PLANSTATUSCD</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">PLACEMENT_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="caseplans" name="ProdSupportForm" property="caseplans">
	<tr>
		<td><font size="-1"><bean:write  name="caseplans" property="caseplanID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caseplans" property="reviewDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caseplans" property="statusId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caseplans" property="placementId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caseplans" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caseplans" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caseplans" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caseplans" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caseplans" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caseplans" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	</span>
	
	<span id="closing" class="hidden">
	<logic:notEmpty	name="ProdSupportForm" property="casefileclosings">
	
	<p>&nbsp;</p>
	<h3 align="left">Associated Casefile Closings</h3>
	
	<table border="1" width="700" id="closings">
	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CASFILECLOSNG_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">SUPERVISIONOUTCOME</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CNTROLLINGREFERRAL</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CASFILECLOSNGSTATS</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">SUPERVISIONENDDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">EXPECTEDRELESEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="casefileclosings" name="ProdSupportForm" property="casefileclosings">
	<tr>
		<td><font size="-1"><bean:write  name="casefileclosings" property="casefileClosingInfoId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="supervisionOutcomeId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="controllingReferralId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="casefileClosingStatus" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="supervisionEndDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="expectedReleaseDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefileclosings" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	</span>
	
	<span id="interview" class="hidden">
	<logic:notEmpty	name="ProdSupportForm" property="interviews">
	
	<p>&nbsp;</p>
	<h3 align="left">Associated Interviews</h3>
	
		<table border="1" width="700" id="iviews">
	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">INTERVIEW_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CALEVENT_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">IVIEWDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">IVIEWTYPECD</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">LOCATION_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ADDRESS_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">JUVLOCUNIT_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">STATUSCD</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ISCSTMADDRSVALID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="interviews" name="ProdSupportForm" property="interviews">
	<tr>
		<td><font size="-1"><bean:write  name="interviews" property="interviewId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="interviews" property="calEventId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="interviews" property="interviewDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="interviews" property="interviewTypeId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="interviews" property="locationDescription" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="interviews" property="addressId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="interviews" property="juvLocationUnitId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="interviews" property="interviewStatusCd" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="interviews" property="customAddressValid" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="interviews" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="interviews" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="interviews" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="interviews" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="interviews" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="interviews" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	</span>


	<span id="iviewdoc" class="hidden">
	<logic:notEmpty	name="ProdSupportForm" property="iviewdocs">
	
	<p>&nbsp;</p>
	<h3 align="left">Associated Iviewdocs</h3>
	
	<table border="1" width="700" id="iviewdocs">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">IVIEWDOC_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DOCTYPECD</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="iviewdocs" name="ProdSupportForm" property="iviewdocs">
	<tr>
		<td><font size="-1"><bean:write  name="iviewdocs" property="reportId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="iviewdocs" property="reportType" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="iviewdocs" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="iviewdocs" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="iviewdocs" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="iviewdocs" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="iviewdocs" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="iviewdocs" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	</span>
	
	<span id="risk" class="hidden">
	<logic:notEmpty	name="ProdSupportForm" property="riskanalyses">
	
	<p>&nbsp;</p>
	<h3 align="left">Associated Risk Analyses</h3>
	
	<table border="1" width="700">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RISKANALYSIS_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">FINALSCORE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSESSMENTTYPE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JPOUSERID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DATEENTERED</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="riskanalyses" name="ProdSupportForm" property="riskanalyses">
	<tr>
		<td><font size="-1"><bean:write  name="riskanalyses" property="assessmentID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="finalScore" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="assessmentType" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="juvenileNumber" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="jpoUserId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="assessmentDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	</span>
	
	<span id="responses" class="hidden">
	<logic:notEmpty	name="ProdSupportForm" property="riskresponses">

	<p>&nbsp;</p>
	<h3 align="left">Associated Risk Responses</h3>
	
	<table border="1" width="700">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RISKRESPONSES_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RISKANALYSIS_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RISKWEIGHTRES_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RESPONSETEXT</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="riskresponses" name="ProdSupportForm" property="riskresponses">
	<tr>
		<td><font size="-1"><bean:write  name="riskresponses" property="responseID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskresponses" property="riskAnalysisID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskresponses" property="weight" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskresponses" property="responseText" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskresponses" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskresponses" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskresponses" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskresponses" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskresponses" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskresponses" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>	
	</span>
	
	
	<span id="progref" class="hidden">
	<logic:notEmpty	name="ProdSupportForm" property="juvprogrefs">
	
	<p>&nbsp;</p>
	<h3 align="left">Associated JuvProgRefs</h3>
	
	<table border="1" width="700">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVPROGREF_ID</font></td>
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
	</span>
	
	<span id="bene" class="hidden">
	<logic:notEmpty	name="ProdSupportForm" property="beneasmnts">
	
	<p>&nbsp;</p>
	<h3 align="left">Associated Beneasmnts</h3>
	
	<table border="1" width="700">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">BENEASMNT_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CONSRELATION_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">REQUESTDATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ENTRYDATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TITLE4EOFFICER_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TITLE4EOFFICERNAME</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVRELATIONDESC</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">REQUESTORNAME</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="beneasmnts" name="ProdSupportForm" property="beneasmnts">
	<tr>
		<td><font size="-1"><bean:write  name="beneasmnts" property="assessmentId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="beneasmnts" property="guardianId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="beneasmnts" property="requestDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="beneasmnts" property="entryDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="beneasmnts" property="titleIVeOfficerId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="beneasmnts" property="titleIVeOfficerName" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="beneasmnts" property="pweRelationshipToJuvenile" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="beneasmnts" property="requesterName" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="beneasmnts" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="beneasmnts" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="beneasmnts" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="beneasmnts" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="beneasmnts" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="beneasmnts" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	</span>

	<span id="suprule" class="hidden">	
	<logic:notEmpty	name="ProdSupportForm" property="suprules">
	
	<p>&nbsp;</p>
	<h3 align="left">Associated Suprules</h3>
	
	<table border="1" width="700">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SUPRULE_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">COMPLETIONSTATUSID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">MONITORFREQUENCYID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CONDITION_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">COMPLETIONDATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RULETYPECD</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="suprules" name="ProdSupportForm" property="suprules">
	<tr>
		<td><font size="-1"><bean:write  name="suprules" property="supervisionRuleId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="suprules" property="completionStatusId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="suprules" property="monitorFrequencyId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="suprules" property="conditionId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="suprules" property="completionDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="suprules" property="ruleTypeCd" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="suprules" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="suprules" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="suprules" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="suprules" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="suprules" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="suprules" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	</span>	
	
		<span id="progrfasgnhist" class="hidden">
	<logic:notEmpty	name="ProdSupportForm" property="progrfasgnhists">
			
	<p>&nbsp;</p>
	<h3 align="left">Associated ProgRefAsgnHists</h3>
	
	<table border="1" width="700">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PROGRFASGNHIST_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVPROGREF_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PRGREFASGNDATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
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
	</span>
	
	<span id="cal" class="hidden">
	<logic:notEmpty	name="ProdSupportForm" property="caleventconts">
			
	<p>&nbsp;</p>
	<h3 align="left">Associated CalEventConts</h3>
	
	<table border="1" width="700">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CALEVENTCONT_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CALEVENT_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">OFFICER_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="caleventconts" name="ProdSupportForm" property="caleventconts">
	<tr>
		<td><font size="-1"><bean:write  name="caleventconts" property="calendarEventContextId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caleventconts" property="calendarEventId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caleventconts" property="probationOfficerId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="caleventconts" property="juvenileId" />&nbsp;</font></td>
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
	</span>
	
		<span id="nttask" class="hidden">
	<logic:notEmpty	name="ProdSupportForm" property="nttasks">
			
	<p>&nbsp;</p>
	<h3 align="left">Associated NTTasks</h3>
	
<table border="1" width="700">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TASK_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SEVLEVEL</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SOURCE_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">OWNER_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">STATUSCD</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TASKSUBJECT</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TOPIC</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ACCEPTEDDATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CLOSEDDATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DUEDATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SUBMITTEDDATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="nttasks" name="ProdSupportForm" property="nttasks">
	<tr>
		<td><font size="-1"><bean:write  name="nttasks" property="taskId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="nttasks" property="severityLevel" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="nttasks" property="sourceId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="nttasks" property="ownerId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="nttasks" property="statusId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="nttasks" property="taskSubject" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="nttasks" property="topic" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="nttasks" property="acceptedDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="nttasks" property="closedDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="nttasks" property="dueDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="nttasks" property="submittedDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="nttasks" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="nttasks" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="nttasks" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="nttasks" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="nttasks" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="nttasks" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	</span>
	
		<span id="event" class="hidden">
	<logic:notEmpty	name="ProdSupportForm" property="eventtasks">
			
	<p>&nbsp;</p>
	<h3 align="center">Associated EventTasks</h3>

	<table border="1" width="700">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">EVENT_TASK_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">EXECUTED</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">NEXT_NOTICE_DATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">FIRST_NOTICE_DATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">EVENT_NAME</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SCHEDULE_NAME</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TASK_NAME</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TASK_STATUS</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="eventtasks" name="ProdSupportForm" property="eventtasks">
	<tr>
		<td><font size="-1"><bean:write name="eventtasks" property="eventTaskId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="eventtasks" property="executed" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="eventtasks" property="nextNoticeDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="eventtasks" property="firstNoticeDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="eventtasks" property="eventName" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="eventtasks" property="scheduleName" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="eventtasks" property="taskName" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="eventtasks" property="taskStatus" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="eventtasks" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="eventtasks" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="eventtasks" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="eventtasks" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="eventtasks" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="eventtasks" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	</span>

	<span id="trait" class="hidden">
	<logic:notEmpty	name="ProdSupportForm" property="traits">
			
	<p>&nbsp;</p>
	<h3 align="left">Associated Traits</h3>
	
<table border="1" width="700">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TRAITS_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">COMMENTS</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DISPOSITIONNUM</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SUPERVISIONNUM</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TRAITTYPE_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TRAITSTATUS</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="traits" name="ProdSupportForm" property="traits">
	<tr>
		<td><font size="-1"><bean:write  name="traits" property="juvenileTraitId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="traits" property="traitComments" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="traits" property="dispositionNum" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="traits" property="juvenileNum" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="traits" property="supervisionNum" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="traits" property="traitTypeId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="traits" property="statusId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="traits" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="traits" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="traits" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="traits" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="traits" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="traits" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	</span>
	
	<span id="drugTestingInfos" class="hidden">
	<logic:notEmpty	name="ProdSupportForm" property="drugTestingInfos">
			
	<p>&nbsp;</p>
	<h3 align="left">Associated Drug Testing</h3>
	
<table border="1" width="700">
    <tr bgcolor="gray">
        <td bgcolor="gray"><font color="white" face="bold" size="-1">DRUGTESTING_ID</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">ASSOCIATECASEFILENO</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">TESTDATE</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">TESTTIME</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">TESTADMINISTERED</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">SUBSTANCETESTED</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">DRUGTESTRESULTS</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">TESTLOCATION</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">ADMINISTEREDBY</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">COMMENTS</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEUSER</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEDATE</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">ACITIVITY_ID</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
    </tr>
    <logic:iterate id="drugTestingInfos" name="ProdSupportForm" property="drugTestingInfos">
    <tr>
        <td><font size="-1"><bean:write name="drugTestingInfos" property="drugTestingId" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="drugTestingInfos" property="associateCasefile" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="drugTestingInfos" property="testDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
        <td><font size="-1"><bean:write name="drugTestingInfos" property="formattedTestTime" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="drugTestingInfos" property="testAdministered" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="drugTestingInfos" property="substanceTested" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="drugTestingInfos" property="drugTestResults" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="drugTestingInfos" property="testLocation" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="drugTestingInfos" property="administeredBy" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="drugTestingInfos" property="comments" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="drugTestingInfos" property="createUser" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="drugTestingInfos" property="createDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
        <td><font size="-1"><bean:write name="drugTestingInfos" property="updateUser" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="drugTestingInfos" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="drugTestingInfos" property="createJIMS2UserID" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="drugTestingInfos" property="updateJIMS2UserID" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="drugTestingInfos" property="activityId" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="drugTestingInfos" property="juvenileId" />&nbsp;</font></td>
    </tr>
    </logic:iterate>
</table>

	</logic:notEmpty>
	</span>
	
	<span id="subAbuseInfos" class="hidden">
	<logic:notEmpty	name="ProdSupportForm" property="subAbuseInfos">
			
	<p>&nbsp;</p>
	<h3 align="left">Associated Substance Abuse</h3>
	
 <table border="1" width="700">
    <tr bgcolor="gray">
        <td bgcolor="gray"><font color="white" face="bold" size="-1">JCSUBABUSE_ID</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUM</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">SABUSE</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">SUBSTANCETYPE</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEDATE</font></td>
        <td bgcolor="gray"><font color="white" face="bold" size="-1">TREATMENTLOCATION</font></td>
    </tr>
    <logic:iterate id="subAbuseInfos" name="ProdSupportForm" property="subAbuseInfos">
    <tr>
        <td><font size="-1"><bean:write name="subAbuseInfos" property="substanceAbuseId" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="subAbuseInfos" property="juvenileNumber" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="subAbuseInfos" property="associatedCasefileId" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="subAbuseInfos" property="referralNumber" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="subAbuseInfos" property="substanceAbuse" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="subAbuseInfos" property="substanceType" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="subAbuseInfos" property="createUser" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="subAbuseInfos" property="createDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
        <td><font size="-1"><bean:write name="subAbuseInfos" property="createJims2User" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="subAbuseInfos" property="updateJims2User" />&nbsp;</font></td>
        <td><font size="-1"><bean:write name="subAbuseInfos" property="updateDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
        <td><font size="-1"><bean:write name="subAbuseInfos" property="treatmentLocation" />&nbsp;</font></td>
    </tr>
    </logic:iterate>
</table>
	</logic:notEmpty>
	</span>
	

	<table border="0" width="700">
	
	<tr><td colspan="4">&nbsp;</td></tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	
	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<bean:write name="ProdSupportForm" property="msg" />
			</logic:notEqual> 
			</font></td>
	</tr>
	</table>

    <input type="submit" name="back" value="Back"/>	
</html:form>



</body>
</html:html>