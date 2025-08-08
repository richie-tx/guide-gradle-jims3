<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 08/03/2006	 Justin Jose - Create JSP -->
<!-- 08/28/2006  Hien Rodriguez  ER#34507 Implement new UI Guidelines for all date fields -->
<!-- 04/30/2007  C. Shimek       defect#41505 added edit to limit casenote text to 1000 characters.
								 Set size based on email from Dawn Gibler stating should match MSO; did not
								 find maxlength in requirement doc as of ver 2.1 -->
<!-- 06/14/2007  C. Shimek       defect#41505 revised max casenote text size to 3500 based note added to defect 5/18/07 6:50:28 AM by MPatino. Verified DB field can hold this length. -->
<!-- 10/23/2007  C. Shimek 		 defect#46036 add cursor set -->
<!-- 4/23/2008   C. Shimek       defect#49971 correct contact method is required message  -->
<!-- 05/13/2008  L Deen		  	 Removed CSCD from page title  -->
<!-- 06/04/2008  C. Shimek       activity#51906 revised date/time display format -->
<!-- 07/02/2008  R. Young        activity#52040 Web focus integration -->
<!-- 09/18/2008  C. Shimek       defect#50133 removed instruction above Search Casenote block for basic search  -->
<!-- 10/09/2008  C. Shimek       defect#54594 removed edit on case number select, All is default part 2 of defect requires major PD changes  -->
<!-- 01/06/2009  C. Shimek       activity#56326 removed compliance buttons  -->
<!-- 12/20/2009  C. Shimek       activity#51908 reopenned - revised time casenote time popup message to include correct message with time example -->
<!-- 02/19/2009  C. Shimek       #57332 revise casenote time edit to correctly edit when input hour = 12 -->
<!-- 05/19/2009  Young    		 #59671 Casenotes print button give error screen -->
<!-- 05/19/2009  L Deen		     #59746 Remove Assessments & Associated Tasks button -->
<!-- 11/13/2009  C Shimek        #62757 Revised Search By "Cases" option value from caseNum to cdiNCaseNum to fix null pointer exception -->
<!-- 05/23/2010  L Deen		     Defect #65672 Hide Advanced Supervisee Search on this page temporarily -->
<!-- 05/26/2010  C. Shimek       #65373 correct new line break issue by using tinyMCECustomInitCasenote.js and removing validateBRTagOnly()-->
<!-- 06/17/2010  C. Shimek       Temp.fix for UJAC printing to limit user to 35 lines of 100 characters each on casenote entry, total excludes hidden bold, underline and italic tags -->
<!-- 06/22/2010  L Deen       	 #66116 Change function count lines var from 100 to 127 -->
<!-- 06/23/2010  Young       	 #63506 Added the disableSubmit for the save as draft and finish buttons -->
<!-- 06/25/2010  C. Shimek       #66167 Added print link for each casenote and aligned 'Edit Delete Print' -->
<!-- 07/13/2010  Young       	 #63506 Remove the countLines javascript -->
<!-- 07/22/2010  C. Shimek       #66425 Revised printReport() window size to better fit image -->
<!-- 08/12/2010  C. Shimek       #66890 Revised js for casenote date and time to use presisted values when present, otherwise use current date and time -->
<!-- 08/12/2010  D. Williamson	 Changed wording of casenoteDate error message	 -->
<!-- 09/10/2010  D. Williamson	 ER #66573 Added Create Date, removed time, and displayed Address and Employment -->
<!-- 09/16/2010  C. Shimek       #66766 Added link to allow user to reset casenote to draft status -->
<!-- 09/21/2010  C. Shimek       #67489 Revised bean define for "fullPathTiny" by removing ChangeFormActionURL to fix random SubmitAction errors -->
<!-- 09/21/2012  R. Capestani	 ER #74310 Casenotes - default Date Range to current date(PT) -->
<!-- 05/06/2013  R.Young	 	 ER #75492 RapidSpellWeb file changesV3.2.0 for CS --> 
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nest" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@page import="naming.UIConstants"%>
<%@page import="naming.Features"%>
<%@page import="ui.common.UIUtil"%>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->


<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />
<%-- Checks to make sure if the user is logged in. --%>
<%--msp:login / --%>
<meta http-equiv="Content-Language" content="en-us">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">

<!-- STYLE SHEET LINK -->
<link rel="stylesheet" type="text/css" href="/<msp:webapp/>css/base.css" />
<html:base />
<title><bean:message key="title.heading" /> - administerCasenotes/journalView.jsp</title>

