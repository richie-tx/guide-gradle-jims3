<!DOCTYPE HTML>
<!-- Used to search create Roles - page 2  -->
<!--MODIFICATIONS -->
<!-- CShimke 04/01/2005	 Create JSP -->
<%-- CShimek 02/13/2006  Corrected prompt values for required field indicator --%> 
<%-- CShimek 03/31/2006  Per ER#26357, revised reset button to refresh button --%>
<%-- CShimek 04/18/2006  Defect#30558, added checkEnterKey() to body tag and nogo() function to agency name hyperlink --%>
<%-- CShimek 11/07/2006  Defect#36825 added Agency Code search field --%>
<%-- CShimek 04/20/2007  #41292 added sort tag to selected agencies --%>
<%-- CShimek 07/05/2007  #43456 removed caseword_util.js, all needed functions are now in app.js --%>
<%-- CShimek 02/05/2009  #56860 add Back to Top  --%>
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
<title><bean:message key="title.heading"/> - roleCreate2.jsp</title> 
<!-- JAVASCRIPT FILES -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/roles/roleCreate2.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>

</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)"">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
  <tr>
    <td align="center" class="header">
        <logic:equal name="roleForm" property="action" value="create" >
		    <bean:message key="title.createRole"/> - Select Agency
		</logic:equal>
        <logic:equal name="roleForm" property="action" value="copy" >
		   <bean:message key="title.copyRole"/> - Select Agency
		</logic:equal>			
	</td>
  </tr>
</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERROR TABLE -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE -->				
<!-- BEGIN INSTRUCTION TABLE -->
<table align="center" width="98%" border="0">
   <tr>
     <td>
	  <ul>
        <li>Enter and/or select one or more Agency search values then select Find for selection list.</li>
        <li>Select agency(s) from selection list then select Next to continue.</li>
      </ul>
	</td>
  </tr>
  <tr>
  	<td class="required"><bean:message key="prompt.msa.diamond"/> One or more values required</td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->
