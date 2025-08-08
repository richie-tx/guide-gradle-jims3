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
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/juvProfileSearch.js"></script>
<html:base />

<title><bean:message key="title.heading" /> - juvenileProfileSearchResults.jsp</title>



</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" >
<html:form action="/displayJuvenileMasterInformation" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|163">


<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" ><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - Search Results</td>
  </tr>
</table>
<%-- END HEADING TABLE --%>


<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>


<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0" align="center">
  <tr>
    <td>
      <ul>
        <li>Click on hyperlinked Juvenile Number to select Juvenile Profile Details.</li>
        <li>Select Cancel to return to the search page.</li>		
      </ul>
    </td>
  </tr>
  <tr>
    <td align="center"><bean:write name="juvenileProfileSearchForm" property="searchResultSize"/> juvenile profiles found in search results</td>
  </tr>
</table> 
<%-- END INSTRUCTION TABLE --%>


<%-- BEGIN DETAIL TABLE --%>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" class='borderTableBlue'>
  <tr>
	 <td>
		<table width='100%' cellpadding="2" cellspacing="1">
			<tr class="detailHead">
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
					<bean:message key="prompt.master" /> <bean:message key="prompt.status" />   
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="masterStatusId" primarySortType="STRING" sortId="10" />
				</td>
				<td>   
					<bean:message key="prompt.casefile" /> <bean:message key="prompt.status" /><br/>   
					<jims:sortResults beanName="juvenileProfileSearchForm" results="juvenileProfiles" primaryPropSort="status" primarySortType="STRING" sortId="11" />
				</td>
			</tr>

		<logic:iterate id="juvenileProfiles" name="juvenileProfileSearchForm" property="juvenileProfiles" indexId="indexer"> 
			  <%-- Begin Pagination item wrap --%>
		  <pg:item>
			<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
				<td>
					<logic:empty name="juvenileProfiles" property="casefiles">
						&nbsp;&nbsp;&nbsp;
					</logic:empty>
					<logic:notEmpty name="juvenileProfiles" property="casefiles">
						<a href="javascript:showHideMulti('img<bean:write name="juvenileProfiles" property="juvenileNum"/>', '<bean:write name="juvenileProfiles" property="juvenileNum"/>', 1, '/<msp:webapp/>');" border=0><img border="0" src="/<msp:webapp/>images/expand.gif" name="img<bean:write name="juvenileProfiles" property="juvenileNum"/>"></a>
					</logic:notEmpty>
					<a onclick="spinner()" class="juvNumId" href="/<msp:webapp/>displayJuvenileBriefingDetails.do?submitAction=Link&fromProfile=profileBriefingDetails&juvenileNum=<bean:write name='juvenileProfiles' property='juvenileNum'/>" data-href="<bean:write name='juvenileProfiles' property='restrictedAccess'/>"><bean:write name="juvenileProfiles" property="juvenileNum"/></a>
				</td>    			  
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
				<!-- US 104418-->
				<logic:equal name="juvenileProfiles" property="recType" value="I.JUVENILE">
					&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Purged'>P</span></b></font>
				</logic:equal>
				<logic:equal name="juvenileProfiles" property="recType" value="S.JUVENILE">
					&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Sealed'>S</span></b></font>
				</logic:equal>
				</td>
				<td><bean:write name="juvenileProfiles" property="race"/></td>
				<td><bean:write name="juvenileProfiles" property="sex"/></td>
				<td><bean:write name="juvenileProfiles" property="dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
				<td><bean:write name="juvenileProfiles" property="formattedSSN"/></td>
				<td><span title="<bean:write name="juvenileProfiles" property="masterStatus"/>"><bean:write name="juvenileProfiles" property="masterStatusId"/></span></td>
				<td><span title="<bean:write name="juvenileProfiles" property="status"/>"><bean:write name="juvenileProfiles" property="statusId"/></span></td>
			</tr>
			<logic:notEmpty name="juvenileProfiles" property="casefiles">
				<tr id="<bean:write name="juvenileProfiles" property="juvenileNum"/>0" class="hidden">
					<td></td>
					<td valign="top" align="center" colspan="5">
						<table cellpadding="4" cellspacing="1" width="100%" class='borderTableGrey'>
							<tr>
								<td class="formDeLabel">Casefile #</td>
								<td class="formDeLabel">Supervision Type</td>
								<td class="formDeLabel">Case Status</td>
								<td class="formDeLabel"> JPO of Record</td>
							</tr>
							
								<logic:iterate indexId="index" id="casefile" name="juvenileProfiles" property="casefiles">
									<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
										<td><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileCasefileDetails.do?supervisionNum=<bean:write name='casefile' property='supervisionNum'/>"><bean:write name="casefile" property="supervisionNum"/></a></td>
										<td><bean:write name="casefile" property="supervisionType"/></td>
										<td><bean:write name="casefile" property="caseStatus"/></td>
										<td><bean:write name="casefile" property="probationOfficerLastName"/>, <bean:write name="casefile" property="probationOfficerFirstName"/></td>
									</tr>	
								</logic:iterate>
						</table>
					</td>
				</tr>
			</logic:notEmpty>
			
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

<%-- BEGIN BUTTON TABLE --%>
<table border="0" width="100%">
  <tr>
    <td align="center">
      <html:form action="/displayJuvenileProfileSearch.do">
    		 <html:submit><bean:message key="button.cancel"></bean:message></html:submit>
      </html:form>
    </td>
  </tr>
</table>
<%-- END BUTTON TABLE --%>

</body>

<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</html:html>
