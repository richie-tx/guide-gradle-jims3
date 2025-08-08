<!DOCTYPE HTML>
<!-- Used to search create Role Features -->
<!--MODIFICATIONS -->
<!-- CShimek 04/01/2005	 Create JSP -->
<%-- CShimek 02/13/2006  Corrected prompt values for required field indicator --%> 
<%-- CShimek 03/31/2006  Per ER#26357, revised reset button to refresh button --%>
<%-- CShimek 03/31/2006  Defect #29629 add cancel button to page --%>
<%-- CShimek 08/18/2006  Defect#31092 revise subsystem from input text to drop down list --%>
<%-- CShimek 08/13/2006  Defect#34863 added checkEnterKey() to body tag to correct enter key problem --%>
<%-- CShimek 04/20/2007  #41358 revise Refresh button to set value for action method --%>
<%-- CShimek 04/20/2007  #41376 added sort tag to selected features --%>
<%-- CShimek 02/05/2009  #56860 add Back to Top  --%>
<%-- CShimek 03/27/2009  #58146 Add script call to deselect parent feature whenever child feature is deselected. --%>
<!-- RYoung  10/19/2015  #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

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
<%--msp:login / --%>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading"/> - roleCreate3.jsp</title>
<!-- JAVASCRIPT FILES -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/roles/roleCreate3.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>

</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN HEADING TABLE -->
	<table width="100%">
		<tr>
			<td align="center" class="header">
				<logic:equal name="roleForm" property="action" value="copy" >    	
					<bean:message key="title.copyRole"/> - Select Features
				</logic:equal>   
				<logic:equal name="roleForm" property="action" value="create" >
					<bean:message key="title.createRole"/> - Select Features
				</logic:equal>              
			</td>
		</tr>
	</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
	<table width="100%">
		<tr>
		 	<td align="center" class="errorAlert">
				<bean:write name='roleForm' property='featureFindErrorMessage'/>
			</td>	                            		  
		</tr>   	  
	</table>
<!-- END ERROR TABLE -->				
<!-- BEGIN INSTRUCTION TABLE -->
	<table align="center" width="98%" border="0">
		<tr>
			<td>
				<ul>
					<li>Enter Feature Name and SubSystem and select Next.</li>
					<li>Enter Feature Name and/or Subsystem and select Find to search for existing features.</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td class="required"><bean:message key="prompt.msa.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
		</tr>  
	</table>
<!-- END INSTRUCTION TABLE -->
<html:form action="/displayRoleSummary" target="content" focus="featureName">		
	<logic:equal name="roleForm" property="action" value="copy" >    	
 		 <input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|272">
    </logic:equal>   
	<logic:equal name="roleForm" property="action" value="create" >
   		 <input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|62"> 
	</logic:equal> 
	<input type="hidden" name="refreshButton" value="">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="98%" align="center" valign="top">
<!-- BEGIN ROLE INFORMATION TABLE -->
				<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
					<tr class="detailHead">
						<td class="detailHead"><bean:message key="prompt.roleInfo"/></td>
						<td align="right"><img src="/<msp:webapp/>images/step_1.gif" hspace="0" vspace="0"></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<table width="100%" cellpadding="4">
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.roleName"/></td>
									<td class="formDe"><bean:write name="roleForm" property="roleName"/></td>
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.description"/></td>
									<td class="formDe"><bean:write name="roleForm" property="roleDescription"/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
<!-- END ROLE INFORMATION TABLE -->
		<br>
<!-- BEGIN AGENCY INFORMATION TABLE -->
				<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
					<tr class="detailHead">
						<td class="detailHead">
						    <logic:equal name="roleForm" property="masterAdmin" value="Y">
						       Selected <bean:message key="prompt.agenciesDeptforRole"/>
						    </logic:equal>   
						    <logic:notEqual name="roleForm" property="masterAdmin" value="Y">
						       <bean:message key="prompt.agencyInfo"/>
						    </logic:notEqual>   
	
						</td>
						<td align="right"><img src="/<msp:webapp/>images/step_2.gif" hspace="0" vspace="0"></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<table width="100%" border="0" cellpadding="2">
								<tr>
									<td class="formDeLabel" class="detailHead"><bean:message key="prompt.agencyName"/></td>
								</tr>
							    <logic:iterate id="agencyNameIndex" name="roleForm" property="currentAgencies" indexId="index">
								    <tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
		         					    <td>
		                                  	<bean:write name="agencyNameIndex" property="agencyName"/>
		                                </td>
		                            </tr>      	
	                            </logic:iterate> 
	                            <logic:empty name="roleForm" property="currentAgencies">
	                            	<tr><td>Agencies not available.</td></tr>
	                            </logic:empty>             	
							</table>
						</td>
					</tr> 
				</table>
<!-- END AGENCY INFORMATION TABLE -->
			<br>
<!-- BEGIN FEATURES TABLE -->
				<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">
					<tr class="detailHead">
						<td class="detailHead"><bean:message key="prompt.msa.diamond" /> Select <bean:message key="prompt.featureName"/></td>
						<td align="right"><img src="/<msp:webapp/>images/step_3.gif" hspace="0" vspace="0"></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
