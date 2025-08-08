<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<!-- 01/23/2006  Hien Rodriguez  JIMS200026607 - Add validateSelectedCourts -->
<!-- 03/06/2006  Clarence Shimek ER#28542 add disable button to next button -->
<!-- 04/03/2006  Hien Rodriguez  defect#300113 display correct labels for each agency -->
<!-- 09/06/2006  Hien Rodriguez  ER#34507 Implement new UI Guidelines for date fields -->
<!-- 01/23/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 10/19/2007  Clarence Shimek defect#46036 add cursor set -->
<!-- 11/01/2007  Clarence Shimek defect#39326 removed extra spaces from heading -->
<!-- 08/24/2010  D Williamson  - #67068 Changed tinyMCECustomInitMSO.js to tinyMCECustomInitCasenote.js -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="org.apache.struts.action.Action"%>
<%@ page import="org.apache.struts.action.ActionErrors"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@page import="naming.UIConstants"%>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Ensures the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />

<logic:equal name="departmentPolicyForm" property="action" value="copy">
	<html:javascript formName="deptPolicyCopy" />
</logic:equal>
<logic:equal name="departmentPolicyForm" property="action"
	value="update">
	<html:javascript formName="deptPolicyUpdate" />
</logic:equal>
<logic:equal name="departmentPolicyForm" property="action"
	value="create">
	<html:javascript formName="deptPolicyCreate" />
</logic:equal>

<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/groups.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/tinyMCECustomInitCasenote.js"></script>

<script type="text/javascript">
<logic:iterate indexId="groupIterIndex" id="groupIter" name="departmentPolicyForm" property="groups">
	groups[<bean:write name="groupIterIndex"/>] = new subgroup("<bean:write name="groupIter" property="groupId" />", "<bean:write name="groupIter" property="name" filter="false" />");

	<logic:iterate indexId="groupIterIndex2" id="groupIter2" name="groupIter" property="subgroups">
		var innerGroup = new subgroup("<bean:write name="groupIter2" property="groupId" />", "<bean:write name="groupIter2" property="name" filter="false" />");
		groups[<bean:write name="groupIterIndex"/>].subgroups[<bean:write name="groupIterIndex2"/>] = innerGroup;

		<logic:iterate indexId="groupIterIndex3" id="groupIter3" name="groupIter2" property="subgroups">
			var innerGroup = new subgroup("<bean:write name="groupIter3" property="groupId" />", "<bean:write name="groupIter3" property="name" filter="false" />");
			groups[<bean:write name="groupIterIndex"/>].subgroups[<bean:write name="groupIterIndex2"/>].subgroups[<bean:write name="groupIterIndex3"/>] = innerGroup;		
		</logic:iterate>
	</logic:iterate>
</logic:iterate>
</script>
<script type="text/javascript">
function validateDept(theForm){
	if(!validateCustomStrutsBasedJS(theForm)){
		return false;
	}
	<logic:equal name="departmentPolicyForm" property="action" value="copy">
		return validateDeptPolicyCopy(theForm);
	</logic:equal>
	<logic:equal name="departmentPolicyForm" property="action" value="update">
		return validateDeptPolicyUpdate(theForm);
	</logic:equal>
	<logic:equal name="departmentPolicyForm" property="action" value="create">
		return validateDeptPolicyCreate(theForm);
	</logic:equal>
}

</script>

<title><bean:message key="title.heading" /> -
deptPolicyUpdate.jsp</title>
</head>

<bean:define id="departmentGroup1Caption" name="departmentPolicyForm"
	property="departmentGroup1Caption" type="java.lang.String" />
<bean:define id="departmentGroup2Caption" name="departmentPolicyForm"
	property="departmentGroup2Caption" type="java.lang.String" />
<bean:define id="departmentGroup3Caption" name="departmentPolicyForm"
	property="departmentGroup3Caption" type="java.lang.String" />
<bean:define id="departmentPolicyLiteralCaption"
	name="departmentPolicyForm" property="departmentPolicyLiteralCaption"
	type="java.lang.String" />
<bean:define id="conditionGroup2Caption" name="departmentPolicyForm"
	property="conditionGroup2Caption" type="java.lang.String" />
<bean:define id="conditionGroup3Caption" name="departmentPolicyForm"
	property="conditionGroup3Caption" type="java.lang.String" />
<bean:define id="deptPolicyTitle" name="departmentPolicyForm"
	property="deptPolicyTitle" type="java.lang.String" />

