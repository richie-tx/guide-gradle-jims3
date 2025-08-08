<!DOCTYPE HTML>
<!-- 12/12/2006	 C Shimek   - Create JSP -->
<!-- 02/22/2007  C Shimek   - #39735 add JIMS User Id as inquiry field -->
<!-- 02/05/2009  C Shimek   - #56860 add Back to Top  -->

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
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
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
<title><bean:message key="title.heading" /> - jims2AccountSearch.jsp</title>

<!-- JAVASCRIPT FILES -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script> 
<script type="text/javascript" src="/<msp:webapp/>js/jims2Account/jims2AccountSearch.js" ></script> 
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>

</head>

<body onload="evalSearch(document.forms[0].searchTypeId)" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayJIMS2AccountSearchResults" target="content" >
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|194">

<!-- BEGIN HEADING TABLE -->
<table width='100%'>
	<tr>
		<td align="center" class="header">
			<bean:message key="prompt.search"/>&nbsp;<bean:message key="prompt.JIMS2"/>&nbsp;<bean:message key="prompt.account"/>
		</td>
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
<span id='generalInstr1' class='hidden'>	
<table align="center" width="98%" border="0" align="center">
	<tr>
		<td>
			<ul>
        		<li>Enter search criteria then select Find button to search.</li>
      		</ul>
		</td>
  	</tr>
</table>
</span>
<span id='generalInstr2' class='hidden'>	
<table align="center" width="98%" border="0">
	<tr>
		<td>
			<ul>
        		<li>Enter search criteria then select Find button to search.</li>
				<li>Full JIMS User ID required for search.</li>
      		</ul>
		</td>
  	</tr>
</table>
</span>

<span id='userIdReqd' class='hidden'>		
<table width="98%" cellpadding="0" cellspacing="0" align="center">
	<tr align="center"> 
	    <td  class="required"><bean:message key="prompt.requiredFields"/></td>
	<tr>	
</table>
</span> 	
<span id='userNameReqd' class='hidden'>
<table width="98%" cellpadding="0" cellspacing="0" align="center">
	<tr>	
		<td class="required">+ Indicates Last Name required to use this search field.</td>
	</tr>
</table>
</span>
<!-- END INSTRUCTION TABLE -->
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td align="center" valign="top">
<!-- BEGIN INQUIRE TYPE TABLE -->		
			<table border="0" cellspacing="1" cellpadding="2" width="98%" class="borderTableBlue" align="center">
				<tr>
					<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inquireBy"/></td>
					<td class="formDe">
<%-- these logic tags are used to maintain selection after refresh - struts does not seem to work --%>					
		     			<select name="searchTypeId" onchange="evalSearch(this)">
							<logic:equal name="jims2AccountForm" property="searchTypeId" value=""> 		     			
								<option value="JIMSUserId" selected>JIMS USER ID</option>
								<option value="JIMS2UserId">JIMS2 USER ID</option>								
								<option value="UserName">USER NAME</option>
							</logic:equal>
							<logic:equal name="jims2AccountForm" property="searchTypeId" value="JIMSUserId"> 		     			
								<option value="JIMSUserId" selected>JIMS USER ID</option>
								<option value="JIMS2UserId">JIMS2 USER ID</option>								
								<option value="UserName">USER NAME</option>
							</logic:equal>
							<logic:equal name="jims2AccountForm" property="searchTypeId" value="JIMS2UserId">
								<option value="JIMSUserId">JIMS USER ID</option>
								<option value="JIMS2UserId" selected>JIMS2 USER ID</option>								
								<option value="UserName">USER NAME</option>
							</logic:equal>
							<logic:equal name="jims2AccountForm" property="searchTypeId" value="UserName"> 		     			
								<option value="JIMSUserId">JIMS USER ID</option> <%--bug fixed as part of ie11 conversion --%>
								<option value="JIMS2UserId">JIMS2 USER ID</option>								
								<option value="UserName" selected>USER NAME</option>
							</logic:equal>
						</select>
					</td>
				</tr>
			</table> 
<!-- END INQUIRE BY TABLE -->	
			<br>
<!-- BEGIN JIMS USERID SEARCH TABLE -->		
			<span id='JIMSUserIdSearch' class='hidden'>		
			<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue" align="center">
				<tr>
					<td align="center">
						<table border="0" cellspacing="1" cellpadding="2" width="100%" align="center">
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond" /> <bean:message key="prompt.JIMS"/> <bean:message key="prompt.userId"/></td>
								<td class="formDe"><html:text property="searchJIMSLogonId" size="5" maxlength="8" /></td>							
							</tr>
							<tr>
								<td class="formDeLabel"></td>
								<td class="formDe">
									<html:submit property="submitAction" onclick="return validateSearchFields(this.form) && disableSubmit(this, this.form)"><bean:message key="button.find"/></html:submit>
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.refresh"/></html:submit>										
								</td>
							</tr>							
						</table>
					</td>
				</tr>
			</table>
			</span>	
