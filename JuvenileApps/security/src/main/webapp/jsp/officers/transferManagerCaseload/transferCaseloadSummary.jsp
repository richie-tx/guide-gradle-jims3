<!DOCTYPE HTML>
<!--MODIFICATIONS -->
<!-- Debbie Williamson	02/10/2011	Create JSP -->
<!-- Richard Capestani  10/09/2015 #30561 MJCW: IE11 conversion of "Officer Profile"  link on UILeftNav -->

<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.UIConstants" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />

<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/security.css"/>
<html:base />
<title><bean:message key="title.heading" /> - transferCaseloadSummary.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->

<!--JAVASCRIPT FILES FOR THIS PAGE -->
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript">
function onloadForm()
		{
		 
		}
</script>
</head>
<!--HEADER TAG -->
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)" topmargin="0" leftmargin="0" onLoad="onloadForm();">
<html:form  action="submitTransferOfficerSummary.do" target="content">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|87">
<!--BEGIN PAGE TITLE TABLE-->
	<table width="100%">
		<tr>
			<td align="center" class="header"><bean:message key="title.juvenileProbation" />&nbsp;-&nbsp;<bean:message key="title.transferOfficers" />
			  <logic:equal name="officerForm" property="action" value="<%=UIConstants.SUMMARY%>" ><bean:message key="title.summary" /></logic:equal>
			  <logic:equal name="officerForm" property="action" value="<%=UIConstants.CONFIRM%>" ><bean:message key="title.confirmation" /></logic:equal>
			</td>
		</tr>
	    <logic:equal name="officerForm" property="action" value="<%=UIConstants.CONFIRM%>" >
		  <tr>
			<td align="center" class="confirm">The following officer(s) have been transferred.</td>
		  </tr>
		</logic:equal> 
	</table>
<!--END PAGE TITLE TABLE-->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
	<div class="spacer"></div>
<!-- BEGIN INSTRUCTION TABLE -->
  <logic:equal name="officerForm" property="action" value="summary" >
	<table width="98%" border="0">
		<tr>
			<td>
				<ul>
					<li>Select Finish button to transfer officers.</li>
					<li>Select Back button to return to previous page.</li>
				</ul>
			</td>
		</tr>
	</table>
  </logic:equal>	
<!-- END INSTRUCTION TABLE -->
	<div class="spacer"></div>
<!-- BEGIN MANAGER TABLE  -->
<table class="borderTableBlue" cellpadding="1" cellspacing="0" width="98%">
  <tr>
    <td align="center">
    <!-- BEGIN MANAGER INNER TABLE  -->
    <table width="100%" border="0" align="center" cellpadding="2" cellspacing="1">
        <tr bgcolor="#cccccc">
          <td class="formDeLabel" width="10%" colspan="2"><bean:message key="prompt.from" />: </td>
        </tr>

        <tr>
          <td align="left" class="formDeBold"><bean:message key="prompt.manager" />&nbsp;<bean:message key="prompt.name" />:</td>
          <td align="left"><bean:write name="officerForm" property="managerLastName"/><logic:notEmpty name="officerForm" property="managerLastName">, </logic:notEmpty>
                                 <bean:write name="officerForm" property="managerFirstName"/>
                                 <bean:write name="officerForm" property="managerMiddleName"/></td>
        </tr>
        <tr>
		   <td align="left" width="1%" class="formDeBold" nowrap="nowrap"><bean:message key="prompt.manager" />&nbsp;<bean:message key="prompt.userId" />:</td>
		   <td align="left" colspan=""><bean:write name="officerForm" property="managerId" /></td>
        </tr>
        <tr>
          <td class="formDeLabel" width="10%" colspan="2"><bean:message key="prompt.to" />:</td>
        </tr>
        <tr>
          <td align="left" class="formDeBold"><bean:message key="prompt.manager" />&nbsp;<bean:message key="prompt.name" />:</td>
          <td align="left"><bean:write name="officerForm" property="newManagerLastName"/><logic:notEmpty name="officerForm" property="newManagerLastName">, </logic:notEmpty>
                                 <bean:write name="officerForm" property="newManagerFirstName"/>
                                 <bean:write name="officerForm" property="newManagerMiddleName"/></td>
        </tr>
        <tr>
		  <td align="left" width="1%" nowrap="nowrap" class="formDeBold"><bean:message key="prompt.manager" />&nbsp;<bean:message key="prompt.userId" />:</td>
		  <td align="left"><bean:write name="officerForm" property="newManagerId" /></td>
        </tr>
      </table>
      <!-- BEGIN MANAGER INNER TABLE  -->
     </td>
    </tr>
</table>
<!-- END MANAGER TABLE  -->
<br>
<!--BEGIN OFFICER TRANSFER TABLE-->
<table width="98%" border="0" cellspacing="1" cellpadding="2" align="center" class="borderTableBlue">
        <tr>
          <td class="detailHead" colspan="5">&nbsp;<bean:message key="prompt.officer" />(s)&nbsp;<bean:message key="prompt.to" />
                                       <bean:message key="prompt.transfer" /></td>
        </tr>
   <%--     <tr>
          <td> --%>
          <!--BEGIN OFFICER TRANSFER INNER TABLE-->
     <%--    <table width="100%" cellpadding="2" cellspacing="1" border="0">  --%>
              <tr>
                <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.name" /></td>
                <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.userId" /></td>
                <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.subType" /></td>
                <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.officer" />&nbsp;<bean:message key="prompt.status" /></td>
                <td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.badgeNumber" /></td>
              </tr>
              <logic:notEmpty name="officerForm" property="selectedList">
				<logic:iterate id="selIter" name="officerForm" property="selectedList" indexId="index">
						<%out.print((index.intValue() == 1) ? ", " : ""); %>
	  	 			<tr>	
			  			<td align="left"><bean:write name="selIter" property="formattedName"/></td>
          				<td align="left"><bean:write name="selIter" property="userId"/></td>
          				<td align="left"><bean:write name="selIter" property="officerSubType"/></td>
          				<td align="left"><bean:write name="selIter" property="status"/></td>
          				<td align="left" nowrap="nowrap"><bean:write name="selIter" property="badgeNum"/></td>
       				</tr>
       		 	</logic:iterate>
  			 </logic:notEmpty>
            </table>
            <!--END OFFICER TRANSFER INNER TABLE-->
  <%--    </td>
     </tr>
</table> --%>
<!--END OFFICER TRANSFER TABLE-->
<br>
<!--BEGIN BUTTON TABLE-->
<table width="98%" border="0" align="center">
   <!-- SUMMARY BUTTONS -->
        <logic:equal name="officerForm" property="action" value="<%=UIConstants.SUMMARY%>" >    
  			<tr>
				<td align="center">
        			<html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
        	    	<html:submit property="submitAction" onclick="return disableSubmit(this, this.form);"><bean:message key="button.finish"/></html:submit>
        			<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
				</td>
   			</tr> 
       </logic:equal> 
	<!-- CONFIRMATION BUTTONS --> 
    <logic:equal name="officerForm" property="action" value="<%=UIConstants.CONFIRM%>" > 
	        <tr>
				<td align="center">
        			<html:submit property="submitAction"><bean:message key="button.transferManagerSearch"/></html:submit>
				</td>
   			</tr> 
	</logic:equal>
</table>
<!--END BUTTON TABLE-->

</html:form>
</body>
</html:html>