<!DOCTYPE HTML>
<!-- 08/02/2006	 C Shimek   - Create JSP -->
<!-- 02/06/2009  C Shimek   - #56860 add Back to Top  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

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

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />

<html:base />
<title><bean:message key="title.heading" /> - securityInquiriesAgencyPopUpDetails.jsp</title>

<!-- INCLUDED JAVASCRIPT FILES -->
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/security_util.js"></script>

</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="/displaySecurityInquiriesSummary" target="content">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|11">	
<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
		<td align="center" class="header"><bean:message key="prompt.agency"/> <bean:message key="prompt.securityInfo"/></td>
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
		<td class="detailHead"><bean:message key="prompt.agency"/> <bean:message key="prompt.securityInfo"/></td>
	</tr>
	<tr>
		<td align="center">  
			<table cellpadding="0" cellspacing="0" border="0" width="98%" class="borderTable">
				<tr>
					<td class="boldText2px" colspan="2"><bean:message key="prompt.agencyName" /></td>
					<td colspan="3"><bean:write name="securityInquiriesForm" property="agencyName" />&nbsp;(<bean:write name="securityInquiriesForm" property="agencyId" />)</td>
				</tr>
				<logic:iterate indexId="indSAUserCount" id="indSAUserIterator" name="securityInquiriesForm" property="saUsers">
				<tr>
					<td class="boldText2px" bgcolor="#F0F0F0" valign="top" nowrap colspan="2">
						<logic:equal name="indSAUserCount" value="0">
							<bean:message key="prompt.sa" />&nbsp;<bean:message key="prompt.user" />s
						</logic:equal>
					</td>
					<td bgcolor="#F0F0F0">
						<a href="/<msp:webapp/>displaySecurityInquiriesUserProfileDetails.do?logonId=<bean:write name="indSAUserIterator" property="logonId"/>" ><bean:write name="indSAUserIterator" property="formattedName"/></a>	
					</td>
					<td bgcolor="#F0F0F0">
						<bean:write name="indSAUserIterator" property="logonId" />
					</td>
				</tr>
				</logic:iterate> 
				
	<!--  BEGIN DEPARTMENT DISPLAY -->	
				<% int ctr = 0; %>				
 				<logic:iterate indexId="departmentCount" id="departmentIterator" name="securityInquiriesForm" property="departments">
				<bean:size id='asaUsersCount' name="departmentIterator" property="asaUsers"/>
				<bean:size id='liasonUsersCount' name="departmentIterator" property="liasonUsers"/>
				<% ctr++; %>
					<tr bgcolor="#CCCCCC">
						<jims:if name="asaUsersCount" value="0" op="greaterThan">
					    <jims:or name="liasonUsersCount" value="0" op="greaterThan"/>
					    <jims:then>
					        <td class="boldText2px" bgcolor="#CCCCCC" width="1%" nowrap valign="top">
								<a href="javascript:showHideExpand('users<%out.print(ctr); %>', 'row','/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="users<%out.print(ctr); %>"></a> 								
							</td>
						</jims:then>
						</jims:if>
						<jims:if name="asaUsersCount" value="0" op="equal">
					    <jims:and name="liasonUsersCount" value="0" op="equal"/>
					    <jims:then>
					        <td class="boldText2px" bgcolor="#CCCCCC" width="1%" nowrap>&nbsp;</td>
						</jims:then>
						</jims:if>						
						<td class="boldText2px" valign="top">
							<bean:message key="prompt.department" />
						</td>
						<td width="60%">
							<bean:write name="departmentIterator" property="departmentName"/>&nbsp;(<bean:write name="departmentIterator" property="departmentId"/>)
						</td>
						<td class="boldText2px">
							<bean:message key="prompt.total" />&nbsp;<bean:message key="prompt.user" />s&nbsp;<span class="bodyText"><bean:write name="departmentIterator" property="userCount"/></span>
						</td>
					</tr>
					<tr>
						<td colspan="5" bgcolor="#CCCCCC">
							<span class="hidden" id=users<%out.print(ctr); %>Span>
							<table cellpadding="0" cellspacing="0" border="0" bgcolor="#ffffff" >
	<!--  BEGIN ASA USER DISPLAY -->
 						<logic:notEmpty name="departmentIterator" property="asaUsers">
								<tr>
									<td width="1%"  valign="top" ><img src="/<msp:webapp/>images/spacer.gif" width="10"></td>
									<td class="boldText2px" bgcolor="#f0f0f0" valign="top" width="20%" nowrap><bean:message key="prompt.asa" />&nbsp;<bean:message key="prompt.user" />s</td>
									<td class="alternateRow">
										<table width="100%">
											<logic:iterate id="userIterator" name="departmentIterator" property="asaUsers">
												<tr>
													<td width="60%">
														<a href="/<msp:webapp/>displaySecurityInquiriesUserProfileDetails.do?logonId=<bean:write name="userIterator" property="logonId"/>" ><bean:write name="userIterator" property="formattedName"/></a>
													</td>
													<td>
														<bean:write name="userIterator" property="logonId" />
													</td>
												</tr>						
											</logic:iterate> 
										</table>
									</td>
								</tr>
							</logic:notEmpty> 
	<!--  END ASA USER DISPLAY -->
	<!--  BEGIN LIAISON USER DISPLAY -->
							<logic:notEmpty name="departmentIterator" property="liasonUsers">
								<tr>
									<td width="1%"  valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="10"></td>
									<td class="boldText2px" bgcolor="#f0f0f0" valign="top" width="20%" nowrap ><bean:message key="prompt.liaison" />&nbsp;<bean:message key="prompt.user" />s</td>
									<td class="alternateRow">
										<table width="100%">
											<logic:iterate id="userIterator" name="departmentIterator" property="liasonUsers">
												<tr>
													<td width="60%">
														<a href="/<msp:webapp/>displaySecurityInquiriesUserProfileDetails.do?logonId=<bean:write name="userIterator" property="logonId"/>" ><bean:write name="userIterator" property="formattedName"/></a>
													</td>
													<td>
														<bean:write name="userIterator" property="logonId" />
													</td>
												</tr>						
											</logic:iterate> 
										</table>
									</td>
								</tr>
							</logic:notEmpty> 
	<!--  END LIAISON USER DISPLAY -->	
							</table>
							</span>
						</td>
					</tr>
				</logic:iterate> 						
	<!--  END DEPARTMENT DISPLAY -->	
	<!--  BEGIN ROLES DISPLAY -->
				<logic:empty name="securityInquiriesForm" property="roles">
					<tr bgcolor="#FFCCCC">
						<td class="boldText2px" colspan="2"><bean:message key="prompt.role" />s</td>
						<td class="boldText2px" colspan="2"></td>
					</tr>
				</logic:empty>

				<logic:iterate indexId="count" id="indUserIterator" name="securityInquiriesForm" property="roles">
					<tr bgcolor="#FFCCCC">
						<td class="boldText2px" colspan="2">
							<logic:equal name="count" value="0">
								<bean:message key="prompt.role" />s
							</logic:equal>
						</td>
						<td class="boldText2px" colspan="2">
							<a href="/<msp:webapp/>displaySecurityInquiriesRoleDetails.do?roleId=<bean:write name="indUserIterator" property="roleId"/>" ><bean:write name="indUserIterator" property="roleName"/></a>
						</td>
					</tr>
				</logic:iterate>
	<!--  END ROLES DISPLAY -->			
			</table>
		</td>
	</tr>
</table>
<br>
<!-- BEGIN BUTTON TABLE -->
<table width="100%">
	<tr>
		<td align="center">
			<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="closeWindow();">
				<bean:message key="button.close"></bean:message>
			</html:button>
		</td>
	</tr>
</table>			
<!-- END BUTTON TABLE -->
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>