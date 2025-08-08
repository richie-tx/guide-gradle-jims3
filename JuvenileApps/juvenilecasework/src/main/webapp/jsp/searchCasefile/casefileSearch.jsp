<!DOCTYPE HTML>

<%-- Used for searching of juvenile casefiles in MJCW --%>
<%--MODIFICATIONS --%>
<%-- LDeen		05/09/2005	Create JSP --%>
<%-- CShimek	02/01/2011  Activity #69085, revised Case Status search to require Supervision Type and/or Probation Officer Last Name, previously only Probation Officer Last Name was required of these 2 fields --%>
<%-- CShimek	02/03/2011  Activity #69137, revised End Date to Expected End Date in title under Juvenile Caseload details, also changed Date Assigned to JPo to JPO Assigned to accomodate increased prompt --%>
<%-- CShimek	09/26/2011  Revised scripting code to handle user pressing enter -- created local code function checkEnterKeyAndSubmit2() based on checkEnterKeyAndSubmit() in app.js --%>
<%-- CShimek	09/12/2012  #74191 Replaced Title IV-E from caseload result details with Referral# - Court Date --%>
<%-- RYoung     08/06/2015  #27796 MJCW:  Adapt MJCW and Warrants to IE10 and 11 (casefile search) --%>

<%--TAG LIBRARIES NEEDED FOR STRUTS --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2"%>
<%--TAG LIBRARIES NEEDED FOR MOJO --%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="../../WEB-INF/msp.tld" prefix="msp"%>
<%@ page import="naming.PDJuvenileCaseConstants"%>



<%--BEGIN HEADER TAG--%>
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<%-- STYLE SHEET LINK --%>

<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/casework.css" />
<html:base />
<title><bean:message key="title.heading" /> - casefileSearch.jsp</title>

<!--JQUERY FRAMEWORK-->
<%@include file="../jQuery.fw"%>

<%-- STRUTS VALIDATION --%>
<html:javascript formName="casefileSearchForm" />
<%--BUTTON CHECK JAVASCRIPT FILE FOR THIS PAGE --%>

<script type='text/javascript' src="/<msp:webapp/>js/casefileSearch.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/casework.js"></script>
<script type='text/javascript'>



/* The variables below are global variables for this page for the search types.  They 
*  are used in the javascript functions to hide spans and validate required fields.
*  The values are retrieved from the static codes for the Search Types.  If the codes
*  change they only need to be changed in the PDJuvenileCaseConstants class and not
*  anywhere else in this jsp.
*/
var _searchJuvenileName = "<%out.print(naming.PDJuvenileCaseConstants.SEARCH_JUVENILE_NAME);%>";
var _searchJuvenileNumber = "<%out.print(naming.PDJuvenileCaseConstants.SEARCH_JUVENILE_NUMBER);%>";
var _searchOfficerName = "<%out.print(naming.PDJuvenileCaseConstants.SEARCH_PROBATION_OFFICER);%>";
var _searchSupervisionType = "<%out.print(naming.PDJuvenileCaseConstants.SEARCH_SUPERVISION_TYPE);%>";
var _searchCaseStatus = "<%out.print(naming.PDJuvenileCaseConstants.SEARCH_CASE_STATUS);%>";
var _searchSupervisionNumber = "<%out.print(naming.PDJuvenileCaseConstants.SEARCH_SUPERVISION_NUMBER);%>";
var _searchCaseLoad = "<%out.print(naming.PDJuvenileCaseConstants.SEARCH_CASE_LOAD);%>";

function onloadForm()
{
  //alert("document.forms[0].searchTypeId : "+document.forms[0].searchTypeId.selectedIndex);
  //document.forms[0].searchTypeId.selectedIndex = 3 ;
  evalSearch( document.forms[0] ) ;
}

//added for user story 11939
function showHideExpandAll(classStatus, format, path)
{
	
	if(classStatus == null || classStatus == ''){
		classStatus = "Hide";
	}	
	var allSpans = document.getElementsByTagName('span');
	if(allSpans != null)
	{
		for(i = 0;i < allSpans.length; i++)
		{
			var spanId = allSpans[i].id;			
			var spanIndex = spanId.indexOf('Span');
			//find each juvanile's casefile details table using 'Span' string in their span tag id
			if(spanIndex != -1){				
				var theClassName = allSpans[i].className;
				var spanName = spanId.substring(0,spanIndex);
				if (classStatus == 'Hide')
				{
					window.document.images[spanName].src = path + "images/expand.gif";
					document.getElementById(spanId).className = 'hidden';
				}
				else 
				{
					window.document.images[spanName].src = path + "images/contract.gif";
					if(format == "row")
					{
						document.getElementById(spanId).className = 'visibleTR';
					}
					else if(format == "table")
					{
						document.getElementById(spanId).className = 'visibleTable';
					}
					else 
					{			
						document.getElementById(spanId).className = 'visible';
					}						
				}
			}
		}		
	}
	
	//toggle Show/Hide link visibility
	var showDiv = document.getElementById("ShowAllLink");
	var hideDiv = document.getElementById("HideAllLink");	
	if (classStatus == 'Hide'){
		showDiv.style.display = "block";
		hideDiv.style.display = "none";
	}else{
		showDiv.style.display = "none";
		hideDiv.style.display = "block";
	}
		

}

function clearJuvNameFields(el)
{
  el.lastName.value = "";
  el.firstName.value = "";
  el.middleName.value = "";
}

function clearOfficerNameFields(el)
{
  el.officerLastName.value = "";
  el.officerFirstName.value = "";
  el.officerMiddleName.value = "";
}

function clearJuvNumFields(el)
{
  el.juvenileNum.value = "";
}

function clearSupervisionNumFields(el)
{
  el.supervisionNum.value = "";
}

function clearSupervisionTypeId2Fields(el)
{
  el.supervisionTypeId2.value = "";
}

function clearCaseStatusTypeId2Fields(el)
{
  el.caseStatusTypeId2.value = "";
}

function clearLocation(el)
{
  el.locationId.value = "";
}

