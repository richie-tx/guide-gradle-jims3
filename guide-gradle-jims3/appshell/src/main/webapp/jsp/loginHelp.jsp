<!DOCTYPE HTML>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<html>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<title>Login Help - loginHelp.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/appshell.css" />
<msp:nocache />
</head>

<body>

<table>	
	<tr>
		<td align="center" class="header">JIMS2 Log In Help</td>
	</tr>	
</table>

<br>
<table border=1 width='98%' cellspacing=0 cellpadding=2 class="borderTableBlue">
	<tr>
		<td>
			<table border=0 width='100%'>
				<tr>
					<td class="bodyText">Your JIMS user ID was provided by your department and/or assigned to you during a JIMS training class.</td>
				</tr>
				<tr>
					<td class="bodyText">Your password is set to an initial default, and you must change it the first time you log on and whenever you receive the message “Password Expired.”</td>
				</tr>
			</table>
    </td>
  </tr>	
</table>

<br>
<br>
<table>
	<tr>
		<td align="center">
			<input type="button" value="Close This Window" name="closer" onclick="javascript:window.close();"/>
		</td>
	</tr>	
</table>

</body>
</html>
