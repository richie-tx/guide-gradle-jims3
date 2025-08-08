<!DOCTYPE HTML>
<!-- Used for create and update summary, view details and delete role. -->
<!--MODIFICATIONS -->
<!-- CShimek 05/10/2005	 Create JSP -->
<!-- CShimek 01/12/2007  #38306 add multiple submit functionality  -->
<!-- CShimek 02/05/2009  #56860 add Back to Top  -->
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
<title><bean:message key="title.heading"/> - roleSummary.jsp</title>
<!-- JAVASCRIPT FILES -->
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>


</head>
<!--END HEADER TAG-->

<!--BEGIN BODY TAG-->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)">

<!-- BEGIN HEADING AND INSTRUCTION TABLE FOR CREATE SUMMARY -->
<logic:equal name="roleForm" property="action" value="create" >
  <table width="100%">
    <tr>
      <td align="center" class="header">
        <bean:message key="title.createRole"/> <bean:message key="title.summary"/>
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
<logic:equal name="roleForm" property="action" value="update" >
  <table width="100%">
    <tr>
      <td align="center" class="header">
        <bean:message key="title.updateRole"/> <bean:message key="title.summary"/>
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
<logic:equal name="roleForm" property="action" value="view" >
  <table width="100%">
    <tr>
      <td align="center" class="header">
        <bean:message key="prompt.role"/>
        <bean:message key="title.details"/> 
      </td>
    </tr>
  </table>

  <table align="center" width="98%" border="0">
    <tr>
      <td>
        <ul>
          <li>Select Back to return to previous page.</li>
          <li>Select Edit to update this role.</li>
        </ul>
      </td>
    </tr>
  </table>
</logic:equal>	
<!-- END HEADING AND INSTRUCTION TABLES FOR VIEW -->

<!-- BEGIN HEADING AND INSTRUCTION TABLE FOR DELETE -->
<logic:equal name="roleForm" property="action" value="delete" >
  <table width="100%">
    <tr>
      <td align="center" class="header">
        <bean:message key="title.deleteRole"/> 
      </td>
    </tr>
  </table>

  <table align="center" width="98%" border="0">
    <tr>
      <td>
        <ul>
          <li>Select Back to return to previous page.</li>
          <li>Select Delete to delete this role.</li>
        </ul>
      </td>
    </tr>
  </table>
</logic:equal>	
<!-- END HEADING AND INSTRUCTION TABLES FOR DELETE -->

<!-- BEGIN HEADING AND INSTRUCTION TABLE FOR CREATE SUMMARY -->
<logic:equal name="roleForm" property="action" value="copy" >
  <table width="100%">
    <tr>
      <td align="center" class="header">
        <bean:message key="title.copyRole"/> <bean:message key="title.summary"/>
      </td>
    </tr>
  </table>
  
  <table align="center" width="98%" border="0">
    <tr>
      <td>
        <ul>
          <li>Select Finish to complete role copy.</li>
        </ul>
      </td>
    </tr>
  </table>
</logic:equal>	
<!-- BEGIN HEADING AND INSTRUCTION TABLE FOR UPDATE SUMMARY -->

