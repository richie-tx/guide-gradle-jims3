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
<jsp:useBean id="reportInfo" class="messaging.interviewinfo.to.SocialHistoryReportDataTO" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.SOCIAL_HISTORY_REPORT.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.SOCIAL_HISTORY_REPORT.getReportName()%>"/>
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
<div class="header">
	<table width="100%" border-style="none">		
		<tr>
			<td align="left" width="100%"><img class="display" height="70" width="70" src="images/HarrisCountySeal.jpg"/></td>
		</tr>
			<c:set var="presentOffensesForGenericList" value="${reportInfo.presentOffensesForGeneric}" scope="session"/>
				<c:forEach var="presentOffenseFromList" items="${presentOffensesForGenericList}" varStatus="status">
					<tr align="center">
						<td align="center" width="100%"><b>IN THE <c:out value="${presentOffenseFromList.courtName}"/></b></td>
					</tr>
				</c:forEach>
			<tr>
				<td align="center" width="100%"><b>HARRIS COUNTY</b></td>
			</tr>
			<tr>
				<td align="center" width="100%" padding-bottom="20"><b>STATE OF TEXAS</b></td>
			</tr>
			<tr>
				<td align="left" width="100%"><b>IN THE MATTER OF: <%=reportInfo.getJuvenileName()%></b></td>
			</tr>
			<tr>
				<td align="left" width="100%"><b>NO: <%=reportInfo.getAllPetitionNumForGeneric()%></b></td>
			</tr>
			<tr padding-top="10">
				<td align="left" width="100%"><b><u>Exhibit B</u></b></td>
			</tr>
	</table>	
</div>
<div class="body">
	<table width="100%" border-style="none" padding-top="5" padding-bottom="275">
		<tr align="center">
			<td align="left" width="100%" colspan="2">
				<b><i>
				The court finds that the following efforts were considered in determining whether 
				reasonable efforts have been made to prevent or eliminate the need for the child 
				to be removed from (his or her) home:
				</i></b>
    		</td>
		</tr>
		<tr align="center">
			<td align="left" width="4%"><b>_____</b></td>
			<td align="left" width="96%">		
   				 <b>The child was previously placed on probation by the Court.</b>
    		</td>
		</tr>
		<tr align="center">
			<td align="left" width="4%"><b>_____</b></td>
			<td align="left" width="96%">		
   				 <b>The child and/or family was previously referred to the following community, court, or educational programs: (list programs)</b>
    		</td>
		</tr>
		<tr align="center">
			<td align="left" width="4%"><b>_____</b></td>
			<td align="left" width="96%">		
   				 <b>The child and/or family was previously referred to the following counseling or psychological services: (list programs)</b>
    		</td>
		</tr>
		<tr align="center">
			<td align="left" width="4%"><b>_____</b></td>
			<td align="left" width="96%">		
   				 <b>The child and/or family is receiving or has previously received services from TDPRS or MHMRA.</b>
    		</td>
		</tr>
		<tr align="center">
			<td align="left" width="4%"><b>_____</b></td>
			<td align="left" width="96%">		
   				 <b>The nature of the offense and/or circumstances in the child&apos;s home necessitates removal of the child from the home.</b>
    		</td>
		</tr>
		<tr align="center">
			<td align="left" width="4%"><b>_____</b></td>
			<td align="left" width="96%">		
   				 <i>(List any other specific circumstances not addressed above)</i>
    		</td>
		</tr>
	</table>
</div>		
<!--  Header information -->
<div class="header">
	<table width="100%" border-style="none">
		<tr>
			<td align="left"><img class="display" height="70" width="70" src="images/TexasJuvenileProbationCommisionSeal.jpg"/></td>
		</tr>	
		<tr>
				<td align="center" width="100%"><b>JUVENILE PROBATION REPORT</b></td>
		</tr>
	</table>	
</div>
<div class="body">
<!--  JUVENILE INFORMATION -->
	<table width="100%" border-style="none">
		<tr><td align="left"></td></tr>
		<tr align="center">
			<td align="left"><b>Name: </b><%=reportInfo.getJuvenileName()%></td>
		</tr>
		<tr>
			<td align="left"><b>DOB: </b><%=reportInfo.getDateOfBirth()%></td>		
		</tr>
		<tr align="center">
			<td align="left"><b>Petition #: </b><%=reportInfo.getAllPetitionNum()%></td>
		</tr>
		<tr>
			<td align="left"><b>Juvenile #: </b><%=reportInfo.getJuvenileNumber()%></td>			
		</tr>
		<logic:equal name="reportInfo" property="generic" value="false">
			<c:set var="presentOffensesForGenericList" value="${reportInfo.presentOffensesForGeneric}" scope="session"/>
				<c:forEach var="presentOffenseFromList" items="${presentOffensesForGenericList}" varStatus="status">
					<tr align="center">
						<td align="left" width="10%"><b>Court Date: </b>
							<fmt:formatDate value="${presentOffenseFromList.courtDate}" pattern="MMM d, yyyy" var="formattedAdmitDate" />
							<c:out value="${formattedAdmitDate}"/>
						</td>
					</tr>
				</c:forEach>
			<tr>
				<td align="left"><b>Date Interviewed: </b><%=reportInfo.getDateInterviewed()%></td>
			</tr>
			<tr>
				<td align="left"><b>Probation Officer: </b><%=reportInfo.getProbationOfficer()%></td>
			</tr>	
		</logic:equal>	
	</table>
