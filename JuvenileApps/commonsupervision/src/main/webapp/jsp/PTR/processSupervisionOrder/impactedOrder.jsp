<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 12/15/2005	 Hien Rodriguez - Create JSP -->
<!-- 10/02/2006  Hien Rodriguez - ER#35457 Add new field PLEA -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

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
<title><bean:message key="title.heading" /> - processSupervisionOrder/impactedOrder.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script type="text/javascript">
// Kiran Krishnamurthy
// For PASO Printing functionality. 
function changeFormSettings(theForm, theTargetString, button, theActionString)
{
	changeFormActionURL(theForm, '/<msp:webapp/>'+theActionString,false);
	changeFormTarget(theForm,theTargetString) ;		

	//if the target is not a new window then disable to prevent multiple submissions
	if(theTargetString != 'new') 
	{
		disableSubmit(button, theForm);
	}
	else
	{
		//this has been added for the PrintComparison of Impacted order		
		window.open (theForm.action + "?"+ button.name+"="+button.value, 'newwindow', config='menubar=no, scrollbars=yes, resizable=yes');
		return false;
	}
}


// End of PASO Printing functionality. 
</script>
</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySupervisionOrderCompareImpactedOrder" target="content">
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
					<td valign="top" align="center">
						<!-- BEGIN HEADING TABLE -->
							<table width=98%>
								<tr>
									<td align="center" class="header">
										<logic:equal name="supervisionOrderForm" property="action" value="create">
											<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|37">
											<bean:message key="prompt.create" />
										</logic:equal>									
										<logic:equal name="supervisionOrderForm" property="action" value="update">
											<input type="hidden" name="helpFile" value="commonsupervision/paso/process_adult_supervision_order.htm#|21">
											<bean:message key="prompt.update" />
										</logic:equal>												
										<bean:message key="title.supervisionOrder" />&nbsp;-&nbsp;<bean:message key="title.compareImpactedOrder" />
									</td>
								 </tr>						 						  
							</table>									
						<!-- END HEADING TABLE -->
						<!-- BEGIN ERROR TABLE -->
							<table width=98% align="center">															
								<tr>
									<td align="center" class="errorAlert"><html:errors></html:errors></td>
								</tr>		
							</table>
						<!-- END ERROR TABLE -->
						<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td><ul>
										<li>Click Previous/Next Impacted Order to navigate through Impacted Orders.</li>
										<li>Click Udpate Current Order to go back to change Set Details.</li>
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
								<tr>
									<td>
										<table width=100% cellpadding=0 cellspacing=0>
											<tr class=detailHead>
												<td width=1%><a href="javascript:showHide('conditions', 'row','/<msp:webapp/>')" ><img border=0 src="/<msp:webapp/>images/expand.gif" name="conditions"></a></td>
												<td class=detailHead>&nbsp;<bean:message key="prompt.conditions" /></td>
												<td align=right><img src="/<msp:webapp/>images/step_2.gif"></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="conditionsSpan" class=hidden>
									<td>
										<table border=0 align="center" cellpadding=4 cellspacing=1 width=100%>
											<tr>
												<td>
													<table width=100% border=0 cellpadding=4 cellspacing=1 class=borderTable>
														<tr>
															<td class="formDeLabel" width=1%></td>
															<td class="formDeLabel" width=1%></td>
															<td class="formDeLabel"><bean:message key="prompt.condition" /></td>
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
																<td class=impactedOrder title="This order has Impacted Orders with Like Conditions"/>														
																	<bean:write name="conditionSelectedListIndex" property="sequenceNum" /></td>
															</logic:equal>
															<logic:equal name="conditionSelectedListIndex" property="likeConditionInd" value="false">
																<td class=boldText><bean:write name="conditionSelectedListIndex" property="sequenceNum" /></td>
															</logic:equal>																																		
																<td>&nbsp;</td>
																<td><bean:write name="conditionSelectedListIndex" property="resolvedDescription"  filter="false"/></td>
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
						<!-- BEGIN AFFECTED ORDERS - LIKE CONDITIONS TABLE -->
							<table width="98%" cellpadding="0" cellspacing="0" class=borderTableBlue>
								<tr>
									<td colspan=3>
										<table width=100% cellpadding=0 cellspacing=0>
											<tr class=detailHead>
												<td class=detailHead><bean:message key="prompt.affectedOrders" /></td>				                        	
												<td align=right><img src="/<msp:webapp/>images/step_3.gif"></td>				                          
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan=3 valign="top">
									  <table width=100% cellpadding=4 cellspacing=1 border=0>
										<!--case numbers-->
											<tr>
												<td colspan=4 class="formDeLabel" align="center"><bean:message key="prompt.case#" />:<bean:write name="supervisionOrderForm" property="caseNum" /></td>
												<td class=detailHead><img src="/<msp:webapp/>images/spacer.gif" width=1></td>
												<td colspan=4 class="formDeLabel"Red align="center"><bean:message key="prompt.impacted" /> <bean:message key="prompt.case#" />:<bean:write name="supervisionOrderForm" property="impactedOrder.caseNum" /></td>
											</tr>
											<tr>
										<!--current case-->
												<td class="formDeLabel"><bean:message key="prompt.orderTitle" /></td>
												<td border="0"><bean:write name="supervisionOrderForm" property="orderTitle" /></td>
												<td class="formDeLabel"><bean:message key="prompt.version" /></td>
												<td border="0"><bean:write name="supervisionOrderForm" property="orderVersion" /></td>
												<td class=detailHead><img src="/<msp:webapp/>images/spacer.gif" width=1></td>
										<!--impacted case-->
												<td class="formDeLabel"Red><bean:message key="prompt.impacted" /> <bean:message key="prompt.orderTitle" /></td>
												<td border="0"><bean:write name="supervisionOrderForm" property="impactedOrder.orderTitle" /></td>
												<td class="formDeLabel"Red><bean:message key="prompt.impacted" /> <bean:message key="prompt.version" /></td>
												<td border="0"><bean:write name="supervisionOrderForm" property="impactedOrder.orderVersion" /></td>
											</tr>
										<!--conditions-->
											
											<%int conditionIndex=0;%>
											<logic:iterate id="currentConditionIndex" name="supervisionOrderForm" property="currentOrder.conditions">
											<logic:equal name="currentConditionIndex" property="likeConditionInd" value="true">
												<bean:define id="condOuterTag" type="java.lang.String"> impactedOrder.conditions[<%=conditionIndex%>] </bean:define>
												<bean:define id="condInnerTag" name="supervisionOrderForm" property="<%=condOuterTag%>"/>

												<tr>
													<td class="formDeLabel" valign="top"><bean:message key="prompt.conditionLiteral" /></td>
													<td border="0" colspan=3 valign="top"><bean:write name="currentConditionIndex" property="resolvedDescription"  filter="false"/></td>
													<td class=detailHead><img src="/<msp:webapp/>images/spacer.gif" width=1></td>
													<td class="formDeLabel"Red valign="top"><bean:message key="prompt.impacted" /> <bean:message key="prompt.conditionLiteral" /></td>
													<td border="0" colspan=3 valign="top"><bean:write name="condInnerTag" property="resolvedDescription"  filter="false"/></td>
												</tr>
												
										<!--variable elements-->
												<%int valueIndex=0;%>
												<logic:iterate id="varElemIndex" name="currentConditionIndex" property="supOrderConditionRelValues">
												<logic:equal name="varElemIndex" property="likeConditionInd" value="true">
													<bean:define id="valueOuterTag" type="java.lang.String"> impactedOrder.conditions[<%=conditionIndex%>].supOrderConditionRelValues[<%=valueIndex%>] </bean:define>
													<nested:define id="valueInnerTag" name="supervisionOrderForm" property="<%=valueOuterTag%>"/>
													<tr>
														<td class="formDeLabel"><bean:write name="varElemIndex" property="name" /></td>
														<td border="0" colspan=3><bean:write name="varElemIndex" property="value" /></td>
														<td class="detailHead"><img src="/<msp:webapp/>images/spacer.gif" width=1></td>
														<td class="formDeLabel"Red><bean:write name="valueInnerTag" property="name" /></td>
														<td border="0" colspan=3><bean:write name="valueInnerTag" property="value" /></td>
													</tr>
												</logic:equal>  
												<%valueIndex++;%>
												</logic:iterate>    
												

												
											<!--condition separator-->
												<tr class="detailHead">
													<td colspan="9"><img src="/<msp:webapp/>images/spacer.gif" width=1></td>
												</tr>
											</logic:equal>  
											<%conditionIndex++;%>
											</logic:iterate>

											<tr>
												<td colspan=4 align="center"></td>
												<td class=detailHead></td>
												<td colspan=4 align="center">
											<!-- next previous buttons to page thru all the cases that have the impacts start-->
												<table cellpadding="0" cellspacing="0">
													<tr>
														<td>
															<logic:notEqual name="supervisionOrderForm" property="currImpactedOrderIndex" value="0">
																<html:submit property="submitAction"><bean:message key="button.previousImpactedOrder"/></html:submit>&nbsp;
															</logic:notEqual>   
														</td>
														<td>
															<logic:equal name="supervisionOrderForm" property="lastImpactedOrder" value="false">
																<html:submit property="submitAction"><bean:message key="button.nextImpactedOrder"/></html:submit>&nbsp;
															</logic:equal>   
														</td>
													</tr>
												</table>
												<script>
													if (location.search=="?nextOrder"){
															show("previousOrderButton", 1)
													}
												</script>
											<!-- next previous buttons to page thru all the cases that have the impacts end-->
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						<!-- END AFFECTED ORDERS - LIKE CONDITIONS TABLE -->
							<br>                     
						<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">											
								<tr>
									<td align="center">
										<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'displaySupervisionOrderCompareImpactedOrder.do');"><bean:message key="button.back"/></html:submit>&nbsp;
										<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'displaySupervisionOrderCompareImpactedOrder.do');"><bean:message key="button.next"></bean:message></html:submit>&nbsp;
										<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'displaySupervisionOrderCompareImpactedOrder.do');"><bean:message key="button.updateCurrentOrder"></bean:message></html:submit>&nbsp;  
										<html:submit property="submitAction" onclick="return changeFormSettings(this.form, 'new', this, 'displaySupervisionOrderCompareImpactedOrder.do');"><bean:message key="button.printComparisonOfImpactedOrder"></bean:message></html:submit>&nbsp; 
										<html:submit property="submitAction" onclick="changeFormSettings(this.form, '', this, 'displaySupervisionOrderCompareImpactedOrder.do');"><bean:message key="button.cancel"></bean:message></html:submit>
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