<script type="text/javascript" src="/<msp:webapp/>js/app.js"></script> 
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/AnchorPosition.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/tinyMCECustomInitCasenote.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type="text/javascript">
	 
	function validateDateAndTime(el){
		var dateElemVal=document.getElementById("currentCasenote.casenoteDateAsStr").value;
		var dateToComp=getCurrentDate();
		var isFuture=compareDate1GreaterEqualDate2(dateToComp,dateElemVal);
		if(!isFuture){
			alert("Casenote Date cannot be for the future");
			document.getElementById("currentCasenote.casenoteDateAsStr").focus();
			return false;
		}
		else{
			var isDateNotEqual=compareDate1GreaterEqualDate2(dateElemVal,dateToComp);
			if(isDateNotEqual){    // true means dates are equal the only way that can happen
				//  Do Casenote Time check now
				var currentTime = new Date()
				var hours = currentTime.getHours()
				var minutes = currentTime.getMinutes()
				var myCurrTime=convertTimeto2DigitHr(getCurrentTime(false));
				var timeElemVal=document.getElementById("currentCasenote.casenoteTime").value;
				var ampmVal=document.getElementById("currentCasenote.AMPMId");
				var theTime = timeElemVal.split(':');
				var hr = theTime[0];
				if (ampmVal.selectedIndex == 1){
					if (theTime[0] < 12){
						hr = Number(theTime[0]) + 12;
					}	
				} else { 
					if (theTime[0] == 12){
						hr = "00";
					}
				} 
				timeElemVal = hr + ":" + theTime[1]; 
				if(timeElemVal>myCurrTime){
					alert("Casenote time cannot be a for a future time");
					document.getElementById("currentCasenote.casenoteTime").focus();
					return false;
				}
			}
		}
		if(document.getElementById("currentCasenote.collateralId").value!="" && document.getElementById("currentCasenote.collateralId").value=="AS"){
			var theAssocElem=el["currentCasenote.associateIds"];
			var theAssocVal=theAssocElem.options[theAssocElem.selectedIndex].value
															
			if(theAssocVal==""){
				alert("An Associate must be selected");
				theAssocElem.focus();
				return false;
			}
		}
		return true;
	}

	function presetAssociateCurrentCasenote(){
		<jims:if name="casenoteJournalForm" property="currentCasenote.action" op="equal" value="update">
		<jims:or name="casenoteJournalForm" property="currentCasenote.action" value="create" op="equal"/>
		<jims:then>
			var el=document.getElementById("currentCasenote.collateralId");
			if (el.options[el.selectedIndex].value=="AS"){
			
					show("currentNoteAssociateIdsSpan", 1, "inline");
					//document.getElementById("currentCasenote.associateIds").selectedIndex=0;
				}else	if (el.options[el.selectedIndex].value==""){
					show("currentNoteAssociateIdsSpan", 0)
					//document.getElementById("currentCasenote.associateIds").selectedIndex=0;
				}
				else {
					show("currentNoteAssociateIdsSpan", 0)
					//document.getElementById("currentCasenote.associateIds").selectedIndex=0;
				}
		</jims:then>
		</jims:if>
	
	}

	function renderAssociatesCurrentNoteSelect(el){
		if (el.options[el.selectedIndex].value=="AS"){
			show("currentNoteAssociateIdsSpan", 1, "inline");
			document.getElementById("currentCasenote.associateIds").selectedIndex=0;
		}else	if (el.options[el.selectedIndex].value==""){
			show("currentNoteAssociateIdsSpan", 0)
			document.getElementById("currentCasenote.associateIds").selectedIndex=0;
		}
		else {
			show("currentNoteAssociateIdsSpan", 0)
			document.getElementById("currentCasenote.associateIds").selectedIndex=0;
		}
	}

	function renderAssociatesSelect(el){
		if (el.options[el.selectedIndex].value=="AS"){
			show("associatesIdsSpan", 1, "inline")
			document.getElementById("searchCasenote.associateIds").selectedIndex=0;
		}
		else {
				show("associatesIdsSpan", 0)
				document.getElementById("searchCasenote.associateIds").selectedIndex=0;
		}
	}
	
	function clearAllFieldsInBasic(){
		document.getElementById("searchCasenote.generatedById").selectedIndex=0;
		document.getElementById("searchCasenote.createdByName.lastName").value="";
		document.getElementById("searchCasenote.createdByName.firstName").value="";
		document.getElementById("searchCasenote.casenoteBeginDateAsStr").value="";
		document.getElementById("searchCasenote.casenoteEndDateAsStr").value="";
		document.getElementById("searchCasenote.casenoteTypeId").selectedIndex=0;
		document.getElementById("searchCasenote.caseIds").selectedIndex=0;
		document.getElementById("searchCasenote.subjectIds").selectedIndex=0;
		document.getElementById("searchCasenote.collateralId").selectedIndex=0;
		document.getElementById("searchCasenote.associateIds").selectedIndex=0;
		document.getElementById("searchCasenote.court").value="";
	}

	function renderAdvancedFields(){
		var endDateStrElem=document.getElementById("searchCasenote.casenoteEndDateAsStr");
		var howGenSelect=document.getElementById("searchCasenote.generatedById");
		changeDropDownSysGen(howGenSelect.options[howGenSelect.selectedIndex].value, 'createdBy', 'row');
		var collateralSelect=document.getElementById("searchCasenote.collateralId");
		renderAssociatesSelect(collateralSelect);
		presetAssociateCurrentCasenote();
	//	if(endDateStrElem.value==""){
	//		endDateStrElem.value=getCurrentDate();
	//	}
	}
	
	function renderFields(el,noClear){
		presetAssociateCurrentCasenote();
		var theSelectedValue = el.options[el.selectedIndex].value
		if(noClear!="true"){
			clearAllFieldsInBasic();
		}
		
		el.focus();
		switch (theSelectedValue)
		{
			case "D":
			show("dateRangeSpan", 1)
			show("courtFieldSpan", 0)
			show("typeFieldSpan", 0)
			show("casesFieldSpan", 0)
			show("subjectFieldSpan", 0)
			show("collateralFieldSpan", 0)
			show("howGeneratedSpan", 0)
			show("createdByFieldSpan", 0)
			show("associatesIdsSpan", 0)
			if(noClear!="true"){
				document.getElementById("searchCasenote.casenoteEndDateAsStr").value=getCurrentDate();
				document.getElementById("searchCasenote.casenoteBeginDateAsStr").value=getCurrentDate();
			}
			break
	
			case "R":
			show("dateRangeSpan", 0)
			show("courtFieldSpan", 1)
			show("typeFieldSpan", 0)
			show("casesFieldSpan", 0)
			show("subjectFieldSpan", 0)
			show("collateralFieldSpan", 0)
			show("howGeneratedSpan", 0)
			show("createdByFieldSpan", 0)
			show("associatesIdsSpan", 0)
			break
	
			case "T":
			show("dateRangeSpan", 0)
			show("courtFieldSpan", 0)
			show("typeFieldSpan", 1)
			show("casesFieldSpan", 0)
			show("subjectFieldSpan", 0)
			show("collateralFieldSpan", 0)
			show("howGeneratedSpan", 0)
			show("createdByFieldSpan", 0)
			show("associatesIdsSpan", 0)
			break
	
			case "C":
			show("dateRangeSpan", 0)
			show("courtFieldSpan", 0)
			show("typeFieldSpan", 0)
			show("casesFieldSpan", 1)
			show("subjectFieldSpan", 0)
			show("collateralFieldSpan", 0)
			show("howGeneratedSpan", 0)
			show("createdByFieldSpan", 0)
			show("associatesIdsSpan", 0)
			break
	
			case "S":
			show("dateRangeSpan", 0)
			show("courtFieldSpan", 0)
			show("typeFieldSpan", 0)
			show("casesFieldSpan", 0)
			show("subjectFieldSpan", 1)
			show("collateralFieldSpan", 0)
			show("howGeneratedSpan", 0)
			show("createdByFieldSpan", 0)
			show("associatesIdsSpan", 0)
			break
	
			case "L":
			show("dateRangeSpan", 0)
			show("courtFieldSpan", 0)
			show("typeFieldSpan", 0)
			show("casesFieldSpan", 0)
			show("subjectFieldSpan", 0)
			show("collateralFieldSpan", 1)
			show("howGeneratedSpan", 0)
			show("createdByFieldSpan", 0)
			show("associatesIdsSpan", 0)
			break
	
			case "H":
			show("dateRangeSpan", 0)
			show("courtFieldSpan", 0)
			show("typeFieldSpan", 0)
			show("casesFieldSpan", 0)
			show("subjectFieldSpan", 0)
			show("collateralFieldSpan", 0)
			show("howGeneratedSpan", 1)
			show("createdByFieldSpan", 0)
			show("associatesIdsSpan", 0)
			var howGenSelect=document.getElementById("searchCasenote.generatedById");
			changeDropDownSysGen(howGenSelect.options[howGenSelect.selectedIndex].value, 'createdByFieldSpan', 'row');
			break
	
			default:
			show("dateRangeSpan", 0)
			show("courtFieldSpan", 0)
			show("typeFieldSpan", 0)
			show("casesFieldSpan", 0)
			show("subjectFieldSpan", 0)
			show("collateralFieldSpan", 0)
			show("howGeneratedSpan", 0)
			show("createdByFieldSpan", 0)
			show("associatesIdsSpan", 0)
		}
	}

	function changeDropDownSysGen(sysGenVal, idToShow, format){
		if(sysGenVal=="CB"){
			show(idToShow,1, format);
		}
		else{
			document.getElementById("searchCasenote.createdByName.lastName").value="";
			document.getElementById("searchCasenote.createdByName.firstName").value="";
			show(idToShow,0);

		}
	}

	function checkBasicSearchCriteria(el){
		clearAllValArrays();
		var theSelectedField = el["searchCasenote.searchById"];
		theSelectedValue = theSelectedField.options[theSelectedField.selectedIndex].value;
		if(theSelectedValue==""){
			alert("A Search By criteria must be selected.");
			theSelectedField.focus();
			return false;
		}
	
		switch (theSelectedValue)
		{
			case "D": 
				customValRequired("searchCasenote.casenoteEndDateAsStr", "End Date is required");
				customValRequired("searchCasenote.casenoteBeginDateAsStr", "Begin Date is required");
				addMMDDYYYYDateValidation("searchCasenote.casenoteEndDateAsStr", "End Date must be in the format MM/DD/YYYY");
				addMMDDYYYYDateValidation("searchCasenote.casenoteBeginDateAsStr", "Begin Date must be in the format MM/DD/YYYY");
				if(validateCustomStrutsBasedJS(el)){
					if(compareDate1GreaterEqualDate2(document.getElementById("searchCasenote.casenoteEndDateAsStr").value,document.getElementById("searchCasenote.casenoteBeginDateAsStr").value)){
						return true;
						
					}
					else{
						alert("Begin Date must be less than or equal to end date");
						document.getElementById("searchCasenote.casenoteBeginDateAsStr").focus();
						return false;
					}
				}
				else{
					return false;
				}
			break
	
			case "R": 
				customValRequired("searchCasenote.court", "Court is required");
				addAlphaNumericnSpaceValidation("searchCasenote.court", "Court must be alphanumeric.");
				return validateCustomStrutsBasedJS(el);
			break
	
			case "T": 
				customValRequired("searchCasenote.casenoteTypeId", "Type is required.");
				return validateCustomStrutsBasedJS(el);
			break

// no edit required as "All" option is always present	
			case "C":  
//				customValRequired("searchCasenote.caseIds", "Case is required.");
//				return validateCustomStrutsBasedJS(el);
			break
	
			case "S":  
				customValRequired("searchCasenote.subjectIds", "Subjects is required.");
				return validateCustomStrutsBasedJS(el);
			break
	
			case "L": 
				customValRequired("searchCasenote.collateralId", "Collateral is required.");
				if(validateCustomStrutsBasedJS(el)){
					if(document.getElementById("searchCasenote.collateralId").value!="" && document.getElementById("searchCasenote.collateralId").value=="AS"){
						var theAssocElem=el["searchCasenote.associateIds"];
						var theAssocVal=theAssocElem.options[theAssocElem.selectedIndex].value
					
						if(theAssocVal==""){
							alert("An Associate must be selected");
							theAssocElem.focus();
							return false;
						}
					}
				}
				else{
					return false;
				}
				
			break  
	     
			case "H":  
				customValRequired("searchCasenote.generatedById", "How Generated is required.");
				var theGenerElem=el["searchCasenote.generatedById"];
				var theGenerVal=theGenerElem.options[theGenerElem.selectedIndex].value
				if(theGenerVal==""){
					alert("How generated is required.");
					theGenerElem.focus();
					return false;
				}  
				if(theGenerVal=="CB"){
					customValRequired("searchCasenote.createdByName.lastName", "Last Name is required.");
					addSearchFieldValidation("searchCasenote.createdByName.lastName", "Last Name has invalid symbols.");
					addSearchFieldValidation("searchCasenote.createdByName.firstName", "First Name has invalid symbols.");
					customValMinLength("searchCasenote.createdByName.lastName", "Last Name must be at least 3 characters.","3");
					customValMinLength("searchCasenote.createdByName.firstName", "First Name must be at least 3 characters.","3");
					return validateCustomStrutsBasedJS(el);
				}
				else{
					return true;
				}
			break;
		}
	}
	
	
	function checkAdvancedSearchCriteria(el){
		clearAllValArrays();
		if(document.getElementById("searchCasenote.casenoteEndDateAsStr").value==""  &&
				document.getElementById("searchCasenote.casenoteBeginDateAsStr").value=="" &&
				document.getElementById("searchCasenote.court").value=="" &&
				document.getElementById("searchCasenote.casenoteTypeId").value == "" &&
				document.getElementById("searchCasenote.caseIds").value == "" &&
				document.getElementById("searchCasenote.subjectIds").value == "" &&
				document.getElementById("searchCasenote.collateralId").value == "" &&
				document.getElementById("searchCasenote.generatedById").value == ""){
			alert("At least one search criteria is required");
			return false;
		}
		
		if(document.getElementById("searchCasenote.casenoteEndDateAsStr").value!="" || document.getElementById("searchCasenote.casenoteBeginDateAsStr").value!=""){
			customValRequired("searchCasenote.casenoteEndDateAsStr", "End Date is required");
			customValRequired("searchCasenote.casenoteBeginDateAsStr", "Begin Date is required");
			addMMDDYYYYDateValidation("searchCasenote.casenoteEndDateAsStr", "End Date must be in the format MM/DD/YYYY");
			addMMDDYYYYDateValidation("searchCasenote.casenoteBeginDateAsStr", "Begin Date must be in the format MM/DD/YYYY");
			if(validateCustomStrutsBasedJS(el)){
				if(compareDate1GreaterEqualDate2(document.getElementById("searchCasenote.casenoteEndDateAsStr").value,document.getElementById("searchCasenote.casenoteBeginDateAsStr").value)){
					
				}
				else{
					alert("Begin Date must be less than or equal to end date");
					document.getElementById("searchCasenote.casenoteBeginDateAsStr").focus();
					return false;
				}
			}
			else{
				return false;
			}
			
		}
		clearAllValArrays();	
		if(document.getElementById("searchCasenote.court").value!=""){
			addAlphaNumericnSpaceValidation("searchCasenote.court", "Court must be alphanumeric.");
			if(validateCustomStrutsBasedJS(el)){
			}
			else{
				return false;	
			}
		}
		if(document.getElementById("searchCasenote.collateralId").value!="" && document.getElementById("searchCasenote.collateralId").value=="AS"){
					var theAssocElem=el["searchCasenote.associateIds"];
				var theAssocVal=theAssocElem.options[theAssocElem.selectedIndex].value
				
				if(theAssocVal==""){
					alert("An Associate must be selected");
					theAssocElem.focus();
					return false;
				}
		}
		clearAllValArrays();
		
		if(document.getElementById("searchCasenote.generatedById").value!=""){
					var theGenerElem=el["searchCasenote.generatedById"];
				var theGenerVal=theGenerElem.options[theGenerElem.selectedIndex].value
				
				if(theGenerVal=="CB"){
					customValRequired("searchCasenote.createdByName.lastName", "Last Name is required.");
					addSearchFieldValidation("searchCasenote.createdByName.lastName", "Last Name has invalid symbols.");
					addSearchFieldValidation("searchCasenote.createdByName.firstName", "First Name has invalid symbols.");
					customValMinLength("searchCasenote.createdByName.lastName", "Last Name must be at least 3 characters.","3");
					customValMinLength("searchCasenote.createdByName.firstName", "First Name must be at least 3 characters.","3");
					if(validateCustomStrutsBasedJS(el)){
					}
					else{
						return false;	
					}
				}
		}
		return true;
	}

	function printReport(casenoteId){
		var webApp = "/<msp:webapp/>";
		var url = webApp + 'displayAdministerCasenotesJournal.do?submitAction=Print&rrand=<%out.print((Math.random()*246));%>&&selectedCasenoteId=' + casenoteId;
		var newWin = window.open(url, "pictureWindow", "height=500,width=650,toolbar=no,scrollbars=yes,resizable=yes,status=yes,dependent=no");
		newWin.focus();
//		openWindow(webApp + 'displayAdministerCasenotesJournal.do?submitAction=Print&rrand=<%out.print((Math.random()*246));%>&&selectedCasenoteId=' + casenoteId);
	} 
	
