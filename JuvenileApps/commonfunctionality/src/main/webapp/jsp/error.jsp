<!DOCTYPE HTML>
<!--NOTE THIS FILE SHOULD ONLY BE CHANGED IN THE APP/SRC/UI/COMMON FOLDER -->

<!--MODIFICATIONS -->
<!-- LDeen	03/29/04	Create JSP -->
<!-- LDeen	08/11/05	Revise JSP -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading"/> - error.jsp</title>
<script>
function togglePageItem(what, format){
	 var elemItem=document.getElementById(what);
	 if(elemItem!=null){
		if(elemItem.className=='hidden'){
			if(format=="row"){
				elemItem.className='visibleTR';
			}else if(format=="table"){
				elemItem.className='visibleTable';
			}else if(format=="inline"){
				elemItem.className='visibleInline';
			}else {
				elemItem.className='visible';
			}
			
		}
		else{
			elemItem.className='hidden';
		}
	 }
}
</script>
</head>

<!--BEGIN BODY TAG-->
<body>

<!-- BEGIN HEADING TABLE -->
<table width=100%>
  <tr>
    <td align="center" class="header">Error Notice </td>
  </tr>
</table>
<!-- END HEADING TABLE -->
<br>
<table align="center" width="98%" border="0">
   <tr>
     <td align="center">A system or application error has occurred. 
     <p>Please contact technical support so that the problem can be diagnosed and it can be determined what can be done to help you complete your work. </p></td>
   </tr>		
</table> 
<br>
<!-- BEGIN INSTRUCTION TABLE -->
<table align="center" width="98%" border="0" cellpadding=1 cellspacing=0>
	<tr>
	  <td width=48% align=center valign=top>
		<table>
			<tr><td style="font-weight:bold; text-align: center;">Weekdays:</td></tr>
			<tr><td align="center">Please contact JPD Data Corrections by:</td></tr>
			<tr><td align="center"><a href="mailto:Data.Corrections@hcjpd.hctx.net">Data.Corrections@hcjpd.hctx.net</a></td></tr>
		</table>
	  </td>
	  <td width=48% align=center valign=top>	    
		<table>
			<tr><td style="font-weight:bold; text-align: center;" >Evenings and Weekends:</td></tr>
			<tr><td align="center">Please contact JPD Data Corrections & US Help Desk by:</td></tr>
			<tr><td align="center"><a href="mailto:Data.Corrections@hcjpd.hctx.net">Data.Corrections@hcjpd.hctx.net</a></td></tr>
			<tr><td align="center"><a href="mailto:helpdesk@us.hctx.net">helpdesk@us.hctx.net</a></td></tr>
			
		</table>
	</td>
			
	</tr>
	<tr>
		<td colspan="3">When reporting the error, please provide the information below.</td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE -->
<br>
<!-- BEGIN INFORMATION TABLE -->
<table align="center" width="80%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width=98% align=center valign=top>
			<table class=borderTableBlue cellpadding=4 cellspacing=0 width=98%>
				<tr class=detailHead>
					<!--  <td class=detailHead ondblclick="togglePageItem('detailStackTrace', 'table')">Error Information</td>-->
					<td class=detailHead>Error Information</td>
				</tr>
				<tr>
					<td align=center>
					<!--error table start-->
						<table border=0 cellspacing=1 cellpadding=2 width=100%>
							<tr>
								<td class=formDeLabel width=1% nowrap >Error Message</td>
								<td class=formDe colspan=3><bean:write name="errorEvent" property="message" /></td>
								
							</tr>
							<tr>
								<td class=formDeLabel width=1%>Requested URL</td>
								<td class=formDe colspan="3"><bean:write name="errorEvent" property="requestedUrl" /></td>
							</tr>							
							<tr>
								<td class=formDeLabel width=1%>Date</td>
								<td class=formDe><bean:write name="errorEvent" property="dateTimeStamp" format="MM/dd/yyyy" /></td>
								<td class=formDeLabel width=1% nowrap>Time</td>
								<td class=formDe><bean:write name="errorEvent" property="dateTimeStamp" format="HH:mm:ss" /></td>
							</tr>
							<tr>
								<td class=formDeLabel width=1%>Error - Log ID</td>
								<td class=formDe><bean:write name="errorEvent" property="errorLogId" /></td>
								<td class=formDeLabel width=1% nowrap>User ID</td>
								<td class=formDe><bean:write name="errorEvent" property="userId" /></td>
							</tr>
						</table>
						<!--error table end-->
						<!--stack trace table begin-->
						<table id="detailStackTrace" width="80%" border="1" align="center">
							<tr>
								<td class=formDeLabel width=1% nowrap>Stack Trace of Error Event</td>
							</tr>
							<tr>
								<td class=formDe><bean:write name="errorEvent" property="stackTrace" filter="false"/></td>
							</tr>
						</table>
						<!--stack trace table end-->
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<br>
<br>
<table width="100%">
	<tr>
		<td align="center">We apologize for the inconvenience.</td>
	</tr>
</table>
<br/>
<!--  LDeen commented out and moved up per Mike G on 6/24/10
<table id="detailStackTrace"  class="hidden" width="80%" border="1" align="center">
<tr>
	<td class=formDeLabel width=1% nowrap>Stack Trace of Error Event</td>
</tr>
<tr>
	<td class=formDe><bean:write name="errorEvent" property="stackTrace" filter="false"/></td>
</tr>
</table>-->

</body>
</html>