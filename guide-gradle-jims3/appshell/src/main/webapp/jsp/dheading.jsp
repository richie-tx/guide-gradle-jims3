<!DOCTYPE HTML>
<!--MODIFICATIONS -->
<!-- LDeen	 03/29/04	Create JSP -->
<!-- CShimek 02/23/06   Added js code for help functionality -->
<!-- CShimek 03/17/2006 Change URL for help file for SSL per email from Brian Bollich -->
<!-- CShimek 06/19/2006 Increased name value size and descreased date size for longer name displays -->  
<!-- CShimek 02/16/2009 #57242, revised script so QA region accesses production help files -->
<!-- CShimek 10/15/2010 #67877, revised script so QA region does not access production help files --> 
<!-- Cshimek 03/21/2011 #68893, revised Help from href to button to resolve back button logout issue -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<html:html>
<%-- jsp:useBean id="userInfo" scope="session" class="messaging.appshell.UserEvent" type="messaging.appshell.UserEvent"/ --%>
<jsp:useBean id="regionInfo" scope="page" class="pd.security.RegionType" type="pd.security.RegionType"/> 
<!--BEGIN HEADER TAG-->
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<!--msp:login --/>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/appshell.css" />
<html:base />
<title><bean:message key="title.heading"/> - Heading</title>
<style type="text/css">
input.helpBtn { 
	font-family: Arial, Helvetica, sans-serif;
	font-size: 13px;
	font-weight: bold;
	color: #003366;
	text-decoration: underline;  
	background-color: transparent; 
	border:0px solid; 
	border-color: #fff #fff #fff #fff;  
</style>
<script type="text/JavaScript" src="/<msp:webapp/>js/RoboHelp_CSH.js"></script>
<script type="text/JavaScript">

//User Story 11052
function helpNewPopUp(){
	var newWin = window.open("http://www.harriscountytx.gov/CmpDocuments/70/JIMS2/Juvenile_Probation_PROJECT_II_Training_Guide.pdf ", "helpWindow", "height=600,width=600,toolbar=no,scrollbars=yes,resizable=yes,status=no,location=no,menubar=no,dependent=no");
	newWin.moveTo(200,200);
	newWin.focus();
}
function helpPopUp(){
	var z = parent.content.document.getElementsByName("helpFile");
	if (z.length != 1) 
	{
		alert("No Help file reference found for this page.");
		return false;
	}
	var strHelpFile = z[0].value;
	if (strHelpFile.length == 0)
	{
		alert("No Help file reference found for this page.");
		return false;	
	}
	var indx = strHelpFile.indexOf('|');
	var useCase = strHelpFile;
	var mapId = 0;
	if (indx > -1)
	{
		useCase = strHelpFile.substring(0,indx);
        mapId = strHelpFile.substring(indx + 1,indx + 4);
		if (isNaN(mapId) == true)
		{  
			mapId = 0;
		}
	}
	var region = document.forms[0].region.value.toUpperCase();
/*  var url  = "http://jims2help.hcintranet.net/" + useCase + "/jims.htm#"; */
/* Use this for production  */
   var url  = "https://help.jims2.hctx.net/" + useCase; 
/* Use this for test  */
	if (region != "PROD"){
		url  = "http://webtest2/" + useCase;
	}
//	var uAstr = navigator.userAgent.toUpperCase();
//	var indx = uAstr.indexOf("FIREFOX");
/*  RH_ShowHelp does not work to Firefox browser, use window.open() instead */	
//	if(indx > -1){
	if (mapId == 0){
		var	wt = screen.width - 100;
		var	ht = screen.height - 180;
		var windowSettings = "toolbar=no,location=no,directories=no,minimize=no," +
		           "status=no,menubar=no,scrollbars=yes,resizable=yes,titlebar=no," +
       		       "width=" + wt + ",height=" + ht + ",left=50,top=20"; 
		window.open(url,'',windowSettings);
		return false; 
	}	
	url = url + "<id=" + mapId;
	RH_ShowHelp(0, url, HH_HELP_CONTEXT, mapId);
	return false;
 }

 var appLoggedout = true;
 
 function checkClosure(internetExplorer) 
 {
   if (appLoggedout)
   {
       if(internetExplorer == true)     
       {
           window.location.href = 'displayLogout.do';
       }
       else
       {
           window.location.href = '/appshell/displayLogout.do';
       }    
   }
 }

 </script> 

</head>

<!--BEGIN BODY TAG-->
<body leftmargin="0" topmargin="0" onunload="checkClosure(true)" onbeforeunload="checkClosure(false)">
<form name="jims2heading">

<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr bgcolor="#000080">
		<td width="1%">
			<font size="4" color="#d0d0d0" face="arial">&nbsp;JIMS2</font>
		</td>
		<td width="85%" align="center" height="26">
			<font size="4" color="#d0d0d0" face="arial">Justice Information Management System</font>
		</td>
		<td width="2%">
			<font size="4" color="#d0d0d0" face="arial"><jsp:getProperty name="regionInfo" property="region"/>&nbsp;</font>
			<input type="hidden" name="region" value="<jsp:getProperty name="regionInfo" property="region"/>" > 	
	<%-- 	<font size=4 color="#d0d0d0" face=arial><jsp:getProperty name="userInfo" property="server"/>&nbsp;</font>
			<input type="hidden" name="region" value=<jsp:getProperty name="userInfo" property="server"/> > --%>
		</td>
	</tr>
</table>

<table background="../images/BGHeaderShadow.gif" width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td width="1" nowrap="nowrap" width="1" height="26"></td>
		<td width="5%" align="right" nowrap="nowrap" class="subhead"><b>User ID:</b>&nbsp;</td>
		<td width="10%" align="left" nowrap="nowrap" class="bodyText"><jsp:getProperty name="userInfo" property="userID"/>&nbsp; </td>
		<td width="5%" align="right" nowrap="nowrap" class="subhead"><b>User Name:</b>&nbsp; </td>
		<td width="32%" align="left" nowrap="nowrap" class="bodyText"><jsp:getProperty name="userInfo" property="userName"/></td>
		   <!-- non-functional Logout and Help -->
		<td width="30%" align="left" nowrap="nowrap" class="bodyText">
			<script Language="Javascript">
			   var monthlit = new Array ("January","February","March","April","May","June","July","August","September","October","November","December");
			   var daylit = new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday");
			   today = new Date();
			   document.write('&nbsp;&nbsp;' + daylit[today.getDay()] + '&nbsp;&nbsp;' + monthlit[today.getMonth()] + ' ' +
							   today.getDate() + ', ' + today.getFullYear());
			</script>
		</td>
		<td width="10%" align="right"  nowrap="nowrap" class="subhead"><b><a target="_top" onclick="appLoggedout=true" href="/<msp:webapp/>displayLogout.do">Logout</a></b>&nbsp;</td>
		<td width="7%" align="right" nowrap="nowrap" class="subhead"><b>
		
			<input type="button" name="showHelp" value="Help" onclick="helpNewPopUp()" class="helpBtn" />&nbsp; 		
		
	<%--	<a href="#" onclick="appLoggedout=false; helpPopUp()">Help</a></b>&nbsp; --%>
		</td>
	</tr>
</table>
</form>
</body>
</html:html>
