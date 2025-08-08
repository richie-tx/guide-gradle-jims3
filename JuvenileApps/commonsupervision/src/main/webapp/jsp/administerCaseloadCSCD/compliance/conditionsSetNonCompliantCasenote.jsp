<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 10/04/2007	 Debbie Williamson - Create JSP -->
<!-- 06/04/2008  Clarence Shimek   = activity#51908 revised date/time display format -->
<!-- 08/21/2008	 Clarence Shimek   - defect#52794 revised buttons per defect -->
<!-- 10/15/2008  Clarence Shimek   - add missing draft casenote highlight, found testing defect#54593/ER55121 -->
<!-- 09/21/2009	 Clarence Shimek   - #61740 revised Case# to use displayCaseNum for case number dispaly -->
<!-- 05/26/2010  Clarence Shimek   - #65373 revised to use tinyMCECustomInitCasenote.js -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@page import="naming.UIConstants"%>
<%@page import="ui.common.UIUtil"%>

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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/compliance/conditionsSetNonCompliantCasenote.jsp</title>

<!-- JavaScripts -->
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/administerCompliance/complianceCasenotes.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/administerCompliance/casenotesAdd.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/tinyMCECustomInitCasenote.js"></script>
</head>
<body topmargin="0" leftmargin="0"
	<logic:equal name="complianceForm" property="displayAction" value="<%=UIConstants.ADVANCED%>">
		 onLoad="displayAssociatesDropDown();" 
	</logic:equal>	
	<logic:notEqual name="complianceForm" property="displayAction" value="<%=UIConstants.ADVANCED%>">
		onLoad="displayBasicSearchFields(document.forms[0]['searchById'],'true'),displayAssociatesDropDown();" 
	</logic:notEqual>
	onKeyDown="return checkEnterKeyAndSubmit(event,true)">	
	<html:form action="/displaySetToNonCompliantSummary" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Administer_Compliance/CSCD_Compliance.htm#|6">
<%-- input type="hidden" name="helpFile" value="commonsupervision/Administer_Compliance/Common_Sup_Compliance_and_Casenotes.htm#|6"--%>
<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5" /></td>
		</tr>
		<tr>
			<td valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
						<td valign="top">
							<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tabid" value="conplianceTab"/>
							</tiles:insert>
						</td>
					</tr>
					<tr>
						<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5" /></td>
					</tr>
				</table>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					<tr>
						<td><img src="/<msp:webapp/>images/spacer.gif" height="5" /></td>
					</tr>
					<tr>
						<td valign="top" align="center">
<!-- BEGIN SUPERVISEE INFORMATION TABLE  -->
							<tiles:insert page="../../common/superviseeInfoForComplianceHeader.jsp" flush="true"></tiles:insert>	
<!-- END SUPERVISEE INFORMATION TABLE  -->	
						</td>
					</tr>
<!-- BEGIN GREEN TABS TABLE -->		
					<tr>
						<td valign="top" align="center"> 
							<table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
								</tr>						
								<tr>
									<td valign="top">
										<tiles:insert page="../../common/caseloadCSCDSubTabs.jsp" flush="true">
						   				 	<tiles:put name="tab" value="ComplianceTab"/> 
							     		</tiles:insert>					
									</td>
								</tr>
								<tr>
									<td  bgcolor="#33cc66"><img src="/<msp:webapp/>js/images/spacer.gif" height="5"></td> 
								</tr>
							</table>
						</td>
					</tr>		
<!-- END GREEN TABS TABLE -->
					<tr>
						<td valign="top" align="center">
<!-- BEGIN GREEN BORDER TABLE -->					
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header">
												<bean:message key="title.CSCD" />&nbsp;-&nbsp;<bean:message key="prompt.compliance" />&nbsp;-
												<bean:message key="prompt.setConditionsToNoncompliant" />&nbsp;-&nbsp;<bean:message key="prompt.casenote" />
											</td>
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
<!-- BEGIN CONFIRMATION TABLE -->
							       	<logic:notEqual name="complianceForm" property="confirmMessage" value="">  	  	
										<table width="98%" align="center">							
											<tr>
									       		<td align="center" class="confirm"><bean:write name="complianceForm" property="confirmMessage" /></td>
											</tr>		
										</table>
								    </logic:notEqual>  