</div>
<!--  END JUVENILE INFORMATION -->
<!--  PRESENT ALLEGED OFFENSE INFORMATION -->
	<table width="100%" border-style="none">
		<logic:notEmpty name="reportInfo" property="presentOffenses">
			<tr><td align="left"></td></tr>
			<tr align="left">
				<td align="left" padding-top="10"><u><b>PRESENT OFFENSE</b></u></td>
			</tr>
			<c:set var="presentOffensesList" value="${reportInfo.presentOffenses}" scope="session"/>
				<c:forEach var="juvenileOffense" items="${presentOffensesList}" varStatus="status">
					<logic:equal name="juvenileOffense" property="included" value="true">
						<tr><td align="left">	
							<table width="100%" border-style="none">
								<tr>
									<td align="left" width="65%"></td>
									<td align="left" width="35%"></td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<p class="arial-font-10"><b>Nature of Offense: </b><c:out value="${juvenileOffense.presentOffense}"/></p>
									</td>
								</tr>
									<logic:notEmpty name="juvenileOffense" property="adultCoActors">	
										<tr align="left">
											<td align="left"><p class="arial-font-10"><b>Adult Co-Actors:</b></p></td>
										</tr>
										<tr>
											<td align="left" width="65%"><p class="arial-font-9"><u><b>Name</b></u></p></td>
											<td align="left" width="35%"><p class="arial-font-9"><u><b>Age</b></u></p></td>
										</tr>
										<tr>
											<td align="left" colspan="2">
											<table width="100%" border-style="none">
												<c:set var="adultCoActorsList" value="${juvenileOffense.adultCoActors}" scope="session"/>
													<c:forEach var="adultCoActor" items="${adultCoActorsList}" varStatus="status">
														<tr>
															<td align="left" width="65%"><p class="arial-font-9">
																<c:out value="${adultCoActor.name}"/></p></td>
															<td align="left" width="35%"><p class="arial-font-9">
																<c:out value="${adultCoActor.age}"/></p></td>
														</tr>
													</c:forEach>
											</table>	
											</td>									
										</tr>
									</logic:notEmpty>
							</table>
						</td></tr>
					</logic:equal>
				</c:forEach>
			<c:set var="pofActivitiesList" value="${reportInfo.pofActivities}" scope="session"/>
				<c:forEach var="pofActivity" items="${pofActivitiesList}" varStatus="status">
					<tr>
						<td align="left" colspan="2">
							<p class="arial-font-9"><c:out value="${pofActivity.comments}"/></p>
						</td>
					</tr>
				</c:forEach>			
		</logic:notEmpty>	
	</table>
<!--  END PRESENT ALLEGED OFFENSE INFORMATION -->
<!--  SIGNIFICANT INFORMATION -->
	<table width="100%" border-style="none">
		<logic:notEmpty name="reportInfo" property="evsActivityComments">
			<tr><td align="left"></td></tr>
			<tr align="left">
				<td align="left" padding-top="10"><u><b>SIGNIFICANT INFORMATION</b></u></td>
			</tr>
			<c:set var="evsActivityCommentsList" value="${reportInfo.evsActivityComments}" scope="session"/>
			<c:forEach var="evsActivityComment" items="${evsActivityCommentsList}" varStatus="status">
				<tr>
					<td align="left" colspan="2">
						<p class="arial-font-9"><c:out value="${evsActivityComment.comments}"/></p>
					</td>
				</tr>
			</c:forEach>
		</logic:notEmpty>
	</table>
<!--  END SIGNIFICANT INFORMATION -->
<!--  PREVIOUS REFERRALS INFORMATION -->
	<table width="100%" border-style="none">
		<logic:notEmpty name="reportInfo" property="previousReferralDetails">
			<tr><td align="left"></td></tr>
			<tr align="left">
				<td align="left" colspan="5" padding-top="10"><u><b>PREVIOUS REFERRALS</b></u></td>
			</tr>
			<tr><td align="left"></td></tr>
			<tr>
				<td align="left" width="16%"><p class="arial-font-9"><b>Referral</b></p></td>
				<td align="left" width="30%"><p class="arial-font-9"><b>Offense/Petition</b></p></td>
				<td align="left" width="16%"><p class="arial-font-9"><b>Off/Pet</b></p></td>
				<td align="left" width="22%"><p class="arial-font-9"><b>Intake Decision/</b></p></td>
				<td align="left" width="16%"><p class="arial-font-9"><b>I/C</b></p></td>
			</tr>
			<tr>
				<td align="left" width="16%"><p class="arial-font-9"><u><b>Date</b></u></p></td>
				<td align="left" width="30%"><p class="arial-font-9"><u><b>Allegation</b></u></p></td>
				<td align="left" width="16%"><p class="arial-font-9"><u><b>Date</b></u></p></td>
				<td align="left" width="22%"><p class="arial-font-9"><u><b>Court Disposition</b></u></p></td>
				<td align="left" width="16%"><p class="arial-font-9"><u><b>Date</b></u></p></td>
			</tr>
			<c:set var="previousReferralList" value="${reportInfo.previousReferralDetails}" scope="session"/>
			<c:forEach var="previousReferral" items="${previousReferralList}" varStatus="status">			
				<logic:equal name="previousReferral" property="petitionAvailable" value="true">
					<tr>
						<td align="left" width="16%"><p class="arial-font-9"><c:out value="${previousReferral.referralData.referralDateString}"/>
						</p></td>						
						<td align="left" width="30%"><p class="arial-font-9"><c:out value="${previousReferral.petitionAllegation}"/></p></td>
						<td align="left" width="16%"><fmt:formatDate value="${previousReferral.petitionFileDate}" pattern="MMM d, yyyy" var="formattedAdmitDate" />
							<c:out value="${formattedAdmitDate}"/></td>
						<td align="left" width="22%"><p class="arial-font-9"><c:out value="${previousReferral.courtDisposition}"/>
						</p></td>
						<td align="left" width="16%"><p class="arial-font-9"><c:out value="${previousReferral.referralData.courtDateString}"/>
						</p></td>
					</tr>	
				</logic:equal>				
				<c:set var="offensesList" value="${previousReferral.offenses}" scope="session"/>
				<logic:equal name="previousReferral" property="petitionAvailable" value="false">
					<c:forEach var="offense" items="${offensesList}" varStatus="status">
						<tr>
							<td align="left" width="16%"><p class="arial-font-9"><c:out value="${previousReferral.referralData.referralDateString}"/>
							</p></td>							
							<td align="left" width="30%"><p class="arial-font-9"><c:out value="${offense.petitionAllegation}"/></p></td>
							<td align="left" width="16%"><fmt:formatDate value="${offense.offenseDate}" pattern="MMM d, yyyy" var="formattedAdmitDate" />
							<c:out value="${formattedAdmitDate}"/></td>
							<td align="left" width="22%"><p class="arial-font-9"><c:out value="${previousReferral.referralData.intakeDecision}"/>
							</p></td>
							<td align="left" width="16%"><fmt:formatDate value="${previousReferral.intakeDate}" pattern="MMM d, yyyy" var="formattedAdmitDate" />
								<p class="arial-font-9"><c:out value="${formattedAdmitDate}"/></p>
							</td>
						</tr>	
					</c:forEach>
				</logic:equal>	
			</c:forEach>
		</logic:notEmpty>
	</table>	
