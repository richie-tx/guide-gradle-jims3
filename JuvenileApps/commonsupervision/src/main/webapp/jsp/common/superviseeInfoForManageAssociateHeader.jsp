<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/03/2006	 Justin Jose - Create JSP -->
<!-- 08/28/2006  Hien Rodriguez  ER#34507 Implement new UI Guidelines for all date fields -->
<!-- 02/25/2009  Debbie Williamson - Changed Header information source from SuperviseeInfoHeaderForm to SuperviseeHeaderForm
      because of Servlet Exception error. -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
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
<title>Common Supervision - common/superviseeInfoForManageAssociateHeader.jsp</title>
</head>

	<table width="100%" border="0" cellpadding="2" cellspacing="1">
       <tr>
       		<td class="headerLabel">Name</td>
	        <td class="headerData">
				<bean:write name="superviseeHeaderForm" property="superviseeNameDesc" />
				<logic:equal name="superviseeHeaderForm" property="compliant" value="true">
					<img src="/<msp:webapp/>images/greenLight.gif" name="greenLight" title="In Compliance" border="0">
				</logic:equal>
				<logic:notEqual name="superviseeHeaderForm" property="compliant" value="true">
					<img src="/<msp:webapp/>images/redLight.gif" name="redLight" title="Out of Compliance" border="0">
				</logic:notEqual>
			</td>
			<td class="headerLabel">SPN</td>
	       	<td class="headerData">
				<bean:write name="superviseeHeaderForm" property="superviseeSpn"/>
			</td>
	   </tr>
       <tr>
	       <td class="headerLabel"><bean:message key="prompt.officer" /></td>
		   <td class="headerData"><bean:write name="superviseeHeaderForm" property="officerNameDesc"/></td>
		   <td class="headerLabel"><bean:message key="prompt.LOS" /></td>
		   <td class="headerData"><bean:write name="superviseeHeaderForm" property="LOSDesc" /> </td>
	   </tr>
	   <tr>
			<td class="headerLabel"><bean:message key="prompt.programUnit" /></td>
			<td colspan="3" class="headerData"><bean:write name="superviseeHeaderForm" property="programUnitDesc"/></td>
		</tr>
	</table>
</html:html>