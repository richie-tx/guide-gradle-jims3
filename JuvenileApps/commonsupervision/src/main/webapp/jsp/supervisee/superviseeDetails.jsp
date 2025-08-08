<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 09/27/2007	 Debbie Williamson - Create JSP -->
<!-- 12/08/2008  Dawn Gibler ER JIMS200054966 - Supervisee Entity - design change -->
<!-- 10/16/2009  C Shimek    - 62475 moved js code for popup from common_supervision_util.js to inside this jsp to psossible fix js error on show() call  -->
<!-- 11/05/2009  C Shimek    - 62475 reworked popup to be within Form tag, hopefully this fixes random js error for object not found -->
<!-- 12/14/2009  RYoung      ER 62850 - Revise code to access the Supervisee Photo-CSCD -->
<!-- 12/31/2009  C Shimek    - 63105 revised coding around LOS Add/View History to not display divider if only View History is active -->
<!-- 02/09/2010  C Shimek    - 63105 removed easter egg coding around LOS only -->
<!-- 02/18/2010  C Shimek    - 62851 added random number to photo to possibly correct caching issue -->
<!-- 11/09/2010  D Gibler    - 68180 Add Program Tracker to Supervisee Profile -->
<!-- 08/10/2012  D Williamson - 61585 Added DNA section  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>  
<%-- <%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %> --%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ page import="naming.UIConstants" %>
<%@ page import="naming.Features" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - supervisee/superviseeDetails.jsp</title>

<!-- Javascript -->
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<!-- FUNCTIONS FOR FILTER CONDITIONS GROUPS  -->
<script type="text/javascript">
</script>  
</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/handleSuperviseeElection" target="content" >

<!-- Begin Pagination Header Tag-->
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
<pg:pager
    index="center"
    maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10"
    export="offset,currentPageNumber=pageNumber"
    scope="request">
<!-- End Pagination header stuff -->

<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Supervisee_Search/Supervisee_Search.htm#|4">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->		
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top">
						<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
					   		<tiles:put name="tab" value="caseloadTab"/> 
				     	</tiles:insert>					
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td> 
				</tr>
			</table>
<!-- END BLUE TABS TABLE -->  
<!-- BEGIN BLUE BORDER TABLE -->	
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
<!-- BEGIN SUPERVISEE INFORMATION TABLE  -->
						<tiles:insert page="../common/superviseeHeader.jsp" flush="true"></tiles:insert>	
<!-- END SUPERVISEE INFORMATION TABLE  -->	
					</td>
				</tr>	
<!-- BEGIN GREEN TABS TABLE -->		
				<tr>
					<td valign="top" align="center"> 
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>						
							<tr>
								<td valign="top">
									<tiles:insert page="../common/caseloadCSCDSubTabs.jsp" flush="true">
					   				 	<tiles:put name="tab" value="SuperviseeTab"/> 
						     		</tiles:insert>					
								</td>
							</tr>
							<tr>
								<td  bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td> 
							</tr>
						</table>
					</td>
				</tr>		
<!-- END GREEN TABS TABLE -->				
				<tr>
					<td valign="top" align="center">
<!-- BEGIN GREEN BORDER TABLE -->					
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header">
												<bean:message key="title.CSCD" />&nbsp;-&nbsp;<bean:message key="prompt.supervisee" />
												<bean:message key="prompt.details" />
											</td>
										</tr>
									</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
									<table width="98%" align="center">							
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>		
									</table>
<!-- END ERROR TABLE -->
									<table width="98%" border="0" cellpadding="0" cellspacing="0">
										<logic:present name="confirmMessage">
											<tr id="confirmations">
												<td class="confirm">
													<bean:write name="confirmMessage" />
												</td>
											</tr>
										</logic:present>
									</table>		
							
<!-- BEGIN CONFIRMATION TABLE -->
							 
<!-- END CONFIRMATION TABLE --> 
                                    <table width="98%" border="0" cellpadding="4" cellspacing="1" align="center">