<!--  END PREVIOUS REFERRALS INFORMATION -->
<!--  FAMILY EMPLOYMENT/INCOME INFORMATION -->
	<table width="100%" border-style="none">
		<logic:notEmpty name="reportInfo" property="familyFinancialHistory">
			<tr><td align="left"></td></tr>
			<tr align="left">
				<td align="left"><u><b>FAMILY</b></u></td>
			</tr>
			<tr><td align="left"></td></tr>
			<c:set var="familyFinancialHistoryList" value="${reportInfo.familyFinancialHistory}" scope="session"/>
				<c:forEach var="familyMember" items="${familyFinancialHistoryList}" varStatus="status">
					<tr><td align="left">	
						<table width="100%" border-style="none">
							<tr>
								<td align="left" width="65%"><b>Family Personal Data </b></td>
								<td align="left" width="35%"></td>
							</tr>
							<tr>
								<td align="left" width="65%"><b><u>Guardian</u></b></td>
								<td align="left" width="35%"><b><u>Relationship</u></b></td>
							</tr>
							<tr>
								<td align="left"><c:out value="${familyMember.guardian}"/></td>
								<td align="left"><p><c:out value="${familyMember.relationship}"/></p></td>
							</tr>
							<tr>
								<td align="left" colspan="2"><b>Address: </b><c:out value="${familyMember.address}"/></td>
							</tr>
							<tr>
								<td align="left" colspan="2"><b>Phone #: </b><c:out value="${familyMember.formattedPhone}"/></td>
							</tr>
							<logic:equal name="reportInfo" property="allGuardiansInHome" value="false">
								<tr>
									<td align="left" width="65%"><b>Family Employment/Income</b></td>
									<td align="left" width="35%"></td>
								</tr>
								<tr>
									<td align="left" width="65%"><b>Total Gross Family Income: </b>$
										<c:out value="${familyMember.totalGross}"/>
									</td>
									<td align="left" width="35%"><b>Number of People in Household: </b><c:out value="${familyMember.numberLivingInHome}"/>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<table width="100%" border-style="none">
											<tr>
												<td align="left" width="30%"><p class="arial-font-9"><u><b>Employer</b></u></p></td>
												<td align="left" width="20%"><p class="arial-font-9"><u><b>Status</b></u></p></td>
												<td align="left" width="30%"><p class="arial-font-9"><u><b>Job Description</b></u></p></td>
												<td align="left" width="16%"><p class="arial-font-9"><u><b>Entry Date</b></u></p></td>
											</tr>
											<c:set var="employmentHistoryList" value="${familyMember.employmentHistory}" scope="session"/>
												<c:forEach var="employment" items="${employmentHistoryList}" varStatus="status">
													<tr>							
														<td align="left" width="30%"><p class="arial-font-9"><c:out value="${employment.placeEmployed}"/></p></td>
														<td align="left" width="20%"><p class="arial-font-9"><c:out value="${employment.employmentStatus}"/></p></td>
														<td align="left" width="34%"><p class="arial-font-9"><c:out value="${employment.jobDescription}"/>
														</p></td>
														<td align="left" width="16%"><fmt:formatDate value="${employment.entryDate}" pattern="MMM d, yyyy" var="formattedEntryDate" />
														<c:out value="${formattedEntryDate}"/></td>
													</tr>	
												</c:forEach>	
										</table>
									</td>
								</tr>
							</logic:equal>
							<logic:equal name="reportInfo" property="allGuardiansInHome" value="true">
								<tr><td align="left"></td></tr>
								<tr>
									<td align="left" width="65%"><b>Family Employment/Income</b></td>
									<td align="left" width="35%"></td>
								</tr>
								<tr>
									<td align="left" width="65%"><b>Total Gross Family Income: </b>$
										<c:out value="${reportInfo.totalGrossFamilyIncome}"/>
									</td>
									<td align="left" width="35%"><b>Number of People in Household: </b><c:out value="${reportInfo.numberInHousehold}"/>
									</td>
								</tr>
								<tr>
									<td align="left" colspan="2">
										<table width="100%" border-style="none">
											<tr>
												<td align="left" width="30%"><p class="arial-font-9"><u><b>Employed By</b></u></p></td>
												<td align="left" width="20%"><p class="arial-font-9"><u><b>Status</b></u></p></td>
												<td align="left" width="34%"><p class="arial-font-9"><u><b>Job Description</b></u></p></td>
												<td align="left" width="16%"><p class="arial-font-9"><u><b>Entry Date</b></u></p></td>
											</tr>
											<c:set var="employmentHistoryList" value="${familyMember.employmentHistory}" scope="session"/>
												<c:forEach var="employment" items="${employmentHistoryList}" varStatus="status">
													<tr>							
														<td align="left" width="30%"><p class="arial-font-9"><c:out value="${employment.placeEmployed}"/></p></td>
														<td align="left" width="20%"><p class="arial-font-9"><c:out value="${employment.employmentStatus}"/></p></td>
														<td align="left" width="34%"><p class="arial-font-9"><c:out value="${employment.jobDescription}"/></p></td>
														<td align="left" width="16%"><fmt:formatDate value="${employment.entryDate}" pattern="MMM d, yyyy" var="formattedEntryDate" />
															<p class="arial-font-9"><c:out value="${formattedEntryDate}"/></p>
														</td>
													</tr>	
												</c:forEach>	
										</table>
									</td>
								</tr>
							</logic:equal>
						</table>
					</td></tr>
				</c:forEach>	
		</logic:notEmpty>
	</table>
