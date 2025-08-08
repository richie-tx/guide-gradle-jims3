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
<title>Juvenile Casework -/prodSupport/deleteCaseDocumentQueryResult.jsp</title>
<style>
.overlay{
	display: none;
	position: fixed; z-index: 100; top: 0px; left: 0px; right: 0px; bottom: 0px;
}
	

.overlay .overlay-background { 
	position: absolute; z-index: -1; top: 0px; left: 0px; right: 0px; bottom: 0px;
	background: black; opacity: 0.5;
}
    
	
	
.se-pre-con {
	position: fixed;
	position: fixed;
	top: 35%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 100%;
	height: 100%;
	z-index: 9999;
	border: 2px solid #f3f3f3;
	border-radius: 50%;
	border-top: 2px solid #3498db;
	width: 80px;
	height:80px;
	-webkit-animation: spin 2s linear infinite; /* Safari */
	 animation: spin 2s linear infinite;
}
	
	/* Safari */
@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script> 
<script language="javascript">

$(document).ready(function(){
	$("#deleteRecord").click(function(e){
		if ( confirmDelete() ) {
			spinner();
		} else {
			e.preventDefault();
		}
	})
})

function confirmDelete(){
	if(confirm('Are you sure you want to delete this document and its children?'))
		return true;	
	else
		return false;
}

</script>

</head>

<html:form action="/TableDetailQuery" onsubmit="return this;">

<input type="hidden" name="tableId" value="" />

	<div>

	<br/>
	<h4 align="center"><i>The following record and all associated records will be <font color="red">DELETED</font>.</i></h4>

<hr>
	<span id="casefiledocs" class="hidden">
	<logic:notEmpty	name="ProdSupportForm" property="casefiledocs">
	<br/>
	<p>&nbsp;</p>
	<h3 align="center">Parent Document</h3>	
	
	<logic:iterate id="casefiledocs" name="ProdSupportForm" property="casefiledocs">
	<table border="1" width="700" id="casefiledocs" align="center">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DOCUMENT_ID</font></td>
		<td><font size="-1"><bean:write  name="casefiledocs" property="documentId" />&nbsp;</font></td>	
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TABLENAME</font></td>
		<td><font size="-1"><bean:write  name="casefiledocs" property="tableName"/></font></td>				
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASENONCOMNOTE_ID</font></td>
		<td><font size="-1"><bean:write  name="casefiledocs" property="caseNonConNoteId" />&nbsp;</font></td>			
	</tr>
	<tr>			
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DOCTYPECD</font></td>
		<td><font size="-1"><bean:write  name="casefiledocs" property="docTypeCd" />&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td><font size="-1"><bean:write  name="casefiledocs" property="createUserID" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td><font size="-1"><bean:write  name="casefiledocs" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td><font size="-1"><bean:write  name="casefiledocs" property="updateUser" />&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td><font size="-1"><bean:write  name="casefiledocs" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>		
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="casefiledocs" property="createJIMS2UserID" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>		
		<td><font size="-1"><bean:write  name="casefiledocs" property="updateJIMS2UserID" />&nbsp;</font></td>		
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>

<logic:notEmpty	name="ProdSupportForm" property="casedocchildren">
	
	<p>&nbsp;</p>
	<h3 align="center">Associated Child Documents</h3>
	
	<table border="1" width="700" align="center" id="casedocchildren">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TABLENAME</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASENONCOMNOTE_ID</font></td>		
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DOCTYPECD</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
	</tr>
	
	<logic:iterate id="casedocchildren" name="ProdSupportForm" property="casedocchildren">
	<tr>
		<td><font size="-1"><bean:write name="casedocchildren" property="tableName"/></font></td>		
		<td><font size="-1"><bean:write  name="casedocchildren" property="caseNonConNoteId" />&nbsp;</font></td>			
		<td><font size="-1"><bean:write  name="casedocchildren" property="docTypeCd" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casedocchildren" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casedocchildren" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
		
	
	<table border="0" width="700" align="center">
	
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
	</div>
	
</html:form>

<table align="center"">
<tr>
<td>
<html:form action="/DeleteCaseDocumentQuery?clr=Y">
<p align="center">
	<input type="submit" name="details" value="Back to Query"/>
</p>
</html:form>	
</td>
<td>&nbsp;</td>
<td>

<html:form action="/PerformDeleteCaseDocument">
<p align="center">
	<input type="submit" id="deleteRecord" 
							name="Delete Record" 
							value="Delete Record" />
</p>
</html:form>

</td>
</tr>
</table>

</html:html>