<!-- END JIMS USERID SEARCH TABLE -->	
<!-- BEGIN JIMS2USERID SEARCH TABLE -->		
			<span id='JIMS2UserIdSearch' class='hidden'>		
			<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue" align="center">
				<tr>
					<td align="center">
						<table border="0" cellspacing="1" cellpadding="2" width="100%" align="center">
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond" /> <bean:message key="prompt.JIMS2UserId"/></td>
								<td class="formDe"><html:text property="searchJIMS2LogonId" size="50" maxlength="50" /></td>							
							</tr>
							<tr>
								<td class="formDeLabel"></td>
								<td class="formDe">
									<html:submit property="submitAction" onclick="return validateSearchFields(this.form) && disableSubmit(this, this.form)"><bean:message key="button.find"/></html:submit>
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.refresh"/></html:submit>										
								</td>
							</tr>							
						</table>
					</td>
				</tr>
			</table>
			</span>	
<!-- END JIMS2 USERID SEARCH TABLE -->	
<!-- BEGIN USER NAME SEARCH TABLE -->
			<span id='userNameSearch' class='hidden'>		
			<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue" align="center">
				<tr>
					<td align="center">
						<table border="0" cellspacing="1" cellpadding="2" width="100%">
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.lastName"/></td>
								<td class="formDe"><html:text property="searchLastName" size="50" maxlength="50" /></td>							
							</tr>
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.plusSign"/> <bean:message key="prompt.firstName"/></td>
								<td class="formDe"><html:text property="searchFirstName" size="50" maxlength="50" /></td>							
							</tr>
							<tr>
								<td class="formDeLabel"></td>
								<td class="formDe">
									<html:submit property="submitAction" onclick="return validateSearchFields(this.form)"><bean:message key="button.find"/></html:submit>
									<html:submit property="submitAction"><bean:message key="button.refresh"/></html:submit>										
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</span>	
<!-- END USER NAME SEARCH TABLE -->			
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td align="center">
			<!--search results start-->
			<logic:notEmpty name="jims2AccountForm" property="users">
				<bean:write name="jims2AccountForm" property="searchResultCount" />&nbsp;<bean:message key="prompt.searchResults"/>&nbsp;<bean:message key="prompt.found"/>
				<div class="scrollingDiv200" >
					<table border="0" width="100%" cellspacing="1" cellpadding="4" align="center"> 
						<tr class="formDeLabel" height="100%">
							<td><bean:message key="prompt.JIMS2UserId"/>
							    <jims:sortResults beanName="jims2AccountForm" results="users" 
                                       primaryPropSort="JIMS2LogonId" primarySortType="STRING" defaultSort="true" 
                                       defaultSortOrder="ASC" sortId="1" /></td>					
							<td><bean:message key="prompt.userName"/>
							    <jims:sortResults beanName="jims2AccountForm" results="users" 
                                       primaryPropSort="lastName" primarySortType="STRING"
                                       secondPropSort="firstName" secondarySortType="STRING" defaultSort="false" 
                                       defaultSortOrder="ASC" sortId="2" />
                            </td>
							<td><bean:message key="prompt.deptCode"/>
							    <jims:sortResults beanName="jims2AccountForm" results="users" 
                                       primaryPropSort="departmentId" primarySortType="STRING" defaultSort="false" 
                                       defaultSortOrder="ASC" sortId="3" /></td>							
							<td>&nbsp;</td>
						</tr>
						<% int RecordCounter = 0; 
							String bgcolor = "";%>
		 				<logic:iterate id="userIterator" name="jims2AccountForm" property="users"> 
						<pg:item>		 				
		 				<tr class= <% RecordCounter++;
			                    bgcolor = "alternateRow";             
               				    if (RecordCounter % 2 == 1)
			                        bgcolor = "normalRow";
               				    out.print(bgcolor);  %> height="100%">
							<td class="boldText">
								<a href="/<msp:webapp/>handleJIMS2AccountSelection.do?action=detail&jims2LogonId=<bean:write name="userIterator" property="JIMS2LogonId"/>" ><bean:write name="userIterator" property="JIMS2LogonId"/></a>
							</td>
							<td>
								<bean:write name="userIterator" property="lastName"/>,&nbsp;<bean:write name="userIterator" property="firstName"/>&nbsp;<bean:write name="userIterator" property="middleName"/> 
							</td>
							<td>
								<bean:write name="userIterator" property="departmentId"/>
							</td>
							<td>
						 		<logic:equal name="userIterator" property="status" value="A">
									<a href="/<msp:webapp/>handleJIMS2AccountSelection.do?action=update&jims2LogonId=<bean:write name="userIterator" property="JIMS2LogonId"/>" ><bean:message key="prompt.edit"/></a>
									<logic:equal name="jims2AccountForm" property="showInactive" value="Y">
										&nbsp;|&nbsp;<a href="/<msp:webapp/>handleJIMS2AccountSelection.do?action=inactivate&jims2LogonId=<bean:write name="userIterator" property="JIMS2LogonId"/>" ><bean:message key="prompt.inactivate"/></a>
									</logic:equal>	
								</logic:equal>
							</td>
						</tr>	
						</pg:item>	
						</logic:iterate>
					</table>
				</div>
				<br>
			</logic:notEmpty>
			<!--search results end-->
		</td>
	</tr>			
<!-- BEGIN PAGINATION NAVIGATOIN ROW -->
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
<!-- END PAGINATION NAVIGATOIN ROW -->				
</table>
<br>
<table align="center">
    <tr>
        <td align="center">
			<html:submit property="submitAction"><bean:message key="button.createNewJIMS2Account"/></html:submit>		
        </td>
    </tr>
</table>
<!-- BEGIN PAGINATION CLOSING TAG -->
</pg:pager>
<!-- END PAGINATION CLOSING TAG -->
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>