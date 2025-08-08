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
<jsp:useBean id="form" class="ui.juvenilecase.schedulecalendarevent.to.AppointmentLetterBean" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.APPOINTMENT_LETTER.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.APPOINTMENT_LETTER.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
		<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style>
body
	{	margin-left: .0in;
    	margin-right: .0in;
		margin-top: .0in; 
		margin-bottom: .1in;
		font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
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
p.body-12-arial
	{	font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
td.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}



td.times-font-10
	{font-size:10;
		font-family:"Times New Roman", Times, serif;		
		line-height: 80%;}
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
td.borderBottom
{
	border-bottom:2pt solid gray;
}

span.underline
	{	border-bottom:1px solid #555;}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.tahoma-font-8
	{font-size:8;
		font-family:"Tahoma", Helvetica, sans-serif;}
p.tahoma-font-6
	{font-size:6;
		font-family:"Tahoma", Helvetica, sans-serif;}
p.arial-font-10
	{font-size:10;
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
p.line-space-125
	{line-height: 125%;}

p.line-space-150
	{line-height: 150%;}
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
table.title
	{
		
	}

</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer" align="center">
				<img margin-top="0" width="500" src="images/JuvenileJustice_Footer.gif"/>
			</div>
		</macro>		
	</macrolist>
</head>
<body footer="myfooter" footer-height="5mm">
<!--  Header information -->

<div class="header">
	<table width="100%" border-style="none">
		<tr>
			<td align="top-left" width='10%'><img margin-top="0" width="65" height="65" src="images/HarrisCountySeal.jpg"/></td>
			<td width='90%'>
				<table width="100%" border-style="none">
				<tr>
					<td width="70%"  valign='bottom' class="times-font-14 leftAlign"><p text-align="left"><h1 font-size="12">HARRIS COUNTY</h1><br/>JUVENILE PROBATION DEPARTMENT</p></td>
					<td width="30%"  valign='bottom' class="times-font-10" align="right"><p font-size ='8' text-align="right"><b>HENRY GONZALES</b><br/>Executive Director<br/>Chief Juvenile Probation Officer</p></td>				
				</tr>	
				<tr>
					<td align="left" colspan="8" border-top = ".9"> </td>
				</tr>
				<c:if test="${form.letterType=='MISDEMEANOR'}">	
					<tr>
						<td width="70%"  valign="top"  align ="left" class="times-font-10"><p font-size ='10' text-align="left"><b>Intake/Court Services Division</b><br/>1200 Congress Ave, Houston, TX 77002</p></td>
						<td width="30%"  valign="top" align ="right" class="times-font-10"><p font-size ='8' text-align="right"><b>LaQuinthia Williams</b><br/>Administrator<br/></p></td>
					</tr>
				</c:if>	
				<c:if test="${form.letterType=='FELONY'}">	
					<tr>
						<td width="70%"  valign="top"  align ="left" class="times-font-10"><p font-size ='10' text-align="left">1200 Congress Ave Houston, TX 77002 * (713)222-4100</p></td>
						<td width="30%"  valign="top" align ="right" class="times-font-10"><p font-size ='8' text-align="right"><b>DR. DIANA QUINTANA</b><br/>Assistant Excecutive Director</p></td>
					</tr>
				</c:if>
			
				<c:if test="${form.letterType=='FELONY'}">
					<tr>
						<td width="70%"  valign="top"  align ="left" class="times-font-10"></td>
						<td width="30%"  valign="top" align ="right" class="times-font-10">						
							<p font-size ='8' text-align="right"><b>Steve Willing</b><br/>Deputy Director, Intake/Court Services</p>									
						</td>
					</tr>
				</c:if>
				</table>
			
			</td>
			
		</tr>	
	</table>
</div>

<br></br>
<div class="body">
	<table class="arial-font-10" width="90%" border-style="none">
		<tr align="center">
			<td align="top-left" width='10%'></td>
			<fmt:formatDate value="<%=DateUtil.getCurrentDate()%>" pattern="MM/dd/yyyy" var="formattedDate" />
						
			<td><p class="times-font-10"  align="left"><b>DATE: </b><c:out value="${formattedDate}"/></p></td>						
		</tr>
		<tr align="center">
			<td align="top-left" width='10%'></td>
			<td><p class="times-font-10"  align="left"><b>RE: </b><%=form.getJuvenileFullName()%></p></td>					
		</tr>
		<tr align="center">
			<td align="top-left" width='10%'></td>
			<td><p class="times-font-10"  align="left"><b>PETITION NUMBER: </b><%=form.getPetitionNumber()%></p></td>					
		</tr>
	</table>
	<br></br>
	<table  class="arial-font-10" width="90%" border-style="none">
		<tr align="center">
			<td align="top-left" width='10%'></td>
			<td><p class="times-font-10"  align="left">Dear Parent/Guardian</p></td>									
		</tr>
	</table>
	<br></br>
	
	<table  class="arial-font-10" width="90%" border-style="none">
		
		<c:if test="${form.letterType=='FELONY'}">
			<tr align="center">
				<td align="top-left" width='22%'></td>
				<td ><p class="times-font-10"  align="justify">
				<fmt:formatDate value="${form.courtDate}" pattern="MM/dd/yyyy" var="formattedDate" />
				Your child is scheduled to appear in the Harris County District Courts alleging delinquent conduct.</p></td>
			</tr>
			<tr align="center"><td></td></tr>
			<tr align="center">
				<td align="top-left" width='22%'></td>
				<td><p class="times-font-10"  align="justify">Information related to the youth and family is needed. This information will be reported to the Court and will help expedite the matter before the Court.</p></td>
			</tr>
			<tr align="center"><td></td></tr>
			<tr align="center">
				<td align="top-left" width='20%'></td>
				<td><p class="times-font-10"  align="justify">Please report to the Juvenile Justice Center located at 1200 Congress 4th floor on <bean:write name="scheduleNewEventForm" property="currentService.currentEvent.eventDateStr"/> at <bean:write name="scheduleNewEventForm" property="currentService.currentEvent.eventTime"/> 
				for an interview. 
				Both parent/guardian and child must be present for the interview.</p></td>
			</tr>
			<tr align="center"><td></td></tr>
			<tr align="center">
				<td align="top-left" width='20%'></td>
				<td><p class="times-font-10"  align="justify">The assigned Officer’s name and number is <bean:write name="scheduleNewEventForm" property="officerName"/>@ <bean:write name="scheduleNewEventForm" property="officerPhone"/>. 
				Please contact me immediately upon receipt of this letter to confirm the appointment.  
				My office hours are <bean:write name="scheduleNewEventForm" property="officerHours"/>.</p></td>
			</tr>
			<tr align="center"><td></td></tr>
			<tr align="center">
				<td align="top-left" width='20%'></td>
				<td><p class="times-font-10"  align="justify">State Law requires that every juvenile appearing before the District Court <b>must</b> have an attorney.  
				The state may assist families who meet certain financial criteria. 
				Eligibility will be determined based on the Federal Poverty Guidelines.  </p></td>
			</tr>
			<tr align="center"><td></td></tr>
			<tr align="center">
				<td align="top-left" width='20%'></td>
				<td><p class="times-font-10"  align="justify">Please bring a copy of the child’s most recent report card, birth certificate, 
				social security card, and immunization records to the interview.</p></td>
			</tr>		
		
		</c:if>
		<c:if test="${form.letterType=='MISDEMEANOR'}">	
			<tr align="center">
				<td align="justify" width='30%'></td>
				<td ><p class="times-font-10"  align="justify">
				<fmt:formatDate value="${form.courtDate}" pattern="MM/dd/yyyy" var="formattedDate" />
				Your child is scheduled to appear in the <%=form.getCourtNumber()%>th Harris County District Court 
				on <c:out value="${formattedDate}"/> at 9:00 a.m. alleging delinquent conduct.</p></td>
			</tr>
				<tr align="center"><td></td></tr>
			<tr align="center">
				<td align="justify" width='22%'></td>
				<td width='90%'><p class="times-font-10"  align="justify">The Court requires that the parent/guardian contact Juvenile Probation prior to their Court appearance. 
				Information related to the youth and family is needed. This information will be reported to the Court and will help expedite the matter before the Court. 
				Failure to contact the assigned Officer upon receipt of this letter could prolong your child’s Court activity, causing unnecessary resets.</p></td>
			</tr>
				<tr align="center"><td></td></tr>
			<tr align="center">
				<td align="justify" width='20%'></td>
				<td width='90%'><p class="times-font-10"  align="justify">The assigned Officer’s name and number are <bean:write name="scheduleNewEventForm" property="officerName"/>@ <bean:write name="scheduleNewEventForm" property="officerPhone"/>. 
				Please contact me immediately upon receipt of this letter.  My office hours are <bean:write name="scheduleNewEventForm" property="officerHours"/>.</p></td>
			</tr>
				<tr align="center"><td></td></tr>
			<tr align="center">
				<td align="justify" width='20%'></td>
				<td width='90%'><p class="times-font-10"  align="justify">State Law requires that every juvenile appearing before the District Court <b>must</b> have an attorney.  
				The state may assist families who meet certain financial criteria. Eligibility will be determined based on the Federal Poverty Guidelines. </p></td>
			</tr>
				<tr align="center"><td></td></tr>
			<tr align="center">
				<td align="top-left" width='20%'></td>
				<td width='90%'><p class="times-font-10"  align="justify">The parent shall provide a copy of the child’s most recent report card, birth certificate, social security card, and immunization records.
				 These records can be faxed to <bean:write name="scheduleNewEventForm" property="officerFax"/>.</p></td>
			</tr>
				<tr align="center"><td></td></tr>
		</c:if>
			<tr align="center">
				<td align="justify" width='20%'></td>
				<td><p class="times-font-10"  align="justify">If you do not speak English, please notify this Officer in advance so that an interpreter can be present.<br/>
				<u><b>SI USTED NO HABLA INGLES, POR FAVOR DE AVISARLE AL OFICIAL EN ADELANTADO. 
				FAVOR DE LLAMAR AL <bean:write name="scheduleNewEventForm" property="officerPhone"/>. </b></u><br/>
				</p></td>
				
			</tr>
			<tr align="center"><td></td></tr>
			<tr align="center"><td></td></tr>
			<tr>
				<td align="top-left" width='20%'></td>
				<td align="right">
					<table>
					<tr>
						<td>
							<p class="arial-font-10 text-margin-bottom-40 leftAlign">Sincerely, </p>
							
							<p class="line-space-150 arial-font-10 text-margin-bottom-40">___________________________ <br/><bean:write name="scheduleNewEventForm" property="officerName"/><br/>
							Juvenile Probation Officer <br/> Office <bean:write name="scheduleNewEventForm" property="officerPhone"/>
							</p>
						</td>
					</tr>	
					</table>
				</td>
			</tr>
	</table>
	
</div>
</body>
</pdf>