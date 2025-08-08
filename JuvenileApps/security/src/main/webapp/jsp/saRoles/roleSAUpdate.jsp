<!DOCTYPE HTML>
<!-- Used to update Security Administrator Role. -->
<!--MODIFICATIONS -->
<!-- CShimek 04/01/2005	 Create JSP -->
<%-- CShimek 02/13/2006  Corrected prompt values for required field indicator --%> 
<!-- CShimek 03/20/2006  #29710 Revise entry order of Role and Agency -->
<!-- CShimek 03/30/2006  Per ER#26357, revised reset button to refresh button -->
<!-- CShimek 11/07/2006  Defect#36824 revise subsystem search field from input text to drop down list -->
<!-- CShimek 12/14/2006  Added hidden field for Role Name when role is All Security Admin, prevents js error -->
<!-- CShimek 01/12/2007  #38306 add multiple submit functionality  -->
<!-- CShimek 02/05/2009  #56860 add Back to Top  -->

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
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />

<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading"/> - roleSAUpdate.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/saRoles/roleSAUpdate.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>

<script type="text/javascript">
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
<!-- BEGIN HEADING TABLE -->
<table width="100%">
  <tr>
    <td align="center" class="header"><bean:message key="title.saRoleUpdate"/></td>
  </tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
<table align="center" width="98%" border="0">
   <tr>
     <td>
	  <ul>
        <li>Make changes as necessary then select Next.</li>
        <li>Select Find to search and features. Feature Name and SubSystem value are optional.</li>        
      </ul>
	</td>
  </tr>
  <tr>
  	<td class="required"><bean:message key="prompt.msa.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/></td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<!-- BEGIN ERROR TABLE -->
<table width="100%">
    <tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
    </tr>   	  
</table>
<!-- END ERROR TABLE -->		