<!--  END FAMILY INFORMATION -->
<!--  CHILD INFORMATION -->
	<logic:equal name="reportInfo" property="generic" value="false">
		<table width="100%" border-style="none">
			<logic:notEmpty name="reportInfo" property="substanceAbuseInformation">
				<tr><td align="left"></td></tr>
				<tr align="center" >
					<td align="left" colspan="6" padding-top="10"><u><b>THE CHILD</b></u></td>
				</tr>
				<tr><td align="left"></td></tr>
				<tr align="left">
					<td align="left" colspan="6"><b>Drug and Alcohol Usage:</b></td>
				</tr>
				<tr>
					<td align="left" width="10%"><u><b>Onset Age</b></u></td>
					<td align="left" width="25%"><u><b>Drug Type</b></u></td>
					<td align="left" width="15%"><u><b>Frequency</b></u></td>
					<td align="left" width="20%"><u><b>Location of Use</b></u></td>
					<td align="left" width="15%"><u><b>Amount Spent</b></u></td>
					<td align="left" width="15%"><u><b>Degree</b></u></td>
				</tr>
				<c:set var="substanceAbuseInformationList" value="${reportInfo.substanceAbuseInformation}" scope="session"/>
					<c:forEach var="substanceAbuse" items="${substanceAbuseInformationList}" varStatus="status">
						<tr>
							<td align="left" width="15%"><p class="arial-font-9"><c:out value="${substanceAbuse.onsetAge}"/></p></td>
							<td align="left" width="20%"><p class="arial-font-9"><c:out value="${substanceAbuse.drugType}"/></p></td>
							<td align="left" width="15%"><p class="arial-font-9"><c:out value="${substanceAbuse.frequency}"/></p></td>
							<td align="left" width="20%"><p class="arial-font-9"><c:out value="${substanceAbuse.locationOfUse}"/></p></td>
							<td align="left" width="15%"><p class="arial-font-9"><c:out value="${substanceAbuse.amountSpent}"/></p></td>
							<td align="left" width="15%"><p class="arial-font-9"><c:out value="${substanceAbuse.degree}"/></p></td>
						</tr>
					</c:forEach>	
			</logic:notEmpty>
		</table>
		<table width="100%" border-style="none">
			<logic:notEmpty name="reportInfo" property="substanceAbuseTraits">
				<tr><td align="left"></td></tr>
				<tr align="left">
					<td align="left" colspan="2" padding-top="10"><b>Substance Abuse:</b></td>
				</tr>
				<tr>
					<td align="left" width="50%"><u><b>Description</b></u></td>
					<td align="left" width="50%"><u><b>Status</b></u></td>
				</tr>
				<c:set var="substanceAbuseTraitsList" value="${reportInfo.substanceAbuseTraits}" scope="session"/>
					<c:forEach var="substanceAbuseTrait" items="${substanceAbuseTraitsList}" varStatus="status">
						<tr>
							<td align="left" width="50%"><p class="arial-font-9"><c:out value="${substanceAbuseTrait.traitTypeDescription}"/></p></td>
							<td align="left" width="50%"><p class="arial-font-9"><c:out value="${substanceAbuseTrait.status}"/></p></td>
						</tr>
					</c:forEach>	
			</logic:notEmpty>
		</table>
		<table width="100%" border-style="none">
			<logic:notEmpty name="reportInfo" property="gangTraits">
				<tr><td align="left"></td></tr>
				<tr align="left">
					<td align="left" colspan="2" padding-top="10"><b>Gang Activity:</b></td>
				</tr>
				<tr>
					<td align="left" width="50%"><u><b>Description</b></u></td>
					<td align="left" width="50%"><u><b>Status</b></u></td>
				</tr>
				<c:set var="gangTraitsList" value="${reportInfo.gangTraits}" scope="session"/>
					<c:forEach var="gangTrait" items="${gangTraitsList}" varStatus="status">
						<tr>
							<td align="left" width="50%"><p class="arial-font-9"><c:out value="${gangTrait.traitTypeDescription}"/></p></td>
							<td align="left" width="50%"><p class="arial-font-9"><c:out value="${gangTrait.status}"/></p></td>
						</tr>
					</c:forEach>	
			</logic:notEmpty>
		</table>
		<table width="100%" border-style="none">
			<logic:notEmpty name="reportInfo" property="strengthTraits">
				<tr><td align="left"></td></tr>
				<tr align="left">
					<td align="left" colspan="2" padding-top="10"><b>Strengths:</b></td>
				</tr>
				<tr>
					<td align="left" width="50%"><u><b>Description</b></u></td>
					<td align="left" width="50%"><u><b>Status</b></u></td>
				</tr>
				<c:set var="strengthTraitsList" value="${reportInfo.strengthTraits}" scope="session"/>
					<c:forEach var="strengthTrait" items="${strengthTraitsList}" varStatus="status">
						<tr>
							<td align="left" width="50%"><p class="arial-font-9"><c:out value="${strengthTrait.traitTypeDescription}"/></p></td>
							<td align="left" width="50%"><p class="arial-font-9"><c:out value="${strengthTrait.status}"/></p></td>
						</tr>
					</c:forEach>	
			</logic:notEmpty>
		</table>
		<table width="100%" border-style="none">
			<logic:notEmpty name="reportInfo" property="juvenileFacilityStayRecord">
				<tr><td align="left"></td></tr>
				<tr align="left">
					<td align="left" colspan="4" padding-top="10"><b>Facility History:</b></td>
				</tr>
				<tr>
					<td align="left" width="35%"><u><b>Name</b></u></td>
					<td align="left" width="15%"><u><b>Admit Date</b></u></td>
					<td align="left" width="35%"><u><b>Admit Reason</b></u></td>
					<td align="left" width="15%"><u><b>Release Date</b></u></td>
				</tr>
				<c:set var="juvenileFacilityStayRecordList" value="${reportInfo.juvenileFacilityStayRecord}" scope="session"/>
					<c:forEach var="facilityStayRecord" items="${juvenileFacilityStayRecordList}" varStatus="status">
						<tr>
							<td align="left" width="35%"><p class="arial-font-9"><c:out value="${facilityStayRecord.facilityName}"/></p></td>
							<td align="left" width="15%">
								<fmt:formatDate value="${facilityStayRecord.admitDate}" pattern="MMM d, yyyy" var="formattedAdmitDate" />
								<p class="arial-font-9"><c:out value="${formattedAdmitDate}"/></p>
							</td>
							<td align="left" width="35%"><p class="arial-font-9"><c:out value="${facilityStayRecord.admitReasonDesc}"/></p></td>
							<td align="left" width="15%">
								<fmt:formatDate value="${facilityStayRecord.releaseDate}" pattern="MMM d, yyyy" var="formattedReleaseDate" />
								<p class="arial-font-9"><c:out value="${formattedReleaseDate}"/></p>
							</td>
						</tr>
					</c:forEach>	
			</logic:notEmpty>
		</table>
		<table width="100%" border-style="none">
			<logic:notEmpty name="reportInfo" property="employmentHistory">
				<tr><td align="left"></td></tr>
				<tr align="left">
					<td align="left" colspan="6"><b>Youth Employment History:</b></td>
				</tr>
				<tr>
					<td align="left" width="20%"><u><b>Employed By</b></u></td>
					<td align="left" width="15%"><u><b>Status</b></u></td>
					<td align="left" width="23%"><u><b>Supervisor</b></u></td>
					<td align="left" width="20%"><u><b>Job Description</b></u></td>
					<td align="left" width="6%"><u><b>Hours</b></u></td>
					<td align="left" width="16%"><u><b>Entry Date</b></u></td>
				</tr>
				<c:set var="employmentHistoryList" value="${reportInfo.employmentHistory}" scope="session"/>
					<c:forEach var="employment" items="${employmentHistoryList}" varStatus="status">
						<tr>
							<td align="left" width="20%"><p class="arial-font-9"><c:out value="${employment.placeEmployed}"/></p></td>
							<td align="left" width="15%"><p class="arial-font-9"><c:out value="${employment.employmentStatus}"/></p></td>
							<td align="left" width="23%"><p class="arial-font-9"><c:out value="${employment.supervisorName}"/></p></td>
							<td align="left" width="20%"><p class="arial-font-9"><c:out value="${employment.jobDescription}"/></p></td>
							<td align="left" width="6%"><p class="arial-font-9"><c:out value="${employment.workHours}"/></p></td>
							<td align="left" width="16%">
								<fmt:formatDate value="${employment.entryDate}" pattern="MMM d, yyyy" var="formattedEntryDate" />
								<p class="arial-font-9"><c:out value="${formattedEntryDate}"/></p>
							</td>
						</tr>
					</c:forEach>	
			</logic:notEmpty>
		</table>
	</logic:equal>
