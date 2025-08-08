<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%--ADDED FOR ER-11051 GANG TATTOO --%>
<%-- START - This is the physical characteristics tile --%>
<%--MODIFICATIONS --%>
<%-- 06/17/2005	Hien Rodriguez	Create JSP --%>
<%-- 02/28/2006 Clarence Shimek Defect#27703 corrected row count in showSomethingFromDropDown() function call from 4 to 6 for new rows added --%>
<%-- 07/09/2007	LDeen			Defect #43502-Remove View All Photos button --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading"/> - juvenileTattoo.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script> 
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script> 

<script type='text/javascript'>
function onloadForm(tForm)
{  
	document.forms[0].reset();

	var r = argItems('source');

	if( r == 'photo' ) // other option for 'source' is 'photo
	{ // otherwise, user selected the photo itself to pop this window open
		// user selected the 'view all photos' button
		showHide( 'backToButton', 0 ) ; 
	}
}

function argItems (theArgName) 
{
	sArgs = location.search.slice(1).split('&');
	r = '';

  for (var i = 0; i < sArgs.length; i++) 
	{
	  if (sArgs[i].slice(0,sArgs[i].indexOf('=')) == theArgName) 
		{
	     r = sArgs[i].slice( sArgs[i].indexOf('=') +1 );
	     break;
	  }
	}

   return (r.length > 0 ? unescape(r).split(',') : '') ;
}
</script>

</head> 
<%--END HEAD TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="onloadForm(this);"  topmargin='0' leftmargin="0">
<html:form action="/getJuvenilePhoto">

<%-- BEGIN pics TABLE --%>
<table width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
	<tr>
		<td valign='top' class='detailHead'>Juvenile Tattoos</td>
	</tr>
	<tr>
		<td align='center'>
			<table width='100%' border="0" cellpadding="0" cellspacing="0">
			
				<logic:notEqual name="juvenilePhotoForm" property="selectedValue" value="">
  				<tr>
						<td><img alt="Mug Shot Not Available" title="Juvenile Tattoo" src="/<msp:webapp/>getJuvenilePhoto.do?submitAction=Photo Detail&isTattoo=true" width="350" border='1' /></td>
					</tr>
					<tr>
						<td align='center'>&nbsp;</td>
					</tr>
				</logic:notEqual>

				<logic:equal name="juvenilePhotoForm" property="selectedValue" value="">
  				<logic:greaterThan name='juvenilePhotoForm' property='totalTattoosAvailable' value='1'>
    				<logic:iterate name="juvenilePhotoForm" property="allTattoos" id="photoIndex">
    					<tr>
    						<td width='1%'>&nbsp;<a href="/<msp:webapp/>getJuvenilePhoto.do?submitAction=Link&&isTattoo=true&selectedValue=<bean:write name="photoIndex" property="photoName"/>"><img alt="Mug Shot Not Available" title="Juvenile Tattoo" src="/<msp:webapp/>getJuvenilePhoto.do?submitAction=Photo Thumbnail&isTattoo=true&selectedValue=<bean:write name='photoIndex' property='photoName'/>" width="128" border='1' /></a></td>
    						<td>&nbsp;<bean:write name='photoIndex' property='entryDate' formatKey="date.format.mmddyyyy"/></td>
    					</tr>
    					</logic:iterate>
  				</logic:greaterThan>	
				</logic:equal>
				</table>
		</td>
	</tr>
</table>
<%-- END pics TABLE --%>

<%-- Defect #43502 --%>
<logic:greaterThan name='juvenilePhotoForm' property='totalTattoosAvailable' value='1'>
  <logic:notEqual name="juvenilePhotoForm" property="selectedValue" value="">
  	<div class='spacer'></div>
  	<input id='backToButton' type='button' value="Back to All Tattoos" onclick="history.go(-1)" />
  </logic:notEqual>
</logic:greaterThan>	

<div class='spacer'></div>
<input type='button' value='Close' onclick='window.close();'>

<script type="text/javaascript">document.forms[0].reset();</script>

</html:form>

<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>

</html:html>
