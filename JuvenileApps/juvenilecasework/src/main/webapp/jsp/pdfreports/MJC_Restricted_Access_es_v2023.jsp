<%-- 06/28/2016		Task 36225--%>
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
<jsp:useBean id="reportInfo" class="ui.juvenilecase.casefile.ClosingPacketReportPrintBean" scope="session" />
<pdf onload="pdf:130">
<head>
<meta name="title" value="<%=PDFReport.MJC_RESTRICTED_ACCESS_ES.getReportName()%>"/>
<meta name="author" value="JIMS2, Harris County"/>
<meta name="subject" value="<%=PDFReport.MJC_RESTRICTED_ACCESS_ES.getReportName()%>"/>
<meta name="security-password" value="406"/>
<meta name="encryption-algorithm" value="128bit"/>
<meta name="access-level" value="print-all change-none extract-none"/>
	<meta name="base" value="file:c:/BFOImages/" /> 

<!-- STYLE SHEET LINK -->
<style>
body
	{	
		size:letter;
		margin-left: 0.30in;
    	margin-right: 0.30in;
		margin-top: 0.00in; 
		margin-bottom: 0.00in;
		font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left 
		bottom: 4px;}
p.div {
    background-color: #B22222;
    width: 10px;
    padding: 4px;
    border: 4px solid white;
    margin: 4px;
}
li.arial-7
	{	font-size:7;
		font-family:"Arial", Helvetica, sans-serif;
		}
ul.arial-7
	{	font-size:7;
		font-family:"Arial", Helvetica, sans-serif;
		}

img.div{
    display: inline-block;
    width: 259px;
	height: 259px; 
}
img.div1{
    display: inline-block;
    width: 263px;
	height: 273px; 
}
  
img.div2{
    
    width: 8px; 
	height: 380px; 
}
 
p.div2 {
    background-color: #6d4a80;
    width: 10px;
    padding: 4px;
    border: 4px solid white;
    margin: 4px;
	text-align: center;
}
p.div1 {
    background-color: #626262;
    width: 3px;
    padding: 4px;
    border: 4px solid white;
    margin: 4px;
}
p.div3 {
    background-color: #23698c;
    width: 10px;
    padding: 4px;
    border: 4px solid white;
    margin: 4px;
}
p.div4 {
     
	height: 140px;
    padding: 15px;
	background-color: #FFFFFF ;
	color: #23698c;
  	border: 15px ;
	}
p.div5 {
     width: 145px;
	height: 140px;
    padding: 5px;
 	border: 4px solid white;
	border: 5px ;
	margin: 0;
}

div.section
	{	
   		 width: 892;
   		 height: 1024;
		
   		 }
div.header
	{ 	
		text-align: center
		font-size:14;
		font-family:"Arial", Helvetica, sans-serif;
		line-height: 100%;}
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
		font-family:"sans-serif", Helvetica, sans-serif;}
p.arial-6
	{	font-size:6;
		font-family:"Calibri", Helvetica, sans-serif;}  
p.arial-7
	{	font-size:7;
		font-family:"Calibri", Helvetica, sans-serif;} 
p.arial-8
	{	font-size:8;
		font-family:"Calibri", Helvetica, sans-serif;}
p.arial-9
	{	font-size:9;
		font-family:"Calibri", Helvetica, sans-serif;}