<!-- BEGIN PARTY NAME INFORMATION TABLE -->
										<tr>
											<td class="detailHead" colspan="4"><bean:message key="prompt.name" />&nbsp;<bean:message key="prompt.information" /></td>
										</tr>
										<tr>
											<td class="formDeLabel" width="1%"><bean:message key="prompt.name" /></td>
											<td class="formDe" colspan="2"> <!-- change colspan back to 2 when photo is added -->
												<bean:write name="superviseeForm" property="superviseeName"/>
												<!--<msp:formatter name="superviseeForm" property="superviseeName" format="L,F"/>-->
												<div>
												<html:submit property="submitAction"><bean:message key="button.viewActiveCases"/></html:submit>
                                                </div>
                                            </td>     
                                       		<td rowspan="2" class="formDe" valign="top" align="center">
                                       			<logic:equal name="superviseeHeaderForm" property="hasPhoto" value="true">
                                          			<a href="javascript:openWindow('/CommonSupervision/jsp/supervisee/superviseePhoto.jsp')">
                                           				<img title="Supervisee Photo" src="/<msp:webapp/>getSuperviseePhoto.do?submitAction=Photo Detail&rrand=<%out.print((Math.random()*246));%>" height="125" border="1" />                                            			
                                           			</a>
											    	<div>
											    		<bean:write name="superviseeHeaderForm" property="superviseePhotoCreateDate" />
											    	</div>
										    	</logic:equal>
                                       			<logic:equal name="superviseeHeaderForm" property="hasPhoto" value="false">	                                            			
                                           			<img alt="Mug Shot Not Available" title="Supervisee Photo" src="/<msp:webapp/>images/photo_na_cscd.gif" height="125" border="1" />                                            				                                            			
										    	</logic:equal>												    	
									    	</td>
                                        </tr>
										<tr>
											<td class="formDeLabel" nowrap>
												<bean:message key="prompt.nameSource" />
											</td>
											<td class="formDe" colspan="2"><!-- change colspan back to 3 when photo is added -->
												<bean:write name="superviseeForm" property="superviseeNameSource" />
											</td>
										</tr>
<!-- END PARTY NAME INFORMATION TABLE -->
										<tr><td><div style="font-size:1pt">&nbsp;</div></td></tr>
<!-- BEGIN DESCRIPTIIVE INFORMATION TABLE -->
										<tr>
											<td class="detailHead" colspan="4">
												<table width="100%" cellpadding="2" cellspacing="0">
													<tr>
														<td width="1%"><a href="javascript:showHideMulti('descInfo', 'descrRow', 12, '/<msp:webapp/>')"><img border=0 src="../../images/expand.gif" name="descInfo"></a></td>
														<td class="detailHead"><bean:message key="prompt.descriptive" />&nbsp;<bean:message key="prompt.information" /></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr id="descrRow0" class="hidden">
											<td class="formDeLabel"><bean:message key="prompt.dateOfBirth" /></td>
											<td class="formDe"><bean:write name="superviseeForm" property="dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
											<td class="formDeLabel"><bean:message key="prompt.build" /></td>
											<td class="formDe"><bean:write name="superviseeForm" property="build"/></td>
										</tr>
										<tr id="descrRow1" class="hidden">
											<td class="formDeLabel"><bean:message key="prompt.race" /></td>
											<td class="formDe"><bean:write name="superviseeForm" property="race"/>
												<td class="formDeLabel"><bean:message key="prompt.sex" /></td>
												<td class="formDe"><bean:write name="superviseeForm" property="sex"/></td>
											</tr>
											<tr id="descrRow2" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.height" /></td>
												
												<td class="formDe"><bean:write name="superviseeForm" property="height"/>
												<!--  
												<td class=formDe><bean:write name="superviseeForm" property="heightFeet"/>ft
															     <bean:write name="superviseeForm" property="heightInch"/>in</td>
												-->
												<td class="formDeLabel"><bean:message key="prompt.weight" /></td>
												<td class="formDe"><bean:write name="superviseeForm" property="weight"/></td>
											</tr>
											<tr id="descrRow3" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.complexion" /></td>
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="complexion"/></td>
											</tr>
											<tr id="descrRow4" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.eyeColor" /></td>
												<td class="formDe"><bean:write name="superviseeForm" property="eyeColor"/></td>
												<td class="formDeLabel"><bean:message key="prompt.hairColor" /></td>
												<td class="formDe"><bean:write name="superviseeForm" property="hairColor"/></td>
											</tr>
											<tr id="descrRow5" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.scars/Marks" /></td>
												<td class="formDe" colspan="3">
													<logic:notEmpty name="superviseeForm" property="scarsMarksDescription">
														<logic:iterate id="scars" name="superviseeForm" property="scarsMarksDescription">
                											<bean:write name="scars" property="description" /><br>
										           		</logic:iterate>
										        	</logic:notEmpty>
												</td>
											</tr>
											<tr id="descrRow6" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.tattoo(s)" /></td>
												<td class="formDe" colspan="3">
													<logic:notEmpty name="superviseeForm" property="tattoosDescription">
														<logic:iterate id="tattoos" name="superviseeForm" property="tattoosDescription">
        	        										<bean:write name="tattoos" property="description" /><br>
											           	</logic:iterate>
										           	</logic:notEmpty>
												</td>
											</tr>
											<tr id="descrRow7" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.usCitizen" /></td>
												<td class="formDe"><bean:write name="superviseeForm" property="usCitizenshipInd"/></td>
												<td class="formDeLabel" width="1%"><bean:message key="prompt.fingerPrinted" /></td>
												<td class="formDe"><bean:write name="superviseeForm" property="fingerprintedInd"/></td>
											</tr>
											<tr id="descrRow8" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.ethnicity" /></td>
												<td class="formDe"><bean:write name="superviseeForm" property="ethnicity"/></td>
												<td class="formDeLabel"><bean:message key="prompt.maritalStatus" /></td>
												<td class="formDe"><bean:write name="superviseeForm" property="maritalStatus"/></td>
											</tr>
											<tr id="descrRow9" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.placeOfBirth" /></td>
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="placeOfBirth"/></td>
											</tr>
											<tr id="descrRow10" class="hidden">
												<td class="formDeLabel" nowrap><bean:message key="prompt.placeOfBirthStateCountry" /></td>
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="stateCountryOfBirth"/></td>
											</tr>
											<tr id="descrRow11" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.descriptionSource" /></td>
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="descriptionSource"/></td>
											</tr>
