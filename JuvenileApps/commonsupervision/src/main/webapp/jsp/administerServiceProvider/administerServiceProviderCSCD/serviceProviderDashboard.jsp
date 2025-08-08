<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Used for Service Provider Creation Summary (CSCD)-->
<!--MODIFICATIONS -->
<!-- DWilliamson 11/20/2007	Create JSP -->
<!-- CShimek     12/12/2008 defect#56011 added onload script that works like onclick on radion buttons  -->
<!-- RCapestani  11/12/2013 defect#76368 change logic on updateSP button to display when service provider is not active  -->
 
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDCodeTableConstants" %>
<%@ page import="naming.Features" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<html:html>

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
<title><bean:message key="title.heading"/> - serviceProviderDashboard.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script>

function goToURLCustom(incomingURL, aValToGoTo){
	incomingURL=incomingURL + "&selectedValue=" + aValToGoTo;
	goNav(incomingURL);
	return;
}

function showProgramButtons(aValToSelect,incomingStatus){
	document.forms[0].selProgVal.value = aValToSelect;
	show( "updateSPbutton", 1, "inline" );
	show( "inactivateSPbutton", 1, "inline" );
	show( "suspendSPbutton", 1, "inline" );
	show( "activateSPbutton", 1, "inline" );
	show( "investigateSPbutton", 1, "inline" );
	if(incomingStatus=='<%=PDCodeTableConstants.ASP_CS_PROGRAM_UNDERINVESTIGATION%>'){
		show( "investigateSPbutton", 0, "inline" );
	}
	if(incomingStatus=='<%=PDCodeTableConstants.ASP_CS_PROGRAM_ACTIVE%>'){
		show( "activateSPbutton", 0, "inline" );
	}
	if(incomingStatus=='<%=PDCodeTableConstants.ASP_CS_PROGRAM_SUSPENDED%>'){
		show( "suspendSPbutton", 0, "inline" );
	}
	if(incomingStatus=='<%=PDCodeTableConstants.ASP_CS_PROGRAM_PENDING%>'){
		show( "suspendSPbutton", 0, "inline" );
		show( "activateSPbutton", 0, "inline" );
		show( "investigateSPbutton", 0, "inline" );
	}
	if(incomingStatus=='<%=PDCodeTableConstants.ASP_CS_PROGRAM_INACTIVE%>'){
		show( "inactivateSPbutton", 0, "inline" );
	}
}

function showContactButtons(aValToSelect,incomingStatus){
	document.forms[0].selContactVal.value = aValToSelect;
	show( "updateContactButton", 1, "inline" );
	show( "inactivateContactButton", 1, "inline" );
}

function checkForSelections(){  
	var pSelects = document.getElementsByName("selectedProgram");
	var pStatusSelects = document.getElementsByName("selectedProgramStatusId");
	var cSelects = document.getElementsByName("selectedContact");
	for (x=0; x<pSelects.length; x++){
		if (pSelects[x].checked == true){
			showProgramButtons(pSelects[x].value,pStatusSelects[x].value);
		}	
	}
	for (x=0; x<cSelects.length; x++){
		if (cSelects[x].checked == true){
			showContactButtons(cSelects[x].value,"");
		}	
	}
}
</script>
</head>
<body topmargin=0 leftmargin="0" onKeyDown="checkEnterKeyAndSubmit(event,true)" onload="checkForSelections()">
<html:form action="/handleCSCServiceProviderDashboard" >
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|15">
<%-- Begin Pagination Header Tag--%>

<%-- End Pagination header stuff --%>
<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
			<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
		</tr>
		<tr>
			<td valign="top">
<!-- BEGIN TABS TABLE -->			
				<table width="100%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
						<td valign="top">
							<!--tabs start-->
							<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true"/>
							<!--tabs end-->
						</td>
					</tr>
					<tr>
						<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
				</table>
