<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 02/27/2008 C Shimek - Converted to JSP -->
<!-- 01/16/2009 C Shimek - added hidden field and logic tags for orderStatus, needed for inactive button display -->
<!-- 01/26/2009 C Shimek  - #52815 revised dateformat in status block -->
<!-- 02/13/2009 C Shimke  - add logic tag around Maintain button, user must be SA  -->
<!-- 02/20/2009 C Shimek  - #57414 added code and call to auto select single search result -->
<!-- 03/09/2009 C Shimek  - 57049 add Maintain button with logic check for user not = SA but has CSCD_NONCOMP_MAINT feature  -->
<!-- 08/02/2013 R Capestani  - 75880 display the case summary create button when the statusId ="CD"  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@page import="naming.UIConstants"%>
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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/caseHistory/violationReportList.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/caseHistory/caseSummary/caseSummaryList.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="return selectSingleResult()">
<html:form action="/handleCaseSummarySelection" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Case_Summary/CSCD_Case_Summary.htm#|1">
<!-- Begin Pagination Header Tag -->
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
	<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    	maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
  	<input type="hidden" name="pager.offset" value="<%= offset %>">
<!-- End Pagination header  -->
<div align="center">
<!-- BEGIN PAGE TABLE -->
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->		
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
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
<!-- BEGIN SUPERVISEE HEADER TABLE -->	
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td bgcolor="#cccccc" colspan="2">
									<tiles:insert page="../../../common/caseloadHeaderCase.jsp" flush="true">
									</tiles:insert> 
								</td>
							</tr>
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
<!-- END SUPERVISEE HEADER TABLE -->	
<!-- BEGIN GREEN TABS TABLE -->	
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<tiles:insert page="../../../common/caseloadCSCDSubTabs.jsp" flush="true">
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
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header">
												<bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.caseSummary"/>&nbsp;<bean:message key="prompt.list"/>
												<input type="hidden" name="orderStatusVal" value="<bean:write name="caseSummaryForm" property="orderStatus" />" />
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
									<logic:notEmpty name="caseSummaryForm" property="caseSummaryDisplayList"> 
<!-- BEGIN DETAILS TABLE -->
										<table width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
											<tr class="detailHead">
												<td width="1%"></td>
												<td><bean:message key="prompt.status" />
	                                                <jims2:sortResults beanName="caseSummaryForm" results="caseSummaryDisplayList" primaryPropSort="status" primarySortType="STRING"  defaultSortOrder="DESC" defaultSort="true" sortId="1" levelDeep="4"/>
	                                            </td>
												<td><bean:message key="prompt.statusChangedDate" />
	                                                <jims2:sortResults beanName="caseSummaryForm" results="caseSummaryDisplayList" primaryPropSort="statusChangedDate" primarySortType="DATE"  defaultSortOrder="DESC" defaultSort="false" sortId="2" levelDeep="4"/>
	                                            </td>
												<td><bean:message key="prompt.createdBy" />
	                                                <jims2:sortResults beanName="caseSummaryForm" results="caseSummaryDisplayList" primaryPropSort="createdBy" primarySortType="STRING"  defaultSortOrder="ASC" defaultSort="false" sortId="3" levelDeep="4"/>
	                                            </td>
												<td><bean:message key="prompt.createDate" />
	                                                <jims2:sortResults beanName="caseSummaryForm" results="caseSummaryDisplayList" primaryPropSort="createDate" primarySortType="DATE"  defaultSortOrder="ASC" defaultSort="false" sortId="4" levelDeep="4"/>
	                                            </td>
											</tr>
	                                     <logic:iterate id="csIter" name="caseSummaryForm" property="caseSummaryDisplayList" indexId="index">
											  <pg:item> 
												<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
	                                            	<td align="center">
	                                            		<input type="radio" name="csStatus" value='<bean:write name="csIter" property="statusId"/>' onClick="disableButtons('<bean:write name="csIter" property="statusId"/>') & setCSvalue('<bean:write name="csIter" property="ncResponseId"/>');">
	                                            		<input type="hidden" name="ncResponseId" value=<bean:write name="csIter" property="ncResponseId"/>  /> 
	                                            	</td>
													<td><bean:write name="csIter" property="status"/></td>
													<td><bean:write name="csIter" property="statusChangedDate" formatKey="datetime.format.mmddyyyyHHmmAMPM"/></td>
													<td><bean:write name="csIter" property="createdByName"/></td>
													<td><bean:write name="csIter" property="createDate" formatKey="datetime.format.mmddyyyyHHmmAMPM"/></td>
													<logic:notEqual name="csIter" property="statusId" value="CD">
														<logic:notEqual name="csIter" property="statusId" value="FL">
															<logic:notPresent name="hideCreateButton">
																<bean:define id="hideCreateButton" value="Y" />
															</logic:notPresent>
														</logic:notEqual>
													</logic:notEqual>
												</tr>
	                                       	</pg:item>   
											</logic:iterate>  
											<tr>
												<td><input type="hidden" name="violationReportId" value=""></td>
											</tr>
											
										</table>
