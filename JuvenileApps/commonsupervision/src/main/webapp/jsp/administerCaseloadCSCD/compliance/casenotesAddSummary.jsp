<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/27/2007	 Debbie Williamson - Create JSP -->
<!-- 08/21/2008	 Clarence Shimek   - defect#52794 revised buttons per defect, mainly adding reset -->
<!-- 09/21/2009	 Clarence Shimek   - #61740 revised Case# to use displayCaseNum for case number dispaly -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/compliance/casenotesAddSummary.jsp</title>

<!-- JavaScripts -->
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" >
<html:form action="/submitAddCasenote" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Administer_Compliance/CSCD_Compliance.htm#|3">
<%-- input type="hidden" name="helpFile" value="commonsupervision/Administer_Compliance/Common_Sup_Compliance_and_Casenotes.htm#|3"--%>
<div align="center">
<!-- BEGIN BLUE TABS TABLE -->
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" >
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
<!-- END SUPERVISEE INFORMATION TABLE  -->	
			</td>
		</tr>		
		<tr>
			<td valign="top" align="center"> 
<!-- BEGIN GREEN TABS TABLE -->				
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
<!-- END GREEN TABS TABLE -->			
			</td>
		</tr>	
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
										<bean:message key="title.create" />&nbsp;<bean:message key="prompt.casenote" />&nbsp;<bean:message key="prompt.summary" />
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
<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td>
										<ul>
											<li>Click Finish to Complete.</li>
										</ul>
									</td>
								</tr>
							</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN SELECTED CONDITIONS TABLE -->	
							<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
								<tr class="detailHead">
									<td><bean:message key="prompt.selectedConditions" /></td>
								</tr>
								<tr>
									<td>
										<table width="100%" cellpadding="2" cellspacing="1" id="compliantList">
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.condition" /></td>
												<td class="formDeLabel" title="Court" align="center"><bean:message key="prompt.CRT" />&nbsp;<bean:message key="prompt.#" /></td>
												<td class="formDeLabel"><bean:message key="prompt.case" />&nbsp;<bean:message key="prompt.#" /></td>
											</tr>	
											<% int RecordCounter = 0;
											   String bgcolor = "";%>  
											<logic:iterate id="conditionIter" name="complianceForm" property="selectedConditions">  
												<tr class= <% RecordCounter++;
															  bgcolor = "alternateRow";                      
				        						             if (RecordCounter % 2 == 1)
				                     						 {
											                    bgcolor = "normalRow";
					    						             }  
					                 						 out.print(bgcolor);  %>  >
													<td>
														<a href="javascript:openWindow('/<msp:webapp/>handleComplianceConditions.do?submitAction=Link&sprOrderConditionId=<bean:write name='conditionIter' property='sprOrderConditionId'/>')" title=""><bean:write name='conditionIter' property='orderConditionName'/></a>
													</td>
													<td align="center"><bean:write name="conditionIter" property="courtId" /></td>
													<td><bean:write name="conditionIter" property="displayCaseNum" /></td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
							</table>
<!-- END SELECTED CONDITIONS TABLE -->
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
												<td class="formDeLabel" valign=top width="1%" nowrap><bean:message key="prompt.casenoteSubjects" /></td>
												<td colspan="3" class="formDe"><bean:write name="complianceForm" property="subjects"/></td>
											</tr>
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.collateral" /></td>
												<td colspan="3" class="formDe">
										<%-- 			<logic:equal name="complianceForm" property="collateral" value="AS">
														<table width="100%" border="0">
															<iterate id="anIter" name="complianceForm" property="selectedAssociateNameList" >
																<tr>
																	<td><bean:write name="anIter" property="selectedAssociateNameList"/></td>
																</tr>	
															</iterate>
														</table>
													</logic:equal>
													<logic:notEequal name="complianceForm" property="collateral" value="AS"> --%>
														<bean:write name="complianceForm" property="collateral" filter="false"/>
											<%-- 		</logic:notEequal>  --%>
												</td>
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
<!--END CASENOTE TABLE-->
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
</div>
</html:form>
<br>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>