<!--  END CHILD INFORMATION -->
<!--  EDUCATION HISTORY INFORMATION -->
	<!--  SCHOOL -->
	<table width="100%" border-style="none">
		<logic:notEmpty name="reportInfo" property="educationalHistory">
			<tr><td align="left"></td></tr>
			<tr align="left">
				<td align="left" colspan="7" padding-top="10"><u><b>SCHOOL</b></u></td>
			</tr>
			<tr><td align="center" colspan="7" border = "0"></td></tr>
			<tr>
				<td align="left" width="25%"><b>Academic Performance:</b></td>
				<td align="left" width="5%"></td>
				<td align="left" width="18%"><b>Appropriate</b></td>
				<td align="left" width="3%"></td>
				<td align="left" width="16%"><b>Last</b></td>
				<td align="left" width="16%"><b>Enrollment</b></td>
				<td align="left" width="17%"></td>
			</tr>
			<tr>
				<td align="left" width="25%"><u><b>Name</b></u></td>
				<td align="left" width="5%"><u><b>Grade</b></u></td>
				<td align="left" width="18%"><u><b>Level</b></u></td>
				<td align="left" width="3%"><u><b>GPA</b></u></td>
				<td align="left" width="16%"><u><b>Attended</b></u></td>
				<td align="left" width="16%"><u><b>Status</b></u></td>
				<td align="left" width="17%"><u><b>Verified</b></u></td>
			</tr>
			<c:set var="educationalHistoryList" value="${reportInfo.educationalHistory}" scope="session"/>
				<c:forEach var="school" items="${educationalHistoryList}" varStatus="status">
				<logic:equal name="school" property="included" value="true">
					<tr>
						<td align="left" width="25%"><p class="arial-font-9">
							<logic:notEmpty name="school" property="specificSchoolName"><c:out value="${school.specificSchoolName}"/></logic:notEmpty>
							<logic:empty name="school" property="specificSchoolName"><c:out value="${school.district}"/></logic:empty></p>
						</td>
						<td align="right" width="5%"><c:out value="${school.gradeLevel}"/></td>
						<td align="left" width="18%"><p class="arial-font-9">
							<c:out value="${school.appropriateLevel}"/></p>
						</td>
						<td align="left" width="3%"><c:out value="${school.GPA}"/></td>
						<td align="left" width="16%">
							<fmt:formatDate value="${school.lastDateAttended}" pattern="MMM d, yyyy" var="formattedLastDateAttended" />
							<p class="arial-font-9"><c:out value="${formattedLastDateAttended}"/></p>
						</td>
						<td align="left" width="16%"><p class="arial-font-9">
							<c:out value="${school.enrollmentStatus}"/></p>
						</td>
						<td align="left" width="17%">
							<fmt:formatDate value="${school.verifiedDate}" pattern="MMM d, yyyy" var="formattedVerifiedDate" />
							<p class="arial-font-9"><c:out value="${formattedVerifiedDate}"/></p>
						</td>
					</tr>
				</logic:equal>
			</c:forEach>
		</logic:notEmpty>
	</table>
	<!--  END SCHOOL -->
	<!--  EDUCATION HISTORY -->
	<table width="100%" border-style="none">
		<logic:notEmpty name="reportInfo" property="educationalHistory">
			<tr><td align="left"></td></tr>
			<tr><td align="center" colspan="7" border = "0"></td></tr>
			<tr>
				<td align="left" width="30%"><b>Educational History:</b></td>
				<td align="left" width="16%"></td>
				<td align="left" width="14%"></td>
				<td align="left" width="10%"></td>
				<td align="left" width="10%"></td>
				<td align="left" width="10%"><b>Grades</b></td>
				<td align="left" width="10%"></td>
			</tr>
			<tr>
				<td align="left" width="30%"></td>
				<td align="left" width="16%"></td>
				<td align="left" width="14%"></td>
				<td align="left" width="10%"></td>
				<td align="left" width="10%"><b>Rule</b></td>
				<td align="left" width="10%"><b>Repeated/</b></td>
				<td align="left" width="10%"></td>
			</tr>
			<tr>
				<td align="left" width="30%"><u><b>Name</b></u></td>
				<td align="left" width="16%"><u><b>Date</b></u></td>
				<td align="left" width="14%"><u><b>Program</b></u></td>
				<td align="left" width="10%"><u><b>Participation</b></u></td>
				<td align="left" width="10%"><u><b>Infraction</b></u></td>
				<td align="left" width="10%"><u><b>(Number)</b></u></td>
				<td align="left" width="10%"><u><b>Status</b></u></td>
			</tr>
			<c:set var="educationalHistoryList" value="${reportInfo.educationalHistory}" scope="session"/>
				<c:forEach var="school" items="${educationalHistoryList}" varStatus="status">
				<logic:equal name="school" property="included" value="true">
					<tr>
						<td align="left" width="25%"><p class="arial-font-9">
						<logic:notEmpty name="school" property="specificSchoolName"><c:out value="${school.specificSchoolName}"/></logic:notEmpty>
							<logic:empty name="school" property="specificSchoolName"><c:out value="${school.district}"/></logic:empty></p>
						</td>
						<td align="left" width="16%">
							<fmt:formatDate value="${school.entryDate}" pattern="MMM d, yyyy" var="formattedEntryDate" />
							<p class="arial-font-9"><c:out value="${formattedEntryDate}"/></p>
						</td>
						<td align="left" width="14%"><p class="arial-font-9">
							<c:out value="${school.program}"/></p>
						</td>
						<td align="left" width="10%"><p class="arial-font-9">
							<c:out value="${school.participation}"/></p>
						</td>
						<td align="left" width="10%">
							<p class="arial-font-9"><c:out value="${school.ruleInfraction}"/></p>
						</td>
						<td align="left" width="10%"><p class="arial-font-9">
							<c:out value="${school.gradesRepeated}"/>
							<logic:notEmpty name="school" property="numberOfGradeRepeated">
								(<c:out value="${school.numberOfGradeRepeated}"/>)
							</logic:notEmpty>
							</p>
						</td>
						<td align="left" width="10%"><p class="arial-font-9">
							<c:out value="${school.status}"/></p>
						</td>
					</tr>
				</logic:equal>
			</c:forEach>
		</logic:notEmpty>
	</table>
	<!--  END EDUCATION HISTORY -->
	<!--  ATTENDANCE -->
	<table width="100%" border-style="none">
		<logic:notEmpty name="reportInfo" property="schoolAttendanceTraits">
			<tr align="left">
				<td align="left" colspan="2" padding-top="10"><b>Attendance:</b></td>
			</tr><tr>
				<td align="left" width="65%"><u><b>Description</b></u></td>
				<td align="left" width="35%"><u><b>Status</b></u></td>
			</tr>
			<c:set var="schoolAttendanceTraitsList" value="${reportInfo.schoolAttendanceTraits}" scope="session"/>
				<c:forEach var="schoolAttendanceTrait" items="${schoolAttendanceTraitsList}" varStatus="status">
					<tr>
						<td align="left" width="65%"><p class="arial-font-9">
							<c:out value="${schoolAttendanceTrait.traitTypeDescription}"/></p>
						</td>
						<td align="left" width="35%"><p class="arial-font-9">
							<c:out value="${schoolAttendanceTrait.status}"/></p>
						</td>
					</tr>
			</c:forEach>
		</logic:notEmpty>
	</table>
	<!--  END ATTENDANCE -->
	<!--  EDUCATIONAL PERFORMANCE -->
	<table width="100%" border-style="none">
		<logic:notEmpty name="reportInfo" property="educationalPerformanceTraits">
			<tr align="left">
				<td align="left" colspan="2" padding-top="10"><b>Educational Performance:</b></td>
			</tr><tr>
				<td align="left" width="65%"><u><b>Description</b></u></td>
				<td align="left" width="35%"><u><b>Status</b></u></td>
			</tr>
			<c:set var="educationalPerformanceTraitsList" value="${reportInfo.educationalPerformanceTraits}" scope="session"/>
				<c:forEach var="educationalPerformanceTrait" items="${educationalPerformanceTraitsList}" varStatus="status">
					<tr>
						<td align="left" width="65%"><p class="arial-font-9">
							<c:out value="${educationalPerformanceTrait.traitTypeDescription}"/></p>
						</td>
						<td align="left" width="35%"><p class="arial-font-9">
							<c:out value="${educationalPerformanceTrait.status}"/></p>
						</td>
					</tr>
			</c:forEach>
		</logic:notEmpty>
	</table>
	<!--  END EDUCATIONAL PERFORMANCE -->
	<!--  EDUCATIONAL PERFORMANCE -->
	<table width="100%" border-style="none">
		<logic:notEmpty name="reportInfo" property="schoolBehaviorTraits">
			<tr align="left">
				<td align="left" colspan="2" padding-top="10"><b>School Behavior:</b></td>
			</tr><tr>
				<td align="left" width="65%"><u><b>Description</b></u></td>
				<td align="left" width="35%"><u><b>Status</b></u></td>
			</tr>
			<c:set var="schoolBehaviorTraitsList" value="${reportInfo.schoolBehaviorTraits}" scope="session"/>
				<c:forEach var="schoolBehaviorTrait" items="${schoolBehaviorTraitsList}" varStatus="status">
					<tr>
						<td align="left" width="65%"><p class="arial-font-9">
							<c:out value="${schoolBehaviorTrait.traitTypeDescription}"/></p>
						</td>
						<td align="left" width="35%"><p class="arial-font-9">
							<c:out value="${schoolBehaviorTrait.status}"/></p>
						</td>
					</tr>
			</c:forEach>
		</logic:notEmpty>
	</table>
	<!--  END EDUCATIONAL PERFORMANCE -->
