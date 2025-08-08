<!DOCTYPE HTML>
<%-- 10/04/2006	 Debbie Williamson	Create JSP --%>
<%-- 07/27/2007	 L Deen				Defect #44098 remove Create Location button --%>
<%-- 10/13/2015  RYoung  - #30342 MJCW: IE11 conversion of HCJPD link on UILeftNav (UI - Search Location)--%>

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>
<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/csbase.css" />
<html:base />
<title><bean:message key="title.heading" /> - location/locationDetails.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script><tiles:insert page="../../js/common_supervision_util.js" flush="true"/></script>
<script type="text/javascript" src="/<msp:webapp/>js/case_util.js"></script>

<!-- JavaScript for emulated navigation -->
<script type="text/javascript">
	function submitSelectedItem(action,locId,theForm)
	{
		url = "/<msp:webapp/>handleLocationSelection.do?submitAction="+action		 			
		changeFormActionURL(theForm, url,true);
	}
	
	function viewJuvLocUnits(action,locId,theForm)
	{
		url = "/<msp:webapp/>handleLocationUnitListUpdate.do?submitAction="+action		 
		url = url + "&locationId=" + locId;
		changeFormActionURL1(theForm, url,true);
	}
	
	function changeFormActionURL1(theForm, URL, doSubmit)
	{
		theForm.action = URL;
		if(doSubmit)
		{
			theForm.submit();
		}
		return true;
	}
</script>
</head>

<body topmargin=0 leftmargin="0">
<html:form action="/handleLocationSelection" target="content">
<logic:equal name="locationForm" property="action" value="view">
	<input type="hidden" name="helpFile" value="commonsupervision/Location/Administer_Location.htm#|396">										
</logic:equal> 
<logic:equal name="locationForm" property="action" value="viewInactive">
    <input type="hidden" name="helpFile" value="commonsupervision/Location/Administer_Location.htm#|396">										
</logic:equal>
<logic:equal name="locationForm" property="action" value="create">
 	<input type="hidden" name="helpFile" value="commonsupervision/Location/Administer_Location.htm#|401">										
</logic:equal>        
<logic:equal name="locationForm" property="action" value="confirmCreate">
 	<input type="hidden" name="helpFile" value="commonsupervision/Location/Administer_Location.htm#|402">										
</logic:equal>
<logic:equal name="locationForm" property="action" value="update">
 	<input type="hidden" name="helpFile" value="commonsupervision/Location/Administer_Location.htm#|398">										
</logic:equal>        
<logic:equal name="locationForm" property="action" value="confirmUpdate">
	<input type="hidden" name="helpFile" value="commonsupervision/Location/Administer_Location.htm#|399">										
</logic:equal>
<logic:equal name="locationForm" property="action" value="activate">
 	<input type="hidden" name="helpFile" value="commonsupervision/Location/Administer_Location.htm#|405">										
</logic:equal>        
<logic:equal name="locationForm" property="action" value="confirmActivate">
	<input type="hidden" name="helpFile" value="commonsupervision/Location/Administer_Location.htm#|406">										
</logic:equal>
<logic:equal name="locationForm" property="action" value="inactivate">
	<input type="hidden" name="helpFile" value="commonsupervision/Location/Administer_Location.htm#|403">										
</logic:equal>        
<logic:equal name="locationForm" property="action" value="confirmInactivate">
	<input type="hidden" name="helpFile" value="commonsupervision/Location/Administer_Location.htm#|404">										
</logic:equal>

