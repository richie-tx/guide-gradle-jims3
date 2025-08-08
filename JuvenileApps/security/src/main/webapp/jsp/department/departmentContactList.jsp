<!DOCTYPE HTML>
<!-- 08/29/2005	 Hien Rodriguez - Create JSP -->
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
<title><bean:message key="title.heading" /> - departmentContactList.jsp</title>
<!-- AUTO TAB JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body topmargin="0" leftmargin="0">
<html:form action="/departmentManageDepartments" target="content">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|33">	
<!-- BEGIN HEADING TABLE -->
<table width="98%">
  	<tr>
    	<td align="center" class="header"><bean:message key="title.contactList"/></td> 
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
		<td><ul><li>Click on User Name hyperlink to view Contact record or click on update/delete hyperlink to Update/Delete contact.</li></ul></td>
	</tr>     
</table>
<!-- END INSTRUCTION TABLE -->

<table align="center" border="0">
	<tr>
		<td class="subhead"><bean:message key="prompt.department"/>:&nbsp;</td>
		<td colspan="3"><bean:write name="departmentForm" property="departmentName" /></td>
	</tr>	
</table>
<!-- BEGIN CONTACT TABLE -->
<table width="98%" border="0" cellspacing="0" cellpadding="2" align="center" class="borderTableBlue">
	<tr>
      	<td class="detailHead" nowrap><bean:message key="prompt.contactRecords"/></td>
	</tr>
	<tr>
	   	<td>					  
			<table width="100%" border="0" cellpadding="4" cellspacing="1" align="center" >
			<logic:notEmpty name="departmentForm" property="contactList">	
				<tr bgcolor="#999999">										
					<td class="subhead"><bean:message key="prompt.name"/>
					    <jims:sortResults beanName="departmentForm" results="contactList" 
                              primaryPropSort="lastName" primarySortType="STRING"
                              secondPropSort="firstName" secondarySortType="STRING" defaultSort="true" 
                              defaultSortOrder="ASC" sortId="1" />
                    </td>
					<td width="15%" class="subhead"><bean:message key="prompt.userId"/>
					    <jims:sortResults beanName="departmentForm" results="contactList" 
                              primaryPropSort="logonId" primarySortType="STRING"
                              defaultSort="false" defaultSortOrder="ASC" sortId="2" />
                    </td>
					<td class="subhead"><bean:message key="prompt.title"/>
					    <jims:sortResults beanName="departmentForm" results="contactList" 
                              primaryPropSort="title" primarySortType="STRING"
                              defaultSort="false" defaultSortOrder="ASC" sortId="3" /></td>
					<td width="15%"></td>					
				</tr>
				<%int RecordCounter = 0;
						String bgcolor = "";%>  
				
					<logic:iterate id="contactListIndex" name="departmentForm" property="contactList"  indexId="index">
						<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
							<td>
								<a href="/<msp:webapp/>departmentHandleContactSelection.do?submitAction=<bean:message key="button.view"/>&contactId=<bean:write name="contactListIndex" property="contactId"/>">
								<bean:write name="contactListIndex" property="lastName" />,&nbsp;<bean:write name="contactListIndex" property="firstName" />&nbsp;<bean:write name="contactListIndex" property="middleName" /></a></td>
							<td><bean:write name="contactListIndex" property="logonId" /></td>
							<td><bean:write name="contactListIndex" property="title" /></td>
							<td><a href="/<msp:webapp/>departmentHandleContactSelection.do?submitAction=<bean:message key="button.modifyContact"/>&contactId=<bean:write name="contactListIndex" property="contactId"/>">
								<bean:message key="prompt.edit"/></a>&nbsp;|&nbsp;
								<a href="/<msp:webapp/>departmentHandleContactSelection.do?submitAction=<bean:message key="button.deleteContact"/>&contactId=<bean:write name="contactListIndex" property="contactId"/>">
								<bean:message key="prompt.delete"/></a>
							</td>
						</tr>																
					</logic:iterate>
				</logic:notEmpty> 		
				<logic:empty name="departmentForm" property="contactList">	
				<tr class="normalRow">
							
							<td colspan="4">
								This department has no existing contacts.
							</td>
						</tr>	
				</logic:empty>		
			</table>				
      	</td>
	</tr>
</table>
<!-- END CONTACT TABLE -->	  
<br>
<!-- BEGIN BUTTON TABLE -->
<table width="98%" border="0">
	<tr>
        <td align="center">
        	<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
							<bean:message key="button.back"></bean:message></html:button>&nbsp;
         	<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>		
		</td>
	</tr>
</table>
<!-- END BUTTON TABLE -->

</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>