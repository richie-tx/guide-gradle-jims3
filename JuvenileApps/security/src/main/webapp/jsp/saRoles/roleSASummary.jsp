<!DOCTYPE HTML>
<!-- Used for create and update summary, view details and delete role. -->
<!--MODIFICATIONS -->
<!-- CShimek 03/30/2005	 Create JSP -->
<!-- CShimek 03/20/2006  #29710 Revise entry order of Role and Agency -->
<!-- CShimek 01/12/2007  #38306 add multiple submit functionality  -->
<!-- CShimek 02/05/2009  #56860 add Back to Top  -->

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
<title><bean:message key="title.heading"/> - roleSASummary.jsp</title>

<!-- APP JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN HEADING AND INSTRUCTION TABLE FOR CREATE SUMMARY -->
<logic:equal name="roleSAForm" property="action" value="create" >
	<table width="100%">
	  <tr>
    	<td align="center" class="header">
           <bean:message key="title.saRoleCreate"/> <bean:message key="title.summary"/>
    	</td>
	  </tr>
	</table>
	<table align="center" width="98%" border="0">
   	  <tr>
        <td>
	       <ul>
	        <li>Select Finish to complete role create.</li>
	      </ul>
		</td>
	  </tr>
	</table>
</logic:equal>	
<!-- BEGIN HEADING AND INSTRUCTION TABLE FOR UPDATE SUMMARY -->
<logic:equal name="roleSAForm" property="action" value="update" >
	<table width="100%">
	  <tr>
    	<td align="center" class="header">
           <bean:message key="title.saRoleUpdate"/>&nbsp;<bean:message key="title.summary"/>
    	</td>
	  </tr>
	</table>
	<table align="center" width="98%" border="0">
   	  <tr>
        <td>
	       <ul>
	        <li>Select Finish to complete update.</li>
 	      </ul>
		</td>
	  </tr>
	</table>
</logic:equal>	
<!-- END HEADING AND INSTRUCTION TABLES FOR UPDATE SUMMARY -->
<!-- BEGIN HEADING AND INSTRUCTION TABLE FOR VIEW SUMMARY -->
<logic:equal name="roleSAForm" property="action" value="view" >
	<table width="100%">
	  <tr>
    	<td align="center" class="header">
           <bean:message key="prompt.sa"/> <bean:message key="prompt.role"/> <bean:message key="title.details"/>
    	</td>
	  </tr>
	</table>
	<table align="center" width="98%" border="0">
   	  <tr>
        <td>
	       <ul>
	        <li>Select Back to return to previous page.</li>
	        <li>Select Edit to update this SA role.</li>
		    <logic:equal name="roleSAForm" property="allSecAdminNameInd" value="">	
	   	        <li>Select Delete to delete this SA role.</li>        
   	        </logic:equal>	
 	      </ul>
		</td>
	  </tr>
	</table>
</logic:equal>	
<!-- END HEADING AND INSTRUCTION TABLES FOR VIEW -->
<!-- BEGIN HEADING AND INSTRUCTION TABLE FOR DELETE -->
<logic:equal name="roleSAForm" property="action" value="delete" >
	<table width="100%">
	  <tr>
    	<td align="center" class="header">
           <bean:message key="title.saRoleDelete"/>&nbsp;<bean:message key="title.summary"/>
    	</td>
	  </tr>
	</table>
	<table align="center" width="98%" border="0">
   	  <tr>
        <td>
	       <ul>
	        <li>Select Back to return to previous page.</li>
	        <li>Select Delete to delete this SA role.</li>	        
 	      </ul>
		</td>
	  </tr>
	</table>
</logic:equal>	
<!-- END HEADING AND INSTRUCTION TABLES FOR DELETE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>	
		<td width="98%" align="center" valign="top">
<!-- BEGIN AGENCY TABLE -->
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.agencyInfo" /></td>
					<td align="right">
					   <logic:notEqual name="roleSAForm" property="action" value="update" > 
					      <img src=/<msp:webapp/>images/step_1.gif hspace=0 vspace=0>
					   </logic:notEqual> 
					   <logic:equal name="roleSAForm" property="action" value="update" > 
					      <img src=/<msp:webapp/>images/step_1_edit.gif hspace=0 vspace=0>
					   </logic:equal> 
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table width="100%" border="0" cellpadding="2">
							<tr>
								<td width="1%" class="formDeLabel" nowrap><bean:message key="prompt.agencyName" /></td>
								<td class="formDe">
									<bean:write name="roleSAForm" property="agencyName" />
									<logic:equal name="roleSAForm" property="agencyName" value=""></logic:equal>
                                </td>
							</tr>			                
 						</table>
					</td>
				</tr>
			</table>
