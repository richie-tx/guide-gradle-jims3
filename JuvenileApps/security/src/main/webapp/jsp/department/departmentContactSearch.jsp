<!DOCTYPE HTML>
<!-- 08/22/2005	 Hien Rodriguez - Create JSP -->
<!-- 02/05/2009  C Shimek       - #56860 add Back to Top  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

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
<title><bean:message key="title.heading" /> - departmentContactSearch.jsp</title>
<!-- AUTO TAB JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/department/departmentContact.js"></script>
<html:javascript formName="contactUpdateForm"/>
</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/departmentFindContactUsers" target="content" focus="userName.lastName">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|44">	
<!-- BEGIN HEADING TABLE -->
<table width="98%" align="center">
  	<tr>
    	<td align="center" class="header"><bean:message key="title.contactCreate"/>&nbsp;<bean:message key="title.search"/></td> 
  	</tr>  	
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
<table width="98%" align="center">
  	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
  	</tr>   	  
</table>
<!-- END ERROR TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" align="center">
	<tr>
    	<td><ul>
        	<li>Enter search criteria then select Find Users button.</li>
		</ul></td>
	</tr>
</table>
<!-- HEADING TABLE -->
<!-- INSTRUCTION TABLE -->
<table width="98%">
    <tr>
   		<td class="required"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.requiredFieldsInstruction" /></td>
    </tr>
    <tr>
    	<td>
      	<table align="center" border="0">
	    	<tr>
	    		<td class="subhead"><bean:message key="prompt.department"/>:</td>
	    		<td ><bean:write name="departmentForm" property="departmentName" /></td>
	    	</tr>
	  	</table>
		</td>
	</tr>
</table>
<!-- BEGIN SEARCH CONTACT TABLE -->
<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%" align="center">
	<tr class="detailHead">
		<td class="detailHead">Search for User(s) - <bean:write name="departmentForm" property="agencyName" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<table border="0" cellspacing="1" cellpadding="2" width="100%">
				<tr>
					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userLastName"/></td>
					<td class="formDe"><html:text property="userName.lastName" maxlength="75" size="30"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap><bean:message key="prompt.userFirstName"/></td>
					<td class="formDe" ><html:text property="userName.firstName" maxlength="50" size="25"/></td>
				</tr>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.userId"/></td>
					<td class="formDe"><html:text property="userLogonId" maxlength="5" size="5"/>&nbsp;</td>
				</tr>
				<tr>
					<td class="formDeLabel"></td>
					<td class="formDe">	
						<html:submit property="submitAction" onclick="return checkFindUsersFields(this.form)"><bean:message key="button.findUsers" /></html:submit>&nbsp;
						<html:submit property="submitAction"><bean:message key="button.refresh"/></html:submit>
					</td> 	  	                         
				</tr>
			</table>
		</td>
	</tr>
	<!-- BEGIN USERS LIST TABLE -->	
	<logic:notEmpty name="departmentForm" property="userList">	                
		<tr>
			<td align="center"><bean:size id="usersSize" name="departmentForm" property="userList"/>
					    <bean:write name="usersSize"/>&nbsp; search results found.</td>
		</tr>		
		<tr>
			<td colspan="2" align="center">
			<div class="scrollingDiv200" >				                
            <table border="0" width="100%" cellspacing="1" cellpadding="2">            	        	
				<tr bgcolor="#cccccc">
					<td class="subhead" width="1%">&nbsp;</td>
					<td class="subhead"><bean:message key="prompt.userName"/>
					    <jims:sortResults beanName="departmentForm" results="userList" 
                              primaryPropSort="lastName" primarySortType="STRING"
                              secondPropSort="firstName" secondarySortType="STRING" defaultSort="true" 
                              defaultSortOrder="ASC" sortId="1" />
                    </td>
					<td class="subhead"><bean:message key="prompt.userId"/>
					    <jims:sortResults beanName="departmentForm" results="userList" 
                              primaryPropSort="logonId" primarySortType="STRING"
                              defaultSort="false" defaultSortOrder="ASC" sortId="2" /></td>
					<td class="subhead"><bean:message key="prompt.departmentName"/></td>
				</tr>
				<logic:iterate id="userIndex" name="departmentForm" property="userList" indexId="index1">
					<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
						<td align="center"><html:radio idName="userIndex" value="logonId" name="departmentForm" property="logonId"> <%-- onclick="chooseRow(this);" --%>
										   </html:radio></td>										 				 				  
						<td><bean:write name="userIndex" property="lastName" />,&nbsp;<bean:write name="userIndex" property="firstName" />&nbsp;<bean:write name="userIndex" property="middleName" /></td>
						<td><bean:write name="userIndex" property="logonId" /></td>
						<td><bean:write name="userIndex" property="departmentName" /></td>
					</tr>
				</logic:iterate>
			</table>      
            </div>
            <table>
				<tr>
					<td colspan="4" align="center">
						<html:submit property="submitAction"> 
							<bean:message key="button.selectUser"/>
						</html:submit>
					</td>			
				</tr>
			</table>            			  
            </td>
		</tr>
	</logic:notEmpty>       
	<!-- END USERS LIST TABLE -->