p.arial-9-blur
	{	font-size:9;
		font-family:"Calibri", Helvetica, sans-serif;
		color:#eaf1f5}
td.div3 {
    background-color: #135f81;
}
td.arial-7
	{	font-size:7;
		font-family:"Arial", Helvetica, sans-serif;} 
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
td.times-font-18
	{font-size:18;
		font-family:"Times New Roman", Times, serif;
		font-weight: normal;
		line-height: 100%;}
td.times-font-16
	{font-size:16;
		font-family:"Times New Roman", Times, serif;
		font-weight: normal;
		line-height: 100%;}
td.times-font-10
	{font-size:12;
		font-family:"Times New Roman", Times, serif;
		font-weight: normal;
		line-height: 100%;}
tr.color{ background-color:#B22222;
		color:#FFFFFF;}
td.color1{ background-color:#626262;
		color:#FFFFFF;}
tr.color1{ background-color:#626262;
		color:#FFFFFF;}
tr.color2{ background-color:#6d4a80;
		color:#FFFFFF;}
tr.color3{ background-color:#23698c;
			color:#FFFFFF;}
tr.color4{ background-color:#58595c;
			color:#FFFFFF;}
tr.color7{ background-color:#0a354b;
			color:#FFFFFF;}
tr.div3 { background-color: #23698c;}
p.color3{ background-color:#23698c;
			color:#FFFFFF;}
p.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.arial-blue-11
	{font-size:11;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-blue-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.times-font-18
	{font-size:18;
		font-family:"Times New Roman", Times, serif;
		font-weight: normal;
		line-height: 100%;}
p.times-font-12
	{font-size:12;
		font-family:"Times New Roman", Times, serif;
		font-weight: normal;
		line-height: 100%;}
p.times-font-12_italic
	{font-size:12;
		font-family:"Times New Roman", Times, serif;
		font-weight: normal;
		font-style: italic;
		line-height: 100%;}
p.times-font-10
	{font-size:10;
		font-family:"Times New Roman", Times, serif;
		font-weight: normal;
		line-height: 100%;}
p.times-font-10_italic
	{font-size:10;
		font-family:"Times New Roman", Times, serif;
		font-weight: normal;
		font-style: italic;
		line-height: 100%;}
p.times-font-8
	{font-size:8;
		font-family:"Times New Roman", Times, serif;
		font-weight: normal;
		line-height: 100%;}
nobr { white-space:nowrap; }
pre  { white-space:pre; }

.margin-adj {
	margin: 2px;
	padding: 0;
	display: block;
	width: 100%;
}

.margin-adj-statutes {
	margin: 2px;
	padding: 0;
	display: block;
	width: 100%;
}

.sub-heading {
	font-family:"Times New Roman", Times, serif;
	font-size: 18px;
	font-style: italic;
	text-align: center;
}

</style>
<style type="text/css">
</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				 <tr>
				 	<td class="arial-8" align="center" width="100%" style="color: #a0a3a1">
						Publicado en octubre de 2022
					</td>
				 </tr>
				</table>
			</div>
		</macro>	
		<macro id="statutesfooter">
			<div class="footer">
				<table width="100%">
				 <tr>
				 	<td class="arial-8" align="left"  width="100%" style="margin-left: 10%; color: #a0a3a1">
						Leyes vigentes al 1 de septiembre de 2021
					</td>
				 	<td class="arial-7" align="right" width="100%">
						Page <pagenumber/> of <totalpages/>
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
	<p class="times-font-18" style="text-align: center; width: 100%;" >Archivos y Expedientes del Sistema de Justicia Juvenil de Texas</p>
	<p class="sub-heading" style="text-align: center; width: 100%;" >
		Una guía para jóvenes para comprender los expedientes juveniles y los sellados
	</p>
</div>
<!--  Page 1 -->
<div class="body" page-break-before="avoid">
   	<p class="arial-font-10" color="DodgerBlue" align="left" padding-top="0" padding-bottom="0" font-weight="bold">
  		¿Quién tiene un expediente juvenil en Texas?
  	</p>
  	<p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">
  		Cualquier persona derivada a un tribunal de menores por una conducta delictiva que ocurrió cuando tenía entre 10 y 16 años (antes de los 17 años) 
  		tiene un expediente juvenil, incluso si no fue detenido por la policía. Las derivaciones por
		conducta delictiva (delitos menores de clase A o B o delitos graves) o una conducta delictiva que indique la necesidad de supervisión (CINS, por su sigla en inglés) 
		(p. ej., delitos menores de Clase C: fuga, “sexting”). Los expedientes juveniles existen en el departamento de libertad condicional, autoridades policiales, 
		fiscales, tribunales y en la base de datos de informática del Sistema de Información de Justicia Juvenil (JJIS, por su sigla en inglés) 
		gestionada por el Departamento de Seguridad Pública de Texas.
	</p>
	
	<p class="arial-font-10" padding-top="0" padding-bottom="0">
  	</p> <!-- margin placeholder -->
  	
  	<p class="arial-font-10" color="DodgerBlue" align="left" padding-top="0" padding-bottom="0" font-weight="bold">
  		¿Quién tiene acceso a los expedientes juveniles?
  	</p> 
	<p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">
	  	Los expedientes juveniles son confidenciales. La ley indica quién puede acceder a ellos. Esto incluye entidades que necesitan acceso 
	  	para la seguridad de la comunidad o brindar servicios a los menores. El Departamento de Seguridad Pública (DPS, por su sigla en inglés) puede compartir 
	  	los expedientes en el JJIS solo con: agencias de justicia penal y juvenil, el Departamento de Justicia Juvenil de Texas (TJJD, por su sigla en inglés) y 
	  	el defensor público del TJJD, los tribunales que tienen jurisdicción sobre los jóvenes, el Departamento de Servicios para la Familia y 
	  	de Protección para ciertas verificaciones de antecedentes, el ejercito (únicamente con el permiso del menor) y las agencias de justicia 
	  	no penal si lo permite la ley federal o una orden ejecutiva. Las autoridades policiales, los funcionarios de libertad condicional, los fiscales y 
	  	los tribunales también tienen limitaciones en la forma en que pueden compartir los expedientes. Una vez que los expedientes son sellados, nadie puede acceder a ellos.
	</p>
	
	<p class="arial-font-10" padding-top="0" padding-bottom="0">
  	</p> <!-- margin placeholder -->
  	
	<p class="arial-font-10" color="DodgerBlue" align="left" padding-top="0" padding-bottom="0" font-weight="bold">
  		¿Cómo puedo sellar mis registros?
	</p>
	<p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">
		Si la única razón por la que fue derivado a un tribunal de menores fue debido a un caso CINS y existen expedientes judiciales, los expedientes serán sellados cuando 
		cumpla 18 años si no tiene una condena por un delito grave o cargos pendientes siendo adulto. Si fue derivado a un tribunal de menores por una conducta delictiva 
		(delito grave o delito menor), pero nunca fue sentenciado (es decir, “no se lo declaró culpable”) o si fue juzgado por un delito menor, pero no por un delito grave, 
		los expedientes serán sellados cuando cumpla 19 años si no tiene una condena por un delito grave o delito menor que amerite cárcel y no tiene cargos pendientes 
		siendo menor o adulto. No tiene que presentar una solicitud al tribunal para este tipo de sellado.  
	</p>
	<p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">
		Si fue juzgado por un delito grave o no cumple con los requisitos para el sellado de expedientes indicados anteriormente, puede presentar una solicitud para 
		pedirle al tribunal que selle los expedientes. No necesita un abogado para hacer esto, pero puede optar por contratar uno. Está permitido presentar esta solicitud 
		si tiene 17 años y ha salido del régimen de libertad condicional o el caso en su contra está cerrado. Si tiene menos de 17 años, puede presentar una solicitud 
		si ha transcurrido al menos un año desde que salió del régimen de libertad condicional o se cerró el caso. El tribunal solo puede sellar los expedientes si no 
		tiene condenas por delitos graves o cargos pendientes siendo adulto, si no está obligado actualmente a registrase como agresor sexual y si no está actualmente recluido 
		en el TJJD. El tribunal puede elegir sellar los expedientes sin una audiencia o puede llevar a cabo una audiencia para decidir si sellar los expedientes o no. 
		El tribunal no puede denegar la solicitud de sellar los expedientes sin realizar primero una audiencia. Debe comunicarse con el departamento de libertad condicional que 
		brinda servicios para obtener información sobre el proceso de sellado. 
	</p>
  	
</div>
<!--  Page 2 -->
<div class="body">
	<p class="arial-font-10" color="DodgerBlue" align="left" padding-top="0" padding-bottom="0" font-weight="bold">
	  	¿Existe algún expediente que no se pueda sellar?
	</p>
	<p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">
		Los expedientes no se pueden sellar si usted fue certificado para ser juzgado como adulto o recibió una sentencia definida (libertad condicional o reclusión en el TJJD). 
		En el caso de ser necesario el registro como agresor sexual, los expedientes no se pueden sellar hasta que haya vencido el requisito de registro. Si está recluido 
		en el TJJD sin una sentencia definida, los expedientes no se pueden sellar hasta que haya salido del TJJD. Los expedientes en los juzgados 
		de paz (JP, por su sigla en inglés) o tribunales municipales (delitos menores de clase C) no se pueden sellar porque no son expedientes juveniles. 
		Los expedientes en la base de datos de pandillas no se pueden sellar, aunque se pueden eliminar en ciertas circunstancias. Solo las agencias de justicia penal pueden acceder a estos expedientes y solo para fines de justicia penal.
	</p>
	<p class="arial-font-10" padding-top="0" padding-bottom="0">
  	</p> <!-- margin placeholder -->
	<p class="arial-font-10" color="DodgerBlue" align="left" padding-top="0" padding-bottom="0" font-weight="bold">
		¿Qué sucede cuando se sellan los expedientes?
	</p>
	<p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">
	  	El sellado de los expedientes implica que todos los expedientes que muestren que usted fue derivado a un tribunal de menores se almacenan de una manera que ya no se puedan ver. 
	  	Se eliminan todas las sentencias y se trata como si usted nunca hubiera sido detenido o derivado a un tribunal de menores. La ley establece que usted no está obligado a 
	  	declarar en ningún procedimiento o en ninguna solicitud de empleo, licencia, admisión, vivienda u otro beneficio público o privado que los expedientes existieron alguna vez o que alguna vez fue arrestado, procesado o sentenciado.
	</p>
	<p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">
	  	Además, en la ley se establece que una vez que los expedientes se sellan, la información en los expedientes, el hecho de que alguna vez existieron o la negación de la 
	  	existencia de los expedientes no pueden usarse en su contra de ninguna manera. Esto incluye un proceso por perjurio u otro proceso penal, un proceso civil, 
	  	incluido un proceso administrativo que involucre a una entidad gubernamental, un proceso de solicitud de licencia o certificación, o una decisión de admisión, 
	  	empleo o vivienda. Incluso si una persona descubre que alguna vez tuvo un expediente juvenil, este no puede ser usado en contra.
	</p>
	<p class="arial-font-10" padding-top="0" padding-bottom="0">
  	</p> <!-- margin placeholder -->
	<p class="arial-font-10" color="DodgerBlue" align="left" padding-top="0" padding-bottom="0" font-weight="bold">
		¿Se pueden reabrir los expedientes sellados?
	</p>
	<p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">
	  	Un tribunal puede reabrir los expedientes sellados si usted lo solicita. El tribunal también puede reabrir los expedientes si un fiscal lo solicita para fines limitados, 
	  	incluidos un enjuiciamiento futuro por un delito capital o por una ofensa por la que el castigo puede aumentar según el expediente juvenil. Reabrir un expediente 
	  	sellado no significa que el expediente sea de dominio “público”. Las protecciones que vienen con el sellado de un expediente permanecen vigentes, 
	  	pero los documentos se pueden usar por las razones especificadas.
	</p>
	
	<p class="arial-font-10" padding-top="0" padding-bottom="0">
  	</p> <!-- margin placeholder -->
  	
	<p class="arial-font-10" color="DodgerBlue" align="left" padding-top="0" padding-bottom="0" font-weight="bold">
		¿Qué sucede con los expedientes en un juzgado de paz o tribunal municipal?
	</p>
	<p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">
	  	Para los menores de edad, los delitos menores de clase C en un juzgado de paz o tribunal municipal son confidenciales y no se pueden divulgar al público. 
	  	Si solo tiene una condena antes de cumplir 17 años, es posible que pueda hacer que se “eliminen” o se remuevan ciertos delitos del expediente.
	</p>
	
	<p class="arial-font-10" padding-top="0" padding-bottom="0">
  	</p> <!-- margin placeholder -->
  	
	<p class="arial-font-10" color="DodgerBlue" align="left" padding-top="0" padding-bottom="0" font-weight="bold">
		¿Dónde puedo obtener información adicional? 
	</p>
	<p class="arial-font-10" padding-top="0" padding-bottom="0">
		Capítulo 58 del Código Familiar de Texas y Artículo 45 del Código de Procedimiento Penal. Puede acceder a las leyes de Texas en línea en: <a href="www.statutes.legis.state.tx.us">www.statutes.legis.state.tx.us</a>
	</p>
	
	<span class="margin-adj">&nbsp;<!-- ============PLACE HOLDER - MARGIN============== --> </span>

	<p class="arial-font-10" align="center" style="margin: 2px; padding: 2px">
		Redactado por el
	</p>
	<p class="times-font-12" color="DodgerBlue" align="center" font-weight="bold" style="margin: 2px; padding: 2px">
		Departamento de Justicia Juvenil de Texas
	</p>
	<p class="arial-font-10" align="center" style="margin: 2px; padding: 2px">
		www.tjjd.texas.gov
	</p>
</div>
</body>
<!--  Page 3 -->
<body footer="statutesfooter" footer-height="10mm">
<div class="body">
	<p class="times-font-10" color="DodgerBlue" align="center" padding-top="0" padding-bottom="0" font-weight="bold">
		Capítulo 58 del Código Familiar de Texas
	</p>
	<p class="arial-font-10" color="DodgerBlue" align="center" padding-top="0" padding-bottom="0" font-weight="bold">
		SUBCAPÍTULO C-1. SELLADO Y DESTRUCCIÓN DE EXPEDIENTES JUVENILES
	</p>
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.251. DEFINICIONES. En este subcapítulo:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) “Expediente electrónico” significa una entrada en un archivo de computadora o información
			en microfilm, microficha o cualquier otro medio de almacenamiento electrónico.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) “Asuntos juveniles” significa una derivación a un tribunal 
			de menores o al departamento de libertad condicional juvenil y todos los procedimientos y resultados judiciales relacionados, si los hubiera.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3)	“Expediente físico” significa una copia impresa de un expediente.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4)	“Expediente” significa cualquier documentación relacionada a un asunto juvenil, 
			incluida la información de esa documentación.</p></td>
		</tr>
		<tr align="center" class="margin-adj-statutes">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0"><!-- ============PLACE HOLDER - MARGIN============== --> </p></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="left" padding-top="0" padding-bottom="0">Sec. 58.252 EXPEDIENTES EXENTOS. Los siguientes expedientes quedan exentos de este subcapítulo:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1) expedientes relacionados con delincuencia organizada o una pandilla callejera delictiva gestionados por el Departamento de Seguridad Pública o agencias de autoridades policiales en virtud del Capítulo 61 del Código de Procedimiento Penal;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2)	expedientes de registro de agresores sexuales gestionados por el Departamento de Seguridad Pública o agencias de autoridades policiales en virtud del Capítulo 62 del Código de Procedimiento Penal; y</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3)	expedientes recopilados o gestionados por el Departamento de Justicia Juvenil de Texas con fines estadísticos y de investigación, incluida la información presentada en virtud de la sección 221.007 del Código de Recursos Humanos e información de identificación personal.</p></td>
		</tr>
		<tr align="center" class="margin-adj-statutes">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0"><!-- ============PLACE HOLDER - MARGIN============== --> </p></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="left" padding-top="0" padding-bottom="0">Sec.58.253. SELLADO DE EXPEDIENTES SIN SOLICITUD: CONDUCTA DELICTIVA.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a) Esta sección no aplica a los expedientes de un menor derivado a un tribunal de menores o al departamento de libertad condicional juvenil únicamente por una conducta que indique la necesidad de supervisión.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b)	Una persona que fue derivada al departamento de libertad condicional juvenil por una conducta delictiva tiene derecho a que se sellen todos los expedientes relacionados con los asuntos juveniles de la persona, 
			incluidos los expedientes relacionados con cualquier asunto que involucre una conducta que indique la necesidad de supervisión, sin solicitar a un tribunal de menores si la persona:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1)	tiene al menos 19 años;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2)	no ha sido juzgada por haber incurrido en una conducta delictiva o, si fue juzgada por una conducta delictiva, no fue juzgada por una conducta delictiva que viola una ley penal del grado de delito grave;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3)	no tiene asuntos pendientes de conducta delictiva;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4)	no ha sido transferida de un tribunal de menores a un tribunal penal para enjuiciamiento en virtud de la Sección 54.02;
				</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(5)	no ha sido condenada como adulto por un delito grave o delito menor punible con reclusión en la cárcel, y
				</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(6)	no tiene ningún cargo pendiente como adulto por un delito grave o delito menor punible con reclusión en la cárcel.
			</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"></td>
		</tr>
		
		
	</table>
</div>
<!--  Page 4 -->
<div class="body">
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.254. CERTIFICACIÓN DE ELEGIBILIDAD PARA SELLAR EXPEDIENTES SIN SOLICITUD POR CONDUCTA DELICTIVA.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a) El Departamento de Seguridad Pública certificará ante un departamento 
			de libertad condicional juvenil que ha presentado los expedientes al sistema de información de justicia juvenil y que los expedientes relacionados 
			con una persona derivada al departamento de libertad condicional juvenil parecen ser elegibles para ser sellados en virtud de la Sección 58.253.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b)	El Departamento de Seguridad Pública podrá emitir la certificación 
			descrita en la Subsección (a) por medios electrónicos, incluido el correo electrónico.
		</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(c) Salvo lo indicado en la Subsección (d), a más tardar 60 días después 
			de la fecha en la que el departamento de libertad condicional juvenil reciba una certificación en virtud de la Subsección (a), el departamento de libertad condicional juvenil deberá:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1)	dar acuse de recibo de la certificación al tribunal de menores; y</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2)	proporcionar al tribunal una lista de todas las derivaciones 
			recibidas por el departamento relacionadas a esa persona y el resultado de cada derivación.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(d)	Si un departamento de libertad condicional juvenil tiene razones 
			para creer que los registros de la persona para quien el departamento recibió una certificación bajo la Subsección (a) no son elegibles para ser sellados, 
			el departamento de libertad condicional juvenil deberá notificar al Departamento de Seguridad Pública a más tardar 15 días después de la fecha en la que 
			el departamento de libertad condicional juvenil recibió la certificación. Si el departamento de libertad condicional juvenil determina posteriormente 
			que los expedientes de la persona son elegibles para ser sellados, el departamento de libertad condicional juvenil deberá notificar al tribunal de menores y 
			brindar al tribunal la información descrita en la Subsección (c) a más tardar 30 días después de la fecha de la determinación.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(e)	Si, después de recibir una certificación en virtud de la 
			Subsección (a), el departamento de libertad condicional juvenil determina que los expedientes de la persona no son elegibles para ser sellados, 
			el departamento de libertad condicional juvenil y el Departamento de Seguridad Pública deberán actualizar la información en el sistema de información 
			de justicia juvenil para reflejar la determinación y no se requiere ninguna otra acción relacionada con los expedientes.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(f)	A más tardar 60 días después de la fecha en la que el tribunal 
			de menores reciba el aviso del departamento de libertad condicional juvenil en virtud de la Subsección (c), el tribunal de menores deberá emitir una 
			orden para sellar todos los expedientes relacionados con la persona nombrada en la certificación.</p></td>
		</tr>
		<tr align="center" class="margin-adj-statutes">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0"><!-- ============PLACE HOLDER - MARGIN============== --> </p></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.255. SELLADO DE EXPEDIENTES SIN SOLICITUD: CONDUCTA QUE INDIQUE LA NECESIDAD DE SUPERVISIÓN.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a)	Una persona que fue derivada a un tribunal de menores debido a una 
			conducta que indique la necesidad de supervisión tiene derecho a que se sellen todos los expedientes relacionados con todos los asuntos de conducta que 
			indique la necesidad de supervisión sin presentar una solicitud al tribunal de menores si la persona:</p></td>
		</tr>
				<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1)	tiene expedientes relacionados con la conducta presentados ante el secretario del tribunal;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2)	tiene al menos 18 años;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3)	no ha sido derivada al departamento de libertad condicional juvenil por una conducta delictiva;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4)	no ha sido condenada como adulto por un delito grave; y</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(5)	no tiene ningún cargo pendiente como adulto por un delito grave o delito menor punible con reclusión en la cárcel.</p></td>
		</tr>		
		
	</table>
