<!DOCTYPE HTML>

<%-- User selects the "Family" tab in the "Interview Info" tab on Juvenile Profile Detail page --%>
<%--MODIFICATIONS --%>
<%-- 09/19/2005	 Justin Jose	Create JSP --%>
<%-- 08/21/2006  HRodriguez     Add calendar icon & implement new UI Guidelines --%>
<%-- 08/21/2012  CShimek        #74094 Add "Go" button and juvenile number input field  --%>
<%-- 05/21/2013  CShimek        #75533 Revised label value for fullName to fullNameWithRel in Relation With drop down select --%>
<%-- 10/23/2013  CShimek        #76282 Made cosmetic change to Driver License/ID Card Information block --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.Features" %>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" /> 
<html:base />

<%-- Javascript for emulated navigation --%>
<html:javascript formName="juvenileMemberForm" />

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/PopupWindow.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>

<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/juvTabInterview/familyMemberCreate.js"></script>
<title><bean:message key="title.heading" /> - familyMemberCreate.jsp</title>

</head>
<%--END HEADER TAG--%>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0">
<html:form action="/displayCreateFamilyMemberConfirm">

<logic:equal name="juvenileMemberForm" property="action" value="createMember">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|234">
</logic:equal>
<logic:equal name="juvenileMemberForm" property="action" value="findCreateMember">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|234">
</logic:equal>
<logic:equal name="juvenileMemberForm" property="action" value="updateMember">
    <input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|245">
</logic:equal>    

<%-- BEGIN HEADING TABLE --%>
<table width="98%">
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="prompt.family" /> <bean:message key="prompt.member" /> 
      		<logic:equal name="juvenileMemberForm" property="action" value="createMember"><bean:message key="prompt.create" /></logic:equal> 
      		<logic:equal name="juvenileMemberForm" property="action" value="updateMember"><bean:message key="prompt.update" /></logic:equal>
		</td>
	</tr>
</table>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>
<div class='spacer'></div>
<%-- begin instruction table --%>
<table width="98%" border="0">
	<tr>
		<td>
			<ul>
				<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_MI_U%>'>		
					<li>Complete fields and click Next button to view summary.</li>
				</jims2:isAllowed>
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required"><bean:message key="prompt.dateFieldsInstruction" /></td>
	</tr>
</table>
<%-- end instruction table --%>

<%-- begin detail table --%>
<table align="center" cellpadding='1' cellspacing='0' border='0' width='100%'>
	<tr>
    	<td>
  			<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
      			<tiles:put name="headerType" value="profileheader" />
      		</tiles:insert> <%--header info end--%>
		</td>
 	</tr>
