<!DOCTYPE HTML>
<!-- Used for Arrest, Directive To Apprehend and Probable Cause Warrants -->
<!--MODIFICATIONS -->
<%-- CShimek		05/17/2004	Create JSP --%>
<%-- CShimek    	02/17/2005  Removed Transaction number as search item JIMS200018646 --%>
<%-- HRodriguez		06/06/2005	Change to new look --%>
<%-- CShimek    	04/05/2006  ER#26357 change Reset button to Refresh button --%>
<%-- LDeen			10/20/2006  Move error table to below heading table --%>
<%-- CShimek    	12/21/2006  Revised helpfile reference value--%>
<%-- CShimek    	01/23/2007  #38442 removed onsubmit in form tag --%>
<%-- CShimek 		01/30/2007  #39097 added multiple submit button logic --%>
<%-- DWilliamson 	03/28/2007 	Removed onKeyDown logic so user can tab to refresh button and hit enter per defect #39641 --%>
<%-- LDeen			06/04/2007  #42564 changed path to app.js --%>
<%-- RYoung         08/06/2015  #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />

<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<html:base />
<title><bean:message key="title.heading"/> - warrantJOTSearch.jsp</title>

<!-- JAVASCRIPT FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/warrantJOTSearch.js"></script>
</head> 
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayJOTSearchResults" target="content" focus="daLogNum"> 
  	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="arr">
	    <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|58"> 
   	</logic:equal>
   	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="dta">
		<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|55"> 
   	</logic:equal>
   	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="pc">
		<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|69"> 
   	</logic:equal>
    <input type="hidden" name="refreshButton" value="">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
	<tr>
      	<td class="header" align="center"> Initiate 
         	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="arr">
            	<bean:message key="title.juvWarrantArrest"/>
          	</logic:equal>
          	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="dta">
            	<bean:message key="title.juvWarrantDTA"/>
          	</logic:equal>
          	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="pc">
            	<bean:message key="title.juvWarrantPC"/>
          	</logic:equal>
         	 Search
      	</td>	
    </tr>
</table>
<!-- END HEADING TABLE -->
<br>
<!-- BEGIN ERROR TABLE -->
<table width="98%"  border="0" align="center">
	<logic:notEmpty name="unrecoverableError" scope="request">
		<logic:equal name="unrecoverableError" value="true" scope="request">
			<tr>
				<td colspan="2" class="errorAlert" align="center">Cannot initiate warrant with search criteria (DA Log Num=<bean:write name="juvenileWarrantForm" property="daLogNum"/>).  Please correct in JOT and try again.</td>
			</tr>
		</logic:equal>
	</logic:notEmpty>			
    <tr>
    	<td width="50"></td>
		<td class="errorAlert" align="center"><html:errors /></td>
	</tr>
</table>
<!-- END ERROR TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<table width="98%">
  	<tr> 
    	<td><ul>
        	<li>Enter DA Log number.</li>            
        	<li>Select Submit button to retrieve JOT information.</li>
      	</ul></td>
  	</tr>
  	<tr> 
    	<td class="required"><bean:message key="prompt.mjw.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/><%-- bean:message key="prompt.requiredFields"/ --%></td>
  	</tr>
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN CONTENT TABLE -->	
<table width="98%" align="center" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
<%--  <tr>
    	<td class=formDeLabel width=1% nowrap><bean:message key="prompt.diamond"/>JOT <bean:message key="prompt.transactionNumber"/></td>
    	<td class=formDe><html:text property="transactionNum" size="6" maxlength="6" tabindex="1" /></td>
  	</tr>	  --%>
  	<tr>
    	<td class=formDeLabel width=1% nowrap><bean:message key="prompt.mjw.diamond"/><bean:message key="prompt.daLogNumber"/></td>
    	<td class=formDe><html:text property="daLogNum" size="6" maxlength="6" tabindex="1" /></td>
  	</tr>	  
</table>
<!-- END CONTENT TABLE -->	
  <br>
<!-- BEGIN BUTTON TABLE -->
<table width="98%">
	<tr>
		<td align="center">
      		<html:submit property="submitAction" onclick="return SearchValidator(this.form)&& disableSubmit(this, this.form);">
      			<bean:message key="button.submit"/>
      		</html:submit>&nbsp;
      		<html:submit property="submitAction" onclick="javascript: this.form.refreshButton.value='Y';">
      			<bean:message key="button.refresh"/>
      		</html:submit>
    	</td>
  	</tr>
</table>
<!-- END BUTTON TABLE --> 

</html:form>
<!-- END FORM -->

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>