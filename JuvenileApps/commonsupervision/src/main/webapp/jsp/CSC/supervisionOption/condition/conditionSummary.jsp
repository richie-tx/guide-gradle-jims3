<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<!-- 03/02/2006  Clarence Shimek - ER#28542 add disable button to finish button -->
<!-- 03/31/2006  Hien Rodriguez  defect#300113 display correct labels for each agency -->
<!-- 06/28/2006  Hien Rodriguez - defect#32504 wrap security tag around Update Associations buttons -->
<!-- 07/31/2006  Hien Rodriguez - defect#33400 display correct buttons labels for earch agency -->
<!-- 01/19/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 11/26/2007  Clarence Shimek - defect#47334 remove extra spaces in heading (merge removed this correction made under defedt39326) -->
<!-- 09/10/2009  Richard Y - defect#61943 added label for the courts -->
<!-- 3/24/2014   R Carter       - #76751 Removed Court and Department Association list/buttons per requirements -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

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
<title><bean:message key="title.heading" /> - conditionSummary.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<bean:define id="conditionGroup1Caption" type="java.lang.String" value="prompt.group1"/>
<bean:define id="conditionGroup2Caption" type="java.lang.String" value="prompt.group2"/>
<bean:define id="conditionGroup3Caption" type="java.lang.String" value="prompt.group3"/>
<bean:define id="courtPolicyTitle" type="java.lang.String" value="title.CSCourtPolicy"/>
<bean:define id="deptPolicyTitle" type="java.lang.String" value="prompt.policyTableTitle"/>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<div align="center"><html:form
	action="/submitSupervisionConditionCreate">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<TBODY>
			<tr>
				<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
			</tr>
			<tr>
				<td valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<TBODY>
						<tr>
							<td valign="top"><!--tabs start--> <tiles:insert
								page="../../../common/commonSupervisionTabs.jsp" flush="true" /> <!--tabs end-->
							</td>
						</tr>
						<tr>
							<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif"
								height="5"></td>
						</tr>
					</TBODY>
				</table>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="borderTableBlue">
					<TBODY>
						<tr>
							<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
						</tr>
						<tr>
							<td valign="top" align="center"><!-- BEGIN HEADING TABLE -->
								<table width="100%">
									<tr>
										<td align="center" class="header">
											<!-- Summary header & help section -->
											<logic:equal name="supervisionConditionForm" property="pageType" value="summary">
											
												<logic:equal name="supervisionConditionForm" property="action" value="asscCourtPolicy">
													<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|46">
													<bean:message key="prompt.associate" />
													<bean:message key="title.supervisionCondition" />&nbsp;<bean:message key="title.summary" />
												</logic:equal>
												
												<logic:equal name="supervisionConditionForm" property="action" value="asscDeptPolicy">
													<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|46">
													<bean:message key="prompt.associate" />
													<bean:message key="title.supervisionCondition" />&nbsp;<bean:message key="title.summary" />
												</logic:equal>
												
												<logic:equal name="supervisionConditionForm" property="action" value="create">
													<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|12">
													<bean:message key="prompt.create" />
													<bean:message key="title.supervisionCondition" />&nbsp;<bean:message key="title.summary" />
												</logic:equal>
												
												<logic:equal name="supervisionConditionForm" property="action" value="copy">
													<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|36">
													<bean:message key="prompt.copy" />
													<bean:message key="title.supervisionCondition" />&nbsp;<bean:message key="title.summary" />
												</logic:equal>
												
												<logic:equal name="supervisionConditionForm" property="action" value="update">
													<logic:notEqual name="supervisionConditionForm" property="inUse" value="true">
														<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
															<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|27">
															<bean:message key="prompt.update" />
															<bean:message key="title.supervisionCondition" />&nbsp;<bean:message key="title.summary" />															
														</logic:notEqual>
													</logic:notEqual>
													
													<logic:equal name="supervisionConditionForm" property="inUse" value="true">
														<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
															<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|112">
															<bean:message key="prompt.update" />
															<bean:message key="prompt.inUse" />
															<bean:message key="title.supervisionCondition" />&nbsp;<bean:message key="title.summary" />															
														</logic:notEqual>
													</logic:equal>
													
													<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
														<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|28">
														<bean:message key="prompt.update" />
														<bean:message key="prompt.special" />
														<bean:message key="title.supervisionCondition" />&nbsp;<bean:message key="title.summary" />														
													</logic:equal>
												</logic:equal>
											
												<logic:equal name="supervisionConditionForm" property="action" value="delete">
													<logic:notEqual name="supervisionConditionForm" property="inUse" value="true">
														<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|39">
														<bean:message key="prompt.delete" />
												<bean:message key="title.supervisionCondition" />&nbsp;<bean:message key="title.summary" />														
													</logic:notEqual>
													
													<logic:equal name="supervisionConditionForm" property="inUse" value="true">
												  		<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|42">
												  		<bean:message key="prompt.inactivate" />
												<bean:message key="title.supervisionCondition" />&nbsp;<bean:message key="title.summary" />
													</logic:equal>
												</logic:equal>
												
											</logic:equal>
											<!-- Summary header & help section end -->
											
											<!-- Confirmation header & help section -->
											<logic:equal name="supervisionConditionForm" property="pageType" value="confirm">
											
												<logic:equal name="supervisionConditionForm" property="action" value="asscCourtPolicy">
													<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|47">
													<bean:message key="prompt.associate" />
													<bean:message key="title.supervisionCondition" />&nbsp;<bean:message key="title.confirmation" />													
												</logic:equal>
												
												<logic:equal name="supervisionConditionForm" property="action" value="asscDeptPolicy">
													<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|47">
													<bean:message key="prompt.associate" />
													<bean:message key="title.supervisionCondition" />&nbsp;<bean:message key="title.confirmation" />													
												</logic:equal>
												
												<logic:equal name="supervisionConditionForm" property="action" value="create">
													<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|13">
													<bean:message key="prompt.create" />
													<bean:message key="title.supervisionCondition" />&nbsp;<bean:message key="title.confirmation" />													
												</logic:equal>
												
												<logic:equal name="supervisionConditionForm" property="action" value="copy">
													<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|37">
													<bean:message key="prompt.copy" />
													<bean:message key="title.supervisionCondition" />&nbsp;<bean:message key="title.confirmation" />													
												</logic:equal>
												
												<logic:equal name="supervisionConditionForm" property="action" value="update">
													<logic:notEqual name="supervisionConditionForm" property="inUse" value="true">
														<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
															<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|29">
															<bean:message key="prompt.update" />
															<bean:message key="title.supervisionCondition" />&nbsp;<bean:message key="title.confirmation" />															
														</logic:notEqual>
													</logic:notEqual>
													
													<logic:equal name="supervisionConditionForm" property="inUse" value="true">
														<logic:notEqual name="supervisionConditionForm" property="specialCondition" value="true">
															<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|113">
															<bean:message key="prompt.update" />
															<bean:message key="prompt.inUse" />
															<bean:message key="title.supervisionCondition" />&nbsp;<bean:message key="title.confirmation" />															
														</logic:notEqual>
													</logic:equal>
													
													<logic:equal name="supervisionConditionForm" property="specialCondition" value="true">
														<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|30">
														<bean:message key="prompt.update" />
														<bean:message key="prompt.special" />
														<bean:message key="title.supervisionCondition" />&nbsp;<bean:message key="title.confirmation" />
													</logic:equal>
												</logic:equal>
											
												<logic:equal name="supervisionConditionForm" property="action" value="delete">
													<logic:notEqual name="supervisionConditionForm" property="inUse" value="true">
														<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|40">
														<bean:message key="prompt.delete" />
														<bean:message key="title.supervisionCondition" />&nbsp;<bean:message key="title.confirmation" />
													</logic:notEqual>
													
													<logic:equal name="supervisionConditionForm" property="inUse" value="true">
												  		<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|43">
														<bean:message key="prompt.inactivate" />
														<bean:message key="title.supervisionCondition" />&nbsp;<bean:message key="title.confirmation" />
													</logic:equal>
												</logic:equal>

											</logic:equal>
											<!-- Confirmation header & help section end -->
										</td>
									</tr>

									<logic:present name="<%=Action.ERROR_KEY%>">
										<tr>
											<td align="center" class="errorAlert"><html:errors
												property="<%=ActionErrors.GLOBAL_MESSAGE%>" /></td>
										</tr>
									</logic:present>

									<logic:equal name="supervisionConditionForm"
										property="pageType" value="confirm">
										<logic:notPresent name="<%=Action.ERROR_KEY%>">
											<tr>
												<td align="center" class="confirm">
													<bean:message key="prompt.conditionSuccessfully" />
													<logic:equal name="supervisionConditionForm" property="action" value="create">
														<bean:message key="prompt.created" />
													</logic:equal>
													<logic:equal name="supervisionConditionForm" property="action" value="copy">
														<bean:message key="prompt.copied" />
													</logic:equal>
													<logic:equal name="supervisionConditionForm" property="action" value="update">
														<bean:message key="prompt.updated" />
													</logic:equal>
													<logic:equal name="supervisionConditionForm" property="action" value="delete">
														<logic:notEqual name="supervisionConditionForm" property="inUse" value="true">
															<bean:message key="prompt.deleted" />
														</logic:notEqual>
														<logic:equal name="supervisionConditionForm" property="inUse" value="true">
											  				<bean:message key="prompt.inactivated" />
														</logic:equal>
													</logic:equal>
													<logic:equal name="supervisionConditionForm" property="action" value="asscCourtPolicy">
													<%-- associated to <bean:message key="<%=courtPolicyTitle%>"/>  --%>
														<bean:message key="prompt.updated" />
													</logic:equal>
													<logic:equal name="supervisionConditionForm" property="action" value="asscDeptPolicy">
													<%-- associated to <bean:message key="<%=deptPolicyTitle%>"/> --%>
														<bean:message key="prompt.updated" />
													</logic:equal>
												</td>
											</tr>
										</logic:notPresent>
									</logic:equal>
								</table>
							<!-- END HEADING TABLE --> <logic:equal
								name="supervisionConditionForm" property="pageType"
								value="summary">
								<!-- BEGIN INSTRUCTION TABLE -->
								<table width="98%" border="0">
									<TBODY>
										<tr>
											<td>
											<ul>
												<li>Click Finish to complete.</li>
											</ul>
											</td>
										</tr>
									</TBODY>
								</table>
							</logic:equal> <!-- BEGIN  TABLE --> <logic:equal
								name="supervisionConditionForm" property="specialCondition"
								value="true">
								<table width="98%" border="0" cellspacing="0" cellpadding="0">
									<TBODY>
										<tr>
											<td><tiles:insert page="conditionSpecialTile.jsp"
												flush="true">
											</tiles:insert></td>
										</tr>
										<tr>
											<td><br>
											</td>
										</tr>
									</TBODY>
								</table>
							</logic:equal> <logic:notEqual name="supervisionConditionForm"
								property="specialCondition" value="true">
								
												<tiles:insert page="conditionInfoSec1Tile.jsp" flush="true">
						</tiles:insert>	
											<br>

											<!--task info start--> <tiles:insert
												page="../../../common/taskListTile.jsp" flush="true">
												<tiles:put name="taskConfig"
													beanName="supervisionConditionForm" beanProperty="tasks" />
											</tiles:insert> <!--task info end--> <br>

											
							</logic:notEqual> <logic:notEqual name="supervisionConditionForm"
								property="specialCondition" value="true">
								<table width="98%" border="0" cellpadding="4" cellspacing="0"
									class="borderTableBlue">
									<TBODY>
										<tr>
											<td class="detailHead">
											<table width="100%" cellpadding="0" cellspacing="0">
												<TBODY>
													<tr>
														<td width="1%"><a
															href="javascript:showHide('courts','row', '/<msp:webapp/>')"><img
															border="0" src="/<msp:webapp/>images/contract.gif"
															name="courts"></a></td>
														<td class="detailHead">&nbsp;<bean:message
															key="prompt.selectedCourts" /></td>
														<td align="right"><img src="/<msp:webapp/>images/step_3.gif"
															vspace="0"></td>
													</tr>
												</TBODY>
											</table>
											</td>
										</tr>
										<tr id="courtsSpan" class="visibleTR">
											<td><tiles:insert page="../../../common/courts.jsp" flush="true">
												<tiles:put name="beanName"
													beanName="supervisionConditionForm" />
												<tiles:put name="mode" value="view" />
												<tiles:put name="displayDDNA" value="true" />
											</tiles:insert></td>
										</tr>
									</TBODY>
								</table>
								<br>
							 <logic:notEmpty name="supervisionConditionForm"
								property="variableElementResponseEvents">
								<bean:size id="varElementRESize" name="supervisionConditionForm"
									property="variableElementResponseEvents" />


								<table width="98%" border="0" cellspacing="1" cellpadding="4">
									<TBODY>
										<tr>
											<td colspan="2" class="detailHead">
											<table width="100%" cellpadding="0" cellspacing="0">
												<TBODY>
													<tr>
														<td width="1%"><a
															href="javascript:showHideMulti('setDetails', 'sd', '<bean:write name="varElementRESize"/>', '/<msp:webapp/>')"><img
															border="0" src="/<msp:webapp/>images/contract.gif"
															name="setDetails"></a></td>
														<td class="detailHead">&nbsp;<bean:message
															key="prompt.setDetailsForSelectedCourts" /></td>
														<td align="right"><img src="/<msp:webapp/>images/step_4.gif"
															vspace="0"></td>
													</tr>
												</TBODY>
											</table>
											</td>
										</tr>

										<logic:iterate indexId="varElementRECount"
											id="varElementREIter" name="supervisionConditionForm"
											property="variableElementResponseEvents">
											<logic:equal name="varElementREIter" property="reference"
												value="false">
												<logic:notEmpty name="varElementREIter" property="name">
													<tr id="sd<bean:write name='varElementRECount'/>"
														class="visibleTR">
														<td class="formDeLabel" nowrap width="1%"><bean:write
															name="varElementREIter" property="name" /></td>
														<td class="formDe"><bean:write name="varElementREIter"
															property="value" /> <!-- Special condition is always VARIABLE -->
														<logic:notEqual name="supervisionConditionForm"
															property="specialCondition" value="true">
															<logic:equal name="varElementREIter" property="fixed"
																value="true">
																<bean:message key="prompt.fixed" />
															</logic:equal>
															<logic:equal name="varElementREIter" property="fixed"
																value="false">
																<bean:message key="prompt.variable" />
															</logic:equal>
														</logic:notEqual></td>
													</tr>
												</logic:notEmpty>
											</logic:equal>
										</logic:iterate>
									</TBODY>
								</table>
								<br>
							</logic:notEmpty> 
							</logic:notEqual>
							<logic:notEqual name="supervisionConditionForm"
								property="specialCondition" value="true">
								<table width="98%" border="0" cellspacing="1" cellpadding="4">
									<TBODY>
										<tr>
											<td colspan="2" class="detailHead">
											<table width="100%" cellpadding="0" cellspacing="0">
												<TBODY>
													<tr>
														<td class="detailHead"><bean:message key="prompt.exceptions" />
														- <bean:message key="prompt.setDetails" /></td>
														<td align="right"><img src="/<msp:webapp/>images/step_5.gif"
															vspace="0"></td>
													</tr>
												</TBODY>
											</table>
											</td>
										</tr>

										<logic:iterate id="exceptionCourtVarElemBeansIter"
											name="supervisionConditionForm"
											property="exceptionCourtVarElemBeans">
											<tr>
												<td class="boldText" colspan="2"><bean:message key="prompt.court"/>&nbsp;<bean:write
													name="exceptionCourtVarElemBeansIter"
													property="courtNumber" /></td>
											</tr>
											<logic:iterate id="variableElementsIter"
												name="exceptionCourtVarElemBeansIter"
												property="variableElements">
												<logic:equal name="variableElementsIter"
													property="reference" value="false">
													<tr>
														<td class="formDeLabel" nowrap width="1%"><bean:write
															name="variableElementsIter" property="name" /></td>
														<td class="formDe"><bean:write name="variableElementsIter"
															property="value" /> <logic:equal
															name="variableElementsIter" property="fixed" value="true">
															<bean:message key="prompt.fixed" />
														</logic:equal> <logic:equal name="variableElementsIter"
															property="fixed" value="false">
															<bean:message key="prompt.variable" />
														</logic:equal></td>
													</tr>
												</logic:equal>
											</logic:iterate>
										</logic:iterate>
									</TBODY>
								</table>
								<br>


								<!-- Delete note for delete action only -->
								<logic:equal name="supervisionConditionForm" property="action"
									value="delete">
									<logic:equal name="supervisionConditionForm" property="inUse" value="true">
									<table width="98%" border="0" cellspacing="1" cellpadding="4">
										<TBODY>
											<tr>
												<td class="detailHead" colspan="2">

								
									<bean:message key="prompt.inactivateReason" />
								
													
													</td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap="nowrap" valign="top"
													width="1%"> <bean:message
													key="prompt.notes" /></td>
												<td class="formDe"><bean:write
													name="supervisionConditionForm" property="deleteNote" /></td>
											</tr>
										</TBODY>
									</table>
									<br>
									</logic:equal>
								</logic:equal>
							</logic:notEqual> <!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">
								<TBODY>
									<tr>
										<td align="center"><!-- create & update will be submitted to the same action -->
										<logic:equal name="supervisionConditionForm"
											property="pageType" value="summary">
											<input type="button" value="Back" name="return"
												onClick="history.go(-1)">
											<logic:equal name="supervisionConditionForm"
												property="action" value="delete">
												<input type="button"
													value="<bean:message key='button.finish'/>"
													property="submitAction"
													onclick=" changeFormActionURL(this.form.name, '/<msp:webapp/>submitSupervisionConditionDelete.do', false) && disableSubmit(this, this.form);">
											</logic:equal>
											<logic:equal name="supervisionConditionForm"
												property="action" value="asscCourtPolicy">
												<input type="button"
													value="<bean:message key='button.finish'/>"
													property="submitAction"
													onclick=" changeFormActionURL(this.form.name, '/<msp:webapp/>submitConditionAssociateToCourtPolicy.do', false) && disableSubmit(this, this.form);">
											</logic:equal>
											<logic:equal name="supervisionConditionForm"
												property="action" value="asscDeptPolicy">
												<input type="button"
													value="<bean:message key='button.finish'/>"
													property="submitAction"
													onclick="changeFormActionURL(this.form.name, '/<msp:webapp/>submitConditionAssociateToDepartmentPolicy.do', false) && disableSubmit(this, this.form);">
											</logic:equal>
											<logic:equal name="supervisionConditionForm"
												property="action" value="create">
												<html:submit property="submitAction"
													onclick="return disableSubmit(this, this.form);">
													<bean:message key="button.finish" />
												</html:submit>
											</logic:equal>
											<logic:equal name="supervisionConditionForm"
												property="action" value="update">
												<html:submit property="submitAction"
													onclick="return disableSubmit(this, this.form);">
													<bean:message key="button.finish" />
												</html:submit>
											</logic:equal>
											<logic:equal name="supervisionConditionForm"
												property="action" value="copy">
												<html:submit property="submitAction"
													onclick="return disableSubmit(this, this.form);">
													<bean:message key="button.finish" />
												</html:submit>
											</logic:equal>

											<logic:equal name="supervisionConditionForm"
												property="action" value="<%=UIConstants.CREATE%>">
												<input type="button"
													value='<bean:message key="button.cancel" />'
													onclick="javascript:changeFormActionURL(this.form.name, '/<msp:webapp/>displaySupervisionConditionCreate.do', false) && disableSubmit(this, this.form);">
											</logic:equal>
											<logic:notEqual name="supervisionConditionForm"
												property="action" value="<%=UIConstants.CREATE%>">
												<input type="button"
													value='<bean:message key="button.cancel" />'
													onclick="javascript:changeFormActionURL(this.form.name, '/<msp:webapp/>displaySupervisionConditionSearch.do', false) && disableSubmit(this, this.form);">
											</logic:notEqual>

										</logic:equal> 										
										<logic:equal name="supervisionConditionForm" property="pageType" value="confirm">
											 <input type="button" value="<bean:message key='button.backToSearch'/>" onclick="javascript:changeFormActionURL(this.form.name, '/<msp:webapp/>displaySupervisionConditionSearch.do', false) && disableSubmit(this, this.form);" > 
										</logic:equal>									
										</td>
									</tr>
								</TBODY>
							</table>
							<!-- END BUTTON TABLE --></td>
						</tr>
					</TBODY>
				</table>
				</td>
			</tr>
		</TBODY>
	</table>
	<!-- END  TABLE -->
</html:form></div>

<br>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
