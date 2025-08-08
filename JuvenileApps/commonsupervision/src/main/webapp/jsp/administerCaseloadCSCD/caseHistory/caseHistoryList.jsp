<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 02/27/2008 C. Shimek - Converted to JSP -->
<!-- 02/02/2009 C. Shimek - #56967 commented out Motions button and added onload script to select single case -->
<!-- 12/09/2009 C. Shimek - added return to resetPagination() so disableSubmit() will function -->
<!-- 01/13/2010 C. Shimek - #63468 added feature check around Violation Report and Case Summary buttons and code to hide radio based on features -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<%@ page import="naming.Features" %>


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/caseHistory/caseHistoryList.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript">
function selectSingleCase(){
	var rb = document.getElementsByName("selectedOrderId");
	if (rb.length == 1){
		rb[0].checked = true;
	}	
}
function resetPagination(){
	document.getElementsByName("pager.offset")[0].value=0; 
	return true;
}
</script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="selectSingleCase()">
<html:form action="/handleCaseHistorySelection" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Case_Summary/CSCD_Case_Summary.htm#|32">
<!-- Begin Pagination Header Tag -->
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
	<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    	maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
  	<input type="hidden" name="pager.offset" value="<%= offset %>">
<!-- End Pagination header  -->
<!-- BEGIN PAGE TABLE -->
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->		
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tabid" value="conplianceTab"/>
						</tiles:insert>						
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
<!-- END BLUE TABS TABLE -->	
<!-- BEGIN BLUE BORDER TABLE -->				
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<tiles:insert page="../../common/superviseeHeader.jsp" flush="true">
						</tiles:insert> 
					</td>
				</tr>
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td>
<!-- BEGIN GREEN TABS TABLE -->	
						<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
							<tr>
								<td valign="top" align="center">
									<tiles:insert page="../../common/caseloadCSCDSubTabs.jsp" flush="true">
										<tiles:put name="tab" value="CasesTab" />
									</tiles:insert>
								</td>
							</tr>
							<tr>
								<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
<!-- END GREEN TABS TABLE -->	
<!-- BEGIN GREEN BORDER TABLE -->						
						<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header">
												<bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.case"/>&nbsp;<bean:message key="prompt.history"/>
											</td>
										</tr>
									</table>
<!-- END HEADING TABLE -->
<%-- BEGIN ERROR TABLE --%>
									<table width="98%" align="center">
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>
									</table>								
<!-- END ERROR TABLE -->
									<logic:notEmpty name="caseHistoryForm" property="caseHistoryList">
