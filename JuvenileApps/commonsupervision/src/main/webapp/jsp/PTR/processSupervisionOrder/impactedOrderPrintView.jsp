<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 12/15/2005	 Hien Rodriguez - Create JSP -->
<!-- 10/02/2006  Hien Rodriguez - ER#35457 Add new field PLEA -->

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
<title><bean:message key="title.heading" /> - processSupervisionOrder/impactedOrderPrintView.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<div align="center"> <br>
							<br>
						<!-- BEGIN AFFECTED ORDERS - LIKE CONDITIONS TABLE -->
							<table width="98%" cellpadding="0" cellspacing="0" class=borderTableBlue>
								<tr>
									<td colspan=3>
										<table width=100% cellpadding=0 cellspacing=0>
											<tr class="detailHead">
												<td class="detailHead"><bean:message key="prompt.affectedOrders" /></td>				                        	
												<td align=right><img src="/<msp:webapp/>images/step_3.gif"></td>				                          
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan=3 align="center">
									  <table width=100% cellpadding=4 cellspacing=1 border=0>
										<!--case numbers-->
											<tr>
												<td colspan=4 class="formDeLabel" align=center><bean:message key="prompt.case#" />:<bean:write name="supervisionOrderForm" property="caseNum" /></td>
												<td class="detailHead"><img src="/<msp:webapp/>images/spacer.gif" width=1></td>
												<td colspan=4 class="formDeLabel"Red align=center><bean:message key="prompt.impacted" /> <bean:message key="prompt.case#" />:<bean:write name="supervisionOrderForm" property="impactedOrder.caseNum" /></td>
											</tr>
											<tr>
										<!--current case-->
												<td class="formDeLabel"><bean:message key="prompt.orderTitle" /></td>
												<td class="formDe"><bean:write name="supervisionOrderForm" property="orderTitle" /></td>
												<td class="formDeLabel"><bean:message key="prompt.version" /></td>
												<td class="formDe"><bean:write name="supervisionOrderForm" property="orderVersion" /></td>
												<td class="detailHead"><img src="/<msp:webapp/>images/spacer.gif" width=1></td>
										<!--impacted case-->
												<td class="formDeLabel"Red><bean:message key="prompt.impacted" /> <bean:message key="prompt.orderTitle" /></td>
												<td class="formDe"><bean:write name="supervisionOrderForm" property="impactedOrder.orderTitle" /></td>
												<td class="formDeLabel"Red><bean:message key="prompt.impacted" /> <bean:message key="prompt.version" /></td>
												<td class="formDe"><bean:write name="supervisionOrderForm" property="impactedOrder.orderVersion" /></td>
											</tr>
										<!--conditions-->
											
											<%int conditionIndex=0;%>
											<logic:iterate id="currentConditionIndex" name="supervisionOrderForm" property="currentOrder.conditions">
											<logic:equal name="currentConditionIndex" property="likeConditionInd" value="true">
												<bean:define id="condOuterTag" type="java.lang.String"> impactedOrder.conditions[<%=conditionIndex%>] </bean:define>
												<bean:define id="condInnerTag" name="supervisionOrderForm" property="<%=condOuterTag%>"/>

												<tr>
													<td class="formDeLabel" align="center"><bean:message key="prompt.conditionLiteral" /></td>
													<td class="formDe" colspan=3 align="center"><bean:write name="currentConditionIndex" property="resolvedDescription"  filter="false"/></td>
													<td class="detailHead"><img src="/<msp:webapp/>images/spacer.gif" width=1></td>
													<td class="formDeLabel"Red align="center"><bean:message key="prompt.impacted" /> <bean:message key="prompt.conditionLiteral" /></td>
													<td class="formDe" colspan=3 align="center"><bean:write name="condInnerTag" property="resolvedDescription"  filter="false"/></td>
												</tr>
												
										<!--variable elements-->
												<%int valueIndex=0;%>
												<logic:iterate id="varElemIndex" name="currentConditionIndex" property="supOrderConditionRelValues">
												<logic:equal name="varElemIndex" property="likeConditionInd" value="true">
													<bean:define id="valueOuterTag" type="java.lang.String"> impactedOrder.conditions[<%=conditionIndex%>].supOrderConditionRelValues[<%=valueIndex%>] </bean:define>
													<nested:define id="valueInnerTag" name="supervisionOrderForm" property="<%=valueOuterTag%>"/>
													<tr>
														<td class="formDeLabel"><bean:write name="varElemIndex" property="name" /></td>
														<td class="formDe" colspan=3><bean:write name="varElemIndex" property="value" /></td>
														<td class="detailHead"><img src="/<msp:webapp/>images/spacer.gif" width=1></td>
														<td class="formDeLabel"Red><bean:write name="valueInnerTag" property="name" /></td>
														<td class="formDe" colspan=3><bean:write name="valueInnerTag" property="value" /></td>
													</tr>
												</logic:equal>  
												<%valueIndex++;%>
												</logic:iterate>    
												
											<!--condition separator-->
												<tr class="detailHead">
													<td colspan=9><img src="/<msp:webapp/>images/spacer.gif" width=1></td>
												</tr>
											</logic:equal>  
											<%conditionIndex++;%>
											</logic:iterate>
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
	   		<input type="button" value="<bean:message key='button.print'/>" onclick="window.print()">		
      		<input type="button" value="<bean:message key='button.close'/>" name="close" onClick="javascript:window.close()">
    	</td>
    	<%--<td align="center">												
			<html:submit property="submitAction"><bean:message key="button.print"></bean:message></html:submit>&nbsp; 
			<html:submit property="submitAction"><bean:message key="button.close"></bean:message></html:submit>
		</td>--%>
	</tr>											
</table>
<!-- END BUTTON TABLE -->

</div>


<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>	
