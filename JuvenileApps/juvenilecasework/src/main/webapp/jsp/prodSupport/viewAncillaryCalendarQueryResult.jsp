<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="elogic"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>


<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title>Juvenile Casework
	-/prodSupport/viewAncillaryCalendarQueryResult</title>


<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript'
	src="/<msp:webapp/>js/prodSupport/updateAncillaryCourtCalendar.js"></script>

</head>

<body>

	<html:form styleId ="updateAncillaryRecord" action="/PerformUpdateAncillaryCalendarRecord" >
		<logic:messagesPresent message="true">
			<table width="100%">
				<tr>
					<td align="center" class="messageAlert"><html:messages
							id="message" message="true">
							<font style="font-weight: bold;" color="green" size="3"
								face="Arial"><bean:write name="message" />
						</html:messages></font></td>
				</tr>
			</table>
		</logic:messagesPresent>
		
		<!-- BEGIN ERROR TABLE -->
		<table width="100%">
			<%-- <tr>
				<td align="center" class="errorAlert"><html:errors></html:errors></td>
			</tr> --%>
			<tr>
				<td align="center" colspan="4" class="errorAlert"><font
					style="font-weight: bold;" color="red" size="3" face="Arial"><html:errors></html:errors>
				</font></td>
			</tr>
		</table>
		<!-- END ERROR TABLE -->
		<BR>		
		<div>

			<h2 align="center">
				Results for Updating Petition Number  =
				<bean:write name="ProdSupportForm" property="petitionNum" />
			</h2>
			<BR>
			
			<!-- End Error Message Area -->

			
			
			<logic:notEmpty name="ProdSupportForm" property="ancillaryCalendarRecords">
			<table class="resultTbl" border="1" width="750" align="center">

				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">Petition
							Number</font></td>
					<td><font size="-1"><bean:write name="ProdSupportForm"
								property="petitionNum" />&nbsp;</font></td>
				</tr>
				<tr>
					<td bgcolor="gray"><font color="white" face="bold" size="-1">Respondent
							Name</font></td>
					<td><font size="-1"><bean:write name="ProdSupportForm"
								property="respondentName" />&nbsp;</font>
					</td>
				</tr>
			
			</table>
			<p align="center">
				<b><i>Change the values of the field(s) <br>
						and Click Update</i> </b>
			</p>
			<table class="resultTbl" border="1" width="800" align="center">
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Chain Number</font>
					</td>
					<td  bgcolor="white">
						<font  size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.chainNumber"/></font>
					</td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Chain Sequence Number</font>
					</td>
					<td  bgcolor="white">
						<font size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.seqNum"/></font>
					</td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Assigned Court</font>
					</td>
					<td bgcolor="white">
						<font  size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.oldCourt"/></font>
					</td>
					<td bgcolor="white"> 
						<html:select name="ProdSupportForm" property="ancillaryCalendarRecord.court" styleId='courtId'>			
							<html:option key="1" value="313" />
							<html:option key="2" value="314" />
							<html:option key="3" value="315" />
						</html:select>
					</td> 
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Hearing Date</font>
					</td>
					<td  bgcolor="white">
						<font  size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.oldCourtDate"/></font>
					</td>
					<td bgcolor="white">
						<font size="-1"><html:text name="ProdSupportForm" property="ancillaryCalendarRecord.courtDate" styleId='courtDate' /></font>
					</td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Hearing Time</font>
					</td>
					<td  bgcolor="white">
						<font  size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.oldFormattedCourtTime"  /></font>
					</td>
					<td bgcolor="white"><font size="-1"><html:text name="ProdSupportForm" property="ancillaryCalendarRecord.formattedCourtTime" styleId='courtTime' size="5" maxlength="5" /></font></td>
				</tr>
				
				
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Hearing Decision(Result)</font>
					</td>
					<td bgcolor="white"><font size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.oldcourtResult"/>&nbsp;</font></td>
					<td bgcolor="white"> 
						<html:select name="ProdSupportForm" property="ancillaryCalendarRecord.courtResult"  >
							<html:option key="select.generic" value="" />
							<html:optionsCollection name="ProdSupportForm" property="courtDecisionsResponses" value="codeAlpha" label="codeAlpha"/> 
						</html:select>
					</td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Hearing Disposition</font>
					</td>
					<td  bgcolor="white"><font size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.olddisposition"/>&nbsp;</font></td>
					<td  bgcolor="white"> 
						<html:select name="ProdSupportForm" property="ancillaryCalendarRecord.disposition" styleId='crtDisposition' >
						<html:option key="select.generic" value="" />
						<html:optionsCollection name="ProdSupportForm" property="detentionHearingResults" value="codeAlpha" label="codeAlpha"/> 				
						</html:select> 
					</td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Reset Hearing Type</font>
					</td>
					<td  bgcolor="white"><font size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.oldresetHearingType"/>&nbsp;</font></td>
					<td bgcolor="white"> 
						<html:select name="ProdSupportForm" property="ancillaryCalendarRecord.resetHearingType" styleId='resetHearingType'>
						<html:option key="select.generic" value=""/>
						<html:optionsCollection property="courtHearingTypes" value="code" label="code"/> 				
						</html:select>
					</td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Petition Number</font>
					</td>
					<td  bgcolor="white">
						<font  size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.oldPetitionNumber"/></font>
					</td>
					<td bgcolor="white"><font size="-1"><html:text name="ProdSupportForm" property="ancillaryCalendarRecord.petitionNumber" size="12" styleId='petitionNumber' /></font></td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Setting Reason</font>																										
					</td>
					<td bgcolor="white"><font size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.oldSettingReason"/></font></td>
					<td bgcolor="white"> 
						<html:select name="ProdSupportForm" property="ancillaryCalendarRecord.settingReason" styleId='settingReason'>
						<html:option key="select.generic" value=""/>
						<html:optionsCollection property="courtHearingTypes" value="code" label="code"/> 				
						</html:select>
					</td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Type Case</font>
					</td>
					<td bgcolor="white"><font size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.oldTypeCase"/></font></td>
					<td bgcolor="white"> 
						<html:select name="ProdSupportForm" property="ancillaryCalendarRecord.typeCase" styleId='typeCase'>
						<html:option key="select.generic" value="" /> 
						<html:option key="A" value="A" />
						<html:option key="P" value="P" />
						<html:option key="I" value="I" />
						<html:option key="C" value="C" />			 
						</html:select>
					</td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Filing Date</font>
					</td>
					<td bgcolor="white"><font size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.oldFilingDate" formatKey="date.format.mmddyyyy"/>&nbsp;</font></td>
					<td bgcolor="white"><font size="-1"><html:text name="ProdSupportForm" property="ancillaryCalendarRecord.filingDate" styleId='filingDate' size="10"/></font></td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Attorney Bar Number</font>
					</td>
					<td bgcolor="white"><font size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.oldbarNum"/>&nbsp;</font></td>
					<td bgcolor="white"><font size="-1"><html:text name="ProdSupportForm" property="ancillaryCalendarRecord.barNum" styleId='barNumber' size="9" maxlength="9"/></font>
					<html:button property="submitAction" styleId="validateBarNumBtn"><bean:message key="button.validateBarNumber"/></html:button> <br/>
					</td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Attorney Connection</font>
					</td>
					<td bgcolor="white"><font size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.oldattorneyConnection"/>&nbsp;</font></td>
					<td bgcolor="white"> 
						<html:select name="ProdSupportForm" property="ancillaryCalendarRecord.attorneyConnection" styleId='attorneyConnection'>
						<html:option key="select.generic" value="" /> 
						<html:option key="1" value="HAT" />
						<html:option key="2" value="AAT" />
						<html:option key="3" value="PDO" />			 
						</html:select>
					</td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Attorney Name</font>
					</td>
					<td bgcolor="white"><font size="-1"><bean:write name="ProdSupportForm" property="ancillaryCalendarRecord.oldattorneyName"/>&nbsp;</font></td>
					<td bgcolor="white"><font size="-1"><html:text name="ProdSupportForm" property="ancillaryCalendarRecord.attorneyName"/></font>
					<html:button property="submitAction" styleId="searchAttorneyBtn"><bean:message key="button.searchAttorney"/></html:button> <br/>
					</td>
				</tr>
				<tr class="detailHead">
					<td bgcolor="gray">
						<font color="white" face="bold" size="-1">Respondant Name</font>
					</td>
					<td bgcolor="white"><font size="-1"><bean:write name="ProdSupportForm"
								property="ancillaryCalendarRecord.oldRespondantName" />&nbsp;</font>
					</td>
					<td bgcolor="white"><font size="-1"><html:text name="ProdSupportForm" property="ancillaryCalendarRecord.respondantName" size="20" styleId='respondantName' /></font></td>
				</tr>
			</table>

					
			
			</logic:notEmpty>
		<BR>

			
			
		</div>


		<table align="center"">
			<tr>
				<html:submit property="submitAction" styleId="updateBtn" onclick="return disableSubmit(this, this.form);" ><bean:message key="button.updateRecord" /></html:submit>
				<td>&nbsp;</td>

			</tr>
		</table>
		
	</html:form>

	<html:form action="/UpdateAncillaryCalendarQuery?clr=Y"
		onsubmit="return this;">
		<table align="center"">
			<tr>
				<td><html:submit value="Back to Query" />
				</td>
			</tr>
		</table>
	</html:form>


</body>
</html:html>




