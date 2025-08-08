<!DOCTYPE HTML>
<!-- 08/29/2005	 Hien Rodriguez - Create JSP -->
<!-- 05/24/2006  C. Shimek      - revise display field order to match other contact pages. -->
<!-- 01/10/2007  C Shimek       - #38306 add multiple submit functionality  -->
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

<html:base />
<title><bean:message key="title.heading" /> - departmentManageContactSummary.jsp</title>
<!-- APP JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>
<!--END HEADER TAG-->

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<!-- BEGIN HEADING TABLE -->
<table width="98%" align="center">
  	<tr>
    	<td align="center" class="header">    
    		<logic:equal name="departmentForm" property="action" value="view">
				<bean:message key="title.contactDetails"/>		
			</logic:equal>			
			<logic:equal name="departmentForm" property="action" value="update">
				<bean:message key="title.contactUpdate"/>&nbsp;<bean:message key="title.summary"/>	
			</logic:equal>
			<logic:equal name="departmentForm" property="action" value="delete">
				<bean:message key="title.contactDelete"/>&nbsp;<bean:message key="title.summary"/>	
			</logic:equal>				
			<logic:equal name="departmentForm" property="action" value="create">
				<bean:message key="title.contactCreate"/>&nbsp;<bean:message key="title.summary"/>	
			</logic:equal>			
			<logic:equal name="departmentForm" property="action" value="updateDept">
				<bean:message key="title.contactCreate"/>&nbsp;<bean:message key="title.summary"/>	
			</logic:equal>
		
			<logic:equal name="departmentForm" property="action" value="confirmCreate">
				<bean:message key="title.contactCreate"/>&nbsp;<bean:message key="title.confirmation"/>
			</logic:equal>
			<logic:equal name="departmentForm" property="action" value="confirmUpdateDept">
				<bean:message key="title.contactCreate"/>&nbsp;<bean:message key="title.confirmation"/>
			</logic:equal>			
			<logic:equal name="departmentForm" property="action" value="confirmUpdate">
				<bean:message key="title.contactUpdate"/>&nbsp;<bean:message key="title.confirmation"/>
			</logic:equal>
			<logic:equal name="departmentForm" property="action" value="confirmDelete">
				<bean:message key="title.contactDelete"/>&nbsp;<bean:message key="title.confirmation"/>
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
	<logic:equal name="departmentForm" property="action" value="view">
			<td><ul>
				<li>Select the Create button to create more contacs.</li>
				<li>Select the Modify button to update this contact.</li>
				<li>Select the Delete button to delete this contact.</li>				
			</ul></td>
		</logic:equal>		
		<logic:equal name="departmentForm" property="action" value="update">
			<td><ul><li>The following contact will be updated when you select the Finish button.</li></ul></td>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="delete">
			<td><ul><li>The following contact will be deleted when you select the Finish button.</li></ul></td>
		</logic:equal>		
		
		<logic:equal name="departmentForm" property="action" value="confirmUpdate">
			<td align="center" class="confirm">The contact has been successfully updated.</td>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="confirmDelete">
			<td align="center" class="confirm">This contact has been successfully deleted.</td>
		</logic:equal>		
		<logic:equal name="departmentForm" property="action" value="create">
			<td><ul><li>Review lists of contacts then select Finish button to continue.</li></ul></td>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="confirmCreate">
			<td align="center" class="confirm">This contact has been successfully created.</td>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="updateDept">
			<td><ul><li>Review lists of contacts then select Finish button to continue.</li></ul></td>
		</logic:equal>
		<logic:equal name="departmentForm" property="action" value="confirmUpdateDept">
			<td align="center" class="confirm">This contact has been successfully added.</td>
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

<%int RecordCounter = 1;
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
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.name"/></td>
		<td class="formDe" colspan="3"><bean:write name="departmentForm" property="contactName.formattedName" />
		 </td>				
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.userId"/></td>
		<td class="formDe" colspan="3"><bean:write name="departmentForm" property="logonId" /></td>				
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.title"/></td>
		<td class="formDe" colspan="3"><bean:write name="departmentForm" property="contactJobTitle" /></td>				
	</tr>
	<tr>			
		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.primaryContact"/></td>
		<td class="formDe"><bean:write name="departmentForm" property="primaryContactAsYesNo" /></td>
		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.liaisonTraining" /></td>
		<td class="formDe"><bean:write name="departmentForm" property="liaisonTrainingIndAsYesNo" /></td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.phone"/></td>
		<td class="formDe" colspan="3"><bean:write name="departmentForm" property="contactPhoneNumber.formattedPhoneNumber" />
			<logic:notEmpty name="departmentForm" property="contactPhoneNumber.ext">
				<b><bean:message key="prompt.ext"/></b>&nbsp;<bean:write name="departmentForm" property="contactPhoneNumber.ext" />
			</logic:notEmpty>
		</td>
	</tr>		
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.email"/></td>
		<td class="formDe" colspan="3"><bean:write name="departmentForm" property="contactEmail" /></td>			
	</tr>	
	<tr><td colspan="4">&nbsp;</td></tr>

	<% if (RecordCounter==0){%>
	<tr>
		<td class="formDe" colspan="4">There are no new contacts to add/update</td>			
	</tr>	
	
	<% } %>
				<logic:empty name="departmentForm" property="contactList">	
				<tr>
					<td class="formDe" colspan="4">There are no new contacts to add/update</td>			
				</tr>
				</logic:empty>
