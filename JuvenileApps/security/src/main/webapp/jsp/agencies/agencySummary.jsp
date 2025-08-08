<!DOCTYPE HTML>
<!-- 07/05/2005	 Aswin Widjaja - Create JSP -->
<!-- 01/10/2007  C Shimek      - #38306 add multiple submit functionality  -->
<!-- 02/04/2009  C Shimek      - #56860 add Back to Top  -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<!-- TAB LIBRARIES NEEDED FOR MOJO -->
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
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css" />
<html:base />
<title><bean:message key="title.heading" /> - agencySummary.jsp</title>
<!-- AUTO TAB JAVASCRIPT FILE -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- BEGIN HEADING TABLE -->
<table width="100%">
  <tr>
    <td align="center" class="header">
       	
		<logic:equal name="agencyForm" property="action" value="view">
			<bean:message key="title.view"/> <bean:message key="title.agencyDetails"/>
		</logic:equal>
		<logic:notEqual name="agencyForm" property="pageType" value="confirm">
			<logic:equal name="agencyForm" property="action" value="create">
				<bean:message key="title.createAgency"/> <bean:message key="title.summary"/>
			</logic:equal>
			<logic:equal name="agencyForm" property="action" value="update">
				<bean:message key="title.updateAgency"/> <bean:message key="title.summary"/>
			</logic:equal>
			<logic:equal name="agencyForm" property="action" value="delete">
				<bean:message key="title.deleteAgency"/> <bean:message key="title.summary"/>
			</logic:equal>
		</logic:notEqual>
		
		<logic:equal name="agencyForm" property="pageType" value="confirm">
			<logic:equal name="agencyForm" property="action" value="create">
				<bean:message key="title.createAgency"/> <bean:message key="title.confirmation"/>
			</logic:equal>
			<logic:equal name="agencyForm" property="action" value="update">
				<bean:message key="title.updateAgency"/> <bean:message key="title.confirmation"/>
			</logic:equal>
			<logic:equal name="agencyForm" property="action" value="delete">
				<bean:message key="title.deleteAgency"/> <bean:message key="title.confirmation"/>
			</logic:equal>
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
<table width="100%" border="0">	
   <tr>
	<logic:equal name="agencyForm" property="action" value="view">
		<td><ul><li>Select Update button to update this agency.</li>
		<li>Select Delete button to delete this agency.</li></ul></td>
	</logic:equal>
		
	<logic:notEqual name="agencyForm" property="pageType" value="confirm">
		<logic:empty name="agencyForm" property="errorMessage">
			<logic:equal name="agencyForm" property="action" value="update">
				<td><ul><li>The following agency will be updated when you select the Finish button.</li></ul></td>
			</logic:equal>
			<logic:equal name="agencyForm" property="action" value="create">
				<td><ul><li>The following agency will be created when you select the Finish button.</li></ul></td>
			</logic:equal>
			<logic:equal name="agencyForm" property="action" value="delete">			
				<td><ul><li>The following agency will be deleted when you select the Finish button.</li></ul></td>
			</logic:equal>
		</logic:empty>
		<logic:notEmpty name="agencyForm" property="errorMessage">
				<table width="100%">
					<tr>
						<td align="center" class="errorAlert"><bean:write name="agencyForm" property="errorMessage"/></td>		  
					</tr>
				</table>
		</logic:notEmpty>
	</logic:notEqual>
	
	<logic:equal name="agencyForm" property="pageType" value="confirm">
		<logic:equal name="agencyForm" property="action" value="create">
			<td align="center" class="confirm">This agency has been successfully created.</td>
		</logic:equal>
		<logic:equal name="agencyForm" property="action" value="update">
			<td align="center" class="confirm">This agency has been successfully updated.</td>
		</logic:equal>
		<logic:equal name="agencyForm" property="action" value="delete">
			<td align="center" class="confirm">This agency has been successfully deleted.</td>
		</logic:equal>
	</logic:equal>
  </tr>	
