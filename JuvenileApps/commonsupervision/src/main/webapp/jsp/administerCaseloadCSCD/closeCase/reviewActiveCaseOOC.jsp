<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- 06/27/2008	LDeen		Copy over from administerCaseload    --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
	<msp:nocache />
	<%-- Checks to make sure if the user is logged in. --%>
	<%--msp:login / --%>
	<meta http-equiv="Content-Language" content="en-us">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META name="GENERATOR" content="IBM WebSphere Studio">
	<META http-equiv="Content-Style-Type" content="text/css">
	
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	<!-- STYLE SHEET LINK -->
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
	<html:base />
	<title><bean:message key="title.heading" /> - administerCaseloadCSCD/closeCase/reviewActiveCaseOOC.jsp</title>
	<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
</head>
	
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
	<html:form action="/displayOutOfCountyCaseClose" target="content">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Close_Supervision/OOC_Case/Close_Supervision_OOC_Case.htm#|1">
			
			<div align="center">
				<!-- BEGIN BASE TABLE -->
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
										<!--blue tabs start-->
									<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
										<tiles:put name="tab" value="setupTab"/>
									</tiles:insert>		
									<!--blue tabs end-->
									</td>
								</tr>
								<tr>
									<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
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
										<!-- BEGIN GREEN TABS TABLE -->
                                         <table width="98%" border="0" cellpadding="0" cellspacing="0" >
											<tr>
												<td valign="top">
													<!--tabs start-->
													<tiles:insert page="../../common/manageFeaturesTabs.jsp" flush="true">
														<tiles:put name="tab" value="oocTab" />
													</tiles:insert>
													<!--tabs end-->
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
															<bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.outOfCountyCase"/>&nbsp;-&nbsp;Review Active Cases</td>
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
																	<li>Click Paper File Received to acknowledge the paper file.</li>
																</ul>
															</td>
														</tr>
													</table>
													<!-- END INSTRUCTION TABLE -->
			
													<!-- BEGIN HEADER TABLE -->
														<tiles:insert page="../../outOfCountyCase/partyInfoHeaderTile.jsp" flush="true">
															<tiles:put name="partyHeader" beanName="outOfCountyCaseForm"/>
														</tiles:insert>	
													<!-- END HEADER TABLE -->
													
													<br>
													<!-- BEGIN DETAIL TABLE -->
													<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
														<tr class="detailHead">
															<td>
																You are about to acknowledge possession of the following case
															</td>
														</tr>
														<tr>
															<td>
																<table width="100%" border="0" cellpadding="2" cellspacing="1">
																	<tr class="formDeLabel">
																		<td><bean:message key="prompt.case" /></td>
																		<td><bean:message key="prompt.court" /></td>
																		<td><bean:message key="prompt.programUnit" /></td>
																		<td><bean:message key="prompt.officer" /></td>
																	</tr>
																	<% int RecordCounter=0;
																	   String bgcolor="";
																	%>
																	<logic:iterate id="activeCase" name="outOfCountyCaseForm" property="closeCaseAssignmentList">
																		<tr class= <% RecordCounter++; 
																		  bgcolor = "alternateRow";                      
																		  if (RecordCounter % 2 == 1)
																			  bgcolor = "normalRow";
																		   out.print(bgcolor); %>>
																			<td><bean:write name="activeCase" property="criminalCaseId"/></td>
																			<td><bean:write name="activeCase" property="courtId"/></td>
																			<td><bean:write name="activeCase" property="programUnitName" /></td>
																			<logic:notEmpty name="activeCase" property="officerName">
																				<td><bean:write name="activeCase" property="officerName.formattedName"/></td>
																			</logic:notEmpty>
																		</tr>
																	</logic:iterate>
																</table>
															</td>
														</tr>
													</table>
													<!-- END DETAIL TABLE -->
													<br>
													<!-- BEGIN BUTTON TABLE -->
													<table border="0" width="100%">
														<tr>
															<td align="center">
																<html:submit property="submitAction">
																	<bean:message key="button.back" />
																</html:submit> 
																<html:submit property="submitAction">
																	<bean:message key="button.paperFileReceived" />
																</html:submit> 
																<html:submit property="submitAction">
																	<bean:message key="button.cancel" />
																</html:submit> 
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
				<!-- END BASE TABLE -->
		    </div>		
		</html:form>
	</body>
</html:html>
