<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/26/2008	 C Shimek       - defect#52199 create this jsp for pop-up, modified version of manageWorkgroup/details.jsp -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

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
	<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
	<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<html:base />
<title>CommonSupervision/administerCaseload/workgroupDetails.jsp</title>

</head>

<body topmargin="0" leftmargin="0">
<html:form action="/submitWorkgroupCreateUpdate" target="content">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
  	</tr>
  	<tr>
  		<td valign="top">
<!-- BEGIN ERROR TABLE -->
			<table width=98% align=center>							
				<tr>
					<td align="center" class="errorAlert"><html:errors></html:errors></td>
				</tr>		
			</table>
<!-- END ERROR TABLE -->
<!-- BEGIN WORKGROUP INFO TABLE -->
			<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
				<tr class="detailHead">
					<td class="detailHead" ><bean:write name="workgroupForm" property="workgroupName" />&nbsp;<bean:message key="prompt.info" /></td>				                        	
					<td align="right"><img src="/<msp:webapp/>images/step_1.gif"></td>				                          
				</tr>
				<tr>
					<td colspan=2>
						<table width="100%" cellpadding="4" cellspacing="1" border="0">	
							<tr>				                          	
								<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.workgroupName" /></td>
								<td class="formDe"><bean:write name="workgroupForm" property="workgroupName" /></td>		
							</tr>
							<tr>				                          	
								<td class="formDeLabel"><bean:message key="prompt.description" /></td>
								<td class="formDe"><bean:write name="workgroupForm" property="workgroupDescription" /></td>		
							</tr>
							<tr>				                          	
								<td class="formDeLabel"><bean:message key="prompt.type" /></td>
								<td class="formDe"><bean:write name="workgroupForm" property="workgroupTypeDesc" /></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
<!-- END WORKGROUP INFO TABLE -->			
		<br>
<!-- BEGIN WORKGROUP USERS TABLE -->
			<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
				<tr class="detailHead">
					<td class="detailHead"><bean:write name="workgroupForm" property="workgroupName" />&nbsp;<bean:message key="prompt.staff" /></td>				                        	
					<td align=right><img src="/<msp:webapp/>images/step_2.gif"></td>				                          
				</tr>
				<tr>
					<td colspan="2">
						<table width="100%" cellpadding="2" cellspacing="1">
							<tr class="formDeLabel">
								<td>
									<jims2:sortResults beanName="workgroupForm" results="userSelectedList" primaryPropSort="formattedName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" hideMe="true" />  
									<bean:message key="prompt.name" />
								</td>
								<td><bean:message key="prompt.jobTitle" /></td>
								<td><bean:message key="prompt.division" /></td>
								<td><bean:message key="prompt.programUnit" /></td>
								<td><bean:message key="prompt.positionType" /></td>
							</tr>
							<logic:notEmpty name="workgroupForm" property="userSelectedList">
								<logic:iterate id="workgroupUserListIndex" name="workgroupForm" property="userSelectedList" indexId="index">
									<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" >
										<td><bean:write name="workgroupUserListIndex" property="formattedName" /></td>
										<td><bean:write name="workgroupUserListIndex" property="jobTitleDesc" /></td>
										<td><bean:write name="workgroupUserListIndex" property="divisionDesc" /></td>
										<td><bean:write name="workgroupUserListIndex" property="programUnitDesc" /></td>
										<td><bean:write name="workgroupUserListIndex" property="positionTypeDesc" /></td>
									</tr>										                
								</logic:iterate>
							</logic:notEmpty>
						</table>
					</td>
				</tr>
			</table>
<!-- END WORKGROUP USERS TABLE -->
			<br>
<!-- BEGIN BUTTON TABLE -->
			<table border="0" width="100%">
				<tr>
					<td align="center">
						<input type="button" value="Close Window" name="close" onClick="window.close()">
					</td>
				</tr>										  	
			</table>
<!-- END BUTTON TABLE -->
		</td>
	</tr>
</table>
</div>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>