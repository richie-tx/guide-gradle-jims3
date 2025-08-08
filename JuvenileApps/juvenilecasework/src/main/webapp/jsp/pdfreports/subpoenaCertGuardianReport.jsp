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
<jsp:useBean id="reportInfo" class="ui.juvenilecase.districtCourtHearings.SubpoenaReportBean" scope="session" />
<pdf onload="pdf:130">
<head>
	<logic:equal name="courtHearingForm" name="queryString" value="F">
		<meta name="title" value="<%=PDFReport.SUBPOENA_CERT_GUARD_FATHER.getReportName()%>"/>
		<meta name="subject" value="<%=PDFReport.SUBPOENA_CERT_GUARD_FATHER.getReportName()%>"/>
	</logic:equal>
	<logic:equal name="courtHearingForm" name="queryString" value="M">
		<meta name="title" value="<%=PDFReport.SUBPOENA_CERT_GUARD_MOTHER.getReportName()%>"/>
		<meta name="subject" value="<%=PDFReport.SUBPOENA_CERT_GUARD_MOTHER.getReportName()%>"/>
	</logic:equal>
	<logic:equal name="courtHearingForm" name="queryString" value="O">
		<meta name="title" value="<%=PDFReport.SUBPOENA_CERT_GUARD_OTHER.getReportName()%>"/>
		<meta name="subject" value="<%=PDFReport.SUBPOENA_CERT_GUARD_OTHER.getReportName()%>"/>
	</logic:equal>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
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
body { size:Legal; padding:0.5in; }
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
P {line-height:140%; }
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
td.helvetica-font-9
	{font-size:9;
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
				 	<td align="center" width="85%">
						*${reportInfo.trackingNum}
					</td>
				 </tr>
				</table>
			</div>
		</macro>		
	</macrolist>
</head>
<body footer="myfooter" footer-height="1mm">
<!--  Header information -->
<div class="header">
	<table width="100%" border-style="none" align="center">
		<tr>
			<td width="10%" valign="Top" align="left" class="helvetica-font-9">
				<p>PARENTS, GUARDIAN, OR <br/>CUSTODIAN OF CHILD</p>
			</td>
			<td class="helvetica-font-9" align="right" padding-top="2" padding-bottom="0" width="40%">
				<p>
					${reportInfo.courtIdWithSuffix} DISTRICT COURT <br/> ${reportInfo.courtLocation} <br/> 1200 CONGRESS, HOUSTON, TEXAS
				</p>
			</td>
		</tr>
	</table>	
</div>
<div class="body" page-break-before="avoid">
	<table width='100%' border="0">
	 		<tr>
	 			<td colspan="9" align="left" class="helvetica-font-9"><p><nobr>CONFIDENTIAL***   CONFIDENTIAL***   CONFIDENTIAL***   CONFIDENTIAL***   CONFIDENTIAL***  CONFIDENTIAL*** CONFIDENTIAL***</nobr></p></td>
	 		</tr>
	 		<tr>
				<td width="4%" valign="Top" align="left" class="helvetica-font-9">
					<p  padding-left="1.98in" text-indent="-0.1in">NO. ${reportInfo.petitionNum} ${reportInfo.reopenPetStatus}</p>
				</td>
				<td colspan="8" class="helvetica-font-9" align="right" padding-top="2" padding-bottom="0" width="40%">
					<p>
						<nobr>TR# ${reportInfo.trackingNum} </nobr>
					</p>
				</td>
			</tr>
			<tr>
	 			<td align="left" class="helvetica-font-9">
	 				<span>THE STATE OF TEXAS)<br/> <span padding-left="1.3in" text-indent="0.1in">
		 			><nobr><span padding-left=".5in" text-indent="0.1in">IN THE JUVENILE DISTRICT COURT OF HARRIS COUNTY TEXAS</span></nobr>
		 			</span></span>
		 			<nobr>COUNTY OF HARRIS)</nobr>
		 		</td>
	 		</tr>
	 		<tr>
	 			<td align="left" class="helvetica-font-9">
	 				<span>
	 					<br/><nobr>THE STATE OF TEXAS:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TO ${reportInfo.memberName}</nobr>
	 				</span>
	 			</td>
	 		</tr>
	 		<tr><td><p></p></td></tr>
	 		<tr>
	 			<td align="left" class="helvetica-font-9"><p><nobr>IN THE MATTER OF<span padding-left=".60in" text-indent="0.1in"> ${reportInfo.juvenileName}, RESPONDENT</span></nobr></p></td>
	 		</tr>
	 		<tr><td><p></p></td></tr>
	 		<tr>
	 			<td align="left" class="helvetica-font-9"><p><nobr>TO THE SHERIFF, ANY CONSTABLE OR JUVENILE PROBATION OFFICER OF THE STATE OF TEXAS GREETINGS:</nobr></p></td>
	 		</tr>
	 		<tr>
	 			<td class="helvetica-font-9" align="left">
	 				<p>
		 				YOU ARE HEREBY COMMANDED TO SUMMON ${reportInfo.memberName}<br/>
		 				<span padding-left="2.8in" text-indent="0.1in"><nobr>${reportInfo.memberAddress.streetAddress},&nbsp;${reportInfo.memberAddress.cityStateZip},&nbsp;${reportInfo.memberContact.contactPhoneNumber.formattedPhoneNumber}</nobr></span>
				 		<p style="line-height:220%">WHO IS HERE AND NOW NOTIFIED THAT THE STATE PRAYS THAT THE ${reportInfo.courtIdWithSuffix} DISTRICT COURT OF HARRIS COUNTY, TEXAS, CONDUCT A HEARING FOR THE PURPOSE OF
				 		 
				 		CONSIDERING WAIVER OF JURISDICTION OVER AND DISCRETIONARY TRANSFER TO CRIMINAL DISTRICT COURT JURISDICTION OF ONE ${reportInfo.juvenileName} UNDER THE PROVISIONS OF SECTION ${reportInfo.certCode},
				 		 
				 		TITLE III OF THE TEXAS FAMILY CODE. AND SAID RESPONDENT IF TO BE FOUND IN YOUR COUNTY, TO BE AND APPEAR IN THE ${reportInfo.courtIdWithSuffix} DISTRICT COURT OF HARRIS COUNTY, TEXAS,
				 		 
				 		TO BE HELD ON THE ${reportInfo.courtLocation}, HOUSTON, TEXAS, AND TO BRING WITH HIM IN PERSON THE ABOVE NAMED RESPONDENT, IF IN HIS CUSTODY,
				 		 
				 		ON THE ${reportInfo.courtDate} A.D., AT ${reportInfo.courtTime} / INSTANTER THEN AND THERE TO ANSWER THE PETITION AND MOTION TO WAIVE JURISDICTION OF ${reportInfo.plaintiffName}, PLAINTIFF, 
				 		
				 		IT BEING CAUSE NO. ${reportInfo.petitionNum}<logic:notEmpty name="reportInfo" property="reopenPetStatus">  ${reportInfo.reopenPetStatus}</logic:notEmpty> FILED HEREIN ON THE  <logic:notEmpty name="reportInfo" property="amendmentDate">${reportInfo.amendmentDate}</logic:notEmpty><logic:empty name="reportInfo" property="amendmentDate">${reportInfo.filingDate}</logic:empty> A.D. IN WHICH ${reportInfo.juvenileName} IS RESPONDENT,
				 		 
				 		WHEREIN THE SAID PETITIONER ALLEGES TO THE FOLLOWING FACTS, TO WIT:  ALL AS FULLY SET OUT IN THE ACCOMPANYING TRUE COPY OF THE PETITION AND MOTION TO WAIVE JURISDICTION OF SAID PLANTIFF TO THE OFFICER SERVING THIS CITATION:</p> 
			 		</p>
			 		<p text-indent="0.1in" style="line-height:150%">A TRUE COPY OF THIS WRIT, TOGETHER WITH A TRUE COPY OF THE PETITION, AND MOTION TO WAIVE JURISDICTION, YOU WILL DELIVER TO SAID PARENT, GUARDIAN OR OTHER PERSON HAVING CUSTODY OF THE SAID RESPONDENT.</p>
			 		<p style="line-height:250%">WITNESS: ${reportInfo.districtClerkName}, CLERK OF THE SAID COURT, AT OFFICE IN THE CITY OF HOUSTON, HARRIS COUNTY, TEXAS, <nobr>THIS ____ DAY OF ${reportInfo.preparationDate} A.D.</nobr></p>
			 		<p padding-left="3.1in" text-indent="0.1in">${reportInfo.districtClerkName}, DISTRICT CLERK, HARRIS COUNTY, TEXAS</p>
			 		<p padding-left="3in" text-indent="0.1in"><nobr>BY_________________________________________________DEPUTY</nobr></p>
			 		<p>
						" " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " R E T U R N " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " " "
					</p>
					<p>
						CAME TO HAND ON THE _____ DAY OF ____________________, 20  ____ A.D., AT ______O'CLOCK __M, AND EXECUTED BY DELIVERING TO EACH OF THE BELOW NAMED IN PERSON, A TRUE COPY OF THIS WRIT AT
						THE FOLLOWING TIMES AND PLACES, TO WIT:  IN PERSON, A TRUE COPY OF
					</p>
	 			</td>
	 		</tr>
			<tr>
				<td>
					<table>
						<tr>
							<td align="center" class="helvetica-font-9"><p>&nbsp;<br/>NAME</p></td>
							<td align="center"><p>*</p></td>
							<td align="center" class="helvetica-font-9"><p>DATE <br/> MO-DA-YR</p></td>
							<td align="center"><p>*</p></td>
							<td align="center" class="helvetica-font-9"><p>TIME <br/> HR:MM-?M</p></td>
							<td align="center"><p>*</p></td>
							<td align="center" class="helvetica-font-9"><p><nobr>PLACE, COURSE&nbsp;&amp;&nbsp;DISTANCE</nobr><br/>FROM COURT HOUSE</p></td>
							<td align="center"><p>*</p></td>
							<td align="center" class="helvetica-font-9"><p>MILEAGE</p></td>
						</tr>
						<tr>
							<td align="center"><p>________________</p></td>
							<td align="center"><p>*</p></td>
							<td align="center"><p>________________</p></td>
							<td align="center"><p>*</p></td>
							<td align="center"><p>________________</p></td>
							<td align="center"><p>*</p></td>
							<td align="center"><p>________________</p></td>
							<td align="center"><p>*</p></td>
							<td align="center"><p>________________</p></td>
						</tr>
						<tr>
							<td align="center"><p>________________</p></td>
							<td align="center"><p>*</p></td>
							<td align="center"><p>________________</p></td>
							<td align="center"><p>*</p></td>
							<td align="center"><p>________________</p></td>
							<td align="center"><p>*</p></td>
							<td align="center"><p>________________</p></td>
							<td align="center"><p>*</p></td>
							<td align="center"><p>________________</p></td>
						</tr>
					</table>
				</td>
			</tr>			
			
			<tr>
				<td class="helvetica-font-9">
					<p>=================================================*==========*===========================================</p>
					<p><nobr>REASON FOR NON SERVICE:_______________________________________________________________________________________</nobr></p>
					<p><nobr>________________________________________________________________________________________________________________</nobr></p>
					<p text-indent="0.1in">&nbsp;&nbsp;I ACTUALLY AND NECESSARILY TRAVELED______MILES IN THE SERVICE OF THIS WRIT, IN ADDITION TO ANY OTHER MILES I MAY HAVE TRAVELED IN THE SERVICE OF OTHER PROCESS IN THE SAME CASE DURING THE SAME TRIP.</p>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<td></td>
							<td></td>
							<td class="helvetica-font-9" align="center" padding-top="2" padding-bottom="0" width="50%">
								<p padding-left="1in" text-indent="0.1in"><nobr>${reportInfo.constablePct1}</nobr></p>
							</td>
						</tr>
						<tr>
							<td></td>
							<td align="left" class="helvetica-font-9">
								<p><nobr>:SERVING______COP______$__________</nobr></p>
							</td>
							<td colspan="4" class="helvetica-font-9" align="right">
								<p><nobr>CONSTABLE. PCT. #1, HARRIS COUNTY, TEXAS</nobr></p>
							</td>
						</tr>
						<tr>
							<td align="left" class="helvetica-font-9"> 
								<p><nobr>FEES:</nobr></p>
							</td>
							<td align="left" class="helvetica-font-9"> 
								<p><nobr>MILEAGE__________MILES $__________</nobr></p>
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td align="left" class="helvetica-font-9">
								<p><nobr>:TOTAL....................................$__________</nobr></p>
							</td>
							<td class="helvetica-font-9" align="right" padding-top="2" padding-bottom="0" width="50%">
								<p padding-left="1in" text-indent="0.1in"><nobr>BY___________________________________DEPUTY</nobr></p>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td width="10%" valign="Top" align="left" class="helvetica-font-9">
					<p>NOTES:</p> 
				</td>
			</tr>
	  	</table>
</div>
</body>
</pdf>