<!--  END EDUCATION HISTORY INFORMATION -->
<!--  MENTAL HEALTH TESTING HISTORY INFORMATION -->
	<logic:equal name="reportInfo" property="generic" value="false">
	<table width="100%" border-style="none">
		<logic:notEmpty name="reportInfo" property="mentalHealthTestingHistory">
			<tr align="left">
				<td align="left" colspan="4" padding-top="10"><u><b>TESTING HISTORY</b></u></td>
			</tr>
			<tr>
				<td align="left" width="15%"><b>Session</b></td>
				<td align="left" width="40%"></td>
				<td align="left" width="30%"></td>
				<td align="left" width="15%"></td>
			</tr>
			<tr>
				<td align="left" width="15%"><u><b>Date</b></u></td>
				<td align="left" width="40%"><u><b>Service Provider</b></u></td>
				<td align="left" width="30%"><u><b>Test Type</b></u></td>
				<td align="left" width="15%"><u><b>Test Name</b></u></td>
			</tr>
			<c:set var="mentalHealthTestingHistoryList" value="${reportInfo.mentalHealthTestingHistory}" scope="session"/>
				<c:forEach var="mentalHealthTest" items="${mentalHealthTestingHistoryList}" varStatus="status">
					<tr>
						<td align="left" width="15%">
							<fmt:formatDate value="${mentalHealthTest.testDate}" pattern="MMM d, yyyy" var="formattedTestDate" />
							<p class="arial-font-9"><c:out value="${formattedTestDate}"/></p>
						</td>
						<td align="left" width="40%"><p class="arial-font-9">
							<c:out value="${mentalHealthTest.serviceProviderName}"/></p>
						</td>
						<td align="left" width="30%"><p class="arial-font-9">
							<c:out value="${mentalHealthTest.testType}"/></p>
						</td>
						<td align="left" width="15%"><p class="arial-font-9">
							<c:out value="${mentalHealthTest.testName}"/></p>
						</td>
					</tr>
			</c:forEach>
		</logic:notEmpty>
	</table>
	</logic:equal>
<!--  END MENTAL HEALTH TESTING HISTORY INFORMATION -->
<!--  PROGRAM REFERRALS INFORMATION -->
	<table width="100%" border-style="none">
		<logic:notEmpty name="reportInfo" property="programReferrals">
			<tr align="center">
				<td align="left" colspan="5" padding-top="10"><u><b>PROGRAM REFERRALS</b></u></td>
			</tr>
			<tr><td align="center" colspan="5" border = "0"></td></tr>
			<tr>
				<td align="left" width="30%"><u><b>Program Name</b></u></td>
				<td align="left" width="20%"><u><b>Status</b></u></td>
				<td align="left" width="20%"><u><b>Outcome</b></u></td>
				<td align="left" width="15%"><u><b>Start Date</b></u></td>
				<td align="left" width="15"><u><b>End Date</b></u></td>
			</tr>
			<c:set var="programReferralsList" value="${reportInfo.programReferrals}" scope="session"/>
				<c:forEach var="programReferral" items="${programReferralsList}" varStatus="status">
				<tr>
					<td align="left" width="30%"><p class="arial-font-9">
						<c:out value="${programReferral.providerProgramName}"/></p>
					</td>
					<td align="left" width="20%"><p class="arial-font-9">
						<c:out value="${programReferral.referralStatusDescription}"/>-
						<c:out value="${programReferral.referralSubStatusDescription}"/></p>
					</td>
					<td align="left" width="20%"><p class="arial-font-9">
						<c:out value="${programReferral.outComeDesc}"/></p>
					</td>
					<td align="left" width="15%">
						<fmt:formatDate value="${programReferral.beginDate}" pattern="MMM d, yyyy" var="formattedBeginDate" />
						<p class="arial-font-9"><c:out value="${formattedBeginDate}"/></p>
					</td>
					<td align="left" width="15%">
						<fmt:formatDate value="${programReferral.endDate}" pattern="MMM d, yyyy" var="formattedEndDate" />
						<p class="arial-font-9"><c:out value="${formattedEndDate}"/></p>
					</td>
				</tr>
			</c:forEach>
		</logic:notEmpty>	
	</table>
