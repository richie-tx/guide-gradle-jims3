<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" /> 
<meta name="GENERATOR" content="IBM WebSphere Studio">
<meta http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<title><bean:message key="title.heading"/>/exhibitBTab.jsp</title>
</head>
<table border=0 cellpadding=0 cellspacing=0>
  <tr>
  	<bean:define id="tab"><tiles:getAsString name="tabid" /></bean:define>
  	<logic:equal name="tab" value="petitions">
      <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
  		<td bgcolor='#ff6666' align=center>Petitions</td><!-- onclick="showAlert();" -->
      <td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>				
  	</logic:equal>
  	<logic:notEqual name="tab" value="petitions">
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>
  		<td bgcolor='#ffcccc' align=center>Petitions</td><!-- onclick="showAlert();"  -->
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>
  	</logic:notEqual>
  	
  	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>
  	<logic:equal name="tab" value="programServices">
  		<td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
  		<td bgcolor='#ff6666' align=center>Program/Services</a></td><!-- onclick="showAlert();" --> 
  		<td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>				
  	</logic:equal>
  	<logic:notEqual name="tab" value="programServices">
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>
  		<td bgcolor='#ffcccc' align=center>Program/Services</td>
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>
  	</logic:notEqual>
  	
  	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>
  	<logic:equal name="tab" value="circumstances">
  		<td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/left_red_active_tab.gif"></td>
  		<td bgcolor='#ff6666' align=center>Circumstances</td><!-- onclick="showAlert();" --> 
  		<td bgcolor='#ff6666' valign=top><img src="/<msp:webapp/>images/right_red_active_tab.gif"></td>				
  	</logic:equal>
  	<logic:notEqual name="tab" value="circumstances">
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/left_red_inactive_tab.gif"></td>
  		<td bgcolor='#ffcccc' align=center>Circumstances</td>
  		<td bgcolor='#ffcccc' valign=top><img src="/<msp:webapp/>images/right_red_inactive_tab.gif"></td>
  	</logic:notEqual>
  	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" width=2></td>	
  	
  	
  </tr>
</table>