</html:form>
<html:form styleId="formTwo" action="/departmentHandleContactAddRemove" target="content">
	<!-- BEGIN CREATE CONTACT TABLE -->
	<tr>
		<td colspan="2" align="center">		
			<table class="borderTableBlue" cellpadding="2" cellspacing="1" width="100%">
				<tr>
					<td colspan="4" class="detailHead">
						<table width="100%" cellpadding="2" cellspacing="1">
							<tr>
								<td class="detailHead"><bean:message key="prompt.createContactInfo"/></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="formDeLabel" valign="top" nowrap><bean:message key="prompt.name"/></td>
					<td colspan="3" class="formDe" >
						<table border="0" cellspacing="1" width="100%">
							<tr>
								<td class="formDeLabel" colspan="2"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.last"/>
							</tr>
							<tr>
								<td class="formDe" colspan="2"><html:text name="departmentForm" property="contactName.lastName" size="30" maxlength="75" /></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.first"/></td>
								<td class="formDeLabel"><bean:message key="prompt.middle"/></td>
							</tr>
							<tr>
								<td class="formDe"><html:text name="departmentForm" property="contactName.firstName" size="25" maxlength="50"/></td>
								<td class="formDe"><html:text name="departmentForm" property="contactName.middleName" size="25" maxlength="50"/></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="formDeLabel" ><bean:message key="prompt.userId"/></td>
					<td class="formDe"  colspan="3"><html:text name="departmentForm" property="logonId" maxlength="5" size="5"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" nowrap><bean:message key="prompt.jobTitle"/></td>
					<td class="formDe" colspan="3"><html:text name="departmentForm" property="contactJobTitle" size="50" maxlength="50" /></td>
				</tr>
				<tr>
					<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.2.diamond" /><bean:message key="prompt.primaryContact" /></td>
					<td class="formDe" > <bean:message key="prompt.yes"/>
						<html:radio name="departmentForm" property="primaryContact" value="Y" />
						&nbsp;&nbsp; <bean:message key="prompt.no"/>
						<html:radio name="departmentForm" property="primaryContact" value="N" />
					</td>
					<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.liaisonTraining" /></td>
					<td class="formDe" > <bean:message key="prompt.yes"/>
						<html:radio name="departmentForm" property="liaisonTrainingInd" value="Y" />
						&nbsp;&nbsp; <bean:message key="prompt.no"/>
						<html:radio name="departmentForm" property="liaisonTrainingInd" value="N" />
					</td>
				</tr>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.phone"/></td>
					<td class="formDe" colspan="3">
						<html:text name="departmentForm" property="contactPhoneNumber.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> -
						<html:text name="departmentForm" property="contactPhoneNumber.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> -
						<html:text name="departmentForm" property="contactPhoneNumber.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/>&nbsp;<b><bean:message key="prompt.ext"/></b>
						<html:text name="departmentForm" property="contactPhoneNumber.ext" size="5" maxlength="5" onkeyup="return autoTab(this, 5);"/></td>
				</tr>
				<tr>
					<td class="formDeLabel" ><bean:message key="prompt.email"/></td>
					<td class="formDe" colspan="3"><html:text name="departmentForm" property="contactEmail"  size="50" maxlength="100" /></td>
				</tr>
				<tr>
					<td align="center" colspan="4"><html:submit property="submitAction" onclick="return validateDepartmentContacts(this.form)&& checkRadio(this.form);"><bean:message key="button.addContactToList"></bean:message></html:submit></td>					
				</tr>
			</table>		
	<!-- END CREATE CONTACT TABLE -->
		</td>
	</tr>
	<!-- BEGIN CREATE CONTACT LIST -->
	<tr heigh="100%">
		<td colspan="2" align="center">
		<!-- added contacts shopping cart start-->
		<logic:notEmpty name="departmentForm" property="contactList">	
		<%   boolean showRemove = false; %>
			<br>
			<div class="scrollingDiv200">
			<table cellpadding="2" cellspacing="1" width="100%">
				<tr height="100%">
					<td colspan="4" class="detailHead">
						<table width="100%" cellpadding="2" cellspacing="1" height="100%">
							<tr height="100%">
								<td class="detailHead"><bean:message key="prompt.currentSelectedUsersList"/>
								    <jims:sortResults beanName="departmentForm" results="userList" 
                                         primaryPropSort="contactName.lastName" primarySortType="STRING"
                                         secondPropSort="contactName.firstName" secondarySortType="STRING"
                                         defaultSort="true" defaultSortOrder="ASC" sortId="1" />
                                </td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
				<td>
				<table width="100%" cellpadding="4" cellspacing="1" border="0" height="100%">
					<tr class="formDeLabel" height="100%">
						<td><bean:message key="prompt.remove"/></td>
						<td colspan="4"><bean:message key="prompt.contactInfo"/></td>
					</tr>
					<logic:iterate id="contactList" name="departmentForm" property="contactList">
					<tr class="formDe" height="100%">											
						<td valign="top" rowspan="6" width="1%" align="center">
						  <logic:empty name="contactList" property="contactId">
						  <%  showRemove=true; %>
						   <html:checkbox indexed="true"  name="contactList" property="deletable" value="true"/>
						   </logic:empty>
						   </td>										 				 				  
						<td class="formDeLabel" ><bean:message key="prompt.name"/></td>
						<td class="formDe"  colspan="3">
						  <bean:write name="contactList" property="lastName" />,&nbsp;
						  <bean:write name="contactList" property="firstName" />&nbsp;
						  <bean:write name="contactList" property="middleName" />
						</td>
					</tr>
					<tr height="100%">	
						<td class="formDeLabel" ><bean:message key="prompt.userId"/></td>
						<td class="formDe"  colspan="3"><bean:write name="contactList" property="logonId" /></td>
					</tr>		
					<tr height="100%">
						<td class="formDeLabel" ><bean:message key="prompt.jobTitle"/></td>
						<td class="formDe"  colspan="3"><bean:write name="contactList" property="title" /></td>
					</tr>
