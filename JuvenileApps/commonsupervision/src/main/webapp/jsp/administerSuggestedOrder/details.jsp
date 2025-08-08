<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/27/2005	 Hien Rodriguez - Create JSP -->
<!-- 01/24/2006  Hien Rodriguez - Implementing interim Back button -->
<!-- 09/12/2006  Hien Rodriguez - Defect#34950 correct instruction for Delete option -->
<!-- 01/18/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!-- SCRIPTING VARIABLES -->
<jsp:useBean id="codeHelper" scope="application" class="ui.supervision.suggestedorder.helper.SuggestedOrderListHelper"/>

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
<title><bean:message key="title.heading" /> - administerSuggestedOrder/details.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/handleSuggestedOrderSelection" target="content">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>    	
  	</tr>
  	<tr>
    	<td valign="top">
    	<!-- BEGIN BLUE TAB TABLE -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--tabs start-->
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tabid" value="suggestedOrderTab"/>
							</tiles:insert>						
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>				
			</table>
		<!-- END BLUE TAB TABLE -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">					
					<!-- BEGIN HEADING TABLE -->
						<table width="98%">
							<tr>
								<td align="center" class="header">
									<logic:equal name="suggestedOrderForm" property="action" value="view">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|3">
										<bean:message key="prompt.view" /> <bean:message key="title.suggestedOrder" />										
										<tr>
											<td><ul>
												<li>Click the appropriate button below.</li>
											</ul></td>
										</tr> 
									</logic:equal>
									<logic:equal name="suggestedOrderForm" property="action" value="delete">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|25">
										<bean:message key="prompt.delete" /> <bean:message key="title.suggestedOrder" /> - <bean:message key="prompt.summary" /> 
										<tr><td>&nbsp;</td></tr>
										<tr>
											<td><ul>
												<li>The following Suggested Order will be deleted when you select the Finish button.</li>
											</ul></td>
										</tr> 
									</logic:equal>
									<logic:equal name="suggestedOrderForm" property="action" value="confirmCreate">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|10">
										<bean:message key="prompt.create" /> <bean:message key="title.suggestedOrder" /> - <bean:message key="prompt.confirmation" />										
										<tr>
											<td class="confirm">Suggested Order successfully created.</td>
										</tr>
									</logic:equal>									
									<logic:equal name="suggestedOrderForm" property="action" value="confirmUpdate">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|17">
										<bean:message key="prompt.update" /> <bean:message key="title.suggestedOrder" /> - <bean:message key="prompt.confirmation" />										
										<tr>
											<td class="confirm">Suggested Order successfully updated.
															<logic:equal name="suggestedOrderForm" property="standardId" value="SNS">
													<br>
												Standard Conditions have been refreshed.
											</logic:equal>
											<logic:equal name="suggestedOrderForm" property="standardId" value="SO">
											<br>
												Standard Conditions have been refreshed.
											</logic:equal>
													
											</td>
										</tr>
									</logic:equal>
									<logic:equal name="suggestedOrderForm" property="action" value="confirmCopy">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|24">
										<bean:message key="prompt.copy" /> <bean:message key="title.suggestedOrder" /> - <bean:message key="prompt.confirmation" />										
										<tr>
											<td class="confirm">Suggested Order successfully copied.
											<logic:equal name="suggestedOrderForm" property="standardId" value="SNS">
													<br>
												Standard Conditions have been refreshed.
											</logic:equal>
											<logic:equal name="suggestedOrderForm" property="standardId" value="SO">
											<br>
												Standard Conditions have been refreshed.
											</logic:equal>
											</td>
										</tr>
									</logic:equal>
									<logic:equal name="suggestedOrderForm" property="action" value="confirmDelete">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|26">
										<bean:message key="prompt.delete" /> <bean:message key="title.suggestedOrder" /> - <bean:message key="prompt.confirmation" />										
										<tr>
											<td class="confirm">Suggested Order successfully deleted.</td>
										</tr>
									</logic:equal>
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
					<!-- BEGIN SUGGESTED ORDER SECTION -->
						<table width="98%" border="0" cellspacing="1" cellpadding="4">
							<tr>
								<td class="detailHead" colspan="2">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>										
											<td class="detailHead">&nbsp;<bean:message key="prompt.suggestedOrder" /></td>
											<td align="right"><img src="/<msp:webapp/>images/step_1.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel" width="1%"><bean:message key="prompt.name" /></td>
								<td class="formDe"><bean:write name="suggestedOrderForm" property="orderName"/></td>
							</tr>
							<tr>
								<td class="formDeLabel" width="1%"><bean:message key="prompt.description" /></td>
								<td class="formDe"><bean:write name="suggestedOrderForm" property="orderDescription" /></td>
							</tr>
					<!-- END SUGGESTED ORDER SECTION -->						
							<tr><td><img src="/<msp:webapp/>images/spacer.gif"></td></tr>				
					<!-- BEGIN SELECTED OFFENSES SECTION -->
							<tr>
								<td class="detailHead" colspan="2">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>										
											<td class="detailHead">&nbsp;<bean:message key="prompt.selectedOffenses" /></td>
											<td align="right"><img src="/<msp:webapp/>images/step_2.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							<logic:empty name="suggestedOrderForm" property="offenseSelectedList">	
							<tr id="offensesSpan">			
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.selectedOffenses" /></td>
								<td class="formDe">None Selected</td>
							</tr>
							</logic:empty>	
							<logic:notEmpty name="suggestedOrderForm" property="offenseSelectedList">	
							<tr>
								<td colspan="2">											
									<table width="100%" border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.offenseCode" /></td>
											<td class="formDeLabel"><bean:message key="prompt.offenseLiteral" /></td>
											<td class="formDeLabel"><bean:message key="prompt.stateOffenseCode" /></td>
											<td class="formDeLabel"><bean:message key="prompt.penalCode" /></td>
											<td class="formDeLabel"><bean:message key="prompt.levelCode" /></td>
											<td class="formDeLabel"><bean:message key="prompt.degreeCode" /></td>
										</tr>
									<logic:notEmpty name="suggestedOrderForm" property="offenseSelectedList">
										<%int RecordCounter = 0;
										String bgcolor = "";%>			
										<logic:iterate id="offenseSelectedListIndex" name="suggestedOrderForm" property="offenseSelectedList">  
										<tr
											class=<%RecordCounter++;
											bgcolor = "alternateRow";
											if (RecordCounter % 2 == 1)
												bgcolor = "normalRow";
											out.print(bgcolor);%>>
											
											<td><bean:write name="offenseSelectedListIndex" property="offenseCodeId" /></td>
											<td><bean:write name="offenseSelectedListIndex" property="description" /></td>
											<td><bean:write name="offenseSelectedListIndex" property="stateCodeNum" /></td>
											<td><bean:write name="offenseSelectedListIndex" property="penalCode" /></td>
											<td><bean:write name="offenseSelectedListIndex" property="level" /></td>
											<td><bean:write name="offenseSelectedListIndex" property="degree" /></td>
										</tr>										
										</logic:iterate>
									</logic:notEmpty>																			
									</table>																		
								</td>
							</tr>
						</logic:notEmpty>
					<!-- END SELECTED OFFENSES SECTION -->						
							<tr><td><img src="/<msp:webapp/>images/spacer.gif"></td></tr>
					<!-- BEGIN SELECT COURTS SECTION -->
							<tr>
								<td class="detailHead" colspan="2">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>										
											<td class="detailHead">&nbsp;<bean:message key="prompt.selectCourtsAndIncludeStandardNonstandard" /></td>
											<td align="right"><img src="/<msp:webapp/>images/step_3.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="2">									
									<table width="100%" border="0" cellpadding="0" cellspacing="0">										
										<tr>
											<td>																	
												<tiles:insert page="../common/courts.jsp" flush="true">
													<tiles:put name="beanName" beanName="suggestedOrderForm" />
													<tiles:put name="mode" value="view" />
												</tiles:insert>
											</td>
										</tr>
									</table>	
									<table width="100%" border="0" cellpadding="2" cellspacing="1">										
										<tr>
											<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.includeConditions" /></td>
											<td class="formDe"><bean:write name="suggestedOrderForm" property="standardLiteral" /></td>	
										</tr>																			
									</table>
								</td>
							</tr>	  	
					<!-- END SELECT COURTS SECTION -->
							<tr><td><img src="/<msp:webapp/>images/spacer.gif"></td></tr>
						</table>
					<!-- BEGIN CONDITIONS SEQUENCE SECTION -->
						<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
							<tr class="detailHead">
								<td>
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td class="detailHead"><bean:message key="prompt.setSupervisionConditionSuggestedOrder" /></td>
											<td align="right"><img src="/<msp:webapp/>images/step_4.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table border="0" align="center" width="100%">
										<tr>
											<td>												
												<table width=100% border=0 cellpadding=4 cellspacing=1 class=borderTable>
													<%int RecordCounter2 = 0;
													String bgcolor = "";%>
													<logic:iterate id="conditionSelectedListIndex" name="suggestedOrderForm" property="conditionSelectedList">
														<logic:notEqual name="conditionSelectedListIndex" property="statusId" value="I" >
															<logic:notEqual name="conditionSelectedListIndex" property="statusId" value="INVALID" >
																<tr
																	class=<%RecordCounter2++;
																	bgcolor = "alternateRow";
																	if (RecordCounter2 % 2 == 1)
																		bgcolor = "normalRow";
																	out.print(bgcolor);%>>
																	<td class="hidden"><bean:write name="conditionSelectedListIndex" property="conditionId" /></td>														
																	<td class="boldText"><bean:write name="conditionSelectedListIndex" property="seqNum" /></td>																									
																	<td class="boldText"><bean:write name="conditionSelectedListIndex" property="standardDesc" /></td>
																	<td><bean:write name="conditionSelectedListIndex" property="conditionLiteral" filter="false"/></td>
																</tr>		
															</logic:notEqual>
														</logic:notEqual>
														
														<logic:equal name="conditionSelectedListIndex" property="statusId" value="I" >
															<tr class="inactiveCondition" title="Inactive Condition">
																<td class="hidden"><bean:write name="conditionSelectedListIndex" property="conditionId" /></td>														
																<td class="boldText"><bean:write name="conditionSelectedListIndex" property="seqNum" /></td>																									
																<td class="boldText"><bean:write name="conditionSelectedListIndex" property="standardDesc" /></td>
																<td><bean:write name="conditionSelectedListIndex" property="conditionLiteral" filter="false"/></td>
															</tr>
														</logic:equal>														
														<logic:equal name="conditionSelectedListIndex" property="statusId" value="INVALID" >
															<tr class="wrongCourtCondition" title="Condition No Longer Applies to This Court">
																<td class="hidden"><bean:write name="conditionSelectedListIndex" property="conditionId" /></td>														
																<td class="boldText"><bean:write name="conditionSelectedListIndex" property="seqNum" /></td>																									
																<td class="boldText"><bean:write name="conditionSelectedListIndex" property="standardDesc" /></td>
																<td><bean:write name="conditionSelectedListIndex" property="conditionLiteral" filter="false"/></td>
															</tr>
														</logic:equal>																																																													
													</logic:iterate>																						
												</table>
												</div>
											</td>											
										</tr>										
									</table>
								</td>
							</tr>
						</table>
						<!-- END CONDITIONS SEQUENCE SECTION -->
						<table width="98%" cellpadding="0" cellspacing="0">
                			<tr>
                  				<td class="legendSmallText">Red <span class="inactiveCondition">conditions</span> signify that the condition is inactive.</td>
                			</tr>
                			<tr>
                  				<td class="legendSmallText">Yellow <span class="wrongCourtCondition">conditions</span> signify that the condition no longer applies to this suggested order's court(s).</td>
                			</tr>
                  		</table>
                  		<br>	
						<!-- BEGIN BUTTON TABLE -->
						<table align="center" width="98%">						
							<tr>
								<td align="center">
									<logic:equal name="suggestedOrderForm" property="action" value="view">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
										<jims2:isAllowed requiredFeatures="CS-ASO-UPDATE">
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.update"></bean:message></html:submit>&nbsp;
										</jims2:isAllowed>
										<jims2:isAllowed requiredFeatures="CS-ASO-COPY">
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.copy"></bean:message></html:submit>&nbsp;
										</jims2:isAllowed>
										<jims2:isAllowed requiredFeatures="CS-ASO-DELETE">
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.delete"></bean:message></html:submit>&nbsp;
										</jims2:isAllowed>
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>											
			        				</logic:equal> 
									<logic:equal name="suggestedOrderForm" property="action" value="delete">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>&nbsp;
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>																
									</logic:equal>									
									<logic:equal name="suggestedOrderForm" property="action" value="confirmCreate">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.backToSearch"></bean:message></html:submit>											
									</logic:equal>
									<logic:equal name="suggestedOrderForm" property="action" value="confirmUpdate">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.backToSearch"></bean:message></html:submit>										
									</logic:equal>
									<logic:equal name="suggestedOrderForm" property="action" value="confirmCopy">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.backToSearch"></bean:message></html:submit>										
									</logic:equal>
									<logic:equal name="suggestedOrderForm" property="action" value="confirmDelete">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.backToSearch"></bean:message></html:submit>										
									</logic:equal>
								</td>								
							</tr>					
						</table>						
					<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>		
		</td>
	</tr>
</table>			
</div>
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>											
						