</script>
</head>
<logic:notEqual name="casenoteJournalForm" property="searchCasenote.action" value="<%=UIConstants.ADVANCED%>">
	<body topmargin="0" leftmargin="0" onLoad="renderFields(document.forms[0]['searchCasenote.searchById'],'true');" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
</logic:notEqual>
<logic:equal name="casenoteJournalForm" property="searchCasenote.action" value="<%=UIConstants.ADVANCED%>">
	<body topmargin="0" leftmargin="0" onLoad="renderAdvancedFields()" onKeyDown="return checkEnterKeyAndSubmit(event,true);">
</logic:equal>
<bean:define id="webPath" type="java.lang.String">/<msp:webapp/></bean:define>
<html:form action="/displayAdministerCasenotesJournal" target="content">
<input type="hidden" name="helpFile" value="commonsupervision/CSCD_Casenotes/CSCD_Casenotes.htm#|4">
<!-- Begin Pagination Header Tag-->
<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 

<pg:pager
    index="center"
    maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
    maxIndexPages="10"
    export="offset,currentPageNumber=pageNumber"
    scope="request">
<!-- End Pagination header stuff -->
<jims:isAllowed requiredFeatures="<%=Features.CSCD_ASSESS_ACCESS%>">
	<bean:define id="allowAssessments" value="Y" />
