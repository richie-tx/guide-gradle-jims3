<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/15/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 08/29/2013 RYoung - #75955 - PROD Error Logs: ErrorMessage_IncorrectPathToAnchorPositionJSFile -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@page import="naming.CaseloadConstants"%>
<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


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
<title><bean:message key="title.heading" /> - reports/assessmentsResults.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>common/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>common/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>common/sorttable.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" > 
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
	</tr>
	<tr>
		<td valign="top">
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--tabs start-->
						<tiles:insert page="/<msp:webapp/>common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="setupTab" />
						</tiles:insert> 
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
			</table>
			<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign="top" align=center>
						<table width=98% border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
						<!--tabs start-->
							<tiles:insert page="/<msp:webapp/>common/manageFeaturesTabs.jsp" flush="true">
								<tiles:put name="tabid" value="reportsTab"/>
							</tiles:insert>	
						<!--tabs end-->
								</td>
							</tr>
						</table>
						<table width=98% border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
							</tr>
							<tr>
								<td valign="top" align=center>
									<!-- BEGIN HEADING TABLE -->
									<table width=100%>
										<tr>
											<td align="center" class="header"><bean:message key="title.cscd"/>&nbsp;-
																			  <bean:message key="prompt.assessments"/>
                                                                              <bean:message key="title.search"/>
                                                                              <bean:message key="prompt.results"/></td>
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
									 <table width=98% border="0" cellpadding="0" cellspacing="0"> 
									   <tr> 
										 <td bgcolor=#cccccc colspan=2> <!--pu header start--> 
										<span id=pu  class=hidden> 
										   <table width=100% border=0 cellpadding=2 cellspacing=1> 
											 <tr> 
											   <td class=headerLabel><bean:message key="prompt.programUnit"/></td> 
											   <td class=headerData><bean:write name="assessmentForm" property="programUnit" /></td> 
											   <td class=headerLabel width=1% nowrap><bean:message key="prompt.assessmentDateRange"/></td> 
											   <td class=headerData><bean:write name="assessmentForm" property="beginAssessmentDate"  formatKey="date.format.mmddyyyy" /> 
												 -
												<bean:write name="assessmentForm" property="endAssessmentDate"  formatKey="date.format.mmddyyyy" /> </td> 
											 </tr> 
										   </table> 
									    </span> 
									<!--pu header end--> 
									<!--supervisee header start--> 
										<span id=supervisee class=hidden> 
										   <table width=100% border=0 cellpadding=2 cellspacing=1> 
											  <tr> 
												<td class=headerLabel nowrap><bean:message key="prompt.SPN"/></td> 
												<td class=headerData><bean:write name="assessmentForm" property="SPN" /></td> 
												<td class=headerLabel nowrap><bean:message key="prompt.superviseeName"/></td> 
												<td class=headerData><bean:write name="assessmentForm" property="superviseeName" /></td> 
											  </tr> 
											  <tr> 
												<td class=headerLabel nowrap><bean:message key="prompt.assessmentDateRange"/></td> 
												<td class=headerData colspan=3> <bean:write name="assessmentForm" property="endAssessmentDate"  formatKey="date.format.mmddyyyy" /> 
												  -
												  <bean:write name="assessmentForm" property="endAssessmentDate"  formatKey="date.format.mmddyyyy" /> </td> 
											  </tr> 
											</table> 
										</span> 
											<!--pu header end--> </td> 
									</tr> 
									<tr> 
									  <td><img src="/<msp:webapp/>images/spacer.gif" height=10></td> 
									</tr> 
								  </table> 
								  <script>
									  if (location.search=="?supervisee"){
										  show('supervisee', 1)
									  }
									  if (location.search=="?programUnit"){
										  show('pu', 1)
									  }
								  </script> 
									<logic:notEmpty name="assessmentSearchForm" property="assessmentSearchResults">	
										<table>
											<tr>
												<td align="center">
													<bean:size id="superviseeSearchResultsSize" name="assessmentSearchForm" property="superviseeSearchResults"/>
													Supervisee Count&nbsp;<b><bean:write name="superviseeSearchResultsSize"/></b></td>            					
											</tr>
										</table>  
										<table width="98%" cellpadding="2" cellspacing="0" class=borderTableBlue> 
											<tr class="detailHead"> 
												<td><bean:size id="assessmentSearchResultsSize" name="assessmentSearchForm" property="assessmentSearchResults"/>
													Assessments - Total Count:&nbsp;<b><bean:write name="assessmentSearchResultsSize"/></b></td> 
												<td align=right></td> 
											</tr> 
											<tr> 
											  <td colspan=2> 
                                                  <table width="100%" cellpadding="2" cellspacing="1" class=sortable id=uniqueID> 
													  <tr class=formDeLabel> 
														<td><bean:message key="prompt.superviseeName"/></td> 
														<td><bean:message key="prompt.SPN"/></td> 
														<td><bean:message key="prompt.officer"/></td> 
														<td><bean:message key="prompt.assessmentType"/></td> 
														<td title='Assessment Date'><bean:message key="prompt.date"/></td> 
														<td title="Due Date"><bean:message key="prompt.dueDate"/></td> 
														<td><bean:message key="prompt.scores"/></td> 
													  </tr> 
													<%int RecordCounter = 0;
													String bgcolor = "";%>  
												
													<logic:iterate id="assessmentSearchResultsIndex" name="assessmentSearchForm" property="assessmentSearchResults">
													<logic:equal name="RecordCounter" value="0">	
													  <input name="assessmentId" value='<bean:write name="assessmentSearchResultsIndex" property="assessmentId"/>'/>
													</logic:equal>
													  <tr
														class=<%RecordCounter++;
															bgcolor = "alternateRow";
															if (RecordCounter % 2 == 1)
																bgcolor = "normalRow";
															out.print(bgcolor);%>>
														  <td><a href=/<msp:webapp/>caseloadCSCD/profile/casefileProfileTab.htm><bean:write name="assessmentSearchResultsIndex" property="superviseeName"/></a></td> 
														  <td><bean:write name="SPN"/></td> 
														  <td><bean:write name="officerName"/>&nbsp;|&nbsp;<bean:write name="assessmentSearchResultsIndex" property="CSOpositionName"/>&nbsp;Position Name</td> 
														  <td><a href=/<msp:webapp/>caseloadCSCD/assessments/wisconsinSummary.htm?reassessView><bean:write property="assessmentName"/></a></td> 
														  <td><bean:write name="assessmentSearchResultsIndex" property="assessmentDate"/></td> 
														  <td><bean:write name="assessmentSearchResultsIndex" property="assessmentDueDate"/></td> 
														  <td><bean:write name="assessmentSearchResultsIndex" property="riskScore"/>&nbsp;<bean:write name="assessmentSearchResultsIndex" property="needsScore"/></td> 
													  </tr> 
												  </table>
										 </td> 
									 </tr> 
									</table>
						         </logic:notEmpty>
									<br>
									<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<input type="button" value="Back" name="return" onClick="history.go(-1)">
												<input type="button" value="Print" onClick="window.print()">
												<input type="button" value="Cancel" name="return" onClick="history.go(-1)">
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
		</td>
	</tr>
</table>

	<!-- END  TABLE -->
</div>
<br>
<!-- BEGIN NOTES TABLE -->
<%--table width="98%">
	<tr>
		<td class="subhead">Notes:</td>
	</tr>
	<tr>
		<td>
			<ol>
				<li>header area varies depending on type of search</li>
			</ol>
		</td>
	</tr>
</table--%>
<!-- END NOTES TABLE -->
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
