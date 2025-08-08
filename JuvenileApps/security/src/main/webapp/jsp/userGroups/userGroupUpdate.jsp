<!DOCTYPE HTML>
<!-- Used for activate, inactivate, create, and update summary pages and view details. -->
<!--MODIFICATIONS -->
<!-- CShimek 05/10/2005	 Create JSP -->
<!-- CShimek 03/24/2006  Defect#29851 Add Reset button (added Refresh buttons due to misunderstanding) -->
<!-- CShimek 04/19/2006  Defect#30360 Add temporary alert for user details hyperlink -->
<!-- CShimek 07/27/2006  Defect#33128 Increase User Group Name maxlength to 25 from 20 -->
<!-- CShimek 08/07/2006  Activity#34092, revised column heading to Name -->
<!-- UGopinath 08/23/2006 Activity #34122, added Middle Name to search criteria and User Name -->
<!-- CShimek 07/27/2006  Defect#30630 Revised user name hyperlink to actually go to User Profile Detail page -->
<!-- CShimek 01/11/2007  #38306 add multiple submit functionality  -->
<!-- CShimek 07/05/2007  #43456 removed casework_util.js, all needed functions are in app.js -->
<!-- CShimek 08/27/2007  added action value to user name hyperlink -- found while testing other UG changes. -->
<!-- CShimek 01/03/2008  #47978 added internal sort to selected/current list -->
<!-- CShimek 01/22/2008  #47978 removed intenal sort by adding sort to action -->
<!-- CShimek 02/06/2009  #56860 add Back to Top  -->
<!-- RYoung  10/19/2015  #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

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
<title><bean:message key="title.heading"/> - userGroupUpdate.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/userGroups/userGroupUpdate.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>

</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
  <tr>
    <td align="center" class="header"><bean:message key="title.userGroupUpdate"/></td>
  </tr>
</table>
<!-- END HEADING TABLE -->
<logic:notEqual name="userGroupForm" property="errorMessage" value="">
<table width="100%">
    <tr>
   	    <td align="center" class="errorAlert"><bean:write name="userGroupForm" property="errorMessage" /></td>
    </tr>	
</table>        
</logic:notEqual> 
<!-- BEGIN INSTRUCTION TABLE -->
<table align="center" width="98%" border="0">
   <tr>
     <td>
	  <ul>
	  <logic:equal name="userGroupForm" property="userType" value="MA">
		<logic:empty name="userGroupForm" property="users">
	        <li>Enter Search values for Agency then click Find Agency button to search for agencies.</li>
    	    <li>Click radio button next to agency name you want to use.</li>
    	 </logic:empty>   
	  </logic:equal>
        <li>Enter User search values for User then click Find Users button to search for users.</li>
        <li>Click Add to List button to add users to this user group.</li>
	    <li>Click Remove button to remove users from this user group.</li>
	  </ul>
	</td>
  </tr>
  <logic:notEqual name="userGroupForm" property="userType" value="ASA">
     <tr>
        <td class="required"><bean:message key="prompt.requiredFields"/></td>
    </tr>
  </logic:notEqual>
  <tr>
    <td class="required">At least one field is required for search.</td>
  </tr>
  <tr>
    <td class="required">+Indicates Last Name is required to use this search field.</td>
  </tr>  
</table>
<!-- END INSTRUCTION TABLE -->

<table width="100%" border="0" cellpadding="0" cellspacing="0">	
<html:form action="/handleUserGroupUpdate" target="content">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|235">					
 <%-- hidden field for refresh button processing --%>
<input type="hidden" name="pageType" value="">
	<td width="98%" align="center" valign="top">
		<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">
			<tr class="detailHead">
				<td class="detailHead">Update User Group</td>
				<td align="right"><img src=/<msp:webapp/>images/step_1_edit.gif hspace=0 vspace=0></td>
			</tr>
			<tr>
				<td colspan="2">
					<table width="100%">
					    <logic:notEqual name="userGroupForm" property="userType" value="ASA">
							<tr>
								<td nowrap class="formDeLabel"><bean:message key="prompt.diamond" /><bean:message key="prompt.userGroupName"/></td>
								<td class="formDe"><html:text property="userGroupName" size="25" maxlength="25"/></td>
							</tr>
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.diamond" /><bean:message key="prompt.userGroupDescription"/></td>
								<td class="formDe"><html:text property="userGroupDescription" size="50" maxlength="50"/></td>
							</tr>
						</logic:notEqual>
					    <logic:equal name="userGroupForm" property="userType" value="ASA">										
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.userGroupName" /></td>
								<td class="formDe">
								     <bean:write name="userGroupForm" property="userGroupName"/>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userGroupDescription" /></td>
								<td class="formDe">
								     <bean:write name="userGroupForm" property="userGroupDescription"/>
								</td>
							</tr>
						</logic:equal>	
					</table>
				</td>
			</tr>
		</table>
