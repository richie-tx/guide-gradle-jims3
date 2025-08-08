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
	<meta name="title" value="<%=PDFReport.MJC_CLOSING_LETTER_ES.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.MJC_CLOSING_LETTER_ES.getReportName()%>"/>
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
<body footer="myfooter" footer-height="5mm">
<!--  Header information -->
<div class="header">
	<table width="100%" border-style="none" align="center" cellpadding="0">
		<tr width="100%">
			<td width="10%" valign="bottom">
				<p class="centered">
				<!-- 
					<img height="45" width="45" src="images/HarrisCountySeal.jpg"/>
				 -->
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
   		Núm. de Supervisión: ${reportInfo.casefileId}<br/><br/>
   		Fecha: <u>${reportInfo.currentDate}</u>
   		<p>
  		${reportInfo.juvenile.fullNameFirst}, esta carta es para reconocer que usted ha completado su período de supervisión de libertad 
  		condicional o adjudicación diferida asignada bajo la supervisión del Departamento de Libertad Condicional de 
  		Menores del condado de Harris. El expediente de su caso con Libertad Condicional de Menores del condado de 
  		Harris está cerrado.
	  	</p>
	  	<p>
  		En general, los registros del tribunal de menores son confidenciales; sin embargo, esta norma no se aplica a todos 
  		ni a todas las situaciones Tenga en cuenta que, si un menor ha estado involucrado con la policía, se genera un registro 
  		de menores permanente en el estado de Texas y el FBI. Ciertas agencias y entidades pueden obtener acceso a estos 
  		registros, lo que puede afectarle en el futuro.
  		</p>
  		<p>
  		El estado de Texas tiene leyes que aparecen dentro del Código de Familia de Texas (Subcapítulo A, Capítulo 58) que 
  		explican la confidencialidad de los registros del tribunal de menores y a quiénes se aplica. El Subcapítulo C-1, Capítulo 
  		58 del Código de Familia de Texas también proporciona los procedimientos para el Sellado de los registros de 
  		menores.
	  	</p>
	  	<p>
		Ciertos registros de menores pueden sellarse de forma automática sin necesidad de una solicitud al tribunal una vez 
		que cumpla los 19 años de edad u otros factores de calificación. También puede ser elegible para solicitar un sellado 
		<u>sin costo</u> de sus registros de menor después de que haya transcurrido un año desde su liberación final de la libertad 
		condicional o cuando cumpla 17 años de edad, lo que ocurra primero. Para determinar si tiene derecho a estos 
		servicios gratuitos para sellar sus registros de menor, póngase en contacto con:
		</p>
		<p>
  		<b>•		Para sellar los delitos menores</b>: Houston Volunteer Lawyers, el mayor proveedor de servicios legales 
  					voluntarios en el condado de Harris. Complete el formulario de admisión 
  					en <a>https://www.makejusticehappen.org/</a> (en inglés) o llame al 713-228-0735
		</p>
		<p>
  		<b>•		Para sellar los delitos mayores</b>: El Centro de Derecho de la Universidad de Houston/Programa de Sellado 
  					de Registros de Menores del Proyecto de defensa de menores y niños. Complete el formulario de admisión 
  					en <a>info@jcaptexas.org</a>o llame a Emanuele DiStefano al 713-743-1132 o a Katya Dow al 713-743-1011. Para 
  					obtener más información, visite <a>https://www.jcaptexas.org/</a> (en inglés).
		</p>
		<p>
	  	Es importante que el Departamento de Libertad Condicional de Menores del condado de Harris tenga su información 
	  	de contacto precisa hasta que cumpla los veinte (20) años. Póngase en contacto con nosotros al 713-222-4100 para 
	  	proporcionar cualquier actualización de su dirección postal y número de teléfono, cuando ocurra.
	  	</p>
		
			<br/>
			<br/>
			<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<c:out value="${reportInfo.officerFirstName}"/> <c:out value="${reportInfo.officerLastName}"/><br/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   	Funcionario de Libertad Condicional de Menores
		</p>
</div>
	
</body>
</pdf>
