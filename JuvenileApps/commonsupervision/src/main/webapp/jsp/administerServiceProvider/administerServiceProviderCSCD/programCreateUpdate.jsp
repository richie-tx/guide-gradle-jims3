<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- Used for Service Provider Creation (CSCD)-->
<!--MODIFICATIONS -->
<!-- DWilliamson 11/16/2007	Create JSP -->
<!-- CShimek     12/10/2008 defect#55788 increased office hours input size from 10 to 30 to match PT, maxlength ok -->
<!-- CShimek     06/09/2009 defect#60094 corrected js null value error prevented setFocus() from being invoked -->
<!-- CShimek     07/31/2009 #61289 added display fields Division, Program Unit, Incarciration Program and Price when page used as Investigate, suspend, activate and inactivate -->
<!-- CShimek     04/30/2010 fixed cosmetic issue to page title, had multiple hyphens displaying. Found by SEK -->
<!-- CShimek     05/20/2010 #65511 revised to display name as input for update for active status. --> 
<!-- RYoung  01/29/2012 Defect#74823 CSCD: Add Filter to exclude inactive code on create program --> 
 
<!--TAG LIBRARIES NEEDED FOR STRUTS -->
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<!--TAG LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ page import="naming.PDCodeTableConstants" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims2" %>

<%@ page import="org.apache.struts.action.Action" %> 
<%@ page import="org.apache.struts.action.ActionErrors" %>

<!--CUSTOM LIBRARIES NEEDED FOR PAGE -->
<%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %>

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
<title><bean:message key="title.heading"/> administerServiceProviderCSCD - programCreateUpdate.jsp</title>
<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/date.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/common_supervision_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/customStrutsBasedValidation.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/CalendarPopup.js"></script>
<script type='text/javaScript' src="/<msp:webapp/>js/AnchorPosition.js"></script>

<logic:equal name="cscServiceProviderProgramForm" property="action" value="update">
	<bean:define id="inUpdateORCreate" value="true"/>
</logic:equal>

<logic:equal name="cscServiceProviderProgramForm" property="action" value="create">
	<bean:define id="allowKeyChanges" value="true" />

	<logic:notPresent name="inUpdateORCreate">
		<bean:define id="inUpdateORCreate" value="true" />
	</logic:notPresent>
</logic:equal>

<logic:notPresent name="allowKeyChanges">
	<logic:equal name="cscServiceProviderProgramForm" property="action" value="update">
		<logic:equal name="cscServiceProviderProgramForm" property="changeToStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_PENDING%>">
			<bean:define id="allowKeyChanges" value="true" />
		</logic:equal>
		<logic:equal name="cscServiceProviderProgramForm" property="changeToStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_ACTIVE%>">
			<bean:define id="allowNameChange" value="true" />
		</logic:equal>
	</logic:equal>
</logic:notPresent>

<script type="text/javascript">


function subgroup(id, name)
{
	this.id = id;
	this.name = name;
	this.childEvents = new Array();
}
var pgmGroups = new Array();
var programTypes = new Array();

<logic:iterate indexId="groupIterIndex" id="groupIter" name="cscServiceProviderSearchForm" property="programHeirarchyList">
	pgmGroups[<bean:write name="groupIterIndex"/>] = new subgroup("<bean:write name="groupIter" property="parentCd" />", "<bean:write name="groupIter" property="parentDesc"/>");

	<logic:iterate indexId="groupIterIndex2" id="groupIter2" name="groupIter" property="childEvents">
		var innerGroup = new subgroup("<bean:write name="groupIter2" property="code" />", "<bean:write name="groupIter2" property="description"/>");
		pgmGroups[<bean:write name="groupIterIndex"/>].childEvents[<bean:write name="groupIterIndex2"/>] = innerGroup;
	</logic:iterate>
</logic:iterate >

function loadPgmTypes(el)
{
	var selId = el.options[el.selectedIndex].value;
	var pgms = document.getElementsByName("programTypeId");

	pgms[0].options.length = 0;
	pgms[0].options[0] = new Option( "Please Select", "", false, false );
	pgms[0].disabled = false;
	
	if(el.selectedIndex == 0)
	{
		pgms[0].selectedIndex = 0; //reset choice back to default
		pgms[0].value="";
		pgms[0].disabled = true; //disable group2 choice		
		return;
	}
 
	for(i in pgmGroups)
	{

	if(pgmGroups[i].id == selId)
		{
			for(j in pgmGroups[i].childEvents)
			{
				pgms[0].options[pgms[0].options.length] = new Option( pgmGroups[i].childEvents[j].name, pgmGroups[i].childEvents[j].id);
			}
			break;	
		}
	}
}
function setFormNameValues()
{	
	//set division name
	var selected_division_name = document.getElementById("selectedDivisionName");	
	var selected_division = document.getElementById("selectedDivision");
	
	if (selected_division.selectedIndex > 0)
	{					
		selected_division_name.value = selected_division.options[selected_division.selectedIndex].text
	}
	else
	{	
		selected_division_name.value = "";
	}
	
		//set program unit name
	var selected_program_unit_name = document.getElementById("selectedProgramUnitName");
	var selected_program_unit = document.getElementById("selectedProgramUnit");

	if (selected_program_unit.selectedIndex > 0)
	{		
		selected_program_unit_name.value = selected_program_unit.options[selected_program_unit.selectedIndex].text
	}
	else
		selected_program_unit_name.value = "";

	
		//set incarceration condition name
	var selected_incarceration_condition_name = document.getElementById("selectedIncarcerationConditionName");		
	var selected_incarceration_condition = document.getElementById("selectedIncarcerationCondition");

	if (selected_incarceration_condition.selectedIndex > 0)
	{			
		selected_incarceration_condition_name.value = selected_incarceration_condition.options[selected_incarceration_condition.selectedIndex].text
	}
	else
		selected_incarceration_condition_name.value = "";
}//end of setFormNameValues()

