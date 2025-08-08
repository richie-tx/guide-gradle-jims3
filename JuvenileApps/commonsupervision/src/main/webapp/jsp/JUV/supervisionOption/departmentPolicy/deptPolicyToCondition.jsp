<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<!-- 04/03/2006  Hien Rodriguez  defect#300113 display correct labels for each agency -->
<!-- 07/31/2006  Hien Rodriguez  defect#33487 display correct page title for each agency -->
<!-- 01/23/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

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
	<title><bean:message key="title.heading" /> - departmentPolicyToCondition.jsp</title>
	<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/groups.js"></script>	
</head>

<bean:define id="deptPolicyTitle" value="prompt.policyTableTitle" type="java.lang.String"/>
<bean:define id="trueTitle">Associate <bean:message key="<%=deptPolicyTitle%>"/> to Condition</bean:define>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<input type="hidden" name="helpFile" value="commonsupervision/mso_juvenile/mso_juvenile.htm#|68">
<tiles:insert page="../../../common/associationBody.jsp" flush="true">

	<!-- Form related -->
	<tiles:put name="form" beanName="departmentPolicyForm"/>
	<tiles:put name="formActionName" value="/handleDepartmentPolicyAssociateToCondition"/>
	<tiles:put name="group1Caption" value="prompt.category"/>
	<tiles:put name="group2Caption" value="prompt.type"/>
	<tiles:put name="group3Caption" value="prompt.subtype"/>
	
	<!-- Main Heading -->
	<tiles:put name="pageTitle" value="<%=trueTitle%>" />
	<tiles:put name="pageInstructionText" value="Filter by Category/Type/Subtype, Name" />

	<!-- Search Criteria -->
	<tiles:put name="searchTitle" value="Filter Conditions" />
	
	<!-- Search Result Table -->
	<tiles:put name="searchResultIter" beanName="departmentPolicyForm" beanProperty="assocSearchResults" />
	<tiles:put name="searchTableHeadingName" value="Condition Name" />
	<tiles:put name="viewURL" value="/CommonSupervision/displaySupervisionConditionView.do?viewOnly=true&conditionId=" />
	
	<!-- Selected Table -->
	<tiles:put name="selectedTitle" value="Current/Selected Conditions" />
	<tiles:put name="selectedResultIter" beanName="departmentPolicyForm" beanProperty="associatedConditions" />
	<tiles:put name="selectedTableHeadingName" value="Condition Name" />
	<tiles:put name="removeURL" value="handleDepartmentPolicyAssociateToConditionRemove" />
	
</tiles:insert>	

</body>
</html:html>