<!-- END DESCRIPTIVE INFORMATION TABLE -->
											<tr><td><div style="font-size:1pt">&nbsp;</div></td></tr>
<!-- BEGIN ADDRESS INFORMATION TABLES -->
											<tr>
												<td class="detailHead" colspan="4">
													<table width="100%" cellpadding="2" cellspacing="0">
														<tr>
															<td width="1%"><a href="javascript:showHideMulti('addressInfo', 'addressRow', 8, '/<msp:webapp/>')"><img border=0 src="../../images/expand.gif" name="addressInfo"></a></td>
															<td class="detailHead"><bean:message key="prompt.addressInfo" /></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr id="addressRow0" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.streetNumber" /></td>
												<td class="formDeLabel" nowrap colspan="3"><bean:message key="prompt.streetName" /></td>
											</tr>
											<tr id="addressRow1" class="hidden">
												<td class="formDe"><bean:write name="superviseeForm" property="superviseeStreetNumber"/></td>
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="superviseeStreetName"/></td>
											</tr>
											<tr id="addressRow2" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.streetType" /></td>
												<td class="formDeLabel" nowrap colspan=3><bean:message key="prompt.aptSuite" /></td>
											</tr>
											<tr id="addressRow3" class="hidden">
												<td class="formDe">
													<bean:write name="superviseeForm" property="superviseeStreetType"/>
												</td>
												<td class="formDe" colspan="3">
													<bean:write name="superviseeForm" property="superviseeApartmentNumber"/>
												</td>
											</tr>
											<tr id="addressRow4" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.city" /></td>
												<td class="formDeLabel"><bean:message key="prompt.state" /></td>
												<td class="formDeLabel" colspan="2"><bean:message key="prompt.zipCode" /></td>
											</tr>
											<tr id="addressRow5" class="hidden">
												<td class="formDe"><bean:write name="superviseeForm" property="superviseeCity"/></td>
												<td class="formDe"><bean:write name="superviseeForm" property="superviseeState"/></td>
												<td class="formDe" colspan="2"><bean:write name="superviseeForm" property="superviseeZipCode"/></td>
											</tr>
											<tr id="addressRow6" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.addressType" /></td>
												<td class="formDeLabel" colspan="3"><bean:message key="prompt.phoneNo" /></td>
											</tr>
											<tr id="addressRow7" class="hidden">
												<td class="formDe" ><bean:write name="superviseeForm" property="superviseeAddressType"/></td>
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="superviseePhoneNumber" formatKey="phone.format"/></td>
											</tr>
<!-- END ADDRESS INFORMATION TABLE -->
											<tr><td><div style="font-size:1pt">&nbsp;</div></td></tr>