function performValidation(theForm){
	clearAllValArrays();	
	
    <logic:equal name="cscServiceProviderProgramForm" property="action" value="activate">    
        customValRequired("changeToStatusDateAsStr","<bean:message key='errors.required' arg0='Activate Date'/>","");
    	 addMMDDYYYYDateValidation("changeToStatusDateAsStr","<bean:message key='errors.date' arg0='Activate Date'/>","");
         customValRequired("changeToStatusReason","<bean:message key='errors.required' arg0='Activate Reason'/>","");
		 addDB2FreeTextValidation("changeToStatusReason","<bean:message key='errors.comments' arg0='Activate Reason'/>","");
		 customValMaxLength("changeToStatusReason","<bean:message key='errors.maxlength' arg0='Activate Reason' arg1='255'/>","255"); 
			if( validateCustomStrutsBasedJS(theForm)){
			 	    if( compareDate1GreaterEqualDate2(new Date(), theForm.changeToStatusDateAsStr.value)){
						return true;
					}
					else{
						alert("Activate Date cannot be in the future");
						theForm.changeToStatusDateAsStr.focus();
						return false;
					}
			}
			else{
				return false;
			}
   </logic:equal>
   <logic:equal name="cscServiceProviderProgramForm" property="action" value="investigate">   
       customValRequired("changeToStatusDateAsStr","<bean:message key='errors.required' arg0='Investigate Date'/>","");
    	 addMMDDYYYYDateValidation("changeToStatusDateAsStr","<bean:message key='errors.date' arg0='Investigate Date'/>","");
         customValRequired("changeToStatusReason","<bean:message key='errors.required' arg0='Investigate Reason'/>","");
		 addDB2FreeTextValidation("changeToStatusReason","<bean:message key='errors.comments' arg0='Investigate Reason'/>","");
		 customValMaxLength("changeToStatusReason","<bean:message key='errors.maxlength' arg0='Investigate Reason' arg1='255'/>","255"); 
		 if( validateCustomStrutsBasedJS(theForm)){
			 	    if( compareDate1GreaterEqualDate2(new Date(), theForm.changeToStatusDateAsStr.value)){
						return true;
					}
					else{
						alert("Investigate Date cannot be in the future");
						theForm.changeToStatusDateAsStr.focus();
						return false;
					}
			}
			else{
				return false;
			}
    </logic:equal>
    <logic:equal name="cscServiceProviderProgramForm" property="action" value="inactivate">    
    	  customValRequired("changeToStatusReason","<bean:message key='errors.required' arg0='Inactivate Reason'/>","");
		 addDB2FreeTextValidation("changeToStatusReason","<bean:message key='errors.comments' arg0='Inactive Reason'/>","");
		 customValMaxLength("changeToStatusReason","<bean:message key='errors.maxlength' arg0='Inactive Reason' arg1='255'/>","255"); 
		 return validateCustomStrutsBasedJS(theForm);
    </logic:equal>
    <logic:equal name="cscServiceProviderProgramForm" property="action" value="suspend">
         customValRequired("changeToStatusReason","<bean:message key='errors.required' arg0='Suspend Reason'/>","");
		 addDB2FreeTextValidation("changeToStatusReason","<bean:message key='errors.comments' arg0='Suspend Reason'/>","");
		 customValMaxLength("changeToStatusReason","<bean:message key='errors.maxlength' arg0='Suspend Reason' arg1='255'/>","255");
		 return validateCustomStrutsBasedJS(theForm);   	  	    
    </logic:equal>
    
    setFormNameValues()
    
    <logic:equal name="cscServiceProviderProgramForm" property="action" value="create">	
		if(!validateRadios(theForm,'contractProgramAsStr','Contract Program is required')){
			return false;
		}
		
		var selected_division = document.getElementById("selectedDivision");
		if (selected_division.value != "")
		{
			var selected_program_unit = document.getElementById("selectedProgramUnit");
			if (selected_program_unit.value == "")
			{
				alert("Please select a Program Unit if a Division is selected.");
				return false;
			}
		}	
		
		
		//add price validation
		addCurrencyValidation("price", "Please enter a price in  ####.## format.");
		customValMaxLength("price","Please enter a price in  ####.## format.","7");		
		customValRequired("identifier","<bean:message key='errors.required' arg0='Identifier'/>","");
		customValMinLength("identifier","<bean:message key='errors.minlength' arg0='Identifier' arg1='3'/>","3");
		customValMaxLength("identifier","<bean:message key='errors.maxlength' arg0='Identifier' arg1='10'/>","10"); 
		addAlphaNumericValidation("identifier","<bean:message key='errors.comments' arg0='Identifier'/>","");
		
		customValRequired("name","<bean:message key='errors.required' arg0='Name'/>","");
		customValMinLength("name","<bean:message key='errors.minlength' arg0='Name' arg1='1'/>","1");
		customValMaxLength("name","<bean:message key='errors.maxlength' arg0='Name' arg1='75'/>","75");
		addAlphaNumericDashPeriodValidation("name","<bean:message key='errors.alphanumericDashPeriod' arg0='Name'/>","");
		
		customValRequired("programGroupId","<bean:message key='errors.required' arg0='Program Group'/>","");
		customValRequired("programTypeId","<bean:message key='errors.required' arg0='Type'/>","");
		
		customValRequired("programStartDateAsStr","<bean:message key='errors.required' arg0='Program Start Date'/>","");
		addMMDDYYYYDateValidation("programStartDateAsStr","<bean:message key='errors.date' arg0='Program Start Date'/>","");
		
		addMMDDYYYYDateValidation("programEndDateAsStr","<bean:message key='errors.date' arg0='Program End Date'/>","");
		
		customValRequired("selectedLocationIds","<bean:message key='errors.required' arg0='Location(s)'/>","");
		
				
		addAlphaNumericNoFirstSpacewSymbolsValidation("officeHours","<bean:message key='errors.comments' arg0='Office Hours'/>","");
		customValMaxLength("officeHours","<bean:message key='errors.maxlength' arg0='Office Hours' arg1='30'/>","30"); 
		
		customValRequired("description","<bean:message key='errors.required' arg0='Description'/>","");
		customValMaxLength("description","<bean:message key='errors.maxlength' arg0='Description' arg1='250'/>","250"); 
		addDB2FreeTextValidation("description","<bean:message key='errors.comments' arg0='Description'/>","");
		if( validateCustomStrutsBasedJS(theForm)){
			if(theForm.programEndDateAsStr.value!=""){
				if( compareDate1GreaterEqualDate2(theForm.programEndDateAsStr.value, theForm.programStartDateAsStr.value)){
					
				}
				else{
					alert("Program End Date must be after Program Start Date");
					theForm.programEndDateAsStr.focus();
					return false;
				}
			}
		}
		else{
			return false;
		}		
    </logic:equal>
    <logic:equal name="cscServiceProviderProgramForm" property="action" value="update"> 	
       if(!validateRadios(theForm,'contractProgramAsStr','Contract Program is required')){
			return false;
		}

       var selected_division = document.getElementById("selectedDivision");
	   	if (selected_division.value != "")
	   	{
	   		var selected_program_unit = document.getElementById("selectedProgramUnit");
	   		if (selected_program_unit.value == "")
	   		{
	   			alert("Please select a Program Unit if a Division is selected.");
	   			return false;
	   		}
	   	}	
	   	
	   	
	   	//add price validation
	   	addCurrencyValidation("price", "Please enter a price in  ####.## format.");
	   	customValMaxLength("price","Please enter a price in  ####.## format.","7");
		
		customValRequired("changeToStatusReason","<bean:message key='errors.required' arg0='Status Change Reason'/>","");
		addAlphaNumericNoFirstSpacewSymbolsValidation("changeToStatusReason","<bean:message key='errors.comments' arg0='Status Change Reason'/>","");
		customValMaxLength("changeToStatusReason","<bean:message key='errors.maxlength' arg0='Activate Reason' arg1='255'/>","255"); 
		
		<logic:equal name="cscServiceProviderProgramForm" property="changeToStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_PENDING%>">
	        customValRequired("name","<bean:message key='errors.required' arg0='Name'/>","");
		    customValMinLength("name","<bean:message key='errors.minlength' arg0='Name' arg1='1'/>","1");
		    customValMaxLength("name","<bean:message key='errors.maxlength' arg0='Name' arg1='75'/>","75"); 
		    customValRequired("programGroupId","<bean:message key='errors.required' arg0='Program Group'/>","");
		    customValRequired("programTypeId","<bean:message key='errors.required' arg0='Type'/>","");
		</logic:equal>
		
		addAlphaNumericDashPeriodValidation("name","<bean:message key='errors.alphanumericDashPeriod' arg0='Name' />","");
						
		addMMDDYYYYDateValidation("programEndDateAsStr","<bean:message key='errors.date' arg0='Program End Date'/>","");
		
		customValRequired("selectedLocationIds","<bean:message key='errors.required' arg0='Location(s)'/>","");
				
		addAlphaNumericNoFirstSpacewSymbolsValidation("officeHours","<bean:message key='errors.comments' arg0='Office Hours'/>","");
		customValMaxLength("officeHours","<bean:message key='errors.maxlength' arg0='Office Hours' arg1='30'/>","30"); 
		
	    customValRequired("description","<bean:message key='errors.required' arg0='Description'/>","");
		addDB2FreeTextValidation("description","<bean:message key='errors.comments' arg0='Description'/>","");
		customValMaxLength("description","<bean:message key='errors.maxlength' arg0='Description' arg1='250'/>","250"); 
		if( validateCustomStrutsBasedJS(theForm)){
			if(theForm.programEndDateAsStr.value!=""){
				if( compareDate1GreaterEqualDate2(theForm.programEndDateAsStr.value, theForm.programStartDateAsStr.value)){
					
				}
				else{
					alert("Program End Date must be after Program Start Date");
					theForm.programEndDateAsStr.focus();
					return false;
				}
			}
		}
		else{
			return false;
		}
    </logic:equal> 
}

