<!DOCTYPE HTML>
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<!-- 05/19/2006  C. Shimek     - Added focus to form tag -->
<!-- 08/28/2006  LDeen 		   - Changed AgencyType field for ER#31371 & deleted Notes table -->
<!-- 01/10/2007  C Shimek      - #38306 add multiple submit functionality  -->
<!-- 02/04/2009  C Shimek      - #56860 add Back to Top  -->

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
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading" /> - agencyUpdate.jsp</title>

<!-- AUTO TAB JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/agencies/agencyUpdate.js" ></script>

</head>
<!--END HEADER TAG-->

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
  <tr>
		<logic:equal name="agencyForm" property="action" value="update">
			<td align="center" class="header"><bean:message key="title.updateAgency"/></td>
		</logic:equal> 
		<logic:equal name="agencyForm" property="action" value="create">
			<td align="center" class="header"><bean:message key="title.createAgency"/></td>
		</logic:equal> 
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
				<logic:equal name="agencyForm" property="action" value="update">
					<li>Make needed changes then select Next button.</li>
				</logic:equal> 
				<logic:equal name="agencyForm" property="action" value="create">
					<li>Enter required fields and select Next button to view summary.</li>
				</logic:equal> 
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required"><img src="/<msp:webapp/>images/<bean:message key="prompt.requiredFieldsImage"/>" title="required" alt="required image" hspace="0" vspace="0">
			<bean:message key="prompt.requiredFieldsInstruction"/></td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->

<html:form action="/displayAgencySummary" target="content" focus="agencyNamePrompt"> 
	<logic:equal name="agencyForm" property="action" value="update">
		<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|215">
	</logic:equal> 
	<logic:equal name="agencyForm" property="action" value="create">
		<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|39">
	</logic:equal> 					
<table width="100%" border="0" cellpadding="0" cellspacing="0"> 
	<tr>
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.agencyInfo"/></td>
				</tr>
				<tr>
					<td align="center">
						<table border="0" cellspacing="1" cellpadding="2" width="100%">
							<tr>
								<td class="formDeLabel"><img src="/<msp:webapp/>images/<bean:message key="prompt.requiredFieldsImage"/>" title="required" alt="required image" hspace="0" vspace="0"><bean:message key="prompt.agencyName"/></td>
								<td class="formDe"><html:text property="agencyNamePrompt" size="60" maxlength="60"/></td>
							</tr>
							<logic:equal name="agencyForm" property="action" value="create">
							<tr>
								<td class="formDeLabel"><img src="/<msp:webapp/>images/<bean:message key="prompt.requiredFieldsImage"/>" title="required" alt="required image" hspace="0" vspace="0"><bean:message key="prompt.agencyType"/></td>
								<td class="formDe">						
									<html:select property="agencyTypeId">
										<html:option value=""><bean:message key="select.generic" /></html:option>
											<html:optionsCollection property="agencyTypes" value="code" label="description" /> 
									</html:select>
								</td>
							</tr>
							</logic:equal>
							<logic:equal name="agencyForm" property="action" value="update">
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.agencyType"/></td>
								<td class="formDe">
									<bean:write name="agencyForm" property="agencyType"/>						
									<!--8/28/06 LDeen changed to display only field for ER#31371
									<html:select property="agencyTypeId">
										<html:option value=""><bean:message key="select.generic" /></html:option>
											<html:optionsCollection property="agencyTypes" value="code" label="description" /> 
									</html:select>-->
								</td>
							</tr>
							</logic:equal>
							<logic:equal name="agencyForm" property="action" value="create">
								<tr>
									<td class="formDeLabel"><img src="/<msp:webapp/>images/<bean:message key="prompt.requiredFieldsImage"/>" title="required" alt="required image" hspace="0" vspace="0"><bean:message key="prompt.agencyCode"/></td>
									<td class="formDe"><html:text property="agencyIdPrompt" size="3" maxlength="3"/></td>
								</tr>
							</logic:equal>
							<logic:equal name="agencyForm" property="action" value="update">
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.agencyCode"/></td>
									<td class="formDe"><bean:write name="agencyForm" property="agencyId"/></td>
								</tr>
							</logic:equal>
							<tr>
								<td class="formDeLabel" nowrap width="1%"><img src="/<msp:webapp/>images/<bean:message key="prompt.requiredFieldsImage"/>" title="required" alt="required image" hspace="0" vspace="0"><bean:message key="prompt.jmcRepresentative"/></td>
								<td class="formDe">
									<html:radio name="agencyForm" property="jmcRep" value="Y"/><bean:message key="prompt.yes"/>
									<html:radio name="agencyForm" property="jmcRep" value="N"/><bean:message key="prompt.no"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		<br>
    <!--BEGIN BUTTON TABLE--> 
			<table> 
				<tr> 
					<td>
						<input type="button" name="back" value="Back" onClick="history.go(-1)">
						<html:submit property="submitAction" onclick="return validateAgencyUpdateForm(this.form) && disableSubmit(this, this.form);">
							<bean:message key="button.next"/>
						</html:submit>
				 		<input type="reset" value="Reset"> 
						<html:submit property="submitAction">
							<bean:message key="button.cancel"/>
						</html:submit>
					</td> 
				</tr> 
			</table>
		 <!--END BUTTON TABLE-->
		</td>
	</tr> 
</table>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
