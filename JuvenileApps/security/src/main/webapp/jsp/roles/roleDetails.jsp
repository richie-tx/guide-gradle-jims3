<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/02/2006	 C Shimek   - Create JSP -->
<!-- 02/05/2009  C Shimek   - #56860 add Back to Top  -->
<!-- 07/17/2009  C Shimek   - #61004 added timeout.js  -->
<!-- 12/14/2009  C Shimek   - add js (code from app.js) to make Back to Top function -->

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
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - roleDetails.jsp</title>

<!-- INCLUDED JAVASCRIPT FILES -->
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/security_util.js"></script>
<script type="text/javascript">
function goNav( location )
{
	if( location == "back" )
	{
		history.go( -1 );
	}
	else
	{
		window.location.href = location;
	}
}
function renderBackToTop(){
	document.write("[<a href=\"javascript:void(0);\" onclick=\"goNav('#top')\">Back to Top</a>]");
}
</script>

</head>

<body>
<html:form action="/displaySecurityInquiriesSummary" target="content">
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE -->
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
					<td width="1%" valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="10"></td>
					<td class="boldText2px" bgcolor="#CCFFCC" valign="top" colspan="2" nowrap="nowrap">
						<a href="javascript:showHide('features', 'row','/<msp:webapp/>')" ><img border='0' src="/<msp:webapp/>images/expand.gif" name="features"></a> <bean:message key="prompt.feature" />s
					</td>
					<td class="boldText" bgcolor="#CCFFCC" >
					<span class="hidden" id="featuresSpan">
						<table width="100%">
	 					<logic:iterate id="featureIterator" name="securityInquiriesForm" property="features">								
							<tr bgcolor="#CCFFCC">
								<td class="boldText"><bean:write name="featureIterator" property="featureName"/></td>
							</tr>
							</logic:iterate>
						</table>
					</span>
					</td>
				</tr>
	<!--  BEGIN USER GROUP DISPLAY -->					
 				<logic:iterate id="userGroupIterator" name="securityInquiriesForm" property="userGroups">							
					<tr>
						<td width="1%" valign="top"></td>
						<td class="boldText2px" bgcolor="#CCFFFF" valign="top" colspan="2" nowrap="nowrap"> <bean:message key="prompt.userGroup" /></td>
						<td class="boldText" bgcolor="#CCFFFF">
							<bean:write name="userGroupIterator" property="name"/>
						</td>
					</tr>
					<logic:notEmpty name="userGroupIterator" property="users">
					<tr>
						<td width="1%" valign="top"></td>
						<td width="1%" valign="top"><img src="/<msp:webapp/>images/spacer.gif" width="10"></td>
						<td class="boldText2px" bgcolor="#f0f0f0" valign="top"><bean:message key="prompt.user" />s</td>
						<td class="alternateRow">
							<table width="100%">
			 					<logic:iterate id="userIterator" name="userGroupIterator" property="users">
									<tr>
										<td width="60%">
											<bean:write name="userIterator" property="formattedName"/>
										</td>
										<td width="10%">
											<bean:write name="userIterator" property="logonId" />
										</td>
										<td class="boldText" width="10%" nowrap="nowrap">
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
					<td colspan="3" class="boldText2px" bgcolor="#f0f0f0" valign="top" nowrap="nowrap"><bean:message key="prompt.individual" /> <bean:message key="prompt.user" />s</td>
					<td class="alternateRow">
						<table width="100%">
							<logic:iterate id="indUserIterator" name="securityInquiriesForm" property="individualUsers">
								<tr>
									<td width="60%" nowrap="nowrap">
										<a href="/<msp:webapp/>displaySecurityInquiriesUserProfileDetails.do?logonId=<bean:write name="indUserIterator" property="logonId"/>" ><bean:write name="indUserIterator" property="formattedName"/></a>
									</td>
									<td width="10%">
										<bean:write name="indUserIterator" property="logonId" />
									</td>
									<td class="boldText" width="10%" nowrap="nowrap">
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
		<%-- %>/td>
	</tr>
</table--%>
<br>
<!-- BEGIN BUTTON TABLE -->
<table width="100%">
	<tr>
		<td align="center">
			<input type="button" value='Close Window' onclick="window.close();">
		</td>
	</tr>
</table>			
<!-- END BUTTON TABLE -->
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>