</div>
<!--  Page 5 -->
<div class="body">
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"></td>
		</tr>		

		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b)	El departamento de libertad condicional juvenil deberá:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1)	notificar al tribunal de menores que los expedientes 
			de la persona son elegibles para ser sellados en virtud de la Subsección (a) y</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2)	proporcionar al tribunal de menores una lista de todas las 
			derivaciones recibidas por el departamento relacionadas a esa persona y el resultado de cada derivación.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(c)	A más tardar 60 días después de la fecha en que el tribunal 
			de menores reciba el aviso del departamento de libertad condicional juvenil en virtud de la Subsección (b), el tribunal de menores deberá emitir 
			una orden para sellar todos los expedientes relacionados con la persona nombrada en el aviso.</p></td>
		</tr>
		<tr align="center" class="margin-adj-statutes">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0"><!-- ============PLACE HOLDER - MARGIN============== --> </p></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.2551. SELLADO DE EXPEDIENTES SIN SOLICITUD: EVIDENCIA DE NO VERDADERO.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Un tribunal de menores, de oficio y sin una audiencia, 
			deberá ordenar inmediatamente que se sellen todos los expedientes relacionados con la supuesta conducta si el tribunal presenta evidencia de que 
			las acusaciones no son verdaderas.
		</p></td>
		</tr>
		<tr align="center" class="margin-adj-statutes">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0"><!-- ============PLACE HOLDER - MARGIN============== --> </p></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.256. SOLICITUD PARA SELLAR EXPEDIENTES.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a) Independientemente de las Secciones 58.253 y 58.255, una persona 
			puede presentar una solicitud para sellar los expedientes relacionados con la persona en el tribunal de menores atendido por el departamento de libertad 
			condicional juvenil al que se derivó la persona. El tribunal no puede cobrar una tarifa por la presentación de la solicitud, independientemente de la forma 
			de la solicitud.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a-1) Una solicitud presentada en virtud de esta sección puede 
			ser enviada al tribunal de menores por cualquier método razonable autorizado en virtud de la Regla 21 de las Reglas de Procedimiento Civil de Texas, 
			incluidos medios electrónicos seguros.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b)	Una solicitud presentada en virtud de esta sección debe incluir la 
			siguiente información o la razón por la que uno o más de los siguientes no están incluidos en la solicitud:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1)	información de la persona:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(A) nombre completo;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(B) sexo;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(C) raza o etnia;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(D) fecha de nacimiento;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(E) número de licencia de conducir o tarjeta de identificación; y</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(F) número de seguro social;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2)	la conducta por la que la persona fue derivada al departamento 
			de libertad condicional juvenil, incluida la fecha en que se alegó o se descubrió que se había cometido la conducta;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3)	el número de causa asignado a cada petición relacionada con la 
			persona presentada en el tribunal de menores, si lo hubiera, y el tribunal ante el que presentó la petición; y</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4)	una lista de todas las entidades que la persona crea que tengan 
			posesión de los expedientes relacionados a ella, incluidas las entidades correspondientes enumeradas en la Sección 58.258(b).</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(c)	Salvo lo dispuesto en la Subsección (d), el tribunal de menores 
			puede ordenar el sellado de los expedientes relacionados con todos los asuntos por los que la persona fue derivada al departamento de libertad condicional 
			juvenil si la persona:</p></td>
		</tr>
		
	</table>
