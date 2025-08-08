<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Used for Service Provider Creation (CSCD)-->
<!--MODIFICATIONS -->
<!-- DWilliamson 11/16/2007	Create JSP -->
<!-- C Shimek    12/30/2009 #63265 added feature check around update button for Service Provider Update -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDCodeTableConstants"%>
<%@ page import="naming.Features" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>

<%@ page import="org.apache.struts.action.Action"%>
<%@ page import="org.apache.struts.action.ActionErrors"%>


<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt"%>

<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> -
programSummary.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript"
	src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript">


</script>
</head>
<body topmargin="0" leftmargin="0"
	onKeyDown="checkEnterKeyAndSubmit(event,true)">

<html:form action="/displayCSCProgramUpdate" target="content">
	<logic:equal name="cscServiceProviderProgramForm" property="action"
		value="update">
		<input type="hidden" name="helpFile"
			value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|14">
	</logic:equal>
	<logic:equal name="cscServiceProviderProgramForm" property="action"
		value="activate">
		<input type="hidden" name="helpFile"
			value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|22">
	</logic:equal>
	<logic:equal name="cscServiceProviderProgramForm" property="action"
		value="investigate">
		<input type="hidden" name="helpFile"
			value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|26">
	</logic:equal>
	<logic:equal name="cscServiceProviderProgramForm" property="action"
		value="inactivate">
		<input type="hidden" name="helpFile"
			value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|24">
	</logic:equal>
	<logic:equal name="cscServiceProviderProgramForm" property="action"
		value="suspend">
		<input type="hidden" name="helpFile"
			value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|28">
	</logic:equal>
	<logic:equal name="cscServiceProviderProgramForm" property="action"
		value="create">
		<input type="hidden" name="helpFile"
			value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|8">
	</logic:equal>
	<logic:equal name="cscServiceProviderProgramForm" property="action"
		value="view">
		<input type="hidden" name="helpFile"
			value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|10">
	</logic:equal>
	<logic:equal name="cscServiceProviderProgramForm" property="action"
		value="update">
		<bean:define id="inUpdateORCreate" value="true" />
	</logic:equal>
	<logic:notPresent name="inUpdateORCreate">
		<logic:equal name="cscServiceProviderProgramForm" property="action"
			value="create">
			<bean:define id="inUpdateORCreate" value="true" />
		</logic:equal>
	</logic:notPresent>
	<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif"
				height="5" alt=""></td>
		</tr>
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="top"><!--tabs start--> <tiles:insert
						page="../../common/commonSupervisionTabs.jsp" flush="true" /> <!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img
						src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"
						alt=""></td>
				</tr>
				<tr>
					<td valign="top" align="center"><!-- BEGIN HEADING TABLE -->
					<table width="100%">
						<tr>
							<td align="center" class="header"><bean:message
								key="title.CSCD" />&nbsp;-&nbsp;<bean:message
								key="title.serviceProvider" />&nbsp;- <logic:equal
								name="cscServiceProviderProgramForm" property="action"
								value="update">
								<bean:message key="title.update" />
							</logic:equal> <logic:equal name="cscServiceProviderProgramForm"
								property="action" value="activate">
									       Activate
									    </logic:equal> <logic:equal name="cscServiceProviderProgramForm"
								property="action" value="investigate">
									      Investigate
									    </logic:equal> <logic:equal name="cscServiceProviderProgramForm"
								property="action" value="inactivate">
								<bean:message key="prompt.inactivate" />
							</logic:equal> <logic:equal name="cscServiceProviderProgramForm"
								property="action" value="suspend">
									       Suspend
									    </logic:equal> <logic:equal name="cscServiceProviderProgramForm"
								property="action" value="create">
								<bean:message key="title.create" />
							</logic:equal> <bean:message key="prompt.program" />&nbsp;<logic:equal
								name="cscServiceProviderProgramForm" property="action"
								value="view">
								<bean:message key="title.details" />
							</logic:equal><logic:notEqual name="cscServiceProviderProgramForm"
								property="action" value="view"><bean:message key="title.summary" />
							</logic:notEqual></td>
						</tr>
					</table>
					<!-- END HEADING TABLE --> <!-- BEGIN ERRORS TABLE -->
					<table width="100%">
						<tr>
							<td align="center" class="errorAlert"><html:errors></html:errors></td>
						</tr>
					</table>
					<!-- END ERRORS TABLE --> <!-- BEGIN INSTRUCTION TABLE -->
					<table width="98%" border="0">
						<tr>
							<td>
							<ul>
								<li>Review entries and Click Save & Continue.</li>
							</ul>
							</td>
						</tr>
					</table>
					<!-- BEGIN  TABLE --> <tiles:insert
						page="../../common/serviceProviderHeader.jsp" flush="true">

					</tiles:insert>
					<div class="spacer4px"></div>
					<table width="98%" cellpadding="2" cellspacing="0"
						class="borderTableBlue">
						<tr>
							<td class="detailHead"><bean:message key="prompt.program" />&nbsp;<bean:message
								key="prompt.info" /></td>
						</tr>
						<tr>
							<td>
							<table width="100%" cellpadding="4" cellspacing="1">
								<logic:notEqual name="cscServiceProviderProgramForm"
									property="action" value="create">



									<logic:equal name="cscServiceProviderProgramForm"
										property="action" value="view">
										<tr>

											<td class="formDeLabel"><bean:message
												key="prompt.status" /></td>
											<td class="formDe"><bean:write
												name="cscServiceProviderProgramForm"
												property="currentStatusDesc" /> &nbsp; <logic:notEqual
												name="cscServiceProviderProgramForm"
												property="currentStatusId"
												value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_INACTIVE%>">
												<span style="padding-left: 5px"> <logic:notEqual
													name="cscServiceProviderProgramForm"
													property="currentStatusId"
													value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_ACTIVE%>">
													<span id="activateLink"> <a
														href="/<msp:webapp/>displayCSCProgramUpdate.do?submitAction=Activate&amp;selectedValue=<bean:write name='cscServiceProviderProgramForm' property='programId'/>"
														class="editLink">Activate</a> | </span>
												</logic:notEqual> <logic:notEqual name="cscServiceProviderProgramForm"
													property="currentStatusId"
													value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_UNDERINVESTIGATION%>">
													<span id="investigateLink"> <a
														href="/<msp:webapp/>displayCSCProgramUpdate.do?submitAction=Investigate&amp;selectedValue=<bean:write name='cscServiceProviderProgramForm' property='programId'/>"
														class="editLink">Investigate</a> | </span>
												</logic:notEqual> <logic:notEqual name="cscServiceProviderProgramForm"
													property="currentStatusId"
													value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_SUSPENDED%>">
													<span id="suspendLink"> <a
														href="/<msp:webapp/>displayCSCProgramUpdate.do?submitAction=Suspend&amp;selectedValue=<bean:write name='cscServiceProviderProgramForm' property='programId'/>"
														class="editLink">Suspend</a> | </span>
												</logic:notEqual> <logic:notEqual name="cscServiceProviderProgramForm"
													property="currentStatusId"
													value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_INACTIVE%>">
													<span id="inactivateLink"> <a
														href="/<msp:webapp/>displayCSCProgramUpdate.do?submitAction=Inactivate&amp;selectedValue=<bean:write name='cscServiceProviderProgramForm' property='programId'/>"
														class="editLink">Inactivate</a> </span>
												</logic:notEqual> </span>
											</logic:notEqual></td>
											<td class="formDeLabel" width="1%" nowrap><logic:equal
												name="cscServiceProviderProgramForm"
												property="currentStatusId"
												value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_ACTIVE%>">
                                                Active
                                                
                                               </logic:equal> <logic:equal
												name="cscServiceProviderProgramForm"
												property="currentStatusId"
												value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_UNDERINVESTIGATION%>">
                                                Under Investigation
                                                
                                               </logic:equal> <logic:equal
												name="cscServiceProviderProgramForm"
												property="currentStatusId"
												value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_SUSPENDED%>">
                                                Suspend
                                                
                                               </logic:equal> <logic:equal
												name="cscServiceProviderProgramForm"
												property="currentStatusId"
												value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_INACTIVE%>">
                                                Inactive
                                                
                                               </logic:equal> <bean:message
												key="prompt.date" /></td>
											<td class="formDe"><bean:write
												name="cscServiceProviderProgramForm"
												property="statusDateAsStr" formatKey="date.format.mmddyyyy" /></td>
										</tr>

										<tr id="statusRow1">
											<td class="formDeLabel" colspan="4" nowrap><logic:equal
												name="cscServiceProviderProgramForm"
												property="currentStatusId"
												value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_ACTIVE%>">
                                                Active
                                                
                                               </logic:equal> <logic:equal
												name="cscServiceProviderProgramForm"
												property="currentStatusId"
												value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_UNDERINVESTIGATION%>">
                                                Under Investigation
                                                
                                               </logic:equal> <logic:equal
												name="cscServiceProviderProgramForm"
												property="currentStatusId"
												value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_SUSPENDED%>">
                                                Suspend
                                                
                                               </logic:equal> <logic:equal
												name="cscServiceProviderProgramForm"
												property="currentStatusId"
												value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_INACTIVE%>">
                                                Inactive
                                                
                                               </logic:equal> <bean:message
												key="prompt.reason" /></td>
										</tr>

										<tr id="statusRow2">
											<td class="formDe" colspan="4"><logic:equal
												name="cscServiceProviderProgramForm" property="statusReason"
												value="">
										       NEW PROGRAM CREATED
									        </logic:equal> <logic:notEqual name="cscServiceProviderProgramForm"
												property="statusReason" value="">
												<bean:write name="cscServiceProviderProgramForm"
													property="statusReason" />
											</logic:notEqual></td>
										</tr>

									</logic:equal>
									<logic:notEqual name="cscServiceProviderProgramForm"
										property="action" value="view">

										<logic:notPresent name="inUpdateORCreate">
											<tr>
												<td class="formDeLabel" nowrap><logic:equal
													name="cscServiceProviderProgramForm"
													property="changeToStatusId"
													value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_ACTIVE%>">
                                                Active
                                               </logic:equal> <logic:equal
													name="cscServiceProviderProgramForm"
													property="changeToStatusId"
													value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_UNDERINVESTIGATION%>">
                                                Under Investigation
                                               </logic:equal> <logic:equal
													name="cscServiceProviderProgramForm"
													property="changeToStatusId"
													value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_SUSPENDED%>">
                                                Suspend
                                               </logic:equal> <logic:equal
													name="cscServiceProviderProgramForm"
													property="changeToStatusId"
													value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_INACTIVE%>">
													<bean:message key="prompt.inactivate" />
												</logic:equal> <bean:message key="prompt.date" /></td>
												<td class="formDe" colspan="3"><bean:write
													name="cscServiceProviderProgramForm"
													property="changeToStatusDateAsStr" /></td>
											</tr>

											<tr>
												<td class="formDeLabel" colspan="4" nowrap><logic:equal
													name="cscServiceProviderProgramForm"
													property="changeToStatusId"
													value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_ACTIVE%>">
													<bean:message key="prompt.activate" />
												</logic:equal> <logic:equal name="cscServiceProviderProgramForm"
													property="changeToStatusId"
													value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_UNDERINVESTIGATION%>">
                                                Under Investigation
                                               </logic:equal> <logic:equal
													name="cscServiceProviderProgramForm"
													property="changeToStatusId"
													value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_SUSPENDED%>">
                                                Suspend
                                               </logic:equal> <logic:equal
													name="cscServiceProviderProgramForm"
													property="changeToStatusId"
													value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_INACTIVE%>">
													<bean:message key="prompt.inactivate" />
												</logic:equal> <bean:message key="prompt.reason" /></td>
											</tr>
											<tr>
												<td class="formDe" colspan="4"><bean:write
													name="cscServiceProviderProgramForm"
													property="changeToStatusReason" /></td>
											</tr>


											<tr id="statusRow0">
												<td class="formDeLabel" width="1%" nowrap>Current <bean:message
													key="prompt.status" /></td>
												<td class="formDe"><bean:write
													name="cscServiceProviderProgramForm"
													property="currentStatusDesc" /></td>
												<td class="formDeLabel" width="1%"><logic:equal
													name="cscServiceProviderProgramForm"
													property="currentStatusId"
													value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_ACTIVE%>">
													<bean:message key="prompt.activate" />

												</logic:equal> <logic:equal name="cscServiceProviderProgramForm"
													property="currentStatusId"
													value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_UNDERINVESTIGATION%>">
                                                Under Investigation
                                                
                                               </logic:equal> <logic:equal
													name="cscServiceProviderProgramForm"
													property="currentStatusId"
													value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_SUSPENDED%>">
                                                Suspend
                                                
                                               </logic:equal> <logic:equal
													name="cscServiceProviderProgramForm"
													property="currentStatusId"
													value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_INACTIVE%>">
													<bean:message key="prompt.inactivate" />

												</logic:equal> <bean:message key="prompt.date" /></td>
												<td class="formDe"><bean:write
													name="cscServiceProviderProgramForm"
													property="statusDateAsStr" formatKey="date.format.mmddyyyy" />
												</td>
											</tr>
											<tr id="statusRow1">
												<td class="formDeLabel" colspan="4" nowrap><logic:equal
													name="cscServiceProviderProgramForm"
													property="currentStatusId"
													value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_ACTIVE%>">
													<bean:message key="prompt.activate" />
												</logic:equal> <logic:equal name="cscServiceProviderProgramForm"
													property="currentStatusId"
													value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_UNDERINVESTIGATION%>">
                                                Under Investigation
                                               </logic:equal> <logic:equal
													name="cscServiceProviderProgramForm"
													property="currentStatusId"
													value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_SUSPENDED%>">
                                                Suspend
                                               </logic:equal> <logic:equal
													name="cscServiceProviderProgramForm"
													property="currentStatusId"
													value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_INACTIVE%>">
													<bean:message key="prompt.inactivate" />
												</logic:equal> <bean:message key="prompt.reason" /></td>
											</tr>
											<tr id="statusRow2">
												<td class="formDe" colspan="4"><bean:write
													name="cscServiceProviderProgramForm"
													property="statusReason" /></td>
											</tr>
										</logic:notPresent>


										<logic:present name="inUpdateORCreate">

											<logic:equal name="cscServiceProviderProgramForm"
												property="action" value="update">
												<tr id="statusRow0">
													<td class="formDeLabel" width="1%" nowrap><bean:message
														key="prompt.status" /></td>
													<td class="formDe"><bean:write
														name="cscServiceProviderProgramForm"
														property="currentStatusDesc" /></td>
													<td class="formDeLabel">Status Change <bean:message
														key="prompt.date" /></td>
													<td class="formDe"><bean:write
														name="cscServiceProviderProgramForm"
														property="statusDateAsStr"
														formatKey="date.format.mmddyyyy" /></td>
												</tr>
												<tr id="statusRow1">
													<td class="formDeLabel" colspan="4" nowrap>Status
													Change Reason &nbsp;</td>
												</tr>
												<tr id="statusRow2">
													<td class="formDe" colspan="4"><bean:write
														name="cscServiceProviderProgramForm"
														property="changeToStatusReason" /></td>
												</tr>


											</logic:equal>
										</logic:present>





									</logic:notEqual>
								</logic:notEqual>
								<tr>
									<td class="formDeLabel" width="1%" nowrap><bean:message
										key="prompt.identifier" /></td>
									<td class="formDe"><bean:write
										name="cscServiceProviderProgramForm" property="identifier" /></td>
									<td class="formDeLabel" nowrap><bean:message
										key="prompt.contractProgram" /></td>
									<td class="formDe"><bean:write
										name="cscServiceProviderProgramForm"
										property="contractProgramAsStr" /></td>
								</tr>
								<tr>
									<td class="formDeLabel" width="1%" nowrap><bean:message
										key="prompt.name" /></td>
									<td colspan="3" class="formDe"><bean:write
										name="cscServiceProviderProgramForm" property="name" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message
										key="prompt.programGroup" /></td>
									<td colspan="3" class="formDe"><bean:write
										name="cscServiceProviderProgramForm"
										property="programGroupDesc" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.type" /></td>
									<td colspan="3" class="formDe"><bean:write
										name="cscServiceProviderProgramForm"
										property="programTypeDesc" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.subtype" /></td>
									<td colspan="3" class="formDe"><bean:write
										name="cscServiceProviderProgramForm"
										property="programSubTypeDesc" /></td>
								</tr>
								<tr>
									<td class="formDeLabel" nowrap><bean:message
										key="prompt.programStartDate" /></td>
									<td class="formDe"><bean:write
										name="cscServiceProviderProgramForm"
										property="programStartDateAsStr"
										formatKey="date.format.mmddyyyy" /></td>
									<td class="formDeLabel" nowrap width="1%"><bean:message
										key="prompt.programEndDate" /></td>
									<td class="formDe"><bean:write
										name="cscServiceProviderProgramForm"
										property="programEndDateAsStr"
										formatKey="date.format.mmddyyyy" /></td>
								</tr>
								<tr>
									<td class="formDeLabel" nowrap>Division</td>

									<td class="formDe" colspan="3"><bean:write
										name="cscServiceProviderProgramForm"
										property="selectedDivisionName" /></td>
								</tr>
								<tr>
									<td class="formDeLabel" nowrap>Program Unit</td>
									<td class="formDe" colspan="3"><bean:write
										name="cscServiceProviderProgramForm"
										property="selectedProgramUnitName" /></td>
								</tr>
								<tr>
									<td class="formDeLabel" nowrap>Incarceration Program</td>
									<td class="formDe"><bean:write
										name="cscServiceProviderProgramForm"
										property="selectedIncarcerationConditionName" /></td>
									<td class="formDeLabel" nowrap>Price</td>
									<td class="formDe"><bean:write
										name="cscServiceProviderProgramForm" property="price"
										formatKey="currency.format" /></td>
								</tr>

								<tr>
									<td class="formDeLabel" nowrap><bean:message
										key="prompt.sexSpecific" /></td>
									<td class="formDe" colspan="3"><bean:write
										name="cscServiceProviderProgramForm"
										property="sexSpecificDesc" /></td>
								</tr>
								<tr>
									<td class="formDeLabel" nowrap>Language(s) Offered</td>
									<td class="formDe" colspan="3"><bean:write
										name="cscServiceProviderProgramForm"
										property="languagesOfferedDesc" /></td>
								</tr>
								<tr>
									<td class="formDeLabel" width="1%" nowrap><bean:message
										key="prompt.location" />(s)</td>
									<td class="formDe" colspan="3"><bean:write
										name="cscServiceProviderProgramForm"
										property="selectedLocationDescs" /></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message
										key="prompt.officeHours" /></td>
									<td colspan="3" class="formDe"><bean:write
										name="cscServiceProviderProgramForm" property="officeHours" /></td>
								</tr>
								<tr>
									<td colspan="4" class="formDeLabel"><bean:message
										key="prompt.description" /></td>
								</tr>
								<tr>
									<td colspan="4" class="formDe"><bean:write
										name="cscServiceProviderProgramForm" property="description" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>

					<div class="spacer4px"></div>
					<!-- BEGIN BUTTON TABLE -->
					<table border="0">

						<tr>
							<td><html:submit property="submitAction">
								<bean:message key="button.back" />
							</html:submit> <logic:notEqual name="cscServiceProviderProgramForm"
								property="action" value="view">
								<logic:equal name="cscServiceProviderProgramForm"
									property="secondaryAction" value="summary">
									<html:submit property="submitAction"
										onclick="return disableSubmit(this, this.form);">
										<bean:message key="button.saveAndContinue"></bean:message>
									</html:submit>
									<html:submit property="submitAction">
										<bean:message key="button.cancel"></bean:message>
									</html:submit>
								</logic:equal>
							</logic:notEqual> <logic:equal name="cscServiceProviderProgramForm"
								property="action" value="view">
								<logic:notEqual name="cscServiceProviderProgramForm"
									property="currentStatusId"
									value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_INACTIVE%>">
									<jims2:isAllowed requiredFeatures='<%=Features.CS_ASP_UPDATE_CSC%>'>	
									<html:submit property="submitAction">
										<bean:message key="button.update"></bean:message>
									</html:submit>
									</jims2:isAllowed>
								</logic:notEqual>
							</logic:equal></td>
						</tr>
					</table>
					<!-- END BUTTON TABLE --></td>
				</tr>
			</table>
			<br>
			</td>
		</tr>
	</table>
	<!-- END  TABLE --></div>
	<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
<html:html></html:html>
