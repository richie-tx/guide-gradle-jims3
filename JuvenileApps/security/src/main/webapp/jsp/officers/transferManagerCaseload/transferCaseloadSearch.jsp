<!DOCTYPE HTML>
<!--MODIFICATIONS -->
<!-- Debbie Williamson	08/12/2005	Create JSP -->
<!-- RCapestani  		10/09/2015 #30561 MJCW: IE11 conversion of "Officer Profile"  link on UILeftNav -->

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
<title><bean:message key="title.heading" /> - transferCaseloadSearch.jsp</title>
<!--THESE TWO LINES FOR STRUTS & JAVASCRIPTS VALIDATIONS-->

<!--JAVASCRIPT FILES FOR THIS PAGE -->
<%-- <html:javascript/> --%>
<%@include file="../../jQuery.fw"%>
<script type='text/javascript' src="/<msp:webapp/>js/timeout.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#reset").click(function(){
		console.log("Manager id click");
		$("[name='managerId']").val("")
	})
})
document.onkeypress = onKeyPress;

function onloadBody( )
{
	document.forms[0].managerId.focus();
}

function onKeyPress( )
{
    var keycode = window.event.keyCode;
    if (keycode == 13)
    {
       buttonCheck('S');
       return false;
    }

    return true;
}

function checkOfficerSelection()
{
  	// a quick edit for at least 1 checkbox selected
  	var myOption = (-1);
  	if( document.getElementsByName("selectedUserIds").length >= 1 )
  	{  	  	  	
  		for(var i=0; i < document.getElementsByName("selectedUserIds").length; i++)
  		{
  			if( document.getElementsByName("selectedUserIds")[i].checked )
  			{
  				myOption = i;
  			}
  		}
  				
   	}
  	if (myOption == (-1)) {
		alert("You must select one or more Juvenile Profile Officers to proceed.");
		return false;
	}
  	else	
  	    return true;
 }

function managerUserCheck(thisForm)
{
    //check for value in Manager User ID text field
  	if( thisForm.managerId.selectedIndex < 1 )
	  { 
  	   alert("Please enter a value for Manager User ID.");
	   thisForm.managerId.focus();
	   return false;
	  } 
}

function allOfficersSelect(el, checkboxName)
{
	var theForm = el.form;
	var checkAllName = el.name;
	var objCheckBoxes = document.getElementsByName(checkboxName);
	var countCheckBoxes = objCheckBoxes.length;
 

	if(el.checked)
	{
		// set the check value for all check boxes
		for(var i = 0; i < countCheckBoxes; i++)
			objCheckBoxes[i].checked = true;
	}else {
///			uncheckSelectAll(el,checkAllName);
			for(var i = 0; i < countCheckBoxes; i++)
			objCheckBoxes[i].checked = false;
		}
}