<!-- BEGIN ID NUMBER INFORMATION TABLES -->
											<tr>
												<td class="detailHead" colspan="4">
													<table width="100%" cellpadding="2" cellspacing="0">
														<tr>
															<td width="1%"><a href="javascript:showHideMulti('idInfo', 'idRow', 4, '/<msp:webapp/>')"><img border=0 src="../../images/expand.gif" name="idInfo"></a></td>
															<td class="detailHead"><bean:message key="prompt.identification" />&nbsp;<bean:message key="prompt.numbers" />&nbsp;<bean:message key="prompt.information" /></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr id="idRow0" class="hidden">
												<td class="formDeLabel" nowrap><bean:message key="prompt.socialSecurityNumber" /></td>
												<td class="formDe" colspan="3"><msp:formatter name="superviseeForm" property="ssn" format="A-B-C" /></td>
											</tr>
											<tr id="idRow2" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.dlNumberState" /></td>
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="driverLicenseNumber"/>&nbsp;/&nbsp;<bean:write name="superviseeForm" property="driverLicenseState"/></td>
											</tr>
											<tr id="idRow1" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.stateIdNumber" />(<bean:message key="prompt.SID" />)</td>
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="stateIdNumber"/></td>
											</tr>
											<tr id="idRow3" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.fbiNumber" /></td>
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="fbiNumber"/></td>
											</tr>
<!-- END ID NUMBER INFORMATION TABLES -->
                                           <tr><td><div style="font-size:1pt">&nbsp;</div></td></tr>
<!-- BEGIN EMPLOYER INFORMATION TABLES -->
											<tr>
												<td class="detailHead" colspan="4">
													<table width="100%" cellpadding="2" cellspacing="0">
														<tr>
															<td width=1%><a href="javascript:showHideMulti('empInfo', 'empRow', 8, '/<msp:webapp/>')"><img border=0 src="../../images/expand.gif" name="empInfo"></a></td>
															<td class="detailHead"><bean:message key="prompt.employer" />&nbsp;<bean:message key="prompt.information" /></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr id="empRow0" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.employer" /></td>
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="employerName"/></td>
											</tr>
											<tr id="empRow1" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.occupation" /></td>
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="employerOccupation"/></td>
											</tr>
											<tr id="empRow2" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.workPhone" /></td>
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="employePhoneNum" formatKey="phone.format"/></td>
											</tr>
											<tr id="empRow3" class="hidden">
												<td class="formDeLabel" nowrap><bean:message key="prompt.employmentStatus" /></td>
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="employerEmploymentStatus"/></td>
											</tr>
											<tr id="empRow4" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.streetNumber" /></td>
												<td class="formDeLabel"><bean:message key="prompt.streetName" /></td>
												<td class="formDeLabel"><bean:message key="prompt.streetType" /></td>
												<td class="formDeLabel" nowrap><bean:message key="prompt.aptSuite" /></td>
											</tr>
											<tr id="empRow5" class="hidden">
												<td class="formDe"><bean:write name="superviseeForm" property="employerStreetNum"/></td>
												<td class="formDe"><bean:write name="superviseeForm" property="employerStreetName"/></td>
												<td class="formDe"><bean:write name="superviseeForm" property="employerStreetType"/></td>
												<td class="formDe"><bean:write name="superviseeForm" property="employerAptNum"/></td>
											</tr>
											<tr id="empRow6" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.city" /></td>
												<td class="formDeLabel"><bean:message key="prompt.state" /></td>
												<td class="formDeLabel" colspan="2"><bean:message key="prompt.zipCode" /></td>
											</tr>
											<tr id="empRow7" class="hidden">
												<td class="formDe"><bean:write name="superviseeForm" property="employerCity"/></td>
												<td class="formDe"><bean:write name="superviseeForm" property="employerState"/></td>
												<td class="formDe" colspan="2"><bean:write name="superviseeForm" property="employerZipCode"/>
                                                    <logic:notEqual name="superviseeForm" property="employerAdditionalZipCode" value="">-
                                                         <bean:write name="superviseeForm" property="employerAdditionalZipCode"/> 
                                                    </logic:notEqual>
                                                </td>
											</tr>
<!-- END EMPLOYER INFORMATION TABLE -->
											<tr><td><div style="font-size:1pt">&nbsp;</div></td></tr>
