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
<jsp:useBean id="reportInfo" class="ui.juvenilecase.casefile.CasefileClosingReportBean" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.MJC_CLOSING_LETTER_EN.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.MJC_CLOSING_LETTER_EN.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
		<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style>
a{  
        color: blue;  
}  

body
	{	margin-left: .1in;
    	margin-right: .1in;
		margin-top: .1in; 
		margin-bottom: .1in;
		font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left 
		bottom: 4px;}
div.body
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.footer
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;}
div.header
	{ 	font-size:14;
		font-family:"Arial", Helvetica, sans-serif;
		line-height: 100%;}
p.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.body-12-arial
	{	font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.bold 
	{font-weight: bold;}
p.centered
	{text-align:center;}
p.text-margin-0px 
	{margin: 0em;}
p.text-margin-10px
	{margin: 10px;}
span.underline
	{	border-bottom:1px solid #555;
		}
table.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
table.margin-top-0px
	{	margin-top: 0px;}
table.margin-top-5px
	{	margin-top: 5px;}
table.margin-top-10px
	{	margin-top: 10px;}
table.margin-top-20px
	{	margin-top: 20px;}
table.margin-left-12px
	{	margin-left: 12px;}
table.indent-15px
	{	text-indent:15px;}
td.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
td.helvetica-font-8
	{font-size:8;
		font-family: Arial, Helvetica, sans-serif;
		text-align:left;}
td.helvetica-font-10
	{font-size:10;
		font-family: Arial, Helvetica, sans-serif;
		text-align:left;}
td.helvetica-font-12
	{font-size:12;
		font-family: Arial, Helvetica, sans-serif;
		text-align:left;}
td.helvetica-font-14
	{font-size:14;
		font-family: Arial, Helvetica, sans-serif;
		text-align:left;}
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				 <tr>
				 	<td align="center" width="100%">
						
					</td>
				 </tr>
				</table>
			</div>
		</macro>		
	</macrolist>
</head>
<body size="A4" footer="myfooter" footer-height="5mm">
<!--  Header information -->
<div class="header">
	<table width="100%" border-style="none" align="center" cellpadding="0">
		<tr width="100%">
			<td width="10%" valign="bottom">
				<p class="centered">
					<img height="45" width="45" src="images/HarrisCountySeal.jpg"/>
				</p>
			</td>
			<td width="90%" border-bottom = "2" border-color="gray" class="helvetica-font-11">
				<p class="text-margin-10px bold ">HARRIS COUNTY<br/>JUVENILE PROBATION DEPARTMENT</p>
			</td>
		</tr>	
	</table>
	<table width="100%" border-style="none" align="center">
		<tr>
			<td width="10%" valign="bottom">
				<p class="centered"></p>
			</td>
			<logic:notEmpty name="reportInfo"  property="locationUnitName">
			<td class="helvetica-font-10" align="left" padding-top="2" padding-bottom="0" width="50%" >
				<p>
					<b><c:out value="${reportInfo.locationUnitName}"/></b><br/>
				   	<c:out value="${reportInfo.locationAddress.streetNumber}"/><c:out value="${reportInfo.locationAddress.streetNumSuffix}"/> <c:out value="${reportInfo.locationAddress.streetName}"/> <c:out value="${reportInfo.locationAddress.streetType}"/><br/>
				   	<c:out value="${reportInfo.locationAddress.city}"/>, <c:out value="${reportInfo.locationAddress.state}"/> <c:out value="${reportInfo.locationAddress.zipCode}"/>
				</p>
			</td>
			</logic:notEmpty>
			<td class="helvetica-font-8" align="right" padding-top="2" padding-bottom="0" width="40%">
				<p>
					<b><c:out value="${reportInfo.managerFirstName}"/> <c:out value="${reportInfo.managerLastName}"/></b><br/>
				   	Caseload Manager<br/>				   	
				   	Office: <c:out value="${reportInfo.managerPhone}"/>
				</p>
			</td>
		</tr>
	</table>	
</div>
<div class="body" page-break-before="avoid">
	<p class="arial-font-10" align="left" padding-top="0" padding-bottom="0">   
	<br/>
		${reportInfo.juvenile.fullNameFirst}<br/>
		c/o ${reportInfo.guardianName.fullNameFirst}<br/>	
		${reportInfo.guardianAddress.streetAddress}<br/>
   		${reportInfo.guardianAddress.cityStateZip}<br/><br/>
   		Supervision#: ${reportInfo.casefileId}<br/><br/>
   		Date: <u>${reportInfo.currentDate}</u>
   		<p>
  		${reportInfo.juvenile.fullNameFirst}, this letter is to acknowledge that you have completed your period of probation
  		supervision or deferred adjudication assigned to you under supervision of the Harris County Juvenile 
  		Probation Department.  Your case file with Harris County Juvenile Probation is now closed.
  		</p>
  		<p>
  		Generally speaking, your juvenile court records are confidential; however, this rule does not apply to 
  		everyone or all situations.  Keep in mind that if a youth has been involved with the police, you have 
  		obtained a juvenile record that is permanent in the State of Texas and the FBI.   Certain agencies and 
  		entities can obtain access to these records, which can affect you in the future.
		</p>
  		<p>
  		The State of Texas has laws that appear within the Texas Family Code (i.e. Subchapter A, Chapter 58) that 
  		explain the confidentiality of juvenile court records and to whom it applies. Subchapter C-1, Chapter 58 
  		of the Texas Family Code also provides the procedures for Sealing of Juvenile Records. 
  		</p> 
	  	<p>
	  	Certain juvenile records may be automatically sealed without an application to the court once you turn 
	  	19 years old as well as other qualifying factors.  You also may be eligible to apply for a <u>no cost</u> sealing of 
	  	your juvenile records after one year has elapsed since your final discharge from probation, or when you 
	  	turn 17 years old; whichever occurs first. To determine whether you are eligible for these free services to 
	  	seal your juvenile records, contact:
	  	</p>
	  	<p>
  		<b>•		For sealing misdemeanor offenses</b>: Houston Volunteer Lawyers -- largest provider of pro bono 
  		    		legal services in Harris County. Please fill out the intake form 
  		    		at: <a> https://www.makejusticehappen.org/</a> or call: 713-228-0735
		</p>
		<p>
  		<b>•		For sealing felony offenses</b>: The University of Houston Law Center/Juvenile and Children's 
  		    		Advocacy Project Juvenile Record Sealing Program. Please fill out the intake form at: 
  		    		<a>info@jcaptexas.org</a>, or call: Emanuele DiStefano at 713-743-1132 or Katya Dow at 713-743-1011. 
  		    		For more information, visit: <a>https://www.jcaptexas.org/</a>
		</p>
		<p>
	  	It is important that the Harris County Juvenile Probation Department maintain accurate contact 
	  	information for you up until you reach age twenty (20). Please contact us at 713-222-4100 to provide any 
	  	updates on your mailing address and phone number as they occur.
	  	</p>
		<br/><br/>
		<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<c:out value="${reportInfo.officerFirstName}"/> <c:out value="${reportInfo.officerLastName}"/>
		<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		Juvenile Probation Officer		
	  	
</p>
</div>

</body>
</pdf>