</jims:isAllowed>
<div align="center">
<!-- BEGIN FULL PAGE TABLE -->
	<table width="98%" border="0" cellpadding="0" cellspacing="0" >
		<tr>
    		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
  		</tr>
  		<tr>
    		<td valign="top">
<!-- BEGIN BLUE TABS TABLE -->    		
				<table width="100%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
						<td valign="top">
							<!--tabs start-->
							<tiles:insert page="../common/commonSupervisionTabs.jsp" flush="true">
								<tiles:put name="tab" value="casenotesTab"/>
							</tiles:insert>		
							<!--tabs end-->
						</td>
					</tr>
					<tr>
						<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
				</table>
<!-- END BLUE TABS TABLE -->
<!-- BEGIN BLUE BORDER TABLE -->    				
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
					<tr>
						<td><img src="/<msp:webapp/>images/spacer.gif" height="5"></td>
					</tr>
					<tr>
						<td valign="top" align="center">
<!-- BEGIN HEADING TABLE -->
							<table width="100%">
								<tr>							
									<td align="center" class="header">
										<bean:message key="prompt.casenotes" />&nbsp;<bean:message key="prompt.list" /> 
									</td>	
								</tr>
								<tr align="center" >
									<td class="confirm">
										<logic:equal name="casenoteJournalForm" property="secondaryAction" value="<%=UIConstants.DELETE_SUCCESS%>">
											Casenote successfully deleted.
										</logic:equal>
										<logic:equal name="casenoteJournalForm" property="secondaryAction" value="<%=UIConstants.UPDATE_SUCCESS%>">
											Casenote successfully saved.
										</logic:equal>
										<logic:equal name="casenoteJournalForm" property="secondaryAction" value="<%=UIConstants.ASSOCIATE_CREATE_SUCCESS%>">
											Associate successfully added.
										</logic:equal>
										<logic:equal name="casenoteJournalForm" property="secondaryAction" value="<%=UIConstants.RESET_SUCCESS%>">
											Casenote successfully reset to draft.
										</logic:equal>
									</td>
								</tr>	
							</table>
<!-- END HEADING TABLE -->
<!-- BEGIN INSTRUCTIONS TABLE -->							
							<table width="98%" border="0">
								<tr>
									<td>
										<ul>
											<li>Search with basic search or Advanced Search or Create a Casenote.</li>
											<li>Save as Draft will allow you to edit the casenote within 24 hours. Click Finish to complete the casenote.</li>
										</ul>
									</td>
								</tr>
								<tr>
									<td class="required" colspan="2"><bean:message key="prompt.requiredFields"/>&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction"/></td>
								</tr>
							</table>
<!-- END INSTRUCTIONS TABLE -->
							<tiles:insert page="../common/superviseeInfoHeader.jsp" flush="true">
							</tiles:insert>		
							<br>
						<!-- common script for calendarPopup -->
							<SCRIPT LANGUAGE="JavaScript" ID="js1">
								var cal1 = new CalendarPopup();
								cal1.showYearNavigation();
							</SCRIPT>
	<!-- /////////////////////////  BEGIN  ADVANCED SEARCH /////////////////////////////////-->
							<nest:nest property="searchCasenote">
								<nest:equal property="action" value="<%=UIConstants.ADVANCED%>">
<!-- BEGIN ADVANCED SEARCH INSTRUCTION TABLE -->
									<table width="98%" border="0">
										<tr>
											<td class="required" colspan="2"> <bean:message key="prompt.2.diamond" /> At least 1 required&nbsp;&nbsp; +Last Name required if searching by First Name  *All date fields must be in the format of mm/dd/yyyy.</td>
										</tr>
									</table>
<!-- END ADVANCED SEARCH INSTRUCTION TABLE -->	
<!-- BEGIN ADVANCED SEARCH BORDER TABLE -->								
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr>
											<td class="detailHead">Search Casenotes&nbsp;&nbsp;&nbsp;&nbsp;<a href="/<msp:webapp/>displayAdministerCasenotesJournal.do?submitAction=<bean:message key="button.basicSearch"/>"><bean:message key="button.basicSearch"/></a></td>
										</tr>
										<tr>
											<td>
