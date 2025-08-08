<!DOCTYPE HTML>

<%-- User selects "Add Hospitalization" button on the Medical page--%>
<%--MODIFICATIONS --%>
<%-- 05/01/07	Uma Gopinath Create JSP --%>
<%-- 07/20/2009 C Shimek    #61004 added timeout.js  --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>

<%--CUSTOM LIBRARIES NEEDED FOR PAGE --%>
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<%-- Javascript for emulated navigation --%>
<title><bean:message key="title.heading"/> - interviewInfoMedicalHospitalizationCreate.jsp</title>
<html:javascript formName="medicalHospForm"/>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>/js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>/js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>/js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>/js/juvTabInterview/medicalHospitalization.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script>
	$(document).ready(function(){
		var currentYear = (new Date).getFullYear();
		console.log("Current year: " + currentYear );
		 $("#admitYear").attr({
		       "max" : currentYear       // substitute your own
		    });
	})
</script>


</head> 
<%--END HEADER TAG--%>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin="0" leftmargin="0" >
<html:form action="/displayMedicalHospitalizationUpdateSummary" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|310">

<%-- begin heading tablE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="title.create"/>
      <bean:message key="prompt.medical"/>
      <bean:message key="prompt.hospitalization"/></td>	
  </tr>
</table>
<%-- end heading table --%>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>

<%-- begin instruction table --%>
<div class="spacer"></div>
<table width="98%" border="0">
  <tr>
    <td>
     <ul>
    		<li>Enter information as required, then select <b>Next</b> button to view summary.</li>
    		<li>Select <b>Back</b> button to return to previous page.</li>
      </ul>
    </td>
  </tr>
  <tr>
		<td class="required"><bean:message key="prompt.requiredFields"/>&nbsp;&nbsp;&nbsp;</td>
  </tr>
</table>
<%-- end instruction table --%>

<%--juv profile header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%--juv profile header end--%>