</table>
<!-- END INSTRUCTION TABLE -->
<table width="100%" border="0" cellpadding="0" cellspacing="0"> 
    <tr>
      <td width="98%" align="center" valign="top"> 
        <table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
          <tr class="detailHead">
            <td class="detailHead"><bean:message key="prompt.agencyInfo"/></td>
	      </tr>
	      <tr>
	        <td align="center">
			    <table border="0" cellspacing="1" cellpadding="2" width="100%">
	                <tr>
	                   <td class="formDeLabel"><bean:message key="prompt.agencyName"/></td>
	                   <td class="formDe"><bean:write name="agencyForm" property="agencyName"/></td>
	                </tr>
	                <tr>
	                   <td class="formDeLabel"><bean:message key="prompt.agencyType"/></td>
	                   <td class="formDe"><bean:write name="agencyForm" property="agencyType"/></td>
	                </tr>
	                <tr>
	                   <td class="formDeLabel"><bean:message key="prompt.agencyCode"/></td>
	                   <td class="formDe"><bean:write name="agencyForm" property="agencyId"/></td>
	                </tr>
	                <tr>
	                   <td class="formDeLabel" nowrap width="1%"><bean:message key="prompt.jmcRepresentative"/></td>
	                   <td class="formDe">
							<logic:equal name="agencyForm" property="jmcRep" value="Y">
								<bean:message key="prompt.yes"/>
							</logic:equal>
							<logic:equal name="agencyForm" property="jmcRep" value="N">
								<bean:message key="prompt.no"/>
							</logic:equal>
					   </td>
                    </tr>
                </table>
		      </td>
          </tr>
       </table> 
      </td>
    </tr>    		
</table>
  
  <input type="hidden" name="submitted" value="true">
  
 <!--       <script>
        	if (location.search == "?showResults")
        	{
        		show("resultSet", 1)
        	}
        </script> -->
        <br>
        <!--BEGIN BUTTON TABLE--> 
<table width="98%" border="0">
	<tr>
    	<td align="center">
		<logic:equal name="agencyForm" property="action" value="view">
			<html:form action="/displayAgencySummary">
				<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
					<bean:message key="button.back"></bean:message></html:button>
				<html:submit property="submitAction"><bean:message key="button.update"/></html:submit>
				<html:submit property="submitAction"><bean:message key="button.delete"/></html:submit>
				<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
				<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|##"> 
			</html:form>
		</logic:equal>
		
		<logic:notEqual name="agencyForm" property="pageType" value="confirm">
			<logic:equal name="agencyForm" property="action" value="create">
				<html:form action="/submitAgencyUpdate">
					<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
						<bean:message key="button.back"></bean:message></html:button>
					<logic:empty name="agencyForm" property="errorMessage">
						<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>
					</logic:empty>
					<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|41"> 				
				</html:form>
			</logic:equal>
			
			<logic:equal name="agencyForm" property="action" value="update">				
				<html:form action="/submitAgencyUpdate">
					<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
						<bean:message key="button.back"></bean:message></html:button>
					<logic:empty name="agencyForm" property="errorMessage">
						<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>
					</logic:empty>
					<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|217">
				</html:form>
			</logic:equal>
			
			<logic:equal name="agencyForm" property="action" value="delete">
				<html:form action="/submitAgencyUpdate">
					<html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);">
						<bean:message key="button.back"></bean:message></html:button>
					<logic:messagesNotPresent>
					   <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>
					</logic:messagesNotPresent>
					<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|85">
				</html:form>
			</logic:equal>
		</logic:notEqual>	
		
		<logic:equal name="agencyForm" property="pageType" value="confirm">
			<html:form action="/submitAgencyUpdate">
				<html:submit property="submitAction"><bean:message key="button.mainPage"/></html:submit>
				<logic:equal name="agencyForm" property="action" value="create">
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|40">	
				</logic:equal>			
				<logic:equal name="agencyForm" property="action" value="update">
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|216">	
				</logic:equal>			
				<logic:equal name="agencyForm" property="action" value="delete">
					<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|84">	
				</logic:equal>			
			</html:form>
		</logic:equal>
	</td>
    </tr>
</table>
<!-- END BUTTON TABLE -->
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