<html:form action="/handleRoleAgencySearch" target="content" focus="agencyName">
	<logic:equal name="roleForm" property="action" value="copy" >    	
 		 <input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|271">
    </logic:equal>   
	<logic:equal name="roleForm" property="action" value="create" >
   		 <input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|61"> 
	</logic:equal> 
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="4" cellspacing=0 border="0" width="98%">
				<tr class="detailHead">  
					<td class="detailHead"><bean:message key="prompt.roleInfo"/></td>
					<td align="right"><img src=/<msp:webapp/>images/step_1.gif hspace=0 vspace=0></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table width="100%" cellpadding="4">
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.roleName"/></td>
								<td class="formDe"><bean:write name="roleForm" property="roleName"/></td>
							</tr>
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.roleDescription"/></td>
								<td class="formDe"><bean:write name="roleForm" property="roleDescription"/></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		<br>
			<!-- BEGIN AGENCY SELECT TABLE -->
			<table class="borderTableBlue"  cellpadding="4" cellspacing="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.msa.diamond"/> Select <bean:message key="prompt.agencyforRole"/></td>
					<td align="right"><img src=/<msp:webapp/>images/step_2.gif hspace=0 vspace=0></td>
				</tr>
				<tr>
					<td colspan="2">
						<table width="100%" cellpadding="4">
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
								<td class="formDeLabel"></td>
								<td class="formDe">
									 <html:submit property="submitAction" onclick="return validateSearchFields(this.form) && disableSubmit(this, this.form);changeFormActionURL('roleForm', '/<msp:webapp>handleRoleAgencySearch.do', true);">
					  					<bean:message key="button.find"></bean:message>
		 							</html:submit>
									 <html:submit property="submitAction">
					  					<bean:message key="button.refresh"></bean:message>
		 							</html:submit>
 								</td>
							</tr>

						<logic:notEmpty name="roleForm" property="availableAgencies">
						<tr>
							<td colspan="2" align="center">
							   <bean:write name="roleForm" property="agencySearchResultSize"/> agency matches found.
						</td>
				</tr>              
							<tr bgcolor="#cccccc">
								<td colspan="2" class="boldText"><bean:message key="prompt.agencyName"/></td>
							</tr>                
							<tr>
								<td colspan="2"> 
					<!-- VARIABLES NEEDED FOR DISPLAY -->
								<div class="scrollingDiv200">
							<!-- agency check box selection start-->
									<table border="0" width="100%"  cellspacing="1" cellpadding="2">
										<jims:sortResults beanName="roleForm" results="availableAgencies" primaryPropSort="agencyName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="2" hideMe="true" /> 						   						   													
								        <logic:iterate id="agenciesIndex" name="roleForm" property="availableAgencies" indexId="index"> 
											<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
												<td class="boldText">
                                    			   <input type="checkbox" name="selectedAgencies" value=<bean:write name='agenciesIndex' property='agencyId'/>> 
                                    			       <a href="javascript: openWindow('/<msp:webapp/>displaySecurityInquiriesAgencyDetails.do?agencyId=<bean:write name="agenciesIndex" property="agencyId"/>&action=view')">
				                                        <bean:write name='agenciesIndex' property='agencyName'/> </a>
												</td>
											</tr>
									    </logic:iterate>		
									</table>
						 <!-- agency check box selection end -->	
								</div>
								</td>
							</tr>
							<tr>
					           <td align="center" colspan="2">
					            <logic:equal name="roleForm" property="action" value="create" >
                				   	<input type="submit" name="submitAction" onclick="changeFormActionURL('/<msp:webapp/>handleRoleAgencySelection.do?action=create&submitAction=<bean:message key="button.addSelected"/>', true);" value="<bean:message key="button.addSelected"/>">
                				</logic:equal>   	
					            <logic:equal name="roleForm" property="action" value="copy" >
                				   	<input type="submit" name="submitAction" onclick="changeFormActionURL('/<msp:webapp/>handleRoleAgencySelection.do?action=copy&submitAction=<bean:message key="button.addSelected"/>', true);" value="<bean:message key="button.addSelected"/>">
                				</logic:equal>   	

					           </td>							    
				            </tr> 
						</logic:notEmpty>
				
				<logic:notEmpty name="roleForm" property="currentAgencies"> 
       			<tr><td>&nbsp;</td></tr>
       			<tr>
				   <td colspan="2" class="formDeLabel">Selected Agencies</td>
				</tr>
				<tr>
       				<td colspan="2" align="center">	
   					<div class="scrollingDiv100">
					<table width="100%"  cellspacing="0" cellpadding="4">
						<jims:sortResults beanName="roleForm" results="currentAgencies" primaryPropSort="agencyName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="2" hideMe="true" /> 						   						   				
						<logic:iterate id="currentIndex" name="roleForm" property="currentAgencies" indexId="index2">
			            <tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
							<td width="1%" valign="top">
							    <a href="/<msp:webapp/>handleRoleAgencyRemove.do?agencyId=<bean:write name="currentIndex" property="agencyId"/>" title='Remove <bean:write name="currentIndex" property="agencyName"/>'>Remove</a>
							</td>
							<td class="boldText">
							    <bean:write name="currentIndex" property="agencyName"/>
							    <input type="hidden" name="agencySelected">
							</td>
						</tr>
						</logic:iterate>
					</table>	
					</div>  
					</td>
				</tr> 	
  			    </logic:notEmpty>
					</table>
					</td>
				</tr>		
			</table>
			<!--assign SA end-->
		</td>
	</tr>     
</table>			
	<br>    
	<table width="100%" >
		<tr>
			<td align="center">
	    	    <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				   <bean:message key="button.back"></bean:message>
				</html:button>
	            <logic:equal name="roleForm" property="action" value="create" >
				   <input type="button" name="submitAction" onclick="return checkSelectedList(this.form) && changeFormActionURL('roleForm', '/<msp:webapp/>handleRoleAgencySelection.do?action=create&submitAction=<bean:message key="button.next"/>', true);" value="<bean:message key="button.next"/>">
				</logic:equal>
        	    <logic:equal name="roleForm" property="action" value="copy" >
				   <input type="button" name="submitAction" onclick="return checkSelectedList(this.form) && changeFormActionURL('roleForm', '/<msp:webapp/>handleRoleAgencySelection.do?action=copy&submitAction=<bean:message key="button.next"/>', true);" value="<bean:message key="button.next"/>">
				</logic:equal>	
				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>				
			</td>
		</tr>
	</table>			
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>