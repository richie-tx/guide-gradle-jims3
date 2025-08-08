<!DOCTYPE HTML>

<%-- ----------------MODIFICATIONS -------------------------- --%>
<%-- March 2007 - daw - create jsp --%>
<%-- 10/19/07	LDeen - Revise length for juv num --%>
<%-- 05/27/08	LDeen - Defect #52100 Remove instruction for mm/yyyy --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="../../WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="../../WEB-INF/struts-nested.tld" prefix="nested" %>
<%@ taglib uri="../../WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="../../WEB-INF/jims2-presentation.tld" prefix="jims2" %>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDCodeTableConstants" %>
<%@ page import="naming.PDCalendarConstants" %>




<%--BEGIN HEADER TAG--%>
<head>
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1"/>
<msp:nocache />
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />

<!--JQUERY FRAMEWORK LOCAL REFERENCE-->
 <%@include file="../jQuery.fw"%> 
 
<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/casework.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/calendar.js"></script>


<title><bean:message key="title.heading"/>&nbsp;<bean:message key="title.juvenileCasework"/>&nbsp;- calendarSearch.jsp</title>

<script type="text/javascript">
var JPO_RADIO = 0 ;		
var CLM_RADIO = 1 ;		
var JUVENILE_RADIO = 2 ;
var nameSelected = false ;
var docketNameSearching = false ;
var servicesSelected = 0 ;

function validateAndGo(tForm)
{
	var errMsg = "" ;

	if(document.forms[0]['searchEvent.searchType'].value == "<%=PDCalendarConstants.JUVENILE_SEARCH%>" && 
   		  document.forms[0]['searchEvent.juvenileNum'].value.length < 1)
	{
		errMsg += "Juvenile number must be entered for an All Events search." ;
	}
	if(document.forms[0]['searchEvent.searchType'].value == "<%=PDCalendarConstants.INSTRUCTOR_SEARCH%>")
	{
		if( document.forms[0]['searchEvent.instructorName'] != null && 
			document.forms[0]['searchEvent.instructorName'].selectedIndex < 1 )
		  {
				errMsg += "An Instructor must be selected." ;
		  }else if(document.forms[0]['searchEvent.serviceProviderName'] != null &&
				   document.forms[0]['searchEvent.serviceProviderName'].selectedIndex < 1 ){
			  errMsg += "A Service Provider must be selected." ;
		  }	
	}

  if( document.forms[0]['searchEvent.searchType'].value == "<%=PDCalendarConstants.DOCKET_SEARCH%>" )
	{
    if( document.forms[0]['searchEvent.searchFirstName'].value.length > 0 && 
  	    document.forms[0]['searchEvent.searchLastName'].value.length < 1)
  	{
  		errMsg += "\nLast Name required if searching by First Name." ;
  	}
		else if( docketNameSearching  && !nameSelected )		 		
  	{
  		errMsg += "\nYou must select a Juvenile from the search results list." ;
  	}
	}
	
	if(document.forms[0]['searchEvent.searchType'].value == "<%=PDCalendarConstants.FUTURE_EVENTS_JUVENILE%>" )
	{
		if( document.forms[0]['searchEvent.juvenileNum'].value.length < 1 )
		{
			errMsg += "Juvenile number must be entered for Future events of Juvenile." ;
		}
		else
		{
			var numericRegExp = /^[0-9]*$/;
			var juv=document.forms[0]['searchEvent.juvenileNum'].value;			
			if (numericRegExp.test(juv,numericRegExp) == false)
		 	{
				errMsg +="Juvenile Number must be numeric.";
		 		document.forms[0]['searchEvent.juvenileNum'].focus();		 		
		 	}	
			
		}

	  	var dateStr = document.forms[0]['searchEvent.startDateStr'].value ; 	
	  	if( startDateLessThanToday( dateStr ) )
	  	{
				errMsg += "From Date can not be in the past." ;
	  	}
	}
	
	if(document.forms[0]['searchEvent.searchType'].value == "<%=PDCalendarConstants.FUTURE_EVENTS_SVC_PROVIDER%>" )
	{
		<logic:notEqual name="calendarEventListForm" property="calendarType" value="<%=PDCalendarConstants.CALENDAR_TYPE_PROVIDER%>">
		  if( document.forms[0]['searchEvent.serviceProviderName'].selectedIndex < 1 )
		  {
				errMsg += "A Service Provider must be selected." ;
		  }
		</logic:notEqual>
		
	  	var dateStr = document.forms[0]['searchEvent.startDateStr'].value ;  	
	  	if( startDateLessThanToday( dateStr ) )
	  	{
				errMsg += "From Date can not be in the past." ;
	  	}
	  	
	  	//JIMS200056905 : Service should not be required to search for future Service Provider events in View Calendar
	  	/*var checkboxes = document.getElementsByName("searchEvent.selectedServices");
	  	if( checkboxes != null )
	  	{
	  		var found = false ;
	  		for( var i = 0; !found && i < checkboxes.length; i++ )
	  		{
	  			if( checkboxes[ i ].checked )
	  			{
	  				found = true ;
	  			}
	  		}
	  		if( !found )
	  		{
					errMsg += "\nAt least one Service must be selected." ;
	  		}
	  	}*/
	}
			

	if (document.forms[0]['searchEvent.startDateStr'].value.length < 1 && 
	    document.forms[0]['searchEvent.endDateStr'].value.length < 1)
	{
		errMsg += "\nA date or date range is required." ;
	}
	else
	{
  	var dateStr = document.forms[0]['searchEvent.startDateStr'].value ;
	  if( ! isDate( dateStr, "MM/dd/yyyy" ) )
		  errMsg += "\nStart date is an invalid date or invalid date format (mm/dd/yyyy)." ;

    if( document.forms[0]['searchEvent.endDateStr'].value.length > 1 )
		{
    	dateStr = document.forms[0]['searchEvent.endDateStr'].value ;
  	  if( ! isDate( dateStr, "MM/dd/yyyy" ) )
  		  errMsg += "\nEnd date is an invalid date or invalid date format (mm/dd/yyyy)." ;
		} 		
	}

	if( errMsg != "" )
	{
	  alert( errMsg ) ;
  	return false;
	}

	spinner();
	return true;
}