<!-- END AGENCY TABLE -->			
			<br>
<!-- BEGIN ROLE TABLE -->
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.saRoleInfo" /></td>
					<td align="right">
					   <logic:notEqual name="roleSAForm" property="action" value="update" > 
					      <img src=/<msp:webapp/>images/step_2.gif hspace=0 vspace=0>
					   </logic:notEqual> 
					   <logic:equal name="roleSAForm" property="action" value="update" > 
					      <img src=/<msp:webapp/>images/step_2_edit.gif hspace=0 vspace=0>
					   </logic:equal> 
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table width="100%" cellpadding="4">
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.roleName" /></td>
								<td class="formDe">
	                                 <logic:equal name="roleSAForm" property="roleName" value=""></logic:equal>             
								     <bean:write name="roleSAForm" property="roleName"/>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap><bean:message key="prompt.roleDescription" /></td>
								<td class="formDe">
	                                 <logic:equal name="roleSAForm" property="roleDescription" value=""></logic:equal>             								
								     <bean:write name="roleSAForm" property="roleDescription"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
<!-- END ROLE TABLE -->			
		<br>
<!-- BEGIN FEATURES TABLE -->		
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead">
						<logic:equal name="roleSAForm" property="action" value="update" > 
					    	Selected 
						</logic:equal>    
						<logic:equal name="roleSAForm" property="action" value="create" > 
					    	Selected 
						</logic:equal>    
					    <bean:message key="prompt.saFeaturesForDelAndUse" />
					</td>
					<td align="right">
					   <logic:notEqual name="roleSAForm" property="action" value="update" > 
					      <img src=/<msp:webapp/>images/step_3.gif hspace=0 vspace=0>
					   </logic:notEqual> 
					   <logic:equal name="roleSAForm" property="action" value="update" > 
					      <img src=/<msp:webapp/>images/step_3_edit.gif hspace=0 vspace=0>
					   </logic:equal> 
					</td>
				</tr>
				<tr bgcolor="#cccccc">
					<td class="boldText" colspan="2"><bean:message key="prompt.featureName" />s</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					    <div style="height:80px; overflow:auto; ">
							<table width="100%" border="0" cellpadding="2">
								<logic:empty name="roleSAForm" property="currentFeatures"> 
									<tr>
										<td>Features not available.</td>             
									</tr>
								</logic:empty>
								<logic:notEmpty name="roleSAForm" property="currentFeatures"> 
								<logic:iterate id="currentIndex" name="roleSAForm" property="currentFeatures" indexId="index">
									<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
								   		<td class="boldText"><bean:write name="currentIndex" property="featureName"/></td>
									</tr>
								</logic:iterate>
								</logic:notEmpty>
							</table>
						</div>
					</td>
				</tr>
			</table>
<!-- END FEATURES TABLE -->			
			<br>
		    <tr>
		    <td align="center">
		    <logic:equal name="roleSAForm" property="action" value="update" >
			    <html:form action="/submitSARole" target="content"> 
			        <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				          <bean:message key="button.back"></bean:message></html:button>
	   	    	    <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message>
			    	</html:submit>
		   	        <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message>
				    </html:submit>
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|234">	
				</html:form>    
			</logic:equal>    
		    <logic:equal name="roleSAForm" property="action" value="create" >
			    <html:form action="/submitSARole" target="content"> 
			        <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				          <bean:message key="button.back"></bean:message></html:button>
	   	    	    <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message>
			    	</html:submit>
		   	        <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message>
				    </html:submit>
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|68"> 		    
				</html:form>    
			</logic:equal>    
		    <logic:equal name="roleSAForm" property="action" value="delete" >
   			    <html:form action="/submitSARole" target="content"> 
			        <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				          <bean:message key="button.back"></bean:message></html:button>
		   	        <%-- html:submit property="submitAction"><bean:message key="button.back"></bean:message>
				    </html:submit --%>
		   	        <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.delete"></bean:message>
				    </html:submit>
		   	        <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message>
				    </html:submit>
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|95">			    
   				</html:form> 
			</logic:equal>    
		    <logic:equal name="roleSAForm" property="action" value="view" >
			    <html:form action="/displaySARoleSummary" target="content"> 
			        <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
				          <bean:message key="button.back"></bean:message></html:button>
		   	        <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.edit"></bean:message>
				    </html:submit>
				    <logic:equal name="roleSAForm" property="allSecAdminNameInd" value="">
	   	   		        <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.delete"></bean:message>
					    </html:submit>
					</logic:equal>    
		   	        <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message>
				    </html:submit>
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|259">			    
				</html:form> 
			</logic:equal>    
		</td>
	</tr>
</table>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>