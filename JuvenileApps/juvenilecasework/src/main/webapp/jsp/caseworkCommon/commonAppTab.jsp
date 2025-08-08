<!DOCTYPE HTML>

<%-- Manages Tabs for Juvenile Casefiles Constellation stuff--%>
<%-- 05/20/2005		glyons	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>


<%--BEGIN HEADER TAG--%>
<head>
	<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
	<msp:nocache />
	<meta http-equiv="Content-Language" content="en-us">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<meta name="GENERATOR" content="IBM WebSphere Studio">
	<meta http-equiv="Content-Style-Type" content="text/css">

	<%-- STYLE SHEET LINK --%>
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
	<html:base />
	<title><bean:message key="title.heading"/>/commonAppTabs.jsp</title>
</head> 


<table border=0 cellpadding=0 cellspacing=0>
	<tr>
    <bean:define id="tab"><tiles:getAsString name="tabid" /></bean:define>	
    <logic:equal name="tab" value="commonApp">		      
      <td bgcolor='#33cc66' align=left class=topNav><a href="/<msp:webapp/>displayCommonAppCourtOrderUpdate.do?submitAction=Link" class=topNav>Common App</a></td>		     			
    </logic:equal>

    <logic:notEqual name="tab" value="commonApp">				
      <td bgcolor='#99ff99' align=left class=topNav><a href="/<msp:webapp/>displayCommonAppCourtOrderUpdate.do?submitAction=Link" class=topNav>Common App</a></td>
    </logic:notEqual>
	</tr>
</table>

