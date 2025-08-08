<!DOCTYPE HTML>
<!-- 12/21/2005	 Debbie Williamson - Create JSP -->
<!-- 02/01/2006  Uma Gopinath - Modified	-->
<!-- 06/12/2006  C Shimek     - Defect#32068 Modified search results layout to more closely match prototype -->  
<!-- 07/26/2006  C Shimek     - Activity#32961 add sort to UserID and Dept Code on result set -->
<!-- 10/03/2006  C Shimek     - Activity#35831 add JIMS2 User ID search field and changed prompt for User ID to JIMS User ID -->
<!-- 10/04/2006  C Shimek     - Activity#35721 modified department Code to default to users department code in disabled mode if user is not MA -->
<!-- 11/06/2006  C Shimek     - Activity#35721 revised to use Agency Code instead of Department Code default for non MA users -->
<!-- 12/07/2006  C Shimek     - Activity 37546, correct placement of buttons.  -->
<!-- 01/04/2007  C Shimek     - Defect #37973 modified loadUser script as part of js fix -->
<!-- 01/11/2007  C Shimek     - #38306 add multiple submit functionality  -->
<!-- 02/26/2007  C Shimek     - #39862 added logic tags around department code and name to check for user type of LA, LA should only find profiles within their own department only	-->
<!-- 03/20/2007  C Shimek     - #40528 added logic tag around Manage User Group and Assign Roles but for user type not equal LA	 -->
<!-- 05/24/2007  C Shimek	  - #42152 revised local js and basic/advanced hyperlinks to maintain display preference once selected, even upon re-entry from other pages -->
<!-- 06/04/2007  C Shimek	  - corrected clearInputs() function to not clear disabled input fields -->
<!-- 07/06/2007  C Shimek     - #43456 replaced casework_uitl.js with security_util.js  -->
<!-- 04/09/2008  DWilliamson	ER #46085 Changed Delete to Inactivate -->
<!-- 02/06/2009  C Shimek     - #56860 add Back to Top  -->
<!-- 10/19/2015  R Young      - #30791 MJCW: IE11 conversion of "Security Admin"  link on UILeftNav (UI-Assign Roles) -->

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/jims2-presentation.tld" prefix="jims" %>

<!-- TAB LIBRARIES NEEDED FOR MOJO -->
<%@ taglib uri="/WEB-INF/msp.tld" prefix="msp" %>
<%@ include file="../../jQuery.fw" %>

<!--LOCALE USED FOR INTERNATIONALIZATION-NOT USED YET -->

<!--BEGIN HEADER TAG-->
<head>
<msp:nocache />

<html:javascript formName="userProfileSearchForm"/>
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
<title><bean:message key="title.heading"/> - userProfileSearch.jsp</title>

<%--tiles:insert page="../js/userProfileSearch.js" flush="true" /--%>

<script type='text/javascript' src="/<msp:webapp/>js/app.js"></script>

<script type="text/javascript" src="/<msp:webapp/>js/userProfiles/userProfileSearch.js"></script>
<script type='text/javascript' src="/<msp:webapp/>js/security_util.js"></script>
<script type="text/javascript" src="/<msp:webapp/>js/wildCardSearch.js" ></script>

<script type="text/javascript">
var currentSelectedUser = 0 ;

function setDisplayType(dType)
{
	document.forms[0].displayType.value = dType;
	return true;
}
function showDetailSearch(dType)
{
	if (dType != document.forms[0].displayType.value && typeof dType != "undefined"){
		clearInputs();		
	}
	if (typeof dType == "undefined" || dType == ""){
		dType = document.forms[0].displayType.value;
		if (dType == ""){
			dType = "basic";
		}	
	}

	if( dType == "advanced" )
	{ // display advanced search fields
    	/* for( var i = 0; i < 10; i++ )
      	{
        	show( 'hrow'+i, '1', 'row' ) ;
      	} */
		show( 'advancedSearchURL', 0 ) ; 				
		show( 'basicSearchURL', 1 ) ; 
	}else { // display basic search fields
		/* for( var i = 0; i < 10; i++ )
		{
        	show( 'hrow'+i, '0', 'row' ) ;
      	} */
		show( 'basicSearchURL', 0 ) ; 				
		show( 'advancedSearchURL', 1 ) ; 
	} 
	document.forms[0].displayType.value = dType;
	document.forms[0].logonId.focus();	
}

