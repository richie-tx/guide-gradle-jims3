<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 02/27/2008 C. Shimek - Converted to JSP -->
<!-- 02/03/2010 CShimek           - #61243 revised last names maxlength from 75 to 73 to accomdate ", " name connector for full name max. size of 125 -->
<!-- 05/19/2010 CShimek           - #65392 revised last names back to maxlength 75, DER changed field length to 130 -->
<!-- 08/13/2013 RCapestani        - #75938 set court actions textbox to 3500 characters -->
<!-- 09/25/2013 RCapestani  	  - #76068 Format Summary of Court Actions -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ page import="naming.UIConstants" %>
<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/caseHistory/caseSummary/caseSumCourtActions.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/tinyMCECustomInitVRCSPresent.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/caseHistory/caseHistory_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/caseHistory/caseSummary/caseSumCourtActions.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="presetSelectNames()">
<html:form action="/displayCaseSummaryCourtActionsSummary" target="content" focus="courtActionfiledDate">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Case_Summary/CSCD_Case_Summary.htm#|5">
<input type="hidden" name="presentedbyLogonId" value="<bean:write name="caseSummaryForm" property="currentLogonId" />" >
<input type="hidden" name="whoSignedCourtNum" value="<bean:write name="caseSummaryForm" property="courtNum" /> " >
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
<!-- END BLUE TABS TABLE -->
<!-- BEGIN BLUE BORDER TABLE -->			
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<!-- BEGIN SUPERVISEE INFO TABLE -->
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
<!-- END SUPERVISEE INFO TABLE -->
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
												<bean:message key="title.CSCD"/>&nbsp;-
												<bean:message key="prompt.file"/>&nbsp;<bean:message key="prompt.caseSummary"/>
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
													<li>Select Court Actions. Click Next.</li>
												</ul>
											</td>
										</tr>
										<tr>
											<td class="required" colspan="2"><bean:message key="prompt.4.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
										</tr>
									</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN DETAIL TABLE -->									
									<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
										<tr class="detailHead">
											<td>
												<table width="100%" cellpadding="0" cellspacing="0">
													<tr>
														<td class="detailHead"><bean:message key="prompt.filingInformation"/></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1">
													<tr>
														<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.4.diamond"/><bean:message key="prompt.filedDate"/></td>
														<td class="formDe">
															<SCRIPT LANGUAGE="JavaScript" ID="js1">
																var cal1 = new CalendarPopup();
																cal1.showYearNavigation();
															</SCRIPT>
															<html:text name="caseSummaryForm" size="10" maxlength="10" property="courtActionfiledDate" />
																	<a href="#" onClick="cal1.select(caseSummaryForm.courtActionfiledDate,'anchorX','MM/dd/yyyy'); return false;"
																		NAME="anchorX" ID="anchorX"><bean:message key="prompt.4.calendar"/></a> 
														</td>
													</tr>
													<tr>
														<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.4.diamond"/><bean:message key="prompt.presentedBy"/></td>
														<td class="formDe">
															<table cellpadding="0" cellspacing="0" border="0">
																<tr>
																	<td colspan="2">														
																		<html:select name="caseSummaryForm" property="presentedById" onchange="checkForOther(this, 'presentedByOther')">
																			<html:option value=""><bean:message key="select.generic"/></html:option>
																		   	<html:optionsCollection name="caseSummaryForm" property="presentedByList" value="logonId" label="formattedName" /> 
																			<html:option value="OTH">OTHER</html:option>
																		</html:select>
																		<input type="hidden" name="presentedByName" size="130">
																	</td>
																</tr>		
																<tr id="presentedByOther" class="hidden">
																	<td>
																		<table>
																			<tr>
																				<td class="formDeLabel"><bean:message key="prompt.4.diamond"/><bean:message key="prompt.firstName"/></td>
																				<td class="formDeLabel"><bean:message key="prompt.4.diamond"/><bean:message key="prompt.lastName"/></td>
																			</tr>
																			<tr>
																				<td>
																					<html:text name="caseSummaryForm" size="25" maxlength="50" property="presentedByFirstName" />
																				</td>
																				<td>
																					<html:text name="caseSummaryForm" size="25" maxlength="75" property="presentedByLastName" />
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>		
															</table>	
														</td>
													</tr>															
													<tr>
														<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.4.diamond"/><bean:message key="prompt.whoSigned?"/></td>
															<td class="formDe">
																<table cellpadding="0" cellspacing="0" border="0">
																	<tr>
																		<td colspan="2">
																			<html:select name="caseSummaryForm" property="whoSignedId" onchange="checkForOther(this, 'judgeOther')">
																				<html:option value=""><bean:message key="select.generic"/></html:option>
																				<html:optionsCollection name="caseSummaryForm" property="whoSignedList" value="courtNumber" label="formattedName" /> 
																				<html:option value="OTH">OTHER</html:option>
																			</html:select>
																			<input type="hidden" name="whoSignedName" size="130">
																		</td>
																	</tr>
																	<tr id="judgeOther" class="hidden">
																		<td>
																			<table>
																				<tr>
																					<td class="formDeLabel">+<bean:message key="prompt.firstName"/></td>
																					<td class="formDeLabel">+<bean:message key="prompt.lastName"/></td>
																				</tr>
																				<tr>
																					<td>
																						<html:text name="caseSummaryForm" size="25" maxlength="50" property="whoSignedFirstName" />
																					</td>
																					<td>
																						<html:text name="caseSummaryForm" size="25" maxlength="75" property="whoSignedLastName" />
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>  
																</table>
															</td>
														</tr> 
												</table>
											</td>
										</tr> 
									</table> 
									
									<div class="spacer4px"></div>
									<% int RecordCtr=0;
									   int bgCtr=0; 
									   String bgcolor="";
									   String orgbgcolor="";
									   String lmr=""; 
									   String checkItem = "";%>
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="formDeLabel">
											<td colspan="6"><bean:message key="prompt.4.diamond"/></span><bean:message key="prompt.courtActions"/></td>
										</tr>
											<logic:iterate id="caIter" name="caseSummaryForm" property="create1ElementsList">
												<% RecordCtr++; 
													lmr = "L";
													if (RecordCtr % 3 == 2){
														 lmr = "M";
													}
													if (RecordCtr % 3 == 0){
														 lmr = "R";
													}
												%>
												<% if(lmr == "L" ) { %>
											  	<% bgCtr++;
												   bgcolor = "alternateRow";                      
												   if (bgCtr % 2 == 1){
														bgcolor = "normalRow";
													}  
													orgbgcolor = bgcolor; %>	  
														<tr>
														<logic:equal name="caIter" property="status" value="B" >
															<% bgcolor = "suggestedCtAction"; %> 
														</logic:equal>
														<td valign="top" class=<%= bgcolor %>>   
															<input type="checkbox" name="selectedCourtActionsIds" value="<bean:write name="caIter" property="code" />" >
														</td>
														<td valign="top" class=<%= bgcolor %>>	
															<bean:write name="caIter" property="description" />
														</td>
													<% } %>	
													<% if(lmr == "M" ) { %>
														<logic:equal name="caIter" property="status" value="B">
															<% bgcolor = "suggestedCtAction"; %> 
														</logic:equal>
														<td valign="top" class=<%= bgcolor %>>
															<input type="checkbox" name="selectedCourtActionsIds" value="<bean:write name="caIter" property="code" />"  >
														</td>
														<td valign="top" class=<%= bgcolor %>>	
															<bean:write name="caIter" property="description" />
														</td>
													<% } %>	
													<% if(lmr == "R" ) { %>
														<logic:equal name="caIter" property="status" value="B">
															<% bgcolor = "suggestedCtAction"; %> 
														</logic:equal>
														<td valign="top" class=<%= bgcolor %> >
															<input type="checkbox" name="selectedCourtActionsIds" value="<bean:write name="caIter" property="code" />" >
														</td>
														<td valign="top" class=<%= bgcolor %>>	
															<bean:write name="caIter" property="description" />
														</td>
													</tr>
												<% } %>	
												<% 	bgcolor = orgbgcolor; %> 
											</logic:iterate>
									 		<% if(lmr == "L" ) { %>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
											<% } %>	
											<% if(lmr == "M" ) { %>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
											<% } %>	 
										<tr class="formDeLabel">
											<td colspan="6"><bean:message key="prompt.summary" /> of <bean:message key="prompt.court"/> <bean:message key="prompt.action"/>s (Max. characters allowed: 3500 excess will be truncated on finish)</td>
										</tr>
										<tr class="formDe">
											<td colspan="6">
												<bean:define id="userAgency" name="caseSummaryForm" property="userAgency"/>	
												<html:textarea styleClass="mceEditor" name="caseSummaryForm" property="summaryOfCourtActions"
													style="mceEditor"> 
												</html:textarea>
												<tiles:insert page="../../../common/spellCheckButtonTile.jsp" flush="false">
													<tiles:put name="agencyCode"><%=userAgency%></tiles:put>
												</tiles:insert>
											</td>
										</tr>
										<tr>
											<td colspan="6">
												<div style='text-align:left; width:98%; padding:2px;' class="legendSmallText"><span class="suggestedCtAction">&nbsp;<bean:message key="prompt.courtActions"/>&nbsp;</span> denotes Suggested Court Actions.</div>
											</td>
										</tr>
									</table>
<!-- END COURT ACTION TABLE -->										
									<div class="spacer4px"></div>
									<script>
										function trimCasenote(){
											document.getElementsByName("summaryOfCourtActions")[0].value = trimTextarea(document.getElementsByName("summaryOfCourtActions")[0].value); 																											
											myReverseTinyMCEFix(document.getElementsByName("summaryOfCourtActions")[0]);														
											return true;
										}													
										
										customValRequired("summaryOfCourtActions","Summary of Court Actions is required","");
										addDefinedTinyMCEFieldMask("summaryOfCourtActions","Summary of Court Actions cannot have % or _ entries","");
										customValMaxLength("summaryOfCourtActions","Summary of Court Actions cannot be more than 3500 characters","3600");
									</script>
<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.back" /></html:submit>
												<html:submit property="submitAction" onclick="return (validateNextInput(this.form) && myTinyMCEFix() && trimCasenote() && validateCustomStrutsBasedJS(this.form) && disableSubmit(this, this.form))"> <bean:message key="button.next" /></html:submit>
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
<!-- END BLUE BORDER TABLE -->			
		</td>
	</tr>
</table>
<!-- END PAGE TABLE -->
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>