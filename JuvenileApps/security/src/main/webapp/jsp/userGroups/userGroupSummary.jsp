<!DOCTYPE HTML>
<!-- Used for activate, inactivate, create, and update summary pages and view details. -->
<!--MODIFICATIONS -->
<!-- CShimek 05/10/2005	 Create JSP -->
<%-- CShimek 08/07/2006  Activity#34092, revised column heading to Name --%>
<!-- CShimek 01/11/2007  #38306 add multiple submit functionality  -->
<!-- CShimek 02/06/2009  #56860 add Back to Top  -->
<!-- RYoung  10/20/2015  #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

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
<title><bean:message key="title.heading"/> - userGroupSummary.jsp</title>
<!-- APP JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<%-- BEGIN HEADING AND INSTRUCTION TABLE FOR CREATE SUMMARY --%>
<logic:equal name="userGroupForm" property="action" value="create" >
	<table width="100%">
	  <tr>
    	<td align="center" class="header">
           <bean:message key="title.userGroupCreate"/> <bean:message key="title.summary"/>
    	</td>
	  </tr>
	</table>
	<table align="center" width="98%" border="0">
   	  <tr>
        <td>
	       <ul>
	        <li>The following User Group will be created when you select the Finish or Add Users button.</li>
	      </ul>
		</td>
	  </tr>
	</table>
</logic:equal>	
<%-- END HEADING AND INSTRUCTION TABLE FOR CREATE SUMMARY --%>
<%-- BEGIN HEADING AND INSTRUCTION TABLE FOR ACTIVATE SUMMARY --%>
<logic:equal name="userGroupForm" property="action" value="activate" >
	<table width="100%">
	  <tr>
    	<td align="center" class="header">
           <bean:message key="title.userGroupActivate"/> <bean:message key="title.summary"/>
    	</td>
	  </tr>
	</table>
	<table align="center" width="98%" border="0">
   	  <tr>
        <td>
	       <ul>
	        <li>Select the Finish button to activate this User Group.</li>
	      </ul>
		</td>
	  </tr>
	</table>
</logic:equal>
<%-- END HEADING AND INSTRUCTION TABLE FOR ACTIVATE SUMMARY --%>	
<%-- BEGIN HEADING AND INSTRUCTION TABLE FOR INACTIVATE SUMMARY --%>
<logic:equal name="userGroupForm" property="action" value="inactivate" >
	<table width="100%">
	  <tr>
    	<td align="center" class="header">
           <bean:message key="title.userGroupInactivate"/> <bean:message key="title.summary"/>
    	</td>
	  </tr>
	</table>
	<table align="center" width="98%" border="0">
   	  <tr>
        <td>
	       <ul>
	        <li>Select the Next button to inactivate this User Group.</li>
	      </ul>
		</td>
	  </tr>
	</table>
</logic:equal>
<%-- END HEADING AND INSTRUCTION TABLE FOR INACTIVATE SUMMARY --%>	
<%-- BEGIN HEADING AND INSTRUCTION TABLE FOR DELETE SUMMARY --%>
<logic:equal name="userGroupForm" property="action" value="delete" >
	<table width="100%">
	  <tr>
    	<td align="center" class="header">
           <bean:message key="title.userGroupDelete"/> <bean:message key="title.summary"/>
    	</td>
	  </tr>
	</table>
	<table align="center" width="98%" border="0">
   	  <tr>
        <td>
	       <ul>
	        <li>Verify you want to delete this User Group then select the Finish button.</li>
	      </ul>
		</td>
	  </tr>
	</table>
</logic:equal>	
<%-- END HEADING AND INSTRUCTION TABLE FOR DELETE SUMMARY --%>
<%-- BEGIN HEADING AND INSTRUCTION TABLE FOR UPDATE SUMMARY --%>
<logic:equal name="userGroupForm" property="action" value="update" >
	<table width="100%">
	  <tr>
    	<td align="center" class="header">
           <bean:message key="title.userGroupUpdate"/> <bean:message key="title.summary"/>
    	</td>
	  </tr>
	</table>
	<table align="center" width="98%" border="0">
   	  <tr>
        <td>
	       <ul>
	        <li>Verify that information is correct the select Finish button to update User Group.</li>
   	        <li>If any changes are needed, select Back button.</li>
	      </ul>
		</td>
	  </tr>
	</table>
