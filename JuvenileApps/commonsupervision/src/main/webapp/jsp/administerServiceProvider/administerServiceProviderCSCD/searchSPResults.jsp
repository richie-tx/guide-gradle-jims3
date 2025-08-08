<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Used for search results of Service Providers (CSCD)-->
<!--MODIFICATIONS -->
<!-- DWilliamson 11/19/2007	Create JSP -->
<!-- C Shimke    10-02-2009 #62231 removed defaultSort = "true" from subheading sort statements to allow sort(compareTo) from action to display  -->
<%-- 10/02/2013 Richard Capesatani 75969 hide update button for inactive service providers--%>
 
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDCodeTableConstants" %>
<%@ page import="naming.Features" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

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
<title><bean:message key="title.heading"/> - searchSPResults.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript">
function checkForSingleResult() {
    var rbs = document.getElementsByName("selectedValue");
	if (rbs.length == 1){
		rbs[0].checked = true;
	}	
}
</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="checkEnterKeyAndSubmit(event,true)" onload="checkForSingleResult()">
<html:form action="/handleCSCServiceProviderSelection" target="content">
<logic:equal name="cscServiceProviderSearchForm" property="searchById" value="PR">
    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|16">
</logic:equal>
<logic:notEqual name="cscServiceProviderSearchForm" property="searchById" value="PR">
    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|29">
