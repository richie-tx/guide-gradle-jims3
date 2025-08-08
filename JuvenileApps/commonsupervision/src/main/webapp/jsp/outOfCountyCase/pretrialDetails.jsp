<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 04/27/2006	 Hien Rodriguez - Create JSP -->
<!-- 11/27/2006	 Hien Rodriguez - Display Texas County & Out of State Code in Case Info section,
     remove County from Agency Address section -->
<!-- 01/24/2007	 Hien Rodriguez - ER#38002 Add coding to integrate the Help Screen. -->

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
<title><bean:message key="title.heading" /> - outOfCountyCase/pretrialDetails.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

</head>
<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/submitOutOfCountyCaseCreateUpdate" target="content">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
    	<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
  	</tr>
  	<tr>
    	<td valign=top>
			<table width=100% border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign=top>
					<!--tabs start-->
					<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
						<tiles:put name="tab" value="setupTab"/>
					</tiles:insert>		
					<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor=#6699FF><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
			</table>
			<table width=100% border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
				</tr>
				<tr>
					<td valign=top align=center>
						<table width=98% border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td valign=top>
								<!--tabs start-->
									<tiles:insert page="../common/manageFeaturesTabs.jsp" flush="true">
										<tiles:put name="tabid" value="oocTab"/>
									</tiles:insert>	
								<!--tabs end-->
								</td>
							</tr>
							
						</table>
						<table width=98% border="0" cellpadding="0" cellspacing="0" class="borderTableGreen">
							<tr>
								<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
							</tr>
							<tr>
								<td valign=top align=center>
								<!-- BEGIN HEADING TABLE -->
									<table width=98%>
										<tr>
											<td align="center" class="header">
												<logic:equal name="outOfCountyCaseForm" property="action" value="pretrialView">
													<input type="hidden" name="helpFile" value="commonsupervision/ooc/out_of_county_case.htm#|15">
													<bean:message key="prompt.view" />&nbsp;<bean:message key="title.outOfCountyCase" />&nbsp;-&nbsp;<bean:message key="prompt.pretrial" />
													<tr>
														<td><ul>
															<li>Click the Modify button to update this case.</li>
														</ul></td>
													</tr>
												</logic:equal>
												<logic:equal name="outOfCountyCaseForm" property="action" value="pretrialCreate">
													<input type="hidden" name="helpFile" value="commonsupervision/ooc/out_of_county_case.htm#|17">
													<bean:message key="prompt.create" />&nbsp;<bean:message key="title.outOfCountyCase" />&nbsp;<bean:message key="prompt.summary" />&nbsp;-&nbsp;<bean:message key="prompt.pretrial" />
													<tr>
														<td><ul>
															<li>Verify that information is correct and select Finish button to create Out of County Case.</li>
															<li>If any changes are needed, select Back button.</li>
														</ul></td>
													</tr>
												</logic:equal>
												<logic:equal name="outOfCountyCaseForm" property="action" value="pretrialUpdate">
													<input type="hidden" name="helpFile" value="commonsupervision/ooc/out_of_county_case.htm#|20">
													<bean:message key="prompt.update" />&nbsp;<bean:message key="title.outOfCountyCase" />&nbsp;<bean:message key="prompt.summary" />&nbsp;-&nbsp;<bean:message key="prompt.pretrial" />
													<tr>
														<td><ul>
															<li>Verify that information is correct and select Finish button to create Out of County Case.</li>
															<li>If any changes are needed, select Back button.</li>
														</ul></td>
													</tr>
												</logic:equal>
												<logic:equal name="outOfCountyCaseForm" property="action" value="pretrialReactivate">
													<input type="hidden" name="helpFile" value="commonsupervision/ooc/out_of_county_case.htm#|23">
													<bean:message key="prompt.reactivate" />&nbsp;<bean:message key="title.outOfCountyCase" />&nbsp;<bean:message key="prompt.summary" />&nbsp;-&nbsp;<bean:message key="prompt.pretrial" />
													<tr>
														<td><ul>
															<li>Verify that information is correct and select Finish button to save changes.</li>
															<li>If any changes are needed, select Back button.</li>
														</ul></td>
													</tr>
												</logic:equal>					
												<logic:equal name="outOfCountyCaseForm" property="action" value="confirmPretrialCreate">
													<input type="hidden" name="helpFile" value="commonsupervision/ooc/out_of_county_case.htm#|18">
													<bean:message key="prompt.create" />&nbsp;<bean:message key="title.outOfCountyCase" />&nbsp;<bean:message key="prompt.confirmation" />										
													<tr>
														<td class="confirm"><bean:message key="prompt.caseNum" /> <bean:write name="outOfCountyCaseForm" property="caseNum" /> has been generated.</td> 
													</tr>
												</logic:equal>
												<logic:equal name="outOfCountyCaseForm" property="action" value="confirmPretrialUpdate">
													<input type="hidden" name="helpFile" value="commonsupervision/ooc/out_of_county_case.htm#|21">
													<bean:message key="prompt.update" />&nbsp;<bean:message key="title.outOfCountyCase" />&nbsp;<bean:message key="prompt.confirmation" />&nbsp;-&nbsp;<bean:message key="prompt.pretrial" />										
													<tr>
														<td class="confirm">Case Information successfully updated.</td>
													</tr>
												</logic:equal>
												<logic:equal name="outOfCountyCaseForm" property="action" value="confirmPretrialReactivate">
													<input type="hidden" name="helpFile" value="commonsupervision/ooc/out_of_county_case.htm#|24">
													<bean:message key="prompt.reactivate" />&nbsp;<bean:message key="title.outOfCountyCase" />&nbsp;<bean:message key="prompt.confirmation" />&nbsp;-&nbsp;<bean:message key="prompt.pretrial" />										
													<tr>
														<td class="confirm">Case successfully reactivated.</td>
													</tr>
												</logic:equal>
											</td>
										 </tr>		  				
									</table>
								<!-- END HEADING TABLE -->
								<!-- BEGIN DETAIL HEADER TABLE -->										
									<tiles:insert page="partyInfoHeaderTile.jsp" flush="true">
										<tiles:put name="partyHeader" beanName="outOfCountyCaseForm"/>
									</tiles:insert>	
								<!-- END DETAIL HEADER TABLE -->
								<!-- BEGIN DETAIL TABLE -->
									<table width=100% border="0" cellpadding="0" cellspacing="0">
                        				<tr>
                          					<td align=center>
					                            <table width=98% border="0" cellpadding="4" cellspacing="1">
					                            <!-- BEGIN PRETRIAL CASE INFORMATION SECTION -->
					                              	<tr>
					                                	<td class=detailHead colspan=4><bean:message key="prompt.pretrial" />&nbsp;<bean:message key="prompt.caseInfo"/></td>
					                              	</tr>
					                              	<logic:notEqual name="outOfCountyCaseForm" property="action" value="create">
					                                	<tr>
															<td class="formDeLabel"><bean:message key="prompt.case#"/></td>
					                                  		<td class="formDe" colspan=3><bean:write name="outOfCountyCaseForm" property="caseNum" /></td>
														</tr>
													</logic:notEqual>
					                              	<tr>
						                            	<td class="formDeLabel"><bean:message key="prompt.offense"/>&nbsp;<bean:message key="prompt.name" /></td>
						                                <td class="formDe" colspan=3><bean:write name="outOfCountyCaseForm" property="offense" /></td>
                                  					</tr>
                                  					<tr>
					                                  	<td class="formDeLabel"><bean:message key="prompt.offenseDate" /></td>
					                                  	<td class="formDe" colspan=3><bean:write name="outOfCountyCaseForm" property="offenseDateAsString" /></td>
					                                </tr>
					                              	<tr>
					                                	<td class=formDeLabel><bean:message key="prompt.violentOffense" /></td>
					                                	<td class=formDe><bean:write name="outOfCountyCaseForm" property="offenseViolenceIndDisplay" /></td>
					                                	<td class=formDeLabel nowrap width=1%><bean:message key="prompt.familyViolence" /></td>
					                                	<td class=formDe><bean:write name="outOfCountyCaseForm" property="familyViolenceIndDisplay" /></td>
					                              	</tr>
					                              	<tr>
					                                	<td class=formDeLabel nowrap width=1%><bean:message key="prompt.originalJurisdictionCase#" /></td>
					                                	<td class=formDe colspan=3><bean:write name="outOfCountyCaseForm" property="orgJurisCaseNum" /></td>
					                              	</tr>
					                              	<tr>
					                                	<td class=formDeLabel><bean:message key="prompt.originalJurisdictionCourt" /></td>
					                                	<td class=formDe colspan=3><bean:write name="outOfCountyCaseForm" property="orgJurisCourt" /></td>
					                                </tr>
                              						<tr>
                                						<td class=formDeLabel><bean:message key="prompt.originalJurisdictionPID" /></td>
                                						<td class=formDe colspan=3><bean:write name="outOfCountyCaseForm" property="orgJurisPID" /></td>
                              						</tr>
                              						<tr>
                                						<td class=formDeLabel><bean:message key="prompt.outOfCountyCaseType" /></td>
                                						<td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="caseType" /></td>
						                            </tr>
						                            <tr>
					                                  	<td class="formDeLabel" ><bean:message key="prompt.texasCounty" /></td>
					                                  	<td class="formDe"><bean:write name="outOfCountyCaseForm" property="orgJurisCounty" /></td>
					                                  	<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.outOfState" /></td>
					                                  	<td class="formDe"><bean:write name="outOfCountyCaseForm" property="state" /></td>
					                                </tr>
						                            <tr>
                                						<td class=formDeLabel nowrap><bean:message key="prompt.supervisionBeginDate"/></td>
                                						<td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="supervisionBeginDateAsString" /></td>
													</tr>
												<!-- END PRETRIAL CASE INFORMATION SECTION -->
                                					<tr><td><br></td></tr>
                      							<!-- BEGIN ORIGINATING AGENCY CONTACT SECTION -->
                      								<tr>
					                                  	<td class=detailHead colspan=4><bean:message key="prompt.originating"/>&nbsp;<bean:message key="prompt.jurisdiction" />&nbsp;<bean:message key="prompt.contact" /></td>
					                                </tr>
					                                <tr>
					                                	<td class="formDeLabel" valign=top nowrap><bean:message key="prompt.name" /></td>
				                                  		<td	class=formDe colspan="3"><bean:write name="outOfCountyCaseForm" property="contactName" /></td>
                            						</tr>
					                                <tr>
					                                  	<td class="formDeLabel"><bean:message key="prompt.jobTitle" /></td>
					                                  	<td class="formDe" colspan=3><bean:write name="outOfCountyCaseForm" property="contactJobTitle" /></td>
					                                </tr>
					                                <tr>
					                                  	<td class="formDeLabel"><bean:message key="prompt.phone" /></td>
					                                  	<td class="formDe" colspan=3>
					                                  		<bean:write name="outOfCountyCaseForm" property="contactPhone1" />
					                                  		- <bean:write name="outOfCountyCaseForm" property="contactPhone2" />
					                                  		- <bean:write name="outOfCountyCaseForm" property="contactPhone3" />
					                                    	<b><bean:message key="prompt.ext" /></b>
					                                    	<bean:write name="outOfCountyCaseForm" property="contactPhoneExt" /></td>
					                                </tr>
						                            <%--<tr>
						                                <td class=formDeLabel nowrap><bean:message key="prompt.county" /></td>
						                                <td class="formDe" colspan="3"><bean:write name="outOfCountyCaseForm" property="orgJurisCounty" /></td>
						                            </tr>--%>
					                                <tr>
					                                	<td class=formDeLabel nowrap><bean:message key="prompt.agencyName" /></td>
					                                	<td class=formDe colspan="3"><bean:write name="outOfCountyCaseForm" property="agencyName" /></td>
					                              	</tr>
					                              	<tr>
					                                	<td class=subhead colspan=4 bgcolor="#999999"><bean:message key="prompt.agency" />&nbsp;<bean:message key="prompt.address" /></td>
					                              	</tr>
					                              	<tr>
						                                <td colspan=4>
						                                  	<table border="0" cellspacing=1 width=100%>
						                                    	<tr class=formDeLabel>
						                                    		<td width=25%><bean:message key="prompt.streetNum" /></td>
                                      								<td width=35%><bean:message key="prompt.streetName" /></td>
                                      								<td width=25%><bean:message key="prompt.streetType" /></td>
                                      								<td width=15%><bean:message key="prompt.aptSuite" /></td>
                                      							</tr>
                                    							<tr class=formDe>
							                                      	<td><bean:write name="outOfCountyCaseForm" property="streetNum" /></td>
							                                      	<td><bean:write name="outOfCountyCaseForm" property="streetName" /></td>
							                                      	<td><bean:write name="outOfCountyCaseForm" property="streetType" /></td>
							                                      	<td><bean:write name="outOfCountyCaseForm" property="aptNum" /></td>
							                                    </tr>
							                                    <tr class=formDeLabel>
							                                      	<td><bean:message key="prompt.city" /></td>
							                                      	<td><bean:message key="prompt.state" /></td>
							                                      	<td><bean:message key="prompt.zipCode" /></td>
							                                      	<td><bean:message key="prompt.addressType" /></td>
							                                    </tr>
							                                    <tr class=formDe>
							                                      	<td><bean:write name="outOfCountyCaseForm" property="city" /></td>
							                                      	<td><bean:write name="outOfCountyCaseForm" property="addressState" /></td>
							                                      	<td><bean:write name="outOfCountyCaseForm" property="zipCode" />
							                                      		- <bean:write name="outOfCountyCaseForm" property="additionalZipCode" /></td>
							                                    	<td><bean:write name="outOfCountyCaseForm" property="addressType" /></td>
							                                    </tr>
								                                <%--<tr class=formDeLabel>
								                                    <td><bean:message key="prompt.addressType" /></td>
								                                    <td colspan="3"><bean:message key="prompt.county" /></td>
								                                </tr> --%>
								                                <%--<tr class=formDe>
								                                    <td><bean:write name="outOfCountyCaseForm" property="addressType" /></td>
								                                    <td colspan="3"><bean:write name="outOfCountyCaseForm" property="county" /></td>
								                                </tr>--%>
								                             </table>
                                						</td>
                              						</tr>
                              					<!-- END ORIGINATING AGENCY CONTACT SECTION -->	
                      							</table>
                            				</td>
                          				</tr>
                        			</table>
                        		<!-- END DETAIL TABLE -->
                        			<br>
                        		<!-- BEGIN BUTTON TABLE -->
                        			<table border="0" width="98%">
                          				<logic:equal name="outOfCountyCaseForm" property="action" value="pretrialView">
											<tr>											
												<td align=center>
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
													<jims2:isAllowed requiredFeatures="CS-OOC-UPDATE">	
														<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.updateCase"></bean:message></html:submit>
													</jims2:isAllowed>
												</td>								
											</tr>		
										</logic:equal>
										<logic:equal name="outOfCountyCaseForm" property="action" value="pretrialCreate">
											<tr>											
												<td align=center>
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>&nbsp;
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
												</td>								
											</tr>		
										</logic:equal>
										<logic:equal name="outOfCountyCaseForm" property="action" value="pretrialUpdate">
											<tr>											
												<td align=center>
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>&nbsp;
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
												</td>								
											</tr>		
										</logic:equal>
										<logic:equal name="outOfCountyCaseForm" property="action" value="pretrialReactivate">
											<tr>											
												<td align=center>
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>&nbsp;
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
												</td>								
											</tr>		
										</logic:equal>
										<logic:equal name="outOfCountyCaseForm" property="action" value="confirmPretrialCreate">
											<tr>											
												<td align=center>
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.newSearch"/></html:submit>&nbsp;
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.createSupervisionOrder"></bean:message></html:submit>
												</td>								
											</tr>		
										</logic:equal>
										<logic:equal name="outOfCountyCaseForm" property="action" value="confirmPretrialUpdate">
											<tr>											
												<td align=center>
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.newSearch"/></html:submit>
												</td>								
											</tr>		
										</logic:equal>
										<logic:equal name="outOfCountyCaseForm" property="action" value="confirmPretrialReactivate">
											<tr>											
												<td align=center>
													<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.newSearch"/></html:submit>
												</td>								
											</tr>		
										</logic:equal>
				                    </table>
                        		<!-- END BUTTON TABLE -->
                              	</td>
							</tr>
						</table><br>
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
