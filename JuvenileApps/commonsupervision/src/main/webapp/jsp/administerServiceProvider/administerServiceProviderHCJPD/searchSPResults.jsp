<!DOCTYPE HTML>
<!-- Used for searching of Service Providers -->
<!--MODIFICATIONS -->
<!-- UGopinath	09/14/2006	Create JSP -->
<!-- ddouglas	07/24/2007	ER#43757-ADD SORTING TAG -->
<!-- jjose	08/13/2007	ER#JIMS200044394-ADD Pagination -->
<!-- RCapestani 10/13/2015  Task #30717 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Service Provider-Juvenile link) -->
 
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>

<!--BEGIN HEADER TAG-->
<html:html>
<head>
<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/commonsupervision.css" />
<html:base />
<title><bean:message key="title.heading"/> - searchSPResults.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/administerServiceProviderHCJPD/searchSPResults.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

<script type="text/javascript">
var currentSelectedSP = 0 ;

function setSP(spNum)
{  
  currentSelectedSP = spNum ;
}

function loadViewSP(file)
{
  var myURL = file + "&selectedValue=" + currentSelectedSP;

  load( myURL, top.opener );
  window.close();
}

function load(file,target) 
{
	window.location.href = file;
}
</script>

</head>

<body topmargin='0' leftmargin="0">
<!--BEGIN FORM TAG-->
<html:form action="/handleJuvServiceProviderSearchResults" target="content">


<logic:equal name="serviceProviderForm" property="searchTypeId" value="PR">
 	<input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|361">
</logic:equal>

<logic:equal name="serviceProviderForm" property="searchTypeId" value="SP">
 	<input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|363">
</logic:equal>

<logic:equal name="serviceProviderForm" property="searchTypeId" value="SV">
 	<input type="hidden" name="helpFile" value="commonsupervision/asp_hcjpd/Administer_Service_Provider.htm#|362">
</logic:equal>

<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
  <input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>