<!-- BEGIN PAGINATION NAVIGATOIN TABLE -->
										<table width="98%" border="0">
											<tr>
												<td colspan="9">
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
										</table>
<!-- END PAGINATION NAVIGATOIN TABLE -->										
									</logic:notEmpty>	
<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
										<logic:notEmpty name="caseSummaryForm" property="caseSummaryDisplayList">
											<tr>
												<td align="center">
	                                                <html:submit property="submitAction" onclick="return checkSelection(this) && disableSubmit(this, this.form);"> <bean:message key="button.view" /></html:submit> 
	                                                <html:submit property="submitAction" onclick="return checkSelection(this) && disableSubmit(this, this.form)"> <bean:message key="button.update" /></html:submit>
	                                                <logic:notPresent name="hideCreateButton">
	                                                	<logic:equal name="caseSummaryForm" property="orderStatus" value="ACTIVE">
		                                                	<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.createCaseSummary" /></html:submit>
		                                                </logic:equal>	
	                                                </logic:notPresent>
													<html:submit property="submitAction" onclick="return checkSelection(this) && disableSubmit(this, this.form)"> <bean:message key="button.delete" /></html:submit>
												</td>
											</tr>
											<tr>
												<td align="center">
													<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
  													<logic:equal name="caseSummaryForm" property="allowMaintain" value="<%=UIConstants.YES%>">
	                                                	<html:submit property="submitAction" onclick="return checkSelection(this) && disableSubmit(this, this.form)"> <bean:message key="button.maintain" /></html:submit>
	                                                </logic:equal>
  													<logic:notEqual name="caseSummaryForm" property="allowMaintain" value="<%=UIConstants.YES%>">
  														<jims2:isAllowed requiredFeatures='<%=Features.CSCD_NONCOMPLIANCE_MAINT_ACCESS%>'>
		                                                	<html:submit property="submitAction" onclick="return checkSelection(this) && disableSubmit(this, this.form)"> <bean:message key="button.maintain" /></html:submit>
	                                                	</jims2:isAllowed>
	                                                </logic:notEqual>
	                                                <jims2:isAllowed requiredFeatures='<%=Features.CSCD_TASKS_CREATE %>'>
	                                                	<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.createTask" /></html:submit>
	                                                </jims2:isAllowed>
													<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
												</td>
											</tr>
										</logic:notEmpty>
										<logic:empty name="caseSummaryForm" property="caseSummaryDisplayList">
											<tr>
												<td align="center">
													<logic:equal name="caseSummaryForm" property="orderStatus" value="ACTIVE">
	                                                	<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.createCaseSummary" /></html:submit>
	                                                </logic:equal>	
												</td>
											</tr>
											<tr>
												<td align="center">
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.back" /></html:submit>
													<jims2:isAllowed requiredFeatures='<%=Features.CSCD_TASKS_CREATE %>'>
	                                                	<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.createTask" /></html:submit>
	                                                </jims2:isAllowed>
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.cancel" /></html:submit>
												</td>
											</tr>
										</logic:empty>
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
			</td>
		</tr>
	</table>
<!-- END PAGE TABLE -->
</pg:pager>
	<br>
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
