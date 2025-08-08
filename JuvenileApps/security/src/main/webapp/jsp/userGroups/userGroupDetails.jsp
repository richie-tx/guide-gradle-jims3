<!DOCTYPE HTML>
<!-- Used for displaying activate, inactivate, delete and view details. -->
<!--MODIFICATIONS -->
<!-- CShimek 05/10/2005	 Create JSP -->
<%-- CShimek 08/07/2006  Activity#34092, revised column heading to Name --%>
<!-- CShimek 01/11/2007  #38306 add multiple submit functionality  -->
<!-- CShimek 03/01/2007  added helpfile reference value for page in view state -->
<!-- CShimek 03/13/2007  #40332 added hyperlink to member name -->
<!-- CShimek 06/21/2007  #42913 revise title to user group details -->
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
<title><bean:message key="title.heading"/> - userGroupDetails.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript">
function openWindow(url)
	{
	alert("URL " + url);
		var newWin = window.open(url, "pictureWindow", "height=300,width=400,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
		newWin.focus();
	}
</script>	
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<%-- BEGIN HEADING AND INSTRUCTION TABLE FOR VIEW --%>
<logic:equal name="userGroupForm" property="action" value="view" >
	<table width="100%">
	  <tr>
    	<td align="center" class="header">
           <bean:message key="title.userGroupDetails"/> 
    	</td>
	  </tr>
<%-- actual groupStatusId values may not be A and I --%>	 	  
   	  <tr>
        <td>
        	<logic:notEqual name="userGroupForm" property="userType" value="ASA">
		       <ul>
		      	  <li>Select the Edit button to update this User Group.</li>
	    	    	<logic:equal name="userGroupForm" property="groupStatusId" value="A" >
			    	    <li>Select the Inactivate button to inactivate this User Group.</li>
			        </logic:equal>
			        <logic:empty name="userGroupForm" property="currentUsers">
			    	    <li>Select the Delete button to delete this User Group.</li>
			        </logic:empty>
			        <logic:equal name="userGroupForm" property="groupStatusId" value="I" >
				        <li>Select the Activate button to activate this User Group.</li>
	        		</logic:equal>
		      </ul>
		    </logic:notEqual>
        	<logic:equal name="userGroupForm" property="userType" value="ASA">
		       <ul>
	    	    	<logic:equal name="userGroupForm" property="groupStatusId" value="A" >
			    	    <li>Select the Update button to update members for this User Group.</li>
			        </logic:equal>
			        <logic:equal name="userGroupForm" property="groupStatusId" value="I" >
				        <li>This is an Inactive User Group, not updates are allowed.</li>
	        		</logic:equal>
		      </ul>
		    </logic:equal> 
		</td>
	  </tr>
	</table>
</logic:equal>	
<%-- BEGIN HEADING AND INSTRUCTION TABLE FOR INACTIVATE --%>

<%-- BEGIN HEADING AND INSTRUCTION TABLE FOR ACTIVATE --%>
<logic:equal name="userGroupForm" property="action" value="activate" >
	<table width="100%">
	  <tr>
    	<td align="center" class="header">
           <bean:message key="title.userGroupActivate"/> 
    	</td>
	  </tr>
   	  <tr>
        <td>
	       <ul>
	        <li>Select the Next button to activate this User Group.</li>
	      </ul>
		</td>
	  </tr>
	</table>
</logic:equal>	
<%-- BEGIN HEADING AND INSTRUCTION TABLE FOR INACTIVATE --%>
<logic:equal name="userGroupForm" property="action" value="inactivate" >
	<table width="100%">
	  <tr>
    	<td align="center" class="header">
           <bean:message key="title.userGroupInactivate"/> 
    	</td>
	  </tr>
   	  <tr>
        <td>
	       <ul>
	        <li>Select the Next button to inactivate this User Group.</li>
	      </ul>
		</td>
	  </tr>
	</table>
</logic:equal>	
<%-- BEGIN HEADING AND INSTRUCTION TABLE FOR DELETE --%>
<logic:equal name="userGroupForm" property="action" value="delete" >
	<table width="100%">
	  <tr>
    	<td align="center" class="header">
           <bean:message key="title.userGroupDelete"/> 
    	</td>
	  </tr>
   	  <tr>
        <td>
	       <ul>
	        <li>Select the Next button to delete this User Group.</li>	       
	      </ul>
		</td>
	  </tr>
	</table>
</logic:equal>	
<%-- END TABLES FOR HEADING AND INSTRUCTIONS --%>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.userGroupInfo" /></td>
					<td align="right"><img src=/<msp:webapp/>images/step_1.gif hspace=0 vspace=0 border=0></td>
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
							<%--logic:equal name="userGroupForm" property="userType" value="SA" --%>
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
							<%--/logic:equal --%>
						</table>
					</td>
				</tr>
			</table>
			<br>
			<!--user members begin-->
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.userGroupMembers" /></td>
					<td align="right"><img src=/<msp:webapp/>images/step_2.gif hspace=0 vspace=0 border="0"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<div style="height:80px; overflow:auto; ">
						<table width="100%" border="0" cellpadding="2">
							<tr>
								<td class="formDeLabel" class="detailHead"><bean:message key="prompt.name"/></td>
								<td class="formDeLabel" class="detailHead"><bean:message key="prompt.agency"/></td>								
							</tr>
						    <logic:iterate id="memberNameIndex" name="userGroupForm" property="currentUsers" indexId="index">
						    <tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
           					    <td>
									<a href="javascript:newCustomWindow('/<msp:webapp/>displaySecurityInquiriesUserProfileDetails.do?action=view&logonId=<bean:write name="memberNameIndex" property="logonId" />','', '500','500')"
                              	       title='View <bean:write name="memberNameIndex" property="lastName" />,&nbsp;<bean:write name="memberNameIndex" property="firstName" />&nbsp;<bean:write name="memberNameIndex" property="middleName" /> details' >
     				              	<bean:write name="memberNameIndex" property="lastName" />,&nbsp;<bean:write name="memberNameIndex" property="firstName" />&nbsp;<bean:write name="memberNameIndex" property="middleName" /> </a>
           					    </td>
         					    <td><bean:write name="memberNameIndex" property="agencyName"/></td>
                            </tr>      	
                            </logic:iterate> 
 						</table>
 						</div>
					</td>
				</tr>
			</table>				
			<br>
		    <tr>
		    <td align="center">
		    <html:form action="/displayUserGroupSummary" target="content"> 
			        <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				          <bean:message key="button.back"></bean:message></html:button>
				    <logic:notEqual name="userGroupForm" property="action" value="view">     
	   	    	    	<html:submit property="submitAction"><bean:message key="button.next"></bean:message></html:submit>
	   	    	    </logic:notEqual>
<%-- actual groupStatusId values may not be A and I --%>	   	    	    
				    <logic:equal name="userGroupForm" property="action" value="view" > 
						<logic:notEqual name="userGroupForm" property="userType" value="ASA"> 
				        	<logic:equal name="userGroupForm" property="groupStatusId" value="A" >
			   	    	    	<html:submit property="submitAction"><bean:message key="button.edit"></bean:message></html:submit>
						        <logic:empty name="userGroupForm" property="currentUsers">
									<html:submit property="submitAction"><bean:message key="button.delete"></bean:message></html:submit>
						        </logic:empty>
								<html:submit property="submitAction"><bean:message key="button.inactivate"></bean:message></html:submit>
					        </logic:equal>
					        <logic:equal name="userGroupForm" property="groupStatusId" value="I" >
								<html:submit property="submitAction"><bean:message key="button.delete"></bean:message></html:submit>
								<html:submit property="submitAction"><bean:message key="button.activate"></bean:message></html:submit>
	        				</logic:equal>
	        			</logic:notEqual> 
	        		</logic:equal>	
				    <logic:equal name="userGroupForm" property="action" value="view" > 	  
						<logic:equal name="userGroupForm" property="userType" value="ASA">    
				        	<logic:equal name="userGroupForm" property="groupStatusId" value="A" >
								<html:submit property="submitAction"><bean:message key="button.update"></bean:message></html:submit>				        	
				        	</logic:equal>
						</logic:equal>
	   	    	    </logic:equal> 
		   	        <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
<!--  Help file references -->
				    <logic:equal name="userGroupForm" property="action" value="view">     				        
						<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|111">
				    </logic:equal>
				    <logic:equal name="userGroupForm" property="action" value="delete">     				        
						<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|86">
				    </logic:equal>
				    <logic:equal name="userGroupForm" property="action" value="activate">     				        
						<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|4">
				    </logic:equal>
				    <logic:equal name="userGroupForm" property="action" value="inactivate">     				        
						<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|126">
				    </logic:equal>
 		   
 		    </html:form>    
		</td>
	</tr>
</table>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>