<!-- END TABS TABLE  -->
<!-- BEGIN BLUE BORDER TABLE -->				
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					<tr>
						<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
					<tr>
						<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
							<table width="100%">
								<tr>
									<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-&nbsp;<bean:message key="title.serviceProvider"/>&nbsp;<bean:message key="title.details"/></td>
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
<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0" cellpadding=0 cellspacing=0>
								<logic:present name="confirmMsg">
									<tr id="confirmations">
										<td class="confirm">
											<bean:write name="confirmMsg"/>
										</td>
									</tr>
								</logic:present>
                 
								<logic:equal name="cscServiceProviderForm" property="statusId" value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_PENDING%>">
									<tr>
										<td>
											<ul>
												<li>Add at least 1 Program and 1 Contact by clicking the Create button under the appropriate section.</li>
											</ul>
										</td>
									</tr>
								</logic:equal>
								<logic:notEqual name="cscServiceProviderForm" property="statusId" value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_PENDING%>">
									<tr>
										<td>
											<ul>
												<li>Add a new Program or Contact or select one and click Update. </li>
											</ul>
										</td>
									</tr>
								</logic:notEqual>
							</table>
<!-- END INSTRUCTION TABLE -->							
<!-- BEGIN SP TABLE -->
							<table width="98%" border="0" cellspacing="0" class="borderTableBlue">
								<tr>
									<td class="detailHead">
										<table width="100%" cellpadding="2" cellspacing="0">
											<tr>
												<td width="1%"><a href="javascript:showHideMulti('serviceProviderSpanRow', 'serviceProviderSpanRow', 1,'/<msp:webapp/>')"><img border=0 src="../../../images/expand.gif" name="serviceProviderSpanRow"></a></td>
												<td class="detailHead"><bean:write name="cscServiceProviderForm" property="name"/></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="serviceProviderSpanRow0" class="hidden">
									<td>
										<table width="100%" border="0" cellpadding="4" cellspacing="1">
											<tr>
												<td class="formDeLabel" ><bean:message key="prompt.startDate" /></td>
												<td class="formDe"><bean:write name="cscServiceProviderForm" property="startDateAsStr" formatKey="date.format.mmddyyyy"/></td>
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.inHouse" /></td>
												<td class="formDe"><bean:write name="cscServiceProviderForm" property="inHouseAsStr"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" ><bean:message key="prompt.IFAS" /></td>
												<td colspan="1" class="formDe"><bean:write name="cscServiceProviderForm" property="ifasNumber"/></td>
												<td class=formDeLabel width=1% nowrap>Faith Based</td>
												<td class=formDe>
												<logic:equal name="cscServiceProviderForm" property="isFaithBased" value="true">
													YES
												</logic:equal>
												<logic:equal name="cscServiceProviderForm" property="isFaithBased" value="false">
													NO
												</logic:equal>																							
													
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.phone" /></td>
												<td class="formDe" colspan="3"><msp:formatter name="cscServiceProviderForm" property="phoneNumber" format="A-P-F"/><logic:notEqual name="cscServiceProviderForm" property="phoneNumber.ext" value=""><bean:message key="prompt.ext"/> <msp:formatter name="cscServiceProviderForm" property="phoneNumber" format="X"/></logic:notEqual></td>                        </tr>
											<tr>
												<td class="formDeLabel" width="1%" ><bean:message key="prompt.fax" /></td>
												<td class="formDe" colspan="3"><msp:formatter name="cscServiceProviderForm" property="faxNumber" format="A-P-F"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" ><bean:message key="prompt.website" /></td>
												<td class="formDe" colspan="3">
												<logic:notEmpty name="cscServiceProviderForm" property="website"><bean:define id="web1" name="cscServiceProviderForm" property="website"/>
													<a href="//<%=web1%>" target="_new"><bean:write name="cscServiceProviderForm" property="website"/></a>
												</logic:notEmpty>
												<logic:empty name="cscServiceProviderForm" property="website">
													<bean:write name="cscServiceProviderForm" property="website"/>
												</logic:empty>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" ><bean:message key="prompt.email" /></td>
												<td class="formDe" colspan="3"><a href='mailto:<bean:write name="cscServiceProviderForm" property="email"/>'><bean:write name="cscServiceProviderForm" property="email"/> </a></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" ><bean:message key="prompt.ftp" /></td>
												<td class="formDe" colspan="3"><bean:write name="cscServiceProviderForm" property="ftp"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap colspan="4"><bean:message key="prompt.comments"/></td>
											</tr>
											<tr>
												<td class="formDe" nowrap colspan="4"><bean:write name="cscServiceProviderForm" property="comments"/></td>
											</tr>
										</table>
