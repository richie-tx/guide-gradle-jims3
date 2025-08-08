<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/27/2005	 Hien Rodriguez - Create JSP -->
<!-- 01/24/2006  Hien Rodriguez - Implementing interim Back button -->
<!-- 01/18/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

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
<title><bean:message key="title.heading" /> - administerSuggestedOrder/summary.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitSuggestedOrderCreateUpdate" target="content">
<div align="center">

<table width="98%" border="0" cellpadding="0" cellspacing="0" >
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
									<logic:equal name="suggestedOrderForm" property="action" value="create">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|9">
										<bean:message key="prompt.create" />
									</logic:equal>									
									<logic:equal name="suggestedOrderForm" property="action" value="update">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|16">
										<bean:message key="prompt.update" />
									</logic:equal>
									<logic:equal name="suggestedOrderForm" property="action" value="copy">
										<input type="hidden" name="helpFile" value="commonsupervision/aso/suggested_order.htm#|23">
										<bean:message key="prompt.copy" />
									</logic:equal>
									<bean:message key="title.suggestedOrder" /> - <bean:message key="prompt.summary" />
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
										<li>Click Finish to complete the Suggested Order or Back to make changes.</li>
									</ul>
								</td>
							</tr>
						</table>										
					<!-- BEGIN SUGGESTED ORDER SECTION -->
						<table width="98%" border="0" cellspacing="1" cellpadding="4">
							<tr>
								<td class="detailHead" colspan="2">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td width="1%"><a href="javascript:showHideMulti('suggestedOrder', 'so', 2,'/<msp:webapp/>')" ><img border="0" src="/<msp:webapp/>images/expand.gif" name="suggestedOrder"></a></td>
											<td class="detailHead">&nbsp;<bean:message key="prompt.suggestedOrder" /></td>
											<td align="right"><img src="/<msp:webapp/>images/step_1.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="so0" class="hidden">
								<td class="formDeLabel" width="1%"><bean:message key="prompt.name" /></td>
								<td class="formDe"><bean:write name="suggestedOrderForm" property="orderName"/></td>
							</tr>
							<tr id="so1" class="hidden">
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
											<td width="1%"><a href="javascript:showHide('offenses','row','/<msp:webapp/>')"><img border="0" src="/<msp:webapp/>images/expand.gif" name="offenses"></a></td>
											<td class="detailHead">&nbsp;<bean:message key="prompt.selectedOffenses" /></td>
											<td align="right"><img src="/<msp:webapp/>images/step_2.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							<logic:empty name="suggestedOrderForm" property="offenseSelectedList">	
							<tr id="offensesSpan" class="hidden">			
								<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.selectedOffenses" /></td>
								<td class="formDe">None Selected</td>
							</tr>
							</logic:empty>	
							<logic:notEmpty name="suggestedOrderForm" property="offenseSelectedList">	
							<tr id="offensesSpan" class="hidden">
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
											<td width="1%"><a href="javascript:showHide('courts','row','/<msp:webapp/>')"><img border="0" src="/<msp:webapp/>images/expand.gif" name="courts"></a></td>
											<td class="detailHead">&nbsp;<bean:message key="prompt.selectCourtsAndIncludeStandardNonstandard" /></td>
											<td align="right"><img src="/<msp:webapp/>images/step_3.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="courtsSpan" class="hidden">
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
											<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.includeConditions" /></td>
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
									<table border="0" width="100%">
										<tr>
											<td>
											<bean:size id="conditionsCount" name="suggestedOrderForm" property="conditionSelectedList" />	
											<script type="text/javascript">
											renderScrollingArea(<bean:write name="conditionsCount" />);									
											</script>																						
												<table border="0" width="100%" cellpadding="6" cellspacing="1">
													<%int RecordCounter = 0;
													String bgcolor = "";%>
													<logic:iterate id="conditionSelectedListIndex" name="suggestedOrderForm" property="conditionSelectedList">												
														<logic:notEqual name="conditionSelectedListIndex" property="statusId" value="I" >
															<logic:notEqual name="conditionSelectedListIndex" property="statusId" value="INVALID" >
																<tr
																	class=<%RecordCounter++;
																	bgcolor = "alternateRow";
																	if (RecordCounter % 2 == 1)
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
																<td><bean:write name="conditionSelectedListIndex" property="conditionLiteral" />&nbsp; </td>
															</tr>
														</logic:equal>														
														<logic:equal name="conditionSelectedListIndex" property="statusId" value="INVALID" >
															<tr class="wrongCourtCondition" title="Condition No Longer Applies to This Court">
																	<td class="hidden"><bean:write name="conditionSelectedListIndex" property="conditionId" /></td>														
																	<td class="boldText"><bean:write name="conditionSelectedListIndex" property="seqNum" /></td>																									
																	<td class="boldText"><bean:write name="conditionSelectedListIndex" property="standardDesc" /></td>
																	<td><bean:write name="conditionSelectedListIndex" property="conditionLiteral" filter="false"/>&nbsp; </td>
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
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>&nbsp;
									<%--<html:reset><bean:message key="button.reset"></bean:message></html:reset>&nbsp;--%>
									<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>		   															
								</td>
							</tr>					
						</table>						
					<!-- END BUTTON TABLE -->
				</td>
			</tr>
		</table>
</td></tr></table>					

</div>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>											
						