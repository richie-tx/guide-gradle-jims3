<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 04/20/2006	 Hien Rodriguez - Create JSP -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->
<!-- 10/23/2010  CShimek        - #67526 added SNU display -->
<!-- 11/15/2010  DGibler        - #37526 changed name ptr to name seq num -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

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
<title><bean:message key="title.heading" /> - outOfCountyCase/details.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitOutOfCountyCaseCreateUpdate" target="content">
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
						<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
							<tiles:put name="tab" value="setupTab"/>
						</tiles:insert>		
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
						<table width="98%" border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign="top">
									<tiles:insert page="../common/manageFeaturesTabs.jsp" flush="true">
										<tiles:put name="tabid" value="oocTab"/>
									</tiles:insert>	
								</td>
							</tr>
							
						</table>
						<table width="98%" border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
							</tr>
							<tr>
								<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
									<table width="98%">
										<tr>
											<td align="center" class="header">
												<logic:equal name="outOfCountyCaseForm" property="action" value="view">
													<input type="hidden" name="helpFile" value="commonsupervision/ooc/out_of_county_case.htm#|5">
													<bean:message key="prompt.view" />&nbsp;<bean:message key="title.outOfCountyCase" />
												</logic:equal>
												<logic:equal name="outOfCountyCaseForm" property="action" value="create">
													<input type="hidden" name="helpFile" value="commonsupervision/ooc/out_of_county_case.htm#|7">
													<bean:message key="prompt.create" />&nbsp;<bean:message key="title.outOfCountyCase" />&nbsp;<bean:message key="prompt.summary" />
													<tr>
														<td>
															<ul>
																<li>Verify that information is correct and select Finish button to create Out of County Case.</li>
																<li>If any changes are needed, select Back button.</li>
															</ul>
														</td>
													</tr>
												</logic:equal>
												<logic:equal name="outOfCountyCaseForm" property="action" value="update">
													<input type="hidden" name="helpFile" value="commonsupervision/ooc/out_of_county_case.htm#|10">
													<bean:message key="prompt.update" />&nbsp;<bean:message key="title.outOfCountyCase" />&nbsp;<bean:message key="prompt.summary" />
													<tr>
														<td>
															<ul>
																<li>Verify that information is correct and select Finish button to create Out of County Case.</li>
																<li>If any changes are needed, select Back button.</li>
															</ul>
														</td>
													</tr>
												</logic:equal>
												<logic:equal name="outOfCountyCaseForm" property="action" value="reactivate">
													<input type="hidden" name="helpFile" value="commonsupervision/ooc/out_of_county_case.htm#|13">
													<bean:message key="prompt.reactivate" />&nbsp;<bean:message key="title.outOfCountyCase" />&nbsp;<bean:message key="prompt.summary" />
													<tr>
														<td>
															<ul>
																<li>Verify that information is correct and select Finish button to save changes.</li>
																<li>If any changes are needed, select Back button.</li>
															</ul>
														</td>
													</tr>
												</logic:equal>					
												<logic:equal name="outOfCountyCaseForm" property="action" value="confirmCreate">
													<input type="hidden" name="helpFile" value="commonsupervision/ooc/out_of_county_case.htm#|8">
													<bean:message key="prompt.create" />&nbsp;<bean:message key="title.outOfCountyCase" />&nbsp;<bean:message key="prompt.confirmation" />										
													<tr>
														<td class="confirm"><bean:message key="prompt.caseNum" />&nbsp;<bean:write name="outOfCountyCaseForm" property="caseNum" /> has been generated.</td> 
													</tr>
												</logic:equal>
												<logic:equal name="outOfCountyCaseForm" property="action" value="confirmUpdate">
													<input type="hidden" name="helpFile" value="commonsupervision/ooc/out_of_county_case.htm#|11">
													<bean:message key="prompt.update" />&nbsp;<bean:message key="title.outOfCountyCase" />&nbsp;<bean:message key="prompt.confirmation" />										
													<tr>
														<td class="confirm">Case Information successfully updated.</td>
													</tr>
												</logic:equal>
												<logic:equal name="outOfCountyCaseForm" property="action" value="confirmReactivate">
													<input type="hidden" name="helpFile" value="commonsupervision/ooc/out_of_county_case.htm#|14">
													<bean:message key="prompt.reactivate" />&nbsp;<bean:message key="title.outOfCountyCase" />&nbsp;<bean:message key="prompt.confirmation" />										
													<tr>
														<td class="confirm">Case successfully reactivated.</td>
													</tr>
												</logic:equal>
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
<!-- BEGIN DETAIL HEADER TABLE -->										
									<tiles:insert page="partyInfoHeaderTile.jsp" flush="true">
										<tiles:put name="partyHeader" beanName="outOfCountyCaseForm"/>
									</tiles:insert>	
