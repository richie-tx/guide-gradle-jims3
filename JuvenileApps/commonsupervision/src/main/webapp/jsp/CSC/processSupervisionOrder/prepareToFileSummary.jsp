<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 12/12/2005	 Hien Rodriguez - Create JSP -->
<!-- 10/02/2006  Hien Rodriguez - ER#35457 Add new field PLEA -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 11/11/2009  C Shimek       - #62440 Revised title from Historical to Pretrial Intervention -->
<!-- 01/11/2010  C Shimek       - #63441 Revised to display Defendant Signature input for Prepare To File page state -->
<!-- 11/05/2010  D Gibler       - #67855 PASO-Additional Signature for CLO/CSO and Judge -->
 
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
<title><bean:message key="title.heading" /> - processSupervisionOrder/prepareToFileSummary.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitSupervisionOrderPrepareToFile" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|24">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  	</tr>
  	<tr>
    	<td valign=top>
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
					<!--tabs start-->
					<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="processOrderTab"/>
					</tiles:insert>		
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
			</table>
			<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
					<%--	<table width=98% border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign=top>
								<!--tabs start-->
									<tiles:insert page="../../common/manageFeaturesTabs.jsp" flush="true">
										<tiles:put name="tabid" value="processOrderTab"/>
									</tiles:insert>	
								<!--tabs end-->
								</td>
							</tr>
							
						</table>
						<table width=98% border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
							</tr>
							<tr>
								<td valign=top align=center>
								--%>
								<!-- BEGIN HEADING TABLE -->
									<table width="98%">
										<tr>
											<td align="center" class="header">
											<logic:equal name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">
												<bean:message key="prompt.pretrialInterventionOrder" />
											</logic:equal>
											<logic:notEqual name="supervisionOrderForm" property="isPretrialInterventionOrder" value="true">
												<bean:message key="title.processSupervisionOrder" />
											</logic:notEqual>
											-&nbsp;<bean:message key="prompt.prepareToFile" />&nbsp;<bean:message key="prompt.summary" />
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
											<td><ul>
												<li>Click Finish to continue.</li>
											</ul></td>
										</tr>									
									</table>
								<!-- END INSTRUCTION TABLE -->
								<!-- BEGIN DETAIL HEADER TABLE -->
																											
									<tiles:insert page="caseOrderHeaderTile.jsp" flush="true"></tiles:insert>
								
								<!-- END DETAIL HEADER TABLE -->
									<br>
								<!-- BEGIN ORDER PRESENTATION TABLE -->
	               <table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
									<tr class="detailHead">
										<td class="paddedFourPix">
											<table width=100% cellpadding=0 cellspacing=0>
												<tr>
													<td width="1%"><img border=0 src="/<msp:webapp/>images/expand.gif" name="orderPresentationFields" onclick="showHide('orderPresentationFields', 'row','/<msp:webapp/>')" style="cursor:pointer"></td>
													<td class="detailHead">&nbsp;<bean:message key="prompt.orderPresentation" /></td>
													<td align="right"><img src="/<msp:webapp/>images/step_1.gif"></td>
												</tr>
											</table>
										</td>
									</tr>
										<tr id="orderPresentationFieldsSpan" class=hidden>
											<td>
															<tiles:insert page="orderPresentationTile.jsp" flush="true"></tiles:insert>
							
							                 </td>							                 
							              </tr>
							         </table>
								<!-- END ORDER PRESENTATION TABLE -->   	
									 <br>
								<!-- BEGIN CONDITIONS SECTION -->
									 <table width="98%" border="0" cellspacing=0 cellpadding=0 class=borderTableBlue>
                      					<tr>
											<td>
												<table width=100% cellpadding=2 cellspacing=0>
													<tr class="detailHead">
														<td width=1%><a href="javascript:showHide('conditions', 'row','/<msp:webapp/>')" ><img border=0 src="/<msp:webapp/>images/expand.gif" name="conditions"></a></td>
														<td class="detailHead">&nbsp;<bean:message key="prompt.conditions" /></td>
														<td align=right><img src="/<msp:webapp/>images/step_2.gif"></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr id="conditionsSpan" class=hidden>
											<td>
												<table border=0 align=center cellpadding=4 cellspacing=1 width=100%>
				                              		<tr>
				                                		<td>
				                                  			<table width=100% border=0 cellpadding=4 cellspacing=1 class=borderTable>
				                                    			<tr>
							                                      	<td class=formDeLabel width=1%></td>
							                                      	<td class=formDeLabel><bean:message key="prompt.condition" /></td>
							                                    </tr>
				                                    			<%int RecordCounter2 = 0;
																String bgcolor = "";%>
																<logic:iterate id="conditionSelectedListIndex" name="supervisionOrderForm" property="conditionSelectedList">
																	<tr
																		class=<%RecordCounter2++;
																		bgcolor = "alternateRow";
																		if (RecordCounter2 % 2 == 1)
																			bgcolor = "normalRow";
																		out.print(bgcolor);%>>
																		<td class="hidden"><bean:write name="conditionSelectedListIndex" property="conditionId" /></td>
																	<logic:equal name="conditionSelectedListIndex" property="likeConditionInd" value="true">
																		<td class=impactedOrderBold title="This is a Like Condition and Impacts other order(s)"/>
																			<%= RecordCounter2 %></td>														
																			<%--<bean:write name="conditionSelectedListIndex" property="seqNum" /></td>--%>
																	</logic:equal>
																	<logic:equal name="conditionSelectedListIndex" property="likeConditionInd" value="false">
																		<td class=boldText><%= RecordCounter2 %></td>
																		<%--<td class=boldText><bean:write name="conditionSelectedListIndex" property="seqNum" /></td>--%>
																	</logic:equal>																																		
																		<%String classVal=""; %>
													<logic:equal name="conditionSelectedListIndex" property="nonCourtApplicable" value="true" >
													  <%classVal=" class=wrongCourtCondition title='Condition No Longer Applies to This Court'"; %>
													</logic:equal>	
													<logic:equal name="conditionSelectedListIndex" property="status" value="I" >
													  <%classVal=" class=inactiveCondition title='Inactive Condition'"; %>
													</logic:equal>
													
																		<td <%=classVal%> ><bean:write name="conditionSelectedListIndex" property="resolvedDescription"  filter="false"/></td>
																	</tr>
				                                    			</logic:iterate>																						
															</table>															
                                						</td>
                              						</tr>
                            					</table>
                            				</td>
                              			</tr>
                            		</table>	
                            	<!-- END CONDITIONS SECTION -->
                            		<br>
                            	<!-- BEGIN ORDER PRINTS DETAIL TABLE -->
                            		<table width="98%" border="0" cellspacing=0 class=borderTableBlue>
                      					<tr class="detailHead">
				                        	<td class="detailHead"><bean:message key="prompt.prepareToFile" /> <bean:message key="prompt.details" /></td>				                        	
											<td align=right><img src="/<msp:webapp/>images/step_3.gif"></td>				                          
				                        </tr>
				                        <tr>
				                          	<td colspan=2>
				                           		<table width="100%" cellpadding="4" cellspacing=1 border=0>
			                      					<tr>			                          	
							                            <td class=formDeLabel nowrap="nowrap"><bean:message key="prompt.CLOCSO" /> <bean:message key="prompt.signedDate" /></td>
							                            <td class="formDe"><bean:write name="supervisionOrderForm" property="signedDateAsString" /></td>
							                        </tr>
							                        <tr>
														<td class="formDeLabel"><bean:message key="prompt.presentedBy" /></td>
														<td class="formDe"><bean:write name="supervisionOrderForm" property="presentedBy" /></td>
													</tr>
			                      					<tr>			                          	
							                            <td class=formDeLabel><bean:message key="prompt.judge" /> <bean:message key="prompt.signedDate" /></td>
							                            <td class="formDe"><bean:write name="supervisionOrderForm" property="judgeSignedDateAsString" /></td>
							                        </tr>
			                              			<tr>
			                                			<td class=formDeLabel nowrap width="1%"><bean:message key="prompt.whoSignedOrder?" /></td>
			                                			<td class=formDe><bean:write name="supervisionOrderForm" property="whoSignedOrder" /></td>
			                              			</tr>
			                              		</table>
			                            	</td>
			                        	</tr>
                            		</table>
                      			<!-- END ORDER PRINTS DETAIL TABLE -->
                      				<br>
                      		    <!--  BEGIN DEFENDANT SIGNATURE TABLE -->  
									<div class="spacer4px"></div>
									<table width="98%" border="0" cellspacing="0" class="borderTableBlue">
										<tr class="detailHead">
											<td class="detailHead"><bean:message key="prompt.defendantSignature" /></td>
										</tr>
										<tr>
											<td colspan="2">
												<table width="100%" cellpadding="2" cellspacing="1" border="0">				                        		
													<tr>				                          	
														<td class="formDeLabel"><bean:message key="prompt.signedDate" /></td>
														<td class="formDe"><bean:write name="supervisionOrderForm" property="defendantSignedDateAsString" /></td>      		
													</tr>
													<tr>
														<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.defendantSignature" /></td>
														<td class="formDe"><bean:write name="supervisionOrderForm" property="defendantSignature" /></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>  
								<!--  END DEFENDANT SIGNATURE TABLE --> 
                      				<br>
                      			<!-- BEGIN BUTTON TABLE -->
                      				<table border="0" width="100%">
								  		<tr>
											<td align=center>			  		
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>&nbsp; 
												<%--<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finishPrint"></bean:message></html:submit>&nbsp;--%>
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
											</td>
										</tr>
									</table>
                      			<!-- END BUTTON TABLE -->
                      			<%--</td>
                  			</tr>
                		</table><br>--%>
               	 	             
		</td>
	</tr>
</table>
</td>
	</tr>
</table>
</div>

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>