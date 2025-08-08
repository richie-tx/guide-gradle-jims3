<!DOCTYPE HTML>
<%-- User selects the "Add More Abuse Info" button to create new abuse info --%>
<%--MODIFICATIONS --%>
<%-- 06/22/2005	Hien Rodriguez	Create JSP --%>
<%-- 02/23/2006	Leslie Deen		Revised JSP for ER#27756 800x600 layout issue--%>
<%-- 12/15/2006 Hien Rodriguez  ER#37077 Remove CasefileOperation box --%>
<%-- 07/16/2007 Leslie Deen		Defect #43633 add nowrap attribute to label tag --%>
<%-- 10/29/2015 R Capestani #30819 MJCW: PROD Juv Profile > Abuse Tab - click on any hyperlink in abuse list - see 4 Tiny Squares Showing (IE11 conversion) --%>
<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims"%>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2"%>


<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>
<%@ page import="naming.PDCodeTableConstants"%>

<%--BEGIN HEADER TAG--%>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1" />
<%-- <html:javascript formName="juvenileAbuseForm" /> --%>
<script type="text/javascript">
function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen + 1)
	{
  	alert("Your input has been truncated to "  +maxlen +" characters!");
	}

	if (field.value.length > maxlen)
	{
  	field.value = field.value.substring(0, maxlen);  	
	}
} 

  
</script>

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%@include file="../jQuery.fw"%>
<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css"
	href="/<msp:webapp/>css/casework.css" />
<html:base />

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading" /> -
	interviewInfoDualStatusCreate.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/dualstatus.js"></script>