<!-- BEGIN CONFIRMATION TABLE --> 
<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
													<li>Enter required fields, click Next</li>
												</ul>
											</td>
										</tr>
										<tr>
											<td class="required"><bean:message key="prompt.3.diamond" />Required Fields&nbsp; *All date fields must be in the format of mm/dd/yyyy.</td>
										</tr>
									</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN COMPLIANCE EVENTS TABLES -->
									<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
										<tr class="detailHead">
											<td class="paddedFourPix">
												<table width="100%" cellpadding="0" cellspacing="0">
													<tr>
														<td width="1%"><a href="javascript:showHide('events', 'row','/<msp:webapp/>')"><img border="0" src="/<msp:webapp/>images/expand.gif" name="events"></a>&nbsp;</td>
														<td class="detailHead"><bean:message key="prompt.noncomplianceEvents" /></td>
														<td align="right"><img src="/<msp:webapp/>images/step_1.gif"></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr id="eventsSpan" class="hidden">
											<td>
												<logic:empty name="complianceForm" property="selectedLikeConditionsEvents">
													<logic:empty name="complianceForm" property="selectedUniqueConditionsEvents">
														<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
															<tr class="detailHead">
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td >No condition events for this person.</td>
															</tr>
														</table>	
													</logic:empty>	
												</logic:empty>
			    	        	                <logic:iterate id="likeEventIter" name="complianceForm" property="selectedLikeConditionsEvents">
													<table width="100%" border="0" cellspacing="1" cellpadding="4">
														<tr class="formDeLabel">
															<td colspan="4">
																<table width="100%" cellpadding="0" cellspacing="0">
																	<tr>
																		<td><a href="javascript:openWindow('/<msp:webapp/>handleComplianceConditions.do?submitAction=Link&sprOrderConditionId=<bean:write name='likeEventIter' property='sprOrderConditionId'/>')" title=""><bean:write name='likeEventIter' property='orderConditionName'/></a></td>
																		<td align="right"><bean:message key="prompt.case#" />:<span class="boldText">
																			<logic:iterate id="caseNumbers" name="likeEventIter" property="createNonCompliantEventEvent" >
																				<bean:write name="caseNumbers" property="displayCaseNum" />
																			</logic:iterate>	
																			</span>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr>
															<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.occurrenceDate"/></td>
															<td class="formDe"><bean:write name="likeEventIter" property="occurrenceDate" formatKey="date.format.MMddyyyy" /></td>
															<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.occurrenceTime"/></td>
															<td class="formDe">
																<bean:write name="likeEventIter" property="occurrenceTime" formatKey="time.format.HHmm" />
																<logic:notEmpty name="likeEventIter" property="occurrenceTime"><bean:write name="likeEventIter" property="AMPMId" /></logic:notEmpty>
															</td>
														</tr>
														<tr>
															<td class="formDeLabel" width="1%" nowrap="nowrap" valign="top"><bean:message key="prompt.eventType"/></td>
															<td class="formDe" colspan="3">
																<bean:write name="likeEventIter" property="eventTypes"/>
																<bean:write name="likeEventIter" property="newEventType"/>																
															</td>
														</tr>
														<tr>
															<td class="formDeLabel" width="1%" nowrap="nowrap" valign="top"><bean:message key="prompt.details"/></td>
															<td class="formDe" colspan="3"><bean:write name="likeEventIter" property="details"/></td>
														</tr>
														<tr>
															<td><img src="/<msp:webapp/>images/spacer.gif"></td>
														</tr>
													</table>	
	                                            </logic:iterate>
			    	        	                <logic:iterate id="uEventIter" name="complianceForm" property="selectedUniqueConditionsEvents">
													<table width="100%" border="0" cellspacing="1" cellpadding="4">
														<tr class="formDeLabel">
															<td colspan="4">
																<table width="100%" cellpadding="0" cellspacing="0">
																	<tr>
																		<td><a href="javascript:openWindow('/<msp:webapp/>handleComplianceConditions.do?submitAction=Link&sprOrderConditionId=<bean:write name='uEventIter' property='sprOrderConditionId'/>')" title=""><bean:write name='uEventIter' property='orderConditionName'/></a></td>
																		<td align="right"><bean:message key="prompt.case#" />:<span class="boldText"><bean:write name="uEventIter" property="displayCaseNum" /></span></td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr>
															<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.occurrenceDate"/></td>
															<td class="formDe"><bean:write name="uEventIter" property="occurrenceDate" formatKey="date.format.MMddyyyy" /></td>
															<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.occurrenceTime"/></td>
															<td class="formDe">
																<bean:write name="uEventIter" property="occurrenceTime" formatKey="time.format.HHmm" />
																<logic:notEmpty name="uEventIter" property="occurrenceTime"><bean:write name="uEventIter" property="AMPMId" /></logic:notEmpty>
															</td>
														</tr>
														<tr>
															<td class="formDeLabel" width="1%" nowrap="nowrap" valign="top"><bean:message key="prompt.eventType"/></td>
															<td class="formDe" colspan="3">
																<bean:write name="uEventIter" property="eventTypes"/>
																<bean:write name="uEventIter" property="newEventType"/>
															</td>
														</tr>
														<tr>
															<td class="formDeLabel" width="1%" nowrap="nowrap" valign="top"><bean:message key="prompt.details"/></td>
															<td class="formDe" colspan="3"><bean:write name="uEventIter" property="details"/></td>
														</tr>
														<tr>
															<td><img src="/<msp:webapp/>images/spacer.gif"></td>
														</tr>
													</table>	
	                                            </logic:iterate>

											</td>
										</tr>
									</table>
