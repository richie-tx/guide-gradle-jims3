<!DOCTYPE HTML>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<html>
<head>
	<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
	<title>JIMS2 Login</title>
	<style>
	a {
		font-family: arial;
		color: black;
		font-size: 11pt
	}
	
	.small {
		font-size: 9pt;
		text-align: right;
		font-style: italic;
		color: black;
		text-decoration: none
	}
	
	table {
		
	}
	
	table.box {
		font: italic 9pt Arial, Helvetica, sans-serif;
		border-style: groove;
		border-right-width: 2px;
		border-bottom-width: 2px;
		border-top-width: 2px;
		border-left-width: 2px;
		margin: 0ex 0ex 0ex 0ex
	}
	</style>

	<script language="JavaScript1.2" src="/<msp:webapp/>js/OpenHelper.js"></script>
	<script>
		var ie4 = (document.all)? true:false
		if(!(ie4))
			{
			alert("You are using the wrong browser!\n JIMS2 can only be viewed using Internet Explorer.");
			}
		
		/*function checkSize(){
		if ((screen.width < 1024) && (screen.height < 768))
		    {
			alert("The defualt resolution for JIMS2 is 1024 X 768.\n Your current resolution is "+screen.width+" X "+screen.height+" please change you settings to at least 1024 X 768.\n If you need assistance please contact Information Technology");
		    }
			this.status="Welcome to JIMS2"
		}*/
		if (parent.document != self.document) {
		  parent.parent.parent.parent.parent.location = self.location;
		}
	</script>
	<script>
		if (parent.document != self.document) {
		  parent.parent.parent.parent.parent.location = self.location;
		}
	</script>
	<script SRC="/<msp:webapp/>js/login.js"></script>
	<script>//color"</script>
	<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/appshell.css" />
	<msp:nocache />
</head>

<body topmargin=15 leftmargin=15 marginheight=0 marginwidth=0 bgcolor="WHITE" >

<html:form action="/submitLogin" focus="userID"	onsubmit="return validate()">

	<table border=0 width="100%" height=95% cellpadding=0 cellspacing=0>
		<tr>
			<td align="center" valign="middle" height="246">
				<table width="954" border=0" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<img src="/<msp:webapp/>images/logos/CenterlinxLogin_01.jpg" width="960" height="280">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr><td height="45"/></tr>
		<tr>
			<td align="center" class="errorAlert" height="20" valign="center"><html:errors/></td>
		</tr>		
		<tr>
			<td align="center" valign="top">
			<table width="350" height="175" class="box">
				<tr>
					<td align=center>
					<table>
						<tr>
							<td><A>Username:</A></td>
						</tr>
						<tr>
							<td><input type="text" name="userID"></td>
						</tr>
						<tr>
							<td><A>Password:</A></td>
						</tr>
						<tr>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="Login">
							</td>
						</tr>
					</table>
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" class="small" onClick="openTheWindow('Helplogin')">
					Login Help </A></TD>
				</TR>
			</TABLE>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html>