<!-- END DETAIL HEADER TABLE -->
<!-- BEGIN DETAIL TABLE -->
                        			<table width="100%" border="0" cellpadding="0" cellspacing="0">
                          				<tr>
                            				<td align="center">
                              					<table border="0" width="98%" cellspacing="1" cellpadding="2">
                              					<!-- BEGIN CASE IDENTIFICATIONS SECTION -->
                                					<tr>
                                  						<td colspan="4" class="detailHead"><bean:message key="prompt.case" />&nbsp;<bean:message key="prompt.identifications" /></td>
                                					</tr>
					                                <tr>
					                                  	<td class="formDeLabel"><bean:message key="prompt.CDI" /></td>
					                                  	<td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="courtDivision" /></td>
					                                </tr>
					                                <logic:notEqual name="outOfCountyCaseForm" property="action" value="create">
					                                	<tr>
															<td class="formDeLabel"><bean:message key="prompt.case#"/></td>
					                                  		<td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="caseNum" /></td>
														</tr>
													</logic:notEqual>												
                                					<tr>
					                                  	<td class="formDeLabel"><bean:message key="prompt.CJIS#" /></td>
					                                  	<td class="formDe"><bean:write name="outOfCountyCaseForm" property="cjis" />
					                                  	<td class="formDeLabel"><bean:message key="prompt.nameSeqNum" /></td>
				                                  		<td class="formDe"><bean:write name="outOfCountyCaseForm" property="nameSeqNum" /></td>	
					                                </tr>
                                					<tr>
						                            	<td class="formDeLabel"><bean:message key="prompt.offense" /></td>
						                                <td class="formDe" colspan="3">
						                                	<bean:write name="outOfCountyCaseForm" property="offenseId" />
					                                  		- <bean:write name="outOfCountyCaseForm" property="offense" />
					                                  	</td>
                                  					</tr>
                                					<tr>
                                  						<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.outOfCountyCase" /><bean:message key="prompt.type" /></td>
                                  						<td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="caseType" /></td>
						                            </tr>
						                        <!-- END CASE IDENTIFICATIONS SECTION -->
                      								<tr><td><br></td></tr>
                                				<!-- BEGIN CASE INFORMATION SECTION -->
						                            <tr>
						                               	<td class="detailHead" colspan="4"><bean:message key="prompt.case" /> <bean:message key="prompt.information" /></td>
						                            </tr>
						                            <tr>
                                  						<td class="formDeLabel"><bean:message key="prompt.disposition" /> <bean:message key="prompt.type" /></td>
						                                <td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="dispositionType" /></td>
						                          	</tr>
					                                <tr>
					                                  	<td class="formDeLabel"><bean:message key="prompt.disposition" /> <bean:message key="prompt.date" /></td>
					                                  	<td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="dispositionDateAsString" /></td>
					                                </tr>
					                                <logic:equal name="outOfCountyCaseForm" property="dispositionTypeId" value="PROB">
						                                <tr>
						                                  	<td class="formDeLabel" ><bean:message key="prompt.confinement" /> <bean:message key="prompt.length" /></td>
						                                  	<td class="formDe" colspan="3">
						                                  		<bean:write name="outOfCountyCaseForm" property="confinementLengthYear" /> <bean:message key="prompt.years" />&nbsp;
						                                    	<bean:write name="outOfCountyCaseForm" property="confinementLengthMonth" /> <bean:message key="prompt.months" />&nbsp;
						                                    	<bean:write name="outOfCountyCaseForm" property="confinementLengthDay" /> <bean:message key="prompt.days" /></td>
						                                </tr>
					                                </logic:equal>
					                                <logic:notEqual name="outOfCountyCaseForm" property="dispositionTypeId" value="PTIN">
						                                <tr>
						                                  	<td class="formDeLabel" ><bean:message key="prompt.supervision" /> <bean:message key="prompt.length" /></td>
						                                  	<td class="formDe" colspan="3">
						                                  		<bean:write name="outOfCountyCaseForm" property="supervisionLengthYear" /> <bean:message key="prompt.years" />&nbsp;
						                                    	<bean:write name="outOfCountyCaseForm" property="supervisionLengthMonth" /> <bean:message key="prompt.months" />&nbsp;
						                                    	<bean:write name="outOfCountyCaseForm" property="supervisionLengthDay" /> <bean:message key="prompt.days" /></td>
						                                </tr>
					                                </logic:notEqual>
					                                <tr>
					                                  	<td class="formDeLabel"><bean:message key="prompt.offense" /> <bean:message key="prompt.date" /></td>
					                                  	<td class="formDe"><bean:write name="outOfCountyCaseForm" property="offenseDateAsString" /></td>
					                                  	<td class="formDeLabel" ><bean:message key="prompt.arrestDate" /></td>
					                                  	<td class="formDe"><bean:write name="outOfCountyCaseForm" property="arrestDateAsString" /></td>
					                                </tr>
                                				<!-- END CASE INFORMATION SECTION -->
                      								<tr><td><br></td></tr>
                                				<!-- BEGIN SUPERVISION PARAMETERS SECTION -->
					                                <tr>
					                                  	<td class="detailHead" colspan="4"><bean:message key="prompt.supervisionParameters" /></td>
					                                </tr>
					                                <logic:notEqual name="outOfCountyCaseForm" property="dispositionTypeId" value="PTIN">
						                                <tr>
						                                  	<td class="formDeLabel"><bean:message key="prompt.begin" /> <bean:message key="prompt.date" /></td>
						                                  	<td class="formDe"><bean:write name="outOfCountyCaseForm" property="supervisionBeginDateAsString" /></td>
						                                  	<td class="formDeLabel"><bean:message key="prompt.end" /> <bean:message key="prompt.date" /></td>
						                                  	<td class="formDe"><bean:write name="outOfCountyCaseForm" property="supervisionEndDateAsString" /></td>
						                                </tr>
					                                </logic:notEqual>
					                                <logic:equal name="outOfCountyCaseForm" property="dispositionTypeId" value="PTIN">
						                                <tr>
						                                  	<td class="formDeLabel" nowrap><bean:message key="prompt.pretrial" /> <bean:message key="prompt.intervention" /> <bean:message key="prompt.begin" /></td>
						                                  	<td class="formDe"><bean:write name="outOfCountyCaseForm" property="pretrialInterventionBeginAsString" /></td>
						                                  	<td class="formDeLabel" nowrap><bean:message key="prompt.pretrial" /> <bean:message key="prompt.intervention" /> <bean:message key="prompt.end" /></td>
						                                  	<td class="formDe"><bean:write name="outOfCountyCaseForm" property="pretrialInterventionEndAsString" /></td>
						                                </tr>
						                            </logic:equal>
					                                <tr>
					                                  	<td class="formDeLabel" nowrap><bean:message key="prompt.dateOfSentence" /></td>
					                                  	<td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="sentenceDateAsString" /></td>
	                                  				</tr>
                                  				<!-- END SUPERVISION PARAMETERS SECTION -->
                                					<tr><td><br></td></tr>
                      							<!-- BEGIN ORIGINAL JURISDICTION SECTION -->
					                                <tr>
					                                  	<td class="detailHead" colspan="4"><bean:message key="prompt.original" /> <bean:message key="prompt.jurisdiction" /></td>
					                                </tr>
					                                <tr>
					                                  	<td class="formDeLabel"><bean:message key="prompt.case#" /></td>
					                                  	<td class="formDe"><bean:write name="outOfCountyCaseForm" property="orgJurisCaseNum" /></td>
					                                  	<td class="formDeLabel"><bean:message key="prompt.court" /></td>
					                                  	<td class="formDe"><bean:write name="outOfCountyCaseForm" property="orgJurisCourt" /></td>
					                                </tr>
					                                <tr>
					                                  	<td class="formDeLabel" ><bean:message key="prompt.personID" /></td>
					                                  	<td class="formDe"><bean:write name="outOfCountyCaseForm" property="orgJurisPID" /></td>
					                                  	<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.transferInDate" /></td>
					                                  	<td class="formDe"><bean:write name="outOfCountyCaseForm" property="transferInDateAsString" /></td>
					                                </tr>
					                                <tr>
					                                  	<td class="formDeLabel" ><bean:message key="prompt.texasCounty" /></td>
					                                  	<td class="formDe"><bean:write name="outOfCountyCaseForm" property="orgJurisCounty" /></td>
					                                  	<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.outOfState" /></td>
					                                  	<td class="formDe"><bean:write name="outOfCountyCaseForm" property="state" /></td>
					                                </tr>
	                                			<!-- END ORIGINAL JURISDICTION SECTION -->
	                                				<tr><td><br></td></tr>
                              					<!-- BEGIN CONTACT INFO FOR ORIGINATING JURISDICTION SECTION -->
					                                <tr>
					                                  	<td colspan="4" class="detailHead"><bean:message key="prompt.contactInformationForOriginatingJurisdiction" /></td>
					                                </tr>
					                                <tr>
				                                  		<td class="formDeLabel" valign="top" nowrap><bean:message key="prompt.name" /></td>
				                                  		<td	class=formDe colspan="3"><bean:write name="outOfCountyCaseForm" property="contactName" /></td>
                            						</tr>
					                                <tr>
					                                  	<td class="formDeLabel" nowrap><bean:message key="prompt.jobTitle" /></td>
					                                  	<td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="contactJobTitle" /></td>
					                                </tr>
					                                <tr>
					                                  	<td class="formDeLabel"  nowrap><bean:message key="prompt.phone" /></td>
					                                  	<td class="formDe" colspan="3">
					                                  		<bean:write name="outOfCountyCaseForm" property="contactPhone1" /> -
					                                  		<bean:write name="outOfCountyCaseForm" property="contactPhone2" /> -
					                                  		<bean:write name="outOfCountyCaseForm" property="contactPhone3" />
					                                  		<%--<msp:formatter name="outOfCountyCaseForm" property="contactPhone" format="A-P-F"/> --%>&nbsp;
					                                    	<b><bean:message key="prompt.ext" /></b>
					                                    	<bean:write name="outOfCountyCaseForm" property="contactPhoneExt" /></td>
					                                </tr>         
                                				<!-- END CONTACT INFO FOR ORIGINATING JURISDICTION SECTION -->
	                                			<!-- BEGIN REASON FOR UPDATE SECTION -->
	                                			<logic:notEqual name="outOfCountyCaseForm" property="action" value="view">
	                                				<logic:notEqual name="outOfCountyCaseForm" property="reasonForUpdate" value="">
						                                <tr><td><br></td></tr>
						                                <tr>
						                                  	<td colspan="4" class="detailHead"><bean:message key="prompt.reasonForUpdate" /></td>
						                                </tr>
						                                <tr>
						                                  	<td class="formDeLabel" nowrap><bean:message key="prompt.reason" /></td>
						                                  	<td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="reasonForUpdate" /></td>
						                                </tr>
					                                </logic:notEqual>
												</logic:notEqual>
												</table>
												<!-- END REASON FOR UPDATE SECTION -->
                            				</td>
                          				</tr>
                        			</table>
