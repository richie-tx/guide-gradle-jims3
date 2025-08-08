<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<!-- 01/18/2006  Hien Rodriguez  JIMS200027598 - Modify all Titles & Labels for JUV & PTR agencies -->
<!-- 03/06/2006  Clarence Shimek ER#28542 add disable button to next button -->
<!-- 03/31/2006  Hien Rodriguez  defect#300113 display correct labels for each agency -->
<!-- 01/22/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. --> 
<!-- 11/01/2007  Clarence Shimek defect#39326 removed extra spaces from heading --> 

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

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
<title><bean:message key="title.heading" /> - courtPolicySetDetails.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>

</head>

<bean:define id="courtPolicyTitle" name="courtPolicyForm" property="courtPolicyTitle" type="java.lang.String"/>
<bean:define id="courtGroup1Caption" name="courtPolicyForm" property="courtGroup1Caption" type="java.lang.String"/>
<bean:define id="courtGroup2Caption" name="courtPolicyForm" property="courtGroup2Caption" type="java.lang.String"/>
<bean:define id="courtGroup3Caption" name="courtPolicyForm" property="courtGroup3Caption" type="java.lang.String"/>
<bean:define id="courtPolicyLiteralCaption" name="courtPolicyForm" property="courtPolicyLiteralCaption" type="java.lang.String"/>
<bean:define id="conditionGroup1Caption" name="courtPolicyForm" property="conditionGroup1Caption" type="java.lang.String"/>
<bean:define id="conditionGroup2Caption" name="courtPolicyForm" property="conditionGroup2Caption" type="java.lang.String"/>

<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayCourtPolicyDetailsExceptions" target="content">
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
									<logic:equal name="courtPolicyForm" property="action" value="copy">
										<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|74">
										<bean:message key="prompt.copy" />
										<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;-&nbsp;<bean:message key="title.setDetails" />
									</logic:equal>
									
									<logic:equal name="courtPolicyForm" property="action" value="create">
										<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|55">
										<bean:message key="prompt.create" />
										<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;-&nbsp;<bean:message key="title.setDetails" />
									</logic:equal>
									
									<logic:equal name="courtPolicyForm" property="action" value="update">
										<logic:notEqual name="courtPolicyForm" property="inUse" value="true">
											<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|65">
											<bean:message key="prompt.update" />
											<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;-&nbsp;<bean:message key="title.setDetails" />
										</logic:notEqual>
										<logic:equal name="courtPolicyForm" property="inUse" value="true">
											<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|66">
											<bean:message key="prompt.update" />
											<bean:message key="prompt.inUse" />
									<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;-&nbsp;<bean:message key="title.setDetails" />
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
									<li>Click Next.</li>
								</ul></td>
							</tr>
						</table>
						<!-- BEGIN  TABLE -->
						<table width="98%" border="0" cellspacing=1 cellpadding=4>
							<tr>
								<td colspan=2 class=detailHead>
									<table width=100% cellpadding=0 cellspacing=0>
										<tr>
											<td width=1%><a href="javascript:showHideMulti('supervisionCondition', 'sc', 13, '/<msp:webapp/>')" ><img border=0 src="/<msp:webapp/>images/expand.gif" name="supervisionCondition"></a></td>
											<td class=detailHead>&nbsp;<bean:message key="<%=courtPolicyTitle%>"/></td>
											<td align=right><img src="/<msp:webapp/>images/step_1.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							
							<tr id="sc11" class="hidden">
								<td width=1% nowrap class=formDeLabel><bean:message key="prompt.name" /></td>
								<td class=formDe><bean:write name="courtPolicyForm" property="courtPolicyName" /></td>
							</tr>
							<tr id="sc0" class="hidden">
								<td class=formDeLabel width=1% nowrap><bean:message key="<%=courtGroup1Caption%>"/></td>
								<td class=formDe><bean:write name="courtPolicyForm" property="group1Name"/></td>
							</tr>
							<tr id="sc1" class="hidden">
								<td class=formDeLabel width=1% nowrap><bean:message key="<%=courtGroup2Caption%>"/></td>
								<td class=formDe><bean:write name="courtPolicyForm" property="group2Name"/></td>
							</tr>
							<tr id="sc2" class="hidden">
								<td class=formDeLabel width=1% nowrap><bean:message key="<%=courtGroup3Caption%>"/></td>
								<td class=formDe><bean:write name="courtPolicyForm" property="group3Name"/></td>
							</tr>
							<tr id="sc3" class="hidden">
								<td class=formDeLabel><bean:message key="prompt.standard" /></td>
								<td class=formDe><logic:equal name="courtPolicyForm" property="standard" value="true">
												Standard</logic:equal>
												<logic:notEqual name="courtPolicyForm" property="standard" value="true">
												Non Standard</logic:notEqual></td>
							</tr>
							<tr id="sc4" class="hidden">
								<td class="formDeLabel" nowrap><bean:message key="prompt.policySource" /></td>
								<td class=formDe>
									<logic:equal name="courtPolicyForm" property="departmentPolicy" value="true">
										<bean:message key="prompt.department"/>
									</logic:equal>
									<logic:notEqual name="courtPolicyForm" property="departmentPolicy" value="true">
										<bean:message key="prompt.court"/>
									</logic:notEqual>
								</td>
							</tr>
							<tr id="sc5" class="hidden">
								<td class=formDeLabel valign=top width=1% nowrap><bean:message key="<%=courtPolicyLiteralCaption%>"/></td>
								<td class=formDe>
									<bean:write name="courtPolicyForm" property="courtPolicyLiteral" filter="false"/>
								</td>
							</tr>								
							<tr id="sc6" class="hidden">
								<td class=formDeLabel nowrap><bean:message key="prompt.effectiveDate" /></td>
								<td class=formDe><bean:write name="courtPolicyForm" property="effectiveDate"/></td>
							</tr>

							<!-- if update scenario, include inactive date -->
							<logic:equal name="courtPolicyForm" property="action" value="update">
								<tr id="sc12" class="hidden">
									<td class=formDeLabel width=1% nowrap><bean:message key="prompt.inactiveDate" /></td>
									<td class=formDe><bean:write name="courtPolicyForm" property="inactiveDate" /></td>
								</tr>
							</logic:equal>