<!-- BEGIN MAILING ADDRESS INFORMATION TABLES -->
										<table width="100%" cellpadding="1" cellspacing="1">
											<tr>
												<td class="detailHead">
													<table width="100%" cellpadding="2" cellspacing="0">
														<tr>
															<td width="1%"><a href="javascript:showHideMulti('mailing', 'ma', 1,'/<msp:webapp/>')"><img border=0 src="../../../images/expand.gif" name="mailing"></a></td>
															<td class="detailHead">
																<logic:equal name="cscServiceProviderForm" property="inHouse" value="true">
																	<bean:write name="cscServiceProviderForm" property="name"/> <bean:message key="prompt.address"/>
																</logic:equal>
																<logic:notEqual name="cscServiceProviderForm" property="inHouse" value="true">
																	<bean:message key="prompt.mailingAddress"/>
																</logic:notEqual>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr class="hidden" id="ma0">
												<td>
													<table width="100%" border=0 cellpadding="2" cellspacing="1">
														<tr class="formDeLabel">
															<td width="20%"><bean:message key="prompt.addressType" /></td>
															<td width="70%"><bean:message key="prompt.address" /></td>
															<td width="10%"><bean:message key="prompt.county" /></td>
														</tr>
														<tr class="formDe">
															<td><bean:write name="cscServiceProviderForm" property="mailingAddress.addressType"/></td>
															<td>
																<div><bean:write name="cscServiceProviderForm" property="mailingAddress.streetAddress"/></div>
																<div><bean:write name="cscServiceProviderForm" property="mailingAddress.cityStateZip"/></div>
															</td>
															<td><bean:write name="cscServiceProviderForm" property="mailingAddress.county"/></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
<!-- END MAILING ADDRESS INFORMATION TABLE -->										
										<logic:equal name="cscServiceProviderForm" property="inHouse" value="false">
<!-- BEGIN BILLING ADDRESS INFORMATION TABLES -->										
											<table width="100%" cellpadding="1" cellspacing="1">
												<tr>
													<td class="detailHead">
														<table width="100%" cellpadding=2 cellspacing=0>
															<tr>
																<td width="1%"><a href="javascript:showHideMulti('billing', 'ba', 1,'/<msp:webapp/>')"><img border=0 src="../../../images/expand.gif" name="billing"></a></td>
																<td class="detailHead"><bean:message key="prompt.billingAddress" /></td>
															</tr>
														</table>
													</td>
												</tr>
												<tr class="hidden" id="ba0">
													<td>
														<table width="100%" border="0" cellpadding="2" cellspacing="1">
															<tr class="formDeLabel">
																<td width="20%"><bean:message key="prompt.addressType" /></td>
																<td width="70%"><bean:message key="prompt.address" /></td>
																<td width="10%"><bean:message key="prompt.county" /></td>
															</tr>
															<tr class="formDe">
																<td><bean:write name="cscServiceProviderForm" property="billingAddress.addressType"/></td>
																<td>
																	<div><bean:write name="cscServiceProviderForm" property="billingAddress.streetAddress"/></div>
																	<div><bean:write name="cscServiceProviderForm" property="billingAddress.cityStateZip"/></div>
																</td>
																<td><bean:write name="cscServiceProviderForm" property="billingAddress.county"/></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
	<!-- END BILLING ADDRESS INFORMATION TABLE -->
										</logic:equal>
									</td>
								</tr>
							</table>
<!-- END SP INFORMATION TABLE -->
							<br>
<!-- BEGIN PROGRAMS TABLE -->
							<table width="98%" cellpadding="0" cellspacing="0" border=0>
								<tr>
									<td width="100%" valign="top">
<!-- BEGIN PROGRAMS BORDER TABLE -->									
										<table width="100%" cellpadding="2" cellspacing="0" class="borderTableBlue">
											<tr class="detailHead">
												<td><bean:message key="prompt.programs"/></td>
											</tr>
											<tr>
												<td>
