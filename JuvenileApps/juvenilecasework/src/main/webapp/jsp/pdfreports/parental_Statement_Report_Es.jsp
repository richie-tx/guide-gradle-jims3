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
<jsp:useBean id="reportInfo" class="messaging.interviewinfo.to.ParentalStatementReportDataTO" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.PARENTAL_STATEMENT_REPORT_ES.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.PARENTAL_STATEMENT_REPORT_ES.getReportName()%>"/>
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
		margin-bottom: .0in;
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
td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
p.arial-font-8
	{font-size:8;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
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
</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				 <tr>
				 	<td align="left" width="70%">
						Declaracion Por Escrito  De Padres - Pagina <pagenumber/> de <totalpages/>
					</td>
					<td align="left" width="30%">
						TJPC-AGE-10-04
					</td>
				 </tr>
				</table>
			</div>
		</macro>		
	</macrolist>
</head>
<body footer="myfooter" footer-height="5mm">		
<!--  Header information -->
<div class="body">
	<table width="100%" border-style="none" padding-top="5" padding-bottom="5">
		<tr align="center">
			<td align="center" width="54%">
				<p align="center" class="arial-font-10"><b>Instrucci&#243;nes Para Completar La Declaraci&#243;n Por Escrito De Padres</b></p>
				<p align="left" class="arial-font-8">
                	El C&#243;digo de la Familia de Texas, Secci&#243;n 61.104, requiere que los padres de un joven que esta acusado de 
                	cometer un delito en contra de la ley y que debe aparecer ante una corte juvenil del estado de Texas tengan la oportunidad 
                	de dar una declaraci&#243;n por escrito sobre su hijo.
                </p>
                <p align="left" class="arial-font-8">
                	La declaraci&#243;n por escrito de padres es su oportunidad de informale a la corte sobre las necesidades de su hijo y de 
                	su familia y puede inclurir las calidades positivas y otras cosas importantes en cuestion de el caso de su hijo.
                	<span>&#160; </span>El Juez puede considerar esta declaraci&#243;n al hacer su decision final.
                </p>
                <p align="left" class="arial-font-8">
                	Una declaraci&#243;n por escrito de padres puede ser sometido cuando los siguentes documentos legales que han sido 
                	entregados a la corte juvenil en el caso de su hijo.
                </p>
                <p align="left" class="arial-font-8">
                	- Una petici&#243;n [acusacion formal escrito] para una audiencia de adjudicaci&#243;n;<br/>
                	- Un moci&#243;n o petici&#243;n para audiencia para modificar las condiciones de libertad condicional;<br/>
                	- Un moci&#243;n o petici&#243;n para una audiencia con el proposito de tranferir con 
                	discreci&#243;n a su hijo a la corte de adultos donde sera juzgado como adulto.<br/>
                	Cualquier informaci&#243;n que usted proporciona, <b>NO</b> sera usada en la audencia de adjudicaci&#243;n que es una 
                	audencia para determiner si su hijo a cometio la ofensa.<span>&#160; </span>Puede ser sin embargo usado en la 
                	disposici&#243;n del caso que pueda asistir a la corte para ser una determinaci&#243;n final en el caso de su hijo que 
                	puede determiner lo siguiente:
                </p>
                <p align="left" class="arial-font-8">
                	- Poner su hijo en libertad condicional en su casa;<br/>
                	- Poner su hijo bajo libertad condicional fuera de su casa con parientes o otras personas.<br/>
                	- Poner su hijo bajo libertad condicional en una facilidad residencial securo o sin securidad para jovenes, o<br/>
                	- Poner su hijo en la custodia del Departamento De Justicia Para Menores Infractores De Texas 
                		[Texas Juvenile Justice Department]<br/>
                	Si los dos padres residen juntos en la misma casa con su hijo, solamente una declaraci&#243;n por escrito por familia 
                	puede ser sometido. Si usted es divorciado o separado, entonces los dos padres pueden someter una declaraci&#243;n 
                	separados.
                </p>
                <p align="left" class="arial-font-8">
                	<u>Una declaraci&#243;n por escrito de padres es completamente voluntario y usted puede considerar de <i>no</i> 
                	someter una declaraci&#243;n con respecto del caso de su hijo.</u> Usted puede escoger de <i>no </i>responder a 
                	algunas o todas de las secciones en la forma.  Aunque se presentan ejemplos, usted como padre no esta limitada a esos 
                	ejemplos.  Su declaraci&#243;n en su totalidad sera presentada al juez, el departamento de libertad condicional de 
                	juvenil, el abogado que representa a su hijo, el fiscal y cualquier persona que la corte juvenil determina si debera 
                	tener una copia.
                </p>
                <p align="left" class="arial-font-8">
	                Cualquier informaci&#243;n que usted entrega a la corte puede ser usada en contra de su hijo; asi que, usted debera 
	                consultar con un abogado sobre las consequencias si presenta su declaracion a la corte juvenil.  Si usted, sin embargo 
	                desea someter una declaracion, lo puede someter en su propio papel o usar cualquier forma que desea. Puede a la ves 
	                agregar otras paginas o escoger su propio forma si necesita mas espacio. Cuando su declaraci&#243;n esta completa, 
	                por favor de entregarlo al departamento de libertad condicional de juvenil al domicio mencionado abajo. Si entrega su 
	                declaraci&#243;n al departamento de libertad condicional, ese departamento entregara su declaraci&#243;n a la corte juvenil.
                </p>
                <p align="left" class="arial-font-8">
                	Si usted tiene preguntas en cuestion de completer su declaraci&#243;n por escrito de padres, usted puede ponerse en contacto 
                	con el departamento de libertad condicional de juvenil mencionado abajo.  Estando la declaracion por escrito de padres 
                	completa, por favor de enviarlo por correo o entregarlo personalmente el dia necesitada o antes, al departmento de libertad 
                	condicional.
                </p>
                <p align="center" class="arial-font-8">
                	<%=reportInfo.getLocationUnitName()%><br/>
                	<%=reportInfo.getLocationAddress1()%><br/>
                	<%=reportInfo.getLocationAddress2()%><br/>             
                	<%=reportInfo.getFormattedPhoneNumber()%><br/>
                </p>
                <p align="left" class="arial-font-8">
                	<i>Si se necesita mas copias de estas instrucci&#243;nes o de la declaraci&#243;n por escrito de padres, puede solicitarlas 
                	al departamento de libertad condicional de juvenil. Este formulario tambien se puede encontrar en la pagina de internet de 
                	la Comisi&#243;n de Libertad Condicional de Juvenil de Texas en <u>www.tjpc.state.tx.us</u>.</i>
                </p>		
			</td>
			<td align="center" width="46%" border="2">		
				<p align="center" class="arial-font-10">
					<b>T&#233;rminos Com&#250;nes Usados En <br /> El Sistema De Justicia Juvenil</b>
				</p>
				<p align="left" class="arial-font-8">
                	<b>Audencia de Adjudicaci&#243;n</b> - Una audencia donde el juez o jurado determina si su hijo cometio la ofensa del cual 
                	se lo acusa o es inocente de los cargos.
                </p>
                <p align="left" class="arial-font-8">
                	<b>Abogado Defensor</b> - Un abogado que representa su hijo y que asegura que los derechos de su hijo estan protegidos.  
                	La Corte Juvenil debera nombrar un abogado que represente a su hijo si la Corte determina que usted como padre, no pueda 
	                emplear un abogado por causas economicas.
                </p>
                <p align="left" class="arial-font-8">
                	<b>Una Audencia Para Transferir con Discreci&#243;n</b> - Una audencia donde el juez decide si debera transferir el caso 
                	de su hijo a una corte criminal donde su hijo sera juzgado como adulto.
                </p>
                <p align="left" class="arial-font-8">
                	<b>Audencia de Disposici&#243;n</b> -  Una audencia que tiene lugar despues de la audencia de adjudicaci&#243;n en la cual 
                	el juez o el jurado hace la decisi&#243;n final con respecto al caso de su hijo y donde  pueda recibir libertad condicional 
                	o internaci&#243;n en la Comisi&#243;n de Jovenes de Texas.
                </p>
                <p align="left" class="arial-font-8">
                	<b>Audencia Para Modificar</b> - Una audencia para cambiar las condiciones previas de la libertad condicional de su hijo o 
                	para revocar la libertad condicional por dichos violaci&#243;nes.
                </p>
                <p align="left" class="arial-font-8">
                	<b>Petici&#243;n / Moci&#243;n</b> - Un documento legal escrito archivado por el fiscal que le informa a usted y su hijo del 
                	delito, que segun se alega cometio, dando la fecha, tiempo y el lugar para una audencia delante de la corte en el futuro. 
                	Usted y su hijo deberan recibir una copia de este documento.
                </p>
                <p align="left" class="arial-font-8">
                	<b>Fiscal/Procurador</b>- Abogado acusador que trabaja para el condado, el districto, o las fiscalias/procuradores criminal 
                	que manejan casos de juveniles. Estas personas se conocen como fiscal/procurador del districto, fiscal/procurador del condado 
                	o asistentes del fiscal/procurador del Condado.
                </p>
                <p align="left" class="arial-font-8">
                	<b>Departamento De Justicia Para Menores Infractores De Texas [Texas Juvenile Justice Department]</b> - Una agencia estatal 
                	que opera facilidades correcci&#243;nales de securidad para jovenes internados por la Corte Juvenil.
                </p>
    		</td>
		</tr>
	</table>
</div>
<div class="body">
	<table width="100%" border-style="none"> 
		<tr padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10"><b>POR FAVOR DE COMPLETAR ESTA FORMA Y ENVIARLO AL DEPARTAMENTO DE LIBERTAD CONDICIONAL ESTE DIA
				<%=reportInfo.getCourtDate()%> O ANTES.</b></p>
			</td>
		</tr>
		<tr padding-bottom="10">
			<td align="center" colspan="3">		
				<p class="arial-font-10"><b>CAUSA NUMERO: <%=reportInfo.getPetitionNumber()%></b></p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" width="49%">		
				<p class="arial-font-10"><b>EN EL INTERES DE:</b></p>
    		</td>
    		<td align="left" width="2%">		
				<p class="arial-font-10"><b>.</b></p>
    		</td>
    		<td align="left" width="49%">		
				<p class="arial-font-10"><b>EN JUZGADO <%=reportInfo.getCourtName()%></b></p>
    		</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" width="49%">		
				<p class="arial-font-10"><b><%=reportInfo.getJuvenileName()%></b></p>
    		</td>
    		<td align="left" width="2%">		
				<p class="arial-font-10"><b>.</b></p>
    		</td>
    		<td align="left" width="49%">		
				<p class="arial-font-10"><b>EN EL CONDADO DE HARRIS, TEXAS</b></p>
    		</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" width="49%">		
				<p class="arial-font-10"><b>UN JUVENIL</b></p>
    		</td>
    		<td align="left" width="2%">		
				<p class="arial-font-10"><b>.</b></p>
    		</td>
    		<td align="left" width="49%">		
				<p class="arial-font-10"><b></b></p>
    		</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="center" colspan="3">		
				<p class="arial-font-10"><b>DECLARACI&#211;N POR ESCRITO DE PADRES</b></p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-9">Yo ________________________________________________________________________ padre o persona 
		        responsable  del joven mencionada en este documento, entrego esta declaraci&#243;n como esta autorizada bajo el Codigo de 
		        Texas de la Familia, Secci&#243;n 61.104. Yo quisiera proporcionar la siguiente informaci&#243;n para que la Corte tenga 
		        mis pensamientos para un major entendimiento de las necesidades y potencias de mi hijo, nuestra familia y cualquier 
		        information importante para la disposici&#243;n de este caso.
			    </p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-9">1.	<b>Historia.</b>  La Corte debe saber la siguiente informaci&#243;n sobre el pasado de mi hijo. 
		        <i>[use este espacio para describir informacion, como eventos importantes, como problemas de drogas y situaci&#243;nes 
		        deficiles que usted cree que contribuyen a las circumstancias que hoy se encuentra su hijo].</i> 
			    </p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="5">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-9">2.	<b>Medico y Psicologico.</b>  La Corte deberia saber los siguientes tratamientos medicos y 
		        historia psicologica de mi hijo. <i>[use este espacio para describir cualquier condici&#243;n medica, enfermedades, 
		        disabilidades fisicas, necesidades de medicinas, historia psicologica, tratamientos psicologicas o terapia, de haber 
		        recibido consejos professional o historia de suicidio, etc.]</i>
			    </p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="5">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
	</table>
</div>
<div class="body">
	<table width="100%" border-style="none"> 
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-9">3.	<b>Educaci&#243;n.</b>  La Corte deberia saber la siguiente informaci&#243;n sobre la historia de la 
		        educaci&#243;n de mi hijo. <i>[use este espacio para describir la informaci&#243;n de los exitos o problemas que su hijo haya 
		        experimentado en la escuela, incluyendo asistencia en la escuela, grados, educaci&#243;n especial, problemas de diciplina, 
		        conflictos, o cosas extras en el plan de estudios, premios, deportes, habilidades especiales y trabajo, etc.]</i>
			    </p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="5">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-9">4.	<b>Medio Ambiente En La Casa.</b> El medio ambiente en la casa y situaci&#243;nes de la familia seria 
        		mas bien discrito de la siguiente manera. <i>[use este espacio para informarle a la Corte sobre el medio ambiente que existe en 
        		la casa de su hijo y decirle a la Corte como se porta su hijo con usted.  Tambien, como su hijo se porta con sus hermanos o 
        		cualquier otra persona que vive tambien en la casa.  Si su hijo cumple con el quehacer domestico o otros trabajos alrededor de la 
        		casa y si hay problemas de conducta o disciplina. Por favor de explicar si su hijo trabaja ayudando a la familia economicamente, 
        		y si su vecindad influye positivamente o negativamente.</i> 
			    </p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="5">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-9">5.	<b>Supervisi&#243;n.</b>  Si la Corte pone mi hijo bajo mi supervisi&#243;n, yo hare todo lo posible
				que mi hijo no tenga mas problemas mientras este bajo libertad condicional. <i>[use este espacio para decirle a la corte que 
				habilidad tiene como padres de supervisar a su hijo, y informar si hay otros adultos competentes de ayudales en la supervisi&#243;n 
				de su hijo/hija.  A la vez, explicar si va a tener problemas con la supervision; su plan de asistir al departamento de libertad 
				condicional en asegurar  que su hijo vaya a la escuela y complir con los  deseos del departmento de libertad condicional.</i>
			    </p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="5">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
	</table>
</div>
<div class="body">
	<table width="100%" border-style="none"> 
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-9">6.	<b>Cosas Positivas de su Hijo.</b>  La Corte deberia saber las cosas buenas de su hijo.  <i>[use 
				este espacio para darle informaci&#243;n al juez sobre exitos especiales, caracteristicas peculiares, trabajos que haya hecho 
				voluntariamente, trabajos pasados, clubs, organizaci&#243;nes, comunidad o de la Iglesia en lo cual su hijo haya participado].
				</i></p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="5">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-9">7.	<b>Resultados Recomendado.</b>  Cuando la Corte considera los resultados finales del caso de mi 
		        hijo, yo quisiera que el juez ordenara lo siguiente: [use este espacio para decirle al juez lo que usted piensa de que le 
		        deberia de pasar a su hijo en este caso.  En su opinion, que necesidades tiene su hijo. Tambien deberia discutir que le paseria 
		        a su familia si su hijo fuera internado afuera de su casa y de su costodia.
			    </p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-9">8.	<b>Informaci&#243;n Adicional.</b> La Corte deberia saber lo siguiente informacion importante que 
		        ayudaria a la Corte en hacer una decisi&#243;n final. <i>[use este espacio para proporcionarle al juez cualquier informaci&#243;n 
		        que usted siente que pudiera ejercer influencia al juez cuando haga una decision final.</i>
			    </p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-10">________________________________________________________________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="3">		
				<p class="arial-font-9">Toda la informaci&#243;n que yo he proporcionado a la Corte en esta declaracion por escrito de padres 
				es verdadero y correcto de la major manera de mis conocimientos y creencia.
			    </p>
			</td>
		</tr>
		<tr align="center" >
			<td align="left" colspan="2">		
				<p class="arial-font-10">_________________________________________</p>
			</td>
			<td align="left">		
				<p class="arial-font-10">_________________________________________</p>
			</td>
		</tr>
		<tr align="center" padding-bottom="10">
			<td align="left" colspan="2">		
				<p class="arial-font-9">Firma del Padre o otra Persona</p>
			</td>
			<td align="left">		
				<p class="arial-font-9">Fecha cuando fue firmado</p>
			</td>
		</tr>
		<tr align="center" >
			<td align="left" colspan="2">		
				<p class="arial-font-10">_________________________________________</p>
			</td>
			<td align="left">		
				<p class="arial-font-10"></p>
			</td>
		</tr>
		<tr align="center" padding-bottom="0">
			<td align="left" colspan="2">		
				<p class="arial-font-9">Nombre del Padre o otra Persona</p>
			</td>
			<td align="left">		
				<p class="arial-font-10"></p>
			</td>
		</tr>
	</table>
</div>
</body>
</pdf>