<!-- BEGIN ADVANCED SEARCH TABLE -->												
												<table width="100%" border="0" cellspacing="1" cellpadding="2">
													<tr>
														<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.dateRange"/></td>
														<td class="formDe">
															<table cellpadding="2" cellspacing="1">
																<tr>
																	<td class="formDeLabel"><bean:message key="prompt.begin"/></td>
																	<td></td>
																	<td class="formDeLabel"><bean:message key="prompt.end"/></td>
																</tr>
																<tr class="formDe">
																	<td>
																	<nest:text property="casenoteBeginDateAsStr" maxlength="10" size="10" />
																		<A HREF="#" onClick="cal1.select(document.getElementById('searchCasenote.casenoteBeginDateAsStr'),'anchor1','MM/dd/yyyy'); return false;"
																		NAME="anchor1" ID="anchor1" border="0"><bean:message key="prompt.2.calendar"/></A>
																	</td>
																	<td>-</td>
																	<td>
																		<nest:text property="casenoteEndDateAsStr" maxlength="10" size="10" />
																			<A HREF="#" onClick="cal1.select(document.getElementById('searchCasenote.casenoteEndDateAsStr'),'anchor2','MM/dd/yyyy'); return false;"
																				NAME="anchor2" ID="anchor2" border="0"><bean:message key="prompt.2.calendar"/></A>
																	</td>
																</tr>
															</table>
														</td>
														<td class="formDeLabel"><bean:message key="prompt.court"/></td>
														<td class="formDe">
															<nest:text property="court" size="5"/>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.collateral"/></td>
														<td class="formDe" colspan="3" valign="top">
															<table cellpadding="2" cellspacing="2">
																<tr>
																	<td valign="top">
																		<nest:select property="collateralId" onchange="renderAssociatesSelect(this)">
																			<html:option value=""><bean:message key="select.generic" /></html:option>
																			<nest:optionsCollection property="collateralList" value="code" label="description" />
																		</nest:select>
																	</td>
																	<td >
																	<span id="associatesIdsSpan" class="hidden">
																		<nest:select property="associateIds" size="3" multiple="true">
																			<html:option value=""><bean:message key="select.generic" /></html:option>
																			<html:optionsCollection  name="casenoteJournalForm" property="associatesList" value="associateId" label="displayLabel" />
																		</nest:select>
																	</span>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.howGenerated"/></td>
														<td class="formDe" colspan="3">
															<nest:select property="generatedById" onchange="changeDropDownSysGen(this.value, 'createdBy', 'row')">
																<html:option value=""><bean:message key="select.generic" /></html:option>
																<nest:optionsCollection property="generatedByList" value="code" label="description" />
															</nest:select>
														</td>
													</tr>
													<tr id="createdBy" class="hidden">
														<td class="formDeLabel"><bean:message key="prompt.createdBy"/></td>
														<td class="formDe" colspan="3">
															<table cellpadding="2" cellspacing="1">
																<tr class="formDeLabel">
																	<td class="formDeLabel"><bean:message key="prompt.plusSign"/><bean:message key="prompt.lastName" /></td>
																	<td class="formDeLabel"><bean:message key="prompt.firstName" /></td>
																</tr>
																<tr>
																	<td class="formDe"><nest:text property="createdByName.lastName" maxlength="30" size="30"/></td>
																	<td class="formDe"><nest:text property="createdByName.firstName" maxlength="30" size="30"/></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.casenoteType"/></td>
														<td class="formDe">
															<nest:select property="casenoteTypeId" >
																<html:option value=""><bean:message key="select.generic" /></html:option>
																<nest:optionsCollection property="casenoteTypeList" value="code" label="description" />
															</nest:select>
														</td>
														<td class="formDeLabel" valign="top">Cases</td>
														<td class="formDe" valign="top">
															<nest:select property="caseIds" onchange="multiSelectFix(this)" multiple="true" >
																<html:option value=""><bean:message key="select.generic" /></html:option>
																<nest:optionsCollection property="casesList" value="cdiNCaseNum" label="caseNCourt" /> 
															</nest:select>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel" valign="top" width="1%" nowrap="nowrap"><bean:message key="prompt.casenoteSubjects"/></td>
														<td class="formDe" valign="top" colspan="3">
															<nest:select property="subjectIds" onchange="multiSelectFix(this)" multiple="true">
																<html:option value=""><bean:message key="select.generic" /></html:option>
																<nest:optionsCollection property="casenoteSubjectList" value="code" label="description" /> 
															</nest:select>
														</td>
													</tr>
													<tr align="center">
														<td class="formDe" colspan="4">
															<html:submit property="submitAction" onclick="return (checkAdvancedSearchCriteria(this.form) && disableSubmit(this, this.form))"><bean:message key="button.search"/></html:submit>	
															<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.viewAll"/></html:submit>	
															<jims:isAllowed requiredFeatures="CS-CASENOTE-CREATE">		
																<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.createCasenote"/></html:submit>	
															</jims:isAllowed>
														</td>
													</tr>
												</table>
<!-- END ADVANCED SEARCH TABLE -->												
											</td>
										</tr>
									</table>
<!-- END ADVANCED SEARCH BORDER TABLE -->										
								</nest:equal>
				<!--  ////////////////// END ADVANCED SEARCH ////////////////////////////////-->
											
				<!-- /////////////////// BEGIN BASIC SEARCH /////////////////////////////////-->
								<nest:notEqual property="action" value="<%=UIConstants.ADVANCED%>">
<!-- BEGIN BASIC SEARCH BORDER TABLE -->									
									<table width="98%" border="0" cellpadding="2" cellspacing="0" class="borderTableBlue">
										<tr>
											<td class="detailHead">Search Casenotes&nbsp;&nbsp;&nbsp;&nbsp;<!-- <a href="/<msp:webapp/>displayAdministerCasenotesJournal.do?submitAction=<bean:message key="button.advancedSearch"/>"><bean:message key="button.advancedSearch"/></a> --></td>
										</tr>
										<tr>
											<td>
												<span id="basicSearch">
<!-- BEGIN BASIC SEARCH TABLE -->												
													<table width="100%" border="0" cellpadding="2" cellspacing="1">
														<tr>
															<td  valign="top" class="formDeLabel" nowrap="nowrap" width="1%">Search By:
																<nest:select property="searchById" onchange="renderFields(this)">
																	<html:option value=""><bean:message key="select.generic" /></html:option>
																	<nest:optionsCollection property="searchByList" value="code" label="description" />
																</nest:select>
															</td>
															<td class="formDe" valign="top">
																<span id="howGeneratedSpan" class="hidden">
																	<bean:message key="prompt.2.diamond" />
																		<nest:select property="generatedById" onchange="changeDropDownSysGen(this.value, 'createdByFieldSpan', '')">
																			<html:option value=""><bean:message key="select.generic" /></html:option>
																		<nest:optionsCollection property="generatedByList" value="code" label="description" />
																		</nest:select>
																		<div class="hidden" id="createdByFieldSpan">
<!-- BEGIN CREATED BY TABLE -->																		
																			<table cellpadding="2" cellspacing="1">
																				<tr class="formDeLabel">
																					<td ><bean:message key="prompt.2.diamond" /><bean:message key="prompt.lastName" /></td>
																					<td ><bean:message key="prompt.firstName" /></td>
																				</tr>
																				<tr>
																					<td><nest:text property="createdByName.lastName" maxlength="30" size="30"/></td>
																					<td ><nest:text property="createdByName.firstName" maxlength="30" size="30"/></td>
																				</tr>
																			</table>
