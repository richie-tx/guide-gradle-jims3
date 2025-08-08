<!DOCTYPE HTML>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionMessages" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>



<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="x-UA-Compatible" content="IE=edge, chrome=1"/>

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/districtCourtHearings/initialSetting.js"></script>

<title><bean:message key="title.heading" />/initialSetting.jsp</title>

</head>
<%--END HEAD TAG--%>
<%--BEGIN BODY TAG--%>
<body>
	<html:form action="/submitJuvenileDistrictCourtInitialSetting" target="content" styleId="initialSettingForm">
		<!-- HELP FILE -->
		<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|264">

		<table width='100%'>
			<tr>
				<td align="center"><h2>Process Juvenile Court Hearings<br/><br/>Initial Setting</h2></td>
			</tr>
		</table>
		 <!-- BEGIN Error Message Table -->
		 <logic:messagesPresent message="true"> 
			<table width="100%">
				<tr>		  
					<td align="center" class="messageAlert"><html:messages id="message" message="true"><bean:write name="message"/></html:messages></td>		  
				</tr>   	  
			</table>
		</logic:messagesPresent> 
		
		<!-- BEGIN ERROR TABLE -->
		<table width="100%">
			<tr>
				<td align="center" class="errorAlert"><html:errors></html:errors></td>
			</tr>
		</table>
	<!-- END ERROR TABLE -->
  
  <!-- END Error Message Table -->
		<br />
		<table width="98%" border="0">
			<tr>
				<td>
					<ul>
						<li>Petition record must exist before an Initial Setting can be created.</li>
						<li>Subpoenas can be re-generated for an existing Initial Setting record.</li>
						<li>Subpoenas will be generated in .pdf format.  Physical print must be initiated from pdf pop-up.</li>
					</ul>
				</td>
			</tr>
		</table>
		<%-- END INSTRUCTION TABLE --%>
	<!-- Juvenile Information  starts-->
		<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue" colspan="5">
			<tr>
				<td>
					<table width='100%' cellpadding="2" cellspacing="1">
						<tr>
							<td class='formDeLabel' width="5%" nowrap="nowrap"><bean:message key="prompt.juvNo"/></td>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.refNo"/></td>
							<td class='formDeLabel'  width="15%"><bean:message key="prompt.name"/></td>
							<td class='formDeLabel' width="5%" nowrap="nowrap"><bean:message key="prompt.DOB"/></td>
							<td class='formDeLabel'  width="1%" nowrap="nowrap"><bean:message key="prompt.verify"/></td>
							<td class='formDeLabel'  width="25%" nowrap="nowrap"><bean:message key="prompt.address"/></td>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.status"/></td>
						</tr>
						<tr>
							<td class='formDe' ><bean:write name="courtHearingForm" property="profileDetail.juvenileNum"/></td>
							<td class='formDe' ><bean:write name="courtHearingForm" property="referralNumber"/></td>
							<td class='formDe' nowrap="nowrap" ><bean:write name="courtHearingForm" property="juvenileName"/></td>
							<td class='formDe' ><bean:write name="courtHearingForm" property="profileDetail.dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
							<td class='formDe'><logic:equal name="courtHearingForm" property="profileDetail.verifiedDOB" value="true"><span title="Yes">Y</span></logic:equal><logic:equal name="courtHearingForm" property="profileDetail.verifiedDOB" value="false"><span title="No">N</span></logic:equal></td>
							<td class='formDe'   nowrap="nowrap" >
							<logic:notEmpty name="courtHearingForm" property="memberAddress">
								<bean:write name="courtHearingForm" property="memberAddress.streetAddress"/><br>
								<logic:notEqual name="courtHearingForm" property="memberAddress.cityStateZip" value="">
		  							<bean:write name="courtHearingForm" property="memberAddress.cityStateZip"/>
		  						</logic:notEqual>
		  					</logic:notEmpty>
		  					</td>
							<td class='formDe'>
								<bean:message key="prompt.juv"/>&nbsp;/<logic:notEmpty  name="courtHearingForm" property="facilityStatus">&nbsp;<span title="<bean:write name="courtHearingForm" property="detainedFacilityDesc"/>">
								<bean:write name="courtHearingForm" property="detainedFacility"/></span></logic:notEmpty><br/>
								<span title="<bean:write name="courtHearingForm" property="profileDetail.status"/>">&nbsp;&nbsp;<bean:write name="courtHearingForm" property="profileDetail.statusId"/></span>
								<span title="<bean:write name="courtHearingForm" property="facilityStatusDesc"/>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="courtHearingForm" property="facilityStatus"/></span>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- Juvenile Information ends-->
		<br/>
		<!--Offense detail starts -->
			<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue" colspan="5">
			<tr class="crtDetailHead">
				<td colspan="10">Referral Information</td> 
			</tr>
			<tr>
				<td>
					<table width='100%' cellpadding="2" cellspacing="1">
						<tr>
							<td class='formDeLabel' nowrap="nowrap" colspan="1" width="10%">DALOG#:</td>
							<td class='formDe'  nowrap="nowrap" colspan="1" width="10%"><bean:write name="courtHearingForm" property="daLogNum"/></td>
							<td class='formDeLabel' nowrap="nowrap" colspan="1" width="10%">OFFENSE DATE:</td>
							<td class='formDe'  nowrap="nowrap"colspan="1"  width="10%"><bean:write name="courtHearingForm" property="offenseDetail.offenseDate" formatKey="date.format.mmddyyyy"/></td>
							<td class='formDeLabel' nowrap="nowrap" colspan="1" width="10%">OFFENSE CODE:</td>
							<td class='formDe'  nowrap="nowrap"colspan="1"  width="10%">
								<logic:iterate id="offenseDetetail" name="courtHearingForm" property="offenseDetails" indexId="index">
									<% if ( index.intValue() > 0 ) { %>
										,
									<%}; %>
									<span title="<bean:write name="offenseDetetail" property="offenseDescription"/>">
										<bean:write name="offenseDetetail" property="offenseCodeId"/>
									</span>
								</logic:iterate>
							</td>
							<td class='formDeLabel' nowrap="nowrap" colspan="1" width="10%">NCIC CODE:</td>
							<td class='formDe'  nowrap="nowrap" colspan="1" width="10%">
							<logic:iterate id="offenseDetetail" name="courtHearingForm" property="offenseDetails" indexId="index">
									<% if ( index.intValue() > 0 ) { %>
										,
									<%}; %>
									<bean:write name="offenseDetetail" property="ncicCode"/>
							</logic:iterate>
							</td>
							<td class='formDeLabel' nowrap="nowrap" colspan="1" width="10%">OFFENSE SEVERITY:</td>
							<logic:empty name="courtHearingForm" property="offenseCategories">
								<td class='formDe'  nowrap="nowrap"colspan="1"  width="10%"><bean:write name="courtHearingForm" property="offenseDetail.catagory"/></td>
							</logic:empty>
							<logic:notEmpty name="courtHearingForm" property="offenseCategories">
								<td class='formDe'  nowrap="nowrap"colspan="1"  width="10%"><bean:write name="courtHearingForm" property="offenseCategories"/></td>
							</logic:notEmpty>
						</tr>
						<tr>
							<td class='formDeLabel' nowrap="nowrap" colspan="1" width="10%">DA STATUS:</td>
							<td class='formDe'  nowrap="nowrap" colspan="1" width="10%"><bean:write name="courtHearingForm" property="logStatus"/></td>
							<td class='formDeLabel' nowrap="nowrap" colspan="1" width="10%">DA DATE OUT:</td>
							<td class='formDe'  nowrap="nowrap" colspan="1" width="10%"><bean:write name="courtHearingForm" property="daDateOut" formatKey="date.format.mmddyyyy"/></td>
							<td class='formDeLabel' nowrap="nowrap" colspan="1" width="10%">PETITION NUM:</td>
							<td class='formDe'  nowrap="nowrap" colspan="1" width="10%"><bean:write name="courtHearingForm" property="jotPetitionnum"/></td>
							<td class='formDeLabel' nowrap="nowrap" colspan="1" width="10%">CJIS NUMBER:</td>
							<td class='formDe'  nowrap="nowrap"colspan="1"  width="10%"><bean:write name="courtHearingForm" property="offenseDetail.cjisNum"/></td>
						</tr>
					</table>
				</td>
			</tr>
			</table>
		<br/>
		<!-- PETITION Information  starts-->
		<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue" colspan="5" id="petitionTbl">
			<tr>
				<td>
					<table width='100%' cellpadding="2" cellspacing="1">
						<tr class="crtDetailHead">
							<td colspan="8"><bean:message key="prompt.petitionInfo"/></td> 
						</tr>
						<tr>
							<td class='formDeLabel' colspan="1"  width="5%" nowrap="nowrap" ><bean:message key="prompt.2.diamond"/><bean:message key="prompt.status"/></td>
							<td class='formDe'colspan="1" >
								<html:select name="courtHearingForm" property="petitionStatus" styleId="petitionStatus">
									<html:option key="select.generic" value="" />
									<html:optionsCollection property="petitionStatuses" value="code" label="descriptionWithCode"/> 				
								</html:select>
							</td>
							<td class='formDeLabel'  colspan="1"  width="%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.filingDate"/></td>
							<td class='formDe' colspan="1" ><html:text name="courtHearingForm" property="filingDate" styleId="filingDate"  maxlength="10" size="10"/></td>
							<td class='formDeLabel'  colspan="1" width="5%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.type"/></td>
							<td class='formDe' colspan="2" >
								<html:select name="courtHearingForm" property="petitionType" styleId="petitionType">
									<html:option key="select.generic" value="" />
									<html:optionsCollection property="petitionTypes" value="code" label="descriptionWithCode"/> 				
								</html:select>
							</td>
						</tr>
						<tr>
							<td class='formDeLabel' colspan="1"  width="5%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.petitionNumber"/></td>
							<td class='formDe' colspan="5"  width="5%"><html:text name="courtHearingForm" property="petitionNumber" styleId="petitionNumber"  maxlength="10" size="10"/></td>
							<%-- <td class='formDeLabel' colspan="1"  width="5%" nowrap="nowrap"><bean:message key="prompt.amendment"/></td>
							<td class='formDe' colspan="5" >
								<html:select name="courtHearingForm" property="petitionAmendment" styleId="petitionAmendment">
									<html:option key="select.generic" value="" />
									<html:optionsCollection property="petitionAmendments" value="code" label="description"/> 				
								</html:select>
							</td> --%>
						</tr>
						<tr>
							<td class='formDeLabel' colspan="1"  width="5%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.allegation"/></td>
							<td class='formDe' colspan="7" >
								<html:text name="courtHearingForm" property="petitionAllegation" styleId="allegation"  maxlength="6" size="6"/>  
								<html:submit property="submitAction" styleId="validateCode"><bean:message key="button.validateOffenseCode" /></html:submit>&nbsp;Or&nbsp;
								<html:submit onclick="spinner()" property="submitAction"><bean:message key="button.searchForOffenseCode" /></html:submit>
								<input type="hidden" name="validateMsg" value="<bean:write name="courtHearingForm" property="validateMsg" />"  id="valOffMsgId" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- PETITION Information ends-->
		<br/>
	<!-- HEARING Information  starts-->
	<div>
		<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue" colspan="5" id="hearingTbl">
			<tr>
				<td>
					<table width='100%' cellpadding="2" cellspacing="1">
						<tr class="crtDetailHead">
							<td colspan="8"><bean:message key="prompt.hearingInfo"/></td> 
						</tr>
						<tr>
							<td class='formDeLabel' colspan="1" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.court"/></td>
							<td class='formDe' colspan="1"><html:text name="courtHearingForm" property="courtId" styleId="courtId"  maxlength="3" size="3"/></td>
							<td class='formDeLabel'  colspan="1" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.date"/></td>
							<td class='formDe' colspan="1" ><html:text name="courtHearingForm" property="courtDate" styleId="courtDate"  maxlength="10" size="10"/></td>
							<td class='formDeLabel'  colspan="1"  width="5%"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.time"/></td>
							<td class='formDe' colspan="3" ><html:text name="courtHearingForm" property="courtTime" styleId="courtTime"  maxlength="4" size="4"/></td>
						</tr>
						<tr>
							<td class='formDeLabel' colspan="1" width="5%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.hearingType"/></td>
							<td class='formDe' colspan="1" width="25%">
								<html:select name="courtHearingForm" property="hearingType" styleId="hearingType">
									<html:option key="select.generic" value=""/>
									<html:optionsCollection property="hearingTypes" value="code" label="descriptionWithCode"/> 				
								</html:select>
							</td>
							<td class='formDeLabel' colspan="1" width="5%" nowrap="nowrap"><bean:message key="prompt.aty"/></td>
							<td class='formDe' colspan="5">	<html:radio name="courtHearingForm" property="attorneyConnection" value="HAT" styleId="HAT"/>HAT<html:radio name="courtHearingForm" property="attorneyConnection" value="AAT" styleId="AAT"/>AAT
							<html:radio name="courtHearingForm" property="attorneyConnection" value="PDO" styleId="PDO"/>PDO</td>
						</tr>
						<tr>
							<td class='formDeLabel' colspan="1" width="5%" nowrap="nowrap"><bean:message key="prompt.bar"/></td>
							<td class='formDe' colspan="7">
							  <html:text name="courtHearingForm" property="barNumber" styleId="barNumber"  maxlength="8" size="8"/>  
							  <html:submit property="submitAction" styleId="validateBarNumber"><bean:message key="button.validateBarNumber"/></html:submit>&nbsp;Or&nbsp;
							  <html:submit onclick="spinner()" property="submitAction"><bean:message key="button.searchAttorney" /></html:submit>
							</td>
						</tr>
						<tr>
							<td class='formDeLabel'  colspan="1"  width="5%" nowrap="nowrap"><bean:message key="prompt.attorney"/></td>
							<td class='formDe' colspan="7" width="5%" nowrap="nowrap" colspan="3">
								<input type="text" name="attorneyName" id="attorneyName" size="50" maxlength="50" value="<bean:write name="courtHearingForm" property="attorneyName"/>" disabled/>
								<input type="hidden" name="validateMsg" value="<bean:write name="courtHearingForm" property="validateMsg" />"  id="valOffMsgId" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- HEARING Information ends-->
		</div>
		<br/>
		<div>
		<!-- Subpoena Information  starts-->
		<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center" class="borderTableBlue" colspan="5" id="subpoenaTbl">
			<tr>
				<td>
					<table width='100%' cellpadding="2" cellspacing="1">
						<tr class="crtDetailHead">
							<td colspan="8"><bean:message key="prompt.subpoenaInfo"/></td> 
						</tr>
						<tr>
							<td class='formDeLabel' width="5%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.preparationDate"/></td>
							<td class='formDe' ><html:text name="courtHearingForm" property="preparationDate" styleId="preparationDate"  maxlength="10" size="10"/></td>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.cert"/></td>
							<td class='formDe' ><html:text name="courtHearingForm" property="cert" styleId="cert"  maxlength="1" size="1"/></td>
							<td class='formDeLabel'  width="5%" nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.plaintiff"/></td>
							<td class='formDe' ><html:text name="courtHearingForm" property="plaintiff" styleId="plaintiff"  maxlength="50" size="50"/></td>
						</tr>
						<tr>
							<td class='formDeLabel' width="5%" nowrap="nowrap"><bean:message key="prompt.father"/></td>
							<td class='formDe' colspan="6"><bean:write name="courtHearingForm" property="father"/>
								<logic:notEmpty name="courtHearingForm" property="father">
									<bean:write name="courtHearingForm" property="fatherAddress.streetAddress"/>
									<logic:notEqual name="courtHearingForm" property="fatherAddress.cityStateZip" value="">
			  							<bean:write name="courtHearingForm" property="fatherAddress.cityStateZip"/>
			  						</logic:notEqual>
			  						<logic:notEqual name="courtHearingForm" property="fatherContact" value="null">
			  							<bean:write name="courtHearingForm" property="fatherContact.contactPhoneNumber.formattedPhoneNumber"/>
			  						</logic:notEqual>
		  						</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.mother"/></td>
							<td class='formDe' colspan="6"><bean:write name="courtHearingForm" property="mother"/>
								<logic:notEmpty name="courtHearingForm" property="mother">
									<bean:write name="courtHearingForm" property="motherAddress.streetAddress"/>
									<logic:notEqual name="courtHearingForm" property="motherAddress.cityStateZip" value="">
			  							<bean:write name="courtHearingForm" property="motherAddress.cityStateZip"/>
			  						</logic:notEqual>
			  						<logic:notEqual name="courtHearingForm" property="motherContact" value="null">
			  							<bean:write name="courtHearingForm" property="motherContact.contactPhoneNumber.formattedPhoneNumber"/>
			  						</logic:notEqual>
		  						</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.other"/></td>
							
							<td class='formDe' colspan="6">
								<div id="guardian">
									<logic:notEmpty name="courtHearingForm" property="guardian">
										<bean:write name="courtHearingForm" property="guardian"/>
										<bean:write name="courtHearingForm" property="guardianAddress.streetAddress"/>
										<logic:notEqual name="courtHearingForm" property="guardianAddress.cityStateZip" value="">
				  							<bean:write name="courtHearingForm" property="guardianAddress.cityStateZip"/>
				  						</logic:notEqual>
				  						<logic:notEqual name="courtHearingForm" property="guardianContact" value="null">
				  							<bean:write name="courtHearingForm" property="guardianContact.contactPhoneNumber.formattedPhoneNumber"/>
				  						</logic:notEqual>
			  						</logic:notEmpty>
		  						</div>
		  						<div id="careGiver">
			  						<logic:notEmpty name="courtHearingForm" property="careGiver">
			  							<bean:write name="courtHearingForm" property="careGiver"/>
										<bean:write name="courtHearingForm" property="careGiverAddress.streetAddress"/>
										<logic:notEqual name="courtHearingForm" property="careGiverAddress.cityStateZip" value="">
				  							<bean:write name="courtHearingForm" property="careGiverAddress.cityStateZip"/>
				  						</logic:notEqual>
				  						<logic:notEqual name="courtHearingForm" property="careGiverContact" value="null">
				  							<bean:write name="courtHearingForm" property="careGiverContact.contactPhoneNumber.formattedPhoneNumber"/>
				  						</logic:notEqual>
			  						</logic:notEmpty>
		  						</div>
		  					</td>
		  			<!-- 		</div> -->
						</tr>
						<tr>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.relationship"/></td>
							<td class='formDe' colspan="6"><bean:write name="courtHearingForm" property="relationship"/></td>
						</tr>
						
						<tr>
							<td class='formDeLabel'  width="5%" nowrap="nowrap"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.subpoenasPrint"/></td>
							<td class='formDe' colspan="6">
								<logic:iterate indexId="idx" id="subpoenasToBePrintedIdx" name="courtHearingForm" property="subpoenasToBePrinted">
									<%-- <input type=radio name="selectedSubpoenasToBePrinted" id="<bean:write name="subpoenasToBePrintedIdx" property="code"/>" value="<bean:write name="subpoenasToBePrintedIdx" property="code"/>"><bean:write name="subpoenasToBePrintedIdx" property="description"/></input> --%>
									<logic:equal name="subpoenasToBePrintedIdx" property="code" value="F">
									<logic:equal name="courtHearingForm" property="isFatherDeceased" value="Y">
									<input type="radio" name="selectedSubpoenasToBePrinted" disabled="true" id="<bean:write name="subpoenasToBePrintedIdx" property="code"/>" value="<bean:write name="subpoenasToBePrintedIdx" property="code"/>"><span title="Deceased"><bean:write name="subpoenasToBePrintedIdx" property="description"/></span></input>
									</logic:equal>
									<logic:notEqual name="courtHearingForm" property="isFatherDeceased" value="Y">
									<logic:equal name="courtHearingForm" property="isFatherIncarcerated" value="Y">
									<input type="radio" name="selectedSubpoenasToBePrinted" disabled="true" id="<bean:write name="subpoenasToBePrintedIdx" property="code"/>" value="<bean:write name="subpoenasToBePrintedIdx" property="code"/>"><span title="Incarcerated"><bean:write name="subpoenasToBePrintedIdx" property="description"/></span></input>
									</logic:equal>
									<logic:notEqual name="courtHearingForm" property="isFatherIncarcerated" value="Y">
									<input type="radio" name="selectedSubpoenasToBePrinted" id="<bean:write name="subpoenasToBePrintedIdx" property="code"/>" value="<bean:write name="subpoenasToBePrintedIdx" property="code"/>"><bean:write name="subpoenasToBePrintedIdx" property="description"/></input>
									</logic:notEqual>
									</logic:notEqual>
								</logic:equal>
								<logic:equal name="subpoenasToBePrintedIdx" property="code" value="M">
									<logic:equal name="courtHearingForm" property="isMotherDeceased" value="Y">
									<input type="radio" name="selectedSubpoenasToBePrinted" disabled="true"  id="<bean:write name="subpoenasToBePrintedIdx" property="code"/>" value="<bean:write name="subpoenasToBePrintedIdx" property="code"/>"><span title="Deceased"><bean:write name="subpoenasToBePrintedIdx" property="description" /></span></input>
									</logic:equal>
									<logic:notEqual name="courtHearingForm" property="isMotherDeceased" value="Y">
									<logic:equal name="courtHearingForm" property="isMotherIncarcerated" value="Y">
									<input type="radio" name="selectedSubpoenasToBePrinted" disabled="true"  id="<bean:write name="subpoenasToBePrintedIdx" property="code"/>" value="<bean:write name="subpoenasToBePrintedIdx" property="code"/>"><span title="Incarcerated"><bean:write name="subpoenasToBePrintedIdx" property="description" /></span></input>
									</logic:equal>
									<logic:notEqual name="courtHearingForm" property="isMotherIncarcerated" value="Y">
									<input type="radio" name="selectedSubpoenasToBePrinted" id="<bean:write name="subpoenasToBePrintedIdx" property="code"/>" value="<bean:write name="subpoenasToBePrintedIdx" property="code"/>"><bean:write name="subpoenasToBePrintedIdx" property="description"/></input>
									</logic:notEqual>
									</logic:notEqual>
								</logic:equal>
								<logic:equal name="subpoenasToBePrintedIdx" property="code" value="J">
									<input type="radio" name="selectedSubpoenasToBePrinted" id="<bean:write name="subpoenasToBePrintedIdx" property="code"/>" value="<bean:write name="subpoenasToBePrintedIdx" property="code"/>"><bean:write name="subpoenasToBePrintedIdx" property="description"/></input>
								</logic:equal>
								<logic:equal name="subpoenasToBePrintedIdx" property="code" value="O">
									<input type="radio" name="selectedSubpoenasToBePrinted" id="<bean:write name="subpoenasToBePrintedIdx" property="code"/>" value="<bean:write name="subpoenasToBePrintedIdx" property="code"/>"><bean:write name="subpoenasToBePrintedIdx" property="description"/></input>
								</logic:equal>
								</logic:iterate> 
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</div>
		<!-- Subpoena Information ends-->
		<br/>
		<table width="100%" border="0">
			<tr>
				<td align="center">				
					<jims2:isAllowed requiredFeatures='<%=Features.JCW_CRTPETUPDATE%>'>
						<html:submit property="submitAction" styleId="petitionUpdateBtn"><bean:message key="button.petitionUpdateBtn"/></html:submit>
					</jims2:isAllowed>
					<html:submit property="submitAction" styleId="petitionCorrectionBtn"><bean:message key="button.petitionCorrection" /></html:submit> 
					<html:submit property="submitAction" styleId="hearingCorrectionBtn"><bean:message key="button.hearingCorrection" /></html:submit>
					<html:submit property="submitAction" styleId="submitInitialSettingBtn"><bean:message key="button.submit" /></html:submit>
				</td>
			</tr>
			<tr>
				<td align="center">
					<html:submit property="submitAction"  styleId="courtActionBtn"><bean:message key="button.courtAction2" /></html:submit>
					<html:submit property="submitAction"  styleId="submitReferralSearch"><bean:message key="button.referralSummary" /></html:submit>
					<html:submit property="submitAction" styleId="initialSettingCourtMainMenuBtn"><bean:message key="button.courtMainMenu"></bean:message></html:submit>
				</td>
			</tr>
		</table>
		
		<!-- html hidden fields starts -->
	 	<html:hidden styleId="referralDate" name="courtHearingForm" property="referralDate"/>
	 	<html:hidden styleId="action" name="courtHearingForm" property="action"/>
	 	<html:hidden styleId="prevAction" name="courtHearingForm" property="prevAction"/>
	 	<html:hidden styleId="actionError" name="courtHearingForm" property="actionError"/>
	 	<html:hidden styleId="actionMessage" name="courtHearingForm" property="actionMessage"/>
	 	<html:hidden styleId="validateMsg" name="courtHearingForm" property="validateMsg"/>
	 	<html:hidden styleId="finalDispEntered" name="courtHearingForm" property="finalDispEntered"/>
		<html:hidden styleId="cursorPosition" name="courtHearingForm" property="cursorPosition"/>
		<html:hidden styleId="hearingCorrectionFlg" name="courtHearingForm" property="hearingCorrectionFlag"/>
		<html:hidden styleId="petitionCorrectionFlg" name="courtHearingForm" property="petitionCorrectionFlag"/>
		<html:hidden styleId="selectedSubpoenas" name="courtHearingForm" property="selectedSubpoenas"/>
		<html:hidden styleId="isCourtActionReady" name="courtHearingForm" property="isCourtActionReady"/>
		
		<html:hidden styleId="isFatherIncarceratedId" name="courtHearingForm" property="isFatherIncarcerated"/>
		<html:hidden styleId="isMotherIncarceratedId" name="courtHearingForm" property="isMotherIncarcerated"/>
		<html:hidden styleId="isFatherDeceasedId" name="courtHearingForm" property="isFatherDeceased"/>
		<html:hidden styleId="isMotherDeceasedId" name="courtHearingForm" property="isMotherDeceased"/>
		
		<logic:empty name="courtHearingForm" property="careGiver">
			<input type="hidden" id="filterCareGiver" name="courtHearingForm" value="careGiver"/>
		</logic:empty>
		<logic:empty name="courtHearingForm" property="guardian">
			<input type="hidden" id="filterGuardian" name="courtHearingForm" value="guardian"/>
		</logic:empty>
		<logic:empty name="courtHearingForm" property="mother">
			<input type="hidden" id="filterMother" name="courtHearingForm" value="mother"/>
		</logic:empty>
		<logic:empty name="courtHearingForm" property="father">
			<input type="hidden" id="filterFather" name="courtHearingForm" value="father"/>
		</logic:empty>
		<input type="hidden" id="holidaysList" name="courtHearingForm" value='<bean:write name="courtHearingForm" property="holidaysList"/>'/>
	</html:form>
	<div id="pdf-download" style="display:none">
 	<!-- iframe created dynamically. -->
	</div>
	<div align='center'>
		<script type="text/javascript">renderBackToTop()</script>
	</div>
</body>
</html:html>