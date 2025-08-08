<!doctype html>
<!--MODIFICATIONS -->
<%-- 01/21/2005 Hien Rodriguez	Create JSP --%>
<%-- 06/06/2005	Hien Rodriguez	Change to new look --%>
<%-- 09/21/2005 CShimek         Defect 24832, added validation --%>
<%-- 10/06/2005 CShimek         ER#23756 revise titles --%>
<%-- 02/02/2006 CShimek         Added hidden field helpFile --%>
<%-- 03/17/2006 CShimek			Activity 29779 - comment out Law Enforcement Department --%>
<%-- 04/05/2006 CShimek      	ER#26357 change Reset button to Refresh button --%>
<%-- 10/18/2006 DWilliamson		ER#36128 Remove OR and add drop down lists in searches --%>
<%-- 12/21/2006 LDeen			Revised help file code --%>
<%-- 01/30/2007 CShimek		 	#39097 added multiple submit button logic --%>
<%-- 06/13/2007 LDeen			#42564 changed path to app.js --%> 
<%-- 06/29/2007 CShimek		 	#42728 moved embedded js into warrantViewSearch.js (revised moved script to clear/reset all inputs on Search By selection)  --%>
<%-- 08/07/2007 RCapestani		#44089 added scripts customStrutsBasedValidation --%>
<%-- 09/10/2007 CShimek         #40266 added onload() to evaluate searchType and display corresponding search fields --%>
<%-- 12/31/2007 RCapestani		#47458 fixed view warrant by Officer ID --%>
<%-- 04/16/2008 DWilliamson		#51016 increase badge number size to 11	 --%>
<%-- 09/21/2010 RCapestani		#66852 added Warrant Status and Issue Date Range to Revise View Search-Warrant Type --%>
<%-- 08/06/2015 RYoung      #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
 
<!-- SCRIPTING VARIABLES -->
<jsp:useBean id="codeHelper" scope="application" class="ui.juvenilewarrant.helper.JuvenileWarrantListHelper" />

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
<title><bean:message key="title.heading"/> - warrantViewSearch.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<!-- JAVASCRIPT FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/warrants.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>


</head>
 
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="evalSearch(document.forms[0])">
<html:form action="/displayWarrantViewSearchResults" focus="warrantNum" target="content">
<input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|143">
<!-- BEGIN HEADING TABLE -->
<table  width="98%" >
    <tr>    
   		<td align="center" class="header">View&nbsp;<bean:message key="title.juvWarrant"/>&nbsp;<bean:message key="prompt.search"/></td> 
   	</tr>
</table>
<!-- END HEADING TABLE -->
<br>
<!-- INSTRUCTION TABLE -->
<table width="98%">
	<tr>  
    	<td><ul>
        	<li>Enter any one search criteria, full or partial Juvenile Name or combination of Juvenile Name and other fields.</li>
			<li>Select Submit button to perform search.</li>
		</ul></td>
	</tr> 
	<tr>
	  <td class="required"><bean:message key="prompt.plusSign"/>&nbsp;indicates Last Name is required to use this search field.</td>
	</tr>	            
</table>
<!-- BEGIN ERROR TABLE --> 
<table width="98%">
    <tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE -->
<!-- BEGIN INQUIRY TABLE -->
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1"  class=borderTableBlue>
   <tr>
     <td class="formDeLabel" width=1% nowrap><bean:message key="prompt.searchBy"/></td>
     <td class="formDe">
     	<select name=searchType id="searchTypeId"> 
	      <option value=warrantNumber><bean:message key="prompt.warrantNumber"/></option>
	      <option value=juvenileName><bean:message key="prompt.juvenileName"/></option>
	      <option value=warrantType><bean:message key="prompt.warrantType"/></option>
<!--      <option value=agencyId><bean:message key="prompt.agencyId"/></option>  --> 
	      <option value=officerId><bean:message key="prompt.officerIdNumber"/></option>
	      <option value=adultName><bean:message key="prompt.responsibleAdult"/> <bean:message key="prompt.name"/></option>
	      <option value=warrantOriginatorId><bean:message key="prompt.warrantOriginatorID"/></option>
	    </select> 
 	 </td> 
   </tr> 
</table>
<br>

<!-- DETAIL TABLE -->

<!-- SEARCH BY WARRANT NUMBER -->
<span id="warrantNumber" class=visible>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
  <tr>
     <td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.1.diamond"/><bean:message key="prompt.warrantNumber"/></td>
     <td class="formDe">
        <input type="text" name="warrantNum" id="warrantNum" value="" size="10" maxlength="10">
     </td>
  </tr>
</table>
</span>

<!-- SEARCH BY JUVENILE NAME -->
<span id="juvName" class=hidden>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
  <tr>
    <td class=formDeLabel width=1% nowrap><bean:message key="prompt.juvenileLastName"/></td>
    <td class=formDe><html:text property="juvenileName.lastName" styleId="lastName" size="30" maxlength="75"/></td>
  </tr>
  <tr>
    <td class=formDeLabel width=1% nowrap><bean:message key="prompt.plusSign"/><bean:message key="prompt.juvenileFirstName"/></td>
    <td class=formDe><html:text property="juvenileName.firstName" styleId="firstName" size="25" maxlength="50"/></td>
  </tr>
