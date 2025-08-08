<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/caseDeleteQueryResult.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 

<script language="javascript">
$(document).ready(function(){
	$("#deleteRecords").click(function(){
		if ( confirmDelete() ) {
			spinner();
		}
	})
})
function setTableId(idName){
     document.forms[0].tableId.value = idName;
}

function confirmDelete(){
	if(confirm('Are you sure you want to delete the casefile and all its records?'))
		return true;	
	else
		return false;
}

</script>

</head>

<html:form action="/TableDetailQuery" onsubmit="return this;">

<input type="hidden" name="tableId" value="" />

	<div>
	
	<h2 align="center">Results for CASEFILE_ID = 
			<bean:write name="ProdSupportForm" property="casefileId" /></h2>
	<br>
	<h4 align="center"><i>The following rows will also be <font color="red">DELETED</font> along with casefile 
	<bean:write name="ProdSupportForm" property="casefileId" />.</i></h4>

<hr>
<table width="100%" cellspacing="1">	
	<!-- The idea here is to present a simple row count and offer a button to drill down and 
	display another page that offers details on the table contents. -->
	<tr align="center">
			<td colspan="4">
			<font style="font-weight: bold;" color="#FF0000" size="3" face="Arial"> 
			<logic:notEqual name="ProdSupportForm" property="msg" value="">
				<bean:write name="ProdSupportForm" property="msg" />
			</logic:notEqual> 
			</font></td>
	</tr>
	<tr>
	<td align="center">
	<font size="+1"><b>Associated Activities</b></font><br>
	
	   <logic:notEmpty name="ProdSupportForm" property="activityCount">
	  	<logic:notEqual name="ProdSupportForm" property="activityCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="activityCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="submit" name="caseDetails" value="Details" onClick="setTableId('act')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="activityCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	     
	</td>
	
	<td align="center">
	<font size="+1"><b>Associated Casefiles</b></font><br>

	  <logic:notEmpty name="ProdSupportForm" property="casefileCount">
	  	<logic:notEqual name="ProdSupportForm" property="casefileCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="casefileCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="submit" name="caseDetails" value="Details" onClick="setTableId('case')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="casefileCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="casefileCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	 
	</td>
	</tr>
	
	<tr><td>&nbsp;</td></tr>
	
	<tr>
	<td align="center">
	<font size="+1"><b>Associated Assignments</b></font><br>

	  <logic:notEmpty name="ProdSupportForm" property="assignmentCount">
	  	<logic:notEqual name="ProdSupportForm" property="assignmentCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="assignmentCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="submit" name="assignDetails" value="Details" onClick="setTableId('assign')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="assignmentCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="assignmentCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>

	</td>
	
	<td align="center">
	<font size="+1"><b>Associated JPOAssnmntHists</b></font><br>

	  <logic:notEmpty name="ProdSupportForm" property="assnmnthistCount">
	  	<logic:notEqual name="ProdSupportForm" property="assnmnthistCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="assnmnthistCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="submit" name="assignHistDetails" value="Details" onClick="setTableId('assignhist')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="assnmnthistCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="assnmnthistCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>

	</td>
	
	</tr>
	
	<tr><td>&nbsp;</td></tr>
	
	<tr>
	<td align="center">
	<font size="+1"><b>Associated Casefile Closings</b></font><br>
	
		  <logic:notEmpty name="ProdSupportForm" property="casefileClosingCount">
	  	<logic:notEqual name="ProdSupportForm" property="casefileClosingCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="casefileClosingCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="submit" name="details" value="Details" onClick="setTableId('closing')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="casefileClosingCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="casefileClosingCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	
	<td align="center">
	<font size="+1"><b>Associated Interviews</b></font><br>
	
		  <logic:notEmpty name="ProdSupportForm" property="interviewCount">
	  	<logic:notEqual name="ProdSupportForm" property="interviewCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="interviewCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="submit" name="details" value="Details" onClick="setTableId('interview')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="interviewCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="interviewCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	</tr>
	
	<tr><td>&nbsp;</td></tr>
	
	<tr>
	<td align="center">
	<font size="+1"><b>Associated Iviewdocs</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="iviewdocCount">
	  	<logic:notEqual name="ProdSupportForm" property="iviewdocCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="iviewdocCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="submit" name="details" value="Details" onClick="setTableId('iviewdoc')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="iviewdocCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="iviewdocCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>

	<td align="center">
	<font size="+1"><b>Associated Risk Analyses</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="riskanalysisCount">
	  	<logic:notEqual name="ProdSupportForm" property="riskanalysisCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="riskanalysisCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="submit" name="details" value="Details" onClick="setTableId('risk')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="riskanalysisCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="riskanalysisCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	</tr>
	
	<tr><td>&nbsp;</td></tr>	
	
	<tr>
	<td align="center">
	<font size="+1"><b>Associated Risk Responses</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="riskresponsesCount">
	  	<logic:notEqual name="ProdSupportForm" property="riskresponsesCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="riskresponsesCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="submit" name="details" value="Details" onClick="setTableId('responses')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="riskresponsesCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="riskresponsesCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>

	
	<td align="center">
	<font size="+1"><b>Associated JuvProgRefs</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="juvprogrefCount">
	  	<logic:notEqual name="ProdSupportForm" property="juvprogrefCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="juvprogrefCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="submit" name="details" value="Details" onClick="setTableId('progref')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="juvprogrefCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="juvprogrefCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	</tr>

	<tr><td>&nbsp;</td></tr>

	<tr>
	<td align="center">
	<font size="+1"><b>Associated Beneasmnts</b></font><br>
	
		  <logic:notEmpty name="ProdSupportForm" property="beneasmntCount">
	  	<logic:notEqual name="ProdSupportForm" property="beneasmntCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="beneasmntCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="submit" name="details" value="Details" onClick="setTableId('bene')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="beneasmntCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="beneasmntCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>

	<td align="center">
	<font size="+1"><b>Associated Suprules</b></font><br>

	  <logic:notEmpty name="ProdSupportForm" property="supruleCount">
	  	<logic:notEqual name="ProdSupportForm" property="supruleCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="supruleCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="submit" name="details" value="Details" onClick="setTableId('suprule')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="supruleCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="supruleCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	</tr>

	<tr><td>&nbsp;</td></tr>
	
	<tr>
	<td align="center">
	<font size="+1"><b>Associated CalEventConts</b></font><br>
	
	 <logic:notEmpty name="ProdSupportForm" property="caleventcontCount">
		<logic:notEqual name="ProdSupportForm" property="caleventcontCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="caleventcontCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="submit" name="details" value="Details" onClick="setTableId('cal')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="caleventcontCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="caleventcontCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>

	<td align="center">
	<font size="+1"><b>Associated EventTasks</b></font><br>
	
		  <logic:notEmpty name="ProdSupportForm" property="eventtaskCount">
	  	<logic:notEqual name="ProdSupportForm" property="eventtaskCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="eventtaskCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="submit" name="details" value="Details" onClick="setTableId('event')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="eventtaskCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="eventtaskCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	</tr>
	
	<tr><td>&nbsp;</td></tr>
	
	<tr>
	<td align="center">
	<font size="+1"><b>Associated NTTasks</b></font><br>
	
		  <logic:notEmpty name="ProdSupportForm" property="nttaskCount">
	  	<logic:notEqual name="ProdSupportForm" property="nttaskCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="nttaskCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="submit" name="details" value="Details" onClick="setTableId('nttask')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="nttaskCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="nttaskCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	  </td>
	
	<td align="center">
	<font size="+1"><b>Associated Caseplans</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="caseplanCount">
	  	<logic:notEqual name="ProdSupportForm" property="caseplanCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="caseplanCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="submit" name="details" value="Details" onClick="setTableId('plan')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="caseplanCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="caseplanCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	</tr>
	
	<tr><td>&nbsp;</td></tr>
	
	<tr>
	<td align="center">
	<font size="+1"><b>Associated ProgRefAsgnHists</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="progrfasgnhistCount">
	  	<logic:notEqual name="ProdSupportForm" property="progrfasgnhistCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="progrfasgnhistCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="submit" name="details" value="Details" onClick="setTableId('progrfasgnhist')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="progrfasgnhistCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="progrfasgnhistCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	
	<td align="center">
	<font size="+1"><b>Associated JCTraits</b></font><br>
	
		  <logic:notEmpty name="ProdSupportForm" property="traitCount">
	  	<logic:notEqual name="ProdSupportForm" property="traitCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="traitCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="submit" name="details" value="Details" onClick="setTableId('trait')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="traitCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="traitCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	  </td>
	</tr>
	
	<tr><td>&nbsp;</td></tr>
	
	<tr>
	<td align="center">
	<font size="+1"><b>Associated Drug Testing</b></font><br>

		  <logic:notEmpty name="ProdSupportForm" property="drugTestingCount">
	  	<logic:notEqual name="ProdSupportForm" property="drugTestingCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="drugTestingCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="submit" name="details" value="Details" onClick="setTableId('drugTestingInfos')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="drugTestingCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="drugTestingCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	</td>
	
	<td align="center">
	<font size="+1"><b>Associated Substance Abuse</b></font><br>
	
		  <logic:notEmpty name="ProdSupportForm" property="subAbuseCount">
	  	<logic:notEqual name="ProdSupportForm" property="subAbuseCount" value="0">	 
	   		<i><bean:write name="ProdSupportForm" property="subAbuseCount" />
	    	 Result(s) Returned</i>&nbsp;&nbsp;<br><br>
	    	<input type="submit" name="details" value="Details" onClick="setTableId('subAbuseInfos')"/>	  
	  	</logic:notEqual>
	 	<logic:equal name="ProdSupportForm" property="subAbuseCount" value="0">
	    	<i>No Result(s) Returned</i>
	  	</logic:equal>
	  </logic:notEmpty>
	  
	  <logic:empty name="ProdSupportForm" property="subAbuseCount">
	    <i>No Result(s) Returned</i>
	  </logic:empty>
	  </td>
	</tr>
	
	</table>
		
	
	<table border="0" width="700" align="center">
	
	<tr><td colspan="4">&nbsp;</td></tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	</table>
	</div>
	
</html:form>

<table align="center"">
<tr>
<td>
<html:form action="/DeleteCasefileQuery?clr=Y">
<p align="center">
	<input type="submit" name="details" value="Back to Query"/>
</p>
</html:form>	
</td>
<td>&nbsp;</td>
<td>

<html:form action="/PerformDelete">
<p align="center">
	<input type="submit" id="deleteRecords" 
						 name="Delete Records" 
						 value="Delete Records"/>
</p>
</html:form>

</td>
</tr>
</table>

</html:html>