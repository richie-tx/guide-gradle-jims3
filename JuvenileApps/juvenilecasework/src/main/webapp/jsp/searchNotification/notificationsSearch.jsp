<!DOCTYPE HTML>
<%-- Used for searching of juvenile profile in MJCW --%>
<%--MODIFICATIONS --%>
<%-- DWilliamson  06/03/2005	Create JSP --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDJuvenileCaseConstants" %>
<%@ page import="naming.PDCodeTableConstants" %>



<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/> 
<msp:nocache />

<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />



<title><bean:message key="title.heading" /> - notificationsSearch.jsp</title>


<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- Javascript for emulated navigation --%>
<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>
<script type='text/javascript' src="/<msp:webapp/>js/casefileSearch.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/notification.js"></script>




<script language=javascript>


// called when the form is loading
function onloadForm()
{
	// 31 aug 2007 - mjt - when "search by name" is allowed,
  // uncomment the next 3 lines of code:
  /* ******************
	document.forms[0].ownerSearch.selectedIndex = 1;
  showHide( 'ownerNameSearch', SHOW_ITEM, 'row' ) ;
	showHide( 'rowID', HIDE_ITEM, 'row' ) ;
	******************  */
}

// the callback used by the onclick for the "search by userID or user name" drop-down
function ownerSearchCallback( elem )
{
  switch( elem.ownerSearch.options[ elem.ownerSearch.selectedIndex ].value )
  {
	  case 'id' :
    	showHide( 'rowID', SHOW_ITEM, 'row' ) ;
		  showHide( 'ownerNameSearch', HIDE_ITEM, 'row' ) ;
		  break;
	  case 'name' :
		  showHide( 'ownerNameSearch', SHOW_ITEM, 'row' ) ;
    	showHide( 'rowID', HIDE_ITEM, 'row' ) ;
		  break;
	}
}

/* 
 Converted to jquery. in notification.js file 
 function showStatusDropDowns(){
	document.getElementById("notificationStatusIdRow").className="hidden";
	document.getElementById("taskStatusIdRow").className="hidden";
	if(document.getElementById("notificationTypeId").value == "N"){
		document.getElementById("notificationStatusIdRow").className="visible";
	}	
	else if(document.getElementById("notificationTypeId").value == "T"){
		document.getElementById("taskStatusIdRow").className="visible";
	}
} */

function compareDate1GreaterEqualDate2(dateValAsString1, dateValAsString2)
{   
	
	if ((dateValAsString1 != null && dateValAsString1 != "") && (dateValAsString2 != null && dateValAsString2 != ""))
	{   
		var date1=new Date(dateValAsString1);
		var date2=new Date(dateValAsString2);
		date1.setHours(0);
		date1.setMinutes(0);
		date1.setSeconds(0);
		date2.setHours(0);
		date2.setMinutes(0);
		date2.setSeconds(0);
		if ((date1 >= date2))			
		{   
    		return true;
		}
		else{
			return false;
		}
	}
	else{
		return false;
	}
}


function validateSearch(el)
{
	clearAllValArrays();	
	
    customValRequired("notificationTypeId", "Notification Type is required");
    <logic:equal name="searchNotificationsForm" property="allowOnlyYours" value="false">
   		 customValRequired("ownerUserId", "Notification Owner User ID is required");
    </logic:equal>
	if( document.getElementById("endDateRange").value > ""){
  		addMMDDYYYYDateValidation("endDateRange", "End Date must be in the format MM/DD/YYYY");
  	}
  	if( document.getElementById("beginDateRange").value > ""){
   		addMMDDYYYYDateValidation("beginDateRange", "Begin Date must be in the format MM/DD/YYYY");
	}
	if(validateCustomStrutsBasedJS(el)){
		if(document.getElementById("notificationTypeId").value == "E"){
			alert("Currently the EMAIL notification type is not supported, please choose an alternate");
			return false;
		}
		if(document.getElementById("endDateRange").value > "" && document.getElementById("beginDateRange").value > ""){
			if(compareDate1GreaterEqualDate2(document.getElementById("endDateRange").value,document.getElementById("beginDateRange").value)){
				return true;
				
			}
			else{
				alert("Begin Date must be less than or equal to end date");
				document.getElementById("searchCasenote.casenoteBeginDateAsStr").focus();
				return false;
			}
		}
		return true;
	}
	else{
		return false;
	}
}
</script>
</head>

<%--BEGIN BODY and FORM TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displayNotificationSearch" target="content" focus="notificationTypeId">

<%-- 17 aug 2007 - mjt - help system ... currently commented out - i only guessed at the fully-qualified path/name
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/searchnotification/Search_Notification.htm#|162">
--%>       
<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
	<tr>
		<td align="center" class="header"><bean:message key="title.juvenileCasework"/> - <bean:message key="prompt.notification"/> <bean:message key="prompt.search"/></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<table width="98%">
	<tr>
   		<td>
			<ul>
      			<li>Enter any required fields and other search values, then select the Submit button to search.</li>
      			<li>At least one search field is required.</li>
      		</ul>
		</td>
 	</tr>
</table>
<%-- END INSTRUCTION TABLE --%>
<%-- BEGIN ERROR TABLE --%>
<table width="98%" border="0" align="center">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END ERROR TABLE --%>
<%-- End --%>

