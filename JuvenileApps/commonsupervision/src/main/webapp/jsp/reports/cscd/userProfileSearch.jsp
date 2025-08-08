<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

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
<title></title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script>
	function diableNextButton(){
		var nextButton = document.getElementById("Next");
		nextButton.disabled = true;
	}
	
	function enableNextButton(){
		var nextButton = document.getElementById("Next");
		nextButton.disabled = false;
	}
</script>
</head>
<body topmargin="0" leftmargin="0" onload="diableNextButton();" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/userProfileSearchAction" target="content">
	
	<div align="center">
		<table width="98%" border="0" cellpadding="0" cellspacing="0" id="mainTable">
			<tr>
				<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
			</tr>
			<tr>
				<td valign="top">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" id="commonSupervisionTabsTable">
						<tr>
							<td valign="top">
								<!--tabs start-->
								<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
									<tiles:put name="tabid" value="setupTab"/>
								</tiles:insert>
								<!--tabs end-->
							</td>
						</tr>
						<tr>
							<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
						</tr>
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue" id="blueBorderTable">
						<tr>
							<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
						</tr>
						<tr>
							<td valign="top" align="center">
								<table width="98%" border="0" cellpadding="0" cellspacing="0" id="managerFeaturesTabTable">
									<tr>
										<td valign="top">
											<!--tabs start-->
                                            <tiles:insert page="../../common/manageFeaturesTabs.jsp" flush="true">
												<tiles:put name="tabid" value="reportsTab"/>
											</tiles:insert>						
										</td>
									</tr>									
								</table>
								<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen" id="greenBorderTable">
									<tr>
										<td><img src="/<msp:webapp/>common/images/spacer.gif" height="5"></td>
									</tr>
									<tr>
										<td valign="top" align="center">
											<!-- BEGIN HEADING TABLE -->
											<table width="100%" id="headingTable">
												<tr>
													<td align="center" class="header">
														<bean:message key="title.CSCD" /> - 
                                                        <bean:message key="title.assignment" />&nbsp;
                                                        Name 
                                                        <bean:message key="title.search" /> 
                                                        Results
                                                   </td>
												</tr>
											</table>
											<!-- END HEADING TABLE -->
											<!-- BEGIN ERROR TABLE -->
											<table width="98%" align="center" id="errorTable">							
												<tr>
													<td align="center" class="errorAlert"><html:errors></html:errors></td>
												</tr>		
											</table>
											<!-- END ERROR TABLE -->

											<!-- BEGIN INSTRUCTION TABLE -->
											<table width="98%" border="0" id="generalInstructionsTable">
												<tr>
													<td>
														<ul>
															<li>Select the user. Click Next.</li>
														</ul>
													</td>
												</tr>
											</table>
											<!-- END INSTRUCTION TABLE -->

											<!-- Begin Pagination Header Tag-->
											<bean:define id="paginationResultsPerPage" type="java.lang.String">
												<bean:message key="pagination.recordsPerPage"></bean:message>
											</bean:define> 
											<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" 
												maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
												
												<input type="hidden" name="pager.offset" value="<%= offset %>">
											<!-- End Pagination header stuff -->

												<table width="98%" cellpadding="2" cellspacing="1" border="0" class="borderTableBlue" id="searchResultsTable">
													<tr class="detailHead">
														<td width="1%"></td>
														<td><bean:message key="prompt.name" /></td>
														<td><bean:message key="prompt.userId" /></td>															
														<td><bean:message key="prompt.poi" /></td>
														<td><bean:message key="prompt.positionName" /></td>
														<td><bean:message key="prompt.division" /> / <bean:message key="prompt.programUnit" /></td>
													</tr>
													<logic:notEmpty name="caseAssignmentReportForm" property="userReport.officersWithMatchingName">
									               <logic:iterate id="userProfileIndex" name="caseAssignmentReportForm" property="userReport.officersWithMatchingName" indexId="index">
									               <pg:item>
													<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
														<td>
															<input type="radio" name="userReport.userId" value=<bean:write name="userProfileIndex" property="userId" /> onclick="enableNextButton();"/>													
														</td>
														<td><bean:write name="userProfileIndex" property="officerName" /></td>
														<td><bean:write name="userProfileIndex" property="userId" /></td>
														<td><bean:write name="userProfileIndex" property="poi" /></td>
														<td><bean:write name="userProfileIndex" property="positionName" /></td>
														<td><bean:write name="userProfileIndex" property="divisionName" />/
															<bean:write name="userProfileIndex" property="programUnitName" />
														</td>
													</tr>
													</pg:item>
	                                               </logic:iterate>
	                                               </logic:notEmpty>
												</table>	
												<br/>										
												
												<!-- Begin Pagination navigation Row-->
												<table align="center">
													<tr><td>
														<pg:index>
															<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
																<tiles:put name="pagerUniqueName" value="pagerSearch"/>
																<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
															</tiles:insert>
														</pg:index>
													</td></tr>
												</table>
												<!-- End Pagination navigation Row-->														
											</pg:pager>								
											</td>
										</tr>		
										<tr >
										<table border="0" width="100%" >		
											<tr>						
												<td align="center">
													<html:submit property="submitAction">
														<bean:message key="button.back"></bean:message>
													</html:submit>
												 	<html:submit property="submitAction" styleId="Next" onclick="return disableSubmit(this, this.form);">
														<bean:message key="button.next"></bean:message>
													</html:submit>
													<html:submit property="submitAction" >
														<bean:message key="button.cancel"></bean:message>
													</html:submit>  																																		 			
												</td>
											</tr>
										</table>
										</tr>
										
									</table>
									<br/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<!-- END  TABLE -->
		</div>
		<br>
	</html:form>	
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
