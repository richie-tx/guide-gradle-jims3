<?xml version="1.0"?>
<!DOCTYPE pdf PUBLIC "-//big.faceless.org//report" "report-1.1.dtd">
<%@page language="java" contentType="text/xml; charset=UTF-8" %>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="mojo.km.utilities.DateUtil"%>
<%@page import="ui.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="reportInfo" class="ui.juvenilecase.districtCourtHearings.StandardDocketReportBean" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.STANDARD_COURT_DOCKET_REPORT.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.STANDARD_COURT_DOCKET_REPORT.getReportName()%>"/>
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
		font-size:8;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left 		bottom: 2px;}
body { size:A4-landscape; padding:0.1in; }
div.body
	{	font-size:8;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left
		bottom: 4px;}
div.footer
	{	font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left;}
div.header
	{ 	font-size:8;
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
p.body-12-arial
	{	font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
tr.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;}
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
		<macro id="myheader">
			<div class="header">
				<table width="100%" border-style="none">		
				<tr border-bottom = ".2"  align="right">
					<td align="right" colspan = "7"><p class="arial-font-9"><b><%=reportInfo.getCourtIdWithSuffix()%> DISTRICT COURT DOCKET – CALENDAR FOR <%=reportInfo.getCourtDate()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></p></td>
					<td align="right" colspan = "5"><p class="arial-font-9"><b><%=reportInfo.getDisplay()%>&nbsp;&nbsp;&nbsp;Page <pagenumber/> of <totalpages/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=reportInfo.getCourtIdWithSuffix()%>--<%=reportInfo.getCourtDate()%></b></p></td>
				</tr>
				<tr border-bottom = ".5" class="arial-font-8" width = "100%">
					<td align="left" width="2%"><b><br/>TIME </b></td>
					<td align="left" width="11%"><p class="arial-font-8" align = "left"><b>* PETITION     *<br/>*NO-STA-AMD*</b></p></td>
					<td align="left" width="25%"><p class="arial-font-8" align = "left"><b>NAME(S)</b></p></td>
					<td align="left" width="5%"><b>DET<br /> (?)&nbsp;</b></td>
					<td align="left" width="5%"><b>&nbsp;<br/> DOB</b></td>
					<td align="left" width="2%"><b>*<br/>*</b></td>
					<td align="left" width="15%"><b>ALLEGATION</b></td>
					<td align="right" width="5%"><b><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CLS</b></td>
					<td align="left" width="1%"><b>*<br/>*</b></td>
					<td align="right" width="20%"><b><br/>NOTES</b></td>
					<td align="right" width="5%"><b>RESET<br/> DATE</b></td>
					<td align="right" width="5%"><nobr><b>HEAR <br/>TYPE</b></nobr></td>
				</tr>
				<tr>
					<td colspan = "11"><p align = "left" class="arial-font-8"><nobr>!!!!!!!!!CONFIDENTIAL!!!!!!!CONFIDENTIAL!!!!!!!CONFIDENTIAL!!!!!!!CONFIDENTIAL!!!!!!!CONFIDENTIAL!!!!!!!CONFIDENTIAL!!!!!!!CONFIDENTIAL!!!!!!!CONFIDENTIAL!!!!!!!CONFIDENTIAL!!!!!!!CONFIDENTIAL!!!!!!!CONFIDENTIAL</nobr><br /></p></td>
				</tr>
				</table>
			</div>
		</macro>	
	</macrolist>
</head>
<body footer="myfooter" footer-height="1mm" header="myheader" header-height="73pt">
<!--  Header information -->
		
<div class="body">
<!--  HEADER INFORMATION -->
</div>
	<table width="100%" border-style="none">
	<logic:notEmpty name="reportInfo" property="dockets">
	<c:set var="docketInformationList" value="${reportInfo.dockets}" scope="session"/>
	<c:forEach var="docketFromList" items="${docketInformationList}" varStatus="status">
		<tr>
			<td align="left" width="2%"><c:out value="${docketFromList.courtTime}"/></td>
			
			<td align="left" width="5%">
				<p align="right" class="arial-font-8">
					<c:out value="${docketFromList.petitionNumber}"/><br/>
					<c:out value="${docketFromList.juvenileNumber}"/><br/>
					<c:out value="${docketFromList.petitionStatusCd}"/><br/>
					<c:out value="${docketFromList.amendment}"/>
				</p>
				<%-- <c:out value="${docketFromList.petitionStatusCd}"/>
				<p align="left" class="arial-font-8">
					<c:out value="${docketFromList.amendment}"/>
				</p> --%>
			</td>
			
			<td align="left" width="25%">
				<p align="left" class="arial-font-8">
					<c:out value="${docketFromList.juvenileName}"/><br />
					<logic:notEqual  name="docketFromList" property="recType" value = "ANCILLARY">
					
						<logic:notEmpty  name="docketFromList" property="noJuvFamily">
			  				<bean:write name="docketFromList" property="noJuvFamily"/><br />
			  			</logic:notEmpty>
		  			</logic:notEqual>
		  			
		  			<logic:notEqual  name="docketFromList" property="recType" value = "ANCILLARY">
						F: <c:out value="${docketFromList.father}"/><br />
						M: <c:out value="${docketFromList.mother}"/>
						<logic:notEmpty  name="docketFromList" property="relationship"><br />O: <c:out value="${docketFromList.other}"/> 
							(<c:out value="${docketFromList.relationship}"/>)
						</logic:notEmpty>
					</logic:notEqual><br />
						COMMENT: <c:out value="${docketFromList.comments}"/>
				</p>
			</td>
			
			<td align="left" width="8%"><c:out value="${docketFromList.facilityName}"/></td>
			<td align="left" width="8%"><c:out value="${docketFromList.dob}"/></td>
			<td align="left" width="2%"></td>
			<td align="left" width="35%"><p style="margin-top:0px; margin-bottom:0px;" align="left" class="arial-font-8">
							
				<c:out value="${docketFromList.allegationDesc}"/>
				
						<!-- VOP -->
						<c:if test="${(docketFromList.hearingType !=null && (docketFromList.hearingType =='VP' || docketFromList.hearingType =='VI'))}">
							&nbsp;&nbsp;&nbsp;(<c:out value="${docketFromList.offenseCode}"/>)
						</c:if>
						
						<!-- TRNDSP, TRNSIN -->
						<c:if test="${(docketFromList.petitionAllegation!=null)&&(docketFromList.petitionAllegation=='TRNDSP' || docketFromList.petitionAllegation=='TRNSIN')}">
							&nbsp;&nbsp;&nbsp;(<c:out value="${docketFromList.offenseCode}"/>)
						</c:if>
						
						<!-- All Others - exclude VOP, TRNDSP, TRNSIN -->
						<c:if test="${(docketFromList.petitionAllegation != null && docketFromList.petitionAllegation !='TRNDSP' && docketFromList.petitionAllegation !='TRNSIN') && 
							(docketFromList.hearingType !=null && docketFromList.hearingType !='VP' && docketFromList.hearingType !='VI' &&
							((docketFromList.hearingType=='DM')||(docketFromList.hearingType=='DS')||(docketFromList.hearingType=='DX'))) }">
								&nbsp;&nbsp;&nbsp;(<c:out value="${docketFromList.petitionAllegation}"/>)
						</c:if>						
						
					
			<c:if test="${(docketFromList.jpoOfRecord!=null)&& (not empty docketFromList.jpoOfRecord)}"><br /><span letter-spacing = "-.5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;JPO OF RCD: <c:out value="${docketFromList.jpoOfRecord}"/></span></c:if>
			<br /><logic:equal name="docketFromList" property="docketType" value="ANCILLARY"><c:if test="${(docketFromList.attorneyConnection!=null)&& (not empty docketFromList.attorneyConnection)}">
			(<c:out value="${docketFromList.attorneyConnection}"/>)</c:if></logic:equal>
				<table>
				<logic:notEmpty  name="docketFromList" property="attorneyName">
					<tr>
						<td>
							ATY: <c:out value="${docketFromList.attorneyName}"/>
							<%-- <logic:equal name="docketFromList" property="docketType" value="DETENTION"><c:if test="${(docketFromList.attorneyConnection!=null)&& (not empty docketFromList.attorneyConnection)}">(<c:out value="${docketFromList.attorneyConnection}"/>)</c:if></logic:equal>
							<logic:equal name="docketFromList" property="docketType" value="COURT"><c:if test="${(docketFromList.attorneyConnection!=null)&& (not empty docketFromList.attorneyConnection)}">(<c:out value="${docketFromList.attorneyConnection}"/>)</c:if></logic:equal>
						 --%>
						 </td>
						<logic:notEqual name="docketFromList" property="docketType" value="ANCILLARY">
						<td>
							<logic:equal name="docketFromList" property="atyConfirmation" value="YES"><img width="8" height="8" src="images/green_check.gif"/></logic:equal>
							<logic:notEqual name="docketFromList" property="atyConfirmation" value="YES"><img width="8" height="8" src="images/unchecked.jpg"/></logic:notEqual>
						</td>
						<logic:equal name="docketFromList" property="docketType" value="COURT">
							<td><c:if test="${(docketFromList.appAttorney!=null)&&(docketFromList.appAttorney=='1')}">Appellate</c:if></td>
						</logic:equal>
						</logic:notEqual>
					</tr>
					</logic:notEmpty>
					<tr>
						<td>
							<logic:equal name="docketFromList" property="docketType" value="DETENTION"><c:if test="${(docketFromList.attorneyConnection!=null)&& (not empty docketFromList.attorneyConnection)}">(<c:out value="${docketFromList.attorneyConnection}"/>)</c:if></logic:equal>
							<logic:equal name="docketFromList" property="docketType" value="COURT"><c:if test="${(docketFromList.attorneyConnection!=null)&& (not empty docketFromList.attorneyConnection)}">(<c:out value="${docketFromList.attorneyConnection}"/>)</c:if></logic:equal>
						</td>
					</tr>					
					<logic:notEmpty  name="docketFromList" property="attorney2Name">
					<tr>							
						<td>2nd ATY: <c:out value="${docketFromList.attorney2Name}"/></td>
					</tr>
					</logic:notEmpty>
					<logic:notEmpty  name="docketFromList" property="galName">
					<tr>
						<td>GAL: <c:out value="${docketFromList.galName}"/></td>
					</tr>
					</logic:notEmpty>
				</table>
				</p>
				
				<%-- <p style="margin-top:0px; margin-bottom:0px;" align="left" class="arial-font-8">GAL: <c:out value="${docketFromList.galName}"/>&nbsp;&nbsp;&nbsp;&nbsp;2nd ATY: <c:out value="${docketFromList.attorney2Name}"/> </p> --%>