<!--Education Start cs release 1 -->
											<tr>
												<td class="detailHead" colspan="4">
													<table width="100%" cellpadding="2" cellspacing="0">
														<tr>
															<td width="1%"><a href="javascript:showHideMulti('eduInfo', 'eduRow', 10, '/<msp:webapp/>')"><img border="0" src="../../images/expand.gif" name="eduInfo"></a></td>
															<td class="detailHead"><bean:message key="prompt.education" />&nbsp;<bean:message key="prompt.information" /></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr id="eduRow0" class="hidden">
												<td colspan="4" class="formDeLabel"><bean:message key="prompt.selfReported" /></td>
											</tr>
											<tr id="eduRow8" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.dateOfIntakeAssessment" /></td>
												<td class="formDe"><bean:write name="superviseeForm" property="intakeDate" formatKey="date.format.mmddyyyy"/></td>
												<td class="formDeLabel"><bean:message key="prompt.highestGradeCompleted" /></td>
												<td class="formDe"><bean:write name="superviseeForm" property="highestGradeCompleted"/></td>
											</tr>
											<tr id="eduRow1" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.highSchoolDiploma" /></td>
												<td class="formDe"><bean:write name="superviseeForm" property="highSchoolDiploma"/></td>
												<td class="formDeLabel"><bean:message key="prompt.GED" /></td>
												<td class="formDe"><bean:write name="superviseeForm" property="GED"/></td>
											</tr>
											<tr id="eduRow2" class="hidden">
												<td class="formDeLabel" nowrap><bean:message key="prompt.advancedDegreeEarned" /></td>
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="advancedDegreeEarned"/></td>
											</tr>
											<tr id="eduRow3" class="hidden">
												<td colspan="4" class="formDeLabel"><bean:message key="prompt.assessment" /></td>
											</tr>
											<tr id="eduRow4" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.GEDVerified" /></td>
												<td class="formDe"><bean:write name="superviseeForm" property="GEDVerified"/></td>
												<td class="formDeLabel"><bean:message key="prompt.GEDAttainedDate" /></td>
												<td class="formDe"><bean:write name="superviseeForm" property="GEDAttainedDate" formatKey="date.format.mmddyyyy"/></td>
											</tr>
											<tr id="eduRow5" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.hsDiplomaVerified" /></td>
												<td class="formDe"><bean:write name="superviseeForm" property="hsDiplomaVerified"/></td>
												<td class="formDeLabel" width="1%"><bean:message key="prompt.hsDiplomaAttainedDate" /></td>
												<td class="formDe"><bean:write name="superviseeForm" property="hsDiplomaAttainedDate" formatKey="date.format.mmddyyyy"/></td>
											</tr>
											<tr id="eduRow6" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.assessmentDate" /></td>
												<td class="formDe"><bean:write name="superviseeForm" property="assessmentDate" formatKey="date.format.mmddyyyy"/></td>
												<td class="formDeLabel"><bean:message key="prompt.method" /></td>
												<td class="formDe"><bean:write name="superviseeForm" property="assessmentMethod"/></td>
											</tr>
											<tr id="eduRow7" class="hidden">
												<td class="formDeLabel" nowrap><bean:message key="prompt.assessmentLevel" /></td>
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="assessmentLevel"/></td>
											</tr>
											<tr id="eduRow9" class="hidden">
												<td class="formDeLabel" nowrap><bean:message key="prompt.reportedLevel" /></td>
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="reportedLevel"/></td>
											</tr> 
											<tr><td><div style="font-size:1pt">&nbsp;</div></td></tr>
<!--Education End cs release 1 -->
<!--LOS Start-->

											<tr>
												<td class="detailHead" colspan="4">
													<table width="100%" cellpadding="2" cellspacing="0">
														<tr>
															<td width="1%"><a href="javascript:showHideMulti('losInfo', 'losRow', 8, '/<msp:webapp/>')"><img border=0 src="../../images/expand.gif" name="losInfo"></a></td>
															<td class="detailHead"><bean:message key="prompt.LOS" />&nbsp;<bean:message key="prompt.information" /></td>       
															<logic:equal name="superviseeHeaderForm" property="superviseeFound" value="true">
																<td align="right"> 
														<%-- 		<span id="losLinks" class="hidden">  --%>
																	<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPERVISEE_LOS_ADD%>'>
																		<a href="/<msp:webapp/>handleSuperviseeElection.do?submitAction=<bean:message key="prompt.add"/>&superviseeId=<bean:write name='superviseeHeaderForm' property='superviseeId'/>&action=<%=UIConstants.CREATE%>" ><bean:message key="prompt.add" /></a>
																	</jims2:isAllowed>
																	<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPERVISEE_LOS_VIEW_HISTORY%>'>
																		<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPERVISEE_LOS_ADD%>'>|</jims2:isAllowed>
																		<a href="/<msp:webapp/>handleSuperviseeElection.do?submitAction=<bean:message key="prompt.viewHistory"/>&superviseeId=<bean:write name='superviseeHeaderForm' property='superviseeId'/>" ><bean:message key="prompt.viewHistory" /></a>
																	</jims2:isAllowed>
														<%--		</span>  --%>
																</td>
															</logic:equal>
														</tr>
													</table>
												</td>
											</tr>
											
											<tr id="losRow0" class="hidden">
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.effectiveDate" /></td> 
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="effectiveDate"/></td>
											</tr>
											<tr id="losRow1" class="hidden">
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.supervisionLevel" /></td>
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="supervisionLevelDesc"/>
												</td>
											</tr>
											<tr id="losRow2" class="hidden">
												<td class="formDeLabel"><bean:message key="prompt.comments" /></td>
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="losComments"/></td>
											</tr>
											<tr><td><div style="font-size:1pt">&nbsp;</div></td></tr>
