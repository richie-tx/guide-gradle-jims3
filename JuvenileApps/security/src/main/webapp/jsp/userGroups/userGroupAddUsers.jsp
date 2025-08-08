<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Used for add users to group. -->
<!--MODIFICATIONS -->
<!-- 05/10/2005  CShimek  Create JSP -->
<!-- 04/20/2006  CShimek  Revised message for user detail alert to mention future feature -->
<%-- 08/07/2006  CShimek  Activity#34092, revised column heading to Name --%>
<%-- 01/10/2007  CShimek  #38306 add multiple submit functionality  --%>
<!-- 02/06/2009  CShimek   #56860 add Back to Top  -->

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
<title><bean:message key="title.heading"/> - userGroupAddUsers.jsp</title>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/userGroups/userGroupAddUsers.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>

</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<logic:equal name="userGroupForm" property="action" value="create">
<table align="center" width="98%" border="0" >
  <tr>
   	<td align="center" class="header">
          <bean:message key="title.userGroupCreate"/> - Add Users
   	</td>
  </tr>
  <tr>
    <logic:equal name="userGroupForm" property="errorMessage" value="">  	  	
       	<td align="center" class=confirm>User Group successfully created</td>
   	</logic:equal>   
    <logic:notEqual name="userGroupForm" property="errorMessage" value="">
   	    <td align="center" class="errorAlert"><bean:write name="userGroupForm" property="errorMessage" /></td>
   	</logic:notEqual>      	    
  </tr>
</table>
</logic:equal>
<logic:equal name="userGroupForm" property="action" value="update">
<table align="center" width="98%" border="0">
  <tr>
   	<td align="center" class="header">
          <bean:message key="title.userGroupUpdate"/> - Add/Remove Users
   	</td>
  </tr>
  <tr>
    <logic:notEqual name="userGroupForm" property="errorMessage" value="">
   	    <td align="center" class="errorAlert"><bean:write name="userGroupForm" property="errorMessage" /></td>
   	</logic:notEqual>      	    
  </tr>
</table>
</logic:equal>

<table align="center" width="98%" border="0">
   <tr>
     <td>
     <ul>
        <li>Select Find button to search for Users.</li>
        <li>Select users and select Add to List button to add users to this User Group.</li>
        <li>Select Remove button to remove users from this User Group.</li>
        <li>Select Next button to view Summary. </li>
      </ul>
	</td>
  </tr>
  <tr>
    <td class="required">At least one field is required for search.</td>
  </tr>
  <tr>
    <td class="required">+Indicates Last Name is required to use this search field.</td>
  </tr>
</table>	
<%-- END TABLES FOR HEADING AND INSTRUCTIONS --%>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
<html:form action="/handleUserGroupAddUsers" target="content" focus="lastName">	
<logic:equal name="userGroupForm" property="action" value="create">
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|70">		
</logic:equal>
<logic:equal name="userGroupForm" property="action" value="update">
	<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|##">		
</logic:equal>
		<tr>
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.userGroupInfo" /></td>
					<td align="right"><img src=/<msp:webapp/>images/step_1.gif hspace=0 vspace=0 border="0"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table border="0" cellspacing="1" cellpadding="4" width="100%">
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.userGroupName" /></td>
								<td class="formDe">
								     <bean:write name="userGroupForm" property="userGroupName"/>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap><bean:message key="prompt.userGroupDescription" /></td>
								<td class="formDe">
								     <bean:write name="userGroupForm" property="userGroupDescription"/>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.status" /></td>
								<td class="formDe">
								     <bean:write name="userGroupForm" property="groupStatus"/>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.agencyName" /></td>
								<td class="formDe">
								     <bean:write name="userGroupForm" property="agencyName"/>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel"><bean:message key="prompt.createdBy" /></td>
								<td class="formDe">
								     <bean:write name="userGroupForm" property="creatorName"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<br>
			<!-- BEGIN ADD USER TABLE -->
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead">Search for Users</td>
					<td align="right">
						<img src=/<msp:webapp/>images/step_2.gif hspace=0 vspace=0 border="0">
					</td> 
				</tr> 