function clearCaseloadFields(el)
{
  el.caseLoadMgr.value = "";
  el.officer.value = "";
  el.caseStatusCd.value = "";
  e1.casefileActivationStDate.value = "";
  e1.casefileActivationEndDate.value = "";
  el.caseloadExpectedEndDateFrom.value = "";
  el.caseloadExpectedEndDateTo.value = "";
}

function clearCaseStatusDateFields(el)
{
  el.caseStatusExpectedEndDateFrom.value = "";
  el.caseStatusExpectedEndDateTo.value = "";
  el.caseStatusDispDateFrom.value = "";
  el.caseStatusDispDateTo.value = "";

}
function evalSearch(el)
{

	showHide('poSearch1', 0);
	showHide('poSearch2', 0);
	showHide('poSearch3', 0);
	showHide('csSearch1', 0);
	showHide('csSearch2', 0);
	showHide('csSearch3', 0); 
	showHide('caseLoad', 0);
	showHide('caseLoadSearchResults', 0);
	showHide('caseStatus', 0);
	showHide('supType', 0);
	showHide('probOfficer', 0);
	showHide('juvNumber', 0);
	showHide('juvenileName', 0);
	showHide('locationSearch', 0);
	showHide('supervisionNumber', 0);
	showHide('caseloadButtons', 0);
	showHide('searchButtons', 0);
	showHide('caseStatusReq1', 0);
	showHide('caseStatusReq2', 0);
	showHide('dispositionDate', 0);

	var searchType = el.searchTypeId.options[el.searchTypeId.selectedIndex].value  ;
  
	if( searchType == _searchJuvenileName)
	{	
		showHide('juvenileName', 1);	
		clearJuvNumFields(el);
		clearSupervisionNumFields(el);
		clearSupervisionTypeId2Fields(el);
		clearCaseStatusTypeId2Fields(el);
		clearOfficerNameFields(el);
		clearLocation(el);
		document.getElementsByName("lastName")[0].focus();
		showHide('searchButtons', 1);
	}
	else if( searchType == _searchJuvenileNumber)
	{
		showHide('juvNumber', 1);
		clearJuvNameFields(el);
		clearSupervisionNumFields(el);
		clearSupervisionTypeId2Fields(el);
		clearCaseStatusTypeId2Fields(el);
		clearOfficerNameFields(el);
		clearLocation(el);
		document.getElementsByName("juvenileNum")[0].focus();
		showHide('searchButtons', 1);
	}
	else if( searchType == _searchOfficerName)
	{
		showHide('probOfficer', 1);
		showHide('poSearch1', 1);
		showHide('poSearch2', 1);
		showHide('poSearch3', 1);
		clearJuvNameFields(el);
		clearSupervisionNumFields(el);
		clearSupervisionTypeId2Fields(el);
		clearCaseStatusTypeId2Fields(el);
		clearJuvNumFields(el);
		clearLocation(el);
		document.getElementsByName("officerLastName")[0].focus();
		showHide('searchButtons', 1);
	}
	else if( searchType == _searchSupervisionType)
	{
		showHide('supType', 1);
		clearJuvNameFields(el);
		clearSupervisionNumFields(el);
		clearJuvNumFields(el);
		clearCaseStatusTypeId2Fields(el);
		clearOfficerNameFields(el);
		clearLocation(el);
		document.getElementsByName("supervisionTypeId2")[0].focus();
		showHide('searchButtons', 1);
	}
	else if( searchType == _searchCaseStatus)
	{
		showHide('caseStatus', 1);		
		showHide('probOfficer', 1);
		showHide('locationSearch', 1);
		clearJuvNumFields(el);
		clearSupervisionNumFields(el);
		clearSupervisionTypeId2Fields(el);
		clearJuvNameFields(el);
		clearCaseStatusDateFields(el);
		document.getElementsByName("caseStatusTypeId2")[0].focus();
		showHide('searchButtons', 1);
		showHide('caseStatusReq1', 1);
		showHide('caseStatusReq2', 1);
		showHide('csSearch1', 1);
		showHide('csSearch2', 1);
		showHide('csSearch3', 1);
		showHide('dispositionDate', 1)
	}
	else if( searchType == _searchCaseLoad)
	{
		showHide('caseLoad', 1);
		showHide('caseLoadSearchResults', 1);
		showHide('officerRow', 1);
		showHide('statusRow', 1);
		clearJuvNumFields(el);
		clearSupervisionNumFields(el);
		clearSupervisionTypeId2Fields(el);
		clearJuvNameFields(el);
		document.getElementsByName("caseLoadMgr")[0].focus();
		showHide('caseloadButtons', 1);
	}
	else if( searchType == _searchSupervisionNumber)
	{
		showHide('supervisionNumber', 1);
		clearJuvNumFields(el);
		clearJuvNameFields(el);
		clearSupervisionTypeId2Fields(el);
		clearCaseStatusTypeId2Fields(el);
		clearOfficerNameFields(el);
		clearLocation(el);
		document.getElementsByName("supervisionNum")[0].focus();
		showHide('searchButtons', 1);
	}	
							
}