</table>
</span>

<!-- SEARCH BY WARRANT TYPE --> 
<span id="warrantTypes" class="hidden">
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
  <tr>
	<td class=formDeLabel width=1% nowrap><bean:message key="prompt.1.diamond"/><bean:message key="prompt.warrantType"/></td>
    <td class=formDe>
	    <html:select size="1" property="warrantTypeId" styleId="warrantTypeCd">
	      	<html:option key="select.generic" value="" />
			<html:optionsCollection name="codeHelper" property="warrantTypes" value="code" label="description" />          		
		</html:select>
	</td>
  </tr>
</table>
</span>
<br>
<span id="warrantStatus" class="hidden" >
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
  <tr>
  	<td class=formDeLabel nowrap>
	  	<span id="warrantStatusReqd" class="hidden">
			<bean:message key="prompt.1.diamond"/><bean:message key="prompt.warrantStatus"/>
		</span>
		<span id="warrantStatusNotReqd" class="hidden">
			<bean:message key="prompt.warrantStatus"/>
		</span>
	</td>  
    <td class=formDe>
	    <html:select size="1" property="warrantStatusId" styleId="warrantStatusCd">
	      	<html:option key="select.generic" value="" />
			<html:optionsCollection name="codeHelper" property="warrantStatuses" value="code" label="description" />          		
		</html:select>
	</td>
  </tr>
  <tr>
	<td class=formDeLabel width=1% nowrap><bean:message key="prompt.issue"/> <bean:message key="prompt.dateRange"/></td>
    <td class=formDe>
        <html:text name="juvenileWarrantForm" property="searchDate1" size="10" styleId="from"/> -
 		<html:text name="juvenileWarrantForm" property="searchDate2" size="10" styleId="to"/>    	
	</td>
  </tr>
</table>
</span>

 
<!-- SEARCH BY AGENCY ID -->  
<span id="agencyId" class=hidden>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
 <tr>
   	<td class="formDeLabel" width="1%" nowrap>Agency Name</td>
    <td class="formDe"><html:text property="warrantOriginatorAgencyName" size="60" maxlength="60"/></td>
  </tr>		
  <tr>
   	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.agencyId"/></td>
    <td class="formDe"><html:text property="warrantOriginatorAgencyId" size="5" maxlength="3"/></td>
  </tr>						
</table>
</span> 
 
<!-- SEARCH BY OFFICER ID --> 
<span id="officerId" class=hidden>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
  <tr>
  	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.1.diamond"/><bean:message key="prompt.officerIdNumber"/></td>
	<td class="formDe"><html:text property="officerId" size="11" maxlength="11"/></td>
  </tr>
  <tr>
	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.1.diamond"/><bean:message key="prompt.officerIdType"/></td>
	<td class="formDe"><html:select property="officerIdTypeId">
	           		   <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
	               	   <html:optionsCollection name="codeHelper" property="officerIdTypes" value="code" label="description" />
	           			</html:select></td>    
  </tr>
  <tr>
	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.1.diamond"/><bean:message key="prompt.department"/><bean:message key="prompt.code" /></td>
	<td class="formDe"><html:text property="officerAgencyId" size="3" maxlength="3" /></td>
  </tr>     					
</table>
</span>

<!-- SEARCH BY RESPONSIBLE ADULT NAME -->
<span id="adultName" class=hidden>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
  <tr>
    <td class="formDeLabel"><bean:message key="prompt.responsibleAdult"/>
                                          <bean:message key="prompt.lastName"/></td>
    <td class="formDe"><html:text property="associateName.lastName" size="30" maxlength="75" /></td>
  </tr>
  <tr>
    <td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.plusSign"/><bean:message key="prompt.responsibleAdult"/>
                                                               <bean:message key="prompt.firstName"/></td>
    <td class="formDe"><html:text property="associateName.firstName" size="25" maxlength="50"/></td>
  </tr>	    			
</table>
</span> 

<!-- SEARCH BY WARRANT ORIGINATOR ID --> 
<span id="warrantOriginatorId" class=hidden>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
  <tr>
   	<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.warrantOriginatorID"/></td>
    <td class="formDe"><html:text property="warrantOriginatorId" size="5" maxlength="5"/></td>
  </tr>						
</table>
</span>
 
<br>

<!-- BEGIN BUTTONS TABLE -->	
<table class="buttons" >
	<tr>
		<td>
			<html:submit property="submitAction" styleId="submit">
				<bean:message key="button.submit" />
			</html:submit>	
 			<html:submit property="submitAction">
				<bean:message key="button.refresh" />
			</html:submit>
		 </td>
	</tr> 
</table>
<!-- END BUTTON TABLE -->

</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>