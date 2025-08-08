<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 02/25/2009	 CShimek        - Create JSP -->
<!-- 05/26/2010  CShime         - #65515 revised default sort to program unit date and name -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


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
<title><bean:message key="title.heading" /> - posttrial/caseAssignmentDataControlSearchList.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/posttrial/caseAssignmentDataControlSearchList.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload ="checkSingleHistory()">
<html:form action="/handleCaseAssignmentDataControlSelect" target="content">
<input type="hidden" name="helpFile" value="">
<div align="center">
<!-- Begin Pagination Header Tag -->
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
	<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    	maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
  	<input type="hidden" name="pager.offset" value="<%= offset %>">
<!-- End Pagination header  -->
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
  	</tr>
  	<tr>
    	<td valign="top">
<!-- BEGIN TAB TABLE -->    	
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
					<!--tabs start-->
						<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true"></tiles:insert>		
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>  
<!-- END TABS TABLE -->	
<!-- BEGIN BLUE BORDER TABLE -->		
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
						<table width="100%">
							<tr>							
								<td align="center" class="header">CSCD - <bean:message key="prompt.caseAssignment"/> <bean:message key="prompt.dataControl"/> <bean:message key="prompt.searchResults"/></td>						
							</tr>
						</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
						<table width="98%" align="center">							
							<tr>
								<td align="center" class="errorAlert"><html:errors></html:errors></td>
							</tr>		
						</table> 
<!-- END ERROR TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
							<tr>
								<td>
									<ul>
										<li>Click Update to update the current case assignment.</li>
										<li>Select a Supervisee and click Correct or Delete to perform those actions on historical records.</li>
									</ul>
								</td>
							</tr>
						</table>
<!-- END INSTRUCTION TABLE -->	
<!-- BEGIN SUPERVISEE INFO TABLE -->    	
						<table width="100%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top" align="center">
									<tiles:insert page="../common/caseAssignmentDataControlHeader.jsp" flush="true"></tiles:insert> 		
								</td>
							</tr>
						</table>  
<!-- END SUPERVISEE INFO TABLE -->	
						<div class="spacer4px"></div>																				
<!-- BEGIN CURRENT ASSIGNMENT TABLE -->									
						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class="detailHead" colspan="2"><bean:message key="prompt.current" /> <bean:message key="prompt.caseAssignment" /></td>	
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellpadding="2" cellspacing="1" class="" id="uniqueID">
										<tr class="formDeLabel">
											<td><bean:message key="prompt.name" /></td>
											<td><bean:message key="prompt.programUnit" /> / <bean:message key="prompt.date" /></td>
											<td><bean:message key="prompt.supervisor" /> / <bean:message key="prompt.date" /></td>
											<td><bean:message key="prompt.position" /> / <bean:message key="prompt.poi" /> / <bean:message key="prompt.officer" /> / <bean:message key="prompt.date" /></td>
											<td>Last Acknowledge <bean:message key="prompt.userName" />  / <bean:message key="prompt.userId" /> / <bean:message key="prompt.date" /></td>
										</tr>
										<tr>
											<td valign="top">
												<bean:write name="caseAssignmentDataControlForm" property="currentCaseAssignment.defendantNameStr" />
											</td>
											<td valign="top">
												<div><bean:write name="caseAssignmentDataControlForm" property="currentCaseAssignment.programUnitName" /></div>
												<div><bean:write name="caseAssignmentDataControlForm" property="currentCaseAssignment.programUnitAssignDate" formatKey="date.format.mmddyyyy" /></div>
											</td>
											<td valign="top">
												<div><bean:write name="caseAssignmentDataControlForm" property="currentCaseAssignment.supervisorName" /></div>
												<div><bean:write name="caseAssignmentDataControlForm" property="currentCaseAssignment.supervisorAllocationDate" formatKey="date.format.mmddyyyy" /></div>
											</td>
											<td valign="top">
												<div><bean:write name="caseAssignmentDataControlForm" property="currentCaseAssignment.assignedStaffPositionName" /></div>
												<div><bean:write name="caseAssignmentDataControlForm" property="currentCaseAssignment.probationOfficerInd" /></div>
												<div><bean:write name="caseAssignmentDataControlForm" property="currentCaseAssignment.officerName" /></div>
												<div><bean:write name="caseAssignmentDataControlForm" property="currentCaseAssignment.officerAssignDate" formatKey="date.format.mmddyyyy" /></div>
											</td>
											<td valign="top">
												<div><bean:write name="caseAssignmentDataControlForm" property="currentCaseAssignment.acknowledgeUserName" /></div>
												<div><bean:write name="caseAssignmentDataControlForm" property="currentCaseAssignment.acknowledgeUserId" /></div>
												<div><bean:write name="caseAssignmentDataControlForm" property="currentCaseAssignment.acknowledgeDate" formatKey="date.format.mmddyyyy" /></div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>  
<!-- END CURRENT ASSIGNMENT TABLE -->
<!-- BEGIN BUTTON TABLE 1 -->
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction" onclick="disableSubmit(this, this.form);"><bean:message key="button.update"/></html:submit>
								</td>
							</tr>
						</table> 
