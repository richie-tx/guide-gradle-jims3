<!DOCTYPE HTML>
<!-- Used to search create Security Administrator Roles - page 1 or 2. -->
<!--MODIFICATIONS -->
<!-- CShimek 04/01/2005	 Create JSP -->
<%-- CShimek 02/13/2006  Corrected prompt values for required field indicator --%> 
<%-- CShimek 04/18/2006  Defect#30559 add missing js nogo() --%>
<!-- CShimek 01/12/2007  #38306 add multiple submit functionality  -->
<!-- CShimek 02/05/2009  #56860 add Back to Top  -->

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
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading"/> - roleSACreate1.jsp</title>

<!-- STRUTS VALIDATIONS-->
<html:javascript formName="roleForm" />
<script type='text/javascript' src="/<msp:webapp/>js/saRoles/roleSACreate1.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
  <tr>
    <td align="center" class="header"><bean:message key="title.saRoleCreate"/> - Select Agency</td>
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
        <li>Select agency then select Next to continue.</li>
      </ul>
	</td>
  </tr>
  <tr>
  	<td class=required><bean:message key="prompt.msa.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<html:form action="/displaySARoleCreate2" target="content" >
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|66">
	<tr>
		<td width="98%" align="center" valign="top">
			<!--assign SA start-->
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.msa.diamond" /><bean:message key="prompt.select" /> <bean:message key="prompt.agencyforSARole"/></td>
					<td align="right"><img src=/<msp:webapp/>images/step_1.gif hspace=0 vspace=0></td>
				</tr>
				<tr bgcolor="#cccccc">
					<td class="boldText" colspan="2"><bean:message key="prompt.agencyName" /></td>
				</tr>
				<tr height="100%">
					<td colspan="2">
						<div class="scrollingDiv200">
						<!--radio button selection start-->
							<table border="0" width="100%" cellspacing="1" cellpadding="2">
					        <logic:iterate id="agenciesIndex" name="roleSAForm" property="agencies" indexId="index"> 
								<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
				  					<td class="boldText">
                                       <input type="radio" name="agencyId" value=<bean:write name='agenciesIndex' property='agencyId'/>> 
                                          <bean:write name='agenciesIndex' property='agencyName'/>
									</td>
								</tr>
							    </logic:iterate>		
							</table>
						 <!-- radio button selection end -->	
						</div>
					</td>  
				</tr>
			</table>
			<!--assign SA end-->
			<br>
		
	        <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
		          <bean:message key="button.back"></bean:message></html:button>&nbsp;
		    <logic:notEmpty name="roleSAForm" property="agencies">      		
    	    	<html:submit onclick="return validateAgencySelect(this.form) && disableSubmit(this, this.form);" property="submitAction"><bean:message key="button.next"/></html:submit>
    	    </logic:notEmpty>	
		</td>
	</tr>
</html:form>	
</table>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>