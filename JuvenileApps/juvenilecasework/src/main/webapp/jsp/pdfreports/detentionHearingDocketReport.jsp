<?xml version="1.0"?>
<!DOCTYPE pdf PUBLIC "-//big.faceless.org//report" "report-1.1.dtd">
<%@page language="java" contentType="text/xml; charset=UTF-8"%>
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
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<jsp:useBean id="reportInfo" class="ui.juvenilecase.detentionCourtHearings.DetentionHearingDocketBean" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.DETENTION_HEARING_DOCKET.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.DETENTION_HEARING_DOCKET.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
		<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style>
body
	{
		size:A4-landscape;
		margin-left: .05in;
    	margin-right: .05in;
		margin-top: .05in; 
		margin-bottom: .05in;
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
td.times-font-14
	{font-size:14;
		font-family:"Times New Roman", Times, serif;
		font-weight: bold;
		line-height: 80%;}

td.times-font-8
	{font-size:8;
		font-family:"Times New Roman", Times, serif;		
		color:#707070;}

td.helvetica-font-16
	{font-size:16;
		font-family: Arial, Helvetica, sans-serif;
		text-align:center;}
		
td.arial-font-12
	{
		font-size:12;
		font-family:"Arial"", Helvetica, sans-serif;
		text-align:center;
	}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.times-font-10
	{font-size:10;
		font-family:"Times New Roman", Times, sans-serif;
		text-align:center;}
p.times-font-12
	{font-size:12;
		font-family:"Times New Roman", Times, sans-serif;
		text-align:center;}

p.times-font-9
	{font-size:9;
		font-family:"Times New Roman", Times, serif;text-align:left;
		}

p.times-font-8
	{font-size:8;
		font-family:"Times New Roman", Times, serif;text-align:left; }
p.times-font-7
	{font-size:7;
		font-family:"Times New Roman", Times, serif;text-align:left; }

p.arial-font-8
	{font-size:8;
		font-family:"Arial", Helvetica, sans-serif;}
p.times-font-8-bold
	{font-size:8;
		font-family:"Times New Roman", Times, sans-serif;
		font-weight: bold;
		text-decoration:underline;
		text-align:left;}
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

span.arial-font-10
{font-size:8;
	font-family:"Times New Roman", Times, sans-serif;text-align:center;}

span.arial-font-9
{font-size:7;
	font-family:"Times New Roman", Times, sans-serif;text-align:center;}

span.times-font-9{
		font-size:6;
		font-family:"Times New Roman", Times, serif;
		font-style: Italic;
		color:#A8A8A8;
}
nobr { white-space:nowrap; }
.description {
  color: #fff;
}

.description em {
  color: #A8A8A8;
}


</style>
	<macrolist>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				 <tr>
				 	<td align="center" width="98%">
						Page <pagenumber/> of <totalpages/>
					</td>
				 </tr>
				</table>
			</div>
		</macro>	
		<macro id="myheader">
			<div class="header" align="center">
				<table  border-style="none" width="100%">
					<tr align="center" page-break-before="avoid">
						<td align="center"><p class="times-font-12"><b>DETENTION HEARING DOCKET, COURT: ${reportInfo.refereeCourt} -  CALENDAR FOR ${reportInfo.hearingDate}</b></p></td>
					</tr>
					<tr align="center" page-break-before="avoid">
						<td align="center"><p class="times-font-12"><b>HONORABLE <bean:write name="detentionHearingForm" property="assignedJudge"/> PRESIDING</b></p></td>
					</tr>
					<tr page-break-before="avoid">
						<td align="center"><p class="times-font-12"><b>TIME:${reportInfo.hearingTime}</b></p></td>
					</tr>
				</table>
			</div>
		</macro>
	</macrolist>
</head>
<body footer="myfooter" footer-height="1mm" header="myheader" header-height="12mm">
		 	<table width='100%' border-style="all" align="center" padding-top="5">
	 		<tr>
	 			<td colspan="9"><p class="times-font-10">${reportInfo.assignedDAName}</p></td>
	 		</tr>
	 		<tr>
	 			<td colspan="9" align="left"><p class="times-font-9"><b><nobr>CONFIDENTIAL!!!   CONFIDENTIAL!!!   CONFIDENTIAL!!!   CONFIDENTIAL!!!   CONFIDENTIAL!!!   CONFIDENTIAL!!!    CONFIDENTIAL!!!     CONFIDENTIAL!!!     CONFIDENTIAL!!!</nobr></b></p></td>
	 		</tr>
	 		<tr>
				<td align="center" width="20%" colspan="20" border-top = ".1"></td>
			</tr>
			<tr>
		    	<td width="15%"><p class="times-font-10">NAME</p></td>
		    	<td width="10%"><p class="times-font-10">JUV#/<br></br>REFERRAL#</p></td>
		    	<td colspan="1"><p class="times-font-10">SEX/<br></br>AGE</p></td> 
		    	<td colspan="1"><p class="times-font-10">DATE<br></br>IN</p></td> 
				<td colspan="1"><p class="times-font-10">PROB.<br></br>CAUSE</p></td> 
		        <td colspan="1"><p class="times-font-10">COURT<br></br>ID/DATE</p></td>   
		        <td colspan="1"><p class="times-font-10">PETITION</p></td>
		        <td colspan="1"><p class="times-font-10"><br></br>OFFENSE/ALLEGATION</p></td>     
		        <td colspan="1"><p class="times-font-10"><br></br>J.P.O</p></td>
			 </tr>
			  <tr>
				<td align="center" width="20%" colspan="20" border-top = ".1"></td>
			  </tr>
			  <c:set var="dockets"  value="${reportInfo.detentionSearchResults}" scope="session"/>
				
					<c:forEach var="resultItems" items="${dockets}" varStatus="status">	
							<tr page-break-before="avoid">
								<td class='formDe' width="35%"><p class="times-font-9"><c:out value="${resultItems.juvenileName}"/> </p></td>
					   			<td class='formDe' width="10%"><p class="times-font-9"><c:out value="${resultItems.juvenileNumber}"/>/<c:out value="${resultItems.referralNum}"/></p></td>
								<td class='formDe' width="10%"><p class="times-font-9"><c:out value="${resultItems.gender}"/>/<c:out value="${resultItems.age}"/></p></td>
					   			<td class='formDe' width="10%"><p class="times-font-9"><c:out value="${resultItems.admitDate}"/></p></td> 
					   			<td class='formDe' width="10%"><p class="times-font-9"><c:out value="${resultItems.probableCauseDate}"/></p></td> <!-- new field -->
					   			<td class='formDe' width="10%"><p class="times-font-9"><c:out value="${resultItems.court}"/>/<br></br><c:out value="${resultItems.lastCourtDate}"/></p></td>
					   			<td class='formDe' width="10%"><p class="times-font-9"><c:out value="${resultItems.petitionNumber}"/></p></td> <!-- new field - limit to size 75-->
					   			<td class='formDe' width="10%"><p class="times-font-9"><c:out value="${resultItems.petitionAllegationDesc}"/></p></td> 
						    	<td class='formDe' width="10%"><p class="times-font-9"><c:out value="${resultItems.probationOfficerCd}"/></p></td>				    
				   			</tr>
				   			<tr page-break-before="avoid">			   			
					   			<td colspan="5">			   				
						   			<table>
							   			<tr>
							   				<td>
							   					<p class="times-font-9"><nobr><c:out value="${resultItems.attorneyConnectionDesc}"/> ATTY: <c:out value="${resultItems.attorneyName}"/></nobr></p>
							   				</td>
											<td>
												<logic:equal name="resultItems" property="atyConfirmation" value="YES"><img width="8" height="8" src="images/green_check.gif"/></logic:equal>
												<logic:notEqual name="resultItems" property="atyConfirmation" value="YES"><img width="8" height="8" src="images/unchecked.jpg"/></logic:notEqual>
											</td>
										</tr>
									</table>							
								</td>
					   			<td colspan="2"><p class="times-font-9"><nobr>GAL: <c:out value="${resultItems.galName}"/></nobr></p></td>
					   			
					   			<td><p class="times-font-9"><nobr>FINDING:_________________ ____/____/______</nobr></p></td>
				   			</tr>
				   			<tr page-break-before="avoid"><td><p class="times-font-9">2nd ATTY: <c:out value="${resultItems.attorney2Name}"/></p></td>
				   			<td colspan="4"></td>
				   			<td width=".1%" ><p valign = 'top' align="left" class="times-font-10 right">Interpreter</p></td>
							<td width="1%"><p valign = 'middle' align="left">					
				    					<elogic:if name="resultItems" property="interpreter" op="equal"	value="1">
											<elogic:then>
												<img width="8" height="8" src="images/green_check.gif"/>
											</elogic:then>
											<elogic:else>
												<img width="8" height="8" src="images/unchecked.jpg"/>
											</elogic:else>
										</elogic:if>
							</p></td></tr>
				   			<tr page-break-before="avoid">
				   				<td colspan="8" align="right" width="70%"><p class="times-font-9">__________________________________________________________________________</p></td> 
				   			</tr>	
				   			<tr><td>&nbsp;</td></tr>		   					   		   			
					</c:forEach>
												
	  	</table>	
</body>
</pdf>