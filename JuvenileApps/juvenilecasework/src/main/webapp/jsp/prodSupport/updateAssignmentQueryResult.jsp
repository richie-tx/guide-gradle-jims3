<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<title>Juvenile Casework -/prodSupport/updateAssignmentQueryResult.jsp</title>

<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/updateAssignment.js"></script>
<script language="javascript"></script>

</head>

<body>

<html:form styleId="updateAssignmentForm" action="/PerformUpdateAssignment" onsubmit="return this;">

<div>
	
	<h2 align="center">Results for Assignment ID = 
			<bean:write name="ProdSupportForm" property="assignmentId" /></h2>
	     
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
	     
<p align="center"><b><i>Choose a new date.</i></b></p>	     
	    
<logic:notEmpty	name="ProdSupportForm" property="assignments">
	<logic:iterate id="assignments" name="ProdSupportForm" property="assignments">
	<p>&nbsp;</p>
	<table class="resultTbl" border="1" width="800" align="center">
	
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ASSIGNMENT_ID</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="assignmentId" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ASSMNTADDDATE</font></td>
	 	<td><font size="-1"><bean:write  name="assignments" property="assignmentDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	 	<input id="assmntDate" type="hidden" value='<bean:write  name="assignments" property="assignmentDate" formatKey="date.format.mmddyyyy" />'/>
	 	<td align="left">
	   		<font color="red">New value:&nbsp;</font>
	   		<input id='newDate' value='<bean:write  name="assignments" property="assignmentDate" formatKey="date.format.mmddyyyy" />' size="10" indexed="true"/>
    	</td>
	 </tr>
	 <tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ASSMNTTYPE</font></td>
	 	<td><font size="-1"><bean:write  name="assignments" property="assignmentType"/>&nbsp;</font></td>
	 	<input id="refAssmntType" type="hidden" value="<bean:write  name="assignments" property="assignmentType"/>" />
	 	<td align="left">
	 		<font color="red">New value:&nbsp;</font>
	 		<html:select styleId="assmntType" name="assignments" property="assignmentType">
	 			<html:option value="">Select a New Value</html:option>
	 			<html:optionsCollection name="ProdSupportForm" property="referralAssignmentCodes" value="code" label="codeWithDescription" />
	 		</html:select>
	 	</td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">REFERRALNUMBER</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="referralNum" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">REFSEQNUM</font></td>
		<input id="refSeqNum" type="hidden" value="<bean:write  name="assignments" property="refSeqNum" />"/>
	 	<td><font size="-1"><bean:write  name="assignments" property="refSeqNum"/>&nbsp;</font>
	 	<td align="left">
	   		<font color="red">New value:&nbsp;</font>
	   		<input id='seqNum' value='<bean:write  name="assignments" property="refSeqNum" />' maxlength="2" size="2" indexed="true"/>
    	</td>
	 </td>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CASEFILEID</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="caseFileId" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">SERVICEUNITCD</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="serviceUnitId" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">ASSMNTLEVELCD</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="assessmentLevelId" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="createUserID" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="createDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="updateUser" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="updateDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="createJIMS2UserID" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="assignments" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</table>	    

	<BR>
	
	<table align="center"">
	
	<logic:notEmpty	name="ProdSupportForm" property="assignments">
	<td>
	<p align="center">
		<input id="updateRecord" type="button" value="Update Record" />
	</p>
	</td>
	<td>
		<input id="resetForm" type="button" value="Reset Form Values" />
	</td>
	</logic:notEmpty>
	</table>
	
	</logic:iterate>
	</logic:notEmpty>
	
	<logic:empty name="ProdSupportForm" property="assignments">
	<br>
	<table align="center" border="1" width="700">
		<tr>
		<td>
	   <h3 align="center"><font color="green"><i>No Records Returned</i></font></h3>
	   </td>
	   </tr>
	</table>
	</logic:empty>



	</div>
	
	<html:hidden styleId="oldAssignmentDate" name="ProdSupportForm" property="oldAssignmentDate" />
	<html:hidden styleId="oldRefAssmntType" name="ProdSupportForm" property="oldRefAssmntType" />
	<html:hidden styleId="oldRefSeqNum" name="ProdSupportForm" property="oldRefSeqNum" />
	<html:hidden styleId="newAssignmentDate" name="ProdSupportForm" property="newActDate" />
	<html:hidden styleId="newAssignmentType" name="ProdSupportForm" property="referralAssmntType" />
	<html:hidden styleId="newSeqNum" name="ProdSupportForm" property="refSeqNum" />
</html:form>

<html:form action="/UpdateAssignmentQuery?clr=Y" onsubmit="return this;">
<table align="center"">
	<tr>
		<td>
		<html:submit value="Back to Query"/>
		</td>
	</tr>
</table>
</html:form>


</body>
</html:html>