<div align="center">
	<table width="98%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
			<td valign=top><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
		</tr>
		<tr>
			<td valign=top>
				<table width='100%' border="0" cellpadding="0" cellspacing="0" >
					<tr>
						<td valign=top>
							<!--tabs start-->
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tabid" value=""/>
							</tiles:insert>						
							<!--tabs end-->
						</td>
					</tr>
					<tr>						
						<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
					</tr>
				</table>
				<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					<tr>						
						<td><img src="/<msp:webapp/>images/spacer.gif" height=5></td>
					</tr>
					<tr>
						<td valign=top align=center>

							<!-- BEGIN HEADING TABLE -->
							<table width='100%'>
								<tr>
									<td align="center" class="header">
  									<logic:equal name="locationForm" property="action" value="view">
  										<bean:message key="prompt.location" />
                      <bean:message key="prompt.details" />										
                    </logic:equal> 

  									<logic:equal name="locationForm" property="action" value="viewInactive">
  										<bean:message key="prompt.location" />
                      <bean:message key="prompt.details" />										
                    </logic:equal>

  									<logic:equal name="locationForm" property="action" value="create">
  										<bean:message key="prompt.create" />
  										<bean:message key="prompt.location" />
                      <bean:message key="prompt.summary" />										
                    </logic:equal>        
  
  									<logic:equal name="locationForm" property="action" value="confirmCreate">
  										<bean:message key="prompt.create" />
  										<bean:message key="prompt.location" />
                      <bean:message key="prompt.confirmation" />										
                    </logic:equal>

  									<logic:equal name="locationForm" property="action" value="update">
  										<bean:message key="prompt.update" />
  										<bean:message key="prompt.location" />
                      <bean:message key="prompt.summary" />										
                    </logic:equal>        

  									<logic:equal name="locationForm" property="action" value="confirmUpdate">
  										<bean:message key="prompt.update" />
  										<bean:message key="prompt.location" />
                      <bean:message key="prompt.confirmation" />										
                    </logic:equal>

  									<logic:equal name="locationForm" property="action" value="activate">
  										<bean:message key="prompt.activate" />
  										<bean:message key="prompt.location" />
                      <bean:message key="prompt.summary" />										
                    </logic:equal>        

  									<logic:equal name="locationForm" property="action" value="confirmActivate">
  										<bean:message key="prompt.activate" />
  										<bean:message key="prompt.location" />
                      <bean:message key="prompt.confirmation" />										
                    </logic:equal>

  									<logic:equal name="locationForm" property="action" value="inactivate">
  										<bean:message key="prompt.inactivate" />
  										<bean:message key="prompt.location" />
                      <bean:message key="prompt.summary" />										
                    </logic:equal>        

  									<logic:equal name="locationForm" property="action" value="confirmInactivate">
  										<bean:message key="prompt.inactivate" />
  										<bean:message key="prompt.location" />
                      <bean:message key="prompt.confirmation" />										
                    </logic:equal>        
									</td>
								</tr>
							</table>
						<!-- END HEADING TABLE -->

						<!-- BEGIN INSTRUCTION TABLE -->
							<table width="98%" border="0">
								<tr>
									<logic:equal name="locationForm" property="action" value="create">
									  <td><ul>
										<li>Review information and select Finish button to view confirmation.</li>
										<li>If information is incorrect, select Back button.</li>
									  </ul></td>
                  					</logic:equal>
									<logic:equal name="locationForm" property="action" value="inactivate">
 									  <td><ul>
										<li>Review location and select Finish button to inactivate this location.</li>
									  </ul></td>
                  					</logic:equal>
									<logic:equal name="locationForm" property="action" value="activate">
									  <td><ul>
										<li>Review location and select Finish button to activate this location.</li>
									  </ul></td>
                  					</logic:equal>
									<logic:equal name="locationForm" property="action" value="view">
									  <td><ul>
										<li>Select the appropriate button.</li>
										<logic:equal name="locationForm" property="activeLocationUnitExists" value="true">
											<li>All Location Units need to be inactive before a Location can be inactivated.</li>
										</logic:equal>
									  </ul></td>
                  					</logic:equal>       
								</tr>
							</table>

							<table width="98%" border="0">
								<tr>
									<logic:equal name="locationForm" property="action" value="confirmInactivate">
									    <td class=confirm>Location successfully inactivated.</td>
                  </logic:equal>
									<logic:equal name="locationForm" property="action" value="confirmActivate">
									    <td class=confirm>Location successfully activated.</td>
                  </logic:equal>
									<logic:equal name="locationForm" property="action" value="confirmCreate">
									    <td class=confirm>Location successfully created.</td>
                  </logic:equal>
									<logic:equal name="locationForm" property="action" value="confirmUpdate">
									    <td class=confirm>Location successfully updated.</td>
                  </logic:equal>
								</tr>
							</table>

							<!-- location start -->
							<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class=borderTableBlue>
								<tr class="detailHead">
									<td>
										<table width='100%' cellpadding=0 cellspacing=1>
											<tr class=detailHead>
												<td><bean:message key="prompt.location" />
													<bean:message key="prompt.information" />										
												</td>

												<logic:equal name="locationForm" property="action" value="view">
                          <td align=right><a href="javascript:openWindow('/<msp:webapp/>handleLocationSelection.do?submitAction=<bean:message key="button.viewAll" />&locationId=<bean:write name="locationForm" property="locationId" />')"><bean:message key="prompt.viewAll" /> Service Providers</td>
												</logic:equal>

												<%--
												<logic:equal name="locationForm" property="action" value="update">
												  <td align=right><a href="javascript: openWindow('/<msp:webapp/>handleLocationSelection.do?action=viewSuccess&serviceProviderId=<bean:write name="locationListIndex" property="serviceProviderId" />')"><bean:message key="prompt.viewAll" />
                            <bean:message key="prompt.serviceProviders" /></a>
													</td>
												</logic:equal>
												<logic:notEmpty name="locationForm" property="serviceProviderList">	
										      <logic:equal name="locationForm" property="action" value="inactivate">
												    <td align=right><a href="javascript: openWindow('/<msp:webapp/>handleLocationSelection.do?action=viewSuccess&serviceProviderId=<bean:write name="locationListIndex" property="serviceProviderId" />')"><bean:message key="prompt.viewAll" />
                              <bean:message key="prompt.serviceProviders" /></a>
														</td>
												  </logic:equal>
												</logic:notEmpty>      
												--%>												
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table width="100%" border=0 cellpadding="4" cellspacing="1">
											<tr>
												<td class="formDeLabel" nowrap width='1%'><bean:message key="prompt.locationID" /></td>
												<td class="formDe"><bean:write name="locationForm" property="locationCd" /></td>
												<td class="formDeLabel" nowrap width='1%'><bean:message key="prompt.location" /></td>
												<td class="formDe"><bean:write name="locationForm" property="locationName" /></td>
												<td class="formDe">&nbsp;</td>
												<td class="formDe">&nbsp;</td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap width='1%'><bean:message key="prompt.inHouse" /></td>
												<td class="formDe"><bean:write name="locationForm" property="isInHouse" /></td>
												<td class="formDeLabel" nowrap width='1%'><bean:message key="prompt.facility" />&nbsp;<bean:message key="prompt.type" /></td>
												<td class="formDe"><bean:write name="locationForm" property="facilityType" /></td>
												<td class="formDe">&nbsp;</td>
												<td class="formDe">&nbsp;</td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap width='1%'><bean:message key="prompt.phone" /></td>
												<td class="formDe"><bean:write name="locationForm" property="phoneNumber" /></td>
												<td class="formDeLabel" nowrap width=1%><bean:message key="prompt.location" />&nbsp;<bean:message key="prompt.status" /></td>
												<td class="formDe"><bean:write name="locationForm" property="status" /></td>
												<td class="formDe">&nbsp;</td>
												<td class="formDe">&nbsp;</td>
											</tr>
											<logic:notEmpty name="locationForm" property="locationUnitId" >
												<tr>
															<td class="formDeLabel" nowrap width='1%'>Location Unit</td>
															<td class="formDe"><bean:write name="locationForm" property="locationUnitId" /></td>
															<td class="formDeLabel" nowrap width=1%>Location Unit Name</td>
															<td class="formDe"><bean:write name="locationForm" property="locationUnitName" /></td>
															<td class="formDeLabel" nowrap width=1%>Location Unit Status</td>
															<td class="formDe"><bean:write name="locationForm" property="locationUnitStatus" /></td>															
												</tr>
												<tr>
												
												</tr>
											</logic:notEmpty>
											<tr>
												<td class="detailHead" colspan=6><bean:message key="prompt.address" /></td>
											</tr>
											<tr>
												<td colspan=6 class="formDe">
													<table width="100%" border=0 cellpadding="2" cellspacing="1">
														<tr>
															<td class="formDeLabel"><bean:message key="prompt.streetNumber" /></td>
															<td class="formDeLabel" colspan="2"><bean:message key="prompt.streetName" /></td>
														</tr>
														<tr>
															<td class="formDe"><bean:write name="locationForm" property="locationAddress.streetNumber" />
															<td class="formDe" colspan="2"><bean:write name="locationForm" property="locationAddress.streetName" />
														</tr>
														<tr>
															<td class="formDeLabel"><bean:message key="prompt.streetType" /></td>
															<td class="formDeLabel" colspan="2"><bean:message key="prompt.apartmentNumber" /></td>
														</tr>
														<tr>
															<td class="formDe"><bean:write name="locationForm" property="locationAddress.streetType" />
															<td class="formDe" colspan="2"><bean:write name="locationForm" property="locationAddress.aptNumber" />
														</tr>

														<tr>
															<td class="formDeLabel"><bean:message key="prompt.city" /></td>
															<td class="formDeLabel"><bean:message key="prompt.state" /></td>
															<td class="formDeLabel"><bean:message key="prompt.zipCode" /></td>
														</tr>
														<tr>
															<td class="formDe"><bean:write name="locationForm" property="locationAddress.city" />
															<td class="formDe"><bean:write name="locationForm" property="locationAddress.state" />
															<td class="formDe"><bean:write name="locationForm" property="locationAddress.zipCode" />-<bean:write name="locationForm" property="locationAddress.additionalZipCode" />
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							
							<!-- location end -->
							<!-- BEGIN summary CONFLICTING EVENTS TABLE -->
							<!-- JIMS200059292 : Administer Location-Unit Location Inactivation(KK) -->
							<%--<logic:equal name="locationForm" property="action" value="inactivate">							
							<br>
							<table align="center" width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr>
									<td class="detailHead">Future Service Events Scheduled At This Location</td>
								</tr>
								<tr>
									<td valign=top align=center>
										<table width='100%' cellpadding="4" cellspacing="1">
											<tr bgcolor='#cccccc'>
												<td class="subHead"><bean:message key="prompt.eventType" /></td>
												<td class="subHead"><bean:message key="prompt.service" /></td>
												<td class="subHead"><bean:message key="prompt.service" /><bean:message key="prompt.provider" /></td>
												<td class="subHead"><bean:message key="prompt.dateTime" /></td>
												<td class="subHead"><bean:message key="prompt.#Scheduled" /></td>
											</tr>

    									<logic:notEmpty name="locationForm" property="serviceEventsList">	
    										<%int RecordCounter = 0;
    										String bgcolor = "";%> 
    										<logic:iterate id="serviceEvent" name="locationForm" property="serviceEventsList">
    											<tr class=<%RecordCounter++; bgcolor = "alternateRow";
      												if (RecordCounter % 2 == 1)
      													bgcolor = "normalRow";
      												out.print(bgcolor);%>>

    												<td><bean:write name="serviceEvent" property="programName"/></td>  
												    <td><bean:write name="serviceEvent" property="eventType"/></td>    											
    												<td><bean:write name="serviceEvent" property="serviceName"/></td>
    												<td><bean:write name="serviceEvent" property="serviceProviderName"/></td>
    												<td><bean:write name="serviceEvent" property="eventDate" formatKey="date.format.mmddyyyy"/>
    													<bean:write name="serviceEvent" property="eventTime"/>
													</td>
    												<td><bean:write name="serviceEvent" property="currentEnrollment"/></td>

    											</tr>
    									  </logic:iterate>
    								  </logic:notEmpty> 				
												
										</table>
									</td>
								</tr>
							</table>
							<br>
							</logic:equal>--%>
							<!-- END CONFLICTING EVENTS TABLE -->

							<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">
  							<logic:equal name="locationForm" property="action" value="view">
  								<tr>
  									<td align="center">
  										<!--Defect #44098<html:submit property="submitAction"><bean:message key="button.createLocation"></bean:message></html:submit>-->
  										<input type="button" name="submitAction" value="Manage Location Units" onClick="viewJuvLocUnits('<bean:message key="button.manageLocationUnits"/>','<bean:write name="locationForm" property="locationId" />',this.form)"/>
                                        
                                        <jims2:isAllowed requiredFeatures='<%=Features.CS_LOC_INACTIVATE%>'>
  										  <logic:equal name="locationForm" property="statusId" value="A">
	  										<logic:equal name="locationForm" property="activeLocationUnitExists" value="true">	
	  											<input type=button value="<bean:message key="button.inactivateLocation"></bean:message>"
	  											  onClick="submitSelectedItem('<bean:message key="button.inactivateLocation"/>',
	  												'<bean:write name="locationForm" property="locationId" />' ,this.form)" disabled="disabled"/>
	  										</logic:equal>	  										  
	  										<logic:equal name="locationForm" property="activeLocationUnitExists" value="false">	
	  											<input type=button value="<bean:message key="button.inactivateLocation"></bean:message>"
	  											  onClick="submitSelectedItem('<bean:message key="button.inactivateLocation"/>',
	  												'<bean:write name="locationForm" property="locationId" />' ,this.form)"/>
	  										</logic:equal>
  										  </logic:equal>
                                        </jims2:isAllowed>
                                        <jims2:isAllowed requiredFeatures='<%=Features.CS_LOC_ACTIVATE%>'>
  										  <logic:notEqual name="locationForm" property="statusId" value="A">
  											<html:submit property="submitAction"><bean:message key="button.activateLocation"></bean:message></html:submit>																					
  										  </logic:notEqual>
  										</jims2:isAllowed>														
  									</td>
  								</tr>
  								<tr>
  									<td align="center">
  										<html:submit property="submitAction" ><bean:message key="button.back"/></html:submit>&nbsp;
  										<html:submit property="submitAction" ><bean:message key="button.cancel"></bean:message></html:submit>											
  									</td>
  								</tr>
  					    </logic:equal>

							  <logic:equal name="locationForm" property="action" value="create">
  								<tr>
  									<td align="center">
  										<html:submit property="submitAction" ><bean:message key="button.back"/></html:submit>&nbsp;
  										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish" /></html:submit>											
  										<html:submit property="submitAction" ><bean:message key="button.cancel" /></html:submit>											
  									</td>
  								</tr>
 						    </logic:equal>

							  <logic:equal name="locationForm" property="action" value="update">
  								<tr>
  									<td align="center">
  										<html:submit property="submitAction" ><bean:message key="button.back"/></html:submit>&nbsp;
  										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish" /></html:submit>											
  										<html:submit property="submitAction" ><bean:message key="button.cancel" /></html:submit>											
  									</td>
  								</tr>
 						    </logic:equal>

							  <logic:equal name="locationForm" property="action" value="activate">
  								<tr>
  									<td align="center">
  										<html:submit property="submitAction" ><bean:message key="button.back"/></html:submit>&nbsp;
  										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish" /></html:submit>											
  										<html:submit property="submitAction" ><bean:message key="button.cancel" /></html:submit>											
  									</td>
  								</tr>
 						    </logic:equal>
							    
								<logic:equal name="locationForm" property="action" value="inactivate">
  								<tr>
  									<td align="center">
  										<html:submit property="submitAction" ><bean:message key="button.back"/></html:submit>&nbsp;
  										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish" /></html:submit>											
  										<html:submit property="submitAction" ><bean:message key="button.cancel" /></html:submit>											
  									</td>
  								</tr>
 						    </logic:equal>
							  
								<logic:equal name="locationForm" property="action" value="confirmCreate">
  								<tr>
  									<td align="center">
  										<input type="button" name="submitAction" value="Manage Location Units" onClick="viewJuvLocUnits('<bean:message key="button.manageLocationUnits"/>','<bean:write name="locationForm" property="locationId" />',this.form)"/>
  										<html:submit property="submitAction" ><bean:message key="button.locationSearch" /></html:submit>
  									</td>
  								</tr>
 						    </logic:equal>
							  
								<logic:equal name="locationForm" property="action" value="confirmUpdate">
  								<tr>
  									<td align="center">
  										<input type="button" name="submitAction" value="Manage Location Units" onClick="viewJuvLocUnits('<bean:message key="button.manageLocationUnits"/>','<bean:write name="locationForm" property="locationId" />',this.form)"/>
  										<html:submit property="submitAction" ><bean:message key="button.locationSearch" /></html:submit>
  									</td>
  								</tr>
 						    </logic:equal>
							  
								<logic:equal name="locationForm" property="action" value="confirmActivate">
  								<tr>
  									<td align="center">
  										<html:submit property="submitAction" ><bean:message key="button.locationSearch" /></html:submit>
  									</td>
  								</tr>
					     </logic:equal>

										<!-- commented out for #JIMS200043650 
										 -->
							 <logic:equal name="locationForm" property="action" value="confirmInactivate">
  								<tr>
  									<td align="center">
  										<html:submit property="submitAction" ><bean:message key="button.locationSearch" /></html:submit>
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
		<!-- END  TABLE -->
	</div>
</html:form>

<br>
<div align=center><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