function startDateLessThanToday( startDateAsString )
{
  // get today's date and set the time to just after midnight
	var today = new Date() ;
	today.setHours( 0 ) ;
	today.setMinutes( 0 ) ;
	today.setSeconds( 1 ) ;

  // get the entered start date and set the time to just after midnight
	var starDateStrings = startDateAsString.split( "/", 3 ) ; // parse string date
	var startDate = new Date( ) ; // default date to today/time
	// setFullYear( year, month, day)
	startDate.setFullYear( starDateStrings[2], starDateStrings[0], starDateStrings[1] );
	startDate.setMonth( (startDate.getMonth() -1) ) ; // month is zero-based
	startDate.setHours( 0 ) ;
	startDate.setMinutes( 0 ) ;
	startDate.setSeconds( 1 ) ;

	return( startDate < today ) ;
}

/* callback for dropdown for event type of either Calendar, Docket, or All
*/
function searchTypeCallback( tForm )
{	
  var searchType = document.forms[0]['searchEvent.searchType'].selectedIndex ;

  if( searchType > 0 ) // not "Please Select"
  {
  	var forwardURL = "/<msp:webapp/>displayViewCalendarSearchResults.do?submitAction=Change Event Type";
  
  	document.forms[0].action = forwardURL;
  	document.forms[0].submit();
  }
}

/* callback for dropdown for Service Provider selection
*/
function svcProviderSelectCallback( tForm )
{
  var offset = document.forms[0]['searchEvent.serviceProviderName'].selectedIndex ;
  var nameSel = document.forms[0]['searchEvent.serviceProviderName'].options[ offset ].text ;

  if( offset > 0 ) // not "Please Select"
  {
  	var forwardURL = "/<msp:webapp/>displayViewCalendarSearchResults.do?submitAction=Service Change";
  	document.forms[0].action = forwardURL;
  	document.forms[0].submit();
  }
}

/* callback for dropdown for Service Provider selection for instructors
*/
function svcProviderForInstructorSelect( tForm )
{
  var offset = document.forms[0]['searchEvent.serviceProviderName'].selectedIndex ;
  var nameSel = document.forms[0]['searchEvent.serviceProviderName'].options[ offset ].text ;

  if( offset > 0 ) // not "Please Select"
  {
  	var forwardURL = "/<msp:webapp/>displayViewCalendarSearchResults.do?submitAction=Instructor";
  	document.forms[0].action = forwardURL;
  	document.forms[0].submit();
  }
}



/* user has enterned name data (first/middle/last)
   and selected Find button. this is for a 'search type'
	 of 'calendar events'. there is another callback
	 when the 'search type' is 'docket events'
*/
function executeFind(tForm)
{
  var errMsg = "" ;

  if( tForm['searchEvent.nameSearchType'][JPO_RADIO].checked == false &&
      tForm['searchEvent.nameSearchType'][CLM_RADIO].checked == false &&
      tForm['searchEvent.nameSearchType'][JUVENILE_RADIO].checked == false )
  {
     errMsg += "Please select one of 'Name search is of type' radio buttons." ;
  }

  if( tForm['searchEvent.searchLastName'].value.length < 1 )
	{
	  errMsg += "\nLast name is required." ;
	}

  if( errMsg.length > 0 )
	{
	  alert( errMsg ) ;
    return( false ) ;
	}

<logic:equal name="calendarEventListForm" property="searchEvent.doNameSearch" value="true">
  <logic:notEmpty name="calendarEventListForm" property="nameSearchResults">
    clearTables() ;
  </logic:notEmpty>
</logic:equal>
	
	var forwardURL = "/<msp:webapp/>displayViewCalendarSearchResults.do?submitAction=Find" ;
	document.forms[0].action = forwardURL;
	document.forms[0].submit();
}

var lastNameTypeSelected = "unknown" ;
function nameTypeCallback(theRadio)
{
<logic:equal name="calendarEventListForm" property="searchEvent.doNameSearch" value="true">
  <logic:notEmpty name="calendarEventListForm" property="nameSearchResults">
    if( lastNameTypeSelected != theRadio.value )
  	{
      clearTables() ;
      document.forms[0]['searchEvent.doNameSearch'] = false ;
  	}
  </logic:notEmpty>
</logic:equal>

	lastNameTypeSelected = theRadio.value ;
}

function clearTables()
{
  show( 'otherParmsTable', 0, 'row' ) ;
  show( 'jpoIdTable', 0, 'row' ) ; 
  show( 'statusTable', 0, 'row' ) ; 
  show( 'dateTable', 0, 'row' ) ; 
  showHide( 'buttonTable', 0 ) ;
}

/* this function is executed when the user has 
   selected the "find name" button while in the 
	 context of a  'docket event" calendar type search. 
*/
function docketExecuteFind( tForm )
{

<logic:equal name="calendarEventListForm" property="searchEvent.doNameSearch" value="true">
  <logic:notEmpty name="calendarEventListForm" property="nameSearchResults">
    clearTables() ;
  </logic:notEmpty>
</logic:equal>
	
	var forwardURL = "/<msp:webapp/>displayViewCalendarSearchResults.do?submitAction=Find";
	document.forms[0].action = forwardURL;
	document.forms[0].submit();
	nameSelected = false ;
	docketNameSearching = true ;
}

<logic:equal name="calendarEventListForm" property="searchEvent.doNameSearch" value="true">
  <logic:notEmpty name="calendarEventListForm" property="nameSearchResults">
    function processRadio(theValue,locId)
    {
    	show( 'otherParmsTable', 1, 'row' ) ;
    	if( document.forms[0]['searchEvent.nameSearchType'][JPO_RADIO].checked == false )
    	{
        show( 'jpoIdTable', 1, 'row' ) ;
    	} 
      show( 'statusTable', 1, 'row' ) ; 
      show( 'dateTable', 1, 'row' ) ;  
      showHide( 'buttonTable', 1 ) ;
    }
  </logic:notEmpty>
</logic:equal>

function processDocketRadio(theValue)
{
	nameSelected = true ;
}

</script>
</head>


<body topmargin="0" leftmargin="0" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
<html:form action="/displayViewCalendarSearchResults" target="content">

<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|349">

<%-- BEGIN HEADING TABLE --%>
<table width='100%'>
  <tr>
    <td align="center" class="header"><bean:message key="title.juvenileCasework" /> - <bean:message key="prompt.calendar" /> <bean:message key="title.search" /></td>
  </tr>
</table>

<table width="100%">
	<tr>
		<td align="center" class="errorAlert"><html:errors></html:errors></td>
	</tr>
</table>
<%-- END HEADING TABLE --%>