function setFocus()
{
	el = document.getElementsByName("programGroupId")[0];
	loadPgmTypes(el);
	var txtFld = document.getElementsByName("identifier");
	if (txtFld != null && txtFld.length > 0 )
	{
		txtFld[0].focus();
	} else {
		txtFld = document.getElementsByName("changeToStatusReason");
		if (txtFld != null && txtFld.length > 0 )
		{
			txtFld[0].focus();
		}	
	}
}

function resetFields(theForm)
{
	alert( "In reset");
	var typeElem = theForm.programTypeId;
	var subtypeElem = theForm.programSubTypeId;
	typeElem.disabled = false;
	typeElem.options.length = 0;
	typeElem.options[0] = new Option("Please Select","",true);
	typeElem.disabled = true;
	subtypeElem.disabled = false;
	subtypeElem.options.length = 0;
	subtypeElem.options[0] = new Option("Please Select","",true);
	subtypeElem.disabled = true;
}

function getProgramUnitsForDivision(theForm) 
{		
 		var selected_division = document.getElementsByName("selectedDivision");
		theForm.action = theForm.action + "?submitAction=Get Division Program Units";
		theForm.submit();
}

</script>
</head>
<body topmargin="0" leftmargin="0" onLoad="setFormNameValues(), setFocus()" onKeyDown="checkEnterKeyAndSubmit(event,true)">