<%-- 				<p style="margin-top:0px; margin-bottom:0px;" align="left" class="arial-font-8">GAL: <c:out value="${docketFromList.galName}"/></p> --%>
			
				<logic:notEmpty  name="docketFromList" property="probationOfficer">
				<p style="margin-top:0px; margin-bottom:0px;" align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;COURT JPO: <c:out value="${docketFromList.probationOfficer}"/></p></logic:notEmpty>
			</td>
			<td align="left" width="5%">
			
				<!-- VOP -->
				<c:if test="${(docketFromList.hearingType!=null && (docketFromList.hearingType=='VP' || docketFromList.hearingType =='VI'))}">
					&nbsp;&nbsp;&nbsp;(<c:out value="${docketFromList.offenseCategory }"/>)
				</c:if>
				
				 <!-- TRNDSP, TRNSIN -->
				<c:if test="${(docketFromList.petitionAllegation !=null && (docketFromList.petitionAllegation == 'TRNDSP' || docketFromList.petitionAllegation == 'TRNSIN')) }">
					&nbsp;&nbsp;&nbsp; (<c:out value="${docketFromList.offenseCategory }"/>)
				</c:if>	

				 <!-- All Others - exclude VOP, TRNDSP, TRNSIN -->
				<c:if test="${(docketFromList.petitionAllegation !=null && docketFromList.petitionAllegation != 'TRNDSP' && docketFromList.petitionAllegation != 'TRNSIN') && 
							(docketFromList.hearingType!=null && docketFromList.hearingType !='VP' && docketFromList.hearingType !='VI')}">
					&nbsp;&nbsp;&nbsp;<c:out value="${docketFromList.offenseCode }"/>
				</c:if>	 			
 
								
			</td>
			<td align="left" colspan="3" width="20%"><p align="right" class="arial-font-8"><c:if test="${(docketFromList.offenseCode == null)|| (not empty docketFromList.offenseCode)}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
			<c:out value="${docketFromList.juryFlagDesc}"/>&nbsp;<c:out value="${docketFromList.prevNotes}"/></p> 
			<p valign = 'bottom' align = 'right'><br /><br />DISP:..................................</p>
			<%-- <p>Interpreter					
				    					<elogic:if name="docketFromList" property="interpreter" op="equal"	value="1">
											<elogic:then>
												<img width="8" height="8" src="images/green_check.gif"/>
											</elogic:then>
											<elogic:else>
												<img width="8" height="8" src="images/unchecked.jpg"/>
											</elogic:else>
										</elogic:if>
										</p> --%>
			<%-- <p >Interpreter
			<logic:equal name="docketFromList" property="interpreter" value="1"><img width="8" height="8" src="images/green_check.gif"/></logic:equal>
			<logic:notEqual name="docketFromList" property="interpreter" value="1"><img width="8" height="8" src="images/unchecked.jpg"/></logic:notEqual>
			</p> --%></td>
			<td><p valign = 'bottom' align = 'left'><br /><br /><br /><nobr>__/__/____ __</nobr>
			</p></td>
		</tr>
		<tr  page-break-before="avoid">
		<td colspan="8"></td>		
		<td width=".5%" ><p valign = 'top' align="left" class="arial-font-10 right">Interpreter</p></td>
		<td width="25%"><p valign = 'middle' align="left">					
				    					<elogic:if name="docketFromList" property="interpreter" op="equal"	value="1">
											<elogic:then>
												<img width="8" height="8" src="images/green_check.gif"/>
											</elogic:then>
											<elogic:else>
												<img width="8" height="8" src="images/unchecked.jpg"/>
											</elogic:else>
										</elogic:if>
		</p></td>
		</tr>
		<tr><td>&nbsp;</td></tr>                               
	</c:forEach>
	</logic:notEmpty>
	</table>
</body>
</pdf>