</table>


<!-- END CREATE CONTACT TABLE -->
<br>
<!-- BEGIN BUTTON TABLE -->
<table width="98%" border="0">
	<tr>
        <td align="center">
        	<logic:equal name="departmentForm" property="action" value="view">				
				<html:form action="/departmentHandleContactSelection">
					<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
						<bean:message key="button.back"></bean:message></html:button>
					<input type="submit" value="<bean:message key='button.createContact'/>" name="submitAction" onclick="javascript:changeFormActionURL('departmentForm', '/<msp:webapp/>departmentDisplayDetails.do?action=contactCreate', false);">
					<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.modifyContact"/></html:submit>
					<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.deleteContact"/></html:submit>					
					<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|31">	
				</html:form>			    			
			</logic:equal>
			
			<logic:equal name="departmentForm" property="action" value="update">				
				<html:form action="/departmentSubmitContactSummary">
					<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
						<bean:message key="button.back"></bean:message></html:button>
					<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>					
					<input type="submit" value="<bean:message key='button.cancel'/>" name="submitAction" onclick="javascript:changeFormActionURL('departmentForm', '/<msp:webapp/>departmentManageDepartments.do', true);">
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|21">	
				</html:form>			    			
			</logic:equal>
			<logic:equal name="departmentForm" property="action" value="confirmUpdate">	
				<html:form action="/departmentDisplayContactList">
					<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.returnToContactList"/></html:submit>					
					<input type="submit" value="<bean:message key='button.mainPage'/>" name="submitAction" onclick="javascript:changeFormActionURL('departmentForm', '/<msp:webapp/>jsp/mainMenu.jsp', false);">
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|19">	
				</html:form>			    			
			</logic:equal>

			<logic:equal name="departmentForm" property="action" value="delete">				
				<html:form action="/departmentSubmitContactSummary">
					<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
						<bean:message key="button.back"></bean:message></html:button>
					<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>					
					<input type="submit" value="<bean:message key='button.cancel'/>" name="submitAction" onclick="javascript:changeFormActionURL('departmentForm', '/<msp:webapp/>departmentManageDepartments.do', true);">
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|270">	
				</html:form>			    			
			</logic:equal>
			<logic:equal name="departmentForm" property="action" value="confirmDelete">			
				<html:form action="/departmentDisplayContactList">
					<html:submit property="submitAction"><bean:message key="button.returnToContactList"/></html:submit>					
					<input type="submit" value="<bean:message key='button.mainPage'/>" name="submitAction" onclick="javascript:changeFormActionURL('departmentForm', '/<msp:webapp/>jsp/mainMenu.jsp', false);">
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|269">	
				</html:form>			    			
			</logic:equal>	
			
        	<logic:equal name="departmentForm" property="action" value="create">				
				<html:form action="/submitDepartmentUpdate">
					<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
						<bean:message key="button.back"></bean:message></html:button>
					<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>					
					<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|45">	
				</html:form>			    			
			</logic:equal>
			<logic:equal name="departmentForm" property="action" value="confirmCreate">			
				<html:form action="/submitDepartmentUpdate">					
					<html:submit property="submitAction"><bean:message key="button.mainPage"/></html:submit>
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|43">	
				</html:form>			    			
			</logic:equal>

			<logic:equal name="departmentForm" property="action" value="updateDept">				
				<html:form action="/submitDepartmentUpdate">
					<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
						<bean:message key="button.back"></bean:message></html:button>
					<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>					
					<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|##">	
				</html:form>			    			
			</logic:equal>
			<logic:equal name="departmentForm" property="action" value="confirmUpdateDept">			
				<html:form action="/submitDepartmentUpdate">					
					<html:submit property="submitAction"><bean:message key="button.continue"/></html:submit>
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|##">	
				</html:form>			    			
			</logic:equal>					
		</td>
	</tr>
</table>
<!-- END BUTTON TABLE -->

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>