<%-- BEGIN USER SEARCH TABLE --%>				
				<tr>
					<td colspan="2">
						<table width="100%" cellpadding="2">
			                <tr>
            			      <td class="formDeLabel"><bean:message key="prompt.name" /></td>
			                  <td class="formDe">
			                  	 <table width="100%"> 
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
								<td class="formDe" colspan="2"><html:text property="userId" size="6" maxlength="5"/></td>
							</tr>
							<logic:equal name="userGroupForm" property="userType" value="MA" >	
							    <logic:equal name="userGroupForm" property="agencyName" value="">
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.agencyName" /></td>
									<td class="formDe" colspan="2"><html:text property="searchAgencyName" size="62" maxlength="60"/></td>
								</tr>
								</logic:equal>
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.departmentName" /></td>
									<td class="formDe" colspan="2"><html:text property="department" size="52" maxlength="50"/></td>
								</tr>
								<tr>
									<td class="formDeLabel"></td>
									<td class="formDe" colspan="2">
										 <html:submit property="submitAction" onclick="return validateSearchFields(this.form) && disableSubmit(this, this.form);">
		  									<bean:message key="button.findUsers"></bean:message>
		 								</html:submit>	
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
		  									<bean:message key="button.refresh"></bean:message>
		 								</html:submit>	
									</td>
								</tr>
							</logic:equal>	
							<logic:equal name="userGroupForm" property="userType" value="SA">
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.departmentName"/></td>
									<td class="formDe" colspan="2">
					            	       <html:select property="departmentId">
            							      <html:option value=""><bean:message key="select.generic" /></html:option>
							            	  <html:optionsCollection property="departments" value="departmentId" label="departmentName" /> 
			  	        		       	   </html:select> 								
									</td>
								</tr>
								<tr>
									<td class="formDeLabel"></td>
									<td class="formDe" colspan="2">
										 <html:submit property="submitAction" onclick="return validateSearchFields(this.form) && disableSubmit(this, this.form);">
		  									<bean:message key="button.findUsers"></bean:message>
		 								</html:submit>	
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
		  									<bean:message key="button.refresh"></bean:message>
		 								</html:submit>	
									</td>
								</tr>
							</logic:equal>
							<logic:equal name="userGroupForm" property="userType" value="ASA">
								<tr>
									<td class="formDeLabel"><bean:message key="prompt.departmentName"/></td>
									<td class="formDe" colspan="2">
					            	       <html:select property="departmentId">
            							      <html:option value=""><bean:message key="select.generic" /></html:option>
							            	  <html:optionsCollection property="departments" value="departmentId" label="departmentName" /> 
			  	        		       	   </html:select> 								
									</td>
								</tr>							
								<tr>
									<td class="formDeLabel"></td>
									<td class="formDe" colspan="2">
										 <html:submit property="submitAction" onclick="return validateSearchFields(this.form) && disableSubmit(this, this.form);">
		  									<bean:message key="button.findUsers"></bean:message>
		 								</html:submit>	
										<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);">
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
            <!-- BEGIN ERROR TABLE -->
           		        <table width="100%">
                		    <tr>		  
								<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
						    </tr>   	  
					    </table>
            <!-- END ERROR TABLE -->
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
						 <!-- BEGIN USER CHECKBOX SELECTION TABLE -->							
							<table border="0" width="100%" cellspacing="1" cellpadding="2">
								<tr bgcolor="#CCCCCC">
									<td class="boldText">
										<input type="checkbox" name="selectAllUsers" onclick="allUsersSelect(this, 'selectedUsers')" />
									   <bean:message key="prompt.name"/>
									    <jims:sortResults beanName="userGroupForm" results="availableUsers" 
                                          primaryPropSort="lastName" primarySortType="STRING"
                                          secondPropSort="firstName" secondarySortType="STRING" defaultSort="true" 
                                          defaultSortOrder="ASC" sortId="1" />
									</td>
									<td class="boldText"><bean:message key="prompt.userId" />
									    <jims:sortResults beanName="userGroupForm" results="availableUsers" 
                                          primaryPropSort="logonId" primarySortType="STRING"
                                          defaultSort="false" 
                                          defaultSortOrder="ASC" sortId="2" />
                                    </td>
									<td class="boldText"><bean:message key="prompt.agency" />/<bean:message key="prompt.department" />
									    <jims:sortResults beanName="userGroupForm" results="availableUsers" 
                                          primaryPropSort="agencyName" primarySortType="STRING"
                                          secondPropSort="departmentName" secondarySortType="STRING" defaultSort="false" 
                                          defaultSortOrder="ASC" sortId="3" />
                                    </td>
								</tr>                
						        <logic:iterate id="usersIndex" name="userGroupForm" property="availableUsers" indexId="index1"> 
									<tr class="<%out.print((index1.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
										<td class="boldText" nowrap>
                               			   <input type="checkbox" name="selectedUsers" value=<bean:write name='usersIndex' property='logonId'/> OnClick="uncheckSelectAll(this, this.form)"> 
	                                        	<a href="javascript: openWindow('/<msp:webapp/>displaySecurityInquiriesUserProfileSearchResults.do?searchJIMSLogonId=<bean:write name="usersIndex" property="logonId"/>&action=view')">
       				                        	<bean:write name="usersIndex" property="lastName" />,&nbsp;<bean:write name="usersIndex" property="firstName" />&nbsp;<bean:write name="usersIndex" property="middleName" /></a>
										</td>
										<td class="boldText" nowrap>
       				                        <bean:write name='usersIndex' property='logonId'/>
										</td>
										<td class="boldText">
       				                        <bean:write name='usersIndex' property='agencyName'/>
       				                        <logic:notEqual name='usersIndex' property='departmentName' value="">
       				                        	/ <bean:write name='usersIndex' property='departmentName'/>
       				                        </logic:notEqual>
										</td>
									</tr>
							    </logic:iterate>		
							</table>
						 <!-- END USER CHECKBOX SELECTION TABLE -->	
						</div>
					</td>
				</tr>
