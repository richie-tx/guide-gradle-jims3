<!DOCTYPE HTML>
<!-- associated object List-->


<%-- LDeen   3/6/2006  Added HTML:error tag --%>
<%-- CShimek 03/16/2006 #29680 Revised content in Helpful Information --%>
<%-- CShimek 04/03/2006 #30242 Revised Helpful Information content to point to pdf document --%>
<%-- CShimek 04/13/2006 #30463 Revised href for Usability Tips --%>
<%-- CShimek 02/02/2007 Corrected problem of "About" not displaying correctly, would display JIMS2 Log In instead
						of aboutjims2.htm after user logged out.  Worked fine on initial entry --%>
<%-- LDeen   05/23/2007 #42239 Remove Cancel button --%>
<%-- LDeen   05/23/2007 #46265 Add instruction re pop up blocker--%>
<%-- LDeen   07/14/2009 #60717 Revise Usability tips URL & add info re copy and paste--%>
<%-- L Deen  09/18/2009 #62066 Add PASO Instructions for CLOs link page --%>
<%-- L Deen  11/18/2009	#62845 Revise news & notes section --%>
<%-- L Deen  11/18/2009	#59843 Revise news & notes section to add Help Instructions--%>
<%-- L Deen  06/10/2010	#65923 Revise news & notes section to add CSCD Faqs--%>
<%-- L Deen  06/25/2010 Revise news & notes section to add IE settings & System Downtimes--%>
<%-- L Deen  08/05/2010 Revise news & notes section to fix link for help instructions PDF--%>
<%-- D James 06/15/2010 #73337 Revise news & notes section to add link for MJCW FAQs--%>


<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<!--JQUERY FRAMEWORK-->
<%@include file="jQuery.fw"%>



<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>JIMS2 Login - login.jsp</title>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/appshell.css" />
<msp:nocache />

<script type="text/javascript" src="/<msp:webapp/>js/login.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/OpenHelper.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script type="text/javascript">
function checkIfTopFrame()
{
  if( self != top )
  {
    window.open( self.location.href, "_top", "" );
  }
  return true;
}

function helpPopUp(url)
{
	var leftPos = 20;
	var wt = 600;
	var ht = 500;

	if( screen ) 
	{
		wt = screen.width - 200;
		ht = screen.height - 200;
		leftPos = (screen.width - wt) / 2;
	}
	var settings = "toolbar=no,location=no,directories=no,minimize=no," +
				"status=no,menubar=no,scrollbars=no,resizable=yes,dependent=no" +
				"width=" + wt + ",height=" + ht + ",left=" + leftPos + ",top=20";
	var helpWin = window.open(url,'',settings);
	helpWin.focus();
}

function openCustomRestrictiveWindow(url,heightSize, widthSize)
{
	var newWin = window.open(url, "", "height=" + heightSize +",width=" + widthSize + ",toolbar=no,scrollbars=yes,resizable=no,status=no,location=no,menubar=no,dependent=no");
	newWin.focus();
}
</script>
<script type="text/JavaScript" src="/appshell/js/RoboHelp_CSH.js"></script>
<script type="text/JavaScript" src="/appshell/js/helpFile.js"></script>
<base href="$url" />
<!-- <html:base/> -->

<body topmargin="0" leftmargin="0" onLoad="checkIfTopFrame(); document.forms[0].logonId.focus();">
<html:form action="/handleValidateUserAction" focus="logonId" target="_top" styleId="loginForm">

<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|87">

<!-- BEGIN HEADING TABLE -->
<table width='100%' cellspacing="0">
	<tr bgcolor='#003366'>
		<td align="left"><img src="/<msp:webapp/>images/Header.jpg"></td>
		<td align="right" valign="top">
		<br>
			<table cellpadding="1" cellspacing="1" border="0">
				<tr>
					<td class="banner"><script>document.write( getCurrentDay() );</script></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td class="banner"><script>document.write( getCurrentDate() );</script></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center" class="header">WELCOME TO JIMS2</td>
	</tr>
	<logic:equal name="loginForm" property="action" value=""> 	
    	<tr>
    		<td colspan="2" align="center" class="confirm"><bean:write name="loginForm" property="confirmMessage"/></td>
	    </tr>
  	</logic:equal>
</table>

<table width="100%">
<logic:notEmpty name="loginForm" property="errorMessage">
 <tr>		  
    <td align="center" class="errorAlert"><bean:write name="loginForm" property="errorMessage"/></td>		  
  </tr> 
</logic:notEmpty>
 <logic:empty name="loginForm" property="errorMessage">
  <tr>		  
    <td align="center" class="errorAlert"><html:errors></html:errors></td>		  
  </tr>  
  </logic:empty> 	  
 </table>
<!-- END HEADING TABLE -->

<!-- BEGIN MAIN TABLE -->
<table width="98%" align="center" cellspacing="0" cellpadding="2">
	<tr valign="middle">
		<td align="center">
