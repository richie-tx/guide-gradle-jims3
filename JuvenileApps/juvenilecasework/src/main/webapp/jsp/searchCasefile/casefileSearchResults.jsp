<!DOCTYPE HTML>

<%-- Used to display search casefile results --%>
<%-- MODIFICATIONS --%>
<%-- 05/09/2005		LDeen	Create JSP --%>

<%-- TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%-- TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%-- msp:login / --%>

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- STYLE SHEET LINK --%>	
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>


<script type='text/javascript'>

$(function(){
	
	$(".supervisionNumId").on("click", function(){		
		if($(this).data("href") == 'Y')
		{
			alert("Juvenile's records are no longer accessible.");
			return false;
		}
		else
			changeFormActionURL($(this).attr("href") , true);
		
	});
	
	$("#searchResults").on("click", function(){
		changeFormActionURL("/JuvenileCasework/displayJuvenileCasefileDetails.do?action=print" , true);
	});
	
	$("#searchResultsAddress").on("click", function(){
		changeFormActionURL("/JuvenileCasework/displayJuvenileCasefileDetails.do?action=printYouthAddress" , true);
	});
	
});

</script>

</head>

<html:base />

<title><bean:message key="title.heading"/> - casefileSearchResults.jsp</title>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" >
<html:form action="/displayJuvenileCasefileDetails" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|44">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" >Juvenile Casework - Casefile Search Results</td>
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
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>Click on hyperlinked Supervision Number to view Casefile Details.</li>
        <li>Select cancel to return to search page.</li>
      </ul>
    </td>
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

<%-- BEGIN MESSAGE TABLE --%>
<table align='center'>
	<tr>
	 <td align="center"><bean:write name="casefileSearchForm" property="searchResultSize"/> casefiles found in search results</td>
  	</tr>
</table>  
<%-- END MESSAGE TABLE --%>

