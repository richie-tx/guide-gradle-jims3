<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 06/20/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 12/31/2008 Clarence Shimek   - #56324 add validation scripts -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDCodeTableConstants" %>
<%@ page import="naming.UIConstants" %>
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
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javaScript" src="/<msp:webapp/>js/transferCSCD/transferCase.js"></script>
</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/transferCaseAction" target="content">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--blue tabs start-->
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
							    <tiles:put name="tab" value="caseloadTab" />
						    </tiles:insert>
						<!--blue tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<!-- BEGIN HEADING TABLE -->
						<table width="100%">
							<tr>
								<td align="center" class="header">
									<bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="prompt.transfer"/>
									<logic:equal name="caseAssignmentForm" property="superviseeTransferCases.transferType" value="<%=UIConstants.CS_TRANSFER_IN_CASES%>">
										<bean:message key="prompt.in"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Transfers/CSCD_Transfers.htm#|8">
										<input type="hidden" name="pagetype" value="<bean:message key="prompt.in" />" size="5" />
									</logic:equal>
									<logic:equal name="caseAssignmentForm" property="superviseeTransferCases.transferType" value="<%=UIConstants.CS_TRANSFER_OUT_CASES%>">
										<bean:message key="prompt.out"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Caseload/Transfers/CSCD_Transfers.htm#|4">
										<input type="hidden" name="pagetype" value="<bean:message key="prompt.out" />" size="5"/>
									</logic:equal>	
								</td>
							</tr>
						</table>
						<!-- END HEADING TABLE -->
						<!-- BEGIN ERROR TABLE -->
						<table width="98%" align="center">							
							<tr>
								<td align="center" class="errorAlert">
									<logic:notEqual name="caseAssignmentForm" property="errorMsg" value="">
										<bean:write name="caseAssignmentForm" property="errorMsg"/>
									</logic:notEqual>
									<logic:equal name="caseAssignmentForm" property="errorMsg" value="">
										<html:errors></html:errors>
									</logic:equal>
								</td>
							</tr>		
						</table>
						<!-- END ERROR TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
							<tr>
								<td>
									<ul>
										<li>Enter required fields. Click Next.</li>
									</ul>
								</td>
							</tr>
							<tr>
								<td class="required"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.requiredFieldInstruction"/>&nbsp;&nbsp;
									<logic:equal name="caseAssignmentForm" property="superviseeTransferCases.transferType" value="<%=UIConstants.CS_TRANSFER_IN_CASES%>">
										<bean:message key="prompt.dateFieldsInstruction"/> + Required if NOT rejected.
									</logic:equal>
									<logic:equal name="caseAssignmentForm" property="superviseeTransferCases.transferType" value="<%=UIConstants.CS_TRANSFER_OUT_CASES%>">
										<bean:message key="prompt.dateFieldsInstruction"/> + One of these fields is required.
									</logic:equal>	
								</td>	
							</tr>
						</table>
						<!-- END INSTRUCTION TABLE -->
						<!--header start-->
							<tiles:insert page="../common/assignmentHeader.jsp" flush="true">
						    </tiles:insert>
						<!--header end-->
						<br>
						<!-- BEGIN DETAIL TABLE -->
						<span id="caselist">
						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<tr class="detailHead">
								<td>
									<bean:message key="prompt.casesToBeTransferred"/>
									<logic:equal name="caseAssignmentForm" property="superviseeTransferCases.transferType" value="<%=UIConstants.CS_TRANSFER_IN_CASES%>">
										<bean:message key="prompt.in"/>
									</logic:equal>
									<logic:equal name="caseAssignmentForm" property="superviseeTransferCases.transferType" value="<%=UIConstants.CS_TRANSFER_OUT_CASES%>">
										<bean:message key="prompt.out"/>
									</logic:equal>									
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellpadding="2" cellspacing="1">
										<tr class="formDeLabel">
											<td><bean:message key="prompt.DEG"/></td>
											<td><bean:message key="prompt.case"/></td>
											<td><bean:message key="prompt.CRT"/></td>
											<td><bean:message key="prompt.DOCS"/></td>
											<td><bean:message key="prompt.endDate"/></td>
											<td><bean:message key="prompt.daysLeft"/></td>
											<td><bean:message key="prompt.caseStatus"/></td>
											<td><bean:message key="prompt.defendantStatus"/></td>
										</tr>
										<logic:notEmpty name="caseAssignmentForm" property="superviseeTransferCases.casesToTransfer">
                                      		<logic:iterate id="transferCase" name="caseAssignmentForm" property="superviseeTransferCases.casesToTransfer" indexId="index">
	                                            <tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
													<td><bean:write name="transferCase" property="degreeOfOffense"/></td>
													<td>		
														<a href="javascript:openWindow('/<msp:webapp/>displaySupervisionOrderDetails.do?submitAction=Link&selectedValue=<bean:write name="transferCase" property="supervisionOrderId"/>')">																																						
															<bean:write name="transferCase" property="criminalCaseId"/>
														</a>
													</td>																																																									
													<td><bean:write name="transferCase" property="courtId"/></td>
													<td><bean:write name="transferCase" property="probationStartDate"/></td>
													<td><bean:write name="transferCase" property="probationEndDate"/></td>
													<logic:equal name="transferCase" property="daysLeft" value="<%=String.valueOf(Long.MAX_VALUE)%>">
														<td/>
													</logic:equal>
													<logic:notEqual name="transferCase" property="daysLeft" value="<%=String.valueOf(Long.MAX_VALUE)%>">
														<td><bean:write name="transferCase" property="daysLeft"/></td>
													</logic:notEqual>
													<td><bean:write name="transferCase" property="caseStatus"/></td>
													<td><bean:write name="transferCase" property="defendantStatus"/></td>																			
												</tr>
											</logic:iterate>
                                       </logic:notEmpty>
									</table>
								</td>
							</tr>
						</table>
						<div class="spacer4px"></div>
						</span>
						<script type="text/javascript">
							var cal = new CalendarPopup();
							cal.showYearNavigation();
						</script>
						
						<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
							<logic:equal name="caseAssignmentForm" property="superviseeTransferCases.transferType" value="<%=UIConstants.CS_TRANSFER_IN_CASES%>">
								<tr class="detailHead">
									<td>
										<bean:message key="prompt.transferInInformation"/>
									</td>
								</tr>
								<tr>
									<td>
										<table width="100%" border="0" cellpadding="4" cellspacing="1">
												<tr>
													<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.rejected"/></td>
													<td class="formDe" colspan="3">
														<bean:message key="prompt.yes"/> <input type="radio" name="superviseeTransferCases.rejectedAsStr" value="Y" /> 
														<bean:message key="prompt.no"/> <input type="radio"  name="superviseeTransferCases.rejectedAsStr" value="N" checked/>
													</td>
												</tr>
												<tr>															
													<td class="formDeLabel" nowrap><bean:message key="prompt.transferInDate"/></td>
													<td class="formDe">
		                                                <html:text name="caseAssignmentForm" property="superviseeTransferCases.transferInDate" size="10" maxlength="10" styleId="transferInDateId" />
														<A HREF="#" onClick="cal.select(document.getElementById('transferInDateId'),'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2">
															<img border="0" src="/<msp:webapp/>images/calendar2.gif" title="(mm/dd/yyyy)"/>
														</A>
														<script>
															document.getElementById('transferInDateId').value=getCurrentDate();
														</script>																												
													</td>
												</tr>
												<tr>
													<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.texasCounty"/></td>
													<td class="formDe" colspan="3">
														<bean:write name="caseAssignmentForm" property="superviseeTransferCases.transferTxCountyName"/>
													</td>
												</tr>
												<tr>
													<td class="formDeLabel" nowrap><bean:message key="prompt.outOfState"/></td>
													<td class="formDe" colspan="3">
														<bean:write name="caseAssignmentForm" property="superviseeTransferCases.transferStateName"/>
													</td>
												</tr>
												<tr>
													<td class="formDeLabel" nowrap>+<bean:message key="prompt.personID"/></td>
													<td class="formDe" colspan="3">
														<html:text name="caseAssignmentForm" property="superviseeTransferCases.personId" size="20" maxlength="20" />
													</td>
												</tr>
											</table>
										</td>
									</tr>
							</logic:equal>
							<logic:equal name="caseAssignmentForm" property="superviseeTransferCases.transferType" value="<%=UIConstants.CS_TRANSFER_OUT_CASES%>">							
								<tr class="detailHead">
									<td>
										<bean:message key="prompt.transferOutInformation"/>
									</td>
								</tr>
								<tr>
									<td>
										<table width="100%" border="0" cellpadding="2" cellspacing="1">
											<tr>
												<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.transferOutDate"/></td>
												<td class="formDe">
	                                                <html:text name="caseAssignmentForm" property="superviseeTransferCases.transferOutDate" size="10" maxlength="10" styleId="transferOutDateId" />
													<A HREF="#" onClick="cal.select(document.getElementById('transferOutDateId'),'anchor1','MM/dd/yyyy'); return false;" NAME="anchor1" ID="anchor1">
														<img border="0" src="/<msp:webapp/>images/calendar2.gif" title="(mm/dd/yyyy)"/>
													</A>
													<script>
														document.getElementById('transferOutDateId').value=getCurrentDate();
													</script>													
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap>+<bean:message key="prompt.texasCounty"/></td>
												<td class="formDe" colspan="3">
													<html:select name="caseAssignmentForm" property="superviseeTransferCases.transferTxCountyId" onchange="checkSelect(this)" >
													   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
													   <jims:codetable codeTableName="<%=PDCodeTableConstants.COUNTY%>" />
													</html:select>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap>+<bean:message key="prompt.outOfState"/></td>
												<td class="formDe" colspan="3">
													<html:select name="caseAssignmentForm" property="superviseeTransferCases.transferStateId" onchange="checkSelect(this)">
													   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
													   <jims:codetable codeTableName="<%=PDCodeTableConstants.STATE_ABBR%>" />
													</html:select>
												</td>
											</tr>
										</table>									
									</td>
								</tr>
							</logic:equal>																		
						</table>
						<!-- END DETAIL TABLE -->
						<br>
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
									<html:submit property="submitAction" onclick="return validateInput(this.form) && disableSubmit(this, this.form);"> <bean:message key="button.next" /></html:submit>
									<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
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
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>