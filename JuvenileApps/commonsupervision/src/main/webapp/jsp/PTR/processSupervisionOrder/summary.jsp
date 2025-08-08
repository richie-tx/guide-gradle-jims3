<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 12/08/2005	 Hien Rodriguez - Create JSP -->
<!-- 10/02/2006  Hien Rodriguez - ER#35457 Add new field PLEA -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

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
<title><bean:message key="title.heading" /> - processSupervisionOrder/summary.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitSupervisionOrderCreateUpdate" target="content">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  	</tr>
  	<tr>
    	<td valign="top">
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
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
					<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign="top" align=center>
						
						<!-- BEGIN HEADING TABLE -->
							<table width=98%>
								<tr>
									<td align="center" class="header">
										<logic:equal name="supervisionOrderForm" property="action" value="create">
											<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|14">
											<bean:message key="prompt.create" />
										</logic:equal>									
										<logic:equal name="supervisionOrderForm" property="action" value="update">
											<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|20">
											<bean:message key="prompt.update" />
										</logic:equal>												
										<bean:message key="title.supervisionOrder" />&nbsp;-&nbsp;<bean:message key="prompt.summary" />
									</td>
								 </tr>						 						  
							</table>									
						<!-- END HEADING TABLE -->								
						<!-- BEGIN ERROR TABLE -->
							<table width=98% align=center>							
								<tr>
									<td align="center" class="errorAlert"><html:errors></html:errors></td>
								</tr>		
							</table>
						<!-- END ERROR TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td><ul>
										<li>Click the appropriate button below.</li>
									</ul></td>
								</tr>										
							</table>
						<!-- END INSTRUCTION TABLE -->
						<!-- BEGIN DETAIL HEADER TABLE -->
																									
							<tiles:insert page="caseOrderHeaderTile.jsp" flush="true"></tiles:insert>
						
						<!-- END DETAIL HEADER TABLE -->
							<br>
						<!-- BEGIN ORDER PRESENTATION TABLE -->
							<table width="98%" border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
								<tr class=detailHead>
									<td class=paddedFourPix>
										<table width=100% cellpadding=0 cellspacing=0>
											<tr>
												<td width=1%><img border=0 src="/<msp:webapp/>images/expand.gif" name="orderPresentationFields" onclick="showHide('orderPresentationFields', 'row','/<msp:webapp/>')" style="cursor:pointer"></td>
												<td class=detailHead>&nbsp;<bean:message key="prompt.orderPresentation" /></td>
												<td align=right><img src="/<msp:webapp/>images/step_1.gif"></td>
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
							<table width="98%" border="0" cellspacing=0 cellpadding=2 class=borderTableBlue>
								<tr class=detailHead>
									<td>
										<table width=100% cellpadding=2 cellspacing=0>
											<tr>
												<td class=detailHead><bean:message key="prompt.conditions" /></td>
												<td align=right><img src="/<msp:webapp/>images/step_2.gif"></td>
											</tr>
										</table>
									</td>
								</tr>                      
								<tr>
									<td>
										<table border=0 align=center width=100%>
											<tr>
												<td>
													<table width=100% border=0 cellpadding=4 cellspacing=1 class=borderTable>
														<tr class=formDeLabel>
															<td width=1%></td>
															<td><bean:message key="prompt.condition" /></td>
														</tr>
														<%int RecordCounter2 = 0;
														String bgcolor = "";%>
														<logic:iterate id="conditionSelectedListIndex" name="supervisionOrderForm" property="conditionSelectedList">
															 <%String classVal="boldText"; 
																 String title="";
																%>
															<logic:equal name="conditionSelectedListIndex" property="compareToPreviousVersion" value="" >															
																
																<tr
																	class=<%RecordCounter2++;
																	bgcolor = "alternateRow";
																	if (RecordCounter2 % 2 == 1)
																		bgcolor = "normalRow";
																	out.print(bgcolor);%>>
																	<td class="hidden"><bean:write name="conditionSelectedListIndex" property="conditionId" /></td>
																<logic:equal name="conditionSelectedListIndex" property="likeConditionInd" value="true">
																	<%classVal="impactedOrder title='This order has Impacted Orders with Like Conditions'";
																	title=" title='This order has Impacted Orders with Like Conditions'";
																	%>
																</logic:equal>
																<logic:equal name="conditionSelectedListIndex" property="likeConditionInd" value="false">
																	<logic:equal name="conditionSelectedListIndex" property="nonCourtApplicable" value="true">
																	<%
																	title="class=wrongCourtCondition title='Condition does not apply to court(s)'";%>
																	</logic:equal>
																	<logic:equal name="conditionSelectedListIndex" property="status" value="I">
																	<%
																	title="class=inactiveCondition title='Inactive Condition'";%>
																	</logic:equal>
																	
																</logic:equal>
																<td class=<%=classVal%> ><bean:write name="conditionSelectedListIndex" property="sequenceNum" /></td>
																<td <%=title%>>
																<logic:equal name="conditionSelectedListIndex" property="specialCondition" value="true">
																	<bean:write name="conditionSelectedListIndex" property="specialConditionDesc"  filter="false"/>
																</logic:equal>
																	<bean:write name="conditionSelectedListIndex" property="resolvedDescription"  filter="false"/>
																</td>
																</tr>															
															</logic:equal>
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
						<!-- BEGIN SUMMARY OF CHANGES SECTION -->
							<logic:equal name="supervisionOrderForm" property="action" value="update">
							<logic:notEmpty name="supervisionOrderForm" property="summaryOfChanges" >
								<table width="98%" border="0" cellspacing=0 cellpadding=0 class=borderTableBlue>
									<tr class=detailHead>
										<td>
											<table width=100% cellpadding=2 cellspacing=0>
												<tr>
													<td class=detailHead><bean:message key="prompt.summaryOfchanges" /></td>
													<td align=right><img src="/<msp:webapp/>images/step_3.gif"></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<table width=100% cellpadding=4>
												<tr>
													<td class=formDe><bean:write name="supervisionOrderForm" property="summaryOfChanges" /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<br>
								</logic:notEmpty>
							</logic:equal>
						<!-- END SUMMARY OF CHANGES SECTION -->
						
						<!-- BEGIN BUTTON TABLE -->
							<table align=center width="98%">
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
													
<%--                  							<logic:equal name="supervisionOrderForm" property="action" value="create">
--%>
											<logic:equal name="supervisionOrderForm" property="likeConditionInd" value="true">
											<logic:equal name="supervisionOrderForm" property="compared" value="false">
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.compareImpactedOrders"></bean:message></html:submit>&nbsp;
											</logic:equal>
											</logic:equal>
											<logic:equal name="supervisionOrderForm" property="likeConditionInd" value="false">
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>&nbsp;
											</logic:equal>										
											<logic:equal name="supervisionOrderForm" property="compared" value="true">
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>&nbsp;
											</logic:equal>										
										<!--	<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit> -->
<%--												</logic:equal>
--%>
<%--												
										<logic:equal name="supervisionOrderForm" property="action" value="update">                      								
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>&nbsp;
										</logic:equal>
--%>												
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
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

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
                      			