</div>
<!--  Page 6 -->
<div class="body">
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"></td>
		</tr>
		
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1)	tiene al menos 17 años, o es menor de 17 años y ha transcurrido un 
			año después de la fecha de liberación final en cada asunto por el que la persona fue derivada al departamento de libertad condicional juvenil;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2)	no tiene ningún asunto de conducta delictiva pendiente con ningún 
			departamento de libertad condicional juvenil o un tribunal de menores;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3)	no fue transferida de un tribunal de menores a un tribunal penal para enjuiciamiento en virtud de la Sección 54.02;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4)	no ha sido condenada como adulto por un delito grave; y</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(5)	no tiene ningún cargo pendiente como adulto por un delito grave 
			o delito menor punible con reclusión en la cárcel.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(d)	Un tribunal no podrá ordenar el sellado de los expedientes de una persona que:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1)	recibió una sentencia definida por participar en:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="2%"></td>
			<td><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(A)	una conducta delictiva que viola la ley penal indicada en virtud de la Sección 53.045; o</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="2%"></td>
			<td><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(B)	conducta delictiva grave habitual como se describe en la Sección 51.031;</p></td>
		</tr>	
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) actualmente, está obligada a registrarse como agresor sexual en virtud del Capítulo 62 del Código de Procedimiento Penal; o
				</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3)	fue recluida en el Departamento de Justicia Juvenil de Texas 
			o a un establecimiento correccional bajo custodia posterior a la sentencia en virtud de la Sección 54.04011, a menos que la persona haya sido liberada 
			de la agencia en la que fue recluida.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(e)	Al recibir una solicitud conforme a esta sección, el tribunal puede:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1)	ordenar que los expedientes de la persona sean sellados de inmediato; sin una audiencia; o</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2)	llevar a cabo una audiencia en virtud de la Sección 58.257 
			a discreción del tribunal para determinar si ordenar o no el sellado de los expedientes de la persona.</p></td>
		</tr>
		<tr align="center" class="margin-adj-statutes">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0"><!-- ============PLACE HOLDER - MARGIN============== --> </p></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.257. AUDIENCIA RELACIONADA CON EL SELLADO DE EXPEDIENTES.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a) Una audiencia relacionada con el sellado de los expedientes de una 
			persona debe llevarse a cabo a más tardar 60 días después de la fecha en la que el tribunal recibe la solicitud de la persona en virtud de la Sección 58.256.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b)	El tribunal deberá dar un aviso previo razonable de audiencia en virtud de esta sección a:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1)	la persona que es el sujeto de los expedientes;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2)	el abogado de la persona que presentó la solicitud 
				de sellado en nombre de la persona, si lo hubiere;
			</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3)	el fiscal del tribunal de menores;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4)	todas las entidades nombradas en la solicitud que la persona 
			cree que poseen expedientes elegibles relacionados con la persona: y
		</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(5)	cualquier individuo o entidad cuya presencia en la audiencia sea solicitada por la personal o el
			fiscal.
			</p></td>
		</tr>
		
		
	</table>