<html:form action="/displayCSCProgramUpdate" target="content">
	<html:hidden styleId="selectedDivisionName" name="cscServiceProviderProgramForm" property="selectedDivisionName"/>
	<html:hidden styleId="selectedProgramUnitName" name="cscServiceProviderProgramForm" property="selectedProgramUnitName"/>
	<html:hidden styleId="selectedIncarcerationConditionName" name="cscServiceProviderProgramForm" property="selectedIncarcerationConditionName"/>

<logic:equal name="cscServiceProviderProgramForm" property="action" value="update">
   <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|13">
</logic:equal>
<logic:equal name="cscServiceProviderProgramForm" property="action" value="activate">
   <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|21">
</logic:equal>
<logic:equal name="cscServiceProviderProgramForm" property="action" value="investigate">
   <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|25">
</logic:equal>
<logic:equal name="cscServiceProviderProgramForm" property="action" value="inactivate">
   <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|23">
</logic:equal>
<logic:equal name="cscServiceProviderProgramForm" property="action" value="suspend">
   <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|27">
</logic:equal>
<logic:equal name="cscServiceProviderProgramForm" property="action" value="create">
   <input type="hidden" name="helpFile" value="commonsupervision/CSCD_Service_Provider/CSCD_Service_Provider.htm#|7">          
</logic:equal>
<SCRIPT LANGUAGE="JavaScript" ID="js1">
	var cal1 = new CalendarPopup();
	cal1.showYearNavigation();
</SCRIPT>
<div align="center">

<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="top"><img src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
	</tr>
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<!--tabs start-->
						<tiles:insert page="../../common/commonSupervisionTabs.jsp" flush="true"/>
						<!--tabs end-->
					</td>
				</tr>
				<tr>
					<td bgcolor="#6699FF"><img src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="borderTableBlue">
				<tr>
					<td><img src="/<msp:webapp/>images/spacer.gif" height="5" alt=""></td>
				</tr>
				<tr>
					<td valign="top" align="center">
						<!-- BEGIN HEADING TABLE -->
						<table width="100%">
							<tr>
								<td align="center" class="header"><bean:message key="title.CSCD"/>&nbsp; - <bean:message key="title.serviceProvider"/> - 
									<logic:equal name="cscServiceProviderProgramForm" property="action" value="update">
								       <bean:message key="title.update"/> 
								    </logic:equal>  
								     <logic:equal name="cscServiceProviderProgramForm" property="action" value="activate">
								       Activate 
								    </logic:equal> 
                                    <logic:equal name="cscServiceProviderProgramForm" property="action" value="investigate">
								      Investigate 
								    </logic:equal>  
								    <logic:equal name="cscServiceProviderProgramForm" property="action" value="inactivate">
								       <bean:message key="prompt.inactivate"/>
								    </logic:equal>  
								    <logic:equal name="cscServiceProviderProgramForm" property="action" value="suspend">
								       Suspend 
								    </logic:equal> 
									<logic:equal name="cscServiceProviderProgramForm" property="action" value="create">
								       <bean:message key="title.create"/>          
								    </logic:equal>
									<bean:message key="prompt.program"/>
								</td>
							</tr>
						</table>
<!-- END HEADING TABLE -->
<!-- BEGIN ERRORS TABLE -->
    				    <table width="100%">
    					    <tr>		  
    						    <td align="center" class="errorAlert"><html:errors></html:errors></td>		  
    					    </tr>   	  
    				    </table>
<!-- END ERRORS TABLE -->
<!-- BEGIN INSTRUCTION TABLE -->
						<table width="98%" border="0">
							<logic:present name="confirmMsg">
    				        	<tr id="confirmations">
                					<td class="confirm">
                						<bean:write name="confirmMsg"/>
                					</td>
              					</tr>
              				</logic:present>
							<tr>
								<td>
									<ul>
										<li>Enter the required fields and click Next</li>
									</ul>
								</td>
							</tr>
							<tr>
								<td class="required"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.requiredFieldsInstruction"/>&nbsp;&nbsp;&nbsp;<bean:message key="prompt.dateFieldsInstruction"/></td>
							</tr>
						</table>
