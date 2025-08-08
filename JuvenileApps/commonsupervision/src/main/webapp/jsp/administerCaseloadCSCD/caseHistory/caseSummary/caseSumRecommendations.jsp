<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 02/27/2008 C Shimek - Converted to JSP -->
<!-- 04/14/2010 C Shimek - #64845 revise block title and layout -->
<!-- 04/30/2010 C Shimek - #64845 removed required marker on Status/Comments and Recommendations per requirement change -->
<!-- 07/02/2010 C Shimek - #66278 revised comment max. check from onsubmit to active -->
<!-- 10/07/2010 C Shimek - #67758 revised comment max. from 500 to 3500 -->
<!-- 06/16/2011 R Young   - #70346 Violation Report-copy Comments in each section(UI) -->
<!-- 09/24/2013  R Capestani - #76068 CSCD:  VR and CS Casenote needs correcting -->
<!-- 10/03/2013 R Capestani   	  - #76218 CSCD: Modify CSO Recommendation to 1000(UI/PD) -->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.UIConstants" %>
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

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/caseHistory/caseSummary/caseSumRecommendations.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/caseHistory/caseHistory_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/caseHistory/caseSummary/caseSumRecommendations.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);"  onload="setCheckBoxFocus()">
<html:form action="/displayRecommendationsSummary" target="content" focus="create1Comments">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Case_Summary/CSCD_Case_Summary.htm#|27">
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
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
						</tiles:insert>
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
<!-- BEGIN SUPERVISEE DETAILS TABLE -->
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
<!-- END SUPERVISEE DETAILS TABLE -->						
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
												<bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.caseSummary"/>&nbsp;-
												<bean:message key="prompt.recommendations"/>
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
<!-- BEGIN INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td>
												<ul>
													<li>Enter required field. Click Next.</li>
												</ul>
											</td>
										</tr>
										<tr>
											<td class="required"><bean:message key="prompt.4.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
										</tr>
									</table>
									<!-- END INSTRUCTION TABLE -->							
									<!-- BEGIN DETAIL TABLE -->
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td><bean:message key="prompt.4.diamond"/> <bean:message key="prompt.suggestedCourtActions"/></td>
										</tr>
										<tr>
											<td>
											<% int RecordCtr=0;
											   int bgCtr=0; 
											   String bgcolor="";
											   String lmr=""; 
											   String checkItem = ""; %>
<!-- BEGIN SUGGESTED COURT ACTIONS TABLE -->
												<table width="100%" border="0" cellpadding="2" cellspacing="1">
													<logic:iterate id="scaIter" name="caseSummaryForm" property="create1ElementsList">
														<%  checkItem = ""; %>
														<logic:equal name="scaIter" property="visible" value="true">
															<% checkItem = "checked=true"; %> 
														</logic:equal>
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
															}  %>	  
															<tr class=<%= bgcolor %> >
																<td valign="top">
																	<input type="checkbox" name="selectedSuggestedCourtActionIds" value="<bean:write name="scaIter" property="code" />" <%= checkItem %> >
																</td>
																<td valign="top">	
																	<bean:write name="scaIter" property="description" />
																</td>
														<% } %>	
														<% if(lmr == "M" ) { %>
																<td valign="top">
																	<input type="checkbox" name="selectedSuggestedCourtActionIds" value="<bean:write name="scaIter" property="code" />" <%= checkItem %> >
																</td>
																<td valign="top">	
																	<bean:write name="scaIter" property="description" />
																</td>
														<% } %>	
														<% if(lmr == "R" ) { %>
																<td valign="top">
																	<input type="checkbox" name="selectedSuggestedCourtActionIds" value="<bean:write name="scaIter" property="code" />" <%= checkItem %> >
																</td>
																<td valign="top">	
																	<bean:write name="scaIter" property="description" />
																</td>
															</tr>
														<% } %>	
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
												</table>
<!-- END SUGGESTED COURT ACTIONS TABLE -->
											</td>
										</tr>
										<tr class="formDeLabel">
											<td><bean:message key="prompt.statusCommentsandRecommendations"/>
											<tiles:insert page="/jsp/common/spellCheckCaseHistTile.jsp" flush="false">
                          						<tiles:put name="tTextField" value="create1Comments"/>
                          						<tiles:put name="tSpellCount" value="spellBtn1" />
                        					</tiles:insert>
                        					(Max. characters allowed: 1000)
											</td>
										</tr>
										<tr class="formDe">
											<td class="formDe">
												<html:textarea name="caseSummaryForm" property="create1Comments" 
													onmouseout="textLimit(this, 1000);" 
       												onkeyup="textLimit(this, 1000);" 
													style="width:100%" rows="10">
												</html:textarea>
											</td>
										</tr>
										<input type="hidden" name="prevComments" value="<bean:write name='caseSummaryForm' property='previousRecommendations'/>" >
									</table>
<!-- END DETAILS TABLE -->
									<div class="spacer4px"></div>
<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.back" /></html:submit>
												<html:submit property="submitAction" onclick="return validateNextInput(this.form) && disableSubmit(this, this.form)"> <bean:message key="button.next" /></html:submit>
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.cancel" /></html:submit>
												<input type="button" name="copyComments" value="<bean:message key="button.copyComments" />" onclick="loadPrevComments()" />
											</td>
										</tr>
									</table>
<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table>
<!-- END GREEEN BORDER TABLE -->									
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