<?xml version="1.0"?>
<!DOCTYPE pdf PUBLIC "-//big.faceless.org//report" "report-1.1.dtd">
<%@page language="java" contentType="text/xml; charset=UTF-8" %>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="mojo.km.utilities.DateUtil"%>
<%@page import="ui.common.StringUtil"%>
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
<jsp:useBean id="reportInfo" class="ui.juvenilecase.casefile.form.ClientSatisfactionSurveyPrintBean" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.CLIENT_SATISFACTION_SURVEY_ES.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.CLIENT_SATISFACTION_SURVEY_ES.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
		<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style>
#watermarkbody { font-size:80; font-family:Helvetica; color:#F0F0F0; }
body
	{	margin-left: .1in;
    	margin-right: .1in;
		margin-top: .1in; 
		margin-bottom: .1in;}
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
hr.left
	{text-align:left;}
p.arial-font-16-B
	{font-size:16;
		font-family:"Arial", Helvetica, sans-serif;
		font-weight: bold;}
p.arial-font-16
	{font-size:16;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-14-B
	{font-size:14;
		font-family:"Arial", Helvetica, sans-serif;
		font-weight: bold;}
p.arial-font-14
	{font-size:14;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-8
	{font-size:8;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-8-B
	{font-size:8;
		font-family:"Arial", Helvetica, sans-serif;
		font-weight: bold;}
p.bold 
	{font-weight: bold;}
p.centered
	{text-align:center;}
p.left
	{text-align:left;}
p.text-margin-0px 
	{margin: 0em;}
p.text-margin-10px
	{margin: 10px;}
p.times-font-16
	{font-size:16;
		font-family:"Times New Roman", Times, serif;
		line-height: 100%;}
p.times-font-12
	{font-size:12;
		font-family:"Times New Roman", Times, serif;
		line-height: 100%;}
p.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		line-height: 100%;}
p.trebuchet-ms-10
	{font-size:10;
		font-family: "Trebuchet MS", "Lucida Grande", "Lucida Sans Unicode", "Lucida Sans", Tahoma, sans-serif;
		line-height: 110%;}
p.trebuchet-ms-14
	{font-size:14;
		font-family:"Trebuchet-ms",  Tahoma, sans-serif;
		line-height: 90%;}
p.trebuchet-ms-16
	{font-size:16;
		font-family:"trebuchet-ms",  Tahoma, sans-serif;
		line-height: 90%;}
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
tr.border-bottom {
	border-bottom: 1pt solid black;}
tr.border-top {
	border-top: 1pt solid black;}
td.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
td.trebuchet-ms-14
	{font-size:14;
		font-family:"trebuchet-ms",  Tahoma, sans-serif;
		line-height: 90%;}
td.row0 {
	}
td.row1 {
	background-color:#bfc1c2}

</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				 <tr>
				 	<td align="left" width="100%">
					 	<c:out value="${reportInfo.currentDate}"/> <c:out value="${reportInfo.casefileId}"/>
					</td>
				 </tr>
				</table>
			</div>
		</macro>		
	</macrolist>
</head>

<body footer="myfooter">
<div class="header">
<!--  Header information -->
	<table width="100%" border-style="none" align="center" cellpadding="0"> 
		<tr width="100%"> 
			<td width="10%" valign="top"> <p> <img height="80" width="80" src="images/HarrisCountySeal.jpg" /> </p> </td> 
			<td width="80%" align="center">
				<p class="arial-font-16-B" align="center">HARRIS COUNTY<br/><br/>JUVENILE PROBATION DEPARTMENT</p>
			</td> 
			<td align="right" width="10%" padding-bottom="5"><p class="arial-font-10"></p></td>				
		</tr>
	</table>	
</div>
<div class="body">
	<table width="100%" border-style="none" align="center">
		<tr>
			<td align="left" padding-top="10" padding-bottom="10" width="100%" >
				<p class="trebuchet-ms-10" align="left" padding-top="0" padding-bottom="0">
					<c:out value="${reportInfo.fullStreetName}"/><br/>
				   	<c:out value="${reportInfo.cityStateZip}"/><br/><br/>
				   	<c:out value="${reportInfo.currentDate}"/><br/><br/>				   	
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		El Departamento Juvenil de Libertad Condicional del Condado de Harris est&#225; comprometido en alcanzar una satisfacci&#243;n total del cliente. 
		Queremos proveer el mejor servicio posible a nuestros juveniles en libertad vigilada y sus familias.<br/><br/><br/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		Queremos asegurarnos que usted se sienta que est&#225; obteniendo servicio de calidad.  No sabremos esto sin su informaci&#243;n.<br/><br/><br/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		Por favor ay&#250;denos a realizar esta meta tomando el tiempo para completar este breve cuestionario. Esta informaci&#243;n nos ayudar&#225; 
		en mantenernos enfocados en sus expectaciones.<br/><br/><br/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		Por favor notar: Aunque los comentarios son opcionales, si usted siente que los servicios fueron menos de lo esperado;  apreciar�amos cualquier 
		comentario que usted podr�a proporcionar; eso nos dar&#225; la mejor oportunidad para mejorar.<br/><br/><br/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		Finalmente sentimos que nuestros oficiales de libertad condicional y abastecedores privados trabajan duro y toman orgullo en hacer un buen trabajo.  
		Le agradeceremos sus comentarios especialmente si usted siente que le impresionaron los servicios que usted y su hijo/hija recibieron de nuestro personal.<br/><br/><br/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		Se incluye un sobre con porte pagado para la devoluci&#243;n del cuestionario completo y se le agradece por antemano el ayudarnos a mejorar nuestros servicios 
		a la juventud y a familias.<br/><br/><br/>	
				</p>
			</td>
		</tr>
	</table>	
</div>	
<div class="body">
	<table width="100%" border-style="all" align="center" padding-top="10">
		<tr width="100%">
			<td colspan="11" >
				<p align="center" class="trebuchet-ms-16">
                	Cuestionario de Satisfacci&#243;n del Cliente
                </p>
                <p align="center" class="trebuchet-ms-14">
                	&#191;Como calificar&#237;a su m&#225;s reciente experiencia con el Departamento Juvenil de Libertad Condicional?
                </p>
			</td>			
		</tr>
		<tr width="100%" > 
			<td padding-bottom="5" padding-top="5" align="center" width="30%" border="0.75" background-color="#800080">
			</td>
			<td colspan="2" padding-bottom="5" padding-top="5" align="center" width="14%" border="0.75" background-color="#800080">
				<p class="trebuchet-ms-10 centered" color="white">Excelente</p>
			</td>
			<td colspan="2" padding-bottom="5" padding-top="5" align="center" width="14%" border="0.75" background-color="#800080">
				<p class="trebuchet-ms-10 centered" color="white">Arriba de lo Normal</p>
			</td>
			<td colspan="2" padding-bottom="5" padding-top="5" align="center" width="14%" border="0.75" background-color="#800080">
				<p class="trebuchet-ms-10 centered" color="white">Normal</p>
			</td>
			<td colspan="2"  padding-bottom="5" padding-top="5" align="center" width="14%" border="0.75" background-color="#800080">
				<p class="trebuchet-ms-10 centered" color="white">Bajo de lo Normal</p>
			</td>
			<td colspan="2" padding-bottom="5" padding-top="5" align="center" width="14%" border="0.75" background-color="#800080">
				<p class="trebuchet-ms-10 centered" color="white">Frustrante</p>
			</td>				
		</tr>
		<tr width="100%" class="trebuchet-ms-14"> 
			<td padding-bottom="5" padding-top="5" align="left" width="30%" border="0.75" background-color="#D9D9D9">
				<p class="trebuchet-ms-10 centered" font-weight="bold">Calificaci&#243;n para HCJPD</p>
			</td>
			<td colspan="2" padding-bottom="5" padding-top="5" align="center" width="14%" border="0.75" background-color="none">
			</td>
			<td colspan="2" padding-bottom="5" padding-top="5" align="center" width="14%" border="0.75" background-color="none">
			</td>
			<td colspan="2" padding-bottom="5" padding-top="5" align="center" width="14%" border="0.75" background-color="none">
			</td>
			<td colspan="2" padding-bottom="5" padding-top="5" align="center" width="14%" border="0.75" background-color="none">
			</td>
			<td colspan="2" padding-bottom="5" padding-top="5" align="center" width="14%" border="0.75" background-color="none">
			</td>				
		</tr>
		<tr width="100%" class="trebuchet-ms-14" height="80"> 
			<td padding-bottom="5" padding-top="5" align="right" width="30%" border="0.75" background-color="#D9D9D9">
				<p class="times-font-12" font-weight="bold">Comentarios</p>
			</td>
			<td  colspan="10" padding-bottom="5" padding-top="5" align="center" width="70%" border="0.75" background-color="none">
			</td>				
		</tr>
		<tr width="100%">
			<td colspan="11">
                <p align="center" class="trebuchet-ms-14">
                	<br/><br/>&#191;Que tan bien desempe&#241;&#243; el Oficial <c:out value="${reportInfo.probationOfficer.firstName}"/> <c:out value="${reportInfo.probationOfficer.lastName}"/> su trabajo?  (Cheque todo lo que aplique)
                </p>
			</td>			
		</tr>
		<tr width="100%" > 
			<td padding-bottom="5" padding-top="5" align="center" width="30%" border="0.75" background-color="#800080">
			</td>
			<td colspan="2" padding-bottom="5" padding-top="5" align="center" width="14%" border="0.75" background-color="#800080">
				<p class="trebuchet-ms-10 centered" color="white">Se relaciono bien con el/la joven</p>
			</td>
			<td colspan="2" padding-bottom="5" padding-top="5" align="center" width="14%" border="0.75" background-color="#800080">
				<p class="trebuchet-ms-10 centered" color="white">Fue beneficial para el/la joven</p>
			</td>
			<td colspan="2" padding-bottom="5" padding-top="5" align="center" width="14%" border="0.75" background-color="#800080">
				<p class="trebuchet-ms-10 centered" color="white">Atento con la familia</p>
			</td>
			<td colspan="2" padding-bottom="5" padding-top="5" align="center" width="14%" border="0.75" background-color="#800080">
				<p class="trebuchet-ms-10 centered" color="white">Atento a las necesidades de el/la joven</p>
			</td>
			<td colspan="2" padding-bottom="5" padding-top="5" align="center" width="14%" border="0.75" background-color="#800080">
				<p class="trebuchet-ms-10 centered" color="white">Fue dif&#237;cil trabajar con este oficial</p>
			</td>				
		</tr>
		<tr width="100%" class="trebuchet-ms-14" height="20"> 
			<td padding-bottom="5" padding-top="5" align="left" width="30%" border="0.75" background-color="#D9D9D9">
			</td>
			<td colspan="2" padding-bottom="5" padding-top="5" align="center" width="14%" border="0.75" background-color="none">
			</td>
			<td colspan="2" padding-bottom="5" padding-top="5" align="center" width="14%" border="0.75" background-color="none">
			</td>
			<td colspan="2" padding-bottom="5" padding-top="5" align="center" width="14%" border="0.75" background-color="none">
			</td>
			<td colspan="2" padding-bottom="5" padding-top="5" align="center" width="14%" border="0.75" background-color="none">
			</td>
			<td colspan="2" padding-bottom="5" padding-top="5" align="center" width="14%" border="0.75" background-color="none">
			</td>				
		</tr>
		<tr width="100%" class="trebuchet-ms-14" height="80"> 
			<td padding-bottom="5" padding-top="5" align="right" width="30%" border="0.75" background-color="#D9D9D9">
				<p class="times-font-12" font-weight="bold">Comentarios</p>
			</td>
			<td  colspan="10" padding-bottom="5" padding-top="5" align="center" width="70%" border="0.75" background-color="none">
			</td>				
		</tr>
		<tr width="100%">
			<td colspan="11" >
                <p align="center" class="trebuchet-ms-14">
                	<br/><br/>Por favor marque todo lo que aplique:
                </p>
			</td>			
		</tr>
		<tr width="100%">
			<td colspan="9" border="0.75" background-color="#800080">
                <p class="times-font-12" align="left" color="white">
                	Satisfacci&#243;n En General:
                </p>
			</td>
			<td border="0.75" background-color="#800080">
                <p class="times-font-12" align="center" color="white">
                	Si
                </p>
			</td>
			<td border="0.75" background-color="#800080">
                <p class="times-font-12" align="center" color="white">
                	No
                </p>
			</td>			
		</tr>
		<tr width="100%" height="20">
			<td colspan="9" border="0.75" background-color="#D9D9D9">
                <p class="times-font-12" align="left">
                	Fuimos prove&#237;dos con la informaci&#243;n y documentaci&#243;n adecuada.
                </p>
			</td>
			<td border="0.75">
                <p align="center" class="trebuchet-ms-10"></p>
			</td>
			<td border="0.75">
                <p align="center" class="trebuchet-ms-10"></p>
			</td>			
		</tr>
		<tr width="100%" height="20">
			<td colspan="9" border="0.75" background-color="#D9D9D9">
                <p class="times-font-12" align="left">
                	Fuimos requeridos a atender demasiados programas.
                </p>
			</td>
			<td border="0.75">
                <p align="center" class="trebuchet-ms-10"></p>
			</td>
			<td border="0.75">
                <p align="center" class="trebuchet-ms-10"></p>
			</td>			
		</tr>
		<tr width="100%" height="20">
			<td colspan="9" border="0.75" background-color="#D9D9D9">
                <p class="times-font-12" align="left">
                	Los programas y citas no fueron convenientemente previstos.
                </p>
			</td>
			<td border="0.75">
                <p align="center" class="trebuchet-ms-10"></p>
			</td>
			<td border="0.75">
                <p align="center" class="trebuchet-ms-10"></p>
			</td>			
		</tr>
		<tr width="100%" height="20">
			<td colspan="9" border="0.75" background-color="#D9D9D9">
                <p class="times-font-12" align="left">
                	Los programas no estaban convenientemente localizados.
                </p>
			</td>
			<td border="0.75">
                <p align="center" class="trebuchet-ms-10"></p>
			</td>
			<td border="0.75">
                <p align="center" class="trebuchet-ms-10"></p>
			</td>			
		</tr>
		<tr width="100%" height="20">
			<td colspan="9" border="0.75" background-color="#D9D9D9">
                <p class="times-font-12" align="left">
                	Pudimos proveer informaci&#243;n al desarrollo del plan del caso y objetivos.
                </p>
			</td>
			<td border="0.75">
                <p align="center" class="trebuchet-ms-10"></p>
			</td>
			<td border="0.75">
                <p align="center" class="trebuchet-ms-10"></p>
			</td>			
		</tr>
		<tr width="100%" height="20">
			<td colspan="9" border="0.75" background-color="#D9D9D9">
                <p class="times-font-12" align="left">
                	En general, estoy complacido con el contacto que tuve con el Oficial.
                </p>
			</td>
			<td border="0.75">
                <p align="center" class="trebuchet-ms-10"></p>
			</td>
			<td border="0.75">
                <p align="center" class="trebuchet-ms-10"></p>
			</td>			
		</tr>
	</table>			
</div>
</body>

</pdf>