<!-- BEGIN PROGRAMS DETAILS TABLE  -->												
													<table width="100%" cellpadding="2" cellspacing="1" id="programsTable">
														<tr class="formDeLabel">
															<jims2:isAllowed requiredFeatures="<%=Features.CS_ASP_UPDATE_CSC%>">	 
																<td width="1%"></td>
															</jims2:isAllowed>
															<td><bean:message key="prompt.identifier"/>s
																<jims2:sortResults beanName="cscServiceProviderForm" results="programs" primaryPropSort="programIdentifier" primarySortType="STRING"  defaultSortOrder="ASC" sortId="1" levelDeep="3"/>
															</td>
															<td><bean:message key="prompt.name"/>
																<jims2:sortResults beanName="cscServiceProviderForm" results="programs" primaryPropSort="programName" primarySortType="STRING"  defaultSortOrder="ASC" sortId="2" levelDeep="3"/>
															</td>
															<td><bean:message key="prompt.programGroup"/>
																<jims2:sortResults beanName="cscServiceProviderForm" results="programs" primaryPropSort="programGroupDesc" primarySortType="STRING"  defaultSortOrder="ASC" sortId="3" levelDeep="3"/>
															</td>
															<td><bean:message key="prompt.status"/>
																<jims2:sortResults beanName="cscServiceProviderForm" results="programs" primaryPropSort="programStatusDesc" primarySortType="STRING"  defaultSortOrder="ASC" sortId="4" levelDeep="3"/>
															</td>
														</tr>
													<% int RecordCounter2=0;
														String bgcolor2="";
													%>
														<logic:iterate id="programIndex" name="cscServiceProviderForm" property="programs">
															<tr class= <% RecordCounter2++;
																bgcolor2 = "alternateRow";                      
																if (RecordCounter2 % 2 == 1)
																bgcolor2 = "normalRow";
																out.print(bgcolor2); %>>
																<!-- list all programs start-->
																<jims2:isAllowed requiredFeatures="<%=Features.CS_ASP_UPDATE_CSC%>">	 
																	<td width="1%">
																		<bean:define id="program" name="programIndex" property="programName"/>
																		<logic:notEqual name="cscServiceProviderForm" property="statusId" value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_INACTIVE%>">				                               
																			<logic:notEqual name="programIndex" property="programStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_INACTIVE%>">
																				<input type=radio onclick="javascript:showProgramButtons('<bean:write name="programIndex" property="programId"/>','<bean:write name="programIndex" property="programStatusId"/>' );" name="selectedProgram" id="programRadio<%=RecordCounter2%>" value="<bean:write name="programIndex" property="programId"/>"/>
																				<input type="hidden" name="selectedProgramStatusId" value="<bean:write name="programIndex" property="programStatusId"/>" >
																			</logic:notEqual>
																		</logic:notEqual>
																	</td>
																</jims2:isAllowed>
																<td> 
																	<jims2:isAllowed requiredFeatures="<%=Features.CS_ASP_UPDATE_CSC%>">	 
																		<a href='/<msp:webapp/>displayCSCProgramUpdate.do?submitAction=View&selectedValue=<bean:write name="programIndex" property="programId"/>'>
																			<bean:write name="programIndex" property="programIdentifier"/>
																		</a>
																	</jims2:isAllowed>
																</td>
																<td><bean:write name="programIndex" property="programName"/></td>
																<td><bean:write name="programIndex" property="programGroupDesc"/></td>
																<td><bean:write name="programIndex" property="programStatusDesc"/></td>
															</tr>
														</logic:iterate>
													</table>
<!-- END PROGRAMS DETAILS TABLE  -->													
												</td>
											</tr>
											<tr>
												<td align="center">
<!-- BEGIN PROGRAMS DETAILS BUTTON TABLE -->												
													<table cellpadding="2" cellspacing="0">
 														<tr>
															<td>
																<input type="hidden" name="selProgVal" value=""/>
																<logic:notEqual name="cscServiceProviderForm" property="statusId" value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_INACTIVE%>">				        
																	<jims2:isAllowed requiredFeatures="<%=Features.CS_ASP_UPDATE_CSC%>">	 
																		<input type="button" value="Create"  class="button" onclick="javascript:goToURLCustom('/<msp:webapp/>displayCSCProgramUpdate.do?submitAction=Create', document.forms[0].selProgVal.value);"></input>
																	</jims2:isAllowed>
																	<span id="updateSPbutton" class="hidden"> <input type="button"  class="button" value="Update" onclick="javascript:goToURLCustom('/<msp:webapp/>displayCSCProgramUpdate.do?submitAction=Update', document.forms[0].selProgVal.value);"></input></span>
																	<span id="activateSPbutton" class="hidden"> <input type="button"  class="button" value="Activate" onclick="javascript:goToURLCustom('/<msp:webapp/>displayCSCProgramUpdate.do?submitAction=Activate', document.forms[0].selProgVal.value);"></input></span>
																	<span id="inactivateSPbutton" class="hidden"> <input type="button"  class="button" value="Inactivate" onclick="javascript:goToURLCustom('/<msp:webapp/>displayCSCProgramUpdate.do?submitAction=Inactivate', document.forms[0].selProgVal.value);"></input></span>
																	<span id="suspendSPbutton" class="hidden"> <input type="button"  class="button" value="Suspend" onclick="javascript:goToURLCustom('/<msp:webapp/>displayCSCProgramUpdate.do?submitAction=Suspend', document.forms[0].selProgVal.value);"></input></span>
																	<span id="investigateSPbutton" class="hidden"> <input type="button"  class="button" value="Investigate" onclick="javascript:goToURLCustom('/<msp:webapp/>displayCSCProgramUpdate.do?submitAction=Investigate', document.forms[0].selProgVal.value);"></input></span>
																</logic:notEqual>
															</td>
														</tr>
													</table>