function validateSearch(el)
{	
	var fromDate = "";
	var toDate = "";
	clearAllValArrays();
	var searchType = el.searchTypeId.options[el.searchTypeId.selectedIndex].value ;
	
	if( searchType == _searchJuvenileName )
	{
			customValRequired ("lastName","Juvenile Last Name is required for search.","");
			customValMinLength("lastName","Juvenile Last Name must be at least 2 characters.","2");
			addM204LNameSearchFieldValidation("lastName","<bean:message key='errors.alphanumericWSymbolsM204' arg0='Last Name'/>", "");
			addM204FMNameSearchFieldValidation("firstName","<bean:message key='errors.alphanumericWSymbolsM204' arg0='First Name'/>", "");
			addM204FMNameSearchFieldValidation("middleName","<bean:message key='errors.alphanumericWSymbolsM204' arg0='Middle Name'/>", "");
	}
	else if( searchType ==_searchJuvenileNumber )
	{
			customValRequired ("juvenileNum","Juvenile Number is required for search.","");
			addNumericValidation("juvenileNum","<bean:message key='errors.numeric' arg0='Juvenile Number'/>", "");
	}
	else if( searchType ==_searchOfficerName )
	{
			customValRequired ("officerLastName","Probation Officer Last Name is required for search.","");
			customValMinLength("officerLastName","Probation Officer Last Name must be at least 2 characters.","2");
			addM204LNameSearchFieldValidation("officerLastName","<bean:message key='errors.alphanumericWSymbolsM204' arg0='Probation Officer Last Name'/>", "");
			addM204FMNameSearchFieldValidation("officerFirstName","<bean:message key='errors.alphanumericWSymbolsM204' arg0='Probation Officer First Name'/>", "");
			addM204FMNameSearchFieldValidation("officerMiddleName","<bean:message key='errors.alphanumericWSymbolsM204' arg0='Probation Officer Middle Name'/>", "");
	}
	else if( searchType ==_searchCaseStatus )
	{
			customValRequired ("caseStatusTypeId2","Case Status is required for search.","");
	//		customValRequired ("officerLastName","Probation Officer Last Name is required for search.","");
	//		customValMinLength("officerLastName","Probation Officer Last Name must be at least 2 characters.","2");
			if (document.forms[0].officerLastName.value.length > 1){
				addM204LNameSearchFieldValidation("officerLastName","<bean:message key='errors.alphanumericWSymbolsM204' arg0='Probation Officer Last Name'/>", "");
			}
			addM204FMNameSearchFieldValidation("officerFirstName","<bean:message key='errors.alphanumericWSymbolsM204' arg0='Probation Officer First Name'/>", "");
			addM204FMNameSearchFieldValidation("officerMiddleName","<bean:message key='errors.alphanumericWSymbolsM204' arg0='Probation Officer Middle Name'/>", "");
			fromDate = document.getElementById("fromExpDate").value;
			toDate = document.getElementById("toExpDate").value;
			dispFromDate = document.getElementById("fromDispDate").value;
			dispToDate = document.getElementById("toDispDate").value;
			if (document.forms[0].caseStatusTypeId2.options[document.forms[0].caseStatusTypeId2.selectedIndex].value == "P") {
				if (fromDate != "") {
					alert("Expected End Date or Date Range search entry not valid on PENDING case status.");
					document.getElementById("fromExpDate").focus();
					return false
				}
				if (toDate != "") {
					alert("Expected End Date or Date Range search entry not valid on PENDING case status.");
					document.getElementById("toExpDate").focus();
					return false
				} 
			}
			if (fromDate != "") 
			{
				addMMDDYYYYDateValidation("caseStatusExpectedEndDateFrom","Expected End Date 'From Date' is invalid.");
			}	
			if(toDate != "")
			{
				if (fromDate == "")
				{
					customValRequired ("caseStatusExpectedEndDateFrom","Expected End Date 'Form Date' is required for search by Expected End Date Range.","");
				}
				addMMDDYYYYDateValidation("caseStatusExpectedEndDateTo","Expected End Date 'To Date' is invalid.");
			} 
			
			if (dispFromDate != "") 
			{
				addMMDDYYYYDateValidation("caseStatusDispDateFrom","Disposition Date 'From Date' is invalid.");
			}	
			if(dispToDate != "")
			{
				if (dispFromDate == "")
				{
					customValRequired ("caseStatusDispDateFrom","Disposition Date 'From Date' is required for search by Disposition Date Range.","");
				}
				addMMDDYYYYDateValidation("caseStatusDispDateTo","Disposition Date 'To Date' is invalid.");
			} 

	}
	else if( searchType ==_searchSupervisionNumber )
	{
			customValRequired ("supervisionNum","Supervision Number is required for search.","");
			customValMinLength("supervisionNum","Supervision Number must be 8 digits.","8");
			addNumericValidation("supervisionNum","<bean:message key='errors.numeric' arg0='Supervision Number'/>", "");
	}
	else if( searchType ==_searchCaseLoad )
	{
			customValRequired ("caseLoadMgr","Caseload Manager is required for search.","");
			customValRequired ("officer","JPO is required for search.","");
			customValRequired ("caseStatusCd","Case Status is required for search.","");
			// activation date edits
			fromDate = document.getElementById("casefileActivationStDate").value;
			toDate = document.getElementById("casefileActivationEndDate").value;
			if (fromDate != "") 
			{
				addMMDDYYYYDateValidation("casefileActivationStDate","Activation 'From Date' is invalid.");
			}	
			if(toDate != "")
			{
				if (fromDate == "")
				{
					customValRequired ("casefileActivationStDate","Activation 'Form Date' is required for Caseload search by Activation Date Range.","");
				}
				addMMDDYYYYDateValidation("casefileActivationEndDate","Activation 'To Date' is invalid.");
			} 
			// expected date edits
			fromDate = document.getElementById("caseloadExpectedEndDateFrom").value;
			toDate = document.getElementById("caseloadExpectedEndDateTo").value;
			if (document.forms[0].caseStatusCd.options[document.forms[0].caseStatusCd.selectedIndex].value == "P") {
				if (fromDate != "") {
					alert("Expected End Date or Date Range search entry not valid on PENDING case status.");
					document.getElementById("caseloadExpectedEndDateFrom").focus();
					return false
				}
				if (toDate != "") {
					alert("Expected End Date or Date Range search entry not valid on PENDING case status.");
					document.getElementById("caseloadExpectedEndDateTo").focus();
					return false
				} 
			}

			if (fromDate != "") 
			{
				addMMDDYYYYDateValidation("caseloadExpectedEndDateFrom","Expected End Date 'From Date' is invalid.");
			}	
			if(toDate != "")
			{
				if (fromDate == "")
				{
					customValRequired ("caseloadExpectedEndDateFrom","Expected End Date 'Form Date' is required for search by Expected End Date Range.","");
				}
				addMMDDYYYYDateValidation("caseloadExpectedEndDateTo","Expected End Date 'To Date' is invalid.");
			} 
			
	}
//	return validateCustomStrutsBasedJS(el)
	var result = validateCustomStrutsBasedJS(el);
		
	if (result == true) {
		var result2 = "";
		if (searchType ==_searchCaseStatus){
			var offLastName = Trim(document.forms[0].officerLastName.value);
			var zipCode = document.getElementsByName("zipCode")[0].value;
			//var locationId = document.getElementsByName("location")[0].value;
			if (document.forms[0].supervisionTypeId2.selectedIndex == 0){
				if (offLastName == "" && zipCode ==""){
					if (document.forms[0].locationId.selectedIndex == 0){
					result2 = "Supervision Type , Probation Officer Last Name, Location and/or ZipCode is required.\n";
					document.forms[0].supervisionTypeId2.focus();
				} 
				}
				if (offLastName != "" && offLastName.length < 2){
					if (result2 == "") {
						document.forms[0].officerLastName.focus();
					}
					result2 += "Probation Officer Last Name must be at least 2 characters.\n";
				}
			}
			if (document.forms[0].supervisionTypeId2.selectedIndex > 0){
				if (offLastName != "" && offLastName.length < 2){
					if (result2 == "") {
						document.forms[0].officerLastName.focus();
					}
					result2 += "Probation Officer Last Name must be at least 2 characters.\n";
				}	
			}
			fromDate = document.getElementById("fromExpDate").value;
			toDate = document.getElementById("toExpDate").value;
			if (fromDate != "" && toDate != "")
			{
				if (compDates(fromDate, toDate) == true )
				{
					if (result2 == "") {
						document.forms[0].caseStatusExpectedEndDateFrom.focus();
					}
					result2 += "Expected End Date 'From Date' must be before Expected End Date 'To Date'.";
				}	
			}
		
		}
		if (searchType ==_searchCaseLoad){
			fromDate = document.getElementById("casefileActivationStDate").value;
			toDate = document.getElementById("casefileActivationEndDate").value;
			if (fromDate != "" && toDate != "")
			{
				if (compDates(fromDate, toDate) == true )
				{
					if (result2 == "") {
						document.forms[0].casefileActivationStDate.focus();
					}
					result2 = "Activation Date 'From Date' must be before Activation 'To Date'.\n";
				}	
			}
			fromDate = document.getElementById("caseloadExpectedEndDateFrom").value;
			toDate = document.getElementById("caseloadExpectedEndDateTo").value;
			if (fromDate != "" && toDate != "")
			{
				if (compDates(fromDate, toDate) == true )
				{
					if (result2 == "") {
						document.forms[0].caseloadExpectedEndDateFrom.focus();
					}
					result2 += "Expected End Date 'From Date' must be before Expected End Date 'To Date'.";
				}	
			}
		}	
		if (result2 != ""){
			alert(result2);
			result = false;
		}
	}
	if ( result ) {
		spinner();
	}
	return result;
}