<!--LOS End-->
<!-- DNA Start -->
                                           <tr>
												<td class="detailHead" colspan="4">
													<table width="100%" cellpadding="2" cellspacing="0">
														<tr>
															<td width="1%"><a href="javascript:showHideMulti('dnaInfo', 'dnaRow', 8, '/<msp:webapp/>')"><img border=0 src="../../images/expand.gif" name="dnaInfo"></a></td>
															<td class="detailHead">DNA&nbsp;<bean:message key="prompt.information" /></td>       
															 <logic:equal name="superviseeHeaderForm" property="superviseeFound" value="true">
																<td align="right">
																	<logic:empty name="superviseeForm" property="dnaCollectedDate">
																		<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPERVISEE_DNA_ADD%>'>
																			<a href="/<msp:webapp/>handleSuperviseeElection.do?submitAction=<bean:message key="prompt.addDNA"/>&superviseeId=<bean:write name='superviseeForm' property='superviseeId'/>&action=<%=UIConstants.CREATE%>" ><bean:message key="prompt.add" /></a>
																		</jims2:isAllowed>	
																	</logic:empty>															
																	<logic:notEmpty name="superviseeForm" property="dnaCollectedDate">
																		<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPERVISEE_DNA_VIEW%>'>
																			<a href="/<msp:webapp/>handleSuperviseeElection.do?submitAction=<bean:message key="prompt.viewDna"/>&superviseeId=<bean:write name='superviseeForm' property='superviseeId'/>&secondaryAction=<%=UIConstants.EMPTY_STRING%>" ><bean:message key="prompt.view" /> </a>																		
																		</jims2:isAllowed>
																	</logic:notEmpty>
																</td>
															</logic:equal>
														</tr>
													</table>
												</td>
											</tr>									
											<tr id="dnaRow0" class="hidden">
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.dnaCollectedDate" /></td>
											    <td class="formDe" colspan="3"><bean:write name="superviseeForm" property="dnaCollectedDate"/></td>
											</tr>
                                            <tr><td><div style="font-size:1pt">&nbsp;</div></td></tr> 
<!-- DNA End -->
<!--Program Tracker Start-->
											<tr>
												<td class="detailHead" colspan="4">
													<table width="100%" cellpadding="2" cellspacing="0">
														<tr>
															<td width="1%"><a href="javascript:showHideMulti('progTrackerInfo', 'progTrackerRow', 8, '/<msp:webapp/>')"><img border=0 src="../../images/expand.gif" name="progTrackerInfo"></a></td>
															<td class="detailHead"><bean:message key="prompt.program" />&nbsp;<bean:message key="prompt.tracker" />&nbsp;<bean:message key="prompt.information" /></td>       
															<logic:equal name="superviseeHeaderForm" property="superviseeFound" value="true">
																<td align="right"> 
																	<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPERVISEE_PROGRAMTRACKER_ADD%>'>
																		<a href="/<msp:webapp/>handleSuperviseeElection.do?submitAction=<bean:message key="prompt.addProgramTracker"/>&superviseeId=<bean:write name='superviseeHeaderForm' property='superviseeId'/>&action=<%=UIConstants.CREATE%>" ><bean:message key="prompt.add" /></a>
																	</jims2:isAllowed>
																	<logic:equal name="superviseeForm" property="programTrackerEndDate" value="">
																		<logic:notEqual name="superviseeForm" property="programTrackerId" value="">
																			<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPERVISEE_PROGRAMTRACKER_REMOVE%>'> 
																				<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPERVISEE_PROGRAMTRACKER_ADD%>'> | 
																					<a href="/<msp:webapp/>handleSuperviseeElection.do?submitAction=<bean:message key="prompt.removeProgramTracker"/>&superviseeId=<bean:write name='superviseeHeaderForm' property='superviseeId'/>&action=<%=UIConstants.REMOVE%>" ><bean:message key="prompt.remove" /></a>
																				</jims2:isAllowed>
																			</jims2:isAllowed>
																		</logic:notEqual>
																	</logic:equal>
																	<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPERVISEE_PROGRAMTRACKER_VIEW_HISTORY%>'>
																		<jims2:isAllowed requiredFeatures='<%=Features.CSCD_SUPERVISEE_PROGRAMTRACKER_ADD%>'>
																			<logic:notEqual name="superviseeForm" property="programTrackerId" value=""> | 
																				<a href="/<msp:webapp/>handleSuperviseeElection.do?submitAction=<bean:message key="prompt.viewProgramTrackerHistory"/>&superviseeId=<bean:write name='superviseeHeaderForm' property='superviseeId'/>&secondaryAction=<%=UIConstants.EMPTY_STRING%>" ><bean:message key="prompt.view" /> <bean:message key="prompt.history" /></a>
																			</logic:notEqual>
																		</jims2:isAllowed>
																	</jims2:isAllowed>
																</td>
															</logic:equal>
														</tr>
													</table>
												</td>
											</tr>
											<tr id="progTrackerRow0" class="hidden">
												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.effectiveDate" /></td> 
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="programTrackerEffectiveDate"/></td>
											</tr>
											<tr id="progTrackerRow1" class="hidden">
												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.endDate" /></td> 
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="programTrackerEndDate"/></td>
											</tr>
											<tr id="progTrackerRow2" class="hidden">
												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.program" /> <bean:message key="prompt.tracker" /> <bean:message key="prompt.name" /></td>
												<td class="formDe" colspan="3"><bean:write name="superviseeForm" property="programTrackerDesc"/></td>
											</tr>
											<tr><td><div style="font-size:1pt">&nbsp;</div></td></tr>
									</table>