function clearInputs()
{
	var iTexts = document.getElementsByTagName("input");
	var iSelects = document.getElementsByTagName("select");
	for (t=0; t<iTexts.length; t++){
		if (iTexts[t].type == "text" && iTexts[t].disabled == false){
// do not clear pagination values			
			if (iTexts[t].name != "pagerSearch"){
				iTexts[t].value = "";
			}
		}
	}
	for (s=0; s<iSelects.length; s++){
		iSelects[s].selectedIndex = 0;
	}	
}
function setUser( userNum, trainingInd)
{
  	var element = document.getElementById('hiddenVal');
  	element.value = userNum ;	
//  	if( trainingInd == "Y" )
//  	{
//  		document.getElementById('deleteLink').disabled = "disabled" ;				
//  	} else {
//  		document.getElementById('deleteLink').disabled = "" ;
//	}
  	currentSelectedUser = userNum ;
}
		
function checkUser()
{
  	if(document.getElementById('deleteLink').disabled == true )
   		return false ;  	
  	else
  		return true ;  	
  }
		
function load(file,target) 
  {
     window.location.href = file;
  }
  
function loadUser(file)
		{
//  	checkWhichRadioFields();	
//  	var myURL = file + "&selectedValue=" + document.forms[2].selectedValue.value;
	var myURL = file + "&selectedValue=" + currentSelectedUser;
  	load( myURL,top.opener ) ;
  	window.close();
  }

function loadViewUser(file)
		{
  	var myURL = file + "&selectedValue=" + currentSelectedUser;
  	load( myURL, top.opener );
  	window.close();
  }
 
function loadAssignUser(file)
{  	
	if( validateRadioFields())
	{ 	
		checkWhichRadioFields();	  	
		var myURL = file+ "?userId=" + document.forms[2].selectedValue.value;  		
		load( myURL,top.opener ) ;
		window.close();
	}  		
}
</script>
</head>

<body onload="showDetailSearch(); checkWhichRadioFields();" onKeyDown="return checkEnterKeyAndSubmit(event,true)">
<!-- AUTO TAB JAVASCRIPT FILE -->

<!-- BEGIN HEADING TABLE  -->
<table width="100%" border="0" align="center">
	<tr>
		<td align="center" class="header"><bean:message key="title.searchUser"/></td>
	</tr>
</table>
<!-- END HEADING TABLE -->
<br>
<!-- BEGIN ERROR TABLE  -->
<table width="100%">
	<tr>		  
		<td align="center" class="errorAlert"><html:errors></html:errors></td>		  
	</tr>   	  
</table>
<!-- END ERROR TABLE  -->
<!-- BEGIN INSTRUCTION TABLE  -->
<table width="98%">
	<tr>
		<td>
			<ul>
				<li>Enter 1 or more values and/or select from drop down list(s) then select &quot;Find User Profiles&quot; button to search.</li>
			</ul>
		</td>
	</tr>
	<tr>
		<td class="required">+ indicates Last Name is required to use this search field.</td>
	</tr>
	<tr>
		<td class="required">++ indicates only 1 of these fields may be entered per search.</td>
	</tr>
	<tr>
		<td class="required"><bean:message key="prompt.dateFieldsInstruction" /></td>
	</tr>
</table>
<!-- END INSTRUCTION TABLE  -->