<logic:equal name="courtPolicyForm" property="agencyJUV" value="false">
							<tr id="sc7" class="hidden">
								<td class=formDeLabel nowrap><bean:message key="prompt.eventType" /></td>
								<td class=formDe><bean:write name="courtPolicyForm" property="selectedEventTypes"/></td>
							</tr>
							<tr id="sc8" class="hidden">
								<td class=formDeLabel nowrap><bean:message key="prompt.eventCount" /></td>
								<td class=formDe><bean:write name="courtPolicyForm" property="eventCountDesc"/></td>
							</tr>
							<tr id="sc9" class="hidden">
								<td class=formDeLabel nowrap><bean:message key="prompt.period" /></td>
								<td class=formDe>
														<logic:notEmpty name="courtPolicyForm" property="periodValue">
														<logic:notEqual name="courtPolicyForm" property="periodValue" value="0">
														<bean:write name="courtPolicyForm" property="periodValue"/> <bean:write name="courtPolicyForm" property="period"/>
														</logic:notEqual>
														</logic:notEmpty>
													</td>
							</tr>
							</logic:equal>
							<tr id="sc10" class="hidden">
								<td class=formDeLabel nowrap><bean:message key="prompt.notes" /></td>
								<td class=formDe><bean:write name="courtPolicyForm" property="notes"/></td>
							</tr>
							
							
											
							
						</table>
						<br>
						<!--task info start-->
						<tiles:insert page="../../../common/taskListTile.jsp" flush="true">
										<tiles:put name="taskConfig" beanName="courtPolicyForm" beanProperty="tasks"/>
									</tiles:insert>	
								<!--task info end-->
						<br>

						<tiles:insert page="../../../common/courts.jsp" flush="true">
							<tiles:put name="beanName" beanName="courtPolicyForm" />
							<tiles:put name="mode" value="setExceptionCourts" />
						</tiles:insert>
						<br>
						
						<logic:notEqual name="courtPolicyForm" property="action" value="<%=UIConstants.CREATE%>"> 
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
						</logic:notEqual>
						
						 <logic:notEmpty name="courtPolicyForm" property="removedAssociations"> 
							<table width="98%" border="0" cellspacing="0" cellpadding="2">
								<tr>
									<td>
										<!-- Associations -->
											<tiles:insert page="../../../common/removedAssociationsView.jsp" flush="true">
												<tiles:put name="removedAssociations" beanName="courtPolicyForm" beanProperty="removedAssociations"/>
												<tiles:put name="associationType" value="court"/>
												<tiles:put name="group2Caption" beanName="courtPolicyForm" beanProperty="conditionGroup2Caption"/>
												<tiles:put name="group3Caption" beanName="courtPolicyForm" beanProperty="conditionGroup3Caption"/>
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
						      	<html:submit property="submitAction" onclick="return (validateVariableElements(this.form) && validateCustomStrutsBasedJS(this.form) && disableSubmit(this, this.form));"><bean:message key="button.next"/></html:submit>
							  	<input type="reset" value="Reset" name="reset">

									<input type=button value='<bean:message key="button.cancel" />' 
										<logic:equal name="courtPolicyForm" property="action" value="<%=UIConstants.CREATE%>"> 
											onclick="javascript:changeFormActionURL(this.form.name, '/<msp:webapp/>displayCourtPolicyCreate.do', true);"
										</logic:equal>
										<logic:notEqual name="courtPolicyForm" property="action" value="<%=UIConstants.CREATE%>"> 
											onclick="javascript:changeFormActionURL(this.form.name, '/<msp:webapp/>displayCourtPolicySearch.do', true);"
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

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</html:html>
