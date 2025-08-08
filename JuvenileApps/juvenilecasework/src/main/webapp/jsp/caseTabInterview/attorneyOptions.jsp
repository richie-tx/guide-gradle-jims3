<!DOCTYPE HTML>

<%--MODIFICATIONS --%>
<%-- 10/19/2006		AWidjaja Create JSP--%>
<%-- 11/2/2012		DGibler - JIMS200073746 - MJCW: Add Street Number suffix field (PD)-DAG-%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="ui.common.UIUtil" %>
<%@ page import="ui.juvenilecase.form.JuvenileMemberForm.MemberAddress" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>




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

<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- attorneyOptions.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- Javascript for emulated navigation --%>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/AnchorPosition.js"></script>

<script type='text/javascript'>
function selectOneFinancialOnly(theCheckBox)
{
	guardian1 = theCheckBox.form["guardianEmploymentRecord[0].financialInfoSelected"];
	guardian2 = theCheckBox.form["guardianEmploymentRecord[1].financialInfoSelected"];
	
	if(guardian2 == null)
	{
		guardian1.checked = true;
	}
	else
	{
		guardian1.checked = false;
		guardian2.checked = false;
		theCheckBox.checked = !theCheckBox.checked;
	}
}

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
</head>

<html:form action="/handleAttorneyOptions" target="content">
<logic:equal name="state" value="requestAttorneyAppointment">
   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|127">
</logic:equal>
<logic:equal name="state" value="summary">
   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|128">
</logic:equal>
<logic:equal name="state" value="confirm">
   <input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|127">
</logic:equal>

 
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);" stopmargin='0' leftmargin="0"> 


<%-- BEGIN HEADING TABLE --%> 
<table width='100%'> 
  <tr> 
  	<td align="center" class="header">
	    <bean:message key="title.juvenileCasework"/> - 
  		<bean:message key="title.mjcwConductInterview"/>
  		<logic:present name="state">
  			<logic:equal name="state" value="requestAttorneyAppointment">
  				- Request Attorney Appointment
  			</logic:equal>
  			<logic:equal name="state" value="summary">
  				- Request Attorney Appointment Summary
  			</logic:equal>
  			<logic:equal name="state" value="confirm">
  				- Request Attorney Appointment Confirmation
  			</logic:equal>
  		</logic:present>
  	</td>
  </tr> 
  <tr>
  	<td align="center" class="errorAlert"><html:errors property="<%=ActionErrors.GLOBAL_MESSAGE%>"/></td> 
  </tr>
</table> 
<%-- END HEADING TABLE --%> 

<%-- BEGIN INSTRUCTION TABLE --%> 
<div class='spacer'></div> 
<table width="98%" border="0"> 
<tr> 
	<td> 
		<ul> 
			<li>Review information entered and click appropriate button to continue.</li>
	        <li>To change information entered click Back button.</li>
		</ul>
	</td>
</tr> 
</table> 
<%-- END INSTRUCTION TABLE --%> 

<div class='spacer'></div>

<%-- BEGIN HEADER INFO TABLE --%>
<tiles:insert page="../caseworkCommon/juvenileHeaderDetails.jsp" flush="true">
<tiles:put name="headerType" value="casefileheader"/>
</tiles:insert>
<%-- END HEADER INFO TABLE --%>