<!-- BEGIN DETAILS TABLE -->
	 									<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
											<tr class="detailHead">
												<logic:equal name="caseHistoryForm" property="allowUpdates" value="true">
													<td width="1%">&nbsp;</td>
												</logic:equal>
												<td><bean:message key="prompt.CDI" />
	                                                <jims2:sortResults beanName="caseHistoryForm" results="caseHistoryList" primaryPropSort="cdi" primarySortType="INTEGER" secondPropSort="caseNumber" secondarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" levelDeep="3"/>  
	                                            </td>
												<td><bean:message key="prompt.case#" />
	                                              <jims2:sortResults beanName="caseHistoryForm" results="caseHistoryList" primaryPropSort="caseNumber" primarySortType="STRING" defaultSort="false" sortId="2" levelDeep="3"/> 
	                                            </td>
												<td><bean:message key="prompt.CRT" />
	                                                <jims2:sortResults beanName="caseHistoryForm" results="caseHistoryList" primaryPropSort="displayCourtId" primarySortType="STRING" defaultSort="false"  sortId="3" levelDeep="3"/>  
	                                            </td>
												<td><bean:message key="prompt.offense" />
	                                                <jims2:sortResults beanName="caseHistoryForm" results="caseHistoryList" primaryPropSort="offenseCodeDesc" primarySortType="STRING" defaultSort="false" sortId="4" levelDeep="3"/>  
	                                            </td>
												<td><bean:message key="prompt.caseFiled" />&nbsp;<bean:message key="prompt.date" />
	                                               <jims2:sortResults beanName="caseHistoryForm" results="caseHistoryList" primaryPropSort="caseFileDate" primarySortType="DATE" defaultSort="false" sortId="5" levelDeep="3"/>  
	                                            </td>
												<td><bean:message key="prompt.orderStatus" />
	                                              <jims2:sortResults beanName="caseHistoryForm" results="caseHistoryList" primaryPropSort="orderStatus" primarySortType="STRING" defaultSort="false"  sortId="6" levelDeep="3"/>  
	                                            </td>
												<td><bean:message key="prompt.version" />
	                                              <jims2:sortResults beanName="caseHistoryForm" results="caseHistoryList" primaryPropSort="version" primarySortType="STRING" defaultSort="false" sortId="7" levelDeep="3"/>  
	                                            </td>
												<td><bean:message key="prompt.orderFiledDate" />
	                                              <jims2:sortResults beanName="caseHistoryForm" results="caseHistoryList" primaryPropSort="orderFiledDate" primarySortType="DATE" defaultSort="false" sortId="8" levelDeep="3"/> 
	                                            </td>
											</tr>
	                                     	<logic:iterate id="chIndex" name="caseHistoryForm" property="caseHistoryList" indexId="index">
												<pg:item>
													<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
														<logic:equal name="caseHistoryForm" property="allowUpdates" value="true">
				                                            <td align="center">
			                                            		<input type="radio" name="selectedOrderId" value='<bean:write name="chIndex" property="supervisionOrderId"/>' >
			                                            	</td>
			                                            </logic:equal>
														<td><bean:write name="chIndex" property="cdi" /></td>
														<td>
															<a href="javascript:openWindow('/<msp:webapp/>displaySupervisionOrderDetails.do?submitAction=Link&selectedValue=<bean:write name="chIndex" property="supervisionOrderId"/>')">
													<%--	<a href="/<msp:webapp/>handleSupervisionOrderSelection.do?submitAction=Link&selectedValue=<bean:write name="chIndex" property="supervisionOrderId"/>"> --%>
																<bean:write name="chIndex" property="caseNumber" />
															</a>
														</td>
														<td><bean:write name="chIndex" property="displayCourtId" /></td>
														<td><bean:write name="chIndex" property="offenseCodeDesc" /></td>													
														<td><bean:write name="chIndex" property="caseFileDate" formatKey="date.format.mmddyyyy"/></td>
														<td><bean:write name="chIndex" property="orderStatus" /></td>
														<td><bean:write name="chIndex" property="version" /></td>										
														<td><bean:write name="chIndex" property="orderFiledDate" formatKey="date.format.mmddyyyy"/></td>																
													</tr>
	                                          	</pg:item>      
	                                      	</logic:iterate> 
	
										</table>  
									</logic:notEmpty>
									<!-- BEGIN PAGINATION NAVIGATOIN ROW -->
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
									<!-- END PAGINATION NAVIGATOIN ROW -->
<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
										</tr>
										<tr>
											<td align="center">
												<logic:notEmpty name="caseHistoryForm" property="caseHistoryList">
													<jims2:isAllowed requiredFeatures='<%=Features.CSCD_CASE_SUMMARY_CREATE%>'>
														<html:submit property="submitAction" onclick="return validateRadios(this.form, 'selectedOrderId', 'Please select a case.') && resetPagination() && disableSubmit(this, this.form) "><bean:message key="button.violationReports" /></html:submit>
													</jims2:isAllowed>	
													<jims2:isAllowed requiredFeatures='<%=Features.CSCD_VIOLATION_REPORT_CREATE%>'>
														<html:submit property="submitAction" onclick="return validateRadios(this.form, 'selectedOrderId', 'Please select a case.') && resetPagination() && disableSubmit(this, this.form) "><bean:message key="button.caseSummaryList" /></html:submit>
													</jims2:isAllowed>	
												</logic:notEmpty>	
												<logic:empty name="caseHistoryForm" property="caseHistoryList"> 
													<html:submit property="submitAction" disabled="true"><bean:message key="button.violationReports" /></html:submit>	
													<html:submit property="submitAction" disabled="true"><bean:message key="button.caseSummaryList" /></html:submit>	
												</logic:empty>	
												<%-- future button 
												<html:submit property="submitAction" disabled="true"><bean:message key="button.motions" /></html:submit>
												--%>	
											</td>
										</tr>
										<tr>
											<td align="center">
												<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
												<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
											</td>
										</tr>
									</table>
<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table>
<!-- END GREEN BORDER TABLE -->
						<br>
						</td>
					</tr>
				</table>
<!-- END BLUE BORDER TABLE -->
				<br>
			</td>
		</tr>
	</table>
<!-- END PAGE TABLE -->
</pg:pager>
	<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>