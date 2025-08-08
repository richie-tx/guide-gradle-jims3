<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/caseDeleteSummary.jsp</title>
</head>

<body>
	
<html:form method="post" action="/DeleteCasefileQuery" onsubmit="return this;">

<h2 align="center">Delete Casefile Summary</h2>
<hr>

<p align="center"><font color="green"><b>Casefile number 
<bean:write name="ProdSupportForm" property="casefileId" /> was successfully deleted.</b></font></p>
</html:form>


<p align="center"><b>The following is a list of records affected by this change. This is for auditing purposes.<br> 
If this delete was made in error, please provide this list to the JIMS Data Team to restore the data.</b></p>
<hr>

	<logic:notEmpty	name="ProdSupportForm" property="activities">
	<h3 align="center">Activities</h3>
	
	<table border="1" width="500" align="center">
	<tr>
	    	  
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">ACTIVITY_ID</font></td>
	</tr>
	
    <logic:iterate id="activities" name="ProdSupportForm" property="activities">
	<tr>
	    <td><font size="-1"><bean:write  name="activities" property="activityId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
	
	</table>
	</logic:notEmpty>
	
	<logic:notEmpty	name="ProdSupportForm" property="casefiles">
	<h3 align="center">Associated Casefiles</h3>
	
	<table border="1" width="500" align="center">
	
	<tr>
	    <td bgcolor="gray"> <font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
	    <td bgcolor="gray"> <font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
	</tr>
	
    <logic:iterate id="casefiles" name="ProdSupportForm" property="casefiles">
	<tr>
        <td><font size="-1"><bean:write  name="casefiles" property="supervisionNum" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefiles" property="juvenileNum" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
	
	</table>
	</logic:notEmpty>
	
	<logic:notEmpty	name="ProdSupportForm" property="assignments">
	
	<p>&nbsp;</p>
	<h3 align="center">Associated Assignments</h3>
	
	<table border="1" width="500" align="center">
	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ASSIGNMENT_ID</font></td>
	</tr>
	
	<logic:iterate id="assignments" name="ProdSupportForm" property="assignments">
	<tr>
		<td><font size="-1"><bean:write  name="assignments" property="assignmentId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	
	<logic:notEmpty	name="ProdSupportForm" property="assnmnthists">
	
	<p>&nbsp;</p>
	<h3 align="center">Associated JPOAssnmntHists</h3>
	
	<table border="1" width="500" align="center">
	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">JPOASSNMNT_ID</font></td>
	</tr>
	
	<logic:iterate id="assignments" name="ProdSupportForm" property="assnmnthists">
	<tr>
		<td><font size="-1"><bean:write  name="assignments" property="jpoAssignmentHistoryId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>

	<logic:notEmpty	name="ProdSupportForm" property="caseplans">
	
	<p>&nbsp;</p>
	<h3 align="center">Associated Caseplans</h3>
	
	<table border="1" width="500" align="center">
	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CASEPLAN_ID</font></td>
	</tr>
	
	<logic:iterate id="caseplans" name="ProdSupportForm" property="caseplans">
	<tr>
		<td><font size="-1"><bean:write  name="caseplans" property="caseplanID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	
	<logic:notEmpty	name="ProdSupportForm" property="casefileclosings">
	
	<p>&nbsp;</p>
	<h3 align="center">Associated Casefile Closings</h3>
	
	<table border="1" width="500" align="center">
	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CASFILECLOSNG_ID</font></td>
	</tr>
	
	<logic:iterate id="casefileclosings" name="ProdSupportForm" property="casefileclosings">
	<tr>
		<td><font size="-1"><bean:write  name="casefileclosings" property="casefileClosingInfoId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>


	<logic:notEmpty	name="ProdSupportForm" property="interviews">
	<p>&nbsp;</p>
	<h3 align="center">Associated Interviews</h3>	
	<table border="1" width="500" align="center">
	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">INTERVIEW_ID</font></td>
		
	</tr>
	
	<logic:iterate id="interviews" name="ProdSupportForm" property="interviews">
	<tr>
		<td><font size="-1"><bean:write  name="interviews" property="interviewId" />&nbsp;</font></td>
	
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>


	<logic:notEmpty	name="ProdSupportForm" property="iviewdocs">
	<p>&nbsp;</p>
	<h3 align="center">Associated Iviewdocs</h3>
	
	<table border="1" width="500" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">IVIEWDOC_ID</font></td>
	</tr>
	
	<logic:iterate id="iviewdocs" name="ProdSupportForm" property="iviewdocs">
	<tr>
		<td><font size="-1"><bean:write  name="iviewdocs" property="reportId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	

	<logic:notEmpty	name="ProdSupportForm" property="riskanalyses">	
	<p>&nbsp;</p>
	<h3 align="center">Associated Risk Analyses</h3>
	<table border="1" width="500" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RISKANALYSIS_ID</font></td>
	</tr>
	
	<logic:iterate id="riskanalyses" name="ProdSupportForm" property="riskanalyses">
	<tr>
		<td><font size="-1"><bean:write  name="riskanalyses" property="assessmentID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	
	<logic:notEmpty	name="ProdSupportForm" property="riskresponses">	
	<p>&nbsp;</p>
	<h3 align="center">Associated Risk Responses</h3>
	<table border="1" width="500" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RISKRESPONSE_ID</font></td>
	</tr>
	
	<logic:iterate id="riskresponses" name="ProdSupportForm" property="riskresponses">
	<tr>
		<td><font size="-1"><bean:write  name="riskresponses" property="responseID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	
	<logic:notEmpty	name="ProdSupportForm" property="juvprogrefs">
	<p>&nbsp;</p>
	<h3 align="center">Associated JuvProgRefs</h3>
	<table border="1" width="500" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVPROGREF_ID</font></td>
	</tr>
	
	<logic:iterate id="juvprogrefs" name="ProdSupportForm" property="juvprogrefs">
	<tr>
		<td><font size="-1"><bean:write  name="juvprogrefs" property="juvenileProgramReferralId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	

	<logic:notEmpty	name="ProdSupportForm" property="beneasmnts">
	<p>&nbsp;</p>
	<h3 align="center">Associated Beneasmnts</h3>
	
	<table border="1" width="500" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">BENEASMNT_ID</font></td>
	</tr>
	
	<logic:iterate id="beneasmnts" name="ProdSupportForm" property="beneasmnts">
	<tr>
		<td><font size="-1"><bean:write  name="beneasmnts" property="assessmentId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>


	<logic:notEmpty	name="ProdSupportForm" property="suprules">	
	<p>&nbsp;</p>
	<h3 align="center">Associated Suprules</h3>
	<table border="1" width="500" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SUPRULE_ID</font></td>
	</tr>
	
	<logic:iterate id="suprules" name="ProdSupportForm" property="suprules">
	<tr>
		<td><font size="-1"><bean:write  name="suprules" property="supervisionRuleId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>

	<logic:notEmpty	name="ProdSupportForm" property="caleventconts">
			
	<p>&nbsp;</p>
	<h3 align="center">Associated CalEventConts</h3>
	
	<table border="1" width="500" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CALEVENTCONT_ID</font></td>

	</tr>
	
	<logic:iterate id="caleventconts" name="ProdSupportForm" property="caleventconts">
	<tr>
		<td><font size="-1"><bean:write  name="caleventconts" property="calendarEventContextId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	
	<logic:notEmpty	name="ProdSupportForm" property="progrfasgnhists">
			
	<p>&nbsp;</p>
	<h3 align="center">Associated ProgRefAsgnHists</h3>
	
	<table border="1" width="500" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">PROGRFASGNHIST_ID</font></td>

	</tr>
	
	<logic:iterate id="progrfasgnhists" name="ProdSupportForm" property="progrfasgnhists">
	<tr>
		<td><font size="-1"><bean:write  name="progrfasgnhists" property="programReferralAssignmentHistoryId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	
	<logic:notEmpty	name="ProdSupportForm" property="nttasks">
			
	<p>&nbsp;</p>
	<h3 align="center">Associated NTTasks</h3>
	
	<table border="1" width="500" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TASK_ID</font></td>
		
	</tr>
	
	<logic:iterate id="nttasks" name="ProdSupportForm" property="nttasks">
	<tr>
		<td><font size="-1"><bean:write  name="nttasks" property="taskId" />&nbsp;</font></td>
		
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	
	
	<logic:notEmpty	name="ProdSupportForm" property="eventtasks">		
	<p>&nbsp;</p>
	<h3 align="center">Associated EventTasks</h3>

	<table border="1" width="500" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">EVENT_TASK_ID</font></td>
	</tr>
	
	<logic:iterate id="eventtasks" name="ProdSupportForm" property="eventtasks">
	<tr>
		<td><font size="-1"><bean:write name="eventtasks" property="eventTaskId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>

	<logic:notEmpty	name="ProdSupportForm" property="traits">
			
	<p>&nbsp;</p>
	<h3 align="center">Associated Traits</h3>
	
	<table border="1" width="500" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TRAITS_ID</font></td>
	</tr>
	
	<logic:iterate id="traits" name="ProdSupportForm" property="traits">
	<tr>
		<td><font size="-1"><bean:write  name="traits" property="juvenileTraitId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	
	<logic:notEmpty	name="ProdSupportForm" property="drugTestingInfos">
			
	<p>&nbsp;</p>
	<h3 align="center">Associated Drug Testing</h3>
	
	<table border="1" width="500" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DRUGTESTING_ID</font></td>
	</tr>
	
	<logic:iterate id="drugTestingInfos" name="ProdSupportForm" property="drugTestingInfos">
	<tr>
		<td><font size="-1"><bean:write  name="drugTestingInfos" property="drugTestingId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	
	<logic:notEmpty	name="ProdSupportForm" property="subAbuseInfos">
			
	<p>&nbsp;</p>
	<h3 align="center">Associated Substance Abuse</h3>
	
	<table border="1" width="500" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JCSUBABUSE_ID</font></td>
	</tr>
	
	<logic:iterate id="subAbuseInfos" name="ProdSupportForm" property="subAbuseInfos">
	<tr>
		<td><font size="-1"><bean:write  name="subAbuseInfos" property="substanceAbuseId" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>

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