<!DOCTYPE HTML>

<%-- User selects the "Mental Health" tab in the "Interview Info" tab  on Juvenile Profile Detail page then selects the "Hospitalization" tab on MAYSI page --%>
<%--MODIFICATIONS --%>
<%-- 01/18/2007	Debbie Williamson	Create JSP --%>

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
<title><bean:message key="title.heading"/> - mentalHealthHospitalizationCreate.jsp</title>
<html:javascript formName="hospitalizationForm"/>
<script type='text/javascript' src="/<msp:webapp/>/js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>/js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>/js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>/js/hospitalization.js"></script>
<script type="text/javascript" src="/<msp:webapp/>/js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>/js/date.js"></script>
<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/mentalHealth.js"></script>

<!-- sceduled for deletion -->


</head> 
<%--END HEADER TAG--%>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true);"  topmargin=0 leftmargin=0 >
<html:form action="/displayMentalHealthHospitalizationCreateSummary" target="content">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchjuvenilecasework/Search_Juvenile_Profile.htm#|192">

<%-- begin heading tablE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header">
		  <bean:message key="title.juvenileCasework"/> - <bean:message key="title.juvenileProfile"/> - <bean:message key="title.create"/>
      <bean:message key="prompt.mentalHealth"/>
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

<div class=spacer></div>
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
		<td class="required"><bean:message key="prompt.requiredFields"/>&nbsp;&nbsp;&nbsp;*All date fields must be in the format of mm/yyyy.</td>
  </tr>
</table>
<%-- end instruction table --%>

<%--juv profile header start--%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
	<tiles:put name="headerType" value="profileheader"/>
</tiles:insert>
<%--juv profile header end--%>