<!-- END CONCOMPLIANCE EVENTS TABLES -->		
									<br>															
<%-- COMMON SCRIPT USED FOR CALENDAR POPUP --%>								
						<SCRIPT type="text/javascript" ID="js1">
							var cal1 = new CalendarPopup();
							cal1.showYearNavigation();
						</SCRIPT>	
<!-- BEGIN DETAIL TABLE -->
<!-- BEGIN SEARCH TABLES -->	
									<tiles:insert page="../../administerCaseloadCSCD/compliance/complianceCasenoteSearchTile.jsp" flush="true">
				    	 				<tiles:put name="returnPage" value="NonCompliant"/>
				    	 			</tiles:insert>					
<!-- END SEARCH TABLES -->	
									<br>
<!-- BEGIN ADD CASENOTE INFORMATION TABLE -->
									<tiles:insert page="../../administerCaseloadCSCD/compliance/complianceCasenoteTile.jsp" flush="true">
						     		</tiles:insert>	
<!-- END ADD CASENOTE INFORMATION TABLE -->						     			
									<br>
<!-- BEGIN CASENOTES DISPLAY TABLE  -->	
								<logic:empty name="complianceForm" property="currentCasenotes">	
									<table width="100%" border="0" cellpadding="2" cellspacing="1" align="center">
										<tr class="detailHead">
											<td align="center">No existing casenotes found to display</td>
										</tr>
									</table>
								</logic:empty>	
<!-- BEGIN PAGINATION HEADER TAG -->
								<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
								<pg:pager
								    index="center"
									maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
								    maxIndexPages="10"
								    export="offset,currentPageNumber=pageNumber"
								    scope="request">
								    <input type="hidden" name="pager.offset" value="<%= offset %>">
<!-- END PAGINATION HEADER TAG -->														
								<logic:iterate id="casenoteIter" name="complianceForm" property="currentCasenotes">
 									<pg:item>
									<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center">
										<tr class="detailHead">
											<td><bean:message key="prompt.date" /></td>
											<td><bean:message key="prompt.subject" /></td>
											<td><bean:message key="prompt.type" /></td>
											<td><bean:message key="prompt.contactMethod" /></td>
											<td><bean:message key="prompt.createdBy" /></td>
											<td><bean:message key="prompt.createDate" /></td>
										</tr>
										<tr class="formDe">
											<td><bean:write name="casenoteIter" property="casenoteDate" formatKey="date.format.mmddyyyy"/></td>
											<td><bean:write name="casenoteIter" property="subjects" /></td>
											<td><bean:write name="casenoteIter" property="casenoteType" /></td>
											<td><bean:write name="casenoteIter" property="contactMethod" /></td>
											<td><bean:write name="casenoteIter" property="createdByName" /></td>
											<td><bean:write name="casenoteIter" property="createDate" formatKey="date.format.mmddyyyy"/></td>
										</tr>
										<tr 
											<logic:equal name="casenoteIter" property="casenoteStatusId" value="D">
												class="draftCasenote"
											</logic:equal>
											>
											<td class="borderTableBlue" colspan="6">
												<bean:write name="casenoteIter" property="casenoteText" filter="false" />
											</td>
										</tr>
										<tr>
											<td colspan="6"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
										</tr>
									</table>	
 									</pg:item>
								</logic:iterate> 
<!-- BEGIN PAGINATION NAVIGATOIN ROW -->
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
								</pg:pager>	
<!-- END PAGINATION NAVIGATOIN ROW -->	
								
<!-- BEGIN CASENOTE DISPLAYS BUTTON TABLE -->
								<table width="100%" border="0" cellpadding="2" cellspacing="1">
									<tr>
										<td align="center">
										</td> 
									</tr>
								</table>
<!-- END CASENOTES DISPLAY BUTTON TABLE  -->
								</td>
							</tr>		
						</table>						
<!-- END GREEN BORDER TABLE -->
					</td>
				</tr>
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table> 
			</td> 
		</tr>
	</table>
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>