<%-- BEGIN INSTRUCTION TABLE --%>
<div class='spacer'></div>
<table width="97%" align="center">
  <tr>
    <td>
      <ul>
        <li>Select a Search Type option for the Search By selection.</li>

        <logic:equal name="calendarEventListForm" property="searchEvent.showButtonSection" value="true">
          <li>Enter search criteria as required.</li>
          <li>Search criteria can be combined. Example: search by Juvenile Number AND Event Type AND Date.</li>
        </logic:equal> 
  
        <logic:equal name="calendarEventListForm" property="searchEvent.showNameSection" value="true">
   				<li>Select a "Name search is of type" and enter name criteria, then select the Find Name button.</li>
        </logic:equal> 
  
        <logic:equal name="calendarEventListForm" property="searchEvent.showDateSection" value="true">
          <li>For a single date, only enter the <b>From</b> date field - dates should be mm/dd/yyyy.</li>
        </logic:equal> 
      </ul>
    </td>
  </tr>
  <tr>
    <td class="required"><bean:message key="prompt.diamond" />&nbsp;Required fields.</td> 
  </tr> 
  <tr id='plusSignInstruction'>
		<td class="required">+ indicates Last Name is required to use this search field.</td>
  </tr>
</table>

<%-- Begin Pagination Header Tag --%>
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define>
<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">

<%-- End Pagination header stuff --%>