<br>
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
  	<td valign='top'><img src="/<msp:webapp/>images/spacer.gif" height='5'></td>
  </tr>
  <tr>
    <td valign='top'>
    	<table width='100%' border="0" cellpadding="0" cellspacing="0" >
    		<tr>
    			<td valign='top'>
    			<!--tabs start-->
    				<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
    				<tiles:put name="tabid" value="suggestedOrderTab"/>
    				</tiles:insert>
    			<!--tabs end-->
    			</td>
    		</tr>
    	  <tr>
    		  <td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" height='5'></td>
    	  </tr>
    	</table>

    	<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
    		<tr>
    			<td><bean:message key="prompt.3.spacer"/></td>
    		</tr>
    		<tr>
    			<td valign='top' align='center'>
    			
      			<!-- BEGIN HEADING TABLE -->
      			<table align="center" >
      				<tr>
      					<td class="header" align="center"><bean:message key="title.serviceProvider"/> -  
        					<logic:equal name="serviceProviderForm" property="searchTypeId" value="PR">
        					 	<bean:message key="prompt.program"/>
        					</logic:equal>
        					<logic:equal name="serviceProviderForm" property="searchTypeId" value="PC">
        					 	<bean:message key="prompt.program"/> <bean:message key="prompt.code"/>
        					</logic:equal>
        					<logic:equal name="serviceProviderForm" property="searchTypeId" value="SP">
        					 	<bean:message key="title.serviceProvider"/>
        					</logic:equal>
        					<logic:equal name="serviceProviderForm" property="searchTypeId" value="SV">
        					 	<bean:message key="prompt.service"/>
        					</logic:equal>
        					<logic:equal name="serviceProviderForm" property="searchTypeId" value="FS">
        					 	Fund Source: <bean:write name="serviceProviderForm" property="fundSourceDescription" />
        					</logic:equal>
							&nbsp;
							<bean:message key="prompt.search"/>
        					<bean:message key="prompt.results"/>
						</td>
      				</tr>
      			</table>
      			<!-- END HEADING TABLE -->

      			<!-- BEGIN INSTRUCTION TABLE -->
      			<table width="98%" border="0">
      				<tr>
      					<td>
      						<ul>
      							<li>Select a Service Provider and click the Update button, or click the Service Provider Name to view details.</li>
      						</ul>
      					</td>
      				</tr>
      				<tr>
	      				<logic:notEqual name="serviceProviderForm" property="searchTypeId" value="FS">
	      					<td align='center'><bean:write name="serviceProviderForm" property="resultsSize"/>&nbsp;search results found.</td>
	      				</logic:notEqual>
	      				
      					<logic:equal name="serviceProviderForm" property="searchTypeId" value="FS">
      						<td align='center'>
      							<bean:write name="serviceProviderForm" property="resultsSize"/>&nbsp;search results found for  
      							<bean:write name="serviceProviderForm" property="fundSourceDescription" />
      						</td>
        					 	
        				</logic:equal>
      				</tr>
      			</table>
  			
      			<!-- BEGIN ERROR TABLE -->
      			<table width="98%" border="0" align="center">
      				<tr>
      					<td align="center" class="errorAlert"><html:errors></html:errors></td>
      				</tr>
      			</table>
  			
      			<!-- BEGIN  TABLE -->
      			<table width="98%" cellpadding="0" cellspacing="0" border='0'>
      				<tr height="100%">
      					<td width='100%' valign='top'>
        					<table width="100%" cellpadding="2" cellspacing="0" class='borderTableBlue'>
        						<tr class='detailHead'>
        						  <td class='detailHead' nowrap='nowrap' colspan='4'><bean:message key="prompt.serviceProviders" /></td>
        						</tr>
        						<tr>
        							<td>
      						
          							<logic:equal name="serviceProviderForm" property="searchTypeId" value="PR">
          								<table width="100%" cellpadding="2" cellspacing="1">
	      								    <tr bgcolor='#cccccc'>
	      								   		<td width="1%"></td>
	      								   		<td align="left" class="subhead" valign='top'>
	      								   		  <bean:message key="title.serviceProvider"/> <bean:message key="prompt.name"/>
 				                        <jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="serviceProviderName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" levelDeep="3"/>
			                        </td>
           										<td align="left" class="subhead" valign='top' nowrap='nowrap'><bean:message key="prompt.inHouse"/>
           										<jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="inHouse" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" levelDeep="3"/>
           										</td>
           										<td align="left" class="subhead" valign='top'><bean:message key="prompt.program"/> <bean:message key="prompt.name"/>
           										<jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="programName" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" levelDeep="3"/>
           										</td>
           										<td align="left" class="subhead" valign='top' width="3%" nowrap='nowrap'><bean:message key="prompt.program"/> <bean:message key="prompt.code"/>
           										<jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="programCodeId" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" levelDeep="3"/>
           										</td>
           										<td align="left" class="subhead" valign='top' width="3%" nowrap='nowrap'><bean:message key="prompt.targetIntervention"/>
           										<jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="targetInterventionId" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" levelDeep="3"/>
           										</td>
           									</tr>

         								   	<logic:iterate indexId="index" id="spIndex" name="serviceProviderForm" property="searchResults">
         										  <pg:item>
            										<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
            											<td align="left">
            												<bean:define id="sp" name="spIndex" property="serviceProviderId"/>
            												<logic:notEqual name="spIndex" property="serviceProviderStatusId" value="I">
            													<input type="radio" name="selectedValue" value="<%=sp%>"/>
            												</logic:notEqual>
            											</td>
            											<td align="left">
            											  <logic:equal name="spIndex" property="serviceProviderStatusId" value="P">
	            												<a href="javascript:loadViewSP('/<msp:webapp/>handleJuvServiceProviderSearchResults.do?submitAction=View')" style="font-style: Italic" onclick="setSP('<bean:write name="spIndex" property="serviceProviderId"/>')">
	            											</logic:equal>
	            											<logic:notEqual name="spIndex" property="serviceProviderStatusId" value="P">
	            												<a href="javascript:loadViewSP('/<msp:webapp/>handleJuvServiceProviderSearchResults.do?submitAction=View')" onclick="setSP('<bean:write name="spIndex" property="serviceProviderId"/>')">
	            											</logic:notEqual>
            												<bean:write name="spIndex" property="serviceProviderName"/></a>														
            											</td>
            											<td align="left"><jims2:displayBoolean name="spIndex" property="inHouse" trueValue="YES" falseValue="NO"/></td> 
            											<td align="left"><bean:write name="spIndex" property="programName"/></td>
            											<td nowrap='nowrap' align="left"><bean:write name="spIndex" property="programCodeId" /></td>
            											<td align="left"><bean:write name="spIndex" property="targetInterventionId"/></td>										
            										</tr>
         										  </pg:item>
            									</logic:iterate>
            								</table>
            							</logic:equal>
            							
            						<logic:equal name="serviceProviderForm" property="searchTypeId" value="FS">
          								<table width="100%" cellpadding="2" cellspacing="1">
	      								    <tr bgcolor='#cccccc'>
	      								   		<td width="1%"></td>
	      								   		<td align="left" class="subhead" valign='top'>
	      								   		  <bean:message key="title.serviceProvider"/> <bean:message key="prompt.name"/>
 				                        <jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="serviceProviderName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" levelDeep="3"/>
			                        </td>
           										<td align="left" class="subhead" valign='top' nowrap='nowrap'><bean:message key="prompt.inHouse"/>
           										<jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="inHouse" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" levelDeep="3"/>
           										</td>
           										<td align="left" class="subhead" valign='top'><bean:message key="prompt.program"/> <bean:message key="prompt.name"/>
           										<jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="programName" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" levelDeep="3"/>
           										</td>
           										<td align="left" class="subhead" valign='top' width="3%" nowrap='nowrap'><bean:message key="prompt.program"/> <bean:message key="prompt.code"/>
           										<jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="programCodeId" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" levelDeep="3"/>
           										</td>
           										<td align="left" class="subhead" valign='top' width="3%" nowrap='nowrap'><bean:message key="prompt.targetIntervention"/>
           										<jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="targetInterventionId" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" levelDeep="3"/>
           										</td>
           									</tr>

         								   	<logic:iterate indexId="index" id="spIndex" name="serviceProviderForm" property="searchResults">
         										  <pg:item>
            										<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
            											<td align="left">
            												<bean:define id="sp" name="spIndex" property="serviceProviderId"/>
            												<logic:notEqual name="spIndex" property="serviceProviderStatusId" value="I">
            													<input type="radio" name="selectedValue" value="<%=sp%>"/>
            												</logic:notEqual>
            											</td>
            											<td align="left">
            											  <logic:equal name="spIndex" property="serviceProviderStatusId" value="P">
	            												<a href="javascript:loadViewSP('/<msp:webapp/>handleJuvServiceProviderSearchResults.do?submitAction=View')" style="font-style: Italic" onclick="setSP('<bean:write name="spIndex" property="serviceProviderId"/>')">
	            											</logic:equal>
	            											<logic:notEqual name="spIndex" property="serviceProviderStatusId" value="P">
	            												<a href="javascript:loadViewSP('/<msp:webapp/>handleJuvServiceProviderSearchResults.do?submitAction=View')" onclick="setSP('<bean:write name="spIndex" property="serviceProviderId"/>')">
	            											</logic:notEqual>
            												<bean:write name="spIndex" property="serviceProviderName"/></a>														
            											</td>
            											<td align="left"><jims2:displayBoolean name="spIndex" property="inHouse" trueValue="YES" falseValue="NO"/></td> 
            											<td align="left"><bean:write name="spIndex" property="programName"/></td>
            											<td nowrap='nowrap' align="left"><bean:write name="spIndex" property="programCodeId" /></td>
            											<td align="left"><bean:write name="spIndex" property="targetInterventionId"/></td>										
            										</tr>
         										  </pg:item>
            									</logic:iterate>
            								</table>
            							</logic:equal>
            							
            							<%-- Add program code as search criteria ER JIMS200075756 --- Start --%>
            							<logic:equal name="serviceProviderForm" property="searchTypeId" value="PC">
          								<table width="100%" cellpadding="2" cellspacing="1">
	      								    <tr bgcolor='#cccccc' height="100%">
	      								   		<td width="1%"></td>
	      								   		<td class="subhead" valign='top' align="left">
	      								   		  <bean:message key="title.serviceProvider"/> <bean:message key="prompt.name"/>
 				                        <jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="serviceProviderName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" levelDeep="3"/>
			                        </td>
           										<td align="left" class="subhead" valign='top' nowrap='nowrap'><bean:message key="prompt.inHouse"/>
           										<jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="inHouse" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" levelDeep="3"/>
           										</td>
           										<td align="left" class="subhead" valign='top'><bean:message key="prompt.program"/> <bean:message key="prompt.name"/>
           										<jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="programName" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" levelDeep="3"/>
           										</td>
           										<td align="left" class="subhead" valign='top' width="3%" nowrap='nowrap'><bean:message key="prompt.program"/> <bean:message key="prompt.code"/>
           										<jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="programCodeId" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" levelDeep="3"/>
           										</td>
           										<td align="left" class="subhead" valign='top' width="3%" nowrap='nowrap'><bean:message key="prompt.targetIntervention"/>
           										<jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="targetInterventionId" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" levelDeep="3"/>
           										</td>
           									</tr>

         								   	<logic:iterate indexId="index" id="spIndex" name="serviceProviderForm" property="searchResults">
         										  <pg:item>
            										<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
            											<td align="left">
            												<bean:define id="sp" name="spIndex" property="serviceProviderId"/>
            												<logic:notEqual name="spIndex" property="serviceProviderStatusId" value="I">
            													<input type="radio" name="selectedValue" value="<%=sp%>"/>
            												</logic:notEqual>
            											</td>
            											<td align="left">
            											  <logic:equal name="spIndex" property="serviceProviderStatusId" value="P">
	            												<a href="javascript:loadViewSP('/<msp:webapp/>handleJuvServiceProviderSearchResults.do?submitAction=View')" style="font-style: Italic" onclick="setSP('<bean:write name="spIndex" property="serviceProviderId"/>')">
	            											</logic:equal>
	            											<logic:notEqual name="spIndex" property="serviceProviderStatusId" value="P">
	            												<a href="javascript:loadViewSP('/<msp:webapp/>handleJuvServiceProviderSearchResults.do?submitAction=View')" onclick="setSP('<bean:write name="spIndex" property="serviceProviderId"/>')">
	            											</logic:notEqual>
            												<bean:write name="spIndex" property="serviceProviderName"/></a>														
            											</td>
            											<td align="left"><jims2:displayBoolean name="spIndex" property="inHouse" trueValue="YES" falseValue="NO"/></td> 
            											<td align="left"><bean:write name="spIndex" property="programName"/></td>
            											<td align="left" nowrap='nowrap'><bean:write name="spIndex" property="programCodeId" /></td>
            											<td align="left" nowrap='nowrap'><bean:write name="spIndex" property="targetInterventionId"/></td>										
            										</tr>
         										  </pg:item>
            									</logic:iterate>
            								</table>
            							</logic:equal>
            							<%-- Add program code as search criteria ER JIMS200075756 --- End --%>
            							
						
              						<logic:equal name="serviceProviderForm" property="searchTypeId" value="SP">
              					    							      
    							      	<table width="100%" cellpadding="2" cellspacing="1">
	        								      <tr class='formDeLabel'>
	        								      	<td width="1%"></td>
	        								   		<td valign='top' align="left"><bean:message key="title.serviceProvider"/> <bean:message key="prompt.name"/>
	        								   		  <jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="juvServProviderName" primarySortType="STRING"  defaultSortOrder="ASC" sortId="6" levelDeep="3"/>
	        								   		</td>
		        										<td valign='top' nowrap='nowrap' align="left"><bean:message key="prompt.inHouse"/>
		        										  <jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="inHouse" primarySortType="STRING" defaultSortOrder="ASC" sortId="7" levelDeep="3"/>
		        										</td>
		        										<td valign='top' align="left"><bean:message key="prompt.status"/>
		        										 <jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="statusId" primarySortType="STRING" defaultSortOrder="ASC" sortId="8" levelDeep="3"/>
		        										</td>																					
		        										<td valign='top' width='5%' nowrap='nowrap' align="left"><bean:message key="prompt.status"/> <bean:message key="prompt.change"/> <bean:message key="prompt.date"/>
		        										 <jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="statusChangeDate" primarySortType="DATE" defaultSortOrder="ASC" sortId="9" levelDeep="3"/>
		        										</td>		        										
		        									</tr>
	
	      								   		<logic:iterate indexId="index" id="spIndex1" name="serviceProviderForm" property="searchResults">
	      										  <pg:item>
	        										<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
	        											<td align="left">
	        												<bean:define id="sp" name="spIndex1" property="juvServProviderId"/>
		      												<logic:notEqual name="spIndex1" property="statusId" value="INACTIVE">
		      													<input type="radio" name="selectedValue" value="<%=sp%>"/>
		      												</logic:notEqual>
	        											</td>
	        											<td align="left">
	        											  <logic:equal name="spIndex1" property="statusId" value="PENDING">
	        													<a href="javascript:loadViewSP('/<msp:webapp/>handleJuvServiceProviderSearchResults.do?submitAction=View')"  style="font-style: Italic" onclick="setSP('<bean:write name="spIndex1" property="juvServProviderId"/>')">
	        												</logic:equal>
	        												<logic:notEqual name="spIndex1" property="statusId" value="PENDING">
	        													<a href="javascript:loadViewSP('/<msp:webapp/>handleJuvServiceProviderSearchResults.do?submitAction=View')" onclick="setSP('<bean:write name="spIndex1" property="juvServProviderId"/>')">
	        												</logic:notEqual>
	        												<bean:write name="spIndex1" property="juvServProviderName"/></a>
	        											</td> 
	        											<td align="left"><jims2:displayBoolean name="spIndex1" property="inHouse" trueValue="YES" falseValue="NO"/></td>
	        											<td align="left"><bean:write name="spIndex1" property="statusId"/></td>
	        											<td nowrap='nowrap' align="left"><bean:write name="spIndex1" property="statusChangeDate" formatKey="datetime.format.mmddyyyyHHmmAMPM"/> </td>																				
	        										</tr>
	      										  </pg:item>
	      									  </logic:iterate>
	    							      </table>
    							      
          						</logic:equal>
						
          						<logic:equal name="serviceProviderForm" property="searchTypeId" value="SV">
            						<table width="100%" cellpadding="2" cellspacing="1">
          							    <tr bgcolor='#cccccc'>
          							    	<td width="1%"></td>
          							   		<td class="subhead" valign='top' align="left"><bean:message key="title.serviceProvider"/> <bean:message key="prompt.name"/>
          							   		 <jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="serviceProviderName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="10" levelDeep="3"/>
          								   	</td>
          									<td class="subhead" valign='top' nowrap='nowrap' align="left"><bean:message key="prompt.inHouse"/>
          									 <jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="inHouse" primarySortType="STRING" defaultSortOrder="ASC" sortId="11" levelDeep="3"/>
          									</td>
          									<td class="subhead" valign='top' align="left"><bean:message key="prompt.service"/> <bean:message key="prompt.type"/>
          						 	   		 <jims2:sortResults beanName="serviceProviderForm" results="searchResults" primaryPropSort="serviceTypeId" primarySortType="STRING" defaultSortOrder="ASC" sortId="12" levelDeep="3"/>
          									</td>									
          								</tr>
          								<tr bgcolor='#cccccc'>
          								 	<td></td>
          							   		<td class="subhead" valign='top' nowrap='nowrap' align="left"><bean:message key="prompt.program"/> <bean:message key="prompt.name"/></td>
          									<td class="subhead" valign='top' align="left"><bean:message key="prompt.targetIntervention"/></td>																					
          									<td class="subhead" valign='top' align="left"><bean:message key="prompt.service"/> <bean:message key="prompt.name"/></td>
          								</tr>
          							   	<logic:iterate indexId='index' id="spIndex" name="serviceProviderForm" property="searchResults">
          							   	  <pg:item>
            									<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
             										<td align="left">
             											<bean:define id="sp" name="spIndex" property="serviceProviderId"/>
              										<logic:notEqual name="spIndex" property="serviceProviderStatusId" value="I">
              												<input type="radio" name="selectedValue" value="<%=sp%>"/>
              										</logic:notEqual>
              									</td>
              									<td align="left">
             										  <logic:equal name="spIndex" property="serviceProviderStatusId" value="P">
             												<a href="javascript:loadViewSP('/<msp:webapp/>handleJuvServiceProviderSearchResults.do?submitAction=View')" style="font-style: Italic" onclick="setSP('<bean:write name="spIndex" property="serviceProviderId"/>')">
              										</logic:equal>
              										<logic:notEqual name="spIndex" property="serviceProviderStatusId" value="P">
             												<a href="javascript:loadViewSP('/<msp:webapp/>handleJuvServiceProviderSearchResults.do?submitAction=View')" onclick="setSP('<bean:write name="spIndex" property="serviceProviderId"/>')">
	             										</logic:notEqual>
              										<bean:write name="spIndex" property="serviceProviderName"/></a>
              									</td> 
            										<td align="left"><jims2:displayBoolean name="spIndex" property="inHouse" trueValue="YES" falseValue="NO"/></td>
            										<td align="left"><bean:write name="spIndex" property="serviceTypeId"/></td>
             									</tr>
            									<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
              									<td width="1%"></td>											
            										<td align="left"><bean:write name="spIndex" property="programName"/></td>												
            										<td align="left"><bean:write name="spIndex" property="targetInterventionId"/></td>											
            										<td align="left" nowrap='nowrap'><bean:write name="spIndex" property="serviceName" /></td>
             									</tr>
         									  </pg:item>
             							</logic:iterate>
            						</table>
            					</logic:equal>
            
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
            		</td>
            	</tr>
            </table>

            <!-- BEGIN BUTTON TABLE -->
            <table border="0" width="100%">
            	<tr>
            		<td align="center">
            			<html:submit property="submitAction"><bean:message key="button.backToSearch"></bean:message></html:submit>
            			<!-- <input type="button" value="Back" name="return" onClick="history.go(-1);"> -->
            			<jims2:isAllowed requiredFeatures="CS-ASP-UPDATEJUV">
          				 	<html:submit property="submitAction" onclick="return validateRadioFields();"><bean:message key="button.update"></bean:message></html:submit>	
          			 	</jims2:isAllowed>							 
          			 <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>	
          			</td>
          		</tr>
            </table>
          	<!-- END BUTTON TABLE -->
        	</td>
        </tr>
      </table><br>
    </td>
  </tr>
</table>
<!-- END  TABLE -->
</div>

<%-- Begin Pagination Closing Tag --%>
</pg:pager>
<%-- End Pagination Closing Tag --%>

</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>

</html:html>
