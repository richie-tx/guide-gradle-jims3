<!DOCTYPE HTML>
<!-- 08/29/2005	 Hien Rodriguez - Create JSP -->
<!-- 12/14/2007  C Shimek	    - revised title for create contact  -->
<!-- 01/10/2007  C Shimek       - #38306 add multiple submit functionality  -->
<!-- 03/13/2007  L Deen		    - #37894 & 37997 fix help file mapIDs -->
<!-- 02/05/2009  C Shimek       - #56860 add Back to Top  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />

<!-- APP JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<html:base />
<title><bean:message key="title.heading" /> - departmentContactSummary.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>  
</head>
<!--END HEADER TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<bean:define id="myContactId" name="departmentForm" property="contactId" type="java.lang.String"/>
<!-- BEGIN HEADING TABLE -->
<table width="98%" align="center">
  	<tr>
    	<td align="center" class="header">
    		<logic:equal name="departmentForm" property="action" value="contactCreate">
				<bean:message key="title.contactCreate"/> <bean:message key="title.summary"/>
				<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|45">		
			</logic:equal>	
    		<logic:equal name="departmentForm" property="action" value="view">
				<bean:message key="title.contactDetails"/>
				<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|31">		
			</logic:equal>			
			<logic:equal name="departmentForm" property="action" value="update">
				<bean:message key="title.contactUpdate"/>&nbsp;Summary
				<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|21">	
			</logic:equal>
			<logic:equal name="departmentForm" property="action" value="delete">
				<bean:message key="title.contactDelete"/>&nbsp;Summary
				<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|270">	
			</logic:equal>		
			
			<logic:equal name="departmentForm" property="action" value="confirmUpdate">
				<bean:message key="title.contactUpdate"/>&nbsp;Confirmation
				<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|19">	
			</logic:equal>
			<logic:equal name="departmentForm" property="action" value="confirmDelete">
				<bean:message key="title.contactDelete"/>&nbsp;Confirmation
				<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|269">
			</logic:equal>	
			<logic:equal name="departmentForm" property="action" value="confirmCreate">
				<bean:message key="title.contactCreate"/>&nbsp;Confirmation
				<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|43">		
			</logic:equal>								
		</td>
    </tr>
</table>
<!-- BEGIN ERROR TABLE -->
	<table width="98%" align="center">
		<TBODY>
			<tr>
				<td align="center" class="errorAlert"><html:errors></html:errors></td>
			</tr>
		</TBODY>
	</table>
	<!-- END ERROR TABLE -->
<!-- INSTRUCTION TABLE -->	  
<table width="98%" border="0">
	<tr>		
	<logic:equal name="departmentForm" property="action" value="contactCreate">
			<td><ul><li>The following contact(s) will be created/updated when you select the Finish button.</li></ul></td>
		</logic:equal>		
		<logic:equal name="departmentForm" property="action" value="view">
			<td><ul>
				<li>Select the Create button to create more contacs.</li>
				<li>Select the Modify button to update this contact.</li>
				<li>Select the Delete button to delete this contact.</li>				
			</ul></td>
		</logic:equal>		
		<logic:equal name="departmentForm" property="action" value="update">
			<td><ul><li>The following contact(s) will be updated when you select the Finish button.</li></ul></td>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="delete">
			<td><ul><li>The following contact(s) will be deleted when you select the Finish button.</li></ul></td>
		</logic:equal>		
		
		<logic:equal name="departmentForm" property="action" value="confirmUpdate">
			<td align="center" class="confirm">The contact(s) have been successfully updated.</td>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="confirmDelete">
			<td align="center" class="confirm">This contact(s) have been successfully deleted.</td>
		</logic:equal>	
		<logic:equal name="departmentForm" property="action" value="confirmCreate">
			<td align="center" class="confirm">The contact(s) have been successfully added.</td>
		</logic:equal>
	</tr>			
</table>
<br> 

<table align="center" border="0"> 
	<tr>
    	<td class="subhead"><bean:message key="prompt.department"/>:</td>
  	  	<td><bean:write name="departmentForm" property="departmentName" /></td>
	</tr>
</table>
<!-- BEGIN CREATE CONTACT TABLE -->

<%int RecordCounter = 0;
  String bgcolor = "";%>  
				
