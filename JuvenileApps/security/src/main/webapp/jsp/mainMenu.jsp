<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Modifications -->
<!-- 01/23/2007   CShimek  #38494 Revised content in Helpful Information to match welcome page -->

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<html>
<head>
<title>JIMS2 Main Menu - mainMenu.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
	<msp:nocache />
<script language="JavaScript" src="/<msp:webapp/>js/login.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<body topmargin=0 leftmargin=0>
<!-- HELP FILE FIELD -->
    <input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|169">
<!-- BEGIN HEADING TABLE -->
<table width=100%>
  <tr>
    <td align="center" class="header">WELCOME TO JIMS2</td>
  </tr>
  <tr><td>&nbsp;</td></tr>
  <tr>
  	<td>
		<ul>
			<li>To access an application, use the Navigation Tree on the left.</li>
			<li>Click on + to expand a list.</li>
			<li>Click on an underlined link to access a page.</li>
			<li>Help is available in the top right corner after you are logged in.</li>
		</ul>
	</td>
  </tr>
</table>
<!-- END HEADING TABLE -->

<!-- BEGIN MAIN TABLE -->
<table align="center" width=98% cellspacing="0" cellpadding="2" class="borderTableBlue">
	<tr>
		<td colspan="2" class="detailHead" align="center">NEWS & NOTICES</td>
	</tr>
	<tr>
		<td>
<!-- BEGIN NEWS TABLE -->		
			<table width=100% cellspacing="1" cellpadding="4">
				<tr class="formDeLabel">
					<td>Helpful Information</td>
				</tr>	
				<tr>
					<td>
						<p><a href="http://www.jims.hctx.net/jimsHome/manuals/jims2/Usability_Tips_For_JIMS2.pdf"" target="_blank">Usability Tips for JIMS</a>
						This document is in Adobe PDF format.&nbsp;
						To view this document requires Adobe Acrobat Reader.&nbsp;
						If you do not have Adobe Acrobat Reader, click the link below to
						download it.</p>
					</td>
				</tr>
				<tr>
					<td align="center">
						<a href="/appshell/jsp/help_getAcrobatReader.jsp" target="_blank"><img alt="Get Adobe Acrobat Reader" border="0" src="/appshell/images/getacro.gif" width="88" height="31"></a>
					</td>
				</tr>	
				<tr>
					<td>
						<p>JIMS2 is running in a secure environment.  For this reason, you may be getting a "Secure Information" warning after you login.
						<br><a href="/appshell/jsp/help_removeSecurityInfoWarning.jsp" target="_blank">How To remove "Secure Information" warning</a></p>
					</td>
				</tr>					
<%--			<tr>
					<td><a href="/<msp:webapp/>jsp/help_clearCache.jsp" target="_blank">To clear your cache.</a></td>
				</tr>
				<tr>
					<td><a href="/<msp:webapp/>jsp/help_allowPopups.jsp" target="_blank">To allow popups on your PC</a></td>
				</tr> 
				<tr>
					<td>&nbsp;</td>
				</tr> --%>
				<tr class="formDeLabel">
					<td>Scheduled System Downtimes</td>
				</tr>
				<tr>
					<td>Monday - Sunday  5:00 am - 6:00 am</td>
				</tr>
			</table>
<!-- END NEWS TABLE -->
		</td>
	</tr>
</table>
<!-- END MAIN TABLE -->
<br>
</body>
</html>
