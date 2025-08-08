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
<jsp:useBean id="reportInfo" class="ui.juvenilecase.programreferral.ExhibitBPrintBean" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.EXHIBITB_REPORT.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.EXHIBITB_REPORT.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
	<meta name="base" value="file:c:/BFOImages/" /> 
<style>
	

	body
	{	margin-left: .1in;
    	margin-right: .1in;
		margin-top: .1in; 
		margin-bottom: .1in;
		font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left 		bottom: 2px;}

	.petitions, .programServices, .circumstances {
		margin-left: 30px;
		margin-right: 30px;
	}
	.petitions{
		margin-top: 30px;
	}
	.answer {
		margin-left: 30px;
	}

	.circumstances{
		margin-top: 15px;
	}


	.check {
		margin-bottom: -12px;
  		display: inline-block;
  		rotate: 45deg;
 		height: 10px;
  		width: 6px;
  		border-bottom: 1px solid black;
  		border-right: 1px solid black;
	}

	.underLine {
		margin-top: -30px;
		margin-left: -10px;
	}
</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				 <tr>
				 	<td align="center" width="100%">
						Page <pagenumber/> of <totalpages/>
					</td>
				 </tr>
				</table>
			</div>
		</macro>		
	</macrolist>
</head>
<body footer="myfooter" footer-height="5mm">
	<div class="header" align="center">
		<table width="100%" border-style="none">
				<tr align="center">
					<td width="80%" align="center"> 
					<logic:notEmpty  name="reportInfo" property="selectedPetitions">
						<p class="arial-font-10">
							IN THE <b>${reportInfo.courtId}th</b> DISTRICT/COUNTY COURT
						</p>
					</logic:notEmpty>
					<logic:empty  name="reportInfo" property="selectedPetitions">
						<p class="arial-font-10">
							DETENTION COURT
						</p>
					</logic:empty>
					</td>
				</tr>
				<tr align="center">
					<td width="80%" align="center"> <p class="arial-font-10">
					HARRIS COUNTY</p></td>
				</tr>
				<tr align="center">
					<td width="80%" align="center"> <p class="arial-font-10">
					STATE OF TEXAS</p></td>
				</tr>
				<tr></tr>
		</table>
	</div>
	<div class="petitions">
		<table width="100%">
			<tr>
				<td width="65%">
					<p>IN THE MATTER OF: <b>${reportInfo.juvenileName}</b></p>
				</td>
				<td width="35%" >
					<p>NO:
						<logic:notEmpty  name="reportInfo" property="selectedPetitions">
							<bean:write name="reportInfo" property="selectedPetitions" />
						</logic:notEmpty>
					</p>
				</td>
			</tr>
		</table>
	</div>
	<div align="center">
			<p ><b>EXHIBIT B</b></p>
	</div>
	<div class="programServices">
		<div>
			<p>
				The court finds that reasonable efforts have been made to prevent or eliminate the need for the child to be removed from (his or her) home. The following services and/or programs were provided:
			</p>
		</div>
		<div>
				<logic:notEmpty name="reportInfo" property="programs">
				
					<p><u>&nbsp;&nbsp;X&nbsp;&nbsp;&nbsp;</u> The child and/or family was previously referred to the following community, court, or educational programs: </p>
					<p class="answer">
							<logic:notEmpty  name="reportInfo" property="selectedPrograms">
								<bean:write name="reportInfo" property="selectedPrograms" />
							</logic:notEmpty>
					</p>
				</logic:notEmpty>
				<logic:empty name="reportInfo" property="programs">
					<p>____ The child and/or family was previously referred to the following community, court, or educational programs: </p>
				</logic:empty>
		</div>
		<div>
				
					<logic:notEmpty name="reportInfo" property="services">
						<p>
							<u>&nbsp;&nbsp;X&nbsp;&nbsp;&nbsp;</u> The child and/or family was previously referred to the following counseling or psychological services:
						</p>
						<p class="answer" >
							<logic:notEmpty  name="reportInfo" property="selectedServices">
								<bean:write name="reportInfo" property="selectedServices" />
							</logic:notEmpty>
						</p>
					</logic:notEmpty>
					<logic:empty name="reportInfo" property="services">
						<p>____ The child and/or family was previously referred to the following counseling or psychological services:</p>
					</logic:empty>	
		</div>
	</div>
	<div class="circumstances">
		<div>
			<table>
				<tr>
					<logic:notEmpty name="reportInfo" property="listServices">
						<td>
							<div  class="check"></div><p class="underLine">____</p>
						</td>
					</logic:notEmpty>
					<logic:empty name="reportInfo" property="listServices">
						<td>
							<p class="underLine">____</p>
						</td>
					</logic:empty>
					<td>
						<p>
							The child and /or family is receiving or has previously received services from TDFPS or the Harris Center <span color="red">(LIST SERVICES)</span>
						</p>
					</td>
				</tr>
			</table>
			
			<p class="answer">
				${reportInfo.listServices}
			</p>
		</div>
		<div>
			<table>
				<tr>
					<logic:notEmpty name="reportInfo" property="childRemoval">
						<td>
							<div class="check"></div><p class="underLine">____</p>
						</td>
					</logic:notEmpty>
					<logic:empty name="reportInfo" property="childRemoval">
						<td>
							<p class="underLine">____</p>
						</td>
					</logic:empty>
					<td>
						<p>
							The nature of the circumstances in the child's home, which may include the offense, required the child's removal.
						</p>
					</td>
				</tr>
			</table>
			<p class="answer">
				${reportInfo.childRemoval}
			</p>
		</div>
		<div>
			<table>
				<tr>
					<logic:notEmpty name="reportInfo" property="listAndExplain">
						<td>
							<div class="check"></div><p class="underLine">____</p>
						</td>
					</logic:notEmpty>
					<logic:empty name="reportInfo" property="listAndExplain">
						<td>
							<p class="underLine">____</p>
						</td>
					</logic:empty>
					<td>
						<p>
							<span color="red">LIST AND EXPLAIN</span> any other specific circumstances not addressed above.
						</p>
					</td>
				</tr>
			</table>
			<p class="answer">
				${reportInfo.listAndExplain}
			</p>
		</div>
	</div>
	<div style="margin-left:80px; font-size:9;">
		<div>
			<p>
			Signed this _____________________ day of___________________, 20___.
			</p>
		</div>
		<div style="margin-left:230px;">
			<p>
			________________________________________
			</p>
			<p>
				Juvenile Judge Presiding
			</p>
		</div>
	</div>
</body>
</pdf>