<!-- END PROGRAMS DETAILS BUTTON TABLE -->														
												</td>
											</tr>
										</table>
<!-- END PROGRAMS BORDER TABLE -->										
									</td>
								</tr>
							</table>
<!-- END PROGRAMS TABLE -->							
 							<br>
<!-- BEGIN CONTACTS TABLE -->              
							<table width="98%" cellpadding="0" cellspacing="0">
								<tr>
									<td width=90% valign="top">
<!-- BEGIN CONTACTS BORDER TABLE -->									
										<table width="100%" cellpadding="2" cellspacing="0" class="borderTableBlue">
											<tr class="detailHead">
												<td><bean:message key="prompt.contacts"/></td>
											</tr>
											<tr>
												<td>
<!-- BEGIN CONTACTS DETAILS TABLE -->												
													<table width="100%" cellpadding="2" cellspacing="1" id="contactsTable">
														<tr class="formDeLabel">
															<jims2:isAllowed requiredFeatures="<%=Features.CS_ASP_UPDATE_CSC%>">	 
																<td width="1%"></td>
															</jims2:isAllowed>
															<td><bean:message key="prompt.name"/>
																<jims2:sortResults beanName="cscServiceProviderForm" results="contacts" primaryPropSort="contactName.lastName" primarySortType="STRING"  secondPropSort="contactName.firstName" secondarySortType="STRING" defaultSortOrder="ASC" sortId="11" levelDeep="3"/>
															</td>
															<td width="5%" nowrap>Job Title
																<jims2:sortResults beanName="cscServiceProviderForm" results="contacts" primaryPropSort="jobTitle" primarySortType="STRING"  defaultSortOrder="ASC" sortId="16" levelDeep="3"/>
															</td>
															<td>
																<bean:message key="prompt.email"/>
																<jims2:sortResults beanName="cscServiceProviderForm" results="contacts" primaryPropSort="email" primarySortType="STRING"  defaultSortOrder="ASC" sortId="12" levelDeep="3"/>
															</td>
															<td><bean:message key="prompt.officePhone"/>
																<jims2:sortResults beanName="cscServiceProviderForm" results="contacts" primaryPropSort="contactOfficePhoneNumber.formattedPhoneNumber" primarySortType="STRING"  defaultSortOrder="ASC" sortId="13" levelDeep="3"/>
															</td>
															<td><bean:message key="prompt.status"/>
															<jims2:sortResults beanName="cscServiceProviderForm" results="contacts" primaryPropSort="contactStatusDesc" primarySortType="STRING"  defaultSortOrder="ASC" sortId="14" levelDeep="3"/>
															</td>
														</tr>
														<% int RecordCounter3=0;
														String bgcolor3="";
														%>
														<logic:iterate id="contactIndex" name="cscServiceProviderForm" property="contacts">
															<tr class= <% RecordCounter3++;
															bgcolor3 = "alternateRow";                      
															if (RecordCounter3 % 2 == 1)
															 bgcolor3 = "normalRow";
															 out.print(bgcolor3); %>>
																<jims2:isAllowed requiredFeatures="<%=Features.CS_ASP_UPDATE_CSC%>">	 
																	<td width="1%">
																		<bean:define id="contact" name="contactIndex" property="contactId"/>
																		<logic:notEqual name="cscServiceProviderForm" property="statusId" value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_INACTIVE%>">				                               
																			<logic:notEqual name="contactIndex" property="contactStatusId" value="<%=PDCodeTableConstants.ASP_CS_CONTACT_INACTIVE%>">
																				<input type=radio onclick="javascript:showContactButtons('<bean:write name="contactIndex" property="contactId"/>','<bean:write name="contactIndex" property="contactStatusId"/>' );" name="selectedContact" id="contactRadio<%=RecordCounter3%>" value="<bean:write name="contactIndex" property="contactId"/>"/> 
																			</logic:notEqual>
																		</logic:notEqual>
																	 </td>
																</jims2:isAllowed>
																<td>
																	<jims2:isAllowed requiredFeatures="<%=Features.CS_ASP_UPDATE_CSC%>">	 
																		<a href='/<msp:webapp/>displayCSCContactUpdate.do?submitAction=View&selectedValue=<bean:write name="contactIndex" property="contactId"/>'>
																			<msp:formatter name="contactIndex" property="contactName" format="L, F M"/>
																		</a>                                		                                
																	</jims2:isAllowed>                                   
																	<jims2:isAllowed requiredFeatures="<%=Features.CS_ASP_UPDATE_CSC%>">	 
																	</jims2:isAllowed>
																</td>
																<td align="center">
																	<bean:write name="contactIndex" property="jobTitle"/>
																</td>
																<td>
																	<a href='mailto:<bean:write name="contactIndex" property="contactEmail"/>'><bean:write name="contactIndex" property="contactEmail"/> </a>
																</td>
																<td>
																	<msp:formatter name="contactIndex" property="contactOfficePhoneNumber" format="A-P-F X"/> &nbsp; 
																</td>
																<td>
																	<bean:write name="contactIndex" property="contactStatusDesc" />
																</td>
															</tr>
														</logic:iterate>
													</table>