<!--  END PROGRAM REFERRALS INFORMATION -->
<!--  AREAS OF COMPLIANCE INFORMATION -->
	<table width="100%" border-style="none">
		<logic:notEmpty name="reportInfo" property="compliantSupervisionRules">
			<tr align="left">
				<td align="left" colspan="2" padding-top="10"><u><b>AREAS OF COMPLIANCE</b></u></td>
			</tr>
			<tr>
				<td align="left" width="65%"><u><b>Description</b></u></td>
				<td align="left" width="35%"><u><b>Status</b></u></td>
			</tr>
			<c:set var="compliantSupervisionRulesList" value="${reportInfo.compliantSupervisionRules}" scope="session"/>
				<c:forEach var="compliantSupervisionRule" items="${compliantSupervisionRulesList}" varStatus="status">
					<tr>
						<td align="left" width="65%"><p class="arial-font-9">
							<c:out value="${compliantSupervisionRule.resolvedDesc}"/></p>
						</td>
						<td align="left" width="35%"><p class="arial-font-9">
							<c:out value="${compliantSupervisionRule.completionStatus}"/></p>
						</td>
					</tr>
			</c:forEach>
		</logic:notEmpty>
	</table>
<!--  END AREAS OF COMPLIANCE INFORMATION -->
<!--  AREAS OF NON-COMPLIANCE INFORMATION -->
	<table width="100%" border-style="none">
		<logic:notEmpty name="reportInfo" property="noncompliantSupervisionRules">
			<tr align="left">
				<td align="left" colspan="2" padding-top="10"><u><b>AREAS OF NON-COMPLIANCE</b></u></td>
			</tr>
			<tr>
				<td align="left" width="65%"><u><b>Description</b></u></td>
				<td align="left" width="35%"><u><b>Status</b></u></td>
			</tr>
			<c:set var="noncompliantSupervisionRulesList" value="${reportInfo.noncompliantSupervisionRules}" scope="session"/>
				<c:forEach var="noncompliantSupervisionRule" items="${noncompliantSupervisionRulesList}" varStatus="status">
					<tr>
						<td align="left" width="65%"><p class="arial-font-9">
							<c:out value="${noncompliantSupervisionRule.resolvedDesc}"/></p>
						</td>
						<td align="left" width="35%"><p class="arial-font-9">
							<c:out value="${noncompliantSupervisionRule.completionStatus}"/></p>
						</td>
					</tr>
			</c:forEach>
		</logic:notEmpty>
	</table>
<!--  END AREAS OF NON-COMPLIANCE INFORMATION -->
<!--  JUSTICE OF THE PEACE COURT REFERRALS INFORMATION -->
	<table width="100%" border-style="none">
		<logic:notEmpty name="reportInfo" property="JPCourtReferralConvictions">
			<tr align="left">
				<td align="left" colspan="5" padding-top="10"><b>JUSTICE OF THE PEACE COURT REFERRALS:</b></td>
			</tr>
			<tr>
				<td align="left" width="8%"><p class="arial-font-9"></p></td>
				<td align="left" width="14%"><p class="arial-font-9"><b>Case</b></p></td>
				<td align="left" width="39%"><p class="arial-font-9"><b>Offense</b></p></td>
				<td align="left" width="26%"><p class="arial-font-9"><b>File</b></p></td>
				<td align="left" width="13%"><p class="arial-font-9"><b>Date of</b></p></td>
			</tr>
			<tr>
				<td align="left" width="8%"><p class="arial-font-9"><u><b>Court</b></u></p></td>
				<td align="left" width="14%"><p class="arial-font-9"><u><b>Number</b></u></p></td>
				<td align="left" width="39%"><p class="arial-font-9"><u><b>Description</b></u></p></td>
				<td align="left" width="26%"><p class="arial-font-9"><u><b>File Date</b></u></p></td>
				<td align="left" width="13%"><p class="arial-font-9"><u><b>Conviction</b></u></p></td>
			</tr>
			<c:set var="JPCourtReferralConvictionsList" value="${reportInfo.JPCourtReferralConvictions}" scope="session"/>
			<c:forEach var="JPCourtReferralConviction" items="${JPCourtReferralConvictionsList}" varStatus="status">
				<tr>
					<td align="left" width="8%"><p class="arial-font-9"><c:out value="${JPCourtReferralConviction.courtName}"/>
					</p></td>
					<td align="left" width="14%"><p class="arial-font-9"><c:out value="${JPCourtReferralConviction.caseNumber}"/>
					</p></td>
					
					<td align="left" width="39%"><p class="arial-font-9"><c:out value="${JPCourtReferralConviction.offenseDescription}"/></p></td>
					<td align="left" width="26%">
						<fmt:formatDate value="${JPCourtReferralConviction.fileDate}" pattern="MMM d, yyyy" var="formattedFileDate" />
						<p class="arial-font-9"><c:out value="${formattedFileDate}"/></p>
					</td>
					<td align="left" width="13%">
						<fmt:formatDate value="${JPCourtReferralConviction.convictionDate}" pattern="MMM d, yyyy" var="formattedConvictionDate" />
						<p class="arial-font-9"><c:out value="${formattedConvictionDate}"/></p>
					</td>
				</tr>		
			</c:forEach>
		</logic:notEmpty>
	</table>	
