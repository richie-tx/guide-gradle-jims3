<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>





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

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/facility.js"></script>

<title><bean:message key="title.heading"/>/facilitySplAttentionReasonPopUp.jsp</title>

</head> 
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
<html:form action="/displayJuvenileFacilityModifyAdmit" target="content">

	<!-- HELP FILE -->
	<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

	<%-- BEGIN HEADING TABLE --%>
	<table width='100%'>
		<tr>
			<td align="center" class="header">Process Juvenile Facility Admission - Special Attention Reason Comments</td> 
		</tr>
	</table>
	<%-- END HEADING TABLE --%>
	<%-- BEGIN INSTRUCTION TABLE --%>
	<br/>
	<table width="98%" border="0">
		<tr>
			<td>
				<ul>
					<li>Lists history of special attention Reason - other comments.</li>
				</ul>
			</td>
		</tr>
	</table>
	<%-- END INSTRUCTION TABLE --%>
	<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>
	<table width="98%" cellpadding="0" cellspacing="1" class='borderTableBlue' align="center">
		<tr class='facDetailHead'>
			<td colspan='4' align='left' class='paddedFourPix'>Special Attention Reason Comments
				<logic:empty name="admitReleaseForm" property="splAttnReasonComments">
			 		(No Comments)
				</logic:empty>
			</td>
		</tr>
		<logic:notEmpty name="admitReleaseForm" property="splAttnReasonComments">
			<tr>
				<td>
					<table width="100%" cellpadding="2" cellspacing="1" class='borderTableBlue'>
						<tr class='formDeLabel'>
							<td>
								<bean:message key="prompt.entryDate"/>
								<jims:sortResults beanName="admitReleaseForm" results="splAttnReasonComments" primaryPropSort="entryDate" primarySortType="DATE" defaultSort="true" defaultSortOrder="DESC" sortId="1" />
							</td>
							<td>
								<bean:message key="prompt.entryTime"/>
								<jims:sortResults beanName="admitReleaseForm" results="splAttnReasonComments" primaryPropSort="entryTime" primarySortType="TIMESTAMP" defaultSort="true" defaultSortOrder="DESC" sortId="2" />
							</td>
							<td>
								<bean:message key="prompt.officer"/>
								<jims:sortResults beanName="admitReleaseForm" results="splAttnReasonComments" primaryPropSort="createUser" primarySortType="String" defaultSort="true" defaultSortOrder="DESC" sortId="3" />
							</td>
							<td nowrap="nowrap"><bean:message key="prompt.otherReasonComments"/></td>
						</tr>
									 
						<logic:iterate name="admitReleaseForm" property="splAttnReasonComments" id="splAttnComment" indexId="indexer">    
							<pg:item>  			
								<tr class="<% out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
								   <td>
										<bean:write name="splAttnComment" property="entryDate"/>
									</td>
									<td>
										<bean:write name="splAttnComment" property="entryTime"/>
									</td>
									<td>
										<bean:write name="splAttnComment" property="createUser"/>
									</td>
									<td>
										<bean:write name="splAttnComment" property="comments"/>
									</td>
								</tr>
							</pg:item>
						</logic:iterate>
					</table>
				</td>
			</tr>
		</logic:notEmpty>
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
</pg:pager>
<br/>
	<table align="center" width="99%">
		<tr>
			<td align="center" colspan='8'>
			   <input type="button" value="Close Window" onclick="window.close()">
			</td>
		</tr>
	</table> 
</html:form>
</body>
</html:html>