<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>	
		<td width="98%" align="center" valign="top">
			<table class="borderTableBlue"  cellpadding="2" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.roleInfo" /></td>
					<td align="right">
					   <logic:notEqual name="roleForm" property="action" value="update" > 
					      <img src=/<msp:webapp/>images/step_1.gif hspace=0 vspace=0>
					   </logic:notEqual> 
					   <logic:equal name="roleForm" property="action" value="update" > 
					      <img src=/<msp:webapp/>images/step_1_edit.gif hspace=0 vspace=0>
					   </logic:equal> 
					</td>
				</tr>

				<tr>
					<td colspan="2" align="center">
						<table width="100%" cellpadding="4" cellspacing="1">
							<tr>
								<td class="formDeLabel" width="1%" nowrap><bean:message key="prompt.roleName" /></td>
 							<td class="formDe">
									<logic:equal name="roleForm" property="roleName" value=""></logic:equal>
								  <bean:write name="roleForm" property="roleName"/>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap><bean:message key="prompt.roleDescription" /></td>
								<td class="formDe">
                <logic:equal name="roleForm" property="roleDescription" value=""></logic:equal>
                <bean:write name="roleForm" property="roleDescription"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

		<logic:equal name="roleForm" property="masterAdmin" value="Y">
		<br>
			<table class="borderTableBlue" cellpadding="2" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.agencyInfo" />
					  <jims:sortResults beanName="roleForm" results="currentAgencies" 
                                            primaryPropSort="agencyName" primarySortType="STRING" defaultSort="true" 
                                            defaultSortOrder="ASC" sortId="1" /></td>
					<td align="right">
					   <logic:notEqual name="roleForm" property="action" value="update" > 
					      <img src=/<msp:webapp/>images/step_2.gif hspace=0 vspace=0>
					   </logic:notEqual> 
					   <logic:equal name="roleForm" property="action" value="update" > 
					      <img src=/<msp:webapp/>images/step_2_edit.gif hspace=0 vspace=0>
					   </logic:equal> 
					</td>
				</tr>

				<tr>
					<td colspan="2" align="center">
						<table width="100%" border="0" cellpadding="2" cellspacing="1">
							<tr>
								<td class="formDeLabel" class="detailHead"><bean:message key="prompt.agencyName"/>
								</td>
							</tr>

            <logic:iterate id="agencyNameIndex" name="roleForm" property="currentAgencies" indexId="index" >
              <tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
                  <td><bean:write name="agencyNameIndex" property="agencyName"/></td>
              </tr>      	
            </logic:iterate> 
            
			<logic:empty name="roleForm" property="currentAgencies">
              <tr><td>Agencies not available.</td></tr>
            </logic:empty>
					  </table>
					</td>
				</tr>
			</table>
  </logic:equal>

		<logic:notEqual name="roleForm" property="masterAdmin" value="Y">
		<logic:notEqual name="roleForm" property="action" value="delete">	
		<br>
			<table class="borderTableBlue" cellpadding="2" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.agencyInfo" /></td>
					<td align="right">
					   <logic:notEqual name="roleForm" property="action" value="update" > 
					      <img src=/<msp:webapp/>images/step_2.gif hspace=0 vspace=0>
					   </logic:notEqual> 
					   <logic:equal name="roleForm" property="action" value="update" > 
					      <img src=/<msp:webapp/>images/step_2_edit.gif hspace=0 vspace=0>
					   </logic:equal> 
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<table width="100%" border="0" cellpadding="2" cellspacing="1">
							<tr>
								<td class="formDeLabel" class="detailHead"><bean:message key="prompt.agencyName"/></td>
							</tr>
            <logic:iterate id="agencyNameIndex" name="roleForm" property="currentAgencies" indexId="index2">
              <tr class="<%out.print((index2.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
                <td><bean:write name="agencyNameIndex" property="agencyName"/></td>
              </tr>      	
            </logic:iterate> 
            <logic:empty name="roleForm" property="currentAgencies">
            <tr><td>Agencies not available.</td></tr>
            </logic:empty>
 					</table>
					</td>
				</tr>
			</table>
			</logic:notEqual>
			</logic:notEqual>
			
			<br>
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="98%">
				<tr class="detailHead">
					<td class="detailHead"><bean:message key="prompt.features" /> Information<jims:sortResults beanName="roleForm" results="currentFeatures" 
                                            primaryPropSort="featureName" primarySortType="STRING" defaultSort="true" 
                                            defaultSortOrder="ASC" sortId="2" /></td>
					<td align="right">
					   <logic:notEqual name="roleForm" property="action" value="update" > 
					      <img src=/<msp:webapp/>images/step_3.gif hspace=0 vspace=0>
					   </logic:notEqual> 
					   <logic:equal name="roleForm" property="action" value="update" > 
					      <img src=/<msp:webapp/>images/step_3_edit.gif hspace=0 vspace=0>
					   </logic:equal> 

					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					    <bean:size id="currentFeaturesSize" name="roleForm" property="currentFeatures" />	       							
		   				<script type="text/javascript">
						renderScrollingArea(<bean:write name="currentFeaturesSize" />);									
						</script>
							<table width="100%" border="0" cellpadding="2" cellspacing="1">
								<logic:empty name="roleForm" property="currentFeatures"> 
									<tr>
										<td>Features not available.</td>
									</tr>
								</logic:empty>
								<logic:iterate id="currentIndex" name="roleForm" property="currentFeatures" indexId="index3">
									<tr class="<%out.print((index3.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>" height="100%">
								   		<td class="boldText"><bean:write name="currentIndex" property="featureName"/></td>
									</tr>
								</logic:iterate>
							</table>
						</div>
					</td>
				</tr>
			</table>

    <!-- start buttons -->
    <br>
    <tr>
      <td align="center">
        <logic:equal name="roleForm" property="action" value="update" >
          <html:form action="/submitRole" target="content">
            <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);"><bean:message key="button.back"></bean:message></html:button>
            <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>
            <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
			<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|231"> 
          </html:form>
        </logic:equal>

        <logic:equal name="roleForm" property="action" value="create" >
          <html:form action="/submitRole" target="content">
            <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);"><bean:message key="button.back"></bean:message></html:button>
            <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>
            <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
			<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|63"> 
          </html:form>
        </logic:equal>

        <logic:equal name="roleForm" property="action" value="delete" >
          <html:form action="/submitRole" target="content">
            <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);"><bean:message key="button.back"></bean:message></html:button>
            <%-- html:submit property="submitAction"><bean:message key="button.back"></bean:message></html:submit --%>
            <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.delete"></bean:message></html:submit>
            <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
			<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|91"> 
          </html:form>
        </logic:equal>

        <logic:equal name="roleForm" property="action" value="view" >
          <html:form action="/displayRoleSummary" target="content">
            <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);"><bean:message key="button.back"></bean:message></html:button>

            <logic:equal name="roleForm" property="masterAdmin" value="Y">
              <html:submit property="submitAction"><bean:message key="button.edit"></bean:message></html:submit>
                <html:submit property="submitAction"><bean:message key="button.copy"></bean:message></html:submit>
            </logic:equal>

            <logic:equal name="roleForm" property="masterAdmin" value="">
              <logic:equal name="roleForm" property="everyOneRoleName" value="">
                <html:submit property="submitAction"><bean:message key="button.edit"></bean:message></html:submit>
              </logic:equal>
            </logic:equal>

            <logic:equal name="roleForm" property="everyOneRoleName" value="">
              <html:submit property="submitAction"><bean:message key="button.delete"></bean:message></html:submit>
            </logic:equal>

            <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
			 <input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|258"> 
          </html:form>
        </logic:equal>

        <logic:equal name="roleForm" property="action" value="copy" >
          <html:form action="/submitRole" target="content">
            <html:button property="org.apache.struts.taglib.html.BUTTON" onclick="history.go(-1);"><bean:message key="button.back"></bean:message></html:button>
            <html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"></bean:message></html:submit>
            <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
			<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|273"> 
          </html:form>
        </logic:equal>
      </td>
    </tr>
    <!-- end buttons -->

</table>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
</html:html>