</div>
<!--  Page 7 -->
<div class="body">
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.258. ORDEN DE SELLAR EXPEDIENTES.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a) Una orden de sellar expedientes de una persona en virtud
			de este subcapítulo debe incluir la siguiente información o la razón por la que uno o más de los siguientes no están incluidos en la solicitud:
			</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1)	información de la persona:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(A) nombre completo;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(B) sexo;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(C) raza o etnia;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(D) fecha de nacimiento;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(E) número de licencia de conducir o tarjeta de identificación; y</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(F) número de seguro social;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2)	cada instancia de una conducta que indique la necesidad de supervisión o conducta delictiva alegada en contra de la persona o por la que la persona fue derivada al sistema de justicia juvenil;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3)	la fecha y el condado en que se alega que ocurrió cada caso de conducta;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4)	si se presentaron peticiones relacionadas con la persona en un 
			tribunal de menores, el número de causa asignado a cada petición y el tribunal de menores y el condado en el que se presentó cada petición; y</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(5)	una lista de entidades que se cree que están en posesión de los expedientes que se han ordenado sellar, incluidas las entidades enumeradas en virtud de la Subsección (b).</p></td>
		</tr>
				<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b)	A más tardar 60 días después de la fecha en que la orden fue dictada, el tribunal deberá proporcionar una copia de la orden a:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1)	el Departamento de Seguridad Pública;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2)	el Departamento de Justicia Juvenil de Texas si la persona fue recluida en el departamento;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3)	el secretario del tribunal;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4)	el departamento de libertad condicional juvenil al servicio del tribunal;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(5)	la oficina del fiscal;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(6)	cada agencia de autoridades policiales que tuvo contacto con la persona en relación con la conducta que es objeto de la orden de sellado;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(7)	cada agencia pública o privada que tuvo la custodia o que brindó supervisión o servicios a la persona en relación con la conducta que es objeto de la orden de sellado; y</p></td>
		</tr>	
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(8)	cada funcionario, agencia u otra entidad de la que el tribunal tenga motivos para creer que tiene algún expediente que contenga información relacionada con la conducta que es objeto de la orden de sellado.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(c)	Al dictarse la orden, se anulan todas las sentencias relacionadas con la persona y se desestiman los procedimientos y se tratan a todos los efectos como si nunca hubieran tenido lugar. El secretario del tribunal deberá:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1)	sellar todos los expedientes judiciales relacionados con los procedimientos, incluidos cualquier expediente creado en el sistema de administración de casos del secretario; y</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2)	enviar copias de la orden a todas las entidades enumeradas en la orden a través de cualquier método razonable, incluido el correo certificado o medios electrónicos seguros.</p></td>
		</tr>
		
		
		
	</table>			
