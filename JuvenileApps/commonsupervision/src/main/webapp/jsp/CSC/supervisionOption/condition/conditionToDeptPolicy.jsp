<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<!-- 03/31/2006  Hien Rodriguez  defect#300113 display correct labels for each agency -->
<!-- 07/31/2006  Hien Rodriguez  defect#33487 display correct page title for each agency -->
<!-- 10/10/2006  Hien Rodriguez  defect#35400 display correct details title for each agency -->
<!-- 01/19/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. --> 

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<head>
<!--BEGIN HEADER TAG-->
	<msp:nocache />

	<%-- Checks to make sure if the user is logged in. --%>
	<%--msp:login / --%>
	<meta http-equiv="Content-Language" content="en-us"/>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	<meta name="GENERATOR" content="IBM WebSphere Studio"/>
	<meta http-equiv="Content-Style-Type" content="text/css"/>
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
	<html:base />
	<title><bean:message key="title.heading" /> - conditionToDeptPolicy.jsp</title>
	<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/groups.js"></script>	
</head>

<bean:define id="deptPolicyTitle" type="java.lang.String" value="prompt.policyTableTitle"/>
<bean:define id="deptPolicyAssoTitle" value="prompt.departmentPolicies" type="java.lang.String"/>
<bean:define id="trueTitle">Associate Supervision Condition to <bean:message key="<%=deptPolicyTitle%>"/></bean:define>
<bean:define id="filterDpTitle">Filter <bean:message key="<%=deptPolicyTitle%>"/></bean:define>
<bean:define id="dpNameTitle"><bean:message key="<%=deptPolicyTitle%>"/> Name</bean:define>
<bean:define id="selectedDpTitle">Current/Selected <bean:message key="<%=deptPolicyAssoTitle%>"/></bean:define>


<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="setFocus()">
<input type="hidden" name="helpFile" value="commonsupervision/mso/manage_supervision_options.htm#|45">
<tiles:insert page="../../../common/associationBody.jsp" flush="true">

	<!-- Form related -->
	<tiles:put name="form" beanName="supervisionConditionForm"/>
	<tiles:put name="formActionName" value="/handleConditionAssociateToDepartmentPolicy"/>
	<tiles:put name="group1Caption" value="prompt.group1"/>
	<tiles:put name="group2Caption" value="prompt.group2"/>
	<tiles:put name="group3Caption" value="prompt.group3"/>
	
	<!-- Main Heading -->
	<tiles:put name="pageTitle" value="<%=trueTitle%>" />
	<tiles:put name="pageInstructionText" value="Filter by Group, Name" />

	<!-- Search Criteria -->
	<tiles:put name="searchTitle" value="<%=filterDpTitle%>" />
	
	<!-- Search Result Table -->
	<tiles:put name="searchResultIter" beanName="supervisionConditionForm" beanProperty="assocSearchResults" />
	<tiles:put name="searchTableHeadingName" value="<%=dpNameTitle%>" />
	<tiles:put name="viewURL" value="/CommonSupervision/displayDepartmentPolicyView.do?viewOnly=true&policyId=" />
	
	<!-- Selected Table -->
	<tiles:put name="selectedTitle" value="<%=selectedDpTitle%>" />
	<tiles:put name="selectedResultIter" beanName="supervisionConditionForm" beanProperty="associatedDeptPolicies" />
	<tiles:put name="selectedTableHeadingName" value="<%=dpNameTitle%>" />
	<tiles:put name="removeURL" value="handleConditionAssociateToDepartmentPolicyRemove" />
	
</tiles:insert>	

</body>
</html:html>