<!-- BEGIN SEARCH TABLE  -->
<html:form action="/displayUserProfileSearchResults" target="content" focus="logonId">
<input type="hidden" name="helpFile" value="jims2security/jims2_security.htm#|196">	
<input type="hidden" name="displayType" value="<bean:write name="userProfileSearchForm" property="displayType" />" >
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td valign="top">
			<table class="borderTableBlue" cellpadding="4" cellspacing="0" border="0" width="100%">
				<tr class="detailHead">
					<td class="detailHead">
						<span id='advancedSearchURL' class='hidden'>
							<bean:message key="prompt.searchForUsers"/>&nbsp;
							<a href="#" id="advancedSearch" >Advanced User Profile Search</a>
						</span>
						<span id='basicSearchURL' class='hidden'>
							<bean:message key="prompt.searchForUsers"/>&nbsp;	
							<a href="#"  id="basicSearch">Basic User Profile Search</a>
						</span>
					</td>
				</tr>
				<tr>
					<td align="center">
						<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1">
							<tr>
								<td class="formDeLabel" >++<bean:message key="prompt.JIMS"/> <bean:message key="prompt.userId"/></td>
								<td class="formDe" colspan="3"><html:text name="userProfileSearchForm" property="logonId" size="8" maxlength="8"/></td>
							</tr>
							<tr>
								<td class="formDeLabel" nowrap width="1%">++<bean:message key="prompt.JIMS2UserId"/></td>
								<td class="formDe" colspan="3"><html:text name="userProfileSearchForm" property="jims2LogonId" size="50" maxlength="50"/></td>
							</tr>

							<tr id='hrow0' class='hidden'>
								<td class="formDeLabel"><bean:message key="prompt.operatorId"/></td>
								<td class="formDe" colspan="3"><html:text name="userProfileSearchForm" property="OPID" size="3" maxlength="3"/></td>
							</tr>
							<tr>
								<td class="formDeLabel" ><bean:message key="prompt.lastName"/></td>
								<td class="formDe" colspan="3"><html:text name="userProfileSearchForm" property="userName.lastName" size="30" maxlength="75"/></td>
							</tr>
							<tr>
								<td class="formDeLabel">+<bean:message key="prompt.firstName"/></td>
								<td class="formDe"><html:text name="userProfileSearchForm" property="userName.firstName" size="25" maxlength="50"/></td>
								<td class="formDeLabel" width="1%" nowrap>+<bean:message key="prompt.middleName"/></td>
								<td class="formDe" nowrap><html:text name="userProfileSearchForm" property="userName.middleName" size="25" maxlength="50"/></td> 
							</tr>
							<tr id='hrow1' class='hidden'>
								<td class="formDeLabel"><bean:message key="prompt.dateOfBirth"/></td>
								<td class="formDe" colspan="3">
									<html:text name="userProfileSearchForm" property="fromDateOfBirth" size="10" maxlength="10" styleId="fromDateOfBirth"/> &nbsp;to&nbsp;
									<html:text name="userProfileSearchForm" property="toDateOfBirth" size="10" maxlength="10" styleId="toDateOfBirth"/>
								</td>
							</tr>
 
							<tr id='hrow2' class='hidden'>
								<td class="formDeLabel"><bean:message key="prompt.SSN"/></td>
								<td class="formDe">
									<html:text name="userProfileSearchForm" property="SSN.SSN1" size="3" maxlength="3" onkeyup="return autoTab(this, 3);" />- 
									<html:text name="userProfileSearchForm" property="SSN.SSN2" size="2" maxlength="2" onkeyup="return autoTab(this, 2);" />- 
									<html:text name="userProfileSearchForm" property="SSN.SSN3" size="4" maxlength="4" onkeyup="return autoTab(this, 4);" />
								</td>
								<td class="formDeLabel"><bean:message key="prompt.userStatus"/></td>
								<td class="formDe">
									<html:select property="userStatusId" name="userProfileSearchForm">
										<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
										<html:optionsCollection property="userStatuses" value="code" label="description"  name="userProfileSearchForm"/>
									</html:select>  
								</td>
							</tr>

							<tr id='hrow3' class='hidden'>
								<td class="formDeLabel"><bean:message key="prompt.agencyCode"/></td>
								<td class="formDe" colspan="3">
									<table width=100% cellpadding="3" cellspacing="0" border="0">
										<tr>
											<logic:equal name="userProfileSearchForm" property="userIsMA" value="Y">
												<td class="formDe" width="1%">
													<html:text name="userProfileSearchForm" property="agencyId" size="3" maxlength="3" />
												</td>
												<td class="formDeLabel" width="1%"><bean:message key="prompt.name"/></td>
												<td class="formDe"><html:text name="userProfileSearchForm" property="agencyName" size="50" maxlength="50" /></td>
											</logic:equal>
											<logic:equal name="userProfileSearchForm" property="userIsMA" value="N">
												<td class="formDe" width="1%">
													<html:text name="userProfileSearchForm" property="agencyId" size="3" maxlength="3" disabled="true" /> 
												</td>
												<td class="formDeLabel" width="1%"><bean:message key="prompt.name"/></td>	                        
												<td class="formDe">
													<input type="hidden" name="agencyName" value="<bean:write name="userProfileSearchForm" property="agencyName" />" >
													<bean:write name="userProfileSearchForm" property="agencyName" />
												</td>
											</logic:equal>
										</tr>
									</table>
								</td>                     		                    
							</tr>
							<tr>
								<td class="formDeLabel" nowrap><bean:message key="prompt.departmentCode"/></td>
								<td class="formDe" colspan="3">
									<table width="100%" cellpadding="3" cellspacing="0" border="0">
										<logic:equal name="userProfileSearchForm" property="userIsLA" value="N">
										<tr>
											<td class="formDe" width="1%"><html:text name="userProfileSearchForm" property="departmentId" size="3" maxlength="3" /></td>
											<td class="formDeLabel" width="1%"><bean:message key="prompt.name"/></td>
											<td class="formDe"><html:text name="userProfileSearchForm" property="departmentName" size="60" maxlength="60"/></td>
										</tr>
										</logic:equal>
										<logic:equal name="userProfileSearchForm" property="userIsLA" value="Y">
										<tr>
											<td class="formDe" width="1%"><html:text name="userProfileSearchForm" property="departmentId" size="3" disabled="true" /></td>
											<td class="formDeLabel" width="1%"><bean:message key="prompt.name"/></td>
											<td class="formDe">
												<bean:write name="userProfileSearchForm" property="departmentName" />
												<input type="hidden" name="departmentName" value="<bean:write name="userProfileSearchForm" property="departmentName" />" >												
											</td>
										</tr>
										</logic:equal>
									</table>
								</td>                    
							</tr>
							<tr id='hrow4' class='hidden'>
								<td class="formDeLabel"><bean:message key="prompt.genericUserType"/></td>
								<td class="formDe" colspan="3">
									<html:select property="genericUserTypeId" name="userProfileSearchForm">
										<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
										<html:optionsCollection property="genericUserTypes" value="code" label="description"  name="userProfileSearchForm"/>
									</html:select>  
								</td>
							</tr>
							<tr id='hrow5' class='hidden'>
								<td class="formDeLabel"><bean:message key="prompt.userType"/></td>
								<td class="formDe" colspan="3">
									<html:select property="userTypeId" name="userProfileSearchForm">
										<html:option value=""><bean:message key="select.generic" /></html:option>  			            	  	
										<html:optionsCollection property="userTypes" value="code" label="description" name="userProfileSearchForm"/>
									</html:select>  
								</td>
							</tr>
							<tr id='hrow6' class='hidden'>
								<td class="formDeLabel"><bean:message key="prompt.requestorLastName"/></td>
								<td class="formDe"><html:text name="userProfileSearchForm" property="requestorName.lastName" size="30" maxlength="30"/></td>
								<td class="formDeLabel">+<bean:message key="prompt.firstName"/></td>
								<td class="formDe"><html:text name="userProfileSearchForm" property="requestorName.firstName" size="25" maxlength="25"/></td>
							</tr>
							<tr id='hrow7' class='hidden'>
								<td class="formDeLabel"><bean:message key="prompt.activationDate"/></td>
								<td class="formDe" colspan="3">
									<html:text name="userProfileSearchForm" property="fromActivationDate" size="10" maxlength="10" styleId="fromActivationDate"/> &nbsp;to&nbsp;										
									<html:text name="userProfileSearchForm" property="toActivationDate" size="10" maxlength="10" styleId="toActivationDate"/>
								</td>
							</tr>
							<tr id='hrow8' class='hidden'>
								<td class="formDeLabel" ><bean:message key="prompt.inactivation"/>&nbsp;<bean:message key="prompt.date"/></td>
								<td class="formDe" colspan="3">
									<html:text name="userProfileSearchForm" property="fromInactivationDate" size="10" maxlength="10" styleId="fromInactivationDate"/> &nbsp;to&nbsp;
									<html:text name="userProfileSearchForm" property="toInactivationDate" size="10" maxlength="10" styleId="toInactivationDate"/>
								</td>
							</tr>
							<tr id='hrow9' class='hidden'>
								<td class="formDeLabel"><bean:message key="prompt.change"/>&nbsp;<bean:message key="prompt.date"/></td>
								<td class="formDe" colspan="3">
									<html:text name="userProfileSearchForm" property="fromChangeDate" size="10" maxlength="10" styleId="fromChangeDate"/> &nbsp;to&nbsp;
									<html:text name="userProfileSearchForm" property="toChangeDate" size="10" maxlength="10" styleId="toChangeDate"/>
								</td>
							</tr>
							<tr>
								<td class="formDeLabel">&nbsp;</td>
								<td class="formDe" colspan="3">
									<html:submit property="submitAction" onclick="return validateTextFields(this.form) && disableSubmit(this, this.form);">
										<bean:message key="button.findUserProfiles"></bean:message>
									</html:submit>	
									<html:submit property="submitAction"><bean:message key="button.refresh"></bean:message></html:submit>			  									                    
								</td>
							</tr>               
						</table>
					</td>
				</tr>
