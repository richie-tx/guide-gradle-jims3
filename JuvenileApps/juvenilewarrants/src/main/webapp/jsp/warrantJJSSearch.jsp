<!DOCTYPE HTML>
<!--MODIFICATIONS -->
<!-- Used for Order of Immediated Custody(OIC) and Violation of Probation Warrant(VOP) -->
<%-- 05/18/2004	 CShimek  		Create JSP --%>
<%-- 10/07/2004	 HRodriguez		Modify JSP --%>
<%-- 03/28/2005  CShimek        make referral number required per defect JIM200019971 and emai for LDeen --%>
<%-- 06/06/2005	 HRodriguez		Change to new look --%>
<%-- 02/01/2006  CShimek        Added hidden field helpFile for each warrant type --%>
<%-- 03/07/2006	 LDeen			Change required prompt --%>
<%-- 04/05/2006  CShimek        ER#26357 change Reset button to Refresh button --%>
<%-- 10/20/2006  LDeen          Move error table to below heading table --%>
<%-- 12/21/2006  CShimek		Revised helpfile reference value--%>
<%-- 01/30/2007  CShimek		#39097 added multiple submit button logic --%>
<%-- 06/04/2007	 LDeen		    #42564 changed path to app.js --%>
<%-- 08/6/2015   RYoung       #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

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
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>

<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<!-- STYLE SHEET LINK -->	
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/warrants.css" />
<html:base />

<!-- JAVASCRIPT FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/warrantJJSSearch.js"></script>

<title>JIMS2 - warrantJJSSearch.jsp</title>
</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN FORM -->	
<html:form action="/displayJJSSearchResults" target="content"> 
<%-- html:hidden property="action" /> --%>
  	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="oic">
	    <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|61">  	
   	</logic:equal>
   	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="vop">
	    <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|68">   	
   	</logic:equal>
    <input type="hidden" name="refreshButton" value="">   	
<!-- BEGIN HEADING TABLE -->
<table width=98% align="center">	
	<tr>
		<td align="center" class="header">		
      		<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="oic">	      		
        		Initiate <bean:message key="title.juvWarrantOIC"/> <bean:message key="title.search"/>
         	</logic:equal>
        	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="vop">      		
         		Initiate <bean:message key="title.juvWarrantVOP"/> <bean:message key="title.search"/>
         	</logic:equal>
         </td>
	</tr> 
</table>
<!-- END HEADING TABLE -->
<br>
<!-- BEGIN ERROR TABLE -->
<table width="98%"  border="0" align="center">
   <tr>
    	<td width="50"></td>
		<td align="center" class="errorAlert"><html:errors /></td>
	</tr>
</table>
<!-- END ERROR TABLE -->

<!-- BEGIN INSTRUCTION TABLE -->
<table width=98% align="center">	
	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="oic">	      		
      	<tr>
      		<td><ul>	       
				<li>Enter the petition number and referral number then select Submit.</li>
    		</ul></td>
     	</tr>
    </logic:equal>
    <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="vop">      		
    	<tr>
        	<td><ul>
          	   	<li>Enter the juvenile number and referral number then select Submit.</li>
 			</ul></td>
 		</tr>
    </logic:equal> 
</table>
<!-- END INSTRUCTION TABLE -->
<!-- BEGIN REQUIRED TABLE -->
<table width="98%">	
	<tr><td class="required"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td></tr>	
</table>
<!-- END REQUIRED TABLE -->

<!-- BEGIN DETAIL TABLE -->
<table width="98%" align="center" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="oic">	  
	   	<tr>
          	<td class=formDeLabel><bean:message key="prompt.1.diamond"/><bean:message key="prompt.petitionNumber"/></td>
          	<td class=formDe><html:text property="petitionNum" size="10" maxlength="10" />      
	   	</tr>
	   	<tr>
          	<td class=formDeLabel width=1% nowrap><bean:message key="prompt.1.diamond"/><bean:message key="prompt.referralNumber"/></td>
          	<td class=formDe><html:text property="referralNum" size="4" maxlength="4" /></td>    
	   	</tr>
	</logic:equal>	
   	<logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="vop">
  	   	<tr>
         	<td class=formDeLabel width=1% nowrap><bean:message key="prompt.1.diamond"/><bean:message key="prompt.juvenileNumber"/></td>
         	<td class=formDe><html:text property="juvenileNum" size="7" maxlength="7" />      
	  	</tr>	
	  	<tr> 
      		<td class=formDeLabel><bean:message key="prompt.1.diamond"/><bean:message key="prompt.referralNumber"/></td>
      		<td class=formDe><html:text property="referralNum" size="4" maxlength="4" /></td>    
	 	</tr>					
   	</logic:equal>
</table>
<!-- END DETAIL TABLE -->				
<br>		
<!-- BEGIN BUTTON TABLE -->
<table width="98%">
	<tr>
		<td align="center">
      		<html:submit property="submitAction" onclick="return SearchValidator(this.form) && disableSubmit(this, this.form);">
      			<bean:message key="button.submit"></bean:message>
      		</html:submit>&nbsp;
      		<html:submit property="submitAction" onclick="javascript: this.form.refreshButton.value='Y';">
      			<bean:message key="button.refresh"/>
      		</html:submit>
    	</td>
  	</tr>		
</table>
<!-- END BUTTON TABLE -->

<!-- script to set focus to first input field  -->
<script language="javascript">
   for (var i=0; i <document.forms[0].length; i++){
     if (document.forms[0].elements[i].type == "text"){
        document.forms[0].elements[i].focus();
        break;
     }
   }	  
</script>   
</html:form>
<!-- END FORM -->

<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
<!--END BODY TAG-->
</html:html>