<table class="borderTableBlue" cellpadding="2" cellspacing="1" width="98%">
	<tr>
		<td colspan="4" class="detailHead">
			<table width="100%" cellpadding="2" cellspacing="1">
				<tr>
					<td class="detailHead"><bean:message key="prompt.contactInfo"/></td>					
				</tr>
			</table>
		</td>
	</tr>	
	<logic:notEmpty name="departmentForm" property="contactList">	
		<logic:iterate id="contactListIndex" name="departmentForm" property="contactList">
			<logic:empty name="contactListIndex" property="contactId">
				<%RecordCounter++; %>  
					<bean:write name="contactListIndex" property="contactId"/>
					
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.name"/></td>
		<td class="formDe"  colspan="3"><bean:write name="contactListIndex" property="lastName" />,&nbsp;
						  <bean:write name="contactListIndex" property="firstName" />&nbsp;
						  <bean:write name="contactListIndex" property="middleName" /></td>				
	</tr>
	<tr>	
		<td class="formDeLabel"><bean:message key="prompt.userId"/></td>
		<td class="formDe"  colspan="3"><bean:write name="contactListIndex" property="logonId" /></td>				
	</tr>	
	<tr>
		<td class="formDeLabel" ><bean:message key="prompt.jobTitle"/></td>
		<td class="formDe"  colspan="3"><bean:write name="contactListIndex" property="title" /></td>
	</tr>
	<tr>	
		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.primaryContact"/></td>
		<td class="formDe" >
			<bean:write name="contactListIndex" property="liaisonTrainingIndAsYesNo" />
		</td>
		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.liaisonTraining"/></td>
		<td class="formDe"  width="25%">
			<bean:write name="contactListIndex" property="primaryContactAsYesNo" />
		</td>
	</tr>
	<tr>	
		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.phone"/></td>
		<td class="formDe"  colspan="3"><bean:write name="contactListIndex" property="formattedPhone" />&nbsp;
			<logic:notEmpty name="contactListIndex" property="phoneExt">
				<b><bean:message key="prompt.ext"/></b>&nbsp;<bean:write name="contactListIndex" property="phoneExt" />
			</logic:notEmpty>
		</td>	
	</tr>		
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.email"/></td>
		<td class="formDe"  colspan="3"><bean:write name="contactListIndex" property="email" /></td>			
	</tr>	
	<tr><td colspan="4">&nbsp;</td></tr>
	</logic:empty>
	</logic:iterate>
		<% if (RecordCounter==0){%>
			<tr>
				<td class="formDe"  colspan="4">There are no new contacts to add/update</td>			
			</tr>	
	<% } %>
	</logic:notEmpty> 	
	<logic:empty name="departmentForm" property="contactList">	
		<tr>
			<td class="formDe"  colspan="4">There are no new contacts to add/update</td>			
		</tr>
	</logic:empty>
</table>
<!-- END CREATE CONTACT TABLE -->
<br>

<!-- BEGIN BUTTON TABLE -->
<table width="98%" border="0">
	<tr>
        <td align="center">
        	<logic:equal name="departmentForm" property="action" value="contactCreate">
				<html:form action="/departmentSubmitContactCreateUpdate">
				<!-- input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|31" -->
				<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
						<bean:message key="button.back" /></html:button>&nbsp;
				<logic:notEmpty name="departmentForm" property="contactList">
				<% if (RecordCounter!=0){%>
					<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>
				<% } %>
				</logic:notEmpty>	
					<input type="submit" value="<bean:message key='button.cancel'/>" name="submitAction" onclick="javascript:changeFormActionURL('departmentForm', '/<msp:webapp/>departmentManageDepartments.do', false);">
				</html:form>	
			</logic:equal>
			<logic:equal name="departmentForm" property="action" value="confirmUpdate">	
				<html:form action="/departmentDisplayContactList">
					<!-- input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|19" -->	
					<html:submit property="submitAction"><bean:message key="button.returnToContactList"/></html:submit>					
					<html:submit property="submitAction"><bean:message key="button.mainPage"/></html:submit>
				</html:form>	
			</logic:equal>	
			<logic:equal name="departmentForm" property="action" value="confirmCreate">	
				<html:form action="/departmentDisplayContactList">
					<!-- input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|19"	-->
					<html:submit property="submitAction"><bean:message key="button.returnToContactList"/></html:submit>					
					<html:submit property="submitAction"><bean:message key="button.mainPage"/></html:submit>
				</html:form>	
			</logic:equal>	

        	<logic:equal name="departmentForm" property="action" value="update">	
				<html:form action="/departmentDisplayContactList">
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|21">	
					<html:submit property="submitAction"><bean:message key="button.returnToContactList"/></html:submit>					
					<html:submit property="submitAction"><bean:message key="button.mainPage"/></html:submit>
				</html:form>	
			</logic:equal>		    							
		</td>
	</tr>	
</table>
<!-- END BUTTON TABLE -->

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>