<!-- END CREATED BY TABLE -->																			
																		</div>
																</span>
																<span id="dateRangeSpan" class="hidden">
<!-- BEGIN DATE SEARCH TABLE -->																
																	<table cellpadding="2" cellspacing="1">
																		<tr>
																			<td class="formDeLabel"><bean:message key="prompt.2.diamond" />Begin</td>
																			<td></td>
																			<td class="formDeLabel"><bean:message key="prompt.2.diamond" />End</td>
																		</tr>
																		<tr class="formDe">
																			<td>
																				<nest:text property="casenoteBeginDateAsStr" maxlength="10" size="10" />
																					<A HREF="#" onClick="cal1.select(document.getElementById('searchCasenote.casenoteBeginDateAsStr'),'anchor1','MM/dd/yyyy'); return false;"
																						NAME="anchor1" ID="anchor1" border="0"><img border="0"
																						src="/<msp:webapp/>images/calendar2.gif" align="middle" /></A>(mm/dd/yyyy)
																			</td>
																			<td>-</td>
																			<td>
																				<nest:text property="casenoteEndDateAsStr" maxlength="10" size="10" />
																					<A HREF="#" onClick="cal1.select(document.getElementById('searchCasenote.casenoteEndDateAsStr'),'anchor2','MM/dd/yyyy'); return false;"
																						NAME="anchor2" ID="anchor2" border="0"><img border="0"
																						src="/<msp:webapp/>images/calendar2.gif" align="middle" /></A>(mm/dd/yyyy)
																			</td>
																		</tr>
																	</table>
<!-- END DATE SEARCH TABLE -->																	
																</span>
																<span id="courtFieldSpan" class="hidden">
																	<bean:message key="prompt.2.diamond" /><nest:text property="court" size="5"/>
																</span>
																<span id="typeFieldSpan" class="hidden"><bean:message key="prompt.2.diamond" />
																	<nest:select property="casenoteTypeId" >
																		<html:option value=""><bean:message key="select.generic" /></html:option>
																		<nest:optionsCollection property="casenoteTypeList" value="code" label="description" />
																	</nest:select>
																</span>
																<span id="casesFieldSpan" class="hidden">
																	<nest:select property="caseIds" onchange="multiSelectFix(this)" multiple="true">
																		<html:option value=""><bean:message key="select.all" /></html:option>
																	    <nest:optionsCollection property="casesList" value="cdiNCaseNum" label="caseNCourt" /> 
																	</nest:select> 
																</span>
																<span id="subjectFieldSpan" class="hidden">
<!-- BEGIN SUBJECT TABLE -->																
																	<table cellpadding="0" cellspacing="0">
																		<tr>
																			<td width="1%"><bean:message key="prompt.2.diamond" /></td>
																			<td>
																				<nest:select property="subjectIds" onchange="multiSelectFix(this)" multiple="true">
																					<html:option value=""><bean:message key="select.generic" /></html:option>
																					<nest:optionsCollection property="casenoteSubjectList" value="code" label="description" /> 
																				</nest:select>
																			</td>
																		</tr>
																	</table>
<!-- END SUBJECT SEARCH TABLE -->																	
																</span>
																<span id="collateralFieldSpan" class="hidden">
<!-- BEGIN COLLATERAL TABLE -->																
																	<table cellpadding="0" cellspacing="0">
																		<tr>
																			<td valign="top">
																				<bean:message key="prompt.2.diamond" />
																					<nest:select property="collateralId" onchange="renderAssociatesSelect(this)">
																						<html:option value=""><bean:message key="select.generic" /></html:option>
																						<nest:optionsCollection property="collateralList" value="code" label="description" />
																					</nest:select>
																			</td>
																			<td>
																				<span id="associatesIdsSpan" class="hidden">
																					<nest:select property="associateIds" size="3" multiple="true">
																						<html:option value=""><bean:message key="select.generic" /></html:option>
																						<html:optionsCollection  name="casenoteJournalForm" property="associatesList" value="associateId" label="displayLabel" />
																					</nest:select>
																				</span>
																			</td>
																		</tr>
																	</table>
<!-- END COLLATERAL TABLE -->																			
																</span>
															</td>
															<td class="formDe" valign="top"></td>																																
														</tr>
														<tr align="center">
															<td class="formDe" colspan="3">
																<html:submit property="submitAction" onclick="return (checkBasicSearchCriteria(this.form) && disableSubmit(this, this.form))"><bean:message key="button.search"/></html:submit>	
																<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.viewAll"/></html:submit>	
																<jims:isAllowed requiredFeatures="CS-CASENOTE-CREATE">	
																	<html:submit property="submitAction" onclick="return disableSubmit(this, this.form)"><bean:message key="button.createCasenote"/></html:submit>	
																</jims:isAllowed>
															</td>
														</tr>
													</table>
<!-- END BASIC SEARCH TABLE -->													
												</span>
											</td>
										</tr>
									</table>
<!-- END BASIC SEARCH BORDER TABLE -->									
								</nest:notEqual>
				<!-- /////////////////// END BASIC SEARCH /////////////////////////////////-->								
							</nest:nest>
				<!-- /////////////////// BEGIN ADD CASENOTE ///////////////////////////////-->											
							<nest:nest property="currentCasenote">
								<jims:if name="casenoteJournalForm" property="currentCasenote.action" op="equal" value="update">
								<jims:or name="casenoteJournalForm" property="currentCasenote.action" value="create" op="equal"/>
								<jims:then>
									<span id="addCN" >
										<br>
										<a name="add"></a>
<!-- BEGIN ADD CASENOTE BORDER TABLE -->										
										<table width="98%" border="0" cellspacing="0" cellpadding="2" class="borderTableBlue">
											<tr class="detailHead">
												<td><bean:message key="prompt.new"/> <bean:message key="prompt.casenote"/> <bean:message key="prompt.information"/></td>
											</tr>
											<tr>
												<td>
