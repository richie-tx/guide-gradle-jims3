<!DOCTYPE HTML>

<%-- Used for displaying search results from Family Member Search form Navigation Tree in MJCW --%>
<%--MODIFICATIONS --%>
<%-- 07/26/2012		CShimek			Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@page import="naming.UIConstants"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - familyMemberLeftNavSearchResults.jsp</title>

<%-- JAVASCRIPT FILES FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/handleFamilyMemberLeftNavSelect" target="content">
<input type="hidden" name="helpFile" value="">
<!-- Begin Pagination Header Tag -->
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
	<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    	maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
  	<input type="hidden" name="pager.offset" value="<%= offset %>">
<!-- End Pagination header  -->
<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - Family <bean:message key="prompt.member"/> - <bean:message key="prompt.search"/> Results</td>
		</tr>
		<tr>		  
			<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
		</tr>   	  
	</table>
<%-- END HEADING TABLE --%>
<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="100%" border="0" align="center">
		<tr>
			<td>
				<ul>
					<li>Click on hyperlinked Juvenile Number to view Juvenile Briefing Details.</li>
					<li>Select cancel to return to the search page.</li>
				</ul>
			</td>
		</tr>
	</table>
<%-- END INSTRUCTION TABLE --%>
	<div class="spacer" ></div>
	<div>
		<bean:size id="searchResultsSize" name="juvenileMemberSearchForm" property="memberSearchResults"/> 
		<STRONG><bean:write name="searchResultsSize"/> &nbsp;Results for Last Name: <bean:write name="juvenileMemberSearchForm" property="name.lastName"/></STRONG>
	</div>

<%-- BEGIN LIST  TABLE --%>
<table width="98%" border="0" cellpadding="0" cellspacing="1" class="borderTableBlue" align="center">
	<tr>
		<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="1">	
				<tr class="detailHead">
					<td width="1%" nowrap="nowrap" ><bean:message key="prompt.member#" />
						<jims2:sortResults beanName="juvenileMemberSearchForm" results="memberSearchResults" primaryPropSort="memberNum" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="ASC" sortId="1" levelDeep="2"/>
					</td>
					<td><bean:message key="prompt.name" />
				 		<jims2:sortResults beanName="juvenileMemberSearchForm" results="memberSearchResults" primaryPropSort="lastName" primarySortType="STRING" secondPropSort="firstName" secondarySortType="STRING" defaultSort="false" sortId="2" levelDeep="2"/>
					</td>
					<td><bean:message key="prompt.ethnicity" />
					 	<jims2:sortResults beanName="juvenileMemberSearchForm" results="memberSearchResults" primaryPropSort="ethnicityDesc" primarySortType="STRING" defaultSort="false"  sortId="3" levelDeep="2"/> 
					</td>
					<td width="1%" nowrap="nowrap"><bean:message key="prompt.sex" />
					 	<jims2:sortResults beanName="juvenileMemberSearchForm" results="memberSearchResults" primaryPropSort="sex" primarySortType="STRING" defaultSort="false" sortId="4" levelDeep="2"/>
					</td>
					<td width="1%" nowrap="nowrap"><bean:message key="prompt.dob" />
						<jims2:sortResults beanName="juvenileMemberSearchForm" results="memberSearchResults" primaryPropSort="dateOfBirth" primarySortType="DATE" defaultSort="false" sortId="5" levelDeep="2"/>  
					</td>
					<td width="40%">	
				 		<table width='100%' border="0" cellpadding="0" cellspacing="1">	
							<tr class="detailHead">
								<td width="40%"><bean:message key="prompt.relationship" /></td>
								<td width="40%"><bean:message key="prompt.juvenileName" /></td>
								<td width="20%"><bean:message key="prompt.juvenile#" /> </td>
							</tr>
						</table>  
					</td>
				</tr>
		 		<logic:iterate id="memIndex" name="juvenileMemberSearchForm" property="memberSearchResults" indexId="index">
					<pg:item>
						<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
							<td valign="top">
							 	<a href="javascript:openWindow('/<msp:webapp/>handleFamilyMemberLeftNavSelect.do?submitAction=Details&selectedValue=<bean:write name="memIndex" property="memberNum" />')"><bean:write name="memIndex" property="memberNum" /></a>
							</td>
							<td valign="top"><bean:write name="memIndex" property="lastName" />, <bean:write name="memIndex" property="firstName" /> <bean:write name="memIndex" property="middleName" /></td>
							<td valign="top"><bean:write name="memIndex" property="ethnicityDesc" /></td>
							<td valign="top"><bean:write name="memIndex" property="sex" />&nbsp;&nbsp;</td>													
							<td valign="top"><bean:write name="memIndex" property="dateOfBirth"  formatKey="date.format.mmddyyyy"/>&nbsp;&nbsp;</td>
							<td valign="top">
								<table width="100%" border="0">
									<logic:iterate id="juvIndex" name="memIndex" property="associatedJuveniles">
						 				<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
											<td width="40%" valign="top"><bean:write name="juvIndex" property="relationType" /></td>
											<td width="40%" valign="top"><bean:write name="juvIndex" property="juvFullName" /></td>
											<td width="20%" valign="top">
												<a href="/<msp:webapp/>displayFamilyConstellationList.do?juvnum=<bean:write name="juvIndex" property="juvId"/>"><bean:write name="juvIndex" property="juvId"/></a>  
							 				</td>  
										</tr>  
									</logic:iterate>	
								</table> 
							</td>
						</tr>			
					</pg:item>      
				</logic:iterate>  
			</table>
		</td>
	</tr>
</table>  
<%-- END LIST TABLE --%>
<%-- BEGIN PAGINATION NAVIGATOIN ROW --%>
<table align=center>
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
<%-- END PAGINATION NAVIGATOIN ROW --%>

<%-- BEGIN BUTTON TABLE --%>		
	<table align="center" border="0" width="100%">
		<tr>
			<td align="center">
				<html:submit property="submitAction" styleId="submitButton"> 
					<bean:message key="button.cancel" />
				</html:submit>
			</td>
		</tr>
	</table>
<%-- END BUTTON TABLE --%> 
</pg:pager>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>