</table>
<div class='spacer'></div>
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td><%-- begin green tabs (1st row) --%>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top">
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="family" />
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert> 
					</td>
				</tr>
				<tr>
					<td bgcolor="#33cc66" height="5"></td>
				</tr>
			</table>
			<%-- end green tabs --%> 

			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
					
					  <%-- begin red tabs (3rd row) --%>
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td valign="top">
 									  <tiles:insert page="../caseworkCommon/memberInfoTabs.jsp" flush="true">
  										<tiles:put name="tabid" value="MemberInformation" />
  										<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
  									</tiles:insert> 
									</td>
								</tr>
								<tr>
									<td bgcolor="#6699FF" height="5"></td>
								</tr>
						</table>
						<%-- end red tabs --%> 

						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
							<tr>
								<td valign="top" height="5"></td>
							</tr>
							<tr>
								<%-- Be sure to include the autoTab.js --%>
								<td valign="top" align="center">
								<%-- begin family member table  --%>
									<table border="0" cellpadding="2" width="98%" cellspacing="1" class="borderTableBlue">
										<tr>
											<td colspan="4" class="detailHead">
												<table width="100%" cellspacing="0" cellpadding="2">
													<tr>
														<logic:equal name="juvenileMemberForm" property="action" value="createMember">
															<td width="40%" class="detailHead">
																<bean:message key="prompt.family" />
																<bean:message key="prompt.member" />
																<bean:message key="prompt.info" />
															</td>
															<td class="detailHead">
																<bean:message key="prompt.juvenileNumber" />
																<html:text name="juvenileMemberForm" property="searchJuvenileNumber" size="10" maxlength="8" />
																<html:submit property="submitAction" styleId="valJuvNumGo">
																	<bean:message key="button.go"></bean:message>
			  													</html:submit> 
															</td>
														</logic:equal>
														<logic:notEqual name="juvenileMemberForm" property="action" value="createMember">
															<td class="detailHead">
																<bean:message key="prompt.family" />
																<bean:message key="prompt.member" />
																<bean:message key="prompt.info" />
															</td>
														</logic:notEqual>	
													</tr>
												</table>			
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" valign="top"><bean:message key="prompt.name" /></td>
											<td class="formDe" colspan="3">
 												<%-- BEGIN FAMILY MEMBER INNER TABLE  --%>
 												<table border="0" cellspacing="1">
													<tr>
														<td class="formDeLabel" colspan="2"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.last" /></td>
													</tr>
													<tr>		
														<td class="formDe" colspan='2'>
															<html:text name="juvenileMemberForm" property="name.lastName" size="30" maxlength="75" />
														</td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.first" /></td>
														<td class="formDeLabel"><bean:message key="prompt.middle" /></td>
													</tr>
													<tr>		
														<td class="formDe"><html:text name="juvenileMemberForm" property="name.firstName" size="25" maxlength="50"/></td>
														<td class="formDe"><html:text name="juvenileMemberForm" property="name.middleName" size="25" maxlength="50"/></td>
														<td class="formDe" width="42%"> </td>
													
													</tr>
												</table>
											<%-- END FAMILY MEMBER INNER TABLE  --%>
											</td>
										</tr>
												<td class="formDeLabel" align="left" nowrap><bean:message key="prompt.over21" /></td> 
												<td class="formDe" colspan='4' nowrap>
													Yes <html:radio name="juvenileMemberForm" property="over21" value="true" styleId="over21Yes" /> 
													No <html:radio name="juvenileMemberForm" property="over21" value="false" styleId="over21No" />
												</td>
										<tr>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.socialSecurity#" /></td>
											<td class="formDe">
											
												<logic:equal name="juvenileMemberForm" property="action" value="createMember">
												   <html:text name="juvenileMemberForm" property="ssn.SSN1" size="3" maxlength="3" onkeyup="return autoTab(this, 3);"  styleId="ssn1" /> 
													<html:text name="juvenileMemberForm" property="ssn.SSN2" size="2" maxlength="2" onkeyup="return autoTab(this, 2);" styleId="ssn2" /> 
													<html:text name="juvenileMemberForm" property="ssn.SSN3" maxlength="4" size="4" onkeyup="return autoTab(this, 4);" styleId="ssn3" />
												</logic:equal>
												<jims2:if name="juvenileMemberForm" property="action" value="updateMember" op="equal">
													<jims2:or name="juvenileMemberForm" property="action" value="confirm" op="equal"/>
													<jims2:or name="juvenileMemberForm" property="action" value="findCreateMember" op="equal"/>
													<jims2:or name="juvenileMemberForm" property="action" value="findJuvenileInfo" op="equal"/> <!-- 88726 -->
												      <jims2:then>
														<html:text name="juvenileMemberForm" property="ssn.SSN1" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" disabled="true"/> 
														<html:text name="juvenileMemberForm" property="ssn.SSN2" size="2" maxlength="2" onkeyup="return autoTab(this, 2);" disabled="true"/> 
														<html:text name="juvenileMemberForm" property="ssn.SSN3" maxlength="4" size="4" onkeyup="return autoTab(this, 4);" disabled="true"/>
														<!-- 88726 -->
														&nbsp;<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_SSN_U%>'><html:submit property="submitAction" styleId="ssnUpdateSubmit"><bean:message key="button.update" /></html:submit></jims2:isAllowed>
													   </jims2:then>
												</jims2:if>
											</td>
											<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.dateOfBirth" /></td>
											<td class="formDe">
												<html:text styleId="dateOfBirth" name="juvenileMemberForm" property="dateOfBirth" maxlength="10" size="10" />
											</td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.sex" /></td>
											<td class="formDe" colspan="3">
												<html:select name="juvenileMemberForm" property="sexId" size="1">
   													<option value="">Please Select</option>
   													<html:optionsCollection name="juvenileMemberForm" property="sexList" value="code" label="description" />
 												</html:select>
											</td>
										</tr>										
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.usCitizen" /></td>
											<td class="formDe" colspan="3">
												<html:select size="1" name="juvenileMemberForm" property="isUSCitizenId">
													<option value="">Please Select</option>
 													<html:optionsCollection name="juvenileMemberForm" property="isUSCitizenList" value="code" label="description" />
 												</html:select>
											</td>
										</tr>
										<tr>
											<td class="formDeLabel"><bean:message key="prompt.nationality" /></td>		
											<td class="formDe" colspan="3">
												<html:select size="1" name="juvenileMemberForm" property="nationalityId">
													<option value="">Please Select</option>
													<html:optionsCollection name="juvenileMemberForm" property="nationalityList" value="code" label="description" />
												</html:select>
											</td>
										</tr>
										<tr>		
											<td class="formDeLabel"><bean:message key="prompt.ethnicity" /></td>
											<td class="formDe" colspan="3">
												<html:select size="1" name="juvenileMemberForm" property="ethnicityId">
													<option value="">Please Select</option>
 													<html:optionsCollection name="juvenileMemberForm" property="ethnicityList" value="code" label="description" />
 												</html:select>
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" ><bean:message key="prompt.primaryLanguage" /></td>
											<td class="formDe" colspan="3">
												<html:select size="1" name="juvenileMemberForm" property="primaryLanguageId">
													<option value="">Please Select</option>
													<html:optionsCollection name="juvenileMemberForm" property="languageList" value="code" label="description" />
												</html:select>
											</td>
										</tr><tr>	
											<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.secondaryLanguage" /></td>
											<td class="formDe" colspan="3">
												<html:select name="juvenileMemberForm" size="1" property="secondaryLanguageId">
													<option value="">Please Select</option>
													<html:optionsCollection name="juvenileMemberForm" property="languageList" value="code" label="description" />
												</html:select>
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%"><bean:message key="prompt.isFamilyMemberDeceased" /></td>
											<td class="formDe" colspan='3'>
												Yes <html:radio name="juvenileMemberForm" property="deceased" value="true" styleId="deceasedYes" /> 
												No <html:radio name="juvenileMemberForm" property="deceased" value="false" styleId="deceasedNo" />
												<html:hidden name="juvenileMemberForm" property="deceasedValue" styleId="deceasedValue"/>
											</td>
										</tr>
									  	<tr>
											<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.causeOfDeath" /></td>
											<td class="formDe" colspan='3'>
												<html:select property="causeofDeathId">
													<option value="">Please Select</option>
													<html:optionsCollection name="juvenileMemberForm" property="causeOfDeathList" value="code" label="description" />
												</html:select>
											</td>	
										</tr>
										<tr id="juvAge">
											<td class="formDeLabel"><bean:message key="prompt.juvenileAgeAtFamilyMemberDeath" /></td>
											<td class="formDe" colspan="3"><html:text name="juvenileMemberForm" property="juvenileAgeAtDeath" size="2" maxlength="2"/></td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%"><bean:message key="prompt.isFamilyMemberIncarcerated" /></td>
											<td class="formDe" colspan='3'>
												Yes <html:radio name="juvenileMemberForm" property="incarcerated" value="true" styleId="incarceratedYes" /> 
												No <html:radio name="juvenileMemberForm" property="incarcerated" value="false" styleId="incarceratedNo"/>
												<html:hidden name="juvenileMemberForm" property="incarceratedValue" styleId="incarceratedValue" />
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" colspan="4" valign="top"><bean:message key="prompt.family" /> <bean:message key="prompt.member" /> <bean:message key="prompt.comments" />&nbsp;
			                					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
			                						<tiles:put name="tTextField" value="familyMemberComments"/>
			                 						<tiles:put name="tSpellCount" value="spellBtn1" />
			              						</tiles:insert> 
			              						(Max. characters allowed: 255)
                       						</td>
										</tr>
										<tr>
											<td class="formDe" colspan="4"><html:textarea name="juvenileMemberForm" property="familyMemberComments"  cols="40" rows="2" style="width:100%" /></td>
										</tr>
									</table>
									<%-- end family member table  --%>
									<div class='spacer'></div>
  									<%-- BEGIN DRIVER LICENSE/ID CARD INFORMATION --%>
									<table width='98%' border="0" cellpadding="4" cellspacing="1" class='borderTableBlue'>
										<tr>
											<td colspan='4' class="detailHead"><bean:message key="prompt.driverLicense"/>/<bean:message key="prompt.idCardInfo" /></td>
										</tr>
										<tr>	
											<td class="formDeLabel"><bean:message key="prompt.number" /></td>
											<td class="formDe"><html:text name="juvenileMemberForm" styleId="driverLicenseNum" property="driverLicenseNum" size="25" maxlength="25" /></td>
											<td class="formDeLabel"><bean:message key="prompt.state" /></td>
											<td class="formDe">
												<html:select name="juvenileMemberForm" property="driverLicenseStateId" size="1">
													<option value="">Please Select</option>
													<html:optionsCollection name="juvenileMemberForm" property="stateList" value="code" label="description" />
												</html:select>
											</td>
										</tr>
										<tr>	
											<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.expirationDate" /></td>
											<td class='formDe'>
												<html:text styleId="dlExperationDate" name="juvenileMemberForm" property="driverLicenseExpirationDate" size="10" maxlength="10" />
											</td>
											<td class='formDeLabel' valign="top" nowrap='nowrap'><bean:message key="prompt.class" /></td>
											<td class='formDe'>
												<html:select property="driverLicenseClassId">
													<html:option value=""><bean:message key="select.generic" /></html:option>
													<html:optionsCollection property="driversLicenseClassList" value="code" label="description" />
												</html:select>
											</td>
										</tr>
										<tr>
											<td class="formDeLabel" nowrap='nowrap' width="1%"><bean:message key="prompt.state" />
                             					<bean:message key="prompt.issued" />
												<bean:message key="prompt.idCard#" />
											</td>
											<td class="formDe"><html:text name="juvenileMemberForm" property="stateIssuedIdNum" size="20" maxlength="20"/></td>
											<td class="formDeLabel"><bean:message key="prompt.state" /> </td>
											<td class="formDe">
												<html:select name="juvenileMemberForm" property="issuedByStateId">
													<option value="">Please Select</option>
													<html:optionsCollection name="juvenileMemberForm" property="stateList" value="code" label="description" />
												</html:select>
											</td>
										</tr>
										<%--adding the passport details --%>
										<tr>
											<td class="formDeLabel" nowrap='nowrap' width="1%"><bean:message key="prompt.passportNumber" /></td>                             					
											<td class="formDe"><html:text name="juvenileMemberForm" property="passportNum" size="20" maxlength="20"/></td>
										    <td class="formDeLabel"><bean:message key="prompt.countryOfIssuance" /></td>		
											<td class="formDe" colspan="3">
												<html:select size="1" name="juvenileMemberForm" property="countryOfIssuanceId">
													<option value="">Please Select</option>
													<html:optionsCollection name="juvenileMemberForm" property="nationalityList" value="code" label="description" />
												</html:select>
											</td>
										</tr>
										<tr>	
											<td class="formDeLabel"><bean:message key="prompt.stateidSID" /></td>
											<td class="formDe"><html:text name="juvenileMemberForm" property="sidNum" size="10" maxlength="10" /></td>
											<td class="formDeLabel"><bean:message key="prompt.alienReg#" /></td>
											<!-- bug fix#22255 -->
											<td class="formDe"><html:text name="juvenileMemberForm" property="alienNum" size="9" maxlength="9"></html:text></td>
										</tr>
										<tr>	
											<td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.expirationDate" /></td>
											<td class='formDe' colspan="3">
												<html:text styleId="passportExpirationDate" name="juvenileMemberForm" property="passportExpirationDate" size="10" maxlength="10" />
											</td>
										</tr>
										<%-- end of passport details --%>
                        			</table>
                        			<%-- end DRIVER LICENSE/ID CARD INFORMATION --%>
								    <div class='spacer'></div>
							  		<%-- BEGIN Marital Status TABLE --%>
							    	<table width='98%' border="0" cellpadding="0" cellspacing="1" class="borderTableBlue">
										<tr>
											<td class='detailHead'><bean:message key="prompt.marital" /> <bean:message key="prompt.status" /></td>
										</tr>
										<tr>
											<td>
												<table width='100%' border="0" cellpadding="2" cellspacing="1">
													<tr bgcolor='#cccccc'>
														<td class="formDeLabel"><bean:message key="prompt.marital" /> <bean:message key="prompt.status" /></td>
														<td class='formDeLabel'><bean:message key="prompt.relationship"/> With</td>
														<td class="formDeLabel"><bean:message key="prompt.marriage" />
															<bean:message key="prompt.date" /></td>
														<td class="formDeLabel"><bean:message key="prompt.divorce" />
															<bean:message key="prompt.date" /></td>
														<td class="formDeLabel"><bean:message key="prompt.#OfChildren" /></td>
													</tr>
													<tr>
														<td class="formDe">
															<html:select name="juvenileMemberForm" property="maritalStatusId">
																<option value="">Please Select</option>
																<html:optionsCollection name="juvenileMemberForm" property="maritalStatusList" value="code" label="description" />
															</html:select>
														</td>
														<td class="formDe">
															<html:select name="juvenileMemberForm" property="relatedFamMemId">
																<option value="">Please Select</option>
																<html:optionsCollection name="juvenileMemberForm" property="maritalRelWithList" value="memberNum" label="fullNameWithRel" />
															</html:select>
														</td>
														<td class="formDe">
															<html:text styleId="marriageDate" name="juvenileMemberForm" property="marriageDate" size="10" maxlength="10"/>
														</td>
														<td class="formDe">
															<html:text styleId="divorceDate" name="juvenileMemberForm" property="divorceDate" size="10" maxlength="10"/>
														</td>
														<td class="formDe"><html:text name="juvenileMemberForm" property="numOfChildren" size="2" maxlength="2" /></td>
													</tr>
												</table>
											</td>
										</tr> 
									</table>
									<div class='spacer'></div>
									<%-- ### begin button table --%>
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit> 
												<jims2:isAllowed requiredFeatures='<%=Features.JCW_PRF_FAM_MI_U%>'>		
													<html:submit styleId="famMembNextValidate" property="submitAction">
														<bean:message key="button.next"></bean:message>
													</html:submit> 
												</jims2:isAllowed>
												<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
											</td>
										</tr>
									</table>
									<div class='spacer'></div>
									<%-- end button table --%>
								</td>
							</tr>
						</table>
		    			<div class='spacer'></div>
						<%-- end outer red --%>
					</td>
				</tr>
			</table>
		<%-- end outer green --%>
		</td>
	</tr>
</table>
<html:hidden name="juvenileMemberForm" property="action" styleId="actionStat"/>
<html:hidden name="juvenileMemberForm" property="guardian" styleId="isGuardian" />
<html:hidden name="juvenileMemberForm" property="juvenileNumber" styleId="juvenileNumber" />
<html:hidden name="juvenileMemberForm" property="over21" styleId="over21" />

<div class='spacer'></div>
<logic:equal name="juvenileMemberSearchForm" property="popUp" value="true">
	<html:hidden name="juvenileMemberSearchForm" property="popUp" styleId="popUp" value="true"/>
</logic:equal>
</html:form>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>