<table width="100%" border="0" cellpadding="0" cellspacing="0">
<html:form action="/displaySARoleSummary" target="content">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|232">
	<tr>   
		<td width="98%" align="center" valign="top">
			<!-- BEGIN AGENCY INFORMATION TABLE -->
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead">Update Agency for SA Role</td>
					<td align="right"><img src=/<msp:webapp/>images/step_1_edit.gif hspace="0" vspace="0"></td>
				</tr>
				<tr> 
					<td colspan="2">
						<table width="100%" border="0" cellpadding="2" class="borderTable">
							<tr class="formDeLabel">
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.msa.diamond" /><bean:message key="prompt.currentAgency"/></td>
								<td class="formDe">
					 			    <logic:equal name="roleSAForm" property="agencyName" value="">Agency name not available.</logic:equal>
			  					    <bean:write name="roleSAForm" property="agencyName"/>
								</td>	  															
							</tr>  
							<tr>  
								<td colspan="2" class="formDeLabel">Available Agencies<input type="hidden" name="currentAgency" value=<bean:write name="roleSAForm" property="agencyName" /> ></td>
							</tr>
							    <logic:iterate name="roleSAForm" property="jmcAgencies" id="agenciesIndex" indexId="index"> 
								<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" id="Agency" >
									<td class="boldText" colspan="2">
									<html:radio property="selectedAgencies" idName="agenciesIndex" value="agencyId" />
				                        <bean:write name='agenciesIndex' property='agencyName'/>
									</td>
								</tr>
							    </logic:iterate>							    
							    <logic:empty  name="roleSAForm" property="jmcAgencies">
							    	<tr>
							    		<td colspan="2" class="formDe">Agencies not available.</td>
							    	</tr>
							    </logic:empty>
						</table>
					</td>
				</tr>
			</table>
			<!-- END AGENCY TABLE -->
			<br>
			<!-- BEGIN ROLE INFORMATION TABLE -->		
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead">Update <bean:message key="prompt.saRoleInfo"/></td>
					<td align="right"><img src=/<msp:webapp/>images/step_2_edit.gif hspace="0" vspace="0"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table width="100%" cellpadding="2">
							<tr>
								<td nowrap class="formDeLabel"><bean:message key="prompt.msa.diamond" /><bean:message key="prompt.roleName"/></td>
								<td class="formDe">
								    <logic:equal name="roleSAForm" property="allSecAdminNameInd" value="">								
				  					   <html:text property="roleName" size="30" maxlength="20"/>
				  					</logic:equal> 
								    <logic:equal name="roleSAForm" property="allSecAdminNameInd" value="Y">								
				  					   <bean:write name="roleSAForm" property="roleName"/>
				  					   <input type="hidden" name="roleName" value="<bean:write name="roleSAForm" property="roleName"/>" >
				  					</logic:equal>   
								</td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.msa.diamond" /><bean:message key="prompt.roleDescription"/></td>
								<td class="formDe"><html:text property="roleDescription" size="52" maxlength="50"/></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<!-- END ROLE INFORMATION TABLE -->
			<br>
			<!-- BEGIN FEATURE SEARCH TABLE -->
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead">Update SA Features for Delegation and Use</td>
					<td align="right"><img src=/<msp:webapp/>images/step_3_edit.gif hspace="0" vspace="0"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<!--sa features search criteria start-->
						<table width="100%" cellpadding="4">
							<tr>
								<td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.featureName"/></td>
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
  	                                <html:submit property="submitAction" onclick="return checkSearchInputs(this.form) && disableSubmit(this, this.form);"><bean:message key="button.find"></bean:message></html:submit>
  	                                <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.refresh"></bean:message></html:submit>	            				     									  	                                
								</td>
							</tr>
						</table>
						<!--sa features search criteria end-->
					</td>
				</tr>
				<!-- END FEATURE SEARCH TABLE -->				
								
				<logic:notEmpty name="roleSAForm" property="availableFeatures">  
					<bean:size id="searchResultSize" name="roleSAForm" property="availableFeatures"/>
					<tr>
						<td colspan="2" align="center">
						   <bean:write name="searchResultSize"/> search results found.
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">	
						<div class="scrollingDiv200" >
				<!-- BEGIN FEATURE SELECT TABLE -->
						<table width="100%" cellspacing="0" cellpadding="4">
							<tr class="formDeLabel">
								<td class="boldText">
								   <input type="checkbox" name="selectAllFeatures" OnClick="allFeaturesSelect(this, 'selectedFeatures')"> Select All Features
								</td>
							</tr>	
								<logic:iterate id="featuresIndex" name="roleSAForm" property="availableFeatures" indexId="featureCount">
									<tr class="alternateRow">
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
									<jims:sortResults beanName="featuresIndex" results="childFeatures" primaryPropSort="featureName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="F1" levelDeep="1" hideMe="true"/>
									<logic:iterate id="childFeaturesIndex" name="featuresIndex" property="childFeatures" indexId="childIndexCount">
										<tr id="<bean:write name="featuresIndex" property="featureId"/><bean:write name="childIndexCount"/>" class="hidden">
											<td style="padding-left:15px">
												<input type="checkbox" id="childOf<bean:write name="featureCount"/>+<bean:write name="childIndexCount"/>" name="selectedFeatures" value="<bean:write name="childFeaturesIndex" property="featureId"/>" >
												<bean:write name="childFeaturesIndex" property="featureName"/>
											</td>
										</tr>
									</logic:iterate>
									</logic:notEmpty>
								</logic:iterate>
							</table>	
					<!-- END FEATURE SELECT TABLE -->
						</div> 
						</td> 
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>	
					<tr>
						<td align="center" colspan="2">
							<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.addFeatures"></bean:message></html:submit>
						</td>							    
					</tr>
				</logic:notEmpty>
			
				<!-- BEGIN SELECTED FEATURE TABLE -->
	            <logic:notEmpty name="roleSAForm" property="currentFeatures"> 
       			<tr><td>&nbsp;</td></tr>
       			<tr>
				   <td colspan="2" class="formDeLabel"><bean:message key="prompt.msa.diamond" />Current/Selected Features List
				        <jims:sortResults beanName="roleSAForm" results="currentFeatures" 
    		                                    primaryPropSort="featureName" primarySortType="STRING" defaultSort="true" 
            		                            defaultSortOrder="ASC" sortId="1" hideMe="true" /></td>
				</tr>
				<tr>
       				<td colspan="2" align="center">	
   					<div class="scrollingDiv100">
					<table width="100%" cellspacing="0" cellpadding="4">
						<logic:iterate id="currentIndex" name="roleSAForm" property="currentFeatures" indexId="index2">
			            <tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">							
							<td align="center" width="1%"><input type="checkbox" id="index2"
								name="selectedFeatures"
								value="<bean:write name='currentIndex' property='featureId'/>">
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
				<tr>
					<td align="center" colspan="2"><html:submit
						property="submitAction"
						onclick="return validateCheckBoxSelect(this.form) &amp;&amp; disableSubmit(this, this.form)">
						<bean:message key="button.removeSelected" />
					</html:submit></td>
				</tr> 	
  			    </logic:notEmpty>
  			 </table>
  			 <!-- END FEATURE SELECTED TABLE -->
			<!-- END FEATURES TABLE -->
			<br>
			<table width="100%" cellspacing="0" cellpadding="2" >
				<tr>
					<td colspan="2" align="center">
			        	<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				        	  <bean:message key="button.back"></bean:message></html:button>&nbsp;		
			    	    <html:submit property="submitAction" onclick="return validateFields(this.form) && disableSubmit(this, this.form);"><bean:message key="button.next"/></html:submit>&nbsp;		          
    				    <html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
    	    		</td>
    	    	</tr>
    	    </table>
		</td>
	</tr>	
</html:form>
</table>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>