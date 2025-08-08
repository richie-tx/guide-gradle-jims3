<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
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
	
	<style>
		 .mceEditor { width:100%; height:150px }
		 .mceEditor2 { width:100%; height:250px }
	</style>
													
	<html:base />
	
	<title>Common Supervision - common/spellCheckButtonTile.jsp</title>
	
	<script language="JavaScript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
	<script language="JavaScript" src="/<msp:webapp/>js/RapidSpellWeb.js"></script>
	
</head> 

<span class="hidden">
	<bean:define id="fakeButtonHolder" value="spellCheckerButtonId" type="java.lang.String"/>
	<RapidSpellWeb:rapidSpellWebLauncher
		id="<%=fakeButtonHolder%>"
		buttonText=" "
		mode="popup"
		rapidSpellWebPage="jsp/common/spellCheckPopUp.jsp"
		textComponentInterface="Custom"
		textComponentName="componentName"
		ignoreCapitalizedWords="false"
		ignoreWordsWithDigits="true"
		allowAnyCase="true"
		includeUserDictionaryInSuggestions="true"
		userDictionaryFile='<%=java.lang.System.getenv("KEYOTI_DICT_DIR")+"/csc-dictionary.txt"%>'
		allowMixedCase="true"
		ignoreXML="true"
		enableUndo="true"
		leaveWindowOpenForUndo="false"
		showNoErrorsMessage="true"
		windowWidth="500"
		/>
		
</span>