<!-- BEGIN AGENCY UPDATE -->
	<logic:equal name="userGroupForm" property="userType" value="MA">
	    <br>
		<table class="borderTableBlue" cellpadding="4" cellspacing="0" width="98%">
			<tr class="detailHead">
				<logic:equal name="userGroupForm" property="updateAgencyFlag" value="true">
					<td class="detailHead">Update <bean:message key="prompt.agency"/> (at least one field is required for search)</td>
				</logic:equal>
				<logic:equal name="userGroupForm" property="updateAgencyFlag" value="false">
					<td class="detailHead"><bean:message key="prompt.agencyInfo"/></td>
				</logic:equal>
				<td align="right"><img src=/<msp:webapp/>images/step_2_edit.gif hspace=0 vspace=0></td>
			</tr>
			<tr>
				<td colspan="2">
					<table width="100%" cellpadding="4">
						<tr>
							<td class="formDeLabel" width="1%" nowrap>Current <bean:message key="prompt.agencyName"/></td>
							<td class="formDe">
								<bean:write name="userGroupForm" property="agencyName"/>
							</td>
						</tr>
						<logic:equal name="userGroupForm" property="updateAgencyFlag" value="true">
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
							  	 <html:submit property="submitAction" onclick="return validateAgencySearchFields(this.form) && disableSubmit(this, this.form);">
				  	                <bean:message key="button.findAgencies"></bean:message>
	 							 </html:submit>	
								 <html:submit property="submitAction" onclick="return setPageType('RefreshAgency') && disableSubmit(this, this.form);">
				  	                <bean:message key="button.refresh"></bean:message>
	 							 </html:submit>		 							 
							</td>
						</tr>
						</logic:equal>
						<tr>
						    <td colspan="2">
       		            <!-- BEGIN AGENCY ERROR TABLE -->
       		            <logic:notEqual name="userGroupForm" property="agencySearchErrorMessage" value="">
                		        <table width="100%">
       	                		    <tr>		  
										<td align="center" class="errorAlert"><bean:write name="userGroupForm" property="agencySearchErrorMessage"/> </td>		  
								    </tr>   	  
							    </table>
						</logic:notEqual>							    
                   		<!-- END AGENCY ERROR TABLE -->				
						</tr>
						<logic:notEmpty name="userGroupForm" property="availableAgencies">
						<tr>
							<td colspan="2" align="center">
						   		<bean:write name="userGroupForm" property="agencySearchResultSize"/> agency matches found.
							</td> 
						</tr>              
						<tr>
							<td colspan="2">
								<table border="0" width="100%" cellspacing=1 cellpadding="0">
									<tr>
										<td>&nbsp;</td>
										<td class="boldText" valign="bottom"><bean:message key="prompt.agencyName"/>s</td>
									</tr>
								</table>
							</td>
						</tr>  
						<tr>
							<td colspan="2"> 
							<div class="scrollingDiv200">
					<!-- agency radio button selection start-->
								<table border="0" width="100%" cellspacing=1 cellpadding=2>
				    	    	<logic:iterate id="agenciesIndex" name="userGroupForm" property="availableAgencies" indexId="index1"> 
									<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
										<td class="boldText">
										<html:radio property="selectedAgencies" idName="agenciesIndex" value="agencyId" />
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
	</logic:equal>
<!-- END AGENCY UPDATE -->
	<br>	
<!-- BEGIN USER UPDATE -->
		<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
			<tr class="detailHead">
				<td class="detailHead">Update Users  (at least one field is required for search)</td>
				<td align="right">
					<logic:equal name="userGroupForm" property="userType" value="MA">	
						<img src=/<msp:webapp/>images/step_3_edit.gif hspace=0 vspace=0 border="0">
					</logic:equal>
					<logic:notEqual name="userGroupForm" property="userType" value="MA">	
						<img src=/<msp:webapp/>images/step_2_edit.gif hspace=0 vspace=0 border="0">
					</logic:notEqual>
				</td> 
			</tr> 
