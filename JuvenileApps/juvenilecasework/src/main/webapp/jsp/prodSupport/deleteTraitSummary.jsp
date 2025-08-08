<!DOCTYPE HTML>
<%--MODIFICATIONS --%>
<%-- 01/04/2016	RCarter	   #32190 html 5 compliance effort and jquery 5 (when required) --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

 
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Juvenile Casework -/prodSupport/deleteTraitSummary.jsp</title>
<script>
window.onload = function() {	
    var traitCount = '<bean:write name="ProdSupportForm" property="searchTraitCount"/>';		
		
    if (traitCount === '0' || traitCount === '1') {
        const element = document.getElementById("btnBackToResults");
        element.style.display = "none"; 
    }
}

			
</script> 
</head>

<body>	
<h2 align="center">Delete Juvenile Trait Summary</h2>
<hr>

<p align="center"><font color="green"><b>Juvenile Trait was successfully deleted.</b></font></p>

<p align="center"><b>The following is a list of records affected by this change. This is for auditing purposes.<br> 
If this modification was made in error, please provide this list to the JIMS Data Team to restore the data.</b></p>
<hr>

<html:form action="/PerformDeleteTrait" onsubmit="return this;">
<br/>

<logic:notEmpty name="ProdSupportForm" property="traits">

    <table border="1" width="750" align="center">

	<logic:iterate id="traits" name="ProdSupportForm" property="traits">

	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TRAITS_ID</font></td>
		<td><font size="-1"><bean:write name="traits" property="juvenileTraitId"/>&nbsp;</font></td>
	</tr>
	<tr>		
		<td bgcolor="gray"><font color="white" face="bold" size="-1">COMMENTS</font></td>		
		<td><font size="-1"><bean:write  name="traits" property="traitComments" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">DISPOSITIONNUM</font></td>	
		<td><font size="-1"><bean:write  name="traits" property="dispositionNum" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>	
		<td><font size="-1"><bean:write  name="traits" property="juvenileNum" />&nbsp;</font></td>
	</tr>
	<tr>
		<td bgcolor="gray"><font color="white" face="bold" size="-1">SUPERVISIONNUM</font></td>					
		<td><font size="-1"><bean:write  name="traits" property="supervisionNum" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TRAITTYPE_ID</font></td>							
		<td><font size="-1"><bean:write  name="traits" property="traitTypeId" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TRAITTYPE_DESC</font></td>							
		<td><font size="-1"><bean:write  name="traits" property="traitTypeDescription" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TRAITSTATUS</font></td>			
		<td><font size="-1"><bean:write  name="traits" property="statusId" />&nbsp;</font></td>
	</tr>
		<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">TRAITSTATUS_DESC</font></td>			
		<td><font size="-1"><bean:write  name="traits" property="status" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEUSER</font></td>			
		<td><font size="-1"><bean:write  name="traits" property="createUserID" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>		
		<td><font size="-1"><bean:write  name="traits" property="createDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEUSER</font></td>	
		<td><font size="-1"><bean:write  name="traits" property="updateUser" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEDATE</font></td>	
		<td><font size="-1"><bean:write  name="traits" property="updateDate" formatKey="date.format.mmddyyyy" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">CREATEJIMS2USER</font></td>			
		<td><font size="-1"><bean:write  name="traits" property="createJIMS2UserID" />&nbsp;</font></td>
	</tr>
	<tr>	
		<td bgcolor="gray"> <font color="white" face="bold" size="-1">UPDATEJIMS2USER</font></td>
		<td><font size="-1"><bean:write  name="traits" property="updateJIMS2UserID" />&nbsp;</font></td>			
	</tr>			
	</logic:iterate>
  </table>
</logic:notEmpty>
</html:form>


<table align="center" border="0" width="500">
   <tr><td colspan="2" align="center"></td></tr>
   <tr>
        <td colspan="2" align="center">            
            <html:form method="post" action="/DeleteTraitQuery?clr=Y" onsubmit="return this;">
				<html:submit value="Back to Query/Search" />
			</html:form>
        </td>
    </tr>
    <tr><td colspan="2" align="center"></td></tr>
    <tr>
        <td colspan="2" align="center">
            <html:form method="post" action="/DeleteTraitQuery?list=Y" onsubmit="return this;" >
            <logic:notEmpty name="ProdSupportForm" property="traits">   
	            <logic:iterate id="traits" name="ProdSupportForm" property="traits">                
                 <input type="hidden" name="juvenileId" value=<bean:write  name="traits" property="juvenileNum" />>
                </logic:iterate>
             </logic:notEmpty>
             <html:submit onclick="return this;" value="Back to Query Results" styleId="btnBackToResults" onclick="spinner()"/>		      
            </html:form>           
        </td>
    </tr>
    <tr><td colspan="2" align="center"></td></tr>
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