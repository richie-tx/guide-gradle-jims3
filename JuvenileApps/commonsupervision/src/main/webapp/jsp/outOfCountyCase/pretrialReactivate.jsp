<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 04/28/2006	 Hien Rodriguez - Create JSP -->
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
<title><bean:message key="title.heading" /> - outOfCountyCase/pretrialReactivate.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>


<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->
<html:javascript formName="outOfCountyCaseForm4" />

</head>

<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayOutOfCountyCaseReactivateSummary" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/ooc/out_of_county_case.htm#|22">
<div align="center">
<table width="98%" border="0" cellpadding="0" cellspacing="0">
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
						<tiles:put name="tab" value="setUpTab"/>
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
											<td align="center" class="header"><bean:message key="prompt.reactivate" />&nbsp;<bean:message key="title.outOfCountyCase" />&nbsp;-&nbsp;<bean:message key="prompt.pretrial" /></td>
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
									<table width=98% border="0">
										<tr>
											<td><ul>
												<li>Enter in at least the required information to reactivate case and click Next button to view summary.</li>
												<li>Click Back button if you selected the wrong case.</li>
											</ul></td>
										</tr>
										<tr>
											<td class="required"><bean:message key="prompt.requiredFields"/>&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction"/></td>												
										</tr>
									</table>	
                    			<!-- END INSTRUCTION TABLE -->
                    			<!-- BEGIN DETAIL HEADER TABLE -->										
									<tiles:insert page="partyInfoHeaderTile.jsp" flush="true">
										<tiles:put name="partyHeader" beanName="outOfCountyCaseForm"/>
									</tiles:insert>	
								<!-- END DETAIL HEADER TABLE -->
								<!-- BEGIN DETAIL TABLE -->
                      				<table width=100% border="0" cellpadding="0" cellspacing="0">
                        				<tr>
                          					<td align=center>
					                            <table width=98% border="0" cellpadding="2" cellspacing="1">
					                            <!-- BEGIN PRETRIAL CASE INFORMATION SECTION -->
					                              	<tr>
					                                	<td class=detailHead colspan=4><bean:message key="prompt.pretrial" />&nbsp;<bean:message key="prompt.caseInfo"/></td>
					                              	</tr>
				                                	<tr>
														<td class="formDeLabel"><bean:message key="prompt.case#"/></td>
				                                  		<td class="formDe" colspan=3><bean:write name="outOfCountyCaseForm" property="caseNum" /></td>
													</tr>
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
					                                	<td class=formDe><bean:write name="outOfCountyCaseForm" property="offenseViolenceInd" /></td>
					                                	<td class=formDeLabel nowrap width=1%><bean:message key="prompt.familyViolence" /></td>
					                                	<td class=formDe><bean:write name="outOfCountyCaseForm" property="familyViolenceInd" /></td>
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
                                						<td class=formDeLabel nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.supervisionBeginDate"/></td>
                                						<td class="formDe" colspan="3">
					                                  		<SCRIPT LANGUAGE="JavaScript" ID="js1">
																var cal1 = new CalendarPopup();
																cal1.showYearNavigation();
															</SCRIPT>
															<html:text name="outOfCountyCaseForm" property="supervisionBeginDateAsString" maxlength="10" size="10" />
															<A HREF="#" onClick="cal1.select(document.forms[0].supervisionBeginDateAsString,'anchor1','MM/dd/yyyy'); return false;"
															NAME="anchor1" ID="anchor1" border="0"><bean:message key="prompt.2.calendar"/></A></td>
                                					</tr>
												<!-- END PRETRIAL CASE INFORMATION SECTION -->
                                					<tr><td><br></td></tr>
                      							<!-- BEGIN ORIGINATING AGENCY CONTACT SECTION -->
					                                <tr>
					                                  	<td class=detailHead colspan=4><bean:message key="prompt.originating" /><bean:message key="prompt.jurisdiction" />&nbsp;<bean:message key="prompt.contact" /></td>
					                                </tr>
					                                <tr>
				                                  		<td class="formDeLabel" valign=top nowrap><bean:message key="prompt.name" /></td>
				                                  		<td	class=formDe colspan="3">
				                                    		<table border=0 cellspacing=1>
							                                	<tr>
							                                        <td class=formDeLabel colspan="2"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.last" /></td>
							                                    </tr>
							                                    <tr>
							                                        <td class=formDe colspan="2"><html:text property="contactLastName" maxlength="75" size="30"/></td>
							                                    </tr>
							                                    <tr>
							                                        <td class=formDeLabel><bean:message key="prompt.2.diamond"/><bean:message key="prompt.first" /></td>
							                                        <td class=formDeLabel><bean:message key="prompt.middle" /></td>
							                                    </tr>
							                                    <tr>
							                                        <td class=formDe><html:text property="contactFirstName" maxlength="50" size="25"/></td>
							                                      	<td class=formDe><html:text property="contactMiddleName" maxlength="50" size="25"/></td>
							                                    </tr>
							                                </table>
                             		 					</td>
                            						</tr>
					                                <tr>
					                                  	<td class="formDeLabel"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.jobTitle" /></td>
					                                  	<td class="formDe" colspan=3><html:text property="contactJobTitle" maxlength="10" size="10"/></td>
					                                </tr>
					                                <tr>
					                                  	<td class="formDeLabel"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.phone" /></td>
					                                  	<td class="formDe" colspan=3>
					                                  		<html:text property="contactPhone1" maxlength="3" size="3"/>
					                                  		- <html:text property="contactPhone2" maxlength="3" size="3"/>
					                                  		- <html:text property="contactPhone3" maxlength="4" size="4"/>
					                                    	<b><bean:message key="prompt.ext" /></b>
					                                    	<html:text property="contactPhoneExt" maxlength="10" size="10"/></td>
					                                </tr>
						                            <%--<tr>
						                                <td class=formDeLabel nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.county" /></td>
						                                <td class="formDe" colspan="3"><html:select property="countyCodeId" size="1">
															<html:option value=""><bean:message key="select.generic" /></html:option>
															<html:optionsCollection name="outOfCountyCaseForm" property="countyList" value="code" label="description" />
															</html:select></td>
						                            </tr>--%>
					                                <tr>
					                                	<td class=formDeLabel nowrap><bean:message key="prompt.2.diamond"/><bean:message key="prompt.agencyName" /></td>
					                                	<td class=formDe colspan="3"><html:text property="agencyName" maxlength="60" size="60"/></td>
					                              	</tr>
					                              	<tr>
					                                	<td class=subhead colspan=4 bgcolor="#999999"><bean:message key="prompt.agency" />&nbsp;<bean:message key="prompt.address" /></td>
					                              	</tr>
						                            <tr>
						                                <td colspan=4>
						                                  	<table border="0" cellspacing=1 width=100%>
						                                    	<tr class=formDeLabel>
						                                      		<td><bean:message key="prompt.2.diamond"/><bean:message key="prompt.streetNum" /></td>
						                                      		<td colspan="2"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.streetName" /></td>
						                                    	</tr>
						                                    	<tr class=formDe>
						                                      		<td><html:text property="streetNum" maxlength="10" size="10"/></td>
						                                      		<td colspan="2"><html:text property="streetName" maxlength="50" size="50"/></td>
						                                    	</tr>
						                                    	<tr class=formDeLabel>
						                                      		<td><bean:message key="prompt.2.diamond"/><bean:message key="prompt.streetType" /></td>
						                                      		<td colspan="2"><bean:message key="prompt.aptSuite" /></td>
						                                    	</tr>
							                                    <tr class=formDe>
							                                      	<td><html:select property="streetTypeId" size="1">
																		<html:option value=""><bean:message key="select.generic" /></html:option>
																		<html:optionsCollection name="outOfCountyCaseForm" property="streetTypeList" value="code" label="description" />
																		</html:select></td>
							                                      	<td colspan="2"><html:text property="aptNum" maxlength="20" size="20"/></td>
							                                    </tr>
							                                    <tr class=formDeLabel>
							                                      	<td><bean:message key="prompt.2.diamond"/><bean:message key="prompt.city" /></td>
							                                      	<td><bean:message key="prompt.2.diamond"/><bean:message key="prompt.state" /></td>
							                                      	<td><bean:message key="prompt.2.diamond"/><bean:message key="prompt.zipCode" /></td>
							                                    </tr>
							                                    <tr class=formDe>
							                                      	<td><html:text property="city" maxlength="15" size="15"/></td>
							                                      	<td><html:select property="addressStateId" size="1">
																		<html:option value=""><bean:message key="select.generic" /></html:option>
																		<html:optionsCollection name="outOfCountyCaseForm" property="stateList" value="code" label="description" />
																		</html:select></td>
							                                      	<td><html:text property="zipCode" maxlength="5" size="5"/>
							                                        	- <html:text property="additionalZipCode" maxlength="4" size="4"/></td>
							                                    </tr>
								                                <tr class=formDeLabel>
								                                    <td colspan="3"><bean:message key="prompt.addressType" /></td>
								                                    <%--<td colspan="2"><bean:message key="prompt.2.diamond"/><bean:message key="prompt.county" /></td>--%>
								                                </tr>
								                                <tr class=formDe>
								                                    <td colspan="3"><html:select property="addressTypeId" size="1">
																		<html:option value=""><bean:message key="select.generic" /></html:option>
																		<html:optionsCollection name="outOfCountyCaseForm" property="addressTypeList" value="code" label="description" />
																		</html:select></td>
								                                    <%--<td colspan="2"><html:select property="countyCodeId" size="1">
																		<html:option value=""><bean:message key="select.generic" /></html:option>
																		<html:optionsCollection name="outOfCountyCaseForm" property="countyList" value="code" label="description" />
																		</html:select></td>--%>
								                                </tr>
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
                          				<tr>
				                            <td align="center">
				                            	<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.back"/></html:submit>&nbsp;
				                            	<html:submit property="submitAction" onclick="return (validateOutOfCountyCaseForm4(this.form) && disableSubmit(this, this.form));"><bean:message key="button.next"></bean:message></html:submit>&nbsp;
												<html:reset><bean:message key="button.reset"></bean:message></html:reset>&nbsp;
												<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.cancel"></bean:message></html:submit>
											</td>
										</tr>
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