function compDates(dateValAsString1, dateValAsString2)
{   
	result = false;
	var date1=new Date(dateValAsString1);
	var date2=new Date(dateValAsString2);
	date1.setHours(0);
	date1.setMinutes(0);
	date1.setSeconds(0);
	date2.setHours(0);
	date2.setMinutes(0);
	date2.setSeconds(0);
	if ((date1 > date2))			
	{   
   		result = true;
	}
	return result;
}

//imitates the reset button by clearing the text fields only
//param: theForm - the form object 
function clearForm(el) 
{
 	for(var i = 0; i < el.length; i++)
 	{
   	if(el.elements[i].type == 'text')
   	{
     	el.elements[i].value = "";
   	}
	}
	// Reset the CaseStatus and SupervisionType Dropdowns
	el.caseStatusTypeId.value = "";
	el.supervisionTypeId.value = "";
	el.caseStatusTypeId2.value = "";
	el.supervisionTypeId2.value = "";
	evalSearch(el);
}

function getOfficersUnderCLM(theForm) 
{
	if(document.getElementById("caseLoadMgr") != null)
	{
		if (document.getElementById("caseLoadMgr").selectedIndex > 0)
		{
			theForm.action = "/<msp:webapp/>displayJuvenileCasefileSearch.do?action=getOfficers";
			theForm.submit();		
		}
		else
		{
			alert("Please select a Caseload Manager.");
		}	
	}
}

function refreshCaseload(theForm) 
{
	theForm.action = "/<msp:webapp/>displayJuvenileCasefileSearch.do?action=refreshCaseload";
	theForm.submit();		
}

function checkEnterKeyAndSubmit2( evt, disableEnter )
{
	var sbys = document.getElementsByName("searchTypeId");
	var searchType = sbys[0].options[sbys[0].selectedIndex].value  ;
	if (searchType != 'CSLD')
	{	
		var currentElement = evt.srcElement;
		var key = evt.keyCode;

		if( event.ctrlKey == true )
		{
			if( key == 78  ||  key == 110 )
			{  // the n or N key
				alert( "Sorry opening a new browser window is not allowed." );
				disableEvent( evt );
				return false;
			}	
		}

		if( disableEnter == true &&  key == 13 )
		{
			if( currentElement != null )
			{
				if( currentElement.type == 'textarea' )
				{
				// alert( "Have a text area don't do anything with the enter key" );
					return true;
				}
			}
			var submitBtn = document.getElementById("submitButton");
			submitBtn.click( );
			return true;
		}
	}
	return true;
}
</script>
</head>

<%--BEGIN BODY TAG--%>
<body onKeyDown="return checkEnterKeyAndSubmit2(event,true);" onload="onloadForm();">


