<!DOCTYPE HTML>
<!-- 03/30/2004	 Hien Rodriguez - Create JSP -->
<!-- 08/18/2004	 Hien Rodriguez - Refactor Action & JSP with new PD Event/Command -->
<!-- 11/04/2004  Hien Rodriguez - ER13690 -->
<!-- 05/10/2005  C Shimek - converted to new layout -->
<%-- 02/13/2006  C Shimek - Corrected prompt values for required field indicator --%> 
<%-- 05/01/2006  C Shimek - Defect#30963 remove extra data for Update Agency Information title --%>
<%-- 06/14/2006  C Shimek - Defect#31092 change subsystem property value form subsystemName to featureCategory to work with action --%>
<%-- 08/08/2006  C Shimek - Activity#34111 Add refresh buttons --%>
<%-- 08/18/2006  C Shimek - Defect#31092 revise subsystem from input text to drop down list --%>
<%-- 11/07/2006  C Shimek - Defect#36825 added Agency Code search field --%>
<!-- 01/12/2007  C Shimek - #38306 add multiple submit functionality  -->
<!-- 05/10/2007	 C Shimek - #41780 add sort to current/selected features list display, also to agency search results and selected and feature search results lists  -->
<!-- 02/11/2008  C Shimek - increase role name length to 30(should have been done when create Defect #45039) -->
<!-- 02/05/2009  C Shimek - #56860 add Back to Top  -->
<!-- 10/19/2015  R Young  - #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %> 
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%-- <msp:login /> --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading" /> - roleUpdate.jsp</title>
<!-- STRUTS VALIDATIONS-->
<html:javascript formName="roleForm" />

<!-- AUTO TAB JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/roles/roleUpdate.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript">
function nogo(){
  alert("Agency pop-up detail is not currently available.\nPlease retry later.");
}

function validateCheckBoxSelect()
{
	var featureSel = document.getElementsByName("featureId");
	if (featureSel.length == 0){
		return true;
	}	
	for (x = 0; x<featureSel.length; x++){
		if (featureSel[x].checked == "true"){
			return true;
		}
	}
	alert("No feature selected to Remove.");
	return false;
}

</script>
</head>
<!--END HEADER TAG--> 

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<html:form action="/displayRoleSummary" target="content">
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|229"> 
	<input type="hidden" name="refreshButton" value="">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
  <tr>
    <td align="center" class="header"><bean:message key="title.updateRole"/></td>
  </tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<table align="center" width="98%" border="0">
   <tr>
     <td>
	  <ul>
        <li>Make changes as necessary then select Next.</li>
        <li>Select Find to search for agencies and features.</li>        
      </ul>
	</td>
  </tr>
  <tr>
  	<td class="required"><bean:message key="prompt.msa.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN FORM -->

	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="98%" align="center" valign="top">
				<table class="borderTableBlue"  cellpadding="2" cellspacing="0" width="98%">
					<tr class="detailHead">
						<td class="detailHead"><bean:message key="prompt.update" />&nbsp;<bean:message key="prompt.roleInfo"/></td>
						<td align="right"><img src=/<msp:webapp/>images/step_1_edit.gif hspace=0 vspace=0></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<table width="100%" cellpadding=2 cellspacing=1>
								<tr>
									<td nowrap class="formDeLabel"><bean:message key="prompt.msa.diamond"/><bean:message key="prompt.roleName"/></td>
									<td class="formDe"><html:text property="roleName" size="30" maxlength="30"/></td>
								</tr>
								<tr>
									<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.roleDescription"/></td>
									<td class="formDe"><html:text property="roleDescription" size="52" maxlength="50"/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<!-- END NAME AND DESCRIPTION TABLE -->
				<br>
				<!-- BEGIN AGENCY TABLE -->
				<table class="borderTableBlue" cellpadding="2" cellspacing="0" border="0" width="98%">
					<tr class="detailHead">
						<td class="detailHead">
							<logic:equal name="roleForm" property="masterAdmin" value="Y">						
								<logic:equal name="roleForm" property="agencyUpdatable" value="true">	
									<bean:message key="prompt.update" />
								</logic:equal>
							</logic:equal>	
							<bean:message key="prompt.agencyInfo" />
						</td>
						<td align="right"><img src=/<msp:webapp/>images/step_2_edit.gif hspace=0 vspace=0></td>
					</tr>
					<logic:notEqual name="roleForm" property="masterAdmin" value="Y">
					<tr>
						<td colspan="2" align="center">
							<table width="100%" border="0" cellpadding="2" cellspacing="1">
								<tr>
								   <td class="formDeLabel" class="detailHead"><bean:message key="prompt.agencyName"/></td>
								</tr>
						    <logic:iterate id="agencyNameIndex" name="roleForm" property="currentAgencies" indexId="index1">
						    <tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
         					    <td>
                                  	<bean:write name="agencyNameIndex" property="agencyName"/><input type="hidden" name="agency">
                                </td>
                            </tr>      	
                            </logic:iterate> 
                            <logic:empty name="roleForm" property="currentAgencies">
                            	<tr><td>Agencies not available.</td></tr>
                            </logic:empty>             	
							</table>
						</td>
					</tr>
					</logic:notEqual>
					<logic:equal name="roleForm" property="masterAdmin" value="Y">
					<tr>
					<td colspan="2">
						<table width="100%" border="0" cellpadding="2" cellspacing="1">
						    <logic:equal name="roleForm" property="agencyUpdatable" value="false">
								<tr>
								   <td class="formDeLabel" class="detailHead"><bean:message key="prompt.agencyName"/></td>
								</tr>
						    <logic:iterate id="agencyNameIndex" name="roleForm" property="currentAgencies" indexId="index2">
						    <tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
         					    <td>
                                  	<bean:write name="agencyNameIndex" property="agencyName"/><input type="hidden" name="agency">
                                </td>
                            </tr>      	
                            </logic:iterate> 
                            <logic:empty name="roleForm" property="currentAgencies">
                            	<tr><td>Agencies not available.</td></tr>
                            </logic:empty>             	

						    </logic:equal>						
						    <logic:equal name="roleForm" property="agencyUpdatable" value="true">
							<tr class="detailHead">
								<td class="detailHead" colspan="2">Select <bean:message key="prompt.agencyforRole"/><%-- (at least 1 search field required for search) --%></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.agencyName"/></td>
								<td class="formDe"><html:text property="agencyName" size="62" maxlength="60"/></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.agencyCode"/></td>
								<td class="formDe"><html:text property="agencyCode" size="3" maxlength="3"/></td>
							</tr>

							<tr>
								<td class="formDeLabel"><bean:message key="prompt.agencyType"/></td>
								<td class="formDe">
						           <html:select property="agencyTypeId">
	            				      <html:option value=""><bean:message key="select.generic" /></html:option>
					       			  <html:optionsCollection property="agencyTypes" value="code" label="description" /> 
				  	  	   			</html:select> 								
								</td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.jmcRepresentative"/></td>
								<td class="formDe">
						           <html:select property="jmcRepId">
	            				      <html:option value=""><bean:message key="select.generic" /></html:option>
						       	      <html:optionsCollection property="jmcReps" value="code" label="description" /> 
						  	  	   </html:select> 								
								</td>
							</tr>
							<tr>
								<td class="formDeLabel">&nbsp;</td>
								<td class="formDe">
									<html:submit property="submitAction" onclick="return validateAgencySearchFields(this.form) && disableSubmit(this, this.form);">
					  					<bean:message key="button.findAgencies"></bean:message>
		 							</html:submit>	
									<html:submit property="submitAction" onclick="javascript: this.form.refreshButton.value ='Agency';">
										<bean:message key="button.refresh" />
									</html:submit>
		 						</td>
							</tr>
							<tr>
							    <td colspan="2">
        		            <!-- BEGIN ERROR TABLE -->
    	            		        <table width="100%">
        	                		    <tr>		  
											<td align="center" class="errorAlert">
												<bean:write name='roleForm' property='agencyFindErrorMessage'/>
											</td>		  
									    </tr>   	  
								    </table>
                    		<!-- END ERROR TABLE -->	 			
							</tr>
							<logic:notEmpty name="roleForm" property="availableAgencies">
							<tr>
								<td colspan="2" align="center">
							   	<bean:write name="roleForm" property="agencySearchResultSize"/> agency matches found.
							</td>
					    	</tr>              
							<tr>
								<td colspan="2"> 
								<script type="text/javascript">
								renderScrollingArea(<bean:write name="roleForm" property="agencySearchResultSize"/>);									
								</script>
							<!-- agency checkbox selection start-->
									<table border="0" width="100%" cellspacing="1" cellpadding="2">
									<thead>
									<tr class="formDeLabel">
										<td class="boldText">
											<input type="checkbox" name="selectAllAgencies" OnClick="allAgenciesSelect(this, 'selectedAgencies')">Select All Agencies
										</td>
									</tr>	
									</thead>
									<tbody>
										<jims:sortResults beanName="roleForm" results="availableAgencies" primaryPropSort="agencyName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="A1" levelDeep="1" hideMe="true"/>									
								        <logic:iterate id="agenciesIndex" name="roleForm" property="availableAgencies" indexId="index3" > 
											<tr class="<%out.print((index3.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
												<td class="boldText">
                                    			        <input type="checkbox" name="selectedAgencies" value=<bean:write name='agenciesIndex' property='agencyId'/>> 
				                                        <a href="#" onclick="nogo()" title='View <bean:write name="agenciesIndex" property="agencyName" /> details' >
                				                        <bean:write name='agenciesIndex' property='agencyName'/> </a>
												</td>
											</tr>
									    </logic:iterate>
									 </tbody>		
									</table>
						 <!-- agency checkbox selection end -->	
								</div>
								</td>
							</tr>
							<tr>
					           <td align="center" colspan="2">
					              <html:submit property="submitAction"><bean:message key="button.addAgencies"></bean:message></html:submit>
					           </td>							    
				            </tr> 
							</logic:notEmpty>
							
							<logic:notEmpty name="roleForm" property="currentAgencies"> 
       						<tr>
							   <td colspan="2" class="formDeLabel"><bean:message key="prompt.msa.diamond"/>Current/Selected Agencies List</td>
							</tr>
							<tr>
       							<td colspan="2" align="center">	
       							<bean:size id="currentAgencySize" name="roleForm" property="currentAgencies" />	       							
				   				<script type="text/javascript">
								renderScrollingArea(<bean:write name="currentAgencySize" />);									
								</script>				   				
									<table width="100%" cellspacing="0" cellpadding="2">
									   <jims:sortResults beanName="roleForm" results="currentAgencies" primaryPropSort="agencyName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="A2" hideMe="true" />				
										<logic:iterate id="currentIndex" name="roleForm" property="currentAgencies" indexId="index4">
							            <tr class="<%out.print((index4.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
											<td width="1%" valign="top">
											    <logic:equal name="roleForm" property="agencyUpdatable" value="false">
											       <a href="/<msp:webapp/>handleRoleAgencyRemove.do?agencyId=<bean:write name="currentIndex" property="agencyId"/>" title='Remove <bean:write name="currentIndex" property="agencyName"/>'>Remove</a>
											    </logic:equal>
											</td>
											<td class="boldText">
											    <bean:write name="currentIndex" property="agencyName"/>
							    				<input type="hidden" name="agency">
											</td>
										</tr>
										</logic:iterate>
									</table>	
								</div>  
								</td>
							</tr> 	
			  			    </logic:notEmpty>
						</logic:equal>
					</table>
				</td>
				</tr> 
				</logic:equal>
				</table>
				<!-- END AGENCY TABLE --> 
				<br>
				<!-- BEGIN FEATURE TABLE -->
				<table class="borderTableBlue"  cellpadding="2" cellspacing="0" border="0" width="98%">
					<tr class="detailHead">
						<td class="detailHead">Update Features</td>
						<td align="right">
							<img src=/<msp:webapp/>images/step_3_edit.gif hspace=0 vspace=0>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<table border="0" cellspacing="1" cellpadding="2" width="100%">
								<tr>
									<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.featureName"/></td>
									<td class="formDe"><html:text property="featureName" size="50" maxlength="50"/></td>								
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.subsystem"/></td>
									<td class="formDe">
										<!-- html:text property="featureCategory" size="35" maxlength="35"/>  -->
						           		<html:select property="featureCategoryId">
		           				      		<html:option value=""><bean:message key="select.generic" /></html:option>
							       			<html:optionsCollection property="jims2Subsystems" value="code" label="description" /> 
					  	  	   			</html:select> 								
									</td>
								</tr>
								<tr>    
									<td class="formDeLabel"></td>
									<td class="formDe">		
										<html:submit property="submitAction" onclick="return validateFeatureSearchFields(this.form) && disableSubmit(this, this.form);">
										   <bean:message key="button.findFeatures"></bean:message>
			  							</html:submit>
										<html:submit property="submitAction" onclick="javascript: this.form.refreshButton.value ='Features';">
											<bean:message key="button.refresh" />
										</html:submit>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
				    	<td colspan="2">
	        	    <!-- BEGIN ERROR TABLE -->
          		        	<table width="100%">
	               		    	<tr>		  
									<td align="center" class="errorAlert">
										<bean:write name='roleForm' property='featureFindErrorMessage'/>
									</td>		  
							    </tr>   	  
						    </table>
           			<!-- END ERROR TABLE -->				
					</tr>
					<logic:equal name="roleForm" property="featuresSearched" value="Y">  				
						<bean:size id="searchResultSize" name="roleForm" property="availableFeatures"/>
					<tr>
						<td colspan="2" align="center">
							<bean:write name="searchResultSize"/> search results for
							<logic:notEqual name="roleForm" property="featureName" value="">
								<bean:message key="prompt.featureName"/>:<bean:write name="roleForm" property="featureName"/> 
							</logic:notEqual>
							<logic:notEqual name="roleForm" property="subsystmName" value="">						   
								<bean:message key="prompt.subsystemName"/>:<bean:write name="roleForm" property="subsystemName"/>
							</logic:notEqual>
						</td>
					</tr>
					</logic:equal>
					<logic:notEmpty name="roleForm" property="availableFeatures">  				
					<tr>
					   <td colspan="2">	
						<div class="scrollingDiv200">
				<!--checkboxes start-->
							<table width="100%" cellspacing="1" cellpadding="4" border="0">
								<thead>
								<tr class="formDeLabel">
									<td class="boldText">
										<input type="checkbox" name="selectAllFeatures" OnClick="allFeaturesSelect(this, 'selectedFeatures')">Select All Features
									</td>
								</tr>	
								</thead>
								<tbody>
								<logic:iterate id="featuresIndex" name="roleForm" property="availableFeatures" indexId="featureCount"> 
									<tr class="alternateRow" height="100%">
										<td>
											<logic:empty name="featuresIndex" property="childFeatures">
												<logic:present name="featuresIndex" property="childFeatures">
												    <input type="checkbox" disabled>
													<bean:write name="featuresIndex" property="featureName"/>
												</logic:present>	
											</logic:empty>
											
											<!-- if parent has null children, display the parent -->
											<logic:notPresent name="featuresIndex" property="childFeatures">
												<input type="checkbox" id="<bean:write name="featureCount"/>" name="selectedFeatures" value="<bean:write name="featuresIndex" property="featureId"/>" >
												<bean:write name="featuresIndex" property="featureName"/>
											</logic:notPresent>
											
											<!-- Only display parent which has children.-->
											<logic:notEmpty name="featuresIndex" property="childFeatures">
												<bean:size id="childFeaturesSize" name="featuresIndex" property="childFeatures"/>
												<!-- only display expand image when parent has children-->
												<logic:greaterThan name="childFeaturesSize" value="0">
													<a href="javascript:showHideMulti('featuresExpandContract<bean:write name="featureCount"/>', '<bean:write name="featuresIndex" property="featureId"/>', <bean:write name="childFeaturesSize"/>, '/<msp:webapp/>')">
													<img src="/<msp:webapp/>images/expand.gif" name="featuresExpandContract<bean:write name="featureCount"/>" border=0></a>
												</logic:greaterThan>
													<input type="checkbox" id="<bean:write name="featureCount"/>" name="selectedFeatures" value="<bean:write name="featuresIndex" property="featureId"/>" onclick="checkChildren(this)" >
													<bean:write name="featuresIndex" property="featureName"/>
											</logic:notEmpty>
										</td>	
									</tr>
									
									<logic:notEmpty name="featuresIndex" property="childFeatures">
									<jims:sortResults beanName="featuresIndex" results="childFeatures" primaryPropSort="featureName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="F1" levelDeep="1" hideMe="true"/>
									<logic:iterate id="childFeaturesIndex" name="featuresIndex" property="childFeatures" indexId="childIndexCount">
										<tr id="<bean:write name="featuresIndex" property="featureId"/><bean:write name="childIndexCount"/>" class="hidden" height="100%">
											<td style="padding-left:15px">
												<input type="checkbox" id="childOf<bean:write name="featureCount"/>+<bean:write name="childIndexCount"/>" name="selectedFeatures" value="<bean:write name="childFeaturesIndex" property="featureId"/>" >
												<bean:write name="childFeaturesIndex" property="featureName"/>
											</td>
										</tr>
									</logic:iterate>
									</logic:notEmpty>
									
								</logic:iterate>
								</tbody>
							</table>	
				<!--checkbox end-->
						</div>  
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
						    <html:submit property="submitAction"><bean:message key="button.addFeatures"></bean:message></html:submit>
						</td>							    
					</tr>	
					</logic:notEmpty>			    
					
					<logic:notEmpty name="roleForm" property="currentFeatures">
					<tr class="formDeLabel">
						<td colspan="2"><bean:message key="prompt.msa.diamond" />Current/Selected
						Features List</td>
					</tr>
					<tr>
						<td colspan="2"><bean:size id="currentFeaturesSize"
							name="roleForm" property="currentFeatures" /> <script
							type="text/javascript">
						renderScrollingArea(<bean:write name="currentFeaturesSize" />);									
						</script>
						<table width="100%" cellspacing="0" cellpadding="4">
							<jims:sortResults beanName="roleForm" results="currentFeatures"
								primaryPropSort="featureName" primarySortType="STRING"
								defaultSort="true" defaultSortOrder="ASC" sortId="F2"
								hideMe="true" />
							<logic:iterate id="currentIndex" name="roleForm"
								property="currentFeatures" indexId="index5">
								<tr
									class='<%out.print((index5.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>' height="100%">
									<td align="center" width="1%"><input type="checkbox" id="index5"
										name="selectedFeatures"
										value="<bean:write name='currentIndex' property="featureId"/>">
									</td>
									<td class="boldText"><bean:write name="currentIndex"
										property="featureName" /> <input type="hidden" name="feature">
									</td>
								</tr>
							</logic:iterate>
						</table>
						<div></div>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2"><html:submit
							property="submitAction"
							onclick="return validateCheckBoxSelect(this.form) &amp;&amp; disableSubmit(this, this.form)">
							<bean:message key="button.removeSelected" />
						</html:submit></td>
					</tr>
				</logic:notEmpty>
				</table>
				<!-- END FEATURES TABLE -->
				<br>
				<!-- BEGIN BUTTON TABLE -->
				<table width="100%" cellspacing="0" cellpadding="4">
					<tr>
						<td align="center" colspan="2">
							<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
								<bean:message key="button.back"></bean:message></html:button>&nbsp;		
							<html:submit property="submitAction" onclick="return validateRequiredFields(this.form) && disableSubmit(this, this.form);"><bean:message key="button.next"/></html:submit>&nbsp;		          
							<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
						</td>
					</tr>
				</table>
			</td>
		</tr>							
	</table>
</html:form>
<!-- END FORM -->
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
<!--END BODY TAG-->
</html:html>