<!-- BEGIN SP HEADER TABLE -->						
      						<tiles:insert page="../../common/serviceProviderHeader.jsp" flush="true" />  
<!-- END SP HEADER TABLE -->      						    							      									
      					<div class="spacer4px"></div>
<!-- BEGIN BLUE BORDER TABLE -->      						
      					<table width="98%" cellpadding="2" cellspacing="0" class="borderTableBlue">
      						<tr>
      							<td class="detailHead"><bean:message key="prompt.program"/>&nbsp;<bean:message key="prompt.info"/></td>
      						</tr>
      						<tr>
      							<td>
			                        <logic:notPresent name="inUpdateORCreate">
            				            <table width="100%" cellpadding="4" cellspacing="1">
											<tr>
      											<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.3.diamond"/>
      												<logic:equal name="cscServiceProviderProgramForm" property="changeToStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_ACTIVE%>">
                                						<bean:message key="prompt.activate"/>
                                
													</logic:equal>
                                					<logic:equal name="cscServiceProviderProgramForm" property="changeToStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_UNDERINVESTIGATION%>">
                                						Investigate
                                
													</logic:equal>
                                					<logic:equal name="cscServiceProviderProgramForm" property="changeToStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_SUSPENDED%>">
						                                Suspend
                                
                               						</logic:equal>
                               						<logic:equal name="cscServiceProviderProgramForm" property="changeToStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_INACTIVE%>">
						                                <bean:message key="prompt.inactivate"/>
                                
                        							</logic:equal>
                               						<bean:message key="prompt.date"/>
      											</td>
      											<td class="formDe" colspan="3">
      												<logic:equal name="cscServiceProviderProgramForm" property="changeToStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_ACTIVE%>">
                            							<html:text name="cscServiceProviderProgramForm" property="changeToStatusDateAsStr" size="10" maxlength="10"/>
      														<a href="#" onClick="cal1.select(document.forms[0]['changeToStatusDateAsStr'],'anchor99','MM/dd/yyyy'); return false;" NAME="anchor99" ID="anchor99"><bean:message key="prompt.3.calendar"/></a>
                          
		                        						</logic:equal>
                          								<logic:equal name="cscServiceProviderProgramForm" property="changeToStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_UNDERINVESTIGATION%>">
                          									<html:text name="cscServiceProviderProgramForm" property="changeToStatusDateAsStr" size="10" maxlength="10"/>
      															<a href="#" onClick="cal1.select(document.forms[0]['changeToStatusDateAsStr'],'anchor99','MM/dd/yyyy'); return false;" NAME="anchor99" ID="anchor99"><bean:message key="prompt.3.calendar"/></a>
      		                   							</logic:equal>
						            					<logic:equal name="cscServiceProviderProgramForm" property="changeToStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_SUSPENDED%>">
						                         			<bean:write name="cscServiceProviderProgramForm" property="changeToStatusDateAsStr" formatKey="date.format.mmddyyyy" />
														</logic:equal>
						                           		<logic:equal name="cscServiceProviderProgramForm" property="changeToStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_INACTIVE%>">
						                          			<bean:write name="cscServiceProviderProgramForm" property="changeToStatusDateAsStr" formatKey="date.format.mmddyyyy" />
														</logic:equal>
												</td>
											</tr>
      										
      										<tr>
      											<td class="formDeLabel" colspan="4" nowrap="nowrap"><bean:message key="prompt.3.diamond"/>
                             						<logic:equal name="cscServiceProviderProgramForm" property="changeToStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_ACTIVE%>">
                              							<bean:message key="prompt.activate"/>
                             						</logic:equal>
                              						<logic:equal name="cscServiceProviderProgramForm" property="changeToStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_UNDERINVESTIGATION%>">
                              							Investigate
                             						</logic:equal>
                              						<logic:equal name="cscServiceProviderProgramForm" property="changeToStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_SUSPENDED%>">
                              							Suspend
                             						</logic:equal>
                               						<logic:equal name="cscServiceProviderProgramForm" property="changeToStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_INACTIVE%>">
                              							<bean:message key="prompt.inactivate"/>
                             						</logic:equal>
						                            <bean:message key="prompt.reason"/>&nbsp;(Max Characters Allowed: 250)
                        						</td>
      										</tr>
      										<tr>
					    						<td class="formDe"  colspan="4">
					    							<html:textarea property="changeToStatusReason" rows="2" style="width:100%" onmouseout="textLimit( this, 250 )" onkeyup="textLimit( this, 255 )"/>
					    						</td>
					    					</tr>
					    										
											<tr id="statusRow0">
												<td class="formDeLabel" width="1%" nowrap="nowrap">Current <bean:message key="prompt.status"/></td>
												<td class="formDe">
													<bean:write name="cscServiceProviderProgramForm" property="currentStatusDesc"/>																							
												</td>
												<td class="formDeLabel" width="1%">
												   	<logic:equal name="cscServiceProviderProgramForm" property="currentStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_ACTIVE%>">
						                                <bean:message key="prompt.activate"/>
					                               	</logic:equal>
													<logic:equal name="cscServiceProviderProgramForm" property="currentStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_UNDERINVESTIGATION%>">
                                						Under Investigation
					                               	</logic:equal>
													<logic:equal name="cscServiceProviderProgramForm" property="currentStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_SUSPENDED%>">
					                                	Suspend
					                                
					                               	</logic:equal>
					                                <logic:equal name="cscServiceProviderProgramForm" property="currentStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_INACTIVE%>">
					                               		Inactive
					                               	</logic:equal>
					                                <bean:message key="prompt.date"/>
					                            </td>
      											<td class="formDe">
      												<bean:write name="cscServiceProviderProgramForm" property="statusDateAsStr" formatKey="date.format.mmddyyyy" />
      											</td>
      										</tr>
      										<tr id="statusRow1">
      											<td class="formDeLabel" colspan="4" nowrap="nowrap">
													<logic:equal name="cscServiceProviderProgramForm" property="currentStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_ACTIVE%>">
					                                	<bean:message key="prompt.activate"/>
					                               	</logic:equal>
					                                <logic:equal name="cscServiceProviderProgramForm" property="currentStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_UNDERINVESTIGATION%>">
					                                	Under Investigation
					                               	</logic:equal>
					                                <logic:equal name="cscServiceProviderProgramForm" property="currentStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_SUSPENDED%>">
					                                	Suspend
					                               	</logic:equal>
					                                <logic:equal name="cscServiceProviderProgramForm" property="currentStatusId" value="<%=PDCodeTableConstants.ASP_CS_PROGRAM_INACTIVE%>">
					                                	Inactive
					                               	</logic:equal>
													<bean:message key="prompt.reason"/>
                                				</td>
      										</tr>
      										<tr id="statusRow2">
      											<td class="formDe"  colspan="4">
      												<bean:write name="cscServiceProviderProgramForm" property="statusReason"/>
      											</td>
      										</tr>
                       				</logic:notPresent>
                                           
   									<logic:present name="inUpdateORCreate">
      									<table width="100%" cellpadding="2" cellspacing="1">
	      									<logic:equal name="cscServiceProviderProgramForm" property="action" value="update">
      											<tr id="statusRow0">
    	  											<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.status"/></td>
      												<td class="formDe">
      													<bean:write name="cscServiceProviderProgramForm" property="currentStatusDesc"/>																							
      												</td>
      												<td class="formDeLabel" >
      												    Status Change <bean:message key="prompt.date"/>
                            	                    </td>
      												<td class="formDe">
      													<bean:write name="cscServiceProviderProgramForm" property="statusDateAsStr" formatKey="date.format.mmddyyyy" />
      												</td>
      											</tr>
      											<tr id="statusRow1">
      												<td class="formDeLabel" colspan="4" nowrap="nowrap"><bean:message key="prompt.3.diamond"/>
                                                    	Status Change Reason&nbsp;(Max Characters Allowed: 255)
                                                    </td>
      											</tr>
      											<tr id="statusRow2">
      												<td class="formDe"  colspan="4">
      													<html:textarea property="changeToStatusReason" rows="2" style="width:100%" onmouseout="textLimit( this, 255 )" onkeyup="textLimit( this, 255 )"/>
      												</td>
      											</tr>
      										</logic:equal>
      									
	      									<tr>
	      										<td class="formDeLabel" width="1%" nowrap="nowrap">
	      											<logic:equal name="cscServiceProviderProgramForm" property="action" value="create">
	      												<bean:message key="prompt.3.diamond"/>
	      											</logic:equal>
	      											<bean:message key="prompt.identifier"/>
	      										</td>
	      										<td class="formDe">
	   												<logic:equal name="cscServiceProviderProgramForm" property="action" value="create">
		                                                <html:text name="cscServiceProviderProgramForm" property="identifier" size="20" maxlength="10"/>
	    											</logic:equal>
	    											<logic:notEqual name="cscServiceProviderProgramForm" property="action" value="create">	
	    											   <bean:write name="cscServiceProviderProgramForm" property="identifier"/>
	    											</logic:notEqual>																				
	    										</td>
	    										<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.contractProgram"/></td>
	    										<td class="formDe">
	   												YES <html:radio name="cscServiceProviderProgramForm" value="<%=PDCodeTableConstants.ASP_CS_CONTRACTPROGRAM_YES%>" property="contractProgramAsStr"> NO <html:radio name="cscServiceProviderProgramForm" value="<%=PDCodeTableConstants.ASP_CS_CONTRACTPROGRAM_NO%>" property="contractProgramAsStr">
	      											</html:radio></html:radio>
	   											</td>
	   										</tr>
	   										<tr>
	   											<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.name"/></td>
	   											<td class="formDe" colspan="3">
	   												<logic:present name="allowKeyChanges">
	   													<html:text name="cscServiceProviderProgramForm" property="name" size="40" maxlength="75"/>
	   												</logic:present>
	   												<logic:present name="allowNameChange">
	   													<html:text name="cscServiceProviderProgramForm" property="name" size="40" maxlength="75"/>
	   												</logic:present>
	   												<logic:notPresent name="allowKeyChanges">
	   													<logic:notPresent name="allowNameChange">
	   														<bean:write name="cscServiceProviderProgramForm" property="name"/>
	   													</logic:notPresent>
	   												</logic:notPresent>
	   											</td>
	   										</tr>
	   										<tr>
	   											<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.programGroup"/></td>
	   											<td colspan="3" class="formDe">
	   												<logic:present name="allowKeyChanges">
	   													<html:select name="cscServiceProviderSearchForm" property="programGroupId" onchange="loadPgmTypes(this);"> 
			          										<html:option value=""><bean:message key="select.generic" /></html:option>
			          										<html:optionsCollection name="cscServiceProviderSearchForm" property="programHeirarchyList" value="parentCd" label="parentDesc" />					
		          										</html:select>
	   												</logic:present>
	   												<logic:notPresent name="allowKeyChanges">
	   													<bean:write name="cscServiceProviderProgramForm" property="programGroupDesc"/>
	   												</logic:notPresent>
	   											</td>
	   										</tr>
	   										<tr>
	   											<td class="formDeLabel"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.type"/></td>
	   											<td colspan="3" class="formDe">
		 											<logic:present name="allowKeyChanges">
		                                          	<html:select name="cscServiceProviderSearchForm" property="programTypeId"  disabled="false">
		          										<html:option value=""><bean:message key="select.generic" /></html:option>
		          										<html:optionsCollection name="cscServiceProviderSearchForm" property="programTypes" value="code" label="description" />					
		          									</html:select>
		 											</logic:present>
		 											<logic:notPresent name="allowKeyChanges">
		 												<bean:write name="cscServiceProviderProgramForm" property="programTypeDesc"/>
		 											</logic:notPresent>
	   											</td>
	   										</tr>
	   										<%-- Removed subtype --%>
	   										
	   										<tr>
      											<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.programStartDate"/></td>
      											<logic:equal name="cscServiceProviderProgramForm" property="action" value="create">
													<td class="formDe">
      													<html:text name="cscServiceProviderProgramForm" property="programStartDateAsStr" size="10" maxlength="10"/>
      											    	<a href="#" onClick="cal1.select(document.forms[0]['programStartDateAsStr'],'anchor1','MM/dd/yyyy'); return false;" NAME="anchor1" ID="anchor1"><bean:message key="prompt.3.calendar"/></a>
      											    </td>
      											</logic:equal>
      											<logic:notEqual name="cscServiceProviderProgramForm" property="action" value="create">
      											   <td class="formDe"><bean:write name="cscServiceProviderProgramForm" property="programStartDateAsStr"/></td>
      											</logic:notEqual>	
      											<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.programEndDate"/></td>
      											<td class="formDe">
      												<html:text name="cscServiceProviderProgramForm" property="programEndDateAsStr" size="10" maxlength="10"/>
      												<A HREF="#" onClick="cal1.select(document.forms[0].programEndDateAsStr,'anchor2','MM/dd/yyyy'); return false;" NAME="anchor2" ID="anchor2"><bean:message key="prompt.3.calendar"/></A>
      											</td>
      										</tr>
      										<tr>
												<td class="formDeLabel" width="1%" nowrap="nowrap">Division</td>
												<td class="formDe" colspan="3">
													<html:select styleId="selectedDivision" name="cscServiceProviderProgramForm" property="selectedDivision" onchange="getProgramUnitsForDivision(this.form)">
      													<html:option value=""><bean:message key="select.generic" /></html:option>
      													<html:optionsCollection name="cscServiceProviderProgramForm" property="availableDivisions" value="OID" label="description" />
      												</html:select>															
												</td>
											</tr>
      												
											<tr>
												<td class="formDeLabel" nowrap="nowrap"><span class="hidden" id="requiredPUImg"><span class="diamond"></span></span>Program Unit</td>
												<td class="formDe" colspan="3">
													<html:select styleId="selectedProgramUnit" name="cscServiceProviderProgramForm" property="selectedProgramUnit">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:optionsCollection name="cscServiceProviderProgramForm" property="divisionProgramUnits" value="organizationTO.OID" label="organizationTO.description" />
													</html:select>															
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap="nowrap">Incarceration Program</td>
												<td class="formDe" colspan="1">
													<html:select styleId="selectedIncarcerationCondition" name="cscServiceProviderProgramForm" property="selectedIncarcerationCondition">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:optionsCollection name="cscServiceProviderProgramForm" property="incarcerationConditions" value="groupId" label="name" />
													</html:select>																													
												</td>
												<td class="formDeLabel" nowrap="nowrap">Price</td>
												<td class="formDe">															
													<html:text name="cscServiceProviderProgramForm" property="price"  />
												</td>														
											</tr>      												
											<tr>
												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.sexSpecific"/></td>
												<td class="formDe" colspan="3">
													<html:select name="cscServiceProviderProgramForm" property="sexSpecificId">
														<html:option value="">Please Select</html:option>
														<jims2:codetable codeTableName="<%=PDCodeTableConstants.ASP_CS_SEX_SPECIFIC%>" sort="true"></jims2:codetable>
													</html:select>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.languageOffered"/></td>
												<td class="formDe" colspan="3">
													<html:select name="cscServiceProviderProgramForm" property="languagesOfferedIds" multiple="true" size="4" onchange="multiSelectFix(this)">
														<html:option value="">Please Select</html:option>
														<jims2:codetable codeTableName="<%=PDCodeTableConstants.ASP_PROGRAM_LANGUAGE%>" simpleCodeTable="true" sort="true"></jims2:codetable>
													</html:select>
												</td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.location"/>(s)</td>
												<td class="formDe" colspan="3">
													<html:select multiple="true" size="6" name="cscServiceProviderProgramForm" property="selectedLocationIds" onchange="multiSelectFix(this)">
														<html:option value=""><bean:message key="select.generic" /></html:option>
														<html:optionsCollection name="cscServiceProviderProgramForm" property="locations" value="locationId" label="locationName" /> 
													</html:select>
													<div class="spacer"></div>
													<a href="javascript:changeFormActionURL(document.forms[0],'/<msp:webapp/>displayCSCProgramUpdate.do?submitAction=<bean:message key="button.transfer"/>',true)"><bean:message key="prompt.add"/>&nbsp;<bean:message key="prompt.new"/>&nbsp;<bean:message key="prompt.location"/></a>
												</td>
											</tr>																										
      										<tr>
												<td class="formDeLabel"><bean:message key="prompt.officeHours"/></td>
												<td class="formDe" colspan="3"><html:text name="cscServiceProviderProgramForm" property="officeHours" size="30" maxlength="30"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" colspan="4"><bean:message key="prompt.3.diamond"/><bean:message key="prompt.description"/>&nbsp;(Max Characters Allowed: 250)</td>
											</tr>
											<tr>
												<td class="formDe" colspan="4">
													<html:textarea rows="4" style="width:100%" name="cscServiceProviderProgramForm" property="description" onmouseout="textLimit( this, 250 )" onkeyup="textLimit( this, 250 )">
												</html:textarea></td>
											</tr>
									</logic:present>
									
      								<logic:notPresent name="inUpdateORCreate">
	      									<tr>
												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.identifier"/></td>
												<td class="formDe"><bean:write name="cscServiceProviderProgramForm" property="identifier"/></td>
												<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.contractProgram"/></td>
												<td class="formDe"><bean:write name="cscServiceProviderProgramForm" property="contractProgramAsStr"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.name"/></td>
												<td colspan="3" class="formDe"><bean:write name="cscServiceProviderProgramForm" property="name"/></td>
											</tr>
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.programGroup"/></td>
												<td colspan="3" class="formDe"><bean:write name="cscServiceProviderProgramForm" property="programGroupDesc"/></td>
											</tr>
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.type"/></td>
												<td colspan="3" class="formDe"><bean:write name="cscServiceProviderProgramForm" property="programTypeDesc"/></td>
											</tr>
											<%--	<tr>
	      										<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.stateProgramCode"/></td>
	      										<td class="formDe" colspan="3"><bean:write name="cscServiceProviderProgramForm" property="stateProgramCode"/></td>
	      									</tr> --%>
											<tr>
												<td class="formDeLabel" nowrap="nowrap"><bean:message key="prompt.programStartDate"/></td>
												<td class="formDe"><bean:write name="cscServiceProviderProgramForm" property="programStartDateAsStr" formatKey="date.format.mmddyyyy"/></td>
												<td class="formDeLabel" nowrap="nowrap" width="1%"><bean:message key="prompt.programEndDate"/></td>
												<td class="formDe"><bean:write name="cscServiceProviderProgramForm" property="programEndDateAsStr" formatKey="date.format.mmddyyyy"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.division"/></td>
												<td class="formDe" colspan="3"><bean:write name="cscServiceProviderProgramForm" property="selectedDivisionName"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.programUnit"/></td>
												<td class="formDe" colspan="3"><bean:write name="cscServiceProviderProgramForm" property="selectedProgramUnitName"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" nowrap="nowrap">Incarceration Program </td>
												<td class="formDe"><bean:write name="cscServiceProviderProgramForm" property="selectedIncarcerationConditionName"/></td>
												<td class="formDeLabel" nowrap="nowrap" width="1%">Price </td>
												<td class="formDe"><bean:write name="cscServiceProviderProgramForm" property="price" formatKey="currency.format"/></td>
											</tr>
											<tr>
												<td class="formDeLabel" width="1%" nowrap="nowrap"><bean:message key="prompt.location"/>(s)</td>
												<td class="formDe" colspan="3"><bean:write name="cscServiceProviderProgramForm" property="selectedLocationDescs"/></td>
											</tr>
											<tr>
												<td class="formDeLabel"><bean:message key="prompt.officeHours"/></td>
												<td colspan="3" class="formDe"><bean:write name="cscServiceProviderProgramForm" property="officeHours"/></td>
											</tr>
											<tr>
												<td colspan="4" class="formDeLabel"><bean:message key="prompt.description"/></td>
											</tr>
											<tr>
												<td colspan="4" class="formDe"><bean:write name="cscServiceProviderProgramForm" property="description"/></td>
											</tr>
									</logic:notPresent>
									</table>
								</td>
							</tr>
						</table>
						<br>
<!-- BEGIN BUTTON TABLE -->
						<table border="0">
							<tr>
								<td align="center">
									<html:submit property="submitAction"> <bean:message key="button.back" /></html:submit>
									<html:submit property="submitAction" onclick="return performValidation(this.form)"> <bean:message key="button.next" /></html:submit>												
									<html:reset onclick="return resetFields(this.form)"><bean:message key="button.reset"/></html:reset>
			                        <html:submit property="submitAction"> <bean:message key="button.cancel" /></html:submit>
								</td>
							</tr>
						</table>
<!-- END BUTTON TABLE -->
					</td>
				</tr>
			</table>
			<br>
		</td>
	</tr>
</table>
			
</div>
</html:form>
<div align="center"><script type="text/javascript">renderBackToTop()</script></div></body>
<html:html></html:html>