<nested:nest property="searchEvent">
<div class='spacer'></div>
<table align="center" width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td valign='top' align='center'>
        <table align="center" width='98%' cellpadding="2" cellspacing="0" class="borderTableBlue" align="center">
        	<tr>
        		<td class="detailHead"><bean:message key="prompt.calendar" /> <bean:message key="title.search" /></td>
        	</tr>
    		<tr>
    			<td>
    				<table align="center" width="100%" cellpadding="2" cellspacing="1"  class="borderTableBlue" align="center">
    					<tr>
    						<td class="detailHead" colspan='2'><bean:message key="prompt.search" />&nbsp;<bean:message key="prompt.type" /></td>
    					</tr>
    					<tr>
    						<td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.searchBy" /></td>
    						<td class="formDe">
    							<nested:select property="searchType" styleId="searchType" onchange="searchTypeCallback(this.form)">								
    								<html:option value=""><bean:message key="select.generic" /></html:option>
    								<html:option value='<%=PDCalendarConstants.CALENDAR_SEARCH%>'><bean:message key="prompt.calendarEvents" /></html:option>
    								<html:option value='<%=PDCalendarConstants.INSTRUCTOR_SEARCH%>'>Calendar Events for Instructor</html:option>
		       						<logic:notEqual name="calendarEventListForm" property="calendarType" value="<%=PDCalendarConstants.CALENDAR_TYPE_PROVIDER%>">
		    							<html:option value='<%=PDCalendarConstants.DOCKET_SEARCH%>'><bean:message key="prompt.docketEvents" /></html:option>
	    	   						</logic:notEqual>
    								<html:option value='<%=PDCalendarConstants.JUVENILE_SEARCH%>'><bean:message key="prompt.allEvents" /></html:option>
									<html:option value='<%=PDCalendarConstants.FUTURE_EVENTS_JUVENILE%>'><bean:message key="prompt.futureJuvEvents" /></html:option>
									<html:option value='<%=PDCalendarConstants.FUTURE_EVENTS_SVC_PROVIDER%>'><bean:message key="prompt.futureSPEvents" /></html:option>
		   						</nested:select>	
    						</td>
    					</tr>
    				</table>
    			</td>
    		</tr>
	 	 <logic:equal name="calendarEventListForm" property="searchEvent.showNameSection" value="true">
					<%-- this is first table to 'show' when the user selects 'calendar events' from the drop-down  --%>
      		<tr id='nameSearchTable'>
      			<td>
					<div class='spacer'></div>
      				<table width='100%' cellpadding="2" cellspacing="1" align="center">  			
	            		<tr>
	                  		<td class="detailHead" colspan='4'>
	    					  <bean:message key="prompt.JPO" /> <bean:message key="prompt.name" />,
	    					  <bean:message key="prompt.caseload" /> <bean:message key="prompt.manager" /> <bean:message key="prompt.name" />, or
	    				   	  <bean:message key="prompt.juvenile" /> <bean:message key="prompt.name" />
	    					</td>
	            		</tr>
	                   <tr>
	                  		<td class="formDeLabel" nowrap='nowrap'>Name search is of type</td>
	                  		<td colspan='3' class="formDe">
	      						<nested:radio property="nameSearchType" value="<%=PDCalendarConstants.JPO_NAME_SEARCH%>" onclick="nameTypeCallback(this);" >
	      							<bean:message key="prompt.JPO" /> <bean:message key="prompt.name" />&nbsp;
	      						</nested:radio>	
	      						<nested:radio property="nameSearchType" value="<%=PDCalendarConstants.CLM_NAME_SEARCH%>" onclick="nameTypeCallback(this);" >
	      							<bean:message key="prompt.caseload" /> <bean:message key="prompt.manager" /> <bean:message key="prompt.name" />
	      						</nested:radio>		
	      						<nested:radio property="nameSearchType" value="<%=PDCalendarConstants.JUVENILE_NAME_SEARCH%>" onclick="nameTypeCallback(this);" >
	      							<bean:message key="prompt.juvenile" /> <bean:message key="prompt.name" />                                                                                                                                                                                                          
	      						</nested:radio>		
	      					</td>
	                  </tr>
	                  <tr>
	                    <td class="formDeLabel" nowrap='nowrap' ><bean:message key="prompt.diamond" />&nbsp;<bean:message key="prompt.lastName" /></td>
	                    <td class="formDe" colspan='3'><nested:text property="searchLastName" size="25" maxlength="75" /></td>
	                  </tr>
	                  <tr>
	                    <td class="formDeLabel" nowrap='nowrap'>+<bean:message key="prompt.firstName" /></td>
	                    <td class="formDe" colspan='3'><nested:text property="searchFirstName" size="20" maxlength="50" /></td>
	                  </tr>
	                  <tr>
	                    <td class="formDeLabel" nowrap='nowrap' width='1%'>+<bean:message key="prompt.middleName" /></td>
	                    <td class="formDe" colspan='3'><nested:text property="searchMiddleName" size="20" maxlength="50" /></td>
	                  </tr>
	                  <tr>
	                    <td class="formDeLabel"></td>
	                    <td class="formDe" colspan='3'>
							<input type='button' value='Find' onclick="executeFind(this.form);">
	    					<html:submit property="submitAction"><bean:message key="button.refresh"></bean:message></html:submit>
	    				</td>
	                  </tr>
      				</table>
      			</td>
    		</tr>
		 </logic:equal>
  		 <logic:equal name="calendarEventListForm" property="searchEvent.doNameSearch" value="true">
          	<tr>
          		<td>
       		      <table width='100%' cellpadding="2" cellspacing="1" class="borderTableBlue" align="center">
          			<logic:notEmpty name="calendarEventListForm" property="nameSearchResults">
	                  <tr>
	                    <td class="detailHead" colspan='7'>
	                    	<bean:message key="prompt.searchResults" /> 
							<bean:size id="collSize" name="calendarEventListForm" property="nameSearchResults" />
							<bean:write name="collSize"/>
	                    </td>
	                  </tr>
          			</logic:notEmpty>									
        			<logic:empty name="calendarEventListForm" property="nameSearchResults">
	        		  <tr bgcolor="#cccccc">
						<td colspan='6' class="subHead">No name(s) found.</td>
					  </tr>
        			</logic:empty>
					<%-- the column data will change whether the name search is of type "Juvenile" or "JPO/CLM" we'll do officer type first  --%>
					<logic:notEqual name="calendarEventListForm" property="searchEvent.nameSearchType" value="<%=PDCalendarConstants.JUVENILE_NAME_SEARCH%>">
          			<logic:notEmpty name="calendarEventListForm" property="nameSearchResults">
					<%-- static header title row --%>
                       <tr bgcolor='#cccccc' class='subhead'>
							<td width='1%'> </td> <%-- holder for radio button column --%>
							<td>
								<bean:message key="prompt.officerName" />
								<jims2:sortResults beanName="calendarEventListForm" results="nameSearchResults" primaryPropSort="lastName" primarySortType="STRING" defaultSort="true" secondPropSort="firstName" secondarySortType="STRING" defaultSortOrder="ASC" sortId="1" />
							</td>
							<td>
								<bean:message key="prompt.otherIdNumber" />
								<jims2:sortResults beanName="calendarEventListForm" results="nameSearchResults" primaryPropSort="otherIdNum" primarySortType="STRING" defaultSortOrder="ASC" sortId="2" />
							</td>
							<td>
								<bean:message key="prompt.badge#" />
								<jims2:sortResults beanName="calendarEventListForm" results="nameSearchResults" primaryPropSort="badgeNum" primarySortType="STRING" defaultSortOrder="ASC" sortId="3" />
							</td>
							<td>
								<bean:message key="prompt.userId" />
								<jims2:sortResults beanName="calendarEventListForm" results="nameSearchResults" primaryPropSort="userId" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" />
							</td>
							<td>
								<bean:message key="prompt.status" />
								<jims2:sortResults beanName="calendarEventListForm" results="nameSearchResults" primaryPropSort="status" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />
							</td>
						</tr>
						<logic:iterate id="namesIndex" name="calendarEventListForm" property="nameSearchResults" indexId="indexer"> 
							<pg:item>
								<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
									<td><input type="radio" id='<bean:write name="namesIndex" property="officerId"/>' name="officerId" value='<bean:write name="namesIndex" property="officerId"/>'  onclick="processRadio(this.value,<bean:write name="namesIndex" property="officerId"/>)" /></td>
									<td><bean:write name="namesIndex" property="lastName" />, <bean:write name="namesIndex" property="firstName" /> <bean:write name="namesIndex" property="middleName" /></td>
									<td><bean:write name="namesIndex" property="otherIdNum"/></td>
									<td><bean:write name="namesIndex" property="badgeNum"/></td>
									<td><bean:write name="namesIndex" property="userId"/></td>  <%-- badge number --%>
									<td><bean:write name="namesIndex" property="status"/></td>                  					
								</tr>
							</pg:item>
						</logic:iterate>
						<tr>
							<td>&nbsp;</td>												
							<td align="right" colspan=2>
								<%-- Begin Pagination navigation Row--%>
								<table>
									<tr>
										<td width="200px">
											<pg:index>
												<tiles:insert page="/jsp/jimsPagination.jsp" flush="false">
													<tiles:put name="pagerUniqueName" value="pagerSearch"/>
													<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
												</tiles:insert>
											</pg:index>
										</td>
									</tr>
								</table>																
								<%-- End Pagination navigation Row--%>
							</td>	
							<td>&nbsp;</td>							
							<td>&nbsp;</td>
							<td>&nbsp;</td>
					  </tr>
				</logic:notEmpty> 
  			    </logic:notEqual>
			  <%-- the column data will change whether the name search is of type "Juvenile" or "JPO/CLM" we'll do Juvenile here  --%>
				<logic:equal name="calendarEventListForm" property="searchEvent.nameSearchType" value="<%=PDCalendarConstants.JUVENILE_NAME_SEARCH%>">
          		<logic:notEmpty name="calendarEventListForm" property="nameSearchResults">
				  <%-- static header title row --%>
                  <tr bgcolor='#cccccc' class='subhead'>
                     <td width='1%'> </td> <%-- holder for radio button column --%>
                     <td>
                         <bean:message key="prompt.juvenile#" />
                         <jims2:sortResults beanName="calendarEventListForm" results="nameSearchResults" primaryPropSort="juvenileNum" defaultSort="true" primarySortType="STRING" defaultSortOrder="ASC" sortId="1" />
                     </td>
                     <td>
                        <bean:message key="prompt.juvenileName" />
                        <jims2:sortResults beanName="calendarEventListForm" results="nameSearchResults" primaryPropSort="lastName" primarySortType="STRING" secondPropSort="firstName" secondarySortType="STRING" defaultSortOrder="ASC" sortId="2" />
                     </td>
                     <td>
						<bean:message key="prompt.race" />
                        <jims2:sortResults beanName="calendarEventListForm" results="nameSearchResults" primaryPropSort="race" primarySortType="STRING"  defaultSortOrder="ASC" sortId="3" />
					 </td>
                     <td>
      					<bean:message key="prompt.sex" />
                        <jims2:sortResults beanName="calendarEventListForm" results="nameSearchResults" primaryPropSort="sex" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" />
                     </td>
                     <td>
      					<bean:message key="prompt.dob" />
                        <jims2:sortResults beanName="calendarEventListForm" results="nameSearchResults" primaryPropSort="dateOfBirth" primarySortType="DATE" defaultSortOrder="ASC" sortId="5" />
                     </td>
                     <td>
      				   <bean:message key="prompt.master" /> <bean:message key="prompt.status" />
                       <jims2:sortResults beanName="calendarEventListForm" results="nameSearchResults" primaryPropSort="status" primarySortType="STRING" defaultSortOrder="ASC" sortId="6" />
                     </td>
                   </tr>
         		   <logic:iterate id="namesIndex" name="calendarEventListForm" property="nameSearchResults" indexId="indexer"> 
                        <pg:item>
                  			<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">		 	
	                            <td>
	                            	<input type="radio" id='<bean:write name="namesIndex" property="juvenileNum"/>' name="juvenileNum" value='<bean:write name="namesIndex" property="juvenileNum"/>'  onclick="processRadio(this.value,<bean:write name="namesIndex" property="juvenileNum"/>)" />
	                            </td>
	                	    	<td>
	                	    		<bean:write name="namesIndex" property="juvenileNum" />
	                	    	</td>
	                            <td>
	                             	<bean:write name="namesIndex" property="lastName" />, <bean:write name="namesIndex" property="firstName" /> <bean:write name="namesIndex" property="middleName" />
	                            </td>
	                  			<td><bean:write name="namesIndex" property="race" /></td>
	                  			<td><bean:write name="namesIndex" property="sex" /></td>
	                  			<td><bean:write name="namesIndex" property="dateOfBirth" formatKey="date.format.mmddyyyy" /></td>
	                  			<td><bean:write name="namesIndex" property="status" /></td>
                  			</tr>
                		</pg:item>
              	</logic:iterate> 
                 <%-- Begin Pagination navigation Row--%>
               	<tr>
              		<td colspan='7'>
                		<table align="center">
                      			<tr>
                        			<td>
                        				<pg:index>
                        					<tiles:insert page="/jsp/jimsPagination.jsp"  flush="false">
                        						<tiles:put name="pagerUniqueName" value="pagerSearch2"/>
                        						<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
                        					</tiles:insert>
                        			 	</pg:index>
                        		  </td>
                      		  </tr>
                    	</table>
            		</td>
               </tr>
            <%-- End Pagination navigation Row--%>
		      </logic:notEmpty> 
		      </logic:equal>
       		</table>
		</td>
	</tr>
	</logic:equal>
	<logic:equal name="calendarEventListForm" property="searchEvent.showJuvNumSection" value="true">
      			<tr id='juvNumTable'>
          			<td>
		  	        	<table width='100%' cellpadding="2" cellspacing="1" align="center">
		                  <tr>
		                    <td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.diamond" />&nbsp;<bean:message key="prompt.juvenileNumber" /></td>
		                    <td class="formDe" colspan='3'><nested:text property="juvenileNum" size="10" maxlength="8"/></td>
		                  </tr>
		    			</table> 	
    				</td>			
     		    </tr>
		  </logic:equal>
									
		  <logic:equal name="calendarEventListForm" property="searchEvent.showJuvNameSection" value="true">
          	<tr id='juvNameTable'>
          		<td>
	        		<table width='100%' cellpadding="2" cellspacing="1" align="center">
	                  <tr>
	                    <td bgcolor='#ffffff' colspan='4'><img src="/<msp:webapp/>images/spacer.gif" width='5'></td>
	                  </tr>
	                  <tr>
	                    <td class="formDeLabel" nowrap='nowrap'><bean:message key="prompt.juvenileLastName" /></td>
	                    <td class="formDe" colspan='3'><nested:text property="searchLastName" size="25" maxlength="75"/></td>
											<%-- juvenileLastName --%>
	                  </tr>
	                  <tr>
	                    <td class="formDeLabel" nowrap='nowrap'>+<bean:message key="prompt.juvenileFirstName" /></td>
	                    <td class="formDe" colspan='3'><nested:text  property="searchFirstName" size="20" maxlength="50"/></td>
											<%-- juvenileFirstName --%>
	                  </tr>
	                  <tr>
	                    <td class="formDeLabel" nowrap='nowrap' width='1%'>+<bean:message key="prompt.juvenileMiddleName" /></td>
	                    <td class="formDe" colspan='3'><nested:text property="searchMiddleName" size="20" maxlength="50"/></td>
											<%-- juvenileMiddleName --%>
	                  </tr>
	
	                  <tr>
	                    <td class="formDeLabel"></td>
	                    <td class="formDe" colspan='3'>
							<input type='button' value='Find Name' onclick="docketExecuteFind(this.form);">
	    				 	<html:submit property="submitAction"><bean:message key="button.refresh"></bean:message></html:submit>
	    				</td>
	               	  </tr>
					</table>
      				<logic:empty name="calendarEventListForm" property="nameSearchResults">
         		      <table width='100%' id='docketEventsNoResultsTable' cellpadding="2" cellspacing="1" class="hidden" align="center">
                   		 <tr>
                    	  <td class="detailHead" colspan='7'><bean:message key="prompt.searchResults" /></td>
                    	</tr>
        				<tr bgcolor="#cccccc">
    						<td colspan='6' class="subHead">No name(s) found.</td>
    					</tr>
         		      </table> 
      				</logic:empty>

       					<logic:notEmpty name="calendarEventListForm" property="nameSearchResults">
         		      <table width='100%' cellpadding="2" cellspacing="1" class="borderTableBlue" align="center">
                    <tr>
                      <td class="detailHead" colspan='7'>
	                    	<bean:message key="prompt.searchResults" /> 
												<bean:size id="collSize" name="calendarEventListForm" property="nameSearchResults" />
												<bean:write name="collSize"/>
                      </td>
                    </tr>

                    <tr bgcolor='#cccccc' class='subhead'>
                      <td width='1%'></td> <%-- holder for radio button column --%>
                      <td>
                        <bean:message key="prompt.juvenile#" />
                        <jims2:sortResults beanName="calendarEventListForm" results="nameSearchResults" primaryPropSort="juvenileNum" primarySortType="STRING"  defaultSort="true" defaultSortOrder="ASC" sortId="1" />
                      </td>
                      <td>
                        <bean:message key="prompt.juvenileName" />
                        <jims2:sortResults beanName="calendarEventListForm" results="nameSearchResults" primaryPropSort="lastName" primarySortType="STRING" secondPropSort="firstName" secondarySortType="STRING" defaultSortOrder="ASC" sortId="2" />
                      </td>
                      <td>
    					<bean:message key="prompt.race" />
                        <jims2:sortResults beanName="calendarEventListForm" results="nameSearchResults" primaryPropSort="race" primarySortType="STRING"  defaultSortOrder="ASC" sortId="3" />
  					 </td>
                      <td>
    					<bean:message key="prompt.sex" />
                        <jims2:sortResults beanName="calendarEventListForm" results="nameSearchResults" primaryPropSort="sex" primarySortType="STRING" defaultSortOrder="ASC" sortId="4" />
                      </td>
                      <td>
    					<bean:message key="prompt.dob" />
                        <jims2:sortResults beanName="calendarEventListForm" results="nameSearchResults" primaryPropSort="dateOfBirth" primarySortType="STRING" defaultSortOrder="ASC" sortId="5" />
                      </td>
                      <td>
    					<bean:message key="prompt.master" /> <bean:message key="prompt.status" />
                        <jims2:sortResults beanName="calendarEventListForm" results="nameSearchResults" primaryPropSort="status" primarySortType="STRING" defaultSortOrder="ASC" sortId="6" />
                      </td>
                    </tr>
						<script type="text/javascript">docketNameSearching = true ;</script> 
              			<logic:iterate id="namesIndex" name="calendarEventListForm" property="nameSearchResults" indexId="indexer"> 
                     	 <pg:item>
                				<tr class="<%out.print( (indexer.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">		 	
                        			  <td width='1%'><input type="radio" name="juvenileNum" value='<bean:write name="namesIndex" property="juvenileNum"/>' onclick="processDocketRadio(this.value)" /></td>
              	    				<td><bean:write name="namesIndex" property="juvenileNum" /></td>
                         			 <td><bean:write name="namesIndex" property="lastName" />, <bean:write name="namesIndex" property="firstName" /> <bean:write name="namesIndex" property="middleName" /></td>
                					<td><bean:write name="namesIndex" property="race" /></td>
                					<td><bean:write name="namesIndex" property="sex" /></td>
                					<td><bean:write name="namesIndex" property="dateOfBirth" formatKey="date.format.mmddyyyy" /></td>
                					<td><bean:write name="namesIndex" property="status" /></td>
                				</tr>
              				</pg:item>
            			  </logic:iterate>
						</table>
  							
             			<%-- Begin Pagination navigation Row--%>
             			<table align="center">
	               			<tr>
	                 			<td>
	                 				<pg:index>
	                 					<tiles:insert page="/jsp/jimsPagination.jsp"  flush="false">
	                 						<tiles:put name="pagerUniqueName" value="pagerSearch3"/>
	                 						<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
	                 					</tiles:insert>
	                 			 	</pg:index>
	                 		  </td>
	               		  </tr>
             		  </table>
                 	<%-- End Pagination navigation Row--%>
		   					</logic:notEmpty> 
   						</td>
   				  </tr>
			  </logic:equal>
			
			  <logic:equal name="calendarEventListForm" property="searchEvent.showSPSection" value="true">
      			<tr id='otherParmsTable' class='hidden'>
      				<td>
							  <div class='spacer'></div>
      					<table width='100%' cellpadding="2" cellspacing="1" align="center">  
	    	        		<tr id='serviceProviderRow'>
	                    		<td class="detailHead" nowrap='nowrap' colspan='4'><bean:message key="prompt.serviceProvider" /></td>
							</tr>
										
	    	        		<tr id='serviceProviderRow'>
	                    		<td class="formDeLabel" nowrap='nowrap' width='1%'><bean:message key="prompt.serviceProvider" /></td>
	        						<logic:notEqual name="calendarEventListForm" property="calendarType" value="<%=PDCalendarConstants.CALENDAR_TYPE_PROVIDER%>">
	        							<td class="formDe" colspan='3'>
	        								<nested:select property="serviceProviderName">
	        										<html:option value=""><bean:message key="select.generic" /></html:option>
	        										<nested:optionsCollection property="serviceProviderList" value="juvServProviderName" label="juvServProviderName" />					
	        								</nested:select>
	        							</td>
	        						</logic:notEqual>
	        						<logic:equal name="calendarEventListForm" property="calendarType" value="<%=PDCalendarConstants.CALENDAR_TYPE_PROVIDER%>">
	        							<td class="formDe" colspan='3'><nested:write property="serviceProviderName"/></td>
	        						</logic:equal>                  
	  		        		</tr>
	  		        		<tr id='locationUnitRow'>
			                    <td class="formDeLabel" nowrap='nowrap' width='1%'><bean:message key="prompt.locationUnit" /></td>
	          					<td class="formDe" colspan="3">
	  										<nested:select property="juvLocUnitId">
	  											<html:option value=""><bean:message key="select.generic" /></html:option>
	  											<nested:optionsCollection property="juvUnitList" value="juvLocationUnitId" label="locationUnitName" />					
	  										</nested:select>
	          					</td>								
	  		        		</tr>
	        	          <tr>
	            	        <td class="formDeLabel" width="1%" nowrap='nowrap'><bean:message key="prompt.eventType" /></td>
	    				      <td class="formDe" colspan='3'>
	        					<nested:select property="eventTypeId">
	          						<html:option value=""><bean:message key="select.generic" /></html:option>
	        						<nested:optionsCollection property="serviceTypeList" value="serviceTypeId" label="description" />					
	        					</nested:select>
	                	    </td>   	
	      				</tr>
      				 </table>
    			</td>
    		</tr>
		</logic:equal>

			  <logic:equal name="calendarEventListForm" property="searchEvent.showFutureSPSection" value="true">
      			<tr id='futureSPSection'>
      				<td>
							  <div class='spacer'></div>
      					<table width='100%' cellpadding="2" cellspacing="1" align="center">  
    	        		<tr>
                    <td class="detailHead" nowrap='nowrap' colspan='4'><bean:message key="prompt.serviceProvider" /></td>
									</tr>
									
    	        		<tr>
                    <td class="formDeLabel" nowrap='nowrap' width='1%'><bean:message key="prompt.diamond" /> <bean:message key="prompt.serviceProvider" /></td>
										
        						<logic:notEqual name="calendarEventListForm" property="calendarType" value="<%=PDCalendarConstants.CALENDAR_TYPE_PROVIDER%>">
        							<td class="formDe" colspan='3'>
        								<nested:select property="serviceProviderName" onchange="svcProviderSelectCallback(this.form)">
        										<html:option value=""><bean:message key="select.generic" /></html:option>
        										<nested:optionsCollection property="serviceProviderList" value="juvServProviderName" label="juvServProviderName" />					
        								</nested:select>
        							</td>
        						</logic:notEqual>
        						<logic:equal name="calendarEventListForm" property="calendarType" value="<%=PDCalendarConstants.CALENDAR_TYPE_PROVIDER%>">
        							<td class="formDe" colspan='3'><nested:write property="serviceProviderName"/></td>
        						</logic:equal>                  
  		        		</tr>
      					</table>
    				  </td>
    			  </tr>
				</logic:equal>
				
				<logic:equal name="calendarEventListForm" property="searchEvent.showSPForInstructorSelectionSection" value="true">
      			<tr id='instructorSection'>
      				<td>
						<div class='spacer'></div>
      					<table width='100%' cellpadding="2" cellspacing="1" align="center">  
    	        		<tr>
                  		  <td class="detailHead" nowrap='nowrap' colspan='4'><bean:message key="prompt.serviceProvider" /></td>
						</tr>
    	        		<tr>
        						<logic:notEqual name="calendarEventListForm" property="calendarType" value="<%=PDCalendarConstants.CALENDAR_TYPE_PROVIDER%>">
        						 <td class="formDeLabel" nowrap='nowrap' width='1%'><bean:message key="prompt.diamond" /> <bean:message key="prompt.serviceProvider" /></td>
        						
        							<td class="formDe" colspan='3'>
        								<nested:select property="serviceProviderName" onchange="svcProviderForInstructorSelect(this.form)">
        										<html:option value=""><bean:message key="select.generic" /></html:option>
        										<nested:optionsCollection property="serviceProviderList" value="juvServProviderName" label="juvServProviderName" />					
        								</nested:select>
        							</td>
        						
        							<logic:equal name="calendarEventListForm" property="searchEvent.showInstructorSelectionSection" value="true">
        							<tr>
        							
		        						<td class="formDeLabel" nowrap='nowrap' width='1%'><bean:message key="prompt.diamond" /> <bean:message key="prompt.instructorName" /></td>  
		        						<logic:notEmpty name="calendarEventListForm" property="searchEvent.instructorList">
										<td class="formDe" colspan='3'>
		        								<nested:select property="instructorName">
		        										<html:option value=""><bean:message key="select.generic" /></html:option>
		        										<nested:optionsCollection property="instructorList" value="contactName.formattedName" label="contactName" />					
		        								</nested:select>
		        							</td>
		        						</logic:notEmpty>
		        					</tr>
	           						</logic:equal>
        						</logic:notEqual>
        						<logic:equal name="calendarEventListForm" property="calendarType" value="<%=PDCalendarConstants.CALENDAR_TYPE_PROVIDER%>">
	        						 <td class="formDeLabel" nowrap='nowrap' width='1%'> <bean:message key="prompt.serviceProvider" /></td>
	        						 <td class="formDe" colspan='3'><nested:write property="serviceProviderName"/></td>
	        						 <tr>
			        					<td class="formDeLabel" nowrap='nowrap' width='1%'><bean:message key="prompt.diamond" /> <bean:message key="prompt.instructorName" /></td>  
			        					<logic:notEmpty name="calendarEventListForm" property="searchEvent.instructorList">
											<td class="formDe" colspan='3'>
					        					<nested:select property="instructorName" >
					        						<html:option value=""><bean:message key="select.generic" /></html:option>
					        						<nested:optionsCollection property="instructorList" value="contactName.formattedName" label="contactName" />					
					        					</nested:select>
				        				 	</td>
			        					</logic:notEmpty>
		           					</tr>
        						</logic:equal>  
  		        			</tr>
      					</table>
    				  </td>
    			  </tr>
				</logic:equal>

			  <logic:equal name="calendarEventListForm" property="searchEvent.showProgramSelectionSection" value="true">
				<tr>
				  <td>
	      			<table width='100%' cellpadding="2" cellspacing="1" align="center">
		      			<tr>
		                	<td class="detailHead" nowrap='nowrap'  width='1%' colspan='4'>Program / Service Name</td>
		      			</tr>
		            			  <tr>
									<td colspan='4' align="left">
										<table align="center" width='100%' border='0' cellpadding="1" cellspacing="1" >
										
											<%-- for each program, loop through and show each Service Name (and associated checkbox) --%>
											<logic:iterate indexId="programIndex" id="program" name="calendarEventListForm" property="serviceProvider.programs">
												<logic:notEmpty name="program" property="services">
													<tr class='selectedRow'>
														<td nowrap='nowrap'  width='1%' colspan="6"><a href="javascript:showHideMulti(
																'list1ParentExpandProg<nested:write name="program" property="providerProgramId"/>', 
																'ProgramID<nested:write name="program" property="providerProgramId"/>-', 1, '/<msp:webapp/>');" align="left">
															<img src="/<msp:webapp/>images/expand.gif" 
																	name="list1ParentExpandProg<nested:write name='program' property='providerProgramId' />" border='0'></a>
																	&nbsp;Program: <nested:write name="program" property="programName" />
														</td>	
													</tr>

													<%int serviceResponseEventsIndex = 0;%><%-- so we can get a zero appended --%>
													<tr id='ProgramID<nested:write name="program" property="providerProgramId"/>-<%=serviceResponseEventsIndex%>' class='hidden'>
														<td align="left">
															<table width='100%' border='0' cellpadding="0" cellspacing="0" align="center">
																<tr>																								
																	<td class="detailHead" width='1%' colspan='2' align="left">&nbsp;&nbsp;&nbsp;&nbsp;Service Name</td>
																</tr>
	
																<logic:iterate indexId="svcIndex" id="service" name="program" property="services">									
																	<tr class="<%out.print( (svcIndex.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>" height="100%">
																		<td width="1%">
																			<html:multibox property="searchEvent.selectedServices"><bean:write name="service" property="serviceId" /></html:multibox>
																		</td>
																		<td><bean:write name="service" property="serviceName"/></td>
																	</tr>
																</logic:iterate>
															</table>
														</td>
													</tr>
												</logic:notEmpty>
											</logic:iterate>										
										</table>
									</td>
	          				    </tr>
							</table>
						</td>
					</tr>
			  </logic:equal>

			<logic:equal name="calendarEventListForm" property="searchEvent.showEventTypeSection" value="true">
            <tr id='eventTypeRow'>
              <td>
             	<table width='100%' align="center">
                 	 <td class="formDeLabel" width="1%" style="white-space:nowrap;"><bean:message key="prompt.eventType" /></td>
      			      <td class="formDe">
     					<nested:select property="eventTypeId">
       						<html:option value=""><bean:message key="select.generic" /></html:option>
      						<nested:optionsCollection property="serviceTypeList" value="serviceTypeId" label="description" />					
      					</nested:select>
                  	 </td>   	
              	 </table> 	
               </td>			
			</tr>
			</logic:equal>
			  			
			<logic:equal name="calendarEventListForm" property="searchEvent.showJPOSection" value="true">
            <tr id='jpoIdTable' class='hidden'>
              <td>
             	 <table width='100%' align="center">
                  <tr>
                    <td class="formDeLabel" width="1%" style="white-space:nowrap;"><bean:message key="prompt.JPO" />&nbsp;<bean:message key="prompt.userId" /></td>
                    <td class="formDe" colspan='3'><nested:text property="jpoUserId" size="5" maxlength="5"/></td>
                  </tr>
                </table> 	
              </td>			
            </tr>			
			</logic:equal>
							
			  <logic:equal name="calendarEventListForm" property="searchEvent.showStatusSection" value="true">
      			<tr id='statusTable' class='hidden'>
      				<td>
						<div class='spacer'></div>
      					<table width='100%' cellpadding="2" cellspacing="1" align="center">  
              			 <tr>
      							<td class="detailHead" style="white-space:nowrap;" width='1%' colspan='4'><bean:message key="prompt.searchBy" /> <bean:message key="prompt.status" /></td>
              			</tr>
              		    <tr>
      					 	<td class="formDeLabel" style="white-space:nowrap;" width='1%'><bean:message key="prompt.event" />&nbsp;<bean:message key="prompt.status" /></td>
              				<td class="formDe" colspan="3">
	                      		<nested:select property="eventStatusCd">
	          						<html:option value=""><bean:message key="select.generic" /></html:option>
	            					<jims2:codetable codeTableName="<%=PDCodeTableConstants.SERVEVENT_STATUS%>"/>
	          					</nested:select>
              				</td>								
              			</tr>	
      				</table>	
      			</td>	
      		  </tr>
			</logic:equal>
			  <logic:equal name="calendarEventListForm" property="searchEvent.showDateSection" value="true">
      		  	<tr id='dateTable'>
      				<td>
					   <div class='spacer'></div>
      					<table width='100%' cellpadding="2" cellspacing="1" align="center"> 	
           				  <tr>
      							<td class="detailHead" style="white-space:nowrap;" width='1%' colspan='4'><bean:message key="prompt.date" /> or <bean:message key="prompt.dateRange" /></td>
        				  </tr>
        				  <tr>
        					  <td class="formDeLabel" style="white-space:nowrap;" width='1%'><bean:message key="prompt.diamond" /> <bean:message key="prompt.from" /></td>
        					  <td valign='top' class="formDe" width='5%'>
          						<nested:text styleId="startDateStr" property="startDateStr" styleId="startDateStr" size="10" maxlength="10"/>
        					  </td> 
        					  <td class="formDeLabel">To</td>
        					  <td valign='top' class="formDe" width='95%'> 
        						  <nested:text styleId="endDateStr" property="endDateStr" styleId="endDateStr" size="10" maxlength="10"/>
        					  </td>
        				  </tr>					
      				  </table>					
      			  </td>	
    			</tr>
			 </logic:equal>
       </table>
			<%-- END DETAIL TABLE --%>
    </td>
  </tr>
