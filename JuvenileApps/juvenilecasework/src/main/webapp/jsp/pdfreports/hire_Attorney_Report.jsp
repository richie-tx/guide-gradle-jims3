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
<jsp:useBean id="reportInfo" class="messaging.interviewinfo.to.HireAttorneyReportDataTO" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.HIRE_ATTORNEY_REPORT.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.HIRE_ATTORNEY_REPORT.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
		<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style>
body
	{	margin-left: .0in;
    	margin-right: .0in;
		margin-top: .1in; 
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
	</macrolist>
</head>
<body footer="myfooter" footer-height="5mm">
<!--  Header information -->
<logic:notEmpty name="reportInfo" property="guardianRelatedInformation">
	<c:set var="guardianRelatedInformationList" value="${reportInfo.guardianRelatedInformation}" scope="session"/>
		<c:forEach var="guardian" items="${guardianRelatedInformationList}" varStatus="status">
<div class="body">
	<table width="100%" border-style="none">
		<tr>
			<td align="center" width="100%"><p class="arial-font-12"><u><b>RIGHTS OF PARENTS- WORKSHEET</b></u></p></td>
		</tr>
		<tr><td align="left"><p class="arial-font-12"></p></td></tr>
		<tr><td align="left"><p class="arial-font-12"></p></td></tr>
		<tr><td align="left"><p class="arial-font-12"></p></td></tr>
		<tr align="center">
			<td align="left" width="100%"><p class="arial-font-10">
				(NOTE: This completed form is to be retained in the master file.  Once information is shared with the parent/guardian, the</p>
    		</td>
    	</tr>
		<tr align="center">
    		<td align="left" width="100%"><p class="arial-font-10">
				service delivery code RPE should be entered).</p>
    		</td>
		</tr>
		<tr><td align="left"><p class="arial-font-12"></p></td></tr>
		<tr><td align="left"><p class="arial-font-12"></p></td></tr>
		<tr align="center">
			<td align="left" width="100%"><p class="arial-font-10">
				As per the Texas Family Code- Rights of Parents, the following information shall be given to the parent/guardian:</p>
    		</td>
		</tr>
		<tr><td align="left"><p class="arial-font-12"></p></td></tr>
		<tr><td align="left"><p class="arial-font-12"></p></td></tr>
	</table>
<!--  JJS OFFENSE INFORMATION -->
	<table width="100%" border-style="none">
		<logic:notEmpty name="reportInfo" property="jjsOffenses">
			<tr><td align="left"></td></tr>
			<tr align="left">
				<td align="left"><u><b>Offense Information:</b></u></td>
			</tr>
			<c:set var="jjsOffensesList" value="${reportInfo.jjsOffenses}" scope="session"/>
				<c:forEach var="jjsOffense" items="${jjsOffensesList}" varStatus="status">
						<tr><td align="left">	
							<table width="100%" border-style="none">
								<tr>
									<td align="left" width="65%">Offense Date:</td>
									<td align="left" width="35%"></td>
								</tr>
								<tr>
									<td align="left" width="65%">Name of Offense:</td>
									<td align="left" width="35%"></td>
								</tr>
								<tr>	
									<td align="left" width="65%">Offense Level:</td>
									<td align="left" width="35%">Penal category:</td>									
								</tr>
							</table>
						</td></tr>
				</c:forEach>			
		</logic:notEmpty>	
	</table>
<!--  END JJS OFFENSE INFORMATION -->
<!--  JOT RECORD INFORMATION -->
	<table width="100%" border-style="none">
		<logic:notEmpty name="reportInfo" property="jotRecord">
			<tr><td align="left"></td></tr>
			<tr align="left">
				<td align="left"><u><b>Offense Information:</b></u></td>
			</tr>
			<c:set var="jotRecordList" value="${reportInfo.jotRecord}" scope="session"/>
				<c:forEach var="jotRecord" items="${jotRecordList}" varStatus="status">				
					<tr><td align="left">	
						<table width="100%" border-style="none">
						<c:set var="jotChargesList" value="${jotRecord.jotCharges}" scope="session"/>
							<c:forEach var="jotCharge" items="${jotChargesList}" varStatus="status">
								<tr>
									<td align="left" width="65%">
										<fmt:formatDate value="${jotCharge.offenseDate}" pattern="MMM d, yyyy" var="formattedOffenseDate" />
										Offense Date: <c:out value="${formattedOffenseDate}"/>
									</td>
									<td align="left" width="35%"></td>
								</tr>
								<tr>
									<td align="left"  colspan="2">Name of Offense: <c:out value="${jotCharge.chargeDescription}"/></td>
								</tr>
								<tr>	
									<td align="left" width="65%">Offense Level: <c:out value="${jotCharge.offenseLevel}"/><c:out value="${jotCharge.offenseDegree}"/>
									</td>
									<td align="left" width="35%">Penal category: <c:out value="${jotCharge.penalCode}"/></td>									
								</tr>
								<tr>	
									<logic:equal name="jotCharge" property="weaponUsed" value="true">
										<td align="left" width="65%">Weapon used: Yes</td>
									</logic:equal>
									<logic:equal name="jotCharge" property="weaponUsed" value="false">
										<td align="left" width="65%">Weapon used: No</td>
									</logic:equal>
									<td align="left" width="35%">If yes, type used: <c:out value="${jotCharge.weaponType}"/></td>									
								</tr>
								<tr>	
									<logic:equal name="jotCharge" property="involvedInGang" value="true">
										<td align="left" width="65%">Offense gang related: Yes</td>
									</logic:equal>
									<logic:equal name="jotCharge" property="involvedInGang" value="false">
										<td align="left" width="65%">Offense gang related: No</td>
									</logic:equal>
									<td align="left" width="35%"></td>									
								</tr>
								<tr>	
									<logic:equal name="jotCharge" property="alcoholInfluence" value="true">
										<td align="left" width="65%">Offense related to alcohol or drugs: Yes</td>
									</logic:equal>
									<logic:equal name="jotCharge" property="alcoholInfluence" value="false">
										<td align="left" width="65%">Offense related to alcohol or drugs: No</td>
									</logic:equal>
									<td align="left" width="35%"></td>									
								</tr>							
							</c:forEach>
						</table>
					</td></tr>
					<tr><td align="left"><p class="arial-font-12"></p></td></tr>
					<tr><td align="left"><p class="arial-font-12"></p></td></tr>	
					<tr><td align="left">	
						<table width="100%" border-style="none">		
<!--  FILING EVENT INFORMATION -->					
							<tr align="left">
								<td align="left"><u><b>Filing Event Information:</b></u></td>  
							</tr>
							<tr>
								<td align="left" width="65%">Date taken into custody: <c:out value="${jotRecord.dateTakenIntoCustody}"/></td>
								<td align="left" width="35%">Time taken into custody: <c:out value="${jotRecord.timeTakenIntoCustody}"/></td>
							</tr>
							<tr>
								<td align="left" colspan="2"><p class="arial-font-10">Type of property taken (damage amount): 
									<c:set var="propertiesLossList" value="${jotRecord.propertyLossList}" scope="session"/>
										<c:forEach var="propertyLost" items="${propertiesLossList}" varStatus="status">
											<c:out value="${propertyLost.description}"/> <c:out value="${propertyLost.value}"/>
										</c:forEach>
								</p></td>
							</tr>
							<tr>
								<td align="left" colspan="2"><p class="arial-font-10">Names of adult co-actors, if any: 
									<c:set var="adultCoActorsList" value="${jotRecord.adultCoActors}" scope="session"/>
										<c:forEach var="adultCoActor" items="${adultCoActorsList}" varStatus="status">
											<c:out value="${adultCoActor}"/>
										</c:forEach>
								</p></td>
							</tr>
							<tr>
								<td align="left"  colspan="2">Names of juvenile co-actors, if any: <c:out value="${jotRecord.juvenileCoActors}"/></td>
							</tr>
<!--  END FILING EVENT INFORMATION -->	
						</table>
					</td></tr>		
				</c:forEach>			
		</logic:notEmpty>	
	</table>
<!--  END JOT RECORD INFORMATION -->
<!--  VICTIM INFORMATION -->
	<table width="100%" border-style="none" padding-top="5" padding-bottom="175">
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="90%"></td>
		</tr>
		<tr align="left">
			<td align="left" colspan="3"><u><b>Victim Information:</b></u></td>
		</tr>
		<tr align="center">
			<td align="left" colspan="3"><p class="arial-font-10">
				Victim&apos;s physical injuries, if any: ${reportInfo.victimsPhysicalInjuries}</p>
    		</td>
    	</tr>
    	<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="90%"></td>
		</tr>
		<tr align="left">
			<td align="left" colspan="3"><b>Aspects of juvenile process that apply:</b></td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" colspan="2">Detention Hearing</td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" colspan="2">Adjudication Hearing or Deferred Prosecution agreement</td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" colspan="2">Disposition Hearing:</td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="90%">a. Regular Probation</td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="90%">b. ISP Probation</td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="90%">c. Custody to Chief</td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="90%">d. TYC</td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="90%">e. Determinate Sentencing</td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="90%">f. Certification</td>
		</tr>
	</table>	
<!--  END VICTIM INFORMATION -->
</div>
<div class="body">	
<!--  VICTIM INFORMATION -->
	<table width="100%" border-style="none">
		<tr>
			<td align="center" colspan="5"><p class="arial-font-12"><u><b>RIGHTS OF PARENTS - Page <pagenumber/></b></u></p></td>
		</tr>
		<tr>
			<td align="left" colspan="5" class="arial-font-10"></td>
		</tr>
		<tr>
			<td align="left" colspan="5" class="arial-font-10"></td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="80%"></td>
		</tr>
		<tr align="left">
			<td align="left" width="5%">A.</td>
			<td align="left" colspan="4">Visitation Policy and hours (if applicable):</td>
		</tr>
		<tr><td align="left" colspan="5"><p class="arial-font-12"></p></td></tr>
		<tr><td align="left" colspan="5"><p class="arial-font-12"></p></td></tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="5%">HOURS:</td>
			<td align="left" colspan="2">Monday and Thursday - 3:00pm to 7:30pm</td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" colspan="2">Tuesday, Wednesday, Friday - 2:00pm to 7:30pm</td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" colspan="2">Saturday, Sunday, County Holidays</td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="80%">9:00am to 12:00pm</td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="80%">12:00pm to 4:30pm</td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="5%">POLICY:</td>
			<td align="left" colspan="2">
				<p class="arial-font-10">Visits are limited to twice a week, for a period of 30 minutes per visit. The visiting week begins on Monday</p>
			</td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="5%"></td>
			<td align="left" colspan="2">
				<p class="arial-font-10">Only legal parents and legal guardian are allowed to visit with proper identification.</p>
			</td>
		</tr>
		<tr><td align="left" colspan="5"><p class="arial-font-12"></p></td></tr>
		<tr><td align="left" colspan="5"><p class="arial-font-12"></p></td></tr>
		<tr align="left">
			<td align="left" width="5%">B.</td>
			<td align="left" colspan="4">Explanation of attorney appointment process:</td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%">-</td>
			<td align="left" colspan="3">
				<p class="arial-font-10">The amount of people in the household and the household income based on the Poverty Guidelines will determine whether or not your child qualifies for a court appointed attorney.</p>
			</td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%">-</td>
			<td align="left" colspan="3">
				<p class="arial-font-10">You may choose to retain an attorney to represent your child in court.</p>
			</td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%">-</td>
			<td align="left" colspan="3">
				<p class="arial-font-10">If you request a court appointed attorney and meet the poverty guidelines criteria, an attorney will be appointed by the court.</p>
			</td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%">-</td>
			<td align="left" colspan="3">
				<p class="arial-font-10">If you request a court appointed attorney and do not meet the poverty guidelines, the court will either require you to retain an attorney or the court will appoint an attorney and you will be required to reimburse the county for charges incurred.</p>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="5" class="arial-font-10"></td>
		</tr>
		<tr align="left">
			<td align="left" width="5%">C.</td>
			<td align="left" colspan="4">Method by which parent can assist legal process:</td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%">-</td>
			<td align="left" colspan="3">
				<p class="arial-font-10">Attend all Hearings</p>
			</td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%">-</td>
			<td align="left" colspan="3">
				<p class="arial-font-10">Retain an attorney if required</p>
			</td>
		</tr>
		<tr align="left">
			<td align="left" width="5%"></td>
			<td align="left" width="5%">-</td>
			<td align="left" colspan="3">
				<p class="arial-font-10">Cooperate with providing information (i.e. birth certificate, social security card, school records, immunization records, and any significant information pertaining to child)</p>
			</td>
		</tr>
	</table>
	<table width="100%" border-style="none">
		<tr>
			<td align="left"></td>
		</tr>
		<tr>
			<td align="left">Information explained to: <c:out value="${guardian.informationExplainedTo}"/></td>
		</tr>
		<tr>
			<td align="left"></td>
		</tr>
		<tr>
			<td align="left">Relationship to child: <c:out value="${guardian.relationshipToChild}"/></td>
		</tr>
		<tr>
			<td align="left"></td>
		</tr>
		<tr>
			<td align="left">Date information given: <c:out value="${reportInfo.dateInformationGiven}"/></td>
		</tr>
		<tr>
			<td align="left"></td>
		</tr>
		<tr>
			<td align="left">Information explained: <c:out value="${guardian.informationExplained}"/></td>
		</tr>
		<tr>
			<td align="left"></td>
		</tr>
		<tr>
			<td align="left">Probation Officer: <c:out value="${reportInfo.probationOfficer}"/></td>
		</tr>
	</table>
<!--  END VICTIM INFORMATION -->
</div>
</c:forEach>			
</logic:notEmpty>
</body>
</pdf>