<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- MODIFICATIONS --%>
<!-- 12/20/2007	 Bhavani Jangay - Create JSP (ER JIMS200047257)-->
<!-- 12/05/2008  C Shimek       - defect#55798 correct titles for initiated and open programs block  -->
<!-- 07/10/2012	 Richard Capestani - add Fax (ER JIMS200073843)-->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

<!-- TAG LIBRARIES NEEDED FOR SECURITY -->
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%@ page import="naming.Features" %>

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
<title><bean:message key="title.heading" /> - location/locationDetails.jsp</title>
<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>case_util.js"></script>

<!-- JavaScript for emulated navigation -->
<script type="text/javascript">
	function submitSelectedItem(action,locId,theForm)
	{
		url = "/<msp:webapp/>handleLocationSelectionForCSC.do?submitAction="+action		 			
		changeFormActionURL(theForm, url,true);
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

<body topmargin="0" leftmargin="0">
<html:form action="/handleLocationSelectionForCSC" target="content">
<logic:equal name="locationForm" property="action" value="view">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Location/CSCD_Location.htm#|6">										
</logic:equal> 
<logic:equal name="locationForm" property="action" value="viewInactive">
    <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Location/CSCD_Location.htm#|6">										
</logic:equal>
<logic:equal name="locationForm" property="action" value="create">
 	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Location/CSCD_Location.htm#|3">										
</logic:equal>        
<logic:equal name="locationForm" property="action" value="confirmCreate">
 	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Location/CSCD_Location.htm#|2">										
</logic:equal>
<logic:equal name="locationForm" property="action" value="update">
 	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Location/CSCD_Location.htm#|11">										
</logic:equal>        
<logic:equal name="locationForm" property="action" value="confirmUpdate">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Location/CSCD_Location.htm#|10">										
</logic:equal>
<logic:equal name="locationForm" property="action" value="inactivate">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Location/CSCD_Location.htm#|5">										
</logic:equal>        
<logic:equal name="locationForm" property="action" value="confirmInactivate">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Location/CSCD_Location.htm#|4">										
</logic:equal>
<%--logic:equal name="locationForm" property="action" value="activate">
 	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Location/CSCD_Location.htm#|">										
</logic:equal>        
<logic:equal name="locationForm" property="action" value="confirmActivate">
	<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Location/CSCD_Location.htm#|">										
</logic:equal--%>

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
								<tiles:put name="tabid" value=""/>
							</tiles:insert>						
							<!--tabs end-->
						</td>
					</tr>
					<tr>						
						<td bgcolor='#6699FF'><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
				</table>
				<table width='100%' border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					<tr>						
						<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
					<tr>
						<td valign="top" align="center">

							<!-- BEGIN HEADING TABLE -->
							<table width='100%'>
								<tr>
									<td align="center" class="header"><bean:message key="title.CSCD" />&nbsp;-&nbsp; 
  									<logic:equal name="locationForm" property="action" value="view">
  										<bean:message key="prompt.location" />&nbsp;<bean:message key="prompt.details" />										
                    				</logic:equal> 

  									<logic:equal name="locationForm" property="action" value="viewInactive">
  										<bean:message key="prompt.location" />&nbsp;<bean:message key="prompt.details" />										
                    				</logic:equal>

  									<logic:equal name="locationForm" property="action" value="create">
  										<bean:message key="prompt.create" />&nbsp;<bean:message key="prompt.location" />&nbsp;<bean:message key="prompt.summary" />										
                    				</logic:equal>        
  
  									<logic:equal name="locationForm" property="action" value="confirmCreate">
  										<bean:message key="prompt.create" />&nbsp;<bean:message key="prompt.location" />&nbsp;<bean:message key="prompt.confirmation" />										
                    				</logic:equal>

  									<logic:equal name="locationForm" property="action" value="update">
  										<bean:message key="prompt.update" />&nbsp;<bean:message key="prompt.location" />&nbsp;<bean:message key="prompt.summary" />										
                    				</logic:equal>        

  									<logic:equal name="locationForm" property="action" value="confirmUpdate">
  										<bean:message key="prompt.update" />&nbsp;<bean:message key="prompt.location" />&nbsp;<bean:message key="prompt.confirmation" />										
                    				</logic:equal>

  									<logic:equal name="locationForm" property="action" value="activate">
  										<bean:message key="prompt.activate" />&nbsp;<bean:message key="prompt.location" />&nbsp;<bean:message key="prompt.summary" />										
                    				</logic:equal>        

  									<logic:equal name="locationForm" property="action" value="confirmActivate">
  										<bean:message key="prompt.activate" />&nbsp;<bean:message key="prompt.location" />&nbsp;<bean:message key="prompt.confirmation" />										
                    				</logic:equal>

  									<logic:equal name="locationForm" property="action" value="inactivate">
  										<bean:message key="prompt.inactivate" />&nbsp;<bean:message key="prompt.location" />&nbsp;<bean:message key="prompt.summary" />										
                    				</logic:equal>        

  									<logic:equal name="locationForm" property="action" value="confirmInactivate">
  										<bean:message key="prompt.inactivate" />&nbsp;<bean:message key="prompt.location" />&nbsp;<bean:message key="prompt.confirmation" />										
                    				</logic:equal>        
									</td>
								</tr>
							</table>
						<!-- END HEADING TABLE -->

					<!--  BEGIN CONFIRMATION MESSAGE TABLE -->
					<table width="98%" border="0">
									<tr>
										<logic:equal name="locationForm" property="action" value="confirmInactivate">
										    <td class="confirm">Location successfully inactivated.</td>
	                                    </logic:equal>
										<logic:equal name="locationForm" property="action" value="confirmActivate">
										    <td class="confirm">Location successfully activated.</td>
	                                    </logic:equal>
										<logic:equal name="locationForm" property="action" value="confirmCreate">
										    <td class="confirm">Location successfully created.</td>
	                                    </logic:equal>
										<logic:equal name="locationForm" property="action" value="confirmUpdate">
										    <td class="confirm">Location successfully updated.</td>
	                                    </logic:equal>
									</tr>
					  </table>
					  <!--  END CONFIRMATION MESSAGE TABLE -->
					  
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
									  </ul></td>
                  </logic:equal>               					  
                 					  <logic:equal name="locationForm" property="action" value="update">
									  <td><ul>
										<li>Review information and select Finish button to view confirmation.</li>
										<li>If information is incorrect, select Back button.</li>
									  </ul></td>									  
                  </logic:equal>    
                  					  <logic:equal name="locationForm" property="action" value="confirmCreate">
									  <td><ul>
										<li>Select Location Search button to return to the Location Search page.</li>
									  </ul></td>
                  </logic:equal>    
                  					  <logic:equal name="locationForm" property="action" value="confirmUpdate">
									  <td><ul>
										<li>Select Location Search button to return to the Location Search page.</li>										
									  </ul></td>
                  </logic:equal>    
                  					  <logic:equal name="locationForm" property="action" value="confirmInactivate">
									  <td><ul>
										<li>Select Location Search button to return to the Location Search page.</li>										
									  </ul></td>
                  </logic:equal>    
                  					  <logic:equal name="locationForm" property="action" value="confirmActivate">
									  <td><ul>
										<li>Select Location Search button to return to the Location Search page.</li>										
									  </ul></td>
                  </logic:equal>
								</tr>
							</table>
	              <!-- END INSTRUCTION TABLE -->
							

							<!-- location start -->
							<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
								<tr class="detailHead">
									<td>
										<table width="100%" cellpadding="0" cellspacing="1">
											<tr class="detailHead">
												<td><bean:message key="prompt.location" />
													<bean:message key="prompt.information" />										
												</td>            
                                      
												<logic:equal name="locationForm" property="action" value="view">
                          							<td align="right"><a href="javascript:openWindow('/<msp:webapp/>handleLocationSelectionForCSC.do?submitAction=<bean:message key="button.viewAll" />&locationId=<bean:write name="locationForm" property="locationId" />')"><bean:message key="prompt.viewAll" /> Service Providers</td>
												</logic:equal>
 										    					
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table width="100%" border="0" cellpadding="4" cellspacing="1">
											<tr>
												<td class="formDeLabel" nowrap="nowrap" width='1%'><bean:message key="prompt.locationID" /></td>
												<td class="formDe"><bean:write name="locationForm" property="locationCd" /></td>
												<td class="formDeLabel" nowrap="nowrap" width='1%'><bean:message key="prompt.location" /></td>
												<td class="formDe"><bean:write name="locationForm" property="locationName" /></td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap="nowrap" width='1%'><bean:message key="prompt.inHouse" /></td>
												<td class="formDe"><bean:write name="locationForm" property="isInHouse" /></td>
												<td class="formDeLabel" nowrap="nowrap" width='1%'><bean:message key="prompt.facility" />&nbsp;<bean:message key="prompt.type" /></td>
												<td class="formDe"><bean:write name="locationForm" property="facilityType" /></td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap="nowrap" width='1%'><bean:message key="prompt.phone" /></td>
												<td class="formDe"><bean:write name="locationForm" property="phoneNumber" /></td>
												<td class="formDeLabel" nowrap="nowrap" width='1%'><bean:message key="prompt.fax" /></td>
												<td class="formDe"><bean:write name="locationForm" property="locationFax" /></td>												
											</tr>
											<tr>
												<td class="formDeLabel" nowrap="nowrap" width='1%'><bean:message key="prompt.region"/></td>
												<td class="formDe"><bean:write name="locationForm" property="locationRegion"/></td>
												<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.location" />&nbsp;<bean:message key="prompt.status" /></td>
												<td class="formDe" colspan="3"><bean:write name="locationForm" property="status" /></td>
											</tr>
									
											<tr>
												<td class="detailHead" colspan="4"><bean:message key="prompt.address"/></td>
											</tr>
											<tr>
												<td colspan="4" class="formDe">
													<table width="100%" border="0" cellpadding="2" cellspacing="1">
														<tr>
															<td class="formDeLabel"><bean:message key="prompt.streetNumber" /></td>
															<td class="formDeLabel" colspan="2"><bean:message key="prompt.streetName" /></td>
														</tr>
														<tr>
															<td class="formDe"><bean:write name="locationForm" property="locationAddress.streetNumber" /></td>
															<td class="formDe" colspan="2"><bean:write name="locationForm" property="locationAddress.streetName" /></td>
														</tr>
														<tr>
															<td class="formDeLabel"><bean:message key="prompt.streetType" /></td>
															<td class="formDeLabel" colspan="2"><bean:message key="prompt.apartmentNumber" /></td>
														</tr>
														<tr>
															<td class="formDe"><bean:write name="locationForm" property="locationAddress.streetType" /></td>
															<td class="formDe" colspan="2"><bean:write name="locationForm" property="locationAddress.aptNumber" /></td>
														</tr>

														<tr>
															<td class="formDeLabel"><bean:message key="prompt.city" /></td>
															<td class="formDeLabel"><bean:message key="prompt.state" /></td>
															<td class="formDeLabel"><bean:message key="prompt.zipCode" /></td>
														</tr>
														<tr>
															<td class="formDe"><bean:write name="locationForm" property="locationAddress.city" /></td>
															<td class="formDe"><bean:write name="locationForm" property="locationAddress.state" /></td>
															<td class="formDe"><bean:write name="locationForm" property="locationAddress.zipCode" />-<bean:write name="locationForm" property="locationAddress.additionalZipCode" /></td>
														</tr>														
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<!-- location end -->
							
							<div class="spacer4px"></div>
									
							<!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">
  							<logic:equal name="locationForm" property="action" value="view">
  								<tr>
  									<td align="center">
  										<jims2:isAllowed requiredFeatures='<%=Features.CSCD_LOC_UPDATE%>'>
  									    	<input type="button" name="submitAction" value="Update Location" onClick="submitSelectedItem('<bean:message key="button.updateLocation"/>','<bean:write name="locationForm" property="locationId" />',this.form)" />                
  									    </jims2:isAllowed>  
  									    <jims2:isAllowed requiredFeatures='<%=Features.CSCD_LOC_INACTIVATE%>'>
  											<logic:equal name="locationForm" property="statusId" value="A">	
  												<input type="button" value="<bean:message key="button.inactivateLocation"></bean:message>"
  											  		onClick="submitSelectedItem('<bean:message key="button.inactivateLocation"/>',
  													'<bean:write name="locationForm" property="locationId" />' ,this.form)"/>
  											</logic:equal>
  										</jims2:isAllowed>
  										<jims2:isAllowed requiredFeatures='<%=Features.CSCD_LOC_ACTIVATE%>'>
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
									<jims2:isAllowed requiredFeatures='<%=Features.CSCD_LOC_SEARCH%>'>
  										<tr>
  											<td align="center">
  												<html:submit property="submitAction" ><bean:message key="button.locationSearch" /></html:submit>
  											</td>
  										</tr>
  									</jims2:isAllowed>		
 						   		</logic:equal>
							  
								<logic:equal name="locationForm" property="action" value="confirmUpdate">
									<jims2:isAllowed requiredFeatures='<%=Features.CSCD_LOC_SEARCH%>'>
  										<tr>
  											<td align="center">
  												<html:submit property="submitAction" ><bean:message key="button.locationSearch" /></html:submit>
  											</td>
  										</tr>
  									</jims2:isAllowed>	
 						    </logic:equal>
							  
								<logic:equal name="locationForm" property="action" value="confirmActivate">
									<jims2:isAllowed requiredFeatures='<%=Features.CSCD_LOC_SEARCH%>'>
  										<tr>
  											<td align="center">
  												<html:submit property="submitAction" ><bean:message key="button.locationSearch" /></html:submit>
  											</td>
  										</tr>
  									</jims2:isAllowed>	
					 		    </logic:equal>

										<!-- commented out for #JIMS200043650 
										 -->
							 <logic:equal name="locationForm" property="action" value="confirmInactivate">
								 <jims2:isAllowed requiredFeatures='<%=Features.CSCD_LOC_SEARCH%>'>
  									<tr>
  										<td align="center">
  											<html:submit property="submitAction" ><bean:message key="button.locationSearch" /></html:submit>
  										</td>
  									</tr>
  								</jims2:isAllowed>	
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
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
