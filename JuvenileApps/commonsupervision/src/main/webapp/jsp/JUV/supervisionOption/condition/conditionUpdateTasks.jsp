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

<html:javascript formName="conditionUpdateTasks" />

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/taskConfiguration.js"></script>



<title><bean:message key="title.heading" /> - conditionUpdateTasks.jsp</title>
</head>

<bean:define id="conditionGroup1Caption" type="java.lang.String" value="prompt.category"/>
<bean:define id="conditionGroup2Caption" type="java.lang.String" value="prompt.type"/>
<bean:define id="conditionGroup3Caption" type="java.lang.String" value="prompt.subtype"/>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySupervisionConditionUpdateSelectCourts" target="content">
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
					<td valign="top" align=center>
						<table width=100%>
							<tr>
								<td align="center" class="header">
									<logic:equal name="supervisionConditionForm" property="action" value="copy">
										<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|19">
										<bean:message key="prompt.copy" />
										<bean:message key="title.supervisionCondition" />&nbsp;-&nbsp;<bean:message key="prompt.name" />
									</logic:equal>
									
									<logic:equal name="supervisionConditionForm" property="action" value="create">
										<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|8">
										<bean:message key="prompt.create" />
										<bean:message key="title.supervisionCondition" />&nbsp;-&nbsp;<bean:message key="prompt.name" />
									</logic:equal>
									
									<logic:equal name="supervisionConditionForm" property="action" value="update">
										<logic:notEqual name="supervisionConditionForm" property="inUse" value="true">
											<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
												<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|3">
												<bean:message key="prompt.update" />
												<bean:message key="title.supervisionCondition" />&nbsp;-&nbsp;<bean:message key="prompt.name" />			
											</logic:notEqual>
										</logic:notEqual>
										<logic:equal name="supervisionConditionForm" property="inUse" value="true">
											<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
												<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|82">
												<bean:message key="prompt.update" />
												<bean:message key="prompt.inUse" />
												<bean:message key="title.supervisionCondition" />&nbsp;-&nbsp;<bean:message key="prompt.name" />												
											</logic:notEqual>
										</logic:equal>
										<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
											<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|19">
											<bean:message key="prompt.update" />
											<bean:message key="prompt.special" />
											<bean:message key="title.supervisionCondition" />&nbsp;-&nbsp;<bean:message key="prompt.name" />
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
									<li>Enter the required fields</li>
									<li>Click Next.</li>
								</ul></td>
						  	</tr>
						  	<tr>
								<td class="required">
									<bean:message key="prompt.4.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/>
								</td>
							</tr>
						</table>
						<!-- BEGIN  TABLE -->
						<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
							<table width="98%" border="0" cellspacing=0 cellpadding=0>
								<tr>
									<td>
										<tiles:insert page="conditionSpecialTile.jsp" flush="true">
										</tiles:insert>
									</td>
								</tr>
								<tr><td><br></td></tr>
							</table>
						</logic:equal>
						
						<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
							<table width="98%" border="0" cellspacing=0 cellpadding=0>
								<tr>
									<td>
										<table width="100%" cellspacing=1 cellpadding=4>
											<tr>
												<td colspan=2 class=detailHead>
													<table width=100% cellpadding=0 cellspacing=0>
														<tr>
															<td width="1%"><a href="javascript:showHideMulti('supervisionCondition', 'sc', 14, '/<msp:webapp/>')" ><img border=0 src="/<msp:webapp/>images/expand.gif" name="supervisionCondition"></a></td>
															<td class=detailHead>&nbsp;<bean:message key="title.supervisionCondition" /></td>
															<td align=right><img src="/<msp:webapp/>images/step_1.gif"></td>
														</tr>
													</table>
												</td>
											</tr>
											<logic:notEqual name="supervisionConditionForm" property="action" value="<%=UIConstants.UPDATE%>">
												<tr>
													<td width="1%" nowrap class="formDeLabel"><bean:message key="prompt.4.diamond" /><bean:message key="prompt.name" /></td>
													<td class="formDe"><html:text name="supervisionConditionForm" property="conditionName" size="50" maxlength="50"/></td>
												</tr>
											</logic:notEqual>	
											<logic:equal name="supervisionConditionForm" property="action" value="<%=UIConstants.UPDATE%>">
												<tr id="sc0" class="hidden">
													<td class="formDeLabel"><bean:message key="prompt.name" /></td>
													<td class="formDe"><bean:write name="supervisionConditionForm" property="conditionName"/></td>
												</tr>
											</logic:equal>
											<tr id="sc1" class="hidden">
												<td class="formDeLabel" width="1%" nowrap><bean:message key="<%=conditionGroup1Caption%>"/></td>
												<td class="formDe"><bean:write name="supervisionConditionForm" property="group1Name"/></td>
											</tr>
											<tr id="sc2" class="hidden">
												<td class="formDeLabel" width="1%" nowrap><bean:message key="<%=conditionGroup2Caption%>"/></td>
												<td class="formDe"><bean:write name="supervisionConditionForm" property="group2Name"/></td>
											</tr>
											<tr id="sc16" class="hidden">
												<td class="formDeLabel" width="1%" nowrap><bean:message key="<%=conditionGroup3Caption%>"/></td>
												<td class="formDe"><bean:write name="supervisionConditionForm" property="group3Name"/></td>
											</tr>
											<tr id="sc3" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.standard" /></td>
												<td class="formDe"><logic:equal name="supervisionConditionForm" property="standard" value="true">
												Standard</logic:equal>
												<logic:notEqual name="supervisionConditionForm" property="standard" value="true">
												Non Standard</logic:notEqual></td>
											</tr>
											<tr id="sc4" class="hidden">
												<td class="formDeLabel" valign="top" width="1%" nowrap><bean:message key="prompt.literal" /></td>
												<td class="formDe"><bean:write name="supervisionConditionForm" property="conditionLiteral"  filter="false"/></td>
											</tr>
											<tr id="sc14" class="hidden">
												<td class="formDeLabel" valign="top" width="1%" nowrap><bean:message key="prompt.spanishLiteral" /></td>
												<td class="formDe"><bean:write name="supervisionConditionForm" property="conditionSpanishLiteral" filter="false"/></td>
											</tr>
												<tr id="sc5" class="hidden">
													<td class="formDeLabel" nowrap><bean:message key="prompt.supervisionType" /></td>
													<td class="formDe"><bean:write name="supervisionConditionForm" property="dispSelSupTypes"/></td>
												</tr>
												<logic:notEmpty name="supervisionConditionForm" property="selDocuments" >
												<logic:notEqual name="supervisionConditionForm" property="selDocuments" value="">
											<tr id="sc6" class="hidden">
												<td class="formDeLabel" nowrap><bean:message key="prompt.documents" /></td>
												<td class="formDe">
													<bean:write name="supervisionConditionForm" property="selDocuments"/>
												</td>
											</tr>
											</logic:notEqual>
										</logic:notEmpty>
											<tr id="sc8" class="hidden">
												<td class="formDeLabel" nowrap><bean:message key="prompt.effectiveDate" /></td>
												<td class="formDe"><bean:write name="supervisionConditionForm" property="effectiveDate"/></td>
											</tr>
											<logic:equal name="supervisionConditionForm" property="action" value="update">
												<tr id="sc9" class="hidden">
													<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inactiveDate" /></td>
													<td class="formDe"><bean:write name="supervisionConditionForm" property="inactiveDate" /></td>
												</tr>
											</logic:equal>
										
											<tr id="sc13" class="hidden">
												<td class="formDeLabel" nowrap><bean:message key="prompt.notes" /></td>
												<td class="formDe"><bean:write name="supervisionConditionForm" property="notes"/></td>
											</tr>
											<tr>
												<td colspan=2><br></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</logic:notEqual>	
							
						<table width="98%" border="0" cellspacing=1 cellpadding=4 class="hidden">
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
								<td class="formDeLabel" width="1%" nowrap valign="top"><bean:message key="prompt.to" /></td>
								<td class="formDe">
									<table border=0 cellpadding=2>
										<tr class="formDeLabel">
											<td>Recipient</td>
											<td>Task List Type</td>
										</tr>
										<%
											int counter=0;
										%>
										<nest:iterate name="supervisionConditionForm" property="tasks.taskItems" id="taskItemList">
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
													<html:optionsCollection name="supervisionConditionForm" property="tasks.toReceipientList" value="code" label="description" /> 
												</nest:select>
												
											</td>
											<td>
												<nest:select size="1" property="taskListTypeId">
													<option value="">Please Select</option>
													<html:optionsCollection name="supervisionConditionForm" property="tasks.taskListTypeList" value="code" label="description" />  
												</nest:select>
												<a href="javascript:changeFormActionURL(document.forms[0], '/<msp:webapp/>displaySupervisionConditionUpdateSelectCourts.do?submitAction=Remove&selectedValue=<%=counter%>', true)"> Remove</a>
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
								<td class="formDeLabel" width="1%" valign="top" nowrap><bean:message key="prompt.taskSubject" /></td>
								<td class="formDe">
									<html:textarea name="supervisionConditionForm" property="tasks.subject2" rows="3" style="width:100%"></html:textarea>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel" width="1%" valign="top" nowrap><bean:message key="prompt.taskDueBy" /></td>
								<td class="formDe"><html:text name="supervisionConditionForm" property="tasks.dueBy" size="3" maxlength="3"/>&nbsp;<bean:message key="prompt.days" /></td>
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
								<td class="formDeLabel" width="1%" nowrap valign="top"><bean:message key="prompt.to" /></td>
								
									<%int loopX=0; %>
									<logic:empty name="supervisionConditionForm" property="tasks.emailTaskItems" >
									<td class="formDe" > </td>
									</logic:empty>
									<nest:iterate name="supervisionConditionForm" property="tasks.emailTaskItems" id="emailTaskList">
										<%if (loopX==0){ %>
												<td class="formDe" >
										<% } else {%>
										<tr >
										<td class="formDeLabel">
										<td colspan="1" class="formDe" >
										<% }  %>
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
										<%if (loopX==0){ %>
												</td>
										<% }else{ %>
											</td>
											</tr>
										<%  } 
								
										 loopX++; %>
									</nest:iterate>
								
							
							</tr>
							<html:hidden name="supervisionConditionForm" property="clearEmailTasks" value="true"/>
							<!--email info end-->
						</table>
						
						
						<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
						
							<logic:notEqual name="supervisionConditionForm" property="action" value="<%=UIConstants.CREATE%>"> 
								<table width="98%" border="0" cellspacing="0" cellpadding="2" class="hidden">
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
								<table width="98%" border="0" cellspacing="0" cellpadding="2" class="hidden">
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
						</logic:notEqual>
							
												
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
							<tr>
								<td align="center">
									<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
									<logic:notEqual name="supervisionConditionForm" property="action" value="<%=UIConstants.UPDATE%>"> 
										<html:submit property="submitAction" onclick="return (validateConditionUpdateTasks(this.form) && customValidateTask()  && disableSubmit(this, this.form));"><bean:message key="button.next"/></html:submit>
									</logic:notEqual>
									<logic:equal name="supervisionConditionForm" property="action" value="<%=UIConstants.UPDATE%>"> 
									<html:submit property="submitAction" onclick="return (customValidateTask()  && disableSubmit(this, this.form));"><bean:message key="button.next"/></html:submit>
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
		</td>
	</tr>
</table>

<!-- END  TABLE -->
</div>

</html:form>

<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