<%--					<tr>	
						<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.liaisonTraining"/></td>
						<td class="formDe" ><bean:write name="contactList" property="liaisonTraining" /></td>
					</tr> --%>
					<tr height="100%">
						<td class="formDeLabel"  width="1%" nowrap><bean:message key="prompt.primaryContact" /></td>
						<td class="formDe">
							<logic:equal name="contactList" property="primaryContact" value="Y">YES</logic:equal>
							<logic:equal name="contactList" property="primaryContact" value="N" >NO</logic:equal>
							<input type="hidden" name="isPrimary" value=<bean:write name="contactList" property="primaryContact"/>>
						</td>
						<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.liaisonTraining" /></td>
						<td class="formDe">
							<logic:equal name="contactList" property="liaisonTrainingInd" value="Y">YES</logic:equal>
							<logic:equal name="contactList" property="liaisonTrainingInd" value="N">NO</logic:equal>
						</td>
					</tr>
					<tr height="100%">
					<td class="formDeLabel"><bean:message key="prompt.phone"/></td>
						<td class="formDe" colspan="3"><bean:write name="contactList" property="formattedPhone" />
							<logic:notEmpty name="contactList" property="phoneExt">
								<b><bean:message key="prompt.ext"/></b>&nbsp;<bean:write name="contactList" property="phoneExt" />
							</logic:notEmpty>
					</td>
					</tr> 
					<tr height="100%">
						<td class="formDeLabel" ><bean:message key="prompt.email"/></td>
						<td class="formDe"  colspan="3"><bean:write name="contactList" property="email" /></td>
					</tr>					
					<tr height="100%"><td colspan="4">&nbsp;</td></tr>					
				</logic:iterate>				
				</table>
				</td>
				</tr>
			</table>
		</div>
		<% if(showRemove) { %>
		<table>
			<tr>
				<td align="center"><html:submit property="submitAction"><bean:message key="button.removeContactFromList"></bean:message></html:submit></td>			
			</tr>
		</table>
		<% } %>
	</logic:notEmpty> 
	</td>
	</tr>
	<!-- END CREATE CONTACT LIST -->
</table>
<!-- END SEARCH CONTACT TABLE -->
<br>
<!-- BEGIN BUTTON TABLE -->
<table width="98%" border="0">
	<tr>
        <td align="center">
		    <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				<bean:message key="button.back"></bean:message></html:button>&nbsp;
		    <logic:equal name="departmentForm" property="action" value="contactCreate">			
				<input type="submit" value="<bean:message key='button.next'/>" name="submitAction" onclick="javascript:changeFormActionURL('formTwo', '/<msp:webapp/>departmentDisplayContactSummary.do', true);"> &nbsp;			    			
			</logic:equal>
			<logic:equal name="departmentForm" property="action" value="deptCreate">	
				<input type="submit" value="<bean:message key='button.next'/>" name="submitAction" onclick="javascript:changeFormActionURL('formTwo', '/<msp:webapp/>departmentDisplaySummary.do', false);"> &nbsp;
			</logic:equal>
			<logic:equal name="departmentForm" property="action" value="deptUpdate">	
				<input type="submit" value="<bean:message key='button.next'/>" name="submitAction" onclick="javascript:changeFormActionURL('formTwo', '/<msp:webapp/>departmentDisplayContactSummary.do', true);"> &nbsp;
			</logic:equal>        
			<html:reset><bean:message key="button.reset"/></html:reset>&nbsp;              
		    <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>		
		</td>
    </tr>
</table>
<!-- END BUTTON TABLE -->

</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>