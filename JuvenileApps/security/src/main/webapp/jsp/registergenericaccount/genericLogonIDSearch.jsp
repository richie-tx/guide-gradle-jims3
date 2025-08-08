<!DOCTYPE HTML>
<!-- 07/28/2006	 CShimek   - Create JSP -->
<!-- 12/18/2006  CShimek   - defect#37932 Add missing multiple submit key check -->
<!-- 02/20/2007  CShimek   - #39739 change generic Logon Id values to register generic account	-->
<!-- 02/05/2009  CShimek   - #56860 add Back to Top  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
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
<title><bean:message key="title.heading" /> - genericLogonIDSearch.jsp</title>

<!-- JAVASCRIPT FILES -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/registerGenericAccount/genericLogonIDSearch.js" ></script>
</head>
<!--END HEADER TAG -->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)" >
<html:form action="/displayGenericLogonIDSearchResults" target="content" focus="searchLogonId">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|193">
<!-- BEGIN HEADING TABLE -->
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="prompt.genericAccount"/> <bean:message key="prompt.search"/></td>
	</tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
	<table width="100%">
		<tr>		  
			<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
		</tr>   	  
	</table>
<!-- END ERROR TABLE -->
<!-- BEGIN PAGINATION HEADER TAG -->
<br>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager
    index="center"
    maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10"
    export="offset,currentPageNumber=pageNumber"
    scope="request">
 <%--   <input type="hidden" name="pager.offset" value="<%= offset %>"> --%>
<!-- END PAGINATION HEADER TAG -->
<!-- BEGIN INSTRUCTION TABLE -->
<table align="center" width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Enter full or partial User ID then select Find to search.</li>
				<li>Select Register Generic Account button to begin process to register new generic account.</li>
			</ul>
		</td>
	</tr>
</table>
<table width="98%" cellpadding="0" cellspacing="0" >
	<tr>
		<td class="required"><bean:message key="prompt.requiredFields"/></td>
	</tr>	
</table>
<!-- END INSTRUCTION TABLE -->	
	
<table width="98%" cellpadding="4" cellspacing="0" class="borderTableBlue" >
	<tr class="detailHead">
		<td class="detailHead"><bean:message key="prompt.searchFor"/> <bean:message key="prompt.genericAccount"/>s</td>
	</tr>
	<tr>
		<td align="center">
<!-- BEGIN SEARCH TABLE -->	
			<table border="0" cellspacing="1" cellpadding="2" width="100%">
				<tr>
					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond" /> <bean:message key="prompt.JIMS"/> <bean:message key="prompt.userId"/></td>
					<td class="formDe"><html:text property="searchLogonId" size="8" maxlength="8" /></td>
				</tr>				
				<tr>
					<td class="formDeLabel"></td>
					<td class="formDe">
						<html:submit property="submitAction" onclick="return submitPage(this.form) && disableSubmit(this, this.form);"><bean:message key="button.find"/></html:submit>
						<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.refresh"/></html:submit>			
					</td>
				</tr>
			</table>
<!-- END SEARCH TABLE -->	
		</td>
	</tr>
	<logic:notEmpty name="genericLogonIdForm" property="users">
	<tr>
		<td align="center">
			<bean:write name="genericLogonIdForm" property="searchResultsCount"/>
			<bean:message key="prompt.searchResults"/> <bean:message key="prompt.found"/>
		</td>
	</tr>
	<tr>
		<td align="center">
<!-- BEGIN SEARCH RESULTS TABLE -->	
			<table width="100%" cellspacing="0" cellpadding="0" class="borderTable">
				<tr>
					<td>	
						<table border="0" width="100%" cellspacing="1" cellpadding="4">
							<tr class="formDeLabel">
								<td width="1%" nowrap><bean:message key="prompt.JIMS"/> <bean:message key="prompt.userId"/>
								    <jims:sortResults beanName="genericLogonIdForm" results="users" 
                                       primaryPropSort="logonId" primarySortType="STRING" defaultSort="true" 
                                       defaultSortOrder="ASC" sortId="1" />
                                </td>
								<td width="1%" nowrap><bean:message key="prompt.JIMS"/> <bean:message key="prompt.password"/>
								    <jims:sortResults beanName="genericLogonIdForm" results="users" 
                                       primaryPropSort="password" primarySortType="STRING" defaultSort="false" 
                                       defaultSortOrder="ASC" sortId="2" />
                                </td>							
								<td width="1%" nowrap><bean:message key="prompt.JIMS"/> <bean:message key="prompt.departmentCode"/>
								    <jims:sortResults beanName="genericLogonIdForm" results="users" 
                                       primaryPropSort="departmentId" primarySortType="STRING" defaultSort="false" 
                                       defaultSortOrder="ASC" sortId="3" />
                                </td>
								<td>&nbsp;</td>							
							</tr>
							<% int RecordCounter = 0; %>
							<logic:iterate id="userIterator" name="genericLogonIdForm" property="users" indexId="index">
							<pg:item>
							<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" id=<%=RecordCounter++%>>				
        			            <td>
                    			  	<bean:write name="userIterator" property="logonId"/>
								</td>
            			        <td>
                      				<bean:write name="userIterator" property="password"/>
								</td>
            			        <td>
                      				<bean:write name="userIterator" property="departmentId"/>
								</td>
								<td class="boldText">
									<a href="/<msp:webapp/>handleGenericLogonIDSelection.do?genericLogonId=<bean:write name="userIterator" property="genericAccountId"/>&action=edit" ><bean:message key="prompt.edit"/></a>&nbsp;
										<logic:equal name="userIterator" property="canInactivate" value="Y">
											|&nbsp;&nbsp;<a href="/<msp:webapp/>handleGenericLogonIDSelection.do?genericLogonId=<bean:write name="userIterator" property="genericAccountId"/>&action=inactivate" ><bean:message key="prompt.inactivate"/></a>		
										</logic:equal>				
								</td>
							</tr>
							</pg:item>
						</logic:iterate>
					</table>
<!-- END SEARCH RESULTS TABLE -->
				  </td>
			   </tr>
			</table>		
			<br>
		</td>
	</tr>
	</logic:notEmpty>
<!-- BEGIN PAGINATION NAVIGATION ROW -->
	<tr>
		<td>
			<table align="center">
				<tr>
					<td>
						<pg:index>
							<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
								<tiles:put name="pagerUniqueName" value="pagerSearch"/>
								<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
							</tiles:insert>
						</pg:index>
					</td>
				</tr>
			</table>
		</td>	
	</tr>	
<!-- END PAGINATION NAVIGATION ROW -->	
</table>
<br>
<!-- BEGIN BUTTON TABLE -->
<table width="98%" align="center">
	<tr>
		<td align="center">
			<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.registerGenericAccount"/></html:submit>
		</td>
	</tr>
</table>
<!-- END BUTTON TABLE -->
<!-- BEGIN PAGINATION CLOSING TAG -->
</pg:pager>
<!-- END PAGINATION CLOSING TAG -->
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>