<!-- END CONTACTS DETAILS TABLE -->													
												</td>
											</tr>
											<tr>
												<td align="center">
<!-- BEGIN CONTACTS DETAILS BUTTON TABLE -->												
													<table cellpadding="2" cellspacing="0">
														<tr>
															<td>
																<input type="hidden" name="selContactVal" value=""/>
																<logic:notEqual name="cscServiceProviderForm" property="statusId" value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_INACTIVE%>">				                        
																	<jims2:isAllowed requiredFeatures="<%=Features.CS_ASP_UPDATE_CSC%>">	 
																		<input type="button"  class="button" value="Create" onclick="javascript:goToURLCustom('/<msp:webapp/>displayCSCContactUpdate.do?submitAction=Create', document.forms[0].selContactVal.value);"></input>
																		<span id="updateContactbutton" class="hidden"> <input type="button" class="button" value="Update" onclick="javascript:goToURLCustom('/<msp:webapp/>displayCSCContactUpdate.do?submitAction=Update', document.forms[0].selContactVal.value);"></input></span>
																	</jims2:isAllowed>
																	<span id="inactivateContactbutton" class="hidden"> <input type="button"  class="button" value="Inactivate" onclick="javascript:goToURLCustom('/<msp:webapp/>displayCSCContactUpdate.do?submitAction=Inactivate', document.forms[0].selContactVal.value);"></input></span>
																</logic:notEqual>
															</td>
														</tr>
													</table>
<!-- END CONTACTS DETAILS BUTTON TABLE -->													
												</td>
											</tr>
										</table>
<!-- END CONTACTS BORDER TABLE -->										
									</td>
								</tr>
							</table>
<!-- END CONTACTS DETAILS TABLE -->							
							<br>
<!-- BEGIN BUTTON TABLE -->
							<table border="0">
								<tr>
									<td>
										<html:submit property="submitAction"><bean:message key="button.back" /></html:submit>
										<logic:notEqual name="cscServiceProviderForm" property="statusId" value="<%=PDCodeTableConstants.ASP_CS_SERVPROV_ACTIVE %>">			
											<jims2:isAllowed requiredFeatures="<%=Features.CS_ASP_UPDATE_CSC%>">	                        
												<html:submit property="submitAction"><bean:message key="button.updateSP" /></html:submit>
											</jims2:isAllowed>
										</logic:notEqual>
										<html:submit property="submitAction"><bean:message key="button.cancel" /></html:submit>
									</td>
								</tr>
							</table>
 <!-- END BUTTON TABLE -->
						</td>
					</tr>
				</table>
<!-- END BLUE BORDER TABLE -->				
				<br>
			</td>
		</tr>
	</table>
</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>