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
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

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
<title><bean:message key="title.heading" /> - courtPolicyUpdateTasks.jsp</title>

<html:javascript formName="courtPolicyUpdateTasks" />

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/taskConfiguration.js"></script>
<script type="text/javascript">
function setFocus()
{
	var txtFld = document.getElementsByName("courtPolicyName");
	if (txtFld != null && txtFld.length > 0 )
	{
		txtFld[0].focus();
	} else {
		txtFld = document.getElementsByName("tasks.taskItems[0].recipientId");
		if (txtFld != null && txtFld.length > 0 )
		{
			txtFld[0].focus();
		}	
	}
}
</script>
</head>

<bean:define id="courtPolicyTitle" value="title.consequence" type="java.lang.String"/>
<bean:define id="courtGroup1Caption" value="prompt.category" type="java.lang.String"/>
<bean:define id="courtGroup2Caption" value="prompt.type" type="java.lang.String"/>
<bean:define id="courtGroup3Caption" value="prompt.subtype" type="java.lang.String"/>
<bean:define id="courtPolicyLiteralCaption" value="prompt.literal" type="java.lang.String"/>
<bean:define id="conditionGroup2Caption" value="prompt.type" type="java.lang.String"/>
<bean:define id="conditionGroup3Caption" value="prompt.subtype" type="java.lang.String"/>