<body topmargin="0" leftmargin="0"
	onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayDepartmentPolicyName" target="content"
	onsubmit="return (myTinyMCEFix() &amp;&amp; validateSelectedCourts(this))"
	focus="group1Id">
	<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif"
				height="5" alt=""></td>
		</tr>
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><!--tabs start--> <tiles:insert
						page="../../../common/commonSupervisionTabs.jsp" flush="true" /> <!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img
						src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
				</tr>
			</table>

			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"
						alt=""></td>
				</tr>
				<tr>
					<td valign="top" align="center"><!-- BEGIN HEADING TABLE -->
					<table width="100%">
						<tr>
							<td align="center" class="header"><logic:equal
								name="departmentPolicyForm" property="action" value="copy">
								<input type="hidden" name="helpFile"
									value="commonsupervision/mso/manage_supervision_options.htm#|100">
								<bean:message key="prompt.copy" />
								<bean:message key="<%=deptPolicyTitle%>" />
							</logic:equal> <logic:equal name="departmentPolicyForm" property="action"
								value="create">
								<input type="hidden" name="helpFile"
									value="commonsupervision/mso/manage_supervision_options.htm#|90">
								<bean:message key="prompt.create" />
								<bean:message key="<%=deptPolicyTitle%>" />
							</logic:equal> <logic:equal name="departmentPolicyForm" property="action"
								value="update">
								<logic:notEqual name="departmentPolicyForm" property="inUse"
									value="true">
									<input type="hidden" name="helpFile"
										value="commonsupervision/mso/manage_supervision_options.htm#|94">
									<bean:message key="prompt.update" />
									<bean:message key="<%=deptPolicyTitle%>" />
								</logic:notEqual>
								<logic:equal name="departmentPolicyForm" property="inUse"
									value="true">
									<input type="hidden" name="helpFile"
										value="commonsupervision/mso/manage_supervision_options.htm#|95">
									<bean:message key="prompt.update" />
									<bean:message key="prompt.inUse" />
									<bean:message key="<%=deptPolicyTitle%>" />
								</logic:equal>
							</logic:equal></td>
						</tr>
					</table>
					<!-- END HEADING TABLE --> <!-- BEGIN Error TABLE -->
					<table width="98%" align="center">
						<tr>
							<td align="center" class="errorAlert"><html:errors></html:errors></td>
						</tr>
					</table>

					<!-- BEGIN INSTRUCTION TABLE -->
					<table width="98%" border="0">
						<tr>
							<td>
							<ul>
								<li>Enter the required fields</li>
								<li>Select Next.</li>
							</ul>
							</td>
						</tr>
						<tr>
							<td class="required"><bean:message key="prompt.4.diamond" /><bean:message
								key="prompt.requiredFieldsInstruction" />&nbsp;&nbsp;&nbsp;<bean:message
								key="prompt.dateFieldsInstruction" /></td>
						</tr>
					</table>

					<!-- BEGIN  TABLE -->
					<table width="98%" border="0" cellspacing="0"
						class="borderTableBlue">
						<tr>
							<td class="detailHead"><bean:message
								key="<%=deptPolicyTitle%>" /></td>
						</tr>
						<tr>
							<td>
							<table width="100%" cellspacing="1">
								<logic:notEqual name="departmentPolicyForm" property="action"
									value="<%=UIConstants.CREATE%>">
									<logic:notEqual name="departmentPolicyForm" property="action"
										value="<%=UIConstants.UPDATE%>">
										<logic:notEqual name="departmentPolicyForm" property="action"
											value="<%=UIConstants.COPY%>">
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.name" /></td>
												<td class="formDe"><bean:write
													name="departmentPolicyForm" property="name" /></td>
											</tr>
										</logic:notEqual>
									</logic:notEqual>
								</logic:notEqual>
								<logic:equal name="departmentPolicyForm" property="action"
									value="<%=UIConstants.UPDATE%>">
									<tr>
										<td class="formDeLabel"><bean:message
											key="prompt.4.diamond" /><bean:message key="prompt.name" /></td>
										<td class="formDe"><html:text name="departmentPolicyForm"
											property="name" size="50" maxlength="50" /></td>
									</tr>
								</logic:equal>
								<tr>
									<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message
										key="prompt.4.diamond" /><bean:message
										key="<%=departmentGroup1Caption%>" /></td>
									<td class="formDe"><input type="hidden"
										name="selectedGroup1"
										value='<bean:write name="departmentPolicyForm" property="group1Id"/>'>
									<logic:equal name="departmentPolicyForm" property="action"
										value="create">
										<html:select property="group1Id" size="1"
											onchange="updateGroup2(this.form);">
											<html:option value="">
												<bean:message key="select.generic" />
											</html:option>
											<html:optionsCollection property="groups" value="groupId"
												label="name" />
										</html:select>
									</logic:equal> <logic:notEqual name="departmentPolicyForm" property="action"
										value="create">
										<html:select property="group1Id" size="1"
											onchange="updateGroup2(this.form);checkGroupChange(this, 1, departmentPolicyForm);">
											<html:option value="">
												<bean:message key="select.generic" />
											</html:option>
											<html:optionsCollection property="groups" value="groupId"
												label="name" />
										</html:select>
									</logic:notEqual></td>
								</tr>

								<tr>
									<td class="formDeLabel" width="1%" nowrap="nowrap"
										<bean:message key="<%=departmentGroup2Caption%>"/>></td>
									<td class="formDe"><input type="hidden"
										name="selectedGroup2"
										value='<bean:write name="departmentPolicyForm" property="group2Id"/>'>
									<logic:equal name="departmentPolicyForm" property="action"
										value="create">
										<html:select property="group2Id" disabled="true"
											onchange="updateGroup3(this.form);">
											<html:option value="">
												<bean:message key="select.generic" />
											</html:option>
										</html:select>
									</logic:equal> <logic:notEqual name="departmentPolicyForm" property="action"
										value="create">
										<logic:empty name="departmentPolicyForm" property="group2">
											<html:select property="group2Id" disabled="true"
												onchange="updateGroup3(this.form);">
												<html:option value="">
													<bean:message key="select.generic" />
												</html:option>
											</html:select>
										</logic:empty>

										<logic:notEmpty name="departmentPolicyForm" property="group2">
											<html:select property="group2Id" size="1"
												onchange="updateGroup3(this.form);checkGroupChange(this, 2, departmentPolicyForm);">
												<html:option value="">
													<bean:message key="select.generic" />
												</html:option>
												<html:optionsCollection property="group2" value="groupId"
													label="name" />
											</html:select>
										</logic:notEmpty>
									</logic:notEqual></td>
								</tr>

								<tr>
									<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message
										key="<%=departmentGroup3Caption%>" /></td>
									<td class="formDe"><logic:equal
										name="departmentPolicyForm" property="action" value="create">
										<html:select property="group3Id" disabled="true">
											<html:option value="">
												<bean:message key="select.generic" />
											</html:option>
										</html:select>
									</logic:equal> <logic:notEqual name="departmentPolicyForm" property="action"
										value="create">
										<logic:empty name="departmentPolicyForm" property="group3">
											<logic:empty name="departmentPolicyForm" property="group2">
												<html:select property="group3Id" disabled="true">
													<html:option value="">
														<bean:message key="select.generic" />
													</html:option>
												</html:select>
											</logic:empty>
											<logic:notEmpty name="departmentPolicyForm" property="group2">
												<html:select property="group3Id">
													<html:option value="">
														<bean:message key="select.generic" />
													</html:option>
												</html:select>
											</logic:notEmpty>
										</logic:empty>

										<logic:notEmpty name="departmentPolicyForm" property="group3">
											<html:select property="group3Id" size="1">
												<html:option value="">
													<bean:message key="select.generic" />
												</html:option>
												<html:optionsCollection property="group3" value="groupId"
													label="name" />
											</html:select>
										</logic:notEmpty>
									</logic:notEqual></td>
								</tr>

								<tr>
									<td class="formDeLabel" width="1%" nowrap="nowrap" valign="top"><bean:message
										key="prompt.4.diamond" /><bean:message
										key="<%=departmentPolicyLiteralCaption%>" /></td>
									<td class="formDe"><logic:notEqual
										name="departmentPolicyForm" property="inUse" value="true">
										<html:textarea styleClass="mceEditor"
											name="departmentPolicyForm" property="departmentPolicy"
											style="width:100%" rows="5"
											ondblclick="myReverseTinyMCEFix(this)" />
										<script type="text/javascript">
																	customValRequired('departmentPolicy','Policy is required',null);
																	addDefinedTinyMCEFieldMask('departmentPolicy','Policy  contains invalid symbols. Invalid symbols are: % (percent) and _ (underscore).',null);
																	customValMinLength('departmentPolicy','Policy  must be at least 10 characters',10);
																	customValMaxLength('departmentPolicy','Policy  cannot be more than 1000 characters',2000);
																</script>
									</logic:notEqual> <logic:equal name="departmentPolicyForm" property="inUse"
										value="true">
										<html:hidden name="departmentPolicyForm"
											property="departmentPolicy" />
										<bean:write name="departmentPolicyForm"
											property="departmentPolicy" filter="false" />
									</logic:equal></td>
								</tr>
								<tiles:insert page="../../../common/spellCheckButtonTile.jsp"
									flush="true">
									<tiles:put name="agencyCode" beanName="departmentPolicyForm"
										beanProperty="agencyId" />
								</tiles:insert>
								<tr>
									<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message
										key="prompt.4.diamond" /><bean:message
										key="prompt.effectiveDate" /></td>
									<td class="formDe"><SCRIPT type="text/javascript" ID="js1">
													var cal1 = new CalendarPopup();
													cal1.showYearNavigation();
												</SCRIPT> <logic:notEqual name="departmentPolicyForm"
										property="inUse" value="true">
										<html:text name="departmentPolicyForm"
											property="effectiveDate" size="10" maxlength="10" />
										<A HREF="#"
											onClick="cal1.select(document.forms[0].effectiveDate,'anchor1','MM/dd/yyyy'); return false;"
											NAME="anchor1" ID="anchor1" border="0"><bean:message
											key="prompt.4.calendar" /></A>
									</logic:notEqual></td>
									<logic:notEqual value=""></logic:notEqual>
									<logic:equal name="departmentPolicyForm" property="inUse"
										value="true">
										<html:hidden name="departmentPolicyForm"
											property="effectiveDate" />
										<bean:write name="departmentPolicyForm"
											property="effectiveDate" />
									</logic:equal>
								</tr>

								<logic:equal name="departmentPolicyForm" property="action"
									value="update">
									<tr>
										<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message
											key="prompt.inactiveDate" /></td>
										<td class="formDe"><html:text name="departmentPolicyForm"
											property="inactiveDate" size="10" maxlength="10" /> <A
											HREF="#"
											onClick="cal1.select(document.forms[0].inactiveDate,'anchor2','MM/dd/yyyy'); return false;"
											NAME="anchor2" ID="anchor2" border="0"><bean:message
											key="prompt.4.calendar" /></A></td>
									</tr>
								</logic:equal>

								<tr>
									<td class="formDeLabel" valign="top" width="1%" nowrap="nowrap"><bean:message
										key="prompt.notes" /></td>
									<td class="formDe"><html:textarea
										name="departmentPolicyForm" property="notes"
										style="width:100%" rows="3" /></td>
								</tr>
								<tr>
									<td class="formDeLabel" colspan="4"><bean:message
										key="prompt.selectCourts" /></td>
								</tr>
								<tr>
									<tiles:insert page="../../../common/courts.jsp" flush="true">
										<tiles:put name="beanName" beanName="departmentPolicyForm" />
										<tiles:put name="ASOSpecialDisplay" value="ASOSpecialDisplay" />
										<tiles:put name="mode" value="select" />
									</tiles:insert>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					<br>

					<!-- association should not show up during create --> <logic:notEqual
						name="departmentPolicyForm" property="action"
						value="<%=UIConstants.CREATE%>">
						<table width="98%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td><!-- Associations --> <tiles:insert
									page="../../../common/associatedConditionsView.jsp"
									flush="true">
									<tiles:put name="associatedConditions"
										beanName="departmentPolicyForm"
										beanProperty="associatedConditions" />
									<tiles:put name="conditionGroup2Caption"
										beanName="departmentPolicyForm"
										beanProperty="conditionGroup2Caption" />
									<tiles:put name="conditionGroup3Caption"
										beanName="departmentPolicyForm"
										beanProperty="conditionGroup3Caption" />
								</tiles:insert></td>
							</tr>
						</table>
						<br>
					</logic:notEqual> <!-- BEGIN BUTTON TABLE -->
					<table border="0" width="100%">
						<tr>
							<td align="center"><logic:notEqual
								name="departmentPolicyForm" property="action"
								value="<%=UIConstants.CREATE%>">
								<input type="button" value="Back" name="return"
									onClick="history.go(-1)">
							</logic:notEqual> <html:submit property="submitAction"
								onclick="return (myTinyMCEFix() &amp;&amp; validateDept(this.form) &amp;&amp; validateSelectedCourts(this.form) &amp;&amp; compareDate(this.form) &amp;&amp; unhideGroups(this.form) &amp;&amp; disableSubmit(this, this.form));">
								<bean:message key="button.next" />
							</html:submit> <input type="reset" onclick="return customCourtReset(this.form)" />
							<input type="button" value='<bean:message key="button.cancel" />'>
							<!-- && return disableSubmit(this, this.form) --> <logic:equal
								name="departmentPolicyForm" property="action"
								value="<%=UIConstants.CREATE%>"> 
												onclick="return changeFormActionURL(this.form.name, '/<msp:webapp />globalCancel.do', false) && disableSubmit(this, this.form)"
											</logic:equal> <logic:notEqual name="departmentPolicyForm"
								property="action" value="<%=UIConstants.CREATE%>"> 
												onclick="return changeFormActionURL(this.form.name, '/<msp:webapp />displayDepartmentPolicySearch.do', false) && disableSubmit(this, this.form)"
											</logic:notEqual> ></td>
						</tr>
					</table>
					</td>
					<!-- END BUTTON TABLE -->
					<td></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<!-- END  TABLE --></div>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
