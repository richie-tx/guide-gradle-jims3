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
<title><bean:message key="title.heading" /> - conditionSetDetails.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
</head>

<bean:define id="conditionGroup1Caption" name="supervisionConditionForm" property="conditionGroup1Caption" type="java.lang.String"/>
<bean:define id="conditionGroup2Caption" name="supervisionConditionForm" property="conditionGroup2Caption" type="java.lang.String"/>
<bean:define id="conditionGroup3Caption" name="supervisionConditionForm" property="conditionGroup3Caption" type="java.lang.String"/>

<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySupervisionConditionUpdateSetDetailsExceptions">
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
					<td valign=top align=center>
						<!-- BEGIN HEADING TABLE -->
						<table width=100%>
						  <tr>
							<td align="center" class="header">
								<logic:equal name="supervisionConditionForm" property="action" value="copy">
									<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|34">
									<bean:message key="prompt.copy" />
									<bean:message key="title.supervisionCondition" />&nbsp;-&nbsp;<bean:message key="title.setDetails" />
								</logic:equal>
								
								<logic:equal name="supervisionConditionForm" property="action" value="create">
									<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|10">
									<bean:message key="prompt.create" />
									<bean:message key="title.supervisionCondition" />&nbsp;-&nbsp;<bean:message key="title.setDetails" />
								</logic:equal>
								
								<logic:equal name="supervisionConditionForm" property="action" value="update">
									<logic:notEqual name="supervisionConditionForm" property="inUse" value="true">
										<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|22">
										<bean:message key="prompt.update" />
										<bean:message key="title.supervisionCondition" />&nbsp;-&nbsp;<bean:message key="title.setDetails" />
									</logic:notEqual>
									<logic:equal name="supervisionConditionForm" property="inUse" value="true">
										<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|23">
										<bean:message key="prompt.update" />
										<bean:message key="prompt.inUse" />
										<bean:message key="title.supervisionCondition" />&nbsp;-&nbsp;<bean:message key="title.setDetails" />
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
						</table>
						<!-- BEGIN  TABLE -->
						<table width="98%" border="0" cellspacing=1 cellpadding=4>
							<tr>
								<td colspan=2 class=detailHead>
									<table width=100% cellpadding=0 cellspacing=0>
										<tr>
											<td width=1%><a href="javascript:showHideMulti('supervisionCondition', 'sc', 15, '/<msp:webapp/>')" ><img border=0 src="/<msp:webapp/>images/expand.gif" name="supervisionCondition"></a></td>
											<td class=detailHead>&nbsp;<bean:message key="title.supervisionCondition" /></td>
											<td align=right><img src="/<msp:webapp/>images/step_1.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="sc0" class="hidden">
								<td class=formDeLabel width=1% nowrap><bean:message key="prompt.name" /></td>
								<td class=formDe><bean:write name="supervisionConditionForm" property="conditionName"/></td>
							</tr>
							<tr id="sc1" class="hidden">
												<td class=formDeLabel width=1% nowrap><bean:message key="<%=conditionGroup1Caption%>"/></td>
												<td class=formDe><bean:write name="supervisionConditionForm" property="group1Name"/></td>
											</tr>
											<tr id="sc2" class="hidden">
												<td class=formDeLabel width=1% nowrap><bean:message key="<%=conditionGroup2Caption%>"/></td>
												<td class=formDe><bean:write name="supervisionConditionForm" property="group2Name"/></td>
											</tr>
											<tr id="sc3" class="hidden">
												<td class=formDeLabel width=1% nowrap><bean:message key="<%=conditionGroup3Caption%>"/></td>
												<td class=formDe><bean:write name="supervisionConditionForm" property="group3Name"/></td>
											</tr>
											<tr id="sc4" class="hidden">
												<td class=formDeLabel><bean:message key="prompt.standard" /></td>
												<td class=formDe><logic:equal name="supervisionConditionForm" property="standard" value="true">
												Standard</logic:equal>
												<logic:notEqual name="supervisionConditionForm" property="standard" value="true">
												Non Standard</logic:notEqual></td>
											</tr>
											<tr id="sc5" class="hidden">
												<td class=formDeLabel valign=top width=1% nowrap><bean:message key="prompt.literal" /></td>
												<td class=formDe><bean:write name="supervisionConditionForm" property="conditionLiteral" filter="false"/></td>
											</tr>
											<tr id="sc15" class="hidden">
												<td class=formDeLabel valign=top width=1% nowrap><bean:message key="prompt.spanishLiteral" /></td>
												<td class=formDe><bean:write name="supervisionConditionForm" property="conditionSpanishLiteral"  filter="false"/></td>
											</tr>
											<logic:equal name="supervisionConditionForm" property="agencyJUV" value="true">
												<tr id="sc6" class="hidden">
													<td class=formDeLabel nowrap><bean:message key="prompt.supervisionType" /></td>
													<td class=formDe><bean:write name="supervisionConditionForm" property="dispSelSupTypes"/></td>
												</tr>
											</logic:equal>
											<tr id="sc7" class="hidden">
												<td class=formDeLabel nowrap><bean:message key="prompt.documents" /></td>
												<td class=formDe>
													<bean:write name="supervisionConditionForm" property="selDocuments"/>
												</td>
											</tr>
											<logic:equal name="supervisionConditionForm" property="agencyJUV" value="false">
												<tr id="sc6" class="hidden">
													<td class=formDeLabel nowrap><bean:message key="prompt.severityLevel" /></td>
													<td class=formDe><bean:write name="supervisionConditionForm" property="severityLevel"/></td>
												</tr>										
												<tr id="sc8" class="hidden">
													<td class=formDeLabel nowrap><bean:message key="prompt.jurisdiction" /></td>
													<td class=formDe><bean:write name="supervisionConditionForm" property="jurisdiction"/></td>
												</tr>
											</logic:equal>	
											<tr id="sc9" class="hidden">
												<td class=formDeLabel nowrap><bean:message key="prompt.effectiveDate" /></td>
												<td class=formDe><bean:write name="supervisionConditionForm" property="effectiveDate"/></td>
											</tr>
											<logic:equal name="supervisionConditionForm" property="action" value="update">
												<tr id="sc10" class="hidden">
													<td class=formDeLabel width=1% nowrap><bean:message key="prompt.inactiveDate" /></td>
													<td class=formDe><bean:write name="supervisionConditionForm" property="inactiveDate" /></td>
												</tr>
											</logic:equal>
											<logic:equal name="supervisionConditionForm" property="agencyJUV" value="false">
												<tr id="sc11" class="hidden">
													<td class=formDeLabel nowrap><bean:message key="prompt.eventType" /></td>
													<td class=formDe>
														<bean:write name="supervisionConditionForm" property="selectedEventTypes"/>
													</td>
												</tr>
												<tr id="sc12" class="hidden">
													<td class=formDeLabel nowrap><bean:message key="prompt.eventCount" /></td>
													<td class=formDe><bean:write name="supervisionConditionForm" property="eventCountDesc"/></td>
												</tr>
												<tr id="sc13" class="hidden">
													<td class=formDeLabel nowrap><bean:message key="prompt.period" /></td>
													<td class=formDe>
														<logic:notEmpty name="supervisionConditionForm" property="periodValue">
																		<logic:notEqual name="supervisionConditionForm" property="periodValue" value="0">
																		<bean:write name="supervisionConditionForm" property="periodValue"/> <bean:write name="supervisionConditionForm" property="period"/>
																		</logic:notEqual>
																		</logic:notEmpty></td>
												</tr>
											</logic:equal>
											<tr id="sc14" class="hidden">
												<td class=formDeLabel nowrap><bean:message key="prompt.notes" /></td>
												<td class=formDe><bean:write name="supervisionConditionForm" property="notes"/></td>
											</tr>
											
							
						</table>