<%-- BEGIN USER SEARCH TABLE --%>				
			<tr>
				<td colspan="2">
					<table width="100%" cellpadding="4">
		                <tr>
           			      <td class="formDeLabel"><bean:message key="prompt.name" /></td>
		                  <td class="formDe">
		                  	 <table>
		                  	 	<tr>
				                    <td class="formDeLabel" width="1%">Last</td>				                    
			                     </tr>
           			        	 <tr>
			                        <td class="formDe"><html:text property="lastName" size="30" maxlength="30"/></td>
				                  <tr>
				                    <td class="formDeLabel" width="1%">+First</td>
				                      <td class="formDeLabel" width="1%">+Middle</td>
			                     </tr>
			                    <tr>
			                    	<td class="formDe"><html:text property="firstName" size="30" maxlength="30"/></td>
			                    	<td class="formDe"><html:text property="middleName" size="30" maxlength="30"/></td>
			                      </tr> 
               			    </table>
              			      </td>
		                </tr>													
						<tr>
							<td class="formDeLabel"><bean:message key="prompt.userId" /></td>
							<td class="formDe"><html:text property="userId" size="6" maxlength="5"/></td>
						</tr>
						<logic:equal name="userGroupForm" property="userType" value="MA">	
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.agencyName" /></td>
								<td class="formDe"><html:text property="userAgencyName" size="62" maxlength="60"/></td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.departmentName" /></td>
								<td class="formDe"><html:text property="department" size="52" maxlength="50"/></td>
							</tr> 
							<tr>
								<td class="formDeLabel"></td>
								<td class="formDe">
									 <html:submit property="submitAction" onclick="return validateMAUserSearchFields(this.form) && disableSubmit(this, this.form);">
	  									<bean:message key="button.findUsers"></bean:message>
	 								</html:submit>	
								 <html:submit property="submitAction" onclick="return setPageType('RefreshMAUsers') && disableSubmit(this, this.form);">
				  	                <bean:message key="button.refresh"></bean:message>
	 							 </html:submit>		 							 
								</td>
							</tr>
						</logic:equal>
						<logic:equal name="userGroupForm" property="userType" value="SA">
							<tr height="100%">
								<td class="formDeLabel"><bean:message key="prompt.departmentName"/></td>
								<td class="formDe">
				            	       <html:select property="departmentId">
          								      <html:option value=""><bean:message key="select.generic" /></html:option>
						            	  <html:optionsCollection property="departments" value="departmentId" label="departmentName" /> 
	  	        			       	   </html:select> 
								</td>
							</tr>
							<tr>
								<td class="formDeLabel"></td>
								<td class="formDe">
									 <html:submit property="submitAction" onclick="return validateSAUserSearchFields(this.form) && disableSubmit(this, this.form);">
	  									<bean:message key="button.findUsers"></bean:message>
	 								</html:submit>	
								 <html:submit property="submitAction" onclick="return setPageType('RefreshUsers') && disableSubmit(this, this.form);">
				  	                <bean:message key="button.refresh"></bean:message>
	 							 </html:submit>		 							 
								</td>
							</tr>
						</logic:equal>
					</table>	
				</td>
			</tr>	
<%-- END USER SEARCH TABLE --%>	
<%-- BEGIN USER SEARCH RESULTS TABLE --%>
			<tr>
			    <td colspan="2">
            <!-- BEGIN USER ERROR TABLE -->
   		            <logic:notEqual name="userGroupForm" property="userSearchErrorMessage" value="">
              		        <table width="100%">
   	                		    <tr>		  
									<td align="center" class="errorAlert"><bean:write name="userGroupForm" property="userSearchErrorMessage"/> </td>		  
							    </tr>   	  
						    </table>
					</logic:notEqual>							    
      		<!-- END USER ERROR TABLE -->				
				 </td>   
			</tr>
			<logic:notEmpty name="userGroupForm" property="availableUsers">
			<tr>
				<td colspan="2" align="center">
				   <bean:write name="userGroupForm" property="userSearchResultSize"/> search results found.
				</td>
			</tr>              
			<tr>
				<td colspan="2"> 
					<div class="scrollingDiv200">
					 <!-- BEGIN USER CHECKBOX SELECTION -->	
						<table border="0" width="100%" cellspacing="1" cellpadding="2">
						<tr bgcolor="#CCCCCC" height="100%">
							<td class="boldText">
								<input type="checkbox" name="selectAllUsers" onclick="allUsersSelect(this, 'selectedUsers')" />
							    <bean:message key="prompt.name"/>
							</td>
							<td class="boldText"><bean:message key="prompt.userId" /></td>
							<td class="boldText"><bean:message key="prompt.agency" />/<bean:message key="prompt.department" /></td>
						</tr>                
					        <logic:iterate id="usersIndex" name="userGroupForm" property="availableUsers" indexId="index2"> 
								<tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
									<td class="boldText" nowrap>
                              			   <input type="checkbox" name="selectedUsers" value=<bean:write name='usersIndex' property='logonId'/> OnClick="uncheckSelectAll(this, 'selectedUsers')"> 
                              			   <a href="javascript: openWindow('/<msp:webapp/>displaySecurityInquiriesUserProfileSearchResults.do?searchJIMSLogonId=<bean:write name="usersIndex" property="logonId"/>&action=view')">											
      				                        <bean:write name='usersIndex' property='lastName'/>,&nbsp;<bean:write name='usersIndex' property='firstName'/> <bean:write name="usersIndex" property="middleName" /></a>
									</td>
									<td class="boldText" nowrap>
      				                        <bean:write name='usersIndex' property='logonId'/>
									</td>
									<td class="boldText">
      				                        <bean:write name='usersIndex' property='agencyName'/>
      				                        <logic:present name="usersIndex" property="departmentName" >
      				                        	/ <bean:write name='usersIndex' property='departmentName'/>
      				                        </logic:present>
									</td>
								</tr>
						    </logic:iterate>	
						</table>
					 <!-- END USER CHECKBOX SELECTION -->	
					</div>
				</td>
			</tr>