<!--Program Tracker End-->
<!--Transfer Information Start-->
									<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr>
											<td class="detailHead" colspan="4"> 
												<table width="100%" cellpadding="2" cellspacing="0">
													<tr>
														<td width="1%"><a href="javascript:showHide('transfer', 'row','/<msp:webapp/>')" border=0><img border=0 src="/<msp:webapp/>images/expand.gif" name="transfer"></a></td>
														<td class="detailHead"><bean:message key="prompt.transfers" /></td>
															<td align="right">
													<%-- 		<span id="transfersLinks" class="hidden"> --%>
																<a href="/<msp:webapp/>viewTransferCaseHistoryAction.do?submitAction=Setup&superviseeId=<bean:write name="superviseeForm" property="superviseeId"/>">
																	<bean:message key="prompt.viewHistory" /> 
																</a>
													<%-- 		</span> --%>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr id="transferSpan" class="hidden">
											<td align="center" colspan="2">
												<table width="100%" cellpadding="2" cellspacing="1">
													<tr class="formDeLabel">
														<td width="25%" colspan="3"><bean:message key="prompt.harrisCountyCases" /></td>
														<td class="formDeLabel" colspan="2" align="center"><bean:message key="prompt.out" /></td>
													</tr>
													<tr class="detailHead">
														<td><bean:message key="prompt.CDI" /></td>
														<td><bean:message key="prompt.case#" /></td>
														<td><bean:message key="prompt.CRT" /></td>
														<td><bean:message key="prompt.transferOutDate" /></td>
														<td><bean:message key="prompt.otherCountyState" /></td>
													</tr>
                         							<logic:notEmpty name="superviseeForm" property="harrisCountyCases">	
														<logic:iterate id="superviseeIndex" name="superviseeForm" property="harrisCountyCases" indexId="index1">
													 		<pg:item>
																<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
																	<td><bean:write name="superviseeIndex" property="cdi"/></td>
																	<td nowrap><%--a href="javascript:openWindow('../../processSupOrderCSCD/processSuggestedOrderView.htm?viewVersionViewOnly')"--%><bean:write name="superviseeIndex" property="caseNum"/><%--/a--%></td>
																	<td><bean:write name="superviseeIndex" property="courtNum"/></td>
																	<td><bean:write name="superviseeIndex" property="hcTransferOutDate" /></td>
																	<td><bean:write name="superviseeIndex" property="receivingCountyStateName"/></td>
																</tr>
												     		</pg:item>
	        				                        	</logic:iterate>
        			                        		</logic:notEmpty>
												</table>
												<div class="spacer"></div>
												<table width="100%" cellpadding="2" cellspacing="1">
													<tr class="formDeLabel">
														<td width="25%" colspan="3"><bean:message key="prompt.courtesyCases" /></td>
														<td class="formDeLabel" colspan="4" align="center"><bean:message key="prompt.in" /></td>
													</tr>
													<tr class="detailHead">
														<td><bean:message key="prompt.CDI" /></td>
															<td><bean:message key="prompt.case#" /></td>
															<td><bean:message key="prompt.CRT" /></td>
															<td><bean:message key="prompt.transferInDate" /></td>
															<td><bean:message key="prompt.otherCountyState" /></td>
															<td><bean:message key="prompt.otherCountyStatePID" /></td>
															<!-- <td><bean:message key="prompt.closureReason" /></td>  -->
													</tr>
													<logic:notEmpty name="superviseeForm" property="courtesyCases">	
														<logic:iterate id="superviseeIndex" name="superviseeForm" property="courtesyCases" indexId="index2">
												 			<pg:item>
																<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">               
																	<td><bean:write name="superviseeIndex" property="cdi"/></td>
																	<td nowrap><%--a href="javascript:openWindow('../../processSupOrderCSCD/processSuggestedOrderView.htm?viewVersionViewOnly')"--%><bean:write name="superviseeIndex" property="caseNum"/><%--/a--%></td>
																	<td><bean:write name="superviseeIndex" property="courtNum"/></td>
																	<td><bean:write name="superviseeIndex" property="outOfCountyTransferInDate" formatKey="date.format.mmddyyyy"/></td>
																	<td><bean:write name="superviseeIndex" property="transferringCountyStateName"/></td> 
																	<td><bean:write name="superviseeIndex" property="otherCountyStatePersonIdNumber"/></td>
																</tr>
														     </pg:item>
	        				  		                     </logic:iterate>
        			       			                 </logic:notEmpty>
												</table>
											</td>
										</tr>
									</table>