</head>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" topmargin=0
	leftmargin="0">
	<html:form action="/displayJuvenileAbuseCreate" target="content">

		<input type="hidden" name="helpFile"
			value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|183">

		<%-- BEGIN HEADING TABLE --%>
		<table width="98%">
			<tr>
				<td align="center" class="header"><bean:message
						key="title.juvenileCasework" /> - <bean:message
						key="title.juvenileProfile" /> - Create Dual Status Information</td>
			</tr>
		</table>
		<%-- END HEADING TABLE --%>

		<%-- BEGIN INSTRUCTION TABLE --%>
		<div class=spacer></div>
		<table width="98%" border="0">
			<tr>
				<td>
					<ul>
						<li>Fill in required fields then click "Next" button to
							continue.</li>
					</ul></td>
			</tr>
			<tr>
				<td class="required"><bean:message key="prompt.2.diamond" />
					<bean:message key="prompt.requiredFieldsInstruction" />
				</td>
			</tr>			
		</table>
		<%-- END INSTRUCTION TABLE --%>

		<%-- BEGIN DISPLAY PROFILE HEADER --%>
		<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp"
			flush="true">
			<tiles:put name="headerType" value="profileheader" />
		</tiles:insert>
		<%-- END DISPLAY PROFILE HEADER --%>

		<%-- BEGIN DETAIL TABLE --%>
		<div class=spacer></div>
		<table align="center" width="98%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td valign=top>
					<table width='100%' border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign=top><tiles:insert
									page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
									<tiles:put name="tabid" value="interviewinfotab" />
									<tiles:put name="juvnum"
										value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
								</tiles:insert></td>
						</tr>
						<tr>
							<td bgcolor='#33cc66'><img
								src="/<msp:webapp/>images/spacer.gif" width=5>
							</td>
						</tr>
					</table>

					<table width='100%' border="0" cellpadding="0" cellspacing="0"
						class="borderTableGreen">
						<tr>
							<td valign=top align=center>
								<div class=spacer></div>
								<table width='98%' border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td valign=top><tiles:insert
												page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
												<tiles:put name="tabid" beanName="juvenileTraitsForm"
													beanProperty="categoryName" />
												<tiles:put name="juvnum"
													value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
											</tiles:insert></td>
									</tr>
									<tr>
										<td bgcolor='#6699FF'><img
											src="/<msp:webapp/>images/spacer.gif" width=5>
										</td>
									</tr>
								</table> <logic:notEmpty name="juvenileTraitsForm"
									property="categoryName">
									<div class=spacer></div>
									<table width='98%' border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td valign="top"><tiles:insert
													page="../caseworkCommon/abuseDualTabs.jsp" flush="true">
													<tiles:put name="tabid" beanName="juvenileTraitsForm"
														beanProperty="categoryName" />
													<tiles:put name="juvnum"
														value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
												</tiles:insert></td>
										</tr>
										<tr>
											<td bgcolor='#ff6666'><img
												src="/<msp:webapp/>images/spacer.gif" width="5">
											</td>
										</tr>
									</table>
								</logic:notEmpty>
								<table width='98%' border="0" cellpadding="0" cellspacing="0"
									class="borderTableRed">
									<tr>
										<td valign=top align=center>
											<%-- BEGIN CASEFILE LIST TABLE --%>
											<div class=spacer></div>
											<table width='98%' border="0" cellpadding="2" cellspacing="1"
												class="borderTableBlue">
												<tr class=detailHead>
													<td colspan=2 class=detailHead><bean:message
															key="prompt.addDualStatusSelectCasefile" />
													</td>
												</tr>
												<tr>
													<td valign=top align=center>
														<%
															int RecordCounter = 0;
														%>
														<table width="100%" border="0" cellpadding=2 cellspacing=1>
															<%-- display detail header --%>
															<tr class=formDeLabel>
																<td><bean:message key="prompt.supervision#" />
																</td>
																<td><bean:message key="prompt.probationOfficerName" />
																</td>
																<td><bean:message key="prompt.supervisionType" />
																</td>
																<td><bean:message key="prompt.supervisionEndDate" />
																</td>
																<td><bean:message key="prompt.caseStatus" />
																</td>
															</tr>
															<%-- display detail info --%>
															<tr bgcolor='#ffffff'>
																<td align="left"><a
																	href="javascript:openWindow('/JuvenileCasework/displayJuvenileProfileCasefileDetails.do?casefileId=<bean:write name="juvenileTraitsForm" property="currentCasefile.supervisionNum"/>')">
																		<bean:write name="juvenileTraitsForm"
																			property="currentCasefile.supervisionNum" /> </a></td>
																<td align="left"><bean:write
																		name="juvenileTraitsForm"
																		property="currentCasefile.probationOfficerFullName" />
																</td>
																<td align="left"><bean:write
																		name="juvenileTraitsForm"
																		property="currentCasefile.supervisionType" />
																</td>
																<td align="left"><bean:write
																		name="juvenileTraitsForm"
																		property="currentCasefile.supervisionEndDate"
																		format="MM/dd/yyyy" />
																</td>
																<td align="left"><bean:write
																		name="juvenileTraitsForm"
																		property="currentCasefile.caseStatus" />
																</td>
															</tr>
														</table></td>
												</tr>
											</table> <%-- END CASEFILE LIST TABLE --%> <%-- BEGIN ABUSE INFO TABLE --%>
											<div class=spacer></div> <%-- <bean:size id="moreThanOne" name="juvenileTraitsForm" property="rootTraitTypes" /> --%>
											<table width='98%' border="0" cellpadding="1" cellspacing="0"
												class="borderTableBlue">
												<tr class=detailHead>
													<td colspan="4"><bean:message
															key="prompt.dualstatusInfo" />
													</td>
												</tr>

												<tr>
													<td class=formDeLabel width="10%"><bean:message
															key="prompt.diamond" />
														<bean:message key="prompt.traitType" />
													</td>
													<td class=formDe width="40%">
															<html:text name="juvenileTraitsForm"
															property="traitsForm.traitTypeId" styleId="traitType"
															value="ADMINISTRATIVE" disabled="true" />
													</td>
													<td class=formDeLabel width="10%" nowrap><bean:message
															key="prompt.2.diamond" /> <bean:message key="prompt.info" />&nbsp;<bean:message
															key="prompt.source" /></td>
													<td class="formDe" width="40%"><html:select
															name="juvenileAbuseForm" property="informationSrcCd"
															styleId="informationSrcCd">
															<html:option value="">Please Select</html:option>
															<html:optionsCollection name="juvenileTraitsForm"
																property="informationSources" value="code"
																label="descriptionWithCode" />
														</html:select><html:hidden styleId='hdninformationSource'  name="juvenileAbuseForm" property="informationSource" />
													</td>
												</tr>

												<tr>
													<td class=formDeLabel width="10%" nowrap><bean:message
															key="prompt.diamond" />
														<bean:message key="prompt.traitType" />&nbsp;<bean:message
															key="prompt.description" />
													</td>
													<td class="formDe" width="40%"><html:text
															name="juvenileTraitsForm"
															property="traitsForm.traitTypeDescriptionId"
															styleId="traitTypeDesc"
															value="DUAL STATUS UNIT ONLY-YOUTH IDENTIFIED" size="50"
															disabled="true" />
													</td>
													<td class=formDe width="10%"></td>
													<td class="formDe" width="40%"></td>																									
												</tr>
												<tr>
													<td class=formDeLabel><bean:message
															key="prompt.diamond" />
														<bean:message key="prompt.referralRegion" />
													</td>
													<td class=formDe><html:radio name="juvenileAbuseForm"
															property="referralRegion" styleId="referralRegionR6A"
															value="R6A" />R6A <html:radio name="juvenileAbuseForm"
															property="referralRegion" styleId="referralRegionR6B"
															value="R6B" />R6B <html:radio name="juvenileAbuseForm"
															property="referralRegion" styleId="referralRegionOOC"
															value="OOC" />OOC
													</td>
													<td class=formDe width="10%"></td>
													<td class="formDe" width="40%"></td>
												</tr>
												<tr>
													<td class=formDeLabel><bean:message
															key="prompt.diamond" />
														<bean:message key="prompt.custodyStatus" />
													</td>
													<td class=formDe colspan="3"><html:select
															name="juvenileAbuseForm" property="custodyStatus"
															styleId="custodyStatus">
															<html:option key="select.generic" value="" />
															<html:option value="TMC">TMC</html:option>
															<html:option value="JMC">JMC</html:option>
															<html:option value="PMC">PMC</html:option>
															<html:option value="UNKNOWN">UNKNOWN</html:option>
														</html:select></td>
												</tr>
												<tr>
													<td class=formDeLabel><bean:message
															key="prompt.diamond" />
														<bean:message key="prompt.CPSlevelofCare" />
													</td>
													<td class=formDe colspan="3"><html:select
															name="juvenileAbuseForm" property="cpslevelofCare"
															styleId="cpslevelofCare">
															<html:option key="select.generic" value="" />
															<html:option value="BASIC">BASIC</html:option>
															<html:option value="MODERATE">MODERATE</html:option>
															<html:option value="SPECIALIZED">SPECIALIZED</html:option>
															<html:option value="INTENSIVE">INTENSIVE</html:option>
															<html:option value="UNKNOWN">UNKNOWN</html:option>
														</html:select></td>
												</tr>

												<tr>
													<%-- <td class=formDeLabel width="1%" nowrap><bean:message key="prompt.wasCpsInvolved" /></td> --%>
													<td class=formDeLabel width="1%" nowrap><bean:message
															key="prompt.diamond" />
														<bean:message key="prompt.parentalRights" />
													</td>
													<td class=formDe colspan="3"><html:select
															name="juvenileAbuseForm" property="parentalrightsTermination"
															styleId="parentalrightsTermination">
															<html:option key="select.generic" value="" />
															<html:option value="YES">YES</html:option>
															<html:option value="NO">NO</html:option>
															<html:option value="UNKNOWN">UNKNOWN</html:option>
														</html:select></td>
													<%-- <td class=formDe colspan=3>Yes<html:radio
															name="juvenileAbuseForm"
															property="parentalrightsTermination"
															styleId="parentalrightsTerminationYes" value="1" />&nbsp;No<html:radio
															name="juvenileAbuseForm"
															property="parentalrightsTermination"
															styleId="parentalrightsTerminationNo" value="0" />
													</td> --%>
												</tr>

												<tr>
													<td class=formDeLabel nowrap width="1%"><bean:message
															key="prompt.diamond" />
														<bean:message key="prompt.CPSServices" />
													</td>
													<td class=formDe colspan=3><html:select
															name="juvenileAbuseForm" property="CPSServices"
															multiple="true" styleId="CPSServices">
															<%-- <html:option key="select.generic" value="" /> --%>
															<%-- <html:optionsCollection property="juvenileRelationships" value="code" label="description"/>	 --%>
															<html:option value="DRUG PROGRAMS">DRUG PROGRAMS</html:option>
															<html:option value="INDIVIDUAL COUNSELING">INDIVIDUAL COUNSELING</html:option>
															<html:option value="FAMILY COUNSELING">FAMILY COUNSELING</html:option>
															<html:option value="SEX COUNSELING">SEX COUNSELING</html:option>
															<html:option value="LIFE SKILLS">LIFE SKILLS</html:option>
															<html:option value="EDUCATION SERVICE">EDUCATION SERVICE</html:option>
															<html:option value="MENTOR">MENTOR</html:option>
															<html:option value="UNKNOWN">UNKNOWN</html:option>
														</html:select><html:hidden styleId='hdnselectedServices'  name="juvenileAbuseForm" property="selectedServices" />
		 						
														</td>
														
												</tr>
												<tr>
												<td class=formDeLabel colspan="4"><bean:message
														key="prompt.dualstatusComments" />&nbsp; <tiles:insert
														page="../caseworkCommon/spellCheckTile.jsp" flush="false">
														<tiles:put name="tTextField" value="dualstatusComments" />
														<tiles:put name="tSpellCount" value="spellBtn2" />
													</tiles:insert>
												</td>
												</tr>
												<tr>
													
													<td class=formDe colspan="4"><html:textarea property="dualstatusComments" rows="3" style="width:100%" onmouseout="textLimit(this, 255);" onkeyup="textLimit(this, 255);" ></html:textarea></td>
							
												</tr>
												<div class=spacer></div>
												<table width='98%' border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td>
															<div class=spacer></div> <tiles:insert
																page="../caseworkCommon/juvenileDualStatusPlacementAdd.jsp" />
															<div class=spacer></div>
														</td>
													</tr>
												</table>
											<div class=spacer></div>
											<table width="98%">
												<tr>
													<td align="center">
														<%-- <html:submit property="submitAction"><bean:message key="button.backDual"/></html:submit> --%>
<%-- 														<html:submit property="submitAction" styleId="btnBack">Back</html:submit> --%>
														<input type="button" value="Back" name="return" id="btnJuvTraitBack">
														<%-- <html:submit property="submitAction" ><bean:message key="button.nextDual"></bean:message></html:submit> --%>
														<html:submit property="submitAction" styleId="btnNext">Next</html:submit>
														<%-- <html:submit property="submitAction">
															<bean:message key="button.cancel"></bean:message> --%>
														<html:submit property="submitAction" styleId="btnCancel">Cancel</html:submit>
														</html:submit>
													
													</td>
												</tr>
											</table>
											</table> <%-- END ABUSE INFO TABLE --%> <%-- BEGIN BUTTON TABLE --%>
											
											<div class=spacer></div> <%-- END BUTTON TABLE --%>
										</td>
									</tr>
								</table>
								<div class=spacer></div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<%-- END DETAIL TABLE --%>


	</html:form>

	<div class=spacer></div>

	<div align=center>
		<script type="text/javascript">
			renderBackToTop()
		</script>
	</div>
</body>
</html:html>

