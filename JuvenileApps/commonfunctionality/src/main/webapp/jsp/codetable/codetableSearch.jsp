<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 02/07/2007		AWidjaja Create JSP--%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>



<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/commonfunctionality.css" />
<html:base />

<title><bean:message key="title.heading"/>&nbsp;Manage Code Table&nbsp;- codetableSearch.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%-- Javascript for emulated navigation --%>
<html:javascript formName="codetableSearch"/>

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript">
function checkSearch()
{
	var tableName = document.codetableForm.promptCodetableName;
	var tableDescription =document.codetableForm.promptCodetableDescription;
	var elem1 = tableName.value;
	var elem2 = tableDescription.value;	
	if((elem1==null||elem1=="") && (elem2==null||elem2=="")){
	alert("Enter At least one Search field");	
	return false;
	}
	if(!(elem1==null||elem1=="")&& elem1.length<2){
	alert("Enter At least 2 characters for a search field");
	tableName.focus();
	return false;
	}
	if(!(elem2==null||elem2=="")&& elem2.length<2){
	alert("Enter At least 2 characters for a search field");
	tableDescription.focus();
	return false;
	}
	else
	return true;
}

</script>
</head>

<html:form action="/displayCodetableRecordSearchResults" target="content" focus="promptCodetableName"> 
<body topmargin=0 leftmargin='0' onKeyDown="return checkEnterKeyAndSubmit(event,true)">



<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header" >Manage Code Table - Code Table Search</td>
  </tr>
  <tr>
	<td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"/></td> 
  </tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%" border="0">
  <tr>
    <td>
      <ul>
        <li>At least one search field is required for search criteria</li>
        <li>Select the Refresh button to clear the data.</li>
         <li>Select the Submit button to execute the search .</li>
      </ul>
    </td>
  </tr>
  <tr>
    
  </tr>
</table>
<%-- END INSTRUCTION TABLE --%>

  <%-- search field --%>
  <table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class=borderTableBlue>
    <tr>
      <td class="formDeLabel" width="1%" nowrap>      
      	<bean:message key="prompt.codetableName"/>
      </td>
      <td class="formDe"> 
		<html:text name="codetableForm"  property="promptCodetableName" size="30" maxlength="30" /> 
	  </td>
    </tr>
    <tr>
     <td class="formDeLabel" width="1%" nowrap>    
      	<bean:message key="prompt.codetableDescription"/>
      </td>
      <td class="formDe"> 
		<html:text name="codetableForm" property="promptCodetableDescription" size="30" maxlength="30" /> 
	  </td>
    </tr>
  </table>
  <br>


<%-- BEGIN BUTTON TABLE --%>
<br>
<table border="0" width="100%">
	<tr>
		<td align="center"> 
			<html:submit property="submitAction" onclick="return checkSearch() && disableSubmit(this, this.form)"><bean:message key="button.submit"/></html:submit>
	        <html:submit property="submitAction"><bean:message key="button.refresh"/></html:submit>
	        <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>


</body>
</html:form>

<span align=center><script type="text/javascript">renderBackToTop()</script></span>

</body>
</html:html>

