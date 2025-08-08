<!DOCTYPE HTML>
<!-- 08/02/2006	 C Shimek   - Create JSP -->
<!-- 04/03/2007  C Shimek	- revised to work with view action  - #40922 -->
<!-- 02/06/2009  C Shimek   - #56860 add Back to Top  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG -->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading" /> - securityInquiriesUserProfileDetails.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<!-- INCLUDED JAVASCRIPT FILES -->
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/security_util.js"></script>

</head>

<body>
<html:form action="/displaySecurityInquiriesSummary" target="content">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|253">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header"><bean:message key="prompt.userProfile"/> <bean:message key="prompt.securityInfo"/></td>
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
				<li>Select hyperlink for more information.</li>
				<li>Select User Profile to view User Profile Details.</li>		
			</ul>
		</td>
	</tr>
</table>
</logic:equal>
<!-- END INSTRUCTION TABLE -->
<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">
	<tr class="detailHead">
		<td class="detailHead"><bean:message key="prompt.userProfile"/> <bean:message key="prompt.securityInfo"/></td>
	</tr>
	<tr>
		<td align="center">
 			<table cellpadding="0" cellspacing="0" border="0" width="98%" class="borderTable">
				<tr>
					<td colspan="5">
						<table bgcolor="#F0F0F0" border="0">
							<tr>
								<td class="boldText2px" valign="top"><bean:message key="prompt.user"/></td>
								<td><bean:write name="securityInquiriesForm" property="formattedName"></bean:write>&nbsp;
								<logic:equal name="securityInquiriesForm" property="action" value="">
									<a href="/<msp:webapp/>handleUserProfileSelection.do?submitAction=View&logonId=<bean:write name="securityInquiriesForm" property="logonId" />"> View User Profile </a>
								</logic:equal>
								</td> 
							</tr>
							<tr>
								<td class="boldText2px" width="1%" nowrap><bean:message key="prompt.userId"/></td>
								<td><bean:write name="securityInquiriesForm" property="logonId" /><%-- 79250 &nbsp;<bean:write name="securityInquiriesForm" property="jims2LogonId" /> --%></td>
							</tr>							
						</table> 
					</td>
				</tr>
				<tr>
					<td colspan="5" bgcolor="#f0f0f0" valign="top">&nbsp;<strong><bean:message key="prompt.agencyCode"/></strong>&nbsp;<bean:write name="securityInquiriesForm" property="agencyId" />&nbsp;&nbsp;<strong><bean:message key="prompt.dept"/></strong>&nbsp;<bean:write name="securityInquiriesForm" property="departmentName" /> <span class="bodyText">(<bean:write name="securityInquiriesForm" property="departmentId" />)</span></td>
				</tr> 
				<logic:notEmpty name="securityInquiriesForm" property="serviceProviderName">
					<logic:notEqual name="securityInquiriesForm" property="serviceProviderName" value="">
						<tr>
							<td colspan="5" bgcolor="#f0f0f0">&nbsp;<strong>ServiceProvider</strong>&nbsp;<bean:write name="securityInquiriesForm" property="serviceProviderName" /></td>
						</tr>
					</logic:notEqual>
				</logic:notEmpty>
				<% int ctr = 0; %>
				<logic:iterate id="ugIterator" name="securityInquiriesForm" property="userGroups">	
					<tr>
						<td width="1%"><img src="/<msp:webapp/>images/spacer.gif" width="10"></td>
						<td class="boldText2px" bgcolor="#CCFFFF" colspan="3"><bean:message key="prompt.userGroup"/></td>
						<td bgcolor="#CCFFFF">
							<logic:equal name="securityInquiriesForm" property="action" value="">
								<a href="/<msp:webapp/>displaySecurityInquiriesUserGroupDetails.do?userGroupId=<bean:write name="ugIterator" property="userGroupId"/>" ><bean:write name="ugIterator" property="name" /></a>
							</logic:equal>
							<logic:equal name="securityInquiriesForm" property="action" value="view">
								<bean:write name="ugIterator" property="name" />
							</logic:equal>
						</td>
					</tr>
					<logic:iterate id="roleIterator" name="ugIterator" property="roles">	
						<tr>
							<td></td>
							<td width="1%"><img src="/<msp:webapp/>images/spacer.gif" width="10"></td>
							<td colspan="2" class="boldText2px" bgcolor="#FFCCCC"><bean:message key="prompt.role"/></td>
							<td bgcolor="#FFCCCC">
								<logic:equal name="securityInquiriesForm" property="action" value="">
									<a href="/<msp:webapp/>displaySecurityInquiriesRoleDetails.do?roleId=<bean:write name="roleIterator" property="roleId"/>" ><bean:write name="roleIterator" property="roleName" /></a>
								</logic:equal>	
								<logic:equal name="securityInquiriesForm" property="action" value="view">
									<bean:write name="roleIterator" property="roleName" />
								</logic:equal>	
							</td>
						</tr>
						<logic:notEmpty name="roleIterator" property="features">
							<% ctr++; %>
							<tr>  
								<td colspan="2"></td>
								<td width="1%"><img src="/<msp:webapp/>images/spacer.gif" width="10"></td>
								<td class="boldText2px" bgcolor="#CCFFCC" valign="top">
									<a href="javascript:showHideExpand('uFeatures<% out.print(ctr); %>', 'row','/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="uFeatures<% out.print(ctr); %>"></a> <bean:message key="prompt.features" />
								</td>
								<td class="boldText2px" bgcolor="#CCFFCC">
									<span class="hidden" id="uFeatures<% out.print(ctr); %>Span">
										<table width="100%">
											<logic:iterate id="featureIterator" name="roleIterator" property="features">						
												<tr>
													<logic:equal name="securityInquiriesForm" property="action" value="">
														<td class="boldText">
															<a href="/<msp:webapp/>displaySecurityInquiriesSubsystemDetails.do?featureId=<bean:write name="featureIterator" property="featureId"/>" ><bean:write name="featureIterator" property="featureName"/></a> 
														</td>	
													</logic:equal>	
													<logic:equal name="securityInquiriesForm" property="action" value="view">
														<td>
															<bean:write name="featureIterator" property="featureName"/>
														</td>
													</logic:equal>	
												</tr>
											</logic:iterate>
										</table>
									</span>
								</td>
							</tr>
						</logic:notEmpty>
					</logic:iterate>	
				</logic:iterate>	
				<logic:notEmpty name="securityInquiriesForm" property="roles">	
					<% ctr = 0; %>												
					<tr>
						<td colspan="5"><hr noshade></td>
					</tr>
					<tr>
						<td colspan="5" class="boldText2px" bgcolor="#f0f0f0" valign="top"><bean:message key="prompt.individual"/> <bean:message key="prompt.role"/>s</td>
					</tr>
					<logic:iterate id="iRoles2Iterator" name="securityInquiriesForm" property="roles">
						<tr>
							<td width="1%"></td>
							<td width="1%" ><img src="/<msp:webapp/>images/spacer.gif" width="10"></td>
							<td colspan="2" class="boldText2px" bgcolor="#FFCCCC"><bean:message key="prompt.role"/></td>
							<logic:equal name="securityInquiriesForm" property="action" value="">
								<td class="boldText2px" bgcolor="#FFCCCC">
									<a href="/<msp:webapp/>displaySecurityInquiriesRoleDetails.do?roleId=<bean:write name="iRoles2Iterator" property="roleId"/>" ><bean:write name="iRoles2Iterator" property="roleName" /></a>
								</td>
							</logic:equal>	
							<logic:equal name="securityInquiriesForm" property="action" value="view">
								<td bgcolor="#FFCCCC">
									<bean:write name="iRoles2Iterator" property="roleName" />
								</td>	
							</logic:equal>	
						</tr>
						<logic:notEmpty name="iRoles2Iterator" property="features">
							<% ctr++; %>
							<tr>
								<td colspan="2"></td>
								<td width="1%"><img src="/<msp:webapp/>images/spacer.gif" width="10"></td>								
								<td class="boldText2px" bgcolor="#CCFFCC" valign="top"><a href="javascript:showHideExpand('rFeatures<% out.print(ctr); %>', 'row','/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="rFeatures<% out.print(ctr); %>"></a> <bean:message key="prompt.features" /></td>
								<td class="boldText" bgcolor="#CCFFCC">
								<span class="hidden" id="rFeatures<% out.print(ctr); %>Span">
									<table width="100%">
										<logic:iterate id="feature2Iterator" name="iRoles2Iterator" property="features">
											<tr>
												<logic:equal name="securityInquiriesForm" property="action" value="">
													<td class="boldText">
														<a href="/<msp:webapp/>displaySecurityInquiriesSubsystemDetails.do?featureId=<bean:write name="feature2Iterator" property="featureId"/>" ><bean:write name="feature2Iterator" property="featureName"/></a>
													</td>
												</logic:equal>	
												<logic:equal name="securityInquiriesForm" property="action" value="view">
													<td>												
														<bean:write name="feature2Iterator" property="featureName"/>
													</td>
												</logic:equal>	
											</tr>
										</logic:iterate>
									</table>
								</span>
								</td>
							</tr>
						</logic:notEmpty>
					</logic:iterate>
				</logic:notEmpty>
			</table>
		</td>
	</tr>
</table> 

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
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
