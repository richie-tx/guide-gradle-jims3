<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--MODIFICATIONS -->
<!-- 01/28/2008 Debbie Williamson - Converted PT to JSP -->
<!-- 07/19/2010 Richard Capestani - added Apt. and aptnum to display in service provider address -->
<!-- 07/12/2011 R Young - #68612 Referral Date - make this an Entry/Update field-UI and validation-->
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp"%>

<%@ page import="naming.CSAdministerServiceProviderConstants" %>
<%@ page import="naming.CSAdministerProgramReferralsConstants"%>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - admininsterCaseloadCSCD/programReferrals/programSelect.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/case_util.js"></script>
<script language="JavaScript" src="/<msp:webapp/>js/sorttable.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>

<script>

	var cal1 = new CalendarPopup();
	cal1.showYearNavigation();
	function setPeriod(val)
	{
		period=val;
	}
function validateProgramSelection()
{
	var isProgramsDisplayed = false;
	var programSelected = false;
	
	var refTypesSize = document.getElementsByName("referralTypeSize")[0].value;

	var arr = [];
	
	for(var i=1; i <= refTypesSize; i++)
	{
		var elementName = "programRefType" + i;

		if((document.getElementsByName(elementName)[0] != null))
		{
			isProgramsDisplayed = true;
			
			for(var j =0; j < document.getElementsByName(elementName).length ; j++)
			{
				if(document.getElementsByName(elementName)[j].checked)
				{
					var selectedProgLocId = document.getElementsByName(elementName)[j].value;
			
					if(selectedProgLocId != null)
					{
						arr[i-1]=selectedProgLocId;
						programSelected = true;
					}
				}
			}
		}
	}	

	if((isProgramsDisplayed) && (!programSelected))
	{
		alert("At least 1 program (via program location selection) must be selected.");
		return false;
	}
	
	document.getElementsByName("selectedPrgmLocIds")[0].value=arr;
	
	return true;
}

function checkForSingleResult() {
    var rbs = document.getElementsByName("programRefType1");
	if (rbs.length == 1){
		rbs[0].checked = true;
	}	
}

function validateReferralDate(){

	var begdt = document.forms[0].referralDateAsStr.value;
	if ( begdt == "" ){
	    alert("Referral Date is Required.");
	    document.forms[0].referralDateAsStr.focus();
	    return false;
    }
	var curDateTime = new Date();
    var begDateTime = new Date( begdt );
    if ( begDateTime > curDateTime ){
	    alert("Referral Date can not be future date.");
	    document.forms[0].referralDateAsStr.focus();
	    return false;
    }
    return true;
}

</script>


