<!DOCTYPE HTML>
<!-- 08/01/2006	 C Shimek   - Create JSP -->
<!-- 02/06/2009  C Shimek   - #56860 add Back to Top  -->
<!-- 10/19/2015  R Young    - #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

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
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading" /> - securityInquiriesUserProfileSearchResults.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>

<body>
<html:form action="/displaySecurityInquiriesUserProfileDetails" target="content">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|133">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header"><bean:message key="title.inquiries"/> <bean:message key="prompt.userProfile"/> <bean:message key="title.searchResults"/></td>
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
    <input type="hidden" name="pager.offset" value="<%= offset %>">
<!-- END PAGINATION HEADER TAG -->
<!-- BEGIN INSTRUCTION TABLE -->
<table align="center" width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select hyperlink for more information.</li>
			</ul>
		</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN DETAILS TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center">
			<bean:write name="securityInquiriesForm" property="searchResultsCount"/>
			<bean:message key="prompt.searchResultsFoundFor"/> <bean:message key="prompt.userProfile"/>
		</td>
	</tr>
	<tr>
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" width="98%" cellpadding="0" cellspacing="0">
		<tr>
		<td>
			<table cellpadding="2" cellspacing="1" width="100%">
				<tr class="detailHead" id="firstRow">
					<td><bean:message key="prompt.name"/>
					    <jims:sortResults beanName="securityInquiriesForm" results="userProfiles" 
                                 primaryPropSort="userLastName" primarySortType="STRING"
                                 secondPropSort="userFirstName" secondarySortType="STRING" defaultSort="true" 
                                 defaultSortOrder="ASC" sortId="1" />
                    </td>					
					<td><bean:message key="prompt.userId"/>
					    <jims:sortResults beanName="securityInquiriesForm" results="userProfiles" 
                                 primaryPropSort="logonId" primarySortType="STRING"
                                 defaultSort="false" 
                                 defaultSortOrder="ASC" sortId="2" />
                    </td>
					<td><bean:message key="prompt.agencyCode"/>
					    <jims:sortResults beanName="securityInquiriesForm" results="userProfiles" 
                                 primaryPropSort="agencyId" primarySortType="STRING"
                                 defaultSort="false" 
                                 defaultSortOrder="ASC" sortId="3" /></td>
					<td><bean:message key="prompt.departmentCode"/>
					<jims:sortResults beanName="securityInquiriesForm" results="userProfiles" 
                                 primaryPropSort="departmentId" primarySortType="STRING"
                                 defaultSort="false" 
                                 defaultSortOrder="ASC" sortId="4" /></td>															
				</tr>
				<% int RecordCounter = 0; 
					String  bgcolor= ""; %>
 				<logic:iterate id="userProfileIterator" name="securityInquiriesForm" property="userProfiles"> 
				<pg:item>
				<tr class= <% RecordCounter++;
			                   bgcolor = "normalRow";                      
               				   if (RecordCounter % 2 == 1)
			                        bgcolor = "alternateRow";
               				    out.print(bgcolor);  %> >	
					<td class="boldText">
						<a href="/<msp:webapp/>displaySecurityInquiriesUserProfileDetails.do?logonId=<bean:write name="userProfileIterator" property="logonId"/>" ><bean:write name="userProfileIterator" property="userLastName"/>, <bean:write name="userProfileIterator" property="userFirstName"/> <bean:write name="userProfileIterator" property="userMiddleName"/></a> 
					</td>
                    <td>
                  	   <bean:write name="userProfileIterator" property="logonId"/>
                  	   <%-- <bean:write name="userProfileIterator" property="jims2LogonId" /> 79250--%>
					</td>
                    <td>
                       	<bean:write name="userProfileIterator" property="agencyId"/> 
					</td>
                    <td>
                       	<bean:write name="userProfileIterator" property="departmentId"/> 
					</td>
				</tr>
				</pg:item>
		 		</logic:iterate>	
			</table>
			</td>
			</tr>
		</table>
		</td>
	</tr>
<!-- BEGIN PAGINATION NAVIGATOIN ROW -->
	<tr>
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
	</tr>	
<!-- END PAGINATION NAVIGATOIN ROW -->	
</table>
<!-- END DETAILS TABLE -->
<br>
<!-- BEGIN BUTTON TABLE -->
<table align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<input type="button" value="Back" onclick="history.go(-1)">		
		</td>
	</tr>
</table>
<!-- END BUTTON TABLE -->
<br>
<!-- BEGIN PAGINATION CLOSING TAG -->
</pg:pager>
<!-- END PAGINATION CLOSING TAG -->
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>