<!-- BEGIN ADD CASENOTE TABLE -->												
												<table width="100%" cellpadding="2" cellspacing="1">
													<tr>
														<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.casenoteDate"/></td>
														<td class="formDe">
															<nest:text property="casenoteDateAsStr" maxlength="10" size="10" />
																<A HREF="#" onClick="cal1.select(document.getElementById('currentCasenote.casenoteDateAsStr'),'anchor4','MM/dd/yyyy'); return false;"
																	NAME="anchor4" ID="anchor4" border="0"><img border="0"
																	src="/<msp:webapp/>images/calendar2.gif" align="middle" /></A>
															<script>
																var elem1=document.getElementById('currentCasenote.casenoteDateAsStr');
																if (elem1.value == null || elem1.value == ""){
																	elem1.value = getCurrentDate();  
																}
															</script>
														</td>
														<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.casenoteTime"/></td>
														<td class="formDe">
															<nest:text size="5" maxlength="5" property="casenoteTime"/>
															<nest:select property="AMPMId">
																<option value="AM">AM</option>
																<option value="PM">PM</option>
															</nest:select>  
															<script>
																var ap = "";
																var elem2=document.getElementById('currentCasenote.casenoteTime');
																if (elem2.value > ""){
																	ap = elem2.value;;
																} else {	
																	elem2.value = getCurrentTime12Hour();
																	ap = convertTimeto2DigitHr(getCurrentTime(false));
																}
																var hm = ap.split(":");
																if (hm[0] > 11){
																	var elem3 = document.getElementById("currentCasenote.AMPMId");
																	elem3.selectedIndex = 1;
																}
																if (hm[0] > 12){
																	var hr = hm[0] - 12;
																	var min = hm[1].toString();
																	if (hr < 10){
																		hr = "0" + hr;
																	}	
																	elem2.value = hr + ":" + min; 
																}	
	//																elem2.value = convertTimeto2DigitHr(getCurrentTime(false));
															</script>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.2.diamond" />Contact Method</td>
														<td class="formDe" colspan="3">
															<nest:select property="contactMethodId" >
																<html:option value=""><bean:message key="select.generic" /></html:option>
																<nest:optionsCollection property="contactMethodList" value="code" label="description" /> 
															</nest:select>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel" valign="top" width="1%" nowrap="nowrap"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.casenoteSubjects"/></td>
														<td class="formDe" colspan="3">
															<nest:select property="subjectIds"  onchange="multiSelectFix(this)" multiple="true">
																<html:option value=""><bean:message key="select.generic" /></html:option>
																<nest:optionsCollection property="casenoteSubjectList" value="code" label="description" /> 
															</nest:select>
														</td>
													</tr>
													<tr>
														<td class="formDeLabel"><bean:message key="prompt.collateral"/></td>
														<td class="formDe" colspan="3">
<!-- BEGIN ADD CASENOTE COLLATERAL TABLE -->														
															<table cellpadding="2" cellspacing="0">
																<tr>
																	<td valign="top">
																		<nest:select property="collateralId" onchange="renderAssociatesCurrentNoteSelect(this)">
																			<html:option value=""><bean:message key="select.generic" /></html:option>
																			<nest:optionsCollection property="collateralList" value="code" label="description" />
																		</nest:select>
																	</td>
																	<td >
																		<span id="currentNoteAssociateIdsSpan" class="hidden">
																			<nest:select property="associateIds" size="3" multiple="true">
																				<html:option value=""><bean:message key="select.generic" /></html:option>
																				<html:optionsCollection  name="casenoteJournalForm" property="currentCasenote.associateList" value="associateId" label="displayLabel" />
																			</nest:select>
																			<a href="javascript:changeFormActionURL(document.forms[0].name,'/<msp:webapp/>handleAssociateCasenoteDisplayOptions.do?submitAction=<bean:message key="button.link"/>&selectedValue=<bean:write name="casenoteJournalForm" property="superviseeId" />&fromPath=<%=UIConstants.FROM_CASENOTES%>',true)">
																				<bean:message key="button.addAssociate"/>
																			</a>
																		</span>
																	</td>
																</tr>
															</table>
<!-- END ADD CASENOTE COLLATERAL TABLE -->																
														</td>
													</tr>
													<tr>
														<td colspan="4" class="formDeLabel"><bean:message key="prompt.2.diamond" /><bean:message key="prompt.casenote"/></td>
													</tr>
												</table>
<!-- END ADD CASENOTE INFO TABLE -->
<!-- BEGIN ADD CASENOTE TEXT TABLE -->													
												<table width="100%" cellpadding="2" cellspacing="0">
												    <style>
														.mceEditor{
														width: "100%";
														height: "150"
														}
													</style>
													<tr class="formDe">
														<td>
															<nest:define id="userAgency" name="casenoteJournalForm" property="userAgency"/>
															<html:textarea styleClass="mceEditor" name="casenoteJournalForm" property="currentCasenote.casenoteText" style="width:100%" rows="15" ondblclick="myReverseTinyMCEFix(this)" />
																<tiles:insert page="../common/spellCheckButtonTile.jsp" flush="false">
																	<tiles:put name="agencyCode"><%=userAgency%></tiles:put>
																</tiles:insert>
														</td>
													</tr>
												</table>
<!-- END ADD CASENOTE TEXT TABLE -->													
											</td>
										</tr>
									</table>
<!-- END ADD CASENOTE BORDER TABLE -->	
<!-- BEGIN ADD CASENOTE BUTTON TABLE -->									
									<table cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<script>
													function trimCasenote(){
														document.getElementsByName("currentCasenote.casenoteText")[0].value = trimTextarea(document.getElementsByName("currentCasenote.casenoteText")[0].value); 																											
														myReverseTinyMCEFix(document.getElementsByName("currentCasenote.casenoteText")[0]);														
														return true;
													}													
													
													customValRequired("currentCasenote.casenoteDateAsStr","Casenote Date is required","");
													addMMDDYYYYDateValidation("currentCasenote.casenoteDateAsStr","Casenote Date must be in the MM/DD/YYYY format","");
													add12HrTimeValidation("currentCasenote.casenoteTime","Casenote Time is not in proper 12 hour format, ie 03:15","");
													customValRequired("currentCasenote.casenoteTime","Casenote Time is required","");
													customValRequired("currentCasenote.contactMethodId", "Contact Method is required","");
													customValRequired("currentCasenote.subjectIds","Casenote Subjects are required","");
													customValRequired("currentCasenote.casenoteText","Casenote Text is required","");
													addDefinedTinyMCEFieldMask("currentCasenote.casenoteText","Casenote Text cannot have % or _ entries","");
													customValMaxLength("currentCasenote.casenoteText","Casenote Text cannot be more than 3500 characters","7000");
												</script>											
													
												<bean:define id="fullPath">changeFormActionURL(this.form.name,'/<msp:webapp/>submitAdministerCasenotesJournal.do',false)</bean:define>
												<bean:define id="fullPathTiny">return (myTinyMCEFix() && trimCasenote() && validateCustomStrutsBasedJS(this.form) && validateDateAndTime(this.form) && disableSubmit(this, this.form))</bean:define>
												<html:submit property="submitAction" onclick="<%=fullPathTiny%>"> <bean:message key="button.saveAsDraft"/></html:submit>
												<html:submit property="submitAction" onclick="<%=fullPathTiny%>"><bean:message key="button.finish"/></html:submit>
												<html:reset value="Reset"/>
												<html:submit property="submitAction" onclick="<%=fullPath%>"><bean:message key="button.cancel"/></html:submit>
											</td>
										</tr>
									</table>
<!-- END ADD CASENOTE BUTTON TABLE -->										
								</span>
							</jims:then>
							</jims:if>
						</nest:nest>
				<!-- /////////////////// END ADD CASENOTE ///////////////////////////////-->									
						<br>
				<!-- /////////////////// BEGIN CASENOTE JOURNAL /////////////////////////-->
						<nest:nest property="searchCasenote">
							<nest:empty property="casenoteResults">
								<table width="98%" border="0" cellpadding="2" cellspacing="1">
									<tr  id="confirm1">
										<td class="detailHead">No Casenotes to Display</td>
									</tr>
								</table>
							</nest:empty>
										
							<nest:notEmpty property="casenoteResults">