<!-- BEGIN LOGIN INSTRUCTION TABLE -->
			<table width='100%' cellspacing="1" cellpadding="2">
				<tr>
					<td width="80%" class="required"><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border="0" width="10" height="9">&nbsp;Required Fields</td>
					<td width="10%" align="right" nowrap="nowrap" class="subhead"><a href="#" onClick="openCustomRestrictiveWindow('/<msp:webapp/>' +'jsp/aboutjims2.html',450,450)" class="hyperlink">&nbsp;About&nbsp;</a></td>
<%--   	  			<td width="10%" align="right" nowrap="nowrap" class="subhead"><a href="#" onclick="helpPopUp('/<msp:webapp/>' + 'jsp/loginHelp.jsp')" class="hyperlink">&nbsp;Help&nbsp;</a></td>  --%>  
					
				</tr>
			</table>
<!-- END LOGIN INSTRUCTION TABLE -->
<!-- END BEGIN TABLE -->
  			<table width='100%' cellspacing="0" cellpadding="2" class="borderTableBlue">
				<tr>
					<td colspan="2" class="detailHead" align="center"><div align="center">JIMS2 Log In</div></td>
				</tr>			
				<tr>
					<td>
						<table>   
							<tr>
								<td class="formDeLabel" nowrap="nowrap" width='1%'><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border="0" width="10" height="9"><bean:message key="prompt.authenticationMethod"/></td>
								<td class="formDe">
									<html:select name="loginForm" property="authenticationMethod">
											<html:option key="select.generic" value=""/>
											<html:option key="select.serviceProvider" value="SP"/>
											<html:option key="select.adAccount" value="AD"/>
											<%-- <html:optionsCollection property="authenticationMethods" value="code" label="code"/> --%> 				
									</html:select>
								</td>
							</tr>		
							<tr>
								<td class="formDeLabel" nowrap="nowrap" width='1%'><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border="0" width="10" height="9"><bean:message key="prompt.userId"/></td>
								<td class="formDe" autocomplete='on'><html:text name="loginForm" property="logonId" maxlength="50" size="55"/></td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap="nowrap" width='1%'><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border="0" width="10" height="9"><bean:message key="prompt.password"/></td>
								<td class="formDe"><html:password name="loginForm" property="password" maxlength="50" size="55" value=""/></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>	 	
<!-- END LOGIN TABLE -->  
<!-- BEGIN BUTTON TABLE -->
			<table border="0" width="100%">
				<tr>
					<td align="center">
						<html:submit property="submitAction" onclick="return validate();" styleId="submitBtn"><bean:message key="button.submit"></bean:message></html:submit>
						<html:submit property="submitAction"><bean:message key="button.reset"></bean:message></html:submit>
						</td>
				</tr>
			</table>
<!-- END BUTTON TABLE -->
		</td>
	</tr>
</table>
<!-- END MAIN TABLE -->
<br>
<!-- BEGIN NEWS TABLE -->
<table align="center" width="70%" cellspacing="0" cellpadding="2" class="borderTableBlue">
	<tr>
		<td colspan="2" class="detailHead" align="center">NEWS &amp; NOTICES</td>
	</tr>
	<tr>
		<td align="left">
			<table width="100%" cellspacing="1" cellpadding="4">
	  		<tr class="formDeLabel"></tr>
	  		<tr>
	  			<td>
	  				<p>Questions:  <a href="mailto:Data.Corrections@hcjpd.hctx.net">Data.Corrections@hcjpd.hctx.net</a></p>
	  			</td>
	  		</tr>
			<tr>
				<td>
					<p>Production Releases: Click here to view <a href="https://webapps.harriscountytx.gov/ReleaseNotes/applications"  target="_blank">Release Notes</a></p>
				</td>
			</tr>
 	 		<tr class="formDeLabel">
					<td>Scheduled System Downtimes</td>
		  	</tr>
				<tr>
					<td>Monday - Friday  	5:00 am - 6:00 am</td>
				</tr>
				<tr>
					<td>Saturday - Sunday  	7:00 am - 8:00 am</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<!-- END NEWS TABLE -->

<table align="center" width="70%" cellspacing="0" cellpadding="2" class="bannerText">
	<tr>
		<td colspan="2" align="left" class="errorAlert">Warning:</td>
	</tr>
	<tr>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td colspan="2" align="left" class="bannerText">
		    All users are advised that this is a restricted information system for authorized use only by authorized members of the law enforcement and criminal justice community. System usage may be monitored, recorded, and subject to audit. Use of the system indicates consent to monitoring and recording. Unauthorized access of this system or unauthorized use of the information provided on the system is prohibited and may be subject to criminal and /or civil penalties.
		</td>
	</tr>	
	<tr>
		<td colspan="2"></td>
	</tr>
</table>

</html:form>

</body>

