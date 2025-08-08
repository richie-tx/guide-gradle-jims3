<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/deleteCaseDocumentSummary.jsp</title>
<script>
window.onload = function() {	
    var searchCaseDocumentCount = '<bean:write name="ProdSupportForm" property="searchCaseDocumentCount"/>';		
		
    if (searchCaseDocumentCount === '0' || searchCaseDocumentCount === '1') {
        const element = document.getElementById("btnBackToResults");
        element.style.display = "none"; 
    }
}
			
</script> 
</head>

<body>
	
<html:form method="post" action="/DeleteCasefileQuery" onsubmit="return this;">

<p align="center"><font color="green"><b>Case Document was successfully deleted.</b></font></p>
</html:form>


<p align="center"><b>The following is a list of records affected by this change. This is for auditing purposes.<br> 
If this delete was made in error, please provide this list to the JIMS Data Team to restore the data.</b></p>
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
	</span>
	
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
	
	<br/>
	
	<table align="center" border="0" width="500">
		<tr>
			<td colspan="2" align="center"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><html:form method="post"
					action="/DeleteCaseDocumentQuery?clr=Y"
					onsubmit="return this;">
					<html:submit onclick="return this;" value="Back to Query/Search" />
				</html:form>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><html:form method="post"
					action="/DeleteCaseDocumentQuery?list=Y" onsubmit="return this;">					
							<input type="hidden" name="casefileId"
								value=<bean:write  name="ProdSupportForm" property="casefileId" />>						
					<html:submit onclick="return this;" value="Back to Query Results"
						styleId="btnBackToResults" onclick="spinner()" />
				</html:form></td>
		</tr>
		<tr>
			<td colspan="2" align="center"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><html:form method="post"
					action="/MainMenu" onsubmit="return this;">
					<html:submit onclick="return this;" value="Back to Main Menu" />
				</html:form></td>
		</tr>
	</table>   
    

</body>
</html:html>