<!DOCTYPE HTML>
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<!-- 03/03/2006  Clarence Shimek  ER#28542 add disable button to finish button -->
<!-- 09/06/2006  Hien Rodriguez  ER#34507 Implement new UI Guidelines for date fields -->
<!-- 01/19/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 10/19/2007  Clarence Shimek defect#46036 add cursor set -->
<!-- 04/08/2010	 LDeen Defect #64811 remove courts section per DJN -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@ page import="org.apache.struts.action.Action"%>
<%@ page import="org.apache.struts.action.ActionErrors"%>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/csbase.css" />
<html:base />
<title>commonsupervision/JUV/supervisionOption/condition/conditionSearch.jsp</title>

<html:javascript formName="conditionSearchForm" />
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript"
	src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/groups.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>

<script type="text/javascript">
<logic:iterate indexId="groupIterIndex" id="groupIter" name="supervisionConditionForm" property="groups">
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

</head>

<bean:define id="conditionGroup1Caption" type="java.lang.String"
	value="prompt.category" />
<bean:define id="conditionGroup2Caption" type="java.lang.String"
	value="prompt.type" />
<bean:define id="conditionGroup3Caption" type="java.lang.String"
	value="prompt.subtype" />

<body topmargin="0" leftmargin="0"
	onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displaySupervisionConditionSearchResults"
	target="content" focus="conditionName">
	<input type="hidden" name="helpFile"
		value="commonsupervision/mso_juvenile/mso_juvenile.htm#|1">

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
					<td valign="top" align="center">

					<table width="100%">
						<tr>
							<td align="center" class="header"><bean:message
								key="title.search" />&nbsp;<bean:message
								key="title.supervisionCondition" /></td>
						</tr>
						<tr>
							<td align="center" class="errorAlert"><html:errors
								property="<%=ActionErrors.GLOBAL_MESSAGE%>" /></td>
						</tr>
					</table>
					<!-- END HEADING TABLE --> <!-- BEGIN INSTRUCTION TABLE -->
					<table width="98%" border="0">
						<tr>
							<td>
							<ul>
								<li>Enter search criteria.</li>
							</ul>
							</td>
						</tr>
						<tr>
							<td class="required"><bean:message
								key="prompt.dateFieldsInstruction" /></td>
						</tr>
					</table>
					<!-- BEGIN  TABLE -->
					<table width="98%" border="0" cellspacing="0" cellpadding="2"
						class="borderTableBlue">
						<tr>
							<td class="detailHead"><bean:message key="title.search" />&nbsp;<bean:message
								key="title.supervisionCondition" /></td>
						</tr>
						<tr>
							<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="2">
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.name" /></td>
									<td class="formDe"><html:text
										name="supervisionConditionForm" property="conditionName" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.literal" /></td>
									<td class="formDe"><html:text
										name="supervisionConditionForm" property="conditionLiteral"
										size="60" /></td>
								</tr>
								<tr>
									<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message
										key="<%=conditionGroup1Caption%>" /></td>
									<td class="formDe"><html:select property="group1Id"
										size="1" onchange="updateGroup2(this.form);">
										<html:option value="">
											<bean:message key="select.generic" />
										</html:option>
										<html:optionsCollection property="groups" value="groupId"
											label="name" />
									</html:select></td>
								</tr>
								<tr>
									<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message
										key="<%=conditionGroup2Caption%>" /></td>
									<td class="formDe"><html:select property="group2Id"
										disabled="true" onchange="updateGroup3(this.form);">
										<html:option value="">
											<bean:message key="select.generic" />
										</html:option>
									</html:select></td>
								</tr>
								<tr class="hidden">
									<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message
										key="<%=conditionGroup3Caption%>" /></td>
									<td class="formDe" colspan="2"><html:select
										property="group3Id" disabled="true">
										<html:option value="">
											<bean:message key="select.generic" />
										</html:option>
									</html:select></td>
								</tr>
								<tr>
									<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message
										key="prompt.supervisionType" /></td>
									<td class="formDe"><html:select
										property="selSupervisionTypes" size="1">
										<html:option value="">
											<bean:message key="select.generic" />
										</html:option>
										<html:optionsCollection property="supervisionTypes"
											value="code" label="description" />
									</html:select></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message
										key="prompt.standard" /></td>
									<td class="formDe"><html:select
										property="standardSearchCriteria" size="1">
										<html:optionsCollection property="standardOptions"
											value="code" label="description" />
									</html:select></td>
								</tr>

								<tr>
									<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message
										key="prompt.effectiveDate" /></td>
									<td class="formDe"><SCRIPT type="text/javascript" ID="js1">
													var cal1 = new CalendarPopup();
													cal1.showYearNavigation();
												</SCRIPT> <html:text name="supervisionConditionForm"
										property="effectiveDate" size="10" maxlength="10" /> <A
										HREF="#"
										onClick="cal1.select(document.forms[0].effectiveDate,'anchor1','MM/dd/yyyy'); return false;"
										NAME="anchor1" ID="anchor1" border="0"><bean:message
										key="prompt.4.calendar" /></A></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message
										key="prompt.inactiveDate" /></td>
									<td class="formDe"><html:text
										name="supervisionConditionForm" property="inactiveDate"
										size="10" maxlength="10" /> <A HREF="#"
										onClick="cal1.select(document.forms[0].inactiveDate,'anchor2','MM/dd/yyyy'); return false;"
										NAME="anchor2" ID="anchor2" border="0"><bean:message
										key="prompt.4.calendar" /></A></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.status" /></td>
									<td class="formDe"><html:select
										property="conditionStatusId" size="1">
										<html:option value="">
											<bean:message key="prompt.selectALL" />
										</html:option>
										<html:optionsCollection property="statusCodes" value="code"
											label="description" />
									</html:select></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					<br>
					<!-- BEGIN BUTTON TABLE -->
					<table border="0" width="100%">
						<tr>
							<td align="center"><html:submit property="submitAction"
								onclick="return validateConditionSearchForm(this.form) &amp;&amp; disableSubmit(this, this.form);">
								<bean:message key="button.submit" />
							</html:submit> <input type="button"
								onclick="goNav('/<msp:webapp/>displaySupervisionConditionSearch.do')"
								value="<bean:message key='button.refresh'/>" /> <input
								type="button" value='<bean:message key="button.cancel" />'
								name="return"
								onclick="return changeFormActionURL(this.form.name, '/<msp:webapp/>globalCancel.do', false) &amp;&amp; disableSubmit(this, this.form)">
							</td>
						</tr>
					</table>
					<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>
			<br>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
	updateGroup2(document.forms[0]);
</script> <!-- END  TABLE --></div>

</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