</div>
<!--  Page 8 -->
<div class="body">
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"></td>
		</tr>
		
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.259. MEDIDAS ADOPTADAS AL RECIBIR LA ORDEN DE SELLAR EXPEDIENTES. </p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a) Una entidad que	reciba una orden de sellar los expedientes de una persona 
			emitida en virtud de este subcapítulo deberá, a más tardar 61 días después de la fecha de recepción de la orden, tomar las siguientes medidas, según corresponda:
		</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1)	el Departamento de Seguridad Pública deberá:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(A) limitar el acceso a los expedientes relacionados con la persona en el sistema de información de justicia juvenil únicamente al Departamento de Justicia Juvenil de Texas con el fin de realizar investigaciones y estudios estadísticos;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(B) destruir cualquier otro expediente relacionado con la persona que esté en posesión del departamento, incluidos los registros de ADN según lo dispuesto en la Sección 411.151 del Código de Gobierno; y</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(C) enviar una verificación por escrito de la limitación y la destrucción de los expedientes al tribunal emisor;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2)	el Departamento de Justicia Juvenil de Texas deberá:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(A) sellar todos los expedientes relacionados con la persona, exceptos aquellos exentos en virtud de la Sección 58.252; y</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(B) enviar una verificación por escrito del sellado de los expedientes al tribunal emisor;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3)	agencia pública o privada que tuvo la custodia o que brindó supervisión o servicios a la persona que es objeto de los expedientes, el departamento de libertad condicional juvenil, entidades de autoridad policial o un fiscal deberán:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(A) sellar todos los expedientes relacionados con la persona, y</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(B) enviar una verificación por escrito del sellado de los expedientes al tribunal emisor; y</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4)	cualquier otra entidad que reciba una orden de sellar los expedientes de una persona deberá:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(A) enviar cualquier expediente relacionado con la persona al tribunal emisor;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(B) eliminar todas las referencias de índice de los expedientes de la persona; y</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(C) enviar una verificación por escrito de la eliminación de las referencias del índice al tribunal emisor.</p></td>
		</tr>
				<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b)	Los expedientes físicos o electrónicos se consideran sellados si los expedientes no son destruidos, pero se almacenan de una manera que se permita acceso a los mismos solo al custodio de expedientes de la entidad que los posee.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(c)	Si una entidad que recibió una orden para sellar los expedientes relacionados con una persona, luego recibe una consulta sobre una persona o el asunto contenido en los expedientes, la entidad debe responde que no existen expedientes relacionados con la persona o el asunto.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(d)	Si una entidad que recibe una orden para sellar expedientes 
			en virtud de este subcapítulo no puede cumplir con la orden porque la información en la orden es incorrecta o insuficiente para permitir que la entidad identifique 
			los expedientes que están sujetos a la orden, la entidad deberá notificar al tribunal emisor a más tardar 30 días después de la fecha de recepción de la orden. 
			El tribunal deberá tomar cualquier medida necesaria y posible para proporcionar la información necesaria a la entidad, incluido ponerse en contacto con la 
			persona que es objeto de la orden o el abogado de la persona.
		</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(e)	Si una entidad que recibe una orden para sellar expedientes en virtud de este subcapítulo no tiene expedientes relacionados con la persona que es objeto de la orden, la entidad deberá proporcionar una verificación por escrito del hecho al tribunal emisor a más tardar 30 días después de la fecha de recepción de la orden.</p></td>
		</tr>
		
	</table>