<%-- SEARCH TABLE BEGIN --%>
<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1"  class=borderTableBlue>
	<tr>
    	<td class="formDeLabel" nowrap><bean:message key="prompt.2.diamond" /><bean:message key="prompt.notification"/> <bean:message key="prompt.type"/></td>
    	<td class="formDe">
    		<html:select property="notificationTypeId" styleId="notificationTypeId">
    			<html:option key="select.generic" value="" />
  				<jims2:codetable codeTableName="<%=PDCodeTableConstants.TASK_NOTIFICATION_TYPES%>"/> 
	    	</html:select>
		</td>
	</tr>
	<tr id="notificationStatusIdRow" class="hidden"> 
	    <td class="formDeLabel" nowrap><bean:message key="prompt.notification"/> <bean:message key="prompt.status"/></td>
	    <td class="formDe">
	    	<html:select property="notificationStatusId">
		    		
	    		<%-- <html:option value="CLOSED" key="C"/>
	    		<html:option value="ERROR" key="E"/>
	    		<html:option value="OPEN" key="O"/> --%>
			   	<html:option value="PENDING" key="P"/>
		    </html:select>
	    </td>
	</tr>
 	 <tr id="taskStatusIdRow" class="hidden"> 
    	<td class="formDeLabel" nowrap><bean:message key="prompt.task"/> <bean:message key="prompt.status"/></td>
    	<td class="formDe">
    		<html:select property="taskStatusId">
			   	<jims2:codetable codeTableName="<%=PDCodeTableConstants.TASK_STATUS%>"/> 
	    	</html:select>
    	</td>
	</tr>
  <%--   Currently tasks/notifications have not category
  <tr>
    <td class="formDeLabel" nowrap width='1%'><bean:message key="prompt.notification"/> <bean:message key="prompt.category"/></td>
    <td class="formDe">
    	<html:select property="notificationCatId">
    		<html:option key="select.generic" value="" />
  			<option selected>Administrative</option>
	    </html:select>
	  </td>
  </tr>
 ---%>
	<tr>
    	<td class=formDeLabel width='1%' nowrap><bean:message key="prompt.notification"/> <bean:message key="prompt.dateRange"/></td>
    	<td class=formDe>
			<html:text property="beginDateRange" size="10" maxlength="10" styleId="beginDateRange"/>-<html:text property="endDateRange" size="10" maxlength="10" styleId="endDateRange"/>
		</td>
	</tr>

  <%-- 31 aug 2007 - mjt - when "search by name" is allowed,
	     completely remove the next <tr>...</tr> section:
	--%>
	<logic:equal name="searchNotificationsForm" property="allowOnlyYours" value="false">
		<tr id='rowID'>
   			<td class="formDeLabel" nowrap width='1%'><bean:message key="prompt.2.diamond" /><bean:message key="prompt.notification"/> <bean:message key="prompt.owner"/> <bean:message key="prompt.userId"/></td>
    		<td class="formDe"><html:text property="ownerUserId" styleId="ownerUserId" size="10" maxlength="10"/></td>
		</tr>
	</logic:equal>
  <%-- end remove <tr>...</tr> section --%>

  <%-- 31 aug 2007 - mjt - when "search by name" is allowed,
	     remove the "class" and "id" attributes for the 
       next <tr...> line 
  --%>
	<tr class='hidden' id='thisIsHiddenSinceWeAreNotOfferingToSearchByNameAtThisTime'>
    	<td colspan='2'>
			<div class='spacer'></div>
    	<%-- the user can choose to search by UserID or User Name --%>
		  	<table width="100%" border="0" cellpadding="2" cellspacing="0" class='borderTableBlue'>
				<tr class='detailHead'>
					<td width='1%' nowrap><bean:message key="prompt.searchBy"/> <bean:message key="prompt.notification"/> <bean:message key="prompt.owner"/></td>
				  	<td>
					  <%-- onchange callback function is used to hide/unhide rows that follow  --%>
					  	<select  name='ownerSearch' property="ownerSearch" onchange='ownerSearchCallback(this.form);'>
							<option value='id'><bean:message key="prompt.userId"/></option>
							<option value='name'><bean:message key="prompt.name"/></option>
						</select>
					</td>
				</tr>
      	<%-- default is by UserID --%>
        
      	<%-- this is hidden until the user selects to search by User Name --%>
				<tr class='hidden' id='ownerNameSearch'>
					<td colspan='2'>
						<table width="100%" border="0" cellpadding="2" cellspacing="1">
							<tr>
								<td class="formDeLabel" nowrap width='1%'><bean:message key="prompt.lastName"/></td>
								<td class="formDe"><input type=text property="lastName" size="20" maxlength="20"/></td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap width='1%'>+&nbsp;<bean:message key="prompt.firstName"/></td>
								<td class="formDe"><input type=text property="firstName" size="20" maxlength="20"/></td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap width='1%'>+&nbsp;<bean:message key="prompt.middleName"/></td>
								<td class="formDe"><input type=text property="middleName" size="20" maxlength="20"/><</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%-- SEARCH TABLE BEGIN --%>
<br>
<%-- BEGIN BUTTON TABLE --%>
<table border="0" width="100%">
	<tr>
		<td align="center">
			<html:submit property="submitAction"  styleId='submitBtn' onclick="return validateSearch(this.form)"><bean:message key="button.submit" /></html:submit>
			<html:submit property="submitAction"><bean:message key='button.refresh'/></html:submit>
			<html:submit property="submitAction"><bean:message key='button.cancel'/></html:submit>
		</td>
	</tr>
</table>
<%-- END BUTTON TABLE --%>

</html:form>
<div align=center><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>