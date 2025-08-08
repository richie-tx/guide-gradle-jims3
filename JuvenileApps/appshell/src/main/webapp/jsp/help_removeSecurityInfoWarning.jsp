<!DOCTYPE HTML>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<html>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>JIMS2 Login - help_removeSecurityInfoWarning.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/appshell.css" />
	<msp:nocache />
	<script language="JavaScript" src="/<msp:webapp/>js/login.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body topmargin=0 leftmargin=0>

<!-- BEGIN HEADING TABLE -->
<table width='100%' cellspacing=0>
  <tr>
    <td colspan=2 align="center" class="header">How to remove Security Information Warning from displaying after login</td>
  </tr>
</table>
<!-- END HEADING TABLE -->


<!-- BEGIN MAIN TABLE -->
<br>
<table width="98%" align="center" cellspacing="0" cellpadding="2">
	<tr valign="middle">
	   <td align=center>
	<!-- BEGIN INSTRUCTION TABLE -->
			<table width='100%' cellspacing="1" cellpadding="2">
				<tr>
					<td>The steps listed below are for the latest version of Internet Explorer.  Older versions may display differently.</td>
				</tr>
        <tr>
					<td>
            <ul>
  						<li>Please follow these steps:</li>
	  				</ul>
					</td>
        </tr>
        <tr>
					<td>
					  <ol>
							<li>Select Tools from the Internet Explorer main menu bar.</li>
							<li>Select Internet Options.</li>	
							<li>Select the Security tab.</li>					  
							<li>Be sure Internet is highlighted and click "Custom Level..." button near the bottom of this window.<br><img src="/<msp:webapp/>images/SecurityInfo1.jpg"></li>
							<li>A Security Settings window opens. Scroll down until "Display mixed content" is visible.</li>
							<li>Select "Enable" under this option. (default selection is Prompt).<br><img src="/<msp:webapp/>images/SecurityInfo2.jpg"></li>
							<li>Select "OK" button.</li>
							<li>You will see a window that looks like the following.<br><img src="/<msp:webapp/>images/SecurityInfo3.jpg"></li>
							<li>Select the "<u>Y</u>es" button.</li>
							<li>Select "OK" to close the Internet Options window.
							<li>The next time you log in you should not see this warning.<br><img src="/<msp:webapp/>images/SecurityInfo4.jpg"></li>
					  </ol>
					</td>
        </tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
      </table><!-- END INSTRUCTION TABLE -->
    </td>
	</tr>
</table>
<!-- END MAIN TABLE -->

</body>
</html>