<!-- END DETAIL TABLE -->
                        			<br>
<!-- BEGIN BUTTON TABLE -->
                        			<table border="0" width="98%">
                          				<logic:equal name="outOfCountyCaseForm" property="action" value="view">
											<tr>											
												<td align="center">
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
	                          						<logic:equal name="outOfCountyCaseForm" property="reactivateInd" value="false">
														<jims2:isAllowed requiredFeatures="CS-OOC-UPDATE">	
															<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.updateCase"></bean:message></html:submit>
														</jims2:isAllowed>
													</logic:equal>
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.viewReasonForUpdateHistory"></bean:message></html:submit>
												</td>								
											</tr>		
										</logic:equal>
										<logic:equal name="outOfCountyCaseForm" property="action" value="create">
											<tr>											
												<td align="center">
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>&nbsp;
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
												</td>								
											</tr>		
										</logic:equal>
										<logic:equal name="outOfCountyCaseForm" property="action" value="update">
											<tr>											
												<td align="center">
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>&nbsp;
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
												</td>								
											</tr>		
										</logic:equal>
										<logic:equal name="outOfCountyCaseForm" property="action" value="reactivate">
											<tr>											
												<td align="center">
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>&nbsp;
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
												</td>								
											</tr>		
										</logic:equal>
										<logic:equal name="outOfCountyCaseForm" property="action" value="confirmCreate">
											<tr>											
												<td align="center">
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.newSearch"/></html:submit>&nbsp;
													<jims2:isAllowed requiredFeatures="CS-PASO-CREATE">
														<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.createSupervisionOrder"></bean:message></html:submit>
													</jims2:isAllowed>
												</td>								
											</tr>		
										</logic:equal>
										<logic:equal name="outOfCountyCaseForm" property="action" value="confirmUpdate">
											<tr>											
												<td align="center">
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.newSearch"/></html:submit>
												</td>								
											</tr>		
										</logic:equal>
										<logic:equal name="outOfCountyCaseForm" property="action" value="confirmReactivate">
											<tr>											
												<td align="center">
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.newSearch"/></html:submit>
												</td>								
											</tr>		
										</logic:equal>
				                    </table>
<!-- END BUTTON TABLE -->
                              	</td>
							</tr>
						</table>
						<br>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>