<!-- END BUTTON TABLE 1 -->						
<!-- BEGIN CURRENT ASSIGNMENT TABLE -->									
						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr>
								<td class="detailHead" colspan="2"><bean:message key="prompt.case" /> <bean:message key="prompt.history" /> <bean:message key="prompt.assignment" /></td>	
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellpadding="2" cellspacing="1">
										<logic:notEmpty name="caseAssignmentDataControlForm" property="caseAssignmentHistoryList">
											<tr class="formDeLabel">
												<td>&nbsp;</td>
												<td><bean:message key="prompt.name" />
													<jims2:sortResults beanName="caseAssignmentDataControlForm" results="caseAssignmentHistoryList" primaryPropSort="defendantNameStr" primarySortType="STRING" defaultSortOrder="ASC" sortId="1" />
												</td>
												<td><bean:message key="prompt.programUnit" /> / <bean:message key="prompt.date" />
													<jims2:sortResults beanName="caseAssignmentDataControlForm" results="caseAssignmentHistoryList" primaryPropSort="programUnitAssignDate" primarySortType="DATE" secondPropSort="programUnitName" secondarySortType="STRING"  defaultSort="true" defaultSortOrder="DESC" sortId="2" />
												</td>
												<td><bean:message key="prompt.supervisor" /> / <bean:message key="prompt.date" />
													<jims2:sortResults beanName="caseAssignmentDataControlForm" results="caseAssignmentHistoryList" primaryPropSort="supervisorName" primarySortType="STRING" secondPropSort="supervisorAllocationDate" secondarySortType="DATE" defaultSortOrder="ASC" sortId="3" />
												</td>
												<td><bean:message key="prompt.position" /> / <bean:message key="prompt.poi" /> / <bean:message key="prompt.officer" /> / <bean:message key="prompt.date" />
												<jims2:sortResults beanName="caseAssignmentDataControlForm" results="caseAssignmentHistoryList" primaryPropSort="assignedStaffPositionName" primarySortType="STRING" secondPropSort="officerAssignDate" secondarySortType="DATE" defaultSortOrder="ASC" sortId="4" />
												</td>
												<td>Last Acknowledge <bean:message key="prompt.userName" />  / <bean:message key="prompt.userId" /> / <bean:message key="prompt.date" />
												<jims2:sortResults beanName="caseAssignmentDataControlForm" results="caseAssignmentHistoryList" primaryPropSort="acknowledgeUserName" primarySortType="STRING" secondPropSort="acknowledgeDate" secondarySortType="DATE" defaultSortOrder="ASC" sortId="5" />
												</td>
											</tr>
											<logic:iterate id="chaIndex" name="caseAssignmentDataControlForm" property="caseAssignmentHistoryList" indexId="index">
												<pg:item>
												<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
			                                    	<td align="center">
			                                           	<input type="radio" name="selectedValue" value='<bean:write name="chaIndex" property="caseAssignmentHistId"/>' >
			                                        </td>
													<td valign="top"><bean:write name="chaIndex" property="defendantNameStr" /></td>
													<td valign="top">
														<div><bean:write name="chaIndex" property="programUnitName" /></div>
														<div><bean:write name="chaIndex" property="programUnitAssignDate" formatKey="date.format.mmddyyyy"/></div>
													</td>
													<td valign="top">
														<div><bean:write name="chaIndex" property="supervisorName" /></div>
														<div><bean:write name="chaIndex" property="supervisorAllocationDate" formatKey="date.format.mmddyyyy"/></div>
													</td>
													<td valign="top">
														<div><bean:write name="chaIndex" property="assignedStaffPositionName" /></div>
														<div><bean:write name="chaIndex" property="probationOfficerInd" /></div>
														<div><bean:write name="chaIndex" property="officerName" /></div>
														<div><bean:write name="chaIndex" property="officerAssignDate" formatKey="date.format.mmddyyyy"/></div>
													</td>													
													<td valign="top">
														<div><bean:write name="chaIndex" property="acknowledgeUserName" /></div>
														<div><bean:write name="chaIndex" property="acknowledgeUserId" /></div>
														<div><bean:write name="chaIndex" property="acknowledgeDate" formatKey="date.format.mmddyyyy"/></div>
													</td>
												</tr>
												</pg:item>
											</logic:iterate>  
<!-- BEGIN PAGINATION NAVIGATOIN ROW -->
											<tr>
												<td colspan="6">
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
<!-- END PAGINATION NAVIGATOIN ROW -->	
										</logic:notEmpty>
										<logic:empty name="caseAssignmentDataControlForm" property="caseAssignmentHistoryList">
											<tr>
												<td>No Case Assignment History records found</td>
											</tr>
										</logic:empty>
									</table>
								
								</td>
							</tr>
						</table> 
<!-- END CURRENT ASSIGNMENT TABLE -->
<!-- BEGIN BUTTON TABLE 2 -->
			 			<table border="0" width="100%">
							<logic:notEmpty name="caseAssignmentDataControlForm" property="caseAssignmentHistoryList">
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="return validateSelect() && disableSubmit(this, this.form);"><bean:message key="button.correct"/></html:submit>
										<html:submit property="submitAction" onclick="return validateSelect() && disableSubmit(this, this.form);"><bean:message key="button.delete"/></html:submit>
									</td>
								</tr>
							</logic:notEmpty>	
							<tr>
								<td align="center">
									<html:submit property="submitAction" onclick="disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>
									<html:submit property="submitAction" onclick="disableSubmit(this, this.form);"><bean:message key="button.cancel"/></html:submit>
								</td>
							</tr>
						</table> 
<!-- END BUTTON TABLE2 -->
					</td>
				</tr>
			</table>
<!-- END BLUE BORDER TABLE -->			
		</td>
	</tr>
</table>
</pg:pager>
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>