</script>
</head>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true)" onload="onloadBody();">
<html:form  action="handleTransferCaseloadSearch.do" target="content">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|87">
  <!-- BEGIN HEADING TABLE  -->
  <table width="100%" border="0" align="center">
    <tr>
      <td align="center" class="header"><bean:message key="title.juvenileProbation" />&nbsp;-
                  <bean:message key="title.search" />&nbsp;<bean:message key="prompt.managerId" />
                   for&nbsp;<bean:message key="prompt.officer" />(s)</td>
    </tr>
  </table>
  <!-- END HEADING TABLE -->

  <table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
  </table>
  <!-- BEGIN INSTRUCTION TABLE  -->
  <br>
  <table width="98%">
    <tr>
      <td><ul>
          <li>Enter Manager User ID then press the &quot;Find Officers&quot; button to search.</li>
        </ul></td>
    </tr>
    <tr>
      <td class="required"><bean:message key="prompt.3.diamond" />&nbsp;<bean:message key="prompt.manager" /></td>
    </tr>
  </table>
  <!-- END INSTRUCTION TABLE  -->
  
  <!-- BEGIN DETAIL 1 TABLE  -->
  <table width="98%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="98%" align="center" valign="top"><!-- BEGIN BLUE BORDER TABLE  -->
        <table class="borderTableBlue" cellpadding="1" cellspacing="0" width="100%">
          <tr>
            <td align="center"><!-- BEGIN ENTRY TABLE  -->
              <table width="100%" border="0" align="center" cellpadding="2" cellspacing="1">
                <tr>
                  <td class="formDeLabel" width="1%" nowrap="nowrap">
                    <bean:message key="prompt.3.diamond" /><bean:message key="prompt.manager" /></td>
                  <td class="formDe" colspan="3">
                  <html:select name="officerForm" property="managerId">
                    <html:option value=""><bean:message key="select.generic" /></html:option>
                   	<html:optionsCollection property="managerProfiles" value="userId" label="formattedName" />
				  </html:select></td>
                </tr>
                <tr>
                  <td class="formDeLabel"></td>
                  <td class="formDe" colspan="3">
	                  <html:submit property="submitAction" onclick="managerUserCheck(this.form)"><bean:message key="button.findOfficers"/></html:submit>                  
				      <input id="reset" type="button" value="Reset" />
				  </td>    
                </tr>
              </table>
              <!-- END ENTRY TABLE  --></td>
          </tr>
        </table>
        <!-- END BLUE BORDER TABLE  --></td>
    </tr>
  </table>
  <!-- END DETAIL 1 TABLE  -->
  <br>
    
 
   <!-- BEGIN SEARCH RESULTS INSTRUCTION TABLE  -->
 <logic:notEmpty name="officerForm" property="officerProfiles">    
  <table width="98%">  
    <tr>
      <td><ul>
          <li>Click Officer checkbox(s) and Next button to continue transfer.</li>
          <li> Select name hyperlink to view the Officer Profile Details.</li>
        </ul></td>
    </tr>
    <tr><bean:size id="officerProfilesSize" name="officerForm" property="officerProfiles" />
      <td align="center"><b><bean:write name="officerProfilesSize"/> search results found for Manager User ID: <bean:write name="officerForm" property="managerId"/></b></td>
    </tr>
 </table>
  
  <!-- END SEARCH RESULTS INSTRUCTION TABLE  -->
    <!-- BEGIN OFFICER DISPLAY TABLE WITH BLUE HEADING -->
   <div class="scrollingDiv200">  
    <table class="borderTableBlue" width="98%" border="0" cellpadding="2" cellspacing="1">
        <tr height="100%" class="detailHead">
          <td colspan="6"><bean:message key="prompt.juvenile" />&nbsp;<bean:message key="prompt.probationOfficer" />s</td>
        </tr>
        <tr height="100%" bgcolor="#cccccc">
              <td width="1%"><input type=checkbox name=checkAll value="test" onClick="allOfficersSelect(this, 'selectedUserIds')" title="Check/Uncheck All"></td>
              <td class="subhead" valign="top" nowrap="nowrap"><bean:message key="prompt.name" /> 
                    <jims:sortResults beanName="officerForm" results="officerProfiles" primaryPropSort="lastName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" levelDeep="3" /></td> 
              <td class="subhead" valign="top" nowrap="nowrap"><bean:message key="prompt.userId" />
                    <jims:sortResults beanName="officerForm" results="officerProfiles" primaryPropSort="userId" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" levelDeep="3" /></td>
              <td class="subhead" valign="top"><bean:message key="prompt.subType" />
                    <jims:sortResults beanName="officerForm" results="officerProfiles" primaryPropSort="officerSubType" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" levelDeep="3" /></td>
              <td class="subhead" valign="top"><bean:message key="prompt.officer" />&nbsp;<bean:message key="prompt.status" />
                    <jims:sortResults beanName="officerForm" results="officerProfiles" primaryPropSort="status" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" levelDeep="3" /></td>
              <td class="subhead" valign="top"><bean:message key="prompt.badgeNumber" />
                    <jims:sortResults beanName="officerForm" results="officerProfiles" primaryPropSort="badgeNum" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" levelDeep="3" /></td>
        </tr>
       
     <logic:iterate id="selIter" name="officerForm" property="officerProfiles" indexId="index">
			  <tr height="100%" class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
			  	<td>
			  	   <input type="checkbox" name="selectedUserIds" value="<bean:write name="selIter" property="userId"/>" indexId="index">
			  	</td>
                <td><a href="/<msp:webapp/>handleTransferCaseloadSearch.do?submitAction=Link&action=view&officerProfileId=<bean:write name="selIter" property="officerProfileId"/>" title="click on Name to View Officer Details" target="_blank"><bean:write name="selIter" property="formattedName"/></a></td>
                <td><bean:write name="selIter" property="userId"/></td>
                <td><bean:write name="selIter" property="officerSubType"/></td>
                <td><bean:write name="selIter" property="status"/></td>
                <td nowrap="nowrap"><bean:write name="selIter" property="badgeNum"/></td>
              </tr>
     </logic:iterate>
   </table>
 </div>  
  </logic:notEmpty>
         
          <!-- END OFFICER DISPLAY TABLE WITH BLUE HEADING -->
 
                  <!--</div> -->

                   <br>     
          <!-- START BUTTON TABLE -->
            <table border="0" width="98%" align="center">
              <tr>
                <td align="center">
                    <html:submit property="submitAction"><bean:message key="button.back"/></html:submit>
                    <html:submit property="submitAction" onclick="return checkOfficerSelection()"><bean:message key="button.next"/></html:submit>
                	<html:submit property="submitAction"><bean:message key="button.cancel"/></html:submit>
                </td>
               </tr>
            </table>
            <!-- END BUTTON TABLE -->
 
</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>