<%-- begin edit detail table --%>
<div class=spacer></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign=top>
			<table width='100%' border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>	
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
					<td bgcolor='#33cc66'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
				</tr>
			</table>

			<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
				<tr>
					<td valign=top align=center>

          <%-- BEGIN  MENTAL HEALTH TABLE --%>
					<div class=spacer></div>
						<table width='98%' border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<table width='100%' border="0" cellpadding="0" cellspacing="0" >
										<tr>
											<td valign=top>
											<%--tabs start--%>
												<tiles:insert page="../caseworkCommon/interviewInfoTabs.jsp" flush="true">
													<tiles:put name="tabid" value="mentalhealthtab"/>
													<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
												</tiles:insert>	
												<%--script type='text/javascript'>renderInterviewInfo("Mental Health")</script--%>
											<%--tabs end--%>
                     </td>
										</tr>
										<tr>
											<td bgcolor='#6699FF'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
										</tr>
									</table>

									<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
										<tr>
											<td valign=top align=center>
											<div class=spacer></div>
												<table width='98%' border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td valign=top>
															<%--tabs start--%>
																<tiles:insert page="../caseworkCommon/mentalHealthTabs.jsp" flush="true">
																	<tiles:put name="tabid" value="hospitalization"/>
																	<tiles:put name="juvnum" value='<%= request.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM) %>' />
																</tiles:insert>	
															<%--script type='text/javascript'>renderTestResultsTabs("Hosp")</script--%>
															<%--tabs end--%>
                            </td>
													</tr>
													<tr>
														<td bgcolor='#ff6666'><img src='/<msp:webapp/>images/spacer.gif' width=5></td>
													</tr>
												</table>

												<table width='98%' border="0" cellpadding="0" cellspacing="0" class="borderTableRed">
													<tr>
														<td valign=top align=center>

  														<%--  BEGIN INPUT TABLE --%>
															<div class=spacer></div>
															<table width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
																<tr>
																	<td valign=top class=detailHead><bean:message key="prompt.hospitalization"/>
                                     <bean:message key="prompt.information"/></td>
																</tr>
																<tr>
																	<td align=center>
																		<table border=0 cellpadding=2 cellspacing=1 width='100%'>
																			<tr>
																				<td class=formDeLabel><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border=0 width=10 height=9><bean:message key="prompt.facilityName"/></td>
																				<td class=formDe colspan=3><html:text name="hospitalizationForm" property="hospRec.facilityName" size="30" /></td>
																			</tr>

																			<tr>
																				<td class=formDeLabel width="1%" nowrap><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border=0 width=10 height=9><bean:message key="prompt.admissionType"/></td>
																				<td class=formDe>
																				  <html:select name="hospitalizationForm" property="hospRec.admissionTypeCd" size="1">
           																	<html:option value="">Please Select</html:option>
           																	<html:optionsCollection name="hospitalizationForm" property="admissionTypes" value="admissionTypeCode" label="description" />
           																</html:select>
																				</td>
																				<td class=formDeLabel width='1%' nowrap><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border=0 width=10 height=9><bean:message key="prompt.admissionDate"/></td>
																				<td class=formDe><html:text name="hospitalizationForm" property="hospRec.admissionDate" styleId="admissionDate" size="10" maxlength="10" />
                                          
                                        										</td>
																			</tr>

																			<tr>
																				<td class=formDeLabel><img src="/<msp:webapp/>images/required.gif" title="Required" alt="required icon" border=0 width=10 height=9><bean:message key="prompt.releaseDate"/></td>
																				<td class=formDe colspan=3><html:text name="hospitalizationForm" property="hospRec.releaseDate" styleId="releaseDate" size="10" maxlength="10" />
	                                        									</td>
																			</tr>
																			<tr>
																				<td class=formDeLabel valign=top width="1%" nowrap><bean:message key="prompt.admittingPhysician"/></td>
																				<td class=formDe colspan=3>
																					<table width='100%'>
																						<tr>
																							<td class=formDeLabel colspan=3><bean:message key="prompt.last"/></td>
																						</tr>
																						<tr>
																							<td colspan=2><html:text name="hospitalizationForm" property="hospRec.physicianLastName" size="30" maxlength="75" /></td>
																						</tr>
																						<tr>
																							<td class=formDeLabel><bean:message key="prompt.first"/></td>
																							<td class=formDeLabel><bean:message key="prompt.middle"/></td>
																						</tr>
																						<tr>
																							<td><html:text name="hospitalizationForm" property="hospRec.physicianFirstName" size="25" maxlength="50" /></td>
																							<td><html:text name="hospitalizationForm" property="hospRec.physicianMiddleName" size="25" maxlength="50" /></td>
																						</tr>
																					</table>
																				</td>
																			</tr>
																			<tr>
																				<td class=formDeLabel><bean:message key="prompt.physicianPhone"/></td>
																				<td class=formDe colspan=3>
																				  <html:text name="hospitalizationForm" property="hospRec.physicianPhone.areaCode" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" /> - 
																					<html:text name="hospitalizationForm" property="hospRec.physicianPhone.prefix" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" /> - 
																					<html:text name="hospitalizationForm" property="hospRec.physicianPhone.last4Digit" size="4"  maxlength="4" onkeyup="return autoTab(this, 4);" />
																				</td>
																			</tr>
																			<tr>
																				<td colspan=4 class=formDeLabel><bean:message key="prompt.diamond"/><bean:message key="prompt.hospitalizationReason"/>&nbsp;
                                					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
                                						<tiles:put name="tTextField" value="hospRec.hospitalizationReason"/>
                                 						<tiles:put name="tSpellCount" value="spellBtn1" />
                              						</tiles:insert>  
                              					</td>
																			</tr>
																			<tr>
																				<td colspan=4 class=formDe> <html:textarea name="hospitalizationForm" property="hospRec.hospitalizationReason" rows="4" cols="40" style="width:100%" ></html:textarea></td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</table><%-- END INPUT TABLE --%>	

															<%-- begin button table --%>
														<div class=spacer></div>
															<table border="0" width="100%">
															  <tr>
																<td align="center">
																	<html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit> 
																	<html:submit property="submitAction" styleId="hospitalizationNext">
																		<bean:message key="button.next"></bean:message>
																	</html:submit>
																	<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
																</td>
															  </tr>
															</table>
														</td>
													</tr>
												</table>
												<div class=spacer></div>

											</td>
										</tr>
									</table>
									<div class=spacer></div><%-- END MENTAL HEALTH TABLE --%>
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
<%-- end detail table --%>
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>

