<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix ="RapidSpellWeb" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<head>
	<msp:nocache />
	<meta http-equiv="Content-Language" content="en-us">
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<meta name="GENERATOR" content="IBM WebSphere Studio">
	<meta http-equiv="Content-Style-Type" content="text/css">

	<!-- STYLE SHEET LINK -->
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
	<html:base />
	<title></title>
</head> 

<tiles:useAttribute name="tTextField" />
<tiles:useAttribute name="tSpellCount" />

<bean:define id="ttext" ><bean:write name="tTextField" /></bean:define>
<bean:define id="scName" ><bean:write name="tSpellCount" /></bean:define>

<RapidSpellWeb:rapidSpellWebLauncher
  	id="<%=scName%>"
  	buttonText=" "
	mode="popup" 
	ignoreXML="false" 
	rapidSpellWebPage="jsp/common/spellCheckPopUp.jsp" 
	textComponentName="<%=ttext%>"
	userDictionaryFile='<%=java.lang.System.getenv("KEYOTI_DICT_DIR")+"/csc-dictionary.txt"%>'
	ignoreXML="true"
	includeUserDictionaryInSuggestions="true"
	windowWidth="500"
	/>

<style type="text/css">
.myspelling
{
  height: 23px; 
	width: 23px; 
	background-color: #cccccc ;
	background-image: url("/<msp:webapp/>images/spelling.png") ; 
	background-repeat: no-repeat; 
	background-position:  center; 
	vertical-align: middle;
}
</style>

<script type='text/javascript'>
var allBtns = document.getElementsByTagName('input');
var i = 0 ;
for( /*empty*/  ; i < allBtns.length; i++ )
{
  if( allBtns[ i ].type == "button" )
	{ 
    if( allBtns[ i ].value == " " )
  	{
  	  allBtns[ i ].className = "myspelling" ;
  	}
	}
} 
</script>