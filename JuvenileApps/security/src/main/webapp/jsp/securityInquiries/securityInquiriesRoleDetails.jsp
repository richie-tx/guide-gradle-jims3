<!DOCTYPE HTML>
<!-- 08/02/2006	 C Shimek   - Create JSP -->
<!-- 02/06/2009  C Shimek   - #56860 add Back to Top  -->
<!-- 10/19/2015  R Young    - #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->



<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading" /> - securityInquiriesRoleDetails.jsp</title>

<!-- INCLUDED JAVASCRIPT FILES -->
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/security_util.js"></script>

</head>

<body>
<html:form action="/displaySecurityInquiriesSummary" target="content">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|191">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header"><bean:message key="prompt.role"/> <bean:message key="prompt.securityInfo"/></td>
	</tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<table align="center" width="98%" border="0">
	<tr>
		<td>
			<ul>
				<li>Select hyperlink for more information.</li>
			</ul>
		</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">
	<tr class="detailHead">
		<td class="detailHead"><bean:message key="prompt.role"/> <bean:message key="prompt.securityInfo"/></td>
	</tr>
	<tr>
		<td align="center">  
			<table cellpadding="0" cellspacing="0" border="0" width="98%" class="borderTable">
				<tr>
					<td class="boldText2px" bgcolor="#FFCCCC" colspan="3"><bean:message key="prompt.role" /></td>
					<td class="boldText2px" bgcolor="#FFCCCC"><bean:write name="securityInquiriesForm" property="roleName" /></td>
				</tr>
				<tr>
					<td class="boldText2px" bgcolor="#FFCCCC" colspan="3"></td>
					<td bgcolor="#FFCCCC"><bean:write name="securityInquiriesForm" property="roleDescription" /></td>
				</tr>
				<tr>
					<td class="boldText2px" bgcolor="#FFCCCC" colspan="3"></td>
					<td bgcolor="#FFCCCC">&nbsp;<strong><bean:message key="prompt.agency" /></strong>&nbsp;<bean:write name="securityInquiriesForm" property="roleAgencyName" /></td>
				</tr>						
				<tr>
					<td width="1%"  valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="10"></td>
					<td class="boldText2px" bgcolor="#CCFFCC" valign="top" colspan="2" nowrap>
						<a href="javascript:showHideExpand('features', 'row','/<msp:webapp/>')" ><img border=0 src="/<msp:webapp/>images/expand.gif" name="features"></a> <bean:message key="prompt.feature" />s
					</td>
					<td class="boldText" bgcolor="#CCFFCC" >
					<span class="hidden" id="featuresSpan">
						<table width="100%">
	 					<logic:iterate id="featureIterator" name="securityInquiriesForm" property="features">								
							<tr bgcolor="#CCFFCC">
								<td class="boldText">
									<a href="/<msp:webapp/>displaySecurityInquiriesSubsystemDetails.do?featureId=<bean:write name="featureIterator" property="featureId"/>" ><bean:write name="featureIterator" property="featureName"/></a>
								</td>
							</tr>
							</logic:iterate>
						</table>
					</span>
					</td>
				</tr>
	<!--  BEGIN USER GROUP DISPLAY -->					
 				<logic:iterate id="userGroupIterator" name="securityInquiriesForm" property="userGroups">							
					<tr>
						<td width="1%"  valign="top"></td>
						<td class="boldText2px" bgcolor="#CCFFFF" valign="top" colspan="2" nowrap> <bean:message key="prompt.userGroup" /></td>
						<td class="boldText" bgcolor="#CCFFFF">
							<a href="/<msp:webapp/>displaySecurityInquiriesUserGroupDetails.do?userGroupId=<bean:write name="userGroupIterator" property="userGroupId"/>" ><bean:write name="userGroupIterator" property="name"/></a>
						</td>
					</tr>
					<logic:notEmpty name="userGroupIterator" property="users">
					<tr>
						<td width="1%"  valign="top"></td>
						<td width="1%"  valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="10"></td>
						<td class="boldText2px" bgcolor="#F0F0F0" valign="top"><bean:message key="prompt.user" />s</td>
						<td class="alternateRow">
							<table width="100%">
			 					<logic:iterate id="userIterator" name="userGroupIterator" property="users">
									<tr>
										<td width="60%">
											<a href="/<msp:webapp/>displaySecurityInquiriesUserProfileDetails.do?logonId=<bean:write name="userIterator" property="logonId"/>" ><bean:write name="userIterator" property="formattedName"/></a>
										</td>
										<td width="10%">
											<bean:write name="userIterator" property="logonId" />
										</td>
										<td class="boldText" width="10%" nowrap>
											<bean:message key="prompt.deptCode" />
										</td>
										<td>
											<bean:write name="userIterator" property="departmentId" />
										</td>
									</tr>						
								</logic:iterate> 
							</table>
						</td>
					</tr>
					</logic:notEmpty>	
				</logic:iterate> 						
	<!--  END USER GROUP DISPLAY -->	
				<logic:notEmpty name="securityInquiriesForm" property="individualUsers">						
				<tr>
					<td colspan="5"><hr noshade></td>
				</tr>
				<tr>
					<td colspan="3" class="boldText2px" bgcolor="#F0F0F0" valign="top" nowrap><bean:message key="prompt.individual" /> <bean:message key="prompt.user" />s</td>
					<td class="alternateRow">
						<table width="100%">
							<logic:iterate id="indUserIterator" name="securityInquiriesForm" property="individualUsers">
								<tr>
									<td width="60%" nowrap>
										<a href="/<msp:webapp/>displaySecurityInquiriesUserProfileDetails.do?logonId=<bean:write name="indUserIterator" property="logonId"/>" ><bean:write name="indUserIterator" property="formattedName"/></a>
									</td>
									<td width="10%">
										<bean:write name="indUserIterator" property="logonId" />
									</td>
									<td class="boldText" width="10%" nowrap>
										<bean:message key="prompt.deptCode" />
									</td>
									<td>
										<bean:write name="indUserIterator" property="departmentId" />
									</td>
								</tr>
							</logic:iterate> 
						</table>
					</td>
				</tr>
				</logic:notEmpty>
			</table>
		</td>
	</tr>
</table>
<br>
<!-- BEGIN BUTTON TABLE -->
<table width="100%">
	<tr>
		<td align="center">
			<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				<bean:message key="button.back"></bean:message>
			</html:button>
			<html:submit property="submitAction">
				<bean:message key="button.cancel" />
			</html:submit>
		</td>
	</tr>
</table>			
<!-- END BUTTON TABLE -->
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>