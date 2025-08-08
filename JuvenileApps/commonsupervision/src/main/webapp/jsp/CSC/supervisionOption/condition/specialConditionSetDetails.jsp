<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<!-- 03/03/2006  C Shimek  	   - ER#28542 add disable button to next button -->
<!-- 01/19/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 11/26/2007  Clarence Shimek - defect#47334 remove extra spaces in heading -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@page import="naming.UIConstants"%>

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
<title><bean:message key="title.heading" /> - conditionSetDetails.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySupervisionConditionSummary">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  	</tr>
  	<tr>
    	<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--tabs start-->
							<!--tabs start-->
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true"/>
						<!--tabs end-->
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					  <td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
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
									<logic:equal name="supervisionConditionForm" property="action" value="copy">
										<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|34">
										<bean:message key="prompt.copy" />
									<bean:message key="prompt.special" />
									<bean:message key="prompt.supervisionCondition" />&nbsp;-&nbsp;<bean:message key="title.setDetails" />

									</logic:equal>
									
									<%-- Special Condition only create from PASO
									<logic:equal name="supervisionConditionForm" property="action" value="create">
										<bean:message key="prompt.create" />
									<bean:message key="prompt.special" />
									<bean:message key="prompt.supervisionCondition" />&nbsp;-&nbsp;<bean:message key="title.setDetails" />

									</logic:equal>--%>
									
									<logic:equal name="supervisionConditionForm" property="action" value="update">
										<logic:notEqual name="supervisionConditionForm" property="inUse" value="true">
											<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|24">
											<bean:message key="prompt.update" />
											<bean:message key="prompt.special" />
											<bean:message key="prompt.supervisionCondition" />&nbsp;-&nbsp;<bean:message key="title.setDetails" />
										</logic:notEqual>
										<logic:equal name="supervisionConditionForm" property="inUse" value="true">
											<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|23">
											<bean:message key="prompt.update" />
											<bean:message key="prompt.inUse" />
											<bean:message key="prompt.special" />
											<bean:message key="prompt.supervisionCondition" />&nbsp;-&nbsp;<bean:message key="title.setDetails" />
										</logic:equal>
									</logic:equal>
								</td>
							  </tr>
							</table>
							<!-- END HEADING TABLE -->
							<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
							   	<tr>
							     	<td><ul>
					        			<li>Enter the required fields </li>
										<li>Select Next.</li>
									</ul></td>
							  	</tr>
							  	<tr>
								<td class="required">
									<bean:message key="prompt.4.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/>
								</td>
							</tr>
							</table>
							<!-- BEGIN  TABLE -->
							<table width="98%" border="0" cellspacing=0 cellpadding=0>
								<tr>
									<td>
										<tiles:insert page="conditionSpecialTile.jsp" flush="true"></tiles:insert>
									 
									</td>
								</tr>
								<tr><td><br></td></tr>
							</table>							
							<logic:notEmpty name="supervisionConditionForm" property="variableElementResponseEventsArray">
								<table width="98%" border="0" cellspacing=0 cellpadding=0>
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td colspan="2" class="detailHead">
														<table width="100%" cellpadding="0" cellspacing=0>
															<tr>
																<td class="detailHead">&nbsp;<bean:message key="prompt.setDetails" /></td>
																<td align="right"><img src="/<msp:webapp/>images/step_3.gif"></td>
															</tr>
														</table>
													</td>
												</tr>
												 <% boolean dateCounter=true; %>
												<nested:iterate id="vere" name="supervisionConditionForm" property="variableElementResponseEventsArray">
													
													<nested:notEmpty property="name">	
													<bean:define id="fieldName" type="java.lang.String"><nested:write property="name"/></bean:define>
														<tr>
															<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.4.diamond"/><nested:write property="name"/></td>
															
															<td class="formDe">
																<nested:equal property="enumeration" value="true">
																	<nested:equal property="enumerationTypeId" value="L">
																		<nested:select property="valueId" size="1">
																			<html:option value=""><bean:message key="select.generic" /></html:option>
																			<nested:optionsCollection property="codeValues" value="code" label="description" /> 
																		</nested:select>																
																	</nested:equal>
																	<nested:equal property="enumerationTypeId" value="R">
																		<logic:iterate id="cdVals" name="vere" property="codeValues">
																			<tr>
																				<td colspan="2">
																					<bean:define name="cdVals" id="valId" property="code" type="java.lang.String" />
																					<nested:radio property="valueId" value="<%=valId%>" /> <bean:write name="cdVals" property="description" /> 
																				</td>	
																			</tr>	
																		</logic:iterate>	
																	</nested:equal>
																</nested:equal>
																<nested:equal property="enumeration" value="false">
																	<nested:equal property="enumerationTypeId" value="D">
											<script>
											 	addMMDDYYYYDateValidation('<nested:writeNesting property="value"/>','<bean:message key="errors.date" arg0="<%=fieldName%>"/>');
													<% if(dateCounter){ %>
													var cusVarCal = new CalendarPopup();
								      		 	cusVarCal.showYearNavigation();
								      		 	<%}dateCounter=false; %>
											</script>
										</nested:equal>
										<nested:equal property="enumerationTypeId" value="C">
											<script>
												addCurrencyValidation('<nested:writeNesting property="value"/>','<bean:message key="errors.currency" arg0="<%=fieldName%>"/>');
											</script>
											$
										</nested:equal>
										<nested:equal property="enumerationTypeId" value="Q">
											<script>
											addAlphaNumericnSpacewSymbolsValidation('<nested:writeNesting property="value"/>','<bean:message key="errors.alphanumeric" arg0="<%=fieldName%>"/>');
											</script>
										</nested:equal>
										<nested:equal property="enumerationTypeId" value="A">
											<script>
												addAlphaValidation('<nested:writeNesting property="value"/>','<bean:message key="errors.alphabetic" arg0="<%=fieldName%>"/>');
											</script>
										</nested:equal>
										<nested:equal property="enumerationTypeId" value="F">
											<script>
												addDB2FreeTextValidation('<nested:writeNesting property="value"/>','<bean:message key="errors.comments" arg0="<%=fieldName%>"/>');
											</script>
										</nested:equal>
										
										<nested:equal property="enumerationTypeId" value="N">
											<script>
												addNumericValidation('<nested:writeNesting property="value"/>','<bean:message key="errors.numeric" arg0="<%=fieldName%>"/>');
											</script>
										</nested:equal>
										<nested:equal property="enumerationTypeId" value="T">
											<nested:equal property="valueType" value="TIME_12HOUR">
												<script>
													add12HrTimeValidation('<nested:writeNesting property="value"/>','<bean:message key="errors.12HourTime" arg0="<%=fieldName%>"/>');
												</script>
											</nested:equal>
											<nested:notEqual property="valueType" value="TIME_12HOUR">
												<script>
													add24HrTimeValidation('<nested:writeNesting property="value"/>','<bean:message key="errors.24HourTime" arg0="<%=fieldName%>"/>');
												</script>
											</nested:notEqual>
										</nested:equal>
										
										<nested:text property="value"/>
										
										<nested:equal property="enumerationTypeId" value="D">
											    <A HREF="#" onClick="cusVarCal.select(((document.getElementsByName('<nested:writeNesting property="value"/>'))[0]),'anchor<nested:write property="name"/>','MM/dd/yyyy'); return false;" NAME="anchor<nested:write property='name'/>" ID="anchor<nested:write property='name'/>" border="0"><bean:message key="prompt.4.calendar"/></A>
										</nested:equal>
																</nested:equal>
															</td>
														</tr>
													</nested:notEmpty>
													<nested:empty property="name">	
													<tr>
															<td class=formDeLabel style="padding:4px" nowrap width=1%>No Details To Set</td>
															
													</tr>
												</nested:empty>
												</nested:iterate>
												
											</table>
										</td>
									</tr>
								</table>				
							<br>		
							</logic:notEmpty>		
							
								
						<bean:size id="vereSize" name="supervisionConditionForm" property="variableElementResponseEvents"/>

						<!-- BEGIN BUTTON TABLE -->
						<br>
						<table border="0" width="100%">
						  	<tr>
						    	<td align="center">
						      		<input type="button" value="Back" name="return" onClick="history.go(-1)">
						      			<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
						      				<html:submit property="submitAction" onclick="return validateVariableElements(this.form,false)  && disableSubmit(this, this.form);"><bean:message key="button.next"/></html:submit>
						      			</logic:notEqual>
						      			<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
						      				<html:submit property="submitAction" onclick="return validateVariableElements(this.form,true) && validateCustomStrutsBasedJS(this.form) && disableSubmit(this, this.form);"><bean:message key="button.next"/></html:submit>
						      			</logic:equal>
						      		<input type="reset" value="Reset" name="reset">

									<input type=button value='<bean:message key="button.cancel" />' 
										<logic:equal name="supervisionConditionForm" property="action" value="<%=UIConstants.CREATE%>"> 
											onclick="javascript:changeFormActionURL(this.form.name, '/<msp:webapp/>displaySupervisionConditionCreate.do', false) && disableSubmit(this, this.form)"
										</logic:equal>
										<logic:notEqual name="supervisionConditionForm" property="action" value="<%=UIConstants.CREATE%>"> 
											onclick="javascript:changeFormActionURL(this.form.name, '/<msp:webapp/>displaySupervisionConditionSearch.do', false) && disableSubmit(this, this.form)"
										</logic:notEqual>
									>
						    	</td>
						  	</tr>
						</table>
						<!-- END BUTTON TABLE -->
						
			</td>
		</tr>
	</table>
<!--</td>
</tr>
</table> -->
<!-- END  TABLE -->
</div>

<br>

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