</div>
<!--  Page 9 -->
<div class="body">
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"></td>
		</tr>

		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.260. INSPECCIÓN Y DIVULGACIÓN DE EXPEDIENTES SELLADOS.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a) Un tribunal de menores puede permitir, por orden, la inspección de los expedientes sellados en virtud de este subcapítulo o en virtud de la Sección 58.003, según existiera esa ley antes del 1 de septiembre 2017, solo mediante:</p></td>
		</tr>
		<tr align="center" style="margin-top: 10px">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">posible uso:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1)	una persona nombrada en la orden, a petición de la persona objeto de los expedientes;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2)	un fiscal, a petición del fiscal, con el fin de revisar los expedientes para su uso posible:
				</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(A) en un juicio capital; o</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(B) para el aumento del castigo en virtud de la Sección 12.42 del Código Penal; o</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3)	un tribunal, el Departamento de Justicia Penal de Texas, o el Departamento de Justicia Juvenil 
			de Texas a los efectos del Artículo 62.007(e) del Código de Procedimiento Penal.
			</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b) Después de que un peticionario inspeccione los expedientes en virtud de esta sección, el tribunal puede ordenar la entrega de todos los expedientes al peticionario a petición de este.</p></td>
		</tr>
		<tr align="center" class="margin-adj-statutes">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0"><!-- ============PLACE HOLDER - MARGIN============== --> </p></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.261. EFECTO DE SELLAR EXPEDIENTES.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a) Una persona cuyos expedientes han sido sellados en virtud de este 
			subcapítulo o en virtud de la Sección 58.003, según existiera esa ley antes del 1 de septiembre de 2017, no está obligada a declarar en ningún procedimiento o en 
			ninguna solicitud de empleo, licencia, admisión, vivienda u otro beneficio público o privado que la persona haya sido objeto de un asunto juvenil.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b)	Si los expedientes han sido sellados, la información en los expedientes, el hecho de que alguna vez existieron o la negación de la existencia de los expedientes o de la participación de la persona en un asunto juvenil no pueden usarse en su contra de ninguna manera, incluso en:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1)	un enjuiciamiento por perjurio u otro procedimiento penal;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2)	un proceso civil, incluido un proceso administrativo que involucre a una entidad gubernamental;</p></td>
		</tr>	
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3)	un proceso de solicitud de licencia o certificación;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4)	una decisión de admisión, empleo o vivienda.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(c)	Una persona que sea objeto de los expedientes sellados no puede renunciar al estado de protección de los expedientes ni a las consecuencias del estado de protección.</p></td>
		</tr>
		<tr align="center" class="margin-adj-statutes">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0"><!-- ============PLACE HOLDER - MARGIN============== --> </p></td>
		</tr>		
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.262. INFORMACIÓN BRINDADA AL MENOR DE EDAD SOBRE EL SELLADO DE EXPEDIENTES.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a) Cuando se deriva a un menor de edad al departamento de libertad condicional juvenil, un funcionario del departamento de libertad condicional juvenil le dará al menor de edad y al padre, madre, tutor o custodio de este una explicación por escrito que describa el proceso de sellar los expedientes en virtud de este subcapítulo y una copia de este subcapítulo.
			</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b)	En la etapa de liberación final de un menor de edad, o en la última medida oficial en el asunto si no hay sentencia, un oficial de libertad condicional o un oficial del Departamento de Justicia Juvenil de Texas, según corresponda, le dará al menor de edad y al padre, madre, tutor o custodio de este una explicación por escrito relacionada con la elegibilidad de los expedientes del menor de edad para sellarlos en virtud de este subcapítulo y una copia de este subcapítulo.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(c)	La explicación por escrito proporcionada al menor de edad en virtud de las Subsecciones (a) y (b) debe incluir los requisitos para que un expediente sea elegible para ser sellado, incluida una explicación de los expedientes que están exentos de ser sellados en virtud de la Sección 58.252 y la siguiente información:</p></td>
		</tr>
	</table>
