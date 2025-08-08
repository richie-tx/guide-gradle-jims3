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
<jsp:useBean id="reportInfo" class="messaging.interviewinfo.to.SocialHistoryReportDataTO" scope="session" />
<pdf onload="pdf:130">
<head>
	<meta name="title" value="<%=PDFReport.SOCIAL_HISTORY_REPORT_GENERIC.getReportName()%>"/>
	<meta name="author" value="JIMS2, Harris County"/>
	<meta name="subject" value="<%=PDFReport.SOCIAL_HISTORY_REPORT_GENERIC.getReportName()%>"/>
	<meta name="security-password" value="406"/>
	<meta name="encryption-algorithm" value = "128bit" />
	<meta name="access-level" value = "print-all change-none extract-none" />
		<meta name="base" value="file:c:/BFOImages/" /> 
<!-- STYLE SHEET LINK -->
<style>
#watermarkbody { font-size: 150; font-family: Helvetica; color:#F0F0F0; }
body
	{	margin-left: .0in;
    	margin-right: .0in;
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
span.underline
	{	border-bottom:1px solid #555;
		}
table.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;}
table.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;}
table.arial-font-11
	{font-size:11;
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
tr.arial-font-9
	{font-size:9;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
tr.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;
		text-align:left}
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
td.arial-font-10
	{font-size:10;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-12
	{font-size:12;
		font-family:"Arial", Helvetica, sans-serif;}
p.arial-font-13
	{font-size:13;
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
		<macro id="watermark" >
			<p id="watermarkbody" rotate="-30" valign="middle" align="center">
			DRAFT
			</p>
		</macro>
		<macro id="myfooter">
			<div class="footer">
				<!--  Footer -->
				<table width="100%">
				
				 <tr>
				 	
				 	<td align="center" width="100%">
				 		<p class="arial-font-8 centered">
							Page <pagenumber/> of <totalpages/>
						</p>
					</td>
					
				 </tr>
				</table>
			</div>
		</macro>		
	</macrolist>
</head>
<c:if test="${reportInfo.draft == 'Y'}">
	<body background-macro="watermark" footer="myfooter" footer-height="5mm">
</c:if>
<c:if test="${reportInfo.draft != 'Y'}">
	<body footer="myfooter" footer-height="5mm">
</c:if>
<div class="body">
<!--  Header information -->
	<table width="100%" border-style="none">
			<tr align="center">
				<td align="center" width="100%"><p class="arial-font-13"><b>COURT REPORT INFORMATION SUMMARY</b></p></td>
			</tr>
			<tr align="center">
				<td align="center" width="100%"><p class="arial-font-13"><b>(CRIS)</b></p></td>
			</tr>
	</table>
<!--  JUVENILE INFORMATION -->
	<table width="100%" border-style="none">
		<tr align="center">
			<td align="left" width="35%"></td>
			<td align="left" width="30%"></td>
			<td align="left" width="35%"></td>
		</tr>
		<tr align="center">
			<td align="left"><b>Name: </b><%=reportInfo.getJuvenileName()%></td>
			<td align="left"></td>
			<td align="left"><b>Juvenile #: </b><%=reportInfo.getJuvenileNumber()%></td>
		</tr>
		<tr align="center">
			<td align="left"><b>Race </b><%=reportInfo.getRace()%></td>
			<td align="left"><b>Multiracial? </b><%=reportInfo.getMultiracialAsString()%></td>
			<td align="left"><b>Hispanic? </b><%=reportInfo.getHispanicAsString()%></td>
		</tr>
		<tr align="center">
			<td align="left"><b>Gender: </b><%=reportInfo.getGender()%></td>
			<td align="left"></td>
			<td align="left"><b>Current Age: </b><%=reportInfo.getAgeInYears()%></td>
		</tr>
		<tr align="center">
			<td align="left"><b>DOB: </b><%=reportInfo.getDateOfBirth()%></td>
			<td align="left"><b>Verified? </b><%=reportInfo.getVerifiedDOBAsString()%></td>
			<td align="left"></td>
		</tr>
		<tr align="center">
			<td align="left" colspan="3"><b>Petition # - Amendment #: </b><%=reportInfo.getAllPetitionAmendNumForGeneric()%></td>
		</tr>
		<tr align="center">
			<td align="left"><b>Court Date: </b><%=reportInfo.getCourtDateAsString()%></td>
			<td align="left"><b>Court </b><%=StringUtil.isNotNull(reportInfo.getPresentOffense().getCourtCodeId())%></td>
			<logic:equal name="reportInfo" property="inFacility" value="false">
				<td align="left"><b>Detention Admit Date: </b>
					<c:out value="${reportInfo.detentionAdmitDate}"/>
				</td>
			</logic:equal>
		</tr>
		<logic:equal name="reportInfo" property="inFacility" value="true">
		<tr align="center">
			
				<td align="left"><b>Facility Admit Date: </b>
					<c:out value="${reportInfo.detentionAdmitDate}"/>
				</td>
		</tr>
		<tr>		
				<td align="left" colspan="2"><b>Facility Name: </b>
				<c:out value="${reportInfo.facilityName}"/>
				</td>
		
		</tr>
		</logic:equal>
		<tr><td align="left" colspan="4" border-top = "0" padding-bottom="5"></td></tr>
	</table>
</div>
<!--  END JUVENILE INFORMATION -->
<!--  PRESENT REFERRAL INFORMATION -->
	<table width="100%" border-style="none" class="arial-font-10">
		<logic:notEmpty name="reportInfo" property="presentOffensesForGeneric">
			<tr align="left">
				<td align="center"><p class="arial-font-12"><u><b>PRESENT REFERRAL</b></u></p></td>
			</tr>
			<tr><td align="left"></td></tr>
			<c:set var="presentOffensesForGenericList" value="${reportInfo.presentOffensesForGeneric}" scope="session"/>
				<c:forEach var="presentOffenseFromList" items="${presentOffensesForGenericList}" varStatus="status">
				<tr><td align="left">	
					<table width="100%" border-style="none">
						<tr>
							<td align="left" width="65%"></td>
							<td align="left" width="35%"></td>
						</tr>
						<tr>
							<td align="left">
								<p class="arial-font-10"><b>Referral #: </b><c:out value="${presentOffenseFromList.referralNumber}"/></p>
							</td>
							<td align="left"></td>
						</tr>
						<tr>
							<td align="left">
								<p class="arial-font-10"><b>Nature of Offense: </b><c:out value="${presentOffenseFromList.presentOffense}"/></p>
							</td>
							<td align="left">
								<p class="arial-font-10"><b>Date of Offense: </b>
									<fmt:formatDate value="${presentOffenseFromList.offenseDate}" pattern="MMM d, yyyy" var="formattedOffenseDate" />
										<c:out value="${formattedOffenseDate}"/>
								</p>
							</td>
						</tr>
						<tr><td align="left" colspan="4" border-top = "0" padding-bottom="5"></td></tr>
					</table>
					
				</td></tr>
			</c:forEach>
		</logic:notEmpty>	
	</table>
	
<!-- added for Bug:12932 TRANSFERRED OFFENSE DETAILS FOR PRESENT REFERRALS -->
	<table width="100%" border-style="none" class="arial-font-10">
		<c:set var="presentOffensesForGenericList" value="${reportInfo.presentOffensesForGeneric}" scope="session"/>
			<c:forEach var="presentOffenseFromList" items="${presentOffensesForGenericList}" varStatus="status">
				<logic:equal name="presentOffenseFromList" property="transferredOffensePresent" value="true">										
					<tr>
						<td align="center" colspan="5"><p class="arial-font-12"><u><b>TRANSFERRED OFFENSE DETAILS</b></u></p></td></tr>
					<tr>
						<td align="left"><p class="arial-font-10"><b>Referral Number</b></p></td>
						<td align="left"><p class="arial-font-10"><b>Originating County</b></p></td>
						<td align="left" colspan="2"><p class="arial-font-10"><b>Originating Offense</b></p></td>				
						<td align="left"><p class="arial-font-10"><b>Adjudication Date</b></p></td>
						
					</tr>
					<tr>
						<td align="left"><p class="arial-font-10"><c:out value="${presentOffenseFromList.referralNumber}"/>
						</p></td>
						<td align="left"><p class="arial-font-10"><c:out value="${presentOffenseFromList.transferredOffenseFromCounty}"/>
						</p></td>
						<td align="left" colspan="2"><p class="arial-font-10"><c:out value="${presentOffenseFromList.transferredOffenseOriginatingOff}"/>
						</p></td>							
						<td align="left"><p class="arial-font-10"><c:out value="${presentOffenseFromList.transferredOffenseAdjuDate}"/></p></td>
						
					</tr>						
					<tr><td align="left" colspan="4" border-top = "0" padding-bottom="5"></td></tr>
					<tr><td align="left" colspan="4" border-top = "0" padding-bottom="5"></td></tr>
			</logic:equal>
		</c:forEach>
	</table>
<!-- ended -->
<!--  END PRESENT REFERRAL INFORMATION -->	
<!--  SUBSEQUENT REFERRALS INFORMATION -->
	<table width="100%" border-style="none" class="arial-font-10">
		<logic:notEmpty name="reportInfo" property="subsequentReferralDetails">
			<tr align="left">
				<td align="center"><p class="arial-font-12"><u><b>SUBSEQUENT REFERRALS</b></u></p></td>
			</tr>
			<tr><td align="left"></td></tr>
			<c:set var="subsequentReferralsList" value="${reportInfo.subsequentReferralDetails}" scope="session"/>
				<c:forEach var="subsequentReferral" items="${subsequentReferralsList}" varStatus="status">
				<tr><td align="left" class="arial-font-10">	
					<table width="100%" border-style="none">
						<tr>
							<td align="left" width="70%"></td>
							<td align="left" width="30%"></td>
						</tr>
						<tr>
							<logic:equal name="subsequentReferral" property="petitionAvailable" value="true">
								<td align="left"><b>Petition Allegtion: </b>
									<c:out value="${subsequentReferral.petitionAllegation}"/>
								</td>
							</logic:equal>
							<logic:equal name="subsequentReferral" property="petitionAvailable" value="false">
								<td align="left"><b>Petition Allegtion: </b>None</td>
							</logic:equal>
							<td align="left"><b>Referral #: </b>
								<c:out value="${subsequentReferral.referralData.referralNumber}"/>
							</td>
						</tr>
						<tr>
							<td align="left"><b>Offense(s): </b></td>
							<td align="left"><b>Offense Dates(s): </b></td>
						</tr>
						<c:set var="offensesList" value="${subsequentReferral.offenses}" scope="session"/>
							<c:forEach var="offense" items="${offensesList}" varStatus="status">
								<tr><td align="left" colspan="2">	
									<table width="100%" border-style="none">
										<tr>
											<td align="left" width="70%"></td>
											<td align="left" width="30%"></td>
										</tr>
										<tr>
											<td align="left" width="70%"><p class="arial-font-10">
												<c:out value="${offense.petitionAllegation}"/></p>
											</td>
											<td align="left" width="30%"><p class="arial-font-10">
												<c:out value="${offense.offenseDateAsString}"/></p>
											</td>
										</tr>
									</table>
								</td></tr>
							</c:forEach>
							<tr>
								<td align="left"><p class="arial-font-10"><b>Intake Decision: </b>
									<c:out value="${subsequentReferral.referralData.intakeDecision}"/></p>
								</td>
								<td align="left"><p class="arial-font-10"><b>Intake Date: </b> 
									<c:out value="${subsequentReferral.intakeDateString}"/></p>
								</td>
							</tr>
							<tr>
								<td align="left" colspan="2"><b>Disposition: </b>
									<c:out value="${subsequentReferral.courtDisposition}"/>
								</td>
							</tr>
							<tr><td align="left" colspan="2" border-top = "0" padding-bottom="5"></td></tr>
					</table>
				</td></tr>
			</c:forEach>
		</logic:notEmpty>
	</table>
	
<!-- added for Bug:12932 TRANSFERRED OFFENSE DETAILS FOR SUBSEQUENT REFERRALS -->
	<table width="100%" border-style="none"  class="arial-font-10">
		<logic:equal name="reportInfo" property="hasSubsequentTransferredOffense" value="true">
			<tr>
				<td align="center" colspan="5"><p class="arial-font-12"><u><b>TRANSFERRED OFFENSE DETAILS</b></u></p></td>
			</tr>
			<tr>
				<td align="left"><p class="arial-font-10"><b>Referral Number</b></p></td>
				<td align="left"><p class="arial-font-10"><b>Originating County</b></p></td>
				<td align="left" colspan="2"><p class="arial-font-10"><b>Originating Offense</b></p></td>				
				<td align="left"><p class="arial-font-10"><b>Adjudication Date</b></p></td>
			</tr>
			<c:set var="subsequentReferralsList" value="${reportInfo.subsequentReferralDetails}" scope="session"/>
				<c:forEach var="subsequentReferral" items="${subsequentReferralsList}" varStatus="status">
					<logic:equal name="subsequentReferral" property="transferredOffensePresent" value="true">	
					<tr>
						<td align="left"><p class="arial-font-10"><c:out value="${subsequentReferral.referralNumber}"/>
						</p></td>
						<td align="left"><p class="arial-font-10"><c:out value="${subsequentReferral.transferredOffenseFromCounty}"/>
						</p></td>
						<td align="left" colspan="2"><p class="arial-font-10"><c:out value="${subsequentReferral.transferredOffenseOriginatingOff}"/>
						</p></td>							
						<td align="left"><p class="arial-font-10"><c:out value="${subsequentReferral.transferredOffenseAdjuDate}"/></p></td>						
					</tr>						
					<tr><td align="left" colspan="4" border-top = "0" padding-bottom="5"></td></tr>
					<tr><td align="left" colspan="4" border-top = "0" padding-bottom="5"></td></tr>
				</logic:equal>
			</c:forEach>
		</logic:equal>
	</table>
<!-- ended -->
<!--  END SUBSEQUENT REFERRALS INFORMATION -->	
<!--  FAMILY INFORMATION -->
	<table width="100%" border-style="none" class="arial-font-10">
		<tr align="left">
			<td align="center"><p class="arial-font-12"><u><b>FAMILY</b></u></p></td>
		</tr>
		<tr><td align="left"></td></tr>
		<c:set var="familyInformationList" value="${reportInfo.familyInformation}" scope="session"/>
			<c:forEach var="familyMember" items="${familyInformationList}" varStatus="status">
				<logic:equal name="familyMember" property="included" value="true">
					<tr><td align="left">	
						<table width="100%" border-style="none" class="arial-font-10">
							<tr>
								<td align="left" width="9%"></td>
								<td align="left" width="5%"></td>
								<td align="left" width="16%"></td>
								<td align="left" width="31%"></td>
								<td align="left" width="20%"></td>
								<td align="left" width="19%"></td>
							</tr>
							<tr>
								<td align="left" colspan="4"><b>Name: </b><c:out value="${familyMember.familyMemberName}"/></td>
								<td align="left" colspan="2"><p><b>Relationship: </b>
									<c:out value="${familyMember.relationship}"/></p>
								</td>
							</tr>
							<tr>
								<td align="left" colspan="4"><b>Address: </b><c:out value="${familyMember.address}"/></td>
								<td align="left" colspan="2"><b>Phone #: </b><c:out value="${familyMember.formattedPhone}"/></td>
							</tr>
							<tr>
								<td align="left" colspan="2" width="45%"><b>Guardian:</b>
									<logic:equal name="familyMember" property="guardianStatus" value="true">YES</logic:equal>
									<logic:notEqual name="familyMember" property="guardianStatus" value="true">NO</logic:notEqual>
								</td>
								<td align="left" colspan="2" width="5%"><b>Lives With:</b>
									<logic:equal name="familyMember" property="inHome" value="true">YES</logic:equal>
									<logic:notEqual name="familyMember" property="inHome" value="true">NO</logic:notEqual>
								</td>
								<td align="left" colspan="2" width="50%"><b>Parental Rights Terminated:</b>
									<logic:equal name="familyMember" property="parentalRightsTerminated" value="true">YES</logic:equal>
									<logic:notEqual name="familyMember" property="parentalRightsTerminated" value="true">NO</logic:notEqual>
								</td>
								
							</tr>
							<tr>
								<td align="left" colspan="2" width="45%"><b>Incarcerated:</b>
									<logic:equal name="familyMember" property="incarcerated" value="true">YES</logic:equal>
									<logic:notEqual name="familyMember" property="incarcerated" value="true">NO</logic:notEqual>
								</td>
								<td align="left" colspan="2" width="5%"><b>Deceased:</b>
									<logic:equal name="familyMember" property="deceased" value="true">YES</logic:equal>
									<logic:notEqual name="familyMember" property="deceased" value="true">NO</logic:notEqual>
								</td>
								<td align="left" colspan="2" width="25%"><b>Primary Care Giver:</b>
									<logic:equal name="familyMember" property="primaryCareGiver" value="true">YES</logic:equal>
									<logic:notEqual name="familyMember" property="primaryCareGiver" value="true">NO</logic:notEqual>
								</td>
							</tr>
						</table>
					</td></tr>
				</logic:equal>
			</c:forEach>	
	</table>
<!--  END FAMILY INFORMATION -->
<!--  EDUCATION HISTORY INFORMATION -->
	<table width="100%" border-style="none" class="arial-font-10">
		<tr>
			<td align="left" width="35%"></td>
			<td align="left" width="5%"></td>
			<td align="left" width="30%"></td>
			<td align="left" width="30%"></td>
		</tr>
		<logic:notEmpty name="reportInfo" property="educationalHistory">
			<tr align="center">
				<td align="center" colspan="4" border = "0"><p class="arial-font-12"><u><b>SCHOOL</b></u></p></td>
			</tr>
			<tr><td align="center" colspan="4" border = "0"></td></tr>
			<tr>
				<td align="left" width="30%"><p class="arial-font-10"><u><b>Name</b></u></p></td>
				<td align="left" width="5%"><p class="arial-font-10"><u><b>Grade</b></u></p></td>
				<td align="left" width="30%"><p class="arial-font-10"><u><b>Program</b></u></p></td>
				<td align="left" width="20%"><p class="arial-font-10"><u><b>Enrollment Status</b></u></p></td>
				<td align="left" width="15%"><p class="arial-font-10"><u><b>School Services</b></u></p></td>
			</tr>
			<c:set var="educationalHistoryList" value="${reportInfo.educationalHistory}" scope="session"/>
				<c:forEach var="school" items="${educationalHistoryList}" varStatus="status">
				<tr>
					<td align="left" width="25%"><p class="arial-font-9">
						<logic:notEmpty name="school" property="specificSchoolName"><c:out value="${school.specificSchoolName}"/></logic:notEmpty>
							<logic:empty name="school" property="specificSchoolName"><c:out value="${school.district}"/></logic:empty></p>
						</td>
					<td align="right" width="5%"><c:out value="${school.gradeLevel}"/></td>
					<td align="left" width="30%"><p class="arial-font-10">
						<c:out value="${school.program}"/></p>
					</td>
					<td align="left" width="20%"><p class="arial-font-10">
						<c:out value="${school.enrollmentStatus}"/></p>
					</td>
					<td align="left" width="15%"><p class="arial-font-10">
						<c:out value="${school.educationService}"/></p>
					</td>
				</tr>
				<tr><td align="center" colspan="4" border = "0" padding-top="5"></td></tr>
			</c:forEach>
		</logic:notEmpty>
	</table>
<!--  END EDUCATION HISTORY INFORMATION -->

<!--  PRESENT REFERRALS INFORMATION -->
<!-- 
	<table width="100%" border-style="none">
		
		<logic:notEmpty name="reportInfo" property="currentReferralDetails">
			<tr align="left">
				<td align="center" colspan="5" border = "0"><p class="arial-font-12"><u><b>PRESENT REFERRALS</b></u></p></td>
			</tr>
			<tr>
				<td align="left"><p class="arial-font-10"><u><b>Referral Number</b></u></p></td>
				<td align="left" ><p class="arial-font-10"><u><b>Referral Date</b></u></p></td>
				<td align="left"><p class="arial-font-10"><u><b>Offense/Allegation</b></u></p></td>
				<td align="left"><p class="arial-font-10"><u><b>Disposition</b></u></p></td>
				<td align="left"><p class="arial-font-10"><u><b>Disposition Date</b></u></p></td>
				</tr>
			<c:set var="currentReferralList" value="${reportInfo.currentReferralDetails}" scope="session"/>
			<c:forEach var="currentReferral" items="${currentReferralList}" varStatus="status">
				<logic:equal name="currentReferral" property="petitionAvailable" value="true">
					<tr>
						<td align="left" ><p class="arial-font-10"><c:out value="${currentReferral.referralData.referralNumber}"/>
						</p></td>
						<td align="left"><p class="arial-font-10"><c:out value="${currentReferral.referralData.referralDateString}"/>
						</p></td>
						
						<td align="left"><p class="arial-font-10"><c:out value="${currentReferral.petitionAllegation}"/></p></td>
						<td align="left"><p class="arial-font-10"><c:out value="${currentReferral.courtDisposition}"/>
						</p></td>
						<td align="left"><p class="arial-font-10"><c:out value="${currentReferral.referralData.courtDateString}"/>
						</p></td>
						</tr>	
				</logic:equal>
				<c:set var="offensesList" value="${currentReferral.offenses}" scope="session"/>
				<logic:equal name="currentReferral" property="petitionAvailable" value="false">
					<c:forEach var="offense" items="${offensesList}" varStatus="status">
						<tr>
							<td align="left"><p class="arial-font-10"><c:out value="${currentReferral.referralData.referralNumber}"/>
							</p></td>
							<td align="left"><p class="arial-font-10"><c:out value="${currentReferral.referralData.referralDateString}"/>
							</p></td>
							
							<td align="left"><p class="arial-font-10"><c:out value="${offense.petitionAllegation}"/></p></td>
							<td align="left"><p class="arial-font-10"><c:out value="${currentReferral.referralData.intakeDecision}"/>
							</p></td>
							<td align="left"><p class="arial-font-10"><c:out value="${currentReferral.referralData.courtDateString}"/>
							</p></td>
						</tr>	
					</c:forEach>
				</logic:equal>
			</c:forEach>
		</logic:notEmpty>
	</table>	
	
-->
<!--  END PRESENT REFERRALS INFORMATION -->
<!--  Below Facility History Added for Task: 47899, US 11078 -->
<!--  BEGIN FACILITY HISTORY INFORMATION -->
	<table width="100%" border-style="none" class="arial-font-10">
			<logic:notEmpty name="reportInfo" property="facilityHistoryInfo">
				<tr align="left">
					<td align="center" colspan="5" border = "0"><p class="arial-font-12"><u><b>FACILITY HISTORY</b></u></p></td>
				</tr>
				<tr>
				<td align="left"><p class="arial-font-10"><u><b>Referral<br/> Number</b></u></p></td>
				<td align="left"><p class="arial-font-10"><u><b>Facility</b></u></p></td>
				<td align="left"><p class="arial-font-10"><u><b>Admit Date</b></u></p></td>
				<td align="left"><p class="arial-font-10"><u><b>Release Date</b></u></p></td>
				<td align="left"><p class="arial-font-10"><u><b>Released To</b></u></p></td>
			</tr>
		<c:set var="facilityHistoryInfoList" value="${reportInfo.facilityHistoryInfo}" scope="session"/>
			<c:forEach var="facilityHistoryInfo" items="${facilityHistoryInfoList}" varStatus="status">
					<tr>
						<td align="left" ><p class="arial-font-10"><c:out value="${facilityHistoryInfo.referralNumber}"/></p></td>
						<td align="left"><p class="arial-font-10"><c:out value="${facilityHistoryInfo.detainedFacilityDesc}"/></p></td>						
						<td align="left"><p class="arial-font-10"><c:out value="${facilityHistoryInfo.admitDateStr}"/></p></td>
						<td align="left"><p class="arial-font-10"><c:out value="${facilityHistoryInfo.releaseDateStr}"/></p></td>
						<td align="left"><p class="arial-font-10"><c:out value="${facilityHistoryInfo.relToDesc}"/></p></td>
					</tr>	
				<tr><td align="center" colspan="4" border = "0" padding-top="5"></td></tr>
			</c:forEach>
		</logic:notEmpty>
</table>

<!--  END FACILITY HISTORY INFORMATION -->

<!--  PREVIOUS REFERRALS INFORMATION -->
	
		<logic:notEmpty name="reportInfo" property="previousReferralDetails">
		<table width="100%" border-style="none" class="arial-font-10">
			<tr align="center">
				<td align="center" colspan="5" border = "0"><p class="arial-font-12"><u><b>PREVIOUS REFERRALS</b></u></p></td>
			</tr>
			</table>
			<table width="100%" border-style="none" class="arial-font-10">
			<!--<tr>
				<td align="left" width="8%"><p class="arial-font-10"><b>Referral</b></p></td>
				<td align="left" width="14%"><p class="arial-font-10"><b>Referral</b></p></td>
				<td align="left" width="39%"></td>
				<td align="left" width="26%"></td>
				<td align="left" width="13%"><p class="arial-font-10"><b>Disposition</b></p></td>
			</tr>
			--><tr>
				<td align="left"><p class="arial-font-10"><u><b>Referral Number</b></u></p></td>
				<td align="left" ><p class="arial-font-10"><u><b>Referral Date</b></u></p></td>
				<td align="left"><p class="arial-font-10"><u><b>Offense/Allegation</b></u></p></td>
				<td align="left"><p class="arial-font-10"><u><b>Disposition</b></u></p></td>
				<td align="left"><p class="arial-font-10"><u><b>Disposition Date</b></u></p></td>
				<td align="left"><p class="arial-font-10"><u><b>Probation End Date</b></u></p></td>
			</tr>
			<c:set var="previousReferralList" value="${reportInfo.previousReferralDetails}" scope="session"/>
			<c:forEach var="previousReferral" items="${previousReferralList}" varStatus="status">
				<logic:equal name="previousReferral" property="petitionAvailable" value="true">
					<tr>
						<td align="left" ><p class="arial-font-10"><c:out value="${previousReferral.referralData.referralNumber}"/>
						</p></td>
						<td align="left"><p class="arial-font-10"><c:out value="${previousReferral.referralData.referralDateString}"/>
						</p></td>
						
						<td align="left"><p class="arial-font-10"><c:out value="${previousReferral.petitionAllegation}"/></p></td>
						<td align="left"><p class="arial-font-10"><c:out value="${previousReferral.courtDisposition}"/>
						</p></td>
						<td align="left"><p class="arial-font-10"><c:out value="${previousReferral.referralData.courtDateString}"/>
						</p></td>
						<td align="left"><p class="arial-font-10"><c:out value="${previousReferral.referralData.probationEndDateString}"/>
							</p></td>
					</tr>	
				</logic:equal>
				<c:set var="offensesList" value="${previousReferral.offenses}" scope="session"/>
				<logic:equal name="previousReferral" property="petitionAvailable" value="false">
					<c:forEach var="offense" items="${offensesList}" varStatus="status">
						<tr>
							<td align="left"><p class="arial-font-10"><c:out value="${previousReferral.referralData.referralNumber}"/>
							</p></td>
							<td align="left"><p class="arial-font-10"><c:out value="${previousReferral.referralData.referralDateString}"/>
							</p></td>
							
							<td align="left"><p class="arial-font-10"><c:out value="${offense.petitionAllegation}"/></p></td>
							<td align="left"><p class="arial-font-10"><c:out value="${previousReferral.referralData.intakeDecision}"/>
							</p></td>
							<td align="left"><p class="arial-font-10"><c:out value="${previousReferral.referralData.courtDateString}"/>
							</p></td>
							<td align="left"><p class="arial-font-10"><c:out value="${previousReferral.referralData.probationEndDateString}"/>
							</p></td>
							</tr>	
					</c:forEach>
				</logic:equal>
			</c:forEach>
			</table>
		</logic:notEmpty>
		
<!--  END PREVIOUS REFERRALS INFORMATION -->
<!-- 37996 Speciality supervisions -->

<table width="100%" border-style="none" class="arial-font-10">
		<logic:notEmpty name="reportInfo" property="supPrgmRefList">
			<tr align="left">
				<td align="center" colspan="5" border = "0"><p class="arial-font-12"><u><b>Speciality Supervisions</b></u></p></td>
			</tr>
			
			<tr>
				<td align="left" width="50%"><p class="arial-font-10"><u><b>Supervision Type</b></u></p></td>
				<td align="left" width="25%"><p class="arial-font-10"><u><b>Start Date</b></u></p></td>
				<td align="center" width="25%"><p class="arial-font-10"><u><b>Expected End Date/End Date</b></u></p></td>
				<td align="left" width="50%"><p class="arial-font-10"><u><b>Outcome</b></u></p></td>
			</tr>
			<c:set var="spPrgmRefList" value="${reportInfo.supPrgmRefList}" scope="session"/>
			<c:forEach var="spPrgmRef" items="${spPrgmRefList}" varStatus="status">
					<tr>
						<td align="left" ><p class="arial-font-10"><c:out value="${spPrgmRef.supervisionCategoryId}"/></p></td>
						<td align="left"><p class="arial-font-10"><c:out value="${spPrgmRef.activationDate}"/></p></td>						
						<td align="left"><p class="arial-font-10"><c:out value="${spPrgmRef.closingDate}"/></p></td>
						<td align="left"><p class="arial-font-10"><c:out value="${spPrgmRef.supervisionOutcome}"/></p></td>
					</tr>	
				
			</c:forEach>
		</logic:notEmpty>
		
	</table>	



<!-- 37996 Speciality supervisions End -->

<!--  TRANSFERRED OFFENSE DETAILS 
	<table width="100%" border-style="none">
		<logic:notEmpty name="reportInfo" property="juvTransferredOffenses">
			<tr align="left">
				<td align="center" colspan="5" border = "0"><p class="arial-font-12"><u><b>TRANSFERRED OFFENSE DETAILS</b></u></p></td>
			</tr>
			<tr>
				<td align="left" width="8%"><p class="arial-font-10"><u><b>Originating County</b></u></p></td>
				<td align="left" width="14%"><p class="arial-font-10"><u><b>Originating Offense</b></u></p></td>				
				<td align="left" width="13%"><p class="arial-font-10"><u><b>Adjudication Date</b></u></p></td>
			</tr>

			<c:set var="juvTransferredOffensesList" value="${reportInfo.juvTransferredOffenses}" scope="session"/>
			<c:forEach var="juvTransferredOffenses" items="${juvTransferredOffensesList}" varStatus="status">
				<tr>
						<td align="left" width="8%"><p class="arial-font-10"><c:out value="${juvTransferredOffenses.countyDesc}"/>
						</p></td>
						<td align="left" width="14%"><p class="arial-font-10"><c:out value="${juvTransferredOffenses.offenseShortDesc}"/>
						</p></td>
						
						<td align="left" width="39%"><p class="arial-font-10"><c:out value="${juvTransferredOffenses.adjudicationDateStr}"/></p></td>
						
				</tr>	
			</c:forEach>
		</logic:notEmpty>
	</table>	
  END TRANSFERRED OFFENSE DETAILS 


-->

<!-- added for Bug:12932 TRANSFERRED OFFENSE DETAILS FOR PREVIOUS REFERRALS -->
<table width="100%" border-style="none" class="arial-font-10">
	<logic:equal name="reportInfo" property="hasPreviousTransferredOffense" value="true">
	<tr>
		<td align="center" colspan="5"><p class="arial-font-10"><u><b>TRANSFERRED OFFENSE DETAILS</b></u></p></td>
	</tr>
	<tr>
		<td align="left"><p class="arial-font-10"><b>Referral Number</b></p></td>
		<td align="left"><p class="arial-font-10"><b>Originating County</b></p></td>
		<td align="left" colspan="2"><p class="arial-font-10"><b>Originating Offense</b></p></td>				
		<td align="left"><p class="arial-font-10"><b>Adjudication Date</b></p></td>						
	</tr>
	<c:set var="previousReferralList" value="${reportInfo.previousReferralDetails}" scope="session"/>
	<c:forEach var="previousReferral" items="${previousReferralList}" varStatus="status">													
		<logic:equal name="previousReferral" property="transferredOffensePresent" value="true">		
				
				<tr>
				<td align="left"><p class="arial-font-10"><c:out value="${previousReferral.referralNumber}"/></p></td>
					<td align="left"><p class="arial-font-10"><c:out value="${previousReferral.transferredOffenseFromCounty}"/></p></td>
					<td align="left" colspan="2"><p class="arial-font-10"><c:out value="${previousReferral.transferredOffenseOriginatingOff}"/></p></td>							
					<td align="left"><p class="arial-font-10"><c:out value="${previousReferral.transferredOffenseAdjuDate}"/></p></td>									
				</tr>						
				<tr><td align="left" colspan="4" border-top = "0" padding-bottom="5"></td></tr>
				<tr><td align="left" colspan="4" border-top = "0" padding-bottom="5"></td></tr>
		</logic:equal>
	</c:forEach>
	</logic:equal>
</table>
<!-- ended -->	



<!--  JUSTICE OF THE PEACE COURT REFERRAL CONVICTIONS -->
	<table width="100%" border-style="none" class="arial-font-10">
		<logic:notEmpty name="reportInfo" property="JPCourtReferralConvictions">
			<tr align="left">
				<td align="left" colspan="5" border = "0"><p class="arial-font-12"><b>Justice of the Peace Court Referrals:</b></p></td>
			</tr>
			<tr>
				<td align="left" width="8%"><p class="arial-font-10"></p></td>
				<td align="left" width="14%"><p class="arial-font-10"><b>Case</b></p></td>
				<td align="left" width="44%"><p class="arial-font-10"><b>Offense</b></p></td>
				<td align="left" width="18%"><p class="arial-font-10"><b>File</b></p></td>
				<td align="left" width="16%"><p class="arial-font-10"><b>Date of</b></p></td>
			</tr>
			<tr>
				<td align="left" width="8%"><p class="arial-font-10"><u><b>Court</b></u></p></td>
				<td align="left" width="14%"><p class="arial-font-10"><u><b>Number</b></u></p></td>
				<td align="left" width="44%"><p class="arial-font-10"><u><b>Description</b></u></p></td>
				<td align="left" width="18%"><p class="arial-font-10"><u><b>Date</b></u></p></td>
				<td align="left" width="16%"><p class="arial-font-10"><u><b>Conviction</b></u></p></td>
			</tr>
			<c:set var="JPCourtReferralConvictionsList" value="${reportInfo.JPCourtReferralConvictions}" scope="session"/>
			<c:forEach var="JPCourtReferralConviction" items="${JPCourtReferralConvictionsList}" varStatus="status">
				<tr>
					<td align="left" width="8%"><p class="arial-font-10"><c:out value="${JPCourtReferralConviction.courtName}"/>
					</p></td>
					<td align="left" width="14%"><p class="arial-font-10"><c:out value="${JPCourtReferralConviction.caseNumber}"/>
					</p></td>
					
					<td align="left" width="44%"><p class="arial-font-10"><c:out value="${JPCourtReferralConviction.offenseDescription}"/></p></td>
					<td align="left" width="18%"><p class="arial-font-10">
						<fmt:formatDate value="${JPCourtReferralConviction.fileDate}" pattern="MMM d, yyyy" var="formattedFileDate" />
							<c:out value="${formattedFileDate}"/></p>
					</td>
					<td align="left" width="16%"><p class="arial-font-10">
						<fmt:formatDate value="${JPCourtReferralConviction.convictionDate}" pattern="MMM d, yyyy" var="formattedConvictionDate" />
							<c:out value="${formattedConvictionDate}"/></p>
					</td>
				</tr>			
			</c:forEach>
		</logic:notEmpty>
	</table>	
<!--  END JUSTICE OF THE PEACE COURT REFERRAL CONVICTIONS -->
</body>
</pdf>