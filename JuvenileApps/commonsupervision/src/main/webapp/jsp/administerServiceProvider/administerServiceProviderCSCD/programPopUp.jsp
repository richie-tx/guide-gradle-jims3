<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Used for Service Provider Creation (CSCD)-->
<!--MODIFICATIONS -->
<!-- DWilliamson 12/10/2007	Create JSP -->
 
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDCodeTableConstants" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>

<!--BEGIN HEADER TAG-->
<msp:nocache />
<html:html>
<head>
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
<title><bean:message key="title.heading"/> - programPopUp.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
</head>
<body>
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<!-- BEGIN HEADING TABLE -->
						<table width="100%">
							<tr>
								<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="title.serviceProvider"/>&nbsp;-
                                                                  <bean:message key="prompt.program"/>&nbsp;<bean:message key="title.details"/></td>
											</tr>
										</table>
										<!-- END HEADING TABLE -->
										<!-- BEGIN ERRORS TABLE -->
										<table width="100%">
											<tr>		  
												<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
											</tr>   	  
										</table>
										<!-- END ERRORS TABLE -->
										<tiles:insert page="../../common/serviceProviderHeader.jsp" flush="true">
										</tiles:insert>
										<div class="spacer4px"></div>
										<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
											<tr>
												<td class="detailHead"><bean:message key="prompt.program"/>&nbsp;<bean:message key="prompt.info"/></td>
											</tr>
											<tr>
												<td>
													<table width="100%" cellpadding="4" cellspacing="1">
														<logic:equal name="cscServiceProviderProgramForm" property="currentStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_ACTIVE%>">
															<tr id="statusRow0">
																<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.status"/></td>
																<td class="formDe">
																	<bean:write name="cscServiceProviderProgramForm" property="currentStatusDesc"/>																							
																</td>
																<td class="formDeLabel" width="1%">
																	<bean:message key="prompt.active"/>&nbsp;<bean:message key="prompt.date"/>
															   </td>
																<td class="formDe">
																	<bean:write name="cscServiceProviderProgramForm" property="statusDateAsStr" formatKey="date.format.mmddyyyy" />
																</td>
															</tr>
															<tr id="statusRow1">
																<td class="formDeLabel" colspan="4" nowrap>
																	<bean:message key="prompt.active"/>&nbsp;<bean:message key="prompt.reason"/></td>
															</tr>
															<tr id="statusRow2">
																<td class="formDe"  colspan="4">
																	<logic:equal name="cscServiceProviderProgramForm" property="statusReason" value="">
																       NEW PROGRAM CREATED
															        </logic:equal>
															        <logic:notEqual name="cscServiceProviderProgramForm" property="statusReason" value="">
																       <bean:write name="cscServiceProviderProgramForm" property="statusReason" />
															       </logic:notEqual>
																</td>
															</tr>
														</logic:equal>
														<logic:equal name="cscServiceProviderProgramForm" property="currentStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_UNDERINVESTIGATION%>">
															<tr id="statusRow0">
																<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.status"/></td>
																<td class="formDe">
																	<bean:write name="cscServiceProviderProgramForm" property="currentStatusDesc"/>																							
																</td>
																<td class="formDeLabel" width="1%">
																	<bean:message key="prompt.investigation"/>&nbsp;<bean:message key="prompt.date"/>
															   </td>
																<td class="formDe">
																	<bean:write name="cscServiceProviderProgramForm" property="statusDateAsStr" formatKey="date.format.mmddyyyy" />
																</td>
															</tr>
															<tr id="statusRow1">
																<td class="formDeLabel" colspan="4" nowrap><span class="diamond"></span>
																	<bean:message key="prompt.investigation"/>&nbsp;<bean:message key="prompt.reason"/></td>
															</tr>
															<tr id="statusRow2">
																<td class="formDe"  colspan="4">
																	<logic:equal name="cscServiceProviderProgramForm" property="statusReason" value="">
																       NEW PROGRAM CREATED
															        </logic:equal>
															        <logic:notEqual name="cscServiceProviderProgramForm" property="statusReason" value="">
																       <bean:write name="cscServiceProviderProgramForm" property="statusReason" />
															       </logic:notEqual>
																</td>
															</tr>
														</logic:equal>
														<tr>
															<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.identifier"/></td>
															<td class="formDe"><bean:write name="cscServiceProviderProgramForm" property="identifier"/></td>
															<td class="formDeLabel" nowrap><bean:message key="prompt.contractProgram"/></td>
															<td class="formDe"><bean:write name="cscServiceProviderProgramForm" property="contractProgramAsStr"/></td>
														</tr>
														<tr>
															<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.name"/></td>
															<td colspan="3" class="formDe"><bean:write name="cscServiceProviderProgramForm" property="name"/></td>
														</tr>
														<tr>
															<td class="formDeLabel"><bean:message key="prompt.programGroup"/></td>
															<td colspan="3" class="formDe"><bean:write name="cscServiceProviderProgramForm" property="programGroupDesc"/></td>
														</tr>
														<tr>
															<td class="formDeLabel"><bean:message key="prompt.type"/></td>
															<td colspan="3" class="formDe"><bean:write name="cscServiceProviderProgramForm" property="programTypeDesc"/></td>
														</tr>
														<tr>
															<td class="formDeLabel"><bean:message key="prompt.subtype"/></td>
															<td colspan="3" class="formDe"><bean:write name="cscServiceProviderProgramForm" property="programSubTypeDesc"/></td>
														</tr>
                                                        <tr>
															<td class="formDeLabel" nowrap><bean:message key="prompt.programStartDate"/></td>
															<td class="formDe"><bean:write name="cscServiceProviderProgramForm" property="programStartDateAsStr" formatKey="date.format.mmddyyyy"/></td>
															<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.programEndDate"/></td>
															<td class="formDe"><bean:write name="cscServiceProviderProgramForm" property="programEndDateAsStr" formatKey="date.format.mmddyyyy"/></td>
														</tr>
														<tr>
															<td class="formDeLabel" nowrap><bean:message key="prompt.sexSpecific"/></td>
															<td class="formDe" colspan="3"><bean:write name="cscServiceProviderProgramForm" property="sexSpecificDesc"/></td>
														</tr>
														<tr>
															<td class="formDeLabel" nowrap><bean:message key="prompt.languagesOffered"/></td>
															<td class="formDe" colspan="3"><bean:write name="cscServiceProviderProgramForm" property="languagesOfferedDesc"/></td>
														</tr>
														<tr>
															<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.location"/>(s)</td>
															<td class="formDe" colspan="3"><bean:write name="cscServiceProviderProgramForm" property="selectedLocationDescs"/></td>
														</tr>
														<tr>
															<td class="formDeLabel"><bean:message key="prompt.officeHours"/></td>
															<td colspan="3" class="formDe"><bean:write name="cscServiceProviderProgramForm" property="officeHours"/></td>
														</tr>
														<tr>
															<td colspan="4" class="formDeLabel"><bean:message key="prompt.description"/></td>
														</tr>
														<tr>
															<td colspan="4" class="formDe"><bean:write name="cscServiceProviderProgramForm" property="description"/></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
										<div class="spacer4px"></div>
										<!-- BEGIN BUTTON TABLE -->
										<table border="0">
											
											<tr>
												<td>
													<input type="button" value="Close" onclick="window.close();" />
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
	<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
