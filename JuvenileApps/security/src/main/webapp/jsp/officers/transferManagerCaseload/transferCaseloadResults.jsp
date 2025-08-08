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
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>

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
<title><bean:message key="title.heading" /> - transferCaseloadResults.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->

<!--JAVASCRIPT FILES FOR THIS PAGE -->
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript">
//document.onkeypress = onKeyPress;


function onloadBody( )
{
	document.forms[0].newManagerId.focus();
}

//function onKeyPress( )
//{
//    var keycode = window.event.keyCode;
//    if (keycode == 13)
//    {
//       buttonCheck('S');
//       return false;
//    }

//    return true;
//}

function buttonCheck(button)
{
  	// a quick edit for at least 1 checkbox selected
  	var selectMade = false;
  	for(var i=0; i < document.forms[0].length; i++)
  	{
  		if( document.forms[0].elements[i].type == 'text' )
  		{
  			if(document.forms[0].elements[i].value != '' )
  			{
  				selectMade = true;
  			}
  		}
  	}
}

//This validation will be used in the jsp
function managerUserCheck(thisForm)
{
    //check for value in Manager User ID text field
  	if( thisForm.newManagerId.selectedIndex < 1 )
	  { 
  	   alert("Please enter a value for Manager User ID.");
	   thisForm.newManagerId.focus();
	   return false;
	  }
	return compareManagers(thisForm) 
}

function compareManagers(thisForm)
{
	newManager = (thisForm.newManagerId.value);
	oldManager = (thisForm.managerId.value);
	
	if (newManager == oldManager)
	{
		alert("Cannot transfer officer(s) to the same manager.");
		thisForm.newManagerId.focus();
		return false;
	}	 
	return
}
</script>
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="onloadBody();">
<html:form  action="displayTransferOfficerSummary.do" target="content">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|87">
<!-- BEGIN HEADING TABLE  -->
<table width="100%" border="0" align="center">
    <tr>
      <td align="center" class="header"><bean:message key="title.juvenileProbation" />&nbsp;-&nbsp;<bean:message key="title.transferOfficers" /></td>
    </tr>
</table>
<!-- END HEADING TABLE -->
<%-- Begin Pagination Header Tag--%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
<input type="hidden" name="pager.offset" value="<%= offset %>">
<%-- End Pagination header stuff --%>
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<br>
<!-- BEGIN INSTRUCTION TABLE  -->
<table width="98%">
  <tr>
    <td>
    <ul>
      <li>Enter Manager User ID to transfer selected Officers, then click Next button.</li>
      <li>Select Back button to return to previous page.</li>
    </ul>
   </td>
  </tr>
  <tr>
      <td><bean:message key="prompt.3.diamond" />&nbsp;<bean:message key="prompt.requiredFieldInstruction" /></td>
  </tr>
</table>
  <!-- END INSTRUCTION TABLE  -->

  <!-- BEGIN OFFICERS SELECTED TABLE  -->
<table class="borderTableBlue" width="98%" border="0" cellpadding="2" cellspacing="1">
   <tr class="detailHead">
      <td colspan="5"><bean:message key="prompt.selectedOfficers" /></td>
   </tr>
   <tr bgcolor="#cccccc">
      <td align="left" class="subhead" valign="top" nowrap="nowrap"><bean:message key="prompt.name" /></td>
      <td align="left" class="subhead" valign="top" nowrap="nowrap"><bean:message key="prompt.userId" /></td>
      <td align="left" class="subhead" valign="top"><bean:message key="prompt.subType" /></td>
      <td align="left" class="subhead" valign="top"><bean:message key="prompt.officer" />&nbsp;<bean:message key="prompt.status" /></td>
      <td align="left" class="subhead" valign="top"><bean:message key="prompt.badgeNumber" /></td>
   </tr>
   <logic:notEmpty name="officerForm" property="selectedList">
	   <logic:iterate id="selIter" name="officerForm" property="selectedList" indexId="index">
			<%out.print((index.intValue() == 1) ? ", " : ""); %>
	   <tr>	
		  <td align="left"><a href="/<msp:webapp/>displayTransferOfficerSummary.do?submitAction=Link&officerProfileId=<bean:write name="selIter" property="officerProfileId"/>" title="click on Name to View Officer Details" target="_blank"><bean:write name="selIter" property="formattedName"/></a></td>
          <td align="left"><bean:write name="selIter" property="userId"/></td>
          <td align="left"><bean:write name="selIter" property="officerSubType"/></td>
          <td align="left"><bean:write name="selIter" property="status"/></td>
          <td align="left" nowrap="nowrap"><bean:write name="selIter" property="badgeNum"/></td>
       </tr>
       </logic:iterate>
   </logic:notEmpty>
</table>
  <!-- END OFFICERS SELECTED TABLE  -->
  <br>
  <!-- BEGIN TRANSFER TABLE  -->
<table class="borderTableBlue" width="98%" border="0" cellpadding="2" cellspacing="1">
   <tr class="detailHead">
      <td colspan="5"><bean:message key="prompt.transfer" />&nbsp;<bean:message key="prompt.selectedOfficers" /></td>
   </tr>
    <tr bgcolor="#cccccc">
      <td class="formDeLabel" width="10%" colspan="2"><bean:message key="prompt.from" />: </td>
    </tr>

    <tr>
      <td class="formDe"><b><bean:message key="prompt.manager" />&nbsp;<bean:message key="prompt.name" />:</b></td>
      <td align="left"><bean:write name="officerForm" property="managerLastName"/><logic:notEmpty name="officerForm" property="managerLastName">, </logic:notEmpty>
                                 <bean:write name="officerForm" property="managerFirstName"/>
                                 <bean:write name="officerForm" property="managerMiddleName"/></td>
    </tr>
    <tr>
       <td width="1%" class="formDe" nowrap="nowrap"><b><bean:message key="prompt.manager" />&nbsp;<bean:message key="prompt.userId" />:</b></td>
       <td align="left" colspan=""><bean:write name="officerForm" property="managerId" />
                      <input type="hidden" name="managerId" value=<bean:write name="officerForm" property="managerId" /> ></td>
    </tr>
    <tr>
      <td class="formDeLabel" width="10%" colspan="2">To:</td>
    </tr>
    <tr>
      <td class="formDe" colspan="3">
       <bean:message key="prompt.3.diamond" /><html:select name="officerForm" property ="newManagerId">
            <html:option value=""><bean:message key="select.generic" /></html:option>
		   <html:optionsCollection property="managerProfiles" value="userId" label="formattedName" />
      </html:select>
      </td>
   </tr> 
</table>
<div class=spacer4px></div>
           <!-- END TRANSFER TABLE  -->
     
              <!-- START BUTTON TABLE -->
            <table border="0" width="100%" align="center">
              <tr>
                <td align="center">
                    <html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
                    <html:submit property="submitAction" onclick="return managerUserCheck(this.form)"><bean:message key="button.next"/></html:submit>
                	<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
                </td>
               </tr>
            </table>
                <!-- END BUTTON TABLE --> 
          
<%-- Begin Pagination Closing Tag --%>
</pg:pager>          
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