<div class='spacer'></div>  
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" >
<tr>
	<td valign='top'>
		<%-- BEGIN TAB --%>
  	<tiles:insert page="../caseworkCommon/casefileTabs.jsp" flush="true">
  		<tiles:put name="tabid" value="casefiledetailstab"/>
  		<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
  	</tiles:insert>				

		<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
			<tr>
			  <td valign='top' align='center'>
			  
			  <%-- BEGIN CASEFILE INFO TABS INNER TABLE --%>
     			<div class='spacer'></div>
    		  <table width='98%' border="0" cellpadding="0" cellspacing="0">
    				<tr>
    					<td>
    						<table width='100%' border="0" cellpadding="0" cellspacing="0" >
    							<tr>
    								<td valign='top'>
    								<%--tabs start--%>
    									<tiles:insert page="../caseworkCommon/casefileInfoTabs.jsp" flush="true">
    										<tiles:put name="tabid" value="interviewtab"/>
    												<tiles:put name="casefileid" value='<%= request.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM) %>' />
    									</tiles:insert>	
    								<%--tabs end--%>
    								</td>
    							</tr>
    						  <tr>
	          				<td bgcolor='#33cc66'>
	          					<table border='0' cellpadding='2' cellspacing='1'>
	          						<tr>
	          							<td>&nbsp;<a href='/<msp:webapp/>displayJuvInterviewList.do?submitAction=Link'><bean:message key="prompt.viewInterviews"/></a> <b>|</b> </td>
	          							<td>&nbsp;<a href='/<msp:webapp/>displayReportHistory.do?submitAction=Link'><bean:message key="prompt.viewReportHistory"/></a>  </td>
	          						</tr>
	          					</table>
	            			</td>
	          	    </tr>
						  	</table>

								<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
									<tr>
										<td valign='top' align='center'><%-- END TAB TABLE --%>
		              		<%int RecordCounter=0;%>
		              		<% int refCounterVal=0; %>
		              		<% String bgcolor=""; %>
		              					
		              		<%-- BEGIN BORDER TABLE BLUE TABLE --%>
			
		        					<%RecordCounter=0;%>
		        					<%refCounterVal=0; %>
		        					<logic:notEmpty name="juvenileInterviewForm" property="guardianEmploymentRecord">
		        					<nested:iterate id="guardiansList" name="juvenileInterviewForm" property="guardianEmploymentRecord">
		                                		
		        						<%refCounterVal++; %>
                        <div class='spacer'></div>
                        <table width='98%' cellspacing='0' cellpadding='2' border='0' class='borderTableBlue'> 
            							<tr> 
            								<td class="detailHead" valign='top'> <bean:message key="prompt.family"/> <bean:message key="prompt.constellation"/> <bean:message key="prompt.guardian"/> <bean:message key="prompt.information"/></td> 
            							</tr> 
            							<tr> 
            								<td align='center'> 
            									<table width='100%' cellspacing='1' cellpadding='4' border='0'> 
            										<tr> 
            											<td colspan='5'> <%-- Begin Member Inner Table 2 --%> 
            												<table width='100%' cellspacing='1' class='borderTableGrey'> 
            													<tr bgcolor='#cccccc'> 
            														<td valign='top' class='subHead' align="left"><bean:message key="prompt.member"/> #</td> 
                                        							<td valign='top' class='subHead' align="left"><bean:message key="prompt.name"/></td> 
                                       								<td valign='top' class='subHead' align="left"><bean:message key="prompt.relationship"/></td> 
                                       								<td valign='top' class='subHead' align="left"><bean:message key="prompt.deceased"/></td>
																	<td valign='top' class='subHead' align="left"><bean:message key="prompt.incarcerated"/></td>
	                                   							</tr> 
	              											  	<tr class="normalRow">
							                                        <td valign='top' align="left"><nested:write property="memberNumber"/></td> 
                            							            <td valign='top' align="left"><nested:write property="name.formattedName"/></td> 
                            							            <td valign='top' align="left"><nested:write property="relationshipToJuv"/></td> 
                                    							    <td valign='top' align="left"><nested:write property="deceased"/></td> 
																	<td valign='top' align="left"><nested:write property="incarcerated"/></td> 
            													</tr> 
            												</table> 
            												<%-- End Member Inner Table 2 --%> 
            											</td> 
            										</tr> 
            										<tr>
            											<td colspan='5'>
            												<table width='100%' border="0" cellpadding="4" cellspacing="0" class="borderTableBlue">
            										  			<tr>
            										  				<td valign='top' class='detailHead'>
            										  					<table width='100%' cellpadding='0' cellspacing='0'>
            										  						<tr>
            										  							<td class='detailHead'><bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.employment"/> <bean:message key="prompt.info"/></td>
            										  						</tr>
            										  					</table>
            										  				</td>
            										  			</tr>
            										  			<tr>
            										  				<td valign='top'>
              															<table width='100%' bgcolor="#cccccc" cellspacing="1"> 
								                                <tr bgcolor="#f0f0f0"> 
                												  <td class="subhead" valign="top" width="1%" align="left">Include</td>
								                                  <td class="subhead" valign="top" width="10%" align="left"><bean:message key="prompt.entryDate"/></td>
								                                  <td class="subhead" valign="top" width="20%" align="left"><bean:message key="prompt.employment"/> <bean:message key="prompt.status"/> </td> 
								                                  <td class="subhead" align="left"><bean:message key="prompt.current"/> <bean:message key="prompt.employer"/></td> 
								                                  <td class="subhead" valign="top" width="15%" align="left"><bean:message key="prompt.jobTitle"/></td> 
								                                  <td class="subhead" valign="top" width="10%" nowrap='nowrap' align="left"><bean:message key="prompt.annualGrossIncome"/></td> 
								                                </tr> 

                 																<nested:notEmpty property="employmentInfoList">
                  																<nested:iterate indexId='indexer' id="empInfoIter" property="employmentInfoList">
                  																	<jims:if name="state" value="requestAttorneyAppointment" op="equal">
                  																		<jims:or name="empInfoIter" property="selected" value="true" op="equal"/>
                  																		<jims:then>
                  																			<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
                  																				  <td align='center'>
                  																					<logic:equal name="state" value="requestAttorneyAppointment">
                  																						<nested:checkbox property="selected" value="true"/>
                  																					</logic:equal>
                  																					<logic:notEqual name="state" value="requestAttorneyAppointment">
                  																						<nested:equal property="selected" value="true">
                  																							<img src="/<msp:webapp/>images/green_check.gif" border='0'>
                  																						</nested:equal>
                  																					</logic:notEqual>
                  																				  </td>
                  																				  <td width='10%' align="left"><nested:write property="entryDate"/></td> 
                  																				  <td nowrap='nowrap' align="left"><nested:write property="employmentStatus"/> </td> 
                  																				  <td nowrap='nowrap' align="left"><nested:write property="currentEmployer"/>  </td> 
                  																				  <td nowrap='nowrap' align="left"><nested:write property="jobTitle"/> </td> 
                  																				  <td nowrap='nowrap' align="left"><bean:message key="currency.prefix"/><nested:write property="annualNetIncomeAsDouble" formatKey="currency.format"/></td> 
                  																			</tr>
                  																		</jims:then>
                  																	</jims:if>
											                            </nested:iterate>
									                             </nested:notEmpty>
																							</table>						
																	  				</td>
																	  			 </tr>
																	  		</table>
																			</td>
																		</tr>
										<%-- Only display financial info of guardians living with the juvenile --%>
										<nested:equal property="inHomeStatus" value="true">
											<nested:define id="abc" property="financialInfoSelected"/>
											<jims:if name="state" value="requestAttorneyAppointment" op="equal">
												<jims:or name="abc" value="true" op="equal"/>
												<jims:then>
												
													<tr> 
														<td valign='top' class='detailHead' colspan='4'>
															<logic:equal name="state" value="requestAttorneyAppointment">
																<nested:checkbox property="financialInfoSelected" value="true" onclick="selectOneFinancialOnly(this);"/>
															</logic:equal>	
															<bean:message key="prompt.family"/> <bean:message key="prompt.financialInformation"/> of <bean:message key="prompt.guardian"/>
														</td> 
													</tr>
													<tr>
														<td valign="top" colspan="4">
															<tiles:insert page="../caseworkCommon/familyFinancialInfo.jsp" flush="false">
																<tiles:put name="familyFinancialInfo" beanName="guardiansList" />
															</tiles:insert>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel" width="1%" nowrap='nowrap' colspan="1"><bean:message key="prompt.notes"/></td>
														<td colspan="3" class="formDe"><nested:write property="notes" /></td>
													</tr>
												</jims:then>
											</jims:if>		
										</nested:equal>
										<logic:present name="state">
											<tr>
												<td colspan='4' align='center'>
													<table width='100%' border="0" cellpadding="4" cellspacing="0" class="borderTableBlue">
											  			<tr>
											  				<td valign='top' class='detailHead'>
											  					<table width='100%' cellpadding='0' cellspacing='0'>
											  						<tr>
											  							<td class='detailHead'>&nbsp;<bean:message key="prompt.family"/> <bean:message key="prompt.member"/> <bean:message key="prompt.address"/> <bean:message key="prompt.info"/> - <nested:write property="name.formattedName"/></td>
											  						</tr>
											  					</table>
											  				</td>
											  			</tr>
											  			<tr id="pChar0">
											  				<td valign='top'>
																<table width="100%" bgcolor="#cccccc" cellspacing="1">
																		<tr bgcolor="#f0f0f0">
																			<td class="subhead" valign="top" width="1%" nowrap='nowrap' align="left"><bean:message key="prompt.entryDate"/></td>
																			<td class="subhead" valign="top" align="left"><bean:message key="prompt.addressType"/></td>
																			<td class="subhead" valign="top" align="left"><bean:message key="prompt.address"/></td>
																			<td class="subhead" valign="top" align="left"><bean:message key="prompt.county"/></td>
																			<td class="subhead" valign="top" align="left"><bean:message key="prompt.phone"/></td>
																		</tr>

																		<bean:define id="guardianAddressMap" name="juvenileInterviewForm" property="guardianAddressMap" type="java.util.HashMap"/>
																		<bean:define id="guardianMemberNumber" name="guardiansList" property="memberNumber" type="java.lang.String"/>
																		<logic:notEmpty name="guardianAddressMap">
  																		<logic:iterate indexId='indexer' id="keysIter" name="guardianAddressMap">
    																		<logic:equal name="keysIter" property="key" value="<%=guardianMemberNumber%>">
  																				 <tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
  																					<td valign="top" align="center" width="1%"><bean:write name="keysIter" property="value.entryDateString"/></td>
  																					<td valign="top" align="left"><bean:write name="keysIter" property="value.addressType"/></td>
  																					<td align="left"><bean:write name="keysIter" property="value.streetNumber"/>&nbsp;
  																						<bean:write name="keysIter" property="value.streetNumSuffix"/>&nbsp;
  																						<bean:write name="keysIter" property="value.streetName"/>&nbsp; 
  																						<bean:write name="keysIter" property="value.streetTypeId"/>&nbsp; 
  																						<bean:write name="keysIter" property="value.aptNumber"/>&nbsp; 
  																						<bean:write name="keysIter" property="value.city"/>,&nbsp;
  																						<bean:write name="keysIter" property="value.state"/> &nbsp;
  																						<bean:write name="keysIter" property="value.zipCode"/>
  																						<logic:notEmpty name="keysIter" property="value.additionalZipCode"> -<bean:write name="keysIter" property="value.additionalZipCode"/>
  																						</logic:notEmpty>
  																					</td>
  																					<td valign="top" align="left"><bean:write name="keysIter" property="value.county"/></td>
  																					<td valign='top' align="left">
            																			<bean:define id="guardianPhoneMap" name="juvenileInterviewForm" property="guardianPhoneMap" type="java.util.HashMap"/>
            																			<nested:define id="guardianNumber" property="memberNumber" type="java.lang.String"/>
            																			<logic:notEmpty name="guardianPhoneMap">
            																				<logic:iterate id="keysIter" name="guardianPhoneMap">
            																					<logic:equal name="keysIter" property="key" value="<%=guardianNumber%>">				
            																						<bean:write name="keysIter" property="value.formattedPhoneNumber"/>
            																					</logic:equal>
            																				</logic:iterate>
            																			</logic:notEmpty>
            																		</td> 
  																				</tr>
  																			</logic:equal>
 																			</logic:iterate>
																		</logic:notEmpty>
  																</table>			
  															</td>
  														</tr>
  													</table>
  												</td>
  											</tr>
  										</logic:present>					
  									</table>
  								</td> 
  							</tr> 
  							<%-- End Family Constellation Table --%> 
  						</table> 
	           </nested:iterate>	
           </logic:notEmpty>

					<logic:equal name="state" value="requestAttorneyAppointment">
						<input type="hidden" name="resetGuardianEmployment" value="true">
					</logic:equal>
						</td>
					</tr>

					<logic:notEmpty name="juvenileInterviewForm" property="stepParentEmploymentRecord">
					<nested:iterate id="stepParentIter" name="juvenileInterviewForm" property="stepParentEmploymentRecord">
					<tr>
						<td valign='top' align='center'>
						  <div class='spacer'></div>
							<table width='98%' cellspacing='0' cellpadding='2' border='0' class='borderTableBlue'> 
								<tr> 
									<td class="detailHead" valign='top'> Step-Parent <bean:message key="prompt.information"/></td> 
								</tr> 
								<tr> 
									<td align='center'> 
										<table width='100%' cellspacing='1' cellpadding='4' border='0'> 
											<tr> 
												<td colspan='5'> <%-- Begin Member Inner Table 2 --%> 
													<table width='100%' cellspacing='1' class='borderTableGrey'> 
														<tr bgcolor='#cccccc'> 
															<td valign='top' class='subHead' align="left"><bean:message key="prompt.member"/> #</td> 
															<td valign='top' class='subHead' align="left"><bean:message key="prompt.name"/></td> 
															<td valign='top' class='subHead' align="left"><bean:message key="prompt.relationship"/></td> 
															<td valign='top' class='subHead' align="left"><bean:message key="prompt.deceased"/></td>
															<td valign='top' class='subHead' align="left"><bean:message key="prompt.incarcerated"/></td>
														</tr> 
					  
														<tr class="normalRow">
															<td valign='top' align="left"><nested:write property="memberNumber"/></td> 
															<td valign='top' align="left"><nested:write property="name.formattedName"/></td> 
															<td valign='top' align="left"><nested:write property="relationshipToJuv"/></td> 
															<td valign='top' align="left"><nested:write property="deceased"/></td> 
															<td valign='top' align="left"><nested:write property="incarcerated"/></td> 
														</tr> 
													</table> 
													<%-- End Member Inner Table 2 --%> 
												</td> 
											</tr> 
											<tr>
												<td valign='top'>
													<table width='98%' border="0" cellpadding="4" cellspacing="0" class="borderTableBlue">
														<tr>
															<td valign='top' class='detailHead'>
																<table width='100%' cellpadding='0' cellspacing='0'>
																	<tr>
																		<td class='detailHead'>Step-parent <bean:message key="prompt.employment"/> <bean:message key="prompt.info"/>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr>
															<td valign='top'>
																<table width='100%' bgcolor="#cccccc" cellspacing="1">
																	<tr bgcolor="#f0f0f0"> 
																	  <td class="subhead" valign="top" width="1%" align="left">Include</td>
																	  <td class="subhead" valign="top" width="10%" align="left"><bean:message key="prompt.entryDate"/></td>
																	  <td class="subhead" valign="top" width="20%" align="left"><bean:message key="prompt.employment"/> <bean:message key="prompt.status"/> </td> 
																	  <td class="subhead" align="left"><bean:message key="prompt.current"/> <bean:message key="prompt.employer"/></td> 
																	  <td class="subhead" valign="top" width="15%" align="left"><bean:message key="prompt.jobTitle"/></td> 
																	  <td class="subhead" nowrap='nowrap' valign="top" width="10%" align="left"><bean:message key="prompt.annualGrossIncome"/></td> 
																	</tr> 
																	<%RecordCounter = 0;
																	bgcolor = "";%>
																	<nested:notEmpty property="employmentInfoList">
																	<nested:iterate indexId='indexer' id="empInfoIter" property="employmentInfoList">
																		<jims:if name="state" value="requestAttorneyAppointment" op="equal">
																			<jims:or name="empInfoIter" property="selected" value="true" op="equal"/>
																			<jims:then>
																				<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
																					  <td align='center'>
																						<logic:equal name="state" value="requestAttorneyAppointment">
																							<nested:checkbox property="selected" value="true"/>
																						</logic:equal>
																						<logic:notEqual name="state" value="requestAttorneyAppointment">
																							<nested:equal property="selected" value="true">
																								<img src="/<msp:webapp/>images/green_check.gif" border='0'>
																							</nested:equal>
																						</logic:notEqual>
																					  </td>
																					  <td width='10%' align="left">
																						<nested:write property="entryDate"/>
																					  </td> 
																					  <td nowrap='nowrap' align="left"><nested:write property="employmentStatus"/> </td> 
																					  <td nowrap='nowrap' align="left"><nested:write property="currentEmployer"/>  </td> 
																					  <td nowrap='nowrap' align="left"><nested:write property="jobTitle"/> </td> 
																					  <td nowrap='nowrap' align="left"><bean:message key="currency.prefix"/><nested:write property="annualNetIncomeAsDouble" formatKey="currency.format"/></td> 
																				</tr>
																			</jims:then>
																		</jims:if>
																	</nested:iterate>
																	</nested:notEmpty>
																</table>						
															</td>
														 </tr>
														 <tr>
															<td valign='top'>
																<table width="100%" bgcolor="#cccccc" cellspacing="1">
																		<tr bgcolor="#f0f0f0">
																			<td class="subhead" valign="top" width="1%" nowrap='nowrap' align="left"><bean:message key="prompt.entryDate"/></td>
																			<td class="subhead" valign="top" align="left"><bean:message key="prompt.addressType"/></td>
																			<td class="subhead" valign="top" align="left"><bean:message key="prompt.address"/></td>
																			<td class="subhead" valign="top" align="left"><bean:message key="prompt.county"/></td>
																			<td class="subhead" valign="top" align="left"><bean:message key="prompt.phone"/></td>
																		</tr>

																		<bean:define id="guardianAddressMap" name="juvenileInterviewForm" property="guardianAddressMap" type="java.util.HashMap"/>
																		<nested:define id="guardianMemberNumber" property="memberNumber" type="java.lang.String"/>
																		<logic:notEmpty name="guardianAddressMap">
																		<logic:iterate indexId='indexer' id="keysIter" name="guardianAddressMap">
																			
																			<logic:equal name="keysIter" property="key" value="<%=guardianMemberNumber%>">
																				<logic:notEmpty name="keysIter" property="value">
																				<bean:define id="addresses" name="keysIter" property="value"/>
																				
																				<logic:notEmpty name="addresses" property="memberAddressId">
																				 <tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>"> 
																					<td valign="top" align="center" width="1%">
																						<bean:write name="addresses" property="entryDateString"/>
																					</td>
																					<td valign="top" align="left"><bean:write name="addresses" property="addressType"/></td>
																					<td><bean:write name="addresses" property="streetNumber"/>&nbsp;
																						<bean:write name="addresses" property="streetName"/>&nbsp; 
																						<bean:write name="addresses" property="streetTypeId"/>&nbsp; 
  																						<bean:write name="addresses" property="aptNumber"/>&nbsp; 
  																						<bean:write name="addresses" property="city"/>,&nbsp;
																						<bean:write name="addresses" property="state"/> &nbsp;
																						<bean:write name="addresses" property="zipCode"/>
																						<logic:notEmpty name="addresses" property="additionalZipCode">
																						-<bean:write name="addresses" property="additionalZipCode"/>
																						</logic:notEmpty>
																					</td>
																					<td valign="top" align="left"><bean:write name="addresses" property="county"/></td>
																					<td valign='top' align="left">
            																			<bean:define id="guardianPhoneMap" name="juvenileInterviewForm" property="guardianPhoneMap" type="java.util.HashMap"/>
            																			<nested:define id="guardianNumber" property="memberNumber" type="java.lang.String"/>
            																			<logic:notEmpty name="guardianPhoneMap">
            																				<logic:iterate id="keysIter" name="guardianPhoneMap">
            																					<logic:equal name="keysIter" property="key" value="<%=guardianNumber%>">				
            																						<bean:write name="keysIter" property="value.formattedPhoneNumber"/>
            																					</logic:equal>
            																				</logic:iterate>
            																			</logic:notEmpty>
            																		</td> 
																				</tr>
																				</logic:notEmpty>
																				
																				</logic:notEmpty>
																			</logic:equal>
																		</logic:iterate>
																		</logic:notEmpty>
																</table>			
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>	
						</td>
					</tr>
					<tr><td><div class='spacer'></div></td></tr>
					</nested:iterate>
					</logic:notEmpty>

					<logic:notEqual name="state" value="attorneyOptions">
					<logic:notEmpty name="juvenileInterviewForm" property="nextCourtDate">
						<tr><td><div class='spacer'></div></td></tr>
						<tr>
						  <td colspan='4' align='center'>
								<table width='98%' cellspacing='1' class='borderTableGrey'>
								  <tr bgcolor="#cccccc">
										<td class="subhead" valign="top" align="left">Next Court Date</td>
										<td class="subhead" valign="top" align="left">Court Time</td>
										<td class="subhead" valign="top" align="left">Court ID</td>
								  </tr>
								  <tr class="normalRow" bgcolor="ffffff" id='row0'>
										<td valign="top" align="left"><bean:write name="juvenileInterviewForm" property="nextCourtDate.courtDate" formatKey="date.format.mmddyyyy"/></td>
										<td align="left"><bean:write name="juvenileInterviewForm" property="nextCourtDate.courtDate" formatKey="time.format.HHmm"/> </td>
										<td valign="top" align="left"><bean:write name="juvenileInterviewForm" property="nextCourtDate.courtName"/> </td>
								  </tr>
								</table>
							</td>
						</tr>
					</logic:notEmpty>
					</logic:notEqual>
					<logic:present name="state" >
						<tr><td><div class='spacer'></div></td></tr>
						<tr>
							<td align='center'>
								<table width='98%'>
									<tr>
										<td class='formDeLabel' valign='top'>Extenuating circumstances (e.g. if respondent is in the managing conservatorship of the Harris County Child Welfare Unit)&nbsp;
											<logic:equal name="state" value="requestAttorneyAppointment">
  											&nbsp;
              					<tiles:insert page="../caseworkCommon/spellCheckTile.jsp" flush="false">
              						<tiles:put name="tTextField" value="attorneyOptionsNotes"/>
              						<tiles:put name="tSpellCount" value="spellBtn1" />
            						</tiles:insert> 
											(Max. characters allowed: 255)
											</logic:equal>
										</td>
									</tr>

									<tr>
										<td class='formDe' colspan="3">
											<logic:equal name="state" value="requestAttorneyAppointment">
												<html:textarea name="juvenileInterviewForm" property="attorneyOptionsNotes" style="width:100%" rows="3" onmouseout="textLimit(this, 255);" onkeyup="textLimit(this, 255);" />
											</logic:equal>
											<logic:notEqual name="state" value="requestAttorneyAppointment">
												<bean:write name="juvenileInterviewForm" property="attorneyOptionsNotes"/>
											</logic:notEqual>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</logic:present>

					<tr>
						<td>
						 <div class='spacer'></div>
							<table border="0" width="100%">
								<logic:notPresent name="state">
									<tr>
										<td align="center">
											<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
											<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.requestAttorneyAppointment"/></html:submit>
											<html:submit property="submitAction"><bean:message key="button.hireAttorney"/></html:submit>
											<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
										</td>
									</tr>
								</logic:notPresent>
								<logic:present name="state">
									<logic:equal name="state" value="requestAttorneyAppointment">
										<tr>
											<td align="center">
												<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.next"/></html:submit>
												<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
											</td>
										</tr>
									</logic:equal>
									<logic:equal name="state" value="summary">
										<tr>
											<td align="center">
												<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
												<html:submit property="submitAction"><bean:message key="button.generateDocument"/></html:submit>
												<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
											</td>
										</tr>
									</logic:equal>
									<logic:equal name="state" value="confirm">
										<tr>
											<td align="center">
												<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.backToSocialHistoryData"/></html:submit>
												<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
											</td>
										</tr>
									</logic:equal>
								</logic:present>
							</table>
							 </td>
                </tr>
                </table>
                <div class='spacer'></div>
               </td>
              </tr>
            </table>			
  				</td>
  			</tr>
  		</table>
  	</td>
  </tr>
</table>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div></body>
									
</html:form>
</html:html>									