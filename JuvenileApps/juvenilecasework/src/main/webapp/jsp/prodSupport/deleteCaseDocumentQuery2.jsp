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
<title>Juvenile Casework -/prodSupport/deleteCaseDocumentQuery2.jsp</title>
<%-- STYLE SHEET LINK --%>
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

function fillId(value1, value2)
{
	//alert('Values are '+value1+', '+value2);
	
	document.forms[0].documentId.value = value1;
	document.forms[0].documentType.value = value2;

	//alert('Form Values are '+document.forms[0].documentId+', '+document.forms[0].documentType);
	
	return true;
}			
</script>

</head>

<body>

<h2 align="center">Production Support - Case Document Query</h2>
<hr>

<p align="center">Select a document you wish to delete from the list and click SUBMIT.<br/> 
	No records will be deleted until confirmed on the next page.</p>

	<div align="center">
	<html:form method="post" action="/DeleteCaseDocumentQuery?edit=Y" onsubmit="return this;">
	
	<span id="casefiledocs" class="hidden">
	<logic:notEmpty	name="ProdSupportForm" property="casefiledocs">
	
	<p>&nbsp;</p>
	<h3 align="center">Associated Documents</h3>
	
	<table border="1"  id="casefiledocs">
	
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DOCUMENT_ID</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TABLENAME</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CASENONCOMNOTE_ID</font></td>		
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DOCTYPECD</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
	</tr>
	
	<logic:iterate id="casefiledocs" name="ProdSupportForm" property="casefiledocs">
	<tr>
		<td>
 		<input type="radio" name="radioProp" value="<bean:write name="casefiledocs" property="documentId"/>" 
	 		onclick="fillId(<bean:write name="casefiledocs" property="documentId"/>,'<bean:write name="casefiledocs" property="tableName"/>')">	 		
	 		<bean:write name="casefiledocs" property="documentId"/>
		</td>
		<td><font size="-1"><bean:write name="casefiledocs" property="tableName"/></font></td>		
		<td><font size="-1"><bean:write  name="casefiledocs" property="caseNonConNoteId" />&nbsp;</font></td>			
		<td><font size="-1"><bean:write  name="casefiledocs" property="docTypeCd" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefiledocs" property="createUserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefiledocs" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefiledocs" property="updateUser" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefiledocs" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefiledocs" property="createJIMS2UserID" />&nbsp;</font></td>
		<td><font size="-1"><bean:write  name="casefiledocs" property="updateJIMS2UserID" />&nbsp;</font></td>
	</tr>
	</logic:iterate>
		
	</table>
	</logic:notEmpty>
	</span>
		
	&nbsp;
	
<table border="0">
	<tr>
		<td align="center" colspan="3"><font style="FONT-FAMILY: Arial" color="#0000aa"><strong>Selected DocumentID:</strong></font></td>
		<td><html:text property="documentId" size="10" maxlength="15" readonly="true"/></td>
		<td><font style="FONT-FAMILY: Arial" color="#0000aa"><strong>Table:</strong></font></td>
		<td><html:text property="documentType" size="10" maxlength="15" readonly="true"/></td>
	</tr>
	
	<tr>
		<td>&nbsp;</td>
		<td colspan="3" align="center"><html:submit onclick="spinner()" value="Submit" /></td>
	</tr>

	<tr>
		<td>&nbsp;</td>
	</tr>
</table>
</html:form>
	
	<table border="0">
		<tr>
		<td align="center">
		<html:form method="post" action="/DeleteCaseDocumentQuery?clr=Y" onsubmit="return this;">
		<html:submit onclick="return this;" value="Search a Different Record"/>
		</html:form>
		</td>
	
		<td>&nbsp;</td>
	
		<td align="center">
		<html:form method="post" action="/MainMenu" onsubmit="return this;">
		<html:submit onclick="return this;" value="Back to Main Menu"/>
		</html:form>
		</td>
		</tr>

    </table>
    
    <table border="0" >
	
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

</body>
</html:html>