<%-- END USER SEARCH RESULTS TABLE --%>				
<%-- ADD USERS TO LIST BUTTON --%>				
				<tr>
		           <td align="center" colspan="2">
		              <html:submit property="submitAction"><bean:message key="button.addSelectedUsers"></bean:message></html:submit>
		           </td>							    
	            </tr> 
	            </logic:notEmpty> 
<%-- BEGIN USER SELECTION TABLE --%>
				<logic:notEmpty name="userGroupForm" property="currentUsers"> 
       			<tr><td>&nbsp;</td></tr>
       			<logic:equal name="userGroupForm" property="action" value="create">
	       			<tr>
					   <td colspan="2" class="boldText">Selected Users List</td>
					</tr>
				</logic:equal>
       			<logic:equal name="userGroupForm" property="action" value="update">
	       			<tr>
					   <td colspan="2" class="boldText" valign="bottom">Current/Selected Users List</td>
					</tr>
				</logic:equal>
				<tr>
       				<td colspan="2" align="center">	
   					<div class="scrollingDiv100">
					<table width="100%" cellspacing="1" cellpadding="4">
			            <tr bgcolor="#cccccc">
							<td nowrap>&nbsp;</td>
							<td class="boldText"><bean:message key="prompt.name" /></td>
							<td class="boldText" nowrap><bean:message key="prompt.userId" /></td>
							<td class="boldText"><bean:message key="prompt.agency" />/<bean:message key="prompt.department" /></td>
						</tr>
						<logic:iterate id="currentIndex" name="userGroupForm" property="currentUsers" indexId="index2">
			            <tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
							<td nowrap>
							    <a href="/<msp:webapp/>handleUserGroupUserRemove.do?logonId=<bean:write name="currentIndex" property="logonId"/>&pageType=userUpdate" title='Remove <bean:write name="currentIndex" property="lastName"/>, <bean:write name="currentIndex" property="firstName"/> <bean:write name="currentIndex" property="middleName"/>'>Remove</a>
							</td>
							<td class="boldText" nowrap>
							    <bean:write name="currentIndex" property="lastName"/>, <bean:write name="currentIndex" property="firstName"/> <bean:write name="currentIndex" property="middleName"/>
							    <input type="hidden" name="user">
							</td>
							<td class="boldText">
							    <bean:write name="currentIndex" property="logonId"/>
							</td>
							<td class="boldText">
       				            <bean:write name='currentIndex' property='agencyName'/>
       				            <logic:notEqual name='currentIndex' property='departmentName' value="">
       				               	/ <bean:write name='currentIndex' property='departmentName'/>
       				            </logic:notEqual>
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
			<br>
		    <tr>
			    <td align="center">
    			    <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
					   <bean:message key="button.back"></bean:message></html:button>
				 	 <html:submit property="submitAction" ><bean:message key="button.next"/></html:submit>
			 		<html:submit property="submitAction" ><bean:message key="button.cancel"/></html:submit>
				</td>
			</tr>
</html:form>
</table>
<br>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>