</logic:notEqual>    
<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
  <input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
	</tr>
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--tabs start-->
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true"/>
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<!-- BEGIN HEADING TABLE -->
						<table width="100%">
							<tr>
								<td align="center" class="header">
								<bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="title.serviceProvider"/>
                                <logic:equal name="cscServiceProviderSearchForm" property="searchById" value="PR">
									<bean:message key="prompt.program"/>
								</logic:equal>
								<bean:message key="title.searchResults"/></td>
							</tr>
						</table>
						<!-- END HEADING TABLE -->
						<%-- BEGIN ERROR TABLE --%>
						<table width="98%" align="center">
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>
						</table>								
                        <%-- END ERROR TABLE --%> 
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
								<td align="center">
									<logic:equal name="cscServiceProviderSearchForm" property="searchById" value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_SEARCHBY_SP%>">
										<logic:notEmpty name="cscServiceProviderSearchForm" property="serviceProviderSearchResults">
											<bean:size id="totalResults" name="cscServiceProviderSearchForm" property="serviceProviderSearchResults"/>
											<b><bean:write name="totalResults"/></b> search results for
											<bean:write name="cscServiceProviderSearchForm" property="searchByFieldsString" filter="false"/>
										</logic:notEmpty>
									</logic:equal>
									<logic:equal name="cscServiceProviderSearchForm" property="searchById" value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_SEARCHBY_PROGRAM%>">
										<logic:notEmpty name="cscServiceProviderSearchForm" property="programSearchResults">
											<bean:size id="totalResults" name="cscServiceProviderSearchForm" property="programSearchResults"/>
											<b><bean:write name="totalResults"/></b> search results for
											<bean:write name="cscServiceProviderSearchForm" property="searchByFieldsString" filter="false"/>
										</logic:notEmpty>
									</logic:equal>
								</td>
							</tr>
						</table>
						<!-- BEGIN  TABLE -->
						<table width="98%" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td width="100%" valign="top">
									<table width="100%" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td><bean:message key="prompt.serviceProviders" /></td>
										</tr>
										<tr>
											<td>
												<logic:equal name="cscServiceProviderSearchForm" property="searchById" value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_SEARCHBY_SP%>">
            									<% int RecordCounter2=0;
            								   String bgcolor2="";
            								  %>
												<table width="100%" cellpadding="2" cellspacing="1"  id="spList">
													<tr class="formDeLabel">
														<td width="1%"></td>
														<td title="Service Provider Name" nowrap><bean:message key="prompt.serviceProviderName" />
															<jims2:sortResults beanName="cscServiceProviderSearchForm" results="serviceProviderSearchResults" primaryPropSort="serviceProviderName" primarySortType="STRING"  defaultSortOrder="ASC" sortId="7" levelDeep="3" /></td>
														<td><bean:message key="prompt.inHouse" />
															<jims2:sortResults beanName="cscServiceProviderSearchForm" results="serviceProviderSearchResults" primaryPropSort="serviceProviderInHouseDesc" primarySortType="STRING" defaultSortOrder="ASC" sortId="8" levelDeep="3"/>  </td>
														<td><bean:message key="prompt.status" />
															<jims2:sortResults beanName="cscServiceProviderSearchForm" results="serviceProviderSearchResults" primaryPropSort="serviceProviderStatusDesc" primarySortType="STRING" defaultSortOrder="ASC" sortId="9" levelDeep="3"/></td>
														<td><bean:message key="prompt.contractProgram" />
															<jims2:sortResults beanName="cscServiceProviderSearchForm" results="serviceProviderSearchResults" primaryPropSort="serviceProviderInContractProgramDesc" primarySortType="STRING" defaultSortOrder="ASC" sortId="10" levelDeep="3"/>  </td>  
														<td><bean:message key="prompt.statusChangeDate" />
															<jims2:sortResults beanName="cscServiceProviderSearchForm" results="serviceProviderSearchResults" primaryPropSort="statusChangeDateAsStr" primarySortType="DATE" defaultSortOrder="ASC" sortId="11" levelDeep="3"/> </td>
													</tr>
                                                 <logic:iterate id="spIndex1" name="cscServiceProviderSearchForm" property="serviceProviderSearchResults">
      										      <pg:item>
													<tr class= '<% RecordCounter2++;
											  bgcolor2 = "alternateRow";                      
											  if (RecordCounter2 % 2 == 1)
												  bgcolor2 = "normalRow";
											   out.print(bgcolor2); %>'>
														<td>
															<bean:define id="sp" name="spIndex1" property="serviceProviderId"/>
														
														<logic:notEqual name="spIndex1" property="serviceProviderStatusId" value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_INACTIVE%>">
															<input type="radio" name="selectedValue"  value="<%=sp%>" />
														</logic:notEqual>
														</td>
														<td>
														   	<a href="/<msp:webapp/>displayCSCServiceProviderUpdate.do?submitAction=View&selectedValue=<bean:write name="spIndex1" property="serviceProviderId"/>" >
														   
														  
																<bean:write name="spIndex1" property="serviceProviderName"/></a>
														</td> 
														<td><bean:write name="spIndex1" property="serviceProviderInHouseDesc"/></td>
														<td><bean:write name="spIndex1" property="serviceProviderStatusDesc"/></td>
                                                        <td><bean:write name="spIndex1" property="serviceProviderInContractProgramDesc" /></td>
														<td nowrap><bean:write name="spIndex1" property="statusChangeDateAsStr" formatKey="datetime.format.mmddyyyyHHmmAMPM"/> </td>																					
													</tr>
												  </pg:item>
											     </logic:iterate>
														
													</table>
                                                </logic:equal>
          							            <logic:equal name="cscServiceProviderSearchForm" property="searchById" value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_SEARCHBY_PROGRAM%>">
													<table width="100%" cellpadding="2" cellspacing="1" id="spList">
														<tr class="formDeLabel">
															<td width="1%"></td>
															<td title="Service Provider Name" nowrap><bean:message key="prompt.serviceProviderName" />
															    <jims2:sortResults beanName="cscServiceProviderSearchForm" results="programSearchResults" primaryPropSort="serviceProviderName" primarySortType="STRING"  defaultSortOrder="ASC" sortId="1" levelDeep="3" /></td>
														    <td><bean:message key="prompt.inHouse" />
															    <jims2:sortResults beanName="cscServiceProviderSearchForm" results="programSearchResults" primaryPropSort="serviceProviderInHouseDesc" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" levelDeep="3"/></td>  
														    <td><bean:message key="prompt.status" />
															    <jims2:sortResults beanName="cscServiceProviderSearchForm" results="programSearchResults" primaryPropSort="serviceProviderStatusDesc" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" levelDeep="3"/></td>
															<td><bean:message key="prompt.contractProgram" />
															    <jims2:sortResults beanName="cscServiceProviderSearchForm" results="programSearchResults" primaryPropSort="serviceProviderInContractProgramDesc" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" levelDeep="3"/></td>
															<td><bean:message key="prompt.programId" />
															    <jims2:sortResults beanName="cscServiceProviderSearchForm" results="programSearchResults" primaryPropSort="programIdentifier" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" levelDeep="3"/></td>
															<td><bean:message key="prompt.programName" />
															    <jims2:sortResults beanName="cscServiceProviderSearchForm" results="programSearchResults" primaryPropSort="programName" primarySortType="STRING" defaultSortOrder="ASC" sortId="6" levelDeep="3"/></td>
															<td>Referral Type
															    <jims2:sortResults beanName="cscServiceProviderSearchForm" results="programSearchResults" primaryPropSort="referralType" primarySortType="STRING" defaultSortOrder="ASC" sortId="7" levelDeep="3"/></td>
														</tr>
														<% int RecordCounter=0;
            								   String bgcolor="";
            								  %>
														<logic:iterate id="spIndex" name="cscServiceProviderSearchForm" property="programSearchResults">
            										 <pg:item>
														<tr class= '<% RecordCounter++;
												  bgcolor = "alternateRow";                      
												  if (RecordCounter % 2 == 1)
													  bgcolor = "normalRow";
												   out.print(bgcolor); %>'>
															<td width="1%"><bean:define id="sp" name="spIndex" property="serviceProviderId"/>
            												   
														<logic:notEqual name="spIndex" property="serviceProviderStatusId" value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_INACTIVE%>">
															<input type="radio" name="selectedValue"  value="<%=sp%>" />
														</logic:notEqual>
														</td>
														<td>
														   		<jims2:isAllowed requiredFeatures="<%=Features.CS_ASP_VIEW_CSC%>"> 
														   		
														   		<a href="/<msp:webapp/>displayCSCServiceProviderUpdate.do?submitAction=View&selectedValue=<bean:write name="spIndex" property="serviceProviderId"/>" >
														  
														   </jims2:isAllowed>
														   
            													<bean:write name="spIndex" property="serviceProviderName"/>
            														<jims2:isAllowed requiredFeatures="<%=Features.CS_ASP_VIEW_CSC%>">
            													</a>
            													</jims2:isAllowed>
            													</td>
															<td><bean:write name="spIndex" property="serviceProviderInHouseDesc"/></td>
															<td><bean:write name="spIndex" property="serviceProviderStatusDesc"/></td>
															<td><bean:write name="spIndex" property="serviceProviderInContractProgramDesc"/></td>
															<td><bean:write name="spIndex" property="programIdentifier"/></td>
															<td><bean:write name="spIndex" property="programName"/></td>
															<td><bean:write name="spIndex" property="referralType"/></td>
														</tr>
													</pg:item>
            									        </logic:iterate>
            								       </table>
  												
												</logic:equal></td>
											</tr>
										</table>
									</td>
								</tr>
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
							<div class="spacer4px"></div>
							<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">
								<tr>
									<td align="center">
										<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
										<logic:notEqual name="cscServiceProviderSearchForm" property="serviceProviderStatusDesc" value="INACTIVE">
											<jims2:isAllowed requiredFeatures="<%=Features.CS_ASP_UPDATE_CSC%>">
												<html:submit property="submitAction" onclick="return validateRadios(this.form,'selectedValue','A service provider must be selected');"><bean:message key="button.update"></bean:message></html:submit>	
											</jims2:isAllowed>
										</logic:notEqual>				 
									 <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>	
									</td>
								</tr>
							</table>
                       
							<!-- END BUTTON TABLE -->
						</td>
					</tr>
				</table>
				<br>
			</td>
		</tr>
	</table>
	<!-- END  TABLE -->
</div>
<br>
</pg:pager></html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
<html:html></html:html>