<br>
							
							<!--task info start-->
								<tiles:insert page="../../../common/taskListTile.jsp" flush="true">
										<tiles:put name="taskConfig" beanName="supervisionConditionForm" beanProperty="tasks"/>
									</tiles:insert>	
								<!--task info end-->
							
						<br>
						<!-- Begin Selected Court(s) -->		
						<tiles:insert page="../../../common/courts.jsp" flush="true">
							<tiles:put name="beanName" beanName="supervisionConditionForm" />
							<tiles:put name="mode" value="setExceptionCourts" />
						</tiles:insert>		
						<!-- End Selected Court(s) -->											
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
									</td>
								</tr>
							</table>
							<br>
						</logic:notEqual>
						
						<logic:notEmpty name="supervisionConditionForm" property="removedAssociations">
							<table width="98%" border="0" cellspacing="0" cellpadding="2">
								<tr>
									<td>
										<!-- Associations -->
											<tiles:insert page="../../../common/removedAssociationsView.jsp" flush="true">
												<tiles:put name="removedAssociations" beanName="supervisionConditionForm" beanProperty="removedAssociations"/>
												<tiles:put name="associationType" value="condition"/>
												<tiles:put name="group2Caption" beanName="supervisionConditionForm" beanProperty="courtGroup2Caption"/>
												<tiles:put name="group3Caption" beanName="supervisionConditionForm" beanProperty="courtGroup3Caption"/>
											</tiles:insert>
									</td>
								</tr>
							</table>
							<br>
						</logic:notEmpty>
							
						<bean:size id="vereSize" name="supervisionConditionForm" property="variableElementResponseEvents"/>

						<!-- BEGIN BUTTON TABLE -->
				
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<input type="button" value="Back" name="return" onClick="history.go(-1)">
									<html:submit property="submitAction" onclick="return (validateVariableElements(this.form) && validateCustomStrutsBasedJS(this.form)&& disableSubmit(this, this.form))"><bean:message key="button.next"/></html:submit>
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
					</td>
				</tr>
			</table>
			<!-- END BUTTON TABLE -->
		</td>
	</tr>
</table>
<!-- END  TABLE -->
</div>

<br>

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