</html:form> 

<html:form action="/handleUserProfileSelection" target="content">
				<logic:equal name="userProfileForm" property="numUsers" value="2">
					<tr>
						<td align="center">
<!-- BEGIN INSTRUCTION TABLE  -->
							<table width="98%">
								<tr>
									<td>
										<ul>
											<li>Click User Profile radio button and then select hyperlink or button to perform action for that User Profile.</li>
										</ul>
									</td>
								</tr>
								<tr>
									<td align="center"><bean:write name="userProfileForm" property="userProfilesSize"/>&nbsp;search results found.</td>
								</tr>
							</table>
<!-- END INSTRUCTION TABLE  -->
<!-- Begin Pagination Header Tag-->
							<bean:define id="paginationResultsPerPage" type="java.lang.String"><bean:message key="pagination.recordsPerPage"></bean:message></bean:define> 
        
							<pg:pager index="center"
								maxPageItems="<%=Integer.parseInt(paginationResultsPerPage)%>"
								maxIndexPages="10"  export="offset,currentPageNumber=pageNumber" scope="request">
							
								<input type="hidden" name="pager.offset" value="<%= offset %>"/>
<!--BEGIN SEARCH RESULTS TABLE-->
							<table width="100%" border="0" cellpadding="0" cellspacing="0" class='borderTable'>
								<tr>
									<td valign="top" width="99%">
										<div>
											<table border="0" cellpadding="2" cellspacing="1">
												<tr bgcolor="#cccccc">
													<td width="1%"></td>
													<td>
										 				<table>
															<tr>
																<td class="subhead" valign="top"><bean:message key="prompt.name"/>
																    <jims:sortResults beanName="userProfileForm" results="userProfiles" 
                                                                        primaryPropSort="userName.lastName" primarySortType="STRING"
                                                                        secondPropSort="userName.firstName" secondarySortType="STRING" defaultSort="true" 
                                                                        defaultSortOrder="ASC" sortId="1" />
                                                                </td>
															</tr>	
														</table>  
													</td>
													<td>
														<table>
															<tr>			
																<td class="subhead" valign="top"><bean:message key="prompt.userId"/>
																    <jims:sortResults beanName="userProfileForm" results="userProfiles" 
                                                                        primaryPropSort="logonId" primarySortType="STRING" defaultSort="false" 
                                                                        defaultSortOrder="ASC" sortId="2" />
                                                                </td>
															</tr>
														</table> 
													</td>	
													<td>
														<table>
															<tr>									
																<td class="subhead" valign="top"><bean:message key="prompt.deptCode"/>
																    <jims:sortResults beanName="userProfileForm" results="userProfiles" 
                                                                        primaryPropSort="departmentId" primarySortType="STRING" defaultSort="false" 
                                                                        defaultSortOrder="ASC" sortId="3" />
                                                                </td>
															</tr>
														</table> 
													</td>										
													<td class="subhead" valign="top"><bean:message key="prompt.dob"/>
													    <jims:sortResults beanName="userProfileForm" results="userProfiles" 
                                                              primaryPropSort="dateOfBirth" primarySortType="DATE"
                                                              defaultSort="false" defaultSortOrder="ASC" sortId="4" /></td>
													<td class="subhead" valign="top" nowrap><bean:message key="prompt.SSN"/>
													    <jims:sortResults beanName="userProfileForm" results="userProfiles" 
                                                              primaryPropSort="ssn.SSN" primarySortType="STRING"
                                                              defaultSort="false" defaultSortOrder="ASC" sortId="5" /></td>
													<td class="subhead" valign="top"><bean:message key="prompt.phoneAndExt"/>
													    <jims:sortResults beanName="userProfileForm" results="userProfiles" 
                                                              primaryPropSort="phoneNum.phoneNumber" primarySortType="STRING"
                                                              defaultSort="false" defaultSortOrder="ASC" sortId="6" /></td>
													<td class="subhead" valign="top" width="1%"><bean:message key="prompt.userStatus"/>
													    <jims:sortResults beanName="userProfileForm" results="userProfiles" 
                                                              primaryPropSort="userStatus" primarySortType="STRING"
                                                              defaultSort="false" defaultSortOrder="ASC" sortId="7" /></td>
												</tr>
												<% String selectUser=""; %>
												<logic:iterate id="userIndex" name="userProfileForm" property="userProfiles" indexId="index">
					<!-- Begin Pagination item wrap -->
													<pg:item>
														<tr class="<%out.print((index.intValue() % 2 == 1) ? "alternateRow" : "normalRow"); %>">
															<td width="1%">
																<bean:define id="user" name="userIndex" property="logonId"/>
															    <input type="radio" name="selectedUser" value="<%=user%>" onclick="setUser('<bean:write name="userIndex" property="logonId"/>','<bean:write name="userIndex" property="trainingInd"/>')"/> 
															</td>
															<td>
																<a href="javascript:loadViewUser('/<msp:webapp/>handleUserProfileSelection.do?submitAction=View')" onclick="setUser('<bean:write name="userIndex" property="logonId"/>')"> 
																   <bean:write name="userIndex" property="userName.formattedName"/>
																</a>
															</td> 
															<td><bean:write name="userIndex" property="logonId"/></td>
															<td><bean:write name="userIndex" property="departmentId"/></td>
															<td><bean:write name="userIndex" property="dateOfBirth" formatKey="date.format.mmddyyyy"/></td>
															<td nowrap><bean:write name="userIndex" property="ssn.formattedSSN" /></td>
															<td><bean:write name="userIndex" property="phoneNum.formattedPhoneNumber"/>&nbsp;
																<bean:write name="userIndex" property="phoneNum.ext"/>
															</td>
															<td><bean:write name="userIndex" property="userStatus"/></td>
														</tr>
													</pg:item>
					<!-- End Pagination item wrap -->
												</logic:iterate> 
											</table> 
					<!-- Begin Pagination navigation Row-->
											<table align="center"> 
												<tr>
													<td>
														<pg:index>
															<tiles:insert page="/jsp/jimsPagination.jsp" flush="true">
																<tiles:put name="pagerUniqueName" value="pagerSearch"/>
																<tiles:put name="resultsPerPage" value="<%=paginationResultsPerPage%>"/>
															</tiles:insert>
														</pg:index>
													</td>
												</tr>
												<html:hidden  name="userProfileForm" property="selectedValue" styleId="hiddenVal"/>
											</table>
					<!-- End Pagination navigation Row-->
										</div> 
									</td>
								</tr>
							</table> 	
							<table width="100%">
								<tr>
									<td width="100%" align="center">
					<!--  BEGIN HYPERLINK SELECTS TABLE -->	
										<table border="0" cellpadding="2" cellspacing="1"> 
											<tr>
												<td>
													<a href="javascript:loadUser('/<msp:webapp/>handleUserProfileSelection.do?submitAction=Update')" onclick="return validateRadioFields();">
														<bean:message key="prompt.update"/></a>&nbsp;&nbsp;
													<a href="javascript:loadUser('/<msp:webapp/>handleUserProfileSelection.do?submitAction=Inactivate&action=inactivate')" onclick="return validateRadioFields();">
														<bean:message key="prompt.inactivate"/></a>&nbsp;&nbsp;
													<a href="javascript:loadUser('/<msp:webapp/>handleUserProfileSelection.do?submitAction=Transfer')" onclick="return validateRadioFields();">
														<bean:message key="prompt.transfer"/></a>&nbsp;&nbsp;
													<a href="javascript:loadUser('/<msp:webapp/>handleUserProfileSelection.do?submitAction=History')" onclick="return validateRadioFields();">
														<bean:message key="prompt.view"/>&nbsp;<bean:message key="prompt.history"/></a>
												</td>
											</tr>
										</table>
					<!--  END HYPERLINK SELECTS TABLE -->								
									</td>
								</tr>  
								<tr><td><br></td></tr>
								<tr>
									<td align="center">
									<logic:equal name="userProfileSearchForm" property="userIsLA" value="N">
										<html:submit property="submitAction">
											<bean:message key="button.manageUserGroup"></bean:message>
										</html:submit> 
									</logic:equal>	
										<html:submit property="submitAction">
											<bean:message key="button.cancel"></bean:message>
										</html:submit>
									</td>
								</tr>							
							</table> 
<!--END SEARCH RESULTS TABLE-->
							</pg:pager>
						</td>
					</tr>
				</logic:equal>
</html:form> 
			</table>
		</td>
	</tr>
</table> 
<!--  END BLUE BORDER TABLE -->
<html:form action="/displayUserProfileCreateUpdate" target="content" >	
<!--BEGIN BUTTON TABLE--> 
	<table width="98%" align="center">
		<tr>
			<td align="center">
				<html:submit property="submitAction">
					<bean:message key="button.createNewUserProfile"></bean:message>
				</html:submit>
			</td>
		</tr>  
	</table>  
<!--END BUTTON TABLE-->
</html:form> 
<div align="center"><script type="text/javascript">renderBackToTop()</script></div>
</body>
</html:html>