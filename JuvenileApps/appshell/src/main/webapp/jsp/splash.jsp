<!DOCTYPE HTML>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<HTML>
<HEAD>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<msp:nocache />
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/foundation.css"/>
<script>
function showDate() {
	var date = new Date();
	var day = date.getDate();
	if (day < 10) day = "0"+day;
	var month = date.getMonth();

	if (month == 0) month = "Jan";
	if (month == 1) month = "Feb";
	if (month == 2) month = "Mar";
	if (month == 3) month = "Apr";
	if (month == 4) month = "May";
	if (month == 5) month = "Jun";
	if (month == 6) month = "Jul";
	if (month == 7) month = "Aug";
	if (month == 8) month = "Sep";
	if (month == 9) month = "Oct";
	if (month == 10) month = "Nov";
	if (month == 11) month = "Dec";

	var year = date.getFullYear();
	var hour = date.getHours();
	if (hour < 10) hour = "0"+hour;
	var minute = date.getMinutes();
	if (minute < 10) minute = "0"+minute;
	var second = date.getSeconds();
	if (second < 10) second = "0"+second;
	dateDiv.innerText = day+"-"+month+"-"+year+"   "+hour+":"+minute+":"+second;
}
var dateDiv = null;
</script>
</HEAD>
<BODY BACKGROUND="/<msp:webapp/>images/borders/TitleBG.gif" CLASS="titlebar" ONLOAD="dateDiv = document.all['date']; setInterval(showDate, 1000);">

<DIV STYLE="Position : Absolute ; Left : 7px ; Top : 1px ; Width : 600px ; Height : 17px">
<TABLE BORDER=0><TR><TD NOWRAP>
<IMG SRC="/<msp:webapp/>images/logos/Centerlinx@106x22pix.jpg" Width="106" Height="22" BORDER="0">
</TD>

<TD NOWRAP>
<A class="label">User:</A> <A class="value">"root.firstName"&nbsp;"root.primaryName"</A>
</TD>

<TD NOWRAP>
<A class="label">Date:</A><a class="value" ID="date"></a>
</TD></TR></TABLE>
</DIV>
</HTML>