<!-- BEGIN FEATURES SEARCH TABLE -->
							<table width="100%" cellpadding="4">
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.featureName"/></td>
									<td class="formDe"><html:text property="featureName" size="50" maxlength="50"/></td>								
								</tr>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.subsystem"/></td>
									<td class="formDe">
										<%--html:text property="featureCategory" size="50" maxlength="50"/ --%>
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
						  					<bean:message key="button.refresh"></bean:message>
			 							</html:submit>	            				     									
									</td>
								</tr>
							</table>
<!-- END FEATURES SEARCH TABLE -->
						</td>
					</tr>
					<logic:equal name="roleForm" property="featuresSearched" value="Y">  
						<bean:size id="searchResultSize" name="roleForm" property="availableFeatures"/>				
						<tr>
							<td colspan="2" align="center">
							   <bean:write name="searchResultSize"/> search results for
		                           <logic:notEqual name="roleForm" property="featureName" value="">
				 					   <bean:message key="prompt.featureName"/>:<bean:write name="roleForm" property="featureName"/> 
								   </logic:notEqual>
		                           <logic:notEqual name="roleForm" property="subsystemName" value="">						   
									   <bean:message key="prompt.subsystemName"/>:<bean:write name="roleForm" property="subSystemName"/>
		   						   </logic:notEqual>
							</td> 
						</tr>
					</logic:equal>
		
		            <logic:notEmpty name="roleForm" property="availableFeatures">  				
						<tr>
						    <td colspan="2" align="center">	
							<div class="scrollingDiv200" >
							<!--checkboxes start-->
								<table width="100%" cellspacing="0" cellpadding="4">
									<tr class="formDeLabel" height="100%">  
										<td class="boldText">
										   <input type="checkbox" name="selectAllFeatures" OnClick="allFeaturesSelect(this, 'selectedFeatures')" /> Select All Features
									    </td>
									</tr>  
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
														<img src="/<msp:webapp/>images/expand.gif" name="featuresExpandContract<bean:write name="featureCount"/>" border="0"></a>
													</logic:greaterThan>
														<input type="checkbox" id="<bean:write name="featureCount"/>" name="selectedFeatures" value="<bean:write name="featuresIndex" property="featureId"/>" onclick="checkChildren(this)" >
														<bean:write name="featuresIndex" property="featureName"/>
												</logic:notEmpty>
											</td>		
										</tr>
										
										<logic:notEmpty name="featuresIndex" property="childFeatures">
											<jims:sortResults beanName="featuresIndex" results="childFeatures" primaryPropSort="featureName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" levelDeep="1" hideMe="true"/>
											<logic:iterate id="childFeaturesIndex" name="featuresIndex" property="childFeatures" indexId="childIndexCount">
												<tr id="<bean:write name="featuresIndex" property="featureId"/><bean:write name="childIndexCount"/>" class="hidden" height="100%">
													<td style="padding-left:15px">
														<input type="checkbox" id="childOf<bean:write name="featureCount"/>+<bean:write name="childIndexCount"/>" name="selectedFeatures" value="<bean:write name="childFeaturesIndex" property="featureId"/>" onClick="resetParent(this)">
														<bean:write name="childFeaturesIndex" property="featureName"/>
													</td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
									</logic:iterate>
								</table>	
						<!--checkbox end-->
							</div> 
							</td> 
							<br>
					    </tr>	
						<tr>
							<td align="center" colspan="2">
							    <html:submit property="submitAction"><bean:message key="button.addFeatures"></bean:message></html:submit>
							</td>							    
						</tr> 
					</logic:notEmpty>
		            <logic:notEmpty name="roleForm" property="currentFeatures"> 
		       			<tr><td>&nbsp;</td></tr>
		       			<tr>
						   <td colspan="2" class="formDeLabel">Selected Features</td>
						</tr>
						<tr>
		       				<td colspan="2" align="center">	
			   					<div class="scrollingDiv100">
									<table width="100%" cellspacing="0" cellpadding="4">
										<jims:sortResults beanName="roleForm" results="currentFeatures" primaryPropSort="featureName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="2" hideMe="true" /> 						   
										<logic:iterate id="currentIndex" name="roleForm" property="currentFeatures" indexId="index2">
								            <tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
												<td width="1%" valign="top">
												    <a href="/<msp:webapp/>handleRoleFeatureRemove.do?featureId=<bean:write name="currentIndex" property="featureId"/>" title='Remove <bean:write name="currentIndex" property="featureName"/>'>Remove</a>
												</td>
												<td class="boldText">
												    <bean:write name="currentIndex" property="featureName"/>
												    <input type="hidden" name="feature">
												</td>
											</tr>
										</logic:iterate>
									</table>	
								</div>  
							</td>
						</tr> 	
	  			    </logic:notEmpty> 
				</table>
			<!--select SA features end-->
				<br>
<!--  BEGIN BUTTON TABLE  -->				
				<table width="100%">
					<tr>
						<td align="center">
					        <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
					          <bean:message key="button.back"></bean:message></html:button>&nbsp;		
	    	    			<html:submit property="submitAction" onclick="return validateFeatureSelect(this.form) && disableSubmit(this, this.form);"><bean:message key="button.next"/></html:submit>&nbsp;
		   	    			<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
	    	    		</td>
		    	    </tr>
	    	    </table>
<!-- END BUTTON TABLE -->	    	    
			</td>
		</tr>
	</table>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>