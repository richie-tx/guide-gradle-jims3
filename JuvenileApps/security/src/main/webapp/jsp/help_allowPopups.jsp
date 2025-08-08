<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<html>
<head>
<title>JIMS2 Login - help_allowPopus.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
	<msp:nocache />
	<script language="JavaScript" src="/<msp:webapp/>js/login.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body topmargin=0 leftmargin=0>

<!-- BEGIN HEADING TABLE -->
<table width=100% cellspacing=0>
  <tr>
    <td colspan=2 align="center" class="header">Help on Allowing Popups on your PC</td>
  </tr>
</table>
<!-- END HEADING TABLE -->
<br>
<!-- BEGIN MAIN TABLE -->
<table width="98%" align="center" cellspacing="0" cellpadding="2">
	<tr valign="middle">
	   <td align=center>
	<!-- BEGIN INSTRUCTION TABLE -->
			<table width=100% cellspacing="1" cellpadding="2">
		        <tr>
		          <td colspan=2>
		            <ul>
		              <li>Please follow these steps:</li>
					  </ul></td>
		        </tr>
		        <tr>
		          <td>&nbsp;&nbsp;&nbsp;</td>
				  <td><ol>
				  <li>If using Microsoft Explorer, select "Tools" on the main tool bar.</li>
				  <li>Select "Internet Options" and then select the "Advanced" tab (usually the last tab).</li>
				  <li>Scroll down to the Security section and make sure the second box "Allow active content to run in files on My Computer" is checked. Say Ok.</li>
				  <li>Be sure to navigate using the "OK" back to the browser function.</li>
				  </ol></td>
		        </tr>
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
		          <td colspan=2>
		            Note: The tool bar and selections would be different on other browsers or different (older) releases of even Internet Explorer.
		          </td>
		        </tr>
				<tr>
		          <td colspan=2>
		            <img src="/<msp:webapp/>images/allowPopups.jpg">
		          </td>
		        </tr>
		       </table>
		<!-- END INSTRUCTION TABLE -->
      </td>
	</tr>
</table>
<!-- END MAIN TABLE -->
</body>
</html>
