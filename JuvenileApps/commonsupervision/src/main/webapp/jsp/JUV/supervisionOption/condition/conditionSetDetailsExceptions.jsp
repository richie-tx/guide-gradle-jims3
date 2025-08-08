<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<!-- 03/03/2006  C Shimek  	   - ER#28542 add disable button to next button -->
<!-- 03/31/2006  Hien Rodriguez  defect#300113 display correct labels for each agency -->
<!-- 01/19/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. --> 
<!-- 11/26/2007  Clarence Shimek - defect#47334 remove extra spaces in heading (merge removed this correction made under defedt39326) -->

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
<title><bean:message key="title.heading" /> - conditionSetDetailsException.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
</head>

<bean:define id="conditionGroup1Caption" type="java.lang.String" value="prompt.category"/>
<bean:define id="conditionGroup2Caption" type="java.lang.String" value="prompt.type"/>
<bean:define id="conditionGroup3Caption" type="java.lang.String" value="prompt.subtype"/>

<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<nested:form action="/displaySupervisionConditionSummary">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src=/<msp:webapp/>images/spacer.gif height=5></td>
  	</tr>
  	<tr>
    	<td valign="top">
				<table width=100% border="0" cellpadding="0" cellspacing="0" >
					<tr>
						<td valign="top">
						<!--tabs start-->
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true"/>
						<!--tabs end-->
						</td>
					</tr>
					<tr>
					  	<td bgcolor=#6699FF><img src=/<msp:webapp/>images/spacer.gif height=5></td>
					</tr>
				</table>
				<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					<tr>
					  	<td><img src=/<msp:webapp/>images/spacer.gif height=5></td>
					</tr>
					<tr>
						<td valign="top" align=center>
							
							<!-- BEGIN HEADING TABLE -->
							<table width=100%>
								<tr>		
									<td align="center" class="header">
										<logic:equal name="supervisionConditionForm" property="action" value="copy">
											<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|89">
											<bean:message key="prompt.copy" />
											<bean:message key="title.supervisionCondition" />&nbsp;-&nbsp;<bean:message key="title.setExceptions" />
										</logic:equal>
										
										<logic:equal name="supervisionConditionForm" property="action" value="create">
											<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|11">
											<bean:message key="prompt.create" />
											<bean:message key="title.supervisionCondition" />&nbsp;-&nbsp;<bean:message key="title.setExceptions" />
										</logic:equal>
										
										<logic:equal name="supervisionConditionForm" property="action" value="update">
											<logic:notEqual name="supervisionConditionForm" property="inUse" value="true">
												<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|80">
												<bean:message key="prompt.update" />
												<bean:message key="title.supervisionCondition" />&nbsp;-&nbsp;<bean:message key="title.setExceptions" />												
											</logic:notEqual>
											<logic:equal name="supervisionConditionForm" property="inUse" value="true">
												<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|85">
												<bean:message key="prompt.update" />
												<bean:message key="prompt.inUse" />
												<bean:message key="title.supervisionCondition" />&nbsp;-&nbsp;<bean:message key="title.setExceptions" />												
											</logic:equal>
										</logic:equal>
									</td>
							  	</tr>
							</table>
							<!-- END HEADING TABLE -->
							
							<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<td>
										<ul>
											<li>Enter the required fields </li>
											<li>Click Next.</li>
										</ul>
									</td>
								</tr>
							</table>
							
							<!-- BEGIN  TABLE -->
							<tiles:insert page="conditionInfoSec1Tile.jsp" flush="true">
						</tiles:insert>	
							
							<br>
							
							<!--task info start-->
								<tiles:insert page="../../../common/taskListTile.jsp" flush="true">
										<tiles:put name="taskConfig" beanName="supervisionConditionForm" beanProperty="tasks"/>
									</tiles:insert>	
								<!--task info end-->
							
						<br>
							<table width="98%" border="0" cellpadding=4 cellspacing=0 class=borderTableBlue>
								<tr>
									<td class=detailHead>
										<table width=100% cellpadding=0 cellspacing=0>
											<tr>
												<td width=1%><a href="javascript:showHide('courts','row', '/<msp:webapp/>')" ><img border=0 src="/<msp:webapp/>images/contract.gif" name="courts"></a></td>
												<td class=detailHead>&nbsp;<bean:message key="prompt.selectedCourts" /></td>
												<td align=right><img src="/<msp:webapp/>images/step_3.gif" vspace=0></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="courtsSpan" class="visibleTR">
									<td>
										<tiles:insert page="../../../common/courts.jsp" flush="true">
											<tiles:put name="beanName" beanName="supervisionConditionForm" />
											<tiles:put name="mode" value="view" />
											<tiles:put name="displayDDNA" value="true" />
										</tiles:insert>
									</td>
								</tr>
							</table>

						<br>
						<logic:notEmpty name="supervisionConditionForm" property="variableElementResponseEvents">
						
						<bean:size id="varElementRESize" name="supervisionConditionForm" property="variableElementResponseEvents"/>
						
						<table width="98%" border="0" cellspacing=1 cellpadding=4>
							<tr>
								<td colspan=2 class=detailHead>
									<table width=100% cellpadding=0 cellspacing=0>
										<tr>
											<td width=1%><a href="javascript:showHideMulti('setDetails', 'sd', '<bean:write name="varElementRESize"/>', '/<msp:webapp/>')" ><img border=0 src="/<msp:webapp/>images/contract.gif" name="setDetails"></a></td>
											<td class=detailHead>&nbsp;<bean:message key="prompt.setDetailsForSelectedCourts" /></td>
											<td align=right><img src="/<msp:webapp/>images/step_4.gif" vspace=0></td>
										</tr>
									</table>
								</td>
							</tr>
						  
							<logic:iterate indexId="varElementRECount" id="varElementREIter" name="supervisionConditionForm" property="variableElementResponseEvents">						  
								<logic:equal name="varElementREIter" property="reference" value="false">
									<logic:notEmpty name="varElementREIter" property="name">
									  <tr id="sd<bean:write name='varElementRECount'/>" class="visibleTR">
										<td class=formDeLabel nowrap width=1%><bean:write name="varElementREIter" property="name"/></td>
										<td class=formDe>
											<bean:write name="varElementREIter" property="value"/>
											<logic:equal name="varElementREIter" property="fixed" value="true">
												<bean:message key="prompt.fixed" />
											</logic:equal>
											<logic:equal name="varElementREIter" property="fixed" value="false">
												<bean:message key="prompt.variable" />
											</logic:equal>
										</td>
											
									  </tr>
									</logic:notEmpty>
								</logic:equal>	
							</logic:iterate>
						 
							<tr><td><br></td></tr>
							<tr><td class=required colspan=2><bean:message key="prompt.4.diamond"/><bean:message key="prompt.requiredForThisSection"/></td></tr>
							<tr>
								<td colspan=2 class=detailHead>
									<table width=100% cellpadding=0 cellspacing=0>
										<tr>
											<td class=detailHead><bean:message key="prompt.exceptions" /> - <bean:message key="prompt.setDetails" /></td>
											<td align=right><img src="/<msp:webapp/>images/step_5.gif" vspace=0></td>
										</tr>
									</table>
								</td>
							</tr>
							
						  <% boolean dateCounter=true; %>
							<nested:iterate name="supervisionConditionForm" property="ECVE">
							
								<tr>
									<td class=boldText colspan=2><bean:message key="prompt.court" />&nbsp;<nested:write property="courtNumber"/></td>
								</tr>
								
								 <% int anchorCounter=0; %>
								<nested:iterate  id="vere" property="variableElements">
								<% anchorCounter++; %>
									<nested:equal property="reference" value="false">
										<nested:notEmpty property="name">
											<tr>
												<td class=formDeLabel nowrap width=1%><nested:write property="name" /></td>
												<td class=formDe>
												<bean:define id="fieldName" type="java.lang.String"><nested:write property="name"/></bean:define>
													
													<nested:equal property="enumeration" value="true">
														<nested:equal property="enumerationTypeId" value="L">
															<nested:select property="valueId" size="1">
																<html:option value=""><bean:message key="select.generic" /></html:option>
																<nested:optionsCollection property="codeValues" value="code" label="description" /> 
															</nested:select>																
														</nested:equal>	
														<nested:equal property="enumerationTypeId" value="R">
															<nested:select property="fixed" size="1">
																<html:option value="true">Fixed</html:option>
																<html:option value="false">Variable</html:option>
															</nested:select>
															<logic:iterate id="cdVals" name="vere" property="codeValues">
																<tr>
																	<td colspan=2>
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
								      		 	<%}
													dateCounter=false;
													 %>
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
												addAlphaNumericnSpaceValidation('<nested:writeNesting property="value"/>','<bean:message key="errors.alphanumeric" arg0="<%=fieldName%>"/>');
											</script>
										</nested:equal>
										<nested:equal property="enumerationTypeId" value="A">
											<script>
												addAlphaValidation('<nested:writeNesting property="value"/>','<bean:message key="errors.alphabetic" arg0="<%=fieldName%>"/>');
											</script>
										</nested:equal>
										<nested:equal property="enumerationTypeId" value="F">
											<script>
												addDB2FreeTextValidation('<nested:writeNesting property="value"/>','<bean:message key="errors.freeTextDB2" arg0="<%=fieldName%>"/>');
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
											    <A HREF="#" onClick="cusVarCal.select(((document.getElementsByName('<nested:writeNesting property="value"/>'))[0]),'anchor<%=anchorCounter%>','MM/dd/yyyy'); return false;" NAME="anchor<%=anchorCounter%>" ID="anchor<%=anchorCounter%>" border="0"><bean:message key="prompt.4.calendar"/></A>
										</nested:equal>
										
										
									</nested:equal>
												
													<!-- <nested:text property="value"/> -->
													
													<nested:notEqual property="enumerationTypeId" value="R">
														<nested:select property="fixed" size="1">
															<html:option value="true">Fixed</html:option>
															<html:option value="false">Variable</html:option>
														</nested:select>
													</nested:notEqual>	
												</td>
											</tr>
											<br>
										</nested:notEmpty>
									</nested:equal>
								</nested:iterate>
							</nested:iterate>
						</table>
						<br>
						</logic:notEmpty>
						
						<br>
						<!-- association should not show up during create -->
						<logic:notEqual name="supervisionConditionForm" property="action" value="<%=UIConstants.CREATE%>"> 
							<table width="98%" border="0" cellspacing="0" cellpadding="2">
								<tr>
									<td>
							<!-- Associations -->
							<tiles:insert page="../../../common/conditionAssociationsView.jsp" flush="true">
								<tiles:put name="associatedCourtPolicies" beanName="supervisionConditionForm" beanProperty="associatedCourtPolicies"/>
								<tiles:put name="associatedDepartmentPolicies" beanName="supervisionConditionForm" beanProperty="associatedDeptPolicies"/>
							</tiles:insert>
							<br>
							</td>
							</tr>
							</table>
						</logic:notEqual>
						
						
						<logic:notEmpty name="supervisionConditionForm" property="removedAssociations">
							<table width="98%" border="0" cellspacing="0" cellpadding="2">
								<tr>
									<td>
										<!-- Associations -->
											<tiles:insert page="../../../common/removedAssociationsView.jsp" flush="true">
												<tiles:put name="removedAssociations" beanName="supervisionConditionForm" beanProperty="removedAssociations"/>
												<tiles:put name="associationType" value="condition"/>
												<tiles:put name="group2Caption" value="prompt.type" />
												<tiles:put name="group3Caption" value="prompt.subtype" />
											</tiles:insert>
									</td>
								</tr>
							</table>
							<br>
						</logic:notEmpty>
						
							
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
						  	<tr>
						    	<td align="center">
						      	<input type="button" value="Back" name="return" onClick="history.go(-1)">
						      	<html:submit property="submitAction" onclick="return ( validateCustomStrutsBasedJS(this.form)&& validateCourtsVariableElements(this.form) && disableSubmit(this, this.form))"><bean:message key="button.next"/></html:submit>
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
		</td>
	</tr>
</table>

<!-- END  TABLE -->
</div>
<br>
<!-- BEGIN NOTES TABLE -->
<%-- <table width="100%">
 <tr>
      <td align="left" class="subhead">Notes:</td>
  </tr>
 <tr>
    <td>
	<ol>
	  <li>If "Fixed" is selected in the Set Details area, the input field corresponding to it is required</li>
	 
	</td>
  </tr>
</table> --%>
<!-- END NOTES TABLE -->
</nested:form>

<div align=center>[<a href=#top>Back to Top</a>]</div></body>
</html:html>