<%-- END USER SEARCH RESULTS TABLE --%>				
<%-- ADD USERS TO LIST BUTTON --%>				
			<tr>
	           <td align="center" colspan="2">
	              <html:submit property="submitAction" onclick="return validateAdd(this.form, 'selectedUsers', 'Select a User to add to the list');"><bean:message key="button.addSelectedUsers"></bean:message></html:submit>
	           </td>							    
            </tr> 
            </logic:notEmpty> 
<%-- BEGIN USER SELECTION TABLE --%>
			<logic:notEmpty name="userGroupForm" property="currentUsers"> 
       			<tr><td>&nbsp;</td></tr>
       			<tr>
				   <td colspan="2" class="boldText" valign="bottom">Selected/Current Users List</td>
				</tr>
				<tr>
       				<td colspan="2" align="center">	
   					<div class="scrollingDiv100">
					<table width="100%" cellspacing="1" cellpadding="4">
			            <tr bgcolor="#cccccc" height="100%">
							<td nowrap>&nbsp;</td>
							<td class="boldText"><bean:message key="prompt.name" /></td>
							<td class="boldText" nowrap><bean:message key="prompt.userId" /></td>
							<td class="boldText"><bean:message key="prompt.agency" />/<bean:message key="prompt.department" /></td>
						</tr>
						<logic:iterate id="currentIndex" name="userGroupForm" property="currentUsers" indexId="index3">
			            <tr class="<%out.print((index3.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
							<td nowrap>
							    <a href="/<msp:webapp/>handleUserGroupUserRemove.do?logonId=<bean:write name="currentIndex" property="logonId"/>" title='Remove <bean:write name="currentIndex" property="lastName"/>,&nbsp;<bean:write name="currentIndex" property="firstName"/> <bean:write name="currentIndex" property="middleName" />'>Remove</a> 
							</td>
							<td class="boldText" nowrap>
							   <bean:write name="currentIndex" property="lastName"/>, <bean:write name="currentIndex" property="firstName"/> <bean:write name="currentIndex" property="middleName"/>
							    <input type="hidden" name="user">
							</td>
							<td class="boldText" nowrap>
							    <bean:write name="currentIndex" property="logonId"/>
							</td>
							<td class="boldText">
       				            <bean:write name='currentIndex' property='agencyName'/>
       				           <logic:present name='currentIndex' property='departmentName'>
       				               	/ <bean:write name='currentIndex' property='departmentName'/>
       				            </logic:present> 
							</td>
						</tr>
						</logic:iterate>
					</table>	
					</div>  
					</td>
				</tr> 	
  			    </logic:notEmpty> 
<%-- END USER SELECTION TABLE --%>						
			</table>	
<!-- END USER UPDATE -->
     <br>	
<!-- BEGIN BUTTONS -->
	<tr> 
		<td align="center">
			<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				<bean:message key="button.back"></bean:message></html:button>&nbsp;		
			<html:submit onclick="return validateRequiredFields(this.form)" property="submitAction"><bean:message key="button.next"/></html:submit>&nbsp;
			<%-- reset required js becuase agency radio button not clearing --%>			
			<html:reset property='reset' onclick="return resetFields(this.form);" />
			<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>			
		</td> 
	</tr> 
<!--END BUTTONS -->
</html:form>	
</table>
<br>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>