<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionMessages" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>



<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<title><bean:message key="title.heading" />/courtNameSearchResults.jsp</title>

</head>
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
	<html:form action="/displayJuvenileDistrictCourtNameSearchResults" target="content" styleId="courtNameSearchForm">
		
	<!-- HELP FILE -->
		<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">
		<!-- BEGIN Error Message Table -->
		 <logic:messagesPresent message="true"> 
			<table width="100%">
				<tr>		  
					<td align="center" class="messageAlert"><html:messages id="message" message="true"><bean:write name="message"/></html:messages></td>		  
				</tr>   	  
			</table>
		</logic:messagesPresent> 
		<!-- BEGIN ERROR TABLE -->
		<table width="100%">
			<tr>
				<td align="center" class="errorAlert"><html:errors></html:errors></td>
			</tr>
		</table>
		<!-- END ERROR TABLE -->
	
		<%-- Begin Pagination Header Tag--%>
			<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
			<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
			    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
			<input type="hidden" name="pager.offset" value="<%= offset %>">
		<%-- End Pagination header stuff --%>

		<%-- BEGIN INSTRUCTION TABLE --%>
		<table width="98%" border="0" align="center">
		  <tr>
		    <td align="center"><bean:write name="courtHearingForm" property="searchResultSize"/> juvenile profiles found in search results</td>
		  </tr>
		</table> 
		<%-- END INSTRUCTION TABLE --%>
		<%-- BEGIN DETAIL TABLE --%>
		<div id="searchResults">
		<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" class='borderTableBlue'>
		  <tr>
			 <td>
				<table width='100%' cellpadding="2" cellspacing="1">
					<tr class="crtDetailHead">
						<td width='10%'>   
							<bean:message key="prompt.juvenileNumber" />
							<jims:sortResults beanName="courtHearingForm" results="juvenileProfiles" primaryPropSort="juvenileNum" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="ASC" sortId="1" />				
						</td>
						<logic:equal name="courtHearingForm" property="searchType" value="JNAM">
						<td> 
							<bean:message key="prompt.alias" />&nbsp;  
							<jims:sortResults beanName="courtHearingForm" results="juvenileProfiles" primaryPropSort="juvenileType" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" />
						</td>
						</logic:equal>
						<td>   
							<bean:message key="prompt.juvenileName" />
							<jims:sortResults beanName="courtHearingForm" results="juvenileProfiles" primaryPropSort="lastName" primarySortType="STRING" secondPropSort="firstName" secondarySortType="STRING" sortId="3" />
						</td>
						<td>   
							<bean:message key="prompt.race" />
							<jims:sortResults beanName="courtHearingForm" results="juvenileProfiles" primaryPropSort="race" primarySortType="STRING" sortId="4" />				
						</td>
						<td>   
							<bean:message key="prompt.sex" />
							<jims:sortResults beanName="courtHearingForm" results="juvenileProfiles" primaryPropSort="sex" primarySortType="STRING" sortId="5" />
						</td>
						<td>   
							<bean:message key="prompt.dob" />
							<jims:sortResults beanName="courtHearingForm" results="juvenileProfiles" primaryPropSort="dateOfBirth" primarySortType="DATE" sortId="6" />
						</td>
						<td>   
							<bean:message key="prompt.master" /> <bean:message key="prompt.status" />
							<jims:sortResults beanName="courtHearingForm" results="juvenileProfiles" primaryPropSort="status" primarySortType="STRING" sortId="7" />
						</td>
					</tr>
		
				<logic:iterate id="juvenileProfiles" name="courtHearingForm" property="juvenileProfiles" indexId="indexer"> 
				  <%-- Begin Pagination item wrap --%>
				  <pg:item>
					<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" heignt="100%">		 	
						<td><a onclick="spinner()" class="juvNumId" href='/<msp:webapp/>displayCourtBriefingDetails.do?submitAction=Link&juvenileNum=<bean:write name='juvenileProfiles' property='juvenileNum'/>'><bean:write name="juvenileProfiles" property="juvenileNum"/></a></td>
						 <logic:equal name="courtHearingForm" property="searchType" value="JNAM">
							<td>
							<logic:equal name="juvenileProfiles" property="juvenileType" value="P">
		   						<span title='<bean:write name="juvenileProfiles" property="preferredFirstName"/>'><bean:write name="juvenileProfiles" property="juvenileType"/></span>
					 		</logic:equal>
					 		<logic:equal name="juvenileProfiles" property="juvenileType" value="A">
					   			<span title='ALIAS'><bean:write name="juvenileProfiles" property="juvenileType"/></span>
					 		</logic:equal>
					 		<logic:equal name="juvenileProfiles" property="juvenileType" value="M">
					  			<span title='SOCIAL MEDIA IDENTIFIER'><bean:write name="juvenileProfiles" property="juvenileType"/></span>
					 		</logic:equal>
							<logic:equal name="juvenileProfiles" property="juvenileType" value="N">
					 			<span title='NICKNAME'><bean:write name="juvenileProfiles" property="juvenileType"/></span>
							 </logic:equal>
							 <logic:equal name="juvenileProfiles" property="juvenileType" value="S">
					   			<span title='STREET NAME'><bean:write name="juvenileProfiles" property="juvenileType"/></span>
							 </logic:equal>
							 <logic:equal name="juvenileProfiles" property="juvenileType" value="G">
							   <span title='GANG NAME'><bean:write name="juvenileProfiles" property="juvenileType"/></span>
							 </logic:equal>
							 <logic:equal name="juvenileProfiles" property="juvenileType" value="F">
							   <span title='FALSE IDENTIFICATION'><bean:write name="juvenileProfiles" property="juvenileType"/></span>
							 </logic:equal>
					 		</td>    		
					  	</logic:equal>
						<td><bean:write name="juvenileProfiles" property="lastName"/>,&nbsp;<bean:write name="juvenileProfiles" property="firstName"/>&nbsp;<bean:write name="juvenileProfiles" property="middleName"/>&nbsp;<bean:write name="juvenileProfiles" property="nameSuffix"/></td>
						<td><bean:write name="juvenileProfiles" property="race"/></td>
						<td><bean:write name="juvenileProfiles" property="sex"/></td>
						<td><bean:write name="juvenileProfiles" property="dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
						<td><bean:write name="juvenileProfiles" property="status"/></td>
					</tr>
				  </pg:item>
				  <%-- End Pagination item wrap --%>
				</logic:iterate>
			</table>
		  		<%-- Begin Pagination navigation Row--%>
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
			  <%-- End Pagination navigation Row--%>
			  </td>
		  </tr>	
		</table>
		</div>
		<%-- END DETAIL TABLE --%>
		</pg:pager>
		<%-- Pagination Closing Tag --%>
	</html:form>
	<div align='center'>
		<script type="text/javascript">renderBackToTop()</script>
	</div>
</body>
</html:html>