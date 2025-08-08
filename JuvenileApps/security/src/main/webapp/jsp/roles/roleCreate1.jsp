<!DOCTYPE HTML>
<!-- Used to create Roles - page 1 -->
<!--MODIFICATIONS -->
<%-- 05/05/2005	 CShimek 	Create JSP --%> 
<%-- 02/13/2006  CShimek	Corrected prompt values for required field indicator --%> 
<%-- 05/25/2006  CShimek	Defect#31899 Add missing Cancel button --%>
<%-- 09/06/2007  LDeen		Defect #45039 role name length changed to 30  --%> 
<!-- 02/05/2009  CShimek    #56860 add Back to Top  -->
<!-- 10/19/2015  R Young    #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
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
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<html:javascript formName="roleForm"/>
<title><bean:message key="title.heading"/> - roleCreate1.jsp</title>
<!-- JAVASCRIPT FILES -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/roles/roleCreate1.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>

</head>
<!-- END HEADER TAG -->

<!-- BEGIN BODY TAG -->
<body>

<!-- BEGIN HEADING TABLE FOR COPY -->
<logic:equal name="roleForm" property="action" value="copy" >
	<table width="100%">
	  <tr>
    	<td align="center" class="header">
           <bean:message key="title.copyRole"/>
        </td>
	  </tr>
	</table>
</logic:equal>	
<!-- END HEADING TABLE FOR COPY -->
<!-- BEGIN HEADING TABLE FOR CREATE -->
<logic:equal name="roleForm" property="action" value="create" >
	<table width="100%">
	  <tr>
    	<td align="center" class="header">
           <bean:message key="title.createRole"/>
    	</td>
	  </tr>
	</table>
</logic:equal>		
<!-- END HEADING TABLE FOR CREATE -->
<!-- END HEADING TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<table align="center" width="98%" border="0">
   <tr>
     <td>
	  <ul>
        <li>Enter Role Name and Description then select Next.</li>
      </ul>
	</td>
  </tr>
<!-- END INSTRUCTION TABLE -->
  <tr>
  	<td class="required"><bean:message key="prompt.msa.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
  </tr>
</table>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
<html:form action="/displayRoleCreate" target="content" focus="roleName">
	<logic:equal name="roleForm" property="action" value="copy" >    	
		 <input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|38">
    </logic:equal>   
	<logic:equal name="roleForm" property="action" value="create" >
 		<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|59">
	</logic:equal>  
	<tr>
		<td width="98%" align="center" valign="top">
			
			<!-- BEGIN ERROR TABLE -->
				<table width="100%">
					<tr>		  
						<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
					</tr>   	  
				</table>
			<!-- END ERROR TABLE -->	
			
			<table class="borderTableBlue"  cellpadding="4" cellspacing="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead">
					   <logic:equal name="roleForm" property="action" value="create" >
					   		<bean:message key="title.roleCreate"/>
					   </logic:equal>
					   <logic:equal name="roleForm" property="action" value="copy" >
					   		<bean:message key="title.roleCopy"/>
					   </logic:equal>
					</td>
					<td align="right"><img src=/<msp:webapp/>images/step_1.gif hspace=0 vspace=0></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table width="100%">
							<tr>
								<td nowrap class="formDeLabel"><bean:message key="prompt.msa.diamond" /><bean:message key="prompt.roleName"/></td>
								<td class="formDe"><html:text property="roleName" size="30" maxlength="30"/></td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.msa.diamond" /><bean:message key="prompt.roleDescription"/></td>
								<td class="formDe"><html:text property="roleDescription" size="50" maxlength="50"/></td>
							</tr>
							<tr>
								<td colspan="2"><input type="hidden" name="originalRoleName" value='<bean:write name="roleForm" property="roleName" />' ></td>
							</tr>
						</table>
					</td> 
				</tr>
			</table>
		<br>
        <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
		     <bean:message key="button.back" />
		</html:button>&nbsp;	
		<logic:equal name="roleForm" property="action" value="create" >
    	    <html:submit onclick="return validateCreateFields(this.form)&& disableSubmit(this, this.form)" property="submitAction">
    	    	<bean:message key="button.next"/>
    	    </html:submit>&nbsp;
		</logic:equal>
	    <logic:equal name="roleForm" property="action" value="copy" >
    	    <html:submit onclick="return validateCopyFields(this.form)&& disableSubmit(this, this.form)" property="submitAction">
    	    	<bean:message key="button.next"/>
    	    </html:submit>
		</logic:equal>
		<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)">
			<bean:message key="button.cancel"/>
		</html:submit>
		</td>
	</tr>
</html:form>
</table>
<br>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>