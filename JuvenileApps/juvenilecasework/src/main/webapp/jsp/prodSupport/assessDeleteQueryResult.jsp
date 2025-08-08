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
<title>Juvenile Casework -/prodSupport/assessDeleteQueryResult.jsp</title>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script language="javascript">
function setTableId(idName){
     document.forms[0].tableId.value = idName;
}

function confirmDelete(){
	if(confirm('Are you sure you want to delete the assessment and all its records?')) {
		spinner();
		return true;	
	} else {
		return false; 
	}
}

</script>

</head>


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

<html:form action="/PerformAssessDelete" onsubmit="return this;">

<div>
	
	<h2 align="center">Results for Risk Assessment ID = 
			<bean:write name="ProdSupportForm" property="riskanalysisId" /></h2>
	<br>
	<h4 align="center"><i>The following rows will also be <font color="red">DELETED</font> along with assessment   
	<bean:write name="ProdSupportForm" property="riskanalysisId" />.</i></h4>	

	<logic:notEmpty	name="ProdSupportForm" property="riskanalyses">
	
	<logic:notEqual name="ProdSupportForm" property="riskanalysisCount" value="0">	 
	<p>&nbsp;</p>
	<font size="+1"><b>Associated Risk Analyses</b></font>
	
	<table align="center" class="resultTbl" border="1" width="700">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RISKANALYSIS_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">FINALSCORE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSESSMENTTYPE</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JPOUSERID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DATEENTERED</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">RECOMMENDATION</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="riskanalyses" name="ProdSupportForm" property="riskanalyses">
	<tr>
		<td><font size="-1"><bean:write  name="riskanalyses" property="riskAnalysisId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="finalScore" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="assessmentType" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="juvenileId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="jpoUserId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="caseFileId" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="dateEntered" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="recommendation" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskanalyses" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEqual>
	
	<logic:equal name="ProdSupportForm" property="riskanalysisCount" value="0">
	  	 <h3 align="center"><i>No Risk Analysis Records Returned</i></h3>
	</logic:equal>
	
	</logic:notEmpty>
	
	<logic:empty name="ProdSupportForm" property="riskanalyses">
	<br>
	<table align="center" border="1" width="700">
		<tr>
		<td>
	   <h3 align="center"><font color="green"><i>No Risk Analysis Records Returned</i></font></h3>
	   </td>
	   </tr>
	   </table>
	</logic:empty>
	     
	
	<logic:notEmpty	name="ProdSupportForm" property="riskresponses">
	<p>&nbsp;</p>
	<table align="center" border="0" width="700">
	<tr>
	<td>
	<font size="+1"><b>Associated Risk Response Records</b></font><br>
	</td>
	</tr>
	
	<tr>
	<td align="center">
	<logic:notEmpty name="ProdSupportForm" property="riskresponsesCount">
		<logic:notEqual name="ProdSupportForm" property="riskresponsesCount" value="0">	 
	
	<table class="resultTbl" border="1" width="700">
	<tr>
	    	  
        <td bgcolor="gray"> <font color="white" face="bold" size="-1">RISKRESPONSES_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">RISKANALYSIS_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">RISKWEIGHTRES_ID</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATDATE</font></td>
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
		<td><font size="-1"><bean:write  name="riskresponses" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskresponses" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskresponses" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskresponses" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskresponses" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="riskresponses" property="updateJIMS2UserID" />&nbsp;</font></td>
	
	</tr>
	</logic:iterate>
	
	</table>
	</logic:notEqual>
	
	
	
	</logic:notEmpty>	
	
	<logic:empty name="ProdSupportForm" property="riskresponses">
		<table border="1" width="700">
		<tr>
		<td>
	   <h3 align="center"><i>No associated Risk Response Records Found</i></h3>
	   </td>
	   </tr>
	   </table>
	</logic:empty>

	</td>
	</tr>
	</table>
	</logic:notEmpty>
		
	</div>
	
</html:form>

<table align="center"">
<tr>

<td>&nbsp;</td>

<logic:notEmpty name="ProdSupportForm" property="riskanalyses">
<td>
<html:form action="/PerformAssessDelete">
<p align="center">
	<input type="submit" name="Delete Records" value="Delete Records" onClick="return confirmDelete();" />
</p>

</html:form>
</td>
</logic:notEmpty>

</tr>
</table>

<html:form action="/AssessDeleteQuery?clr=Y" onsubmit="return this;">
<table align="center"">
	<tr>
		<td>
		<html:submit value="Back to Query"/>
		</td>
	</tr>
</table>
</html:form>

</html:html>