<%-- begin edit detail table --%>
<div class="spacer"></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top">
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">	
					<%--tabs start--%>
						<tiles:insert page="../caseworkCommon/juvenileProfileTabs.jsp" flush="true">
							<tiles:put name="tabid" value="interviewinfotab"/>
							<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
						</tiles:insert>		
						<%--script type='text/javascript'>renderTabs("Interview Info")</script--%>
					<%--tabs end--%>
          </td>
				</tr>
				<tr>
					<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width="5"></td>
				</tr>
			</table>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign="top" align="center">

          <%-- BEGIN  MENTAL HEALTH TABLE --%>
					<div class="spacer"></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									
									<table width='100%' border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td>
											<div class="spacer"></div>
												<table width='98%' border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td valign="top">
															<%--tabs start--%>
																<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
																	<tiles:put name="tabid" value="medical"/>
																	<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
																</tiles:insert>	
															<%--script type='text/javascript'>renderTestResultsTabs("Hosp")</script--%>
															<%--tabs end--%>
                            </td>
													</tr>
													<tr>
														<td bgcolor='#6699FF'><img src='/<msp:webapp/>images/spacer.gif' width="5"></td>
													</tr>
												</table>

												<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
													<tr>
														<td valign="top" align="center">

  														<%--  BEGIN INPUT TABLE --%>
															<div class="spacer"></div>
															<table width='98%' border="0" cellpadding="1" cellspacing="0" class="borderTableBlue">
																<tr>
																	<td valign="top" class="detailHead">
																	  <bean:message key="prompt.hospitalization"/> <bean:message key="prompt.information"/>
																	</td>
																</tr>
																<tr>
																	<td align="center">
																		<table border="0" cellpadding="2" cellspacing="2" width='100%'>
																		<tr>
                                         									<td class="formDeLabel" width='10%' nowrap="nowrap"><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border="0" width="10" height="9">Admit Year</td>
																			<td class="formDe" >
																				<input id="admitYear" name="admitYear" type="number" min="1999" />
                                         									</td>
                   															<td class="formDeLabel" width="10%" nowrap="nowrap"><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border="0" width="10" height="9"><bean:message key="prompt.admissionType"/></td>
              																<td class="formDe" >	
																				  <html:select name="medicalForm" property="hospRec.admissionTypeId" size="1">
																						<html:option value="">Please Select</html:option>
																						<html:optionsCollection name="medicalForm" property="admissionTypes" value="admissionTypeCode" label="description" />
																				  </html:select>
																		    </td>
																		</tr>
																		<tr>
																			<td class="formDeLabel"><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border="0" width="10" height="9"><bean:message key="prompt.facilityName"/></td>
																			<td class="formDe" colspan="5"><html:text name="medicalForm" property="hospRec.facilityName" size="30" /></td>
																		</tr>
																		<tr>
																			<td colspan=6 class="formDeLabel"><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border="0" width="10" height="9"><bean:message key="prompt.hospitalizationReason"/>&nbsp;
                              					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
                              						<tiles:put name="tTextField" value="hospRec.hospitalizationReason"/>
                               						<tiles:put name="tSpellCount" value="spellBtn1" />
																				</tiles:insert>
																				(Max. characters allowed: 250)
        																	</td>
																		</tr>
																		<tr>
																			<td colspan="6" class="formDe"> <html:textarea name="medicalForm" property="hospRec.hospitalizationReason" rows="4" cols="40" style="width:100%" ></html:textarea></td>
																		</tr>
																		<tr>
                                        									<td class="formDeLabel"  width="10%"><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border="0" width="10" height="9">Length of Stay</td>
																			<td class="formDe" colspan="3" >
																				 <html:select styleId="lengthOfStay" name="medicalForm" property="hospitalLengthOfStay" size="1">
																						<html:option value="">Please Select</html:option>
																						<html:optionsCollection name="medicalForm" property="hospitalLengthOfStays" value="code" label="description" />
																				  </html:select>
                                        									</td>
																		</tr>
																			<tr>
																				<td class="formDeLabel" valign="top" width="1%" nowrap="nowrap"><bean:message key="prompt.admittingPhysician"/></td>
																				<td class="formDe" colspan="5">
																					<table width='100%'>
																						<tr>
																							<td class="formDeLabel" colspan="3"><bean:message key="prompt.last"/></td>
																						</tr>
																						<tr>
																							<td colspan="2"><html:text name="medicalForm" property="hospRec.admittingPhysicianName.lastName" size="30" maxlength="75" /></td>
																						</tr>
																						<tr>
																							<td class="formDeLabel"><bean:message key="prompt.first"/></td>
																							<td class="formDeLabel"><bean:message key="prompt.middle"/></td>
																						</tr>
																						<tr>
																							<td><html:text name="medicalForm" property="hospRec.admittingPhysicianName.firstName" size="25" maxlength="50" /></td>
																							<td><html:text name="medicalForm" property="hospRec.admittingPhysicianName.middleName" size="25" maxlength="50" /></td>
																						</tr>
																					</table>
																				</td>
																			</tr>
																			<tr>
																				<td class="formDeLabel"><bean:message key="prompt.physicianPhone"/></td>
																				<td class="formDe" colspan="5">
																				  <html:text name="medicalForm" property="hospRec.physicianPhone.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" /> - 
																					<html:text name="medicalForm" property="hospRec.physicianPhone.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" /> - 
  																				<html:text name="medicalForm" property="hospRec.physicianPhone.last4Digit" size="4"  maxlength="4" onkeyup="return autoTab(this, 4);" />
																				</td>
																			</tr>
																		
																		</table>
																	</td>
																</tr>
															</table><%-- END INPUT TABLE --%>	

															<%-- begin button table --%>
															<div class="spacer"></div>
															<table border="0" width="100%">
															  <tr>
																<td align="center">
																	<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit> 
																	<html:submit property="submitAction" styleId="validateCreateFeild"><bean:message key="button.next"></bean:message></html:submit>
																	<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
																</td>
															  </tr>
															</table>
														</td>
													</tr>
												</table>
												<div class="spacer"></div>

											</td>
										</tr>
									</table>
									<div class="spacer"></div><%-- END MENTAL HEALTH TABLE --%>
								</td>
							</tr>
						</table>
						<div class="spacer"></div>
					</td>
				</tr>
			</table>
		 </td>
	</tr>
</table>
<%-- end detail table --%>
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>

