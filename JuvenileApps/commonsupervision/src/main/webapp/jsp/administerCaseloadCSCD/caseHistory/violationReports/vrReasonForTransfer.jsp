<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 03/06/2008 C. Shimek - Created from Case Summary version as part of ER#50113 -->
<!-- 07/02/2010 C. Shimek - #66278 revised comment max. check from onsubmit to active -->
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/caseHistory/violationReports/vrReasonForTransfer.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/caseHistory/caseHistory_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/caseHistory/violationReports/vrReasonForTransfer.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displayVRReasonForTransferSummary" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Violation_Reports/CSCD_Violation_Reports.htm#|34">
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
						<!--blue tabs start-->
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
						</tiles:insert>
						<!--blue tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
<!-- ENE BLUE TABS TABLE -->	
<!-- BEGIN BLUE BORDER TABLE -->				
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif"  height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<!-- BEGIN SUPERVISEE HEADING TABLE -->
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
<!-- END SUPERVISEE HEADING TABLE -->						
					</td>
				</tr>							
				<tr>
					<td valign="top" align="center">
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
												<bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="title.violationReport"/>&nbsp;-
												<bean:message key="prompt.reasonForTransfer"/>
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
<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
													<li>Select reason for transfer. Click next when complete.</li>
												</ul>
											</td>
										</tr>
										<tr>
											<td class="required"><bean:message key="prompt.4.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
										</tr>
									</table>
<!-- END INSTRUCTION TABLE -->
									<% int RecordCtr=0;
									   int bgCtr=0; 
									   String bgcolor="";
									   String leftRight="R"; 
									   String checkItem = "";%>									
<!-- BEGIN REASON FOR TRANSFER TABLE -->									
									<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
										<tr class="detailHead">
											<td><bean:message key="prompt.4.diamond"/> <bean:message key="prompt.reasonForTransfer"/></td>
										</tr>
										<jims2:sortResults beanName="violationReportsForm" results="create1ElementsList" primaryPropSort="description" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="10" hideMe="true"/> 
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1">
													<logic:iterate id="scaIter" name="violationReportsForm" property="create1ElementsList">
													<%  checkItem = ""; %>
														<logic:equal name="scaIter" property="visible" value="true">
															<% checkItem = "checked=true"; %> 
														</logic:equal>
														<% RecordCtr++; 
															leftRight = "L";
															if (RecordCtr % 2 == 0){
																 leftRight = "R";
															}
														%>
														<% if(leftRight == "L" ) { %>
															<% bgCtr++;
															   bgcolor = "alternateRow";                      
															   if (bgCtr % 2 == 1)
																   bgcolor = "normalRow"; %>
															<tr class=<%=bgcolor %> >
																<td>
																	<input type="checkbox" name="selectedReasonForTransferIds" value="<bean:write name="scaIter" property="code" />" <%= checkItem %> >
																	<bean:write name="scaIter" property="description" />
																</td>
														<% } %>	
														<% if(leftRight == "R" ) { %>
																<td>
																	<input type="checkbox" name="selectedReasonForTransferIds" value="<bean:write name="scaIter" property="code" />" <%= checkItem %>  >
																	<bean:write name="scaIter" property="description" />
																</td>
															</tr>
														<% } %>	
													</logic:iterate>
													<% if(leftRight == "L" ) { %>
															<td>&nbsp;</td>
														</tr>
													<% } %>	
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="1" cellspacing="0">
													<tr>
														<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.4.diamond"/> Extended?</td>
														<td class="formDe">
															<logic:equal name="violationReportsForm" property="currentReasonForTransferComments" value="">
																<logic:equal name="violationReportsForm" property="previousReasonForTransferComments" value="YES">
																	<input type="radio" name="isExtended" value="YES" checked> Yes
																	<input type="radio" name="isExtended" value="NO"> No
																</logic:equal>
																<logic:equal name="violationReportsForm" property="previousReasonForTransferComments" value="NO">
																	<input type="radio" name="isExtended" value="YES"> Yes
																	<input type="radio" name="isExtended" value="NO"> No
																</logic:equal>
																<logic:equal name="violationReportsForm" property="previousReasonForTransferComments" value="">
																	<input type="radio" name="isExtended" value="YES"> Yes
																	<input type="radio" name="isExtended" value="NO"> No
																</logic:equal>
															</logic:equal>
															<logic:notEqual name="violationReportsForm" property="currentReasonForTransferComments" value="">
																<logic:equal name="violationReportsForm" property="currentReasonForTransferComments" value="YES">
																	<input type="radio" name="isExtended" value="YES" checked> Yes
																	<input type="radio" name="isExtended" value="NO"> No
																</logic:equal>
																<logic:equal name="violationReportsForm" property="currentReasonForTransferComments" value="NO">
																	<input type="radio" name="isExtended" value="YES"> Yes
																	<input type="radio" name="isExtended" value="NO" checked> No
																</logic:equal>
															</logic:notEqual>
														</td>
													</tr>
												</table>			
											</td>
										</tr>
									</table>
<!-- END REASON FOR TRANSFER TABLE -->								
									<br>
<!-- BEGIN COMMENT TABLE 										
									<table width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.comments"/> (Max. characters allowed: 500)</td>
										</tr>
										<tr>
											<td class="formDe">
												<html:textarea name="violationReportsForm" property="create1Comments" 
													onmouseout="textLimit(this, 500);" 
       												onkeyup="textLimit(this, 500);" 
													style="width:100%" rows="5">
												</html:textarea>
											</td>
										</tr>
									</table>
 END COMMENT TABLE 										
									<br>
-->
<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.back" /></html:submit>
												<html:submit property="submitAction" onclick="return validateInput(this.form) && disableSubmit(this, this.form)"> <bean:message key="button.next" /></html:submit>												
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.cancel" /></html:submit>
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
<!-- END BLUE BORDERE TABLE -->				
		</td>
	</tr>
</table>
<!-- END PAGE TABLE -->	
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>