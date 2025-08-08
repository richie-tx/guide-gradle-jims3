<!DOCTYPE HTML>
<!-- 08/29/2005	 Hien Rodriguez - Create JSP -->
<!-- 05/04/2006  C. Shimek      - defect#30737 fixed cancel button -->
<!-- 05/24/2006  C Shimek        - fixed merge display issue -->
<!-- 01/10/2007  C Shimek       - #38306 add multiple submit functionality  -->
<!-- 02/05/2009  C Shimek       - #56860 add Back to Top  -->

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
<title><bean:message key="title.heading" /> - departmentManageContact.jsp</title>
<!-- AUTO TAB JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<html:javascript formName="contactUpdateForm"/>
</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/departmentDisplayContactSummary" target="content" focus="contactName.lastName">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|17">	
<!-- BEGIN HEADING TABLE -->
<table width="98%" align="center">
  	<tr>
    	<td align="center" class="header"><bean:message key="title.contactUpdate"/></td> 
  	</tr>  	
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
<table width="98%" align="center">
  	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
  	</tr>   	  
</table>
<!-- END ERROR TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%" align="center">
	<tr>
    	<td><ul>
        	<li>Make any change then select the Next button to continue.</li>
		</ul></td>
	</tr>
	<tr>
   		<td class="required"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.requiredFieldsInstruction" /></td>
    </tr>
</table>
<!-- HEADING TABLE -->
<!-- INSTRUCTION TABLE -->
<br>
<table align="center" border="0">
    
    <tr >
    	<td class="subhead"><bean:message key="prompt.department"/>:</td>
    	<td ><bean:write name="departmentForm" property="departmentName" /></td>
    </tr>
</table>

<!-- BEGIN CREATE CONTACT TABLE -->
<table class="borderTableBlue" cellpadding="2" cellspacing="1" width="98%">
	<tr>
		<td colspan="4" class="detailHead">
			<table width="100%" cellpadding="2" cellspacing="1">
				<tr>
					<td class="detailHead"><bean:message key="prompt.contactInfo"/></td>					
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td class="formDeLabel" valign="top" nowrap>Name</td>
		<td colspan="3" class="formDe" >
			<table width="100%" border="0" cellspacing="1">
				<tr>
					<td class="formDeLabel" colspan="2"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.last"/></td>
				</tr>
				<tr>
					<td class="formDe"  colspan="2"><html:text property="contactName.lastName" size="30" maxlength="75"/></td>
				</tr>
				<tr>
					<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.first"/></td>
					<td class="formDeLabel"><bean:message key="prompt.middle"/></td>
				</tr>
				<tr>								
					<td class="formDe" ><html:text property="contactName.firstName" size="25" maxlength="50"/></td>
					<td class="formDe" ><html:text property="contactName.middleName" size="25" maxlength="50"/></td>
				</tr>
			</table>
		</td>
	</tr>	
	<tr>	
		<td class="formDeLabel"><bean:message key="prompt.userId"/></td>
		<td class="formDe"  colspan="3"><html:text property="logonId" maxlength="5" size="5"/></td>				
	</tr>
	<tr>
		<td class="formDeLabel" nowrap><bean:message key="prompt.jobTitle"/></td>
		<td class="formDe"  colspan="3"><html:text property="contactJobTitle" maxlength="50" size="50"/></td>
	</tr>
	<tr>
		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.2.diamond" /><bean:message key="prompt.primaryContact" /></td>
		<td class="formDe" > Yes
			<html:radio name="departmentForm" property="primaryContact" value="Y" />
				&nbsp;&nbsp; No
		 	<html:radio name="departmentForm" property="primaryContact" value="N" />
		</td>
		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.liaisonTraining" /></td>
		<td class="formDe" > Yes
		   	<html:radio name="departmentForm" property="liaisonTrainingInd" value="Y" />
			 &nbsp;&nbsp; No
			<html:radio name="departmentForm" property="liaisonTrainingInd" value="N" />
		</td>
	</tr>
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.phone"/></td>
		<td class="formDe" colspan="3">
			<html:text property="contactPhoneNumber.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> -
			<html:text property="contactPhoneNumber.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"/> -
			<html:text property="contactPhoneNumber.last4Digit" size="4" maxlength="4" onkeyup="return autoTab(this, 4);"/>&nbsp;<b><bean:message key="prompt.ext"/></b>
			<html:text property="contactPhoneNumber.ext" size="5" maxlength="5" onkeyup="return autoTab(this, 5);"/>
		</td>
	</tr>	
	<tr>
		<td class="formDeLabel"><bean:message key="prompt.email"/></td>
		<td class="formDe"  colspan="3"><html:text property="contactEmail" maxlength="100" size="50" /></td>			
	</tr>	
</table>
<!-- END CREATE CONTACT TABLE -->
<br>

<!-- BEGIN BUTTON TABLE -->
<table width="98%" border="0">
	<tr>
        <td align="center">        	
	        <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				<bean:message key="button.back"></bean:message></html:button>
			<html:submit property="submitAction" onclick="return validateContactUpdateForm(this.form) && disableSubmit(this, this.form);"><bean:message key="button.next" /></html:submit>
			<html:reset property="submitAction"><bean:message key="button.reset"/></html:reset>					
			<input type="submit" value="<bean:message key='button.cancel'/>" name="submitAction" onclick="javascript:changeFormActionURL('departmentForm', '/<msp:webapp/>departmentDisplayDetails.do?action=contactModify', false);">		  	
		</td>
    </tr>
</table>
<!-- END BUTTON TABLE -->
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>