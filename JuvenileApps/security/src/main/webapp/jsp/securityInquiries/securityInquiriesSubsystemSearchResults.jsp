<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/02/2006	 C Shimek   - Create JSP -->
<!-- 02/06/2009  C Shimek   - #56860 add Back to Top  -->

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
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - securityInquiriesSubsystemSearchResults.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>

<body>
<html:form action="/displaySecurityInquiriesSubsystemDetails" target="content">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|130">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header"><bean:message key="title.inquiries"/> <bean:message key="prompt.subsystem"/> <bean:message key="title.searchResults"/></td>
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
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center">
			<bean:write name="securityInquiriesForm" property="searchResultsCount"/>
			<bean:message key="prompt.searchResultsFoundFor"/> <bean:message key="prompt.subsystem"/> 
		</td>
	</tr>
	<tr>
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">
				<tr class="detailHead">
					<td><bean:message key="prompt.subsystem"/>
					    <jims:sortResults beanName="securityInquiriesForm" results="featuresList" 
                                 primaryPropSort="featureCategoryName" primarySortType="STRING" defaultSort="true" 
                                 defaultSortOrder="ASC" sortId="1" />
                    </td>
					<td><bean:message key="prompt.features" />
					    <jims:sortResults beanName="securityInquiriesForm" results="featuresList" 
                                 primaryPropSort="featureName" primarySortType="STRING" defaultSort="false" 
                                 defaultSortOrder="ASC" sortId="2" />
                    </td>					
				</tr>
				<% int RecordCounter = 0; %>	
				<logic:iterate id="featureIterator" name="securityInquiriesForm" property="featuresList" indexId="index">	
				<% RecordCounter++; %>
				<pg:item>								
					<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">	
						<td>
							<bean:define id="displayCtr" type="java.lang.String"><%=RecordCounter%></bean:define>
							<logic:equal name="displayCtr" value="1">
								<bean:write name="securityInquiriesForm" property="featureCategoryName" />
							</logic:equal>
						</td>
						<td>
							<a href="/<msp:webapp/>displaySecurityInquiriesSubsystemDetails.do?featureId=<bean:write name="featureIterator" property="featureId"/>" ><bean:write name="featureIterator" property="featureName"/></a> 
						</td>
					</tr>
				</pg:item>											
				</logic:iterate>	
			</table>
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
<!-- BEGIN BUTTON TABLE -->
<table width="100%">
	<tr>
		<td align="center">
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