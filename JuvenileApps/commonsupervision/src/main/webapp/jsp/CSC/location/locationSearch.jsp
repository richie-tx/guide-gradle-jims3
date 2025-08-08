<!DOCTYPE HTML>
<%-- MODIFICATIONS --%>
<!-- 12/20/2007	 Bhavani Jangay - Create JSP (ER JIMS200047257)-->


<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

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
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/csbase.css" />
<html:base />
<title><bean:message key="title.heading" /> - location/locationSearch.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/administerServiceProviderHCJPD/locationSearch.js"></script>

</head>
<body topmargin="0" leftmargin="0">

<html:form action="/displayLocationSearchResultsForCSC" target="content" focus="locationCd">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Location/CSCD_Location.htm#|7">
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
						<%--script language="JavaScript">renderTabs("")</script--%>
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
							<!-- BEGIN HEADING TABLE -->
							<table width="100%">
							  	<tr>
							    	<td align="center" class="header"><bean:message key="title.CSCD" /> - <bean:message key="prompt.location" /> <bean:message key="title.search" /></td>
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
							<table width="98%" border="0">
								<tr>
									<td><ul>
										<li>Enter the required fields for the search you wish to make.</li>
									</ul></td>
								</tr>
								<tr>
		                           <td class="required" colspan="4"> <bean:message key="prompt.3.diamond"/> At least 1 Required</td>
		                        </tr>
                                <tr>
                                   <td colspan="4" class="required">+ Street Name required if searching by Street Number.</td>
                                </tr>
							</table>
							 <!-- SEARCH start -->
					        <table width="98%" align="center" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
					          	<tr class="detailHead">
					              <td><bean:message key="title.search" />
		                              <bean:message key="prompt.locations" /></td>
					            </tr>
					          	<tr>
					              <td>
					              	<table width="100%" cellpadding="2" cellspacing="1">
					              	<tr>
					              		<td class="formDeLabel" nowrap><bean:message key="prompt.locationID" /></td>
										<td class="formDe" colspan="3"><html:text name="locationForm" property="locationCd" size="10" maxlength="10"/></td>
					              	</tr>
						            <tr>
						              <td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.serviceProviderName" /></td>
						              <td class="formDe" colspan="3" >						              	
										<html:text name="locationForm" property="serviceProviderName" size="50" maxlength="100" />
						              </td>	             
						            </tr>
						            <tr>
						            	<td class="formDeLabel"><bean:message key="prompt.region" /></td>
							              <td class="formDe" >
							              	<html:select property="locationRegionId" name="locationForm">
											<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  							                	
						                	<html:optionsCollection property="locationRegionList"  value="code" label="description" name="locationForm" />								                	
						                </html:select>											
						                </td>	
						                
						              	<td class="formDeLabel"><bean:message key="prompt.inHouse" /></td>
							            <td class="formDe">
							              	<html:select property="isInHouse" name="locationForm">
											<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  							                	
						                	<html:option value="1">YES</html:option>
						                	<html:option value="0">NO</html:option>
						
						                </html:select>											
						                </td>		
						    
						            </tr>
						            <tr>
						              <td class="formDeLabel" nowrap><bean:message key="prompt.location" />
                                                                     <bean:message key="prompt.name" /></td>
						              <td class="formDe">
						              	<html:text name="locationForm" property="locationName" maxlength="35" size="35"/></td>
						              	
						              	
						              <td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.location" />&nbsp;<bean:message key="prompt.status" /></td>
						              <td class="formDe">
						              	<html:select property="statusId" name="locationForm">
											<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  												   
											   <html:optionsCollection property="locationStatusList"  value="code" label="description"  name="locationForm"/>
						                </html:select>
					                  </td>
						            </tr>      
						            
						            <tr>
						              <td class="formDeLabel" colspan="4"><bean:message key="prompt.address" /></td>
						            </tr>
						            <tr>
						              <td colspan="4" class="formDe">
							              <table width="100%" border="0" cellpadding="2" cellspacing="1">				              				          
							                <tr>
							                  <td class="formDeLabel">+<bean:message key="prompt.streetNum" /></td>
							                  <td class="formDeLabel" colspan="3"><bean:message key="prompt.streetName" /></td>
							                  <!--<td class=formDeLabel>Street Type</td>
							                  <td class=formDeLabel>Apt/Suite</td>-->
							                </tr>
							                <tr>
							                  <td class="formDe">
						              	        <html:text property="locationAddress.streetNumber" maxlength="15" size="6"/>
							                  </td>
							                  <td class="formDe" colspan="3">
						              	        <html:text property="locationAddress.streetName" maxlength="50" size="35"/>
							                  </td>
							                  <%--td class=formDe>
							                    <input type="text" name="apartmentNumber" value="" size="6" maxlength="20">
							                  </td--%>
							                </tr>
							                <tr>
							                  <td class="formDeLabel"><bean:message key="prompt.city" /></td>
							                  <td class="formDeLabel"><bean:message key="prompt.state" /></td>
							                  <td class="formDeLabel" colspan="2"><bean:message key="prompt.zipCode" /></td>
							                </tr>
							                <tr>
							                  <td class="formDe">
						              	        <html:text property="locationAddress.city" maxlength="25" size="25"/>
							                  </td>
							                  <td class="formDe">
											    <html:select property="locationAddress.stateId" name="locationForm">
											        <html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
											        <html:optionsCollection property="stateList"  value="code" label="description"  name="locationForm"/>
											    </html:select>  
							                  </td>
							                  <td class="formDe" colspan="2">
						              	        <html:text property="locationAddress.zipCode" maxlength="5" size="5"/>
							                    <!---<input type="text" name="zipExt" value="" size="4" maxlength="4">-->
							                  </td>
							                </tr>
							              </table>
							          </td>
					          </tr>
				            </table>
				            <!-- BEGIN BUTTON TABLE -->
							<table border="0" width="100%">
							  	<tr>
							    	<td align="center">
										<html:submit property="submitAction" onclick="return validateSearchFields() && disableSubmit(this, this.form);"><bean:message key="button.submit"></bean:message></html:submit>&nbsp;
										<input type="button" onclick="goNav('/<msp:webapp/>displayLocationSearchForCSC.do')" value="<bean:message key='button.refresh'/>"/>
									</td>
							  	</tr>
							</table>
							<!-- END BUTTON TABLE -->
          
          			  </table>
						<!-- search END-->
						<!-- BEGIN BUTTON TABLE -->
						<table border="0" width="100%">
						  	<tr>
						    	<td align="center">
						    		<jims2:isAllowed requiredFeatures='<%=Features.CSCD_LOC_CREATE%>'>
										<html:submit property="submitAction" ><bean:message key="button.createLocation"></bean:message></html:submit>&nbsp;
									</jims2:isAllowed>
									<html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
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
<!-- END  TABLE -->
</div>
<br>
</html:form>

<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
