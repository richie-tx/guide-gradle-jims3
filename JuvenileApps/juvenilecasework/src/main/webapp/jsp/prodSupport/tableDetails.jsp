<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.Features" %>
<html>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
	
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/prodSupport/deleteReferral.js"></script>
</head>
<body>
<h1 align="center">
Table Detail
</h1>
<hr>
<html:form action="/PerformDeleteAssociatedMsReferral">
<logic:notEmpty name="ProdSupportForm" property="referralOffenses">
	<logic:equal name="ProdSupportForm" property="tableId" value="referralOffense">
		<div align="center">
			<div>
				<font size="+1"><b>JJS_MS_OFFENSE</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JJS_MS_OFFENSE_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILENUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">OFFENSECODE</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">RECTYPE</font></td>
					</tr>
					<logic:iterate id="referralOffense" name="ProdSupportForm" property="referralOffenses">
						<tr>
							<td><bean:write name="referralOffense" property="OID"/></td>
							<td><bean:write name="referralOffense" property="juvenileNum"/></td>
							<td><bean:write name="referralOffense" property="referralNum"/></td>
							<td><bean:write name="referralOffense" property="offenseCodeId"/></td>
							<td><bean:write name="referralOffense" property="recType"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="assignments">
	<logic:equal name="ProdSupportForm" property="tableId" value="assignment">
		<div align="center">
			<div>
				<font size="+1"><b>JCASSIGNMENT</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSIGNMENT_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSMNTADDDATE</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
					</tr>
					<logic:iterate id="assignment" name="ProdSupportForm" property="assignments">
						<tr>
							<td><bean:write name="assignment" property="OID"/></td>
							<td><bean:write name="assignment" property="referralNumber"/></td>
							<td><bean:write name="assignment" property="assignmentAddDate" format="MM/dd/yyyy"/></td>
							<td><bean:write name="assignment" property="caseFileId"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="intakeHistoryRecords">
	<logic:equal name="ProdSupportForm" property="tableId" value="intakeHistory">
		<div align="center">
			<div>
				<font size="+1"><b>JJS_SV_INTAKE_HISTORY</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JJS_SV_INTAKE_HISTORY_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILENUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSMNTTYPE</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">INTAKEDECISION</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">SPRVSIONTYPECD</font></td>
					</tr>
					<logic:iterate id="intakeHistoryRecord" name="ProdSupportForm" property="intakeHistoryRecords">
						<tr>
							<td><bean:write name="intakeHistoryRecord" property="OID"/></td>
							<td><bean:write name="intakeHistoryRecord" property="juvenileNum"/></td>
							<td><bean:write name="intakeHistoryRecord" property="referralNumber"/></td>
							<td><bean:write name="intakeHistoryRecord" property="assignmentType"/></td>
							<td><bean:write name="intakeHistoryRecord" property="intakeDecisionId"/></td>
							<td><bean:write name="intakeHistoryRecord" property="supervisionTypeId"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="transOffenseReferrals">
	<logic:equal name="ProdSupportForm" property="tableId" value="transOffenseReferral">
		<div align="center">
			<div>
				<font size="+1"><b>JCTRANSOFFNSREF</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">TRANSOFFNSREF_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILENUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">OFFENSECD</font></td>
					</tr>
					<logic:iterate id="transOffenseReferral" name="ProdSupportForm" property="transOffenseReferrals">
						<tr>
							<td><bean:write name="transOffenseReferral" property="transOffenseReferralId"/></td>
							<td><bean:write name="transOffenseReferral" property="juvenileNum"/></td>
							<td><bean:write name="transOffenseReferral" property="referralNum"/></td>
							<td><bean:write name="transOffenseReferral" property="offenseCode"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="facilityHeaderList">
	<logic:equal name="ProdSupportForm" property="tableId" value="facilityHeader">
		<div align="center">
			<div>
				<font size="+1"><b>JJS_HEADER</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JJS_HEADER_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILENUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">BOOKINGSUPERVISIONNUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">DETENTIONSTATUS</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">HEADERFACILITY</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">LASTSEQ</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">NEXTHEARINGDATE</font></td>
					</tr>
					<logic:iterate id="facilityHeader" name="ProdSupportForm" property="facilityHeaderList">
						<tr>
							<td><bean:write name="facilityHeader" property="headerId"/></td>
							<td><bean:write name="facilityHeader" property="juvenileNumber"/></td>
							<td><bean:write name="facilityHeader" property="referralNo"/></td>
							<td><bean:write name="facilityHeader" property="bookingSupervision"/></td>
							<td><bean:write name="facilityHeader" property="facilityStatus"/></td>
							<td><bean:write name="facilityHeader" property="facilityCode"/></td>
							<td><bean:write name="facilityHeader" property="lastSeqNum"/></td>
							<td><bean:write name="facilityHeader" property="nextHearingDate" format="MM/dd/yyyy"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="facilityDetentionList">
	<logic:equal name="ProdSupportForm" property="tableId" value="facilityDetention">
		<div align="center">
			<div>
				<font size="+1"><b>JJS_DETENTION</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JJS_DETENTION_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILENUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">ADMITTEDDATE</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">BOOKINGSUPERVISIONNUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CURRENTREFERRAL</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CURRENTSUPERVISIONNUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">DETENTIONSTATUS</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">FACILITY</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">REASONCODE</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">RELEASEDATE</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">SEQNUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">RISKANALYSIS_ID</font></td>
						
					</tr>
					<logic:iterate id="facilityDetention" name="ProdSupportForm" property="facilityDetentionList">
						<tr>
							<td><bean:write name="facilityDetention" property="detentionId"/></td>
							<td><bean:write name="facilityDetention" property="juvNum"/></td>
							<td><bean:write name="facilityDetention" property="referralNumber"/></td>
							<td><bean:write name="facilityDetention" property="admitDate" format="MM/dd/yyyy"/></td>
							<td><bean:write name="facilityDetention" property="bookingSupervisionNum"/></td>
							<td><bean:write name="facilityDetention" property="currentReferral"/></td>
							<td><bean:write name="facilityDetention" property="currentSupervisionNum"/></td>
							<td><bean:write name="facilityDetention" property="detentionStatus"/></td>
							<td><bean:write name="facilityDetention" property="facilityCode"/></td>
							<td><bean:write name="facilityDetention" property="admitReason"/></td>
							<td><bean:write name="facilityDetention" property="releaseDate" format="MM/dd/yyyy"/></td>
							<td><bean:write name="facilityDetention" property="facilitySequenceNumber"/></td>
							<td><bean:write name="facilityDetention" property="riskAnalysisId"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="juvDetCourtRecords">
	<logic:equal name="ProdSupportForm" property="tableId" value="juvDetCourtRecord">
		<div align="center">
			<div>
				<font size="+1"><b>JCJJSCLDETENTION</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JJSCLDETENTION_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILENUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CHAINNUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">COURTDATE</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">HEARINGRESULT</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">HEARINGTYPE</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">SEQNUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JJS_DETENTION_ID</font></td>
					</tr>
					<logic:iterate id="juvDetCourtRecord" name="ProdSupportForm" property="juvDetCourtRecords">
						<tr>
							<td><bean:write name="juvDetCourtRecord" property="docketEventId"/></td>
							<td><bean:write name="juvDetCourtRecord" property="juvenileNumber"/></td>
							<td><bean:write name="juvDetCourtRecord" property="referralNum"/></td>
							<td><bean:write name="juvDetCourtRecord" property="chainNumber"/></td>
							<td><bean:write name="juvDetCourtRecord" property="courtDate"/></td>
							<td><bean:write name="juvDetCourtRecord" property="courtResult"/></td>
							<td><bean:write name="juvDetCourtRecord" property="hearingType"/></td>
							<td><bean:write name="juvDetCourtRecord" property="seqNum"/></td>
							<td><bean:write name="juvDetCourtRecord" property="detentionId"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="juvDistCourtRecords">
	<logic:equal name="ProdSupportForm" property="tableId" value="juvDistCourtRecord">
		<div align="center">
			<div>
				<font size="+1"><b>JCJJSCLCOURT</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JJSCLCOURT_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILENUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CHAINNUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">COURTDATE</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">HEARINGDISPOSITION</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">HEARINGRESULT</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">PETITIONALLEGATION</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">PETITIONNUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">SEQNUM</font></td>
					</tr>
					<logic:iterate id="juvDistCourtRecord" name="ProdSupportForm" property="juvDistCourtRecords">
						<tr>
							<td><bean:write name="juvDistCourtRecord" property="docketEventId"/></td>
							<td><bean:write name="juvDistCourtRecord" property="juvenileNumber"/></td>
							<td><bean:write name="juvDistCourtRecord" property="referralNum"/></td>
							<td><bean:write name="juvDistCourtRecord" property="chainNumber"/></td>
							<td><bean:write name="juvDistCourtRecord" property="courtDate" format="MM/dd/yyyy"/></td>
							<td><bean:write name="juvDistCourtRecord" property="disposition"/></td>
							<td><bean:write name="juvDistCourtRecord" property="courtResult"/></td>
							<td><bean:write name="juvDistCourtRecord" property="allegation"/></td>
							<td><bean:write name="juvDistCourtRecord" property="petitionNumber"/></td>
							<td><bean:write name="juvDistCourtRecord" property="seqNum"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="petitionDetails">
	<logic:equal name="ProdSupportForm" property="tableId" value="petition">
		<div align="center">
			<div>
				<font size="+1"><b>JCJJSMSPETITION</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JJSMSPETITION_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILENUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">PETITIONALLEGATION</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">PETITIONNUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">PETITIONSTATUS</font></td>
					</tr>
					<logic:iterate id="petitionDetail" name="ProdSupportForm" property="petitionDetails">
						<tr>
							<td><bean:write name="petitionDetail" property="OID"/></td>
							<td><bean:write name="petitionDetail" property="juvenileNum"/></td>
							<td><bean:write name="petitionDetail" property="referralNum"/></td>
							<td><bean:write name="petitionDetail" property="offenseCodeId"/></td>
							<td><bean:write name="petitionDetail" property="petitionNum"/></td>
							<td><bean:write name="petitionDetail" property="petitionStatus"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="casefiles">
	<logic:equal name="ProdSupportForm" property="tableId" value="casefile">
		<div align="center">
			<div>
				<font size="+1"><b>JCCASEFILE</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CNTROLLINGREFERRAL</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CASESTATUSCD</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">SPRVSIONTYPECD</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
					</tr>
					<logic:iterate id="casefile" name="ProdSupportForm" property="filteredCasefiles">
						<tr>
							<td><bean:write name="casefile" property="supervisionNum"/></td>
							<td><bean:write name="casefile" property="juvenileNum"/></td>
							<td><bean:write name="casefile" property="controllingReferralId"/></td>
							<td><bean:write name="casefile" property="caseStatusId"/></td>
							<td><bean:write name="casefile" property="supervisionTypeId"/></td>
							<td><bean:write name="casefile" property="createDate" format="MM/dd/yyyy"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="filteredCasefileClosings">
	<logic:equal name="ProdSupportForm" property="tableId" value="casefileClosing">
		<div align="center">
			<div>
				<font size="+1"><b>JCCASFILECLOSNG</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILECLOSNG_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CNTROLLINGREFERRAL</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILECLOSNGSTATS</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">SUPERVISIONENDDATE</font></td>
					</tr>
					<logic:iterate id="casefileclosing" name="ProdSupportForm" property="filteredCasefileClosings">
						<tr>
							<td><bean:write name="casefileclosing" property="OID"/></td>
							<td><bean:write name="casefileclosing" property="controllingReferralId"/></td>
							<td><bean:write name="casefileclosing" property="casefileClosingStatus"/></td>
							<td><bean:write name="casefileclosing" property="supervisionNumber"/></td>
							<td><bean:write name="casefileclosing" property="supervisionEndDate" format="MM/dd/yyyy"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="filteredProgramReferrals">
	<logic:equal name="ProdSupportForm" property="tableId" value="juvprogref">
		<div align="center">
			<div>
				<font size="+1"><b>CSJUVPROGREF</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVPROGREF_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CNTROLREFERL</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">BEGINDATE</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">ENDDATE</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">STATUSCD</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
					</tr>
					<logic:iterate id="programReferral" name="ProdSupportForm" property="filteredProgramReferrals">
						<tr>
							<td><bean:write name="programReferral" property="OID"/></td>
							<td><bean:write name="programReferral" property="controllingReferralNum"/></td>
							<td><bean:write name="programReferral" property="beginDate" format="MM/dd/yyyy"/></td>
							<td><bean:write name="programReferral" property="endDate" format="MM/dd/yyyy"/></td>
							<td><bean:write name="programReferral" property="referralStatusCd"/></td>
							<td><bean:write name="programReferral" property="casefileId"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="riskNeedLevels">
	<logic:equal name="ProdSupportForm" property="tableId" value="riskanalysis">
		<div align="center">
			<div>
				<font size="+1"><b>JCRISKNEEDSLVL</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">RISKNEEDSLVL_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUMBER</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CASEFILE_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">PACTDATE</font></td>
					</tr>
					<logic:iterate id="riskNeedLevel" name="ProdSupportForm" property="riskNeedLevels">
						<tr>
							<td><bean:write name="riskNeedLevel" property="riskNeedLvlId"/></td>
							<td><bean:write name="riskNeedLevel" property="juvenileNumber"/></td>
							<td><bean:write name="riskNeedLevel" property="referralNumber"/></td>
							<td><bean:write name="riskNeedLevel" property="caseFileId"/></td>
							<td><bean:write name="riskNeedLevel" property="lastPactDate" format="MM/dd/yyyy"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="riskresponses">
	<logic:equal name="ProdSupportForm" property="tableId" value="riskresponse">
		<div align="center">
			<div>
				<font size="+1"><b>JCRISKRESPONSES</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">RISKRESPONSES_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">RESPONSETEXT</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">RISKANALYSIS_ID</font></td>
					</tr>
					<logic:iterate id="riskresponse" name="ProdSupportForm" property="riskresponses">
						<tr>
							<td><bean:write name="riskresponse" property="riskResponseId"/></td>
							<td><bean:write name="riskresponse" property="text"/></td>
							<td><bean:write name="riskresponse" property="riskAnalysisId"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="maysidetails">
	<logic:equal name="ProdSupportForm" property="tableId" value="maysiDetail">
		<div align="center">
			<div>
				<font size="+1"><b>JCMAYSIDETAIL</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">MAYSIDETAIL_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUMBER</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">SCREENINGDATE</font></td>
					</tr>
					<logic:iterate id="maysidetail" name="ProdSupportForm" property="maysidetails">
						<tr>
							<td><bean:write name="maysidetail" property="OID"/></td>
							<td><bean:write name="maysidetail" property="juvenileNumber"/></td>
							<td><bean:write name="maysidetail" property="referralNum"/></td>
							<td><bean:write name="maysidetail" property="screenDate" format="MM/dd/yyyy"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="maysis">
	<logic:equal name="ProdSupportForm" property="tableId" value="maysiAssessment">
		<div align="center">
			<div>
				<font size="+1"><b>JCMAYSIASSESSMNT</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">MAYSIASSESSMNT_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUMBER</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSESSDATE</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSESSOPTION</font></td>
					</tr>
					<logic:iterate id="maysi" name="ProdSupportForm" property="maysis">
						<tr>
							<td><bean:write name="maysi" property="OID"/></td>
							<td><bean:write name="maysi" property="juvenileNumber"/></td>
							<td><bean:write name="maysi" property="referralNumber"/></td>
							<td><bean:write name="maysi" property="requestDate" format="MM/dd/yyyy"/></td>
							<td><bean:write name="maysi" property="assessmentOptionId"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="juvenileWarrants">
	<logic:equal name="ProdSupportForm" property="tableId" value="juvenileWarrant">
		<div align="center">
			<div>
				<font size="+1"><b>JWWARRANT</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">WARRANT_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVNUMBER</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUMBER</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">WACTIVATIONSTAT</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">WARRANTSTATUSCD</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
					</tr>
					<logic:iterate id="juvenileWarrant" name="ProdSupportForm" property="juvenileWarrants">
						<tr>
							<td><bean:write name="juvenileWarrant" property="warrantNum"/></td>
							<td><bean:write name="juvenileWarrant" property="juvenileNum"/></td>
							<td><bean:write name="juvenileWarrant" property="referralNum"/></td>
							<td><bean:write name="juvenileWarrant" property="warrantActivationStatusId"/></td>
							<td><bean:write name="juvenileWarrant" property="warrantStatusId"/></td>
							<td><bean:write name="juvenileWarrant" property="createDate" format="MM/dd/yyyy"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="juvenileWarrantCharges">
	<logic:equal name="ProdSupportForm" property="tableId" value="juvenileWarrantCharge">
		<div align="center">
			<div>
				<font size="+1"><b>JWWARRANTCHARGES</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">WARRANT_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVNUMBER</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUMBER</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">WACTIVATIONSTAT</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">WARRANTSTATUSCD</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CREATEDATE</font></td>
					</tr>
					<logic:iterate id="juvenileWarrantCharge" name="ProdSupportForm" property="juvenileWarrantCharges">
						<tr>
							<td><bean:write name="juvenileWarrantCharge" property="warrantNum"/></td>
							<td><bean:write name="juvenileWarrantCharge" property="juvenileNum"/></td>
							<td><bean:write name="juvenileWarrantCharge" property="referralNum"/></td>
							<td><bean:write name="juvenileWarrantCharge" property="warrantActivationStatusId"/></td>
							<td><bean:write name="juvenileWarrantCharge" property="warrantStatusId"/></td>
							<td><bean:write name="juvenileWarrantCharge" property="createDate" format="MM/dd/yyyy"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="juvenileInactivatedWarrants">
	<logic:equal name="ProdSupportForm" property="tableId" value="juvenileInactivatedWarrant">
		<div align="center">
			<div>
				<font size="+1"><b>JWWARRANTFORINACT</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">WARRANT_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVNUMBER</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUMBER</font></td>
					</tr>
					<logic:iterate id="juvenileInactivatedWarrant" name="ProdSupportForm" property="juvenileInactivatedWarrants">
						<tr>
							<td><bean:write name="juvenileInactivatedWarrant" property="warrantNum"/></td>
							<td><bean:write name="juvenileInactivatedWarrant" property="juvenileNum"/></td>
							<td><bean:write name="juvenileInactivatedWarrant" property="referralNum"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="juvenileWarrantRecalls">
	<logic:equal name="ProdSupportForm" property="tableId" value="juvenileWarrantRecall">
		<div align="center">
			<div>
				<font size="+1"><b>JWWARRANTRECALL</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">WARRANT_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVNUMBER</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUMBER</font></td>
					</tr>
					<logic:iterate id="juvenileWarrantRecall" name="ProdSupportForm" property="juvenileWarrantRecalls">
						<tr>
							<td><bean:write name="juvenileWarrantRecall" property="warrantNum"/></td>
							<td><bean:write name="juvenileWarrantRecall" property="juvenileNum"/></td>
							<td><bean:write name="juvenileWarrantRecall" property="referralNum"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="juvenileWarrantAssociates">
	<logic:equal name="ProdSupportForm" property="tableId" value="juvenileWarrantAssociate">
		<div align="center">
			<div>
				<font size="+1"><b>JWWARRANTASSOCIATE</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSOCIATE_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILE_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUMBER</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">WARRANT_ID</font></td>
					</tr>
					<logic:iterate id="juvenileWarrantAssociate" name="ProdSupportForm" property="juvenileWarrantAssociates">
						<tr>
							<td><bean:write name="juvenileWarrantAssociate" property="OID"/></td>
							<td><bean:write name="juvenileWarrantAssociate" property="juvenileNum"/></td>
							<td><bean:write name="juvenileWarrantAssociate" property="referralNum"/></td>
							<td><bean:write name="juvenileWarrantAssociate" property="associateWarrantNum"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="juvenileWarrantServes">
	<logic:equal name="ProdSupportForm" property="tableId" value="juvenileWarrantServe">
		<div align="center">
			<div>
				<font size="+1"><b>JWWARRANTSERVE</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">WARRANTSERVE_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">WARRANT_ID</font></td>
					</tr>
					<logic:iterate id="juvenileWarrantServe" name="ProdSupportForm" property="juvenileWarrantServes">
						<tr>
							<td><bean:write name="juvenileWarrantServe" property="OID"/></td>
							<td><bean:write name="juvenileWarrantServe" property="associateWarrantNum"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="juvenileWarrantFields">
	<logic:equal name="ProdSupportForm" property="tableId" value="juvenileWarrantField">
		<div align="center">
			<div>
				<font size="+1"><b>JWWARRANTFIELDS</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">WARRANT_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVNUMBER</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUMBER</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">WACTIVATIONSTAT</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">WARRANTSTATUSCD</font></td>
					</tr>
					<logic:iterate id="juvenileWarrantField" name="ProdSupportForm" property="juvenileWarrantFields">
						<tr>
							<tr>
							<td><bean:write name="juvenileWarrantField" property="warrantNum"/></td>
							<td><bean:write name="juvenileWarrantField" property="juvenileNum"/></td>
							<td><bean:write name="juvenileWarrantField" property="referralNum"/></td>
							<td><bean:write name="juvenileWarrantField" property="warrantActivationStatusId"/></td>
							<td><bean:write name="juvenileWarrantField" property="warrantStatusId"/></td>
						</tr>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="juvenileWarrantServiceOfficers">
	<logic:equal name="ProdSupportForm" property="tableId" value="juvenileWarrantServiceOfficer">
		<div align="center">
			<div>
				<font size="+1"><b>JWWARRANTSERVOFF</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">WARRANT_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">PETITIONNUMBER</font></td>
					</tr>
					<logic:iterate id="juvenileWarrantServiceOfficer" name="ProdSupportForm" property="juvenileWarrantServiceOfficers">
						<tr>
							<td><bean:write name="juvenileWarrantServiceOfficer" property="warrantNum"/></td>
							<td><bean:write name="juvenileWarrantServiceOfficer" property="petitionNum"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="associatedCautions">
	<logic:equal name="ProdSupportForm" property="tableId" value="associatedCaution">
		<div align="center">
			<div>
				<font size="+1"><b>JWCAUTIONS</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CAUTIONS_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">WARRANT_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CAUTIONCD</font></td>
					</tr>
					<logic:iterate id="associatedCaution" name="ProdSupportForm" property="associatedCautions">
						<tr>
							<td><bean:write name="associatedCaution" property="OID"/></td>
							<td><bean:write name="associatedCaution" property="parentId"/></td>
							<td><bean:write name="associatedCaution" property="childId"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="associatedCharges">
	<logic:equal name="ProdSupportForm" property="tableId" value="associatedCharge">
		<div align="center">
			<div>
				<font size="+1"><b>JWCHARGE</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CHARGE_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">WARRANT_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">PETITIONNUMBER</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">COURT_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CHARGECODECD</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">SEQUENCENUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">CHARGEDESCRIPTION</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">OFFENSEDATE</font></td>
					</tr>
					<logic:iterate id="associatedCharge" name="ProdSupportForm" property="associatedCharges">
						<tr>
							<td><bean:write name="associatedCharge" property="OID"/></td>
							<td><bean:write name="associatedCharge" property="warrantNum"/></td>
							<td><bean:write name="associatedCharge" property="petitionNum"/></td>
							<td><bean:write name="associatedCharge" property="courtId"/></td>
							<td><bean:write name="associatedCharge" property="offenseCodeId"/></td>
							<td><bean:write name="associatedCharge" property="sequenceNum"/></td>
							<td><bean:write name="associatedCharge" property="chargeDescription"/></td>
							<td><bean:write name="associatedCharge" property="offenseDate"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="associatedScarMarks">
	<logic:equal name="ProdSupportForm" property="tableId" value="associatedScarMark">
		<div align="center">
			<div>
				<font size="+1"><b>JWSCARSMARKS</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">SCARSMARKS_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">WARRANT_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">SCARSMARKSCD</font></td>
					</tr>
					<logic:iterate id="associatedScarMark" name="ProdSupportForm" property="associatedScarMarks">
						<tr>
							<td><bean:write name="associatedScarMark" property="OID"/></td>
							<td><bean:write name="associatedScarMark" property="parentId"/></td>
							<td><bean:write name="associatedScarMark" property="childId"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="associatedTattoos">
	<logic:equal name="ProdSupportForm" property="tableId" value="associatedTattoo">
		<div align="center">
			<div>
				<font size="+1"><b>JWTATTOOS</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">TATTOOS_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">WARRANT_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">TATTOOSCD</font></td>
					</tr>
					<logic:iterate id="associatedTattoo" name="ProdSupportForm" property="associatedTattoos">
						<tr>
							<td><bean:write name="associatedTattoo" property="OID"/></td>
							<td><bean:write name="associatedTattoo" property="parentId"/></td>
							<td><bean:write name="associatedTattoo" property="childId"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="associatedAddresses">
	<logic:equal name="ProdSupportForm" property="tableId" value="associatedAddress">
		<div align="center">
			<div>
				<font size="+1"><b>JWASSOCIATEADDR</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSOCIATEADDR_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">ASSOCIATE_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">ADDRESS_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">ADDRESSSTATUS</font></td>
					</tr>
					<logic:iterate id="associatedAddress" name="ProdSupportForm" property="associatedAddresses">
						<tr>
							<td><bean:write name="associatedAddress" property="OID"/></td>
							<td><bean:write name="associatedAddress" property="associateNum"/></td>
							<td><bean:write name="associatedAddress" property="addressId"/></td>
							<td><bean:write name="associatedAddress" property="addressStatus"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="corespondents">
	<logic:equal name="ProdSupportForm" property="tableId" value="associatedCorespondent">
		<div align="center">
			<div>
				<font size="+1"><b>JJS_MS_CORESPONDENT</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JJS_MS_CORESPONDENT_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">JUVENILENUM</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">REFERRALNUM</font></td>
					</tr>
					<logic:iterate id="corespondent" name="ProdSupportForm" property="corespondents">
						<tr>
							<td><bean:write name="corespondent" property="OID"/></td>
							<td><bean:write name="corespondent" property="juvenileNum"/></td>
							<td><bean:write name="corespondent" property="referralNum"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<logic:notEmpty name="ProdSupportForm" property="jcVOPs">
	<logic:equal name="ProdSupportForm" property="tableId" value="associatedVOPs">
		<div align="center">
			<div>
				<font size="+1"><b>JCVOP</b></font>
			</div>
			<br>
			<div>
				<table class="resultTbl" border="1" width="550" align="center">
					<tr>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">VOP_ID</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">OffenseCharge</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">OffenseChargeDate</font></td>
						<td bgcolor="gray"><font color="white" face="bold" size="-1">InCountyOrgPetitionedRefNum</font></td>
					</tr>
					<logic:iterate id="vop" name="ProdSupportForm" property="jcVOPs">
						<tr align="center">
							<td><bean:write name="vop" property="OID"/></td>
							<td><bean:write name="vop" property="offenseCharge"/></td>
							<td><bean:write name="vop" property="offenseChargeDate" formatKey="date.format.mmddyyyy"/></td>
							<td><bean:write name="vop" property="inCCountyOrigPetitionedRefNum"/></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</logic:equal>
</logic:notEmpty>

<br>
	<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit>
</html:form>
</body>
</html>