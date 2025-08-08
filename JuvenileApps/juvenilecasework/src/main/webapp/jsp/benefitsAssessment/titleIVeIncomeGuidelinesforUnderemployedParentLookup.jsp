<%-- 11/09/2005	 Aswin Widjaja - Create JSP --%>
<%-- 07/17/2009  C Shimek -  #61004 added timeout.js  --%>
<%-- 08/31/2015 RCapestani #29429 MJCW:  Adapt MJCW and Warrants to IE9, IE11 and Chrome (Benefits Assessment UI) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%-- TAB LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>





<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<html:base />

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/> - benefitsAssessmentMemberList.jsp</title>
</head>

  <%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
      <%-- BEGIN HEADING TABLE --%>
      <table width="100%">
        <tr>
          <td align="center" class="header">Income Guidelines for Underemployed Parent</td>
        </tr>
      </table>
      <%-- END HEADING TABLE --%>
			<br> 
      <%-- BEGIN INSTRUCTION TABLE --%>
      <table align="center" width="98%" border="0">
        <tr>
          <td>
            <ul>
              <li>Use this chart only when two parents live in the Home of Removal and neither parent is physically or mentally incapacitated.</li>
              <li>To determine if the Primary Wage Earner (PWE) meets the income guidelines for Underemployed Parent, compare the PWE's monthly gross earned income to the appropriate maximum income limit for the appropriate certified group size.</li>
              
				  <li>The certified group size is both parents plus the number of dependents in the AFDC Certified Group.</li>
              <li>If the PWE's monthly gross earned income <b>exceeds</b> the maximum income limit the PWE <b>is not</b> considered underemployed.</li>
				  <li>If the PWE's monthly gross earned income is <b>equal to or less</b> than the maximum income limit the PWE <b>is</b> considered underemployed.</li>
            </ul>
          </td>
        </tr>
      </table>
      <%-- END INSTRUCTION TABLE --%>
      <br>

     	<%-- BEGIN DETAIL TABLE --%>
      <table border=1 cellspacing=0 cellpadding=0 class="borderTableBlue"> 
         <tr> 
            <td width="175" align="center" class=detailHead>Certified Group Size</td> 
            <td width="175" align="center" class=detailHead>Maximum Income Limit</td> 
         </tr> 
         <tr align="center"> 
            <td class="Normal">1</td> 
            <td class="Normal">-</td> 
         </tr> 
         <tr align="center"> 
            <td class="Normal">2</td> 
            <td class="Normal">498</td> 
         </tr> 
         <tr align="center"> 
            <td class="Normal">3</td> 
            <td class="Normal">824</td> 
         </tr> 
         <tr align="center"> 
            <td class="Normal">4</td> 
            <td class="Normal">925</td> 
         </tr> 
         <tr align="center"> 
            <td class="Normal">5</td> 
            <td class="Normal">1073</td> 
         </tr> 
         <tr align="center"> 
            <td class="Normal">6</td> 
            <td class="Normal">1176</td> 
         </tr> 
         <tr align="center"> 
            <td class="Normal">7</td> 
            <td class="Normal">1319</td> 
         </tr> 
         <tr align="center"> 
            <td class="Normal">8</td> 
            <td class="Normal">1422</td> 
         </tr> 
         <tr align="center"> 
            <td class="Normal">9</td> 
            <td class="Normal">1595</td> 
         </tr> 
         <tr align="center"> 
            <td class="Normal">10</td> 
            <td class="Normal">1698</td> 
         </tr> 
         <tr align="center"> 
            <td class="Normal">11</td> 
            <td class="Normal">1871</td> 
         </tr> 
         <tr align="center"> 
            <td class="Normal">12</td> 
            <td class="Normal">1975</td> 
         </tr> 
         <tr align="center"> 
            <td class="Normal">13</td> 
            <td class="Normal">2147</td> 
         </tr> 
         <tr align="center"> 
            <td class="Normal">14</td> 
            <td class="Normal">2251</td> 
         </tr> 
         <tr align="center"> 
            <td class="Normal">15</td> 
            <td class="Normal">2423</td> 
         </tr> 
         <tr align="center"> 
            <td valign=top class="Normal">For each additional member</td> 
            <td valign=top class="Normal">173</td> 
         </tr> 
      </table>
      <%-- END DETAIL TABLE --%>
			 
      <br>
      <%-- BEGIN BUTTON TABLE --%>
      <table border="0" width="100%">
        <tr>
          <td align="center">
            <input type="button" value="Close" onclick="window.close();"> 
          </td>
        </tr>
      </table>
      <%-- END BUTTON TABLE --%>
	<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
