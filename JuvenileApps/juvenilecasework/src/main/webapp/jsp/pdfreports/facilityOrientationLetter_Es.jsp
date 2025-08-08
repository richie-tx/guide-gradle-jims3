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
<jsp:useBean id="reportInfo" class="ui.juvenilecase.casefile.FacilityOrientationLetterReportBean" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.FACILITY_ORIENTATION_LETTER_ES.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.FACILITY_ORIENTATION_LETTER_ES.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
		<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style>
body
	{	margin-left: .1in;
    	margin-right: .1in;
		margin-top: .1in; 
		margin-bottom: .1in;
		font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left 
		bottom: 4px;}
div.header
	{ 	
		font-size:14;
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
p.body-12-arial
	{	font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}   
td.times-font-12
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 100%;}
td.times-font-12
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 100%;}
td.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 100%;}
td.times-font-16
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 100%;}
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
p.times-font-10
	{font-size:11;
		font-family:"Times New Roman", Times, serif;
		line-height: 100%;}
p.constantia-font-12
	{font-size:12;
		font-family:"Constantia", Times, serif;
		line-height: 100%;}
p.constantia-font-14
	{font-size:14;
		font-family:"Constantia", Times, serif;
		line-height: 100%;}

p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
p.text-margin-0px 
	{margin: 0em;}
p.bold 
	{font-weight: bold;}
p.text-margin-10px
	{margin: 10px;}
p.centered
	{text-align:center;}
p.leftAlign
	{text-align:left;}
nobr { white-space:nowrap; }
pre  { white-space:pre; }
</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				 <tr>
				 	<td align="center" width="100%" class="times-font-10" font-size="10">
						<b>A BALANCED APPROACH TO JUVENILE JUSTICE</b> 
					</td>
				 </tr>
				  <tr>
					 <td align="center" width="50%" colspan="4" border-top = ".9"></td>
				 </tr>
				</table>
			</div>
		</macro>		
	</macrolist>
</head>
<body footer="myfooter" footer-height="5mm">
<!--  Header information -->
<div class="header">
	<table width="100%" border-style="none">
			<tr>
				<td align="top-left" width='10%'><img src="images/HarrisCountySeal.jpg" width="65" height="65"/></td>
				<td width='90%'>
					<table width="100%" border-style="none">
						<tr>
							<td width="75%"  valign='bottom' class="times-font-14 leftAlign"><p text-align="left"><h1 font-size="12">HARRIS COUNTY</h1><br/>JUVENILE PROBATION DEPARTMENT</p></td>
							<td width="25%"  valign='bottom' class="times-font-14 leftAlign"><p font-size ='8' text-align="right">HENRY GONZALES<br/>Executive Director<br/>Chief Juvenile Probation Officer</p></td>
						</tr>
						<tr>
							<td align="left" colspan="8" border-top = ".9"> </td>
						</tr>
						<tr>
							<td width="70%"  valign="top"  align ="left" class="times-font-12"><p font-size ='12' text-align="left">RESIDENTIAL SERVICES<br/>1200 Congress ST., Houston, Tx 77002</p></td>
							<td width="30%"  valign="top" align ="left" class="times-font-12"><p font-size ='8' text-align="right">DR. DIANA QUINTANA<br/>Assistant Executive Director<br/><br/>MELISSA DEHOYOS-WATSON<br/>Deputy Director<br/><br/>KEITH V. BRANCH<br/>Assistant Deputy Director</p></td>
						</tr>
					</table>
				</td>
			</tr>
	</table>
	<div class="body">
	<table>
		<tr>
			<td>
			<p></p><p></p><p></p><p></p>
			<p valign="top" class="leftAlign times-font-10">Estimados <%=reportInfo.getContactName()%>,</p>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<td>
				<p></p><p></p>
				<p class="leftAlign times-font-10">
		  			Su hijo(a), <%=reportInfo.getJuvenileFullName()%> ha sido puesto en la custodia del Chief Probation Officer (CJPO). El Departamento de Harris County Juvenile Probation está aquí para ayudarles a comprender los programas y ubicaciones.
				</p>
			</td>
		</tr>
		<tr>
			<td> 
				<p></p>
				<p class="leftAlign times-font-10" font-size="11">Ud. está programada(o) para asistir una junta el <bean:write name="ScheduleNewEventForm" property="currentService.currentEvent.eventDateStr"/> para hacerlo saber lo que es de esperarse de su hijo(a).</p>
			</td>
		</tr>
		<tr>
			<td>
				<p></p>
				<p class="leftAlign times-font-10">Expertos estarán disponibles para contestarle cualquier pregunta(s) que tenga, como lo siguiente: 
					<ul marker-type="middle-dot">
						<li>
							<ul marker-type="middle-dot">
								<li>•	Asistirá mi hijo(a) a la escuela?</li>
								<li>•	Qué tal si mi hijo(a) requiere clases de educación especial?</li>
								<li>•	Que debo de esperar sobre el comportamiento de mi hijo(a) durante su colocamiento?</li>
								<li>•	Que programas ay disponible para mi hijo(a)?</li>
								<li>•	Que tenemos que hacer para la probatoria?</li>
								<li>•	Cuál es la función del oficial encargado de mi hijo(a)?</li>
							</ul>
						</li>
					</ul>
					</p>
			</td>
		</tr>
		<tr>
			<td>
				<p class="leftAlign times-font-10">Su asistencia es recomendada, esta junta  ayudará en los cambios que vienen.</p><p></p>
			</td>
		</tr>
		<tr>
			<td>
				<table colspan="2">
					<tr>
						<td>
							<p class="leftAlign times-font-10" width="100%"><b>Lugar:</b></p>
						</td>
						<td>
							<p class="leftAlign times-font-10" width="100%"><%=reportInfo.getLocation()%>.</p><p></p>
						</td>
					</tr>
					
					<tr>
						<td>
							<p class="leftAlign times-font-10" width="100%"><b>Horario:</b></p>
						</td>
						<td>
							<p class="leftAlign times-font-10" width="100%"><bean:write name="ScheduleNewEventForm" property="currentService.currentEvent.eventTime"/> - <%=reportInfo.getEndTime()%></p>
						</td>
					</tr>
					
					<tr>
						<td></td>
						<td>
							<p class="leftAlign times-font-10" width="100%">Por favor de llegar 10 minutos antes de la hora indicada.</p><p></p>
						</td>
					</tr>
					
					<tr>
						<td>
							<p class="leftAlign times-font-10" width="100%"><b>Fecha:</b></p>
						</td>
						<td>
							<p class="leftAlign times-font-10" width="100%"><%=reportInfo.getEventDateStr()%>.</p>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<p></p>
				<p class="leftAlign times-font-10">No habrá cuidado de niños/guardería. Es su responsabilidad de supervisar a sus niños.</p>
			</td>
		</tr>
		<tr>
			<td>
				<p></p>
				<p class="leftAlign times-font-10">Si tiene preguntas sobre esta junta, por favor comunicase con el encargado del caso de su hijo(a),<br/> <%=reportInfo.getOfficerName()%> <%=reportInfo.getOfficerPhone()%>.</p>
			</td>
		</tr>
	</table>
	</div>
</div>
</body>
</pdf>
