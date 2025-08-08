<!DOCTYPE HTML>
<!-- 08/02/2006	 C Shimek   - Create JSP -->
<!-- 04/03/2007  C Shimek	- revised to work with view action - #40922 -->
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

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading" /> - securityInquiriesUserGroupDetails.jsp</title>

<!-- INCLUDED JAVASCRIPT FILES -->
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/security_util.js"></script>

</head>

<body>
<html:form action="/displaySecurityInquiriesSummary" target="content">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|246">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header"><bean:message key="prompt.userGroup"/> <bean:message key="prompt.securityInfo"/></td>
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
<logic:equal name="securityInquiriesForm" property="action" value="">
	<table align="center" width="98%" border="0">
		<tr>
			<td>
				<ul>
					<li>Select hyperLink for more information.</li>
				</ul>
			</td>
		</tr>
	</table>
</logic:equal>	
<!-- END INSTRUCTION TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.userGroup"/> <bean:message key="prompt.securityInfo"/></td>
				</tr>
				<tr>
					<td align="center">
					<table cellpadding="0" cellspacing="0" border=0 width="98%" class="borderTable">
						<tr>
							<td class="boldText2px" bgcolor="#CCFFFF" colspan="3"><bean:message key="prompt.userGroup"/></td>
							<td bgcolor="#CCFFFF"><bean:write name="securityInquiriesForm" property="userGroupName" /></td>
						</tr>
						<tr>
							<td class="boldText2px" bgcolor="#CCFFFF" colspan="3"></td>
							<td bgcolor="#CCFFFF"><bean:write name="securityInquiriesForm" property="userGroupDescription" /></td>
						</tr>
						<tr>
							<td class="boldText2px" bgcolor="#CCFFFF" colspan="3"></td>
							<td bgcolor="#CCFFFF"><strong><bean:message key="prompt.agency"/></strong> <bean:write name="securityInquiriesForm" property="userGroupAgencyName" /></td>
						</tr>
						<logic:notEmpty name="securityInquiriesForm" property="roles">
						<% int ctr = 0; %>
			 			<logic:iterate id="roleIterator" name="securityInquiriesForm" property="roles">														
							<tr>
								<td width="1%" ><img src="/<msp:webapp/>images/spacer.gif" width="10"></td>
								<td colspan=2 class="boldText2px" bgcolor="#FFCCCC"><bean:message key="prompt.role"/></td>
								<logic:equal name="securityInquiriesForm" property="action" value="view">
									<td bgcolor="#FFCCCC">
										<bean:write name="roleIterator" property="roleName" />
									</td>
								</logic:equal>	
								<logic:equal name="securityInquiriesForm" property="action" value="">
									<td class="boldText2px" bgcolor="#FFCCCC">
										<a href="/<msp:webapp/>displaySecurityInquiriesRoleDetails.do?roleId=<bean:write name="roleIterator" property="roleId"/>" ><bean:write name="roleIterator" property="roleName" /></a>
									</td>
								</logic:equal>	
							</tr>
							<% ctr++; %>
							<tr>
								<td width="1%" ></td>
								<td width="1%"  valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="10"></td>
								<td class="boldText2px" bgcolor="#CCFFCC" valign="top">
									<a href="javascript:showHideExpand('features<%out.print(ctr); %>', 'row','/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="features<%out.print(ctr); %>"></a> <bean:message key="prompt.features" />
								</td>
								<td class="boldText" bgcolor="#CCFFCC">
									<span class="hidden" id=features<%out.print(ctr); %>Span>
									<table width="100%">
							 			<logic:iterate id="featureIterator" name="roleIterator" property="features">								
										<tr>
											<logic:equal name="securityInquiriesForm" property="action" value="view">										
												<td>
													<bean:write name="featureIterator" property="featureName"/>
												</td>
											</logic:equal>	
											<logic:equal name="securityInquiriesForm" property="action" value="">										
												<td class="boldText">
													<a href="/<msp:webapp/>displaySecurityInquiriesSubsystemDetails.do?featureId=<bean:write name="featureIterator" property="featureId"/>" ><bean:write name="featureIterator" property="featureName"/></a>
												</td>
											</logic:equal>	
										</tr>
										</logic:iterate>
									</table>
									</span>
								</td>
							</tr>
						</logic:iterate>
						</logic:notEmpty>
						<logic:notEmpty name="securityInquiriesForm" property="individualUsers">
						<tr>
							<td width="1%"  valign="top"></td>
							<td class="boldText2px" bgcolor="#F0F0F0" valign="top" colspan=2><bean:message key="prompt.user"/>s</td>
							<td class="alternateRow">
								<table width="100%">
						 			<logic:iterate id="userIterator" name="securityInquiriesForm" property="individualUsers">
										<tr>
											<logic:equal name="securityInquiriesForm" property="action" value="view">
												<td>
													<bean:write name="userIterator" property="formattedName"/>
												</td>
											</logic:equal>	
											<logic:equal name="securityInquiriesForm" property="action" value="">																				
												<td>
													<a href="/<msp:webapp/>displaySecurityInquiriesUserProfileDetails.do?logonId=<bean:write name="userIterator" property="logonId"/>" ><bean:write name="userIterator" property="formattedName"/></a>
												</td>
											</logic:equal>	
											<td>
												<bean:write name="userIterator" property="logonId" />
											</td>
											<td class="boldText" width="1%" nowrap>
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
					</table>
				</td>
			</tr>
		</table>
	<br>
<!-- BEGIN BUTTON TABLE -->
<table width="100%">
	<tr>
		<td align="center">
			<logic:equal name="securityInquiriesForm" property="action" value="">
				<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
					<bean:message key="button.back"></bean:message>
				</html:button>
				<html:submit property="submitAction">
					<bean:message key="button.cancel" />
				</html:submit>
			</logic:equal>							
			<logic:equal name="securityInquiriesForm" property="action" value="view">
				<input type="button" name="close" value="Close Window" onclick="javascript:window.close()">
			</logic:equal>			
		</td>
	</tr>
</table>			
<!-- END BUTTON TABLE -->
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>