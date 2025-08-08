<?xml version="1.0"?>
<!DOCTYPE pdf PUBLIC "-//big.faceless.org//report" "report-1.1.dtd">
<%@page language="java" contentType="text/xml; charset=UTF-8" %>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="mojo.km.utilities.DateUtil"%>
<%@page import="ui.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="form" class="ui.juvenilecase.schedulecalendarevent.to.AdjudicationNotificationTO" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.ADJUDICATION_NOTIFICATION.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.ADJUDICATION_NOTIFICATION.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
	<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style>
body
	{	margin-left: -.1in;
    	margin-right: -.1in;
		margin-top: -.1in; 
		margin-bottom: -.18in;
		font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
div.header
	{ 	
		font-size:12;
		font-family:"Arial", Helvetica, sans-serif;
		line-height: 80%;}
div.body
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.footer
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;}
img.display
    {	display:inline;
		position:bottom;
   		margin:0px;
   		border:1px solid #ffffff;}
span.underline
	{	border-bottom:1px solid #555;}
span.underline-space
	{	border-bottom:1px solid #555;
		line-height: 125%;}
span.underline-space-red
	{	border-bottom:1px solid #F00;
		line-height: 125%;}
table.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
table.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;}
table.line-space-125
	{line-height: 125%;}
table.margin-top-0px
	{	margin-top: 0px;}
table.margin-top-5px
	{	margin-top: 5px;}
table.margin-top-10px
	{	margin-top: 10px;}
table.margin-top-20px
	{	margin-top: 20px;}
table.margin-bottom-5
	{	margin-bottom: 5;}
table.margin-bottom-10
	{	margin-bottom: 10;}
table.margin-bottom-15
	{	margin-bottom: 15;}
table.margin-bottom-20
	{	margin-bottom: 20;}
table.margin-bottom-20px
	{	margin-bottom: 20px;}
table.margin-left-12px
	{	margin-left: 12px;}
table.indent-15px
	{	text-indent:15px;}
.margin-bottom-1
	{	margin-bottom: 1px;}
.margin-bottom-3
	{	margin-bottom: 3px;}
.margin-bottom-6
	{	margin-bottom: 6px;}
.margin-bottom-10
	{	margin-bottom: 10px;}
.margin-top-6
	{	margin-top: 6px;}
p.body-12-arial
	{	font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
td.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
td.helvetica-font-15
	{font-size:15;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
td.border-bottom
{
	border-bottom:1pt solid black;
}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-8
	{font-size:8;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.arial-font-11
	{font-size:11;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.text-margin-0px 
	{margin: 0em;}
p.bold 
	{font-weight: bold;}
p.line-space-115
	{line-height: 115%;}
p.line-space-125
	{line-height: 125%;}
p.text-margin-10px
	{margin: 10px;}
p.text-margin-top-6
	{margin-top: 6px;}
p.text-margin-top-10
	{margin-top: 10px;}
p.text-margin-top-25
	{margin-top: 25px;}
p.text-margin-bottom-25
	{margin-bottom: 25px;}
p.text-margin-bottom-40
	{margin-bottom: 40px;}
p.centered
	{text-align:center;}
p.leftAlign
	{text-align:left;}
p.rightAlign
	{text-align:right;}

</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				 <tr>
				 	<td align="center" width="100%">
						Page :<pagenumber/> of <totalpages/>
					</td>
				 </tr>
				</table>
			</div>
		</macro>		
	</macrolist>
</head>

<body footer="myfooter">

	<table class="margin-bottom-10" border-style="none" border-width="0" width="100%">
		<tr width="100%">
			<td align="left" vertical-align="bottom" padding-bottom="0" colspan="2">
					<img height="70" width="550" src="images/hcjpdfsd.jpg"/>
			</td>
		</tr>
	</table>

	<p class="arial-font-12 bold line-space-125 margin-bottom-6" align="center" color="red">
	<span class="underline-space-red">
		PERSONAL AND CONFIDENTIAL!!!!!!
	</span>	
	</p>
	
	<table class="arial-font-10" border-style="none" border-width="0" width="100%" padding-left="6px" padding-bottom="6px">	
		<tr class="margin-top-6 margin-bottom-6">
			<td vertical-align="top" width="15%">Date:</td>
			<td vertical-align="top" align="left" width="82%">
				<fmt:formatDate value="${form.currentDate}" pattern="MM/dd/yyyy" var="formattedDate" />
				${formattedDate}
			</td>
		</tr>
		<tr></tr>
		<tr class="margin-bottom-6">
			<td vertical-align="top" width="15%">To:</td>
			<td vertical-align="top" align="left" width="82%">Superintendent/Principal<br/>${form.schoolDistrictName}</td>
		</tr>
		<tr></tr>
		<tr  class="margin-bottom-6">
			<td vertical-align="top" width="15%">Address:</td>
			<td vertical-align="top" width="82%">${form.schoolName}<br/>
				${form.schoolStreet}<br/>
				${form.schoolCity}, ${form.schoolState} ${form.schoolZip}
			</td>
		</tr>
		<tr></tr>
		<tr class="margin-bottom-6">
			<td vertical-align="top" width="15%">Regarding:</td>
			<td vertical-align="top" width="82%">${form.juvenileFullName}<br/>DOB: ${form.birthDate}</td>
		</tr>
	</table>
	
	<table class="arial-font-10" border-style="none" border-width="0" width="100%" padding-left="6">	
		<tr>
			<td><p class="margin-bottom-6" align="left">
				Dear Sir/Madame:
			</p></td>
		</tr>
		<tr>
			<td><p class="margin-bottom-6" align="left">
				In accordance with Article 15.27 of the Texas Code of Criminal Procedure, I orally notified ${form.contactFirstName} 
				${form.contactLastName} on ${form.eventDateStr} at ${form.eventTime} that 
				the above-mentioned juvenile, believed to be transferring to or returning to your school or school district, or transferring to 
				or returning to another school within your district was adjudicated for the	criminal offense of
			</p></td>
		</tr>	
	</table>	
	
	<c:forEach var="offense" items="${form.offenseInvolvedWeaponList}" varStatus="loop">
		<table class="arial-font-10 margin-top-6 margin-bottom-1" border-style="none" border-width="0" width="100%" padding-left="8" padding-bottom="6">	
			<tr class="margin-bottom-6">
				<td colspan="4" align="left">
					${offense.offenseDescription} 
				</td>
	 		</tr>
			<tr class="margin-bottom-6"></tr>
			<tr class="margin-bottom-6">
				<td colspan="4" align="left">
					This adjudication involved the following conduct:
				</td>
			</tr>
			<tr class="margin-bottom-6">
				<td width="26%">
					Offense Code: <span class="underline-space">${offense.offenseCodeId}</span>
				</td>
				
				<td width="20%">
					<c:set var="catagoryBOOL" value="${offense.category == 'F1' || offense.category == 'F2' || offense.category == 'F3' || offense.category == 'JF' || offense.category == 'CF'}" scope="session"/>
					<c:if test="${catagoryBOOL}"> 
						<span class="underline-space">X</span> Felony
					</c:if>
					<c:if test="${!catagoryBOOL}"> 
						__Felony
					</c:if>			
				</td>
				<td width="20%">
					<c:if test="${catagoryBOOL}"> 
						__Misdemeanor
					</c:if>
					<c:if test="${!catagoryBOOL}"> 
						<span class="underline-space">X</span> Misdemeanor
					</c:if>				
				</td>
				<td width="31%">		
					Petition#:<span class="underline-space">${offense.petitionNumber}</span>
				</td>	
			</tr>						
			<tr class="margin-bottom-6"></tr>
			<tr class="margin-bottom-6">
				<td colspan="4" align="left">
					<span class="underline-space">Punishment Classification</span>
				</td>
			</tr>
		</table>
		<table class="arial-font-10" border-style="none" border-width="0" width="100%" padding-left="8" padding-bottom="6">	
			<tr class="margin-bottom-3">
				<td width="26%">
					Assaultive or Violent Behavior
				</td>
				<td width="9%">
					<c:if test="${offense.severitySubtype == 'V'}"> 
						<span class="underline-space">X</span> Yes
					</c:if>
					<c:if test="${offense.severitySubtype != 'V'}"> 
						__Yes
					</c:if> 
				</td>
				<td width="8%">
					<c:if test="${offense.severitySubtype == 'V'}"> 
						__No
					</c:if>
					<c:if test="${offense.severitySubtype != 'V'}"> 
						<span class="underline-space">X</span> No
					</c:if>
				</td>	
				<td width="54%"></td>
			</tr>
			<tr class="margin-bottom-3" ></tr>
			<!--  currentService fields may need to be added to offense entity -->
			<tr class="margin-bottom-3" >
				<td width="26%">
					Weapon Involved
				</td>
				<td width="9%">
					<c:if test="${offense.weaponInvolvedStr == 'Y'}"> 
						<span class="underline-space">X</span> Yes 
					</c:if>
					<c:if test="${offense.weaponInvolvedStr != 'Y'}"> 
						__Yes
					</c:if>
				</td>
				<td width="8%">
					<c:if test="${offense.weaponInvolvedStr != 'Y'}"> 
						<span class="underline-space">X</span> No 
					</c:if>
					<c:if test="${offense.weaponInvolvedStr == 'Y'}"> 
						__No
					</c:if>
				</td>
				<td width="54%"></td>
			</tr>
		</table>
		<table class="arial-font-10" border-style="none" border-width="0" width="100%" padding-left="8" padding-bottom="6">	
			<tr class="margin-bottom-6">
				<td width="20%" align="right">
					Type of Weapon:
				</td>
				<td width="6%"></td>
				<td width="10%">	
					<c:if test="${offense.typeOfWeaponId == 'FRM'}">
						<span class="underline-space">X</span> Firearm
					</c:if>
					<c:if test="${offense.typeOfWeaponId != 'FRM'}">
					___Firearm
					</c:if>
				</td>
				<td width="14%">
					<c:if test="${offense.typeOfWeaponId == 'ILK'}">
						<span class="underline-space">X</span> Illegal Knife
					</c:if>
					<c:if test="${offense.typeOfWeaponId != 'ILK'}">
					___Illegal Knife
					</c:if>
				</td>
				<td width="12%">
					<c:if test="${offense.typeOfWeaponId == 'EXP'}">
						<span class="underline-space">X</span> Explosive
					</c:if>
					<c:if test="${offense.typeOfWeaponId != 'EXP'}">	 
					___Explosive
					</c:if>
				</td>
				<td width="35%">
					<c:if test="${offense.typeOfWeaponId == 'CDD'}">
						<span class="underline-space">X</span> Chemical Dispensing Device
					</c:if>
					<c:if test="${offense.typeOfWeaponId != 'CDD'}"> 
					___Chemical Dispensing Device
					</c:if>
				</td>
			</tr>
			<tr class="margin-bottom-6">
				<td colspan="2" width="26%"></td>
				<td colspan="4" width="74%" align="left">
					<c:if test="${offense.typeOfWeaponId == 'OTH'}">
						<span class="underline-space">X</span> Other/Specify <span class="underline-space">${offense.typeOfWeaponDescription}</span>
					</c:if>
					<c:if test="${offense.typeOfWeaponId != 'OTH'}">
					___Other/Specify _______________________________________________________
					</c:if>
				</td>
			</tr>
		</table>
	</c:forEach>
	
	<table class="arial-font-10" border-style="none" border-width="0" width="97%" padding-left="8" padding-bottom="6">	
		<tr class="margin-bottom-6">
			<td width="26%">
				Sex Offender Registrant
			</td>
			<td width="9%">
				<c:if test="${form.sexOffenderRegistrantStr == 'Y'}"> 
					<span class="underline-space">X</span> Yes
				</c:if>
				<c:if test="${form.sexOffenderRegistrantStr != 'Y'}"> 
					__Yes
				</c:if>
			</td>
			<td width="8%">
				<c:if test="${form.sexOffenderRegistrantStr == 'N'}"> 
					<span class="underline-space">X</span> No
				</c:if>
				<c:if test="${form.sexOffenderRegistrantStr != 'N'}"> 
				__No
				</c:if>
			</td>
			<td width="54%">
				Restrictions/Other
				<c:if test="${form.sexOffenderRegistrantStr == 'Y'}">
					<span class="underline-space">${form.restrictionsOther}</span>
				</c:if>
				<c:if test="${form.sexOffenderRegistrantStr != 'Y'}">
				___________________________________
				</c:if>
			</td>
		</tr>
	</table>
	
	<table class="arial-font-10" border-style="none" border-width="0" width="100%" padding-left="6">	
		<tr class="margin-bottom-6">
			<td><p align="left" class="margin-bottom-10">
				Please be advised that Article 15.27 of the Texas Code of Criminal Procedure states that the information contained in this notice
				MAY NOT BE DISCLOSED except as specifically authorized by Article 15.27.  A person who intentionally violates this mandate may
				have their certification suspended or revoked.  In addition, an individual violating this mandate commits an offense, and may be 
				prosecuted.
			</p></td>
		</tr>
		<tr class="margin-bottom-6">
			<td><p align="left" class="margin-bottom-6">
				Pursuant to Section 37.017 of the Texas Education Code, this notification MAY NOT be attached to the permanent academic file
				of the student who is named in this notification.  The statute requires the school district to destroy the information contained in
				this report at the end of the academic year in which it was filed.
			</p></td>
		</tr>
	</table>
	
	<table class="arial-font-10 " border-style="none" border-width="0" width="100%" padding="0">	
		<tr>
			<c:if test="${form.officerPhone == null}">
			<td width="48%">___________________________________________</td>
			<td width="49%" align="right">___________________________________________</td>
			</c:if>
			<c:if test="${form.officerPhone != null}">
			<td width="48%">___________________________________________</td>
			<td width="49%" align="right"><span class="underline-space">${form.officerPhone}</span></td>
			</c:if>
		</tr>
		<tr>
			<td width="48%">Harris County Juvenile Probation Department</td>
			<td width="49%" align="right">Contact Number</td>
		</tr>
		<tr class="margin-bottom-6">
			<td width="48%">Administrator/Supervisor</td>
		</tr>
	</table>
		
	<table class="arial-font-10 margin-bottom-20" border-style="none" border-width="0" width="100%" padding="0">	
		<tr>
			<td width="48%">___________________________________________</td>
		</tr>
		<tr>
			<td width="48%">Harris County Juvenile Probation Department</td>
		</tr>
		<tr>
			<td width="48%">Official Designee</td>
		</tr>
	</table>
	
</body>
</pdf>