</table>
</nested:nest>

</pg:pager>

<logic:equal name="calendarEventListForm" property="searchEvent.showButtonSection" value="true">
  <%-- BEGIN BUTTON TABLE --%>
  <div class='spacer'></div>
  <span id='buttonTable'>
	  <table width="100%" align="center">  
	    <tr>
	      <td align="center">
	        <html:submit property="submitAction" onclick="return validateAndGo(this.form);"><bean:message key="button.submit"></bean:message></html:submit>
	        <html:submit property="submitAction"><bean:message key="button.refresh"></bean:message></html:submit>	
	        <html:submit property="submitAction"><bean:message key="button.cancel"></bean:message></html:submit>
	      </td>
	    </tr>
	  </table>
  </span>
  <%-- END BUTTON TABLE --%>
</logic:equal>

<logic:equal name="calendarEventListForm" property="searchEvent.doNameSearch" value="true">
  <logic:notEmpty name="calendarEventListForm" property="nameSearchResults">
    <script type="text/javascript">clearTables();</script> 
  </logic:notEmpty>
</logic:equal>

<logic:equal name="calendarEventListForm" property="searchEvent.searchType" value="<%=PDCalendarConstants.JUVENILE_SEARCH%>">
  <script type="text/javascript">
    show( 'otherParmsTable', 1, 'row' ) ;
    show( 'statusTable', 1, 'row' ) ; 
    show( 'dateTable', 1, 'row' ) ; 
	</script> 
