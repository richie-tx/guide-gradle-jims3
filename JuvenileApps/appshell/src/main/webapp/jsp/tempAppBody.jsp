<!DOCTYPE HTML>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<HTML>
<HEAD>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<msp:nocache />
<STYLE>
td {font-family:arial;color:black;font-size:10pt}
a {font-family:arial;color:black;font-size:10pt}
h1 { font: bold 11pt Arial, Helvetica, sans-serif; color: black; margin-bottom: 0px; text-align: center }
</STYLE>
</HEAD>

<BODY>
<font size="14">
Launch Application for: 
</font>
<br><br><br>
<font size="18">
<b>
<%= session.getAttribute("appName") %>
</font>
</b>
</BODY>
</HTML>