<!--  END JUSTICE OF THE PEACE COURT REFERRALS INFORMATION -->
<!--  WARRANT HISTORY INFORMATION -->
	<table width="100%" border-style="none">
		<logic:notEmpty name="reportInfo" property="warrantHistory">
			<tr align="left">
				<td align="left" colspan="4" padding-top="10"><u><b>WARRANT HISTORY</b></u></td>
			</tr>
			<tr>
				<td align="left" width="44%"><p class="arial-font-9"><u><b>Warrant Type</b></u></p></td>
				<td align="left" width="16%"><p class="arial-font-9"><u><b>Initiated</b></u></p></td>
				<td align="left" width="16%"><p class="arial-font-9"><u><b>Executed</b></u></p></td>
				<td align="left" width="24%"><p class="arial-font-9"><u><b>Status</b></u></p></td>
			</tr>
			<c:set var="warrantHistoryList" value="${reportInfo.warrantHistory}" scope="session"/>
			<c:forEach var="warrant" items="${warrantHistoryList}" varStatus="status">
				<tr>
					<td align="left" width="44%">
						<p class="arial-font-9"><c:out value="${warrant.warrantType}"/></p>
					</td>
					<td align="left" width="16%">
						<fmt:formatDate value="${warrant.dateOfIssue}" pattern="MMM d, yyyy" var="formattedDateOfIssue" />
						<p class="arial-font-9"><c:out value="${formattedDateOfIssue}"/></p>
					</td>
					<td align="left" width="16%">
						<fmt:formatDate value="${warrant.serviceDate}" pattern="MMM d, yyyy" var="formattedServiceDate" />
						<p class="arial-font-9"><c:out value="${formattedServiceDate}"/></p>
					</td>
					<td align="left" width="24%"><p class="arial-font-9">
						<c:out value="${warrant.warrantStatus}"/></p>
					</td>
				</tr>		
			</c:forEach>
		</logic:notEmpty>
	</table>	
<!--  END WARRANT HISTORY INFORMATION -->
<!--  DISPOSITIONAL ALTERNATIVES INFORMATION -->
	<table width="100%" border-style="none">
		<logic:notEmpty name="reportInfo" property="useCourtDispositionAlternatives">
			<tr align="left">
				<td align="left" width="100%" padding-top="10"><b>DISPOSITIONAL ALTERNATIVES</b></td>
			</tr>
			<tr>
				<td align="left" width="100%"><p class="arial-font-9">
					<c:out value="${reportInfo.courtDispositionAlternative1}"/></p>
				</td>
			</tr>
			<tr>
				<td align="left" width="100%"><p class="arial-font-9">
					<c:out value="${reportInfo.courtDispositionAlternative2}"/></p>
				</td>
			</tr>
			<tr>
				<td align="left" width="100%"><p class="arial-font-9">
					<c:out value="${reportInfo.courtDispositionAlternative3}"/></p>
				</td>
			</tr>
		</logic:notEmpty>
	</table>
<!--  END DISPOSITIONAL ALTERNATIVES INFORMATION -->
<!--  REASON FOR DETENTION INFORMATION -->
	<table width="100%" border-style="none">
		<logic:notEmpty name="reportInfo" property="detentionReasons">
			<tr align="left">
				<td align="left" colspan="3" padding-top="10"><u><b>REASON FOR DETENTION</b></u></td>
			</tr>
			<tr><td align="left" colspan="3"></td></tr>
			<tr>
				<td align="left" colspan="3"><p class="arial-font-9">
					<c:out value="${reportInfo.detentionReasons}"/></p>
				</td>
			</tr>
			<tr align="left">
				<td align="left" colspan="3" padding-top="10"><b>NOTIFICATION OF DETENTION HEARING:</b></td>
			</tr>
			<tr><td align="left" colspan="3"></td></tr>
			<tr>
				<td align="left" width="30%"><p class="arial-font-9"><u><b>Assigned Court:</b></u></p></td>
				<td align="left" width="35%"><p class="arial-font-9"><u><b>Judge:</b></u></p></td>
				<td align="left" width="35%"></td>
			</tr>
			<c:set var="presentOffensesForGenericList" value="${reportInfo.presentOffensesForGeneric}" scope="session"/>
				<c:forEach var="presentOffenseFromList" items="${presentOffensesForGenericList}" varStatus="status">
					<tr>
						<td align="left" width="30%">
							<p class="arial-font-9"><c:out value="${presentOffenseFromList.courtName}"/></p>
						</td>
						<td align="left" colspan="2">
							<p class="arial-font-9"><c:out value="${presentOffenseFromList.judgesName}"/></p>
						</td>
					</tr>		
				</c:forEach>
			<tr>
				<td align="left" colspan="3"><p class="arial-font-9"><u><b>Attorney Notification Date:</b></u></p></td>
			</tr>
			<tr>
				<td align="left" colspan="3">
					<p class="arial-font-9"><c:out value="${reportInfo.attorneyNotificationDateAsString}"/></p>
				</td>
			</tr>
			<tr>
				<td align="left" width="30%"><p class="arial-font-9"><u><b>Person Notified:</b></u></p></td>
				<td align="left" width="35%"><p class="arial-font-9"><u><b>Notified By Whom:</b></u></p></td>
				<td align="left" width="35%"><p class="arial-font-9"><u><b>Date / Time Notified:</b></u></p></td>
			</tr>
			<tr>
				<td align="left" width="30%">
					<p class="arial-font-9"><c:out value="${reportInfo.notifiedPerson}"/></p>
				</td>
				<td align="left" width="24%"><p class="arial-font-9"><c:out value="${reportInfo.currentUser}"/></p>
				</td>
				<td align="left" width="16%">
					<p class="arial-font-9"><c:out value="${reportInfo.personNotificationDateAsString}"/></p>
				</td>
			</tr>	
			<tr>
				<td align="left" colspan="3"><p class="arial-font-9"><u><b>Notification Method:</b></u></p></td>
			</tr>
			<tr>
				<td align="left" colspan="3">
					<p class="arial-font-9"><c:out value="${reportInfo.notifiedMethod}"/></p>
				</td>
			</tr>			
		</logic:notEmpty>
	</table>	
<!--  END REASON FOR DETENTION INFORMATION -->
	<table width="100%" border-style="none">
		<tr align="left">
			<td align="left" width="50%" padding-top="20"></td>
			<td align="left" width="50%" padding-top="20">Respectfully Submitted,</td>
		</tr>
		<tr><td align="left" colspan="2"></td></tr>
		<tr><td align="left" colspan="2"></td></tr>
		<tr><td align="left" colspan="2"></td></tr>
		<tr><td align="left" colspan="2"></td></tr>
		<tr><td align="left" colspan="2"></td></tr>
		<tr><td align="left" colspan="2"></td></tr>
		<tr align="left">
			<td align="left" width="50%"></td>
			<td align="left" width="50%">By:____________________________________</td>
		</tr>
		<tr>
			<td align="left" width="50%"></td>
			<td align="left" width="50%"><p class="arial-font-10">
				<c:out value="${reportInfo.probationOfficer}"/> / Juvenile Probation Officer</p>
			</td>
		</tr>
	</table>
</body>
</pdf>