</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);" onload="checkForSingleResult()">
<html:form action="/displayProgRefProgramSelect" target="content">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
	</tr>
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--tabs start-->
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="caseloadTab" />
						</tiles:insert> 
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<!--header area start-->
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td bgcolor="#cccccc" colspan="2">
									<!--header start-->
									<tiles:insert page="../../common/caseloadHeaderCase.jsp" flush="true">
									</tiles:insert> 
									<!--header end-->
								</td>
							</tr>
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
						<!--header area end-->
						<!--casefile tabs start-->
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<!--tabs start-->
									<tiles:insert page="../../common/caseloadCSCDSubTabs.jsp" flush="true">
										<tiles:put name="tab" value="ProgramReferralsTab" />
									</tiles:insert>
									<!--tabs end-->
								</td>
							</tr>
							<tr>
								<td bgcolor="#33cc66"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
						</table>
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
									<!-- BEGIN HEADING TABLE -->
									<table width="100%">
										<tr>
											<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp;-
												<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_UPDATE_INIT%>">
													<bean:message key="title.update"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|11">
												</logic:equal>
												<logic:equal name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_CREATE%>">
													<bean:message key="prompt.initiate"/><input type="hidden" name="helpFile" value="commonsupervision/CSCD_Program_Referrals/CSCD_Program_Referrals.htm#|5">
												</logic:equal> 
												<bean:message key="prompt.programReferral"/>&nbsp;-&nbsp;<bean:message key="prompt.select"/>&nbsp;<bean:message key="prompt.program"/></td>
										</tr>
									</table>
									<!-- END HEADING TABLE -->
									<%-- BEGIN ERROR TABLE --%>
									<table width="98%" align="center">
										<tr>
											<td align="center" class="errorAlert"><html:errors></html:errors></td>
										</tr>
									</table>
									<!-- END ERROR TABLE -->
									<logic:notEqual name="cscProgRefForm" property="action" value="<%=CSAdministerProgramReferralsConstants.ACTION_UPDATE_INIT%>">
										<table width="98%" align="center">
											<tr>
												<td align="center">
													<logic:present name="cautionMsg" >
														<div>
															<span class="cautionText" style='font-weight:bold; width:98%'><bean:write name="cautionMsg" /></span>
														</div>
													</logic:present>
												</td>
											</tr>
										</table>	
									</logic:notEqual>							
									<table width="98%" border="0" cellpadding="2" cellspacing="1" align="center">
										<tr>
											<td>
												<ul>
													<li>Select program locations and click Next.</li>
													<li>Click the envelope next to the Program Identifier to provide feedback on that program.</li>
												</ul>
											</td>
										</tr>
										<tr>
											<td class="required"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
										</tr>
									</table>
									
							<!--Conditions List start-->
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td  colspan="2"><!--conditions list tile start-->
									<tiles:insert page="prConditionsListTile.jsp" flush="true">
										<tiles:put name="condList" beanName="cscProgRefForm"
											beanProperty="conditionsList"></tiles:put>
									</tiles:insert> <!--conditions list tile end--></td>
								</tr>
							</table>
							<!--Conditions List end-->
									<div class="spacer4px"></div>
									<html:hidden name="cscProgRefForm" property="selectedPrgmLocIds" />
									<table width="98%" cellpadding="0" cellspacing="0" border="0">
										<tr>
											<td width="100%" valign="top">
												<table width="100%" cellpadding="2" cellspacing="0" class="borderTableBlue">
													<tr class="detailHead">
														<td><bean:message key="prompt.programReferral"/>&nbsp;<bean:message key="prompt.info"/></td>
													</tr>
													<tr>
														<td>
															<% int ReferralTypeCounter=0; %>
															<table width="100%" cellpadding="4" cellspacing="1">
																<tr>
																	<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.referralTypes"/></td>
																	<td colspan="3" class="formDe">
																		<div>
																		   <logic:iterate id="referralsIndex" name="cscProgRefForm" property="selectedReferralTypesList">
																		   	  <% ReferralTypeCounter++; %>
																			  <bean:write name="referralsIndex" property="referralTypeNum" />.&nbsp;
																			  <bean:write name="referralsIndex" property="referralTypeDesc" />&nbsp;
																			  <logic:equal name="referralsIndex" property="notProgressedForSP" value="true">
																			  	<img src="/<msp:webapp/>images/circledXicon.png" title="Referral Type Not Progressed" border="0" hspace="4">
																			  </logic:equal>
																			  </br>
																		   </logic:iterate> 
																		  <input type="hidden" name="referralTypeSize" value="<%= ReferralTypeCounter %>" /> 
																	   </div>
																	 </td>
																</tr>
																<tr>
																	<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.referralDate"/></td>
																	<td colspan="3" class="formDe">
																		<html:text name="cscProgRefForm" property="referralDateAsStr" size="10" maxlength="10"  title="(mm/dd/yyyy)"></html:text>
																		<A HREF="#" onClick="cal1.select(referralDateAsStr,'anchor3','MM/dd/yyyy'); return false;" NAME="anchor3" ID="anchor3"><bean:message key="prompt.3.calendar" /></A>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<div class="spacer4px"></div>
							 <nest:iterate id="spIndex" name="cscProgRefForm" property="selectedSPList">
									<table width="98%" cellpadding="0" cellspacing="0" border="0">
										<tr>
											<td width="100%" valign="top">
												<table width="100%" cellpadding="2" cellspacing="0" class="borderTableBlue">
													<tr class="detailHead">
														<td><a href="javascript:openWindow('/<msp:webapp/>displayCSCServiceProviderUpdate.do?submitAction=View Details&selectedValue=<bean:write name="spIndex" property="serviceProviderId" />')"><bean:write name="spIndex" property="serviceProviderName" /></a>&nbsp;-&nbsp;<bean:message key="prompt.programs"/>&nbsp;<bean:message key="prompt.list"/></td>
													</tr>
													<tr>
														<td>
															<table width="100%" cellpadding="2" cellspacing="1" border=0>
																<tr class="formDeLabel">
																	<td width="35%" colspan="2"><bean:message key="prompt.identifier"/></td>
																	<td width="40%"><bean:message key="prompt.name"/></td>
																	<td width="5%" title="Referral Type"><bean:message key="prompt.refType"/></td>
																	<td width="5%"><bean:message key="prompt.CSTSCode"/></td>
																	<td width="15%"><bean:message key="prompt.languagesOffered"/></td>
																	<td width="5%" nowrap><bean:message key="prompt.sexSpecific"/></td>
																	<td width="5%"><bean:message key="prompt.contractProgram"/></td>
																</tr>
															<% int RecordCounter2=0;
															   String bgcolor2=""; %>
															 <logic:iterate id="programIndex" name="spIndex" property="serviceProviderPrograms" >
																<tr class= '<% RecordCounter2++;
																	  bgcolor2 = "alternateRow";                      
																	  if (RecordCounter2 % 2 == 1)
																		  bgcolor2 = "normalRow";
																	   out.print(bgcolor2); %>'>
																	<td colspan="2" class="bottomBorder">
																		
																		<a href="javascript: openWindow('/<msp:webapp/>displayCSCProgramUpdate.do?submitAction=View Details&selectedValue=<bean:write name="programIndex" property="programId"/>')"><bean:write name="programIndex" property="programIdentifier" /></a>
																		<a href="javascript:openWindow('/<msp:webapp/>handleTask.do?submitAction=Link')"><img src="/<msp:webapp/>images/envelopeIcon.gif" title="Report Issue" border=0></a>
																		<logic:equal name="programIndex" property="programStatusId" value="<%= CSAdministerServiceProviderConstants.UNDER_INVESTIGATION_PROG_STATUS %>">
																			<img src="/<msp:webapp/>images/clip_image001.gif" title="Under Investigation" border="0" hspace="4">
																		</logic:equal> 
																	</td>
																	<td class="bottomBorder"><bean:write name="programIndex" property="programName" />&nbsp;</td>
																	<td align="center" class="bottomBorder"><b><bean:write name="programIndex" property="referralTypeNum" /></b>&nbsp;</td>
																	<td class="bottomBorder"><bean:write name="programIndex" property="cstsCode" />&nbsp;</td>
																	<td class="bottomBorder">
																		<logic:iterate id="eachLanguage" name="programIndex" property="languagesOffered" >
																			<bean:write name="eachLanguage" />
																		</logic:iterate>
																		&nbsp;
																	</td>
																	<td class="bottomBorder"><bean:write name="programIndex" property="sexSpecificDesc" />&nbsp;</td>
																	<td class="bottomBorder"><bean:write name="programIndex" property="contractProgramDesc" />&nbsp;</td>
																</tr>
															 <logic:iterate id="locationIndex" name="programIndex" property="programLocationList">
																<tr class='<%out.print(bgcolor2);%>'>
																	<td width="1%">
																		<bean:define id="location_id" name="locationIndex" property="locationId" type="java.lang.String"/>
																		<logic:equal name="locationIndex" property="selected" value="true">
																			<input type="radio" name="programRefType<bean:write name='programIndex' property='referralTypeNum' />" value="<bean:write name='programIndex' property='programId'/>-<bean:write name='locationIndex' property='locationId'/>" checked/>
																		</logic:equal>
																		<logic:equal name="locationIndex" property="selected" value="false">
																			<input type="radio" name="programRefType<bean:write name='programIndex' property='referralTypeNum' />" value="<bean:write name='programIndex' property='programId'/>-<bean:write name='locationIndex' property='locationId'/>" />
																		</logic:equal>
																	</td>
																	<td colspan="2"><div><bean:write name="locationIndex" property="streetNumber" />
                                                                                       <bean:write name="locationIndex" property="streetName" />
                                                                                       <bean:write name="locationIndex" property="streetTypeCd" />
                                                                                       <logic:notEqual name="locationIndex" property="aptNum" value="">
                                                                                       Apt/Suite <bean:write name="locationIndex" property="aptNum" /></logic:notEqual>
                                                                                    </div>
                                                                                  <div><bean:write name="locationIndex" property="city" />,
                                                                                       <bean:write name="locationIndex" property="state" />
                                                                                       <bean:write name="locationIndex" property="zipCode" /></div></td>
																	<td nowrap colspan="5">
																		<div>
																			<logic:notEqual name="locationIndex" property="locationPhone.formattedPhoneNumber" value="">
																				<bean:write name="locationIndex" property="locationPhone.formattedPhoneNumber" />&nbsp;ph<br>
																			</logic:notEqual>
																			<logic:notEqual name="locationIndex" property="locationFax.formattedPhoneNumber" value="">
																				<bean:write name="locationIndex" property="locationFax.formattedPhoneNumber" />&nbsp;f
																			</logic:notEqual>
																		&nbsp;
																		</div>
																	</td>
																</tr>
															 </logic:iterate>
														   </logic:iterate>
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<div class="spacer4px"></div>
								  </nest:iterate>
								  
								  <logic:equal name="cscProgRefForm" property="userEnteredServiceProvider" value="true">
										<table width="98%" cellpadding="0" cellspacing="0" border="0">
											<tr>
												<td width="100%" valign="top">
													<table width="100%" cellpadding="2" cellspacing="0" class="borderTableBlue">
														<tr class="detailHead">
															<td>
																<bean:write name="cscProgRefForm" property="userEnteredServiceProviderName" />
															</td>
														</tr>
														<tr>
															<td>
																<table width="100%" cellpadding="2" cellspacing="1">
																	<tr>
																		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.referralType"/></td>
																		<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.phoneNo"/>/<bean:message key="prompt.fax"/></td>
																	</tr>
																	<tr>
																		<td class="formDe"><bean:write name="cscProgRefForm" property="userEnteredServiceProviderRefTypeDesc" /></td>
																		<td class="formDe">
																			<div>
																				<logic:notEqual name="cscProgRefForm" property="userEnteredSPPhone.formattedPhoneNumber" value="">
																					<bean:write name="cscProgRefForm" property="userEnteredSPPhone.formattedPhoneNumber" />&nbsp;ph
																				</logic:notEqual>
																			</div>
																			<div>
																				<logic:notEqual name="cscProgRefForm" property="userEnteredSPFax.formattedPhoneNumber" value="">
																					<bean:write name="cscProgRefForm" property="userEnteredSPFax.formattedPhoneNumber" />&nbsp;f
																				</logic:notEqual>
																			</div>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</logic:equal>
									    
									<div class="spacer"></div>
									<div id="legendArea"><img src="/<msp:webapp/>images/clip_image001.gif" title="Under Investigation" border="0" hspace="4"> indicates programs that are Under Investigation </div>
									<div id="legendArea"><img src="/<msp:webapp/>images/circledXicon.png" title="Referral Type Not Progressed" border="0" hspace="4"> referral type not progressed </div>
									<!-- BEGIN BUTTON TABLE -->
									<table border="0" cellpadding="2" cellspacing="1">
										<tr>
											<td align="center">
												<html:submit property="submitAction" onclick="return validateProgramSelection() && validateReferralDate();"> <bean:message key="button.next" /></html:submit>
												<html:submit property="submitAction" onclick="return validateProgramSelection();"> <bean:message key="button.scheduleDateTime" /></html:submit>
												<html:reset><bean:message key="button.reset" /></html:reset>
											</td>
										</tr>
										<tr>
											<td align="center">
												<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
												<html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
											</td>
										</tr>
									</table>
									<!-- END BUTTON TABLE -->
								</td>
							</tr>
						</table>
						<div class="spacer4px"></div>
					</td>
				</tr>
			</table>
			<br>
		</td>
	</tr>
</table>
<br>
<!--casefile tabs end-->
<!-- END  TABLE -->
</div>
<br>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