<%--BEGIN FORM TAG--%>
<html:form action="/displayJuvenileCasefileSearchResults" target="content" focus="searchTypeId" onsubmit="return SearchValidator(this);">
<input type="hidden" name="helpFile" value="ManageJuvenileCasework/managejuvenilecasework/Manage_Juvenile_Casework.htm#|46">

	<%-- BEGIN HEADING TABLE --%>
	<table width="100%">
		<tr>
			<td class="header" align="center"><bean:message key="title.juvenileCasework" /> - <bean:message key="title.mjcwSearchCasefile" /></td>
		</tr>
	</table>
	<%-- END HEADING TABLE --%>

	<%-- BEGIN INSTRUCTION TABLE --%>
	<table width="98%" border="0" align="center">
		<tr>
			<td>
				<ul>
					<li><bean:message key="prompt.mjcwSearchRequirements" /></li>
				</ul>
			</td>
		</tr>
		<tr>
			<td class="required"><bean:message key="prompt.2.diamond" />Required Fields</td>
		</tr>
		<tr id="caseStatusReq1" class="hidden">
			<td class="required">+ At least one of these fields is required.</td>
		</tr>
		<tr id="caseStatusReq2" class="hidden">
			<td class="required">++ indicates Last Name is required to use this search field.</td>
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

	<%-- BEGIN INQUIRY TABLE --%>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr>
			<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.searchBy" /></td>
			<td class="formDe">
				<html:select property="searchTypeId" onchange="evalSearch(this.form)">
					<html:optionsCollection property="juvenileCaseSearchTypes" value="code" label="description" />
				</html:select>
			</td>
		</tr>
	</table>
	<br>

	<%-- SEARCH BY CASE STATUS --%>
	<span id="caseStatus" class='hidden'>
		<table align="center" width="98%" border="0" cellpadding="2"  colspan="4" cellspacing="1" class="borderTableBlue">
			<tr>
				<td class="formDeLabel" width="1%" nowrap="nowrap" colpsan="1"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.caseStatus" /></td>
				<td class="formDe" colspan="3">
					<html:select property="caseStatusTypeId2">
						
						<html:option key="select.generic" value=""/>
						<html:option key="A" value="ACTIVE"/>
						<html:option key="C" value="CLOSED"/>
						<html:option key="CIP" value="CLOSING IN PROCESS"/>
						<html:option key="P" value="PENDING"/>
						<%--<html:option value=""><bean:message key="select.generic" /></html:option>
						 <html:optionsCollection property="juvenileCaseStatuses" value="code" label="description" /> --%> <!-- commented for US 157620 -->
					</html:select>
				</td>
			</tr>
			<tr>
				<td class="formDeLabel" width="1%" nowrap="nowrap" colspan="1">+<bean:message key="prompt.supervisionType" /></td>
				<td class="formDe" colspan="1" width="50%">
					<html:select property="supervisionTypeId2">
						<html:option value=""><bean:message key="select.generic" /></html:option>
						<html:optionsCollection property="supervisionTypes" value="code" label="description" />
					</html:select>
				</td>
				<td class="formDeLabel" colspan="1" width="1%" nowrap="nowrap">+ <bean:message key="prompt.zipCode"/></td>
				<td class="formDe" colspan="1"><html:text property="zipCode" size="5" maxlength="5" /></td>
			</tr>
			<tr>
			<td class="formDeLabel" width="1%" nowrap="nowrap" colspan="1">+<bean:message key="prompt.location" />&nbsp;<bean:message key="prompt.unit" /></td>
			<td class="formDe" colspan="3">
				<html:select property="locationId">
					<html:option value=""><bean:message key="select.generic" /></html:option>
					<%-- <html:optionsCollection property="location" value="locationId" label="locationName" /> --%>
					<html:optionsCollection property="location" value="locationId" label="locationUnitName" />
				</html:select>
			</td>
			</tr>
		</table>
		<br>
	</span>
	
	<%-- SEARCH BY JUVENILE NAME --%>
	<span id="juvenileName" class='visible'>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.juvenileLastName" /></td>
			<td class="formDe"><html:text property="lastName" size="65" maxlength="75" /></td>
		</tr>
		<tr>
			<td class="formDeLabel"><bean:message key="prompt.juvenileFirstName" /></td>
			<td class="formDe"><html:text property="firstName" size="50" maxlength="50" /></td>
		</tr>
		<tr>
			<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.juvenileMiddleName" /></td>
			<td class="formDe"><html:text property="middleName" size="50" maxlength="50" /></td>
		</tr>
	</table>
	<br>
	</span>
	<span id="probOfficer" class='hidden'>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr>
			<td class="formDeLabel">
				<span id="poSearch1" class="hidden"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.probationOfficer" /> <bean:message key="prompt.lastName" /></span>
				<span id="csSearch1" class="hidden">+<bean:message key="prompt.probationOfficer" /> <bean:message key="prompt.lastName" /></span>
			</td>	
			<td class="formDe"><html:text property="officerLastName" size="60" maxlength="75" /></td>
		</tr>
		<tr>
			<td class="formDeLabel">
				<span id="poSearch2" class="hidden"><bean:message key="prompt.probationOfficer" /> <bean:message key="prompt.firstName" /></span>
				<span id="csSearch2" class="hidden">++<bean:message key="prompt.probationOfficer" /> <bean:message key="prompt.firstName" /></span>
			</td>
			<td class="formDe"><html:text property="officerFirstName" size="50" maxlength="50" /></td>
		</tr>
		<tr>
			<td class="formDeLabel" width="1%" nowrap="nowrap">
				<span id="poSearch3" class="hidden"><bean:message key="prompt.probationOfficer" /> <bean:message key="prompt.middleName" /></span>
				<span id="csSearch3" class="hidden">++<bean:message key="prompt.probationOfficer" /> <bean:message key="prompt.middleName" /></span>
			</td>
			<td class="formDe"><html:text property="officerMiddleName" size="50" maxlength="50" /></td>
		</tr>
	</table>
	<br>
	</span>

	<span id="locationSearch" class='hidden'>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr class="formDeLabel">
			<td class="detailHead" colspan="14">Expected End Date or Date Range</td>
		</tr>
		<tr class="formDe">
			<td class="formDeLabel" nowrap="nowrap" width='1%'>From</td>
			<td colspan="2">
				<html:text name="casefileSearchForm" property="caseStatusExpectedEndDateFrom" size="10" maxlength="10" title="mm/dd/yyyy" styleId="fromExpDate">From</html:text>
			</td>
			<td class="formDeLabel" nowrap="nowrap" width='1%'>To</td>
			<td colspan="2">	
				<html:text name="casefileSearchForm" property="caseStatusExpectedEndDateTo" size="10" maxlength="10" title="mm/dd/yyyy" styleId="toExpDate">To</html:text>
			</td>
		</tr> 
	</table>
	<br>	
	</span>
	<%-- Task 50044 changes --%>
	<span id="dispositionDate" class='hidden'>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr class="formDeLabel">
			<td class="detailHead" colspan="14">Disposition Date or Date Range</td>
		</tr>
		<tr class="formDe">
			<td class="formDeLabel" nowrap="nowrap" width='1%'>From</td>
			<td colspan="2">
				<html:text name="casefileSearchForm" property="caseStatusDispDateFrom" size="10" maxlength="10" title="mm/dd/yyyy" styleId="fromDispDate">From</html:text>
			</td>
			<td class="formDeLabel" nowrap="nowrap" width='1%'>To</td>
			<td colspan="2">	
				<html:text name="casefileSearchForm" property="caseStatusDispDateTo" size="10" maxlength="10" title="mm/dd/yyyy" styleId="toDispDate">To</html:text>
			</td>
		</tr> 
	</table>
	</span>
	<br>
	
	
	
	<%-- SEARCH BY SUPERVISION NUMBER --%>
	<span id="supervisionNumber" class='hidden'>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr>
			<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.supervisionNumber" /></td>
			<td class="formDe"><html:text property="supervisionNum" size="8" maxlength="8" /></td>
		</tr>
	</table>
	<br>
	</span>
	
		<%-- SEARCH BY JUVENILE NUMBER --%>
	<span id="juvNumber" class='hidden'>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
		<tr>
			<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.juvenileNumber" /></td>
			<td class="formDe"><html:text property="juvenileNum" size="8" maxlength="8" /></td>
		</tr>
	</table>
	<br>
	</span>

	

	<%-- SEARCH BY SUPERVISION TYPE --%>
	<span id="supType" class='hidden'>
	<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" colspan="4" class="borderTableBlue">
		<tr>
			<td class="formDeLabel" width="1%" nowrap="nowrap" colspan="1"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.supervisionType" /></td>
			<td class="formDe" colspan="1" width="50%">
				<html:select property="supervisionTypeId">
					<html:option value=""><bean:message key="select.generic" /></html:option>
					<html:optionsCollection property="supervisionTypes" value="code" label="description" />
				</html:select>
			</td>
			<td class="formDeLabel" width="1%" nowrap="nowrap" colspan="1">+ <bean:message key="prompt.zipCode"/></td>
			<td class="formDe" colspan="1"><html:text property="zipCode" size="5" maxlength="5" /></td>
		</tr>
		<tr>
			<td class="formDeLabel" width="1%" nowrap="nowrap" colspan="1"><bean:message key="prompt.caseStatus" /></td>
			<td class="formDe" colspan="3">
				<html:select property="caseStatusTypeId">
					<html:option value="">
						<bean:message key="select.generic" />
					</html:option>
					<html:optionsCollection property="juvenileCaseStatuses" value="code" label="description" />
				</html:select>
			</td>
		</tr>
	</table>
	<br>
	</span>
	<span id="searchButtons" class='hidden'> <%-- BEGIN BUTTON TABLE --%>
	<table align="center" border="0" width="100%">
		<tr>
			<td align="center">
				<html:submit property="submitAction" onclick="return validateSearch(this.form)" styleId="submitButton">
					<bean:message key="button.submit" />
				</html:submit>&nbsp; <input type="button" onclick="goNav('/<msp:webapp/>displayJuvenileCasefileSearch.do')" value="<bean:message key='button.refresh'/>" /> <html:button property="org.apache.struts.taglib.html.CANCEL" onclick="document.location.href='/appshell/displayHomeSecurity.do'">
					<bean:message key="button.cancel"></bean:message>
				</html:button>
			</td>
		</tr>
	</table>
	<%-- END BUTTON TABLE --%> 
	</span>
	
	<%-- SEARCH BY CASE LOAD --%>
	<%-- <KISHORE> JIMS200059601 : Emulate CSCD Caseload Search-Search CF (UI) - KK --%>
	<span id="caseLoad" class='hidden'>
		<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
			<tr class="detailHead">
				<td colspan="7">Search Caseloads</td>
			</tr>
			<tr>
				<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.caseloadManagerName" /></td>
				<td class="formDe" colspan="3">
					<html:select property="caseLoadMgr" styleId="caseLoadMgr">
						<html:option value=""><bean:message key="select.generic" /></html:option>
						<html:optionsCollection property="caseLoadMgrs" value="userId" label="formattedName" />
					</html:select> 
					<input type="button" onclick="javascript:getOfficersUnderCLM(this.form);" value="<bean:message key='button.getOfficers'/>" />
				</td>
			</tr>
			<logic:notEmpty name="casefileSearchForm" property="caseLoadMgrs">
				<logic:notEmpty name="casefileSearchForm" property="officers">
					
					<tr>
						<div id="officerRow" > 
							<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.JPO" /></td>
							<td class="formDe" colspan="3">
								<html:select property="officer">
									<html:option value=""><bean:message key="select.generic" /></html:option>
									<html:optionsCollection property="officers" value="officerId" label="formattedNameWithUserId" />
								</html:select>
							</td>
						</div>
					</tr>
					
					
					<tr id="statusRow">
						<div id="statusRow"> 
							<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.caseStatus" /></td>
							<td class="formDe" colspan="3">
								<html:select property="caseStatusCd">
									<html:option value=""><bean:message key="select.generic" /></html:option>
									<html:optionsCollection property="juvenileCaseStatuses" value="code" label="description" />
								</html:select>
							</td>
						</div>
					</tr>
					
					<!-- <KISHORE> JIMS200060469 : MJCW CF: Add Caseload Search date range(UI)-KK-->
					<tr id="dateRangeRow">
						<td colspan="8">
							<table cellspacing="0" cellpadding="4" width="100%">
								<tr class="formDeLabel">
									<td class="detailHead" colspan="14">Activation Date or Date Range</td>
								</tr>
								<tr class="formDe">
									<td class="formDeLabel" nowrap="nowrap" width='1%'>From</td>
									<td colspan="2">
										<html:text name="casefileSearchForm" property="casefileActivationStDate" size="10" maxlength="10" title="mm/dd/yyyy" styleId="casefileActivationStDate" >From</html:text>
									</td>
									<td class="formDeLabel" nowrap="nowrap" width='1%'>To</td>
									<td colspan="2">	
										<html:text name="casefileSearchForm" property="casefileActivationEndDate" size="10" maxlength="10" title="mm/dd/yyyy" styleId="casefileActivationEndDate">To</html:text>
									</td>
								</tr> 
								<tr class="formDeLabel">
									<td class="detailHead" colspan="14">Expected End Date or Date Range</td>
								</tr>
								<tr class="formDe">
									<td class="formDeLabel" nowrap="nowrap" width='1%'>From</td>
									<td colspan="2">
										<html:text name="casefileSearchForm" property="caseloadExpectedEndDateFrom" size="10" maxlength="10" title="mm/dd/yyyy" styleId="caseloadExpectedEndDateFrom">From</html:text>
									</td>
									<td class="formDeLabel" nowrap="nowrap" width='1%'>To</td>
									<td colspan="2">	
										<html:text name="casefileSearchForm" property="caseloadExpectedEndDateTo" size="10" maxlength="10" title="mm/dd/yyyy" styleId="caseloadExpectedEndDateTo">To</html:text>
									</td>
								</tr> 
							</table>
						</td>
					</tr>
					<tr>
						<td class="formDeLabel"></td>
						<td class="formDe" colspan="3">
							<html:submit property="submitAction" onclick="return validateSearch(this.form);return disableSubmit(this, this.form)">
								<bean:message key="button.viewCaseload" />
							</html:submit> 
							<input type="button" onclick="javascript:refreshCaseload(this.form);" value="<bean:message key='button.refresh'/>" />
						</td>
					</tr>
				</logic:notEmpty>
			</logic:notEmpty>
			<logic:empty name="casefileSearchForm" property="officers">
				<tr>
					<td class="formDeLabel"></td>
					<td class="formDe" colspan="3">
						<html:submit property="submitAction" disabled="true" styleId="viewCaseloadId"><bean:message key="button.viewCaseload" /></html:submit> 
						<input type="button" onclick="javascript:refreshCaseload(this.form);" value="<bean:message key='button.refresh'/>" />
					</td>
				</tr>
			</logic:empty>
		</table>
		<br>
	</span>


	<span id="caseLoadSearchResults" class='hidden'> 
	<logic:notEmpty name="casefileSearchForm" property="caseloads">
		<!-- Begin Pagination Header Tag-->
		<bean:define id="paginationResultsPerPage" type="java.lang.String">
			<bean:message key="pagination.recordsPerPage"></bean:message>
		</bean:define>
		<pg:pager index="center" maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>" maxIndexPages="10" export="offset,currentPageNumber=pageNumber" scope="request">
			<!-- End Pagination header stuff -->

			<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
				<tr>
					<td class="detailHead" colspan="7">Current Caseload</td>
				</tr>
				<tr>
					<td class="formDeLabel">JPO</td>
					<td class="formDe" colspan="6"><bean:write name="casefileSearchForm" property="officerName" /></td>
				</tr>
				<tr>
					<td width="1%"></td>
				</tr>
				<tr>
					<td class="formDeLabel" width="1%" nowrap="nowrap"># of Juveniles</td>
					<td class="formDe"><bean:write name="casefileSearchForm" property="juvenilesCount" /></td>
					<td class="formDeLabel" width="1%" nowrap="nowrap"># of Casefiles</td>
					<td class="formDe"><bean:write name="casefileSearchForm" property="casefilesCount" /></td>
				</tr>
			</table>
			
			<div class="spacer4px" />
			<div class="spacer4px" />
			
			<bean:size id="searchResultsCount" name="casefileSearchForm" property="caseloads"/>
			<table align="center" width="98%" border="0" cellpadding="2" cellspacing="1" class="borderTableBlue">
				
				<tr>
					<td class="detailHead" colspan="7">
					<div id="ShowAllLink" style="display: block;">Juvenile Caseload - <bean:write name="searchResultsCount" /> record(s) found
					<!-- added for User Story 11939 -->
					&nbsp;<a href="javascript:showHideExpandAll('Show','table','/<msp:webapp/>');" title="Show All Cases">Show All</a>
					</div>
					
					<div id="HideAllLink" style="display: none;">Juvenile Caseload - <bean:write name="searchResultsCount" /> record(s) found
					<!-- added for User Story 11939 -->
					&nbsp;<a href="javascript:showHideExpandAll('Hide','table','/<msp:webapp/>');" title="Hide All Cases">Hide All</a>
					</div>
					</td>
				</tr>						
				
				<tr class="formDeLabel">
					<td width="1%"></td>
					<td><bean:message key="prompt.juvenileName"/><jims2:sortResults beanName="casefileSearchForm" results="caseloads" primaryPropSort="juvenileName" primarySortType="STRING" defaultSort="true" defaultSortOrder="ASC" sortId="1" /></td>
					<td><bean:message key="prompt.juvenile#"/><jims2:sortResults beanName="casefileSearchForm" results="caseloads" primaryPropSort="juvenileNum" primarySortType="INTEGER" defaultSort="false" defaultSortOrder="ASC" sortId="2" /></td>
					<td><bean:message key="prompt.facility"/> <bean:message key="prompt.location"/><jims2:sortResults beanName="casefileSearchForm" results="caseloads" primaryPropSort="detentionFacility" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="3" /></td>
					<td><bean:message key="prompt.facility"/> <bean:message key="prompt.status"/><jims2:sortResults beanName="casefileSearchForm" results="caseloads" primaryPropSort="detentionStatus" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="4" /></td>
					<td><bean:message key="prompt.master"/> <bean:message key="prompt.status"/><jims2:sortResults beanName="casefileSearchForm" results="caseloads" primaryPropSort="masterStatus" primarySortType="STRING" defaultSort="false" defaultSortOrder="ASC" sortId="5" /></td>
				</tr>

				<logic:iterate indexId="index" id="juv" name="casefileSearchForm" property="caseloads">
					<pg:item>
						<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
							<td width="1%"><a href="javascript:showHideExpand('img<bean:write name="juv" property="juvenileNum" />','table','/<msp:webapp/>');" title="View Cases"> <img src="/<msp:webapp/>images/expand.gif" name="img<bean:write name="juv" property="juvenileNum" />" border="0" /> </a></td>
							<td><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileBriefingDetails.do?submitAction=Link&juvenileNum=<bean:write name='juv' property='juvenileNum'/>"> <bean:write name="juv" property="juvenileName" /> </a>
							<logic:equal name="juv" property="juvRectype" value="I.JUVENILE">
							&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Purged'>P</span></b></font>
							</logic:equal>
							<logic:equal name="juv" property="juvRectype" value="S.JUVENILE">
							&nbsp;&nbsp;&nbsp;&nbsp;<font color="Blue" face="verdana"><b><span title='Sealed'>S</span></b></font>
							</logic:equal></td>
							<td><bean:write name="juv" property="juvenileNum" /></td>
							<td><bean:write name="juv" property="detentionFacility" /></td>
							<td><bean:write name="juv" property="detentionStatus" /></td>
							<td><bean:write name="juv" property="masterStatus" /></td>
						</tr>
						<tr>
							<td colspan="10">
								<span id="img<bean:write name="juv" property="juvenileNum" />Span" class="hidden">
								<table align="center" width="100%" border="0" cellpadding="2" cellspacing="1" class='borderTableGrey'>
									<tr class="formDeLabel">
										<td width="7%">Casefile #</td>
										<td width="5%">Seq #</td>
										<td width="27%">Supervision Type</td>
										<td width="8%">Case Status</td>
										<td width="10%">JPO Assigned</td>
										<td width="8%">Expected End Date</td>
										<td width="7%">Days Left</td>
										<td width="14%">Referral# - Court Date</td>
										<td width="7%">MAYSI needed?</td>
									</tr>
									<logic:iterate indexId="index" id="activeCase" name="juv" property="casefileAssignments">
										<tr class="<%out.print( (index.intValue() % 2 == 1) ? "alternateRow" : "normalRow" );%>">
											<logic:equal name="activeCase" property="caseStatus" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING_DESCRIPTION%>">	 	
	  											<td valign="top"><a onclick="spinner()" href="/<msp:webapp/>handleCasefileActivation.do?submitAction=Link&casefileID=<bean:write name='activeCase' property='supervisionNum'/>&action=default"><bean:write name="activeCase" property="supervisionNum"/></a></td>
	  										</logic:equal>
	  										<logic:notEqual name="activeCase" property="caseStatus" value="<%=naming.UIConstants.CASEFILE_CASE_STATUS_PENDING_DESCRIPTION%>">
	  											<td valign="top"><a onclick="spinner()" href="/<msp:webapp/>displayJuvenileCasefileDetails.do?submitAction=Link&supervisionNum=<bean:write name='activeCase' property='supervisionNum'/>&action=default"><bean:write name="activeCase" property="supervisionNum"/></a></td> 	
	 										</logic:notEqual>
											<td valign="top"><bean:write name="activeCase" property="sequenceNum" /></td>
											<td valign="top"><bean:write name="activeCase" property="supervisionType" /></td>
											<td valign="top"><bean:write name="activeCase" property="caseStatus" /></td>
											<td valign="top"><bean:write name="activeCase" property="jpoAssignmentDate" /></td>
											<td valign="top"><bean:write name="activeCase" property="supervisionEndDate" /></td>
											<logic:lessThan name="activeCase" property="daysLeft" value="0">
												<td class="detailHead" valign="top"><bean:write name="activeCase" property="daysLeft" /></td>
											</logic:lessThan>
											<logic:greaterEqual name="activeCase" property="daysLeft" value="0">
												<logic:lessEqual name="activeCase" property="daysLeft" value="30">
													<td class="changedRed" valign="top"><bean:write name="activeCase" property="daysLeft" /></td>
												</logic:lessEqual>
											</logic:greaterEqual>
											<logic:greaterThan name="activeCase" property="daysLeft" value="30">
												<logic:lessEqual name="activeCase" property="daysLeft" value="90">
													<td valign="top"><bean:write name="activeCase" property="daysLeft" /></td>
												</logic:lessEqual>
											</logic:greaterThan>
											<logic:greaterThan name="activeCase" property="daysLeft" value="90">
												<td></td>
											</logic:greaterThan>
										<%-- 	<td><logic:equal name="activeCase" property="titleIVNeeded" value="true">Y</logic:equal> <logic:equal name="activeCase" property="titleIVNeeded" value="false">N</logic:equal></td> --%>
											<td valign="top">
												<logic:iterate id="refCrtDates" name="activeCase" property="referralNumCourtDates">
											 		<div>&nbsp;&nbsp;<bean:write name="refCrtDates" property="referralNumber"/> - <bean:write name="refCrtDates" property="courtDate" formatKey="date.format.mmddyyyy"/></div> 
												</logic:iterate>
											</td>
											<td valign="top"><logic:equal name="activeCase" property="MAYSINeeded" value="true">Y</logic:equal> <logic:equal name="activeCase" property="MAYSINeeded" value="false">N</logic:equal></td>
										</tr>
									</logic:iterate>
								</table>
								</span>
							</td>
						</tr>
					</pg:item>
				</logic:iterate>
				<!-- Begin Pagination navigation Row-->
				<table align="center">
					<tr>
						<td>
							<pg:index>
								<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
									<tiles:put name="pagerUniqueName" value="pagerSearch" />
									<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>" />
								</tiles:insert>
							</pg:index>
						</td>
					</tr>
				</table>
				<!-- End Pagination navigation Row-->
			</table>
		</pg:pager>
	</logic:notEmpty> 
	</span>

	

	

	<%-- END INQUIRY TABLE --%>

	<span id="caseloadButtons" class='hidden'> <%-- BEGIN BUTTON TABLE --%>
	<table align="center" border="0" width="100%">
		<tr>
			<td align="center">
				
			</td>
		</tr>
	</table>
	<%-- END BUTTON TABLE --%> </span>
	
</html:form>
<%-- END FORM --%>

		<br/>
		<logic:notEmpty name="casefileSearchForm" property="caseloads">
	<table align="center">
  			<tr>
    			<td>
    			<html:form action="/displayJuvenileCasefileSearchResults?print=Y" onsubmit="return this;" method="post">
    				<html:submit onclick="return this;" styleId="printResults"><bean:message key="button.printCaseloadSearchResults"/></html:submit>
    				</html:form>
    			</td>    			
    		</tr>
    	</table>
    	</logic:notEmpty>
<br/>
<div align='center'><script type="text/javascript">renderBackToTop()</script></div>

</body>
</html:html>
