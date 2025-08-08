<!DOCTYPE HTML>
<!--MODIFICATIONS -->
<%-- CShimek    08/15/2005  revised to new look and feel --%>
<%-- CShimek    10/06/2005  revised title to generic initiate warrant --%>
<%-- CShimek    02/09/2006  Added hidden field helpFile for each warrant type --%>
<%-- CShimek    12/21/2006  Revised helpfile reference value--%>
<%-- LDeen		06/04/2007  #42564 changed path to app.js --%>
<%-- RYoung     08/06/2015  #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (Juv Profile - Education UI) --%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

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
<title><bean:message key="title.heading"/> - warrantJOTSummaryofFacts.jsp</title>

<!-- JAVASCRIPT FILES-->
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
</head>

<!--BEGIN BODY TAG-->
<body>

<!-- BEGIN HEADING TABLE -->
	<!-- Heading table -->
<!-- Need to add warrant type to this title -->	
	<table width="100%">
      <tr>
        <td align="center" class="header">Initiate Warrant - Summary of Facts </td>
      </tr>
  </table>
  
<!-- END HEADING TABLE -->

<!-- BEGIN HEADING/REQUIRED INFORMATION TABLE --> 
 <table width=98% border=0 >
   <tr>
     <td>
	  <ul>
        <li>Click Back button to return to Warrant.</li>
	  </ul>
     </td>
  </tr>
</table>
<!-- END HEADING/REQUIRED INFORMATION TABLE --> 
<!-- BEGIN MAIN BODY TABLE -->
<table width=98% border=0 cellspacing=1 cellpadding=4 align=center>
<!-- BEGIN JUVENILE WARRANT INFORMATION BLOCK -->
	<tr>
		<td class=detailHead colspan=4 nowrap><bean:message key="prompt.juvenileWarrantInfo" /></td>
	</tr>
	<tr>
		<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.transactionNumber"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="transactionNum"/></td>
		<td class=formDeLabel width="1%" nowrap><bean:message key="prompt.juvenileNumber"/></td>
		<td class=formDe><bean:write name="juvenileWarrantForm" property="juvenileNum"/></td>
	</tr>
	<tr>
		<td class=formDeLabel><bean:message key="prompt.daLogNumber"/></td>
		<td class=formDe colspan=3><bean:write name="juvenileWarrantForm" property="daLogNum"/></td>
	</tr>
<!-- END JUVENILE WARRANT INFORMATION BLOCK -->
<tr><td><br></td></tr>
</table>
<!-- BEGIN SUMMARY OF FACTS INFORMATION BLOCK -->
<table width=98% border=0 cellspacing=0 align=center>
	<tr>
		<td class=detailHead><bean:message key="prompt.summaryOfFacts"/></td>
	</tr>
	<logic:iterate id="fact" name="juvenileWarrantForm" property="summaryOfFacts">
		<tr>
			<td class=formDe><bean:write name="fact" /></bean:write></td>
		</tr>
	</logic:iterate>
<!-- END SUMMARY OF FACTS INFORMATION BLOCK -->
</table>
<!-- END MAIN BODY TABLE -->
<br>
<!-- BEGIN BUTTON TABLE -->
<table border=0 align=center>
      <tr valign=top>
        <td>
          <html:button
				property="org.apache.struts.taglib.html.BUTTON"
				onclick="history.go(-1);">
				<bean:message key="button.back"></bean:message>
		  </html:button>
	    </td>
	    <td>  	
 		  <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="arr">	
		    <html:form action="/displayJOTSearch.do?warrantTypeUI=arr">
			    <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|120"> 
              <html:submit>
                <bean:message key="button.cancel"></bean:message>
              </html:submit>
            </html:form> 
		  </logic:equal>
          <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="dta">	
		    <html:form action="/displayJOTSearch.do?warrantTypeUI=dta"> 
			    <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|120"> 		    
              <html:submit>
                <bean:message key="button.cancel"></bean:message>
              </html:submit>
            </html:form> 
	      </logic:equal>
          <logic:equal name="juvenileWarrantForm" property="warrantTypeUI" value="pc">	
			    <input type="hidden" name="helpFile" value="juvwarnt/juvenile_warrants_final.htm#|120">           
		    <html:form action="/displayJOTSearch.do?warrantTypeUI=pc"> 
              <html:submit>
                <bean:message key="button.cancel"></bean:message>
              </html:submit>
            </html:form> 
		  </logic:equal>
        </td>
      </tr>
  </table>
<!-- END BUTTON TABLE -->
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html>