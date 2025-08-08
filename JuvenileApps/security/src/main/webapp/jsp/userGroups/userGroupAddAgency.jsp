<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Used to create user groups - page 2  -->
<!--MODIFICATIONS -->
<!-- CShimek 06/07/2005	 Create JSP -->
<!-- CShimek 03/30/2006  Per ER#26357, revised reset button to refresh button -->
<!-- CShimek 06/09/2006  Defect#32128 - added multiple submit button check -->
<!-- CShimek 02/06/2009  #56860 add Back to Top  -->

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
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />  
<title><bean:message key="title.heading"/> - userGroupCreate2.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/userGroups/userGroupAddAgency.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>

</head>

<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
  <tr>
    <td align="center" class="header"><bean:message key="title.userGroupCreate"/> - Select Agency</td>
  </tr>
  <tr>
    <td> <ul>
        <li>Enter Agency Name and/or select Agency Type then click Find Agenices to search for Agencies.</li>
        <li>Select Agency to associate to User Group.</li>
        <li>Click Next to continue. </li>
      </ul></td>
  </tr>
</table>
<!-- END INSTRUCTION TABLE -->

<table width="100%" border="0" cellpadding="0" cellspacing="0">
<html:form action="/handleUserGroupMAAddAgency" target="content">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|73">	
 	<tr>
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">  
					<td class="detailHead"><bean:message key="prompt.userGroupInfo"/></td>
					<td align="right"><img src=/<msp:webapp/>images/step_1.gif hspace=0 vspace=0></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table width="100%" cellpadding="4">
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.userGroupName"/></td>
								<td class="formDe"><bean:write name="userGroupForm" property="userGroupName"/></td>
							</tr>
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userGroupDescription"/></td>
								<td class="formDe"><bean:write name="userGroupForm" property="userGroupDescription"/></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>		
<!--select agency note start-->
	<tr><td>&nbsp;</td></tr>     
	 <tr>
	 	<td width="98%" align="center" valign="bottom">
	 		<table width="98%">
	 			<tr>
				    <td class="required">Note: Agency is optional.  If you specify an agency for a user group, then you can only add users from that agency to that group.</td>
				</tr>
			</table>
		</td>		    
	 </tr>
<!--select agency note end--> 

	<tr>
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">
			
				<tr class="detailHead">
					<td class="detailHead">Select <bean:message key="prompt.agency"/> (at least one field is required for search)</td>
					<td align="right"><img src=/<msp:webapp/>images/step_2.gif hspace=0 vspace=0></td>
				</tr>
				<tr>
					<td colspan="2">
						<table width="100%" cellpadding="4">
</html:form>
<html:form action="/handleUserGroupMAFindAgency" target="content">		
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.agencyName"/></td>
								<td class="formDe"><html:text property="searchAgencyName" size="62" maxlength="60"/></td>
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
								<td class="formDeLabel"></td>
								<td class="formDe">
									 <html:submit property="submitAction" onclick="return validateSearchFields(this.form);">
					  					<bean:message key="button.findAgencies"></bean:message>
		 							</html:submit>	
									 <html:submit property="submitAction">
					  					<bean:message key="button.refresh" />
		 							</html:submit>			 							
								</td>
							</tr>
</html:form>
<html:form action="/handleUserGroupMAAddAgency" target="content">		
							<tr>
							    <td colspan="2">
        		            <!-- BEGIN ERROR TABLE -->
    	            		        <table width="100%">
        	                		    <tr>		  
											<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
									    </tr>   	  
								    </table>
                    		<!-- END ERROR TABLE -->				
							</tr>
							<logic:notEmpty name="userGroupForm" property="availableAgencies">
							<tr>
								<td colspan="2" align="center">
							   		<bean:write name="userGroupForm" property="agencySearchResultSize"/> agency matches found.
								</td> 
							</tr>              
							<tr bgcolor="#CCCCCC">
								<td colspan="2" class="boldText"><bean:message key="prompt.agencyName"/>
								    <jims:sortResults beanName="userGroupForm" results="availableAgencies" 
                                      primaryPropSort="agencyName" primarySortType="STRING" defaultSort="true" 
                                      defaultSortOrder="ASC" sortId="1" />
                                </td>
							</tr>  
							<tr>
								<td colspan="2"> 
								<div class="scrollingDiv200">
						<!-- agency radio button selection start-->
									<table border="0" width="100%" cellspacing=1 cellpadding=2>
					    	    	<logic:iterate id="agenciesIndex" name="userGroupForm" property="availableAgencies" indexId="index"> 
										<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
											<td class="boldText">
	                                    	  	<input type="radio" name="agencyId" value=<bean:write name='agenciesIndex' property='agencyId'/>> 
    	                                    		<bean:write name='agenciesIndex' property='agencyName'/> 
											</td>
										</tr>
									</logic:iterate>		
									</table>
						 <!-- agency radio button selection end -->	
								</div>
								</td>
							</tr>
							</logic:notEmpty>
						</table>
					</td>
				</tr>     
			</table>			
		<br>
		<table width="100%">
			<tr>
				<td align="center">
    			    <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
					   <bean:message key="button.back"></bean:message>
					</html:button>
				 	<html:submit property="submitAction"><bean:message key="button.next"/></html:submit>
			 		<html:submit property="submitAction" ><bean:message key="button.cancel"/></html:submit>
				</td>
			</tr> 
		</table>
</html:form>			
</table>			
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