</div>
<!--  Page 10 -->
<div class="body">
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"></td>
		</tr>
				
		
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1)	que, independientemente de si la conducta del menor de edad fue juzgada o no, el menor de edad tiene un expediente juvenil con el Departamento de Seguridad Pública y la Oficina Federal de Investigación;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2) el expediente juvenil del menor de edad es un expediente permanente a menos que esté sellado en virtud de este subcapítulo;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3)	a excepción de lo dispuesto en la Sección 58.260, un oficial de policía, alguacil, fiscal, oficial de libertad condicional, oficial de correccional u otro oficial de justicia penal o juvenil puede acceder al expediente juvenil del menor de edad, que no sea un expediente de tratamiento confidencial por ley, a menos que el expediente se selle según lo dispuesto en este subcapítulo;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(4)	sellar los expedientes del menor de edad en virtud de la Sección 58.253 o la Sección 58.255, según corresponda, no requiere de ninguna acción por parte del menor de edad o la familia de este, incluida la presentación de una solicitud o la contratación de un abogado, pero ocurre automáticamente a los 18 o 19 años según corresponda según el historial de derivación y sentencia del menor de edad;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(5)	el expediente juvenil del menor de edad puede ser elegible para una fecha de sellado anticipada en virtud de la Sección 58.256, pero un sellado anticipado requiere que el menor de edad o el abogado de este presente una solicitud ante el tribunal;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(6)	el impacto de sellar expedientes en el menor de edad y</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(7)	las circunstancias bajo las que se puede reabrir un expediente sellado.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(d)	El Departamento de Justicia Juvenil de Texas adoptará reglas para implementar esta sección y para facilitar la explicación efectiva de la información requerida para ser comunicada en esta sección.</p></td>
		</tr>
		<tr align="center" class="margin-adj-statutes">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0"><!-- ============PLACE HOLDER - MARGIN============== --> </p></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.263. DESTRUCCIÓN DE EXPEDIENTES: SIN CAUSA PROBABLE.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">El tribunal ordenará la destrucción de los expedientes relacionados con la conducta por la que un menor de edad es detenido o remitido a un tribunal de menores sin ser detenido, incluidos los expedientes del sistema de información de justicia juvenil, si:</p></td>
		</tr>		
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1)	se determina en virtud de la Sección 53.01 que no existe causa probable para creer que el menor de edad participó en la conducta y el caso no se deriva al fiscal para revisión en virtud de la Sección 53.012; o</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2)	es determinado por el fiscal en virtud de la Sección 53.012 que no existe causa probable para creer que el menor de edad participó en la conducta.</p></td>
		</tr>
		<tr align="center" class="margin-adj-statutes">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0"><!-- ============PLACE HOLDER - MARGIN============== --> </p></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.264. DESTRUCCIÓN PERMITIDA DE EXPEDIENTES.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(a) Sujeto a las Subsecciones (b) y 
			(c) de esta sección, la Sección 202.001 del Código de Gobierno Local y a cualquier otra restricción impuesta por las pautas de retención de expedientes de 
			una entidad, los siguientes pueden autorizar la destrucción de los expedientes en un asunto juvenil cerrado, independientemente de la fecha en que se crearon 
			los expedientes:</p></td>
		</tr>		
				<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1)	una junta de menores, en relación con los expedientes en posesión del departamento de libertad condicional juvenil;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2)	el jefe de una agencia de autoridades policiales, en relación con los expedientes en posesión de la agencia; y
		</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3)	un fiscal, en relación con los expedientes en posesión de la oficina del fiscal.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(b)	Los expedientes relacionados con una persona derivada al departamento de libertad condicional juvenil pueden ser destruidos si la persona:</p></td>
		</tr>
	</table>
</div>
<!--  Page 10 -->
<div class="body">
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"></td>
		</tr>
		
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1)	tiene al menos 18 años, y:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(A) la conducta más grave por la que se derivó a la persona fue una conducta que indicó una necesidad de supervisión ya sea que la persona haya sido juzgada o no; o</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(B) la derivación o información no se relaciona con la conducta que indique una necesidad de supervisión o conducta delictiva y el departamento de libertad condicional juvenil, el fiscal o el tribunal de menores no tomaron medidas sobre la derivación o la información por esa razón;</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2)	tiene al menos 21 años, y:</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(A) la conducta más grave por la que la persona fue juzgada fue una conducta delictiva que violó una ley penal del grado de un delito menor; o</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td align="left" width="70%"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(B) la conducta más grave por la que se derivó a la persona fue una conducta delictiva y no se determinó que la persona participó en la conducta; o</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(3)	tiene al menos 31 años y la conducta más grave por la que la persona fue juzgada fue una conducta delictiva que violó una ley penal del grado de un delito grave.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(c)	Si los expedientes contienen información relacionada con más de una persona derivada al departamento de libertad condicional juvenil, el expediente solo puede ser destruido si:</p></td>
		</tr>		
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(1)	la destrucción del expediente está autorizada en virtud de esta sección; y</p></td>
		</tr>		
		<tr align="center">
			<td align="left" width="10%"></td>
			<td align="left" width="10%"></td>
			<td colspan="2"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(2)	la información en el expediente que puede ser destruida en virtud de esta sección puede ser separada de la información que no está autorizada para ser destruida.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(d)	Los expedientes electrónicos se consideran destruidos si se eliminan, incluido el índice de los expedientes.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(e)	La conversión de expedientes físicos a electrónicos y, posteriormente, la destrucción de los expedientes físicos mientras se mantienen los expedientes electrónicos no se considera destrucción de un expediente en virtud de este subcapítulo.</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(f)	Esta sección no autoriza la destrucción de los expedientes del tribunal de menores o del secretario del tribunal.
			</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(g)	Esta sección no autoriza la destrucción de los expedientes gestionados con fines estadísticos y de investigación por parte del Departamento de Justicia Juvenil de Texas en un sistema de administración de casos e información juvenil autorizado en virtud de la Sección 58.403.
		</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">(h)	Esta sección no afecta la destrucción de los expedientes físicos y los archivos autorizados por el Programa de Retención de Expedientes de la Biblioteca del Estado de Texas.</p></td>
		</tr>
		<tr align="center" class="margin-adj-statutes">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0"><!-- ============PLACE HOLDER - MARGIN============== --> </p></td>
		</tr>
		<tr align="center">
			<td colspan="4"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Sec. 58.265. EXPEDIENTES JUVENILES NO SUJETOS A ELIMINACIÓN</p></td>
		</tr>
		<tr align="center">
			<td align="left" width="10%"></td>
			<td colspan="3"><p class="arial-font-10" align="justify" padding-top="0" padding-bottom="0">Los expedientes a los que se aplica este capítulo no están sujetos a una orden de eliminación emitida por ningún tribunal.</p></td>
		</tr>
	</table>
</div>
</body>
</pdf>
