<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 02/14/2008 Debbie Williamson  - Converted PT to JSP -->
<!-- 03/06/2009 C. Shimek - #57241 revised input amounts lengths from 7 to 8 -->
<!-- 07/02/2010 C. Shimek - #66278 revised comment max. check from onsubmit to active -->
<!-- 10/07/2010 C Shimek  - #67758 revised comment max. from 500 to 3500 and added spell check -->
<!-- 06/10/2011 R Young   - #70346 Violation Report-copy Comments in each section(UI) -->

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
<title><bean:message key="title.heading" /> - administerCaseloadCSCD/caseHistory/violationReports/vrFeeHistory.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/caseHistory/caseHistory_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/caseHistory/violationReports/vrFeeHistory.js"></script>
</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displayVRFeeHistorySummary" target="content">
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
<!-- ENE BLUE TABS TABLE -->	
<!-- BEGIN BLUE BORDER TABLE -->				
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif"  height="5"></td>
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
					</td>
				</tr>							
				<tr>
					<td valign="top" align="center">
						<!--casefile tabs start-->
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
<!-- BEGIN BLUE BORDER TABLE -->								
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
												<bean:message key="prompt.feeHistory"/>
												<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Violation_Reports/CSCD_Violation_Reports.htm#|12">
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
													<li>Click Remove Selected to remove selected Fee History records. Click next when complete.</li>
												</ul>
											</td>
										</tr>
									</table>
<!-- END INSTRUCTION TABLE -->
									<br>
<!-- BEGIN FEE HISTORY TABLE -->									
 									<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
										<tr class="detailHead">
											<td><bean:message key="prompt.feeHistory"/></td>
										</tr>
										<tr>
											<td>
												<table width="100%" cellpadding="2" cellspacing="1">
												<% String displayClass; %>
													<tr class="formDeLabel">
														<td width="1%"></td>
														<td><bean:message key="prompt.payType"/>
															<jims2:sortResults beanName="violationReportsForm" results="create1ElementsList" primaryPropSort="payType" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" levelDeep="4" hideMe="true"/>  
														</td>
														<td><bean:message key="prompt.amtOrdered"/></td>
														<td><bean:message key="prompt.paidToDate"/></td>
														<td><bean:message key="prompt.delinquentAmt"/></td>
													</tr>
													<logic:iterate id="vrIter" name="violationReportsForm" property="create1ElementsList" indexId="index">
														<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
															<td width="1%">
																<input type="checkbox" name="selectedFeeHistoryIds" value="<bean:write name='vrIter' property='feeId'/>" indexId="index">																
															</td>
															<% displayClass = ""; %>
															<logic:equal name="vrIter" property="manualAdded" value="true">
																<% displayClass = "class='pendingSP'"; %>	
															</logic:equal> 
															<td <%= displayClass %>><bean:write name="vrIter" property="payType"/></td>
															<td <%= displayClass %>><bean:write name="vrIter" property="amountOrdered" /></td>
															<td <%= displayClass %>><bean:write name="vrIter" property="paidToDate" /></td>
															<td <%= displayClass %>><bean:write name="vrIter" property="delinquentAmount"/></td>
														</tr>
												   	</logic:iterate>           													
													<logic:equal name="violationReportsForm" property="showAddFields" value="true"> 
														<tr class="normalRow" >
															<td width="1%">&nbsp;</td>	
				 								<%-- 		<td><html:text name="violationReportsForm" property="payType" size="20" maxlength="20"/></td> --%>
													 		<td>
													 			<html:select name="violationReportsForm" property="payTypeId">
																	<html:option value=""><bean:message key="select.generic"/></html:option>
																	<html:optionsCollection name="violationReportsForm" property="payTypes" value="code" label="description" /> 
																</html:select>
													 		</td>
															<td><html:text name="violationReportsForm" property="amountOrdered" size="15" maxlength="15" /></td>
															<td><html:text name="violationReportsForm" property="paidToDate" size="15" maxlength="15"/></td>
															<td><html:text name="violationReportsForm" property="deliquentAmount" size="15" maxlength="15" /></td>
														</tr>
													</logic:equal>  
												</table> 
											</td>
										</tr>
									</table>  
<!-- END FEE HISTORY TABLE -->	
<!-- BEGIN ADD BUTTON TABLE -->
									<table width="98%">
										<tr>
											<td><bean:message key="prompt.rowsManuallyAdded"/></td>
										</tr>	
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return validateInputs(this, this.form) && disableSubmit(this, this.form)"><bean:message key="button.addFee" /></html:submit>
											</td>	
										</tr>
										<tr>
											<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
										</tr>
									</table>
<!-- END ADD BUTTON TABLE -->											
<!-- BEGIN FEE HISTORY COMMENT TABLE -->										
									<table width="98%" cellpadding="2" cellspacing="1" class="borderTableBlue">
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.comments"/> 
											<tiles:insert page="/jsp/common/spellCheckCaseHistTile.jsp" flush="false">
                          						<tiles:put name="tTextField" value="create1Comments"/>
                          						<tiles:put name="tSpellCount" value="spellBtn1" />
                        					</tiles:insert>
											 (Max. characters allowed: 3500)</td>
										</tr>
										<tr>
											<td class="formDe">
												<html:textarea name="violationReportsForm" property="create1Comments" 
													onmouseout="textLimit(this, 3500);" 
       												onkeyup="textLimit(this, 3500);" 
													style="width:100%" rows="30">
												</html:textarea>
											</td>
										</tr>
										<input type="hidden" name="prevComments" value="<bean:write name='violationReportsForm' property='previousFeeHistoryComments' />" >
									</table>
<!-- END FEE HISTORY COMMENT TABLE -->										
									<br>
<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return validateInputs(this, this.form) && disableSubmit(this, this.form)"> <bean:message key="button.next" /></html:submit>
												<html:submit property="submitAction" onclick="return validateCheckBoxSelect(this.form) && disableSubmit(this, this.form)"> <bean:message key="button.removeSelected" /></html:submit>
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.refreshFees" /></html:submit>
												<input type="button" name="copyComments" value="<bean:message key="button.copyComments" />" onclick="loadPrevComments()" />
											</td>
										</tr>
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"> <bean:message key="button.back" /></html:submit>
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