<!--Transfer Information End-->	
									<div class="spacer4px"></div>
									<!-- BEGIN BUTTON TABLE -->
									<table border="0" width="100%">
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="disableSubmit(this, this.form)"><bean:message key="button.back"/></html:submit>
												<%--html:button property="submitAction" onclick="alert('Transfer to Casenotes not implemented at this time.')"><bean:message key="button.casenotes"/></html:button--%>
			                                	<html:submit property="submitAction" onclick="disableSubmit(this, this.form)"><bean:message key="button.casenotes"/></html:submit>
			                                	<html:submit property="submitAction" onclick="disableSubmit(this, this.form)"><bean:message key="button.cancel"/></html:submit>
											</td>
										</tr>
									</table>
								<!-- END BUTTON TABLE -->
<!-- END GREEN BORDER TABLE -->
						</table>
						<div class="spacer4px"></div>
					</td>
				</tr>				
			</table>	
<!-- END BLUE BORDER TABLE -->			
	</td>
</tr>
</table>
<!-- END  TABLE -->
</div>
<!-- Begin Pagination Closing Tag -->
</pg:pager>
<!-- End Pagination Closing Tag -->
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
<!-- slin: the folling password and function are to simply hide some functionality for .3 release (no need for any "real" type of security) -->
<%-- easter egg code 
<style>
		#easterEgg{			
			color: white; 
			text-align:center; 
			width:100%;
		}
		
		#passwordContainer{
			border: 1px solid blue;		
			position: absolute; 
			padding:5px;
			left:400px;
			background-color:#f0f0f0;
			top:400px;
		}
	
</style>	
<div id="easterEgg">Click <a href="javascript:verifyLOSAndTransfer(1)" style="color:white">Here</a> to enable LOS and Transfers</div>
<div id="passwordContainer" class="hidden">
<table cellpadding="2" cellspacing="0">
	<tr>
		<td class="formDeLabel">Password:</td>
		<td class="formDe"><input type="text" id="pwField"</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="button" name="" value="Enter" onclick="verifyLOSAndTransfer()"> <input type="button" name="" value="Cancel" onclick="show('passwordContainer', 0)">
		</td>
	</tr>	
</div>

<script>

var pwHidden="4QA";

function verifyLOSAndTransfer(clearField){
	if (clearField == 1){
		document.getElementById('pwField').value="";
	} 	
	pw=document.getElementById('pwField').value;
	document.getElementById("passwordContainer").className='visible';
	document.getElementById('pwField').focus();						
	if (pw==pwHidden){	
//		document.getElementById("losLinks").className='visible';
		document.getElementById("transfersLinks").className='visible';	
		document.getElementById("passwordContainer").className='hidden';
	}else if (pw!="" && pw!=pwHidden){	
		alert("Try Again.");
		document.getElementById("passwordContainer").className='hidden';
	} else if (pw=="" && clearField != 1){
		alert("Please enter password.");
	}	
}
</script> --%>
<!-- end password coding for version .3 -->
</html:form>
</body>
</html:html>