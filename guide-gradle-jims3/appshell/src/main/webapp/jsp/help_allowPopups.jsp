<!DOCTYPE HTML>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<html>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>JIMS2 Login - help_allowPops.jsp</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/appshell.css" />
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
		              <li>Please follow these steps if using Microsoft Internet Explorer:</li>
					  </ul></td>
		        </tr>
		        <tr>
		          <td>&nbsp;&nbsp;&nbsp;</td>
				  <td><ol>
				  <li>Select "Tools" on the main tool bar.</li>
				  <li>Select "Internet Options" and then select the "Advanced" tab (usually the last tab).</li>
				  <li>Scroll down to the Security section and make sure the second box "Allow active content to run in files on My Computer" is checked. Select &quot;OK&quot; button.</li>
				  <li>Again, select "Tools" on the main tool bar.</li>
  				  <li>Select "Pop-up Blocker". (may not appear in older versions of IE)</li>
  				  		<ul>
  				  			<li>If "<b>Turn On Pop-up Blocker</b>" appears in the submenu, pop-up blocker is off.</li>
	  				  		<li>If "<b>Turn Off Pop-up Blocker</b>" appears in the submenu, highlight and click to turn off pop-up blocker.</li>
  				  		</ul>
  				  <li>Click outside menu to close "Tools" menu.</li>		
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
      
</body>
</html>