<%-- BEGIN DETAIL TABLE --%>
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue">          
 	<tr>
		<td>
	  		<table width='100%' cellpadding="2" cellspacing="1">
 				<tr class="detailHead">   
    				<td>   
						<bean:message key="prompt.supervision" />&nbsp;#
						<jims:sortResults beanName="casefileSearchForm" results="casefileSearchResults" primaryPropSort="supervisionNum" primarySortType="INTEGER" defaultSortOrder="ASC" sortId="11"/>				
          			</td>
          			<td>   
						<bean:message key="prompt.sequenceNo" />&nbsp;#
						<jims:sortResults beanName="casefileSearchForm" results="casefileSearchResults" primaryPropSort="sequenceNum" primarySortType="INTEGER" defaultSort="true" defaultSortOrder="DESC" sortId="1" />				
          			</td>
          			<td>   
  						<bean:message key="prompt.juvenileNumber" />
  						<jims:sortResults beanName="casefileSearchForm" results="casefileSearchResults" primaryPropSort="juvenileNum" primarySortType="INTEGER" defaultSortOrder="ASC" sortId="2" />				
          			</td>
          			<td> 
						<bean:message key="prompt.disposition" />&nbsp;<bean:message key="prompt.date" />  
            			<jims:sortResults beanName="casefileSearchForm" results="casefileSearchResults" primaryPropSort="dispositionDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="3" />
          			</td>
          			<td> 
						<bean:message key="prompt.expected" />&nbsp;<bean:message key="prompt.endDate" />  
            			<jims:sortResults beanName="casefileSearchForm" results="casefileSearchResults" primaryPropSort="supervisionEndDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="4" />
          			</td>
          			<logic:equal name="casefileSearchForm" property="searchTypeId" value="JNAM">
					  	<td>
  							<bean:message key="prompt.alias" />&nbsp;  
         					<jims:sortResults beanName="casefileSearchForm" results="casefileSearchResults" primaryPropSort="juvenileNameType" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />
	          			</td>
				  	</logic:equal>          
					<td> 
						<bean:message key="prompt.juvenileName" />  
            			<jims:sortResults beanName="casefileSearchForm" results="casefileSearchResults" primaryPropSort="juvenileFullName" primarySortType="STRING" defaultSortOrder="ASC" sortId="6" />
          			</td>
          			<td>   
  						<bean:message key="prompt.probationOfficer" />
  						<jims:sortResults beanName="casefileSearchForm" results="casefileSearchResults" primaryPropSort="probationOfficerFullName" primarySortType="STRING" defaultSortOrder="ASC" sortId="7" />				
          			</td>
          			<td>   
  						<bean:message key="prompt.supervisionType" />
            			<jims:sortResults beanName="casefileSearchForm" results="casefileSearchResults" primaryPropSort="supervisionType" primarySortType="STRING" defaultSortOrder="ASC" sortId="8" />
          			</td>
          			<logic:equal name="casefileSearchForm" property="searchTypeId" value="CSST">
          			<td>   
  						<bean:message key="prompt.zipCode" />
            			<jims:sortResults beanName="casefileSearchForm" results="casefileSearchResults" primaryPropSort="zipCode" primarySortType="STRING" defaultSortOrder="ASC" sortId="9" />
          			</td>
          			</logic:equal>
          			<td>   
            			<bean:message key="prompt.status" />&nbsp;
            			<jims:sortResults beanName="casefileSearchForm" results="casefileSearchResults" primaryPropSort="caseStatus" primarySortType="STRING" defaultSortOrder="ASC" sortId="10" />
          			</td>
				</tr>

  				<logic:iterate id="casefiles" name="casefileSearchForm" property="casefileSearchResults" indexId='indexer'> 
          <%-- Begin Pagination item wrap --%>
          			<pg:item>
	    				<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
	    					<td><a onclick="spinner()" class="supervisionNumId" href="/<msp:webapp/>displayJuvenileBriefingDetails.do?submitAction=Link&juvenileNum=<bean:write name='casefiles' property='juvenileNum'/>&supervisionNum=<bean:write name='casefiles' property='supervisionNum'/>" data-href="<bean:write name='casefiles' property='restrictedAccess'/>"><bean:write name='casefiles' property='supervisionNum'/></a></td>
	    					<td><bean:write name="casefiles" property="sequenceNum"/></td>
							<td><bean:write name="casefiles" property="juvenileNum"/></td>
							<td><bean:write name="casefiles" property="dispositionDate" formatKey="date.format.mmddyyyy"/></td>
	    					<td><bean:write name="casefiles" property="supervisionEndDate" formatKey="date.format.mmddyyyy"/></td>
							<logic:equal name="casefileSearchForm" property="searchTypeId" value="JNAM">
								<td>
								<logic:equal name="casefiles" property="juvenileNameType" value="P">
					   				<span title='<bean:write name="casefiles" property="preferredFirstName"/>'><bean:write name="casefiles" property="juvenileNameType"/></span>
								 </logic:equal><logic:equal name="casefiles" property="juvenileNameType" value="A">
					   				<bean:write name="casefiles" property="juvenileNameType"/>
								 </logic:equal></td> </td> 
	    					</logic:equal>
	    					<td onclick="javascript:goNav('/<msp:webapp/>displayJuvenileCasefileDetails.do?supervisionNum=<bean:write name='casefiles' property='supervisionNum'/>');"> <bean:write name="casefiles" property="juvenileFullName"/>
	    					 <logic:equal name="casefiles" property="juvRectype" value="I.JUVENILE">
							&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Purged'>P</span></b></font>
							</logic:equal>
							<logic:equal name="casefiles" property="juvRectype" value="S.JUVENILE">
							&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Sealed'>S</span></b></font>
							</logic:equal></td>
	    					<td><bean:write name="casefiles" property="probationOfficerFullName"/></td>
	    					<td><bean:write name="casefiles" property="supervisionType"/></td>
	    					<logic:equal name="casefileSearchForm" property="searchTypeId" value="CSST">
	    					<td><bean:write name="casefiles" property="zipCode"/></td>
	    					</logic:equal>
	    					<td><bean:write name="casefiles" property="caseStatus"/></td>
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
<br>
	<table align="center">
  			<tr>
    			<td>
    				<html:submit property="submitAction" styleId="searchResults"><bean:message key="button.printCasefileSearchResults"/></html:submit>
    			</td>
    			<logic:notEmpty name="casefileSearchForm" property="officerLastName">
    				<td>
    					<html:submit property="submitAction" styleId="searchResultsAddress"><bean:message key="button.printYouthAddresses"/></html:submit>
    				</td>
    			</logic:notEmpty>
    		</tr>
    	</table>

<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<%-- End Pagination Closing Tag --%>
<br>
<%-- BEGIN DETAIL TABLE --%>
<logic:equal name="casefileSearchForm" property="searchTypeId" value="CSST">
<table width="99%" border="0" cellpadding="0" cellspacing="0" align="center">
	  <tr>
			<td valign="top" colspan="4">
			  <tiles:insert page="caseloadSummaryResultsTile.jsp" flush="false">
	        	 <tiles:put name="casefileSearchForm" beanName="casefileSearchForm" />	
	        </tiles:insert>
           
          </td>
       </tr> 
</table>
</logic:equal>
</html:form>
<%-- BEGIN BUTTON TABLE --%>
<table width="100%" border="0">
	<tr>
		<td align="center">
	      	<html:form action="/displayJuvenileCasefileSearch.do"> 
		       <input id='back' type='button' value="Back" onclick="history.go(-1)"/>
	  	       <html:submit><bean:message key="button.cancel"></bean:message></html:submit>
	       </html:form> 
    	</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>