<!-- BEGIN CASENOTE JOURNAL TABLE -->								
								<table width="98%" border="0" cellpadding="2" cellspacing="1">											
									<nest:iterate id="casenoteList" property="casenoteResults">											
<!-- Begin Pagination item wrap -->
  										<pg:item>
  										<tr>
											<td class="detailHead"><bean:message key="prompt.casenoteDate"/></td>
											<td class="detailHead"><bean:message key="prompt.subject"/></td>
											<td class="detailHead"><bean:message key="prompt.type"/></td>
											<td class="detailHead"><bean:message key="prompt.contactMethod"/></td> 
											<td class="detailHead"><bean:message key="prompt.createdBy"/></td>
											<td class="detailHead"><bean:message key="prompt.createDate"/></td>
										</tr>
										<tr id="confirm2" >
											<td class="formDe">
										<%-- 	<nest:write property="casenoteDateAsString"/>&nbsp;<nest:write property="casenoteTime"/> --%>
												<nest:write property="casenoteDate" formatKey="date.format.mmddyyyy" />
											</td>
											<td class="formDe"><nest:write property="subjects"/></td>
											<td class="formDe"><nest:write property="casenoteType"/></td>
											<td class="formDe"><nest:write property="contactMethod"/></td>
											<td class="formDe">
											  	<nest:equal  property="generatedById" value="MI">
											       <nest:write property="migrateCreator"/>
											   	</nest:equal>
											   	<nest:notEqual property="generatedById" value="MI">
											       <nest:write property="createdByName.formattedName"/>
											    </nest:notEqual>
												<nest:equal property="autoSaved" value="true">
													<img src="../../images/clip_image001.gif" title="This casenote was autosaved">
												</nest:equal>
											</td>
											<td class="formDe">
												<nest:notEqual property="generatedById" value="MI"> 
											       <nest:write property="createDate" formatKey="date.format.mmddyyyy" />
											   </nest:notEqual>
											</td>    
										</tr>
										<tr 
											<nest:equal property="casenoteStatusId" value="D">
												class="draftCasenote"
											</nest:equal>
										>
											<td class="borderTableBlue" colspan="6">
											    <nest:write property="collateralInfo" filter="false" />
												<nest:write property="casenoteText" filter="false" />
													<div align="center">
<!-- BEGIN CASENOTE JOURNAL ACTION(HREF) TABLE -->													
														<table width="100%">
														   <nest:equal property="casenoteStatusId" value="D">
																<tr>
																	<td width="80%"></td>
																	<td width="5%" align="center">
																		<nest:equal property="allowEdit" value="true">
																			<a href='/<msp:webapp/>displayAdministerCasenotesJournal.do?submitAction=Edit&selectedValue=<nest:write property="casenoteId" />'>Edit</a>
																		</nest:equal>
																	</td>
																	<td width="10%" align="center"> 
																		<nest:equal property="allowDelete" value="true">
																			<a href="javascript:confirmDeleteLink('Are you sure you want to delete this casenote?', '/<msp:webapp/>displayAdministerCasenotesJournal.do?submitAction=Delete&selectedValue=<nest:write property="casenoteId" />')">Delete</a>																																	
																		</nest:equal>
																	</td>
																	<td width="5%" align="center">
																 		<a href="javascript:printReport('<nest:write property="casenoteId" />' )">Print</a> 
																	</td>
																</tr>
															</nest:equal>
															<nest:notEqual property="casenoteStatusId" value="D">
																<tr>
																	<td width="73%"></td>
																	<td width="12%" align="center"> 
																		<jims:isAllowed requiredFeatures="CS-CASENOTE-DELETE">
																			<nest:equal property="allowDelete" value="true">
													 							<a href="javascript:confirmDeleteLink('Are you sure you want to reset this casenote to draft?', '/<msp:webapp/>displayAdministerCasenotesJournal.do?submitAction=Reset&selectedValue=<nest:write property="casenoteId" />')">Reset to Draft</a>																																	
																			</nest:equal>
																		</jims:isAllowed>	
																	</td>
																	<td width="10%" align="center">
																		<jims:isAllowed requiredFeatures="CS-CASENOTE-DELETE">	
																			<nest:equal property="allowDelete" value="true">
																				<a href="javascript:confirmDeleteLink('Are you sure you want to delete this casenote?', '/<msp:webapp/>displayAdministerCasenotesJournal.do?submitAction=Delete&selectedValue=<nest:write property="casenoteId" />')">Delete</a>																																	
																			</nest:equal>
																		</jims:isAllowed>	
																	</td>
																	<td width="5%" align="center">
																		<a href="javascript:printReport('<nest:write property="casenoteId" />' )">Print</a> 
																	</td>
																</tr>
															</nest:notEqual>
														</table>
<!-- END CASENOTE JOURNAL ACTION(HREF) TABLE -->															
													</div>
												</td>
											</tr>
											<tr id="confirm4">
												<td colspan="6" height="5px"></td>
											</tr>
										</pg:item>
  									 <!-- End Pagination item wrap -->
									</nest:iterate>
								</table>
<!-- END CASENOTE JOURNAL TABLE -->								
<!-- BEGIN PAGINATION NAVIGATION TABLE -->
								<table align="center">
									<tr>
										<td>
											<pg:index>
												<tiles:insert page="/jsp/jimsPagination.jsp" flush="false">
													<tiles:put name="pagerUniqueName" value="pagerSearch"/>
													<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
												</tiles:insert>
									 		</pg:index>
									    </td>
								    </tr>
							    </table>
<!-- END PAGINATION NAVIGATION TABLE -->
<!-- BEGIN CASENOTE JOURNAL ICON TABLE -->
								<table cellpadding="0" cellspacing="0" border="0" width="98%">
									<tr>
										<td class="legendSmallText"><span class="draftCasenote">Casenotes</span> are Draft and can be edited. </td>
									</tr>
									<tr>
										<td class="legendSmallText"><span class="casenoteNoncompliant">Casenotes</span> concern noncompliance. </td>
									</tr>
								</table>
<!-- END CASENOTE JOURNAL ICON TABLE -->								
							</nest:notEmpty>
						</nest:nest>
<!-- BEGIN CASENOTE JOURNAL BUTTON TABLE -->
						<table border="0" width="100%">
							<tr>
								<td align="center">
										<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
										<html:button property="submitAction" onclick="printReport('')" ><bean:message key="button.print"/></html:button>
										<input type="button" value="Cancel" name="cancel" onClick="goNav('/<msp:webapp/>displayAdministerCasenotesSearch.do?submitAction=Link')">
									</td>
								</tr>
							</table>
<!-- END CASENOTE JOURNAL BUTTON TABLE -->
						</td>
					</tr>
				</table>
<!-- END BLUE BORDER TABLE -->				
			</td>
		</tr>
	</table>
<!-- END FULL PAGE TABLE -->
</div>
<!-- Begin Pagination Closing Tag -->
</pg:pager>
<!-- End Pagination Closing Tag -->
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>