<!DOCTYPE HTML>

<%-- Used for displaying results of juvenile profile search in MJCW --%>
<%-- MODIFICATIONS --%>
<%-- DWilliamson  06/06/2005	Create JSP --%>
<%-- CShimek	  07/13/2011	ER 70827 - add Name Suffix field to juvenile name display --%>

<%-- TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<%-- TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<%-- LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET --%>

<%-- BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1"/>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

<script type="text/javascript">
$(function(){
	
	$(".juvNumId").on("click", function(){	
		if($(this).data("href") == 'Y')
		{
			alert("Juvenile's records are no longer accessible.");
			return false;
		}
		else
			changeFormActionURL($(this).attr("href") , true);
		
	});
});
</script>

<html:base />
<title><bean:message key="title.heading" /> - juvenileFacilityProfileSearchResults.jsp</title>
</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" >
<html:form action="/displayJuvenileMasterInformation" target="content">

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>

<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" class='borderTableBlue'>
  <tr>
	 <td>
		<table width='100%' cellpadding="2" cellspacing="1">
			<tr class="facDetailHead">
				<td>   
					<bean:message key="prompt.juvenileNumber" />
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="juvenileNum" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="ASC" sortId="1" />				
				</td>
				<logic:equal name="juvenileProfileSearchForm" property="searchType" value="JNAM">
				<td> 
					<bean:message key="prompt.alias" />&nbsp;  
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="juvenileType" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" />
				</td>
				</logic:equal>
				<td>   
					<bean:message key="prompt.juvenileName" />
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="lastName" primarySortType="STRING" secondPropSort="firstName" secondarySortType="STRING" sortId="3" />
				</td>
				<td>   
					<bean:message key="prompt.race" />
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="race" primarySortType="STRING" sortId="4" />				
				</td>
				<td>   
					<bean:message key="prompt.sex" />
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="sex" primarySortType="STRING" sortId="5" />
				</td>
				<td>   
					<bean:message key="prompt.dob" />
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="dateOfBirth" primarySortType="DATE" sortId="6" />
				</td>
				<td>   
					<bean:message key="prompt.ssn" />
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="SSN" primarySortType="STRING" sortId="7" />
				</td>
				<td>   
					<bean:message key="prompt.facilityLocation" />
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="detentionFacilityId" primarySortType="STRING" sortId="8" />
				</td>
				<td>   
					<bean:message key="prompt.facilityStatus" />
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="detentionStatusId" primarySortType="STRING" sortId="9" />
				</td>
				<td>   
					<bean:message key="prompt.master" /> <bean:message key="prompt.status" />
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="masterStatusId" primarySortType="STRING" sortId="10" />
				</td>
				<td width="12%">   
					<bean:message key="prompt.casefile" /> <bean:message key="prompt.status" /><br/>   
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="status" primarySortType="STRING" sortId="11" />
				</td>
			</tr>

		<logic:iterate id="juvenileProfiles" name="juvenileProfileSearchForm" property="juvenileProfiles" indexId="indexer"> 
			  <%-- Begin Pagination item wrap --%>
		  <pg:item>
			<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" heignt="100%">		 	
				<td><a onclick="spinner()" class="juvNumId" href="/<msp:webapp/>displayJuvenileBriefingDetails.do?submitAction=facilityLink&fromProfile=profileBriefingDetails&juvenileNum=<bean:write name='juvenileProfiles' property='juvenileNum'/>" data-href="<bean:write name="juvenileProfiles" property="restrictedAccess"/>"><bean:write name="juvenileProfiles" property="juvenileNum"/></a></td>
				 <logic:equal name="juvenileProfileSearchForm" property="searchType" value="JNAM"> 
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
				<td><bean:write name="juvenileProfiles" property="lastName"/>,&nbsp;<bean:write name="juvenileProfiles" property="firstName"/>&nbsp;<bean:write name="juvenileProfiles" property="middleName"/>&nbsp;<bean:write name="juvenileProfiles" property="nameSuffix"/>
				<logic:equal name="juvenileProfiles" property="recType" value="I.JUVENILE">
					&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Purged'>P</span></b></font>
				</logic:equal>
				<logic:equal name="juvenileProfiles" property="recType" value="S.JUVENILE">
					&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Sealed'>S</span></b></font>
				</logic:equal></td>
				<td><bean:write name="juvenileProfiles" property="race"/></td>
				<td><bean:write name="juvenileProfiles" property="sex"/></td>
				<td><bean:write name="juvenileProfiles" property="dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
				<td><bean:write name="juvenileProfiles" property="formattedSSN"/></td>
				<td><span title='<bean:write name="juvenileProfiles" property="detentionFacility"/>'><bean:write name="juvenileProfiles" property="detentionFacilityId"/></span></td>
				<td><span title='<bean:write name="juvenileProfiles" property="detentionStatus"/>'><bean:write name="juvenileProfiles" property="detentionStatusId"/></span></td>
				<td><span title="<bean:write name="juvenileProfiles" property="masterStatus"/>"><bean:write name="juvenileProfiles" property="masterStatusId"/></span></td>
				<td><span title="<bean:write name="juvenileProfiles" property="status"/>"><bean:write name="juvenileProfiles" property="statusId"/></span></td>
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
<%-- END DETAIL TABLE --%>

</pg:pager>
<%-- Pagination Closing Tag --%>
</html:form>
</body>
</html:html>