</logic:equal>	
<%-- END HEADING AND INSTRUCTION TABLE FOR UPDATE SUMMARY --%>

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
							<%--<logic:equal name="userGroupForm" property="userType" value="SA">--%>
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
							<%--</logic:equal>--%>
						</table>
					</td>
				</tr>
			</table>
			<br> 
			<!--user members begin-->
			<logic:notEqual name="userGroupForm" property="action" value="create">
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.userGroupMembers" /></td>
					<td align="right"></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<div style="height:80px; overflow:auto; ">
						<table width="100%" border="0" cellpadding=2>
							<tr>
								<td class="formDeLabel" class="detailHead"><bean:message key="prompt.name"/></td>
								<td class="formDeLabel" class="detailHead"><bean:message key="prompt.agency"/></td>								
							</tr>
						    <logic:iterate id="memberNameIndex" name="userGroupForm" property="currentUsers" indexId="index">
						    <tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
         					    <td><bean:write name="memberNameIndex" property="lastName"/>, <bean:write name="memberNameIndex" property="firstName"/> <bean:write name="memberNameIndex" property="middleName"/></td>
         					    <td><bean:write name="memberNameIndex" property="agencyName"/></td>
                            </tr>      	
                            </logic:iterate> 
 						</table>
 						</div>
					</td>
				</tr>
			</table>
			<br>
			</logic:notEqual>				
		    <tr>
		    <td align="center">
<%-- these buttons need to be refined per action --%>		    
		    <logic:equal name="userGroupForm" property="action" value="update" >
			    <html:form action="/submitUserGroupUpdate" target="content"> 
			        <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				          <bean:message key="button.back"></bean:message></html:button>
	   	    	    <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message>
			    	</html:submit>
		   	        <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message>
				    </html:submit>
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|237">				
				</html:form>    
			</logic:equal>    
		    <logic:equal name="userGroupForm" property="action" value="create" >
			    <logic:equal name="userGroupForm" property="userType" value="MA">
				    <html:form action="/submitUserGroupCreateMA" target="content"> 
				        <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
					          <bean:message key="button.back"></bean:message></html:button>
	   	    		    <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message>
			    		</html:submit>
			   	        <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message>
					    </html:submit>
			   	        <html:submit property="submitAction"><bean:message key="button.addUsers"></bean:message>
					    </html:submit>
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|74">				
					</html:form>    
				</logic:equal>		
			    <logic:notEqual name="userGroupForm" property="userType" value="MA">
				    <html:form action="/submitUserGroupCreate" target="content"> 
				        <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
					          <bean:message key="button.back"></bean:message></html:button>
	   	    		    <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message>
			    		</html:submit>
			   	        <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message>
					    </html:submit>
			   	        <html:submit property="submitAction"><bean:message key="button.addUsers"></bean:message>
					    </html:submit>
						<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|74">				
					</html:form>    
				</logic:notEqual>		
			</logic:equal>    
		    <logic:equal name="userGroupForm" property="action" value="delete" >
			    <html:form action="/submitUserGroupUpdate" target="content"> 
			        <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				          <bean:message key="button.back"></bean:message></html:button>
	   	    	    <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message>
			    	</html:submit>
		   	        <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message>
				    </html:submit>
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|264">				
				</html:form>    
			</logic:equal>    
		    <logic:equal name="userGroupForm" property="action" value="activate" >
			    <html:form action="/submitUserGroupUpdate" target="content"> 
			        <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				          <bean:message key="button.back"></bean:message></html:button>
	   	    	    <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message>
			    	</html:submit>
		   	        <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message>
				    </html:submit>
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|6">				
				</html:form>    
			</logic:equal>    
		    <logic:equal name="userGroupForm" property="action" value="inactivate" >
			    <html:form action="submitUserGroupUpdate" target="content"> 
			        <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				          <bean:message key="button.back"></bean:message></html:button>
	   	    	    <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message>
			    	</html:submit>
		   	        <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message>
				    </html:submit>
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|128">				
				</html:form>    
			</logic:equal>    
		</td>
	</tr>
</table>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>