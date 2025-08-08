<!DOCTYPE pdf PUBLIC "-//big.faceless.org//report" "report-1.1.dtd">
<%@ page language="java" contentType="text/xml; charset=UTF-8" %>
<%@page import="java.net.URLEncoder"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="mojo.km.utilities.DateUtil"%>
<%@ page import="ui.util.*"%>
<%@ page import="ui.common.StringUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<jsp:useBean id="reportInfo" class="ui.juvenilewarrant.ReportingBean" scope="session" />
<jsp:useBean id="juvenileWarrantForm" class="ui.juvenilewarrant.form.JuvenileWarrantForm" scope="session" />
<jsp:useBean id="reportPhoto" class="ui.common.Photo" scope="session" />
<pdf onload="pdf:ActualSize">
<head>
	<meta name="title" value="<%=PDFReport.GENERICWARRANT_REPORT_NAME.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.GENERICWARRANT_REPORT_NAME.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
	<meta name="base" value="file:c:/BFOImages/" /> 	
<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" src="/<msp:webapp/>css/bfo.css" />
<style>
span.underline
	{	border-bottom:1px solid #555;
		}
table.margin-top-8px
	{	margin-top: 8px;}
table.margin-top-12px
	{	margin-top: 12px;}
table.margin-left-12px
	{	margin-left: 12px;}
table.indent-15px
	{	text-indent:15px;}
body
	{	margin-left: .2in;
    	margin-right: .2in;
		margin-top: .3in; 
		margin-bottom: .3in;}
#page1 
	{	footer:myfooter; footer-height:0.1in;}
div.header
	{ 	font-size:12;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
div.photo
	{	font-size:10;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		padding:11;
		text-align:left;}
div.body
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.footer
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;}
</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%" border-style="none">
						<tr align="left">
							<td align="left">All service attempts and executions should be entered in the Harris Country Juvenile Warrant System using the aforementioned warrant number. </td>
						</tr>
				</table>
				<table width="100%">
				 <tr>
				 	<td align="left" width="50%">
						Page <pagenumber/> of <totalpages/>
					</td>
				 </tr>
				</table>
			</div>
		</macro>		
	</macrolist>
</head>
<body>
<!--<font name="times" size="12" style="normal"> -->
<!--  Header information -->
<div class="header">
<table width="100%" border-style="none">
		<tr align="center">
			<td align="center">Harris County</td>
		</tr>
		<tr align="center">
			<td align="center">Warrant Number: <%=reportInfo.getWarrantNum()%></td>
		</tr>
		<tr align="center">
				<td align="center"> <%=reportInfo.getWarrantType()%></td>
		</tr>
		<tr align="center">
				<td align="center">
					<%=StringUtil.isNotNull(DateUtil.dateToString(reportInfo.getWarrantActivationDate(), "MMM dd, yyyy"))%>
				</td>
		</tr>
</table>	
</div>
<div class="photo">
<table width="100%" border-style="none" class="margin-top-12px">
		<tr align="center">
			<td align="center" >
				<%	String printWarrantPhotoPath = (String)session.getAttribute("printWarrantPhotoPath");
					if(printWarrantPhotoPath != null){ // don't show photo if does not exist
						String encodedURL = response.encodeURL(printWarrantPhotoPath);					
						%>
						<img src="<%=encodedURL%>"  width="100" height="85"/>
						
				    <%}else{ %>
				    	<img border="1" src="images/photo_na.gif" width="100" height="85"/>
				    <%}%>
			</td>
		</tr>
</table>
</div>
<div class="body">
<!--  Juvenile name and info -->
<table width="100%" border-style="none">
		<tr>
			<td  align="left"><span class="underline"><%=reportInfo.getNameFirstMiddleLastSuffix()%></span> is actively wanted pursuant the <span class="underline"><%=reportInfo.getWarrantType()%></span> Warrant Number <span class="underline"><%=reportInfo.getWarrantNum()%></span>. The juvenile is:   
			</td>
		</tr>
</table>
<br/>
<!--  Race, sex, skin tone -->
<table width="100%" border-style="none">
		<tr>
			<td align="left" width="33%"><b>Race: </b>
				<%=StringUtil.isNotNull(reportInfo.getRace())%>
			</td>
			<td align="left" width="33%"><b>Sex: </b>
				<%=StringUtil.isNotNull(reportInfo.getSexId())%>
			</td>
			<td align="left" width="33%"><b>Skin Tone: </b>
				<%=StringUtil.isNotNull(reportInfo.getComplexionId())%>
			</td>
		</tr>
		<tr>
			<td align="left" width="33%"><b>Height: </b>
				<%=StringUtil.isNotNull(reportInfo.getHeight())%>
			</td>
			<td align="left" width="33%"><b>Weight: </b>
				<%=StringUtil.isNotNull(reportInfo.getWeight())%>
			</td>
			<td align="left" width="33%"><b>Age: </b>
				<%=StringUtil.isNotNull(reportInfo.getJuvenileAge())%>
			</td>
		</tr>
		<tr>
			<td align="left" width="33%"><b>Eyes: </b>
				<%=StringUtil.isNotNull(reportInfo.getEyeColor())%>
			</td>
			<td align="left" width="33%"><b>Hair: </b>
				<%=StringUtil.isNotNull(reportInfo.getHairColor())%>
			</td>
			<td align="left" width="33%"></td>
		</tr>
</table>	

<!--  Scars and Marks -->
<table width="100%" border-style="none" class="margin-top-8px">
		<tr>
			<td align="left"><b>Scars/Marks: </b>
				<%=StringUtil.isNotNull(reportInfo.getScars())%>
			</td>
		</tr>
		<tr>
			<td align="left"><b>Tattoos: </b>
				<%=StringUtil.isNotNull(reportInfo.getTattoos())%>
			</td>
		</tr>
</table>

<!--  Possible Addresses lead in -->
<table width="100%" border-style="none" class="margin-top-12px">
		<tr>
			<td align="left">The following addresses are considered possible locations: (list all associate addresses)</td>
		</tr>
</table>
<!--  Possible Addresses -->
<table width="100%" border-style="none" class="margin-top-12px margin-left-12px">
		<tr align="left">
			<td align="left">
				<%=StringUtil.isNotNull(reportInfo.getAssociateAddresses())%>
			</td>
		</tr>
</table>
<!--  Note -->
<% if(reportInfo.getCautionsSelected() != null && !reportInfo.getCautionsSelected().equals("")){  // only show if there are cautions selected %>
<table width="100%" border-style="none" class="margin-top-12px">
		<tr>
			<td align="left"><b>Please Note: </b>
				<%=StringUtil.isNotNull(reportInfo.getCautionsSelected())%>
			</td>
		</tr>
</table>
<% }%>
<!--  Other Caution Comments -->
<% if(reportInfo.getCautionComments() != null && !reportInfo.getCautionComments().equals("")){  // only show if there are Other Caution Comments%>
<table width="100%" border-style="none" class="margin-top-12px">
		<tr>
			<td align="left"><b>Other Caution Comments: </b>
				<%=StringUtil.isNotNull(reportInfo.getCautionComments())%>
			</td>
		</tr>
</table>
<% }%>
</div>
</body>
</pdf>