</logic:equal>

<logic:equal name="calendarEventListForm" property="searchEvent.searchType" value="<%=PDCalendarConstants.DOCKET_SEARCH%>">
  <script type="text/javascript">
    show( 'juvNameTable', 1, 'row' ) ;
	show( 'jpoIdTable', 1, 'row' ) ;
    show( 'dateTable', 1, 'row' ) ; 
	</script> 
</logic:equal>


<logic:equal name="calendarEventListForm" property="searchEvent.goneToSearchResultsPage" value="true">
	<logic:notEmpty name="calendarEventListForm" property="nameSearchResults">
		<logic:notEqual name="calendarEventListForm" property="searchEvent.nameSearchType" value="<%=PDCalendarConstants.JUVENILE_NAME_SEARCH%>">
			<script type="text/javascript">
				var radios = document.getElementsByName('officerId'); 
				var selectedVal = "<bean:write name="calendarEventListForm" property="officerId" />" ;
			</script>
		</logic:notEqual>
		<logic:equal name="calendarEventListForm" property="searchEvent.nameSearchType" value="<%=PDCalendarConstants.JUVENILE_NAME_SEARCH%>">
			<script type="text/javascript">
				var radios = document.getElementsByName('juvenileNum'); 
				var selectedVal = "<bean:write name="calendarEventListForm" property="juvenileNum" />" ;
			</script>
		</logic:equal>
		
		<script type="text/javascript">
			for( var i = 0; i < radios.length; i++ ) 
			{
				var input = radios[ i ];    
       			if( input.type == 'radio' && input.value == selectedVal )
				{
					input.checked = "checked" ;
        		}
      		}
		</script>
	</logic:notEmpty>
	<script type="text/javascript">
		show( 'otherParmsTable', 1, 'row' ) ;
		if( document.forms[0]['searchEvent.nameSearchType'] != null && 
			document.forms[0]['searchEvent.nameSearchType'][JPO_RADIO].checked == false )
		{
		   show( 'jpoIdTable', 1, 'row' ) ;
		} 
		show( 'statusTable', 1, 'row' ) ; 
		show( 'dateTable', 1, 'row' ) ;  
		showHide( 'buttonTable', 1 ) ;
	</script> 
</logic:equal>
</html:form>

<div><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
