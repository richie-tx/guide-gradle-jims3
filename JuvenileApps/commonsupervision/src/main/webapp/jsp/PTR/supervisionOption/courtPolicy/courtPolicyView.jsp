<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<!-- 01/18/2006  Hien Rodriguez  JIMS200027598 - Modify all Titles & Labels for JUV & PTR agencies -->
<!-- 01/25/2006  Clarence Shimek Defect #27634 - Modify to not display Event Type, Event Count and Period fields if agency = JUV -->
<!-- 03/06/2006  Clarence Shimek ER#28542 add disable button to copy, update and deletet button -->
<!-- 03/31/2006  Hien Rodriguez  defect#300113 display correct labels for each agency --> 
<!-- 06/28/2006  Hien Rodriguez - defect#32504 wrap security tag around Update Association button -->
<!-- 01/22/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 11/01/2007  Clarence Shimek defect#39326 removed extra spaces from heading --> 

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.UIConstants" %>
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
<title><bean:message key="title.heading" /> - courtPolicyView.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<bean:define id="courtPolicyTitle" name="courtPolicyForm" property="courtPolicyTitle" type="java.lang.String"/>
<bean:define id="courtGroup1Caption" name="courtPolicyForm" property="courtGroup1Caption" type="java.lang.String"/>
<bean:define id="courtGroup2Caption" name="courtPolicyForm" property="courtGroup2Caption" type="java.lang.String"/>
<bean:define id="courtGroup3Caption" name="courtPolicyForm" property="courtGroup3Caption" type="java.lang.String"/>
<bean:define id="courtPolicyLiteralCaption" name="courtPolicyForm" property="courtPolicyLiteralCaption" type="java.lang.String"/>
<bean:define id="conditionGroup2Caption" name="courtPolicyForm" property="conditionGroup2Caption" type="java.lang.String"/>
<bean:define id="conditionGroup3Caption" name="courtPolicyForm" property="conditionGroup3Caption" type="java.lang.String"/>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/handleCourtPolicySelection" target="content">
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
						<tiles:insert page="../../../common/commonSupervisionTabs.jsp" flush="true"/>
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
						<table width=100%>
						  <tr>
							<td align="center" class="header">
								<logic:notEqual name="courtPolicyForm" property="inUse" value="true">
									<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|50">
									<bean:message key="title.view" />
									<bean:message key="<%=courtPolicyTitle%>"/>
								</logic:notEqual>
								
								<logic:equal name="courtPolicyForm" property="inUse" value="true">
									<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|11">
									<bean:message key="title.view" />
									<bean:message key="prompt.inUse" />
									<bean:message key="<%=courtPolicyTitle%>"/>
								</logic:equal>
							</td>
						  </tr>
						</table>
						<!-- END HEADING TABLE -->
						
						<!-- BEGIN  TABLE -->
						<table width="98%" border="0" cellspacing=1 cellpadding=4>
							<tr>
								<td colspan=2 class="detailHead">
									<table width=100% cellpadding=0 cellspacing=0>
										<tr>
											<td class="detailHead"><bean:message key="<%=courtPolicyTitle%>"/></td>
											<td align=right><img src="/<msp:webapp/>images/step_1.gif"
															vspace=0></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td width=1% nowrap class="formDeLabel"><bean:message key="prompt.name" /></td>
								<td class="formDe"><bean:write name="courtPolicyForm" property="courtPolicyName" /></td>
							</tr>
							<tr>
								<td class="formDeLabel" width=1% nowrap><bean:message key="<%=courtGroup1Caption%>"/></td>
								<td class="formDe"><bean:write name="courtPolicyForm" property="group1Name" /></td>
							</tr>
							<tr>
								<td class="formDeLabel" width=1% nowrap><bean:message key="<%=courtGroup2Caption%>"/></td>
								<td class="formDe"><bean:write name="courtPolicyForm" property="group2Name" /></td>
							</tr>
							<tr>
								<td class="formDeLabel" width=1% nowrap><bean:message key="<%=courtGroup3Caption%>"/></td>
								<td class="formDe"><bean:write name="courtPolicyForm" property="group3Name" /></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.standard" /></td>
								<td class="formDe"><logic:equal name="courtPolicyForm" property="standard" value="true">
												Standard</logic:equal>
												<logic:notEqual name="courtPolicyForm" property="standard" value="true">
												Non Standard</logic:notEqual></td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap><bean:message key="prompt.policySource" /></td>
								<td class="formDe">
									<logic:equal name="courtPolicyForm" property="departmentPolicy" value="true">
										<bean:message key="prompt.department"/>
									</logic:equal>
									<logic:notEqual name="courtPolicyForm" property="departmentPolicy" value="true">
										<bean:message key="prompt.court"/>
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel" valign="top" width=1% nowrap><bean:message key="<%=courtPolicyLiteralCaption%>"/></td>
								<td class="formDe">
									<bean:write name="courtPolicyForm" property="courtPolicyLiteral"  filter="false"/>
								</td>
							</tr>								
							<tr>
								<td class="formDeLabel" nowrap><bean:message key="prompt.effectiveDate" /></td>
								<td class="formDe"><bean:write name="courtPolicyForm" property="effectiveDate" /></td>
							</tr>
							
							<logic:notEmpty name="courtPolicyForm" property="inactiveDate" >
									<logic:notEqual name="courtPolicyForm" property="inactiveDate" value="">
									<tr>
										<td class="formDeLabel" width=1% nowrap><bean:message key="prompt.inactiveDate" /></td>
										<td class="formDe"><bean:write name="courtPolicyForm" property="inactiveDate" /></td>
									</tr>
									</logic:notEqual>
									</logic:notEmpty>
							
							<logic:notEqual name="courtPolicyForm" property="agencyId" value="<%= UIConstants.JUV %>">
								<tr>
									<td class="formDeLabel" nowrap><bean:message key="prompt.eventType" /></td>
									<td class="formDe"><bean:write name="courtPolicyForm" property="selectedEventTypes" /></td>
								</tr>
								<tr>
									<td class="formDeLabel" nowrap><bean:message key="prompt.eventCount" /></td>
									<td class="formDe"><bean:write name="courtPolicyForm" property="eventCountDesc" /></td>
								</tr>
								<tr>
									<td class="formDeLabel" nowrap><bean:message key="prompt.period" /></td>
									<td class="formDe">
														<logic:notEmpty name="courtPolicyForm" property="periodValue">
														<logic:notEqual name="courtPolicyForm" property="periodValue" value="0">
														<bean:write name="courtPolicyForm" property="periodValue"/> <bean:write name="courtPolicyForm" property="period"/>
														</logic:notEqual>
														</logic:notEmpty>
													</td>
								</tr>
							</logic:notEqual>									
							<tr>
								<td class="formDeLabel" nowrap><bean:message key="prompt.notes" /></td>
								<td class="formDe"><bean:write name="courtPolicyForm" property="notes" /></td>
							</tr>
							</table>
								<br>
						<!--task info start-->
						<tiles:insert page="../../../common/taskListTile.jsp" flush="true">
										<tiles:put name="taskConfig" beanName="courtPolicyForm" beanProperty="tasks"/>
									</tiles:insert>	
								<!--task info end-->
						<br>
						
						<table width="98%" border="0" cellpadding=4 cellspacing=0 class=borderTableBlue>
							<tr>
								<td class="detailHead">
									<table width=100% cellpadding=0 cellspacing=0>
										<tr>
											<td width=1%><a href="javascript:showHide('courts','row', '/<msp:webapp/>')" ><img border=0 src="/<msp:webapp/>images/contract.gif" name="courts"></a></td>
											<td class="detailHead">&nbsp;<bean:message key="prompt.selectedCourts" /></td>
											<td align=right><img src="/<msp:webapp/>images/step_3.gif"
															vspace=0></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="courtsSpan" class="visibleTR">
								<td>
									<tiles:insert page="../../../common/courts.jsp" flush="true">
										<tiles:put name="beanName" beanName="courtPolicyForm" />
										<tiles:put name="mode" value="view" />
										<tiles:put name="displayDDNA" value="true" />
									</tiles:insert>
								</td>
							</tr>
						</table>
						<br>
						
						<logic:notEmpty name="courtPolicyForm" property="variableElementResponseEvents">
						<bean:size id="varElementRESize" name="courtPolicyForm" property="variableElementResponseEvents"/>
						
							
						<table width="98%" border="0" cellspacing=1 cellpadding=4>
							<tr>
									<td colspan=2 class="detailHead">
										<table width=100% cellpadding=0 cellspacing=0>
											<tr>
												<td class="detailHead"><bean:message key="prompt.setDetailsForSelectedCourts" /></td>
												<td align=right><img src="/<msp:webapp/>images/step_4.gif"
															vspace=0></td>
											</tr>
										</table>
									</td>
							</tr>
						  
						  <logic:iterate indexId="varElementRECount" id="varElementREIter" name="courtPolicyForm" property="variableElementResponseEvents">						  
								<logic:equal name="varElementREIter" property="reference" value="false">
									<logic:notEmpty name="varElementREIter" property="name">
										<tr id="sd<bean:write name='varElementRECount'/>" class="visibleTR">
											<td class="formDeLabel" nowrap width=1%><bean:write name="varElementREIter" property="name"/></td>
											<td class="formDe">
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
						</table>
						<br>
						</logic:notEmpty>
						
						<table width="98%" border="0" cellspacing=1 cellpadding=4>
						<tr><td><br></td></tr>
							<tr>
								<td colspan=2 class="detailHead">
									<table width=100% cellpadding=0 cellspacing=0>
										<tr>
											<td class="detailHead"><bean:message key="prompt.exceptions" /> - <bean:message key="prompt.setDetails" /></td>
											<td align=right><img src="/<msp:webapp/>images/step_5.gif"
															vspace=0></td>
										</tr>
									</table>
								</td>
							</tr>
						  
							<logic:iterate id="exceptionCourtVarElemBeansIter" name="courtPolicyForm" property="exceptionCourtVarElemBeans">
								<tr>
									<td class=boldText colspan=2><bean:write name="exceptionCourtVarElemBeansIter" property="courtNumber"/></td>
								</tr>
								<logic:iterate id="variableElementsIter" name="exceptionCourtVarElemBeansIter" property="variableElements">
									<logic:equal name="variableElementsIter" property="reference" value="false">
										<logic:notEmpty name="variableElementsIter" property="name">
											<tr>
												<td class="formDeLabel" nowrap width=1%><bean:write name="variableElementsIter" property="name"/></td>
												<td class="formDe">
													<bean:write name="variableElementsIter" property="value"/>
													
													<logic:equal name="variableElementsIter" property="fixed" value="true">
														<bean:message key="prompt.fixed" />
													</logic:equal>
													<logic:equal name="variableElementsIter" property="fixed" value="false">
														<bean:message key="prompt.variable" />
													</logic:equal>
												</td>
											</tr>
										</logic:notEmpty>
									</logic:equal>
								</logic:iterate>
							</logic:iterate>
						</table>
					
						<br>
					
						<table width="98%" border="0" cellspacing="0" cellpadding="2">
							<tr>
								<td>
									<!-- Associations -->
										<tiles:insert page="../../../common/associatedConditionsView.jsp" flush="true">
											<tiles:put name="associatedConditions" beanName="courtPolicyForm" beanProperty="associatedConditions"/>
											<tiles:put name="conditionGroup2Caption" beanName="courtPolicyForm" beanProperty="conditionGroup2Caption"/>
											<tiles:put name="conditionGroup3Caption" beanName="courtPolicyForm" beanProperty="conditionGroup3Caption"/>
										</tiles:insert>
								</td>
							</tr>
						</table>
						
						<br>
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
						  <tr>
							<td align="center">
								<input type="button" value="Back" name="return" onClick="history.go(-1)">
								
								<logic:equal name="courtPolicyForm" property="viewOnly" value="false">
									<jims2:isAllowed requiredFeatures="CS-CPOL-COPY">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.copy"/></html:submit>
									</jims2:isAllowed>
									<jims2:isAllowed requiredFeatures="CS-CPOL-UPDATE">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.update"/></html:submit>
									</jims2:isAllowed>
									<jims2:isAllowed requiredFeatures="CS-CPOL-DELETE">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.delete"/></html:submit>
									</jims2:isAllowed>
									<jims2:isAllowed requiredFeatures="CS-CPOL-UPDATE">
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.updateAssociations"/></html:submit>
									</jims2:isAllowed>
									<input type="button" value='<bean:message key="button.cancel" />' name="return" onclick="javascript:changeFormActionURL(this.form.name, '/<msp:webapp/>displayCourtPolicySearch.do', true);">
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

<!-- END  TABLE -->
</div>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</html:html>
