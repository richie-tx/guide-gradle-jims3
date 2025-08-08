<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 10/04/2007	 Debbie Williamson - Create JSP -->
<!-- 07/07/2008  C Shimek		   - defect#51135 revised supervisee header to correct inserted jsp  -->
<!-- 09/21/2009	 Clarence Shimek   - #61740 revised Case# to use displayCaseNum for case number dispaly -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/compliance/conditionsSetCompliantSummary.jsp</title>

<!-- JavaScripts -->
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitResolveNonCompliance" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Administer_Compliance/CSCD_Compliance.htm#|10">
<%-- input type="hidden" name="helpFile" value="commonsupervision/Administer_Compliance/Common_Sup_Compliance_and_Casenotes.htm#|10"--%>
<div align="center">
<!--  BEGIN CONTENT TABLE -->  
	<table width="98%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
<!--BEGIN BLUE TABS TABLE  -->        
				<table width="100%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
						<td valign="top">
					<!--tabs start-->
							<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tabid" value="conplianceTab"/>
							</tiles:insert>
					<!--tabs end-->
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
<!-- BEGIN SUPERVISEE INFORMATION TABLE  -->
							<tiles:insert page="../../common/superviseeInfoForComplianceHeader.jsp" flush="true"></tiles:insert>	
<!-- END SUPERVISEE INFORMATION TABLE  -->	               				    </td>
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
				                        			<bean:message key="prompt.resolveNoncompliance" />&nbsp;-&nbsp;<bean:message key="title.summary" />
				                     			</td>
				                  			</tr>
				                		</table>
<!-- END HEADING TABLE -->				                		
				                	</td>
				                </tr>
								<tr>
									<td valign="top" align="center"> 
<!-- BEGIN ERROR TABLE -->
										<table width="98%" align="center">							
											<tr>
												<td align="center" class="errorAlert"><html:errors></html:errors></td>
											</tr>		
										</table>
<!-- END ERROR TABLE -->
				                	</td>
				                </tr>
								<tr>
									<td valign="top" align="center"> 
<!-- BEGIN INSTRUCTION TABLE -->
				               			<table width="98%" border="0">
				               				<tr>
				                   				<td>
				                   					<ul>
							                    	    <li>Review entries. Click Finish.</li>
				                					</ul>
				   	                			</td>
				       	          			</tr>
				           	    		</table>
<!-- END INSTRUCTION TABLE -->
				                	</td>
				                </tr>
								<tr>
									<td valign="top" align="center"> 
<!-- BEGIN SELECTED CONDITIONS - COMPLIANCE REASON TABLE -->
										<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
											<tr class="detailHead">
												<td class="paddedFourPix">
													<table width="100%" cellpadding="0" cellspacing="0">
														<tr>
															<td width="1%"><a href="javascript:showHide('complianceReason','row','/<msp:webapp/>')"><img border="0" src="/<msp:webapp/>images/expand.gif" name="complianceReason"></a>&nbsp;</td>
															<td class="detailHead"><bean:message key="prompt.selectedConditions" />&nbsp;-&nbsp;<bean:message key="prompt.complianceReason" /></td>
															<td align="right"><img src="/<msp:webapp/>images/step_1.gif"></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr id="complianceReasonSpan" class="hidden">
												<td>
								 					<table width="100%" cellpadding="2" cellspacing="1">
														<tr class="formDeLabel">
															<td><bean:message key="prompt.condition" /></td>
															<td title="Court" align="center"><bean:message key="prompt.CRT" /></td>
															<td><bean:message key="prompt.case#" /></td>
															<td><bean:message key="prompt.complianceReason" /></td>
															<td title="Non-Compliance Count" align="center"><bean:message key="prompt.NCCount" /></td>
														</tr>
						                               	<logic:notEmpty name="complianceForm" property="selectedConditions">
						                                   	<logic:iterate id="conditionIndex" name="complianceForm" property="selectedConditions">
																<tr>
																	<td class="boldText">
																		<a href="javascript:openWindow('/<msp:webapp/>handleComplianceConditions.do?submitAction=Link&sprOrderConditionId=<bean:write name='conditionIndex' property='sprOrderConditionId'/>')" title=""><bean:write name='conditionIndex' property='orderConditionName'/></a>
																	</td>
																	<td align="center"><bean:write name="conditionIndex" property="courtId"/></td>
																	<td><bean:write name="conditionIndex" property="displayCaseNum"/></td>
																	<td><bean:write name="conditionIndex" property="complianceReasonDescription"/></td>
																	<td align="center"><bean:write name="conditionIndex" property="ncCount"/></td>
																</tr>
						                       	            </logic:iterate>
						                           	    </logic:notEmpty>  
													</table>  
												</td>
											</tr> 
										</table>
<!-- END SELECTED CONDITIONS - COMPLIANCE REASON TABLE -->	
										<br> 							
<!-- BEGIN CASENOTE TABLE -->
										<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
											<tr class="detailHead">
												<td><bean:message key="prompt.new" />&nbsp;<bean:message key="prompt.casenoteInfo" /></td>
												<td align="right"><img src="/<msp:webapp/>images/step_2.gif"></td>
											</tr>
											<tr>
												<td colspan="2">
													<table width="100%" cellpadding="4" cellspacing="1">
														<tr>
															<td class="formDeLabel" valign="top" nowrap><bean:message key="prompt.casenoteDate" /></td>
															<td class="formDe"><bean:write name="complianceForm" property="casenoteDateAsString" formatKey="date.format.MMddyyyy"/></td>
															<td class="formDeLabel" valign="top" nowrap width="1%"><bean:message key="prompt.casenoteTime" /></td>
															<td class="formDe">
																<bean:write name="complianceForm" property="casenoteTime" formatKey="time.format.HHmm"/>
																<bean:write name="complianceForm" property="AMPMId" /> 
															</td>
														</tr>
														<tr>
															<td class="formDeLabel"><bean:message key="prompt.contactMethod" /></td>
															<td colspan="3" class="formDe"><bean:write name="complianceForm" property="contactMethod"/></td>
														</tr>
														<tr>
															<td class="formDeLabel" valign="top" width="1%" nowrap><bean:message key="prompt.casenoteSubjects" /></td>
															<td colspan="3" class="formDe"><bean:write name="complianceForm" property="subjects"/></td>
														</tr>
														<tr>
															<td class="formDeLabel"><bean:message key="prompt.collateral" /></td>
															<td colspan="3" class="formDe"><bean:write name="complianceForm" property="collateral" filter="false"/></td>
														</tr>
														<tr>
															<td colspan="4" class="formDeLabel"><bean:message key="prompt.casenote" /></td>
														</tr>
														<tr>
															<td colspan="4" class="formDe"><bean:write name="complianceForm" property="casenoteText" filter="false"/></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
<!-- END CASENOTE TABLE -->  
<!-- BEGIN BUTTON TABLE -->
					           		    <table border="0" cellpadding="2" cellspacing="1">
											<tr>
												<td align="center">
													<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finishCasenoteLater" /></html:submit>
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish" /></html:submit>
													<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
												</td>
											</tr>
										</table>
<!-- END BUTTON TABLE -->
									</td>
								</tr>
							</table>
<!-- END GREEN BORDER TABLE -->									
						</td>
					</tr>
					<tr>
						<td><img src="/<msp:webapp/>js/images/spacer.gif" height="5"></td> 
					</tr>
				</table>
<!-- END BLUE BORDER TABLE -->					
			</td>
		</tr>
	</table>
</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