<body topmargin=0 leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="setFocus()">
<html:form action="/displayCourtPolicySelectCourts" target="content">
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
									<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|53">
									<bean:message key="prompt.copy" />
									<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;-&nbsp;<bean:message key="title.emailNotificationAndTasks" />
								</logic:equal>
								
								<logic:equal name="courtPolicyForm" property="action" value="create">
									<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|29">
									<bean:message key="prompt.create" />
									<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;-&nbsp;<bean:message key="title.emailNotificationAndTasks" />
								</logic:equal>
								
								<logic:equal name="courtPolicyForm" property="action" value="update">
									<logic:notEqual name="courtPolicyForm" property="inUse" value="true">
										<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|40">
										<bean:message key="prompt.update" />
										<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;-&nbsp;<bean:message key="title.emailNotificationAndTasks" />
									</logic:notEqual>
									<logic:equal name="courtPolicyForm" property="inUse" value="true">
										<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|41">
										<bean:message key="prompt.update" />
										<bean:message key="prompt.inUse" />
										<bean:message key="<%=courtPolicyTitle%>"/>&nbsp;-&nbsp;<bean:message key="title.emailNotificationAndTasks" />
									</logic:equal>
								</logic:equal>
							
							</td>
						  </tr>
							<tr>
								<td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"/></td> 
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
							<logic:notEqual name="courtPolicyForm" property="action" value="<%=UIConstants.UPDATE%>">
								<tr id="12" class="visibleTR">
									<td width=1% nowrap class=formDeLabel><bean:message key="prompt.4.diamond" /><bean:message key="prompt.name" /></td>
									<td class=formDe><html:text name="courtPolicyForm" property="courtPolicyName" size="50" maxlength="50"/></td>
								</tr>
							</logic:notEqual>	
							<logic:equal name="courtPolicyForm" property="action" value="<%=UIConstants.UPDATE%>">
								<tr id="sc12" class="hidden">
									<td class=formDeLabel><bean:message key="prompt.name" /></td>
									<td class=formDe><bean:write name="courtPolicyForm" property="courtPolicyName"/></td>
								</tr>
							</logic:equal>
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
								<td class=formDeLabel nowrap><bean:message key="prompt.policySource" /></td>
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

	  						<logic:equal name="courtPolicyForm" property="action" value="update">
		  						<tr id="sc11" class="hidden">
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
													</td></tr>
							</logic:equal>
							<tr id="sc10" class="hidden">
								<td class=formDeLabel nowrap><bean:message key="prompt.notes" /></td>
								<td class=formDe><bean:write name="courtPolicyForm" property="notes"/></td>
							</tr>
							<tr>
								<td colspan=2><br></td>
							</tr>
							<!--task info start-->
							<tr class=detailHead>
								<td colspan=2>
									<table width=100%cellpadding=0 cellspacing=0>
										<tr>
											<td class=detailHead><bean:message key="prompt.taskSubject" /></td>
											<td align=right><img src="/<msp:webapp/>images/step_2.gif" vspace=0></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td class=formDeLabel width=1% nowrap valign=top><bean:message key="prompt.to" /></td>
								<td class=formDe>
									<table border=0 cellpadding=2>
										<tr class=formDeLabel>
											<td>Recipient</td>
											<td>Task List Type</td>
										</tr>
										<%
											int counter=0;
										%>
										<nest:iterate name="courtPolicyForm" property="tasks.taskItems" id="taskItemList">
										<nest:notEqual property="deleted" value="true">
											<bean:define id="recipName"><nest:writeNesting property="recipientId"/></bean:define>
											<bean:define id="taskListType"><nest:writeNesting property="taskListTypeId"/></bean:define>
											<bean:define id="testCheck">addCustomValidationFields('<%=recipName%>','<%=taskListType%>');</bean:define>
											<script>
												<%=testCheck%>
												
												
												
											</script>
										<tr>
											<td>
												<nest:select size="1" property="recipientId">
													<option value="">Please Select</option>
													<html:optionsCollection name="courtPolicyForm" property="tasks.toReceipientList" value="code" label="description" /> 
												</nest:select>
												
											</td>
											<td>
												<nest:select size="1" property="taskListTypeId">
													<option value="">Please Select</option>
													<html:optionsCollection name="courtPolicyForm" property="tasks.taskListTypeList" value="code" label="description" />  
												</nest:select>
												<a href="javascript:changeFormActionURL(document.forms[0], '/<msp:webapp/>displayCourtPolicySelectCourts.do?submitAction=Remove&selectedValue=<%=counter%>', true)"> Remove</a>
											</td>
										</tr>
										</nest:notEqual>
										<%
											counter++;
										%>
										
										</nest:iterate>
										<tr>
											<td colspan="2">
												
													<html:submit property="submitAction"><bean:message key="button.addMore"/></html:submit>
												
											</td>
											
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td class=formDeLabel width=1% valign=top nowrap><bean:message key="prompt.taskSubject" /></td>
								<td class=formDe>
									<html:textarea name="courtPolicyForm" property="tasks.subject2" rows="3" style="width:100%" ></html:textarea>
								</td>
							</tr>
							<tr>
								<td class=formDeLabel width=1% valign=top nowrap><bean:message key="prompt.taskDueBy" /></td>
								<td class=formDe><html:text name="courtPolicyForm" property="tasks.dueBy" size="3" maxlength="3"/>&nbsp;<bean:message key="prompt.days" /></td>
							</tr>
							<!--task info end-->
							<tr><td><br></td></tr>
							<!--email info start-->
							<tr class=detailHead>
								<td colspan=2>
									<table width=100% cellpadding=1 cellspacing=0>
										<tr>
											<td class=detailHead><bean:message key="prompt.emailNotificationInfo" /></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td class=formDeLabel width=1% nowrap valign=top><bean:message key="prompt.to" /></td>
								<td class=formDe>
									<nest:iterate name="courtPolicyForm" property="tasks.emailTaskItems" id="emailTaskList">
										<nest:notEqual property="recipientId" value="OT">
											<nest:checkbox  property="selected" > <bean:write name="emailTaskList" property="recipient"/></nest:checkbox>
											
											
										</nest:notEqual>
										<nest:equal property="recipientId" value="OT">
										<bean:define id="emailCheckBoxName"><nest:writeNesting property="selected"/></bean:define>
											<bean:define id="emailCheckBoxAddr"><nest:writeNesting property="emailAddress"/></bean:define>
											<bean:define id="emailCheckBoxCheck">evaluateOtherEmailField('<%=emailCheckBoxName%>','<%=emailCheckBoxAddr%>');</bean:define>
											
											<nest:checkbox  property="selected" onclick="<%=emailCheckBoxCheck%>"> <bean:write name="emailTaskList" property="recipient"/></nest:checkbox>
											<nest:text  property="emailAddress" size="50"/>
											
											<script>
												<%=emailCheckBoxCheck%>
												
											</script>
										</nest:equal>
										<bean:define id="globalEmailName"><nest:writeNesting property="selected"/></bean:define>
										<script>
												addCustomEmailFields('<%=globalEmailName%>');
												
											</script>
										<br>
									</nest:iterate>
								</td>
							</tr>
							<html:hidden name="courtPolicyForm" property="clearEmailTasks" value="true"/>
							<!--email info end-->
															
						</table>
						<br>
						
						<logic:notEqual name="courtPolicyForm" property="action" value="<%=UIConstants.CREATE%>"> 
							<table width="98%" border="0" cellspacing="0" cellpadding="2">
								<tr>
									<td>
										<!-- Associations -->
											<tiles:insert page="../../../common/associatedConditionsView.jsp" flush="true">
												<tiles:put name="associatedConditions" beanName="courtPolicyForm" beanProperty="associatedConditions"/>
												<tiles:put name="conditionGroup2Caption" value="prompt.type"/>
											    <tiles:put name="conditionGroup3Caption" value="prompt.subtype"/>
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
												<tiles:put name="group2Caption" value="prompt.type"/>
												<tiles:put name="group3Caption" value="prompt.subtype"/>
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
										<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
										<logic:equal name="courtPolicyForm" property="action" value="<%=UIConstants.CREATE%>"> 
											<html:submit property="submitAction" onclick="return (customValidateTask() && disableSubmit(this, this.form));"><bean:message key="button.next"/></html:submit>
							  			</logic:equal>
							  			<logic:notEqual name="courtPolicyForm" property="action" value="<%=UIConstants.CREATE%>"> 
											<html:submit property="submitAction" onclick="return (customValidateTask() && disableSubmit(this, this.form));"><bean:message key="button.next"/></html:submit>
							  			</logic:notEqual>
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
